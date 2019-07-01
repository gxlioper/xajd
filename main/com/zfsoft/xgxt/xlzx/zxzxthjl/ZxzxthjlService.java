/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-1 ����09:57:03 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-7-1 ����09:57:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxthjlService  extends SuperServiceImpl<ZxzxthjlForm,ZxzxthjlDao>{
	
	private ZxzxthjlDao rd = new ZxzxthjlDao();
	public ZxzxthjlService() {
		super.setDao(rd);
	}
	/**
	 * @����: ɾ��������ѯ̸����¼��Ϣ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-4 ����10:40:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delZxzxthjlId(String[] id) throws Exception {	
			return dao.delZxzxthjlId(id);
		}
	/**
	 * @����: ����Ψһ���ж�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-5 ����07:49:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addCheck(ZxzxthjlForm form) throws Exception {
			String num = dao.addCheck(form);
			return Integer.valueOf(num) > 0;	
		}
	
	public HashMap<String, String> getInfoByZgh(String zgh) {
		return dao.getInfoByZgh(zgh);
	}
	/**
	 * @����: ��ȡһ���������������������
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-5 ����09:53:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYblxwtlbList() {
		return dao.getYblxwtlbList();
	}
	/**
	 * chakan 
	 */
	public HashMap<String, String> getThjlxx(String id) {
		return dao.getThjlxx(id);
	}
	/**
	 * @����: ̸����¼�����ڱ���
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-7-13 ����06:57:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getThjl(String id) {
		return dao.getThjl(id);
	}
	/**
	 * @����: ȡһ���������
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-14 ����05:02:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYbwtlb(String id) {
		return dao.getYbwtlb(id);
	}
	/**
	 * @����: ȡ�����ϰ��;��񼲲�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-14 ����05:03:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZajb(String id) {
		return dao.getZajb(id);
	}
}
