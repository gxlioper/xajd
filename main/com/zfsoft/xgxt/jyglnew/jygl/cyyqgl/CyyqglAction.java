package com.zfsoft.xgxt.jyglnew.jygl.cyyqgl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class CyyqglAction extends SuperAction {
	
	private static final String url = "jyglnew_jygl_cyyqgl.do";
	
	/**
	 * 查询创业园区管理
	 */
	@SystemAuth(url = url)
	public ActionForward cyyqglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CyyqglForm model = (CyyqglForm) form;
		CyyqglService service = new CyyqglService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "该模块不允许学生访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jyglnew_jygl_cyyqgl.do";
		request.setAttribute("path", path);
		// 默认条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cyyqglManage");
	}
	/**
	 * 学生列表
	 */
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CyyqglForm myForm = (CyyqglForm) form;
		CyyqglService service = new CyyqglService();
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jyglnew_jygl_cyyqglgl.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	/**
	 * 增加创业园区管理
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-创业园区管理-增加")
	public ActionForward addCyyqgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CyyqglForm model = (CyyqglForm) form;
		CyyqglService service = new CyyqglService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				String id = UniqID.getInstance().getUniqIDHash();
				model.setGsid(id);
				boolean result = service.insertCyyqgl(model, user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String message = MessageUtil.getText(MessageKey.JYGLNEW_JYGL_CYYQGL_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		List<HashMap<String, String>> sshyList = service.getSshyList();
		request.setAttribute("sshyList", sshyList);
		List<HashMap<String, String>> gslxList = new OptionUtil().getOptions(OptionUtil.GSLX);
		request.setAttribute("gslxList", gslxList);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);
		request.setAttribute("isnotList", isnotList);
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("xqList", Base.getXqList());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setSfzc("1");
		String path = "jyglnew_jygl_cyyqglgl.do?method=addCyyqgl";
		request.setAttribute("path", path);
		return mapping.findForward("addCyyqgl");
	}
	/**
	 * 修改创业园区管理
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-创业园区管理-修改XH:{xh}")
	public ActionForward updateCyyqgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CyyqglForm model = (CyyqglForm) form;
		CyyqglService service = new CyyqglService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				boolean result = service.updateCyyqgl(model, user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String message = MessageUtil.getText(MessageKey.JYGLNEW_JYGL_CYYQGL_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		List<HashMap<String, String>> sshyList = service.getSshyList();
		request.setAttribute("sshyList", sshyList);
		List<HashMap<String, String>> gslxList = new OptionUtil().getOptions(OptionUtil.GSLX);
		request.setAttribute("gslxList", gslxList);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);
		request.setAttribute("isnotList", isnotList);
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("xqList", Base.getXqList());
		HashMap<String, String> updateForm = service.getModelMap(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("cyList", service.getTdmxList(model, user));
		return mapping.findForward("updateCyyqgl");
	}
	/**
	 * 删除创业园区管理
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-创业园区管理-删除VALUES:{values}")
	public ActionForward delCyyqgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CyyqglForm model = (CyyqglForm) form;
		CyyqglService service = new CyyqglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.delCyyqgl(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 查看创业园区管理
	 */
	@SystemAuth(url = url)
	public ActionForward viewCyyqgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CyyqglForm model = (CyyqglForm) form;
		CyyqglService service = new CyyqglService();
		User user = getUser(request);
		HashMap<String, String> updateForm = service.getModelMap(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("cyList", service.getTdmxList(model, user));
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("viewCyyqgl");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CyyqglForm model = (CyyqglForm) form;
		CyyqglService service = new CyyqglService();
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
	
}
