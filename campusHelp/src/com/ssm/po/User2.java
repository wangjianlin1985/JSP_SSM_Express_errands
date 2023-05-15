// 
// 
// 

package com.ssm.po;

import java.util.Date;

public class User2
{
    private Integer stuid;
    private String studentid;
    private String password;
    private Integer schoolid;
    private Integer sex;
    private String name;
    private Date registertime;
    private String money;
    private Integer state;
    
    public User2() {
    }
    
    public User2(final Integer stuid, final String studentid, final String password, final Integer schoolid, final Integer sex, final String name, final Date registertime, final String money, final Integer state) {
        this.stuid = stuid;
        this.studentid = studentid;
        this.password = password;
        this.schoolid = schoolid;
        this.sex = sex;
        this.name = name;
        this.registertime = registertime;
        this.money = money;
        this.state = state;
    }
    
    public Integer getStuid() {
        return this.stuid;
    }
    
    public void setStuid(final Integer stuid) {
        this.stuid = stuid;
    }
    
    public String getStudentid() {
        return this.studentid;
    }
    
    public void setStudentid(final String studentid) {
        this.studentid = studentid;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public Integer getSchoolid() {
        return this.schoolid;
    }
    
    public void setSchoolid(final Integer schoolid) {
        this.schoolid = schoolid;
    }
    
    public Integer getSex() {
        return this.sex;
    }
    
    public void setSex(final Integer sex) {
        this.sex = sex;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Date getRegistertime() {
        return this.registertime;
    }
    
    public void setRegistertime(final Date registertime) {
        this.registertime = registertime;
    }
    
    public String getMoney() {
        return this.money;
    }
    
    public void setMoney(final String money) {
        this.money = money;
    }
    
    public Integer getState() {
        return this.state;
    }
    
    public void setState(final Integer state) {
        this.state = state;
    }
    
    @Override
    public String toString() {
        return "User [stuid=" + this.stuid + ", studentid=" + this.studentid + ", password=" + this.password + ", schoolid=" + this.schoolid + ", sex=" + this.sex + ", name=" + this.name + ", registertime=" + this.registertime + ", money=" + this.money + ", state=" + this.state + "]";
    }
}
