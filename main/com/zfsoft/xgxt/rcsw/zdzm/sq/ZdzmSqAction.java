/**
 * @部门:学工产品事业部
 * @日期：2014-3-4 下午04:18:09 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.PrintService;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszForm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-4 下午04:18:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmSqAction extends SuperAction {
	
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @描述 在读证明申请服务
	 */
	private ZdzmSqService service = new ZdzmSqService();
	
	/**
	 * @描述 在读证明参数设置服务
	 */
	private ZdzmCsszService csszService = new ZdzmCsszService();
	
	/**
	 * @描述 学生显示信息表单服务
	 */
	private BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 审核流操作接口
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @描述 ：初始化学生信息表单参数列表
	 */
	public ZdzmSqAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(ZdzmComm.ZDZM_BDID);
	}

	private static final String url = ZdzmComm.PATH_SQ;
	
	/**
	 * 
	 * @描述:查询在读证明申请列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 下午04:44:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward queryZdzmSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		ZdzmCsszService csszService = new ZdzmCsszService();			
		ZdzmCsszForm csszModel = csszService.getCssz();
		request.setAttribute("csszModel", csszModel);
		
		request.setAttribute("path", ZdzmComm.PATH_SQ);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryZdzmSqList");
	}
	
	/**
	 * 
	 * @描述:新增在读证明
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-5 下午03:34:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addZdzmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if(!StringUtil.isNull(model.getXh())){
			
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		
		String path = "rcsw_zdzm_sqgl.do?method=addZdzmSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		this.saveToken(request);
		return mapping.findForward("addZdzmSq");
	}
	/**
	 * 
	 * @描述:保存新增申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午09:09:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在读证明办理-在读证明申请-增加XH:{xh},SQLY:{sqly}")
	public ActionForward addZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		ZdzmSqForm model  = (ZdzmSqForm) form;
		JSONObject message = null; 
		ZdzmCsszForm csszModel = csszService.getCssz(); 
		/*********************！！！！！各位开发者注意！！！！！*****************************/
		/**这里的代码在可读性方面比较差，***************************************************/
		/**希望别的同学在写代码时候尽量能够把各个独立的模块单独抽取出来，************************/
		/**让代码逻辑简单清晰，为后续维护同学提供方便。***************************************/
		/**并且需要添加必要注释！**********************************************************/
		/*********************！！！！！各位开发者注意！！！！！！***************************/
		if(null == csszModel) /*--------------------------------------->>注释1：如果参数设置未设定，学生不能申请，并提示如下信息！*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_NOT_EXIST);
		else if(StringUtils.equals(csszModel.getKsqkg(), "0"))/*-------->>注释2：如果参数设置已设定，但开关为关闭状态，学生不能申请，并提示如下信息！*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_SQKG_CLOSE);
		else if(StringUtils.equals(csszModel.getKsqkg() , "1")){
			String curDate = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();/*-->>注释2.1：获取当前时间*/
			String ksqkssj = csszModel.getKsqkssj();/*-------->>注释2.2：获取参数设置可申请开始时间！*/
			String ksqjssj = csszModel.getKsqjssj();/*-------->>注释2.3：获取参数设置可申请结束时间！*/
			model.setSqsj(curDate);
			if((!StringUtils.isBlank(ksqkssj) && curDate.compareTo(ksqkssj) < 0 ) ||(!StringUtils.isBlank(ksqjssj)&&curDate.compareTo(ksqjssj) > 0))/*-------->>注释2.4：如果当前时间不在参数设置时间范围内，则不允许学生申请！*/
				message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_SJ_NOT_IN_SZ);
			else{
				super.resetToken(request);															 	/*-------->>注释2.5：反之则允许！*/
				if(StringUtils.equals(SUBMIT, model.getType())){				/*-------->>注释2.5.1：如果是提交，调用doSubmitAction*/
					message = doAddSubmitAction(model, csszModel.getSplid());
				}else if(StringUtils.equals(SAVE, model.getType())){			/*-------->>注释2.5.2：如果是保存，调用doSaveAction*/
					message = doSaveAction(model, csszModel.getSplid());
				}
			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * @描述 处理在读证明的新增操作
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doSaveAction(ZdzmSqForm model , String splid) throws Exception{
		boolean isSuccess = true;
		model.setShzt(Constants.YW_WTJ);
		model.setSplcid(splid);
		isSuccess = service.saveZdzmSq(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @描述 处理在读证明的更新操作
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doUpdateAction(ZdzmSqForm model) throws Exception{
		boolean isSuccess = service.updateZdzmSq(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @描述 处理在读证明的新增提交操作
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doAddSubmitAction(ZdzmSqForm model , String splid) throws Exception{
		boolean isSuccess = false;
		JSONObject message = null;
		List inShlSq = service.getZdzmInShlcByXh(model.getXh());
		if(null != inShlSq && inShlSq.size() >= 1){
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_ALREADY_IN_SHL);
		}else{
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setShzt(Constants.YW_WTJ);
			model.setSplcid(splid);
			model.setZdzmsqid(uuid);
			isSuccess = service.saveZdzmSq(model);
			if(isSuccess){
				isSuccess = shlc.runSubmit(uuid, splid, model.getXh(), ZdzmComm.PATH_SH, ZdzmComm.PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					service.updateZdzmSq(model);	//修改为审核中状态
					String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					message = getJsonMessageByKey(messageKey);
				}
			}
		}
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @描述 处理在读证明的更新提交操作
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doUpdateSubmitAction(ZdzmSqForm model ) throws Exception{
		boolean isSuccess = false;
		JSONObject message = null;
		List inShlSq = service.getZdzmInShlcByXh(model.getXh());
		if(null != inShlSq && inShlSq.size() >= 1){
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_ALREADY_IN_SHL);
		}else{
			ZdzmCsszForm csszModel = csszService.getCssz(); 
			if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt()))
				model.setSplcid(csszModel.getSplid());
			isSuccess = service.updateZdzmSq(model);
			if(isSuccess){
				isSuccess = shlc.runSubmit(model.getZdzmsqid(), model.getSplcid(), model.getXh(), ZdzmComm.PATH_SH, ZdzmComm.PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					service.updateZdzmSq(model);	//修改为审核中状态
					String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					message = getJsonMessageByKey(messageKey);
				}
			}
		}
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @描述 处理在读证明的新增提交操作
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doSubmitAction(ZdzmSqForm model , String splid) throws Exception{
		boolean isSuccess = false;
		JSONObject message = null;
		List inShlSq = service.getZdzmInShlcByXh(model.getXh());
		if(null != inShlSq && inShlSq.size() >= 1){
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_ALREADY_IN_SHL);
		}else{
			isSuccess = service.updateZdzmSq(model);
			if(isSuccess){
				isSuccess = shlc.runSubmit(model.getZdzmsqid(), splid, model.getXh(), ZdzmComm.PATH_SH, ZdzmComm.PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					service.updateZdzmSq(model);	//修改为审核中状态
					String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					message = getJsonMessageByKey(messageKey);
				}
			}
		}
		return message;
	}
	
	/**
	 * 
	 * @描述:修改在读证明
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 下午02:18:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZdzmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		if(!StringUtil.isNull(model.getZdzmsqid())){
			ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		
		ZdzmCsszService csszService = new ZdzmCsszService();			
		ZdzmCsszForm csszModel = csszService.getCssz();
		request.setAttribute("csszModel", csszModel);
		
		String path = "rcsw_zdzm_sqgl.do?method=updateZdzmSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateZdzmSq");
	}
	
	/**
	 * 
	 * @描述:更新在读 申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 上午09:09:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在读证明办理-在读证明申请-修改ZDZMSQID:{zdzmsqid},XH:{xh},SQLY:{sqly}")
	public ActionForward updateZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		JSONObject message = null; 
		ZdzmCsszForm csszModel = csszService.getCssz(); 
		if(null == csszModel) /*--------------------------------------->>注释1：如果参数设置未设定，学生不能申请，并提示如下信息！*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_NOT_EXIST);
		else if(StringUtils.equals(csszModel.getKsqkg(), "0") && StringUtils.equals(SUBMIT, model.getType())&& !StringUtils.equals(Constants.YW_YTH, model.getShzt()))/*-------->>注释2：如果参数设置已设定，但开关为关闭状态并且申请状态不等于退回，学生不能申请，并提示如下信息！*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_SQKG_CLOSE);
//		else if(StringUtils.equals(csszModel.getKsqkg() , "1")){
		else{
			//如果当前时间不在参数设置时间范围内，则不允许学生申请！
			if(!DateUtils.betweenTime(csszModel.getKsqkssj(), csszModel.getKsqjssj())){
				message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_SJ_NOT_IN_SZ);
			}else{
				if(StringUtils.equals(SUBMIT, model.getType())){
					message = doUpdateSubmitAction(model);
				}else if(StringUtils.equals(SAVE, model.getType())){
					message =doUpdateAction(model);
				}
			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:删除在读证明申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 下午03:52:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在读证明办理-在读证明申请-删除SQIDS:{sqids}")
	public ActionForward deleteZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sqids = request.getParameter("sqids"); //带删除的sqids
		
		int isSuccess = service.deleteZdzmSqs(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:撤销在读证明申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 下午05:50:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在读证明办理-在读证明申请-撤销ZDZMSQID:{zdzmsqid}")
	public ActionForward cancelZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		
		boolean isSuccess = shlc.firstStepCancle(model.getZdzmsqid(), model.getSplcid());
		
		ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());

		if(isSuccess){
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getZdzmsqid()))>0){
				dataModel.setShzt(Constants.YW_YTH);
			}else{
				dataModel.setShzt(Constants.YW_WTJ);
			}
			
			isSuccess = service.runUpdate(dataModel);
		}
		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:提交在读证明申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 上午08:45:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request TODO
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在读证明办理-在读证明申请-提交ZDZMSQID:{zdzmsqid}")
	public ActionForward submitZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		String message = null; 
		ZdzmCsszForm csszModel = csszService.getCssz();
		String curDate = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();/*-->>注释2.1：获取当前时间*/
		String ksqkssj = csszModel.getKsqkssj();/*-------->>注释2.2：获取参数设置可申请开始时间！*/
		String ksqjssj = csszModel.getKsqjssj();/*-------->>注释2.3：获取参数设置可申请结束时间！*/
		
		if(null == csszModel) /*--------------------------------------->>注释1：如果参数设置未设定，学生不能申请，并提示如下信息！*/
			message = MessageUtil.getText(MessageKey.RCSW_ZDZM_CSSZ_NOT_EXIST);
		else if(StringUtils.equals(csszModel.getKsqkg(), "0")&& !StringUtils.equals(Constants.YW_YTH, model.getShzt()))/*-------->>注释2：如果参数设置已设定，但开关为关闭状态并且申请状态非退回，学生不能申请，并提示如下信息！*/
			message = MessageUtil.getText(MessageKey.RCSW_ZDZM_CSSZ_SQKG_CLOSE);
		else if((curDate.compareTo(ksqkssj) < 0 || curDate.compareTo(ksqjssj) > 0) && !StringUtils.equals(Constants.YW_YTH, model.getShzt()))/*-------->>注释3：如果当前时间不在参数设置时间范围内，则不允许学生申请！*/
			message = MessageUtil.getText(MessageKey.RCSW_ZDZM_SJ_NOT_IN_SZ);
		else{
			if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt()))
				model.setSplcid(csszModel.getSplid());
			boolean isSuccess = shlc.runSubmit(model.getZdzmsqid(), model.getSplcid(), model.getXh(), "rcsw_zdzmsh.do", "rcsw_zdzmsq.do");
			if(isSuccess){
				ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());
				
				dataModel.setSplcid(model.getSplcid());
				dataModel.setShzt(Constants.YW_SHZ);
				isSuccess = service.runUpdate(dataModel);
			}
			 message =isSuccess ? MessageUtil.getText(
					MessageKey.SYS_SUBMIT_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_SUBMIT_FAIL);
		}
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:查看在读证明申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 上午09:11:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewZdzmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		if(!StringUtil.isNull(model.getZdzmsqid())){
			ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());
			BeanUtils.copyProperties(model, dataModel);
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		
		String path = "rcsw_zdzm_sqgl.do?method=viewZdzmSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewZdzmSq");
	}
	
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号:1036]
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xhs = request.getParameter("xhs");
		String pyccmc = request.getParameter("pyccmc");
		HashMap<String,String> xzModel = new HashMap<String, String>();
		if(xhs != null){
			xzModel.put("ts", xhs.split(",").length + "");
			xzModel.put("pyccmc", pyccmc);
			xzModel.put("xhs", xhs);
			request.setAttribute("xzModel", xzModel);
			request.setAttribute("zdzmlbList", new PrintService().getXzlbList());
		}
		
		return mapping.findForward("print");
	}
	
	
	/**
	 * 下载表格
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		String zdzmsqids = request.getParameter("xhs");
		if(zdzmsqids != null && zdzmsqids.split(",").length == 1){	/*-->下载单个表格*/
			HashMap<String , String> data = new PrintService().getData(zdzmsqids);
			if("11488".equals(Base.xxdm)){
				String xymc=data.get("xymc");
				data.put("xymc", xymc.replace("学院", ""));
			}
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			objectData.putAll(data);
			File file = null;
			String guid = "rcsw_zdzm";
			if(Base.xxdm.equals(Globals.XXDM_ZYMZDX)){ /*-->中央民族大学个性化*/
				String pyccmc = data.get("pyccmc");
				if(StringUtils.equals("本科",pyccmc)){
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_cn_10052"; 
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_en_10052";
				}else if(StringUtils.equals("预升本",pyccmc)){
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_ysb_cn_10052";
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_ysb_en_10052";
				}else{
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_cn_10052";
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_en_10052";
				}
			}else if("11488".equals(Base.xxdm)){
				guid="rcsw_zdzm_11488";
			}
			objectData.put("xxmc", Base.xxmc);
			file = BbdmUtils.getSigleFile(guid, objectData);
			FileUtil.outputWord(response, file);
		}else{/*-->下载多个表格 ZIP打包*/
			List<File> files = new ArrayList<File>();
			for(String zdzmsqid:zdzmsqids.split(",")){
				HashMap<String , String> data = new PrintService().getData(zdzmsqid);
				if("11488".equals(Base.xxdm)){
					String xymc=data.get("xymc");
					data.put("xymc", xymc.replace("学院", ""));
				}
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				objectData.putAll(data);
				File file=null;
				String guid = "rcsw_zdzm";
				if(Base.xxdm.equals(Globals.XXDM_ZYMZDX)){/*-->中央民族大学个性化*/
					String pyccmc = data.get("pyccmc");
					if(StringUtils.equals("本科",pyccmc)){
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_en_10052";
					}else if(StringUtils.equals("预升本",pyccmc)){
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_ysb_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_ysb_en_10052";
					}else{
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_en_10052";
					}
				}else if("11488".equals(Base.xxdm)){
					guid="rcsw_zdzm_11488";
				}
				objectData.put("xxmc", Base.xxmc);
				file = BbdmUtils.getSigleFile(guid, objectData);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

}
