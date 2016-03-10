package network;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Display extends JTextArea implements Runnable {

	private Thread t = null;
	private GraphPanel graph;
	private ArrayList nodes;
	String str;
	String names;
	String[] namess = new String[1000];

	public Display(GraphPanel g) {
		super();		
		graph = g;
		nodes = g.getNodes();
		t = new Thread(this);
		t.start();
	}

	// //
	/*
	 * public String displays(){ int r=0; for(r=0;r<nodes.size();r++){ Node
	 * nn=(Node)nodes.get(r); ArrayList lss=nn.getLinks(); for(int
	 * j=0;j<lss.size();j++){ Link ll=(Link)lss.get(j); NetNode
	 * net=ll.getNetNode();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return "";
	 * 
	 * }
	 */
	// 创建线程
	public void run() {
		int i = 0;
		while (true) {
			try {
				if (i < nodes.size()) {
					// 获取路由节点
					Node n = (Node) nodes.get(i);
					this.setText("结点" + n.getName() + "开始发送路由信息\n");
					ArrayList ls = n.getLinks();
					for (int j = 0; j < ls.size(); j++) {
						// 获取与路由节点的连接线
						Link l = (Link) ls.get(j);
						// 获取与路由节点直连的网络断
						NetNode net = l.getNetNode();
						// /
						 String[] netNa = n.setNetName();
						 names = net.setAllNet(netNa);
						 System.out.println("names=" + names);
						// /
						// 若网络节点被破坏，置为最大跳数不可达
						if (net.getStatus() == NetNode.distroy) {
							str = net.getNetName();
							n.destroy(net.getNetName());
							continue;
						}
						// 获取每个网络节点的连接线
						ArrayList netLinks = net.getLinks();
						for (int k = 0; k < netLinks.size(); k++) {
							// 获取每个网络节点的所有的连接线对象
							Link nl = (Link) netLinks.get(k);
							// 如果一个节点的连接线对象不同，则说明不同的连接线对应不同的路由节点
							if (!nl.equals(l)) {
								// 获得另一个路由节点对象
								Node n2 = nl.getN2();
								// /
								// String[] netNa = n.setNetName();
								// String names = net.setAllNet(netNa);
								// System.out.println("names=" + names);
								// /
								this.setText(this.getText() + "由路由"
										+ n.getName() + "向路由" + n2.getName()
										+ "发送路由表信息\n" + "更新前" + n2.getName()
										+ "的路由信息为\n" + n2.toString() + "\n");
								// 两个路由节点对象进行路由表交换,并执行更新操作
								 if(GraphPanel.begin==false){
								 break;
								 }
								 else{
								 sendTo(n,n2);
								 }
								this.setText(this.getText() + "更新后的路由表信息为\n"
										+ n2.toString() + "\n");
							}
						}
					}
				}
				i++;
				if (i >= nodes.size()) {
					i = 0;
				}
				t.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 比较两个路由表中的内容，更新路由表
	// 用两个for循环实现两个相邻路由路由表的比较
	private void sendTo(Node n1, Node n2) {
		ArrayList ps = n1.getTable();
		ArrayList ps2 = n2.getTable();
		for (int i = 0; i < ps.size(); i++) {
			boolean added = false;
			Packet p1 = (Packet) ps.get(i);
			///
			//Packet p11 = (Packet) ps.get(i+1);
			///
			for (int j = 0; j < ps2.size(); j++) {
				Packet p2 = (Packet) ps2.get(j);
				// 对到达的同一个网络断进行比较更新
				// 如果该路由表中下一跳路由器为其直连路由器，则不作更新
				//if (p2.getNetName().equals(p1.getNetName())) {
				if (p2.getNetName().equals(names)) {
					if (p2.getNextR().equals(n1.getName())) {
						//定义最大值
						if (p1.getNetName() == str&&p1.getStep()==16) {
							this.setText(this.getText() + "对于网络"
									+ p2.getNetName() + ",目的不可达\n");
							p2.setStep(16);
						} else {
							this.setText(this.getText() + "对于网络"
									+ p2.getNetName() + ",下一跳字段相同直接替换\n");
							p2.setStep(p1.getStep() + 1);
						}
						added = true;
					}
					// 比较到达同一网络段的跳数，若本路由器跳数大则进行更新
					else if (p2.getStep() > p1.getStep() + 1) {
						this.setText(this.getText() + "对于网络" + p2.getNetName()
								+ ",收到的项目中的距离小于路由表中的,进行更新\n");
						p2.setNextR(n1.getName());
						p2.setStep(p1.getStep() + 1);
						added = true;
					}
					// 比较到达同一网络段的跳数，若本路由器跳数小则进行路由条目不变
					else if (p2.getStep() <= p1.getStep() + 1) {
						added = true;
					}
				}
			}
			// 若在接收方没有发送方发来的路由条目，则在路由表中添加该新条目
			if (!added) {
				this.setText(this.getText() + "项目的目的网络不在路由表中,将该项目添加到路由表中\n");
				//n2.addPacket(new Packet(p1.getNetName(), p1.getStep() + 1, n1.getName()));
				n2.addPacket(new Packet(names,p1.getStep()+1,n1.getName()));
			}
		}
	}
}
