/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-25 ����03:13:50 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx.bak;

import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-25 ����03:13:50
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglXsxxBakDao extends FbglXsxxDao {
	protected void setTableInfo() {
		this.setKey("xh");
		this.setTableName("XG_XSXX_FBGL_XSXX_LSB_BAK");
		this.setClass(FbglXsxxForm.class);
	}
}
