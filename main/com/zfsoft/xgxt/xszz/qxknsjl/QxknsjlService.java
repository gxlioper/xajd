/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-20 ����06:27:43 
 */  
package com.zfsoft.xgxt.xszz.qxknsjl;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-4-21 ����08:35:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class QxknsjlService extends SuperServiceImpl<QxknsjlForm, QxknsjlDao> {
	
	private QxknsjlDao dao = new QxknsjlDao();
		
	public QxknsjlService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(��õ���ѧ���϶���Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����05:38:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneKnsjgList(String  guid) throws Exception {
		 
		return dao.getOneKnsjgList(guid);
	}
	
	/**
	 * 
	 * @����:TODO(��õ���ѧ��ȡ����������¼)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����06:16:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgguid
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneKnsqxjlList(String jgguid) throws Exception {
		 
		return dao.getOneKnsqxjlList(jgguid);
	}

}
