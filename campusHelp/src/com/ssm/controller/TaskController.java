// 
// 
// 

package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Date;
import com.ssm.po.User;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import com.ssm.po.Task;
import com.ssm.service.UserService;
import javax.annotation.Resource;
import com.ssm.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "task/" })
public class TaskController
{
    @Resource(name = "taskService")
    public TaskService taskService;
    @Resource(name = "userService")
    public UserService userService;
    
    @RequestMapping({ "newtask.do" })
    public String newtask(final Task task, final HttpServletRequest request, final Model model) {
        User user = null;
        try {
            user = (User)request.getSession(false).getAttribute("nowuser");
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return "login";
        }
        try {
            if (user == null || user.getStuid() == 0) {
                model.addAttribute("msg", (Object)"\u53d1\u5e03\u5931\u8d25-\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
                return "userNewtask";
            }
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u53d1\u5e03\u5931\u8d25-\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return "userNewtask";
        }
        if (user.getState() > 0) {
            model.addAttribute("msg", (Object)"\u53d1\u5e03\u5931\u8d25-\u7528\u6237\u72b6\u6001\u53d7\u9650");
            return "userNewtask";
        }
        if (user.getMoney() < task.getReward()) {
            model.addAttribute("msg", (Object)"\u53d1\u5e03\u5931\u8d25-\u4f59\u989d\u4e0d\u8db3-\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\u6dfb\u52a0\u4f59\u989d");
            return "userNewtask";
        }
        final Task task2 = new Task(0, new StringBuilder().append(user.getStuid()).toString(), user.getName(), user.getSchoolid(), 0, task.getReward(), new Date(), new Date(), task.getTaskname(), task.getTaskcontext(), 0);
        user.setMoney(user.getMoney() - task2.getReward());
        final int ruser = this.userService.updateUserInfo(user);
        if (ruser <= 0) {
            model.addAttribute("msg", (Object)"\u53d1\u5e03\u5931\u8d25");
            return "userNewtask";
        }
        final int r = this.taskService.setNewTask(task2);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u53d1\u5e03\u6210\u529f");
            return this.getusertask(request, model);
        }
        model.addAttribute("msg", (Object)"\u4f59\u989d\u5df2\u6263\u9664-\u53d1\u5e03\u5931\u8d25");
        return "userNewtask";
    }
    
    @RequestMapping({ "getusertask.do" })
    public String getusertask(final HttpServletRequest request, final Model model) {
        User user = null;
        try {
            user = (User)request.getSession(false).getAttribute("nowuser");
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.findtask("", "-1", 1, model);
        }
        int uid = 0;
        try {
            uid = user.getStuid();
            if (user == null || uid == 0) {
                model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
                return this.findtask("", "-1", 1, model);
            }
        }
        catch (Exception e2) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.findtask("", "-1", 1, model);
        }
        final List<Task> list = this.taskService.getUserTask(uid);
        model.addAttribute("list", (Object)list);
        return "userPtask";
    }
    
    @RequestMapping({ "getuseratask.do" })
    public String getuseratask(final HttpServletRequest request, final Model model) {
        User user = null;
        try {
            user = (User)request.getSession(false).getAttribute("nowuser");
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.findtask("", "-1", 1, model);
        }
        int uid = 0;
        try {
            uid = user.getStuid();
            if (user == null || uid == 0) {
                model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
                return this.findtask("", "-1", 1, model);
            }
        }
        catch (Exception e2) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.findtask("", "-1", 1, model);
        }
        final List<Task> list = this.taskService.getUserATask(uid);
        model.addAttribute("list", (Object)list);
        return "userAtask";
    }
    
    @RequestMapping({ "taskok.do" })
    public String taskok(final String tidstr, final HttpServletRequest request, final Model model) {
        int tid = 0;
        try {
            tid = Integer.parseInt(tidstr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            System.out.println("11111111111");
            return this.getusertask(request, model);
        }
        if (tid == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            System.out.println("22222222222222");
            return this.getusertask(request, model);
        }
        User user = null;
        try {
            user = (User)request.getSession(false).getAttribute("nowuser");
        }
        catch (Exception e2) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return "login";
        }
        int uid = 0;
        try {
            uid = user.getStuid();
            if (user == null || uid == 0) {
                model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
                return this.getusertask(request, model);
            }
        }
        catch (Exception e3) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.getusertask(request, model);
        }
        final Task theTask = this.taskService.getTask(tid);
        if (!new StringBuilder(String.valueOf(uid)).toString().equals(theTask.getPublishUserId())) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusertask(request, model);
        }
        theTask.setState(3);
        final User auser = this.userService.getByUid(theTask.getAcceptUserId());
        auser.setMoney(auser.getMoney() + theTask.getReward());
        final int ruser = this.userService.updateUserInfo(auser);
        if (ruser > 0) {
            final int r = this.taskService.updateTask(theTask);
            if (r > 0) {
                model.addAttribute("msg", (Object)"\u6210\u529f");
            }
            else {
                model.addAttribute("msg", (Object)"\u5931\u8d25");
            }
        }
        else {
            model.addAttribute("msg", (Object)"\u5931\u8d25\uff0c\u5956\u52b1\u65e0\u6cd5\u5230\u8d26-\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458");
        }
        final List<Task> list = this.taskService.getUserTask(uid);
        model.addAttribute("list", (Object)list);
        return "userPtask";
    }
    
    @RequestMapping({ "taskclose.do" })
    public String taskclose(final String tidstr, final HttpServletRequest request, final Model model) {
        int tid = 0;
        try {
            tid = Integer.parseInt(tidstr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusertask(request, model);
        }
        if (tid == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusertask(request, model);
        }
        User user = null;
        try {
            user = (User)request.getSession(false).getAttribute("nowuser");
        }
        catch (Exception e2) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return "login";
        }
        int uid = 0;
        try {
            uid = user.getStuid();
            if (user == null || uid == 0) {
                model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
                return this.getusertask(request, model);
            }
        }
        catch (Exception e3) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.getusertask(request, model);
        }
        final Task theTask = this.taskService.getTask(tid);
        if (!new StringBuilder(String.valueOf(uid)).toString().equals(theTask.getPublishUserId())) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.getusertask(request, model);
        }
        theTask.setState(1);
        final int r = this.taskService.updateTask(theTask);
        if (r > 0) {
            user.setMoney(user.getMoney() + theTask.getReward());
            final int ruser = this.userService.updateUserInfo(user);
            if (ruser > 0) {
                model.addAttribute("msg", (Object)"\u6210\u529f");
            }
            else {
                model.addAttribute("msg", (Object)"\u5df2\u5173\u95ed-\u5956\u52b1\u672a\u8fd4\u56de\uff0c\u8bf7\u4e0e\u7ba1\u7406\u5458\u8054\u7cfb");
            }
        }
        else {
            model.addAttribute("msg", (Object)"\u5931\u8d25");
        }
        final List<Task> list = this.taskService.getUserTask(uid);
        model.addAttribute("list", (Object)list);
        return "userPtask";
    }
    
    @RequestMapping({ "taskaccept.do" })
    public String taskaccept(final String tidstr, final HttpServletRequest request, final Model model) {
        int tid = 0;
        try {
            tid = Integer.parseInt(tidstr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.gettaskinfo(tidstr, request, model);
        }
        if (tid == 0) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return this.gettaskinfo(tidstr, request, model);
        }
        User user = null;
        try {
            user = (User)request.getSession(false).getAttribute("nowuser");
        }
        catch (Exception e2) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.gettaskinfo(tidstr, request, model);
        }
        int uid = 0;
        try {
            uid = user.getStuid();
            if (user == null || uid == 0) {
                model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
                return this.gettaskinfo(tidstr, request, model);
            }
        }
        catch (Exception e3) {
            model.addAttribute("msg", (Object)"\u8bf7\u68c0\u67e5\u767b\u5f55\u72b6\u51b5");
            return this.gettaskinfo(tidstr, request, model);
        }
        final Task theTask = this.taskService.getTask(tid);
        if (new StringBuilder(String.valueOf(uid)).toString().equals(theTask.getPublishUserId())) {
            model.addAttribute("msg", (Object)"\u4e0d\u80fd\u63a5\u53d7\u81ea\u5df1\u7684\u4efb\u52a1\u554a");
            return this.gettaskinfo(tidstr, request, model);
        }
        theTask.setAcceptUserId(uid);
        theTask.setState(2);
        final int r = this.taskService.updateTask(theTask);
        if (r > 0) {
            model.addAttribute("msg", (Object)"\u6210\u529f");
        }
        else {
            model.addAttribute("msg", (Object)"\u5931\u8d25");
        }
        return this.gettaskinfo(tidstr, request, model);
    }
    
    @RequestMapping({ "gettaskinfo.do" })
    public String gettaskinfo(final String tidstr, final HttpServletRequest request, final Model model) {
        int tid = 0;
        try {
            tid = Integer.parseInt(tidstr);
        }
        catch (Exception e) {
            model.addAttribute("msg", (Object)"\u51fa\u73b0\u9519\u8bef");
            return "taskInfo";
        }
        final Task theTask = this.taskService.getTask(tid);
        model.addAttribute("theTask", (Object)theTask);
        return "taskInfo";
    }
    
    @RequestMapping({ "list.do" })
    public String findtask(String words, @RequestParam(required = true, defaultValue = "-1") final String schoolidstr, @RequestParam(required = true, defaultValue = "1") final Integer page, final Model model) {
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
        PageHelper.startPage((int)page, 6);
        final List<Task> list = this.taskService.getTaskByKeysNoState(words, schoolid);
        final PageInfo<Task> pageInfo = (PageInfo<Task>)new PageInfo((List)list);
        model.addAttribute("p", (Object)pageInfo);
        return "index";
    }
}
