package com.hungkuei.crud.dao;

import com.hugkuei.crud.model.Institute;
import com.hugkuei.crud.model.InstituteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InstituteMapper {
    long countByExample(InstituteExample example);

    int deleteByExample(InstituteExample example);

    int deleteByPrimaryKey(Integer insId);

    int insert(Institute record);

    int insertSelective(Institute record);

    List<Institute> selectByExample(InstituteExample example);

    Institute selectByPrimaryKey(Integer insId);

    int updateByExampleSelective(@Param("record") Institute record, @Param("example") InstituteExample example);

    int updateByExample(@Param("record") Institute record, @Param("example") InstituteExample example);

    int updateByPrimaryKeySelective(Institute record);

    int updateByPrimaryKey(Institute record);
}