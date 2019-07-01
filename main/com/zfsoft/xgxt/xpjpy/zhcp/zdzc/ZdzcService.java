/**
 * @部门:学工产品事业部
 * @日期：2015-5-29 上午11:39:55 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2015-5-29 上午11:39:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzcService extends SuperServiceImpl<ZdzcForm, ZdzcDao> {
	
	public static final String tjzt_tj = "1"; //评奖评优，提交状态提交
	public static final String tjzt_qxtj = "2"; //评奖评优，提交状态为取消提交

	/**
	 * @throws Exception  
	 * @描述:浙大综测维护
	 * @作者：cq [工号：785]
	 * @日期：2015-5-29 下午02:58:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdzcForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZcwhList(ZdzcForm zdzcForm,
			User user) throws Exception {

		//获得综测分项目
		List<HashMap<String, String>> zcxmList = dao.getzcxmList();
		
		return dao.getZcwhList(zdzcForm,zcxmList,user);
	}

	/** 
	 * @描述:批量取消参评
	 * @作者：cq [工号：785]
	 * @日期：2015-6-1 上午11:27:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean qxcp(String values, User user) throws Exception {

		if(values == null || "".equalsIgnoreCase(values)){
			logger.error("未选择取消参评人员！");
			throw new NullPointerException();
		}
		
			boolean qxcp = false;
		
			//插入调整记录
			qxcp=dao.insertTzjl(values,user);
		
			if(!qxcp){
				return false;
			}
			
			//更新评奖人员库
			qxcp=dao.updateCpmd(values,user);
			
			//学期综测，自动更新学年综测,如果是学年综测，则不更新
			if(CsszService.getZczq()){
				dao.updateXncpmd(values,user);
			}
		
			return qxcp;
	}
	
	
	
	/**
	 * 
	 * @描述:创建导入模版
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 上午11:06:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File createImportTemplate(ZdzcForm model, User user) throws Exception{
		
		WritableWorkbook wwb = null;
		
		//导出文件存放 的临时目录
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		//创建导出文件
		File file = new File( tempDir.getPath() + "/" +"综测分导入模版.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//创建excel工作表
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("综测分导入模版", 0);
			
			
			//获得综测分项目
			List<HashMap<String, String>> zcxmList = dao.getzcxmList();
			
			
			//固定表头学号、姓名
			ws.addCell(new Label(0, 0, "学号"));
			ws.addCell(new Label(1, 0, "姓名"));
			ws.addCell(new Label(2, 0, "年级"));
			ws.addCell(new Label(3, 0, "学院"));
			ws.addCell(new Label(4, 0, "专业"));
			ws.addCell(new Label(5, 0, "班级"));
			
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(2+4+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将项目代码作为注释，用于识别导入
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			//不分页
			model.getPages().setPageSize(Integer.MAX_VALUE);
			//查询学生及已经录入的分数
			List<HashMap<String,String>> zcfList = dao.getZcwhList(model,zcxmList,user);
			
			ZcfsService zdzcService = new ZcfsService();
			//等级名称
			List<String> DjmcList= zdzcService.getDjmc();
			//等级list
			List<HashMap<String, String>> djList = zdzcService.getDj();
			
			
			for (int i = 0 , j = zcfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
				ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
				ws.addCell(new Label(2, i+1, zcfList.get(i).get("nj")));
				ws.addCell(new Label(3, i+1, zcfList.get(i).get("xymc")));
				ws.addCell(new Label(4, i+1, zcfList.get(i).get("zymc")));
				ws.addCell(new Label(5, i+1, zcfList.get(i).get("bjmc")));
				
				for (int m = 0 , n = zcxmList.size() ; m < n ; m++){
					if("4".equals(zcxmList.get(m).get("xmlx"))){
						for (int k = 0; k < djList.size(); k++) {
							if(djList.get(k).get("xmmc").equals(zcxmList.get(m).get("xmmc"))&&djList.get(k).get("dm").equals(zcfList.get(i).get("fs"+m))){
								ws.addCell(new Label(m+2+4, i+1, djList.get(k).get("mc")));
								break;
							}
						}
					}else{
						ws.addCell(new Label(m+2+4, i+1, zcfList.get(i).get("fs"+m)));
					}
				}
			}
			
			WritableSheet ws1 = wwb.createSheet("项目等级名", 1);
			
			
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
		}catch (Exception e) {
			throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
		}
		
		file.setWritable(true);
		return file;
	}
	
	
	/**
	 * 
	 * @描述: 综测导入
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 下午02:40:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public File importZcfs(ZdzcForm model,User user) throws Exception{
		
		ZcfsService zcfsservoce = new ZcfsService();
		
		FormFile importFile = model.getImportFile();
		//将FormFile 转换为File 对象
		File file = FileUtil.conversionFormFile(importFile);
		
		//获取Excel工作表
		Workbook book = Workbook.getWorkbook(file);
		
		List<HashMap<String, String>> zcxmList = dao.getzcxmList();

		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		//设置错误消息提示·列宽
		ws.setColumnView(zcxmList.size()+2+4, 30);
		
		//参评班级学生
		String[] stus = dao.getStuById(model,user);
		
		//导入模版与综测结构比较验证
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			
			String xmdm = zcxmList.get(i).get("xmdm");
			String xmmc = zcxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(2+4+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(2+4+i, 0).getContents();
			
			//验证下表头是否与综测结构的导出模版一致
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
			
			String xh = ws.getCell(0, i).getContents();
			String xm = ws.getCell(1, i).getContents();
			
			if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
				//学号和姓名怎么可以为空呢？
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
			}else if (!ArrayUtil.contains(stus, xh)){ //验证学号有效性
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_FAIL));
			}else{
				for (int m = 0 ; m < zcxmList.size() ; m++){
					String xmfz = ws.getCell(m+2+4, i).getContents().trim();
					
					if (StringUtil.isNull(xmfz)){
						//项目分数不能为空 
						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NULL));
						continue;
					}
					
					//综测项目类型  4 为等级，可以录入非数字
					String xmlx = zcxmList.get(m).get("xmlx");
					
					//综测项目名称
					String drxmmc = zcxmList.get(m).get("xmmc");
					
					if(!"4".equals(xmlx)){
						
						//验证项目分数的 数字有效性
						Double xmf = 0.0;
						
						try {
							xmf = Double.valueOf(xmfz.trim());
						} catch (Exception e) {
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NOTNUMBER,
									new Object[]{zcxmList.get(m).get("xmmc")}));
							continue;
						}
						
						//验证长度
						if (xmfz.length() > 10){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH));
							continue;
						}
						
						//验证最大、最小限制
						Double max = Double.valueOf(zcxmList.get(m).get("zdfs"));
						Double min = Double.valueOf(zcxmList.get(m).get("zxfs"));
						
						if (xmf > max || xmf < min){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDZX,
									new Object[]{zcxmList.get(m).get("xmmc"),max,min}));
							continue;
						}
					}else{
						boolean checkFlag = false;
						//验证录入的等级是否符合要求
						List<HashMap<String, String>> djList = zcfsservoce.getDj();
						for (int j = 0; j < djList.size(); j++) {
							if(djList.get(j).get("mc").equals(xmfz)&&djList.get(j).get("xmmc").equals(drxmmc)){
								xmfz = djList.get(j).get("dm");
								checkFlag = true;
								continue;
							}
						}
						
						if(!checkFlag){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
									new Object[]{xmfz}));
							continue;
						}
					}
					
					//收集参数
					String[] param = new String[]{xh,zcxmList.get(m).get("xn"),
												  zcxmList.get(m).get("xq"),
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
				ws.addCell(new Label(zcxmList.size()+2+4, i, errorMessage.toString(),wcf));
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
			ws1.addCell(new Label(2,0,ws.getCell(2,0).getContents()));
			ws1.addCell(new Label(3,0,ws.getCell(3,0).getContents()));
			ws1.addCell(new Label(4,0,ws.getCell(4,0).getContents()));
			ws1.addCell(new Label(5,0,ws.getCell(5,0).getContents()));
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(2+i+4, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//将项目代码作为注释，用于识别导入
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws1.addCell(label);
			}
			
			int z = 1;//已打印行号
			for (int i = 0; i < rows; i++) {
				if(!StringUtils.isBlank((ws.getCell(zcxmList.size()+2+4,i).getContents()).trim())){
					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
					ws1.addCell(new Label(2,z,ws.getCell(2,i).getContents()));
					ws1.addCell(new Label(3,z,ws.getCell(3,i).getContents()));
					ws1.addCell(new Label(4,z,ws.getCell(4,i).getContents()));
					ws1.addCell(new Label(5,z,ws.getCell(5,i).getContents()));
					for (int j = 0; j < zcxmList.size()+1; j++) {
						ws1.addCell(new Label(j+2+4,z,ws.getCell(j+2+4,i).getContents()));
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
	 * @throws Exception  
	 * @描述:提交参评人员状态
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 下午04:22:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean tjCpxs(String values, String tjzt, ZdzcForm model, User user) throws Exception {
		
		if("tj".equals(tjzt)){
			tjzt=tjzt_tj;
		}else{
			tjzt=tjzt_qxtj;
		}
		
		return dao.tjCpxs(values,tjzt,model,user);
	}

	/**
	 * @throws Exception  
	 * @描述:检测是否可提交综测分
	 * @作者：cq [工号：785]
	 * @日期：2015-6-10 下午05:19:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param zdzcForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isCanSubmit(String values, ZdzcForm zdzcForm, User user) throws Exception {
		return dao.isCanSubmit(values,zdzcForm,user);
	}
	
	/**
	 * 
	 * @描述:允许录入综测分的综测项目
	 * @作者：cq [工号：785]
	 * @日期：2015-6-18 上午09:19:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		return dao.getAllowEditZcfxm();
	}

	/**
	 * @throws SQLException  
	 * @描述:通过id取得学号列表)
	 * @作者：黄辰霁
	 * @日期：2015-12-3 下午04:43:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String[] getXhArray(String values) throws SQLException {
		return dao.getXhArray(values);
	}

}
