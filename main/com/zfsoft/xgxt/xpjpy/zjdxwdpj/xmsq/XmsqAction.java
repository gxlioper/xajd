/**
 * @部门:学工产品(1)部
 * @日期：2017-5-12 下午04:59:38 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
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
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.bbwh.BbwhService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh.ZcwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖_奖项申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-5-12 下午05:00:34 
 * @版本： V5.18.26
 * @修改记录: 2018-01-04 
 */

public class XmsqAction extends SuperAction {
	private static final String url = "xpjpy_wdpj_pjsq.do";
	private static final String PJPY = "pjpy";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
		jbxxList = bdpzService.getJbxxpz(PJPY);
	}
	private XmsqService service = new XmsqService();
	
	/**
	 * @描述: 跳转到项目申请列表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2017-5-12 下午06:01:44
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
	@SystemLog(description = "访问新评奖评优-我的评奖-项目申请-查询页面")
	public ActionForward getXmsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*参数设置信息*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		/*默认查询条件{xn}*/
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		request.setAttribute("searchTj", searchModel);
		/*返回path*/
		String path = "xpjpy_wdpj_pjsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsqList");
	}
	
	/**
	 * @描述: 获取项目申请列表JSON数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-15 上午11:11:25
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
	@SystemLog(description = "访问新评奖评优-我的评奖-项目申请-查询页面Json数据")
	public ActionForward getXmsqListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm) form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		/*查询并返回JSON数据*/
		List<HashMap<String, String>> resultList = service.getPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 项目申请页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-15 下午07:33:23
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
	@SystemLog(description = "访问新评奖评优-我的评奖-项目申请-增加页面")
	public ActionForward xmsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		/*用户对象*/
		User user = getUser(request);
		/*如果是学生用户取登录用户，如果不是学生用户进行学号选择*/
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
		/*学生基本信息*/
		if (!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			
			/*获取该学生最近一次申请信息*/
			HashMap<String,String> latestSqxx = service.getLatestSqxx(xh);
			request.setAttribute("latestSqxx",latestSqxx);
		}
		/*自定义表单*/
		request.setAttribute("jbxxList", jbxxList);
		/*参数设置信息*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		/*返回path*/
		String path = "xpjpy_xmsq.do?method=xmsqAdd";
		request.setAttribute("path", path);
		return mapping.findForward("xmsqAdd");
	}
	
	/**
	 * @描述: 项目申请_“自动加载开放的奖项”或者“点击选择申请奖项”
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-15 下午08:22:38
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项申请-奖项选择")
	public ActionForward selectPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		Map<String,Object> resultMap = service.getXmsqInfoList(model.getXh());
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("selectPjxm");
	}
	
	/**
	 * @描述: 根据项目代码，获取不可兼得已申请的奖项。
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 上午11:23:12
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项申请-兼得查询")
	public ActionForward getBjdxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xmdm = request.getParameter("xmdm");
		List<HashMap<String, String>> resultList = service.getBjdxm(xmdm);
		JSONArray data = JSONArray.fromObject(resultList);
		response.getWriter().print(data);
		return null;
	}
	
	/**
	 * @描述: 项目申请保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午03:10:23
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-保存项目代码:{xmdms},学号:{xh},外语水平:{wysp},宿舍电话:{ssdh},担任社会工作职务:{gzzw}")
	public ActionForward saveXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		String[] xmdm = request.getParameterValues("xmdms");
		/*验证人数*/
		if("submit".equals(model.getType())){
			HashMap<String,String> checkMap = new HashMap<String,String>();
			for (int i = 0; i < xmdm.length; i++) {
				model.setXmdm(xmdm[i]);
				checkMap = checkRs(model);
				/*如果一个学生一次申请多个奖项，只要有一条不符合规则的，那么所有奖项信息都不能进行保存*/
				if("true".equals(checkMap.get("isXmrsOut"))){
					response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
					return null;
				}
			}
			
		}
		
		if("dgtj".equals(request.getParameter("flag"))){
			CsszService csszService = new CsszService();
			CsszForm csszForm = csszService.getCsszModel();
			/*判断是否不可兼得*/
			boolean result = service.checkIsNotbkjd(xmdm[0], csszForm.getXn(),model.getXh());
			if(!result){
				/*不兼得名称*/
				String bkjdmc = service.getbkjdMc(xmdm[0]);
				String message = "该项目与["+bkjdmc+"]不可兼得！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		/*用户*/
		User user = getUser(request);
		model.setSqr(user.getUserName());
		/*批量保存*/
		boolean result = service.saveXmsq(xmdm, model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 项目申请保存checkRs,验证人数
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-16 下午03:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	private HashMap<String, String> checkRs (XmsqForm model)throws Exception{
		/*参数设置信息*/
		CsszDao csszDao = new CsszDao();
		CsszForm csszModel = csszDao.getModel();
		/*获取参数设置的学年，塞进model*/
		model.setXn(csszModel.getXn());
		String isXmrsOut = "false";
		String rsOutMsg = "";
		HashMap<String, String> checkMap = new HashMap<String, String>();
		/*根据项目代码查询记录*/
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> xmMap = xmwhService.getDataById(model.getXmdm());
		/*批量获取奖项信息、学生信息*/
		HashMap<String, String> xsxxMap = service.getPjxmXsxxMap(model);
		/*获取金额上线项目表数据{xg_pjpy_new_jesxxmb}*/
		List<String> xzjxList = service.getXzjx();
		/*查询金额上线项目表里的项目是否包含项目代码表里的数据*/
		if (xzjxList.contains(xmMap.get("xmmc"))) {
			/*项目已申请人数*/
			String xmxx = xmMap.get("xmdm")+xsxxMap.get("xydm");
			String ysqrs = service.getYsqXs(xmxx);
			/*项目人数上限*/
			String rssx = service.getPjxmRsxxsx(xmxx);
			if (Integer.parseInt(ysqrs) + 1 > Integer.parseInt(rssx)) {
				isXmrsOut = "true";
				rsOutMsg += xmMap.get("xmmc") + " 超过人数上限" + rssx + "人<br/>";
			}
		}
		checkMap.put("isXmrsOut", isXmrsOut);
		checkMap.put("rsOutMsg", rsOutMsg);
		return checkMap;
	}
	
	/**
	 * @描述: 项目申请修改页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 下午01:45:53
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
	@SystemLog(description = "访问新评奖评优-我的评奖-奖项申请-修改页面")
	public ActionForward xmsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm xmsqForm = (XmsqForm)form;
		XmsqForm model = service.getModel(xmsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(xmsqForm, StringUtils.formatData(model));
			/*学生基本信息*/
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*根据学号查询学生参评基本信息*/
			HashMap<String, String> cpbjxx = service.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*评奖项目信息*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		}
		request.setAttribute("jbxxList", jbxxList);	
		return mapping.findForward("xmsqUpdate");
	}
	
	/**
	 * @描述: 修改保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2017-5-19 下午03:11:07
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
	@SystemLog(description="访问新评奖评优-我的评奖-荣誉申请-保存修改-项目代码:{xmdm},外语水平:{wysp},宿舍电话:{ssdh}")
	public ActionForward saveJxsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		TjszService tjszService = new TjszService();
		boolean result = false;
		if("submit".equalsIgnoreCase(model.getType())||"tj".equalsIgnoreCase(model.getType())){
			/*验证人数*/
			HashMap<String,String> checkMap = checkRs(model);
			if("true".equals(checkMap.get("isXmrsOut"))){
				response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
				return null;
			}
			
			/*学生申请提交*/ 
			if("tj".equalsIgnoreCase(model.getType())){
				String values = request.getParameter("values");
				model.setId(values);
			}
			
			/*审核状态【已审核】*/
			model.setShzt(Constants.YW_SHZ);
			
			/*======验证条件Begin======*/
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(), model.getXh());
			/*校验评奖条件，返回不符合条件的项目名称。*/
			CheckCondition checkCondition = new CheckStudentCondition();
			/*是否满足全部评奖条件*/
			boolean conditionsAllOk = checkCondition.checkConditionBoolean(model.getXh(), conditions);
			if(!conditionsAllOk){
				response.getWriter().print(getJsonMessage("不符合条件！请确认！"));
				return null;
			}
			/*======验证条件End======*/
			
			result = service.runUpdate(model);
			
			XmwhDao xmwhDao = new XmwhDao();
			XmwhForm xmwhModel = xmwhDao.getModel(model.getXmdm());
			/*获取审核流程*/
			String splc = xmwhModel.getShlc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getId(), splc,model.getXh(),"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
			}
		}else{
			 result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 删除
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-18 上午10:27:14
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-删除VALUES：{values}")
	public ActionForward xmsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*获取值*/
		String values = request.getParameter("values");
		/*空判断*/
		if(!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			/*抛出对象，已经在JS控制了*/
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述: 查看
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-18 下午12:22:28
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
	@SystemLog(description="访问新评奖评优-我的评奖-奖项申请-查看")
	public ActionForward xmsqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XmsqForm xmsqForm = (XmsqForm)form;
		XmsqForm model = service.getModel(xmsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(xmsqForm, StringUtils.formatBean(model));
			/*学生基本信息*/
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*根据学号查询学生的参评班级*/
			HashMap<String, String> cpbjxx = service.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*评奖项目信息*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			/*审核记录列表*/
			List<HashMap<String,String>> spjlList = service.getSpjlList(model.getId());
			request.setAttribute("spjlList", spjlList);
			/*审核记录*/
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
			//校验条件，返回校验结果
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
			request.setAttribute("checkResult", results);
			
			request.setAttribute("mkxxForm", StringUtils.formatData(model));
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xmsqView");
	}
	
	
	/**
	 * @描述: 项目申请提交
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-22 上午09:09:14
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-批量提交申请-VALUES:{values}")
	public ActionForward xmsqSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		TjszService tjszService = new TjszService();
		ShlcInterface shlcInterface = new CommShlcImpl();
		boolean result = true;
		/*人数超过上限*/
		boolean isXmrsOut = false;
		String rsOutMsg = "";
		/*获取值*/
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			int okNum = 0;
			/*不符合条件的学生*/
			StringBuilder notOkStu = new StringBuilder();
			HashMap<String, List<HashMap<String,String>>> notOkStuMap = new HashMap<String, List<HashMap<String,String>>>();
			/*根据申请信息的id获取学生评奖信息：id、xmdm、xh、xm、xmmc、shlc*/
			List<HashMap<String,String>> dataList = service.getPjxmXsxxList(values.split(","));
			/*提交的各学院各项目人数，包括{未提交、已提交}*/
			List<HashMap<String,String>> xmrsList = service.getPjxmRsxx(values.split(","));
			/*查询奖学金金额上限*/
			List<String> xzjxList = service.getXzjx();
			String[] xmArr = new String[xmrsList.size()];
			for (int i = 0; i < xmrsList.size(); i++) {
				xmArr[i]=xmrsList.get(i).get("xmdm")+xmrsList.get(i).get("xydm");
			}
			
			for (int i = 0; i < xmrsList.size(); i++) {
				/*提交人数*/
				if(xzjxList.contains(xmrsList.get(i).get("xmmc"))){
					String xmtjrs = xmrsList.get(i).get("xmtjrs");
					/*项目已申请人数*/
					String ysqrs = service.getYsqXs(xmArr[i]);
					/*项目人数上限*/
					String rssx = service.getPjxmRsxxsx(xmArr[i]);
					if(Integer.parseInt(ysqrs)+Integer.parseInt(xmtjrs)>Integer.parseInt(rssx)){
						isXmrsOut=true;
						rsOutMsg+=xmrsList.get(i).get("xmmc")+" 超过人数上限"+rssx+"人<br/>";
					}
				}
			}
			
			if(isXmrsOut){
				response.getWriter().print(getJsonMessage(rsOutMsg));
				return null;
			}
			
			/*查询提交项目人数*/
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> dataMap = dataList.get(i);
				String id = dataMap.get("id");
				String xmdm = dataMap.get("xmdm");
				String xmmc = dataMap.get("xmmc");
				String xh = dataMap.get("xh");
				String xm = dataMap.get("xm");
				String splc = dataMap.get("shlc");
				
				/*========== 验证评奖条件 begin ============*/
				List<HashMap<String, String>> conditions = tjszService.getTjszGl(xmdm, xh);
				/*校验评奖条件，返回不符合条件的项目名称。*/
				CheckCondition checkCondition = new CheckStudentCondition();
				/*是否满足全部评奖条件*/
				boolean conditionsAllOk = checkCondition.checkConditionBoolean(xh, conditions);
				/*========== 验证评奖条件 end ============*/
				/*========== 不可兼得奖项 begin ============*/
				List<HashMap<String, String>> bjdxmList = service.getBjdxm(xmdm);
				if(conditionsAllOk && bjdxmList.size() > 0){
					/*=========== 已申请XX，不能再申请该奖项 begin ==========*/
					Map<String,Object> xmsqInfoMap = service.getXmsqInfoList(xh);
					List<HashMap<String, String>> ysqList = (List<HashMap<String, String>>) xmsqInfoMap.get("ysqList");
					if(ysqList != null && ysqList.size() > 0){
						for (HashMap<String, String> bjdxmMap : bjdxmList) {
							for (HashMap<String, String> ysqMap : ysqList) {
								if(bjdxmMap.get("xmdm").equals(ysqMap.get("xmdm"))){
									conditionsAllOk = false;
									break;
								}
							}
							if(!conditionsAllOk){
								break;
							}
						}
					}
					/*=========== 已申请XX，不能再申请该奖项 end ==========*/
					/*=========== 该奖项与XX不能同时申请 begin ==========*/
					if(conditionsAllOk){
						for (int j = i + 1; j < dataList.size(); j++) {
							HashMap<String, String> dataJ = dataList.get(j);
							String xhJ = dataJ.get("xh");
							String xmdmJ = dataJ.get("xmdm");
							if(xh.equals(xhJ)){
								for (HashMap<String, String> bjdxmMap : bjdxmList) {
									if(bjdxmMap.get("xmdm").equals(xmdmJ)){
										conditionsAllOk = false;
										break;
									}
								}
								if(!conditionsAllOk){
									break;
								}
							}
						}
					}
					/*=========== 该奖项与XX不能同时申请 end ==========*/
				}
				/*========== 不可兼得奖项 end ============*/
				
				
				if(conditionsAllOk){
					model.setId(id);
					model.setShzt(Constants.YW_SHZ);
					result = service.runUpdate(model);
					if (result) {
						result = shlcInterface.runSubmit(id,splc,xh,"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
					}
					if (result) {
						okNum++;
					}
				}else{
					HashMap<String,String> mapTemp = new HashMap<String,String>();
					mapTemp.put("xh", xh);
					mapTemp.put("xm", xm);
					mapTemp.put("xmmc", xmmc);
					List<HashMap<String,String>> listTemp = notOkStuMap.get(xmdm);
					if(listTemp == null){
						listTemp = new ArrayList<HashMap<String,String>>();
						notOkStuMap.put(xmdm, listTemp);
					}
					listTemp.add(mapTemp);
				}
			}
			
			Set<String> keys = notOkStuMap.keySet();
			for(String key : keys){
				List<HashMap<String,String>> temp = notOkStuMap.get(key);
				for (int i = 0; i < temp.size(); i++) {
					if(i == 0){
						notOkStu.append(temp.get(0).get("xmmc") + "：<br/>");
					}
					notOkStu.append(temp.get(i).get("xh") + " " + temp.get(i).get("xm") + "<br/>");
				}
			}
			String resultMsg = "提交成功"+okNum+"条！";
			if(dataList.size() - okNum > 0){
				resultMsg += "不符合条件的记录：<br/>" + notOkStu.toString().substring(0, notOkStu.toString().length() - 1);
			}
			String message = result ? resultMsg : MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	
	/**
	 * @描述: 项目申请撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 下午04:35:32
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-撤销VALUES：{values}")
	public ActionForward xmsqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		result = service.cancleRecord(values,lcid);
		if(result){
			/*更新业务状态为【未提交】*/
			XmsqForm model = new XmsqForm();
			model.setId(values);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 导出
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-5-19 下午05:44:58
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-导出")
	public ActionForward xmsqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmsqForm model = (XmsqForm)form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);
		/*导出功能代码*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*当前操作员*/
		exportModel.setZgh(user.getUserName());
		/*设置数据*/
		exportModel.setDataList(resultList);
		/*设置当前导出功能编号*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*生成导出文件*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述: 单个打印申请登记表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-13 上午11:49:08
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-下载登记表")
	public ActionForward getWordForid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XmsqForm xmsqForm = (XmsqForm)form;
		XmsqService service = new XmsqService();
		XmsqForm model = service.getModel(xmsqForm);
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 批量打印申请登记表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-14 上午08:33:56
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
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请-批量下载登记表")
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String value = request.getParameter("value");
		XmsqService service = new XmsqService();
		XmsqForm model = null;
		XmsqForm modelForm = null;
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				modelForm = new XmsqForm();
				modelForm.setId(values[i]);
				model = service.getModel(modelForm);
				File file = getWord(model);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @描述: 填充模版数据生成word文件
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-14 上午09:03:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmsqForm
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getWord(XmsqForm xmsqForm) throws Exception {
		
		/**定义一些需要用的*/
		Map<String,Object> data = new HashMap<String,Object>();
		XmsqForm model = xmsqForm;
		XmsqService service = new XmsqService();
		PjjgService pjjgService = new PjjgService();
		
		/**从model中获取学号，方便下面使用*/
		String xh = model.getXh();
		/**报表*/
		HashMap<String, String> bbMap = null;
		
		/**当model不为空的时候，在里面进行操作*/
		if(model != null){
			
			/*获取选取数据的项目代码*/
			String xmdm = model.getXmdm();
			/*判断xmdm是否为空*/
			if(StringUtils.isNull(xmdm)){
				throw new SystemException("当前项目代码为空，不允许下载登记表！");
			}
			
			/**取报表信息*/
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				/*根据项目名称和学年查询记录详细信息*/
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn());
				/*判断是或否有项目不存在*/
				if(xmMap != null){
					model.setXmdm(xmMap.get("xmdm"));
					/*因为方法和表都一样，new以前的BbwhService就可以了！*/
					BbwhService bbwhService = new BbwhService();
					/*取对应的登记表*/
					bbMap = bbwhService.getDataById(xmMap.get("djb"));
				}
			}
			
			if(null == bbMap || 0 == bbMap.size()){
				/*查询不到相关联报表信息*/
				throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
			}
			
			/**根据学号查询学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.put("rs", xsjbxx);
			/*出生日期年月，例如：2017年01月*/
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));
			/*出生日期年月，例如：2017.01*/
			String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
			xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));
			/*入学日期年月，例如：2017年12月*/
			xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));

			//党团员
			String zzmmmc = xsjbxx.get("zzmmmc");
			if(zzmmmc != null){
				if (zzmmmc.equals("中国共产党党员") || zzmmmc.equals("中国共产党预备党员")) {
					data.put("dty", "党员");
				}else if(zzmmmc.equals("中国共产主义青年团团员")){
					data.put("dty", "团员");
				}
			}

			/**年级专业人数*/
			String njzyrs = service.getNjzyrs(xsjbxx.get("nj"),xsjbxx.get("zydm"));
			data.put("njzyrs",njzyrs);

			/**学生照片信息*/
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			data.put("photo", photo);
			
			/**分解身份证号begin*/
			String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
			for (int i = 0,j = xssfzh.length(); i < j; i++) {
				xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
			}
			
			/**form里面的值*/
			/*勾选项目的申请理由*/
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));
			data.put("grxxjl",HtmlUtil.xmlZy(model.getGrxxjl()));
			data.put("cjkyqk",HtmlUtil.xmlZy(model.getCjkyqk()));
			data.put("dwrs",HtmlUtil.xmlZy(model.getDwrs()));

			/*勾选项目的项目名称*/
			data.put("xmmc", model.getXmmc());
			/*勾选项目的申请学年*/
			data.put("currXn", model.getXn());
			/*勾选项目的所有信息*/
			data.put("xmxx", model);
			/*当前学校名称*/
			data.put("xxmc", Base.xxmc);
			
			/**当前年、月、日*/
			/*当前年,例如：2017*/
			data.put("currY", DateUtils.getYear());
			/*当前月,例如：02*/
			data.put("currM", DateUtils.getMonth());
			/*当前日,例如：01*/
			data.put("currD",DateUtils.getDayOfMonth());
			
			/**担任职务*/
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			
			/**加载学生家庭基本信息*/
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			data.put("jtqk", jtqkmodel);//家庭情况
			
			/**加载家庭成员信息*/
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
			data.put("jtcyxxList", jtcyxxList);
			int size=(5 - jtcyxxList.size())<0?0:(5 - jtcyxxList.size());
			data.put("blankList", getBlankList(size));
			
			/**困难生认定档信息*/
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
			/*认定档次*/
			data.put("rddc", knsjg.get("rddc") == null ? "" : knsjg.get("rddc"));
			
			/**困难生认定档次，【困难】、【特困】*/
			KnsdcService knsdcService = new KnsdcService();
			data.put("knsdcList", knsdcService.getKnsdcList());
			
			/**项目性质循环（目前还没发现浙大用，通用转过来的）*/
			data.put("xmxzmc", "");
			XmwhService xmwhService = new XmwhService();
			List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
			for (HashMap<String, String> xmxzMap : xmxzList) {
				if (xmxzMap.get("dm").equals(model.getXzdm())){
					data.put("xmxzmc", xmxzMap.get("mc"));
					break;
				}
			}
			
			/**综测分*/
			ZcwhService zcwhService = new ZcwhService();
			HashMap<String, String> zcf = zcwhService.getZczfByXh(xh, model.getXn());
			data.put("zcf", zcf);
			
			/**按照学号加载学生学年成绩*/
			List<HashMap<String,String>> cjList = xsxxService.getCjList(xh,model.getXn(),model.getXq());
			/*格式化数据*/
			cjList = pjjgService.formatForDjb(cjList);
			data.put("cjList", cjList);
			
			/**获取平均分数*/
			String pjfs = pjjgService.getAverage(cjList);
			xsjbxx.put("pjfs",pjfs );
			
			/**获取不及格成绩数量*/
			String bjgcjs = pjjgService.getBjgcjNum(xh, model.getXn(), model.getXq());
			xsjbxx.put("bjgcjs",bjgcjs );

			/**评奖日期，奖项名称，颁奖单位用的太多，拉出来通用*/
			List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
			pjjgService.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
			data.put("pjjgListhjqk", pjjgListhjqk);
			int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
			data.put("blankListhjqk", getBlankList(size1));
			
			String[] xnArr = model.getXn().split("-");
			if(xsjbxx.get("csrq")!=null){
				String[] csArr = xsjbxx.get("csrq").split("\\D");
				if(csArr != null&&csArr.length == 3){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
					data.put("csr1", csArr[2]);
				}else if (csArr != null&&csArr.length ==2){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
				}else if (csArr != null&&csArr.length ==1){
					data.put("csn1", csArr[0]);
				}
				if(xnArr.length == 2){
					data.put("qsxn", xnArr[0]);
					data.put("zhxn", xnArr[1]);
				}else if (xnArr.length == 1){
					data.put("qsxn", xnArr[0]);
				}
				if(csArr != null&&csArr.length == 2){
					data.put("csn", csArr[0]);
					data.put("csy", csArr[1]);
				}else if (csArr != null&&csArr.length == 1){
					data.put("csn", csArr[0]);
				}
			}

			//学年拼接，拆分
			int uqsnx = Integer.parseInt(xnArr[0])-1;//such as:2016->2015
			int uzhxn = Integer.parseInt(xnArr[1])-1;//such as:2017->2016
			StringBuilder usxn = new StringBuilder();
			String upsxn1 = usxn.append(uqsnx+"-"+uzhxn).toString();//such as:2016-2017 ->2015-2016
			String sxnhlw = xnArr[0].substring(xnArr[0].lastIndexOf("/")+3, xnArr[0].lastIndexOf("/")+5);//such as:2016->16
			String zxnhlw = xnArr[1].substring(xnArr[1].lastIndexOf("/")+3, xnArr[1].lastIndexOf("/")+5);//such as:2017->17
			data.put("sxnhlw", sxnhlw);
			data.put("zxnhlw", zxnhlw);
			int ssxnhlw = Integer.parseInt(sxnhlw)-1;//such as:16->15
			int zxxnhlw = Integer.parseInt(zxnhlw)-1;//such as:17->16
			data.put("upqsxn", ssxnhlw);
			data.put("upzhxn", zxxnhlw);
			
			/**班级人数*/
			String bjrs = service.getBjrs(xsjbxx.get("bjdm"), model.getXn());
			xsjbxx.put("bjrs", bjrs);		
			
			/**审核意见信息*/
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getId());
			data.put("yjshyj", spxxInfo.get("yjshyj"));
			data.put("ejshyj", spxxInfo.get("ejshyj"));
			data.put("sjshyj", spxxInfo.get("sjshyj"));
			
			/**根据学号查询*/
			List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoMapForDjb(xh);
			data.put("pjjg", pjjg);
			List<HashMap<String, String>> pjjgAll = pjjgService.getPjpyInfoList(xh);
			data.put("pjjgAll",pjjgAll);
			
			/**查询成绩信息*/
			HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
			if(xxcjMap == null){
				xxcjMap = new HashMap<String,String>();
			}
			data.put("xxcjMap", xxcjMap);
			
			/**输出文件格式及数据*/
			File wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			return wordFile;
		}
		return null;
	}

	/**
	 * @描述: 输出空list
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-14 下午06:07:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param size
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBlankList(int size){
			
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
}
