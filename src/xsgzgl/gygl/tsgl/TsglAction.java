package xsgzgl.gygl.tsgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.Globals;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

public class TsglAction extends BasicAction {
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SystemLog(description = "访问公寓管理-统计查询-学生退宿信息查询-删除XH:{xh}")
	public ActionForward tsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_tsgl_tsgl.do");
		TsglForm myForm = (TsglForm) form;
		TsglService service = new TsglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		String doType=request.getParameter("doType");
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] colList = new String[]{"学号","姓名","年级",Base.YXPZXY_KEY,"性别","退宿寝室","入住时间","退宿时间","退宿原因","退宿备注"};

		String message="";
		if("delete".equalsIgnoreCase(doType)){
			
			boolean flag=service.deleteTsglInfo(myForm);
			
		    message = flag ? "操作成功" : "操作失败";
			
		}
		
		// =============== 执行查询操作 ===========
		rsArrList = service.getTsglInfoList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(colList));
		request.setAttribute("message", message);
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("tableName", "xg_gygl_new_tsxxb");	// 导出表
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	
	/**
	 *  退宿信息查询 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward tsxxcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		TsglForm model = (TsglForm) form;
		TsglService service = new TsglService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.getTsglInfoExportList(model,request);
		
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
	
	
	
	public ActionForward tsglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = request.getParameter("pkValue");
		
		TsglService service = new TsglService();	
		HashMap<String,String> map = service.viewTsxx(pk);
		request.setAttribute("map", map);
		return mapping.findForward("tsglView");
	}
	

}
