package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Żݿ������������ģ��
 * @�๦������: TODO(���Żݿ������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-16 ����02:55:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcyhkJcszDao extends SuperDAOImpl<HcyhkJcszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HcyhkJcszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HcyhkJcszForm t, User user)
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
		super.setTableName("xg_rcsw_hcyhk_jcszb");
		super.setClass(HcyhkJcszForm.class);
	}

	/**
	 * ��ѯ����������Ϣ
	 */
	public HcyhkJcszForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcsw_hcyhk_jcszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * ɾ������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteXszbbJcsz(HcyhkJcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_rcsw_hcyhk_jcszb ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	
}
