/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-26 ����04:34:40 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl.qsdskh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ���ҵ�ʦ����
 * @���ߣ� ��ˮ��[���ţ�1150]
 * @ʱ�䣺 2014-11-26 ����04:34:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QsdskhService extends SuperServiceImpl<QsdskhForm, QsdskhDao> {

	public QsdskhService(){
		setDao(new QsdskhDao());
	}

	/**
	 * 
	 * ��ȡ���ҵ�ʦ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getQsdskh(QsdskhForm model){
		
		return dao.getQsdskh(model);
	}
	
	/**
	 * �������ҵ�ʦ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateQsdskh(QsdskhForm model) throws Exception{
		return dao.updateQsdskh(model);
	}
	
	/**
	 * ���ҵ�ʦ���˱��棨��飩
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qsdskhAddCheck(QsdskhForm model) throws Exception{
		return dao.qsdskhAddCheck(model);
	}
	
	/**
	 * 
	 * ɾ����Ϣ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����04:34:40 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ldxx
	 * @param qsh
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int[] deleteDsdsxxPl(List<String[]> pks) throws Exception{
		return dao.deleteDsdsxxPl(pks);
	}
	
}
