/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-6 ����03:34:28 
 */  
package com.zfsoft.xgxt.gygl.gywp;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: (��Ԣ��Ʒ����) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-6 ����03:34:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GywpxxService extends SuperServiceImpl<GywpxxForm, GywpxxDAO> {
	
	private GywpxxDAO dao=new GywpxxDAO();
	
	public GywpxxService(){
		super.setDao(dao);
	}

	/**
	 * @throws Exception  
	 * @����:(��ȡ���й�Ԣ¥��Ʒά����Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-6 ����03:40:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGywplrxxList(GywpxxForm model,
			User user) throws Exception {
		// TODO �Զ����ɷ������
		return dao.getGywplrxxList(model,user);
	}

	/** 
	 * @����:(��ȡ��Ʒ����List)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����08:37:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWpdlList() {
		// TODO �Զ����ɷ������
		return dao.getWpdlList();
	}

	/** 
	 * @����:(��ȡ��Ʒ���List)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-7 ����08:42:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWplbList() {
		// TODO �Զ����ɷ������
		return dao.getWplbList();
	}
}
