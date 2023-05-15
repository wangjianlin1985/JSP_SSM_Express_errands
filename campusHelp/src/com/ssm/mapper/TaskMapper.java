// 
// 
// 

package com.ssm.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ssm.po.Task;

public interface TaskMapper
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Task p0);
    
    int insertSelective(Task p0);
    
    Task selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Task p0);
    
    int updateByPrimaryKey(Task p0);
    
    List<Task> selectAllTask();
    
    List<Task> selectUserTask(Integer p0);
    
    List<Task> selectUserATask(Integer p0);
    
    List<Task> selectTaskByKeys(@Param("words") String p0, @Param("schoolid") Integer p1);
    
    List<Task> selectTaskByKeysNoState(@Param("words") String p0, @Param("schoolid") Integer p1);
}
