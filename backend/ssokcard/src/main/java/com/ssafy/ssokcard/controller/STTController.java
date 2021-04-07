package com.ssafy.ssokcard.controller;

import com.ssafy.ssokcard.model.response.BasicResponse;
import com.ssafy.ssokcard.service.STTService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api
@CrossOrigin(origins = { "*" })
@RequestMapping("/stt")
@RestController
public class STTController {

    @Autowired
    private STTService sttService;

    @ApiOperation(value = "음성을 텍스트로 변환하기",
        notes = "[languageCode] ko-KR(대한민국), en-US(미국), zh(중국), ja-JP(일본), vi-VN(베트남), fr-FR(프랑스), es-ES(스페인)"
    )
    @PostMapping(value = "/convert")
    public Object convertVoiceToText(@RequestParam("file") MultipartFile inputFile, @RequestParam("code")String languageCode) {
        BasicResponse answer = new BasicResponse();
        if(inputFile == null || languageCode == null) {
            answer.status = false;
            answer.data = "변환 실패";
            answer.object = null;
            return answer;
        }

        File file = sttService.saveFile(inputFile, inputFile.getOriginalFilename());
        if("IOException".equals(file.getName())) {
            answer.status = false;
            answer.data = "변환 실패";
            answer.object = null;
            return answer;
        }

        String gcsUri = sttService.saveBucket(file.getName(), file.getPath());
        if("IOException".equals(gcsUri)) {
            answer.status = false;
            answer.data = "변환 실패";
            answer.object = null;
            return answer;
        }

        // ko-KR(대한민국), en-US(미국), zh(중국), ja-JP(일본), vi-VN(베트남), fr-FR(프랑스), es-ES(스페인)
        return sttService.googleConvertVoiceToText(languageCode, gcsUri);
    }
}
