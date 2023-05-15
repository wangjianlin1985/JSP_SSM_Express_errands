// 
// 
// 

package com.ssm.service.impl;

import com.ssm.po.Task;
import java.util.List;
import javax.annotation.Resource;
import com.ssm.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import com.ssm.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService
{
    @Resource(name = "taskMapper")
    private TaskMapper taskMapper;
    
    @Override
    public List<Task> getAllTask() {
        return this.taskMapper.selectAllTask();
    }
    
    @Override
    public int setNewTask(final Task task) {
        return this.taskMapper.insert(task);
    }
    
    @Override
    public List<Task> getUserTask(final Integer stuid) {
        return this.taskMapper.selectUserTask(stuid);
    }
    
    @Override
    public Task getTask(final Integer tid) {
        return this.taskMapper.selectByPrimaryKey(tid);
    }
    
    @Override
    public int updateTask(final Task task) {
        return this.taskMapper.updateByPrimaryKeySelective(task);
    }
    
    @Override
    public List<Task> getUserATask(final Integer stuid) {
        return this.taskMapper.selectUserATask(stuid);
    }
    
    @Override
    public List<Task> getTaskByKeys(final String words, final Integer schoolid) {
        return this.taskMapper.selectTaskByKeys(words, schoolid);
    }
    
    @Override
    public List<Task> getTaskByKeysNoState(final String words, final Integer schoolid) {
        return this.taskMapper.selectTaskByKeysNoState(words, schoolid);
    }
}
