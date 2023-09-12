package com.declare.dao.dataobject;


import com.declare.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`declare`")
@Where(clause = "is_deleted = false")
public class DeclareEntity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "`status`", nullable = false)
    private Integer status;

    @Column(name = "build_company_name", nullable = false)
    private String buildCompanyName;

    @Column(name = "exhibition", nullable = false)
    private String exhibition;

    @Column(name = "exhibit_company_name", nullable = false)
    private String exhibitCompanyName;

    @Column(name = "exhibit_contact", nullable = false, length = 32)
    private String exhibitContact;

    @Column(name = "exhibit_contact_phone", nullable = false, length = 32)
    private String exhibitContactPhone;

    @Column(name = "gallery_name", nullable = false)
    private String galleryName;

    @Column(name = "booth_number", nullable = false, length = 20)
    private String boothNumber;

    @Column(name = "booth_area", nullable = false, length = 20)
    private String boothArea;

    @Column(name = "booth_structure", nullable = false, length = 10)
    private String boothStructure;

    @Column(name = "booth_size", nullable = false, length = 20)
    private String boothSize;

    @Column(name = "booth_highest_point", nullable = false, precision = 10, scale = 2)
    private BigDecimal boothHighestPoint;

    @Column(name = "booth_widest_length", nullable = false, precision = 10, scale = 2)
    private BigDecimal boothWidestLength;

    @Column(name = "booth_material_desc", nullable = false, columnDefinition = "TEXT")
    private String boothMaterialDesc;

    @Column(name = "construction_site_manager", nullable = false)
    private String constructionSiteManager;

    @Column(name = "construction_site_manager_phone", nullable = false, length = 15)
    private String constructionSiteManagerPhone;

    @Column(name = "feature", columnDefinition = "json")
    private String feature;

}
