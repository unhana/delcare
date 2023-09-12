package com.declare.web;


import com.declare.biz.FileService;
import com.declare.biz.dto.StorageOptions;
import com.declare.common.BizError;
import com.declare.common.model.Result;
import com.declare.common.utils.AssertUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Resource
    private FileService fileService;


    /**
     * 上传图片http接口
     */
    @PostMapping("/uploadImage")
    @SneakyThrows
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // 校验文件是否为空
        AssertUtils.notNull(file, BizError.IMAGE_NOT_SELECTED);
        AssertUtils.assertTrue(!file.isEmpty(), BizError.IMAGE_NOT_SELECTED);
        AssertUtils.assertTrue(file.getSize() < 1024 * 1024 * 10, BizError.IMAGE_SIZE_LIMIT);

        // 处理文件存储逻辑，例如保存到本地磁盘或云存储中
        String image = fileService.uploadFile(
                file.getInputStream(),
                StorageOptions
                        .builder()
                        .contentType(file.getContentType())
                        .fileName(file.getOriginalFilename())
                        .build()
        );
        return Result.success(image);
    }
}
