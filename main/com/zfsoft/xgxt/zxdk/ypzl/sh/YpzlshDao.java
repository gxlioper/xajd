/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����10:08:14 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.sh;

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
 * @ʱ�䣺 2016-2-24 ����10:08:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzlshDao extends SuperDAOImpl<YpzlshForm>{
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzlshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzlshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t4.yhmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t5.xqmc,nvl(t8.xm,t10.xm) sqrxm, ");
		if("10511".equals(Base.xxdm)){
			sql.append(" t11.lbmc ytmc, ");
		}
		sql.append(" t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append(" decode(t6.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t6.shsj desc) rn ");
		sql.append(" from xg_zdgxh_ypzl_sqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join xqdzb t5 on t1.xq=t5.xqdm");
		sql.append(" left join dmk_yh t4 on t2.yhdm = t4.yhdm");
		sql.append(" left join xg_xtwh_shztb t6 on t1.sqid = t6.ywid");
		if("10511".equals(Base.xxdm)){
			sql.append(" left join xg_zxdk_ytzjlbdmb t11 on t1.ytlb = t11.lbdm");
		}		
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw left join yhb t8 on t1.sqr=t8.yhm left join view_xsbfxx t10 on t1.sqr=t10.xh left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4)");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(YpzlshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_zdgxh_ypzl_sqb");		
	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update xg_zdgxh_ypzl_sqb set shzt=?  where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,ywid});
		
	}
	
}
