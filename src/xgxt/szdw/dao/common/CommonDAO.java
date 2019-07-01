package xgxt.szdw.dao.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.form.common.CommonForm;
import xgxt.utils.CommonQueryDAO;

/**
* <p>Title: ѧ������ϵͳ</p>
* <p>Description: ѧ����Ϣ����˼������ͨ��DAO��</p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: zfsoft</p>
* @author ³��
* @version 1.0
*/

public class CommonDAO  {
	DAO dao = DAO.getInstance();
	public List<HashMap<String, String>> getCommonList(String tableName,String dm,String mc,String query) {
		// ��ȡ�б�ͨ�÷���
		// query���String ��������ݴ�����ʱҪ��" " 
		StringBuffer sqlBuffer = new StringBuffer("select ");
		sqlBuffer.append(dm);
		sqlBuffer.append(",");
		sqlBuffer.append(mc);
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(query);
		return dao.getList(sqlBuffer.toString(), new String[] {}, new String[] { dm, mc });
	}
	
	public List<HashMap<String, String>> getFdyList(String xydm) {
		// ��ȡְ���б�
		if(xydm==null){
			xydm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' '  ";
		return dao.getList(sql, new String[] {xydm,xydm+" "}, new String[] { "zgh", "xm" });
	}
	
	public List<HashMap<String, String>> getFdyList2(String xydm,String zgh) {
		// ��ȡְ���б�
		if(xydm==null){
			xydm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' ' and " +
				"exists (select bmdm from view_fdyxx where zgh = ? or ? = ' ')";
		return dao.getList(sql, new String[] {xydm,xydm+" ",zgh,zgh+" "}, new String[] { "zgh", "xm" });
	}

	public List<HashMap<String, String>> getTutorshippersonList(String userDep) {
		// ��ȡ����Ա��Ϣ
		String sql = "select zgh,xm from fdyxxb where (bmdm = ? or ? = ' ') order by zgh";
		return dao.getList(sql, new String[] {userDep,userDep+" "}, new String[] { "zgh", "xm" });
	}

	public Object getClassDutyList() {
		//��ȡ��ɲ�ְ������
		String sql = "select bjgbdm,bjgbmc from sxjy_bjgbzlb order by bjgbdm";
		return dao.getList(sql, new String[] {}, new String[] { "bjgbdm", "bjgbmc" });
	}
	
	public List<HashMap<String, String>> getChkList(int type) {
		// ��˽��������
		String[] chkList = null;
		if (type == 1) {
			chkList = new String[] { "�����", "δ���" };
		} else if (type == 2) {
			chkList = new String[] { "��", "��" };
		} else if (type == 3) {
			chkList = new String[] { "δ���", "ͨ��", "��ͨ��" };
		} else if(type ==4) {
			chkList = new String[] { "�ɹ�", "ʧ��" };
		} else if(type ==5) {
			chkList = new String[] { "δ���", "����", "һ������","��ͨ��" };
		} else if(type ==6) {
			chkList = new String[] { "1", "2", "3","4","5","6","7","8","9","10","11","12" };
		}else if(type ==7) {
			chkList = new String[] { "����", "��̸", "����","�ݽ�","�ι�","����" };
		}else if(type ==8) {
			chkList = new String[] { "����潻��", "����ת��", "�绰��ϵ","�ź�","�����ʼ�","�ֻ�����" };
		}else if(type ==9) {
			chkList = new String[] { "��������","1����֮��", "2����֮��", "3����֮��","����֮��","һ��֮��","����֮��" };
		}else if(type ==10) {
			chkList = new String[] { ">", "=","<" };
		}
		return dao.arrayToList(chkList, chkList);
	}
	
	
	public String[] getFdyBjList(String pk){
    //    ��ð༶����Ա�б�
		String sql = "select bjmc from fdybjb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk}, "bjmc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getZyBjList(String pk){
    //    ��ð༶����Ա����רҵ
		String sql = "select distinct zymc from fdybjb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk}, "zymc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getSfList() {
    //    ���ʡ���б�
		String sql = "select sfdm,sfmc from sfdmdzb ";
		return dao.getList(sql,new String[]{},new String[]{"sfdm","sfmc"});
	}
	
	
	public ArrayList<String[]> sxjyQuery(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
    //    ��ѯ�� ��������ͨ�÷���
		ArrayList<String[]> rs = null;
		//ȡ��colList�ĳ���
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	public HashMap<String, String[]> sxjyQueryForHashMap(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
    //    ��ѯ�� ���HashMap<String, String[]>��ʽ��ͨ�÷���
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		//    ȡ��colList�ĳ���
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = dao.getHashMapList(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.getHashMapList(sql, inPutList, colList);
		}
		return rs;
	}
	
	
	public List<HashMap<String, String>> sxjyQueryforList(String tableName,String query,String [] inPutList,
			String [] colList,String sql) {
    //    ��ѯ�� ���List<HashMap<String, String>>��ʽ��ͨ�÷���
		List<HashMap<String, String>> rs;
		int size = colList.length-1;
        //    ȡ��colList�ĳ���
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			 rs = dao.getList(sqlBuffer.toString(), inPutList, colList);
		}else{
			 rs = dao.getList(sql, inPutList, colList);
		}
		return rs;
	}
	
	
	public HashMap<String, String> sxjyQueryOne(String tableName,String [] colList,String pk,String pkValue) {
    //    ͨ��������ѯ���������� ���HashMap<String, String>��ʽ��ͨ�÷���
		HashMap<String, String> map = new HashMap<String, String>();
		int size = colList.length-1;
		if(pkValue.equalsIgnoreCase("")){
			for(int i=0;i<size;i++){
				map.put(colList[i], "");
			}
		}else{
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<size;i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			map = dao.getMap(sqlBuffer.toString(), new String []{ pkValue }, colList);
		}
		return map;
	}
	
	public ArrayList<String[]> sxjyQueryCx(String tableName,String query,String [] inPutList,
			String [] colList,String sql,CommonForm myForm,String[]colsList) throws Exception{
    //    ��ѯ�� ��������ͨ�÷���
		ArrayList<String[]> rs = null;
		//ȡ��colList�ĳ���
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select rownum r ,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = CommonQueryDAO.commonQuery(sqlBuffer.toString(), "", inPutList, colsList, myForm);
		}else{
			rs = CommonQueryDAO.commonQuery(sql, "", inPutList, colsList, myForm);
		}
		return rs;
	}
	
	public HashMap<String, String> sxjyQueryOne3(String tableName,String [] colList,String pk,
			String pkValue,HashMap<String, String> map,String sql) {
    //    ͨ��������ѯ���������� ���HashMap<String, String>��ʽ,������֮ǰ�����HashMap���ֵ��ͨ�÷���
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
		if(pkValue.equalsIgnoreCase("")){
			for(int i=0;i<size;i++){
				map.put(colList[i], "");
			}
		}else{
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<size;i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		}else{
			String [] rsTmp = dao.getOneRs(sql, new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		return map;
	}
	
	public String[] sxjyQueryOne2(String tableName,String [] colList,String pk,String pkValue) {
    //    ͨ��������ѯ���������� ���HashMapString[]��ʽ��ͨ�÷���
		int size = colList.length-1;
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<(size);i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
		}
		sqlBuffer.append(colList[size]);
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName); 
		sqlBuffer.append(" where ");
		sqlBuffer.append(pk);
		sqlBuffer.append("=?");
		String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
		return rsTmp;
	}
	
	public synchronized String[] getViewComm(String viewName) throws SQLException {
    //    �õ���ͼ���ֶ�ע�����
		DAO dao = DAO.getInstance();
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||" +
				"chr(39) com from user_col_comments where table_name=upper('"
				+ viewName
				+ "')";
		arr = dao.getArray(sql, new String[] {}, "com");
		return arr;
	}
	
	
	public boolean getSffdy(String zgh) throws SQLException {
    //    �ж��Ƿ��ǽ�ʦ�ķ���
		HashMap<String, String> map =sxjyQueryOne("fdyxxb", new String[]{"zgh"}, "zgh",zgh);
		if(map.get("zgh").equalsIgnoreCase("")){//����Ǹ���Ա�������ø���Ա��־
			return false;
		} else {
			return true;
		}
	}
	
	public List getZwList() throws SQLException {
	//    ���ְ���б�ķ���
		String sql = "select zwdm,zwmc from zwb";
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm","zwmc" });
		return zwList;
	}

	public Object getBjxsList(String bj) {
		// ��øð༶ѧ���б�
		String sql = "select xh,xm from view_xsjbxx where bjdm = ?";
		List zwList = dao.getList(sql, new String[] {bj}, new String[] { "xh","xm" });
		return zwList;
	}

	public List getSercicesbxskjcglxList() {
		// ����걨ѧ���Ƽ��ɹ������б�
		String sql = "select sbxskjcglxdm,sbxskjcglxmc from fzjy_sbxskjcglxdmb";
		List sercicesbxskjcglxList = dao.getList(sql, new String[] {}, new String[] { "sbxskjcglxdm","sbxskjcglxmc" });
		return sercicesbxskjcglxList;
	}

	public List getCgjbList() {
		// ����걨ѧ���Ƽ��ɹ��������
		String sql = "select cgjbdm,cgjbmc from fzjy_cgjb";
		List cgjbList = dao.getList(sql, new String[] {}, new String[] { "cgjbdm","cgjbmc" });
		return cgjbList;
	}
	
	public List getStlbList() {
		// ����Ա�������������б�
		String sql = "select stlbdm,stlbmc from szdw_fdykhstdlb";
		List stlblist = dao.getList(sql, new String[] {}, new String[] { "stlbdm","stlbmc" });
		return stlblist;
	}

	public List getKhqzList() {
		// ��������Ⱥ���б�
		String sql = "select qzdm,qzmc from szdw_fdykhqzb";
		List stlblist = dao.getList(sql, new String[] {}, new String[] { "qzdm","qzmc" });
		return stlblist;
	}
	
	public String [] getKhqzs() throws SQLException {
		// ��������Ⱥ���б�
		String sql = "select qzmc from szdw_fdykhqzb";
		String [] stlblist = dao.getArray(sql,new String []{},"qzmc");
		return stlblist;
	}

	public boolean updataFdybjjcszSave(String fdybjsz, String bzrbjsz, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return StandardOperation.update("fdybjcsszb", new String[]{"fdybjsz","bzrbjsz"}, new String[]{fdybjsz,bzrbjsz}, "1", "1", request);
	}

	public boolean delAllFpbj(String type) throws Exception {
		// TODO �Զ����ɷ������
		String sql = "";;
		if(type.equalsIgnoreCase("fdy")){
			sql = "delete from fdybjb";
		}else{
			sql = "delete from bzrbbb";
		}
		return dao.runUpdate(sql, new String[]{});
	}

	/**
	 * ȡ���༶��༶����ѧ���ɲ�����
	 * @return
	 * @throws Exception
	 */
	public String getBjgbRsForBjdm(String bjdm) {
		String sql = "select count(0) num from sxjy_bjgbxxb a where bjdm = ? and exists (select 1 from (select bjgbdm from "+
				"sxjy_bjgbzlb b where not exists (select 1 from bjgbzljbb c where jbmc = '�༶' and b.gbzljb = c.jbdm)) d"+
				" where a.BJGBDM = d.BJGBDM)";
		return dao.getOneRs(sql, new String []{bjdm}, "num");
	}

	/**
	 * ������Ա��ʷ��¼
	 * @return
	 * @throws Exception 
	 */
	public boolean putFdyLsjl(){
		String xn = Base.currXn;
		String sql = "delete from fdybjlsb where xn = ?";
		boolean updata = false;
		try {
			updata = dao.runUpdate(sql,new String[]{xn});
		
			if(updata){
				sql = "insert into fdybjlsb select zgh,bjdm,'"+xn+"'from fdybjb";
				updata = dao.runUpdate(sql,new String[]{});
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return updata;
	}
	
	/**
	 * �����������ʷ��¼
	 * @return
	 */
	public boolean putBzrLsjl() {
		String xn = Base.currXn;
		String sql = "delete from bzrbjlsb where xn = ?";
		boolean updata = false;
		try {
			updata = dao.runUpdate(sql,new String[]{xn});
		
			if(updata){
				sql = "insert into bzrbjlsb select zgh,bjdm,'"+xn+"'from bzrbbb";
				updata = dao.runUpdate(sql,new String[]{});
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return updata;
	}
	
	/**
	 * ����ѧ����ø���Ա�����������
	 * @return
	 */
	public String[] getFdyBzrXmForXh(String xh,String type) {
		StringBuilder sqlBuilder = new StringBuilder("select xm from ");
		if(type.equalsIgnoreCase("fdy")){
			sqlBuilder.append(" view_fdybbj ");
		}else{
			sqlBuilder.append(" view_bzrbbj ");
		}
		sqlBuilder.append("a where exists (select 1 from view_xsjbxx b  where xh ");
		sqlBuilder.append(" = ? and a.bjdm = b.bjdm)");
		try {
			return dao.getArray(sqlBuilder.toString(),new String[]{xh},"xm");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
