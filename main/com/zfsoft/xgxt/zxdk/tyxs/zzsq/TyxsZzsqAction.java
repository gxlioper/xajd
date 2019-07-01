/**
 * @部门:学工产品事业部
 * @日期：2015-4-8 上午11:56:10 
 */
package com.zfsoft.xgxt.zxdk.tyxs.zzsq;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.sun.jmx.snmp.Timestamp;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq.KnsrdsqForm;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq.KnsrdsqService;
import com.zfsoft.xgxt.zxdk.tyxs.cssz.TyxsCssz;
import com.zfsoft.xgxt.zxdk.tyxs.cssz.TyxsCsszService;
import com.zfsoft.xgxt.zxdk.tyxs.shjg.TyxsZzjg;
import com.zfsoft.xgxt.zxdk.tyxs.shjg.TyxsZzjgService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 退役学生助学管理X管理模块
 * @类功能描述: 申请审核操作
 * @作者： 冯兰英[工号:1177]
 * @时间： 2015-4-19下午03:53:15
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TyxsZzsqAction extends SuperAuditAction<TyxsZzsq, TyxsZzsqService> {
	private Log logger = LogFactory.getLog(TyxsZzsqAction.class);
	// 字段采集配置表
	private static final String KNSRD = "knsrd";	
	// 导出配置
	private static final String ZZPZ = "tyxs";
	// 申请高级查询
	private static final String GJCXPATH = "zxdk_tyxs_zzsq.do";
	// 学生系统基本配置配置
	private static final String XSPZ = "tyxs";
	// 导出配置
	private static final String SQDC = "tyxs_zzsq.do";

	// 导入配置

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param gnid
	 * @param squrl
	 * @param shurl
	 */
	protected TyxsZzsqAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
		// TODO 自动生成构造函数存根
	}

	public TyxsZzsqAction() {
		this("tyxs_zzsq", "zxdk_tyxs_zzsq.do", "zxdk_tyxs_zzsh.do");
	}

	/**
	 * 
	 * @描述:申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:53:50
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	@SystemLog(description = "退役学生入学学费资助-学费申请")
	public ActionForward tyxsZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz model = csszService.getModel();
		request.setAttribute("cssz", model);
		
		request.setAttribute("path", "zxdk_tyxs_zzsq.do");
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		logger.info("searchTj" + searchModel);
		return mapping.findForward("zzsqList");
	}

	/**
	 * 
	 * @描述:ajax获取申请列表
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:53:15
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward getZzsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzsqService service = getService();
		TyxsZzsq model = (TyxsZzsq) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		logger.info("model" + searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		logger.info("list" + resultList);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;

	}

	/**
	 * 
	 * @描述:查看申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:52:41
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward ckZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzsqService service = getService();
		TyxsZzsq myForm = (TyxsZzsq) form;

		TyxsZzsq model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 查看显示配置表
		List<HashMap<String, String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService()
				.getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("mkxxForm", myForm); // 学生基本信息显示配置 BdpzService
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		logger.info("jbxxList" + jbxxList);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhmc", service.getYhListByYhdm(model.getYhdm()));
			request.setAttribute("ndjelist", service.getNdJe(myForm.getId()));
		}
		return mapping.findForward("ckZzsq");
	}

	/**
	 * 
	 * @描述:删除申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:52:09
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-退役学生入学学费管理-学费申请-删除values:{id}")
	public ActionForward delZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsZzsq model = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		boolean result = service.runDelete(new String[] { model.getId() }) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;

	}

	/**
	 * 
	 * @描述:填写申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:51:44
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsq model = (TyxsZzsq) form;
		TyxsCsszService csszService = new TyxsCsszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", new TyxsZzsqService().getYhList());
		}

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xlccList", csszService.getXlccList());
		List<HashMap<String, String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("mkxxForm", model);
		String path = "tyxs_zzsq.do?method=zzsq";
		request.setAttribute("path", path);	
		this.saveToken(request);
		return mapping.findForward("zzsq");
	}

	/**
	 * 
	 * @描述:导出申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:51:16
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward dcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsqService servive = getService();
		TyxsZzsq model = (TyxsZzsq) form;
		// 生成高级查询对象
		CommService commService = new CommService();
		logger.info("request="+request);
		SearchModel searchModel = commService.getSearchModel(request);	
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询所有记录
		List<HashMap<String, String>> resultList = servive.getAllList(model,
				user);

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
	 * @描述:打印申请表
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:50:46
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do")
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String[] ids = request.getParameter("ids").split(",");

		if (null != ids && ids.length == 1) {// 一条数据
			File file = print(ids[0], request);
			FileUtil.outputWord(response, file);
		} else {// 多条数据
			List<File> files = new ArrayList<File>();
			for (String id : ids) {
				File file = print(id, request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

	/**
	 * 
	 * @描述:打印
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:50:21
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	private synchronized File print(String id, HttpServletRequest request)
			throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();

		TyxsZzsqService service = getService();
		TyxsZzsq model = service.getModel(id);
		data.put("m", model);
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
				.getXh());
		data.put("j", xsjbxx);
		if(Base.xxdm.equals("10704")){
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk",
					"tyxs_zzsq_10704.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh")
							+ "[" + xsjbxx.get("xm") + "]"));
		}else{
			return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk",
					"tyxs_zzsq.xml", FreeMarkerUtil.getFileName(xsjbxx.get("xh")
							+ "[" + xsjbxx.get("xm") + "]"));
		}
	}

	/**
	 * 
	 * @描述:根据ID查询资助信息
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:49:47
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	public ActionForward zzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = request.getParameter("id");
		TyxsZzsqService service = getService();

		HashMap<String, String> dkxxMap = service.getSqxxByID(id);
		logger.info("dkxxMap" + dkxxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(dkxxMap));
		return null;
	}

	/**
	 * 
	 * @描述:修改申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:48:57
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward editZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("path = " + request.getParameter("path"));

		String path = "tyxs_zzsh.do?method=zzsq";
		request.setAttribute("path", path);
		return mapping.findForward("zzsq");
		// TODO 自动生成方法存根

	}

	/**
	 * 
	 * @描述:按学号学年查询记录总数
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:48:37
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	public ActionForward getCountByXhAndXn(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TyxsZzsqService service = getService();
		TyxsZzsq model = (TyxsZzsq) form;
		String count = service.getCountByXhAndXn(model);
		logger.info("count = "+count);
		response.getWriter().print(count);
		return null;
	}

	/**
	 * 
	 * @描述:修改申请
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:48:14
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgZzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsqService service = getService();
		TyxsZzsq myForm = (TyxsZzsq) form;
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsZzsq model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			/*
			 * 家庭情况 TyxsCssz csszModel = new TyxsCssz(); csszModel=
			 * csszService.getModel(); if (csszModel != null &&
			 * "1".equals(csszModel.getSfwcjtdc()) ){ JtqkdcService jtqkService
			 * = new JtqkdcService(); JtqkdcForm jtqkForm = new JtqkdcForm();
			 * jtqkForm.setXh(model.getXh()); JtqkdcForm jtqkModel =
			 * jtqkService.getModel(jtqkForm);
			 * 
			 * request.setAttribute("openJtqk", jtqkModel == null); }
			 */
		}
		List<HashMap<String, String>> mkxxList = null;
		mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService().getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xlccList", csszService.getXlccList());
		request.setAttribute("xn",Base.currXn);
		logger.info("xn= "+Base.getXnndList()+"\r\n"+Base.currXn);
		request.setAttribute("mkxxForm", model);
		String path = "tyxs_zzsq.do?method=xgZzsq";
		request.setAttribute("path", path);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhlist", service.getYhList());
			request.setAttribute("ndjelist", service.getNdJe(myForm.getId()));
		}
		request.setAttribute("cssz", csszService.getModel());
		this.saveToken(request);
		return mapping.findForward("xgZzsq");
	}

	/**
	 * 
	 * @描述:审核列表
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-19-下午03:47:25
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 * @throws
	 */
	@SystemAuth(url = "zxdk_tyxs_zzsh.do")
	public ActionForward tyxsZzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz model = csszService.getModel();
		request.setAttribute("cssz", model);
		request.setAttribute("path", "zxdk_tyxs_zzsh.do");
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);

		return mapping.findForward("zzshList");

	}

	/**
	 * @描述: ajax加载审核列表
	 * @作者：冯兰英[工号：1177]
	 * @日期：2014-9-25 下午03:39:06
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
	@SystemAuth(url = "zxdk_tyxs_zzsh.do")
	public ActionForward getZzshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("getZzshList");
		TyxsZzsq myform = (TyxsZzsq) form;
		User user = getUser(request);

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myform.setSearchModel(searchModel);

		// 查询
		List<HashMap<String, String>> resultList = getService().getAudingList(
				myform, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		logger.info(resultList);
		return null;
	}

	/**
	 * 
	 * @描述: 资助审核
	 * @作者：冯兰英[工号：1177]
	 * @日期：2014-9-25 下午03:40:39
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
	@SystemAuth(url = "zxdk_tyxs_zzsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TyxsZzsqService service = getService();
		TyxsZzsq myForm = (TyxsZzsq) form;

		TyxsZzsq model = service.getModel(myForm.getId());

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();

		List<HashMap<String, String>> mkxxList = new com.zfsoft.xgxt.xszz.bdpz.BdpzService()
				.getBdpz(KNSRD);
		request.setAttribute("mkxxList", mkxxList);
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZZPZ);

		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("jbxxList", jbxxList);
		if(Base.xxdm.equals("10511")){
			request.setAttribute("yhmc", service.getYhListByYhdm(model.getYhdm()));
			request.setAttribute("ndjelist", service.getNdJe(myForm.getId()));
		}
		return mapping.findForward("zzsh");
		
	}
	
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-23 上午11:42:17
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TyxsZzsq t = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		ShlcInterface shlc = new CommShlcImpl();		
		TyxsZzsq model = service.getModel(t.getId());		
		boolean isSuccess = shlc.firstStepCancle(model.getId(), model.getSplcid());
		
		if(isSuccess){
			//更新业务状态为'未提交'
			model.setShzt(Constants.YW_WTJ);
			model.setId(model.getId());
			isSuccess = service.runUpdate(model);
			if(isSuccess){
				//删除审核信息				
				shlc.deleteShjl(model.getId());
			}
		}		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		logger.info("sdag");
		return null;
	}
	
	/**
	 * 
	 * @描述:审核
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-24 上午09:20:58
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
	@SystemAuth(url = "zxdk_tyxs_zzsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitAuditSH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TyxsZzsq t  = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		ShlcInterface shlc = new CommShlcImpl();
		User user = getUser(request);
		
		ShxxModel shxxModel = new ShxxModel();
		BeanUtils.copyProperties(shxxModel, t);
		
		TyxsZzsq model = service.getModel(t.getId());
				
		shxxModel.setYwid(t.getId());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShzt(t.getShjg());
		shxxModel.setSqrid(model.getXh());

//		shxxModel.setShyj(t.getShyj());
//		shxxModel.setThgw(t.getThgw());
//		shxxModel.setGwid(t.getGwid());
		shxxModel.setTzlj("tyxs_zzsh");
		shxxModel.setTzljsq("tyxs_zzsq");
		
		try {
			//审核操作{插入一条数据到审核表中}
			String zhzt  = shlc.runAuditing(shxxModel); 
			model.setShzt(zhzt);//获取审核状态标志，并更新Model
			
			boolean result = service.runUpdate(model);//更新申请表{变更申请表中审核状态信息}
			
			//如果审核通过 插入一条数据到结果库
			if(result && Constants.SH_TG.equals(zhzt)){ 
				BeanUtils.copyProperties(model, shxxModel);
				result = service.afterLastAuditself(model);		
				if(result){
					TyxsZzjg JgModel = new TyxsZzjg();
					TyxsZzjgService tyjgService = new TyxsZzjgService();
					// 根据学号、学年、项目代码删除结果表中数据
					//tyjgService.delWpjgxx(model.getXh(), model.getXn(), model.getXmdm());
					//BeanUtils.copyProperties(JgModel, StringUtils.formatData(wpsqForm));
					JgModel.setSqid(model.getId());
					JgModel.setSqly(model.getSqly());
					JgModel.setSqxfzj(model.getSqxfzj());
					JgModel.setZzzje(model.getSqxfzj());
					JgModel.setSqsj(model.getSqsj());
					JgModel.setXn(model.getXn());
					JgModel.setXh(model.getXh());
					JgModel.setFilepath(model.getFilepath());
					JgModel.setDkbj(model.getDkbj());
					JgModel.setYhdm(model.getYhdm());
					JgModel.setDkhth(model.getDkhth());
					JgModel.setDkkssj(model.getDkkssj());
					JgModel.setDkjssj(model.getDkjssj());
					JgModel.setDklx(model.getDklx());
					logger.info("=="+JgModel);
					result = tyjgService.runInsert(JgModel);
					logger.info(result);
				}
			}/*else if(result && Constants.SH_BTG.equals(zhzt)){
				
					TyxsZzjg JgModel = new TyxsZzjg();
					TyxsZzjgService tyjgService = new TyxsZzjgService();
					// 根据学号、学年、项目代码删除结果表中数据
					//tyjgService.delWpjgxx(model.getXh(), model.getXn(), model.getXmdm());
					//BeanUtils.copyProperties(JgModel, StringUtils.formatData(wpsqForm));
					JgModel.setSqid(model.getId());
					JgModel.setSqly(model.getSqly());
					JgModel.setSqxfzj(model.getSqxfzj());
					JgModel.setZzzje("0");
					JgModel.setSqsj(model.getSqsj());
					JgModel.setXn(model.getXn());
					JgModel.setXh(model.getXh());
					logger.info("=="+JgModel);
					result = tyjgService.runInsert(JgModel);
					logger.info(result);
				
			}*/
			
			
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:提交
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-27 下午01:24:53
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz cssz = csszService.getModel();		
		TyxsZzsqService service  = new TyxsZzsqService();
		TyxsZzsq myform = (TyxsZzsq)form;		
		TyxsZzsq model = service.getModel(myform.getId());					
		model.setId(myform.getId());
		model.setSplcid(cssz.getXfzzshlc());
		service.runUpdate(model);
		TyxsZzsq tyForm = service.getModel(myform.getId());		
		 return submit(mapping, tyForm, request, response);
		
	}

	/**
	 * 
	 * @描述:ajax修改操作
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-27 下午01:24:53
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz cssz = csszService.getModel();		
		TyxsZzsqService service  = new TyxsZzsqService();
		TyxsZzsq model = (TyxsZzsq)form;		
		
		model.setSplcid(cssz.getXfzzshlc());
		boolean result=service.runUpdate(model);
		if(Base.xxdm.equals("10511")){
			String[] nds = request.getParameterValues("nd");
			String[] jes = request.getParameterValues("je");
			service.delNdJe(model.getId());
			result = service.saveNdJeHsd(nds, jes, model.getId());
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	
		
	}

	/**
	 * 
	 * @描述:新增和提交
	 * @作者：冯兰英[工号：1177]
	 * @日期：2015-4-27 下午01:24:53
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
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TyxsCsszService csszService = new TyxsCsszService();
		TyxsCssz cssz = csszService.getModel();		
		TyxsZzsqService service  = new TyxsZzsqService();
		TyxsZzsq myform = (TyxsZzsq)form;		
		myform.setSplcid(cssz.getXfzzshlc());
		if(Base.xxdm.equals("10511") && StringUtils.isNull(myform.getId())){
			String guid =  UniqID.getInstance().getUniqIDHash().toUpperCase();
			myform.setId(guid);
			service.runInsert(myform);
		}
		
		if(Base.xxdm.equals("10511")){
			service.delNdJe(myform.getId());
			String[] nds = request.getParameterValues("nd");
			String[] jes = request.getParameterValues("je");
			service.saveNdJeHsd(nds, jes, myform.getId());
		}
		return super.saveAndSubmit(mapping, myform, request, response);
		
	}
	
	@SystemAuth(url = "zxdk_tyxs_zzsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TyxsZzsq model = (TyxsZzsq) form;
		TyxsZzsqService service = getService();
		String  guid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setId(guid);
		boolean isSuccess = service.runInsert(model);
		if(Base.xxdm.equals("10511")){
			String[] nds = request.getParameterValues("nd");
			String[] jes = request.getParameterValues("je");
			isSuccess = service.saveNdJeHsd(nds, jes, guid);
		}
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
}
