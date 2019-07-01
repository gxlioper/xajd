
package xgxt.sxjy.action;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.ExcelMB;
import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SxjyForm;
import xgxt.utils.SearchUtils;

public class FdypjForShgcAction extends DispatchAction{
	SxjyDAO dao = new SxjyDAO();
	String[] colList = null;
	String[] colListCN = null;
	String sql =null;
	String writeAble = null;
	
	public ActionForward fdyzp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		HttpSession session = request.getSession();
		SxjyForm myForm = (SxjyForm) form;
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String puber = session.getAttribute("userName").toString();
		//�ж��Ƿ��Ǹ���Ա
		HashMap<String, String> map = dao.sxjyQueryOne("fdyxxb", new String[]{"zgh"}, "zgh",puber);
		if(map.get("zgh").equalsIgnoreCase("")){//����Ǹ���Ա�������ø���Ա��־
			request.setAttribute("isfdy", "false");
			return mapping.findForward("fdyzp");
		} else {
			request.setAttribute("isfdy", "true");
		}
		String tableName = "sxjy_fdyzpb";
		map = dao.sxjyQueryOne(tableName, new String []{"zgh"}, "zgh||xn||nd||xq",puber+xn+nd+xq);
		if (!map.get("zgh").equalsIgnoreCase("")) {
			sql = "select xjmc,zpdjdm,jhnr from sxjy_fdyzpb where zgh||xn||nd||xq = ? ";
			CLOB clob = dao.getOneClob(sql, new String[] { puber+xn+nd+xq }, "jhnr");
			String  [] tmp = (dao.getOneRs(sql, new String[] { puber+xn+nd+xq },new String[] { "xjmc","zpdjdm"}));
			String plansTit = tmp[0];
			String pjdj = tmp[1];
			request.setAttribute("planscont", clob.getSubString(1L, (int) clob.length()));
			request.setAttribute("planstit", plansTit);
			request.setAttribute("pjdj", pjdj);
			request.setAttribute("isModi", "yes");
			myForm.setPjdj(pjdj);
		} else {
			request.setAttribute("planscont", "");
			request.setAttribute("planstit", "");
			request.setAttribute("pjdj", "");
			request.setAttribute("isModi", "no");
			myForm.setPjdj("");
		}
		List<HashMap<String, String>> fdydjList = dao.getCommonList("sxjy_fdypjdjdmb", "djdm","djmc", "");
		request.setAttribute("PjdjList", fdydjList);// �������ݼ�
		return mapping.findForward("fdyzp");
}
	public ActionForward saveFdyzp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		SxjyDAO dao = new SxjyDAO();
		HttpSession session = request.getSession();
		String clobSql="";
		String sql="";
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String puber = session.getAttribute("userName").toString();
		//String ssmk = msgForm.getXmdm();
		String title = DealString.toGBK(request.getParameter("title"));
		String pjdj = DealString.toGBK(request.getParameter("pjdj"));
		String sContent = DealString.toGBK(request.getParameter("content1"));
		String isModi = request.getParameter("isModi");
		title = isNull(title) ? "�ޱ���" : title;
		if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				sql = "update sxjy_fdyzpb set xjmc='"+title+"',zpdjdm = '"+pjdj+"', jhnr='null' where zgh||xn||xq||nd = '"+puber+xn+xq+nd+"'";
				clobSql="select jhnr from sxjy_fdyzpb where zgh||xn||xq||nd = '"+puber+xn+xq+nd+"' for update";
				dao.saveClobs(sql, clobSql, sContent, "jhnr");
		} else {
				sql="insert into sxjy_fdyzpb(zgh,xn,xq,nd,xjmc,jhnr,zpdjdm) values('"+puber+"','"+xn+"','"+xq+"','"+nd+"','"+title+"','null','"+pjdj+"')";
				clobSql="select jhnr from sxjy_fdyzpb where jhnr like 'null' for update";
				dao.saveClobs(sql, clobSql, sContent, "jhnr");
		}
		return new ActionForward("fdypjOther.do?method=fdyzp", true);
}
	public ActionForward fdyzpCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		HttpSession session = request.getSession();
		SxjyForm myForm = (SxjyForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = "view_sxjy_fdyzpb";
		String xydm = request.getParameter("xydm");
		if(userType.equalsIgnoreCase("xy")){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd = request.getParameter("nd");
		String xm = request.getParameter("xm");
		colList = new String [] {"zgh||xn||nd||xq","zgh","xm","bmmc","djmc","lrrq"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
		String query = SearchUtils.makeQueryCondition(xydm,"","","","",xm,"",nd,xq,xn);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
		}
		if(isNull(xn)){
			xn = Base.currXn;
		}
		if(isNull(nd)){
			nd = Base.currNd;
		}
		if(isNull(xq)){
			xq = Base.currXq;
		}
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("userType", userType);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xydm", xydm);
		myForm.setXn(xn);
		myForm.setNd(nd);
		myForm.setXq(xq);
		return mapping.findForward("fdyzpList");
}
	
	public ActionForward fdyhp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		HttpSession session = request.getSession();
//		SxjyForm myForm = (SxjyForm) form;;
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		//�ж��Ƿ��Ǹ���Ա
		HashMap<String, String> map = dao.sxjyQueryOne("fdyxxb", new String[]{"zgh"}, "zgh",userName);
		if(map.get("zgh").equalsIgnoreCase("")){//����Ǹ���Ա�������ø���Ա��־
			request.setAttribute("isfdy", "false");
			return mapping.findForward("fdyzp");
		} else {
			request.setAttribute("isfdy", "true");
		}
		//ȡ�����Լ�֮��������Լ�ѧԺ�ĸ���Ա�б�
		colList = new String[]{"zgh","xm","pjdj"};
		String sql = "select a.zgh,a.xm,b.pjdjdm pjdj from view_fdyxx a left join (select zgh,pjdjdm from sxjy_fdyhpb where pjr = ? and xn = ? and nd = ? and xq = ?) b on a.zgh = b.zgh  where a.zgh != ? and a.bmdm = ?";
		List<HashMap<String, String>> list = dao.sxjyQueryforList("view_fdyxx", " where zgh != ? and bmdm = ?", new String[]{userName,xn,nd,xq,userName,userDep}, colList, sql);
//		String userType = session.getAttribute("userType").toString();
		String sum = (((Integer)(list.size())).toString());
		request.setAttribute("sum", sum);
		List<HashMap<String, String>> fdydjList = dao.getCommonList("sxjy_fdypjdjdmb", "djdm","djmc", "");
		request.setAttribute("rs", list);// �������ݼ�
		request.setAttribute("PjdjList", fdydjList);// �������ݼ�
		String stStr =dao.listToString(list, new String[] { "zgh","pjdj" });
		request.setAttribute("stStr", stStr);
		return mapping.findForward("fdyhp");
}
	
	public ActionForward fdyhpSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		HttpSession session = request.getSession();
//		SxjyForm myForm = (SxjyForm) form;
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		//ȡ�����Լ�֮��������Լ�ѧԺ�ĸ���Ա�б�
		boolean inserted = StandardOperation.delete("sxjy_fdyhpb", "xn||nd||xq||pjr",xn+nd+xq+userName, request);
		if(inserted){
		colList = new String[]{"zgh"};
		rs = dao.sxjyQuery("view_fdyxx", " where zgh != ? and bmdm = ?", new String[]{userName,userDep}, colList, "");
//		String userType = session.getAttribute("userType").toString();
		String[] insertSql = new String[rs.size()];
		for(int i=0;i<rs.size();i++){
			String zgh =rs.get(i)[0];
			String djdm = request.getParameter(rs.get(i)[0]);
			insertSql[i] = StandardOperation.insertSql("sxjy_fdyhpb", new String[]{"zgh","xn","nd","xq","pjr","pjdjdm"}, new String []{zgh,xn,nd,xq,userName,djdm}, request);
		}
		int[] res = dao.runBatch(insertSql);
		for(int i=0;i<res.length;i++){
			inserted = (res[i]==Statement.EXECUTE_FAILED)?false:true;
			if(!inserted) break;
		}
		}
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return new ActionForward("fdypjOther.do?method=fdyhp", true);
}
	public ActionForward fdyhpCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		HttpSession session = request.getSession();
		SxjyForm myForm = (SxjyForm) form;
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
//		String puber = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
//		String tableName = "sxjy_fdyhpb";
		String xydm = request.getParameter("xydm");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd = request.getParameter("nd");
		String xm = request.getParameter("xm");
		String [] colListCN = new String []{"pk","zgh","xm","bmmc","pjrs"};
		@SuppressWarnings("unused")
		String query = SearchUtils.makeQueryCondition(xydm,"","","","",xm,"",nd,xq,xn);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			ArrayList<String[]> rs1 = dao.sxjyQuery("sxjy_fdypjdjdmb", " order by djdm", new String[]{}, new String[]{"djdm","djmc"},"");
			int pddj = rs1.size();
			String queryx = "";
			colList = new String [5+pddj];
			colList[0]= "pk";
			colList[1]= "zgh";
			colList[2]= "xm";
			colList[3]= "bmmc";
			colList[4]= "pjrs";
			colListCN = new String [5+pddj];
			colListCN[0]= "pk";
			colListCN[1]= "ְ����";
			colListCN[2]= "����";
			colListCN[3]= "��������";
			colListCN[4]= "��������";
			for(int i = 0; i<pddj;i++){
				colList[5+i]= "y"+rs1.get(i)[0];
				colListCN[5+i]= rs1.get(i)[1];
				queryx += "'0' y";  
				queryx += rs1.get(i)[0];
				queryx += ",";                   
			}
			String sql = "select a.zgh pk,a.zgh,a.xm,nvl(b.pjrs,'0') pjrs," +queryx+
				"a.bmmc from view_fdyxx a left join (select count(*) pjrs,zgh from sxjy_fdyhpb where xn = ? and nd = ? and xq = ? group by zgh ) b on a.zgh = b.zgh ";
			if(xydm != null && !("".equalsIgnoreCase(xydm.trim()))){
				sql+=" where a.bmdm='";
				sql+=xydm;
				sql+="' ";
			}
			sql+=" order by zgh";
			rs = dao.sxjyQueryForHashMap("","",new String []{xn,nd,xq},colList,sql);
			String [] colList2 = new String [] {"zgh","pjrs","pjdjdm"};
			sql = "select count(*) pjrs,zgh,pjdjdm from sxjy_fdyhpb where xn = ? and nd = ? and xq = ? group by zgh,pjdjdm order by zgh,pjdjdm";
			ArrayList<String[]> rs2 = dao.sxjyQuery("","",new String []{xn,nd,xq},colList2,sql);
//			ArrayList<String[]> vector = new ArrayList<String[]>();
			for (int i = 0;i<(rs2.size());i++){
				if(rs.get(rs2.get(i)[0])!=null){
					String [] rsTmp= new String [5+pddj];
					String [] tmp = rs.get(rs2.get(i)[0]);
					rsTmp[0] = tmp[0];
					rsTmp[1] = tmp[1];
					rsTmp[2] = tmp[2];
					rsTmp[3] = tmp[3];
					rsTmp[4] = tmp[4];
					for(int j = 0; j<pddj;j++){
						if(rs1.get(j)[0].equalsIgnoreCase(rs2.get(i)[2])){
						rsTmp[5+j]= rs2.get(i)[1];
						}else{
							rsTmp[5+j] = "0";
						}
					}
					rs.put(rs2.get(i)[0],rsTmp);
				}
		}
			List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
			request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		}
		if(isNull(xn)){
			xn = Base.currXn;
		}
		if(isNull(nd)){
			nd = Base.currNd;
		}
		if(isNull(xq)){
			xq = Base.currXq;
		}
		request.setAttribute("rs", rs.values());// �������ݼ�
		request.setAttribute("userType", userType);// �������ݼ�
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xydm", xydm);
		request.setAttribute("pkbc", xn+nd+xq);
		myForm.setXn(xn);
		myForm.setNd(nd);
		myForm.setXq(xq);
		if(userType.equalsIgnoreCase("xy")){
			xydm = userDep;
		}
		myForm.setXydm(xydm);
		return mapping.findForward("fdyhpList");
}
	
	public ActionForward ztjyjhzjb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	ExcelMB Emb         = new ExcelMB();
    	SxjyService service = new SxjyService();
    	String nd = DealString.toGBK(request.getParameter("nd"));
    	String xqsj = DealString.toGBK(request.getParameter("xq"));
    	response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream());
		String[] thd = service.getXymcz();
		HashMap<String, String[]> map = service.getSjrq(nd,xqsj);
		HashMap<String, String[]> zjmap = service.getSjzjrq(nd,xqsj);
		
		WritableSheet ws = wwb.createSheet(nd+"���"+xqsj+"��������ƻ��ܽ�ͳ�Ʊ�", 0);
		if(xqsj.equalsIgnoreCase("�ϰ���")){
			Emb.printToOneCell(ws,"03�¼ƻ�",0,2,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"03���ܽ�",0,3,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"04�¼ƻ�",0,4,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"04���ܽ�",0,5,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"05�¼ƻ�",0,6,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"05���ܽ�",0,7,10,false,Alignment.CENTRE,true);	
			Emb.printToOneCell(ws,"06�¼ƻ�",0,8,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"06���ܽ�",0,9,10,false,Alignment.CENTRE,true);
		}else if(xqsj.equalsIgnoreCase("�°���")){
			Emb.printToOneCell(ws,"09�¼ƻ�",0,2,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"09���ܽ�",0,3,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"10�¼ƻ�",0,4,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"10���ܽ�",0,5,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"11�¼ƻ�",0,6,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"11���ܽ�",0,7,10,false,Alignment.CENTRE,true);	
			Emb.printToOneCell(ws,"12�¼ƻ�",0,8,10,false,Alignment.CENTRE,true);
			Emb.printToOneCell(ws,"12���ܽ�",0,9,10,false,Alignment.CENTRE,true);
		}
		
		for(int i=0;i<thd.length;i++){
			Emb.printToOneCell(ws,thd[i],i+1,1,10,false,Alignment.CENTRE,true);
			if(xqsj.equalsIgnoreCase("�ϰ���")){
				for(int j = 3;j<7;j++){
					String [] xymcz = map.get(thd[i]+j);
					if(xymcz!=null){
						Emb.printToOneCell(ws,xymcz[1],i+1,(j-2)*2,10,false,Alignment.CENTRE,true);
					}
					String [] zjxymcz = zjmap.get(thd[i]+j);
					if(zjxymcz!=null){
						Emb.printToOneCell(ws,zjxymcz[1],i+1,(j-2)*2+1,10,false,Alignment.CENTRE,true);
					}
				}
				
			}else if(xqsj.equalsIgnoreCase("�°���")){
				for(int j = 9;j<13;j++){
					String [] xymcz = map.get(thd[i]+j);
					if(xymcz!=null){
						Emb.printToOneCell(ws,xymcz[1],i+1,(j-8)*2,10,false,Alignment.CENTRE,true);
					}
					String [] zjxymcz = zjmap.get(thd[i]+j);
					if(zjxymcz!=null){
						Emb.printToOneCell(ws,zjxymcz[1],i+1,(j-8)*2+1,10,false,Alignment.CENTRE,true);
					}
				}
			}
		}
//		for(int j=0;j<list.size();j++){
//				Emb.printToOneCell(ws,list.get(j).get("xh"),0,j+2,10,false,Alignment.CENTRE,true);
//				Emb.printToOneCell(ws,list.get(j).get("xm"),1,j+2,10,false,Alignment.CENTRE,true);
//				Emb.printToOneCell(ws,list.get(j).get("sjfs"),2,j+2,10,false,Alignment.CENTRE,true);
//				Emb.printToOneCell(ws,list.get(j).get("ydfs"),3,j+2,10,false,Alignment.CENTRE,true);
//				Emb.printToOneCell(ws,list.get(j).get("bhfs"),4,j+2,10,false,Alignment.CENTRE,true);
//				Emb.printToOneCell(ws,list.get(j).get("mbfs"),5,j+2,10,false,Alignment.CENTRE,true);
//				Emb.printToOneCell(ws,list.get(j).get("jdfs"),6,j+2,10,false,Alignment.CENTRE,true);
//				Emb.printToOneCell(ws,list.get(j).get("hj"),7,j+2,10,false,Alignment.CENTRE,true);		  			   
//		}
		wwb.write();
		wwb.close();
    	return mapping.findForward("success");
    }
	
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}
}