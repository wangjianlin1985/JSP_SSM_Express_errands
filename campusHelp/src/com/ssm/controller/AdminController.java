// 
// 
// 

package com.ssm.controller;

import org.springframework.web.bind.annotation.InitBinder;
import java.beans.PropertyEditor;
import java.text.DateFormat;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.ServletRequestDataBinder;
import com.ssm.po.School;
import com.ssm.po.User;
import com.ssm.po.Task;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import com.ssm.po.Admin;
import org.springframework.ui.Model;
import com.ssm.service.UserService;
import com.ssm.service.TaskService;
import com.ssm.service.SchoolService;
import javax.annotation.Resource;
import com.ssm.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;

@Controller
@SessionAttributes({ "nowadmin" })
@RequestMapping({ "admin/" })
public class AdminController
{
    @Resource(name = "adminService")
    public AdminService adminService;
    @Resource(name = "schoolService")
    public SchoolService schoolService;
    @Resource(name = "taskService")
    public TaskService taskService;
    @Resource(name = "userService")
    public UserService userService;
    
    @RequestMapping({ "adminlogin.do" })
    public String adminlogin(final String account, final String password, final Model model) {
        Admin admin = null;
        admin = this.adminService.login(account);
        if (admin == null) {
            model.addAttribute("msg", (Object)"\u767b\u5f55\u8d26\u53f7\u4e0d\u5b58\u5728");
            return "login";
        }
        if (password.equals(admin.getPassword())) {
            model.addAttribute("nowadmin", (Object)admin);
            return "adminIndex";
        }
        model.addAttribute("msg", (Object)"\u5bc6\u7801\u9a8c\u8bc1\u9519\u8bef\u6b63\u786e");
        return "login";
    }
    
    @RequestMapping({ "update.do" })
    public String update(final HttpServletRequest request, final Admin admin, final Model model) {
        final int r = this.adminService.updateAdminInfo(admin);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u66f4\u65b0\u6210\u529f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55");
            request.getSession(false).removeAttribute("nowadmin");
            return "login";
        }
        model.addAttribute("msg", (Object)"\u66f4\u65b0\u5931\u8d25");
        return "adminUpdate";
    }
    
    @RequestMapping({ "updatepwd.do" })
    public String updatepwd(final HttpServletRequest request, final String oldpassword, final Admin admin, final Model model) {
        System.out.println("9999999999999");
        final Admin oldadmin = (Admin)request.getSession(false).getAttribute("nowadmin");
        System.out.println("6666666666666");
        System.out.println(oldadmin.getPassword());
        System.out.println(oldpassword);
        if (!oldadmin.getPassword().equals(oldpassword)) {
            model.addAttribute("msg", (Object)"\u539f\u5bc6\u7801\u9519\u8bef");
            return "adminPassword";
        }
        final int r = this.adminService.updateAdminInfo(admin);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u6210\u529f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55");
            request.getSession(false).removeAttribute("nowadmin");
            return "login";
        }
        model.addAttribute("msg", (Object)"\u4fee\u6539\u5931\u8d25");
        return "adminPassword";
    }
    
    @RequestMapping({ "getadmin.do" })
    public String getadmin(final String aidstr, final HttpServletRequest request, final Model model) {
        int aid = 0;
        try {
            aid = Integer.parseInt(aidstr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return "adminInfo";
        }
        if (aid == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return "adminInfo";
        }
        return "adminInfo";
    }
    
    @RequestMapping({ "addadmin.do" })
    public String addadmin(final String account, final HttpServletRequest request, final Model model) {
        final int countnum = this.adminService.getAccountCount(account);
        if (countnum > 0) {
            model.addAttribute("msg", (Object)(String.valueOf(account) + "   \u8be5\u8d26\u53f7\u5df2\u7ecf\u88ab\u4f7f\u7528"));
            return "adminAddAdmin";
        }
        final Admin admin = new Admin(0, account, "123456", account, new Date(), 0);
        final int result = this.adminService.setAdmin(admin);
        if (result <= 0) {
            model.addAttribute("msg", (Object)"\u6ce8\u518c\u5931\u8d25");
            return "adminAddAdmin";
        }
        model.addAttribute("msg", (Object)"\u6ce8\u518c\u6210\u529f\uff0c\u53ef\u4ee5\u767b\u5f55\u3002\u9ed8\u8ba4\u5bc6\u7801\uff1a123456");
        return "adminAddAdmin";
    }
    
    @RequestMapping({ "gettasks.do" })
    public String gettasks(String words, @RequestParam(required = true, defaultValue = "-1") final String schoolidstr, final Model model) {
        model.addAttribute("words", (Object)words);
        model.addAttribute("schoolidstr", (Object)schoolidstr);
        int schoolid = -1;
        if (!schoolidstr.equals("-1")) {
            try {
                schoolid = Integer.parseInt(schoolidstr);
            }
            catch (Exception e) {
                System.err.println("err");
            }
        }
        if (words != null) {
            words = "%" + words + "%";
        }
        else {
            words = "%%";
        }
        final List<Task> list = this.taskService.getTaskByKeys(words, schoolid);
        model.addAttribute("list", (Object)list);
        return "adminTask";
    }
    
    @RequestMapping({ "taskclose.do" })
    public String taskclose(final String tidstr, final String words, @RequestParam(required = true, defaultValue = "-1") final String schoolidstr, final HttpServletRequest request, final Model model) {
        int tid = 0;
        try {
            tid = Integer.parseInt(tidstr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.gettasks(words, schoolidstr, model);
        }
        if (tid == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.gettasks(words, schoolidstr, model);
        }
        Admin admin = null;
        try {
            admin = (Admin)request.getSession(false).getAttribute("nowadmin");
            int uid = 0;
            uid = admin.getAid();
            if (admin == null || uid == 0) {
                model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
                return this.gettasks(words, schoolidstr, model);
            }
        }
        catch (Exception e2) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return "login";
        }
        final Task theTask = this.taskService.getTask(tid);
        theTask.setState(1);
        final int r = this.taskService.updateTask(theTask);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u6210\u529f");
        }
        else {
            model.addAttribute("msg", (Object)"\u5931\u8d25");
        }
        return this.gettasks(words, schoolidstr, model);
    }
    
    @RequestMapping({ "getusers.do" })
    public String getusers(String userstr, final Model model) {
        model.addAttribute("keys", (Object)userstr);
        if (userstr != null) {
            userstr = "%" + userstr + "%";
        }
        else {
            userstr = "%%";
        }
        final List<User> list = this.userService.getByLikeNameAccount(userstr);
        model.addAttribute("list", (Object)list);
        return "adminUser";
    }
    
    @RequestMapping({ "getuser.do" })
    public String getuser(final String stuidstr, final Model model) {
        int stuid = 0;
        try {
            stuid = Integer.parseInt(stuidstr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return "userInfo";
        }
        if (stuid == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return "userInfo";
        }
        final User user = this.userService.getByUid(stuid);
        model.addAttribute("theuser", (Object)user);
        return "adminUserMoney";
    }
    
    @RequestMapping({ "addusermoney.do" })
    public String addusermoney(final String moneystr, final String stuidstr, final Model model) {
        double money = 0.0;
        try {
            money = Double.parseDouble(moneystr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u91d1\u989d\u51fa\u73b0\u9519\u8bef");
            return this.getuser(stuidstr, model);
        }
        if (stuidstr == null) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getuser(stuidstr, model);
        }
        if (stuidstr.length() == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getuser(stuidstr, model);
        }
        int stuid = 0;
        try {
            stuid = Integer.parseInt(stuidstr);
            if (stuid == 0) {
                model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
                return this.getuser(stuidstr, model);
            }
        }
        catch (Exception e2) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getuser(stuidstr, model);
        }
        final User theUser = this.userService.getByUid(stuid);
        theUser.setMoney(theUser.getMoney() + money);
        final int r = this.userService.updateUserInfo(theUser);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u6210\u529f");
        }
        else {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u5931\u8d25");
        }
        return this.getuser(stuidstr, model);
    }
    
    @RequestMapping({ "useropen.do" })
    public String useropen(final String keys, final String stuidstr, final Model model) {
        if (stuidstr == null) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusers(keys, model);
        }
        if (stuidstr.length() == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusers(keys, model);
        }
        int stuid = 0;
        try {
            stuid = Integer.parseInt(stuidstr);
            if (stuid == 0) {
                model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
                return this.getusers(keys, model);
            }
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusers(keys, model);
        }
        final User theUser = this.userService.getByUid(stuid);
        theUser.setState(0);
        final int r = this.userService.updateUserInfo(theUser);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u6210\u529f");
        }
        else {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u5931\u8d25");
        }
        return this.getusers(keys, model);
    }
    
    @RequestMapping({ "userclose.do" })
    public String userclose(final String keys, final String stuidstr, final Model model) {
        if (stuidstr == null) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusers(keys, model);
        }
        if (stuidstr.length() == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusers(keys, model);
        }
        int stuid = 0;
        try {
            stuid = Integer.parseInt(stuidstr);
            if (stuid == 0) {
                model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
                return this.getusers(keys, model);
            }
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusers(keys, model);
        }
        final User theUser = this.userService.getByUid(stuid);
        theUser.setState(1);
        final int r = this.userService.updateUserInfo(theUser);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u6210\u529f");
        }
        else {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u5931\u8d25");
        }
        return this.getusers(keys, model);
    }
    
    @RequestMapping({ "getschools.do" })
    public String getschools(final Model model) {
        final List<School> list = this.schoolService.getAllSchools();
        model.addAttribute("list", (Object)list);
        return "adminSchool";
    }
    
    @RequestMapping({ "getschool.do" })
    public String getschool(final String schoolidstr, final Model model) {
        if (schoolidstr == null) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return "adminSchoolSetting";
        }
        if (schoolidstr.length() == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return "adminSchoolSetting";
        }
        int schoolid = 0;
        try {
            schoolid = Integer.parseInt(schoolidstr);
            if (schoolid == 0) {
                model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
                return "adminSchoolSetting";
            }
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getschools(model);
        }
        final School theSchool = this.schoolService.getSchoolByID(schoolid);
        if (theSchool != null) {
            model.addAttribute("theSchool", (Object)theSchool);
        }
        else {
            model.addAttribute("msg", (Object)"\u8bfb\u53d6\u5931\u8d25");
        }
        return "adminSchoolSetting";
    }
    
    @RequestMapping({ "updateschool.do" })
    public String updateschool(final School school, final Model model) {
        int r = 0;
        r = this.schoolService.updateSchool(school);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u6210\u529f-\u5237\u65b0\u9875\u9762\u91cd\u65b0\u52a0\u8f7d\u663e\u793a");
        }
        else {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u5931\u8d25");
        }
        final School theSchool = this.schoolService.getSchoolByID(school.getSchoolid());
        model.addAttribute("theSchool", (Object)theSchool);
        return "adminSchoolSetting";
    }
    
    @RequestMapping({ "addschool.do" })
    public String addschool(final String name, final Model model) {
        if (name == null) {
            model.addAttribute("msg", (Object)"\u6dfb\u52a0\u5931\u8d25");
            return "adminSchoolAdd";
        }
        if (name.length() == 0) {
            model.addAttribute("msg", (Object)"\u6dfb\u52a0\u5931\u8d25");
            return "adminSchoolAdd";
        }
        final School theSchool = new School(0, name, new Date(), 0);
        int r = 0;
        r = this.schoolService.setSchool(theSchool);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u6dfb\u52a0\u6210\u529f");
            model.addAttribute("flag", (Object)"\u6dfb\u52a0\u6210\u529f");
            return "adminSchoolAdd";
        }
        model.addAttribute("msg", (Object)"\u6dfb\u52a0\u5931\u8d25");
        return "adminSchoolAdd";
    }
    
    @InitBinder
    public void InitBinder(final ServletRequestDataBinder bin) {
        bin.registerCustomEditor((Class)Date.class, (PropertyEditor)new CustomDateEditor((DateFormat)new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }
}
