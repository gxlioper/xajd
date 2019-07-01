package xsgzgl.gygl.xszstj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class XszstjAction extends BasicExtendAction{

	public ActionForward xszstjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_xszstj_xszstj.do");
		User user=getUser(request);
		
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		if(fdyqx||bzrqx){
			request.setAttribute("fdybzr", "true");
		}
		XszstjForm myForm=(XszstjForm)form;
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> list=service.getXszstjxxList(myForm,request);
		request.setAttribute("rs", list);
		request.setAttribute("searchTj", myForm.getSearchModel());

		// write和titile
		setWriteAbleAndTitle(request, "gyglnew_xszstj_xszstj.do");
		return mapping.findForward("xxzstjManage");
	}
	
	
	public ActionForward zstjExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjForm model=(XszstjForm)form;
		XszstjService service=new XszstjService();
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList=service.getXszstjxxExportList(model,request);
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
	 * 导出寝室分布统计表
	 * @author wujian
	 */
	public ActionForward qsfbbTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjService service=new XszstjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("gyglnew_xszstj_xszstj.do");
		service.setRequestValue(rForm, user, request);
		// 获取学院下拉列表框
		request.setAttribute("xyList", service.getXyList(request));
		return mapping.findForward("qsfbbTj");
	}
	
	public ActionForward qsfbDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjForm myForm=(XszstjForm)form;
		XszstjService service=new XszstjService();
		String xymc= request.getParameter("xymc");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String(xymc.getBytes("gb2312"),"iso-8859-1")+"qsfbdc.xls");
		service.expQsfbb(myForm,response.getOutputStream(),xymc);
		return null;
	}
	public ActionForward xszstjDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjForm myForm=(XszstjForm)form;
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> list=service.getXszstjDetailxxList(myForm);
		request.setAttribute("rs", list);
		request.setAttribute("path", "gyglnew_xszstj_detail.do");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xxzstjDetail");
	}
	
	public ActionForward xszstjXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszstjForm myForm = (XszstjForm) form;
		XszstjService service=new XszstjService();
//		RequestForm rForm = new RequestForm();
//		User user = getUser(request);// 用户对象
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// 结果集显示字段
		String[] colList = new String[]{"学号","姓名","年级",Base.YXPZXY_KEY,"性别","楼栋名称","寝室号","床位号"};

		// =============== 执行查询操作 ===========
		rsArrList = service.getXszstjXsxxList(myForm,request);
//		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
//		service.setRequestValue(rForm, user,request);
		request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");
		
		request.setAttribute("path", "gyglnew_xszsgl_xszsgl.do");
		request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");	// 导出表
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("xxzstjXsxx");
	}
	
	
	/**
	 * 
	 * @描述:图表统计
	 * @作者：cq [工号：785]
	 * @日期：2016-5-17 下午04:06:22
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
	public ActionForward ztqksyt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> listTbtj = service.getTbtj();
		HashMap<String, String> zsgktj = service.getZsgktj();
		
		StringBuffer lddm = new StringBuffer();
		StringBuffer ldmc = new StringBuffer();
		StringBuffer yrz = new StringBuffer();
		StringBuffer kcw = new StringBuffer();
		StringBuffer rzl = new StringBuffer();
		
		
		for (HashMap<String, String> tbtj : listTbtj) {
			lddm.append(tbtj.get("lddm")+",");
			ldmc.append(tbtj.get("ldmc")+",");
			yrz.append(tbtj.get("yrz")+",");
			kcw.append(tbtj.get("kcw")+",");
			rzl.append(tbtj.get("rzl")+",");
		}
		if (lddm.length() > 1 && ',' == lddm.charAt(lddm.length() - 1)){ 
			request.setAttribute("lddm", lddm.deleteCharAt(lddm.length()-1));
			request.setAttribute("ldmc", ldmc.deleteCharAt(ldmc.length()-1));
			request.setAttribute("yrz", yrz.deleteCharAt(yrz.length()-1));
			request.setAttribute("kcw", kcw.deleteCharAt(kcw.length()-1));
			request.setAttribute("rzl", rzl.deleteCharAt(rzl.length()-1));
		 }
		request.setAttribute("path", "gyglnew_xszstj_ztqksyt.do");
		request.setAttribute("zsgktj", zsgktj);
		return mapping.findForward("ztqksyt");
	}
	
	
	/**
	 * 
	 * @描述:宿舍楼具体信息图
	 * @作者：cq [工号：785]
	 * @日期：2016-5-19 上午10:28:28
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
	public ActionForward ssljtxxt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> SslInfo = service.getSslInfo();
		List<HashMap<String, String>> SslcInfo = service.getSslcInfo();

		request.setAttribute("SslInfo", SslInfo);
		request.setAttribute("SslcInfo", SslcInfo);
		request.setAttribute("path", "gyglnew_xszstj_ssljtxxt.do");
		
		return mapping.findForward("ssljtxxt");
	}
	
	
	/**
	 * 
	 * @描述:各系宿舍分布图
	 * @作者：cq [工号：785]
	 * @日期：2016-6-1 上午10:25:43
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
	public ActionForward gxssfbt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> gxsstj = service.getGxsstj();

		request.setAttribute("gxsstj", gxsstj);
		request.setAttribute("path", "gyglnew_xszstj_gxssfbt.do");
		
		return mapping.findForward("gxssfbt");
	}
	
	
	/*
	 * 各系空床位信息
	 */
	public ActionForward gxkcwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszstjService service=new XszstjService();
		List<HashMap<String, String>> gxkcwxx = service.getGxkcwxx();

		request.setAttribute("gxkcwxx", gxkcwxx);
		request.setAttribute("path", "gyglnew_xszstj_gxkcwxx.do");
		
		return mapping.findForward("gxkcwxx");
	}
	
}
