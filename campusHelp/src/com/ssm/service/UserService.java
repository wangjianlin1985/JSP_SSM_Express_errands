// 
// 
// 

package com.ssm.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ssm.po.User;

public interface UserService
{
    int setUser(User p0);
    
    int getAccountCount(String p0);
    
    User login(String p0);
    
    int updateUserInfo(User p0);
    
    List<User> getByLikeNameAccount(@Param("words") String p0);
    
    User getByUid(Integer p0);
}
