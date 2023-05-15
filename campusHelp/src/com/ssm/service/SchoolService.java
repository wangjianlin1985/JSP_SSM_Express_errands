// 
// 
// 

package com.ssm.service;

import com.ssm.po.School;
import java.util.List;

public interface SchoolService
{
    List<School> getAllSchools();
    
    List<School> getAllSchoolsNoState();
    
    School getSchoolByID(Integer p0);
    
    int updateSchool(School p0);
    
    int setSchool(School p0);
}
