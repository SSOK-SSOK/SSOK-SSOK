package com.ssafy.ssokcard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;

import java.io.IOException;

@Api
@CrossOrigin(origins = { "*" })
@RequestMapping("/translate")
@RestController
public class TranslateController {

    @Value("${google.cloud.projectId}")
    private String projectId;

    @ApiOperation(value = "번역하기")
    @GetMapping("/{text}")
    public String translateText(@PathVariable("text")String text) throws IOException {
        TranslateTextResponse response;

        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            LocationName parent = LocationName.of(projectId, "global");

            TranslateTextRequest request =
                    TranslateTextRequest.newBuilder()
                            .setParent(parent.toString())
                            .setMimeType("text/plain")
                            .setTargetLanguageCode("en")
                            .addContents(text)
                            .build();

            response = client.translateText(request);

            System.out.printf("Translated text: %s\n", response.getTranslationsList().get(0).getTranslatedText());
        }

        return response.getTranslationsList().get(0).getTranslatedText();
    }
}