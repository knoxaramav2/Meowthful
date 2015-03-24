package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import scriptEngine.unownInterpreter;
 
@SuppressWarnings("serial")
public class Console extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    static JFrame frame;
    private static unownInterpreter ui;
 
    public Console() {
        super(new GridBagLayout());
 
        textField = new JTextField(20);
        textField.addActionListener(this);
 
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }
 
    public void appendText(String text)
    {
        this.textArea.append(text + newline);
        this.textField.selectAll();
        
        this.textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    public void toggleVisible()
    {
    	frame.setVisible(!frame.isVisible());
    }
    
    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textArea.append(text + newline);
        textField.selectAll();
        ui.interpret(text);
        
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI(unownInterpreter UI) {
        //Create and set up the window.
        frame = new JFrame("TextDemo");
        frame.setUndecorated(true);
        frame.setSize(420, 280);
        //Add contents to the window.
        frame.add(new Console());
        
        ui=UI;
 
        //Display the window
        frame.setVisible(false);
    }
}