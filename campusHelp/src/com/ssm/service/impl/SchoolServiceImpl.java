// 
// 
// 

package com.ssm.service.impl;

import com.ssm.po.School;
import java.util.List;
import javax.annotation.Resource;
import com.ssm.mapper.SchoolMapper;
import org.springframework.stereotype.Service;
import com.ssm.service.SchoolService;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService
{
    @Resource(name = "schoolMapper")
    private SchoolMapper schoolMapper;
    
    @Override
    public List<School> getAllSchools() {
        return this.schoolMapper.selectAllSchools();
    }
    
    @Override
    public School getSchoolByID(final Integer schoolid) {
        return this.schoolMapper.selectByPrimaryKey(schoolid);
    }
    
    @Override
    public int updateSchool(final School school) {
        return this.schoolMapper.updateByPrimaryKeySelective(school);
    }
    
    @Override
    public List<School> getAllSchoolsNoState() {
        return this.schoolMapper.selectAllSchoolsNoState();
    }
    
    @Override
    public int setSchool(final School school) {
        return this.schoolMapper.insert(school);
    }
}
