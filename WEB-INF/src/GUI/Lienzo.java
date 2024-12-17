package GUI;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

public class Lienzo extends Canvas
{

	private static final long serialVersionUID = 1L;
	
	private byte[] imagen;
	
	public Lienzo()
	{
		imagen = new byte[0];
	}
	
	public void setImagen(byte[] imagen)
	{
		this.imagen = imagen;
	}
	
	public void paint(Graphics g, int alt, int lar)
	{
		super.paint(g);
		
		Image img1 = Toolkit.getDefaultToolkit().createImage(imagen);
		Image img2 = img1.getScaledInstance(alt, lar, Image.SCALE_SMOOTH);
		
		if (img2 != null)
		{
			MediaTracker md = new MediaTracker(this);
			md.addImage(img2, 1);
			try 
			{
				md.waitForAll();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();			
			}
			g.drawImage(img2,0,0,this);
		}		
	}

}
