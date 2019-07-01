package com.zfsoft.xgxt.xlzx.zxsgly;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZxsglyDao extends SuperDAOImpl<ZxsglyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZxsglyForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select a.*");
		sql.append(" from (select b.yhm zgh, b.xm, b.zdm, b.szbm bmdm, c.bmmc, d.zmc, c.bmlb");
		sql.append(" from yhb b");
		sql.append(" left join zxbz_xxbmdm c");
		sql.append(" on b.szbm = c.bmdm");
		sql.append(" left join (select distinct yhm, zmc from view_yhz_yhxxb) d");
		sql.append(" on b.yhm = d.yhm");
		sql.append(" where not exists");
		sql.append(" (select 1 from xg_xlzx_glyb a where a.zgh = b.yhm)) a");
		sql.append("  where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" ");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZxsglyForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from");
		sql.append(" (select t.zgh, t1.xm, t1.xb, t1.bmdm,t1.bmmc, t1.lxdh");
		sql.append(" from xg_xlzx_glyb t");
		sql.append(" left join view_fdyxx t1");
		sql.append(" on t.zgh = t1.zgh)");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(ZxsglyForm.class);
		this.setKey("zgh");
		this.setTableName("xg_xlzx_glyb");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �������Ա��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����04:35:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zxsform
	 * @return
	 * boolean �������� 
	 * @throws
	 */
    public boolean saveForm(List<String[]> params) throws Exception{
		String sql = "insert into xg_xlzx_glyb values(?)";
		return dao.runBatchNotCommit(sql, params);
	}

	/**
	 * @����:����ְ�����ж��Ƿ�����ѯʦ����Ա
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��8�� ����1:44:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * boolean �������� 
	 * @throws Exception  
	 */
	public boolean isZxsGly(String zgh) throws Exception {

		String sql = "SELECT count(zgh) num FROM xg_xlzx_glyb WHERE zgh = ?";
		String result = dao.getOneRs(sql.toString(), new String[]{zgh}, "num");
		return Integer.valueOf(result) > 0;
	}

}
