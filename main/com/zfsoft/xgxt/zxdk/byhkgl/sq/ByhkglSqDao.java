/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-6 ����02:04:03 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��ҵ��������  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-6 ����02:04:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglSqDao extends SuperDAOImpl<ByhkglSqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglSqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh, a.xm, a.xb, a.xymc, a.zymc, a.bjmc, a.xydm, a.zydm, a.bjdm, a.sfzh, a.SJHM, a.ZZMMMC,a.dzyx,a.xmsj sjdh, a.jtdzxx, a.MZMC, a.nj, a.XZ, ");
        sql.append(" t2.hkbj,z.zqyymc,t1.sqid,t1.hkje,t1.sfzq,t1.zqyy,t1.zqqx,t1.shzt,t1.splc,t1.sqsj,t1.bz,t1.filepath,decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc "); 
        sql.append(" from view_xsbfxx a ");
        sql.append(" left join xg_zxdk_tqhkjgb t2 on a.xh = t2.xh ");
        sql.append(" left join XG_ZXDK_HZSF_BYHKSQB t1 on a.xh = t1.xh ");
        sql.append(" left join XG_ZXDK_HZSF_ZQYYDMB z on t1.zqyy = z.zqyydm ");
        sql.append(" where exists (select 1 from xg_zxdk_xydkjgb b where a.xh = b.xh) and ");
        sql.append(Base.currNd);
        sql.append(" - nvl(a.nj, 0) >= nvl(a.xz, 0) ");
        sql.append(" and (t2.hkbj <> 'ȫ������' or t2.hkbj is null)) t where 1= 1 ");
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
		super.setClass(ByhkglSqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_ZXDK_HZSF_BYHKSQB");
	}
	
	/**
	 * 
	 * @����: չ��ԭ��LIST
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����03:00:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZqyyList() {
		String sql = "select zqyydm, zqyymc from XG_ZXDK_HZSF_ZQYYDMB order by zqyydm ";
		return dao.getList(sql, new String[] {}, new String[] {"zqyydm", "zqyymc" });
	}
	
	/**
	 * 
	 * @����: Ӧ�����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����03:50:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String yhjeCx(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select (nvl(a.dkze, 0) - nvl(b.hkje, 0)) yhje from (select sum(dkje) dkze, b.xh from xg_zxdk_xydkjgb b ");
        sql.append(" where xh = ? ");
        sql.append(" group by xh) a left join xg_zxdk_tqhkjgb b on a.xh = b.xh ");
        sql.append(" where a.xh = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { xh, xh}, "yhje");
		
		return num;		
	}
	
	/**
	 * 
	 * @����: ��ȡsplc
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-9 ����04:31:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_ZXDK_HZSF_BYHKCSSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
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
		sql.append(" select b.zqyymc zqyymc from XG_ZXDK_HZSF_BYHKSQB a left join XG_ZXDK_HZSF_ZQYYDMB b on a.zqyy = b.zqyydm where a.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[] {xh}, "zqyymc");
	}
}
