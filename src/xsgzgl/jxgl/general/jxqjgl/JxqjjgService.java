/**
 * @部门:学工产品事业部
 * @日期：2015-7-6 上午09:53:40 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-6 上午09:53:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JxqjjgService extends SuperServiceImpl<JxqjjgForm, JxqjjgDao> {
	/**
	 * 
	 * @描述:获取请假类型
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-6 上午10:23:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
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
	 * @描述:请假结果单打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-7 上午10:05:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsjbxx
	 * @param qjjgMap
	 * @return
	 * @throws Exception
	 * File 返回类型 
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
	 * @描述:打印请假单
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-8 上午08:49:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File 返回类型 
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
	 * 病假单
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
	 * 最高级建制ID
	 */
	public String getJzid(){
		return dao.getJzid();
	}
	
	/*
	 * 建制名称
	 */
	public String getJzmc(String jxid,String xh){
		return dao.getJzmc(jxid, xh);
	}
	public String getJzmc1(String xh){
		return dao.getJzmc1(xh);
	}
	
}
