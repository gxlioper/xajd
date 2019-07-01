/**
 * @����:ѧ����Ʒ��ҵ��
 * @ʱ�䣺 2014-1-10 ����04:01:59 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ��ս������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-10 ����04:01:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxjgDao extends SuperDAOImpl<YlbxjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xg_rcsw_ylbx_ylbxjgb");
		super.setKey("yljgid");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select t1.yljgid,t1.xh,t1.xn,t1.xq,t5.xqmc,t1.sqsj,t1.cbsj,t1.zjsyrxm,t1.zjh, ");
		sql.append(" t1.cbzkdm,t1.sqly,t1.sjly,t1.ylsqid,t2.xm,t2.xb,t2.bjmc, ");
		sql.append(" t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj,t2.jgmc,(t3.czqebzmc || '  ' || t7.czqebzmc || '  ' || t8.czqebzmc) bzlxmc, ");
		sql.append(" ( case when t1.czqebzdm is not null  then '��' else '��' end ) czqebzmc, ");
		sql.append(" ( case when t1.cbzkdm is not null  then '�Ѳα�' else 'δ�α�' end ) cbzkmc ");
		sql.append(" from xg_rcsw_ylbx_ylbxjgb t1 left join " );
		sql.append(" (select t1.yljgid,substr(t1.czqebzdm,'0','3') a1,substr(t1.czqebzdm,'5','3') a2,substr(t1.czqebzdm,'9','3') a3 from xg_rcsw_ylbx_ylbxjgb t1) t6 on t1.yljgid=t6.yljgid ");		
		sql.append(" left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xg_rcsw_ylbx_czqebzlxb t3 ");
		sql.append(" on t6.a1 = t3.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t7 on t6.a2 = t7.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t8 on t6.a3 = t8.czqebzdm left join xg_rcsw_ylbx_cbzklxb t4 ");
		sql.append(" on t1.cbzkdm = t4.cbzkdm left join xqdzb t5 on t1.xq = t5.xqdm ) a where 1 = 1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	/**
	 * 
	 * @����:TODO(�ж�ҽ�Ʊ��ս�������Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����11:54:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(YlbxjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_ylbxjgb where xh = ?  and xn = ? and xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;
	}
	
	
	/**
	 * 
	 * @����:TODO(�Ƿ�Ҫɾ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����02:41:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yljgid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String yljgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_ylbx_ylbxjgb where yljgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{yljgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡҽ�ƽ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����02:41:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yljgid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYljg(String yljgid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.xh xh, xsxx.xm xm from xg_rcsw_ylbx_ylbxjgb a ");
		sb.append(" ,view_xsxxb xsxx where a.xh=xsxx.xh and a.yljgid=? ");
		return dao.getMapNotOut(sb.toString(),new String[]{yljgid});
	}
	
	
	
	/**
	 * 
	 * @����:TODO(�鿴����ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����02:40:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xszbbjgId
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> viewOneYlbxjgList(String  xszbbjgId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.cbsj,t1.cbzkdm,  ");
		sql.append(" t1.czqebzdm,t1.zjsyrxm,t1.zjh,t1.qtcbzkval, ");
		sql.append(" t1.sqly,t2.xqmc "); 
		sql.append(" from xg_rcsw_ylbx_ylbxjgb t1 left join xqdzb t2 on t1.xq = t2.xqdm where t1.yljgid = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{xszbbjgId});
		
	}
	
	/**
	 * 
	 * @����:TODO(�õ���ǰѧ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:16:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select xqmc from xqdzb where xqdm=? ");
		String xqmc = dao.getOneRs(sql.toString(), new String[] { model.getXq()}, "xqmc");
		return xqmc;
	}
	
}
