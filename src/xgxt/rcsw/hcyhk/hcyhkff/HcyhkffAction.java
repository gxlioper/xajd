package xgxt.rcsw.hcyhk.hcyhkff;

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
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.szyqxy.zhszcp.PjpySzyqxyZhszcpDAO;
import xgxt.rcsw.RcswForm;
import xgxt.rcsw.xszgl.XszglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class HcyhkffAction extends BasicAction {

	/**
	 * 火车优惠卡发放
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hcyhkffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		RcswForm myForm = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();
		CommService commService = new CommService();

		String doType = request.getParameter("doType");
		String ffsj = request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		//之前设置为当前用户所属学院，先一个用户再带多个班下会有多个学院进行选择。
		//user.setUserDep(myForm.getQueryequals_xydm());
		myForm.setUser(user);
		
		String message = "";

		// -----------取消发放-------------
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.delHcyhkff(myForm);
			message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			request.setAttribute("message", message);
		}

		// -----------批量发放---------
		if ("plff".equalsIgnoreCase(doType)) {
			boolean flag = service.plHcyhkff(myForm);
			message = flag ? MessageInfo.MESSAGE_EXTEND_SUCCESS
					: MessageInfo.MESSAGE_EXTEND_FALSE;
			request.setAttribute("message", message);
		}

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(service.getTopTr("hcyhkff", rForm)
				.size() - 1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);

		myForm.setUser(user);

		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}

		if (Base.isNull(myForm.getNd())) {
			myForm.setNd(Base.currNd);
		}

		rForm.setRsArrList((ArrayList<String[]>) service.queryHcyhkff(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============通用设置 end ============

		request.setAttribute("path", "hcyhkff.do?method=hcyhkffManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);

		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("topTr", service.getTopTr("hcyhkff", rForm));
		return mapping.findForward("hcyhkffManage");
	}

	/**
	 * 学生证注册
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hcyhkffUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		User user = getUser(request);
		RcswForm myForm = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);

		// =================学生证注册 begin==================
		String message = "";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addHcyhk(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================学生证注册 end==================

		request.setAttribute("path", "hcyhkff.do?method=hcyhkffManage");
		FormModleCommon.commonRequestSet(request);

		// ============学年、学期、学期名称 begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============学年、学期、学期名称 end====================
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xh", xh);
		request.setAttribute("doType", "add");
		return mapping.findForward("hcyhkffUpdate");
	}

	/**
	 * 学生证注册信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hcyhkffOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();

		RcswForm myForm = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();

		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		XsxxglService stuService = new XsxxglService();

		HashMap<String, String> rs = new HashMap<String, String>();
		String message = "";
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setPkValue(pkValue);
			boolean flag = service.updateHcyhk(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		// 学生证注册信息
		myForm.setPkValue(pkValue);
		rs = service.getHcyhkMap(myForm);

		request.setAttribute("path", "hcyhkff.do?method=hcyhkffManage");
		FormModleCommon.commonRequestSet(request);

		// ============学年、学期、学期名称 begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("nd", rs.get("nd"));
		request.setAttribute("xh", rs.get("xh"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		request.setAttribute("pkValue", pkValue);
		// ============学年、学期、学期名称 end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		return mapping.findForward("hcyhkffUpdate");
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswForm model = (RcswForm) form;
		HcyhkffService service = new HcyhkffService();
		//生成高级查询对象
/*		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);*/
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = service.getExportList(model,user);//查询出所有记录，不分页
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
