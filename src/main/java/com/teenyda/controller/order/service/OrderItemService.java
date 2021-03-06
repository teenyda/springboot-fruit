package com.teenyda.controller.order.service;

import com.teenyda.controller.order.dto.SettlementOrder;
import com.teenyda.entity.OrderInfo;
import com.teenyda.entity.OrderItem;

import java.util.List;

/**
 * (OrderItem)表服务接口
 *
 * @author makejava
 * @since 2020-10-09 17:10:50
 */
public interface OrderItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderItemId 主键
     * @return 实例对象
     */
    SettlementOrder queryByOrderNum(String orderNum);

    List<SettlementOrder> queryCartOrder(String orderNum);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderItem> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orderItem 实例对象
     * @param type
     * @return 实例对象
     */
    OrderItem insert(OrderItem orderItem, int type);

    /**
     * 修改数据
     *
     * @param orderItem 实例对象
     * @return 实例对象
     */
    OrderItem update(OrderItem orderItem);

    OrderInfo updateCart(List<OrderItem> orderItem);

    /**
     * 通过主键删除数据
     *
     * @param orderItemId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderItemId);

    /**
     * 查询所有订单
     * @return
     * @param userId
     */
    List<OrderInfo> queryAllOrder(Integer userId);

    /**
     * 后台 查询所有订单
     * @return
     */
    List<OrderInfo> queryAllOrder();
    List<OrderInfo> queryCart(Integer userId);
    List<OrderInfo> queryByStatus(Integer userId, Integer status);

    OrderInfo queryOrderByNumber(Integer userId, String orderNum);

}