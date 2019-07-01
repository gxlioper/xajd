/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-3-21 ����09:19:30 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_��������_��������
 * @���ߣ�  Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-21 ����09:19:30 
 * @�汾��V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszDao extends SuperDAOImpl<CsszForm>{
	/*
    	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(CsszForm.class);
		setTableName("xg_zjdx_pjpy_csszb");
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		return null;
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user) throws Exception {
		/*����ҳ*/
		t.getPages().setPageSize(Integer.MAX_VALUE);
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.xmdm,a.xn,RPAD(' ', 2 * (LEVEL - 1)) || a.xmmc xmmc,a.fjdm,px, ");
		sql.append(" case when xmlx = '0' then result when xmlx = '1' then zxfz || '-' || zdfz end result, ");
		sql.append(" decode(a.xmlx,'0','�ȼ�','1','��ֵ',a.xmlx)xmlx ");
		sql.append(" from xg_zjdx_pjpy_zcxmb a ");
		sql.append(" left join (select xmdm, max(result) result ");
		sql.append(" from (select xmdm,mc, wm_concat(mc) over(partition by xmdm order by px) result ");
		sql.append(" from xg_zjdx_pjpy_zcxmxxb t) s ");
		sql.append(" group by xmdm) b ");
		sql.append(" on a.xmdm = b.xmdm ");
		sql.append(" START WITH a.fjdm = 'top' ");
		sql.append(" CONNECT BY PRIOR a.xmdm = a.fjdm ");
		sql.append(" ORDER SIBLINGS BY to_number(px) ");
		sql.append(" )t where xn = '"+t.getXn()+"' ");
		return getPageList(t, sql.toString(), new String[]{});
	}
	
	/**
	 * @����: �����������ڣ�����ѧ�꣩
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-24 ����09:13:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updatePjzq(String[] xn) throws Exception{
		String sql = " update xg_zjdx_pjpy_csszb set xn = ? where rownum = 1";
		return dao.runUpdate(sql, xn);
	}
	
	/**
	 * @����: ����������ֹʱ����������أ����忴JS����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-24 ����09:14:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateCssz(String key,String value) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_zjdx_pjpy_csszb set ");
		sql.append(key);
		sql.append(" = ? where rownum = 1 ");
		return dao.runUpdate(sql.toString(), new String[]{value});
	}
	
	/**
	 * @����: ��ѯ����������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-21 ����04:34:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * CsszForm �������� 
	 * @throws
	 */
	public CsszForm getModel() throws Exception{
		String sql = " select * from xg_zjdx_pjpy_csszb where rownum = 1 ";
		return getModel(sql, new String[]{});
	}
	
	/*
	 * ɾ������__������Ŀ����ɾ���۲���Ŀ������
	 */
	public int numDj(String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjdx_pjpy_zcxmxxb where ( ");
		for (int i = 0, n = values.length; i < n; i++) {
			sql.append(" xmdm = ? ");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/*
	 * ɾ������__������Ŀ����ɾ���۲���Ŀѡ�������
	 */
	public int numFz(String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zjdx_pjpy_zcxmb where ( ");
		for (int i = 0, n = values.length; i < n; i++) {
			sql.append(" xmdm = ? ");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}

	/*
	 * ���Ӽ��飬ͬһ����Ŀ���ơ�ѧ�겻���ظ�
	 */
	public String isExistByZcxm(CsszForm model) {
		String sql = "select count(1) cont from xg_zjdx_pjpy_zcxmb where xn = ? and xmmc = ? ";
		return dao.getOneRs(sql, new String[]{model.getXn(),model.getXmmc()}, "cont");
	}
	
	/*
	 * �޸ķ����۲���Ŀ(����ȼ�)checkBox����
	 */
	public List<HashMap<String, String>> getZcxmDjList(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zjdx_pjpy_zcxmxxb where xmdm = ? order by to_number(px) asc");
		return dao.getListNotOut(sql.toString(), new String[] { xmdm });
	}

	/*
	 * �޸ķ���ҳ��__�����۲���Ŀ�����ݣ����ص�model
	 */
	public HashMap<String, String> getZcxmDate(String xmdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_zjdx_pjpy_zcxmb where xmdm = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{xmdm});
	}
	
	/*
	 * ѡ������__�жϵ�ǰ�����Ƿ����۲��¼
	 */
	public boolean getSfcz(){
		String sql = "select count(1) num " +
					 "  from xg_zjdx_pjpy_cpmdb a " +
					 " where xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) " +
					 "   and exists (select 1 " +
					 "          from xg_zjdx_pjpy_zcfsb b " +
					 "         where a.xh = b.xh " +
					 "           and a.xn = b.xn " +
					 "           and fs is not null) ";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{}, "num")) > 0;
	}
	
	/*��������Ա��ִ�г�ʼ������*/
	public boolean initDel() throws Exception {
		String sql = "delete from xg_zjdx_pjpy_cpmdb where xn = (select xn from xg_zjdx_pjpy_csszb where rownum = 1) ";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/*��������Ա��ִ�г�ʼ������*/
	public boolean init() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_zjdx_pjpy_cpmdb (xn, xh, bjdm) ");
		sql.append("select (select xn from xg_zjdx_pjpy_csszb where rownum = 1) xn, xh, bjdm from view_xsjbxx where nj >= '2017'");
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/*��ѯ�۲���Ŀ�б�*/
	public List<HashMap<String,String>> getZcxmList(String xn){
		String sql = "select xmdm,xn,xmmc,fjdm,xmlx,px,zxfz,zdfz from xg_zjdx_pjpy_zcxmb where xn = ?";
		return dao.getListNotOut(sql, new String[]{xn});
	}
	
	/*��ѯϵͳ�е��۲���Ŀ��*/
	public int getZcxmCount() throws SQLException{
		String sql = "select count(1) num from xg_zjdx_pjpy_zcxmb";
		return dao.getOneRsint(sql);
	}
	
	/*��ѯ������ڵ��۲���Ŀ��  ����*/
	public List<HashMap<String,String>> getZjzqZcxm(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zjdx_pjpy_zcxmb where substr(xn,6,9)=(select max(substr(xn,6,9)) from xg_zjdx_pjpy_zcxmb)");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/*�����۲���Ŀ��*/
	public boolean initZcxmList(List<String[]> params) throws SQLException{
		String sql = "insert into xg_zjdx_pjpy_zcxmb(xmdm,xn,xmmc,fjdm,xmlx,px,zxfz,zdfz) values (?,?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/*��ѯ�۲���Ŀ���е��������*/
	public String maxZczq() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct xn maxXn from xg_zjdx_pjpy_zcxmb where substr(xn,6,9)=(select max(substr(xn,6,9)) from xg_zjdx_pjpy_zcxmb)");
		return dao.getOneRs(sb.toString(), new String[] {}, "maxXn");
	}
	
	/*�����۲���Ŀѡ���*/
	public boolean initZcxmxxList(String xn,String maxZczq) throws Exception{
		String sql = "insert into xg_zjdx_pjpy_zcxmxxb(xmdm,mc,px)" +
				" select (select xmdm from xg_zjdx_pjpy_zcxmb c where a.xmmc=c.xmmc and xn = ?) xmdm,b.mc,b.px " +
				" from xg_zjdx_pjpy_zcxmb a left join xg_zjdx_pjpy_zcxmxxb b on a.xmdm = b.xmdm where a.xn = ? and b.xmdm is not null";
		String [] input = new String[]{xn,maxZczq};
		return dao.runUpdate(sql, input);
	}
	
	/*�жϵ�ǰ�������Ƿ��з����ύ��¼������*/
	public boolean isHaveFstjjl(String xn){
		String sql = "select count(*)num from xg_zjdx_pjpy_fstjjlb where xn = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xn}, "num")) > 0;
	}
	
	/*ɾ�������ύ��¼�����ݣ�ʵ��Ҳû��Ҫ����ɾ��������*/
	public boolean delFsTjjl(String xn,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zjdx_pjpy_fstjjlb t1 where t1.xn = ? ");
		return dao.runUpdate(sql.toString(), new String[]{xn});
	}
	
	/*
	 * ��ʼ�������ύ��¼�����ݣ�����Ҫ���۲���Ŀ��������ȥ
	 * Ϊ���۲�ά���Ǳ߷�����ʾ
	 * �������Ŀ���룬��������Ϊtop��
	 */
	public boolean insertFstjjl(String xn,User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_zjdx_pjpy_fstjjlb(xn,xydm,xmdm) ");
		sql.append("select distinct ? xczq, t1.xydm,t2.xmdm from view_njxyzybj_all t1,(select * from view_xg_zjdx_pjpy_cssz t2 where fjdm = 'top' and xn = ?)t2 ");
		return dao.runUpdate(sql.toString(), new String[]{xn,xn});
	}
	
	/**
	 * @����: ��ѯ���ñ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����11:22:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsz(String csdm) {
		String sql = "select csz from xg_pjpy_new_cspzb where csdm = ? ";
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
	}
	
	/**
	 * @����: ����۲���Ŀ�Ƿ�ʹ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-7-24 ����11:11:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkZcxmMade(String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) count from xg_zjdx_pjpy_zcxmb ");
		sql.append("where xmdm in (select distinct xmdm from xg_zjdx_pjpy_zcfsb) and xn = ?");
		return dao.getOneRs(sql.toString(), new String[]{xn},"count");
	}
	
	/**
	 * @����: ��ȡ����������Ϣ�����ݣ���ǰ���õķ����е�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-6 ����02:38:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCszzInfo() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_zjdx_pjpy_csszb where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
}
