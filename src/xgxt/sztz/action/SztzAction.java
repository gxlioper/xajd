/**
 * 创建于2007-7-31下午06:31:33
 * 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.sztz.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.*;
import xgxt.sztz.dao.SztzDao;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.sztz.form.SztzForm;
import xgxt.utils.Fdypd;

import java.util.*;

/**
 * @author lp
 * 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class SztzAction extends Action{	
	private String userType = "";
	private String sUName;
	private String writeAble;
	private boolean isStu = true;
//	private String power;
//	private int p = -1;
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws Exception{
//		DAO dao = DAO.getInstance();
		SztzForm chkUser=(SztzForm)form;		  
//		String xxdm = dao.getXxdm();
		try{
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			} 		    
			ActionForward myAfw = new ActionForward();		    
			userType = session.getAttribute("userOnLine").toString();
			isStu = (userType.equalsIgnoreCase("student"));
			sUName = session.getAttribute("userName").toString();
			String myPar = mapping.getParameter();	    
			if(myPar.equalsIgnoreCase("sztzhdsb")){	    					
				if (isStu) {
					request.setAttribute("errMsg", "学生无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myAfw = sztzhdsb(mapping, form, request, response);
			}else if (myPar.equalsIgnoreCase("sztzDataSearch")) {//江苏信息职业技术学院

				myAfw = dataSearch(mapping, form, request, response);
			}else if (myPar.equalsIgnoreCase("applyResult")) {				
				myAfw =applyResult(mapping, form, request, response,userType);	
			}else if (myPar.equalsIgnoreCase("saveApply")) {//江苏信息职业技术学院
				myAfw = saveApply(mapping, form, request, response);				
			}else if (myPar.equalsIgnoreCase("sztzpointin")) {

				myAfw = SztzPointIn(mapping, form, request, response);
			}else if (myPar.equalsIgnoreCase("modipointdata")) {//江苏信息职业技术学院
				myAfw = sztzModiPointData(mapping, form, request, response);	
			}else if (myPar.equalsIgnoreCase("sztzxfsb")) {		
				myAfw = sztzXfSb(mapping, form, request, response,userType);	
			}else if (myPar.equalsIgnoreCase("sztz_modiData")) {//江苏信息职业技术学院
				myAfw = modiData(mapping, form, request, response);	
			}else if (myPar.equalsIgnoreCase("xfsbsh")) {						    	
				if (isStu) {
					request.setAttribute("errMsg", "学生无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myAfw = sztzXfSbSH(mapping, form, request, response);	
			}else if (myPar.equalsIgnoreCase("sztzcjhzquery")) {//江苏信息职业技术学院
				myAfw = tzCjHzQuery(mapping, form, request, response);	
			}else if (myPar.equalsIgnoreCase("sztzcjhz")) {//江苏信息职业技术学院
				myAfw = sztzCjHz(mapping, form, request, response);	
			}else if (myPar.equalsIgnoreCase("sztzExpData")){//江苏信息职业技术学院
				myAfw = sztzExpData(mapping, form, request, response);	
			}else if (myPar.equalsIgnoreCase("sztzxfsbautochk")){//江苏信息职业技术学院
				myAfw = sztzxfsbAutochk(mapping, form, request, response);	
			} else if ("SavaSztzHdsb".equalsIgnoreCase(myPar)){//江苏信息职业技术学院
				myAfw = SavaSztzHd(mapping, form, request, response);
			} else if("sztz_hd_cx".equalsIgnoreCase(myPar)){
				if (isStu) {
					request.setAttribute("errMsg", "学生无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myAfw = SztzHdsbxxCx(mapping, form, request, response);
			}else if("sztz_hd_check".equalsIgnoreCase(myPar)){//江苏信息职业技术学院				
				if (isStu) {
					request.setAttribute("errMsg", "学生无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
				myAfw = Checktzhd(mapping, form, request, response);
			}else if("sztz_hd_view".equalsIgnoreCase(myPar)){
				myAfw = tzhdView(mapping, form, request, response);
			}else if("sztzxmquery".equalsIgnoreCase(myPar)){
				myAfw = sztzxmquery(mapping, form, request, response);
			}else if("xftjManage".equalsIgnoreCase(myPar)){
				myAfw = xftjManage(mapping, form, request, response);
			}
			return myAfw;			
		} catch(Exception e){
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}		
	}	
	private ActionForward sztzhdsb (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception {	
		//素质拓展活动申报
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String xmdm = request.getParameter("xmdm");
		String sql = "";
		String tableName ="view_sztz_sbxx";
		String realTable = "sztz_xmsbxxb";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		List kmdmList = null;
		List bmList = null;
		boolean del = false;
		String []colList = {"xmdm","xmmc","kmdm","bmdm","xsdw","xf","xmms","sqkssj","sqjssj"};
		HashMap<String,String> map = new HashMap<String,String>();
		if("".equalsIgnoreCase(doType)||doType==null||"add".equalsIgnoreCase(doType)){
			doType = "";
			for(int i=0;i<colList.length; i++){
				map.put(colList[i], "");
			}
		} else if("modi".equalsIgnoreCase(doType)){//修改时获取页面信息

			sql = "select * from " + tableName + " where xmdm=?"; 
			map = dao.getMap(sql, new String[]{xmdm}, colList);			
			List lydmList = dao.getVectorList("select lydm from sztz_lysqxxb where xmdm=?", new String[]{xmdm}, new String[]{"lydm"});
			List lbdmList = dao.getVectorList("select jbdm from sztz_hjjbsqxxb where xmdm=? ", new String[]{xmdm}, new String[]{"jbdm"});
			for(int i = 0;i<lydmList.size();i++){//返回申请页面上理由map
				int j = i+1;
				String lydmTem = "lydm"+j;
				String lymcTem = "lymc"+j;
				String lynrTem = "lynr"+j;
				map.put(lydmTem,lydmList.get(i).toString().replace("[", "").replace("]", ""));
				map.put(lymcTem, dao.getOneRs("select lymc from sztz_lysqxxb where lydm = ?",new String[]{map.get(lydmTem)},"lymc"));
				map.put(lynrTem, dao.getOneRs("select lynr from sztz_lysqxxb where lydm = ?",new String[]{map.get(lydmTem)},"lynr"));
			}
			for(int i = 0;i<lbdmList.size();i++){//返回申请页面上奖项类别map
				int j = i+1;
				String lbdmTem = "lbdm"+j;
				String lbmcTem = "lbmc"+j;
				String lbxfTem = "lbxf"+j;
				map.put(lbdmTem,lbdmList.get(i).toString().replace("[", "").replace("]", ""));
				map.put(lbmcTem, dao.getOneRs("select jbmc from sztz_hjjbsqxxb where jbdm = ?",new String[]{map.get(lbdmTem)},"jbmc"));
				map.put(lbxfTem, dao.getOneRs("select xf from sztz_hjjbsqxxb where jbdm = ?",new String[]{map.get(lbdmTem)},"xf"));
			}

		} else{
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
			sql = "select * from sztz_xmsbxxb where xmdm=? and xxsh='通过'";
			String tag = dao.returntag(sql, new String[]{xmdm});
			if("empty".equalsIgnoreCase(tag)){
				del = StandardOperation.delete(realTable, "xmdm", xmdm,request);
				if(del){
					del = StandardOperation.delete("sztz_lysqxxb", "xmdm", xmdm,request);
				}
				if(del){
					del = StandardOperation.delete("sztz_hjjbsqxxb", "xmdm", xmdm,request);
				}
				if(del){
					request.setAttribute("isEXIST", "ok");
				}else{
					request.setAttribute("isEXIST", "no");
				}
				return new ActionForward("/sztz_hd_cx.do?go=go",false);
			}else{
				request.setAttribute("isEXIST", "ischeck");
				return new ActionForward("/sztz_hd_cx.do?go=go",false);
			}

		}
		sql = "select kmdm,kmm from sztz_kmdmb";
		kmdmList = dao.getList(sql, new String[] {}, new String[] {
				"kmdm", "kmm" });
		sql = "select  bmdm,bmmc from zxbz_xxbmdm order by bmmc";
		bmList = dao.getList(sql, new String[] {}, new String[] {
				"bmdm", "bmmc" });
		if("xy".equalsIgnoreCase(userType)){
			map.put("bmdm", userDep);
		}
		if(Base.isNull(request.getParameter("xn"))){//默认学年
			map.put("xn",Base.currXn);		
		}
		if(Base.isNull(request.getParameter("xq"))){//默认学期
			map.put("xq",Base.currXq);
		}
		request.setAttribute("userType1", userType);
		request.setAttribute("doType", doType);
		request.setAttribute("kmdmList", kmdmList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("rs", map);
		if("modi".equalsIgnoreCase(doType)){
			return new ActionForward ("/sztz/tzhd_sb_modi.jsp",false);
		}else{
			return mapping.findForward("success");
		}
	}
	private ActionForward dataSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception {		
		//数据查询
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		HttpSession session = request.getSession();
		ActionForward newFwd=new ActionForward();
		StringBuffer querry = new StringBuffer(); 
		querry.append(" where 1=1 ");// sql条件
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句		
		String tips = "";// 位置提示信息
		String tableName = request.getParameter("tableName");// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String dataType = request.getParameter("act");
		String xh = request.getParameter("xh");
		String nd = dataSearchForm.getNd();
		String nj = dataSearchForm.getNj();
		String xn = dataSearchForm.getXn();
		String xq = dataSearchForm.getXq();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xmdm = dataSearchForm.getXmdm();
		String xmmc = DealString.toGBK(dataSearchForm.getXmmc());
		dataSearchForm.setXmmc(xmmc);
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[]{}, "xxmc");
		String userOnLine = session.getAttribute("userOnLine").toString();
		int rightNd = new Integer(dao.getOneRs("select dqnd from xtszb", new String[]{}, new String[]{"dqnd"})[0]).intValue();
		List<HashMap<String, String>> ndList = new ArrayList<HashMap<String,String>>();
		for(int i=6; i >=0 ; i--){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("nd", (new Integer(rightNd-i)).toString());
			ndList.add(map);
		}
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		if (dataType == null) {
			dataType = "";
		}
		if(!dataType.equalsIgnoreCase("search")){
			if (userType.equalsIgnoreCase("xy")&&!dataType.equalsIgnoreCase("dormInfo")) {
				xy = userDep;
				dataSearchForm.setXydm(userDep);
			}
		}
		//查询条件
		querry.append(((xh == null) || xh.equalsIgnoreCase(""))?"and 1=1 ":"and xh = '" + xh + "' ");
		querry.append(((nj == null) || nj.equalsIgnoreCase(""))?"and 1=1 ":"and nj = '" + nj + "' ");
		querry.append(((xn == null) || xn.equalsIgnoreCase(""))?"and 1=1 ":"and xn = '" + xn + "' ");
		querry.append(((xq == null) || xq.equalsIgnoreCase(""))?"and 1=1 ":"and xq = '" + xq + "' ");
		querry.append(((zy == null) || zy.equalsIgnoreCase(""))?"and 1=1 ":"and zydm = '" + zy + "' ");
		querry.append(((bj == null) || bj.equalsIgnoreCase(""))?"and 1=1 ":"and bjdm = '" + bj + "' ");		
		querry.append(((xy == null) || xy.equalsIgnoreCase(""))?"and 1=1 ":"and xydm = '" + xy + "' ");		
		querry.append(((nd == null) || nd.equalsIgnoreCase(""))?"and 1=1 ":"and nd = '"+ nd +"' ");
		querry.append(((xmdm == null) || xmdm.equalsIgnoreCase(""))?"and 1=1 ":"and xmdm = '"+ xmdm +"' ");

		if (dataType.equalsIgnoreCase("search")) {							
			realTable = "sztz_xm_xxfbb";
			pk = "xn||nd||xq||tzxmdm";
			tips = "素质拓展 - 素质拓展活动信息发布 - 信息查询";
			tableName = "view_sztzxx";
			colList = new String[] { "主键", "nd", "xn", "xq", "xmmc",
					"kmm", "sqjzrq", "xmkssj", "xmjssj" };
			sql ="select distinct tzxmdm from sztz_xm_xxfbb";
			List xmdmList = dao.getList(sql, new String[]{}, new String[]{"tzxmdm".toLowerCase()});
			request.setAttribute("xmdmList", xmdmList);
			newFwd=mapping.findForward("success");
		}else if (dataType.equalsIgnoreCase("sztzhd")) {
			realTable = "sztz_hdcjsqb";
			pk = "xn||nd||xq||tzxmdm||xh";
			tips = "素质拓展 - 拓展活动参加申请 - 申请结果查询";
			tableName = "view_tzhdcjsqb";
			colList = new String[] { "主键", "nd", "xn", "xq", "xh", "xm",
					"xmmc", "kmm", "xysh", "xxsh" };
			sql = "select  xmdm,xmmc from sztz_xmdmb ";
			List xmList = dao.getList(sql, new String[] {}, new String[] {
					"xmdm", "xmmc" });
			request.setAttribute("xmList", xmList);
			newFwd=new ActionForward("/sztz/sztz_data_search.jsp", false);
		}else if (dataType.equalsIgnoreCase("xfsb")) {
			realTable = "sztz_xfsbb";
			pk = "xn||nd||xq||xmdm||xh";
			tips = "素质拓展 - 拓展学分个人申报 - 申报结果查询";
			tableName = "view_sztzxfsbxx";
			colList = new String[] { "主键", "nd", "xn", "xq", "xh", "xm",
					"xmmc", "kmm","xysh", "xxsh" };
			sql = "select  xmdm,xmmc from sztz_xmdmb ";
			List xmList = dao.getList(sql, new String[] {}, new String[] {
					"xmdm", "xmmc" });
			querry.append((Base.isNull(xmmc))?"and 1=1 ":"and xmmc like '%"+ xmmc +"%' ");
			request.setAttribute("xmList", xmList);
			writeAble = (Base.chkUPower(sUName,"sztz_applyResult.do?doType=xfsb", userType.equalsIgnoreCase("student"))==1)?"yes":"no";
			newFwd=new ActionForward("/sztz/sztz_data_search.jsp", false);
		}else if (dataType.equalsIgnoreCase("point_input")) {
			realTable = "sztz_xscjxxb";
			pk = "xn||nd||xh||xq||xmdm";
			tips = "素质拓展 - 信息维护 - 素质拓展成绩";
			tableName = "view_sztzcj";
			colList = new String[] { "主键", "nd", "xn", "xq", "xh", "xm",
					"kmm","xmmc","xf"};
			if("student".equalsIgnoreCase(userOnLine)){
				request.setAttribute("writeAble","no");
			}
			querry.append((Base.isNull(xmmc))?"and 1=1 ":"and xmmc like '%"+ xmmc +"%' ");
			writeAble = (Base.chkUPower(sUName,"sztz_applyResult.do?doType=xfsb", userType.equalsIgnoreCase("student"))==1)?"yes":"no";
			newFwd=new ActionForward("/sztz/sztz_data_search.jsp", false);
		}else if (dataType.equalsIgnoreCase("xfsb_sh")) {
			realTable = "sztz_xscjxxb";
			pk = "xn||nd||xq||xmdm||xh";
			tips = "素质拓展 - 申报审核与考评 - 审核";
			tableName = "view_sztzxfsbxx";
			colList = new String[] { "主键", "nd", "xn", "xq", "xh", "xm",
					"xmmc", "kmm","xysh", "xxsh" };
			request.setAttribute("xfsh", "xfsh");// 发送读写权限
			newFwd=new ActionForward("/sztz/sztz_data_search.jsp", false);
		}else{
			dataSearchForm.setErrMsg("S");
			return mapping.findForward("false");
		}				

		sql = "select * from (select * from( select " + pk + " 主键,rownum r,a.* from " + tableName + " a " + querry + ") where rownum<="
		+ (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) 
		+ ") where r>" + dataSearchForm.getPages().getStart();

		colListCN = dao.getColumnNameCN(colList, tableName);
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
//		分页
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry, new String[]{}, "count")));		


		xxmc = dao.getOneRs("select xxmc from xtszb", new String[]{}, new String[]{"xxmc"})[0];
		getListxx(request,dao,xy,zy,nj);
		request.setAttribute("xxmc", xxmc);//取学校名称信息
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("ndList", ndList);//获得5年内年度列表
		//读写权判断		 			
		request.setAttribute("writeAble", writeAble);
		return newFwd;
	}
	private ActionForward saveApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception {		
		// 数据保存
		DAO dao = DAO.getInstance();
		ActionForward newFwd = new ActionForward();
		SztzForm saveApplyForm = (SztzForm) form;
		SztzDao sztzdao = new SztzDao();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userOnLine").toString();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tab = request.getParameter("tab");
		//String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[] {}, "xxmc");
		String sql = "";		
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = saveApplyForm.getXh();
		}
		if (tab.equalsIgnoreCase("hdcjsq")) {
			//拓展活动参加申请
			String sqly = DealString.toGBK(request.getParameter("lynr"));
			sql = "select xysh from sztz_hdcjsqb"
				+ " where xn||nd||xq||tzxmdm=? and xh=?";
			String tmp[] = dao.getOneRs(sql, new String[] { pkValue, xh }, new String[]{"xysh"});

			if ((tmp != null)&& ("通过".equalsIgnoreCase(tmp[0]) || "已通过".equalsIgnoreCase(tmp[0]))) {
				HashMap<String,String> map = new HashMap<String,String>();
				sql = "select nd,xn,xq,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm," +
				"bjmc,tzxmdm,kmm,xmmc,xmnr,xsdw,zzbmmc,sqztj,xmkssj,xmjssj,bz,sqly from view_tzhdcjsqb where  xn||nd||xq||tzxmdm=? and xh=?";
				String[] outValue = dao.getOneRs(sql,new String[]{pkValue, xh },new String[]{ 
						"nd","xn","xq","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc",
						"tzxmdm","kmm","xmmc","xmnr","xsdw","zzbmmc","sqztj","xmkssj","xmjssj","bz","sqly"});
				if(outValue!=null){
					map.put("nd", outValue[0]);
					map.put("xn", outValue[1]);
					map.put("xq", outValue[2]);
					map.put("xh", outValue[3]);
					map.put("xm", outValue[4]);
					map.put("xb", outValue[5]);
					map.put("nj", outValue[6]);
					map.put("xydm", outValue[7]);
					map.put("xymc", outValue[8]);
					map.put("zydm", outValue[9]);
					map.put("zymc", outValue[10]);
					map.put("bjdm", outValue[11]);
					map.put("bjmc", outValue[12]);
					map.put("tzxmdm",outValue[13]);
					map.put("kmm", outValue[14]);
					map.put("xmmc", outValue[15]);
					map.put("xmnr", outValue[16]);
					map.put("xsdw", outValue[17]);
					map.put("zzbmmc", outValue[18]);
					map.put("sqztj", outValue[19]);
					map.put("xmkssj", outValue[20]);
					map.put("xmjssj", outValue[21]);					
					map.put("bz", outValue[22]);
					map.put("sqly", outValue[23]);					
				}
				request.setAttribute("isPASS", "is");
				sql = "select (xn||nd||xq||tzxmdm) tzxmdm,xmmc from view_sztzxx where sqjzrq>=to_char(sysdate,'yyyymmdd')";
				List tzxmList = dao.getList(sql, new String[] {}, new String[] {
						"tzxmdm", "xmmc" });
				request.setAttribute("tzxmList", tzxmList);    			
				map.put("xmdm", map.get("tzxmdm"));			
				request.setAttribute("isPASS", "is");
				map.put("stuExists", "yes");
				map.put("userType", userType);
				request.setAttribute("rs", map);
				newFwd = new ActionForward("/sztz/join_apply.jsp", false);
			}else{		   
				boolean del = false;
				sql = "delete from sztz_hdcjsqb where xn||nd||xq||tzxmdm=? and xh=?";
				del = dao.runUpdate(sql, new String[] { pkValue, xh });
				if (del) {		    
					sql = "select xn,xq,tzxmdm,nd from sztz_xm_xxfbb where xn||nd||xq||tzxmdm=?";
					String[] xmxxfbInfo = dao.getOneRs(sql, new String[] {pkValue},
							new String[] { "xn", "xq", "tzxmdm", "nd"});
					if (xmxxfbInfo != null) { 	
						sql = "insert into sztz_hdcjsqb(xn,xq,xh,tzxmdm,nd,sqly) values( ?,?,?,?,?,?)";
						del = dao.runUpdate(sql, new String[] {xmxxfbInfo[0],xmxxfbInfo[1],xh,xmxxfbInfo[2],xmxxfbInfo[3],sqly});
						if(del){
							String logMsg = userName + "在表'sztz_hdcjsqb'中增加或修改了学年、年度、学期、项目代码（存在sztz_xmdm中）为"+pkValue+"的记录";
							dao = new DAO(request.getRemoteAddr());
							dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);	
						}
					}            		
				} else {
					request.setAttribute("errMsg", "sdf");	
					saveApplyForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				request.setAttribute("dataSaved", "ok");
				request.setAttribute("path","sztz_join_apply.do");
				newFwd = new ActionForward("/sqb/comm_pub_mid.jsp", false);
			}		     		    
		}else if (tab.equalsIgnoreCase("sztz_xfsbb")) {
			//拓展学分个人申报		  
			String xmjb = DealString.toGBK(request.getParameter("cjtzxmjb"));
			String cjxz = DealString.toGBK(request.getParameter("tzxmcjsf"));
			String cgms = DealString.toGBK(request.getParameter("cgms"));
			String lydm = DealString.toGBK(request.getParameter("lydm"));
//			String xn = request.getParameter("xn");
			String  xf = request.getParameter("xf");
//			String xq = request.getParameter("xq");
			String nd = request.getParameter("nd");
			String xmdm = request.getParameter("xmdm");
			String hjjbdm = DealString.toGBK(request.getParameter("jbdm"));
			sql = "select xysh from sztz_xfsbb"
				+ " where xn||nd||xq||tzxmdm=? and xh=?";
			String tmp[] = dao.getOneRs(sql, new String[] { pkValue, xh }, new String[]{"xysh"});

			if ((tmp != null)  //校验同学年年度学期正在审核或已经通过的申报
					&& ("通过".equalsIgnoreCase(tmp[0]) || "已通过"
							.equalsIgnoreCase(tmp[0]))) {
				HashMap<String,String> map = new HashMap<String,String>();
				sql = "select nd,xn,xq,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xmdm,kmm,xmmc from view_sztzxfsbxx where xn||nd||xq||xmdm=? and xh=?";
				String[] outValue = dao.getOneRs(sql,new String[]{ pkValue, xh},new String[]{ 
						"nd","xn","xq","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","xmdm","kmm","xmmc"});
				if(outValue!=null){
					map.put("nd", outValue[0]);
					map.put("xn", outValue[1]);
					map.put("xq", outValue[2]);
					map.put("xh", outValue[3]);
					map.put("xm", outValue[4]);
					map.put("xb", outValue[5]);
					map.put("nj", outValue[6]);
					map.put("xydm", outValue[7]);
					map.put("xymc", outValue[8]);
					map.put("zydm", outValue[9]);
					map.put("zymc", outValue[10]);
					map.put("bjdm", outValue[11]);
					map.put("bjmc", outValue[12]);
					map.put("xmdm",outValue[13]);
					map.put("kmm", outValue[14]);
					map.put("xmmc", outValue[15]);
					map.put("cjtzxmjb", xmjb);
					map.put("tzxmcjsf", cjxz);
					map.put("cgms", cgms);
					map.put("lydm", lydm);
					map.put("xf", xf);
					map.put("jbdm", hjjbdm);
					sql = "select lynr from sztz_sqsblyb  where  lydm=? ";
					String lyInfo = dao.getOneRs(sql, new String[] {map.get("lydm")},
					"lynr");
					map.put("lynr", lyInfo);						
					sql = "select lydm,lymc from sztz_sqsblyb  where XMDM=? order by lymc";
					List lyList = dao.getList(sql, new String[] {map.get("xmdm")}, new String[] {
							"lydm", "lymc" });
					request.setAttribute("lyList", lyList);//理由列表
					sql = "select jbdm,jbmc from sztz_hjjbdmb where XMDM=? ";
					List hjjbList = dao.getList(sql, new String[] {map.get("xmdm")}, new String[] {
							"jbdm", "jbmc" });								    
					request.setAttribute("hjjbList", hjjbList);//奖项类别列表
				}
				sql = "select xmdm,xmmc from sztz_xmdmb";
				List tzxmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });
				request.setAttribute("tzxmList", tzxmList);//项目列表			   
				map.put("stuExists", "yes");
				map.put("userType", userType);
				request.setAttribute("rs", map);
				//request.setAttribute("doType", doType);
				request.setAttribute("njList", Base.getNjList());// 发送年级列表
				request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
				request.setAttribute("xqList", Base.getXqList());// 发送学期列表
				request.setAttribute("cjxmjbList",sztzdao.getCjTzxmJbList());//发送参加项目级别列表
				request.setAttribute("tzxmcjsfList",sztzdao.getTzxmCjSfList());//发送素质拓展项目参加身份列表	
				request.setAttribute("xmdm",xmdm);
				request.setAttribute("isPASS", "is");
				newFwd = new ActionForward("/sztz/tzxf_sb.jsp", false);       	   
			}else {
				boolean del = false;
				sql = "delete from sztz_xfsbb where xn||nd||xq||tzxmdm=? and xh=?";
				del = dao.runUpdate(sql, new String[] { pkValue, xh });
				if (del) {		        
					sql = "insert into sztz_xfsbb(xh,xn,xq,nd,tzxmdm,jb,xz,cg,lydm,xf,jbdm) select ? xh," +
					"(select xn from sztz_xmdmb where xmdm=? )xn,  " +
					" (select xq from sztz_xmdmb where xmdm=? )xq," +
					"? nd,? xmdm,? xmjb,? cjxz,? cgms,? lydm,? xf,? hjjbdm from dual";
					dao.runUpdate(sql, new String[] {xh,xmdm,xmdm,nd,xmdm,xmjb,cjxz,cgms,lydm,xf,hjjbdm});			   
					if(del){
						String logMsg = userName + "在表'sztz_xfsbb'中增加或修改了学年、年度、学期、项目代码（存在sztz_xmdm中）为"+pkValue+"的记录";
						dao = new DAO(request.getRemoteAddr());
						dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);		
					}
				} else {
					request.setAttribute("errMsg", "sdf");	
					saveApplyForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				request.setAttribute("dataSaved", "ok");
				request.setAttribute("path", "sztz_xf_sb.do");
				newFwd = new ActionForward("/sqb/comm_pub_mid.jsp", false);
			}
		}else {
			saveApplyForm.setErrMsg("sdf");
			return mapping.findForward("false");
		}
		return newFwd;	
	}
	private ActionForward applyResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,String userType) 
	throws Exception {	
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String doType = request.getParameter("doType");
		userType = session.getAttribute("userOnLine").toString();
		String sql = "";
		SztzForm applyForm = (SztzForm) form;
		String xh = applyForm.getXh();
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			if (doType.equalsIgnoreCase("sztzhd")){//活动参加申请
				colList = new String[] { "行号", "nd", "xn", "xq", "xh", "xm",
						"xmmc", "kmm","sqsj", "xysh", "xxsh"};		    
				colListCN = dao.getColumnNameCN(colList, "view_tzhdcjsqb");
				sql = "select rownum 行号, a.* from view_tzhdcjsqb a where xh=?";

			}else if (doType.equalsIgnoreCase("xfsb")){//学分申报
				colList = new String[] { "行号", "nd", "xn", "xq", "xh", "xm",
						"xmmc", "kmm", "xysh", "xxsh"};		    
				colListCN = dao.getColumnNameCN(colList, "view_sztzxfsbxx");
				sql = "select rownum 行号, a.* from view_sztzxfsbxx a where xh=?";    
			}
			List topTr = dao.arrayToList(colList, colListCN);

			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("writeAble", "no");
			request.setAttribute("num", String.valueOf(rs.size()));		      
		}else{ 
			if (doType.equalsIgnoreCase("sztzhd")){
				return new ActionForward("/sztz_data_search.do?act="+doType, false);
			}else if (doType.equalsIgnoreCase("xfsb")){
				return new ActionForward("/sztz_data_search.do?act="+doType, false);
			}
		}
		return mapping.findForward("success");				
	}		
	private ActionForward SztzPointIn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		ActionForward newFwd= new ActionForward();
		String doType = request.getParameter("act");
		if(doType.equalsIgnoreCase("point_input")){
			newFwd= new ActionForward("/sztz_data_search.do?act="+doType, false);
		}else if(doType.equalsIgnoreCase("xfsb_sh")){
			newFwd= new ActionForward("/sztz_data_search.do?act="+doType, false);
		}
		return newFwd;
	}
	private ActionForward sztzModiPointData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		SztzForm sztzApplyForm = (SztzForm) form;
		String doType = request.getParameter("doType");
		String tableName = request.getParameter("tableName");
		String pkValue = request.getParameter("pkValue");
		String userName = session.getAttribute("userName").toString();
		userType=session.getAttribute("userOnLine").toString();
		HashMap<String,String> map = new HashMap<String,String>();
		ActionForward newFwd = new ActionForward();
		SztzForm dataSearchForm = (SztzForm) form;
		String tzxmdm = DealString.toGBK(request.getParameter("xmdm"));
		String jbdm = request.getParameter("jbdm");
		String xn   = request.getParameter("xn");
		String xq   = request.getParameter("xq");
		String act = request.getParameter("act");
		String pk = request.getParameter("pk");
		String realTable = request.getParameter("realTable");
//		String from = request.getParameter("from");
		List hjjbList = null;//学生获奖类别列表
//		String[] dqndq = dao.getOneRs("select dqxn,dqxq,dqnd from xtszb", new String[]{}, new String[]{"dqxn","dqxq","dqnd"});
		String sql = "";
		String tips="素质拓展成绩添加";
//		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[]{}, new String []{"xxmc"})[0];
		if (doType.equalsIgnoreCase("viewOne")){
			tips="素质拓展成绩修改";
			sql = "select " +
			pk+",nd,xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,xmdm,xmmc,zzbmdm,xsdw,hjjb,xf,bz from view_sztzcj where " +
			pk+"='"+pkValue+"'";
			String[] colList = new String[] { pk, "nd", "xn","xq", "xh", "xm", "xb", "nj","xymc",
					"zymc", "bjmc", "xmdm", "xmmc", 
					"zzbmdm","xsdw","hjjb","xf","bz"};
			String[] rsvalue = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				rsvalue[i] = ((rsvalue[i] == null) || rsvalue[i].equalsIgnoreCase("")) ? " "
						: rsvalue[i];
				map.put(colList[i], rsvalue[i].trim());
			}   
			map.put("jbdm",map.get("hjjb"));
			if("do".equalsIgnoreCase(act)){
				if((jbdm==null || "".equalsIgnoreCase(jbdm))){
					sql = "select nvl(xf,'0') xf from sztz_xmdmb where xmdm = ?";
					String xf = dao.getOneRs(sql, new String[]{map.get("xmdm")}, "xf");
					map.put("xf", xf);
					map.put("jbdm", jbdm);
				}else if((jbdm!=null || !"".equalsIgnoreCase(jbdm))){
					sql = "select xf from sztz_hjjbdmb where jbdm=? and xmdm=?";
					String xf = dao.getOneRs(sql, new String[]{jbdm,map.get("xmdm")}, "xf");
					map.put("xf", xf);
					map.put("jbdm", jbdm);				    	
				}
			}   
			sql = "select jbdm,jbmc from sztz_hjjbdmb where xmdm=?";
			hjjbList = dao.getList(sql, new String[] {map.get("xmdm")}, new String[] {
					"jbdm", "jbmc" });
			request.setAttribute("hjjbList", hjjbList);
			sql="select bmmc from zxbz_xxbmdm where bmdm="+map.get("zzbmdm");
			String[] bmmc=dao.getOneRs(sql, new String[] {}, new String[] {"bmmc"});			      
			map.put("zzbmmc", bmmc[0].toString());			    			    	
			String a = request.getQueryString();
			String b = request.getRequestURL().toString();
			b = b.substring(b.lastIndexOf('/'), b.length());
			b = b + "?" + a;
			request.setAttribute("url", b);
			sql="select xmdm,xmmc from sztz_xmdmb";
			List tzxmList=dao.getList(sql,new String[]{},new String[]{"xmdm","xmmc"});
			request.setAttribute("tzxmList", tzxmList);		    	
			request.setAttribute("rs", map);
			request.setAttribute("pkValue", pkValue);// 发送表主键
			request.setAttribute("userType", userType);
			request.setAttribute("njList", Base.getNjList());// 发送年级列表
			request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
			request.setAttribute("xqList", Base.getXqList());// 发送学期列表
			request.setAttribute("doType", doType);
			request.setAttribute("realTable", realTable);
			request.setAttribute("tableName", tableName);
			request.setAttribute("pk",pk);
			request.setAttribute("disabled","true");
			request.setAttribute("doact", "doact");
			request.setAttribute("tips",tips);
			request.setAttribute("writeAble", writeAble);
			newFwd = new ActionForward("/sztz/sztz_point_modi.jsp", false); 
		}else if (doType.equalsIgnoreCase("del")) {		
			// 删除数据	
			boolean del = false;
			sql = "delete from sztz_xscjxxb where xn||nd||xh||xmdm=?";
			del=dao.runUpdate(sql, new String[] {pkValue});		  	 	   
			if (del) {
				String logMsg = userName + "在表'sztz_xscjxxb'中删除了学年、年度、学号、学期、项目代码（存在sztz_xmdm表中）为"+pkValue+"的记录";
				dao = new DAO(request.getRemoteAddr());
				dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);			  			
				newFwd=null;
			}else {
				request.setAttribute("errMsg", "sdf");	
				dataSearchForm.setErrMsg("sdf");
				return mapping.findForward("false");
			}
			return newFwd;
		}else if (doType.equalsIgnoreCase("modi")) {
			String xf = request.getParameter("xf");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String hjjb=DealString.toGBK(request.getParameter("jbdm"));
			if(!(xf.equals(null))&&!(xf.equals(""))){
				boolean del = false;		  	    
				sql = "select xh,xn,xq,nd,xmdm,kmdm from view_sztzcj where xn||nd||xh||xq||xmdm=?";
				String[] sztzcjcp = dao.getOneRs(sql, new String[] {pkValue},
						new String[] {"xh","xn","xq","nd","xmdm","kmdm"});
				if (sztzcjcp != null) {     
					sql = "delete from sztz_xscjxxb where xn||nd||xh||xq||xmdm=?";
					del = dao.runUpdate(sql, new String[] {pkValue});
					if (del) {  			   	
						sql = "insert into sztz_xscjxxb(xh,xn,xq,nd,xmdm,kmdm,hjjb,xf,bz) values( ?,?,?,?,?,?,?,?,?)";
						del = dao.runUpdate(sql, new String[] {sztzcjcp[0],sztzcjcp[1],
								sztzcjcp[2],sztzcjcp[3],sztzcjcp[4],sztzcjcp[5],hjjb,xf,bz});		 
						if(del){
							String logMsg = userName + "在表'sztz_xscjxxb'中修改了学年、年度、学号、学期、项目代码（存在sztz_xmdm表中）为"+pkValue+"的记录";
							dao = new DAO(request.getRemoteAddr());
							dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);			  			                          	  
						}
					}
				}else {
					request.setAttribute("errMsg", "sdf");	
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			}
			request.setAttribute("dataSaved", "ok");	
			return null;
		}else if (doType.equalsIgnoreCase("save")){
			String xh=request.getParameter("xh");
			String nd=request.getParameter("nd"); 		
			String xmdm=request.getParameter("xmdm");
			String xf = request.getParameter("xf");
			String bz = DealString.toGBK(request.getParameter("bz"));
			String hjjb=DealString.toGBK(request.getParameter("jbdm"));
			sql = "select xn,xq,kmdm from sztz_xmdmb where xmdm="+xmdm;
			String[] kmdm = dao.getOneRs(sql, new String[] {}, new String[] {"xn","xq","kmdm"});
			boolean del = false;
			sql="select rownum hh from " + realTable+" where xn=? and xq=? and xh=? and xmdm=?";
			String tmp[] = dao.getOneRs(sql, new String[] { xn, xq, xh, xmdm }, new String[]{"hh"});
			if((tmp!=null)){    			    
				request.setAttribute("isEXIST", "yes");
			}else {
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable
					+ " where xn=? and xq=? and xh=? and xmdm=?";
					del = dao.runUpdate(sql, new String[] { kmdm[0], kmdm[1], xh,xmdm });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(xh,xn,xq,nd,xmdm,kmdm,hjjb,xf,bz)"
						+ " values(?,?,?,?,?,?,?,?,?)";
					del = dao.runUpdate(sql, new String[] { xh,kmdm[0],kmdm[1],nd,xmdm,kmdm[2 ],
							hjjb,xf,bz});	     			    	
					if(del){	     			    
						String logMsg = userName + "在表'"+realTable+"'中修改了记录";
						dao = new DAO(request.getRemoteAddr());
						dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);	
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				request.setAttribute("dataSaved", "ok");
				return null;
			}
		}else {				
			String xh = sztzApplyForm.getXh();
			sql = "select * from view_xsjbxx where xh=?";
			String[] colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
			String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
			}
			if (tzxmdm != null) {
				sql = "select xf  from sztz_xmdmb where xmdm=?";
				String tzxmInfo = dao.getOneRs(sql, new String[] {tzxmdm},"xf");
				if (tzxmInfo != null) {
					map.put("xf", tzxmInfo);														
				}
//				map.put("nd", dataSearchForm.getNd());
//				map.put("xn", dataSearchForm.getXn());
//				map.put("xq", dataSearchForm.getXq());
				map.put("jbdm", request.getParameter("jbdm"));
				map.put("bz", request.getParameter("bz"));
//				map.put("xmdm",tzxmdm);
				sql = "select jbdm,jbmc from sztz_hjjbdmb where xmdm = ?";
				hjjbList = dao.getList(sql, new String[] {tzxmdm}, new String[] {"jbdm", "jbmc" });
				request.setAttribute("hjjbList", hjjbList);//获奖类别列表
				if ((jbdm!=null || !"".equalsIgnoreCase(jbdm))&&"do".equalsIgnoreCase(act)){    
					sql = "select xf from sztz_hjjbdmb where jbdm=? and xmdm=?";
					String xf = dao.getOneRs(sql, new String[]{jbdm,tzxmdm}, "xf");
					map.put("xf", xf);
					map.put("jbdm", dataSearchForm.getJbdm());
				}
				sql = "select xmdm,xn,(select xqmc from xqdzb b where b.xqdm=xq and rownum=1 )xq,xmmc,kmdm kmm from view_sztz_xmdmb where xmdm=?";
				colList = new String[] { "xmdm", "xn", "xq", "xmmc", "kmm" };
				String[] temV = dao.getOneRs(sql, new String[] { tzxmdm }, colList);
				if (temV != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(),(temV[i] == null || temV[i].equalsIgnoreCase("")) ? "": temV[i]);
					}
				}
			}else {
				sql = "select jbdm,jbmc from sztz_hjjbdmb";
				hjjbList = dao.getList(sql, new String[] {}, new String[] {
						"jbdm", "jbmc" });
				request.setAttribute("hjjbList", hjjbList);
			}
			sql = "select xmdm,xmmc from sztz_xmdmb where 1=1 ";
			sql +=Base.isNull(xn)?"":" and xn='"+xn+"' ";
			sql +=Base.isNull(xq)?"":" and xq='"+xq+"'";
			List tzxmList = dao.getList(sql, new String[] {}, new String[] {"xmdm", "xmmc" });
			request.setAttribute("tzxmList", tzxmList);					
			request.setAttribute("rs", map);					
			request.setAttribute("pkValue", pkValue);// 发送表主键
			request.setAttribute("njList", Base.getNjList());// 发送年级列表
			request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
			request.setAttribute("xqList", Base.getXqList());// 发送学期列表
			//request.setAttribute("hjjbList", sztzDao.getTzxmHjJbList());//发送获奖级别列表
			map.put("stuExists", "yes");
			map.put("userType", userType);
			request.setAttribute("rs", map);
			request.setAttribute("isEXIST", "no");
			request.setAttribute("doType", doType);
			request.setAttribute("tips",tips);
			request.setAttribute("xmdm",tzxmdm);
			newFwd =mapping.findForward("success");
		}
		return newFwd;
	}
	private ActionForward sztzXfSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,String userType) 
	throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		SztzDao sztzdao= new SztzDao();
		HttpSession session = request.getSession();
		SztzForm sztzForm = (SztzForm) form;
		String doType = DealString.toString(request.getParameter("doType"));
		userType=session.getAttribute("userOnLine").toString();			
		String sql = "";		
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = sztzForm.getXh();
		String tzxmdm = DealString.toGBK(request.getParameter("xmdm"));
		String lydm = request.getParameter("lydm");
		String jbdm = request.getParameter("jbdm");
		String xn = sztzForm.getXn();
		String xq = sztzForm.getXq();
		String nd = sztzForm.getNd();
//		sztzForm.setXn(xn);
//		sztzForm.setXq(xq);
//		sztzForm.setNd(nd);
		String cgms = DealString.toGBK(request.getParameter("cgms"));
		sztzForm.setCgms(cgms);
		if(Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){//江苏信息职业技术学院
			// 获得参数设置表数据(获得设置申请时间范围)
			sql = "select * from csmz_sztzszb where to_char(sysdate,'yyyymmddhh24miss') between kssj and jssj";
			String sjsz = dao.returntag(sql, new String[] {});
			if ("empty".equalsIgnoreCase(sjsz)) {
				if (doType.equalsIgnoreCase("modi")
						|| doType.equalsIgnoreCase("add")) {// 修改、添加操作下不限制
					request.setAttribute("sqsjTag", "0");
				} else {
					request.setAttribute("sqsjTag", "1");
				}
			}
		}else{
			request.setAttribute("sqsjTag", "0");
		}
		doType="";
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
		if (!Base.isNull(tzxmdm)) {

			sql = "select lydm,lymc from sztz_sqsblyb where xmdm = ? order by lymc desc";
			List lyList = dao.getList(sql, new String[] {tzxmdm}, new String[] {
					"lydm", "lymc" });
			request.setAttribute("lyList", lyList);

			sql = "select b.kmm kmm from sztz_xmdmb a, sztz_kmdmb b where a.kmdm=b.kmdm and a.xmdm=?";	
			map.put("kmm", dao.getOneRs(sql, new String[]{tzxmdm}, "kmm"));   		
			if (lydm != null){
				sql = "select lynr from sztz_sqsblyb  where  lydm=? ";
				String lyInfo = dao.getOneRs(sql, new String[] {lydm},"lynr");
				map.put("lynr", lyInfo);
				map.put("lydm", lydm);
			}   		
			sql = "select * from sztz_hjjbdmb where xmdm=?";
			String tag = dao.returntag(sql, new String[]{tzxmdm});
			if("empty".equalsIgnoreCase(tag)){//初始学分
				sql = "select nvl(xf,'0') xf from sztz_xmdmb where xmdm=?";
				String xf = dao.getOneRs(sql, new String[]{tzxmdm}, "xf");
				map.put("xf", xf);
			}else{//奖项分数
				sql = "select nvl(xf,'0') xf from sztz_hjjbdmb where jbdm=?";
				String xf = dao.getOneRs(sql, new String[]{jbdm}, "xf");
				map.put("xf", xf); 
			}

			sql = "select xmdm,xn,(select xqmc from xqdzb b where b.xqdm=xq and rownum=1 )xqmc,xq,xmmc,kmm from (select a.xmdm,a.xmmc, a.xn,a.xq,b.kmm from sztz_xmdmb a,sztz_kmdmb b,zxbz_xxbmdm c where a.kmdm=b.kmdm and a.zzbmdm = c.bmdm) where xmdm=?";
			colList = new String[] { "xmdm", "xn", "xqmc","xq", "xmmc", "kmm" };
			String[] temV = dao.getOneRs(sql, new String[] { tzxmdm }, colList);
			if (temV != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(),(temV[i] == null || temV[i].equalsIgnoreCase("")) ? "": temV[i]);
				}
			}

		}else{
			//理由列表
			sql = "select lydm,lymc from sztz_sqsblyb order by lymc";
			List lyList = dao.getList(sql, new String[] {}, new String[] {
					"lydm", "lymc" });
			request.setAttribute("lyList", lyList);
			//理由列表
		} 
		//项目列表
		StringBuffer qry = new StringBuffer();
		qry.append(" where 1=1 ");
		qry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		qry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		if(!Base.isNull(xh)){//筛选活动组织部门须满足： 申请学生所在院系或院系子部门及学校级部门
			qry.append(" and ((zzbmdm =(select xydm from view_xsjbxx where xh='"+xh+"' ) or zzbmdm in ( ");
			qry.append("select bmdm from zxbz_xxbmdm where bmfdm=(select xydm from view_xsjbxx where xh='"+xh+"' ))) or (zzbmdm not in( ");
			qry.append("select bmdm from zxbz_xxbmdm where bmjb='1' and bmlb='5' union all(select b.bmdm from zxbz_xxbmdm a,zxbz_xxbmdm b ");
			qry.append("where a.bmjb='1' and a.bmlb='5'and a.bmdm=b.bmfdm ) )) )");
		}
		sql = "select xmdm,xmmc from sztz_xmdmb "+qry;		
		List tzxmList = dao.getList(sql, new String[] {}, new String[] {
				"xmdm", "xmmc" });
		request.setAttribute("tzxmList", tzxmList);
		//项目列表

		//获奖级别列表
		sql = "select jbdm,jbmc from sztz_hjjbdmb where xmdm=?";
		List hjjbList = dao.getList(sql, new String[] {tzxmdm}, new String[] {
				"jbdm", "jbmc" });
		request.setAttribute("hjjbList", hjjbList);
		//获奖级别列表

		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("cjxmjbList",sztzdao.getCjTzxmJbList());//发送参加项目级别列表
		request.setAttribute("tzxmcjsfList",sztzdao.getTzxmCjSfList());//发送素质拓展项目参加身份列表			
		request.setAttribute("isPASS", "no");
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("nd", nd);
		request.setAttribute("xmdm", tzxmdm);
		return mapping.findForward("success");	
	}
	private ActionForward modiData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		ActionForward newFwd = new ActionForward();
		SztzForm dataSearchForm = (SztzForm) form;
		HttpSession session = request.getSession();
		SztzDao sztzDao=new SztzDao();
		String pk = request.getParameter("pk");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String realTable = request.getParameter("realTable");
		String tableName = request.getParameter("tableName");
		String doType = request.getParameter("doType");
		String from = request.getParameter("from");
		String jbdm = request.getParameter("jbdm");
		boolean done = false;
		String userName = session.getAttribute("userName").toString();
//		String xmdm = request.getParameter("xmdm");
		String act = request.getParameter("act");
//		String[] dqndq = dao.getOneRs("select dqxn,dqxq,dqnd from xtszb", new String[]{}, new String[]{"dqxn","dqxq","dqnd"});
		String sql = "";
//		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[]{}, new String []{"xxmc"})[0];
		/*if("杭州职业技术学院".equalsIgnoreCase(xxmc)){
		 *	request.setAttribute("showhzy", "showhzy");
		 * }
		 *if("浙江商业职业技术学院".equalsIgnoreCase(xxmc)){
		 *	request.setAttribute("showzszy", "showzszy");
		 *}
		 */
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			dataSearchForm.setErrMsg("sdf");
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("del")) {
			// 删除数据			   
			if(realTable.equalsIgnoreCase("sztz_hdcjsqb")){
				sql = "delete from " + realTable + " where " + pk + "='" + pkValue
				+ "'";
				done = dao.runUpdate(sql, new String[] {});
				newFwd = new ActionForward("/sztz_data_search.do?go=go&act=" + from,
						false);
			}else if(realTable.equalsIgnoreCase("sztz_xscjxxb")){
				pk = "xn||nd||xh||xq||xmdm";
				sql = "delete from sztz_xscjxxb where "+pk+"='"+ pkValue
				+ "'";
				done = dao.runUpdate(sql, new String[] {});   			
				newFwd = new ActionForward("/sztz_data_search.do?go=go&act=" + from,
						false);				    
			}else if(realTable.equalsIgnoreCase("sztz_xfsbb")){
				pk = "xn||nd||xq||tzxmdm||xh";
				sql = "delete from " + realTable + " where "+pk+"='" + pkValue
				+ "'";
				done = dao.runUpdate(sql, new String[] {});
				newFwd = new ActionForward("/sztz_data_search.do?go=go&act=" + from,
						false);
			}
			if(done){
				String logMsg = userName + "删除了表'"+realTable+"'中字段组合"+pk+"为"+pkValue+"的记录";
				dao = new DAO(request.getRemoteAddr());
				dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);	
			}

		}else if(doType.equalsIgnoreCase("modi")){
			if (realTable.equalsIgnoreCase("sztz_hdcjsqb")) {
				HashMap<String,String> map = new HashMap<String,String>();

				sql = "select nd,xn,xq,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,tzxmdm,kmm," +
				"xmmc,xmnr,xsdw,zzbmmc,sqztj,xmkssj,xmjssj,bz,sqly from "+tableName+"" +
				" where xn||nd||xq||tzxmdm||xh=?";
				String[] outValue = dao.getOneRs(sql,new String[]{pkValue},new String[]{ 
						"nd","xn","xq","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc",
						"tzxmdm","kmm","xmmc","xmnr","xsdw","zzbmmc","sqztj","xmkssj","xmjssj","bz","sqly"});
				if(outValue!=null){
					map.put("nd", outValue[0]);
					map.put("xn", outValue[1]);
					map.put("xq", outValue[2]);
					map.put("xh", outValue[3]);
					map.put("xm", outValue[4]);
					map.put("xb", outValue[5]);
					map.put("nj", outValue[6]);
					map.put("xydm", outValue[7]);
					map.put("xymc", outValue[8]);
					map.put("zydm", outValue[9]);
					map.put("zymc", outValue[10]);
					map.put("bjdm", outValue[11]);
					map.put("bjmc", outValue[12]);
					map.put("xmdm",outValue[13]);
					map.put("kmm", outValue[14]);
					map.put("xmmc", outValue[15]);
					map.put("xmnr", outValue[16]);
					map.put("xsdw", outValue[17]);
					map.put("zzbmmc", outValue[18]);
					map.put("sqztj", outValue[19]);
					map.put("xmkssj", outValue[20]);
					map.put("xmjssj", outValue[21]);					
					map.put("bz", outValue[22]);
					map.put("sqly", outValue[23]);											  
				}				
				sql = "select a.tzxmdm,b.xmmc from sztz_xm_xxfbb a,sztz_xmdmb b where a.tzxmdm=b.xmdm  ";
				List tzxmList = dao.getList(sql, new String[] {}, new String[] {
						"tzxmdm", "xmmc" });
				request.setAttribute("tzxmList", tzxmList);
				map.put("stuExists", "yes");
				map.put("userType", userType);			
				request.setAttribute("rs", map);
				request.setAttribute("pk",pkValue);
				request.setAttribute("realTable",realTable);
				request.setAttribute("tableName",tableName);
				request.setAttribute("act", doType);// 发送数据查询类型
				request.setAttribute("doType", doType);
				request.setAttribute("writeAble", writeAble);
				newFwd = new ActionForward("/sztz/tzhd_sq_modi.jsp",false);
			}else if(realTable.equalsIgnoreCase("sztz_xfsbb")){
				HashMap<String,String> map = new HashMap<String,String>();
				sql = "select nd,xn,xq,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xmdm,kmm,xmmc,jb,xz,cg,lydm,xf,jbdm from "+tableName+" where xn||nd||xq||xmdm||xh=?";
				String[] outValue = dao.getOneRs(sql,new String[]{pkValue},new String[]{ 
						"nd","xn","xq","xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","xmdm","kmm","xmmc","jb","xz","cg","lydm","xf","jbdm"});
				if(outValue!=null){
					map.put("nd", outValue[0]);
					map.put("xn", outValue[1]);
					map.put("xq", outValue[2]);
					map.put("xh", outValue[3]);
					map.put("xm", outValue[4]);
					map.put("xb", outValue[5]);
					map.put("nj", outValue[6]);
					map.put("xydm", outValue[7]);
					map.put("xymc", outValue[8]);
					map.put("zydm", outValue[9]);
					map.put("zymc", outValue[10]);
					map.put("bjdm", outValue[11]);
					map.put("bjmc", outValue[12]);
					map.put("xmdm",outValue[13]);
					map.put("kmm", outValue[14]);
					map.put("xmmc", outValue[15]);
					map.put("cjtzxmjb", outValue[16]);
					map.put("tzxmcjsf", outValue[17]);
					map.put("cgms", outValue[18]);
					map.put("lydm", outValue[19]);
					map.put("xf", outValue[20]);
					map.put("jbdm", outValue[21]);
					sql = "select lynr from sztz_sqsblyb  where  lydm=? ";
					String lyInfo = dao.getOneRs(sql, new String[] {map.get("lydm")},
					"lynr");
					map.put("lynr", lyInfo);						    
					sql = "select jbdm,jbmc from sztz_hjjbdmb where xmdm = ?";
					List hjjbList = dao.getList(sql, new String[] {map.get("xmdm")}, new String[] {
							"jbdm", "jbmc" });
					request.setAttribute("hjjbList", hjjbList);//获奖类别列表
					if("do".equalsIgnoreCase(act)){

						if((jbdm == null || "".equalsIgnoreCase(jbdm))){ 
							sql = "select nvl(xf,'0') xf from sztz_xmdmb where xmdm=?";
							String xf = dao.getOneRs(sql, new String[]{map.get("xmdm")}, "xf");
							map.put("xf", xf);
							map.put("jbdm", jbdm);							
						}else if ((jbdm!=null || !"".equalsIgnoreCase(jbdm))){    
							sql = "select xf from sztz_hjjbdmb where jbdm=? and xmdm=?";
							String xf = dao.getOneRs(sql, new String[]{jbdm,map.get("xmdm")}, "xf");							
							map.put("xf", xf);
							map.put("jbdm", jbdm);
						}
					}	
				}			  
				sql = "select xmdm,xmmc from sztz_xmdmb";
				List tzxmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });
				request.setAttribute("tzxmList", tzxmList);
				sql = "select lydm,lymc from sztz_sqsblyb order by lymc";
				List lyList = dao.getList(sql, new String[] {}, new String[] {
						"lydm", "lymc" });
				request.setAttribute("lyList", lyList);//理由列表			
				map.put("stuExists", "yes");
				map.put("userType", userType);
				request.setAttribute("rs", map);
				request.setAttribute("doType", doType);
				request.setAttribute("act", doType);// 发送数据查询类型
				request.setAttribute("njList", Base.getNjList());// 发送年级列表
				request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
				request.setAttribute("xqList", Base.getXqList());// 发送学期列表
				request.setAttribute("cjxmjbList",sztzDao.getCjTzxmJbList());//发送参加项目级别列表
				request.setAttribute("tzxmcjsfList",sztzDao.getTzxmCjSfList());//发送素质拓展项目参加身份列表				
				request.setAttribute("realTable",realTable);
				request.setAttribute("tableName",tableName);
				request.setAttribute("pkValue",pkValue);
				request.setAttribute("pk",pk);
				request.setAttribute("writeAble", writeAble);
				newFwd = new ActionForward("/sztz/tzxf_sb_modi.jsp",false);
			}else if(realTable.equalsIgnoreCase("sztz_xm_xxfbb")){
				List xmdmList = null;		
				sql = "select  xmdm, xmmc from sztz_xmdmb";
				xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });
				HashMap<String, String> map = new HashMap<String, String>();
				sql="select * from "+realTable;
				String[] colList = dao.getColumnName(sql);
				sql += (" where " + pk + "='" + pkValue + "'");
				String[] record = dao.getOneRs(sql, new String[] {}, colList);
				if (record == null) {
					record = new String[colList.length];
				}				   				    
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), record[i]);
				}
				sql = "select b.kmm from sztz_xmdmb a, sztz_kmdmb b where a.kmdm=b.kmdm and a.xmdm=?";
				String kmInfo = dao.getOneRs(sql, new String[] {map.get("tzxmdm")},
				"kmm");
				if (map.get("tzxmdm") == null || map.get("tzxmdm").equals(null)){
					map.put("xmdm", "");
				}else {map.put("xmdm", map.get("tzxmdm").toString());}								    
				map.put("kmmc",kmInfo);
				request.setAttribute("pkValue", pkValue);// 发送表主键
				request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
				request.setAttribute("xqList", Base.getXqList());// 发送学期列表
				request.setAttribute("xmdmList", xmdmList);
				request.setAttribute("rs", map);
				request.setAttribute("doType", doType);				   
				newFwd = new ActionForward("/sztz/tzhd_modi.jsp", false); 
			}			
		} else if(doType.equalsIgnoreCase("save")){

			if (realTable.equalsIgnoreCase("sztz_hdcjsqb")) {
				//拓展活动参加申请修改
				String sqly = DealString.toGBK(request.getParameter("sqly"));
				sql = "update sztz_hdcjsqb set sqly=? where xn||nd||xq||tzxmdm||xh=?";
				dao.runUpdate(sql, new String[] {sqly,pkValue});				   
				request.setAttribute("path","sztz_modiData.do"); 
			}else if(realTable.equalsIgnoreCase("sztz_xfsbb")){
				//拓展学分个人申报		  
				String xmjb = DealString.toGBK(request.getParameter("cjtzxmjb"));
				String cjxz = DealString.toGBK(request.getParameter("tzxmcjsf"));
				String cgms = DealString.toGBK(request.getParameter("cgms"));
				String xf = DealString.toGBK(request.getParameter("xf"));
				jbdm = DealString.toGBK(request.getParameter("jbdm"));
				sql = "update sztz_xfsbb set jb=?,xz=?,cg=?,xf=?,jbdm=? where xn||nd||xq||tzxmdm||xh=?";
				dao.runUpdate(sql, new String[] {xmjb,cjxz,cgms,xf,jbdm, pkValue});			   
				request.setAttribute("path","sztz_modiData.do");
			}else if(realTable.equalsIgnoreCase("sztz_xm_xxfbb")){
				//拓展活动发布
				String sqjzrq = request.getParameter("sqjzrq");
				String xmkssj=request.getParameter("xmkssj");
				String xmjssj=request.getParameter("xmjssj");
				String sqztj=DealString.toGBK(request.getParameter("sqztj"));
				String xmnr=DealString.toGBK(request.getParameter("xmnr"));
				String bz=DealString.toGBK(request.getParameter("bz"));
				sql = "update sztz_xm_xxfbb set xmnr=?,sqztj=?,sqjzrq=?,xmkssj=?,xmjssj=?,bz=?" +
				" where xn||nd||xq||tzxmdm=?";
				dao.runUpdate(sql, new String[] {xmnr,sqztj,sqjzrq,xmkssj,xmjssj,bz,pkValue});			   
				request.setAttribute("path","sztz_modiData.do");
			}
			newFwd = new ActionForward("/sqb/comm_pub_mid.jsp", false);				
		}
		return newFwd;
	}  
	private ActionForward sztzXfSbSH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	throws Exception {			
		//数据查询	
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		HttpSession session = request.getSession();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");// sql条件
		String tips = "";// 位置提示信息
		String tableName = request.getParameter("tableName");// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "xn||nd||xq||xmdm||xh";;// 数据源表主键（格式为“字段名||字段名||字段名”）
		//String writeAble = "yes";// 写权限
		String dataType = request.getParameter("act");
		String xh = request.getParameter("xh");
		String nd = dataSearchForm.getNd();
		String nj = dataSearchForm.getNj();
		String xn = dataSearchForm.getXn();
		String xq = dataSearchForm.getXq();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xmmc = DealString.toGBK(dataSearchForm.getXmmc());
		dataSearchForm.setXmmc(xmmc);
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String pkValue = request.getParameter("pkValue");
		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1", new String[]{}, "xxmc");
		String xxdm = dao.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){//西昌学院
			request.setAttribute("isXCXY","yes");
		}
		int rightNd = new Integer(dao.getOneRs("select dqnd from xtszb", new String[]{}, new String[]{"dqnd"})[0]).intValue();
		List<HashMap<String, String>> ndList = new ArrayList<HashMap<String,String>>();
		for(int i=6; i >=0 ; i--){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("nd", (new Integer(rightNd-i)).toString());
			ndList.add(map);
		}			
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		if (dataType == null) {
			dataType = "";
		}
		//查询条件
		querry.append(((xh == null) || xh.equalsIgnoreCase(""))?"and 1=1 ":"and xh = '" + xh + "' ");
		querry.append(((nj == null) || nj.equalsIgnoreCase(""))?"and 1=1 ":"and nj = '" + nj + "' ");
		querry.append(((nj == null) || nj.equalsIgnoreCase(""))?"and 1=1 ":"and nj = '" + nj + "' ");
		querry.append(((xn == null) || xn.equalsIgnoreCase(""))?"and 1=1 ":"and xn = '" + xn + "' ");
		querry.append(((xq == null) || xq.equalsIgnoreCase(""))?"and 1=1 ":"and xq = '" + xq + "' ");
		querry.append(((zy == null) || zy.equalsIgnoreCase(""))?"and 1=1 ":"and zydm = '" + zy + "' ");
		if (userType.equalsIgnoreCase("xy")&&!dataType.equalsIgnoreCase("dormInfo")) {
			xy = userDep;
			dataSearchForm.setXydm(userDep);
		}
		querry.append(((zy == null) || zy.equalsIgnoreCase(""))?"and 1=1 ":"and zydm = '" + zy + "' ");
		querry.append(((bj == null) || bj.equalsIgnoreCase(""))?"and 1=1 ":"and bjdm = '" + bj + "' ");
		querry.append(((xy == null) || xy.equalsIgnoreCase(""))?"and 1=1 ":"and xydm = '" + xy + "' ");
		querry.append(((nd == null) || nd.equalsIgnoreCase(""))?"and 1=1 ":"and nd='"+nd+"' ")	;		
		querry.append(Base.isNull(xmmc)?"":" and xmmc like '%"+xmmc+"%' ");
		if( (dataType.equalsIgnoreCase("save")||dataType.equalsIgnoreCase("viewOne"))&&
				tableName.equalsIgnoreCase("view_sztzxfsbxx")){
			if( dataType.equalsIgnoreCase("save")){//保存修改
				String yesNo = request.getParameter("yesNo");
				yesNo = DealString.toGBK(yesNo);
				String xf = request.getParameter("xf");
				String hjjb = DealString.toGBK(request.getParameter("jbdm"));
				boolean modi = false;
				boolean del = false;
				String [] xfsbInfo=dao.getOneRs("select xh,xn,xq,nd,xmdm,kmdm from view_sztzxfsbxx where xn||nd||xq||xmdm||xh=?",
						new String[]{pkValue}, new String[]{"xh","xn","xq","nd","xmdm","kmdm"});
				if (userType.equalsIgnoreCase("xx")) {
					sql = "update sztz_xfsbb set xxsh=? where xn||nd||xq||tzxmdm||xh=?";
				} else {
					sql = "update sztz_xfsbb set xysh=? where xn||nd||xq||tzxmdm||xh=?";
				}
				modi = dao.runUpdate(sql, new String[] { yesNo, pkValue});
				if(userType.equalsIgnoreCase("xx")){
					if ("通过".equalsIgnoreCase(yesNo)||"已通过".equalsIgnoreCase(yesNo)){
						if(modi){
							sql = "delete from sztz_xscjxxb where xn||nd||xq||xmdm||xh=?";
							del = dao.runUpdate(sql, new String[] {pkValue});    	 	   		    
							if (del) {
								sql = "insert into sztz_xscjxxb(xh,xn,xq,nd,xmdm,kmdm,hjjb,xf) values( ?,?,?,?,?,?,?,?)";
								dao.runUpdate(sql, new String[] {xfsbInfo[0],xfsbInfo[1],xfsbInfo[2],xfsbInfo[3],xfsbInfo[4],xfsbInfo[5],hjjb,xf});
							}
						}
					}else {
						sql = "delete from sztz_xscjxxb where xn||nd||xq||xmdm||xh=?";
						del = dao.runUpdate(sql, new String[] {pkValue});   
					}
				}
				request.setAttribute("writeAble", "writeAble");  
				request.setAttribute("result", "view");
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select "
					+ pk
					+ ",nd,xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,xmmc,xxsh yesNo,kmm,sbsj,jb,xz,cg,lydm,xf,jbdm from view_sztzxfsbxx where "
					+ pk + "='" + pkValue + "'";
			} else {
				sql = "select "
					+ pk
					+ ",nd,xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,xmmc,xysh yesNo,kmm,sbsj,jb,xz,cg,lydm,xf,jbdm from view_sztzxfsbxx where "
					+ pk + "='" + pkValue + "'";
			}
			colList = new String[] { pk, "nd", "xn","xq", "xh", "xm", "xb", "nj","xymc",
					"zymc", "bjmc", "xmmc", "yesNo","kmm","sbsj","jb","xz","cg","lydm","xf","jbdm"};
			String[] rsvalue = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				rsvalue[i] = (Base.isNull(rsvalue[i])) ? " ": rsvalue[i];
				request.setAttribute(colList[i], rsvalue[i]);
			}
			String lynr = dao.getOneRs("select lynr from sztz_sqsblyb where lydm = ?", new String[]{rsvalue[18]}, "lynr");//理由内容
			String jbmc = dao.getOneRs("select jbmc from sztz_hjjbdmb where jbdm = ?", new String[]{rsvalue[20]}, "jbmc");//奖项类别名
			dataSearchForm.setYesNo(rsvalue[12]);
			request.setAttribute("sqly", lynr);
			request.setAttribute("jbmc", jbmc);
			request.setAttribute("jbdm",rsvalue[20]);
			request.setAttribute("tableName", tableName);// 发送视图名
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			request.setAttribute("act", dataType);// 发送数据查询类型
			writeAble = (Base.chkUPower(sUName,"sztz_xfsb_sh.do?act=sbsh", userType.equalsIgnoreCase("student"))==1)?"yes":"no";
			request.setAttribute("writeAble", writeAble);
			return new ActionForward("/sztz/xfsbsh_one.jsp", false);	
		}
		if (dataType.equalsIgnoreCase("sbsh")){	 			
			realTable = "sztz_xfsbb";
			tips = "素质拓展 - 申报审核与考评 - 审核";
			tableName = "view_sztzxfsbxx";
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "xh",
						"xm", "bjmc", "xmmc","cg", "xxsh" };
				sql = "select rownum 行号,(case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select  "
					+ pk
					+ " 主键, a.* from "
					+ tableName
					+ " a" + querry + " and xysh='通过' order by xxsh desc) a " ;
			} else{
				dataSearchForm.setXydm(userDep);
				colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "xh",
						"xm", "bjmc", "xmmc","cg", "xysh" };
				sql = "select rownum 行号,(case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
			}

		}
		colListCN = dao.getColumnNameCN(colList, tableName);
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
		xxmc = dao.getOneRs("select xxmc from xtszb", new String[]{}, "xxmc");
		getListxx(request,dao,xy,zy,nj);
		request.setAttribute("xxmc", xxmc);//取学校名称信息
		//request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		writeAble = (Base.chkUPower(sUName,"sztz_xfsb_sh.do?act=sbsh", userType.equalsIgnoreCase("student"))==1)?"yes":"no";
		request.setAttribute("writeAble", writeAble);
		return  mapping.findForward("success");
	}
	private ActionForward tzCjHzQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		HttpSession session = request.getSession();
		String xxdm    = dao.getXxdm();
		String tableName="view_sztzcjhz";
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();	
		boolean disabled = true;
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");// sql条件
		String rsNum = "0";// 返回的记录数
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh().trim();
		String xm = dataSearchForm.getXm().trim();
		if(Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){
			tableName="jsxx_view_sztzcjhz";
		}

		if(userSpecType.equalsIgnoreCase("xy")){
			xy = userDep;
			dataSearchForm.setXydm(xy);
		}
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		//查询条件
		querry = querry.append(((nj == null) || nj.equalsIgnoreCase(""))?"and 1=1 ":"and nj = '" + nj + "' ");
		querry = querry.append(((xy == null) || xy.equalsIgnoreCase(""))?"and 1=1 ":"and xydm = '" + xy + "' ");
		querry = querry.append(((zy == null) || zy.equalsIgnoreCase(""))?"and 1=1 ":"and zydm = '" + zy + "' ");
		querry = querry.append(((bj == null) || bj.equalsIgnoreCase(""))?"and 1=1 ":"and bjdm = '" + bj + "' ");
		querry = querry.append(((xh == null) || xh.equalsIgnoreCase(""))?"and 1=1 ":"and xh = '" + xh + "' ");
		querry = querry.append(((xm == null) || xm.equalsIgnoreCase(""))?"and 1=1 ":"and xm like '%" + DealString.toGBK(xm) + "%' ");		
		dataSearchForm.setXm(DealString.toGBK(xm));


		sql = "select * from (select * from( select distinct xh,xm,xb,nj,xymc,bjmc,"
			+" (case when zxf<1 then '0'||to_char(ltrim(trim (zxf),'0')) else trim(zxf) end )zxf ," 
			+" rownum r from "+tableName+ querry + ") "			
			+ ") where r>" + dataSearchForm.getPages().getStart()+" and r<= "
			+ (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) ;

		colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "bjmc","zxf"};		
		colListCN = dao.getColumnNameCN(colList, tableName);
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
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry, new String[]{}, "count")));			
		if(userSpecType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
			xy = userDep;
			dataSearchForm.setXydm(xy);
			disabled = false;
		}
		request.setAttribute("disabled", disabled);
		getListxx(request,dao,xy,zy,nj);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("tableName", tableName);
		return mapping.findForward("success");
	}
	private ActionForward sztzCjHz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//学分汇总 
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		SztzDao sztzDao=new SztzDao();
		Vector<Object> rs = new Vector<Object>();
		ActionForward fwd= new ActionForward();
		String dataType = request.getParameter("act");
		String xh = request.getParameter("xh").trim();
		String nj = request.getParameter("nj").trim();
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		String[] colList = null;
		StringBuffer sql=new StringBuffer();
		StringBuffer sqla =new StringBuffer();
		float hzzj=0;
		String[] xqdm=sztzDao.getRs("select xqdm from xqdzb order by xqdm", new String[] {}, "xqdm");			
		String dqxn=dao.getConf(0);//获得系统设置表中当前学年
//		String dqxq=dao.getConf(1);//获得系统设置表中当前学期	
//		String[] nd = dqxn.split("-");
//		int dqnd=Integer.parseInt(nd[0]);//获得当前年度
		String onexq="";
		String towxq="";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			onexq=xqdm[0];//秋季	
			towxq=xqdm[1];//春季	
		}else{
			towxq=xqdm[0];//春季	
			onexq=xqdm[1];//秋季				    		
		}			
//		int len=dqnd-Integer.parseInt(nj)+1;//"len"统计学生年级数
		/***/

		String xzStr = dao.getOneRs("select fun_Disp_dxtonum(nvl(xz,'4'))xz from view_xsjbxx where xh=?", new String[]{xh},"xz");
		int lenStr = Integer.parseInt(xzStr);//获得学生学制
		String[] xnListStr =  new String[lenStr];//学生在校期间初始学年度列表
		String[] njCNStr = new String[lenStr];//存储中文年级
		int njStr = Integer.parseInt(nj);
		for(int i=0;i<lenStr;i++){//根据学制获得该生所在期间学年列表及中文年级值
			int m = Integer.parseInt(nj)+i;
			int n = Integer.parseInt(nj)+i+1;
			xnListStr[i]= m+"-"+n;
			njCNStr[i]=(DealString.numToChnum(i+1+""))+"年级";
		}
		//获得素质拓展成果奖学年列表
		String[] xnVList = sztzDao.getRs(" select xn from view_sztzcj where xh=?  group by xn order by xn ", new String[]{xh},"xn");
		int xnVLen = xnVList.length;//获得素质拓展成果奖学年列表长度

		/**学生在校期间初始学年度列表值与获得素质拓展成果奖学年列表进行对比，并且，
		若后者有大于前者值时，将大的值按前者数组下标值由大到小逐个赋给前者。以实现能统计学生休学、征兵入伍后等返校学习期间获奖的情况
		 */
		if(lenStr!=0
				&&xnVLen!=0
//				&&(Integer.parseInt(xnVList[xnVLen-1].substring(xnVList[xnVLen-1].length()-4,xnVList[xnVLen-1].length()))
//				>Integer.parseInt(xnListStr[lenStr-1].substring(xnListStr[lenStr-1].length()-4,xnListStr[lenStr-1].length())))
		){
			int k =(lenStr>xnVLen)?xnVLen:lenStr;//取前后数组列表最小长度	
			int indexV = lenStr-1;//前列表最大长度
			for(int i=k;i>0;i--){
				//int xnVa = Integer.parseInt(xnListStr[i-1].substring(xnListStr[i-1].length()-4,xnListStr[i-1].length()));
				int xnVb = Integer.parseInt(xnVList[i-1].substring(xnVList[i-1].length()-4,xnVList[i-1].length()));				
				boolean exists= true;				
				for(int j=0;j<lenStr;j++){//遍历前列表值，并与后列表i下标值进行对比
					if(Integer.parseInt(xnListStr[j].substring(xnListStr[j].length()-4,xnListStr[j].length()))==xnVb){
						exists=false;
					}
				}
				if(exists){
					xnListStr[indexV--]=xnVList[i-1];
				}				
			}
		}
		String kmdm[]  = sztzDao.getRs("select kmdm from sztz_kmdmb order by kmdm", new String[]{},"kmdm");//素质拓展科目列表
		if(kmdm==null){
			request.setAttribute("errMsg", "先在路径 \"系统维护 - 代码维护 - 素质拓展\"下，维护科目设立！");
			return new ActionForward("/errMsg.do", false);			
		}
		/***/
		if(dataType.equalsIgnoreCase("lnhz")){
			for(int i=0;i<lenStr;i++){
				njStr++;
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("nj",njCNStr[i]);
				sql=new StringBuffer();
				sqla=new StringBuffer();
				String onehzzj = "0";
				String towhzzj = "0";
				if(Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){//江苏信息职业技术学院
					/**读取学生军训成绩表中的军训成绩,大于60分时加2个学分
				读取评奖评优中的德育成绩,大于60分时加1个学分
				读取成绩表(cjb)中'就业指导课'学分,及课程性质为选修课的课程学分*/
					sql.append(" select * from (select a.xmmc,a.xf,a.bz,b.xmmc1,b.xf1,b.bz1 from ( select rownum hh,xmmc,xf,bz from ( ");
					sql.append(" select xmmc,xf,bz from (select xmmc,  xf,  bz  from view_sztzcj where xn like ? and xh=? and xq=?  ");
					sql.append(" )union all (select '军事训练'xmmc,'2'xf,''bz from jxgl_cjb where cj>='60'  and substr(xn,0,4) +1=? and xh=?) ");
					sql.append(" union all (select '德育考核'xmmc,'1'xf,''bz from zhszcp_dyszjfwh where dyzf>='60' and  xh=? and xn like ? and xq=?) ");
					sql.append(" union  all(select kcmc xmmc,xf,''bz from cjb where (kcxz='03'or kcmc like '就业%')  and  xh=? and xn like ? and xq=? ) ");
					sql.append(" )) a,(select rownum hh,xmmc1,xf1,bz1 from (select xmmc1,xf1,bz1 from (select xmmc xmmc1,xf xf1,bz bz1 from view_sztzcj where xn like ? and xh=?  and xq=? "); 
					sql.append(" )union all (select '德育考核'xmmc1,'1'xf1f,''bz1 from zhszcp_dyszjfwh where dyzf>='60' and  xh=? and xn like ? and xq=?) ");
					sql.append(" union all (select kcmc xmmc1,xf xf1,''bz1 from cjb where (kcxz='03'or kcmc like '就业%')  and  xh=? and xn like ? and xq=? ) ");
					sql.append(" )) b ,(select rownum hh,xmmc xmmc1,xf xf1,bz bz1 from view_sztzcj) c where (c.hh=b.hh(+) and c.hh=a.hh(+) )) where not ");
					sql.append(" ( xmmc is null and xmmc1 is null) ");
					colList = new String[] { "xmmc", "xf", "bz","xmmc1","xf1","bz1"};
					map.put("njList", dao.getList(sql.toString(), new String[] {"%"+xnListStr[i]+"%",xh,onexq,njStr+"",xh,
						xh,"%"+xnListStr[i]+"%",onexq,xh,"%"+xnListStr[i]+"%",onexq,"%"+xnListStr[i]+"%",xh,towxq,xh,"%"+xnListStr[i]+"%",towxq,xh,"%"+xnListStr[i]+"%",towxq}, colList));
					//一学期汇总
					sqla.append(" select  (case when to_char(nvl(sum(xfzh),0),'99999.99')=0 then '0.00' else  to_char(nvl(sum(xfzh),0),'99999.99') end )xfzh from (  ");
					sqla.append(" (select nvl(sum(xf),0)xfzh from sztz_xscjxxb where xn like ? and xh=? and xq=?) ");
					sqla.append(" union all(select nvl(sum(xf),0)xfzh from (select '2'xf from jxgl_cjb c where cj>='60' and c.xh=? and substr(xn,0,4)+1=? ");
					sqla.append(" ))union all(select nvl(sum(xf),0)xfzh from (select '1'xf from zhszcp_dyszjfwh d where dyzf>='60' and xh=? and xn like ? and xq=? ");		
					sqla.append(" ))union all(select nvl(sum(xf),0)xfzh from (select xf from cjb e where (kcxz='03' or kcmc like '就业%') ");
					sqla.append(" and xh=? and xn like ? and xq=? )))");
					map.put("oenxqcjzhList",dao.getList(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,onexq,xh,njStr+"",xh,"%"+xnListStr[i]+"%",onexq,xh,"%"+xnListStr[i]+"%",onexq}, new String[]{"xfzh"}));
					onehzzj=dao.getOneRs(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,onexq,xh,njStr+"",xh,"%"+xnListStr[i]+"%",onexq,xh,"%"+xnListStr[i]+"%",onexq}, "xfzh");
					//二学期汇总
					sqla   = new StringBuffer();
					sqla.append(" select  (case when to_char(nvl(sum(xfzh),0),'99999.99')=0 then '0.00' else  to_char(nvl(sum(xfzh),0),'99999.99') end )xfzh from (  ");
					sqla.append(" (select nvl(sum(xf),0)xfzh from sztz_xscjxxb where xn like ? and xh=? and xq=?) ");
					sqla.append(" union all(select nvl(sum(xf),0)xfzh from (select '1'xf from zhszcp_dyszjfwh d where dyzf>='60' and xh=? and xn like ? and xq=? ");		
					sqla.append(" ))union all(select nvl(sum(xf),0)xfzh from (select xf from cjb e where (kcxz='03' or kcmc like '就业%') ");
					sqla.append(" and xh=? and xn like ? and xq=? )))");
					map.put("towxqcjzhList",dao.getList(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,towxq,xh,"%"+xnListStr[i]+"%",towxq,xh,"%"+xnListStr[i]+"%",towxq}, new String[]{"xfzh"}));						
					towhzzj=dao.getOneRs(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,towxq,xh,"%"+xnListStr[i]+"%",towxq,xh,"%"+xnListStr[i]+"%",towxq}, "xfzh");						
				}else{
					sql.append("select * from (select a.xmmc,a.xf,a.bz,b.xmmc1,b.xf1,b.bz1 from ( select rownum hh,  xmmc,  xf, ");
					sql.append(" bz  from view_sztzcj where xn like ? and xh=? and xq=? ) a,(select rownum hh,xmmc " );
					sql.append("xmmc1,xf xf1,bz bz1 from view_sztzcj where xn like ? and xh=?  and xq=? ) b ,(" );
					sql.append("select rownum hh,xmmc xmmc1,xf xf1,bz bz1 from view_sztzcj where xh=?  )" );
					sql.append(" c where (c.hh=b.hh(+) and c.hh=a.hh(+) )) where not( xmmc is   null and xmmc1 is   null)");							
					colList = new String[] { "xmmc", "xf", "bz","xmmc1","xf1","bz1"};
					map.put("njList", dao.getList(sql.toString(), new String[] {"%"+xnListStr[i]+"%",xh,onexq,"%"+xnListStr[i]+"%",xh,towxq,xh}, colList));

					sqla.append(" select to_char(sum (xf),'99999.99')xfzh from sztz_xscjxxb where xn like ? and xh=? and xq=?" );		
					map.put("oenxqcjzhList",dao.getList(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,onexq}, new String[]{"xfzh"}));
					map.put("towxqcjzhList",dao.getList(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,towxq}, new String[]{"xfzh"}));					
					onehzzj=dao.getOneRs(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,onexq}, "xfzh");
					towhzzj=dao.getOneRs(sqla.toString(), new String[] {"%"+xnListStr[i]+"%",xh,towxq}, "xfzh");					
				}
				if(onehzzj==null){
					onehzzj="0";
				}
				if(towhzzj==null){
					towhzzj="0";
				}
				hzzj+=Float.parseFloat(onehzzj)+Float.parseFloat(towhzzj);
				rs.add(map);
			}

//			if(len==5){
//			len=len-1;//"len"始终不等于四，以避免页面上出现四年级(针对三年制专科院校),学生毕业一年内可统计，以后不能统计
//			}		
//			if((dqnd-Integer.parseInt(nj)==5 && dqxq==onexq) || ((len > 5) )){
//			request.setAttribute("errMsg", "只能汇总三年级内的拓展成绩！");
//			return new ActionForward("/errMsg.do", false);
//			}else if(len<5){
//			String[] njList =  new String[len];
//			String[] njCN = new String[]{"一年级","二年级","三年级","四年级"};
//			System.arraycopy(njCN, 0, njList, 0, len);
//			int cjxn=Integer.parseInt(nj);
//			int qjxn=Integer.parseInt(nj);
//			for(int i=0;i<len;i++){
//			qjxn=qjxn+1;
//			cjxn=cjxn+1;
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("nj", njCN[i]);
//			sql=new StringBuffer();
//			sqla=new StringBuffer();
//			String onehzzj = "0";
//			String towhzzj = "0";
//			if(Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){//江苏信息职业技术学院
//			/**读取学生军训成绩表中的军训成绩,大于60分时加2个学分
//			读取评奖评优中的德育成绩,大于60分时加1个学分
//			读取成绩表(cjb)中'就业指导课'学分,及课程性质为选修课的课程学分*/
//			sql.append(" select * from (select a.xmmc,a.xf,a.bz,b.xmmc1,b.xf1,b.bz1 from ( select rownum hh,xmmc,xf,bz from ( ");
//			sql.append(" select xmmc,xf,bz from (select xmmc,  xf,  bz  from view_sztzcj where xn like ? and xh=? and xq=?  ");
//			sql.append(" )union all (select '军事训练'xmmc,'2'xf,''bz from jxgl_cjb where cj>='60'  and nd+1=? and xh=?) ");
//			sql.append(" union all (select '德育考核'xmmc,'1'xf,''bz from zhszcp_dyszjfwh where dyzf>'60' and  xh=? and xn like ? and xq=?) ");
//			sql.append(" union  all(select kcmc xmmc,xf,''bz from cjb where (kcxz='03'or kcmc like '就业%')  and  xh=? and xn like ? and xq=? ) ");
//			sql.append(" )) a,(select rownum hh,xmmc1,xf1,bz1 from (select xmmc1,xf1,bz1 from (select xmmc xmmc1,xf xf1,bz bz1 from view_sztzcj where xn like ? and xh=?  and xq=? "); 
//			sql.append(" )union all (select '德育考核'xmmc1,'1'xf1f,''bz1 from zhszcp_dyszjfwh where dyzf>='60' and  xh=? and xn like ? and xq=?) ");
//			sql.append(" union all (select kcmc xmmc1,xf xf1,''bz1 from cjb where (kcxz='03'or kcmc like '就业%')  and  xh=? and xn like ? and xq=? ) ");
//			sql.append(" )) b ,(select rownum hh,xmmc xmmc1,xf xf1,bz bz1 from view_sztzcj) c where (c.hh=b.hh(+) and c.hh=a.hh(+) )) where not ");
//			sql.append(" ( xmmc is null and xmmc1 is null) ");
//			colList = new String[] { "xmmc", "xf", "bz","xmmc1","xf1","bz1"};
//			map.put("njList", dao.getList(sql.toString(), new String[] {"%-"+qjxn,xh,onexq,qjxn+"",xh,
//			xh,"%-"+qjxn,onexq,xh,"%-"+qjxn,onexq,"%-"+cjxn,xh,towxq,xh,"%-"+cjxn,towxq,xh,"%-"+cjxn,towxq}, colList));
//			sqla.append(" select  (case when to_char(nvl(sum(xfzh),0),'99999.99')=0 then '0.00' else  to_char(nvl(sum(xfzh),0),'99999.99') end )xfzh from (  ");
//			sqla.append(" (select nvl(sum(xf),0)xfzh from sztz_xscjxxb where xn like ? and xh=? and xq=?) ");
//			sqla.append(" union all(select nvl(sum(xf),0)xfzh from (select '2'xf from jxgl_cjb c where cj>='60' and c.xh=? and nd+1=? ");
//			sqla.append(" ))union all(select nvl(sum(xf),0)xfzh from (select '1'xf from zhszcp_dyszjfwh d where dyzf>='60' and xh=? and xn like ? and xq=? ");		
//			sqla.append(" ))union all(select nvl(sum(xf),0)xfzh from (select xf from cjb e where (kcxz='03' or kcmc like '就业%') ");
//			sqla.append(" and xh=? and xn like ? and xq=? )))");
//			map.put("oenxqcjzhList",dao.getList(sqla.toString(), new String[] {"%-"+qjxn,xh,onexq,xh,qjxn+"",xh,"%-"+qjxn,onexq,xh,"%-"+qjxn,onexq}, new String[]{"xfzh"}));
//			onehzzj=dao.getOneRs(sqla.toString(), new String[] {"%-"+qjxn,xh,onexq,xh,qjxn+"",xh,"%-"+qjxn,onexq,xh,"%-"+qjxn,onexq}, "xfzh");
//			sqla   = new StringBuffer();
//			sqla.append(" select  (case when to_char(nvl(sum(xfzh),0),'99999.99')=0 then '0.00' else  to_char(nvl(sum(xfzh),0),'99999.99') end )xfzh from (  ");
//			sqla.append(" (select nvl(sum(xf),0)xfzh from sztz_xscjxxb where xn like ? and xh=? and xq=?) ");
//			sqla.append(" union all(select nvl(sum(xf),0)xfzh from (select '1'xf from zhszcp_dyszjfwh d where dyzf>='60' and xh=? and xn like ? and xq=? ");		
//			sqla.append(" ))union all(select nvl(sum(xf),0)xfzh from (select xf from cjb e where (kcxz='03' or kcmc like '就业%') ");
//			sqla.append(" and xh=? and xn like ? and xq=? )))");
//			map.put("towxqcjzhList",dao.getList(sqla.toString(), new String[] {"%-"+cjxn,xh,towxq,xh,"%-"+cjxn,towxq,xh,"%-"+cjxn,towxq}, new String[]{"xfzh"}));						
//			towhzzj=dao.getOneRs(sqla.toString(), new String[] {"%-"+cjxn,xh,towxq,xh,"%-"+cjxn,towxq,xh,"%-"+cjxn,towxq}, "xfzh");						
//			}else{
//			sql.append("select * from (select a.xmmc,a.xf,a.bz,b.xmmc1,b.xf1,b.bz1 from ( select rownum hh,  xmmc,  xf, ");
//			sql.append(" bz  from view_sztzcj where xn like ? and xh=? and xq=? ) a,(select rownum hh,xmmc " );
//			sql.append("xmmc1,xf xf1,bz bz1 from view_sztzcj where xn like ? and xh=?  and xq=? ) b ,(" );
//			sql.append("select rownum hh,xmmc xmmc1,xf xf1,bz bz1 from view_sztzcj where xh=?  )" );
//			sql.append(" c where (c.hh=b.hh(+) and c.hh=a.hh(+) )) where not( xmmc is   null and xmmc1 is   null)");							
//			colList = new String[] { "xmmc", "xf", "bz","xmmc1","xf1","bz1"};
//			map.put("njList", dao.getList(sql.toString(), new String[] {"%-"+qjxn,xh,onexq,"%-"+cjxn,xh,towxq,xh}, colList));

//			sqla.append(" select to_char(sum (xf),'99999.99')xfzh from sztz_xscjxxb where xn like ? and xh=? and xq=?" );		
//			map.put("oenxqcjzhList",dao.getList(sqla.toString(), new String[] {"%-"+qjxn,xh,onexq}, new String[]{"xfzh"}));
//			map.put("towxqcjzhList",dao.getList(sqla.toString(), new String[] {"%-"+cjxn,xh,towxq}, new String[]{"xfzh"}));					
//			onehzzj=dao.getOneRs(sqla.toString(), new String[] {"%-"+qjxn,xh,onexq}, "xfzh");
//			towhzzj=dao.getOneRs(sqla.toString(), new String[] {"%-"+cjxn,xh,towxq}, "xfzh");
//			}
//			if(onehzzj==null){
//			onehzzj="0";
//			}
//			if(towhzzj==null){
//			towhzzj="0";
//			}
//			hzzj+=Float.parseFloat(onehzzj)+Float.parseFloat(towhzzj);
//			rs.add(map);
//			}				
			request.setAttribute("allcj",  new java.text.DecimalFormat("#,##0.00").format(hzzj));
//			}
			request.setAttribute("rs",rs);// 发送数据集
			//分科目总成绩汇总
			sql = new StringBuffer();
			sql.append(" select a.kmdm,a.kmm, (case when a.kmm like '%思想政治%' then  (select sum(xf)+nvl(b.zxf,0) from (select  '1' xf from zhszcp_dyszjfwh where dyzf >= '60' and xh = ?)) ");
			sql.append("  when a.kmm like '%军事教育%' then (select sum(xf)+nvl(b.zxf,0) from ( select  '2' xf from jxgl_cjb where cj >= '60' and xh = ?))");
			sql.append(" when a.kmm like '%人文素质%' then (select sum(xf)+nvl(b.zxf,0)   from cjb where (kcxz = '03' or kcmc like '%就业%') and xh = a.xh)");
			sql.append(" else b.zxf end )zxf  from (select ? xh,a.* from sztz_kmdmb a order by kmdm) a left join ");
			sql.append(" ( select kmdm, (select kmm from sztz_kmdmb b where a.kmdm=b.kmdm)kmmc,sum(nvl(xf,0))zxf,xh "); 
			sql.append(" from view_sztzcj a  group by kmdm,xh  )b on a.kmdm=b.kmdm and a.xh=b.xh  ");
			request.setAttribute("zxfrs",dao.getList(sql.toString(),new String[]{xh,xh,xh},new String[]{"kmm","zxf"}));
			request.setAttribute("rskm",kmdm);
			fwd = new ActionForward("/sztz/tzcjlnhz.jsp", false);
		}else if(dataType.equalsIgnoreCase("dnhz")){			
			for(int i=0;i<kmdm.length;i++){	
				HashMap<String, Object> map = new HashMap<String, Object>();
				sql=new StringBuffer();
				List xmList = null;
				if(Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){//江苏信息职业技术学院 Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)
					sql.append(" select kmm,xmmc,xf,bz from view_sztzcj where xh=? and xn = ?  and xq = ? and kmdm=? ");
					sql.append(" union all (select (select kmm from sztz_kmdmb where kmm like'%国防%' and kmdm=? )kmm,");
					sql.append(" '军事训练'xmmc,'2'xf,''bz from jxgl_cjb where cj>='60'  and substr(xn,0,4)||'-'||(substr(xn,0,4)+1)=?  and xh=? ");
					sql.append(" and (select kmm from sztz_kmdmb where  kmdm=?) like '%国防%')");
					sql.append(" union all(select (select kmm from sztz_kmdmb where kmm like'%思想%'and kmdm=? and rownum=1)kmm,");
					sql.append(" '德育考评'xmmc,'1'xf ,''bz from zhszcp_dyszjfwh d where dyzf>='60'  and xh=?  and xn = ?  and xq=?");
					sql.append(" and (select kmm from sztz_kmdmb where  kmdm=?) like '%思想%'  )");
					sql.append(" union all(select (select kmm from sztz_kmdmb where kmm like'%人文%'and kmdm=? and rownum=1)kmm,");
					sql.append(" kcmc xmmc,xf,''bz from cjb  where (kcxz='03' or kcmc like '就业%') ");
					sql.append(" and xh=?  and xn = ?  and xq=? and (select kmm from sztz_kmdmb where kmdm=?) like '%人文%'  )");
					colList=new String[]{"xmmc","xf","bz"};
					xmList = dao.getList(sql.toString(), new String[] { xh, dqxn,onexq,kmdm[i],kmdm[i],dqxn,xh,kmdm[i],kmdm[i],
						xh,dqxn,onexq,kmdm[i],kmdm[i],xh,dqxn,onexq,kmdm[i]}, colList);
				}else{
					sql.append("select kmm,xmmc,xf,bz from view_sztzcj where xh =? and xn like ? and xq=? and kmdm=?");
					colList=new String[]{"xmmc","xf","bz"};
					xmList = dao.getList(sql.toString(), new String[] { xh, dqxn,onexq,kmdm[i]}, colList);
				}				
				map.put("xmList", xmList);
				map.put("kmList", dao.getList("select kmm from sztz_kmdmb where kmdm='"+kmdm[i]+"'", new String[] {}, new String[]{"kmm"}));                	

				sql=new StringBuffer();
				List xmListb = null;
				if(Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){//江苏信息职业技术学院
					sql.append(" select kmm,xmmc,xf,bz from view_sztzcj where xh=? and xn = ?  and xq = ? and kmdm=? ");
					sql.append(" union all(select (select kmm from sztz_kmdmb where kmm like'%思想%'and kmdm=? and rownum=1)kmm,");
					sql.append(" '德育考评'xmmc,'1'xf ,''bz from zhszcp_dyszjfwh d where dyzf>='60'  and xh=?  and xn = ?  and xq=?");
					sql.append(" and (select kmm from sztz_kmdmb where  kmdm=?) like '%思想%'  )");
					sql.append(" union all(select (select kmm from sztz_kmdmb where kmm like'%人文%'and kmdm=? and rownum=1)kmm,");
					sql.append(" kcmc xmmc,xf,''bz from cjb  where (kcxz='03' or kcmc like '就业%') ");
					sql.append(" and xh=?  and xn = ?  and xq=? and (select kmm from sztz_kmdmb where kmdm=?) like '%人文%'  )");
					colList=new String[]{"xmmc","xf","bz"};
					xmListb = dao.getList(sql.toString(), new String[] { xh,dqxn,towxq,kmdm[i],kmdm[i],xh,dqxn,towxq,kmdm[i],kmdm[i],xh,dqxn,towxq,kmdm[i]}, colList);
				}else{
					sql.append(" select kmm,xmmc,xf,bz from view_sztzcj where xh =? and xn = ? and xq=? and kmdm=?");				
					colList=new String[]{"xmmc","xf","bz"};
					xmListb = dao.getList(sql.toString(), new String[] { xh,dqxn,towxq,kmdm[i]}, colList);
				}
				map.put("xmListb", xmListb);
				map.put("kmListb", dao.getList("select kmm from sztz_kmdmb where kmdm='"+kmdm[i]+"'", new String[] {}, new String[]{"kmm"}));                	
				request.setAttribute("xmList",xmList);
				request.setAttribute("xmListb",xmListb);
				rs.add(map);  					
			}	
			request.setAttribute("rs",rs);//发送数据集
			fwd = new ActionForward("/sztz/tzcjdnhz.jsp", false);
		}
		request.setAttribute("xh", xh);
		request.setAttribute("nj", nj);
		request.setAttribute("xm", xm);
		request.setAttribute("xy", xy);
		request.setAttribute("bj", bj);
		return fwd;
	}   
	private ActionForward sztzExpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		HttpSession session = request.getSession();
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");// sql条件
		String tableName = "";// 查询信息源（多为视图名）
		String nj = dataSearchForm.getNj();
		String xn = dataSearchForm.getXn();
		String nd = dataSearchForm.getNd();
		String xq = dataSearchForm.getXq();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xmdm = dataSearchForm.getXmdm();
		String xm = DealString.toGBK(dataSearchForm.getXm());
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String xh = dataSearchForm.getXh();
		if (tableName == null ){
			tableName = " ";
		}
		String zd = " * ";
		if(tableName.equalsIgnoreCase("view_sztzcjhz")){
			zd = "distinct xh,xm,xb,nj,xydm,xymc,bjdm,bjmc,zxf";
		}
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		//查询条件
		querry.append(((xh == null) || xh.equalsIgnoreCase(""))?"and 1=1 ":"and xh = '"+xh+"' ");
		querry.append(((nj == null) || nj.equalsIgnoreCase(""))?"and 1=1 ":"and nj = '" + nj + "' ");
		querry.append(((nj == null) || nj.equalsIgnoreCase(""))?"and 1=1 ":"and nj = '" + nj + "' ");
		querry.append(((xn == null) || xn.equalsIgnoreCase(""))?"and 1=1 ":"and xn = '" + xn + "' ");
		querry.append(((nd == null) || nd.equalsIgnoreCase(""))?"and 1=1 ":"and nd = '" + nd + "' ");
		querry.append(((xq == null) || xq.equalsIgnoreCase(""))?"and 1=1 ":"and xq = '" + xq + "' ");		
		querry.append(((xy == null) || xy.equalsIgnoreCase(""))?"and 1=1 ":"and xydm = '" + xy + "' ");
		querry.append(((zy == null) || zy.equalsIgnoreCase(""))?"and 1=1 ":"and zydm = '" + zy + "' ");
		querry.append(((bj == null) || bj.equalsIgnoreCase(""))?"and 1=1 ":"and bjdm = '" + bj + "' ");
		querry.append(((xm == null) || xm.equalsIgnoreCase(""))?"and 1=1 ":"and xm like '%"+ xm +"%' ");

		if (tableName.equalsIgnoreCase("view_sztzcjcp")){
			querry.append((userType.equalsIgnoreCase("xx"))?" and xxsh='通过' ":" and xysh='通过' and xydm='"+ userDep+ "' ");
			querry.append(((xmdm == null) || xmdm.equalsIgnoreCase(""))?"and 1=1 ":"and tzxmdm = '" + xmdm + "' ");
		}
		if (tableName.equalsIgnoreCase("view_sztzxfsbcj")){
			querry.append((userType.equalsIgnoreCase("xx"))?" and xxsh='通过' ":" and xysh='通过' and xydm='"+ userDep+ "' ");			
		}
		sql = "select "+zd+" from "+ tableName  + querry;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if(tableName == null){
			Excel2Oracle.exportData(sql, realTable, response.getOutputStream());
		} else {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}
		return mapping.findForward("success");
	}   
	private ActionForward sztzxfsbAutochk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//自动审核
		DAO dao = DAO.getInstance();
		String sql = "";
		SztzForm autoChk = (SztzForm) form;
		StringBuffer querry = new StringBuffer();
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);  	
		String bmdm = autoChk.getXydm();
		String nj = autoChk.getNj();
		String zydm = autoChk.getZydm();
		String bjdm = autoChk.getBjdm();
		String xh = autoChk.getXh();
		String xn = autoChk.getXn();
		String xq = autoChk.getXq();
		String xxdm = dao.getXxdm();
		boolean res = false; 
		if (userType.equalsIgnoreCase("xy")) {
			bmdm = userDep;
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){
			querry.append(" where 1=1 ");
			querry.append(Base.isNull(nj)?" and 1=1 ":" and nj='"+nj+"' ");
			querry.append(Base.isNull(xn)?" and 1=1 ":" and xn='"+xn+"' ");
			querry.append(Base.isNull(xq)?" and 1=1 ":" and xq='"+xq+"' ");
			querry.append(Base.isNull(xh)?" and 1=1 ":" and xh='"+xh+"' ");
			querry.append(Base.isNull(bmdm)?" and 1=1 ":" and xydm='"+bmdm+"' ");
			querry.append(Base.isNull(zydm)?" and 1=1 ":" and zydm='"+zydm+"' ");
			querry.append(Base.isNull(bjdm)?" and 1=1 ":" and bjdm='"+bjdm+"' ");
			sql = "select xn||nd||xq||xmdm||xh pkValue from view_sztzxfsbxx "+querry;
			String[] sqlTem = dao.getRs(sql,new String[]{}, "pkValue");
			if(sqlTem!=null){
				for(int i=0;i<sqlTem.length;i++){
					if (userType.equalsIgnoreCase("xx")) {
						sql = "update sztz_xfsbb set xxsh=? where xn||nd||xq||tzxmdm||xh=?";
					} else {
						sql = "update sztz_xfsbb set xysh=? where xn||nd||xq||tzxmdm||xh=?";
					}
					res = dao.runUpdate(sql, new String[] {"通过",sqlTem[i]});
					if(userType.equalsIgnoreCase("xx")){
						if(res){
							sql = "delete from sztz_xscjxxb where xn||nd||xq||xmdm||xh=?";
							res = dao.runUpdate(sql, new String[] {sqlTem[i]});    	 	   		    
							if (res) {
								String[] sbXx = dao.getOneRs("select xh,xn,xq,nd,xmdm,kmdm,jbdm,xf from view_sztzxfsbxx where xn||nd||xq||xmdm||xh=?",new String[]{sqlTem[i]}, new String[]{"xh","xn","xq","nd","xmdm","kmdm","jbdm","xf"});
								sql = "insert into sztz_xscjxxb(xh,xn,xq,nd,xmdm,kmdm,hjjb,xf) values( ?,?,?,?,?,?,?,?)";
								res = dao.runUpdate(sql, new String[] {sbXx[0],sbXx[1],sbXx[2],sbXx[3],sbXx[4],sbXx[5],sbXx[6],sbXx[7]}); 						   
							}
						}

					}
				}
			}
		}else{   		   	
			sql = "{call SZTZXFSBAUTOCHK(?,?,?,?,?)}";
			res = dao.runProcuder(sql, new String[] { userType,nj,bmdm, zydm, bjdm});   	
		}
		if (res) {
			request.setAttribute("autoChk", "ok");
		} else {
			request.setAttribute("autoChk", "no");
		}
		return mapping.findForward("success");
	}
	private ActionForward SavaSztzHd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne) throws Exception{
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String xmdm = request.getParameter("xmdm");
		String xmmc = DealString.toGBK(request.getParameter("xmmc"));
		String kmdm = request.getParameter("kmdm");
		String bmdm = request.getParameter("bmdm");
		String xsdw = DealString.toGBK(request.getParameter("xsdw"));
		String xf = request.getParameter("xf");
		String xmms = DealString.toGBK(request.getParameter("xmms"));
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String sqkssj = request.getParameter("sqkssj");
		String sqjssj = request.getParameter("sqjssj");

		String[] my_lydm = new String[10];
		String[] my_lymc = new String[10];
		String[] my_lynr = new String[10];

		String[] my_lbdm = new String[10];
		String[] my_lbmc = new String[10];
		String[] my_lbxf = new String[10];

		for(int i=0;i<10;i++){//获取申报页面理由、奖项类别相关参数
			int j = i+1;
			my_lydm[i] = request.getParameter("lydm"+j);
			my_lymc[i] = DealString.toGBK(request.getParameter("lymc"+j));
			my_lynr[i] = DealString.toGBK(request.getParameter("lynr"+j));
			my_lbdm[i] = request.getParameter("lbdm"+j);
			my_lbmc[i] = DealString.toGBK(request.getParameter("lbmc"+j));
			my_lbxf[i] = request.getParameter("lbxf"+j);    	   		
		}
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		if("xy".equalsIgnoreCase(userType)){
			bmdm = userDep ;
		}
		String sql = "";
//		boolean del = false;
		boolean inset = false;
		String tag_sb = "";
		String tag_zs = "";
//		String []colList = {"xmdm","xmmc","kmdm","bmdm","xsdw","xf","xmms","xn","xq"};
		sql = "select * from sztz_xmdmb where xmdm=?";
		tag_zs = dao.returntag(sql, new String[]{xmdm});
		sql = "select * from sztz_xmsbxxb where xmdm=?";
		tag_sb = dao.returntag(sql, new String[]{xmdm});

		if("modi".equalsIgnoreCase(doType)){
			sql = "select * from sztz_xmsbxxb where xmdm=? and xxsh='通过'";
			String tag = dao.returntag(sql, new String[]{xmdm});

			HashMap<String,String> map = new HashMap<String,String>();

			sql = "select kmdm,kmm from sztz_kmdmb";
			List kmdmList = dao.getList(sql, new String[] {}, new String[] {
					"kmdm", "kmm" });
			sql = "select  bmdm,bmmc from zxbz_xxbmdm";
			List	bmList = dao.getList(sql, new String[] {}, new String[] {
					"bmdm", "bmmc" });
			if("xy".equalsIgnoreCase(userType)){
				map.put("bmdm", userDep);
			}
			request.setAttribute("xnList",Base.getXnndList());
			request.setAttribute("xqList",Base.getXqList());
			request.setAttribute("userType1", userType);
			request.setAttribute("doType", doType);
			request.setAttribute("kmdmList", kmdmList);
			request.setAttribute("bmList", bmList);
			request.setAttribute("rs", map);

			if("empty".equalsIgnoreCase(tag)){
				StringBuffer sqlStr = new StringBuffer();
				sqlStr.append("delete sztz_xmsbxxb where xmdm='"+xmdm+"'").append("!!");
				sqlStr.append("delete from sztz_lysqxxb where xmdm='"+xmdm+"'").append("!!");
				sqlStr.append("delete from sztz_hjjbsqxxb where xmdm='"+xmdm+"'").append("!!");
				sqlStr.append("insert into sztz_xmsbxxb(xmdm,xmmc,kmdm,bmdm,xmms,xsdw,xf,xn,xq,sqkssj,sqjssj)values('"+xmdm+"','"+xmmc+"','"+kmdm+"','"+bmdm+"','"+xmms+"','"+xsdw+"','"+xf+"','"+xn+"','"+xq+"','"+sqkssj+"','"+sqjssj+"')").append("!!");    			
				for(int i=0;i<10;i++){
					if(!my_lydm[i].equalsIgnoreCase("")){
						sqlStr.append("insert into sztz_lysqxxb(lydm,lymc,xmdm,lynr)values('"+my_lydm[i]+"','"+my_lymc[i]+"','"+xmdm+"','"+my_lynr[i]+"')").append("!!");
					}
					if(!my_lbdm[i].equalsIgnoreCase("")){
						sqlStr.append("insert into sztz_hjjbsqxxb(jbdm,jbmc,xmdm,xf)values('"+my_lbdm[i]+"','"+my_lbmc[i]+"','"+xmdm+"','"+my_lbxf[i]+"')").append("!!");
					}
				}
				String[] sqlArr = sqlStr.toString().split("!!");
				int[] array = dao.runBatch(sqlArr);
				boolean doFlag = dao.checkBatch(array);
				if(doFlag){
					request.setAttribute("isEXIST", "ok");
				}else{
					request.setAttribute("isEXIST", "no");
				}
				return new ActionForward("/sztz/tzhd_sb_modi.jsp",false);
			}else{
				request.setAttribute("isEXIST", "ischeck");
				return new ActionForward("/sztz/tzhd_sb_modi.jsp",false);
			}
		}else{
			if("empty".equalsIgnoreCase(tag_zs) && "empty".equalsIgnoreCase(tag_sb)){
				StringBuffer sqlStr = new StringBuffer();
				sqlStr.append("insert into sztz_xmsbxxb(xmdm,xmmc,kmdm,bmdm,xsdw,xf,xmms,xn,xq,sqkssj,sqjssj)values('"+xmdm+"','"+xmmc+"','"+kmdm+"','"+bmdm+"','"+xsdw+"','"+xf+"','"+xmms+"','"+xn+"','"+xq+"','"+sqkssj+"','"+sqjssj+"')").append("!!");
				//inset = StandardOperation.insert("sztz_xmsbxxb", colList, new String[]{xmdm,xmmc,kmdm,bmdm,xsdw,xf,xmms,xn,xq},request);
				//if(inset){
				for(int i=0;i<10;i++){
					if(!my_lydm[i].equalsIgnoreCase("")){
						sqlStr.append("insert into sztz_lysqxxb(lydm,lymc,xmdm,lynr) values('"+my_lydm[i]+"','"+my_lymc[i]+"','"+xmdm+"','"+my_lynr[i]+"' )").append("!!");
						//StandardOperation.insert("sztz_lysqxxb", new String[]{"lydm","lymc","xmdm","lynr"}, new String[]{my_lydm[i],my_lymc[i],xmdm,my_lynr[i]},request);
					}
					if(!my_lbdm[i].equalsIgnoreCase("")){
						sqlStr.append("insert into sztz_hjjbsqxxb(jbdm,jbmc,xmdm,xf)values('"+my_lbdm[i]+"','"+my_lbmc[i]+"','"+xmdm+"','"+my_lbxf[i]+"')").append("!!");
						//StandardOperation.insert("sztz_hjjbsqxxb", new String[]{"jbdm","jbmc","xmdm","xf"}, new String[]{my_lbdm[i],my_lbmc[i],xmdm,my_lbxf[i]},request);
					}
				}	
				String[] sqlArr = sqlStr.toString().split("!!");
				int[] array = dao.runBatch(sqlArr);
				inset = dao.checkBatch(array);
				//}
			}else{
				HashMap<String,String> map = new HashMap<String,String>();   		
				map.put("xmdm", xmdm);
				map.put("xmmc", xmmc);
				map.put("kmdm", kmdm);
				map.put("bmdm", bmdm);
				map.put("xsdw", xsdw);
				map.put("xf", xf);
				map.put("xmms", xmms);
				sql = "select kmdm,kmm from sztz_kmdmb";
				List kmdmList = dao.getList(sql, new String[] {}, new String[] {
						"kmdm", "kmm" });
				sql = "select  bmdm,bmmc from zxbz_xxbmdm";
				List bmList = dao.getList(sql, new String[] {}, new String[] {
						"bmdm", "bmmc"});
				if("xy".equalsIgnoreCase(userType)){
					map.put("bmdm", userDep);
				}
				request.setAttribute("userType1", userType);
				request.setAttribute("doType", doType);
				request.setAttribute("kmdmList", kmdmList);
				request.setAttribute("bmList", bmList);
				request.setAttribute("rs", map);
				request.setAttribute("isEXIST", "is");
				return new ActionForward("/sztz/tzhd_sb.jsp",false);
			}
		}

		if(inset){
			request.setAttribute("isEXIST", "ok");
		}else{
			request.setAttribute("isEXIST", "no");
		}    	
		return mapping.findForward("success");	  	
	}
	private ActionForward SztzHdsbxxCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		DAO dao = DAO.getInstance();
		SztzForm hdsbForm = (SztzForm) form; 
//		String xxdm = dao.getXxdm();
		String tableName = "view_sztz_sbxx";
		String realTable = "sztz_xmsbxxb";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");//sql条件
		String sql = "";
		String []colList = {"bgcolor","行号","xmdm","xn","xq","xmmc","kmm","bmmc","sbsj","xf","xxsh"};
		String []CNcolList = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, CNcolList);
		String rsNum = "";
		Vector<Object> rs = new Vector<Object>();
		String xydm = request.getParameter("xydm");
		String kmdm = request.getParameter("kmdm");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String act = DealString.toString(request.getParameter("act"));
		if(xn==null){//默认学年学期
			hdsbForm.setXn(Base.currXn);
		}
		if(xq==null){
			hdsbForm.setXq(Base.currXq);
		}
		if("".equalsIgnoreCase(act) || act == null){
			request.setAttribute("tips", "素质拓展 - 拓展项目申报 - 查询");   		
		}else if("check".equals(act)){
			request.setAttribute("tips", "素质拓展 - 拓展项目审核 - 审核");
			request.setAttribute("check", "yes");
		}
		sql = "select distinct kmdm,kmm from sztz_kmdmb";
		List kmdmList = dao.getList(sql, new String[]{}, new String[]{"kmdm","kmm"});
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			hdsbForm.setXydm(userDep);
//			request.setAttribute("writeAble1", "yes");
		}
		//查询条件
		querry.append(((kmdm == null) || kmdm.equalsIgnoreCase(""))?"and 1=1 ":"and kmdm = '" + kmdm + "' ");
		querry.append(((xydm == null) || xydm.equalsIgnoreCase(""))?"and 1=1 ":"and bmdm = '" + xydm + "' ");
		querry.append(((xn == null) || xn.equalsIgnoreCase(""))?"and 1=1 ":"and xn = '" + xn + "' ");
		querry.append(((xq == null) || xq.equalsIgnoreCase(""))?"and 1=1 ":"and xq = '" + xq + "' ");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {

			sql = "select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,rownum 行号," 
				+ "a.*  from ( select a.*  from " 
				+ tableName 
				+ " a "+querry
				+ " order by xxsh desc ,sbsj desc)a ";
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("kmdmList", kmdmList);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("userType1", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("rs", rs);
		request.setAttribute("act",act);
		request.setAttribute("writeAble",(Base.chkUPower(sUName,"sztz_hd_cx.do", userType.equalsIgnoreCase("student"))==1)?"yes":"no");		
		return mapping.findForward("success");
	}
	private ActionForward Checktzhd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne) throws Exception{
		DAO dao = DAO.getInstance();
		String tableName = "view_sztz_sbxx";
		String realTable = "sztz_xmsbxxb";
		String doType = request.getParameter("doType");
		String xmdm = request.getParameter("xmdm");   	
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		String sql = "";
		boolean inset = false;    	
		String tag_zs = "";
		sql = "select * from sztz_xmdmb where xmdm=?";
		tag_zs = dao.returntag(sql, new String[]{xmdm});

		if("save".equalsIgnoreCase(doType)){
			inset = StandardOperation.update(realTable, new String[]{"xxsh"}, new String[]{yesNo}, "xmdm", xmdm,request);
			if("empty".equalsIgnoreCase(tag_zs)){    			    			
				if("通过".equalsIgnoreCase(yesNo)||"已通过".equalsIgnoreCase(yesNo)){
					if(inset){
						sql="insert into sztz_xmdmb(XMDM,XMMC,KMDM,ZZBMDM,XSDW,XF,XN,XQ,SQKSSJ,SQJSSJ) select XMDM,XMMC,KMDM,BMDM,XSDW,XF,XN,XQ,SQKSSJ,SQJSSJ from " +
						realTable+" where xmdm = ?";
						boolean insertdm = dao.runUpdate(sql,new String[]{xmdm}); 
						if(insertdm){
							sql = "insert into sztz_sqsblyb (lydm,lymc,xmdm,lynr) select lydm,lymc,xmdm,lynr from sztz_lysqxxb where xmdm = ?";
							dao.runUpdate(sql,new String[]{xmdm});
							sql = "insert into sztz_hjjbdmb (jbdm,jbmc,xmdm,xf) select jbdm,jbmc,xmdm,xf from sztz_hjjbsqxxb where xmdm = ?";
							dao.runUpdate(sql,new String[]{xmdm});
						}
					}
				}
			}else{
				if("不通过".equalsIgnoreCase(yesNo)
						||"未审核".equalsIgnoreCase(yesNo)){
					if(inset){
						sql = "delete from sztz_xmdmb where xmdm=?";
						boolean insertdm = dao.runUpdate(sql,new String[]{xmdm});
						if(insertdm){
							sql = "delete from sztz_sqsblyb where xmdm = ?";
							dao.runUpdate(sql,new String[]{xmdm});
							sql = "delete from sztz_hjjbdmb where xmdm = ?";
							dao.runUpdate(sql,new String[]{xmdm});
						}
					}
				}
			}
			if(inset){
				request.setAttribute("isEXIST", "ok");
			}else{
				request.setAttribute("isEXIST", "no");
			}
		} 		
		String rslyNum = "";
		String rslbNum = "";
		Vector<Object> rsly = new Vector<Object>();
		Vector<Object> rslb = new Vector<Object>();
		sql = "select rownum 行号,lymc,lynr from sztz_lysqxxb where xmdm =?";
		rsly.addAll(dao.rsToVator(sql, new String[] {xmdm}, new String[]{"行号","lymc","lynr"}));
		if (rsly == null) {
			rslyNum = "0";
		} else {
			rslyNum = String.valueOf(rsly.size());
		}

		sql = "select rownum 行号,jbmc,xf from sztz_hjjbsqxxb where xmdm =?";
		rslb.addAll(dao.rsToVator(sql, new String[] {xmdm}, new String[]{"行号","jbmc","xf"}));
		if (rslb == null) {
			rslbNum = "0";
		} else {
			rslbNum = String.valueOf(rslb.size());
		}

		sql="select xmdm,xmmc,kmdm,kmm,bmdm,bmmc,xsdw,xxsh yesNo,xf,xmms,xn,xq,sqkssj,sqjssj from " + tableName+ " where xmdm='"+xmdm+"' "; 	
		String[] collist = {"xmdm","xmmc","kmdm","kmm","bmdm","bmmc","xsdw","yesNo","xf","xmms","xn","xq","sqkssj","sqjssj"};   		
		HashMap<String,String> rsvalue = dao.getMap(sql, new String []{}, collist);

		request.setAttribute("rslbNum",rslbNum);
		request.setAttribute("rslyNum", rslyNum);
		request.setAttribute("rsly",rsly );
		request.setAttribute("rslb",rslb);
		request.setAttribute("rs", rsvalue);
		request.setAttribute("chkList", dao.getChkList(3));
		return mapping.findForward("success");
	}
	private ActionForward tzhdView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne) throws Exception{
		DAO dao = DAO.getInstance();
		String tableName = "view_sztz_sbxx";
		String xmdm = request.getParameter("xmdm");   	
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		String sql = "";
		sql = "select * from sztz_xmdmb where xmdm=?";
		@SuppressWarnings("unused")
		String tag_zs = dao.returntag(sql, new String[]{xmdm});

		String rslyNum = "";
		String rslbNum = "";
		Vector<Object> rsly = new Vector<Object>();
		Vector<Object> rslb = new Vector<Object>();
		sql = "select rownum 行号,lymc,lynr from sztz_lysqxxb where xmdm =?";
		rsly.addAll(dao.rsToVator(sql, new String[] {xmdm}, new String[]{"行号","lymc","lynr"}));
		if (rsly == null) {
			rslyNum = "0";
		} else {
			rslyNum = String.valueOf(rsly.size());
		}

		sql = "select rownum 行号,jbmc,xf from sztz_hjjbsqxxb where xmdm =?";
		rslb.addAll(dao.rsToVator(sql, new String[] {xmdm}, new String[]{"行号","jbmc","xf"}));
		if (rslb == null) {
			rslbNum = "0";
		} else {
			rslbNum = String.valueOf(rslb.size());
		}

		sql="select xmdm,xmmc,kmdm,kmm,bmdm,bmmc,xsdw,xxsh yesNo,xf,xmms,xn,xq,sqkssj,sqjssj from " + tableName+ " where xmdm='"+xmdm+"' "; 	
		String[] collist = {"xmdm","xmmc","kmdm","kmm","bmdm","bmmc","xsdw","yesNo","xf","xmms","xn","xq","sqkssj","sqjssj"};   		
		HashMap<String,String> rsvalue = dao.getMap(sql, new String []{}, collist);

		request.setAttribute("rslbNum",rslbNum);
		request.setAttribute("rslyNum", rslyNum);
		request.setAttribute("rsly",rsly );
		request.setAttribute("rslb",rslb);
		request.setAttribute("rs", rsvalue);
		return mapping.findForward("success");
	}
//	获取列表信息
	private static void getListxx(HttpServletRequest request, DAO dao, String xydm, String zydm, String nj) {

		
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
		if(request.getSession().getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			
			request.setAttribute("bjList", Fdypd.getFdybjList(request.getSession().getAttribute("userName").toString()));
			request.setAttribute("bjFdy", "yes");
		}	
				
	}
	public ActionForward sztzxmquery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne) throws Exception{
		DAO dao = DAO.getInstance();
		SztzForm myForm = (SztzForm) form;
		String tips = "素质拓展 - 拓展项目信息 - 查询";
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String url = request.getParameter("url");
		String xmmc = DealString.toGBK(myForm.getXmmc());
		myForm.setXmmc(xmmc);
		String kmdm = myForm.getKmdm();
		String xh = request.getParameter("xh");
		String sql = "";
		boolean xnxqNos = false;
		if(xn==null){
			myForm.setXn(Base.currXn);
		}

		request.setAttribute("xnxqNos",xnxqNos);
		StringBuffer querry = new StringBuffer();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String pk = " xmdm ";
		String tableName = "view_sztz_xmdmb";
		String rsNum = "";

		querry.append(" where 1=1 ");
		querry.append((Base.isNull(xn)) ? " and 1=1" : " and xn ='" + xn+ "' ");
		querry.append((Base.isNull(xq)) ? " and 1=1" : " and xq ='" + xq+ "' ");
		querry.append((Base.isNull(xmmc)) ? " and 1=1" : " and xmmc like '%"+ xmmc + "%' ");
		querry.append((Base.isNull(kmdm)) ? " and 1=1" : " and  (select kmdm from sztz_xmdmb b where a.xmdm=b.xmdm) ='" + kmdm+ "' ");				

		colList = new String[] { "行号","主键","xmdm","xn", "xq","xmmc","kmdm","sqkssj","sqjssj"};
		sql = "select * from (select * from( select rownum 行号,"
			+ pk
			+ " 主键,rownum r,a.xmdm,a.xn,(select xqmc from xqdzb c where c.xqdm=a.xq)xq,a.xmmc,kmdm,sqkssj,sqjssj from "
			+ tableName
			+ " a"
			+ querry
			+ ") where rownum<="
			+ (myForm.getPages().getStart() + myForm.getPages().getPageSize()) + ") where r>"
			+ myForm.getPages().getStart();
		// 分页
		myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName+ " a" + querry, new String[] {},"count")));
		colListCN = dao.getColumnNameCN(colList, tableName);
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
		List kmList = dao.getList("select kmdm,kmm from sztz_kmdmb",new String[] {}, new String[] { "kmdm", "kmm" });
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("kmList", kmList);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("url", DealString.toString(url));
		request.setAttribute("xh", xh);
		return mapping.findForward("success");
	}
	public ActionForward xftjManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse resposne){
		// 数据查询
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		HttpSession session = request.getSession();
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();	
		boolean disabled = true;
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		StringBuffer sql = new StringBuffer();// sql语句
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");// sql条件
		String rsNum = "0";// 返回的记录数
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String doPrint = request.getParameter("doPrint");

		if(userSpecType.equalsIgnoreCase("xy")){
			xy = userDep;
			dataSearchForm.setXydm(xy);
		}
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		//查询条件
		querry = querry.append(((nj == null) || nj.equalsIgnoreCase(""))?"and 1=1 ":"and nj = '" + nj + "' ");
		querry = querry.append(((xy == null) || xy.equalsIgnoreCase(""))?"and 1=1 ":"and xydm = '" + xy + "' ");
		querry = querry.append(((zy == null) || zy.equalsIgnoreCase(""))?"and 1=1 ":"and zydm = '" + zy + "' ");
		querry = querry.append(((bj == null) || bj.equalsIgnoreCase(""))?"and 1=1 ":"and a.bjdm = '" + bj + "' ");				

		sql.append("select rownum r, nj,xydm,zydm,(select bmmc from zxbz_xxbmdm e where e.bmdm=a.xydm )xymc,a.bjdm,(select bjmc from view_njxyzybj e where e.bjdm=a.bjdm )bjmc, ") ;
        sql.append("nvl(cout,0)cout,(nvl(coutbl,0)*100)||'%' bl from (select nj,xydm,zydm,bjdm from view_xsjbxx group by nj,xydm,zydm,bjdm order by bjdm   )a left join ( ");
        sql.append("select bjdm,count(xh)cout,to_char(nvl(count(xh),0)/(select count(xh)from view_xsjbxx e where e.bjdm=d.bjdm),'9999.99')coutbl from ( ");
        sql.append("select kmdm,bjdm,xh,sum(xf)kmzxf from view_sztzcj  group by kmdm,bjdm,xh) d where kmzxf >(select hgfs from sztz_kmdmb e where e.kmdm=d.kmdm) ");
        sql.append("group by bjdm) b on a.bjdm=b.bjdm");
		colList = new String[] { "r", "xymc", "bjmc", "cout", "bl"};		
		colListCN = new String[] { "序号", "系部名称", "班级", "符合人数", "完成比例"};
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql+querry.toString(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("disabled", disabled);
		getListxx(request,dao,xy,zy,nj);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数	
		if("print".equalsIgnoreCase(doPrint)){
			request.setAttribute("nj",nj);
			request.setAttribute("xymc", dao.getOneRs("select  bmmc from zxbz_xxbmdm where bmdm=? and rownum=1",new String[]{xy},"bmmc"));
			request.setAttribute("zymc", dao.getOneRs("select  zymc from view_njxyzybj where zydm=? and rownum=1",new String[]{zy},"zymc"));
			request.setAttribute("bjmc", dao.getOneRs("select  bjmc from view_njxyzybj where bjdm=? and rownum=1",new String[]{bj},"bjmc"));
			return new ActionForward("/sztz/xftjPrint.jsp", false);
		}else{
		    return mapping.findForward("success");
		}
	}
}