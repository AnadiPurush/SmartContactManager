package com.utsavsharma.smartContactManager.form;

import org.springframework.web.multipart.MultipartFile;

import com.utsavsharma.smartContactManager.validators.ValidFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @file ContactForm.java
 *       Author: Utsav Sharma
 *       Date: 25-09-2024
 *       Time: 16:46:02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {
    // @NotBlank(message = "Name cannot be blank")
    private String name;
    // @Email(message = "Email is not valid")
    private String email;
    // @NotBlank(message = "Phone number cannot be blank")
    // @Pattern(regexp = "^{[0-9]{10}}$", message = "Phone number is not valid")
    private String phoneNumber;
    private String address;
    private String description;
    private String website;
    private String linkedInLink;
    @ValidFile
    private MultipartFile contactImage;
    private boolean favorite;
}
