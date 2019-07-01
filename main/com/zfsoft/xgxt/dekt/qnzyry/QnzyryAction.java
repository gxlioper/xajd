/**
 * @部门:学工产品事业部
 * @日期：2017-7-19 上午09:08:02 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dekt.qnzyhd.QnzyhdService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 青年志愿人员action(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-7-19 上午09:08:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyryAction extends SuperAction<QnzyryForm, QnzyryService>{
	public static final String BMWSH = "0";//报名未审核
	public static final String BMSHTG = "1";//报名审核通过
	public static final String BMSHWTG = "2";//报名审核未通过
	public static final String GSWSH = "0";//工时未审核
	public static final String GSSHTG = "1";//工时审核通过
	public static final String GSSHWTG = "2";//工时审核退回 
	public static final String YTJ = "1";//提交工时状态
	public static final String DEKTQNZYHD = "dektqnzyhd";
	private QnzyryService service = new QnzyryService();
	private static List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "dekt_qnzyhd_hdlb.do";
	
	private static final String YfbUrl = "dekt_qnzyhd_yfbhd.do";
	
	private static final String ycjUrl = "dekt_qnzyhd_ycjhd.do";
	
	private static final String gsshUrl = "dekt_qnzyhd_gssh.do";
	
	private static final String gsjgUrl = "dekt_qnzyhd_gsjg.do";
	
	
	
	/** 
	 * @描述:保存记录(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-19 上午09:18:14
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
	
	public ActionForward saveJl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm)form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		model.setBmzt(BMWSH);
		model.setGsshzt(GSWSH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sj = sdf.format(Calendar.getInstance().getTime());
		model.setBmsj(sj);
		if(service.isExist(model)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.JHZY_DEKT_YBM));
			return null;
		}else{			
			boolean result = service.runInsert(model);
			String message = result?MessageKey.JHZY_DEKT_BM_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
	}
	
	/** 
	 * @描述:人员管理页面(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-21 上午10:14:48
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
	public ActionForward rygl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm)form;
		QnzyhdService hdService = new QnzyhdService();
		HashMap<String,String> map = hdService.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", map);
//		List<HashMap<String,String>> ryList = service.getXmryList(model);
//		request.setAttribute("ryList",ryList);
		return mapping.findForward("rygl");		
	}
	
	/** 
	 * @描述:搜索人员(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-21 下午02:11:57
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
	public ActionForward searchRy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm)form;
		List<HashMap<String,String>> ryList = service.getXmryList(model);
		JSONArray dataList = JSONArray.fromObject(ryList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @描述:保存报名批量审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-24 上午09:12:27
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
	public ActionForward bcBmPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm)form;
		String messageKey = service.plshBmzt(model);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:提交工时(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-25 下午03:54:49
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
	public ActionForward tjgs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		model.setSftj(YTJ);
		model.setGsshzt(GSWSH);
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:撤销申报记录(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-25 下午04:12:57
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
	public ActionForward qxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		boolean result = service.runDelete(new String[]{model.getId()})>0;
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:服务工时审核列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-25 下午05:41:08
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
	@SystemAuth(url = gsshUrl)
	public ActionForward gsshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
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
		String path = "dekt_qnzyhd_gssh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gsshList");
	}
	
	/** 
	 * @描述:单个审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 上午09:34:35
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

	public ActionForward dgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		HashMap<String, String> data = service.getZyhdbmxx(model.getId());
		if (!StringUtil.isNull(data.get("xh"))) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(data.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(DEKTQNZYHD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", data);
		return mapping.findForward("dgsh");
	}
	
	/** 
	 * @描述:保存单个审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 下午02:13:17
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
	
	public ActionForward BcDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		if(model.getGsshzt().equalsIgnoreCase(GSSHWTG)){
			model.setSftj("");
		}
		boolean result = service.runUpdate(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:批量审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 下午03:31:22
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
	
	public ActionForward plsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		if(SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.plsh(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("plsh");
	}
	
	/** 
	 * @描述:撤销审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 下午04:47:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm) form;
		model.setGsshzt(GSWSH);
		model.setSftj(YTJ);
		boolean result = service.plcx(model.getIds());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;		
	}
	
	/** 
	 * @描述:保存工时(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-26 下午05:41:56
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
	
	public ActionForward saveGs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm) form;
		if(StringUtils.isNull(model.getGs())){
			model.setGs("");
		}
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;		
	}
	
	/** 
	 * @描述:工时结果列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-27 上午09:14:11
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
	@SystemAuth(url = gsjgUrl)
	public ActionForward gsjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);			
			// 查询
			List<HashMap<String, String>> resultList = service.getJgPageList(model, user);			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);		
		String path = "dekt_qnzyhd_gsjg.do";
		request.setAttribute("path", path);
		//学生用户登录查询获得的总学分
		if(user.getUserStatus().equalsIgnoreCase("stu")){			
			String xh = user.getUserName();
			String fs = service.countXf(xh);
			request.setAttribute("xf", fs);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gsjgList");		
	}
	
	/** 
	 * @描述:工时审核结果导出(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-27 下午01:58:46
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
	@SystemAuth(url = gsjgUrl)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
	 * @描述:查看志愿活动(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-27 下午02:32:25
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
	public ActionForward viewZyhd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(DEKTQNZYHD);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> data = service.getZyhdbmxx(model.getId());
		request.setAttribute("data", data);
		if (!StringUtil.isNull(data.get("xh"))) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(data.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		return mapping.findForward("viewZyhd");
	}
	
	/**
	 * @description	： 评分
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-15 下午03:53:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		QnzyryService service = new QnzyryService();
		QnzyryForm copyModel = service.getModel(model);
		if(null != copyModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(copyModel));
		}
		return mapping.findForward("pfzhhd");
	}
	
	/**
	 * @description	： 评分保存
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-15 下午05:25:42
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		boolean result = service.runUpdateForPf(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
}
