package kdanzer.cipher;

public class TranspositionCipher implements Cipher{
	//Atributte
	private int transposLevel;
	
	
	//Konstruktor
	public TranspositionCipher(int level) {
		this.setTransposLevel(level);
	}
	
	//Methoden
	@Override
	public String encrypt(String geheimtext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String klartext) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean setTransposLevel(int level) {
		if (level > 0) {
			this.transposLevel = level;
			return true;
		}
		return false;
	}
		

}
