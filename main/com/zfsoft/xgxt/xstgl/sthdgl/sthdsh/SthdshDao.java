/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-28 ����04:10:55 
 */  
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsh;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-7-28 ����04:10:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SthdshDao extends SuperDAOImpl<SthdshForm>{


	
	@Override
	public List<HashMap<String, String>> getPageList(SthdshForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(SthdshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,");
		sql.append("t2.nj,t2.xb,t2.zybj,t2.zybjmc,t2.mz,t2.mzdm,t2.zzmm,t2.zzmmmc,t2.sydm1 sydm,t2.symc1 symc, ");
		sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append("decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz, ");
	    sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.fwdd fwddxxdz, ");
		sql.append(" row_number() over(partition by t1.hdid order by t4.shsj desc) rn ");
		sql.append("from xg_sthd_dj t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.hdid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" where t5.spyh ='"+ user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
		
	}
	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-28 ����06:23:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getHdshInfo(SthdshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,");
		sql.append("t2.nj,t2.xb,t2.zybj,t2.zybjmc,t2.mz,t2.mzdm,t2.zzmm,t2.zzmmmc,t2.sydm1 sydm,t2.symc1 symc, ");
		sql.append(" decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc from xg_sthd_dj t1 ");
		sql.append(" left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" where t1.hdid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getHdid() });
	}
	/**
	 * 
	 * @����:���������¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-28 ����06:57:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param tzhxmdm
	 * @param shzt
	 * @param dqxmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjl(String ywid, String shzt,String yxgs) throws Exception{
		String sql = "update xg_sthd_dj set shzt=? ,yxgs=? where hdid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,yxgs,ywid});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(SthdshForm.class);
		super.setKey("hdid");
		super.setTableName("xg_sthd_dj");
		
	}

}
