/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-18 ����04:41:25 
 */  
package com.zfsoft.xgxt.rcsw.txhd.hdkhgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-9-18 ����04:41:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HdkhglDao extends SuperDAOImpl<hdkhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(hdkhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(hdkhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ");
	    sql.append(" (select t.xh,");
	    sql.append(" t2.xqmc,");
	    sql.append(" t.sfhdxf,");
	    sql.append(" t.hjqk,");
	    sql.append(" t1.*,");
	    sql.append(" t3.lbmc,");
	    sql.append(" t4.hdggmc hdgg,");
	    sql.append(" t1.hdkssj || ' �� ' || t1.hdjssj hdsj,");
	    sql.append(" t5.xm,");
	    sql.append(" t5.bjmc,");
	    sql.append(" t5.xymc,");
	    sql.append(" t5.zymc");
	    sql.append(" from XG_RCSW_TXHD_HDKH t");
	    sql.append(" left join xg_rcsw_txhd_xmwh t1");
	    sql.append(" on t.hdxmbh = t1.xmdm");
	    sql.append(" left join xqdzb t2");
	    sql.append(" on t.xq = t2.xqdm");
	    sql.append(" left join xg_rcsw_txhd_lbdm t3");
	    sql.append(" on t1.lbdm = t3.lbdm");
	    sql.append(" left join xg_rcsw_txhd_hdgg t4");
	    sql.append(" on t1.hdggdm = t4.hdggdm");
	    sql.append(" left join view_xsjbxx t5");
	    sql.append(" on t.xh = t5.xh)");
	    sql.append(" where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(hdkhForm.class);
		super.setKey("xh");
		super.setTableName("XG_RCSW_TXHD_HDKH");
	}
	
	//��ȡ���������list
	public List<HashMap<String, String>> getKhcjList(String xmdm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT t.xh,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.xymc,");
		sql.append(" t1.zymc,");
		sql.append(" t1.bjmc,");
		sql.append(" t2.hjqk,");
		sql.append(" t2.sfhdxf,");
		sql.append(" t2.id");
		sql.append(" FROM xg_rcsw_txhd_jgb t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join XG_RCSW_TXHD_HDKH t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" and t.xmdm = t2.hdxmbh");
		sql.append(" where t.xmdm = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	public List<HashMap<String, String>> getKhglList(hdkhForm t, User user)
	throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ");
		sql.append(" (select a.*,b.xqmc,c.lbmc,d.hdggmc hdgg,");
		sql.append(" hdkssj || ' �� ' || hdjssj hdsj");
		sql.append(" from xg_rcsw_txhd_xmwh a");
		sql.append(" left join xqdzb b");
		sql.append(" on a.xq = b.xqdm");
		sql.append(" left join xg_rcsw_txhd_lbdm c");
		sql.append(" on a.lbdm = c.lbdm");
		sql.append(" left join xg_rcsw_txhd_hdgg d");
		sql.append(" on a.hdggdm = d.hdggdm)");
		sql.append(" where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
     }

}
