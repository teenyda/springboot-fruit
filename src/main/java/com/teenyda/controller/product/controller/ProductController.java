package com.teenyda.controller.product.controller;

import com.teenyda.common.ResultBody;
import com.teenyda.controller.api.AbstractApiController;
import com.teenyda.entity.Product;
import com.teenyda.controller.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Product)表控制层
 *
 * @author makejava
 * @since 2020-10-09 20:17:18
 */
@RestController
@RequestMapping("fruit")
public class ProductController extends AbstractApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ResultBody<Product> selectOne(Integer id) {
        return responseSuccessJson(this.productService.queryById(id));
    }

    @GetMapping("product")
    public ResultBody<List<Product>> all() {
        return responseSuccessJson(this.productService.queryAllByLimit(0, 100));
    }

    @PostMapping("product")
    public ResultBody<Product> insert(@RequestBody Product product) {
        Product p = this.productService.insert(product);
        return responseSuccessJson(p);
    }
}