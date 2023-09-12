package com.declare.dao;

import com.declare.dao.dataobject.DeclareInformationImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclareInformationImgRepository extends JpaRepository<DeclareInformationImgEntity, Long> {
}
