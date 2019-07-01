/**
 * ������2007-11-26����14:30
 * Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.sztz.xnmz;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.sztz.dao.SztzDao;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.sztz.form.SztzForm;

/**
 * @author lp
 * Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class SztzAction extends Action{
	
	private static boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase(""));
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	    SztzForm myform = (SztzForm)form;
		HttpSession session = request.getSession();
		String writeAble = "";
		String userName = "";
		String userType = "";
//		DAO dao = DAO.getInstance();
		try{
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myform.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}						
			if (session == null) {
				request.setAttribute("errMsg", "��������");
				return mapping.findForward("false");
			} 
			
			userName = session.getAttribute("userName").toString();
			userType = session.getAttribute("userOnLine").toString();
	        String act = request.getParameter("act");
			boolean isStu = userType.equalsIgnoreCase("student");
			int p = 0;
			ActionForward myActFwd = null;
			String mypam = mapping.getParameter();
			if(mypam.equalsIgnoreCase("para_set")){
				p = Base.chkUPower(userName, "para_set.do", isStu);
//				if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_XNMZ)){
					myActFwd = paraSet(mapping,form,request,response);
//				}else{
//					myActFwd = new ActionForward("/csmz_sztz.do?method=param_set",false);
//				}	
			   	if (isStu) { 		
		    		request.setAttribute("errMsg", "ѧ����Ȩ���ʴ�ҳ�棡");
		    		return new ActionForward("/errMsg.do", false); 
		        }
		   }else if(mypam.equalsIgnoreCase("tzbj_pub")){
				p = Base.chkUPower(userName, "tzbj_pub.do", isStu);
				myActFwd = tzbj_pub(mapping,form,request,response);
			}else if(mypam.equalsIgnoreCase("tz_dataSearch")){
				if(act.equalsIgnoreCase("bjxx")){
					p = Base.chkUPower(userName, "tz_dataSearch.do?act=bjxx", isStu);
				}else if(act.equalsIgnoreCase("bjsq")){
					p = Base.chkUPower(userName, "tz_dataSearch.do?act=bjsq", isStu);
				}else if(act.equalsIgnoreCase("bjsh")){
					p = Base.chkUPower(userName, "tz_dataSearch.do?act=bjsh", isStu);
				}else if(act.equalsIgnoreCase("bjjy")){
					p = Base.chkUPower(userName, "tz_dataSearch.do?act=bjjy", isStu);
				}
				myActFwd = dataSearch(mapping,form,request,response);
			}else if(mypam.equalsIgnoreCase("tzbj_sq")){				
				myActFwd = tzbj_sq(mapping,form,request,response);
			}else if(mypam.equalsIgnoreCase("tzbj_sh")){				
				myActFwd = tzbj_sh(mapping,form,request,response);
			}else if(mypam.equalsIgnoreCase("tzbj_jy")){				
				myActFwd = tzbj_jy(mapping,form,request,response);
			}
			writeAble = (p == 1) ? "yes" : "no";
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		}catch(Exception e){
			e.printStackTrace();
			myform.setErrMsg("���������жϣ������µ�½��");
	    	return new ActionForward("/login.jsp", false);
		}
	}
	//���ݲ�ѯ����ģ��
	private ActionForward dataSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		DAO dao = DAO.getInstance();
		SztzForm dsForm = (SztzForm)form;
		HttpSession session = request.getSession();
		SztzDao myDao = new SztzDao();
		StringBuffer querry = new StringBuffer();
		String act = request.getParameter("act");
		String userStType = session.getAttribute("userOnLine").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String pk = "";
		Vector<Object> rs = new Vector<Object>();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "";
		String sql = "";
		String tips = "";		
		if(userStType.equalsIgnoreCase("student")){
//			if(act.equalsIgnoreCase("bjxx")){
			String xh = session.getAttribute("userName").toString();
			colList = new String[] {"�к�", "����", "xn", "xq", "mc", "sqsj", "fdysh",
					"xysh", "xxsh"};		    
			colListCN = dao.getColumnNameCN(colList, "view_sztz_bjsqxx");
			sql = "select rownum �к�, xh||xn||xq||dm ����, a.* from view_sztz_bjsqxx a where xh=?";
			List topTr = dao.arrayToList(colList, colListCN);

			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("num", String.valueOf(rs.size()));	
			return new ActionForward("/sztz/xnmz/tzbjSq_result.jsp",false);
		}
		sql = "select * from xnmz_sztz_bjsqsjb where rownum=1";
		String[] tem = dao.getOneRs(sql, new String[]{}, new String[]{"xn","xq"});    
	    String szXn = "";	
	    String szXq = "";
		if(tem!=null&&tem.length==2){//Ĭ�ϲ������ñ��е�ѧ�ꡢѧ��
			szXn = tem[0];
			szXq = tem[1];
		}
		if(isNull(dsForm.getXn())){
			dsForm.setXn(szXn);		
		}
		if(isNull(dsForm.getXq())){
			dsForm.setXq(szXq);				
		}
		
		boolean isSH = false;//��˱�־
		String nj = dsForm.getNj();
		String xn = dsForm.getXn();
		String xq = dsForm.getXq();
		String xydm = dsForm.getXydm();
		String zydm = dsForm.getZydm();
		String bjdm = dsForm.getBjdm();
		String dm = dsForm.getDm();//������չ�༶����
		String xh = dsForm.getXh();
		String xm = DealString.toGBK(dsForm.getXm());
				
		//��ѯ����		
		querry.append(" where 1=1 ");		
		querry.append((isNull(nj))? " and 1=1 " : " and nj = '"+nj+"'");
		querry.append((isNull(xn))? " and 1=1 " : " and xn = '"+xn+"'");
		querry.append((isNull(xq))? " and 1=1 " : " and xq = '"+xq+"'");
		querry.append((isNull(xh))? " and 1=1 " : " and xh = '"+xh+"'");
		querry.append((isNull(xm))? " and 1=1 " : " and xm like '%"+xm+"%'");
		querry.append((isNull(xydm))? " and 1=1 " : " and xydm = '"+xydm+"'");
		querry.append((isNull(zydm))? " and 1=1 " : " and zydm = '"+zydm+"'");
		querry.append((isNull(bjdm))? " and 1=1 " : " and bjdm = '"+bjdm+"'");
		querry.append((isNull(dm)? " and 1=1 " : " and dm = '"+dm+"'"));
		
		
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
			dsForm.setXydm(userDep);
		}
		if(act.equalsIgnoreCase("bjxx")){
			realTable = "sztz_bjfbb";
			tableName = "view_sztzBjFbxx";
			pk = "xn||xq||dm";
			tips = "������չ - ��Ϣ���� - �༶��Ϣ����";
			colList = new String[]{"�к�","����","nd","xn","xqmc","mc","kssj","jssj"};
		}else if(act.equalsIgnoreCase("bjsq")){
			realTable = "sztz_bjxssqb";
			tableName = "view_sztz_bjsqxx";
			pk = "xh||xn||xq||dm";
			tips = "������չ - ���� - ��������ѯ";
			colList = new String[]{"�к�","����","xh","xm","xn","xqmc","mc","fdysh","xysh","xxsh"};	
			request.setAttribute("isdisabled", "isdisabled");//����ѧ�ꡢѧ���������Ƿ�disabled��־
		}else if(act.equalsIgnoreCase("bjsh")){
			realTable = "sztz_bjxssqb";
			tableName = "view_sztz_bjsqxx";
			pk = "xh||xn||xq||dm";
			tips = "������չ - ��� - �༶�μ��������";
			colList = new String[] { "bgcolor","����", "xh","xm","xn","xqmc","mc","sqsj",""};
			isSH = true;
			request.setAttribute("isdisabled", "isdisabled");//����ѧ�ꡢѧ���������Ƿ�disabled��־
		}else if(act.equalsIgnoreCase("bjjy")){
			realTable = "sztz_bjxsjyxxb";
			tableName = "view_sztz_bjjyxx";
			pk = "xh||xn||xq||dm";
			tips = "������չ - ��Ϣά�� - ��չ�༶��ҵ��Ϣ";
			colList = new String[]{"�к�","����","xh","xm","xn","xqmc","mc","jysj","jyfs","sfjy"};				
		}
		if(isSH){			
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from (select * from(select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,rownum r,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xysh='ͨ��' order by xxsh desc) a ) where rownum<=" 
					+ (dsForm.getPages().getStart() + dsForm.getPages().getPageSize()) 
					+ ") where r>" + dsForm.getPages().getStart();
				colList[colList.length - 1] = "xxsh";
				dsForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry +" and xysh='ͨ��'", new String[]{}, "count")));
			} else {
				dsForm.setXydm(userDep);
				if (userBj.size() == 0) {
					sql = "select * from (select * from(select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,rownum r,a.* from "
						+ tableName
						+ " a"
						+ querry
						+ " and xydm='"
						+ userDep + "' and fdysh='ͨ��' order by xysh desc) a )where rownum<="
						+ (dsForm.getPages().getStart() + dsForm.getPages().getPageSize()) 
						+ ") where r>" + dsForm.getPages().getStart();
					colList[colList.length - 1] = "xysh";
					dsForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry +" and xydm = '"+ userDep +"' and fdysh='ͨ��'", new String[]{}, "count")));
				}else{
					sql = "select * from (select * from(select (case when nvl(a.fdysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,rownum r,a.* from "
						+ tableName
						+ " a"
						+ querry
						+ " and xydm='"
						+ userDep + "' order by xysh desc) a ) where rownum<="
						+ (dsForm.getPages().getStart() + dsForm.getPages().getPageSize()) 
						+ ") where r>" + dsForm.getPages().getStart();
					colList[colList.length - 1] = "fdysh";
					
					dsForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry + "and xydm = '"+ userDep +"'" , new String[]{}, "count")));
				}
			}
		}else{
			sql = "select * from (select * from( select rownum �к�," + pk + " ����,rownum r,a.* from " + tableName + " a" + querry + ") where rownum<="
			+ (dsForm.getPages().getStart() + dsForm.getPages().getPageSize()) 
			+ ") where r>" + dsForm.getPages().getStart();		
					
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
        //��ҳ
		dsForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry, new String[]{}, "count")));		

		List tzBjList = myDao.getTzBjList(xn, xq);
	    request.setAttribute("tzBjList",tzBjList);//����������չ�༶�б�
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				new String[] { "xxmc" })[0];
		getListxx(request,dao,xydm,zydm,nj);
		request.setAttribute("xxmc", xxmc);// ȡѧУ������Ϣ
		request.setAttribute("xxdm", StandardOperation.getXxdm());
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", act);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		request.setAttribute("isSH", isSH);
		if(realTable.equalsIgnoreCase("sztz_bjfbb")){
			return new ActionForward("/sztz/xnmz/tzbj_pubcx.jsp",false);
		}
		return mapping.findForward("success");
	}
	
	//��������
	private ActionForward paraSet(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		DAO dao = DAO.getInstance();
		SztzForm setForm = (SztzForm)form;
		String   xxdm    = dao.getXxdm();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String sql = "";
		String act = request.getParameter("act");
        if(act!=null&&act.equalsIgnoreCase("save")){//���桢�޸�
        	String xn = request.getParameter("xn");
        	String nd = request.getParameter("nd");
        	String xq = request.getParameter("xq");
        	String kssj = request.getParameter("kssqsj");
        	String jssj = request.getParameter("jssqsj");
        	boolean done = false;
        	
        	sql = "delete from xnmz_sztz_bjsqsjb";
        	done = dao.runUpdate(sql, new String[]{});
            if(done){
            	sql = "insert into xnmz_sztz_bjsqsjb(nd,xn,xq,kssj,jssj)values(?,?,?,?,?)";
            	done = dao.runUpdate(sql,new String[]{nd,xn,xq,kssj,jssj});               
            }
            if(done){
            	request.setAttribute("ok", "ok");
            	String logMsg = userName + "������������չ������ѧ��"+xn+"�����"+nd+"��ѧ��"+xq+"�����뿪ʼʱ��"+kssj+"���������ʱ��"+jssj;
    			dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);
             }else{
            	request.setAttribute("ok","no");
            }
        }
		sql = "select xn,nd,xq, strtodatetime(substr(kssj,1,8)) kssj1,"
			+ "substr(kssj,9,2) kssj2," + "substr(kssj,11,2) kssj3,"
			+ "substr(kssj,13,2) kssj4,"
			+ "strtodatetime(substr(jssj,1,8)) jssj1,"
			+ "substr(jssj,9,2) jssj2," + "substr(jssj,11,2) jssj3,"
			+ "substr(jssj,13,2) jssj4 from xnmz_sztz_bjsqsjb " + "where rownum=1";
		String[] colList = { "xn", "nd", "xq", "kssj1", "kssj2", "kssj3", "kssj4",
				"jssj1", "jssj2", "jssj3", "jssj4"};
		String[] sqsj = dao.getOneRs(sql, new String[] {}, colList);
		if (sqsj == null) {
			sqsj = new String[colList.length];
			for (int i = 0; i < sqsj.length; i++) {
				sqsj[i] = "";
			}
		}
		for (int i = 0; i < sqsj.length; i++) {
			request.setAttribute(colList[i], sqsj[i]);
		}
		if(sqsj==null){//���Ϊ��ȡϵͳ���ñ��е�����
			setForm.setXn(dao.getConf(0));
			setForm.setNd(dao.getConf(3));
			setForm.setXq(dao.getConf(1));
		}else{
			setForm.setXn(sqsj[0]);
			setForm.setNd(sqsj[1]);
			setForm.setXq(sqsj[2]);
	
		}
		if(Globals.XXDM_XNMZ.equalsIgnoreCase(xxdm)){
			request.setAttribute("XNMZ", "XNMZ");
		}
		request.setAttribute("xnndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());

		return mapping.findForward("success");
	}
	//��չ�༶��Ϣ����
	private ActionForward tzbj_pub(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String doType = DealString.toString(request.getParameter("doType"));
		String sql = "";
		String pk = " xn||xq||dm ";
		String pkValue = DealString.toString(request.getParameter("pkValue"));
		List tzBjList = null;
		HashMap<String,String> map = new HashMap<String,String>();
		boolean done = false;		
		if(doType.equalsIgnoreCase("del")){
			if(pkValue!=null||!pkValue.equalsIgnoreCase("")){
			    sql = "delete from sztz_bjfbb where "+pk+"=?";	
			    done = dao.runUpdate(sql,new String[]{pkValue});
			}
			    if(done){
					request.setAttribute("done", "ok");
					String logMsg = userName + "�ڱ�'sztz_bjfbb'��ɾ����ѧ�ꡢѧ�ڡ���չ�༶���루���ڱ�sztz_bjdmb�У�Ϊ"+pkValue+"�ļ�¼";
	    			dao = new DAO(request.getRemoteAddr());
	    			dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);
				}else{
					request.setAttribute("done", "no");
				}    
			return new ActionForward("/tz_dataSearch.do?act=bjxx&go=go",false);
		}else if(doType.equalsIgnoreCase("save")){//���桢�޸�
			String xn = request.getParameter("xn");
			String nd = request.getParameter("nd");
			String xq = request.getParameter("xq");
			String kssj = request.getParameter("kssj");
			String jssj = request.getParameter("jssj");
			String dm = request.getParameter("dm");
			String nr = DealString.toGBK(request.getParameter("nr"));
			String cz = "";
			if(!pkValue.equalsIgnoreCase("")){
			    sql = "delete from sztz_bjfbb where "+pk+"=?";	
			    done = dao.runUpdate(sql,new String[]{pkValue});
			    cz = "�޸�";
			}else{
				sql = "delete from sztz_bjfbb where xn=? and xq=? and dm=?";
				done = dao.runUpdate(sql,new String[]{xn,xq,dm});	
		        cz = "����";	
			}
			if(done){
				sql = "insert into sztz_bjfbb(nd,xn,xq,dm,kssj,jssj,nr)values(?,?,?,?,?,?,?)";
				done = dao.runUpdate(sql, new String[]{nd,xn,xq,dm,kssj,jssj,nr});
			}
			if(done){
				request.setAttribute("done", "ok");
            	String logMsg = userName + "�ڱ�'sztz_bjfbb'��"+cz+"��ѧ��Ϊ"+xn+"��ѧ��Ϊ"+xq+"����չ�༶���루���ڱ�sztz_bjdmb�У�Ϊ"+dm+"�ļ�¼";
    			dao = new DAO(request.getRemoteAddr());
    			dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);		
			}else{
				request.setAttribute("done", "no");
			}
		}else if(doType.equalsIgnoreCase("view")){
			sql = "select * from view_sztzBjFbxx where "+pk+"= ?";
			String [] colList = new String[]{"nd","xn","xqmc","mc","kssj","jssj","nr"};
			String[] tzbjXx = dao.getOneRs(sql,new String[]{pkValue},colList);
			for (int i = 0; i < tzbjXx.length; i++) {
				request.setAttribute(colList[i], tzbjXx[i]);
			}
			return new ActionForward("/sztz/xnmz/tzbjInfoView.jsp",false);
		}else if(doType.equalsIgnoreCase("modi")){
			sql = "select * from view_sztzBjFbxx where "+pk+"= ?";
			String [] colList = new String[]{"nd","xn","xq","dm","kssj","jssj","nr"};
			String[] tzbjXx = dao.getOneRs(sql,new String[]{pkValue},colList);
			for (int i = 0; i < tzbjXx.length; i++) {
				map.put(colList[i].toLowerCase(), tzbjXx[i]);
			}
			sql = "select dm,mc from sztz_bjdmb where xn='"+map.get("xn")+"' and xq='"+map.get("xq")+"'";
			tzBjList = dao.getList(sql,new String[]{}, new String[]{"dm","mc"});
		}
		if(!doType.equalsIgnoreCase("modi")){//���޸�����»�ȡ�������ñ�����ȡ�ѧ�ꡢѧ�ڼ���Ӧ����չ�༶�б�
			sql = "select nd,xn,xq from XNMZ_SZTZ_BJSQSJB where rownum=1";	
			String[] sjTem = dao.getOneRs(sql, new String[]{}, new String[]{"nd","xn","xq"});
			String tag = dao.returntag(sql, new String[]{});
			if ("empty".equalsIgnoreCase(tag)) {
				map.put("xn", "");
				map.put("nd", "");
				map.put("xueqi", "");
			}else{							
				map.put("nd",sjTem[0]);
				map.put("xn",sjTem[1]);
				map.put("xq",sjTem[2]);	
			}
			sql = "select dm,mc from sztz_bjdmb where xn='"+map.get("xn")+"' and xq='"+map.get("nd")+"'";
			tzBjList = dao.getList(sql,new String[]{}, new String[]{"dm","mc"});
		}
		request.setAttribute("rs",map);
		request.setAttribute("doType",doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("tzBjList",tzBjList);
		return mapping.findForward("success");
	}
	//��չ�༶����
	public ActionForward tzbj_sq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		DAO dao = DAO.getInstance();
		SztzForm myForm = (SztzForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String doType = DealString.toString(request.getParameter("doType"));
		String userType=session.getAttribute("userOnLine").toString();	
		String sql = "";
		String pkValue = DealString.toString(request.getParameter("pkValue"));
		String xh = myForm.getXh().trim();
		String dm = request.getParameter("dm");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd = request.getParameter("nd");
		String realTable = "sztz_bjxssqb";
		String tableName = "view_sztz_bjsqxx";
		String pk = "xh||xn||xq||dm";
		HashMap<String,String> map = new HashMap<String,String>();
		List tzBjList = null;
		boolean done = false;
		String tag = "";//���Ƿ�Ϊ�ձ�־
		String[] tem = null;
		//ѧ����Ϣ
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
		//��ð༶����ѧ�ꡢѧ���ڵİ༶List			
		sql = "select * from xnmz_sztz_bjsqsjb where rownum=1";		
		tem = dao.getOneRs(sql, new String[]{}, new String[]{"xn","xq"});
		tag = dao.returntag(sql, new String[]{});
		if("empty".equalsIgnoreCase(tag)){
		    tem = new String[]{"",""};	
		}
		sql = "select dm,mc from view_sztzbjfbxx where xn='"+tem[0]+"' and xq='"+tem[1]+"'";
		tzBjList = dao.getList(sql,new String[]{}, new String[]{"dm","mc"});
		request.setAttribute("tzBjList",tzBjList);
		
        //��ò������ñ�����(�����������ʱ�䷶Χ)
		sql = "select * from xnmz_sztz_bjsqsjb where to_char(sysdate,'yyyymmddhh24miss') between kssj and jssj";
		tag = dao.returntag(sql, new String[]{});
		tem = dao.getOneRs(sql, new String[]{}, new String[]{"nd","xn","xq"});
		if("empty".equalsIgnoreCase(tag)){
			if(doType.equalsIgnoreCase("modi")){//�޸Ĳ����²�����
				request.setAttribute("sqsjTag", "0");
			}else{
				request.setAttribute("sqsjTag", "1");	
			}
			map.put("nd", "");
			map.put("xn", "");
			map.put("xq", "");
		}else{			
			map.put("nd", tem[0]);
			map.put("xn", tem[1]);
			map.put("xq", tem[2]);			
		}
				
		if(doType.equalsIgnoreCase("del")){			
			if(pkValue!=null||!pkValue.equalsIgnoreCase("")){
			    sql = "delete from "+realTable+" where "+pk+"=?";	
			    done = dao.runUpdate(sql,new String[]{pkValue});
			}
			if(done){
				request.setAttribute("done", "ok");
				String logMsg = userName + "�ڱ�'"+realTable+"'��ɾ����ѧ�š�ѧ�ꡢѧ�ڡ���չ�༶���루���ڱ�sztz_bjdmb�У�Ϊ"+pkValue+"�ļ�¼";
				dao = new DAO(request.getRemoteAddr());
				dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);	
			}else{
				request.setAttribute("done", "no");
			} 
			return new ActionForward("/tz_dataSearch.do?act=bjsq&go=go&xh=",false);
		}else if(doType.equalsIgnoreCase("save")){
			String sqly = DealString.toGBK(request.getParameter("sqly"));
			String cz = "";
			if(!pkValue.equalsIgnoreCase("")){
			    sql = "delete from "+realTable+" where "+pk+"=?";	
			    done = dao.runUpdate(sql,new String[]{pkValue});
			    cz = "�޸�";
			}else{
				sql = "delete from "+realTable+" where xh=? and xn=? and xq=? and dm=?";
				done = dao.runUpdate(sql,new String[]{xh,xn,xq,dm});	
		        cz = "����";	
			}
		    if(done){
		    	sql = "insert into "+realTable+"(xh,nd,xn,xq,dm,sqly)values(?,?,?,?,?,?)";
		    	done = dao.runUpdate(sql, new String[]{xh,nd,xn,xq,dm,sqly});
		    }
		    if(done){
		    	request.setAttribute("done", "ok");
            	String logMsg = userName + "�ڱ�'sztz_bjxssqb'��"+cz+"��ѧ��Ϊ"+xh+"��ѧ��Ϊ"+xn+"��ѧ��Ϊ"+xq+"����չ�༶���루���ڱ�sztz_bjdmb�У�Ϊ"+dm+"�ļ�¼";
    			dao = new DAO(request.getRemoteAddr());
    			dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);		
			}else{
				request.setAttribute("done", "no");
			}
		}else if(doType.equalsIgnoreCase("view")){
			sql = "select * from "+tableName+" where "+pk+"= ?";
			colList = new String[]{"nd","xn","xqmc","mc","sqly","sqsj"};
			String[] tzbjSqXx = dao.getOneRs(sql,new String[]{pkValue},colList);
			for (int i = 0; i < tzbjSqXx.length; i++) {
				request.setAttribute(colList[i].toLowerCase(), tzbjSqXx[i]);
			}
			request.setAttribute("rs",map);
			return new ActionForward("/sztz/xnmz/tzbjSqInfoView.jsp",false);
		}else if(doType.equalsIgnoreCase("modi")){
			sql = "select * from "+tableName+" where "+pk+"= ?";
			colList = new String[]{"nd","xn","xq","dm","sqly"};
			String[] tzbjSqXx = dao.getOneRs(sql,new String[]{pkValue},colList);
			for (int i = 0; i < tzbjSqXx.length; i++) {
				map.put(colList[i].toLowerCase(), tzbjSqXx[i]);
			}
			String [] outputValue = new String[]{"kssj","jssj","nr"};						
			sql = "select kssj,jssj,nr from view_sztzbjfbxx where xn||xq||dm in (select xn||xq||dm from "+tableName+" where "+pk+" =? )";
			String[] rsvalue = dao.getOneRs(sql, new String[]{pkValue},outputValue);
			for (int i = 0; i < outputValue.length; i++) {
 		    	rsvalue[i] = ((rsvalue[i] == null) || rsvalue[i].equalsIgnoreCase("")) ? "asd "
 				: rsvalue[i];
 		    	map.put(outputValue[i], rsvalue[i].trim());
 		    }						
		}
        //�༶������Ϣ				
		if(dm==null||dm.equalsIgnoreCase("")){		
			if(!doType.equalsIgnoreCase("modi")){//�޸Ĳ��������
				map.put("kssj","");
				map.put("jssj","");
				map.put("nr", "");				
			}
		}else{
			String [] outputValue = new String[]{"kssj","jssj","nr"};
			sql = "select kssj,jssj,nr from view_sztzbjfbxx where xn=? and xq=? and dm=?";
			String[] rsvalue = dao.getOneRs(sql, new String[]{xn,xq,dm},outputValue);
			for (int i = 0; i < outputValue.length; i++) {
 		    	rsvalue[i] = ((rsvalue[i] == null) || rsvalue[i].equalsIgnoreCase("")) ? "asd "
 				: rsvalue[i];
 		    	map.put(outputValue[i], rsvalue[i].trim());
 		    }
			map.put("dm", dm);			
		}
		
		request.setAttribute("rs",map);
		request.setAttribute("realTable", realTable);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("success");
	}

	public ActionForward tzbj_sh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse reponse)throws Exception{
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		SztzForm myForm = (SztzForm)form;
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tableName = "view_sztz_bjsqxx";
		String realTable = "sztz_bjxssqb";
		String pk ="xh||xn||xq||dm";
		String[] colList = null;
		String tips = "";
		HashMap<String,String> map =new HashMap<String,String>();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String shType = "";
		String sql = "";
		if (userType.equalsIgnoreCase("xx")) {
			shType = "xxsh";			
		} else {
			if (userBj.size() == 0) {
				shType = "xysh";				
			}else{
				shType = "fdysh";				
			}
		}							
		if(doType.equalsIgnoreCase("save")){
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			String fdyyj = DealString.toGBK(request.getParameter("fdyyj"));
			String xyyj = DealString.toGBK(request.getParameter("xyyj"));
			String xxyj = DealString.toGBK(request.getParameter("xxyj"));
			sql = "update "+realTable+" set "+shType+" = '"+yesNo+"',fdyyj=?,xyyj=?,xxyj=? where "+pk+" = ?";
			boolean rel = false;
			rel = dao.runUpdate(sql, new String[]{fdyyj,xyyj,xxyj,pkValue});
			if(rel){
				request.setAttribute("done","yes");
				String logMsg = userName + "�ڱ�'sztz_bjxssqb'���޸���ѧ�š�ѧ�ꡢѧ�ڡ���չ�༶���루���ڱ�sztz_bjdmb�У�Ϊ"+pkValue+"��¼�ֶ�"+shType+"Ϊ"+yesNo+"";
    			dao = new DAO(request.getRemoteAddr());
    			dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);
     			if(yesNo.equalsIgnoreCase("ͨ��")&&shType.equalsIgnoreCase("xxsh")){
    				sql = "select * from sztz_bjxsjyxxb where "+pk+"=?";
    		    	String tag = dao.returntag(sql, new String[]{pkValue});
    				if("empty".equalsIgnoreCase(tag)){//ѧУ���ͨ�����Ѹ�����Ϣ�����ҵ��Ϣ���У�����ҵ��ά����ҵ�ɼ���
    					sql = "select xh,xn,xq,dm,jssj from view_sztz_bjsqxx where "+pk+"=?";
    					String[] relTem = dao.getOneRs(sql,new String[]{pkValue},new String[]{"xh","xn","xq","dm","jssj"});
    					sql = "insert into sztz_bjxsjyxxb(xh,xn,xq,dm,jysj)values(?,?,?,?,?)";
    					dao.runUpdate(sql,new String[]{relTem[0],relTem[1],relTem[2],relTem[3],relTem[4]} );
    				}
    			}
			}else{
				request.setAttribute("done", "no");
			}
		}
		
		tips = "������չ - ��� - �༶�μ�������� - �������";
		sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,mc,fdyyj,xyyj,xxyj,sqly,sqsj,"+shType+" yesNo from "+tableName+" where "+pk+" = '"+pkValue+"'";
		colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xqmc","mc","fdyyj","xyyj","xxyj","sqly","sqsj","yesNo"};

		String[] rsTem = dao.getOneRs(sql, new String[] {}, colList);
		for (int i = 0; i < colList.length; i++) {
			rsTem[i] = ((rsTem[i] == null) || rsTem[i].equalsIgnoreCase("")) ? " "
					: rsTem[i];
			map.put(colList[i].toLowerCase(), rsTem[i]);
		}
		myForm.setYesNo(rsTem[16]);
		request.setAttribute("userType", userType);
	    request.setAttribute("pkValue", pkValue);
	    request.setAttribute("chkList", dao.getChkList(3));
	    request.setAttribute("realTable", realTable);
	    request.setAttribute("tableName", tableName);
	    request.setAttribute("tips", tips);
	    request.setAttribute("rs", map);
	    request.setAttribute("pk", pk);
	    request.setAttribute("shType", shType);
		return mapping.findForward("success");
	}
	public ActionForward tzbj_jy(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		DAO dao = DAO.getInstance();
		SztzDao mydao = new SztzDao();
		SztzForm myForm = (SztzForm)form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String pk = "xh||xn||xq||dm";
		String pkValue = request.getParameter("pkValue");
		String realTable = "sztz_bjxsjyxxb";
		String tableName = "view_sztz_bjjyxx";
		String[] colList = null;
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = "";
		String xh = myForm.getXh().trim();
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		boolean done = false;
		String cz = "";
		sql = "select * from view_xsjbxx where xh=?";
		colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
		    for (int i = 0; i < colList.length; i++) {
			map.put(colList[i].toLowerCase(), rs[i]);
		    }
		}
		
		sql = "select xn,xq from xnmz_sztz_bjsqsjb where rownum=1";
		colList = new String[]{"xn","xq"};
		String[] tem = dao.getOneRs(sql, new String[]{}, colList);
		String tag = dao.returntag(sql, new String[]{});
		if("empty".equalsIgnoreCase(tag)){
		    tem = new String[]{"",""};	
		}
		for (int i = 0; i < colList.length; i++) {
			tem[i] = ((tem[i] == null) || tem[i].equalsIgnoreCase("")) ? " "
					: tem[i];
			map.put(colList[i].toLowerCase(), tem[i]);
		}
		if(doType.equalsIgnoreCase("del")){
			if(!pkValue.equalsIgnoreCase("")){
			    sql = "delete from "+realTable+" where "+pk+"=?";	
			    done = dao.runUpdate(sql,new String[]{pkValue});
			}
			if(done){
				request.setAttribute("done", "ok");
				String logMsg = userName + "�ڱ�'"+realTable+"'��ɾ����ѧ�š�ѧ�ꡢѧ�ڡ���չ�༶���루���ڱ�sztz_bjdmb�У�Ϊ"+pkValue+"�ļ�¼";
				dao = new DAO(request.getRemoteAddr());
				dao.writeLog(sql,new String[]{}, new String[]{}, logMsg,request);
			}else{
				request.setAttribute("done", "no");
			}    
			return new ActionForward("/tz_dataSearch.do?act=bjjy&go=go&xh=",false);
		}else if(doType.equalsIgnoreCase("view")){
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xqmc,dm,mc,jyfs,jysj,sfjy from "+tableName+" where "+pk+" =?";
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","xqmc","dm","mc","jyfs","jysj","sfjy"};
			String[] rsTem = dao.getOneRs(sql,new String[]{pkValue},colList);		
			for (int i = 0; i < colList.length; i++) {
				rsTem[i] = ((rsTem[i] == null) || rsTem[i].equalsIgnoreCase("")) ? " "
						: rsTem[i];
				map.put(colList[i].toLowerCase(), rsTem[i].trim());
			}
			request.setAttribute("rs", map);
			return new ActionForward("/sztz/xnmz/tzbjJyMoreInfo.jsp",false);
	    }else if(doType.equalsIgnoreCase("save")){
	    	String dm = request.getParameter("dm");
	    	String jyfs = request.getParameter("jyfs");
	    	String jysj = request.getParameter("jysj");
	    	String sfjy = DealString.toGBK(request.getParameter("SF"));
	    	if(!pkValue.equalsIgnoreCase("")){
			    sql = "delete from "+realTable+" where "+pk+"=?";	
			    done = dao.runUpdate(sql,new String[]{pkValue});
			    cz = "�޸�";
			}else{
				sql = "delete from "+realTable+" where xh=? and xn=? and xq=? and dm=?";
				done = dao.runUpdate(sql,new String[]{xh,xn,xq,dm});	
		        cz = "����";	
			}
			if(done){
				sql = "insert into "+realTable+"(xh,xn,xq,dm,jyfs,jysj,sfjy)values(?,?,?,?,?,?,?)";
				done = dao.runUpdate(sql, new String[]{xh,xn,xq,dm,jyfs,jysj,sfjy});
			}
			if(done){
				request.setAttribute("done", "ok");
            	String logMsg = userName + "�ڱ�'"+realTable+"'��"+cz+"��ѧ��Ϊ"+xh+"ѧ��Ϊ"+xn+"��ѧ��Ϊ"+xq+"����չ�༶���루���ڱ�sztz_bjdmb�У�Ϊ"+dm+"�ļ�¼�ֶ�jyfsΪ"+jyfs+"���ֶ�jysjΪ"+jysj+"";
    			dao = new DAO(request.getRemoteAddr());
    			dao.writeLog(userName,new String[]{}, new String[]{}, logMsg,request);				
 			}else{
				request.setAttribute("done", "no");
			}			  
		}else if(doType.equalsIgnoreCase("modi")){
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xqmc,dm,mc,jyfs,jysj,sfjy SF from "+tableName+" where "+pk+" =?";
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","xqmc","dm","mc","jyfs","jysj","SF"};
			String[] rsTem = dao.getOneRs(sql,new String[]{pkValue},colList);		
			for (int i = 0; i < colList.length; i++) {
				rsTem[i] = ((rsTem[i] == null) || rsTem[i].equalsIgnoreCase("")) ? " "
						: rsTem[i];
				map.put(colList[i].toLowerCase(), rsTem[i].trim());
			}
			myForm.setSF(rsTem[13]);
		}
		
		
		List tzBjList = mydao.getTzBjList(xn, xq);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("sfList", dao.getChkList(2));
		request.setAttribute("tzBjList",tzBjList);		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("rs", map);
		request.setAttribute("pk", pk);
		return mapping.findForward("success");
	}
	
//  ��ȡ�б���Ϣ
	private static void getListxx(HttpServletRequest request, DAO dao, String xydm, String zydm, String nj) {
		
		xydm = xydm==null ? "":xydm;
		zydm = zydm==null ? "":zydm;
		nj = nj==null ? "":nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// ���Ͱ༶�б�		
	}
}


