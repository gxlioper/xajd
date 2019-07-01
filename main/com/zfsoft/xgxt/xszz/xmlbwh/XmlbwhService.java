/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmlbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀ���ά��
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmlbwhService extends SuperServiceImpl<XmlbwhForm, XmlbwhDao> {

	private XmlbwhDao dao = new XmlbwhDao();

	public XmlbwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:������idֵ��1,������id
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public int getNewId() throws Exception {
		int maxId = 0;
		maxId = dao.getMaxId();
		return maxId + 1;
	}


	/**
	 * 
	 * @����:��ȡ��Ŀ�����뼰����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		List<HashMap<String, String>> list = dao.getXmlb();
		return list;
	}
	
	/**
	 * 
	 * @����:�ж��ظ����ݣ�������Ϊ׼
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRepeat(XmlbwhForm model) throws Exception {
		return dao.isRepeat(model);
	}
	
	
	/**
	 * 
	 * @����:�жϹ����ԣ����ݿɷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(XmlbwhForm model) throws Exception {
		XmlbwhForm modelOld = dao.getModel(model);
		boolean flag = false;
		if(!model.getLbmc().equals(modelOld.getLbmc())){
			flag = dao.isRalate(model);
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @����:�жϹ����ԣ����ݿɷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		return dao.isRalate(values);
	}
	
	
}
