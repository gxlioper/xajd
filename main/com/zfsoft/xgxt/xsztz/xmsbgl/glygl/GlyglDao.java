/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����10:27:02 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.glygl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����10:27:02
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class GlyglDao extends SuperDAOImpl<GlyglForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GlyglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r,a.* from (select a.yhm,b.xm,b.zdm,b.szbm,c.bmmc,d.zmc from xg_sztz_glyb a ");
		sql.append("left join yhb b on a.yhm = b.yhm left join zxbz_xxbmdm c on b.szbm = c.bmdm ");
		sql.append("left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm order by yhm,zdm,szbm) a where 1=1 ");
		return getPageList(t, sql.toString(), new String[]{});
	}
	/**
	 * 
	 * @����:����Ա�б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-13 ����03:12:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getGlyList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select yhm from xg_sztz_glyb  ");
		return dao.getList(sql.toString(), new String[]{}, "yhm");
		
	}

	/**
	 * 
	 * @����:�û��б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-9 ����02:58:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(GlyglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append("select rownum r,a.* from (select b.yhm,b.xm,b.zdm,b.szbm bmdm,c.bmmc,d.zmc,c.bmlb from yhb b ");
		sql.append(" left join zxbz_xxbmdm c on b.szbm = c.bmdm left join (select distinct yhm,zmc from view_yhz_yhxxb) d on b.yhm = d.yhm where c.bmlb = '1' ");
		sql.append(" and not exists(select 1 from xg_sztz_glyb a where a.yhm = b.yhm) order by b.szbm desc)a where 1=1 ");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public boolean save(List<String[]> params) throws Exception{
		boolean flag = false;
		String sql = "insert into xg_sztz_glyb values(?,?,?)";
		int[] result = dao.runBatch(sql, params);
		flag = dao.checkBatchResult(result);
		return flag;
		
	}

	

	@Override
	public List<HashMap<String, String>> getPageList(GlyglForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(GlyglForm.class);
		super.setKey("yhm");
		super.setTableName("xg_sztz_glyb");

	}

}
