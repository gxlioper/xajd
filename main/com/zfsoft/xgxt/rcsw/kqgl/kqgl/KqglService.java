/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-31 ����02:45:02 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class KqglService extends SuperServiceImpl<KqglForm, KqglDAO> {
	
	private KqglDAO dao = new KqglDAO();
	
	@SuppressWarnings("deprecation")
	public KqglService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:��֤���ڽ���Ƿ����
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-8-31 ����04:42:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isKqjgExists(KqglForm model){
		return dao.isKqjgExists(model);
	}
	
	/**
	 * 
	 * @����:��ȡList
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-8-31 ����04:45:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tablename
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getList(String tablename){
		return dao.getList(tablename);
	}
}
