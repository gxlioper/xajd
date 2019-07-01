/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-15 ����01:14:50 
 */
package com.zfsoft.xgxt.base.util;

import java.util.HashMap;
import java.util.List;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:
 * @�๦������: List��س��ò���
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-15 ����01:14:50
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ListUtils {
	/**
	 * 
	 * @����: ��ȡlist size�������ظ��ն���list�жϣ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-15 ����01:16:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * Integer �������� 
	 */
	public static Integer getListSize(List<HashMap<String, String>> list) {
		if (null == list || list.size() <= 0) {
			return 0;
		}
		return list.size();
	}
}
