package com.example.baitaplon;

import com.darkprograms.speech.synthesiser.SynthesiserV2;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;

/**
 * This class uses the V2 version of Google's Text to Speech API. While this class requires an API key,
 * the endpoint allows for additional specification of parameters including speed and pitch.
 * See the constructor for instructions regarding the API_Key.
 * Source I consulted:
 * https://www.youtube.com/watch?v=42-ZqfPYmVw
 * https://github.com/goxr3plus/Java-Google-Text-To-Speech
 * https://github.com/goxr3plus/java-google-speech-api?fbclid=IwAR29ytMQTiw5jc5_fnJqGc0j9Hu_KUFIv8o1zZw5Mu3pRg_HYrz6JwMhsAk
 */
public class TextToSpeech {

//    public static void main(String[] args) {
//        new TextToSpeech("con chim");
//    }
    SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

    public TextToSpeech(String text) {
        speak(text);
    }

    public void speak(String text) {
        System.out.println(text);

        //Create a new Thread because JLayer is running on the current Thread and will make the application to lag
        Thread thread = new Thread(() -> {
            try {

                //Create a JLayer instance
                AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
                player.play();

                System.out.println("Successfully got back synthesizer data");

            } catch (IOException | JavaLayerException e) {

                e.printStackTrace(); //Print the exception ( we want to know , not hide below our finger , like many developers do...)

            }
        });
        //We don't want the application to terminate before this Thread terminates
        thread.setDaemon(false);
        //Start the Thread
        thread.start();

    }
}