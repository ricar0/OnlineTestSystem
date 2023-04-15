package com.kaka.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

/**
 * @Description:
 * @date 2019/9/23 10:21
 */
public class MultipartFileToFileUtils {

    /**
     * MultipartFile 转 File
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file ) throws IOException {
        File toFile = null;
        if(null == file ||file.getSize()<=0){
            file = null;
        }else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(Optional.ofNullable(file.getOriginalFilename()).orElse(String.valueOf(UUID.randomUUID())));
            toFile = inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }


    private static File inputStreamToFile(InputStream ins, File file) {
        try(OutputStream os = new FileOutputStream(file)) {
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}