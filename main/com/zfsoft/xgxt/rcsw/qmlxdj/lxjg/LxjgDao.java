/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����09:07:52 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����09:07:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxjgDao extends SuperDAOImpl<LxjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.* from (");
		sql.append(" select t.*,");
		sql.append(" rownum rnn,");
		sql.append(" t1.XM,");
		sql.append(" t1.XB,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.NJ,");
		sql.append(" t1.ZYMC,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.BJMC,t1.mzmc,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.zybj,");
		sql.append(" t1.zybjmc,");
		sql.append(" x1.sydm,x1.symc,");
		sql.append(" t2.xqmc,");
		sql.append(" t3.mc lxgj,");
		sql.append(" t4.mc fxgj,");
		sql.append(" ssx.shengmc,");
		sql.append(" ssx.shimc,");
		sql.append(" ssx.xianmc,");
		sql.append(" ssx.shengmc || ssx.shimc || ssx.xianmc ssxdz");
		sql.append(" from xg_rcsw_lxqxdj_lxqxdjjgb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join XG_XTWH_SYBJGLB x on t1.bjdm = x.bjdm");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm");
		sql.append(" left join xqdzb t2");
		sql.append(" on t.xq = t2.xqdm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t3");
		sql.append(" on t.lxjtgjdm = t3.dm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t4");
		sql.append(" on t.fxjtgjdm = t4.dm");
		sql.append(" left join xg_view_dmk_qx ssx");
		sql.append(" on ssx.qxdm = t.mddssx");
		sql.append(" ) t where 1=1 ");
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
		this.setClass(LxjgForm.class);
		this.setTableName("xg_rcsw_lxqxdj_lxqxdjjgb");
		this.setKey("jgid");
	}
	
	/**
	 * 
	 * @����: ��ȡ�鿴��Ϣmap
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����03:45:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCkxx(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,");
		sql.append(" t1.xqmc,");
		sql.append(" t2.mc lxgj,");
		sql.append(" t3.mc fxgj");
		sql.append(" from");
		sql.append(" xg_rcsw_lxqxdj_lxqxdjjgb t");
		sql.append(" left join xqdzb t1");
		sql.append(" on t.xq = t1.xqdm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t2");
		sql.append(" on t.lxjtgjdm = t2.dm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t3");
		sql.append(" on t.fxjtgjdm = t3.dm");
		sql.append(" where t.jgid = ?");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���ͨ��֮��ɾ�������֮��ԭ�к����ͨ����¼�ظ��ļ�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:33:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delJgbyShTg(String xh,String xn,String xq) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_rcsw_lxqxdj_lxqxdjjgb where xh = ? and xn = ? and xq = ?");
		return dao.runUpdate(sql.toString(), new String[]{xh,xn,xq});
	}

}
