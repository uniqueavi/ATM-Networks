import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Switching implements ActionListener
{
	static JFrame jf;
	static JLabel jl1,jl2,jl3,jl4,jl5;
	static JTextField t1;
	static JButton ok,res,home;
	static int count=0;
	static Container c1;

	Switching()
	{
		Switch();
	}

	public void Switch()
	{
		jf = new JFrame("Switch");
		jl1=new JLabel("SWITCHING");
	 	jl2=new JLabel("1.Forwarding");
	 	jl3=new JLabel("2.Discarding");
	 	jl5=new JLabel("3.Filltering");
	 	jl4=new JLabel("Choose the Option:");
	 	t1= new JTextField(10);
		ok=new JButton("Ok");
		res=new JButton("Reset");
		home=new JButton("HOME");
		c1=jf.getContentPane();
		c1.setLayout(null);
	 	jl1.setFont(new Font("Times-Roman",Font.BOLD,28));
		c1.add(jl1);
		c1.add(jl2);
		c1.add(jl3);
		c1.add(jl4);
		c1.add(jl5);
		c1.add(t1);
		c1.add(ok);
		ok.addActionListener(this);
		c1.add(res);
	 	res.addActionListener(this);
	 	c1.add(home);
	 	home.addActionListener(this);
	 	jl1.setBounds(120,10,200,50);
		jl2.setBounds(100,60,80,25);
		jl3.setBounds(100,90,80,25);
		jl5.setBounds(100,120,80,25);
		jl4.setBounds(100,150,120,25);
		t1.setBounds(230,150,25,25);
		ok.setBounds(100,200,75,25);
		res.setBounds(175,200,75,25);
		home.setBounds(250,200,75,25);
		jf.setBounds(30,30,350,350);
		jf.setVisible(true);
	 	jf.setResizable(false);
	}


	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			try
			{
				count=Integer.parseInt(t1.getText());
			}
			catch(NumberFormatException nfe)
			{
				count=0;
			}
			switch(count)
			{
				case 1:
					Forwarding f = new Forwarding();
					break;
				case 2:
					Discarding d = new Discarding();
					break;
				case 3:
					
					Filtering fl=new Filtering();
					break;

				default:
				JOptionPane.showMessageDialog((Component) null,"Type Only Numbers Between or equal to 1 & 2","Click OK",JOptionPane.INFORMATION_MESSAGE);
				t1.setText("");
			}
		}

		else if(e.getSource()==home)
		{
			jf.setVisible(false);
			STP s = new STP();
		}

		else
		{
			t1.setText("");
		}
	}
}