/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����03:41:05 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����03:41:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JqfxJbszDao  extends SuperDAOImpl<JqfxJbszForm> {
		
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqfxJbszForm t)
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
	public List<HashMap<String, String>> getPageList(JqfxJbszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("XG_RCSW_JQFXGL_JBSZB");
		super.setClass(JqfxJbszForm.class);
	}

	/**
	 * 
	 * @����:TODO(��ѯ���ڷ�У��������)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:41:05 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * JqfxJbszForm �������� 
	 * @throws
	 */
	public JqfxJbszForm getModel() throws Exception {

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*, case when fxkg=1 and sysdate between to_date(nvl(fxtxkssj,'1990-01-01 00:00'),'yyyy-MM-dd hh24:mi') ");
		sql
				.append("and to_date(nvl(fxtxzzsj,'2030-01-01 23:59'),'yyyy-MM-dd hh24:mi') then 'true' else 'false' end isopen from XG_RCSW_JQFXGL_JBSZB a ");

		return super.getModel(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����:TODO(ɾ�����ڷ�У�������ñ�)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:41:05 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteXszbbJcsz(JqfxJbszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from XG_RCSW_JQFXGL_JBSZB ";
		flag = dao.runUpdate(sql, new String[] {});
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ��У����ͷ�У����)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:41:05 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getfxmcList() {
		String sql = "select fxdm,fxmc from XG_RCSW_JQFXGL_FXLBB order by fxdm";
		return dao.getList(sql, new String[] {}, new String[] { "fxdm", "fxmc" });
	}

}

