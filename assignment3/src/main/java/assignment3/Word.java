package assignment3;

public class Word {
	String word;
	int freq;
	public Word() {
		word= null;
		freq=0;
	}
	public Word(String word) {
		this.word = word;
		this.freq = 0;
	}
	public void increaseFrequency() {
		this.freq++;
	}
	public int getFrequency() {
		return this.freq;
	}
	public String getWord() {
		return this.word;
	}

	public String toString() {
		return this.word;
	}
}
