/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-07-31 ����02:33:02 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-07-31 ����02:33:02
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XmlbglService extends SuperServiceImpl<XmlbglForm, XmlbglDao> {
	private XmlbglDao dao = new XmlbglDao();

	/**
	 * 
	 * @����:��ȡ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����03:44:04
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
	public List<HashMap<String, String>> getPageList(XmlbglForm model) throws Exception {
		return super.getPageList(model);
	}
	/**
	 * 
	 * @����:������Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����04:00:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addXmlb(XmlbglForm model) throws Exception{
		return dao.addXmlb(model);
		
	}
	/**
	 * 
	 * @����:�޸���Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����04:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXmlb(XmlbglForm model) throws Exception{
		return dao.runUpdate(model);
	}
	/**
	 * 
	 * @����:ɾ����Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����04:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public int delXmlb(String values) throws Exception{
		return dao.deleteXmlb(values);
	}
	/**
	 * 
	 * @����:��Ŀ����Ƿ�ռ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����02:58:59
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
	 *��ȡ��Ŀ���
	 */
	public String getXmlbmc(String lbdm) throws Exception {
		return dao.getXmlbmc(lbdm);

	}
	/**
	 * 
	 * @����:��Ŀ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����09:45:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbList(String stlbdm) throws Exception {
		return dao.getXmlbList(stlbdm);

	}
}
