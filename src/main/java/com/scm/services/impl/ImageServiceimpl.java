package com.scm.services.impl;

import java.io.IOException;
import java.util.UUID;

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
    public String uploadImage(MultipartFile contactImage,String publicId) {
        if (contactImage == null || contactImage.isEmpty()) {
            throw new IllegalArgumentException("An image file is required.");
        }

       
        try {
            cloudinary.uploader().upload(
                contactImage.getBytes(),
                ObjectUtils.asMap("public_id", publicId)
            );
        } catch (IOException e) {
            throw new IllegalStateException("Could not read the uploaded image.", e);
        }
        return this.getUrlFromPublicId(publicId);
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
