/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-18 ����02:43:59 
 */  
package com.zfsoft.xgxt.jjgl.jjnj;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.SfbzService;

import java.util.HashMap;
import java.util.List;

/** 
 * @�๦������: �ҽ��꼶
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-18 ����02:43:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjnjService extends SuperServiceImpl<JjnjForm, JjnjDao> {
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����05:03:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean add(JjnjForm model) throws Exception{
		//��������
		String dm = dao.getPrimayKey();
		//����
		model.setJjnjdm(dm);
		
		return dao.runInsert(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����05:03:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delete(String delIds) throws Exception{
		if(StringUtils.isBlank(delIds)){
			return false;
		}
		String deleteId = delIds.split(",")[0];
		//check
		int rs = Integer.valueOf(dao.checkReference(deleteId));
		if(rs > 0){
			return false;
		}
		
		dao.runDelete(delIds.split(","));
		
		new SfbzService().deleteSfbzByJJnjdm(deleteId);
		
		return true;
	}

    public List<HashMap<String,String>> getJjnjList() {
		return dao.getJjnjList();
    }

}
