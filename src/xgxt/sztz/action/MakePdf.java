/**
 * 创建于2007-8-29上午10:13:03
 * 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.sztz.action;

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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.sztz.form.SztzForm;
import xgxt.utils.CommonQueryDAO;

import common.Globals;

/**
 * @author lp
 * 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class MakePdf extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SztzForm chkUser = (SztzForm) form;
		DAO dao = DAO.getInstance();
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			if (act.equalsIgnoreCase("sztzprint")) {
				if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//长沙民政职业技术学院
					myActFwd = csmz_sztzPrint(mapping, form, request, response);
				}else{
					myActFwd = sztzPrint(mapping, form, request, response);
				}
			}else if(act.equalsIgnoreCase("xcxy_sztzprint")){//西昌学院证书打印查询
				myActFwd = xcxy_sztzprint(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("xcxy_sztzprintOne")){//西昌学院证书打印
				myActFwd = xcxy_sztzprintOne(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("csmz_sztzprintOne")){//长沙民政职业技术学院证书打印
				myActFwd = csmz_sztzprintOne(mapping,form,request,response);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			//chkUser.setErrMsg("出现灾难性故障，");
			return new ActionForward("/errMsg.do", false);
		}
	}
	private ActionForward sztzPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 证书打印
		DAO dao = DAO.getInstance();
		SztzForm sztzForm = (SztzForm) form;
		HttpSession session = request.getSession();
		Vector<Object> rs = new Vector<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String xxdm = dao.getXxdm();
		String sql = "";// sql语句
		String querry = "";
		String rsNum = "0";// 返回的记录数
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String bmdm = sztzForm.getXydm();
		String zydm = sztzForm.getZydm();
		String bjdm = sztzForm.getBjdm();
		String xh = sztzForm.getXh();
		String nj = sztzForm.getNj();
		String hjjb=sztzForm.getHjjb();
		String xn = sztzForm.getXn();
		String kmdm = sztzForm.getKmdm();
		if (userType.equalsIgnoreCase("xy")) {
			bmdm = userDep;
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){//西昌学院
			return new ActionForward("/xcxy_sztzprint.do",false);
		}
        String tips = "素质拓展 - 拓展成果认证 - 证书打印";
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 - 活动认证 - 证书打印";
		}
		request.setAttribute("tips",tips);
		sql = "select dqxn,dqnd,replace(dqxn||'-'||dqnd,'200','') sj from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
				"dqnd", "sj" });						
		xn =colList[0];
		String nd = colList[1];
		sztzForm.setXn(xn);
		sztzForm.setNd(nd);
		String sj = colList[2];
		request.setAttribute("sj", sj);

		if ((hjjb == null) || hjjb.equalsIgnoreCase("")) {
			hjjb="";
		} else {
			map.put("hjjb", hjjb);
		}

		map.put("xn", sztzForm.getXn());
		sql = "select kmdm,kmm from sztz_kmdmb";
		List kmList = dao.getList(sql, new String[] {}, new String[] {
				"kmdm", "kmm" });
		request.setAttribute("kmList", kmList);// 发送素质拓展科目列表

		querry = getQuery(xn,kmdm,nj,bmdm,zydm,bjdm,xh);//查询条件

		if (userType.equalsIgnoreCase("xy")) {
			sztzForm.setXydm(userDep);
		}

		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){//江苏信息职业技术学院
			colList = new String[] { "行号", "xh", "xm", "zymc", "bjmc","xmmc", "kmm","jbmc","xf" };
			sql = "select rownum 行号,rownum r,c.* from (select  * from view_sztzcj "+ querry
			+ "order by xh) c";
			colListCN = dao.getColumnNameCN(colList, "view_sztzcj");				
		}else {
			colList = new String[] { "行号", "xh", "xm", "zymc", "bjmc","xmmc", "kmmc","jxm","xf" };
			sql = "select rownum 行号,rownum r,c.* from (select  * from view_tzcgcjxx "+ querry
			+ "order by xh) c";
			colListCN = dao.getColumnNameCN(colList, "view_tzcgcjxx");
		}

		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {}, colList, sztzForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getXyZyBjxx(request,dao);//常用数据列表
		request.setAttribute("writeAble", writeAble);// 发送读写权限			
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rsa", map);			
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);			
		return mapping.findForward("success");
	}
	private ActionForward xcxy_sztzprint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		Vector<Object> rs = new Vector<Object>();
		String querry = "";
		String sql = "";
		String rsNum = "0";// 返回的记录数
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh().trim();
		String userSpecType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();	
		if(userSpecType.equalsIgnoreCase("xy")){
			xy = userDep;
			dataSearchForm.setXydm(xy);
		}
		querry = getQuery("","",nj,xy,zy,bj,xh);//查询条件

//		sql = "select * from (select * from(select a.*,rownum r from( select distinct xh,xm,xb,nj,xydm,xymc,bjdm,bjmc,zydm,zymc from view_sztzcj )a " + querry + ") where rownum<="
//		+ (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) 
//		+ ") where r>" + dataSearchForm.getPages().getStart();
		sql = "select a.*,rownum r from( select distinct xh,xm,xb,nj,xydm,xymc,bjdm,bjmc,zydm,zymc from view_sztzcj )a " + querry;
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc","bjmc"};		
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		//分页
//		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(distinct xh ) count from view_sztzcj " + querry, new String[]{}, "count")));		
		getXyZyBjxx(request,dao);//常用数据列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return mapping.findForward("success");
	}
	/**西昌学院素质拓展证书单个打印*/
	private ActionForward xcxy_sztzprintOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String xh = request.getParameter("xh");
		String fzrq = request.getParameter("fzrq");//颁证日期
		String mb = request.getParameter("mb");//模板
		String sql = "";
		String forward = "";//跳转标志		
		HashMap<String, String> rsMap = new HashMap<String, String>();
		HashMap<String, String> csMap = new HashMap<String, String>();
		HashMap<String, String> rxMap = new HashMap<String, String>();
		HashMap<String, String> fzMap = new HashMap<String, String>();
		HashMap<String, String> zsbhMap = new HashMap<String, String>();

		String [] colList = {"xh","xm","xb","xymc","zymc","mzmc","lydq","zzmmmc","nj"};
		//学生基本信息
		sql = "select xh,xm,xb,xymc,zymc,mzmc,jg lydq,zzmmmc,nj from view_xsxxb where xh=?"; 
		rsMap = dao.getMap(sql, new String[]{xh}, colList);
		request.setAttribute("rsMap", rsMap);

		zsbhMap.put("zsbh", "5110628"+xh);//证书编号
		request.setAttribute("bhMap", zsbhMap);

		if(!Base.isNull(mb)){
			if(mb.equalsIgnoreCase("0")){//证书第一页，学生相关基本信息
				//学生出生日期
				sql = " select csrq from view_xsxxb where xh=? ";
				String csrq=dao.getOneRs(sql, new String[] {xh}, "csrq");	
				if(csrq == null || "".equalsIgnoreCase(csrq)){
					csMap.put("csNYR", "-年-月-日");
				}else if(csrq.indexOf("-")<0 && !csrq.equalsIgnoreCase("")){
					csMap.put("csNYR", csrq.substring(0,4)+"年"+csrq.substring(4,6)+"月");					
				}else{
					csMap.put("csNYR", csrq.substring(0,4)+"年"+csrq.substring(csrq.indexOf("-")+1,csrq.lastIndexOf("-"))+"月"+csrq.substring(csrq.lastIndexOf("-")+1,csrq.length())+"日");
				}
				request.setAttribute("csMap", csMap);

				//学生入学日期
				sql = "select rxrq rxny from view_xsxxb where xh=?";
				String rxny=dao.getOneRs(sql, new String[] {xh}, "rxny");	
				
				if(rxny == null || "".equalsIgnoreCase(rxny)){			
					rxMap.put("rxNYR", "-年-月-日");							
				}else{
					if(rxny.length()==8){
						rxMap.put("rxNYR",rxny.substring(0,4)+"年"+rxny.substring(4,6)+"月"+rxny.substring(6,8)+"日");			
					}else{
						rxMap.put("rxNYR",rxny.substring(0,4)+"年"+rxny.substring(5,7)+"月"+rxny.substring(8,10)+"日");			
					}
//					if(rxny.indexOf("-")<0){
//						rxMap.put("rxNYR",csrq.substring(0,4)+"年"+csrq.substring(4,6)+"月"+csrq.substring(6,8)+"日");					
//					}else{
//						rxMap.put("rxNYR",rxny.substring(0,4)+"年"+rxny.substring(rxny.indexOf("-")+1,rxny.lastIndexOf("-"))+"月"+rxny.substring(rxny.lastIndexOf("-")+1,rxny.length())+"日");			
//					}					
				}
				
				//发证日期
				sql = "select (substr(nvl('" + fzrq + "','0000-00-00'),1,4)||'年'||substr(nvl('" + fzrq + "','0000-00-00'),6,2)||'月'||substr(nvl('" + fzrq + "','0000-00-00'),9,2)||'日')fzNYR from dual";
				fzMap = dao.getMap(sql, new String[]{}, new String[]{"fzNYR"});			
				request.setAttribute("fzMap", fzMap);
				request.setAttribute("rxMap", rxMap);
				forward = "successO";								
			}else if(mb.equalsIgnoreCase("1")){//证书第二页，证书面信息
				//发证日期
				sql = "select substr(nvl('" + fzrq + "','0000-00-00'),1,4) fzN,substr(nvl('" + fzrq + "','0000-00-00'),6,2) fzY,substr(nvl('" + fzrq + "','0000-00-00'),9,2) fzR from dual";
				fzMap = dao.getMap(sql, new String[]{}, new String[]{"fzN","fzY","fzR"});
				request.setAttribute("fzMap", fzMap);
				forward = "successT";
			}else if(mb.equalsIgnoreCase("2")){//证书第三页，具体事项及内容
				SztzForm dataSearchForm = (SztzForm) form;
				dataSearchForm.getPages().setPageSize(15);				 
				sql = "select a.* from ( select (case when (select b.kmm from (select b.kmm ,rownum ro from" +
				" (select kmm from view_sztzcj where xh ='"+xh+"' order by kmm,xn)b)b where b.ro=a.r-1  )=a.kmm then null else a.kmm end)kmm,"
				+ "a.xn,(case when (select b.xmmc from (select b.xmmc, rownum ro from (select xmmc from view_sztzcj where xh = '"+xh+"' order " +
				"by kmm,xmmc,xn) b) b where b.ro = a.r - 1) = a.xmmc then null else a.xmmc end) xmmc,(case when a.cg is null then a.jbmc else" +
				" a.cg end )jbmc ,(select xqmc from xqdzb where xqdm = a.xq )xqmc,a.r from "
				+"(select (select cg from sztz_xfsbb a where a.xh||a.nd||a.xn||a.xq||a.tzxmdm=xh||nd||xn||xq||xmdm and rownum=1 )cg,"
				+" a.kmm,a.xn,a.xmmc,a.jbmc , a.xq,rownum r from (select xmdm,kmm,xn,xmmc,jbmc,xq from view_sztzcj where xh ='"+xh+"' order by kmm,xmmc,xn,xq) a)a where rownum<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) 
				+ ")a where r>" + dataSearchForm.getPages().getStart();

				//分页
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(xh ) cout from view_sztzcj where xh=? ", new String[]{xh}, "cout")));		
				
				List<HashMap<String,String>> rs = dao.getList(sql,  new String[] {}, new String[]{"kmm","xn","xqmc","xmmc","jbmc"});
				

				request.setAttribute("rs",rs);//发送数据集
				forward = "successTh";
			}

		}
		request.setAttribute("xh", xh);
		request.setAttribute("fzrq",fzrq);//颁证日期
		request.setAttribute("mb",mb);//正是模板号

		return mapping.findForward(forward);
	}
	/**
	 * 
	 * @param request
	 * @param dao
	 * 设置学年学期年纪学院专业班级信息
	 */
	private static void getXyZyBjxx(HttpServletRequest request, DAO dao) {
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");		
		xydm = xydm==null ? "":xydm;
		zydm = zydm==null ? "":zydm;
		nj = nj==null ? "":nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// 发送班级列表	
	}
	/**
	 * @param xn
	 * @param kmdm
	 * @param nj
	 * @param bmdm
	 * @param zydm
	 * @param bjdm
	 * @param xh
	 * @return 查询条件字符串
	 */
	public static String getQuery(String xn,String kmdm,String nj,String bmdm,String zydm,String bjdm,String xh){
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");// sql条件
		querry.append((Base.isNull(xn))?" and 1=1 ":"and xn = '" + xn + "' ");
		querry.append((Base.isNull(kmdm))?" and 1=1 ":" and kmdm = '"+kmdm+"' ");
		querry.append((Base.isNull(nj))?" and 1=1 ":" and nj = '"+nj+"' ");
		querry.append((Base.isNull(bmdm))?" and 1=1 ":" and xydm = '"+bmdm+"' ");
		querry.append((Base.isNull(zydm))?" and 1=1 ":" and zydm = '"+zydm+"' ");
		querry.append((Base.isNull(bjdm)?" and 1=1 ":" and bjdm = '"+bjdm+"' "));
		querry.append((Base.isNull(xh))?" and 1=1 ":" and xh = '"+xh+"' ");
		return querry.toString();
	}
	/**长沙民政素质拓展证书打印查询*/
	public ActionForward  csmz_sztzPrint (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		Vector<Object> rs = new Vector<Object>();
		String querry = "";
		StringBuffer sql = new StringBuffer();
		String rsNum = "0";// 返回的记录数
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = DealString.toGBK(dataSearchForm.getXh().trim());
		dataSearchForm.setXh(xh);
		String xn = dataSearchForm.getXn();
		String userSpecType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();	
		if(userSpecType.equalsIgnoreCase("xy")){
			xy = userDep;
			dataSearchForm.setXydm(xy);
		}
		if(xn==null){
			xn = Base.currXn;
			dataSearchForm.setXn(Base.currXn);
		}
		querry = getQuery(xn,"",nj,xy,zy,bj,xh);//查询条件
//		querry +=" and fdysh='通过' and xysh='通过' and xxsh='通过' ";
		querry +=" and sfdy='是' ";
		//sql = " select distinct xh,xm,xb,nj,xymc,zymc,bjmc,(case when zxf<1 then '0'||to_char(ltrim(trim (zxf),'0')) else trim(zxf) end )zxf from view_tzcgrzxx  "+querry ;
		
		sql.append(" select distinct xh,xm,xb,nj,xymc,zymc,bjmc,(case when zxf<1 then '0'||to_char(ltrim(trim (zxf),'0')) else trim(zxf) end )zxf from (");	
		sql.append(" select distinct xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,(select sum(xf) zxf from  view_tzcgcjxx b "+querry+" and a.xh=b.xh )zxf  ");
		sql.append(" from view_tzcgcjxx a "+querry+" )");
		
		String[] colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","zxf"};
		String[] colListCN = new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","素质总分"};
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getXyZyBjxx(request,dao);//常用数据列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return new ActionForward("/sztz/print/csmz/csmzprint.jsp",false);
	}
	/**长沙民政素质拓展证书单个打印*/
	public ActionForward csmz_sztzprintOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		DAO dao = DAO.getInstance();
		String xh = request.getParameter("xh");		
		String fzrq = request.getParameter("fzrq");//颁证日期
		String mb = request.getParameter("mb");//模板
		String xn = request.getParameter("xn");//学年
		String xqRz = request.getParameter("xqRz");
		xqRz = Base.isNull(xqRz)?"one":xqRz;
		String sql = "";
		String forward = "";//跳转标志
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String [] colList = {"xh","xm","xb","xymc","zymc","mzmc","lydq","zzmmmc","nj"};
		//学生基本信息
		sql = "select xh,xm,xb,xymc,zymc,mzmc,jg lydq,zzmmmc,nj from view_xsxxb where xh=?";
		rsMap = dao.getMap(sql, new String[]{xh}, colList);
		rsMap.put("xxmc",StandardOperation.getXxmc());//学校名称
		if(!Base.isNull(mb)){
			if(mb.equalsIgnoreCase("0")){//证书第一页，学生相关基本信息
				//学生出生日期
				sql = "select a.csrq csrq from view_xsxxb a where a.xh=?";
				String csrq=dao.getOneRs(sql, new String[] {xh}, "csrq");	
//				if(csrq == null || "".equalsIgnoreCase(csrq)){
//					rsMap.put("csNYR", "-年-月-日");
//				}else if(csrq.indexOf("-")<0 && !csrq.equalsIgnoreCase("")){
//					rsMap.put("csNYR", csrq.substring(0,4)+"年"+csrq.substring(4,6)+"月");					
//				}else{
//					rsMap.put("csNYR", csrq.substring(0,4)+"年"+csrq.substring(csrq.indexOf("-")+1,csrq.lastIndexOf("-"))+"月"+csrq.substring(csrq.lastIndexOf("-")+1,csrq.length())+"日");
//				}
				rsMap.put("csNYR",Base.isNull(csrq)?"":csrq);
				//学生入学日期
				sql = "select nvl(a.rxrq,(select c.rxny from bks_xsjbxx c where c.xh=a.xh) )rxny from xsxxb a,view_xsjbxx b where a.xh=b.xh and a.xh=?";
				String rxny=dao.getOneRs(sql, new String[] {xh}, "rxny");	
//				if(rxny == null || "".equalsIgnoreCase(rxny)){			
//					rsMap.put("rxNYR", "-年-月-日");							
//				}else if(rxny.indexOf("-")<0){					
//					rsMap.put("rxNYR",csrq.substring(0,4)+"年"+csrq.substring(4,6)+"月"+csrq.substring(6,8)+"日");					
//				}else{
//					rsMap.put("rxNYR",rxny.substring(0,4)+"年"+rxny.substring(rxny.indexOf("-")+1,rxny.lastIndexOf("-"))+"月"+rxny.substring(rxny.lastIndexOf("-")+1,rxny.length())+"日");			
//				}
				rsMap.put("rxNYR",Base.isNull(rxny)?"":rxny);
				//发证日期
   		        if(Base.isNull(fzrq)){
					fzrq = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd')fzrq from dual", new String[]{}, "fzrq");
				}
				sql = "select substr(nvl('" + fzrq + "','0000-00-00'),1,4)fzN, substr(nvl('" + fzrq + "','0000-00-00'),6,2)fzY,substr(nvl('" + fzrq + "','0000-00-00'),9,2)fzR from dual ";
				String[] fzNyr = dao.getOneRs(sql, new String[]{}, new String[]{"fzN","fzY","fzR"});
				rsMap.put("fzN", fzNyr[0]);
				rsMap.put("fzY",fzNyr[1]);
				rsMap.put("fzR",fzNyr[2]);
				rsMap.put("zsbh","43-"+"10827"+"-"+xh);
				forward = "successO";
			}else if(mb.equalsIgnoreCase("1")){//证书第二页，获奖选项内容
				Vector<Object> rs1 = new Vector<Object>();
				Vector<Object> rs2 = new Vector<Object>();
				String sql1 = "" ;
				String sql2 = "";								
				String sqlCon = "";
//				SztzForm dataSearchForm = (SztzForm) form;
				sqlCon = "select ((substr(nvl(zbsj,'00000000'),1,4))||'年'||(substr(nvl(zbsj,'00000000'),5,2))" 
					+"||'月'||'  '||xmmc||'   '||cyjs||'  '||jxm||'  '||xmjb)jxnr from view_tzcgcjxx ";
				sql1 =  sqlCon+" where kmmc like '%思想政治%'  and xn='"+xn+"' and xh='"+xh+"'   ";//第一学期
				sql2 = sqlCon+" where kmmc like '%社会实践%'  and xn='"+xn+"'and xh='"+xh+"'   ";
				if(xqRz!=null&&xqRz.equalsIgnoreCase("tow")){//第二学期
					sql1 = sqlCon+"  where kmmc like '%科技学术%' and xn='"+xn+"' and xh='"+xh+"' ";
					sql2 = sqlCon+"  where kmmc like '%文化艺术%' and xn='"+xn+"' and xh='"+xh+"' ";
				}else if(xqRz!=null&&xqRz.equalsIgnoreCase("three")){//第三学期
					sql1 = sqlCon+" where kmmc like '%社团活动%' and xn='"+xn+"' and xh='"+xh+"'  ";
					sql2 = sqlCon+"  where kmmc like '%技能培训%' and xn='"+xn+"' and xh='"+xh+"' ";
				}
				sql1+=" and sfdy='是'";
				sql2+=" and sfdy='是'";
				rs1.addAll(dao.rsToVator(sql1, new String[] {}, new String[]{"jxnr"}));
				rs2.addAll(dao.rsToVator(sql2, new String[] {}, new String[]{"jxnr"}));
				//不满8条后记录，起始条用‘——’填充，填充一条即可			
				String[] tmp = new String[]{"——  ——  ——  —— ——  "};
				int rs1Len = rs1.size();
				int rs2Len = rs2.size();
				if(rs1Len<8){//记录不满8条起始条用‘——’填充，后用空格填充一条即可		
					for(int i=rs1Len;i<8;i++){
						if(i==rs1Len){
							rs1.add(i, tmp);
						}else{
							rs1.add(i, new String[]{""});
						}						
					}					 
				}
				if(rs2Len<8){//记录不满8条起始条用‘——’填充，后用空格填充一条即可		
					for(int i=rs2Len;i<8;i++){
						if(i==rs2Len){
							rs2.add(i, tmp);
						}else{
							rs2.add(i,new String[]{"  "});
						}
					}					 
				}
				//不满8条用	‘——’填充结束
				request.setAttribute("rs1",rs1);
				request.setAttribute("rs2",rs2);
				forward = "successT";
			}else if(mb.equalsIgnoreCase("2")){//证书第三页，认证所需内容
				//发证日期
   		        if(Base.isNull(fzrq)){
					fzrq = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd')fzrq from dual", new String[]{}, "fzrq");
				}
				sql = "select substr(nvl('" + fzrq + "','0000-00-00'),1,4)fzN, substr(nvl('" + fzrq + "','0000-00-00'),6,2)fzY,substr(nvl('" + fzrq + "','0000-00-00'),9,2)fzR from dual ";
				String[] fzNyr = dao.getOneRs(sql, new String[]{}, new String[]{"fzN","fzY","fzR"});
				rsMap.put("fzN", fzNyr[0]);
				rsMap.put("fzY",fzNyr[1]);
				rsMap.put("fzR",fzNyr[2]);
				forward = "successTH";
			}

		}
		request.setAttribute("xqRz",xqRz);
		request.setAttribute("xn",xn);
		request.setAttribute("fzrq",fzrq);
		request.setAttribute("mb",mb);
		request.setAttribute("xh", xh);
		request.setAttribute("rsMap", rsMap);			
		return mapping.findForward(forward);
	}
}
