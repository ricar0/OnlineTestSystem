package com.kaka.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author SANZONG
 */
@Component
@Data
public class TencentCosUtil {
    @Value("${TencentCos.secretId}")
    private String secretId;
    @Value("${TencentCos.secretKey}")
    private String secretKey;
    @Value("${TencentCos.region}")
    private String region;
    @Value("${TencentCos.bucketName}")
    private String bucketName;
    private COSClient cosClient;

    public void build() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(getRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        this.cosClient = new COSClient(cred, clientConfig);
    }

    public void shutdown() {
        cosClient.shutdown();
    }

    public String upload(File file, String key) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(getContentType(file.getName()));
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, new FileInputStream(file), objectMetadata);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return putObjectResult.getETag();
        } catch (CosClientException e) {
            throw new IOException(e);
        }
    }

    public String getContentType(String filename) {
        String filenameExtension = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (filenameExtension.equalsIgnoreCase(".pdf")) {
            return "application/pdf";
        }
        return "image/jpg";
    }
}