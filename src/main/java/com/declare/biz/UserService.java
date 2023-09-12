package com.declare.biz;

import com.declare.dao.UserRepository;
import com.declare.dao.dataobject.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity registerUser(UserEntity user) {
        // 在保存用户之前，对密码进行加密
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setGmtCreate(LocalDateTime.now());
        user.setGmtModified(LocalDateTime.now());
        user.setIsDeleted(0); // 默认值为0，表示未删除
        user.setStatus(1); // 默认值为1，表示用户状态正常

        // 保存用户到数据库
        return userRepository.save(user);
    }

    public UserEntity loginUser(String username, String password) {
        // 根据用户名查找用户
        UserEntity user = userRepository.findByUsername(username);

        // 检查用户是否存在以及密码是否匹配
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // 更新最后登录时间
            user.setLastLoginTime(LocalDateTime.now());
            userRepository.save(user);
            return user;
        }

        return null; // 用户不存在或密码错误
    }
}