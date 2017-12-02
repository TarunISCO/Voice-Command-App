/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voicecommand;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.awt.Desktop;
import java.io.File;
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
        configuration.setDictionaryPath("/home/tarun/NetBeansProjects/VoiceCommand/latest.dic");
        
        // Set path to the language model.
        configuration.setLanguageModelPath("/home/tarun/NetBeansProjects/VoiceCommand/latest.lm");
        
        // Recognizer Object, Pass the Configuration object
        LiveSpeechRecognizer recognize = new LiveSpeechRecognizer(configuration);
 
        // Start Recognition Process (The bool parameter clears the previous cache if true)
        recognize.startRecognition(true);
        
        // Create SpeechResult object
        SpeechResult result;
        
        Runtime mRunTime = Runtime.getRuntime();
        
        // Checking if recogniser has recognised the speech
        while ((result = recognize.getResult()) != null) {
            //Get the recognized speech
            String command = result.getHypothesis();
            String work = null;
            Process p;
            
            //Some Extra Commands from my Corpus File
            if(command.equalsIgnoreCase("open search")) {
                work = "google-chrome http://www.google.com";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("new tab")) {
                work = "google-chrome \\c";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("open mail")) {
                work = "google-chrome mail.google.com";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("open linked in")) {
                work = "google-chrome linkedin.com/in/tarunisco";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("open blog")) {
                work = "google-chrome procurity.wordpress.com";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("open git hub")) {
                work = "google-chrome github.com/tarunisco";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("browser")) {
                work = "google-chrome";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("terminal")) {
                work = "gnome-terminal";
                System.out.println(command);
            } 
            else if (command.equalsIgnoreCase("file manager")) {
                work = "nautilus";
                System.out.println(command);
            }
            else if (command.equalsIgnoreCase("presentation")) {
                File presentationFile = new File("/home/tarun/speech.pdf");
                if(presentationFile.exists()) {
                    if(Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(presentationFile);
                    }
                }
                System.out.println("Presentation opened");
            }
            else if (command.equalsIgnoreCase("open paper")) {
                File presentationFile = new File("/home/tarun/paper.pdf");
                if(presentationFile.exists()) {
                    if(Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(presentationFile);
                    }
                }
                System.out.println("Paper opened");
            }
            
            if(work != null) {
                p = Runtime.getRuntime().exec(work);
            }
        }
        
    }
    
}
