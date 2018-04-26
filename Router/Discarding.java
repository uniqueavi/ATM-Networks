import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Discarding
{
	Forwarding forw=new Forwarding("get");

	Discarding()
	{
		Discard();
	}

	public void Discard()// discarding the vector 
	{
		forw.destVec.removeAllElements();
		forw.sVec.removeAllElements();
		forw.souVec.removeAllElements();
		JOptionPane.showMessageDialog((Component) null,"Packets are Discarded at Bridge","Click OK",JOptionPane.INFORMATION_MESSAGE);
	}
}
