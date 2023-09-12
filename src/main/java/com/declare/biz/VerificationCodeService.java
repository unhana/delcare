package com.declare.biz;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VerificationCodeService {

    /**
     * 验证码过期时间（分钟）
     */
    private static final long EXPIRATION_DURATION_MINUTES = 5;

    private final Map<String, VerificationCode> verificationCodeMap = new ConcurrentHashMap<>();

    public String generateCode(String phoneNumber) {
        // 创建一个随机数生成器
        Random random = new Random();
        // 生成一个四位数字验证码
        int verificationCode = 1000 + random.nextInt(9000);
        // 将验证码转换为字符串
        String verificationCodeStr = String.valueOf(verificationCode);

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(EXPIRATION_DURATION_MINUTES);
        VerificationCode code = new VerificationCode(verificationCodeStr, expirationTime);
        verificationCodeMap.put(phoneNumber, code);
        return verificationCodeStr;
    }

    public boolean isValid(String phoneNumber, String verificationCode) {
        VerificationCode code = verificationCodeMap.remove(phoneNumber);
        return code != null && code.code().equals(verificationCode) && !code.isExpired();
    }

    private record VerificationCode(String code, LocalDateTime expirationTime) {
        public boolean isExpired() {
                return LocalDateTime.now().isAfter(expirationTime);
            }
        }
}
