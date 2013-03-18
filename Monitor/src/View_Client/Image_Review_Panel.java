package View_Client;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image_Review_Panel extends JPanel {
	BufferedImage image;
	boolean flag=false;
	public Image_Review_Panel()
	{	
	}
public void draw(Graphics g)
{
	g.drawImage(image, 0,0,300,300,null);
	repaint();
}
   @Override
public void paintComponent(Graphics g) {
	// TODO Auto-generated method stub
	super.paintComponent(g);
	g.drawRect(0,0, 300, 300);
    if(flag)
    {
    	draw(g);
    }
}
}
