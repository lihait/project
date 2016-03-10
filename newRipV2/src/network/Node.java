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
 * ��������ڴ洢������Ϣ
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
	 * @param p ������ĳ�ʼλ�� 
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
		Packet p=new Packet(net.getNetName(),0,"ֱ��");
		rTable.add(p);
	}
	
	public ArrayList getLinks(){
		return this.links;
	}
	
	/**
	 *�ж�p�Ƿ���������ķ�Χ֮��.
	 *@param p ��һ��Point2Dʵ��,�洢��һ���������
	 *@return ������������������귵����
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
	 * �ѽ���Ƶ�һ�������ĵط�
	 * @param x �����X�᷽���ƶ��ľ���
	 * @param y �����Y�᷽���ƶ��ľ���
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
		String s="������\t\t����\t\t��һ��·��\n";
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
			//�ƻ�һ���ڵ�����·�ɱ����õ���ֱ��·��������Ϊ16
			if(p.getStep()>=1){
				p.setStep(16);
			}
			if(p.getNetName().equals(net)){
				p.setStep(16);
			}
		}
	}
	//��ȡ��Ҫ���ܵ�������ַ
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
