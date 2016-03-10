package com.dom4j.test;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.dom4j.Document;

public class UI extends JFrame implements ActionListener {
	JButton button;
	JButton Select;
	JButton btnOK;
	public static String filePath = null;

	JTextField textfield;
	JPanel p;
	JFileChooser fc = new JFileChooser();
	TextArea area;

	public UI() {
		p = new JPanel(); // ����һ�����
		this.getContentPane().add(p);// �������ӵ����
		p.add(new JButton("�ı�"));// ��һ���ı���ť��ӵ����
		textfield = new JTextField(10);
		p.add(textfield); // ��һ���ı�����ӵ����
		Select = new JButton("���");
		p.add(Select); // ��һ�������ť��ӵ����
		Select.addActionListener(this);
		btnOK = new JButton("ȷ��");
		p.add(btnOK);// ��һ��ȷ����ť��ӵ����
		btnOK.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// ������ѡ��ť����һ���ļ�ѡ���ı�����ʾ�ļ�·��
		if (e.getSource() == Select) {
			int intRetVal = fc.showOpenDialog(this);
			if (intRetVal == JFileChooser.APPROVE_OPTION) {
				textfield.setText(fc.getSelectedFile().getPath());
			}
		} else if (e.getSource() == btnOK) { // ������ȷ����ť������һ���¿�ܣ����������һ���ı�����ʾ���ļ�������
			JFrame f = new JFrame();
			f.setSize(400, 400);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			String extensionName = getExtensionName(textfield.getText());
			if ("xml".equals(extensionName)) {
				f.setTitle("��ʾ�ı�");
				area = new TextArea();
				// ��ȡ�ı�ֵ
				String text = readTxt(textfield.getText());
				//String text = readTxt("D:\\stormCode\\main\\Topology.java");
				filePath = textfield.getText();
				area.setText(text);
				f.add(area);
				f.setVisible(true);

				Document document;
				try {
					document = stormCode.readXmlFile();

					stormCode.list(document.getRootElement());

					stormCode.readNode(document);

					stormCode.readNodeName(document);

					PrintClass.printSpoutBoltClass();

					stormCode.topology();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "��ѡ��xml��ʽ���ļ���");
			}
		}
	}

	/**
	 * @Description����ȡ�ļ���׺��
	 * @param filename
	 * @return
	 * @throws
	 */
	private String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * @Description����ȡ�ļ�
	 * @param path
	 *            - �ļ���ַ
	 * @return
	 * @throws
	 */
	private String readTxt(String path) {
		if (path == null || "".equals(path)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		File file = new File(path);
		InputStreamReader read = null;
		BufferedReader reader = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), "gb2312");
			reader = new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		UI frame = new UI();
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}