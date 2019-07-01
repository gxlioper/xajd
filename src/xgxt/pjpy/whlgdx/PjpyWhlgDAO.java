package xgxt.pjpy.whlgdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �人����ѧ��������DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-01</p>
 */
public class PjpyWhlgDAO {
	ArrayList<String> values = new ArrayList<String>();
	DAO dao = DAO.getInstance();
	/**
	 * �ۺ����ʲ�����ѯ������ֵ
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(WhlgdxZhszcpModel zhszcpModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String nj = zhszcpModel.getNj();
		String xn = zhszcpModel.getXn();
		String xq = zhszcpModel.getXq();
		String xh = zhszcpModel.getXh();
		String xydm = zhszcpModel.getXydm();
		String zydm = zhszcpModel.getZydm();
		String bjdm = zhszcpModel.getBjdm();
		String nd = zhszcpModel.getNd();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		return whereSql;
	}
	
	/**
	 * �Ƚ��༶��ѯ������ֵ
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSqlXjbj(WhlgdxXjbjModel xjbjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();		
		String xn = xjbjModel.getXn();
		String xq = xjbjModel.getXq();
		String nd = xjbjModel.getNd();
		String xydm = xjbjModel.getXydm();
		String zydm = xjbjModel.getZydm();
		String bjdm = xjbjModel.getBjdm();
		String nj = xjbjModel.getNj();
		String userName = xjbjModel.getUserName();
		
		boolean isFdy = xjbjModel.isFdy();
		
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		if(isFdy){
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh=?)");
			values.add(userName);
		}
		return whereSql;
	}
	
	
	
	/**
	 * ��ȡѧ���Ļ�����Ϣ
	 * @param xh HashMap<String, String>
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		String sql= "select a.xh,a.xm,a.xb,a.nj,a.bjdm,a.bjmc,a.zydm,a.zymc,a.xydm,a.xymc,a.xz,a.sjhm from view_xsxxb a where xh=?";
		String[] output = {"xh", "xm", "xb", "nj", "bjdm", "bjmc", "zydm", "zymc", "xydm", "xymc", "xz","sjhm"};
		return dao.getMap(sql, new String[]{xh}, output);
	}
	
	/**
	 * ��ȡ�ۺ����ʲ�����ѯ�����ͷ
	 * @return List
	 * */
	public List getZhszcpTitle(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xh||xn||nd||xq", "nd", "xn", "xq", "xh", "xm", "bjmc", "dcj", "stszzf", "sztzzf","zhszcpzf"};
		String[] cnList = new String[]{"xh||xn||nd||xq", "���", "ѧ��" ,"ѧ��","ѧ��","����", "�༶����" ,"˼��������ʷ���", "�������ʷ���", "��չ���ʷ���","�ۺ����ʲ����ܷ�"};
		for(int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			list.add(tmpMap);
		}
		return list;
	}
	
	/**
	 * �ۺ����ʲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpResult(WhlgdxZhszcpModel zhszcpModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||xq,nd,xn,xq,xh,xm,bjmc,dcj,stszzf,sztzzf,zhszcpzf from view_zhszcp where 1=1 ";
		String[] opList = new String[]{"xh||xn||nd||xq", "nd", "xn", "xq", "xh", "xm", "bjmc", "dcj", "stszzf", "sztzzf","zhszcpzf"};		
		StringBuffer whereSql = getWhereSql2(zhszcpModel);//��ѯ����
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ѯѧ���ۺ����ʲ�����ϸ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhszcpInfoByPk(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_zhszcp where xh||xn||nd||xq=?";
		String[] output = {"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xq","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl",
				 "stszzf","sztzzf","zhszcpzf","zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk","bz"};
		
		map = dao.getMap(sql, new String[]{pkValue}, output);
		return map;
	}
	
	/**
	 * �����ۺ����ʲ�����Ϣ
	 * @param model
	 * @param request
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean saveZhszcp(WhlgdxZhszcpModel model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] columns = { "xh", "xn", "nd", "xq", "dcj", "xxpjcj", "xxpjcjpm", "xxpjcjpmbl", "stszzf",
				"sztzzf", "zhszcpzf", "zhszcpcjpm", "zhszcpcjpmbl", "dkzdfs", "wygjqk", "bz" };
		String[] values = null;
		
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String dcj = model.getDcj();
		String xxpjcj = model.getXxpjcj();
		String xxpjcjpm = model.getXxpjcjpm();
		String xxpjcjpmbl = model.getXxpjcjpmbl();
		String stszzf = model.getStszzf();
		String sztzzf = model.getSztzzf();
		String zhszcpzf = model.getZhszcpzf();
		String zhszcpcjpm = model.getZhszcpcjpm();
		String zhszcpcjpmbl = model.getZhszcpcjpmbl();
		String dkzdfs = model.getDkzdfs();
		String wygjqk = DealString.toGBK(model.getWygjqk());
		String bz = DealString.toGBK(model.getBz());
		
		values = new String[]{xh, xn, nd, xq, dcj, xxpjcj, xxpjcjpm, xxpjcjpmbl, stszzf,
				sztzzf, zhszcpzf, zhszcpcjpm, zhszcpcjpmbl, dkzdfs, wygjqk, bz};
		flag = StandardOperation.delete("zhszcp", "xh||xn||nd||xq", xh + xn + nd + xq, request);
		if (flag) {
			flag = StandardOperation.insert("zhszcp", columns,values, request);
		}
		return flag;
	}
	
	/**
	 * ɾ��ѧ���ۺ����ʲ�����Ϣ
	 * @param String[] key
	 * @return String
	 * @throws Exception
	 * */
	public String zhszcpDel(String[] key, HttpServletRequest request) throws Exception{
		int del = 0;//ɾ����¼��
		for (int i = 0; i < key.length; i++) {
			String whichpk = key[i];// �õ�����
			boolean bFlag = StandardOperation.delete("zhszcp", "xh||xn||nd||xq", whichpk, request);
			if (bFlag){//ɾ���ɹ�
				del++;
			}
		}
		return String.format("%1$s ����¼�ɹ�ɾ��!", del);
	}
	
	/**
	 * ��ȡ�ۺ����ʲ�����Ϣ����
	 * @param model
	 * @return List
	 * */
	public List getZhszcpToExp(WhlgdxZhszcpModel model){
		List list = new ArrayList<HashMap<String, String>>();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String xh = model.getXh();
		String sql = "select xh, xm, nd, xn, xq, dcj, xxpjcj, xxpjcjpm, xxpjcjpmbl,stszzf, sztzzf, zhszcpzf, zhszcpcjpm," +
				     "zhszcpcjpmbl, dkzdfs, wygjqk from view_zhszcp where 1=1 ";
		String[] input = {"xh","xm","nd","xn","xq","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl", "stszzf","sztzzf","zhszcpzf",
				         "zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk"};
		
		StringBuffer whereSql = new StringBuffer();
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}
		list = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{},input);
		return list;
	}
	
	/**
	 * ��ȡ�ۺ����ʲ����������ݱ�ͷ
	 * @return String[]
	 * */
	public String[] getZhszcpTop(){
		String[] cName =  {"xh","xm","nd","xn","xq","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl","stszzf","sztzzf","zhszcpzf","zhszcpcjpm",
						  "zhszcpcjpmbl","dkzdfs","wygjqk"};
		String tabName = "view_zhszcp";
		return dao.getColumnNameCN(cName, tabName);
	}
	
	/**
	 * ��ȡ�Ƚ��༶��ѯ�����ͷ
	 * @return List
	 * */
	public List getXjbjTitle(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||xjbjlbdm", "xn", "xq", "nj", "xymc", "zymc", "bjmc","xjbjlbmc"};
		String[] cnList = new String[]{"xn||xq||bjdm||xjbjlbdm", "ѧ��" ,"ѧ��", "�꼶","ѧԺ����","רҵ����","�༶����", "�Ƚ��༶���" };
		for(int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			list.add(tmpMap);
		}
		return list;
	}
	
	
	/**
	 *�Ƚ��༶��ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResult(WhlgdxXjbjModel xjbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||xjbjlbdm,xn,xq,nj,xymc,zymc,bjmc,xjbjlbmc from view_pjpy_xjbj a where 1=1 ";
		String[] opList = new String[]{"xn||xq||bjdm||xjbjlbdm", "xn", "xq", "nj", "xymc", "zymc", "bjmc", "xjbjlbmc"};		
		StringBuffer whereSql = getWhereSqlXjbj(xjbjModel);//��ѯ����
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ѯ�Ƚ��༶��ϸ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXjbjInfoByPk(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_pjpy_xjbj where xn||xq||bjdm||xjbjlbdm=?";
		String[] output = {"xn","xq","xydm","xymc","zydm","zymc","bjdm","bjmc","xjbjlbdm","xjbjlbmc","bz"};
		
		map = dao.getMap(sql, new String[]{pkValue}, output);
		return map;
	}
	
	/**
	 * �����Ƚ��༶��Ϣ
	 * @param model
	 * @param request
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean saveXjbj(WhlgdxXjbjModel model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tabName = "pjpy_xjbjandwmsqb";
		String[] columns = {"xn","xq","bjdm","rychdm","bz"};
		String[] values = null;
		String xn = model.getXn();
		String xq = model.getXq();
		String bjdm = model.getBjdm();
		String xjbjlbdm = model.getXjbjlbdm();
		String bz = DealString.toGBK(model.getBz());
		
		values = new String[]{xn, xq, bjdm, xjbjlbdm, bz};
		flag = StandardOperation.delete(tabName, "xn||xq||bjdm||rychdm",xn + xq + bjdm + xjbjlbdm, request);
		if (flag) {
			flag = StandardOperation.insert(tabName, columns,values, request);
		}
		return flag;
	}
	
	/**
	 * ɾ���Ƚ��༶��Ϣ
	 * @param String[] key
	 * @return String
	 * @throws Exception
	 * */
	public String xjbjDel(String[] key, HttpServletRequest request) throws Exception{
		int del = 0;//ɾ����¼��
		String tabName = "pjpy_xjbjandwmsqb";
		for (int i = 0; i < key.length; i++) {
			String whichpk = key[i];// �õ�����
			boolean bFlag = StandardOperation.delete(tabName, "xn||xq||bjdm||rychdm", whichpk, request);
			if (bFlag){//ɾ���ɹ�
				del++;
			}
		}
		return String.format("%1$s ����¼�ɹ�ɾ��!", del);
	}
	
	/**
	 * ��ȡ�Ƚ��༶������
	 * @return List
	 * */
	public List getXjbjlbList(){
		String sql = "select distinct xjbjlbdm,xjbjlbmc from xjbjlbdmb order by xjbjlbdm";
		return dao.getList(sql, new String[]{}, new String[]{"xjbjlbdm", "xjbjlbmc"});
	}
	
	
	/**
	 * ��ȡ�Ƚ��༶�������ݱ�ͷ
	 * @return String[]
	 * */
	public String[] getXjbjTop(){
		String[] cName =  {"xymc","bjmc","xjbjlbmc"};
		String tabName = "view_pjpy_xjbj";
		return dao.getColumnNameCN(cName, tabName);
	}
	
	/**
	 * �Ƚ��༶��Ϣ����
	 * @param model
	 * @return List
	 * */
	public List getXjbjToExp(WhlgdxXjbjModel model){
		List list = new ArrayList<HashMap<String, String>>();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String sql = "select xymc,bjmc,xjbjlbmc from view_pjpy_xjbj";
		String[] input = {"xymc","bjmc","xjbjlbmc"};
		
		StringBuffer whereSql = new StringBuffer();
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		list = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{},input);
		return list;
	}
	
	/**
	 * ����ѧԺ��ѯ����������Ϣ
	 * @param xydm
	 * @return List
	 * */
	public List getFpxxByXydm(String xydm,String nd){
		List list = null;
		String sql = "select jxjdm,jxjmc,jxjrs,jxjje from (" +
               "select jxjdm,(select distinct jxjmc from jxjdmb b where a.jxjdm=b.jxjdm)jxjmc,max(jxjrs)jxjrs,(select max(jlje) from jxjdmb b where a.jxjdm=b.jxjdm)jxjje from xyjxjrs a" +  
               " where (jxjrs<>'0' or jxjrs is not null) and nd=? and bmdm=? group by jxjdm)";
		list = dao.getList(sql, new String[]{nd,xydm}, new String[]{"jxjdm","jxjmc","jxjrs","jxjje"});
		return list;
	}
	
	/**
	 * ����ѧԺ��ѯ��ѧ����Ϣ
	 * @param xydm
	 * @return String
	 * */
	public String getJxjjeOfXy(String xydm,String nd){
		String sql = "select sum(to_number(jxjje)) jxjje from (select jxjdm,jxjmc,jxjrs,jxjje from (" +
               "select jxjdm,(select distinct jxjmc from jxjdmb b where a.jxjdm=b.jxjdm)jxjmc,max(jxjrs)jxjrs,(select max(jlje) from jxjdmb b where a.jxjdm=b.jxjdm)jxjje from xyjxjrs a" +  
               " where (jxjrs<>'0' or jxjrs is not null) and nd=? and bmdm=? group by jxjdm))";
		return dao.getOneRs(sql, new String[]{nd,xydm}, "jxjje");
	}
	
	/**
	 * ��ѯ��ѧ�����ѧ��ѧԺ������Ϣ
	 * @param xydm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNdAndXyxx(String xydm){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb";
		map = dao.getMap(sql, new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
		sql = "select xymc from view_njxyzybj where xydm=?";
		map.put("xymc",dao.getOneRs(sql, new String[]{xydm}, "xymc"));
		return map;
	}
	
	/**
	 * ��ѯ��ͷ�ֶ�
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 * */
	public List<HashMap<String, String>> getJxjsztjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "jxjdm", "rownum", "xn", "jxjmc","tjzdm", "tj", };
		String[] cnList = new String[]{"pk", "jxjdm", "�к�", "ѧ��", "����", "�����ֶ���", "����"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * ��ȡ��ѧ����������
	 * @param xn
	 * @param jxjdm
	 * @param tableName
	 * @return List
	 * */
	public List getJxjsztj(String xn,String jxjdm,String jxjfl,String tableName){
		List<String[]> jxjsztjList = new ArrayList<String[]>();
		String sql = "";
		if(tableName != null && tableName.equalsIgnoreCase("jxjdmb")){//��ѧ��
			sql = "select xn||jxjdm||tjzdm pk,jxjdm,(select distinct jxjmc from jxjdmb c where a.jxjdm=c.jxjdm)jxjmc,rownum,xn,(case when tjzdm='dcj' then '˼��������ʷ���' when tjzdm='xxpjcj' then 'ѧϰƽ���ɼ�' when tjzdm='sztzzf' then '������չ����' when tjzdm='dkzdfs' then '������ͷ���' when tjzdm='zhszcpcjpmbl' then '�ۺϲ����ɼ���������' when tjzdm='xxpjcjpmbl' then 'ѧϰƽ���ɼ���������' when tjzdm='wygjqk' then '����������' when tjzdm='sfpks' then '�Ƿ�ƶ����' end) tjzdm, zdcz||tj tj from jxjhdtj a where 1=1 ";	
		}else if (tableName != null && tableName.equalsIgnoreCase("rychdmb")){//�����ƺ�			
			sql = "select xn||rychdm||tjzdm pk,rychdm jxjdm,(select rychmc from rychdmb c where a.rychdm=c.rychdm)jxjmc,rownum,xn,(case when tjzdm='dcj' then '˼��������ʷ���' when tjzdm='xxpjcj' then 'ѧϰƽ���ɼ�' when tjzdm='sztzzf' then '������չ����' when tjzdm='dkzdfs' then '������ͷ���' when tjzdm='zhszcpcjpmbl' then '�ۺϲ����ɼ���������' when tjzdm='xxpjcjpmbl' then 'ѧϰƽ���ɼ���������' when tjzdm='wygjqk' then '����������' when tjzdm='sfpks' then '�Ƿ�ƶ����' end) tjzdm, zdcz||tj tj from rychhdtj a where 1=1 ";
		}
		
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn='");
			whereSql.append(xn);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjdm) && tableName != null && !jxjdm.equalsIgnoreCase("null") && tableName.equals("jxjdmb")) {
			whereSql.append(" and jxjdm='");
			whereSql.append(jxjdm);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjdm) && tableName != null && !jxjdm.equalsIgnoreCase("null") && tableName.equals("rychdmb")) {
			whereSql.append(" and rychdm='");
			whereSql.append(jxjdm);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjfl) && tableName != null && !jxjfl.equalsIgnoreCase("null") && tableName.equals("jxjdmb")) {
			whereSql.append(" and exists(select 1 from jxjdmb b where a.jxjdm=b.jxjdm and b.jxjfl='");
			whereSql.append(jxjfl);
			whereSql.append("')");
		}
		if (!StringUtils.isNull(jxjfl) && tableName != null && !jxjfl.equalsIgnoreCase("null") && tableName.equals("rychdmb")) {
			whereSql.append(" and esists(select 1 from rychdmb b where a.rychdm=b.rychdm and b.rychfl='");
			whereSql.append(jxjfl);
			whereSql.append("')");
		}
		jxjsztjList = dao.rsToVator(sql + whereSql, new String[]{}, new String[]{"pk", "jxjdm","rownum","xn","jxjmc" , "tjzdm", "tj"});
		return jxjsztjList;
	}
	
	/**
	 * ��ȡ��ѧ������б�
	 * @param tableName
	 * @return List
	 * */
	public List getJxjflList(String tableName){
		String sql = "";
		//��ѧ��
		sql = "select distinct JXJFL jxjfldm,JXJFL jxjflmc from jxjdmb";
		if(tableName!=null && tableName.equalsIgnoreCase("rychdmb")){//�����ƺ�
			sql = "select distinct rychfl jxjfldm,rychfl jxjflmc from rychdmb";
		}
		List jxjflList = dao.getList(sql, new String[]{}, new String[]{"jxjfldm","jxjflmc"});		
		return jxjflList;
	}
	
	/**
	 * ��ȡ��ѧ���б�
	 * @param jxjfl
	 * @param tableName 
	 * @return List
	 * */
	public List getJxjList(String jxjfl,String tableName){
		String sql = "";
		if(jxjfl!=null && !jxjfl.equalsIgnoreCase("") && !jxjfl.equalsIgnoreCase("null")){
			sql = "select distinct jxjdm,jxjmc from jxjdmb where jxjfl='" + jxjfl + "' order by jxjdm";
			if(tableName != null && tableName.equalsIgnoreCase("rychdmb")){
				sql = "select distinct rychdm jxjdm,rychmc jxjmc from rychdmb where rychfl='" + jxjfl + "' order by rychdm";
			}
		}else{
			sql = "select distinct jxjdm,jxjmc from jxjdmb order by jxjdm";
			if(tableName != null && tableName.equalsIgnoreCase("rychdmb")){
				sql = "select distinct rychdm jxjdm,rychmc jxjmc from rychdmb order by rychdm";
			}
		}
		return dao.getList(sql, new String[]{}, new String[]{"jxjdm","jxjmc"});
	}
	
	/**
	 * ���ݴ����ȡ��ѧ��������ƺŵ�����
	 * @param tableName
	 * @param jxjdm
	 * @return String
	 * */	
	public String getJxjmc(String tableName, String jxjdm){
		String sql = "";
		if(tableName!=null && tableName.equalsIgnoreCase("jxjdmb")){			
			sql = "select jxjmc from jxjdmb where jxjdm=?";
		}else if (tableName!=null && tableName.equalsIgnoreCase("rychdmb")){
			sql = "select rychmc jxjmc from rychdmb where rychdm=?";
		}		
		return dao.getOneRs(sql, new String[]{jxjdm}, "jxjmc");
	}
	
	/**
	 * ��������������Ϣ
	 * @param model
	 * @return boolean 
	 * */
	@SuppressWarnings("unchecked")
	public String saveTjsz(PjpyWhlgdxForm model,String tableName){
		String flag = "true";
		String[] cztj = model.getCztj();
		String dcj = model.getDcj();
		String xxpjcj = model.getXxpjcj();
		String sztzzf = model.getSztzzf();
		String dkzdfs = model.getDkzdfs();
		String zhszcpcjpmbl = model.getZhszcpcjpmbl();  
		String xxpjcjpmbl = model.getXxpjcjpmbl();
		String wygjqk = DealString.toGBK(model.getWygjqk());
		String sfpks = DealString.toGBK(model.getSfpks());
		String xn = model.getXn();
		String jxjdm = model.getJxjdm();
		String jxjfl = model.getJxjfl();
		
		String[] cztjV = new String[cztj.length+2];
		for(int i=0; i<cztj.length; i++){
			cztjV[i] = cztj[i];
		}
		cztjV[cztj.length] = "=";//����������
		cztjV[cztj.length+1] = "="; //�Ƿ�ƶ����
		
		
		String[] value = {dcj,xxpjcj,sztzzf,dkzdfs,zhszcpcjpmbl,xxpjcjpmbl,wygjqk,sfpks};
		String[] input = {"dcj","xxpjcj","sztzzf","dkzdfs","zhszcpcjpmbl","xxpjcjpmbl","wygjqk","sfpks"};
		String[] sqlV = new String[value.length];
		String tjzddyb = "zhszcp";
		String sql = "";
		String tempSql = "";
		String whereSql = " where 1=1 ";		
		List jxjList = null;
		
		//�ж��Ƕ��ĸ���Ŀ����������
		if(tableName.equals("jxjdmb")){//��ѧ��
			sql = "insert into jxjhdtj(JXJDM,TJZDM,TJZDLYB,TJ,ZDCZ,XN) values(";
			tempSql = "select jxjdm from jxjdmb ";
			//��ѯ��ѧ������б�����
			if(jxjdm!=null && !jxjdm.equals("")){
				whereSql += " and jxjdm=" + jxjdm;
			}
			if(jxjfl!=null && !jxjfl.equalsIgnoreCase("")){
				whereSql += " and jxjfl=" + DealString.toGBK(jxjfl);
			}
			tableName = "jxjhdtj";
		}else if (tableName.equalsIgnoreCase("rychdmb")){
			sql = "insert into rychhdtj(RYCHDM,TJZDM,TJZDLYB,TJ,ZDCZ,XN) values(";//�����ƺ�
			tempSql = "select rychdm jxjdm from rychdmb ";
			if(jxjdm!=null && !jxjdm.equals("")){
				whereSql += " and rychdm=" + jxjdm;
			}
//			if(jxjfl!=null && !jxjfl.equalsIgnoreCase("")){
//				whereSql += " and rychfl=" + DealString.toGBK(jxjfl);
//			}
			tableName = "rychhdtj";
		}
		
		
		jxjList = dao.getList(tempSql + whereSql, new String[]{}, new String[]{"jxjdm"});
		
		//���sql �����뵽������
		try {
		boolean dl = dao.runUpdate2("delete from " + tableName + whereSql, new String[]{});
		for(int j=0; j<jxjList.size(); j++){
			HashMap<String, String> jxjdmV = (HashMap<String, String>)jxjList.get(j);		
			
				if(dl){
					StringBuffer sb = new StringBuffer();
					for(int i=0; i<value.length; i++){						
						if(cztjV[i]!=null && !cztjV[i].equals("") && value[i]!=null && !value[i].equals("")){							
							sb.append(sql);
							sb.append("'" + (jxjdmV.get("jxjdm") ==null ? "" : jxjdmV.get("jxjdm")) + "',");//��ѧ�����			
							sb.append("'" + input[i] + "',");
							sb.append("'" + tjzddyb + "',");
							sb.append("'" + (value[i]==null ? "" : value[i]) + "',");
							sb.append("'" + (cztjV[i]==null ? "" : cztjV[i]) + "',");
							sb.append("'" + (xn==null ? "" : xn) + "')");
							sb.append("!!##!!");
						}
						
					}	
					sqlV = sb.toString().split("!!##!!");
					int[] result = dao.runBatch(sqlV);
					for(int m=0; m<result.length; m++){
						if(result[m]<1){
							flag = "false";
							break;
						}
					}
				}
		}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}	
		return flag;
	}
	
	/**
	 * �����������
	 * @param tableName
	 * @param pkValue
	 * @param request
	 * @return boolean 
	 * */
	public boolean deleteTjsz(String tableName,String pkValue,HttpServletRequest request){
		boolean flag = false;
		String primaryKey = "";
		if(tableName != null && tableName.equals("jxjdmb")){
			primaryKey = "xn||jxjdm||tjzdm";
			tableName = "jxjhdtj";
		}
		if(tableName != null && tableName.equals("rychdmb")){
			primaryKey = "xn||rychdm||tjzdm";
			tableName = "rychhdtj";
		}
		try {
			flag = StandardOperation.delete(tableName, primaryKey, pkValue, request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ��ȡ��ѧ������ѧ��ѧ��ѧ�����ۺ����ò�����Ϣ
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return List
	 * */
	public HashMap<String, String> getStuZhszcpxx(String xh,String xn,String xq){
		String outputValue[] = new String[]{"xh","xm","xb","xymc","zymc","bjmc","nj",
				 "dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl","stszzf","sztzzf","zhszcpzf",
				 "zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk"};
		String sql = "select * from view_zhszcp where xh=? and xn=? and xq=?";
		return dao.getMap(sql, new String[]{xh,xn,xq}, outputValue);
	}
	
	/**
	 * �ж�ѧ���Ƿ���ƶ����
	 * @param xh
	 * @return boolean
	 * */
	public boolean isKns(String xh){
		return dao.isKns(xh);		
	}
	
	/**
	 * ��ѧ�����뱣��
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveJxjsq(WhlgJxjModel model,HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsjxjb";
		String xh = model.getXh();
		String sqly = DealString.toGBK(model.getSqly());
		String jxjdm = model.getJxjdm();
		String lwmc = DealString.toGBK(model.getLwmc());
		String qkmc = DealString.toGBK(model.getQkmc());
		String fbsj = model.getFbsj();
		String sfdyzz = DealString.toGBK(model.getSfdyzz());
		String gkfs = model.getGkfs();
		String gzashjqk = DealString.toGBK(model.getGzashjqk());
//		String sbdj = DealString.toGBK(model.getSbdj());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String tjFlag = DealString.toGBK(model.getTjFlag());
		String sqsj = dao.getOneRs("select to_char(sysdate,'yyyymmddhh24miss') sj from dual", new String[]{}, "sj");		
		
		String[] columns = {"xh","jxjdm","xn","nd","xq","lwmc","qkmc","fbsj","sfdyzz","gkfs","gzashjqk","sqsj","sqly","tjflag"};
		String[] values = {xh, jxjdm, xn, nd, xq, lwmc, qkmc, fbsj, sfdyzz, gkfs, gzashjqk, sqsj, sqly,tjFlag};
		
		try {
			flag = StandardOperation.delete("xsjxjb", "xh||jxjdm||nd||xn||xq", xh+jxjdm+nd+xn+xq, request);
			if(flag){		
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ������Ŀ�����ȡ��Ŀ�ķ���
	 * @param tableName
	 * @param xmdm
	 * @return String
	 * */
	public String getXmfl(String tableName,String xmdm){
		String sql = "";
		if(tableName!=null && tableName.equalsIgnoreCase("jxjdmb")){
			sql = "select jxjfl xmfl from jxjdmb where jxjdm=?";
		}else{
			sql = "select rychfl xmfl from rychdmb where rychdm=?";
		}
		return dao.getOneRs(sql, new String[]{xmdm},"xmfl");
	}
	
	/**
	 * �ж�ѧ�����ڵİ༶�Ƿ����Ƚ��༶
	 * @param xh
	 * @param xn
	 * @param xjbjlb
	 * @return boolean
	 * */
	private boolean isXjbj(String xh,String xn,String xq,String xjbjlb){
		boolean flag = false;
		String sql = "select count(*) count from view_pjpy_xjbj where xn=? and xq=? and bjdm=(select bjdm from view_xsjbxx where xh=?) and xjbjlbmc=?";
		flag = Integer.parseInt(dao.getOneRs(sql, new String[]{xn,xq,xh,xjbjlb}, "count"))>0 ? true : false;
		return flag;
	}
	
	/**
	 * �������
	 * @param xh
	 * @param jxjdm
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public String checkDemand(String xh, String jxjdm,String tableName){
		String result = "";
		String xn = getNdAndXyxx("").get("jxjsqxn");
		String xq = getNdAndXyxx("").get("jxjsqxq");
		String sql = "select tjzdm,tj,zdcz from jxjhdtj where jxjdm=? and xn=?";
		if(tableName!=null && tableName.equalsIgnoreCase("rychdmb")){
			sql = "select tjzdm,tj,zdcz from rychhdtj where rychdm=? and xn=?";
		}
		List list = dao.getList(sql, new String[]{jxjdm,xn}, new String[]{"tjzdm","tj","zdcz"});
		sql = "select dcj,xxpjcj,sztzzf,dkzdfs,zhszcpcjpmbl,xxpjcjpmbl,wygjqk from zhszcp where xh=? and xn=?";
		HashMap<String, String> tmpMap = dao.getMap(sql, new String[]{xh,xn}, new String[]{"dcj","xxpjcj","sztzzf","dkzdfs","zhszcpcjpmbl","xxpjcjpmbl","wygjqk"});;
		StringBuffer sb = new StringBuffer();
		
		for(int i=0;i<list.size();i++){
			HashMap<String, String> map = (HashMap<String, String>) list.get(i);
			String zdm = map.get("tjzdm");
			
			if(!(map.get("tjzdm").equals("wygjqk") || map.get("tjzdm").equals("sfpks"))){//������Ĳ���				
				int value = Integer.parseInt(tmpMap.get(zdm)==null || tmpMap.get(zdm).equals("") ? "0" : tmpMap.get(zdm));
				int tj = Integer.parseInt(map.get("tj"));
				if(tableName!=null && tableName.equalsIgnoreCase("rychdmb")){//�����ƺ�
					if(zdm.equalsIgnoreCase("zhszcpcjpmbl") || zdm.equalsIgnoreCase("xxpjcjpmbl")){
						if(isXjbj(xh,xn,xq,"����༶")){//����༯��
							tj = tj+5;
						}
						if(isXjbj(xh,xn,xq,"����༶")){//����༶��
							tj = tj+10;
						}
					}
					
				}
				if(map.get("zdcz").equals(">=")){//���ڵ���
					if(!(value>=tj)){
						sb.append(zdm + ",");
					}					
				}
				if(map.get("zdcz").equals(">")){//����
					if(!(value>tj)){
						sb.append(zdm + ",");
					}
				}
				if(map.get("zdcz").equals("=")){//����
					if(value!=tj){
						sb.append(zdm + ",");
					}
				}
				if(map.get("zdcz").equals("<")){//С��
					if(!(value<tj)){
						sb.append(zdm + ",");
					}
				}
				if(map.get("zdcz").equals("<=")){//С�ڵ���
					if(!(value<=tj)){
						sb.append(zdm + ",");
					}
				}
			}
			
			if(map.get("tjzdm").equals("wygjqk")){//�ַ���Ĳ��� ����������
				if(!map.get("tj").equals(tmpMap.get("wygjqk"))){
					sb.append(zdm + ",");
				}
			}
			if(map.get("tjzdm").equals("sfpks")){//�ַ���Ĳ��� �Ƿ�ƶ����
				String sfpks = dao.isKns(xh) ? "��" : "��";
				if(!map.get("tj").equals(sfpks)){
					sb.append(zdm + ",");
				}
			}
			
		}
		if(sb!=null && !sb.toString().equals("")){
			sb.deleteCharAt(sb.length()-1);
		}
		String[] nameCN = sb.toString().split(",");
		nameCN = dao.getColumnNameCN(nameCN, "zhszcp");
		for(int j=0; j<nameCN.length; j++){
			if(nameCN[j].equals("sfpks")){
				nameCN[j] = "�Ƿ�ƶ����";
			}
			result += nameCN[j] + " ";
		}

		return result;
	}
	
	/**
	 * ��⽱ѧ��������
	 * @param model
	 * @return boolean 
	 * */
	public int checkPersonNumber(WhlgJxjModel model){
		//boolean flag = false;
		String bmlb = "";
		String xh = model.getXh();
		String jxjdm = model.getJxjdm();
		String xn = model.getXn();
		String nd = model.getNd();
		String userType = model.getUserType();
		String userOper = "";
		String sql = "";
		String rs = "";
		if(userType!=null && userType.equals("fdy")){
			userOper = "fdysh";
		}else if(userType!=null && userType.equals("xy")){
			userOper = "xysh";
		}else{
			userOper = "xxsh";
		}
		
		sql = "select distinct bmlb from xyjxjrs where jxjdm=? and xn=? and nd=? and jxjrs is not null";
		bmlb = dao.getOneRs(sql, new String[]{jxjdm,xn,nd}, "bmlb");//��ȡ��ѧ���������÷�ʽ
		sql = "select count(*) count from view_xsjxjb where " + bmlb + "=(select "+ bmlb +" from view_xsjbxx where xh=?) and "+userOper+"='ͨ��'";
		
		rs = dao.getOneRs(sql, new String[]{xh}, "count");
		int tgrs =  Integer.parseInt(rs==null || rs.equals("") ? "0" : rs);//���ѧ���Ѿ����ͨ��������	
		
		sql = "select jxjrs from xyjxjrs where jxjdm=? and xn=? and nd=? and bmdm=(select "+ bmlb +" from view_xsjbxx where xh=?)";
		rs = dao.getOneRs(sql,new String[]{jxjdm,xn,nd,xh}, "jxjrs");
		int yqrs = Integer.parseInt(rs==null || rs.equals("") ? "0" : rs);//���յ����������
		
		return yqrs-tgrs;
	}
	
	/**
	 * �ж�ѧ���Ƿ�Ϊ����
	 * @param xh
	 * @return boolean
	 * */
	public boolean checkIsNewStu(String xh){
		boolean flag = false;
		String nd = Base.currNd;
		String nj = dao.getOneRs("select nj from view_xsjbxx where xh=?", new String[]{xh}, "nj");
		if(Integer.parseInt(nd)==Integer.parseInt(nj) || Integer.parseInt(nd)-1==Integer.parseInt(nj)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ��ѯѧ����ѧ��������Ϣ
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjInfo(String pk,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_xsjxjb where " + pk + " = ?";
		map = dao.getMap(sql, new String[]{pkValue}, new String[]{"xh","jxjdm","nd","xn","xq","lwmc","qkmc","fbsj","sfdyzz","gkfs","gzashjqk","sqly"});
		return map;
	}
	
	/**
	 * ��ȡ����б�
	 * @param num
	 * @return List
	 * */
	public List getChkList(int num){
		return dao.getChkList(num);
	}
	
	/**
	 * ��ȡѧ�����뽱ѧ�����Ϣ
	 * @param pkValue
	 * @param userType
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjCheckInfo(String pkValue,String userType){
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = "xn||nd||xh||jxjdm";
		String[] output = {"pk","xh","xm","xb","nj","xn","nd","xq","xymc","zymc","bjmc","jxjdm","jxjmc","sqly","lwmc","qkmc","fbsj","sfdyzz","gkfs","gzashjqk",
				  "dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl","stszzf","sztzzf","zhszcpzf","zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk","xxshyj",
				  "tjflag","xyshyj","fdyyj","yesNo"};
		String sql = "";
		if(userType!=null && userType.equals("fdy")){//����Ա
			sql = "select xn||nd||xh||jxjdm pk,xh,xm,xb,nj,xn,nd,xq,xymc,zymc,bjmc,jxjdm,jxjmc,sqly,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk," +
				  "dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,xxshyj," +
				  "tjflag,xyshyj,fdyyj,fdysh yesNo from view_xsjxjb where " + pk + "=?";			
		}else if(userType!=null && userType.equals("xy")){//ѧԺ
			sql = "select xn||nd||xh||jxjdm pk,xh,xm,xb,nj,xn,nd,xq,xymc,zymc,bjmc,jxjdm,jxjmc,sqly,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk," +
			  "dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,sztzzf,stszzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,xxshyj," +
			  "tjflag,xyshyj,fdyyj,xysh yesNo from view_xsjxjb where " + pk + "=?";	
		}else{//ѧУ
			sql = "select xn||nd||xh||jxjdm pk,xh,xm,xb,nj,xn,nd,xq,xymc,zymc,bjmc,jxjdm,jxjmc,sqly,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk," +
			  "dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,xxshyj," +
			  "tjflag,xyshyj,fdyyj,xxsh yesNo from view_xsjxjb where " + pk + "=?";	
		}
		map = dao.getMap(sql, new String[]{pkValue}, output);
		return map;
	}
	
	/**
	 * ���潱ѧ����˽��
	 * @param model
	 * @param request
	 * @return boolean 
	 * */
	public boolean saveCheckPrise(WhlgJxjModel model,HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsjxjb";
		String primaryKey = "xn||nd||xh||jxjdm";
		String userType = model.getUserType();
		String pkValue = model.getPkVal();
		String yesNo = DealString.toGBK(model.getYesNo());
		String fdyyj = DealString.toGBK(model.getFdyyj());
		String xxshyj = DealString.toGBK(model.getXxshyj());
		String xyshyj = DealString.toGBK(model.getXyshyj());
		try {
			if(userType!=null && userType.equals("fdy")){//����Ա
				flag = StandardOperation.update(tableName, new String[]{"fdysh", "fdyyj"}, new String[]{yesNo,fdyyj}, primaryKey, pkValue, request);
				
			}else if(userType!=null && userType.equals("xy")){//ѧԺ
				flag = StandardOperation.update(tableName, new String[]{"xysh", "xyshyj"}, new String[]{yesNo,xyshyj}, primaryKey, pkValue, request);
			}else{//ѧУ
				flag = StandardOperation.update(tableName, new String[]{"xxsh", "xxshyj"}, new String[]{yesNo,xxshyj}, primaryKey, pkValue, request);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ѧ�������ƺ����뱣�� 
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveRychsq(WhlgJxjModel model,HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsrychb";
		String primaryKey = "xh||xn||nd||xq";
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String rychdm = model.getRychdm();
		String sfdlsq = DealString.toGBK(model.getSfdlsq());
		String dlsqly = DealString.toGBK(model.getDlsqly());
		String sqly = DealString.toGBK(model.getSqly());
		
		String value = xh+xn+nd+xq;
		String[] columns = {"xh","nd","xn","xq","rychdm","sfdlsq","dlsqly","sqly"};
		String[] values = {xh, nd, xn, xq, rychdm, sfdlsq, dlsqly, sqly};
		try {
			flag = StandardOperation.delete(tableName, primaryKey, value, request);
			if(flag){
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * ��ѯѧ�������ƺ������Ϣ
	 * @param pk
	 * @param pkValue
	 * @param userType
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsrychInfo(String pk, String pkValue,String userType){
		String sql = "";
		String[] output = {"pk","xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xq","rychdm","rychmc","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl",
				"stszzf","sztzzf","zhszcpzf","zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk","fdyyj","xyyj","xxyj","yesNo"};
		if(userType!=null && userType.equalsIgnoreCase("fdy")){//����Ա
			sql = "select xn||nd||xh||rychdm pk, xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,rychdm,rychmc,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl," +
					"stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,fdyyj,xyyj,xxyj,fdysh yesNo from view_xsrychb where " + pk + "=?";
		}else if (userType!=null && userType.equalsIgnoreCase("xy")){//ѧԺ
			sql = "select xn||nd||xh||rychdm pk, xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,rychdm,rychmc,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl," +
			"stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,fdyyj,xyyj,xxyj,xysh yesNo from view_xsrychb where " + pk + "=?";
		}else{//ѧУ
			sql = "select xn||nd||xh||rychdm pk, xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,rychdm,rychmc,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl," +
			"stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,fdyyj,xyyj,xxyj,xxsh yesNo from view_xsrychb where " + pk + "=?";
		}		
		return dao.getMap(sql, new String[]{pkValue}, output);
	}
	
	/**
	 * ѧ�������ƺŵ�����˱���
	 * @param model
	 * @param request
	 * @return boolean s
	 * */
	public boolean saveCheckRych(WhlgJxjModel model, HttpServletRequest request){
		boolean flag = false;
		String pk = "xn||nd||xh||rychdm";
		String tableName = "xsrychb";
		String pkValue = model.getPkVal();
		String userType = model.getUserType();
		String[] columns = {"xxsh","xxshyj"};
		String[] values = null;
		
		String yesNo = DealString.toGBK(model.getYesNo());
		String fdyyj = DealString.toGBK(model.getFdyyj());
		String xyyj = DealString.toGBK(model.getXyshyj());
		String xxyj = DealString.toGBK(model.getXxshyj());
		
		values = new String[]{yesNo, xxyj};		
		if(userType!=null && userType.equalsIgnoreCase("fdy")){//����Ա
			columns = new String[]{"fdysh", "fdyyj"};
			values = new String[]{yesNo, fdyyj};
		}else if(userType!=null && userType.equalsIgnoreCase("xy")){//ѧԺ
			columns = new String[]{"xysh", "xyyj"};
			values = new String[]{yesNo, xyyj};
		}
		
		try{
			flag = StandardOperation.delete(tableName, pk, pkValue, request);
			if(flag){
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ѧ�������ƺ��������
	 * @param pkValue
	 * @param userType
	 * @param yesNo
	 * @param request
	 * */
	public boolean checkRychAll(String[] pkValue,String userType,String yesNo, HttpServletRequest request)throws Exception{
		String sj = DateUtils.getTime();
		boolean flag = false;
		String tableName = "xsrychb";		
		String primaryKey = "xn||nd||xh||rychdm";
		String[] columns = new String[]{"xxsh", "xxshsj"};
		if(yesNo.equals("pass")){
			yesNo = "ͨ��";
		}else if(yesNo.equals("nopass")){
			yesNo = "��ͨ��";
		}
		
		String xxdm = Base.xxdm;
		if (Globals.XXDM_WHLGDX.equalsIgnoreCase(xxdm)) {
			if(userType!=null && userType.equalsIgnoreCase("fdy")){
				columns = new String[]{"fdysh", "xyshsj"};
			}else if(userType!=null && userType.equalsIgnoreCase("xy")){
				columns = new String[]{"xysh", "xyshsj"};
			}
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			String[] sqlArr = new String[pkValue.length];
			for (int i=0;i<pkValue.length;i++) {
				StringBuffer sql = new StringBuffer("update xsrychb set xxsh='");
				sql.append(yesNo);
				sql.append("',xxshsj='");
				sql.append(sj);
				sql.append("' where xh||xn||rychdm='");
				sql.append(pkValue[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		} else {
			if("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)){
				columns = new String[]{"xysh","xyshsj"};
			}
		}
		
		try{
			for(int i=0; i<pkValue.length; i++){
				flag = StandardOperation.update(tableName, columns, new String[]{yesNo,sj}, primaryKey, pkValue[i], request);
				if(flag == false){
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * ��ȡ�������
	 * @param model
	 * @return String
	 * */
	public String getWhereSql(WhlgJxjModel model){
		String jxjdm = model.getJxjdm();
		String rychdm = model.getRychdm();
		String xh = model.getXh();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if(xh!=null && !xh.equalsIgnoreCase("")){
			sb.append(" and xh='");
			sb.append(xh);
			sb.append("' ");			
		}
		if(xydm!=null && !xydm.equalsIgnoreCase("")){
			sb.append(" and xydm ='");
			sb.append(xydm);
			sb.append("' ");
		}
		if(zydm!=null && !zydm.equalsIgnoreCase("")){
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("' ");
		}
		if(bjdm!=null && !bjdm.equalsIgnoreCase("")){
			sb.append(" and bjdm ='");
			sb.append(bjdm);
			sb.append("' ");
		}
		if(nj!=null && !nj.equalsIgnoreCase("")){
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("' ");
		}
		if(xn!=null && !xn.equalsIgnoreCase("")){
			sb.append(" and xn='");
			sb.append(xn);
			sb.append("' ");
		}
		if(nd!=null && !nd.equalsIgnoreCase("")){
			sb.append(" and nd='");
			sb.append(nd);
			sb.append("' ");
		}
		if(xq!=null && xq.equalsIgnoreCase("")){
			sb.append(" and xq='");
			sb.append(xq);
			sb.append("' ");
		}
		if(jxjdm!=null && !jxjdm.equalsIgnoreCase("")){
			sb.append(" and jxjdm='");
			sb.append(jxjdm);
			sb.append("' ");
		}
		if(rychdm!=null && !rychdm.equalsIgnoreCase("")){
			sb.append(" and rychdm='");
			sb.append(rychdm);
			sb.append("' ");
		}
		return sb.toString();
	}
	
	/**
	 * ��ȡ��ѧ����ർ���Ĳ�ѯ��� 
	 * @param model
	 * @return String
	 * */
	public String getPriseExpSql(WhlgJxjModel model){
		String sql = "";
		String jxjdm = model.getJxjdm();
		String jxjfl = getXmfl("jxjdmb", jxjdm);
		
		if(jxjfl!=null && (jxjfl.equalsIgnoreCase("���ҡ�ѧУ��ѧ��") 
				|| jxjfl.equalsIgnoreCase("���ҽ�ѧ��")
				|| jxjfl.equalsIgnoreCase("ѧУ��ѧ��")
				|| jxjfl.equalsIgnoreCase("ѧУ�����ҽ�ѧ��"))){//���ҡ�ѧУ��ѧ��
			sql = "select xh,xm,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf," +
					"sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk," +
					"jxjmc from view_xsjxjb ";
		}else if(jxjfl!=null && jxjfl.equalsIgnoreCase("��άѧ��")){//��άѧ��
			sql = "select xh,xm,jxjmc ��άѧ�� from view_xsjxjb ";
		}else if(jxjfl!=null && (jxjfl.equalsIgnoreCase("ѧ�����н�") || jxjfl.equalsIgnoreCase("ѧ�����н�ѧ��"))){//ѧ�����н�
			sql = "select xh,xm,lwmc ,qkmc ,fbsj ,sfdyzz from view_xsjxjb ";
		}else if (jxjfl!=null && jxjfl.equalsIgnoreCase("������ѧ��")){//������ѧ��
			sql = "select xh,xm,gkfs,gzashjqk,jxjmc from view_xsjxjb ";
		}else if(jxjfl!=null && (jxjfl.equalsIgnoreCase("���") || jxjfl.equalsIgnoreCase("���ѧ��"))){//���
			sql = "select xh,xm,jxjmc �������ֵ�� from view_xsjxjb ";
		}else{
			sql = "select * from view_xsjxjb ";
		}
		
		sql += getWhereSql(model);	//��ϲ�ѯ����	
		return sql;
	}
	
	/**
	 * ��ȡ�����ƺŷ��ർ���Ĳ�ѯ��� 
	 * @param model
	 * @return String
	 * */
	public String getRychExpSql(WhlgJxjModel model){
		String sql = "";
		String rychdm = model.getRychdm(); 
		String rychfl = getXmfl("rychdmb", rychdm);
		
		if(rychfl!=null && rychfl.equalsIgnoreCase("�����ƺ�")){//�����ƺ�
			sql = "select xh,xm,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf,sztzzf,zhszcpzf,zhszcpcjpm," +
				  "zhszcpcjpmbl,dkzdfs,wygjqk,rychmc from view_xsrychb ";
		}else if(rychfl!=null && rychfl.equalsIgnoreCase("�����ҵ��")){//�����ҵ��
			sql = "select xh, xm, sqly, sfdlsq from view_xsrychb ";
		}else {
			sql = "select * from view_xsrychb ";
		}		
		sql += getWhereSql(model);	//��ϲ�ѯ����	
		return sql;
	}
}
