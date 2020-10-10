package com.teenyda.controller;

import com.teenyda.common.ResultBody;
import com.teenyda.controller.api.AbstractApiController;
import com.teenyda.entity.Wallet;
import com.teenyda.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Wallet)表控制层
 *
 * @author makejava
 * @since 2020-10-09 20:17:26
 */
@RestController
@RequestMapping("wallet")
public class WalletController extends AbstractApiController {
    /**
     * 服务对象
     */
    @Resource
    private WalletService walletService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ResultBody<Wallet> selectOne(Integer id) {
        return responseSuccessJson(this.walletService.queryById(id));
    }

}