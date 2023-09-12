package com.declare.biz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageOptions {

    /**
     * 文件名
     */
    String fileName;

    /**
     * 文件类型
     */
    String contentType;
}
