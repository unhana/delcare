package com.declare.dao;

import com.declare.dao.dataobject.DeclareExpenseItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclareExpenseItemRepository extends JpaRepository<DeclareExpenseItemEntity, Long> {
}
