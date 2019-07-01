/**
 * @部门:学工产品事业部
 * @日期：2013-6-18 上午10:49:05 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.gygl.xjdgybz.ktsqjg.KtsqjgForm;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 学生岗位-学生申请岗位审核
 * @作者： zhangjw
 * @时间： 2013-6-18 上午10:49:05
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsgwshAction extends SuperAction {
	
	private static final String url = "qgzx_xsgwsh.do?method=xsgwshCx";

	private static List<HashMap<String, String>> jbxxList = null;

	static {
		// 学生基本信息显示配置
		jbxxList = new BdpzService().getJbxxpz("cjff");
	}

	/**
	 * @描述:
	 * @作者：zhangjw
	 * @日期：2013-6-18 下午03:39:53
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
	public ActionForward xsgwshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();

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
		String path = "qgzx_xsgwsh.do?method=xsgwshCx";
		// 最后一级撤销审核后调用的路径
		request.setAttribute("cancelPath", "qgzx_xsgwsh.do?method=cancel");
		request.setAttribute("path", path);
		QgCommUtilf.setCzpath(request, null);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsgwshCx");
	}
	
	
	public ActionForward checkSplc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splcid=request.getParameter("splc");
		String type=request.getParameter("type");
		XsgwshService service = new XsgwshService();
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("message",service.isHaveSplcSj(splcid,type).toString());
		response.getWriter().print(JSONObject.fromBean(map));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();
		HashMap<String, String> shxx = ShlcUtil.getShxx(model.getShid());
		// 业务回滚
		boolean result = service.cancel(model.getSplc(), shxx.get("ywid"));
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	public ActionForward lzcancel(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();
		HashMap<String, String> shxx = ShlcUtil.getShxx(model.getShid());
		// 业务回滚
		boolean result = service.lzcancel(model.getSplc(), shxx.get("ywid"));
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		String ids=request.getParameter("ids");
		String shyj=request.getParameter("shyj");
		String shgws=request.getParameter("shgws");
		String bmlbs=request.getParameter("bmlbs");
		String shzt=request.getParameter("shzt");
		String message=request.getParameter("message");
		XsgwshService service = TransactionControlProxy.newProxy(new XsgwshService());
		User user = getUser(request);
		if(SAVE.equals(model.getType())){
			List<HashMap<String, String>> list=service.plsh(ids.split(","),shgws.split(","),bmlbs.split(","),message.split(","),shyj,shzt,user);
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
		}
//		request.setAttribute("ids", ids);
//		request.setAttribute("shgws", shgws);
//		request.setAttribute("message", message);
		return mapping.findForward("plsh");
	}
	public ActionForward lzplsh(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		String ids=request.getParameter("ids");
		String shyj=request.getParameter("shyj");
		String shgws=request.getParameter("shgws");
		String shzt=request.getParameter("shzt");
		XsgwshService service = new XsgwshService();
		User user = getUser(request);
		if(SAVE.equals(model.getType())){
			int flag =service.lzplsh(ids.split(","),shgws.split(","),shyj,shzt,user);
			String msg = flag==0 ? "审核成功！"
					: "部分审核成功，失败"+flag+"条！";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", msg);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		return mapping.findForward("plsh");
	}
	/**
	 * @描述:学生岗位审核
	 * @作者：zhangjw
	 * @日期：2013-7-1 上午09:04:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward xsgwSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsgwshForm model = (XsgwshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		XsgwshService service = TransactionControlProxy.newProxy(new XsgwshService());
		String type = model.getType();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 困难生认定信息
		/*KnsjgDao knsjgDao = new KnsjgDao();
		List<KnsjgForm> knslist = new ArrayList<KnsjgForm>();
		List<String[]> list = new ArrayList<String[]>();

		list.addAll(knsjgDao.getKnsjgList(model.getXh()));

		if (list != null && list.size() > 0) {
			for (String[] kns : list) {
				KnsjgForm knsjg = new KnsjgForm();
				knsjg.setXn(kns[0]);
				knsjg.setXqmc(kns[1]);
				knsjg.setDcmc(kns[2]);
				knsjg.setSqsj(kns[3]);
				knslist.add(knsjg);
			}
		}*/

		// 设置学生基本信息
		szXsxx(request, model.getXh());
		XsgwshForm modelN = service.getModel(model);
		modelN.setShid(model.getShid());
		request.setAttribute("model", modelN);
//		request.setAttribute("knslist", knslist);
		
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());
		request.setAttribute("qgmxList", new WdgwsqService().getQgmxList(model.getXh()));
		//参数设置
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", StringUtils.formatData(csszService.setZjmrCs(request)));
		
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("bmlb", model.getBmlb());
		if("10344".equals(Base.xxdm)){
			if(StringUtils.isNotNull(model.getXh())){
				KnsjgService knsjgService = new KnsjgService();
				List<HashMap<String, String>> knsInfoList = knsjgService
						.getKnsInfoList(model.getXh());
				request.setAttribute("knsInfoList", knsInfoList);
			}
		}

		if ("ck".equalsIgnoreCase(type)) {
			return mapping.findForward("xsgwCk");
		}
		return mapping.findForward("xsgwsh");
	}

	/**
	 * @描述:TODO获取学生基本信息
	 * @作者：zhangjw
	 * @日期：2013-6-17 下午02:30:28
	 * @param request
	 * @param xh
	 *            void 返回类型
	 */
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request, String xh) {
		// 查询学生信息
		if (xh != null && !"".equals(xh)) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		request.setAttribute("jbxxList", jbxxList);
	}

	/**
	 * @描述:TODO审核前验证
	 * @作者：zhangjw
	 * @日期：2013-6-27 上午11:25:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward yzgwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();
		// 增加人数控制级别设定 验证
		String message = service.yzjb(model,false);
		if (message.equals("true")) {
			response.getWriter().print(service.yzsh(model));
		} else {
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("message", message);
			response.getWriter().print(JSONObject.fromObject(resultmap));
		}
		return null;
	}

	public ActionForward loadRskz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid 为选择的审批流程代码值
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;
	}

	public ActionForward cxRollBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshService service = new XsgwshService();
		User user = getUser(request);
		String shid = request.getParameter("shid");
		try {
			service.cxRollBack(user.getUserName(), shid);
		} catch (Exception e) {
			throw new Exception("回滚审核岗位错误！" + e.getMessage());
		}
		return null;
	}

	public ActionForward zgxsList(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();
		if(QUERY.equals(model.getType())){
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getZgxsList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_xsgwsh.do?method=zgxsList");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zgxsList");
	}

	public ActionForward zjzgxs(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if(SAVE.equals(model.getType())){
			boolean flag = service.zjZgxs(model);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("path", "qgzx_xsgwsh.do?method=zjzgxs");
		return mapping.findForward("zjzgxs");
	}

	public ActionForward xsgwmxck(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xh = request.getParameter("xh");
		String gwdm = request.getParameter("gwdm");
		szXsxx(request, xh);
		XsgwshService service = new XsgwshService();
		HashMap<String,String> gwxx = service.getZgxx(gwdm,xh);
		request.setAttribute("model",gwxx);
		return mapping.findForward("xsgwmx");
	}

	public ActionForward pljg(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();

		String jgData = request.getParameter("jgList");

		if(SUBMIT.equals(model.getType())){
			int flag = service.savePlLzSq(model);
			String message = flag==0 ? "申请成功！"
					: "部分申请成功，失败"+flag+"条！";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		request.setAttribute("jgData",jgData);
		return mapping.findForward("pljg");
	}

	public ActionForward xslzshCx(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsgwshForm model = (XsgwshForm) form;
		XsgwshService service = new XsgwshService();
		if(QUERY.equals(model.getType())){
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getLzxsList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_xsgwsh.do?method=xslzshCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xslzshCx");
	}
	public ActionForward xslzSh(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsgwshForm model = (XsgwshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		XsgwshService service = new XsgwshService();
		String type = model.getType();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveLzSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 设置学生基本信息
		szXsxx(request, model.getXh());
		request.setAttribute("model", service.getLzModel(model));
		request.setAttribute("xh",model.getXh());
		if ("ck".equalsIgnoreCase(type)) {
			return mapping.findForward("xsgwCk");
		}
		return mapping.findForward("xslzsh");
	}

	public ActionForward ckgzjl(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		String xh = request.getParameter("xh");

		if (QUERY.equals(type)) {
			WdgwsqForm model = new WdgwsqForm();
			WdgwsqService service = new WdgwsqService();
			model.setXh(xh);
					// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getWdGwList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_xsgwsh.do?method=gzjlList");
		request.setAttribute("xh",xh);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gzjlList");
	}

	public ActionForward ckgzsc(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;
		if (QUERY.equals(model.getType())) {
			// 查询
			XsgwshService service = new XsgwshService();
			List<HashMap<String, String>> resultList = service.getGzscList(
					model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xh",model.getXh());
		request.setAttribute("gwdm",model.getGwdm());
		request.setAttribute("path","qgzx_xsgwsh.do?method=ckgzsc");
		return mapping.findForward("ckgzsc");
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgwshForm model = (XsgwshForm) form;

		//生成高级查询对象
		CommService comService = new CommService();
		XsgwshService service = new XsgwshService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setMaxPage(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getZgxsList(model,user);

		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
