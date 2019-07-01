/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����10:04:09 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����10:04:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbjgService extends SuperServiceImpl<XxsbjgForm, XxsbjgDao>{

	/**
	 * 
	 * @����:����sbsqidɾ��������¼
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-3 ����10:08:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sbsqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteBySqid(String sbsqid) throws Exception{
		return dao.deleteBySqid(sbsqid);
	}
	
	
	/**
	 * 
	 * @����:��ȡ��ѯ��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-9 ����03:04:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqjgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getModelMap(String sqjgid )throws Exception{
		return dao.getModelMap(sqjgid);
	}
	
	/**
	 * 
	 * @����:����ظ�
	 * @���ߣ�1036
	 * @���ڣ�2014-7-11 ����04:46:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param sblx
	 * @param zc
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExist(String xh , String sblx  , String zc)throws Exception{
		return dao.checkExist(xh, sblx, zc);
	}
	
	public List<HashMap<String, String>> getXnList() {
		List<HashMap<String, String>> list = dao.getXnList();
		return list;
	}
	
	public List<HashMap<String,String>> getZcList(String xn, String xq, String sblx) {
		return dao.getZcList(xn, xq, sblx);
	}
	
	public List<HashMap<String,String>> getQzrq(String xn, String xq, String zbid) {
		return dao.getQzrq(xn, xq, zbid);
	}
	
	public XxsbjgService(){
		setDao(new XxsbjgDao());
	}
	
}
