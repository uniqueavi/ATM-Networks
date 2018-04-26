import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class Forwarding
{
	static String dest="";//giving static access modifier
	static String s="";
	static String sou="";
	static int dec=0;
	int c=0;
	static Enumeration e;
	static Enumeration e1;
	static Enumeration e2;
	static Vector souVec=new Vector();// declaring the vector
	static Vector sVec=new Vector();
	static Vector destVec=new Vector();
	static Listining l=new Listining("get");

	Forwarding()
	{
	 	try
	 	{
			Forward();
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog((Component) null,"There Is No Message To Forward","Click OK",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	Forwarding(String i)
	{

	}

    public void addVec(String sou,String s,String dest)
    {
		souVec.add(sou);
		sVec.add(s);
		destVec.add(dest);
		e=destVec.elements();
		e1=sVec.elements();
		e2=souVec.elements();
    }

	public boolean Forward()
	{
		System.out.println("c="+c);
		System.out.println("E="+e.hasMoreElements());
		
		while(e.hasMoreElements())
		{
			c=1;
			dest=(String) e.nextElement();
			s=(String) e1.nextElement();
			sou=(String) e2.nextElement();
			try
			{
				System.out.println("IN FORWARDING CLASS:"+dest);
				Socket soc=new Socket(dest,9898);//socket pgm for data transfer from router to destination 
	   			ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
	   			String all=sou+"`"+s;
	   			oos.writeObject(all);
	   			l.count1(++dec);
	   			oos.flush();
  		    	soc.close();
  		    	try
  		    	{
  		    		Thread.sleep(1000);
				}
				catch(Exception e){}
  		   	}
			catch(ConnectException e1)
			{
				JOptionPane.showMessageDialog((Component) null,dest +" Is Not Connected","Click OK",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(IOException e0)
			{JOptionPane.showMessageDialog((Component) null,dest +" Is Not Connected","Click OK",JOptionPane.INFORMATION_MESSAGE);}
		  }
		  if(c==0)
		  {
			  JOptionPane.showMessageDialog((Component) null,"There is No Message To Forward","Click OK",JOptionPane.INFORMATION_MESSAGE);
			  return false;
		  }
		  else
		  {
			  JOptionPane.showMessageDialog((Component) null,"Packets are Sucessfully Forwarded from Bridge to Destination","Click OK",JOptionPane.INFORMATION_MESSAGE);
			  destVec.removeAllElements();
			  sVec.removeAllElements();
		  	  souVec.removeAllElements();
			  return true;
		  }

	}
}