package com.zfsoft.xgxt.jskp.lxsq;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshDao;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshForm;

public class LxsqService extends SuperServiceImpl<LxsqForm,LxsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述: 负责人信息(学生)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-7 上午11:53:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxStu(String username){
		return dao.getFzrxxStu(username);
	}
	
	/**
	 * 
	 * @描述: 负责人信息(老师)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-7 上午11:54:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFzrxxTea(String username){
		return dao.getFzrxxTea(username);
	}
	
	/**
	 * 
	 * @描述: 获取可获得的学号
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-7 下午05:11:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAvailableXhList(String[] xhs,String sqid,String ryflag){
		return dao.getAvailableXhList(xhs,sqid,ryflag);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-11 上午08:57:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean  saveForLxsq(LxsqForm lxsq,User user) throws Exception{
		boolean rs = true;
		
		String sqid1 = UniqID.getInstance().getUniqIDHash().toUpperCase();
		
		/*参数设置为否时取【上报】流程*/
		if("0".equals(new CsszService().getSfsh())){
			lxsq.setSplcid(new CsszService().getSplc("sb").get("splc"));
			/*唯一条件是学号、项目名称、立项时间*/
			if(!this.checkForPara(lxsq)){
				throw new SystemException(MessageKey.XG_ZJDX_JSKP_REPEAT);
			}
		}else{
			if(!this.checkIsNotRepeat(lxsq)){
				throw new SystemException(MessageKey.XG_ZJDX_JSKP_REPEAT);
			}
			lxsq.setSplcid(new CsszService().getSplc("lx").get("splc"));
		}

		if("submit".equals(lxsq.getSaveFlag())){
			lxsq.setShzt(Constants.YW_SHZ);
		}else{
			lxsq.setShzt(Constants.YW_WTJ);
		}
		
		if(StringUtils.isNotNull(lxsq.getSqid())){
			rs = dao.delRy(lxsq.getSqid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdate(lxsq);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*当参数设置为否，此时分值类型在页面已经被屏蔽，为不影响后面的功能使用，默认把最小分和最大分设置为0-10*/
			if("0".equals(new CsszService().getSfsh())){
				lxsq.setZxf("0");
				lxsq.setZdf("10");
				/*在插入一个字段值，来判断这个数据来源来自非审核数据，以后可以判断垃圾数据*/
				lxsq.setSjly("NO");
			}else{
				lxsq.setSjly("YES");
			}
			/*当前学年*/
			lxsq.setLxxn(Base.currXn);
			/*自主生成的id插入form*/
			lxsq.setSqid(sqid1);
			/*根据客户要求，要在增加数据时候加入录入数据的用户名。字段：数据录入人【sjlrr】插入form*/
			lxsq.setSjlrr(user.getUserName());
			rs = dao.runInsert(lxsq);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> params = new ArrayList<String[]>();
		String sbsj = GetTime.getTimeByFormat(DATE_FORMAT);
		if("1".equals(new CsszService().getSfsh())){
			String[] xhs = lxsq.getXhs();
			if(xhs != null && xhs.length > 0){
				for (int i = 0; i < xhs.length; i++) {
					params.add(new String[]{xhs[i],lxsq.getSqid(),lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),Constants.YW_WTJ,"0" });
				}
				rs = dao.saveRy(params);
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
		}else{
			if(!"Edit".equals(lxsq.getTjsf())){
				/*申请页面提交判断*/
				params.add(new String[]{sqid1,lxsq.getXh(),sqid1,lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),Constants.YW_SHZ,"1",lxsq.getFilepath()});
				rs = dao.saveRyOne(params);
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}else{
				/*修改页面提交判断*/
				params.add(new String[]{lxsq.getSqid(),lxsq.getXh(),lxsq.getSqid(),lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),Constants.YW_SHZ,"1",lxsq.getFilepath()});
				rs = dao.saveRyOne(params);
				if(!rs){
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
		}
		if("submit".equals(lxsq.getSaveFlag())){
			if("1".equals(new CsszService().getSfsh())){
				rs = shlc.runSubmit(lxsq.getSqid(),lxsq.getSplcid(),lxsq.getFzr(), "pjpy_jskp_lxsh.do", "pjpy_jskp_lxsq.do");
			}else{
				if(!"Edit".equals(lxsq.getTjsf())){
					rs = shlc.runSubmit(sqid1,lxsq.getSplcid(), lxsq.getFzr(), "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
				}else{
					rs = shlc.runSubmit(lxsq.getSqid(),lxsq.getSplcid(), lxsq.getFzr(), "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
				}
				
			}
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 验证是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-11 下午02:36:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(LxsqForm lxsq){
		return dao.checkIsNotRepeat(lxsq);
	}
	
	/**
	 * 验证学号、项目名称、立项时间是否重复
	 */
	public boolean checkForPara(LxsqForm lxsq){
		return dao.checkForPara(lxsq);
	}
	
	/**
	 * 
	 * @描述:获取项目参与人员的学号
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-12 下午02:31:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmcyryXhs(String xmid){
		return dao.getXmcyryXhs(xmid);
	}
	
	/**
	 * 
	 * @描述:删除人员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-12 下午03:11:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRys(String[] sqids) throws Exception{
		return dao.delRys(sqids);
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @描述: 批量提交审核流
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-7 下午04:23:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusi(LxsqForm model, User user)  throws Exception {
		String sfsh = new CsszDao().getSfsh();
		boolean flag = false;
		String splc = null;
		
		if("1".equals(sfsh)){
			splc = new CsszService().getSplc("lx").get("splc");
	        if(Constants.YW_YTH.equals(model.getShzt())){
	        	splc = model.getSplcid();
			}
	        model.setShzt(Constants.YW_SHZ);
			model.setSplcid(splc);
			flag = dao.runUpdate(model);
		}else{
			JskpXmsbshForm jskpXmsbshForm = new JskpXmsbshForm();
			JskpXmsbshDao jskpXmsbshDao = new JskpXmsbshDao();
			splc = new CsszService().getSplc("sb").get("splc");
			jskpXmsbshForm.setShzt(Constants.YW_SHZ);
			jskpXmsbshForm.setSplcid(splc);
			jskpXmsbshForm.setSqid(model.getSqid());
			flag = jskpXmsbshDao.runUpdate(jskpXmsbshForm);
		}
		
		if(flag){
			if("1".equals(sfsh)){
				shlc.runSubmit(model.getSqid(), splc, model.getFzr(), "pjpy_jskp_lxsh.do", "pjpy_jskp_lxsq.do");
			}else{
				shlc.runSubmit(model.getSqid(), splc, model.getFzr(), "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 批量提交
	 */
	public boolean plSubmit(String sqid,String splc,String fzr) throws Exception{
		boolean flag = false;
		JskpXmsbshForm jskpXmsbshForm = new JskpXmsbshForm();
		JskpXmsbshDao jskpXmsbshDao = new JskpXmsbshDao();
		jskpXmsbshForm.setShzt(Constants.YW_SHZ);
		jskpXmsbshForm.setSplcid(splc);
		jskpXmsbshForm.setSqid(sqid);
		flag = jskpXmsbshDao.runUpdate(jskpXmsbshForm);
		if(flag){
			shlc.runSubmit(sqid,splc,fzr, "jskp_xmsh.do", "pjpy_jskp_lxsq.do");
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-12 下午05:46:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRyszList(LxsqForm t, User user) throws Exception{
		return dao.getRyszList(t, user);
	} 
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存人员设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 上午09:48:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveRysz(LxsqForm lxsq) throws Exception{
		String sbsj = GetTime.getTimeByFormat(DATE_FORMAT);
		String[] xhs = lxsq.getXhs();
		String lxzt = "1".equals(lxsq.getShzt()) ? "1" :"0";
		String shzt = "1".equals(lxsq.getShzt()) ? Constants.YW_SHZ:Constants.YW_WTJ;
		lxsq = this.getModel(lxsq);
		List<String[]> params = new ArrayList<String[]>();
		List<String> sqidList = new ArrayList<String>();
		for (int i = 0; i < xhs.length; i++) {
			String sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			params.add(new String[]{sqid,xhs[i],lxsq.getSqid(),lxsq.getLxsj(),sbsj,new CsszService().getSplc("sb").get("splc"),shzt,lxzt });
			sqidList.add(sqid);
		}
		boolean rs = dao.saveRys(params);
		if("1".equals(lxsq.getShzt()) && rs){
			rs = shlc.runSubmitBatchNotCommit(sqidList.toArray(new String[]{}), new CsszService().getSplc("sb").get("splc"), xhs, "jskp_xmsh.do", "jskp_xmsb.do");
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述:获取部门名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 下午03:20:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBmmc(String bmdm){
		return dao.getBmmc(bmdm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除人员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-20 下午04:24:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXmry(String[] sqids) throws Exception{
		return dao.delXmry(sqids);
	}
	
	/**
	 * 
	 * @描述: 是否所有记录都未被审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-21 上午09:34:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isNotHaveShjl(String[] ywids){
		return dao.isNotHaveShjl(ywids);
	}
	
	/**
	 * @描述: 判断学生的年级是否大于等于2017
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-17 下午04:38:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isStandardStu(User user) throws Exception{
		boolean flag = false;
		if("stu".equals(user.getUserType())){
			flag = dao.isStandardStu(user.getUserName());
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @描述: 下载导入模板,绘制excel表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-23 下午06:17:01
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
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("仿宋"),11,WritableFont.NO_BOLD );
		
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
	    ws.setColumnView(2, 16);/*项目类别名称*/
	    ws.setColumnView(3, 30);/*参与时间(如:2018-01-01)*/
	    ws.setColumnView(4, 16);/*负责人*/
	    ws.setColumnView(5, 20);/*负责人联系方式*/
	    ws.setColumnView(6, 16);/*指导老师*/
	    ws.setColumnView(7, 26);/*指导老师联系方式*/
	    ws.setColumnView(8, 15);/*参与人学号*/
	    ws.setColumnView(9, 36);/*立项理由*/
		
		/*第一行抬头*/
		Label row1col1= new Label(0, 0, "项目名称",body);
		Label row1col2= new Label(1, 0, "指导部门",body);
		Label row1col3= new Label(2, 0, "项目类别名称",body);
		Label row1col4= new Label(3, 0, "参与时间(如:2018-01-01)",body);
		Label row1col5= new Label(4, 0, "负责人",body);
		Label row1col6= new Label(5, 0, "负责人联系方式",body);
		Label row1col7= new Label(6, 0, "指导老师",body);
		Label row1col8= new Label(7, 0, "指导老师联系方式",body);
		Label row1col9= new Label(8, 0, "参与人学号",body);
		Label row1col10= new Label(9, 0, "立项理由",body);
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
	    
	    /**sheet2：项目类别对照表*/
	    WritableSheet ws1 = wwb.createSheet("项目类别对照表", 1);
	    List<HashMap<String, String>> xmlbList = dao.getXmlbList();
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
		try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
			 
		 }
	}
	
	/**
	 * @描述: 表格中的数据保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-24 上午10:07:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param lxsqForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,LxsqForm lxsqForm,User user){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap = this.DrExcelInfoCheck(is, lxsqForm,user);
			/**判断Excle表格是否为空*/
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				/**把数据导入申请表*/
				List<String[]> paraSqblist = (List<String[]>) resultMap.get("drSqblist");
				if( paraSqblist == null ||paraSqblist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrSqbData(paraSqblist);
			  	 
			  	 /**上报表*/
			  	List<String[]> paraSbblist = (List<String[]>) resultMap.get("drSbblist");
				if( paraSbblist == null ||paraSbblist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrSbbData(paraSbblist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),lxsqForm.getExclePath());
				resultMap.put("gid", gid);
				this.saveDrSqbData((List<String[]>) resultMap.get("drSqblist"));
				this.saveDrSqbData((List<String[]>) resultMap.get("drSbblist"));
				logger.info("导入失败！");
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return resultMap;
	}
	
	
	/**
	 * @描述: 导入信息验证
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-24 上午10:37:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param lxsqForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,LxsqForm lxsqForm,User user){
		
		/**导入记录数组*/
		/*导入申请表数组*/
		List<String[]> drSqblist = new ArrayList<String[]>();
		/*导入上报表数组*/
		List<String[]> drSbblist = new ArrayList<String[]>();
		
		
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		Workbook rwb = null;
		
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
			/*得到所有的列*/
		    int clos=rs.getColumns();
		    /*得到所有的行*/
	        int rows=rs.getRows();
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){
	        	resultMap.put("result", "excelrepeat");
	        }else{
	        	/*行*/
	        	for (int i = 1; i < rows; i++) {
					/*取出每行验证数据，塞入lsmap*/
					HashMap<String, String>  lsmap = new HashMap<String, String>();
					String xmmc = rs.getCell(0, i).getContents();
					String zdbmmc = rs.getCell(1, i).getContents();
					String xmlbmc = rs.getCell(2, i).getContents();
					String cysj = rs.getCell(3, i).getContents();
					String fzrxm = rs.getCell(4, i).getContents();
					String fzrlxfs = rs.getCell(5, i).getContents();
					String zdls = rs.getCell(6, i).getContents();
					String zdlslxfs = rs.getCell(7, i).getContents();
					String xh = rs.getCell(8, i).getContents();
					String lxly = rs.getCell(9, i).getContents();
					lsmap.put("xmmc", xmmc);
					lsmap.put("zdbmmc", zdbmmc);
					lsmap.put("xmlbmc", xmlbmc);
					lsmap.put("cysj", cysj);
					lsmap.put("fzrxm", fzrxm);
					lsmap.put("fzrlxfs",fzrlxfs);
					lsmap.put("zdls",zdls);
					lsmap.put("zdlslxfs", zdlslxfs);
					lsmap.put("xh", xh);
					lsmap.put("lxly", lxly);
					//空行判断
					if(this.checkNullRow(lsmap)){
						continue;
					}
  	        		
  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, lxsqForm);
  	        		ArrayList<String> paralist = new ArrayList<String>();
  	        		/*上报表数据用的paralistSbb*/
  	        		ArrayList<String> paralistSbb = new ArrayList<String>();
  	        		
  	        		if("false".equals(resultmap.get("result"))){
  	        			paralist.add(lsmap.get("xmmc"));
  	        			paralist.add(lsmap.get("zdbmmc"));
  	        			paralist.add(lsmap.get("xmlbmc"));
  	        			paralist.add(lsmap.get("cysj"));
  	        			paralist.add(lsmap.get("fzrxm"));
  	        			paralist.add(lsmap.get("fzrlxfs"));
  	        			paralist.add(lsmap.get("zdls"));
  	        			paralist.add(lsmap.get("zdlslxfs"));
  	        			paralist.add(lsmap.get("xh"));
  	        			paralist.add(lsmap.get("lxly"));
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
  	        			/*取上报的审批流程*/
  	        			String splc = new CsszService().getSplc("sb").get("splc");
  	        			/*去系统当前时间*/
  	        			
  	        			/**第一批：出申请表数据*/
  	        			paralist.add(sqid);                   /*每次循环前先生成一次sqid*/
  	        			paralist.add(lsmap.get("xmmc"));      /*项目名称*/
  	        			paralist.add(lsmap.get("zdbmdm"));    /*指导部门代码*/
  	        			paralist.add(lsmap.get("xmlbdm"));    /*项目类别代码*/
  	        			paralist.add(lsmap.get("cysj"));      /*立项时间（参与时间）*/
  	        			paralist.add(lsmap.get("fzrgh"));     /*负责人工号或者学号*/
  	        			paralist.add(lsmap.get("fzrlxfs"));   /*负责人联系方式*/
  	        			paralist.add(lsmap.get("zdls"));      /*指导老师*/
  	        			paralist.add(lsmap.get("zdlslxfs"));  /*指导老师联系方式*/
  	        			paralist.add(lsmap.get("lxly"));      /*立项理由*/
  	        			paralist.add(splc);	                  /*审批流程*/
  	        			paralist.add("0");					  /*审核状态*/
  	        			paralist.add("10");					  /*最大分*/
  	        			paralist.add("0");					  /*最小分*/
  	        			paralist.add("NO");					  /*最小分*/
  	        			paralist.add(Base.currXn);			  /*当前学年*/
  	        			paralist.add(user.getUserName());	  /*获取当前登录用的用户名，导入系统，记录谁导入了这条记录*/
  	        			drSqblist.add(paralist.toArray(new String[]{}));
  	        			
  	        			/**第二批：出上报数据*/
  	        			paralistSbb.add(sqid);				  /*每次循环前先生成一次sqid*/
  	        			paralistSbb.add(lsmap.get("xh"));	  /*参与人学号*/
  	        			paralistSbb.add(sqid);				  /*项目ID，其实也是sqid*/
  	        			paralistSbb.add(lsmap.get("cysj"));	  /*参与时间*/
  	        			paralistSbb.add(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));/*系统当前时间，上报时间*/
  	        			paralistSbb.add(splc);				  /*审批流程*/
  	        			paralistSbb.add("0");				  /*审核状态*/
  	        			paralistSbb.add("1");				  /*立项状态*/
  	        			drSbblist.add(paralistSbb.toArray(new String[]{}));
  	        		}
  				}
        	  resultMap.put("drSqblist", drSqblist);
        	  resultMap.put("drSbblist", drSbblist);
        	  resultMap.put("errorlist", errorlist);
	        }
	      
			} catch (BiffException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return resultMap;
	}
	
	/**
	 * @描述: 验证表格中是否有重复数据（参与人、参与时间、参与项目名称）
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-24 上午11:16:31
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
			/*项目名称*/
			String xmmc = rs.getCell(0, i).getContents();
			/*立项时间（参与时间）*/
			String lxsj = rs.getCell(3,i).getContents();
			/*学号（参与人）*/
			String xh = rs.getCell(8,i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNotNull(xmmc)){
				str = str + xmmc.trim();
			}
			if(StringUtils.isNotNull(lxsj)){
				str = str + lxsj.trim();
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
	 * @描述: 检查空行
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-24 上午11:49:02
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
	 * @描述: 验证每行的记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-24 上午11:53:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param lxsqForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,LxsqForm lxsqForm){
		
		/**
		 * 1、学号是否存在xsxxb中！
		 * 2、指导部门不存在，请检查！
		 * 3、项目类别不存在，请检查！
		 * 4、参与时间格式不合法，请按照（2018-01-01）格式填写！
		 * 5、负责人在系统中不存在！
		 * 6、联系方式不正确
		 */
		String message = "true";
		
		/**验证项目名称、指导部门、项目类别、参与时间、负责人、参与人是否为空*/
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "项目名称、指导部门、项目类别、参与时间、负责人、参与人不可为空！");
		}
		
		/**验证指导部门是否存在*/
		String zdbm = dao.getZdbmdm(lsmap.get("zdbmmc"));
		if(StringUtils.isNull(zdbm)){
			message = "false";
			lsmap.put("zdbmyz", "指导部门不存在或存在空格，请核对！");
		}
		
		/**验证项目类别是否存在*/
		String xmlb = dao.getXmlbdm(lsmap.get("xmlbmc"));
		if(StringUtils.isNull(xmlb)){
			message = "false";
			lsmap.put("xmlbyz", "项目类别不存在或存在空格，请核对！");
		}
		
		/**验证参与时间是否正确*/
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = (Date)formatter.parse(lsmap.get("cysj"));
			if(!lsmap.get("cysj").equals(formatter.format(date))){
				message = "false";
				lsmap.put("cysjyz", "参与时间格式必须为yyyy-mm-dd！");
			}
		} catch (ParseException e) {
			message = "false";
			lsmap.put("cysjyz", "参与时间格式必须为yyyy-mm-dd！");
		} 
		
		/**验证负责人是否存在*/
		String fzr = dao.getFzr(lsmap.get("fzrxm"));
		if(StringUtils.isNull(fzr)){
			message = "false";
			lsmap.put("fzryz", "负责人不存在或者存在空格，请核对！");
		}
		
		/**验证参数人是否存在*/
		String cyr = dao.getCyr(lsmap.get("xh"));
		if(StringUtils.isNull(cyr)){
			message = "false";
			lsmap.put("cyryz", "参与人学号不存，请核对！");
		}
		
		/**主键验证*/
		if(!dao.checkIsNotExists(lsmap.get("xh"), lsmap.get("xmmc"), lsmap.get("cysj"))){
			message = "false";
		    lsmap.put("zjyz", "学生在同一日期已经申请过该项目，请检查！");
		}
		
		/**当所有验证都没有问题时，*/
		if("true".equals(message)){
			lsmap.put("zdbmdm", zdbm);/*指导部门代码*/
			lsmap.put("fzrgh", fzr);/*负责人工号*/
			lsmap.put("xmlbdm", xmlb);/*负责人工号*/
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @描述: 验证非空项(必填字段有xmmc、zdbmmc、xmlbmc、cysj、fzrxm、xh)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-24 下午02:28:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yzMap
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String,String> yzMap){
		
		boolean rs = true;
		for(String key : yzMap.keySet()){
			if(StringUtils.isNull(yzMap.get(key)) && !("lxly").equals(key) && !("zdlslxfs").equals(key) && !("zdls").equals(key) && !("fzrlxfs").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 批量导入项目申请表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-26 上午09:51:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDrSqbData(List<String[]> paraSqblist) 
		throws Exception{
		if(paraSqblist != null && paraSqblist.size() > 0){
			return dao.saveDrSqbData(paraSqblist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @描述: 批量导入项目上报表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-26 上午11:06:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paraSqblist
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDrSbbData(List<String[]> paraSqblist) 
		throws Exception{
		if(paraSqblist != null && paraSqblist.size() > 0){
			return dao.saveDrSbbData(paraSqblist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @描述: 将错误excel表格写入服务器
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-26 上午11:34:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist,String filepath)
		throws Exception{
		
		String fileName = System.currentTimeMillis()+"error.xls";
		String path = filepath+fileName;
		File file=new File(path);
		
        if (!file.exists()) {
            file.createNewFile();
        }
        WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
			/*创建工作表*/
			WritableSheet ws = wwb.createSheet("错误数据", 0);
       		WritableCellFormat titlefont =  this.getFontStyle("title");
   		/**
   		 * sheet1
   		 */
   	    Label row1col1= new Label(0, 0, "项目名称",titlefont);
   	    Label row1col2= new Label(1, 0, "指导部门",titlefont);
   	    Label row1col3= new Label(2, 0, "项目类别名称",titlefont);
   	    Label row1col5= new Label(3, 0, "参与时间",titlefont);
   		Label row1col6= new Label(4, 0, "负责人",titlefont);
   		Label row1col7= new Label(5, 0, "负责人联系方式",titlefont);
   		Label row1col8= new Label(6, 0, "指导老师",titlefont);
   		Label row1col9= new Label(7, 0, "指导老师联系方式",titlefont);
   		Label row1col10= new Label(8, 0, "参与人学号",titlefont);
   		Label row1col11= new Label(9, 0, "立项理由",titlefont);
   		
           try {
           	ws.addCell(row1col1);
       		ws.addCell(row1col2);
       		ws.addCell(row1col3);
       		ws.addCell(row1col5);
       		ws.addCell(row1col6);
       		ws.addCell(row1col7);
       		ws.addCell(row1col8);
       		ws.addCell(row1col9);
       		ws.addCell(row1col10);
       	    ws.addCell(row1col11);
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
               	 if(j<=9){
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
               		try {
							ws.addCell(labelId_i);
						} catch (RowsExceededException e) {
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
	 * @描述: 错误excle导出字体样式
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-1-26 上午11:39:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat 返回类型 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras)
		throws WriteException{

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

	/**
	 * @描述: 根据ID查询学生申报项目的信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-3 下午05:59:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuSbDataList(String[] sqidArr)throws Exception{
		return dao.getStuSbDataList(sqidArr);
	}
}
