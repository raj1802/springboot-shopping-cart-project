package com.dreamcart.dreamshop.service.image;

import com.dreamcart.dreamshop.exception.ResourceNotFoundException;
import com.dreamcart.dreamshop.model.Image;
import com.dreamcart.dreamshop.repository.ImageRepository;
import com.dreamcart.dreamshop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;


@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

private final ImageRepository imageRepository;
private final IProductService productService;



    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("image not found with id:"+id));
    }

    @Override
    public void deleteImageById(Long id) {
imageRepository.findById(id).ifPresentOrElse(imageRepository::delete,()->{
    throw new ResourceNotFoundException("image not found with id:"+id);
});
    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long productId) {

        Image image = getImageById(productId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e ) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
