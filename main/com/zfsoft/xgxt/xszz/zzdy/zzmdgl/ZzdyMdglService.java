/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:40:48 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzmdgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xszz.zzdy.jcsz.ZzdyJcszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-11-23 ����08:40:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdyMdglService extends SuperServiceImpl<ZzdyMdglForm, ZzdyMdglDao> {
	private ZzdyMdglDao dao = new ZzdyMdglDao();
	
	/**
	 * 
	 * @����:��������ͬ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-23 ����05:26:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean zzmdTb(String xn,String xq) throws Exception {
		return dao.zzmdTb(xn, xq);
	}
	/**
	 * 
	 * @����:���������޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-23 ����05:55:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean editZzmd(ZzdyMdglForm model) throws Exception {
		model.setFfzt(model.getBghzt());
		model.setYffje(model.getBghje());
			dao.runUpdate(model);
			//��¼������־
			return dao.insertBgLog(model);
	}
	
	public List<HashMap<String, String>> getBgjlList(ZzdyMdglForm model)
	throws Exception {
		return dao.getBgjlList(model);
	}


}
