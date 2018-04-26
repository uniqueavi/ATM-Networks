import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Filtering
{
	Forwarding forw=new Forwarding("get");
	boolean dat=true;
	String s[];
	String s1[];
	String s2[];
	Filtering()
	{
		try
		{
		Filter();
		}
		catch(NullPointerException e)
		{
		}
	}

	public void Filter()
	{
		while(dat)
		{
			String str=JOptionPane.showInputDialog((Component) null,"Enter the SystemName :","Click OK",JOptionPane.INFORMATION_MESSAGE);
			if(str.length()>0)
			{
				int ln=forw.destVec.size();
				s=new String[ln];
				s1=new String[ln];
				s2=new String[ln];
				forw.destVec.copyInto(s);
				forw.sVec.copyInto(s1);
				forw.souVec.copyInto(s2);
				for(int i=(s.length-1);i>=0;i--)
				{
				/*	if(s2[i].equalsIgnoreCase(str))
					{
						forw.destVec.removeElementAt(i);
						forw.sVec.removeElementAt(i);
						forw.souVec.removeElementAt(i);
					}*/
					dat=false;

				}
				try
				{   
			   		
			   		//forw.addVec(s2,s1,s);
			   		forw.Forward();
				}
				catch(NullPointerException npe)
				{
					JOptionPane.showMessageDialog((Component) null,"There Is No Message To Filter","Click OK",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog((Component) null,"Input text is empty","Click OK",JOptionPane.INFORMATION_MESSAGE);
				dat=true;
			}
		}
	}
}






