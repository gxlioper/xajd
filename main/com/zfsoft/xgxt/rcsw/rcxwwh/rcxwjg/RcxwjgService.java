/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:42:04 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.ExcelMB;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhDao;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.OtmMapping;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为结果
 * @作者： dlq [工号：995]
 * @时间： 2013-8-7 下午04:42:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RcxwjgService extends SuperServiceImpl<RcxwjgForm, RcxwjgDao> {

	private RcxwjgDao dao = new RcxwjgDao();
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};

	public RcxwjgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * 获取行为大类集合
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午03:58:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		return dao.getXwdlList(request);
	}
	
	/**
	 * 
	 * 保存行为结果
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午04:31:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXwjg(RcxwjgForm model) throws Exception {
		String[] xwlbdmArr=model.getXwlbdmArr();
		String[] fzArray=model.getFzArray();
		String[] xwdldmArr=model.getXwdldmArr();
		String[] fssjArr=model.getFssjArr();
		String[] gflyArr=model.getGflyArr();
		//附件
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		
		boolean flag=true;
		for(int i=0;i<xwlbdmArr.length;i++){
			model.setRcxwlbdm(xwlbdmArr[i]);
			model.setRcxwlbdldm(xwdldmArr[i]);
			model.setFz(fzArray[i]);
			model.setFssj(fssjArr[i]);
			model.setGfly(gflyArr[i]);
			
			//处理附件
			FormFile file = (FormFile) files.get("lbfj"+i);
			
			if (file != null && !StringUtil.isNull(file.getFileName())){
				String basePath = resource.getString("filesys.local.dir");
				//如果没有给定文件存储路径，就默认给系统用户目录
				if(StringUtils.isNull(basePath)){
					basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
				}
				String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
				
				if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
					File srcFile = FileUtil.conversionFormFile(file);
					File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
					FileUtils.copyFile(srcFile, destFile);
					model.setFjlj(destFile.getAbsolutePath());
					model.setFjmc(file.getFileName());
				}
			}
			
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag=insertResult;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:获取行为类别集合
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午04:47:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jldldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwlbList(String jldldm,
			HttpServletRequest request) {
		return dao.getXwlbList(jldldm, request);
	}
	
	/**
	 * 
	 * @描述:插入行为结果
	 * @作者：tgj [工号：1075]
	 * @日期：2014-12-13 下午12:05:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertXwjg(RcxwjgForm model) throws Exception {
		
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		//处理附件
		FormFile file = (FormFile) files.get("lbfj");
		
		if (file != null && !StringUtil.isNull(file.getFileName())){
			String basePath = resource.getString("filesys.local.dir");
			//如果没有给定文件存储路径，就默认给系统用户目录
			if(StringUtils.isNull(basePath)){
				basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
			}
			String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
			
			if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
				File srcFile = FileUtil.conversionFormFile(file);
				File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
				FileUtils.copyFile(srcFile, destFile);
				model.setFjlj(destFile.getAbsolutePath());
			}
		} else if (file != null) {
			model.setFjlj("");
		}
		
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}
	
	/**
	 * 
	 * @描述:更改行为结果
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午04:31:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXwjg(RcxwjgForm model) throws Exception {
		
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		//处理附件
		FormFile file = (FormFile) files.get("lbfj");
		
		if (file != null && !StringUtil.isNull(file.getFileName())){
			String basePath = resource.getString("filesys.local.dir");
			//如果没有给定文件存储路径，就默认给系统用户目录
			if(StringUtils.isNull(basePath)){
				basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
			}
			String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
			
			if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
				File srcFile = FileUtil.conversionFormFile(file);
				File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
				FileUtils.copyFile(srcFile, destFile);
				model.setFjlj(destFile.getAbsolutePath());
				model.setFjmc(file.getFileName());
			}
		} else if (file != null) {
			model.setFjlj("");
		}
		
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}

	/**
	 * 
	 * 查看行为结果
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 上午10:33:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 *             Map<String,String> 返回类型
	 * @throws
	 */
	public Map<String, String> getOneXwjgList(String xwjgId) throws Exception {
		return dao.getOneXwjgList(xwjgId);
	}
	/**
	 * 
	 * @描述:获取日行为结果参数配置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-9 上午11:30:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCspz(){
		return dao.getCspz();
	}
	
	
	/**
	 * 
	 * 查看已获行为记录信息
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 上午10:34:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreXwjgList(RcxwjgForm model,HashMap<String,String> cspzMap)throws Exception {
		return dao.getMoreXwjgList(model,cspzMap);
	}
	
	/**
	 * 判断更改后的行为信息是否存在行为结果库中 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-12 下午05:12:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXwwhForxwjg(String xwjgId) throws Exception {
		boolean updateResult = dao.checkXwwhForxwjg(xwjgId);
		return updateResult;
	}
	
	/**
	 * 
	 * 学生能够查看到自己的操行分分数，及相关记录
	 * @作者：Dlq[工号：995]
	 * @日期：2013-10-16 上午10:31:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<String[]> getStuRcswList(String xh) throws Exception {
		return dao.getStuRcswList(xh);
	}
	
	
	/**
	 * 
	 * @描述:根据学号查询日常事务
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcswList(String xh) {
		HashMap<String,String> csszMap = dao.getCspz();
		return dao.getRcswList(xh,csszMap);
	}

	/** 
	 * @描述: 判断信息是否重复(学号、学期、学年、行为列表、发生时间)
	 * @作者：HongLin[工号：707]
	 * @日期：2014-2-24 下午05:47:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String xwlbStr,String fssjStr){
		String [] xwlbArr = xwlbStr.split("!!");
		String [] fssjArr = fssjStr.split("!!");
		List<HashMap<String,String>> list = dao.getRcxwxxSfcf(request,xh,xn,xq,xwlbArr,fssjArr);
		String message = "无";
		if(list!=null && list.size()>0){
			message = "行为记录信息有重复记录：<br/>";
			for (int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					message+="【行为类别："+list.get(i).get("rcxwlbmc")+"，发生时间："+list.get(i).get("fssj")+"】";	
				}else{
					message+="【行为类别："+list.get(i).get("rcxwlbmc")+"，发生时间："+list.get(i).get("fssj")+"】、";
				}
				
			}
			message+="，请确认！";
		}
		return message;
	}

	/**
	 * 
	 * @描述:温大文明品行实践课评定导出
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-12-1 下午03:11:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void xsPxsjDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String yqdm = searchModel.getSearch_tj_yqdm()[0];
		String xn = searchModel.getSearch_tj_xn()[0];
		String xq = searchModel.getSearch_tj_xq()[0];
		List<HashMap<String,String>> resultList = dao.getpxfList(yqdm, xn, xq);
		String xqmc = dao.getXqmc(xq);
		String yqmc = dao.getYqmc(yqdm);
		// 设置标题
		StringBuffer title = new StringBuffer();
		title.append(xn+xqmc+"温州大学"+yqmc+"文明品行实践课评定");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("文明品行实践课评定", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);			
			ws.addCell(new Label(0, 2, "楼栋", wcf2));
			ws.addCell(new Label(1, 2, "寝室号", wcf2));
			ws.addCell(new Label(2, 2, "姓名", wcf2));
			ws.addCell(new Label(3, 2, "班级", wcf2));
			ws.addCell(new Label(4, 2, "政治面貌", wcf2));
			ws.addCell(new Label(5, 2, "品行基础分80分", wcf2));
			ws.addCell(new Label(6, 2, "品行实际总分", wcf2));
			ws.addCell(new Label(7, 2, "加分项目", wcf2));
			ws.addCell(new Label(8, 2, "减分项目", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 10);
					ws.setColumnView(2, 10);
					ws.setColumnView(3, 20);
					ws.setColumnView(4, 20);
					ws.setColumnView(5, 10);
					ws.setColumnView(6, 10);
					ws.setColumnView(7, 30);
					ws.setColumnView(8, 30);
					ws.addCell(new Label(0, 3 + i, map.get("ldmc"), wcf2));
					ws.addCell(new Label(1, 3 + i, map.get("qsh"), wcf2));
					ws.addCell(new Label(2, 3 + i, map.get("xm"), wcf2));
					ws.addCell(new Label(3, 3 + i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(4, 3 + i, map.get("zzmmmc"), wcf2));
					ws.addCell(new Label(5, 3 + i, map.get("zfs"), wcf2));
					ws.addCell(new Label(6, 3 + i, map.get("sjfz"), wcf2));
					ws.addCell(new Label(7, 3 + i, map.get("plusxm"), wcf2));
					ws.addCell(new Label(8, 3 + i, map.get("cutxm"), wcf2));
		}
		}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 
	 * @描述: 日常行为数据导出（2大类能力素质测评与思想道德素质测评-浙江医学个性化）
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-20 下午05:01:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void rcxwsjDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String xn = searchModel.getSearch_tj_xn()[0];
		List<HashMap<String,String>> resultList = dao.getrcxwfList(xn);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("素质测评分导出", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充	
			ws.addCell(new Label(0, 0, "系", wcf2));
			ws.addCell(new Label(1, 0, "专业", wcf2));
			ws.addCell(new Label(2, 0, "班级", wcf2));
			ws.addCell(new Label(3, 0, "学号", wcf2));
			ws.addCell(new Label(4, 0, "姓名", wcf2));
			ws.addCell(new Label(5, 0, "能力分（基础60分）", wcf2));
			ws.addCell(new Label(6, 0, "思想道德分（基础60分）", wcf2));
			ws.addCell(new Label(7, 0, "总分", wcf2));
			ws.addCell(new Label(8, 0, "获得学分", wcf2));
			ws.addCell(new Label(9, 0, "考核结果", wcf2));
			ws.addCell(new Label(10, 0, "备注", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 20);
					ws.setColumnView(1, 30);
					ws.setColumnView(2, 30);
					ws.setColumnView(3, 15);
					ws.setColumnView(4, 15);
					ws.setColumnView(5, 15);
					ws.setColumnView(6, 15);
					ws.setColumnView(7, 10);
					ws.setColumnView(8, 10);
					ws.setColumnView(9, 20);
					ws.setColumnView(10, 50);
					ws.addCell(new Label(0, 1+i, map.get("xymc"), wcf2));
					ws.addCell(new Label(1, 1+i, map.get("zymc"), wcf2));
					ws.addCell(new Label(2, 1+i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(3, 1+i, map.get("xh"), wcf2));
					ws.addCell(new Label(4, 1+i, map.get("xm"), wcf2));
					ws.addCell(new Label(5, 1+i, map.get("nlf"), wcf2));
					ws.addCell(new Label(6, 1+i, map.get("sxddf"), wcf2));
					ws.addCell(new Label(7, 1+i, map.get("zf"), wcf2));
					ws.addCell(new Label(8, 1+i, map.get("xf"), wcf2));
					ws.addCell(new Label(9, 1+i, map.get("khjg"), wcf2));
					ws.addCell(new Label(10, 1+i, map.get("bz"), wcf2));
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void rcxwtjbDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		SearchModel searchModel = myForm.getSearchModel();
		String xn = searchModel.getSearch_tj_xn()[0];
		HashMap<String,String> cspzMap = dao.getCspz();
		List<HashMap<String,String>> resultList = dao.getrcxwzfList(cspzMap,xn);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("日常行为分统计导出", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充	
			ws.addCell(new Label(0, 0, "学号", wcf2));
			ws.addCell(new Label(1, 0, "姓名", wcf2));
			ws.addCell(new Label(2, 0, "年级", wcf2));
			ws.addCell(new Label(3, 0, "学院", wcf2));
			ws.addCell(new Label(4, 0, "专业", wcf2));
			ws.addCell(new Label(5, 0, "班级", wcf2));
			ws.addCell(new Label(6, 0, "学年", wcf2));
			ws.addCell(new Label(7, 0, "日常行为总分", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 10);
					ws.setColumnView(2, 10);
					ws.setColumnView(3, 15);
					ws.setColumnView(4, 15);
					ws.setColumnView(5, 15);
					ws.setColumnView(6, 15);
					ws.setColumnView(7, 15);
					ws.addCell(new Label(0, 1+i, map.get("xh"), wcf2));
					ws.addCell(new Label(1, 1+i, map.get("xm"), wcf2));
					ws.addCell(new Label(2, 1+i, map.get("nj"), wcf2));
					ws.addCell(new Label(3, 1+i, map.get("xymc"), wcf2));
					ws.addCell(new Label(4, 1+i, map.get("zymc"), wcf2));
					ws.addCell(new Label(5, 1+i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(6, 1+i, map.get("xn"), wcf2));
					ws.addCell(new Label(7, 1+i, map.get("fz"), wcf2));
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-14 上午11:34:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param os
	 * @param user
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void rcxwdlfDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		List<HashMap<String,String>> xwdlList = new RcxwdmwhDao().getRcxwdlList();
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = dao.getXwdlfList(myForm,user,xwdlList);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("日常行为大类分统计导出", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// 填充	
			ws.addCell(new Label(0, 0, "学号", wcf2));
			ws.addCell(new Label(1, 0, "姓名", wcf2));
			ws.addCell(new Label(2, 0, "年级", wcf2));
			ws.addCell(new Label(3, 0, "学院", wcf2));
			ws.addCell(new Label(4, 0, "专业", wcf2));
			ws.addCell(new Label(5, 0, "班级", wcf2));
			ws.addCell(new Label(6, 0, "学年", wcf2));
			for (int i = 0; i < xwdlList.size(); i++) {
				ws.addCell(new Label(i+7, 0,xwdlList.get(i).get("rcxwlbdlmc") , wcf2));
			}
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 10);
					ws.setColumnView(2, 10);
					ws.setColumnView(3, 15);
					ws.setColumnView(4, 15);
					ws.setColumnView(5, 15);
					ws.setColumnView(6, 15);
					ws.addCell(new Label(0, 1+i, map.get("xh"), wcf2));
					ws.addCell(new Label(1, 1+i, map.get("xm"), wcf2));
					ws.addCell(new Label(2, 1+i, map.get("nj"), wcf2));
					ws.addCell(new Label(3, 1+i, map.get("xymc"), wcf2));
					ws.addCell(new Label(4, 1+i, map.get("zymc"), wcf2));
					ws.addCell(new Label(5, 1+i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(6, 1+i, map.get("xn"), wcf2));
					for (int j = 0; j < xwdlList.size(); j++) {
						ws.setColumnView(j+7, 15);
						ws.addCell(new Label(j+7, 1+i, map.get("fz"+j), wcf2));
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:日常行为大类分汇总
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-13 下午03:37:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXwdlfList(RcxwjgForm model,User user) throws Exception{
		RcxwdmwhDao rcxwdmwhDao = new RcxwdmwhDao();
		List<HashMap<String,String>> xmdlList = rcxwdmwhDao.getRcxwdlList();
		return dao.getXwdlfList(model,user,xmdlList);
		
	}
	
	public boolean getCffWarnStudent(String xh){
		return dao.getCffWarnStudent(xh);
	}

	/**
	 * @描述:青岛滨海个性化：查询思想品德课成绩汇总表数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月31日 上午9:49:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSxpdcjhzList(RcxwjgForm model, User user) throws Exception {
		return dao.getSxpdcjhzList(model, user);
	}

	/**
	 * @描述:青岛滨海个性化：生成思想品德课成绩汇总表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月31日 上午9:02:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param resultList
	 * @return
	 * File 返回类型 
	 * @throws Exception
	 */
	public File getSxpdcjhzFile(List<HashMap<String,String>> resultList) throws Exception {
		
		Map<String,List<Map<String,String>>> classMap = new OtmMapping().setResultMap("bjdm", resultList).getResultMap();
		
		//画Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		if(!file.exists()){
			file.createNewFile();
		}	
		
		//创建工作簿
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		
		//设置相关格式
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置标题字体
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体
		
		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
		WritableCellFormat body_bz_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
		
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
		title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置标题单元格边框
//		title_cf.setBackground(Colour.YELLOW);	//设置标题背景色
		
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头水平对齐
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		
		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
		body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
		body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 
		Collection<List<Map<String,String>>> classCollection = classMap.values(); //相关学生列表集合，以班级为单位
		Iterator<List<Map<String,String>>> iterator = classCollection.iterator();
		
		int i = 0;
		while(iterator.hasNext()){
			List<Map<String,String>> list = iterator.next();	//班级对应的相关学生列表
			String bjmc = list.get(0).get("bjmc");	//班级名称
			String xn = list.get(0).get("xn");		//学年
			String xqmc = list.get(0).get("xqmc");	//学期名称
			String title = "青岛滨海学院学生思想品德课成绩汇总表";	//标题
			String title_tip = bjmc+" / "+xn+"学年度 / "+xqmc; //标题说明
			
			if(StringUtils.isNull(bjmc)) bjmc = "未知班级";
			
			//创建工作表
			WritableSheet sheet = wwb.createSheet(bjmc, i);
			
			//设置各列列宽
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 14);
			sheet.setColumnView(2, 10);
			sheet.setColumnView(3, 10);
			sheet.setColumnView(4, 10);
			sheet.setColumnView(5, 10);
			sheet.setColumnView(6, 10);
			sheet.setColumnView(7, 10);
			sheet.setColumnView(8, 10);
			sheet.setColumnView(9, 10); 
			sheet.setColumnView(10, 10);
			sheet.setColumnView(11, 14);
			
			//合并单元格
			sheet.mergeCells(0, 0, 11, 0);	//青岛滨海学院学生思想品德课成绩汇总表
			sheet.mergeCells(0, 1, 11, 1);	//xx班级  xx学年  xx学期
			sheet.mergeCells(0, 2, 0, 3);	//名次
			sheet.mergeCells(1, 2, 1, 3);	//学号
			sheet.mergeCells(2, 2, 2, 3);	//姓名
			sheet.mergeCells(3, 2, 7, 2);	//日常行为表现分
			sheet.mergeCells(8, 2, 9, 2);	//理论课成绩
			sheet.mergeCells(10, 2, 10, 3);	//总分
			sheet.mergeCells(11, 2, 11, 3);	//备注
			
			//创建标题及表头
			Label t_0_0 = new Label(0, 0,title,title_cf);
			Label h_0_1 = new Label(0,1,title_tip,head_cf);
			Label h_0_2 = new Label(0,2,"名次",head_cf);
			Label h_1_2 = new Label(1,2,"学号",head_cf);
			Label h_2_2 = new Label(2,2,"姓名",head_cf);
			Label h_3_2 = new Label(3,2,"日常行为表现分",head_cf);
			Label h_3_3 = new Label(3,3,"基础分",head_cf);
			Label h_4_3 = new Label(4,3,"加分",head_cf);
			Label h_5_3 = new Label(5,3,"减分",head_cf);
			Label h_6_3 = new Label(6,3,"总分",head_cf);
			Label h_7_3 = new Label(7,3,"60%",head_cf);
			Label h_8_2 = new Label(8,2,"理论课成绩",head_cf);
			Label h_8_3 = new Label(8,3,"总分",head_cf);
			Label h_9_3 = new Label(9,3,"40%",head_cf);
			Label h_10_2 = new Label(10,2,"总分",head_cf);
			Label h_11_2 = new Label(11,2,"备注",head_cf);
			
			sheet.addCell(t_0_0);
			sheet.addCell(h_0_1);
			sheet.addCell(h_0_2);
			sheet.addCell(h_1_2);
			sheet.addCell(h_2_2);
			sheet.addCell(h_3_2);
			sheet.addCell(h_3_3);
			sheet.addCell(h_4_3);
			sheet.addCell(h_5_3);
			sheet.addCell(h_6_3);
			sheet.addCell(h_7_3);
			sheet.addCell(h_8_2);
			sheet.addCell(h_8_3);
			sheet.addCell(h_9_3);
			sheet.addCell(h_10_2);
			sheet.addCell(h_11_2);
			
			//遍历创建单元格
			if(list.size()>0){
				for(int j=0;j<list.size();j++){
					Map<String,String> map = list.get(j);
					Label rank = new Label(0, j+4, map.get("rank"), body_cf);	//名次
					Label xh = new Label(1, j+4, map.get("xh"), body_cf);		//学号
					Label xm = new Label(2, j+4, map.get("xm"), body_cf);		//姓名
					Label jcf = new Label(3, j+4, map.get("jcf"), body_cf);		//基础分
					Label pf = new Label(4, j+4, map.get("pf"), body_cf);		//加分
					Label mf = new Label(5, j+4, map.get("mf"), body_cf);		//减分
					Label tf = new Label(6, j+4, map.get("tf"), body_cf);		//素质总分
					Label tf_p6 = new Label(7, j+4, map.get("tf_p6"), body_cf);	//素质总分60%
					Label llf = new Label(8, j+4, map.get("llf"), body_cf);		//理论分总分
					Label llf_p4 = new Label(9, j+4, map.get("llf_p4"), body_cf);		//理论分总分40%
					Label zf = new Label(10, j+4, map.get("zf"), body_cf);		//总分
					Label bz = new Label(11, j+4, "", body_cf);		//备注
					
					sheet.addCell(rank);
					sheet.addCell(xh);
					sheet.addCell(xm);
					sheet.addCell(jcf);
					sheet.addCell(pf);
					sheet.addCell(mf);
					sheet.addCell(tf);
					sheet.addCell(tf_p6);
					sheet.addCell(llf);
					sheet.addCell(llf_p4);
					sheet.addCell(zf);
					sheet.addCell(bz);
				}
			}
			i++;
		}
		
		//如果数据为空
		if(classCollection==null||classCollection.size()==0){
			//创建工作表
			WritableSheet sheet = wwb.createSheet("本次导出数据为空", 0);
			sheet.setColumnView(0, 15);
			Label msg = new Label(0, 0,"暂无数据！");
			sheet.addCell(msg);
		}
		 
		wwb.write();
		wwb.close();
			
		return file;
	}
	
	/**
	 * @描述: 根据获取的id查询相关信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-14 上午11:01:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getKptzsForId(String id){
		return dao.getKptzsForId(id);
	}
}
