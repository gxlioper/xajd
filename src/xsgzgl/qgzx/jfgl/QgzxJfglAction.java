package xsgzgl.qgzx.jfgl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * 勤工助学-勤工经费管理-经费信息管理
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglAction extends BasicAction{
	/**
	 * 经费信息管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		searchModel.setPath("qgzx_jfgl_jfxxgl.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_yf");
		}else{
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_nd");
		}
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_qgzx_jfhbb");
		if("no".equalsIgnoreCase(qgzxCsszService.getCssz().get("sfjfhb"))){
			String msg = "当前参数设置无需通过经费划拨控制酬金发放，该模块无需维护！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//验证是否是勤工管理员
		QgzxGlyglService qgzxGlyglService=new QgzxGlyglService();
		boolean sfQggly=qgzxGlyglService.sfQggly(user.getUserName());
		if(!sfQggly){
			String msg = "该模块仅允许勤工管理员用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		
		return mapping.findForward("jfxxCx");
	}
	
	public ActionForward jfxxInit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		HashMap<String, String> rs = service.getMrcs(user);
		service.setRequestValue(rForm, user, request);
		QgzxJfglForm myForm =  (QgzxJfglForm)form;
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		myForm.setXn(Base.currXn);
		request.setAttribute("ndList", Base.getXnndList());
		//取当前学年的用人部门列表
		String qgzq = QgCommUtilf.getQgzq();
		String xq = "xq".equals(qgzq) ? Base.currXq : null;
		request.setAttribute("bmList", service.getBms(Base.currXn,xq));
		request.setAttribute("nowTime",GetTime.getNowTime2());
		request.setAttribute("rs", rs);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		this.saveToken(request);

		return mapping.findForward("jfxxInit");
	}
	
	/**
	 * 经费信息增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		HashMap<String, String> rs = service.getMrcs(user);
		service.setRequestValue(rForm, user, request);
		QgzxJfglForm myForm =  (QgzxJfglForm)form;
		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList2());
		myForm.setXn(Base.currXn);
		request.setAttribute("ndList", Base.getXnndList());
		String qgzq = QgCommUtilf.getQgzq();
		String xq = "xq".equals(qgzq) ? Base.currXq : null;
		myForm.setXq(xq);
		myForm.setNd(Base.currNd);
		//取当前学年的用人部门列表
		request.setAttribute("bmList", service.getBms(Base.currXn,xq));
		request.setAttribute("nowTime",GetTime.getNowTime2() );
		request.setAttribute("rs", rs);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		request.setAttribute("qgzq",qgzq);
		request.setAttribute("xqList", Base.getXqList());
		this.saveToken(request);
		return mapping.findForward("jfxxZj");
	}
	/**
	 * 经费信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxXg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = new QgzxJfglForm();
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		myForm.setPkValue(pkValue);
		myForm.setDoType(doType);
		HashMap<String, String> rs = service.getJfxxMap(myForm);
		List<HashMap<String,String>> list = new QgzxJfglDAO().getJfxxList(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		String qgzq = QgCommUtilf.getQgzq();
		request.setAttribute("qgzq", qgzq);
		//工具栏跨列判断
		String colspan = "xq".equals(qgzq)? "6":"5";
		request.setAttribute("col", colspan);
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("jfxxXg");
	}
	
	
	/**============================浙大新勤工Begin============================*/
	/**
	 * @描述: 查询列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-20 下午04:02:24
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
	public ActionForward jfhbxxList(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		QgzxJfglForm model = (QgzxJfglForm) form;
		QgzxJfglService service = new QgzxJfglService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		request.setAttribute("searchTj", searchModel);
		
		String path = "qgzx_jfcjgl_jfhb_zjdx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jfhbxxList");
	}
	/**
	 * @描述: 增加
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-19 上午09:50:00
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
	public ActionForward jfhbxxAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		service.setRequestValue(rForm, user, request);
		//取年度list
		request.setAttribute("ndList", Base.getXnndList());
		//取当前学年的用人部门列表
		request.setAttribute("bmList", service.getYrbm());
		//取系统当前格式，格式为
		request.setAttribute("nowTime",GetTime.getNowTime2());
		//传出年度和用人部门代码
		HashMap<String, String> rs = service.getXycs(user);
		request.setAttribute("rs", rs);
		this.saveToken(request);
		return mapping.findForward("jfhbxxAdd");
	}
	
	/**
	 * @描述: 增加保存验证
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-19 上午09:49:44
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
	public ActionForward checkBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm model = (QgzxJfglForm) form;
		// 传输乱码问题
		model.setBm(service.unicode2Gbk(model.getBm()));
		model.setHbsj(service.unicode2Gbk(model.getHbsj()));
		String message = service.checkBcInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * @描述: 修改方法
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-19 下午02:09:58
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
	public ActionForward jfhbxxUpdate(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = new QgzxJfglForm();
		RequestForm rForm = new RequestForm();
		//因为经费划拨表是联合主键，现从JS中做一个主键带出来
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		myForm.setPkValue(pkValue);
		myForm.setDoType(doType);
		HashMap<String, String> rs = service.getZdJfxxMap(myForm);
		request.setAttribute("rs", rs);
		User user = getUser(request);
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_jfcjgl_jfhb_zjdx.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("jfhbxxUpdate");
	}
	/**
	 * @描述: 修改保存验证
	 * @作者：  Meng.Wei[工号：1186]
	 * @日期：2016-12-19 下午04:51:12
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
	public ActionForward checkXgBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// 传输乱码问题
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		String message = service.checkXgBcInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * @描述: 修改保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-19 下午04:44:25
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
	public ActionForward updateBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// 传输乱码问题
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxXg(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/** 
	 * @描述: 查看方法
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-20 上午10:14:03
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
	public ActionForward jfhbxxView(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = new QgzxJfglForm();
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		myForm.setPkValue(pkValue);
		HashMap<String, String> rs = service.getJfhbMap(myForm);
		request.setAttribute("rs", rs);
		User user = getUser(request);
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_jfcjgl_jfhb_zjdx.do");
		service.setRequestValue(rForm, user, request);
		//查询发放明细列表
		HashMap<String,String> ffmx = service.getFfmxList(myForm);
		request.setAttribute("ffmx", ffmx);
		return mapping.findForward("jfhbxxView");
	}
	/**
	 * @描述: 导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-20 下午06:31:52
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglForm model = (QgzxJfglForm) form;
		QgzxJfglService service = new QgzxJfglService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getPageList(model, user);//查询出所有记录，不分页
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
	/**============================浙大新勤工End============================*/
	
	/**
	 * @描述: 复制经费划拨
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-20 下午06:31:52
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
	public ActionForward copyJfhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List<HashMap<String, String>> ndyfList = new QgzxJfglService().getSourceMonth();
		request.setAttribute("ndyfList", ndyfList);
		request.setAttribute("targetMonth", xgxt.utils.date.DateUtils.getCurrYearAndMonth2());
		if(!new QgzxJfglService().checkIsNotJfhbDataExist()){
			request.setAttribute("message", "当前月份已有经费划拨数据，不能进行拷贝操作！");
			return mapping.findForward("error");
		}else{
			return mapping.findForward("copyjfhb");
		}
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-24 下午01:21:12
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
	public ActionForward saveJfhbCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sourcemonth = request.getParameter("sourcemonth");
		String targetmonth = request.getParameter("targetmonth");
		boolean rs = new QgzxJfglService().copyJfhbData(targetmonth, sourcemonth);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
