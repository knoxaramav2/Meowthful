package drawer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NameBox extends JFrame implements ActionListener{
	private JTextField  textField;
	private String  	text;
	private SaveButton  save;
	private String		output;
	
	public NameBox(SaveButton save, String output){			
		textField = new JTextField(20);
		textField.addActionListener(this);
		add(textField);
		text = "";
		this.output = output;
		
		this.save = save;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		text = textField.getText();
		save.setFilePath(text, output);
		dispose();
	}
}
