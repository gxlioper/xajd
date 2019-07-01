package xgxt.comm;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.DMLLogger;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class CommDAO extends CommonQueryDAO {

	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		
		DAO dao = DAO.getInstance();
		
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql) throws Exception {

		DAO dao = DAO.getInstance();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] <0) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}

	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String sql, List<String[]> params,
			String tableName, User user) throws Exception {

		DAO dao = DAO.getInstance();
		boolean flag = true;
		int[] res = dao.runBatch(sql, params);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}

		if (flag) {
			// д������־
			DMLLogger dmlLogger = new DMLLogger();
			dmlLogger.setOldData(tableName, "runBatch :");
			dmlLogger.insertLog(sql, null, user);
		}

		return flag;
	}
	
	/**
	 * �����ύ
	 * 
	 * @throws Exception
	 * @author qlj
	 */
	public boolean saveArrDate(String sql, List<String[]> params,
			String tableName) throws Exception {

		DAO dao = DAO.getInstance();
		boolean flag = true;
		int[] res = dao.runBatch(sql, params);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}

		return flag;
	}
	
	/**
	 * ���ָ������ֶ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getTableZd(String tableName) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct zd from (select lower(COLUMN_NAME) zd ");
		sql.append(",length(lower(COLUMN_NAME)) cd ");
		sql.append("from user_tab_cols where table_name ");
		sql.append("in (upper('" + tableName + "'), upper('" + tableName
				+ "'))) order by cd");

		String[] zd = dao.getRs(sql.toString(), new String[] {}, "zd");

		return zd;
	}

	/**
	 * ���ָ������ֶ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableZdList(String tableName)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.column_name dm, b.comments mc,a.nullable,a.data_length len ");
		sql.append("from (select a.table_name,a.nullable,a.column_name,a.data_length ");
		sql.append("from user_tab_cols a where a.table_name in ");
		sql.append("(upper(?), upper(?))) a ");
		sql.append("left join USER_COL_COMMENTS b on a.table_name = b.table_name ");
		sql.append("and a.column_name = b.column_name order by a.nullable");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { tableName, tableName }, new String[] { "dm",
						"mc", "len", "nullable" });

		return list;
	}

	/**
	 * ���������Ϣ��������
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean submitData(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * ��������������DB
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveData(SaveForm form, Object model, User user)
			throws Exception {

		DAO dao = DAO.getInstance();
		return dao.saveData(form, model, user);
	}

	/**
	 * �����޸�DB�е�����
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean updateData(SaveForm form, Object model, User user)
			throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model, user);
	}

	/**
	 * ����ɾ��DB�е�����
	 * 
	 * @return boolean
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delDate(SaveForm form, Object model, User user)
			throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model, user);
	}

	/**
	 * ���ݲ�ѯ��HashMap��
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getRsInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * ���ݲ�ѯ��ArrayList<String[]>��
	 * 
	 * @param tableName(����)
	 * @param query(����)
	 * @param inPutList(����ֵ)
	 * @param colList(���ֵ)
	 * @param sql(���Ի�sql)
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getRsArrList(String tableName, String query,
			String[] inPutList, String[] colList, String sql, Object model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return commonQuery(tableName, query, inPutList, colList, sql, model);
	}

	/**
	 * ���ݲ�ѯ��List<HashMap>��
	 * 
	 * @param tableName(����)
	 * @param query(����)
	 * @param inPutList(����ֵ)
	 * @param colList(���ֵ)
	 * @param sql(���Ի�sql)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getRsList(String tableName,
			String query, String[] inPutList, String[] colList, String sql) {
		return commonQueryforList(tableName, query, inPutList, colList, sql);
	}

	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {	
		DAO dao = new DAO();
		return dao.getNowTime(lx);
	}
	
	/**
	 * ��ȡ�������ڵļ������
	 * 
	 * @param begDate(��ʼʱ��)
	 * @param endDate(����ʱ��)
	 * 
	 * @author luojw
	 */
	public String getBetweenDate(String endDate, String begDate) {

		DAO dao = DAO.getInstance();

		String sql = "SELECT to_Date(?,'yyyyMMdd')-to_Date(?,'yyyyMMdd') days FROM DUAL";
		String days = dao.getOneRs(sql, new String[] { endDate, begDate },
				"days");

		return days;
	}

	/**
	 * ���ָ����ı�ṹ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableInfo(String tableName) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.COLUMN_NAME zdm,");
		sql.append(" B.COMMENTS zwm,");
		sql.append(" DATA_TYPE lx,");
		sql.append(" DATA_LENGTH cd,");
		sql.append(" A.NULLABLE sfk");
		sql.append(" FROM USER_TAB_COLUMNS A, USER_COL_COMMENTS B");
		sql.append(" WHERE A.TABLE_NAME = B.TABLE_NAME");
		sql.append(" AND A.COLUMN_NAME = B.COLUMN_NAME");
		sql.append(" AND A.TABLE_NAME = UPPER('" + tableName + "')");
		sql.append(" ORDER BY COLUMN_ID");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {},
				new String[] { "zdm", "zwm", "lx", "cd", "sfk" });

		return list;
	}
	
	/**
	 * ��ѯָ����ָ���ֶ�����
	 */
	public List<HashMap<String, String>> getTableListInfo(String tableName,
			String[] colList, String pk, String pkValue, String query) {

		DAO dao = DAO.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where 1 = 1 ");
		if (!Base.isNull(pk) && !Base.isNull(pkValue)) {
			sql.append(" and ");
			sql.append(pk);
			sql.append(" = '");
			sql.append(pkValue);
			sql.append("'");
		}
		sql.append(query);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}
	
	/**
	 * ��ѯָ����ָ���ֶ�����
	 */
	public List<HashMap<String, String>> getTableListInfo(String tableName,
			String[] colList) {

		return getTableListInfo(tableName, colList, null, null, "");
	}

	//===============��ʼ�����ݲ��� begin===================
	
	/**
	 * ��ʼ����ѯ����
	 * 
	 * @date 2013.01.25
	 * 
	 * @author ΰ�����
	 */
	public void initSearch(List<String[]> params) throws Exception {

		String tableName = "xg_search_szb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_search_szb ");
		sql.append("(");
		sql.append("path,tj,mc,lx,zd,ssmk,xssx");
		sql.append(") values");
		sql.append("(?,?,?,?,?,?,?)");

		saveArrDate(sql.toString(), params, tableName);

	}
	
	/**
	 * �������ݱ�
	 * 
	 * @date 2013.01.31
	 * 
	 * @author ΰ�����
	 */
	public void createTable(String tableName, String[] content)
			throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("create table ");
		sql.append(tableName);
		sql.append("(");
		for (int i = 0; i < content.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append(content[i]);
		}
		sql.append(")");

		dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * ��ʼ������ͼ��ע
	 * 
	 * @date 2013.01.31
	 * 
	 * @author ΰ�����
	 */
	public void initComment(String tableName, String[] comment_en,
			String[] comment_cn) throws Exception {

		DAO dao = DAO.getInstance();

		for (int i = 0; i < comment_en.length; i++) {
			StringBuilder sql = new StringBuilder();
			sql = new StringBuilder();
			sql.append("comment on column ");
			sql.append(tableName);
			sql.append(".");
			sql.append(comment_en[i]);
			sql.append(" is '");
			sql.append(comment_cn[i]);
			sql.append("'");
			dao.creatView(sql.toString(), new String[] {});
		}

	}
	
	//===============��ʼ�����ݲ��� end===================
	
	
	/**
	 * ���ѧ������������Ϣ
	 */
	public List<HashMap<String, String>> getLnXszzList(String[] xmb,
			String[] xmdm, String[] xmmc, String[] sqzq, String[] shjb,
			String[] xmlb,String xh) {

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = null;

		if (xmb != null && xmb.length > 0) {

			StringBuilder sql = new StringBuilder();

			for (int i = 0; i < xmb.length; i++) {
				if (i != 0) {
					sql.append(" union all ");
				}
				sql.append(" select '").append(xmmc[i]).append("' xmmc,a.xmdm,");
				sql.append(" '").append(sqzq[i]).append("' sqzq,");
				sql.append("'").append(xmlb[i]).append("' xmlb,");
				sql.append("(select b.xqdm from xqdzb b where a.xq = b.xqdm) xqmc,");
				sql.append(" a.xn,a.xq,a.nd,a.sqsj,a.xmzzje from ");
				sql.append(xmb[i]);
				sql.append(" a where a.xh = '").append(xh).append("' ");
				sql.append(" and a.xmdm = '").append(xmdm[i]).append("' ");
				if ("һ�����".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt1 = 'ͨ��' ");
				} else if ("�������".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt2 = 'ͨ��' ");
				} else if ("�������".equalsIgnoreCase(shjb[i])) {
					sql.append(" and a.shzt3 = 'ͨ��' ");
				}
			}
			
			String[] outputValue = new String[] { "xmdm", "xmmc", "sqzq", "xn",
					"xq", "xqmc", "nd", "sqsj", "xmzzje","xmlb" };
			list = dao.getList(sql.toString(), new String[] {}, outputValue);
		}

		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				// ��Ŀ����
				String dm = map.get("xmdm");
				// ��������
				String zq = map.get("sqzq");
				// ѧ��
				String xn = map.get("xn");
				// ѧ��
				String xq = map.get("xqmc");
				// ���
				String nd = map.get("nd");
				// ��Ŀ���
				String xmzzje = map.get("xmzzje");
				
				if (!XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(dm)
						&& !XszzXmdm.XSZZ_QTXX.equalsIgnoreCase(dm)) {
					
					String zzsj = "����������";
				
					if ("ѧ��".equalsIgnoreCase(zq)) {
						zzsj = xn + "ѧ��";
					} else if ("ѧ��".equalsIgnoreCase(zq)) {
						zzsj = xn + "ѧ��" + xq + "����";
					} else if ("���".equalsIgnoreCase(zq)) {
						zzsj = nd + "���";
					}
					
					if (Base.isNull(xmzzje)) {
						xmzzje = "���漰���";
					}
					map.put("xmzzje", xmzzje);
					map.put("zzsj", zzsj);
					
					resultList.add(map);
				}
			}
		}
		
		return resultList;
	}
	
	/**
	 * ������Դ�ش����ѯ��Դ������<br>
	 * ����������ѧ��������Ϣ��洢��ʡ/��/�ص�ת��
	 * @param syd ��Դ�ش���  ʡ/��/��
	 * @param splitFlg �������Դ�ش��밴ʲô�ָ�ģ� '/'?
	 * @param returnSplitFlg ���ص���Դ��������Ҫ�ָ�ô�����Ϊ�պ�����
	 * @return String
	 */
	public  String getSydmc(String syd,String splitFlg,String returnSplitFlg){
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select max(ltrim(sys_connect_by_path((t.qxmc) || '', '");
		sql.append(returnSplitFlg);
		sql.append("'), '");
		sql.append(returnSplitFlg);
		sql.append("')) qxmc");
		sql.append(" from (select a.qxmc,rownum  pno,rownum - 1 sno ");
		sql.append(" from dmk_qx a where  '");
		sql.append(splitFlg);
		sql.append(syd);
		sql.append(splitFlg);
		sql.append("'  like '%");
		sql.append(splitFlg);
		sql.append("'||qxdm||'");
		sql.append(splitFlg);
		sql.append("'||'%') t");
		sql.append(" start with pno = 1");
		sql.append(" connect by prior pno = sno");
		
		return dao.getOneRs(sql.toString(), new String[]{}, "qxmc");
	}
	
	/**
	 * ��õ�½�û��Ĺ�Ԣ����ԱȨ��
	 * 
	 * @return String
	 */
	public String getGyglyQx(String userName) {
		DAO dao = DAO.getInstance();

		String sql = "select count(1) num from xg_gygl_new_gyfdyb a where a.yhm =? and exists (select 1 from xg_gygl_new_ldxxb b where b.lddm = a.lddm)";
		String num = dao.getOneRs(sql, new String[] { userName }, "num");

		String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
				: "no";

		return gyglyQx;
	}
	
	/**
	 * �ж��Ƿ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean isExists(String table, String pk, String pkValue)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ");
		sql.append(table);
		sql.append(" where 1 = 1 ");
		sql.append(" and " + pk + " = ? ");

		DAO dao = DAO.getInstance();
		String num = dao.getOneRs(sql.toString(), new String[] { pkValue },
				"num");

		boolean flag = false;

		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}

	/**
	 * ��ò�����Ϣ��������
	 * 
	 * @author ΰ�����
	 */
	public String getBmxxQuery(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm,String tableName) {

		tableName = Base.isNull(tableName) ? "b" : tableName;
		
		StringBuilder sql = new StringBuilder();

		// �꼶����
		if (nj != null && nj.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" ");
				sql.append(tableName);
				sql.append(".nj = '" + nj[i] + "' ");
			}
			sql.append(") ");
		}

		// ѧԺ����
		if (xydm != null && xydm.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" ");
				sql.append(tableName);
				sql.append(".xydm = '" + xydm[i] + "' ");
			}
			sql.append(") ");
		}

		// רҵ����
		if (zydm != null && zydm.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < zydm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" ");
				sql.append(tableName);
				sql.append(".zydm = '" + zydm[i] + "' ");
			}
			sql.append(") ");
		}

		// �༶����
		if (bjdm != null && bjdm.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < bjdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" ");
				sql.append(tableName);
				sql.append(".bjdm = '" + bjdm[i] + "' ");
			}
			sql.append(") ");
		}

		return sql.toString();
	}

	// ===================ţB begin=====================================
	/**
	 * ��ţB����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean insertTable(String tableName, String[] key, String[] value,
			User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(tableName);
		if (key != null && key.length > 0) {
			sql.append("(");
			for (int i = 0; i < key.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append(key[i]);
			}
			sql.append(")");
		}
		sql.append("values");

		if (value != null && value.length > 0) {
			sql.append("(");
			for (int i = 0; i < value.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
			}
			sql.append(")");
		}

		List<String[]> params = new ArrayList<String[]>();
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * ��ţB���޸�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean updateTable(String tableName, String[] key, String[] value,
			String pk, String pkValue, User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(tableName);
		sql.append(" set ");
		if (key != null && key.length > 0) {
			for (int i = 0; i < key.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append(key[i]);
				sql.append(" = ? ");

			}
		}
		sql.append(" where ");
		sql.append(pk);
		sql.append(" = ");
		sql.append("'");
		sql.append(pkValue);
		sql.append("'");

		List<String[]> params = new ArrayList<String[]>();
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * ��ţB��ɾ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public boolean deleteTable(String tableName, String pk, String[] pkValue,
			User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(tableName);
		sql.append(" where 1=? ");
		sql.append(" and ( ");
		if (pkValue != null && pkValue.length > 0) {
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(pk);
				sql.append(" = '");
				sql.append(pkValue[i]);
				sql.append("'");
			}
		}
		sql.append(" ) ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * ��ţB�������ϸ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public HashMap<String, String> getDetail(String tableName, String[] key,
			String pkValue) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		if (key != null && key.length > 0) {
			for (int i = 0; i < key.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append(" a.");
				sql.append(key[i]);
			}
		}
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" a where 1=1 ");
		sql.append(" and a.pk=?");

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { pkValue }, key);

		return map;
	}
	// ===================ţB end=====================================
	
	public List<String> arrayToList(String[] array){
		List list = new ArrayList<String>();
		if(array != null  && array.length >0){
			for(int i=0;i<array.length;i++){
				list.add(array[i]);
			}			
		}
		return list;
	}
}
