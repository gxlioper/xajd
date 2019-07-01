
package xgxt.sxjy.action.szdw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.FdyglForm;
import xgxt.utils.dealDate;

public class FdyxgAction extends DispatchAction{
	SxjyDAO dao = new SxjyDAO();
	String[] colList = null;
	String[] colListCN = null;
	ArrayList<String[]> rs = new ArrayList<String[]>();
	String sql ="";
	String writeAble = null;
	String inputSQL[];
	String outputSQL[];
	
	public ActionForward fdybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询


		String userType;

		String userDep;

		String sUName;

		String logMsg;

//		String writeAble;

		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		userDep = session.getAttribute("userDep").toString();


		FdyglForm fdyForm = (FdyglForm) form;
		String sql;
		String inputSQL[];
		String outputSQL[];
		String bmdm = fdyForm.getXydm();
		String zgh = fdyForm.getZgh();
		String nj = fdyForm.getNj();
		String jsbmdm = fdyForm.getBmdm();
		String zydm = fdyForm.getZydm();
		String fdyxm = fdyForm.getFdyxm();
		String act = request.getParameter("act");
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String[] tmp;
		bmdm = Base.chgNull(bmdm, "%", 0);
		nj = Base.chgNull(nj, "%", 0);
		zydm = Base.chgNull(zydm, "%", 0);
		zgh = Base.chgNull(zgh, "", 0);
		fdyxm = Base.chgNull(fdyxm, "", 0);

		userDep = request.getSession().getAttribute("userDep")
		.toString();
		userType = dao.getUserType(userDep);
		if ("xy".equalsIgnoreCase(userType)) {
			fdyForm.setXydm(userDep);
			bmdm = userDep;
		}

		/** 保存 */
			boolean ok = false;

			String[] bjdm = fdyForm.getBjdm();
			if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {
			if (bjdm == null || Base.isNull(zgh)) {
				sql = "delete from bzrbbb where zgh=?";
				ok = dao.runUpdate(sql, new String[] { zgh });
				logMsg = "清空了代码为" + zgh + "的班主任名下的班级";
			} else {
				mapTmp = new HashMap<String, Object>();
				sql = "delete from bzrbbb where zgh=?";
				ok = dao.runUpdate(sql, new String[] { zgh });
				tmp = new String[] { zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
				logMsg = "维护班主任班级对照表，班主任代码为" + zgh + "名下的班级有";
				for (int i = 0; i < bjdm.length; i++) {
					mapTmp = new HashMap<String, Object>();
					sql = "insert into bzrbbb(zgh,bjdm) values(?,?)";
					tmp = new String[] { zgh, bjdm[i] };
					mapTmp.put("sqlTxt", sql);
					mapTmp.put("sqlVal", tmp);
					sqlToExe.add(mapTmp);
					logMsg += bjdm[i] + ",";
				}
				ok = dao.runUpdate(sqlToExe);
			}

			if (ok) {
				Base.log(request, logMsg, sUName);
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
			}

		sql = "select zgh,xm,xb from view_fdyxx where bmdm = ? order by zgh";
		inputSQL = new String[] { jsbmdm };
		outputSQL = new String[] { "zgh", "xm", "xb" };
		List fdyList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where a.nj like ? and"
			+ " a.xydm = ? and a.zydm like ? and not exists (select bjdm from "
			+ "bzrbbb b where a.bjdm = b.bjdm)";
		inputSQL = new String[] { nj, bmdm, zydm };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List bjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from bzrbbb b where b.zgh=? and b.bjdm=a.bjdm)";
		inputSQL = new String[] { zgh };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List fdyBjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select * from view_fdyxx where zgh=? and bmdm like ?";
		inputSQL = new String[] { zgh, jsbmdm };
		outputSQL = new String[] { "zgh", "xm", "xb", "lxdh", "zwmc", "zyzz",
		"bmmc" };
		HashMap<String, String> fdyInfo = dao.getMap(sql, inputSQL, outputSQL);
		if (fdyInfo == null) {
			fdyInfo = new HashMap<String, String>();
		}

		fdyForm.setFdyxm(fdyxm);
		
		request.setAttribute("bmList", dao.getBmList());
		request.setAttribute("fdyList", fdyList);
		request.setAttribute("bjList", bjList);
		request.setAttribute("fdyBjList", fdyBjList);
		request.setAttribute("fdyInfo", fdyInfo);
		request.setAttribute("njList",  Base.getNjList());// 发送年级列表
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		if(bmdm.equalsIgnoreCase("%")){
			bmdm = "";
		}
		request.setAttribute("zyList", (Base.getZyMap()).get(bmdm));// 发送专业列表
		return mapping.findForward("fdybb");
}
	public ActionForward fdykhjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userType = session.getAttribute("userType").toString();
		FdyglForm myForm = (FdyglForm) form;
		String bmdm = myForm.getBmdm();
		myForm.setBmdm(bmdm);
		String realTable = "szkhjgb";
		String title = "";
		String [] inputList = new String []{};
		writeAble = request.getParameter("writeAble");
		if(writeAble==null){
			writeAble = Base.getWriteAble(request);
		}
		String tableName = "view_szkhjgb";
		String query = " where 1 = 1 ";
		if(bmdm!=null&&!bmdm.equalsIgnoreCase("")){
			query += " and bmdm = '"+bmdm+"'";
		}
		String xn = DealString.toGBK(request.getParameter("xn"));
		if(xn!=null&&!xn.equalsIgnoreCase("")){
			query += " and xn = ? ";
			inputList = new String []{xn};
		}
		String zgh = DealString.toGBK(request.getParameter("zgh"));
		if(zgh!=null&&!zgh.equalsIgnoreCase("")){
			query += " and zgh = ? ";
			inputList = new String []{zgh};
		}
		String xm = DealString.toGBK(request.getParameter("xm"));
		if(zgh!=null&&!zgh.equalsIgnoreCase("")){
			query += " and xm like '%"+xm+"%' ";
		}
		colList = new String[] { "pk","zgh","xm","bmmc","lxdh","yddh","zcj","djmc"};
		title = "当前位置：思政队伍 - 思政考核 - 辅导员考核结果";
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,inputList,colList,"");
		}
		if((request.getAttribute("delete"))!=null){
			rs = dao.sxjyQuery(tableName,query,inputList,colList,"");
			request.setAttribute("delete", (String)request.getAttribute("delete"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", dao.getBmList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("bmdm", bmdm);
		request.setAttribute("xnList", dao.getXnndList());
		return mapping.findForward("fdykhjg");
	}
	
	public ActionForward jskhjgOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		String zgh = DealString.toGBK(request.getParameter("zgh"));
		String writeAble = "no";
		String xn = Base.currXn;
		if(pk.equalsIgnoreCase("")){
			writeAble = "yes";
		}
		String tableName = "view_szkhjgb";
		String [] colList = new String []{"pk","zgh","xn","zwmc","bmdm","bmmc","djdm","zcj","lxdh","zyzz","yddh","lrrq","xb"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		if(!zgh.equalsIgnoreCase("")){
			colList = new String []{"zgh","zwmc","bmmc","bmdm","lxdh","zyzz","yddh","xb"};
			rs = dao.sxjyQueryOne3("view_fdyxx", colList, "zgh", zgh, rs, sql);
		}
		if(rs.get("xn").equalsIgnoreCase("")){
			rs.put("xn", xn);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("djList", dao.getCommonList("szdwkhdjdmb", "djdm", "djmc", ""));
		request.setAttribute("bmList", dao.getBmList());
		request.setAttribute("bmdm", rs.get("bmdm"));
		request.setAttribute("xnlist", dao.getXnndList());
		return mapping.findForward("fdykhjgOne");
	}
	
	public ActionForward saveKhjgOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		String zgh = DealString.toGBK(request.getParameter("zgh"));
		String zcj = DealString.toGBK(request.getParameter("zcj"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		String djdm = DealString.toGBK(request.getParameter("djdm"));
		String xn = DealString.toGBK(request.getParameter("xn"));
		colList = new String[] { "zgh","xn","zcj","bz","djdm"};
		String [] inputList =  new String[] { zgh,xn,zcj,bz,djdm};
		boolean inserted = StandardOperation.delete("szkhjgb", "xn||zgh",pk, request);
		if(inserted){
			inserted = StandardOperation.insert("szkhjgb", colList, inputList, request);
		}
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("fdykhjgOne");
	}
	
	public ActionForward delKhjgOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean del = StandardOperation.delete("szkhjgb", "xn||zgh",pk, request);
		if(del){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return fdykhjg(mapping,form,request,response);
	}
	
	public ActionForward fdypx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userType = session.getAttribute("userType").toString();
		FdyglForm myForm = (FdyglForm) form;
		String bmdm = myForm.getBmdm();
		String xn = myForm.getXn();
		myForm.setBmdm(bmdm);
		String realTable = "szdwFdyPxb";
		String title = "";
		String [] inputList = new String []{};
		writeAble = request.getParameter("writeAble");
		if(writeAble==null){
			writeAble = Base.getWriteAble(request);
		}
		String tableName = "view_fdyPx";
		String query = " where 1 = 1 ";
		if(bmdm!=null&&!bmdm.equalsIgnoreCase("")){
			query += " and bmdm = '"+bmdm+"'";
		}
		String zgh = DealString.toGBK(request.getParameter("zgh"));
		if(zgh!=null&&!zgh.equalsIgnoreCase("")){
			query += " and zgh = ? ";
			inputList = new String []{zgh};
		}
		String xm = DealString.toGBK(request.getParameter("xm"));
		if(zgh!=null&&!zgh.equalsIgnoreCase("")){
			query += " and xm like '%"+xm+"%' ";
		}
		if(xn!=null&&!xn.equalsIgnoreCase("")){
			query += " and xn = '"+xn+"' ";
		}
		colList = new String[] { "pk","zgh","xm","bmmc","lxdh","yddh","pxqj","pxmc"};
		title = "当前位置：思政队伍 - 信息维护 - 辅导员培训信息";
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,inputList,colList,"");
		}
		if((request.getAttribute("delete"))!=null){
			rs = dao.sxjyQuery(tableName,query,inputList,colList,"");
			request.setAttribute("delete", (String)request.getAttribute("delete"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", dao.getBmList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("bmdm", bmdm);
		request.setAttribute("xnList", dao.getXnndList());
		return mapping.findForward("fdyPx");
	}
		public ActionForward fdypxOne(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			String pk = DealString.toGBK(request.getParameter("pk"));
			String zgh = DealString.toGBK(request.getParameter("zgh"));
			String writeAble = "no";
			String xn = Base.currXn;
			dealDate getDate = new dealDate();
			String time = "";
			if(pk.equalsIgnoreCase("")){
				writeAble = "yes";
			}else{
				time = getDate.getToday();
			}
			String tableName = "view_fdyPx";
			String [] colList = new String []{"pk","zgh","xn","zwmc","bmdm","bmmc","lxdh","zyzz","yddh","lrrq","xb","xxyj","bz","pxqj","pxmc","pxkssj","pxjssj","pxdd","pxbmc"};
			HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
			if(!zgh.equalsIgnoreCase("")){
				colList = new String []{"zgh","zwmc","bmmc","bmdm","lxdh","zyzz","yddh","xb"};
				rs = dao.sxjyQueryOne3("view_fdyxx", colList, "zgh", zgh, rs, sql);
			}
			if(rs.get("xn").equalsIgnoreCase("")){
				rs.put("xn", xn);
			}
			if((!rs.get("lrrq").equalsIgnoreCase(""))&&zgh.equalsIgnoreCase(rs.get("zgh"))){
				time = rs.get("lrrq");
			}
			String[] pxList = dao.getFdypxjlList(rs.get("zgh"), time);
			request.setAttribute("fdypxList", pxList);
			request.setAttribute("rs", rs);
			request.setAttribute("pk", pk);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("bmList", dao.getBmList());
			request.setAttribute("xnlist", dao.getXnndList());
			return mapping.findForward("fdyPxOne");
		}
		
		public ActionForward savefdypxOne(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			String pk = DealString.toGBK(request.getParameter("pk"));
			String zgh = DealString.toGBK(request.getParameter("zgh"));
			String pxqj = DealString.toGBK(request.getParameter("pxqj"));
			String xxyj = DealString.toGBK(request.getParameter("xxyj"));
			String xn = DealString.toGBK(request.getParameter("xn"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String pxmc = DealString.toGBK(request.getParameter("pxmc"));
			String pxkssj = DealString.toGBK(request.getParameter("pxkssj"));
			String pxjssj = DealString.toGBK(request.getParameter("pxjssj"));
			String pxdd = DealString.toGBK(request.getParameter("pxdd"));
			String pxbmc = DealString.toGBK(request.getParameter("pxbmc"));
			colList = new String[] { "zgh","xn","xxyj","bz","pxqj","pxmc","pxkssj","pxjssj","pxdd","pxbmc"};
			String [] inputList =  new String[] { zgh,xn,xxyj,bz,pxqj,pxmc,pxkssj,pxjssj,pxdd,pxbmc};
			boolean inserted = StandardOperation.delete("szdwFdyPxb", "xn||zgh||pxqj||pxmc",pk, request);
			if(inserted){
				inserted = StandardOperation.insert("szdwFdyPxb", colList, inputList, request);
			}
			if(inserted){
				request.setAttribute("inserted", "ok");
			}else{
				request.setAttribute("inserted", "no");
			}
			return mapping.findForward("fdyPxOne");
		}
		
		public ActionForward delFdypxOne(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			String pk = DealString.toGBK(request.getParameter("pk"));
			boolean del = StandardOperation.delete("szdwFdyPxb", "xn||zgh||pxqj||pxmc",pk, request);
			if(del){
				request.setAttribute("delete", "ok");
			}else{
				request.setAttribute("delete", "no");
			}
			return fdykhjg(mapping,form,request,response);
		}
	
	public List<HashMap<String, String>> getFdyList(String bmdm){
		String sql = "";
		if(bmdm.equalsIgnoreCase("%")){
			sql = "select 'zgh' dm,'--请选择--' mc from view_fdyxx where rownum='1' union all select zgh dm,xm mc from view_fdyxx";
			List<HashMap<String, String>> fdyList = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
			return fdyList;
		}else{
			sql = "select 'zgh' dm,'--请选择--' mc from view_fdyxx where rownum='1' union all select zgh dm,xm mc from view_fdyxx where bmdm = ? ";
			List<HashMap<String, String>> fdyList = dao.getList(sql, new String[]{bmdm}, new String[]{"dm","mc"});
			return fdyList;
		}
	}
}