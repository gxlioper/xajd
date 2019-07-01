/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-07-31 ����02:33:02 
 */
package com.zfsoft.xgxt.ystgl.dmwh;

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

public class YstglDmwhService extends SuperServiceImpl<YstglDmwhForm, YstglDmwhDao> {
	private YstglDmwhDao dao = new YstglDmwhDao();
	/**
	 * 
	 * @����:����������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-7 ����03:25:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getYstlbList(YstglDmwhForm model) throws Exception{
		return dao.getYstlbList(model);
	}
	public List<HashMap<String, String>> getYstlbListAll() throws Exception {
		return dao.getYstlbListAll();
	}
	public List<HashMap<String, String>> getXmlbListAll(String ystlbdm) throws Exception {
		return dao.getXmlbListAll(ystlbdm);
	}
	public List<HashMap<String, String>> getGkdwListAll() throws Exception {
		return dao.getGkdwListAll();
	}
	public List<HashMap<String, String>>  getGkdwList(YstglDmwhForm model) throws Exception{
		return dao.getGkdwList(model);
	}
	/**
	 * 
	 * @����:��Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-11 ����08:48:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getXmlbList(YstglDmwhForm model) throws Exception{
		return dao.getXmlbList(model);
	}
	
	/**
	 * 
	 * @����:�������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-07-31 ����04:00:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addYstlb(YstglDmwhForm model) throws Exception{
		return dao.addYstlb(model);
		
	}
	public boolean updateYstlb(YstglDmwhForm model) throws Exception{
		return dao.updateYstlb(model);
	}
	public int deleteYstlb(String[] values) throws Exception {
		return dao.deleteYstlb(values);
	}
	public YstglDmwhForm getYstlb(YstglDmwhForm model) throws Exception{
		return dao.getYstlb(model);
	}
	/**
	 * 
	 * @����:������Ŀ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-7 ����05:55:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addXmlb(YstglDmwhForm model) throws Exception{
		return dao.addXmlb(model);
		
	}
	public boolean updateXmlb(YstglDmwhForm model) throws Exception{
		return dao.updateXmlb(model);
	}
	public int deleteXmlb(String[] values) throws Exception {
		return dao.deleteXmlb(values);
	}
	public YstglDmwhForm getXmlb(YstglDmwhForm model) throws Exception{
		return dao.getXmlb(model);
	}
	
	/**
	 * 
	 * @����:�ҿ���λ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-14 ����05:55:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addGkdw(YstglDmwhForm model) throws Exception{
		return dao.addGkdw(model);
		
	}
	public boolean updateGkdw(YstglDmwhForm model) throws Exception{
		return dao.updateGkdw(model);
	}
	public int deleteGkdw(String[] values) throws Exception {
		return dao.deleteGkdw(values);
	}
	public YstglDmwhForm getGkdw(YstglDmwhForm model) throws Exception{
		return dao.getGkdw(model);
	}
	
	
	/**
	 * 
	 * @����:����������Ƿ�ռ��
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
	
	public boolean isExistsXmlbData(String values) throws Exception {
		boolean flag = false;
		if (values != null) {
			String[] lbdms = values.split(",");
			for (int i = 0; i < lbdms.length; i++) {
				flag = dao.isExistsXmlbData(lbdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	
	public boolean isExistsGkdwData(String values) throws Exception {
		boolean flag = false;
		if (values != null) {
			String[] lbdms = values.split(",");
			for (int i = 0; i < lbdms.length; i++) {
				flag = dao.isExistsGkdwData(lbdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	
}
