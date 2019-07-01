package xgxt.pjpy.tyb.zhszcp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.form.User;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.qgzx.QgzxTyForm;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class PjpyZhszcpDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * ��ȡ��ѯ����
	 * @param model
	 * @return StringBuilder
	 * */
	public StringBuilder getWhereSql(PjpyZhszcpwhActionForm model){
		value = new ArrayList<String>();
		String xh = model.getQuerylike_xh();
		String xm = model.getQuerylike_xm();
		String xn = model.getQueryequals_xn();
		String nd = model.getQueryequals_nd();
		String xq = model.getQueryequals_xq();
		String xydm = model.getQueryequals_xydm();
		String zydm = model.getQueryequals_zydm();
		String bjdm = model.getQueryequals_bjdm();
		String nj = model.getQueryequals_nj();
		
		StringBuilder sb = new StringBuilder("where 1=1 ");
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(zydm !=null && !zydm.equalsIgnoreCase("")){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(bjdm !=null && !bjdm.equalsIgnoreCase("")){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(xh !=null && !xh.equalsIgnoreCase("")){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(xm !=null && !xm.equalsIgnoreCase("")){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(nd !=null && !nd.equalsIgnoreCase("")){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(xq !=null && !xq.equalsIgnoreCase("")){
			sb.append( " and xq=?");
			value.add(xq);
		}
		return sb;
	}
	
	/**
	 * ��ѯ�Զ����ֶ���Ϣ
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> getZdyZdList(String tableName) {
		return getList("select zdid,zdmc from py_bdszb where tabname = ?",
				new String[] { tableName }, new String[] { "zdid", "zdmc" });
	}
	
	/**
	 * ��ѯ�Զ����ֶ���Ϣ
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> getZdyZdList(String tableName, String cxxs) {
		return getList("select zdid,zdmc from py_bdszb where tabname = ? and cxxs = ?",
				new String[] { tableName, cxxs }, new String[] { "zdid", "zdmc" });
	}
	
	/**
	 * ��ѯ�ۺ����ʲ������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpResult(PjpyZhszcpModel model,
			String[] colList,HashMap<String, String[]> zdyzdMap, String tableName, String fdm, String[] zbzdArray) throws Exception {
		String userName = model.getUserName();
		String userType = model.getUserType();
		String isFdy = model.getIsFdy();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getXq();
		
		String shjb = model.getShjb() != null &&  model.getShjb().length>0 ? model.getShjb()[0] : "";//��˼���
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[] { "nj", "xydm", "zydm", "bjdm",
				"xqmc", "fdysh", "xysh", "xxsh" }, new String[]{"xh","xm"}, model);
		String whereSql = makeQuery.getQueryString();
		
		StringBuilder sql = new StringBuilder("select "); 
		
		//ƴ�Ӹ�����Ҫ���õ�SQL
		sql.append(appendDisSql(userType, isFdy, shjb));		
		sql.append(" a.*,rownum r from (");
		sql.append("select ");
		
		if (zbzdArray != null ) {
			for (int i=0;i<zbzdArray.length;i++) {
					sql.append("a.");
					sql.append(zbzdArray[i]);
					sql.append(",");
			}
		}
		
		
		//sql.append("rownum r ");
		sql = new StringBuilder(sql.delete(sql.length() - 1, sql.length()));
		
		//�Զ����ֶ�
		String[] enzdArr = zdyzdMap.get("zdid");
		
		sql.append(appendZdyQueryZd(tableName, enzdArr));
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" a) a ");
		
		//����ԱĬ��ֻ�ܶԲ�ѯ���Լ������༶
		if(UserTypePd.isFdy(isFdy)){
			whereSql += " and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
		}
		
		if(StringUtils.isNotNull(xn) && !"��".equalsIgnoreCase(xn)){
			whereSql += " and xn='" + xn + "'";
		}
		if(StringUtils.isNotNull(xq) && !"��".equalsIgnoreCase(xq)){
			whereSql += " and xq='" + xq + "'";
		}
		if(StringUtils.isNotNull(nd) && !"��".equalsIgnoreCase(nd)){
			whereSql += " and nd='" + nd + "'";
		}
		
		
		return CommonQueryDAO.commonQuery(sql.toString(), whereSql, makeQuery.getInputList(), colList, model);
	}
	
	/**
	 * ƴ�������ַ���
	 * @param zdArr
	 * @return
	 */
	public String appendQueryZd(String[] zdArr) {
		StringBuilder result = new StringBuilder("");
		if (zdArr != null && zdArr.length > 0) {
			for (String s : zdArr) {
				result.append(s);
				result.append(",");
			}
		}
		return result.toString();
	}
	
	/**
	 * ƴ���Զ����ֶα���
	 * @param tableName
	 * @param zdArr
	 * @return
	 */
	public String appendZdyQueryZd(String tableName, String[] zdArr){
		StringBuilder result = new StringBuilder();
		if (zdArr != null && zdArr.length > 0) {
			for (String s : zdArr) {
				result.append(", (select bcnr from PY_BDSZ_BCNR where tabname='");
				result.append(tableName);
				result.append("' and zdid='");
				result.append(StringUtils.isNull(s) ? "" : s.toLowerCase());
				result.append("' and zbid=a.pk and rownum=1) ");
				result.append(s);
			}
		}
		return result.toString();
	}
	
	/**
	 * ��ȡ��ͬ���۲���ͼ��
	 * @return
	 */
	public String appendQueryViewName() {
		String viewName = "view_gzdx_pdbxfb a";
		return viewName;
	}
	
	/**
	 * �ж�Ҫ������¼�Ķ�дȨ
	 * @param userType
	 * @param isFdy
	 * @param shjb
	 * @return String
	 */
	public String appendDisSql(String userType, String isFdy, String shjb) {
		String result = "";
		if("3".equalsIgnoreCase(shjb)){//�������
			if (UserTypePd.isFdy(isFdy)) {
				result = " (case when xysh='ͨ��' or xxsh='ͨ��' then 'disabled' else '' end) dis,(case nvl(fdysh,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end)bgcolor,";
			} else {
				result = UserTypePd.isXy(userType) ? " (case when xxsh='ͨ��' then 'disabled' else '' end) dis,(case nvl(xysh,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end)bgcolor," 
						                           : " '' dis,(case nvl(xxsh,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end)bgcolor,";
			}
		}else if("2".equalsIgnoreCase(shjb)){//�������
			result = UserTypePd.isXy(userType) ? " (case when xxsh='ͨ��' then 'disabled' else '' end) dis,(case nvl(xysh,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end)bgcolor," 
					                           : " '' dis,(case nvl(xxsh,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end)bgcolor,";
		}else if("1".equalsIgnoreCase(shjb)){//һ��
			result = "'' dis,(case nvl(xxsh,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end)bgcolor,";
		}else{
			result = "'' dis,'' bgcolor,";
		}
		return result;
	}
	
	/**
	 * ��ѯ�Զ����ֶ�Ҫ������ֶ�
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public String[] queryZdyOutputZd(String tableName) throws SQLException {
		return getArray(
						"select zdid from py_bdszb where cxxs='��ʾ' and tabname = ? order by cxxspx asc",
						new String[] { tableName }, "zdid");
	}
	
	/**
	 * ƴ��Ҫ������ֶ�����
	 * @param tableName ������
	 * @param colList  ����Ҫ������ֶ�����
	 * @return
	 * @throws SQLException
	 */
	public String[] queryOutputZdArr(String tableName, String[] colList)
			throws SQLException {
		String[] resultArr = new String[] {};
		
		//��ѯ�Զ����ֶ����������
		String[] zdArr = queryZdyOutputZd(tableName);
		if (zdArr != null && zdArr.length > 0) {
			resultArr = new String[colList.length + zdArr.length];
			for (int i = 0; i < colList.length; i++) {
				resultArr[i] = colList[i];
			}
			for (int i = colList.length; i < resultArr.length; i++) {
				resultArr[i] = zdArr[i];
			}
		}
		return resultArr;
	}
	
	/**
	 * ��ѯ��������Ĭ��Ҫ��ѯ�ֶ��б�
	 * 
	 * @param tableName
	 * @return
	 */
	public List<String[]> queryPjpyzdcxList(String tableName) {
		return rsToVator("select en,cn from pjpy_zdcxb where bm = ? order by to_number(px) asc",
				new String[] { tableName }, new String[] { "en", "cn" });
	}
	
	/**
	 * ��ѯ�۲���Ŀ����˼��� 1������xxsh, 2������xysh,xxsh, 3������fdysh,xysh,xxsh
	 * @param tableName
	 * @param fdm
	 * @return
	 */
	public String[] queryZcxmShzdList(String tableName, String fdm) {
		String shjg = getOneRs(
				"select shjb from pjpy_zctjszb where bm = ? and fdm = ?",
				new String[] { tableName, fdm }, "shjb");
		return "1".equalsIgnoreCase(shjg) ? new String[]{"xxsh"} : ("2"
				.equalsIgnoreCase(shjg) ? new String[]{"xysh", "xxsh"} : ("3"
				.equalsIgnoreCase(shjg) ? new String[]{"fdysh", "xysh", "xxsh"} : new String[]{}));
	}
	
//	/**
//	 * ��ȡ����б�
//	 * @param type
//	 * @return List<HashMap<String, String>>
//	 * */
//	public List<HashMap<String, String>> getChkList(int type){
//		return thisgetChkList(type);
//	}
	
	/**
	 * ͨ���ֶ�ID�ͱ�����ѯ���ֶ�����Ӧ�������б�����
	 * @param zdid
	 * @param tabName
	 * @return
	 */
	public List<HashMap<String, String>> queryZdyzdSelectOption(String zdid,
			String tabName) {
		return getList(
				"select opid dm,opmc mc,? zdid from ty_bdszxxb where szbzj=? ",
				new String[] { zdid, zdid + tabName }, new String[] { "dm",
						"mc", "zdid" });
	}
	
	/**
	 * ��ȡ�Զ����ֶα�������
	 * @param tableName
	 * @param pkValue
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZdyzybcnr (String tableName, String pkValue){
		String whereSql = " where tabname=? and zbid=?";
		return CommonQueryDAO.commonQueryforList("py_bdsz_bcnr", whereSql,
				new String[] { tableName, pkValue }, new String[] { "zdid",
						"bcnr" }, "");		
	}
	
	/**
	 * �����Զ����ֶ���Ϣ,valueMap�б������pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public int[] saveZdyzdNr(String tableName, HashMap<String, String>[] valueMap){
		//ɾ�����������ֶ�������Ϣ
		String[] sqlArr = {};
		
		
		int[] result = null;
		for(int i=0; i< valueMap.length; i++){
			HashMap<String, String> tmpMap = valueMap[i];
			if(tmpMap != null){
				String[] mapSql = new String[tmpMap.keySet().size()*2];
				String pkValue = tmpMap.get("pkValue");
				
				//String zcdm = tmpMap.isEmpty() ? "" : tmpMap.keySet().iterator().next();		
				int n = 0;
				for(String key : tmpMap.keySet()){
					StringBuilder zdz = new StringBuilder();
					if(!"pkValue".equalsIgnoreCase(key)){
						//zdid
						zdz.append("'");
						zdz.append(key);
						zdz.append("',");
						//tabname
						zdz.append("'");
						zdz.append(tableName);
						zdz.append("',");
						//zdid
						zdz.append("'");
						zdz.append(pkValue);
						zdz.append("',");
						//bcnr
						zdz.append("'");
						zdz.append(tmpMap.get(key));
						zdz.append("'");
						mapSql[n++] = StringUtils.joinStr("delete from py_bdsz_bcnr where tabname='",
								tableName,
								"' and zbid='" ,
								pkValue ,
								"'", " and zdid='", key, "'");
						mapSql[n++] = "insert into py_bdsz_bcnr(zdid,tabname,zbid,bcnr)values(" + zdz+ ")" ;
						//n++;
					}
				}		
				sqlArr = StringUtils.joinStrArr(sqlArr, mapSql);
			}
		}
		//��������		
		try{		
			result = runBatch(sqlArr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * �����Զ����ֶ���Ϣ,valueMap�б������pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public String[] saveZdyzdNrByString(String tableName, HashMap<String, String>[] valueMap){
		//ɾ�����������ֶ�������Ϣ
		String[] sqlArr = {};
		
		
		int[] result = null;
		for(int i=0; i< valueMap.length; i++){
			HashMap<String, String> tmpMap = valueMap[i];
			if(tmpMap != null){
				String[] mapSql = new String[tmpMap.keySet().size()*2];
				String pkValue = tmpMap.get("pkValue");
				
				//String zcdm = tmpMap.isEmpty() ? "" : tmpMap.keySet().iterator().next();		
				int n = 0;
				for(String key : tmpMap.keySet()){
					StringBuilder zdz = new StringBuilder();
					if(!"pkValue".equalsIgnoreCase(key)){
						//zdid
						zdz.append("'");
						zdz.append(key);
						zdz.append("',");
						//tabname
						zdz.append("'");
						zdz.append(tableName);
						zdz.append("',");
						//zdid
						zdz.append("'");
						zdz.append(pkValue);
						zdz.append("',");
						//bcnr
						zdz.append("'");
						zdz.append(tmpMap.get(key));
						zdz.append("'");
						mapSql[n++] = StringUtils.joinStr("delete from py_bdsz_bcnr where tabname='",
								tableName,
								"' and zbid='" ,
								pkValue ,
								"'", " and zdid='", key, "'");
						mapSql[n++] = "insert into py_bdsz_bcnr(zdid,tabname,zbid,bcnr)values(" + zdz+ ")" ;
						//n++;
					}
				}		
				sqlArr = StringUtils.joinStrArr(sqlArr, mapSql);
			}
		}
//		//��������		
//		try{		
//			result = runBatch(sqlArr);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		return sqlArr;
	}
	
	
	/**
	 * ɾ���Զ����ֶ�����
	 * @param primaryKeys
	 * @param tabName
	 * @param bdszBcnrb
	 * @param user
	 * @return boolean
	 * */
	public boolean deleteBdsznr(String[] primaryKeys,
					            String tabName,
					            String bdszBcnrb,
					            User user){
		boolean result = false;//�������
		String[] sqlArr = new String[primaryKeys.length];
		
		for(int i=0; i<primaryKeys.length; i++){
			sqlArr[i] = "delete from " + bdszBcnrb + " where tabname ='" + tabName + "' and zbid='" + primaryKeys[i] + "'";
		}
		
		try{
			int[] tmpCount = runBatch(sqlArr, bdszBcnrb, user);
			result = checkBatch(tmpCount);
		} catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ������Ŀ�����ȡ�ü���������Ŀ����
	 * @param jb
	 * @param xmdm
	 * @return String[]
	 * */
	public String[] getZhcpxmMc(String jb , String[] xmdm){
		PjpyZctjszDAO zctjszDao = new PjpyZctjszDAO();
		List<HashMap<String, String>> list = zctjszDao.queryZhcpXmxx(jb,xmdm);
		String[] xmmc = {};
		for(HashMap<String, String> map : list){
			xmmc = StringUtils.joinStrArr(xmmc, new String[]{map.get("mc"), map.get("mc")+"����"});
		}
		return xmmc;
	}
	
	/**
	 * �����ۺ����ʲ�����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzf(PjpyZhszcpModel model){
		try {
			return runProcuder("{call PROC_PJPY_TYB_ZHSZCP (?,?,?,?,?,?,?)}",
					new String[] { model.getQueryequals_xydm(), model.getQueryequals_xn(), model.getQueryequals_xq(),
							model.getNd(),model.getYjdm(), model.getPjzq(), model.getZczq()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * �����ۺ����ʲ�����������(�ڶ���)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzfSec(PjpyZhszcpModel model){
		try {
			return runProcuder("{call PROC_PJPY_TYB_ZHSZCP_SEC (?,?,?,?,?,?,?)}",
					new String[] { model.getQueryequals_xydm(), model.getQueryequals_xn(), model.getQueryequals_xq(),
							model.getNd(),model.getYjdm(), model.getPjzq(), model.getZczq()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ��ѯ�ۺϲ����ܷ���Ϣ
	 * @param pjzq
	 * @param jb
	 * @param map
	 * @param model
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]>  queryZhszcpzfForExp(String pjzq, 
									           String jb,
									           HashMap<String, String> map,
									           String[] output,
									           PjpyZhszcpwhActionForm model, 
									           boolean isPage) throws Exception{
		PjpyZctjszDAO zctjszDao = new PjpyZctjszDAO();
		String xmdm = map.get(jb);//��Ŀ����
		String[] xmdmArray = new String[]{};
		if ("1".equalsIgnoreCase(jb)) {
			xmdmArray = new String[]{"999"};
		}
		if ("2".equalsIgnoreCase(jb)) {
			if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
				xmdm = map.get("2");
				xmdmArray = new String[]{xmdm};
			}
			
		} else if ("3".equalsIgnoreCase(jb)) {
			if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
				xmdm = map.get("3");
				xmdmArray = new String[]{xmdm};
			} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
				xmdm = map.get("2");
				xmdmArray = getArray("select dm from pjpy_zctjszb where fdm = ? and dmjb='3'", new String[]{xmdm}, "dm");
			}
			
		} else if ("4".equalsIgnoreCase(jb)) {
			if (StringUtils.isNotNull(model.getQueryequals_sidm())) {
				xmdm = map.get("4");	
				xmdmArray = new String[]{xmdm};
			} else if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
				xmdm = map.get("3");
				xmdmArray = getArray("select dm from pjpy_zctjszb where fdm = ? and dmjb='4'", new String[]{xmdm}, "dm");
			} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
				xmdm = map.get("2");
				xmdmArray = getArray("select dm from pjpy_zctjszb a where exists (select 1 from pjpy_zctjszb b where b.fdm = ? and b.dmjb='3' and a.fdm=b.dm)", new String[]{xmdm}, "dm");
			}
		}
		
		List<HashMap<String, String>> list = zctjszDao.queryZhcpXmxx(jb,xmdmArray);
		String viewName = "view_pjpy_zhszcpb";//��ͼ
		/*
		StringBuilder sql = new StringBuilder("select a.*,rownum r from (select a.* from (select pk,xh,xm,xymc,nj,zymc,bjmc,xn,xqmc,nd ");
		//��̬��ѯ�ֶ�
		int i=0;
		for(HashMap<String, String> xmMap : list){
			xmdm = xmMap.get("dm");
			output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i});
			
			sql.append(",max((select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) fs");
			sql.append(i);
			sql.append(",max((select pm from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) pm");
			sql.append(i++);			
		}
		//�ܷ���Ϣ
		xmdm = "999";//Ĭ�ϵ��ִܷ���
		sql.append(",max((select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) fs");
		sql.append(i);
		sql.append(",max((select pm from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) pm");
		sql.append(i);
		output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i});
		
		sql.append(" from ");
		sql.append(viewName);
		sql.append(" a ");
		*/
		StringBuilder sql = new StringBuilder("select a.*,rownum r from (select a.xh,a.xm,a.xymc,a.nj,a.zymc,a.bjmc,a.xn,a.xqmc,a.nd,a.pk");
		int j=0;
		for(HashMap<String, String> xmMap : list){
			sql.append(",max(fs")
			   .append(j)
			   .append(") fs")
			   .append(j);
			sql.append(",max(pm")
			   .append(j)
			   .append(") pm")
			   .append(j);
			j++;
		}
		sql.append(",max(fs")
		   .append(j)
		   .append(") fs")
		   .append(j);
		sql.append(",max(pm")
		   .append(j)
		   .append(") pm")
		   .append(j);
		
		sql.append("  from(select xh,xm,xydm,zydm,bjdm,xymc,nj,zymc,bjmc, xn, xqmc, nd, pk");
		
		
		//��̬��ѯ�ֶ�
		int i=0;
		for(HashMap<String, String> xmMap : list){
			xmdm = xmMap.get("dm");
			output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i});
			
			/*sql.append(",max((select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) fs");
			sql.append(i);
			sql.append(",max((select pm from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) pm");*/
			sql.append(" ,case when dm='")
			   .append(xmdm)
			   .append("' then to_number(nvl(max(fs), 0)) else 0 end fs")
			   .append(i);
			sql.append(" ,case when dm='")
			   .append(xmdm)
			   .append("' then to_number(nvl(max(pm), 0)) else 0 end pm")
			   .append(i);
			i++;
			//sql.append(i++);			
		}
		//�ܷ���Ϣ
		xmdm = "999";//Ĭ�ϵ��ִܷ���
		/*sql.append(",max((select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) fs");
		sql.append(i);
		sql.append(",max((select pm from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) pm");
		sql.append(i);*/
		sql.append(" ,case when dm='")
		   .append(xmdm)
		   .append("' then to_number(nvl(max(fs), 0)) else 0 end fs")
		   .append(i);
		sql.append(" ,case when dm='")
		   .append(xmdm)
		   .append("' then to_number(nvl(max(pm), 0)) else 0 end pm")
		   .append(i);
		
		output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i});
		
		sql.append(" from ");
		sql.append(viewName);
		
		//��ѯ����
		sql.append(" group by dm,xh, xn, xqmc, nd, pk,xm,xydm,zydm,bjdm,xymc,nj,zymc,bjmc) a ");
		sql.append(getWhereSql(model));
		sql.append(" group by a.xh,a.xm,a.xymc,a.nj,a.zymc,a.bjmc,a.xn,a.xqmc,a.nd,a.pk ");
		if ("1".equalsIgnoreCase(jb)) {
			sql.append(" order by xymc,nj");
			String zcpmfs = getOneRs("select value from pjpy_jcszb where key='pmfs' and gnmc='zhszcp'", new String[]{}, "value"); 
			if ("bjdm".equalsIgnoreCase(zcpmfs)) {
				sql.append(",bjmc");
			} else if ("zydm".equalsIgnoreCase(zcpmfs)) {
				sql.append(",zymc");
			}
			sql.append(",pm0");
		}
		sql.append(") a "); 
		
		if (isPage) {
			
			return CommonQueryDAO.commonQuery(sql.toString(), "", value != null ? value.toArray(new String[0]) : new String[]{}, output, model);
		}		
		return CommonQueryDAO.commonQueryNotFy(viewName, "" , value != null ? value.toArray(new String[0]) : new String[]{}, output, sql.toString(), model);
	}
	
	/**
	 * ����������ظ����ݣ�ɾȥ�ظ�����,���ص�һ�������в���ڶ��������ظ�������
	 * 
	 * @author lt
	 */
	public String[] replaceCfsj(String[] first, String[] second) {

		if (first != null && first.length > 0 && second != null
				&& second.length > 0) {
			List<String> fir = new ArrayList<String>(Arrays.asList(first));
			List<String> sec = Arrays.asList(second);

			fir.removeAll(sec);
			return !fir.isEmpty() ? fir.toArray(new String[0]) : new String[] {};
		} else {
			return first;
		}
	}
	
	public List<String> queryXhList(String bjdm) throws Exception {
		return getList("select a.xh from xszpb a,view_xsjbxx b where a.xh=b.xh and b.bjdm=?", new String[]{bjdm}, "xh");
	}
	
	public boolean selectTableExists(String table) {
		String cnt = getOneRs(
				"select count(*) cnt from user_tab_cols where table_name=?",
				new String[] { table != null ? table.toUpperCase() : "" },
				"cnt");
		return "0".equalsIgnoreCase(cnt) || StringUtils.isNull(cnt) ? false : true;
	}
	
	public static void main(String...strings) {
		System.out.println("abce".toUpperCase());
	}
}
