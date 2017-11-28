/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voicecommand;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;

/**
 *
 * @author tarun
 */
public class VoiceCommand {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        // Configuration Object
        Configuration configuration = new Configuration();
        
        // Set path to the acoustic model.
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        
        // Set path to the dictionary.
        configuration.setDictionaryPath("/home/tarun/NetBeansProjects/VoiceCommand/0665.dic");
        // Set path to the language model.
        configuration.setLanguageModelPath("/home/tarun/NetBeansProjects/VoiceCommand/0665.lm");
        
        // Recognizer Object, Pass the Configuration object
        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
 
        // Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);
        
        // Create SpeechResult object
        SpeechResult result;
        
        // Checking if recogniser has recognised the speech
        while ((result = recognize.getResult()) != null) {
            //Get the recognize speech
            String command = result.getHypothesis();
            
            //Match recognized speech with our commands
            if(command.equalsIgnoreCase("open chrome")) {
                System.out.println("Chrome Opened!");
            } else if (command.equalsIgnoreCase("close chrome")) {
                System.out.println("Chrome Closed!");
            } else if (command.equalsIgnoreCase("open terminal")) {
                System.out.println("Terminal Opened!");
            } else if (command.equalsIgnoreCase("close terminal")) {
                System.out.println("Terminal Closed!");
            }
        }
        
    }
    
}
