package com.teenyda.mapper.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.teenyda.dao.Discounts;
/**
*  @author mymx.banner
*/
public interface DiscountsBaseMapper {

    int insertDiscounts(Discounts object);

    int updateDiscounts(Discounts object);

    int update(Discounts.UpdateBuilder object);

    List<Discounts> queryDiscounts(Discounts object);

    Discounts queryDiscountsLimit1(Discounts object);

}