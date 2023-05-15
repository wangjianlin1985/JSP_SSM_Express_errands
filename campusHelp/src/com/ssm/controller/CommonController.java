// 
// 
// 

package com.ssm.controller;

import com.ssm.po.User;
import java.io.IOException;
import java.io.PrintWriter;
import com.ssm.po.School;
import java.util.List;
import com.ssm.util.JsonUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import com.ssm.service.UserService;
import javax.annotation.Resource;
import com.ssm.service.SchoolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;

@Controller
@SessionAttributes({ "nowuser", "nowadmin" })
@RequestMapping({ "common/" })
public class CommonController
{
    @Resource(name = "schoolService")
    public SchoolService schoolService;
    @Resource(name = "userService")
    public UserService userService;
    
    @RequestMapping({ "logout.do" })
    public String logout(final HttpServletRequest request, final Model model) {
        model.addAttribute("msg", (Object)"\u5df2\u9000\u51fa");
        request.getSession(false).removeAttribute("nowuser");
        request.getSession(false).removeAttribute("nowadmin");
        return "login";
    }
    
    @RequestMapping({ "getallschools.do" })
    public void getallschools(final HttpServletResponse response) throws IOException {
        System.out.println("000000000000000000000000000000000");
        final List<School> list = this.schoolService.getAllSchoolsNoState();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        final String list_String = JsonUtil.list2json(list);
        final PrintWriter out = response.getWriter();
        out.println(list_String);
        out.flush();
        out.close();
    }
    
    @RequestMapping({ "getuser.do" })
    public String getuser(final String stuidstr, final HttpServletRequest request, final Model model) {
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
        return "userInfo";
    }
}
