// 
// 
// 

package com.ssm.po;

import java.util.Date;

public class Task2
{
    private Integer taskid;
    private String publishUserId;
    private String publishUserName;
    private Integer publishSchoolId;
    private Integer acceptUserId;
    private String reward;
    private Date addtime;
    private Date endtime;
    private String taskname;
    private String taskcontext;
    private Integer state;
    
    public Task2() {
    }
    
    public Task2(final Integer taskid, final String publishUserId, final String publishUserName, final Integer publishSchoolId, final Integer acceptUserId, final String reward, final Date addtime, final Date endtime, final String taskname, final String taskcontext, final Integer state) {
        this.taskid = taskid;
        this.publishUserId = publishUserId;
        this.publishUserName = publishUserName;
        this.publishSchoolId = publishSchoolId;
        this.acceptUserId = acceptUserId;
        this.reward = reward;
        this.addtime = addtime;
        this.endtime = endtime;
        this.taskname = taskname;
        this.taskcontext = taskcontext;
        this.state = state;
    }
    
    public Date getEndtime() {
        return this.endtime;
    }
    
    public void setEndtime(final Date endtime) {
        this.endtime = endtime;
    }
    
    public Integer getTaskid() {
        return this.taskid;
    }
    
    public void setTaskid(final Integer taskid) {
        this.taskid = taskid;
    }
    
    public String getPublishUserId() {
        return this.publishUserId;
    }
    
    public void setPublishUserId(final String publishUserId) {
        this.publishUserId = publishUserId;
    }
    
    public String getPublishUserName() {
        return this.publishUserName;
    }
    
    public void setPublishUserName(final String publishUserName) {
        this.publishUserName = publishUserName;
    }
    
    public Integer getPublishSchoolId() {
        return this.publishSchoolId;
    }
    
    public void setPublishSchoolId(final Integer publishSchoolId) {
        this.publishSchoolId = publishSchoolId;
    }
    
    public Integer getAcceptUserId() {
        return this.acceptUserId;
    }
    
    public void setAcceptUserId(final Integer acceptUserId) {
        this.acceptUserId = acceptUserId;
    }
    
    public String getReward() {
        return this.reward;
    }
    
    public void setReward(final String reward) {
        this.reward = reward;
    }
    
    public Date getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(final Date addtime) {
        this.addtime = addtime;
    }
    
    public String getTaskname() {
        return this.taskname;
    }
    
    public void setTaskname(final String taskname) {
        this.taskname = taskname;
    }
    
    public String getTaskcontext() {
        return this.taskcontext;
    }
    
    public void setTaskcontext(final String taskcontext) {
        this.taskcontext = taskcontext;
    }
    
    public Integer getState() {
        return this.state;
    }
    
    public void setState(final Integer state) {
        this.state = state;
    }
    
    @Override
    public String toString() {
        return "Task [taskid=" + this.taskid + ", publishUserId=" + this.publishUserId + ", publishUserName=" + this.publishUserName + ", publishSchoolId=" + this.publishSchoolId + ", acceptUserId=" + this.acceptUserId + ", reward=" + this.reward + ", addtime=" + this.addtime + ", endtime=" + this.endtime + ", taskname=" + this.taskname + ", taskcontext=" + this.taskcontext + ", state=" + this.state + "]";
    }
}
