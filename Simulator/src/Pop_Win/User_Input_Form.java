package Pop_Win;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.IO_Instructions;
import main.Instructions;
import main.Simulator_View;
public class User_Input_Form extends JDialog implements ActionListener,WindowListener {
	public JButton sure=new JButton("OK");
	public JTextField user_input=new JTextField();
	public JLabel jLabel=new JLabel("please input your number or char:");
    public User_Input_Form()
    {
    	this.addWindowListener(new WindowAdapter(){
    		   public void windowClosing(WindowEvent e) {
    			   IO_Instructions.user_input="0";
    		    dispose();
    		            }
    		  });
    	this.setLayout(null);
    	this.setModal(true);
    	this.sure.setBounds(200, 100, 100, 30);
    	this.jLabel.setBounds(20, 40, 200, 20);
    	this.user_input.setBounds(10, 100,160, 30);
    	this.sure.addActionListener(this);
    	this.add(sure);
    	this.add(jLabel);
    	this.add(user_input);
    	this.setTitle("user input");
    	this.setSize(400, 300);
    	this.setLocation(200,50);
    	this.setVisible(true);
    	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==sure)
		{
			IO_Instructions.user_input=user_input.getText();
			this.dispose();
		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		 IO_Instructions.user_input="0";
		 System.out.println("dialog closed");
		    dispose();
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
