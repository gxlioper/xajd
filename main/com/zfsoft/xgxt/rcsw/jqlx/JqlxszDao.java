/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����03:50:57 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ������У����
 * @���ߣ� 945
 * @ʱ�䣺 2013-12-30 ����03:50:57
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JqlxszDao extends SuperDAOImpl<JqlxszModel> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqlxszModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqlxszModel t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_jqlxsz");
		super.setClass(JqlxszModel.class);
	}

	public JqlxszModel getModel() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.fid uploadid,a.*, case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_rcsw_jqlxsz a ");
		sql.append("left join xg_comm_fileupload_data b on (a.fjid=b.gid) ");
		return super.getModel(sql.toString(), new String[] {});
	}

	public boolean deleteJqlxsz(JqlxszModel model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_rcsw_jqlxsz ";
		flag = dao.runUpdate(sql, new String[] {});
		return flag;
	}

}
