/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-21 ����05:30:53 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ��ɫͨ��
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-21 ����05:30:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdJcszDao extends SuperDAOImpl<LstdJcszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdJcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdJcszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
		super.setTableName("xg_rcsw_lstd_jcsz");
		super.setClass(LstdJcszForm.class);

	}
	
	
	public LstdJcszForm getModel() throws Exception {
		
		StringBuilder sql = new StringBuilder(" select * from xg_rcsw_lstd_jcsz ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:TODO(ɾ������������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:21:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteLstdJcsz(LstdJcszForm model) throws Exception {
		
		boolean flag = false;
		String sql = " delete from xg_rcsw_lstd_jcsz ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}

}
