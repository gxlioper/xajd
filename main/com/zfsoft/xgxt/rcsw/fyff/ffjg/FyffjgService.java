/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-10 ����10:18:47 
 */  
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡����÷��š������Ž��  ����ģ��
 * @�๦������: ���Ž��ά��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-10 ����10:18:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyffjgService extends SuperServiceImpl<FyffjgForm, FyffjgDao> {
	
	
	
	private FyffjgDao dao = new FyffjgDao();
	
	public FyffjgService() {
		super.setDao(dao);
	}
	
	
	
	/**
	 * 
	 * @����:�ж��Ƿ��Ѵ��ڽ���⵱�У�������ѧ�ţ�����ʱ�䣬������Ŀ������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����02:05:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByFfjg(FyffjgForm form) 
			throws Exception {
		
		
		if("save".equalsIgnoreCase(form.getType())){
			//�����ж�
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
		}else {
			//�޸��ж�
			String num = dao.checkExistForUpdate(form);
			return Integer.valueOf(num) > 0;
		}
		
	}
	
	
	/**
	 * 
	 * @����:���÷����������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-10 ����02:10:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneFyffjgList(String guid) {
		
		return dao.getOneFyffjgList(guid);
	}
	
	/**
	 * 
	 * @����:���ݷ�����Ŀ�����ȡ���ŷ�ʽ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-29 ����08:07:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffxmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFffs(String ffxmdm){
		
		return dao.getFffs(ffxmdm);
	}
	
}
