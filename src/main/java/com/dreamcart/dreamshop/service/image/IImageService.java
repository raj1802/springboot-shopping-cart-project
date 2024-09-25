package com.dreamcart.dreamshop.service.image;


import com.dreamcart.dreamshop.dto.ImageDto;
import com.dreamcart.dreamshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file, Long productId);


}
