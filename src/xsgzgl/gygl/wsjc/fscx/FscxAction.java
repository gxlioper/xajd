package xsgzgl.gygl.wsjc.fscx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 上午11:29:22
 * </p>
 */
public class FscxAction extends BasicAction {

	/**
	 * 卫生检查，卫生分信息的查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);

		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_wsjc_fscx.do");
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_gygl_new_wsjc_qsfsb_dc");
		
		//------------------设置高级查询默认值-------------
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj", searchModel);
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("userType", user.getUserType());
		
		if (("0").equals(GyglNewInit.JFFS)) {
			request.setAttribute("sfsdj", "0");
		} else {
			request.setAttribute("sfsdj", "1");
		}
		return mapping.findForward("fscxCx");
	}

	/**
	 * 卫生检查，卫生分信息的查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm fscxForm = (FscxForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("lddm", lddm);
		request.setAttribute("qsh", qsh);
		
		request.setAttribute("sfsdj", GyglNewInit.JFFS);
		
		HashMap<String,String> jcrc = service.getFscxCz2(null, pkValue);
		
		String jclx = jcrc.get("jclx");
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		request.setAttribute("jclx", jclx);
		// 检查日程
		HashMap<String, String> jcrcbt = service.getFscxCz2(fscxForm, pkValue);
		String jcrcmc = jcrcbt.get("mc") + "[" + jcrcbt.get("kssj") + "至"+ jcrcbt.get("jssj") + "]";
		request.setAttribute("jcrc", jcrcmc);

		// 查询分数录入详细
		fscxForm.setLddm(lddm);
		fscxForm.setGuid(pkValue);
		fscxForm.setQsh(qsh);
		HashMap<String, String> fscxxx = service.getFscxMap(fscxForm);
		HashMap<String, String> fscxxxAll = service.getFscxAllMap(fscxForm);
		request.setAttribute("rs", fscxxx);
		request.setAttribute("rsAll", fscxxxAll);
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_wsjc_fslr.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("fscxCk");
	}
	
	/**
	 * 卫生检查，卫生分信息的维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm fscxForm = (FscxForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("lddm", lddm);
		request.setAttribute("qsh", qsh);
		
		request.setAttribute("sfsdj", GyglNewInit.JFFS);

		HashMap<String,String> jcrc = service.getFscxCz2(null, pkValue);
		
		String jclx = jcrc.get("jclx");
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		request.setAttribute("jclx", jclx);
		
		// 检查日程
		HashMap<String, String> jcrcbt = service.getFscxCz2(fscxForm, pkValue);
		String jcrcmc = jcrcbt.get("mc") + "[" + jcrcbt.get("kssj") + "至"+ jcrcbt.get("jssj") + "]";
		request.setAttribute("jcrc", jcrcmc);

		// 查询分数录入详细
		fscxForm.setLddm(lddm);
		fscxForm.setGuid(pkValue);
		fscxForm.setQsh(qsh);
		HashMap<String, String> fscxxx = service.getFscxMap(fscxForm);
		request.setAttribute("rs", fscxxx);
		// 获取等级下拉列表框
		request.setAttribute("djlist", service.getDjList(request));
		request.setAttribute("xjlist", service.getXjList(request));
		
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_wsjc_fslr.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("fscxWh");
	}

	/**
	 * 卫生检查，对卫生分信息的批量删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-卫生检查-卫生分查询-删除VALUES:{str}")
	public ActionForward fscxSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		String message = "";
		boolean flag = false;
		FscxForm myForm = (FscxForm) form;
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.fscxSc(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @description	：公寓月卫生分导出页面
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-17 上午11:20:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyywsfDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		request.setAttribute("nd", Base.currNd);
		FscxService fscxService = new FscxService();
		List<HashMap<String,String>> yfList = Base.getYfList();
		request.setAttribute("yfList", yfList);
		List<HashMap<String,String>> ldList = fscxService.getLdList();
		request.setAttribute("ldList", ldList);
		return  mapping.findForward("gyywsfDc");
	}
	
	/**
	 * @description	： 公寓卫生分月统计导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-17 下午02:27:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gywsfdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.gywsfdc(model,response.getOutputStream());
				
		return null;		
	}
	
	/**
	 * @description	： 是否检查
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-20 下午04:31:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sfjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;

		boolean result = true;
		List<HashMap<String, String>> list = service.getLdYwsfList(model);
		
		if (null != list && list.size() > 0) {
			result = true;
			response.getWriter().print(result);
		}else{
			result = false;
			response.getWriter().print(result);
		}
		return null;		
	} 
}