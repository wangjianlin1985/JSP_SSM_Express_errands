// 
// 
// 

package com.ssm.service.impl;

import java.util.List;
import com.ssm.po.User;
import javax.annotation.Resource;
import com.ssm.mapper.UserMapper;
import org.springframework.stereotype.Service;
import com.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    
    @Override
    public int setUser(final User record) {
        int result = 0;
        try {
            result = this.userMapper.insert(record);
        }
        catch (Exception ex) {}
        return result;
    }
    
    @Override
    public int getAccountCount(final String account) {
        int result = 0;
        result = this.userMapper.selectAccountCount(account);
        return result;
    }
    
    @Override
    public User login(final String account) {
        User user = null;
        user = this.userMapper.selectUserByAccount(account);
        return user;
    }
    
    @Override
    public int updateUserInfo(final User user) {
        int result = 0;
        try {
            result = this.userMapper.updateByPrimaryKeySelective(user);
        }
        catch (Exception ex) {}
        return result;
    }
    
    @Override
    public List<User> getByLikeNameAccount(final String words) {
        List<User> list = null;
        list = this.userMapper.selectByLikeNameAccount(words);
        return list;
    }
    
    @Override
    public User getByUid(final Integer uid) {
        User user = null;
        user = this.userMapper.selectByPrimaryKey(uid);
        return user;
    }
}
