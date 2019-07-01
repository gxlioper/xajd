/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjjg;

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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqForm;
import com.zfsoft.xgxt.rcsw.qjgl.qjsq.QjsqService;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqCsszForm;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqcsszService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:03:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjjgAction extends SuperAction {
	//定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private static final String RCSWRCXW = "rcswqjgl_qjgl";
	private static int maxSize=5*1024*1024;
	private BdpzService bdpzService = new BdpzService();
	private QjsqService qjsqService = new QjsqService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "qjjgbase.do";

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
		QjjgService service = new QjjgService();
		CommService cs = new CommService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("qjjgbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "qjjgbase.do";
		request.setAttribute("path", path);
		request.setAttribute("currTime", GetTime.getSystemTime());
		//重庆三峡销假提醒
		request.setAttribute("xjts", MessageUtil.getText("xjts"));
		request.setAttribute("usertype",getUser(request).getUserType());
		XjsqCsszForm xjForm = new XjsqcsszService().getModel();
		if(xjForm != null && StringUtils.isNotNull(xjForm.getSplc())){
			request.setAttribute("xjadmit", true);
		}else{
			request.setAttribute("xjadmit", false);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
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
	@SystemLog(description="访问日常事务-请假管理-请假结果-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
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
	@SystemLog(description="访问日常事务-请假管理-请假结果-修改QJJGID:{qjjgid},XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},QJJS:{qjjs},SFLX:{sflx},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
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
			HashMap<String, String> dataMap = qjsqService.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			boolean result = service.update(myForm);
			QjsqForm qjsqForm = new QjsqForm();
			qjsqForm.setQjsqid(myForm.getQjsqid());
			new QjsqService().saveQjmx(qjsqForm, mxrq, mxxmList);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		request.setAttribute("qjlxList",service.getQjlx());
		request.setAttribute("filepath", model.getFilepath());
		//学生基本信息
		String path = "qjjg.do?method=update";
		request.setAttribute("path", path);
		//请假课程信息
		List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", new QjsqService().getQjmxList(myForm.getQjsqid()));
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("qjsjxsgz", service.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("isnotList", isnotList);
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//西藏民族大学个性化
			request.setAttribute("dmList", djService.getDmList());
		}
		return mapping.findForward("update");
	}
	/**
	 * 
	 * @描述:销假处理
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-12 上午09:49:20
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
	@SystemLog(description="访问日常事务-请假管理-请假结果-销假QJJGID:{qjjgid}")
	public ActionForward xjcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
 		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.xjcl(myForm,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		//请假课程信息
		List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		//请假类型
		QjlxService qjlx=new QjlxService();
		request.setAttribute("qjlxmc",qjlx.getModel(model.getQjlxid()).getQjlxmc());
		request.setAttribute("qjsjxsgz", service.getCssz().get("qjsjxsgz"));
		return mapping.findForward("xjcl");
	}
	/**
	 * 
	 * @描述:增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-17 上午10:49:02
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
	@SystemLog(description="访问日常事务-请假管理-请假结果-增加XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},QJJS:{qjjs},SFLX:{sflx},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
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
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			myForm.setQjzt("1");//结果库增加
			myForm.setQjsqid(UniqID.getInstance().getUniqIDHash());
			
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
			HashMap<String, String> dataMap = qjsqService.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			super.resetToken(request);
			
			boolean result = service.save(myForm);
			
			QjsqForm qjsqForm = new QjsqForm();
			qjsqForm.setQjsqid(myForm.getQjsqid());
			new QjsqService().saveQjmx(qjsqForm, mxrq, mxxmList);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", new QjsqService().getQjmxList(myForm.getQjsqid()));
		request.setAttribute("qjlxList",service.getQjlx());
		//get student detail
		//学生基本信息
		String path = "qjjg.do?method=add";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//学年 学期
		myForm.setXq(Base.currXq);
		myForm.setXn(Base.currXn);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("qjsjxsgz", service.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("isnotList", isnotList);
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//西藏民族大学个性化
			request.setAttribute("dmList", djService.getDmList());
		}
		this.saveToken(request);
		return mapping.findForward("add");
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
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		QjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
 		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HashMap<String, String>  hm=service.getSplc(model,true);
		request.setAttribute("shztmc", hm.get("splcid"));
		//请假类型
		QjlxService qjlx=new QjlxService();
		request.setAttribute("qjlxmc",qjlx.getModel(model.getQjlxid()).getQjlxmc());
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", model);
		//请假课程信息
		List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", new QjsqService().getQjmxList(myForm.getQjsqid()));
		
		//销假信息
		request.setAttribute("xjxx", service.getXjxx(model.getQjsqid()));
		//历史请假记录
		request.setAttribute("history",service.getHistory(model));
		return mapping.findForward("show");
	}
	/**
	 * 
	 * @描述:检测请假天数对应审核流程
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-12 上午10:11:54
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
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		String sfjc=request.getParameter("sfjc");
		Map<String, String> map = new HashMap<String, String>();
		//如果设置为不检测
		if(!StringUtil.isNull(sfjc)){
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		//获取请假天数
		myForm.setQjts(request.getParameter("qjts"));
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
	 * @描述:判断是否有最新申请记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-28 上午09:10:35
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
	public ActionForward haveNewSqjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		Map<String, String> map = new HashMap<String, String>();
		if(service.haveNewSqjl(myForm)){
			map.put("message", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;

	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专销假人判断（超过10天（包含10天））
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-8 上午09:01:52
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
	public ActionForward xjrpdTenD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
		String yhm = user.getUserName();
		Map<String, String> map = new HashMap<String, String>();
		String sqid = myForm.getQjsqid();
		HashMap<String, String> lcxx = service.xjrpdTenD(sqid);
		String xjr = lcxx.get("shr"); // 获取流程中倒数第二流程的审核人
		if(!yhm.equals(xjr)) {
			map.put("message", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
				
		return null;

	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专销假人判断（10天以下（不包含10天））
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-8 上午09:01:52
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
	public ActionForward xjrpdOneToN(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		User user = getUser(request);
		String yhm = user.getUserName();
		Map<String, String> map = new HashMap<String, String>();
		String sqid = myForm.getQjsqid();
		HashMap<String, String> lcxx = service.xjrpdOneToN(sqid);
		String xjr = lcxx.get("shr"); // 获取流程中最后一级流程的审核人
		if(!yhm.equals(xjr)) {
			map.put("message", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
				
		return null;

	}
	
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgForm model = (QjjgForm) form;
		//根据不同的审核类型 去自定义导出
		QjjgService service = new QjjgService();
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
	
	/**
	 * 
	 * @描述:下载请假结果表
	 * @作者：王洪锦[工号：1004]
	 * @日期：2013-12-17 下午02:33:17
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
	public ActionForward printQjjgb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjjgService service = new QjjgService();
		QjjgForm myForm = (QjjgForm) form;
		XsxxService xsxxService = new XsxxService();
		if(StringUtils.isNotNull(myForm.getQjjgid())){
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjjgid().split(",");
			for (String id : qjjgids) {
				myForm.setQjjgid(id);
				HashMap<String,String> qjjgMap=service.getQjjgForPrint(id);
				//加载学生基本信息
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(qjjgMap.get("xh"));
				
				File file = service.printForWord(xsjbxx,qjjgMap);
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
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-22 下午04:01:52
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
	public ActionForward printQjjgbOfHs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> fdyData = new HashMap<String, Object>();
		QjjgService service = new QjjgService();
		XsxxService xsxxService = new XsxxService();
		QjjgForm myForm = (QjjgForm) form;
		if(StringUtils.isNotNull(myForm.getQjjgid())){
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjjgid().split(",");
			for (String id : qjjgids) {
				myForm.setQjjgid(id);
				QjjgForm model = service.getModel(myForm);
				// ==========查询审核信息，根据审核时间降序 begin=======
				Map<String, String> shMap0 = new HashMap<String, String>();
				ShlcDao splcdao = new ShlcDao();
				List<HashMap<String , String>> shyjList = splcdao.getShyjList(model.getQjsqid(), "desc");
				if(shyjList.size() >= 1){
					shMap0 = shyjList.get(0);
				}
				// ==========查询审核信息，根据审核时间降序 end=======
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
				List<HashMap<String,String>> qjkcList = qjsqService.getKcList(model.getKcbhs());
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
	
	/**
	 * @描述: 浙江警官职业学院个性化——单个打印请假审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-13 下午02:17:09
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
		QjjgForm model = (QjjgForm)form;
		/*获取url带过来的请假结果id*/
		String qjjgid = model.getQjjgid();
		/*获取文件信息*/
		File wordFile = getWordForQjspb(qjjgid);
		/*输出文件*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——批量打印请假审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-13 下午02:17:26
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
	 * @日期： 2017-10-13 下午02:19:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjjgid
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForQjspb (String qjjgid) throws Exception{
		
		QjjgService service = new QjjgService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*定义一个空file*/
		File file = null;
		/*根据所选qjsqid获取请假信息*/
		HashMap<String,String> qjxxInfo = service.getQjjgxxForQjsqid(qjjgid);
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

