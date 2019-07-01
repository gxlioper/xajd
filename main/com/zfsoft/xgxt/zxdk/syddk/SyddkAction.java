/**
 * @部门:学工产品事业部
 * @日期：2014-9-29 上午09:34:58 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:助学贷款-生源地贷款
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-29 上午09:34:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SyddkAction extends SuperAction<SyddkModel, SyddkService> {

	private static final String ZXDK = "gjzxdk";
	
	private static final String url = "zxdk_syddm_dkwh.do";
	
	/**
	 * 
	 * @描述: 贷款信息列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-29 上午09:38:47
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
	public ActionForward dkxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_syddm_dkwh.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("dkxxList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载贷款申请列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:38:22
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
	public ActionForward getDkxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkService service = getService();
		SyddkModel model = (SyddkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 增加贷款信息
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-29 上午10:27:48
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
	public ActionForward addDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkModel model = (SyddkModel) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(ZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		
		// 银行列表
		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("yhList", xsxxglService.getYhList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		String path = "zxdkSyddk.do?method=addDkxx";
		request.setAttribute("path", path);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("addDkxx_zjdx");
		}
		this.saveToken(request);
		return mapping.findForward("addDkxx");
	}
	
	
	/**
	 * 
	 * @描述: 生源地贷款
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-29 上午10:27:28
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
	public ActionForward editDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkService service = getService();
		SyddkModel myForm = (SyddkModel) form;
		
		SyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(ZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		
		// 银行列表
		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("yhList", xsxxglService.getYhList());
		ZxdkCsszService csszService = new ZxdkCsszService();
		request.setAttribute("cssz", csszService.getModel());
		List<HashMap<String,String>> dkxxList = service.getDkxxList(myForm.getId());
		request.setAttribute("dkxxList", dkxxList);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("editDkxx_zjdx");
		}
		
		return mapping.findForward("editDkxx");
	}
	
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-29 下午04:04:41
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
	public ActionForward ckDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SyddkService service = getService();
		SyddkModel myForm = (SyddkModel) form;
		
		SyddkModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(ZXDK);
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String,String>> dkxxList = service.getDkxxList(myForm.getId());
		request.setAttribute("dkxxList", dkxxList);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("ckDkxx_zjdx");
		}
		
		return mapping.findForward("ckDkxx");
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.action.SuperAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-生源地贷款-贷款结果-删除values:{ids}")
	public ActionForward delDkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyddkService service = getService();
		String ids = request.getParameter("ids");
		
		boolean result = service.runDelete(ids.split(",")) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	@SystemAuth(url = url)
	public ActionForward dcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyddkService service = getService();
		SyddkModel model = (SyddkModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

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
	 * @描述:save方法重写
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-4-13 上午09:04:11
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
	public ActionForward savePd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SyddkModel model = (SyddkModel) form;
		SyddkService service = new SyddkService();
		boolean isExist = service.getExists(model.getXh(), model.getXn());
		if(isExist) {
			response.getWriter().print(
					getJsonResult(MessageKey.ZXDK_SYDDK, false));		
		}else {		
			boolean isSuccess = service.runInsert(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		return null;
	}
}
