/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:51:51 
 */  
package com.zfsoft.xgxt.xsztz.tzxmsq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-9 ����04:51:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsXmSqDao extends SuperDAOImpl<XsXmSqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsXmSqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsXmSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.sskmmc,t2.sskmdm,t4.xmjbmc,t4.xmjbdm,t3.xmkssj,t3.xmmc xmmc1,t3.jcxf,t5.bjdm,t5.bjmc,t6.bmmc sbbmmc,t5.xb,");
		sql.append("t5.nj,t5.zydm,t5.zymc,t5.xydm,t5.xymc,t5.xm,decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc,  ");
		sql.append(" case when t3.sqkg = 1 and sysdate between to_date(nvl(t3.sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and   ");
		sql.append("  to_date(nvl(t3.sqjssj, '9999-01-01 00:00'), 'yyyy-mm-dd hh24:mi') + 1 then  '1'  else '0' end sqkg,t7.xqmc   ");
		sql.append(" from xg_sztz_xsxmsq  t1");
		sql.append(" left join xg_sztz_xmjg t3");
		sql.append(" on t1.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_sskm t2 ");
		sql.append(" on t3.sskmdm = t2.sskmdm");
		sql.append(" left join xg_sztz_xmjb  t4");
		sql.append(" on t3.xmjbdm = t4.xmjbdm");
		sql.append(" left join view_xsjbxx t5" );
		sql.append(" on t1.xh = t5.xh");
		sql.append(" left join zxbz_xxbmdm t6");
		sql.append(" on t3.sbbmdm = t6.bmdm");
		sql.append(" left join xqdzb t7");
		sql.append(" on t1.xq = t7.xqdm");
		sql.append(" )t where 1= 1  ");
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
		super.setClass(XsXmSqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_sztz_xsxmsq");
	}
	
	/**
	 * 
	 * @����:��ȡ������˿���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����04:29:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from xg_sztz_xmjg t where 1=1 and t.xmdm = ?");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg"});
	}
	
	/**
	 * 
	 * @����:�ظ��ж�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����04:37:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExistForSave(XsXmSqForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select max(sqid) sqid from");
		sql.append("(select count(sqid) sqid from xg_sztz_xsxmsq t where t.xh = ? and t.xn = ? and t.xmdm = ?  and t.xq = ? ");
		sql.append(" union select count(jgid) sqid from xg_sztz_xs_sqjg t where t.xh = ? and t.xn = ? and t.xmdm = ? and t.xq = ? )");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq(),
			model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�ظ��ж�2
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����04:37:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExistForSave2(XsXmSqForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(jgid) sqid from xg_sztz_xs_sqjg t where t.xh = ? and t.xn = ? and t.xmdm = ? and t.xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:ֻ��ѯ������Ŀ��csms = 1������ģʽ  =1
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-13 ����11:03:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlist(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*, nvl(t2.sqrs,0) sqrs from (select t.*, nvl(t1.tgrs,0) tgrs");
		sql.append(" from (select t.*, t1.sskmmc, t2.xmjbmc, t3.bmmc sbbmmc, t4.xqmc , case  when exists (select 1");
        sql.append("   from xg_sztz_xsxmsq c  where t.xmdm = c.xmdm  and xh = ?) then  '1' else '0'  end sfsq, ");
        sql.append("  case when (((select xydm from view_xsjbxx where xh = ?) in (select xydm ");
        sql.append("  from XG_SZTZ_XMCYXYB c where t.xmdm = c.xmdm ) or(t.sbbmdm not in(select xydm from XG_SZTZ_XMCYXYB a where a.xmdm=t.xmdm  )and nvl(t5.num,0)='0')) and exists (select 1 from xg_sztz_xmjg t11 where t.xmdm = t11.xmdm and t11.xfrdsqzt in ('0','3') and t11.xfrdjgzt = '0')) then");
        sql.append("  '1' else '0' end sfksq");
		sql.append(" from xg_sztz_xmjg t  left join (select count(1) num ,xmdm from XG_SZTZ_XMCYXYB group by xmdm ) t5 on t.xmdm=t5.xmdm ");
		sql.append(" left join xg_sztz_sskm t1");
		sql.append(" on t1.sskmdm = t.sskmdm");
		sql.append(" left join xg_sztz_xmjb t2 ");
		sql.append(" on t.xmjbdm = t2.xmjbdm");
		sql.append(" left join zxbz_xxbmdm  t3 ");
		sql.append(" on t.sbbmdm = t3.bmdm ");
		sql.append(" left join xqdzb t4 ");
		sql.append(" on t.xq = t4.xqdm");
		sql.append(" where  t.sqkg = 1 and t.csms = '1') t");
		sql.append(" left join (select count(1) tgrs, t.xmdm");
		sql.append(" from xg_sztz_xs_sqjg t where xn = ? and xq = ?");
		sql.append("  group by t.xmdm) t1");
		sql.append("  on t.xmdm = t1.xmdm) t1 left join");
		sql.append("  (select count(1) sqrs, t.xmdm");
		sql.append("  from xg_sztz_xsxmsq t");
		sql.append("  where xn = ? and xq = ? and shzt <> 3 and shzt <> 2 group by t.xmdm) t2 on t1.xmdm = t2.xmdm");
			sql.append("  where (t1.sqkssj <= to_char(sysdate, 'yyyyMMdd')");
			sql.append("  and t1.sqjssj >= to_char(sysdate, 'yyyyMMdd')) or (t1.sqkssj is null and t1.sqjssj is null)");
			sql.append("  or (t1.sqkssj is null and  t1.sqjssj >= to_char(sysdate, 'yyyyMMdd'))");
			sql.append("  or (t1.sqjssj is null and  t1.sqkssj <= to_char(sysdate, 'yyyyMMdd'))");
		sql.append(" )t where 1= 1  ");
		sql.append(" order by sfrm desc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh,Base.currXn,Base.currXq,Base.currXn,Base.currXq});
	}
	
	//��ȡ������
//	public String getShlcID() {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select splc from xg_sztz_xmjg ");
//		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
//	}
	
	//��ȡsqid
	public String getSqid(XsXmSqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sqid from xg_sztz_xsxmsq where xh = ? and  xn = ? and xmdm = ? and xq = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
	}
	
	/**
	 * 
	 * @����:��ȡ�޸Ĳ鿴ʱ�Ļ��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-15 ����08:09:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getHdMap(String xmdm,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		sql.append("  select t.*,nvl(t1.tgrs,0) tgrs from ");
		sql.append("  (select t.*,nvl(t1.sqrs,0) sqrs from ");
		sql.append("  (select t.*, t1.sskmmc, t2.xmjbmc, t3.bmmc sbbmmc,t4.xqmc xqmc ");
		sql.append("  from xg_sztz_xmjg t");
		sql.append("  left join xg_sztz_sskm t1" );
		sql.append("  on t1.sskmdm = t.sskmdm");
		sql.append("  left join xg_sztz_xmjb t2" );
		sql.append("  on t.xmjbdm = t2.xmjbdm");
		sql.append("  left join zxbz_xxbmdm t3 ");
		sql.append("  on t.sbbmdm = t3.bmdm ");
		sql.append("  left join xqdzb t4 ");
		sql.append("  on t4.xqdm = t.xq ");
		sql.append("  where  t.xmdm = ? ) t left join (select count(1) sqrs,xmdm  from xg_sztz_xsxmsq  where xmdm = ? and xn = ? and xq= ? group by xmdm ) t1 ");
		sql.append("  on t.xmdm = t1.xmdm)t ");
		sql.append("  left join (select count(1) tgrs,xmdm  from xg_sztz_xs_sqjg  where xmdm = ? and xn = ? and xq= ? group by xmdm )t1 on t.xmdm = t1.xmdm ");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,xn,xq,xmdm,xn,xq});
	}
	
}
