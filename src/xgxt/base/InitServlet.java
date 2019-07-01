/*
 * 创建日期 2006-5-13
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.util.ResourceUtils;

import common.Globals;

import xgxt.action.Base;

/**
 * 
 * 系统初始化Servlet
 * 修改日期：2013-5-17
 * 修改人：Penghui.Qu
 * 修改内容：梳理启动加载，将web.xml中系统参数移到classpath://config//config.xml文件中<br>
 * web.xml文件仅保留系统支撑项
 */
public class InitServlet extends HttpServlet {

	private Log logger = LogFactory.getLog(InitServlet.class);

	private static final long serialVersionUID = 1L;

	
	//系统初始化
	public void init(ServletConfig conf) throws ServletException {
		try {
			
			logger.info("\r\n\r\nSystem init start .....\r\n");
			//初始化config.xml相关参数
			initParams();

			// ==========加载资源文件设置院系（学院）===============
			MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
			Base.YXPZXY_KEY = message.getMessage("lable.xsgzyxpzxy");
			
			logger.info("\r\n\r\nSystem init end .....\r\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//初始化config.xml中相关配置
	@SuppressWarnings("unchecked")
	private void initParams() throws FileNotFoundException, JDOMException,
			IOException {
		
		SAXBuilder builder = new SAXBuilder(false);
		File configFile = ResourceUtils.getFile("classpath:/config/config.xml");
		
		Document doc = builder.build(configFile);
		Element root = doc.getRootElement();
		
		// 北京联合大学基础数据webservice
		if (Globals.XXDM_BJLHDX.equals(Base.xxdm)){
			Element bjlhWebService = root.getChild("bjlhWebService");
			String url = bjlhWebService.getChildTextTrim("url");
			String user = bjlhWebService.getChildTextTrim("user");
			String pwd = bjlhWebService.getChildTextTrim("pwd");
			Base.setBjlhBaseDataEndPointUri(url);
			Base.setWebserviceUser(user);
			Base.setWebservicePass(pwd);
		}
		
		//根据config.xml配置文件初始化系统参数
		Element systemConfig = root.getChild("systemConfig");
		List<Element> configs = systemConfig.getChildren("config");
		
		for (Element config : configs){
			String key = config.getChildTextTrim("key");
			String value = config.getChildTextTrim("value");
			Base.setInitProperties(key,value);
		}
		
	}


}
