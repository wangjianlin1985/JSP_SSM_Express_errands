// 
// 
// 

package com.ssm.service;

import org.apache.ibatis.annotations.Param;
import com.ssm.po.Task;
import java.util.List;

public interface TaskService
{
    List<Task> getAllTask();
    
    int setNewTask(Task p0);
    
    List<Task> getUserTask(Integer p0);
    
    List<Task> getUserATask(Integer p0);
    
    Task getTask(Integer p0);
    
    int updateTask(Task p0);
    
    List<Task> getTaskByKeys(String p0, Integer p1);
    
    List<Task> getTaskByKeysNoState(@Param("words") String p0, @Param("schoolid") Integer p1);
}
