/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-10 ����05:29:14 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-6-10 ����05:29:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RzkhService extends SuperServiceImpl<rzkhjgForm, RzkhDao> {
	
	//�жϵ�ǰѧ���ɲ��ڱ�ѧ�걾ѧ���ڽ�������Ƿ��п��˼�¼
	public boolean checkExistForSave(rzkhjgForm model){
		return dao.checkExistForSave(model);
	}
	
	//��ȡְλ����
	public HashMap<String, String> getZwmc(String zwdm){
		return dao.getZwmc(zwdm);
	}
	
	public HashMap<String, String> getKhjgxxMap(rzkhjgForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getKhjgxxMap(model, user);
	
		return xsxxmap;
	}
	
	public List<HashMap<String, String>> getKhjgxxList(rzkhjgForm model ) throws Exception{
		return dao.getKhjgxxList(model);
	}
	
	//��ȡѧ������
	public HashMap<String, String> getxqdz(String xqdm){
		return dao.getxqdz(xqdm);
	}
}
