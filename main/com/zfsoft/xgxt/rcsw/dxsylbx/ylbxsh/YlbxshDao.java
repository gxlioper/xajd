/**
 * @����:ѧ����Ʒ��ҵ��
 * @ʱ�䣺 2014-1-10 ����03:02:24 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ�����˹���ģ��
 * @�๦������: TODO(ҽ�Ʊ������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-10 ����03:02:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxshDao extends SuperDAOImpl<YlbxshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("ylsqid");
		super.setTableName("xg_rcsw_ylbx_ylbxsqb");

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.ylsqid,t1.xh,t1.xn,t1.xq,t1.cbsj, ");
		sql.append(" t1.czqebzdm,t1.zjsyrxm,t1.zjh,t1.qtcbzkval, ");
		sql.append(" ( case when t1.cbzkdm is not null  then '�Ѳα�' else 'δ�α�' end ) cbzkmc, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm,t2.xb,t2.bjmc,t2.lxdh, "); 
		sql.append(" t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjdm,t2.nj,t4.guid shid, ");
		sql.append(" t4.gwid,t4.shr,t4.shyj,t7.xqmc, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', 'δ���', '1', ");
		sql.append(" 'ͨ��','2','��ͨ��','3','�˻�','4','������', '5', ");
		sql.append(" '�����') || ']' shztmc,t6.gwz,row_number() over(partition by t1.ylsqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_ylbx_ylbxsqb t1 left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.ylsqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh = '"+user.getUserName()+"'   ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:TODO(�õ�ҽ�Ʊ��������ϸ��Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����02:59:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String>  getYlbxshInfo(YlbxshForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.cbsj, ");
		sql.append(" t1.czqebzdm,t1.zjsyrxm,t1.zjh,t1.qtcbzkval, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xqmc "); 
		sql.append(" from xg_rcsw_ylbx_ylbxsqb t1 left join xqdzb t2 on t1.xq = t2.xqdm where t1.ylsqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getYlsqid()});
	}
	
	/**
	 * 
	 * @����:TODO(����ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����08:59:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCzqebzrymcList(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select czqebzmc  from xg_rcsw_ylbx_czqebzlxb  where czqebzdm in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		return dao.getList(sql.toString(),params.toArray(new String[]{}),new String[] { "czqebzmc" });
	}

	/**
	 * 
	 * @����:TODO(�α�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����09:35:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzkdmcsList(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select cbzkmc from xg_rcsw_ylbx_cbzklxb  where cbzkdm in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		return dao.getList(sql.toString(),params.toArray(new String[]{}),new String[] { "cbzkmc" });
	}
	
	
	/**
	 * 
	 * @����:TODO(����ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����02:55:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYlbxsq(String bbsqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_ylbx_ylbxsqb ");
		sql.append(" set ");
		sql.append(" shzt = ? ");
		sql.append(" where ylsqid = ? ");
		inputV[0] = shzt;
		inputV[1] = bbsqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����02:56:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bbsqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteYlbxjg(String ylsqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcsw_ylbx_ylbxjgb ");
		sql.append(" where ylsqid = ? ");
		inputV[0] = ylsqid;
		return dao.runDelete(sql.toString(),inputV)>=0 ? true:false;
	}
}
