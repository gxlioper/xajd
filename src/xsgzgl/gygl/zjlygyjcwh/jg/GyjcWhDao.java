/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-28 ����04:26:52 
 */  
package xsgzgl.gygl.zjlygyjcwh.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-28 ����04:26:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyjcWhDao extends SuperDAOImpl<GyjcWhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjcWhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjcWhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from ( ");
		sql.append(" select t.id,");
		sql.append(" t.xh,");
		sql.append(" t.jcdm,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t1.nj,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t3.xqmc,");
		sql.append(" t2.lb,");
		sql.append(" decode(t2.lb,'jf','�ӷ�','kf','�۷�',t2.lb) lbmc,");
		sql.append(" decode(t2.lb, 'jf', '+' || t2.fz, 'kf', '-' || t2.fz, t2.fz) fz,");
		sql.append(" t2.gyjllbdlmc,");
		sql.append(" t2.gyjllbdldm,");
		sql.append(" t.czsj");
		sql.append(" from XG_GYGL_ZJLY_GYJCWHB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join XG_GYGL_NEW_GYJLLBDLB t2");
		sql.append(" on t.jcdm = t2.gyjllbdldm");
		sql.append(" left join xqdzb t3");
		sql.append(" on t.xq = t3.xqdm");
		sql.append(" ");
		sql.append(" ) t where 1=1 ");
		sql.append(" ");
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
		this.setClass(GyjcWhForm.class);
		this.setKey("id");
		this.setTableName("XG_GYGL_ZJLY_GYJCWHB");
	}
	
	public List<HashMap<String, String>> getJclbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_GYGL_NEW_GYJLLBDLB");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
}
