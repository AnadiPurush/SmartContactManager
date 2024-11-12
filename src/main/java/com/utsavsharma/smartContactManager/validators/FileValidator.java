package com.utsavsharma.smartContactManager.validators;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File can not be empty");

            return false;

        }
        if (value.getSize() > MAX_FILE_SIZE) {

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size exceeded maximum allowed size of 2MB");
            return false;
        }
        // try {
        // var image = ImageIO.read(value.getInputStream());
        // if(image.getHeight()>1080 || image.getWidth()>1080){

        // context.disableDefaultConstraintViolation();
        // context.buildConstraintViolationWithTemplate("File size exceeded maximum
        // allowed size of 2MB");
        // return false;
        // }

        // } catch (IOException ex) {
        // }

        return true;
    }

}
