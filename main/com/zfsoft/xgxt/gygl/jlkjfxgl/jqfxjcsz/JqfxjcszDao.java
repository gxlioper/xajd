/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-19 ����10:14:34 
 */
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(���ڷ�У) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-2-25 ����01:55:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JqfxjcszDao extends SuperDAOImpl<JqfxjcszForm> {
		
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JqfxjcszForm t)
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
	public List<HashMap<String, String>> getPageList(JqfxjcszForm t, User user)
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
		super.setTableName("xg_gygl_new_fxgl_csszb");
		super.setClass(JqfxjcszForm.class);
	}

	/**
	 * 
	 * @����:TODO(��ѯ���ڷ�У��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-25 ����01:54:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * JqfxjcszForm �������� 
	 * @throws
	 */
	public JqfxjcszForm getModel() throws Exception {

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*, case when fxkg=1 and sysdate between to_date(nvl(fxtxkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql
				.append("and to_date(nvl(fxtxzzsj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_gygl_new_fxgl_csszb a ");

		return super.getModel(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @����:TODO(ɾ�����ڷ�У�������ñ�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-25 ����01:53:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteXszbbJcsz(JqfxjcszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_gygl_new_fxgl_csszb ";
		flag = dao.runUpdate(sql, new String[] {});
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ��У����ͷ�У����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-11 ����09:42:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getfxmcList() {
		String sql = "select fxdm,fxmc from xg_gygl_new_fxgl_dmwhb order by fxdm";
		return dao.getList(sql, new String[] {}, new String[] { "fxdm", "fxmc" });
	}

}
