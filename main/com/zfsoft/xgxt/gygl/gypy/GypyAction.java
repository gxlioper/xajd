package com.zfsoft.xgxt.gygl.gypy;

/**
 * @部门:学工产品事业部
 * @日期：2013-8-20 下午04:49:21 
 */

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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.qsgl.QsglService;

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
import com.zfsoft.xgxt.szdw.jtff.FdyjtffService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓评优
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路 [工号：982]
 * @时间： 2013-8-20 下午04:49:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GypyAction extends SuperAction {
	
	
	
	/**
	 * 
	 * @描述:公寓评优列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		CommService cs = new CommService();
		GypyForm myForm = (GypyForm) form;
		BeanUtils.copyProperties(myForm, request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(service.getGjcxPath(myForm));
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path",service.getGjcxPath(myForm));//对应高级查询对应path
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gypylb");
	}
	
	
	public ActionForward yxfdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		CommService cs = new CommService();
		GypyForm myForm = (GypyForm) form;
		myForm.setPylx(GypyService._YXFDY);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(service.getGjcxPath(myForm));
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path",service.getGjcxPath(myForm));//对应高级查询对应path
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yxfdylb");
	}
	
	
	public ActionForward yxxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		CommService cs = new CommService();
		GypyForm myForm = (GypyForm) form;
		myForm.setPylx(GypyService._YXXS);
		request.setAttribute("path",service.getGjcxPath(myForm));//对应高级查询对应path
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(service.getGjcxPath(myForm));
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			GyglNewService gyglNewService = new GyglNewService();
			String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员数据范围过滤条件
			myForm.setOtherFilter(searchTjByGyfdy);
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yxxslb");
	}
	/**
	 * 
	 * @描述:批量删除公寓评优
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	@SystemLog(description="访问公寓管理-公寓评优-文明宿舍-删除VALUES:{values};" +
			"访问公寓管理-公寓评优-优秀辅导员-删除VALUES:{values};" +
			"访问公寓管理-公寓评优-优秀学生-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.delete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述:修改素质评价
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	@SystemLog(description="访问公寓管理-公寓评优-文明宿舍-修改PK:{gypyid},PYLY:{pyly};" +
			"访问公寓管理-公寓评优-优秀辅导员-修改{gypyid},PYLY:{pyly};" +
			"访问公寓管理-公寓评优-优秀学生-修改{gypyid},PYLY:{pyly}")		
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm myForm = (GypyForm) form;
		String gypyid=request.getParameter("gypyid");
		myForm.setGypyid(gypyid);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("data", service.query(myForm));
		String gotopath=service.getForwordPath(myForm,3);
		//寝室信息
		//QsglService lds = new QsglService();
		//request.setAttribute("ldList", lds.getLdList());
		//学院 学期
		//FormModleCommon.setNdXnXqList(request);
		return mapping.findForward(gotopath);
	}
	
	
	@SystemLog(description="访问公寓管理-公寓评优-文明宿舍-增加LDDM:{lddm},QSH:{qsh},XN:{xn},XQDM:{xqdm},PYLY:{pyly};" +
			"访问公寓管理-公寓评优-优秀辅导员-增加GLDM:{gldm},XN:{xn},XQDM:{xqdm},PYLY:{pyly};" +
			"访问公寓管理-公寓评优-优秀学生-增加XH:{xh},XN:{xn},XQDM:{xqdm},PYLY:{pyly}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm myForm = (GypyForm) form;
		if(StringUtils.isNull(myForm.getXn())&&StringUtils.isNull(myForm.getXqdm())){
			myForm.setXn(Base.currXn);
			myForm.setXqdm(Base.currXq);
		}
		myForm.setPylx(request.getParameter("pylx"));
		//获取是否选择了老师
		String zgh=request.getParameter("zgh");
		if (!StringUtil.isNull(zgh)){
			myForm.setGldm(zgh);
			//加载f辅导员基本信息
			FdyjtffService fdyservice = new FdyjtffService();
			HashMap<String,String> jbxx = fdyservice.getFdyjbxx(zgh);
			request.setAttribute("jbxx", jbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//选择辅导员 跳转地址
		String path = "gypy.do?method=add";
		request.setAttribute("path", path);
		//寝室信息
		QsglService lds = new QsglService();
		request.setAttribute("ldList", lds.getLdList());
		//学院 学期
		FormModleCommon.setNdXnXqList(request);
		String gotopath=service.getForwordPath(myForm, 2);
		return mapping.findForward(gotopath);
	}
	/**
	 * 
	 * @描述:查看详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-21 下午05:44:54
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
	
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		String gypyid=request.getParameter("gypyid");
		GypyForm myForm = (GypyForm) form;
		myForm.setGypyid(gypyid);	
		request.setAttribute("data", service.query(myForm));
		String gotopath=service.getForwordPath(myForm,5);

		return mapping.findForward(gotopath);
	}
	
	
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm model = (GypyForm) form;
		model.setPylx(request.getParameter("pylx"));
		
		//查询配置
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//获取导出数据
		List<HashMap<String,String>> resultList=service.exportData(model);
		
		//导出配置
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
	 * ajax加载寝室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadQsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		
		GypyService service = new GypyService();
		Map<String, String> map = service.getMaxQsh(lddm, ch);
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		
		response.getWriter().write(json);
		return null;
	}
	/**
	 * 
	 * @描述:加载寝室电话
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-23 上午08:53:11
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
	public ActionForward loadQsxxdh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		String qsh = request.getParameter("qsh");

		GypyService service = new GypyService();
		Map<String, String> map = service.getQsxx(lddm, ch, qsh);
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	/**
	 * 
	 * @描述:加载学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-23 上午08:52:56
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
	public ActionForward loadXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		GypyService service = new GypyService();
		Map<String, String> map = service.getXsxx(xh);
		
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	/**
	 * 
	 * @描述:检查数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-23 上午08:52:28
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
	public ActionForward checkData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyService service = new GypyService();
		GypyForm model = (GypyForm) form;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message",service.checkSoleData(model));
		String json = JSONObject.fromObject(map).toString();
		response.getWriter().write(json);
		return null;
	}
}
