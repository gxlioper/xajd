/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-1-5 ����11:21:58 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.pjxmhz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����������
 * @�๦������:  �㽭��ѧ����������-ͳ�Ʋ�ѯ-������Ŀ����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-1-5 ����10:26:12 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmhzService extends SuperServiceImpl<PjxmhzForm, PjxmhzDao>{
	
	private PjxmhzDao dao = new PjxmhzDao();
	
	public PjxmhzService(){
		super.setDao(dao);
	}
	
	/**
	 * @����: 
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-1-9 ����10:10:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param fyFlag
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> viewRs(PjxmhzForm model,User user,Boolean fyFlag) throws Exception{
		return dao.viewRs(model, user,fyFlag);
	}
}
