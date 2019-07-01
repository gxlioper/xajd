/*
 * �������� 2006-5-13
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
 * ϵͳ��ʼ��Servlet
 * �޸����ڣ�2013-5-17
 * �޸��ˣ�Penghui.Qu
 * �޸����ݣ������������أ���web.xml��ϵͳ�����Ƶ�classpath://config//config.xml�ļ���<br>
 * web.xml�ļ�������ϵͳ֧����
 */
public class InitServlet extends HttpServlet {

	private Log logger = LogFactory.getLog(InitServlet.class);

	private static final long serialVersionUID = 1L;

	
	//ϵͳ��ʼ��
	public void init(ServletConfig conf) throws ServletException {
		try {
			
			logger.info("\r\n\r\nSystem init start .....\r\n");
			//��ʼ��config.xml��ز���
			initParams();

			// ==========������Դ�ļ�����Ժϵ��ѧԺ��===============
			MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
			Base.YXPZXY_KEY = message.getMessage("lable.xsgzyxpzxy");
			
			logger.info("\r\n\r\nSystem init end .....\r\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//��ʼ��config.xml���������
	@SuppressWarnings("unchecked")
	private void initParams() throws FileNotFoundException, JDOMException,
			IOException {
		
		SAXBuilder builder = new SAXBuilder(false);
		File configFile = ResourceUtils.getFile("classpath:/config/config.xml");
		
		Document doc = builder.build(configFile);
		Element root = doc.getRootElement();
		
		// �������ϴ�ѧ��������webservice
		if (Globals.XXDM_BJLHDX.equals(Base.xxdm)){
			Element bjlhWebService = root.getChild("bjlhWebService");
			String url = bjlhWebService.getChildTextTrim("url");
			String user = bjlhWebService.getChildTextTrim("user");
			String pwd = bjlhWebService.getChildTextTrim("pwd");
			Base.setBjlhBaseDataEndPointUri(url);
			Base.setWebserviceUser(user);
			Base.setWebservicePass(pwd);
		}
		
		//����config.xml�����ļ���ʼ��ϵͳ����
		Element systemConfig = root.getChild("systemConfig");
		List<Element> configs = systemConfig.getChildren("config");
		
		for (Element config : configs){
			String key = config.getChildTextTrim("key");
			String value = config.getChildTextTrim("value");
			Base.setInitProperties(key,value);
		}
		
	}


}
