/**
 * @部门:学工产品事业部
 * @日期：2016-2-22 下午01:45:55 
 */  
package com.zfsoft.xgxt.xpjpy.cpfwh;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-22 下午01:45:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpfwhService extends SuperServiceImpl<CpfwhForm, CpfwhDao>{
	CpfwhDao dao = new CpfwhDao();
	
	/** 
	 * @描述:验证申请
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午01:53:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(CpfwhForm form){
		return dao.isHaveRecord(form);
	}
	
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	
//	/** 
//	 * @描述:创建导入模板
//	 * @作者：柳俊[工号：1282]
//	 * @日期：2016-2-22 下午05:31:34
//	 * @修改记录: 修改者名字-修改日期-修改内容
//	 * @param model
//	 * @param user
//	 * @return
//	 * @throws Exception
//	 * File 返回类型 
//	 * @throws 
//	 */
//	public File createImportTemplate(CpfwhForm model, User user) throws Exception{
//		
//		WritableWorkbook wwb = null;
//		
//		//导出文件存放 的临时目录
//		File tempDir = new File(System.getProperty("java.io.tmpdir"));
//		if (!tempDir.exists()){
//			tempDir.mkdir();
//		}
//		
//		//List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);    
//		//创建导出文件
//		File file = new File( tempDir.getPath() + "/" +"晨跑分导入模版.xls");
//		file.setWritable(true);
//		
//		try{
//			FileOutputStream stream = new FileOutputStream(file);
//			//创建excel工作表
//			wwb = Workbook.createWorkbook(stream);
//			
//			WritableSheet ws = wwb.createSheet("晨跑分导入模版", 0);
//			
//			//固定表头学号、姓名
//			ws.addCell(new Label(0, 0, "学年"));
//			ws.addCell(new Label(1, 0, "学期"));
//			ws.addCell(new Label(2, 0, "学号"));
//			ws.addCell(new Label(3, 0, "分数"));//TODO
//			
//			WritableSheet ws1 = wwb.createSheet("学期对照名", 1);
//			
//			List<HashMap<String, String>> xqList = getXqList();
//			
//			if(!xqList.isEmpty()){
//				WritableCellFormat wcf = new WritableCellFormat();
//				WritableFont wf = new WritableFont(WritableFont.ARIAL);
//				wf.setBoldStyle(WritableFont.BOLD);
//				wf.setPointSize(10);
//				wf.setColour(Colour.BLUE);
//				wcf.setAlignment(Alignment.LEFT);
//				wcf.setWrap(true);
//				wcf.setFont(wf);
//				
//				ws1.addCell(new Label(0, 0, "学期代码"));
//				ws1.addCell(new Label(1, 0, "学期名称"));
//				for (int i = 1; i <= xqList.size(); i++) {						
//					ws1.addCell(new Label(0, i,xqList.get(i-1).get("xqdm")));
//					ws1.addCell(new Label(1, i,xqList.get(i-1).get("xqmc")));
//				}
//			}
//			wwb.write();
//			wwb.close();
//		}catch (Exception e) {
//			throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
//		}
//		
//		file.setWritable(true);
//		return file;
//	}
//	
//	public File importCpfwh(CpfwhForm model,User user) throws Exception{
//		FormFile importFile = model.getImportFile();
//		//将FormFile 转换为File 对象
//		File file = FileUtil.conversionFormFile(importFile);
//		
//		//获取Excel工作表
//		Workbook book = Workbook.getWorkbook(file);
//
//		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
//		WritableSheet ws = wwb.getSheet(0);
////		//设置错误消息提示·列宽
////		ws.setColumnView(zcxmList.size()+2, 30);
////		
////		//参评班级学生
////		String[] stus = dao.getStuById(model,user);
////		
////		//导入模版与综测结构比较验证
////		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
////			
////			String xmdm = zcxmList.get(i).get("xmdm");
////			String xmmc = zcxmList.get(i).get("xmmc");
////			
////			CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();
////			
////			if (cellFeatures == null){
////				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
////			}
////			
////			String cellComment = cellFeatures.getComment();
////			String cellContent = ws.getCell(2+i, 0).getContents();
////			
////			//验证下表头是否与综测结构的导出模版一致
////			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
////				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
////			}
////		}
//		
//		/*
//		 * 检测导入数据
//		 */
//		int rows = ws.getRows();
//		List<String[]> params = new ArrayList<String[]>();
//		
//		boolean checkResult = true;
//		CpfwhForm form = new CpfwhForm();
//		for (int i = 1 ; i < rows ; i++){
//			StringBuilder errorMessage = new StringBuilder();
//			String xn = ws.getCell(0, i).getContents();
//			String xq = ws.getCell(1, i).getContents();
//			String xh = ws.getCell(2, i).getContents();
//			String fs = ws.getCell(3, i).getContents();
//			
//			if (StringUtil.isNull(xn) || StringUtil.isNull(xq) || StringUtil.isNull(xn) || StringUtil.isNull(xn)){
//				//学号和姓名怎么可以为空呢？
//				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_CPFWH_YANZHEN));
//			}else{				
//				boolean checkFlag = true;
//				String bjdm = dao.getBjdmForStu(xh);
//				List<String> bjdmList = dao.getBjdmForTeacher(user);
//				if(!bjdmList.contains(bjdm)){
//					checkFlag = false;
//					errorMessage.append(MessageUtil.getText(MessageKey.PJPY_CPFWH_XHNULL));
//				}else{
//					form.setXh(xh);
//					form.setXn(xn);
//					form.setXq(xq);
//					if(dao.isHaveRecord(form)){
//						checkFlag = false;
//						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_CPFWH_REPEAT));
//					}
//				}
//				if(!checkFlag){
//					errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
//							new Object[]{xmfz}));
//					break;
//				}
//			
//					//收集参数
//					String[] param = new String[]{xh,zcxmList.get(m).get("xn"),
//												  zcxmList.get(m).get("xq"),
//												  zcxmList.get(m).get("xmdm"),
//												  xmfz,user.getUserName(),
//												  xh,zcxmList.get(m).get("xmdm")};
//					params.add(param);
//				}
//			}
//			
//			
//			//错误消息追加
//			if (errorMessage.length() > 0){
//				
//				WritableCellFormat wcf = new WritableCellFormat();
//				WritableFont wf = new WritableFont(WritableFont.ARIAL);
//				wf.setColour(Colour.RED);
//				wcf.setFont(wf);
//				wcf.setAlignment(Alignment.CENTRE);
//				ws.addCell(new Label(zcxmList.size()+2, i, errorMessage.toString(),wcf));
//				checkResult = false;
//			}
//		}
//		
//		
//		//验证OK的导入，失败的导出
//		if(!params.isEmpty()){
//			System.out.println("action========"+System.currentTimeMillis());
//			dao.batchInsertZcfs(params);//批量插入
//			System.out.println("end=========="+System.currentTimeMillis());
//		}
//		
//		//TODO
//			
//			
//		if (!checkResult){
//			WritableSheet ws1 = wwb.createSheet("错误数据", 1);
//			ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
//			ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
//			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
//				Label label = new Label(2+i, 0, zcxmList.get(i).get("xmmc"));
//				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
//				//将项目代码作为注释，用于识别导入
//		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
//		        label.setCellFeatures(wcfeatures); 
//		        ws1.addCell(label);
//			}
//			
//			int z = 1;//已打印行号
//			for (int i = 0; i < rows; i++) {
//				if(!StringUtils.isBlank((ws.getCell(zcxmList.size()+2,i).getContents()).trim())){
//					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
//					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
//					for (int j = 0; j < zcxmList.size()+1; j++) {
//						ws1.addCell(new Label(j+2,z,ws.getCell(j+2,i).getContents()));
//					}
//					z++;
//				}
//			}
//			
//			wwb.removeSheet(0);
//			wwb.write();
//			wwb.close();
//			return file;
//		}
//		return null;
//	}
	
	public List<HashMap<String, String>> getXqList(){
		return dao.getXqList();
	}
}
