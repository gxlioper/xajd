/**
 * @部门:学工产品事业部
 * @日期：2016-6-1 上午11:16:09 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 学生岗位申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-1 上午11:16:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsgwsqAction extends SuperAction<XsgwsqForm, XsgwsqService>{
	
	private XsgwsqService service = new XsgwsqService();
	private static final String XSGW = "xsgw";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XSGW);
	}
	private static final String url = "qgzx_xsgwsqnew_sq.do";
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-1 下午04:38:21
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
	public ActionForward getXsgwSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		
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
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));

		String path = "qgzx_xsgwsqnew_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if(Base.xxdm.equals("10704") && getUser(request).getUserStatus().equals("stu")){//西安科技大学个性化
			WdgwsqService gwsqService = new WdgwsqService();
			request.setAttribute("isTg", gwsqService.isTgInOneYear(getUser(request).getUserName())==true?"1":"0");
		}
		
		return mapping.findForward("getXsgwSqList");
	}
	
	/**
	 * 
	 * @描述: 申请
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 上午09:32:56
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
	public ActionForward addXsgwSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		String xh = request.getParameter("xh");
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			
			boolean result = service.saveSq(model);
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {	
			if(service.isHaveRecordForSq(model.getXh(), Base.currXn)) {
				String message = MessageUtil.getText(MessageKey.XLZXWZDX_XSSQ_EXIST);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}			
			String result = service.saveSq(model, "submit");
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}
			
			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result) ? MessageKey.SYS_SUBMIT_SUCCESS
							: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 设置学生基本信息
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			szXsxx(request, user.getUserName());
		} else {
			szXsxx(request, xh);
		}
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));
		request.setAttribute("xxdm", Base.xxdm);
		String path = "xsgwsqnew_sq.do?method=addXsgwSq";
		if ( "yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))) {
			String qgzxPath = "xsgwsqnew_sq.do?method=QgzxStuSq";
			request.setAttribute("path", qgzxPath);
		} else {
			request.setAttribute("path", path);
		}
		List<HashMap<String, String>> qgxmList= service.getQgmxList(xh);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());//和日常事务当中的课程时间一致
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		request.setAttribute("model", StringUtils.formatData(model));
		
		return mapping.findForward("addXsgwSq");
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
	 * @描述: 删除
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 下午02:37:16
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
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		int result = service.runDelete(values.split(","));
		String messageKey = null;
		if (result > 0) {
			messageKey = ShlcUtil.deleteSpxx(values.split(",")) ? MessageKey.SYS_DEL_SUCCESS
					: MessageKey.SYS_DEL_FAIL;
		} else {
			messageKey = MessageKey.SYS_DEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;		
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 下午03:26:31
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
	public ActionForward subBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		boolean result = service.submitRecord(values, lcid, xh);
		if (result) {
			// 更新业务状态为'审核中'
			XsgwsqForm model = new XsgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_SHZ);
			// 暂时去掉
			// ShlcDao shlcDao = new ShlcDao();
			// model.setShgw(shlcDao.getFirstGwid(lcid));
			// 清空审核岗位
			model.setShgw("");
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-2 下午03:55:33
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
	public ActionForward cancle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = false;
		result = service.cancleRecord(values, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			XsgwsqForm model = new XsgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_WTJ);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-3 上午09:50:34
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
	public ActionForward ckXsgwSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
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
		XsgwsqForm modelN = service.getModel(model);
		request.setAttribute("model", modelN);
		request.setAttribute("knslist", knslist);
		
		request.setAttribute("kzyz", modelN.getGwdm());
		
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());
		request.setAttribute("qgmxList", service.getQgmxList(model.getXh()));
		//参数设置
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", StringUtils.formatData(csszService.setZjmrCs(request)));
		
		request.setAttribute("xxdm", Base.xxdm);
		
		return mapping.findForward("ckXsgwSq");
	}
	
	
	@SystemAuth(url = url)
	public ActionForward ckXsgwJgwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsgwsqForm myForm = (XsgwsqForm) form;
		String gwdm = request.getParameter("gwdm");
		String xh = request.getParameter("xh");		
		XsgwsqDao dao = new XsgwsqDao();
		HashMap<String,String> rs = dao.getGwxxMap(gwdm);
		request.setAttribute("rs", rs);
		// 设置学生基本信息
		szXsxx(request, xh);
		
		return mapping.findForward("ckXsgwJgwh");
	}
	
	/**
	 * 
	 * @描述: 岗位信息查询
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:32:23
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
	public ActionForward wdgwxzCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		final String tzlx = "tz";
		XsgwsqForm model = (XsgwsqForm) form;
		String lx = request.getParameter("lx");
		String path = "xsgwsqnew_sq.do?method=wdgwxzCx";
		// 标志为调整类型
		if (StringUtils.isNotNull(lx) && lx.equals(tzlx)) {
			path += "&lx=" + tzlx;
			request.setAttribute("lx", tzlx);
			XsgwsqForm wf = service.getModel(model);
			model.setYrdwdm(wf.getYrdwdm());
		}else {	
			request.setAttribute("lx", null);
		}
		if (QUERY.equals(model.getType())) {

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getGwPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
//		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		

		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wdgwxzCx");
		
	}
	
	/**
	 * 
	 * @描述: 岗位信息
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-6-13 上午09:33:13
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
	public ActionForward gwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsgwsqForm model = (XsgwsqForm) form;
		int xszggwsl = service.getXszggwsl(model);
		int xssqsl = service.getXssqsl(model);
		String message = "";
		if (xszggwsl > 0) {
			message = "此岗位该学生已经在岗";
		}
		if (xssqsl > 0) {
			message = "此岗位该学生已经申请";
		}
		// 酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String, String> map = qgzxCsszService.getCssz();
		String isshow = "false";
		String sfsdgwcjsx = map.get("sfsdgwcjsx");
		model = service.getModel(model);
		// 如果设置了酬金上限
		if ("yes".equals(sfsdgwcjsx)) {
			isshow = "true";
			// 如果当前岗位未设置酬金上限，则显示基本配置的酬金上限
			if (StringUtils.isNull(model.getGwcjsx())) {
				model.setGwcjsx(map.get("gwzgcjsx"));
			}
		}
		request.setAttribute("isshow", isshow);
		request.setAttribute("message", message);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("lx", request.getParameter("lx"));

		if (StringUtils.equals(request.getParameter("type"), "stu")) {
			request.setAttribute("userlx", "stu");
		}

		return mapping.findForward("gwxx");	
	}
	
}
