import java.applet.Applet;
import java.awt.*;
import java.util.Date;

public class DateTimeApp extends Applet implements Runnable
{
	Thread th;
	public void init()
	{
		th = new Thread(this); 
		th.start();
	}
	public void paint (Graphics g)
	{
		Date d = new Date();
		g.drawString(d.toString(), 50, 10);
	}
	public void run ( ) 
	{
		while(true)
		{
			repaint( );
			try
			{
				Thread.sleep(1000) ;
			}
			catch(Exception ex)
				{
				System.out.println("error");
				}
		}
	}
}