/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-25 ����03:14:08 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx.bak;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-25 ����03:14:08
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglXsxxBakService extends SuperServiceImpl<FbglXsxxForm, FbglXsxxBakDao> {
	FbglXsxxBakDao dao = new FbglXsxxBakDao();
	public FbglXsxxBakService() {
		this.setDao(dao);
	}
}
