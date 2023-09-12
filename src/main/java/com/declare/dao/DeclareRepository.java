package com.declare.dao;

import com.declare.dao.dataobject.DeclareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclareRepository extends JpaRepository<DeclareEntity, Long>, JpaSpecificationExecutor<DeclareEntity> {


}
