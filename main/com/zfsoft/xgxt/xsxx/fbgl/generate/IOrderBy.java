/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-17 ����09:38:54 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import java.util.HashMap;
import java.util.List;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-17 ����09:38:54
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public interface IOrderBy {
	/**
	 * 
	 * @����: ��ȡ����sql���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-17 ����03:12:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBaseSql();
	/**
	 * 
	 * @����: ��ȡsql��Ӧ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-17 ����03:12:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getParam();
	/**
	 * 
	 * @����: ��ȡ����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-17 ����03:13:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjPzxx ����������Ϣ
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGroupByData(
			List<HashMap<String, String>> tjPzxx);
}
