/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-10 ����04:56:46 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-2-10 ����04:56:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcshDAO extends SuperDAOImpl<ZcshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select *");
		sql.append(" from (select t.*,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XB,");
		sql.append(" floor(months_between(SYSDATE, to_date(t1.csrq,'yyyy-mm-dd'))/ 12) nl,");
		sql.append(" case t1.xz when '3' then '��ѧר��' else '��ѧ����' end xlmc,");
		sql.append(" t1.sfzh,");
		sql.append(" t1.sjhm,");
		sql.append(" t1.mzmc,");
		sql.append(" t1.XM,");
		sql.append(" t1.csrq,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,");
		sql.append(" t2.jc,");
		sql.append(" t3.dzbmc,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.shzt shztx,");
		sql.append(" t5.gwid,");
		sql.append(" t5.shr,");
		sql.append(" t5.shyj,");
		sql.append(" t7.mc || '[' ||");
		sql.append(" decode(t5.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,");
		sql.append(" t7.gwz,");
		sql.append(" row_number() over(partition by t.sqid order by t5.shsj desc) rn");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcsqb t");
		sql.append(" left join VIEW_XSBFXX t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join zzmmdmb t2");
		sql.append(" on t1.ZZMM = t2.zzmmdm");
		sql.append(" left join xg_dtjs_zzgxzc_dzbdmb t3");
		sql.append(" on t.szdzb = t3.dzbdm");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.sqid = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on  t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7 on t5.gwid = t7.id ");
		sql.append(" where t6.spyh ='" + user.getUserName() + "'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
		}
		sql.append(" ) t");
		sql.append(" where 1 = 1 and  rn = 1");
		sql.append(" ");
		sql.append(searchTjByUser);
	    sql.append(searchTj);
	    sql.append(shgwzByUser);
	    return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setKey("sqid");
		this.setTableName("XG_DTJS_ZZGXZC_ZZGXZCSQB");
		this.setClass(ZcshForm.class);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���浽�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����11:57:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zcsh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveZcJg(ZcshForm zcsh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_dtjs_zzgxzc_zzgxzcjgb");
		sql.append(" (jgid,");
		sql.append(" xh,");
		sql.append(" szdzb,");
		sql.append(" sfsn,");
		sql.append(" jsdzz,");
		sql.append(" sqdw,");
		sql.append(" dfjzrq,");
		sql.append(" sfkjhyzm,");
		sql.append(" jsxbh,");
		sql.append(" sjly,");
		sql.append(" lclyid,");
		sql.append(" sqr,");
		sql.append(" sqsj) ");
		sql.append(" values(?,?,?,?,?,?,?,(SELECT sfkjhyzm FROM xg_dtjs_zzgxzc_zzgxzcsqb WHERE sqid = ?),?,?,?,?,?)");
		sql.append(" ");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{zcsh.getSqid(),zcsh.getXh(),zcsh.getSzdzb(),zcsh.getSfsn(),
			zcsh.getJsdzz(),zcsh.getSqdw(),zcsh.getDfjzrq(),zcsh.getSqid(),zcsh.getJsxbh(),"1",zcsh.getSqid(),zcsh.getSqr(),zcsh.getSqsj()});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����02:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJg(String jgid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_DTJS_ZZGXZC_ZZGXZCJGB where jgid = ?");
		return dao.runUpdate(sql.toString(),new String[]{jgid});
	}
	
	/**
	 *
	 * @����: �Ƿ���ڽ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����02:23:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isNotExistInjgb(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_DTJS_ZZGXZC_ZZGXZCJGB where xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����02:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgByXh(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_DTJS_ZZGXZC_ZZGXZCJGB where xh = ?");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{xh});
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����02:46:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isNotJsxInJgb(String jsxbh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_dtjs_zzgxzc_zzgxzcjgb t where t.jsxbh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{jsxbh}, "num");
		return "0".equals(num) ? true :false;
	}

	/** 
	 * @����:������������id����ȡ�������̸�λ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��14�� ����10:48:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSplcgwList(String splcid) {
		String sql="select * from xg_xtwh_spbz where splc=? order by xh ";
		return  dao.getListNotOut(sql, new String[]{splcid});
	}
}
