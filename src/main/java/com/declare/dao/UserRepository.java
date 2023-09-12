package com.declare.dao;

import com.declare.dao.dataobject.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 根据用户名查找用户
    UserEntity findByUsername(String username);

}
