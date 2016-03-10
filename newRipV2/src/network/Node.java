package network;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.TexturePaint;
/**
 * 这个类用于存储结点的信息
 */
public class Node {

	private Rectangle2D bounds;
	private String name;
	private ArrayList rTable;
	private static int i=0;
	private ArrayList links;
	private final double width=30,height=30;
	private double locX,locY;
	private static Color p=Color.cyan;
	public Node(){
		
	}
	/**
	 * @param p 这个结点的初始位置 
	 * 
	 * */
	public Node(Point2D p){
		name="R"+i;
		i++;
		links=new ArrayList();
		rTable=new ArrayList();
		locX=p.getX();
		locY=p.getY();
		bounds=new Rectangle2D.Double(p.getX(),p.getY(),width,height);
	}
	
	public void addPacket(Packet p){
		rTable.add(p);
	}
	///
	public void removePacket(Packet p){
		rTable.remove(p);
	}
	///
	public ArrayList getTable(){
		return this.rTable;
	}
	
	public void addLink(Link l){
		links.add(l);
		NetNode net=l.getNetNode();
		Packet p=new Packet(net.getNetName(),0,"直连");
		rTable.add(p);
	}
	
	public ArrayList getLinks(){
		return this.links;
	}
	
	/**
	 *判断p是否在这个结点的范围之内.
	 *@param p 是一个Point2D实例,存储者一个点的坐标
	 *@return 如果这个结点包含这个坐标返回真
	 * */
	public boolean contains(Point2D p){
		return bounds.contains(p);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Rectangle2D getBounds(){
		return this.bounds;
	}
	/**
	 * 把结点移到一个给定的地方
	 * @param x 结点在X轴方向移动的距离
	 * @param y 结点在Y轴方向移动的距离
	 * */
	public void translate(double x,double y){
		locX+=x;
		locY+=y;
		bounds=new Rectangle2D.Double(locX,locY,width,height);
	}
	
	public void draw(Graphics2D g2d){
		Color oldColor=g2d.getColor();
		g2d.setColor(p);
		g2d.fill(bounds);
		g2d.setColor(oldColor);
		g2d.drawString(name, (float)locX, (float)locY);
	}
	public String toString(){
		String s="网络名\t\t跳数\t\t下一跳路由\n";
		for(int i=0;i<rTable.size();i++){
			Packet p=(Packet)rTable.get(i);
			s+=p.toString()+"\n";
		}
		return s;
	}
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		if(o instanceof Node ){
			Node n=(Node)o;
			if(n.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	public void destroy(String net){
		for(int i=0;i<rTable.size();i++){
			Packet p=(Packet)rTable.get(i);
			//破坏一个节点后更新路由表，设置到非直连路由器距离为16
			if(p.getStep()>=1){
				p.setStep(16);
			}
			if(p.getNetName().equals(net)){
				p.setStep(16);
			}
		}
	}
	//获取需要汇总的子网地址
	public String[] setNetName(){
		String[] netNa = new String[rTable.size()];
		for(int i=0;i<rTable.size();i++){
			Packet p=(Packet)rTable.get(i);
			//if(i==0){
			//	netNa[i] = "255.255.255.255";
			//}
			//else{
			netNa[i] = p.getNetName();
		//	}
		}
		return netNa;
	}
}
