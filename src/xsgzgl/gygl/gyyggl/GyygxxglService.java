/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:46:57 
 */  
package xsgzgl.gygl.gyyggl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy [���ţ�754]
 * @ʱ�䣺 2013-7-30 ����04:46:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyygxxglService extends SuperServiceImpl<GyygxxglForm, GyygxxglDao>{
	private GyygxxglDao dao = new GyygxxglDao();

	public GyygxxglService() {
		super.setDao(dao);
	}
	
	public HashMap<String, String> getYgxxmap (String ygbh){
		return dao.getYgxxmap(ygbh);
	}
	
	public String checkYgbh(String ygbh){
		return dao.checkYgbh(ygbh);
	}
	
	public List<HashMap<String, String>> getZwdmList()
	throws Exception {
		return dao.getZwdmList();
	}
	
}
