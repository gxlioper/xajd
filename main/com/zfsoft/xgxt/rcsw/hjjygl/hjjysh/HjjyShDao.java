/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:38:35 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.hjjysh;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺2016-5-9 ����02:38:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjjyShDao extends SuperDAOImpl<HjjyShForm>{

	
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjyShForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	
	@Override
	public List<HashMap<String, String>> getPageList(HjjyShForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc,t3.jyyymc");
		sql.append(",t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append("decode(t6.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t6.shsj desc) rn ");
		sql.append("from XG_RCSW_HZSF_HJJYSQB t1 left join view_xsxxb t2 on t1.xh=t2.xh left join XG_RCSW_HZSF_JYYYDMB t3 on t1.JYYYDM = t3.JYYYDM");
		sql.append(" left join xg_xtwh_shztb t6 on t1.sqid = t6.ywid");
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
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
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ�������������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-5-9 ����10:36:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJyxxInfo(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc, t3.jyyymc");
		sql.append(" from XG_RCSW_HZSF_HJJYSQB t1 left join XG_RCSW_HZSF_JYYYDMB t3 on t1.jyyydm=t3.jyyydm left join xqdzb t2 on t1.xq=t2.xqdm");
		sql.append(" where t1.sqid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}

	
	
	@Override
	protected void setTableInfo() {
		super.setClass(HjjyShForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_HZSF_HJJYSQB");
	}

}
