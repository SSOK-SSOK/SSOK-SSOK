package com.ssafy.ssokcard.service;

import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.*;
import com.ssafy.ssokcard.model.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class STTService {
    @Value("${file.localPath}")
    private String LOCAL_PATH;  // 로컬 빌드 시 사용
    @Value("${file.serverPath}")
    private String SERVER_PATH; // 서버 빌드 시 사용

    @Value("${google.cloud.storage.projectId}")
    private String projectId;
    @Value("${google.cloud.storage.bucketName}")
    private String bucketName;

    public File saveFile(MultipartFile inputFile, String fileName) {
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + fileName;
        File file = new File(SERVER_PATH, newFileName + ".wav");

        try {
            inputFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new File(SERVER_PATH, "IOException");
        }

        return file;
    }

    public String saveBucket(String objectName, String filePath) {
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        try {
            storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();

            return "IOException";
        }

        return "gs://" + bucketName + "/" + objectName;
    }

    public Object googleConvertVoiceToText(String languageCode, String audioFilePath) {
        BasicResponse answer = new BasicResponse();
        if(!("ko-KR".equals(languageCode) || "en-US".equals(languageCode) || "zh".equals(languageCode) || "ja-JP".equals(languageCode) || "vi-VN".equals(languageCode) || "fr-FR".equals(languageCode) || "es-ES".equals(languageCode))) {
            answer.status = false;
            answer.data = "변환 실패";
            answer.object = null;
            return answer;
        }

        try (SpeechClient speechClient = SpeechClient.create()) {
            // The path to the audio file to transcribe
            String gcsUri = audioFilePath;

            // Builds the sync recognize request
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setAudioChannelCount(2) // WAV header indicates 2 channels
                            .setLanguageCode(languageCode) // "en-US"
                            .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            if(results == null || results.size() == 0) {
                answer.status = false;
                answer.data = "변환 실패";
                answer.object = null;
                return answer;
            }

            SpeechRecognitionAlternative alternative = results.get(0).getAlternativesList().get(0);

            answer.status = true;
            answer.data = "변환 성공";
            answer.object = alternative.getTranscript();

        } catch (IOException e) {
            e.printStackTrace();

            answer.status = false;
            answer.data = "변환 실패";
            answer.object = null;

            return answer;
        }

        return answer;
    }
}
