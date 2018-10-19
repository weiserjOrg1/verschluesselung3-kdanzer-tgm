package kdanzer.cipher;

public class Model {
	//Attribute
	public SubstitutionCipher subc;
	public ShiftCipher shiftC;
	private String alphabet;
	public Model() {
		this.alphabet = "abcdefghijklmnopqrstuvwxyzäöüß";
		this.shiftC = new ShiftCipher(0);
		this.subc = new SubstitutionCipher();
		this.subc.setSecretAlphabet(this.alphabet);
	}
}
