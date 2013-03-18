package View_Server;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionListener;

public class Server_Main extends JFrame implements MouseListener,MouseMotionListener,ActionListener {
	JTabbedPane jTabbedPane=new JTabbedPane();
	//这两个panel是分别是三个标签
	Image_Analyze imageAnalyze=new Image_Analyze();
	Violation_Search violationSearch=new Violation_Search();
	User_Management userManagement=new User_Management();
    public Server_Main()
    {
    	this.setTitle("Server");
    	this.setLocation(200,200);
    	this.setSize(800, 900);
        this.setLayout(null);
        this.jTabbedPane.setBounds(10, 10, 760, 820);
        this.jTabbedPane.addTab("Image Analyze", imageAnalyze);
        this.jTabbedPane.addTab("Violation Search", violationSearch);
        this.jTabbedPane.addTab("User Management", userManagement);
        this.add(jTabbedPane);
        
    	
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
    public static void main(String[] args) {
		new Server_Main();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
