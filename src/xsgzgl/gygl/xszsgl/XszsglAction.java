package xsgzgl.gygl.xszsgl;

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

public class XszsglAction extends BasicAction {
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward xszsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_xszsgl_xszsgl.do");
		XszsglForm myForm = (XszsglForm) form;
		XszsglService service = new XszsglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] colList = new String[]{"学号","姓名","年级",Base.YXPZXY_KEY,"班级","性别","楼栋名称","寝室号","床位号","辅导员","班主任"};
		if(Globals.XXDM_NTZYDX.equals(Base.xxdm)){//南通职业大学
			colList = new String[]{"学号","姓名","年级",Base.YXPZXY_KEY,"性别","楼栋名称","寝室号","床位号","入学方式","班主任"};
			request.setAttribute("tableName", "xg_view_gygl_new_xszsgl_ntzydx");	// 导出表
		}else{
			request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");	// 导出表
		}

		// =============== 执行查询操作 ===========
		rsArrList = service.getTsglZsxxList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		//学校代码
		request.setAttribute("xxdm", Base.xxdm);
		service.setRequestValue(rForm, user,request);
		
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	
	
	/**
	 * 学生住宿信息 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xszsxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		XszsglForm model = (XszsglForm) form;
		XszsglService service = new XszsglService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		
		//查询
		List<HashMap<String,String>> resultList = service.getExportResultList(model,user,request);//查询出所有记录，不分页
		
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
	
	
	
	public ActionForward bzxbzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		XszsglForm myForm = (XszsglForm) form;
		XszsglService service = new XszsglService();
		String xhs=request.getParameter("xhs");
		request.setAttribute("xhs", xhs);
		String[] xhstr=xhs.split("-");
		if(xhstr.length==1){
			String bz=service.getBzxbz(xhstr[0]);
			request.setAttribute("bz", bz);
		}
		if("save".equalsIgnoreCase(doType)){
			
			boolean flag=service.setBz(myForm,xhstr);
			String msg=flag?"保存成功":"保存失败";
			request.setAttribute("message", msg);
		}
		return mapping.findForward("bzxbzsz");
	}
	/**
	 * 学生住宿信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh=request.getParameter("pkValue");
		XszsglService service = new XszsglService();
		HashMap<String, String> rs=new HashMap<String, String>();
		rs=service.getXszsxx(xh);
		request.setAttribute("rs", rs);
		return mapping.findForward("xszsxxView");
	}
	
	
	
}
