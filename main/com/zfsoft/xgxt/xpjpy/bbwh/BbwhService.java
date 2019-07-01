/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-26 ����05:35:29 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-11-26 ����05:35:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbwhService extends  SuperServiceImpl<BbwhForm, BbwhDao> {

	private BbwhDao dao = new BbwhDao();

	public BbwhService() {
		super.setDao(dao);
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-27 ����11:26:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(){
		
		return dao.getBbxxList();
	}
	
	/**
	 * @��������ȡ������Ϣ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBbxx(String bbdm){
		return dao.getBbxx(bbdm);
	}
	
	/**
	 * @��������ȡ�����б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��4��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bblx
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		return dao.getBbxxList(bblx);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-28 ����09:48:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm){
		return dao.getDataById(bbdm);
	}
	
}
