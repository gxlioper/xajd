/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:38:35 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����02:38:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwzxKqshDao extends SuperDAOImpl<ZwzxKqshForm>{

	
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqshForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwzxKqshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append(" select t1.*,t1.bjdm zybj,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc, ");
		sql.append("t4.guid shid,t4.shzt shztx,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append("decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(",t7.xm jlrxm ");
		if("2297".equals(Base.xxdm)){
			sql.append(" ,nvl(t8.rs,0) ydrsszly ");
		}
		sql.append("from XG_RCSW_ZXKQ_SQB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
		sql.append(" left join fdyxxb t7 on t1.jlr=t7.zgh ");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		if("2297".equals(Base.xxdm)){
			sql.append(" left join (select count(1) rs, bjdm from view_xsxxb group by bjdm) t8");
			sql.append(" on t1.bjdm = t8.bjdm");
		}
		sql.append(" where t5.spyh ='" + user.getUserName() + "' ");
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
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����10:36:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKqshInfo(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t2.nj,t2.xydm,");
		sql.append(" t2.xymc,t2.zydm,t2.zymc,t2.bjmc,t3.lxmc cclxmc, ");
		sql.append(" decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc,t4.xm jlrxm from XG_RCSW_ZXKQ_SQB t1 left join VIEW_NJXYZYBJ_all t2 on t1.bjdm = t2.bjdm left join XG_RCSW_ZWZXKQ_CCLXB");
		sql.append(" t3 on t1.cclxdm=t3.lxdm");
		sql.append(" left join fdyxxb t4 on t1.jlr=t4.zgh ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { sqid });
	}

	
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZwzxKqshForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_ZXKQ_SQB");
	}

}
