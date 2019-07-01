/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-26 ����01:58:05 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-6-26 ����01:58:05
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXmShDao extends SuperDAOImplExtend<TxhdXmShForm> {
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmShForm t)
			throws Exception {
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmShForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * ");
		sql.append(" from (select t2.xm,t2.xymc,t2.xydm,t2.bjmc,t2.bjdm,t2.xb,t1.sqid,t1.xh,t1.sqr,t1.sqsj,t1.sqly,t1.splc,t1.xmdm,t1.xn,t1.xq,f.*,d.shzt,");
		sql.append(" t3.xmmc,");
		sql.append(" d.gwid, ");
		sql.append("  d.shr, ");
		sql.append("  d.shyj, ");
		sql.append(" d.guid shid, ");
		sql
				.append(" row_number() over(partition by t1.sqid order by d.shsj desc) rn ");
		sql.append(" from xg_rcsw_txhd_xmsq t1 ");
		sql.append("  left join xg_xtwh_shztb d ");
		sql.append("  on t1.sqid = d.ywid ");
		//ѧ����Ϣ
		sql.append(" left join view_xsxxb t2");
		sql.append(" on t1.xh = t2.xh");
		
		sql.append("  left join xg_xtwh_spgwyh e ");
		sql.append("   on d.gwid = e.spgw ");
		sql.append(" left join xg_xtwh_spgw f ");
		sql.append("    on d.gwid = f.id  left join xg_rcsw_txhd_xmwh t3 on t1.xmdm = t3.xmdm ");
		sql.append(" where e.spyh = '" + user.getUserName()
				+ "' and t1.shzt<>9 and d.shzt<>9 ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		} else {
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}
		sql.append(" and t3.shkg = '1' and (sysdate between to_date(nvl(shkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and ");
		sql.append(" to_date(nvl(shjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')) ");
		sql.append(" ) a where rn = 1 )a where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ��ѧ��Ŀ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-6-26 ����04:58:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTxXmxx(TxhdXmShForm t){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_rcsw_txhd_xmwh t where t.xmdm=?");
		return dao.getListNotOut(sql.toString(),new String[]{t.getXmdm()});
	}

	@Override
	protected void setTableInfo() {
		this.setKey("sqid");
		this.setTableName("xg_rcsw_txhd_xmsq");
		this.setClass(TxhdXmShForm.class);
	}
	
	
	/**
	 * 
	 * @����: ������Ŀ���롢��λID��ȡͨ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����06:00:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param gwid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getTgrsByXmdm(String xmdm, String gwid)throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from ( ");
		sql.append("select t1.xmdm,t2.gwid,t2.shzt, ");
		sql.append("row_number() over(partition by t1.sqid, t2.gwid order by t2.shsj desc) lvl from xg_rcsw_txhd_xmsq t1 ");
		sql.append(" left join xg_xtwh_shztb t2 on t1.sqid=t2.ywid ) where lvl = 1 and shzt = '1' and xmdm =? and gwid = ?");
		
		return dao.getOneRs(sql.toString(), new String[]{xmdm,gwid}, "count");
	}
	
}
