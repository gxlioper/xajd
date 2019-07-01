package xgxt.utils.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import xgxt.utils.xml.exception.NoReadXMLInitiateException;
import xgxt.utils.xml.exception.OutOfArrayLength2Exception;

public class XMLOperation {
	private String targetPath = "";//要存放xml文件的地址
	private String sourcePath = "";//获得xml文件的地址
	private Element root;//根节点
	private Document sourceDoc;//源文件所关联的文档
	private boolean flag;//读xml文件进行初始化的标志：true=已经初始化成功；false=初始化失败/没有初始化操作
	public XMLOperation(){}
	
	/**
	 * @param path 用于存放生成的xml文件地址
	 */
	public XMLOperation(String path){
		this.targetPath = path;
	}
	
//	public boolean generateOutPutFile(String rootName,String[] subNames,String[] subValues,HashMap<String, String[][]> attrs){
//		boolean result = false;
//		XMLOutputter xmlOutput = new XMLOutputter();
//		xmlOutput.setFormat(Format.getPrettyFormat());
//		try{
//			FileOutputStream fos = new FileOutputStream(this.targetPath);
//			genXMLDoc
//			xmlOutput.output(doc, out)
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	/**
	 * 根据提供的元素名称生成一个两层的xml文件
	 * @param rootName        根节点
	 * @param subNames        子节点
	 * @param subValues       子节点值
	 * @param attrs           子节点对应的属性
	 * 				              其中  key     是subNames中的值，
	 * 					               value   对应key指定的子节点的属性的二维数组，并且第二维长度只能为2，
	 * 							               数组的第一个值是属性名，第二个值是属性值
	 */
	public boolean genXMLDoc(String rootName,String[] subNames,String[] subValues,HashMap<String, String[][]> attrs) throws OutOfArrayLength2Exception {
		boolean result = false;
		Element root = new Element(rootName);
		Document doc = new Document(root);
		//向根节点中添加子节点
		for(int i=0;i<subNames.length;i++){
			Element subElement = new Element(subNames[i]);
			subElement.addContent(subValues[i]);//添加子节点属性值
			String[][] attributes = attrs.get(subNames[i]);
			if(attributes != null){
				for(int j=0;j<attributes.length;j++){
					if(attributes[j].length>2)//属性键值对，所以长度只能为2，否则抛出长度超过2的限制的异常 
						throw new OutOfArrayLength2Exception();
					addAttribute(subElement,attributes[j][0],attributes[j][1]);
				}
			}
			addSubElement(root,subElement);
		}
		//根据给定的路径生成文件
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		try{
			FileOutputStream fos = new FileOutputStream(this.targetPath);
			xmlOutput.output(doc, fos);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 给指定节点添加属性
	 * @param target
	 * @param attrName
	 * @param value
	 */
	public void addAttribute(Element target,String attrName,String value){
		target.setAttribute(attrName, value);
	}
	
	/**
	 * 给指定节点添加子节点
	 * @param target
	 * @param subElement
	 */
	public void addSubElement(Element target,Element subElement){
		target.addContent(subElement);
	}
	
	/**
	 * 在读取xml文档之前，要先进行初始化操作
	 * @return 初始化是否成功
	 */
	public boolean readXMLInit(){
		boolean result = false;
		SAXBuilder saxBuilder = new SAXBuilder();
		try{
			this.sourceDoc = saxBuilder.build(new File(this.sourcePath));
			this.root = this.sourceDoc.getRootElement();
			this.flag = true;//标示读取xml文件初始化成功
			result = true;
		}catch(Exception e){
			this.flag = false;//标示读取xml文件初始化失败
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 读取指定的xml文件的根节点名
	 * @return 
	 */
	public String getRootElement() throws NoReadXMLInitiateException{
		if(!this.flag) //判断是否经过初始化，若没有初始化则抛出没有进行初始化的异常
			throw new NoReadXMLInitiateException();

		return this.root.getName();
	}
	
	/**
	 * @return 返回根元素的所有属性名
	 * @throws NoReadXMLInitiateException
	 */
	public List<String> getRootAttrs() throws NoReadXMLInitiateException{
		List<String> result = new ArrayList<String>();
		if(!this.flag) //判断是否经过初始化，若没有初始化则抛出没有进行初始化的异常
			throw new NoReadXMLInitiateException();
		SAXBuilder saxBuilder = new SAXBuilder();
		try{
			Document doc = saxBuilder.build(new File(this.sourcePath));
			Element root = doc.getRootElement();
			Object[] obj = root.getAttributes().toArray();
			for(int i = 0; i < obj.length;i++){
				Attribute attribute = (Attribute) obj[i];
				result.add(attribute.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return 根节点下的所有子节点名
	 */
	public List<String> getChildren()throws NoReadXMLInitiateException{
		if(!this.flag) //判断是否经过初始化，若没有初始化则抛出没有进行初始化的异常
			throw new NoReadXMLInitiateException();
		List<String> list = new ArrayList<String>();
		Object[] obj = this.root.getChildren().toArray();
		for(int i = 0; i < obj.length;i++){
			Element ele = (Element)obj[i];
			list.add(ele.getName());
		}
		return list;
	}
	
	/**
	 * @return 根节点下的所有子节点的值
	 */
	public HashMap<String, String> getChildrenValues()throws NoReadXMLInitiateException{
		if(!this.flag) //判断是否经过初始化，若没有初始化则抛出没有进行初始化的异常
			throw new NoReadXMLInitiateException();
		
		HashMap<String, String> result = new HashMap<String, String>();
		List<String> children = getChildren();
		for(String sub : children){
			result.put(sub, this.root.getChild(sub).getTextTrim());
		}
		return result;
	}
	
	/**
	 * @return  根节点的属性键值对
	 * @throws NoReadXMLInitiateException
	 */
	public HashMap<String, String> getRootAttrAndValues() throws NoReadXMLInitiateException{
		HashMap<String, String> result = new HashMap<String, String>();
		if(!this.flag) //判断是否经过初始化，若没有初始化则抛出没有进行初始化的异常
			throw new NoReadXMLInitiateException();
		List<String> attrs = getRootAttrs();
		for(String sub : attrs){
			result.put(sub, this.root.getAttributeValue(sub));
		}
		return result;
	}
	
	/**
	 * @param attrName 根节点的属性名
	 * @return
	 */
	public String getAttrValue(String attrName)throws NoReadXMLInitiateException{
		if(!this.flag) //判断是否经过初始化，若没有初始化则抛出没有进行初始化的异常
			throw new NoReadXMLInitiateException();
		return this.root.getAttributeValue(attrName);
	}
	
	/**
	 * 指定子节点的属性对应的值
	 * @param elementName 根节点下的子节点
	 * @param attrName 子节点的一个属性名
	 * @return
	 */
	public String getSubElementAttrValue(String elementName,String attrName)throws NoReadXMLInitiateException{
		if(!this.flag) //判断是否经过初始化，若没有初始化则抛出没有进行初始化的异常
			throw new NoReadXMLInitiateException();
		return this.root.getChild(elementName).getAttributeValue(attrName);
	}
	
//	public static void main(String[] args){
//		//test
////		<?xml version="1.0" encoding="UTF-8"?>
////		<car vin="123fhg5869705iop90">
////		  <!--Description of a car-->
////		  <make>Toyota</make>
////		  <model>Celica</model>
////		  <year>1997</year>
////		  <color>green</color>
////		  <license state="CA">1ABC234</license>
////		</car>
//
//		XMLOperation xmlOperation = new XMLOperation("d:\\test.xml");
//		String rootName = "car";
//		String[] subNames = {"make","model","year","color","license"};
//		String[] subValues = {"toyota","Celica","2007","green","1ABC234"};
//		HashMap<String, String[][]> attrs = new HashMap<String, String[][]>();
//		attrs.put("license", new String[][]{{"state","ca"}});
//		attrs.put("make", new String[][]{{"vin","1231"}});
//		try {
//			xmlOperation.genXMLDoc(rootName, subNames, subValues, attrs);
//		} catch (OutOfArrayLength2Exception e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
//		xmlOperation.setSourcePath("d:\\test.xml");
//		boolean b = xmlOperation.readXMLInit();
//		try {
//			List<String> a = xmlOperation.getChildren();//根节点下的所有子节点名
//			HashMap<String, String> c = xmlOperation.getChildrenValues();//根节点下的所有子节点的值
//			HashMap<String, String> d = xmlOperation.getRootAttrAndValues();//根节点的属性键值对
//			List<String> e = xmlOperation.getRootAttrs();//返回根元素的所有属性名
//			String f = xmlOperation.getRootElement();//读取指定的xml文件的根节点名
//			xmlOperation.getSubElementAttrValue("license", "state");
//			xmlOperation.getRootElement();//读取指定的xml文件的根节点名
//		} catch (NoReadXMLInitiateException e1) {
//			// TODO 自动生成 catch 块
//			e1.printStackTrace();
//		}
//	}
}
