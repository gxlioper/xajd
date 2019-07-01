/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-25 ����04:54:05 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxsq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-25 ����04:54:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxsqDao extends SuperDAOImpl<XnjxsqForm>{

	
	public List<HashMap<String, String>> getPageList(XnjxsqForm t)
			throws Exception {
		return null;
	}

	//�߼���ѯ
	@Override
	public List<HashMap<String, String>> getPageList(XnjxsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.sskmmc,t2.sskmdm,t4.xmjbmc,t4.xmjbdm,t3.xmkssj,t3.xmmc xmmc1,t3.jcxf,t5.bjdm,t5.bjmc,t6.bmmc sbbmmc,t5.xb,");
		sql.append("t5.nj,t5.zydm,t5.zymc,t5.xydm,t5.xymc,t5.xm,decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc,  ");
		sql.append(" case when t3.sqkg = 1 and sysdate between to_date(nvl(t3.sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and   ");
		sql.append("  to_date(nvl(t3.sqjssj, '9999-01-01 00:00'), 'yyyy-mm-dd hh24:mi') + 1 then  '1'  else '0' end sqkg,t7.xqmc,t8.jxmc,t8.fjxf   ");
		sql.append(" from XG_SZTZ_JXSQ  t1");
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
		sql.append(" left join xg_sztz_xm_jx t8");
		sql.append(" on t1.jxid = t8.jgid");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	

	
	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("XG_SZTZ_JXSQ");
		this.setClass(XnjxsqForm.class);	
	}
	
	
	/** 
	 * @����:��ȡ������Ľ�����Ŀ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:24:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYiShen(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.sskmdm,t2.lxdh,t3.sskmmc,t4.xmjbmc,t5.xqmc,t6.bmmc sbbmmc,t2.kcyrs,t2.xmkssj,nvl(t8.xm,t10.xm) sbrxm,case when t2.sfsljx='1' then '��' else '��' end sfsljxmc,t2.jcxf from XG_SZTZ_XS_SQJG t1 " );
		sql.append(" left join xg_sztz_xmsq t2 on t1.xmdm = t2.xmdm ");
		sql.append(" left join xg_sztz_sskm t3 on t3.sskmdm = t2.sskmdm ");
		sql.append(" left join xg_sztz_xmjb t4 on t2.xmjbdm = t4.xmjbdm");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" left join zxbz_xxbmdm t6 on t2.sbbmdm=t6.bmdm ");
		sql.append(" left join yhb t8 on t2.sbr=t8.yhm left join xsxxb t10 on t2.sbr=t10.xh");
		sql.append(" where t1.xh = ? and t1.xn = ? and t1.xq = ? and t1.ylzd1 is null and not exists(select 1 from XG_SZTZ_JXSQ t11 where t11.xmdm = t1.xmdm and t11.xh = ?) and t1.xmdm in (select xmdm from xg_sztz_xm_jx)");
		sql.append(" ) t");
		return dao.getListNotOut(sql.toString(), new String[]{xh,Base.currXn,Base.currXq,xh});	
	}
	
	
	/** 
	 * @����:��ȡ����id
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:22:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getSqid(XnjxsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id as sqid from XG_SZTZ_JXSQ where xh = ? and  xn = ? and xmdm = ? and xq = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
	}
	
	/** 
	 * @����:��ȡ�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-2 ����09:22:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getShlc() {
		String sql = "select splc from xg_sztz_xnjx_cssz";
		return dao.getOneRs(sql, new String[]{}, "splc");
	}
	
}
