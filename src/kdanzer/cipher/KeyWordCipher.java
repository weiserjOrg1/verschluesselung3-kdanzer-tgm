package kdanzer.cipher;

public class KeyWordCipher extends MonoAlphabeticCipher{
	//Attribute
	private String secreteAlphabet;
	
	//Konstrukor
		public KeyWordCipher(String key) {
			this.secreteAlphabet = MonoAlphabeticCipher.alphabet;
			this.createSecretAlphabet(key);
		}
		
		public void createSecretAlphabet(String key) {
			//Makes a secrete Alphabet from the key
			
		}

		public boolean setSecretAlphabet() {
			//Sets the secret Alphabet from the generated
			return super.setSecretAlphabet(this.secreteAlphabet);
		}
}
