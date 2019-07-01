
package xgxt.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;


public class PropertesFileParser {
	private String path ="";
	public PropertesFileParser(String path){
		this.path = path;
	}
	/**
	 * �����serviceProperties/localTableColumns�����ļ��е�ĳ������keyָ��������Ϣ���У�
	 * @param key �������ļ�����ĳ������
	 * @return
	 */
	public String getLocalTableValue(String key){
		String result = null;
		try{
			FileReader fr = new FileReader(this.path);
			LineNumberReader lnr = new LineNumberReader(fr);
			String tempLine = "";
			while((tempLine = lnr.readLine()) != null){
				tempLine = tempLine.replaceAll("\\s+", "");
				if(tempLine.startsWith("#")) continue;
				if(tempLine.startsWith(key)) result = tempLine.substring(tempLine.indexOf("=")+1);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		if(result != null ) result = result.replaceAll("\\s+", "");
		return result;
	}

	/**
	 *  �����serviceProperties/remoteTableColumns�����ļ��е�ĳ�����ر���keyָ�����Ͷ�Ӧ��webservice�ṩ��key���������Ϣ���У�
	 * @param localTableKey  �ÿ�ͷ�ı��ر���Ϊkey��������
	 * @return  ���ص����鳤��Ϊ4 ��һ����localTable key(һ���Ǳ��ر�)��
	 *                         �ڶ�����localTable�У���˳�����ƣ���
	 *                         �������� remoteTable key(һ����webservice�ṩ���ṩ���ڲ�ѯ��key)��
	 *                         ���ĸ���remoteTableָ�����У���˳�����ƣ�
	 */
	public String[] getLocalAndRemoteTableValue(String localTableKey){
		String[] result = null;
		try{
			FileReader fr = new FileReader(this.path);
			LineNumberReader lnr = new LineNumberReader(fr);
			String tempLine = "";
			while((tempLine = lnr.readLine()) != null){
				tempLine = tempLine.replaceAll("\\s+", "");
				if(tempLine.startsWith("#")) continue;
				if(tempLine.startsWith(localTableKey)) {
					String[] tables = tempLine.split(";");
					String localTable = tables[0].substring(0,tables[0].indexOf("="));
					String remoteTable = tables[1].substring(0,tables[1].indexOf("="));
					String localTableColumns = tables[0].substring((tables[0].indexOf("=")+1));
					String remoteTableColumns = tables[1].substring((tables[1].indexOf("=")+1));
					if(localTableColumns != null && remoteTableColumns != null)
						result = new String[]{localTable,localTableColumns,remoteTable,remoteTableColumns};
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	//test
//	public static void main(String[] args){
//		PropertesFileParser parsor = new PropertesFileParser("D:/Tomcat 5.5/webapps/xgxt/servicesProperties/remoteTableColumns");// remoteTableColumns////localTableColumns
//		//System.out.println(parsor.getLocalTableValue("view_xsjxjb"));
//		System.out.println(parsor.getLocalAndRemoteTableValue("bks_zydm")[0]);
//	}
}
