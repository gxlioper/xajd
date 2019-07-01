/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-26 ����11:07:18 
 */  
package com.zfsoft.xgxt.comm.bbdmpz.service;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.bbdmpz.dao.BbdmDao;
import com.zfsoft.xgxt.comm.bbdmpz.model.BbdmModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-26 ����11:07:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbdmService extends SuperServiceImpl<BbdmModel, BbdmDao> {
	
	{
		super.dao = new BbdmDao();
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����01:00:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>>  getBbdmList(String mkdm) throws SystemException{
		return dao.getBbdmList(mkdm);
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����01:00:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws SystemException
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getBbdm(String guid) throws SystemException{
		return dao.getBbdm(guid);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����01:00:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws SystemException
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getBbmbInfo(String guid) throws SystemException{
		return dao.getBbmbInfo(guid);
	}

	/**
	 * 
	 */
	public BbdmModel getModel(String mkdm) throws Exception{
		return dao.getModel(mkdm);
	}
	
	public BbdmModel getModelByGuid(String guid) throws Exception{
		return dao.getModelByGuid(guid);
	}
	
	/**
	 * 
	 * ��ȡģ����ز���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����12:59:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getBbdmCs(String mkdm) throws Exception{
		return dao.getBbdmCs(mkdm);
	}
	
	/**
	 * 
	 * @����:��ȡ��ǰѡ�еı���ID
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����05:02:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @param pk
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getDybb(String mkdm , String pk)throws Exception{
		return dao.getDybb(mkdm, pk);
	}
	
	/**
	 * 
	 * @����:���ñ���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����05:04:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mkdm
	 * @param pk
	 * @param guid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean setupDybb(String mkdm , String pk , String guid) throws Exception{
		return dao.setupDybb(mkdm, pk, guid);
	}
	
	
}
