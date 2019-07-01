/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-4 ����02:27:43 
 */
package xsgzgl.qgzx.xsgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: �ڹ���ѧѧ��ά��
 * @���ߣ� �ո־� [1075]
 * @ʱ�䣺 2014-7-4 ����02:27:43 
 * @�汾�� V5.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QgzxXsglDao extends SuperDAOImpl<QgzxXsglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QgzxXsglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ѧ�������ѯ
	 */
	public List<HashMap<String, String>> getPageList(QgzxXsglForm t, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.* from (select b.*,b.sydm1 sydm,b.symc1 symc,case when a.sfgmbx = '0' then '��' else '��' end sfgmbx from xg_qgzx_qgzxxsb a join view_xsjbxx b on a.xh = b.xh ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by nj,xymc,zymc,bjmc desc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	protected void setTableInfo() {
		super.setKey("xh");
		super.setTableName("xg_qgzx_qgzxxsb");
	}
	
	/**
	 * 
	 * @����:��ȡѧ��
	 * @���ߣ��ո־� [1075]
	 * @���ڣ�2014-7-7 ����04:30:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsPageList(QgzxXsglForm model, User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.*,a.sydm1 sydm,a.symc1 symc from view_xsjbxx a where not exists (select 1 from xg_qgzx_qgzxxsb c where a.xh = c.xh ) ");
		sql.append("order by a.nj, a.xydm, a.zydm, a.bjdm) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by nj,xymc,zymc,bjmc desc");
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:����ڹ���ѧѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-30 ����02:14:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQgzxXs(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		return dao.runInsert("xg_qgzx_qgzxxsb", new String[]{"xh"}, new String[]{xh});
	}

	/**
	 * @��������:ά����������Ϣ
	 * @auther: ������[1692]
	 */
	public boolean updateSfgmbx(QgzxXsglForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_qgzx_qgzxxsb set sfgmbx = ? where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{model.getSfgmbx(),model.getXh()});
	}

	public HashMap<String, String> getDetail(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_qgzx_qgzxxsb where xh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}

	public boolean plUpdate(String xh) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_qgzx_qgzxxsb set sfgmbx = '1' where xh = ?");
		return dao.runUpdate(sql.toString(),new String[]{xh});
	}

	public List<HashMap<String,String>> getQgryAllList(QgzxXsglForm t, User user)throws Exception {
		//���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		//Ȩ�޿���
		sql.append("select t1.* from (select b.*,b.sydm1 sydm,b.symc1 symc,case when a.sfgmbx = '0' then '��' else '��' end sfgmbx from xg_qgzx_qgzxxsb a join view_xsjbxx b on a.xh = b.xh ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by nj,xymc,zymc,bjmc desc");
		return getPageList(t, sql.toString(), inputValue);
	}
}
