/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����03:28:03 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����03:28:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FbzgService extends SuperServiceImpl<FbzgForm, FbzgDao> {

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
	public boolean add(FbzgForm model) throws Exception{
		boolean isSuccess = false;
		//check
		FbzgForm curData = dao.getModel(model.getFbzgdm());
		if(curData == null){
			isSuccess = dao.runInsert(model);
		}
		return isSuccess;
	}
	
}
