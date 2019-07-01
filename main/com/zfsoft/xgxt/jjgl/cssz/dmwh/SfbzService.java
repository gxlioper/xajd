/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����03:16:14 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����03:16:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SfbzService extends SuperServiceImpl<SfbzForm, SfbzDao> {

	/**
	 * 
	 * @����:�½�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����09:26:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean add(SfbzForm model) throws Exception{
		boolean isSuccess = false;
		//check
		SfbzForm curData = dao.getModelByXkdmAndNjdm(model.getJjxkdm(), model.getJjnjdm());
		if(curData == null){
			model.setId(UniqID.getInstance().getUniqIDHash().toUpperCase());
			isSuccess = dao.runInsert(model);
		}
		return isSuccess;
	}
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����04:46:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jjnjdm
	 * @return
	 * int �������� 
	 * @throws
	 */
	public boolean deleteSfbzByJJnjdm(String jjnjdm) throws Exception{
		return dao.deleteSfbzByJJnjdm(jjnjdm) > 0;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-26 ����04:46:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jjnjdm
	 * @return
	 * int �������� 
	 * @throws
	 */
	public boolean deleteSfbzByJJxkdm(String jjxkdm) throws Exception{
		return dao.deleteSfbzByJJxkdm(jjxkdm) > 0;
	}
}
