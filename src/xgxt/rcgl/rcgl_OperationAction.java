
package xgxt.rcgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import common.Globals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.rcgl.rcgl_form;
import xgxt.utils.FormModleCommon;
import xgxt.base.DealString;

public class rcgl_OperationAction{
	private static boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase(""));
	}
	public static ActionForward rcgl_sdataSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		rcgl_form dsForm = (rcgl_form)form;
		HttpSession session = request.getSession();
		DAO  dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		String act = request.getParameter("act");
//		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String isFdy=session.getAttribute("fdyQx")==null ? "false"  : String.valueOf(session.getAttribute("fdyQx"));
		String isBzr=session.getAttribute("bzrQx")==null ? "false"  : String.valueOf(session.getAttribute("bzrQx"));
		String.valueOf("");
		String pk = "";
		String nj = dsForm.getNj();
		String xn = dsForm.getXn();
		String xq = dsForm.getXq();
		String xydm = dsForm.getXydm();
		String zydm = dsForm.getZydm();
		String bjdm = dsForm.getBjdm();
		String xh = dsForm.getXh();
		String xm = DealString.toGBK(dsForm.getXm());
		Vector<Object> rs = new Vector<Object>();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "";
		String sql = "";
		String tips = "";
		String xxdm = StandardOperation.getXxdm();
		boolean isSH = false;
		querry.append(" where 1=1 ");		
		querry.append((isNull(nj))? " and 1=1 " : " and nj = '"+nj+"'");
		querry.append((isNull(xn))? " and 1=1 " : " and xn = '"+xn+"'");
		querry.append((isNull(xq))? " and 1=1 " : " and xq = '"+xq+"'");
		querry.append((isNull(xh))? " and 1=1 " : " and xh = '"+xh+"'");
		querry.append((isNull(xm))? " and 1=1 " : " and xm like '"+xm+"'");
		querry.append((isNull(xydm))? " and 1=1 " : " and xydm = '"+xydm+"'");
		querry.append((isNull(zydm))? " and 1=1 " : " and zydm = '"+zydm+"'");
		querry.append((isNull(bjdm))? " and 1=1 " : " and bjdm = '"+bjdm+"'");
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
			dsForm.setXydm(userDep);
		}
		if(act.equalsIgnoreCase("xsz")){
			realTable = "xszbbb";
			tableName = "view_xszbb";
			pk = "xh||sqsj";
			tips = "�ճ����� - ��� - ѧ��֤�������";
			colList = new String[]{"bgcolor", "����","nd","xn","xqmc","xh","xm","sqsj",""};
			isSH = true;						
		}else if(act.equalsIgnoreCase("hck")){
			realTable = "hcyhkbbb";
			tableName = "view_hcyhkbb";
			pk = "xh||sqsj";
			tips = "�ճ����� - ��� - ���Żݿ��������";
			tableName = "view_hcyhkbb";
			colList = new String[] { "bgcolor","����", "nd", "xn", "xqmc", "xh", "xm","sqsj",""};
			isSH = true;
		}else if(act.equalsIgnoreCase("ykt")){
			realTable = "yktbbb";
			tableName = "view_yktbb";
			pk = "xh||sqsj";
			tips = "�ճ����� - ��� - һ��ͨ�������";
			colList = new String[] { "bgcolor","����", "nd", "xn", "xqmc", "xh", "xm","sqsj",""};
			isSH = true;
		}else if(act.equalsIgnoreCase("xh")){
			realTable = "xhbbb";
			tableName = "view_xhbb";
			pk = "xh||sqsj";
			tips = "�ճ����� - ��� - У�ղ������";
			colList = new String[] { "bgcolor","����", "nd", "xn", "xqmc", "xh", "xm","sqsj",""};
			isSH = true;
			
			if("true".equalsIgnoreCase(isFdy) || "true".equalsIgnoreCase(isBzr)){
				
				querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' union ");
				querry.append(" select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' ) ");
			}else if("true".equalsIgnoreCase(isFdy)){
					
				querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

			}else if("true".equalsIgnoreCase(isBzr)){
					
				querry.append(" and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and zgh='"+userName+"' )");

			}else if("xy".equalsIgnoreCase(userType)){
				
				querry.append(" and xydm='"+userDep+"' ");
			}
		}else if(act.trim().equalsIgnoreCase("xfhj")){//&& xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)
			//ѧ�ѻ���     �㽭����  
			realTable = "xfhjb";
			tableName = "view_xfhjxx";
			pk = "xh";
			tips = "�ճ����� - ��� - ѧ�ѻ������";
			colList = new String[] { "bgcolor","����", "nd", "xn", "xqmc", "xh", "xm",""};
			isSH = true;
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
					if(Globals.XXDM_YNYS.equals(xxdm)){
						sql = "select * from (select * from(select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ "a.* from(select "
							+ pk
							+ " ����,rownum r,a.* from "
							+ tableName
							+ " a"
							+ querry
							+ " and xydm='"
							+ userDep + "'order by xysh desc) a )where rownum<="
							+ (dsForm.getPages().getStart() + dsForm.getPages().getPageSize()) 
							+ ") where r>" + dsForm.getPages().getStart();
					}else{
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
					}
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
		
			sql = "select count(*) count from " + tableName + " a" + querry;
			
			dsForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName + " a" + querry, new String[]{}, "count")));

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
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��		
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				new String[] { "xxmc" })[0];
		request.setAttribute("xxmc", xxmc);// ȡѧУ������Ϣ
		request.setAttribute("xxdm", StandardOperation.getXxdm());
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", act);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
//		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
//		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
//		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
//		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
//		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
//		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ����רҵ�б�
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("userType", userType);
		request.setAttribute("isSH", isSH);
		return mapping.findForward("success");
	}
	public static ActionForward xszCheckOne(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		DAO dao = DAO.getInstance();
		rcgl_form chkForm = (rcgl_form) form;
		HttpSession session = request.getSession();
		ActionForward newFwd = null;
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String pk = request.getParameter("pk");
		String[] colList = null;
		String tips = "";
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String shType = "";
		String sql = "";
		String xxdm = StandardOperation.getXxdm();
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
			sql = "update "+realTable+" set "+shType+" = '"+yesNo+"' where "+pk+" = ?";
			boolean rel = false;
			rel = dao.runUpdate(sql, new String[]{pkValue});
			if(rel){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done", "no");
			}
		}
		if(tableName.equalsIgnoreCase("view_xszbb")){
			tips = "�ճ����� - ��� - ѧ��֤������� - ������� ";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
				//����Ƽ�ѧԺ
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,sqsj,bbyy,syd,csrq,mz,hczm,xz,sfzh,"+shType+" yesNo"
					+ " from view_xszbb  where " + pk + "='" + pkValue + "'";
				//colList = dao.getColumnName("select * from view_xszbb where 1=2");
				colList = new String[] {"xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "xn", "nd", "xqmc","sqsj","bbyy","syd","csrq","mz","hczm","xz","sfzh","yesNo"};	
			}else{
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,sqsj,bbyy,"+shType+" yesNo from view_xszbb where "+pk+" = '"+pkValue+"'";
				colList = new String[] {"xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "xn", "nd", "xqmc","sqsj","bbyy","yesNo"};
			}	
			newFwd = new ActionForward("/shgc/rcgl/xszbbsh.jsp",false);
		}else if(tableName.equalsIgnoreCase("view_hcyhkbb")){
			tips = "�ճ����� - ��� - ���Żݿ�������� - �������";
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,sqsj,bbyy,"+shType+" yesNo from view_hcyhkbb where "+pk+" = '"+pkValue+"'";
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xqmc","sqsj","bbyy","yesNo"};
			newFwd = new ActionForward("/shgc/rcgl/xszbbsh.jsp",false);
		}else if(tableName.equalsIgnoreCase("view_yktbb")){
			tips = "�ճ����� - ��� - һ��ͨ������� - �������";
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,sqsj,bbyy,"+shType+" yesNo from view_yktbb where "+pk+" = '"+pkValue+"'";
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xqmc","sqsj","bbyy","yesNo"};
			newFwd = new ActionForward("/shgc/rcgl/xszbbsh.jsp",false);
		}else if(tableName.equalsIgnoreCase("view_xhbb")){
			tips = "�ճ����� - ��� - У�ղ������ - �������";
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,sqsj,bbyy,"+shType+" yesNo from view_xhbb where "+pk+" = '"+pkValue+"'";
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xqmc","sqsj","bbyy","yesNo"};
			newFwd = new ActionForward("/shgc/rcgl/xszbbsh.jsp",false);
		}else if(tableName.equalsIgnoreCase("view_xfhjxx")){// &&  xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)
			//�����㽭����  ��Ҫ�������
			tips = "�ճ����� - ��� - ѧ�ѻ������ - �������";
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,hjqx,bz,"+shType+" yesNo from view_xfhjxx where "+pk+" = '"+pkValue+"'";
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xqmc","hjqx","bz","yesNo"};
			newFwd = new ActionForward("/shgc/rcgl/xfhjsh.jsp",false);
		}
		
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			request.setAttribute(colList[i], rs[i]);
		}
		String bbxmmc = "";
		if("xszbbb".equals(realTable)){
			bbxmmc = "ѧ��֤";
		}else if("hcyhkbbb".equals(realTable)){
			bbxmmc = "���Żݿ�";
		}else if("yktbbb".equals(realTable)){
			bbxmmc = "һ��ͨ";
		}else if("xhbbb".equals(realTable)){
			bbxmmc = "У��";
		}
		request.setAttribute("bbxmmc", bbxmmc);
		chkForm.setYesNo(rs[12]);
		request.setAttribute("userType", userType);
	    request.setAttribute("pkValue", pkValue);
	    request.setAttribute("chkList", dao.getChkList(3));
	    request.setAttribute("realTable", realTable);
	    request.setAttribute("tableName", tableName);
	    request.setAttribute("tips", tips);
	    request.setAttribute("pk", pk);

		return newFwd;
		
	}
//	public ActionForward rcgl_modiData(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
//		ActionForward newFwd = new ActionForward();
//		rcgl_form mdForm = (rcgl_form) form;
//		HttpSession session = request.getSession();
//		DAO dao = DAO.getInstance();
//		DealString deal = new DealString();
//		String pk = request.getParameter("pk");
//		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
//		String realTable = request.getParameter("realTable");
//		String tableName = request.getParameter("tableName");
//		String doType = request.getParameter("doType");
//		String from = request.getParameter("from");
//		String sql = "";
//		String xxmc = StandardOperation.getXxmc();
//		String xxdm = StandardOperation.getXxdm();
//		if ((doType == null) || doType.equalsIgnoreCase("")) {
//			// �����쳣
//			mdForm.setErrMsg("sdf");
//			return mapping.findForward("false");
//		}else if (doType.equalsIgnoreCase("del")){
//			
//		}else if (doType.equalsIgnoreCase("save")){
//			
//		}else{
//			sql = "select * from " + tableName;
//			String[] record = null;
//			String[] colList = dao.getColumnName(sql);
//			sql += (" where " + pk + "='" + pkValue + "'");
//			String rec = dao.getStringToSplit(sql, new String[] {}, colList);
//		}
//		return newFwd;	
//	}
	
	public static ActionForward xfcjSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		DAO dao = DAO.getInstance();
		rcgl_form chkForm = (rcgl_form) form;
		boolean flag = false;		
		String values = request.getParameter("val");
		String[] valueOne = values.split("@@");
		String xq = dao.getOneRs("select dqxq from xtszb", new String[] {}, "dqxq");
		//String nd = dao.getOneRs("select dqnd from xtszb", new String[] {}, "dqnd");
		String xh = chkForm.getXh();
		try{
		for(int i=0;i<valueOne.length;i++){
			String[] val = valueOne[i].split("!!");		
			String xn=val[0].substring(val[0].indexOf("-")+1,val[0].length());
			String je=val[1].substring(val[1].indexOf("-")+1,val[1].length());
			String qflx=val[2].substring(val[2].indexOf("-")+1,val[2].length());
			String bz=DealString.toGBK(val[3].substring(val[3].indexOf("-")+1,val[3].length()));
			List list= dao.getList("select xh from xfcjb where xh=? and xn=?", new String[] {xh,xn}, new String[] {"xh"});
			if(qflx!=null && !"".equalsIgnoreCase(qflx)){
			if(list!=null && list.size()>0){
				StandardOperation.delete("xfcjb", "xh||xn", xh.trim()+xn.trim(), request);
				flag = StandardOperation.insert("xfcjb", new String[]{"xh","je","xn","xq","qflxdm","bz"}, new String[]{xh,je,xn,xq,qflx,bz}, request);
			}else{
				flag = StandardOperation.insert("xfcjb", new String[]{"xh","je","xn","xq","qflxdm","bz"}, new String[]{xh,je,xn,xq,qflx,bz}, request);
			}
			if(flag==false)
				new Exception();
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		request.setAttribute("result", flag);
		request.setAttribute("doType", request.getParameter("doType"));
		return mapping.findForward("success");
	}
	
	public static ActionForward selectInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{

		DAO dao = DAO.getInstance();
//		rcgl_form chkForm = (rcgl_form) form;
//		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh").trim();
		String xn = request.getParameter("xn").trim();
		//ѧ��������Ϣ
		HashMap< String, String> rs = new HashMap<String, String>();
		rs=dao.getMap("select xh,xm,xb,xymc,zymc,bjmc,xz,nj from view_xsjbxx where xh=?", new String[] {xh}, new String[] {"xh","xm","xb","xymc","zymc","bjmc","xz","nj"});		
		//�߽���Ϣ
		List xfcjList = dao.getList("select * from xfcjb where xh=?", new String[] {xh}, new String[] {"xn","je","qflxdm","bz"});
		//������Ϣ
		List xfhjList = dao.getList("select hjqx,hjje from xfhjb where xh=?", new String[] {xh}, new String[] {"hjqx","hjje"});
		//Ƿ������
		List qflxList = dao.getList("select qflxdm,qflxmc from qflxdmb", new String[] {}, new String[] {"qflxdm","qflxmc"});
		request.setAttribute("doType", "modi");
		request.setAttribute("rs", rs);
		request.setAttribute("xfcjList", xfcjList);
		request.setAttribute("xfhjList", xfhjList);
		request.setAttribute("rsNum", xfcjList.size());
		request.setAttribute("pkValue", xh+xn);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("qflxList", qflxList);
		return mapping.findForward("success");
	}
	
	public static ActionForward xfhjSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
		DAO dao = DAO.getInstance();
		rcgl_form chkForm = (rcgl_form) form;
		boolean flag = false;		
		String values = request.getParameter("val");
		String[] valueOne = values.split("@@");
		String xq = dao.getOneRs("select dqxq from xtszb", new String[] {}, "dqxq");
		String xh = chkForm.getXh();		
		try{
		for(int i=0;i<valueOne.length;i++){
			String[] val = valueOne[i].split("!!");		
			String nd=val[0].substring(val[0].indexOf("-")+1,val[0].length());
			String hjje=val[1].substring(val[1].indexOf("-")+1,val[1].length());
			String hjqx=val[2].substring(val[2].indexOf("-")+1,val[2].length());
			String bz=val[3].substring(val[3].indexOf("-")+1,val[3].length());
			List list= dao.getList("select xh from xfhjb where xh=? and nd=?", new String[] {xh,nd}, new String[] {"xh"});
			if(hjqx!=null && !"".equalsIgnoreCase(hjqx)){
			if(list!=null && list.size()>0){
				flag = StandardOperation.update("xfhjb", new String[]{"xh","hjje","hjqx","nd","xq","bz"}, new String[]{xh,hjje,hjqx,nd,xq,bz}, "xh||nd", xh.trim()+nd.trim(), request);
			}else{
				flag = StandardOperation.insert("xfhjb", new String[]{"xh","hjje","hjqx","nd","xq","bz"}, new String[]{xh,hjje,hjqx,nd,xq,bz}, request);
			}
			if(flag==false)
				new Exception();
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		request.setAttribute("result", flag);
		request.setAttribute("doType", request.getParameter("doType"));
		return mapping.findForward("success");
	}
	
	public static ActionForward selectXfhjInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{

		DAO dao = DAO.getInstance();
//		rcgl_form chkForm = (rcgl_form) form;
//		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh").trim();
		String nd = request.getParameter("nd").trim();
		//ѧ��������Ϣ
		HashMap< String, String> rs = new HashMap<String, String>();
		rs=dao.getMap("select xh,xm,xb,xymc,zymc,bjmc,xz,nj from view_xsjbxx where xh=?", new String[] {xh}, new String[] {"xh","xm","xb","xymc","zymc","bjmc","xz","nj"});		
		//������Ϣ
		List xfhjList = dao.getList("select nd,hjqx,hjje,bz from xfhjb where xh=?", new String[] {xh}, new String[] {"nd","hjqx","hjje","bz"});
		
		request.setAttribute("doType", "modi");
		request.setAttribute("rs", rs);		
		request.setAttribute("xfhjList", xfhjList);		
		request.setAttribute("pkValue", xh+nd);
		request.setAttribute("rsNum", xfhjList.size());
		request.setAttribute("xnList", dao.getXnndList());		
		return mapping.findForward("success");
	}
	
}
