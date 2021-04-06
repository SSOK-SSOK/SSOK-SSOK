package com.ssafy.ssokcard.controller;

import com.ssafy.ssokcard.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api
@CrossOrigin(origins = { "*" })
@RequestMapping("/stt")
@RestController
public class STTController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "음성을 텍스트로 변환하기")
    @PostMapping(value = "/convert")
    public Object convertVoiceToText(@RequestParam("file") MultipartFile inputFile, @RequestParam("code")String languageCode) throws IOException {
        System.out.println("convertVoiceToText()");

        File file = fileService.saveFile(inputFile, inputFile.getOriginalFilename());

        String gcsUri = fileService.saveBucket(file.getName(), file.getPath());

//        zh(중국), en-US(미국), ja-JP(일본), es-ES(스페인), vi-VN(베트남), ko-KR(대한민국), fr-FR(프랑스)
        return fileService.googleConvertVoiceToText(languageCode, gcsUri);
    }
}
