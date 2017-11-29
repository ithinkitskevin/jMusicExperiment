package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jm.music.data.Note;
import jm.util.Play;

public class Guitar {
	static boolean stndTune = true;
	static int pitch1, pitch2, pitch3, pitch4, pitch5, pitch6;

	public static class allStr{
		static Note str1;
		static Note str2;
		static Note str3;
		static Note str4;
		static Note str5;
		static Note str6;
	}
	
	private static void initalize(int pitch1, int pitch2, int pitch3, int pitch4, int pitch5, int pitch6){
		allStr.str1 = new Note();
		allStr.str1.setPitch(pitch1); //g
		allStr.str2 = new Note();
		allStr.str2.setPitch(pitch2); //g
		allStr.str3 = new Note();
		allStr.str3.setPitch(pitch3); //g
		allStr.str4 = new Note();
		allStr.str4.setPitch(pitch4); //g
		allStr.str5 = new Note();
		allStr.str5.setPitch(pitch5); //g
		allStr.str6 = new Note();
		allStr.str6.setPitch(pitch6); //g
	}
	
	private static void strum() throws InterruptedException {
		Play.midi(allStr.str1);
		TimeUnit.MILLISECONDS.sleep(100);
		Play.midi(allStr.str2);
		TimeUnit.MILLISECONDS.sleep(100);
		Play.midi(allStr.str3);
		TimeUnit.MILLISECONDS.sleep(100);
		Play.midi(allStr.str4);
		TimeUnit.MILLISECONDS.sleep(100);
		Play.midi(allStr.str5);
		TimeUnit.MILLISECONDS.sleep(100);
		Play.midi(allStr.str6);
	}
	
	private static void reset(int pitch1, int pitch2, int pitch3, int pitch4, int pitch5, int pitch6) {
		pitch1 = 40; // Low E
		pitch2 = 45; // A
		pitch3 = 50; // D
		pitch4 = 55; // G
		pitch5 = 59; // B
		pitch6 = 64; // High E
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		int defWait = 150;
		
		if(true) { //standard way of tuning a guitar
			pitch1 = 40; // Low E
			pitch2 = 45; // A
			pitch3 = 50; // D
			pitch4 = 55; // G
			pitch5 = 59; // B
			pitch6 = 64; // High E
		}
				
		initalize(pitch1, pitch2, pitch3, pitch4, pitch5, pitch6);
		
		//strum(defWait);
		String csvFile = "/Users/KevinLee/Desktop/guitarMidi/GuitarMidi/src/main/asturias.csv"; 
		//Find a better way to do this... maybe JPanel with SQL Database?????
        String csvLine = "";
        String csvSplit = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((csvLine = br.readLine()) != null) {
        		reset(pitch1, pitch2, pitch3, pitch4, pitch5, pitch6);

                String[] csvNotes = csvLine.split(csvSplit);

                int temp = 0;
                
            	if(!csvNotes[0].equals("\"\"")){
            		csvNotes[0] = csvNotes[0].replaceAll("\"", "");
            		temp = pitch1 + Integer.parseInt(csvNotes[0]);
        			allStr.str1.setPitch(temp); 
        			Play.midi(allStr.str1);
            	} 
            	if(!csvNotes[1].equals("\"\"")){
            		csvNotes[1] = csvNotes[1].replaceAll("\"", "");
            		temp = pitch2 + Integer.parseInt(csvNotes[1]);
        			allStr.str2.setPitch(temp); 
        			Play.midi(allStr.str2);
            	} 
            	if(!csvNotes[2].equals("\"\"")){
            		csvNotes[2] = csvNotes[2].replaceAll("\"", "");
            		temp = pitch3 + Integer.parseInt(csvNotes[2]);
        			allStr.str3.setPitch(temp); 
        			Play.midi(allStr.str3);
            	} 
            	if(!csvNotes[3].equals("\"\"")){
            		csvNotes[3] = csvNotes[3].replaceAll("\"", "");
            		temp = pitch4 + Integer.parseInt(csvNotes[3]);
        			allStr.str4.setPitch(temp); 
        			Play.midi(allStr.str4);
            	} 
            	if(!csvNotes[4].equals("\"\"")){
            		csvNotes[4] = csvNotes[4].replaceAll("\"", "");
            		temp = pitch5 + Integer.parseInt(csvNotes[4]);
        			allStr.str5.setPitch(temp); 
        			Play.midi(allStr.str5);
            	} 
            	if(!csvNotes[5].equals("\"\"")){
            		csvNotes[5] = csvNotes[0].replaceAll("\"", "");
            		temp = pitch6 + Integer.parseInt(csvNotes[5]);
        			allStr.str6.setPitch(temp); 
        			Play.midi(allStr.str6);
            	} 
            	
        		TimeUnit.MILLISECONDS.sleep(defWait);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}

}

/*
Final Notes...
Finished making a program that gets a .csv file and converts them into a  midi musical piece, but ran into a trouble

The biggest trouble is that for a fast piece like Asturias, the jMusic MidiSynth will sometimes freeze up, thus having a 
lag in the middle of the piece... You'll be able to see this in the console as...

jMusic MidiSynth: Playing 'Untitled Score' using JavaSound General MIDI soundbank.
jMusic MidiSynth: Stopping
jMusic MidiSynth: Stopping
jMusic MidiSynth: Stopping

Maybe try other ways, check out package
javax.sound.midi
*/

