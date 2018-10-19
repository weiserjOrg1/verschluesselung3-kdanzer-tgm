package kdanzer.cipher;

public class ShiftCipher extends MonoAlphabeticCipher {
	private int shiftValue;
	
	public ShiftCipher(int value) {
		while (value >= 30) value-=29;
		if (value >= 0)	this.shiftValue = value;
	}
	
	
	public void setShiftValue(int value) {
		while (value >= 30) value-=29;
		if (value >= 0)	this.shiftValue = value;
	}
	
	public String getRotatedAlphabet() {
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
				'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ä', 'ö', 'ü', 'ß', };
		char[] hilfe = new char[alphabet.length];
		int zahl;
		for (int i = 0; i < alphabet.length; i++) {
			
			zahl = i + this.shiftValue;
			while (zahl >= 30) zahl-=30;
			
			hilfe[i] = alphabet[zahl];
		}
		String string = "";
		for (int i = 0; i < alphabet.length; i++) {
			string += hilfe[i] + "";
		}
		return string;
	}
}
