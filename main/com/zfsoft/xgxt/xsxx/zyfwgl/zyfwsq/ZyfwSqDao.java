/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��4�� ����2:22:10 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը��������Dao
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��4�� ����2:22:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwSqDao extends SuperDAOImpl<ZyfwSqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
		super.setClass(ZyfwSqForm.class);
		super.setKey("fwid");
		super.setTableName("xg_xsxx_zyfwgl_sqb");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwSqForm t) throws Exception {
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwSqForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xqmc,t3.xm,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.nj,t3.xb,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc,");
		sql.append("ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append("from xg_xsxx_zyfwgl_sqb t1 ");
		sql.append("left join xqdzb t2 on t1.xq=t2.xqdm ");
		sql.append("left join view_xsbfxx t3 on t1.xh=t3.xh ");
		sql.append("left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @����:�жϵ�ǰʱ���Ƿ��������¼�����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����4:01:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwSqForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwSqForm zyfwSqForm) {
		
		String id = zyfwSqForm.getFwid() == null ? "null" : zyfwSqForm.getFwid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from(select case when (fwjssj<? or fwkssj>?) then '0' else '1' end sfcd from ");
		sql.append(" (select xh,xn,xq,fwid,fwkssj,fwjssj from xg_xsxx_zyfwgl_sqb union select xh,xn,xq,fwid,fwkssj,fwjssj from xg_xsxx_zyfwgl_jgb) ");
		sql.append("  where xh=? and xn=? and xq=? and fwid <>?)where sfcd='1' ");
		String num = dao.getOneRs(sql.toString(), new String[] {zyfwSqForm.getFwkssj(),zyfwSqForm.getFwjssj(),zyfwSqForm.getXh(),
				zyfwSqForm.getXn(),zyfwSqForm.getXq(),id}, "num");
		return Integer.valueOf(num) > 0;
	}

	/** 
	 * @����:��ȡ�������������õ��������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��5�� ����4:35:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getShlcID() {

		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_xsxx_zyfwgl_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * @����:��д����ѯһ��������ϸ��Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��9�� ����11:52:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean �������� 
	 * @throws Exception  
	 */
	public ZyfwSqForm getModel(String fwid) throws Exception{
		
		String sql = "select t1.*,t2.xqmc from xg_xsxx_zyfwgl_sqb t1 left join xqdzb t2 on t1.xq=t2.xqdm where fwid = ?";
		return super.getModel(sql, new String[]{fwid});
	}


}
