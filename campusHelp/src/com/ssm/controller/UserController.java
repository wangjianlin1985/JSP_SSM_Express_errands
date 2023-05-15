// 
// 
// 

package com.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import com.ssm.po.User;
import org.springframework.ui.Model;
import com.ssm.service.AdminService;
import javax.annotation.Resource;
import com.ssm.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;

@Controller
@SessionAttributes({ "nowuser" })
@RequestMapping({ "user/" })
public class UserController
{
    @Resource(name = "userService")
    public UserService userService;
    @Resource(name = "adminService")
    public AdminService adminService;
    
    @RequestMapping({ "login.do" })
    public String login(final String studentid, final String password, final Model model) {
        User user = null;
        user = this.userService.login(studentid);
        if (user == null) {
            model.addAttribute("msg", (Object)"\u767b\u5f55\u5931\u8d25--\u8bf7\u786e\u5b9a\u8d26\u53f7\u548c\u5bc6\u7801\u6b63\u786e");
            return "login";
        }
        if (password.equals(user.getPassword())) {
            model.addAttribute("nowuser", (Object)user);
            model.addAttribute("loginflag", (Object)"loginflag");
            return "index";
        }
        model.addAttribute("msg", (Object)"\u767b\u5f55\u5931\u8d25--\u8bf7\u786e\u5b9a\u8d26\u53f7\u548c\u5bc6\u7801\u6b63\u786e");
        return "login";
    }
    
    @RequestMapping({ "register.do" })
    public String register(final User user, final Model model) {
        final int countnum = this.userService.getAccountCount(user.getStudentid());
        if (countnum > 0) {
            model.addAttribute("msg", (Object)"\u8be5\u5b66\u53f7\u5df2\u7ecf\u6ce8\u518c");
            model.addAttribute("user", (Object)user);
            return "register";
        }
        user.setStuid(0);
        user.setRegistertime(new Date());
        user.setMoney(0.0);
        user.setState(0);
        System.out.println(user.toString());
        final int result = this.userService.setUser(user);
        if (result <= 0) {
            model.addAttribute("msg", (Object)"\u6ce8\u518c\u5931\u8d25");
            model.addAttribute("user", (Object)user);
            return "register";
        }
        model.addAttribute("msg", (Object)"\u6ce8\u518c\u6210\u529f\uff0c\u8bf7\u767b\u5f55");
        return "login";
    }
    
    @RequestMapping({ "update.do" })
    public String update(final HttpServletRequest request, final User user, final Model model) {
        final int r = this.userService.updateUserInfo(user);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u66f4\u65b0\u6210\u529f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55");
            model.addAttribute("flag", (Object)"\u66f4\u65b0\u6210\u529f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55");
            return "userUpdate";
        }
        model.addAttribute("msg", (Object)"\u66f4\u65b0\u5931\u8d25");
        return "userUpdate";
    }
    
    @RequestMapping({ "updatepwd.do" })
    public String updatepwd(final HttpServletRequest request, final String oldpassword, final User user, final Model model) {
        final User olduser = (User)request.getSession(false).getAttribute("nowuser");
        System.out.println(olduser.getPassword());
        System.out.println(oldpassword);
        if (!olduser.getPassword().equals(oldpassword)) {
            model.addAttribute("msg", (Object)"\u539f\u5bc6\u7801\u9519\u8bef");
            return "userPassword";
        }
        final int r = this.userService.updateUserInfo(user);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u4fee\u6539\u6210\u529f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55");
            model.addAttribute("flag", (Object)"\u66f4\u65b0\u6210\u529f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55");
            return "userPassword";
        }
        model.addAttribute("msg", (Object)"\u4fee\u6539\u5931\u8d25");
        return "userPassword";
    }
}
