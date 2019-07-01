/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-4-19 ����11:42:00 
 */  
package com.zfsoft.xgxt.szdw.bfjs.bfjsgl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��罨��dao(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-4-19 ����11:42:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BfjsglDao extends SuperDAOImpl<BfjsglForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BfjsglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BfjsglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xydm,t2.nj,t2.bjmc,t2.xymc,t2.zydm,t2.zymc,count(t3.xh) bjrs,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '1' and t1.jcid = b.jcid)) zcs,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '2' and t1.jcid = b.jcid)) zds,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '3' and t1.jcid = b.jcid)) sks,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '4' and t1.jcid = b.jcid)) wzxs");
		sql.append(" from XG_SZDW_BFJS_KQJCB t1");
		sql.append(" left join view_njxyzybj t2 on t1.bjdm = t2.bjdm");
		sql.append(" left join view_xsbfxx t3 on t1.bjdm = t3.bjdm");
		sql.append(" group by t1.jcid,t1.bjdm,t1.jcrq,t2.xydm,t2.nj,t2.bjmc,t2.xymc,t2.zydm,t2.zymc order by t1.jcrq desc");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @����:��ȡ�༶�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-21 ����11:23:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(BfjsglForm t,User user) throws Exception{
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1","xydm", "bjdm");
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.* from view_njxyzybj a where not exists (select 1 from XG_SZDW_BFJS_KQJCB b where a.bjdm = b.bjdm and b.jcrq = '"+t.getJcrq()+"')");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
	
		return getPageList(t, sql.toString(), inputValue);
	}
	
	/** 
	 * @����:��ȡѧ����Ϣ�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-21 ����02:29:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxxList(BfjsglForm model, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhs();		
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.nj,a.xb,a.xymc,a.zymc,");
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where a.bjdm='" + model.getBjdm() + "'");

		if(xhs.length>0){
			sql.append(" and a.xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhs[i]+"' ");
			}
			sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(BfjsglForm.class);
		super.setKey("jcid");
		super.setTableName("XG_SZDW_BFJS_KQJCB");
		
	}
	
	/**
	 * @throws SQLException  
	 * @����:������������ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-19 ����02:42:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertJcmx(List<String[]> list,BfjsglForm form) throws SQLException{
		String[] sqr = new String[list.size()];
		boolean flg = false;
		for(int i = 0;i<sqr.length;i++){
			String[] str = list.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append("insert into xg_szdw_bfjs_kqjcmxb(xh,jclx,kqlx,jcid) values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+form.getJcid()+"')");
			sqr[i] = sb.toString();
		}
		int []res = dao.runBatch(sqr);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/** 
	 * @����:�жϸð༶�ڸü�������Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-24 ����08:34:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isBjExist(BfjsglForm form) {
		String sql = "select count(1) num from XG_SZDW_BFJS_KQJCB where bjdm = ? and jcrq = ?";
		String num = dao.getOneRs(sql, new String[]{form.getBjdm(),form.getJcrq()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���ȡ������Ա��Ϣ[���ţ�1282]
	 * @���ڣ�2017-4-24 ����09:55:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKqRyxxList(BfjsglForm form){
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.*,decode(qqlxdm,'cq','����','qj','���','qq','ȱ��','cd','�ٵ�','zt','����') qqlxmc from ");
		//�������ʱ���ȱ�����
		sb.append("(select a.jcid,a.bjdm,a.jcrq,b.xh,c.xm,decode(b.jclx,'1','zc','2','zd','3','sk','4','wzx')jclx,decode(b.kqlx,'1','qj','2','qq','3','cd','4','zt')qqlxdm from XG_SZDW_BFJS_KQJCB a left join xg_szdw_bfjs_kqjcmxb b on a.jcid = b.jcid left join view_xsbfxx c on b.xh = c.xh where a.jcid = ? ");	
		//��ٳ�����Ա
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'zc' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '1')");
		sb.append(" and a.jcid = ? ");		
		//���������Ա
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'zd' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '2')");
		sb.append(" and a.jcid = ? ");
		//�Ͽγ�����Ա
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'sk' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '3')");
		sb.append(" and a.jcid = ? ");
		//����ϰ������Ա
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'wzx' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '4')");
		sb.append(" and a.jcid = ? ) a order by xh asc");
		return dao.getListNotOut(sb.toString(), new String[]{form.getJcid(),form.getJcid(),form.getJcid(),form.getJcid(),form.getJcid()});
	}
	
	/** 
	 * @����:��ȡmodel(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-24 ����11:11:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * BfjsglForm �������� 
	 * @throws 
	 */
	public HashMap<String,String> getModelMap(BfjsglForm form){
		String sql = "select a.*,b.bjmc from XG_SZDW_BFJS_KQJCB a left join view_njxyzybj b on a.bjdm = b.bjdm where a.jcid = ?";
		return dao.getMapNotOut(sql.toString(), new String[]{form.getJcid()});
	}
	
	/**
	 * @throws Exception  
	 * @����:ɾ����罨��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-24 ����04:14:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delBfjs(String[] ids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_szdw_bfjs_kqjcmxb where jcid in (");
		for(int i = 0;i<ids.length;i++){
			if(i != ids.length - 1){
				sb.append("?,");
			}else{
				sb.append("?)");
			}
		}
		return dao.runUpdate(sb.toString(), ids);
	}
	
	/**
	 * @throws SQLException  
	 * @����:����ѧ�š�jcID���������ɾ��������ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-25 ����08:57:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public void delXh(List<String[]> list) throws SQLException{
		String sql = "delete from xg_szdw_bfjs_kqjcmxb where jcid = ? and jclx = ? and xh = ?";
		dao.runBatch(sql, list);
	}
	
}
