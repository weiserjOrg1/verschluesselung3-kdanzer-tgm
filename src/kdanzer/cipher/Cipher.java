package kdanzer.cipher;

public interface Cipher {
	/**Interface which showes how the Methodes encrypt and decrpyt has
	 * to be
	 * 
	 * @author Kalian Danzer
	 * @version 1.0
	 * @param geheimtext
	 * @return
	 */
	
	public String encrypt(String geheimtext);
	
	public String decrypt(String klartext);
}
