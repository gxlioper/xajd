/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:03:03 
 */  
package com.zfsoft.xgxt.rcsw.sxjy;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	�� DwjlService
 * @description	�� TODO(��������������)
 * @author 		��CP��1352��
 * @date		�� 2017-11-20 ����10:36:02
 * @version 	V1.0
 */

public class SxjyService extends SuperServiceImpl<SxjyForm, SxjyDao> {
	
	private SxjyDao dao = new SxjyDao();
	
	public SxjyService() {
		super.setDao(dao);
	}
	public boolean isExist(SxjyForm model) {
		// TODO Auto-generated method stub
		return dao.isExist(model);
	}
}
