/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-10 ����09:30:11 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsfcx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-10 ����09:30:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsfcxService extends SuperServiceImpl<WsfcxForm, WsfcxDao>{
	private WsfcxDao dao = new WsfcxDao();
	
	/** 
	 * @����:�����ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-10 ����09:31:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param f
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public String cxtj(WsfcxForm f) throws Exception{
		String ids[] = f.getIds();
		List<String> failXms = new ArrayList<String>();
		for(int i = 0;i<ids.length;i++){
			boolean result = dao.cxtj(ids[i]);
			if (!result) {
				failXms.add(ids[i]);
			}
		}
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	
	/** 
	 * @����:�޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-10 ����11:03:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param f
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateWsfcx(WsfcxForm f) throws Exception{
		return dao.updateWsfcx(f);
	}
	
	/** 
	 * @����:��ȡ¥���ţ����Һţ���ȡ���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-10 ����11:03:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param f
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getCh(WsfcxForm f){
		return dao.getCh(f);
	}
	
	/** 
	 * @����:�����ֲ�ѯ��Ϣ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-10 ����11:30:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param f
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String, String> getWsfcx(WsfcxForm f){
		return dao.getWsfcx(f);
	}
}
