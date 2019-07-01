/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-24 ����11:03:37 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-��ѯʦ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-24 ����11:03:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxsxxService extends SuperServiceImpl<ZxsxxForm, ZxsxxDao>{

	public ZxsxxService() {
		super.setDao(new ZxsxxDao());
	}
	
	/** 
	 * @����:����ְ���Ų�ѯ��ʦ��Ϣ
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����02:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(String zgh){
		
		return dao.getFdyInfo(zgh);
	}
	
	/** 
	 * @����:(����ְ���Ų�ѯ��ѯʦ��Ϣ�Ƿ����)
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����03:35:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * boolean �������� 
	 */
	public boolean zxsxxIsExist(String zgh) {
		return dao.zxsxxIsExist(zgh);
	}
	
	/**
	 * 
	 * @����:���������ѯʦ   
	 *       ���� ������ľ�� [��][�̷߽�չ�о�����][20020964] [����10��][��ԤԼ5��]
	 * @���ߣ���־��
	 * @���ڣ�2014-4-30 ����04:36:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllList(){
		return dao.getZxsxxAllList();
	}
	
	/**
	 * 
	 * @����:���������ѯʦ  (����ԤԼʱ��) 
	 *       ���� ������ľ�� [��][�̷߽�չ�о�����][20020964] [����10��][��ԤԼ5��]
	 * @���ߣ���־��
	 * @���ڣ�2014-4-30 ����04:36:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllByYysjList(String yysj){
		return dao.getZxsxxAllByYysjList(yysj);
	}
	
	/**
	 * 
	 * @����:������ѯʦ�ڸ�״̬
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����09:14:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh ְ����
	 * @param status �ڸ�״̬
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxsxxStatus(String zgh, String status) throws Exception{
		return dao.setZxsxxStatus(zgh, status);
	}
	
	/**
	 * 
	 * @����:������ѯԤԼ˵��
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����09:43:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zxyysm ��ѯԤԼ˵��
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxyysm(String zxyysm) throws Exception{
		return dao.setZxyysm(zxyysm);
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
}
