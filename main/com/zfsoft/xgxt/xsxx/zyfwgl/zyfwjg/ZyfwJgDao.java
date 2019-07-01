/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��10�� ����8:42:24 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqForm;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը������Dao
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��10�� ����8:42:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwJgDao extends SuperDAOImpl<ZyfwJgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
		super.setClass(ZyfwJgForm.class);
		super.setKey("fwid");
		super.setTableName("xg_xsxx_zyfwgl_jgb");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwJgForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwJgForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xqmc,t3.xm,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.nj,t3.xb,");
		sql.append("ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append("from xg_xsxx_zyfwgl_jgb t1 ");
		sql.append("left join xqdzb t2 on t1.xq=t2.xqdm ");
		sql.append("left join view_xsbfxx t3 on t1.xh=t3.xh ");
		sql.append("left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @����:�жϽ������ָ��ʱ������Ƿ����ظ���¼
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��12�� ����8:39:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zyfwJgForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwJgForm zyfwJgForm) {
		
		String id = zyfwJgForm.getFwid() == null ? "null" : zyfwJgForm.getFwid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from(select case when (fwjssj<? or fwkssj>?) then '0' else '1' end sfcd ");
		sql.append("from xg_xsxx_zyfwgl_jgb where xh=? and xn=? and xq=? and fwid <>?)where sfcd='1' ");
		String num = dao.getOneRs(sql.toString(), new String[] {zyfwJgForm.getFwkssj(),zyfwJgForm.getFwjssj(),zyfwJgForm.getXh(),
				zyfwJgForm.getXn(),zyfwJgForm.getXq(),id}, "num");
		return Integer.valueOf(num) > 0;
	}

	/** 
	 * @����:����ѧ�Ų�ѯ����־Ը��������Ϣ�б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��12�� ����11:59:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZyfwJgListByXh(String xh) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc,ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz,");
		sql.append("t1.fwkssj||'-'||t1.fwjssj fwsj ");
		sql.append("from xg_xsxx_zyfwgl_jgb t1 ");
		sql.append("left join xqdzb t2 on t1.xq=t2.xqdm ");
		sql.append("left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append("WHERE xh = ? ORDER BY xn desc,xq desc,fwkssj desc");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
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
	public ZyfwJgForm getModel(String fwid) throws Exception{
		
		String sql = "select t1.*,t2.xqmc from xg_xsxx_zyfwgl_jgb t1 left join xqdzb t2 on t1.xq=t2.xqdm where fwid = ?";
		return super.getModel(sql, new String[]{fwid});
	}

}
