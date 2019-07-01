/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsh;

import java.io.File;
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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.gygl.zzdgl.sh.ZzdshForm;
import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgService;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshForm;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxsh.LxshForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @className	： HdblshAction
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-18 下午04:49:47
 * @version 	V1.0 
 */

public class HdblshAction extends SuperAction<HdblsqshForm, HdblshService> {
	private static final String url = "hdgl_hdbl_hdblsh.do";
	
	//学生基本信息配置
	private static List<HashMap<String, String>> jbxxList = null;

	public static String HDBL = "hdbl";

	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	
	/**
	 * @description	： 获取审核列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-18 下午04:55:22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdblshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		HdblshService hdblshService = new HdblshService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = hdblshService.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "hdgl_hdbl_hdblsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdblshList");
	}
	
	/**
	 * @description	： 单个审核
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-19 下午03:31:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward sbDgsh (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		HdblshService hdblshService = new HdblshService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 保存单个审核
			boolean result = hdblshService.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HdblsqshService hdblsqshService = new HdblsqshService();
		HashMap<String, String> map = hdblsqshService.gethdblInfo(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(map));
		/*活动标签*/
		if(StringUtils.isNotNull(map.get("hdbq"))){
			model.setHdbq(map.get("hdbq"));
			model.setHdbqs(map.get("hdbq").split(","));
			model.setHdbqmc(map.get("hdbqmc"));
		}
		/*能力标签*/
		if(StringUtils.isNotNull(map.get("nlbq"))){
			model.setNlbq(map.get("nlbq"));
			model.setNlbqs(map.get("nlbq").split(","));
			model.setNlbqmc(map.get("nlbqmc"));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		model.setShid(request.getParameter("shid"));
		
		//获取最后一级信息
		hdblshService.getModelForSh(model);
		
		HdbljgService hdbljgService = new HdbljgService();

		//活动类型列表
		List<HashMap<String, String>> hdlxList = hdblsqshService.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);

		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);

		/*讲座类型列表*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//自选课程列表
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*能力标签*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);

		/*主讲人职称*/
		List<HashMap<String, String>> zjrzcList = hdblsqshService.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);

		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("hdblDgsh");
	}
	
	/**
	 * @description	： 撤销审核
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 下午04:55:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setSplc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		HdblshService service = new HdblshService();
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * @description	：最后一级撤销
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 下午05:04:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		HdblshService service = new HdblshService();
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 上午10:18:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdblsqshForm model = (HdblsqshForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HdblshService service = new HdblshService();
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页
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
