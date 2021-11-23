package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class VocabFileThread extends Thread {
	private BinarySearchTree tree;
	private String fileName;
	
	public VocabFileThread(String fileName, BinarySearchTree tree ){
		this.tree = tree;
		this.fileName = fileName;
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
			this.tree.add(word);
		}
		inputFile.close();
	}
}
