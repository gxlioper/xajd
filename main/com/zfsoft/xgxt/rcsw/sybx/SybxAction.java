/**
 * @部门:学工产品事业部
 * @日期：2013-5-8 下午05:22:39 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

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
 * @模块名称: 日常事务管理模块
 * @类功能描述: TODO 商业保险管理
 * @作者： honglin 
 * @时间： 2013-5-8 下午05:22:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SybxAction  extends SuperAction{

	private static final String SYBX = "sybx";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_sybx_cx.do";
	
	/**
	 * 
	 * @描述:商业保险查询
	 * @作者：honglin
	 * @日期：2013-05-09 上午09:08:33
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
	public ActionForward getSybxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
		
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
		
		String path = "rcsw_sybx_cx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("sybxList");
	}
	
	/**
	 * 商业医疗保险维护 自定义导出
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
	public ActionForward syylbxwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
	 * @描述:增加商业保险信息
	 * @作者：honglin
	 * @日期：2013-05-09 上午09:34:06
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
	@SystemLog(description="访问日常事务-商业保险-商业保险维护-增加")
	public ActionForward addSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
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
	
        if (SAVE.equalsIgnoreCase(model.getType())){
        	//唯一性判断（学号，学年）
        	boolean isExist=service.isExistBySybz(model,SAVE);
        	if(!isExist){
	        	//添加商业保险信息
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_SYBX_RESULT_REPEAT));
				return null;
        	}
		}
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//默认将学年设置为系统当前学年
		if(StringUtil.isNull(model.getXn())){
        	model.setXn(Base.currXn);
        }
        
		String path = "rcsw_sybx.do?method=addSybx";
		request.setAttribute("path", path);
		request.setAttribute("mkxxForm", model);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(SYBX);
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String,String>> zjyyList = service.getAllZjyyList();
		request.setAttribute("zjyyList", zjyyList);
		List<HashMap<String,String>> cbrylbList = service.getAllCbrylbList();
		request.setAttribute("cbrylbList", cbrylbList);
		List<HashMap<String,String>> jfrylbList = service.getAllJfrylbList();
		request.setAttribute("jfrylbList", jfrylbList);
		
		return mapping.findForward("addSybx");
	}
	
	/**
	 * 
	 * @描述:修改商业保险信息
	 * @作者：honglin
	 * @日期：2013-05-09 上午09:34:06
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
	@SystemLog(description="访问日常事务-商业保险-商业保险维护-修改GUID:{guid}")
	public ActionForward updateSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
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
	
        if (UPDATE.equalsIgnoreCase(model.getType())){
        	//唯一性判断（学号，学年）
        	boolean isExist=service.isExistBySybz(model,UPDATE);
        	if(!isExist){
				//修改困难生认定信息
	        	boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(null));
				return null;
        		
        	}
		}
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		
		request.setAttribute("mkxxForm", model);
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(SYBX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		
		List<HashMap<String,String>> zjyyList = service.getAllZjyyList();
		request.setAttribute("zjyyList", zjyyList);
		List<HashMap<String,String>> cbrylbList = service.getAllCbrylbList();
		request.setAttribute("cbrylbList", cbrylbList);
		List<HashMap<String,String>> jfrylbList = service.getAllJfrylbList();
		request.setAttribute("jfrylbList", jfrylbList);
		
		SybxForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		return mapping.findForward("updateSybx");
	}
	
	/**
	 * 
	 * @描述:删除商业保险信息
	 * @作者：HongLin
	 * @日期：2013-05-09 上午09:34:06
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
	@SystemLog(description="访问日常事务-商业保险-商业保险维护-删除VALUES:{values}")
	public ActionForward deleteSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxService service = new SybxService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:查看商业保险信息
	 * @作者：honglin
	 * @日期：2013-05-09 上午09:34:06
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
	public ActionForward viewSybx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SybxForm model = (SybxForm) form;
		SybxService service = new SybxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//获得单个学生商业保险信息
			request.setAttribute("rs", StringUtils.formatData(service.getOneSybxList(model.getGuid())));

			//学生基本信息显示配置
			jbxxList = bdpzService.getJbxxpz(SYBX);
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewSybx");
		} else {
			return updateSybx(mapping, form, request, response);
		}
	}
	
}
