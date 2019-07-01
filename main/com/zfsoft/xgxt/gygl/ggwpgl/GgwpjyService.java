/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-19 ����01:58:53 
 */  
package com.zfsoft.xgxt.gygl.ggwpgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.sbgl.jyjl.JyjlModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-4-19 ����01:58:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GgwpjyService extends SuperServiceImpl<GgwpjyForm, GgwpjyDao>{
	private GgwpjyDao dao = new GgwpjyDao();
	
	@Override
	public boolean runInsert(GgwpjyForm t) throws Exception {
		
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
	 * @����:�Ƿ�黹
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-20 ����10:21:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean sfgh(GgwpjyForm t){
		boolean result = true;
		if("update".equalsIgnoreCase(t.getType())){
			result = dao.sfgh(t);
		}else{
			String[] sbdmArr = t.getSbdmArr();			
			for(int i = 1;i<sbdmArr.length;i++){
				t.setSbdm(sbdmArr[i]);
				result = dao.sfgh(t);
				if(!result){
					break;
				}
			}
		}
		return result;
	}
	
	/** 
	 * @����:�黹
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-20 ����02:00:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean ghsb(GgwpjyForm t,String ids) throws Exception{
		
		String[] idArr = ids.split(",");
		
		if (idArr == null || idArr.length == 0){
			return false;
		}
		
		boolean result = true;
		
		for (String id : idArr){
			t.setId(id);
			t.setGhzt("1");
			result = super.runUpdate(t);
		}
		
		return result;
	}
}
