import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


class Listining implements Runnable,ActionListener// interface 
    {
	 String Monlab="Listening";
     int count=0;
     Thread t3;
     JFrame jf;
     Container cp;
     JLabel jl,in,out;
     static int i,j;
     static JTextField tin;
     static JTextField tout;
     JButton jb;

     Listining()
       {
		dis_Fir_Frame();
	   }

	 Listining(String i)
	   {
		dis_Fir_Frame();
		t3=new Thread(this,"Mon");
	    t3.start();
	   }

	public void count(int i)
	{
		this.i=i;
	}

	public void count1(int j)
	{
		this.j=j;
	}

	public void dis_Fir_Frame()
         {
		  jf =new JFrame("Listening");
	      jl=new JLabel(Monlab);
	      in=new JLabel("Incoming Packets   :");
	      out=new JLabel("Outgoing Packets  :");
	      tin=new JTextField(20);
	      tout=new JTextField(20);
          jb=new JButton("Close");
          cp=jf.getContentPane();
          jl.setFont(new Font("Italic",Font.BOLD,40));
          cp.setLayout(null);
	      cp.add(jl);
		  cp.add(jb);
		  cp.add(in);
		  cp.add(out);
		  cp.add(tin);
		  cp.add(tout);
		  tin.setText(this.i+"");
		  tout.setText(this.j+"");
		  tin.setEnabled(false);
		  tout.setEnabled(false);
		  jb.addActionListener(this);
		  jl.reshape(20,10,500,100);
		  in.reshape(100,120,150,50);
		  out.reshape(100,160,150,50);
		  tin.reshape(230,135,50,25);
		  tout.reshape(230,175,50,25);
          jb.reshape(150,245,75,25);
          jf.setBounds(50,15,400,400);
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

     public void run()
          {
           while(true)
               {
				Monlab=Monlab+".";
                jl.setText(Monlab);
                count++;
                if(count==11)
                 {
                  Monlab="Listening";
                  count=0;
                 }
                else
                 {
                  try
                    {
				     t3.sleep(500);
                    }
                  catch(InterruptedException e)
                    {
                    }
                 }
              }
         }
    }