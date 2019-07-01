/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����03:53:11 
 */  
package com.zfsoft.xgxt.szdw.gzjl.lbgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����03:53:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlLbglService extends SuperServiceImpl<GzjlLbglForm, GzjlLbglDao> {
	
	/**
	 * 
	 * @����:��ȡ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-6-25 ����03:51:50 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(GzjlLbglForm model) throws Exception {
		return super.getPageList(model);
	}
	/**
	 * 
	 * @����:���ӹ�����¼���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-6-25 ����03:51:50 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addGzjllb(GzjlLbglForm model) throws Exception{
		return dao.addGzjllb(model);
		
	}
	/**
	 * 
	 * @����:�޸Ĺ�����¼���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-6-25 ����03:51:50 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGzjllb(GzjlLbglForm model) throws Exception{
		return dao.runUpdate(model);
	}
	/**
	 * 
	 * @����:ɾ��������¼���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-6-25 ����03:51:50 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public int delGzjllb(String values) throws Exception{
		return dao.deleteGzjllb(values);
	}
	/**
	 * 
	 * @����:������¼����Ƿ�ռ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-10 ����02:58:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isUsed(String values) throws Exception {
		boolean flag = false;
		if (values != null) {
			String[] lbdms = values.split(",");
			for (int i = 0; i < lbdms.length; i++) {
				flag = dao.isExistsXmData(lbdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 *��ȡ������¼���
	 */
	public String getGzjllbmc(String lbdm) throws Exception {
		return dao.getGzjllbmc(lbdm);

	}
	/**
	 * 
	 * @����:��������б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public List<HashMap<String,String>>  getGzjllbList() throws Exception {
		return dao.getGzjllbList();

	}
	
}
