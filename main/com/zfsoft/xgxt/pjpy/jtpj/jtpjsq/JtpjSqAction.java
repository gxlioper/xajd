package com.zfsoft.xgxt.pjpy.jtpj.jtpjsq;

import java.io.File;
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
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.sdftj.sdfTjService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjjg.JtpjJgService;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzForm;
import com.zfsoft.xgxt.pjpy.jtpj.jtpzsz.JtpjSzService;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqForm;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-28 上午10:54:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjSqAction extends SuperAction {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	private static final String template_dir = "classpath://templates//jtpj";//模版路径
	public final static String JTPJ_SHLC = "pjpy";

	private static final String url = "jtpjsqbase.do";
	
	/**
	 * 
	 * @描述:集体评奖列表查询显示
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
		JtpjSqService service = new JtpjSqService();
		JtpjSqForm myForm = (JtpjSqForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "jtpjsqbase.do");
		FormModleCommon.commonRequestSet(request);
		// 默认当前学年学期
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("jtpjsqlist");
	}

	/**
	 * 
	 * @描述: 寝室信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-7 下午03:54:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward qsxxlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		JtpjSqForm myForm = (JtpjSqForm) form;
		String bjdm = request.getParameter("bjdm");
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("jtpjsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================

			List<HashMap<String, String>> resultList = service
					.getPageListForQsxx(myForm, bjdm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("bjdm", bjdm);
		return mapping.findForward("bjqsxx");
	}
	
	public ActionForward qsxsxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("qsxsxx");
	}
	
	public ActionForward getQsxsxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtpjSqService service = new JtpjSqService();
		JtpjSqForm myForm = (JtpjSqForm) form;
		// ==================高级查询相关 end========================

		List<HashMap<String, String>> resultList = service.getQsxsxxList(myForm);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖申请-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		String values = request.getParameter("values");
		if (!StringUtils.isNull(values)) {
			Map<String, Object> map = service.delete(values.split(","));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述: 集体评奖模块
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖申请-修改SQID:{sqid},JXID:{jxid},PJJTID:{pjjtid},SQLY:{sqly}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		JtpjSqForm myForm = (JtpjSqForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			myForm.setSqr(user.getUserName());
			myForm.setSqrylx(service.getSqrlx(user.getUserType()));
			
			JtpjSqForm model = service.getModel(myForm.getSqid());
			
			// 已退回的记录取老的审核流程ID,否则取最新的
			if(!Constants.YW_YTH.equals(model.getShzt())){
				JtpjSzForm jtpjsz = new JtpjSzForm();
				JtpjSzService jtpjszService = new JtpjSzService();
				jtpjsz.setJxid(model.getJxid());
				jtpjsz = jtpjszService.getModel(jtpjsz);
				
				// 取最新的审核流程ID
				myForm.setSplcid(jtpjsz.getSplcid());
			}else{
				myForm.setSplcid(model.getSplcid());
				myForm.setShzt(Constants.YW_YTH);
			}
			
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		myForm = service.getModel(myForm.getSqid());
		request.setAttribute("data", myForm);
		request.setAttribute("xqmc", service.getXqmc(myForm.getSqxq()));
		JtpjSzService jss = new JtpjSzService();
		
		// 已退回则需要锁定
		if(Constants.YW_YTH.equals(myForm.getShzt())){
			request.setAttribute("jxList", jss.getJxList(Base.currXn, Base.currXq, "2", myForm.getJxid(),user));
		}else{
			request.setAttribute("jxList", jss.getJxList(Base.currXn, Base.currXq, "1",user));
		}
		return mapping.findForward("jtpjsqupdate");
	}

	/**
	 * 
	 * @描述: 获取修改默认信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午05:32:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward loadUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		String sqid = request.getParameter("sqid");
		JtpjSqForm myForm = service.getModel(sqid);
		response.getWriter().print(JSONObject.fromObject(StringUtils.formatData(myForm)));
		return null;
	}

	/**
	 * 
	 * @描述:增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:44:10
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
	@SystemLog(description = "访问评奖评优-集体评奖-集体评奖申请-增加JXID:{jxid},PJJTID:{pjjtid},SQLY:{sqly}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		JtpjSqForm myForm = (JtpjSqForm) form;
		myForm.setSqxn(Base.currXn);
		myForm.setSqxq(Base.currXq);
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			myForm.setSqr(user.getUserName());
			myForm.setSqrylx(service.getSqrlx(user.getUserType()));
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("xqmc", Base.getDqxqmc());
		JtpjSzService jss = new JtpjSzService();
		request.setAttribute("data", myForm);
		request.setAttribute("jxList", jss.getJxList(Base.currXn, Base.currXq, "1",user));
		this.saveToken(request);
		return mapping.findForward("jtpjsqadd");
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		JtpjSqForm myForm = (JtpjSqForm) form;
		boolean result=false;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//重复判断
			if(!("qt").equals(myForm.getJtpjdw())&&!service.checkIsNotExists(myForm.getJxid(), myForm.getPjjtid(),null, null,"sq")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			if(("qt").equals(myForm.getJtpjdw())&&!service.checkIsNotExists(myForm.getJxid(),null,myForm.getPjjtmc(),null,"sq")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			myForm.setSqxn(Base.currXn);
			myForm.setSqxq(Base.currXq);
			User user = getUser(request);
			myForm.setSqr(user.getUserName());
			myForm.setSqrylx(service.getSqrlx(user.getUserType()));
			myForm.setSqid(UniqID.getInstance().getUniqIDHash());
			result = service.save(myForm);
			
			//提交
		}else if(UPDATE.equalsIgnoreCase(myForm.getType())){
			
			//重复判断
			if(!("qt").equals(myForm.getJtpjdw())&&!service.checkIsNotExists(myForm.getJxid(), myForm.getPjjtid(),null,myForm.getSqid(),"sq")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			if(("qt").equals(myForm.getJtpjdw())&&!service.checkIsNotExists(myForm.getJxid(),null,myForm.getPjjtmc(),myForm.getSqid(),"sq")){
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_JTPJ_SQ_REPEAT));
				return null;
			}
			User user = getUser(request);
			myForm.setSqr(user.getUserName());
			myForm.setSqrylx(service.getSqrlx(user.getUserType()));
			
			JtpjSqForm model = service.getModel(myForm.getSqid());
			
			// 已退回的记录取老的审核流程ID,否则取最新的
			if(!Constants.YW_YTH.equals(model.getShzt())){
				JtpjSzForm jtpjsz = new JtpjSzForm();
				JtpjSzService jtpjszService = new JtpjSzService();
				jtpjsz.setJxid(model.getJxid());
				jtpjsz = jtpjszService.getModel(jtpjsz);
				
				// 取最新的审核流程ID
				myForm.setSplcid(jtpjsz.getSplcid());
			}else{
				myForm.setSplcid(model.getSplcid());
			}
			
			result = service.update(myForm);
		}
		result = service.submitRecord(myForm.getSqid(), myForm.getSplcid(),null);
		if (result) {
			// 更新业务状态为'审核中'
			myForm.setShzt(Constants.YW_SHZ);
			service.update(myForm);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述: 提交
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 上午10:46:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		String values = request.getParameter("values");
		String xh = request.getParameter("xh");
		
		String lcid = "";
		JtpjSqForm modelGet = service.getModel(values);
		
		// 已退回的记录取老的审核流程ID,否则取最新的
		if(!Constants.YW_YTH.equals(modelGet.getShzt())){
			JtpjSzForm jtpjsz = new JtpjSzForm();
			JtpjSzService jtpjszService = new JtpjSzService();
			jtpjsz.setJxid(modelGet.getJxid());
			jtpjsz = jtpjszService.getModel(jtpjsz);
			
			// 取最新的审核流程ID
			lcid = jtpjsz.getSplcid();
		}else{
			lcid = modelGet.getSplcid();
		}
		
		boolean result = service.submitRecord(values, lcid, xh);
		if (result) {
			// 更新业务状态为'审核中'
			JtpjSqForm model = new JtpjSqForm();
			model.setSqid(values);
			model.setShzt(Constants.YW_SHZ);
			model.setSplcid(lcid);
			service.update(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述: 撤销
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 上午10:46:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			JtpjSqForm model = new JtpjSqForm();
			model.setSqid(values);
			model.setSplcid(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(values)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.update(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:显示具体信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-17 下午05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		JtpjSqForm myForm = (JtpjSqForm) form;
		myForm = service.getModel(myForm.getSqid());
		JtpjSzService jss = new JtpjSzService();
		myForm.setJxmc(jss.getModel(myForm.getJxid()).getJxmc());
		request.setAttribute("xqmc", service.getXqmc(myForm.getSqxq()));
		request.setAttribute("data", StringUtils.formatData(myForm));
		return mapping.findForward("jtpjsqview");
	}

	/**
	 * 
	 * @描述: 加载班级评奖信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:57:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward loadBjpjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		User user = getUser(request);
		String bjdm = request.getParameter("bjdm");
		List<HashMap<String, String>> bjlist = service.getBjList(user);
		request.setAttribute("bjList", bjlist);
//		if (StringUtils.isNull(bjdm) && null != bjlist && bjlist.size() > 0) {
//			bjdm = bjlist.get(0).get("bjdm");
//		}
		if ("stu".equals(user.getUserType())) {
			if(null != bjlist && bjlist.size() > 0){
				bjdm = bjlist.get(0).get("bjdm");
			}
		}
		request.setAttribute("bjdm", bjdm);
		if(StringUtils.isNotNull(bjdm)){
			request.setAttribute("bjmc", service.getBjmc(bjdm));
		}else{
			request.setAttribute("bjmc", "");
		}
		
		request.setAttribute("qss", service.getQss(bjdm));
		request.setAttribute("bjrs", service.getBjrs(bjdm));
		request.setAttribute("bzrList", service.getBzrxx(bjdm));
		request.setAttribute("fdyList", service.getFdyxx(bjdm));
		// 是否温州大学
		request.setAttribute("iswzdx",
				Globals.XXDM_WZDX.equals(Base.xxdm) ? "1" : "0");
		if (VIEW.equals(request.getParameter("type"))) {
			String sqid = request.getParameter("sqid");
			JtpjSqForm myForm = service.getModel(sqid);
			request.setAttribute("data", StringUtils.formatData(myForm));
			return mapping.findForward("bjview");
		}
		return mapping.findForward("bj");
	}

	/**
	 * 
	 * @描述: 加载学院相关信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-30 下午01:56:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward loadXypjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		User user = getUser(request);
		String xydm = request.getParameter("xydm");
		List<HashMap<String, String>> xylist = service.getXyList(user);
		request.setAttribute("xyList", xylist);
		if (StringUtils.isNull(xydm) && null != xylist && xylist.size() > 0) {
			xydm = xylist.get(0).get("xydm");
		}
		request.setAttribute("xydm", xydm);
		request.setAttribute("qss", service.getXyQss(xydm));
		request.setAttribute("xylable", message.getMessage("lable.xb"));
		request.setAttribute("xyrs", service.getXyrs(xydm));
		if (VIEW.equals(request.getParameter("type"))) {
			String sqid = request.getParameter("sqid");
			JtpjSqForm myForm = service.getModel(sqid);
			request.setAttribute("data", StringUtils.formatData(myForm));
			return mapping.findForward("xyview");
		}
		return mapping.findForward("xy");
	}
	
	public ActionForward loadld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		sdfTjService sdfTjService=new sdfTjService();
		if (VIEW.equals(request.getParameter("type"))) {
			JtpjSqService service = new JtpjSqService();
			String sqid = request.getParameter("sqid");
			JtpjSqForm myForm = service.getModel(sqid);
			String lddm=myForm.getPjjtid().split("@@")[0];
			request.setAttribute("ldmc", sdfTjService.getLdmc(lddm));
			String qsh=myForm.getPjjtid().split("@@")[1];
			request.setAttribute("qsh", qsh);
			return mapping.findForward("qsview");
		}
		List<HashMap<String, String>> ldlist = sdfTjService.getLddmList();
		request.setAttribute("ldlist", ldlist);
		return mapping.findForward("qs");
	}

	public ActionForward loadOtherPjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqService service = new JtpjSqService();
		if (VIEW.equals(request.getParameter("type"))) {
			String sqid = request.getParameter("sqid");
			JtpjSqForm myForm = service.getModel(sqid);
			request.setAttribute("data", StringUtils.formatData(myForm));
			return mapping.findForward("qtview");
		}
		return mapping.findForward("qt");
	}
	
	/**
	 * 
	 * @描述: 验证是否可提交
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward checkSfktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JtpjSqForm model = (JtpjSqForm) form;
		JtpjSqService service = new JtpjSqService();
		String jxid = request.getParameter("jxid");
		model.setJxid(jxid);
		
		// 取得是否存在验证(根据异动类别) true:可提交/false：不可提交
		boolean isSfktj = service.checkSfktj(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	
	/**
	 * @描述: 打印Word登记表（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
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
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqForm model = (JtpjSqForm) form;
		File wordFile = getWord(model.getSqid());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getWord(String sqid) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		JtpjSqService jtpjSqService = new JtpjSqService();
		HashMap<String, String> djbMap=null;
		if("11458".equals(Base.xxdm)){
			djbMap= jtpjSqService.getDjbInfo(sqid);
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				djbMap.put("sqxn", djbMap.get("sqxn").replace("-", "/"));
				data.putAll(djbMap);
				
				String templateXmlPath = "xjjtdjb_11458.xml";
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, templateXmlPath, djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}
		else if("10080".equals(Base.xxdm)){
			//河北工业大学个性化
			djbMap= jtpjSqService.getjtpjInfo(sqid);//基本信息
			if (djbMap != null){
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				String bjdm = djbMap.get("bjdm");
				List<HashMap<String,String>> bjgbList = jtpjSqService.getbjgbInfo(bjdm);//班干部信息
				data.putAll(djbMap);
				data.put("bjgbInfo",bjgbList);
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, djbMap.get("mbmc"), djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}
		else{
			djbMap= jtpjSqService.getDjb(sqid);
			if(djbMap!=null){
				//登记表自动填充所需数据
				data.putAll(new JtpjJgService().fillData(djbMap));
				djbMap.put("sqly", HtmlUtil.xmlZy(djbMap.get("sqly")));
				data.put("currY", DateUtils.getYear());//当前年
				data.put("currM", DateUtils.getMonth());//当前月
				data.put("currD",DateUtils.getDayOfMonth());//当前日
				//获取其他单位信息
				HashMap<String, String> dwMap = jtpjSqService.getBjxgxx(djbMap.get("pjjtmc"));
				if("10876".equals(Base.xxdm)){
					data.put("xymc", dwMap.get("xymc"));
				}
				if(StringUtils.isNull(djbMap.get("mbmc"))){
					throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
				}
				File wordFile = FreeMarkerUtil.getWordFile(data, template_dir, djbMap.get("mbmc"), djbMap.get("pjjtmc") + "-" + djbMap.get("jxmc"));
				return wordFile;
			}
		}
		return null;
	}
	
	/**
	 * @描述: 登记 表ZIP 
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-4 上午11:30:24
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	public ActionForward bjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JtpjSqForm model1 = (JtpjSqForm) form;
		XsgzzbsqForm model = new XsgzzbsqForm();
		model.setSearchModel(model1.getSearchModel());
		model.setPages(model1.getPages());
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model1.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("flag", StringUtils.isNotNull(request.getParameter("flag"))? request.getParameter("flag"):"");
		String path = "jtpjsq.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}
	
}
