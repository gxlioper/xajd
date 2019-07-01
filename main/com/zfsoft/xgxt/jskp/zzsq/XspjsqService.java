/**
 * @部门:学工产品(1)部
 * @日期：2018-4-11 上午09:09:33 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.dmwh.DmwhDao;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgForm;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学习评价管理模块
 * @类功能描述: 自主申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-11 上午09:09:20 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjsqService extends SuperServiceImpl<XspjsqForm,XspjsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @描述: 检查学生年级，如果是学生用户的话必须大于2017级才能申请
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-11 上午10:54:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getCheckStuNj(User user) throws Exception{
		boolean flag = false;
		if("stu".equals(user.getUserType())){
			flag = dao.getCheckStuNj(user.getUserName());
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @描述: 获取审批流程
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-11 上午11:06:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sb
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSplcForParam(String sb){
		return dao.getSplcForParam(sb);
	}
	
	
	/**
	 * @描述: 获取短学期信息List
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-12 下午07:25:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDxqInfoList(){
		return dao.getDxqInfoList();
	}
	
	/**
	 * @描述: 申请保存(保存草稿、提交申请)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-12 下午05:45:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveFormXspjsq(XspjsqForm model,User user) throws Exception{
		
		boolean rs = true;
		
		/*生成唯一标识符*/
		String sqid = UniqID.getInstance().getUniqIDHash();
		/*取审批流程*/
		String splcid = this.getSplcForParam("sb").get("splc");
		
		/*判断唯一键，学号(xh),项目名称(xmmc),参与时间(cysj)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*判断参与人(学号)是否存在xsxxb*/
		if(!dao.checkXh(model.getXh())){
			throw new SystemException(MessageKey.PJPY_CPFWH_XHNULL);
		}
		
		/*当前操作人员用户名塞入*/
		model.setSjlrr(user.getUserName());
		
		/*获取当前操作时间塞入表中，配合操作人工号防止老师耍赖*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*保存申请审核状态塞入*/
		if("save".equals(model.getSaveFlag())){
			/*点击 保存草稿 按钮审核状态为未提交【0】*/
			model.setShzt(Constants.YW_WTJ);
		}else{
			/*点击 提交申请 按钮审核状态为审核中【5】*/
			model.setShzt(Constants.YW_SHZ);
		}
		
		/*判断该数据是否为修改数据*/
		if(StringUtils.isNotNull(model.getSqid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*塞入唯一ID*/
			model.setSqid(sqid);
			model.setSplcid(splcid);
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		
		/*数据提交时，把sqid,审批流程、操作对象、.do插入审核流程*/
		if("submit".equals(model.getSaveFlag())){
			rs = shlc.runSubmit(model.getSqid(),splcid,model.getXh(), "xspj_xspj_xspjsh.do", "xspj_xspj_xspjsq.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 判断唯一键，学号(xh),项目名称(xmmc),参与时间(cysj)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-12 下午05:58:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjsqForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @描述: 删除时清理审核状态表中的垃圾数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 上午10:36:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		return dao.delShztbData(sqids);
	}
	
	/**
	 * @描述: 撤销审核中的记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 上午11:33:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @描述: 根据sqid获取相关信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 下午02:13:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr)throws Exception{
		return dao.getInfoBySqid(sqidArr);
	}
	
	/**
	 * @描述: 批量提交
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 下午02:15:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param splc
	 * @param fzr
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean plSubmit(String sqid,String splcid,String xh) throws Exception{
		boolean flag = false;
		XspjsqForm model = new XspjsqForm();
		model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splcid);
		model.setSqid(sqid);
		flag = dao.runUpdate(model);
		if(flag){
			shlc.runSubmit(sqid,splcid,xh, "xspj_xspj_xspjsh.do", "xspj_xspj_xspjsq.do");
		}
		return flag;
	}
	
	/**
	 * @描述: 根据指导部门代码取部门名称
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 下午04:48:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdbmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBmmcByZdbmdm(String zdbmdm){
		return dao.getBmmcByZdbmdm(zdbmdm);
	}
	
	/**
	 * @描述: 根据申请ID获得学生申请信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-16 下午03:16:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid) throws Exception{
		return dao.getInfoBySqid(sqid);
	}
	
	/**
	 * @描述: 下载导入模板
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-22 上午08:57:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void createWwb(OutputStream os) throws Exception{
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		/**sheet1*/
		WritableSheet ws = wwb.createSheet("数据导入模板", 0);
		
		/**设置表格抬头的格式*/
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("仿宋"),10,WritableFont.NO_BOLD );
		/**单元格字体格式写入*/
		WritableCellFormat body = new WritableCellFormat(bodyFont);
		/**设置单元格对齐*/
		/*水平居中*/
		body.setAlignment(jxl.format.Alignment.CENTRE);
		/*垂直居中*/
		body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		
		/**设置列宽*/
		ws.setColumnView(0, 16);/*项目名称*/
		ws.setColumnView(1, 16);/*指导部门*/
		ws.setColumnView(2, 16);/*项目类别*/
		ws.setColumnView(3, 34);/*参与时间*/
		ws.setColumnView(4, 14);/*学号*/
		ws.setColumnView(5, 28);/*学年*/
		ws.setColumnView(6, 24);/*短学期*/
		ws.setColumnView(7, 12);/*分值*/
		ws.setColumnView(8, 12);/*负责人*/
		ws.setColumnView(9, 18);/*负责人联系方式*/
		ws.setColumnView(10, 14);/*指导老师*/
		ws.setColumnView(11, 20);/*指导老师联系方式*/
		ws.setColumnView(12, 24);/*申请理由*/
		
		
		/*第一行抬头*/
		Label row1col1= new Label(0, 0, "项目名称(必填)",body);
		Label row1col2= new Label(1, 0, "指导部门(必填)",body);
		Label row1col3= new Label(2, 0, "项目类别(必填)",body);
		Label row1col4= new Label(3, 0, "参与时间(必填),例如：2018-01-01",body);
		Label row1col5= new Label(4, 0, "学号(必填)",body);
		Label row1col6= new Label(5, 0, "学年(必填),例如：2017-2018",body);
		Label row1col7= new Label(6, 0, "短学期(必填),例如：春",body);
		Label row1col8= new Label(7, 0, "分值(必填)",body);
		Label row1col9= new Label(8, 0, "负责人",body);
		Label row1col10= new Label(9, 0, "负责人联系方式",body);
		Label row1col11= new Label(10, 0, "指导老师",body);
		Label row1col12= new Label(11, 0, "指导老师联系方式",body);
		Label row1col13= new Label(12, 0, "申请理由",body);
	    ws.addCell(row1col1);
	    ws.addCell(row1col2);
	    ws.addCell(row1col3);
	    ws.addCell(row1col4);
	    ws.addCell(row1col5);
	    ws.addCell(row1col6);
	    ws.addCell(row1col7);
	    ws.addCell(row1col8);
	    ws.addCell(row1col9);
	    ws.addCell(row1col10);
	    ws.addCell(row1col11);
	    ws.addCell(row1col12);
	    ws.addCell(row1col13);
	    
	    /**sheet2：项目类别对照表*/
	    DmwhDao dmwhDao = new DmwhDao();
	    WritableSheet ws1 = wwb.createSheet("项目类别对照表", 1);
	    List<HashMap<String, String>> xmlbList = dmwhDao.getXmlbListByAll();
	    Label ws1row1col1= new Label(0, 0, "项目类别名称",body);
	    ws1.addCell(ws1row1col1);
	    for (int i = 0; i < xmlbList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xmlbList.get(i).get("xmlbmc"),body);
	    	 ws1.addCell(tempLabel);
		}
	    
	    /**sheet3：指导部门对照表*/
	    WritableSheet ws2 = wwb.createSheet("指导部门对照表", 2);
	    List<HashMap<String, String>> xqList = dao.getZdbmList();
	    Label ws2row1col1= new Label(0, 0, "指导部门",body);
	    ws2.addCell(ws2row1col1);
	    for (int i = 0; i < xqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xqList.get(i).get("zdbmmc"),body);
	    	 ws2.addCell(tempLabel);
		}
	    
	    /**sheet4：短学期代码表*/
	    WritableSheet ws3 = wwb.createSheet("短学期对照表", 3);
	    List<HashMap<String, String>> dxqList = dao.getDxqList();
	    Label ws3row1col1= new Label(0, 0, "短学期",body);
	    ws3.addCell(ws3row1col1);
	    for (int i = 0; i < dxqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, dxqList.get(i).get("dxqmc"),body);
	    	ws3.addCell(tempLabel);
		}
		try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
			 
		 }
	}
	
	/**
	 * @描述: 导入信息保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-22 上午09:46:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param xspjsqForm
	 * @param user
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,XspjsqForm xspjsqForm,User user){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap = this.DrExcelInfoCheck(is, xspjsqForm,user);
			//判断excel表格是否为空
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				this.saveDrDataIntoDb(paralist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),xspjsqForm.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb((List<String[]>) resultMap.get("drlist"));
				logger.info("导入失败！");
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @描述: 保存数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-22 上午09:48:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @描述: 导入信息验证
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-22 上午10:09:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param xspjsqForm
	 * @param user
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,XspjsqForm xspjsqForm,User user){
		//导入记录数组
		List<String[]> drlist = new ArrayList<String[]>();
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>();
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		
		Workbook rwb = null;
		try{
			rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0);
			/*得到所有的列*/
		    int clos = rs.getColumns();
		    /*得到所有的行*/
	        int rows = rs.getRows();
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){//验证表格中是否有重复数据(项目名称、参与时间、学号不能重复)
	        	resultMap.put("result", "excelrepeat");
	        }else{
	        	for(int i = 1; i < rows; i++){
	        		/*取出每行验证数据，塞入lsmap*/
	        		HashMap<String,String> lsmap = new HashMap<String,String>();
	        		String xmmc = rs.getCell(0, i).getContents();
	        		String zdbmmc = rs.getCell(1, i).getContents();
	        		String xmlbmc = rs.getCell(2, i).getContents();
	        		String cysj = rs.getCell(3, i).getContents();
	        		String xh = rs.getCell(4, i).getContents();
	        		String xn = rs.getCell(5, i).getContents();
	        		String dxqmc = rs.getCell(6, i).getContents();
	        		String fz = rs.getCell(7, i).getContents();
	        		String fzrxm = rs.getCell(8, i).getContents();
	        		String fzrlxfs = rs.getCell(9, i).getContents();
	        		String zdlsxm = rs.getCell(10, i).getContents();
	        		String zdlslxfs = rs.getCell(11, i).getContents();
	        		String sqly = rs.getCell(12, i).getContents();
	        		lsmap.put("xmmc", xmmc);
	        		lsmap.put("zdbmmc", zdbmmc);
	        		lsmap.put("xmlbmc", xmlbmc);
	        		lsmap.put("cysj", cysj);
	        		lsmap.put("xh", xh);
	        		lsmap.put("xn", xn);
	        		lsmap.put("dxqmc", dxqmc);
	        		lsmap.put("fz", fz);
	        		lsmap.put("fzrxm", fzrxm);
	        		lsmap.put("fzrlxfs", fzrlxfs);
	        		lsmap.put("zdlsxm", zdlsxm);
	        		lsmap.put("zdlslxfs", zdlslxfs);
	        		lsmap.put("sqly", sqly);
	        		//空行判断
  	        	    if(this.checkNullRow(lsmap)){
  	        	    	continue;
  	        	    }
  	        	    HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap,xspjsqForm,user);
  	        	    ArrayList<String> paralist = new ArrayList<String>();
  	        	    if("false".equals(resultmap.get("result"))){
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmmc"));
  	        	    	paralist.add(lsmap.get("xmlbmc"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("xh"));
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("dxqmc"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
  	        	    	if(resultmap.get("resultmap") != null ){
  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
  	        				for (Map.Entry<String, String> entry : map.entrySet()){
  	        					if(entry.getKey().indexOf("yz") != -1){
  	        						paralist.add(entry.getValue());
  	        					}
  	        					
  	        				}
  	        			}
  	        			errorlist.add(paralist.toArray(new String[]{}));
  	        			resultMap.put("result", "false");
  	        	    }else{
  	        	    	/*编辑一个id供数据插入*/
  	        			String sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
  	        	    	String splcid = this.getSplcForParam("sb").get("splc");
  	        	    	paralist.add(sqid);
  	        	    	paralist.add(lsmap.get("xh"));
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("dxqdm"));
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmdm"));
  	        	    	paralist.add(lsmap.get("xmlbdm"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
  	        	    	paralist.add(xspjsqForm.getSjlrr());
  	        	    	paralist.add("0");
  	        	    	paralist.add(splcid);
  	        	    	paralist.add(xspjsqForm.getSjlrsj());
  	        	    	drlist.add(paralist.toArray(new String[]{}));
  	        	    }
	        	}
	          resultMap.put("drlist", drlist);
        	  resultMap.put("errorlist", errorlist);
	        }
			
		}catch (BiffException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @描述: 验证表格中是否存在重复数据(项目名称、参与时间、学号不能重复)
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-22 下午02:53:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rs
	 * @param cols
	 * @param rows
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExcelRepeat(Sheet rs,int cols,int rows){
		ArrayList<String> compareList = new ArrayList<String>();
		boolean flag = true;
		for (int i = 0; i < rows; i++) {
			String xmmc = rs.getCell(0,i).getContents();
			String cysj = rs.getCell(3,i).getContents();
			String xh = rs.getCell(4, i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xmmc)){
				str = str + xmmc.trim();
			}
			if(StringUtils.isNotNull(cysj)){
				str = str + cysj.trim();
			}
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNull(str)){
				continue;
			}
			compareList.add(str);
		}
	    for (int i = 0; i < compareList.size(); i++) {
			for (int j = i+1; j < compareList.size(); j++) {
				if(compareList.get(i).equals(compareList.get(j))){
					flag = false;
					break;
				}
			}
			if(!flag){
				break;
			}
		}
		return flag;
	}
	
	/**
	 * @描述: 判断空行
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-22 下午02:53:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yzMap
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNullRow(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNotNull(yzMap.get(key))){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 将错误excel表格写入服务器
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-22 下午02:54:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist,String filepath) throws Exception{
		 String fileName = System.currentTimeMillis()+"error.xls";
		 String path = filepath + fileName;
		 File file=new File(path);
         if (!file.exists()) {
            file.createNewFile();
         }
         WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
			// 创建工作表
	        WritableSheet ws = wwb.createSheet("错误数据", 0);
	       	WritableCellFormat titlefont =  this.getFontStyle("title");
	   		/**
	   		 * sheet1
	   		 */
	   	    Label row1col1= new Label(0, 0, "项目名称",titlefont);
	   	    Label row1col2= new Label(1, 0, "指导部门",titlefont);
	   	    Label row1col3= new Label(2, 0, "项目类别",titlefont);
	   	    Label row1col4= new Label(3, 0, "参与时间",titlefont);
	   		Label row1col5= new Label(4, 0, "学号",titlefont);
	   		Label row1col6= new Label(5, 0, "学年",titlefont);
	   		Label row1col7= new Label(6, 0, "短学期",titlefont);
	   		Label row1col8= new Label(7, 0, "分值",titlefont);
	   		Label row1col9= new Label(8, 0, "负责人",titlefont);
	   		Label row1col10= new Label(9, 0, "负责人联系方式",titlefont);
	   		Label row1col11= new Label(10, 0, "指导老师",titlefont);
	   		Label row1col12= new Label(11, 0, "指导老师联系方式",titlefont);
	   		Label row1col13= new Label(12, 0, "申请理由",titlefont);
            try {
	           	ws.addCell(row1col1);
	       		ws.addCell(row1col2);
	       		ws.addCell(row1col3);
	       		ws.addCell(row1col4);
	       		ws.addCell(row1col5);
	       		ws.addCell(row1col6);
	       		ws.addCell(row1col7);
	       		ws.addCell(row1col8);
	       		ws.addCell(row1col9);
	       		ws.addCell(row1col10);
	       	    ws.addCell(row1col11);
	       	    ws.addCell(row1col12);
	       	    ws.addCell(row1col13);
			} catch (RowsExceededException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
           
           for (int i = 0; i < errorlist.size(); i++) {
                for(int j = 0;j<errorlist.get(i).length;j++){
	               	 Label labelId_i= null;
	               	 if(j<=12){
	               		  labelId_i= new Label(j, i+1, errorlist.get(i)[j]+"");
	               	 }else{
	               		 WritableCellFormat wcf = new WritableCellFormat();
	        				WritableFont wf = new WritableFont(WritableFont.ARIAL);
	        				try {
								wf.setColour(Colour.RED);
								wcf.setFont(wf);
								wcf.setAlignment(Alignment.CENTRE);
								labelId_i = new Label(j, i+1, errorlist.get(i)[j],wcf);
							} catch (RowsExceededException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
							} catch (WriteException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
							}
	               	 }
	           		try{
						ws.addCell(labelId_i);
					}catch(RowsExceededException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
					} catch (WriteException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
					}
                }
           }
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	/**
	 * @描述: 验证每行记录
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-22 下午03:10:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param xspjsqForm
	 * @param user
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap, XspjsqForm xspjsqForm,User user){
		/**
		 * 验证规则
		 * 1、项目名称、指导部门、项目类别、参与时间、学号、学年、短学期、分值不可为空
		 * 2、验证指导部门是否存在
		 * 3、验证项目类别是否存在
		 * 4、验证参与时间格式是否正确
		 * 5、学号是否存在学生信息表
		 * 6、验证短学期是否存在
		 * 7、唯一性验证
		 */
		String message = "true";
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "项目名称、指导部门、项目类别、参与时间、学号、学年、短学期、分值不可为空！");
		}else{
			//验证知道部门是否存在
			String zdbmdm = dao.checkZdbm(lsmap.get("zdbmmc"));
			if(StringUtils.isNull(zdbmdm)){
				message = "false";
				lsmap.put("zdbmyz", "指导部门不存在！");
			}
			
			//验证本学院只能导出本学院数据
			if(!user.getUserDepName().equals(lsmap.get("zdbmmc"))){
				message = "false";
				lsmap.put("zdbmyz", "只能导入本学院(园)数据！");
			}
			
			//验证项目类别是否存在
			String xmlbdm = dao.checkXmlb(lsmap.get("xmlbmc"));
			if(StringUtils.isNull(xmlbdm)){
				message = "false";
				lsmap.put("xmlbyz", "项目类别不存在！");
			}
			
			//验证参与时间格式是否正确
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try{
				Date date = (Date)formatter.parse(lsmap.get("cysj"));
				if(!lsmap.get("cysj").equals(formatter.format(date))){
					message = "false";
					lsmap.put("cysjgsyz", "参与时间格式必须为yyyy-MM-dd！");
				}
			}catch (ParseException e){
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
			//验证学号是否存在学生信息表
			if(!dao.checkXhisTrue(lsmap.get("xh"))){
				message = "false";
				lsmap.put("xsxhyz", "学号不存在或有空格！");
			}
			
			//验证短学期是否存在
			String dxqdm = dao.checkDxq(lsmap.get("dxqmc"));
			if(StringUtils.isNull(dxqdm)){
				message = "false";
				lsmap.put("dxqyz", "短学期不存在！");
			}
			
			//主键验证
			if(!dao.checkIsNotExists(lsmap.get("xmmc"), lsmap.get("cysj"), lsmap.get("xh"), null)){
				message = "false";
				lsmap.put("zjyz", "同一个学生在同一时间申报过此项目，请确认！");
			}
			
			//备注验证
			if(lsmap.get("sqly").trim().length() > 200){
				message = "false";
				lsmap.put("sqlyyz", "备注不能超过200字！");
			}
			
			if("true".equals(message)){
				lsmap.put("zdbmdm", zdbmdm);
				lsmap.put("xmlbdm", xmlbdm);
				lsmap.put("dxqdm", dxqdm);
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @描述: 验证非空项
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-22 下午03:10:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yzMap
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNull(yzMap.get(key)) && !("fzrxm").equals(key) && !("fzrlxfs").equals(key) && !("zdlsxm").equals(key) && !("zdlslxfs").equals(key) && !("sqly").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @描述: excel字体
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-22 下午03:10:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat 返回类型 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras) throws WriteException{
		
		/** 
         * 定义单元格样式 
         */ 
		if("title".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);  
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("errorinfo".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.RED);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}
     
        return null;
	}
}
