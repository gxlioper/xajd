/**
 * @部门:学工产品(1)部
 * @日期：2017-6-22 上午09:41:25 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import jxl.CellFeatures;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-6-22 上午09:41:52 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcwhService extends SuperServiceImpl<ZcwhForm,ZcwhDao>{
	private ZcwhDao dao = new ZcwhDao();
	public static final String tjzt_tj = "1"; //评奖评优，提交状态提交
	public static final String tjzt_qxtj = "2"; //评奖评优，提交状态为取消提交
	
	public ZcwhService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述: 查看是否还有未提交记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-23 上午11:37:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isSubmitInfo(ZcwhForm t, User user) throws Exception {
		String num = dao.isSubmitInfo(t, user);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @描述: 获得等级
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-6-28 上午09:25:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDj() {
		return dao.getDj();
	}
	
	/**
	 * @描述: 综测分维护
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 上午09:41:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcwhForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcwhList (ZcwhForm zcwhForm,User user,String zcxmdmForTop)
		throws Exception {
		/*获得综测分项目,fjdm不为top的子项*/
		List<HashMap<String,String>> zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop);
		/*综测维护*/
		return dao.getZcwhList(zcwhForm,zcxmList,user,zcxmdmForTop);
	}
	
	/**
	 * @描述: 允许录入综测分的综测项目
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 上午11:34:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		return dao.getZcxmList();
	}
	
	/**
	 * @描述: 实时保存综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 下午05:43:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveZcfs(ZcwhForm t,User user) throws Exception{
		/*判断该学年、学期，该学生是否已经录入了此项目的分数*/
		HashMap<String,String> zcfsMap  = dao.getFsid(t);
		t.setLrr(user.getUserName());
		t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
		/*无：插入 有：更新*/
		if (StringUtil.isNull(zcfsMap.get("id"))){
			return dao.runInsert(t);
		} else {
			t.setId(zcfsMap.get("id"));
			return dao.runUpdate(t);
		}
	}
	
	/**
	 * @描述: 批量取消参评
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 下午05:47:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qxcp(String values, User user) throws Exception {
		if(values == null || "".equalsIgnoreCase(values)){
			logger.error("未选择取消参评人员！");
			throw new NullPointerException();
		}
		boolean qxcp = false;
		/*插入调整记录*/
		qxcp=dao.insertTzjl(values,user);
		if(!qxcp){
			return false;
		}
		/*更新评奖人员库*/
		qxcp=dao.updateCpmd(values,user);
		
		/*实际我个人认为，取消这个学生的参评资格应该删除分数记录表中的数据，
		 * 如果不删除的话，那么调整班级的时候，数据会带过去*/
		return qxcp;
	}
	
	/**
	 * @描述: 查询增加参评学生列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 上午09:39:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAddCpxsList(ZcwhForm model) throws Exception{
		return dao.getAddCpxsList(model);
	}
	
	/**
	 * @描述: 批量调整学生
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午02:50:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tzhbjdm
	 * @param user
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean bjtzs(String tzhbjdm, User user,String ids) throws Exception {
			
		String [] id = ids.split(",");
		//没有单独写方法，循环以前的
		for (int i = 0; i < id.length; i++) {
			bjtz(tzhbjdm, user, id[i]);
		}
		return true;
	}
	
	
	/**
	 * @描述: 将学生从一个班级调整到另一个班级
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午02:52:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tzhbjdm
	 * @param user
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean bjtz(String tzhbjdm, User user,String xh) throws Exception {
		
		CsszDao csszDao = new CsszDao();
		CsszForm csszModel = csszDao.getModel();
		String xn = csszModel.getXn();
		/*判断ID是否在当前周期评奖名单内*/
		boolean isHavePjry = dao.isHavePjry(xh,xn);
		/*插入班级调整记录*/
		if(isHavePjry){
			 /*从一个班级到另一个班级*/
			 dao.insertbjTzjl(xh,xn,tzhbjdm,user);
		}else{
			 /*从未参评到另一个班级*/
			 dao.insertbjTzjl1(xn,xh,tzhbjdm,user);
		}
		 
		/*更新班级调整评奖人员库*/
		 if(isHavePjry){
			/*从一个班级调整到另一个班级*/
			dao.updateBjtzCpmd(xh,xn,tzhbjdm);
		 }else{
			//从不参评调整到参评
			dao.insertBjtzCpmd(xn,xh,tzhbjdm);
		 }
//		 //现是学年综测
//		 if(CsszService.getZczq()){
//			 dao.updateXnzc(xn,xh);
//		 }
		return true;
	}
	
	/**
	 * @描述: 检测是否可提交综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午05:04:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcwhForm
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanSubmit(String values, ZcwhForm zcwhForm, User user) throws Exception {
		return dao.isCanSubmit(values,zcwhForm,user);
	}
	
	/**
	 * @描述: 提交参评人员状态
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-6 上午09:32:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param tjzt
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean tjCpxs(String values, String tjzt,ZcwhForm model, User user) throws Exception {
		if("tj".equals(tjzt)){
			tjzt=tjzt_tj;
		}else{
			tjzt=tjzt_qxtj;
		}
		return dao.tjCpxs(values,tjzt,model,user);
	}
	
	/**
	 * @描述: 通过ID获取学号列表
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-7-6 上午09:51:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws SQLException
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getXhArray(String values) throws SQLException {
		return dao.getXhArray(values);
	}
	
	/**
	 * @描述: 取消提交综测分提交
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-7 下午04:27:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcfqxList(ZcwhForm t, User user)
		throws Exception {
		
		return dao.getZcfqxList(t, user);
	}
	
	/**
	 * @描述: 参评名单导出
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-7-10 下午02:54:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcfList
	 * @param zcxmList
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File createImportTemplateDc(List<HashMap<String,String>> zcfList,List<HashMap<String,String>> zcxmList, User user) 
		throws Exception{
		
		WritableWorkbook wwb = null;
		
		//导出文件存放 的临时目录
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		File file = new File( tempDir.getPath() + "/" +"综测分导出.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//创建excel工作表
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("综测分导出", 0);
			
			//可编辑综测分的综测项目
			CsszDao csszDao = new CsszDao();
			CsszForm csszModel = csszDao.getModel();

			//固定表头学号、姓名
			ws.addCell(new Label(0, 0, "学号"));
			ws.addCell(new Label(1, 0, "姓名"));//TODO
			ws.addCell(new Label(2, 0, "班级名称"));
			
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(3+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将项目代码作为注释，用于识别导入
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			for (int i = 0 , j = zcfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
				ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
				ws.addCell(new Label(2, i+1, zcfList.get(i).get("bjmc")));
				for (int m = 0 , n = zcxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+3, i+1, zcfList.get(i).get("fs"+m)));
				}
			}
			
			wwb.write();
			wwb.close();
		}catch (Exception e) {
			throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
		}
		
		file.setWritable(true);
		return file;
	}
	
	/**
	 * @描述: 导入验证数据条数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-11 上午09:19:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param num
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDownload(ZcwhForm model,User user, String num,String zcxmdmForTop) throws Exception {
		
		List<HashMap<String,String>> zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop);
		
		//查询学生及已经录入的分数
		List<HashMap<String,String>> zcfList = dao.getCpxsExportList(model, zcxmList,user,zcxmdmForTop);
		boolean boo = false;
		if(zcfList!=null&&0!=zcfList.size()){
			boo = Integer.parseInt(zcfList.get(0).get("total"))> Integer.parseInt(num)? false : true;
		}
		return boo;
	}
	
	/**
	 * @描述: 创建导入下载模板
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-11 下午01:54:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File createImportTemplate(ZcwhForm model,User user,String zcxmdmForTop) throws Exception{
		
		/*用于生成excel文件*/
		WritableWorkbook wwb = null;
		
		/*导出文件存放的临时目录*/
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		/*创建导出文件*/
		File file = new File(tempDir.getPath() + "/" + "综测分导入模板.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			/*创建excle工作表*/
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("综测分导入模板", 0);
			
			/*综测项目（除顶级项目）*/
			List<HashMap<String,String>> zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop); 
			
			/*固定表头学号、姓名*/
			ws.addCell(new Label(0, 0, "学号"));
			ws.addCell(new Label(1, 0, "姓名"));
			
			for(int i = 0, j = zcxmList.size(); i < j; i++){
				Label label = new Label(2+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures();
				/*讲项目代码作为注释，用于识别导入*/
				wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
				label.setCellFeatures(wcfeatures);
				ws.addCell(label);
			}
			
			/*不分页*/
			model.getPages().setPageSize(Integer.MAX_VALUE);
			
			/*综测分List*/
			List<HashMap<String,String>> zcfList = new ArrayList<HashMap<String,String>>();
			zcfList = dao.getCpxsExportList(model, zcxmList,user,zcxmdmForTop);
			
			for (int i = 0 , j = zcfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
				ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
				
				for (int m = 0 , n = zcxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+2, i+1, zcfList.get(i).get("fs"+m)));
				}
			}
			WritableSheet ws1 = wwb.createSheet("项目等级名", 1);
			
			/*等级名称*/
			List<String> DjmcList = getDjmc();
			
			/*等级list*/
			List<HashMap<String, String>> djList = getDj();
			
			if(!DjmcList.isEmpty()){
				WritableCellFormat wcf = new WritableCellFormat();
				WritableFont wf = new WritableFont(WritableFont.ARIAL);
				wf.setBoldStyle(WritableFont.BOLD);
				wf.setPointSize(10);
				wf.setColour(Colour.BLUE);
				wcf.setAlignment(Alignment.LEFT);
				wcf.setWrap(true);
				wcf.setFont(wf);
				
				for (int i = 0; i < DjmcList.size(); i++) {
					ws1.addCell(new Label(i, 0, DjmcList.get(i),wcf));
					
					int z=1;
					for (int j = 0; j < djList.size(); j++) {
						if(DjmcList.get(i).equals(djList.get(j).get("xmmc"))){
							ws1.addCell(new Label(i, z, djList.get(j).get("mc")));
							z++;
						}
					}
				}
			}
			
			wwb.write();
			wwb.close();
			
		}catch (Exception e){
			throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
		}
		
		file.setWritable(true);
		return file;
	}
	
	/**
	 * @描述: 获得等级名称list
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-11 下午03:52:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getDjmc() throws SQLException{
		return dao.getDjmc();
	}
	
	/**
	 * 导入综测分数
	 */
	public File importZcfs(ZcwhForm model,User user,String zcxmdmForTop) throws Exception{
		
		FormFile importFile = model.getImportFile();
		/*将FormFile 转换为File 对象*/
		File file = FileUtil.conversionFormFile(importFile);
		
		/*获取Excle工作表*/
		Workbook book = Workbook.getWorkbook(file);
		
		List<HashMap<String,String>> zcxmList = null;
		zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop);
		
		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		/*设置错误消息提示·列宽*/
		ws.setColumnView(zcxmList.size()+2, 30);
		
		/*参评学院学生信息*/
		String[] stus = dao.getStuById(model,user);
		
		/*导入模版与综测结构比较验证*/
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			String xmdm = zcxmList.get(i).get("xmdm");
			String xmmc = zcxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(2+i, 0).getContents();
			
			/*验证下表头是否与综测结构的导出模版一致*/
			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
		}
		
		/*检测导入数据*/
		int rows = ws.getRows();
		List<String[]> params = new ArrayList<String[]>();
		
		boolean checkResult = true;
		for (int i = 1 ; i < rows ; i++){
			StringBuilder errorMessage = new StringBuilder();
			
			String xh = ws.getCell(0, i).getContents();
			String xm = ws.getCell(1, i).getContents();
			String[] param = null;
			if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
				/*学号和姓名为空*/
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
			}else if (!ArrayUtil.contains(stus, xh)){/*验证学号有效性*/
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_FAIL));
			}else{
				
				for (int m = 0 ; m < zcxmList.size() ; m++){
					String xmfz = ws.getCell(m+2, i).getContents().trim();
					
					if (StringUtil.isNull(xmfz)){
						/*项目分数不能为空 */
						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NULL));
						break;
					}
					
					/*综测项目类型  【等级】 为等级，可以录入非数字*/
					String xmlx = zcxmList.get(m).get("xmlx");
					
					/*综测项目名称*/
					String drxmmc = zcxmList.get(m).get("xmmc");
					
					if(!"等级".equals(xmlx)){
						
						/*验证项目分数的 数字有效性*/
						Double xmf = 0.0;
						
						try {
							xmf = Double.valueOf(xmfz.trim());
						} catch (Exception e) {
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NOTNUMBER,
									new Object[]{zcxmList.get(m).get("xmmc")}));
							break;
						}
						
						/*验证长度*/
						if (xmfz.length() > 10){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH));
							break;
						}
						
						/*验证最大、最小限制*/
						Double max = Double.valueOf(zcxmList.get(m).get("zdfz"));
						Double min = Double.valueOf(zcxmList.get(m).get("zxfz"));
						
						if (xmf > max || xmf < min){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDZX,
									new Object[]{zcxmList.get(m).get("xmmc"),max,min}));
							break;
						}
					}else{
						boolean checkFlag = false;
						/*验证录入的等级是否符合要求*/
						List<HashMap<String, String>> djList = dao.getDj();
						for (int j = 0; j < djList.size(); j++) {
							if(djList.get(j).get("mc").equals(xmfz)&&djList.get(j).get("xmmc").equals(drxmmc)){
								xmfz = djList.get(j).get("dm");
								checkFlag = true;
								break;
							}
						}
						if(!checkFlag){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
									new Object[]{xmfz}));
							break;
						}
					}
					param=null;
					param= new String[]{xh,zcxmList.get(m).get("xn"),
//							  zcxmList.get(m).get("xq"),
							  zcxmList.get(m).get("xmdm"),
							  xmfz,user.getUserName(),
							  xh,zcxmList.get(m).get("xmdm")};
					params.add(param);
				}
			}
				//错误消息追加
				if (errorMessage.length() > 0){
					
					WritableCellFormat wcf = new WritableCellFormat();
					WritableFont wf = new WritableFont(WritableFont.ARIAL);
					wf.setColour(Colour.RED);
					wcf.setFont(wf);
					wcf.setAlignment(Alignment.CENTRE);

					int y = 2;
					ws.addCell(new Label(zcxmList.size()+y, i, errorMessage.toString(),wcf));
					checkResult = false;
				}
			}
			//验证OK的导入，失败的导出
			if(!params.isEmpty()){
				System.out.println("action========"+System.currentTimeMillis());
					dao.batchInsertZcfs(params);//批量插入
				System.out.println("end=========="+System.currentTimeMillis());
			}
				
		if (!checkResult){
			WritableSheet ws1 = wwb.createSheet("错误数据", 1);
			ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
			ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(2+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将项目代码作为注释，用于识别导入
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws1.addCell(label);
			}
			
			int z = 1;//已打印行号
			int t = 2;
			int x = 1;
			for (int i = 0; i < rows; i++) {
				if(!StringUtils.isBlank((ws.getCell(zcxmList.size()+t,i).getContents()).trim())){
					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
					for (int j = 0; j < zcxmList.size()+x; j++) {
						ws1.addCell(new Label(j+2,z,ws.getCell(j+2,i).getContents()));
					}
					z++;
				}
			}
			
			wwb.removeSheet(0);
			wwb.write();
			wwb.close();
			return file;
		}
		return null;
	}
	
	/**
	 * @描述: 综测分提交检测
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-12 上午10:17:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkZcfSubmit(ZcwhForm model) throws Exception{
		
		boolean isCanSubmit = false;
		
		//将默认项目 和 同步接口项目的分数加到分数记录表
		initDefaultZcfs(model.getXn(),model.getId(),model.getZcxmdmForTop());
		
		/*可录项目数 * 学生数  <= 分数记录数*/
		isCanSubmit = Boolean.valueOf(dao.getSfyWlr(model));
		
		if (!isCanSubmit){
			return false;
		}
		
		/*检测是否有NULL分值 的分数记录*/
		isCanSubmit = Integer.valueOf(dao.getNullZcf(model)) == 0;
		
		return isCanSubmit;
	}
	
	/**
	 * @描述: 初始化默认综则分数（为支持批量重写）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-12 下午03:34:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param id
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void initDefaultZcfs(String xn,String id,String zcxmdmForTop) throws Exception{
		/*根据ID获取学院信息*/
		Map<String,String> map = getXyxxById(id);
		String xydm = map.get("xydm");
		
		/*根据id和学院代码，在点击提交的时候，把选择的学院所有人员信息插到综测分数表中，分数为空*/
		dao.insertDefaultZcxmf(xn,id,xydm,zcxmdmForTop);
	}
	
	/**
	 * @描述: 根据ID获取学院信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-12 下午03:38:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXyxxById(String id){
		return dao.getXyxxById(id);
	}
	
	/**
	 * @描述: 提交综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 上午09:06:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitZcfs(ZcwhForm model,User user){
		/*根据ID获取学院信息*/
		Map<String,String> map = getXyxxById(model.getId());
		/*学年*/
		String xn = map.get("xn");
		/*学院代码*/
		String xydm = map.get("xydm");
		 try{
			 /*执行存储过程*/
			 Thread thread = new Thread(new ComputeZcpm(xn,xydm));
			 thread.start();
			 
			 boolean result = dao.submitXyzcf(model.getId(), user.getUserName());
			 
			 return result;
		 }catch (Exception e) {
			e.printStackTrace();
		 }
		return false;
	}
	
	/**
	 * @描述: 取消提交综测记录
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-3 上午11:16:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelSubmit(ZcwhForm model) throws Exception{
		boolean result = false;
		result = dao.getCancelSubmit(model.getId());
		return result;
	}
	
	/**
	 * @描述: 取消提交综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 上午10:45:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelTj( User user,ZcwhForm model)throws Exception {
		
		/*定义勾选记录的ID*/
		String id = model.getId();
		boolean cancelTj = false;
		
		/*参数设置信息*/
		CsszDao csszDao = new CsszDao();
		CsszForm csszForm = csszDao.getModel();
		/*参数设置学年*/
		String xn = csszForm.getXn();
		
		//插入取消记录
		cancelTj =  dao.insertTzjl(id,user,model,xn);
		
		if(!cancelTj){
			return false;
		}
		
		/*更新评奖人员库*/
		cancelTj = dao.updateCpmd(id,user,tjzt_qxtj);
		
		if(cancelTj){
			Map<String,String> map = getXyxxById(id);
			String xydm = map.get("xydm");
			/*计算综测分*/
			Thread thread = new Thread(new ComputeZcpm(xn,xydm));
			thread.start();
		}
		
		return cancelTj;
	}
	
	/**
	 * @描述: 综测维护查看
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 下午04:25:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param zcxmList
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcwhView(ZcwhForm t,List<HashMap<String, String>> zcxmList, User user,String zcxmdmForTop)
		throws Exception {
	
		return dao.getZcwhView(t, zcxmList,user,zcxmdmForTop);
	}
	
	/**
	 * @描述: 同步综测分（纪实考评五个类别分）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-7 上午11:07:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param proName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getIntefaceData() throws Exception {
		
		boolean bool = false;
		String proName = "pro_pjpy_tbjskpzf";
		bool = dao.getIntefaceData(proName);
		return bool;
	}
	
	/**
	 * @描述: 综测总分
	 * @日期： 2018-1-3 下午03:38:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZczfByXh(String xh ,String xn){
		return dao.getZczfByXh(xh, xn);
	}
	
	/**
	 * @描述: ※※※※※综测fjdm为top的项目代码获取相关的综测子项※※※※※
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-28 下午03:07:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcxmdmForTop
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcxmListByFjdmisTop(String zcxmdmForTop)throws Exception {
		return dao.getZcxmListByFjdmisTop(zcxmdmForTop);
	}
}
