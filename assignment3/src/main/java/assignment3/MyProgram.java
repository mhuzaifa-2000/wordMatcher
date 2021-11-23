package assignment3;
import java.util.*;
import java.io.*;
public class MyProgram {
	
	
	public static void checkMatches(BinarySearchTree tree, Vector<String>[] vectors) {
		for(int i=0; i<vectors.length; i++)
		{
			for(int j=0; j<vectors[i].size(); j++)
			{
				String w = vectors[i].get(j);
				BinarySearchTree.Node temp = tree.search(w);
				if(temp!=null)
				{
					temp.word.increaseFrequency();
				}
				
			}
		}
	}
	public static void queryHandler(String query, BinarySearchTree tree, Vector<String>[] vectors,ArrayList<String> fileNames) {
		boolean[] found = new boolean[vectors.length];
		int[] freq = new int[vectors.length];
		for(int i=0; i<freq.length; i++)
		{
			freq[i] = 0;
		}
		for(int i=0; i<vectors.length; i++)
		{
			for(int j=0; j<vectors[i].size(); j++)
			{
				if(vectors[i].get(j).compareToIgnoreCase(query)==0) {
					found[i] = true;
					freq[i]++;
				}
			}
		}
		
		boolean f=false;
		for(int i=0; i<found.length; i++)
		{
			if(found[i]==true)
			{
				f=true;
			}
		}
		if(f)
		{
			System.out.println("Query Found in Following Files: ");
		}
		else
		{
			System.out.println("Query not found in any of the input files");
		}
		for(int i=0; i<found.length; i++)
		{
			if(found[i]==true)
			{
				System.out.println(fileNames.get(i));
				System.out.println("Frequency in this file: "+Integer.toString(freq[i]));
			}
		}
	}
	public static void main(String[] args) throws InterruptedException,IOException
	{
		
		ArrayList<String> fileNames =  new ArrayList<String>();
		BinarySearchTree tree = new BinarySearchTree();
		Scanner input = new Scanner(System.in);
		Vector<String>[] vectors;
		String vocabName=new String();
		try {
			vocabName = args[0];
		}catch(Exception e) {
			System.out.println("Please Enter Arguments and Run Again");
			System.exit(0);
		}
		
		
		VocabFileThread vocabThread = new VocabFileThread(vocabName, tree);

		int numFiles = args.length-1;
		for(int i=1; i<args.length; i++)
		{
			String fileName = args[i];
			fileNames.add(fileName);
		}
		vectors =(Vector<String>[])  new Vector[numFiles];
		vocabThread.setName(vocabName);
		vocabThread.start();
		InputFileThread[] inputThreads = new InputFileThread[numFiles];
		for(int i=0; i< numFiles; i++)
		{
			vectors[i] = new Vector<String>();
			inputThreads[i] = new InputFileThread(fileNames.get(i), vectors[i]);
			inputThreads[i].setName(fileNames.get(i));
			inputThreads[i].start();
		}
		for(int i=0;i<numFiles; i++)
			inputThreads[i].join();
		
		vocabThread.join();
		checkMatches(tree, vectors);
		while(true)
		{
			System.out.print("1) Display BST build from Vocabulary File.\r\n"
					+ "2) Displaying Vectors build from Input files.\r\n"
					+ "3) Viewing Match words and its frequency\r\n"
					+ "4) Searching a query->It should display all the files query found in.\r\n"
					+ "5) Enter 5 for Exiting\n");
			String option = input.next();
			if(option.equals("1"))
			{
				System.out.println("");
				tree.print2D();
				System.out.println("");
			}
			else if(option.equals("2"))
			{
				System.out.println("");
				System.out.println("All Vectors built from input files");
				for(int i=0; i<numFiles; i++)
				{
					System.out.println("Vector for tree with input file"+ inputThreads[i].getName()+":" );
					System.out.println(vectors[i]);
				}
				System.out.println("");
			}
			else if(option.equals("3"))
			{
				System.out.println("");
				tree.displayMatchedWordsAndFrequencies();
				System.out.println("");
			}
			else if(option.equals("4"))
			{
				System.out.println("Enter you query word to search in input files: ");
				String query = input.next();
				queryHandler(query,tree,vectors,fileNames);
			}
			else if(option.equals("5"))
			{
				System.out.println("Application Closing...");
				break;
			}
			
		}
		input.close();
	}
}
