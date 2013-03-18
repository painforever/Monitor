package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sun.java2d.pipe.SpanClipRenderer;
public class Simulator_View extends JFrame implements ActionListener{
	public boolean start_flag=false;
	public JButton start=new JButton("start");
	public JFileChooser fileChooser=new JFileChooser();
	public JButton selectButton=new JButton("chose intructions");
	public JTextField jTextField=new JTextField("input your number!");
	public JButton sure=new JButton("OK");
	public JButton clean_ram=new JButton("clean RAM");
	public static Result_Panel result_Panel=new Result_Panel();
	public Simulator_View()
	{
		this.setLayout(null);
		//set and add components
		this.start.setBounds(450,50 ,200, 30);
		this.jTextField.setBounds(200, 550, 200, 40);
		this.sure.setBounds(410, 550, 100, 40);
		this.selectButton.setBounds(450, 100, 200, 30);
		this.clean_ram.setBounds(450,150,200,30);
		this.selectButton.addActionListener(this);
		this.clean_ram.addActionListener(this);
		this.add(clean_ram);
		this.start.addActionListener(this);
		this.add(start);
	//	this.add(sure);
	//	this.add(jTextField);
		this.add(selectButton);
		this.add(result_Panel);
		this.result_Panel.jTextArea.setText("");
		this.setSize(700,700);
		this.setLocation(200, 50);
		this.setTitle("computer simulator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Simulator_View();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start)
		{
			start_flag=true;
		}
		else if(e.getSource()==selectButton)
		{
			if(!start_flag)
				return;
			int result=fileChooser.showOpenDialog(this);
			if(result==0&&fileChooser.getSelectedFile().getAbsolutePath()!=null&&fileChooser.getSelectedFile().getAbsolutePath()!="")
			{
				String file_path=fileChooser.getSelectedFile().getAbsolutePath();
				File file=new File(file_path);
				StringBuffer sb=null;
				String temp=null;
				try {
					BufferedReader br=new BufferedReader(new FileReader(file));	
					sb=new StringBuffer();
					try {
						temp=br.readLine();
						int index=0;
						while(temp!=null)
						{
							sb.append(temp+"\n");
							char temp_char_array[]=temp.toCharArray();
							for (int i = 0; i < temp_char_array.length; i++) {// the length must be 16-bit length
								int temp_int=Integer.parseInt(temp_char_array[i]+"");
								Analyze_Instructions.memory[index][i]=temp_int;//assign instructions
							}
							index++;
							temp=br.readLine();
						}
						br.close();
					} catch (IOException e1) {e1.printStackTrace();}	
				} catch (FileNotFoundException e1) {e1.printStackTrace();}
				Analyze_Instructions.Decode_Opcode(Analyze_Instructions.memory, 0);
			}
		}
		else if(e.getSource()==clean_ram)
		{
			Analyze_Instructions.Clean_RAM();
			JOptionPane.showConfirmDialog(this, "RAM has been cleaned!");
			System.out.println("RAM has been cleaned!");
		}
	}

}
