/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjgz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcDAO;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:03:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjgzAction extends SuperAction {

	private static final String url = "qjgzbase.do";
	
	/**
	 * 
	 * @描述:请假管理列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		CommService cs = new CommService();
		QjgzForm myForm = (QjgzForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("Qjlxwh.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String userXy = "";
		String userXymc = "";
		if("xx".equals(user.getUserStatus())){
			userXy = "qx";
			userXymc = "全校";
		}else{
			userXy = user.getUserDep();
			userXymc = user.getUserDepName();
		}
		request.setAttribute("userXy",userXy);
		request.setAttribute("userXymc",userXymc);
		String path = "qjgzbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qjgzlb");
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description="访问日常事务-请假管理-请假规则维护-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述: 请假管理模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description="访问日常事务-请假管理-请假规则维护-删除QJGZID:{qjgzid},KSSJ:{kssj},JSSJ:{jssj},SPLCID:{splcid}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		QjgzForm myForm = (QjgzForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String, String> map = service.getInfo(myForm);
		request.setAttribute("ssxymc",map.get("ssxymc"));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(map));
		//获取日常事务审核流
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("qjlxList", service.getQjlxList());
		return mapping.findForward("qjgzxg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-请假管理-请假规则维护-增加KSSJ:{kssj},JSSJ:{jssj},SPLCID:{splcid}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		QjgzForm myForm = (QjgzForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String ssxy = "xx".equals(user.getUserStatus()) ? "qx":user.getUserDep();
			myForm.setSsxydm(ssxy);
			boolean result = service.save(myForm);

			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String userXy = "";
		String userXymc = "";
		if("xx".equals(user.getUserStatus())){
			userXy = "qx";
			userXymc = "全校";
		}else{
			userXy = user.getUserDep();
			userXymc = user.getUserDepName();
		}
		request.setAttribute("ssxydm",userXy);
		request.setAttribute("ssxymc",userXymc);
		XtwhShlcService shlcService = new XtwhShlcService();
		//获取日常事务审核流
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("qjlxList", service.getQjlxList());
		return mapping.findForward("qjgzzj");
	}
	/**
	 * 
	 * @描述:是否可以增加
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:24:41
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
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjgzService service = new QjgzService();
		QjgzForm myForm = (QjgzForm) form;
		Map<String, String> map = new HashMap<String, String>();
		
		// 是否和其他区间冲突
		String result = service.getClash(myForm);
		if (StringUtils.isNotNull(result)) {//有冲突
			map.put("success", "false");
			map.put("message", result);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:查看请假规则，分 
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-30 下午04:46:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward ckQjgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QjgzForm myForm = (QjgzForm) form;
		HashMap<String,String> map = new QjgzService().getInfo(myForm);
		String qjlxmc = new QjlxService().getModel(map.get("qjlxid")).getQjlxmc();
		String shlcmc = new XtwhShlcDAO().getShlcMap(map.get("splcid")).get("lcxx");
		request.setAttribute("rs", map);
		request.setAttribute("qjlxmc", qjlxmc);
		request.setAttribute("shlcmc", shlcmc);
		return mapping.findForward("qjgzck");
		
			
	}
	
	/**
	 * 
	 * @描述: 开启状态
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-19 上午09:38:20
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
	public ActionForward openZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjgzForm myForm = (QjgzForm) form;
		QjgzForm model = new QjgzService().getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		String qjlxmc;
		try {
			qjlxmc = new QjlxService().getModel(myForm.getQjlxid()).getQjlxmc();
			request.setAttribute("qjlxmc", qjlxmc);
		} catch (NullPointerException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String shlcmc = new XtwhShlcDAO().getShlcMap(myForm.getSplcid()).get("lcxx");
		request.setAttribute("shlcmc", shlcmc);
		return mapping.findForward("openZt");
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-6-19 上午10:31:19
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
	public ActionForward saveOpenZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjgzForm myForm = (QjgzForm) form;
		boolean rs = new QjgzService().runUpdate(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
