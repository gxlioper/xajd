/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:00:29 
 */  
package xsgzgl.gygl.gyyggl;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;




/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy [���ţ�754]
 * @ʱ�䣺 2013-7-30 ����04:00:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyygzwdmglService  extends SuperServiceImpl<GyygzwdmglForm, GyygzwdmglDao> {
	private GyygzwdmglDao dao=new GyygzwdmglDao();
	
	public GyygzwdmglService() {
		super.setDao(dao);
	}
	
	public boolean isExist(GyygzwdmglForm model) {
		// TODO �Զ����ɷ������
		return dao.isExist(model);
	}

	public boolean checkDel(String values) {
		
		return dao.checkDel(values);
	}

	
}
