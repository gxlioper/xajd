/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:30:13 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ�����
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:30:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdjgDao extends SuperDAOImpl<LstdjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LstdjgForm t)
			throws Exception {
		return null;
	}

	/**
	 * �����ѯ
	 */
	public List<HashMap<String, String>> getPageList(LstdjgForm t, User user)
			throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.jgid,t1.xh,t1.xn,t1.xq,(select xqmc from xqdzb t4 where t1.xq=t4.xqdm) xqmc,t1.lxdm, ");
		sql.append(" t1.sjly,t1.sqsj,t1.sqly,t2.xm, t2.xb,t2.xymc,t2.zymc, ");
		sql.append(" t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj,t3.lxmc from xg_rcsw_lstd_jgb t1 ");
		sql.append("  left join view_xsbfxx t2 on t1.xh=t2.xh left join xg_rcsw_lstd_lxwhb t3 on t1.lxdm=t3.lxdm ) a where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_lstd_jgb");
		super.setKey("jgid");
	}
	
	/**
	 * 
	 * @����:�жϽ�����е�ѧ���Ƿ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����10:37:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(LstdjgForm model) {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select count(1) num from xg_rcsw_lstd_jgb where xh= ? and xn= ? and xq= ? ");
		params.add(model.getXh());
		params.add(model.getXn());
		params.add(model.getXq());
		
		if(!StringUtils.isBlank(model.getJgid())){
			sql.append(" and jgid<> ?");
			params.add(model.getJgid());
		}
		
		return dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");

	}
	
	
	/**
	 * 
	 * @����:����ͨ�������ݲ������޸�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����10:44:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String jgid){
		
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_lstd_jgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
		
	}
	
	
	/**
	 * 
	 * @����:���������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-25 ����10:48:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jgid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	
	public Map<String, String> viewOneLstdjgList(String jgId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jgid,t1.xh,t1.lxdm,t1.sqly,t1.sqsj,t1.bz,t2.lxmc ");
		sql.append(" from xg_rcsw_lstd_jgb  t1 left join xg_rcsw_lstd_lxwhb t2  on t1.lxdm = t2.lxdm  ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{jgId});
	}
	
	
	public HashMap<String, String> getBbjg(String jgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_lstd_jgb a");
		sb.append(",view_xsbfxx xsxx where a.xh=xsxx.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}

	/**
	 * @throws Exception  
	 * @����:����ѧ��ѧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-12-4 ����11:19:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int delForXhxnxq(String xh, String xn, String xq) throws Exception {
		
		String sql = "delete from xg_rcsw_lstd_jgb where xh = ? and xn = ? and xq = ?" ; 
		
		return dao.runDelete(sql, new String[]{xh,xn,xq});
	}
	
}
