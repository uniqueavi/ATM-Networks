import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.*;

class Learning implements ActionListener
	{
	 static JFrame jf;
	 static JTextArea jta;
	 static JButton jb;
	 static Container cp;
	 static String text="";
	 DefaultTableModel dft=new DefaultTableModel();
	 JTable jt=new JTable(dft);

     Learning()
       {

	   }
	 Learning(String s)
	 {
		Learn_Frame();
	 }

	 public void print(String sou,String s,String dest)
	 	  {
		   Vector get=new Vector();
		   get.add(sou);
		   get.add(dest);
		   get.add(s.trim());
		   dft.addRow(get);
		  }

	 public void Learn_Frame()
          {
		   jf =new JFrame("Details");
	       jb=new JButton("Close");
	       jta=new JTextArea();
	       jta.setEnabled(false);
	       dft.addColumn("Source");
	       dft.addColumn("Destination");
	       dft.addColumn("Message");
	       JScrollPane jsp=new JScrollPane(jt);
	       cp=jf.getContentPane();
	       cp.setLayout(null);
		   cp.add(jb);
		   jb.addActionListener(this);
		   cp.add(jsp);

	       jsp.reshape(5,5,400,400);
	       jb.reshape(200,425,100,25);
	       jf.setBounds(40,15,500,500);
	       jf.setResizable(false);
	      }
	 public void actionPerformed(ActionEvent e)
	 {
		 if(e.getSource()==jb)
		 {
			jf.setVisible(false);
			STP s=new STP();
		 }
	 }
 }
