/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:35:31 
 */  
package com.zfsoft.xgxt.xpjpy.cpmd;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013版-参评学生名单
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:35:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpmdAction extends SuperAction {
	
	
	
	/**
	 * 
	 * @描述: 评奖名单查询
	 * @作者：cq [工号：785]
	 * @日期：2013-7-22 下午03:58:12
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
	
	public ActionForward viewCpxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();
		
		//判断当前评奖名单人员库是否为空，空：根据在校生初始化
		boolean sfcz = service.getSfcz();
		if(!sfcz){
			//评奖人员库执行初始化操作
			service.init();
		}
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
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
			request.setAttribute("userName",user.getUserName());
			
			return null;
		}
		
		CsszService csszservice = new CsszService();
		request.setAttribute("zqmc", csszservice.getModel().getZqmc());
		
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		String path = "pj_cpmd.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewCpxsList");
	}
	
	/**
	 * 
	 * @描述: 调整记录查询
	 * @作者：cq [工号：785]
	 * @日期：2013-7-23 上午10:44:11
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
	@SystemAuth(url = "pj_tzjl.do")
	public ActionForward viewTzjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();  

		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getTzjlList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszservice = new CsszService();
		request.setAttribute("zqmc", csszservice.getModel().getZqmc());
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		String path = "pj_tzjl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewTzjlList");
	}
	
	/**
	 * 
	 * @描述: 单个调整参评学生状态
	 * @作者：cq [工号：785]
	 * @日期：2013-7-24 上午10:34:21
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
	
	public ActionForward tzcpxszt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		CpmdModel model = (CpmdModel) form;
		User user = getUser(request);
		
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			request.setAttribute("yhInfo", "您没有访问该模块的权限！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (!StringUtil.isNull(model.getXh())){
			
			XsxxService xsxxService = new XsxxService();
			
			//判断学号是否存在
			boolean isHaveXx = xsxxService.getCheckStuExists(model.getXh()); 
			if(isHaveXx){
				//加载调整信息
				HashMap<String, String> tjxx = service.getTzxx(model.getXh());
				request.setAttribute("tjxx", StringUtils.formatData(tjxx));
				
				//加载学生基本信息
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
				
			}else{
				request.setAttribute("xhInfo", "学号不存在，请重新录入");
			}
			
		}		
		
		request.setAttribute("ids", request.getParameter("ids"));

		FormModleCommon.setAllNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		return mapping.findForward("tzcpxszt");
	}
	
	/**
	 * 
	 * @描述: 将参评人员从一个班级调整到另一个班级
	 * @作者：cq [工号：785]
	 * @日期：2013-7-25 下午07:06:39
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
	
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-增加-保存学生TZHBJDM：{tzhbjdm}")
	public ActionForward updateCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		String tzbjdm = request.getParameter("tzqbjdm");
		
		User user = getUser(request);
		
		 if (!StringUtil.isNull(tzbjdm)){
			 
			//班级调整			 	
			String tzhbjdm = request.getParameter("tzhbjdm");
			String xh = request.getParameter("xh");
			 
			boolean result = service.bjtz(tzhbjdm,user,xh);
			String messageKey = result ? MessageKey.SYS_TZ_SUCCESS
					: MessageKey.SYS_TZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
						
		}
		
		return null;

	}
	
	
	/**
	 * 
	 * @描述: 取消参评人员状态
	 * @作者：cq [工号：785]
	 * @日期：2013-7-24 上午10:34:21
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
	
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-删除学生VALUES：{values}")
	public ActionForward delCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		
		String values = request.getParameter("values");
		User user = getUser(request);
		

		boolean result = service.qxcp(values,user);
		String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
				: MessageKey.SYS_QXCP_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @描述:参评人员调整记录导出
	 * @作者：cq [工号：785]
	 * @日期：2013-8-14 下午03:43:11
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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		CpmdModel model = (CpmdModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 查询
		List<HashMap<String,String>> resultList = service.getTzjlList(model,user);//查询出所有记录，不分页

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
	 * @描述: 选择增加参评学生页面
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-14 上午11:34:12
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
	public ActionForward viewAddCpxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();
		
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getAddCpxsList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//年级、学院、专业、班级
		FormModleCommon.setAllNjXyZyBjList(request);
		request.setAttribute("path", "pj_cpmd_zjcpxs.do");
		return mapping.findForward("viewAddCpxsList");
	}
	
	
	
	/**
	 * 
	 * @描述: 参评学生选择
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-17 下午02:03:06
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
	 
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdModel model = (CpmdModel) form;
		CpmdService service = new CpmdService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		String path = "pj_cpmd_showStudents.do";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("showStudents");
	}
	
	
	/**
	 * 
	 * @描述: 将参评人员从一个班级调整到另一个班级s
	 * @作者：cq [工号：785]
	 * @日期：2013-7-25 下午07:06:39
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
	 
	public ActionForward updateCpbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpmdService service = new CpmdService();
		String bjdm = request.getParameter("bjdm");
		String ids = request.getParameter("ids");
		
		User user = getUser(request);
		
		 if (!StringUtil.isNull(bjdm)){
			 
			boolean result = service.bjtzs(bjdm,user,ids);
			String messageKey = result ? MessageKey.SYS_TZ_SUCCESS
					: MessageKey.SYS_TZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
						
		}
		
		return null;

	}
	
	
	
}
