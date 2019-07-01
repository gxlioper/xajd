package xgxt.xtwh.xtwhOther;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.util.JdbcUtil;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;

import common.newp.StringUtil;

/**
 * ϵͳά�����������䣩service
 */
public class XtwhOtherService {
	
	private String[] whzds;
	
	private HashMap<String, ArrayList<HashMap<String, String>>> zdOpMap ;
	private List<HashMap<String, String>> Toptr;
	private List<HashMap<String, String>> oneRsAllAttribute;
	private ArrayList<String[]> queryRs;
	private HashMap<String, String[]> opTwoAssociate;

	public List<HashMap<String, String>> getToptr() {
		return Toptr;
	}

	public void setToptr(List<HashMap<String, String>> toptr) {
		Toptr = toptr;
	}

	// ���ݴ����ѧУ������һ���˵�
	public String[] getTopMenuForStu(String xxdm,String userName){
		
		String returnSql = "select gnmkdm,gnmkmc from view_yhzqx_dj where zdm=? and length(gnmkdm)=3 order by to_number(xssx)";
		String xstxtsxx = "0";
		//�ж�ѧУ
		if(xxdm.equalsIgnoreCase("10491")){
			
			//ȡ�ø����Ƿ���ȷ����Ϣ
			XsxxglService xsxxService = new XsxxglService();
			boolean update = xsxxService.checkXsxxsfqr(userName);
			if(!update){
				returnSql = "select gnmkdm,gnmkmc from view_yhzqx_dj where zdm=? and length(gnmkdm)=3 and gnmkdm in ('N01','N11') order by to_number(xssx)";
				xstxtsxx = "1";
			}
		}
		return new String[]{returnSql,xstxtsxx};
	}

	/**
	 * ��ȡ����ά����
	 * @param ssmk
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String, String>> getDmwhTable(String ssmk, String xxdm) {
		
		// ����List�ṹ�Ľ����
		DAO dao = DAO.getInstance();
		String sql = "select 1 sfcz from dmwhxxszb where ssmk = ? and xxdm = ?";
		String sfcz = dao.getOneRs(sql, new String []{ssmk,xxdm}, "sfcz");
		if(sfcz!=null&&!sfcz.equalsIgnoreCase("")){
			StringBuilder sqlBuilder = new StringBuilder("select whdmb, whdmbmc ");
			sqlBuilder.append("from dmwhglb where ssmk = ? and xxdm = ? ");
			return dao.getList(sqlBuilder.toString(), new String []{ssmk,xxdm},
					new String []{"whdmb","whdmbmc"});
		}else{
			StringBuilder sqlBuilder = new StringBuilder("select whdmb, whdmbmc ");
			sqlBuilder.append("from dmwhglb where ssmk = ? and xxdm = ? union ");
			sqlBuilder.append("select whdmb, whdmbmc from dmwhglb where ssmk = ? ");
			sqlBuilder.append("and xxdm = 'all'");
		
			return dao.getList(sqlBuilder.toString(), new String []{ssmk,xxdm,ssmk},
				new String []{"whdmb","whdmbmc"});
		}
	}
	
	/**
	 * ��ȡ����ά���ֶ�
	 * @param ssmk
	 * @param xxdm
	 * @param request 
	 * @param myFrom 
	 * @return
	 */
	public void getDmwhZd(String tableName, String xxdm,String ssmk, HttpServletRequest request, XtwhOtherForm myFrom) {
		
		DAO dao = DAO.getInstance();
        //�õ������б�
		StringBuilder zdglSql =  new StringBuilder("select zbzd,whdmb,zbtjzd,glbtjzd,jlbm,dmzd,mczd,zdlx,selmethod from dmwhxmglb where whdmb = ? " );
		zdglSql.append(" and xxdm = ? and (zdlx = 'select' or zdlx = 'selectAppoint') union all select zbzd, whdmb, zbtjzd, glbtjzd, jlbm, dmzd, mczd,zdlx, selmethod  from dmwhxmglb where whdmb = ? and xxdm = 'all' and (zdlx = 'select' or zdlx = 'selectAppoint')  "); 
		zdglSql.append(" and not exists (select 1 from dmwhxmglb where whdmb = ? and xxdm = ?)");
		String[] colList = new String []{"zbzd","whdmb","zbtjzd","glbtjzd","jlbm","dmzd","mczd","zdlx","selmethod"};
		
		ArrayList<String[]> jldzL=dao.rsToVator(zdglSql.toString(),new String[]{tableName,xxdm,tableName,tableName,xxdm}, colList);
		
		HashMap<String, ArrayList<HashMap<String, String>>>zdOpMap = new HashMap<String, ArrayList<HashMap<String, String>>>();
		//��������ά���ֶι����ֶ� 
		HashMap<String, String[]> opTwoAssociate= new HashMap<String, String[]>();
		for(int i = 0;i<jldzL.size();i++){
			String [] jldzlList = jldzL.get(i);
			String zbzd = jldzlList[0];
			String zdlx = jldzlList[7];
			if(zdlx.equalsIgnoreCase("select")){
				String[] zbtjzd = jldzlList[2].split(",");
				String[] glbtjzd = jldzlList[3].split(",");
				String jlbm = jldzlList[4];
				String dmzd = jldzlList[5];
				String mczd = jldzlList[6];
				
				StringBuilder sqlList = new StringBuilder("select distinct ");
				sqlList.append(dmzd);
				sqlList.append(" dm,");
				sqlList.append(mczd);
				sqlList.append(" mc from " );
				sqlList.append(jlbm);
				sqlList.append(" where 1 = 1 ");
				
		        //��ҳ��ȡֵ��ƴ�Ӽ��������������ֶ�����Ӧ��ֵ 
			    for(int j = 0;j<zbtjzd.length;j++){
			    	String reqValue = request.getParameter(zbtjzd[j]);
			    	if(!StringUtil.isNull(reqValue)){
			    		sqlList.append(" and ");
			    		sqlList.append(glbtjzd[j]);
			    		sqlList.append(" = ");
			    		sqlList.append(reqValue);
			    	}
			    }
			    
			    //��õĵ����������б�
			    ArrayList<HashMap<String, String>> opList = (ArrayList<HashMap<String, String>>)
			    dao.getList(sqlList.toString(),new String[]{},new String []{"dm","mc"});
			    zdOpMap.put(zbzd, opList);
		    	if(jldzlList[2]!=null&&!jldzlList[2].equalsIgnoreCase("")){
		    		opTwoAssociate.put(jldzlList[2], jldzlList);
		    	}
			}else if(zdlx.equalsIgnoreCase("selectAppoint")){
				String selmethod = jldzlList[8];
				try {
					ArrayList<HashMap<String, String>> opList = (ArrayList<HashMap<String, String>>)DmwhSelectMethod.class.getMethod(selmethod,
							(Class[]) null).invoke(null,(Object[]) null);
					 zdOpMap.put(zbzd, opList);
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
		}
		setOpTwoAssociate(opTwoAssociate);
		setZdOpMap(zdOpMap);
		
	}

	public void  getDmwhZdForCx(String tableName, String xxdm, String ssmk, XtwhOtherForm myFrom) {
		DAO dao = DAO.getInstance();
		
		String[] whzds = getWhzdm(tableName, xxdm, ssmk);
		
        //����ȡ�õ��ֶ���ȡ��ʵ�ʵ�����
		ArrayList<String[]> rs = null;
		if(whzds.length==1&&whzds[0].equalsIgnoreCase("all")){
			whzds=null;
		}
		if(whzds==null){
			whzds =  dao.getColumnName("select * from "+tableName);
			for(int i=0;i<whzds.length;i++){
				whzds[i]=whzds[i].toLowerCase();
			}
		}
		List<HashMap<String, String>> Toptr = dao.getColumnAttributeList(tableName,whzds);
		
		
		setToptr(Toptr);
		String pkCol = getPkCol(tableName, xxdm, ssmk);
		String [] outputString = new String[whzds.length+1];
		outputString[0] = pkCol;
		for(int i=0;i<whzds.length;i++){
			outputString[i+1] = whzds[i];
		}
		try {
			 rs  = CommonQueryDAO.commonQueryForPk(tableName, "", new String[]{}, outputString, "", myFrom);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		setQueryRs(rs);
	}

	/**
	 * �õ���ά�����ֶ���
	 * @param tableName
	 * @param xxdm
	 * @param ssmk
	 * @return
	 */
	public String[] getWhzdm(String tableName, String xxdm, String ssmk) {
		DAO dao = DAO.getInstance();
		StringBuilder sqlBuilder = new StringBuilder("select upper(whzd) whzd from dmwhglb a where ssmk = ? and  whdmb = ? and xxdm = ? ");
		sqlBuilder.append("union all ");
		sqlBuilder.append("select upper(whzd) whzd from dmwhglb b where ssmk = ? and whdmb = ?");
		sqlBuilder.append("and xxdm = 'all' and not exists (select 1 from dmwhglb where ssmk = ? and  whdmb = ? and xxdm = ? and b.whdmb=whdmb) ");
		String[] whzds =  dao.getOneRs(sqlBuilder.toString(), new String[]{ssmk,tableName,xxdm,ssmk,tableName,ssmk,tableName,xxdm}, "whzd").split(",");
		return whzds;
	}

	public HashMap<String, ArrayList<HashMap<String, String>>> getZdOpMap() {
		return zdOpMap;
	}

	public void setZdOpMap(
			HashMap<String, ArrayList<HashMap<String, String>>> zdOpMap) {
		this.zdOpMap = zdOpMap;
	}

	public String[] getWhzds() {
		return whzds;
	}

	public void setWhzds(String[] whzds) {
		this.whzds = whzds;
	}

	
	/**
	 * ����ά���ֶε�����ʾ
	 * @param tableName
	 * @param xxdm
	 * @param ssmk
	 * @param pkValue
	 */
	public void getDmwhZdForOneDis(String tableName, String xxdm, String ssmk, String pkValue) {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		
		String[] whzds = getWhzdm(tableName,xxdm,ssmk);
		if(whzds.length==1&&whzds[0].equalsIgnoreCase("all")){
			whzds=null;
		}
		List<HashMap<String, String>> Toptr = dao.getColumnAttributeList(tableName,whzds);
		
		
		//Ϊ��ȡ����ָ��������������������
		String [] zdbt = new String [Toptr.size()];
		for(int i = 0;i<Toptr.size();i++){
			zdbt[i]= Toptr.get(i).get("en");
		}
		String pkCol = getPkCol(tableName, xxdm, ssmk);
		HashMap<String, String> rs = CommonQueryDAO.commonQueryOne(tableName,
				zdbt, pkCol, pkValue);
		List<HashMap<String, String>> oneRsAllAttribute = new ArrayList<HashMap<String, String>>();
		
		for(int i = 0;i<Toptr.size();i++){
			HashMap<String, String> TopMap= Toptr.get(i);
			String value = rs.get(TopMap.get("en"));
			if(!("KFSC_XG").equalsIgnoreCase(Toptr.get(i).get("en"))){
			TopMap.put("value", value);
			oneRsAllAttribute.add(TopMap);
			}
		}
		
		setOneRsAllAttribute(oneRsAllAttribute);
		
	}
	
	/**
	 * ����ά������
	 * @param tableName
	 * @param xxdm
	 * @param ssmk
	 * @param pkValue
	 * @param request 
	 * @throws Exception 
	 */
	public boolean dmwhZdForOneSave(String tableName,String xxdm,String ssmk,String pkValue, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		String[] whzds = getWhzdm(tableName,xxdm,ssmk);
		if(whzds.length==1&&whzds[0].equalsIgnoreCase("all")){
			whzds =  dao.getRs("select COLUMN_NAME from user_tab_cols where table_name=upper('"+tableName+"') and COLUMN_NAME !='KFSC_XG' ",new String[]{},"COLUMN_NAME");
		}
		String pkKey =  getPkCol(tableName,xxdm,ssmk);
		String[] whzdzs = new String [whzds.length];
		for(int i = 0 ;i<whzds.length;i++){
			whzdzs[i] = request.getParameter(whzds[i]).trim();
		}
		if(pkValue!=null&&!pkValue.equalsIgnoreCase("")){
			return StandardOperation.update(tableName, whzds, whzdzs, pkKey, pkValue,request);
		}else{
			return StandardOperation.insert(tableName, whzds, whzdzs,request);
		}
		
	}

	private String getPkCol(String tableName, String xxdm, String ssmk) {
		//��ȡ�ñ������
		DAO dao = DAO.getInstance();
		String sql = "select distinct pkkey from dmwhglb where (xxdm = ? or xxdm = 'all') and whdmb = ? and ssmk = ?";
		String pkCol = dao.getOneRs(sql, new String []{xxdm,tableName,ssmk}, "pkkey");
		return pkCol;
	}

	public List<HashMap<String, String>> getOneRsAllAttribute() {
		return oneRsAllAttribute;
	}

	public void setOneRsAllAttribute(List<HashMap<String, String>> oneRsAllAttribute) {
		this.oneRsAllAttribute = oneRsAllAttribute;
	}

	public void setQueryRs(ArrayList<String[]> queryRs) {
		this.queryRs = queryRs;
	}

	public ArrayList<String[]> getQueryRs() {
		return queryRs;
	}

	public HashMap<String, String[]> getOpTwoAssociate() {
		return opTwoAssociate;
	}

	public void setOpTwoAssociate(HashMap<String, String[]> opTwoAssociate) {
		this.opTwoAssociate = opTwoAssociate;
	}

	public boolean dmwhDel(String tableName, String pkValue, String xxdm, String ssmk, HttpServletRequest request) throws Exception {
		String pkCol = getPkCol(tableName, xxdm, ssmk);
		return StandardOperation.delete(tableName, pkCol, pkValue, request);
	}

	/**
	 * �޸Ŀ���״̬
	 * @param model
	 * @return boolean
	 * @author lr
	 * @throws Exception
	 */
	public boolean updateKgzt(XtwhOtherForm model) throws Exception {
		XtwhOtherDAO dao = new XtwhOtherDAO();		
		return dao.updateKgzt(model);
	}
	
	/**
	 * ��ȡ��Ŀ����״̬
	 * @param gndm
	 * @return boolean
	 * @author lr
	 * */
	public boolean gnkgFlag(String gndm){
		XtwhOtherDAO dao = new XtwhOtherDAO();		
		String kgzt = dao.getOneValue("xgxtgy_gnkgb", "kgzt", "gndm", gndm);
		return "��".equalsIgnoreCase(kgzt) ? true : false;
	}
	
	/**
	 * ��ȡ�����б��ֵ
	 * @param tableName
	 * @param dm
	 * @param mc
	 * @return List
	 * @author lr
	 * */
	public List<HashMap<String, String>> getList(String tableName, String dm, String mc,String pk, String pkValue){
		DAO dao = DAO.getInstance();
		return dao.getWhList(tableName, dm, mc, "", pk, pkValue);
	}
	
	/**
	 * ��ȡĬ�ϵ��б�
	 * @param lx
	 * @return List<HashMap<String, String>>
	 * @author lr
	 * */
	public List<HashMap<String, String>> getDefaultList(String lx){
		XtwhOtherDAO dao = new XtwhOtherDAO();		
		return dao.getDefaultList(lx);
	}
	
	public List<String[]> selectExportData(String viewName,String[] inputValue, String[] outputColumn, String sql){
		XtwhOtherDAO dao = new XtwhOtherDAO();
		if (inputValue != null && StringUtil.isNull(inputValue[0])){
			inputValue = new String[]{};
		}
		return dao.rsToVator(sql, inputValue, outputColumn);
	}

	public String getXxdm(String xxmc) {
		DAO dao = DAO.getInstance();
		String sql = "select xxdm from dmk_xx where xxmc=?";
		String xxdm = dao.getOneRs(sql, new String[] { xxmc }, "xxdm");
		return xxdm;
	}
	
	/**
	 * �°����ά���Զ��嵼��
	 * @param tablename
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmwhExport(String tablename) {
		// ����List�ṹ�Ľ����
		DAO dao = DAO.getInstance();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("select * from ");
		sqlBuilder.append(tablename);
		
		return dao.getListNotOut(sqlBuilder.toString(), new String []{});
		
	}
	
	/**
	 * 
	 * @����:ȡ��ѧУ����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����04:21:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xxmc
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXxmc(String xxmc) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct(xxmc) from dmk_xx ");
		if (xxmc != null && !"".equalsIgnoreCase(xxmc)) {
			sql.append("where xxmc like '%" + xxmc + "%'");
		}
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xxmc" });
		StringBuilder sb = new StringBuilder();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				if (i != list.size() - 1) {
					sb.append(map.get("xxmc")).append(",");
				} else {
					sb.append(map.get("xxmc"));
				}

			}
			return sb.toString();
		}else{
			return null;
		}
	}
//			
//		
//		if (xxmc != null && !"".equalsIgnoreCase(xxmc) && !xxmc.startsWith("'")
//				&& !xxmc.endsWith("'")) {
//			DAO dao = DAO.getInstance();
//			String sql = "select distinct(xxmc) from dmk_xx where xxmc like '%"
//					+ xxmc + "%' and rownum <= 20 ";
//			List<HashMap<String, String>> list = dao.getList(sql,
//					new String[] {}, new String[] { "xxmc" });
//			StringBuilder sb = new StringBuilder();
//			if (null != list && list.size() > 0) {
//				for (int i = 0; i < list.size(); i++) {
//					HashMap<String, String> map = list.get(i);
//					if (i != list.size() - 1) {
//						sb.append(map.get("xxmc")).append(",");
//					} else {
//						sb.append(map.get("xxmc"));
//					}
//
//				}
//				return sb.toString();
//			} else {
//				return null;
//			}
//			
//		} else {
//			return null;
//		}
	
	/**
	 * @����:����ģ����ѯ����
	 * @���ߣ�qilm
	 * @���ڣ�2013-8-15 ����11:18:47
	 * @param dataTable ���� 
	 *        dataField �ֶ���
	 *        dataFieldKey �ֶ�Key
	 *        param �ֶ�����Ϊ����
	 * @throws
	 */
	public List<HashMap<String, String>> getAutocomplete(String dataTable,
			String dataField, String dataFieldKey, String param, String sqlTj) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		String[] params = new String[] {};
		// ���������ֶ���Ϊ���򷵻�NULL
		if (dataTable != null && !"".equalsIgnoreCase(dataTable)
				&& dataField != null && !"".equals(dataField)) {
			String[] dataRows = new String[1];
			       
			// ��ָ���ı�ȡ��ָ�����ֶμ�
			sql.append(" SELECT DISTINCT(" + dataField + ") " + dataField);
			dataRows[0] = dataField;
			if (dataFieldKey != null && !"".equalsIgnoreCase(dataFieldKey)) {
				sql.append(" ," + dataFieldKey);
				dataRows = new String[2];
				dataRows[0] = dataField;
				dataRows[1] = dataFieldKey;
			}
			sql.append(" FROM " + dataTable);
			sql.append(" WHERE " + dataField + " IS NOT NULL ");
//			if (dataFieldKey != null && !"".equalsIgnoreCase(dataFieldKey)) {
//				sql.append(" AND " + dataFieldKey + " IS NOT NULL ");
//			}

			if (param != null && !"".equalsIgnoreCase(param)) {
				sql.append(" AND " + dataField + " = ?" );
				params = new String[] { param };
			}
			// ���Ի�sql����
			if (sqlTj != null && !"".equalsIgnoreCase(sqlTj)) {
				sql.append(sqlTj);
			}
			List<HashMap<String, String>> list = dao.getList(sql.toString(),
					params, dataRows);
			return list;
		}
		return null;
	}
	/**
	 * @throws SQLException 
	 * 
	 * @����:��ȡ��վ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-19 ����04:06:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<String> getHczd() throws SQLException {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
        sql.append(" select zdmc||'|'||zdpy||'|'||zdjp||'|'||sfrmcs||'|'||F_PINYIN(substr(zdmc,0,1)) city from xg_rcsw_hczdb");
		return dao.getList(sql.toString(),new String[]{}, "city");
	
	}
	
	/**
	 * �ж�inputvalueֵ�Ƿ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-17 ����04:10:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isInputValueExist(String inputValue){
		return new XtwhOtherDAO().isInputValueExist(inputValue);
	}
}
