package com.yaofei.admin.index;

import com.yaofei.framework.util.CurrentIpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fei.yao on 2016/10/19.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * admin管理后台登录接口
     * 1、参数验证
     * 2、用户名入库查用户数据
     * 3、验证密码是否正确
     * 4、用户信息入session
     * 5、登录成功或失败跳转
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<String, Object>();
        String loginName = "";
        String loginPassword = "";
        try {
            // 1.

            // 2.

            String ip = CurrentIpUtil.getIpAddr(request);
            String md5Password = DigestUtils.md5DigestAsHex(loginPassword.getBytes());
        } catch (Exception e) {

        }


        return model;

    }
}
