/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-23 ����03:03:31 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-23 ����03:03:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjJgDao extends SuperDAOImpl<XnxjJgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjJgForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		//to_date('2009-12-25 14:23:31','yyyy-mm-dd,hh24:mi:ss')
		sql.append("select t1.* ")
		.append(" from (select a.id, a.xn, a.xjnr, to_char(to_date(a.txsj , 'yyyy-MM-dd,hh24:mi:ss'),'yyyy-MM-dd') txsj , a.sjly, a.sqid, b.* ")
		.append(" from xg_xsxx_xnxjjgb a ")
		.append(" left join view_xsjbxx b ")
		.append(" on a.xh = b.xh) t1 where xh is not null ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	public int delXnxjg(String ywid) throws Exception{
		String sql = "delete from xg_xsxx_xnxjjgb where sqid = ? ";
		return dao.runDelete(sql, new String[]{ywid});
	}
	
	public XnxjJgForm getModel(String xh , String xn) throws Exception{
		String sql = "select * from xg_xsxx_xnxjjgb where xh = ? and xn = ?";
		return super.getModel(sql, new String[]{xh , xn});
	}
	
	public List<HashMap<String , String>> getAllXnxjList(String xh) throws Exception{
		String sql = "select xn , xjnr from xg_xsxx_xnxjjgb where xh = ? order by xn desc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	public List<HashMap<String , String>> getXnxjList(String xh , String xn) throws Exception{
		String sql = "select xn , xjnr from xg_xsxx_xnxjjgb where xh = ? and xn = ? order by xn desc";
		return dao.getListNotOut(sql, new String[]{xh , xn});
	}
	
	public HashMap<String , String> getXnxjJgInfo(String id) throws Exception{
		String sql = "select * from xg_xsxx_xnxjjgb where id = ?";
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(XnxjJgForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_xnxjjgb");
	}

}
