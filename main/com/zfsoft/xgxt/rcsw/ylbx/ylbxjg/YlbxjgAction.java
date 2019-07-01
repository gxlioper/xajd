/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午11:03:35 
 */  
package com.zfsoft.xgxt.rcsw.ylbx.ylbxjg;

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
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 医疗保险
 * @作者： cq [工号:785]
 * @时间： 2015-1-21 上午11:03:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlbxjgAction extends SuperAction {
	
	//定义日常事务中医疗保险常量可以从基本信息表中获取学生信息
	private static final String RCSWYLBX = "rcswylbx";
	private static List<HashMap<String, String>> jbxxList = null;
	
	YlbxjgService service = new YlbxjgService();
	
	private static final String url = "rcsw_ylbx_ylbxjg.do";

	/**
	 * 
	 * @描述:结果集查询
	 * @作者：cq [工号：785]
	 * @日期：2015-1-22 下午05:15:53
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
	public ActionForward ylbxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxjgForm model = (YlbxjgForm) form;
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办结果数据
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_ylbx_ylbxjg.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		
		SearchModel searchModel=new SearchModel();
		if("14073".equals(Base.xxdm)){
			searchModel.setSearch_tj_nd(new String[] {Base.currNd});
		}else{
			searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		}
		request.setAttribute("searchTj", searchModel);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxjgManage");
	}
	@SystemAuth(url = "rcsw_ylbx_ylbxmd.do")
	public ActionForward ylbxmdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxjgForm model = (YlbxjgForm) form;
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办结果数据
			List<HashMap<String, String>> resultList = service.getXjbxmdList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_ylbx_ylbxmd.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxmdManage");
	}

	/**
	 * 
	 * @描述:增加
	 * @作者：cq [工号：785]
	 * @日期：2015-1-26 上午11:54:06
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
	@SystemLog(description = "访问日常事务-医疗保险-医疗保险结果-增加XH:{xh},XN:{xn},ZD12:{zd12},ZD6:{zd6}")
	public ActionForward addYlbxsqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxjgForm model = (YlbxjgForm) form;
		User user = getUser(request);
		model.setCzyh(user.getUserName());
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

			// 唯一性判断(先按学号+学年
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				super.resetToken(request);
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				boolean result = service.saveSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_YLBX_YLBXJG_REPEAT));
				return null;
			}
		}

		String path = "rcsw_ylbx_ylbxjggl.do?method=addYlbxsqjg";
		request.setAttribute("path", path);
		
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		if("14073".equals(Base.xxdm)){
			request.setAttribute("dqnd", Base.currNd);
		}else{
			request.setAttribute("dqxn", Base.currXn);
		}
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addYlbxjg");
	}

	
	/**
	 * 
	 * @修改
	 * @作者：cq [工号：785]
	 * @日期：2015-1-26 上午11:56:14
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
	@SystemLog(description = "访问日常事务-医疗保险-医疗保险结果-修改JGID:{jgid},XH:{xh},XN:{xn},ZD12:{zd12},ZD6:{zd6}")
	public ActionForward updateYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学号）
			boolean isExist=service.isExistBysqjg(model);
			if (!isExist) {
				// 修改学生证补办结果
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				boolean result = service.updateSqjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_YLBX_YLBXJG_REPEAT));
				return null;
			}
		}

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		request.setAttribute("jbxxList", jbxxList);
		
		YlbxjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateYlbxjg");
	}


	
	/**
	 * 
	 * @描述:删除结果
	 * @作者：cq [工号：785]
	 * @日期：2015-1-26 上午11:58:41
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
	@SystemLog(description = "访问日常事务-医疗保险-医疗保险结果-删除VALUES:{values}")
	public ActionForward delYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteSqjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:单个查看
	 * @作者：cq [工号：785]
	 * @日期：2015-1-26 下午01:34:51
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
	public ActionForward viewOneYlbxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxjgForm model = (YlbxjgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		//查询查看结果
		request.setAttribute("rs", StringUtils.formatData(service.viewOneYlbxjgList(model.getJgid())));

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewYlbxjg");
	}
	
	/**
	 * 
	 * @描述:结果导出
	 * @作者：cq [工号：785]
	 * @日期：2015-1-26 下午01:38:42
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxjgForm model = (YlbxjgForm) form;

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getExportAllList(model, user);//查询出所有记录，不分页
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
	
	public ActionForward exportConfigXjbx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxjgForm model = (YlbxjgForm) form;

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXjbxmdList(model, user);//查询出所有记录，不分页
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
	 * 
	 * @描述:根据ID查询结果信息
	 * @作者：cq [工号：785]
	 * @日期：2015-1-27 上午09:37:33
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
	public ActionForward ylbxXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		
		Map<String, String> ylbxXxMap = service.viewOneYlbxjgList(id);
		ylbxXxMap = (Map<String, String>) StringUtils.formatData(ylbxXxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(ylbxXxMap));
		return null;
	}
	
	/**
	 * 
	 * @描述:照片导出
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-2-16 下午02:07:06
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
	public ActionForward zpdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxjgForm myForm = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		SearchModel searchModel=new SearchModel();
		User user = getUser(request);// 用户对象
		CommService comService = new CommService();
		searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		System.out.println("zpType:"+myForm.getZpType());
		System.out.println("photoNameType:"+myForm.getPhotoNameType());
		
		if (EXP.equals(myForm.getType())){
			//生成高级查询对象
			service.zpdc(myForm, response,user);
			return null;
		}
		return mapping.findForward("zpdc");
	}
	public ActionForward dszmdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxjgForm myForm = (YlbxjgForm) form;
		YlbxjgService service = new YlbxjgService();
		Map<String, String> bxMap = service.viewOneYlbxjgList(myForm.getJgid());
		request.setAttribute("bxMap", bxMap);
		return mapping.findForward("dszmdy");
	}
	
	

}
