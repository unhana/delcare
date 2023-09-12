package com.declare.biz;

import com.declare.biz.dto.StorageOptions;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class FileService {
    private String savePath = "/Users/zhaoya/Pictures/save/";

    public static final String urlPrefix = "http://localhost:4396/image/";

    @SneakyThrows
    public String uploadFile(InputStream inputStream, StorageOptions options) {
        String realName = generateRandomFileKey(options);
        String fillName = savePath + realName;
        try (OutputStream outputStream = new FileOutputStream(fillName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return "image/" + realName;
    }

    private String generateRandomFileKey(StorageOptions options) {
        String fileName = options.getFileName();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return uuid + suffix;
    }


}
