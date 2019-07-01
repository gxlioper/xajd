/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:48:42 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.jyjl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: �豸����ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:48:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JyjlService extends SuperServiceImpl<JyjlModel, JyjlDao> {

	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(JyjlModel t) throws Exception {
		
		String[] fldmArr = t.getFldmArr();
		String[] sbdmArr = t.getSbdmArr();
		String[] jysjArr = t.getJysjArr();
		String[] bzArr = t.getBzArr();
		
		boolean result = true;
		
		for (int i = 1 , j = fldmArr.length ; i < j ; i++){
			t.setFldm(fldmArr[i]);
			t.setSbdm(sbdmArr[i]);
			t.setJysj(jysjArr[i]);
			t.setBz(bzArr[i]);
			result = super.runInsert(t);
		}
		
		return result;
	}

	
	/**
	 * 
	 * @����: �黹�豸
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-12-18 ����09:45:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean ghsb(JyjlModel t,String ids) throws Exception{
		
		String[] idArr = ids.split(",");
		
		if (idArr == null || idArr.length == 0){
			return false;
		}
		
		boolean result = true;
		
		for (String id : idArr){
			t.setId(id);
			result = super.runUpdate(t);
		}
		
		return result;
	}
	
}
