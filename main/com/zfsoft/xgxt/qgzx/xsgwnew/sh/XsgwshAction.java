/**
 * @部门:学工产品事业部
 * @日期：2016-6-6 上午09:37:37 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqDao;
import com.zfsoft.xgxt.qgzx.xsgwnew.sq.XsgwsqService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 学生岗位审核 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-6 上午09:37:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsgwshAction extends SuperAction<XsgwshnewForm, XsgwshService>{
	private XsgwshService service = new XsgwshService();
	private XsgwsqService sqservice = new XsgwsqService();
	private static final String XSGW = "xsgw";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XSGW);
	}
	private static final String url = "qgzx_xsgwshnew_sh.do";
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-6 上午11:02:14
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
	public ActionForward getXsgwShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_xsgwshnew_sh.do";
		// 最后一级撤销审核后调用的路径
		request.setAttribute("cancelPath", "xsgwshnew_sh.do?method=cancel");
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXsgwShList");
		
	}
	
	/**
	 * 
	 * @描述: 最后一级撤销  
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-7 下午05:33:03
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		
		HashMap<String, String> shxx = ShlcUtil.getShxx(model.getShid());
		// 业务回滚
		boolean result = service.cancel(model.getSplc(), shxx.get("ywid"));
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-8 上午08:20:33
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
	public ActionForward cxRollBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String shid = request.getParameter("shid");
		try {
			service.cxRollBack(user.getUserName(), shid);
		} catch (Exception e) {
			throw new Exception("回滚审核岗位错误！" + e.getMessage());
		}
		return null;	
	}
	
	/**
	 * 
	 * @描述: 审核
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:24:22
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
	public ActionForward xsgwSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		String type = model.getType();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 困难生认定信息
		KnsjgDao knsjgDao = new KnsjgDao();
		List<KnsjgForm> knslist = new ArrayList<KnsjgForm>();
		List<String[]> list = new ArrayList<String[]>();

		list.addAll(knsjgDao.getKnsjgList(model.getXh()));

		if (list != null && list.size() > 0) {
			for (String[] kns : list) {
				KnsjgForm knsjg = new KnsjgForm();
				knsjg.setXn(kns[0]);
				knsjg.setXqmc(kns[1]);
				knsjg.setDcmc(kns[2]);
				knsjg.setSqsj(kns[3]);
				knslist.add(knsjg);
			}
		}
		
		// 设置学生基本信息
		szXsxx(request, model.getXh());
		XsgwshnewForm modelN = service.getModel(model);
		modelN.setShid(model.getShid());
		request.setAttribute("model", modelN);
		request.setAttribute("knslist", knslist);
		
		request.setAttribute("kzyz", modelN.getGwdm());
		
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());
		request.setAttribute("qgmxList", new WdgwsqService().getQgmxList(model.getXh()));
		//参数设置
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", StringUtils.formatData(csszService.setZjmrCs(request)));
		
		request.setAttribute("xxdm", Base.xxdm);
		if ("ck".equalsIgnoreCase(type)) {
			return mapping.findForward("xsgwCk");
		}	
		return mapping.findForward("xsgwsh");
		
	}
	
	/**
	 * 
	 * @描述: 学生表单
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午09:49:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param xh
	 * void 返回类型 
	 * @throws
	 */
	private void szXsxx(HttpServletRequest request, String xh) {
		// 查询学生信息
		if (xh != null && !"".equals(xh)) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * 
	 * @描述: 审核验证
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:24:46
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
	public ActionForward yzgwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		// 增加人数控制级别设定 验证
		String message = service.yzjb(model,false);
		if (message.equals("true")) {
			response.getWriter().print(service.yzsh(model));
		}else if(message.equals("LastLc")) {
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("message", "请选择该学生岗位！");
			response.getWriter().print(JSONObject.fromObject(resultmap));		
		}else {
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("message", message);
			response.getWriter().print(JSONObject.fromObject(resultmap));
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		String ids=request.getParameter("ids");
		String shyj=request.getParameter("shyj");
		String shgws=request.getParameter("shgws");
		String shzt=request.getParameter("shzt");
		String message=request.getParameter("message");
		String gwdm = request.getParameter("gwdm");
		String xh = request.getParameter("xh");
		String splc = request.getParameter("splc");
		
		request.setAttribute("ids", ids);
		request.setAttribute("shgws", shgws);
		request.setAttribute("message", message);
		if("null".equals(gwdm)) {
			request.setAttribute("gwdms", null);
		}else {
			request.setAttribute("gwdms", gwdm);
		}
		request.setAttribute("xh", xh);
		request.setAttribute("splc", splc);
		
		return mapping.findForward("plsh");
		
	}
	
	
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwshnewForm model = (XsgwshnewForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		XsgwsqDao sqDao = new XsgwsqDao();
		HashMap<String, String> gwxx = sqDao.getGwxx(model.getGwdm());
		String xqrsString = gwxx.get("xqrs");// 需求总人数
		Integer xqrs = StringUtils.paseStringToInteger(xqrsString);
		XsgwshDao shDao = new XsgwshDao();
		Integer yzrs = shDao.getRskz(model.getGwdm());// 在岗人数
		Integer shrs = model.getXhs().length;// 审核人数
		String message = "";
		if(shrs + yzrs > xqrs) {
			message = "当前批量操作：申请人数已经超过该岗位的需求人数，请确认！";
		}else {
			message = service.savePlsh(model, user);
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
		
	}
}
