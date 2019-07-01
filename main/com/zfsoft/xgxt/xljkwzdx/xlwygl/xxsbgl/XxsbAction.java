/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 下午03:22:29 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz.JcszForm;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz.JcszService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl.XssqService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl.XxsbjgService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl.ZbrcService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 下午03:22:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbAction extends SuperAction {

	private XxsbService service = new XxsbService();
	/**
	 * @描述 学生显示信息表单服务
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * 学生授权service
	 */
	private XssqService xssqService = new XssqService();
	/**
	 *  @属性： PATH 路径
	 */
	public static final String PATH = "xljk_xlwygl_xxsbgl.do";
	
	public static final String url = "xljk_xlwygl_xxsbgl.do";
	
	public static final String PATH_SH = "xljk_xlwygl_xxsbshgl.do";

	/**
	 * 周报日程service
	 */
	private ZbrcService zbrcService = new ZbrcService();
	
	/**
	 * 基础设置
	 */
	private JcszService jcszService = new JcszService();
	
	/**
	 * 结果service
	 */
	private XxsbjgService xxsbjgService = new XxsbjgService();
	
	/**
	 * @属性： CDGL_BBID 表单配置id
	 */
	private static final String BBID = "xlzxxxsbgl"; 
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward cx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();//该模块只允许学生访问
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//学生权限查询
		HashMap<String , String> xssqCheck = xssqService.xssqCheck(user.getUserName());
		if("N".equalsIgnoreCase(xssqCheck.get("bjxlwy")) && 
				"N".equalsIgnoreCase(xssqCheck.get("tsxs")) && 
				"N".equalsIgnoreCase(xssqCheck.get("gygly"))){
			String msg = "您没有该面页的访问权限！请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//
		request.setAttribute("xssqCheck", xssqCheck);
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("xqList", Base.getXqList());
		
		XxsbForm myForm = (XxsbForm) form;
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		User user = getUser(request);
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		if(StringUtil.isNull(model.getXn())) {
			model.setXn(Base.currXn);
		}
		if(StringUtil.isNull(model.getXq())) {
			model.setXq(Base.currXq);
		}
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:查看
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbsqid());
		//上报类型
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "平时信息上报");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		request.setAttribute("sbxxdata", xgxt.utils.String.StringUtils.formatData(sbxxdata));
		request.setAttribute("path", PATH);
		return mapping.findForward("ck");
	}
	
	
	
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		HashMap<String , String> sbxx = new HashMap<String, String>();
		//上报类型
		String sblx = model.getSblx();
		//上报周报id
		String sbzbid = model.getSbzbid();
		JcszForm jcszModel = jcszService.getJcsz();
		
		if(jcszModel == null){
			String msg = "基础配置不正确，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//周报信息
		if(sbzbid != null){
			HashMap<String , String> zbrcModel = zbrcService.getZbrcxx(sbzbid);
			sbxx.put("zbqzrq", zbrcModel.get("zbksrq") + " 至 " + zbrcModel.get("zbjsrq"));
			sbxx.putAll(zbrcModel);
		}
		//上报类型
		String splcid = jcszModel.selectSplc(sblx);;
		sbxx.put("sblx", sblx);
		if(StringUtils.equals("0", sblx)){
			sbxx.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxx.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxx.put("sblxmc", "平时信息上报");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		sbxx.put("splcid", splcid);
		request.setAttribute("sbxx", sbxx);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sb");
	}
	
	/**
	 * 
	 * @描述:保存
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:19:28
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
	public ActionForward saveAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		JSONObject message = new JSONObject();
		User user = getUser(request);
		//操作
		String actionType = model.getType();
		//获取审批流程
		JcszForm jcszModel = jcszService.getJcsz();
		String splc = jcszModel.selectSplc(model.getSblx());
		//检查审批流配置
		boolean checkSpl =StringUtils.isBlank(splc) ? false : true;
		
		if((StringUtils.equals(actionType, "submit")||StringUtils.equals(actionType, "saveSubmit")) && (!checkSpl)){
			message = new JSONObject();
			message.put("code", "-1");
			response.getWriter().print(message);
			return null;
		}else if((StringUtils.equals(actionType, "submit")||StringUtils.equals(actionType, "saveSubmit"))&& (checkSpl)){
			String sblx = model.getSblx();
			HashMap<String , String> xssqData = null;
			//如果是班级周报需要检查任职结束日期
			if("0".equals(sblx)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				xssqData = xssqService.getModelData(user.getUserName(), "0");
				String rzjsrq = xssqData.get("rzjsrq");
				if(StringUtils.isNotBlank(rzjsrq)){
					Date rzjsrqDate = df.parse(rzjsrq);
					Date curDate = df.parse(DateUtils.getCurrDate());
					if(curDate.getTime() > rzjsrqDate.getTime()){
						message = new JSONObject();
						message.put("code", "-2"); //表示任职结束日期已过，无法提交申请
						response.getWriter().print(message);
						return null;
					}
				}
				//平时上报需要检查是否需要平时上报字段
			}else if("2".equals(sblx)){
				xssqData = xssqService.getModelData(user.getUserName(), "1");
				String sfxypssb = xssqData.get("sfxypssb");
				if("0".equals(sfxypssb)){
					message = new JSONObject();
					message.put("code", "-3"); //无需上报
					response.getWriter().print(message);
					return null;
				}
			}
			
			model.setSplcid(splc);
		}
		
		model.setXh(user.getUserName());
		model.setSbsj(DateUtils.getCurrTime());
		model.setSplcid(splc);
		if("2".equals(model.getSblx())) {
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
		}else {
			HashMap<String , String> xnxqData = service.getXnXqmc(model.getSbzbid());
			model.setXn(xnxqData.get("xn"));
			model.setXq(xnxqData.get("xq"));
		}
		boolean isSuccess = service.saveXxsb(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		message.put("code", isSuccess ? "1" : "0");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		HashMap<String , String> sbxx = new HashMap<String, String>();
		//上报类型
		String sblx = model.getSblx();
		//上报周报id
		String sbsqid = model.getSbsqid();
		JcszForm jcszModel = jcszService.getJcsz();
		//周报信息
		if(sbsqid != null){
			XxsbForm data = service.getModel(sbsqid);
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(data));
			if(StringUtils.isNotBlank(model.getSbzbid())){}
			HashMap<String , String> zbrcModel = zbrcService.getZbrcxx(model.getSbzbid());
			sbxx.put("zbqzrq", zbrcModel.get("zbksrq") + " 至 " + zbrcModel.get("zbjsrq"));
			sbxx.putAll(zbrcModel);
		}
		//上报类型
		String splcid = jcszModel.selectSplc(sblx);;
		sbxx.put("sblx", sblx);
		if(StringUtils.equals("0", sblx)){
			sbxx.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxx.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxx.put("sblxmc", "平时信息上报");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		sbxx.put("splcid", splcid);
		request.setAttribute("sbxx", xgxt.utils.String.StringUtils.formatData(sbxx));
		return mapping.findForward("xg");
	}
	
	
	
	/**
	 * 
	 * @描述:提交
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 上午08:45:16
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
	public ActionForward submitAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		JSONObject message = new JSONObject();
		//基础设置model
		JcszForm jcszModel = jcszService.getJcsz();
		String splc = jcszModel.selectSplc(model.getSblx());
		if(StringUtils.isBlank(splc)){
			message = new JSONObject();
			message.put("code", "-1");
			response.getWriter().print(message);
			return null;
		}
		//设置审批流程
		if(StringUtils.isBlank(model.getSplcid())){
			model.setSplcid(splc);
		}
		
		/////////////////////////////////////////////////
		User user = getUser(request);
		String sblx = model.getSblx();
		HashMap<String , String> xssqData = null;
		//如果是班级周报需要检查任职结束日期
		if("0".equals(sblx)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			xssqData = xssqService.getModelData(user.getUserName(), "0");
			String rzjsrq = xssqData.get("rzjsrq");
			if(StringUtils.isNotBlank(rzjsrq)){
				Date rzjsrqDate = df.parse(rzjsrq);
				Date curDate = df.parse(DateUtils.getCurrDate());
				if(curDate.getTime() > rzjsrqDate.getTime()){
					message = new JSONObject();
					message.put("code", "-2"); //表示任职结束日期已过，无法提交申请
					response.getWriter().print(message);
					return null;
				}
			}
			
			//平时上报需要检查是否需要平时上报字段
		}else if("2".equals(sblx)){
			xssqData = xssqService.getModelData(user.getUserName(), "1");
			String sfxypssb = xssqData.get("sfxypssb");
			if("0".equals(sfxypssb)){
				message = new JSONObject();
				message.put("code", "-3"); //无需上报
				response.getWriter().print(message);
				return null;
			}
		}
		////////////////////////////////////
		
		boolean isSuccess = service.submitXxsb(model);
		String messageKey =isSuccess ? MessageUtil.getText(
				MessageKey.SYS_SUBMIT_SUCCESS) : MessageUtil
				.getText(MessageKey.SYS_SUBMIT_FAIL);
		message = getJsonMessage(messageKey);
		message.put("code", isSuccess ? "1" : "0");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:撤销
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
	public ActionForward cancelAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		
		boolean isSuccess = service.cancel(model.getSbsqid()); //撤销申请
		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	//----------------------------审核页面----------------------------------//
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = PATH_SH)
	public ActionForward shcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();//该模块只允许学生访问
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH_SH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shcx");
	}
	
	
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = PATH_SH)
	public ActionForward shquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		User user = getUser(request);
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//查询
		List<HashMap<String,String>> resultList = service.getSHPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午01:38:49
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		String sbsqid = model.getSbsqid();
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();

		HashMap<String , String> sbxxdata = service.getModelMap(sbsqid);
		//上报类型
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "平时信息上报");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		sbxxdata.put("xtgwid", xtgwid);
		sbxxdata.put("shid", shid);
		request.setAttribute("sbxxdata", sbxxdata);
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(sbxxdata.get("xh"));
		request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(BBID);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("sh");
	}
	
	
	/** 
	 * @描述:提交审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午02:14:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward shAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		User user = getUser(request);
		String messageKey ;
		//单个保存审核
		boolean result = service.saveSh(model,user);
		messageKey = result ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:撤销审核
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午03:22:24
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelCdshAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		XxsbForm dataModel = service.getModel(model.getSbsqid());
		dataModel.setShzt(Constants.YW_SHZ);
		boolean isSuccess = service.runUpdate(dataModel); //更新 Model
		if(isSuccess){
			isSuccess = xxsbjgService.deleteBySqid(dataModel.getSbsqid()); //删除结果表数据
		}
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:批量保存审核
	 * @作者：zhangxiaobin [工号：1036]
	 * @日期：2014-4-28 上午11:52:29
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("plsh");
	}
	
	/**
	 * 
	 * @描述:查看
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
	@SystemAuth(url = PATH_SH)
	public ActionForward shck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbsqid());
		//上报类型
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "班级心理周报");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "公寓心理周报");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "平时信息上报");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		request.setAttribute("sbxxdata", sbxxdata);
		request.setAttribute("path", PATH);
		return mapping.findForward("shck");
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-23 下午04:57:44
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelShAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XxsbForm model  = (XxsbForm) form;
		String sbsqid = request.getParameter("sbsqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSbsqid(sbsqid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
}
