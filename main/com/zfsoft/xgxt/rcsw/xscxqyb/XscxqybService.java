/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-17 ����11:04:45 
 */  
package com.zfsoft.xgxt.rcsw.xscxqyb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-3-25 ����10:19:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
	public class XscxqybService extends SuperServiceImpl<XscxqybForm,XscxqybDao>{
		private XscxqybDao rd = new XscxqybDao();
		public XscxqybService() {
			setDao(rd);
	}
/**
 * @����:TODO(��õ�ǰѧ������)
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-25 ����10:18:48
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param model
 * @return
 * @throws Exception
 * String �������� 
 * @throws
 */
	public String getCurrentXqmc(XscxqybForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
/**
 * @����:TODO(ͬһѧ��ѧ�ڣ���ͬ�Ĳ������Ƿ����ͬ���·ݵļ�¼)
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-25 ����10:20:27
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param form
 * @return
 * @throws Exception
 * boolean �������� 
 * @throws
 */
	public boolean isExistYf(XscxqybForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;	
	}
/**
 * @����:TODO(�鿴ҳ������SQL)
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-3-25 ����10:24:50
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param jgid
 * @return
 * HashMap<String,String> �������� 
 * @throws
 */
	public HashMap<String,String> getXxck (String jgid){
		return dao.getXxck(jgid);
	}
/**
 * 
 * @����:����
 * @���ߣ�����[���ţ�982]
 * @���ڣ�2016-3-24 ����02:05:25
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param t
 * @param user
 * @return
 * @throws Exception
 * List<HashMap<String,String>> �������� 
 * @throws
 */
	public List<HashMap<String, String>> getXscxqybdcList(XscxqybForm t, User user) throws Exception{
		return dao.getXscxqybdcList(t, user);
	}
}
