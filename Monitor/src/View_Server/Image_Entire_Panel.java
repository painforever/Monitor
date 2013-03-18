package View_Server;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image_Entire_Panel extends JPanel implements MouseListener,MouseMotionListener{
	int drag_status=0,c1,c2,c3,c4;
	BufferedImage image;
	boolean flag=false;
	public Image_Entire_Panel()
	{
		addMouseListener(this);
		addMouseMotionListener( this );
	}
	
public void draw(Graphics g)
{
	if(image!=null)
	{
		g.drawImage(image, 0,0,image.getWidth(),image.getHeight(),null);
	}
	repaint();
}
   @Override
public void paintComponent(Graphics g) {
	super.paintComponent(g);
    draw(g);
}
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
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
	repaint();
	c1=e.getX();
	c2=e.getY();
}
@Override
public void mouseReleased(MouseEvent e) {
	repaint();
	if(drag_status==1)
	{
		c3=e.getX();
	    c4=e.getY();
    try
	{
	   draggedScreen();
	}
	catch(Exception e1)
	{
	   e1.printStackTrace();
	}
	}
}
@Override
public void mouseDragged(MouseEvent e) {
	repaint();
	drag_status=1;
	c3=e.getX();
	c4=e.getY();
}
@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void paint(Graphics g) {
	super.paint(g);
	int w = c1 - c3;
	int h = c2 - c4;
	w = w * -1;
	h = h * -1;
	if(w<0)
	w = w * -1;
	g.drawRect(c1, c2, w, h);
}
public void draggedScreen()throws Exception  {
	int w = c1 - c3;
	int h = c2 - c4;
	w = w * -1;
	h = h * -1;
//	Robot robot = new Robot();
//	BufferedImage img = robot.createScreenCapture(new Rectangle(c1, c2,w,h));
//	File save_path=new File("screen1.jpg");
//	ImageIO.write(img, "JPG", save_path);
	try {
		BufferedImage new_image=this.image.getSubimage(c1, c2, w, h);
		File save_path=new File("temp.jpg");
		ImageIO.write(new_image,"JPG",save_path);
		System.out.println("Cropped image saved successfully.");
//        	String image_path=new File("").getAbsolutePath()+"\\temp.jpg";
//			BufferedImage temp_image=ImageIO.read(new File(image_path));
//			this.image_part_panel.image=temp_image;
//			this.image_part_panel.flag=true;
//			this.image_part_panel.repaint();
//			System.out.println("paint hahaha");
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	}
}
