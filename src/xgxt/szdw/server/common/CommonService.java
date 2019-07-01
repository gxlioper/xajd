
package xgxt.szdw.server.common;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.FdyglForm;
import xgxt.szdw.dao.common.CommonDAO;
import xgxt.szdw.form.common.CommonForm;
import xgxt.szdw.model.common.CommonModel;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.utils.SearchUtils;

public class CommonService {
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ����˼������-����service��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	
	CommonDAO dao = new CommonDAO();
	
	public HashMap<String, String> inputXnNjXq(HashMap<String, String> rs){
	//    �ж�rs��ѧ�꣬��ȣ�ѧ���Ƿ���ֵ��������뵱ǰѧ�꣬��ȣ�ѧ��
		String xn           = Base.currXn;
		String xq           = Base.currXq;
		String nd           = Base.currNd;
		if(rs.get("xn").equalsIgnoreCase("")){
			rs.put("xn", xn);
		}
		
		if(rs.get("nd").equalsIgnoreCase("")){
			rs.put("nd", nd);
		}
		
		if(rs.get("xq").equalsIgnoreCase("")){
			rs.put("xq", xq);
		}
		return rs;
	}
	
	public List getShztList() {
		//    �õ����״̬�б�
		List ShztList = dao.getChkList(3);//��ͷ 
		return ShztList;
	}

	public ArrayList<String[]> getLtxxList(CommonModel myModel, String tableName,CommonForm myForm) throws Exception {
		// ���ʴ�ѧ����ȡ��̳��Ϣ
		String [] colList = new String []{}; 
		String [] colsList = new String []{}; 
		if(tableName.equalsIgnoreCase("fzjy_xsrxjyb")){
			colList = new String []{"fssj||zjr||kcmc pk","fssj","zjr","zjrdw","kcmc"};
			colsList = new String []{"pk","fssj","zjr","zjrdw","kcmc"};
		}else if (tableName.equalsIgnoreCase("fzjy_fdyltb")){
			colList = new String []{"fssj||zjr||zt pk","fssj","zjr","zjrzw","qj","zt"};
			colsList = new String []{"pk","fssj","zjr","zjrzw","qj","zt"};
		}else if (tableName.equalsIgnoreCase("view_yxdxsxsltb")){
			colList = new String []{"fssj||zjr||zt pk","fssj","zjr","xymc","qj","zt"};
			colsList = new String []{"pk","fssj","zjr","xymc","qj","zt"};
		}else if (tableName.equalsIgnoreCase("fzjy_hyjtb")){
			colList = new String []{"fssj||zjr||zt pk","fssj","zjr","qj","zt"};
			colsList = new String []{"pk","fssj","zjr","qj","zt"};
		}
		String zt = myModel.getZt();
		String kssj = myModel.getKssj();
		String jssj = myModel.getJssj();
		StringBuffer query = new StringBuffer(" where 1=1 ");
		if(zt != null && !("".equalsIgnoreCase(zt.trim()))){
			query.append(" and zt like '%");
			query.append(zt);
			query.append("%' ");
		}
		if(kssj != null && !("".equalsIgnoreCase(kssj.trim()))){
			query.append(" and fssj >= '");
			query.append(kssj);
			query.append("' ");
		}
		if(jssj != null && !("".equalsIgnoreCase(jssj.trim()))){
			query.append(" and fssj <= '");
			query.append(jssj);
			query.append("' ");
		}
		ArrayList<String[]> rs = dao.sxjyQueryCx(tableName, query.toString(), new String []{}, colList, "",myForm,colsList);
		return rs;
	}
	public ArrayList<String[]> getFdyGzwj(CommonModel myModel, String tableName) {
		//����Ա�����ʾ�
		String [] colList = new String []{}; 
		colList = new String []{"zgh","zgh","xm","bmmc","xn","xq","pjf","pjrs","sdrs","cpl"};
		String xn = myModel.getXn();
		String xq = myModel.getXq();
		String xm = DealString.toGBK(myModel.getXm());
		String bmdm = myModel.getBmdm();
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition("","","","","",xm,"","",xq,xn));
		if(bmdm != null && !("".equalsIgnoreCase(bmdm.trim()))){
			query.append(" and bmdm = '");
			query.append(bmdm);
			query.append("' ");
		}
		query.append(" order by pjf");
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getFdyGzwjTopTr(String tableName) {
		//		 ѧ���ɲ�������˱�ͷ
	    DAO dao = DAO.getInstance();
	    String [] colList = new String []{};
	    colList = new String []{"zgh","zgh","xm","bmmc","xn","xq","pjf","pjrs","sdrs","cpl"};
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
		return topTr;
	}

	public List getLtxxTopTr(String tableName) {
		//		 ѧ���ɲ�������˱�ͷ
	    DAO dao = DAO.getInstance();
	    String [] colList = new String []{};
	    if(tableName.equalsIgnoreCase("fzjy_xsrxjyb")){
			colList = new String []{"fssj||zjr||kcmc","fssj","zjr","zjrdw","kcmc"};
		}else if (tableName.equalsIgnoreCase("fzjy_fdyltb")){
			colList = new String []{"fssj||zjr||zt","fssj","zjr","zjrzw","qj","zt"};
		}else if (tableName.equalsIgnoreCase("view_yxdxsxsltb")){
			colList = new String []{"fssj||zjr||zt","fssj","zjr","xymc","qj","zt"};
		}else if (tableName.equalsIgnoreCase("fzjy_hyjtb")){
			colList = new String []{"fssj||zjr||zt","fssj","zjr","qj","zt"};
		}
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
		return topTr;
	}

	public HashMap<String, String> getJyltxtOne(String pk, String tableName) {
		// ��������̳��Ϣ��Ҫ������ʾ
		String [] colList = new String []{};
		String pkCol = "fssj||zjr||zt"; 
	    if(tableName.equalsIgnoreCase("fzjy_xsrxjyb")){
	    	pkCol = "fssj||zjr||kcmc"; 
			colList = new String []{"fssj||zjr||kcmc","fssj","zjr","zjrdw","kcmc","cyxy","bz"};
		}else if (tableName.equalsIgnoreCase("fzjy_fdyltb")){
			colList = new String []{"fssj||zjr||zt","fssj","zjr","zjrzw","qj","zt","jy","bz","cyry"};
		}else if (tableName.equalsIgnoreCase("view_yxdxsxsltb")){
			colList = new String []{"fssj||zjr||zt","fssj","zjr","xymc","qj","zt","jy","bz","chry","xydm","zjrjj"};
		}else if (tableName.equalsIgnoreCase("fzjy_hyjtb")){
			colList = new String []{"fssj||zjr||zt","fssj","zjr","qj","zt","zjrjj","jy","bz"};
		}
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, pkCol, pk);
		return rs;
	}

	public boolean updataJyltxt(CommonModel myModel, String pk, HttpServletRequest request) throws Exception {
		//���½�����̳��Ϣ 
		String tableName = DealString.toGBK(myModel.getTableName());
		String [] colList = new String []{};
		String pkComment = "fssj||zjr||zt"; 
		if(tableName.equalsIgnoreCase("fzjy_xsrxjyb")){
				pkComment = "fssj||zjr||kcmc"; 
				colList = new String []{"fssj","zjr","zjrdw","kcmc","cyxy","bz"};
		}else if (tableName.equalsIgnoreCase("fzjy_fdyltb")){
				colList = new String []{"fssj","zjr","zjrzw","qj","zt","jy","bz","cyry"};
		}else if (tableName.equalsIgnoreCase("view_yxdxsxsltb")){
				tableName = "fzjy_yxdxsxsltb";
				colList = new String []{"fssj","zjr","qj","zt","jy","bz","chry","xydm","zjrjj"};
		}else if (tableName.equalsIgnoreCase("fzjy_hyjtb")){
				colList = new String []{"fssj","zjr","qj","zt","zjrjj","jy","bz"};
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted  =  StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean deleteJyltxtOne(String pk, String tableName, HttpServletRequest request) throws Exception {
		// ɾ��������̳��Ϣ 
		String pkComment = "fssj||zjr||zt"; 
		if(tableName.equalsIgnoreCase("fzjy_xsrxjyb")){
				pkComment = "fssj||zjr||kcmc"; 
		}
		boolean del  =  StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}

	public ArrayList<String[]> getSjszsList() {
		String tableName    = "fzjy_sqsjzjb";
		String [] colList = new String []{"tablename","mkmc","kssj","jssj"};
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, " order by tablename", new String []{}, colList, "");
		return rs;
	}

	public boolean saveSjszs(CommonModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		String [] mkmcs = myModel.getMkmcs();
		String [] mkdms = myModel.getMkdms();
		String [] kssjs = myModel.getKssjs();
		String [] jssjs = myModel.getJssjs();
		boolean inserted = StandardOperation.delete("fzjy_sqsjzjb", "1","1", request);
		if(inserted){
			String[] insertSql = new String[mkmcs.length];
			for(int i=0;i<mkmcs.length;i++){
				insertSql[i] = StandardOperation.insertSql("fzjy_sqsjzjb", new String []{"mkmc","tablename","kssj","jssj"}, new String []{DealString.toGBK(mkmcs[i]),mkdms[i],kssjs[i],jssjs[i]}, request);
			}
			DAO dao = DAO.getInstance();
			int[] res = dao.runBatch(insertSql);
			for(int i=0;i<res.length;i++){
				inserted = (res[i]==Statement.EXECUTE_FAILED)?false:true;
				if(!inserted) break;
			}
		}
		return inserted;
	}

	public boolean getSqsj(String tableName) {
		String[] colList = new String[] { "kssj", "jssj"};
		HashMap<String, String> rs = dao.sxjyQueryOne("fzjy_sqsjzjb", colList, "tableName",
				tableName);
		String sqkssj =  rs.get("kssj");
		String sqjssj =  rs.get("jssj");
		String dqsj = GetTime.getNowTime2();
		if(sqkssj!=null&&sqjssj!=null){
		if(Integer.parseInt(sqkssj)<=Integer.parseInt(dqsj)&&Integer.parseInt(dqsj)<=Integer.parseInt(sqjssj)){
			return true;
		}else{
			return false;
		}
		}else{
			return true;
		}
	}

	public HashMap<String, String> getXsxxxx(String xh) {
		//  Ӣ�Ž���
		String tableName    = "view_xsxxb";
		String [] colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","zzmm","mzmc"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "xh", xh);
		return rs;
	}
	
	public HashMap<String, String> getXssqxx(String xh) {
		//  ��ͨѧ��������Ϣ
		String tableName    = "view_xsjbxx";
		String [] colList = new String []{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "xh", xh);
		return rs;
	}

	public List getSercicesbxskjcglxList() {
		// TODO �Զ����ɷ������
		CommonDAO dao = new CommonDAO();
		return dao.getSercicesbxskjcglxList();
	}

	public List getCgjbList() {
		//TODO �Զ����ɷ������
		CommonDAO dao = new CommonDAO();
		return dao.getCgjbList();
	}

	public boolean updateYcjy(CommonModel myModel,String pk, HttpServletRequest request) throws Exception {
		// Ӣ�Ź���
		boolean inserted = true;
		String tableName     = "fzjy_ycjyb";
		String pkComment       = "xn||nd||sbjhmc||xh";
		String [] colList      = new String []{"xn","nd","sbjhmc","xh","jsjsp","xfjj","zhcppm","cdrshzw","lxdh","email","hjqk","jhsxnr","bz","dsxm","dsdw","dsdh","dsemail","xysh","xxsh"}; 
		
		if(!pk.equalsIgnoreCase("")){
			inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		}else{
			colList      = new String []{"xn","nd","sbjhmc","xh","jsjsp","xfjj","zhcppm","cdrshzw","lxdh","email","hjqk","jhsxnr","bz","dsxm","dsdw","dsdh","dsemail"};
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean updateJzfdy(CommonModel myModel,String pk, HttpServletRequest request) throws Exception {
		// ��ְ����Ա
		boolean inserted = true;
		String tableName     = "fzjy_jzfdyb";
		String pkComment       = "xn||nd||xh";
		String [] colList      = new String []{"xn","nd","lxdh","xh","yysp","jsjsp","sfsxy","xfjj","zygbjl","shzyjl","xysh","xxsh"}; 
		
		if(!pk.equalsIgnoreCase("")){
			inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		}else{
			colList      = new String []{"xn","nd","lxdh","xh","yysp","jsjsp","sfsxy","xfjj","zygbjl","shzyjl"};
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean updateWspyxjgr(CommonModel myModel,String pk, HttpServletRequest request) throws Exception {
		// ���������Ƚ�����
		boolean inserted = true;
		String tableName     = "fzjy_wspyxjgrb";
		String pkComment       = "xn||nd||xh";
		String [] colList      = new String []{"xn","nd","sbjx","xh","zch","xfjj","zhcppm","yycj","cdrshzw","bz","xysh","xxsh"}; 
		
		if(!pk.equalsIgnoreCase("")){
			inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		}else{
			colList      = new String []{"xn","nd","sbjx","xh","zch","xfjj","zhcppm","yycj","cdrshzw","bz"}; 
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean updateXsjlsbgr(CommonModel myModel,String pk, HttpServletRequest request) throws Exception {
		// ѧ�������걨����
		boolean inserted = true;
		String tableName     = "fzjy_xsjlsbgrb";
		String pkComment       = "xn||nd||xh||sbxskjcgmc";
		String [] colList      = new String []{"xn","nd","sbxskjcglxdm","sbxskjcgmc","cgwcsj","cgjb","xskycgjzsm","xyshyj","xgcsdyj","xysh","xxsh","xh"}; 
		
		if(!pk.equalsIgnoreCase("")){
			inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		}else{
			colList = new String []{"xn","nd","sbxskjcglxdm","sbxskjcgmc","cgwcsj","cgjb","xskycgjzsm","xyshyj","xgcsdyj","xh"}; 
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public ArrayList<String[]> getXssqshList(CommonModel myModel, String tableName) {
		// ��ȡѧ����������б�
		String [] colList = new String []{}; 
		if(tableName.equalsIgnoreCase("view_fzjyycjyb")){
			colList = new String []{"pk","xh","xm","xymc","lxdh","sbjhmc","xysh","xxsh"};
		}else if (tableName.equalsIgnoreCase("view_fzjyjzfdyb")){
			colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","xysh","xxsh"};
		}else if (tableName.equalsIgnoreCase("view_fzjyxsjlsbgr")){
			colList = new String []{"pk","xh","xm","xymc","cgwcsj","cgjb","sbxskjcgmc","xysh","xxsh"};
		}else if (tableName.equalsIgnoreCase("view_wspyxjgr")){
			colList = new String []{"pk","xh","xm","xymc","sbjx","xysh","xxsh"};
		}
		String xydm = myModel.getXydm();
		String zydm = myModel.getZydm();
		String bjdm = myModel.getBjdm();
		String xm   = myModel.getXm();
		String nj   = myModel.getNj();
		String xysh = myModel.getXysh();
		String xxsh = myModel.getXxsh();
		String nd = myModel.getNd();
		String xn = myModel.getXn();
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"","",xm,nj,nd,"",xn));
		if(xysh != null && !("".equalsIgnoreCase(xysh.trim()))){
			query.append(" and xysh = '");
			query.append(xysh);
			query.append("' ");
		}
		if(xxsh != null && !("".equalsIgnoreCase(xxsh.trim()))){
			query.append(" and xxsh = '");
			query.append(xxsh);
			query.append("' ");
		}
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}

	public List getXssqshTopTr(String tableName) {
		// ��ȡѧ��������˱�ͷ
		DAO dao = DAO.getInstance();
	    String [] colList = new String []{}; 
		if(tableName.equalsIgnoreCase("view_fzjyycjyb")){
			colList = new String []{"pk","xh","xm","xymc","lxdh","sbjhmc","xysh","xxsh"};
		}else if (tableName.equalsIgnoreCase("view_fzjyjzfdyb")){
			colList = new String []{"pk","xh","xm","xymc","zymc","bjmc","xysh","xxsh"};
		}else if (tableName.equalsIgnoreCase("view_fzjyxsjlsbgr")){
			colList = new String []{"pk","xh","xm","xymc","cgwcsj","cgjb","sbxskjcgmc","xysh","xxsh"};
		}else if (tableName.equalsIgnoreCase("view_wspyxjgr")){
			colList = new String []{"pk","xh","xm","xymc","sbjx","xysh","xxsh"};
		}
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
		return topTr;
	}

	public boolean deleteXssbshOne(String pk, String realTable, HttpServletRequest request) throws Exception {
		// ɾ��ѧ�����������Ϣ
		String pkComment = "xn||nd||xh"; 
		if(realTable.equalsIgnoreCase("fzjy_ycjyb")){
			pkComment = "xn||nd||sbjhmc||xh"; 
		}else if(realTable.equalsIgnoreCase("fzjy_xsjlsbgrb")){
			pkComment = "xn||nd||xh||sbxskjcgmc"; 
		}
		boolean del  =  StandardOperation.delete(realTable, pkComment,pk, request);
		return del;
	}
	
	public HashMap<String, String> getXssbshOne(String pk, String tableName) {
		//	ѧ���걨�������
		String [] colList = new String []{};
		String pkCol = "pk"; 
	    if(tableName.equalsIgnoreCase("view_fzjyycjyb")){
			colList = new String []{"pk","xn","nd","sbjhmc","xh","jsjsp","xfjj","zhcppm","cdrshzw","lxdh","email","hjqk","jhsxnr","bz","dsxm","dsdw","dsdh","dsemail","xysh","xxsh","xydm","zydm","zymc","bjdm","bjmc","zzmm","mzmc","nj","xm","xymc","xb"};
		}else if (tableName.equalsIgnoreCase("view_fzjyjzfdyb")){
			colList = new String []{"pk","xn","nd","lxdh","xh","yysp","jsjsp","sfsxy","xfjj","zygbjl","shzyjl","xysh","xxsh","xm","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","xb","zzmmmc"};
		}else if (tableName.equalsIgnoreCase("view_fzjyxsjlsbgr")){
			colList = new String []{"bjdm","bjmc","cgjb","cgwcsj","nd","nj","pk","sbxskjcglxdm","sbxskjcgmc","xgcsdyj","xh","xm","xn","xskycgjzsm","xxsh","xydm","xymc","xysh","xyshyj","zydm","zymc","xb"};
		}else if (tableName.equalsIgnoreCase("view_wspyxjgr")){
			colList = new String []{"bjdm","bjmc","bz","cdrshzw","nd","nj","pk","sbjx","xfjj","xh","xm","xn","xxsh","xydm","xymc","xysh","yycj","zch","zhcppm","zydm","zymc","xb"};
		}
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, pkCol, pk);
		return rs;
	}

	public String getBjdmByXh(String xh) {
		//	ͨ����ɲ�ѧ������ȡ�༶����ķ���
		DAO dao = DAO.getInstance();
		String sql = "select bjdm from sxjy_bjgbxxb where xh = ?";
		return dao.getOneRs(sql, new String []{xh}, "bjdm");
	}

	public HashMap<String, String> getBjxx(String bjdm) {
		//  ͨ���༶�����ȡ�༶���
		DAO dao = DAO.getInstance();
		String sql   =  "select count(*) bjrs,bjdm,bjmc,zydm,zymc,xydm,xymc,nj,(select xm from view_bjgbxx where bjdm = a.bjdm and bjgbmc = '�೤') xm  from view_xsjbxx a where bjdm = ? group by bjdm,bjmc,zydm,zymc,xydm,xymc,nj";
		String [] colList = new String []{"bjrs","bjdm","zydm","bjmc","zymc","xydm","xymc","xm","nj"};
		HashMap<String, String> rs = dao.getMap(sql, new String []{ bjdm }, colList);
		return rs;
	}

	public boolean updateWspyxjJt(CommonModel myModel, String pk, HttpServletRequest request) throws Exception {
		//		 ���������Ƚ�����
		boolean inserted = true;
		String tableName     = "fzjy_wspyxjjtb";
		String pkComment       = "bjdm||xn||nd";
		String [] colList      = new String []{"bjdm","bz","cjcjhgl","cjcjyxl","nd","qjcjhgl","qjcjyxl","sbjx","xn","yyljhgl","yyljyxl","yysjhgl","yysjyxl","xxsh","xysh"}; 
		
		if(!pk.equalsIgnoreCase("")){
			inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		}else{
			colList = new String []{"bjdm","bz","cjcjhgl","cjcjyxl","nd","qjcjhgl","qjcjyxl","sbjx","xn","yyljhgl","yyljyxl","yysjhgl","yysjyxl"}; 
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public boolean updateXsjlsbJt(CommonModel myModel, String pk, HttpServletRequest request) throws Exception {
		//		 ѧ���Ƽ����������걨
		boolean inserted = true;
		String tableName     = "fzjy_xskjjljtsbb";
		String pkComment       = "bjdm||xn||nd";
		String [] colList      = new String []{"bjdm","bjjjsqsm","kcmc1","kcmc2","kcmc3","kcmc4","kcmc5","nd","pjf1","pjf2","pjf3","pjf4","pjf5","sljtgrs","xn","xqzxf","xxyj","xyyj","yxl1","yxl2","yxl3","yxl4","yxl5","zdf1","zdf2","zdf3","zdf4","zdf5","zgf1","zgf2","zgf3","zgf4","zgf5","xxsh","xysh"}; 
		
		if(!pk.equalsIgnoreCase("")){
			inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		}else{
			colList = new String []{"bjdm","bjjjsqsm","kcmc1","kcmc2","kcmc3","kcmc4","kcmc5","nd","pjf1","pjf2","pjf3","pjf4","pjf5","sljtgrs","xn","xqzxf","xxyj","xyyj","yxl1","yxl2","yxl3","yxl4","yxl5","zdf1","zdf2","zdf3","zdf4","zdf5","zgf1","zgf2","zgf3","zgf4","zgf5"}; 
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public ArrayList<String[]> getJtsqshList(CommonModel myModel, String tableName) {
		// ��ȡ������������б�
		String [] colList = new String []{}; 
		if(tableName.equalsIgnoreCase("view_wspyxjjt")){
			colList = new String []{"pk","bjmc","nj","xymc","zymc","xysh","xxsh"};
		}else if(tableName.equalsIgnoreCase("view_xsjlsbjt")){
			colList = new String []{"pk","bjmc","nj","xymc","zymc","xysh","xxsh"};
		}
		String xydm = myModel.getXydm();
		String zydm = myModel.getZydm();
		String bjdm = myModel.getBjdm();
		String nj   = myModel.getNj();
		String xysh = myModel.getXysh();
		String xxsh = myModel.getXxsh();
		String nd = myModel.getNd();
		String xn = myModel.getXn();
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"","","",nj,nd,"",xn));
		if(xysh != null && !("".equalsIgnoreCase(xysh.trim()))){
			query.append(" and xysh = '");
			query.append(xysh);
			query.append("' ");
		}
		if(xxsh != null && !("".equalsIgnoreCase(xxsh.trim()))){
			query.append(" and xxsh = '");
			query.append(xxsh);
			query.append("' ");
		}
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getJtsqshTopTr(String tableName) {
		// ��ȡ����������˱�ͷ
		DAO dao = DAO.getInstance();
	    String [] colList = new String []{}; 
	    if(tableName.equalsIgnoreCase("view_wspyxjjt")){
			colList = new String []{"pk","bjmc","nj","xymc","zymc","xysh","xxsh"};
		}else if(tableName.equalsIgnoreCase("view_xsjlsbjt")){
			colList = new String []{"pk","bjmc","nj","xymc","zymc","xysh","xxsh"};
		}
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
		return topTr;
	}
	
	public HashMap<String, String> getJtsbshOne(String pk, String tableName) {
		//	�����걨���
		String [] colList = new String []{};
		String pkCol = "pk"; 
		HashMap<String, String> rs = new HashMap<String, String>();
		if(tableName.equalsIgnoreCase("view_wspyxjjt")){
			colList = new String []{"bjdm","bjmc","bz","cjcjhgl","cjcjyxl","nd","nj","pk","qjcjhgl","qjcjyxl","sbjx","xn","xxsh","xydm","xymc","xysh","yyljhgl","yyljyxl","yysjhgl","yysjyxl","zydm","zymc"};
			rs = dao.sxjyQueryOne(tableName, colList, pkCol, pk);
		}else if(tableName.equalsIgnoreCase("view_xsjlsbjt")){
			colList = new String []{"bjdm","bjjjsqsm","bjmc","kcmc1","kcmc2","kcmc3","kcmc4","kcmc5","nd","nj","pjf1","pjf2","pjf3","pjf4","pjf5","pk","sljtgrs","xn","xqzxf","xxsh","xxyj","xydm","xymc","xysh","xyyj","yxl1","yxl2","yxl3","yxl4","yxl5","zdf1","zdf2","zdf3","zdf4","zdf5","zgf1","zgf2","zgf3","zgf4","zgf5","zydm","zymc"};
			rs = dao.sxjyQueryOne(tableName, colList, pkCol, pk);
			String bjdm = rs.get("bjdm");
			String sql   =  "select count(*) bjrs,bjdm,bjmc,zydm,zymc,xydm,xymc,nj,(select xm from view_bjgbxx where bjdm = a.bjdm and bjgbmc = '�೤') xm  from view_xsjbxx a where bjdm = ? group by bjdm,bjmc,zydm,zymc,xydm,xymc,nj";
			colList = new String []{"bjrs","bjdm","zydm","bjmc","zymc","xydm","xymc","xm","nj"};
			rs = dao.sxjyQueryOne3("", colList, "", bjdm, rs, sql);
		}
		return rs;
	}

	public ArrayList<String[]> getFdyxgxxList(CommonModel myModel, String tableName) {
		// ���ʴ�ѧ��ø���Ա�����Ϣ
		String [] colList = new String []{}; 
		if(tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
			colList = new String []{"pk","xm","cjr","fwxx","fwxxlxr","fwxxlxrlxfs"};
		}else if (tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
			colList = new String []{"pk","xm","bmmc","cjr","qj","zt","mc"};
		}
		String xm = myModel.getXm();
		String bmmc = DealString.toGBK(myModel.getBmmc());
		StringBuffer query = new StringBuffer(" where 1=1 ");
		if(xm != null && !("".equalsIgnoreCase(xm.trim()))){
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%' ");
		}
		if(bmmc != null && !("".equalsIgnoreCase(bmmc.trim()))){
			query.append(" and bmdm = '");
			query.append(bmmc);
			query.append("' ");
		}
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getFdyxgxxTopTr(String tableName) {
		//		 ѧ���ɲ�������˱�ͷ
	    DAO dao = DAO.getInstance();
	    String [] colList = new String []{};
	    if(tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
			colList = new String []{"pk","xm","cjr","fwxx","fwxxlxr","fwxxlxrlxfs"};
		}else if (tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
			colList = new String []{"pk","xm","bmmc","cjr","qj","zt","mc"};
		}
		String[] colListCN     = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
		return topTr;
	}
	
	public HashMap<String, String> getFdyxgxxOne(String pk, String tableName) {
		// ����Ա�����Ϣ������ʾ
		String [] colList = new String []{};
		String pkCol = "pk"; 
	    if(tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
			colList = new String []{"bmdm","bmmc","cjr","mc","pk","qj","xm","zgh","zt","fssj"};
		}else if (tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
			colList = new String []{"bmdm","bmmc","bz","cjr","fwsj","fwxx","fwxxgzld","fwxxlxr","fwxxlxrlxfs","pk","xm","zgh"};
		}
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, pkCol, pk);
		return rs;
	}

	public Object getBmList() {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		return dao.getBmListAll();
	}
	
	public List<HashMap<String, String>> getFdyList(String bmdm){
		return dao.getFdyList(bmdm);
	}
	
	public boolean updataFdyxgxx(CommonModel myModel, String pk, HttpServletRequest request) throws Exception {
		//�����¸���Ա�����Ϣ		
		String tableName = DealString.toGBK(myModel.getTableName());
		String [] colList = new String []{};
		String pkComment = ""; 
		String realTable = "";
		if(tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
			realTable = "fzjy_fdyjnds";
			pkComment= "zgh||qj||zt"; 
			colList = new String []{"zgh","qj","cjr","mc","zt","fssj"};
		}else if (tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
			realTable = "fzjy_fdymxfw";
			pkComment = "zgh||fwxx||fwsj";
			colList = new String []{"bz","cjr","fwsj","fwxx","fwxxgzld","fwxxlxr","fwxxlxrlxfs","zgh"};
		}
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted  =  StandardOperation.delete(realTable, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(realTable, colList, inputList, request);
		}
		return inserted;
	}

	public boolean delFdyxgxx(String pk, String tableName, HttpServletRequest request) throws Exception {
		//ɾ������Ա�����Ϣ		
		String pkComment = ""; 
		String realTable = "";
		if(tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
			realTable = "fzjy_fdyjnds";
			pkComment= "zgh||qj||zt"; 
		}else if (tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
			realTable = "fzjy_fdymxfw";
			pkComment = "zgh||fwxx||fwsj";
		}
		boolean inserted  =  StandardOperation.delete(realTable, pkComment,pk, request);
		return inserted;
	}
	
	public HashMap<String, String> getFdyzpxxOne(String pk) {
		// ����Ա�����Ϣ������ʾ
		String [] colList = new String []{};
		String tableName = "view_fdyzpxx";
		String pkCol = "zgh||xn||nd||xq"; 
		colList = new String []{"addfsm","bmmc","delfsm","fzgz","fzrs","grndxj","nd","tsgz","xm","xn","xq","zgh"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, pkCol, pk+Base.currXn+Base.currNd+Base.currXq);
		tableName = "view_fdyxx";
		pkCol = "zgh"; 
		colList = new String []{"zgh","xm","bmmc"};
		rs = dao.sxjyQueryOne3(tableName, colList, pkCol, pk, rs, "");
		return rs;
	}

	public boolean updataFdyzpxx(CommonModel myModel, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		String [] colList = new String []{};
		String pkComment = ""; 
		String realTable = "";
		realTable = "szdw_fdyzpxxb";
		pkComment = "zgh||xn||nd||xq"; 
		String zgh = DealString.toGBK(myModel.getZgh());
		myModel.setXn(Base.currXn);
		myModel.setNd(Base.currNd);
		myModel.setXq(Base.currXq);
		colList = new String []{"addfsm","delfsm","fzgz","fzrs","grndxj","nd","tsgz","xn","xq","zgh"};
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted  =  StandardOperation.delete(realTable, pkComment,zgh+Base.currXn+Base.currNd+Base.currXq, request);
		if(inserted){
			inserted = StandardOperation.insert(realTable, colList, inputList, request);
		}
		return inserted;
	}

	public HashMap<String, String> getFdybjjcsz(){
		String tableName ="fdybjcsszb";
		return CommonQueryDAO.commonQueryOne(tableName, new String[]{"fdybjsz","bzrbjsz"}, "1", "1");
	}

	public boolean updataFdybjjcszSave(String fdybjsz, String bzrbjsz, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return dao.updataFdybjjcszSave(fdybjsz,bzrbjsz,request);
	}
	
	public String getFpfs(String fplx){
		String tableName ="fdybjcsszb";
		HashMap<String, String> rs = CommonQueryDAO.commonQueryOne(tableName, new String[]{"fdybjsz","bzrbjsz"}, "1", "1");
		if(fplx.equalsIgnoreCase("fdy")){
			return rs.get("fdybjsz");
		}else{
			return rs.get("bzrbjsz");
		}
	}

	public boolean delAllFpbj(String type) throws Exception {
		// TODO �Զ����ɷ������
	    return dao.delAllFpbj(type); 
	}
	
	/**
	 * �ӿ� ����ȡ���༶��༶����ѧ���ɲ�����
	 * @return
	 * @throws Exception
	 */
	public String getBjgbRsForBjdm(String bjdm) throws Exception {
		// ����dao���һ������
	    return dao.getBjgbRsForBjdm(bjdm); 
	}
	
	/**
	 * ת�Ƶ���ʷ��
	 * @return
	 */
	public boolean putLsjl() {
		// ת�Ƶ���ʷ��
		boolean update = dao.putFdyLsjl();
		if(update){
			update =  dao.putBzrLsjl();
		}
		return update;
	}
	
	/**
	 * ��ò�������List
	 * @return
	 */
	public List<HashMap<String, String>> getCpdxList() {
		DAO commonDAO = DAO.getInstance();
		String sql = "select b.PFBZ,b.QZ from pjqtb a,pfbzb b where a.PFBZ= b.BZBH and a.qtmc = 'ѧ��' order by b.xssx";
		return commonDAO.getList(sql, new String[]{}, new String []{"pfbz","qz"});
	}

	/**
	 * ��ȡ��������
	 * @param cpdxList
	 * @return
	 * @throws Exception 
	 */
	public void getPjjgForGzListPrint(List<HashMap<String, String>> cpdxList, OutputStream os) throws Exception {
		
		//		���������Ȩ�ط���
		int cpdxSize = cpdxList.size();
		String [] qzList = new String[cpdxSize];
		//������������۱�׼����
		String [] pjbzList = new String[cpdxSize];
		for(int i = 0;i<cpdxSize;i++){
			HashMap<String, String> cpdx = cpdxList.get(i);
			qzList[i] = cpdx.get("qz");
			pjbzList[i] = cpdx.get("pfbz");
		}
		//��ǰ��ѧ��,ѧ�ڣ����
		String xn = Base.currXn;
		String nd = Base.currNd;
		String xq = Base.currXq;
		//eΪ����,cΪ����Ա��Ϣ��aΪ���ֱ�
		StringBuffer sql = new StringBuffer("select c.xm,c.bmmc,c.zwmc,c.zwrzsj,");
		//ѧ������������
		sql.append("(select count(1) from (select distinct xh,jszgh ");
		sql.append(" from xspfb a where xn = '");
		sql.append(xn);
		sql.append("' and nd = '");
		sql.append(nd);
		sql.append("' and xq = '");
		sql.append(xq);
		sql.append("' and not exists (select 1 from yhb where yhm = a.xh)) a where a.jszgh =e.zgh group by jszgh) sum, ");
		
		//�������������ӵ�sql���
		for(int i = 0;i<cpdxSize;i++){
			sql.append(" (select round(count(1) / (select count(1) from pjzbb where mxdx = '6727'),2)");
			sql.append(" pjz from xspfb a where not exists (select 1 from yhb where yhm = a.xh)");
			sql.append(" and pjfs = '");
			sql.append(qzList[i]);
			sql.append("' and xn = '"); 
			sql.append(xn);
			sql.append("' and nd = '");
			sql.append(nd);
			sql.append("' and xq = '");
			sql.append(xq);
			sql.append("' and a.jszgh = e.zgh group by jszgh) ");
			sql.append(pjbzList[i]);
			sql.append(",");
		}
		
		//��ʦ�����ɼ�
		sql.append("(select round(sum(a.PJFS * b.PFBL * c.fz) / (count(distinct a.xh)), 2) pjfs ");
		sql.append("from xspfb a,(select b.PFBL, b.qtmc from pjqtb b where b.qtdm = '999999') b,");
	    sql.append("pjzbb c where a.xn ='");
	    sql.append(xn);
		sql.append("' and a.nd = '");
		sql.append(nd);
		sql.append("' and a.xq = '");
		sql.append(xq);
		sql.append("' and a.jszgh = e.zgh and a.xh = e.zgh  and c.xh = a.pjzbxh ) zpcj, ");  
	    
	    //ѧԺ�����ֳɼ�
	    sql.append("(select round(sum(a.PJFS * b.PFBL * c.fz) / (count(distinct a.xh)), 2) pjfs ");  
	    sql.append(" from xspfb a,(select b.PFBL, c.yhm, c.zdm, b.qtmc from yhzb a, pjqtb b, yhb c" );
	    sql.append(" where a.zdm = b.qtdm and c.zdm = a.zdm and b.khqzdm = '006') b, pjzbb c where a.xn ='");
	    sql.append(xn);
		sql.append("' and a.nd = '");
		sql.append(nd);
		sql.append("' and a.xq = '");
		sql.append(xq);
	    sql.append("' and a.jszgh = e.zgh and a.xh = b.yhm and c.xh = a.pjzbxh  and a.xh != a.jszgh) xycj ");
	 
	    //��������
		sql.append(" from (select * from fdypfb where xn ='");
		sql.append(xn);
		sql.append("' and nd = '");
		sql.append(nd);
		sql.append("' and xq = '");
		sql.append(xq);
		sql.append("') e left join view_fdyxx c on e.zgh = c.zgh ");
		
		DAO commonDAO = DAO.getInstance();
		//���ֵ
		String [] outputValue = new String [cpdxSize+7];
		outputValue[0]="xm";
		outputValue[1]="bmmc";
		outputValue[2]="zwmc";
		outputValue[3]="zwrzsj";
		outputValue[4]="sum";
		for(int i = 0;i<cpdxSize;i++){
			outputValue[5+i]= pjbzList[i];
		}
		outputValue[5+cpdxSize]= "zpcj";
		outputValue[6+cpdxSize]= "xycj";
		ArrayList<String[]> rs = commonDAO.rsToVator(sql.toString(), new String[]{}, outputValue);
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("ѧ�긨��Ա��ȿ��������", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat wcf3 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ������
//		 ������
		ExcelMB ex = new ExcelMB();
		String title = "ѧ�긨��Ա��ȿ��������";
		ws.mergeCells(0, 0, 10 + cpdxSize, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
//		 --------------��һ��----------------
		ws.addCell(new Label(0, 2, "����", wcf3));
		ws.mergeCells(0, 2, 0, 3);
		ws.addCell(new Label(1, 2, "��λ", wcf3));
		ws.mergeCells(1, 2, 1, 3);
		ws.addCell(new Label(2, 2, "����", wcf3));
		ws.mergeCells(2, 2, 2, 3);
		ws.addCell(new Label(3, 2, "��ְʱ��", wcf3));
		ws.mergeCells(3, 2, 3, 3);
		ws.addCell(new Label(4, 2, "����Ȳ������", wcf3));
		ws.mergeCells(4, 2, 4+cpdxList.size(), 2);
		ws.addCell(new Label(4, 3, "����", wcf3));
		for(int i = 0;i<pjbzList.length;i++){
			ws.addCell(new Label(5+i, 3, pjbzList[i], wcf3));
		}
		ws.addCell(new Label(5+pjbzList.length, 2, "������", wcf3));
		ws.mergeCells(5+pjbzList.length, 2, 5+pjbzList.length, 3);
		ws.addCell(new Label(6+pjbzList.length, 2, "Ժϵ����", wcf3));
		ws.mergeCells(6+pjbzList.length, 2, 6+pjbzList.length, 3);
		ws.addCell(new Label(7+pjbzList.length, 2, "����С�鿼�����", wcf3));
		ws.mergeCells(7+pjbzList.length, 2, 10+pjbzList.length, 2);
		ws.addCell(new Label(7+pjbzList.length, 3, "����", wcf3));
		ws.addCell(new Label(8+pjbzList.length, 3, "��ְ", wcf3));
		ws.addCell(new Label(9+pjbzList.length, 3, "������ְ", wcf3));
		ws.addCell(new Label(10+pjbzList.length, 3, "����ְ", wcf3));
		for (int i = 0; i < rs.size(); i++) {
			String[] tmp = (String[]) rs.toArray()[i];
			for (int j = 0; j < tmp.length; j++) {
				ws.addCell(new Label(j, i + 4, tmp[j]));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���ѧ��ͶƱ��Ϣ
	 * @param myForm
	 * @return
	 */
	public ArrayList<String[]> getXstpxx(FdyglForm myForm){
		MakeQuery makeQuery = new MakeQuery();
		String xn = myForm.getXn();
		String nd = myForm.getNd();
		String xq = myForm.getXq();
		String sftp = myForm.getSftp();
		String bjdm = myForm.getBj();
		String[] colList = new String []{"xydm","zydm","nj"};
		String[] colLikeList = new String[]{"a.xh","xm","zgh","fdyxm"};
		try {
			makeQuery.makeQuery(colList, colLikeList, myForm);
		} catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
		}
		String queryString = makeQuery.getQueryString();
		
		if(("��ͶƱ").equalsIgnoreCase(sftp)){
			queryString +=" and sftp = '��ͶƱ' ";
		}else if(("δͶƱ").equalsIgnoreCase(sftp)){
			queryString +=" and sftp is null ";
		}
		if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
			queryString +=" and a.bjdm = '";
			queryString +=bjdm;
			queryString +="' ";
		}
		//ƴ��sql
		StringBuffer sqlBuffer = new StringBuffer("select rownum r,a.xh,a.xm,a.xymc,a.bjmc,");
		sqlBuffer.append("a.lxdh,a.fdyxm,nvl(b.sftp,'δͶƱ') sftp from (select a.xh,a.xm,");
		sqlBuffer.append("a.xymc,a.bjmc,a.bjdm,a.zydm,a.xydm,a.nj,a.lxdh,c.zgh,c.xm fdyxm from view_xsjbxx a,");
		sqlBuffer.append("view_fdybbj c where a.bjdm = c.bjdm order by xh ) a left join ");
		sqlBuffer.append("(select distinct xh, xn, nd, xq,jszgh,'��ͶƱ' sftp from xspfb a where ");
		sqlBuffer.append("not exists (select 1 from yhb where yhm = a.xh) and xn = '");
		sqlBuffer.append(xn);
		sqlBuffer.append("' and nd = '");
		sqlBuffer.append(nd);
		sqlBuffer.append("' and xq = '");
		sqlBuffer.append(xq);
		sqlBuffer.append("') b on a.xh = b.xh and a.zgh = b.jszgh ");
		
		String[] returnList = new String[]{"xh","xm","xymc","bjmc","lxdh","fdyxm","sftp"};
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		try {
			rs = CommonQueryDAO.commonQuery(sqlBuffer.toString(), queryString,
					makeQuery.getInputList(), returnList,myForm);
		} catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * ���ѧ��ͶƱ��Ϣ��ͷ
	 * @return
	 */
	public List getXstpxxTop() {
		DAO dao = DAO.getInstance();
		String[] colList = new String[]{"xh","xm","xymc","bjmc","lxdh","fdyxm","sftp"};
		String[] colListCn = new String[]{"ѧ��","����",Base.YXPZXY_KEY+"����","�༶����","��ϵ�绰","����Ա����","�Ƿ�ͶƱ"};
		return dao.arrayToList(colList, colListCn);
	}
	
	/**
	 * ����ְ���Ż�ò��Ŵ���
	 * @return
	 */
	public String getBmdmForZgh(String zgh) {
		DAO dao = DAO.getInstance();
		String sql = "select bmdm from fdyxxb where zgh = ?";
		return dao.getOneRs(sql, new String[]{zgh}, "bmdm");
	}
}
