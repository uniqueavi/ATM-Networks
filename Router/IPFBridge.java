import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

class STP implements ActionListener
{
	static JFrame jf;
	static JLabel lm,lb,ll,lL,ls,lt;
	static JTextField t1;
	static JButton ok,res;
	static int count=0;
	static Container c1;
	static int i;
	Listining l;

	STP()
	{
	 menu();
	}
	public void menu()
	{
	 jf = new JFrame("MENU");
	 lm=new JLabel("MAIN MENU");
	 lb=new JLabel("1.Blocking");
	 ll=new JLabel("2.Listening");
	 lL=new JLabel("3.Learning");
	 ls=new JLabel("4.Switching");
	 lt=new JLabel("Select the Option:");
	 t1=new JTextField(10);
	 ok=new JButton("Ok");
	 res=new JButton("Reset");
	 c1=jf.getContentPane();
	 c1.setLayout(null);
	 lm.setFont(new Font("Times-Roman",Font.BOLD,28));
	 c1.add(lm);
	 c1.add(lb);
	 c1.add(ll);
	 c1.add(lL);
	 c1.add(ls);
	 c1.add(lt);
	 c1.add(t1);
	 c1.add(ok);
	 ok.addActionListener(this);
	 c1.add(res);
	 res.addActionListener(this);
	 lm.setBounds(110,10,200,50);
	 lb.setBounds(100,60,80,25);
	 ll.setBounds(100,90,80,25);
	 lL.setBounds(100,120,80,25);
	 ls.setBounds(100,150,80,25);
	 lt.setBounds(100,190,120,25);
	 t1.setBounds(230,190,25,25);
	 ok.setBounds(150,270,75,25);
	 res.setBounds(225,270,75,25);
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
			           jf.setVisible(false);
			           Block b=new Block();
			           b.Blofir();
			           break;
				case 2:
				  	   jf.setVisible(false);
					   l=new Listining("get");
				  	   l.jf.setVisible(true);
				       break;
				case 3:
				  	   jf.setVisible(false);
				  	   Learning L=new Learning();
				  	   try
				  	   {
				  	   	L.jf.setVisible(true);
				   	   }
				   	   catch(NullPointerException npe)
				   	   {
						JOptionPane.showMessageDialog((Component) null,"There Is No Message To Learn","Click OK",JOptionPane.INFORMATION_MESSAGE);
						jf.setVisible(true);
					   }
				  	   break;
				case 4:

				       jf.setVisible(false);
				       Switching s=new Switching();
				       break;
				default:
				    	JOptionPane.showMessageDialog((Component) null,"Type Only Numbers Between or equal to 1 & 4","Click OK",JOptionPane.INFORMATION_MESSAGE);
				 		t1.setText("");
			   }
		  }
		 else
		  {
		   t1.setText("");
		  }
     }
}

class Login implements ActionListener
{
	boolean ch=false;
	JFrame jf;
	JTextField txtuser;
	JPasswordField txtpass;
	JButton submit;
	JButton reset;
	Login()
	{
		jf=new JFrame("Login");
		JLabel user=new JLabel("Name 	 :");
		JLabel pass=new JLabel("Password :");
		txtuser=new JTextField(10);
		txtpass=new JPasswordField(10);
		submit=new JButton("Submit");
		reset=new JButton("Reset");
		Container c=jf.getContentPane();
		c.setLayout(new GridLayout(3,2));
		c.add(user);
		c.add(txtuser);
		c.add(pass);
		c.add(txtpass);
		c.add(submit);
		submit.addActionListener(this);
		c.add(reset);
		reset.addActionListener(this);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jf.setResizable(false);
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==submit)
		{
			String pass="";
			char c[]=txtpass.getPassword();
			for(int i=0;i<c.length;i++)
			{
				pass+=c[i];
			}
			if( (((txtuser.getText()).trim()).equalsIgnoreCase("admin")) && ((pass.trim()).equalsIgnoreCase("admin")))//string comparison for user validation
			{
				jf.setVisible(false);
				ch=true;
			}
			else
			{
				JOptionPane.showMessageDialog((Component) null,"User Name & Password Is Invalid","Click OK",JOptionPane.INFORMATION_MESSAGE);
				ch=false;
			}
		}
		else
		{
			txtuser.setText("");
			txtpass.setText("");
		}
	}
}

class IPFBridge extends Thread
{
	IPFBridge()
	{
		start();
	}
	public void run()
	{
     	Login c=new Login();
     	while(true)
     	{
     		while(c.ch)
     		{
     			try
     			{
					ServerSocket socket = new ServerSocket(7788);//server socket listening the port 7788
					STP s = new STP();
					Block b1=new Block();
					b1.Blosec();
					while(true)
					{
						//System.out.println("waiting");
						Socket insocket1 = socket.accept();
					    //System.out.println("connected");
					    Receiver g1=new Receiver(insocket1);// calling the receiver class
					}
				}
				catch(UnknownHostException e1)
				{}
				catch(IOException e2)
				{}
			}
		}
	}
}



