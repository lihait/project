package myclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;

import subjectclient.GetSubjectByIdDelegate;
import subjectclient.GetSubjectByIdService;
import javax.swing.JTextPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import net.miginfocom.swing.MigLayout;

public class Client {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton_1;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
					Client window = new Client();
					window.frame.setVisible(true);
			
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(7, 35, 101, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(7, 98, 420, 147);
		frame.getContentPane().add(textPane);
		
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.setBounds(7, 71, 101, 23);
		btnNewButton.addActionListener(new ActionListener() {
			//注意，下面的代码就是涛神调用的代码，直接拿来就可以用了。
			public void actionPerformed(ActionEvent e) {
				int studentId = Integer.parseInt(textField.getText().trim());
				 GetresultbyidService Service = new GetresultbyidService();
		         GetresultbyidDelegate sss = Service.getGetresultbyidPort();
		    
		         try {
					textPane.setText("您查询的成绩如下：\r\n "+sss.getResult(studentId).toString());
				} catch (Exception_Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u5B66\u53F7");
		label.setBounds(7, 7, 101, 15);
		frame.getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(318, 35, 109, 32);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		btnNewButton_1 = new JButton("查询");
		btnNewButton_1.setBounds(318, 71, 109, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			//注意，这里面的也是涛神的代码，拿来直接就可以调用了
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				 GetSubjectByIdService Service = new GetSubjectByIdService();
		         GetSubjectByIdDelegate sss = Service.getGetSubjectByIdPort();
		         //System.out.println("请输入要查询的题号：");
		         //Scanner scanner = new Scanner(System.in);
		         int id = Integer.parseInt(textField_1.getText().trim());
		         String[] mysubject = new String[6];
		         List list = new ArrayList();
		         try {
					list = sss.getSubjectById(id);
				} catch (subjectclient.Exception_Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		         StringBuffer result = new StringBuffer();
		         result.append("您查询的第"+id+"题的解析如下：\r\n");
		         for(int i=0;i<list.size();i++){
		        //	System.out.println(list.get(i));
		        	 result.append(list.get(i));
		        	 result.append("\r\n");
		         }
			
				textPane.setText(result.toString());
				
			}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		label_1 = new JLabel("\u8BF7\u8F93\u5165\u67E5\u8BE2\u9898\u53F7");
		label_1.setBounds(318, 10, 109, 15);
		frame.getContentPane().add(label_1);
		
		
	}
}
