import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Block implements ActionListener
{

	static JFrame jf,jf1;
	static JLabel jl,mess;
	static String text="";
	static JTextArea jta;
	static JButton view,close,exit;
	static Container c1,c2;


	Block()
	{

	}

	public void Blofir()
	{
		//System.out.println("Blofir");
		jf=new JFrame("BLOCK");
		c1=jf.getContentPane();
		jl=new JLabel("Message Is Blocked");
		view=new JButton("View Details");
		close=new JButton("Close");
		jl.setFont(new Font("Times-Roman",Font.BOLD,28));
		jl.setForeground(Color.red);
		c1.setLayout(null);
		c1.add(jl);
		c1.add(view);
		c1.add(close);
		view.addActionListener(this);
		close.addActionListener(this);
		jl.setBounds(25,100,375,50);
		view.setBounds(50,225,125,25);
		close.setBounds(200,225,75,25);
		jf.setBounds(250,50,350,350);
		jf.setVisible(true);
		jf.setResizable(false);
	}

	public void add(String s)
	{
		text+=s+"\n";
	    jta.setText(text);
	}
	public void Blosec()
	{
		jf1=new JFrame("BLOCK");
		c2=jf1.getContentPane();
		mess=new JLabel("Message in RootBridge:");
		jta=new JTextArea(10,5);
		jta.setEnabled(false);
		JScrollPane jsp=new JScrollPane(jta);//defining the scrollpane
		exit=new JButton("Exit");
		c2.setLayout(null);
		c2.add(mess);
		c2.add(jsp);
		c2.add(exit);
		exit.addActionListener(this);
		mess.setBounds(15,15,155,50);
		jsp.setBounds(170,20,500,500);
		exit.setBounds(225,550,75,25);
		jf1.setBounds(50,15,700,710);
 	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==view)
		{
			jf.setVisible(false);
			jf1.setVisible(true);
		}
	    else if(e.getSource()==close)
		{
			jf.setVisible(false);
			STP s=new STP();
		}
	   if(e.getSource()==exit)
		{
			jf1.setVisible(false);
			STP s=new STP();

		}
	}
}