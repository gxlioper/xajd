/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-6 ����09:58:29 
 */
package com.zfsoft.xgxt.base.extend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��չsuperservice
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-6 ����09:58:29
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SuperServiceImplExtend<T, E extends SuperDAOImplExtend<T>> extends
		SuperServiceImpl<T, E> {
	/**
	 * 
	 * @����: ����ִ�У�����ϸ��ʾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����10:07:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 *            Ҫִ�еĶ�Ӧ����
	 * @param delete
	 *            ִ��ҵ��ӿ�
	 * @return
	 * @throws Exception
	 *             String[] ��������
	 */
	public Map<String, Object> batchExecute(String[] ids, IDelete delete) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> delId = new ArrayList<String>();// ��ɾ����id����
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			boolean isHaveError = false;
			if (null == ids || ids.length <= 0) {
				return null;
			}
			for (String str : ids) {
				if (delete.isCanDelete(str)) {
					delId.add(str);// ��¼ɾ��id
				} else {
					isHaveError = true;
					list.add(delete.showMessage(str));
				}
			}
			int i = delId.size() > 0 ? delete.execute(delId
					.toArray(new String[] {})) : 0;
			map.put(IDelete._CGTS, String.valueOf(i));
			if (isHaveError) {
				map.put(IDelete._ERROE_OBJ, list);
			} else {
				map.put(IDelete._ERROE_OBJ, IDelete._UNHAVE_ERROE);
			}
			return map;
		} catch (Exception e) {
			throw new RuntimeException("����ִ��ʧ��!" + e.getMessage());
		}
	}
	public Map<String, Object> delete(String[] ids) throws RuntimeException {
		return batchExecute(ids, new IDelete() {
			public Map<String, String> showMessage(String pk) throws Exception {
				return null;
			}

			public boolean isCanDelete(String pk) throws Exception {
				return true;
			}

			public int execute(String[] ids) throws Exception {
				return runDelete(ids);
			}
		});
	}

	/**
	 * 
	 * @����: ɾ��[֧�ֲ�ѡȫ��ɾ��]
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-31 ����11:17:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 *            ids�ַ����� ��,���ŷָ������Ϊ����ȫ��ɾ��
	 * @param user
	 *            ��ǰ�����û�
	 * @return
	 * @throws Exception int ��������
	 */
	public int runDelete(String value, User user) throws Exception {
		if (StringUtils.isNull(value)) {
			return dao.deleteAll(user);
		}
		return super.runDelete(value.split(","));
	}

	/**
	 * 
	 * @����: ��ȡ����������ָ���е�ֵ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-3 ����03:51:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param colName
	 *            ����
	 * @param list
	 *            ����Դ
	 * @return String ���ŷָ���ֵ
	 */
	public String getAllCols(String colName, List<HashMap<String, String>> list) {
		StringBuffer pks = new StringBuffer();
		int i = 0;
		for (HashMap<String, String> hm : list) {
			pks.append(hm.get(colName));
			if (i++ != list.size()) {
				pks.append(",");
			}
		}
		return pks.toString();
	}

	/**
	 * 
	 * @����: ��ȡ�༶��ӦȨ��list
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����10:33:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getBjList(User user) {
		
		return dao.getBjListForZgh(user);
	}
	/**
	 * 
	 * @����: ��ȡѧԺlist
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����01:55:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getXyList(User user) {
		return dao.getXyListForZgh(user);
	}
	/**
	 * 
	 * @����: ��ȡѧԺ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-5-4 ����12:13:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return
	 * String ��������
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	/**
	 * 
	 * @����: ��ȡѧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-30 ����10:35:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String ��������
	 */
	public String getXqmc(String xqdm) {
		return dao.getXqmcForXqdm(xqdm);
	}
}
