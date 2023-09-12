package com.declare.common.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "gmt_create", nullable = false, updatable = false)
    private LocalDateTime gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    private LocalDateTime gmtModified;

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted = 0; // 默认为未删除

    @PrePersist
    protected void onCreate() {
        gmtCreate = LocalDateTime.now();
        gmtModified = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        gmtModified = LocalDateTime.now();
    }
}
