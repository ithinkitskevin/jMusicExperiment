package main;

import jm.music.data.Note;
import jm.util.Play;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Note str1 = new Note();
		
		str1.setPitch(50); // 50 is G!!!
		
		Play.midi(str1);
	
	}

}
