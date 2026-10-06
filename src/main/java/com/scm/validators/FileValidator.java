package com.scm.validators;

// import java.io.IOException;
// import java.awt.image.BufferedImage;

// import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile>{

    private static final long MAX_FILE_SIZE = 1023*1024*2;



    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if(file==null || file.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("file can not be empty").addConstraintViolation();
            return false;
        }

        //file size
        if(file.getSize()>MAX_FILE_SIZE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be 2MB").addConstraintViolation();
            return false;
        }
        // //file resolution
        // try{
        //     BufferedImage bufferedImage=ImageIO.read(file.getInputStream());

        //     if(bufferedImage.getH)
        // }catch(IOException e){
        //     e.printStackTrace();
        // }
   return  true;
    }


}
