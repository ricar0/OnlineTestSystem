package com.kaka.service.Impl;

import com.kaka.service.IOssService;
import com.kaka.utils.MultipartFileToFileUtils;
import com.kaka.utils.TencentCosUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author SANZONG
 */
@Service
@Slf4j
public class OssServiceImpl implements IOssService {
    private final TencentCosUtil tencentCosUtil;
    private final Date expire = new Date(System.currentTimeMillis() + 3600L * 10000 * 24 * 365);

    public OssServiceImpl(TencentCosUtil tencentCosUtil) {
        this.tencentCosUtil = tencentCosUtil;
        this.tencentCosUtil.build();
    }


    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        // 指定要上传的文件
        File file = MultipartFileToFileUtils.multipartFileToFile(multipartFile);
        String fileName = UUID.randomUUID() + file.getName();
        log.info("正在上传文件 {}", fileName);
        try {
            tencentCosUtil.upload(file, fileName);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw e;
        }

        URL url = tencentCosUtil.getCosClient().generatePresignedUrl(tencentCosUtil.getBucketName(),fileName,expire);
        boolean delete = false;
        try {
            // 尝试关闭与文件相关的所有流和资源，以确保文件可以被删除
            IOUtils.closeQuietly(new FileInputStream(file));
            IOUtils.closeQuietly(new FileOutputStream(file));
            // 检查文件是否上传成功
            delete = file.delete();
        } catch (IOException e) {
            log.error("文件删除失败", e);
        }
        if (!delete) {
            // 如果文件未能成功删除，将文件重命名为另一个名称以避免冲突
            String newFileName = UUID.randomUUID() + "-" + file.getName();
            boolean renamed = file.renameTo(new File(file.getParentFile(), newFileName));
            if (renamed) {
                fileName = newFileName;
            }
        }
        log.info("文件上传成功 地址 {}", url);
        return String.valueOf(url);
    }



    @Override
    public String download(String fileName) {
        URL url = tencentCosUtil.getCosClient().generatePresignedUrl(tencentCosUtil.getBucketName(), fileName, expire);
        return String.valueOf(url);
    }
}

