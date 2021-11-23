package assignment3;
import java.io.*;
import java.util.*;

public class InputFileThread extends Thread {
	String fileName;
	Vector<String> vector;
	public InputFileThread(String fileName, Vector<String>vector) {
		this.fileName = fileName;
		this.vector = vector;
	}
	public void run(){
		try {
			this.readFile();
		}catch(Exception e) {
			System.out.println("Error while reading file "+this.fileName);
		}
	}
	private synchronized void readFile() throws FileNotFoundException, IOException {
    	Scanner inputFile = new Scanner(new File(this.fileName));
		while(inputFile.hasNext()) {
			String word = inputFile.next();
			vector.add(word);
		}
		inputFile.close();
	}
	
}
