package com.utsavsharma.smartContactManager.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom annotation for validating file uploads.
 * 
 * <p>
 * This annotation can be used to validate files uploaded via form fields or
 * method parameters
 * within the application. It ensures that the file meets custom-defined
 * validation rules,
 * such as acceptable file type, size, or other characteristics, by using the
 * {@link FileValidator} class.
 * </p>
 * 
 * <p>
 * The validation logic is encapsulated in the {@link FileValidator}, which
 * implements
 * the {@code ConstraintValidator<ValidFile, File>} interface. You can customize
 * the validation
 * logic by modifying the {@code FileValidator} class.
 * </p>
 * 
 * <p>
 * Usage example:
 * </p>
 * 
 * <pre>
 * {@code
 * public class FileUploadForm {
 * @ValidFile(message = "Please upload a valid image file.")
 * private MultipartFile file;
 * 
 * // getters and setters
 * }
 * }
 * 
 * @author : Utsav Sharma
 * 
 * @since:30-09-2024
 * 
 *                   Time: 15:46:27
 * 
 * 
 * @see FileValidator
 */
@Documented
@Target({
        ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface ValidFile {

    /**
     * Specifies the default validation message when the file does not meet the
     * requirements.
     * 
     * @return the error message
     */
    String message() default "Invalid file";

    /**
     * Allows specification of validation groups, which can be used to apply
     * different validation
     * logic under different contexts.
     * 
     * @return the group classes
     */
    Class<?>[] groups() default {};

    /**
     * Can be used to carry additional information about the validation, often used
     * in error reporting.
     * 
     * @return the payload classes
     */
    Class<? extends Payload>[] payload() default {};
}
