/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:28:57 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ��DAO
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:28:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwwhDAO extends SuperDAOImpl<ZwwhForm> {
	
	@Override
	protected void setTableInfo() {
		super.setKey("zwid");
		super.setTableName("xg_szdw_xsgb_zwb");
		super.setClass(ZwwhForm.class);
	}
	@Override
	public List<HashMap<String, String>> getPageList(ZwwhForm model)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append("  select * from (select a.*,b.lxmc from xg_szdw_xsgb_zwb a left join xg_szdw_xsgb_zwlxb b on a.lxdm = b.lxdm  where 1=1 order by zwzz) ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZwwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 * @����:��ȡְ���б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����3:30:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getZwList(String zwid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zwid,t.zwmc from xg_szdw_xsgb_zwb t ");
		if(!StringUtil.isNull(zwid)){
			sql.append("  where t.lxdm = '"+zwid+"'"); 
		}
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"zwid","zwmc"});
	}
	/**
	 * @����:����ְ�����Ͳ�ѯְ������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-12 ����6:20:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxdm
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public int getZwCount(String lxdm) throws SQLException{
		String sql = " select count(1) from xg_szdw_xsgb_zwb e where e.lxdm in( "+lxdm+")";
		return dao.getOneRsint(sql);
	}
}
