// 
// 
// 

package com.ssm.po;

import java.util.Date;

public class School
{
    private Integer schoolid;
    private String name;
    private Date addtime;
    private Integer state;
    
    public School() {
    }
    
    public School(final Integer schoolid, final String name, final Date addtime, final Integer state) {
        this.schoolid = schoolid;
        this.name = name;
        this.addtime = addtime;
        this.state = state;
    }
    
    public Integer getSchoolid() {
        return this.schoolid;
    }
    
    public void setSchoolid(final Integer schoolid) {
        this.schoolid = schoolid;
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
        return "School [schoolid=" + this.schoolid + ", name=" + this.name + ", addtime=" + this.addtime + ", state=" + this.state + "]";
    }
}
