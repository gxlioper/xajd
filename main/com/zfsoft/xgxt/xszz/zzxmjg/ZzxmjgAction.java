/**
 * @部门:学工产品事业部
 * @日期：2013-4-24 下午01:38:38 
 */
package com.zfsoft.xgxt.xszz.zzxmjg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

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
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgAction;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgModel;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.bbwh.BbwhService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcDao;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmlbwh.XmlbwhService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 资助项目结果管理模块
 * @类功能描述: action
 * @作者： maxh
 * @时间： 2013-4-24 下午01:38:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZzxmjgAction extends SuperAction {

	private static final String ZZXM = "zzxm";
	private static List<HashMap<String, String>> jbxxList = null;
	private static int CYSIZE = 4;// 家庭成员最大支持数量
	private static int ZZJGSIZE = 15;// 资助结果上报表最佳显示数量
	private static String CG = "导入成功！";
	private static String KONG = "请上传文件";
	private static String SB = "导入失败,请仔细核对【出错记录.xls】！";
	private static String NOCONTENT = "上传文件无数据";
	BdpzService bdpzService = new BdpzService();
		
	private static final String url = "xszz_zzxmjg_cx.do";
		

	/**
	 * 
	 * @描述:查询
	 * @作者：maxh
	 * @日期：2013-4-24 下午02:25:14
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
	public ActionForward getZzxmjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

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
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "xszz_zzxmjg_cx.do";
		request.setAttribute("path", path);
		List<HashMap<String, String>> list=service.getkfzZqList(Base.currXn);
		request.setAttribute("iscanCopy", null==list||list.size()<=0?"0":"1");
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("zzxmjg");
	}

	/**
	 * 
	 * @描述:增加
	 * @作者：maxh
	 * @日期：2013-4-24 下午03:13:17
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
	@SystemLog(description="访问学生资助-资助管理-资助结果-增加XMMC:{xmmc},XH:{xh},LBDM:{lbdm},PDXN:{pdxn}")
	public ActionForward addZzxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;

		ZzxmjgService service = new ZzxmjgService();
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

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}
			// 唯一性判断（学号，学年，学期,项目名称）
			boolean isExist = service.isExistByZzxmjg(model, SAVE);
			if (!isExist) {
				super.resetToken(request);
				// 添加资助信息
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setZsbm(service.getZsbm(model));
				}
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}

		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);

		// 项目类别
		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);
		// 学年list
		request.setAttribute("xnList", Base.getXnndList());
		// 学期list
		request.setAttribute("xqList", Base.getXqList());

		String path = "xszz_zzxmjg.do?method=addZzxmjg";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(ZZXM);
		request.setAttribute("jbxxList", jbxxList);
		this.saveToken(request);
		return mapping.findForward("addzzxmjg");
	}

	/**
	 * 
	 * @描述:修改
	 * @作者：Administrator
	 * @日期：2013-4-25 下午04:14:09
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
	@SystemLog(description="访问学生资助-资助管理-资助结果-修改GUID:{guid},XMMC:{xmmc},XH:{xh},LBDM:{lbdm},PDXN:{pdxn}")
	public ActionForward updateZzxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;

		ZzxmjgService service = new ZzxmjgService();
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

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号，学年，学期,项目名称）
			boolean isExist = service.isExistByZzxmjg(model, UPDATE);
			if (!isExist) {
				if("10511".equals(Base.xxdm)){
					model.setXmdm(service.getXmdm(model));
					model.setZsbm(service.getZsbm(model));
					
				}
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		// 项目类别
		XmlbwhService xmlbwhService = new XmlbwhService();
		List<HashMap<String, String>> xmlb = xmlbwhService.getXmlb();
		request.setAttribute("xmlbList", xmlb);
		// 学年list
		request.setAttribute("xnList", Base.getXnndList());
		// 学期list
		request.setAttribute("xqList", Base.getXqList());

		String path = "xszz_zzxmjg.do?method=addZzxmjg";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(ZZXM);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);

		ZzxmjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));

		return mapping.findForward("updateZzxmjg");
	}

	/**
	 * 
	 * @描述:删除资助结果
	 * @作者：maxh
	 * @日期：2013-4-25 下午04:41:32
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
	@SystemLog(description="访问学生资助-资助管理-资助结果-删除-VALUES:{values}")
	public ActionForward delZzxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgService service = new ZzxmjgService();

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;

			if (result) {
				XszzSqshService sqshService = new XszzSqshService();
				sqshService.delXmsqFromZzjg(values.split(","));
			}

			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}

	/**
	 * 
	 * @描述:查看单个资助项目信息
	 * @作者：maxh
	 * @日期：2013-4-25 下午06:37:05
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
	public ActionForward zzxmjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (model != null) {

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// 查询结果
			request.setAttribute("rs", StringUtils.formatData(service
					.getOneZzxmjgList(model.getGuid())));
			// 学生基本信息显示配置
			jbxxList = bdpzService.getJbxxpz(ZZXM);
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewZzxmjg");
		} else {
			return updateZzxmjg(mapping, form, request, response);
		}

	}

	/**
	 * 
	 * @描述:资助项目汇总信息列表
	 * @作者：wanghj
	 * @日期：2014-1-10
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
	public ActionForward zzxmhzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getZzxmhzList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		//searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "xszz_zzxmjg.do?method=zzxmhzList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zzxmhzList");
	}
	
	/**
	 * 获奖名单导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward expHjmdtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		ZzxmjgService service = new ZzxmjgService();
		CommService comService = new CommService();
		ZzxmjgExportModel exporModel = new ZzxmjgExportModel();
		comService.getModelValue(exporModel, request);
		String array_xmlb = request.getParameter("array_xmlb");
		array_xmlb = java.net.URLDecoder.decode(array_xmlb,"UTF-8");
		String xmlbs[] = array_xmlb.split("!!array!!");
		exporModel.setXmlb(xmlbs);

		response.setContentType("application/vnd.ms-excel");
		
		service.exportHjmdtj(exporModel, response.getOutputStream(),user);
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward zzxmhzView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		if (QUERY.equals(model.getType())) {
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.zzxmhzView(model, user, true);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("model", model);
		return mapping.findForward("zzxmhzView");
	}
	
	/**
	 * 
	 * @描述:打印困难生申请信息
	 * @作者：honglin
	 * @日期：2013-5-3
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZzxmjgForm myForm = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		ZzxmjgForm model = service.getModel(myForm);
		myForm.setXn(model.getXn());
		myForm.setXq(model.getXq());
		// 根据项目名称获取代码信息
		if (!StringUtil.isNull(myForm.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			String xsxmdm = xmwhService.getXszzdm(myForm.getXmmc(),myForm.getXn(),myForm.getXq());
			myForm.setXmdm(xsxmdm);
		}
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			// 分解身份证号begin
			String xssfzh = xsjbxx.get("sfzh") == null || "".equals("sfzh") ? ""
					: xsjbxx.get("sfzh");
			int sylen = 18 - xssfzh.length();
			for (int i = 0; i < xssfzh.length(); i++) {
				xsjbxx.put("sfzh" + i, xssfzh.charAt(i) + "");
			}
			for (int i = 0; i < sylen; i++) {
				xsjbxx.put("sfzh" + (xssfzh.length() + i), "");
			}
			// 分解身份证号end
			request.setAttribute("jbxx", xsjbxx);

			// 加载学生家庭基本信息
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(myForm.getXh());
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			request.setAttribute("jtqk", jtqkmodel);

			// 按学号加载成员列表
			JtqkdcService jtqkdcService = new JtqkdcService();
			List<HashMap<String, String>> cyList = jtqkdcService
					.getJtcyList(myForm.getXh());
			request.setAttribute("blankList",
					CYSIZE > cyList.size() ? jtqkdcService.getBlankList(CYSIZE
							- cyList.size()) : jtqkdcService.getBlankList(0));
			request.setAttribute("cyList", cyList);
			request.setAttribute("cysize", CYSIZE > cyList.size() ? CYSIZE
					: cyList.size());

			// 加载学生困难认定情况
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(myForm
					.getXh(), myForm.getXn(), myForm.getXq());
			request.setAttribute("rddc", knsjg.get("rddc") == null ? "" : knsjg
					.get("rddc"));
		}
		// 学校名称
		request.setAttribute("xxmc", Base.xxmc);
		KnsdcService knsdcService = new KnsdcService();
		// 困难生档次list
		request.setAttribute("knsdc", knsdcService.getKnsdcList());
		// 跳转
		String forward = "";
		if ("gjjxjb".equalsIgnoreCase(myForm.getXmdm())) {// 国家奖学金申请审批表
			forward = "/xsgzgl/xszz/print/gjjxjbDefault.jsp";
		} else if ("gjlzjxjb".equalsIgnoreCase(myForm.getXmdm())) {// 国家励志奖学金申请审批表
			forward = "/xsgzgl/xszz/print/gjlzjxjbDefault.jsp";
		} else if ("gjzxjb".equalsIgnoreCase(myForm.getXmdm())) {// 国家助学金申请审批表
			forward = "/xsgzgl/xszz/print/gjzxjbDefault.jsp";
		} else {
			forward = "/xsgzgl/xszz/print/gjzxjbDefault.jsp";
			// throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("xmxx", model);// 加载学生项目信息
		return new ActionForward(forward, false);
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
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		Pages pages = model.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		model.setPages(pages);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getJgExportList(model, user);

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
	 * @描述:浙江机电汇总导出功能
	 * @作者：ChenQ
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录:
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
	public ActionForward exportHzData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getZzxmjgHzList();

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
	 * @描述:打印word
	 * @作者：ligl
	 * @日期：2013-5-31 上午11:32:52
	 * @修改记录:
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
	public ActionForward downloadWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm myForm = (ZzxmjgForm) form;
		String guid = myForm.getGuid();
		File wordFile = getWord(guid);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * 
	 * @描述:下载ZIP
	 * @作者：ligl
	 * @日期：2013-5-31 上午11:32:26
	 * @修改记录:
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
	public ActionForward downloadZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

	// 填充模版数据生成word文件
	@SuppressWarnings("unchecked")
	private File getWord(String guid) throws Exception {
		ZzxmjgService service = new ZzxmjgService();
		ZzxmjgForm myForm = new ZzxmjgForm();
		myForm.setGuid(guid);
		ZzxmjgForm model = service.getModel(guid);//资助项目结果
		HashMap<String, String> bbMap = null;// 报表
		// 根据项目名称获取代码信息
		if (!StringUtil.isNull(model.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> xmMap = xmwhService.getDataByName(model
					.getXmmc(),model.getXn(),model.getXq());//查询项目记录
			if (xmMap != null) {
				model.setXmdm(xmMap.get("xmdm"));//项目代码
				BbwhService bbwhService = new BbwhService();
				bbMap = bbwhService.getDataById(xmMap.get("dybb"));
			}
		}
		if (bbMap == null || bbMap.size() == 0) {//查询不到相关联报表信息
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		String xh = model.getXh();
		if (!StringUtil.isNull(xh)) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//转换为中文日期格式
			xsjbxx.put("rxrq", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));
			// 分解身份证号begin
			String xssfzh = xsjbxx.get("sfzh") == null || "".equals("sfzh") ? ""
					: xsjbxx.get("sfzh");
			int sylen = 18 - xssfzh.length();
			for (int i = 0; i < xssfzh.length(); i++) {
				xsjbxx.put("sfzh" + i, xssfzh.charAt(i) + "");
			}
			for (int i = 0; i < sylen; i++) {
				xsjbxx.put("sfzh" + (xssfzh.length() + i), "");
			}
			//学生照片
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			// 加载学生家庭基本信息
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}

			// 按学号加载成员列表
			JtqkdcService jtqkdcService = new JtqkdcService();
			List<HashMap<String, String>> cyList = jtqkdcService
					.getJtcyList(model.getXh());
			Map<String, Object> data = new HashMap<String, Object>();
			if(cyList != null && cyList.size() > 0){
				for (HashMap<String, String> cyMap : cyList) {
					cyMap.put("cyxm",HtmlUtil.xmlZy(cyMap.get("cyxm")));
					cyMap.put("cygxmc",HtmlUtil.xmlZy(cyMap.get("cygxmc")));
					cyMap.put("cyxxdw",HtmlUtil.xmlZy(cyMap.get("cyxxdw")));
				}
				data.put("cyMap", cyList.get(0));
			}
			else{
				data.put("cyMap", new HashMap<String,String>());
			}
			List<HashMap<String, String>> cyList4line = jtqkdcService.getJtcyList(xh,4); //取4条家庭成员列表，不足4条填空
			List<HashMap<String, String>> cyList5line = jtqkdcService.getJtcyList(xh,5);
			// 加载学生困难认定情况
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(model
					.getXh(), model.getPdxn(), model.getPdxq());
			KnsdcService knsdcService = new KnsdcService();
			// 有无违纪处分
			WjcfCfsbService wjcfcfsbService =  new WjcfCfsbService();
			List<HashMap<String , String>> wjList = wjcfcfsbService.getYscfqk(xh);
			data.put("wjList", wjList);
			  if ("12867".equals(Base.xxdm)){
		        XsxxtyglService service1 = new XsxxtyglService();
		        XsxxglService xsxxglService = new XsxxglService();
		        List cyList1 = xsxxglService.getJtcyxxXsList(xh);
		        data.put("jtcyxxList", cyList1);
		        int size1 = 5 - cyList1.size() <= 0 ? 0 : 5 - cyList1.size();
		        data.put("cyblankList", service1.getBlankList(size1));

		        Map jtqkmap = jtqkdcService.getJtqkInfo(xh);
		        data.put("jtqkmap", jtqkmap);

		        List zzxx = service.getZzxmjgInfoList(xh);
		        HashMap zxjxxmap = new HashMap();
		        for (int i = 0; i < zzxx.size(); i++) {
		          if (((String)((HashMap)zzxx.get(i)).get("xmmc")).equals(model.getXmmc())) {
		            zxjxxmap = (HashMap)zzxx.get(i);
		          }
		        }
		        data.put("zxjxxmap", zxjxxmap);
		        
		        if (model.getXmmc().indexOf("爱心基金")!=-1) {
		        	//爱心基金
		        	HashMap<String,String> axjjmap = service.getZjlyAxjjMap(xh, model.getXn());
		        	data.put("axjjmap", axjjmap);
				}else if (model.getXmmc().indexOf("孤儿爱心补助")!=-1) {
					//孤儿爱心补助
					HashMap<String,String> geaxmap = service.getZjlyGeaxMap(xh, model.getXn());
		        	data.put("geaxmap", geaxmap);
				}else if (model.getXmmc().indexOf("校服费用减免")!=-1) {
					//新生校服费用减免
					HashMap<String,String> xfjmmap = service.getZjlyXfjmMap(xh, model.getXn());
		        	data.put("xfjmmap", xfjmmap);
				}else if (model.getXmmc().indexOf("生活用品费用减免")!=-1) {
					//生活用品费用减免
					HashMap<String,String> shfjmmap = service.getZjlyShfjmMap(xh, model.getXn());
		        	data.put("shfjmmap", shfjmmap);
				}else if (model.getXmmc().indexOf("商业保险补助")!=-1) {
					//生活用品费用减免
					HashMap<String,String> sybxbzmmap = service.getZjlySybxbzMap(xh, model.getXn());
		        	data.put("sybxbzmmap", sybxbzmmap);
				}else if (model.getXmmc().indexOf("交通补助")!=-1) {
					//交通补助
					HashMap<String,String> jtbzmmap = service.getZjlyJtbzMap(xh, model.getXn());
		        	data.put("jtbzmmap", jtbzmmap);
				}else if (model.getXmmc().indexOf("技能考证")!=-1) {
					//技能考证费补助
					HashMap<String,String> jnkzfbzmap = service.getZjlyJnkzfbzmapMap(xh, model.getXn());
		        	data.put("jnkzfbzmap", jnkzfbzmap);
				}else if (model.getXmmc().indexOf("学费减免")!=-1) {
					//学费减免
					HashMap<String,String> xfjmmap = service.getZjlyXfjmmapMap(xh, model.getXn());
		        	data.put("xfjmmap", xfjmmap);
				}else if (model.getXmmc().indexOf("助学金")!=-1) {
					//学费减免
					HashMap<String,String> zxjmap = service.getZjlyZxjmapMap(xh, model.getXn());
		        	data.put("zxjmap", zxjmap);
				}          
		      }
			//广西师范大学 个性化
			if(StringUtils.isEqual(Base.xxdm, "10602")){
				XsxxtyglService service1 = new XsxxtyglService();
				//学生家庭成员情况
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				data.put("dqxmmc", model.getXmmc());
				data.put("photo", photo);
				data.put("jtcyxxList", jtcyxxList);
				int size1=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
				data.put("cyblankList", service1.getBlankList(size1));
				//加载评奖结果信息
				PjjgService pjjgService = new PjjgService();
				List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoList(xh);
				data.put("pjjg1", pjjg);
				//加载资助信息
				List<HashMap<String, String>> zzjg = service.getZzxmjgInfoList(xh);
				data.put("zzjglist", zzjg);
			}
			//广西职业学院个性化
			if("11773".equals(Base.xxdm)){
				// ======== 曾获何种奖励 begin============
				PjjgService pjjgService = new PjjgService();
				ZzxmjgService zzxmjgService = new ZzxmjgService();
				List<HashMap<String, String>> pjjgList3line = pjjgService.getHjqkByXhMap(xh,3);
				data.put("pjjgList3line", pjjgList3line);
				List<HashMap<String, String>> zzjgList4line = zzxmjgService.getZzjgList(xh,4);
				data.put("zzjgList4line", zzjgList4line);
				// ======== 曾获何种奖励 end============
				// ========= 上学年各科学习成绩 begin ============
				ZcfsService zcfService = new ZcfsService();
				String sT = model.getXn().substring(0,4);
				Base.beforXn = String.valueOf(Integer.parseInt(sT)-1) + "-" + sT;
				List<HashMap<String, String>> cjXnxqList = zcfService.getCjListByXhXn(xh,Base.beforXn, "");
				data.put("cjXnxqList", cjXnxqList);
				// ========= 学习成绩 end ============
			}
			
			//闽南师范学院个性化
			if("10402".equals(Base.xxdm)){
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList_10402 = xsxxglService.getHjqkNewList(xh);
				
				data.put("hjqkList_10402", hjqkList_10402);
				int hjqkSize=(4 - hjqkList_10402.size())<0?0:(4 - hjqkList_10402.size());
				data.put("hjqkBlankList_10402", jtqkdcService.getBlankList(hjqkSize));
				
			}
			if("11799".equals(Base.xxdm)){			
				ZzxmjgService service_11799 = new ZzxmjgService();
				String rq = model.getXn();
				String[] xnArr = rq.split("-");
				String sxn = Integer.parseInt(xnArr[0])-1+"-"+Integer.parseInt(xnArr[1]);
				HashMap<String,String> xfjmmap = service_11799.getQtzzje(xh, sxn);
	        	data.put("qtzzje", xfjmmap.get("qtzzje"));
	        	HashMap<String,String> knbzcsje = service_11799.getKncsAndJe(xh, sxn);
	        	data.put("knbzcs", knbzcsje.get("knbzcs"));
	        	data.put("knbzje", knbzcsje.get("knbzje"));
			}
			data.putAll(xsjbxx);//学生基本信息
			
			//加载学生职务
			data.put("zwmc", service.getZwForXh(xh));
			data.put("lxdh",xsjbxx.get("sjhm"));
			
			//加载评奖结果信息
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh,4);  //获取最新4条评奖结果
			String xmmcs = pjjgService.getXmmcAllByPjjg(xh);
			//加载资助信息
			List<HashMap<String, String>> zzjg = service.getZzxmjgInfoList(xh);
			// 家庭成员列表空行
			data.put("pjjgblankList", CYSIZE > pjjg.size() ? jtqkdcService
					.getBlankList(CYSIZE - pjjg.size()) : jtqkdcService
					.getBlankList(0));
			
			data.put("pjjg", pjjg);
			data.put("pjjgList4line", pjjgList4line);
			data.put("xmmcs", xmmcs);
			data.put("zzjgList", zzjg);
			data.put("sqzzje",model.getJe()); // 申请资助金额
			data.put("lbmc",model.getLbmc());
			data.put("zzjg",model);//资助结果
			data.put("jtqk", jtqkmodel);//家庭情况
			//家庭年总收入
			if(jtqkmodel.getJtnzsr() != null) {
				data.put("jtnzsr",Double.parseDouble(jtqkmodel.getJtnzsr()));//家庭年总收入
				data.put("jtnsr",Double.parseDouble(jtqkmodel.getJtnzsr()));//家庭年收入
				data.put("jtyzsr",(Double.parseDouble(jtqkmodel.getJtnzsr())/12));//家庭月总收入
				data.put("jtrjysr",jtqkmodel.getJtrs() == null? "":(Double.parseDouble(jtqkmodel.getJtnzsr())/12/(Integer.parseInt(jtqkmodel.getJtrs()))));//家庭人均月收入
			}else {
				data.put("jtyzsr","");
				data.put("jtrjysr","");
			}
			
			/*家庭情况--家庭人均年收入(通用)*/
			if(jtqkmodel != null){
				String jtrjsr = jtqkmodel.getJtrjsr();
				if(jtrjsr != null){
					data.put("jtrjsr", jtrjsr);
				}else{
					data.put("jtrjsr", "");
				}
			}else{
				data.put("jtrjsr", "");
			}
			
			data.put("cyList", cyList);//家庭成员列表
			data.put("cyList4line", cyList4line);
			data.put("cyList5line", cyList5line);
			//华中农业大学
			if("10504".equals(Base.xxdm)){
				int size6=(4 - pjjg.size())<=0?0:(4 - pjjg.size());
				data.put("blankList1", jtqkdcService.getBlankList(size6));
				int size7=(4 - cyList.size())<=0?0:(4 - cyList.size());
				data.put("blankList2", jtqkdcService.getBlankList(size7));
				
			}
			//浙江传媒||重庆三峡医药||太原旅游职业学院||陕西师范大学||东北石油
			if("11647".equals(Base.xxdm) || "14008".equals(Base.xxdm) || "13696".equals(Base.xxdm) || "10718".equals(Base.xxdm) || "10220".equals(Base.xxdm)) {
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			//长江职业
			}else if("10956".equals(Base.xxdm)) {
				data.put("blankList", 3 > cyList.size() ? jtqkdcService
						.getBlankList(3 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}else {
				// 家庭成员列表空行
				data.put("blankList", CYSIZE > cyList.size() ? jtqkdcService
						.getBlankList(CYSIZE - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}
			//山西财经大学个性化
			if("10125".equals(Base.xxdm)){
				List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map = null;
				int pjjgsize = 4;
				if(pjjg.size() > pjjgsize){
					for (int i = 0;i < pjjgsize;i++){
						map = new HashMap<String,String>();
						map.put("sqsjs", pjjg.get(i).get("sqsjs"));
						map.put("xmmc", pjjg.get(i).get("xmmc"));
						map.put("bjdw", pjjg.get(i).get("bjdw"));
						pjjgList.add(map);
					}
					data.put("pjjgblankList",pjjgList);
				} else {
					data.put("pjjgblankList", pjjg);
				}
				int size2=(4 - pjjg.size())<=0?0:(4 - pjjg.size());
				data.put("blankList2", jtqkdcService.getBlankList(size2));
				data.put("cyList", cyList);
				int size1=(4 - cyList.size())<=0?0:(4 - cyList.size());
				data.put("blankList1", jtqkdcService.getBlankList(size1));
			}
			 //浙江警官学院 个性化
			  if ("12869".equals(Base.xxdm)){
					List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
					HashMap<String,String> map = null;
					int pjjgsize = 4;
					if(pjjg.size() > pjjgsize){
						for (int i = 0;i < pjjgsize;i++){
							map = new HashMap<String,String>();
							map.put("sqsjs", pjjg.get(i).get("sqsjs"));
							map.put("xmmc", pjjg.get(i).get("xmmc"));
							map.put("bjdw", pjjg.get(i).get("bjdw"));
							pjjgList.add(map);
						}
						data.put("pjjgblankList",pjjgList);
					} else {
						data.put("pjjgblankList", pjjg);
					}
					int size2=(4 - pjjg.size())<=0?0:(4 - pjjg.size());
					data.put("blankList2", jtqkdcService.getBlankList(size2));
					data.put("cyList", cyList);
					int size1=(4 - cyList.size())<=0?0:(4 - cyList.size());
					data.put("blankList1", jtqkdcService.getBlankList(size1));
					HashMap<String, String> sfkns = service.getSfkns(xh, model.getXn());
				    data.put("jgxyrddc", sfkns.get("dcmc"));
					data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				}
			  
			//黑龙江农垦职业学院个性化
			if("12727".equals(Base.xxdm)){
				// ======== 曾获何种奖励 begin============
				StringBuffer chhzjlBuffer = new StringBuffer();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						chhzjlBuffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(xmmc).append("；");
					}
				}
				String chhzjlmc = chhzjlBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("chhzjlmc", chhzjlmc);
				// ======== 曾获何种奖励 end============
				// 家庭情况
				JtqkdcForm jtqkmodel_12727 = jtqkService.getModel(jtqkdcForm); 
				if (jtqkmodel_12727 == null) {
					jtqkmodel_12727 = new JtqkdcForm();
				}
				data.put("jtqkmodel_12727", jtqkmodel_12727);
				String jthk = jtqkmodel.getJthk();
				boolean jthkCzFlag = false;
				if(jthk != null && jthk.contains("城镇")){
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// 家庭成员
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				pjjgService.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
				// ========= 学习成绩 begin ============
				ZcfsService zcfService = new ZcfsService();
				List<HashMap<String,String>> cjXnxqList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "");
				StringBuffer bxnxxcjBuffer = new StringBuffer();
				for (int i = 0; i < cjXnxqList.size(); i++) {
					HashMap<String, String> cjXnxqMap = cjXnxqList.get(i);
					bxnxxcjBuffer.append(cjXnxqMap.get("kcmc")).append("：").append(cjXnxqMap.get("cj")).append("，");
				}
				String bxnxxcjmc = bxnxxcjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("bxnxxcjmc", bxnxxcjmc);
				
				List<HashMap<String,String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "");
				String bxn_cjSxqPjf = pjjgService.getPjf(bxn_cjSxqBxList, 2); // 本学年上学期平均成绩
				data.put("bxn_cjSxqPjf", bxn_cjSxqPjf);
				List<HashMap<String,String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				String bxn_cjXxqPjf = pjjgService.getPjf(bxn_cjXxqBxList, 2); // 本学年下学期平均成绩
				data.put("bxn_cjXxqPjf", bxn_cjXxqPjf);
				
				String bxn_bjgcjsSxq = pjjgService.getBjgcjNum(xh, model.getXn(), "01"); //本学年上学期不及格成绩数量
				data.put("bxn_bjgcjsSxq", "".equals(bxn_bjgcjsSxq) ? "0" : bxn_bjgcjsSxq); 
				String bxn_bjgcjsXxq = pjjgService.getBjgcjNum(xh, model.getXn(), "02"); //本学年下学期不及格成绩数量
				data.put("bxn_bjgcjsXxq", "".equals(bxn_bjgcjsXxq) ? "0" : bxn_bjgcjsXxq);
				// ========= 学习成绩 end ============
			}
			data.put("knsdcList", knsdcService.getKnsdcList());// 困难生档次list
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//认定档次
			data.put("rddcmc", knsjg.get("dcmc")==null?"":knsjg.get("dcmc"));//认定档次名称
			
			data.put("xxmc", Base.xxmc);// 学校名称
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));//申请理由
			data.put("currXnNow", Base.currXn);//当前学年
			data.put("currXn", model.getPdxn());// 当前项目评定学年---（2014-10-17-沈晓波修改，之前currXn里的值为当前学年）
			//综测分排名
			HashMap<String,String> zcfMap = new ZcfsService().getZczfByXh(xh, Base.currXn, Base.currXq);
			String bjrs = new ZcfsService().getBjrs(xh);
			//太原旅游职业学院
			if("13696".equals(Base.xxdm)) {
				//当前学期
				if("02".equals(Base.currXq)) {
					data.put("currXqNow", "一");
				}else if("01".equals(Base.currXq)) {
					data.put("currXqNow", "二");
				}
				data.put("zcfMap", zcfMap);
				data.put("bjrs", bjrs);
				
			}
			
			//山东畜牧兽医职业
			if("12947".equals(Base.xxdm)) {
				//月总收入
				String  Jtyzsr = Float.parseFloat(jtqkmodel.getJtrjysr())*Integer.parseInt(jtqkmodel.getJtrs())+"";
				data.put("jtyzsr", Jtyzsr);
				data.put("xn", model.getXn());
			}
				
			//重庆三峡高等医药专科学校
			if("14008".equals(Base.xxdm)) {
				YlbxjgService ylbxjgService = new YlbxjgService();
				HashMap<String, String> jfdcInfo = ylbxjgService.getJfdcRylbInfo(xh);
				data.put("yljfdc", jfdcInfo.get("dcmc")); // 医疗缴费档次	
				data.put("rylb", jfdcInfo.get("rylb")); // 人员类别
			}
			
			//上海戏剧
			if("10279".equals(Base.xxdm)) {
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxrq = xsjbxx.get("rxrq") == null ? "" : xsjbxx.get("rxrq");
				data.put("csny_n", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				data.put("rxny_n", rxrq.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}
			//浙江大学
			if ("10335".equals(Base.xxdm)) {
				String csrq = xsjbxx.get("csrq");
				String nf="" ;
				String yf="" ;
				String rq="" ;
				if (csrq!=null) {
					if (csrq.length()==8) {
						nf = xsjbxx.get("csrq").substring(0, 4);
						yf = xsjbxx.get("csrq").substring(4, 6);
						rq = xsjbxx.get("csrq").substring(6, 8);
					}else if (csrq.length()==10) {
						nf = xsjbxx.get("csrq").substring(0, 4);
						yf = xsjbxx.get("csrq").substring(5, 7);
						rq = xsjbxx.get("csrq").substring(8, 10);
					}
				}
					data.put("nf", nf);
					data.put("yf", yf);
					data.put("rq", rq);
				data.put("blankList", 3 > cyList.size() ? jtqkdcService
						.getBlankList(3 - cyList.size()) : jtqkdcService
						.getBlankList(0));
				 SimpleDateFormat datenow = new SimpleDateFormat("yyyy-MM-dd");
				    String date = datenow.format(new Date());
				    String year = date.substring(0, 4);
				    String month = date.substring(5, 7);
				    String day = date.substring(8, 10);
				    data.put("today", date);
				    data.put("year", year);
				    data.put("month", month);
				    data.put("day", day);
				
				//加载资助信息
				List<HashMap<String, String>> zzjg1 = service.getZzxmjgInfoList(xh);
				data.put("zzjglist", zzjg1);
				//学生家庭成员信息
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				XsxxtyglService service1 = new XsxxtyglService();
				int size1=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
				data.put("cyblankList", service1.getBlankList(size1));
				data.put("jtcyxxList", jtcyxxList);
				data.put("jtcyxxList1", jtcyxxList != null && jtcyxxList.size() > 0 ? jtcyxxList.get(0) : new HashMap<String, String>());
				data.put("jtcyxxList2", jtcyxxList != null && jtcyxxList.size() > 1 ? jtcyxxList.get(1) : new HashMap<String, String>());
				data.put("jtcyxxList3", jtcyxxList != null && jtcyxxList.size() > 2 ? jtcyxxList.get(2) : new HashMap<String, String>());
				data.put("jtcyxxList4", jtcyxxList != null && jtcyxxList.size() > 3 ? jtcyxxList.get(3) : new HashMap<String, String>());
				data.put("jtcyxxList5", jtcyxxList != null && jtcyxxList.size() > 4 ? jtcyxxList.get(4) : new HashMap<String, String>());
				data.put("jtcyxxList6", jtcyxxList != null && jtcyxxList.size() > 5 ? jtcyxxList.get(5) : new HashMap<String, String>());
			
				
				HashMap<String,String> knsxx = knsjgService.getKnsxx(model.getXh());
				data.put("knssqsj", DateTranCnDate.fomartDateToCn(knsxx.get("sqsj"),FomartDateType.month));//学生申请困难生时间（申请时间取XXXX年XX月）
				xsjbxx.put("zzxmsqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day));//取学生申请奖项时间
				XszzSqshService xszzSqshservice = new XszzSqshService();
				String zzxmshje = xszzSqshservice.zzxmshtgJe(xh,model.getXn(),model.getXq());
				data.put("zzxmshje", zzxmshje);
			}
			
			//温州大学
			if("10351".equals(Base.xxdm)) {
					
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList = xsxxglService.getHjqkNewFourList(xh);
				data.put("pjjg", hjqkList);
				int hjqkSize=(4 - hjqkList.size())<0?0:(4 - hjqkList.size());
				data.put("pjjgblankList", jtqkdcService.getBlankList(hjqkSize));
				String csny = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month);
				String rxrq = xsjbxx.get("rxrq");
				data.put("csny", csny);
				data.put("rxrq", rxrq);
				String xz = "";
				if(StringUtils.isNotNull(xsjbxx.get("xz"))){
					xz = DateUtils.numToZh(xsjbxx.get("xz"))+"年";
				}
				data.put("xz", xz);
				HashMap<String, String> bxk = pjjgService.getBxk(model.getXh(),model.getPdxn());
				HashMap<String, String> pm = pjjgService.getCjPm(model.getXh(),model.getPdxn());
				data.put("bxk", bxk);
				data.put("pm", pm);
			}
			
			//中央民族大学
			if("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// 出生日期
				xsjbxx.put("rxny", xsjbxx.get("rxrq"));// 入学日期
				// 家庭成员
				PjjgService servicePjPy = new PjjgService();
				List<HashMap<String, String>> jtcyxxList_zymzdx = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_zymzdx, 5 - jtcyxxList_zymzdx.size());
				data.put("jtcyxxList_zymzdx", jtcyxxList_zymzdx);
				// 项目学年学期必修课成绩
				ZcfsService zcfService = new ZcfsService();
				HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
				data.put("zcf", zcf);
				List<HashMap<String,String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "必修");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList); 
				String xmXnxqBxCjPjf = servicePjPy.getPjf(xmXnxqBxCjList, 2); // 平均成绩
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				// 党团建设
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if(StringUtils.isNotNull(zzmmmc)){
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if((zzmmmc.contains("团员") && "02".equals(jddm))
							|| (zzmmmc.contains("预备党员") && "09".equals(jddm))
							|| (zzmmmc.contains("党员") && !zzmmmc.contains("预备党员") && "11".equals(jddm))){
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj,FomartDateType.day));
				// 已获奖项
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjg1 =  servicePjPy.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg1.size(); i++) {
					HashMap<String, String> pj = pjjg1.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("、");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
			}

			//河北工业大学
			if("10080".equals(Base.xxdm)){
				List<HashMap<String,String>> zzxmList = service.getZzxmInfoByXhXn(model.getXh(),model.getXn(),model.getXq());//本学年已享受的资助项目
				String yxszzxm = "";
				if(zzxmList.size()>0) {
					for (int i = 0; i < zzxmList.size(); i++) {
						HashMap<String, String> zzxmmap = zzxmList.get(i);
						yxszzxm += zzxmmap.get("xmmc");
						if (i<zzxmList.size()-1)
						{
							yxszzxm +=",";
						}
					}
					data.put("yxszzxm",yxszzxm);
				}

			}
			
			//西北民族大学
			if("10742".equals(Base.xxdm)) {
				data.put("xn1", model.getXn().trim().substring(0, 4));
		        data.put("xn2", model.getXn().trim().substring(5, 9));
		        String tbrq = model.getSqsj();// 申请时间
		        data.put("y", tbrq.trim().substring(0, 4));
		        data.put("m", tbrq.trim().substring(5, 7));
		        data.put("d", tbrq.trim().substring(8, 10));
		        CxpyService cxpyService = new CxpyService();
		        XsxxglService xsxxglService = new XsxxglService();
		        HashMap<String, String> cxpyDj = cxpyService.getCxpyByXhXnXq(xh, model.getXn(), model.getXq());
		        data.put("cxpyDj", cxpyDj.get("cxdjmc"));//操行评定
		        
		        // ========== 本学年成绩相关 begin ============
		        List<HashMap<String, String>> kcxxlist = xsxxglService.getStuCjOfXnList(xh, model.getXn());
		 
		        pjjgService.addBlankList(kcxxlist, 30 - kcxxlist.size());
				data.put("kcxxlist", kcxxlist); // 本学年成绩（课程信息）
					
				List<HashMap<String, String>> xfjdlist = xsxxglService.getStuXFJDOfXnList(xh, model.getXn());
		        data.put("xfjdlist", xfjdlist); // 总学分绩点、平均学分绩点
		        // ========== 本学年成绩相关 end ============
			}
			
			//东北石油
			if("10220".equals(Base.xxdm)) {
				String zwmc = service.getZwForXh(xh);
				data.put("zwmc", zwmc);
				if(zwmc == "") {
					data.put("sfyzw", "0");
				}
				String csrq = xsjbxx.get("csrq");
				data.put("csrq", csrq);
				String jtsfyqk = "";		
				if(jtqkmodel!=null 
						&& jtqkmodel.getJtqzqk()!=null 
						&& jtqkmodel.getJtqzqk().indexOf("无")==-1 
						&& jtqkmodel.getJtqzqk().indexOf("没有")==-1){
					jtsfyqk = "有";
				}else{
					jtsfyqk = "无";
				}
				
				data.put("jtsfyqk", jtsfyqk);// 家庭是否有欠款
				
				if(jtqkmodel != null){
					data.put("sfpkx", jtqkmodel.getSfpkx());// 是否贫困县
					data.put("pkxjb", jtqkmodel.getPkxjb());// 贫困县级别
					data.put("fmjk", jtqkmodel.getFmjk());// 父母是否有病或残疾
					data.put("fmjz", jtqkmodel.getFmjz());// 父母健在情况
				}else{  
					data.put("sfpkx", "");// 是否贫困县
					data.put("pkxjb", "");// 贫困县级别
					data.put("fmjk", "");// 父母是否有病或残疾
					data.put("fmjz", "");// 父母健在情况
				}
				HashMap<String, String> fqxxInfo = jtqkdcService.getfqInfo(xh);
				HashMap<String, String> mqxxInfo = jtqkdcService.getmqInfo(xh);
				data.put("fqzy", fqxxInfo.get("cyzy"));
				data.put("mqzy", mqxxInfo.get("cyzy"));
										
			}
			//广州铁路职业技术学院个性化
			if("13943".equals(Base.xxdm)){
				String sqly_13943 = HtmlUtil.xmlZy(model.getSqly());
				String sqly_13943_1 = sqly_13943;
				String sqly_13943_2 = "";
				if(sqly_13943.length() > 363){
					sqly_13943_1 = sqly_13943.substring(0, 363);
					sqly_13943_2 = sqly_13943.substring(363);
				}
				data.put("sqly_13943_1", sqly_13943_1);// 申请理由(第一部分)
				data.put("sqly_13943_2", sqly_13943_2);// 申请理由(第二部分)
				String xymc_13943 = xsjbxx.get("xymc");
				if(xymc_13943.length() < 39){
					int max = 39 - xymc_13943.length();
					for (int i = 0; i < max; i++) {
						xymc_13943 += " ";
					}
				}
				data.put("xymc_13943", xymc_13943);
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh_13943", qsbh);
				// 担任职务
				DwwhService dwwhService = new DwwhService();
				data.put("zwmc", dwwhService.getZwForXh(xh));
				ZcfsService zcfService = new ZcfsService();
				// 本学年上学期成绩
				PjjgService servicePjPy = new PjjgService();
				List<HashMap<String,String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", ""); 
				servicePjPy.addBlankList(bxn_cjSxqBxList, 12 - bxn_cjSxqBxList.size());
				data.put("bxn_cjSxqBxList", bxn_cjSxqBxList);
				HashMap<String, String> bxn_zcfSxq = zcfService.getZczfByXh(xh, model.getXn(), "01");
				data.put("bxn_zcfSxq", bxn_zcfSxq);
				// 本学年下学期成绩
				List<HashMap<String,String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				servicePjPy.addBlankList(bxn_cjXxqBxList, 12 - bxn_cjXxqBxList.size());
				data.put("bxn_cjXxqBxList", bxn_cjXxqBxList);
				HashMap<String, String> bxn_zcfXxq = zcfService.getZczfByXh(xh, model.getXn(), "02");
				data.put("bxn_zcfXxq", bxn_zcfXxq);
				// 评定学期
				String pdxq = model.getPdxq();
				if(StringUtils.isNull(pdxq)){
					pdxq = CsszService.XQKG;
				}
				// 项目学年学期成绩
				HashMap<String, String> xmZcf = zcfService.getZczfByXh(xh, model.getPdxn(), pdxq);
				data.put("xmZcf", xmZcf);
				// 班级人数
				CpmdService cpmdService = new CpmdService();
				String bjbjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getPdxn(), pdxq);
				data.put("bjbjrs", bjbjrs);
				int yjNum = 0; // 院级
				int xjNum = 0; // 校级
				int xjysNum = 0; // 校级以上
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pjjgMap = pjjg.get(i);
					String xmxzmc = pjjgMap.get("xmxzmc");
					if(StringUtils.isNotNull(xmxzmc)){
						if(xmxzmc.contains("院级")){
							yjNum++;
						}else if(xmxzmc.contains("校级以上")){
							xjysNum++;
						}else if(xmxzmc.contains("校级")){
							xjNum++;
						}
					}
				}
				data.put("yjNum", String.valueOf(yjNum));
				data.put("xjNum", String.valueOf(xjNum));
				data.put("xjysNum", String.valueOf(xjysNum));
				// 本学年上学期班级人数
				String bxn_cjSxqBjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), "01");
				data.put("bxn_cjSxqBjrs", bxn_cjSxqBjrs);
				// 本学年下学期班级人数
				String bxn_cjXxqBjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), "02");
				data.put("bxn_cjXxqBjrs", bxn_cjXxqBjrs);
			}
			//陕西师大
			if("10718".equals(Base.xxdm)){
				PjjgModel form = new PjjgModel();
				form.setXn(model.getXn());
				form.setXh(model.getXh());
				form.setCpbjdm(xsjbxx.get("bjdm"));
				form.setCpzydm(xsjbxx.get("zydm"));
				data.put("jejg", model.getJe());
		        data.put("xn1", model.getXn().trim().substring(0, 4));
		        data.put("xn2", model.getXn().trim().substring(5, 9));
		        String sqsj = model.getSqsj();
		        data.put("year", sqsj.trim().substring(0, 4));
		        data.put("mon",  sqsj.trim().substring(5, 7));
		        List<HashMap<String, String>> qgzxlist = service.getQgzxList(model.getXh());
		        data.put("qgzxlist", qgzxlist);
		        data.put("mdjlist", service.getMjxList(xh, "明德奖学金"));
		        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time=format.format(new java.util.Date());
		        data.put("y", time.trim().substring(0, 4));
		        data.put("m", time.trim().substring(5, 7));
		        data.put("d", time.trim().substring(8, 10));
		        if(service.getSfzxDk(xh).get("cs").equals("0")){
		        	data.put("zxdk", "否");
		        }else{
		        	data.put("zxdk", "是");
		        }
		        String sxn = (Integer.parseInt(model.getXn().trim().substring(0, 4))-1)+"-"+(Integer.parseInt(model.getXn().trim().substring(5, 9))-1);
		        HashMap<String, String> sfkns = service.getSfkns(xh, model.getXn());
		        if(sfkns.get("dcmc") == null){
		        	sfkns.put("dcmc", "家庭经济不困难");
		        }
		        List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
		        HashMap<String,String> sfxs = service.getSfxs();
		        data.putAll(sfkns);
		        if((sfxs.get("dqnd").trim()).equals(xsjbxx.get("rxrq").trim().substring(0, 4))){
		        	sfkns.put("isxs", "是");
		        }else{
		        	sfkns.put("isxs", "否");
		        }
		        JtqkdcDao jtqkDao = new JtqkdcDao();
		        List<HashMap<String, String>> cyListSXSD = jtqkDao.getJtcyListFour(model.getXh());
		        data.put("cyListSXSD", cyListSXSD);//家庭成员列表
		        data.put("blankList", 4 > cyList.size() ? jtqkdcService
						.getBlankList(4 - cyList.size()) : jtqkdcService
						.getBlankList(0));
		        ZzxmjgDao zzjgDao = new ZzxmjgDao();
		        data.put("zypm", zzjgDao.getXsZyBxPm(sxn, xsjbxx.get("zydm"), xh));
		        data.put("zyrs", zzjgDao.getZyrs(xsjbxx.get("zydm")));
		        data.put("pjcj", zzjgDao.getBxPjcj(xh, sxn));
		        data.put("zdf", zzjgDao.getZdf(xh, sxn));
		        data.put("zdfkmmc", zzjgDao.getZdfkmmc(xh, sxn));    
			}
			
			//江西陶瓷
			if("12930".equals(Base.xxdm)) {
				HashMap<String, String> cjpm = pjjgService.getCjPm(model.getXh(),model.getXn());
				data.put("cjpm", cjpm);
				HashMap<String, String> bxkms_12930 = pjjgService.getXakjdxylzjbxkms(model.getXh(),model.getXn());
				//必修课及格门数
				data.put("bxkjgms", bxkms_12930.get("bxkjgms"));
				data.put("bxkms", bxkms_12930.get("bxkms"));
				PjjgService servicePjPy = new PjjgService();
				PjjgAction pjjgAction = new PjjgAction();
				List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
				data.put("pjjgListhjqk", pjjgListhjqk);
				int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
				data.put("blankListhjqk", pjjgAction.getBlankList(size1));
				//励志用的评奖
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 3 - pjjgList.size());
				data.put("pjjgList1", pjjgList);
				int size=(3 - pjjgList.size())<0?0:(3 - pjjgList.size());
				data.put("blankList1", pjjgAction.getBlankList(size));
				ZzxmjgService zzxmjgservice_12930=new ZzxmjgService();
				List<HashMap<String, String>> shyjList = zzxmjgservice_12930.getShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+i, shyjList.get(i).get("shyj"));
				}
			}
			String jtrjysrtemp = " ";
			try {
				jtqkmodel = jtqkmodel == null ? new JtqkdcForm() : jtqkmodel;
				String jtnzsr = jtqkmodel.getJtnzsr() != null ? jtqkmodel.getJtnzsr() : "0";
				String jtrs = jtqkmodel.getJtrs() != null ? jtqkmodel.getJtrs() : "0";
				if (!jtrs.equals("0")) {
					jtrjysrtemp = String.valueOf((int) (Double.valueOf(jtnzsr) / Double.valueOf(jtrs) / 12));
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
			data.put("jtrjysrtemp", jtrjysrtemp);// 家庭人均月收入
			//重庆信息职业技术学院
			if ("12755".equals(Base.xxdm)) {
				List<HashMap<String, String>> cyList3line = jtqkdcService.getJtcyList(xh, 3); //取3条家庭成员列表，不足3条填空
				data.put("cyList3line", cyList3line);
				data.put("hkxian", xsjbxx.get("hkxian"));//户口县
				String tbrq = model.getSqsj();// 申请时间
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				ZcfsService zcfService = new ZcfsService();
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// 班级人数
				data.put("cjpm", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
				String mzmc = xsjbxx.get("mzmc").trim();
				String ssmz = "";
				if("汉族".equals(mzmc)){
					ssmz="否";
				}else if(!"汉族".equals(mzmc) && !"".equals(mzmc) && mzmc != null){
					ssmz="是";
				}
				data.put("ssmz", ssmz);
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjg4_12755 = pjjgservice.getHjqkByXhMap(xh, 4);  //获取最新4条评奖结果
				for (HashMap<String, String> hashMap : pjjg4_12755) {
					String xmmc = hashMap.get("xmmc");
					if(xmmc!=null && !"".equals(xmmc)){
						hashMap.put("bjdw","重庆信息技术职业学院 ");
					}
				}
				data.put("pjjg4_12755", pjjg4_12755);
				if (jtqkmodel != null && jtqkmodel.getJtnzsr() != null) {
					data.put("jtrjysr12755", jtqkmodel.getJtrs() == null ? "" :  String.format("%.2f",Double.parseDouble(jtqkmodel.getJtnzsr()) / 12 / Integer.parseInt(jtqkmodel.getJtrs())));//家庭人均月收入
					data.put("jtyzsr12755", String.format("%.2f", Double.parseDouble(jtqkmodel.getJtnzsr()) / 12));//家庭月总收入
				} else {
					data.put("jtyzsr12755", "");
					data.put("jtrjysr12755","");
				}
			}
			//杭州师范个性化
			if("10346".equals(Base.xxdm)){
				ZcfsService zcfService = new ZcfsService();
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// 班级人数
				data.put("cjpm", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数

				//加载评奖结果信息
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjg4line = pjjgservice.getHjqkByXhMap(xh, 4);  //获取最新4条评奖结果
				for (HashMap<String, String> hashMap : pjjg4line) {
					String xmmc = hashMap.get("xmmc");
					if(xmmc!=null && !"".equals(xmmc)){
						hashMap.put("bjdw","杭州师范大学");
					}
				}
				data.put("pjjg4_hzsf", pjjg4line);
				
				//加载资助信息（3条）
				List<HashMap<String, String>> zzdwlist = service.getZzxmjgInfoList(xh);
				int m=3-zzdwlist.size();
				for (int i = 0; i <m; i++) {
					zzdwlist.add(new HashMap<String, String>());
				}
				List<HashMap<String, String>> pjjg3line = pjjgservice.getHjqkByXhMap(xh, 3);  //获取最新3条评奖结果
				//将资助信息放入评奖结果中，方便xml文件读取
				for (int i = 0; i < pjjg3line.size(); i++) {
					pjjg3line.get(i).put("zzxmmc", zzdwlist.get(i).get("xmmc"));
					pjjg3line.get(i).put("zzje", zzdwlist.get(i).get("je"));
				}
				data.put("pjjg3_hzsf", pjjg3line);
				//助学贷款信息
				XszzSqshService xszzSqshService = new XszzSqshService();
				String dkxx = xszzSqshService.getXsDkxx(Base.currXn, xh);
				data.put("zxdkxx", dkxx);
				
			}
			XszzSqshService xszzSqshservice = new XszzSqshService();
			
			String theSameXmmcNum = xszzSqshservice.getTheSameZzxmNumber(bbMap.get("bbmc"), xh);  //以前申请相同项目的数量
			//已申请且批准通过的相同项目的数量
			data.put("theSameXmmcNum", theSameXmmcNum);
			
			List<HashMap<String,String>> ywqtjxj = xszzSqshservice.getYwqtjxList(bbMap.get("bbmc"), xh);
			//大学期间同时享受其他奖(助)学金
			data.put("ywqtjxj", ywqtjxj==null?new ArrayList<HashMap<String,String>>():ywqtjxj);
			
			String szbbjrs = xszzSqshservice.countBjRs(xh);
			
			data.put("szbbjrs", szbbjrs);  //所在班班级人数
			//加载项目名称
			data.put("bbmc", bbMap.get("bbmc"));
			data.put("xmmc", model.getXmmc());
			//浙江交通职业技术学院
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getGuid());
				for (int i = 0; i < shyjjtzyList.size(); i++) {
					data.put("shyj"+(i+1), shyjjtzyList.get(i).get("shyj"));
				}
			}
			WdgwsqService wdgwsqService = new WdgwsqService();
			HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
			String qsh=qsxx.get("qsh");
			data.put("qsh", qsh);
			
			
			//通用审核意见1-7级
			//先根据guid取到业务流程id,然后根据业务流程id取到审核意见
			String shyjGuid = service.getSqbIdByYwid(myForm.getGuid());
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(shyjGuid);
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			/*String csny = xsjbxx.get("csrq");//出生日期
			if (csny != null) {
				csny = csny.replace("-","");
				if(csny.length() >= 6){
					csny = csny.substring(0, 6);
				}
			}
			
			data.put("csny", csny);// 出生年月,根据出生日期截取
			 */
			data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
			data.put("photo", photo);// 学生照片
			//File wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR
				//	+ bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]"+"-"+model.getXmdm());//"classpath://templates//" + "xszz" + "gjjxjb.xml"
			File wordFile =null;
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(xsjbxx.get("bjmc")+""+xsjbxx.get("xh")+"["+xsjbxx.get("xm")+"]"));
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(model.getXh()+"["+xsjbxx.get("xm")+"]"));
			}
			return wordFile;
		}

		return null;
	}
	
	
	/**
	 * 
	 * @描述:打印excel-支持多选
	 * @作者：wanghj
	 * @日期：2014-1-14 上午11:23:46
	 * @修改记录:
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
	public ActionForward downloadMultiExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String rows =  request.getParameter("rows");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		//---------获取模板数据和内容-----------------------------------------------------
		HashMap<String, String> bbMap = null;
		User user = getUser(request);
		ZzxmjgService service = new ZzxmjgService();
		List<HashMap<String, String>> zzxmjgLists = new ArrayList<HashMap<String, String>>();
		int count=1;
		if(rowsList!=null && rowsList.size()>0){
			for (Object object : rowsList) {
				MorphDynaBean rowBean = (MorphDynaBean) object;
				ZzxmjgForm model = new ZzxmjgForm();
				model.setLbdm((String) rowBean.get("lbdm"));
				model.setXn((String) rowBean.get("xn"));
				model.setXq((String) rowBean.get("xq"));
				model.setXmmc((String) rowBean.get("xmmc"));
				if(rowBean.get("xmmc")!=null){
					if(bbMap==null){
						bbMap = this.getBbMap(model);
					}
				}
				List<HashMap<String, String>> zzxmjgList = new ArrayList<HashMap<String, String>>();
				
				//闽南师范学院个性化
				if("10402".equals(Base.xxdm)){
					zzxmjgList = service.zzxmhzView_10402(model, user);//不分页
				}else if("11483".equals(Base.xxdm)){
					zzxmjgList = service.getXmGxhDy_12947_gjzxjbahzexcel(model.getXmmc(),model.getXn(),model.getXq());
				}else if("14008".equals(Base.xxdm)){
					zzxmjgList = service.getDxscbInfoList(model, user); // 不分页
				}else if("12898".equals(Base.xxdm)){
					zzxmjgList = service.getLnjdzyjsxyList(model, user); //辽宁机电职业技术学院
				}else if("12930".equals(Base.xxdm)){
					zzxmjgList = service.getJxtczzxmList(model, user); //江西陶瓷
				}else if("10351".equals(Base.xxdm)){
					zzxmjgList = service.getWzdxzzxmList(model, user);
				}else{
					// 通用
					zzxmjgList = service.zzxmhzView(model, user, false);//不分页
				}
				
				for(HashMap<String, String> map:zzxmjgList){
					map.put("sqly",map.get("sqly"));
					map.put("row",String.valueOf(count));//加个序号
					map.put("rxny",DateTranCnDate.fomartDateToCn(map.get("rxrq"),FomartDateType.month));//入学年月   XXXX年XX月
					zzxmjgLists.add(map);
					count ++;
				}
			}
		}
		//--------------------------------------------------------------------------------
		if (bbMap == null || bbMap.size() == 0) {//查询不到相关联报表信息
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		
		if(zzxmjgLists!=null && zzxmjgLists.size()>0){
			File excelFile = getExcel(zzxmjgLists,bbMap,user);
			if(excelFile!=null){
				FileUtil.outputExcel(response, excelFile);
			}
		}
		return null;
	}


	/**
	 * @描述:项目汇总上报表打印（选择了学院的）
	 * @作者：lgx [工号：1553]
	 * @日期：2018/8/27 17:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadMultiExcelByXy(ActionMapping mapping, ActionForm form,
											HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String rows =  request.getParameter("rows");
		String[] xydms =  request.getParameter("xydms").split(",");
		rows = "{data:" + URLDecoder.decode(URLDecoder.decode(rows,"UTF-8"),"UTF-8") + "}";
		List rowsList = JsonUtil.jsonToList(rows);
		User user = getUser(request);
		ZzxmjgService service = new ZzxmjgService();
		List<File> files = new ArrayList<File>();
		String xmmc = "";
		//---------获取模板数据和内容-----------------------------------------------------

		for(String xydm : xydms){
			int count=1;
			HashMap<String, String> bbMap = null;
			List<HashMap<String, String>> zzxmjgLists = new ArrayList<HashMap<String, String>>();
			if(rowsList!=null && rowsList.size()>0){
				for (Object object : rowsList) {
					MorphDynaBean rowBean = (MorphDynaBean) object;
					ZzxmjgForm model = new ZzxmjgForm();
					model.setType("excelByXy");
					model.setXydm(xydm);
					model.setLbdm((String) rowBean.get("lbdm"));
					model.setXn((String) rowBean.get("xn"));
					model.setXq((String) rowBean.get("xq"));
					model.setXmmc((String) rowBean.get("xmmc"));
					xmmc=(String) rowBean.get("xmmc");
					if(rowBean.get("xmmc")!=null){
						if(bbMap==null){
							bbMap = this.getBbMap(model);
						}
					}
					List<HashMap<String, String>> zzxmjgList  = service.zzxmhzView(model, user, false);//不分页
					for(HashMap<String, String> map:zzxmjgList){
						map.put("sqly",map.get("sqly"));
						map.put("row",String.valueOf(count));//加个序号
						map.put("rxny",DateTranCnDate.fomartDateToCn(map.get("rxrq"),FomartDateType.month));//入学年月   XXXX年XX月
						zzxmjgLists.add(map);
						count ++;
					}
				}
			}
			//--------------------------------------------------------------------------------
			if (bbMap == null || bbMap.size() == 0) {//查询不到相关联报表信息
				throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
			}
			if(zzxmjgLists!=null && zzxmjgLists.size()>0){
				bbMap.put("type","excelByXy");
				File excelFile = getExcel(zzxmjgLists,bbMap,user);
				if(excelFile!=null){
					files.add(excelFile);
				}
			}else{
				//获取空的Excel
				String xymc = new PjjgService().getXymcBydm(xydm);
				String fileName = FreeMarkerUtil.getFileName(xmmc+"_"+xymc);
				File file = new File(System.getProperty("java.io.tmpdir"),fileName+".xls");
				if(!file.exists()){
					file.createNewFile();
				}
				WritableWorkbook wwb = Workbook.createWorkbook(file);
				WritableSheet sheetNull = wwb.createSheet("本次导出数据为空", 0);
				sheetNull.setColumnView(0, 15);
				Label msg = new Label(0, 0,"暂无数据！");
				sheetNull.addCell(msg);
				wwb.write();
				wwb.close();
				files.add(file);
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
		return null;
	}


	// 填充模版数据生成excel文件
	private File getExcel(List<HashMap<String, String>> zzxmjgLists,HashMap<String, String> bbMap, User user) throws Exception {
		double zje = 0;
		String xymc = "";
		if(zzxmjgLists != null && !zzxmjgLists.isEmpty()){
			for (int i = 0; i < zzxmjgLists.size(); i++) {
				if(!"".equals(zzxmjgLists.get(i).get("je"))&&StringUtils.isNotNull(zzxmjgLists.get(i).get("je"))){
					zje = zje + Double.parseDouble(zzxmjgLists.get(i).get("je")) ;
				}
			}
			xymc = zzxmjgLists.get(0).get("xymc");
		}
		String xn = zzxmjgLists.get(0).get("xn");
		String xmmc = zzxmjgLists.get(0).get("xmmc");
		String pdxn = zzxmjgLists.get(0).get("pdxn");//评定学年
		String xh = zzxmjgLists.get(0).get("xh");
		String xq = zzxmjgLists.get(0).get("xq");
		Map<String, Object> data = new HashMap<String, Object>();
		ZzxmjgService service = new ZzxmjgService();
		Map<String, String> jzgInfo = service.getJzgInfo(user); //教职工信息
		//温州大学
		if("10351".equals(Base.xxdm)) {
			HashMap<String, String> bxkms_10351 = service.getWzdxbxkms(xh, xn);
			data.put("bxkjgms", bxkms_10351.get("bxkjgms"));
			data.put("bxkms", bxkms_10351.get("bxkms"));
			ZcfsService zcfService = new ZcfsService();
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, xn, xq);
			
			HashMap<String, String> pm = pjjgService.getCjPm(xh,xn);
			data.put("pm", pm);
		}
		// 重庆三峡医药高等专科学校
		if("14008".equals(Base.xxdm)) {
			String beforXn = xn.substring(0, 4);
			String afterXn = xn.substring(5, 9);
			data.put("beforXn", beforXn);
			data.put("afterXn", afterXn);
		}
		
		JtqkdcService jtqkdcService = new JtqkdcService();
		
		data.put("blankList", ZZJGSIZE > zzxmjgLists.size() ? jtqkdcService.getBlankList(ZZJGSIZE - zzxmjgLists.size()) : jtqkdcService.getBlankList(0));
		data.put("zzxmjgList", zzxmjgLists);//资助项目结果列表
		data.put("bbMap", bbMap);
		data.put("xxmc", Base.xxmc);// 学校名称
		data.put("xn", xn);
		data.put("dqrq", DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));//山西财经大学，取当前打印日期
		data.put("currxn", Base.currNd);
		data.put("xmmc", xmmc);
		data.put("pdxn", pdxn);//评定学年
		data.put("today", DateUtils.getLyr());
		data.put("month", DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.month)); //月份
		data.put("jzgInfo", jzgInfo);
		data.put("zje",zje);
		String templateDirectory = Constants.TEP_DIR + bbMap.get("mblj");
		String templateName = bbMap.get("mbmc");
		String fileName = FreeMarkerUtil.getFileName(xmmc);
		if(StringUtils.equals("12036", Base.xxdm) && StringUtils.equals("excelByXy",bbMap.get("type"))){
			fileName = FreeMarkerUtil.getFileName(xmmc+"_"+xymc);
		}
		File wordFile = FreeMarkerUtil.getExcelFile(data, templateDirectory, templateName, fileName);
		return wordFile;
	}

	
	// 根据项目名称获取报表信息
	public HashMap<String, String> getBbMap(ZzxmjgForm model) throws Exception{
		HashMap<String, String> bbMap = null;// 报表
		
		if (!StringUtil.isNull(model.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(),model.getXn(),model.getXq());//查询项目记录
			if (xmMap != null) {
				//model.setXmdm(xmMap.get("xmdm"));//项目代码
				BbwhService bbwhService = new BbwhService();
				bbMap = bbwhService.getDataById(xmMap.get("dysbbb"));//获取对应上报报表
			}
		}
		if (bbMap == null || bbMap.size() == 0) {//查询不到相关联报表信息
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		return bbMap;
	}
	
	/*public List getNewZzjgLists(List<HashMap<String, String>> zzxmjgLists){
		List<HashMap<String, String>> newzZxmjgList = new ArrayList<HashMap<String, String>>();
		boolean flag = true;
		for(int i=0;i<zzxmjgLists.size()-1;i++){
			newzZxmjgList.add(zzxmjgLists.get(i));
			for(int j=zzxmjgLists.size();j>i;j--){
				if(zzxmjgLists.get().get("xmmc")==zzxmjgLists.get(i).get("xmmc")){
					
				}
			}
			if(zzxmjgLists.get(i+1).get("xmmc")!=zzxmjgLists.get(i).get("xmmc")){
				flag = false;
			}else{
				newzZxmjgList.add(zzxmjgLists.get(i+1));
			}
		}
		
		return null;
		
	}*/
	/**
	 * 资助结果复制
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zzjgCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgService service = new ZzxmjgService();
		request.setAttribute("kfzZqList", service.getkfzZqList(Base.currXn));
		request.setAttribute("xn",Base.currXn);
		this.saveToken(request);
		return mapping.findForward("zzjgCopy");
	}
	/**
	 * 
	 * @描述:考核复制保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-18 下午03:35:56
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
	public ActionForward saveCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		ZzxmjgService service = new ZzxmjgService();
		String lyxn=request.getParameter("lyxn");
		String mbxn=request.getParameter("mbxn");
		boolean result = service.copy(lyxn,mbxn);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//山东畜牧兽医职业学院个性化需求（社会助学金汇总表）
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getSdxm_shzxjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
			 {
		    ZzxmjgForm model = (ZzxmjgForm) form;
			ZzxmjgDao dao = new ZzxmjgDao();
			Map<String,Object> data = new HashMap<String,Object>();
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = dao.getDcList(model,user);
			String zrs = "";
			float xmze1 = 0;
			for (HashMap<String, String> hashMap : resultList) {
				if(zrs.equals("")&&hashMap.get("total")!= null){
					zrs = hashMap.get("total");
				}
				if(hashMap.get("xmje")!= null){
					xmze1 = xmze1+ Float.parseFloat(hashMap.get("xmje"));
				}
			}
			if(zrs.equals("")){
				zrs = "0";
			}
			String xmze = xmze1+"";
			data.put("xmze",xmze);
			data.put("zrs", zrs);
			data.put("xsxxlist",resultList);
			String xmlb1 = request.getParameter("value");
			String[] xmlb = xmlb1.split(",");
			data.put("xmlb", xmlb[0]);
			File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_shzxjhzb.xml", "社会助学金学生汇总表");
			FileUtil.outputWord(response, excelFile);
			return null;
	}
	
	/*山东畜牧兽医职业学院个性化需求（国家励志奖学金汇总表）
	public ActionForward getSdxm_gjlzjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		User user = getUser(request);
		ZzxmjgService pjjgserice = new ZzxmjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_gjlzjhzexcel(values,user);
		FileUtil.outputWord(response, excelFile);
		return null;
	}*/
	
	/*山东畜牧兽医职业学院个性化需求（国家助学金汇总表）
	public ActionForward getSdxm_gjzxjhzexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		ZzxmjgService pjjgserice = new ZzxmjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_gjzxjbahzexcel(values);
		FileUtil.outputWord(response, excelFile);
		return null;
	}*/
	
	//山东畜牧兽医职业学院个性化需求（国家助学金汇总表）
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getSdxm_gjlzjhzmbexcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String[] values = value.split(",");
		ZzxmjgService pjjgserice = new ZzxmjgService();
		File excelFile = pjjgserice.getXmGxhDy_12947_gjlzjhzmbexcel(values);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 资助项目汇总导出（陕西师大）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-12-8 下午01:19:07
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
	public ActionForward exportDataSx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.zzxmhzView(model, user, false);// 查询出所有记录，不分页

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
     * @throws Exception 
     * @throws IOException 
     * 
     * @描述:上海体育
     * @作者：yxy[工号：1206]
     * @日期：2016-10-15 上午10:46:58
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * ActionForwad 返回类型 
     * @throws
     */
	public ActionForward exportShty_hzdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		String[] params = request.getParameterValues("params");
		ZzxmjgService service = new ZzxmjgService();
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("shty_zzxmjehz_tjdc.xls".getBytes(), "GBK") + "\"");
		service.createWwb(params,response.getOutputStream());
		return null;
	}
	
	/** 
	 * @描述:青岛酒店管理个性化导入(加入身份证号个性化导入)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-2 下午03:28:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward drForQdjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("drForQdjd");
	}
	
	/**
	 * @throws IOException  
	 * @描述:下载导入模板(青岛酒店管理职业技术学院个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-2 下午03:45:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取青岛酒店职业技术学院导入模板
		String temPath = request.getSession().getServletContext().getRealPath("/temp/mb/zzjgmb_13011.xls");
		File file = new File(temPath);
		if(file.exists()){
			response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("资助结果导入模板.xls","utf-8")); 
			FileUtil.outputFile(response, file);
		}
		return null;		
	}
	
	/** 
	 * @描述:保存导入(青岛酒店管理职业技术学院)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-2 下午04:26:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward saveDrForQdjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ZzxmjgForm model = (ZzxmjgForm)form;
		FormFile file = model.getDrmb();
		ZzxmjgService service = new ZzxmjgService();
		if(file != null){
			try {
				model.setFilepath(servlet.getServletContext().getRealPath(
				"/temp/importTemp/")+"/");
				try {
					HashMap<String,Object> resultMap = service.saveDrForQdjd(model);
					String message = CG;
					if(resultMap.get("result").equals("true")){
						 Map<String,String> map = new HashMap<String, String>();
							map.put("message", message);
							JSONObject json = JSONObject.fromObject(map);
							response.getWriter().print(json);
							return null;
					}else if(resultMap.get("result").equals("null")){
						 message = NOCONTENT;
						 Map<String,String> map = new HashMap<String, String>();
							map.put("message", message);
							JSONObject json = JSONObject.fromObject(map);
							response.getWriter().print(json);
							return null;
					}
					else{
					    message = SB;
					    Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						map.put("gid", (String)resultMap.get("gid"));
						if(resultMap.get("cgs") != null){							
							map.put("cgs", (String)resultMap.get("cgs"));
						}else{
							map.put("cgs", "0");
						}
						map.put("cws", (String)resultMap.get("cws"));
						JSONObject json = JSONObject.fromObject(map); 
					    response.getWriter().print(json);
						return null;
					}
				} catch (BiffException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				logger.info("导入文件未找到！");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				logger.info("IO异常！");
				e.printStackTrace();
			}
		}else{
			    String message = KONG;
			    Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				try {
					response.getWriter().print(json);
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				return null;
		}
		
		return null;		
	}
	
	/** 
	 * @描述:下载错误数据(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 下午03:21:19
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
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//得到tomcat/webapp/temp/importTemp下错误文件的路径
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("错误数据.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @description	： 国家奖项导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-24 下午03:14:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxdc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzxmjgForm model = (ZzxmjgForm) form;
		ZzxmjgService service = new ZzxmjgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		
		List<HashMap<String, String>> resultList = null;
		
		// 查询
		String dcclbh = request.getParameter("dcclbh");
		//国家奖学金导出
		if(dcclbh.equalsIgnoreCase("xg_xszz_gjjxz.do")){			
			resultList = service.getGjjxjdc(model,user);// 查询出所有记录，不分页
		}else if(dcclbh.equalsIgnoreCase("xg_xszz_gjzxz.do")){//国家助学金导出
			resultList = service.getGjzxjdc(model,user);
		}else if(dcclbh.equalsIgnoreCase("xg_xszz_gjlzj.do")){
			resultList = service.getGjlzjdc(model,user);
		}else if(dcclbh.equalsIgnoreCase("xg_xszz_xmzzqkhz.do")){
			resultList = service.getXmzzqkhz(model,user);
		}

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(dcclbh);// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}


