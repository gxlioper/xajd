package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg;

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
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmwh.KycxxmwhService;

public class KycxxmjgAction extends SuperAction {
	
	private static final String url = "kycxgl_kycxxm_kycxxmjggl.do?method=kycxxmjgManageCx";

	/**
	 * 查询科研创新项目结果
	 */
	@SystemAuth(url = url)
	public ActionForward kycxxmjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
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
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "kycxgl_kycxxm_kycxxmjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kycxxmjgManage");
	}
	/**
	 * 增加科研创新项目结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目结果-增加")
	public ActionForward addKycxxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setCzr(user.getUserName());
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				String jgid = UniqID.getInstance().getUniqIDHash();
				model.setJgid(jgid);
				boolean result = service.insertKycxxmjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.KYCXGL_KYCXXM_KYCXXMWH_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		model.setXn(Base.currXn);
		model.setXmsqrxm(user.getRealName());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		model.setXmsqsj(df.format(new Date()));
		request.setAttribute("rs", model);
		KycxxmwhService kycxxmwhService = new KycxxmwhService();
		request.setAttribute("lbList", kycxxmwhService.getKycxxmwhOpenList());
		request.setAttribute("xnList", Base.getXnndList2());
		return mapping.findForward("addKycxxmjg");
	}
	/**
	 * 学生列表
	 */
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KycxxmjgForm myForm = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
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
		String path = "kycxgl_kycxxm_kycxxmjggl.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	/**
	 * 修改科研创新项目结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目结果-修改JGID:{jgid}")
	public ActionForward updateKycxxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setCzr(user.getUserName());
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				boolean result = service.updateKycxxmjg(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.KYCXGL_KYCXXM_KYCXXMWH_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		KycxxmjgForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", StringUtils.formatData(model));
		KycxxmwhService kycxxmwhService = new KycxxmwhService();
		request.setAttribute("lbList", kycxxmwhService.getKycxxmwhOpenList());
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("kycxxmcyList", service.getKycxxmcyList(model.getJgid(), user));
		return mapping.findForward("updateKycxxmjg");
	}

	/**
	 * 删除科研创新项目结果
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目结果-删除VALUES:{values}")
	public ActionForward delKycxxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String [] jgidArr = values.split(",");
			boolean rs = service.delKycxxmcy(jgidArr);
			int num = 0;
			if(rs){
				num = service.runDelete(jgidArr);
			}
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
	 * 查看科研创新项目结果
	 */
	@SystemAuth(url = url)
	public ActionForward viewKycxxmjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
		User user = getUser(request);
		model = service.getModel(model);
		request.setAttribute("rs", StringUtils.formatData(model));
		request.setAttribute("kycxxmcyList", service.getKycxxmcyList(model.getJgid(), user));
		return mapping.findForward("viewKycxxmjg");
	}
	/**
	 * 自定义导出设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
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
	 * 科研项目查询
	 */
	@SystemAuth(url = url)
	public ActionForward kycxxmjgManageCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.kycxxmjgManageCx(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "kycxgl_kycxxm_kycxxmjggl.do?method=kycxxmjgManageCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kycxxmjgManageCx");
	}
	
	/**
	 * 自定义导出设置-科研项目查询
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportDataCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmjgForm model = (KycxxmjgForm) form;
		KycxxmjgService service = new KycxxmjgService();
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListCx(model, user);//查询出所有记录，不分页
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
