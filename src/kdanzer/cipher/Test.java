package kdanzer.cipher;

public class Test {
	/**This is the main class starts the Gui
	 * @author Kalian Danzer
	 */
	public static void main(String[] args) { 
		TranspositionCipher tc = new TranspositionCipher(3);
		String gh = tc.encrypt("Alder");
		System.out.println(gh);
		gh = tc.decrypt(gh);
		System.out.println(gh);
		Controller c = new Controller();
	}
}
