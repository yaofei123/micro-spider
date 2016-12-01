package com.yaofei.admin.index;

import com.yaofei.admin.manager.account.domain.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by fei.yao on 2016/10/19.
 */
@Controller
public class SecurityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

//    @RequiresRoles("ADMIN")
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String index(Model model) {
//        String userName = (String) SecurityUtils.getSubject().getPrincipal();
//        model.addAttribute("username", userName);
//        return "index";
//    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String index(Model model) {
//        String userName = (String) SecurityUtils.getSubject().getPrincipal();
//        model.addAttribute("userName", userName);
//        return "index";
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String defaultIndex(Model model) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("userName", userName);
        return "/index.html";
    }


//    @RequestMapping(value = "/{dir}/{page}.html")
//    public String page(Model model, @PathVariable("dir") String dir, @PathVariable("page") String page) {
//        return dir + "/" + page;
//    }


    @RequestMapping(value = "/{page}.html")
    public String page(@PathVariable("page") String page) {
        return page;
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginForm(Model model) {
//        model.addAttribute("user", new Account());
//        return "login";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "login.html";
        }

        String userName = account.getUserName();
        UsernamePasswordToken token = new UsernamePasswordToken(account.getUserName(), account.getPassword());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            LOGGER.info("对用户[" + userName + "]进行登录验证..验证开始");
            currentUser.login(token);
            LOGGER.info("对用户[" + userName + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            LOGGER.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            LOGGER.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            LOGGER.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            LOGGER.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            LOGGER.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            LOGGER.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return "/index.html";
        } else {
            token.clear();
            return "redirect:/login.html";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login.html";
    }

    @RequestMapping("/pages/403")
    public String unauthorizedRole() {
        LOGGER.info("------没有权限-------");
        return "pages/403.html";
    }
}