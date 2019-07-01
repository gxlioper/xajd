/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-25 ����11:30:08 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-ѧ��ԤԼ��ѯ
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-25 ����11:30:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyysqService extends SuperServiceImpl<XsyysqForm, XsyysqDao>{

	public XsyysqService() {
		super.setDao(new XsyysqDao());
	}
	
	/**
	 * 
	 * @����:��ѯ��ѯԤԼ˵��
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����10:14:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param 
	 * @return
	 * boolean �������� 
	 */
	public String getZxyysm() throws Exception{
		return dao.getZxyysm();
	}
	
	/**
	 * 
	 * @����:�������������������
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����03:17:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllList(){
		return dao.getXlwtAllList();
	}
	
	/**
	 * 
	 * @����:�����ѡ��������������
	 * @���ߣ���־��
	 * @���ڣ�2014-4-29 ����02:17:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxdmstr   ����'23','123','213'
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllListByLxdm(String lxdmstr){
		return dao.getXlwtAllListByLxdm(lxdmstr);
	}
	
	/**
	 * 
	 * @����: ȡ��ѧ��ԤԼ��ѯ����
	 * @���ߣ���־��
	 * @���ڣ�2014-4-29 ����11:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid 
	 * @param qxyyyy ȡ��ԤԼԭ��
	 * @param yyzt ԤԼ״̬
	 * @return
	 * boolean �������� 
	 */
	public boolean cancelXsyysq(String sqid, String qxyyyy, String yyzt) throws Exception{
		return dao.cancelXsyysq(sqid, qxyyyy, yyzt);
	}
	
	/**
	 * 
	 * @����: ѧ����ѯ����(��������id)
	 * @���ߣ���־��
	 * @���ڣ�2014-5-7 ����11:46:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid 
	 * @param zxxgmydpf ��ѯЧ�����������
	 * @param xszxpj ѧ����ѯ����
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxpj(String sqid, String zxxgmydpf, String xszxpj) throws Exception{
		return dao.setZxpj(sqid, zxxgmydpf, xszxpj);
	}
	
	/**
	 * 
	 * @����: ѧ����ѯ����(������ѯid)
	 * @���ߣ���־��
	 * @���ڣ�2014-5-9 ����15:00:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zxid 
	 * @param zxxgmydpf ��ѯЧ�����������
	 * @param xszxpj ѧ����ѯ����
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxpjByZxid(String zxid, String zxxgmydpf, String xszxpj) throws Exception{
		return dao.setZxpjByZxid(zxid, zxxgmydpf, xszxpj);
	}
}
