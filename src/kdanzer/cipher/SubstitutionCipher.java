package kdanzer.cipher;

public class SubstitutionCipher extends MonoAlphabeticCipher {
	
	//Konstrukor
	public SubstitutionCipher(String secretAlphabet) {
		super.setSecretAlphabet(secretAlphabet);
	}
	public SubstitutionCipher() {
	}
	
	public boolean setSecretAlphabet(String secretAlphabet) {
		return super.setSecretAlphabet(secretAlphabet);
	}
}
