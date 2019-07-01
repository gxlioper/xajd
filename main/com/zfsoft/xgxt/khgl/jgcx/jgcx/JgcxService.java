/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午03:11:42 
 */  
package com.zfsoft.xgxt.khgl.jgcx.jgcx;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核管理
 * @类功能描述: 考核结果
 * @作者：cq [工号:785]
 * @时间： 2015-8-18 下午03:11:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JgcxService extends SuperServiceImpl<JgcxForm, JgcxDao> {
	
	
	/**
	 * 
	 * @描述:按照单个项目查询结果
	 * @作者：cq [工号：785]
	 * @日期：2015-8-19 下午02:00:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> xmjgList(JgcxForm form, User user) throws Exception{
		if(KhpfService.YHLX_JS.equals(form.getKhlx())){
			return dao.xmjgToJs(form,user);
		}
		return dao.xmjgToXs(form, user);
	}
	public List<HashMap<String, String>> xmjgListOfSdty(JgcxForm form, User user) throws Exception{
		List<HashMap<String,String>> pfzList = dao.getPfzListByXmid(form.getXmid());
		if(KhpfService.YHLX_JS.equals(form.getKhlx())){
			return dao.xmjgToJsOfSdty(form,user,pfzList);
		}
		return dao.xmjgToXsOfSdty(form, user,pfzList);
	}
	
	public List<HashMap<String, String>> getPfzListByXmid(String xmid) throws Exception{
		return dao.getPfzListByXmid(xmid);
	}
	
	
	/**
	 * 
	 * @描述:打分统计
	 * @作者：cq [工号：785]
	 * @日期：2015-8-24 下午01:43:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> dftjList(JgcxForm form, User user) throws Exception{
		
		if(KhpfService.YHLX_JS.equals(form.getKhlx())){
			return dao.jsForKhr(form,user);
		}
		return dao.xsForKhr(form, user);
	}
	
	public List<HashMap<String, String>> lscpjgList(JgcxForm form, User user) throws Exception{
		return dao.lscpjgList(form, user);
	}


	/**
	 * @throws Exception  
	 * @描述:打分人查看
	 * @作者：cq [工号：785]
	 * @日期：2015-8-24 下午06:05:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDfrList(JgcxForm model, User user) throws Exception {
		
		if(KhpfService.YHLX_JS.equals(model.getKhlx())){
			return dao.getDfrListJs(model,user);			
		}

		return dao.getDfrListXs(model,user);
		
	}
	
	public void exportXsOfSdty(JgcxForm model,OutputStream os, List<HashMap<String, String>> resultList)
	throws Exception {

		List<HashMap<String,String>> pfzList = dao.getPfzListByXmid(model.getXmid());
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("考核结果", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();

		ws.addCell(new Label(0, 0, "序号", wcf2));
		ws.addCell(new Label(1, 0, "学号", wcf2));
		ws.addCell(new Label(2, 0, "姓名", wcf2));
		ws.addCell(new Label(3, 0, "性别", wcf2));
		ws.addCell(new Label(4, 0, "年级", wcf2));
		ws.addCell(new Label(5, 0, "学院", wcf2));
		ws.addCell(new Label(6, 0, "班级", wcf2));
		if (pfzList != null && pfzList.size() > 0) {
			for (int i = 0; i < pfzList.size(); i++) {
				HashMap<String, String> map = pfzList.get(i);
				ws.addCell(new Label(6+i+1, 0, map.get("pfzmc"), wcf2));
			}
		}
		ws.addCell(new Label(6+pfzList.size()+1, 0, "总分", wcf2));
		ws.addCell(new Label(6+pfzList.size()+2, 0, "学院排名", wcf2));
		ws.addCell(new Label(6+pfzList.size()+3, 0, "全校排名", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> rsMap = resultList.get(i);
				ws.addCell(new Label(0, 1+i, String.valueOf(i+1), wcf2));
				ws.addCell(new Label(1, 1+i, rsMap.get("xh"), wcf2));
				ws.addCell(new Label(2, 1+i, rsMap.get("xm"), wcf2));
				ws.addCell(new Label(3, 1+i, rsMap.get("xb"), wcf2));
				ws.addCell(new Label(4, 1+i, rsMap.get("nj"), wcf2));
				ws.addCell(new Label(5, 1+i, rsMap.get("xymc"), wcf2));
				ws.addCell(new Label(6, 1+i, rsMap.get("bjmc"), wcf2));
				for (int j = 0; j < pfzList.size(); j++) {
					HashMap<String, String> map = pfzList.get(j);
					ws.addCell(new Label(6+j+1, 1+i, rsMap.get("fs"+j), wcf2));
				}
				ws.addCell(new Label(6+pfzList.size()+1, 1+i, rsMap.get("zf"), wcf2));
				ws.addCell(new Label(6+pfzList.size()+2, 1+i, rsMap.get("xypm"), wcf2));
				ws.addCell(new Label(6+pfzList.size()+3, 1+i, rsMap.get("pm"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
}
	
	public void exportJsOfSdty(JgcxForm model,OutputStream os, List<HashMap<String, String>> resultList)
	throws Exception {

		List<HashMap<String,String>> pfzList = dao.getPfzListByXmid(model.getXmid());
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("考核结果", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();

		ws.addCell(new Label(0, 0, "序号", wcf2));
		ws.addCell(new Label(1, 0, "职工号", wcf2));
		ws.addCell(new Label(2, 0, "姓名", wcf2));
		ws.addCell(new Label(3, 0, "性别", wcf2));
		ws.addCell(new Label(4, 0, "所在部门", wcf2));
		if (pfzList != null && pfzList.size() > 0) {
			for (int i = 0; i < pfzList.size(); i++) {
				HashMap<String, String> map = pfzList.get(i);
				ws.addCell(new Label(4+i+1, 0, map.get("pfzmc"), wcf2));
			}
		}
		ws.addCell(new Label(4+pfzList.size()+1, 0, "总分", wcf2));
		ws.addCell(new Label(4+pfzList.size()+2, 0, "学院排名", wcf2));
		ws.addCell(new Label(4+pfzList.size()+3, 0, "全校排名", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> rsMap = resultList.get(i);
				ws.addCell(new Label(0, 1+i, String.valueOf(i+1), wcf2));
				ws.addCell(new Label(1, 1+i, rsMap.get("zgh"), wcf2));
				ws.addCell(new Label(2, 1+i, rsMap.get("xm"), wcf2));
				ws.addCell(new Label(3, 1+i, rsMap.get("xbmc"), wcf2));
				ws.addCell(new Label(4, 1+i, rsMap.get("bmmc"), wcf2));
				for (int j = 0; j < pfzList.size(); j++) {
					HashMap<String, String> map = pfzList.get(j);
					ws.addCell(new Label(4+j+1, 1+i, rsMap.get("fs"+j), wcf2));
				}
				ws.addCell(new Label(4+pfzList.size()+1, 1+i, rsMap.get("zf"), wcf2));
				ws.addCell(new Label(4+pfzList.size()+2, 1+i, rsMap.get("xypm"), wcf2));
				ws.addCell(new Label(4+pfzList.size()+3, 1+i, rsMap.get("pm"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
}
	/** 
	 * @描述:根据项目id，班主任（职工号）List查询学生对班主任的汇总打分数据列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月5日 下午3:43:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @param khdxrs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsdbzrhzList(String xmid, List<String> bzrList) {
		
		return dao.getXsdbzrhzList(xmid,bzrList);
	}
	/** 
	 * @描述:生成word文件，学生对班主任打分的汇总打印
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月5日 下午3:43:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsdbzrhzList
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File getXsdbzrhzFile(List<HashMap<String, String>> xsdbzrhzList) {
		
		HashMap<String,Object> data = new HashMap<String,Object>();
		//将list记录重组，2条为一组，返回新的list
		List<HashMap<String, HashMap<String,String>>> list = new ArrayList<HashMap<String,HashMap<String,String>>>();
		
		int size = xsdbzrhzList.size();
		int i = 1;
		for(;i<size;i+=2){
			HashMap<String,HashMap<String,String>> map = new HashMap<String,HashMap<String,String>>();
			map.put("col1", xsdbzrhzList.get(i-1));
			map.put("col2", xsdbzrhzList.get(i));
			list.add(map);
		}
		if(i==size){
			HashMap<String,HashMap<String,String>> map = new HashMap<String,HashMap<String,String>>();
			map.put("col1", xsdbzrhzList.get(i-1));
			map.put("col2", new HashMap<String, String>());
			list.add(map);
		}
		
		data.put("list", list);
		
		String mbmc = "xsdbzrhzdy_" + Base.xxdm + ".xml";
		String fileName = "学生对班主任测评汇总表_"+System.currentTimeMillis();
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//khgl", mbmc, fileName);
		
		return wordFile;
	}

}
