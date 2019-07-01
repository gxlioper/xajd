/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsq;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.rcsw.qjgl.qjxysz.QjXySzService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:03:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjsqAction extends SuperAction {
	//定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private static final String RCSWRCXW = "rcswqjgl_qjgl";//由 rcswqjgl 改为 rcswqjgl_qjgl
	private BdpzService bdpzService = new BdpzService();
	private QjjgService qjjgService = new QjjgService();
	private List<HashMap<String,String>> jbxxList = null;
	private static int maxSize=5*1024*1024;
	private static String _TJ="tj";//表示提交操作
	
	private static final String url = "qjsqbase.do";
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
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("qjsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "qjsqbase.do";
		request.setAttribute("path", path);
		request.setAttribute("swtjs", service.getWtjs(myForm,user));
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("usertype", user.getUserType());
		QjXySzService qjxysz = new QjXySzService();
		request.setAttribute("qjxy", qjxysz.getQjXySzDada().get("id"));
		return mapping.findForward("qjsqlb");
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
	@SystemLog(description="访问日常事务-请假管理-请假申请-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
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
	 * @描述:批量提交
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-23 上午10:57:09
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
	@SystemLog(description="访问日常事务-请假管理-请假申请-提交VALUES:{values}")
	public ActionForward pltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		String values = request.getParameter("values");
		QjsqForm qjsq = (QjsqForm)form ;
		if (!StringUtil.isNull(values)) {
			qjsq.setQjsqid(values);			
			
			// 退回提交则取最新的审核流程ID
			QjsqForm model = service.getModel(values);	
			model.setSsxydm(qjsq.getSsxydm());
			// 不是退回的记录，重新取审核流程ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				HashMap<String, String> hm = service.getSplc(model);
				if (null==hm) {
					model.setSplcid("无需审核");
				}else{
					model.setSplcid(hm.get("splcid"));
				}
				qjsq.setSplcid(model.getSplcid());
				//已退回的记录取老的审核流程ID
			}else{
				qjsq.setSplcid(model.getSplcid());
			}
			
			boolean result = service.tj(qjsq);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;

			response.getWriter().print(getJsonMessageByKey(messageKey));
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
	@SystemLog(description="访问日常事务-请假管理-请假申请-修改QJSQID:{qjsqid},XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy},QJJS:{qjjs}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		if (!StringUtil.isNull(myForm.getMdd())) {
			myForm.setMdd(URLDecoder.decode(myForm.getMdd(),"UTF-8"));
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//天津经贸请假时段个性化判断
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			// 退回提交则取最新的审核流程ID
			QjsqForm model = service.getModel(myForm.getQjsqid());	
			
			// 不是退回的记录，重新取审核流程ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				HashMap<String, String> hm = service.getSplc(model);
				if (null==hm) {
					model.setSplcid("无需审核");
				}else{
					model.setSplcid(hm.get("splcid"));
				}
				myForm.setSplcid(model.getSplcid());
				model.setShzt(Constants.YW_WTJ);
				//已退回的记录取老的审核流程ID
			}else{
				myForm.setSplcid(model.getSplcid());
				myForm.setShzt(Constants.YW_YTH);
			}
			
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			
			/**
			 * 通用功能保存和提交时做节假日和周末类型的时间选择正确性验证
			 */
			HashMap<String, String> dataMap = service.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			boolean result = service.update(myForm);
			service.saveQjmx(myForm, mxrq, mxxmList);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("qjzt",myForm.getQjzt());
		request.setAttribute("qjts",myForm.getQjts());
		request.setAttribute("qjlxList",service.getQjlx());
		request.setAttribute("filepath", model.getFilepath());
		request.setAttribute("shzt", model.getShzt());
		
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", service.getQjmxList(myForm.getQjsqid()));
		
		//请假课程信息
		List<HashMap<String,String>> qjkcList = service.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		//学生基本信息
		String path = "qjsq.do?method=update";
		request.setAttribute("path", path);

		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("qjsjxsgz", qjjgService.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("isnotList", isnotList);
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//西藏民族大学个性化
			request.setAttribute("dmList", djService.getDmList());
		}
		return mapping.findForward("qjsqxg");
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
 * ActionForward 返回类型 
 * @throws
 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-请假管理-请假申请-增加XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		if (!StringUtil.isNull(myForm.getMdd())) {
			myForm.setMdd(URLDecoder.decode(myForm.getMdd(),"UTF-8"));
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//获取审批流程时的所属学院
			myForm.setSsxydm(xsjbxx.get("xydm"));
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//天津经贸请假时段个性化判断
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			if(service.getDupStatusSerivce(myForm)){
				String messageKey =  MessageKey.RCSW_QJGL_CFSQ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			//myForm.setQjsqid(UniqID.getInstance().getUniqIDHash());
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			
			/**
			 * 通用功能保存和提交时做节假日和周末类型的时间选择正确性验证
			 */
			HashMap<String, String> dataMap = service.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			super.resetToken(request);

			boolean result = service.save(myForm);
			service.saveQjmx(myForm, mxrq, mxxmList);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjlxList",service.getQjlx());
		//get student detail
		//学生基本信息
		String path = "qjsq.do?method=add";
		request.setAttribute("path", path);

		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("qjsjxsgz", qjjgService.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("isnotList", isnotList);
		request.setAttribute("usertype", user.getUserType());
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//西藏民族大学个性化
			request.setAttribute("dmList", djService.getDmList());
		}
		this.saveToken(request);
		return mapping.findForward("qjsqzj");
	}
	/**
	 * 
	 * @描述:提交请假申请
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:44:21
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
	@SystemLog(description="访问日常事务-请假管理-请假申请-提交")
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		boolean result=false;
		//myForm.setQjzt(QjsqService._SQZT_TJZT);
		Map<String, String> map = new HashMap<String, String>();
		HashMap<String, String> dataMap = service.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
		if("false".equals(dataMap.get("rs"))){
			response.getWriter().print(getJsonMessage(dataMap.get("message")));
			return null;
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//天津经贸请假时段个性化判断
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			if(service.getDupStatusSerivce(myForm)){
				String messageKey =  MessageKey.RCSW_QJGL_CFSQ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			//myForm.setQjsqid(UniqID.getInstance().getUniqIDHash());
			
			result = service.save(myForm);
			result=service.tj(myForm);
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			service.saveQjmx(myForm, mxrq, mxxmList);
		}else if(_TJ.equals(myForm.getType())){
			//天津经贸请假时段个性化判断
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			// 退回提交则取最新的审核流程ID
			QjsqForm model = service.getModel(myForm.getQjsqid());	

			// 不是退回的记录，重新取审核流程ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				model.setSsxydm(myForm.getSsxydm());
				HashMap<String, String> hm = service.getSplc(model);
				if (null==hm) {
					model.setSplcid("无需审核");
				}else{
					model.setSplcid(hm.get("splcid"));
				}
				myForm.setSplcid(model.getSplcid());
				//已退回的记录取老的审核流程ID
			}else{
				myForm.setSplcid(model.getSplcid());
			}			
			
			result = service.update(myForm);
			result=service.tj(myForm);
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			service.saveQjmx(myForm, mxrq, mxxmList);
		}else{

			HashMap<String, String> hm = service.getSplc(myForm);
			if (null==hm) {
				myForm.setSplcid("无需审核");
			}else{
				myForm.setSplcid(hm.get("splcid"));
			}
			result = service.update(myForm);
		}
		if(result){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		response.getWriter().print(JSONObject.fromObject(map));
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
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		QjsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HashMap<String, String>  hm=service.getSplc(model,true);
		if(StringUtils.isNotNull(myForm.getShzt())&&!myForm.getShzt().equals("")){
			request.setAttribute("shztmc", hm.get("splcid"));
		}else{
			request.setAttribute("shztmc", "无需审核");
		}
		//请假类型
		QjlxService qjlx=new QjlxService();
		request.setAttribute("qjlxmc",qjlx.getModel(model.getQjlxid()).getQjlxmc());
		//请假课程信息
		List<HashMap<String,String>> qjkcList = service.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", service.getQjmxList(myForm.getQjsqid()));
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", model);
		//获得销假信息
		QjjgService qjjg=new QjjgService();
		request.setAttribute("xjxx", qjjg.getXjxx(model.getQjsqid()));
		return mapping.findForward("qjsqck");
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
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		// 是否可以增加
		boolean result = service.isCanAdd(myForm);
		if (!result) {// 不可以
			response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存之前先验证
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-1 上午11:52:27
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
	public ActionForward checkTs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		//获取请假天数
		Map<String, String> map = new HashMap<String, String>();
		boolean result = service.isHaveSplcForTs(myForm);
		if (!result) {
			map.put("success", "false");
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
	 * 
	 * @描述:获取未提交数
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-18 下午05:55:50
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
	public ActionForward getSwtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		User user = getUser(request);
		Map<String, String> map = new HashMap<String, String>();
		CommService cs = new CommService();
		SearchModel searchModel = cs.getSearchModel(request);
		searchModel.setPath("qjsqbase.do");
		myForm.setSearchModel(searchModel); 
		map.put("wtjs", service.getWtjs(myForm,user));
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	
	
	/**
	 * 
	 * @描述:下载请假申请信息
	 * @作者：王洪锦[工号：1004]
	 * @日期：2013-12-16 下午04:41:17
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
	public ActionForward printQjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		CommShlcImpl commService = new CommShlcImpl();
		XsxxService xsxxService = new XsxxService();
		QjsqForm myForm = (QjsqForm) form;
		if(StringUtils.isNotNull(myForm.getQjsqid())){
			List<File> files = new ArrayList<File>();
			String[] qjsqids = myForm.getQjsqid().split(",");
			for (String id : qjsqids) {
				myForm.setQjsqid(id);
				QjsqForm model = service.getModel(myForm);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				
				String qjsj="自  "+DateTranCnDate.fomartDateToCn(model.getKssj(),FomartDateType.day);
				qjsj+="  至  "+DateTranCnDate.fomartDateToCn(model.getJssj(),FomartDateType.day);
				qjsj+=" 共  "+model.getQjts()+" 天";
				
				if("10351".equals(Base.xxdm)){
					qjsj+=" 共  "+model.getQjjs()+" 节";
				}
				//广西国际商务职业技术学院,请假申请表个性化begin
				if("12379".equals(Base.xxdm) || "14008".equals(Base.xxdm)){
					List<HashMap<String,String>> shxx = commService.getShyjListByYwid(id);
					String sqsj = model.getSqsj();
					xsjbxx.put("sqsj", sqsj);
					
					//审核信息
					for (int i = 0; i < shxx.size(); i++){
						//审核人姓名
						xsjbxx.put("shrxm"+String.valueOf(i+1), shxx.get(i).get("shrmc"));

						//审核时间
						xsjbxx.put("shsj"+String.valueOf(i+1), shxx.get(i).get("shsj"));

						//审核意见
						xsjbxx.put("shyj"+String.valueOf(i+1), shxx.get(i).get("shyj"));
					}
					
				}//广西国际商务职业技术学院,请假申请表个性化end
				xsjbxx.put("qjsj", qjsj);
				
				if("10695".equals(Base.xxdm)){//西藏民族大学个性化
					List<HashMap<String, String>> shxx = commService.getShyjListByYwid(id);
					for (int i = 0; i < shxx.size(); i++){
						xsjbxx.put("shyj"+String.valueOf(i+1), shxx.get(i).get("shyj"));
					}
				}
				
				String qjkssj = DateTranCnDate.fomartDateToCn(model.getKssj(),FomartDateType.day);
				String qjjssj = DateTranCnDate.fomartDateToCn(model.getJssj(),FomartDateType.day);
				xsjbxx.put("qjkssj", qjkssj.substring(qjkssj.indexOf("年")+1));
				xsjbxx.put("qjjssj", qjjssj.substring(qjjssj.indexOf("年")+1));
				xsjbxx.put("sqsj", model.getSqsj().substring(0,10));
				xsjbxx.put("qjsy", HtmlUtil.xmlZy(model.getQjsy()));
				xsjbxx.put("bz", HtmlUtil.xmlZy(model.getBz()));
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}
	/**
	 * 
	 * @描述:华师大个性化请假审批表打印
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-11-19 下午02:54:58
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
	public ActionForward printQjsqbOfHs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> fdyData = new HashMap<String, Object>();
		QjsqService service = new QjsqService();
		XsxxService xsxxService = new XsxxService();
		QjsqForm myForm = (QjsqForm) form;
		if(StringUtils.isNotNull(myForm.getQjsqid())){
			List<File> files = new ArrayList<File>();
			String[] qjsqids = myForm.getQjsqid().split(",");
			for (String id : qjsqids) {
				// ==========查询审核信息，根据审核时间降序 begin=======
				Map<String, String> shMap0 = new HashMap<String, String>();
				ShlcDao splcdao = new ShlcDao();
				List<HashMap<String , String>> shyjList = splcdao.getShyjList(id, "desc");
				if(shyjList.size() >= 1){
					shMap0 = shyjList.get(0);
				}
				// ==========查询审核信息，根据审核时间降序 end=======
				myForm.setQjsqid(id);
				QjsqForm model = service.getModel(myForm);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				String qjkssj = DateTranCnDate.fomartDateToCn(model.getKssj(),FomartDateType.day);
				String qjjssj = DateTranCnDate.fomartDateToCn(model.getJssj(),FomartDateType.day);
				String sqsj = DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day);
				String qjbh = model.getSqsj().substring(0,10).replace("-", "")+model.getQjbh();
				xsjbxx.put("qjkssj", qjkssj.substring(qjkssj.indexOf("年")+1));
				xsjbxx.put("qjjssj", qjjssj.substring(qjjssj.indexOf("年")+1));
				xsjbxx.put("qjts", model.getQjts());
				xsjbxx.put("qjsy", HtmlUtil.xmlZy(model.getQjsy()));
				xsjbxx.put("sqsj", sqsj);
				xsjbxx.put("qjbh", qjbh);
				//请假课程信息
				xsjbxx.put("rkjs","任课教师");
				List<HashMap<String,String>> qjkcList = service.getKcList(model.getKcbhs());
				//任课老师留存请假单
				File qjkcFile =null;
				for (HashMap<String, String> qjkc : qjkcList) {
					xsjbxx.put("kcmc", qjkc.get("kcmc"));
					xsjbxx.put("rkjs", "任课教师");
					xsjbxx.put("rklsxm", qjkc.get("rklsxm"));
					fdyData.putAll(xsjbxx);
					fdyData.put("shMap0", shMap0);
					qjkcFile =service.getWordOfFdy(fdyData);
					files.add(qjkcFile);
				}
				//报表特殊，第一条数据单独处理
				if(qjkcList.size()>0){
					xsjbxx.put("kcmc1", qjkcList.get(0).get("kcmc"));
					xsjbxx.put("rkjs1", "任课教师");
					xsjbxx.put("rklsxm1", qjkcList.get(0).get("rklsxm"));
					qjkcList.remove(0);
					
				}
				data.putAll(xsjbxx);
				
				data.put("qjkcList", qjkcList);
				data.put("shMap0", shMap0);
				File file = service.getWord(data);
				
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-请假管理-请假申请-撤销VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values,lcid);
		if(result){
			//更新业务状态为'未提交'
			QjsqForm model = service.getModel(values);
			ShlcDao splcdao = new ShlcDao();
			if(Integer.valueOf(splcdao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			model.setQjzt(QjsqService._SQZT_CGZT);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
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

		QjsqForm model = (QjsqForm) form;
		QjsqService service = new QjsqService();
		String qjts = request.getParameter("qjts");
		model.setQjts(qjts);
		
		// 取得是否存在验证(根据异动类别) true:可提交/false：不可提交
		boolean isSfktj = service.isHaveSplcForTs(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	/**
	 * 
	 * 选择请假课程
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-18 下午02:38:24
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
	public ActionForward selectQjkc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("qjsq.do?method=selectQjkc");
			myForm.setSearchModel(searchModel);
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getQjkcList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qjsq.do?method=selectQjkc";
		request.setAttribute("path", path);
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("selectqjkc");
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——单个打印请假审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 下午05:55:41
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
	public ActionForward getXsqjspbOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		QjsqForm model = (QjsqForm)form;
		/*获取url带过来的请假申请id*/
		String qjsqid = model.getQjsqid();
		/*获取文件信息*/
		File wordFile = getWordForQjspb(qjsqid);
		/*输出文件*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——批量打印请假审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 下午06:54:55
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
	public ActionForward getXsqjspbZip (ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		/*获取url带过来的Value*/
		String value = request.getParameter("value");
		/*判断value是否为空*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForQjspb(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @描述: 请假审批表数据输出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 下午06:44:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForQjspb (String qjsqid) throws Exception{
		
		QjsqService service = new QjsqService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*定义一个空file*/
		File file = null;
		/*根据所选qjsqid获取请假信息*/
		HashMap<String,String> qjxxInfo = service.getQjsqxxForQjsqid(qjsqid);
		String xh = qjxxInfo.get("xh");
		/*输出假信息*/
		data.put("qjxxInfo", qjxxInfo);
		
		XsxxService xsxxService = new XsxxService();
		/*加载学生基本信息*/
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		
		/*分割请假开始结束时间的月日时*/
		String kssjMonth = qjxxInfo.get("kssj").substring(5, 7);
		String kssjDay = qjxxInfo.get("kssj").substring(8, 10);
		String kssjHour = qjxxInfo.get("kssj").substring(11, 13);
		data.put("kssjMonth", kssjMonth);
		data.put("kssjDay", kssjDay);
		data.put("kssjHour", kssjHour);
		String jssjMonth = qjxxInfo.get("jssj").substring(5, 7);
		String jssjDay = qjxxInfo.get("jssj").substring(8, 10);
		String jssjHour = qjxxInfo.get("jssj").substring(11, 13);
		data.put("jssjMonth", jssjMonth);
		data.put("jssjDay", jssjDay);
		data.put("jssjHour", jssjHour);
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw","qjspb_12869.xml", FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		return file;
	}
}
