
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
	 * 获得在serviceProperties/localTableColumns配置文件中的某个表（由key指定）的信息（列）
	 * @param key 在配置文件中是某个表名
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
	 *  获得在serviceProperties/remoteTableColumns配置文件中的某个本地表（由key指定）和对应的webservice提供的key（或表）的信息（列）
	 * @param localTableKey  用开头的本地表作为key来遍历行
	 * @return  返回的数组长度为4 第一个是localTable key(一般是本地表)，
	 *                         第二个是localTable列（有顺序限制），
	 *                         第三个是 remoteTable key(一般是webservice提供方提供用于查询的key)，
	 *                         第四个是remoteTable指定的列（有顺序限制）
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
