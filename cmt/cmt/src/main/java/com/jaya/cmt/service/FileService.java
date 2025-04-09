package com.jaya.cmt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "c:/uploads/";

    public String uploadFile(MultipartFile file) throws IOException {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        return filePath;
    }
}
