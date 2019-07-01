/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.hdgl.hdblsh.HdblshService;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshForm;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @className	： HdbljgAction
 * @description	： 活动结果action(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-23 上午10:55:47
 * @version 	V1.0 
 */

public class HdbljgAction extends SuperAction<HdbljgForm, HdbljgService> {
	private static final String url = "hdgl_hdbl_hdbljg.do";
	//学生基本信息配置
	private static List<HashMap<String, String>> jbxxList = null;
	
	public static String HDBL = "hdbl";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	
	/**
	 * @description	： 活动结果list
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 上午10:57:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdbljgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;		
		HdbljgService service = new HdbljgService();
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);		
		String path = "hdgl_hdbl_hdbljg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdbljgList");
	} 
	
	/**
	 * @description	： 增加
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午02:12:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		HdbljgService HdbljgService = new HdbljgService();
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String path = "hdgl_hdbljg.do?method=addHdbljg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		HdblsqshService hdblsqshService = new HdblsqshService();
		
		//获取当前学期
		request.setAttribute("currXq", hdblsqshService.getCurrXq());
		request.setAttribute("sqsj",GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		/*活动类型列表*/
		List<HashMap<String, String>> hdlxList = hdblsqshService.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);

		/*主讲人职称*/
		List<HashMap<String, String>> zjrzcList = hdblsqshService.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);
		
		/*能标签列表*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = HdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);
		
		/*课程类型列表*/
		List<HashMap<String,String>> jzlxList = HdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//自选课程列表
		List<HashMap<String,String>> zxckclxList = HdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*能力标签*/
		List<HashMap<String,String>> abilityLabelList = HdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);
		
		return mapping.findForward("addHdbljg");
	}
	
	/**
	 * @description	：  修改
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午03:43:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		HdbljgService service = new HdbljgService();
		HdbljgForm view_model = service.getModelForJg(model);
		//view_model = (HdbljgForm) StringUtils.formatBean(view_model);
		if(null != view_model){
			BeanUtils.copyProperties(model, StringUtils.formatData(view_model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		String path = "hdgl_hdbljg.do?method=updateHdbljg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		//活动类型列表
		HdblsqshService hdblsqshService = new HdblsqshService();
		List<HashMap<String, String>> hdlxList = hdblsqshService.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);
		
		/*活动标签列表*/
		//List<HashMap<String, String>> activityLabelList = hdblsqshService.getActivityLabelList();
		List<HashMap<String, String>> activityLabelList = service.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);
		
		/*讲座类型列表*/
		List<HashMap<String,String>> jzlxList = service.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//自选课程列表
		List<HashMap<String,String>> zxckclxList = service.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);
		
		/*能力标签*/
		List<HashMap<String,String>> abilityLabelList = service.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);

		/*主讲人职称*/
		List<HashMap<String, String>> zjrzcList = hdblsqshService.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);
		
		/*返回活动形式*/
		request.setAttribute("hdxs", model.getHdxs());
		
		return mapping.findForward("updateHdbljg");
	}
	
	/**
	 * @description	： 保存
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午03:07:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		HdbljgService hdbljgService = TransactionControlProxy.newProxy(new HdbljgService());
		boolean result = false;
	    //User user = getUser(request);
 		if(model.getType().equals("save")){
 			String jgid = UniqID.getInstance().getUniqIDHash();
 			model.setJgid(jgid);
			result = hdbljgService.runInsert(model);
		}else if(model.getType().equals("update")){
			result = hdbljgService.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 删除
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午05:23:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			HdbljgService service = TransactionControlProxy.newProxy(new HdbljgService());
			boolean result = service.runDeleteJg(ids);
			int num = ids.length;
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
	 * @description	： 查看结果
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午05:43:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewHdbljg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm)form;
		request.setAttribute("jbxxList", jbxxList);
		HdbljgService service = new HdbljgService();
		HdbljgForm view_model = service.getModelForJg(model);
		//view_model = (HdbljgForm) StringUtils.formatBean(view_model);
		if(null != view_model){
			BeanUtils.copyProperties(model, view_model);
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		return mapping.findForward("viewHdbljg");
	}
	
	/**
	 * @description	： 导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-24 上午10:30:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdbljgForm model = (HdbljgForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HdbljgService service = new HdbljgService();
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
