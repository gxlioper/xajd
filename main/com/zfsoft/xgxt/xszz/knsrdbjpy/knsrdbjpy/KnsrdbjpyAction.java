package com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy;

import java.io.File;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jglr.JglrService;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版 困难生认定 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-20 上午11:38:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdbjpyAction extends SuperAction {

	private static final String KNSRD = "knsrd";
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> mkxxList = null;
	private List<HashMap<String,String>> jbxxList = null;
	private static final String OPEN = "1";
	
	private static final String url = "xszz_knsrdbjpy_sq.do";
	
	/**
	 * 
	 * @描述:困难生申请管理
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 上午10:25:18
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
	public ActionForward knssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "基本设置未初始化，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("jcszModel", jcszModel);
		
		//基础设置认定周期不为空
		if(jcszModel != null && jcszModel.getXn() != null) {
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getXn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getXq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		//参数设置已设置
		if(jcszModel != null){
			request.setAttribute("sfysq", service.getExists(model, user.getUserName()));
		}
		
		request.setAttribute("sqzq", SQZQ);
		String path = "xszz_knsrdbjpy_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knssqManage");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定--申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 上午11:38:24
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
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		request.setAttribute("type", model.getType());
		
		if (!StringUtil.isNull(model.getXh())){
			KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
			KnsjcszbjpyForm jcszModel = jcszService.getModel();
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if(!"update".equals(model.getType())){
				model.setXn(jcszModel.getXn());
			}
			if("xn".equals(SQZQ)){
				model.setXq("on");
			}
			else{
				if(!"update".equals(model.getType())){
					model.setXq(jcszModel.getXq());
				}
			}
			KnsrdbjpyForm knsrdbjpyModel = service.getModelByXnXq(model);
			
			if (knsrdbjpyModel != null){
				BeanUtils.copyProperties(model, StringUtils.formatData(knsrdbjpyModel));
			}
			
			
			
			if (jcszModel != null && OPEN.equals(jcszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				JtqkdcForm jtqkModel = jtqkService.getModel(jtqkForm);
				request.setAttribute("openJtqk", jtqkModel == null);
			}
			request.setAttribute("ishave",service.getExists(model, model.getXh()));
		}
		//获取困难生基本设置
		KnsjcszbjpyService jcszService=new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		request.setAttribute("isopen", jcszForm.getIsopen());
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		//困难生档次列表
		KnsdcService knsdcbjpySerivce = new KnsdcService();
		request.setAttribute("knsdcbjpyList", knsdcbjpySerivce.getKnsdcList());
		//困难生认定显示配置
		mkxxList = bdpzService.getBdpz(KNSRD);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_knsrdbjpy.do?method=knssq";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("knssq");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定申请 
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 上午08:43:38
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
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-申请或修改保存-XH:{xh},SQLY:{sqly}")
	public ActionForward saveKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if(model.getType().equals("submit")){
			// 评议小组人员不能申请！
			JglrService jglrService = new JglrService();
			HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(model.getXh());
			if(bjpyxzcyMap.get("xh") != null){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		super.resetToken(request);
		boolean result = service.saveKnssq(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-提交VALUES:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyService service = new KnsrdbjpyService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String shzt = request.getParameter("shzt");
		
		if(!Constants.YW_YTH.equalsIgnoreCase(shzt)){
			KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
			KnsjcszbjpyForm jcszModel = jcszDao.getModel();
			lcid = jcszModel.getSplc();
		}
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			xh=user.getUserName();
		}
		// 评议小组人员不能申请！
		JglrService jglrService = new JglrService();
		HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(xh);
		if(bjpyxzcyMap.get("xh") != null){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
			return null;
		}
		
		KnsrdbjpyForm model = new KnsrdbjpyForm();
		model.setGuid(values);
		model.setShzt(Constants.YW_BJPYZ);
		model.setShlc(lcid);
		boolean result = service.updateModel(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-撤销VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyService service = new KnsrdbjpyService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
//		boolean result = service.cancleRecord(values,lcid);
//		if(result){
			//更新业务状态为'未提交'
			KnsrdbjpyForm model = new KnsrdbjpyForm();
			model.setGuid(values);
			ShlcDao shlcDao = new ShlcDao();
			if(Integer.valueOf(shlcDao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			model.setPddc("");
			boolean result = service.updateModel(model);
			
			KnsrdbjpyForm modelNew = service.getModel(model);
			JgcxDao jgcxDao = new JgcxDao();
			String guid = modelNew.getGuid();
			String xn = modelNew.getXn();
			String xq = modelNew.getXq();
			String sqr = modelNew.getXh();
			// 更新班级评议表
			boolean rs = jgcxDao.cxBjpyxzpy(xn, xq, sqr);
			if(rs){
				// 更新结果查询表
				JgcxForm jgcxForm = new JgcxForm();
				jgcxForm.setSqid(guid);
				jgcxForm.setTjzt("0");
				jgcxForm.setTjsj(" ");
				jgcxForm.setShzt(" ");
				jgcxDao.runUpdate(jgcxForm);
			}
//		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:删除困难生申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午01:14:49
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
	@SystemLog(description="访问学生资助-困难生认定-困难生申请-删除VALUES:{values}")
	public ActionForward delKnssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyService service = new KnsrdbjpyService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @描述:流程跟踪
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午01:53:42
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
	public ActionForward getSplcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		List<HashMap<String,String>> infoList = service.getSplcInfo(model);
		
		request.setAttribute("infoList", infoList);
		return mapping.findForward("knsrdbjpyLcgz");
	}
	
	/**
	 * 
	 * @描述:困难生认定审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 上午11:39:03
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
	public ActionForward knsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getShjlList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "基本设置未初始化，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//基础设置认定周期不为空
		if(jcszModel != null && jcszModel.getXn() != null) {
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xn(new String[]{jcszModel.getXn()});
			if("xq".equals(SQZQ)){
				searchModel.setSearch_tj_xq(new String[]{jcszModel.getXq()});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		request.setAttribute("sqzq", SQZQ);
		String path = "xszz_knsrdbjpy_sh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("knsshManage");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定--单个审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-20 上午11:39:33
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
	public ActionForward knsrdbjpyDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		String xtgwid = myForm.getXtgwid();
		KnsrdbjpyForm model = service.getModel(myForm);
		model.setShid(myForm.getShid());
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			request.setAttribute("infoList", infoList);
			BeanUtils.copyProperties(myForm, model);
		}
		
		myForm.setXtgwid(xtgwid);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		
		//获取困难生基本设置
		KnsjcszbjpyService jcszService=new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		
		KnsdcService knsdcbjpySerivce = new KnsdcService();
		
		//困难生档次列表
		request.setAttribute("knsdcbjpyList", knsdcbjpySerivce.getKnsdcList());
		//浙大个性化无偿资助金额列表
		request.setAttribute("wczzjeList", knsdcbjpySerivce.getWczzList());
		//加载困生认定记录
		request.setAttribute("rdlsjlList", new KnsjgService().getKnsInfoList(model.getXh()));
		//加载最新审核通过困难生档次
		String zd2 = service.getNewKnsdcbjpy(myForm).get("zd2");
		if(StringUtil.isNull(zd2)){
			zd2 = model.getPddc();
		}
		myForm.setRddc(zd2);
		//困难生认定显示配置
		mkxxList = bdpzService.getBdpz(KNSRD);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("knsrdbjpyDgsh");
	}
	
	

	/**
	 * 
	 * @描述:困难生认定单个审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 上午11:35:24
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
	@SystemLog(description="访问学生资助-困难生认定-困难生审核-审核保存-GUID:{guid}")
	public ActionForward saveKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) StringUtils.formatBean(form);
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		boolean isSuccess = service.saveKnssh(myForm, user);
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:撤消困难生审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 上午10:22:56
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
	@SystemLog(description="访问学生资助-困难生认定-困难生审核-撤销GUID:{guid}")
	public ActionForward cancelKnssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		//HashMap<String,String> shxx = ShlcUtil.getShxx(myForm.getShid());	
		// 业务回滚
		boolean result = service.cancelKnssh(myForm.getGuid());
		// 业务回滚成功
		//boolean isSuccess = service.cancelKnssh(myForm);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定--批量审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 上午11:41:21
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
	public ActionForward knsrdbjpyPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcService knsdcbjpySerivce = new KnsdcService();
		//困难生档次列表
		request.setAttribute("knsdcbjpyList", knsdcbjpySerivce.getKnsdcList());
		//浙大个性化无偿资助金额列表
		request.setAttribute("wczzjeList", knsdcbjpySerivce.getWczzList());
		request.setAttribute("xxdm", Base.xxdm);
		
		return mapping.findForward("knsrdbjpyPlsh");
	}
	
	
	
	/**
	 * 
	 * @描述:困难生审核--批量审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 下午02:04:34
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:困难生认定查看
	 * @作者：Penghui.Qu
	 * @日期：2013-4-27 下午02:04:07
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
	public ActionForward knsrdbjpyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		KnsrdbjpyForm model = service.getModel(myForm);
		User user = getUser(request);
//		if ("stu".equals(user.getUserType())){
//			model.setXh(user.getUserName());
//		}
		if (model != null){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			request.setAttribute("infoList", infoList);
			
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		KnsjcszbjpyService jcszService=new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());

		//困难生认定显示配置
		mkxxList = bdpzService.getBdpz(KNSRD);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		return mapping.findForward("knsrdbjpyView");
	}
	
	
	
	/**
	 * 
	 * @描述:打印困难生申请信息
	 * @作者：maxh
	 * @日期：2013-4-26 下午02:19:37
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm myForm = (KnsrdbjpyForm) form;
		String guids[]=myForm.getGuid().split(",");
		if(null!=guids&&guids.length==1){//一条数据
			if (myForm != null){
				File file=print(myForm,guids[0],request);
				FileUtil.outputWord(response, file);
			}
		}else{//多条数据
			List<File> files = new ArrayList<File>();
			for(String guid:guids){
				File file=print(myForm,guid,request);
				files.add(file);
				}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	/**
	 * 
	 * @描述:获取打印File
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-20 下午05:33:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File print(KnsrdbjpyForm myForm,String guid,HttpServletRequest request) throws Exception{
		myForm.setGuid(guid);
		KnsrdbjpyService service = new KnsrdbjpyService();
		File file=null;
		KnsrdbjpyForm model = service.getModel(myForm);
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			//审批信息
			List<HashMap<String,String>> infoList = service.getSplcInfo(model);
			
			BeanUtils.copyProperties(myForm, model);
			 //困难生档次list
			KnsdcService knsdcbjpyService = new KnsdcService();
			file=service.printForWord(xsjbxx,infoList,model,knsdcbjpyService.getKnsdcList(), model.getRddc(),request);
		}
		return file;
}

	/**
	 * 
	 * @描述: 困难生认定审核情况
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-22 上午10:23:32
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
	public ActionForward knsrdbjpyShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		User user = getUser(request);
		KnsrdbjpyService service = new KnsrdbjpyService();
		Map<String,Object> shqkInfo = service.getShqkInfo(user);
		
		request.setAttribute("shqkInfo", shqkInfo);
		return mapping.findForward("knsrdbjpyShqk");
	}
	

	/**
	 * 
	 * @描述:导出功能
	 * @作者：ligl
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录: 
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		//查询
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
	 * @描述: 审核导出(自定义导出设置)
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-12-15 下午01:53:50
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
	public ActionForward exportDataSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListSh(model,user);//查询出所有记录，不分页
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
	 * @描述: 按审核情况查询具体学生
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-3 下午04:27:45
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
	public ActionForward getStudentsByShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdbjpyForm model = (KnsrdbjpyForm) form;
		KnsrdbjpyService service = new KnsrdbjpyService();
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getStudentsList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "xszz_knsrdbjpy_shtj.do");
		return mapping.findForward("studentsList");
	}
}
