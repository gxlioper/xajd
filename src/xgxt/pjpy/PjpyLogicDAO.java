package xgxt.pjpy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.wjcf.gdby.WjcfGdbyService;

/**
 * @author Administrator
 *
 */
public class PjpyLogicDAO {


	public static ActionForward zyszjfwh(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance(); 
		PjpyForm sif = (PjpyForm) form; 
		String tableName;
		String xh = sif.getXh();
		String xm = sif.getXm();
		String nj = sif.getNj();
		String xydm = sif.getXydm();
		String zydm = sif.getZydm();
		String bjdm = sif.getBjdm();
		String xn = sif.getXn();
		String xq = sif.getXq();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
		}

		sif.setXn(xn);
		sif.setXydm(xydm);
		sif.setZydm(zydm);
		sif.setBjdm(bjdm);
		sif.setNj(nj);
		sif.setXh(xh);
		sif.setXm(xm);
		String isFdy = session.getAttribute("isFdy").toString();
		String userName = session.getAttribute("userName").toString();

		String xxdm = StandardOperation.getXxdm();

		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){//浙江商业职业技术学院
			return new ActionForward("/pjpy_zjsyzy.do?method=zyszjfwh",false);
		}
		String[] cols_en = null;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			cols_en = new String []{"xh","xm","xn","xq","xymc","zymc","bjmc","dyzf","dycxdj"};
			request.setAttribute("showjsxx", "showjsxx");
		} else{
			cols_en = new String []{"xh","xm","xn","xq","xymc","zymc","bjmc"};
		}

		tableName = "view_zhszcp_dyszjfwh";
		String[] cols_cn = dao.getColumnNameCN(cols_en, tableName);
		///topTr 用于生成查询结果的表头
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<cols_en.length;i++){
			HashMap<String, String> temmap = new HashMap<String, String>();
			temmap.put("en",cols_en[i]);
			temmap.put("cn", cols_cn[i]);
			topTr.add(temmap);
		}
		StringBuffer sql = new StringBuffer("select a.*,rownum r from ");
		sql.append(tableName);
		sql.append(" a where 1=1");
		sql.append((nj==null || nj.trim().length()<1) ? "" : " and nj='"+nj+"'");
		sql.append((xydm==null || xydm.trim().length()<1)? "" : " and xydm='"+xydm+"'");
		sql.append((zydm==null || zydm.trim().length()<1) ? "" : " and zydm='"+zydm+"'");
		sql.append((bjdm==null || bjdm.trim().length()<1)? "" : " and bjdm='"+bjdm+"'");
		sql.append((xn==null || xn.trim().length()<1) ? "" : " and xn='"+xn+"'");
		sql.append((xq==null || xq.trim().length()<1) ? "" : " and xq='"+xq+"'");

		String[] inputs = {xh,xm};
		String go = request.getParameter("go");
		searchWithGo(request, dao, cols_en, topTr, sql, go,inputs, sif);
		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
		if ("true".equalsIgnoreCase(isFdy)) {
			WjcfGdbyService service = new WjcfGdbyService();
			bjList = service.getFdybjList(userName);
		} else {
			bjList = dao.getBjList(xydm, zydm, nj);
		}
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("userType", userType);
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", bjList);
		return mapping.findForward("success");
	}

	public static ActionForward dyszwh(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception{
		ActionForward af = new ActionForward();
		DAO dao = DAO.getInstance();
		PjpyForm myForm = (PjpyForm) form;
		String xh = request.getParameter("xh");
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String doType = request.getParameter("doType");
		String sql = "";
		ActionForward newFwd= new ActionForward();
		String xxdm = StandardOperation.getXxdm();
		sql = "select * from rychdmb";
		List rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		request.setAttribute("rychList", rychList);
//		江苏信息职业技术学院
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			String modiXn = request.getParameter("xn");
			String modiXq = request.getParameter("xq");
			@SuppressWarnings("unused")
			String nd = dao.getConf(3);
			if("add".equalsIgnoreCase(doType)){
				xh = myForm.getXh();
				String jthdbx = myForm.getJthdbx();
				String skxxbx = myForm.getSkxxbx();
				String ldwsbx = myForm.getLdwsbx();
				String xjxgbx = myForm.getXjxgbx();
				String qtbx = myForm.getQtbx();
				String zzxx = myForm.getZzxx();
				String xxtd = myForm.getXxtd();
				String ddxy = myForm.getDdxy();
				String shhd = myForm.getShhd();
				String fzgn = myForm.getFzgn();
				String gbrzjf = myForm.getGbrzjf();
				String xfxfjf = myForm.getXfxfjf();
				String jjsjjf = myForm.getJjsjjf();
				String glbsjf = myForm.getGlbsjf();
				String wjcljf = myForm.getWjcljf();
				String dyzf = myForm.getDyzf();
				String[] cols = {"xh","xn","xq","jthdbx","skxxbx","ldwsbx","xjxgbx","qtbx","zzxx","xxtd","ddxy","shhd","fzgn","gbrzjf","xfxfjf","jjsjjf","glbsjf","wjcljf","dyzf"};
				String[] values = {xh,xn,xq,jthdbx,skxxbx,ldwsbx,xjxgbx,qtbx,zzxx,xxtd,ddxy,shhd,fzgn,gbrzjf,xfxfjf,jjsjjf,glbsjf,wjcljf,dyzf};
				sql = "select xh from zhszcp_dyszjfwh where xh=? and xn=? and xq=?";
				String exist = dao.returntag(sql, new String[]{xh,xn,xq});
				boolean doresult = false;

				if("notempty".equalsIgnoreCase(exist)){
					doresult = StandardOperation.update("zhszcp_dyszjfwh", cols, values, "xh||xn||xq", xh+xn+xq, request);
					/*if(doresult){
						sql = "{call JSXX_SINGLECOUNT(?,?,?,?)}";
						doresult=dao.runProcuder(sql, new String[] {xn, nd, String.valueOf(Integer.parseInt(xq)), xh});
					}*/
				} else{
					doresult = StandardOperation.insert("zhszcp_dyszjfwh", cols, values, request);
					/*sql = "{call JSXX_SINGLECOUNT(?,?,?,?)}";
					doresult=dao.runProcuder(sql, new String[] {xn, nd, String.valueOf(Integer.parseInt(xq)), xh});*/
				}
				request.setAttribute("doresult", doresult);
			}
			HashMap<String, String> rs = new HashMap<String, String>();
			String[] cols = {"xh","xm","xn","xq","jthdbx","skxxbx","ldwsbx","xjxgbx","qtbx","zzxx","xxtd","ddxy","shhd","fzgn","gbrzjf","xfxfjf","jjsjjf","glbsjf","wjcljf",
					"psbxhj","zhpjhj","fjfhj","dyzf"};
			if(modiXn == null || "".equalsIgnoreCase(modiXn)){
				sql = "select xh,xm,xn,xq,jthdbx,skxxbx,ldwsbx,xjxgbx,qtbx,zzxx,xxtd,ddxy,shhd,fzgn,gbrzjf,xfxfjf,jjsjjf,glbsjf,wjcljf," +
				" psbxhj,zhpjhj,fjfhj,dyzf from view_zhszcp_dyszjfwh where xh=? and xn is null and xq is null ";
				rs = dao.getMap(sql, new String[]{xh}, cols);
				rs.put("xn", dao.getConf(2));
				rs.put("xq", dao.getConf(6));
			}else{
				sql = "select xh,xm,xn,xq,jthdbx,skxxbx,ldwsbx,xjxgbx,qtbx,zzxx,xxtd,ddxy,shhd,fzgn,gbrzjf,xfxfjf,jjsjjf,glbsjf,wjcljf," +
				" psbxhj,zhpjhj,fjfhj,dyzf from view_zhszcp_dyszjfwh where xh=? and xn=? and xq=? ";
				rs = dao.getMap(sql, new String[]{xh,modiXn,modiXq}, cols);
			}
			List<HashMap<String, String>> xnList = dao.getXnndList();
			//myForm.setXn((rs.get("xn")==null)? dao.getOneRs("select dqxn xn from xtszb", new String[]{}, "xn") : rs.get("xn"));
			request.setAttribute("xnList", xnList);
			request.setAttribute("xqList", dao.getXqList());
			request.setAttribute("rs", rs);
			newFwd = new ActionForward("/pjpy/dycphzwh_jsxx.jsp", false);
			return newFwd;
		}

		if("add".equalsIgnoreCase(doType)){//是增加操作时
			//when the doType is equal to add then goes here
			xh = myForm.getXh();
			String ryjf = myForm.getRyjf();
			String rzjf = myForm.getRzjf();
			String bsjf = myForm.getBsjf();
			String qsbsjf = myForm.getQsbsjf();
			String cjhdjf = myForm.getCjhdjf();
			String wjkf = myForm.getWjkf();
			String jttbkf = myForm.getJttbkf();
			String qtjjfxx = myForm.getQtjjfxx();
			String hjjf = myForm.getHjjf();
			String fkjf = myForm.getFkjf();
			String kccjbjgkf = myForm.getKccjbjgkf();
			String qtzykf = myForm.getQtzykf();
			String tybsjf = myForm.getTybsjf();
			String jnjf = myForm.getJnjf();
			String pjljf = myForm.getPjljf();
			String cpcq = myForm.getCpcq();
			String stsz = myForm.getStsz();
			String dlqk = myForm.getDlqk();
			String hdqk = myForm.getHdqk();
			String yydjjianf = myForm.getYydjjianf();
			String jsjjianf = myForm.getJsjjianf();
			String kyjf = myForm.getKyjf();
			String zxksjf = myForm.getZxksjf();
			String yydjjf = myForm.getYydjjianf();
			String jsjjf = myForm.getJsjjf();
			String qtjf = myForm.getQtjf();
			String sxpdbx = myForm.getSxpdbx();

			String[] cols = {"xh","xn","xq","ryjf","rzjf","bsjf","qsbsjf","cjhdjf","wjkf","jttbkf","qtjjfxx","hjjf",
					"fkjf","kccjbjgkf","qtzykf","tybsjf","jnjf","pjljf","cpcq","stsz","dlqk","hdqk","yydjjianf","jsjjianf",
					"kyjf","zxksjf","yydjjf","jsjjf","qtjf","sxpdbx"};
			String[] values = {xh,xn,xq,ryjf,rzjf,bsjf,qsbsjf,cjhdjf,wjkf,jttbkf,qtjjfxx,hjjf,fkjf,kccjbjgkf,
					qtzykf,tybsjf,jnjf,pjljf,cpcq,stsz,dlqk,hdqk,yydjjianf,jsjjianf,kyjf,zxksjf,yydjjf,jsjjf,
					qtjf,sxpdbx};
			//sql = "insert into zhszcp_dyszjfwh(xh,xn,xq,ryjf,rzjf,bsjf,qsbsjf,cjhdjf,wjkf,jttbkf,qtjjfxx,hjjf,fkjf,kccjbjgkf,qtzykf,tybsjf,jnjf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sql = "select xh from zhszcp_dyszjfwh where xh=? and xq=? and xn=?";
			String tmpXh = dao.getOneRs(sql, new String[]{xh,xq,xn}, "xh");
			boolean exist = (tmpXh!=null && !"".equalsIgnoreCase(tmpXh)) ? true : false;
			boolean doresult = false;
			if(exist){
				doresult = StandardOperation.update("zhszcp_dyszjfwh", cols, values, "xh||xn||xq", xh+xn+xq, request);
			} else{
				doresult = StandardOperation.insert("zhszcp_dyszjfwh", cols, values, request);
			}
			request.setAttribute("doresult", doresult);
		} 
		//不是增加的时候做如下操作
		sql = "select xh,xm,xn,xq,ryjf,rzjf,bsjf,qsbsjf,cjhdjf,wjkf,jttbkf,qtjjfxx,hjjf,fkjf,kccjbjgkf,qtzykf,tybsjf,jnjf from view_zhszcp_dyszjfwh where 1=1 ";
		HashMap<String, String> rs = new HashMap<String, String>();
		String[] cols = {"xh","xm","xn","xq","ryjf","rzjf","bsjf","qsbsjf","cjhdjf","wjkf","jttbkf","qtjjfxx","hjjf","fkjf","kccjbjgkf","qtzykf","tybsjf","jnjf"};

		//中北大学加分维护
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)){
			//以下为体育，人文，创新列表
			String xn_lrh=request.getParameter("xn");
			xn=xn_lrh;
			sql = "select xh,xm,xn,tyjsdm,tyjsmc,zyjsdm,zyrwjsmc,cxxmdm,cxxmmc from view_zhszcp_jfwh where 1=1 ";
			cols = new String[]{"xh","xm","xn","tyjsdm","tyjsmc","zyjsdm","zyrwjsmc","cxxmdm","cxxmmc"};
			List<HashMap<String, String>> tyxmList = dao.getList("select jsdm tyjsdm,jsmc tyjsmc from ZHCP_TYJSLB order by jsdm", new String[]{}, new String[]{"tyjsdm","tyjsmc"});
			List<HashMap<String, String>> rwxmList = dao.getList("select jsdm rwjsdm,jsmc rwjsmc from zhcp_zyjslb order by jsdm", new String[]{}, new String[]{"rwjsdm","rwjsmc"});
			List<HashMap<String, String>> cxxmList = dao.getList("select xmdm,xmmc from zhcp_cxlb order by xmdm", new String[]{}, new String[]{"xmdm","xmmc"});
			request.setAttribute("tyxmList", tyxmList);
			request.setAttribute("rwxmList", rwxmList);
			request.setAttribute("cxxmList", cxxmList);
			List<HashMap<String, String>> tyjfxmList = dao.getList("select b.jsdm tyjsdm,b.jsmc||'-------------------'||a.TYBSJF opt from zhszcp_tycp a,ZHCP_TYJSLB b where a.TYJSDM=b.jsdm and xn='"+xn+"' and xh=?", new String[]{xh},  new String[]{"tyjsdm","opt"});
			List<HashMap<String, String>> rwjfxmList = dao.getList("select b.jsdm rwjsdm,b.jsmc||'-------------------'||a.HJJF opt from zhszcp_zyrwcp a,zhcp_zyjslb b where a.zYJSDM=b.jsdm and xn='"+xn+"'  and xh=?", new String[]{xh}, new String[]{"rwjsdm","opt"});
			List<HashMap<String, String>> cxjfxmList = dao.getList("select b.xmdm ,b.xmmc||'-------------------'||a.CXDF opt from zhszcp_cxcp a,zhcp_cxlb b where a.CXXMDM=b.xmdm and xn='"+xn+"'  and xh=?", new String[]{xh},  new String[]{"xmdm","opt"});
			request.setAttribute("tyjfxmList", tyjfxmList);
			request.setAttribute("rwjfxmList", rwjfxmList);
			request.setAttribute("cxjfxmList", cxjfxmList);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			//浙江商业职业
			List<HashMap<String, String>> bsList = dao.getList("select distinct bsdm, bsmc from zhcp_bslb", new String[]{}, new String[]{"bsdm","bsmc"});
			List<HashMap<String, String>> qsPbList = dao.getList("select distinct mcdm, mcmc from zhcp_qspbb", new String[]{}, new String[]{"mcdm","mcmc"});
			List<HashMap<String, String>> hdList = dao.getList("select distinct cjhddm, cjhdmc from zhcp_cjhdb", new String[]{}, new String[]{"cjhddm","cjhdmc"});
			List<HashMap<String, String>> wjcfList = dao.getList("select distinct cfdm, cfmc from zhcp_cflb", new String[]{}, new String[]{"cfdm","cfmc"});
			List<HashMap<String, String>> zyjsList = dao.getList("select distinct jsdm, jsmc from zhcp_zyjslb", new String[]{}, new String[]{"jsdm","jsmc"});
			List<HashMap<String, String>> tyjsList = dao.getList("select distinct jsdm, jsmc from zhcp_tyjslb", new String[]{}, new String[]{"jsdm","jsmc"});
			request.setAttribute("bsList", bsList);
			request.setAttribute("qsPbList", qsPbList);
			request.setAttribute("hdList", hdList);
			request.setAttribute("wjcfList", wjcfList);
			request.setAttribute("zyjsList", zyjsList);
			request.setAttribute("tyjsList", tyjsList);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJMZYJSXY)){
			sql = "select xh,xm,xn,xq,sxpdbx,ryjf,rzjf,qtjf,jsjjf,yydjjf,bsjf,fkjf,zxksjf,kyjf," +
			"kccjbjgkf,jsjjianf,yydjjianf,hdqk,dlqk,stsz,cpcq,tybsjf,pjljf from view_zhszcp_dyszjfwh " +
			"where 1=1 ";		
			cols = new String[]{"xh","xm","xn","xq","sxpdbx","ryjf","rzjf","qtjf","jsjjf","yydjjf","bsjf","fkjf",
					"zxksjf","kyjf","kccjbjgkf","jsjjianf","yydjjianf","hdqk","dlqk","stsz","cpcq","tybsjf","pjljf"};
		}
		if(xn != null && !(xn.trim().equals(""))){
			sql += " and (xn='"+xn+"' or xn is null)";
		}
		if(xh != null && !(xh.trim().equals(""))){
			sql += " and xh='"+xh+"'";
		}
		if(xq != null && !(xq.trim().equals(""))){
			sql += " and xq='"+xq+"'";
		}
		HashMap<String, String> stu_map = new HashMap<String, String>();
		stu_map=dao.getMapNotOut("select xh,xm from view_xsjbxx where xh=?",new String[]{xh});
		rs = dao.getMap(sql, new String[]{}, cols);
		List<HashMap<String, String>> xnList = dao.getXnndList();
		rs.put("xn", (rs.get("xn")==null)? dao.getConf(2) : rs.get("xn"));
		rs.put("xh", stu_map.get("xh"));
		rs.put("xm", stu_map.get("xm"));
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("rs", rs);
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJMZYJSXY)){
			af = new ActionForward("/pjpy/zjjmzyjsxy/dycphzwh_zjjmzyjsxy.jsp");
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)){
			af = new ActionForward("/pjpy/dycphzwh_zbdx.jsp",false);
		} else {
			af = mapping.findForward("success");
		}

		return af; 
	}


	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @category <font color=red>绩点设定</font>
	 */
	public static ActionForward jdsd(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String[] title = {"奖学金代码","奖学金名称"};
		ArrayList<String[]> rs = dao.rsToVator("select jxjdm,jxjmc from jxjdmb where szjdflag is not null order by jxjdm", new String[]{}, new String[]{"jxjdm","jxjmc"});

		if("save".equalsIgnoreCase(doType)){
			String[] rsArr = (String[])rs.toArray();
//			String[] jxjArrForJd = new String[rsArr.length];
			for(int i=0;i<rsArr.length;i++){

			}
		}		
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}


	//主要用于上海工程技术大学的优秀、自强奖学金申请
	public static ActionForward applyForPrise(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception{
		//奖学金申请包括的用户主要是学生和管理员，
		//管理员具有各种权限包括增（提交申请）、删、改，而学生只能进行查询和提交申请
//		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		PjpyForm myForm = (PjpyForm)form;
		String haveRequested = request.getParameter("haveRequested");
		//String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String xh = "";
		String[] xnxq = StandardOperation.getXnXq();
		String doType = request.getParameter("doType");//操作方式
		String excellentStu = request.getParameter("excellentStu");//优秀学生奖学金代码
		String innovationfirst    = request.getParameter("innovationfirst");//创新甲奖学金代码
		String innovationsecond   = request.getParameter("innovationsecond");//创新乙奖学金代码
		String zqPrise      = request.getParameter("zqPrise");//自强奖学金代码
		String jxjdm  = request.getParameter("jxjdm");//获得哪种要申请的奖学金     
		String jdxz = "";//用于绩点限制
		double jxjjd = 0.0;//奖学金绩点
		double stuJd = 0.0;//学生的绩点
		//数据库操作变量定义
		String primaryKey   = "xh||jxjdm||xn";
		String pkValue      = "";
		String[] columns = null;
		String[] values  = null;
		String tableName = "xsjxjb";		
		boolean dbopForExcellence = false;
		boolean DbOpForInnovationFirst = false;
		boolean DbOpForInnovationSecond= false;
		boolean DbOpforzq         = false;
		boolean DbOperation       = false;

		HashMap<String, String> map = new HashMap<String, String>();
		int count = 0;
		if("save".equalsIgnoreCase(doType)){
			try{
				String dqsj = StandardOperation.getRightTime();
				xh = myForm.getXh();			
				if(excellentStu != null && excellentStu.trim().length()>0){	//特、一、二、三等奖学金
					count++;
					pkValue = xh+excellentStu+xnxq[0];
					columns = new String[]{"xh","jxjdm","xn","sqsj"};
					values = new String[]{xh,excellentStu,xnxq[0],dqsj};
					if(StandardOperation.testRecordExist(tableName,primaryKey,pkValue)){
						dbopForExcellence = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
					}else{
						dbopForExcellence = StandardOperation.insert(tableName, columns, values, request);
					}
				}
				if(innovationfirst != null && innovationfirst.trim().length()>0){	//创新甲等
					count+=5;
					pkValue = xh+innovationfirst+xnxq[0];
					columns=new String[]{"xh","jxjdm","xn","sqsj"};
					values =new String[]{xh,innovationfirst,xnxq[0],dqsj};
					if(StandardOperation.testRecordExist(tableName,primaryKey,pkValue)){						
						DbOpForInnovationFirst = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
					}else{
						DbOpForInnovationFirst = StandardOperation.insert(tableName, columns, values, request);
					}
				}
				if(innovationsecond != null && innovationsecond.trim().length()>0){	//创新乙等
					count+=7;
					pkValue = xh+innovationsecond+xnxq[0];
					columns=new String[]{"xh","jxjdm","xn","sqsj"};
					values =new String[]{xh,innovationsecond,xnxq[0],dqsj};
					if(StandardOperation.testRecordExist(tableName,primaryKey,pkValue)){
						DbOpForInnovationSecond = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
					}else{
						DbOpForInnovationSecond = StandardOperation.insert(tableName, columns, values, request);
					}
				}
				if(zqPrise != null && zqPrise.trim().length()>0){	//自强甲等、乙等
					count+=17;
					pkValue = xh+zqPrise+xnxq[0];
					columns=new String[]{"xh","jxjdm","xn","sqsj"};
					values =new String[]{xh,zqPrise,xnxq[0],dqsj};
					if(StandardOperation.testRecordExist(tableName,primaryKey,pkValue)){
						DbOpforzq = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
					}else{
						DbOpforzq = StandardOperation.insert(tableName, columns, values, request);
					}
				}
				//检测数据库操作结果
				switch(count){			
				case 1:  DbOperation = dbopForExcellence; break;
				case 5:  DbOperation = DbOpForInnovationFirst; break;
				case 6:  DbOperation = dbopForExcellence && DbOpForInnovationFirst;break;
				case 7:  DbOperation = DbOpForInnovationSecond; break;
				case 8:  DbOperation = dbopForExcellence && DbOpForInnovationSecond; break;
				case 12: DbOperation = DbOpForInnovationFirst && DbOpForInnovationSecond; break;
				case 13: DbOperation = dbopForExcellence && DbOpForInnovationFirst && DbOpForInnovationSecond;break;
				case 17: DbOperation = DbOpforzq;break;
				case 18: DbOperation = dbopForExcellence && DbOpforzq;break;
				case 22: DbOperation = DbOpForInnovationFirst && DbOpforzq;break;
				case 23: DbOperation = dbopForExcellence && DbOpForInnovationFirst && DbOpforzq;break;
				case 24: DbOperation = DbOpForInnovationSecond && DbOpforzq;break;
				case 25: DbOperation = 	dbopForExcellence && DbOpForInnovationSecond && DbOpforzq;break;
				case 29: DbOperation = DbOpForInnovationFirst && DbOpForInnovationSecond && DbOpforzq;break;
				case 30: DbOperation = dbopForExcellence && DbOpForInnovationFirst && DbOpForInnovationSecond && DbOpforzq;break; 	

				default : DbOperation = false;count=0;
				}
				request.setAttribute("dboperation", DbOperation);
				request.setAttribute("haveRequested", haveRequested);
			}catch(SQLException e){
				request.setAttribute("dboperation", DbOperation);
				request.setAttribute("haveRequested", haveRequested);
			}
		} else {
			if("student".equalsIgnoreCase(userOnLine)){
				if("yes".equalsIgnoreCase(haveRequested)){
					xh = userName;
//					int intJxjdm = new Integer(jxjdm).intValue();
					//todo 判断奖学金代码所对应的绩点
					jxjjd = Double.parseDouble(PjpyDAO.getJxjjd(jxjdm));
					stuJd = Double.parseDouble(PjpyDAO.getStuJd(xh, xnxq[0]));
					jdxz = (stuJd >= jxjjd) ? "yes" :"no";
					String[] outCols = {"xh","xm","xymc","zymc","bjmc","xb","nj","rxny","ssbh","qsdh","sjhm","mzmc"};
					String[] outVals = StandardOperation.getFixupStuInfo(xh);
					for(int i=0;i<outCols.length;i++){
						map.put(outCols[i], (outVals !=null)?outVals[i]:"");
					}				
				}
			} else {//学院或学校用户就直接到选择相应学生信息
				jdxz = "yes";
			}
		}

		if("yes".equalsIgnoreCase(haveRequested)){//如果已经访问过，那么就不再显示《层》
			request.setAttribute("haveRequested", haveRequested);
		}
		request.setAttribute("rs",map);
		request.setAttribute("jdxz", jdxz);
		return mapping.findForward("success");
	}



	/**
	 * @category 申请上海工程技术大学sony奖学金
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward applyForSonyPrise(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		PjpyForm  myForm = (PjpyForm) form;
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String jxjdm = request.getParameter("jxjdm");
		String[] xnxq = StandardOperation.getXnXq();
		String tableName = "";
		String[] columns = null;
		String[] values  = null;
		String primaryKey = "";
		String pkValue ="";
		String sysTime = StandardOperation.getRightTime();//获得系统当前时间
		boolean dbOperation = false;
		boolean exists = false;
		if("save".equalsIgnoreCase(doType)){
			String xh = Base.chgNull(myForm.getXh(), "", 1);
			String csny = Base.chgNull(myForm.getCsny(), "", 1);
			String email = Base.chgNull(myForm.getEmail(), "", 1);
			String lxdh = Base.chgNull(myForm.getLxdh(), "", 1);
			String zzmc = Base.chgNull(myForm.getZzmm(), "", 1);
			String mzmc = Base.chgNull(myForm.getMzmc(), "", 1);
			String rxny = Base.chgNull(myForm.getRxny(), "", 1);
			String hjjl = Base.chgNull(myForm.getHjjl(), "", 1);;
			String pwyj = Base.chgNull(myForm.getPwyj(), "", 1);
			String dsyj = Base.chgNull(myForm.getDsyj(), "", 1);

			String[] inputVals = {xh,jxjdm,xnxq[0],csny,email,lxdh,zzmc,mzmc,rxny,dsyj,hjjl, pwyj};
			String[] inputCols = {"xh","jxjdm","xn","csny","email","lxdh","zzmc","mzmc","rxny","dsyj","hjjl", "pwyj"};
			/*
			 * 申请sony奖学金要将信息保存到xsjxjb和xsjxjfzxxb两个表中，以下分别在处理时加入了信息
			 */
			tableName = "xsjxjb";
			primaryKey = "xh||jxjdm||xn";
			pkValue  = xh+jxjdm+xnxq[0];
			columns = new String[]{"xh","jxjdm","xn","sqsj"};
			values =  new String[]{xh,jxjdm,xnxq[0],sysTime};
			exists = StandardOperation.testRecordExist(tableName, primaryKey, pkValue);
			if(exists){				
				dbOperation = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
			} else{
				dbOperation = StandardOperation.insert(tableName, columns, values, request);
			}
			tableName = "xsjxjfzxxb";
			exists = StandardOperation.testRecordExist(tableName, primaryKey, pkValue);
			if(dbOperation){//首先检查有没有准确将记录保存到奖学金表中，如未成功执行则直接跳转
				if(exists){
					dbOperation = StandardOperation.update(tableName, inputCols, inputVals, primaryKey, pkValue, request);
				}else{
					dbOperation = StandardOperation.insert(tableName, inputCols, inputVals, request);
				}
				request.setAttribute("dboperation", dbOperation);
			} else{//如果奖学金表没有正确保存，则直接返回
				request.setAttribute("dboperation", dbOperation);
			}
		} else {//不是保存操作时
			if("student".equalsIgnoreCase(userOnLine)){
				String[] outVals = StandardOperation.getFixupStuInfo(userName);
				String[] outCols = {"xh","xm","xymc","zymc","bjmc","xb","nj","rxny","ssbh","qsdh","sjhm","mzmc"};
				for(int i=0;i<outCols.length;i++){
					map.put(outCols[i], (outVals!=null)?outVals[i]:"");
				}
			}
		}

		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 上海工程技术大学	交运奖学金
	 */
	public static ActionForward applyForJiaoyunPrise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		PjpyForm myForm = (PjpyForm) form;
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String jxjdm = request.getParameter("jxjdm");
		String[] xnxq = StandardOperation.getXnXq();
		String tableName = "";
		String[] columns = null;
		String[] values = null;
		String primaryKey = "";
		String pkValue = "";
		String sysTime = StandardOperation.getRightTime();// 获得系统当前时间
		boolean dbOperation = false;
		boolean exists = false;
		if ("save".equalsIgnoreCase(doType)) {
			String xh = Base.chgNull(myForm.getXh(), "", 1);
			String csny = Base.chgNull(myForm.getCsny(), "", 1);
			String email = Base.chgNull(myForm.getEmail(), "", 1);
			String lxdh = Base.chgNull(myForm.getLxdh(), "", 1);
			String zzmc = Base.chgNull(myForm.getZzmm(), "", 1);
			String mzmc = Base.chgNull(myForm.getMzmc(), "", 1);
			String rxny = Base.chgNull(myForm.getRxny(), "", 1);
			String sqly = Base.chgNull(myForm.getSqly(), "", 1);
			String beizhu = Base.chgNull(myForm.getBeizhu(), "", 1);
			String shjyyj = Base.chgNull(myForm.getShjyyj(), "", 1);
			String zysj = Base.chgNull(myForm.getZysj(), "", 1);

			String[] inputVals = { xh, jxjdm, xnxq[0], csny, email, lxdh, zzmc,
					mzmc, rxny, sqly, beizhu, shjyyj,zysj };
			String[] inputCols = { "xh", "jxjdm", "xn", "csny", "email",
					"lxdh", "zzmc", "mzmc", "rxny", "sqly", "beizhu", "shjyyj", "zysj" };
			/*
			 * 申请交运奖学金要将信息保存到xsjxjb和xsjxjfzxxb两个表中，以下分别在处理时加入了信息
			 */
			tableName = "xsjxjb";
			primaryKey = "xh||jxjdm||xn";
			pkValue = xh + jxjdm + xnxq[0];
			columns = new String[] { "xh", "jxjdm", "xn", "sqsj" };
			values = new String[] { xh, jxjdm, xnxq[0], sysTime };
			exists = StandardOperation.testRecordExist(tableName, primaryKey,
					pkValue);
			if (exists) {
				dbOperation = StandardOperation.update(tableName, columns,
						values, primaryKey, pkValue, request);
			} else {
				dbOperation = StandardOperation.insert(tableName, columns,
						values, request);
			}
			tableName = "xsjxjfzxxb";
			exists = StandardOperation.testRecordExist(tableName, primaryKey,
					pkValue);
			if (dbOperation) {// 首先检查有没有准确将记录保存到奖学金表中，如未成功执行则直接跳转
				if (exists) {
					dbOperation = StandardOperation.update(tableName,
							inputCols, inputVals, primaryKey, pkValue, request);
				} else {
					dbOperation = StandardOperation.insert(tableName,
							inputCols, inputVals, request);
				}
				request.setAttribute("dboperation", dbOperation);
			} else {// 如果奖学金表没有正确保存，则直接返回
				request.setAttribute("dboperation", dbOperation);
			}
		} else {// 不是保存操作时
			if ("student".equalsIgnoreCase(userOnLine)) {
				String[] outVals = StandardOperation.getFixupStuInfo(userName);
				String[] outCols = { "xh", "xm", "xymc", "zymc", "bjmc", "xb",
						"nj", "rxny", "ssbh", "qsdh", "sjhm", "mzmc" };
				for (int i = 0; i < outCols.length; i++) {
					map.put(outCols[i], (outVals != null) ? outVals[i] : "");
				}
			}
		}

		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}


	/**
	 * @author ChenHuamao E-MAIL:chhuma@hotmail.com
	 * @describe 上海工程技术大学	上联奖学金
	 */
	public static ActionForward applyForShanglianPrise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		PjpyForm myForm = (PjpyForm) form;
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String jxjdm = request.getParameter("jxjdm");
		String[] xnxq = StandardOperation.getXnXq();
		String tableName = "";
		String[] columns = null;
		String[] values = null;
		String primaryKey = "";
		String pkValue = "";
		String sysTime = StandardOperation.getRightTime();// 获得系统当前时间
		boolean dbOperation = false;
		boolean exists = false;
		if ("save".equalsIgnoreCase(doType)) {
			String xh = Base.chgNull(myForm.getXh(), "", 1);
			String csny = Base.chgNull(myForm.getCsny(), "", 1);
			String email = Base.chgNull(myForm.getEmail(), "", 1);
			String lxdh = Base.chgNull(myForm.getLxdh(), "", 1);
			String zzmc = Base.chgNull(myForm.getZzmm(), "", 1);
			String mzmc = Base.chgNull(myForm.getMzmc(), "", 1);
			String rxny = Base.chgNull(myForm.getRxny(), "", 1);
			String sqly = Base.chgNull(myForm.getSqly(), "", 1);
			String beizhu = Base.chgNull(myForm.getBeizhu(), "", 1);
			String pwyj = Base.chgNull(myForm.getPwyj(), "", 1);
			String hzjydw = Base.chgNull(myForm.getHzjydw(), "", 1);
			String zysj = Base.chgNull(myForm.getZysj(), "", 1);

			String[] inputVals = { xh, jxjdm, xnxq[0], csny, email, lxdh, zzmc,
					mzmc, rxny, sqly, beizhu, pwyj, hzjydw, zysj };
			String[] inputCols = { "xh", "jxjdm", "xn", "csny", "email",
					"lxdh", "zzmc", "mzmc", "rxny", "sqly", "beizhu", "pwyj", "hzjydw", "zysj" };
			/*
			 * 申请交运奖学金要将信息保存到xsjxjb和xsjxjfzxxb两个表中，以下分别在处理时加入了信息
			 */
			tableName = "xsjxjb";
			primaryKey = "xh||jxjdm||xn";
			pkValue = xh + jxjdm + xnxq[0];
			columns = new String[] { "xh", "jxjdm", "xn", "sqsj" };
			values = new String[] { xh, jxjdm, xnxq[0], sysTime };
			exists = StandardOperation.testRecordExist(tableName, primaryKey,
					pkValue);
			if (exists) {
				dbOperation = StandardOperation.update(tableName, columns,
						values, primaryKey, pkValue, request);
			} else {
				dbOperation = StandardOperation.insert(tableName, columns,
						values, request);
			}
			tableName = "xsjxjfzxxb";
			exists = StandardOperation.testRecordExist(tableName, primaryKey,
					pkValue);
			if (dbOperation) {// 首先检查有没有准确将记录保存到奖学金表中，如未成功执行则直接跳转
				if (exists) {
					dbOperation = StandardOperation.update(tableName,
							inputCols, inputVals, primaryKey, pkValue, request);
				} else {
					dbOperation = StandardOperation.insert(tableName,
							inputCols, inputVals, request);
				}
				request.setAttribute("dboperation", dbOperation);
			} else {// 如果奖学金表没有正确保存，则直接返回
				request.setAttribute("dboperation", dbOperation);
			}
		} else {// 不是保存操作时
			if ("student".equalsIgnoreCase(userOnLine)) {
				String[] outVals = StandardOperation.getFixupStuInfo(userName);
				String[] outCols = { "xh", "xm", "xymc", "zymc", "bjmc", "xb",
						"nj", "rxny", "ssbh", "qsdh", "sjhm", "mzmc" };
				for (int i = 0; i < outCols.length; i++) {
					map.put(outCols[i], (outVals != null) ? outVals[i] : "");
				}
			}
		}

		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}


	//完成奖学金申请报表打印
	public static ActionForward applicationPrint(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		//to-do 完成奖学金申请报表打印


		return mapping.findForward("sucess");	
	}

	public static ActionForward applyForBaoSteelPrise(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		PjpyForm  myForm = (PjpyForm) form;
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String jxjdm = request.getParameter("jxjdm");
		String[] xnxq = StandardOperation.getXnXq();
		String tableName = "";
		String[] columns = null;
		String[] values  = null;
		String primaryKey = "";
		String pkValue ="";
		String sysTime = StandardOperation.getRightTime();//获得系统当前时间
		boolean dbOperation = false;
		boolean exists = false;
		if("save".equalsIgnoreCase(doType)){
			String xh = Base.chgNull(myForm.getXh(), "", 1);
			String csny = Base.chgNull(myForm.getCsny(), "", 1);
			String email = Base.chgNull(myForm.getEmail(), "", 1);
			String lxdh = Base.chgNull(myForm.getLxdh(), "", 1);
			String zzmc = Base.chgNull(myForm.getZzmm(), "", 1);
			String mzmc = Base.chgNull(myForm.getMzmc(), "", 1);
			String rxny = Base.chgNull(myForm.getRxny(), "", 1);
			String qzrq1 = Base.chgNull(myForm.getQzrq1(), "", 1);
			String qzrq2 = Base.chgNull(myForm.getQzrq2(), "", 1);
			String qzrq3 = Base.chgNull(myForm.getQzrq3(), "", 1);
			String qzrq4 = Base.chgNull(myForm.getQzrq4(), "", 1);
			String qzrq5 = Base.chgNull(myForm.getQzrq5(), "", 1);
			String hdmc1 = Base.chgNull(myForm.getHdmc1(), "", 1);
			String hdmc2 = Base.chgNull(myForm.getHdmc2(), "", 1);
			String hdmc3 = Base.chgNull(myForm.getHdmc3(), "", 1);
			String hdmc4 = Base.chgNull(myForm.getHdmc4(), "", 1);
			String hdmc5 = Base.chgNull(myForm.getHdmc5(), "", 1);
			String hjrq1 = Base.chgNull(myForm.getHjrq1(), "", 1);
			String hjrq2 = Base.chgNull(myForm.getHjrq2(), "", 1);
			String hjrq3 = Base.chgNull(myForm.getHjrq3(), "", 1);
			String hjrq4 = Base.chgNull(myForm.getHjrq4(), "", 1);
			String hjrq5 = Base.chgNull(myForm.getHjrq5(), "", 1);
			String jxmc1 = Base.chgNull(myForm.getJxmc1(), "", 1);
			String jxmc2 = Base.chgNull(myForm.getJxmc2(), "", 1);
			String jxmc3 = Base.chgNull(myForm.getJxmc3(), "", 1);
			String jxmc4 = Base.chgNull(myForm.getJxmc4(), "", 1);
			String jxmc5 = Base.chgNull(myForm.getJxmc5(), "", 1);
			String njrs = Base.chgNull(myForm.getNjrs(), "", 1);
			String njpm = Base.chgNull(myForm.getNjpm(), "", 1);
			String gnkw = Base.chgNull(myForm.getGnkw(), "", 1);
			String gjkw = Base.chgNull(myForm.getGjkw(), "", 1);
			String gnxshy = Base.chgNull(myForm.getGnxshy(), "", 1);
			String gjxshy = Base.chgNull(myForm.getGjxshy(), "", 1);
			String sci = Base.chgNull(myForm.getSci(), "", 1);
			String ei = Base.chgNull(myForm.getEi(), "", 1);
			String zl = Base.chgNull(myForm.getZl(), "", 1);
			String kjjl = Base.chgNull(myForm.getKjjl(), "", 1);
			String qt = Base.chgNull(myForm.getQt(), "", 1);
			String zysj = Base.chgNull(myForm.getZysj(), "", 1);;
			String xscg = Base.chgNull(myForm.getXscg(), "", 1);
			String baosteelshyj = Base.chgNull(myForm.getBaosteelshyj(), "", 1);
			String[] inputVals = {xh,jxjdm,xnxq[0],csny,email,lxdh,zzmc,mzmc,rxny,qzrq1,qzrq2,qzrq3,qzrq4,qzrq5,hdmc1,hdmc2,hdmc3,hdmc4,hdmc5,hjrq1,hjrq2,hjrq3,hjrq4,hjrq5,jxmc1,jxmc2,jxmc3,jxmc4,jxmc5,njrs,njpm,gnkw,gjkw,gnxshy,gjxshy,sci,ei,zl,kjjl,qt,zysj,xscg,baosteelshyj};
			String[] inputCols = {"xh","jxjdm","xn","csny","email","lxdh","zzmc","mzmc","rxny","qzrq1","qzrq2","qzrq3","qzrq4","qzrq5","hdmc1","hdmc2","hdmc3","hdmc4","hdmc5","hjrq1","hjrq2","hjrq3","hjrq4","hjrq5","jxmc1","jxmc2","jxmc3","jxmc4","jxmc5","njrs","njpm","gnkw","gjkw","gnxshy","gjxshy","sci","ei","zl","kjjl","qt","zysj","xscg","baosteelshyj"};
			/*
			 * 申请宝钢奖学金要将信息保存到xsjxjb和xsjxjfzxxb两个表中，以下分别在处理时加入了信息
			 */
			tableName = "xsjxjb";
			primaryKey = "xh||jxjdm||xn";
			pkValue  = xh+jxjdm+xnxq[0];
			columns = new String[]{"xh","jxjdm","xn","sqsj"};
			values =  new String[]{xh,jxjdm,xnxq[0],sysTime};
			exists = StandardOperation.testRecordExist(tableName, primaryKey, pkValue);
			if(exists){				
				dbOperation = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
			} else{
				dbOperation = StandardOperation.insert(tableName, columns, values, request);
			}
			tableName = "xsjxjfzxxb";
			exists = StandardOperation.testRecordExist(tableName, primaryKey, pkValue);
			if(dbOperation){//首先检查有没有准确将记录保存到奖学金表中，如未成功执行则直接跳转
				if(exists){
					dbOperation = StandardOperation.update(tableName, inputCols, inputVals, primaryKey, pkValue, request);
				}else{
					dbOperation = StandardOperation.insert(tableName, inputCols, inputVals, request);
				}
				request.setAttribute("dboperation", dbOperation);
			} else{//如果奖学金表没有正确保存，则直接返回
				request.setAttribute("dboperation", dbOperation);
			}
		} else {//不是保存操作时
			if("student".equalsIgnoreCase(userOnLine)){
				String[] outVals = StandardOperation.getFixupStuInfo(userName);
				String[] outCols = {"xh","xm","xymc","zymc","bjmc","xb","nj","rxny","ssbh","qsdh","sjhm","mzmc"};
				for(int i=0;i<outCols.length;i++){
					map.put(outCols[i], (outVals!=null)?outVals[i]:"");
				}
			}
		}

		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	} 

	public static ActionForward applyForDtPrise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		PjpyForm myForm = (PjpyForm) form;
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String jxjdm = request.getParameter("jxjdm");
		String[] xnxq = StandardOperation.getXnXq();
		String tableName = "";
		String[] columns = null;
		String[] values = null;
		String primaryKey = "";
		String pkValue = "";
		String sysTime = StandardOperation.getRightTime();// 获得系统当前时间
		boolean dbOperation = false;
		boolean exists = false;
		if ("save".equalsIgnoreCase(doType)) {
			String xh = Base.chgNull(myForm.getXh(), "", 1);
			String csny = Base.chgNull(myForm.getCsny(), "", 1);
			String email = Base.chgNull(myForm.getEmail(), "", 1);
			String lxdh = Base.chgNull(myForm.getLxdh(), "", 1);
			String zzmc = Base.chgNull(myForm.getZzmm(), "", 1);
			String mzmc = Base.chgNull(myForm.getMzmc(), "", 1);
			String rxny = Base.chgNull(myForm.getRxny(), "", 1);
			String qzrq1 = Base.chgNull(myForm.getQzrq1(), "", 1);
			String qzrq2 = Base.chgNull(myForm.getQzrq2(), "", 1);
			String qzrq3 = Base.chgNull(myForm.getQzrq3(), "", 1);
			String qzrq4 = Base.chgNull(myForm.getQzrq4(), "", 1);
			String qzrq5 = Base.chgNull(myForm.getQzrq5(), "", 1);
			String hdmc1 = Base.chgNull(myForm.getHdmc1(), "", 1);
			String hdmc2 = Base.chgNull(myForm.getHdmc2(), "", 1);
			String hdmc3 = Base.chgNull(myForm.getHdmc3(), "", 1);
			String hdmc4 = Base.chgNull(myForm.getHdmc4(), "", 1);
			String hdmc5 = Base.chgNull(myForm.getHdmc5(), "", 1);
			String hjrq1 = Base.chgNull(myForm.getHjrq1(), "", 1);
			String hjrq2 = Base.chgNull(myForm.getHjrq2(), "", 1);
			String hjrq3 = Base.chgNull(myForm.getHjrq3(), "", 1);
			String hjrq4 = Base.chgNull(myForm.getHjrq4(), "", 1);
			String hjrq5 = Base.chgNull(myForm.getHjrq5(), "", 1);
			String jxmc1 = Base.chgNull(myForm.getJxmc1(), "", 1);
			String jxmc2 = Base.chgNull(myForm.getJxmc2(), "", 1);
			String jxmc3 = Base.chgNull(myForm.getJxmc3(), "", 1);
			String jxmc4 = Base.chgNull(myForm.getJxmc4(), "", 1);
			String jxmc5 = Base.chgNull(myForm.getJxmc5(), "", 1);
			String njrs = Base.chgNull(myForm.getNjrs(), "", 1);
			String njpm = Base.chgNull(myForm.getNjpm(), "", 1);
			String gnkw = Base.chgNull(myForm.getGnkw(), "", 1);
			String gjkw = Base.chgNull(myForm.getGjkw(), "", 1);
			String gnxshy = Base.chgNull(myForm.getGnxshy(), "", 1);
			String gjxshy = Base.chgNull(myForm.getGjxshy(), "", 1);

			String lwzzmc1 = Base.chgNull(myForm.getLwzzmc1(), "", 1);
			String lwnf1 = Base.chgNull(myForm.getLwnf1(), "", 1);
			String kwcbsmc1 = Base.chgNull(myForm.getKwcbsmc1(), "", 1);
			String pm1 = Base.chgNull(myForm.getPm1(), "", 1);
			String ygsm1 = Base.chgNull(myForm.getYgsm1(), "", 1);

			String lwzzmc2 = Base.chgNull(myForm.getLwzzmc2(), "", 1);
			String lwnf2 = Base.chgNull(myForm.getLwnf2(), "", 1);
			String kwcbsmc2 = Base.chgNull(myForm.getKwcbsmc2(), "", 1);
			String pm2 = Base.chgNull(myForm.getPm2(), "", 1);
			String ygsm2 = Base.chgNull(myForm.getYgsm2(), "", 1);

			String lwzzmc3 = Base.chgNull(myForm.getLwzzmc3(), "", 1);
			String lwnf3 = Base.chgNull(myForm.getLwnf3(), "", 1);
			String kwcbsmc3 = Base.chgNull(myForm.getKwcbsmc3(), "", 1);
			String pm3 = Base.chgNull(myForm.getPm3(), "", 1);
			String ygsm3 = Base.chgNull(myForm.getYgsm3(), "", 1);

			String lwzzmc4 = Base.chgNull(myForm.getLwzzmc4(), "", 1);
			String lwnf4 = Base.chgNull(myForm.getLwnf4(), "", 1);
			String kwcbsmc4 = Base.chgNull(myForm.getKwcbsmc4(), "", 1);
			String pm4 = Base.chgNull(myForm.getPm4(), "", 1);
			String ygsm4 = Base.chgNull(myForm.getYgsm4(), "", 1);

			String dsyj = Base.chgNull(myForm.getDsyj(), "", 1);
			String xsgzbgsyj = Base.chgNull(myForm.getXsgzbgsyj(), "", 1);
			String shdtyj = Base.chgNull(myForm.getShdtyj(), "", 1);


			String zysj = Base.chgNull(myForm.getZysj(), "", 1);
			String xscg = Base.chgNull(myForm.getXscg(), "", 1);
			String[] inputVals = { xh, jxjdm, xnxq[0], csny, email, lxdh, zzmc,
					mzmc, rxny, qzrq1, qzrq2, qzrq3, qzrq4, qzrq5, hdmc1,
					hdmc2, hdmc3, hdmc4, hdmc5, hjrq1, hjrq2, hjrq3, hjrq4,
					hjrq5, jxmc1, jxmc2, jxmc3, jxmc4, jxmc5, njrs, njpm, gnkw,
					gjkw, gnxshy, gjxshy, dsyj, xsgzbgsyj, lwzzmc1, lwnf1,kwcbsmc1,pm1,ygsm1,
					lwzzmc2, lwnf2,kwcbsmc2,pm2,ygsm2,lwzzmc3, lwnf3,kwcbsmc3,pm3,ygsm3,lwzzmc4, lwnf4,kwcbsmc4,pm4,ygsm4,
					zysj, xscg,	shdtyj };
			String[] inputCols = { "xh", "jxjdm", "xn", "csny", "email",
					"lxdh", "zzmc", "mzmc", "rxny", "qzrq1", "qzrq2", "qzrq3",
					"qzrq4", "qzrq5", "hdmc1", "hdmc2", "hdmc3", "hdmc4",
					"hdmc5", "hjrq1", "hjrq2", "hjrq3", "hjrq4", "hjrq5",
					"jxmc1", "jxmc2", "jxmc3", "jxmc4", "jxmc5", "njrs",
					"njpm", "gnkw", "gjkw", "gnxshy", "gjxshy", "dsyj", "xsgzbgsyj","lwzzmc1", "lwnf1","kwcbsmc1","pm1","ygsm1",
					"lwzzmc2", "lwnf2","kwcbsmc2","pm2","ygsm2","lwzzmc3", "lwnf3","kwcbsmc3","pm3","ygsm3","lwzzmc4", "lwnf4","kwcbsmc4","pm4","ygsm4",
					"zysj", "xscg", "shdtyj" };
			/*
			 * 申请宝钢奖学金要将信息保存到xsjxjb和xsjxjfzxxb两个表中，以下分别在处理时加入了信息
			 */
			tableName = "xsjxjb";
			primaryKey = "xh||jxjdm||xn";
			pkValue = xh + jxjdm + xnxq[0];
			columns = new String[] { "xh", "jxjdm", "xn", "sqsj" };
			values = new String[] { xh, jxjdm, xnxq[0], sysTime };
			exists = StandardOperation.testRecordExist(tableName, primaryKey,
					pkValue);
			if (exists) {
				dbOperation = StandardOperation.update(tableName, columns,
						values, primaryKey, pkValue, request);
			} else {
				dbOperation = StandardOperation.insert(tableName, columns,
						values, request);
			}
			tableName = "xsjxjfzxxb";
			exists = StandardOperation.testRecordExist(tableName, primaryKey,
					pkValue);
			if (dbOperation) {// 首先检查有没有准确将记录保存到奖学金表中，如未成功执行则直接跳转
				if (exists) {
					dbOperation = StandardOperation.update(tableName,
							inputCols, inputVals, primaryKey, pkValue, request);
				} else {
					dbOperation = StandardOperation.insert(tableName,
							inputCols, inputVals, request);
				}
				request.setAttribute("dboperation", dbOperation);
			} else {// 如果奖学金表没有正确保存，则直接返回
				request.setAttribute("dboperation", dbOperation);
			}
		} else {//不是保存操作时
			if ("student".equalsIgnoreCase(userOnLine)) {
				String[] outVals = StandardOperation.getFixupStuInfo(userName);
				String[] outCols = { "xh", "xm", "xymc", "zymc", "bjmc", "xb",
						"nj", "rxny", "ssbh", "qsdh", "sjhm", "mzmc" };
				for (int i = 0; i < outCols.length; i++) {
					map.put(outCols[i], (outVals != null) ? outVals[i] : "");
				}
			}
		}

		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	} 

	//SQ　查询学生成绩
	public static ActionForward showStuCjInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";// sql语句
		String sql1 = "";
		List rs = null;
		String rsNum = "";
		String[] tmp = null;
		String xh =DealString.toGBK(request.getParameter("xh"));
		xh = StringUtils.isNull(xh) ? "" : xh.trim();
		String xxdm = StandardOperation.getXxdm();
		// 学生基本信息
		sql = "select * from view_xsjbxx where xh='" + xh + "'";
		tmp = dao.getOneRs(sql, new String[] {}, new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc"});
		if(tmp != null){
			map.put("xh", tmp[0]);
			map.put("xm", tmp[1]);
			map.put("xb", tmp[2]);
			map.put("nj", tmp[3]);
			map.put("xymc", tmp[4]);
			map.put("zymc", tmp[5]);
			map.put("bjmc", tmp[6]);
		}
		//学生成绩单
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			String jxjsqxn = dao.getConf(2);
			String xn = request.getParameter("xn");
			xn = StringUtils.isNull(xn) ? jxjsqxn : xn.trim();
			String xq = request.getParameter("xq");
			xq = StringUtils.isNull(xq) ? Base.getJxjsqxq() : xq.trim();
			sql = "select (case when kcxz ='03' then '#DDDDDD' else '' end) bgcolor,rownum hh,xn,xq,kcsbm kcdm,kcmc,(case when bz is null or cj ='' " +
			"then cj when bz like '%重修%' or bz like '%补考%' then '' else cj end) " +
			"cj,(case when bz like '%补考%' then cj else '' end) bkcj,(case when bz " +
			"like '%重修%' then cj else '' end) cxcj,jd,xf,bz,(case when kcxz ='03' then '是' else '否' end) sfxx from cjb where xh = ? and xn=? order by xn,xq,kcxz";
			sql1 = "select count(*) rsNum from cjb where xh = ? and xn=? and xq=? order by kcxz";
			rs = dao.getList(sql, new String[] {xh,xn}, new String[] {"bgcolor","hh","xn","xq","kcdm","kcmc","cj","bkcj","cxcj","jd","xf","sfxx"});
			//rsNum = dao.getOneRs(sql1, new String[]{xh,xn}, "rsNum");
		}else{
			sql = "select rownum hh,xn,xq,kcsbm kcdm,kcmc,cj,bkcj,cxcj,jd,xf from cjb where xh = ?";
			sql1 = "select count(*) rsNum from cjb where xh = ?";
			rs = dao.getList(sql, new String[] {xh}, new String[] {"hh","xn","xq","kcdm","kcmc","cj","bkcj","cxcj","jd","xf"});
			rsNum = dao.getOneRs(sql1, new String[]{xh}, "rsNum");
		}
		request.setAttribute("rs22", rs);
		request.setAttribute("rsNum22", rsNum);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	//中北大学师生思品评定时间设定
	public static ActionForward pjpyZbdxSssppdsjsz(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		PjpyZbdxSssppdsjszForm thisForm = (PjpyZbdxSssppdsjszForm) form;
		String action = request.getParameter("act");
		String xxdm = StandardOperation.getXxdm();
		//杭职院,淮阴工学院评奖时间设定
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) || xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
			return new ActionForward("/pjpy_pjsjsz.do", false);
		}
		String[] sjxz = PjpyDAO.getSjxz();
		String[] saveSjxz ;
		if(sjxz != null && sjxz.length != 0 && action == null){
			thisForm.setXn(sjxz[0]);
			thisForm.setXspdkssj(sjxz[1]);
			thisForm.setXspdjssj(sjxz[2]);
			thisForm.setJspdkssj(sjxz[3]);
			thisForm.setJspdjssj(sjxz[4]);
		}
		if("save".equalsIgnoreCase(action)){//对设定的时间进行保存
			saveSjxz = new String[]{thisForm.getXn(),thisForm.getXspdkssj(),thisForm.getXspdjssj(),thisForm.getJspdkssj(),thisForm.getJspdjssj()};
			if(PjpyDAO.setSjxz(saveSjxz)){
				request.setAttribute("doresult", true);
			}
		}
		return mapping.findForward("success");
	}

	public static ActionForward exportPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException{
//		response.reset();
//		response.setContentType("application/vnd.ms-excel");
//		DAO dao = DAO.getInstance();
//		Vector<Object> vec = new Vector<Object>();
//		File file = new File("F:\\zzz.xls");
//		Workbook wwb = Workbook.getWorkbook(file);
//		WritableWorkbook wb = Workbook.createWorkbook(file);

//		Workbook.createWorkbook(in).
//		WritableSheet ws = wwb.createSheet("数据导出", 0);
//		try {
//		String ColumnName[] = dao.getColumnName(sql);
//		String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, tabName
//		.toUpperCase());
//		for (int m = 0; m < ColumnNameCN.length; m++) {
//		ws.addCell(new Label(m, 0, ColumnNameCN[m]));
//		}
//		vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
//		int k = ColumnName.length;
//		for (int i = 0; i < vec.size(); i++) {
//		String[] tmp = (String[]) vec.toArray()[i];
//		for (int j = 0; j < k; j++) {
//		ws.addCell(new Label(j, i + 1, tmp[j]));
//		}
//		}
//		} catch (Exception e) {
//		e.printStackTrace();
//		System.out.println("数据导出失败!");
//		} finally {
//		wwb.write();
//		wwb.close();
//		}
		return mapping.findForward("success");
	}

	public static ActionForward zbdxPjpyApply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		String xxdm = StandardOperation.getXxdm();

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}

		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			if(!("".equalsIgnoreCase(xh) || xh==null)){
				String jxjsqxn = dao.getConf(2);
				String jxjhdxn = jxjsqxn;
				String jxjhdxq = Base.getJxjsqxq();
				HashMap<String, String> rs = new HashMap<String, String>();
				HashMap<String, String> rskc = new HashMap<String, String>();
				HashMap<String, String> rscj = new HashMap<String, String>();
//				sql = "select dycxdj cxcj from view_zhszcp_dyszjfwh where xh=? and xn=? and to_number(xq)=to_number(?)";
//				rs = dao.getMap(sql, new String []{xh,jxjhdxn,String.valueOf(jxjhdxq)}, new String []{"cxcj"});
				sql = "select dycxdj cxcj from view_zhszcp_dyszjfwh where xh=? and xn=?";
				rs = dao.getMap(sql, new String []{xh,jxjhdxn}, new String []{"cxcj"});
//				sql = "select to_char(sum(to_number(nvl(cj,0))*to_number(nvl(xf,0)))/sum(to_number(nvl(xf,0))),'999.9') pjcj from cjb where xh=? and xn=? and to_number(xq)=to_number(?) and kcmc not like '体育%' and kcmc not like '%选修%'";
//				rs.put("pjcj", dao.getOneRs(sql, new String []{xh,jxjhdxn,String.valueOf(jxjhdxq)}, "pjcj"));
				sql = "select to_char(sum(to_number(nvl(cj,0))*to_number(nvl(xf,0)))/sum(to_number(nvl(xf,0))),'999.9') pjcj from cjb where xh=? and xn=? and kcmc not like '体育%' and kcmc not like '%选修%'";
				rs.put("pjcj", dao.getOneRs(sql, new String []{xh,jxjhdxn}, "pjcj"));
//				sql = "select to_char(max(cj)) tycj from tycjb where xh=? and xn=? and to_number(xq)=to_number(?)";
//				rs.put("tycj", dao.getOneRs(sql, new String []{xh,jxjhdxn,String.valueOf(jxjhdxq)}, "tycj"));
				sql = "select to_char(max(cj)) tycj from tycjb where xh=? and xn=? ";
				rs.put("tycj", dao.getOneRs(sql, new String []{xh,jxjhdxn}, "tycj"));
				request.setAttribute("rszh", rs);
				//sql = "select cj from cjb where xh=? and xn=  and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
				sql = "select cj from view_zhhcjb where xh=? and xn=? and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
				//String []cjvalue = dao.getRs(sql, new String []{xh,jxjhdxn,String.valueOf(jxjhdxq)}, "cj");
				String []cjvalue = dao.getRs(sql, new String []{xh,jxjhdxn}, "cj");
//				sql = "select kcmc from cjb where xh=? and xn=? and to_number(xq)=to_number(?) and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
//				String []kcvalue= dao.getRs(sql, new String []{xh,jxjhdxn,String.valueOf(jxjhdxq)}, "kcmc");
				sql = "select kcmc from cjb where xh=? and xn=? and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
				String []kcvalue= dao.getRs(sql, new String []{xh,jxjhdxn}, "kcmc");
				for(int j=1; j<=cjvalue.length; j++){
					rskc.put("kc"+j, kcvalue[j-1]);
					rscj.put("kccj"+j, cjvalue[j-1]);
				}
				if(cjvalue.length<10){
					for(int j=cjvalue.length+1; j<=10; j++ ){
						rskc.put("kc"+j, "");
						rskc.put("kccj"+j, "");
					}
				}
				request.setAttribute("rskc", rskc);
				request.setAttribute("rscj", rscj);
			} else {
				HashMap<String, String> rs = new HashMap<String, String>();
				HashMap<String, String> rskc = new HashMap<String, String>();
				HashMap<String, String> rscj = new HashMap<String, String>();
				rs.put("cxcj", "");
				rs.put("tycj", "");
				rs.put("pjcj", "");
				request.setAttribute("rszh", rs);
				for (int j = 1; j <= 10; j++) {
					rskc.put("kc" + j, "");
					rscj.put("kccj" + j, "");
				}
				request.setAttribute("rskc", rskc);
				request.setAttribute("rscj", rscj);
			}
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
		map.put("xn", Base.getJxjsqxn());
		map.put("nd", Base.getJxjsqnd());
		map.put("xq", Base.getJxjsqxq());
		map.put("xqmc", Base.getJxjsqxqmc());
		map.put("stuExists", "yes");
		map.put("userType", userType);
		String[] qtxx = null;
		qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1", "bxkpjcj1", "bjcjpx1", "zycjpx1",
				"tyhgbz1", "jxj2", "shyg2", "ddj2",  "bxkpjcj2", "bjcjpx2", "zycjpx2",
				"tyhgbz2", "jxj3", "shyg3", "ddj3",  "bxkpjcj3", "bjcjpx3", "zycjpx3",
				"tyhgbz3", "jxj4", "shyg4", "ddj4",   "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4" };
		sql = "select * from xsjxjxxb where xh=?";
		String[] qtxxfs = dao.getOneRs(sql, new String[] {xh}, qtxx);
		if( act != null && act.equalsIgnoreCase("modi")){
			String sql1 = "select * from view_xsjxjb where "+pk+"='"+pkValue+"'";
			String[] jxjxx = dao.getColumnName("select * from view_xsjxjb where 1=2");
			String[] jxjxxArr = dao.getOneRs(sql1, new String[]{}, jxjxx);
			for(int i=0;i<jxjxx.length;i++){
				if(jxjxxArr[i] != null){
					if(jxjxx[i].equalsIgnoreCase("jxjdm")){
						applyForm.setXmdm(jxjxxArr[i]);
					} else if(jxjxx[i].equalsIgnoreCase("DNSHJXJ")){
						applyForm.setZdm(jxjxxArr[i]);
					} else if (jxjxx[i].equalsIgnoreCase("KYCG")){
						//		System.out.println(jxjxx[i]);
					}
					map.put(jxjxx[i].toLowerCase(), jxjxxArr[i]);
				}
			}
		}
		//System.out.println(sql);
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
		request.setAttribute("rs",map);
		return mapping.findForward("success");
	}

	public static ActionForward zbdxPjpyJxjdjb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne){
		DAO dao = DAO.getInstance();
		String tips = "评奖评优 - 奖学金申请 - 奖学金评审登记表";
		request.setAttribute("tips", tips);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xh = request.getParameter("xh");
		String jxj = request.getParameter("jxjdm");
		if (userType.equalsIgnoreCase("stu")) {
			xh = session.getAttribute("userName").toString();
		}

		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
				"jxjsqxn", "jxjsqnd" });
		String xn = tmp[0];
		String nd = tmp[1];
		sql = "select * from view_xsjxjb where jxjdm=? and xh=? and xn=? and nd=?";
		HashMap<String, String> map = dao.getMap(sql, new String[] { jxj, xh, xn, nd },
				new String[] { "nd", "xh", "xm", "xymc", "zymc", "bjmc", "xb",
				"jxjmc", "sqly", "drzw", "kycg", "dnshjxj","xxjl","xyshyj","xxshyj","fdyyj","xypswyhyj" });
		String[] qtxx = new String[] { "xh", "jxj1", "shyg1", "ddj1",  "bxkpjcj1", "bjcjpx1", "zycjpx1",
				"tyhgbz1", "jxj2", "shyg2", "ddj2",  "bxkpjcj2", "bjcjpx2", "zycjpx2",
				"tyhgbz2", "jxj3", "shyg3", "ddj3", "bxkpjcj3", "bjcjpx3", "zycjpx3",
				"tyhgbz3", "jxj4", "shyg4", "ddj4", "bxkpjcj4", "bjcjpx4", "zycjpx4", "tyhgbz4" };
		sql = "select * from xsjxjxxb where xh=?";
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
		if(null!=jxj&&jxj.equalsIgnoreCase("a")){
			return new ActionForward("/added/tlmjxj.jsp",false);
		}
		return mapping.findForward("success");
	}

	public static ActionForward zbdxPjpyRychApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		if(!("".equalsIgnoreCase(xh) || xh==null)){
			HashMap<String, String> rs = new HashMap<String, String>();
			HashMap<String, String> rskc = new HashMap<String, String>();
			HashMap<String, String> rscj = new HashMap<String, String>();
			String jxjsqxn = dao.getConf(2);
			String jxjhdxn = jxjsqxn;
			sql = "select dycxdj cxcj from view_zhszcp_dyszjfwh where xh=? and xn=?";
			rs = dao.getMap(sql, new String []{xh,jxjhdxn}, new String []{"cxcj"});
			sql = "select to_char(sum(to_number(nvl(cj,0))*to_number(nvl(xf,0)))/sum(to_number(nvl(xf,0))),'999.9') pjcj from view_zhhcjb where xh=? and xn=? and kcmc not like '体育%' and kcmc not like '%选修%'";
			rs.put("pjcj", dao.getOneRs(sql, new String []{xh,jxjhdxn}, "pjcj"));
			sql = "select to_char(max(cj)) tycj from tycjb where xh=? and xn=?";
			rs.put("tycj", dao.getOneRs(sql, new String []{xh,jxjhdxn}, "tycj"));
			request.setAttribute("rs1", rs);
			sql = "select cj from view_zhhcjb where xh=? and xn=? and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
			String []cjvalue = dao.getRs(sql, new String []{xh,jxjhdxn}, "cj");
			sql = "select kcmc from cjb where xh=? and xn=? and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
			String []kcvalue= dao.getRs(sql, new String []{xh,jxjhdxn}, "kcmc");
			for(int j=1; j<=cjvalue.length; j++){
				rskc.put("kc"+j, kcvalue[j-1]);
				rscj.put("kccj"+j, cjvalue[j-1]);
			}
			if(cjvalue.length<10){
				for(int j=cjvalue.length+1; j<=10; j++ ){
					rskc.put("kc"+j, "");
					rskc.put("kccj"+j, "");
				}
			}
			request.setAttribute("rs"+"kc", rskc);
			request.setAttribute("rs"+"cj", rscj);

//			for(int i=1; i<=2; i++){
//				HashMap<String, String> rs = new HashMap<String, String>();
//				HashMap<String, String> rskc = new HashMap<String, String>();
//				HashMap<String, String> rscj = new HashMap<String, String>();
//				sql = "select dycxdj cxcj from view_zhszcp_dyszjfwh where xh=? and xn=? and to_number(xq)=to_number(?)";
//				rs = dao.getMap(sql, new String []{xh,jxjhdxn,String.valueOf(i)}, new String []{"cxcj"});
//				sql = "select to_char(sum(to_number(nvl(cj,0))*to_number(nvl(xf,0)))/sum(to_number(nvl(xf,0))),'999.9') pjcj from cjb where xh=? and xn=? and to_number(xq)=to_number(?) and kcmc not like '体育%' and kcmc not like '%选修%'";
//				rs.put("pjcj", dao.getOneRs(sql, new String []{xh,jxjhdxn,String.valueOf(i)}, "pjcj"));
//				sql = "select to_char(max(cj)) tycj from tycjb where xh=? and xn=? and to_number(xq)=to_number(?)";
//				rs.put("tycj", dao.getOneRs(sql, new String []{xh,jxjhdxn,String.valueOf(i)}, "tycj"));
//				request.setAttribute("rs"+i, rs);
//				sql = "select cj from cjb where xh=? and xn=? and to_number(xq)=to_number(?) and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
//				String []cjvalue = dao.getRs(sql, new String []{xh,jxjhdxn,String.valueOf(i)}, "cj");
//				sql = "select kcmc from cjb where xh=? and xn=? and to_number(xq)=to_number(?) and kcmc not like '体育%' and kcmc not like '%选修%' order by kcmc";
//				String []kcvalue= dao.getRs(sql, new String []{xh,jxjhdxn,String.valueOf(i)}, "kcmc");
//				for(int j=1; j<=cjvalue.length; j++){
//					rskc.put("kc"+j, kcvalue[j-1]);
//					rscj.put("kccj"+j, cjvalue[j-1]);
//				}
//				if(cjvalue.length<10){
//					for(int j=cjvalue.length+1; j<=10; j++ ){
//						rskc.put("kc"+j, "");
//						rskc.put("kccj"+j, "");
//					}
//				}
//				request.setAttribute("rs"+i+"kc", rskc);
//				request.setAttribute("rs"+i+"cj", rscj);
//
//			}
		}else{
			HashMap<String, String> rs = new HashMap<String, String>();
			HashMap<String, String> rskc = new HashMap<String, String>();
			HashMap<String, String> rscj = new HashMap<String, String>();
			
			rs.put("cxcj", "");
			rs.put("tycj", "");
			rs.put("pjcj", "");
			request.setAttribute("rs1", rs);
			for(int j=1; j<=10; j++){
				rskc.put("kc"+j, "");
				rscj.put("kccj"+j, "");
			}
			request.setAttribute("rskc", rskc);
			request.setAttribute("rscj", rscj);
			
//			for(int i=1; i<=2; i++){
//				rs.put("cxcj", "");
//				rs.put("tycj", "");
//				rs.put("pjcj", "");
//				request.setAttribute("rs"+i, rs);
//				for(int j=1; j<=10; j++){
//					rskc.put("kc"+j, "");
//					rscj.put("kccj"+j, "");
//				}
//				request.setAttribute("rs"+i+"kc", rskc);
//				request.setAttribute("rs"+i+"cj", rscj);
//			}
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
		sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
				"jxjsqxn", "jxjsqnd" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
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
		String[] tt = dao.getOneRs("select sjhm,wysp from xsfzxxb where xh=?",new String[]{xh},new String[]{"sjhm","wysp"});
		String sjhm = "";
		String wysp = "";
		if(tt != null && tt.length == 2){
			sjhm = tt[0];
			wysp = tt[1];
		}
		map.put("sjhm", sjhm);
		map.put("wysp", wysp);
		request.setAttribute("rs",map);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	/**
	 * 中北大学学生思想道德评议
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjpyZbdxXspy(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
//		String userOnLine = session.getAttribute("userOnLine").toString();
		String action = request.getParameter("act");
		String userName = session.getAttribute("userName").toString();
		PjpyZbdxJspdForm myform = (PjpyZbdxJspdForm) form;
		DAO dao = DAO.getInstance();
		PjpyDAO pjpyDao = new PjpyDAO();
		String[] titleArr = {"学号","姓名","学院名称","专业名称","班级名称","思评得分"};
		Vector<String> vectorTit = new Vector<String>();
		vectorTit.addAll(Arrays.asList(titleArr));
		request.setAttribute("title", vectorTit);//标题


		if("save".equalsIgnoreCase(action)){
			// TODO 对学生输入的评定成绩进行保存
			boolean doresult = false;
			String jxjsqxn = dao.getConf(2);
			String jxjsqnd = dao.getConf(3);
			String[] xhArr = myform.getXh();
			String[] xspdcjArr = myform.getXspdcj();

			try{
				for(int i=0;i<xhArr.length;i++){
					StandardOperation.delete("xsspcjb", "xh||xn||pdr", xhArr[i]+jxjsqxn+userName, request);
					StandardOperation.insert("xsspcjb", new String[]{"xn","nd","xh","cj","pdr"}, new String[]{jxjsqxn,jxjsqnd,xhArr[i],xspdcjArr[i],userName}, request);
				}
				doresult = true;//所有数据插入成功
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("doresult", doresult);
		}

		List<HashMap<String, String>> classmatesRs =  pjpyDao.getClassMate(userName);
		request.setAttribute("rs", classmatesRs);//同班同学的数据
		request.setAttribute("rsNum", classmatesRs.size());
		return mapping.findForward("student");
	}

	public ActionForward pjpyZbdxJspy(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyZbdxJspdForm myform = (PjpyZbdxJspdForm) form;
		DAO dao = DAO.getInstance();
		PjpyDAO pjpydao = new PjpyDAO();
		List<HashMap<String, String>> bjList = pjpydao.getClasses(userName);
		request.setAttribute("bjList", bjList);
		String bjdm = myform.getBjdm();

		String action = request.getParameter("act");
		if("save".equalsIgnoreCase(action)){
			// TODO 教师对学生的评分保存操作
			String jxjsqxn = dao.getConf(2);
			String jxjsqnd = dao.getConf(3);
			String[] xhArr = myform.getXh();
			String[] jspdcjArr = myform.getJspdcj();
			boolean doresult = false;
			try{
				for(int i=0;i<xhArr.length;i++){
					StandardOperation.delete("jsspcjb", "xh||xn||pdr", xhArr[i]+jxjsqxn+userName, request);
					StandardOperation.insert("jsspcjb", new String[]{"xn","nd","xh","cj","pdr"}, new String[]{jxjsqxn,jxjsqnd,xhArr[i],jspdcjArr[i],userName}, request);
				}
				doresult = true;
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("doresult", doresult);
		}

		if(bjdm != null && !bjdm.trim().equals("")){
			List<HashMap<String, String>> rs = pjpydao.getClassByTeacher(bjdm);
			String[] titleArr = {"学号","姓名","学院名称","专业名称","班级名称","思想品德得分"};
			request.setAttribute("title", titleArr);//标题
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		return mapping.findForward("teacher");
	} 

	public static ActionForward PrintDyszjf(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		String sql = "";
		String xydm = request.getParameter("xydm");
		String bjdm = request.getParameter("bjdm");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		StringBuffer querry = new StringBuffer();
		HashMap<String, String> map = new HashMap<String,String>();
		List rs = null;
		querry.append(" where 1=1");
		if("".equalsIgnoreCase(xydm) || xydm == null){
			querry.append(" and 1=1");
		}else{
			querry.append(" and xydm='");
			querry.append(xydm);
			querry.append("'");
		}
		if("".equalsIgnoreCase(bjdm) || bjdm == null){
			querry.append(" and 1=1");
		}else{
			querry.append(" and bjdm='");
			querry.append(bjdm);
			querry.append("'");
		}
		if("".equalsIgnoreCase(xn) || xn == null){
			querry.append(" and 1=1");
		}else{
			querry.append(" and xn='");
			querry.append(xn);
			querry.append("'");
		}
		if("".equalsIgnoreCase(xq) || xq == null){
			querry.append(" and 1=1");
		}else{
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("'");
		}
		sql = "select xymc,bjmc,to_char(sysdate,'yyyy') year,to_char(sysdate,'mm') month,to_char(sysdate,'dd') day from view_njxyzybj" + querry;
		map = dao.getMap(sql, new String[]{}, new String[]{"xymc","bjmc","year","month","day"});

		sql = "select * from view_zhszcp_dyszjfwh" + querry;
		rs = dao.getList(sql, new String[]{}, new String[]{"xh","xm","jthdbx","skxxbx","ldwsbx","xjxgbx","qtbx","psbxhj","zzxx","xxtd","ddxy",
				"shhd","fzgn","zhpjhj","gbrzjf","xfxfjf","jjsjjf","glbsjf","wjcljf","fjfhj","dyzf","dycxdj"});
		request.setAttribute("map", map);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	} 

	public static ActionForward PrintZhszcp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "";
		String xydm = request.getParameter("xydm");
		String bjdm = request.getParameter("bjdm");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		StringBuffer querry = new StringBuffer();
		HashMap<String, String> map = new HashMap<String, String>(); //表头班级日期信息
		HashMap<String, String> rskc = new HashMap<String, String>();  //学生课程信息
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		querry.append(" where 1=1");
		if("".equalsIgnoreCase(xydm) || xydm == null){
			querry.append(" and 1=1");
			xydm = "%";
		}else{
			querry.append(" and xydm='");
			querry.append(xydm);
			querry.append("'");
		}
		if("".equalsIgnoreCase(bjdm) || bjdm == null){
			querry.append(" and 1=1");
			bjdm = "%";
		}else{
			querry.append(" and bjdm='");
			querry.append(bjdm);
			querry.append("'");
		}
		if("".equalsIgnoreCase(xn) || xn == null){
			xn = "%";
		}
		if("".equalsIgnoreCase(xq) || xq == null){
			xq = "%";
		}
		String kcxz = "03";//成绩表必修课成绩为KCXZ为为03 且BZ不为重修，补考
		sql = "select distinct a.kcmc from cjb a where (kcxz !='"+kcxz+"' or kcxz is null )and bz is null and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.bjdm = ? and b.xydm = ?) and a.xn = ? and a.xq = ? order by a.kcmc ";
		//查询班级下面学生所有必修课课程名称信息
		String []kcvalue = dao.getRs(sql, new String []{bjdm,xydm,xn,xq}, "kcmc");

		if(kcvalue == null){
			for(int i =0;i <13; i++){
				rskc.put("kc"+(i+1), "");
			}
		}else{
			for(int i =0;i <kcvalue.length; i++){
				rskc.put("kc"+(i+1), kcvalue[i]);
			}
		}
		sql = "select xh from view_xsjbxx where bjdm = ? and xydm = ?";
		String [] xhValue = dao.getRs(sql, new String []{bjdm,xydm}, "xh");
		List<String[]> cjList = new ArrayList<String[]>();
		List<String[]> zhcpList = new ArrayList<String[]>();
		if(xhValue != null){
			//查询这个班级下面学生所有必修课成绩
			cjList = dao
			.rsToVator(
					"select a.* from (select a.xh,b.xm,b.nj,b.xydm,"
					+ "b.zydm,b.bjdm,a.kcmc,a.cj,b.xymc,b.bjmc from cjb a,view_xsjbxx"
					+ " b where a.xh=b.xh and (a.kcxz !='" + kcxz
					+"' or a.kcxz is null)"
					+ " and a.bz is null and a.xn =? and a.xq=?) "
					+ "a where xydm=? and bjdm=?",
					new String[] { xn, xq, xydm, bjdm }, new String[] {
							"xh", "xm", "kcmc", "cj", "xymc", "bjmc" });
			//查询这个班级下面学生所有综合测评成绩
			zhcpList = dao.rsToVator("select xh,zcjzf,nvl(case when instr(to_char(trim(tcjzf)),'.',1,1)=1 then '0'||to_char(trim(tcjzf)) else to_char(tcjzf) end, '0') tcjzf,nvl(case when instr(to_char(trim(dcjzf)),'.',1,1)=1 then '0'||to_char(trim(dcjzf)) else to_char(dcjzf) end, '0') dcjzf,kpf,cxdj,bjpm " +
					"from zhszcp a where a.xn=? and a.xq=? and exists (select 1 " +
					"from view_xsjbxx b where b.bjdm=? and a.xh=b.xh)",
					new String[] { xn, xq, bjdm }, new String[] { "xh",
					"zcjzf", "tcjzf", "dcjzf", "kpf", "cxdj",
			"bjpm" });


			//将查询数据转化为列表
			if (cjList != null) {
				if (xhValue != null) {
					for (int j = 0; j < xhValue.length; j++) {
						HashMap<String, String> oneRs = new HashMap<String, String>();
						int index = 0;
						for (int k = 0; k < kcvalue.length; k++) {


							for (int l = 0; l < cjList.size(); l++) {
								String[] oneData = cjList.get(l);
								if (oneData != null && oneData.length == 6) {
									if (kcvalue[k].equalsIgnoreCase(oneData[2]) && xhValue[j].equalsIgnoreCase(oneData[0])) {

										oneRs.put("xh", oneData[0]);
										oneRs.put("xm", oneData[1]);
										oneRs.put("kccj" + (index + 1), oneData[3]);
										index++;

										if (zhcpList != null) {
											for (int m=0;m<zhcpList.size();m++) {
												String[] zhcpOne = zhcpList.get(m);
												if (zhcpOne != null && zhcpOne.length==7) {
													if (xhValue[j].equalsIgnoreCase(zhcpOne[0])) {
														oneRs.put("zcjzf", zhcpOne[1]);
														oneRs.put("tcjzf", zhcpOne[2]);
														oneRs.put("dcjzf", zhcpOne[3]);
														oneRs.put("kpf", zhcpOne[4]);
														oneRs.put("cxdj", zhcpOne[5]);
														oneRs.put("bjpm", zhcpOne[6]);
														break;
													}
												}
											}
										}
										break;

									}
								}
							}

						}
						rs.add(oneRs);
					}
				}
			}

//			for(int i=0; i<xhValue.length; i++){
//			HashMap<String, String> rsMap = new HashMap<String, String>(); //学生成绩信息
//			sql = "select xh,xm from view_xsjbxx where xh='" + xhValue[i] + "'";
//			rsMap = dao.getMap(sql, new String[]{}, new String[]{"xh","xm"});
//			for(int j=0; j<kcvalue.length; j++){
//			sql = "select cj from cjb where xh='" + xhValue[i] + "' and kcmc='" + kcvalue[j] + "' and xn = ? and xq = ?";
//			rsMap.put("kccj" + (j+1), dao.getOneRs(sql, new String[]{xn,xq}, "cj"));
//			}
//			rs.add(rsMap);
//			}
		}
		//sql = "select xymc,bjmc,to_char(sysdate,'yyyy') year,to_char(sysdate,'mm') month,to_char(sysdate,'dd') day from view_njxyzybj" + querry.toString();
		//map = dao.getMap(sql, new String[]{}, new String[]{"xymc","bjmc","year","month","day"});
		//学院，班级，年月日数据
		if (cjList != null && cjList.size() > 0) {
			map.put("xymc", cjList.get(0) != null && cjList.get(0).length == 6 ? cjList.get(0)[4] : "");
			map.put("bjmc", cjList.get(0) != null && cjList.get(0).length == 6 ? cjList.get(0)[5] : "");
		}
		map.put("year", DateUtils.getYear()+" 年" + DateUtils.getMonth() + " 月" + DateUtils.getDayOfMonth() + " 日");
//		map.put("month", DateUtils.getMonth());
//		map.put("day", DateUtils.getDayOfMonth());
		map.put("kclen", kcvalue != null ? String.valueOf(kcvalue.length) : "1");
		map.put("len", kcvalue != null ? String.valueOf(kcvalue.length+1) : "1");
		map.put("trlen", kcvalue != null ? String.valueOf(kcvalue.length+1 + 12) : "1");
		request.setAttribute("rs", rs);
		request.setAttribute("map", map);

		request.setAttribute("rskc", rskc);

		return mapping.findForward("success");
	} 

	public static ActionForward HzzyCjImpCommit(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response, ActionServlet servlet) throws Exception{
		DAO dao = DAO.getInstance();
		//String filePath = request.getParameter("filePath");
		String dir = servlet.getServletContext().getRealPath("/upload");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyForm myForm = (PjpyForm) form;
		FormFile fFile = myForm.getFile();
		if(fFile==null) return mapping.findForward("false");
		int fsize = fFile.getFileSize();
		InputStream ins = fFile.getInputStream();

		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tempFName = userName+ts.toString().replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
		String filePath = dir+"/"+tempFName+".xls";
		OutputStream os = new FileOutputStream(filePath);
		int bsize = 0;
		byte[] bytes = new byte[fsize];
		while((bsize=(ins.read(bytes, 0, fsize)))!= -1){
			os.write(bytes, 0, bsize);
		}
		ins.close();
		os.close();

		String xn = Base.currXn;
		String xq = Base.currXq;
		String kcsbm = "";
		String xh = "";
		String kcxz = "";
		String cj = "";
		String xf = "";
		Workbook book = Workbook.getWorkbook(new File(filePath));
		// 获得第一个工作表对象
		Sheet sheet = book.getSheet(0);
		int columns = sheet.getColumns();
		int rows = sheet.getRows();
//		for(int i=4; i<columns; i++){
//		Cell cell_kcsbm = sheet.getCell(i,3);
//		kcsbm = cell_kcsbm.getContents();
//		Cell cell_kcxzxf = sheet.getCell(i,4);
//		String kcxz_xf = cell_kcxzxf.getContents();
//		if("".equalsIgnoreCase(kcxz_xf) || kcxz_xf == null){
//		break;
//		}
//		kcxz = kcxz_xf.substring(1, 3);
//		xf = kcxz_xf.substring(3, 6);
//		for(int j=5; j<rows; j++){
//		Cell cell_xh = sheet.getCell(2, j);
//		xh = cell_xh.getContents();
//		if("".equalsIgnoreCase(xh) || xh == null){
//		break;
//		}
//		Cell cell_cj = sheet.getCell(i, j);
//		cj = cell_cj.getContents();
//		if(!"".equalsIgnoreCase(cj) && cj != null){
//		String sql = "select * from cjb where xn=? and xq=? and xh=? and kcsbm=?";
//		String flag = dao.returntag(sql, new String[]{xn,xq,xh,kcsbm});
//		if("notempty".equalsIgnoreCase(flag)){
//		del = StandardOperation.delete("cjb", new String[]{"xn","xq","kcsbm","xh"}, new String[]{xn,xq,kcsbm,xh}, request);
//		if(del){
//		insert = StandardOperation.insert("cjb", new String[]{"xn","xq","kcsbm","xh","kcxz","cj","xf"}, new String[]{xn,xq,kcsbm,xh,kcxz,cj,xf}, request);
//		}
//		}else{
//		insert = StandardOperation.insert("cjb", new String[]{"xn","xq","kcsbm","xh","kcxz","cj","xf"}, new String[]{xn,xq,kcsbm,xh,kcxz,cj,xf}, request);
//		}
//		}
//		}
//		}

		long t1 = System.currentTimeMillis();
		String[] cols = new String[columns-4];
		String[] creditsA = new String[columns-4];
		String[] kcxzA = new String[columns-4];
		String[][] data = new String[rows][columns-4];
		for(int u=0;u<cols.length-4;u++){
			cols[u] = sheet.getCell(u+4,3).getContents().replace(" ", "").replace("\n", "");
			String contents = sheet.getCell(u+4,4).getContents().replace(" ", "").replace("\n", "");
			String kcxzAndCredit = !(contents.trim().equals(""))?contents.substring(contents.indexOf("[")+1, contents.lastIndexOf("]")):"";
			kcxzA[u] = kcxzAndCredit.equals("")?"":kcxzAndCredit.substring(0, 2); 
			creditsA[u]= kcxzAndCredit.equals("")?"":kcxzAndCredit.substring(2, kcxzAndCredit.length());

//			System.out.println(cols[u]);
		}

		for(int j=0;j+5<data.length;j++){
			for(int n=0;n+2<data[j].length;n++){
				data[j][n] = sheet.getCell(n+2, j+5).getContents().replace(" ", "").replace("\n", "");
//				System.out.print(data[j][n]+"  ");
			}
//			System.out.println();
		}
		ArrayList<String> insertSqlList = new ArrayList<String>();
		ArrayList<String> deleteSqlList = new ArrayList<String>();
		for(int i=0;i<data.length-6;i++){
			//String[] rowdata = new String[7];
			xh = data[i][0];
//			System.out.print(i);
			for(int j=2;j<data[i].length-2;j++){
				kcsbm = cols[j-2];
				kcxz = kcxzA[j-2];
				xf = creditsA[j-2];
				cj = data[i][j];
				String comp = "";
//				System.out.print(xh+" "+kcsbm+" "+xf+" "+kcxz+" "+cj);
//				System.out.print("     ");
				if(!"".equalsIgnoreCase(cj) && cj != null){
//					String sql = "select 1 from cjb where xn=? and xq=? and xh=? and kcsbm=?";
//					String flag = dao.returntag(sql, new String[]{xn,xq,xh,kcsbm});
//					if("notempty".equalsIgnoreCase(flag)){
//					//del = StandardOperation.delete("cjb", new String[]{"xn","xq","kcsbm","xh"}, new String[]{xn,xq,kcsbm,xh}, request);
					deleteSqlList.add(StandardOperation.deleteSql("cjb", new String[]{"xn","xq","kcsbm","xh"}, new String[]{xn,xq,kcsbm,xh}, request));
//					if(del){
//					//insert = StandardOperation.insert("cjb", new String[]{"xn","xq","kcsbm","xh","kcxz","cj","xf"}, new String[]{xn,xq,kcsbm,xh,kcxz,cj,xf}, request);
					comp = StandardOperation.insertSql("cjb", new String[]{"xn","xq","kcsbm","xh","kcxz","cj","xf"}, new String[]{xn,xq,kcsbm,xh,kcxz,cj,xf}, request);
					if(comp != null )
						insertSqlList.add(comp);
//					}
//					}else{
//					//insert = StandardOperation.insert("cjb", new String[]{"xn","xq","kcsbm","xh","kcxz","cj","xf"}, new String[]{xn,xq,kcsbm,xh,kcxz,cj,xf}, request);
//					comp = StandardOperation.insertSql("cjb", new String[]{"xn","xq","kcsbm","xh","kcxz","cj","xf"}, new String[]{xn,xq,kcsbm,xh,kcxz,cj,xf}, request);
//					if(comp != null )
//					insertSqlList.add(comp);
//					}
				}
			}

//			System.out.println();
		}
		String[] insertSqlArr = insertSqlList.toArray(new String[0]);
		String[] deleteSqlArr = deleteSqlList.toArray(new String[0]);
		int[] deleteRs = dao.runBatch(deleteSqlArr);

		ArrayList<String> problems = new ArrayList<String>();
		for(int j=0;j<deleteRs.length;j++){//判断有多少删除语句执行错误
			if(deleteRs[j] == Statement.EXECUTE_FAILED ){
				problems.add(deleteSqlArr[j]);
				insertSqlArr[j]="";//删除不成功的记录不进行插入操作
			}
		}
		int[] insertRs = dao.runBatch(insertSqlArr);
		for(int j=0;j<insertRs.length;j++){//判断有多少插入语句执行错误
			if(insertRs[j] == Statement.EXECUTE_FAILED ){
				problems.add(insertSqlArr[j]);
			}
		}
		for(String sqlt : problems ){//输出问题语句（包括删除和插入语句）
			System.out.println(sqlt);
		}
		book.close();
		long t2 = System.currentTimeMillis();
		System.out.println("导入总共用了："+(t2-t1));
		int lastCheck = problems.size(); 
//		System.out.println(problems.size());
		if(!(lastCheck>0)){
			request.setAttribute("dataImported", "ok");
		}else{
			request.setAttribute("dataImported", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward Hzzy_cjQlcommit(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response, ActionServlet servlet) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "delete from cjb where cj is null";
		boolean del = dao.runUpdate(sql, new String[] {});
		if(del){
			request.setAttribute("del", "ok");
		}else{
			request.setAttribute("del", "no");
		}
		return mapping.findForward("success");
	}
	/**
	 * @param bmdm 输入要进行计算的学院代码
	 * @return
	 */
	public static boolean  pjpyZbdxZhcpAutoCalc(String bmdm){
		// TODO 完成综合测评的计算
		DAO dao = DAO.getInstance();
		String sql = "{ call zbuniversity_zhszcp_calculate(?)}";
		try{
			return dao.runProcuder(sql, new String[]{bmdm});
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @param xhxn
	 * 用于传回学生的德育信息
	 * @return
	 */
	public String getStuDyInfo(String xhxnxq){
		DAO dao = DAO.getInstance();
		String sql = null;
		String[] cols = null;
		String xxdm = StandardOperation.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){//江苏信息
			sql = "select xh,xm,xn,xq,jthdbx,skxxbx,ldwsbx,xjxgbx,qtbx,zzxx,xxtd,ddxy,shhd,fzgn,gbrzjf,xfxfjf,jjsjjf,glbsjf,wjcljf," +
			" psbxhj,zhpjhj,fjfhj from view_zhszcp_dyszjfwh where xh||xn||xq=?";
			cols = new String [] {"jthdbx","skxxbx","ldwsbx","xjxgbx","qtbx","zzxx","xxtd","ddxy","shhd","fzgn","gbrzjf","xfxfjf","jjsjjf",
					"glbsjf","wjcljf","psbxhj","zhpjhj","fjfhj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJMZYJSXY)){//浙江经贸职业技术学院
			sql = "select xh,xm,xn,xq,ryjf,rzjf,bsjf,fkjf,kccjbjgkf,tybsjf,pjljf,cpcq,stsz,dlqk,hdqk,yydjjianf,jsjjianf,kyjf,zxksjf," +					
			"yydjjf,jsjjf,qtjf,sxpdbx from view_zhszcp_dyszjfwh where xh||xn||xq=?";
			cols = new String[] {"ryjf","rzjf","bsjf","fkjf","kccjbjgkf","tybsjf","pjljf","cpcq","stsz","dlqk","hdqk","yydjjianf","jsjjianf",
					"kyjf","zxksjf","yydjjf","jsjjf","qtjf","sxpdbx"};
		}else{//其它学校
			sql = "select xh,xm,xn,xq,ryjf,rzjf,bsjf,qsbsjf,cjhdjf,wjkf,jttbkf,qtjjfxx from view_zhszcp_dyszjfwh where xh||xn||xq=?";
			cols = new String [] {"ryjf","rzjf","bsjf","qsbsjf","cjhdjf","wjkf","jttbkf","qtjjfxx"};
		}
		String[] rsArr = dao.getOneRs(sql, new String[] {xhxnxq}, cols);
		StringBuffer rs = new StringBuffer();
		if(rsArr != null){
			for(int i=0;i<rsArr.length;i++){
				rs.append((rsArr[i]==null)?" ":rsArr[i]);
				rs.append("!!");
			}
		}
		return rs.toString();
	}


	/**
	 * @param aa 此变量没有实际用途，只是做为一个传入值，此函数用于向dwr传回奖学金列表
	 * @return
	 */
	public String getJxjList(String aa){
		DAO dao = DAO.getInstance();
		Vector<String[]> jxjVector = new Vector<String[]>();
		jxjVector.addAll(dao.rsToVator("select jxjdm,jxjmc from jxjdmb order by jxjdm", new String[]{}, new String[]{"jxjdm","jxjmc"}));
		Enumeration<String[]> jxjArr = jxjVector.elements();
		StringBuffer sb = new StringBuffer();
		while(jxjArr.hasMoreElements()){
			String[] temArr = jxjArr.nextElement();
			for(int j=0;j<temArr.length;j++){
				sb.append(temArr[j]);
				sb.append((j==temArr.length-1)?"":"!!");
			}

			sb.append((jxjArr.hasMoreElements())?"@@":"");
		}
		return sb.toString();
	}

	/**
	 * 此函数用于向dwr传回奖学金列表
	 * @param jxjfl
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getJxjListByJxjfl(String jxjfl){
		DAO dao = DAO.getInstance();
		List jxjVector = new ArrayList();
		String sql = "";

		String whereSql = " where 1=1";
		if(jxjfl!=null && !jxjfl.equals("")){
			whereSql += " and jxjfl='" + jxjfl + "'";
		}

		sql = "select '' jxjdm,'全部' jxjmc from dual union all select * from (select distinct jxjdm,jxjmc from jxjdmb " + whereSql +" order by jxjdm)";		
		jxjVector.addAll(dao.getList(sql, new String[]{}, new String[]{"jxjdm","jxjmc"}));
		return jxjVector;
	}

	/**
	 * 获取项目分类列表
	 * @param tableName
	 * @return List
	 * */
	public List getXmflList(String tableName){
		DAO dao = DAO.getInstance();
		List list = new ArrayList();
		String sql = "";
		if(tableName != null && tableName.equalsIgnoreCase("jxjdmb")){//奖学金
			sql = "select '' jxjfldm,'----请选择----' jxjflmc from dual union all select * from (select distinct jxjfl jxjfldm, jxjfl jxjflmc from jxjdmb)";
		}else if(tableName != null && tableName.equalsIgnoreCase("rychdmb")){//荣誉称号
			sql = "select '' jxjfldm,'----请选择----' jxjflmc from dual union all select * from (select distinct rychfl jxjfldm, rychfl jxjflmc from rychdmb)";
		}
		list = dao.getList(sql, new String[]{}, new String[]{"jxjfldm","jxjflmc"});
		return list;
	}

	/**
	 * 获取奖学金或荣誉称号列表
	 * @param tableName
	 * @param xmfl
	 * @return List
	 * */
	public List getXmList(String tableName,String xmfl){
		List list = null;
		DAO dao = DAO.getInstance();
		String sql = "";
		String whereSql = " where 1=1 ";

		if(tableName.equals("jxjdmb")){//奖学金
			if(xmfl!=null && !xmfl.equals("null") && !xmfl.equals("")){
				whereSql += " and jxjfl='"+xmfl+"'";
			}
			sql = "select '' jxjdm, '----请选择----' jxjmc from dual union all select * from (select distinct jxjdm,jxjmc from jxjdmb " + whereSql + ")";

		}else if(tableName != null && tableName.equals("rychdmb")){//荣誉称号
			if(xmfl!=null && !xmfl.equals("null") && !xmfl.equals("")){
				whereSql += " and rychfl='"+xmfl+"'";
			}
			sql = "select '' jxjdm, '----请选择----' jxjmc from dual union all select * from (select distinct rychdm jxjdm,rychmc jxjmc from rychdmb " + whereSql + ")";			
		}
		list = dao.getList(sql, new String[]{}, new String[]{"jxjdm","jxjmc"});
		return list;
	}

	/**
	 * 根据奖学金代码获取奖学金的相关信息
	 * @param jxjdm
	 * @return  HashMap<String, String> 
	 * */
	public HashMap<String, String> getJxjInfo(String jxjdm){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select jxjdm,jxjmc,jxjjb,jxjlb,jlje,szjdbz,xydm,jxjfl,sztzxfbz from jxjdmb where jxjdm=?";
		map = dao.getMap(sql, new String[]{jxjdm}, new String[]{"jxjdm","jxjmc","jxjjb","jxjlb","jlje","szjdbz","xydm","jxjfl","sztzxfbz"});
		return map;
	}
	/**
	 * @category 在查询信息时使用，从查询函数中重构出来的
	 * @param request
	 * @param dao
	 * @param cols_en
	 * @param topTr
	 * @param sql
	 * @param go
	 */
	private static void searchWithGo(HttpServletRequest request, DAO dao, String[] cols_en, List<HashMap<String, String>> topTr, StringBuffer sql, String go,String[] inputs, PjpyForm dataSearchForm) {
		ArrayList<String[]> rs;
		int count = 0;
		if(inputs[0]!=null && inputs[0].trim().length()>0){
			sql.append(" and xh=?");
			count=1;
		}
		if(inputs[1]!=null && inputs[1].trim().length()>0){
			///to-do 函数名：searchWithGo   主要用于查询，在这里的sql句子有问题
			sql.append(" and xm like '%?%'");
			count+=2;
		}
		//sql.append(" order by xh");
		String isFdy = request.getSession().getAttribute("isFdy").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		if ("true".equalsIgnoreCase(isFdy)) {
			sql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		int size = 0;
		if("go".equalsIgnoreCase(go)){
			if(count==0){
				rs = dao.rsToVator("select * from (" + sql.toString() + ") where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize()) + " and r> "
								+ dataSearchForm.getPages().getStart(), new String[]{}, cols_en);
				size = dao.rsToVator(sql.toString(), new String[]{}, cols_en).size();
			} else if(count==1){
				rs = dao.rsToVator("select * from (" + sql.toString() + ") where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize()) + " and r> "
								+ dataSearchForm.getPages().getStart(), new String[]{inputs[0]}, cols_en);
				size = dao.rsToVator( sql.toString() , new String[]{inputs[0]}, cols_en).size();
			} else if(count==2){
				rs = dao.rsToVator("select * from (" + sql.toString() + ") where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize()) + " and r> "
								+ dataSearchForm.getPages().getStart(), new String[]{inputs[1]}, cols_en);
				size = dao.rsToVator(sql.toString(), new String[]{inputs[1]}, cols_en).size();
			} else {
				rs = dao.rsToVator("select * from (" + sql.toString() + ") where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize()) + " and r> "
								+ dataSearchForm.getPages().getStart(), inputs, cols_en);
				size = dao.rsToVator(sql.toString(), inputs, cols_en).size();
			}
			request.setAttribute("topTr", topTr);
			dataSearchForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(size)));//设置最大的记录数
			if(rs != null ){
				int rsNum = rs.size();
				request.setAttribute("rsNum", rsNum);
			} 
			request.setAttribute("rs", rs);
		}
	}
	/**
	 * 
	 * @param tableName表名
	 * @param pk 主键
	 * @param pkValue 主键值
	 * @param andCondition 其他条件
	 * @return
	 */
	public boolean getInfoEx(String tableName,String pk,String pkValue,String andCondition){
		DAO dao= DAO.getInstance();
		boolean flag=false;
		int result=0;
		String sql="select count(*) num from "+tableName+" where "+pk+"=? ";
		if(andCondition != ""){
			sql += " and ";
			sql += andCondition;
		}
		result=Integer.parseInt(dao.getOneRs(sql, new String[]{pkValue}, "num"));
		if(result>0){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}	

	/**
	 * 盐城师范检测获奖名单数据是否存在
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public boolean checkDataExists(String pk, String lb) throws Exception{
		DAO dao= DAO.getInstance();
		String num = "";
		if ("jxj".equalsIgnoreCase(lb)) {
			num = dao.getOneRs("select count(*) cont from xsjxjb where xh||xn||xq||jxjdm=?", new String[]{pk}, "cont");
		} else {
			num = dao.getOneRs("select count(*) cont from xsrychb where xh||xn||xq||rychdm=?", new String[]{pk}, "cont");
		}
		if (!StringUtils.isNull(num) && !"0".equalsIgnoreCase(num)) {
			return true;
		}
		return false;
	}
	/**
	 * 获取综合表现
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getZhbx(String lb)
	throws SQLException {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "dm", "mc" };
		String sql ="";
		if("xw".equalsIgnoreCase(lb)){
			sql = " select ''dm,'-请选择-'mc  from dual union(select dm, mc from xwbxfdmb ) order by dm nulls first ";
		}else if("tc".equalsIgnoreCase(lb)){
			sql = " select ''dm,'-请选择-'mc  from dual union(select dm, mc from tcbxfdmb ) order by dm nulls first  ";
		}else{
			sql = " select ''dm,'-请选择-'mc  from dual union(select dm, mc from xwbxfdmb union all select dm, mc from tcbxfdmb ) order by dm nulls first";
		}
		return dao.getList(sql, new String[] {}, colList);
	}
}
