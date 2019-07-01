/**

 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq;

import java.io.File;
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
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgDao;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @作者： 张昌路[工号:982]
 * @时间： 2013-10-25 上午10:37:06
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DtxxsqAction extends SuperAction {
	//从基本信息表中获取学生信息
	private static final String XSXXPZ = "dtxxXsxxpz";
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String SUBMIT = "submit";
	
	private static final String url = "dtxxsqbase.do";

	/**
	 * 
	 * @描述:列表查询
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-25 上午10:37:23
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("dtxxsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "dtxxsqbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dtxxsqlb");
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-10-24 下午05:17:35
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if (null == mess || mess.length != 2) {
				String message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", mess[0]);
			map.put("nodel", mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述:修改
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-25 上午10:38:19
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
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		DtxxsqForm model = service.getModel(myForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService
					.getXsjbxxMoreForDtgl(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType()) ||SUBMIT.equalsIgnoreCase(myForm.getType())) {

			DtxxsqForm modelGet = service.getModel(myForm.getDtxxsqid());			
			
			// 已退回的记录取老的审核流程ID;非已退回记录则再去取审核流程
			if(!Constants.YW_YTH.equals(modelGet.getShzt())){

				ShlcpzService shlcService = new ShlcpzService();
				ShlcpzForm shlcpz = new ShlcpzForm();
				shlcpz.setJddm(modelGet.getJddm());
				shlcpz = shlcService.getModel(shlcpz);
				if(shlcpz!=null){
					myForm.setSplc(shlcpz.getSplc());
				}
			}else{
				myForm.setJddm(modelGet.getJddm());
				myForm.setSplc(modelGet.getSplc());
				//　保存，则审核状态仍未：退回
				if(SAVE.equalsIgnoreCase(myForm.getType())){
					myForm.setShzt(Constants.YW_YTH);
				}
			}

			
			boolean result = service.update(myForm);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
							: MessageKey.SYS_SUBMIT_FAIL;
			}else{
				 messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
			}
			
			
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		// 学生基本信息
		String path = "dtxxsq.do?method=update";
		request.setAttribute("path", path);
		
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XSXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		
		// 阶段信息
		List<HashMap<String, String>> sqJdlist = null;
		
		// 已退回
		if(Constants.YW_YTH.equals(model.getShzt())){

			// 阶段信息
			sqJdlist = service.getSqJdList(myForm.getXh(),model.getJddm());
		}else{
			// 阶段信息
			sqJdlist = service.getSqJdList(myForm.getXh(),"");
		}
		request.setAttribute("sqJdlist", sqJdlist);
		
		return mapping.findForward("dtxxsqxg");
	}

	/**
	 * 
	 * @描述:增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-23 上午10:44:10
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService
			.getXsjbxxMoreForDtgl(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())||SUBMIT.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
						: MessageKey.SYS_SUBMIT_FAIL;
			}else{
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			}
			
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> sqJdlist = service.getSqJdList(myForm.getXh(),"");
		request.setAttribute("sqJdlist", sqJdlist);
		// get student detail
		// 学生基本信息
		String path = "dtxxsq.do?method=add";
		request.setAttribute("path", path);

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XSXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("dtxxsqzj");
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitDtxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm model = (DtxxsqForm) form;
		User user = getUser(request);
		String dtxxsqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		String splc = request.getParameter("splc");
		model.setDtxxsqid(dtxxsqid);
		model.setXh(xh);
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm t = service.getModel(dtxxsqid);
		String jdmc = service.getJdmc(t.getJddm());
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService
				.getXsjbxxMoreForDtgl(xh);
		int age = service.getAge(xsjbxx.get("csrq"));
		if(StringUtils.equals(jdmc,"入党积极分子") && age<18){
			String message ="提交失败，入党积极分子申请必须年满18岁！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if(StringUtils.equals(jdmc,"预备党员") && age<19){
			String message ="提交失败，预备党员申请必须年满19岁！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.submitDtxxsq(model);
    	String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(撤销党团信息申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-9 上午10:07:32
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
	public ActionForward cancelDtxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		DtxxsqService service = new DtxxsqService();
		String dtxxsqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(dtxxsqid,lcid);
		if(result){
			//更新业务状态为'未提交'
			DtxxsqForm model = new DtxxsqForm();
			model.setDtxxsqid(dtxxsqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(dtxxsqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateDtxxsq(model);
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
	 * @日期：2013-10-24 下午05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		DtxxsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService
					.getXsjbxxMoreForDtgl(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XSXXPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dtxxsqck");
	}
	
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqService service = new DtxxsqService();
		DtxxsqForm myForm = (DtxxsqForm) form;
		// 是否可以增加
		boolean result = service.isCanAdd(myForm);
		Map<String, String> map = new HashMap<String, String>();
		if (!result) {// 不可以
			map.put("success", "false");
			map.put("message", "该学生已有党团申请正在审核流程中!");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			/*response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));*/
		} else {

			//西交大 学生年龄验证
			XsxxService xsxxService = new XsxxService();
			map.put("success", "true");
			String jdmc = service.getJdmc(myForm.getJddm());
			HashMap<String, String> xsjbxx = xsxxService
					.getXsjbxxMoreForDtgl(myForm.getXh());
			int age = service.getAge(xsjbxx.get("csrq"));
			if(StringUtils.equals(jdmc,"入党积极分子") && age<18){
				map.put("success", "false");
				map.put("message", "入党积极分子申请必须年满18岁！");
			}
			if(StringUtils.equals(jdmc,"预备党员") && age<19){
				map.put("success", "false");
				map.put("message", "预备党员申请必须年满19岁！");
			}

			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	} 
	/**
	 * 
	 * @描述:导出
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-25 上午10:43:40
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm model = (DtxxsqForm) form;
		DtxxsqService service = new DtxxsqService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述: 验证是否可提交
	 * @作者：qilm
	 * @日期：2014-5-26
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

		DtxxsqForm model = (DtxxsqForm) form;
		DtxxsqService service = new DtxxsqService();
		String jddm = request.getParameter("jddm");
		model.setJddm(jddm);
		
		// 取得是否存在验证(根据异动类别) true:可提交/false：不可提交
		boolean isSfktj = service.checkSfktj(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	
	/**
	 * 
	 * @描述: 入团申请表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午11:27:19
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
	public ActionForward getRtsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm myForm = (DtxxsqForm) form;
		File wordFile = getWord(myForm,request);		
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 获取word模板
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午11:27:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getWord(DtxxsqForm myForm,HttpServletRequest request) throws Exception{
		DtxxsqService service = new DtxxsqService();
		
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsjbxxMap = service.getXsjbxxMap(myForm.getDtxxsqid(),myForm.getFlag());
		String xh = xsjbxxMap.get("xh");
//		data.put("xyzsjg", myForm);
		data.putAll(xsjbxxMap);
		List<HashMap<String,String>> pjjgList = service.getPjjgList(xh); 
		List<HashMap<String,String>> wjcfList = service.getWjcfList(xh);
		data.put("pjjgList", pjjgList);
		data.put("wjcfList", wjcfList);
		String bjrs = new CxddJgDao().getBjrs(xsjbxxMap.get("bjdm"));
		data.put("bjrs",bjrs);
	
		//当前申请时间
		String rq = GetTime.getTimeByFormat("yyyy-mm-dd");
		String[] xnArr = rq.split("-");
		data.put("nowTime", xnArr[0]+"年"+xnArr[1]+"月"+xnArr[2]+"日");

		
		//职务
		HashMap<String, String> zw = service.getZwmc(xh);
		data.put("zwmc", zw.get("zwmc"));
		//有无不及格课程
		HashMap<String, String> bjgkcs = service.getBjgkc(xh);	
		String bjgkc = bjgkcs.get("num");
		if("0".equals(bjgkc)){ // 本学年有无不及格课程
			data.put("bjgkc", "无");
		}else{
			data.put("bjgkc", "有");
		}
		
		String dyxq = "01";
		String dexq = "02";
		//如果当前学期是第一学期
		if(dyxq.equals(Base.currXq)){
			//取上学年第一学期的综测分
			List<HashMap<String,String>> zcfList1 = service.getZcfListByXh(myForm.getXh(),Base.beforXn, dyxq);
			for (HashMap<String, String> zcfMap : zcfList1) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("德育")){
					data.put("dypm_01", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("综测总分")){
					data.put("zhpm_01", zcfMap.get("bjpm"));
				}
			}
			List<HashMap<String,String>> zcfList2 = service.getZcfListByXh(myForm.getXh(),Base.beforXn, dexq);
			for (HashMap<String, String> zcfMap : zcfList2) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("德育")){
					data.put("dypm_02", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("综测总分")){
					data.put("zhpm_02", zcfMap.get("bjpm"));
				}
			}
		}
		//如果当前学期是第二学期
		if(dexq.equals(Base.currXq)){
			//取上学年第二学期成绩
			List<HashMap<String,String>> zcfList1 = service.getZcfListByXh(myForm.getXh(),Base.beforXn, dexq);
			for (HashMap<String, String> zcfMap : zcfList1) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("德育")){
					data.put("dypm_01", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("综测总分")){
					data.put("zhpm_01", zcfMap.get("bjpm"));
				}
			}
			List<HashMap<String,String>> zcfList2 = service.getZcfListByXh(myForm.getXh(),Base.currXn, dyxq);
			for (HashMap<String, String> zcfMap : zcfList2) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("德育")){
					data.put("dypm_02", zcfMap.get("bjpm"));
				}
				if(xmmc.contains("综测总分")){
					data.put("zhpm_02", zcfMap.get("bjpm"));
				}
			}
		}
		if("入团申请".equals(myForm.getJdmc())){
			try{
				ResourceUtils.getFile(Constants.TEP_DIR+"dtxx/rtsqb.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"dtxx","rtsqb.xml",FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_团员发展申请表"));
			}catch (Exception e) {
				file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"dtxx","rtsqb.xml", FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_团员发展申请表"));
			}
		}else{
			try{
				ResourceUtils.getFile(Constants.TEP_DIR+"dtxx/tyrd.xml");
				file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"dtxx","tyrd.xml",FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_入党积极分子推荐表"));
			}catch (Exception e) {
				file = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR+"dtxx","tyrd.xml", FreeMarkerUtil.getFileName(xsjbxxMap.get("xm")+"_入党积极分子推荐表"));
			}
		}
		return file;
	}
	
	/**
	 * 
	 * @描述: 入团申请表zip
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午11:41:18
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
	public ActionForward getRtsqbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxsqForm myForm = (DtxxsqForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setDtxxsqid(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
}
