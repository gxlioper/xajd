/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-4 ����04:19:29 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ڶ�֤������-SERVICE�� 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-4 ����04:19:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmSqService extends SuperServiceImpl<ZdzmSqForm, ZdzmSqDao> {

	public ZdzmSqService(){
		super.setDao(new ZdzmSqDao());
	}
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ�����¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����09:43:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmListByXh(String xh) throws Exception{
		return dao.getZdzmListByXh(xh);
	}
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ��������е������¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����10:00:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmInShlcByXh(String xh) throws Exception{
		return dao.getZdzmInShlcByXh(xh);
	}
	
	/**
	 * 
	 * @����:�����ڶ�֤��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����10:18:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdzmsq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveZdzmSq(ZdzmSqForm zdzmsq) throws Exception{
		return dao.runInsert(zdzmsq);
	}
	
	/**
	 * 
	 * @����:�����ڶ�֤������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����10:22:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdzmsq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateZdzmSq(ZdzmSqForm zdzmsq) throws Exception{
		return dao.runUpdate(zdzmsq);
	}
	
	/**
	 * 
	 * @����:ɾ���ڶ�֤������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����10:24:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdzmsqids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteZdzmSqs(String[] zdzmsqids) throws Exception{
		return dao.runDelete(zdzmsqids);
	}
	
	/**
	 * 
	 * @����:�ύ�ڶ�֤���������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����10:19:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdzmsq
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitZdzmSq(ZdzmSqForm zdzmsq) throws Exception{
		return false;
	}
	
	/**
	 * 
	 * @����:�����ڶ�֤������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����10:20:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelZdzmSq(String zdzmsqid) throws Exception {
		return false;
	}
}
