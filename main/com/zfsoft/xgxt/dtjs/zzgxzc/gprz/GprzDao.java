/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��2��14�� ����3:34:11 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-��֯��ϵת��-������־ģ��
 * @�๦������: Dao
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��2��14�� ����3:34:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GprzDao extends SuperDAOImpl<GprzForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_dtjs_zzgxzc_zprzb");
		super.setKey("id");
		super.setClass(GprzForm.class);
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GprzForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GprzForm t, User user) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT  t1.*,t2.xm,t2.nj,t2.xymc,t2.zymc,t2.bjmc,t2.zzmmmc");
		sql.append(" FROM xg_dtjs_zzgxzc_zprzb t1  ");
		sql.append(" LEFT JOIN VIEW_XSJBXX t2 ON t1.xh=t2.xh) t WHERE 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws SQLException  
	 * @����:�������������־
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��15�� ����5:34:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gprzFormList
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveGprzFormList(List<GprzForm> gprzFormList) throws SQLException {
		String sql = "insert into xg_dtjs_zzgxzc_zprzb (xh,xgsj,xgr,xgqjl) values(?,?,?,?)";
		List<String[]> paramList = new ArrayList<String[]>();
		
		for(GprzForm gprzForm:gprzFormList){
			String [] param = {gprzForm.getXh(),gprzForm.getXgsj(),gprzForm.getXgr(),gprzForm.getXgqjl()};
			paramList.add(param);
		}
		int [] results = dao.runBatch(sql, paramList);
		
		return results.length>0;
	}


}
