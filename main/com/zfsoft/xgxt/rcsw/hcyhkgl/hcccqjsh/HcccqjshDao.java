/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����08:52:28
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �𳵳����������ģ��
 * @�๦������: TODO(�𳵳����������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:36:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjshDao extends SuperDAOImpl<HcccqjshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("ccqjtxid");
		super.setTableName("xg_rcsw_hcyhk_hcccqjtxb");

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjshForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		sql.append(" t1.bz,t1.shzt, t1.splc, t2.xm, (case t2.xb when '1' then  '��' when '2' then 'Ů' else t2.xb end) xb, t3.bjmc,t2.sfzh, ");
		sql.append(" t2.lxdh,t2.sjhm, t31.xydm, t31.zydm,t31.xymc,t31.zymc,t2.zybj,t31.bjmc zybjmc,t2.bjdm,t2.nj, t4.guid shid,t4.gwid,t4.shr, t4.shyj, t6.mc || '[' || decode(t4.shzt, ");
		sql.append(" '0', 'δ���',  '1',  'ͨ��',  '2', '��ͨ��','3', '�˻�', '4', '������',  '5','�����') || ']' shztmc,t6.gwz,t7.xqmc,t8.lxmc hcyhklxmc, ");
		sql.append(" row_number() over(partition by t1.ccqjtxid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjtxb t1 left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append(" left join view_njxyzybj_all t31 on t2.zybj = t31.bjdm ");
		sql.append(" left join xg_xtwh_shztb t4  ");
		sql.append(" on t1.ccqjtxid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id   ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t8 on t1.hcyhklx = t8.lxdm ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		sql.append("  ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�𳵳���������д��ϸ��Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����04:45:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getHcccqjshInfo(HcccqjshForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		sql.append(" t1.bz,t1.shzt, t1.splc, t2.xm, t2.xb, t2.bjmc,t2.lxdh, t2.xydm, t2.zydm,t2.bjdm,t3.xqmc,t2.nj,t4.lxmc hcyhklxmc ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjtxb t1 left join view_xsxxb t2 on t1.xh = t2.xh  "); 
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t4 on t1.hcyhklx = t4.lxdm ");
		sql.append(" where t1.ccqjtxid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getCcqjtxid()});
	}

	/**
	 * 
	 * @����:TODO(���»𳵳˳�������д)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-24 ����02:24:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccqjtxid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateHcccqjtx(String ccqjtxid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_hcyhk_hcccqjtxb ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where ccqjtxid = ?");
		inputV[0] = shzt;
		inputV[1] = ccqjtxid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ���𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-24 ����02:22:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccqjjgid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteHcccqjtx(String ccqjtxid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_rcsw_hcyhk_hcccqjjgb ");
		sql.append(" where ccqjtxid = ? ");
		inputV[0] = ccqjtxid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
}
