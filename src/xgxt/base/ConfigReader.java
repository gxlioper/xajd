/*
 * �������� 2006-5-13
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.base;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import xgxt.base.Configuration;
/**
 * @author bat_zzj
 * 
 *  Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class ConfigReader {

	private Log logger = LogFactory.getLog(ConfigReader.class);
	
	public ConfigReader(String configFileName) {
		parseConfigXML(configFileName);
	}

	public void parseConfigXML(String configURI) {
		
		if (logger.isDebugEnabled()){
			logger.debug("���ڶ�ȡ�����ļ�: " + configURI);
		}
		
		try {
			SAXBuilder builder = new SAXBuilder(false);
			Document doc;

			doc = builder.build("file:///" + configURI);

			Element root = doc.getRootElement();

			
			Configuration.FILE_UPLOAD_DIR = root.getChild("news").getChild(
			"uploadFileDir").getTextTrim();

		} catch (JDOMException jdome) {
			jdome.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
		}
	}

}
