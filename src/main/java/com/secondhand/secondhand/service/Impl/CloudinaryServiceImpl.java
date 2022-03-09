package com.secondhand.secondhand.service.Impl;

import com.cloudinary.Cloudinary;
import com.secondhand.secondhand.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";
    private  final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public CloudinaryImage upload(MultipartFile file) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE,file.getOriginalFilename());
        file.transferTo(tempFile);

      try {
          Map<String,String> upload = cloudinary
                  .uploader()
                  .upload(tempFile, Map.of());

          String url = upload.getOrDefault(URL, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsairhVA5q080vP7Niigy3bMCnGZNdzNCN4w&usqp=CAU");
          String publicId = upload.getOrDefault(PUBLIC_ID,"");
          return new CloudinaryImage()
                  .setPublicId(publicId)
                  .setUrl(url);
      } finally {
          tempFile.delete();
      }
    }

    @Override
    public boolean delete(String publicId) {

        try {
            cloudinary
                    .uploader()
                    .destroy(publicId,Map.of());
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
