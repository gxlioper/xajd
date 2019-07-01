
package xgxt.jxgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.ExcelMethods;
import xgxt.utils.SearchUtils;

public class JxglJzService {
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ�����ѵ����-2��Ժ-����ά��service��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	JxglDAO dao = new JxglDAO();
	
	public ArrayList<String[]> getJxjzForbjList(JxglJzModel myModel) throws SQLException {
	    //    �õ���ѵ�༶�����б�
			String tableName     = "view_jxglbz";
			String nd           = DealString.toGBK(myModel.getNd());
			String xydm         = DealString.toGBK(myModel.getXydm());
			String zydm         = DealString.toGBK(myModel.getZydm());
			String bjdm         = DealString.toGBK(myModel.getBjdm());
			String nj         = DealString.toGBK(myModel.getNj());
			StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"","","",nj,nd,"",""));
			String [] colList = new String []{"pk","bjmc","yjmc","ljmc","ljxb","yjjdy"}; 
			ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
			return rs;
	}
	
	public List getJxjzTopTr() {
		//    �õ���ѵ�༶�����б��ͷ
		    DAO dao = DAO.getInstance();
			String tableName     = "view_jxglbz";
			String [] colList = new String []{"pk","bjmc","yjmc","ljmc","ljxb","yjjdy"}; 
			String[] colListCN     = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
			
			return topTr;
	}
	
	public boolean updataJxjz(JxglJzModel myModel,String pk, HttpServletRequest request) 
			throws Exception {
		//    ���浥����ѵ����
		String tableName       = "jxgljzwhb";
		String pkComment       = "bjdm||ljxb";
		String [] colList      = new String []{"yjmc","ljmc","ljxb","yzmc","yjjdy","lzmc","ljzdymc","jgmc","bjdm","nd"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean deleteJxjz(String pk, HttpServletRequest request) 
			throws Exception {
		//    ���浥����ѵ����
		String tableName       = "jxgljzwhb";
		String pkComment       = "bjdm||ljxb";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	
	public HashMap<String, String> getJxglJzFor2my(String pk) {
		// ���������õ���ѵ�༶������Ϣ
		String tableName    = "view_jxglbz";
		String [] colList = new String []{"nd","zydm","xydm","nj","zymc","xymc","pk","bjdm","yjmc","ljmc","yzmc","yjjdy","lzmc","ljzdymc","jgmc","ljxb","bjrs","bjmc"};
		
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		
		return rs;
	}

	public ArrayList<String[]> getXsjxList(JxglJzModel myModel) {
		// ��ѵ�����б��ѯ
		String tableName     = "view_xsjxxx";
		String xydm         = DealString.toGBK(myModel.getXydm());
		String xm           = DealString.toGBK(myModel.getXm());
		String zydm         = DealString.toGBK(myModel.getZydm());
		String bjdm         = DealString.toGBK(myModel.getBjdm());
		String nj         = DealString.toGBK(myModel.getNj());
		String xh         = DealString.toGBK(myModel.getXh());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(xydm,zydm,bjdm,"",xh,xm,nj,"","",""));
		String [] colList = new String []{"pk","xh","xm","mzmc","xymc","bjmc","ljmc","ljzdymc","jgmc"}; 
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
		return rs;
	}
	
	public List getXsjxTopTr() {
		//    �õ���ѵ�༶�����б��ͷ
		    DAO dao = DAO.getInstance();
			String tableName     = "view_xsjxxx";
			String [] colList = new String []{"pk","xh","xm","mzmc","xymc","bjmc","ljmc","ljzdymc","jgmc"}; 
			String[] colListCN     = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
			
			return topTr;
	}

	public HashMap<String, String> getJxglxsJzFor2my(String pk) {
		// �õ�����ѧ��������ϸ��Ϣ
		String tableName    = "view_xsjxxx";
		String [] colList = new String []{"pk","xh","xm","xb","mzmc","xymc","bjmc","ljmc","ljzdymc","jgmc","nj","zymc","yjmc","yzmc","yjjdy","lzmc"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}
	
	//��������
	public ArrayList<String[]> getLdjzList(JxglJzModel myModel) throws SQLException {
	    //    �õ���ѵ���ӽ����б�
			String tableName     = "jxlddmwhb";
			String nd           = DealString.toGBK(myModel.getNd());
			String xn         = DealString.toGBK(myModel.getXn());
			String xb         = DealString.toGBK(myModel.getXb());
			StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition("","","",xb,"","","",nd,"",xn));
			String [] colList = new String []{"ldbh","ldbh","ldmc","xb","zdy","jgmc"}; 
			ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
			return rs;
	}
	
	public ArrayList<String[]> getKqxxList(JxglJzModel myModel) throws SQLException {
	    //    �õ���ѵ������Ϣ�б�
			String tableName     = "view_jxkqxxb";
			String nd           = DealString.toGBK(myModel.getNd());
			String xn         = DealString.toGBK(myModel.getXn());
			String kqqk       = DealString.toGBK(myModel.getKqqk());
			StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition("","","","","","","",nd,"",xn));
			if(kqqk != null && !("".equalsIgnoreCase(kqqk.trim()))){
				query.append(" and kqdm='");
				query.append(kqqk);
				query.append("' ");
			}
			String [] colList = new String []{"pk","lssm","kqmc","fsrq","qxsj"}; 
			ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
			return rs;
	}
	
	public HashMap<String, String> getKqxxYnys(String pk) {
		//  �õ���ѵ������Ϣ
		String tableName    = "view_jxkqxxb";
		String [] colList = new String []{"pk","kqdm","lssm","fsrq","qxsj","bz"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		return rs;
	}
	
	public List getLdjzTopTr() {
		//    �õ���ѵ���ӽ����б��ͷ
		    DAO dao = DAO.getInstance();
			String tableName     = "jxlddmwhb";
			String [] colList = new String []{"ldbh","ldbh","ldmc","xb","zdy","jgmc"}; 
			String[] colListCN     = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
			return topTr;
	}
	
	public List getKqxxTopTr() {
		//    �õ���ѵ���ӽ����б��ͷ
		    DAO dao = DAO.getInstance();
		    String tableName     = "view_jxkqxxb";
		    String [] colList = new String []{"pk","lssm","kqmc","fsrq","qxsj"}; 
			String[] colListCN     = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
			return topTr;
	}
	
	public HashMap<String, String> getJxglldwhYnys(String pk) {
		// ���������õ���ѵ�༶������Ϣ
		String tableName    = "jxlddmwhb";
		String [] colList = new String []{"ldbh","ldbh","ldmc","xb","zdy","jgmc","xn","nd","bz"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "ldbh", pk);
		return rs;
	}
	
	public boolean updataLdjz(JxglJzModel myModel,String pk, HttpServletRequest request) 
			throws Exception {
		//    ���浥����ѵ����
		String tableName       = "jxlddmwhb";
		String pkComment       = "ldbh";
		String [] colList = new String []{"ldbh","ldmc","xb","zdy","jgmc","xn","nd","bz"};
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean deleteJxLdjz(String pk, HttpServletRequest request) 
		    throws Exception {
		//    ɾ��������ѵ����
		String tableName       = "jxlddmwhb";
		String pkComment       = "ldbh";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	
	public boolean deleteKqxx(String pk, HttpServletRequest request) 
			throws Exception {
		//    ɾ������������Ϣ
		String tableName       = "jxkqxxb";
		String pkComment       = "lssm||kqdm||fsrq";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	
	public boolean updataKqxx(JxglJzModel myModel,String pk, HttpServletRequest request) 
			throws Exception {
			//    ���浥��������Ϣ
		String tableName       = "jxkqxxb";
		String pkComment       = "lssm||kqdm||fsrq";
		String [] colList = new String []{"kqdm","lssm","fsrq","qxsj","bz","nd","xn"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
}

	public List<HashMap<String, String>> getKqqkList() {
		// TODO �Զ����ɷ������
		return dao.getJxqkList();
	}

	public ArrayList<String[]> getZxsjList(JxglJzModel myModel) {
		//�õ���ѵ��Ϣ��Ϣ�б�
		String tableName     = "jxglzxsj";
		String nd           = DealString.toGBK(myModel.getNd());
		String xn         = DealString.toGBK(myModel.getXn());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition("","","","","","","",nd,"",xn));
		query.append(" order by ksxs,ksfz");
		String [] colList = new String []{"pk","kssj","jssj","zxap"}; 
		String sql = "select xn||nd||ksxs||ksfz pk,ksxs||'ʱ'||ksfz||'��' kssj,jsxs||'ʱ'||jsfz||'��' jssj,zxap from jxglzxsj";
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, sql);
		return rs;
	}
	
	public List getZxsjTopTr() {
		//    �õ���ѵ��Ϣ��Ϣ�б��ͷ
		    DAO dao = DAO.getInstance();
		    String [] colList = new String []{"pk","kssj","jssj","zxap"}; 
			String[] colListCN     = new String [] {"����","��ʼʱ��","����ʱ��","��Ϣ����"};
			List topTr = dao.arrayToList(colList, colListCN);//��ͷ 
			return topTr;
	}
	
	public HashMap<String, String> getZxsjYnys(String pk) {
		//  �õ���ѵ��Ϣ��Ϣ
		String tableName     = "jxglzxsj";
		String [] colList = new String []{"xn","nd","ksxs","ksfz","jsxs","jsfz","zxap","jtsm","bz"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "xn||nd||ksxs||ksfz", pk);
		return rs;
	}
	
	public boolean updataZxsj(JxglJzModel myModel,String pk, HttpServletRequest request) 
		throws Exception {
		//    ���浥����ѵ��Ϣ��Ϣ
		String tableName       = "jxglzxsj";
		String pkComment       = "xn||nd||ksxs||ksfz";
		String [] colList = new String []{"xn","nd","ksxs","ksfz","jsxs","jsfz","zxap","jtsm","bz"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}
	
	public boolean deleteZxsj(String pk, HttpServletRequest request) 
		throws Exception {
		//    ɾ��������ѵ��Ϣ��Ϣ
		String tableName       = "jxglzxsj";
		String pkComment       = "xn||nd||ksxs||ksfz";
		boolean del = StandardOperation.delete(tableName, pkComment,pk, request);
		return del;
	}
	
	public String getNblgJxjz(String nj) throws SQLException {
	    //    �õ���ѵ�༶�����б�
			String sT = dao.getNblgJxjz(nj);
			return sT;
	}
	
	public String getXbmzJxjz(String nj) throws SQLException {
	    //    �õ���ѵ�༶�����б�
			String sT = dao.getXbmzJxjz(nj);
			return sT;
	}
	
	public HashMap<String, String> getNblgJxjzxx(String bzdm,String nj) throws Exception {
		return dao.getNblgJxjzxx(bzdm,nj);
	}

	public HashMap<String, String> getXbmzJxjzxx(String bzdm,String nj) throws Exception {
		return dao.getXbmzJxjzxx(bzdm,nj);
	}
	
	public List<HashMap<String, String>> getLdList() {
		// ���������Ϣ
		return dao.getLdList();
	}
	
	/**
	 * @describe ��ȡ�����б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getLsList(String nj) {
		// ��ô�����ʦ��Ϣ
		return dao.getLsList(nj);
	}
	
	/**
	 * @describe ��ȡ�̹��б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getJgList(String nj) {
		// ��ô��ӽ̹���Ϣ
		return dao.getJgList(nj);
	}
	
	public String getLsOne(String sjdm) {
		// ���ָ��Ա��Ϣ
		return dao.getLsOne(sjdm);
	}
	
	public String getXbjsOne(String sjdm) {
		// ���ָ��Ա��Ϣ
		return dao.getXbjsOne(sjdm);
	}
	
	public String getXbjgOne(String sjdm) {
		// ���ָ��Ա��Ϣ
		return dao.getXbjgOne(sjdm);
	}
	
	public String getJgOne(String jgmc) {
		// ��ý̹���Ϣ
		return dao.getJgOne(jgmc);
	}
	
	public HashMap<String, String> getXbmzXx(String bzdm) {
		// ���ָ��Ա��Ϣ
		String sql = "select jgmc,zdy from XBMZ_JXBZDMB where bzdm=?";
		return dao.getMap(sql, new String[] { bzdm }, new String[] { "jgmc",
				"zdy" });
	}

	public List<String> getBzList(String jgbh) throws SQLException {
		// ��ñ�����Ϣ
		return dao.getBzList(jgbh);
	}
	
	public List<String> getJsBzList(String jsbh) throws SQLException {
		// ��ñ�����Ϣ
		return dao.getJsBzList(jsbh);
	}
	
	public boolean saveNblgJxjz(NblgJxjzModel model, String doS, 
			HttpServletRequest request) throws Exception {
		return dao.saveNblgJxjz(model, doS, request);
	}
	
	public boolean saveXbmzJxjz(NblgJxjzModel model, String doS, 
			HttpServletRequest request) throws Exception {
		return dao.saveXbmzJxjz(model, doS, request);
	}
	
	public List<HashMap<String, String>> getNblgYjList(String nj) {
		return dao.getNblgYjList(nj);
	}
	
	public List<HashMap<String, String>> getNblgLjList(String yjdm,String nj) {
		return dao.getNblgLjList(yjdm,nj);
	}
	
	public List<HashMap<String, String>> getXbmzYjList(String nj) {
		return dao.getXbmzYjList(nj);
	}
	
	public List<HashMap<String, String>> getXbmzLjList(String yjdm,String nj) {
		return dao.getXbmzLjList(yjdm,nj);
	}
	
	public List<HashMap<String, String>> getNblgPjList(String ljdm,String nj) {
		return dao.getNblgPjList(ljdm,nj);
	}
	
	public boolean delNblgJxbz(String bzdm,String nj) throws Exception {
			return dao.delNblgJxbz(bzdm,nj);
	}
	
	public boolean delXbmzJxbz(String bzdm, String nj, String xb)
			throws Exception {
		return dao.delXbmzJxbz(bzdm, nj, xb);
	}
	
	   
  	/**
	 * @describe ��ñ�ͷ
	 * @author luojw
	 */
	public List getTopTr(String tableName, String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		return topTr;
	}
	
	   
  	/**
	 * @describe ��þ�ѵ���ƴ�ӡ�б�
	 * @author luojw
	 */
	public List<HashMap<String, String>> getJxjzPrintList1(JxglJzModel myModel)
			throws SQLException {

		String[] colList = new String[] { "bzmc1", "num1", "jgmc1", "zdy1",
				"tdnum" };
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
//
//		String sql = "select a.bzmc1, a.num1, a.jgmc1, a.zdy1,(count(distinct(b.bzmc2))*100) tdnum from (select distinct (t.bzmc1),"
//				+ " t.num1, t.jgmc1, t.zdy1 from view_xbmz_bztj t order by t.bzmc1) a, view_xbmz_bztj b where a.bzmc1 = b.bzmc1"
//				+ " group by a.bzmc1, a.num1, a.jgmc1, a.zdy1 order by a.bzmc1";
		String sql = "select distinct (a.bzmc1),a.jgmc1,a.zdy1,(count(distinct(d.bzmc2)) * 100) tdnum,"
				+ " max(ltrim(sys_connect_by_path((a.num1) || '', '��'), '��')) num1 from (select distinct (t.bzmc1),"
				+ " t.num1, t.jgmc1, t.zdy1, row_number() over(partition by b.bzmc1 order by t.bzmc1) pno,"
				+ " row_number() over(partition by b.bzmc1 order by t.bzmc1) - 1 sno from view_xbmz_bztj t, view_xbmz_bztj b"
				+ " where t.num1 = b.num1 and t.bzmc1 = b.bzmc1 group by t.bzmc1, t.num1, t.jgmc1, t.zdy1, b.bzmc1 order by t.bzmc1) a,"
				+ " view_xbmz_bztj d where a.bzmc1 = d.bzmc1 start with pno = 1 connect by prior pno = sno and prior a.bzmc1 = a.bzmc1"
				+ " group by a.bzmc1, a.jgmc1, a.zdy1 order by a.bzmc1";
		
		rs = dao.getList(sql, new String[] {}, colList);
		
		return rs;
	}
	
	public ArrayList<String[]> getJxjzPrintList2(JxglJzModel myModel)
			throws SQLException {

		String[] colList = new String[] { "bzmc2", "num2", "cy", "jgmc2",
				"zdy2", "jgmc3", "num" };
		ArrayList<String[]> rs = new ArrayList<String[]>();

		String sql = "select distinct (bzmc2), num2, count(bzmc1) num, bzmc1, max(ltrim(sys_connect_by_path((a.num3) || '', '��'), '��')) cy,"
				+ " jgmc2, zdy2, jgmc3 from (select t.bzmc2, t.bzmc1, t.bjmc, t.num3, t.num2, t.jgmc2, t.zdy2,t.jgmc3,"
				+ " row_number() over(partition by b.bzmc2 order by t.bzmc2) pno,"
				+ " row_number() over(partition by b.bzmc2 order by t.bzmc2) - 1 sno"
				+ " from view_xbmz_bztj t , view_xbmz_bztj b where t.bzmc2 = b.bzmc2 group by t.bzmc1, t.num3,"
				+ " t.num2, t.bjmc, t.bzmc2, t.jgmc2, t.zdy2, t.jgmc3, b.bzmc2) a start with pno = 1 connect by prior pno = sno"
				+ " and prior a.bzmc2 = a.bzmc2 group by bzmc2, bzmc1, num2, jgmc2, zdy2, jgmc3 order by bzmc1";

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		return rs;
	}
	
	public ArrayList<String[]> getJxrqList(JxglJzModel myModel)
			throws SQLException {

		String[] colList = new String[] { "pk","jxrq", "sj", "nr", "dd", "zzz" };
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String kssj = myModel.getKssj();
		String jssj = myModel.getJssj();
		String query=" where 1=1";
		if(kssj!=null&&!"".equals(kssj)){
			query += " and jxrq > '" + kssj + "'";
		}
		if(jssj!=null&&!"".equals(jssj)){
			query += " and jxrq < '" + jssj + "'";
		}
		String sql = "select pk,substr(jxrq, 0, 4) || '��' || substr(jxrq, 5, 2) || '��' ||"
				+ " substr(jxrq, 7, 2) || '��' jxrq, sj, nr,dd,zzz from view_xbmz_jxrc";	
		sql += query;
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		return rs;
	}
	
	public boolean saveXbmzJxrc(JxglJzModel model, HttpServletRequest request)
			throws Exception {
		
		String jxrq = model.getJxrq();
		String sj = DealString.toGBK(model.getSj());
		String nr = DealString.toGBK(model.getNr());
		String dd = DealString.toGBK(model.getDd());
		String zzz = DealString.toGBK(model.getZzz());

		boolean bFlag = StandardOperation.insert("xbmz_jxrc", new String[] {
				"jxrq", "sj", "nr", "dd", "zzz" }, new String[] { jxrq, sj, nr,
				dd, zzz }, request);
		return bFlag;
	}
	
	public boolean saveXbmzJxrc2(JxglJzModel model, String pk,
			HttpServletRequest request) throws Exception {

		String jxrq = model.getJxrq();
		String sj = DealString.toGBK(model.getSj());
		String nr = DealString.toGBK(model.getNr());
		String dd = DealString.toGBK(model.getDd());
		String zzz = DealString.toGBK(model.getZzz());

		boolean bFlag = StandardOperation.update("xbmz_jxrc", new String[] {
				"jxrq", "sj", "nr", "dd", "zzz" }, new String[] { jxrq, sj, nr,
				dd, zzz }, "rowid", pk, request);
		return bFlag;
	}
	
	public boolean delXbmzJxrc(String pk,
			HttpServletRequest request) throws Exception {

		boolean bFlag = StandardOperation.delete("xbmz_jxrc", "rowid", pk, request);
		return bFlag;
	}
	
	public HashMap<String, String> getXbmzJxrc(String pk) {

		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "jxrq", "sj", "nr", "dd", "zzz" };

		String sql = "select jxrq, sj, nr,dd,zzz from view_xbmz_jxrc where pk=? ";
		map = dao.getMap(sql, new String[] { pk }, colList);
		return map;
	}
	
	/**
	 * ��ѵ���Ʊ����ӡ
	 * @param wwb
	 * @return void	 
	 * */
	@SuppressWarnings("unchecked")
	public void printPayReport(WritableWorkbook wwb) {
		String xxmc = StandardOperation.getXxmc();

		List list = dao.getList(
				" select * from view_xbmz_bztj order by bzmc1,bzmc2",
				new String[] {}, new String[] { "bzmc1", "num1", "jgmc1",
						"zdy1", "bzmc2", "num2", "num3", "jgmc2", "zdy2",
						"jgmc3" });

		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, xxmc + "��ѵѧ��Ӫ�������", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			for (int i = 0; i < list.size(); i++) {
				if (i == 7) {
					int rowtd1 = 0;
					rowtd1++;
				}
				HashMap<String, String> map = new HashMap<String, String>();

				map = (HashMap<String, String>) list.get(i);

				ws.addCell(new Label(0, 3 + i, map.get("bzmc1"), wcfTytle));
				ws.addCell(new Label(1, 3 + i, map.get("num1"), wcfTytle));
				ws.addCell(new Label(2, 3 + i, map.get("jgmc1"), wcfTytle));
				ws.addCell(new Label(3, 3 + i, map.get("zdy1"), wcfTytle));
				ws.addCell(new Label(4, 3 + i, map.get("bzmc2"), wcfTytle));
				ws.addCell(new Label(5, 3 + i, map.get("num2"), wcfTytle));
				ws.addCell(new Label(6, 3 + i, map.get("num3"), wcfTytle));
				ws.addCell(new Label(7, 3 + i, map.get("jgmc2"), wcfTytle));
				ws.addCell(new Label(8, 3 + i, map.get("zdy2"), wcfTytle));
				ws.addCell(new Label(9, 3 + i, map.get("jgmc3"), wcfTytle));
				
			}
			int m = 1;
			int k=1;
			boolean n = false;
			boolean zyFlg = false;
			boolean ldFlg = false;
			boolean zlFlg = false;
			String zybz="";
			String zlrs="";
			for (int i = 0; i <= list.size(); i++) {
				String a3 = "";
				String a4 = "";
				String b3 = "";
				String b4 = "";
				String e3 = "";
				String e4 = "";
				String g3 = "";
				String g4 = "";
				
				WritableCell a1 = ws.getWritableCell(0, 3 + i);
				WritableCell b1 = ws.getWritableCell(1, 3 + i);
				WritableCell e1 = ws.getWritableCell(4, 3 + i);
				WritableCell g1 = ws.getWritableCell(6, 3 + i);
				
				if (i > 0) {
					WritableCell a2 = ws.getWritableCell(0, 3 + i - 1);
					WritableCell b2 = ws.getWritableCell(1, 3 + i - 1);
					WritableCell e2 = ws.getWritableCell(4, 3 + i - 1);
					WritableCell g2 = ws.getWritableCell(6, 3 + i - 1);
					
					a4 = a2.getContents();
					b4 = b2.getContents();
					e4 = e2.getContents();
					g4 = g2.getContents();
				}
				a3 = a1.getContents();
				b3 = b1.getContents();
				e3 = e1.getContents();
				g3 = g1.getContents();
				
				if (a3.equals(a4)) {
					m++;
					n = true;
					if(!b3.equals(b4)){
						zyFlg=true;
						zybz += b4 + " ";
					}
					if(e3.equals(e4)){
						if (!g3.equals(g4)) {
							zlrs += g4 + " ";
							zlFlg = true;
						}
						ldFlg=true;
						k++;
					}
				}
				
				if ((!a3.equals(a4)) && n) {
					ws.mergeCells(0, 3 + i - m, 0, 3 + i - 1);
					ws.mergeCells(1, 3 + i - m, 1, 3 + i - 1);
					ws.mergeCells(2, 3 + i - m, 2, 3 + i - 1);
					ws.mergeCells(3, 3 + i - m, 3, 3 + i - 1);
					if (zyFlg) {
						zybz = zybz + b4;
						String[] zy = zybz.split(" ");
						String a="";
						if (zy.length > 0) {
							for (int t = 0; t < zy.length; t++) {
								for (int y = t + 1; y < zy.length; y++) {
									if (zy[t].equals(zy[y])) {
										a = zybz.replace(zy[t], "");
										a += zy[t] + " ";
									}
								}
							}
						}
					
						if (a != null && !"".equals(a)) {
							ws.addCell(new Label(1, 3 + i - m, a, wcfTytle));
						}else{
							ws.addCell(new Label(1, 3 + i - m, zybz, wcfTytle));
						}
						zybz = "";
						zyFlg = false;
					} else {
						ws.addCell(new Label(1, 3 + i - m, b4, wcfTytle));
					}					
					n = false;
					m = 1;
				}
				if (!e3.equals(e4) && ldFlg) {
					ws.mergeCells(4, 3 + i - k, 4, 3 + i - 1);
					ws.mergeCells(5, 3 + i - k, 5, 3 + i - 1);
					ws.mergeCells(6, 3 + i - k, 6, 3 + i - 1);
					ws.mergeCells(7, 3 + i - k, 7, 3 + i - 1);
					ws.mergeCells(8, 3 + i - k, 8, 3 + i - 1);
					ws.mergeCells(9, 3 + i - k, 9, 3 + i - 1);
					
					if (zlFlg) {
						ws.addCell(new Label(6, 3 + i - k, zlrs + g4, wcfTytle));
						zlrs = "";
						zlFlg = false;
					} else {
						ws.addCell(new Label(6, 3 + i - k, g4, wcfTytle));
					}		
					
					ldFlg = false;
					k = 1;
				}
			}

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;
			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
