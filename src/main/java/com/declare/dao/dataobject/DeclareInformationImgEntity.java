package com.declare.dao.dataobject;

import com.declare.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@Entity
@Table(name = "declare_information_img")
@Where(clause = "is_deleted = false")
public class DeclareInformationImgEntity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "declare_id", nullable = false)
    private Long declareId;

    @Column(name = "type", nullable = false, length = 32)
    private String type;

    @Column(name = "path", nullable = false, length = 128)
    private String path;
}
