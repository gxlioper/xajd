/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-8 ����02:18:28 
 */  
package xsgzgl.gygl.rcjc.xswsf;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-�������-ѧ�������֣���������ְҵ����ѧԺ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-5-8 ����02:18:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XswsfService extends SuperServiceImpl<XswsfForm,XswsfDao> {
	private XswsfDao dao= new XswsfDao();
	
	public XswsfService(){
		super.setDao(dao);
	 }

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-9 ����08:55:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * XswsfForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXswsfById(XswsfForm myForm) {
		return dao.getXswsfById(myForm);
	}


	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-9 ����04:40:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getRcXnList() {
		
		return dao.getRcXnList();
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-10 ����11:42:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWmqsxsMd(String xn, String xq) {
		
		return dao.getWmqsxsMd(xn,xq);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-10 ����01:41:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsmcList
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertXsmc(List<HashMap<String, String>> xsmcList, User user) throws Exception {
		return dao.insertXsmc(xsmcList, user);
	}
	
	
}
