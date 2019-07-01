/**
 * @部门:学工产品事业部
 * @日期：2013-5-13 上午08:55:52 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

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
 * @模块名称: 人武管理模块
 * @类功能描述: TODO入伍学费补偿管理
 * @作者：HongLin 
 * @时间： 2013-5-13 上午08:55:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwxfbcglAction extends SuperAction {

	private static final String RWXFBC = "rwxfbc";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rwgl_rwtwgl_rwxfbcgl.do";
	
	/**
	 * 
	 * @描述: 入伍学费补偿查询
	 * @作者：HongLin
	 * @日期：2013-5-14 上午09:12:05
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
	public ActionForward getRwxfbcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		
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
		
		String path = "rwgl_rwtwgl_rwxfbcgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("rwxfbcList");
	}
	
	/**
	 * 入伍学费补偿管理自定义导出
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
	public ActionForward rwxfbcExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
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
	 * @描述:TODO 增加单个学生入伍学费补偿
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:04:24
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
	public ActionForward addRwxfDgbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//加载学生入伍信息
			HashMap<String,String> rwxx = service.getOneRwxfbcList(model.getXh());
			request.setAttribute("rwxx", StringUtils.formatData(rwxx));
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
        	//唯一性判断（学号，学年）
			model.setGuid(null);
        	boolean isExist=service.isExistByRwxfbc(model,SAVE);
        	if(!isExist){
	        	//添加商业保险信息
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(MessageKey.RCSW_SYBX_RESULT_REPEAT));
				return null;
        	}
		}
		//学年list
		request.setAttribute("xnList", Base.getXnndList());
		//默认将学年设置为系统当前学年
		if(StringUtil.isNull(model.getXn())){
        	model.setXn(Base.currXn);
        }
		
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RWXFBC);
		request.setAttribute("jbxxList", jbxxList);
		String path = "rwgl_rwxfbcgl.do?method=rwxfDgbc";
		request.setAttribute("path", path);
		
		return mapping.findForward("rwxfDgbc");
	}
	
	/** 
	 * @描述:TODO 修改单个学生入伍学费补偿
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:04:24
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
	public ActionForward updateRwxfDgbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//加载学生入伍信息
			HashMap<String,String> rwxx = service.getOneRwxfbcList(model.getXh());
			request.setAttribute("rwxx", StringUtils.formatData(rwxx));
		}
		if (UPDATE.equalsIgnoreCase(model.getType())){
        	//唯一性判断（学号，学年）
        	boolean isExist=service.isExistByRwxfbc(model,UPDATE);
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
		
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RWXFBC);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		
		RwxfbcglForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		return mapping.findForward("rwxfDgbc");
	}
	
	/** 
	 * @描述:TODO 获得单个学生入伍学费补偿信息
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:08:14
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
	public ActionForward viewRwxfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//查询单个学生入伍学费补偿信息结果
			request.setAttribute("rs", StringUtils.formatData(service.getOneRwxfbcList(model.getXh())));

			//学生基本信息显示配置
			jbxxList = bdpzService.getJbxxpz(RWXFBC);
			request.setAttribute("jbxxList", jbxxList);
			return mapping.findForward("viewRwxfbc");
		} else {
			if(null!=model && null!=model.getGuid() && !"null".equalsIgnoreCase(model.getGuid()) && !"".equalsIgnoreCase(model.getGuid())){
				return updateRwxfDgbc(mapping, form, request, response);
			}else{
				return addRwxfDgbc(mapping, form, request, response);
			}
		}
	}
	
	/**
	 * 
	 * @描述: 撤销学生学费补偿
	 * @作者：HongLin
	 * @日期：2013-5-14 下午03:41:14
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
	public ActionForward cancelRwxfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglService service = new RwxfbcglService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			
			
			String message = result ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS,num) 
									: MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_CANCEL_NULL);
		}
		
		return null;
	}
	
	/** 
	 * @描述: 批量学生学费补偿
	 * @作者：HongLin
	 * @日期：2013-5-14 下午06:46:56
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
	public ActionForward rwxfPlbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwxfbcglForm model = (RwxfbcglForm) form;
		//学年list
		String[] ids = model.getId();
		String[] xhs = model.getXhs();
		String guid="";
		String xh="";
		if(null!=ids && ids.length>0){
			for (int i=0;i<ids.length;i++){
				if(i==(ids.length-1)){
					guid+=ids[i];
				}else{
					guid+=ids[i]+",";
				}
			}
		}
		if(null!=xhs && xhs.length>0){
			for (int i=0;i<xhs.length;i++){
				if(i==(xhs.length-1)){
					xh+=xhs[i];
				}else{
					xh+=xhs[i]+",";
				}
			}
		}
		model.setGuid(guid);
		model.setXh(xh);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("mkxxForm", StringUtils.formatData(model));
		return mapping.findForward("rwxfPlbc");
	}
	
	/** 
	 * @描述: 保存批量学生学费补偿
	 * @作者：HongLin
	 * @日期：2013-5-14 下午06:46:56
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
	public ActionForward savePlbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RwxfbcglForm model = (RwxfbcglForm) form;
		RwxfbcglService service = new RwxfbcglService();
		
		User user = getUser(request);
		
		boolean result = service.savePlbc(model, user);
		String message = result ? MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS) 
				: MessageUtil.getText(MessageKey.SYS_PLBC_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
}
