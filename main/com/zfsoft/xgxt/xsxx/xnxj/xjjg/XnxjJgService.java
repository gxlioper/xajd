/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-23 ����03:04:49 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-23 ����03:04:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjJgService extends SuperServiceImpl<XnxjJgForm, XnxjJgDao> {

	private XnxjJgDao dao = new XnxjJgDao();

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param dao
	 */
	public XnxjJgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����11:49:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 * XnxjJgForm �������� 
	 * @throws
	 */
	public XnxjJgForm getModel(String xh , String xn) throws Exception{
		return dao.getModel(xh, xn);
	}
	
	public HashMap<String , String> getXnxjJgInfo(String id) throws Exception{
		return dao.getXnxjJgInfo(id);
	}
	
	
	public List<HashMap<String , String>> getXnxjList(String xh , String xn) throws Exception{
		return dao.getXnxjList(xh, xn);
	}
	
	/**
	 * 
	 * @����:����ҵ��idɾ�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����11:50:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delXnxjJg(String ywid) throws Exception{
		return dao.delXnxjg(ywid);
	}
	
	/**
	 * 
	 * @����:��ȡѧ��С����ʷ��¼
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-24 ����11:55:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllXnxjList(String xh) throws Exception{
		return dao.getAllXnxjList(xh);
	}
}
