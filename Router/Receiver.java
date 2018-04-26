import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Receiver implements Runnable //implemeting the algorithm
	{
	 Thread t1;
	 static double TAT=4.5;//theoritical analysis time
	  static int dat=0;
	 	 static int t=5;
	 	 static int I=0;//increment 
	 	 static int L=4;//limit
	 	 static int lct=5;//last compliance time
	 	 static int x=0;
	 	 static int cong=0;
	 	 static int flag=0;
	 static int fla=0;
	 static int in=0;
	 static String instring=" ";
	 static int length=0;
	 static int length1=0;
	 static String s=" ";
	 static String s1=" ";
	 static String dest=" ";
	 static String sou=" ";
	 static String con="";
	 Socket soc;
	 static String str="";
	 static String str1="";
	 char chstr[]=new char[512];
	 int readcnt=0;
	 static Block b=new Block();
	 static Listining l=new Listining();
	 static Learning L1=new Learning("get");
	 static Forwarding f=new Forwarding("get");
	 OutputStream out;
	 BufferedReader in1;

     Receiver()
       {

	   }

	 Receiver(Socket insocket1)
	   {
			t1=new Thread(this,"Receiver");
			soc=insocket1;
			try
	    	{
	  	   		in1= new BufferedReader(new InputStreamReader(soc.getInputStream()));
		   		out=soc.getOutputStream();
	      	}
	    	catch(IOException e)
			  {

	    	  }
			t1.start();
       }



         public void display()
	      {
		  while(true)
		 		  {
		 			try
		 			{
		           	while(true)
		 			{
		 				readcnt=in1.read(chstr);
		 				if(readcnt <=0)
		 			    {
		 				   continue;
		 				}
		 				else
		 				{
		 					break;
		 				}
		 			}

		 	        instring =new String(chstr, 0, readcnt);
					System.out.println("INSTRING IN RECEIVER CLASS :"+instring);
		 			if(!instring.endsWith("null"))
		 			{
		 			     length=instring.length();
				 	}
				 	else
				 	{
						length=(instring.length()-4);
					}
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
		 				 if( (!(str.equals(s.trim()))) || (!(str1.equals(sou.trim()))))
		 				 {
		 				 	b.add(s);
		 				 	instring="";
		 				 	l.count(++in);
		 				 	L1.print(sou,s,dest);
		 				 	f.addVec(sou,s,dest);
					 	 }
					 	 str=s.trim();
					 	 str1=sou;

		 	         }

		 		  	catch(IOException e1)
		 		    {}

		 		  	catch(NullPointerException e2)
		 		    {}
  	     	}
		}

     public void run()
	      {
		   //System.out.println("Enter one");
		   flag=0;
		   while(true)
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
			            { }
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
		   }
    }

