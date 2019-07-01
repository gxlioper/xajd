/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-17 ����09:54:49 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ģ��
 * @�๦������: ����������
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-17 ����09:54:49
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public abstract class BaseCreate implements IOrderBy {
	protected static final String _FB_SPLIT = ":";
	protected static final String _XHGZ_XY = "1";//����ѧԺȡ��ˮ��
	protected static final String _XHGZ_ZY = "2";//����רҵȡ��ˮ��
	protected static final String _XHGZ_BJ = "3";//���հ༶ȡ��ˮ��
	private StringBuffer gzSql = new StringBuffer();
	// ������sql�������
	private List<String> paramList = new ArrayList<String>();
	// ���������������
	private Map<String, String> mapParam = new HashMap<String, String>();

	/**
	 * ���ݡ�������򡯻�ȡ����������
	 */
	public List<HashMap<String, String>> getGroupByData(
			List<HashMap<String, String>> tjPzxx) {
		gzSql.append(getBaseSql());
		String xxz;
		String tjszzd;
		String xxzs[];
		int i = 0;
		for (HashMap<String, String> hm : tjPzxx) {
			xxz = hm.get("xxz");
			tjszzd = hm.get("tjszzd");
			if (i == 0) {
				gzSql.append(" order by bjdm asc,");
			}
			gzSql.append(tjszzd);
			if (StringUtils.isNotNull(xxz)) {
				xxzs = xxz.split(_FB_SPLIT);
				gzSql.append(" ");
				gzSql.append(xxzs[0]);
			}
			if (i + 1 != tjPzxx.size()) {
				gzSql.append(",");
			}
			i++;
		}
		return DAO.getInstance().getListNotOut(gzSql.toString(),
				getParam().toArray(new String[] {}));
	}

	public List<String> getParam() {
		return paramList;
	}

	public void addParam(String param) {
		paramList.add(param);
	}

	public void addAllParam(List<String> paramList) {
		paramList.addAll(paramList);
	}

	/**
	 * 
	 * @����: ���ݹ���;����������ɶ�Ӧ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����02:20:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param bj
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public abstract List<HashMap<String, String>> generate(String pzgzid,
			List<HashMap<String, String>> bj);

	/**
	 * 
	 * @����: ���ݹ���;����������ɶ�Ӧ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����02:20:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param bj
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> generate(String pzgzid) {
		return generate(pzgzid, null);
	}

	/**
	 * 
	 * @����:���ݹ���;����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-9 ����03:32:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param data
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> generate(String pzgzid, Object data) {
		return generate(pzgzid, (List<HashMap<String, String>>) data);
	}

	/**
	 * 
	 * @����: ��ȡ���������Ϣ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-24 ����02:20:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public abstract List<HashMap<String, String>> getTJpzxxId(String pzgzid);

	/**
	 * @return the mapParam
	 */
	public Map<String, String> getMapParam() {
		return mapParam;
	}

	public String getMapParam(String key) {
		return mapParam.get(key);
	}

	/**
	 * @param mapParamҪ���õ�
	 *            mapParam
	 */
	public void putMapParam(String key, String value) {
		mapParam.put(key, value);
	}
}
