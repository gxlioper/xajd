/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.xlzx.cjtsxs;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	�� CjtsxsService
 * @description	�� ��������ѧ��service(��������������)
 * @author 		��������1282��
 * @date		�� 2017-11-7 ����02:01:31
 * @version 	V1.0 
 */

public class CjtsxsService extends SuperServiceImpl<CjtsxsForm, CjtsxsDao>{
	/**
	 * @description	�� �Ƿ���ڸ�ѧ��������ѧ�ꡢѧ�ڽ����жϣ�
	 * @author 		�� ������1282��
	 * @date 		��2017-11-7 ����02:40:25
	 * @return
	 */
	public boolean isExist(CjtsxsForm form){
		return dao.isExist(form);
	}
}
