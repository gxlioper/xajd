/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-6 ����09:53:40 
 */  
package xsgzgl.jxgl.general.jxqjgl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-6 ����09:53:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxqjjgService extends SuperServiceImpl<JxqjjgForm, JxqjjgDao> {
	/**
	 * 
	 * @����:��ȡ�������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-6 ����10:23:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjlx() {
		return dao.getQjlx();
	}
	public HashMap<String, String> getQjjgForPrint(String qjjgid)
	throws Exception {
	return dao.getQjjgForPrint(qjjgid);
	}
	/**
	 * 
	 * @����:��ٽ������ӡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-7 ����10:05:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsjbxx
	 * @param qjjgMap
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File printForWord(HashMap<String, String> xsjbxx,HashMap<String, String> qjjgMap) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String qjkssjFormat=DateTranCnDate.fomartDateToCn(qjjgMap.get("qjkssj"),FomartDateType.day);
		String qjjssjFormat=DateTranCnDate.fomartDateToCn(qjjgMap.get("qjjssj"),FomartDateType.day);
		xsjbxx.put("qjkssjFormat",qjkssjFormat);
		xsjbxx.put("qjjssjFormat",qjjssjFormat);
		xsjbxx.put("qjsy", HtmlUtil.xmlZy(qjjgMap.get("qjsy")));
		qjjgMap.remove("xh");
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.put("jzmc", qjjgMap.get("jzmc"));
		data.put("xxmc", Base.xxmc);
		data.putAll(xsjbxx);
		data.putAll(qjjgMap);
		return getWord(data);
	}
	/**
	 * 
	 * @����:��ӡ��ٵ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8 ����08:49:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File �������� 
	 * @throws
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException {
		String templatePath = Constants.TEP_DIR+"jxgl/jxqjd_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "jxgl", "jxqjd_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "jxgl", "jxqjd.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	
	/*
	 * ���ٵ�
	 */
	public File printForWordBjd(HashMap<String, String> xsjbxx,HashMap<String, String> qjjgMap) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String qjkssjFormat=DateTranCnDate.fomartDateToCn(qjjgMap.get("qjkssj"),FomartDateType.day);
		String qjjssjFormat=DateTranCnDate.fomartDateToCn(qjjgMap.get("qjjssj"),FomartDateType.day);
		xsjbxx.put("qjkssjFormat",qjkssjFormat);
		xsjbxx.put("qjjssjFormat",qjjssjFormat);
		xsjbxx.put("qjsy", HtmlUtil.xmlZy(qjjgMap.get("qjsy")));
		qjjgMap.remove("xh");
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.put("jzmc", qjjgMap.get("jzmc"));
		data.put("xxmc", Base.xxmc);
		data.putAll(xsjbxx);
		data.putAll(qjjgMap);
		return getWordBjd(data);
	}
	
	public File getWordBjd(Map<String, Object> data) throws FileNotFoundException {
		String templatePath = Constants.TEP_DIR+"jxgl/bjd_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "jxgl", "bjd_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "jxgl", "bjd.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	
	/*
	 * ��߼�����ID
	 */
	public String getJzid(){
		return dao.getJzid();
	}
	
	/*
	 * ��������
	 */
	public String getJzmc(String jxid,String xh){
		return dao.getJzmc(jxid, xh);
	}
	public String getJzmc1(String xh){
		return dao.getJzmc1(xh);
	}
	
}
