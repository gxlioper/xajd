/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-17 ����04:08:23 
 */
package com.zfsoft.xgxt.zxdk.tyxs.shjg;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-4-17 ����04:08:23
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TyxsZzjgService extends SuperServiceImpl<TyxsZzjg, TyxsZzjgDao> {
	/**
	 * 
	 * @����:��ѧ�š�ѧ���ѯ��¼����
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-23 ����05:14:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCountByXhAndXn(TyxsZzjg t) {

		return dao.getCountByXhAndXn(t);
	}

	/**
	 * 
	 * @����:����ID��ѯ�����Ϣ
	 * @���ߣ�����Ӣ[���ţ�1177]
	 * @���ڣ�2015-4-23 ����05:15:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getZzjgById(String id) {
		return dao.getZzjgById(id);
	}
	
	public HashMap<String, String> getDjbById(String id) {
		return dao.getDjbById(id);
	}

}
