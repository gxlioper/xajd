/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����02:27:47 
 */  
package xsgzgl.szdw.jtff.jtmdwh;

import xsgzgl.szdw.jtff.jtff.JtffDao;
import xsgzgl.szdw.jtff.jtff.JtffForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-8 ����02:27:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtmdwhService extends SuperServiceImpl<JtmdwhForm, JtmdwhDao> {
	public int runDeletegdjtmd(String[] para) throws Exception{
		return dao.runDeletegdjtmd(para);
	}
}
