package com.declare.biz;

import com.declare.biz.dto.CaptchaImage;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component
public class CaptchaService {

    @Resource
    private DefaultKaptcha captchaProducerMath;


    public CaptchaImage generateCaptcha() {
        // 生成验证码字符串
        String captchaCode = captchaProducerMath.createText();

        // 创建验证码图片
        BufferedImage image = captchaProducerMath.createImage(captchaCode);

        // 将验证码图片转换为Base64编码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CaptchaImage(captchaCode, outputStream.toByteArray());
    }

    public boolean isCaptchaValid(String storedCaptcha, String userCaptcha) {
        // 校验用户输入的验证码是否与Session中保存的验证码一致
        return storedCaptcha != null && storedCaptcha.equalsIgnoreCase(userCaptcha);
    }

}
