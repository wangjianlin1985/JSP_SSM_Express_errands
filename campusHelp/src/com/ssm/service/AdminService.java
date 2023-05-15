// 
// 
// 

package com.ssm.service;

import com.ssm.po.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminService
{
    int getAccountCount(@Param("account") String p0);
    
    Admin getAdminByAccount(@Param("account") String p0);
    
    int updateAdminInfo(Admin p0);
    
    Admin login(String p0);
    
    int setAdmin(Admin p0);
}
