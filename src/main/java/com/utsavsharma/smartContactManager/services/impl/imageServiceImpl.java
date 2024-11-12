package com.utsavsharma.smartContactManager.services.impl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.utsavsharma.smartContactManager.services.imageService;

@Service
public class imageServiceImpl implements imageService {
    private final Cloudinary cloudinary;
    String fileName = UUID.randomUUID().toString();

    public imageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String UploadImage(MultipartFile file) {
        try {
            // byte[] imagedata = new byte[file.getInputStream().available()];
            // file.getInputStream().read(imagedata);
            // cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", fileName));
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName));
            return result.get("url").toString();
        } catch (IOException e) {
            System.out.println(e.getClass().getName());

            return "";
        }

    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
                .url()
                .transformation(
                        new Transformation<>()
                                .width(500)
                                .height(500)
                                .crop("fill"))
                .generate(publicId);
    }
}