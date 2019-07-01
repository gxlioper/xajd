/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-3 下午05:01:26 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.rysq;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖_荣誉申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-7-3 下午05:01:26 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RysqAction extends SuperAction{
	private static final String url = "xpjpy_wdpj_rysq.do";
	private static final String PJPY = "pjpy";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
		jbxxList = bdpzService.getJbxxpz(PJPY);
	}
	private RysqService service = new RysqService();
	
	/**
	 * @描述: 荣誉申请回显页面
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-3 下午05:38:53
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
	@SystemLog(description = "访问新评奖评优-我的评奖-荣誉申请-查询页面")
	public ActionForward getRysqList(ActionMapping mapping, ActionForm form,
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
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rysqList");
	}
	
	/**
	 * @描述: 查询列表返回Json数据
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-3 下午08:27:31
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
	@SystemLog(description = "访问新评奖评优-我的评奖-荣誉申请-查询页面Json数据")
	public ActionForward getRysqListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RysqForm model = (RysqForm)form;
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
	 * @描述: 增加
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 上午09:28:08
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
	@SystemLog(description = "访问新评奖评优-我的评奖-荣誉申请-增加页面")
	public ActionForward rysqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RysqForm model = (RysqForm)form;
		/*用户对象*/
		User user = getUser(request);
		
		/*如果是学生用户取登录用户，如果不是学生用户进行学号选择*/
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
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
		String path = "xpjpy_rysq.do?method=rysqAdd";
		request.setAttribute("path", path);
		return mapping.findForward("rysqAdd");
	}
	
	
	/**
	 * @描述: 荣誉申请_“自动加载开放的荣誉”或者“点击选择申请荣誉称号”
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 上午11:54:32
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
	@SystemLog(description = "访问新评奖评优-我的评奖-荣誉申请-荣誉选择")
	public ActionForward selectRyxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm model = (RysqForm)form;
		Map<String,Object> resultMap = service.getRysqInfoList(model.getXh());
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("selectRyxm");
	}
	
	
	/**
	 * @描述: 荣誉申请保存、提交
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午04:19:44
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
	@SystemLog(description="访问评奖评优-我的评奖-荣誉申请-保存项目代码:{xmdms},学号:{xh},外语水平:{wysp},宿舍电话:{ssdh},担任社会工作职务:{gzzw}")
	public ActionForward saveRysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm model = (RysqForm)form;
		String[] xmdm = request.getParameterValues("xmdms");
		/*验证人数*/
		if("submit".equals(model.getType())){
			HashMap<String,String> checkMap = new HashMap<String,String>();
			for (int i = 0; i < xmdm.length; i++) {
				model.setXmdm(xmdm[i]);
				checkMap = checkRs(model);
			}
			if("true".equals(checkMap.get("isXmrsOut"))){
				response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
				return null;
			}
		}
		/*用户*/
		User user = getUser(request);
		model.setSqr(user.getUserName());
		/*批量保存*/
		boolean result = service.saveRysq(xmdm, model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 荣誉申请保存checkRs,验证人数
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午04:30:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	@SystemLog(description="访问评奖评优-我的评奖-荣誉申请-保存验证人数")
	private HashMap<String, String> checkRs (RysqForm model)throws Exception{
		XmsqService xmsqService = new XmsqService();
		/*参数设置信息*/
		CsszDao csszDao = new CsszDao();
		CsszForm csszModel = csszDao.getModel();
		/*获取参数设置的学年，塞进model*/
		model.setXn(csszModel.getXn());
		String isXmrsOut = "false";
		String rsOutMsg = "";
		HashMap<String, String> checkMap = new HashMap<String, String>();
		/*根据项目代码查询1条记录*/
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> xmMap = xmwhService.getDataById(model.getXmdm());
		/*获取1个学生的学号、学院代码*/
		HashMap<String, String> xsxxMap = service.getPjxmXsxxMap(model);
		
		/* 因为这里是申请荣誉，和奖项申请稍有不同，查询已设置人数的荣誉称号项目
		 * 1、本学年评奖项目
		 * 2、且是荣誉称号
		 * 3、在人数设置表里面的zzme不为空*/
		List<String> rychList = service.getRychList(model);
		if (rychList.contains(xmMap.get("xmmc"))) {
			/*学生申请的xmdm和学生自身的xydm拼接作为条件，xmdm+xydm*/
			String xmxx = xmMap.get("xmdm") + xsxxMap.get("xydm");
			/*项目已申请人数*/
			String ysqrs = xmsqService.getYsqXs(xmxx);
			/*项目人数上限*/
			String rssx = xmsqService.getPjxmRsxxsx(xmxx);
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
	 * @描述: 荣誉申请修改
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-6 上午09:42:34
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
	@SystemLog(description = "访问新评奖评优-我的评奖-荣誉申请-修改页面")
	public ActionForward rysqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm rysqForm = (RysqForm)form;
		RysqForm model = service.getModel(rysqForm);
		XmsqService xmsqServcie = new XmsqService();
		
		if(model != null){
			BeanUtils.copyProperties(rysqForm, StringUtils.formatData(model));
			/*学生基本信息*/
			HashMap<String,String> xsjbxx = xmsqServcie.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*根据学号查询学生参评基本信息*/
			HashMap<String, String> cpbjxx = xmsqServcie.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*评奖项目信息*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		}
		request.setAttribute("jbxxList", jbxxList);	
		return mapping.findForward("rysqUpdate");
	}
	
	/**
	 * @描述: 修改保存
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-7 下午02:47:16
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
	public ActionForward saveRysqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		RysqForm model = (RysqForm)form;
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
			if(result){
				result = shlc.runSubmit(model.getId(), splc,model.getXh(),"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
			}
		}else{
			 result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if(!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 荣誉申请删除
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-10 上午09:37:12
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
	@SystemLog(description="访问新评奖评优-我的评奖-荣誉申请-删除VALUES：{values}")
	public ActionForward rysqDelete(ActionMapping mapping, ActionForm form,
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
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-10 上午09:54:24
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
	@SystemLog(description="访问新评奖评优-我的评奖-荣誉申请-查看")
	public ActionForward rysqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm rysqForm = (RysqForm)form;
		RysqForm model = service.getModel(rysqForm);
		if(model != null){
			XmsqService xmsqService = new XmsqService();
			BeanUtils.copyProperties(rysqForm, StringUtils.formatBean(model));
			/*学生基本信息*/
			HashMap<String,String> xsjbxx = xmsqService.getPjInfo(model.getXh(), model.getXn());
			request.setAttribute("jbxx", xsjbxx);
			/*根据学号查询学生参评基本信息*/
			HashMap<String, String> cpbjxx = xmsqService.getCpbjListByXh(model.getXh(), model.getXn());
			request.setAttribute("cpbjxx", cpbjxx);
			/*评奖项目信息*/
			XmwhService xmmwService = new XmwhService();
			XmwhForm xmwhModel = xmmwService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			/*审核记录列表*/
			List<HashMap<String,String>> spjlList = xmsqService.getSpjlList(model.getId());
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
		return mapping.findForward("rysqView");
	}
	
	/**
	 * @描述: 荣誉申请撤销
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-10 上午10:40:51
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
	@SystemLog(description="访问新评奖评优-我的评奖-荣誉申请-撤销VALUES：{values}")
	public ActionForward rysqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		result = service.cancleRecord(values,lcid);
		if(result){
			RysqForm model = (RysqForm)form;
			model.setId(values);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 荣誉申请导出
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-10 下午03:19:49
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
	@SystemLog(description="访问新评奖评优-我的评奖-荣誉申请-导出 ")
	public ActionForward rysqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		RysqForm model = (RysqForm)form;
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
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
}
