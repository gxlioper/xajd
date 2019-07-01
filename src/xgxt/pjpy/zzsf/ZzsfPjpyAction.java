package xgxt.pjpy.zzsf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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
import xgxt.base.Excel2Oracle;
import xgxt.pjpy.PjpyForm;
import xgxt.utils.String.StringUtils;

public class ZzsfPjpyAction extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		ActionForward af = new ActionForward();
		String parameter = mapping.getParameter();
		try{
			if("apply".equalsIgnoreCase(parameter)){
				af = zzsfPjpyApply(mapping,form,request,response);
			} else if("saveapplication".equalsIgnoreCase(parameter)){
				af = zzsfPjpySaveApply(mapping,form,request,response);
			} else if("zzsfPjpyJxjdjb".equalsIgnoreCase(parameter)){
				af = zzsfPjpyJxjdjb(mapping,form,request,response);
			} else if("zzsfPjpyRychsq".equalsIgnoreCase(parameter)){
				af = zzsfPjpyRychsq(mapping,form,request,response);
			}  else if("zzsfPjpyRychSave".equalsIgnoreCase(parameter)){
				af = zzsfPjpyRychSaveApply(mapping,form,request,response);
			} else if("zzsfPjpyRychdjb".equalsIgnoreCase(parameter)){
				af = zzsfPjpyRychdjb(mapping,form,request,response);
			} else if ("jxjhz".equalsIgnoreCase(parameter)){
				af = jxjhz(mapping,form,request,response);
			} else if("jxjhz_output".equalsIgnoreCase(parameter)){
				af = jxjhzOutput(mapping,form,request,response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return af;
	}
	
	/**
	 * 漳州师范与安徽建筑工业学院奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzsfPjpyApply(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String sql = "";
		String act = request.getParameter("act");
		String pk = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");
		PjpyForm applyForm = (PjpyForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String jxjdm = request.getParameter("xmdm");
		String tab = request.getParameter("tab");
		
		String xxdm = dao.getXxdm();
		//HashMap<String, String> xnxqMap = dao.getMapNotOut("select jxjsqxn,jxjsqxq from xtszb", new String[]{});
		if (userType.equalsIgnoreCase("student")) {
		    xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
		if (!StringUtils.isNull(xh)) {
			xh = xh.trim();
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
		if (jxjdm != null) {
			sql = "select jxjlb,jlje from jxjdmb where jxjdm=?";
			String[] jxjInfo = dao.getOneRs(sql, new String[] { jxjdm },
					new String[] { "jxjlb", "jlje" });
			if (jxjInfo != null) {
				map.put("jxjlb", jxjInfo[0]);
				map.put("jlje", jxjInfo[1]);
			}
		}
		sql = "select * from jxjdmb";
		List jxjList = dao.getList(sql, new String[] {}, new String[] {	
			"jxjdm", "jxjmc" });
		request.setAttribute("jxjList", jxjList);
		sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
			"jxjsqxn", "jxjsqnd","jxjsqxq" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
		map.put("xq", tmp[2]);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] qtxx = null;
		if ("qtjxj".equalsIgnoreCase(tab) && !xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			HashMap<String, String> rsMap = dao.getMapNotOut("select szmc1,szmc2,szmc3,szmc4,szmc5,szcj1,szcj2,szcj3,szcj4,szcj5,zhszcpcj,zhszcpcjpm from ahjg_szfb where xh=? and xn=? and xq=?", new String[]{xh, map.get("xn"),map.get("xq")});
			request.setAttribute("rsMap", rsMap);
			request.setAttribute("zh", "yes");
		}
		//安徽建筑工业学院
		if (StringUtils.isEqual(xxdm, Globals.XXDM_AHJZGYXY)) {
			qtxx = new String[] { "xh", "bjcjpx1","bjcjpx2","bjcjpx3","bjcjpx4","bjcjpx5","zycjpx1","zycjpx2","zycjpx3","zycjpx4","zycjpx5","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpzf5","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpbjpx5","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4","zhkpzypx5"};
			sql = "select xh, bjcjpx1,bjcjpx2,bjcjpx3,bjcjpx4 ,bjcjpx5,zycjpx1,zycjpx2,zycjpx3,zycjpx4,zycjpx5,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpzf5,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpbjpx5,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4,zhkpzypx5 from xsjxjxxb where xh=?";
			request.setAttribute("isAHJG", "yes");
			if (!StringUtils.isNull(xh)) {
				List<HashMap<String, String>> pjf = dao
						.getList(
								"select xqmc,round(avg(cj)) pjf from cjb,xqdzb where xn=? and xq=xqdm and xh=? and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%'  group by xqmc",
								new String[] { map.get("xn"), xh },
								new String[] { "xqmc", "pjf" });
				if (pjf != null && pjf.size() > 0) {
					request.setAttribute("cxq", pjf.get(0).get("xqmc"));
					request.setAttribute("cpjf", pjf.get(0).get("pjf"));
					if (pjf.size() > 1) {
						request.setAttribute("qxq", pjf.get(1).get("xqmc"));
						request.setAttribute("qpjf", pjf.get(1).get("pjf"));
					}
				}
				List<HashMap<String, String>> cjList = dao
						.getList(
								"select kcmc,xqmc,cj from cjb,xqdzb where xn=? and xq = xqdm and xh=? and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%'",
								new String[] { map.get("xn"), xh },
								new String[] { "kcmc", "xqmc","cj" });
				
				request.setAttribute("cjList", cjList);
			}
		} else {
			qtxx = new String[] { "xh", "bjcjpx1","bjcjpx2","bjcjpx3","bjcjpx4","zycjpx1","zycjpx2","zycjpx3","zycjpx4","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4","pjcj1","pjcj2","pjcj3","pjcj4"};
			sql = "select xh, bjcjpx1,bjcjpx2,bjcjpx3,bjcjpx4 ,zycjpx1,zycjpx2,zycjpx3,zycjpx4,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4,pjcj1,pjcj2,pjcj3,pjcj4 from xsjxjxxb where xh=?";
		}
		
		String[] qtxxfs = dao.getOneRs(sql, new String[] {StringUtils.isNull(xh) ? "" : xh.trim()}, qtxx);
		if( act != null && act.equalsIgnoreCase("modi")){
			String sql1 = "select * from view_xsjxjb where "+pk+"='"+pkValue+"'";
			String[] jxjxx = dao.getColumnName("select * from view_xsjxjb where 1=2");
			String[] jxjxxArr = dao.getOneRs(sql1, new String[]{}, jxjxx);
			if (jxjxx != null && jxjxxArr != null) {
			for(int i=0;i<jxjxx.length;i++){
				if(!StringUtils.isNull(jxjxx[i]) && !StringUtils.isNull(jxjxxArr[i])){
					if("jxjdm".equalsIgnoreCase(jxjxx[i])){
						applyForm.setXmdm(jxjxxArr[i]);
					} else if("DNSHJXJ".equalsIgnoreCase(jxjxx[i])){
						applyForm.setZdm(jxjxxArr[i]);
					} 
					map.put(jxjxx[i].toLowerCase(), jxjxxArr[i]);
				}
			}
			}
		}
		if(qtxxfs == null){
		    qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
		    map.put(qtxx[i],qtxxfs[i]);
		}
		String[] tt = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{StringUtils.isNull(xh) ? "" : xh.trim()},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tt != null && tt.length == 2){
		    sjhm = tt[0];
		    wysp = tt[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		String xq = map.get("xq");
//		String onLine = session.getAttribute("userOnLine").toString();
		String[] count = dao.getOneRs("select jxjdm from xsjxjb where xh=? and xn=? and xq=?", new String[]{xh,map.get("xn"),xq}, new String[]{"jxjdm"});
		int cot = count != null ? count.length : 0;
		if (cot > 1) {
				request.setAttribute("exis", "yes");
		} else if (cot==1) {
			if ("modi".equalsIgnoreCase(act)) {
				
			} else {
				request.setAttribute("exis", "yes");
			}
		}
		xq = dao.getOneRs("select xqmc from xqdzb where xqdm=?", new String[]{xq}, "xqmc");
		map.put("xq", xq);
		boolean bFlag = dao.isKns(xh);
		if (bFlag && !"modi".equalsIgnoreCase(act)) {
			map.put("bz", "困难生");
			applyForm.setBz("困难生");
		}
		if (map != null) {
			applyForm.setPjcj1(map.get("pjcj1"));
			applyForm.setPjcj2(map.get("pjcj2"));
			applyForm.setPjcj3(map.get("pjcj3"));
			applyForm.setPjcj4(map.get("pjcj4"));
			applyForm.setBjcjpx1(map.get("bjcjpx1"));
			applyForm.setBjcjpx2(map.get("bjcjpx2"));
			applyForm.setBjcjpx3(map.get("bjcjpx3"));
			applyForm.setBjcjpx4(map.get("bjcjpx4"));
			applyForm.setBjcjpx5(map.get("bjcjpx5"));
			applyForm.setZycjpx1(map.get("zycjpx1"));
			applyForm.setZycjpx2(map.get("zycjpx2"));
			applyForm.setZycjpx3(map.get("zycjpx3"));
			applyForm.setZycjpx4(map.get("zycjpx4"));
			applyForm.setZhkpbjpx1(map.get("zhkpbjpx1"));
			applyForm.setZhkpbjpx2(map.get("zhkpbjpx2"));
			applyForm.setZhkpbjpx3(map.get("zhkpbjpx3"));
			applyForm.setZhkpbjpx4(map.get("zhkpbjpx4"));
			applyForm.setZhkpbjpx5(map.get("zhkpbjpx5"));
			applyForm.setZhkpzf1(map.get("zhkpzf1"));
			applyForm.setZhkpzf2(map.get("zhkpzf2"));
			applyForm.setZhkpzf3(map.get("zhkpzf3"));
			applyForm.setZhkpzf4(map.get("zhkpzf4"));
			applyForm.setZhkpzf5(map.get("zhkpzf5"));
			applyForm.setZhkpzypx1(map.get("zhkpzypx1"));
			applyForm.setZhkpzypx2(map.get("zhkpzypx2"));
			applyForm.setZhkpzypx3(map.get("zhkpzypx3"));
			applyForm.setZhkpzypx4(map.get("zhkpzypx4"));
			applyForm.setZhkpzypx5(map.get("zhkpzypx5"));
			
		}
		
		map.put("xh", xh);
		request.setAttribute("act", act);
		request.setAttribute("rs",map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	
	}
	
	public ActionForward zzsfPjpySaveApply(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		PjpyForm dataSearchForm = (PjpyForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		if ("modi".equalsIgnoreCase(act)) {
			pkValue = request.getParameter("pkVal");
		}
//		String tab = request.getParameter("tab");
		
		String sql = "";
//		String xxdm = StandardOperation.getXxdm();
		// 保存数据
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
		    xh = session.getAttribute("userName").toString();
		} else {
		    xh = dataSearchForm.getXh();
		}
		
		String xxdm = dao.getXxdm();
		
		sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
	    String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
		    "jxjsqxn", "jxjsqnd","jxjsqxq" });
	    String xn = tmp[0];
	    String nd = tmp[1];
	    String xq = tmp[2];
	    request.setAttribute("jxjList", dao.getList("select jxjdm,jxjmc from jxjdmb order by jxjdm", new String[]{}, new String[]{"jxjdm","jxjmc"}));
	    if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//安徽建工奖学金申请判断是否受处分
	    	
	    	HashMap<String, String> rs = new HashMap<String, String>();
			rs.put("xn", xn);
			rs.put("nd", nd);
			rs.put("xq", xq);
			rs.put("xh", xh);
	    	
	    	String sCjflag = dao.getOneRs("select count(*) num from cjb where xh=? and xn=? and xq=?", new String[]{xh, xn, xq}, "num");
	    	
	    	if (!StringUtils.isNull(sCjflag) && StringUtils.isEqual(sCjflag, "0")) {//成绩表中是否有该生信息
	    		request.setAttribute("inserted", "nocj");
	    		request.setAttribute("rs",rs);
	    		return mapping.findForward("success");
	    	}
	    	
			String sWjjg = dao.getOneRs("select count(*) num from view_wjcf where xh=? and xn=? and xq=?", new String[]{xh, xn,xq}, "num");//是否有违纪处分
			sWjjg = !StringUtils.isNull(sWjjg) ? sWjjg : "";
			
			String sBkjg = dao.getOneRs("select count(*) num from cjb where xh=? and xn=? and xq=? and to_number(cj)<60 and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%'", new String[]{xh, xn, xq}, "num");//是否有补考课程
			sBkjg = !StringUtils.isNull(sBkjg) ? sBkjg : "";
			
			String sZdcj = dao.getOneRs("select count(*) num from cjb where xh=? and xn=? and xq=? and to_number(cj)<70 and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%'", new String[]{xh, xn, xq}, "num");//是否有三门成绩低于70分
			int iZdcj = StringUtils.isNull(sZdcj) ? 0 : Integer.parseInt(sZdcj); 
			
			boolean bFlag = chkJxjsqFlag(xh, request.getParameter("xmdm"), "jxj", tmp);
			if (!bFlag) {
				request.setAttribute("rs",rs);
				request.setAttribute("inserted", "notj");
				return mapping.findForward("success");
			}
			
			//该生必修课存在重修，补考,违纪处分
			if (!StringUtils.isEqual(sBkjg, "0") || !StringUtils.isEqual(sWjjg, "0") || (iZdcj >= 3)) {
				request.setAttribute("inserted", "bhg");
				request.setAttribute("rs",rs);
				return mapping.findForward("success");
			}
		}
	    
	    
	    sql = "delete from xsjxjb where jxjdm=? and xh=? and xn=? and xq=?";
	    //boolean del = dao.runUpdate(sql, new String[] { pkValue, xh, xn, xq });
	    String count = dao.getOneRs("select count(*) num from xsjxjb where xh=? and xn=? and xq=? and jxjdm=?", new String[]{xh,xn,xq,request.getParameter("xmdm")}, "num");
	    int cot = StringUtils.isNull(count) ? 0 : Integer.parseInt(count.trim());
	    boolean del = true;
	    String drzw  = Base.chgNull(request.getParameter("drzw")," ",1);
	    String kyxm  = Base.chgNull(request.getParameter("kyxm")," ",1);
	    String sqly  = Base.chgNull(request.getParameter("sqly")," ",1);
	    String jxjlb  = Base.chgNull(request.getParameter("xmdm")," ",1);
	    String shjxj  = Base.chgNull(request.getParameter("zdm")," ",1);
	    String xxjl  = Base.chgNull(request.getParameter("xxjl")," ",1);
	    String bjcjpx1 = Base.chgNull(request.getParameter("bjcjpx1")," ",1);
	    String bjcjpx2 = Base.chgNull(request.getParameter("bjcjpx2")," ",1);
	    String bjcjpx3 = Base.chgNull(request.getParameter("bjcjpx3")," ",1);
	    String bjcjpx4 = Base.chgNull(request.getParameter("bjcjpx4")," ",1);
	    String zycjpx1 = Base.chgNull(request.getParameter("zycjpx1")," ",1);
	    String zycjpx2 = Base.chgNull(request.getParameter("zycjpx2")," ",1);
	    String zycjpx3 = Base.chgNull(request.getParameter("zycjpx3")," ",1);
	    String zycjpx4 = Base.chgNull(request.getParameter("zycjpx4")," ",1);
	    String zhkpzf1 = Base.chgNull(request.getParameter("zhkpzf1")," ",1);
	    String zhkpzf2 = Base.chgNull(request.getParameter("zhkpzf2")," ",1);
	    String zhkpzf3 = Base.chgNull(request.getParameter("zhkpzf3")," ",1);
	    String zhkpzf4 = Base.chgNull(request.getParameter("zhkpzf4")," ",1);
	    String zhkpbjpx1 = Base.chgNull(request.getParameter("zhkpbjpx1")," ",1);
	    String zhkpbjpx2 = Base.chgNull(request.getParameter("zhkpbjpx2")," ",1);
	    String zhkpbjpx3 = Base.chgNull(request.getParameter("zhkpbjpx3")," ",1);
	    String zhkpbjpx4 = Base.chgNull(request.getParameter("zhkpbjpx4")," ",1);
	    String zhkpzypx1 = Base.chgNull(request.getParameter("zhkpzypx1")," ",1);
	    String zhkpzypx2 = Base.chgNull(request.getParameter("zhkpzypx2")," ",1);
	    String zhkpzypx3 = Base.chgNull(request.getParameter("zhkpzypx3")," ",1);
	    String zhkpzypx4 = Base.chgNull(request.getParameter("zhkpzypx4")," ",1);
	    String bjcjpx5 = Base.chgNull(request.getParameter("bjcjpx5"), " ", 1);
	    String zycjpx5 = Base.chgNull(request.getParameter("zycjpx5"), " ", 1);
	    String zhkpzf5 = Base.chgNull(request.getParameter("zhkpzf5"), " ", 1);
	    String zhkpbjpx5 = Base.chgNull(request.getParameter("zhkpbjpx5"), " ", 1);
	    String zhkpzypx5 = Base.chgNull(request.getParameter("zhkpzypx5"), " ", 1);
	    String sjhm  = Base.chgNull(request.getParameter("sjhm")," ",1);
	    String wysp  = Base.chgNull(request.getParameter("wysp")," ",1);
	    String pjcj1 = Base.chgNull(request.getParameter("pjcj1"), " ", 1);
	    String pjcj2 = Base.chgNull(request.getParameter("pjcj2"), " ", 1);
	    String pjcj3 = Base.chgNull(request.getParameter("pjcj3"), " ", 1);
	    String pjcj4 = Base.chgNull(request.getParameter("pjcj4"), " ", 1);
	    String kh = request.getParameter("kh");
	    String bz = DealString.toGBK(request.getParameter("bz"));
//	    String jfqk = deal.toGBK(request.getParameter("jfqk"));
	    boolean result = false;
	    String[] resval = null;
		String[] reskey = null;
		HashMap<String, String> map = new HashMap<String, String>();
	    if (del) {
	    	if (cot > 0) {//已申请,要修改数据
	    		sql = "update xsjxjb set sqly=?,drzw=?,kycg=?,dnshjxj=?,xxjl=?,kh=?,bz=? where xh=? and xn=? and xq=? and jxjdm=?";
		    	
		    	result = dao.runUpdate(sql, new String[] { sqly,drzw,kyxm,shjxj,xxjl,kh,bz,xh,xn,xq,jxjlb});
	    	} else {//未申请,重新插入
	    		if ("modi".equalsIgnoreCase(act)) {
	    			sql = "update xsjxjb set sqly=?,drzw=?,kycg=?,dnshjxj=?,xxjl=?,kh=?,bz=?,jxjdm=? where xn||nd||jxjdm||xh=?";
			    	
			    	result = dao.runUpdate(sql, new String[] { sqly,drzw,kyxm,shjxj,xxjl,kh,bz,jxjlb,pkValue});
	    		} else {
	    			sql = "insert into xsjxjb(XH,JXJDM,XN,nd,SQSJ,"
			    		+ "SQLY,FFBJ,FFSJ,FFWJH,XQ,DRZW,KYCG,DNSHJXJ,XXJL,KH,BZ) values("
			    		+ "?,?,?,?,to_char(sysdate,'yyyymmddhh24miss'),?,?,?,?,?,?,?,?,?,?,?)";
			    	
			    	result = dao.runUpdate(sql, new String[] { xh, jxjlb,
			    			xn, nd, sqly, "0", " ", " ", xq, drzw, kyxm,shjxj,xxjl,kh,bz});
	    		}
	    		
	    	}
	    	
		if (result) {
		    request.setAttribute("inserted", "ok");
		} else {
		    request.setAttribute("inserted", "no");
		}
		sql = "delete xsjxjxxb where xh=?";
		dao.runUpdate(sql, new String[] { xh });
		
		//安徽建工
		if (StringUtils.isEqual(xxdm, Globals.XXDM_AHJZGYXY)) {
			sql = "insert into xsjxjxxb(xh, bjcjpx1, bjcjpx2, bjcjpx3, bjcjpx4, bjcjpx5, zycjpx1, zycjpx2, zycjpx3, zycjpx4, zycjpx5, zhkpzf1, zhkpzf2, zhkpzf3, zhkpzf4, zhkpzf5, zhkpbjpx1, zhkpbjpx2, zhkpbjpx3, zhkpbjpx4, zhkpbjpx5, zhkpzypx1, zhkpzypx2, zhkpzypx3, zhkpzypx4, zhkpzypx5"
				+ " ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, new String[] { xh,  bjcjpx1, bjcjpx2, bjcjpx3, bjcjpx4, bjcjpx5, zycjpx1, zycjpx2, zycjpx3, zycjpx4, zycjpx5, zhkpzf1, zhkpzf2, zhkpzf3, zhkpzf4, zhkpzf5, zhkpbjpx1, zhkpbjpx2, zhkpbjpx3, zhkpbjpx4, zhkpbjpx5, zhkpzypx1, zhkpzypx2, zhkpzypx3, zhkpzypx4, zhkpzypx5});
			resval = new String[]{xh,xn,xq,drzw,kyxm,sqly,jxjlb,shjxj,xxjl,bjcjpx1, bjcjpx2, bjcjpx3, bjcjpx4, bjcjpx5, zycjpx1, zycjpx2, zycjpx3, zycjpx4, zycjpx5, zhkpzf1, zhkpzf2, zhkpzf3, zhkpzf4, zhkpzf5, zhkpbjpx1, zhkpbjpx2, zhkpbjpx3, zhkpbjpx4, zhkpbjpx5, zhkpzypx1, zhkpzypx2, zhkpzypx3, zhkpzypx4, zhkpzypx5,sjhm,wysp};
		    reskey = new String[]{"xh","xn","xq","drzw","kyxm","sqly","xmdm","shjxj","xxjl","bjcjpx1","bjcjpx2","bjcjpx3","bjcjpx4","bjcjpx5","zycjpx1","zycjpx2","zycjpx3","zycjpx4","zycjpx5","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpzf5","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpbjpx5","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4","zhkpzypx5","sjhm","wysp"};
		    request.setAttribute("isAHJG", "yes");
		} else {
			sql = "insert into xsjxjxxb(xh, bjcjpx1, bjcjpx2, bjcjpx3, bjcjpx4, zycjpx1, zycjpx2, zycjpx3, zycjpx4, zhkpzf1, zhkpzf2, zhkpzf3, zhkpzf4, zhkpbjpx1, zhkpbjpx2, zhkpbjpx3, zhkpbjpx4, zhkpzypx1, zhkpzypx2, zhkpzypx3, zhkpzypx4"
				+ ",pjcj1,pjcj2,pjcj3,pjcj4 ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			dao.runUpdate(sql, new String[] { xh,  bjcjpx1, bjcjpx2, bjcjpx3, bjcjpx4, zycjpx1, zycjpx2, zycjpx3, zycjpx4, zhkpzf1, zhkpzf2, zhkpzf3, zhkpzf4, zhkpbjpx1, zhkpbjpx2, zhkpbjpx3, zhkpbjpx4, zhkpzypx1, zhkpzypx2, zhkpzypx3, zhkpzypx4,pjcj1,pjcj2,pjcj3,pjcj4});
			resval = new String[]{xh,xn,xq,drzw,kyxm,sqly,jxjlb,shjxj,xxjl,bjcjpx1, bjcjpx2, bjcjpx3, bjcjpx4, zycjpx1, zycjpx2, zycjpx3, zycjpx4, zhkpzf1, zhkpzf2, zhkpzf3, zhkpzf4, zhkpbjpx1, zhkpbjpx2, zhkpbjpx3, zhkpbjpx4, zhkpzypx1, zhkpzypx2, zhkpzypx3, zhkpzypx4,sjhm,wysp,pjcj1,pjcj2,pjcj3,pjcj4};
		    reskey = new String[]{"xh","xn","xq","drzw","kyxm","sqly","xmdm","shjxj","xxjl","bjcjpx1","bjcjpx2","bjcjpx3","bjcjpx4","zycjpx1","zycjpx2","zycjpx3","zycjpx4","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4","sjhm","wysp","pjcj1","pjcj2","pjcj3","pjcj4"};
		}//end if
		
		sql = "select count(*) num from xsfzxxb where xh=?";
		String tmptmp = dao.getOneRs(sql,new String[]{xh},new String[]{"num"})[0];
		sql = "update xsfzxxb set sjhm=?,wysp=? where xh=?";
		if(tmptmp.equalsIgnoreCase("0")){
			sql = "insert into xsfzxxb(sjhm,wysp,xh) values(?,?,?)";		    
		}
		dao.runUpdate(sql,new String[]{sjhm,wysp,xh});
		
	    } else {
	    	return mapping.findForward("false");
	    }
	    String[] stuInfo = new String[]{"xm","xymc","zymc","bjmc","nj","xb"};
	    String[] baseInfo = dao.getOneRs("select xm,xymc,zymc,bjmc,nj,xb from view_xsjbxx where xh=?", new String[]{xh}, stuInfo);
	    
	    for(int i=0;i<resval.length;i++){
	    	map.put(reskey[i],resval[i]);
	    }
	    if (baseInfo != null && baseInfo.length > 0) {
			for (int i = 0; i < baseInfo.length; i++) {
				map.put(stuInfo[i], baseInfo[i]);
			}
		}
	    map.put("kh", kh);
	    map.put("bz", bz);
	    request.setAttribute("rs", map);
	    return mapping.findForward("success");
	}
	
	public static ActionForward zzsfPjpyJxjdjb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne) throws Exception {
		DAO dao = DAO.getInstance();
		String tips = "评奖评优 - 奖学金申请 - 奖学金评审登记表";
		request.setAttribute("tips", tips);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xh = request.getParameter("xh");
		String jxj = request.getParameter("jxjdm");
		
		String xxdm = dao.getXxdm();
		if (userType.equalsIgnoreCase("stu")) {
		    xh = session.getAttribute("userName").toString();
		}
		
		String sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
			"jxjsqxn", "jxjsqnd","jxjsqxq" });
		String xn = tmp[0];
		String xq = tmp[2];
		sql = "select * from view_xsjxjb where jxjdm=? and xh=? and xn=? and xq=?";
		HashMap<String, String> map = dao.getMap(sql, new String[] { jxj, xh, xn,xq },
			new String[] { "xn","xq", "xh", "xm", "xymc", "zymc", "bjmc", "xb",
				"jxjmc", "sqly", "drzw", "kycg", "dnshjxj","xxjl","xyshyj","xxshyj","fdyyj","xypswyhyj" });
		
		String[] qtxx = null;
		//安徽建工
		if (StringUtils.isEqual(xxdm, Globals.XXDM_AHJZGYXY)) {
			qtxx = new String[] { "xh", "bjcjpx1","bjcjpx2","bjcjpx3","bjcjpx4","bjcjpx5","zycjpx1","zycjpx2","zycjpx3","zycjpx4","zycjpx5","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpzf5","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpbjpx5","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4","zhkpzypx5"};
			sql = "select xh, bjcjpx1,bjcjpx2,bjcjpx3,bjcjpx4 ,bjcjpx5,zycjpx1,zycjpx2,zycjpx3,zycjpx4,zycjpx5,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpzf5,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpbjpx5,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4,zhkpzypx5 from xsjxjxxb where xh=?";
			request.setAttribute("isAHJG", "yes");
		}else {
			qtxx = new String[] { "xh", "bjcjpx1","bjcjpx2","bjcjpx3","bjcjpx4","zycjpx1","zycjpx2","zycjpx3","zycjpx4","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4"};
			sql = "select xh, bjcjpx1,bjcjpx2,bjcjpx3,bjcjpx4 ,zycjpx1,zycjpx2,zycjpx3,zycjpx4,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4 from xsjxjxxb where xh=?";
		}//end if
		
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if(qtxxfs == null){
		    qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
		    map.put(qtxx[i],qtxxfs[i]);
		}
		String[] tt = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{xh},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tt != null && tt.length == 2){
		    sjhm = tt[0];
		    wysp = tt[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		String zyrsNum = dao
			.getOneRs(
				"select count(*) num from view_xsjbxx where zydm=(select zydm from bks_xsjbxx where xh=?)",
				new String[] { xh }, new String[] { "num" })[0];
		tt = dao.getOneRs("select mzmc,zzmmmc from view_stu_details where xh=?",new String[]{xh},new String[]{"mzmc","zzmmmc"});
		String mzmc = "";
		String zzmmmc = "";
		if(tt != null && tt.length == 2){
		    mzmc = tt[0];
		    zzmmmc = tt[1];
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[] {}, new String [] {"xxmc"})[0];
		map.put("mzmc", mzmc);
		map.put("zzmmmc", zzmmmc);
		map.put("zyrsNum", zyrsNum);
		map.put("xxmc", xxmc);
		request.setAttribute("map", map);
		return mapping.findForward("success");
	}

	public ActionForward zzsfPjpyRychsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne) throws Exception{
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		String xh = "";
		PjpyForm applyForm = (PjpyForm) form;
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		if (userType.equalsIgnoreCase("stu")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = applyForm.getXh();
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
		sql = "select * from rychdmb";
		List rychList = dao.getList(sql, new String[] {}, new String[] {
			"rychdm", "rychmc" });
		request.setAttribute("rychList", rychList);
		sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
			"jxjsqxn", "jxjsqnd","jxjsqxq" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
		map.put("xq", tmp[2]);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] qtxx = new String[] { "xh","drzw","tydbqk","byzx","jtdz","syd","brjl","zysj","hjqk"};
		sql = "select * from xsrychxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if(qtxxfs == null){
		    qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
		    map.put(qtxx[i],qtxxfs[i]);
		}
		
		String[] tem = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{xh},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tem != null && tem.length == 2){
		    sjhm = tem[0];
		    wysp = tem[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		
		String[] outArr = new String[]{"bjcjpx1","zycjpx1","bjcjpx2","zycjpx2","bjcjpx3","zycjpx3","bjcjpx4","zycjpx4","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4", "pjcj1", "pjcj2", "pjcj3", "pjcj4"};
		String[] tt = dao.getOneRs("select bjcjpx1,zycjpx1,bjcjpx2,zycjpx2,bjcjpx3,zycjpx3,bjcjpx4,zycjpx4,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4,pjcj1,pjcj2,pjcj3,pjcj4 from xsjxjxxb where xh=?",new String[]{xh},outArr);
		if(tt != null){
			for(int i=0;i<outArr.length;i++){
				map.put(outArr[i], tt[i]);
			}
		}		
		request.setAttribute("rs",map);
		return mapping.findForward("success");
	}

	public ActionForward zzsfPjpyRychSaveApply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne) throws Exception{
//		 荣誉称号
		
		ActionForward newFwd = new ActionForward();
		PjpyForm dataSearchForm = (PjpyForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		DAO dao = DAO.getInstance();
		ZzsfPjpyDAO zzsfdao = new ZzsfPjpyDAO();
//		String pkValue = request.getParameter("pkValue");
//		String tab = request.getParameter("tab");
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
		    xh = session.getAttribute("userName").toString();
		} else {
		    xh = dataSearchForm.getXh();
		}
		
		String sql = "";
		String rychdm = DealString.toGBK(request.getParameter("xmdm"));
		String[] tmp =null;
	    sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
	    tmp = dao.getOneRs(sql, new String[] {}, new String[] {
	    		"jxjsqxn", "jxjsqnd","jxjsqxq" });
	    String xn = tmp[0];
	    String nd = tmp[1];
	    String xq = tmp[2];
	    sql = "delete xsrychb where rychdm=? and xh=? and xn=? and xq=?";
	    String drzw = Base.chgNull(request.getParameter("drzw")," ",1);
	    String tydbqk = Base.chgNull(request.getParameter("tydbqk")," ",1);
	    String byzx = Base.chgNull(request.getParameter("byzx")," ",1);
	    String jtdz = Base.chgNull(request.getParameter("jtdz")," ",1);
	    String syd = Base.chgNull(request.getParameter("syd")," ",1);
	    String brjl = Base.chgNull(request.getParameter("brjl")," ",1);
	    String zysj = Base.chgNull(request.getParameter("zysj")," ",1);
	    String hjqk = Base.chgNull(request.getParameter("hjqk")," ",1);
	    
	    String sjhm = Base.chgNull(request.getParameter("sjhm")," ",1);
	    String wysp = Base.chgNull(request.getParameter("wysp")," ",1);
//	    String zzmm = DealString.toGBK(request.getParameter("zzmm"));
//	    String cjmc = Base.chgNull(request.getParameter("cjmc"), " ", 1);
//	    String zhpfmc = Base.chgNull(request.getParameter("zhpfmc"), " ", 1);
//	    String lxdh = Base.chgNull(request.getParameter("lxdh"), " ", 1);
	    String bjcjpx1=Base.chgNull(request.getParameter("bjcjpx1"), " ", 1);
	    String zycjpx1=Base.chgNull(request.getParameter("zycjpx1"), " ", 1);
	    String bjcjpx2=Base.chgNull(request.getParameter("bjcjpx2"), " ", 1);
	    String zycjpx2=Base.chgNull(request.getParameter("zycjpx2"), " ", 1);
	    String bjcjpx3=Base.chgNull(request.getParameter("bjcjpx3"), " ", 1);
	    String zycjpx3=Base.chgNull(request.getParameter("zycjpx3"), " ", 1);
	    String bjcjpx4=Base.chgNull(request.getParameter("bjcjpx4"), " ", 1);
	    String zycjpx4=Base.chgNull(request.getParameter("zycjpx4"), " ", 1);
	    String zhkpzf1=Base.chgNull(request.getParameter("zhkpzf1"), " ", 1);
	    String zhkpzf2=Base.chgNull(request.getParameter("zhkpzf2"), " ", 1);
	    String zhkpzf3=Base.chgNull(request.getParameter("zhkpzf3"), " ", 1);
	    String zhkpzf4=Base.chgNull(request.getParameter("zhkpzf4"), " ", 1);
	    String zhkpbjpx1=Base.chgNull(request.getParameter("zhkpbjpx1"), " ", 1);
	    String zhkpbjpx2=Base.chgNull(request.getParameter("zhkpbjpx2"), " ", 1);
	    String zhkpbjpx3=Base.chgNull(request.getParameter("zhkpbjpx3"), " ", 1);
	    String zhkpbjpx4=Base.chgNull(request.getParameter("zhkpbjpx4"), " ", 1);
	    String zhkpzypx1=Base.chgNull(request.getParameter("zhkpzypx1"), " ", 1);
	    String zhkpzypx2=Base.chgNull(request.getParameter("zhkpzypx2"), " ", 1);
	    String zhkpzypx3=Base.chgNull(request.getParameter("zhkpzypx3"), " ", 1);
	    String zhkpzypx4=Base.chgNull(request.getParameter("zhkpzypx4"), " ", 1);
	    String pjcj1 = Base.chgNull(request.getParameter("pjcj1"), " ", 1);//平均成绩1
	    String pjcj2 = Base.chgNull(request.getParameter("pjcj2"), " ", 1);
	    String pjcj3 = Base.chgNull(request.getParameter("pjcj3"), " ", 1);
	    String pjcj4 = Base.chgNull(request.getParameter("pjcj4"), " ", 1);
	    boolean del = dao.runUpdate(sql, new String[] { rychdm, xh, xn,xq });  
	    if (del) {
	    	sql = "insert into xsrychb(XH,XN,ND,RYCHDM,XQ) values(?,?,?,?,?)";
	    	boolean result = dao.runUpdate(sql, new String[] { xh, xn, nd,
	    			rychdm, xq });
	    	if (result) {
	    		request.setAttribute("inserted", "ok");
	    	} else {
	    		request.setAttribute("inserted", "no");
	    	}
	    	sql = "delete xsrychxxb where xh=?";
	    	dao.runUpdate(sql, new String[] { xh });
	    	sql = "insert into xsrychxxb(xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk) values(?,?,?,?,?,?,?,?,?)";
	    	dao.runUpdate(sql,new String[]{xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk});
	    	sql = "select count(*) num from xsfzxxb where xh=?";
	    	String tmptmp = dao.getOneRs(sql,new String[]{xh},new String[]{"num"})[0];
	    	sql = "update xsfzxxb set sjhm=?,wysp=? where xh=?";
	    	String[] valArr = new String[]{bjcjpx1,zycjpx1,bjcjpx2,zycjpx2,bjcjpx3,zycjpx3,bjcjpx4,zycjpx4,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4,pjcj1,pjcj2,pjcj3,pjcj4,xh};
//	    	String[] keyArr = new String[]{"bjcjpx1","zycjpx1","bjcjpx2","zycjpx2","bjcjpx3","zycjpx3","bjcjpx4","zycjpx4","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4","xh","xn","xq"};
	    	if(zzsfdao.checkExistJxjxx(xh)){
	    		sql = "update xsjxjxxb set bjcjpx1=?,zycjpx1=?,bjcjpx2=?,zycjpx2=?,bjcjpx3=?,zycjpx3=?,bjcjpx4=?,zycjpx4=?,zhkpzf1=?,zhkpzf2=?,zhkpzf3=?,zhkpzf4=?,zhkpbjpx1=?,zhkpbjpx2=?,zhkpbjpx3=?,zhkpbjpx4=?,zhkpzypx1=?,zhkpzypx2=?,zhkpzypx3=?,zhkpzypx4=?,pjcj1=?,pjcj2=?,pjcj3=?,pjcj4=? where xh=?";
	    	} else {
	    		sql = "insert into xsjxjxxb(bjcjpx1,zycjpx1,bjcjpx2,zycjpx2,bjcjpx3,zycjpx3,bjcjpx4,zycjpx4,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4,pjcj1,pjcj2,pjcj3,pjcj4,xh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    	}
	    	dao.runUpdate(sql,valArr);
	    	if(tmptmp.equalsIgnoreCase("0")){
	    		sql = "insert into xsfzxxb(sjhm,wysp,xh) values(?,?,?)";
	    		dao.runUpdate(sql,new String[]{sjhm,wysp,xh});
	    	}
	    	
	    } else {
	    	
	    	return mapping.findForward("false");
	    }
	   
	   newFwd = new ActionForward("/pjpy_zzsf_rychsq.do", false);
	   return newFwd;
	}
	
	public ActionForward zzsfPjpyRychdjb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne){
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		String xh = "";
		PjpyForm applyForm = (PjpyForm) form;
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		if (userType.equalsIgnoreCase("stu")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = applyForm.getXh();
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
		
		sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
			"jxjsqxn", "jxjsqnd","jxjsqxq" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
		map.put("xq", tmp[2]);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] tt = dao.getOneRs("select mzmc,zzmmmc from view_stu_details where xh=?",new String[]{xh},new String[]{"mzmc","zzmmmc"});
		String mzmc = "";
		String zzmmmc = "";
		if(tt != null && tt.length == 2){
		    mzmc = tt[0];
		    zzmmmc = tt[1];
		}
		map.put("mzmc", mzmc);
		map.put("zzmmmc", zzmmmc);
		
		String[] qtxx = new String[] { "xh","drzw","tydbqk","byzx","jtdz","syd","brjl","zysj","hjqk"};
		sql = "select * from xsrychxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if(qtxxfs == null){
		    qtxxfs = new String[qtxx.length];
		}
		for(int i = 1;i < qtxx.length; i++){
		    map.put(qtxx[i],qtxxfs[i]);
		}
		
		String[] tem = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{xh},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tem != null && tem.length == 2){
		    sjhm = tem[0];
		    wysp = tem[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		
		String[] temp = dao.getOneRs("select a.rychmc from rychdmb a,xsrychb b where b.xh=? and a.rychdm=b.rychdm and b.xn=? and b.xq=?",new String[]{xh,tmp[0],tmp[2]},new String[]{"rychmc"});
		String rychmc = "";
		if(temp != null && temp.length >0){
			rychmc = temp[0];
		}
		map.put("rychmc", rychmc);
		
		String[] outArr = new String[]{"bjcjpx1","zycjpx1","bjcjpx2","zycjpx2","bjcjpx3","zycjpx3","bjcjpx4","zycjpx4","zhkpzf1","zhkpzf2","zhkpzf3","zhkpzf4","zhkpbjpx1","zhkpbjpx2","zhkpbjpx3","zhkpbjpx4","zhkpzypx1","zhkpzypx2","zhkpzypx3","zhkpzypx4"};
		String[] ttt = dao.getOneRs("select bjcjpx1,zycjpx1,bjcjpx2,zycjpx2,bjcjpx3,zycjpx3,bjcjpx4,zycjpx4,zhkpzf1,zhkpzf2,zhkpzf3,zhkpzf4,zhkpbjpx1,zhkpbjpx2,zhkpbjpx3,zhkpbjpx4,zhkpzypx1,zhkpzypx2,zhkpzypx3,zhkpzypx4 from xsjxjxxb where xh=?",new String[]{xh},outArr);
		if(ttt != null){
			for(int i=0;i<outArr.length;i++){
				map.put(outArr[i], ttt[i]);
			}
		}		
		request.setAttribute("rs",map);
		return mapping.findForward("success");
	}
	
	public ActionForward jxjhz(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	                          throws Exception{
		HttpSession session = request.getSession();
		String tips ="当前所在位置：评奖评优 － 审核 － 奖学金汇总";
		String userdep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		ZzsfPjpyDAO dao = new ZzsfPjpyDAO();
		ZzsfPjpyForm myForm = (ZzsfPjpyForm) form; 
		String selTab = myForm.getSelectTab();
		String nj   = myForm.getNj();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		DAO utilDao = DAO.getInstance();
		
		if(selTab == null || "".equals(selTab.trim())) selTab = "view_jxjhz_output";
		String sql = "select * from " +selTab+ " where 1=2";
		String[] colList = utilDao.getColumnName(sql);
		String[] colListCn = utilDao.getColumnNameCN(colList, selTab);
		ArrayList<HashMap<String, String>> srcTabColsList = new ArrayList<HashMap<String,String>>();
		generateList(srcTabColsList,colList,colListCn);
		
		request.setAttribute("tableName", selTab);
		request.setAttribute("tips", tips);
		request.setAttribute("userType", userType);
		request.setAttribute("srcTabColsList", srcTabColsList);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		//  对于学院用户，主要是辅导员，按照辅导员所对应的班级给出班级列表，如果是管理员，则可以根据学院等条件查询所有的班级
		//同时提供管理员的全部汇总功能
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("disable", "true");
			myForm.setXydm(userdep);
			xydm = userdep;
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", dao.getZyList(userName, utilDao));
			request.setAttribute("bjList", dao.getBjList(userName, utilDao,zydm));
		} else {
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", utilDao.getZyList(xydm));
			request.setAttribute("bjList", utilDao.getBjList(xydm, zydm,nj));
		}
		return mapping.findForward("success");
	}
	
	
	private ActionForward jxjhzOutput(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		ZzsfPjpyForm myForm = (ZzsfPjpyForm) form;
		DAO utilDao = DAO.getInstance();
		String nj = myForm.getNj();
		String xn = myForm.getXn();
		String xh = myForm.getXh();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String bjdm = myForm.getBjdm();
		
		String tableName = request.getParameter("tableName");
		StringBuffer condi = new StringBuffer(" where 1=1 ");
		//String[] queryCondiArr = {nj,xn,xh,xydm,zydm,bjdm};
		String queryStr = request.getParameter("queryStr");
		String[] queryColsArr = queryStr.split("!!");
		String[] queryColsArrCn = utilDao.getColumnNameCN(queryColsArr, tableName);
		StringBuffer sql = new StringBuffer("select ");
		for(int i=0;i<queryColsArr.length;i++){
			sql.append(queryColsArr[i]);
			sql.append(i!=queryColsArr.length-1?",":"");
		}
		sql.append(" from ");
		sql.append(tableName);
		//以下生成条件
		if(nj != null && !(nj.trim().equalsIgnoreCase(""))){
			condi.append(" and nj='");
			condi.append(nj);
			condi.append("' ");
		}
		if(xn != null && !(xn.trim().equalsIgnoreCase(""))){
			condi.append(" and xn='");
			condi.append(xn);
			condi.append("' ");
		}
		if(xh != null && !(xh.trim().equalsIgnoreCase(""))){
			condi.append(" and xh='");
			condi.append(xh);
			condi.append("' ");
		}
		if(xydm != null && !(xydm.trim().equalsIgnoreCase(""))){
			condi.append(" and xydm='");
			condi.append(xydm);
			condi.append("' ");
		}
		if(zydm != null && !(zydm.trim().equalsIgnoreCase(""))){
			condi.append(" and zydm='");
			condi.append(zydm);
			condi.append("' ");
		}
		if(bjdm != null && !(bjdm.trim().equalsIgnoreCase(""))){
			condi.append(" and bjdm='");
			condi.append(bjdm);
			condi.append("' ");
		}
		condi.append(" order by bjpm,njpm");
		sql.append(condi.toString());
		Vector<Object> rs = new Vector<Object>();
		rs.addAll(utilDao.rsToVator(sql.toString(), new String[] {}, queryColsArr));
		try{
			response.reset();
			OutputStream output = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportDataFor(rs,queryColsArrCn,output);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	private void generateList(ArrayList<HashMap<String, String>> list,String[] col,String[] colCn){
		for(int i=0;i<col.length;i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en",col[i]);
			map.put("cn", colCn[i]);
			list.add(map);
		}
	}
	
	/**
	 * 检查该生是否符合申请条件
	 * @param xh
	 * @param xmdm
	 * @param xmlx
	 * @return
	 * @throws Exception
	 */
	public boolean chkJxjsqFlag(String xh, String xmdm, String xmlx, String[] jxjsq) throws Exception {
		DAO dao = DAO.getInstance();
		boolean bFlag  = false;
		List<String[]> tjList= dao.rsToVator(
				"select jxjdm,tjzdm,zdcz,zdbj,tj from jxjhdtj where jxjdm=?",
				new String[] { xmdm }, new String[] { "tjzdm", "zdcz", "zdbj", "tj"});
		StringBuffer query = new StringBuffer("select count(*) num from (select round(avg(cj)) pjcj from view_zhhcjb where xh=? and xn=? and xq=? and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%' ) where 1=1 ");
		if (StringUtils.isEqual(xmlx, "jxj")) {//奖学金检查
			for (int i = 0; i < tjList.size(); i++) {
				String[] tmpList = tjList.get(i);//取单个条件
				if (tmpList != null && tmpList.length == 4) {
						query.append(" and to_number(");
						query.append(tmpList[0]);
						query.append(") ");
						query.append(tmpList[2]);
						query.append(tmpList[3]);
				}
			}
			String num = dao.getOneRs(query.toString(), new String[] { xh,
				StringUtils.isNull(jxjsq[0]) ? "" : jxjsq[0],
				StringUtils.isNull(jxjsq[2]) ? "" : jxjsq[2] },
				"num");
		if (!StringUtils.isNull(num) && StringUtils.isEqual(num, "0")) {
			return false;
		} else {
			return true;
		}
		} else {//荣誉称号检查
			
		}
		return bFlag;
	}
	
	/**
	 * 检查该生是否符合申请条件
	 * @param xh
	 * @param xmdm
	 * @param xmlx
	 * @return
	 * @throws Exception
	 */
	public static boolean chkJxjsqFlags(String xh, String xmdm, String xmlx, String[] jxjsq) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
//		boolean bFlag  = false;
		
		/*String sCjflag = dao.getOneRs("select count(*) num from cjb where xh=? and xn=? and xq=?", new String[]{xh, jxjsq[0], jxjsq[2]}, "num");
    	
    	if (!StringUtils.isNull(sCjflag) && StringUtils.isEqual(sCjflag, "0")) {//成绩表中是否有该生信息
    		return false;
    	}*/
    	
		String sWjjg = dao.getOneRs("select count(*) num from view_wjcf where xh=? and xn=? and xq=?", new String[]{xh, jxjsq[0],jxjsq[2]}, "num");//是否有违纪处分
		sWjjg = !StringUtils.isNull(sWjjg) ? sWjjg : "";
		
		String sBkjg = dao.getOneRs("select count(*) num from cjb where xh=? and xn=? and xq=? and to_number(cj)<60 and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%'", new String[]{xh, jxjsq[0], jxjsq[2]}, "num");//是否有补考课程
		sBkjg = !StringUtils.isNull(sBkjg) ? sBkjg : "";
		
		
		

		// 该生必修课存在重修，补考,违纪处分
		if (!Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			if (!StringUtils.isEqual(sBkjg, "0")
					|| !StringUtils.isEqual(sWjjg, "0")) {
				return false;
			}
		}
		
		List<String[]> tjList= dao.rsToVator(
				"select jxjdm,tjzdm,zdcz,zdbj,tj from jxjhdtj where jxjdm=?",
				new String[] { xmdm }, new String[] { "tjzdm", "zdcz", "zdbj", "tj"});
		StringBuffer query = new StringBuffer("select count(*) num from (select round(avg(cj)) pjcj from view_zhhcjb where xh=? and xn=? and xq=? and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%' ) where 1=1 ");
		if (StringUtils.isEqual(xmlx, "jxj")) {//奖学金检查
			String sZdcj = dao.getOneRs("select count(*) num from cjb where xh=? and xn=? and xq=? and to_number(cj)<70 and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%'", new String[]{xh, jxjsq[0], jxjsq[2]}, "num");//是否有三门成绩低于70分
			int iZdcj = StringUtils.isNull(sZdcj) ? 0 : Integer.parseInt(sZdcj); 
			if (iZdcj >= 3) {
				return false;
			}
			for (int i = 0; i < tjList.size(); i++) {
				String[] tmpList = tjList.get(i);//取单个条件
				if (tmpList != null && tmpList.length == 4) {
						query.append(" and to_number(");
						query.append(tmpList[0]);
						query.append(") ");
						query.append(tmpList[2]);
						query.append(tmpList[3]);
				}
			}
			String num = dao.getOneRs(query.toString(), new String[] { xh,
				StringUtils.isNull(jxjsq[0]) ? "" : jxjsq[0],
				StringUtils.isNull(jxjsq[2]) ? "" : jxjsq[2] },
				"num");
		if (!StringUtils.isNull(num) && StringUtils.isEqual(num, "0")) {
			return false;
		} else {
			return true;
		}
		} else {//荣誉称号检查
			for (int i=0;i<tjList.size();i++) {
				String[] tmpList = tjList.get(i);//取单个条件
				if (tmpList != null && tmpList.length == 4) {
					if (tmpList[0].equalsIgnoreCase("pjcj")) {//平均成绩
						String sPjcj = dao.getOneRs("select count(*) num from (select round(avg(cj)) pjcj from cjb where xh=? and xn=? and xq=? and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%') where 1=1 and to_number(pjcj)>="+tmpList[3]+"", new String[]{xh, jxjsq[0], jxjsq[2]}, "num");
						if (!StringUtils.isNull(sPjcj) && StringUtils.isEqual(sPjcj, "0")) {
							return false;
						}
					}
					if (tmpList[0].equalsIgnoreCase("cjmc")) {//成绩名次
						String mcs = dao.getOneRs("select count(*) num from ( select mc from (select xh,(dense_rank() over(partition by bjdm order by to_number(pjcj) desc nulls last)) mc from (select xh,xn,xq, round(avg(cj)) pjcj,(select bjdm from view_xsjbxx a where a.xh = b.xh) bjdm from cjb b where xn=? and xq=? group by b.xh)) where xh=?) where mc<="+tmpList[3]+"", new String[]{ jxjsq[0], jxjsq[2], xh}, "num");
						if (!StringUtils.isNull(mcs) && StringUtils.isEqual(mcs, "0")) {
							return false;
						}
					}
					if (tmpList[0].equalsIgnoreCase("sjdxspfmc")) {//十佳大学生
						String mc = dao.getOneRs("select count(*) num from (select xh,mc from (select xh, (dense_rank() over (order by to_number(zf) desc nulls last)) mc from sjdxspfxzb) where xh=?) where to_number(mc)<="+tmpList[3]+"", new String[]{xh}, "num");
						
						String num = dao.getOneRs("select count(*) num from view_xsrychb where rychmc like '%十佳%' and xysh='通过' and xxsh='通过' and xh=?", new String[]{xh}, "num");
						int iNum = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
						if (!StringUtils.isNull(mc) && StringUtils.isEqual(mc, "0")) {
							return false;
						}
						if (iNum > 0) {
							return false;
						}
					}
					if (tmpList[0].equalsIgnoreCase("bjgkm")) {// 不及格科目
						String sql = "select count(*) num from cjb where to_number(cj)<60 and xh = ? and xn= ? and kcxz not like '%校选修课%' and kcmc not like '%形势与政策%' and upper(xkkh) not like '%BY%'";
						String num = dao.getOneRs(sql, new String[] { xh,
								jxjsq[0] }, "num");
						if ("no".equalsIgnoreCase(tmpList[3])) {
							if (!"0".equalsIgnoreCase(num)) {
								return false;
							}
						} else {
							if ("0".equalsIgnoreCase(num)) {
								return false;
							}
						}
					}
					if (tmpList[0].equalsIgnoreCase("wjcf")) {// 违纪处分
						String sql = "select count(*) num from view_wjcf where xh = ? and xn= ?";
						String num = dao.getOneRs(sql, new String[] { xh,
								jxjsq[0] }, "num");
						if ("no".equalsIgnoreCase(tmpList[3])) {
							if (!"0".equalsIgnoreCase(num)) {
								return false;
							}
						} else {
							if ("0".equalsIgnoreCase(num)) {
								return false;
							}
						}
					}
					if (tmpList[0].equalsIgnoreCase("xnxqcj")) {// 学年度每学期平均成绩
						String xqcj = tmpList[3];
						String sql = "select xq, trim(to_Char(avg(cj), '99.99')) cj"
								+ " from cjb where xh = ? and xn = ? group by xq";
						List<String> cjList = dao.getList(sql, new String[] {
								xh, jxjsq[0] }, "cj");
						for (int j = 0; j < cjList.size(); j++) {
							String avgCj = cjList.get(j);
							if (!Base.isNull(xqcj) && !Base.isNull(avgCj)) {
								if (Float.parseFloat(xqcj) > Float
										.parseFloat(avgCj)) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	
}
