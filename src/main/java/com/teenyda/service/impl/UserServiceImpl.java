package com.teenyda.service.impl;

import com.teenyda.common.GlobalErrorInfoException;
import com.teenyda.controller.user.exception.LoginFailException;
import com.teenyda.dao.UserDao;
import com.teenyda.dao.WalletDao;
import com.teenyda.entity.User;
import com.teenyda.entity.Wallet;
import com.teenyda.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-10-09 17:11:01
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private WalletDao walletDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer userId) {
        return this.userDao.queryById(userId);
    }

    @Override
    public User queryByPassword(User user) throws GlobalErrorInfoException {
        User byUserName = this.userDao.queryByUserName(user.getUsername());
        if (byUserName == null) {
            throw new GlobalErrorInfoException(LoginFailException.USER_NOT_EXIST);
        }

        User byPassword = this.userDao.queryByPassword(user);
        if (byPassword == null) {
            throw new GlobalErrorInfoException(LoginFailException.USERNAME_PASSWORD_ERR);
        }
        return byPassword;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public User insert(User user) throws GlobalErrorInfoException {
        User byUserName = this.userDao.queryByUserName(user.getUsername());
        if (byUserName != null) {
            throw new GlobalErrorInfoException(LoginFailException.USER_EXIST);
        }
        Wallet wallet = new Wallet();
        user.setRegisterTime(new Date());
        this.userDao.insert(user);
        wallet.setUserId(user.getUserId());
        wallet.setBalance(100d);
        walletDao.insert(wallet);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userId) {
        return this.userDao.deleteById(userId) > 0;
    }
}