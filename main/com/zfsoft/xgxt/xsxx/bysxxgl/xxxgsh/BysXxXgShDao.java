/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-10 ����11:14:47 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-10 ����11:14:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysXxXgShDao extends SuperDAOImpl<BysXxXgShForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(BysXxXgShForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(BysXxXgShForm model, User user)
			throws Exception {
		// ====================��������===================================

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================�������� end================================
		// ====================SQLƴװ================================
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb
				.append("select b.*,c.*,a.xgsj,a.sqid,e.mc,row_number() over (partition by b.ywid order by b.shsj desc) as rn  ");
		sb
				.append(" from xg_bysxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx c,xg_xtwh_spgwyh d,xg_xtwh_spgw e ");
		sb
				.append(" where a.xh=c.xh and a.sqid=b.ywid and b.gwid=d.spgw   and e.id=b.gwid ");
		String shzt = model.getShzt();

		if (shzt != null && shzt.equals("tg")) {
			sb.append(" and b.shzt='1' ");
		} else if (shzt != null && !shzt.equals("dsh")) {
			sb.append(" and (b.shzt!='0' and b.shzt!='4')");
		} else {
			sb.append(" and (b.shzt='0' or b.shzt='4')");
		}

		sb.append(" and d.spyh='");
		sb.append(user.getUserName());
		sb.append("' ) a where rn=1 ");
		sb.append(searchTjByUser);
		sb.append(searchTj);
		return getPageList(model, sb.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("");
	}

}
