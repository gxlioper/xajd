/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-16 ����10:53:38 
 */
package com.zfsoft.xgxt.base.export.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-16 ����10:53:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ExportUtils {
	/**
	 * 
	 * @����: mapת��Ϊstring����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-16 ����10:58:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hm
	 * @return String[] ��������
	 */
	public static String[] MapToArray(HashMap<String, String> hm) {
		if (null == hm || hm.size() <= 0) {
			return null;
		}
		String[] param = new String[hm.size()];
		int i = 0;
		Iterator<Entry<String, String>> it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			param[i] = (String) entry.getValue();
			i++;
		}
		return param;
	}
}
