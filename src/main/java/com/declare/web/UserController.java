package com.declare.web;

import com.declare.biz.CaptchaService;
import com.declare.biz.VerificationCodeService;
import com.declare.biz.dto.CaptchaImage;
import com.declare.common.model.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private CaptchaService captchaService;

    @Resource
    private VerificationCodeService verificationCodeService;

    @GetMapping("/captcha")
    @SneakyThrows
    public void getCaptchaImage(HttpServletRequest request, HttpServletResponse httpServletResponse) {

        CaptchaImage captchaImage = captchaService.generateCaptcha();
        // 将验证码绑定到Session
        HttpSession session = request.getSession();
        session.setAttribute("captchaCode", captchaImage.captchaCode());

        //设置响应头
        httpServletResponse.setHeader("Cache-Control", "no-store");
        //设置响应头
        httpServletResponse.setHeader("randomstr",captchaImage.captchaCode());
        //设置响应头
        httpServletResponse.setHeader("Pragma", "no-cache");
        //在代理服务器端防止缓冲
        httpServletResponse.setDateHeader("Expires", 0);
        //设置响应内容类型
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaImage.base64Image());
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("captchaCode") String captchaCode,
                        HttpServletRequest request) {
        // 获取Session中保存的验证码
        HttpSession session = request.getSession();
        String storedCaptchaCode = (String) session.getAttribute("captchaCode");

        // 校验验证码是否正确
        if (captchaService.isCaptchaValid(storedCaptchaCode, captchaCode)) {
            // 验证码正确，执行登录逻辑
            return "redirect:/dashboard";
        } else {
            // 验证码不正确，返回登录页面并显示错误消息
            return "login";
        }
    }

    @PostMapping("sendVerificationCode")
    public Result<Void> sendVerificationCode(@RequestParam("phoneNumber") String phoneNumber) {
        String code = verificationCodeService.generateCode(phoneNumber);
        return Result.success();
    }
}
