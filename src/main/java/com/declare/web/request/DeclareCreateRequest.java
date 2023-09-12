package com.declare.web.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DeclareCreateRequest {

    @NotEmpty(message = "buildCompanyName不能数为空")
    private String buildCompanyName;


    @NotNull
    private String exhibition;


    private String exhibitCompanyName;


    private String exhibitContact;


    private String exhibitContactPhone;


    private String galleryName;


    private String boothNumber;


    private String boothArea;


    private String boothStructure;


    private String boothSize;


    private BigDecimal boothHighestPoint;


    private BigDecimal boothWidestLength;


    private String boothMaterialDesc;


    private String constructionSiteManager;


    private String constructionSiteManagerPhone;

    /**
     * 文件列表
     */
    private List<FileInfoRequest> fileInfoList;

    private String deposit;

    private String managementFee;

    private String electricFee;

    private String electricFeeDetail;

    private String tempElectricFee;

    private String tempElectricFeeDetail;

    private String gasFee;

    private String gasFeeDetail;

    private String waterFee;

    private String networkFee;

    private String networkFeeDetail;

    private Long userId;
}
