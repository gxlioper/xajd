package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;
import org.mortbay.http.HttpRequest;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class XsgzzbsqService extends SuperServiceImpl<XsgzzbsqForm, XsgzzbsqDao> {

	private XsgzzbsqDao dao = new XsgzzbsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	
	//四川信息职业技术学院附件上传功能
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};
	public XsgzzbsqService() {
		super.setDao(dao);
	}
	
	/**
	 * 动态取表
	 */
	protected void setTableInfo(XsgzzbsqForm t) {
		dao.setTableInfo(t);
	}
	
	/**
	 * 班级周报
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbsqForm t, User user)
		throws Exception {
		return dao.getPageListBj(t, user);
	}

	/**
	 * @描述:增加周报申请
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveXsgzzbsq(XsgzzbsqForm model) throws Exception {
		String gzzblx = model.getGzzblx();
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID(gzzblx);
		model.setSplc(splc);
		dao.setTableInfo(model); // 动态取表
		String filegid = UniqID.getInstance().getUniqIDHash();//动态生成guid
		model.setFilegid(filegid);
		boolean insertResult = super.runInsert(model);
        if(Base.xxdm.equalsIgnoreCase("13815")){
			this.uploadFile(model);
		}
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
			String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
			if("bj".equals(gzzblx)){
				sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
				shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
			}
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(),splc,model.getLrr(),shURL,sqURL);
		}
		return result;
	}
	
	/**
	 * 班级周报
	 */
	public XsgzzbsqForm getModelBj(XsgzzbsqForm t) throws Exception {
		return dao.getModelBj(t);
	}
	
	/**
	 * @描述:当前学期名称
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCurrentXqmc(XsgzzbsqForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
	
	/**
	 * @描述: 获取学院列表
	 */
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}
	/**
	 * @描述: 获取班级列表
	 */
	public List<HashMap<String, String>> getBjList(XsgzzbsqForm t, User user)
		throws Exception {
		return dao.getBjList(t, user);
	}
	
	/**
	 * @描述: 获取所有周次的起止日期
	 */
	public List<HashMap<String, String>> getZcList(){
		// 得到学期周数,首周起始日期
		HashMap<String, String> zcMap = dao.getZc();
		String xqzs = zcMap.get("xqzs");
		String qsrq = zcMap.get("qsrq");
		if(StringUtils.isNull(xqzs) || StringUtils.isNull(qsrq)){
			return new ArrayList<HashMap<String, String>>();
		}
		xqzs = xqzs.trim();
		qsrq = qsrq.trim();
		int xqzsNum = Integer.parseInt(xqzs);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		List<HashMap<String, String>>  zcList = new ArrayList<HashMap<String, String>>();
		try {
			for (int i = 0; i < xqzsNum; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				// 首周起始日期
				c.setTime(DateFormat.getDateInstance().parse(qsrq));
				// 第i周
				c.add(Calendar.WEEK_OF_YEAR, i);
				// 第i周开始日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				if(i == 0){ // 第一周开始日期为数据库配置的起始日期
					map.put("ksrq", qsrq);
				}else{
					map.put("ksrq", f.format(c.getTime()));
				}
				// 第i周结束日期
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				map.put("jsrq", f.format(c.getTime()));
				map.put("dm", String.valueOf(i + 1));
				map.put("mc", "第" + (i + 1) + "周");
				zcList.add(map);
			}
		} catch (Exception e) {
			logger.error("学期周数、首周起始日期还未初始化！");
			e.printStackTrace();
		}
		return zcList;
	}
	
	/**
	 * @描述:修改周报申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXsgzzbsq(XsgzzbsqForm model) throws Exception {
		String gzzblx = model.getGzzblx();
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID(gzzblx));
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}
		dao.setTableInfo(model);
		String previousgid = this.getFileGID(model.getSqid());
		if(StringUtils.isNull(previousgid)){
			String filegid = UniqID.getInstance().getUniqIDHash();//动态生成guid
			model.setFilegid(filegid);
		}else{
			model.setFilegid(previousgid);
		}
		boolean insertResult = super.runUpdate(model);
		if(Base.xxdm.equalsIgnoreCase("13815") && model.getScbz().equalsIgnoreCase("yfj")){
			this.uploadFile(model);
		}
		boolean result = true;
		if (insertResult && SUBMIT.equals(model.getType())) {
			shlc.deleteShjl(model.getSqid());
			String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
			String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
			if("bj".equals(gzzblx)){
				sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
				shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
			}
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getLrr(),shURL,sqURL);
		}
		return insertResult && result;
	}
	
	/**
	 * @描述:提交周报申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitXsgzzbsq(XsgzzbsqForm model) throws Exception {
		String gzzblx = model.getGzzblx();
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID(gzzblx));
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultXsgzzbsq = dao.updateXsgzzbsq(model);
		boolean result = false;
		if(resultXsgzzbsq){
			String sqURL = "rcsw_xsgzzb_xsgzzbsq.do";
			String shURL = "rcsw_xsgzzb_xsgzzbsh.do";
			if("bj".equals(gzzblx)){
				sqURL = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
				shURL = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
			}
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getLrr(),shURL,sqURL);
		}
		return result;
	}
	
	/**
	 * @描述:更新周报申请
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelXsgzzbsq(XsgzzbsqForm model) throws Exception {
		boolean resultXsgzzbsq = dao.updateXsgzzbsq(model);
		return resultXsgzzbsq;
	}
	
	/**
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	
	
	@Override
	public List<HashMap<String, String>> getAllList(XsgzzbsqForm t, User user)
			throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		String gzzblx = t.getGzzblx();
		if("bj".equals(gzzblx)){
			return dao.getPageListBj(t, user);
		}
		return dao.getPageList(t, user);
	}

	/**
	 * @描述:是否已经存在
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean isExistByXszbbsq(XsgzzbsqForm model, User user) throws Exception {
		boolean flag = false;
		String gzzblx = model.getGzzblx();
		String num = "";
		if("bj".equals(gzzblx)){
			num = dao.checkExistForSaveBj(model, user);
		}else{
			num = dao.checkExistForSave(model, user);
		}
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:四川信息职业技术学院附件上传个性化功能
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-19 下午02:20:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean uploadFile(XsgzzbsqForm model){
		String[] wjlxdms=model.getWjlxdms();
		if(wjlxdms == null || wjlxdms.length == 0){
			return true;
		}
		int arrLen = wjlxdms.length;
		XsgzzbsqDao dao = new XsgzzbsqDao();
		//获取附件内容
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		//附件表实体对象
		XsgzzbsqForm fjmodel = new XsgzzbsqForm();
		boolean insertResult = true;
		if(wjlxdms != null){
			for(int i=0;i<arrLen;i++){
				fjmodel.setWjlxdm(wjlxdms[i]);
				//处理附件
				FormFile file = (FormFile) files.get("fjid"+i);
				if (file != null && !StringUtil.isNull(file.getFileName())){
					String basePath = resource.getString("filesys.local.dir");
					//如果没有给定文件存储路径，就默认给系统用户目录
					if(StringUtils.isNull(basePath)){
						basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
					}
					String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
					
					if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
						File srcFile = null;
						try {
							srcFile = FileUtil.conversionFormFile(file);
						} catch (IOException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
						File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
						try {
							FileUtils.copyFile(srcFile, destFile);
						} catch (IOException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
						fjmodel.setFjlj(destFile.getAbsolutePath());
						fjmodel.setFjmc(file.getFileName());
						fjmodel.setFilegid(model.getFilegid());
					}
				}
				
				try {
					insertResult = dao.savefjb(fjmodel);
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
		
		return insertResult;
	}
	
	/**
	 * 四川信息职业技术学院，结果删除时先去获取所删除记录的filegid
	 */
	public String getFileGID(String sqid) throws Exception{
		return dao.getFileGID(sqid);
	}
	
	/**
	 * 四川信息职业技术学院，结果删除时先去获取所删除记录的fjlj
	 */
	public List<HashMap<String, String>>  getfjlj(String[]filegids) throws Exception{
		return dao.getfjlj(filegids);
	}
	
	/**
	 * 四川信息职业技术学院，结果删除时先删除附件表中的内容
	 * @throws Exception 
	 */
	public boolean Delfile_13815(String[] filegids) throws Exception{
		return dao.Delfile_13815(filegids);
	}
	
	/**
	 * 四川信息职业技术学院删除附件表中的内容成功后，最后直接删除服务器上的真实附件文件
	 * 
	 */
	public boolean Delfile_13815_realfile(List<HashMap<String, String>> fjljs){
		int fjljslen = fjljs.size();
		boolean result = true;
		for(int i = 0;i < fjljslen;i++){
			File file = new File(fjljs.get(i).get("fjlj"));
			if (file.exists()){
				result = file.delete();
			}
		}
		return result;
	}
	
	/**
	 * 修改的时候查询是否有filegid来判断是否有原文件
	 */
	public String getUpdateFilegid(String sqid){
		return dao.getUpdateFilegid(sqid);
	}
	
	/**
	 * 修改界面单个删除附件但不删除主表记录的情况，获取单个文件的文件fjlj
	 */
	public HashMap<String, String> onefjlj(String fileid){
		return dao.onefjlj(fileid);
	}
	
	/**
	 * 删除单个附件
	 */
	public boolean delonefjlj(String fileid) throws Exception{
		return dao.delonefjlj(fileid);
	}
}
