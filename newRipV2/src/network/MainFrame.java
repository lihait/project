package network;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	
	private GraphPanel workPlace;
	private Toolbar toolbar;
	private Display display;
	private Container ctp;
	private JTabbedPane tab;
	private JTextArea info;	
	private JSplitPane split;
	public MainFrame(){
		super("RIPģ����");
		ctp=this.getContentPane();
		init();
		this.setSize(1000, 700);
		this.setVisible(true);
	}
	private void init(){
		toolbar=new Toolbar();
		info=new JTextArea();
		info.setEditable(false);
		info.setWrapStyleWord(true);
		info.setLineWrap(true);
		workPlace=new GraphPanel(toolbar,info);
		display=new Display(workPlace);
		display.setEditable(false);
		display.setWrapStyleWord(true);
		display.setLineWrap(true);
		tab=new JTabbedPane(JTabbedPane.TOP);
		tab.addTab("���", new JScrollPane(display));
		tab.addTab("·�ɱ���Ϣ", new JScrollPane(info));
		
/*		JPanel p1=new JPanel();
		p1.add(toolbar,BorderLayout.NORTH);
		JPanel p2=new JPanel();
		p2.add( new JScrollPane(workPlace),BorderLayout.CENTER);
		JPanel p=new JPanel();
		p.add(p1,BorderLayout.NORTH);
		p.add(p2,BorderLayout.SOUTH);*/
		JPanel p=new JPanel();
		//p.add(toolbar);
		//toolbar.setLocation(0, 0);
		//workPlace.setLocation(2, 100);
		//p.add(new JScrollPane(workPlace));
		split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,workPlace,tab);
		split.setAutoscrolls(true);
		split.setDividerLocation(500);
		ctp.add(toolbar,BorderLayout.NORTH);
		ctp.add(split,BorderLayout.CENTER);
		createMenu();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createMenu(){
		JMenuBar bar=new JMenuBar();
		JMenu file=new JMenu("�ļ�O");
		JMenuItem nf=new JMenuItem("�½�");
		nf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				workPlace.reset();
				workPlace.repaint();
				info.setText("");
				display.setText("");
			}
		});
		file.add(nf);
		bar.add(file);
		
		this.setJMenuBar(bar);
	}
}
