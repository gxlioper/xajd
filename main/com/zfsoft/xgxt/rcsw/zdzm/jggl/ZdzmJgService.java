/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����02:49:36 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.jggl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����02:49:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmJgService extends SuperServiceImpl<ZdzmJgForm, ZdzmJgDao> {

	public ZdzmJgService(){
		setDao(new ZdzmJgDao());
	}
	
	/**
	 * @��������������IDɾ���������(����)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����03:38:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteZdzmJgBySqid(String sqid) throws Exception{
		return dao.deleteZdzmJgBySqid(sqid) == 1 ? true : false;
	}
	
	/**
	 * @��������������IDsɾ��������ݣ�������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����05:23:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteZdzmJgs(String[] sqids) throws Exception{
		return dao.runDelete(sqids);
	}
	
}
