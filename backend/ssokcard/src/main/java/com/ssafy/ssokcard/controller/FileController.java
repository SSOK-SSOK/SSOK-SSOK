package com.ssafy.ssokcard.controller;

import com.google.gson.Gson;
import com.ssafy.ssokcard.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Api
@CrossOrigin(origins = { "*" })
@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "파일 업로드")
    @PostMapping(value = "/upload")
    public Object readFile(@RequestParam ("file") MultipartFile inputFile) throws IOException {
//        File file = fileService.saveFile(inputFile, inputFile.getOriginalFilename());
//        return fileService.googleConvertVoiceToText("ko-KR", file.getPath());
        return fileService.googleConvertVoiceToText("ko-KR", "C:\\SSAFY\\dog.wav");
//        return fileService.aihubConvertVoiceToText("korean", file.getPath());
    }
}
