/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:39:52 
 */  
package com.zfsoft.xgxt.xszz.zzdy.jcsz;

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

public class ZzdyJcszDao extends SuperDAOImpl<ZzdyJcszForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyJcszForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyJcszForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xmmc,t3.xqmc,case when t1.sqkg = 1 and sysdate between to_date(nvl(t1.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t1.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end isopen ");
		sql.append(" from xg_xszz_new_zzdy_xmszb t1 left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm left join xqdzb t3 on t2.sqxq=t3.xqdm)t where 1=1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean isHave(ZzdyJcszForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_xszz_new_zzdy_xmszb where xmdm=?");
		if(null!=model.getSzid()){
			sql.append(" and szid<>'"+model.getSzid()+"' ");	
		}
 		String num= dao.getOneRs(sql.toString(), new String[]{model.getXmdm()}, "num");
 		return Integer.parseInt(num)>0;
	}
	
	public boolean isUsed(String[] values) {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_XSZZ_NEW_ZZDY_ZZMDB where xmdm in(select xmdm from xg_xszz_new_zzdy_xmszb where szid in(");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(values[i]);
		}
		sql.append(" )) ");
 		String num= dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
 		return Integer.parseInt(num)>0;
	}
	/**
	 * 
	 * @����:��ȡ��Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-23 ����11:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmList(String xn,String xq) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_xszz_new_zzxmdmb  where pdxn=? ");
		if("".equals(xq)){
			sql.append(" and pdxq is null");
		}else{
			sql.append("and pdxq='"+xq+"'");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xn});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(ZzdyJcszForm.class);
		super.setKey("szid");
		super.setTableName("xg_xszz_new_zzdy_xmszb");

		
	}

}
