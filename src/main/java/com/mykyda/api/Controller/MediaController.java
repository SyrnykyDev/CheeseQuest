package com.mykyda.api.controller;


import com.mykyda.api.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping(value = "/profile/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadProfileImage(@RequestPart MultipartFile image) {
        return mediaService.uploadProfileImage(image);
    }
    @PostMapping(value = "/taskMedia/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadMedia(@RequestPart MultipartFile media) {
        return mediaService.uploadTaskMedia(media);
    }
}
