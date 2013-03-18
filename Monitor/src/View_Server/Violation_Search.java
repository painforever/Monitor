package View_Server;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import BLL_Server.Server_Main_BLL;

public class Violation_Search extends JPanel implements MouseListener {
	//bll对象
	Server_Main_BLL server_main_bll=new Server_Main_BLL();
    JPanel Search_Method_Panel=new JPanel();
    JPanel violation_Car_List_Panel=new JPanel();
    //子组件
    JLabel search_content_label=new JLabel("please input the search key:");
    JTextField search_key_textfield=new JTextField();
    JButton plate_search_button=new JButton("according to plate");
    JButton identify_cart_search_button=new JButton("according to ID card");
    JButton name_search_button=new JButton("according to name");
    //表格的定义
    JTable table;
	Object table_data[][]={};
	JScrollPane jScrollPane;
	Object column_name[]={"car owner name","ID number","plate number","violate time","sex","address","contact"}; //table里的列名
	JLabel plate_number_lable=new JLabel("plate number:");
	JTextField plate_number_textfield=new JTextField();
	JButton update_button=new JButton("update");
	JButton delete_button=new JButton("delete");
	public Violation_Search()
	{
		this.setLayout(null);
		//设置表格
		table=new JTable(table_data,column_name);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,400,400);
		jScrollPane.setBounds(15,18,370,500);
		this.Search_Method_Panel.setLayout(null);
		this.violation_Car_List_Panel.setLayout(null);
	    Search_Method_Panel.setBounds(10,10,320,750);
	    violation_Car_List_Panel.setBounds(350,10,400,750);
	    search_content_label.setBounds(30,20,160,40);
	    search_key_textfield.setBounds(30,80,160,40);
	    plate_search_button.setBounds(30,150,160,30);
	    identify_cart_search_button.setBounds(30,200,160,30);
	    name_search_button.setBounds(30,250,160,30);
	    plate_number_lable.setBounds(30,540,130,30);
	    plate_number_textfield.setBounds(180,540,160,30);
	    update_button.setBounds(30,600,130,30);
	    delete_button.setBounds(200,600,130,30);
	    Search_Method_Panel.setBorder(BorderFactory.createTitledBorder("search method"));
	    violation_Car_List_Panel.setBorder(BorderFactory.createTitledBorder("car list"));
	    this.add(Search_Method_Panel);
	    this.add(violation_Car_List_Panel);
	    Load_All_Violation_Holder();
	    
	    //添加子组件
	    this.Search_Method_Panel.add(search_content_label);
	    this.Search_Method_Panel.add(search_key_textfield);
	    this.Search_Method_Panel.add(plate_search_button);
	    this.Search_Method_Panel.add(identify_cart_search_button);
	    this.Search_Method_Panel.add(name_search_button);
	    this.violation_Car_List_Panel.add(jScrollPane);
	    this.violation_Car_List_Panel.add(plate_number_lable);
	    this.violation_Car_List_Panel.add(plate_number_textfield);
	 //   this.violation_Car_List_Panel.add(update_button);
	    this.violation_Car_List_Panel.add(delete_button);
	    //添加监听
	    this.plate_search_button.addMouseListener(this);
	    this.identify_cart_search_button.addMouseListener(this);
	    this.name_search_button.addMouseListener(this);
	    this.delete_button.addMouseListener(this);
		this.setSize(760, 820);
		this.setVisible(true);
	}
	public void Load_All_Violation_Holder()
	{
		String[][] all_holders=server_main_bll.Load_All_Violation_Holder();
		this.table_data=all_holders;
		Reload_Table();
	}
	//重新加载表格
    private void  Reload_Table() 
    {
    	this.remove(table);
		this.remove(jScrollPane);
		//设置表格
    	table=new JTable(table_data,column_name);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,400,400);
		jScrollPane.setBounds(15,18,370,500);
		this.table.addMouseListener(this);
		this.violation_Car_List_Panel.add(jScrollPane);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0,0, 760, 820);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==plate_search_button)
		{
			//System.out.print(this.search_key_textfield.getText()+"sdjksfjsljk");
			Search("plate_number",this.search_key_textfield.getText());
		}
		else if(e.getSource()==identify_cart_search_button)
		{
			Search("ID_Number", this.search_key_textfield.getText());
		}
		else if(e.getSource()==name_search_button)
		{
			Search("holder_name", this.search_key_textfield.getText());
		}
		else if(e.getSource()==delete_button)
		{
			//System.out.println("delete");
			String result=server_main_bll.Delete_Violation_Brand(this.plate_number_textfield.getText());
			Load_All_Violation_Holder();
			Reload_Table();
		}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    //总查询方法,内部根据情况调用三个方法
	public String[][] Search(String according,String keyword)
	{
		if(according=="plate_number")
		{
			String result[][]=server_main_bll.Search_Through_Keyword(according,keyword);
			this.table_data=result;
			Reload_Table();
		}
		else if(according=="ID_Number")
		{
			String result[][]=server_main_bll.Search_Through_Keyword(according,keyword);
			this.table_data=result;
			Reload_Table();
		}
		else if(according=="holder_name")
		{
			String result[][]=server_main_bll.Search_Through_Keyword(according,keyword);
			this.table_data=result;
			Reload_Table();
		}
		return null;
	}
}
