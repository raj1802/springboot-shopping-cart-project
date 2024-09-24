package com.dreamcart.dreamshop.service.image;


import com.dreamcart.dreamshop.model.Image;
import org.springframework.web.multipart.MultipartFile;
public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    Image saveImage(MultipartFile file, Long productId);
    void updateImage(MultipartFile file, Long productId);


}
