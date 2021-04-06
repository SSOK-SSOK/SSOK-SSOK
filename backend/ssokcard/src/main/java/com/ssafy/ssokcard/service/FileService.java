package com.ssafy.ssokcard.service;

import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.Gson;
import com.ssafy.ssokcard.model.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.sql.Blob;
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

//    private Storage storage = StorageOptions.getDefaultInstance().getService();
    private Storage storage;
    private final String bucketName = "ssokssok";

    public File saveFile(MultipartFile inputFile, String fileName) throws IOException {
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + fileName;

//        File file = new File(SERVER_PATH, newFileName + ".wav");
        File file = new File(LOCAL_PATH, newFileName + ".wav");
        inputFile.transferTo(file);

        return file;
    }

    public String saveBucket(String fileName, String localFileLocation) {

        BlobInfo blobInfo = null;
        //            blobInfo = storage.create(
//                    BlobInfo.newBuilder(bucketName, fileName)
//                            .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
//                            .build(),
//                    new FileInputStream(localFileLocation));

        blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();
        storage.create(blobInfo);

        System.out.println(blobInfo.toString());

        return "";
    }

    //    zh(중국), en-US(미국), ja-JP(일본), es-ES(스페인), vi-VN(베트남), ko-KR(대한민국), fr-FR(프랑스)
    public Object googleConvertVoiceToText(String languageCode, String audioFilePath) {
        BasicResponse answer = new BasicResponse();

        try (SpeechClient speechClient = SpeechClient.create()) {
            // The path to the audio file to transcribe
            String gcsUri = audioFilePath;

            // Builds the sync recognize request
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setSampleRateHertz(16000)
                            .setLanguageCode(languageCode) // "en-US"
                            .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

//            for (SpeechRecognitionResult result : results) {
//                // There can be several alternative transcripts for a given chunk of speech. Just use the
//                // first (most likely) one here.
//                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
//                System.out.printf("Transcription: %s%n", alternative.getTranscript());
//            }

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
