/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-14 ����10:42:02 
 */  
package xgxt.szdw.bjlh.fdykh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����Ա����ͳ��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-2-14 ����10:42:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjlhFdykhNewService extends
		SuperServiceImpl<BjlhFdykhForm, BjlhFdykhNewDao> {

	private BjlhFdykhNewDao dao = new BjlhFdykhNewDao();

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param dao
	 */
	public BjlhFdykhNewService() {
		super.setDao(dao);
	}
	
	/**
	 * ������ͳ��
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByRS(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByRS(myForm, user);
	}
	
	/**
	 * 
	 * @����: ���༶ͳ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-17 ����11:21:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByBJ(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByBJ(myForm, user);
	}
	
	/**
	 * 
	 * @����:��ְ��ͳ��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-18 ����02:08:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByZZ(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByZZ(myForm, user);
	}
	
	/**
	 * 
	 * @����:ͳ����ϸ��ѯ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-19 ����02:20:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] �������� 
	 * @throws
	 */
	public Object[] getFdyKhMx(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhMx(myForm, user);
	}
	
	/**
	 * 
	 * @����:ͳ����ϸ��ѯ�б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-20 ����09:46:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] �������� 
	 * @throws
	 */
	public Object[] getFdyKhxxMxDetail(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxMxDetail(myForm, user);
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-21 ����10:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByRSAll(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByRSAll(myForm, user);
	}

	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-21 ����10:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByBJAll(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByBJAll(myForm, user);
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-21 ����10:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByZZAll(BjlhFdykhForm myForm , User user) throws Exception{
		return dao.getFdyKhxxByZZAll(myForm, user);
	}
}
