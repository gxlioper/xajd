package xgxt.comm.xml;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

/**
 * @descrpiton 采用dom4j解析xml文件
 * @author sjf
 * @date 2011.4.28
 * @copyRight 1.0
 */
public class Dom4jForXmlHandle {
	/**
	 * 解析xml，获取document对象
	 * 
	 * @param fileName
	 * @return
	 */
	public static Document parserXml(String fileName) {
		InputStream is = Dom4jForXmlHandle.class.getResourceAsStream(fileName);
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			try {
				doc = saxReader.read(is);
			} finally {
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}
