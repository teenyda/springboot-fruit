package com.teenyda.mapper.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.teenyda.dao.SnapUp;
/**
*  @author mymx.banner
*/
public interface SnapUpBaseMapper {

    int insertSnapUp(SnapUp object);

    int updateSnapUp(SnapUp object);

    int update(SnapUp.UpdateBuilder object);

    List<SnapUp> querySnapUp(SnapUp object);

    SnapUp querySnapUpLimit1(SnapUp object);

}