/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-25 ����08:52:08 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-25 ����08:52:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CommTtxmDao extends SuperDAOImpl<TttzxmForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 *������Ŀ������Ŀѡ���ѯ,ֻ���˲���ģʽ��csms�� = 2,����ѧ��ѧ��Ϊ��ѧ�걾ѧ�ڵ���Ŀ��ʵ�ʿɲ�����>0,����ģʽ��1.���ˣ�2.����
	 *
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String xydm = user.getUserDep();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
        sql.append(" select t.*,");
        sql.append(" nvl(t1.sqrs,0) sqrs,");
        sql.append(" nvl(t2.tgrs,0) tgrs,");
        sql.append(" (t.kcyrs - nvl(t2.tgrs,0)) sjnum ,");//ʵ�ʿɲ�������= �ɲ�����-ͨ������
        sql.append(" t3.sskmmc,");
        sql.append(" t4.xmjbmc,");
        sql.append(" t5.bmmc,");
      
        if(user.getUserType().equalsIgnoreCase("stu")){
        	  sql.append(" t6.xqmc,'");
        	  sql.append(xydm +"' xydm ");
        }else{
        	 sql.append(" t6.xqmc ");
        }
       
//        sql.append("  case when (((select xydm from view_xsjbxx where xh = ?) in (select xydm ");
//        sql.append("  from XG_SZTZ_XMCYXYB c where t.xmdm = c.xmdm ) or(t.sbbmdm not in(select xydm from XG_SZTZ_XMCYXYB a where a.xmdm=t.xmdm  )and nvl(t5.num,0)='0')) and exists (select 1 from xg_sztz_xmjg t11 where t.xmdm = t11.xmdm and t11.xfrdsqzt in ('0','3') and t11.xfrdjgzt = '0')) then");
//        sql.append("  '1' else '0' end sfksq");
        sql.append(" from xg_sztz_xmjg t");
        sql.append(" left join (select count(1) sqrs,xmdm from xg_sztz_ttxmsq group by xmdm) t1");
        sql.append(" on t.xmdm = t1.xmdm");
        sql.append(" left join (select count(1) tgrs,xmdm from xg_sztz_ttxmjg group by xmdm) t2");
        sql.append(" on t.xmdm = t2.xmdm");
        sql.append(" left join xg_sztz_sskm t3");
        sql.append(" on t.sskmdm = t3.sskmdm");
        sql.append(" left join xg_sztz_xmjb t4");
        sql.append(" on t.xmjbdm = t4.xmjbdm");
        sql.append(" left join zxbz_xxbmdm t5");
        sql.append(" on t.sbbmdm = t5.bmdm");
        sql.append(" left join xqdzb t6");
        sql.append(" on t.xq = t6.xqdm");
        sql.append(" ");
        sql.append(" ");
        sql.append(" ");
		sql.append(") t where  t.sqkg = '1' and t.csms = '2' and  t.xn= '"+Base.currXn+"' and t.xq = '"+Base.currXq+"'");
		sql.append(" and sjnum >'0'");
		sql.append(" and ((t.sqkssj <= to_char(sysdate, 'yyyyMMdd')");
		sql.append(" and t.sqjssj >= to_char(sysdate, 'yyyyMMdd')) or (t.sqkssj is null and t.sqjssj is null)");
		sql.append(" or (t.sqkssj is null and  t.sqjssj >= to_char(sysdate, 'yyyyMMdd'))");
		sql.append(" or (t.sqjssj is null and  t.sqkssj <= to_char(sysdate, 'yyyyMMdd')))");
		sql.append(" and (t.xfrdsqzt in ('0','3') and t.xfrdjgzt = '0')");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and  (exists(select 1 from XG_SZTZ_XMCYXYB t7 where t.xmdm = t7.xmdm and t.xydm = t7.xydm)");
			sql.append(" or t.xmdm not in (select xmdm from  XG_SZTZ_XMCYXYB ))");
		}
		
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	
	/**
	 * 
	 * @����:��ѯѧ����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����11:05:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxx(String xh,String xmdm,String[]xhs){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (select t2.xh,t2.xm,t2.xydm,t2.xymc,t2.bjmc,'"+xmdm+"' xmdm from view_xsjbxx t2) t");
		sql.append(" where  (exists(select 1 from XG_SZTZ_XMCYXYB t1 where t1.xmdm = t.xmdm and t.xydm = t1.xydm)");
		sql.append(" or xmdm not in (select xmdm from  XG_SZTZ_XMCYXYB ))");
		
		if(xhs != null){
			sql.append("and xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				sql.append("?");
				if(xhs.length-1 != i ){
					sql.append(",");
				}
				paralist.add(xhs[i]);
			}
			sql.append(")");
		}
		sql.append(" and  xh = ?");
		paralist.add(xh);
		return dao.getMapNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}
	
	/**
	 * ѧ���б�
	 */
	public List<HashMap<String, String>> getXsxxList(TttzxmForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(model.getXhs())){
			xhArr = model.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder("select a.* from (select a1.xz,a1.nj,a1.xh,a1.xm,a1.xb,a1.xymc,a1.zymc,");
		sql.append("a1.bjmc,a1.xydm,a1.zydm,a1.bjdm, '"+model.getXmdm()+"' xmdm from view_xsjbxx a1  ");
		if(xhArr.length > 0){
			sql.append(" where a1.xh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(" and  (exists(select 1 from XG_SZTZ_XMCYXYB t1 where t1.xmdm = a.xmdm and a.xydm = t1.xydm)");
		sql.append(" or xmdm not in (select xmdm from  XG_SZTZ_XMCYXYB ))");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @����:��������ظ� һ������һ����Ŀ��ֻ�ܲμ�һ���Ŷ�(һ����ֻ�ܲμ�һ����Ŀ)
	 * @������Ϊ�˿���һ���Ŷ���Ŀ�в������ظ���ѧ�ţ��˴��ж��ظ���ʱ�������뻹�ǽ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����11:32:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public String checkIsNotExists(String[] xh,String xmdm,String ttsqid){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select wm_concat(xh) str from xg_sztz_ttcy");
		sql.append(" where xh in( ");
		for (int i = 0; i < xh.length; i++) {
			sql.append("?");
			if(i != xh.length-1){
				sql.append(",");
			}
			paralist.add(xh[i]);
		}
		sql.append(" )");
		sql.append(" and xmdm = ?");
		
		paralist.add(xmdm);
		if(StringUtils.isNotNull(ttsqid)){
			sql.append(" and ttsqid != ?");
			paralist.add(ttsqid);
		}
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		String result = dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "str");
		return result;
	}
	
	/**
	 * 
	 * @����: �����Ŷӳ�Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����01:49:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTtcy(String ttsqid,String xmdm,String[] xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_sztz_ttcy where  ttsqid = '"+ttsqid+"'");
		ArrayList<String> sqlArr = new ArrayList<String>();
		sqlArr.add(sql.toString());
		for (int i = 0; i < xh.length; i++) {
			StringBuilder sqlLs = new StringBuilder();
			sqlLs.append(" insert into xg_sztz_ttcy (xmdm,ttsqid,xh) values(");
			sqlLs.append("'"+xmdm+"',");
			sqlLs.append("'"+ttsqid+"',");
			sqlLs.append("'"+xh[i]+"'");
			sqlLs.append(" )");
			sqlArr.add(sqlLs.toString());
		}
		
		int[] result =	dao.runBatch(sqlArr.toArray(new String[]{}));
		return result != null&&result.length == (xh.length+1) ? true:false;
		
	}
	
	/**
	 * 
	 * @����: ����Ŷ������Ƿ��ظ�(ͬһ��Ŀ�¶������Ʋ������ظ�)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����02:19:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tdmc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNameIsNotExists(String tdmc,String xmdm,String ttsqid,String flag){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean result = true;
		//���������ͽ������ظ���֤,qb(ȫ��)
		if(flag.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" xg_sztz_ttxmsq");
			sql.append(" where xmdm = ?");
			sql.append(" and tdmc = ?");
			parameter.add(xmdm);
			parameter.add(tdmc);
			if(StringUtils.isNotNull(ttsqid)){
				sql.append(" and ttsqid != ?");
				parameter.add(ttsqid);
			}
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" xg_sztz_ttxmjg");
			sql.append(" where xmdm = ?");
			sql.append(" and tdmc = ?");
			parameter.add(xmdm);
			parameter.add(tdmc);
			if(StringUtils.isNotNull(ttsqid)){
				sql.append(" and ttjgid != ?");
				parameter.add(ttsqid);
			}
			sql.append(")");
		}else if(flag.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_sztz_ttxmjg");
			sql.append(" where xmdm = ?");
			sql.append(" and tdmc = ?");
			parameter.add(xmdm);
			parameter.add(tdmc);
			if(StringUtils.isNotNull(ttsqid)){
				sql.append(" and ttjgid != ?");
				parameter.add(ttsqid);
			}
//			parameter.add(utilform.getXn());
//			parameter.add(utilform.getXq());
//			parameter.add(utilform.getXh());
		}
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ����Ƿ��ѽ���׶�ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����02:52:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotInJdwh(String jdcy){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_sztz_xm_jdwh where jdcy = ?");
		String num  = dao.getOneRs(sql.toString(),new String[]{jdcy}, "num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @����:��ȡ�ų��ӳ�������ѧ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-27 ����09:01:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ttsqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDyxxNotDz(String ttsqid,String dzxh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select t.xh, t1.xm, t1.xymc, t1.bjmc ");
		sql.append("  from xg_sztz_ttcy t");
		sql.append("  left join view_xsjbxx t1");
		sql.append("  on t.xh = t1.xh");
		sql.append("  where t.xh != ?");
		sql.append("  and t.ttsqid =?");
		sql.append("  order by t1.xh,t1.xymc,t1.bjmc");
		return dao.getListNotOut(sql.toString(), new String[]{dzxh,ttsqid});
	}
	
	/**
	 * 
	 * @����: ��ȡ��Ŀ��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-27 ����09:41:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmxxMap(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
        sql.append(" select t.*,");
        sql.append(" nvl(t1.sqrs,0) sqrs,");
        sql.append(" nvl(t2.tgrs,0) tgrs,");
        sql.append(" t3.sskmmc,");
        sql.append(" t4.xmjbmc,");
        sql.append(" t5.bmmc,");
        sql.append(" t6.xqmc");
        sql.append(" from xg_sztz_xmjg t");
        sql.append(" left join (select count(1) sqrs,xmdm from xg_sztz_ttxmsq group by xmdm) t1");
        sql.append(" on t.xmdm = t1.xmdm");
        sql.append(" left join (select count(1) tgrs,xmdm from xg_sztz_ttxmjg group by xmdm) t2");
        sql.append(" on t.xmdm = t2.xmdm");
        sql.append(" left join xg_sztz_sskm t3");
        sql.append(" on t.sskmdm = t3.sskmdm");
        sql.append(" left join xg_sztz_xmjb t4");
        sql.append(" on t.xmjbdm = t4.xmjbdm");
        sql.append(" left join zxbz_xxbmdm t5");
        sql.append(" on t.sbbmdm = t5.bmdm");
        sql.append(" left join xqdzb t6");
        sql.append(" on t.xq = t6.xqdm");
        sql.append(" ");
        sql.append(" ");
        sql.append(" ");
		sql.append(") t where  t.xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * 
	 * @����:��ȡ�ӳ���Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-29 ����09:50:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xmdm
	 * @param xhs
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDzxx(String xh){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select t.xh,t.xm,t.xymc,t.bjmc from view_xsjbxx t");
		sql.append(" where  xh = ?");
		paralist.add(xh);
		return dao.getMapNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ�������Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-29 ����11:27:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delTtcy(String[] ids) throws Exception{
		if(ids == null){
			return false;
		}else{
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from xg_sztz_ttcy where ttsqid in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append("?");
				if(i != ids.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
			return dao.runUpdate(sql.toString(), ids);
		}
		
	}
	
}
