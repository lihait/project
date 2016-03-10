
package network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * @author new
 *
 */
public class GraphPanel extends JPanel{
	private Toolbar toolbar;
	private ArrayList nodes;
	private ArrayList links;
	private ArrayList netNodes;
	private Object selected;
	private Point2D lastMousePoint;
    private Point2D rubberBandStart;
    private Point2D dragStartPoint;
    private Rectangle2D dragStartBounds;
    private JTextArea info;
	private static final Color PURPLE = new Color(0.7f, 0.4f, 0.7f);  
	static boolean begin = false;
	public GraphPanel(Toolbar t,JTextArea info){
		this.toolbar=t;
		this.info=info;
	    setBackground(Color.WHITE);
		init();
		this.setLayout(null);
	}
	private void init(){
		nodes=new ArrayList();
		links=new ArrayList();
		netNodes=new ArrayList();

		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Point2D mousePoint=new Point2D.Double(
						e.getX(), e.getY());
				Node n=findNode(mousePoint);
				Link l=findLink(mousePoint);
				NetNode net=findNetNode(mousePoint);
				String tool=toolbar.getSelectedTool();
				if(tool.equals("node")||tool.equals("netNode")){
					boolean added=addNode(n,mousePoint);
					if(added){
						if(tool.equals("node")){
							Node newNode=new Node(mousePoint);
							nodes.add(newNode);
		                    selected = newNode;
		                    dragStartBounds = newNode.getBounds();
		                    dragStartPoint = mousePoint;
						}				
						else{

							NetNode newNode=new NetNode(mousePoint);
							netNodes.add(newNode);
		                    selected = newNode;
		                    dragStartBounds = newNode.getBounds();
		                    dragStartPoint = mousePoint;
						}
					}
					else if(n!=null){
						selected=n;
	                    dragStartBounds = n.getBounds();
	                    dragStartPoint = mousePoint;						
					}
					else if(net!=null){
						selected=net;
	                    dragStartBounds = net.getBounds();
	                    dragStartPoint = mousePoint;
					}
				}
				else if (tool.equals("distroy")){
					if(net!=null){
						if(net.getStatus()==NetNode.ative){
							net.setStatus(NetNode.distroy);
						}
						else{
							net.setStatus(NetNode.ative);
						}
						selected=net;
						//netNodes.remove(selected);
					}	
					/*
					else if(n!=null){
						selected=n;
						nodes.remove(selected);
					}	
					else if(l!=null){
						selected=l;
						links.remove(selected);
					}
					*/
				}
				else if(tool.equals("begin")){
					begin = true;
				}				
				else if(tool.equals("link")){
					if(n!=null){
						selected=n;
						rubberBandStart = mousePoint;
					}
					else if(net!=null){
						selected=net;
						rubberBandStart = mousePoint;						
					}
				}
				else{
					if (l != null)
	                  {
	                     selected = l;
	                  }
	                  else if (n != null)
	                  {
	                     selected = n;
	                     dragStartBounds = n.getBounds();
	                     dragStartPoint = mousePoint;
	                  }
	                  else if (net != null)
	                  {
	                     selected = net;
	                     dragStartBounds = net.getBounds();
	                     dragStartPoint = mousePoint;
	                  }
	                  else 
	                  {
	                     selected = null;
	                  }
				}
				
	            lastMousePoint = mousePoint;
	            
	            repaint();
			}			
			
			public void mouseReleased(MouseEvent e){
				String tool=toolbar.getSelectedTool();
				if(rubberBandStart!=null){
					Point2D mousePoint=new Point2D.Double(
							e.getX(), e.getY());
					if(selected instanceof NetNode){
						Node n=findNode(mousePoint);
						if(n!=null){
							NetNode net=(NetNode)selected;
							Link l=new Link(net,n);
							links.add(l);
							n.addLink(l);
							net.addLink(l);
							selected=l;
						}
					}
					else if(selected instanceof Node){
						NetNode net=findNetNode(mousePoint);
						if(net!=null){
							Node n=(Node)selected;
							Link l=new Link(net,n);
							net.addLink(l);
							n.addLink(l);
							links.add(l);
							selected=l;
						}
					}
					
				}

	               revalidate();
	               repaint();

	               lastMousePoint = null;
	               dragStartBounds = null;
	               rubberBandStart = null;
			}
			public void mouseClicked(MouseEvent e){
				Point2D mousePoint=new Point2D.Double(
						e.getX(), e.getY());
				Node n=findNode(mousePoint);
				if(n!=null){
					info.setText(n.toString());
				}
			}
			
		});
		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				Point2D mousePoint=new Point2D.Double(
						e.getX(), e.getY());
				if(dragStartBounds!=null){
					if(selected instanceof Node){
						Node n=(Node)selected;
						n.translate(e.getX()-n.getBounds().getX()
								+dragStartBounds.getX()-dragStartPoint.getX(),
								e.getY()-n.getBounds().getY()
								+dragStartBounds.getY()-dragStartPoint.getY());
					}
					else if(selected instanceof NetNode){
						NetNode net=(NetNode)selected;
						net.translate(e.getX()-net.getBounds().getX()
								+dragStartBounds.getX()-dragStartPoint.getX(),
								e.getY()-net.getBounds().getY()
								+dragStartBounds.getY()-dragStartPoint.getY());
					}
				}
	            lastMousePoint = mousePoint;
	            repaint();
			}
			public void mouseMoved(MouseEvent e){
				
				
			}
		});
		//this.setSize(1000, 700);
	}
	private Rectangle2D getBs(){
		Rectangle2D r=null;
		for(int i=0;i<nodes.size();i++){
			Node n=(Node)nodes.get(i);
			Rectangle2D b=n.getBounds();
			if(r==null){
				r=b;
			}
			else{
				r.add(b);
			}
		}
		for(int i=0;i<netNodes.size();i++){
			NetNode net=(NetNode)netNodes.get(i);
			Rectangle2D b=net.getBounds();
			if(r==null){
				r=b;
			}
			else{
				r.add(b);
			}
		}
		return r == null ? new Rectangle2D.Double() : r;
	}
	public Dimension getPreferredSize(){
		Rectangle2D r=this.getBs();
		return new Dimension(10*(int)r.getMaxX(),10*(int)r.getMaxY());
	}
	private boolean addNode(Node n,Point2D p){
		for(int i=0;i<nodes.size();i++){
			Node x=(Node)nodes.get(i);
			if(x.contains(p)){
				return false;
			}
		}
		for(int i=0;i<netNodes.size();i++){
			NetNode x=(NetNode)netNodes.get(i);
			if(x.contains(p)){
				return false;
			}
		}
		return true;
	}
	protected Node findNode(Point2D p){
		
		for(int i=0;i<nodes.size();i++){
			Node n=(Node)nodes.get(i);
			if(n.contains(p)){
				return n;
			}
		}
		return null;
	}
	
	protected Link findLink(Point2D p){
		
		for(int i=0;i<links.size();i++){
			Link l=(Link)links.get(i);
			if(l.contains(p)){
				return l;
			}
		}
		return null;
	}

	protected NetNode findNetNode(Point2D p){
		
		for(int i=0;i<netNodes.size();i++){
			NetNode net=(NetNode)netNodes.get(i);
			if(net.contains(p)){
				return net;
			}
		}
		return null;
	}
	
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		for(int i=0;i<nodes.size();i++){
			Node n=(Node)nodes.get(i);
			n.draw(g2d);
		}
		for(int i=0;i<links.size();i++){
			Link l=(Link)links.get(i);
			l.draw(g2d);
		}
		for(int i=0;i<netNodes.size();i++){
			NetNode net=(NetNode)netNodes.get(i);
			net.draw(g2d);
		}
		if(nodes.contains(selected)){
			Rectangle2D grabberBounds = ((Node)selected).getBounds();
	         drawGrabber(g2d, grabberBounds.getMinX(), grabberBounds.getMinY());
	         drawGrabber(g2d, grabberBounds.getMinX(), grabberBounds.getMaxY());
	         drawGrabber(g2d, grabberBounds.getMaxX(), grabberBounds.getMinY());
	         drawGrabber(g2d, grabberBounds.getMaxX(), grabberBounds.getMaxY());
		}
		else if(links.contains(selected)){
			Line2D line=((Link)selected).getConnectionPoints();
			drawGrabber(g2d, line.getX1(), line.getY1());
	        drawGrabber(g2d, line.getX2(), line.getY2());
		}
		else if(netNodes.contains(selected)){
			Rectangle2D grabberBounds = ((NetNode)selected).getBounds();
	         drawGrabber(g2d, grabberBounds.getMinX(), grabberBounds.getMinY());
	         drawGrabber(g2d, grabberBounds.getMinX(), grabberBounds.getMaxY());
	         drawGrabber(g2d, grabberBounds.getMaxX(), grabberBounds.getMinY());
	         drawGrabber(g2d, grabberBounds.getMaxX(), grabberBounds.getMaxY());
		}

	      if (rubberBandStart != null)
	      {
	         Color oldColor = g2d.getColor();
	         g2d.setColor(PURPLE);
	         g2d.draw(new Line2D.Double(rubberBandStart, lastMousePoint));
	         g2d.setColor(oldColor);
	      }
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
	   
	   public ArrayList getNodes(){
		   return this.nodes;
	   }
	   public ArrayList getLinks(){
		   return this.links;
	   }
	   public ArrayList getNetNode(){
		   return this.netNodes;
	   }
	   public void reset(){
		   nodes.clear();
		   links.clear();
		   netNodes.clear();
		   selected=null;
           lastMousePoint = null;
           dragStartBounds = null;
           rubberBandStart = null;
           dragStartPoint=null;
	   }

}
