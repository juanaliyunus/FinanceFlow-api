package com.enigma.loan.service;

import com.enigma.loan.entity.ProfilePicture;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePictureService {
    ProfilePicture create (MultipartFile multipartFile);
    Resource getById(String id);
    void deletedById(String id);

}
