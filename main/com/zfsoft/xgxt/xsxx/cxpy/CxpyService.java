/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����05:04:49 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�����������) 
 * @���ߣ� CMJ [���ţ�913]
 * @ʱ�䣺 2013-7-24 ����05:04:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpyService extends SuperServiceImpl<CxpyForm, CxpyDao> {

	private CxpyDao dao = new CxpyDao();
	
	@SuppressWarnings("deprecation")
	public CxpyService(){
		super.setDao(dao);
	}

	/** 
	 * @����:TODO(��ѯ���еȼ�list)
	 * @���ߣ�CMJ [���ţ�913]
	 * @���ڣ�2013-7-25 ����03:30:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCxdjList() {
		// TODO �Զ����ɷ������
		
		return dao.getCxdjList();
	}

	/** 
	 * @����:TODO(��ȡѧ��)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-25 ����05:02:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(CxpyForm model, User user) throws Exception{
		// TODO �Զ����ɷ������
		return dao.getXsPageList(model, user);
	}

	/** 
	 * @����:TODO(��ȡѡ���ѧ��list)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-25 ����07:13:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXzxsList(String xhs) {
		// TODO �Զ����ɷ������
		return dao.getXzxsList(xhs);
	}

	/**
	 * @throws SQLException  
	 * @����:TODO(�������������Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-7-26 ����10:47:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public boolean save(CxpyForm model) throws SQLException {
		// TODO �Զ����ɷ������
		return dao.save(model);
	}
	
	public String getCount(CxpyForm t){
		
		return dao.getCount(t);
	}
	
	public HashMap<String,String> getCxpyByXhXnXq(String xh,String xn,String xq){
		return dao.getCxpyByXhXnXq(xh, xn, xq);
	}
	
	public HashMap<String,String> getCxpyByXhXn(String xh,String xn){
		return dao.getCxpyByXhXn(xh, xn);
	}
	public HashMap<String,String> getCxpyForXzyy(String xh,String xn,String xq){
		return dao.getCxpyForXzyy(xh, xn, xq);
	}
}
