/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-5 ����09:56:16 
 */  
package xsgzgl.rcsw.wsbz.dmwh;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-5-5 ����09:56:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsbzDmwhService extends SuperServiceImpl<WsbzDmwhForm, WsbzDmwhDao> {
	public HashMap<String, String> getWsbzCk(String fddm){
		return dao.getWsbzCk(fddm);
	}
	
	//�����Ƿ����Ŀ��������Ŀ�����Ƿ����
	public boolean checkIsExists(WsbzDmwhForm t){
		return dao.checkIsExists(t);
	}
	
	//ɾ��ʱ��鱶ɾ������Ŀ�Ƿ���ʹ����
	public boolean checkIsUsingNow(String[]para){
	   return dao.checkIsUsingNow(para);
	}

	public HashMap<String, String> getQjcs() {
		// TODO Auto-generated method stub
		return dao.getQjcs();
	}

	public boolean runUpdateQjcs(String bmcs, String jzts, String jzsj) throws Exception {
		// TODO Auto-generated method stub
		return dao.runUpdateQjcs( bmcs,  jzts,  jzsj);
	}
	

}
