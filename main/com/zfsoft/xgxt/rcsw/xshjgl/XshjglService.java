/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-31 ����04:59:02 
 */  
package com.zfsoft.xgxt.rcsw.xshjgl;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.fyff.ffjg.FyffjgForm;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2015-9-14 ����09:35:12 
 * @�汾�� V5.17
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XshjglService extends SuperServiceImpl<XshjglForm, XshjglDao> {
	private XshjglDao rd = new XshjglDao();

	public XshjglService() {
		setDao(rd);
	}
	public HashMap<String, String> getOneInfo(XshjglForm t) throws Exception {
		
		return dao.getOneInfo(t);
	}
	
	
	public boolean isExistQysj(XshjglForm form) 
	throws Exception {
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
		
	}
	/**
	 * ��������
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-10-13 ����04:04:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
public List<HashMap<String, String>> getXshjgldcList(XshjglForm t, User user) throws Exception{
		
		return dao.getXshjgldcList(t, user);
	}
}
