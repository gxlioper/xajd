/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-2 ����02:33:02 
 */
package com.zfsoft.xgxt.axcs.axlb;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-2 ����02:33:02
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class AxlbglService extends SuperServiceImpl<AxlbglForm, AxlbglDao> {
	private AxlbglDao dao = new AxlbglDao();

	/**
	 * 
	 * @����:��ȡ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����03:44:04
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
	public List<HashMap<String, String>> getPageList(AxlbglForm model) throws Exception {
		return super.getPageList(model);
	}
	/**
	 * 
	 * @����:���Ӱ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����04:00:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addAxlb(AxlbglForm model) throws Exception{
		return dao.addAxlb(model);
		
	}
	/**
	 * 
	 * @����:�޸İ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����04:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateAxlb(AxlbglForm model) throws Exception{
		return dao.runUpdate(model);
	}
	/**
	 * 
	 * @����:ɾ���������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����04:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public int delAxlb(String values) throws Exception{
		return dao.deleteAxlb(values);
	}
	/**
	 * 
	 * @����:��������Ƿ�ռ��
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
	 *��ȡ�������
	 */
	public String getAxlbmc(String lbdm) throws Exception {
		return dao.getAxlbmc(lbdm);

	}
}
