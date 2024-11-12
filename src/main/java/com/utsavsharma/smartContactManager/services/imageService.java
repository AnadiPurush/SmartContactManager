package com.utsavsharma.smartContactManager.services;

import org.springframework.web.multipart.MultipartFile;

public interface imageService {
    public String UploadImage(MultipartFile file);

    public String getUrlFromPublicId(String publicId);
}
