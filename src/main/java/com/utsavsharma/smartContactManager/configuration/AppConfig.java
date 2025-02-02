package com.utsavsharma.smartContactManager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration

public class AppConfig {

    @Value("${default.profile.picture.url}")
    private String defaultProfilePic;

    @Value("${cloudinary.cloud.name}")
    private String cloudName;

    @Value("${cloudinary.api.key}")
    private String apiKey;

    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    public String getDefaultProfilePictureUrl() {
        return defaultProfilePic;
    }

    @Bean
    public Cloudinary getCloudinary() {

        return new Cloudinary(
                ObjectUtils.asMap(

                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret)

        );
    }

}
