/**
 * @部门:学工产品事业部
 * @日期：2016-11-15 下午03:08:15 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.hsdxd;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-15 下午03:08:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HsdxdAction extends SuperAction<HsdxdForm, HsdxdService> {
	HsdxdService service = new HsdxdService();
	private static final String FDWW = "fdwh";
	public ActionForward xdCsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
        User user = getUser(request);
        /**
         * 如果不是学生用户,直接转到错误页面
         */
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "该页面只能学生用户访问。");
			return mapping.findForward("error");
		}
		/**
		 * 验证续贷功能是否开放
		 */
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		if (Constants.CLOSE.equals(csszModel.getXdKg())){
			request.setAttribute("message", "续贷功能未开放。");
			return mapping.findForward("error");
		}
		String num = service.getDkjgNum(user.getUserName());
		if (("0").equals(num)){
			request.setAttribute("message", "您未申请过国家助学贷款。");
			return mapping.findForward("error");
		}
		List<HashMap<String, String>> xdxxList = service.getXdxxMx(user.getUserName());
		HashMap<String, String> dkxMap = service.getDkxxMap(user.getUserName());
		request.setAttribute("xdxxList", xdxxList);
		request.setAttribute("dkxx", dkxMap);
		
		/**
		 * 续贷申请记录
		 */
		request.setAttribute("xdsqList",service.getXdsqList(user.getUserName()));
		request.setAttribute("fkzh", service.getFdjeZh(user.getUserName()));
		String path = "zxdk_gjdk_xdsqnew.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xdcsh");
	}
	
	/**
	 * 
	 * @描述: 放弃申请
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-18 上午10:02:13
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
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String jgid = request.getParameter("jgid");
		boolean result = service.qxsq(jgid);
		String message = "";
		if(result){
			message = "取消成功！";
		}else{
			message = "取消失败！";
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述: 放贷查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-21 上午11:06:42
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
	public ActionForward fdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		    HsdxdForm model = (HsdxdForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				// 查询
				HsdxdDao dao = new HsdxdDao();
				List<HashMap<String, String>> resultList = dao.getFdcxList(user, model);
				
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			// 默认高级查询条件
			SearchModel searchModel = new SearchModel();
			searchModel.setSearch_tj_xn(new String[] { Base.currXn });
			searchModel.setSearch_tj_xq(new String[] { Base.currXq });
			request.setAttribute("searchTj", searchModel);
			String path = "zxdk_gjdk_hsdfd.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("hsdfd");
	}
	
	/**
	 * 
	 * @描述: 放贷维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-21 下午01:53:07
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
	public ActionForward fdWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		HsdxdForm myForm = (HsdxdForm) form;
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HsdxdForm model = service.getFdbModel(myForm.getId());
		BeanUtils.copyProperties(myForm, model);
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(FDWW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(myForm.getXq()));
		request.setAttribute("dkzhs", service.getDkzh(model.getHtbh()));
		request.setAttribute("xdjes", service.getFdjes(model.getId()));
		request.setAttribute("fkjes", model.getFkje());
		request.setAttribute("rs", model);
		return mapping.findForward("fdwh");
	}
	
	/**
	 * 
	 * @描述: 放贷维护保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-21 下午01:57:49
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
	public ActionForward saveFdwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		HsdxdForm myForm = (HsdxdForm) form;
		boolean rs = service.updateFdb(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 放贷维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-21 下午01:53:07
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
	public ActionForward fdCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		HsdxdForm myForm = (HsdxdForm) form;
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HsdxdForm model = service.getFdbModel(myForm.getId());
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(FDWW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
		request.setAttribute("rs", model);
		return mapping.findForward("fdck");
	}
	
	/**
	 * 
	 * @描述:导出放贷记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-24 下午03:30:15
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HsdxdForm myForm = (HsdxdForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		myForm.getPages().setMaxRecord(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = new HsdxdDao().getFdcxList(user,myForm);
		

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
