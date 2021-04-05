package com.ssafy.ssokcard.service;

import com.google.gson.Gson;
import com.ssafy.ssokcard.model.response.BasicResponse;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
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
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {
    @Value("${file.localPath}")
    private String LOCAL_PATH;
    @Value("${file.serverPath}")
    private String SERVER_PATH;

    @Value("${AIHub.STT.openApiURL}")
    private String openApiURL;
    @Value("${AIHub.STT.accessKey}")
    private String accessKey;    // 발급받은 API Key

    public BasicResponse saveFile(MultipartFile inputFile, String fileName) throws IOException {
        BasicResponse result = new BasicResponse();
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + fileName;

        File file = new File(SERVER_PATH, newFileName + ".wav");
        inputFile.transferTo(file);

        result.status = true;
        result.data = "파일 경로";
        result.object = file.getPath();

        return result;
    }

    public Blob convertFileToBlob(File file) throws Exception {

        Blob blob = null;
        FileInputStream inputStream = null;
        try {
            byte[] byteArray = new byte[(int) file.length()];
            inputStream = new FileInputStream(file);
            inputStream.read(byteArray);

            blob = new javax.sql.rowset.serial.SerialBlob(byteArray);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                inputStream = null;
            } finally {
                inputStream = null;
            }
        }

        return blob;
    }

    public Object convertVoiceToText(String languageCode, String audioFilePath) {
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
