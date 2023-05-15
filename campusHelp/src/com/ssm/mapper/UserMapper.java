// 
// 
// 

package com.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ssm.po.User;

public interface UserMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(User p0);
    
    int insertSelective(User p0);
    
    User selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(User p0);
    
    int updateByPrimaryKey(User p0);
    
    List<User> selectByLikeNameAccount(@Param("words") String p0);
    
    int selectAccountCount(@Param("account") String p0);
    
    User selectUserByAccount(@Param("account") String p0);
}
