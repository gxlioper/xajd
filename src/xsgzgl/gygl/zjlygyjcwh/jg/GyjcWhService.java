/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-28 ����04:26:35 
 */  
package xsgzgl.gygl.zjlygyjcwh.jg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-28 ����04:26:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyjcWhService extends SuperServiceImpl<GyjcWhForm, GyjcWhDao> {
	public List<HashMap<String, String>> getJclbList(){
		return dao.getJclbList();
	}
}
