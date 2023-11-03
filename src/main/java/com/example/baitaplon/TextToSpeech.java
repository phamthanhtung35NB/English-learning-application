////import com.google.api.client.googleapis.auth.oauth2.GoogleCredentials;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class TextToSpeech {
//
//    private static final String API_KEY_FILE_PATH = "/path/to/api_key.json";
//
//    public static void main(String[] args) throws IOException {
//        // Tải xuống API key từ tệp.
//        InputStream inputStream = new FileInputStream(API_KEY_FILE_PATH);
//        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
//
//        // Tạo một HttpTransport.
//        HttpTransport httpTransport = new NetHttpTransport();
//
//        // Tạo một JsonFactory.
//        JsonFactory jsonFactory = new JacksonFactory();
//
//        // Tạo một TextToSpeechClient.
//        TextToSpeechClient textToSpeechClient = new TextToSpeechClient(httpTransport, jsonFactory, credentials);
//
//        // Chuyển văn bản thành giọng nói.
//        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(
//                "Xin chào, thế giới!",
//                SynthesisConfig.newBuilder()
//                        .setLanguageCode("vi-VN")
//                        .setSpeakingRate(1.0)
//                        .setPitch(1.0)
//                        .setVolume(1.0)
//                        .build()
//        );
//
//        // Lưu kết quả thành tệp âm thanh.
//        byte[] audioData = response.getAudioContent();
//        File audioFile = new File("output.wav");
//        audioFile.createNewFile();
//        FileOutputStream fileOutputStream = new FileOutputStream(audioFile);
//        fileOutputStream.write(audioData);
//        fileOutputStream.close();
//
//    }
//}