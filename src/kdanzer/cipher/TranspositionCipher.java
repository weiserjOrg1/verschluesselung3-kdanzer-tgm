package kdanzer.cipher;

public class TranspositionCipher implements Cipher{
	//Atributte
	private int transposLevel; //Level of the Cipher
	private int between; //How many letters are between the 2 top 
	
	//Konstruktor
	public TranspositionCipher(int level) {
		this.setTransposLevel(level);
	}
	
	//Methoden
	@Override
	public String decrypt(String text) {
		// check if \n == translvl
		int count = text.length() - text.replace(" ", "").length();
		if(count != this.transposLevel) {
			return text;
		}
		
		
		String usedText = text;
		String[] textFragments = new String[this.transposLevel];
		
		String decryptedText = "";
		
		for(int i = 0; i < textFragments.length; i++) {
			textFragments[i] = "";
		}
		for(int i = 0; i < this.transposLevel;) {
			int help = usedText.indexOf(" ");
			textFragments[i] = usedText.substring(0, usedText.indexOf(" "));
			i++;
			if(i < this.transposLevel) {
				usedText = usedText.substring(usedText.indexOf(" ")+1, usedText.length());
			}
		}
		int counter = 0;
		int[] counters = new int[this.transposLevel];
		for(int i = 0; i < text.length()-this.transposLevel; i++) {
			int resetVal = counter%this.transposLevel;
			if(resetVal == 0) {
				counter = 0;
			}
			decryptedText = decryptedText + textFragments[counter].charAt(counters[counter]);
			counters[counter] = counters[counter] + 1;
			counter++;
		}
		return decryptedText;
	}

	@Override
	public String encrypt(String text) {
		text = text.replace(" ", "");
		String[] placeHolder = new String[this.transposLevel];
		for(int i = 0; i < placeHolder.length; i++) {
			placeHolder[i] = "";
		}
		String encryptedText = "";
		
		int counter = 0;
		for(int i = 0; i < text.length(); i++) {
			int resetVal = counter%this.transposLevel;
			if(resetVal == 0) {
				counter = 0;
			}
			placeHolder[counter] = placeHolder[counter] + text.charAt(i);
			counter++;
		}
		for(int i = 0; i < placeHolder.length; i++) {
			encryptedText = encryptedText + placeHolder[i] + " "; 
		}
		return encryptedText;
	}
	
	//Sets the Transpos Level
	public boolean setTransposLevel(int level) {
		if (level > 0) {
			this.transposLevel = level;
			this.between = (this.transposLevel-1)*2-1;
			return true;
		}
		return false;
	}
		

}
