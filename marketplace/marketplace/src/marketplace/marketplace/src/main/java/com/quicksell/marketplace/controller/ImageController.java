package com.quicksell.marketplace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private static final String UPLOAD_DIR =
            System.getProperty("user.dir")
                    + "/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            File uploadDir = new File(UPLOAD_DIR);

            if (!uploadDir.exists()) {

                uploadDir.mkdirs();
            }

            String fileName =
                    UUID.randomUUID()
                            + "_"
                            + file.getOriginalFilename();

            String filePath = UPLOAD_DIR + fileName;

            file.transferTo(new File(filePath));

            return ResponseEntity.ok(
                    "Image Uploaded Successfully: "
                            + fileName
            );

        } catch (IOException e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .body("Upload Failed");
        }
    }
}