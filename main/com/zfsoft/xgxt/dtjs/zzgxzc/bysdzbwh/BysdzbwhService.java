/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��4�� ����2:30:27 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת������ģ��
 * @�๦������: ��ҵ����֧������ά��Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��4�� ����2:30:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysdzbwhService extends SuperServiceImpl<BysdzbwhForm,BysdzbwhDao>{

	/** 
	 * @����:�жϵ�֧������������Ƿ��Ѿ�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��4�� ����5:41:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bysdzbwhForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(BysdzbwhForm bysdzbwhForm) {
		boolean result = false;
		String type = bysdzbwhForm.getType();
		if("save".equalsIgnoreCase(type)){
			result = dao.isExistForAdd(bysdzbwhForm);
		}else{
			result = dao.isExistForUpdate(bysdzbwhForm);
		}
		return result;
	}

	/** 
	 * @����:�жϵ�֧�������Ƿ��Ѿ���ʹ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��7�� ����10:14:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public HashMap<String,Object> isUsed(String [] ids) {
		List<HashMap<String,String>> dzbUsedList = dao.getUsedList(ids);
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("isUsed", false);
		if(dzbUsedList.size()>0){
			result.put("isUsed", true);
			result.put("dzbmc", dzbUsedList.get(0).get("dzbmc"));
		}
		return result;
	}

}
