/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-11 ����10:07:32 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��ҵ������  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-11 ����10:07:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglJgDao extends SuperDAOImpl<ByhkglJgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglJgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*, ");
        sql.append(" t2.xm, t2.xb, t2.xymc, t2.zymc, t2.bjmc, t2.xydm, t2.zydm, t2.bjdm, t2.sfzh, t2.SJHM, t2.ZZMMMC, t2.jtdzxx, t2.MZMC, t2.nj, t2.XZ,t2.dzyx,t2.xmsj sjdh, ");
        sql.append(" t3.zqyymc ");
        sql.append(" from XG_ZXDK_HZSF_BYHKJGB t1 ");
        sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
        sql.append(" left join XG_ZXDK_HZSF_ZQYYDMB t3 on t1.zqyy = t3.zqyydm ");       
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(ByhkglJgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_ZXDK_HZSF_BYHKJGB");
	}
	
	/**
	 * 
	 * @����: Ψһ���ж�
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����03:31:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjg(ByhkglJgForm form) {
		String sql = "select count(1) num from XG_ZXDK_HZSF_BYHKJGB where xh = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh()}, "num");
		return Integer.valueOf(num)>0;	
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����03:34:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteForSq(ByhkglJgForm form) {
		String sql = "delete from XG_ZXDK_HZSF_BYHKJGB where xh = ?";
		try {
			return dao.runUpdate(sql, new String[]{form.getXh()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-11 ����03:36:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lclyywid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delByLclyywid(String lclyywid){
		String sql = "delete from XG_ZXDK_HZSF_BYHKJGB where lclyywid = ?";
		try {
			return dao.runUpdate(sql, new String[]{lclyywid});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @����: ��ȡzqyymc
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-10 ����01:43:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZqyymc(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.zqyymc zqyymc from XG_ZXDK_HZSF_BYHKJGB a left join XG_ZXDK_HZSF_ZQYYDMB b on a.zqyy = b.zqyydm where a.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[] {xh}, "zqyymc");
	}
}
