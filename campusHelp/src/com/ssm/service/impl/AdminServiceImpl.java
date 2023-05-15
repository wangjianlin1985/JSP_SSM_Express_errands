// 
// 
// 

package com.ssm.service.impl;

import com.ssm.po.Admin;
import javax.annotation.Resource;
import com.ssm.mapper.AdminMapper;
import org.springframework.stereotype.Service;
import com.ssm.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService
{
    @Resource(name = "adminMapper")
    private AdminMapper adminMapper;
    
    @Override
    public int getAccountCount(final String account) {
        return this.adminMapper.selectAccountCount(account);
    }
    
    @Override
    public Admin getAdminByAccount(final String account) {
        return this.adminMapper.selectAdminByAccount(account);
    }
    
    @Override
    public int updateAdminInfo(final Admin admin) {
        int result = 0;
        try {
            result = this.adminMapper.updateByPrimaryKeySelective(admin);
        }
        catch (Exception ex) {}
        return result;
    }
    
    @Override
    public Admin login(final String account) {
        Admin admin = null;
        admin = this.adminMapper.selectAdminByAccount(account);
        return admin;
    }
    
    @Override
    public int setAdmin(final Admin admin) {
        int result = 0;
        try {
            result = this.adminMapper.insert(admin);
        }
        catch (Exception ex) {}
        return result;
    }
}
