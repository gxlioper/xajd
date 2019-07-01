
package xgxt.xsgygl.action;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Check_Input_Value;



public class XsGyGlLogic extends DispatchAction {
	String writeAble = "";
	String userType = "";
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		/**�ж��û���дȨ*/
		writeAble = Base.getWriteAble(request);
		String dxq = request.getParameter("writeAble");
		if(!"".equalsIgnoreCase(dxq) && dxq != null){
			writeAble = dxq;
		}
		/** ���߼�� */
		int i = Base.chkTimeOut(session);
		if (i <= 2) {
			request.setAttribute("errMsg", "��½��ʱ�������µ�½��");
			return new ActionForward("/login.jsp", false);
		}
		userType = session.getAttribute("userType").toString(); //ֱ�ӻ���û����ͣ��˷�ʽ��ȡ������Ϊ���ࣺ1��"stu"ѧ��2��"teacher"��ʦ
		request.setAttribute("writeAble", writeAble);
		return super.execute(mapping, form, request, response);
	}
   /**ˮ�糬�����Ϣ�����ѯ*/
	public ActionForward xsGySdCbGl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		XsgyglForm myForm =(XsgyglForm)form;
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		}
		String lddm = request.getParameter("lddm");
		String nd =  request.getParameter("nd");
		String yf = request.getParameter("yf");
		String qsh = request.getParameter("qsh");
		String sql = "";
		String rsNum = "";
		String pk = "nd||yf||lddm||qsh";
		Vector<Object> rs = new Vector<Object>();
		String tableName = "view_gysdcbxx";
		String realTable = "gdby_gysdcbxxb";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append((Base.isNull(nd))?" and 1=1 ":" and nd='"+nd+"' ");
		querry.append((Base.isNull(yf))?" and 1=1 ":" and yf='"+yf+"' ");
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(qsh)?" and 1=1 ":" and qsh ='"+qsh+"' ");
        
		sql = "select * from (select * from( select " + pk + " ����,rownum r,a.* from " + tableName + " a " + querry.toString() + " order by nd,yf,cbsl,cbdl) where rownum<="
		+ (myForm.getPages().getStart() + myForm.getPages().getPageSize()) 
		+ ") where r>" + myForm.getPages().getStart();
		String[] colList = new String[]{"����","nd","yf","ldmc","qsh","cbsl","cbsf","cbdl","cbdf","yjje","yijje","yue"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);      
		List topTr = dao.arrayToList(colList, colListCN);
		// ========== begin ���ΰ 2009/5/19 ===============
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
			String xh = request.getParameter("xh");
			querry.append((Base.isNull(xh))?" and 1=1 ":" and xh='"+xh+"' ");
			sql = "select * from (select rownum r, c.* from (select distinct (qsh), ����, nd, yf, lddm, ldmc,"
					+ " cbsl, cbsf, cbdl, cbdf, yjje, yijje, yue from (select nd || yf || lddm || qsh ����, a.*"
					+ " from view_gysdcbxx a, xszsxxb b"
					+ querry.toString()
					+ " and a.lddm || '-' || a.qsh = b.ssbh"
					+ " order by nd, yf, cbsl, cbdl)) c) where r > "
					+ myForm.getPages().getStart()
					+ " and r <= "
					+ (myForm.getPages().getStart() + myForm.getPages()
							.getPageSize());
			request.setAttribute("xxdm", xxdm);
		}
		// ========== end ���ΰ 2009/5/19 ===============
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
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
			sql = "select count(*) count from (select distinct (qsh), ����, nd, yf, lddm, ldmc,"
					+ " cbsl, cbsf, cbdl, cbdf, yjje, yijje, yue from (select nd || yf || lddm || qsh ����, a.*"
					+ " from view_gysdcbxx a, xszsxxb b"
					+ querry.toString()
					+ " and a.lddm || '-' || a.qsh = b.ssbh"
					+ " order by nd, yf, cbsl, cbdl))";
			myForm.getPages().setMaxRecord(
					Integer.parseInt(dao
							.getOneRs(sql, new String[] {}, "count")));
		} else {
			myForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from "
							+ tableName + " a" + querry, new String[] {},
							"count")));
		}
		
		getldQsList(lddm,request,dao);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr",topTr);
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("yfList",dao.getYfList());
		request.setAttribute("rs", rs);
		return mapping.findForward("xsGySdCbGl");
	}
	/**ˮ�糬����Ϣ��ɾ�Ĳ���*/
	public ActionForward sdCbXx_Modi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
        String sql = "";
        String lddm = request.getParameter("lddm");
        String qsh = request.getParameter("qsh");
        String nd = request.getParameter("nd");
        String yf = request.getParameter("yf");
        String cbsl = request.getParameter("cbsl");
        String cbsf = request.getParameter("cbsf");
        String cbdl = request.getParameter("cbdl");
        String cbdf = request.getParameter("cbdf");
        String yjje = request.getParameter("yjje");
        String yijje = request.getParameter("yijje");
        String yue   = request.getParameter("yue");
        String pkValue = request.getParameter("pkValue");
        HashMap<String,String> map = new HashMap<String,String>();
        boolean del = false;
        String[] colList= new String[]{"nd","yf","lddm","qsh","cbsl","cbsf","cbdl","cbdf","yjje","yijje","yue"};
        String[] colListV= new String[]{"nd","yf","lddm","ldmc","qsh","cbsl","cbsf","cbdl","cbdf","yjje","yijje","yue"};
        if(!Base.isNull(doType)){
        	if(doType.equalsIgnoreCase("add")){
        		for(int i=0; i<colList.length; i++){
        			map.put(colList[i], "");
        		}
        	}else if(doType.equalsIgnoreCase("modi")){
        		sql = "select * from view_gysdcbxx where nd||yf||lddm||qsh=?";
        		String[] sdValue = dao.getOneRs(sql,new String[]{pkValue}, colListV);
        		if(sdValue!=null){
        			for(int i=0;i<colListV.length;i++){
        				map.put(colListV[i].toString(),Base.isNull(sdValue[i])?"":sdValue[i]);
        			}
        		}
        	}else if(doType.equalsIgnoreCase("save")){//��ӡ��޸Ľ������
        		String yhcz = "";
        		String[] oldVals = null;
        		if(!Base.isNull(pkValue)){
        			del =true;
        			oldVals = dao.getOneRs("select * from gdby_gysdcbxxb where nd||yf||lddm||qsh=?", new String[]{pkValue}, colList);
        		}else{
        			oldVals = dao.getOneRs("select * from gdby_gysdcbxxb where nd=? and yf=? and lddm=? and qsh=?", new String[]{nd,yf,lddm,qsh}, colList);
        			sql = "delete from gdby_gysdcbxxb where nd=? and yf=? and lddm=? and qsh=?";
        			del = dao.runUpdate(sql, new String[]{nd,yf,lddm,qsh});
        		}       	   
        		if(del){
        			if(!Base.isNull(pkValue)){
        				yhcz="�޸�";
        				sql = "update gdby_gysdcbxxb set cbsl=?,cbdl=?,cbsf=?,cbdf=?,yjje=?,yijje=?,yue=? where  nd||yf||lddm||qsh=?";
        				del = dao.runUpdate(sql, new String[]{cbsl,cbdl,cbsf,cbdf,yjje,yijje,yue,pkValue});
        			}else{
        				yhcz = "���";
        				sql = "insert into gdby_gysdcbxxb(nd,yf,lddm,qsh,cbsl,cbdl,cbsf,cbdf,yjje,yijje,yue) values(?,?,?,?,?,?,?,?,?,?,?)";
        				del = dao.runUpdate(sql,new String[]{nd,yf,lddm,qsh,cbsl,cbdl,cbsf,cbdf,yjje,yijje,yue});
        			}
        		}	
        		if(del){
        			dao.writeLog(sql, new String[]{nd,yf,lddm,qsh,cbsl,cbdl,cbsf,cbdf}, oldVals, yhcz, request);
        			request.setAttribute("done", "ok");
        		}else{
        			request.setAttribute("done", "no");
        		}	   
        	}else if(doType.equalsIgnoreCase("del")){
        		String[] oldVals = dao.getOneRs("select * from gdby_gysdcbxxb where nd||yf||lddm||qsh=?", new String[]{pkValue}, colList);
        		sql = "delete from gdby_gysdcbxxb where nd||yf||lddm||qsh=?";
    			del = dao.runUpdate(sql, new String[]{pkValue});
    			if(del){
    				dao.writeLog(sql,null, oldVals, "ɾ��", request);
        			request.setAttribute("done", "ok");
        		}else{
        			request.setAttribute("done", "no");
        		}	
    			return new ActionForward("/XsGyGlLogic.do?method=xsGySdCbGl",false);
        	} 
        }
        request.setAttribute("pkValue",pkValue);
        getldQsList(lddm,request,dao);	
        request.setAttribute("rs", map);
        request.setAttribute("doType", doType);
		return mapping.findForward("sdCbXx_Modi");
	}
	/**ˮ�糬����Ϣ�·ݻ���*/
	public ActionForward sdCbXxTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		XsgyglForm myForm=(XsgyglForm)form; 
		String nd = myForm.getNd();
		String yf = myForm.getYf();
		String xxmc = dao.getOneRs("select xxmc from xtszb ",new String[]{}, "xxmc");
		Vector<Object> rs = new Vector<Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "select (select a.ldmc from sslddmb a where a.lddm=b.lddm )ldmc,sum(nvl(cbsl,'0'))sumsl,sum(nvl(cbsf,'0'))sumsf," +
		"sum(nvl(cbdl,'0'))sumdl,sum(nvl(cbdf,'0'))sumdf from view_gysdcbxx b where nd=? and yf=? group by lddm";
		List list  = dao.getList(sql,new String[]{nd,yf},new String[]{"ldmc","sumsl","sumsf","sumdl","sumdf"});
		map.put("list",list);		
		sql = "select sum(nvl(cbsl,'0'))zsl,sum(nvl(cbsf,'0'))zsf,sum(nvl(cbdl,'0'))zdl,sum(nvl(cbdf,'0'))zdf from view_gysdcbxx where nd=? and yf=?";
		List list2 = dao.getList(sql,new String[]{nd,yf},new String[]{"zsl","zsf","zdl","zdf"});
		map.put("list2",list2);
		rs.add(map);
		request.setAttribute("rs", rs);
		if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_GDBYXY)){
			xxmc = "���ƹ��̸߼�����ѧУ";
		}
		request.setAttribute("xxmc",xxmc);
		request.setAttribute("nd", nd);
		request.setAttribute("yf", yf);
		return mapping.findForward("sdCbXxTj");
	}
	/**������Ա��Ϣ��ѯ*/
	public ActionForward xsGyWlRy (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		XsgyglForm myForm = (XsgyglForm)form;
//		String userNameReal = request.getSession().getAttribute("userNameReal").toString();
//		String userName     = request.getSession().getAttribute("userName").toString();
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		}
		String lddm = myForm.getLddm();
		String yf = myForm.getYf();
		String rq = myForm.getRq();
		String nd = myForm.getNd();
		String sql = "";
		String rsNum = "";
		String pk = "id";
		Vector<Object> rs = new Vector<Object>();
		String tableName = "view_wlryxx";
		String realTable = "wlryxxb";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append((Base.isNull(nd))?" and 1=1 ":" and substr(lfrq,1,4)='"+nd+"' ");
		querry.append((Base.isNull(yf))?" and 1=1 ":" and substr(lfrq,5,2)='"+yf+"' ");
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm='"+lddm+"' ");
		querry.append((Base.isNull(rq))?" and 1=1 ":" and lfrq='"+rq+"'");
		
		if(request.getParameter("done")!=null
				&&request.getParameter("done").equalsIgnoreCase("bbTj")){//�ǼǱ���
			String ldmc = dao.getOneRs("select ldmc from sslddmb where lddm=? ",new String[]{lddm}, "ldmc");
			sql = "select * from "+tableName+ querry.toString();
			rs.addAll(dao.rsToVator(sql, new String[] {}, new String[]{"lfsj","xm","zj","hjr","lfsy","lfrq","lksj"}));
			
			request.setAttribute("nd",Base.isNull(nd)?"":nd+"��");
			request.setAttribute("yf",Base.isNull(yf)?"":yf+"��");
			request.setAttribute("ldmc",ldmc);
			request.setAttribute("rq",Base.isNull(rq)?"":rq.substring(6)+"��");
			request.setAttribute("rs", rs);
			return new ActionForward("/gygl/gdby/wlyrxxbb.jsp",false);
		}
		
		sql = "select * from (select * from( select (case when lksj is null then '#CCCCCC' else '#FFFFFF' end) bgcolor," + pk + " ����,rownum r,a.ldmc,a.xm,a.zj,a.hjr,a.lfrq,(case when a.lfsy is null then '' else substr(a.lfsy,1,1)||'...' end) lfsy,a.lfsj,(case when a.lksj is null then 'δ�Ǽ��뿪'else a.lksj end )lksj from " + tableName + " a " + querry.toString() + " order by lfrq desc ) where rownum<="
		+ (myForm.getPages().getStart() + myForm.getPages().getPageSize()) 
		+ ") where r>" + myForm.getPages().getStart();
		String[] colList= new String[]{"bgcolor","����","ldmc","xm","zj","hjr","lfrq","lfsy","lfsj","lksj"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);      
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
	    myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry, new String[]{}, "count")));		

		getldQsList(lddm,request,dao);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr",topTr);
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
//		request.setAttribute("userNameReal",userNameReal);
		return mapping.findForward("xsGyWlRy");
	}
	/**������Ա��Ϣ��ɾ�Ĳ���
	 * @throws Exception */
	public ActionForward xsGyWlRy_Modi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
        String pkValue = request.getParameter("pkValue");
        String doType = request.getParameter("doType");
		String userName     = request.getSession().getAttribute("userName").toString();
		String userNameReal = request.getSession().getAttribute("userNameReal").toString();
        String xm = DealString.toGBK(request.getParameter("xm"));
        String lfrq = request.getParameter("lfrq");
        String lfsj = DealString.toGBK(request.getParameter("lfsj"));
        String lksj = DealString.toGBK(request.getParameter("lksj"));
        String zj = DealString.toGBK(request.getParameter("zj"));
        String hjr = DealString.toGBK(request.getParameter("hjr"));
        String lfsy = DealString.toGBK(request.getParameter("lfsy"));
        String lddm = request.getParameter("lddm");
        String sql = "";
        boolean del = false;
        String[] colList= new String[]{"lddm","xm","zj","hjr","lfsy","lfrq","lfsj","lksj","zbrxm"};
        if(!Base.isNull(doType)){
        	if(doType.equalsIgnoreCase("add")){
        		for(int i=0;i<colList.length;i++){
        			map.put(colList[i].toString(), "");
        		}
        		map.put("zbrxm",userNameReal);
        	}else if(doType.equalsIgnoreCase("save")){
        		String yhcz = "";
        		String[] oldVals = null;
        		if(!Base.isNull(pkValue)){
        			oldVals = dao.getOneRs(sql,new String[]{pkValue},colList);
        			yhcz = "�޸�";
        			sql = "update wlryxxb set lddm=?,xm=?,zj=?,hjr=?,lfsy=?,lfrq=?,lfsj=?,lksj=? where id=?";        			
        			del = dao.runUpdate(sql, new String[]{lddm,xm,zj,hjr,lfsy,lfrq,lfsj,lksj,pkValue});
        		}else{
        			yhcz = "���";
        			sql = "insert into wlryxxb(id,lddm,xm,zj,hjr,lfsy,lfrq,lfsj,lksj,zbr,zbrxm) values(S_WLRYXX_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
        			del = dao.runUpdate(sql,new String[]{lddm,xm,zj,hjr,lfsy,lfrq,lfsj,lksj,userName,userNameReal});
        		}
        		if(del){
        			request.setAttribute("done","ok");
        			dao.writeLog(sql,new String[]{lddm,xm,zj,hjr,lfsy,lfrq,lfsj,lksj}, oldVals, yhcz, request);
        		}else{
        			request.setAttribute("done", "no");
        		}
        	}else if(doType.equalsIgnoreCase("modi")||doType.equalsIgnoreCase("view")){
        		sql = "select * from wlryxxb where id=?";
        		String[] ryValue = dao.getOneRs(sql,new String[]{pkValue}, colList);
        		if(ryValue!=null){
        			for(int i=0;i<colList.length;i++){
        				map.put(colList[i].toString(),Base.isNull(ryValue[i])?"":ryValue[i]);
        			}
        		}
        	}else if(doType.equalsIgnoreCase("del")){
        		String[] oldVals = dao.getOneRs("select * from wlryxxb where id=?",new String[]{pkValue}, colList); 
        		sql = "delete from wlryxxb where id=?";
        		del = dao.runUpdate(sql, new String[]{pkValue});
        		if(del){
        			dao.writeLog(sql,null, oldVals, "ɾ��", request);
        			request.setAttribute("done", "ok");
        		}else{
        			request.setAttribute("done", "no");
        		}
        		return new ActionForward("/XsGyGlLogic.do?method=xsGyWlRy",false);
        	}
        }
        List ldList = dao.getList("select distinct lddm,ldmc from sslddmb  order by  lddm ", new String [] {}, new String[]{"lddm","ldmc"});
		request.setAttribute("ldList", ldList);
        request.setAttribute("rs", map);
        request.setAttribute("pkValue", pkValue);
        request.setAttribute("doType",doType);
		return mapping.findForward("xsGyWlRy_Modi");
	}
	/**ѧ���ǳ������Ǽ�*/
	public ActionForward xsGyXsCr (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		XsgyglForm myForm = (XsgyglForm)form;
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		}
		String lddm = myForm.getLddm();
		String yf = myForm.getYf();
		String rq = myForm.getRq();
		String nd = myForm.getNd();
		String xh = myForm.getXh();
		String xm = DealString.toGBK(myForm.getXm());
		String xdp = DealString.toGBK(myForm.getXdp());
		myForm.setXm(xm);
		myForm.setXdp(xdp);
		String sql = "";
		String rsNum = "";
		String pk = "id";
		Vector<Object> rs = new Vector<Object>();
		String tableName = "view_xscrxx";
		String realTable = "xscrxxb";
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append((Base.isNull(nd))?" and 1=1 ":" and substr(rq,1,4)='"+nd+"' ");
		querry.append((Base.isNull(yf))?" and 1=1 ":" and substr(rq,5,2)='"+yf+"' ");
		querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm='"+lddm+"' ");
		querry.append((Base.isNull(rq))?" and 1=1 ":" and rq='"+rq+"' ");
		querry.append(Base.isNull(xm)?" and 1=1 ":" and xm like '%"+xm+"%' ");
		querry.append(Base.isNull(xdp)?" and 1=1 ":" and xdp like '%"+xdp+"%'");
		querry.append(Base.isNull(xh)?" and 1=1 ":" and xh = '"+xh+"' ");
		  
		sql = "select * from (select * from( select " + pk + " ����,rownum r,a.* from " + tableName + " a " + querry.toString() + " order by rq desc ) where rownum<="
		+ (myForm.getPages().getStart() + myForm.getPages().getPageSize()) 
		+ ") where r>" + myForm.getPages().getStart();
		String[] colList = new String[]{"����","ldmc","xm","bjmc","qsh","xdp","sl","jlr","rq","djsj","dcsj"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);      
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
	    myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry, new String[]{}, "count")));		

		getldQsList(lddm,request,dao);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr",topTr);
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("yfList",dao.getYfList());
		request.setAttribute("rs", rs);
		return mapping.findForward("xsGyXsCr");
	}
	public ActionForward xsGyXsCr_Modi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
        String pkValue = request.getParameter("pkValue");
        String doType = request.getParameter("doType");
        String xh = DealString.toGBK(request.getParameter("xh"));
        String lddm = request.getParameter("lddm");
        String qsh = request.getParameter("qsh");
        String xdp = DealString.toGBK(request.getParameter("xdp"));
        String sl = DealString.toGBK(request.getParameter("sl"));
        String djsj = DealString.toGBK(request.getParameter("djsj"));
        String dcsj = DealString.toGBK(request.getParameter("dcsj"));
        String djr = request.getSession().getAttribute("userName").toString();
        String rq = request.getParameter("rq");
        String bz = DealString.toGBK(request.getParameter("bz"));
        String sql = "";
        boolean del = false;
        String[] colListV = new String[] { "xh", "xm", "xb", "nj", "xymc","zymc", "bjmc","lddm","qsh" };
		String[] rsV = dao.getOneRs("select xh,xm,xb,nj,xymc,zymc,bjmc,lddm,qsh from view_xszsxx where xh=?",
				new String[] { xh }, colListV);
		if (rsV != null) {
			for (int i = 0; i < colListV.length; i++) {
				map.put(colListV[i], rsV[i]);
			}
			lddm = map.get("lddm");
		}             
        String[] colList= new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","lddm","ldmc","qsh","xdp","sl","djsj","dcsj","jlr","rq","bz"};
        if(!Base.isNull(doType)){
        	if(doType.equalsIgnoreCase("save")){
        		String yhcz = "";
        		String[] oldVals = null;
        		if(!Base.isNull(pkValue)){
        			oldVals = dao.getOneRs(sql,new String[]{pkValue},colList);
        			yhcz = "�޸�";
        			sql = "update xscrxxb set xdp=?,sl=?,djsj=?,dcsj=?,jlr=?,rq=?,bz=? where id=? ";        			
        			del = dao.runUpdate(sql, new String[]{xdp,sl,djsj,dcsj,djr,rq,bz,pkValue});
        		}else{
        			yhcz = "���";
        			sql = "insert into xscrxxb(id,xh,lddm,qsh,xdp,sl,djsj,dcsj,jlr,rq,bz) values(S_XSCRXXB_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
        			del = dao.runUpdate(sql,new String[]{xh,lddm,qsh,xdp,sl,djsj,dcsj,djr,rq,bz});
        		}
        		if(del){
        			request.setAttribute("done","ok");
        			dao.writeLog(sql,new String[]{xh,lddm,qsh,xdp,sl,djsj,dcsj,djr,rq,bz}, oldVals, yhcz, request);
        		}else{
        			request.setAttribute("done", "no");
        		}
        	}else if(doType.equalsIgnoreCase("modi")||doType.equalsIgnoreCase("view")){
        		sql = "select * from view_xscrxx where id=?";
        		String[] ryValue = dao.getOneRs(sql,new String[]{pkValue}, colList);
        		if(ryValue!=null){
        			for(int i=0;i<colList.length;i++){
        				map.put(colList[i].toString(),Base.isNull(ryValue[i])?"":ryValue[i]);
        			}
        		}
        	}else if(doType.equalsIgnoreCase("del")){
        		String[] oldVals = dao.getOneRs("select * from xscrxxb where id=?",new String[]{pkValue}, colList); 
        		sql = "delete from xscrxxb where id=?";
        		del = dao.runUpdate(sql, new String[]{pkValue});
        		if(del){
        			dao.writeLog(sql,null, oldVals, "ɾ��", request);
        			request.setAttribute("done", "ok");
        		}else {
        			request.setAttribute("done", "no");
        		}
        		return new ActionForward("/XsGyGlLogic.do?method=xsGyXsCr",false);
        	}
        }
    	getldQsList(lddm,request,dao);
    	request.setAttribute("rs", map);
        request.setAttribute("pkValue", pkValue);
        request.setAttribute("doType",doType);
		
		return mapping.findForward("xsGyXsCr_Modi");
	}
	public ActionForward xsGySsGbxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();		
		String[] colList = new String[]{"�к�","xh","xm","xb","bjmc","ldmc","qsh","cs","zw","qsdh","sjhm","jtdz"} ;
		String sql = "select rownum �к�,a.xh,a.xm,a.xb,a.bjmc,b.ldmc,b.qsh,substr(b.qsh,1,1)cs,a.qsdh,a.sjhm,"
			+"(select c.jtszd from xsfzxxb c where b.qsz=c.xh)jtdz"
			+",(case when b.qsz=a.xh then '���ҳ�  ' else '' end )||(case when b.cz=a.xh then '�㳤' else '' end) zw "
			+"from(select  xh,xm,xb,bjmc,qsdh,sjhm from  view_xsjbxx where xh in "
			+"(select distinct xh from (select qsz xh from view_gygl_lczfpb a union all (select cz  xh from view_gygl_lczfpb )))"
			+" order by bjmc )a,view_gygl_lczfpb b where a.xh=b.qsz or a.xh=b.cz";    
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		request.setAttribute("rs", rs);
		return mapping.findForward("xsGySsGbxx");
	}
	public static void getldQsList(String lddm,HttpServletRequest request, DAO dao) {
		String sql = "";
		StringBuffer cxtj = new StringBuffer();

		cxtj.append(" where 1=1");
		if("".equalsIgnoreCase(lddm) || lddm==null){
			cxtj.append(" and 1=2");
		}else{
			if(Check_Input_Value.check2(lddm)){
				cxtj.append(" and lddm='");
				cxtj.append(lddm);
				cxtj.append("'");
			}
		}
		sql = "select distinct qsh from ssxxb " + cxtj +" order by qsh";
		List ssList = dao.getList(sql, new String[] {},
				new String[] { "qsh" });
		request.setAttribute("ssList", ssList);
		List ldList = dao.getList("select distinct lddm,ldmc from sslddmb  order by  lddm ", new String [] {}, new String[]{"lddm","ldmc"});
		request.setAttribute("ldList", ldList);
		request.setAttribute("ndList",Base.getXnndList());
		request.setAttribute("yfList", dao.getYfList());			
	}
   /**�㶫�������ᴲλ���ͳ��*/
   public ActionForward gdby_dormCwtj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
	   DAO dao = DAO.getInstance();
	   StringBuffer sql = new StringBuffer();
	   Vector<Object> rs = new Vector<Object>();
//	   Vector<Object> rs1 = new Vector<Object>();
//	   Vector<Object> rs2 = new Vector<Object>();
	   sql.append("select lddm from sslddmb  order by  lddm ");
	   String[] lddmVal = dao.getRs(sql.toString(), new String[]{},"lddm");	  
	   String[] ldmcVal = dao.getRs("select ldmc from sslddmb  order by  lddm ".toString(), new String[]{},"ldmc");
	   String[] colList = null;
	   int maxNj = Integer.parseInt(dao.getOneRs("select max(nj) maxnj from view_xsjbxx ", new String[]{},"maxnj"));//��ǰ����꼶
	   request.setAttribute("nj1",maxNj-2);
	   request.setAttribute("nj2",maxNj-1);
	   request.setAttribute("nj3",maxNj);
	   int hjzss=0,hjzcw=0,hjycw=0,hjsycw=0,hjcoutxh1=0,hjcoutxh2=0,hjcoutxh3=0,hjsumcws=0;//�ϼ���
	   if(lddmVal!=null){
		   for(int i=0;i<lddmVal.length;i++){
			   HashMap<String,String> map = new HashMap<String,String>();
			  //��ȡ��λʹ�����������¥����
			   sql = new StringBuffer();
			   sql.append(" select lddm,ldmc,zss,zcw,ycw,(zcw-ycw)sycw from ( select a.lddm,a.zss,a.zcw,b.ycw,a.ldmc from( ");
			   sql.append(" select ldmc,lddm,count(ssbh)zss, sum(is_ret_number(cws))zcw  from view_ssxx group by lddm,ldmc ");
			   sql.append(" )a left join  (select ldmc,lddm,count(xh) ycw from view_xszsxx  group by lddm,ldmc )b on a.lddm=b.lddm ");
			   sql.append(" ) where lddm  in (select lddm from sslddmb)and lddm=? ");
			   colList = new String[]{"ldmc","zss","zcw","ycw","sycw"};
			   String[] valueTem = dao.getOneRs(sql.toString(),new String[]{lddmVal[i]},colList);			 
			   if(valueTem==null){
				   valueTem = new String[]{ldmcVal[i],"0","0","0","0","0"};
			   }
			   for (int j = 0; j < colList.length; j++) {
					   map.put(colList[j],valueTem[j]);
			   }
			   
			   //��ȡ¥��ʹ����� �����꼶 �����꼶�ڣ�
			   sql = new StringBuffer();
			   sql.append("select coutxh1,coutxh2,coutxh3,(coutxh1+coutxh2+coutxh3)sumcws from dual a," );
			   sql.append("(select count(xh) coutxh1 from view_xszsxx where nj=? and lddm=?)b, ");
			   sql.append("(select count(xh) coutxh2 from view_xszsxx where nj=? and lddm=?)c, ");
			   sql.append("(select count(xh) coutxh3 from view_xszsxx where nj=? and lddm=?)d ");			
			   colList = new String[]{"coutxh1","coutxh2","coutxh3","sumcws"};
			   String[] value = dao.getOneRs(sql.toString(),new String[]{String.valueOf(maxNj-2),lddmVal[i],
					   String.valueOf(maxNj-1),lddmVal[i],String.valueOf(maxNj),lddmVal[i]},colList);
			   if(value==null){
				   value = new String[]{"0","0","0","0"};
			   }
			   for (int n = 0; n < colList.length; n++) {
				   map.put(colList[n],Base.isNull(value[n])?"0":value[n]);
			   }
			   
			   rs.add(map);
			   //�ϼ�			   
			   hjzss+=Integer.parseInt(map.get("zss").toString());
			   hjzcw+=Integer.parseInt(map.get("zcw").toString());
			   hjycw+=Integer.parseInt(map.get("ycw").toString());
			   hjsycw+=Integer.parseInt(map.get("sycw").toString());
			   hjcoutxh1+=Integer.parseInt(map.get("coutxh1").toString());
			   hjcoutxh2+=Integer.parseInt(map.get("coutxh2").toString());
			   hjcoutxh3+=Integer.parseInt(map.get("coutxh3").toString());
			   hjsumcws+=Integer.parseInt(map.get("sumcws").toString());
		   }
		   request.setAttribute("hjzss", hjzss);//�����ϼ�
		   request.setAttribute("hjzcw", hjzcw);//��λ�ϼ�
		   request.setAttribute("hjycw", hjycw);//ռ�ô�λ�ϼ�
		   request.setAttribute("hjsycw", hjsycw);//ʣ�ലλ�ϼ�
		   request.setAttribute("hjcoutxh1", hjcoutxh1);//ʹ�ô�λ�ϼ�
		   request.setAttribute("hjcoutxh2", hjcoutxh2);//ʹ�ô�λ�ϼ�
		   request.setAttribute("hjcoutxh3", hjcoutxh3);//ʹ�ô�λ�ϼ�
		   request.setAttribute("hjsumcws", hjsumcws);////ʹ�ô�λС�Ǻϼ�
		   request.setAttribute("rs",rs);
	   }
	   request.setAttribute("rs", rs);
	   return mapping.findForward("gdby_dormCwtj");
   }
   /**�㶫�������ᰲȫ(����)�����¼��*/
   public ActionForward gdby_dormJlTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
	    Vector<Object> vec = new Vector<Object>();
		StringBuffer sql = new StringBuffer();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream()); // ����������
		WritableSheet ws = wwb.createSheet("�������ͳ�Ʊ�", 0); // ����������
		WritableFont wf = new WritableFont(WritableFont.TIMES); // ���������ʽ
		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wf.setBoldStyle(WritableFont.BOLD); // ���������ʽ(����)
		wf.setPointSize(22); // ���������ʽ(��С)
		wcf.setFont(wf); // ����ָ�������ʽ�ĵ�Ԫ���ʽ
		wcf.setAlignment(Alignment.CENTRE); // ָ����ʽ�����ַ����Ҿ���
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // ָ����ʽ�����ַ����¾���
		//wcf.setWrap(true); // ָ����ʽ�����Զ�����
		ws.mergeCells(0, 0, 12, 0); // ����ָ����-�кϲ���Ԫ��
		ws.setColumnView(0, 4); // ����ָ���е��п�		
		//����
		ws.addCell(new Label(0, 0, "���ᰲȫ(����)�����¼��",wcf)); // �����ָ����ʽ�ĵ�Ԫ��ֵ       
		sql.append("select xh, xm, bjmc,zymc,ldmc,qsh,wjlbmc,count(xh) sumwj,bz ");
        sql.append("from view_zsjlxx group by xh,wjlbdm,ldmc,qsh,xm,bjmc,zymc,wjlbmc,bz ");
        String[] ColumnName = new String[]{"xh","xm","bjmc","zymc","ldmc","qsh","wjlbmc","sumwj","bz"};
        String[] ColumnNameCN = new String[]{"ѧ��","����","�༶����","רҵ����","¥������","���Һ�","Υ����Ŀ(���)","����","��ע"};
        
       
        WritableFont wf1 = new WritableFont(WritableFont.TIMES); // ���������ʽ
		WritableCellFormat wcf1 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wf1.setBoldStyle(WritableFont.BOLD); // ���������ʽ(����)
		wf1.setPointSize(10); // ���������ʽ(��С)
		wcf1.setFont(wf1); // ����ָ�������ʽ�ĵ�Ԫ���ʽ
		 //�ֶ���
        for (int m = 0; m < ColumnNameCN.length; m++) {
        	ws.setColumnView(m,12);// ����ָ���е��п�
			ws.addCell(new Label(m, 1, ColumnNameCN[m],wcf1));
		}
        //�ֶ�ֵ
        vec.addAll(dao.rsToVator(sql.toString(), new String[] {}, ColumnName));
        for (int i = 0; i < vec.size(); i++) {
			String[] tmp = (String[]) vec.toArray()[i];
			for (int j = 0; j < ColumnName.length; j++) {
				ws.addCell(new Label(j, i + 2, tmp[j]));
			}
		}
		wwb.write();
		wwb.close();
		return mapping.findForward("gdby_dormJlTj");
   }
   /**
    * ����ɾ������
   */
	public  ActionForward xsGyGlDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Base.chkSessionTimeOut(session);
		ActionForward newFwd = new ActionForward();
		String tabName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		if(tabName!=null&tabName.equalsIgnoreCase("view_ssxx")){
			//String writeAble = CheckPower.checkUsrPower(userName, "stu_info_query.do?method=stuInfo")?"yes":"no";
			newFwd = new ActionForward("/ssxx_search.do?go=go", false);
		}
		String delPk = request.getParameter("delPk");
		String[] pkValueA = delPk.split("!!");

		for (int i = 0; i < pkValueA.length; i++) {
			String pk = "ssbh";
			StandardOperation.delete(realTable, pk, pkValueA[i], request);							
		}
		return newFwd;
	}
}
