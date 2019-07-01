package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XssqDao extends SuperDAOImpl<XssqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XssqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select t2.* from (select t1.*,decode(t1.sbcount, '0', '��', '��') ywsbmc");
		sql.append(" from (select a.xh as pk,a.rzksrq,a.rzjsrq,a.sfxypssb,");
		sql.append(" decode(a.sfxypssb,'1', '��', '0', '��') sfxypssbmc,");
		sql.append(" b.*,(select count(1) from XG_XLJK_new_XLWYGL_XLSBJGB aa  where aa.xh = a.xh");
		sql.append("  ) sbcount");
		sql.append(" from XG_XLJK_XLWYGL_NEW_XSSQXXB a");
		sql.append(" left join view_xsjbxx b");
		sql.append(" on a.xh = b.xh");
		sql.append(" where 1 = 1");
		sql.append(" ) t1) t2  where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" )order by xh asc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setKey("xh");
		this.setClass(XssqForm.class);
		this.setTableName("XG_XLJK_XLWYGL_NEW_XSSQXXB");
	}
	
	/**
	 * 
	 * @����:��֤�Ƿ��Ѵ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-10 ����05:18:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int checkExist(String xh) throws SQLException{
		String sql = "select count(1) rs from XG_XLJK_XLWYGL_NEW_XSSQXXB a where a.xh = ? ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh}, "rs"));
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-11 ����04:59:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.* from (select a.xh as pk,a.rzksrq,a.rzjsrq,a.sfxypssb,decode(a.sfxypssb, '1', '��', '0', '��') sfxypssbmc,b.*");
		sql.append(" from XG_XLJK_XLWYGL_NEW_XSSQXXB a left join view_xsjbxx b on a.xh = b.xh) t1  where t1.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
}
