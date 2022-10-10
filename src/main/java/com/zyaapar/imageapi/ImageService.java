package com.zyaapar.imageapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    ImgRepo imgRepo;

    @Autowired
    ImageService(ImgRepo imgRepo){
        this.imgRepo=imgRepo;
    }

    public long addImage(MultipartFile file) throws IOException {

        ImageEntity imageEntity= new ImageEntity( file.getOriginalFilename(), file.getBytes() );

        imageEntity = imgRepo.save(imageEntity);

        return imageEntity.getId();

    }

    public ImageEntity fetchImage(long id){

        return imgRepo.findById(id).get();
    }

}
