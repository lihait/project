package network;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
    
public class IPMask {
    //���û������IP��ַ���������������Ӧ�Ĵ�����
    public String getIPMask(String ipAddr, String maskAddr) {
        String outputMaskInfo = "�Ƿ�IP�����������ַ��";
        //�Ƿ�IP�����������ַ�������м��㡣 
        if(!isValidIP(ipAddr) || !isValidMask(maskAddr))
            return outputMaskInfo;
        //�Ϸ�IP���������룬��ʼ���㡣
        if(maskAddr.indexOf(".") == -1)
            maskAddr = getMask(Byte.parseByte(maskAddr));
        String[] ipSplit = {"0","0","0","0"};
        String[] maskSplit = {"0","0","0","0"};
        ipSplit = ipAddr.split("\\.");
        maskSplit = maskAddr.split("\\.");
        String ip = "";
        String mask = "";
        String broadcastIP = "";
        String startIP = "";
        String endIP = "";
        String netNu = "";       
        String netIP = "";
        int maskNum = 0;
        //ת��255.0.0.0,255.255.0.0,255.255.255.0,255.255.255.255���������͵���������Ϊ��Ӧ������
        if(Integer.parseInt(maskSplit[0])==255&&Integer.parseInt(maskSplit[1])==0&&Integer.parseInt(maskSplit[2])==0&&Integer.parseInt(maskSplit[3])==0){
        	maskNum = 8;
        }
        if(Integer.parseInt(maskSplit[0])==255&&Integer.parseInt(maskSplit[1])==255&&Integer.parseInt(maskSplit[2])==0&&Integer.parseInt(maskSplit[3])==0){
        	maskNum = 16;
        }
        if(Integer.parseInt(maskSplit[0])==255&&Integer.parseInt(maskSplit[1])==255&&Integer.parseInt(maskSplit[2])==255&&Integer.parseInt(maskSplit[3])==0){
        	maskNum = 24;
        }
        if(Integer.parseInt(maskSplit[0])==255&&Integer.parseInt(maskSplit[1])==255&&Integer.parseInt(maskSplit[2])==255&&Integer.parseInt(maskSplit[3])==255){
        	maskNum = 32;
        }
        for(int i = 0; i < 4; i++) {
            int ipTemp = Integer.parseInt(ipSplit[i]);
            int maskTemp = Integer.parseInt(maskSplit[i]);
            //���������������ת��Ϊ����
           if(i == 0){
        	   if(maskTemp==128){
        		   maskNum = 1;
        	   }
        	   else if(maskTemp==192){
        		   maskNum = 2;
        	   }
        	   else if(maskTemp==224){
        		   maskNum = 3;
        	   }
        	   else if(maskTemp==240){
        		   maskNum = 4;
        	   }
        	   else if(maskTemp==248){
        		   maskNum = 5;
        	   }
        	   else if(maskTemp==252){
        		   maskNum = 6;
        	   }
        	   else if(maskTemp==254){
        		   maskNum = 7;
        	   }
           }
           if(i==1){
        	  if(maskTemp==128){
 				  maskNum = 9;
 			  }
 			  else if(maskTemp==192){
 				  maskNum = 10;
 			  }
 			  else if(maskTemp==224){
 				  maskNum = 11;
 			  }
 			  else if(maskTemp==240){
 				  maskNum = 12;
 			  }
 			  else if(maskTemp==248){
 				  maskNum = 13;
 			  }
 			  else if(maskTemp==252){
 				  maskNum = 14;
 			  }
 			  else if(maskTemp==254){
 				  maskNum = 15;
 			  }
           }
           if(i==2){
		     if(maskTemp==128){
 				  maskNum = 17;
 			  }
 			  else if(maskTemp==192){
 				  maskNum = 18;
 			  }
 			  else if(maskTemp==224){
 				  maskNum = 19;
 			  }
 			  else if(maskTemp==240){
 				  maskNum = 20;
 			  }
 			  else if(maskTemp==248){
 				  maskNum = 21;
 			  }
 			  else if(maskTemp==252){
 				  maskNum = 22;
 			  }
 			  else if(maskTemp==254){
 				  maskNum = 23;
 			  }
           }
           if(i==3){
				 if(maskTemp==128){
					  maskNum = 25;
				  }
				  else if(maskTemp==192){
 				  maskNum = 26;
 			  }
 			  else if(maskTemp==224){
 				  maskNum = 27;
 			  }
 			  else if(maskTemp==240){
 				  maskNum = 28;
 			  }
 			  else if(maskTemp==248){
 				  maskNum = 29;
 			  }
 			  else if(maskTemp==252){
 				  maskNum = 30;
 			  }
 			  else if(maskTemp==254){
 				  maskNum = 31;
 			  }
 			  else if(maskTemp==255){
 				  maskNum = 32;
 			  }
           }
            //�û������IP
            ip = ip.concat(Integer.toString(ipTemp)).concat(".");
            //�û��������������
            mask = mask.concat(Integer.toString(maskTemp)).concat(".");         
            //�����ַ
            netIP = netIP.concat(Integer.toString(ipTemp & maskTemp)).concat(".");
            //�㲥��ַ
            broadcastIP = broadcastIP.concat(Integer.toString(~maskTemp+256 | ipTemp)).concat(".");
            //�ɷ���������ַ
            if(i < 3) {
                startIP = startIP.concat(Integer.toString(ipTemp & maskTemp)).concat(".");
                endIP = endIP.concat(Integer.toString(~maskTemp+256 | ipTemp)).concat(".");
            }
            else if(i == 3) {
                if(maskTemp != 254) {
                    startIP = startIP.concat(Integer.toString((ipTemp & maskTemp) + 1)).concat(".");
                    endIP = endIP.concat(Integer.toString((~maskTemp+256 | ipTemp) - 1)).concat(".");
                }
                else {
                    startIP = "��.";
                    endIP = "��.";
                }
            }           
        }     
        //��������
        String str = String.valueOf(maskNum);      
        //��������
        //outputMaskInfo = netNu.concat(netIP.substring(0, netIP.length() - 1)).concat("/").concat(str);
        outputMaskInfo = netNu.concat(netIP.substring(0, netIP.length() - 1));
        return outputMaskInfo;
    }
    //ת��ʮ��������ΪIP��ַ��ʽ����
    public String getMask(byte maskBit) {
        if(maskBit == 1)
            return "128.0.0.0";
        else if(maskBit == 2)
            return "192.0.0.0";
        else if(maskBit == 3)
            return "224.0.0.0";
        else if(maskBit == 4)
            return "240.0.0.0";
        else if(maskBit == 5)
            return "248.0.0.0";
        else if(maskBit == 6) 
            return "252.0.0.0";
        else if(maskBit == 7)
            return "254.0.0.0";
        else if(maskBit == 8)
            return "255.0.0.0";
        else if(maskBit ==9)
            return "255.128.0.0";
        else if(maskBit == 10)
            return "255.192.0.0";
        else if(maskBit == 11)
            return "255.224.0.0";
        else if(maskBit == 12)
            return "255.240.0.0";
        else if(maskBit == 13)
            return "255.248.0.0";
        else if(maskBit == 14)
            return "255.252.0.0";
        else if(maskBit == 15)
            return "255.254.0.0";
        else if(maskBit == 16)
            return "255.255.0.0";
        else if(maskBit == 17)
            return "255.255.128.0";
        else if(maskBit == 18)
            return "255.255.192.0";
        else if(maskBit == 19)
            return "255.255.224.0";
        else if(maskBit == 20)
            return "255.255.240.0";
        else if(maskBit == 21)
            return "255.255.248.0";
        else if(maskBit == 22)
            return "255.255.252.0";
        else if(maskBit == 23)
            return "255.255.254.0";
        else if(maskBit == 24)
            return "255.255.255.0";
        else if(maskBit == 25)
            return "255.255.255.128";
        else if(maskBit == 26)
            return "255.255.255.192";
        else if(maskBit == 27)
            return "255.255.255.224";
        else if(maskBit == 28)
            return "255.255.255.240";
        else if(maskBit == 29)
            return "255.255.255.248";
        else if(maskBit == 30)
            return "255.255.255.252";
        else if(maskBit == 31)
            return "255.255.255.254";
        else if(maskBit == 32)
            return "255.255.255.255";
        return "";
    }
    //�ж�IP�Ƿ�Ϸ�
    public boolean isValidIP(String ip) {
        if(ip.indexOf(".") == -1)
            return false;
        String[] ipSplit = ip.split("\\.");
        int ipNum = 0;
        if (ipSplit.length != 4)
            return false;
        for (int i = 0; i < ipSplit.length; i++) {
            try {
                ipNum = Integer.parseInt(ipSplit[i]);
            }catch(Exception e) {
                return false;
            }
            if(ipNum < 0 || ipNum > 255)
                return false;
            if(i == 0)
                if(ipNum == 0 || ipNum == 255)
                return false;
        }
        return true;
    }
    
    //�ж����������Ƿ�Ϸ�
    public boolean isValidMask(String mask) {
        int maskNum = 0;
        int maskBit = 0;
        //ʮ��������
        if(mask.indexOf(".") == -1) {
            try {
                maskBit = Byte.parseByte(mask);
            }catch(Exception e) {
                return false;
            }
            if(maskBit > 31 || maskBit < 1) {
                return false;
            }
            return true;
        }
        //IP��ʽ����
        String[] maskSplit = mask.split("\\.");
        String maskBinString = "";
        if(maskSplit.length != 4) 
            return false;
        //������128��4�����������2�����ַ���
        for(int i=0; i<maskSplit.length; i++) {
            try {
                maskNum = Integer.parseInt(maskSplit[i]);
            }catch(Exception e) {
                return false;
            }
            //��λΪ0���Ƿ�����
            if(i == 0 && Integer.numberOfLeadingZeros(maskNum) == 32)
                return false;
            //��0��128��255֮�䣬�Ƿ�����
            if(Integer.numberOfLeadingZeros(maskNum) != 24)
                if(Integer.numberOfLeadingZeros(maskNum) != 32)
                    return false;
            //������128����������ӳ������Ķ������ַ���
            maskBinString = maskBinString.concat(Integer.toBinaryString(maskNum));
        }
        //�����������ַ���������������1ʱ���Ƿ�����
        if(maskBinString.indexOf("0") < maskBinString.lastIndexOf("1"))
                return false;
        //ʣ�µľ��ǺϷ�����
        return true;
    }
    
    //ʶ������λ��
    public int getMaskBit(String binaryMask) {
        return binaryMask.lastIndexOf("1") + 1;
    }
    
    //���˿ո�
    public String deSpace(JTextField textField) {
        String curStr = null;
        String outStr = "";
            for (int i = 0; i < textField.getText().length(); i++) {
                curStr = textField.getText().substring(i, i + 1);
                if (!curStr.equals(" ")) {
                    outStr += curStr;
                }
            }
            return outStr;
        }
    
}

