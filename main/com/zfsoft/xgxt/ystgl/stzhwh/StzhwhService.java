/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-14 ����06:00:43 
 */
package com.zfsoft.xgxt.ystgl.stzhwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-8-14 ����06:00:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class StzhwhService extends SuperServiceImpl<StzhwhForm, StzhwhDao> {
	// �ݶ���ǰѧ��ѧ�ڵ�ά������
	public List<HashMap<String, String>> getzhwhList(String xn, String stid,
			String tnzt) {
		return dao.getzhwhList(xn, stid, tnzt);
	}

	// ��Ա״̬ά��list
	public List<HashMap<String, String>> getStCyZtWhList(String stid) {
		return dao.getStCyZtWhList(stid);
	}

	public List<HashMap<String, String>> getStzhwhList(StzhwhForm t, User user)
			throws Exception {
		return dao.getStzhwhList(t, user);
	}
	
	public List<HashMap<String, String>> getCycjlist(String stid,String xh){
		return dao.getCycjlist(stid,xh);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ���ɼ�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-4-11 ����07:02:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param stid
	 * @param xh
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int delCycj(String[] values) throws Exception{
		return dao.delCycj(values);
	}
}
