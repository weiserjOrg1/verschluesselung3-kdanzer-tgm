package kdanzer.cipher;

public class KeyWordCipher extends MonoAlphabeticCipher{
	//Attribute
	private String secreteAlphabet;
	
	//Konstrukor
		public KeyWordCipher(String key) {
			this.secreteAlphabet = MonoAlphabeticCipher.alphabet;
			this.createSecretAlphabet(key);
		}
		
		public String createSecretAlphabet(String key) {
			key = key.toLowerCase();
			String hilfe = "";
			for (int i = 0; i < key.length(); i ++) {
				String str = key.charAt(i) + "";
				if (str.matches("[a-zäöüß}]")) {
					hilfe += str;
				}
			}
			key = hilfe;
			hilfe = "";
			boolean letters[] = new boolean[MonoAlphabeticCipher.alphabet.length()];
			for (int i = 0; i < key.length(); i ++) {
				if (letters[alphabet.indexOf(key.charAt(i))] == false) {
					hilfe += key.charAt(i);
					letters[alphabet.indexOf(key.charAt(i))] = true;
				}
			}
			key = hilfe;
			for (int i = 0; i < alphabet.length(); i ++) {
				if (key.contains("" + alphabet.charAt(i))) {
				} else {
					key += alphabet.charAt(i);
				}
			}
			return key;
		}

		public boolean setSecretAlphabet() {
			//Sets the secret Alphabet from the generated
			return super.setSecretAlphabet(this.secreteAlphabet);
		}
}
