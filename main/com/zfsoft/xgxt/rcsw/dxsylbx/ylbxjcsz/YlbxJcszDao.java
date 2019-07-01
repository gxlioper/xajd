package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ��ҽ�Ʊ��ղ���������Ϣģ��
 * @�๦������: TODO(ѧ��֤�����������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-16 ����02:55:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxJcszDao extends SuperDAOImpl<YlbxJcszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxJcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxJcszForm t, User user)
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
		super.setTableName("xg_rcsw_ylbx_jcszb");
		super.setClass(YlbxJcszForm.class);
	}

	/**
	 * 
	 * @����:TODO(��ѯ����������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����10:15:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * YlbxJcszForm �������� 
	 * @throws
	 */
	public YlbxJcszForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcsw_ylbx_jcszb a ");
		
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:TODO(ɾ������������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����10:16:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteXszbbJcsz(YlbxJcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_rcsw_ylbx_jcszb ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
