package xgxt.jxgl;

import java.util.Vector;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import common.Globals;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.jxgl.jxglxxwh.JxglwhService;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

import java.util.ArrayList;

public class JxglOperationAction extends DispatchAction {

	public static ActionForward ArmyCalendarArrange(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String tableName = "jxgl_jxrcxxb";
		String realTable = "jxgl_jxrcxxb";
//		String xxdm      = Base.xxdm;
		String userType = request.getSession().getAttribute("userType").toString();
		if(userType.equalsIgnoreCase("stu")){//学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
//		if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){//浙江金华职业技术学院
//			if("xy".equalsIgnoreCase(userType)){
//				
//			}
//		}
		String nj = request.getParameter("nj");
		String nd = request.getParameter("nd");
		String go = request.getParameter("go");
		String pk = "nd||kssj||jssj";
		List topTr = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		
		//----------2010/6/7 edit by luojw ----------
		JxglForm myForm = (JxglForm) form;
		if(!Base.isNull(nj)){
			myForm.setNj(nj);
		}
		if(!Base.isNull(nd)){
			myForm.setNd(nd);
		}
//		----------end ----------
		if ("".equalsIgnoreCase(nj) || nj == null) {
			nj = "%";
		}
		if ("".equalsIgnoreCase(nd) || nd == null) {
			nd = "%";
		}
		String[] setpara = { nj, nd };
		String[] getpara = { "主键", "行号", "nj", "nd", "kssj", "jssj","rczy" };
		topTr = JxglDataAccessAction.getCalendarInfoTr(getpara, tableName);
		if ("go".equalsIgnoreCase(go)) {
			vector = JxglDataAccessAction.GetCalendarInfo(setpara, getpara, pk);
			request.setAttribute("rs", vector);
			rsNum = (vector == null ? "0" : String.valueOf(vector.size()));
		}
		// setList(request);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// 年级学院专业班级
		FormModleCommon.setNdXnXqList(request);// 年度学年学期
		return mapping.findForward("success");
	}

	public static ActionForward ArmyCalendarManager(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String tableName = "jxgl_jxrcxxb";
		String pk = "nd||kssj||jssj";
		String pkValue = request.getParameter("pkValue");
		boolean del = false;
		if ("del".equalsIgnoreCase(doType)) {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/ArmyCalendarArrange.do", false);
		}
		map = JxglDataAccessAction.getCalendarDetail(doType, pk, pkValue);
		setList(request);
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}

	public static ActionForward SaveArmyCalendar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tableName = "jxgl_jxrcxxb";
		String nj = request.getParameter("nj");
		String nd = request.getParameter("nd");
		String jtrr = DealString.toGBK(request.getParameter("jtrr"));
		StringBuffer kssj = new StringBuffer();
		StringBuffer jssj = new StringBuffer();
		kssj.append(request.getParameter("kssj"));
		kssj.append(" ");
		kssj.append(request.getParameter("kssjH"));
		kssj.append(request.getParameter("kssjM"));
		kssj.append(request.getParameter("kssjS"));
		jssj.append(request.getParameter("jssj"));
		jssj.append(" ");
		jssj.append(request.getParameter("jssjH"));
		jssj.append(request.getParameter("jssjM"));
		jssj.append(request.getParameter("jssjS"));
		String apnr = DealString.toGBK(request.getParameter("apnr"));
		String rczy = DealString.toGBK(request.getParameter("rczy"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		String pk = "nd||kssj||jssj";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String type = request.getParameter("type");

		boolean insert = false;
		boolean del = true;
		if ("modi".equalsIgnoreCase(doType)) {
			String flag = dao.returntag("select * from " + tableName
					+ " where nd||kssj||jssj = ?", new String[] { nd + kssj
					+ jssj });
			if ("notempty".equalsIgnoreCase(flag)
					&& "add".equalsIgnoreCase(type)) {
				request.setAttribute("result", "exist");
				return mapping.findForward("success");
			} else {
				if ("".equalsIgnoreCase(pkValue) || pkValue == null) {
					del = StandardOperation.delete(tableName, "nd||kssj||jssj",
							nd + kssj + jssj, request);
				} else {
					del = StandardOperation.delete(tableName, pk, pkValue,
							request);
				}
			}
		}
		if (del) {
			insert = StandardOperation.insert(tableName, new String[] { "nj",
					"nd", "kssj", "jssj", "apnr", "bz","rczy","jtrr" }, new String[] { nj,
					nd, kssj.toString(), jssj.toString(), apnr, bz,rczy,jtrr }, request);
		}
		if (insert) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	//军训学生信息
	public static ActionForward ArmyStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO
		DAO dao = DAO.getInstance();
		JxglForm jxglform = (JxglForm) form;
		String tableName = "view_jxmdxx";
		String realTable = "jxgl_jxmdb";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xxdm = StandardOperation.getXxdm();
		String xm = DealString.toGBK(jxglform.getXm());
		String ldbh = DealString.toGBK(jxglform.getLdbh());
		jxglform.setXm(xm);
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			tableName = "view_xbmz_jxmd";
			JxglDAO jxdao= new JxglDAO();
			List<HashMap<String, String>> ldList = jxdao.getLdList();
			request.setAttribute("tableName", tableName);
			request.setAttribute("ldList", ldList);
		}
		StringBuffer querry = new StringBuffer();
		querry = getQuerry(jxglform, userDep, userType);
		String pk = "xh||nd";
		String go = request.getParameter("go");
		List topTr = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		String[] colList = { "主键", "行号", "xh", "xm", "mzmc", "xb", "nj", "nd", "xymc", "bjmc","ldmc" };
		topTr = JxglDataAccessAction.getCalendarInfoTr(colList, tableName);
		if ("go".equalsIgnoreCase(go)) {
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				//宁波理工学院
				String sfqgx = dao.getOneRs("select jxmdsfqgx from jxglxtszb", new String[]{}, "jxmdsfqgx");
				sfqgx = sfqgx == null || "".equalsIgnoreCase(sfqgx) ? "0" : sfqgx;
				boolean flag = Integer.parseInt(sfqgx)>0 ? true : false;
				if (flag){
					tableName = "view_bks_jxmdxx";
				}
				vector = JxglDataAccessAction.getArmyStuListOfNblg(pk, querry.toString(), tableName, colList);
			}else{
				if (ldbh!=null&&!"".equals(ldbh)){
					querry.append(" and ldbh='"+ldbh+"'");
				}
				vector = JxglDataAccessAction.getArmyStuList(pk, querry.toString(),
						tableName, colList);
			}
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
			request.setAttribute("rs", vector);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			jxglform.setXydm(userDep);
		}
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			NblgJxglActionUtils commonAction = new NblgJxglActionUtils();//公用ACTION
			NblgJxglCommenBean commenBean = new NblgJxglCommenBean();
			commenBean.setYjdm(jxglform.getYjdm());
			commenBean.setLjdm(jxglform.getLjdm());
			commenBean.setPjdm(jxglform.getPjdm());
			commonAction.appendProperties(request, commenBean);
			return mapping.findForward("success_nblg");
		} else {
//			JxglService service = new JxglService();
			setList(request);
			return mapping.findForward("success");
		}
	}

	public static ActionForward ArmyStuInfoManager(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		JxglwhService service = new JxglwhService();
		String pk = "xh||nd";
		String tableName = "jxgl_jxmdb";
		String realTable = "view_jxmdxx";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			realTable = service.getJxmdTableName();
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			tableName = "view_xbmz_jxmd";
			JxglDAO jxdao= new JxglDAO();
			List<HashMap<String, String>> ldList = jxdao.getLdList();
			request.setAttribute("tableName", tableName);
			request.setAttribute("ldList", ldList);
		}
		
		String[] colList = { "xh", "xb", "xm", "nd", "nj", "xymc", "zymc",
				"bjmc","ldbh","sfbx","jxbx","jxbz","jxcf" };
		map = JxglDataAccessAction.getArmyStuDetail(doType, pk, pkValue,
				colList, xh, realTable);
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			map.put("yjdm", dao.getOneRs("select y.sjdm yjdm from nblg_jxbzdmb y where y.bzdm=(select x.sjdm from nblg_jxbzdmb x where x.bzdm=? and x.nj=?) and y.nj=?", new String[]{map.get("ldbh"),map.get("nd"),map.get("nd")}, "yjdm"));
			map.put("ljdm", dao.getOneRs("select x.sjdm ljdm from nblg_jxbzdmb x where x.bzdm=? and x.nj=?", new String[]{map.get("ldbh"),map.get("nd")}, "ljdm"));
			map.put("pjdm", map.get("ldbh"));
		}
		if ("add".equals(doType)) {
			map.put("stuExists", "yes");
		}
		request.setAttribute("rs", map);

		boolean del = false;
		if ("del".equalsIgnoreCase(doType)) {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/ArmyStuInfo.do", false);
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			NblgJxglActionUtils commonAction = new NblgJxglActionUtils();//公用ACTION
			NblgJxglCommenBean commenBean = new NblgJxglCommenBean();
			commenBean.setYjdm(map.get("yjdm"));
			commenBean.setLjdm(map.get("ljdm"));
			commenBean.setPjdm(map.get("pjdm"));
			commonAction.appendProperties(request, commenBean);
			return mapping.findForward("success_nblg");
		} else {
			setList(request);
			return mapping.findForward("success");
		}
	}

	public static ActionForward SaveArmyStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xh||nd";
		String pkValue = request.getParameter("pkValue");
		String tableName = "jxgl_jxmdb";
		String xh = request.getParameter("xh");
		String nd = request.getParameter("nd");
		String ldbh = request.getParameter("ldbh");
		String sfbx = request.getParameter("sfbx");
		String jxbx = DealString.toGBK(request.getParameter("jxbx"));
		String jxbz = DealString.toGBK(request.getParameter("jxbz"));
		String jxcf = DealString.toGBK(request.getParameter("jxcf"));
		boolean del = true;
		boolean insert = false;
		if ("".equalsIgnoreCase(pkValue)) {
			del = StandardOperation.delete(tableName, "xh||nd", xh + nd,
					request);
		} else {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (del) {
			insert = StandardOperation.insert(tableName, new String[] { "xh",
					"nd", "ldbh", "sfbx", "jxbx", "jxbz", "jxcf" },
					new String[] { xh, nd, ldbh, sfbx, jxbx, jxbz, jxcf },
					request);
		}
		if (insert) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward FugleTeacherInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO
		DAO dao = DAO.getInstance();
		JxglForm jxglform = (JxglForm) form;
		String tableName = "view_ddjsxx";
		String realTable = "jxgl_ddjsxxb";
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		StringBuffer querry = new StringBuffer();
		String xm = DealString.toGBK(jxglform.getXm());
		jxglform.setXm(xm);
		
		querry = getQuerry(jxglform, userDep, userType);
		String pk = "jsdm";
		String go = request.getParameter("go");
		List topTr = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		
		String[] colList = { "主键", "行号", "jsdm", "xm", "xb", "xymc", "lxdh",
				"sfzld", "ddcs" };
		//============ begin 骆嘉伟 2009/4/3 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			topTr = JxglDataAccessAction.getCalendarInfoTr(new String[] { "主键",
					"行号", "jsdm", "xm", "xb", "xymc", "lxdh","bzmc" },
					"view_nblg_lsxx");
		} else {
			topTr = JxglDataAccessAction.getCalendarInfoTr(colList, tableName);
		}		
		if ("go".equalsIgnoreCase(go)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				vector = JxglDataAccessAction.getArmyStuList_Nblg(pk, querry
						.toString(), "view_nblg_lsxx", new String[] { "主键",
						"行号", "jsdm", "xm", "xb", "xymc", "lxdh", "bzmc" },jxglform,request);
			} else {
				vector = JxglDataAccessAction.getArmyStuList(pk, querry
						.toString(), tableName, colList);
			}
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
			request.setAttribute("rs", vector);
		}
		// TODO 分页
		String tmpsql = "select count(1) count from " + tableName + " a"
		+ querry;
		jxglform.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(tmpsql, new String[] {}, "count")));
		
	
		if ("xy".equalsIgnoreCase(userType)) {
			jxglform.setXydm(userDep);
		}
		// setList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		// ============ begin 骆嘉伟 2009/4/1 =================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			NblgJxglActionUtils commonAction = new NblgJxglActionUtils();// 公用ACTION
			NblgJxglCommenBean commenBean = new NblgJxglCommenBean();
			commenBean.setYjdm(jxglform.getYjdm());
			commenBean.setLjdm(jxglform.getLjdm());
			commenBean.setPjdm(jxglform.getPjdm());
			commonAction.appendProperties(request, commenBean);
			return mapping.findForward("success");
		}
		// ============ end 骆嘉伟 2009/4/1 =================	
		return mapping.findForward("success");
	}

	public static ActionForward FugleTeacherManager(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO
		DAO dao = DAO.getInstance();
		String forwardPage = "success";
		String pk = "jsdm";
		String tableName = "jxgl_ddjsxxb";
		String realTable = "view_ddjsxx";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String xxdm = StandardOperation.getXxdm();
		String userType = dao.getUserType(userDep);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = { "jsdm", "xm", "xb", "xydm", "mzdm", "lxdh",
				"sfzld", "ddcs", "bz","jxnd", "zw", "zzdh", "sjhm" };
		map = JxglDataAccessAction.getArmyStuDetail(doType, pk, pkValue,
				colList, realTable);
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//宁波理工
			forwardPage = "nblg_ddjsxx";
		}else{		
			if ("男".equalsIgnoreCase(map.get("xb"))) {
				map.put("xb", "1");
			} else if ("女".equalsIgnoreCase(map.get("xb"))) {
				map.put("xb", "2");
			}
		}
		// 获得编制信息
		JxglJzService service = new JxglJzService();
		List<String> bzList = service.getJsBzList(pkValue);
		String bzmc = "";
		if (bzList.size() != 0) {
			for (int i = 0; i < bzList.size(); i++) {
				if (i != bzList.size() - 1) {
					bzmc = bzmc + bzList.get(i) + ",";
				} else {
					bzmc = bzmc + bzList.get(i);
				}
			}
			map.put("sfzld", bzmc);
		}
		
		boolean del = false;
		if ("xy".equalsIgnoreCase(userType)) {
			map.put("xydm", userDep);
		}
		if ("add".equalsIgnoreCase(doType)) {
			request.setAttribute("tp", "add");
		}
		if ("del".equalsIgnoreCase(doType)) {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/fugleTeacherInfo.do", false);
		}
		setList(request);
		String xb=map.get("xb");
		if("男".equals(xb)){
			request.setAttribute("xbV", "1");
		}else if("女".equals(xb)){
			request.setAttribute("xbV", "2");
		}else{
			request.setAttribute("xbV", "");
		}
			
		request.setAttribute("rs", map);
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward(forwardPage);
	}

	public static ActionForward SaveTeacherInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String pk = "jsdm";
		String pkValue = request.getParameter("pkValue");
		String tableName = "jxgl_ddjsxxb";
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		String jsdm = request.getParameter("jsdm");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = request.getParameter("xb");
		String xydm = request.getParameter("xydm");
		String jxnd = request.getParameter("jxnd");
		String zw = DealString.toGBK(request.getParameter("zw"));
		String zzdh = DealString.toGBK(request.getParameter("zzdh"));
		String sjhm = DealString.toGBK(request.getParameter("sjhm"));
		
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		}
		String mzdm = request.getParameter("mzdm");
		String lxdh = DealString.toGBK(request.getParameter("lxdh"));
		String sfzld = DealString.toGBK(request.getParameter("sfzld"));
		String ddcs = request.getParameter("ddcs");
		String bz = DealString.toGBK(request.getParameter("bz"));
		// ============ begin 骆嘉伟 2009/4/3 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			xb = request.getParameter("xbV");
			mzdm = request.getParameter("mzdmV");
			xydm = request.getParameter("xydmV");
		}
		// ============ end 骆嘉伟 2009/4/3 =================
		boolean del = true;
		boolean insert = false;
		if ("".equalsIgnoreCase(pkValue)) {
			del = StandardOperation.delete(tableName, "jsdm", jsdm, request);
		} else {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (del) {
			insert = StandardOperation.insert(tableName,
					new String[] { "jsdm", "xm", "xb", "xydm", "mzdm", "lxdh",
							"sfzld", "ddcs", "bz", "jxnd", "zw", "zzdh", "sjhm" }, new String[] { jsdm, xm,
							xb, xydm, mzdm, lxdh, sfzld, ddcs, bz ,jxnd, zw, zzdh, sjhm}, request);
		}
		if (insert) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward DrillmasterInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		JxglForm jxglform = (JxglForm) form;
		String tableName = "view_jgxx";
		String realTable = "jxgl_jgxxb";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		StringBuffer querry = new StringBuffer();
		
		String pk = "jgbh";
		String go = request.getParameter("go");
		List topTr = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		
		String[] colList = { "主键", "行号", "jgbh", "xm", "xb", "mzmc", "lxdh",
				"sfzld", "ddcs" };
	
		jxglform.setXm(DealString.toGBK(jxglform.getXm()));
		jxglform.setLdmc(DealString.toGBK(jxglform.getLdmc()));
		jxglform.setLddm(DealString.toGBK(jxglform.getLddm()));
		jxglform.setSfzld(DealString.toGBK(jxglform.getSfzld()));
		querry = getQuerry(jxglform, userDep, userType);
		//============ begin 骆嘉伟 2009/4/1 =================
		String xxdm = StandardOperation.getXxdm();

		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			topTr = JxglDataAccessAction.getCalendarInfoTr(new String[] { "主键",
					"行号", "jgbh", "xm", "xb", "mzmc", "lxdh","jxnd","bzmc" },
					"view_nblg_jxwh");
		} else {
			topTr = JxglDataAccessAction.getCalendarInfoTr(colList, tableName);
		}
		
		if ("go".equalsIgnoreCase(go)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {

				vector = JxglDataAccessAction.getArmyStuList_Nblg(pk, querry
						.toString(), "view_nblg_jxwh", new String[] { "主键", "行号",
						"jgbh", "xm", "xb", "mzmc", "lxdh","jxnd","bzmc" },jxglform,request);
			} else {
				vector = JxglDataAccessAction.getArmyStuList(pk, querry
						.toString(), tableName, colList);
			}
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
			request.setAttribute("rs", vector);
		}
		// ============ end 骆嘉伟 2009/4/1 =================
		setList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		// ============ begin 骆嘉伟 2009/4/1 =================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			NblgJxglActionUtils commonAction = new NblgJxglActionUtils();// 公用ACTION
			NblgJxglCommenBean commenBean = new NblgJxglCommenBean();
			commenBean.setYjdm(jxglform.getYjdm());
			commenBean.setLjdm(jxglform.getLjdm());
			commenBean.setPjdm(jxglform.getPjdm());
			commonAction.appendProperties(request, commenBean);
			return mapping.findForward("success");
		}
		// ============ end 骆嘉伟 2009/4/1 =================
		return mapping.findForward("success");
	}

	public static ActionForward DrillmasterInfoManager(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO
		String pk = "jgbh";
		String tableName = "jxgl_jgxxb";
		String realTable = "view_jgxx";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = { "jgbh", "xm", "xb", "mzdm", "lxdh", "sfzld",
				"ddcs" ,"jxnd", "zw","bz"};
		// ============ begin 骆嘉伟 2009/4/1 =================
		String xxdm = StandardOperation.getXxdm();

		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			map = JxglDataAccessAction.getArmyStuDetail(doType, pk, pkValue,
					new String[] {  "xm","jgbh", "xb", "mzdm", "lxdh", "ddcs",
							"jxnd", "zw","bz"}, "view_nblg_jxwh");
		} else {
			map = JxglDataAccessAction.getArmyStuDetail(doType, pk, pkValue,
					colList, realTable);
		}
		// ============ end 骆嘉伟 2009/4/1 =================
		if ("男".equalsIgnoreCase(map.get("xb"))) {
			map.put("xb", "1");
		} else if ("女".equalsIgnoreCase(map.get("xb"))) {
			map.put("xb", "2");
		}
		// 获得编制信息
		JxglJzService service = new JxglJzService();
		List<String> bzList = service.getBzList(pkValue);
		String bzmc = "";
		if (bzList.size() != 0) {
			for (int i = 0; i < bzList.size(); i++) {
				if (i != bzList.size() - 1) {
					bzmc = bzmc + bzList.get(i) + ",";
				} else {
					bzmc = bzmc + bzList.get(i);
				}
			}
			map.put("sfzld", bzmc);
		}
		// JxglDataAccessAction.getBzInfo();
		//request.setAttribute("bzList", bzList);
		
		request.setAttribute("rs", map);
		boolean del = false;
		if ("del".equalsIgnoreCase(doType)) {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				return new ActionForward("/jgxxInfoOne_nblg.do", false);
			} else {
				return new ActionForward("/drillmasterInfo.do", false);
			}
		}
		if ("add".equalsIgnoreCase(doType)) {
			request.setAttribute("tp", "add");
		}
		setList(request);
		if(request.getParameter("db")=="db"){
			request.setAttribute("db", "db");
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("success");
	}

	public static ActionForward SaveDrillmasterInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO
		String pk = "jgbh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "jxgl_jgxxb";
		String jgbh = request.getParameter("jgbh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = request.getParameter("xb");
		String mzdm = request.getParameter("mzdm");
		String lxdh = request.getParameter("lxdh");
		String sfzld = DealString.toGBK(request.getParameter("sfzld"));
		String ddcs = request.getParameter("ddcs");
		String bz = DealString.toGBK(request.getParameter("bz"));
		String jxnd = DealString.toGBK(request.getParameter("jxnd"));
		String zw = DealString.toGBK(request.getParameter("zw"));

		boolean del = true;
		boolean insert = false;
		if ("".equalsIgnoreCase(pkValue)) {
			del = StandardOperation.delete(tableName, "jgbh", jgbh, request);
		} else {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (del) {
			insert = StandardOperation.insert(tableName, new String[] { "jgbh",
					"xm", "xb", "mzdm", "lxdh", "sfzld", "ddcs", "bz", "jxnd", "zw" },
					new String[] { jgbh, xm, xb, mzdm, lxdh, sfzld, ddcs, bz, jxnd, zw},
					request);
		}
		if (insert) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward XscxgbInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO
		DAO dao = DAO.getInstance();
		JxglForm jxglform = (JxglForm) form;
		String tableName = "view_jxxscxgb";
		String realTable = "jxxscxgbb";
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		StringBuffer querry = new StringBuffer();
		String xm = DealString.toGBK(jxglform.getXm());
		jxglform.setXm(xm);

		querry = getQuerry(jxglform, userDep, userType);
		String pk = "xh";
		String go = request.getParameter("go");
		List topTr = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";

		String[] colList = { "主键", "xh", "xm", "xymc", "bjmc", "jxnd",
				"fzldmc", "zw" };

		topTr = JxglDataAccessAction.getCalendarInfoTr(colList, tableName);

		if ("go".equalsIgnoreCase(go)) {

			vector = JxglDataAccessAction.getArmyStuList_Nblg(pk, querry
					.toString(), tableName, colList, jxglform, request);

			rsNum = vector == null ? "0" : String.valueOf(vector.size());
			request.setAttribute("rs", vector);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			jxglform.setXydm(userDep);
		}
		// setList(request);
		
		tableName = "view_nblg_xsbz";
		JxglJzService service = new JxglJzService();
		
		String xy = jxglform.getXydm();
		String zy = jxglform.getZydm();
		String bj = jxglform.getBjdm();
		String nj = jxglform.getNj();
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			jxglform.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		// 获得带队老师信息
		List<HashMap<String, String>> lsList = service.getLsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		
		setList(request);
		request.setAttribute("lsList", lsList);
		request.setAttribute("jgList", jgList);
		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);

		NblgJxglActionUtils commonAction = new NblgJxglActionUtils();// 公用ACTION
		NblgJxglCommenBean commenBean = new NblgJxglCommenBean();
		commenBean.setYjdm(jxglform.getYjdm());
		commenBean.setLjdm(jxglform.getLjdm());
		commenBean.setPjdm(jxglform.getPjdm());
		commonAction.appendProperties(request, commenBean);
		return mapping.findForward("success");

	}
	
	public static ActionForward XscxgbInfoManager(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO
		String pk = "xh";
		String tableName = "view_jxxscxgb";
		@SuppressWarnings("unused")
		String realTable = "jxxscxgbb";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		JxglJzService service = new JxglJzService();
		
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = { "xh", "xm", "xb", "bjmc", "sjhm", "zzdh",
				"fzlddm" ,"jxnd", "zw","bz"};
		// ============ begin 骆嘉伟 2009/4/1 =================
		String xxdm = StandardOperation.getXxdm();

		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			map = JxglDataAccessAction.getArmyStuDetail(doType, pk, pkValue,
					colList, tableName);
		} 
		// ============ end 骆嘉伟 2009/4/1 =================
		if ("男".equalsIgnoreCase(map.get("xb"))) {
			map.put("xb", "1");
		} else if ("女".equalsIgnoreCase(map.get("xb"))) {
			map.put("xb", "2");
		}
		// 获得编制信息
//		JxglJzService service = new JxglJzService();
//		List<String> bzList = service.getBzList(pkValue);
//		String bzmc = "";
//		if (bzList.size() != 0) {
//			for (int i = 0; i < bzList.size(); i++) {
//				if (i != bzList.size() - 1) {
//					bzmc = bzmc + bzList.get(i) + ",";
//				} else {
//					bzmc = bzmc + bzList.get(i);
//				}
//			}
//			map.put("sfzld", bzmc);
//		}
		// JxglDataAccessAction.getBzInfo();
		//request.setAttribute("bzList", bzList);
		
		request.setAttribute("rs", map);
		boolean del = false;
		if ("del".equalsIgnoreCase(doType)) {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				return new ActionForward("/jgxxInfoOne_nblg.do", false);
			} else {
				return new ActionForward("/drillmasterInfo.do", false);
			}
		}
		if ("add".equalsIgnoreCase(doType)) {
			request.setAttribute("tp", "add");
		}
		setList(request);
		if(request.getParameter("db")=="db"){
			request.setAttribute("db", "db");
		}
		// 获得连队信息
		List<HashMap<String, String>> ldList = service.getLdList();

		request.setAttribute("ldList", ldList);
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("success");
	}
	
	public static ActionForward SaveXscxgbInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "jxxscxgbb";
		String xh = request.getParameter("xh");
//		String xm = DealString.toGBK(request.getParameter("xm"));
//		String xb = request.getParameter("xb");
//		String bjmc = request.getParameter("bjmc");
		String sjhm = request.getParameter("sjhm");
		String zzdh = DealString.toGBK(request.getParameter("zzdh"));
		String fzlddm = request.getParameter("fzlddm");
		String bz = DealString.toGBK(request.getParameter("bz"));
		String jxnd = DealString.toGBK(request.getParameter("jxnd"));
		String zw = DealString.toGBK(request.getParameter("zw"));

		boolean del = true;
		boolean insert = false;
		if ("".equalsIgnoreCase(pkValue)) {
			del = StandardOperation.delete(tableName, "xh", xh, request);
		} else {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (del) {
			insert = StandardOperation.insert(tableName, new String[] { "xh",
					"zzdh", "sjhm", "fzlddm", "bz", "jxnd", "zw" },
					new String[] { xh, zzdh, sjhm, fzlddm, bz, jxnd, zw },
					request);
		}
		if (insert) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}
	
	public static ActionForward CadremanInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		JxglForm jxglform = (JxglForm) form;
		String tableName = "view_jgmbxx";
		String realTable = "jxgl_jgmbxxb";
		String act = request.getParameter("act");
		String tips = "";
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		StringBuffer querry = new StringBuffer();
		querry = getQuerry(jxglform, userDep, userType);
		if ("jgmb".equalsIgnoreCase(act)) {
			tips = "军训管理 - 信息维护 - 基干民兵组成";
			jxglform.setZzlb(act);
			querry.append(" and zzlb='基干民兵'");
		} else if ("ybybd".equalsIgnoreCase(act)) {
			tips = "军训管理 - 信息维护 - 预备役部队组成";
			querry.append(" and zzlb='预备役部队'");
			jxglform.setZzlb(act);
		}
		String pk = "xh||zzlb";
		String go = request.getParameter("go");
		List topTr = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		String[] colList = { "主键", "行号", "xh", "xm", "xb", "xymc", "bjmc",
				"zzzw", "jrzzsj" };
		topTr = JxglDataAccessAction.getCalendarInfoTr(colList, tableName);
		if ("go".equalsIgnoreCase(go)) {
			vector = JxglDataAccessAction.getArmyStuList(pk, querry.toString(),
					tableName, colList);
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
			request.setAttribute("rs", vector);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			jxglform.setXydm(userDep);
		}
		setList(request);
		request.setAttribute("act", act);
		request.setAttribute("tips", tips);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}

	public static ActionForward CadremanInfoManager(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xh||zzlb";
		String tableName = "jxgl_jgmbxxb";
		String realTable = "view_jgmbxx";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String doType = request.getParameter("doType");
		doType=Base.isNull(doType)?"add":doType;
		String zzlb = DealString.toGBK(request.getParameter("zzlb"));
		zzlb = "ybybd".equalsIgnoreCase(zzlb) || "预备役部队".equalsIgnoreCase(zzlb) ? "预备役部队"
				: "基干民兵";
		String xh = request.getParameter("xh");
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = { "xh", "xb", "xm", "nj", "zzzw", "jrzzsj", "zzlb",
				"xymc", "zymc", "bjmc" };
		map = JxglDataAccessAction.getArmyStuDetail(doType, pk, pkValue,
				colList, realTable);
		if (!Base.isNull(xh)) {
			colList = new String[] { "xh", "xb", "xm", "nj", "xymc", "zymc",
					"bjmc" };
			map = JxglDataAccessAction.getStuDetail(xh, colList);
		}
		map.put("zzlb", zzlb);
		request.setAttribute("rs", map);
		boolean del = false;
		if ("del".equalsIgnoreCase(doType)) {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/cadremanInfo.do?go=go", false);
		}
		setList(request);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("success");
	}

	public static ActionForward SaveCadremanInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xh||zzlb";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tableName = "jxgl_jgmbxxb";
		String xh = request.getParameter("xh");
		String jrzzsj = request.getParameter("jrzzsj");
		String zzlb = DealString.toGBK(request.getParameter("zzlb"));
		String zzzw = DealString.toGBK(request.getParameter("zzzw"));
		boolean del = true;
		boolean insert = false;
		if ("".equalsIgnoreCase(pkValue)) {
			del = StandardOperation.delete(tableName, "xh||zzlb", xh + zzlb,
					request);
		} else {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (del) {
			insert = StandardOperation.insert(tableName, new String[] { "xh",
					"jrzzsj", "zzlb", "zzzw" }, new String[] { xh, jrzzsj,
					zzlb, zzzw }, request);
		}
		if (insert) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	// 获取查询条件
	private static StringBuffer getQuerry(JxglForm jxglform, String userDep,
			String userType) {
		String xydm = jxglform.getXydm();
		String zydm = jxglform.getZydm();
		String bjdm = jxglform.getBjdm();
		String nj = jxglform.getNj();
		String nd = jxglform.getNd();
		String xh = jxglform.getXh();
		String xm = jxglform.getXm();
		String ldbh = jxglform.getLdbh();
		String sfzld = jxglform.getSfzld();
		String ldmc = jxglform.getLdmc();
		String yjdm = jxglform.getYjdm();
		String ljdm = jxglform.getLjdm();
		String pjdm = jxglform.getPjdm();
		String jgbh = jxglform.getJgbh();
		
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		//=========begin 骆嘉伟 2009/4/3 =================
		String zdy = jxglform.getZdy();
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String dm = "";
			if (!"".equalsIgnoreCase(jgbh) && jgbh != null) {
				querry.append(" and jgbh='");
				querry.append(jgbh);
				querry.append("'");
			}
			if (!"".equalsIgnoreCase(zdy) && zdy != null) {
				querry.append(" and jsdm='");
				querry.append(zdy);
				querry.append("'");
			}
			if ("".equalsIgnoreCase(xm) || xm == null) {
				querry.append(" and 1=1");
			} else {
				if (Check_Input_Value.check2(xm)) {
					querry.append(" and xm='");
					querry.append(xm);
					querry.append("'");
				}
			}
			if ((bjdm != null && !"".equals(bjdm))
					|| (pjdm != null && !"".equals(pjdm))
					|| (ljdm != null && !"".equals(ljdm))
					|| (yjdm != null && !"".equals(yjdm))) {
				if (!"".equalsIgnoreCase(bjdm) && bjdm != null) {
					querry.append(" and sfzld='");
					querry.append(bjdm);
					querry.append("'");
				} else {
					if (!"".equalsIgnoreCase(pjdm) && pjdm != null) {
						dm = pjdm;
					} else if (!"".equalsIgnoreCase(ljdm) && ljdm != null) {
						dm = ljdm;
					} else if (!"".equalsIgnoreCase(yjdm) && yjdm != null) {
						dm = yjdm;
					}
					String sql = "select * from ( select '"
							+ dm
							+ "' from dual union (select "
							+ "distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b where t.sfzld = b.bzdm and b.sjdm = '"
							+ dm
							+ "') union (select bzdm from nblg_jxbzdmb"
							+ " where sjdm in (select distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b where t.sfzld = b.bzdm"
							+ " and b.sjdm = '"
							+ dm
							+ "')) union (select bzdm from nblg_jxbzdmb where sjdm in (select bzdm"
							+ " from nblg_jxbzdmb where sjdm in (select distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b"
							+ " where t.sfzld = b.bzdm and b.sjdm = '" + dm
							+ "'))))";
					querry.append(" and sfzld in (");
					querry.append(sql);
					querry.append(")");
				}
			}
			return querry;
		}
		//=========end 骆嘉伟 2009/4/3 =================
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		}
		if ("".equalsIgnoreCase(xydm) || xydm == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and xydm='");
			querry.append(xydm);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(zydm) || zydm == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and zydm='");
			querry.append(zydm);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(ldbh) || ldbh == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and ldbh='");
			querry.append(ldbh);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(bjdm) || bjdm == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and bjdm='");
			querry.append(bjdm);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(nj) || nj == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(nd) || nd == null) {
			querry.append(" and 1=1");
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("'");
		}
		if ("".equalsIgnoreCase(xh) || xh == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(xh)) {
				querry.append(" and xh='");
				querry.append(xh);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(xm) || xm == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(xm)) {
				querry.append(" and xm='");
				querry.append(xm);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(sfzld) || sfzld == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(sfzld)) {
				querry.append(" and sfzld='");
				querry.append(sfzld);
				querry.append("'");
			}
		}
		if ("".equalsIgnoreCase(ldmc) || sfzld == null) {
			querry.append(" and 1=1");
		} else {
			if (Check_Input_Value.check2(ldmc)) {
				querry.append(" and ldmc='");
				querry.append(ldmc);
				querry.append("'");
			}
		}
		if (!"".equalsIgnoreCase(yjdm) && yjdm != null) {
			querry.append(" and yjdm='");
			querry.append(yjdm);
			querry.append("'");
		}
		if (!"".equalsIgnoreCase(ljdm) && ljdm != null) {
			querry.append(" and ljdm='");
			querry.append(ljdm);
			querry.append("'");
		}
		if (!"".equalsIgnoreCase(pjdm) && pjdm != null) {
			querry.append(" and pjdm='");
			querry.append(pjdm);
			querry.append("'");
		}
		if (!"".equalsIgnoreCase(jgbh) && jgbh != null) {
			querry.append(" and jgbh='");
			querry.append(jgbh);
			querry.append("'");
		}
		return querry;
	}

	public static ActionForward Army(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglForm jxgl = (JxglForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";
		String tips = "";
		String tableName = request.getParameter("tableName");
		String rsNum = "0";
		String realTable = "";
		String pk = "";
		String dataType = request.getParameter("act");

		String xh = jxgl.getXh();
		String xm = DealString.toGBK(jxgl.getXm());
		String nj = jxgl.getNj();
		String zydm = jxgl.getZydm();
		String xydm = jxgl.getXydm();
		String bjdm = jxgl.getBjdm();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			jxgl.setXydm(xydm);
		}
		jxgl.setXm(xm);
		// String pkValue = request.getParameter("pkValue");
		StringBuffer querry = new StringBuffer("");
		querry.append(" where 1=1");
		querry.append((nj == null || nj.trim().length() < 1) ? "" : " and nj='"
				+ nj.trim() + "'");
		querry.append(xh == null || xh.trim().length() < 1 ? ""
				: " and xh like '%" + xh.trim() + "%'");
		querry.append(xm == null || xm.trim().length() < 1 ? ""
				: " and xm like '%" + xm.trim() + "%'");
		querry.append((xydm == null || xydm.trim().length() < 1) ? ""
				: " and xydm='" + xydm + "'");
		querry.append((zydm == null || zydm.trim().length() < 1) ? ""
				: " and zydm='" + zydm + "'");
		querry.append((bjdm == null || bjdm.trim().length() < 1) ? ""
				: " and bjdm='" + bjdm + "'");

		if (dataType.equalsIgnoreCase("xsjxrcbx")) {// 学生军训日常表现
			realTable = "xsjxbxb";
			pk = "jxrq-xh";
			tips = "军训管理 - 学生军训表现 ";
			tableName = "view_xsbx";
			colList = new String[] { "jxrq", "xh", "xm", "xymc", "zymc",
					"bjmc", "nj", "bxqk" };
			querry.append(" order by jxrq desc");
		}
		if (dataType.equalsIgnoreCase("xsjxbz")) {// 学生军训表彰
			//TODO
			realTable = "xsjxbzb";
			pk = "bzh";
			tips = "军训管理 - 学生军训表彰 ";
			tableName = "view_xsbz";
			colList = new String[] { "bzh", "xh", "xm", "xymc", "zymc", "bjmc",
					"nj", "ldmc", "hjyy","lddm" };
			
			
			// ============ begin 骆嘉伟 2009/4/3 =================
			String xxdm = StandardOperation.getXxdm();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				tableName = "view_nblg_xsbz";
				JxglJzService service = new JxglJzService();
				JxglForm jxglform = (JxglForm) form;
				
				String xy = jxglform.getXydm();
				String zy = jxglform.getZydm();
				String bj = jxglform.getBjdm();
				 nj = jxglform.getNj();
				
				if (userType.equalsIgnoreCase("xy")
						&& (xy == null || xy.equalsIgnoreCase(""))) {
					xy = userDep;
					jxglform.setXydm(xy);
				}
				xy = (xy == null) ? "" : xy;
				zy = (zy == null) ? "" : zy;
				bj = (bj == null) ? "" : bj;
				nj = (nj == null) ? "" : nj;

				String bjKey = xy + "!!" + zy + "!!" + nj;
				// 获得带队老师信息
				List<HashMap<String, String>> lsList = service.getLsList(nj);
				// 获得带队教官信息
				List<HashMap<String, String>> jgList = service.getJgList(nj);
				
				request.setAttribute("lsList", lsList);
				request.setAttribute("jgList", jgList);
				request.setAttribute("xydm", xy);
				request.setAttribute("njList", Base.getNjList());
				request.setAttribute("xyList", Base.getXyList());
				request.setAttribute("zyList", (Base.getZyMap()).get(xy));
				request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
				NblgJxglActionUtils commonAction = new NblgJxglActionUtils();// 公用ACTION
				NblgJxglCommenBean commenBean = new NblgJxglCommenBean();

				commenBean.setYjdm(jxglform.getYjdm());
				commenBean.setLjdm(jxglform.getLjdm());
				commenBean.setPjdm(jxglform.getPjdm());
				commonAction.appendProperties(request, commenBean);
				
				String ljdm = jxgl.getLjdm();
				//String ld = jxgl.getLjdm();
				String yjdm = jxgl.getYjdm();
				String ldmc = "";
				if (ljdm != null && !"".equals(ljdm)) {
					ldmc = dao.getOneRs(
							"select bzmc from nblg_jxbzdmb where bzdm = ?",
							new String[] { yjdm }, "bzmc")
							+ dao
									.getOneRs(
											"select bzmc from nblg_jxbzdmb where bzdm = ?",
											new String[] { ljdm }, "bzmc");
					querry.append((ldmc == null || ldmc.trim().length() < 1) ? ""
							: " and ldmc='" + ldmc + "'");
				} else if (yjdm != null && !"".equals(yjdm)) {
					ldmc = dao.getOneRs(
							"select bzmc from nblg_jxbzdmb where bzdm = ?",
							new String[] { yjdm }, "bzmc");
					querry
							.append((ldmc == null || ldmc.trim().length() < 1) ? ""
									: " and ldmc like '" + ldmc + "%'");
				}
				
				
			}
			// ============ end 骆嘉伟 2009/4/3 =================
			querry.append(" order by bzh desc");
		}
		if (dataType.equalsIgnoreCase("xsjxcf")) {// 学生军训惩罚
			realTable = "xsjxcfb";
			pk = "cfh";
			tips = "军训管理 - 学生军训惩罚 ";
			tableName = "view_xscf";
			colList = new String[] { "cfh", "xh", "xm", "xymc", "zymc", "bjmc",
					"nj", "ldmc", "sclqk", "sclyy" };
			querry.append(" order by cfh desc");
		}

		sql = " select v.* from " + tableName + " v" + querry.toString();
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			if (!dataType.equalsIgnoreCase("xsjxbz")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}else{
				sql = "select * from (select a.xh,a.xm,a.xb,a.xymc,a.xydm,a.zymc,a.zydm,a.bjmc,a.bjdm,a.nj,"
						+ " (select (select getJxglbzss(bzdm, nj) sT from dual) || bzmc bzmc from jxbzdmb where bzdm = b.lddm) ldmc,"
						+ " b.jxhjlbdm,(SELECT DISTINCT jxmc FROM jxhjlbb c WHERE b.jxhjlbdm = c.jxdm) jxhjlbmc,"
						+ " b.bzh,b.lddm,b.hjyy from xsjxbzb b, view_jxmdxx a where a.xh = b.xh)";
				sql+=querry.toString();
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		setList(request);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("act", dataType);
		return mapping.findForward("success");
	}

	public static ActionForward ShowArmy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		JxglDAO jxglDao = new JxglDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = request.getSession().getAttribute("userOnLine").toString();
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String doType = request.getParameter("doType");
		String sql = "select * from " + tableName + " where 1=2";
		if (doType!=null&&!doType.equalsIgnoreCase("") && doType.equalsIgnoreCase("modi")) {
			String pk = DealString.toGBK(request.getParameter("pk"));
			String pkValue = DealString.toGBK(request.getParameter("rpkValue"));
			String[] rpk = pk.split("-");
			String[] rpkValue = pkValue.split("!!");
			if ("view_xsbx".equalsIgnoreCase(tableName)) {
				sql = "select * from " + tableName + " where " + rpk[0] + "='"
						+ rpkValue[0] + "' and " + rpk[1] + "='" + rpkValue[1]
						+ "' and 1=1";
			} else {
				sql = "select * from " + tableName + " where " + pk + "="
						+ pkValue + " and 1=1";
			}
			request.setAttribute("pk", pk);
			request.setAttribute("pkValue", pkValue);
		}else {
			String xh = DealString.toGBK(request.getParameter("xh"));
			sql = "select xh,ldmc,xymc,zymc, bjmc, xm, nj, xb from view_jxmdxx where xh = '"+xh+"'";
		}
		String[] colList = new String[] {};
		if (realTable.equalsIgnoreCase("xsjxbxb")) {
			colList = new String[] { "jxrq", "xh", "xymc", "zymc", "bjmc",
					"bxqk", "xm", "nj", "xb" };
		}
		if (realTable.equalsIgnoreCase("xsjxbzb")) {
			colList = new String[] { "bzh", "xh", "ldmc", "hjyy", "xymc",
					"zymc", "bjmc", "xm", "nj", "xb", "jxhjlbdm","lddm" };
			// ============ begin 骆嘉伟 2009/4/3 =================
			String xxdm = StandardOperation.getXxdm();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				colList = new String[] { "bzh", "xh", "ldmc", "hjyy", "xymc",
						"zymc", "bjmc", "xm", "nj", "xb", "jxhjlbdm", "zdy",
						"jgmc" };
			}
			List xnList = dao.getXnndList();
			xnList.remove(0);
			request.setAttribute("xnList", xnList);
			// ============ end 骆嘉伟 2009/4/3 =================
		}
		if (realTable.equalsIgnoreCase("xsjxcfb")) {
			colList = new String[] { "cfh", "xh", "ldmc", "sclqk", "sclyy",
					"xymc", "zymc", "bjmc", "xm", "nj", "xb" };
		}
		if(doType==null) {
				colList = new String[] { "xh", "ldmc","xymc",
						"zymc", "bjmc", "xm", "nj", "xb" };
		}
		String[] outValues = dao.getOneRs(sql, new String[] {}, colList);
		if (outValues != null && outValues.length > 0) {
			for (int i = 0; i < outValues.length; i++) {
				map.put(colList[i], outValues[i]);
			}
		}
		if(doType==null) {
			if(map.size()==0) {
				request.setAttribute("sfjxs", "no");
			}
			doType="add";
		}
		if(!Base.isNull(map.get("lddm"))){
			String jxnd=dao.getOneRs("select nj from jxbzdmb where bzdm=?", new String[]{map.get("lddm")}, "nj");
			map.put("jxnd", jxnd);
		}
		
		request.setAttribute("rs", map);
		request.setAttribute("realTable", realTable);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("bzList", jxglDao.getLdList2(Base.currNd));
		request.setAttribute("jxhjlbList", jxglDao.getJxhjlbList());
		if (realTable.equalsIgnoreCase("xsjxbxb")) {
			return new ActionForward("/jxgl/xsjxbx.jsp", false);
		} else if (realTable.equalsIgnoreCase("xsjxbzb")) {
			return new ActionForward("/jxgl/xsjxbz.jsp", false);
		} else if (realTable.equalsIgnoreCase("xsjxcfb")) {
			return new ActionForward("/jxgl/xsjxcf.jsp", false);
		}
		return mapping.findForward("success");
	}

	public static ActionForward ModifyArmy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglForm jxgl = (JxglForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String act = request.getParameter("act");
		String pk = DealString.toGBK(request.getParameter("pk"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String userDep = session.getAttribute("userDep").toString();
		String from = request.getParameter("from");
		String userType = dao.getUserType(userDep);
		boolean result = false;
		String sql = "";
		String xh = jxgl.getXh().trim();
		if ("save".equalsIgnoreCase(doType)) {
			if ("xsjxbxb".equalsIgnoreCase(realTable)) {
				String jxrq = jxgl.getJxrq();
				String bxqk = DealString.toGBK(jxgl.getBxqk());
				String[] rpk = pk.split("-");
				String[] rpkValue = pkValue.split("!!");
				request.setAttribute("tips", "军训管理-学生军训表现");
				if (pkValue == null || "".equalsIgnoreCase(pkValue)) {
					result = StandardOperation.delete("xsjxbxb", new String[] {
							"jxrq", "xh" }, new String[] { jxrq, xh }, request);
				} else {
					result = StandardOperation.delete("xsjxbxb", new String[] {
							rpk[0], rpk[1] }, new String[] { rpkValue[0],
							rpkValue[1] }, request);
				}
				if (result) {
					sql = "insert into xsjxbxb(id,jxrq,xh,bxqk) values(seq_jxbx.nextval,?,?,?)";
					dao.runUpdate(sql, new String[] { jxrq, xh, bxqk });
					dao.writeLog(sql, new String[] { jxrq, xh, bxqk }, null,
							"增加记录：xsjxbxb:学生军训表现表", request);
				}

			}
			if ("xsjxbzb".equalsIgnoreCase(realTable)) {
				String lddm = DealString.toGBK(jxgl.getLddm());
				String hjyy = DealString.toGBK(jxgl.getHjyy());
				String jxhjlbdm = DealString.toGBK(jxgl.getJxhjlbdm());
				String bzh = "";
				bzh = request.getParameter("bzh");
				request.setAttribute("tips", "军训管理-学生军训表彰");
				result = StandardOperation.delete("xsjxbzb",
						new String[] { "bzh" }, new String[] { bzh }, request);
				if (result) {
					sql = "insert into xsjxbzb(bzh,xh,lddm,hjyy,jxhjlbdm) values(seq_jxbz.nextval,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xh, lddm, hjyy, jxhjlbdm });
					dao.writeLog(sql, new String[] { xh, lddm, hjyy, jxhjlbdm }, null,
							"增加记录：xsjxbzb:学生军训表彰表", request);
				}
			}
			if ("xsjxcfb".equalsIgnoreCase(realTable)) {
				String lddm = DealString.toGBK(jxgl.getLddm());
				String sclqk = DealString.toGBK(jxgl.getSclqk());
				String sclyy = DealString.toGBK(jxgl.getSclyy());
				String cfh = "";
				cfh = request.getParameter("cfh");
				request.setAttribute("tips", "军训管理-学生军训惩罚");
				result = StandardOperation.delete("xsjxcfb",
						new String[] { "cfh" }, new String[] { cfh }, request);
				if (result) {
					sql = "insert into xsjxcfb(cfh,xh,lddm,sclqk,sclyy) values(seq_jxcf.nextval,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xh, lddm, sclqk, sclyy });
					dao.writeLog(sql, new String[] { xh, lddm, sclqk, sclyy },
							null, "增加记录：xsjxcfb:学生军训惩罚表", request);
				}
			}
		}
		if ("del".equalsIgnoreCase(doType)) {
			if ("xsjxbxb".equalsIgnoreCase(realTable)) {
				String jxrq = request.getParameter("pk");
				xh = request.getParameter("pk2");
				result = StandardOperation.delete("xsjxbxb", new String[] {
						"jxrq", "xh" }, new String[] { jxrq, xh }, request);
				sql = "delete from xsjxbxb where xh is null";
				dao.runUpdate(sql, new String[] {});
				dao.writeLog(sql, null, null, "删除记录：删除xsjxbxb:学生军训表现表中学号为空的数据",
						request);
			}
			if ("xsjxbzb".equalsIgnoreCase(realTable)) {
				String bzh = request.getParameter("pk");
				result = StandardOperation.delete("xsjxbzb",
						new String[] { "bzh" }, new String[] { bzh }, request);
				sql = "delete from xsjxbzb where xh is null";
				dao.runUpdate(sql, new String[] {});
				dao.writeLog(sql, null, null, "删除记录：删除xsjxbzb:学生军训表彰表中学号为空的数据",
						request);
			}
			if ("xsjxcfb".equalsIgnoreCase(realTable)) {
				String cfh = request.getParameter("pk");
				result = StandardOperation.delete("xsjxcfb",
						new String[] { "cfh" }, new String[] { cfh }, request);
				sql = "delete from xsjxcfb where xh is null";
				dao.runUpdate(sql, new String[] {});
				dao.writeLog(sql, null, null, "删除记录：删除xsjxcfb:学生军训处罚表中学号为空的数据",
						request);
			}
			if (result) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
			return new ActionForward("/Army.do?act=" + from + "&go=go", false);
		}
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("act", act);
		request.setAttribute("pk", pk);
		setList(request);
		return mapping.findForward("success");
	}

	public static ActionForward ArmyIntoAchievement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		JxglForm myform = (JxglForm) form;
		String go = request.getParameter("go");
		String tableName = "view_jxcjxx";
		String realTable = "jxgl_cjb";
		String sql = "";
		List rs = null;
		String xydm = myform.getXydm();
		String zydm = myform.getZydm();
		String bjdm = myform.getBjdm();
		String nj = myform.getNj();
		String nd = myform.getNd();
		String xm = DealString.toGBK(myform.getXm());
		String xh = DealString.toGBK(myform.getXh());
		String xb = DealString.toGBK(myform.getXb());
		String ldbh = DealString.toGBK(myform.getLdbh());
		String xxdm = StandardOperation.getXxdm();
		
//		String userType = request.getSession().getAttribute("userType").toString();
    	String userName = request.getSession().getAttribute("userName").toString();
//    	String userDep  =  request.getSession().getAttribute("userDep").toString();
    	
    	//	================= begin 骆嘉伟 2009/6/13 ==================
    	if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			HttpSession session = request.getSession();
			String isFdy = session.getAttribute("isFdy").toString();// 辅导员
			if ("true".equalsIgnoreCase(isFdy)) {
				String[] outputSQL = new String[] { "bjdm", "bjmc" };
				sql = "select b.bjdm, b.bjmc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm"
						+ " and a.zgh = ? order by b.bjdm";
				List bjList = dao.getList(sql, new String[] { userName },
						outputSQL);
				request.setAttribute("bjList", bjList);
			}
		}
    	// 	================= end 骆嘉伟 2009/6/13 ==================
    	
		if ("".equalsIgnoreCase(xydm) || xydm == null) {
			xydm = "%";
		}
		if ("".equalsIgnoreCase(zydm) || zydm == null) {
			zydm = "%";
		}
		if ("".equalsIgnoreCase(bjdm) || bjdm == null) {
			bjdm = "%";
		}
		if ("".equalsIgnoreCase(nj) || nj == null) {
			nj = "%";
		}
		if ("".equalsIgnoreCase(nd) || nd == null) {
			nd = "%";
		}
		if ("".equalsIgnoreCase(xm) || xm == null) {
			xm = "%";
		}
		if ("".equalsIgnoreCase(xh) || xh == null) {
			xh = "%";
		}
		if ("".equalsIgnoreCase(xb) || xb == null) {
			xb = "%";
		}

		int rsNum = 0;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			request.setAttribute("cjlrType2", "cjlrType2");
			String qszz = DealString.toGBK(request.getParameter("qszz"));
			String jszz = DealString.toGBK(request.getParameter("jszz"));
			request.setAttribute("cjlrType2", "cjlrType2");
			sql = "select xh xhArray,xm xmArray,nd ndArray,xb,nj,bjmc,cj cjArray,xlcj xlcjArray,kscj kscjArray from "
					+ tableName;
			//
			if (go != null && go.equalsIgnoreCase("go")) {
				String [] colList = new String[] { "xydm", "zydm", "bjdm", "nj", "nd","cj"};
				String[] inputList = getInputList(request, colList);
				ArrayList rsTmp = getTj(colList, inputList);
				String query = (String)rsTmp.get(0);
				String [] setList = (String[])rsTmp.get(1);
				if(!qszz.equalsIgnoreCase("")&&!jszz.equalsIgnoreCase("")) {
					query += " and cj > "+qszz+" and cj <"+jszz;
				}
				// ================= begin 骆嘉伟 2009/3/30 ==================
				rs = dao.getList(sql + query, setList, new String[] {
						"xhArray", "xmArray", "ndArray", "xb", "xh", "nj","xm","xydm","zydm","nd",
						"bjmc", "cjArray", "xlcjArray", "kscjArray" });
				// ================= end 骆嘉伟 2009/3/30 ========================
				rsNum = (rs != null ? rs.size() : 0);
			}
		}
		//================= begin 骆嘉伟 2009/3/30 ========================
		else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			request.setAttribute("jxgl_zjcm", "jxgl_zjcm");
			sql = "select xh xhArray,xm xmArray,nd ndArray,xb,nj,bjmc,cj cjArray,xlcj xlcjArray,kscj kscjArray from "
					+ tableName;
			//
			if (go != null && go.equalsIgnoreCase("go")) {
				String[] colList = new String[] { "xydm", "zydm", "bjdm", "nj",
						"nd", "cj", "xb", "xh", "nj", "xm" };
				String[] inputList = getInputList(request, colList);
				ArrayList rsTmp = getTj(colList, inputList);
				String query = (String)rsTmp.get(0);
				String [] setList = (String[])rsTmp.get(1);
				
				rs = dao.getList(sql+query,setList,
						new String[] { "xhArray", "xmArray", "ndArray", "xb",
								"nj", "bjmc", "cjArray", "xlcjArray",
								"kscjArray" });
				rsNum = (rs != null ? rs.size() : 0);
				myform.setXm(DealString.toGBK(myform.getXm()));
			}
		}
		//================= end 骆嘉伟 2009/3/30 ========================
		else {
			sql = "select xh xhArray,xm xmArray,nd ndArray,xb,nj,bjmc,cj cjArray from "
					+ tableName
					+ " where xydm like ? and "
					+ "zydm like ? and bjdm like ? and nj like ? and nd like ? and xm like ? and xh like ? and xb like ? ";
			if(null!=ldbh&&!ldbh.equalsIgnoreCase("")) {
				sql +=" and ldbh = '";
				sql +=ldbh;
				sql +="'";
			}
			if(go != null && go.equalsIgnoreCase("go")) {
				rs = dao.getList(sql,
						new String[] { xydm, zydm, bjdm, nj, nd,xm,xh,xb },
						new String[] { "xhArray", "xmArray", "ndArray", "xb",
								"nj", "bjmc", "cjArray" });
				rsNum = (rs != null ? rs.size() : 0);
			}
		}
		setList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("realTable", realTable); // 表
		request.setAttribute("tableName", tableName); // 视图
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	public static ActionForward SaveArmyAchievement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		JxglForm myform = (JxglForm) form;
		String tableName = "jxgl_cjb";
		String sql = "";
		String[] xhArray = myform.getXhArray();
		String[] ndArray = myform.getNdArray();
		String[] cjArray = myform.getCjArray();
		String lrr = request.getSession().getAttribute("userName").toString();
		String[] xlcjArray = myform.getXlcjArray();
		String[] kscjArray = myform.getKscjArray();
		String xxdm = StandardOperation.getXxdm();
		for (int i = 0; i < xhArray.length; i++) {
			sql = "select * from " + tableName + " where xh=? and nd=?";
			String tag = dao.returntag(sql, new String[] { xhArray[i],
					ndArray[i] });
			if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				if ("notempty".equalsIgnoreCase(tag)) {
					StandardOperation.update(tableName, new String[] { "cj",
							"xlcj", "kscj" }, new String[] { cjArray[i],
							xlcjArray[i], kscjArray[i] }, "xh||nd", xhArray[i]
							+ ndArray[i], request);
				} else {
					StandardOperation.insert(tableName, new String[] { "xh",
							"nd", "cj", "lrr", "xlcj", "kscj" }, new String[] {
							xhArray[i], ndArray[i], cjArray[i], lrr,
							xlcjArray[i], kscjArray[i] }, request);
				}
			}
			//================= end 骆嘉伟 2009/3/30 ========================
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {
				if ("notempty".equalsIgnoreCase(tag)) {
					StandardOperation.update(tableName, new String[] { "cj",
							"xlcj", "kscj" }, new String[] { cjArray[i],
							xlcjArray[i], kscjArray[i] }, "xh||nd", xhArray[i]
							+ ndArray[i], request);
				} else {
					StandardOperation.insert(tableName, new String[] { "xh",
							"nd", "cj", "lrr", "xlcj", "kscj" }, new String[] {
							xhArray[i], ndArray[i], cjArray[i], lrr,
							xlcjArray[i], kscjArray[i] }, request);
				}
			}
			//================= end 骆嘉伟 2009/3/30 ========================
			else {
				if ("notempty".equalsIgnoreCase(tag)) {
					StandardOperation.update(tableName, new String[] { "cj" },
							new String[] { DealString.toGBK(cjArray[i]) }, "xh||nd", xhArray[i]
									+ ndArray[i], request);
				} else {
					StandardOperation.insert(tableName, new String[] { "xh",
							"nd", "cj", "lrr" }, new String[] { xhArray[i],
							ndArray[i], DealString.toGBK(cjArray[i]), lrr }, request);
				}
			}
		}
		request.setAttribute("result", "ok");
		return mapping.findForward("success");
	}

	public static ActionForward trainConf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";// sql语句
		String xn = "";
		String nd = "";
		String xq = "";
		String nj = "";
		String kssj = "";
		String jssj = "";
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("save")) {
			xn = request.getParameter("xn");
			nd = request.getParameter("nd");
			xq = request.getParameter("xq");
			nj = request.getParameter("nj");
			kssj = request.getParameter("kssqsj");
			jssj = request.getParameter("jssqsj");
			// sql = "delete from xsfzsqkfsjb where xn=? and nd=? and xq=?";
			boolean ok = StandardOperation.update("xsfzsqkfsjb", new String[] {
					"xn", "nd", "xq", "nj", "kssqsj", "jssqsj" }, new String[] {
					xn, nd, xq, nj, kssj, jssj }, "1", "1", request);
			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		sql = "select xn,nd,xq,nj, strtodatetime(substr(kssqsj,1,8)) kssqsj1,"
				+ "substr(kssqsj,9,2) kssqsj2,"
				+ "substr(kssqsj,11,2) kssqsj3,"
				+ "substr(kssqsj,13,2) kssqsj4,"
				+ "strtodatetime(substr(jssqsj,1,8)) jssqsj1,"
				+ "substr(jssqsj,9,2) jssqsj2,"
				+ "substr(jssqsj,11,2) jssqsj3,"
				+ "substr(jssqsj,13,2) jssqsj4 from xsfzsqkfsjb "
				+ "where rownum=1";
		String[] rst = { "xn", "nd", "xq", "nj", "kssqsj1", "kssqsj2",
				"kssqsj3", "kssqsj4", "jssqsj1", "jssqsj2", "jssqsj3",
				"jssqsj4" };
		String[] sj = dao.getOneRs(sql, new String[] {}, rst);
		if (sj == null) {
			sj = new String[rst.length];
			for (int i = 0; i < sj.length; i++) {
				sj[i] = "";
			}
		}
		for (int i = 0; i < sj.length; i++) {
			request.setAttribute(rst[i], sj[i]);
		}
		request.setAttribute("xnndList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("njList", dao.getNjList());
		return mapping.findForward("success");
	}

	public static ActionForward TrainColthApplyInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();

		ActionForward myActFwd = null;
		CommanForm applyForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String sql = "";
		String[] inputSQLGroup = {};
		sql = "select distinct zxfh from zxfhxb";
		String[] outputSQLGroup = { "zxfh" };
		List zxfhList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		request.setAttribute("zxfhList", zxfhList);

		sql = "select distinct zxfx from zxfhxb";
		outputSQLGroup = new String[] { "zxfx" };
		List zxfxList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		request.setAttribute("zxfxList", zxfxList);

		sql = "select cm,cm||'码--'||dycd||'cm' dycd from jfxcmb";
		outputSQLGroup = new String[] { "cm", "dycd" };
		List jfxList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		request.setAttribute("jfxList", jfxList);

		sql = "select xh,twhjdx from zxmtwb";
		outputSQLGroup = new String[] { "xh", "twhjdx" };
		List zxmList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		request.setAttribute("zxmList", zxmList);

		sql = "select xh,cc from whsccb";
		outputSQLGroup = new String[] { "xh", "cc" };
		List whsList = dao.getList(sql, inputSQLGroup, outputSQLGroup);
		request.setAttribute("whsList", whsList);

		HashMap<String, String> map = new HashMap<String, String>();

		String xh = applyForm.getXh();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select * from view_xsjbxx where xh=?";
		String[] colList = dao
				.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		sql = "select * from xsfzsqkfsjb where to_char(sysdate,'yyyymmddhh24miss')>kssqsj and to_char(sysdate,'yyyymmddhh24miss')<jssqsj";
		String falg = dao.returntag(sql, new String[] {});
		if ("empty".equalsIgnoreCase(falg)) {
			request.setAttribute("result", "view");
			return mapping.findForward("success");
		}
		map.put("xh", xh);
		request.setAttribute("rs", map);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		myActFwd = mapping.findForward("success");
		return myActFwd;
	}

	public static ActionForward TrainColthApplyInsert(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xh = request.getParameter("xh");
		String xq = request.getParameter("xq");
		String zxfh = request.getParameter("zxfh");
		String zxfx = request.getParameter("zxfx");
		String jfxcm = DealString.toGBK(request.getParameter("jfxcm"));
		String zxmxh = request.getParameter("zxmxh");
		String whsxh = request.getParameter("whsxh");
		boolean del = false;
		boolean tmp = false;

		del = StandardOperation.delete("xsfzsqb", new String[] { "xn", "xq",
				"xh" }, new String[] { xn, xq, xh }, request);
		if (del) {
			tmp = StandardOperation.insert("xsfzsqb",
					new String[] { "xn", "nd", "xh", "xq", "zxfh", "zxfx",
							"jfxcm", "zxmxh", "whsxh" }, new String[] { xn, nd,
							xh, xq, zxfh, zxfx, jfxcm, zxmxh, whsxh }, request);
		}
		if (tmp) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward trainWeave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String xxbz = request.getParameter("xxbz");
		String xxbzfx = request.getParameter("xxbzfx");
		String mappingItems = request.getParameter("mappingItems");
		String delete = request.getParameter("delete");
		String tableName = "";
		String nj = "";
		String query = "";
		String query1 = "";
		String bz = "";
		boolean doFlag = false;
		sql = "select nj from xsfzsqkfsjb where rownum=1";
		String[] tmp = dao
				.getOneRs(sql, new String[] {}, new String[] { "nj" });
		if (tmp != null) {
			nj = tmp[0];
		}
		if ((xxbz != null) && xxbz.equals("xy")) {
			sql = "select distinct xydm column_name,xymc comments from view_njxyzybj order by xydm";
			List excelItemList = dao.getList(sql, new String[] {},
					new String[] { "column_name", "comments" });
			sql = "select b.dm||'!!SplitSignTwo!!'||b.bz column_name,a.xymc||'-----'||b.bz comments from (select distinct xydm,xymc from view_njxyzybj) a, xyjxbzb b where a.xydm=b.dm and b.nj=? order by b.dm";
			List mappingList = dao.getList(sql, new String[] { nj },
					new String[] { "column_name", "comments" });
			request.setAttribute("mappingList", mappingList);
			request.setAttribute("excelItemList", excelItemList);
			tableName = "xyjxbzb";
		} else if ((xxbz != null) && xxbz.equals("zy")) {
			sql = "select distinct xydm column_name,xymc comments from view_njxyzybj where xydm in (select dm from xyjxbzb where nj=?) order by xydm";
			List xxbzfxList = dao.getList(sql, new String[] { nj },
					new String[] { "column_name", "comments" });
			request.setAttribute("xxbzfxList", xxbzfxList);
			if ((xxbzfx != null) && !xxbzfx.equals("")) {
				query += " xydm = '" + xxbzfx + "' ";
				query1 += " dm = '" + xxbzfx + "' ";
			} else {
				query += " 1=1 ";
				query1 += " 1=2 ";
			}
			sql = "select bz from xyjxbzb where nj=? and " + query1;
			tmp = dao.getOneRs(sql, new String[] { nj }, new String[] { "bz" });
			if (tmp != null) {
				bz = tmp[0];
			} else {
				bz = "";
			}
			sql = "select distinct zydm column_name,zymc comments from view_njxyzybj where "
					+ query + " order by zydm";
			List excelItemList = dao.getList(sql, new String[] {},
					new String[] { "column_name", "comments" });
			sql = "select b.dm||'!!SplitSignTwo!!'||b.bz column_name,a.zymc||'-----'||b.bz comments from (select distinct zydm,zymc from view_njxyzybj) a, zyjxbzb b where a.zydm=b.dm and b.nj=? order by b.dm";
			List mappingList = dao.getList(sql, new String[] { nj },
					new String[] { "column_name", "comments" });
			request.setAttribute("mappingList", mappingList);
			request.setAttribute("excelItemList", excelItemList);
			tableName = "zyjxbzb";
		} else if ((xxbz != null) && xxbz.equals("bj")) {
			sql = "select distinct zydm column_name,zymc comments from view_njxyzybj where zydm in (select dm from zyjxbzb where nj=?) order by zydm";
			List xxbzfxList = dao.getList(sql, new String[] { nj },
					new String[] { "column_name", "comments" });
			int size = xxbzfxList.size();
			if (size == 0) {
				sql = "select distinct xydm column_name,xymc comments from view_njxyzybj where xydm in (select dm from xyjxbzb where nj=?) order by xydm";
				xxbzfxList = dao.getList(sql, new String[] { nj },
						new String[] { "column_name", "comments" });
			}
			request.setAttribute("xxbzfxList", xxbzfxList);

			if (size == 0) {
				if ((xxbzfx != null) && !xxbzfx.equals("")) {
					query += " xydm = '" + xxbzfx + "' ";
					query1 += " dm = '" + xxbzfx + "' ";
				} else {
					query += " 1=1 ";
					query1 += " 1=2 ";
				}
				sql = "select bz from xyjxbzb where nj=? and " + query1;
			} else {
				if ((xxbzfx != null) && !xxbzfx.equals("")) {
					query += " zydm = '" + xxbzfx + "' ";
					query1 += " a.dm = '" + xxbzfx + "' ";
				} else {
					query += " 1=1 ";
					query1 += " 1=2 ";
				}
				sql = "select distinct c.bz||a.bz bz from zyjxbzb a ,view_njxyzybj b ,xyjxbzb c where a.dm=b.zydm and b.xydm=c.dm and a.nj=? and "
						+ query1;
			}
			tmp = dao.getOneRs(sql, new String[] { nj }, new String[] { "bz" });
			if (tmp != null) {
				bz = tmp[0];
			} else {
				bz = "";
			}
			sql = "select distinct bjdm column_name,bjmc comments from view_njxyzybj where nj=? and "
					+ query + " order by bjdm";
			List excelItemList = dao.getList(sql, new String[] { nj },
					new String[] { "column_name", "comments" });
			sql = "select b.dm||'!!SplitSignTwo!!'||b.bz column_name,a.bjmc||'-----'||b.bz comments from (select distinct bjdm,bjmc from view_njxyzybj where nj=?) a, bjjxbzb b where a.bjdm=b.dm order by b.dm";
			List mappingList = dao.getList(sql, new String[] { nj },
					new String[] { "column_name", "comments" });
			request.setAttribute("mappingList", mappingList);
			request.setAttribute("excelItemList", excelItemList);
			tableName = "bjjxbzb";
		}
		if ((mappingItems != null) && !mappingItems.equals("")) {
			String jxbzfx = request.getParameter("jxbzfx");
			String mappingItems1[] = mappingItems.split("!!SplitSignOne!!");
			int num = mappingItems1.length;
			String mappingItems2[][] = new String[num][];
			boolean del = false;
			if (tableName.equalsIgnoreCase("xyjxbzb")
					|| tableName.equalsIgnoreCase("zyjxbzb")) {
				sql = "delete " + tableName + " where nj=?";
				del = dao.runUpdate(sql, new String[] { nj });
			} else if (tableName.equalsIgnoreCase("bjjxbzb")) {
				sql = "delete from bjjxbzb where dm in (select distinct bjdm from view_njxyzybj where nj=?)";
				del = dao.runUpdate(sql, new String[] { nj });
			}
			for (int i = 1; i < num; i++) {
				mappingItems2[i] = mappingItems1[i].split("!!SplitSignTwo!!");
				if (tableName.equals("xyjxbzb") || tableName.equals("zyjxbzb")) {
					if (del) {
						sql = "insert into " + tableName
								+ "(dm,bz,nj) values(?,?,?)";
						doFlag = dao.runUpdate(sql, new String[] {
								mappingItems2[i][1],
								DealString.toGBK(mappingItems2[i][2]), nj });
					}
				} else if (tableName.equals("bjjxbzb")) {
					if (del) {
						sql = "insert into " + tableName
								+ "(dm,bz) values(?,?)";
						doFlag = dao.runUpdate(sql,
								new String[] {
										mappingItems2[i][1],
										DealString.toGBK(jxbzfx
												+ mappingItems2[i][2]) });
					}
				}
			}
			if (doFlag == true) {
				request.setAttribute("doFlag", "ok");
			} else {
				request.setAttribute("doFlag", "no");
			}
		}
		if ((delete != null) && delete.equals("do")) {
			sql = "delete from xyjxbzb where nj=?";
			doFlag = dao.runUpdate(sql, new String[] { nj });
			sql = "delete from zyjxbzb where nj=?";
			doFlag = doFlag && dao.runUpdate(sql, new String[] { nj });
			sql = "delete from bjjxbzb where dm in (select distinct bjdm from view_njxyzybj where nj=?)";
			doFlag = doFlag && dao.runUpdate(sql, new String[] { nj });
			if (doFlag == true) {
				request.setAttribute("doFlag", "ok");
			} else {
				request.setAttribute("doFlag", "no");
			}
		}
		request.setAttribute("bz", bz);
		return mapping.findForward("success");
	}

	/**
	 * @param request
	 * @作用 设置基础列表
	 */
	private static void setList(HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		JxglDAO jxglDao = new JxglDAO();
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		}
		request.setAttribute("bzList", jxglDao.getLdList2(Base.currNd));
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
	}

	// 军训管理照片管理
	public static ActionForward jxglphotoquery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = "";
		String[] colList = null;
		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		JxglForm wjscform = (JxglForm) form;
		String pkValue = request.getParameter("pkValue");
        HashMap<String, String> rs1 = new HashMap<String, String>();
        String remark = DealString.toGBK(request.getParameter("remark"));//照片类型
        
        //照片类型
        if(null!=remark&&!"".equalsIgnoreCase(remark)){
        	if("国旗护卫队".equalsIgnoreCase(remark)){
        		remark = " and remark='国旗护卫队'";
        		rs1.put("remark", "国旗护卫队");
        	}
        	if("军训相关".equalsIgnoreCase(remark)){
        		remark = " and remark='军训相关'";
        		rs1.put("remark", "军训相关");
        	}
        }
        
        
        
		if (null != pkValue) {
			pkValue.replace(" ", "+");
		}

		// 翻页
		sql = "select count(*) count from jxgl_photo a where 1=1 "+remark	;
		String rsNum = dao.getOneRs(sql, new String[] {}, "count");
		wjscform.getJxglpages().setMaxRecord(Integer.parseInt(rsNum));

		// 照片信息
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.bt,a.nr,a.path,a.fbsj,a.fbr from jxgl_photo a where 1=1 "+ remark +" order by fbsj) a ) a where a.r>"
				+ wjscform.getJxglpages().getStart()
				+ " and a.r<="
				+ (wjscform.getJxglpages().getStart() + wjscform.getJxglpages()
						.getPageSize()) + " order by fbsj";
		colList = new String[] { "rid", "bt", "nr", "path", "fbsj", "fbr" };
		rs = dao.getArrayList(sql, new String[] {}, colList);

		//
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		return mapping.findForward("success");
	}

	// 查看照片信息
	public static ActionForward jxglphotoinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		if (null != pkValue) {
			pkValue.replace(" ", "+");
		}
		HashMap<String, String> map = new HashMap<String, String>();

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "xx");
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select rowid rid,a.* from jxgl_photo a where a.rowid=?";
			String[] colList = { "rid", "bt", "nr", "path", "fbr", "fbsj","remark" };
			String[] tpxxinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (tpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), tpxxinfo[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");

	}

	// 修改照片
	public static ActionForward jxglupdatephoto(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		if (null != pkValue) {
			pkValue.replace(" ", "+");
		}

		if ("update".equals(doType)) {
			String bt = DealString.toGBK(request.getParameter("bt"));
			String nr = DealString.toGBK(request.getParameter("nr"));

			boolean judge = false;
			judge = StandardOperation.update("jxgl_photo", new String[] { "bt",
					"nr" }, new String[] { bt, nr }, "rowid", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}

		sql = "select rowid rid,a.* from jxgl_photo a where a.rowid=?";
		String[] colList = { "rid", "bt", "nr" };
		String[] tpxxinfo = dao
				.getOneRs(sql, new String[] { pkValue }, colList);
		if (tpxxinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), tpxxinfo[i]); // 将记录循环放入map中
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 入伍报名
	public static ActionForward jxglrwbm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();

		if (!"stu".equalsIgnoreCase(userType)
				&& !"admin".equalsIgnoreCase(userType)) {
			return mapping.findForward("false");
		}

		if ("admin".equalsIgnoreCase(userType)) {
			request.setAttribute("readonly", "readonly");
		}

		sql = "select t.xh, t.xm, t.xb, t.nj,t.sfzh,t.xydm,t.zydm, t.zymc, t.nj, t.lxdh,t.hkshen, t.hkshi," +
				" t.hkxian,t.syd from view_xsxxb t where t.xh=?";
		String[] colList = { "xh", "xm", "xb", "nj","sfzh", "xydm", "zydm", "zymc",
				"nj", "lxdh", "hkshen", "hkshi", "hkxian", "syd" };
		String[] stuinfo = dao
				.getOneRs(sql, new String[] { userName }, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
			map.put("rxsj", map.get("nj"));
		}

		if ("save".equalsIgnoreCase(doType)) {

			String xh = request.getParameter("xh"); // 学号
			String xm = request.getParameter("xm"); // 姓名
			String xb = request.getParameter("xb"); // 性别
			String mz = request.getParameter("mz"); // 民族
			String jg = request.getParameter("jg"); // 籍贯
			String csny = request.getParameter("csny"); // 出生年月
			String zzmm = request.getParameter("zzmm"); // 政治面貌
			String rxsj = request.getParameter("rxsj"); // 入学时间
			String zymc = request.getParameter("zymc"); // 专业名称
			String jtcs = request.getParameter("jtcs"); // 家庭出身
			String tc = request.getParameter("tc"); // 特长
			String xszh = request.getParameter("xszh"); // 学生证号
			String sfzh = request.getParameter("sfzh"); // 身份证号
			String jtzz = request.getParameter("jtzz"); // 家庭住址
			String lxdh = request.getParameter("lxdh"); // 联系电话
			String sfjsxl = request.getParameter("sfjsxl"); // 是否受过军事训练
			String sxnr = request.getParameter("sxnr"); // 受训内容
			String jcqk = request.getParameter("jcqk"); // 奖惩情况
			String zxbx = request.getParameter("zxbx"); // 在校表现
			String jtzycy = request.getParameter("jtzycy"); // 家庭主要成员
			String grjl = request.getParameter("grjl"); // 个人简历
			String sg = request.getParameter("sg"); // 身高
			String tz = request.getParameter("tz"); // 体重
			String slright = request.getParameter("slright"); // 右眼视力
			String slleft = request.getParameter("slleft"); // 左眼视力
			String jtjgrbs = request.getParameter("jtjgrbs"); // 家庭及个人病史情况
			//	================= begin 骆嘉伟 2009/3/30 ========================
			String xxdm = StandardOperation.getXxdm();
			request.setAttribute("xxdm", xxdm);
			String jtyj = "";
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
				  jtyj = request.getParameter("jtyj"); // 家庭意见
			}
			String djsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 登记时间
			
			// ================= begin luojw 2010/6/8 ========================
			
			//字段
			String[] zdList = new String[]{
					"xh", "xm", "xb", "mz", "jg", "csny", "zzmm", "rxsj",
					"zymc", "jtcs", "tc", "xszh", " sfzh", "jtzz", "lxdh",
					"sfjsxl", "sxnr", "jcqk", "zxbx", "jtzycy", "grjl",
					"sg", "tz", "slright", "slleft", "jtjgrbs", "djsj" };
			
			//字段值
			String[] zdzList = new String[]{ xh, xm, xb, mz, jg, csny, zzmm, rxsj,
					zymc, jtcs, tc, xszh, sfzh, jtzz, lxdh, sfjsxl,
					sxnr, jcqk, zxbx, jtzycy, grjl, sg, tz,
					slright, slleft, jtjgrbs, djsj };
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {//浙江传媒
				
				zdList = new String[]{
						"xh", "xm", "xb", "mz", "jg", "csny", "zzmm", "rxsj",
						"zymc", "jtcs", "tc", "xszh", " sfzh", "jtzz", "lxdh",
						"sfjsxl", "sxnr", "jcqk", "zxbx", "jtzycy", "grjl",
						"sg", "tz", "slright", "slleft", "jtjgrbs", "djsj","jtyj" };
				
				zdzList = new String[]{ xh, xm, xb, mz, jg, csny, zzmm, rxsj,
						zymc, jtcs, tc, xszh, sfzh, jtzz, lxdh, sfjsxl,
						sxnr, jcqk, zxbx, jtzycy, grjl, sg, tz,
						slright, slleft, jtjgrbs, djsj,jtyj };
			}
			
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GUIZHDX)) {//贵州大学
				String lx = request.getParameter("lx"); // 家庭及个人病史情况
				zdList = new String[] { "xh", "xm", "xb", "mz", "jg", "csny",
						"zzmm", "rxsj", "zymc", "jtcs", "tc", "xszh", " sfzh",
						"jtzz", "lxdh", "sfjsxl", "sxnr", "jcqk", "zxbx",
						"jtzycy", "grjl", "sg", "tz", "slright", "slleft",
						"jtjgrbs", "djsj", "lx" };

				zdzList = new String[] { xh, xm, xb, mz, jg, csny, zzmm, rxsj,
						zymc, jtcs, tc, xszh, sfzh, jtzz, lxdh, sfjsxl, sxnr,
						jcqk, zxbx, jtzycy, grjl, sg, tz, slright, slleft,
						jtjgrbs, djsj, lx };
			}
			
			// ================= end ========================
			
			boolean judge = StandardOperation.insert("jxgl_rwbmdjb", zdList,
					zdzList, request);
			
			// ================= end 骆嘉伟 2009/3/30 ========================
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		// 获得政治面貌列表
		sql = "select zzmmdm,zzmm from dmk_zzmm";
		ArrayList<HashMap<String, String>> zzmmList = dao.getArrayList(sql,
				new String[] {}, new String[] { "zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);

		// 获得专业列表
		sql = "select distinct zydm,zymc from view_njxyzybj order by zydm";
		ArrayList<HashMap<String, String>> zyList = dao.getArrayList(sql,
				new String[] {}, new String[] { "zymc", "zydm" });

		// ================= begin luojw 2010/6/8 ========================
		JxglTyService tyService = new JxglTyService();
		JxglTyForm tyForm = new JxglTyForm();
		
		if("stu".equalsIgnoreCase(userType)){
			
			String syd = map.get("syd");
			String xh = userName;
			
			tyForm.setXh(xh);
					
			if (!Base.isNull(syd)) {
				String[] arrDq = syd.split("/");
				map.put("syshen", (arrDq.length >= 1) ? arrDq[0] : "");
				map.put("syshi", (arrDq.length >= 2) ? arrDq[1] : "");
				map.put("syxian", (arrDq.length >= 3) ? arrDq[2] : "");
			}
			
			colList = new String[] { "jtcy1_gx", "jtcy1_xm", "jtcy1_gzdz",
					"jtcy1_lxdh1", "jtcy2_gx", "jtcy2_xm", "jtcy2_gzdz",
					"jtcy2_lxdh1", "jtcy3_gx", "jtcy3_xm", "jtcy3_gzdz",
					"jtcy3_lxdh1" };
			
			//学生家庭其他信息
			HashMap<String, String> qtxxMap = CommonQueryDAO.commonQueryOne("xsfzxxb", colList, "xh", xh);
			map.put("jtcy1_gx", qtxxMap.get("jtcy1_gx"));
			map.put("jtcy1_xm", qtxxMap.get("jtcy1_xm"));
			map.put("jtcy1_gzdz", qtxxMap.get("jtcy1_gzdz"));
			map.put("jtcy1_lxdh1", qtxxMap.get("jtcy1_lxdh1"));
			map.put("jtcy2_gx", qtxxMap.get("jtcy2_gx"));
			map.put("jtcy2_xm", qtxxMap.get("jtcy2_xm"));
			map.put("jtcy2_gzdz", qtxxMap.get("jtcy2_gzdz"));
			map.put("jtcy2_lxdh1", qtxxMap.get("jtcy2_lxdh1"));		
			map.put("jtcy3_gx", qtxxMap.get("jtcy3_gx"));
			map.put("jtcy3_xm", qtxxMap.get("jtcy3_xm"));
			map.put("jtcy3_gzdz", qtxxMap.get("jtcy3_gzdz"));
			map.put("jtcy3_lxdh1", qtxxMap.get("jtcy3_lxdh1"));
		}

		tyService.setList(tyForm, request, "rwbm");
		// ================= end ========================
		
		request.setAttribute("zyList", zyList);
		request.setAttribute("mzList", dao.getMzList());// 民族列表
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 入伍报名查询
	public static ActionForward jxglrwbmquery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		String[] colList = null;
		List topTr = null;
		String rsNum = "0";
		String sql ="";

		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		
		//---------------2010/6/29 edit by luojw-----------------------------
		CommanForm myForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName= session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		// ---------------2010/6/29 end-----------------------------
		String shsj = (dao
				.getOneRs(
						"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
						new String[] {}, new String[] { "sdate" }))[0]; // 审核时间
		
		
		//批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = DealString
					.toGBK(request.getParameter("pkstring"));
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				//拆分主键字符串，形成主键盘数组
				pkstringI = pkstring.split("!!#!!");
			}

			StringBuffer pksql1 = new StringBuffer();
			String[] pksql =new String[]{};
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				// 对入伍报名表进行操作
				sql = "delete from jxgl_rwbmdjb where xh='"+whichxh+"'";
			    //把主键组合成sql语句
				pksql1.append(sql);
				pksql1.append("!!#!!");
			}
            //sql语句拆分成数组
			pksql = pksql1.toString().split("!!#!!");
			
			int[] judge2  =dao.runBatch(pksql);
			String whichpk = "";
			//检查哪一条删除失败，并在页面提示
			for(int i=0;i<judge2.length;i++){
				if(judge2[i]<1){
					whichpk =whichpk+" 第"+String.valueOf(i+1)+"条数据删除失败;\n";
				}
			}
			request.setAttribute("piliang", whichpk);
		}
		
		//批量审核
		if ("shall".equalsIgnoreCase(doType2)) {
			String shjg = request.getParameter("shjg");
			shjg = "tg".equalsIgnoreCase(shjg) ? "已通过√" : "未通过X";
			String pkstring = DealString
					.toGBK(request.getParameter("pkstring"));
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				//拆分主键字符串，形成主键盘数组
				pkstringI = pkstring.split("!!#!!");
			}

			StringBuffer pksql1 = new StringBuffer();
			String[] pksql =new String[]{};
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				// 对入伍报名表进行操作
				sql = "update jxgl_rwbmdjb set xxsh='"+shjg+"' , xxshr='"+userName+"' , shsj='"+ shsj +"' where xh='"+whichxh+"'";
			    //把主键组合成sql语句
				pksql1.append(sql);
				pksql1.append("!!#!!");
			}
            //sql语句拆分成数组
			pksql = pksql1.toString().split("!!#!!");
			
			int[] judge2  =dao.runBatch(pksql);
			String whichpk = "";
			//检查哪一条审核失败，并在页面提示
			for(int i=0;i<judge2.length;i++){
				if(judge2[i]<1){
					whichpk =whichpk+" 第"+String.valueOf(i+1)+"条数据审核失败;\n";
				}
			}
			request.setAttribute("piliang", whichpk);
		}
		if ("shall2".equalsIgnoreCase(doType2)) {
			String pkstring = DealString
					.toGBK(request.getParameter("pkstring"));
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				//拆分主键字符串，形成主键盘数组
				pkstringI = pkstring.split("!!#!!");
			}

			StringBuffer pksql1 = new StringBuffer();
			String[] pksql =new String[]{};
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				// 对入伍报名表进行操作
				sql = "update jxgl_rwbmdjb set ZBBGSSH='已通过√' , ZBBGSSHR='"+userName+"' , ZBBGSSHSJ='"+ shsj +"' where xh='"+whichxh+"'";
			    //把主键组合成sql语句
				pksql1.append(sql);
				pksql1.append("!!#!!");
			}
            //sql语句拆分成数组
			pksql = pksql1.toString().split("!!#!!");
			
			int[] judge2  =dao.runBatch(pksql);
			String whichpk = "";
			//检查哪一条审核失败，并在页面提示
			for(int i=0;i<judge2.length;i++){
				if(judge2[i]<1){
					whichpk =whichpk+" 第"+String.valueOf(i+1)+"条数据审核失败;\n";
				}
			}
			request.setAttribute("piliang", whichpk);
		}

		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			map.put("xydm", userDep);
			request.setAttribute("who", "xy");
		} else if ("stu".equalsIgnoreCase(userType)) {
			return mapping.findForward("false");
		}

		if ("del".equalsIgnoreCase(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete("jxgl_rwbmdjb", "xh", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String shjg = DealString.toGBK(request.getParameter("shjg"));

		if ("query".equalsIgnoreCase(doType)) {
			StringBuffer query = new StringBuffer();
			query.append(" where 1=1 ");
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("nj", nj);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("shjg", shjg);

			if (!"".equalsIgnoreCase(xh) && null != xh) {
				query.append(" and xh like'");
				query.append(xh);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(xm) && null != xm) {
				query.append(" and xm like'");
				query.append(xm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(xb) && null != xb) {
				query.append(" and xb='");
				query.append(xb);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(nj) && null != nj) {
				query.append(" and rxsj='");
				query.append(nj);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(xydm) && null != xydm) {
				query.append(" and xydm='");
				query.append(xydm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(zydm) && null != zydm) {
				query.append(" and zydm='");
				query.append(zydm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(bjdm) && null != bjdm) {
				query.append(" and bjdm='");
				query.append(bjdm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(shjg) && null != shjg) {

				if ("未审核".equalsIgnoreCase(shjg)) {
					query.append(" and xxsh ='");
					query.append("未审核");
					query.append("' ");
				}
				if ("已通过√".equalsIgnoreCase(shjg)) {
					query.append(" and xxsh ='");
					query.append("已通过√");
					query.append("' ");
				}
				if ("未通过X".equalsIgnoreCase(shjg)) {
					query.append(" and xxsh ='");
					query.append("未通过X");
					query.append("' ");
				}
			}

			if ("go".equalsIgnoreCase(act)) {

				String querry = query.toString();
				String tableName = "";
				if (!xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
					sql = "select rownum 行号,a.* from view_jxgl_rwbmdjb a "
							+ querry;

					colList = new String[] { "行号", "xh", "xm", "xb", "rxsj",
							"xymc", "zymc", "djsj", "xxshimg" };
					tableName = "view_jxgl_rwbmdjb";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
					sql = "select rownum 行号,a.* from view_jxglXbmz_rwbmdjb a "
							+ querry;

					colList = new String[] { "行号", "xh", "xm", "xb", "rxsj",
							"xymc", "zymc", "djsj", "xxshimg", "zbbgsshimg" };
					tableName = "view_jxglXbmz_rwbmdjb";
				}
				String[] colListCN = dao.getColumnNameCN(colList,
						tableName);
				topTr = dao.arrayToList(colList, colListCN);
				rs = dao.getArrayList(sql, new String[] {}, colList);
				rsNum = String.valueOf(rs.size());
			}

		}

		sql = "select count(a.bmdm) num from xbmz_bmshqx a,yhb b where a.bmdm=b.szbm and b.yhm=? group by a.bmmc";
		String num = dao.getOneRs(sql,new String[]{userName},"num");
		if(num!=null&&!"".equals(num)){
			if(Integer.parseInt(num)>0){
				request.setAttribute("num", num);
				request.setAttribute("who", "xx");
			}
		}
		sql = "select count(a.yhm) yhm from yhb a, yhzb b where b.zmc = '征兵办公室' and a.zdm = b.zdm and a.yhm=?";
		String yhm = dao.getOneRs(sql,new String[]{userName},"yhm");
		if(yhm!=null&&!"".equals(yhm)){
			if(Integer.parseInt(yhm)>0){
				request.setAttribute("yhm", yhm);
			}
		}
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);

		return mapping.findForward("success");
	}

	// 入伍报名表详细查看
	public static ActionForward jxglrwbmmorequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		sql = "select a.* from view_jxgl_rwbmdjb a where a.xh=?";
		String[] colList = dao
				.getColumnName("select * from view_jxgl_rwbmdjb where 1=2");
		String[] stuinfo = dao.getOneRs(sql, new String[] { pkValue }, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
			map.put("djsj", dao.doForTime2(map.get("djsj")));
			if (!"".equalsIgnoreCase(map.get("shsj"))
					&& null != map.get("shsj")) {
				map.put("shsj", dao.doForTime2(map.get("shsj")));
			}
		}
		// ================= begin 骆嘉伟 2009/3/30 ========================
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// ================= end 骆嘉伟 2009/3/30 ========================
		// ================= begin luojw 2010/6/8 ========================
		JxglTyService tyService = new JxglTyService();
		JxglTyForm tyForm = new JxglTyForm();
		
		String xh = map.get("xh");
		String syd = tyService.getOneValue("view_xsxxb", "syd", "xh", xh);

		tyForm.setXh(xh);

		if (!Base.isNull(syd)) {
			String[] arrDq = syd.split("/");
			map.put("syshen", (arrDq.length >= 1) ? arrDq[0] : "");
			map.put("syshi", (arrDq.length >= 2) ? arrDq[1] : "");
			map.put("syxian", (arrDq.length >= 3) ? arrDq[2] : "");
		}

		colList = new String[] { "jtcy1_gx", "jtcy1_xm", "jtcy1_gzdz",
				"jtcy1_lxdh1", "jtcy2_gx", "jtcy2_xm", "jtcy2_gzdz",
				"jtcy2_lxdh1", "jtcy3_gx", "jtcy3_xm", "jtcy3_gzdz",
				"jtcy3_lxdh1" };

		// 学生家庭其他信息
		HashMap<String, String> qtxxMap = CommonQueryDAO.commonQueryOne(
				"xsfzxxb", colList, "xh", xh);
		map.put("jtcy1_gx", qtxxMap.get("jtcy1_gx"));
		map.put("jtcy1_xm", qtxxMap.get("jtcy1_xm"));
		map.put("jtcy1_gzdz", qtxxMap.get("jtcy1_gzdz"));
		map.put("jtcy1_lxdh1", qtxxMap.get("jtcy1_lxdh1"));
		map.put("jtcy2_gx", qtxxMap.get("jtcy2_gx"));
		map.put("jtcy2_xm", qtxxMap.get("jtcy2_xm"));
		map.put("jtcy2_gzdz", qtxxMap.get("jtcy2_gzdz"));
		map.put("jtcy2_lxdh1", qtxxMap.get("jtcy2_lxdh1"));
		map.put("jtcy3_gx", qtxxMap.get("jtcy3_gx"));
		map.put("jtcy3_xm", qtxxMap.get("jtcy3_xm"));
		map.put("jtcy3_gzdz", qtxxMap.get("jtcy3_gzdz"));
		map.put("jtcy3_lxdh1", qtxxMap.get("jtcy3_lxdh1"));

		tyService.setList(tyForm, request, "rwbm");
		// ================= end ========================
		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// 入伍报名审核
	public static ActionForward jxglrwbmxxsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = DealString.toGBK(request.getParameter("doType"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String zbbgssh = DealString.toGBK(request.getParameter("zbbgssh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String zbbgsbtgyy = DealString.toGBK(request.getParameter("zbbgsshbtgyy"));
		String shsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0]; // 审核时间

		if ("xxsh".equalsIgnoreCase(doType)) {
			boolean judge = false;

			if ("未审核".equalsIgnoreCase(xxsh)) {
				userName = "";
				btgyy = "";
				shsj = "";
			}

			judge = StandardOperation.update("jxgl_rwbmdjb", new String[] {
					"xxsh", "xxshr", "shsj", "btgyy" }, new String[] { xxsh,
					userName, shsj, btgyy }, "xh", pkValue, request);

			if (judge) {
				request.setAttribute("xxsh", "ok");
			} else {
				request.setAttribute("xxsh", "no");
			}
		}

		if ("zbxxsh".equalsIgnoreCase(doType)) {
			boolean judge = false;

			if ("未审核".equalsIgnoreCase(zbbgssh)) {
				userName = "";
				btgyy = "";
				shsj = "";
			}
			sql = "select xxsh from view_jxgl_rwbmdjb where xh=?";
			String sh=dao.getOneRs(sql, new String[] {pkValue}, "xxsh");
			if ("未审核".equals(sh)) {
				String msg = "学校未审核，无法提交征兵办公室审核";
				request.setAttribute("msg", msg);
				request.setAttribute("xxsh", "no");
				request.setAttribute("rs", map);
				return mapping.findForward("success");
			}
			
			judge = StandardOperation.update("jxgl_rwbmdjb", new String[] {
					"ZBBGSSH", "ZBBGSSHR", "ZBBGSSHSJ", "ZBBGSSHBTGYY" }, new String[] { zbbgssh,
					userName, shsj, zbbgsbtgyy }, "xh", pkValue, request);

			request.setAttribute("zb", "zb");
			if (judge) {
				request.setAttribute("xxsh", "ok");
			} else {
				request.setAttribute("xxsh", "no");
			}
		}
		
		String[] colList = new String[]{};
		if (request.getParameter("zb") != null
				&& "zb".equals(request.getParameter("zb"))) {
			sql = "select a.* from view_jxglXbmz_rwbmdjb a where a.xh=?";
			colList = dao
					.getColumnName("select * from view_jxglXbmz_rwbmdjb where 1=2");
			request.setAttribute("zb", "zb");
		} else {
			sql = "select a.* from view_jxgl_rwbmdjb a where a.xh=?";
			colList = dao
					.getColumnName("select * from view_jxgl_rwbmdjb where 1=2");
		}
		String[] stuinfo = dao.getOneRs(sql, new String[] { pkValue }, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
			map.put("djsj", dao.doForTime2(map.get("djsj")));
			if (!"".equalsIgnoreCase(map.get("shsj"))
					&& null != map.get("shsj")) {
				map.put("shsj", dao.doForTime2(map.get("shsj")));
			}
		}
		// ================= begin luojw 2010/6/8 ========================
		JxglTyService tyService = new JxglTyService();
		JxglTyForm tyForm = new JxglTyForm();
		
		String xh = map.get("xh");
		String syd = tyService.getOneValue("view_xsxxb", "syd", "xh", xh);

		tyForm.setXh(xh);

		if (!Base.isNull(syd)) {
			String[] arrDq = syd.split("/");
			map.put("syshen", (arrDq.length >= 1) ? arrDq[0] : "");
			map.put("syshi", (arrDq.length >= 2) ? arrDq[1] : "");
			map.put("syxian", (arrDq.length >= 3) ? arrDq[2] : "");
		}

		colList = new String[] { "jtcy1_gx", "jtcy1_xm", "jtcy1_gzdz",
				"jtcy1_lxdh1", "jtcy2_gx", "jtcy2_xm", "jtcy2_gzdz",
				"jtcy2_lxdh1", "jtcy3_gx", "jtcy3_xm", "jtcy3_gzdz",
				"jtcy3_lxdh1" };

		// 学生家庭其他信息
		HashMap<String, String> qtxxMap = CommonQueryDAO.commonQueryOne(
				"xsfzxxb", colList, "xh", xh);
		map.put("jtcy1_gx", qtxxMap.get("jtcy1_gx"));
		map.put("jtcy1_xm", qtxxMap.get("jtcy1_xm"));
		map.put("jtcy1_gzdz", qtxxMap.get("jtcy1_gzdz"));
		map.put("jtcy1_lxdh1", qtxxMap.get("jtcy1_lxdh1"));
		map.put("jtcy2_gx", qtxxMap.get("jtcy2_gx"));
		map.put("jtcy2_xm", qtxxMap.get("jtcy2_xm"));
		map.put("jtcy2_gzdz", qtxxMap.get("jtcy2_gzdz"));
		map.put("jtcy2_lxdh1", qtxxMap.get("jtcy2_lxdh1"));
		map.put("jtcy3_gx", qtxxMap.get("jtcy3_gx"));
		map.put("jtcy3_xm", qtxxMap.get("jtcy3_xm"));
		map.put("jtcy3_gzdz", qtxxMap.get("jtcy3_gzdz"));
		map.put("jtcy3_lxdh1", qtxxMap.get("jtcy3_lxdh1"));

		tyService.setList(tyForm, request, "rwbm");
		// ================= end ========================
		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}
	// 入伍报名审核结果查询
	public static ActionForward jxglrwbmxxshresult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		String sql ="";
		String[] colList = null;
		String[] colListCN = null;
		List topTr = null;
		String xxdm = StandardOperation.getXxdm();
		
		if ("stu".equalsIgnoreCase(userType)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				sql = "select rownum 行号,a.* from view_jxglXbmz_rwbmdjb a where xh=?";

				colList = new String[] { "行号", "xh", "xm", "xb", "rxsj",
						"xymc", "zymc", "djsj", "xxsh","zbbgssh" };
				colListCN = dao.getColumnNameCN(colList, "view_jxglXbmz_rwbmdjb");
				topTr = dao.arrayToList(colList, colListCN);
				rs = dao.getArrayList(sql, new String[] { userName }, colList);
			} else {
				sql = "select rownum 行号,a.* from view_jxgl_rwbmdjb a where xh=?";

				colList = new String[] { "行号", "xh", "xm", "xb", "rxsj",
						"xymc", "zymc", "djsj", "xxshimg" };
				colListCN = dao.getColumnNameCN(colList, "view_jxgl_rwbmdjb");
				topTr = dao.arrayToList(colList, colListCN);
				rs = dao.getArrayList(sql, new String[] { userName }, colList);
			}

		}else{
			return mapping.findForward("false");
		}
		
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}
	
	private static String[] getInputList(HttpServletRequest request, String[] colList) {
		String [] inputList = new String [colList.length];
			for(int i = 0;i<colList.length;i++) {
				inputList[i] = DealString.toGBK(request.getParameter(colList[i]));
			}
		return inputList;
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList getTj(String[] colList, String[] inputList) {
		ArrayList map = new ArrayList();
		StringBuffer  queryBuffer =  new StringBuffer(" where 1 = 1 ");
		StringBuffer  tempBuffer =  new StringBuffer("");
		String [] setList = new String [] {};
		for(int i = 0;i<colList.length;i++) {
			if(!inputList[i].equalsIgnoreCase("")) {
				queryBuffer.append("and ");
				queryBuffer.append(colList[i]);
				queryBuffer.append(" =  ? ");
				tempBuffer.append("-");
				tempBuffer.append(inputList[i]);
			}
		}
		if(queryBuffer.length()>14) {
			setList = (tempBuffer.toString().substring(1)).split("-");
		}else {
			setList = new String [] {};
		}
		map.add(queryBuffer.toString());
		map.add(setList);
		return map;
	}
	
}
