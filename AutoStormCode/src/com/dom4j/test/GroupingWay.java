package com.dom4j.test;
/**
 * �����û�����Ĳ�ͬ���������ַ���ʽ
 * @author Administrator
 *
 */
public class GroupingWay {
       
	public static String getGroupWay(String grouping,String dataId,String data){
		
		String groupCode = null;
		
		// �漴����Ͳ�����
		if (grouping.equals("shuffleGrouping")) {

			groupCode = grouping + "(" + "\"" + dataId + "\"" + ")";

		}
		// ���ֶη���
		else if (grouping.equals("fieldsGrouping")) {

			groupCode = grouping + "(" + "\"" + dataId + "\"" + ","
					+ "new Fields(" + "\"" + data + "\"" + "))";

		}
		// �㲥����
		else if (grouping.equals("allGrouping")) {

			groupCode = grouping + "(" + "\"" + dataId + "\"" + ","
					+ "\"" + data + "\"" + ")";

		}
		// ȫ�ַ���
		else if (grouping.equals("globalGrouping")) {

			groupCode = grouping + "(" + "\"" + dataId + "\"" + ")";

		}
		// ֱ�ӷ���
		else if (grouping.equals("directGrouping")) {

			groupCode = grouping + "(" + "\"" + dataId + "\"" + ")";

		}

		
		return groupCode;
		
	}
	
}
