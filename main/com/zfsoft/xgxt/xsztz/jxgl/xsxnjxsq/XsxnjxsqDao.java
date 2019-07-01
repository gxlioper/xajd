/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-1 ����08:39:24 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xsxnjxsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ���������:1282]
 * @ʱ�䣺 2016-2-1 ����08:39:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxnjxsqDao extends SuperDAOImpl<XsxnjxsqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxnjxsqForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	
	//����ѧ��������δ��������߼���ѯ
	public List<HashMap<String, String>> getPageList(XsxnjxsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();		
		if("wsq".equals(t.getSqzt())){
			sql.append(" select t.* from (");
			sql.append(" select t1.*,t2.sskmdm,t2.xmjbdm,t2.lxdh,t3.sskmmc,t4.xmjbmc,t2.xmmc xmmc1,t5.xqmc,t6.bmmc sbbmmc,t2.kcyrs,t2.xmkssj,nvl(t8.xm,t10.xm) sbrxm,case when t2.sfsljx='1' then '��' else '��' end sfsljxmc,t2.jcxf,decode(t12.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����','δ����') shztmc,t12.shzt,t12.shlc, " );
			sql.append(" t13.bjdm,t13.bjmc,t13.xb,t12.id, ");
		    sql.append(" t13.nj,t13.zydm,t13.zymc,t13.xydm,t13.xymc,t13.xm from XG_SZTZ_XS_SQJG t1 ");
			sql.append(" left join xg_sztz_xmjg t2 on t1.xmdm = t2.xmdm ");
			sql.append(" left join xg_sztz_sskm t3 on t3.sskmdm = t2.sskmdm ");
			sql.append(" left join xg_sztz_xmjb t4 on t2.xmjbdm = t4.xmjbdm ");
			sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
			sql.append(" left join zxbz_xxbmdm t6 on t2.sbbmdm=t6.bmdm ");
			sql.append(" left join yhb t8 on t2.sbr=t8.yhm left join xsxxb t10 on t2.sbr=t10.xh ");
			sql.append(" left join XG_SZTZ_JXSQ t12 on t1.xmdm = t12.xmdm and t1.xh = t12.xh");
			sql.append(" left join view_xsjbxx t13 on t1.xh = t13.xh ");
			sql.append(" where t1.ylzd1 is null and t12.shzt in('0','3')");
			sql.append(" union all");
			sql.append(" select t1.*,t2.sskmdm,t2.xmjbdm,t2.lxdh,t3.sskmmc,t4.xmjbmc,t2.xmmc xmmc1,t5.xqmc,t6.bmmc sbbmmc,t2.kcyrs,t2.xmkssj,nvl(t8.xm,t10.xm) sbrxm,case when t2.sfsljx='1' then '��' else '��' end sfsljxmc,t2.jcxf,decode(t12.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����','δ����') shztmc,t12.shzt,t12.shlc, " );
			sql.append(" t13.bjdm,t13.bjmc,t13.xb,t12.id, ");
		    sql.append(" t13.nj,t13.zydm,t13.zymc,t13.xydm,t13.xymc,t13.xm from XG_SZTZ_XS_SQJG t1");
			sql.append(" left join xg_sztz_xmjg t2 on t1.xmdm = t2.xmdm ");
			sql.append(" left join xg_sztz_sskm t3 on t3.sskmdm = t2.sskmdm ");
			sql.append(" left join xg_sztz_xmjb t4 on t2.xmjbdm = t4.xmjbdm ");
			sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
			sql.append(" left join zxbz_xxbmdm t6 on t2.sbbmdm=t6.bmdm ");
			sql.append(" left join yhb t8 on t2.sbr=t8.yhm left join xsxxb t10 on t2.sbr=t10.xh ");
			sql.append(" left join XG_SZTZ_JXSQ t12 on t1.xmdm = t12.xmdm and t1.xh = t12.xh ");
			sql.append(" left join view_xsjbxx t13 on t1.xh = t13.xh");
			sql.append(" where t1.ylzd1 is null and not exists(select 1 from XG_SZTZ_JXSQ t10 where t10.xmdm = t1.xmdm and t10.xh = '" + user.getUserName() +"')");			
		}else if ("ysq".equals(t.getSqzt())){
			sql.append(" select t.* from (");
			sql.append(" select t1.*,t2.sskmdm,t2.lxdh,t2.xmjbdm,t3.sskmmc,t4.xmjbmc,t2.xmmc xmmc1,t5.xqmc,t6.bmmc sbbmmc,t2.kcyrs,t2.xmkssj,nvl(t8.xm,t10.xm) sbrxm,case when t2.sfsljx='1' then '��' else '��' end sfsljxmc,t2.jcxf,decode(t12.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����','δ����') shztmc,t12.shzt,t12.shlc, " );
			sql.append(" t13.bjdm,t13.bjmc,t13.xb,t12.id, ");
		    sql.append(" t13.nj,t13.zydm,t13.zymc,t13.xydm,t13.xymc,t13.xm from XG_SZTZ_XS_SQJG t1");
			sql.append(" left join xg_sztz_xmjg t2 on t1.xmdm = t2.xmdm ");
			sql.append(" left join xg_sztz_sskm t3 on t3.sskmdm = t2.sskmdm ");
			sql.append(" left join xg_sztz_xmjb t4 on t2.xmjbdm = t4.xmjbdm");
			sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
			sql.append(" left join zxbz_xxbmdm t6 on t2.sbbmdm=t6.bmdm ");
			sql.append(" left join yhb t8 on t2.sbr=t8.yhm left join xsxxb t10 on t2.sbr=t10.xh");
			sql.append(" left join XG_SZTZ_JXSQ t12 on t1.xmdm = t12.xmdm and t1.xh = t12.xh ");
			sql.append( "left join view_xsjbxx t13 on t1.xh = t13.xh");
			sql.append(" where t12.shzt not in('0','3') and exists(select 1 from XG_SZTZ_JXSQ t10 where t10.xmdm = t1.xmdm and t10.xh = '" + user.getUserName() + "')");
		}
		if(null!=t.getXmjbdm()){
			sql.append(" and t2.xmjbdm = '"+t.getXmjbdm()+"'");
		}
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
		this.setKey("id");
		this.setTableName("XG_SZTZ_JXSQ");
		this.setClass(XsxnjxsqForm.class);
		
	}
	
	//�õ����뽱��id
	public String getSqid(XsxnjxsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id as sqid from XG_SZTZ_JXSQ where xh = ? and  xn = ? and xmdm = ? and xq = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
	}
	
}
