/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:46:29 
 */
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:46:29
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XslxwhService extends SuperServiceImpl<XslxwhForm, XslxwhDao> {
	private XslxwhDao dao = new XslxwhDao();
	/**
	 * 
	 * @����:��ȡѧ������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-14 ����04:12:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXslxList() {
		return dao.getXslxList();
	}
	public List<HashMap<String, String>> getXslxList(String[] xslxdms){
		
		return dao.getXslxList(xslxdms);
	}
	public String getXslxMc(String xslxdms){
		String xslxmc = "";
		if(xslxdms!=null){
			String[] xslxdm = xslxdms.split(",");
			List<HashMap<String, String>> knlxInfoList = this.getXslxList(xslxdm);
			if(knlxInfoList!=null && knlxInfoList.size()>0){
				for(int i=0;i<knlxInfoList.size();i++){
					if(i==knlxInfoList.size()-1){
						xslxmc += knlxInfoList.get(i).get("xslxmc");
					}else{
						xslxmc += knlxInfoList.get(i).get("xslxmc")+" ��";
					}
				}
			}
		}
		return xslxmc;
	}

	/**
	 * ��ȡ����б�
	 */
	public List<HashMap<String, String>> getPageList(XslxwhForm model) throws Exception {
		return super.getPageList(model);
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����01:14:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean addXslx(XslxwhForm model) throws Exception{
		return dao.addXslx(model);
		
	}
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����01:14:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXslx(XslxwhForm model) throws Exception{
		return dao.runUpdate(model);
	}
	/**
	 * 
	 * @����:ɾ��ѧ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-2 ����04:10:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public int delXslx(String values) throws Exception{
		return dao.deleteXslx(values);
	}
	/**
	 * 
	 * @����:ѧ�������Ƿ�ռ��
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
				flag = isExistsXmData(lbdms);
		}
		return flag;
	}
	
	/**
	 *��ȡѧ������
	 */
	public String getXslxmc(String lbdm) throws Exception {
		return dao.getXslxmc(lbdm);

	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�ж�ѧ������Ƿ���ʹ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����01:37:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	private boolean isExistsXmData(String[] lbdms) throws Exception{
		boolean flag = false;
		List<String>  xslxData = dao.getTsxslxData();
		for (int i = 0; i < lbdms.length; i++) {
			if(xslxData.contains(lbdms[i])){
				flag=true;
				break;
			}
			
		}
		return flag;
		
	}

}
