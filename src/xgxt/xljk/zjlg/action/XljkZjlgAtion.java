package xgxt.xljk.zjlg.action;

import java.lang.reflect.InvocationTargetException;
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
import xgxt.xljk.zjlg.form.XljkZjlgForm;
import xgxt.xljk.zjlg.model.XljkZjlgmodel;
import xgxt.xljk.zjlg.service.XljkZjlgService;

public class XljkZjlgAtion extends DispatchAction {

	/**
	 * 心理咨询师
	 */
	public ActionForward xlzxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgForm myform = (XljkZjlgForm) form;
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		XljkZjlgService service = new XljkZjlgService();
		String userName = session.getAttribute("userName").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = request.getParameter("tableName");
		String bmmc = (session.getAttribute("bmmc") == null)?"":session.getAttribute("bmmc").toString();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    String userType = session.getAttribute("userType").toString();
		String titleName = "";
		if ("zjlg_xlzxs".equals(tableName)) {
			titleName = "心理健康 - 心理咨询 - 心理咨询师";
			if(userType.equalsIgnoreCase("stu")){
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
			}	
		}
		if ("zjlg_xljkzdls".equals(tableName)) {
			titleName = "心理健康 - 心理咨询 - 心理健康指导老师";
			if(userType.equalsIgnoreCase("stu")){
				request.setAttribute("errMsg", "学生无权访问该页面！");
				return new ActionForward("/errMsg.do", false);
				}
		}
		if ("zjlg_xlzxxszl".equals(tableName)) {
			titleName = "心理健康 - 心理咨询 - 心理咨询学生助理";
		}
		if ("zjlg_xlxhhy".equals(tableName)) {
			titleName = "心理健康 - 心理健康协会 - 心理协会会员";
		}
		if ("zjlg_xlwy".equals(tableName)) {
			titleName = "心理健康 - 心理健康协会 - 心理委员";
		}

		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			XljkZjlgmodel model = new XljkZjlgmodel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			topTr = service.getToptrTitle(tableName);
			rs = service.ser_xlzxsQuery(model, tableName);
		}
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		String xy = request.getParameter("xy");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		if (userType.equalsIgnoreCase("student")) {
			myform.setXy(userDep);
			myform.setXm(userNameReal);
		}
		if (userType.equalsIgnoreCase("xy")) {
			myform.setXy(userDep);
//			myform.setXm(userNameReal);
			myform.setXymc(bmmc);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;		
		String bjKey = xy + "!!" + zy + "!!" + nj;	
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));	
		request.setAttribute("titleName", titleName);
		
		if (userType.equalsIgnoreCase("stu")) {
			DAO dao = DAO.getInstance();
			String sql = "select * from view_xsxxb a left join view_njxyzybj b on a.bjdm=b.bjdm where xh=?";
			HashMap<String, String> stuMap = dao.getMap(sql, new String[]{userName}, new String[]{"bjmc","xymc","xm","xydm","bjdm"}); 
			myform.setXm(stuMap.get("xm"));
			myform.setXh(userName);
			myform.setXy(stuMap.get("xydm"));
			myform.setBjdm(stuMap.get("bjdm"));
			myform.setBj(stuMap.get("bjdm"));
			myform.setXymc(stuMap.get("xymc"));
			myform.setBjmc(stuMap.get("bjmc"));
		}
		//读写权判断		
//		request.setAttribute("writeAble",(Base.chkUPower(userName,"zjlg_xljk.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("writeAble","yes");
		return mapping.findForward(tableName);
	}

	/**
	 * 心理咨询师
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward xlzxsAdd(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgForm myform = (XljkZjlgForm) form;
		XljkZjlgmodel model = new XljkZjlgmodel();
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
	    String userType = session.getAttribute("userType").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String bmmc = (session.getAttribute("bmmc") == null)?"":session.getAttribute("bmmc").toString();
		String userDep = session.getAttribute("userDep").toString();
		String titleName = "";
		String title = request.getParameter("titleType");
		String tableName = request.getParameter("tableName");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		if("add".equals(title)){
			if ("zjlg_xlzxs".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理咨询师申请";
			}
			if ("zjlg_xljkzdls".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理健康指导老师申请";
			}
			if ("zjlg_xlzxxszl".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理咨询学生助理申请";
			}
			if ("zjlg_xlxhhy".equals(tableName)) {
				titleName = "心理健康 - 心理健康协会 - 心理协会会员申请";
			}
			if ("zjlg_xlwy".equals(tableName)) {
				titleName = "心理健康 - 心理健康协会 - 心理委员申请";
			}
		}else if("update".equals(title)){
			if ("zjlg_xlzxs".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理咨询师修改";
			}
			if ("zjlg_xljkzdls".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理健康指导老师修改";
			}
			if ("zjlg_xlzxxszl".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理咨询学生助理修改";
			}
			if ("zjlg_xlxhhy".equals(tableName)) {
				titleName = "心理健康 - 心理健康协会 - 心理协会会员修改";
			}
			if ("zjlg_xlwy".equals(tableName)) {
				titleName = "心理健康 - 心理健康协会 - 心理委员修改";
			}
		}else if("view".equals(title)){
			if ("zjlg_xlzxs".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理咨询师查看";
			}
			if ("zjlg_xljkzdls".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理健康指导老师查看";
			}
			if ("zjlg_xlzxxszl".equals(tableName)) {
				titleName = "心理健康 - 心理咨询 - 心理咨询学生助理查看";
			}
			if ("zjlg_xlxhhy".equals(tableName)) {
				titleName = "心理健康 - 心理健康协会 - 心理协会会员查看";
			}
			if ("zjlg_xlwy".equals(tableName)) {
				titleName = "心理健康 - 心理健康协会 - 心理委员查看";
			}
		}
		request.setAttribute("titleName", titleName);
		XljkZjlgService service = new XljkZjlgService();
		boolean result = false;
		if ("add".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			if (service.serv_isexistsxlwy(model.getXh()) && "zjlg_xlwy".equals(tableName)) {
				result = service.ser_xlzxsAdd(model, tableName, request);
				request.setAttribute("done", "no");
			}else {
				result = service.ser_xlzxsAdd(model, tableName, request);
			}
			FormModleCommon.formToGBK(myform);
			if (result) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		
		if (userType.equalsIgnoreCase("stu")) {
			DAO dao = DAO.getInstance();
			String sql = "select a.xh,a.xb,a.xm,a.xydm,a.bjdm,b.xymc,b.bjmc from view_xsxxb a left join view_njxyzybj b on a.bjdm=b.bjdm where xh=?";
			HashMap<String, String> stuMap = dao.getMap(sql, new String[]{userName}, new String[]{"xh","bjmc","xymc","xm","xydm","bjdm","xb"}); 
			map.put("xh", stuMap.get("xh"));
			map.put("xm",stuMap.get("xm"));
			map.put("xy",stuMap.get("xydm"));
			map.put("bjdm",stuMap.get("bjdm"));
			map.put("bj",stuMap.get("bjdm"));
			map.put("xymc",stuMap.get("xymc"));
			map.put("bjmc",stuMap.get("bjmc"));
			map.put("xb", stuMap.get("xb"));
		}
		request.setAttribute("view", title);
		
		String xy = request.getParameter("xy");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;	
		if (userType.equalsIgnoreCase("xy")) {
			myform.setXy(userDep);
			myform.setXm(userNameReal);
			myform.setXymc(userNameReal);
			xy = userDep;
			myform.setXydm(userDep);
			map.put("xydm", userDep);
			map.put("xy", userDep);
			map.put("xymc", bmmc);
		}
		String bjKey = xy + "!!" + zy + "!!" + nj;	
		request.setAttribute("rs", map);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));		
		return mapping.findForward(tableName+"Add");
	}
	/**
	 * 删除
	 */
	public ActionForward xlzxsDel(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgService service = new XljkZjlgService();
		String pks = request.getParameter("pkVStr");
		String tableName = request.getParameter("tableName");
		String whichpk = service.dao_AllDelList(pks,tableName);
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("done", "yes");
		} else {
			request.setAttribute("done", "no");
		}
		if ("zjlg_xlcb".equals(tableName)) {
			return xlpcbManage(mapping, form, request, response);
		} else {
			return xlzxsManage(mapping, form, request, response);
		}
	}
	/**
	 * 导出数据
	 */
	public ActionForward expData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgForm myform = (XljkZjlgForm) form;
		XljkZjlgService service = new XljkZjlgService();
		String tableName = request.getParameter("tableName");
		XljkZjlgmodel model = new XljkZjlgmodel();
		BeanUtils.copyProperties(model, myform);
		FormModleCommon.formToGBK(model);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dao_expData(tableName,response,model);
		return mapping.findForward("");
	}
	/**
	 * 心理普查
	 */
	public ActionForward xlpcbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgForm myform = (XljkZjlgForm) form;
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		XljkZjlgService service = new XljkZjlgService();
		String userName = session.getAttribute("userName").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = request.getParameter("tableName");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    String userType = session.getAttribute("userType").toString();

		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			XljkZjlgmodel model = new XljkZjlgmodel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			topTr = service.getToptrTitle(tableName);
			rs = service.ser_xlzxsQuery(model, tableName);
		}
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		String xy = request.getParameter("xy");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		if (userType.equalsIgnoreCase("xy") || userType.equalsIgnoreCase("student")) {
			myform.setXy(userDep);
			myform.setXm(userNameReal);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;		
		String bjKey = xy + "!!" + zy + "!!" + nj;	
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));	
		request.setAttribute("path", "xlpcb.do");
		FormModleCommon.commonRequestSet(request);
		
		if (userType.equalsIgnoreCase("stu")) {
			DAO dao = DAO.getInstance();
			String sql = "select * from view_xsxxb a left join view_njxyzybj b on a.bjdm=b.bjdm where xh=?";
			HashMap<String, String> stuMap = dao.getMap(sql, new String[]{userName}, new String[]{"bjmc","xymc","xm","xydm","bjdm"}); 
			myform.setXm(stuMap.get("xm"));
			myform.setXy(stuMap.get("xydm"));
			myform.setBjdm(stuMap.get("bjdm"));
			myform.setBj(stuMap.get("bjdm"));
			myform.setXymc(stuMap.get("xymc"));
			myform.setBjmc(stuMap.get("bjmc"));
		}
		//读写权判断		
//		request.setAttribute("writeAble",(Base.chkUPower(userName,"zjlg_xljk.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("writeAble","yes");
		request.setAttribute("xlcslbList", service.ser_getxllbxlwtlx().get("xlcslb"));
		request.setAttribute("xlwtlxList", service.ser_getxllbxlwtlx().get("xlwtlx"));
		return mapping.findForward("xlpc_manager");
	}
	/**
	 * 心理普查
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward xlpcbAdd(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgForm myform = (XljkZjlgForm) form;
		XljkZjlgmodel model = new XljkZjlgmodel();
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
	    String userType = session.getAttribute("userType").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String userDep = session.getAttribute("userDep").toString();
		String titleName = "";
		String title = request.getParameter("titleType");
		String tableName = request.getParameter("tableName");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		
		XljkZjlgService service = new XljkZjlgService();
		boolean result = false;
		if ("add".equals(act)) {
			BeanUtils.copyProperties(model, myform);
			result = service.ser_xlpcAdd(model,tableName,request);
			FormModleCommon.formToGBK(myform);
			if (result) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		
		if (userType.equalsIgnoreCase("stu")) {
			DAO dao = DAO.getInstance();
			String sql = "select a.xm,a.xydm,a.bjdm,b.xymc,b.bjmc from view_xsxxb a left join view_njxyzybj b on a.bjdm=b.bjdm where xh=?";
			HashMap<String, String> stuMap = dao.getMap(sql, new String[]{userName}, new String[]{"bjmc","xymc","xm","xydm","bjdm"}); 
			map.put("xm",stuMap.get("xm"));
			map.put("xy",stuMap.get("xydm"));
			map.put("bjdm",stuMap.get("bjdm"));
			map.put("bj",stuMap.get("bjdm"));
			map.put("xymc",stuMap.get("xymc"));
			map.put("bjmc",stuMap.get("bjmc"));
		}
		request.setAttribute("view", title);
		request.setAttribute("rs", map);
		String xy = request.getParameter("xy");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
//		if (userType.equalsIgnoreCase("xy")) {
//			xy = userDep;
//		}
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;	
		if (userType.equalsIgnoreCase("xy")) {
			myform.setXy(userDep);
			myform.setXm(userNameReal);
			map.put("xm", userNameReal);
			xy = userDep;
		}
		String bjKey = xy + "!!" + zy + "!!" + nj;	
		
		request.setAttribute("path", "xlpcb.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		
		request.setAttribute("xlcslbList", service.ser_getxllbxlwtlx().get("xlcslb"));
		request.setAttribute("xlwtlxList", service.ser_getxllbxlwtlx().get("xlwtlx"));
		return mapping.findForward("xlpc_add");
	}
	/**
	 *工作联系
	 */
	public ActionForward gzlxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgForm myform = (XljkZjlgForm) form;
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		XljkZjlgService service = new XljkZjlgService();
		String userName = session.getAttribute("userName").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = request.getParameter("tableName");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
	    String userType = session.getAttribute("userType").toString();
	    String view = request.getParameter("titleType");
		String titleName = "";
		titleName = " 心理健康 - 工作联系 - 工作联系";

		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			XljkZjlgmodel model = new XljkZjlgmodel();
			BeanUtils.copyProperties(model, myform);
			FormModleCommon.formToGBK(model);
			topTr = service.getToptrTitle(tableName);
			if ("xx".equals(userType) || "admin".equals(userType)) {
				model.setUserlx(userType);
			}
			rs = service.ser_xlzxsQuery(model, tableName);
		}
		FormModleCommon.formToGBK(myform);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", "0");
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		String xy = request.getParameter("xy");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		if (userType.equalsIgnoreCase("student")) {
			myform.setXy(userDep);
			myform.setXm(userNameReal);
		}
		if (userType.equalsIgnoreCase("xy")) {
			myform.setXy(userDep);
			xy = userDep;
//			myform.setXm(userNameReal);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;		
		String bjKey = xy + "!!" + zy + "!!" + nj;	
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));	
		request.setAttribute("titleName", titleName);
		request.setAttribute("view", view);
		
		if (userType.equalsIgnoreCase("stu")) {
			DAO dao = DAO.getInstance();
			String sql = "select * from view_xsxxb a left join view_njxyzybj b on a.bjdm=b.bjdm where xh=?";
			HashMap<String, String> stuMap = dao.getMap(sql, new String[]{userName}, new String[]{"bjmc","xymc","xm","xydm","bjdm"}); 
			myform.setXm(stuMap.get("xm"));
			myform.setXy(stuMap.get("xydm"));
			myform.setBjdm(stuMap.get("bjdm"));
			myform.setBj(stuMap.get("bjdm"));
			myform.setXymc(stuMap.get("xymc"));
			myform.setBjmc(stuMap.get("bjmc"));
		}
		//读写权判断		
//		request.setAttribute("writeAble",(Base.chkUPower(userName,"zjlgXlzxs.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		request.setAttribute("writeAble","yes");
		return mapping.findForward("gzlx_manager");
	}
	/**
	 * 工作联系
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward gzlxAdd(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		XljkZjlgForm myform = (XljkZjlgForm) form;
		XljkZjlgmodel model = new XljkZjlgmodel();
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
	    String userType = session.getAttribute("userType").toString();
//		String userOnline = session.getAttribute("userOnLine").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String userDep = session.getAttribute("userDep").toString();
		String titleName = "";
		String title = request.getParameter("titleType");
		String tableName = request.getParameter("tableName");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pk");
		titleName = " 心理健康 - 工作联系 - 工作联系";

		request.setAttribute("titleName", titleName);
		XljkZjlgService service = new XljkZjlgService();
		boolean result = false;
		if ("add".equals(act)) {
//			if (!service.serv_isexistsxlwy(userName)) {
//				request.setAttribute("notxlwy", "yes");
//			}else {
				BeanUtils.copyProperties(model, myform);
				result = service.ser_xlzxsAdd(model,tableName,request);
				FormModleCommon.formToGBK(myform);
				if (result) {
					request.setAttribute("done", "yes");
				} else {
					request.setAttribute("done", "no");
				}
//			}
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,tableName);
		}
		
		if (userType.equalsIgnoreCase("stu")) {
			DAO dao = DAO.getInstance();
			String sql = "select a.xm,a.xydm,a.bjdm,b.xymc,b.bjmc from view_xsxxb a left join view_njxyzybj b on a.bjdm=b.bjdm where xh=?";
			HashMap<String, String> stuMap = dao.getMap(sql, new String[]{userName}, new String[]{"bjmc","xymc","xm","xydm","bjdm"}); 
			map.put("xm",stuMap.get("xm"));
			map.put("xh", userName);
			map.put("xlwyxm", stuMap.get("xm"));
			map.put("xy",stuMap.get("xydm"));
			map.put("xydm",stuMap.get("xydm"));
			map.put("bjdm",stuMap.get("bjdm"));
			map.put("bj",stuMap.get("bjdm"));
			map.put("xymc",stuMap.get("xymc"));
			map.put("bjmc",stuMap.get("bjmc"));
		}
		request.setAttribute("view", title);
		String xy = request.getParameter("xy");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;	
		if (userType.equalsIgnoreCase("xy")) {
			myform.setXy(userDep);
			myform.setXydm(userDep);
			map.put("xydm", userDep);
			myform.setXm(userNameReal);
			map.put("xm", userNameReal);
			xy = userDep;
		}
		request.setAttribute("rs", map);
		
//		if (userType.equalsIgnoreCase("xy")) {
//			xy = userDep;
//		}
		//
		
		String bjKey = xy + "!!" + zy + "!!" + nj;	
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));	
		request.setAttribute("zdlsList", service.dao_getXljkzdls());
		return mapping.findForward("gzlx_add");
	}
	/**
	 * 工作联系
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward getxsInfo(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		XljkZjlgService service = new XljkZjlgService();
		map = service.ser_getxsInfo(xh);
		map.put("xy", map.get("xydm"));
		map.put("bj", map.get("bjdm"));
		request.setAttribute("view", "add");
		request.setAttribute("rs", map);
		return mapping.findForward("zjlg_xlwyAdd");
	}
	/*
	 * 工作联系print
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward getxsInfoprint(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("id");
		XljkZjlgService service = new XljkZjlgService();
		if(StringUtils.isNotNull(pk)){
			map = service.ser_idforQuery(pk,"zjlg_gzlxb");
		}
		map.put("xy", map.get("xydm"));
		map.put("bj", map.get("bjdm"));
		request.setAttribute("view", "add");
		request.setAttribute("rs", map);
		return mapping.findForward("gzlx_print");
	}
	/**
	 * 学号刷学生信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward getxsInfoforxh(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		XljkZjlgService service = new XljkZjlgService();
		map = service.ser_getxsInfo(xh);
		map.put("xy", map.get("xydm"));
		map.put("bj", map.get("bjdm"));
		map.put("mz", map.get("mzmc"));
		request.setAttribute("view", "add");
		request.setAttribute("rs", map);
		request.setAttribute("path", "xlpcb.do");
		request.setAttribute("xlcslbList", service.ser_getxllbxlwtlx().get("xlcslb"));
		request.setAttribute("xlwtlxList", service.ser_getxllbxlwtlx().get("xlwtlx"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xlpc_add");
	}
	/**
	 * 心理协会会员学号刷学生信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward getxlxhhyforxh(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String titleName = " 心理健康 - 心理健康协会 - 心理协会会员申请";
		XljkZjlgService service = new XljkZjlgService();
		map = service.ser_getxsInfo(xh);
		map.put("xy", map.get("xydm"));
		map.put("bj", map.get("bjdm"));
		map.put("mz", map.get("mzmc"));
		request.setAttribute("view", "add");
		request.setAttribute("rs", map);
		request.setAttribute("titleName", titleName);
		return mapping.findForward("zjlg_xlxhhyAdd");
	}
	/**
	 * 心理咨询学生助理学号刷学生信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward getxlzxxszlforxh(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String titleName = " 心理健康 - 心理咨询 - 心理咨询学生助理申请";
		XljkZjlgService service = new XljkZjlgService();
		map = service.ser_getxsInfo(xh);
		map.put("xy", map.get("xydm"));
		map.put("bj", map.get("bjdm"));
		map.put("mz", map.get("mzmc"));
		request.setAttribute("view", "add");
		request.setAttribute("rs", map);
		request.setAttribute("titleName", titleName);
		return mapping.findForward("zjlg_xlzxxszlAdd");
	}
	/**
	 * 工作联系学号刷学生信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ActionForward getgzlxforxh(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String titleName = "心理健康 - 工作联系 - 工作联系";
		XljkZjlgService service = new XljkZjlgService();
		map = service.ser_getxsInfo(xh);
		map.put("xy", map.get("xydm"));
		map.put("bj", map.get("bjdm"));
		map.put("mz", map.get("mzmc"));
		map.put("xlwyxm", map.get("xm"));
		request.setAttribute("view", "add");
		request.setAttribute("rs", map);
		request.setAttribute("titleName", titleName);
		request.setAttribute("zdlsList", service.dao_getXljkzdls());
		return mapping.findForward("gzlx_add");
	}
}