import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 class SouFrame implements ActionListener
 {
     static JButton jb1;
     static JButton jb2;
     static JLabel jl1,jl3,jl4;
	 static JLabel jl2;
	 static JTextField jtf;
	 static JTextArea jtf1;
	 static JScrollPane jsp;
	 static JFrame jf;
	 static int succ=0;
	 static double tot=0;          
	 boolean send=false;
	 String dest_addr=" ";
	 String Msg=" ";
	 String conc=" ";
	 String fin=" ";
	 String str=" ";
	 int st=0;
	 int clp=0;
	 int bclp=0;
	 int end=48;
	 int len1=0;
	 int split=0;
	 int count=1;
	 int check=0;
	 int num=2;
InetAddress in1=InetAddress.getLocalHost();//to get the localhost
	 
	 SouFrame() throws IOException
	        {
			 CreateFrame();
 	        }
	 public void CreateFrame()
	      {
		   jf=new JFrame("Source");
		   Container cp=jf.getContentPane();
		   jl1=new JLabel("Destination Machine Name :");
		   jl3=new JLabel(" ");
		   jl4=new JLabel(" ");
		   jtf=new JTextField();
		   cp.setBackground(Color.pink);
		   jl2=new JLabel("Type   the  Message              :");
		   jtf1=new JTextArea();
		   jb1=new JButton("SEND");
		   jb2=new JButton("CLEAR");
	       jsp=new JScrollPane(jtf1);
	       cp.add(jl1);
	       cp.add(jtf);
	       cp.add(jl2);
	       cp.add(jsp);
	       cp.add(jb1);
		   jb1.addActionListener(this);
		   cp.add(jb2);
	       jb2.addActionListener(this);
	       cp.add(jl4);
	       cp.add(jl3);
	       jl4.reshape(5,5,195,25);//to set boundry value in java 1.5 
	       jl1.reshape(35,50,195,25);
	       jtf.reshape(220,50,270,25);
	       jl2.reshape(35,100,195,25);
	       jsp.reshape(220,100,470,35);
	       jb1.reshape(200,170,75,25);
	       jb2.reshape(300,170,75,25);
	       jl3.reshape(200,200,150,150);
	       cp.repaint();
	       jf.setVisible(true);
	       jf.setBounds(0,0,700,250);
	       jf.validate();
	      }
	 public void actionPerformed(ActionEvent e)
	      {
           if(e.getSource()==jb1)
            {
	  	     try
			   {
			    SendPacket();
			   }
			 catch(IOException e1){}
		    }
		   else
		    {
             jtf.setText("");
             jtf1.setText("");
            }
          }
     public void  SendPacket() throws IOException
          {
	 	   try
		     {
		      System.out.println("**********************"+jtf.getText()+"****************");
              	Socket soc=new Socket("localhost",7700);//socket programming listening to 7700 port
  		      System.out.println("testing");
  		      OutputStream out  = soc.getOutputStream(); //o/p stream value
		      BufferedReader in2 = new BufferedReader(new InputStreamReader(soc.getInputStream()));//buffered reader 
			  st=0;
		      end=48;
		      dest_addr=jtf.getText(); //getting the destination address 
		      Msg=jtf1.getText(); //getting the message from user
		      conc= dest_addr+"`"+in1.getHostName()+"`";//concatination of destination address and localhost from inetaddress
			  byte buffer[]=Msg.getBytes(); //getting the message in byte format
		      int len=buffer.length;//getting the length of the buffer
		      len1=len;
		      tot=Math.ceil(len/48.0);// ceil value (i,e) factorial only
              if(len<=48)
		       {
		        fin=conc+Msg+"\n";// 
		        byte buffer1[]=fin.getBytes();
		        do
		         {
		          out.write(buffer1);//writing it in out buffer in router
		          succ++;
		          fin="null\n";
		      	  byte bufferf[]=fin.getBytes();
				  out.write(bufferf);// again writing the buffer in router
				  Thread.sleep(1000);
				  str=in2.readLine();// reading the content from the buffered reader
				  clp=Integer.parseInt(str);//counting the number of buffer
				  bclp=clp;
				   if(clp==0)
				   {
					JOptionPane.showMessageDialog((Component) null,"CONGESTION IS OCCURED WAIT UNTIL ROUTER IS CLEAR","Click OK",JOptionPane.INFORMATION_MESSAGE);
					jf.setEnabled(false);
		  		      str=in2.readLine();
					clp=Integer.parseInt(str);
				      succ--;
				   }
                 }while(clp==2 && bclp==0);
                if(succ==tot)
				 {
				  JOptionPane.showMessageDialog((Component) null,"ROUTER IS FREE & UR MESSAGE IS TRANSFER SUCCESSFULLY","Click OK",JOptionPane.INFORMATION_MESSAGE);
				  jf.setEnabled(true);
				  succ=0;
				 }
			   }
			  else
			   {
			    fin=conc+Msg.substring(st,end)+"\n";//defining the sub string
			    byte buffer2[]=fin.getBytes();
			    do
			     {
			      out.write(buffer2);
			      succ++;
			      str=in2.readLine();
			      clp=Integer.parseInt(str);
			      bclp=clp;

                  if(clp==0)
                   {
                    JOptionPane.showMessageDialog((Component) null,"CONGESTION IS OCCURED WAIT UNTIL ROUTER IS CLEAR","Click OK",JOptionPane.INFORMATION_MESSAGE);
            		jf.setEnabled(false);
                    str=in2.readLine();
                    clp=Integer.parseInt(str);
                   }
                  }while(clp==2 && bclp==0);
                if(succ==tot)
				 {
				  JOptionPane.showMessageDialog((Component) null,"ROUTER IS FREE & UR MESSAGE IS TRANSFER SUCCESSFULLY","Click OK",JOptionPane.INFORMATION_MESSAGE);
				  jf.setEnabled(true);
				  succ=0;
				 }
			    Thread.sleep(1000);
			    while(len1>48)//greater than 48 means
			        {
				     len1-=48; //len1=len1-48
			         if(len1<=48)//remainig will be placed in len1 same process is carried out but in different buffer
			          {
			           fin=conc+Msg.substring(end,len)+"\n";
			           byte buffer3[]=fin.getBytes();
			           do
			            {
					     out.write(buffer3);
					     succ++;
			             fin="null\n";
					     byte bufferf4[]=fin.getBytes();
					     out.write(bufferf4);
					     str=in2.readLine();
					     clp=Integer.parseInt(str);
					     bclp=clp;

			             if(clp==0)
						  {
						   JOptionPane.showMessageDialog((Component) null,"CONGESTION IS OCCURED WAIT UNTIL ROUTER IS CLEAR","Click OK",JOptionPane.INFORMATION_MESSAGE);
				           jf.setEnabled(false);
						   str=in2.readLine();
						   clp=Integer.parseInt(str);
						   succ--;
				  	      }
                     	 }while(clp==2 && bclp==0);
                       if(succ==tot)
				        {
				         JOptionPane.showMessageDialog((Component) null,"ROUTER IS FREE & UR MESSAGE IS TRANSFER SUCCESSFULLY","Click OK",JOptionPane.INFORMATION_MESSAGE);
				         jf.setEnabled(true);
				         succ=0;
				        }
			           Thread.sleep(1000);
			          }
			         else
			          {
			           split=end+48;
			           fin=conc+Msg.substring(end,split)+"\n";
			           end=split;
			           byte buffer5[]=fin.getBytes();
			           do
			            {
			             out.write(buffer5);
			             succ++;
			             str=in2.readLine();
			             clp=Integer.parseInt(str);
			             bclp=clp;

			             if(clp==0)
						  {
						   JOptionPane.showMessageDialog((Component) null,"CONGESTION IS OCCURED WAIT UNTIL ROUTER IS CLEAR","Click OK",JOptionPane.INFORMATION_MESSAGE);
						   jf.setEnabled(false);
						   str=in2.readLine();
						   clp=Integer.parseInt(str);
					      }
						 }while(clp==2 && bclp==0);
					   if(succ==tot)
				        {
				         JOptionPane.showMessageDialog((Component) null,"ROUTER IS FREE & UR MESSAGE IS TRANSFER SUCCESSFULLY","Click OK",JOptionPane.INFORMATION_MESSAGE);
				         jf.setEnabled(true);
				         succ=0;
				        }
			           Thread.sleep(1000);
			          }
			        }
			     }
			  //soc.close();
			 }
		   catch(UnknownHostException e){

			   e.printStackTrace();
			   System.out.println("UHE" + e);}
	   	   catch(InterruptedException e1){}
		  }
    }
class Source
    {
     public static void main(String args[])throws IOException
          {
           SouFrame sf=new SouFrame();
          }
     }
