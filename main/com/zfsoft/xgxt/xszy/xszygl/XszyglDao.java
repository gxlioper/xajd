/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����11:25:44 
 */  
package com.zfsoft.xgxt.xszy.xszygl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-11 ����11:25:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszyglDao extends SuperDAOImpl<XszyglForm>{


	@Override
	public List<HashMap<String, String>> getPageList(XszyglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	
	@Override
	public List<HashMap<String, String>> getPageList(XszyglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t1.*,t2.bmdm xydm,t2.bmlb,(case when t2.bmlb='1' then '��' else '��' end)sfjg, t2.bmmc,t3.zzmmmc,t4.bmmc bjyxmc,t5.ppqss,t5.ppqsdm,t5.ppqs,(case when t5.ppqs is null then '��' else '��' end)sfypp from XG_XSZY_XSZYJBXXB t1 left join zxbz_xxbmdm t2 on t1.dwdm=t2.bmdm ");
		sql.append(" left join zzmmdmb t3 on t1.zzmmdm=t3.zzmmdm");
		sql.append(" left join(select distinct(xydm) bmdm,xymc bmmc from view_njxyzybj_all");
		sql.append(" union all ");
		sql.append(" select bmdm,bmmc from xg_xszy_tsbmb where bmdm!='90')t4 on t1.bjyx=t4.bmdm ");
		sql.append(" left join (select a.zgh,b.nj,count(1) ppqss,wm_concat(c.lddm||'-'||b.qsh) ppqsdm,wm_concat(c.ldmc||'-'||b.qsh) ppqs from XG_XSZY_XSZYJBXXB a left join xg_xszy_xszyqsgxb b on");
		sql.append(" a.zgh=b.zgh and a.nj=b.nj");
		sql.append(" left join xg_gygl_new_ldxxb c on b.lddm=c.lddm group by a.zgh,b.nj) t5 on t1.zgh=t5.zgh and t1.nj=t5.nj");
		sql.append(" ) t where 1=1 and nj='"+t.getNj()+"' ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by ppqs desc,bmlb,bmmc asc ");
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ѯ����֮����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����10:34:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXszy(XszyglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.bmmc dwmc,t3.zzmmmc from XG_XSZY_XSZYJBXXB t1 left join zxbz_xxbmdm t2 on t1.dwdm=t2.bmdm ");
		sql.append("left join zzmmdmb t3 on t1.zzmmdm=t3.zzmmdm");
		sql.append(" where t1.id=?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getId()});

	}
	/**
	 *�жϰ༶����֮���Ƿ��Ѵ���
	 */
	public boolean isHaveXszy(XszyglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_XSZY_XSZYJBXXB where nj=? and zgh=?");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getNj(),model.getZgh() }, "num");
		return Integer.parseInt(num) > 0;
	}
	/**
	 * 
	 * @����:��Ժϵ�����Ϣ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����03:03:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean kyxbj(List<String[]> params) throws SQLException {
		String sql = "update XG_XSZY_XSZYJBXXB set kyxbj = ? where id=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @����:Ժϵ������Ϣ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����03:04:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean bjyx(List<String[]> params) throws SQLException {
		String sql = "update XG_XSZY_XSZYJBXXB set bjyx = ? where id=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	@Override
	protected void setTableInfo() {
		super.setClass(XszyglForm.class);
		super.setKey("id");
		super.setTableName("XG_XSZY_XSZYJBXXB");
		
	}

}
