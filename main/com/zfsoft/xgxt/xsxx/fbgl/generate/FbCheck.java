/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-11 ����03:43:15 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: ��������Ƿ�Ϸ�
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-11 ����03:43:15
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbCheck {
	/**
	 * 
	 * @����: ����ַ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����03:45:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @param xzcd
	 * @return boolean ��������
	 */
	public boolean checkLength(String value, int xzcd) {
		if (value.length() <= xzcd) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @����: ����ַ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����03:45:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @param xzcd
	 * @return boolean ��������
	 */
	public boolean checkLength(Integer value, int xzcd) {
		if (value.toString().length() <= xzcd) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @����: ��������Ƿ񳬹����ݿ��г���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����04:23:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tableName
	 * @param columnName
	 * @param nowValue
	 * @return boolean ��������
	 */
	public boolean checkTableColumLength(String tableName, String columnName,
			String nowValue) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from all_TAB_COLUMNS ");
		sb.append(" where table_name=? and column_name=?");
		HashMap<String, String> hm = DAO.getInstance().getMapNotOut(
				sb.toString(), new String[] { tableName.toUpperCase(), columnName.toUpperCase() });
		String column_length = hm.get("data_length");
		// ����С�����Ƴ���
		if (nowValue.length() <= Integer.parseInt(column_length)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @����: ���ֵ��Ψһ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����04:28:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tableName
	 * @param columnName
	 * @param nowValue
	 * @return boolean ��������
	 */
	public boolean checkUniqeKey(String tableName, String columnName,
			String nowValue) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(tableName);
		sb.append(" where ");
		sb.append(columnName);
		sb.append("=?");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] { nowValue });
		if (null == list || list.size() <= 0) {
			return true;
		}
		return false;
	}
}
