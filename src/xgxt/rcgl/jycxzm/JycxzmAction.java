package xgxt.rcgl.jycxzm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;


// TODO: Auto-generated Javadoc
/**
 * The Class JycxzmAction.
 */
public class JycxzmAction extends DispatchAction {

	/**
	 * jycczm manage.教育储蓄证明管理查询
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward searchJycczm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JycxzmForm myform = (JycxzmForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String tableName = request.getParameter("tableName");
		String userType = session.getAttribute("userType").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    
	    JycxzmService service = new JycxzmService();
	    
		if ("zjlg_xlzxs".equals(tableName)) {
			if(userType.equalsIgnoreCase("stu")){
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
			}	
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			JycxzmModel model = new JycxzmModel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			String[] colList = {"pk","xn","xqmc","xh","xm","xb","xymc","zymc","bjmc","zqsj","xxsh"};
			topTr = service.getToptrTitle(tableName,colList);
			rs = service.ser_JycczmQuery(model, tableName,colList);
		}
		if ("stu".equals(userType)) {
			myform.setXh(userName);
			myform.setXm(userNameReal);
		}
		
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("xbList", service.ser_getXbList());
		request.setAttribute("yhList", service.serv_getYhList());
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("sexList", DAO.getInstance().getSexList());
		//读写权判断		
		request.setAttribute("writeAble",(Base.chkUPower(userName,"jycxzmgl.do?method=searchJycczm", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("jycxzmcx");
	}
	
	/**
	 * jycczm manage.教育储蓄证明管理审核查询
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward auditJycczm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JycxzmForm myform = (JycxzmForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String userType = session.getAttribute("userType").toString();
		String tableName = request.getParameter("tableName");
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		JycxzmService service = new JycxzmService();
	    
		if ("zjlg_xlzxs".equals(tableName)) {
			if(userType.equalsIgnoreCase("stu")){
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
			}	
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			JycxzmModel model = new JycxzmModel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			String[] colList = {"pk","xn","xqmc","xh","xm","xb","xymc","zymc","bjmc","zqsj","xxsh"};
			topTr = service.getToptrTitle(tableName,colList);
			rs = service.ser_JycczmQuery(model, tableName,colList);
		}
		
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("xbList", service.ser_getXbList());
		request.setAttribute("yhList", service.serv_getYhList());
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("sexList", DAO.getInstance().getSexList());
		//读写权判断		
		request.setAttribute("writeAble",(Base.chkUPower(userName,"jycxzmgl.do?method=auditJycczm", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		
		return mapping.findForward("jycxzmsh");
	}

	/**
	 * Adds the jycczm.教育储蓄证明管理增加
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward addJycczm(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		JycxzmForm myform = (JycxzmForm) form;
		
		HttpSession  session = request.getSession(); 
		String tableName = request.getParameter("tableName");
		String userName = session.getAttribute("userName").toString();
		
		String act = request.getParameter("act");
		String xh = request.getParameter("xh");
		
		JycxzmService service = new JycxzmService();
		JycxzmModel model = new JycxzmModel();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		boolean result = false;
		if ("add".equals(act)) {
			FormModleCommon.formToGBK(myform);
			BeanUtils.copyProperties(model, myform);
//			model.setFbrxm(userNameReal);
			if (service.ser_JycczmIsExists(xh)) {
				result = service.ser_JycczmAdd(model,tableName);
				if (result) {
					request.setAttribute("done", "yes");
				} else {
					request.setAttribute("done", "no");
				}
			}else {
				request.setAttribute("isexists", "yes");
			}
		}
		if (StringUtils.isNotNull(xh)) {
			userName = xh;
		}
//		if(StringUtils.isNotNull(model.getId())){
//			map = service.ser_idforQuery(model.getId(),tableName);
//		}
		if (StringUtils.isNotNull(userName)) {
			map = service.getXsxx_ser(userName);
		}
		map.put("xn", Base.currXn);
		map.put("nd", Base.currNd);
		map.put("xq", Base.currXq);
		
		request.setAttribute("rs", map);
		request.setAttribute("yhList", service.serv_getYhList());
		request.setAttribute("xbList", service.ser_getXbList());
//		request.setAttribute("zxnrList", service.ser_zxnrQuery());
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		
		return mapping.findForward("jycxzmsq");
	}

	/**
	 * jycczm update.教育储蓄证明管理更新
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward jycczmUpdate(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		JycxzmForm myform = (JycxzmForm) form;
		String act = request.getParameter("act");
		String tableName = request.getParameter("tableName");
		String pk = request.getParameter("pkVal");
		pk = StringUtils.isNull(pk)?request.getParameter("pk"):pk;
		
		JycxzmService service = new JycxzmService();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		boolean result = false;
		if ("update".equals(act)) {
			JycxzmModel model = new JycxzmModel();
			FormModleCommon.formToGBK(myform);
			BeanUtils.copyProperties(model, myform);
			result = service.ser_JycczmUpdate(model,tableName);
			if (result) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		
		request.setAttribute("xbList", service.ser_getXbList());
		request.setAttribute("rs", map);
		request.setAttribute("yhList", service.serv_getYhList());
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		
		return mapping.findForward("jycxzmUpdate");
	}

	/**
	 * jycczm view.教育储蓄证明管理查看
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward jycczmView(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = request.getParameter("tableName");
		String pk = request.getParameter("pk");
		
		JycxzmService service = new JycxzmService();
		
		HashMap<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		
		request.setAttribute("rs", map);
//		request.setAttribute("zxnrList", service.ser_zxnrQuery());
		request.setAttribute("yhList", service.serv_getYhList());
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		
		return mapping.findForward("jycxzmView");
	}

	/**
	 * jycczm del.教育储蓄证明管理删除
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward jycczmDel(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pks = request.getParameter("pkVStr");
		String tableName = request.getParameter("tableName");
		
		JycxzmService service = new JycxzmService();
		String whichpk = service.dao_AllDelList(pks,tableName);
		
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("done", "yes");
		} else {
			request.setAttribute("done", "no");
		}
		return searchJycczm(mapping, form, request, response);
	}
	
	/**
	 * jycczm del.教育储蓄证明管理删除
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward jycczmSh(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String pks = request.getParameter("pkVStr");
		String chkVal = request.getParameter("chkVal");
		String userName = session.getAttribute("userName").toString();
		
		JycxzmService service = new JycxzmService();
		String whichpk = service.serv_jycczmSh(pks,userName,chkVal);
		
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("done", "yes");
		} else {
			request.setAttribute("done", "no");
		}
		return auditJycczm(mapping, form, request, response);
	}
	
	/**
	 * Exp data.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 */
	public ActionForward expData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		JycxzmForm myform = (JycxzmForm) form;
		
		String tableName = request.getParameter("tableName");
		
		JycxzmModel model = new JycxzmModel();	
		JycxzmService service = new JycxzmService();
		
		BeanUtils.copyProperties(model, myform);
		FormModleCommon.formToGBK(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dao_expData(tableName,response,model);
		
		return mapping.findForward("");
	}
}
