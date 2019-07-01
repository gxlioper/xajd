/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:39:52 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzmdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-11-23 ����08:39:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdyMdglDao extends SuperDAOImpl<ZzdyMdglForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyMdglForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyMdglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select t1.*,case when t1.ffzt='1' then '��������' when t1.ffzt='0' then '��ͣ����' when t1.ffzt='-1' then '��ֹ����' else t1.ffzt end ffztmc ,");
		sql.append("t3.xqmc,t4.nj,t4.xm,t4.xb,t4.xymc,t4.zymc,t4.bjdm,t4.bjmc,t4.xydm,t4.zydm,t5.ffys from XG_XSZZ_NEW_ZZDY_ZZMDB t1 ");
		sql.append("left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm left join xqdzb t3 on t2.sqxq=t3.xqdm");
		sql.append(" left join view_xsbfxx t4 on t1.xh=t4.xh left join xg_xszz_new_zzdy_xmszb t5 on t1.xmdm=t5.xmdm)t where 1=1");
		sql.append(searchTj);
		sql.append(" order by xmmc,xymc,xn,xq desc ");
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ��ͬ��ѧ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-23 ����11:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean zzmdTb(String xn,String xq) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("MERGE INTO xg_xszz_new_zzdy_zzmdb t1");
		sql.append(" USING (");
		sql.append("select a.*,b.xmdm,t5.ffys,ceil(a.je/t5.ffys) yffje from xg_xszz_new_zzxmjgb a left join xg_xszz_new_zzxmdmb b on a.xmmc=b.xmmc and a.pdxn=b.pdxn and a.pdxq=b.pdxq left join xg_xszz_new_zzdy_xmszb t5 on b.xmdm=t5.xmdm where a.xmmc||a.pdxn||a.pdxq  in(select t.xmmc||t.xn||t.xq from (");
		sql.append("select t1.*,t2.xmmc,case when t1.sqkg = 1 and sysdate between to_date(nvl(t1.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t1.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end isopen ");
		sql.append(" from xg_xszz_new_zzdy_xmszb t1 left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm )t where isopen='1'))t2");
		sql.append(" ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm and t1.xn=t2.pdxn and t1.xq=t2.pdxq)");
		sql.append("WHEN NOT MATCHED THEN");
		sql.append("  INSERT (xh, xn, xq, xmdm,xmmc,yffje, zzzje)");
		sql.append("  VALUES (t2.xh, t2.pdxn, t2.pdxq, t2.xmdm,t2.xmmc,t2.yffje, t2.je)");
		return dao.runUpdate(sql.toString(), new String[]{});
		
	}
	public boolean insertBgLog(ZzdyMdglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into xg_xszz_zzdy_jebgb (xmdm, xh, bgr,bgqzt,bgqje,bghzt,bghje,bgly) values(?,?,?,?,?,?,?,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm(),t.getXh(),t.getBgr(),t.getBgqzt(),t.getBgqje(),t.getBghzt(),t.getBghje(),t.getBgly()});
	}
	/**
	 * 
	 * @����:�����¼�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-25 ����08:14:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBgjlList(ZzdyMdglForm model)
	throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,case when a.bgqzt='1' then '��������' when a.bgqzt='0' then '��ͣ����' when a.bgqzt='-1' then '��ֹ����' else a.bgqzt end ffztmc  from xg_xszz_zzdy_jebgb a where a.xh=? and a.xmdm=? ");
		sql.append(" order by bgsj desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXmdm()});
	}
	public ZzdyMdglForm getModel(ZzdyMdglForm t) throws Exception{
		String sql = "select a.*,b.xmmc ,a.xn||c.xqmc pdzq from XG_XSZZ_NEW_ZZDY_ZZMDB a left join xg_xszz_new_zzxmdmb b on a.xmdm = b.xmdm left join xqdzb c on a. xq= c.xqdm where a.id=? ";
		return getModel(sql, new String[]{t.getId()});
		
	}
	@Override
	protected void setTableInfo() {
		super.setClass(ZzdyMdglForm.class);
		super.setKey("id");
		super.setTableName("XG_XSZZ_NEW_ZZDY_ZZMDB");

	}

}
