package com.example.baitaplon.LibrarySpeech;

import net.sourceforge.javaflacencoder.FLACFileWriter;

import java.io.IOException;

public class speechToText implements GSpeechResponseListener {
    public static void main(String[] args) throws IOException {
        System.out.println(new speechToText().speech("en"));
    }
    protected final Microphone mic = new Microphone(FLACFileWriter.FLAC);
    // You have to make your own GOOGLE_API_KEY
    protected GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    private String ketQua = "";
    // Set language to English (The language must be supported by Google)
    public String speech(String setLanguage) throws IOException {

        duplex.setLanguage(setLanguage);

                new Thread(() -> {
                    try {
                        duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }).start();

//                mic.close();
//                duplex.stopSpeechRecognition();


        duplex.addResponseListener(new GSpeechResponseListener() {
            String old_text = "";
            public void onResponse(GoogleResponse gr) {
                String output = "";
                output = gr.getResponse();
                if (gr.getResponse() == null) {
                    if (this.old_text.contains("(")) {
                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                    }
                    System.out.println("Paragraph Line Added");
                    this.old_text = this.old_text.replace(")", "").replace("( ", "");
                    return;
                }
                if (output.contains("(")) {
                    output = output.substring(0, output.indexOf('('));
                }
                if (!gr.getOtherPossibleResponses().isEmpty()) {
                    output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
                }
                ketQua = output;
                System.out.println(output);
            }
        });
        return ketQua;
    }

    // Default language is English
    public String speech() throws IOException {

        duplex.setLanguage("en");

        new Thread(() -> {
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }).start();

//                mic.close();
//                duplex.stopSpeechRecognition();



        duplex.addResponseListener(new GSpeechResponseListener() {
            String old_text = "";

            public void onResponse(GoogleResponse gr) {
                String output = "";
                output = gr.getResponse();
                if (gr.getResponse() == null) {
                    if (this.old_text.contains("(")) {
                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                    }
                    System.out.println("Paragraph Line Added");
                    this.old_text = this.old_text.replace(")", "").replace("( ", "");
                    return;
                }
                if (output.contains("(")) {
                    output = output.substring(0, output.indexOf('('));
                }
                if (!gr.getOtherPossibleResponses().isEmpty()) {
                    output = output + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
                }
                ketQua = output;
                System.out.println(output);
            }
        });
        return ketQua;
    }
    @Override
    public void onResponse(GoogleResponse paramGoogleResponse) {
        // TODO Auto-generated method stub

    }
}