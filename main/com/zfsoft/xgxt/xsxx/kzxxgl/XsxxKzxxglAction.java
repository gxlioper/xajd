/**
 * @部门:学工产品事业部
 * @日期：2015-6-4 下午02:25:22 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.extend.model.ExtendModule;
import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-4 下午02:25:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

@SuppressWarnings("unchecked")
public class XsxxKzxxglAction extends SuperAction {
	
	private static final String DATA_EXTEND_MODULE = "XSXX";
	
	private ExtendService extendService = new ExtendService();
	
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	private ShlcInterface shlc = new CommShlcImpl(); //审核流操作接口
	
	private XsxxKzxxglService service = new XsxxKzxxglService();
	
	private XsxxKzxxglJgService jgService = new XsxxKzxxglJgService();
	
	private static final String url = "xsxx_kzxxgl_xslr.do";
	
	
	/**
	 * 
	 * @描述:查询申请列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-18 下午04:51:52
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
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XsxxKzxxglForm model = (XsxxKzxxglForm) form;
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
		String path = "xsxx_kzxxgl_xssqgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sqManage");
	}
	
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return null;
	}
	
	
	@SystemAuth(url = "xsxx_kzxxgl_cssz.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendModule queryExtentModule = extendService.queryExtentModule(DATA_EXTEND_MODULE);
		//List<ExtendModuleElement> queryExtendModuleElements = extendService.queryExtendModuleElements(DATA_EXTEND_MODULE);
		//request.setAttribute("extendModuleElements", queryExtendModuleElements);
		request.setAttribute("extendModule", queryExtentModule);
		request.setAttribute("path", "xsxx_kzxxgl_cssz.do");
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xsxx"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	/**
	 * 
	 * @描述: 保存参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-17 下午01:35:49
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
	
	public ActionForward csszAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String extendModuleID = request.getParameter("extendModuleID");
		String sfqy = request.getParameter("sfqy");
		String shlc = request.getParameter("shlc");
		String kssj = request.getParameter("kssj");
		String jssj = request.getParameter("jssj");
		String sfsh = request.getParameter("sfsh");
		String sjxz = request.getParameter("sjxz");
		ExtendModule module = new ExtendModule();
		module.setId(extendModuleID);
		module.setJssj(jssj);
		module.setKssj(kssj);
		module.setSfqy(sfqy);
		module.setSfsh(sfsh);
		module.setSjxz(sjxz);
		module.setSplc(shlc);
		boolean isSuccess = extendService.saveConfig(module);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:学生扩展信息填写页面
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-12 上午09:24:45
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
	public ActionForward xslr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		ExtendModule queryExtentModule = extendService.queryExtentModule(DATA_EXTEND_MODULE);
		String kssj = queryExtentModule.getKssj();
		String jssj = queryExtentModule.getJssj();
		String sfqy = queryExtentModule.getSfqy();
		String sfsh = queryExtentModule.getSfsh();
		String splc = queryExtentModule.getSplc();
		
		//1.判断该扩展表单是否使用
		request.setAttribute("sfqy", sfqy);
		//2.判断该扩展表单是否在设置时间内
		String yxqkz = "1";
		Date currentDate = new Date();
		Date beforDate = null;
		Date afterDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(kssj)){
			beforDate = sdf.parse(kssj);
			if(currentDate.compareTo(beforDate) < 0)
				yxqkz = "0";
		}
		if(StringUtils.isNotBlank(jssj)){
			afterDate = sdf.parse(jssj);
			if(currentDate.compareTo(afterDate) > 0)
				yxqkz = "0";
		}
		request.setAttribute("yxqkz", yxqkz);
		//3.判断审核流程配置信息
		String splsz = "1";
		if(StringUtils.equals("1", sfsh) && StringUtils.isBlank(splc)){
			splsz = "0";
		}
		request.setAttribute("splsz", splsz);
		
		if(StringUtils.equals("0", sfqy) || StringUtils.equals("0", yxqkz) || StringUtils.equals("0", splsz)){
			request.setAttribute("configError", "1");
		}
		String xh = user.getUserName();
		//获取学生扩展信息
		//1.如果该学生没有申请数据，学生可以申请新数据
		//2.如果该学生已经有数据正在审核中 学生不能再次申请新数据,但如果还未第一步审核，可以撤回
		//3.如果该学生的数据已经审核完成 学生可以申请新数据
		//4.如果学生的数据还未提交，学生可以修改之前的数据
		XsxxKzxxglForm oneSqListByXh = service.getOneSqListByXh(xh);
		if(oneSqListByXh!=null){
			request.setAttribute("sqid", oneSqListByXh.getSqid());
			request.setAttribute("splc", oneSqListByXh.getSplc());
			request.setAttribute("shzt", oneSqListByXh.getShzt());
		}
		request.setAttribute("xskzxxSqData", oneSqListByXh);
		request.setAttribute("xh", xh);
		request.setAttribute("dataExtendModule", DATA_EXTEND_MODULE);
		request.setAttribute("path", "xsxx_kzxxgl_xslr.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xslr");
	}
	
	
	/**
	 * 
	 * @描述:学生扩展信息查看页面
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-12 上午09:24:45
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
	@SystemAuth(url = "xsxx_kzxxgl_xsck.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xsck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String xh = user.getUserName();
		XsxxKzxxglJgForm modelByXh = jgService.getModelByXh(xh);
		if(modelByXh!=null){
			request.setAttribute("jgid", modelByXh.getJgid());
		}
		request.setAttribute("xh", xh);
		request.setAttribute("dataExtendModule", DATA_EXTEND_MODULE);
		request.setAttribute("path", "xsxx_kzxxgl_xsck.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsck");
	}
	
	
	/**
	 * 
	 * @描述: 保存数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-17 下午05:35:01
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
	
	public ActionForward xslrAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		XsxxKzxxglForm model = (XsxxKzxxglForm)form;
		String actionType = model.getActionType();
		String extendData = model.getExtendData();
		String xh = user.getUserName();
		String sqid = model.getSqid();
		String exendDateModuleId = model.getExendDateModuleId();
		ExtendModule extentModule = extendService.queryExtentModule(exendDateModuleId);
		String[] checkDataModuleConfig = extendService.checkDataModuleConfig(extentModule);
		boolean isSuccess = true;
		String messageKey = null;
		if(checkDataModuleConfig[0] == "0"){
			response.getWriter().print(getJsonMessageResult(checkDataModuleConfig[1], Boolean.FALSE));
			return null;
		}else{
			boolean isNewApply = false;
			
			XsxxKzxxglForm sqDateModel = null;
			if(StringUtils.isNotBlank(sqid)){
				sqDateModel = service.getModel(sqid);
			}

			//如果是新申请，之前数据被审核不通过，之前数据审核通过现在需要新申请，那么新生成ID
			if(StringUtils.isBlank(sqid) || sqDateModel == null || 
					StringUtils.equals(Constants.YW_BTG, sqDateModel.getShzt()) || 
					StringUtils.equals(Constants.YW_TG, sqDateModel.getShzt())){
				isNewApply = true;
				sqid = UniqID.getInstance().getUniqIDHash();
			}
			
			JSONArray sqdata = JSONObject.fromString(extendData).getJSONArray("data");
			
			/**
			 * 如果是提交操作
			 * 1. 无需审核： 临时表和正式表都插入数据，并且临时表的审核状态是审核通过
			 * 2. 如果需要审核： 临时表插入数据，状态为审核中
			 */
			if(StringUtils.equals("submit", actionType)){
				if(StringUtils.equals(extentModule.getSfsh(), "1")){
					if(isNewApply){
						sqDateModel = new XsxxKzxxglForm();
						sqDateModel.setSqid(sqid);
						sqDateModel.setShzt(Constants.YW_SHZ);
						sqDateModel.setSplc(extentModule.getSplc());
						sqDateModel.setSqid(sqid);
						sqDateModel.setSqsj(DateUtils.getCurrTime());
						sqDateModel.setXh(xh);
						isSuccess = service.runInsert(sqDateModel);
					}else{
						sqDateModel.setShzt(Constants.YW_SHZ);
						isSuccess = service.runUpdate(sqDateModel);
					}
					//临时表数据保存
					isSuccess = extendService.dataPersistentTemp(sqid, extentModule.getId(), sqdata);
					//提交审核流
					isSuccess = shlc.runSubmit(sqid, extentModule.getSplc(), xh, "xsxx_kzxxgl_sh.do", "xsxx_kzxxgl_sq.do");

				}else{
					if(isNewApply){
						sqDateModel = new XsxxKzxxglForm();
						sqDateModel.setSqid(sqid);
						sqDateModel.setShzt(Constants.YW_TG);
						sqDateModel.setSplc(extentModule.getSplc());
						sqDateModel.setSqid(sqid);
						sqDateModel.setSqsj(DateUtils.getCurrTime());
						sqDateModel.setXh(xh);
						isSuccess = service.runInsert(sqDateModel);
					}else{
						sqDateModel.setShzt(Constants.YW_TG);
						isSuccess = service.runUpdate(sqDateModel);
					}
					XsxxKzxxglJgForm jgModel = jgService.getModelByXh(xh);
					String jgid = null;
					if(jgModel != null){
						jgid = jgModel.getJgid();
						jgModel.setSqid(sqid);
						jgModel.setSjly("1");
						jgModel.setJrsj(DateUtils.getCurrTime());
						isSuccess = jgService.runUpdate(jgModel);
					}else{
						jgModel = new XsxxKzxxglJgForm();
						jgid = UniqID.getInstance().getUniqIDHash();
						jgModel.setJgid(jgid);
						jgModel.setJrsj(DateUtils.getCurrTime());
						jgModel.setSjly("1");
						jgModel.setSqid(sqid);
						jgModel.setXh(xh);
						//结果表插入数据
						isSuccess = jgService.runInsert(jgModel);
					}
					//临时表数据保存
					isSuccess = extendService.dataPersistentTemp(sqid, extentModule.getId(), sqdata);
					//正式表数据保存
					isSuccess = extendService.dataPersistent(jgid, extentModule.getId(), sqdata);
				}
			}else if(StringUtils.equals("save", actionType)){
				if(isNewApply){
					sqDateModel = new XsxxKzxxglForm();
					sqDateModel.setSqid(sqid);
					sqDateModel.setShzt(Constants.YW_WTJ);
					sqDateModel.setSplc(extentModule.getSplc());
					sqDateModel.setSqid(sqid);
					sqDateModel.setSqsj(DateUtils.getCurrTime());
					sqDateModel.setXh(xh);
					isSuccess = service.runInsert(sqDateModel);
				}
				//临时表数据保存
				isSuccess = extendService.dataPersistentTemp(sqid, extentModule.getId(), sqdata);
			}
		}
		if(StringUtils.equals("submit", actionType)){
			messageKey = isSuccess ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}else{
			messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
