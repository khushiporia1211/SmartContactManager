package com.scm.services.impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import com.scm.services.ImageService;
@Service 
public class ImageServiceimpl implements ImageService {
    private final Cloudinary cloudinary;

    ImageServiceimpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage) {

        String filename = UUID.randomUUID().toString();
        try{
        byte[] data = new byte[contactImage.getInputStream().available()];
        contactImage.getInputStream().read(data);
        cloudinary.uploader().upload(data,ObjectUtils.asMap(
            "public_id",contactImage.getOriginalFilename()) );
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
        return this.getUrlFromPublicId(filename);
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
            .url()
            .transformation(
                new Transformation<>()
                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                .crop(AppConstants.CONTACT_IMAGE_CROP)
            )
            .generate(publicId);
    }
    

}
