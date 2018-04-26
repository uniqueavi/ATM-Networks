import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Get implements Runnable
	{
	 static int dat=0;
	 static int t=5;
	 static int I=0;
	 static int L=4;
	 static int lct=5;
	 static int x=0;
	 static int cong=0;
	 static int flag=0;
	 static int fla=0;
	 Thread t1;
	 static double TAT=4.5;
	 static String instring=" ";
     static int length=0;
	 static int length1=0;
	 static String s="";
	 static String s1="";
	 static String dest="";
	 static String sou="";
	 Socket soc;
	 String fin=" ";
     OutputStream out;
     BufferedReader in1;
	 char chstr[]=new char[512];
	 int readcnt=0;
	 Object[] data=new Object[5];
	 static String cap[]=new String[10];
	 static String text;
	 static String text1;


     Get()
       {
	   }

	 Get(Socket insocket1)
	   {
		t1=new Thread(this,"Get");
		soc=insocket1;
		try
	      {
	  	   in1= new BufferedReader(new InputStreamReader(soc.getInputStream()));
		   out=soc.getOutputStream();
	      }
	    catch(IOException e)
		  {  }
		t1.start();
		LBA l=new LBA(soc);
      }

	  public void display()
	      {
		   try
	         {
			  while(true)
			  	  {
		           readcnt=in1.read(chstr);
		           if(readcnt <=0)
		             continue;
		           else
		             break;
		  		  }
              instring =new String(chstr, 0, readcnt);
              if(!instring.endsWith("null") && (!(instring.equals("null"))))
		       {
				//System.out.println("dat :"+dat);
				length=instring.length();
		        length1=0;
		        for(int l=0;l<length;l++)
		          {
		           if((instring.charAt(l))=='`')
		            {
		             dest=instring.substring(0,l);
		             s1=instring.substring(l+1,length);
		             l=length+1;
		             length1=s1.length();
		            }
		          }
		        for(int l=0;l<length1;l++)
			      {
			       if((s1.charAt(l))=='`')
			        {
			         sou=s1.substring(0,l);
			         s=s1.substring(l+1,length1);
			         l=length1+1;
			        }
			      }
			    cap[dat]=instring.trim();
                dat++;
			    fin="1\n";
			    byte buffer1[]=fin.getBytes();
      		    out.write(buffer1);
      		   }
		   }
		  catch(IOException e){}
		}

     public void run()
	      {
		   //System.out.println("Enter one");
		   flag=0;
		   while(true)
	           {
				if(I!=L)
				 {
				  if(TAT<t)
		           {
				    TAT=t;
		            TAT+=I;
		            try
		              {
					   display();
					   flag=1;
					   I++;
		              }
		            catch(NullPointerException e)
			            {}
		           }
	              else if(TAT>(t+L))
		    	        {
					     TAT-=I;
			             t-=5;
			            }
			           else
			            {
					     TAT+=I;
			             display();
			             flag=1;
			             I++;
			            }
			     t=t+5;
			    }
			   if(I==L)
			    {
			  	  fla=1;
			    }
			   if(fla==1 && cap[0]!=null)
			    {
			     fin="0\n";
			     cong=1;
			     byte buffer1[]=fin.getBytes();
			     fla=0;
			     try
			       {
			        out.write(buffer1);
			       }
			     catch(IOException e)  {}
			    }
               try
				 {
				  t1.sleep(1000);
				 }
			   catch(InterruptedException e){}
			  //System.out.println("end1");
			}
	   }
    }

class LBA extends Get implements Runnable
    {
	 Thread	 tv;
	 String des;
	 String str;
	 Socket soc1;
	 OutputStream out1;
	 int f;
     LBA(Socket soc2)
       {
		soc1=soc2;
		try
		  {
		   out1=soc1.getOutputStream();
	      }
	    catch(IOException e){}
		tv=new Thread(this);
		tv.start();
       }
     public void send()
          {
		   if(cap[0]!=null)
		    {
		     int len=cap[0].length();
		     for(int l=0;l<len;l++)
		       {
		        if((cap[0].charAt(l))=='`')
		         {
		          des=cap[0].substring(0,l);
		          str=cap[0].substring(0,len);
		          l=len+1;
		         }
			   }
			for(int sw=0;sw<cap.length;sw++)
			  {
			   String swap=cap[sw+1];
			   if(swap!=null)
			    {
			     cap[sw]=swap;
			    }
			   else
			     {
				  cap[sw]=null;
				  dat=sw;
				  break;
			     }
			   }
			  try
		       {
                Socket sen=new Socket("localhost",7788);
  	            OutputStream oos=sen.getOutputStream();
  	            byte b1[]=str.getBytes();
  		        oos.write(b1);
  		        oos.flush();
  		        sen.close();
  		        Thread.sleep(500);
  		       }
		      catch(Exception e)
				 {}

		     }

	      }
     public void run()
	      {
		   //System.out.println("Enter two");
		   while(true)
		       {
				if(flag==1)
		         {
		          if(cap[0]!=null)
		           {
		            while(I>0)
		                {
			         	 f=x-(t-lct);
                         if(f<0)
                          {
			               f=0;
                           x=f+I;
                           lct=t;
                           send();
                           flag=0;
                           I--;
                          }
                         else if(f>L)
                               {
                                x=f-I;
                               }
                              else
                               {
			                    x=f+I;
                                lct=t;
                                send();
                                flag=0;
                                I--;
                               }
                        }
                   //System.out.println("oneu anniea"+cong);
                   if(cong==1)
                    {
			         cong=0;
			         fin="2\n";
			         byte buffer11[]=fin.getBytes();
			         //I=0;
			         try
			           {
			  	        out1.write(buffer11);
				       }
			         catch(IOException e)  {}
                   }
                }


			   }
              try
			    {
			     tv.sleep(5000);
			    }
			  catch(InterruptedException e)
			    {}
                //System.out.println("end");
             }

        }
    }




class Router
    {
     public static void main(String args[]) throws IOException,InterruptedException
	  {
	   System.out.println("******************************ROUTER********************************************");
	   try
	     {
	      ServerSocket socket = new ServerSocket(7700);
	      IPFBridge ip=new IPFBridge();
	      while(true)
	          {
			   System.out.println("waiting");
			   Socket insocket1 = socket.accept();
	           System.out.println("connected");
	           Get g1=new Get(insocket1);
	          }
	     }
	   catch(UnknownHostException e)
	     {
	      System.out.println("UHE");
	     }
	  }
    }

