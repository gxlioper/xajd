/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-31 ����10:31:39 
 */
package com.zfsoft.xgxt.base.extend;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Dao��չ
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-31 ����10:31:39
 * @�汾�� V1.
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public abstract class SuperDAOImplExtend<T> extends SuperDAOImpl<T> {
	protected Class<T> cla;

	@Override
	protected void setClass(Class<T> c) {
		cla = c;
		super.setClass(c);
	}

	protected int deleteForSql(String sql, String[] param) {
		try {
			return dao.runDelete(sql, param);
		} catch (Exception e) {
			throw new RuntimeException("ɾ��ʧ��!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @����: ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-8 ����04:34:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param beans
	 * @return
	 * @throws Exception int[] ��������
	 */
	protected int[] batchSave(List<T> beans) throws Exception {
		Field[] fields = cla.getClass().getDeclaredFields();
		List<String> columns = dao.getColumnsName(this.getTableName());
		List<String> keys = new ArrayList<String>();
		List<String[]> params = new ArrayList<String[]>();
		List<String> values = new ArrayList<String>();
		for (T t : beans) {
			for (Field f : fields) {
				String fieldName = f.getName();
				if (columns.contains(fieldName.toUpperCase())) {
					String methodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					String value = null;
					try {
						value = (String) cla.getClass().getMethod(methodName,
								new Class[] {}).invoke(t);
					} catch (Exception e) {
						logger.error("����get��������,methodName:" + methodName);
						throw e;
					}

					if (!StringUtil.isNull(value)) {
						keys.add(fieldName);
						values.add(value);
					}
				}
			}
			params.add(values.toArray(new String[] {}));
		}
		String sql = getInsertSql(keys, this.getTableName());
		logger.debug(sql);
		return dao.runBatch(sql, params);
	}
	protected String getInsertSql(List<String> keys, String tableName) {

		if (keys.isEmpty()) {
			logger.error("����insert sql ����");
		}

		StringBuilder insertSql = new StringBuilder();

		insertSql.append("insert into ");
		insertSql.append(tableName);
		insertSql.append(" ( ");

		insertSql.append(keys.toString().replace("[", "").replace("]", ""));
		insertSql.append(") values (");

		for (int i = 0, n = keys.size(); i < n; i++) {
			insertSql.append("?");
			if (i != n - 1) {
				insertSql.append(",");
			}
		}
		insertSql.append(") ");
		return insertSql.toString();
	}

	/**
	 * 
	 * @����: ��Ȩ��ɾ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-31 ����11:17:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return int ��������
	 */
	protected int deleteAll(User user) {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" delete ");
			sb.append(getTableName());
			sb.append(" where 1=1 ");
			sb.append(searchTjByUser);
			return dao.runDelete(sb.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("ɾ��ʧ��!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @����: ��ȡ��ǰ�û�Ȩ�޵İ༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����10:31:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBjListForZgh(User user) {
		StringBuffer sb = new StringBuffer();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		sb.append("select distinct a.bjdm,a.bjmc from view_xsxxb a where 1=1 ");
		sb.append(searchTjByUser);
		sb.append(" order by a.bjmc ");
		return dao.getListNotOut(sb.toString(), new String[] {  });
	}

	/**
	 * 
	 * @����: ��ȡ�û�Ȩ�޷�ΧѧԺlist
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-4 ����12:00:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXyListForZgh(User user) {
		StringBuffer sb = new StringBuffer();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		sb.append("select distinct a.xydm,a.xymc from view_xsxxb a where 1=1 ");
		sb.append(searchTjByUser);
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 
	 * @����: ��ȡѧԺ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-4 ����12:00:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return String ��������
	 */
	public String getXymc(String xydm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select bmmc xymc from zxbz_xxbmdm where bmdm=?");
		return dao.getOneRs(sb.toString(), new String[] { xydm }, "xymc");
	}

	/**
	 * 
	 * @����:����ѧ�ڴ����ȡѧ������ �ܶ�ط�ʹ�ã�ÿ��dao��д���ظ���̫��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-18 ����02:48:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return String ��������
	 * @throws
	 */
	public String getXqmcForXqdm(String xqdm) {
		String sql = "select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[] { xqdm }, "xqmc");
	}
}
