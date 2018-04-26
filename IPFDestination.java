import java.net.*;
import java.io.*;

public class IPFDestination
    {
     public static void main(String args[]) throws IOException
       {
	    System.out.println("******************************DESTINATION***************************************");
	    String instring=" ";
        String s=" ";
        int length=0;
        int sublen=0;
	    String dest=" ";
	    try
          {
	       ServerSocket sock1 = new ServerSocket(9898);//server socket listening the 9898 port
	       while(true)
	           {
	   	        System.out.println("waiting..........");
                System.out.println("testing");
                Socket insocket1 = sock1.accept();
                System.out.println("connected sucessfully.............");
                try
                  {
                   ObjectInputStream ois=new ObjectInputStream(insocket1.getInputStream());
                   instring=(String) ois.readObject();
			      }
			    catch(ClassNotFoundException e){}
			    length=instring.length();
			    for(int l=0;l<length;l++)
                  {
			       if((instring.charAt(l))=='`')
                    {
			         sublen=l;
                     dest=instring.substring(0,l);
                     s=instring.substring(l+1,length);
                     l=length+1;
                    }
                  }
                byte data[]=s.getBytes();
          	    FileOutputStream fos=new FileOutputStream(dest+".txt",true);
                fos.write(data);
                Runtime r = Runtime.getRuntime();
		        Process p = null;
                p = r.exec("notepad"+" "+dest+".txt");
               }
	       }
        catch(UnknownHostException e){System.out.println("UHE");}
          }
        }