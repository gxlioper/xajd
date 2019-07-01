/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-2 ����10:29:43 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-4-2 ����10:29:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BxbxshDao extends SuperDAOImpl<BxbxshForm>{
	

	@Override
	public List<HashMap<String, String>> getPageList(BxbxshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(BxbxshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from (select t1.*,");
		sql.append("t4.guid shid,t4.shzt shztx,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append("decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from view_rcsw_bxbxsq t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join VIEW_NJXYZYBJ_all t3 on t2.bjdm = t3.bjdm");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(" ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	
	@Override
	protected void setTableInfo() {
		super.setClass(BxbxshForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_BXGL_BXBXSQB");
		
	}

}
