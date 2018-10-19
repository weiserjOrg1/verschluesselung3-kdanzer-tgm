package kdanzer.cipher;

public class MonoAlphabeticCipher implements Cipher {
	//Attribute
	public static final String alphabet = "abcdefghijklmnopqrstuvwxyzäöüß"; //String to save the alphabet how it normally lookes
	private String secretAlphabet; //String to save the secrete alphabet
	
	//Konstruktor
	public MonoAlphabeticCipher() {
		System.setProperty("file.encoding", "UTF-8"); //Set all to UTF-8
	}
	
	
	public String getSecretAlphabet() {
		//Returns the secrete Alphabet
		return secretAlphabet;
	}
	
	protected boolean setSecretAlphabet(String secretAlphabet) {
		//Sets the secrete Alphabet only allowed for Methodes in the same Package if an unallowed thing happens it throws an Exception
		if (secretAlphabet.length() == 30) {
			for (int i = 0; i < 30; i++) {
				if (secretAlphabet.contains("" + MonoAlphabeticCipher.alphabet.charAt(i)) == false) {
					return false;
				}
			}
			this.secretAlphabet = secretAlphabet;
		} else {
			return false;
		}
		return true;
		
	}


	@Override
	public String encrypt(String klartext) {
		//Encrypts the clear text to a secret text and returns it
		StringBuilder ausgabe = new StringBuilder();
		klartext = klartext.toLowerCase();
	
		for(int i = 0; i < klartext.length(); i++) {
			int index = MonoAlphabeticCipher.alphabet.indexOf(klartext.charAt(i));
			if (index == -1) 
				ausgabe.append(klartext.charAt(i));
			else
				ausgabe.append(this.secretAlphabet.charAt(index));
		}
		
		return ausgabe.toString();
	}
	

	@Override
	public String decrypt(String geheimtext) {
		//Dencrypts the secret text to a clear text and returns it
		StringBuilder ausgabe = new StringBuilder();
		geheimtext = geheimtext.toLowerCase();
		
		for(int i = 0; i < geheimtext.length(); i++) {
			int index = this.secretAlphabet.indexOf(geheimtext.charAt(i));
			if (index == -1) 
				ausgabe.append(geheimtext.charAt(i));
			else
			ausgabe.append(MonoAlphabeticCipher.alphabet.charAt(index));
		}
		
		return ausgabe.toString();
	}
}
