/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-8 ����02:38:32 
 */  
package com.zfsoft.xgxt.rcsw.xshdgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-8 ����02:38:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XshdglService extends SuperServiceImpl<XshdglForm, XshdglDao> {
	/**
	 * 
	 * @����:��ȡ���Ŵ���list
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-12 ����10:08:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBmdmList(){
		return dao.getBmdmList();
	}
	
	/**
	 * 
	 * @����:��ȡѧ�����¼map,���ڲ鿴����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-6-12 ����10:09:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXshdMap(String sqid){
        return dao.getXshdMap(sqid);
	}
	
	/**
	 * 
	 * @����:�Ƿ����ͬһ���쵥λͬһʱ�������������������ϵĻ���ƣ�����ǣ�����false,����񣬷���true
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-6-12 ����10:15:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsExistNotSame(XshdglForm t){
		return dao.checkIsExistNotSame(t);
	}
}
