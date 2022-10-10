package com.zyaapar.imageapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping(value = {"/acceptImage"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ImageResponse> acceptImage(@RequestParam("image")MultipartFile file ){
        long id;
        try {
           id= imageService.addImage(file);
        } catch (IOException e) {

            e.printStackTrace();
            return new ResponseEntity<>( new ImageResponse(null,"Image not uploaded"), HttpStatus.BAD_REQUEST );

        }
        return new ResponseEntity<>(new ImageResponse(file.getOriginalFilename(), "Image uploaded: "+id),HttpStatus.OK);
    }

    @GetMapping(value = {"/downloadImage/{imageId}"},produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String imageId){

        ImageEntity imageEntity= imageService.fetchImage(Long.parseLong(imageId));

        ByteArrayInputStream bis= new ByteArrayInputStream( imageEntity.getImgBytes() );

        InputStreamResource in= new InputStreamResource( bis );

        return ResponseEntity.ok().body( in );

    }

}
