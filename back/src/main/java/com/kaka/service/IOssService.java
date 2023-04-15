package com.kaka.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author SANZONG
 */
public interface IOssService {
    String upload(MultipartFile multipartFile) throws IOException;

    String download(String fileName);
}
