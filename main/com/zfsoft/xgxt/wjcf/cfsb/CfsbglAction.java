/**
 * @部门:学工产品事业部
 * @日期：2013-10-22 上午11:32:01 
 */  
package com.zfsoft.xgxt.wjcf.cfsb;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqForm;
import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqService;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.wjcf.cfsh.CfshService;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhForm;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪处分管理模块
 * @类功能描述: (违纪处分上报管理) 
 * @作者：陈敏杰[工号:913]
 * @时间： 2013-10-22 上午11:32:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfsbglAction extends SuperAction {


	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	public static final String SUBMIT = "submit";
	
	private static final String url = "wjcf_cfsbgl.do?method=cxCfsbList";
	
	/**
	 * 
	 * @描述:TODO(查询处分上报管理列表)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-22 上午11:41:22
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
	public ActionForward cxCfsbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "wjcf_cfsbgl.do?method=cxCfsbList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxCfsbList");
	}
	
	

	/**
	 * 
	 * @描述:(处分上报增加)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
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
	public ActionForward cxCfsbAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			//已受违纪处分（全部记录）
			WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
			request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(model.getXh()));
			
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		//get student detail
		//学生基本信息
		String path = "wjcf_cfsbgl.do?method=cxCfsbAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//违纪处分原因
		User user = getUser(request);
		request.setAttribute("sbbxm", user.getRealName());
		this.saveToken(request);
		return mapping.findForward("cxCfsbAdd");
	}
	
	/**
	 * 
	 * @描述:(处分上报保存)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
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
	@SystemLog(description = "访问违处分-处分上报管理-处分上报-增加XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj}")
	public ActionForward cxCfsbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		User user = getUser(request);
		//设置上报人、学年、学期、上报时间
		model.setSbb(user.getUserName());
		model.setSbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		//设置原始发文权限，用于权限始终取到申报时处分类别所对应的权限
		String ffqx = new CflbdmwhService().getFwqxByLbdm(model.getCflbdm());
		model.setKzzd4(ffqx);
		
		String result = service.save(model);
		String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
				: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:(处分上报修改)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
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
	public ActionForward cxCfsbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm myForm = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		
		CfsbglForm model=service.getModel(myForm);
		//查询学生数据
		if(StringUtils.isNotNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//已受违纪处分（全部记录）
			WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
			request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(model.getXh()));
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		// 设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//违纪处分原因
		request.setAttribute("type", UPDATE);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("sbbxm", myForm.getSbbxm());
		return mapping.findForward("cxCfsbUpdate");
		
	}
	
	/**
	 * 
	 * @描述:(处分上报修改保存)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
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
	@SystemLog(description = "访问违处分-处分上报管理-处分上报-修改CFID:{cfid},XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj}")
	public ActionForward cxCfsbUpdateSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm myForm = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		
		//设置原始发文权限，用于权限始终取到申报时处分类别所对应的权限
		String ffqx = new CflbdmwhService().getFwqxByLbdm(myForm.getCflbdm());
		myForm.setKzzd4(ffqx);
		
		String result=service.updateSave(myForm);
		String messageKey = "";
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result)?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		}else{
				messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		}
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	
	}
	
	/**
	 * 
	 * @描述:(处分上报删除)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
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
	public ActionForward cxCfsbDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglService service = new CfsbglService();
		
		String values = request.getParameter("values");
		int num = service.runDelete(values.split(","));
		
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.WJCF_DEL_CFYSH);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 查看处分上报
	 */
	@SystemAuth(url = url)
	public ActionForward cfsbView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm=service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		//查询学生数据
		if(StringUtils.isNotNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		return mapping.findForward("cfsbView");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问违处分-处分上报管理-处分上报-删除VALUES:{values}")
	public ActionForward cfsbDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values=request.getParameter("values");
		String[] ids=values.split(",");
		CfsbglService service = new CfsbglService();
		int num = service.runDelete(ids);
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.WJCF_DEL_CFYSH);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:导出功能
	 * @作者：cmj
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm myForm = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO提交上报)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-4 下午05:09:39
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
	public ActionForward submitCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		String cfid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setCfid(cfid);
		model.setXh(xh);
		CfsbglForm modelGet = service.getModel(cfid);
		
		// 非退回记录重新取审核流程
		if(!Constants.YW_WTJ.equals(modelGet.getSbjg())){

			CflbdmwhForm myForm = new CflbdmwhForm();
			CflbdmwhService myservice = new CflbdmwhService();
			myForm.setCflbdm(modelGet.getCflbdm());
			myForm = myservice.getModel(myForm);
			model.setSplcid(myForm.getSpl());
		}else{
			model.setSplcid(modelGet.getSplcid());
		}
		boolean result = service.submitCfsb(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		CfsbglService service = new CfsbglService();
		String cfid = request.getParameter("values");
		String splcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(cfid,splcid);
		if(result){
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			//更新业务状态为'未提交'
			CfsbglForm model = new CfsbglForm();
			model.setCfid(cfid);
			model.setSplcid(splcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(cfid))>0){
				model.setSbjg(Constants.YW_YTH);
			}else{
				model.setSbjg(Constants.YW_WTJ);
			}
			service.updateCfsbModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:验证处分在上报和结果库当中是否存在 （验证条件：学号、处分类别、处分时间
	 * @作者：cq [工号：785]
	 * @日期：2014-5-14 下午03:46:20
	 * @日期：2014-5-14 下午03:46:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return true：存在；false:不存在
	 * @throws Exception 
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward checkExistCfsbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm = (CfsbglForm) form;
		// 取得是否存在验证
		boolean isExistCfsb = service.checkExistCfsbjg(myForm);
		response.getWriter().print(isExistCfsb);
		return null;
	}
	

	/**
	 * 
	 * @描述:验证处分在结果当中是否存在 （验证条件：学号、处分类别、处分时间
	 * @作者：cq [工号：785]
	 * @日期：2014-5-14 下午03:46:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return true：存在；false:不存在
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward checkExistCfjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm = (CfsbglForm) form;
		// 取得是否存在验证
		boolean isExistCfsb = service.checkExistCfjg(myForm);
		response.getWriter().print(isExistCfsb);
		return null;
	}
	/**
	 * 
	 * @描述:验证处分文号
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-29 下午06:54:51
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
	public ActionForward checkExistCfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm = (CfsbglForm) form;
		// 取得是否存在验证
		boolean isExistCfwh = service.checkExistCfwh(myForm);
		response.getWriter().print(isExistCfwh);
		return null;
	}
	
	
	/**
	 * 打印Word登记表
	 */
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglForm model = (CfsbglForm) form;
		String type = request.getParameter("type");
		String fwjg = request.getParameter("fwjg");
		File wordFile = getWord(model.getCfid(),fwjg, type);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 登记 表ZIP
	 */
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		String fwjgArray =request.getParameter("fwjgArray");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			String[] fwjgs = fwjgArray == null ? new String[values.length] : fwjgArray.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i],fwjgs[i], type);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	//填充模版数据生成word文件
	private File getWord(String id,String fwjg, String type) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		CfsbglService service = new CfsbglService();
		HashMap<String, String> wjcfxx =  service.getDjbModel(id);
		
		if (wjcfxx != null){
			wjcfxx.put("cfjc_sqly", HtmlUtil.xmlZy(wjcfxx.get("cfjc_sqly")));
			wjcfxx.put("cfss_sqly", HtmlUtil.xmlZy(wjcfxx.get("cfss_sqly")));
			wjcfxx.put("wjssjg", HtmlUtil.xmlZy(wjcfxx.get("wjssjg")));
			wjcfxx.put("cfyj", HtmlUtil.xmlZy(wjcfxx.get("cfyj")));
			wjcfxx.put("cfwh", HtmlUtil.xmlZy(wjcfxx.get("cfwh")));
			String xh = wjcfxx.get("xh");
			String cfsj = wjcfxx.get("cfsj");
			String cfsjny = ""; // 中文格式年月
			int cfsjNum = 0; // 处分后时间 至今已满X个月
			if(!StringUtil.isNull(cfsj)){
				cfsjny = DateTranCnDate.fomartDateToCn(cfsj,FomartDateType.day);
				Calendar calendar = Calendar.getInstance();
				int nowYear = calendar.get(Calendar.YEAR);
				int nowMonth = calendar.get(Calendar.MONTH);
				calendar.setTime(DateFormat.getDateInstance().parse(cfsj));
				nowYear = nowYear - calendar.get(Calendar.YEAR);
				nowMonth = nowMonth - calendar.get(Calendar.MONTH);
				int cfsjInt = nowYear * 12 + nowMonth;
				cfsjNum = cfsjInt < 0 ? 0 : cfsjInt;
			}
			wjcfxx.put("cfsjny", cfsjny);
			wjcfxx.put("wjsjny", DateTranCnDate.fomartDateToCn(wjcfxx.get("wjsj"),FomartDateType.day));
			wjcfxx.put("jcsjny", DateTranCnDate.fomartDateToCn(wjcfxx.get("jcsj"),FomartDateType.day));
			wjcfxx.put("cfsjNum", String.valueOf(cfsjNum));
			
			//基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//转换为中文日期格式
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
			// 担任职务
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			// 寝室
			WdgwsqService wdgwsqService = new WdgwsqService();
			HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
			String qsbh=null;
			if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
				qsbh="";
			}else{
				qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
			}
			data.put("qsbh", qsbh);
			
			//已受违纪处分（全部记录）
			WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
			List<HashMap<String, String>> yscfqkList = wjcfCfsbService.getYscfqk(xh);
			HashMap<String, String> yscfqkMap = new HashMap<String, String>();
			if(yscfqkList.size() > 0){
				yscfqkMap = yscfqkList.get(0);
			}
			data.put("yscfqkMap", StringUtils.formatData(yscfqkMap));
			data.put("yscfqkList", yscfqkList);
			//已受违纪处分（不包含本记录）
			List<HashMap<String, String>> yscfqkNotIdList = wjcfCfsbService.getYscfqkNotId(xh, id);
			data.put("yscfqkNotIdList", yscfqkNotIdList);
			if(yscfqkNotIdList.size() == 0){
				yscfqkNotIdList.add(new HashMap<String, String>());
			}
			//通用审核意见1-7级
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(id);
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			data.put("xsxx", xsjbxx);
			data.put("wjcfxx", wjcfxx);
			data.put("xxmc", Base.xxmc);
			String fileName = xh +"["+xsjbxx.get("xm")+"]" + "-";
			String mbmc = Base.xxdm + ".xml";
			// 南通工贸技师学院
			if("5002".equals(Base.xxdm)){
				if("wjcfsq".equals(type)){
					fileName += "学生处分申报表";
					mbmc = "wjcfsq_" + mbmc;
				}else if("wjcftzs".equals(type)){
					fileName += "学生违纪处分决定通知书";
					mbmc = "wjcftzs_" + mbmc;
				}else if("cfjcsq".equals(type)){
					fileName += "学生撤销处分申请表";
					mbmc = "cfjcsq_" + mbmc;
				}else if("cfjctzs".equals(type)){
					fileName += "学生撤销处分决定通知书";
					mbmc = "cfjctzs_" + mbmc;
				}
			}
			// 黑龙江农垦职业学院
			if("12727".equals(Base.xxdm)){
				String cfsj1 = wjcfxx.get("cfsj");
				String cfqx = wjcfxx.get("sfszcfqx")!=null&&wjcfxx.get("sfszcfqx").equals("1")?wjcfxx.get("cfqx"):null;
				if (cfsj1!=null) {
					if (cfsj1.length()==10) {
						data.put("cfn", cfsj1.substring(0,4));
						data.put("cfy", cfsj1.substring(5,7));
						data.put("cfr", cfsj1.substring(8,10));
						if (cfqx!=null) {
							String[] array=cfqx.split("-");
							int ts=0 ;
							if (Integer.parseInt(array[0])!=0)
								ts+=Integer.parseInt(array[0])*365;
							if (Integer.parseInt(array[1])!=0)
								ts+=Integer.parseInt(array[1])*30;
							if (Integer.parseInt(array[2])!=0) 
								ts+=Integer.parseInt(array[2]);
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
							Date date = dateFormat.parse(cfsj1); // 处分日期
						    Calendar cl = Calendar.getInstance();
						    cl.setTime(date);
						    cl.add(Calendar.DATE,ts);
						    String newdate = dateFormat.format(cl.getTime());
							data.put("jsn", newdate.substring(0,4));
							data.put("jsy", newdate.substring(5,7));
							data.put("jsr", newdate.substring(8,10));
						}else{
							data.put("jsn", "  ");
							data.put("jsy", "  ");
							data.put("jsr", "  ");
						}
					}
				}
				if("wjcftzs".equals(type)){
					fileName += "学生违纪处分决定书";
					mbmc = "wjcftzs_" + mbmc;
				}else if("wjcfqsd".equals(type)){
					fileName += "学生处分决定书签收单";
					mbmc = "wjcfqsd_" + mbmc;
				}else if("wjcfsq".equals(type)){
					fileName += "学生违纪处分审批表";
					String cflbmc = wjcfxx.get("cflbmc");
					if("留校察看".equals(cflbmc) || "开除学籍".equals(cflbmc)){
						mbmc = "wjcfsq_lxckjys_" + mbmc;
					}else{
						mbmc = "wjcfsq_jgjyx_" + mbmc;
					}
				}else if("cfjcsq".equals(type)){
					fileName += "学生解除处分审批表";
					mbmc = "cfjcsq_" + mbmc;
				}
			}
			// 重庆三峡医药高等专科学校
			if("14008".equals(Base.xxdm)){
				if("cfsssq".equals(type)){
					fileName += "学生申诉申请表";
					mbmc = "cfsssq_" + mbmc;
				}else if("cfjcsq".equals(type)){
					fileName += "学生处分撤销申请表";
					mbmc = "cfjcsq_" + mbmc;
				}
			}
			//苏州工业职业技术学院
			if("12686".equals(Base.xxdm)){
				if("cfjgdy".equals(type)){
					if("解除处分".equals(fwjg)){
					fileName += "解除处分决定书";
					mbmc = "jccfjds_" + mbmc;
					}else{
						fileName += "处分决定书";
						mbmc = "cfjds_" + mbmc;
					}
				}
			}
			if(Globals.XXDM_CDTYXY.equals(Base.xxdm)){
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String dyrq =  df.format(new java.util.Date());
				data.put("dyrq",DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				if("wjcfsq".equals(type)){
					fileName += "学生纪律处理登记表";
					mbmc = "wjsbsq_" + mbmc;
				}else if("wjjcsq".equals(type)){
					StringBuilder sql = new StringBuilder();
					for(int i=0;i<yscfqkNotIdList.size();i++){
						String cjcfsj = yscfqkNotIdList.get(i).get("cfsj");
						sql.append(cjcfsj==null?"":cjcfsj+":");
						String cjcf = yscfqkNotIdList.get(i).get("cflbmc");
						sql.append(cjcf==null?"":cjcf+"; \n ");
						
					}
					data.put("cjwjqk", sql.toString());
					fileName += "学生解除(撤销)处分登记表";
					mbmc = "wjjcsq_" + mbmc;
				}
				
			}
			//四川职业技术学院
			if("12970".equals(Base.xxdm)){
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String dyrq =  df.format(new java.util.Date());
				data.put("dyrq",DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				if("wjcfsq".equals(type)){
					fileName += "学生纪律处理登记表";
					mbmc = "wjsbsq_" + mbmc;
				}
			}
			//北京经管
			if("14073".equals(Base.xxdm)) {
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// 出生日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ".").replaceAll("日", ""));// 1988.9.9
				if("wjcfsq".equals(type)){
					fileName += "学生违纪处分登记表";
					mbmc = "wjcfdjb_" + mbmc;
				}else if("wjcfjds".equals(type)) {
					fileName += "学生违纪处分决定书";
					mbmc = "wjcfjds_" + mbmc;			
				}
			}
			
			//青岛酒店管理职业技术学院【经过2017年08月份需求，已经不用此判断了，判断在新的onclick方法里。2017年08月21日_孟威！】
			if("13011".equals(Base.xxdm)){
				if("13011".equals(type)){
					fileName += "处分决定书";
					mbmc = "cfjds_" + mbmc;
					String cfwh = wjcfxx.get("cfwh");
					if(cfwh.matches("^[0-9]{8}$")){
						wjcfxx.put("cfwh","〔"+cfwh.substring(0,4)+"〕"+cfwh.substring(4,8));
					}
				}
			}
			
			if("13871".equals(Base.xxdm)){
				if("wjcfjds".equals(type)){
					fileName += "学生违纪处分决定书";
					mbmc = "wjcfjds_" + mbmc;
				}
			}
			//徐州高等医科专科学校
			if("70002".equals(Base.xxdm)){
				if("wjcfsq".equals(type)){
					fileName += "违纪学生处分审批表";
					mbmc = "jsygzcfb_" + mbmc;
				}else if("cfjgdy".equals(type)){
					fileName += "撤销处分申请表";
					mbmc = "jsygzcx_" + mbmc;
				}
				
				HashMap<String, String> bzrxx = xsxxService.getBzrxxByXh(xh);
				data.put("bzr", bzrxx.get("bzrxx"));
			}
			//广西职业技术学院
			if("11773".equals(Base.xxdm)) {
				if("wjcfsq".equals(type)){
					fileName += "学生违纪调查处理申报表";
					mbmc = "wjdcclsbb_" + mbmc;
				}
			}
			fileName = fileName + "-" + wjcfxx.get("wjsj");
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			
			return wordFile;
		}
		
		return null;
	}
	
	/**
	 * @描述: 青岛酒店管理职业技术学院,处分决定书打印
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-17 下午03:27:22
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
	public ActionForward getCfjdsForQdjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CfsbglService service = new CfsbglService();
		/*获取勾选的ID*/
		String value = request.getParameter("ids");
		/*定义数组*/
		String [] ids = null;
		if(StringUtils.isNotNull(value)){
			ids = value.split(",");
		}
		/*根据ID获取处分信息,再传入获取文件的方法*/
		List<HashMap<String,String>> cfjdList = service.getCfjdXxByIds(ids);
		/*根据处分类别进行记录合并操作*/
		Map<String,List<HashMap<String,String>>> cfjdXxMap = service.getCfjdListMap(cfjdList);
		/*生成word文件数组*/
		File[] files = service.getCfjdsFiles(cfjdXxMap);
		
		if(files.length>1){
			File zipFile = ZipUtil.zip(files);
			FileUtil.outputZip(response, zipFile);
		}else{
			FileUtil.outputWord(response, files[0]);
		}
		return null;
	}


	/**
	 * 河北民族师范学院解除表zip
	 */
	public ActionForward getCfjcZip(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String value = request.getParameter("value");
		String xhs = request.getParameter("xhs");
		String jcids =request.getParameter("jcids");
		if (StringUtils.isNotNull(value)){
			String[] ids = value.split(",");
			String[] xh = xhs.split(",");
			String[] jcid = new String[ids.length];;
			if(jcids==null)
			{
				for (int i = 0; i <ids.length ; i++) {
					CfsbglService service = new CfsbglService();
					HashMap<String,String> jcidMap = service.getjcid(ids[i]);
					String jc = jcidMap.get("jcid");
					jcid[i]=jc;
				}

			}
			else{
			 jcid = jcids.split(",");
			}

			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = ids.length ; i < n ; i++){
				CfsbglForm model = (CfsbglForm) form;
				model.setCfid(ids[i]);
				model.setXh(xh[i]);
				File file = getcfjcWord(model,jcid[i]);
				files.add(file);
			}

			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}



	/**
	 *
	 * @描述: 打印Word(河北民族师范学院)
	 * @作者：Penghui.Qu
	 * @日期：2018-7-18 下午02:22:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getCfjcWord(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jcid = request.getParameter("jcid");
		CfsbglForm model = (CfsbglForm) form;
		if(jcid==null)
		{
			CfsbglService service = new CfsbglService();
			HashMap<String,String> jcidMap = service.getjcid(model.getCfid());
			jcid = jcidMap.get("jcid");
		}
		File wordFile = getcfjcWord(model,jcid);
		FileUtil.outputWord(response, wordFile);
		return null;

	}

	private File getcfjcWord(CfsbglForm model,String jcid) throws Exception {
		CfshService cfshService=new CfshService();
		CfjcsqService cfjcsqservice=new CfjcsqService();
		Map<String, Object> data = new HashMap<String, Object>();
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			// 基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.putAll(xsjbxx);

			//班级人数
			String bjdm = xsjbxx.get("bjdm");
			HashMap<String,String> bjrsMap = cfshService.getbjrs(bjdm);
			data.putAll(bjrsMap);
			//违纪信息
			CfshForm cfshForm = new CfshForm();
			cfshForm.setYwid(model.getCfid());
			HashMap<String, String> map=cfshService.getCfsbxxForjg(cfshForm);
			data.putAll(map);
			data.put("model",model);

			//解除理由
			CfjcsqForm cfjcsqForm = new CfjcsqForm();
			cfjcsqForm.setJcid(jcid);
			CfjcsqForm myForm=cfjcsqservice.getModel(cfjcsqForm);
			HashMap<String,String> sqlyMap = new HashMap<String, String>();
			sqlyMap.put("sqly",myForm.getSqly());
			data.putAll(sqlyMap);
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//wjcf", "jccfspb_10098.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}

		return null;
	}
}
