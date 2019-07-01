/**
 * @部门:学工产品事业部
 * @日期：2015-6-4 上午10:17:54 
 */  
package com.zfsoft.xgxt.gygl.wsjc.wsf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import org.apache.struts.upload.FormFile;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmDao;

/** 
 * @类功能描述: 卫生分录入
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-4 上午10:17:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsfService extends SuperServiceImpl<WsfModel, WsfDao> {

//	private static final String JCDX_QS = "0";//按寝室录入 
	private static final String JCDX_CW = "1";//按床位录入
	private static final String FSLX_FS = "0";//0:分数  1:等级  2:星级
	
	
	/***卫生分日程录入列表***/
	public List<HashMap<String,String>> getRclrList(WsfModel t, User user) throws Exception{
		
		if (JCDX_CW.equals(t.getJcdx())){
			return dao.getCwlrList(t, user);
		} else {
			return dao.getQslrList(t, user);
		}
	}
	
	
	/***卫生分分数录入列表***/
	public List<HashMap<String,String>> getFslrList(WsfModel t, User user) throws Exception{
		
		JcxmDao jcxmDao = new JcxmDao();
		List<HashMap<String,String>> rcxmList = jcxmDao.getRcxmList(t.getRcid(),t.getJcdx());
		
		if (JCDX_CW.equals(t.getJcdx())){
			return dao.getCwflrList(t, user, rcxmList);
		} else {
			return dao.getQsflrList(t, user, rcxmList);
		}
	}
	
	/***卫生分等级列表***/
	public List<String> getWsfdjList() throws Exception{
		return dao.getFslrDj();
	}
	
	/***卫生分星级列表***/
	public List<String> getWsfxjList() throws Exception{
		return dao.getFslrXj();
	}


	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(WsfModel t) throws Exception {
		
		if (JCDX_CW.equals(t.getJcdx())){
			dao.delWsfByCwh(t);
		} else {
			dao.delWsfByQsh(t);
		}
		
		return super.runInsert(t);
	}
	
	
	public File createImportTemplate(WsfModel model,User user) throws Exception{
		
		WritableWorkbook wwb = null;
		
		//导出文件存放 的临时目录
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		//创建导出文件
		File file = new File( tempDir.getPath() + "/" +"卫生分导入模版.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//创建excel工作表
			wwb = Workbook.createWorkbook(stream);
			WritableSheet ws = wwb.createSheet("卫生分导入模版", 0);
			
			int start = 2;
			
			//固定表头学号、姓名
			ws.addCell(new Label(0, 0, "楼栋"));
			ws.addCell(new Label(1, 0, "寝室"));
			String jcdx  = model.getJcdx();
			if (JCDX_CW.equals(jcdx)){
				ws.addCell(new Label(2, 0, "床位"));
				start = 3;
			}
			
			JcxmDao jcxmDao = new JcxmDao();
			List<HashMap<String, String>>  rcxmList = jcxmDao.getRcxmList(model.getRcid(),jcdx);
			
			for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
				Label label = new Label(start+i, 0, rcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将项目代码作为注释，用于识别导入
		        wcfeatures.setComment(rcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			//不分页
			model.getPages().setPageSize(Integer.MAX_VALUE);
			//查询模版数据列表
			List<HashMap<String,String>> resultList = null;
			
			if (JCDX_CW.equals(jcdx)){
				resultList = dao.getCwflrList(model, user, rcxmList);
			} else {
				resultList = dao.getQsflrList(model, user, rcxmList);
			}
			
			for (int i = 0 , j = resultList.size() ; i < j ; i++){
				Label label = new Label(0, i+1, resultList.get(i).get("ldmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将楼栋代码作为注释，用于识别导入
		        wcfeatures.setComment(resultList.get(i).get("lddm"));  
		        label.setCellFeatures(wcfeatures); 
				ws.addCell(label);
				
				ws.addCell(new Label(1, i+1, resultList.get(i).get("qsh")));
				
				if (JCDX_CW.equals(jcdx)){
					ws.addCell(new Label(2, i+1, resultList.get(i).get("cwh")));
				}
				
				for (int m = 0 , n = rcxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+start, i+1, resultList.get(i).get("fs"+m)));
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
	
	
	
	
	public File importWsf(WsfModel model,User user) throws Exception{
		
		FormFile importFile = model.getImportFile();
		//将FormFile 转换为File 对象
		File file = FileUtil.conversionFormFile(importFile);
		//获取Excel工作表
		Workbook book = Workbook.getWorkbook(file);

		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		
		JcxmDao jcxmDao = new JcxmDao();
		String rcid  = model.getRcid();
		String jcdx  = model.getJcdx();
		List<HashMap<String, String>>  rcxmList = jcxmDao.getRcxmList(rcid,jcdx);
		
		int start = 2;
		
		if (JCDX_CW.equals(jcdx)){
			start = 3;
		}
		
		//设置错误消息提示·列宽
		ws.setColumnView(rcxmList.size()+start, 30);
		
		//导入模版比较验证
		for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
			
			String xmdm = rcxmList.get(i).get("xmdm");
			String xmmc = rcxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(start+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(start+i, 0).getContents();
			
			//验证下表头是否与导出模版一致
			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
		}
		
		int rows = ws.getRows();
		List<String[]> params = new ArrayList<String[]>();
		List<String> param;
		List<String> paramXm;
		
		boolean checkResult = true;
		
		for (int i = 1 ; i < rows ; i++){
			StringBuilder errorMessage = new StringBuilder();
			String lddm = ws.getCell(0, i).getCellFeatures().getComment();
			String qsh = ws.getCell(1, i).getContents();
			
			if (StringUtil.isNull(lddm) || StringUtil.isNull(qsh)){
				errorMessage.append(MessageUtil.getText(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE));
			}
			
			param = new ArrayList<String>();
			param.add(rcid);
			param.add(lddm);
			param.add(qsh);
			
			if (JCDX_CW.equals(jcdx)){
				String cwh = ws.getCell(2, i).getContents();
				param.add(cwh);
				
				if (StringUtil.isNull(cwh)){
					errorMessage.append(MessageUtil.getText(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE));
				}
			}
			
			param.add(user.getUserName());
			
			for (int m = 0 ; m < rcxmList.size() ; m++){
				String wsf = ws.getCell(m+start, i).getContents();
				if (StringUtil.isNull(wsf)){
					errorMessage.append(MessageUtil.getText(MessageKey.GYGL_WSJC_IMPORT_NULL));
					break;
				}
				paramXm = new ArrayList<String>();
				paramXm.addAll(param);
				paramXm.add(rcxmList.get(m).get("xmdm"));
				paramXm.add(wsf);
				params.add(paramXm.toArray(new String[]{}));
			}			
			
			//错误消息追加
			if (errorMessage.length() > 0){
				WritableCellFormat wcf = new WritableCellFormat();
				WritableFont wf = new WritableFont(WritableFont.ARIAL);
				wf.setColour(Colour.RED);
				wcf.setFont(wf);
				wcf.setAlignment(Alignment.CENTRE);
				ws.addCell(new Label(rcxmList.size()+start, i, errorMessage.toString(),wcf));
				checkResult = false;
			}
		}
		
		//验证完毕，可以开始导入了
		if (checkResult){
			// 寝室
			if (JCDX_CW.equals(jcdx)){
				// 分数
				if(FSLX_FS.equals(model.getFslx())){
					dao.saveWsf(params);
				} else {
					dao.saveWsdj(params);
				}
			} else {
				// 分数
				if(FSLX_FS.equals(model.getFslx())){
					dao.saveQsWsf(params);
				} else {
					dao.saveQsWsdj(params);
				}
			}
			wwb.write();
			wwb.close();
			return null;
		} else {
			wwb.write();
			wwb.close();
			return file;
		}
	}


	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsfModel t, User user)
			throws Exception {
		
		if (JCDX_CW.equals(t.getJcdx())){
			return dao.getFscxListByCw(t, user);
		} else {
			return dao.getFscxListByQs(t, user);
		}
		
	}
	
}
