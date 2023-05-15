// 
// 
// 

package com.ssm.mapper;

import java.util.List;
import com.ssm.po.School;

public interface SchoolMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(School p0);
    
    int insertSelective(School p0);
    
    School selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(School p0);
    
    int updateByPrimaryKey(School p0);
    
    List<School> selectAllSchools();
    
    List<School> selectAllSchoolsNoState();
}
