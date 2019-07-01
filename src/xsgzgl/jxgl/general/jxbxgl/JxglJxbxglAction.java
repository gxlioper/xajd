package xsgzgl.jxgl.general.jxbxgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class JxglJxbxglAction extends BasicAction{

	
	/**
	 * 军训表现管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("jxgl_jxkhgl_jxbxgl.do");
		// ----------------设置PATH end-----------------------
		JxglJxbxglService.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jxbxCx");
		}
		
	}
	
	/**
	 * 表现名单管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxmdCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String sfhd = request.getParameter("sfhd");
		if("yes".equalsIgnoreCase(sfhd)){
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_sfhd(new String[]{"是"});
			searchModel.setPath("jxgl_jxbxgl.do?method=bxmdCx");
			request.setAttribute("searchTj", searchModel);
		}
		model.setPkValue(pkValue);
		request.setAttribute("pkValue", pkValue);
		HashMap<String,String> rs = JxglJxbxglService.getJxbx(model);
		request.setAttribute("rs", rs);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		request.setAttribute("path", "jxgl_jxkhgl_jxbxgl.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxgl_jxbxgl.do?method=bxmdCx");
		// ----------------设置PATH end-----------------------
		JxglJxbxglService.setRequestValue(rForm, user, request);
		return mapping.findForward("bxmdCx");
		
	}
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxbxglForm model = (JxglJxbxglForm) form;
		JxglJxbxglService service = new JxglJxbxglService();
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllbxmd(model, request);

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
}
