/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-23 ����11:46:58 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_�༶�±�����ģ��
 * @�๦������: TODO(������ҽҩ_�༶�±�_��������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-23 ����11:46:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybcsszDao extends SuperDAOImpl<BjxqybcsszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybcsszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybcsszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_bjzyy_xqyb_bjyb_cssz");//������ҽҩ_�༶�±�_��������
		super.setClass(BjxqybcsszForm.class);
	}
	
	/**
	 * 
	 * @����:TODO(ѧ���±�����-�༶�±�����-��������-��ѯ����������Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����01:10:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * BjxqybcsszForm �������� 
	 * @throws
	 */
	public BjxqybcsszForm getModel() throws Exception {
	
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_bjzyy_xqyb_bjyb_cssz a ");
		return super.getModel(sql.toString(), new String[]{});
		
	}
	
	/**
	 * 
	 * @����:TODO(ѧ���±�����-�༶�±�����-��������-����һ�����ݵ�ͬʱɾ��ԭ�ȵ�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-23 ����05:01:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteBjxqybcssz(BjxqybcsszForm t) throws Exception {
		
		boolean flag = false;
		String sql = " delete from xg_bjzyy_xqyb_bjyb_cssz ";
		flag = dao.runUpdateTab(sql);		
		return flag;
		
	}
}
