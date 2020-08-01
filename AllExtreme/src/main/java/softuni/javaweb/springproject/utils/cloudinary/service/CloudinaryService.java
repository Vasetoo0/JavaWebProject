package softuni.javaweb.springproject.utils.cloudinary.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface CloudinaryService {

    String uploadFile(MultipartFile file);

    File convertMultiPartToFile(MultipartFile file) throws IOException;

}
