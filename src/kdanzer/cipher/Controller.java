package kdanzer.cipher;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller implements ActionListener, ChangeListener, ComponentListener {
	/**This is the Controller which has an ActionListener implemented so the View can use Buttons
	 * 
	 * @author Kalian Danzer
	 */
	//Attribute
	private View v1;
	private Model m1;
	//Konstruktor
	public Controller() {
		this.m1 = new Model();
		this.v1 = new View(this.m1, this);
	}
		
	//Methoden
	
	
	
	//Listener
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (this.v1.isComboBox(ev.getSource())) {
			if (this.v1.getComboBoxIndex() == 0) {
				this.v1.activateSubstitution();
			} else if (this.v1.getComboBoxIndex() == 1) {
				this.v1.activateShift();
			}
		}
		if (this.v1.isVerschButton(ev.getSource())) {
			if (View.SUBSTITUTION == this.v1.getActiveCipher()) {
				String versch = this.m1.subc.encrypt(this.v1.getEnterdText());
				this.v1.setExitedText(versch);
			} else if (View.SHIFT == this.v1.getActiveCipher()) {
				String versch = this.m1.shiftC.encrypt(this.v1.getEnterdText());
				this.v1.setExitedText(versch);
			}
		}
		if (this.v1.isEntschButton(ev.getSource())) {
			if (View.SUBSTITUTION == this.v1.getActiveCipher()) {
				String versch = this.m1.subc.decrypt(this.v1.getEnterdText());
				this.v1.setExitedText(versch);
			} else if (View.SHIFT == this.v1.getActiveCipher()) {
				String versch = this.m1.shiftC.decrypt(this.v1.getEnterdText());
				this.v1.setExitedText(versch);
			}
		}
		if (this.v1.isSetAlphButton(ev.getSource())) {
			this.m1.subc.setSecretAlphabet(this.v1.getSecretAlphabet());
		}
		if (this.v1.isCopyButton(ev.getSource())) {
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.v1.getExitedText()), null);
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent ev) {
		if (this.v1.isSpinner(ev.getSource())) {
			this.m1.shiftC.setShiftValue(this.v1.getSpinnerValue());
			this.m1.shiftC.setSecretAlphabet(this.m1.shiftC.getRotatedAlphabet());
		}
	}


	@Override
	public void componentResized(ComponentEvent e) { //Repaint Frame so the Components in the Pane updates
		try {
			this.v1.sizeAll();
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
	
	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
	
}
