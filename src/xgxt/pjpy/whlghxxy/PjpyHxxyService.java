package xgxt.pjpy.whlghxxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.utils.ModelToStrings;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
public class PjpyHxxyService {

	private static PjpyHxxyService service = new PjpyHxxyService();
	
	public static PjpyHxxyService getInstance() {
		return service;
	}
	
	private PjpyHxxyDAO dao = PjpyHxxyDAO.getInstance();
	
	/**
	 * ��ѯ�ۺ����ʱ�ͷ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryZhszcpTitle() {
		String[] enList = new String[]{"pk", "rownum", "xn","xh", "xm", "bjmc", "dcj", "zcj", "tcj", "mcj", "xf", "jlf", "cff"};
		String[] cnList = new String[]{"pk", "�к�", "ѧ��","ѧ��", "����", "�༶", "����", "����", "����", "����", "ѧ�ּ�", "������", "�ͷ���"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * �����ƺ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryRychshTitle() {
		String[] enList = new String[]{"pk", "rownum", "xn","xh", "xm", "xn","xysh", "xxsh"};
		String[] cnList = new String[]{"pk", "�к�", "ѧ��","ѧ��", "����", "ѧ��", "ѧԺ���", "ѧУ���"};
		return dao.getTitleList(enList, cnList);
	}
	/**
	 * ��ѯ�ۺ����ʽ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryrychshList(PjpyHxxyModel model) throws Exception {
		return dao.queryrychshResultByxy(model);
	}
	/**
	 * �����ƺ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpList(PjpyHxxyModel model, PjpyHxxyActionForm dataSearchForm) throws Exception {
		return dao.queryZhszcpList(model, dataSearchForm);
	}
	
	public int queryZhszcpListNum(PjpyHxxyModel model) throws Exception {
		return dao.queryZhszcpListNum(model);
	}
	
	/**
	 * ɾ���ۺ����ʷ�
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteZhszf(String[] keys) throws Exception {
		return dao.deleteZhszf(keys);
	}
	
	/**
	 * ��ȡ���˽�ѧ��������Ϣ
	 * @param jxjpdModel
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(PjpyHxxyModel model) throws Exception {
		return dao.getJxjpdxx(model);
	}
	
	public boolean chksqtj(String xh) {
		return dao.chksqtj(xh);
	}
	
	/**
	 * ��ѧ�����뱣��
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(PjpyHxxyModel model, HttpServletRequest request) throws Exception {
		return dao.jxjsqSave(model, request);
	}
	
	/**
	 * �����������LIST��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String tableName) throws Exception {
		String[] enList = null;
		String[] cnList = null;
		if (StringUtils.isEqual(tableName, "xsjxjb")) {
			enList = new String[]{"pk", "rownum", "nd", "xn", "xh", "xq", "xm", "bjmc","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk", "�к�", "���", "ѧ��", "ѧ��", "ѧ��", "����", "�༶����", "��ѧ������", "ѧԺ���", "ѧУ���"};
		}
		if (StringUtils.isEqual(tableName, "stusqxx")) {
			enList = new String[]{"xh||xn||xq||nd||jxjdm", "rownum","xn", "xq","nd","jxjmc", "xysh", "xxsh"};
			cnList = new String[]{"pk","�к�", "ѧ��","ѧ��","���", "��ѧ��", "ѧԺ���", "ѧУ���"};
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * ѧ����ѧ���ѯ��Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stuJxjSqxx(String xh) throws Exception {
		return dao.stuJxjSqxx(xh);
	}
	
	/**
	 * �Զ������ۺϷ�
	 * @param xn
	 * @param nd
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean autoCount(String xn, String nd, String xydm) throws Exception{
		return dao.autoCount(xn, nd, xydm);
	}
	
	/**
	 * ��ʾ�ۺ����ʲ�����
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewZhszcp(String pkValue) {
		return dao.viewZhszcp(pkValue);
	}
	
	/**
	 * �޸��ۺϷ�
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcp(String pkValue, PjpyHxxyModel model, HttpServletRequest request) throws Exception{
		return dao.updateZhszcp(pkValue, model, request);
	}
	
	
	public List<String[]> queryJxjshResult(PjpyHxxyModel model, String userType)
	throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryJxjshResultByxy(model);
		} else {
			return dao.queryJxjshResultByxx(model);
		}
	}
	
	/**
	 * ���
	 * @param key
	 * @param jg
	 * @param userType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	public String jxjplsh(String[] key,String jg, String userType, HttpServletRequest request) throws Exception {
		return dao.jxjplsh(key, jg, userType, request);
	}
	
	public HashMap<String, String> viewJxjshOne(String pkValue, String userType) throws Exception {
		return dao.viewJxjshOne(pkValue, userType);
	}
	
	public boolean jxjshOne(String pkValue, String userType, String sh, String yj, String fdyyj) throws Exception {
		return dao.jxjshOne(pkValue, userType, sh, yj, fdyyj);
	}
	
	public List<HashMap<String, String>> queryJxjshTitle(String userType) {
		String[] enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
				"xm", "bjmc", "jxjmc", "xf", "xfpm", "zf", "zfpm", "xysh" };
		String[] cnList = new String[] { "pk", "dis", "�к�", "ѧ��", "ѧ��","����", "�༶",
				"��ѧ��", "ѧ�ּ�", "ѧ�ּ�����", "�ۺ������ܷ�", "�ܷ�����" , "ѧԺ���"};
		if (!"xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
					"xm", "bjmc", "jxjmc", "xf", "xfpm", "zf", "zfpm", "xysh" };
			cnList = new String[] { "pk", "dis", "�к�", "ѧ��", "ѧ��","����", "�༶",
					"��ѧ��", "ѧ�ּ�", "ѧ�ּ�����", "�ۺ������ܷ�", "�ܷ�����" , "ѧУ���"};
		}
		return dao.getTitleList(enList, cnList);
	}
	
	/**
	 * @describe �����ƺű��浽���ݿ�
	 * @author luo
	 */
	public boolean updataRych(PjpyHxxyModel myModel, String pk,
			HttpServletRequest request, String type, String oldpk) throws Exception {

		String tableName = "XSRYCHB";
		String pkComment = "xh||xn||rychdm";
		
		String[] colList = new String[] { "xn", "nd", "xh", "rychdm", "zysj",
				"drshgzqk","wydj" };
		
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		// boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
		// request);
		boolean inserted = false;
		if ("yes".equalsIgnoreCase(type)) {
			inserted = StandardOperation.update(tableName, colList, inputList, pkComment, oldpk, request);
		} else {
		inserted = StandardOperation.insert(tableName, colList,
				inputList, request);
		}

		return inserted;
	}
	/**
	 * @describe ȡ�������ƺű�ͷ
	 * @author luo
	 */
	public List getRychTopTr() {

		DAO dao = DAO.getInstance();
		String tableName = "view_xsrychb";
		String[] colList = new String[] { "pk","nd", "xn","xh", "xm", "bjmc",
				"rychmc", "xysh", "xxsh" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ

		return topTr;
	}
	

	/**
	 * @describe ȡ�������ƺ��б�
	 * @author luo
	 */
	public ArrayList<String[]> getRychList(PjpyHxxyModel myModel,
			PjpyHxxyActionForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String xn = DealString.toGBK(myModel.getXn());
		String nd = DealString.toGBK(myModel.getNd());
		String xq = DealString.toGBK(myModel.getXq());
		String rychdm = DealString.toGBK(myModel.getRychdm());
//		String xxsh = DealString.toGBK(myModel.getXxsh());
		String xysh = DealString.toGBK(myModel.getXysh());

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, nd, xq, xn));

		if(rychdm!=null&&!"".equals(rychdm)){
			query.append(" and rychdm = '");
			query.append(rychdm);
			query.append("'");
		}
		if(xysh!=null&&!"".equals(xysh)){
			query.append(" and xysh = '");
			query.append(xysh);
			query.append("'");
			query.append(" and xxsh = '");
			query.append(xysh);
			query.append("'");
		}
		
		String[] colList = new String[] { "pk","nd", "xn", "xh", "xm", "bjmc",
				"rychmc", "xysh", "xxsh" };

		String sql = "select * from (select * from (select xn||xh||rychdm pk,rownum r,nd,xn,xh,xm,bjmc,rychmc,xysh, xxsh from view_xsrychb "
				+ query
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ ") where r>"
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// ��ҳ
		sql = "select count(*) count from view_xsrychb " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}
	/**
	 * @describe �õ������ƺ���ϸ��Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getRychOne(String pk) {

		DAO daoC=new DAO();
		String[] colList = new String[] { "xh", "nd", "xm", "xn", "xb",
				"xymc", "zymc", "bjmc", "rychdm","rychmc","zysj","drshgzqk","nj","mzmc", "wydj", "csrq", "lxdh", "zzmmmc","rxrq"
				 };
		pk= pk.replace(" ", "");
		String sql = "select a.xh, a.nd, a.xm, a.xn, a.xb,a.xymc, a.zymc, a.bjmc, a.rychdm,a.rychmc,a.zysj,a.drshgzqk,"
				+ " a.nj,b.mzmc, a.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from view_xsrychb a"
				+ " ,view_xsxxb b  where a.xh=b.xh and a.xn ||a.xh||a.rychdm = ? ";
		HashMap<String, String> rs = daoC.getMap(sql, new String[] { pk },
				colList);
		return rs;
	}
	
	/**
	 * @describe �õ������ƺ���ϸ��Ϣ
	 * @author luo
	 */
	public HashMap<String, String> getRychStu(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		DAO daoc=new DAO();
		String sql = "select b.xh,b.xm,b.xb,b.xymc, b.zymc,b.bjmc,b.nj,b.mzmc,"
				+ " b.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from "
				+ " view_xsxxb b where b.xh=?";
		
		map = daoc.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc", "mzmc", "wydj", "zzmmmc",
				"csrq", "lxdh", "rxrq" });
		
		return map;
	}
	
	/**
	 * �����ƺ�����ɾ��
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String rychDel(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from xsrychb where xn||xh||rychdm = '" + pk + "'";
			// ��������ϳ�sql���
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * @describe �Ѿ�����������ƺ�
	 * @author luo
	 */
	public HashMap<String, String> getRych(String pk,
			HttpServletRequest request) throws Exception {

		DAO daoc = new DAO();
		String sql = "select t.xh,t.xb,t.xm,t.nj,t.xymc,t.rychdm,t.zymc,t.bjmc, b.mzmc, b.wydj, b.csrq, b.lxdh, b.rxrq from "
			+ " view_xsrychb t, view_xsxxb b where t.xh = b.xh and t.xh||t.xn||t.rychdm=?";
		String[] colList = new String[] { "xh",
				"xm", "xb", "nj", "xymc", "rychdm","zymc", "bjmc", "mzmc", "wydj",
				"csrq", "lxdh", "rxrq" };
		HashMap<String, String> map = daoc.getMap(sql, new String[]{pk}, colList);
		

		return map;
	}
	
	
	public List<String[]> jxjsqjgQuery(PjpyHxxyModel model,PjpyHxxyActionForm dataSearchForm) throws Exception {
		return dao.jxjsqjgQuery(model, dataSearchForm);
	}
	
	public int jxjsqjgQueryNum(PjpyHxxyModel model, PjpyHxxyActionForm dataSearchForm) throws Exception {
		return dao.jxjsqjgQueryNum(model, dataSearchForm);
	}
	
	public List<HashMap<String, String>> jxjsqTitlequery(String userType) {
		String[] enList = new String[]{"pk", "rownum", "xn", "xh", "xm", "bjmc", "xysh", "xxsh"};
		String[] cnList = new String[] { "pk", "�к�", "ѧ��", "ѧ��","����", "�༶",
				 "ѧԺ���", "ѧУ���"};
		
		return dao.getTitleList(enList, cnList);
	}
	
	public String jxjsqDelete(String[] keys) throws Exception {
		return dao.jxjsqDelete(keys);
	}
	
	public boolean jxjsqUpdate(String pkValue, PjpyHxxyModel model, HttpServletRequest request) throws Exception {
		return dao.jxjsqUpdate(pkValue, model, request);
	}
	
	public HashMap<String, String> jxjsqView(String pkValue) {
		return dao.jxjsqView(pkValue);
	}
	
	/**
	 * ���
	 * @param key
	 * @param jg
	 * @param userType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryrychshResult(PjpyHxxyModel model, String userType)
	throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryrychshResultByxy(model);
		} else {
			return dao.queryrychshResultByxx(model);
		}
	}
	
	public String ruchplsh(String[] key,String jg, String userType, HttpServletRequest request) throws Exception {
		return dao.rychplsh(key, jg, userType, request);
	} 
	
	public HashMap<String, String> viewrychshOne(String pkValue, String userType) throws Exception {
		return dao.viewrychshOne(pkValue, userType);
	}
	
	public boolean rychshOne(String pkValue, String userType, String sh, String yj, String fdyyj) throws Exception {
		return dao.rychshOne(pkValue, userType, sh, yj, fdyyj);
	}
	
	public List<HashMap<String, String>> queryrychshTitle(String userType) {
		String[] enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
				"xm", "xb", "rychdm","xysh"};
		String[] cnList = new String[] { "pk", "dis", "�к�", "ѧ��", "ѧ��","����", "�Ա�",
				"�����ƺ�","ѧԺ���"};
		if (!"xy".equalsIgnoreCase(userType)) {
			enList = new String[] { "pk", "dis", "rownum", "xn", "xh",
					"xm", "xb", "rychdm","xysh","xxsh"};
			cnList = new String[] { "pk", "dis", "�к�", "ѧ��", "ѧ��","����", "�Ա�",
					"�����ƺ�","ѧԺ���","ѧУ���"};
		}
		return dao.getTitleList(enList, cnList);
	}
}
