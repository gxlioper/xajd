/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-31 ����09:36:52 
 */  
package com.zfsoft.xgxt.xsxx.yrgl;



import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
	/**
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: XXXX����ģ��
	 * @�๦������: TODO(������һ�仰��������������) 
	 * @���ߣ� ����[����:1186]
	 * @ʱ�䣺 2016-1-5 ����09:46:24 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */
	public class YrglService extends SuperServiceImpl<YrglForm, YrglDao> {
		private YrglDao rd = new YrglDao();
	public YrglService() {
		setDao(rd);
	}
	//������ѯ
	public HashMap<String, String> getOneInfo(YrglForm t) throws Exception {
		return dao.getOneInfo(t);
	}
	public boolean isExistQysj(YrglForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;	
	}
	/**
	 * @����: ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-1-5 ����10:00:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXshjgldcList(YrglForm t, User user) throws Exception{	
			return dao.getXshjgldcList(t, user);
		}
	public HashMap<String, String> getXn(String xh) throws Exception {
		return dao.getXn(xh);
	}

}
