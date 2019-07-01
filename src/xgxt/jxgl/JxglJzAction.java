
package xgxt.jxgl;

import java.io.File;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDataInfo;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;

public class JxglJzAction extends DispatchAction{
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理思政队伍-西北2民院-军训管理建制action类</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	
	public ActionForward jxgljzwh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//军训管理建制维护
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_jxglbz";
		String realTable     = "jxgljzwhb";
		String title     = "军训管理-军训编制－建制维护";
		
		ArrayList<String[]> rs = null;
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service          =    new JxglJzService();
		JxglJzModel myModel            =    new JxglJzModel();
		
		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		nj = (nj==null) ? "" : nj;
		
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getJxjzForbjList(myModel);
		}
		
		setFormForXnNdXqNj(myForm);
		List topTr = service.getJxjzTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("jxgljzwh");		
	}
	
	public ActionForward jxgljzwhOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 军训管理建制单个维护
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xy = "";
		JxglForm myForm              =    (JxglForm)  form;
		
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		JxglJzService service          =    new JxglJzService();
		
		HashMap<String, String> rs = service.getJxglJzFor2my(pk);
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;	
		}else {
			xy = rs.get("xydm");
		}
		myForm.setXydm(xy);
		setFormForXnNdXqNj(myForm);
		request.setAttribute("xydm", xy);
		request.setAttribute("rs", rs);
		String zy = (rs.get("zy")==null) ? "" : rs.get("zy");
		String nj = "";
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("jxgljzwhOne");		
	}
	
	public ActionForward jxgljzwhOneSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 军训管理建制单个维护
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service        =    new JxglJzService();
		JxglJzModel myModel          =    new JxglJzModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataJxjz(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("jxgljzwhOne");		
	}
	
	public ActionForward delJxgljzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 军训管理建制单个删除
		JxglJzService service        =    new JxglJzService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteJxjz(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return jxgljzwh(mapping, form,request, response);		
	}
	
	public ActionForward jxxxztcx (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//军训编制查询
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_xsjxxx";
		String title     = "军训管理-军训编制－编制查询";
		
		ArrayList<String[]> rs = null;
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service          =    new JxglJzService();
		JxglJzModel myModel            =    new JxglJzModel();
		
		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		nj = (nj==null) ? "" : nj;
		
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getXsjxList(myModel);
		}
		
		setFormForXnNdXqNj(myForm);
		List topTr = service.getXsjxTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		
		commonRequestSet(request, tableName, "", rs, topTr,title);
		return mapping.findForward("jxxxztcx");		
	}
	
	public ActionForward jxglxsjzOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // 军训学生建制单个查看
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			JxglJzService service          =    new JxglJzService();
			HashMap<String, String> rs = service.getJxglxsJzFor2my(pk);
			request.setAttribute("rs", rs);
			return mapping.findForward("jxglxsjzOne");		
	}
	
	public ActionForward xsgrjxxxcx (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // 学生登入查看军训信息
			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userName = session.getAttribute("userName").toString();
			String xh = "";
			if(userType.equalsIgnoreCase("stu")) {
				xh = userName;
			}else {
				request.setAttribute("rs", "");
				return mapping.findForward("jxglxsjzOne");		
			}
			JxglJzService service          =    new JxglJzService();
			HashMap<String, String> rs = service.getJxglxsJzFor2my(xh);
			request.setAttribute("rs", rs);
			return mapping.findForward("jxglxsjzOne");		
	}
	
	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable,
			ArrayList<String[]> rs, List topTr,String title) {
    //    Request存值的通用方法
		String writeAble    = request.getParameter("writeAble");
		if(writeAble==null){
			   writeAble    = Base.getWriteAble(request);
		}
		
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	//	以下是云南艺术的连队维护
	public ActionForward jxglldwhYnys(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//云南艺术的连队维护
		String tableName     = "jxlddmwhb";
		String realTable     = "jxlddmwhb";
		String title     = "军训管理-军训编制－连队建制维护";
		
		ArrayList<String[]> rs = null;
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service          =    new JxglJzService();
		JxglJzModel myModel            =    new JxglJzModel();
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getLdjzList(myModel);
		}
		
		setFormForXnNdXqNj(myForm);
		List topTr = service.getLdjzTopTr();
		
		request.setAttribute("xnList", Base.getXnndList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("jxglldwh");		
	}
	
	public ActionForward jxglldwhYnysOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 云南艺术的连队单个维护
		JxglForm myForm              =    (JxglForm)  form;
		
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		JxglJzService service          =    new JxglJzService();
		
		HashMap<String, String> rs = service.getJxglldwhYnys(pk);
		setFormForXnNdXqNj(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("jxglldwhOne");		
	}
	
	public ActionForward jxglldwhYnysOneSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 云南艺术的连队维护保存
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service        =    new JxglJzService();
		JxglJzModel myModel          =    new JxglJzModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataLdjz(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("jxglldwhOne");		
	}
	
	public ActionForward delJxglLdjzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 云南艺术的连队维护删除
		JxglJzService service        =    new JxglJzService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteJxLdjz(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return jxglldwhYnys(mapping, form,request, response);		
	}
	
	public ActionForward jxgljskqxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//云南艺术的考勤信息
		String tableName     = "view_jxkqxxb";
		String realTable     = "jxkqxxb";
		String title     = "军训管理-信息维护－军训考勤维护";
		
		ArrayList<String[]> rs = null;
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service          =    new JxglJzService();
		JxglJzModel myModel            =    new JxglJzModel();
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getKqxxList(myModel);
		}
		
		setFormForXnNdXqNj(myForm);
		List topTr = service.getKqxxTopTr();
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("kqqkList", service.getKqqkList());
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("jxgljskqxx");		
	}
	
	public ActionForward jxgljskqxxYnysOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//单个考勤信息
		JxglForm myForm              =    (JxglForm)  form;
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		JxglJzService service          =    new JxglJzService();
		HashMap<String, String> rs = service.getKqxxYnys(pk);
		setFormForXnNdXqNj(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("kqqkList", service.getKqqkList());
		return mapping.findForward("jxgljskqxxOne");		
	}
	
	public ActionForward jxgljskqxxOneSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 云南艺术的考勤信息保存
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service        =    new JxglJzService();
		JxglJzModel myModel          =    new JxglJzModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataKqxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("jxglldwhOne");		
	}
	
	public ActionForward delKqxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 云南艺术的考勤信息删除
		JxglJzService service        =    new JxglJzService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteKqxx(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return jxgljskqxx(mapping, form,request, response);		
	}
	
	public ActionForward jxglzxsj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//云南艺术的作息时间
		String tableName     = "jxglzxsj";
		String realTable     = "jxglzxsj";
		String title     = "军训管理-信息维护－作息时间";
		
		ArrayList<String[]> rs = null;
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service          =    new JxglJzService();
		JxglJzModel myModel            =    new JxglJzModel();
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getZxsjList(myModel);
		}
		
		setFormForXnNdXqNj(myForm);
		List topTr = service.getZxsjTopTr();
		request.setAttribute("xnList", Base.getXnndList());
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("jxglzxsj");		
	}
	
	public ActionForward jxglzxsjOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//单个作息时间
		JxglForm myForm              =    (JxglForm)  form;
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		JxglJzService service          =    new JxglJzService();
		HashMap<String, String> rs = service.getZxsjYnys(pk);
		setFormForXnNdXqNj(myForm);
		rs.put("pk", pk);
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("jxglzxsjOne");		
	}
	
	public ActionForward jxglzxsjOneSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 云南艺术的作息时间保存
		JxglForm myForm              =    (JxglForm)  form;
		JxglJzService service        =    new JxglJzService();
		JxglJzModel myModel          =    new JxglJzModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataZxsj(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("jxglzxsjOne");		
	}
	
	public ActionForward delJxglzxsjOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 云南艺术的作息时间删除
		JxglJzService service        =    new JxglJzService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteZxsj(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return jxglzxsj(mapping, form,request, response);		
	}
	
	public ActionForward jxglldfp(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		DAO dao = DAO.getInstance();
		String userType;
		String userDep;
//		String writeAble;
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		userDep = session.getAttribute("userDep").toString();
		String nd = Base.currNd;
		JxglForm myForm = (JxglForm) form;
		String xb = DealString.toGBK(myForm.getXb());
		String sql;
		String inputSQL[];
		String outputSQL[];
		String xydm = myForm.getXydm();
		String ldbh = myForm.getLdbh();
		String nj = myForm.getNj();
		String zydm = myForm.getZydm();
		String ldmc = myForm.getLdmc();
		String act = request.getParameter("act");
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String[] tmp;
		xydm = Base.chgNull(xydm, "%", 0);
		nj = Base.chgNull(nj, "%", 0);
		zydm = Base.chgNull(zydm, "%", 0);
		ldbh = Base.chgNull(ldbh, "", 0);
		ldmc = Base.chgNull(ldmc, "", 0);

		userDep = request.getSession().getAttribute("userDep")
		.toString();
		userType = dao.getUserType(userDep);
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			xydm = userDep;
		}

		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {

			boolean ok = false;

			String[] bjdm = myForm.getBjdmList();
			if (bjdm == null || Base.isNull(ldbh)) {
				sql = "delete from ldbzfpb where ldbh=? and nd = ?";
				ok = dao.runUpdate(sql, new String[] { ldbh,nd });
			} else {
				mapTmp = new HashMap<String, Object>();
				sql = "delete from ldbzfpb where ldbh=? and nd = ?";
				ok = dao.runUpdate(sql, new String[] { ldbh,nd });
				StringBuffer mdQuery = new StringBuffer();
				mdQuery.append(" where exists (select 1 from view_xsjbxx b where a.xh = b.xh and b.xb = '"+xb+"' and b.bjdm in ('");
				for (int i = 0; i < bjdm.length; i++) {
					mdQuery.append(bjdm[i]);
					if(i==bjdm.length-1) {
						mdQuery.append("'");
					}else {
						mdQuery.append("','");
					}
					mapTmp = new HashMap<String, Object>();
					sql = "insert into ldbzfpb(ldbh,bjdm,nd,xb) values(?,?,?,?)";
					tmp = new String[] { ldbh, bjdm[i],nd,xb};
					mapTmp.put("sqlTxt", sql);
					mapTmp.put("sqlVal", tmp);
					sqlToExe.add(mapTmp);
				}
				ok = dao.runUpdate(sqlToExe);
				mdQuery.append("))");
				String tj2 = mdQuery.toString();
				mdQuery.append("and nd = '"+nd+"'");
				if(ok) {
				sql = "delete from jxgl_jxmdb a ";
					ok = dao.runUpdate(sql+mdQuery.toString(), new String[] {});
					if(ok) {
						sql = "insert into jxgl_jxmdb (xh,nd,ldbh) select xh,'"+nd+"' nd, '"+ldbh+"' ldbh from view_xsjbxx a";
						ok = dao.runUpdate(sql+tj2, new String[] {});
					}
				}
			}

			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		sql = "select ldbh,ldmc,xb from jxlddmwhb where nd =? and xb = ? order by ldbh";
		inputSQL = new String[] {nd,xb};
		outputSQL = new String[] { "ldbh","ldmc", "xb" };
		List ldList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where a.nj like ? and"
			+ " a.xydm = ? and a.zydm like ? and not exists (select bjdm from "
			+ "ldbzfpb b where a.bjdm = b.bjdm and b.nd = ? )";
		inputSQL = new String[] { nj, xydm, zydm,nd };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List bjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from ldbzfpb b where b.ldbh=? and b.bjdm=a.bjdm and b.nd = ?)";
		inputSQL = new String[] { ldbh,nd };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List ldBjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select ldbh,ldmc,zdy,jgmc,xb,nd,bz from jxlddmwhb where ldbh=? and xb = ? and nd = ?";
		inputSQL = new String[] { ldbh,xb,nd };
		outputSQL = new String[] { "ldbh","ldmc","zdy","jgmc","xb","nd","bz" };
		HashMap<String, String> LdInfo = dao.getMap(sql, inputSQL, outputSQL);
		if (LdInfo == null) {
			LdInfo = new HashMap<String, String>();
		}

		myForm.setLdmc(ldmc);
		myForm.setXb(xb);
		request.setAttribute("ldList", ldList);
		request.setAttribute("bjList", bjList);
		request.setAttribute("ldBjList", ldBjList);
		request.setAttribute("LdInfo", LdInfo);
		request.setAttribute("njList",  Base.getNjList());// 发送年级列表
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		if(xydm.equalsIgnoreCase("%")){
			xydm = "";
		}
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		return mapping.findForward("jxglldfp");
	}
	
	private void setFormForXnNdXqNj(JxglForm myForm) {
	//    判断学年，年度，学期,年级后，往form里注入的方法
		String xn           = Base.currXn;
		String xq           = Base.currXq;
		String nd           = Base.currNd;
		
		if((DealString.toGBK(myForm.getXn())).equalsIgnoreCase("")){
			myForm.setXn(xn);
		}
		
		if((DealString.toGBK(myForm.getXq())).equalsIgnoreCase("")){
			myForm.setXq(xq);
		}
		
		if((DealString.toGBK(myForm.getNd())).equalsIgnoreCase("")){
			myForm.setNd(nd);
			myForm.setNj(nd);
		}
	}
	
	public ActionForward jxgljzwhNblg(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		String title = "军训管理-军训编制－建制维护";
		//TODO
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rsT = new HashMap<String, String>();
		GetDataInfo dataInfo = new GetDataInfo();
		DAO dao = DAO.getInstance();
		
		JxglForm myForm = (JxglForm) form;
		JxglJzService service = new JxglJzService();
		
		String nj = myForm.getNj();
		nj = (nj==null) ? Base.currNd : nj;
		myForm.setNj(nj);
		rsT.put("nj", nj);
		
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String jgbh =DealString.toGBK(request.getParameter("jxjgmcV"));
		String zdy =DealString.toGBK(request.getParameter("jxzdyV"));
		String jzdm =DealString.toGBK(request.getParameter("jxjzdmV"));
		if ("del".equalsIgnoreCase(act)){
			String sql="";
			String querry ="";
			if (jgbh != null && !"".equals(jgbh)) {
				sql = "delete from nblg_jgxx where sfzld in (";
				querry = "select * from ( select '"
						+ jzdm
						+ "' from dual union (select "
						+ "distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b where t.sfzld = b.bzdm and b.sjdm = '"
						+ jzdm
						+ "') union (select bzdm from nblg_jxbzdmb"
						+ " where sjdm in (select distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b where t.sfzld = b.bzdm"
						+ " and b.sjdm = '"
						+ jzdm
						+ "')) union (select bzdm from nblg_jxbzdmb where sjdm in (select bzdm"
						+ " from nblg_jxbzdmb where sjdm in (select distinct (b.bzdm) from view_nblg_jxwh t, nblg_jxbzdmb b"
						+ " where t.sfzld = b.bzdm and b.sjdm = '" + jzdm
						+ "')))))";
				StandardOperation.delete(sql + querry, "nblg_jgxx", request);
			}
			if (zdy != null && !"".equals(zdy)) {
				sql = "delete from nblg_lsxx where sfzld in (";
				querry = "select * from ( select '"
						+ jzdm
						+ "' from dual union (select "
						+ "distinct (b.bzdm) from view_nblg_lsxx t, nblg_jxbzdmb b where t.sfzld = b.bzdm and b.sjdm = '"
						+ jzdm
						+ "') union (select bzdm from nblg_jxbzdmb"
						+ " where sjdm in (select distinct (b.bzdm) from view_nblg_lsxx t, nblg_jxbzdmb b where t.sfzld = b.bzdm"
						+ " and b.sjdm = '"
						+ jzdm
						+ "')) union (select bzdm from nblg_jxbzdmb where sjdm in (select bzdm"
						+ " from nblg_jxbzdmb where sjdm in (select distinct (b.bzdm) from view_nblg_lsxx t, nblg_jxbzdmb b"
						+ " where t.sfzld = b.bzdm and b.sjdm = '" + jzdm
						+ "')))))";

				StandardOperation.delete(sql + querry, "nblg_lsxx", request);
			}
			boolean b = service.delNblgJxbz(Base.chgNull(request
					.getParameter("cbVal"), "", 1), nj);
			if (b) {
				request.setAttribute("isDel", "is");
			} else {
				request.setAttribute("isDel", "no");
			}
		}
		
		String menuListTop = service.getNblgJxjz(nj);
		
		setFormForXnNdXqNj(myForm);
		request.setAttribute("njList", Base.getNjList());
		
		String[] sT = dataInfo.getJxjzDate(dao.getOneRs("select bzdm from nblg_jxbzdmb where nj=? and rownum=1 order by bzdm", new String[]{nj}, "bzdm"),nj);
		request.setAttribute("bzdm", sT != null ? sT[0] : "");
		request.setAttribute("bzmc", sT != null ? sT[1] : "");
		request.setAttribute("bzdj", sT != null ? sT[2] : "");
		request.setAttribute("bzdjmc", sT != null ? sT[3] : "");
		request.setAttribute("zdy", sT != null ? sT[4] : "");
		request.setAttribute("jgmc", sT != null ? sT[5] : "");
		request.setAttribute("ssjz", sT != null ? sT[6] : "");
		request.setAttribute("bz", sT != null ? sT[7] : "");
		request.setAttribute("rsT", rsT);
		request.setAttribute("userType", userType);
		request.setAttribute("menuListTop", menuListTop);
		request.setAttribute("title", title);
		return mapping.findForward("jxgljzwhNblg");
	}
	
	public ActionForward jxgljzwhXbmz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{

		String title = "军训管理-军训编制－建制维护";
		//TODO
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rsT = new HashMap<String, String>();
		GetDataInfo dataInfo = new GetDataInfo();
		DAO dao = DAO.getInstance();
		
		JxglForm myForm = (JxglForm) form;
		JxglJzService service = new JxglJzService();
		
		String nj = myForm.getNj();
		String xb = DealString.toGBK(request.getParameter("xb"));
		nj = (nj==null) ? Base.currNd : nj;
		myForm.setNj(nj);
		rsT.put("nj", nj);
		
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		if ("del".equalsIgnoreCase(act)) {
			boolean b = false;
			if ("-".equals(xb)) {
				b = service.delXbmzJxbz(Base.chgNull(request
						.getParameter("cbVal"), "", 1), nj, xb);
			} else {
				b = service.delXbmzJxbz(Base.chgNull(request
						.getParameter("cbVal"), "", 1), nj, xb);
			}

			if (b) {
				request.setAttribute("isDel", "is");
			} else {
				request.setAttribute("isDel", "no");
			}
		}
		
		String menuListTop = service.getXbmzJxjz(nj);
		
		setFormForXnNdXqNj(myForm);
		request.setAttribute("njList", Base.getNjList());
		
		String[] sT = dataInfo.getJxjzDate(dao.getOneRs("select bzdm from XBMZ_JXBZDMB where nj=? and rownum=1 order by bzdm", new String[]{nj}, "bzdm"),nj);
		request.setAttribute("bzdm", sT != null ? sT[0] : "");
		request.setAttribute("bzmc", sT != null ? sT[1] : "");
		request.setAttribute("bzdj", sT != null ? sT[2] : "");
		request.setAttribute("bzdjmc", sT != null ? sT[3] : "");
		request.setAttribute("zdy", sT != null ? sT[4] : "");
		request.setAttribute("jgmc", sT != null ? sT[5] : "");
		request.setAttribute("ssjz", sT != null ? sT[6] : "");
		request.setAttribute("bz", sT != null ? sT[7] : "");
		request.setAttribute("xb", sT != null ? sT[8] : "");
		request.setAttribute("rsT", rsT);
		request.setAttribute("userType", userType);
		request.setAttribute("menuListTop", menuListTop);
		request.setAttribute("title", title);
		return mapping.findForward("jxgljzwhXbmz");
	
	}

	public ActionForward addYjjz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		String title = "军训管理-军训编制－增加营级建制";
		//TODO
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		JxglForm myForm = (JxglForm) form;
		JxglJzService service = new JxglJzService();
		String act = request.getParameter("act");
		NblgJxjzModel model = new NblgJxjzModel();
		BeanUtils.copyProperties(model, myForm);
		
		String jgbh=myForm.getJgmc();
		String sfzld=myForm.getBzdm();
		String nj = model.getNj();
		String bzdm ="";
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if ("save".equalsIgnoreCase(act)) {
				// =========begin 骆嘉伟 2009/4/2 ===============
				boolean bJg = service.saveNblgJxjz(model, "add", request);
				boolean jg = true;
				if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
					jg = StandardOperation.delete("nblg_jgxx", "jgbh||sfzld",
							jgbh + sfzld, request);
				}
				if ((jgbh != null && !"".equalsIgnoreCase(jgbh)) && jg) {
					jg = StandardOperation.insert("nblg_jgxx", new String[] {
							"jgbh", "sfzld" }, new String[] { jgbh, sfzld },
							request);
				}
				if (bJg && jg) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
				rs = service.getNblgJxjzxx(model.getBzdm(), model.getNj());
				// =========begin 骆嘉伟 2009/4/2 ===============
			}
			bzdm = JxglDataAccessAction.getJxbzdm();
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			if ("save".equalsIgnoreCase(act)) {
				// =========begin 骆嘉伟 2009/4/27 ===============
				boolean bJg = service.saveXbmzJxjz(model, "add", request);
				if (bJg) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
				rs = service.getXbmzJxjzxx(model.getBzdm(), model.getNj());
				// =========begin 骆嘉伟 2009/4/27 ===============
			}
			bzdm = JxglDataAccessAction.getXbmz_Jxbzdm();
		}
		
		if (rs.size() == 0){
			rs.put("nj", myForm.getNj());
			rs.put("bzdj", "1");
			rs.put("bzdjmc", "营级");
		}
		rs.put("bzdm", bzdm);
		// =========begin 骆嘉伟 2009/4/1 ===============
		// 获得带队老师信息
		List<HashMap<String, String>> lsList = service.getLsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		request.setAttribute("lsList", lsList);
		request.setAttribute("jgList", jgList);
		// =========end 骆嘉伟 2009/4/1 ===============
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		return mapping.findForward("addYjjz");
	}
	
	public ActionForward addQtjz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//TODO
		String title = "军训管理-军训编制－增加其它建制";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		JxglForm myForm = (JxglForm) form;
		JxglJzService service = new JxglJzService();
		String act = request.getParameter("act");
		String sjdm = Base.chgNull(request.getParameter("sjdm"), "", 1);
		String ssjz = Base.chgNull(request.getParameter("ssjz"), "", 1);
		String sjdj = Base.chgNull(request.getParameter("sjdj"), "0", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		
		NblgJxjzModel model = new NblgJxjzModel();
		BeanUtils.copyProperties(model, myForm);
		model.setSjdm(sjdm);
		model.setNj(nj);
		
		String jgbh=myForm.getJgmc();
		String sfzld=myForm.getBzdm();
		String zdy = myForm.getZdy();
//		String xb = myForm.getXb();
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if ("save".equalsIgnoreCase(act)) {
				boolean bJg = service.saveNblgJxjz(model, "add", request);
				// =========begin 骆嘉伟 2009/4/2 ===============
				boolean jg = true;
				boolean ls = true;
				if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
					jg = StandardOperation.delete("nblg_jgxx", "jgbh||sfzld",
							jgbh + sfzld, request);
				}
				if ((jgbh != null && !"".equalsIgnoreCase(jgbh)) && jg) {
					// String[] str = service.getJgOne(jgbh);
					jg = StandardOperation.insert("nblg_jgxx", new String[] {
							"jgbh", "sfzld" }, new String[] { jgbh, sfzld },
							request);
				}
				if (zdy != null && !"".equalsIgnoreCase(zdy)) {
					ls = StandardOperation.delete("nblg_lsxx", "jsdm||sfzld",
							zdy + sfzld, request);
				}
				if ((zdy != null && !"".equalsIgnoreCase(zdy)) && ls) {
					// String[] str = service.getJgOne(jgbh);
					ls = StandardOperation.insert("nblg_lsxx", new String[] {
							"jsdm", "sfzld" }, new String[] { zdy, sfzld },
							request);
				}
				if (bJg && jg && ls) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
				// =========begin 骆嘉伟 2009/4/2 ===============
			}
			rs = service.getNblgJxjzxx(model.getBzdm(), model.getNj());
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			if ("save".equalsIgnoreCase(act)) {
				boolean bJg = service.saveXbmzJxjz(model, "add", request);
				if (bJg) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			}
			rs = service.getXbmzJxjzxx(model.getBzdm(), model.getNj());
		}
		
		if (rs.size() == 0){
			rs.put("nj", myForm.getNj());
			int i = Integer.valueOf(sjdj)+1;
			rs.put("bzdj", String.valueOf(i));
			String sT = "";
			switch (i) {
			case 1:
				sT = "营级";
				break;
			case 2:
				sT = "连级";
				break;
			case 3:
				sT = "排级";
				zdy=service.getLsOne(sjdm);
				rs.put("zdy", zdy);
				break;
			case 4:
				sT = "班级";
				break;
			default:
				break;
			}
			rs.put("bzdjmc", sT);
		}
		rs.put("ssjz", ssjz);
		rs.put("sjdm", sjdm);
		String bzdm = "";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			bzdm = JxglDataAccessAction.getJxbzdm();
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
			bzdm = JxglDataAccessAction.getXbmz_Jxbzdm();
		}
		rs.put("bzdm", bzdm);
		// =========begin 骆嘉伟 2009/4/1 ===============
		// 获得带队老师信息
		List<HashMap<String, String>> lsList = service.getLsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		request.setAttribute("lsList", lsList);
		request.setAttribute("jgList", jgList);
		// =========end 骆嘉伟 2009/4/1 ===============
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		return mapping.findForward("addQtjz");
	}
	
	public ActionForward modJxjz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		String title = "军训管理-军训编制－修改建制信息";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		JxglForm myForm = (JxglForm) form;
		JxglJzService service = new JxglJzService();
		String act = request.getParameter("act");
		String bzdm = Base.chgNull(request.getParameter("bzdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		DAO dao = DAO.getInstance();
		
		NblgJxjzModel model = new NblgJxjzModel();
		BeanUtils.copyProperties(model, myForm);
		model.setNj(nj);
		model.setBzdm(bzdm);
		
		if ("save".equalsIgnoreCase(act)){
			boolean bJg = service.saveNblgJxjz(model, "mod", request);
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		
		rs = service.getNblgJxjzxx(model.getBzdm(), model.getNj());
		
		switch (Integer.valueOf(rs.get("bzdj"))) {
		case 2:
			myForm.setYjdm(rs.get("sjdm"));
			request.setAttribute("getNblgYjList", service.getNblgYjList(nj));
			break;
		case 3:
			String yjdm = dao.getOneRs("select (select b.sjdm from nblg_jxbzdmb b where b.bzdm=a.sjdm) yjdm from nblg_jxbzdmb a where bzdm=?", new String[]{model.getBzdm()}, "yjdm");
			myForm.setYjdm(yjdm);
			myForm.setLjdm(rs.get("sjdm"));
			request.setAttribute("getNblgYjList", service.getNblgYjList(nj));
			request.setAttribute("getNblgLjList", service.getNblgLjList(yjdm,nj));
			break;
		case 4:
			yjdm = dao.getOneRs("select (select (select c.sjdm from nblg_jxbzdmb c where c.bzdm=b.sjdm) from nblg_jxbzdmb b where b.bzdm=a.sjdm) yjdm from nblg_jxbzdmb a where bzdm=?", new String[]{model.getBzdm()}, "yjdm");
			myForm.setYjdm(yjdm);
			String ljdm = dao.getOneRs("select (select b.sjdm from nblg_jxbzdmb b where b.bzdm=a.sjdm) ljdm from nblg_jxbzdmb a where bzdm=?", new String[]{model.getBzdm()}, "ljdm");
			myForm.setLjdm(ljdm);
			myForm.setPjdm(rs.get("sjdm"));
			request.setAttribute("getNblgYjList", service.getNblgYjList(nj));
			request.setAttribute("getNblgLjList", service.getNblgLjList(yjdm,nj));
			request.setAttribute("getNblgPjList", service.getNblgPjList(ljdm,nj));
			break;
		default:
			break;
		}
		

		// =========begin 骆嘉伟 2009/4/3 ===============
		// 获得带队老师信息
		List<HashMap<String, String>> lsList = service.getLsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		request.setAttribute("lsList", lsList);
		request.setAttribute("jgList", jgList);
		rs.put("zdy", service.getLsOne(rs.get("bzdm")));
		rs.put("jgmc", service.getJgOne(rs.get("jgmc")));
		// =========end 骆嘉伟 2009/4/3 ===============
		
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		return mapping.findForward("modJxjz");
	}
	
	public ActionForward modXbmzJxjz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		String title = "军训管理-军训编制－修改建制信息";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		JxglForm myForm = (JxglForm) form;
		JxglJzService service = new JxglJzService();
		String act = request.getParameter("act");
		String bzdm = Base.chgNull(request.getParameter("bzdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		DAO dao = DAO.getInstance();
		
		NblgJxjzModel model = new NblgJxjzModel();
		BeanUtils.copyProperties(model, myForm);
		model.setNj(nj);
		model.setBzdm(bzdm);
		
		if ("save".equalsIgnoreCase(act)){
			boolean bJg = service.saveXbmzJxjz(model, "mod", request);
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		
		rs = service.getXbmzJxjzxx(model.getBzdm(), model.getNj());
		
		switch (Integer.valueOf(rs.get("bzdj"))) {
		case 2:
			myForm.setYjdm(rs.get("sjdm"));
			request.setAttribute("getNblgYjList", service.getXbmzYjList(nj));
			break;
		case 3:
			String yjdm = dao.getOneRs("select (select b.sjdm from XBMZ_JXBZDMB b where b.bzdm=a.sjdm) yjdm from XBMZ_JXBZDMB a where bzdm=?", new String[]{model.getBzdm()}, "yjdm");
			myForm.setYjdm(yjdm);
			myForm.setLjdm(rs.get("sjdm"));
			request.setAttribute("getNblgYjList", service.getXbmzYjList(nj));
			request.setAttribute("getNblgLjList", service.getXbmzLjList(yjdm,nj));
			break;
		case 4:
			yjdm = dao.getOneRs("select (select (select c.sjdm from nblg_jxbzdmb c where c.bzdm=b.sjdm) from nblg_jxbzdmb b where b.bzdm=a.sjdm) yjdm from nblg_jxbzdmb a where bzdm=?", new String[]{model.getBzdm()}, "yjdm");
			myForm.setYjdm(yjdm);
			String ljdm = dao.getOneRs("select (select b.sjdm from nblg_jxbzdmb b where b.bzdm=a.sjdm) ljdm from nblg_jxbzdmb a where bzdm=?", new String[]{model.getBzdm()}, "ljdm");
			myForm.setLjdm(ljdm);
			myForm.setPjdm(rs.get("sjdm"));
			request.setAttribute("getNblgYjList", service.getNblgYjList(nj));
			request.setAttribute("getNblgLjList", service.getNblgLjList(yjdm,nj));
			request.setAttribute("getNblgPjList", service.getNblgPjList(ljdm,nj));
			break;
		default:
			break;
		}
		

		// =========begin 骆嘉伟 2009/4/3 ===============
		// 获得带队老师信息
		List<HashMap<String, String>> lsList = service.getLsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		request.setAttribute("lsList", lsList);
		request.setAttribute("jgList", jgList);
		rs.put("zdy", service.getXbmzXx(rs.get("bzdm")).get("zdy"));
		rs.put("jgmc", service.getXbmzXx(rs.get("bzdm")).get("jgmc"));
		// =========end 骆嘉伟 2009/4/3 ===============
		
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		return mapping.findForward("modJxjz");
	}
	
	public ActionForward xbmzJxbj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//TODO
		DAO dao = DAO.getInstance();
		JxglForm myForm = (JxglForm) form;
		String sql;
		String inputSQL[];
		String outputSQL[];
		String nj = request.getParameter("nj");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String sjdm = Base.chgNull(request.getParameter("sjdm"), "", 1);
		String xb = dao.getOneRs("select xb from XBMZ_JXBZDMB where bzdm = ?", new String[]{sjdm}, "xb");
		String ssjz = Base.chgNull(request.getParameter("ssjz"), "", 1);
		String act = request.getParameter("act");
		String jgbh=DealString.toGBK(myForm.getJgmc());
//		String sfzld=myForm.getBzdm();
		String zdy = DealString.toGBK(myForm.getZdy());
//		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
//		String[] tmp;
		xydm = Base.chgNull(xydm, "%", 1);
		nj = Base.chgNull(nj, "", 1);
		zydm = Base.chgNull(zydm, "%", 1);

		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {

			boolean ok = false;;

			String[] bjdm = myForm.getBjdmList();
			
			if (bjdm == null || Base.isNull(sjdm)) {
				String[] bjT = dao.getArray("select bzdm from XBMZ_JXBZDMB where sjdm=? and nj=?", new String[]{sjdm,nj}, "bzdm");
				
				if (null != bjT) {
					StringBuffer delSQL = new StringBuffer("delete jxgl_jxmdb a where a.nd=? and exists (select 1 from view_xsjbxx b where b.xb=? and a.xh=b.xh and b.bjdm in (''");
					for (int i = 0; i < bjT.length; i++){
						delSQL.append(",'");
						delSQL.append(bjT[i]);
						delSQL.append("'");
					}
					delSQL.append("))");
					
					dao.runUpdate(delSQL.toString(), new String[] { nj,xb });
				}
				
				sql = "delete from XBMZ_JXBZDMB where sjdm=? and nj = ? and xb = ?";
				ok = dao.runUpdate(sql, new String[] { sjdm,nj,xb });
			} else {
				
//				StringBuffer bjSQL = new StringBuffer("select count(distinct(xydm)) count from view_njxyzybj where bjdm in (''");
//				for (int i = 0; i < bjdm.length; i++) {
//					bjSQL.append(",'");
//					bjSQL.append(bjdm[i]);
//					bjSQL.append("'");
//				}
//				bjSQL.append(")");
				//String count=dao.getOneRs(bjSQL.toString(), new String[]{}, "count");
//				if (count != null && !"".equals(count)) {
//					if (Integer.parseInt(count) > 0) {
//						HashMap<String, String> rs = new HashMap<String, String>();
//						sql = "select xydm from view_njxyzybj where bjdm in (select bzdm from XBMZ_JXBZDMB"
//								+ " where sjdm in (select bzdm from XBMZ_JXBZDMB where sjdm in (select bzdm from XBMZ_JXBZDMB"
//								+ " where bzdm in (select sjdm from XBMZ_JXBZDMB where bzdm = ?))))";
//						String[] bzdm = dao.getArray(
//								sql,
//								new String[] { sjdm }, "xydm");
//						if (bzdm.length != 0) {
//							boolean flg = false;
//							String bj = bzdm[0];
//							StringBuffer bj1SQL = new StringBuffer(
//									"select distinct(xydm) from view_njxyzybj where bjdm in (''");
//							for (int i = 0; i < bjdm.length; i++) {
//								bj1SQL.append(",'");
//								bj1SQL.append(bjdm[i]);
//								bj1SQL.append("'");
//							}
//							bj1SQL.append(")");
//							String[] xy1 = dao.getArray(bj1SQL.toString(),
//									new String[] {}, "xydm");
//							for (int i = 0; i < xy1.length; i++) {
//								if (!bj.equals(xy1[i])) {
//									flg = true;
//								}
//							}
//
//							if (flg) {
//								String msg = "保存失败，请确认选择班级所属学院与此前所分配的班级属于同一学院";
//								JxglJzService service = new JxglJzService();
//								HashMap<String, String> rs1 = new HashMap<String, String>();
//								HashMap<String, String> LdInfo = new HashMap<String, String>();
//								sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where a.nj=? and"
//										+ " xydm like ? and zydm like ? and not exists (select 1 from "
//										+ "XBMZ_JXBZDMB b where a.bjdm = b.bzdm and b.nj = ? and b.xb = ?)";
//								inputSQL = new String[] { nj, xydm, zydm, nj,
//										xb };
//								outputSQL = new String[] { "bjdm", "bjmc" };
//								List bjList = dao.getList(sql, inputSQL,
//										outputSQL);
//
//								sql = "select bzdm bjdm,bzmc bjmc from XBMZ_JXBZDMB where sjdm=? and nj = ? and xb = ?";
//								inputSQL = new String[] { sjdm, nj, xb };
//								outputSQL = new String[] { "bjdm", "bjmc" };
//								List ldBjList = dao.getList(sql, inputSQL,
//										outputSQL);
//								rs.put("zdy", service.getXbjsOne(sjdm));
//								rs.put("jgmc", service.getXbjgOne(sjdm));
//								request.setAttribute("rs", rs1);
//								String xy = myForm.getXydm();
//								xy = (xy == null) ? "" : xy;
//								request.setAttribute("xydm", xy);
//								request
//										.setAttribute("njList", Base
//												.getNjList());
//								request.setAttribute("LdInfo", LdInfo);
//								request.setAttribute("nj", nj);
//								request.setAttribute("sjdm", sjdm);
//								request.setAttribute("ssjz", ssjz);
//								request.setAttribute("bjList", bjList);
//								request.setAttribute("ldBjList", ldBjList);
//								request
//										.setAttribute("xyList", Base
//												.getXyList());// 发送学院列表
//								if (xydm.equalsIgnoreCase("%")) {
//									xydm = "";
//								}
//								request.setAttribute("zyList",
//										(Base.getZyMap()).get(xydm));
//								request.setAttribute("msg", msg);
//								request.setAttribute("xb", xb);
//								return mapping.findForward("xbmzJxbj");
//							}
//						}
//					}
//					if (Integer.parseInt(count) > 1) {
//						HashMap<String, String> rs = new HashMap<String, String>();
//						String msg = "保存失败，请确认选择班级是属于同一学院";
//						JxglJzService service = new JxglJzService();
//						HashMap<String, String> rs1 = new HashMap<String, String>();
//						HashMap<String, String> LdInfo = new HashMap<String, String>();
//						sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where a.nj=? and"
//								+ " xydm like ? and zydm like ? and not exists (select 1 from "
//								+ "XBMZ_JXBZDMB b where a.bjdm = b.bzdm and b.nj = ? and b.xb = ?)";
//						inputSQL = new String[] { nj, xydm, zydm, nj, xb };
//						outputSQL = new String[] { "bjdm", "bjmc" };
//						List bjList = dao.getList(sql, inputSQL, outputSQL);
//
//						sql = "select bzdm bjdm,bzmc bjmc from XBMZ_JXBZDMB where sjdm=? and nj = ? and xb = ?";
//						inputSQL = new String[] { sjdm, nj, xb };
//						outputSQL = new String[] { "bjdm", "bjmc" };
//						List ldBjList = dao.getList(sql, inputSQL, outputSQL);
//						rs.put("zdy", service.getXbjsOne(sjdm));
//						rs.put("jgmc", service.getXbjgOne(sjdm));
//						request.setAttribute("rs", rs1);
//						String xy = myForm.getXydm();
//						xy = (xy == null) ? "" : xy;
//						request.setAttribute("xydm", xy);
//						request.setAttribute("njList", Base.getNjList());
//						request.setAttribute("LdInfo", LdInfo);
//						request.setAttribute("nj", nj);
//						request.setAttribute("sjdm", sjdm);
//						request.setAttribute("ssjz", ssjz);
//						request.setAttribute("bjList", bjList);
//						request.setAttribute("ldBjList", ldBjList);
//						request.setAttribute("xyList", Base.getXyList());// 发送学院列表
//						if (xydm.equalsIgnoreCase("%")) {
//							xydm = "";
//						}
//						request.setAttribute("zyList", (Base.getZyMap())
//								.get(xydm));
//						request.setAttribute("msg", msg);
//						request.setAttribute("xb", xb);
//						return mapping.findForward("xbmzJxbj");
//					}
//				}
				
				StringBuffer delSQL = new StringBuffer(
						"delete jxgl_jxmdb a where a.nd=? and exists (select 1 from view_xsjbxx b where b.xb=? and a.xh=b.xh and b.bjdm in (''");
				for (int i = 0; i < bjdm.length; i++) {
					delSQL.append(",'");
					delSQL.append(bjdm[i]);
					delSQL.append("'");
				}
				delSQL.append("))");

				dao.runUpdate(delSQL.toString(), new String[] { nj, xb });

				sql = "delete from XBMZ_JXBZDMB where sjdm=? and nj = ? and xb=?";
				dao.runUpdate(sql, new String[] { sjdm, nj, xb });
				String[] sqlT = new String[bjdm.length * 2];

//				JxglDAO nblg_dao = new JxglDAO();

				int n = 0;

				for (int i = 0; i < bjdm.length; i++) {
					sqlT[n] = "insert into jxgl_jxmdb(xh,nd,ldbh) (select xh,'"
							+ nj + "','" + sjdm
							+ "' from view_xsjbxx where xb='" + xb
							+ "' and bjdm='" + bjdm[i] + "')";
					n++;

					String bjmc = dao.getOneRs(
							"select bjmc from view_njxyzybj where bjdm=?",
							new String[] { bjdm[i] }, "bjmc");

					sqlT[n] = "insert into XBMZ_JXBZDMB(nj,bzdm,bzmc,bzdj,jgmc,zdy,sjdm,xb) values('"
							+ nj
							+ "','"
							+ bjdm[i]
							+ "','"
							+ bjmc
							+ "','4','"
							+ jgbh
							+ "','"
							+ zdy
							+ "','"
							+ sjdm
							+ "','"
							+ xb
							+ "')";
					n++;
				}

				int[] res = dao.runBatch(sqlT);
				for (int i = 0; i < res.length; i++) {
					ok = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!ok)
						break;
				}
			}

			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a, (select t.bjdm, count(t.xh) num"
				+ " from view_xsjbxx t where t.xb = ? group by t.bjdm) c where a.nj=? and"
				+ " xydm like ? and zydm like ? and a.bjdm = c.bjdm and not exists (select 1 from "
				+ " XBMZ_JXBZDMB b where a.bjdm = b.bzdm and b.nj = ? and b.xb = ?)";
		inputSQL = new String[] { xb,nj, xydm, zydm, nj,xb };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List bjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select bzdm bjdm,bzmc bjmc from XBMZ_JXBZDMB where sjdm=? and nj = ? and xb = ?";
		inputSQL = new String[] { sjdm,nj,xb };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List ldBjList = dao.getList(sql, inputSQL, outputSQL);
		HashMap<String, String> LdInfo = new HashMap<String, String>();
		// =========begin 骆嘉伟 2009/4/1 ===============
		JxglJzService service = new JxglJzService();	
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("zdy", service.getXbjsOne(sjdm));
		rs.put("jgmc", service.getXbjgOne(sjdm));
		request.setAttribute("rs", rs);
		String xy = myForm.getXydm();
		xy = (xy == null) ? "" : xy;
		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		// =========end 骆嘉伟 2009/4/1 ===============
		request.setAttribute("LdInfo", LdInfo);
		request.setAttribute("nj", nj);
		request.setAttribute("xb", xb);
		request.setAttribute("sjdm", sjdm);
		request.setAttribute("ssjz", ssjz);
		request.setAttribute("bjList", bjList);
		request.setAttribute("ldBjList", ldBjList);
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		if(xydm.equalsIgnoreCase("%")){
			xydm = "";
		}
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		return mapping.findForward("xbmzJxbj");
	}
	
	public ActionForward nblgJxbj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//TODO
		DAO dao = DAO.getInstance();
		JxglForm myForm = (JxglForm) form;
		String sql;
		String inputSQL[];
		String outputSQL[];
		String nj = request.getParameter("nj");
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String sjdm = Base.chgNull(request.getParameter("sjdm"), "", 1);
		String ssjz = Base.chgNull(request.getParameter("ssjz"), "", 1);
		String act = request.getParameter("act");
		String jgbh=myForm.getJgmc();
//		String sfzld=myForm.getBzdm();
		String zdy = myForm.getZdy();
//		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
//		String[] tmp;
		xydm = Base.chgNull(xydm, "%", 1);
		nj = Base.chgNull(nj, "", 1);
		zydm = Base.chgNull(zydm, "%", 1);

		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {

			boolean ok = false;;

			String[] bjdm = myForm.getBjdmList();
			if (bjdm == null || Base.isNull(sjdm)) {
				String[] bjT = dao.getArray("select bzdm from nblg_jxbzdmb where sjdm=? and nj=?", new String[]{sjdm,nj}, "bzdm");
				
				if (null != bjT) {
					StringBuffer delSQL = new StringBuffer("delete jxgl_jxmdb a where a.nd=? and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.bjdm in (''");
					for (int i = 0; i < bjT.length; i++){
						delSQL.append(",'");
						delSQL.append(bjT[i]);
						delSQL.append("'");
					}
					delSQL.append("))");
					
					dao.runUpdate(delSQL.toString(), new String[] { nj });
				}
				
				sql = "delete from nblg_jxbzdmb where sjdm=? and nj = ?";
				ok = dao.runUpdate(sql, new String[] { sjdm,nj });
			} else {
				
				
					StringBuffer delSQL = new StringBuffer("delete jxgl_jxmdb a where a.nd=? and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.bjdm in (''");
					for (int i = 0; i < bjdm.length; i++){
						delSQL.append(",'");
						delSQL.append(bjdm[i]);
						delSQL.append("'");
					}
					delSQL.append("))");
					
					dao.runUpdate(delSQL.toString(), new String[] { nj });
				
				sql = "delete from nblg_jxbzdmb where sjdm=? and nj = ?";
				dao.runUpdate(sql, new String[] { sjdm,nj });
				String[] sqlT = new String[bjdm.length*6];
				
				JxglDAO nblg_dao = new JxglDAO();
				String zdymc = nblg_dao.getLsXm(zdy);
				String jgmc = nblg_dao.getJgXm(jgbh);
				int n = 0;
				
				for (int i = 0; i < bjdm.length; i++) {
					sqlT[n] = "insert into jxgl_jxmdb(xh,nd,ldbh) (select xh,'"+nj+"','"+sjdm+"' from view_xsjbxx where bjdm='"+bjdm[i]+"')";
					n++;
					
					String bjmc = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?", new String[]{bjdm[i]}, "bjmc");
					
					sqlT[n] = "insert into nblg_jxbzdmb(nj,bzdm,bzmc,bzdj,jgmc,zdy,sjdm) values('"
							+ nj
							+ "','"
							+ bjdm[i]
							+ "','"
							+ bjmc
							+ "','4','"
							+ jgmc + "','" + zdymc + "','" + sjdm + "')";
					n++;
					
					if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
						sqlT[n] = "delete from nblg_jgxx where jgbh||sfzld = '"
								+ jgbh + bjdm[i] + "'";
						n++;
						sqlT[n] = "insert into nblg_jgxx(jgbh,sfzld) values('"
								+ jgbh + "','" + bjdm[i] + "')";
						n++;
					}

					if (zdy != null && !"".equalsIgnoreCase(zdy)) {
						sqlT[n] = "delete from nblg_lsxx where jsdm||sfzld = '"
								+ zdy + bjdm[i] + "'";
						n++;
						sqlT[n] = "insert into nblg_lsxx(jsdm,sfzld) values('"
								+ zdy + "','" + bjdm[i] + "')";
						n++;
					}
				}
				
				int[] res = dao.runBatch(sqlT);
				for(int i=0;i<res.length;i++){
					ok = (res[i]==Statement.EXECUTE_FAILED)?false:true;
					if(!ok) break;
				}
			}

			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where a.nj=? and"
			+ " xydm like ? and zydm like ? and not exists (select 1 from "
			+ "nblg_jxbzdmb b where a.bjdm = b.bzdm and b.nj = ? )";
		inputSQL = new String[] { nj, xydm, zydm, nj };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List bjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select bzdm bjdm,bzmc bjmc from nblg_jxbzdmb where sjdm=? and nj = ?";
		inputSQL = new String[] { sjdm,nj };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List ldBjList = dao.getList(sql, inputSQL, outputSQL);
		HashMap<String, String> LdInfo = new HashMap<String, String>();
		// =========begin 骆嘉伟 2009/4/1 ===============
		JxglJzService service = new JxglJzService();
		// 获得带队老师信息
		List<HashMap<String, String>> lsList = service.getLsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		request.setAttribute("lsList", lsList);
		request.setAttribute("jgList", jgList);
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("zdy", service.getLsOne(sjdm));
		request.setAttribute("rs", rs);
		String xy = myForm.getXydm();
		xy = (xy == null) ? "" : xy;
		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		// =========end 骆嘉伟 2009/4/1 ===============
		request.setAttribute("LdInfo", LdInfo);
		request.setAttribute("nj", nj);
		request.setAttribute("sjdm", sjdm);
		request.setAttribute("ssjz", ssjz);
		request.setAttribute("bjList", bjList);
		request.setAttribute("ldBjList", ldBjList);
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		if(xydm.equalsIgnoreCase("%")){
			xydm = "";
		}
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		return mapping.findForward("nblgJxbj");
	}

	public ActionForward jxjzPrintAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		JxglJzService service = new JxglJzService();
//		JxglJzModel myModel =new JxglJzModel();
//		String[] colList = new String[] { "bzmc1", "num1", "jgmc1", "zdy1",
//				"bzmc2", "num2", "num3", "jgmc2", "zdy2", "jgmc3" };
//		String tableName = "view_xbmz_bztj";
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		Vector<Object> rs = new Vector<Object>();
//		
//		List topTr = service.getTopTr(tableName, colList);	
//		List rsList = service.getJxjzPrintList1(myModel);
//		List rs1 = service.getJxjzPrintList2(myModel);
//		
//		map.put("rsList", rsList);
//		rs.add(map);  			
//		
//		request.setAttribute("topTr", topTr);
//		request.setAttribute("rs", rs);
//		request.setAttribute("rs1", rs1);
//		return mapping.findForward("JxbjPrint");
		JxglJzService service = new JxglJzService();
//		String xxdm = StandardOperation.getXxdm();
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/jxjzb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printPayReport(wwb);
		
		return mapping.findForward("");
	}
	
	public ActionForward xbmz_jxrc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		HttpSession session = request.getSession();
		ArrayList<String[]> rs = null;
		JxglJzModel myModel =new JxglJzModel();
		JxglJzService service = new JxglJzService();
		JxglForm myForm = (JxglForm) form;
		
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "pk","jxrq", "sj", "nr", "dd", "zzz" };
			String tableName = "view_xbmz_jxrc";
			// 取得表头
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			rs = service.getJxrqList(myModel);
			// 页面返回保证姓名不乱码
			//myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		return mapping.findForward("xbmzJxrc");
	}
	
	public ActionForward xbmzjxrcAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJzModel myModel =new JxglJzModel();
		JxglJzService service = new JxglJzService();
		JxglForm myForm = (JxglForm) form;

		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if ((request.getParameter("add") != null)
				&& request.getParameter("add").equalsIgnoreCase("add")) {
			boolean flg=service.saveXbmzJxrc(myModel, request);
			if (flg) {
				request.setAttribute("result", "yes");
			}else{
				request.setAttribute("result", "no");
			}
		}

		return mapping.findForward("xbmzJxrcOne");
	}
	
	public ActionForward xbmzjxrcEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJzModel myModel =new JxglJzModel();
		JxglJzService service = new JxglJzService();
		JxglForm myForm = (JxglForm) form;

		String pk = rowidToPK(request.getParameter("pk"));
		// 取得查询结果
		HashMap<String, String> rs = service.getXbmzJxrc(pk);
		
		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if ((request.getParameter("save") != null)
				&& request.getParameter("save").equalsIgnoreCase("save")) {
			boolean flg=service.saveXbmzJxrc2(myModel, pk,request);
			if (flg) {
				request.setAttribute("result", "yes");
			}else{
				request.setAttribute("result", "no");
			}
		}
		request.setAttribute("pk", pk);
		request.setAttribute("rs", rs);
		return mapping.findForward("xbmzJxrcEdit");
	}
	
	public ActionForward xbmzjxrcDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJzService service = new JxglJzService();

		String pk = rowidToPK(request.getParameter("pk"));
		// 取得查询结果

		boolean flg = service.delXbmzJxrc(pk, request);
		if (flg) {
			request.setAttribute("result", "yes");
		} else {
			request.setAttribute("result", "no");
		}

		return mapping.findForward("xbmzJxrc");

	}
	
	public static String rowidToPK (String rowid)
	{
		if(rowid==null){
			rowid = "";
		}
		return rowid.replaceAll(" ", "+");
	}
	
//	 数据导出
	
	public ActionForward wszbDataExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String realTable = request.getParameter("realTable");
		String sql = "";
		String zd = "";
		String querry = " where 1=1";
		if ("jxgl_rwbmdjb".equals(realTable)) {
			zd = "*";
		}

		sql = "select " + zd + " from " + realTable + querry;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if (realTable == null) {
			Excel2Oracle.exportData(sql, realTable, response.getOutputStream());
		} else {
			Excel2Oracle.exportData(sql, realTable, response.getOutputStream());
		}
		return mapping.findForward("wszbDataExport");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward xbmz_yhsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> BmInfo = new HashMap<String, String>();
		//HttpSession session = request.getSession();
		String act = request.getParameter("act");
		JxglForm myForm = (JxglForm) form;
		
		String sql="";
		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {

			boolean ok = false;
			boolean ok1 = false;

			String delbm = request.getParameter("delbm");
			String[] delbm1 = delbm.split("!!SplitSignOne!!");
			String[] bmdm = myForm.getBjdmList();
			if (bmdm == null) {
				sql = "delete xbmz_bmshqx";
				ok = dao.runUpdate(sql, new String[] {});

			} else {

				StringBuffer delSQL1 = new StringBuffer(
						"delete yhqxb a where a.gnmkdm = 'N080702' and a.yhm in (select a.yhm from yhb b where b.yhm = a.yhm and b.szbm in (''");
				for (int i = 0; i < bmdm.length; i++) {
					delSQL1.append(",'");
					delSQL1.append(bmdm[i]);
					delSQL1.append("'");
				}
				if (delbm1.length != 0) {
					for (int i = 0; i < delbm1.length; i++) {
						delSQL1.append(",'");
						delSQL1.append(delbm1[i]);
						delSQL1.append("'");
					}
				}
				delSQL1.append("))");
				dao.runUpdate(delSQL1.toString(), new String[] {});

				StringBuffer delSQL2 = new StringBuffer(
						"delete yhqxb a where a.gnmkdm = 'N0807' and a.yhm in (select a.yhm from yhb b where b.yhm = a.yhm and b.szbm in (''");
				for (int i = 0; i < bmdm.length; i++) {
					delSQL2.append(",'");
					delSQL2.append(bmdm[i]);
					delSQL2.append("'");
				}
				if (delbm1.length != 0) {
					for (int i = 0; i < delbm1.length; i++) {
						delSQL2.append(",'");
						delSQL2.append(delbm1[i]);
						delSQL2.append("'");
					}
				}
				delSQL2.append("))");
				dao.runUpdate(delSQL2.toString(), new String[] {});

				StringBuffer delSQL3 = new StringBuffer(
						"delete yhqxb a where a.gnmkdm = 'N08' and a.yhm in (select a.yhm from yhb b where b.yhm = a.yhm and b.szbm in (''");
				for (int i = 0; i < bmdm.length; i++) {
					delSQL3.append(",'");
					delSQL3.append(bmdm[i]);
					delSQL3.append("'");
				}
				if (delbm1.length != 0) {
					for (int i = 0; i < delbm1.length; i++) {
						delSQL3.append(",'");
						delSQL3.append(delbm1[i]);
						delSQL3.append("'");
					}
				}
				delSQL3.append("))");
				dao.runUpdate(delSQL3.toString(), new String[] {});

				StringBuffer delSQL = new StringBuffer(
						"delete xbmz_bmshqx a where bmdm in (select a.bmdm from ZXBZ_XXBMDM b where a.bmdm=b.bmdm and b.bmdm in (''");
				for (int i = 0; i < bmdm.length; i++) {
					delSQL.append(",'");
					delSQL.append(bmdm[i]);
					delSQL.append("'");
				}
				if (delbm1.length != 0) {
					for (int i = 0; i < delbm1.length; i++) {
						delSQL.append(",'");
						delSQL.append(delbm1[i]);
						delSQL.append("'");
					}
				}
				delSQL.append("))");

				dao.runUpdate(delSQL.toString(), new String[] {});

				String[] sqlT = new String[bmdm.length];

				String bm = "";
				for (int i = 0; i < bmdm.length; i++) {

					String bmmc = dao.getOneRs(
							"select bmmc from ZXBZ_XXBMDM where bmdm=?",
							new String[] { bmdm[i] }, "bmmc");

					sqlT[i] = "insert into xbmz_bmshqx(bmdm,bmmc) values('"
							+ bmdm[i] + "','" + bmmc + "')";
					bm += ",'" + bmdm[i] + "'";
				}
				int[] res = dao.runBatch(sqlT);

				sql = "select yhm from yhb where szbm in(''" + bm + ")";
				List yhm = dao.getList(sql, new String[] {},
						new String[] { "yhm" });
				String[] sqlX = new String[yhm.size() * 3];
				int n = 0;
				for (int i = 0; i < yhm.size(); i++) {
					HashMap<String, String> map = (HashMap<String, String>) yhm
							.get(i);
					sqlX[n] = "insert into yhqxb (yhm, gnmkdm, dxq) values ('"
							+ map.get("yhm") + "','N08','1')";
					n++;
					sqlX[n] = "insert into yhqxb (yhm, gnmkdm, dxq) values ('"
							+ map.get("yhm") + "','N0807','1')";
					n++;
					sqlX[n] = "insert into yhqxb (yhm, gnmkdm, dxq) values ('"
							+ map.get("yhm") + "','N080702','1')";
					n++;
				}
				dao.runBatch(sqlX);

				for (int i = 0; i < res.length; i++) {
					ok = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
					if (!ok && !ok1)
						break;
				}
			}

			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		
		String[] inputSQL = new String[] {};
		String[] outputSQL = new String[] { "bmdm", "bmmc" };
		sql = "select distinct bmdm,bmmc from ZXBZ_XXBMDM"
				+ " where bmdm not in (select bmdm from xbmz_bmshqx) order by bmdm";
		List bmList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select distinct bmdm,bmmc from xbmz_bmshqx order by bmdm";
		List qxList = dao.getList(sql, inputSQL, outputSQL);

		request.setAttribute("BmInfo", BmInfo);
		request.setAttribute("bmList", bmList);
		request.setAttribute("qxList", qxList);
		
		return mapping.findForward("xbmzConf");
	}
	
	public ActionForward bmxxPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		sql = "select a.* from view_jxglXbmz_rwbmdjb  a where a.xh=?";
		String[] colList = dao
				.getColumnName("select * from view_jxglXbmz_rwbmdjb  where 1=2");
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
			if (!"".equalsIgnoreCase(map.get("zbbgsshsj"))
					&& null != map.get("zbbgsshsj")) {
				map.put("zbbgsshsj", dao.doForTime2(map.get("zbbgsshsj")));
			}
		}

		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("rs", map);

		return mapping.findForward("bmxxPrint");
	}
}
