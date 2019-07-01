/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午04:52:48 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: TODO(操行评语管理) 
 * @作者： CMJ [工号：913]
 * @时间： 2013-7-24 下午04:52:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpyAction extends SuperAction {
	
	private static final String url = "xsxx_gygl_cxcxpy.do?method=cxCxpyList";
	
	@SystemAuth(url = url)
	public ActionForward cxCxpyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
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
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		if(!("13943".equalsIgnoreCase(Base.xxdm))) {
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		String path = "xsxx_gygl_cxcxpy.do?method=cxCxpyList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxCxpyList");
	}
	
	/**
	 * 
	 * @描述:(增加操行评语)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-9 上午10:46:24
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
	@SystemLog(description="访问学生信息-学生操行评定管理-操行评语录入-增加XHS:{xhs}")
	public ActionForward addCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		HashMap<String, String> map=new HashMap<String, String>();
		String currxn=Base.currXn;
		String currxq=Base.currXq;
		map.put("xn", currxn);
		map.put("xq", currxq);
		String xhs=request.getParameter("xhs");
		if(xhs!=null&&!"".equals(xhs)){
			map.put("xn", model.getXn());
			map.put("xq", model.getXq());
			map.put("cxpy", model.getCxpy());
			List<HashMap<String, String>> xsList=service.getXzxsList(xhs);
			request.setAttribute("xsList", xsList);
			request.setAttribute("xhs", xhs);
		}
		map.put("xhs", xhs);
		User user = getUser(request);
		String name=user.getRealName();
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		List<HashMap<String, String>> cxdjList=service.getCxdjList();
		request.setAttribute("map", map);
		request.setAttribute("name", name);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		request.setAttribute("cxdjList", cxdjList);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		request.setAttribute("nowTime", time);
		
		return mapping.findForward("addCxpy");
	}
	/**
	 * 
	 * @描述:TODO(获取学生信息)
	 * @作者：Cmj [工号：913]
	 * @日期：2013-7-25 下午03:45:55
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
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getXsPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String xhs=request.getParameter("xhs");
		String xn=request.getParameter("xn");
		String xq=request.getParameter("xq");
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		String path = "xsxx_gygl_cxcxpy.do?method=getStu";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStu");
	}
	/**
	 * 
	 * @描述:TODO(保存操行评语信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-7-25 下午06:42:50
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
	@SystemLog(description="访问学生信息-学生操行评定管理-操行评语录入-保存PK:{pk}")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		boolean result=service.save(model);
		String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		request.setAttribute("message", message);
		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生操行评定管理-操行评语录入-修改PK:{pk}")
	public ActionForward updateCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		CxpyForm myForm=new CxpyForm();
		myForm=service.getModel(model);
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.runUpdate(model);
			String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		List<HashMap<String, String>> cxdjList=service.getCxdjList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		request.setAttribute("cxdjList", cxdjList);
		BeanUtils.copyProperties(model,StringUtils.formatData(myForm ));
		return mapping.findForward("updateCxpy");
	}
	
	/**
	 * 删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学生操行评定管理-操行评语录入-删除VALUES:{values}")
	public ActionForward delCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
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
	 * 查看操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewCxpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		CxpyForm myForm=new CxpyForm();
		myForm=service.getModel(model);
		BeanUtils.copyProperties(model,StringUtils.formatData(myForm ));
		return mapping.findForward("viewCxpy");
	}
	
	/**
	 * 
	 * @描述:导出功能
	 * @作者：cmj
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录: 
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
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
		
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

	
	public ActionForward getExistsCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpyService service=new CxpyService();
		CxpyForm model=(CxpyForm) form;
		
		String count = service.getCount(model);
		response.getWriter().print(count);
		
		return null;
	}
}
