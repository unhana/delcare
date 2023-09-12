package com.declare.dao.dataobject;

import com.declare.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
@Where(clause = "is_deleted = false")
public class UserEntity extends BaseEntity {


    @Column(name = "username", nullable = false, unique = true, length = 128)
    private String username;

    @Column(name = "password", nullable = false, length = 1024)
    private String password;

    @Column(name = "phone", length = 32)
    private String phone;

    @Column(name = "email", length = 128)
    private String email;

    @Column(name = "nick", length = 128)
    private String nick;

    @Column(name = "gender", length = 32)
    private String gender;

    @Column(name = "last_login_time", nullable = false)
    private LocalDateTime lastLoginTime;

    @Column(name = "type", nullable = false, length = 32)
    private String type;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "feature", columnDefinition = "json")
    private String feature;
}