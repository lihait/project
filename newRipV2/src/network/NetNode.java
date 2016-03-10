package network;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NetNode {
	private ArrayList links;
	private String netName;
	private int status;
	public static final int distroy=0;
	public static final int ative=1;
	private static int i=0;
	
	private Ellipse2D bounds;
	private static final double width=30,height=30;
	private static final Color p=Color.magenta;
	private double locX,locY;
	//static String[] netNames = new String[1000];
	private static String netNameResult = "";
	public NetNode(Point2D p){
		links=new ArrayList();
		IPMask frame = new IPMask();
		String ipAddr=JOptionPane.showInputDialog("请输入ip地址:");
		String maskAddr=JOptionPane.showInputDialog("请输入子网掩码:");
        String s = frame.getIPMask(ipAddr, maskAddr);
		netName=s;
		i++;
		status=NetNode.ative;
		locX=p.getX();
		locY=p.getY();
		bounds=new Ellipse2D.Double(p.getX(),p.getY(),width,height);
	}
	/**
	 * 获得当前所有子网段号，进行路由汇总
	 * @param netNames 存储所有的子网地址
	 * @return 汇总的结果网络地址
	 */
		public String setAllNet(String[] netNames){
			String netNu = "";
			String ip = "";
			String mask = "";
			String allNetName = "";
			if(netNames.length<=1&&netNames.length!=0){
				System.out.println("hahahaha");
				netNameResult = netNames[0];
			}
			else if(netNames.length==0){
				netNameResult = "";
			}
			else{
	    			for(int d=1;d<netNames.length;d++){
	    				String netName1 = netNames[d-1];
	    				String netName2 = netNames[d];
	    				String[] ipSplit = {"0","0","0","0"};
	    		        String[] maskSplit = {"0","0","0","0"};
	    		        ipSplit = netName1.split("\\.");
	    		        maskSplit = netName2.split("\\.");
	    		        for(int h = 0; h < 4; h++) {
	    		            int ipTemp = Integer.parseInt(ipSplit[h]);
	    		            int maskTemp = Integer.parseInt(maskSplit[h]);
	    		            allNetName = allNetName.concat(Integer.toString(ipTemp & maskTemp)).concat(".");
	    		        }
	    		        netNameResult = netNu.concat(allNetName.substring(0, allNetName.length() - 1));  
	    		        netNames[d] = netNameResult;
	    		        allNetName = "";
	    			}			   				
			}
			return netNameResult;
		}
	public ArrayList getLinks() {
		return links;
	}
	public void setLinks(ArrayList links) {
		this.links = links;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void addLink(Link l){
		links.add(l);
	}

	/**
	 * 把结点移到一个给定的地方
	 * @param x 结点在X轴方向移动的距离
	 * @param y 结点在Y轴方向移动的距离
	 * */
	public void translate(double x,double y){
		locX+=x;
		locY+=y;
		bounds=new Ellipse2D.Double(locX,locY,width,height);
	}
	
	public void draw(Graphics2D g2d){
		Color oldColor=g2d.getColor();
		if(status==NetNode.distroy){
			g2d.setColor(Color.black);
		}
		else
			g2d.setColor(p);
			
		g2d.fill(bounds);
		g2d.setColor(oldColor);
		g2d.drawString(netName,(float)locX, (float)locY);
	}
	
	/**
	 *判断p是否在这个结点的范围之内.
	 *@param p 是一个Point2D实例,存储者一个点的坐标
	 *@return 如果这个结点包含这个坐标返回真
	 * */
	public boolean contains(Point2D p){
		return bounds.contains(p);
	}
	public Rectangle2D getBounds() {
		return bounds.getBounds();
	}
	public void setBounds(Ellipse2D bounds) {
		this.bounds = bounds;
	}
	
	public boolean equals(Object o){
		if(o==null){
			return false;
			
		}
		if(o instanceof NetNode){
			NetNode net=(NetNode)o;
			if(net.getNetName().equals(netName)){
				return true;
			}
		}
		
		
		return false;
	}
}
