
package network;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class SplashWindow extends JWindow implements Runnable{ 
	Thread t;
  //********************************************************************
  /**
   * ���췽��
   *
   * @param  imagePath   ����·��
   * @param  argFrame    �Ĵ���
   * 
   */
  //********************************************************************
  public SplashWindow(String imagePath, JFrame argFrame){
	
    super(argFrame);
    try{
    	JLabel l = new JLabel(new ImageIcon(SplashWindow.class.getResource(imagePath)));
    getContentPane().add(l, BorderLayout.CENTER);
    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension labelSize = l.getPreferredSize();

    setLocation(screenSize.width / 2 - (labelSize.width / 2),screenSize.height / 2 - (labelSize.height /2));

    }catch(Exception e){
    	e.printStackTrace();
    }
    setVisible(true);
    t=new Thread(this);
    t.start();
  }
  public void run(){
	  try{
		  t.sleep(5000);
	  }catch(InterruptedException ine){
		  ine.printStackTrace();
	  }
	  finally{
		  setVisible(false);
	  }
  }
  //********************************************************************
  /**
   * �ر���ʾ����
   * 
   */
  //********************************************************************
  public void close() {
    setVisible(false);
  }
}