package kdanzer.cipher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;

	/**This View builds a Window which makes it possible to decrypt/encrypt
	 * and you are able to create Keys
	 * 
	 * @author Kalian Danzer
	 */
	public static final int SUBSTITUTION = 0;
	public static final int SHIFT = 1;
	public static final int ANDERES = 2;
	
	//Attribute
	private Controller c1;
	@SuppressWarnings("unused")
	private Model m1;
	
	private JButton verschGo, entschGo, copyButton, alphabetButton; //start the Cipher
	private JComboBox<String> comboBox; //Combobox to choose the Cipher
	private JTextArea text, ausgabe, secretAlphabet; 	/*text field where one enter the clear text or the secret text , 
														Returns of the clear text or the secret text
														secretAlphabet where u enter the secrete Alphabet*/
	private JScrollPane scrollPane1, scrollPane2;
	private JPanel panel1, panel2, panel3; //to make the Layout
	private JLabel label1; //to be able to name some sections on the Panel;
	private Border border1, border2;
	private JSpinner shiftValueSpinner; //Where you rotate the secrete alphabet of shiftCipher
	private int activeCipher;
	
	//Konstruktor
	public View(Model m, Controller c) {
		/*Initaliesing of all Parameters*/
		this.c1 = c;
		this.m1 = m;
		this.addComponentListener(this.c1);
		

		
		/*Border*/
		this.border1 = BorderFactory.createTitledBorder("Ver-/Entschlüsselter Text");
		this.border2 = BorderFactory.createTitledBorder("Ausgabe");
		
		
		/*Frame Options*/
		this.setSize(500, 400);
		this.setTitle("Cipher");
		this.setMinimumSize(new Dimension(500, 400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		
		
		
		
		this.init();
		this.sizeAll();
		
		this.activateSubstitution();
		this.setVisible(true);
	}
	
	private void init() {
		this.getContentPane().setBackground(new Color(255, 255, 255));
		/*First Panel and his Components*/
		
		this.panel1 = new JPanel();
		this.panel1.setLayout(null);
		this.panel1.setBackground(new Color(255, 255, 255));
		this.panel1.setBorder(this.border1);
		this.add(panel1);
		
		this.text = new JTextArea();
		this.text.setLineWrap(true);
		this.text.setWrapStyleWord(true);
		this.text.setBackground(new Color(230, 250, 230));
		
		this.scrollPane1 = new JScrollPane();
		this.scrollPane1.setViewportView(this.text);
		this.panel1.add(scrollPane1);
		
		
		/*Second Panel and his Components*/
		this.panel2 = new JPanel();
		this.panel2.setLayout(null);
		this.panel2.setBackground(new Color(255, 255, 255));
		this.panel2.setBorder(this.border2);
		this.add(panel2);
		
		this.ausgabe = new JTextArea();
		this.ausgabe.setLineWrap(true);
		this.ausgabe.setWrapStyleWord(true);
		this.ausgabe.setEditable(false);
		this.ausgabe.setBackground(new Color(206, 224, 230));
		
		this.scrollPane2 = new JScrollPane();
		this.scrollPane2.setViewportView(this.ausgabe);
		this.panel2.add(scrollPane2);
		
		
		/*Buttons to start Cipher*/
		this.verschGo = new JButton("Verschlüssen");
		this.add(this.verschGo);
		this.verschGo.setFocusPainted(false);
		this.verschGo.addActionListener(this.c1);
		
		this.entschGo  = new JButton("Entschlüsseln");
		this.add(this.entschGo);
		this.entschGo.setFocusPainted(false);
		this.entschGo.addActionListener(this.c1);
		
		
		/*Copy Buton*/
		this.copyButton  = new JButton("Ausgabe Kopieren ");
		this.add(this.copyButton);
		this.copyButton.setFocusPainted(false);
		this.copyButton.addActionListener(this.c1);
		
		
		/*Third Panel and his Components*/
		this.panel3 = new JPanel();
		this.panel3.setLayout(null);
		this.panel3.setBackground(new Color(250, 255, 255));
		this.add(panel3);
		
		this.comboBox = new JComboBox<>(new String[] {"Substitution", "Shift"});
		this.comboBox.setFocusable(false);
		this.comboBox.addActionListener(this.c1);
		this.panel3.add(this.comboBox);
		
		this.label1 = new JLabel("Shift Wert");
		this.panel3.add(this.label1);
		
		this.shiftValueSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 29, 1));
		this.shiftValueSpinner.setEditor(new JSpinner.DefaultEditor(this.shiftValueSpinner));
		this.shiftValueSpinner.addChangeListener(this.c1);
		this.panel3.add(this.shiftValueSpinner);
		
		this.secretAlphabet = new JTextArea();
		this.secretAlphabet.setLineWrap(true);
		this.secretAlphabet.setWrapStyleWord(true);
		this.secretAlphabet.setBackground(new Color(231, 208, 234));
		this.secretAlphabet.setFont(new Font("", Font.PLAIN, 15));
		this.secretAlphabet.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.secretAlphabet.setDocument(new PlainDocument() {
			private static final long serialVersionUID = 1L;

			@Override
			public void insertString(int offs, String str, AttributeSet a) 
		            throws BadLocationException {
				for (int i = str.length()-1; i >= 0; i--) {
					String s = str.charAt(i) + "";
			        if(s.matches("[a-zöüäß]") && secretAlphabet.getText().contains(s) == false) {
			        	super.insertString(offs, s, a);
			        } else {
			        	Toolkit.getDefaultToolkit().beep();
			        }
				}
				if (secretAlphabet.getText().length() < 30) {
					alphabetButton.setEnabled(false);
				} else {
					alphabetButton.setEnabled(true);
				}
		    }
			@Override
			protected void removeUpdate(AbstractDocument.DefaultDocumentEvent chng) {
				if (secretAlphabet.getText().length() <= 30) {
					alphabetButton.setEnabled(false);
				} else {
					alphabetButton.setEnabled(true);
				}
			}
		});
		this.panel3.add(this.secretAlphabet);
		
		this.alphabetButton = new JButton("Set Alphabet");
		this.alphabetButton.setFocusPainted(false);
		this.alphabetButton.setBorder(new LineBorder(Color.GRAY));
		this.alphabetButton.addActionListener(this.c1);
		this.alphabetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent source) {
				if (alphabetButton.isEnabled())
					alphabetButton.setBorder(BorderFactory.createEtchedBorder(0, Color.BLACK, Color.WHITE));
			}
			@Override
			public void mouseExited(MouseEvent source) {
				alphabetButton.setBorder(new LineBorder(Color.GRAY));
			}
		});
		this.panel3.add(this.alphabetButton);
		
	}
	
	public void sizeAll() {
		int realh = this.getHeight()-39;
		/*First Panel and his Components*/
		this.panel1.setBounds(0, 5, this.getWidth()-16, realh/4);
		
		this.scrollPane1.setBounds(10, this.panel1.getHeight()/4, this.panel1.getWidth()/15*14, this.panel1.getHeight()/5*3);
		this.scrollPane1.setViewportView(this.text);
		
		/*Second Panel and his Components*/
		this.panel2.setBounds(0, realh/8*5-5, this.getWidth()-16, realh/4);
		
		this.scrollPane2.setBounds(10, this.panel2.getHeight()/4, this.panel2.getWidth()/15*14, this.panel2.getHeight()/5*3);
		this.scrollPane2.setViewportView(this.ausgabe);
		
		/*Buttons to start Cipher*/
		int helpInt1 = realh/8*7;
		int helpInt2 = realh - helpInt1;
		this.verschGo.setBounds(0, helpInt1, this.getWidth()/3, helpInt2);
		
		this.entschGo.setBounds(this.getWidth()/3, helpInt1, this.getWidth()/3, helpInt2);
		
		/*Copy Buton*/
		this.copyButton.setBounds(this.getWidth()*2/3, helpInt1, this.getWidth()/3-15, helpInt2);
		
		/*Third Panel and his Components*/
		this.panel3.setBounds(0, realh/4, this.getWidth()-16, realh*3/8);
		
		this.comboBox.setBounds(10, 10, 150, 35);
		this.label1.setBounds(10, 54, 80, 16);
		this.shiftValueSpinner.setBounds(10, 70, 60, 20);
		this.secretAlphabet.setBounds(270, 10, this.panel3.getWidth()/15*14-260, this.panel3.getHeight()/5*2);
		this.alphabetButton.setBounds(165, 10, 100, 35);
	}
	
	//Methoden
	public String getEnterdText() {
		return this.text.getText();
	}
	
	public String getExitedText() {
		return this.ausgabe.getText();
	}
	
	public void setExitedText(String versch) {
		this.ausgabe.setText(versch);
	}
	

	public String getSecretAlphabet() {
		return this.secretAlphabet.getText();
	}

	
	public int getComboBoxIndex() {
		return this.comboBox.getSelectedIndex();
	}

	public int getSpinnerValue() {
		return (int) this.shiftValueSpinner.getValue();
	}
	
	public int getActiveCipher() {
		return this.activeCipher;
	}
	
	public void activateShift() {
		this.shiftValueSpinner.setEnabled(true);
		this.secretAlphabet.setEnabled(false);
		this.alphabetButton.setEnabled(false);
		this.activeCipher = View.SHIFT;
	}
	
	public void activateSubstitution() {
		this.shiftValueSpinner.setEnabled(false);
		this.secretAlphabet.setEnabled(true);
		this.alphabetButton.setEnabled(true);
		this.activeCipher = View.SUBSTITUTION;
		if (secretAlphabet.getText().length() < 30) this.alphabetButton.setEnabled(false);
	}
	
	
	public boolean isComboBox(Object o) {
		if (this.comboBox == o) return true;
		return false;
	}
	
	public boolean isVerschButton(Object o) {
		if (this.verschGo == o) return true;
		return false;
	}
	
	public boolean isEntschButton(Object o) {
		if (this.entschGo == o) return true;
		return false;
	}
	
	public boolean isCopyButton(Object o) {
		if (this.copyButton == o) return true;
		return false;
	}
	
	public boolean isSetAlphButton(Object o) {
		if (this.alphabetButton == o) return true;
		return false;
	}
	
	public boolean isSpinner(Object o) {
		if (this.shiftValueSpinner == o) return true;
		return false;
	}

	

}
