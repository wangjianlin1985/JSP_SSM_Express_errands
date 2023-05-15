// 
// 
// 

package com.ssm.po;

import java.util.Date;

public class Admin
{
    private Integer aid;
    private String account;
    private String password;
    private String name;
    private Date addtime;
    private Integer state;
    
    public Admin() {
    }
    
    public Admin(final Integer aid, final String account, final String password, final String name, final Date addtime, final Integer state) {
        this.aid = aid;
        this.account = account;
        this.password = password;
        this.name = name;
        this.addtime = addtime;
        this.state = state;
    }
    
    public Integer getAid() {
        return this.aid;
    }
    
    public void setAid(final Integer aid) {
        this.aid = aid;
    }
    
    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(final String account) {
        this.account = account;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Date getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(final Date addtime) {
        this.addtime = addtime;
    }
    
    public Integer getState() {
        return this.state;
    }
    
    public void setState(final Integer state) {
        this.state = state;
    }
    
    @Override
    public String toString() {
        return "Admin [aid=" + this.aid + ", account=" + this.account + ", password=" + this.password + ", name=" + this.name + ", addtime=" + this.addtime + ", state=" + this.state + "]";
    }
}
