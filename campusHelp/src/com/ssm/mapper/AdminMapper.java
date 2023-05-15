// 
// 
// 

package com.ssm.mapper;

import org.apache.ibatis.annotations.Param;
import com.ssm.po.Admin;

public interface AdminMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Admin p0);
    
    int insertSelective(Admin p0);
    
    Admin selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Admin p0);
    
    int updateByPrimaryKey(Admin p0);
    
    int selectAccountCount(@Param("account") String p0);
    
    Admin selectAdminByAccount(@Param("account") String p0);
}
