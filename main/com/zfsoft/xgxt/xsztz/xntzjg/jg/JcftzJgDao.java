/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����11:17:59 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-31 ����11:17:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzJgDao extends SuperDAOImpl<JcftzJgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcftzJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JcftzJgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,t2.xmjbmc,t6.xmmc,t3.sskmmc,t4.xm,t4.bjdm,nvl(t12.bjmc,' ') bjmc,t4.xydm,t4.xy,t6.xn,t6.xq,t6.xmjbdm,t6.sskmdm,t6.xmkssj,t6.jcxf,t6.jgid xmckjgid,t11.jgid xsckjgid,nvl(t8.xm,t7.xm) sbrxm,t9.bmmc sbbmmc,nvl(t10.jxmc,' ') jxmc, ");
		sql.append(" decode(t1.sfqq,'0','��','1','��','��') sfqqmc, decode(t6.csms,1,'����',2,'����',t6.csms) csmsmc,t6.csms,");
		sql.append(" (case nvl(t1.jxdm,'0') when '0' then (to_number(t1.tzhjcf)) else (to_number(t1.tzhjcf)+to_number(nvl(t10.fjxf,'0'))) end) zf ");
		sql.append(" from xg_sztz_jcftz_jg t1 left join xg_sztz_xmjg t6 on t1.xmdm = t6.xmdm ");
		sql.append(" left join xg_sztz_xmjb t2 on t6.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t6.sskmdm=t3.sskmdm");
		sql.append(" left join xsxxb t4 on t1.xh = t4.xh ");
		sql.append(" left join view_njxyzybj t12 on t4.bjdm = t12.bjdm ");
		sql.append(" left join xqdzb t5 on t6.xq=t5.xqdm ");
		sql.append(" left join xsxxb t7 on t6.sbr = t7.xh ");
		sql.append(" left join xg_sztz_xm_jx t10 on t1.jxdm = t10.jgid ");
		sql.append(" left join yhb t8 on t6.sbr = t8.yhm ");
		sql.append(" left join zxbz_xxbmdm t9 on t6.sbbmdm = t9.bmdm ");
		sql.append(" left join xg_sztz_xs_sqjg t11 on t1.xmdm = t11.xmdm and t1.xh = t11.xh ");
		sql.append(" order by t6.xmkssj desc) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(JcftzJgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_sztz_jcftz_jg");
	}
	
	//�Ƿ��н����¼
	public boolean isHaveRecord(JcftzJgForm t) {
		String sql = "select count(1) num from xg_sztz_jcftz_jg where xmdm = ?";
		String num = dao.getOneRs(sql, new String[]{t.getXmdm()}, "num");
		return Integer.valueOf(num) > 0;
	}
	
	//ɾ��ԭ�н����¼
	public boolean delForSq(JcftzJgForm t) throws Exception{
		String sql = "delete from xg_sztz_jcftz_jg where xmdm = ?";
		return dao.runUpdate(sql, new String[]{t.getXmdm()});
	}
	
	public boolean delForSqBylcId(String sqid) throws Exception {
		String sql = "delete from xg_sztz_jcftz_jg where lylcywid = ?";
		return dao.runUpdate(sql, new String[]{sqid});
	}
	
	//�����϶����״̬
	public boolean updateRenDing(JcftzJgForm t) throws Exception{
		String sql = "update xg_sztz_xmjg set xfrdjgzt = ? where xmdm = ?";
		return dao.runUpdate(sql, new String[]{t.getXfrdjgzt(),t.getXmdm()});
	}
	
	//�õ���Ŀ�б�
	public List<HashMap<String, String>> getXmSelectList(User user,JcftzJgForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,nvl(t8.xm,t7.xm) sbrxm,nvl(t9.num,0) ybjrs,decode(t1.sfsljx,'0','��','1','��','��') sfsljxmc,t5.xqmc from xg_sztz_xmjg t1 ");
		sql.append(" left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm = t4.bmdm");
	    sql.append(" left join yhb t8 on t1.sbr = t8.yhm ");
	    sql.append(" left join xsxxb t7 on t1.sbr = t7.xh ");
	    sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
	    sql.append(" left join(select count(xh)num,xmdm from XG_SZTZ_XS_SQJG group by xmdm" +
	    		" union all " +
	    		" select count(ttjgid) num,xmdm " +
	    		" from xg_sztz_ttxmjg  group by xmdm" +
	    		")t9 on t1.xmdm=t9.xmdm ");
	    sql.append(" ) t ");
	    if("wrd".equalsIgnoreCase(t.getType())){
	    	sql.append(" where not exists (select 1 from (select distinct xmdm from xg_sztz_jcftz_jg) t10 where t10.xmdm = t.xmdm) ");
	    }else if("yrd".equalsIgnoreCase(t.getType())){
	    	sql.append(" where exists (select 1 from (select distinct xmdm from xg_sztz_jcftz_jg) t10 where t10.xmdm = t.xmdm) ");
	    }
	    sql.append(" and t.ybjrs <> 0 ");
	    return dao.getListNotOut(sql.toString(), new String[]{});		
	}
	
	//�õ��϶���ѧ��
	public List<HashMap<String, String>> getXsListForRenDing(JcftzJgForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t1.*,t7.xm,nvl(t9.bjmc,' ') bjmc,t8.jcxf,t8.xfrdsqzt,t1.ylzd1 jxdm,t1.ylzd3 tzhjcf,nvl(t1.ylzd2,'0') sfqq,t3.jxmc, ");
		sql.append(" (case nvl(t1.ylzd3,'0') when '0' then (to_number(t8.jcxf)+to_number(nvl(t3.fjxf,'0'))) else (to_number(t1.ylzd3)+to_number(nvl(t3.fjxf,'0'))) end) zf, ");
		sql.append(" decode(t1.ylzd2,'0','��','1','��','��') sfqqmc, ");
		sql.append(" decode(t1.ylzd2,'0','��','1','��',ylzd2) sfqqrs ");
		sql.append(" from xg_sztz_xs_sqjg t1 ");
		sql.append(" left join xg_sztz_xm_jx t3 on t1.ylzd1 = t3.jgid ");
		sql.append(" left join xsxxb t7 on t1.xh = t7.xh ");
		sql.append(" left join view_njxyzybj t9 on t7.bjdm = t9.bjdm ");
		sql.append(" left join xg_sztz_xmjg t8 on t1.xmdm = t8.xmdm ");		
		sql.append(" where t1.xmdm = ? )");
	   return dao.getListNotOut(sql.toString(), new String[]{t.getXmdm()});
	}
	
	public boolean delForJg(JcftzJgForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_sztz_jcftz_jg where xmdm = ? and lylcywid is null");
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm()});
	}
	
	//�õ���Ҫ�޸��϶���ѧ��
	public List<HashMap<String, String>> getXsListForUpdate(JcftzJgForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t1.*,t7.xm,nvl(t9.bjmc,' ') bjmc,t8.jcxf,t8.xfrdsqzt,t3.jxmc, ");
		sql.append(" (case nvl(t1.jxdm,'0') when '0' then (to_number(t1.tzhjcf)) else (to_number(t1.tzhjcf)+to_number(nvl(t3.fjxf,'0'))) end) zf, ");
		sql.append(" decode(t1.sfqq,'0','��','1','��') sfqqmc ");
		sql.append(" from xg_sztz_jcftz_jg t1 ");
		sql.append(" left join xg_sztz_xm_jx t3 on t1.jxdm = t3.jgid ");
		sql.append(" left join xsxxb t7 on t1.xh = t7.xh ");
		sql.append(" left join xg_sztz_xmjg t8 on t1.xmdm = t8.xmdm ");
		sql.append(" left join view_njxyzybj t9 on t7.bjdm = t9.bjdm ");
		sql.append(" where t1.xmdm = ? )");
	   return dao.getListNotOut(sql.toString(), new String[]{t.getXmdm()});
	}
	
	//�õ���Ŀ��Ϣ
	public HashMap<String, String> getXmxx(JcftzJgForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,nvl(t8.xm,t7.xm) sbrxm,nvl(t9.num,0) ybjrs,decode(t1.sfsljx,'0','��','1','��','��') sfsljxmc,t5.xqmc from xg_sztz_xmjg t1 ");
		sql.append(" left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm = t4.bmdm");
	    sql.append(" left join yhb t8 on t1.sbr = t8.yhm ");
	    sql.append(" left join xsxxb t7 on t1.sbr = t7.xh ");
	    sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
	    sql.append(" left join(select count(xh)num,xmdm from XG_SZTZ_XS_SQJG group by xmdm)t9 on t1.xmdm=t9.xmdm ");
	    sql.append(" where t1.xmdm = ? ");
	    return dao.getMapNotOut(sql.toString(), new String[]{t.getXmdm()});
	}
	
	/**
	 * 
	 * @����: �õ�Ҫ�϶�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-5 ����11:24:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtListForRenDing(JcftzJgForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select t1.*,t1.dzxh xh,t1.ttjgid jgid,t7.xm,nvl(t9.bjmc,' ') bjmc,t8.jcxf,t8.xfrdsqzt,t1.ylzd1 jxdm,t1.ylzd3 tzhjcf,nvl(t1.ylzd2,'0') sfqq,t3.jxmc,t9.xymc, ");
		sql.append(" (case nvl(t1.ylzd3,'0') when '0' then (to_number(t8.jcxf)+to_number(nvl(t3.fjxf,'0'))) else (to_number(t1.ylzd3)+to_number(nvl(t3.fjxf,'0'))) end) zf, ");
		sql.append(" decode(t1.ylzd2,'0','��','1','��','��') sfqqmc, ");
		sql.append(" decode(t1.ylzd2,'0','��','1','��',ylzd2) sfqqrs ");
		sql.append(" from xg_sztz_ttxmjg t1 ");
		sql.append(" left join xg_sztz_xm_jx t3 on t1.ylzd1 = t3.jgid ");
		sql.append(" left join xsxxb t7 on t1.dzxh = t7.xh ");
		sql.append(" left join view_njxyzybj t9 on t7.bjdm = t9.bjdm ");
		sql.append(" left join xg_sztz_xmjg t8 on t1.xmdm = t8.xmdm ");		
		sql.append(" where t1.xmdm = ? )");
	   return dao.getListNotOut(sql.toString(), new String[]{t.getXmdm()});
	}
}
