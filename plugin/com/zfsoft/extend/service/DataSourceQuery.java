/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-5 ����05:25:54 
 */  
package com.zfsoft.extend.service;

import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-5 ����05:25:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public abstract class DataSourceQuery {

	/**
	 * 
	 * @����:��ȡ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-8 ����09:39:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public abstract List<HashMap<String,String>> getData();
}
