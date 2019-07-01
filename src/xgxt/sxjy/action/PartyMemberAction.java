package xgxt.sxjy.action;
/*
 * 创建日期 2007-7-9 ls_zzj
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.CheckPower;
import xgxt.utils.StatisticAllAction;


public class PartyMemberAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
    	throws Exception {	
	ActionForward myActFwd = null;
	String myAct = mapping.getParameter();
	
	try {
  	  int i = Base.chkTimeOut(request.getSession());
  	  if (i <= 2) {
  	  request.setAttribute("errMsg", "登陆超时，请重新登陆！");
  	  return new ActionForward("/login.jsp", false);
  	}
  	 if ("partyMemberSave".equalsIgnoreCase(myAct)) {
	    // 党建模块保存
	    myActFwd = partyMemberSave(mapping, form, request, response);
	}else if("allStatistic".equalsIgnoreCase(myAct)){
		 StatisticAllAction  allStatistic = new StatisticAllAction();
		 myActFwd = allStatistic.allStatistic(mapping, form, request, response);
	}else if("djxgOne".equalsIgnoreCase(myAct)){
		 myActFwd = djxgOne(mapping, form, request, response);
	}else if("member_List".equalsIgnoreCase(myAct)){
		 myActFwd = member_List(mapping, form, request, response);
	}else if("member_Del".equalsIgnoreCase(myAct)){
		 myActFwd = member_Del(mapping, form, request, response);
	}else if("member_modi".equalsIgnoreCase(myAct)){
		 myActFwd = member_modi(mapping, form, request, response);
	}else if("party_stuinfo".equalsIgnoreCase(myAct)){
		 myActFwd = party_stuinfo(mapping, form, request, response);
	}
	return myActFwd;
	}
	catch (Exception e) {
	    e.printStackTrace();
	    request.setAttribute("errMsg", "数据连接中断，请重新登陆！");
	    return new ActionForward("/login.jsp", false);
	}
    }

	private ActionForward party_stuinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act = request.getParameter("act");
		if(act.equalsIgnoreCase("party")){
			String sql = "update xsxxb a set zzmm = '01' where exists (select xh from dyxxb b where a.xh = b.xh)";
			dao.runUpdate(sql,new String[] {});
			sql = "insert into xsxxb(xh,xm,xb,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjztm,csrq,syd,sfzh,mz,zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny)" +
			"(select xh,xm,xb,xymc,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjlb,csrq,syd,sfzh,mz,'01' zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny from view_xsxxb a "+
			" where exists (select xh from dyxxb b where a.xh = b.xh) and not exists (select xh from xsxxb c where a.xh = c.xh))";
			dao.runUpdate(sql,new String[] {});
			return mapping.findForward("success");
		}else{
			String sql = "update xsxxb a set zzmm = '02' where exists (select xh from ybdyxxb b where a.xh = b.xh)";
			dao.runUpdate(sql,new String[] {});
			sql = "insert into xsxxb(xh,xm,xb,xy,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjztm,csrq,syd,sfzh,mz,zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny)" +
			"(select xh,xm,xb,xymc,xydm,zymc,zydm,bjmc,bjdm,nj,xz,xjlb,csrq,syd,sfzh,mz,'02' zzmm,lxdh,sjhm,dzyx,kslb,rxfs,pyfs,pycc,jg,jtdz,jtyb,rxrq,kh,hkszd,sfzx,byny from view_xsxxb a "+
			" where exists (select xh from ybdyxxb b where a.xh = b.xh) and not exists (select xh from xsxxb c where a.xh = c.xh))";
			dao.runUpdate(sql,new String[] {});
			return mapping.findForward("ybdyxx");
		}
		
	}

	private ActionForward member_modi(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
//		CommanForm priseChkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String sql = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
			.toString());
		String xh= DealString.toGBK(request.getParameter("stu_id"));
		if(xh.equalsIgnoreCase("")){
			xh=request.getParameter("xh");
		}
		String[] colList;
		String xxdm = StandardOperation.getXxdm();
		String doType = DealString.toGBK(request.getParameter("doType"));
		HashMap<String, String> map = new HashMap<String, String>();
		colList = new String[] { "xh","xm","nj","xymc","zymc","bjmc","rtrq","bz", "xb","rtdd" };
		if(doType.equalsIgnoreCase("save")){
			boolean inserted = false;
			sql = "delete from tyxxb where xh = ?";
			inserted = dao.runUpdate(sql, new String []{xh});
			if(inserted){
				String rtrq = request.getParameter("rtrq");
				String rtdd = DealString.toGBK(request.getParameter("rtdd"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				sql = "insert into tyxxb (xh,rtrq,bz,rtdd) values (?,?,?,?)";
				inserted = dao.runUpdate(sql, new String []{xh,rtrq,bz,rtdd});
			}
			if(inserted){
				request.setAttribute("inserted", "ok");
			}else{
				request.setAttribute("inserted", "no");
			}
			for (int i = 0; i < colList.length; i++) {
	    		map.put(colList[i].toLowerCase(), "");
			}
		}else{
		sql = "select a.xh,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,a.rtrq,a.bz,a.xb,a.rtdd from view_tyxxb a where a.xh = ?";
		
	    String[] rs = dao.getOneRs(sql, new String[] {xh}, colList);
	    if (null!=rs) {
	    for (int i = 0; i < colList.length; i++) {
	    	rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
	    			: rs[i];
	    	map.put(colList[i].toLowerCase(), rs[i]);
	    }
	    }else{
	    	for (int i = 0; i < colList.length; i++) {
	    		map.put(colList[i].toLowerCase(), "");
			    }
	    }
		
		}
		map.put("nd",Base.currNd );
		request.setAttribute("rs", map);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("doType", doType);
	    request.setAttribute("userType", userType);
	    return new ActionForward("/szdw/member_One.jsp",false);
	}

	private ActionForward member_Del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		DAO dao = DAO.getInstance();
		CommanForm com = (CommanForm) form;
		HttpSession session = request.getSession();	
		boolean flag = false;
		String userSpecType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String xydm = com.getXydm();
		String zydm = com.getZydm();
		String bjdm = com.getBjdm();
		String nj = com.getNj();
		String writeAble = CheckPower.checkUsrPower(userName, "member_List.do")?"yes":"no";
		String oper = "del";
		String pk = request.getParameter("delPk");
		flag = StandardOperation.delete("tyxxb", "xh", pk, request);
		com.setXh("");			
		request.setAttribute("xydm", xydm);
		request.setAttribute("zydm", zydm);
		request.setAttribute("bjdm", bjdm);
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		request.setAttribute("njList",  Base.getNjList());// 发送年级列表
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// 发送班级列表
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userSpecType", userSpecType);
		request.setAttribute("result", flag);
		request.setAttribute("oper", oper);
		return mapping.findForward("success");
	}

	private ActionForward member_List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		 数据查询
		DAO dao = DAO.getInstance();
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();		
		String oper = request.getParameter("oper");
		String writeAble = Base.getWriteAble(request);
		
		boolean disabled = true;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句		
		String rsNum = "0";// 返回的记录数
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh();
		String xm = DealString.toGBK(dataSearchForm.getXm());
		
		dataSearchForm.setXm(xm);
		nj = (nj==null) ? "" : nj;
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		querry.append(xy==null ||"".equalsIgnoreCase(xy) ? "" : " and xydm='" + xy + "' ");
		querry.append(nj==null || "".equalsIgnoreCase(nj) ? "" : " and nj='" +nj + "' ");
		querry.append(zy==null || "".equalsIgnoreCase(zy) ? "" : " and zydm='" + zy + "' ");
		querry.append(bj==null || "".equalsIgnoreCase(bj) ? "" : " and bjdm='" + bj + "' ");
		querry.append(xh==null || "".equalsIgnoreCase(xh) ? "" : " and xh like '%"+xh+ "%' ");
		querry.append(xm==null || "".equalsIgnoreCase(xm) ? "" : " and xm like '%"+xm+"%' ");		
		if (userSpecType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			dataSearchForm.setXydm(xy);
			disabled = false;
		}		
		
		colList = new String[] { "xh","r" ,"xm", "nj","bjmc","rtrq"};				
		sql = "select * from (select * from (select distinct xh,rownum r, xm,nj,bjmc,rtrq from view_tyxxb " + querry + " ) where r<=" +
		(dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) + 
		") where r>" + dataSearchForm.getPages().getStart();
		colListCN = dao.getColumnNameCN(colList, "view_tyxxb");
		List topTr = dao.arrayToList(colList, colListCN);
		//查询
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {			
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}	
			//TODO 分页 
			sql = "select count(*) count from view_tyxxb " + querry;		
			dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sql, new String[]{}, "count")));
		}
		//判断权限
		if (userSpecType.equalsIgnoreCase("xy")&& (xy != null || !xy.equalsIgnoreCase(""))) {
			request.setAttribute("writeble", "no");
		} else if ("xx".equalsIgnoreCase(userSpecType)|| "admin".equalsIgnoreCase(userSpecType)) {
			request.setAttribute("writeble", "yes");
		}
		request.setAttribute("oper", oper);
		request.setAttribute("userSpecType", userSpecType);
		request.setAttribute("disabled", disabled);
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("njList",  Base.getNjList());// 发送年级列表
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));// 发送专业列表
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// 发送班级列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数				
		request.setAttribute("writeAble", writeAble);		
		request.setAttribute("xydm", xy);
		request.setAttribute("zydm", zy);
		request.setAttribute("bjdm", bj);
		return mapping.findForward("success");
	}

	private ActionForward djxgOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ActionForward newFwd = new ActionForward();
		CommanForm dataSearchForm = (CommanForm) form;
//		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String pk = request.getParameter("pk");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String realTable = request.getParameter("realTable");
		String doType = request.getParameter("doType");
		String sql = "";
		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[]{}, new String []{"xxmc"})[0];
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			dataSearchForm.setErrMsg("sdf");
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("save"))  {
			String xh =  request.getParameter("xh");
			String nd =  request.getParameter("nd");
			String xn =  request.getParameter("xn");
			String xq =  request.getParameter("xq");    
			if (realTable.equalsIgnoreCase("ybdyxxb")) {
			// 预备党员
				String pxkssj = request.getParameter("kssj")
				.replaceAll("-", "");
				String pxjssj = request.getParameter("jssj")
				.replaceAll("-", "");
				String bz = DealString.toGBK(request.getParameter("bz"));
				String lxr1 = DealString.toGBK(request.getParameter("lxr1"));
				String lxr2 = DealString.toGBK(request.getParameter("lxr2"));
				String rzqk = DealString.toGBK(request.getParameter("rzqk"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from rdjjfzxxb where xh =?";
					del = dao.runUpdate(sql, new String[] {xh});
					if(del){
				    sql = "delete from " + realTable
					    + " where xn=? and xq=? and xh=?";
				    del = dao.runUpdate(sql, new String[] { xn, xq, xh });
					}
				} else {
					sql = "delete from rdjjfzxxb where xh =?";
					del = dao.runUpdate(sql, new String[] {xh});
					if(del){
				    sql = "delete from " + realTable + " where "+ pk +" =?";
				    del = dao.runUpdate(sql, new String[] {pkValue});
					}
				}
				if (del) {
					if ("杭州职业技术学院".equalsIgnoreCase(xxmc)){
						sql = "insert into " + realTable + "(nd,xn,xq,xh,kssj,jssj,bz,lxr1,lxr2,rzqk) values(?,?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] {nd,xn,xq,xh,pxkssj,pxjssj,bz,lxr1,lxr2,rzqk});
					} else{
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,kssj,jssj,bz) values(?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
								pxkssj, pxjssj, bz });
					}
				} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			} else if (realTable.equalsIgnoreCase("dyxxb")) {
			// 党员信息
			String pxkssj = request.getParameter("rdsj")
			.replaceAll("-", "");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String rzqk = DealString.toGBK(request.getParameter("rzqk"));
			boolean del = false;
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
				sql = "delete from rdjjfzxxb where xh =?";
				del = dao.runUpdate(sql, new String[] {xh});
				if(del){
			    sql = "delete from " + realTable
				    + " where xn=? and xq=? and xh=?";
			    del = dao.runUpdate(sql, new String[] { xn, xq, xh });
				}
				if(del){
				    sql = "delete from ybdyxxb where xh =?";
				    del = dao.runUpdate(sql, new String[] {xh});
				}
			} else {
				sql = "delete from rdjjfzxxb where xh =?";
				del = dao.runUpdate(sql, new String[] {xh});
				if(del){
					    sql = "delete from ybdyxxb where xh =?";
					    del = dao.runUpdate(sql, new String[] {xh});
						}
				if(del){
			    sql = "delete from " + realTable + " where "+ pk +" =?";
			    del = dao.runUpdate(sql, new String[] {pkValue});
				}
			}
			if (del) {
				if("杭州职业技术学院".equalsIgnoreCase(xxmc)){
					sql = "insert into " + realTable + "(nd,xn,xq,xh,rdsj,bz,rzqk) values(?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {nd,xn,xq,xh,pxkssj,bz,rzqk});
				} else {
					sql = "insert into " + realTable
					+ "(nd,xn,xq,xh,rdsj,bz) values(?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, pxkssj,
							bz });
				}
			} else {
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
		}
		}
			return null;
	}

	private ActionForward partyMemberSave(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	CommanForm dataSearchForm = (CommanForm) form;
    	DAO dao = DAO.getInstance();
    	String pk = request.getParameter("pk");
    	String pkValue = DealString.toGBK(request.getParameter("pkValue"));
    	String realTable = request.getParameter("realTable");
    	String xh = dataSearchForm.getXh();
	    String nd = dataSearchForm.getNd();
	    String xn = dataSearchForm.getXn();
	    String xq = dataSearchForm.getXq();	 
	    String xxdm = StandardOperation.getXxdm();
	    
    	String sql = "";
    	boolean del = false;
		if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
			sql = "delete from rdjjfzxxb where xh =?";
			del = dao.runUpdate(sql, new String[] { xh });
			if (realTable.equalsIgnoreCase("dyxxb")) {
				if (del) {
					sql = "delete from ybdyxxb where xh =?";
					del = dao.runUpdate(sql, new String[] { xh });
				}
			}
			if (del) {
				sql = "delete from " + realTable
						+ " where xn=? and xq=? and xh=?";
				del = dao.runUpdate(sql, new String[] { xn, xq, xh });
			}
		} else {
			sql = "delete from rdjjfzxxb where xh =?";
			del = dao.runUpdate(sql, new String[] {xh});
			if (realTable.equalsIgnoreCase("dyxxb")){
				if(del){
				    sql = "delete from ybdyxxb where xh =?";
				    del = dao.runUpdate(sql, new String[] {xh});
					}
			}
			if(del){
		    sql = "delete from " + realTable + " where "+ pk +" =?";
		    del = dao.runUpdate(sql, new String[] {pkValue});
			}
		}
		if (del) {
			if (realTable.equalsIgnoreCase("ybdyxxb")) {
	 			// 预备党员
	 			String pxkssj = request.getParameter("kssj")
	 				.replaceAll("-", "");
	 			String pxjssj = request.getParameter("jssj")
	 				.replaceAll("-", "");
	 			String kssj = request.getParameter("gzkssj");

	 			String bz = DealString.toGBK(request.getParameter("bz"));
	 			String lxr1 = DealString.toGBK(request.getParameter("lxr1"));
	 			String kcqk = DealString.toGBK(request.getParameter("kcqk"));
	 			String dfjnqk = DealString.toGBK(request.getParameter("dfjnqk"));
	 			String thcs = DealString.toGBK(request.getParameter("thcs"));
	 			String sfkyzz = DealString.toGBK(request.getParameter("sfkyzz"));
	 			String cjzzxxqk = DealString.toGBK(request.getParameter("cjzzxxqk"));
	 			String xsccdm = DealString.toGBK(request.getParameter("xsccdm"));
	 			String ssbx1 = DealString.toGBK(request.getParameter("ssbx1"));
				String ssbx2= DealString.toGBK(request.getParameter("ssbx2"));
				String ssbx3 = DealString.toGBK(request.getParameter("ssbx3"));
				String ssbx4 = DealString.toGBK(request.getParameter("ssbx4"));
				//	============= begin 北京联合 骆嘉伟 2009/5/31===============
				if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
					String zzgxzx = DealString.toGBK(request
							.getParameter("zzgxzx"));
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,kssj,jssj,lxr1,bz,dfjnqk,kcqk,thcs,sfkyzz,cjzzxxqk,xsccdm,ssbx1,ssbx2,ssbx3,ssbx4,zzgxzx) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, pxkssj,
							pxjssj, lxr1, bz, dfjnqk, kcqk, thcs, sfkyzz,
							cjzzxxqk, xsccdm, ssbx1, ssbx2, ssbx3, ssbx4,
							zzgxzx });
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					String zygx = DealString
							.toGBK(request.getParameter("zygx"));
					String zybx = DealString
							.toGBK(request.getParameter("zybx"));
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,kssj,jssj,lxr1,bz,dfjnqk,kcqk,thcs,sfkyzz,cjzzxxqk,xsccdm,ssbx1,ssbx2,ssbx3,ssbx4,zygx,zybx) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, pxkssj,
							pxjssj, lxr1, bz, dfjnqk, kcqk, thcs, sfkyzz,
							cjzzxxqk, xsccdm, ssbx1, ssbx2, ssbx3, ssbx4, zygx,
							zybx });
				}else {
					if(Base.isNull(kssj)){
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,kssj,jssj,lxr1,bz,dfjnqk,kcqk,thcs,sfkyzz,cjzzxxqk,xsccdm,ssbx1,ssbx2,ssbx3,ssbx4) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, pxkssj,
							pxjssj, lxr1, bz, dfjnqk, kcqk, thcs, sfkyzz,
							cjzzxxqk, xsccdm, ssbx1, ssbx2, ssbx3, ssbx4});
					}else{

		 				sql="select count(*) num from dyxxb where xh=?";
		 				String num = dao.getOneRs(sql, new String[]{xh},"num");
		 				if ("0".equalsIgnoreCase(num)) {
							sql = "insert into dyxxb (xn,xq,xh,nd,zzsj,rdsj,xsccdm,dfjnqk,zzxxqk,ybdykssj,ybdyjssj) values(?,?,?,?,?,?,?,?,?,?,?)";
							dao.runUpdate(sql, new String[] { xn, xq, xh, nd, kssj,kssj,xsccdm,dfjnqk,cjzzxxqk,pxkssj,pxjssj});
						} else {
							sql = "update dyxxb set zzsj=?,rdsj=?,xsccdm=? where xh=? and xq=? and xn=?";
							dao.runUpdate(sql, new String[] { kssj,kssj,xsccdm,xh,xq,xn});
		 				}
		 			
					}
				}
				//	============= end 北京联合 骆嘉伟 2009/5/31===============
 			} else if (realTable.equalsIgnoreCase("dyxxb")) {
	 			// 党员
 				String bz = DealString.toGBK(request.getParameter("bz"));
	 			String rdsj = request.getParameter("rdsj")
	 				.replaceAll("-", "");
	 			String cjhdqk = DealString.toGBK(request.getParameter("cjhdqk"));
	 			String zbshqk = DealString.toGBK(request.getParameter("zbshqk"));
	 			String dfjnqk = DealString.toGBK(request.getParameter("dfjnqk"));
	 			String zzxxqk = DealString.toGBK(request.getParameter("zzxxqk"));
	 			String gxzcqx = DealString.toGBK(request.getParameter("gxzcqx"));
	 			String xsccdm = DealString.toGBK(request.getParameter("xsccdm"));
	 			String zzsj   = DealString.toGBK(request.getParameter("zzsj"));
	 			String ssbx1 = DealString.toGBK(request.getParameter("ssbx1"));
				String ssbx2= DealString.toGBK(request.getParameter("ssbx2"));
				String ssbx3 = DealString.toGBK(request.getParameter("ssbx3"));
				String ssbx4 = DealString.toGBK(request.getParameter("ssbx4"));
				String xl= DealString.toGBK(request.getParameter("xl"));
				String jg = DealString.toGBK(request.getParameter("jg"));
				String csd = DealString.toGBK(request.getParameter("csd"));
				String ybdykssj = DealString.toGBK(request.getParameter("ybdykssj"));
				String ybdyjssj = DealString.toGBK(request.getParameter("ybdyjssj"));
				
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					String zygx = DealString
							.toGBK(request.getParameter("zygx"));
					String zybx = DealString
							.toGBK(request.getParameter("zybx"));
//					String[] a = new String[] { nd, xn, xq, xh, rdsj, bz,
//							cjhdqk, zbshqk, dfjnqk, zzxxqk, gxzcqx, xsccdm,
//							zzsj, ssbx1, ssbx2, ssbx3, ssbx4, zygx, zybx };
					sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,rdsj,bz,cjhdqk,zbshqk,dfjnqk,zzxxqk,gxzcqx,xsccdm,zzsj,ssbx1,ssbx2,ssbx3,ssbx4,zygx,zybx) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, rdsj, bz,
							cjhdqk, zbshqk, dfjnqk, zzxxqk, gxzcqx, xsccdm,
							zzsj, ssbx1, ssbx2, ssbx3, ssbx4, zygx, zybx });
				}else{
					sql = "insert into "
				    	+ realTable
				    	+ "(nd,xn,xq,xh,rdsj,bz,cjhdqk,zbshqk,dfjnqk,zzxxqk,gxzcqx,xsccdm,zzsj,ssbx1,ssbx2,ssbx3,ssbx4,ybdykssj,ybdyjssj,xl,jg,csd) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
			    			rdsj,bz,cjhdqk,zbshqk,dfjnqk,zzxxqk,gxzcqx,xsccdm,zzsj,ssbx1,ssbx2,ssbx3,ssbx4,ybdykssj,ybdyjssj,xl,jg,csd});
				}	
 			}else if(realTable.equalsIgnoreCase("rdjjfzxxb")){
 				sql="select count(*) num from ybdyxxb where xh=?";
 				String num = dao.getOneRs(sql, new String[]{xh},"num");
 				String kssj = request.getParameter("gzkssj");
 				String xsccdm = dataSearchForm.getXsccdm();
 				if ("0".equalsIgnoreCase(num)) {
					sql = "insert into ybdyxxb (xn,xq,xh,nd,kssj,xsccdm) values(?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xn, xq, xh, nd, kssj,xsccdm });
				} else {
					sql = "update ybdyxxb set kssj=?, xsccdm=? where xh=? and xq=? and xn=?";
					dao.runUpdate(sql, new String[] { kssj,xsccdm,xh,xq,xn});
 				}
 			}else {
 			    dataSearchForm.setErrMsg("sdf");
 			    return mapping.findForward("false");
 			}
    	}
	return null;
    }
}
