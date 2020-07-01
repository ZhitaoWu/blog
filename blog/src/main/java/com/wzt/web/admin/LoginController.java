package com.wzt.web.admin;

import com.wzt.po.User;
import com.wzt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Create By coder_tao on 2020/3/29 0029
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {

        return "admin/login";
    }

    @PostMapping("/login")  // 登录
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if(user != null) {
            // 不让密码传到前台
            user.setPassword(null);

            session.setAttribute("user", user);
            return "admin/index";
        } else {
            // 重定向
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";

            // 不能使用这个简单的 return 到登录页面，重新登录会出现异常
            // return "admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
