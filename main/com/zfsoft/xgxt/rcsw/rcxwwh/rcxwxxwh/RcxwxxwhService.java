/**
 * @部门:学工产品事业部
 * @日期：2013-8-2 上午09:24:48 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为信息维护 
 * @作者： Dlq [工号：995]
 * @时间： 2013-8-2 上午09:24:48
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RcxwxxwhService extends
		SuperServiceImpl<RcxwxxwhForm, RcxwxxwhDao> {

	private RcxwxxwhDao dao = new RcxwxxwhDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static final String BACK = "back";
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};

	public RcxwxxwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * 获取行为类别集合
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午04:06:00
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
	 * 获取行为大类集合
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午04:06:20
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
	 * @描述:查询在日常行为维护中是否存在
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:07:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByXwxxwh(RcxwxxwhForm model, String type)
			throws Exception {
		boolean flag = false;
		if ("save".equalsIgnoreCase(type)) {
			String shzt = dao.checkExistForSave(model);
			if (!"0".equalsIgnoreCase(shzt)) {
				flag = true;
			}
		} else if ("update".equalsIgnoreCase(type)) {
			String shzt = dao.checkExistForUpdate(model);
			if (!"0".equalsIgnoreCase(shzt)) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @描述:保存日常维护
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:09:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param checkShzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveRcww(RcxwxxwhForm model) throws Exception {
		
	/*	if(checkShzt){
			model.setShzt(Constants.YW_WTJ);
		}*/
		String[] xwlbdmArr=model.getXwlbdmArr();
		String[] fzArray=model.getFzArray();
		String[] xwdldmArr=model.getXwdldmArr();
		String[] fssjArr=model.getFssjArr();
		String[] gflyArr=model.getGflyArr(); 
		//附件
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		
		for(int i=0;i<xwlbdmArr.length;i++){
			
			String guid = UniqID.getInstance().getUniqIDHash();
			model.setId(guid);
			
			RcxwxxwhForm form=new RcxwxxwhForm();
			form.setRcxwlbdldm(xwdldmArr[i]);
			//验证是否需要设
			boolean checkShzt=checkForSplc(form);
			if(model.getType().equals("submit")&&checkShzt){
				model.setShzt(Constants.YW_SHZ);//审核中
			}else if(model.getType().equals("submit")&&!checkShzt){
				model.setShzt("");//无需审核
			}else{
				model.setShzt(Constants.YW_WTJ);//未提交
			}
			// 获取审批流程
			String splc = dao.getShlcID(xwdldmArr[i]);
			model.setSplc(splc);
			
			model.setFz(fzArray[i]);
			model.setRcxwlbdm(xwlbdmArr[i]);
			model.setRcxwlbdldm(xwdldmArr[i]);
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
			boolean result = false;
			if (checkShzt&&insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
				//保存审核流程
				result = shlc.runSubmit(model.getId(), splc,model.getXh(),"rcsw_rcxwwh_rcxwsh.do","rcsw_rcxwwh_rcxwxxwh.do");
			}
			//不需要走审核流程直接把数据保存到日常行为结果表中
        	if(!checkShzt&&insertResult&& SUBMIT.equalsIgnoreCase(model.getType())){
        		
        		RcxwjgForm rcxwjgForm = new RcxwjgForm();
        		RcxwjgService rcxwjgService = new RcxwjgService();
        		BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(model));
        		//天津市经济贸易学校个新华
        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
        			model.setShzt("1");
        			dao.runUpdate(model);
        		}
        		//rcxwjgForm.setId(model.getId());
        		rcxwjgForm.setId(guid);
        		rcxwjgForm.setSjly("1");
        		result = rcxwjgService.runInsert(rcxwjgForm);

        	}
		}
		
//		if( SAVE.equalsIgnoreCase(model.getType())){
//			return insertResult;
//		}
		return true;
	}
	
	
	public boolean submitRcww(RcxwxxwhForm model) throws Exception {
		
		boolean isSplc =  false;
		if(!"无需审核".equalsIgnoreCase(model.getSplc())){
			isSplc = true;
		}
		String shzt="";
		if(isSplc){
			shzt = Constants.YW_SHZ;
		}
		
		boolean resultRcww = dao.updateRcxwxxwh(model.getId(),model.getSplc(), shzt);
		boolean result = false;
		if(resultRcww&&isSplc){
		// 获取审批流程
//		String splc = dao.getShlcID(model.getRcxwlbdldm());
		//保存审核流程
		result = shlc.runSubmit(model.getId(), model.getSplc(),model.getXh(),"rcsw_rcxwwh_rcxwsh.do","rcsw_rcxwwh_rcxwxxwh.do");
		}
		if(resultRcww&&!isSplc){//无需审核直接进结果库
			if(Globals.XXDM_TJJM.equals(Base.xxdm)){
    			model.setShzt("1");
    			dao.runUpdate(model);
    		}
			RcxwjgForm rcxwjgForm = new RcxwjgForm();
    		RcxwjgService rcxwjgService = new RcxwjgService();
    		RcxwxxwhForm myForm=dao.getModel(model);
    		myForm.setRcxwlbdldm(model.getRcxwlbdldm());
    		BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(myForm));
    		rcxwjgForm.setRcxwxxid(model.getId());
    		//rcxwjgForm.setId(guid);
    		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
    			rcxwjgForm.setSjly("1");
    		}
    		result=rcxwjgService.runInsert(rcxwjgForm);
		}
		return result;
	}
	
	
	public boolean updateRcww(RcxwxxwhForm model,boolean checkShzt) throws Exception {
		
		
		if(checkShzt){
			if(model.getType().equals("submit")){
				model.setShzt(Constants.YW_SHZ);//审核中
			}
		}else{
			if(model.getType().equals("submit")){
				model.setShzt("");//无需审核
			}
		}
		
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
		}
		boolean insertResult = super.runUpdate(model);
		boolean result = true;
		if (checkShzt && insertResult && model.getType().equals("submit")) {
			//shlc.deleteShjl(model.getId());
			//result = shlc.runSubmit(model.getId(), splc);
			result = shlc.runSubmit(model.getId(), model.getSplc(),model.getXh(),"rcsw_rcxwwh_rcxwsh.do","rcsw_rcxwwh_rcxwxxwh.do");
		}
		return insertResult && result;
	}
	
	
	/**
	 * 
	 * @描述:在删除日常行为结果同时删除日常行为维护
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午04:30:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delRcxwwhFromRcxwjg(String[] values) throws Exception {
		if (values == null || values.length == 0) {
			return false;
		}
		int num = dao.delRcxwwhFromRcxwjg(values);
		return num > 0;
	}
	
	/**
	 * 
	 * @描述:检查审批流
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午04:30:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkForSplc(RcxwxxwhForm model) throws Exception {
		boolean result = false;
		String splc = dao.checkForSplc(model.getRcxwlbdldm());
		if(!"无需审核".equalsIgnoreCase(splc)){
			result = true;
		}
		return result;
	}
	
	
	// 删除操作
	public int runDeleteXwxx(String[] values) throws Exception {
		// 删除行为记录
		int delNum = dao.delXwxx(values);
		if (delNum >0){
			// 删除审核流
			for(String sqid: values){
				// 删除原审核流
				shlc.deleteShjl(sqid);
			}
		}
		return delNum;
	}
	
	/**
	 * 
	 * @描述:查询获取一条行为维护数据
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 下午04:29:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneXwxxList(String  xwjgId) throws Exception {
		return dao.getOneXwxxList(xwjgId);
	}
	
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-3 上午09:18:51
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	
   public boolean updateRcxwModel(RcxwxxwhForm model) throws Exception {
		boolean resultRcww = dao.updateRcxwxxwh(model.getId(),model.getSplc(),model.getShzt());
		return resultRcww;
	}
	
   /**
	 * @描述: 查询获取行为类别信息
	 * @作者：HongLin [工号：707]
	 * @日期：2014-2-21 上午10:50:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXwlbxx(HttpServletRequest request,String lbdm) {
		return dao.getXwlbxx(request,lbdm);
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
	
	
}
