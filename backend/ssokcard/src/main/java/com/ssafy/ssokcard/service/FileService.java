package com.ssafy.ssokcard.service;

import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.*;
import com.google.gson.Gson;
import com.ssafy.ssokcard.model.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.localPath}")
    private String LOCAL_PATH;
    @Value("${file.serverPath}")
    private String SERVER_PATH;

    @Value("${AIHub.STT.openApiURL}")
    private String openApiURL;
    @Value("${AIHub.STT.accessKey}")
    private String accessKey;    // 발급받은 API Key

    @Value("${google.cloud.storage.projectId}")
    private String projectId;
    @Value("${google.cloud.storage.bucketName}")
    private String bucketName;

    public File saveFile(MultipartFile inputFile, String fileName) throws IOException {
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + fileName;

        File file = new File(SERVER_PATH, newFileName + ".wav");
        inputFile.transferTo(file);

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
        }

        return "gs://" + bucketName + "/" + objectName;
    }

    public Object googleConvertVoiceToText(String languageCode, String audioFilePath) {
        BasicResponse answer = new BasicResponse();

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

            SpeechRecognitionAlternative alternative = results.get(0).getAlternativesList().get(0);

            answer.status = true;
            answer.data = "음성 번역 성공";
            answer.object = alternative.getTranscript();

        } catch (IOException e) {
            e.printStackTrace();

            answer.status = false;
            answer.data = "음성 번역 실패";
            answer.object = null;

            return answer;
        }

        return answer;
    }

    public Object aihubConvertVoiceToText(String languageCode, String audioFilePath) {
        String audioContents = null;
        Gson gson = new Gson();
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        try {
            Path path = Paths.get(audioFilePath);
            byte[] audioBytes = Files.readAllBytes(path);
            audioContents = Base64.getEncoder().encodeToString(audioBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        argument.put("language_code", languageCode);
        argument.put("audio", audioContents);
        request.put("access_key", accessKey);
        request.put("argument", argument);

        URL url;
        Integer responseCode = null;
        String responBody = null;
        try {
            url = new URL(openApiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(gson.toJson(request).getBytes("UTF-8"));
            wr.flush();
            wr.close();

            responseCode = con.getResponseCode();
            InputStream is = con.getInputStream();
            byte[] buffer = new byte[is.available()];
            int byteRead = is.read(buffer);
            responBody = new String(buffer);

            System.out.println("[responseCode] " + responseCode);
            System.out.println("[responBody]");
            System.out.println(responBody);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responBody;
    }

}
