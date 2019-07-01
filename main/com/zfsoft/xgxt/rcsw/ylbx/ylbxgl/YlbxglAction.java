package com.zfsoft.xgxt.rcsw.ylbx.ylbxgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 医疗保险管理
 */
public class YlbxglAction extends SuperAction {
	
	//定义日常事务中医疗保险常量可以从基本信息表中获取学生信息
	private static final String RCSWYLBX = "rcswylbx";
	private static List<HashMap<String, String>> jbxxList = null;
	
	YlbxglService service = new YlbxglService();
	
	private static final String url = "rcsw_ylbx_ylbxgl.do";

	/**
	 * 查询
	 */
	@SystemAuth(url = url)
	public ActionForward ylbxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxglForm model = (YlbxglForm) form;
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办结果数据
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_ylbx_ylbxgl.do";
		request.setAttribute("path", path);
		request.setAttribute("userType", getUser(request).getUserType());
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxglManage");
	}

	/**
	 * 增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		model.setCzyh(user.getUserName());
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			
			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.insertYlbxgl(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}

		String path = "rcsw_ylbx_ylbxglgl.do?method=addYlbxgl";
		request.setAttribute("path", path);
		
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		//下一个学年
		request.setAttribute("dqxn", Base.afterXn);
		return mapping.findForward("addYlbxgl");
	}
	
	/**
	 * 批量增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addYlbxglPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		model.setCzyh(user.getUserName());
		
		if (SAVE.equalsIgnoreCase(model.getType())) {
			if(StringUtil.isNull(model.getXh())){
				// 根据查询结果进行批量增加
				//生成高级查询对象		
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath("rcsw_ylbx_ylbxgl.do");
				model.setSearchModel(searchModel);
				List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
				
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				boolean result = true;
				for (int i = 0; i < resultList.size(); i++) {
					model.setXh(resultList.get(i).get("xh"));
					result = service.insertYlbxgl(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}else{
				// 根据勾选记录进行批量增加
				model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				String[] arr = model.getXh().split(",");
				boolean result = true;
				for (int i = 0; i < arr.length; i++) {
					model.setXh(arr[i]);
					result = service.insertYlbxgl(model);
				}
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}
		}
		
		String path = "rcsw_ylbx_ylbxglgl.do?method=addYlbxglPl";
		request.setAttribute("path", path);
		//下一个学年
		request.setAttribute("dqxn", Base.afterXn);
		return mapping.findForward("addYlbxglPl");
	}
	
	/**
	 * 修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.updateYlbxgl(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		// 修改时，排除当前记录，查询应交年数
		model.setYjnum(service.viewYjnum(model.getXh(), model.getJgid()));
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("updateYlbxgl");
	}

	/**
	 * 删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 查看需交
	 */
	@SystemAuth(url = url)
	public ActionForward viewOneYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		
		//查询历史保险
		List<HashMap<String, String>> lsList = service.viewLsList(model.getXh());
		request.setAttribute("lsList", lsList);

		return mapping.findForward("viewYlbxgl");
		
	}
	/**
	 * 查看已交保险
	 */
	@SystemAuth(url = url)
	public ActionForward viewYlbxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		YlbxglForm model = (YlbxglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//学生基本信息
		request.setAttribute("jbxxList", jbxxList);
		
		//查询历史保险
		List<HashMap<String, String>> lsList = service.viewOneList(model.getXh(),model.getJgid());
		request.setAttribute("lsList", lsList);
		
		return mapping.findForward("viewlsYlbxgl");
		
	}
	
	/**
	 * 导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxglForm model = (YlbxglForm) form;
		String ylbxzt = request.getParameter("ylbxzt");
		model.setYlbxzt(ylbxzt);
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
	 * 查询
	 */
	@SystemAuth(url = url)
	public ActionForward ylbxXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		String jgid = request.getParameter("jgid");
		Map<String, String> ylbxXxMap = service.viewOneYlbxglList(xh, jgid);//
		ylbxXxMap = (Map<String, String>) StringUtils.formatData(ylbxXxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(ylbxXxMap));
		return null;
	}

}
