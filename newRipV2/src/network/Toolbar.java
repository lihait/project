/**
 * 
 */
package network;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


/**
 * 主界面控件实现
 * @author lihaitao
 * 
 *
 */
public class Toolbar extends JPanel {
	private JToggleButton  normal,nodeBtn,linkBtn,netBtn,distroy,begin;
 
	private ButtonGroup group;
	private ArrayList tools;

	private static final int BUTTON_SIZE = 25;
	private static final int OFFSET = 4;	
	
	public Toolbar(){
		init();
	}
	
	private void init(){
		group = new ButtonGroup();
	    tools = new ArrayList();
/*
	      normal = new JToggleButton(new 
	         Icon()
	         {
	            public int getIconHeight() { return BUTTON_SIZE; }
	            public int getIconWidth() { return BUTTON_SIZE; }
	            public void paintIcon(Component c, Graphics g,
	               int x, int y)
	            {
	               Graphics2D g2 = (Graphics2D)g;
	               drawGrabber(g2, x + OFFSET, y + OFFSET);
	               drawGrabber(g2, x + OFFSET, y + BUTTON_SIZE - OFFSET);
	               drawGrabber(g2, x + BUTTON_SIZE - OFFSET, y + OFFSET);
	               drawGrabber(g2, x + BUTTON_SIZE - OFFSET, y + BUTTON_SIZE - OFFSET);
	            }
	         });
*/
	      normal = new JToggleButton("选中节点");
	      normal.setToolTipText("选中可移动节点");
	      group.add(normal);      
	      add(normal);
	      normal.setSelected(true);
	      tools.add(null);
	      
	      nodeBtn=new JToggleButton("路由结点");
	      nodeBtn.setToolTipText("画一个路由结点");
	      netBtn=new JToggleButton("网络结点");
	      netBtn.setToolTipText("网络结点");
	      linkBtn=new JToggleButton("连接");
	      linkBtn.setToolTipText("连接两个路由结点");
	      distroy=new JToggleButton("销毁或修复");
	      distroy.setToolTipText("销毁或者修复一个网络结点");
	      begin=new JToggleButton("开始模拟");
	      begin.setToolTipText("开始运行ripv2协议");
	      group.add(netBtn);
	      group.add(nodeBtn);
	      group.add(linkBtn);
	      group.add(distroy);
	      group.add(begin);
	      add(nodeBtn);
	      add(netBtn);
	      add(linkBtn);
	      add(distroy);
	      add(begin);
	      tools.add(nodeBtn);
	      tools.add(linkBtn);
	      tools.add(netBtn);
	      tools.add(distroy);
	      tools.add(begin);
	}
	
	   public String getSelectedTool()
	   {
	      for (int i = 0; i < tools.size(); i++)
	      {
	         JToggleButton button = (JToggleButton)getComponent(i);
	         if (button.isSelected()) {
	        	 if(button.equals(nodeBtn)){
	        		 return "node";
	        	 }
	        	 else if(button.equals(linkBtn)){
	        		 return "link";
	        	 }
	        	 else if(button.equals(netBtn)){
	        		 return "netNode";
	        	 }
	        	 else if(button.equals(distroy)){
	        		 return "distroy";
	        	 }
	        	 else if(button.equals(begin)){
	        		 return "begin";
	        	 }
	        	 return "normal";
	         }
	      }
	      return "";
	   }
	   public static void drawGrabber(Graphics2D g2, double x, double y)
	   {
	      final int SIZE = 5;
	      Color oldColor = g2.getColor();
	      g2.setColor(PURPLE);
	      g2.fill(new Rectangle2D.Double(x - SIZE / 2,
	         y - SIZE / 2, SIZE, SIZE));      
	      g2.setColor(oldColor);
	   }
	   private static final Color PURPLE = new Color(0.7f, 0.4f, 0.7f);   
}
