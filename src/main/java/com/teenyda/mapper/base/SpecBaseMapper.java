package com.teenyda.mapper.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.teenyda.dao.Spec;
/**
*  @author mymx.banner
*/
public interface SpecBaseMapper {

    int insertSpec(Spec object);

    int updateSpec(Spec object);

    int update(Spec.UpdateBuilder object);

    List<Spec> querySpec(Spec object);

    Spec querySpecLimit1(Spec object);

}