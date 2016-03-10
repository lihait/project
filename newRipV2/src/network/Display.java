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
	// �����߳�
	public void run() {
		int i = 0;
		while (true) {
			try {
				if (i < nodes.size()) {
					// ��ȡ·�ɽڵ�
					Node n = (Node) nodes.get(i);
					this.setText("���" + n.getName() + "��ʼ����·����Ϣ\n");
					ArrayList ls = n.getLinks();
					for (int j = 0; j < ls.size(); j++) {
						// ��ȡ��·�ɽڵ��������
						Link l = (Link) ls.get(j);
						// ��ȡ��·�ɽڵ�ֱ���������
						NetNode net = l.getNetNode();
						// /
						 String[] netNa = n.setNetName();
						 names = net.setAllNet(netNa);
						 System.out.println("names=" + names);
						// /
						// ������ڵ㱻�ƻ�����Ϊ����������ɴ�
						if (net.getStatus() == NetNode.distroy) {
							str = net.getNetName();
							n.destroy(net.getNetName());
							continue;
						}
						// ��ȡÿ������ڵ��������
						ArrayList netLinks = net.getLinks();
						for (int k = 0; k < netLinks.size(); k++) {
							// ��ȡÿ������ڵ�����е������߶���
							Link nl = (Link) netLinks.get(k);
							// ���һ���ڵ�������߶���ͬ����˵����ͬ�������߶�Ӧ��ͬ��·�ɽڵ�
							if (!nl.equals(l)) {
								// �����һ��·�ɽڵ����
								Node n2 = nl.getN2();
								// /
								// String[] netNa = n.setNetName();
								// String names = net.setAllNet(netNa);
								// System.out.println("names=" + names);
								// /
								this.setText(this.getText() + "��·��"
										+ n.getName() + "��·��" + n2.getName()
										+ "����·�ɱ���Ϣ\n" + "����ǰ" + n2.getName()
										+ "��·����ϢΪ\n" + n2.toString() + "\n");
								// ����·�ɽڵ�������·�ɱ���,��ִ�и��²���
								 if(GraphPanel.begin==false){
								 break;
								 }
								 else{
								 sendTo(n,n2);
								 }
								this.setText(this.getText() + "���º��·�ɱ���ϢΪ\n"
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

	// �Ƚ�����·�ɱ��е����ݣ�����·�ɱ�
	// ������forѭ��ʵ����������·��·�ɱ�ıȽ�
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
				// �Ե����ͬһ������Ͻ��бȽϸ���
				// �����·�ɱ�����һ��·����Ϊ��ֱ��·��������������
				//if (p2.getNetName().equals(p1.getNetName())) {
				if (p2.getNetName().equals(names)) {
					if (p2.getNextR().equals(n1.getName())) {
						//�������ֵ
						if (p1.getNetName() == str&&p1.getStep()==16) {
							this.setText(this.getText() + "��������"
									+ p2.getNetName() + ",Ŀ�Ĳ��ɴ�\n");
							p2.setStep(16);
						} else {
							this.setText(this.getText() + "��������"
									+ p2.getNetName() + ",��һ���ֶ���ֱͬ���滻\n");
							p2.setStep(p1.getStep() + 1);
						}
						added = true;
					}
					// �Ƚϵ���ͬһ����ε�����������·��������������и���
					else if (p2.getStep() > p1.getStep() + 1) {
						this.setText(this.getText() + "��������" + p2.getNetName()
								+ ",�յ�����Ŀ�еľ���С��·�ɱ��е�,���и���\n");
						p2.setNextR(n1.getName());
						p2.setStep(p1.getStep() + 1);
						added = true;
					}
					// �Ƚϵ���ͬһ����ε�����������·��������С�����·����Ŀ����
					else if (p2.getStep() <= p1.getStep() + 1) {
						added = true;
					}
				}
			}
			// ���ڽ��շ�û�з��ͷ�������·����Ŀ������·�ɱ�����Ӹ�����Ŀ
			if (!added) {
				this.setText(this.getText() + "��Ŀ��Ŀ�����粻��·�ɱ���,������Ŀ��ӵ�·�ɱ���\n");
				//n2.addPacket(new Packet(p1.getNetName(), p1.getStep() + 1, n1.getName()));
				n2.addPacket(new Packet(names,p1.getStep()+1,n1.getName()));
			}
		}
	}
}
