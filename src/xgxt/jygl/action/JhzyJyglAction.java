package xgxt.jygl.action;

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
import xgxt.base.DealString;
import xgxt.jygl.form.JhzyForm;
import xgxt.jygl.model.JhzyModel;
import xgxt.jygl.service.JhzyService;
import xgxt.jygl.util.JyglUnit;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;

// TODO: Auto-generated Javadoc
/**
 * The Class JhzyJyglAction.金华职业就业
 */
public class JhzyJyglAction extends DispatchAction {

	/**
	 * Jysbsjsz.就业上报时间设置
	 */
	public ActionForward jysbsjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyForm myform = (JhzyForm) form;
		JhzyModel model = new JhzyModel();
		JhzyService service = new JhzyService();
		String userType = request.getSession().getAttribute("userType").toString();
//		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		boolean result = false;
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}	
		if("save".equals(doType)){
			BeanUtils.copyProperties(model, myform);
			result = service.serv_savesjsz(model,request);
			if(result){
				request.setAttribute("result", "yes");
			}else{
				request.setAttribute("result", "no");
			}
		}
		map = service.serv_jysbsjsz();
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	/**
	 * Jysb.就业上报查询
	 * 
	 */
	public ActionForward jysb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyForm myform = (JhzyForm) form;
		JhzyModel model = new JhzyModel();
		JhzyService service = new JhzyService();
		ArrayList<String[]> rs = new ArrayList<String[]> ();
		List<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = request.getSession().getAttribute("userType").toString();
//		String userDep = request.getSession().getAttribute("userDep").toString();
		String go = request.getParameter("go");
		if("xx".equals(userType) || "admin".equals(userType)){
			userType = "xx";
		}else{
			userType = "xy";
		}
		int count = 0;
		if("go".equals(go)){
			BeanUtils.copyProperties(model, myform);
			JyglUnit.formToGBK(model);
			myform.setXl(model.getXl());
			rs = service.serv_jysbquery(myform,model);
			String[] inputValue = new String[]{"id","xymc","zymc","bynf","sbsjd","xl","byrs","qyrs","yprs","jyrs","lhrs","cghsxrs","qyl","ypl","jyl","xxsh"};
			topTr = service.getTitleList(inputValue,"jhzy_jysbb");
			count = rs != null ? rs.size():0;
		}
		request.setAttribute("userType", userType);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", count);
		JyglUnit.setNjXyZyBjList(request,myform);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}
	/**
	 * Jysb.就业上报增加
	 * 
	 */
	public ActionForward jysbadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyForm myform = (JhzyForm) form;
		DAO dao = DAO.getInstance();
		JhzyModel model = new JhzyModel();
		JhzyService service = new JhzyService();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		boolean bool = false;
		if("xy".equals(userType)){
			bool = true;
			String sfsj = dao.getOneRs(
					"select to_char(sysdate,'yyyymmdd') rtime from dual",
					new String[] {}, new String[] { "rtime" })[0];
			map = service.serv_querysjsz(sfsj);
			if(map == null || "".equals(map) || map.isEmpty()){
				bool = false;
				request.setAttribute("nosqsj", "nosqsj");
			}
		}else{
			request.setAttribute("noxy", "noxy");
		}
		
		if(bool){
			if("save".equals(doType)){
				BeanUtils.copyProperties(model, myform);
				JyglUnit.formToGBK(model);
				myform.setXl(model.getXl());
				boolean rut = service.serv_issavejysb(model);
				boolean result = false;
				if(rut){
					bool = false;
					request.setAttribute("istj", "istj");
				}else{
					result = service.serv_savejysb(model);
					if(result){
						request.setAttribute("save", "yes");
					}else{
						request.setAttribute("save", "no");
					}
				}
			}	
		}
		if(StringUtils.isNotNull(userDep)){
			myform.setXydm(userDep);
		}
		JyglUnit.setNjXyZyBjList(request,myform);
		if((Base.getZyMap()).get(myform.getXydm())==null){
		request.setAttribute("zyList", (Base.getZyMap()).get(""));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("add");
	}
	/**
	 * Jysb.就业上报修改
	 * 
	 */
	public ActionForward jysbupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyForm myform = (JhzyForm) form;
		JhzyModel model = new JhzyModel();
		JhzyService service = new JhzyService();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String pk = request.getParameter("pkValue");
		
			if("save".equals(doType)){
				BeanUtils.copyProperties(model, myform);
				JyglUnit.formToGBK(model);
				myform.setXl(model.getXl());
				model.setId(pk);
				boolean result = service.serv_updatejysb(model,request);
				if(result){
					request.setAttribute("save", "yes");
				}else{
					request.setAttribute("save", "no");
				}
			}	
			if(StringUtils.isNotNull(userDep)){
				myform.setXydm(userDep);
			}
		map = service.serv_jysbview(pk,"update");
		JyglUnit.setNjXyZyBjList(request,myform);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("update");
	}
	/**
	 * Jysb.就业上报view
	 * 
	 */
	public ActionForward jysbView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyForm myform = (JhzyForm) form;
		JhzyService service = new JhzyService();
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pkValue");
		map = service.serv_jysbview(pk,"view");
		JyglUnit.setNjXyZyBjList(request,myform);
		request.setAttribute("rs", map);
		return mapping.findForward("view");
	}
	/**
	 * Jysb.就业上报.删除
	 * 
	 */
	public ActionForward jysbDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyService service = new JhzyService();
		String pks = request.getParameter("pkstring");
		String whichpk = service.serv_deljysb(pks);
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("whichpk", whichpk);
		}
		return jysb(mapping,form,request,response);
	}
	/**
	 * Jysb.就业上报.全部清空
	 * 
	 */
	public ActionForward jysbDelAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyService service = new JhzyService();
		boolean judge = false;
		judge = service.serv_delAlljysb();
		if (judge) {
			request.setAttribute("delallinfo", "ok");
		} else {
			request.setAttribute("delallinfo", "no");
		}
		return jysb(mapping,form,request,response);
	}
	/**
	 * Jysb.就业上报.学校审核
	 * 
	 */
	public ActionForward jysbXXsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyService service = new JhzyService();
		String pks = request.getParameter("pkstring");
		String xxsh = request.getParameter("xxsh");
		if("yes".equals(xxsh)){
			xxsh="通过";
		}else{
			xxsh="不通过";
		}
		String xxshr = request.getSession().getAttribute("userName").toString();
		String whichpk = service.serv_jysbXxsh(pks,xxsh,xxshr);
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("xxsh", "yes");
		} else {
			request.setAttribute("xxsh", "no");
			request.setAttribute("whichpk", whichpk);
		}
		return jysb(mapping,form,request,response);
	}
	/**
	 * Jysb.就业上报数据导出
	 * 
	 */
	public ActionForward jysbExpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyForm myform = (JhzyForm) form;
		JhzyModel model = new JhzyModel();
		JhzyService service = new JhzyService();
		BeanUtils.copyProperties(model, myform);
		JyglUnit.formToGBK(model);
		myform.setXl(model.getXl());
		service.serv_jysbExpdata(response,model);
		return mapping.findForward("");
	}
	/**
	 * Jysb.就业招聘次数
	 * 
	 */
	public ActionForward jhzyzphcs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		JhzyForm myForm = (JhzyForm) form;
		JhzyService service = new JhzyService();
		String realTable = "jhzy_zphcsb";
		String xydm = request.getParameter("xydm");
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		String nd = request.getParameter("nd");
		int count = 0;
		ArrayList<HashMap<String, String>> topTr = service.getZphcsTopTr();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if(request.getParameter("go")!=null&&request.getParameter("go").equalsIgnoreCase("go")){
			rs = service.getCphcs(xydm,nd);
			count = rs.size()-1;
		}
		request.setAttribute("realTable", realTable);
		lrh_commen_util commen_util = new lrh_commen_util();
		request.setAttribute("xyList", commen_util.get_xyList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("who", userType);
		request.setAttribute("rsNum", count);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}
	
	/**
	 * Jysb.就业招聘次数
	 * 
	 */
	public ActionForward dellZphcs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JhzyService service = new JhzyService();
		String pks = request.getParameter("pkstring");
		String whichpk = service.ZphcsPlDel(pks, request);
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("delall", "ok");
		} else {
			request.setAttribute("whichpk", whichpk);
			request.setAttribute("delall", whichpk);
		}
		return jhzyzphcs(mapping, form,request,response);
	}
	
	public ActionForward zphcsUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		String pk = DealString.toGBK(request.getParameter("pkValue"));
		JhzyService service = new JhzyService();
		HashMap<String, String> rs = service.getZphcsOne(pk);
		if(rs.get("nd")==null||rs.get("nd").equalsIgnoreCase("")){
			rs.put("nd", Base.currNd);
		}
		if("xy".equalsIgnoreCase(userType)){
			rs.put("xydm", userDep);
		}
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("pk", pk);
		request.setAttribute("rs", rs);
		return mapping.findForward("update");
	}

	public ActionForward zphcsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = DealString.toGBK(request.getParameter("pk"));
		JhzyService service = new JhzyService();
		JhzyModel myModel = new JhzyModel();
		JhzyForm myForm = (JhzyForm) form;
		BeanUtils.copyProperties(myModel, myForm);
		boolean update = service.zphcsUpdate(pk, myModel, request);
		if (update) {
			request.setAttribute("update", "yes");
		} else {
			request.setAttribute("update", "no");
		}
		return mapping.findForward("update");
	}
	
	/** 
	 * Method byssytj
	 * 毕业生生源统计打印
	 * @return ActionForward
	 */
	public ActionForward byssytj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JhzyForm myForm = (JhzyForm) form;
		JhzyService service = new JhzyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? "" 
				: session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? "" 
				: session.getAttribute("userDep").toString();
		String bmmc = session.getAttribute("bmmc") == null ? "" 
				: session.getAttribute("bmmc").toString();
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
			myForm.setXymc(bmmc);
		}else{
			myForm.setXymc(DealString.toGBK(myForm.getXymc()));
		}
		if(myForm.getNd() == null){
			myForm.setNd(Base.currNd);
		}
		if("expdata".equals(myForm.getAct())){
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			service.byssytj_ser(userType,myForm.getXydm(),myForm.getXymc(),myForm.getSyddm(),myForm.getNd(),response.getOutputStream());
			return mapping.findForward("blank");
		}	
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("ssList", service.getSyd_ser());
		return mapping.findForward("byssytj");
	}
	public ActionForward bysdalx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JhzyForm myform = (JhzyForm) form;
		JhzyModel model = new JhzyModel();
		JhzyService service = new JhzyService();
		ArrayList<String[]> rs = new ArrayList<String[]> ();
		List<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		List sydList = service.getDmList("syddmb", new String[]{"syddm","sydmc"});
		request.setAttribute("sydList", sydList);
		String go = request.getParameter("go");
		String xh = "";
		int count = 0;
		if ("stu".equals(userType)) {
			myform.setXh(userName);
			if (StringUtils.isNull(myform.getXm())) {
				myform.setXm(request.getSession().getAttribute("userNameReal").toString());
			}
		}
		if("del".equals(doType)){
			String pks = request.getParameter("pkstring");
			String delres = service.serv_delxmlg(pks);
			if(StringUtils.isNull(delres)){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
			go = "go";
		}

		if("go".equals(go)){
			BeanUtils.copyProperties(model, myform);
			JyglUnit.formToGBK(model);
			JyglUnit.formToGBK(myform);
			myform.setXl(model.getXl());
			if ("stu".equals(userType)) {
				myform.setXm(request.getSession().getAttribute("userNameReal").toString());
			}
			if ("xy".equals(userType)) {
				model.setXymc(request.getSession().getAttribute("bmmc").toString());
			}
			rs = service.serv_xmlgquery(myform,model,userType);
			String[] inputValue = new String[]{"id","xm","zydm","xh","sysheng","yzbm","lxfs"};
			topTr = service.getTitleList(inputValue,"xmlg_bysdalxsqb");
			count = rs != null ? rs.size():0;
		}
		if("add".equals(doType)){
			boolean result = false;
			HashMap<String, String> rs1 = new HashMap<String, String>();
			FormModleCommon.formToGBK(myform);
			BeanUtils.copyProperties(model, myform);
			if ("add".equals(act)) {
				result = service.serv_xmlgAdd(model);
				if (result) {
					request.setAttribute("done", "ok");
				}else {
					request.setAttribute("done", "no");
				}
			}
			if(StringUtils.isNotNull(request.getParameter("pkValue"))){
				model.setId(request.getParameter("pkValue"));
				rs1 = service.serv_xmlgupdate(model);
			}else if ("stu".equals(userType)) {
				xh = userName;
				rs1 = service.serv_getXsInfo(xh);
			}
			request.setAttribute("rs1", rs1);
			FormModleCommon.setNdXnXqList(request);
			FormModleCommon.setNjXyZyBjList(request);
			return mapping.findForward("add");
		}
		if("print".equals(doType)){
			HashMap<String, String> map2 = new HashMap<String, String>();
			String xn = Base.currXn;
			if(StringUtils.isNotNull(request.getParameter("pkValue"))){
				model.setId(request.getParameter("pkValue"));
				map2 = service.serv_xmlgupdate(model);
			}
			String[] dqxn = xn.split("-");
			map2.put("xn", dqxn[1]);
			request.setAttribute("rs", map2);
			FormModleCommon.setNdXnXqList(request);
			FormModleCommon.setNjXyZyBjList(request);
			return mapping.findForward("print");
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", count);
		JyglUnit.setNjXyZyBjList(request,myform);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("realTable" , "xmlg_bysdalxsqb");
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("ssList", service.getSyd_ser());
		return mapping.findForward("success");
	}
	public ActionForward xmlgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JhzyForm myform = (JhzyForm) form;
		JhzyModel model = new JhzyModel();
		JhzyService service = new JhzyService();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		List sydList = service.getDmList("syddmb", new String[]{"syddm","sydmc"});
		request.setAttribute("sydList", sydList);
		String xh = "";
		if ("stu".equals(userType)) {
			myform.setXh(userName);
			myform.setXm(request.getSession().getAttribute("userNameReal").toString());
		}
		if("add".equals(doType)){
			boolean result = false;
			HashMap<String, String> rs1 = new HashMap<String, String>();
			FormModleCommon.formToGBK(myform);
			BeanUtils.copyProperties(model, myform);
			if ("add".equals(act)) {
				result = service.serv_xmlgshAdd(model,userType);
				if (result) {
					request.setAttribute("done", "ok");
				}else {
					request.setAttribute("done", "no");
				}
			}
			if(StringUtils.isNotNull(request.getParameter("pkValue"))){
				model.setId(request.getParameter("pkValue"));
				rs1 = service.serv_xmlgupdate(model);
			}else if ("stu".equals(userType)) {
				xh = userName;
				rs1 = service.serv_getXsInfo(xh);
			}
			request.setAttribute("rs1", rs1);
			FormModleCommon.setNdXnXqList(request);
			FormModleCommon.setNjXyZyBjList(request);
			return mapping.findForward("sh");
		}
		JyglUnit.setNjXyZyBjList(request,myform);
		request.setAttribute("rs1", map);
		request.setAttribute("realTable" , "xmlg_bysdalxsqb");
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("ndList", Base.getXnndList());
		return mapping.findForward("sh");
	}
}
