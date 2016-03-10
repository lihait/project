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
		String ipAddr=JOptionPane.showInputDialog("������ip��ַ:");
		String maskAddr=JOptionPane.showInputDialog("��������������:");
        String s = frame.getIPMask(ipAddr, maskAddr);
		netName=s;
		i++;
		status=NetNode.ative;
		locX=p.getX();
		locY=p.getY();
		bounds=new Ellipse2D.Double(p.getX(),p.getY(),width,height);
	}
	/**
	 * ��õ�ǰ���������κţ�����·�ɻ���
	 * @param netNames �洢���е�������ַ
	 * @return ���ܵĽ�������ַ
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
	 * �ѽ���Ƶ�һ�������ĵط�
	 * @param x �����X�᷽���ƶ��ľ���
	 * @param y �����Y�᷽���ƶ��ľ���
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
	 *�ж�p�Ƿ���������ķ�Χ֮��.
	 *@param p ��һ��Point2Dʵ��,�洢��һ���������
	 *@return ������������������귵����
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
