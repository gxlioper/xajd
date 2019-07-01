/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 下午04:19:25 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsq;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.wjcf.cfsh.CfshService;
import com.zfsoft.xgxt.wjcf.cfsssq.CfsssqForm;
import com.zfsoft.xgxt.wjcf.cfsssq.CfsssqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分解除申请管理) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-30 下午04:19:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjcsqAction extends SuperAction {

	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	
	private static final String url = "wjcf_cfjcsq.do?method=cxCfjcsqList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfjcsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcsqForm model=(CfjcsqForm)form;
		CfjcsqService service=new CfjcsqService();
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
		
		String path = "wjcf_cfjcsq.do?method=cxCfjcsqList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxCfjcsqList");
	}
	
	/**
	 * 
	 * @描述:(申请解除)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-30 下午05:46:11
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
	public ActionForward cfjcsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcsqForm model=(CfjcsqForm)form;
		@SuppressWarnings("unused")
		CfjcsqService service=new CfjcsqService();
		
		CfshService cfshService=new CfshService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		//违纪信息
		CfshForm cfshForm = new CfshForm();
		cfshForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(cfshForm);
		request.setAttribute("map", StringUtils.formatData(map));
		this.saveToken(request);
		return mapping.findForward("cfjcsqAdd");
	}
	
	/**
	 * 
	 * @描述:(解除保存)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-29 下午04:37:34
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
	@SystemLog(description = "访问违纪处分-处分撤销管理-处分撤销-增加CFID:{cfid},XH:{xh},SQLY:{sqly}")
	public ActionForward cfjcsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		CfjcsqForm model=(CfjcsqForm)form;
		CfjcsqService service=new CfjcsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		String result=service.save(model);
		
		String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
				: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:(解除申请修改 )
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-29 下午04:37:34
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
	public ActionForward cfjcsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcsqForm model=(CfjcsqForm)form;
		CfjcsqService service=new CfjcsqService();
		CfshService cfshService=new CfshService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		//违纪信息
		CfshForm cfshForm = new CfshForm();
		cfshForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(cfshForm);
		request.setAttribute("map", StringUtils.formatData(map));
		
		//申诉信息
		CfjcsqForm myForm=service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("cfjcsqUpdate");
	}
	
	/**
	 * 
	 * @描述:(解除修改保存 )
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-29 下午04:37:34
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
	@SystemLog(description = "访问违纪处分-处分撤销管理-处分撤销-修改JCID:{jcid},CFID:{cfid},XH:{xh},SQLY:{sqly}")
	public ActionForward cfjcsqUpdateSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcsqForm model=(CfjcsqForm)form;
		CfjcsqService service=new CfjcsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(SAVE.equalsIgnoreCase(model.getType())){
			boolean result=service.runUpdate(model);
			String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			//修改页面提交
			String result=service.cfjcsqUpdateSave(model);
			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result)?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:(解除申请删除 )
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-29 下午04:37:34
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
	@SystemLog(description = "访问违纪处分-处分撤销管理-处分撤销-删除VALUES:{values}")
	public ActionForward cfjcsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcsqService service=new CfjcsqService();
		
		String[] ids=request.getParameter("values").split(",");
		int num = service.runDelete(ids);
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelCfjcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		CfjcsqService service=new CfjcsqService();
		String jcid = request.getParameter("values");
		String splcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(jcid,splcid);
		if(result){
			//更新业务状态为'未提交'
			CfjcsqForm model = new CfjcsqForm();
			model.setJcid(jcid);
			model.setSplcid(splcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(jcid))>0){
				model.setSqjg(Constants.YW_YTH);
			}else{
				model.setSqjg(Constants.YW_WTJ);
			}
			service.updateRecord(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitCfjcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcsqForm model = (CfjcsqForm) form;
		CfjcsqService service = new CfjcsqService();
		String jcid = request.getParameter("values");
		String xh = request.getParameter("xh");

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		String splcid = request.getParameter("splcid");
		model.setJcid(jcid);
		model.setXh(xh);
		
		CfjcsqForm myModel = service.getModel(jcid);
		// 已退回
		if(Constants.YW_YTH.equals(myModel.getSqjg())){
			splcid = myModel.getSplcid();
		}else{
			CfsssqService sqservice = new CfsssqService();
			//获取申诉解除流程
			HashMap<String, String> ssjcsplc = sqservice.getSsjcsplc();
			splcid = ssjcsplc.get("jcspl");
		}
		//审批流程为空，提示显示设置审批流程
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SELECT_SHLC_FAIL));			
			return null;
		}
		
		model.setSplcid(splcid);
		model.setSqjg(Constants.YW_SHZ);
		boolean result = service.submitCfjcsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
		
	
	/**
	 * 
	 * @描述:TODO(打印解除处分通知书)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-14 上午09:00:47
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
	public ActionForward getJccfwjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfjcsqForm myForm = (CfjcsqForm) form;
		
		File wordFile = getJccfwjbWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getJccfwjbWord(CfjcsqForm myForm,HttpServletRequest request) throws Exception{
		CfjcsqService service = new CfjcsqService();
		
		//查询处分撤销下载信息
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.jccfwjxx(myForm.getCfid());
		data.put("xh",cfData.get("xh"));
		data.put("jcsj",DateTranCnDate.fomartDateToCn(cfData.get("jcsj"),FomartDateType.day));
		data.put("cflbmc",cfData.get("cflbmc"));
		data.put("cfsj",DateTranCnDate.fomartDateToCn(cfData.get("cfsj"),FomartDateType.day));
		data.put("cfyymc",cfData.get("cfyymc"));
		data.put("xm",cfData.get("xm"));
		data.put("xb",cfData.get("xb"));
		data.put("xymc",cfData.get("xymc"));
		data.put("bjmc",cfData.get("bjmc"));
		data.put("dysj",DateTranCnDate.fomartDateToCn(cfData.get("dysj"),FomartDateType.day));
				
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf",
				"jccfwj_10606.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;
		
	}

}
