package network;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
/**
 * 这个类用于存储两个结点的连线
 * 
 * */
public class Link {
	/**
	 *
	 */
	private Node n1,n2;
	private NetNode net;
	private String netName;
	private static int i=0;
	private static Stroke DOTTED_STROKE = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0.0f,new float[] { 3.0f, 3.0f }, 0.0f);
	public Link(NetNode n1,Node n2){
		this.net=n1;
		this.n2=n2;
		netName="link"+i;
		i++;
	}
	
	public NetNode getNetNode(){
		return this.net;
	}
	public Node getNode() {
		return n2;
	}

	public void setN1(Node n1) {
		this.n1 = n1;
	}

	public Node getN2() {
		return n2;
	}

	public void setN2(Node n2) {
		this.n2 = n2;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}
	public boolean contains(Point2D p){
		return false;
	}
	
	public void draw(Graphics2D g){
		Stroke oldStroke = g.getStroke();
	    g.setStroke(DOTTED_STROKE);
	    g.draw(getConnectionPoints());
	    g.setStroke(oldStroke);
	}
	public Line2D getConnectionPoints(){
		Rectangle2D r2d1 = net.getBounds();
        Rectangle2D r2d2 = n2.getBounds();
        Point2D.Double d1=new Point2D.Double(r2d1.getCenterX(),r2d1.getCenterY());
        Point2D.Double d2=new Point2D.Double(r2d2.getCenterX(),r2d2.getCenterY());
		return new Line2D.Double(d1,d2);
	}
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		if(o instanceof Link){
			Link l=(Link)o;
			if(l.getNetName().equals(netName)){
				return true;
			}
		}
		return false;
	}
	
}
