package com.enigma.loan.service.impl;

import com.enigma.loan.entity.ProfilePicture;
import com.enigma.loan.repository.ProfilePictureRepository;
import com.enigma.loan.service.ProfilePictureService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {
    private final Path directoryPath;
    private final ProfilePictureRepository profilePictureRepository;

    @Autowired
    public ProfilePictureServiceImpl(@Value("${app.upload.directory}") String directory, ProfilePictureRepository profilePictureRepository) {
        this.directoryPath = Paths.get(directory);
        this.profilePictureRepository = profilePictureRepository;
    }

    @PostConstruct
    public void initDirectory() {
        try {
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not initialize storage directory", e);
        }
    }

    @Override
    public ProfilePicture create(@NotNull MultipartFile multipartFile) {
        try{
            if (!List.of("image/jpeg", "image/png", "image/jpg", "image/svg+xml").contains(multipartFile.getContentType()))
                throw new ConstraintViolationException("Eror invalid content type",null);
            String uniqueFilename = System.currentTimeMillis()+"_"+multipartFile.getOriginalFilename();
            Path filePath = directoryPath.resolve(uniqueFilename);
            Files.copy(multipartFile.getInputStream(), filePath);

            ProfilePicture profilePicture = ProfilePicture.builder()
                    .name(uniqueFilename)
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .url(filePath.toString())
                    .build();
            profilePictureRepository.saveAndFlush(profilePicture);

            return profilePicture;
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Resource getById(String id) {
        try {
            ProfilePicture profilePicture= profilePictureRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile Picture Not Found"));
            Path filePath = Paths.get(profilePicture.getUrl());
            if(!Files.exists(filePath))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile Picture Not Found");
            return new UrlResource(filePath.toUri());
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Override
    public void deletedById(String id) {
        try{
          ProfilePicture profilePicture = profilePictureRepository.findById(id)
                  .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile picture not found"));
          Path filePath = Paths.get(profilePicture.getUrl());
          if (!Files.exists(filePath))
              throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile picture not found");
          Files.delete(filePath);
          profilePictureRepository.delete(profilePicture);
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
