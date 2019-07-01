package com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz.JcszForm;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz.JcszService;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jglr.JglrService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshModel;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

public class JgcxAction extends SuperAction {
	
	private static final String JXSQ = "jxsq";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xpjpy_pjpybjpy_jgcx.do";
	
	/**
	 * 查询评奖班级评议结果查询
	 */
	@SystemAuth(url = url)
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JgcxForm model = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		String xh = user.getUserName();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "基本设置未初始化，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("jcszModel", jcszModel);
		HashMap<String,String> bjpyxzdbMap = service.queryBjpyxzdb(xh);
		String userType = user.getUserType();
		boolean isStuFlag = "stu".equalsIgnoreCase(userType) && bjpyxzdbMap.get("xh") == null;
		if(isStuFlag){
			String msg = "该模块只允许教师、班级评议小组代表访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if("stu".equalsIgnoreCase(userType) && "0".equals(bjpyxzdbMap.get("tjzt"))){
			String msg = "班级评议小组尚未提交，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		JglrService jglrService = new JglrService();
		HashMap<String,String> xsxxMap = jglrService.queryXsxx(xh);
		request.setAttribute("xsxxMap", xsxxMap);
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("searchTj", searchModel);
		
		String path = "xpjpy_pjpybjpy_jgcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcxManage");
	}
	/**
	 * 查看班级评议结果
	 */
	@SystemAuth(url = url)
	public ActionForward jgcxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JgcxForm model = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.jgcxView(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xpjpy_pjpybjpy_jgcx.do?method=jgcxView";
		request.setAttribute("path", path);
		request.setAttribute("rs", StringUtils.formatData(model));
		JglrService jglrService = new JglrService();
		HashMap<String,String> xsxxMap = jglrService.queryXsxx(model.getSqr());
		request.setAttribute("xsxxMap", StringUtils.formatData(xsxxMap));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgcxView");
	}
	/**
	 * 提交评奖班级评议结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="评奖评优-评奖班级评议管理-班级评议结果查询-提交VALUES:{values}")
	public ActionForward jgcxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JgcxForm myForm = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		// 申请信息
		SqshModel pjpybjpyForm = new SqshModel();
		pjpybjpyForm.setSqid(myForm.getSqid());
		SqshService pjpybjpyService = new SqshService();
		SqshModel pjpybjpyModel = pjpybjpyService.getModel(pjpybjpyForm);
		if(SAVE.equals(myForm.getType())){
			boolean result = service.jgcxTj(myForm, pjpybjpyModel, user);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(pjpybjpyModel.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(JXSQ);
		request.setAttribute("jbxxList", jbxxList);
		
		//学生参评班级
		ZcfsService zcfsService = new ZcfsService();
		HashMap<String, String> cpbjxx = zcfsService.getCpbjListByXh(pjpybjpyModel.getXh(), pjpybjpyModel.getXn(), pjpybjpyModel.getXq());
		request.setAttribute("cpbjxx", cpbjxx);
		
		//评奖项目信息
		XmwhService xmwhService = new XmwhService();
		XmwhModel xmwhModel = xmwhService.getModel(pjpybjpyModel.getXmdm());
		request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		
		//条件
		TjszService tjszService = new TjszService();
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(pjpybjpyModel.getXmdm(),pjpybjpyModel.getXh());
		
		//校验条件，返回校验结果
		CheckCondition check = new CheckStudentCondition();
		List<HashMap<String, String>> results = check.checkCondition(pjpybjpyModel.getXh(), conditions);
		request.setAttribute("checkResult", results);
		
		request.setAttribute("mkxxForm", pjpybjpyModel);
		List<HashMap<String,String>> pyjgList = new OptionUtil().getOptions(OptionUtil.PYJG);
		request.setAttribute("pyjgList", pyjgList);
		
		JgcxForm model = service.getModel(myForm);
		if(model == null){
			model = new JgcxForm();
		}
		request.setAttribute("jgcxForm", StringUtils.formatData(model));
		HashMap<String,String> bjpyxzdbMap = service.queryBjpyxzdb(user.getUserName());
		request.setAttribute("bjpyxzdbMap", StringUtils.formatData(bjpyxzdbMap));
		return mapping.findForward("jgcxTj");
	}
	
	/**
	 * 撤销评奖班级评议结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="评奖评优-评奖班级评议管理-班级评议结果查询-撤销VALUES:{values}")
	public ActionForward jgcxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JgcxForm myForm = (JgcxForm) form;
		JgcxService service = new JgcxService();
		User user = getUser(request);
		// 申请信息
		SqshModel pjpybjpyForm = new SqshModel();
		pjpybjpyForm.setSqid(myForm.getSqid());
		SqshService pjpybjpyService = new SqshService();
		SqshModel pjpybjpyModel = pjpybjpyService.getModel(pjpybjpyForm);
		boolean result = service.jgcxCx(pjpybjpyModel);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JgcxForm model = (JgcxForm) form;
		JgcxService service = new JgcxService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
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
}
