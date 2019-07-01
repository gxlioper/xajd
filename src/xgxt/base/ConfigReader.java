/*
 * 创建日期 2006-5-13
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
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
 *  要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class ConfigReader {

	private Log logger = LogFactory.getLog(ConfigReader.class);
	
	public ConfigReader(String configFileName) {
		parseConfigXML(configFileName);
	}

	public void parseConfigXML(String configURI) {
		
		if (logger.isDebugEnabled()){
			logger.debug("正在读取配置文件: " + configURI);
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
