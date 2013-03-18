package main;

import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Result_Panel extends JPanel {
	public JTextArea jTextArea=new JTextArea(10,30);
	public JScrollPane jScrollPane=new JScrollPane(jTextArea);
	public JScrollPane pane=new JScrollPane(jTextArea);
    public Result_Panel()
    {
    	this.pane.setBounds(20, 20, 360, 360);
    	this.add(pane);
    	this.setBounds(40,100,400,400);
    	this.setVisible(true);
    	this.setBorder(BorderFactory.createTitledBorder("result"));
    	this.setLayout(null);
    }
}
