/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-4 ����02:27:43 
 */  
package xsgzgl.qgzx.xsgl;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xgxt.utils.Pages;

import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: �ڹ���ѧѧ��ά��
 * @���ߣ� �ո־� [1075]
 * @ʱ�䣺 2014-7-4 ����02:27:43 
 * @�汾�� V5.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QgzxXsglService extends SuperServiceImpl<QgzxXsglForm, QgzxXsglDao> {
	
	private QgzxXsglDao dao = new QgzxXsglDao();

	/**
	 * 
	 * @����:��ȡѧ��
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2014-7-7 ����04:33:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsPageList(QgzxXsglForm model, User user) throws Exception{
		
		return dao.getXsPageList(model, user);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:����ڹ���ѧѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-30 ����02:13:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQgzxXs(String xh) throws Exception{
		return dao.saveQgzxXs(xh);
	}

	/**
	 * @��������:ά���Ƿ�����
	 * @auther: ������[1692]
	 */
	public boolean updateSfgmbx(QgzxXsglForm model) throws Exception{
		return dao.updateSfgmbx(model);
	}

	public HashMap<String, String> getDetail(String xh) {
		return dao.getDetail(xh);
	}

	public boolean plUpdate(String xh) throws Exception {
		return dao.plUpdate(xh);
	}

	public List<HashMap<String,String>> getQgryAllList(QgzxXsglForm t, User user)
		throws Exception {
			Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
			pages.setPageSize(Integer.MAX_VALUE);

			t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getQgryAllList(t,user);
	}
}
