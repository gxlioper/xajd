/**
 * @部门:学工产品事业部
 * @日期：2016-4-23 下午01:35:39 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.fswh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.CellFeatures;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszDao;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszModel;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmDao;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 竞赛分数 
 * @作者： xiaxia [工号：1104]
 * @时间： 2016-4-23 下午01:35:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BfjsFswhService extends SuperServiceImpl<BfjsFswhModel, BfjsFswhDao> {
	private BfjsJsxmService jsxmService = new BfjsJsxmService();
	private BfjsJsxmDao jsxmDao = new BfjsJsxmDao();
	private BfjsCsszDao csszDao = new BfjsCsszDao();
	public static final String DEFAULT_TJ = "1"; //提交状态提交
	public static final String DEFAULT_QXTJ = "2"; //提交状态为取消提交
	
	public BfjsFswhService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述: 竞赛班级提交查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-24 上午10:16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getJsbjList(BfjsFswhModel t, User user)
		throws Exception {
		//获得竞赛分项目
		List<HashMap<String, String>> jsxmList = jsxmDao.getNoChildJsfxm();
		return dao.getJsbjList(t,jsxmList, user);
	}


	/**
	 * 
	 * @描述: 保存竞赛分数
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-24 下午03:07:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean saveBfjsFswh(BfjsFswhModel t,User user) throws Exception{
		
		//判断该学年、学期，该学生是否已经录入了此项目的分数
		HashMap<String,String> BfjsFswhMap  = dao.getFsid(t);
		
		t.setLrr(user.getUserName());
		t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
		
		//无：插入 有：更新
		if (StringUtil.isNull(BfjsFswhMap.get("id"))){
			return dao.runInsert(t);
		} else {
			t.setId(BfjsFswhMap.get("id"));
			return dao.runUpdate(t);
		}
	}
	
	
	/**
	 * 
	 * @描述: 检测班级竞赛分是否可以提交
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-24 下午04:57:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型
	 * @throws Exception 
	 */
	public boolean isCanSubmit(String values, BfjsFswhModel model, User user) throws Exception{
		
		return dao.isCanSubmit(values,model,user);
	}
	
	
	/**
	 * 
	 * @描述: 提交竞赛分
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-24 下午04:58:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型
	 * @throws Exception 
	 */
	public boolean submitBfjsFswh(String values, String tjzt, BfjsFswhModel model , User user) throws Exception{
		boolean result =true;
		String[] ids = values.split(",");
		if("tj".equals(tjzt)){
			tjzt=DEFAULT_TJ;
		}else{
			tjzt=DEFAULT_QXTJ;
		}
		for (int i = 0; i < ids.length; i++) {
			try{
				result=dao.submitBfjsFswh(ids[i],tjzt,model,user);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<HashMap<String,String>> xyList = getXyxxById(ids);
		for (int i = 0; i < xyList.size(); i++) {
			//计算综测分
			Thread thread = new Thread(new ComputeJspm(xyList.get(i).get("xn"), xyList.get(i).get("xq"), xyList.get(i).get("xydm")));
			thread.start();
		}
		return result;
		
	}


	/**
	 * 
	 * @描述: 批量取班级信息
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-26 下午01:51:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String,String>> getBjxxByIds(BfjsFswhModel model, User user) throws Exception{
		
		return dao.getBjxxByIds(model, user);
	}
	
	
	/**
	 * 
	 * @描述: 按ID取班级信息
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-26 下午01:51:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public List<HashMap<String,String>> getXyxxById(String[] ids){
		
		return dao.getXyxxById(ids);
	}
	
	
	/**
	 * 
	 * @描述: 创建导入模版
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-29 上午08:51:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * File 返回类型
	 */
	public File createImportTemplate(BfjsFswhModel model, User user) throws Exception{
		
		WritableWorkbook wwb = null;
		
		//导出文件存放 的临时目录
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);    
		//创建导出文件
		File file = new File( tempDir.getPath() + "/" +"竞赛分导入模版.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//创建excel工作表
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("竞赛分导入模版", 0);
			
			BfjsJsxmDao bfjsJsxmDao = new BfjsJsxmDao();
			//可编辑竞赛分的竞赛项目
		
			BfjsCsszModel csszModel = csszDao.getModel();
			List<HashMap<String,String>> jsxmList = null;
			jsxmList = bfjsJsxmDao.getAllowEditJsfxm();
			//固定表头学号、姓名
			ws.addCell(new Label(0, 0, "班级代码"));
			ws.addCell(new Label(1, 0, "班级名称"));
			
			for (int i = 0 , j = jsxmList.size() ; i < j ; i++){
				Label label = new Label(2+i, 0, jsxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将项目代码作为注释，用于识别导入
		        wcfeatures.setComment(jsxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			//不分页
			model.getPages().setPageSize(Integer.MAX_VALUE);
			//查询班级及已经录入的分数
			
			List<HashMap<String,String>> jsfList = new ArrayList<HashMap<String,String>>();
		
				 jsfList = dao.getPageList(model, jsxmList,user);
			
			
			for (int i = 0 , j = jsfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, jsfList.get(i).get("bjdm")));
				ws.addCell(new Label(1, i+1, jsfList.get(i).get("bjmc")));
				for (int m = 0 , n = jsxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+2, i+1, jsfList.get(i).get("fs"+m)));
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
	 * 
	 * @描述: 导入竞赛分数
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-29 上午10:56:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @throws FileNotFoundException
	 * @throws IOException
	 * void 返回类型
	 * @throws BiffException 
	 */
	public File importBfjsFswh(BfjsFswhModel model,User user) throws Exception{
		
		FormFile importFile = model.getImportFile();
		//将FormFile 转换为File 对象
		File file = FileUtil.conversionFormFile(importFile);
		
		//获取Excel工作表
		Workbook book = Workbook.getWorkbook(file);
		//班级信息
		List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);
		
		BfjsJsxmDao BfjsJsxmDao = new BfjsJsxmDao();
		//可编辑竞赛分的竞赛项目
		BfjsCsszModel csszModel = csszDao.getModel();
		List<HashMap<String,String>> JsxmList = null;
		
			JsxmList = BfjsJsxmDao.getAllowEditJsfxm();
		

		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		//设置错误消息提示·列宽
		ws.setColumnView(JsxmList.size()+2, 30);
		
		
		//导入模版与竞赛结构比较验证
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			
			String xmdm = JsxmList.get(i).get("xmdm");
			String xmmc = JsxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(2+i, 0).getContents();
			
			//验证下表头是否与竞赛结构的导出模版一致
			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
		}
		
		/*
		 * 检测导入数据
		 */
		int rows = ws.getRows();
		List<String[]> params = new ArrayList<String[]>();
		
		boolean checkResult = true;
		
		for (int i = 1 ; i < rows ; i++){
			StringBuilder errorMessage = new StringBuilder();
			
			String Bjdm = ws.getCell(0, i).getContents();
			
			if (StringUtil.isNull(Bjdm)){
				//班级不能为空
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_BJDM_NULL));
			}else{
				for (int m = 0 ; m < JsxmList.size() ; m++){
					String xmfz = ws.getCell(m+2, i).getContents().trim();
					
					if (StringUtil.isNull(xmfz)){
						//项目分数不能为空 
						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_NULL));
						break;
					}
				
						
						//验证项目分数的 数字有效性
						Double xmf = 0.0;
						
						try {
							xmf = Double.valueOf(xmfz.trim());
						} catch (Exception e) {
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_NOTNUMBER,
									new Object[]{JsxmList.get(m).get("xmmc")}));
							break;
						}
						
						//验证长度
						if (xmfz.length() > 10){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_MORETHEN_MAXLENGTH));
							break;
						}
						
						//验证最大、最小限制
						Double max = Double.valueOf(JsxmList.get(m).get("zdfz"));
						Double min = Double.valueOf(JsxmList.get(m).get("zxfz"));
						
						if (xmf > max || xmf < min){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_MORETHEN_ZDZX,
									new Object[]{JsxmList.get(m).get("xmmc"),max,min}));
							break;
						}
					
					String[] param=null;
					
						param= new String[]{Bjdm,JsxmList.get(m).get("xn"),
								  JsxmList.get(m).get("xq"),
								  JsxmList.get(m).get("xmdm"),
								  xmfz,user.getUserName(),Bjdm,JsxmList.get(m).get("xmdm")};
					
					//收集参数
					 
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
				ws.addCell(new Label(JsxmList.size()+2, i, errorMessage.toString(),wcf));
				checkResult = false;
			}
		}
		
		
		//验证OK的导入，失败的导出
		if(!params.isEmpty()){
			System.out.println("action========"+System.currentTimeMillis());
			
			dao.batchInsertBfjsFswh(params);//批量插入
			
			System.out.println("end=========="+System.currentTimeMillis());
		}
		
		//TODO
			
			
		if (!checkResult){
			WritableSheet ws1 = wwb.createSheet("错误数据", 1);
			ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
			ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
			for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
				Label label = new Label(2+i, 0, JsxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将项目代码作为注释，用于识别导入
		        wcfeatures.setComment(JsxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws1.addCell(label);
			}
			
			int z = 1;//已打印行号
			for (int i = 0; i < rows; i++) {
				if(!StringUtils.isBlank((ws.getCell(JsxmList.size()+2,i).getContents()).trim())){
					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
					for (int j = 0; j < JsxmList.size()+1; j++) {
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
	 * 
	 * @描述:竞赛分取消查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-30 上午10:42:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJsfqxList(BfjsFswhModel t, User user)
		throws Exception {

	return dao.getJsfqxList(t, user);
}

	

	/** 
	 * @描述:竞赛结果查询
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-31 下午04:09:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param BfjsFswhForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	
	public List<HashMap<String,String>> getJsfjgList(BfjsFswhModel t, User user) throws Exception{
		
		//前两级竞赛项目
		List<HashMap<String, String>> JsxmList = jsxmDao.getFirstAndSecondBfjsJsxm(t);
		
		return dao.getJsfjgList(t, user, JsxmList);
	}
	
	
	
	/**
	 * 
	 * @描述: 获取班级竞赛分的导出文件
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-6 上午09:08:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param BfjsFswhForm
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型
	 */
	public File getBjJsfFile(BfjsFswhModel BfjsFswhForm, User user) throws Exception{
		
		BfjsJsxmDao BfjsJsxmDao = new BfjsJsxmDao();
		
		List<HashMap<String,String>> JsxmList = BfjsJsxmDao.getNoChildJsfxm();
		//构建导出表头
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("bjmc", "班级");
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			map.put("fs"+i, JsxmList.get(i).get("xmmc"));
		}
		//导出数据
		BfjsFswhForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> dataList = dao.getPageList(BfjsFswhForm,JsxmList,user);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	
	/**
	 * 
	 * @描述:竞赛分结果导出
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-6 上午11:34:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param BfjsFswhForm
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File getJsfjgFile(BfjsFswhModel BfjsFswhForm, User user) throws Exception{
		
		BfjsJsxmDao BfjsJsxmDao = new BfjsJsxmDao();
		
		List<HashMap<String, String>> JsxmList = BfjsJsxmDao.getFirstAndSecondBfjsJsxm(BfjsFswhForm);
		
		//构建导出表头
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("jszq", "竞赛周期");
		map.put("xymc", "学院");
		map.put("bjmc", "班级");
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			map.put("fs"+i, JsxmList.get(i).get("xmmc"));
				map.put("pm"+i, "排名");
		}
		//导出数据
		BfjsFswhForm.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getJsfjgList(BfjsFswhForm, user, JsxmList);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	public boolean isHaveSubmitClass(String xn ,String xq){
		
		return Integer.valueOf(dao.getYtjZcfNum(xn, xq)) > 0;
	}


	/**
	 * @throws Exception  
	 * @描述:查看是否还有未提交记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-9 下午03:55:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param BfjsFswhForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isSubmitInfo(BfjsFswhModel BfjsFswhForm, User user) throws Exception {
		
		String num = dao.isSubmitInfo(BfjsFswhForm, user);
		
		return Integer.valueOf(num) > 0;
		
	}

	public boolean cancelTj( User user,BfjsFswhModel bfjsFswhForm) throws Exception {
		String id = bfjsFswhForm.getId();	
		boolean cancelTj = false;
		//更新提交记录表
		cancelTj=dao.updateTjjL(id,user,DEFAULT_QXTJ);
		String[] ids = id.split(",");
		if(cancelTj){
			List<HashMap<String,String>> xyList = getXyxxById(ids);
			for (int i = 0; i < xyList.size(); i++) {
				//计算综测分
				Thread thread = new Thread(new ComputeJspm(xyList.get(i).get("xn"), xyList.get(i).get("xq"), xyList.get(i).get("xydm")));
				thread.start();
			}
			
		}
		return cancelTj;
	}
	}
