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
	private String targetPath = "";//Ҫ���xml�ļ��ĵ�ַ
	private String sourcePath = "";//���xml�ļ��ĵ�ַ
	private Element root;//���ڵ�
	private Document sourceDoc;//Դ�ļ����������ĵ�
	private boolean flag;//��xml�ļ����г�ʼ���ı�־��true=�Ѿ���ʼ���ɹ���false=��ʼ��ʧ��/û�г�ʼ������
	public XMLOperation(){}
	
	/**
	 * @param path ���ڴ�����ɵ�xml�ļ���ַ
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
	 * �����ṩ��Ԫ����������һ�������xml�ļ�
	 * @param rootName        ���ڵ�
	 * @param subNames        �ӽڵ�
	 * @param subValues       �ӽڵ�ֵ
	 * @param attrs           �ӽڵ��Ӧ������
	 * 				              ����  key     ��subNames�е�ֵ��
	 * 					               value   ��Ӧkeyָ�����ӽڵ�����ԵĶ�ά���飬���ҵڶ�ά����ֻ��Ϊ2��
	 * 							               ����ĵ�һ��ֵ�����������ڶ���ֵ������ֵ
	 */
	public boolean genXMLDoc(String rootName,String[] subNames,String[] subValues,HashMap<String, String[][]> attrs) throws OutOfArrayLength2Exception {
		boolean result = false;
		Element root = new Element(rootName);
		Document doc = new Document(root);
		//����ڵ�������ӽڵ�
		for(int i=0;i<subNames.length;i++){
			Element subElement = new Element(subNames[i]);
			subElement.addContent(subValues[i]);//����ӽڵ�����ֵ
			String[][] attributes = attrs.get(subNames[i]);
			if(attributes != null){
				for(int j=0;j<attributes.length;j++){
					if(attributes[j].length>2)//���Լ�ֵ�ԣ����Գ���ֻ��Ϊ2�������׳����ȳ���2�����Ƶ��쳣 
						throw new OutOfArrayLength2Exception();
					addAttribute(subElement,attributes[j][0],attributes[j][1]);
				}
			}
			addSubElement(root,subElement);
		}
		//���ݸ�����·�������ļ�
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
	 * ��ָ���ڵ��������
	 * @param target
	 * @param attrName
	 * @param value
	 */
	public void addAttribute(Element target,String attrName,String value){
		target.setAttribute(attrName, value);
	}
	
	/**
	 * ��ָ���ڵ�����ӽڵ�
	 * @param target
	 * @param subElement
	 */
	public void addSubElement(Element target,Element subElement){
		target.addContent(subElement);
	}
	
	/**
	 * �ڶ�ȡxml�ĵ�֮ǰ��Ҫ�Ƚ��г�ʼ������
	 * @return ��ʼ���Ƿ�ɹ�
	 */
	public boolean readXMLInit(){
		boolean result = false;
		SAXBuilder saxBuilder = new SAXBuilder();
		try{
			this.sourceDoc = saxBuilder.build(new File(this.sourcePath));
			this.root = this.sourceDoc.getRootElement();
			this.flag = true;//��ʾ��ȡxml�ļ���ʼ���ɹ�
			result = true;
		}catch(Exception e){
			this.flag = false;//��ʾ��ȡxml�ļ���ʼ��ʧ��
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��ȡָ����xml�ļ��ĸ��ڵ���
	 * @return 
	 */
	public String getRootElement() throws NoReadXMLInitiateException{
		if(!this.flag) //�ж��Ƿ񾭹���ʼ������û�г�ʼ�����׳�û�н��г�ʼ�����쳣
			throw new NoReadXMLInitiateException();

		return this.root.getName();
	}
	
	/**
	 * @return ���ظ�Ԫ�ص�����������
	 * @throws NoReadXMLInitiateException
	 */
	public List<String> getRootAttrs() throws NoReadXMLInitiateException{
		List<String> result = new ArrayList<String>();
		if(!this.flag) //�ж��Ƿ񾭹���ʼ������û�г�ʼ�����׳�û�н��г�ʼ�����쳣
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
	 * @return ���ڵ��µ������ӽڵ���
	 */
	public List<String> getChildren()throws NoReadXMLInitiateException{
		if(!this.flag) //�ж��Ƿ񾭹���ʼ������û�г�ʼ�����׳�û�н��г�ʼ�����쳣
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
	 * @return ���ڵ��µ������ӽڵ��ֵ
	 */
	public HashMap<String, String> getChildrenValues()throws NoReadXMLInitiateException{
		if(!this.flag) //�ж��Ƿ񾭹���ʼ������û�г�ʼ�����׳�û�н��г�ʼ�����쳣
			throw new NoReadXMLInitiateException();
		
		HashMap<String, String> result = new HashMap<String, String>();
		List<String> children = getChildren();
		for(String sub : children){
			result.put(sub, this.root.getChild(sub).getTextTrim());
		}
		return result;
	}
	
	/**
	 * @return  ���ڵ�����Լ�ֵ��
	 * @throws NoReadXMLInitiateException
	 */
	public HashMap<String, String> getRootAttrAndValues() throws NoReadXMLInitiateException{
		HashMap<String, String> result = new HashMap<String, String>();
		if(!this.flag) //�ж��Ƿ񾭹���ʼ������û�г�ʼ�����׳�û�н��г�ʼ�����쳣
			throw new NoReadXMLInitiateException();
		List<String> attrs = getRootAttrs();
		for(String sub : attrs){
			result.put(sub, this.root.getAttributeValue(sub));
		}
		return result;
	}
	
	/**
	 * @param attrName ���ڵ��������
	 * @return
	 */
	public String getAttrValue(String attrName)throws NoReadXMLInitiateException{
		if(!this.flag) //�ж��Ƿ񾭹���ʼ������û�г�ʼ�����׳�û�н��г�ʼ�����쳣
			throw new NoReadXMLInitiateException();
		return this.root.getAttributeValue(attrName);
	}
	
	/**
	 * ָ���ӽڵ�����Զ�Ӧ��ֵ
	 * @param elementName ���ڵ��µ��ӽڵ�
	 * @param attrName �ӽڵ��һ��������
	 * @return
	 */
	public String getSubElementAttrValue(String elementName,String attrName)throws NoReadXMLInitiateException{
		if(!this.flag) //�ж��Ƿ񾭹���ʼ������û�г�ʼ�����׳�û�н��г�ʼ�����쳣
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
//			// TODO �Զ����� catch ��
//			e.printStackTrace();
//		}
//		xmlOperation.setSourcePath("d:\\test.xml");
//		boolean b = xmlOperation.readXMLInit();
//		try {
//			List<String> a = xmlOperation.getChildren();//���ڵ��µ������ӽڵ���
//			HashMap<String, String> c = xmlOperation.getChildrenValues();//���ڵ��µ������ӽڵ��ֵ
//			HashMap<String, String> d = xmlOperation.getRootAttrAndValues();//���ڵ�����Լ�ֵ��
//			List<String> e = xmlOperation.getRootAttrs();//���ظ�Ԫ�ص�����������
//			String f = xmlOperation.getRootElement();//��ȡָ����xml�ļ��ĸ��ڵ���
//			xmlOperation.getSubElementAttrValue("license", "state");
//			xmlOperation.getRootElement();//��ȡָ����xml�ļ��ĸ��ڵ���
//		} catch (NoReadXMLInitiateException e1) {
//			// TODO �Զ����� catch ��
//			e1.printStackTrace();
//		}
//	}
}
