package xsgzgl.szdw.txgb;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.xljk.hzny.HznyXljkZxzxForm;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-21 上午08:50:58</p>
 */
public class SzdwTxgbAction extends BasicAction{
	
	SzdwTxgbService service = new SzdwTxgbService();

	// -----------------------公寓管理 检查日程管理 begin---------------------------
	
	/**
	 * 团学干部查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward txgbCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("szdw_xsgb_txgb.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_view_szdw_txgb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_szdw_txgbb");
		
		return mapping.findForward("txgbCx");
	
	}

	/**
	 * 团学干部管理增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward txgbZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user=getUser(request);
		
		XsxxglService stuService = new XsxxglService();
		
		String doType="add";
		
		String xh=request.getParameter("xh");
		
		String userType=user.getUserType();
		
		if("stu".equalsIgnoreCase(userType)){
			xh=user.getUserName();
		}
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		stuInfo.putAll(stuService.selectStuinfo(xh));
		
		request.setAttribute("doType", doType);
		
		request.setAttribute("path", "szdw_xsgb_txgb.do");

		request.setAttribute("rs", stuInfo);
		
		request.setAttribute("xn", Base.currXn);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("txgbWh");
	}
	
	/**
	 * 团学干部修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward txgbXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		SzdwTxgbForm myForm = (SzdwTxgbForm) form;
		
		String doType="modi";
		
		String pkValue=request.getParameter("pkValue");
		
		myForm.setPkValue(pkValue);

		HashMap<String,String>rs=service.getTxgbMap(myForm);
		
		rs.putAll(stuService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("doType", doType);
		// 单条记录信息
		request.setAttribute("rs", rs);
		
		request.setAttribute("pkValue", pkValue);
		// 加载学年、学期、年度列表
		FormModleCommon.setNdXnXqList(request);

		request.setAttribute("path", "szdw_xsgb_txgb.do");
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("txgbWh");
	}
	
	/**
	 * 团学干部详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward txgbCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		SzdwTxgbForm myForm = (SzdwTxgbForm) form;
		
		String pkValue=request.getParameter("pkValue");
		
		myForm.setPkValue(pkValue);
		// 单条记录信息
		HashMap<String,String>rs=service.getTxgbMap(myForm);
		
		rs.putAll(stuService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("rs", rs);
		
		request.setAttribute("path", "szdw_xsgb_txgb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("txgbCk");
	}

	// -----------------------公寓管理 检查日程管理 end---------------------------
}
