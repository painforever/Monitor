package View_Server;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image_Part_Panel  extends JPanel{
	boolean flag=false;
	BufferedImage image;
	public Image_Part_Panel()
	{
		try {
			image=ImageIO.read(new File("D:/wo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void draw(Graphics g)
	{
		g.drawImage(image, 450,10,100,100,null);
		repaint();
	}
	   @Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		draw(g);
	}
}
