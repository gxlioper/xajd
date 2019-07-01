/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 下午03:45:11 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-24 下午03:45:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyyjxStuAction extends SuperAction {

	private static BdpzService bdpzService = new BdpzService();
	
	private static List<HashMap<String,String>> jbxxList = null;
	
	static{
		jbxxList = bdpzService.getJbxxpz("gyglyjx");
	}
	
	
	
	
	public ActionForward listStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		
		/*if(!"stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块只允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}*/
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_yjxstugl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listStu");
	}
	
	
	public ActionForward listGl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_jxjggl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listGl");
	}
	
	/**
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
	
	public ActionForward actionNavi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		String type = model.getType();
		String mappingUri = "gyyjxStuAddUpdate";
		request.setAttribute("actionType", type);
		
		if(StringUtils.equals("update", type)){
			GyyjxForm dataModel = service.getModel(model.getGyyjid());
			xgxt.utils.String.StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
		}else if(StringUtils.equals("add", type)){
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
		}else if(StringUtils.equals("yjfk", type)){
			mappingUri = "gyyjfk";
			GyyjxForm dataModel = service.getModel(model.getGyyjid());
			xgxt.utils.String.StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
			request.setAttribute("gyyjxx", service.getModelMap(model.getGyyjid())); //查询;
		}
		
		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
			CwglService cwglservice = new CwglService();
			HashMap<String , String> cwxx = cwglservice.getCwForXh(model.getXh());
			request.setAttribute("cwxx", cwxx); //查询学生床位信息
		}
		request.setAttribute("yjflList",new GyyjxdmwhService().getAllList(model));
		String path = "gygl_gyyjxstu.do?method=actionNavi&type=" + type;
		request.setAttribute("path", URLEncoder.encode(path, "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward(mappingUri);
	}
	
	
	@SystemLog(description="访问公寓管理-公寓意见箱-公寓意见箱-增加XH:{xh},YJFLDM:{yjfldm},YJMS:{yjms}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();

		if(StringUtils.isNotBlank(model.getYjfldm()) && StringUtils.isNotBlank(model.getXh()) && StringUtils.isNotBlank(model.getYjms())){
			model.setYjsj(DateUtils.getCurrTime());
			//xgxt.utils.String.StringUtils.formatData(model);
			model.setFknr(HtmlUtil.xmlZy(model.getFknr()));
			boolean isSuccess = service.runInsert(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		return null;
	}
	
	
	@SystemLog(description="访问公寓管理-公寓意见箱-公寓意见箱-修改QYYJID:{gyyjid},YJFLDM:{yjfldm},YJMS:{yjms}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();

		if(StringUtils.isNotBlank(model.getYjfldm()) && StringUtils.isNotBlank(model.getYjms())){
			//xgxt.utils.String.StringUtils.formatData(model);
			model.setFknr(HtmlUtil.xmlZy(model.getFknr()));
			boolean isSuccess = service.runUpdate(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		
		return null;
	}
	
	
	@SystemLog(description="访问公寓管理-公寓意见箱-删除PK:{pks}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxStuService service  = new GyyjxStuService();
		String pks = request.getParameter("pks");
		
		if(StringUtils.isNotBlank(pks)){
			int isSuccess = service.runDelete(pks.split(","));
			String messageKey = (isSuccess>0) ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		
		return null;
	}
	
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		if(StringUtils.isNotBlank(model.getGyyjid())){
			//BeanUtils.copyProperties(model, service.getModel(model.getYjfldm()));
			request.setAttribute("gyyjxx", service.getModelMap(model.getGyyjid())); //查询;
		}
		if(StringUtils.isNotBlank(model.getGyyjid())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
			CwglService cwglservice = new CwglService();
			HashMap<String , String> cwxx = cwglservice.getCwForXh(model.getXh());
			request.setAttribute("cwxx", cwxx); //查询学生床位信息
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("queryGyyjxx");
	}
	
	
	@SystemLog(description="访问公寓管理-公寓意见箱-反馈PK:{gyyjid}")
	public ActionForward gyyjfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();
		User user = getUser(request);
		if(StringUtils.isNotBlank(model.getGyyjid()) && StringUtils.isNotBlank(model.getFknr())){
			model.setFkqk("1");
			model.setFkr(user.getUserName());
			model.setFksj(DateUtils.getCurrTime());
			boolean isSuccess = service.runUpdate(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:导出
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
		GyyjxForm model = (GyyjxForm) form;
		GyyjxStuService service  = new GyyjxStuService();

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
}
