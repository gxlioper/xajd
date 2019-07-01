/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��12�� ����10:57:33 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ���������춯Dao
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��12�� ����10:57:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PlydDao extends SuperDAOImpl<PlydModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(PlydModel.class);
		super.setTableName("xg_gygl_ydlsb");
		super.setKey("xh");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PlydModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PlydModel t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @����: ��ѯ����ס�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��13�� ����9:34:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYrzPageList(PlydModel t, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,t2.xb,t2.nj,t2.zydm,t2.zymc,t2.xydm,t2.xymc,");
		sql.append("t2.bjdm,t2.bjmc,t3.ldmc,t1.lddm,t1.qsh,t1.cwh,t1.qsh yss ");
		sql.append("from xg_gygl_new_cwxxb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where t1.xh is not null and not exists ");
		sql.append("(select 1 from xg_gygl_ydlsb t4 where t1.xh=t4.xh)");
		sql.append(") where 1=1");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/**
	 * 
	 * @����: �������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��13�� ����9:45:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtzPageList(PlydModel t, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,t2.xb,t2.nj,t2.zydm,t2.zymc,");
		sql.append("t2.xydm,t2.xymc,t2.bjdm,t2.bjmc,t3.ldmc,t3.lddm,t1.yss,t1.ycw ");
		sql.append("from xg_gygl_ydlsb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.yld = t3.lddm ");
		sql.append("where xld is null");
		sql.append(") where 1=1");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/**
	 * 
	 * @����: ȷ�ϵ����б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��13�� ����9:53:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQrtzList(PlydModel t, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.xh,t2.xm,t2.xb,t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,");
		sql.append("t3.ldmc,t3.lddm,t1.yss,t1.ycw,t4.ldmc xldmc,");
		sql.append("t1.xld,t1.xss,t1.xcw,t5.xh yxh, ");
		sql.append("case when t5.xh is null or exists ");
		sql.append("(select 1 from xg_gygl_ydlsb t6 where t5.xh = t6.xh) then 1 else 0 end kftj ");
		sql.append("from xg_gygl_ydlsb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.yld = t3.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t4 on t1.xld = t4.lddm ");
		sql.append("left join xg_gygl_new_cwxxb t5 on t1.xld = t5.lddm ");
		sql.append("and t1.xss = t5.qsh and t1.xcw = t5.cwh ");
		sql.append("where t1.xcw is not null ");
		sql.append(") where 1=1");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/**
	 * 
	 * @����: ���ô�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����11:16:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhArr
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean szDtz(String[] xhArr) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_gygl_ydlsb(xh,yld,yss,ycw) ");
		sql.append("select xh,lddm,qsh,cwh from xg_gygl_new_cwxxb t1 ");
		sql.append("where (");
		
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh)");
		return dao.runUpdate(sql.toString(), xhArr);
	}
	
	
	/**
	 * 
	 * @����: ȡ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����01:43:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhArr
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxDtz(String[] xhArr) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_gygl_ydlsb ");
		sql.append("where (");
		
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), xhArr);
	}
	
	
	/**
	 * 
	 * @����: ȡ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����01:43:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhArr
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxtz(String[] xhArr) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_gygl_ydlsb set xld='',xss='',xcw='' ");
		sql.append("where (");
		
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), xhArr);
	}
	
	
	
	/**
	 * 
	 * @����: ��ѧ�Ų�ѯ����ѧ����Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����02:23:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhArr
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getTzxsList(String[] xhArr){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,xm,xb from view_xsjbxx where (");
		for (int i = 0 ; i < xhArr.length ; i++){
			sql.append("xh=?");
			
			if (i+1 != xhArr.length){
				sql.append(" or ");
			}
		}
		sql.append(")");
		
		return dao.getListNotOut(sql.toString(), xhArr);
	}
	
	
	
	
	/**
	 * 
	 * @����: �ɵ�����ס�Ĵ�λ��Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����02:50:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xb
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCwxxList(String xb){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.lddm,t1.qsh,t1.cwh,t2.qsxb,t2.ch,t3.ldmc ");
		sql.append("from xg_gygl_new_cwxxb t1 ");
		sql.append("left join xg_gygl_new_qsxxb t2 on t1.qsh = t2.qsh and t1.lddm = t2.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where (exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh) ");
		sql.append("or t1.xh is null) ");
		sql.append("and not exists (select 1 from xg_gygl_ydlsb t4 where t1.lddm=t4.xld and t1.qsh=t4.xss and t1.cwh=t4.xcw) ");
		sql.append("and t2.qsxb=? and t1.sfbl='��' order by t1.lddm,t2.ch,t1.qsh,t1.cwh ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xb});
	}
	
	
	/**
	 * 
	 * @����: �ɵ�����ס��¥����Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����02:50:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xb
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getLcxxList(String xb){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct t1.lddm,t2.ch,t3.ldmc ");
		sql.append("from xg_gygl_new_cwxxb t1 ");
		sql.append("left join xg_gygl_new_qsxxb t2 on t1.qsh = t2.qsh and t1.lddm = t2.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where (exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh) ");
		sql.append("or t1.xh is null)  ");
		sql.append("and not exists (select 1 from xg_gygl_ydlsb t4 where t1.lddm=t4.xld and t1.qsh=t4.xss and t1.cwh=t4.xcw) ");
		sql.append("and t2.qsxb=? order by t1.lddm,t2.ch ");
		return dao.getListNotOut(sql.toString(), new String[]{xb});
	}
	
	/**
	 * 
	 * @����: �ɵ�����ס��¥����Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����02:50:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xb
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdxxList(String xb){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct t1.lddm,t3.ldmc ");
		sql.append("from xg_gygl_new_cwxxb t1 ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.lddm = t3.lddm ");
		sql.append("where (exists (select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh) ");
		sql.append("or t1.xh is null) ");
		sql.append("and exists (select 1 from xg_gygl_new_qsxxb t2 where t1.lddm=t2.lddm and t2.qsxb=?)");
		sql.append("and not exists (select 1 from xg_gygl_ydlsb t4 where t1.lddm=t4.xld and t1.qsh=t4.xss and t1.cwh=t4.xcw)");
		sql.append("order by t1.lddm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xb});
	}
	
	
	/**
	 * 
	 * @����: ����������ס��Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-19 ����03:25:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRzxx(List<String[]> params) throws SQLException{
		
		String sql = "update xg_gygl_ydlsb set xld=?,xss=?,xcw=? where xh=?";
		
		int[] result = dao.runBatch(sql, params);
		
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�����ύ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-24 ����02:17:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int getCountByBktj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) bktj from (");
		sql.append("select case when t5.xh is null or exists ");
		sql.append("(select 1 from xg_gygl_ydlsb t6 where t5.xh = t6.xh) then 1 else 0 end kftj ");
		sql.append("from xg_gygl_ydlsb t1 left join xg_gygl_new_cwxxb t5 on t1.xld = t5.lddm ");
		sql.append("and t1.xss = t5.qsh and t1.xcw = t5.cwh ) where kftj = 0");
		
		String count = dao.getOneRs(sql.toString(), new String[]{}, "bktj");
		return Integer.valueOf(count);
	}
	
	
	/**
	 * 
	 * @����: ��ѯδ������λ�ļ�¼��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-26 ����05:04:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int getCountWrz(){
		
		String sql = "select count(1) c from xg_gygl_ydlsb where xcw is null";
		
		String count = dao.getOneRs(sql, new String[]{}, "c");
		return Integer.valueOf(count);
	}
	
	
	
	
	/**
	 * 
	 * @����: ��Ŀ�괲λԭ��ס��ѧ�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-26 ����04:57:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYcwToBlank() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_gygl_new_cwxxb t1 set xh='' where exists (");
		sql.append("select 1 from xg_gygl_ydlsb t2 where t1.xh = t2.xh");
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����: �������������µ���ʽס����Ϣ��
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-26 ����04:56:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateToXcw() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_gygl_new_cwxxb t1 set xh = ");
		sql.append("(select xh from xg_gygl_ydlsb t2 where ");
		sql.append("t1.lddm=t2.xld and t1.qsh=t2.xss and t1.cwh=t2.xcw) ");
		sql.append("where exists (select 1 from xg_gygl_ydlsb t2 ");
		sql.append("where t1.lddm=t2.xld and t1.qsh=t2.xss and t1.cwh=t2.xcw) ");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: ����춯��ʱ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-27 ����10:05:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean clearTempData() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_gygl_ydlsb t1 where exists ");
		sql.append("(select 1 from xg_gygl_new_cwxxb t2 where t1.xh=t2.xh and ");
		sql.append("t1.xld = t2.lddm and t1.xss=t2.qsh and t1.xcw=t2.cwh");
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	
	
	/**
	 * 
	 * @����: �������춯��¼���浽�춯�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-27 ����10:50:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveYdjg() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_gygl_new_ssyd_ssydjg(xh,czsj,xn,xq,ssydlx,tstzsj,");
		sql.append("ydqlddm,ydqldmc,ydqqsh,ydqcwh,ydhlddm,ydhldmc,ydhqsh,ydhcwh)");
		sql.append("select t1.xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),t2.dqxn,t2.dqxq,");
		sql.append("'01',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),t1.yld,t3.ldmc,");
		sql.append("t1.yss,t1.ycw,t1.xld,t4.ldmc,t1.xss,t1.xcw ");
		sql.append("from xg_gygl_ydlsb t1 left join xtszb t2 on 1=1 ");
		sql.append("left join xg_gygl_new_ldxxb t3 on t1.yld = t3.lddm ");
		sql.append("left join xg_gygl_new_ldxxb t4 on t1.xld = t4.lddm ");
		
		return dao.runUpdate(sql.toString(), new String[]{});
	}
}
