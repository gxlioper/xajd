package xgxt.comm.xml;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.ClassPathResource;

public class XMLReader {

	
	/**
	 * 读取flowControl.xml中的一些配置
	 * @param gnmkmc
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getFlowControl(String gnmkmc,String key) {
		
		String lcbh = "";
		SAXBuilder builder = new SAXBuilder(false);
		
		try {
			//flowControl.xml 转移到了classpath的根目录下
			Document doc = builder.build(new ClassPathResource("flowControl.xml").getURL());
			Element root = doc.getRootElement();
			List<Element> gnmk = root.getChildren("gnmk");
			
			for (int i = 0 ; i < gnmk.size() ; i++) {
				if (gnmkmc.equalsIgnoreCase(gnmk.get(i).getAttribute("id").getValue())) {
					List<Element> gnd = gnmk.get(i).getChildren("param");
					
					for (int j = 0 ; j < gnd.size() ; j++) {
						if (key.equals(gnd.get(j).getChild("key").getValue())) {
							lcbh = gnd.get(j).getChild("value").getValue();
							break;
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return lcbh;
	}
	
	/**
	 * 我的事项
	 * @param gnmkmc
	 * @param gnmc
	 * @return
	 * author qlj
	 */
	public static String getMyProceeding(String gnmkmc,String gnmc) {
		
		String lcbh = "";
		SAXBuilder builder = new SAXBuilder(false);
		
		try {
			Document doc = builder.build(XMLReader.class.getResource("MyProceeding.xml"));
			Element root = doc.getRootElement();
			List<Element> gnmk = root.getChildren("gnmk");
			
			for (int i = 0 ; i < gnmk.size() ; i++) {
				if (gnmkmc.equalsIgnoreCase(gnmk.get(i).getAttribute("id").getValue())) {
					List<Element> gnd = gnmk.get(i).getChildren("gnd");
					
					for (int j = 0 ; j < gnd.size() ; j++) {
						if (gnmc.equals(gnd.get(j).getChild("gnmc").getValue())) {
							lcbh = gnd.get(j).getChild("sql").getValue();
							break;
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return lcbh;
	}
	
	public static String getDataDetect(String mklx,String jcmk,String yclx) {
		
		
		String sql = "";
		SAXBuilder builder = new SAXBuilder(false);
		
		String xmlUrl="dataDetect/"+mklx+"/"+mklx+"Detect.xml";
		try {
			Document doc = builder.build(XMLReader.class.getResource(xmlUrl));
			Element root = doc.getRootElement();
			List<Element> jcmkList = root.getChildren("gnmk");
			
			for (int i = 0 ; i < jcmkList.size() ; i++) {
				if (mklx.equalsIgnoreCase(jcmkList.get(i).getAttribute("id").getValue())) {
					List<Element> gnd = jcmkList.get(i).getChildren("jcmk");
					
					for (int j = 0 ; j < gnd.size() ; j++) {
						
						if(jcmk.equalsIgnoreCase(gnd.get(j).getAttribute("id").getValue())){
							
							List<Element> paramList = gnd.get(j).getChildren("param");
							for(int k=0;k<paramList.size();k++){
								
								if (yclx.equals(paramList.get(k).getChild("yclx").getValue())) {
									sql = paramList.get(k).getChild("sql").getValue();
									break;
								}
							}
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return sql;
		
	}
}
