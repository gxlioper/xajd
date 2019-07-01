/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.jdsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��-�������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class JdszService extends
		SuperServiceImpl<JdszModel, JdszDao> {

	private JdszDao dao = new JdszDao();

	public JdszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean jdsz(String xmdm,String xmdms) throws Exception{
		return dao.runJdsz(xmdm,xmdms);
	}
	
	/**
	 * 
	 * @����:����xmdm��ѯ���õļ�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn, String currXq) throws Exception{
		return dao.getByXmdm(xmdm,currXn, currXq);		
	}
	
	
	/**
	 * 
	 * @����:������Ŀ���룬��ȡ�����õĲ��ɼ����Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjdxm(String xmdm) throws Exception{
		return dao.getBjdxm(xmdm);
	}
	/**
	 * 
	 * @����:��ȡ�Ǽ��Ǽ�
	 * @���ߣ�caopei[���ţ�1352]
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllXj() {
		List<HashMap<String, String>> list = dao.getAllXj();
		return list;
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����:������Ŀ���룬���ز��ɼ��������Ľ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-3-3 ����10:36:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJdysq(User user,String xmdm) throws Exception {
		
		return dao.getJdysq(user,xmdm);
	}
}
