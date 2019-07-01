/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-4 ����06:12:13 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-11-4 ����06:12:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjcxDao extends SuperDAOImpl<TjcxModel>{
	
	CsszService csszService = new CsszService();
	String pjzq = csszService.getCsz("pjzq");

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TjcxModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TjcxModel t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		if("jxjlqExcel".equals(t.getType())){
			sql.append(" select bjdm,bjmc,xydm,xymc,xh,xm,sum(xmje) xmje from ( ");
		}
		sql.append(" select rownum r, t1.* from ( ");
		sql.append(" select a.id,a.xn,c.xsxh,d.sx,(select xqmc from xqdzb c where a.xq=c.xqdm) xqmc,a.xq, ");
		sql.append(" a.xn||(select xqmc from xqdzb c where a.xq=c.xqdm) pjzq, ");
		sql.append(" a.xh,b.xm,a.cpnj,a.cpxydm xydm,a.cpxydm,a.cpzydm,a.cpbjdm bjdm,a.cpbjdm,a.cpxymc,a.xmmc,");
		sql.append(" a.sqsj hdsj,a.xmje,a.lxdm xxmlx,a.xzdm xxmxz,b.zybjmc bjmc,b.xymc, ");
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz c where a.xzdm=c.xmxzdm) xmxzmc, ");
		sql.append(" (select xmlxmc from xg_pjpy_new_xmlx c where a.lxdm=c.xmlxdm) xmlxmc ");
		sql.append(" from xg_pjpy_new_pjjgb a left join view_xsxxb b on a.xh=b.xh ");
		sql.append(" left join xg_pjpy_new_pjxmb c on a.xmdm=c.xmdm left join xg_xtwh_bmsxb d on a.cpxydm=d.bmdm");
		sql.append(" order by to_number(d.sx) asc,to_number(c.xsxh) asc,a.xn,a.xq desc)t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		if("jxjtjExcel".equals(t.getType())){
			sql.append("order by xmmc,bjmc,xm ");
		}
		if("jxjlqExcel".equals(t.getType())){
			sql.append(") group by xydm,xymc,bjdm,bjmc,xh,xm order by xymc,bjmc,xm ");
		}
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @����:��Ŀ���ͻ�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-2 ����03:49:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param xmlx
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getXmleList(String xn , String xq , String[] xmlx ,  String[] xydm, String[] xmxz, String[] xmmc, User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if("1".equals(pjzq)){ 
			params.add(xq);
		}
		//params.add(xmlx);
		sql.append("select b.xmmc xmmc ,xsxh, sum(b.rs) rs from  (");
		sql.append("select a.xmmc ,a.lxdm,d.xsxh,");
		sql.append(" count(1) rs ");
	 	sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh))");
		sql.append(" bjdm from xg_pjpy_new_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join  xg_pjpy_new_xmlx b on a.lxdm = b.xmlxdm");
		sql.append(" inner join xg_pjpy_new_xmxz c on a.xzdm = c.xmxzdm");
		sql.append(" left join (select * from xg_pjpy_new_pjxmb where xn || xq = (select xn || xq from xg_pjpy_new_csszb)) d on  a.xmmc = d.xmmc ");
		sql.append(" where a.xn = ? ");
		if("1".equals(pjzq)){
			sql.append(" and a.xq = ? ");
		}
		sql.append("and  b.xmlxdm in( ");
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlx[i]);
		}
		sql.append(")");
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and c.xmxzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append("group by a.xmmc,d.xsxh,a.lxdm) b  group by xmmc,xsxh order by to_number(xsxh) ");

		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "xmmc", "rs"});
	}
	
	/**
	 * 
	 * @����:����Ա����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-3 ����09:12:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param xmlx
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getHjmdList(String xn , String xq , String[] xmlx , String[] xydm, String[] xmxz, String[] xmmc ){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if("1".equals(pjzq)){
			params.add(xq);
		}
		//params.add(xmlx);
		
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.xm xm");
		sql.append(" from (select a.xmmc, a.cpxymc xymc, c.xm");
		sql.append(" from XG_PJPY_NEW_PJJGB a");
		sql.append(" inner join xg_pjpy_new_xmlx b");
		sql.append(" on a.lxdm = b.xmlxdm");
		sql.append("  inner join xsxxb c on a.xh=c.xh ");
		sql.append(" inner join xg_pjpy_new_xmxz d on a.xzdm=d.xmxzdm");
		sql.append(" where a.xn = ? ");
		if("1".equals(pjzq)){
			sql.append(" and a.xq = ? ");
		}
			//append(" and b.xmlxmc = ?");
		
		sql.append("and  b.xmlxdm in( ");
			for (int i = 0; i < xmlx.length; i++) {
				if(i != 0)
					sql.append(",");
				sql.append("?");
				params.add(xmlx[i]);
			}
			sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and a.xzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and a.xzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" ) b order by length(xm) ");
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] {"xymc" , "xmmc", "xm"});
	}
	
	/**
	 * 
	 * @����:ѧԺ������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-2 ����04:16:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmlx
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getXyList(String xn, String xq ,String[] xmlx, String[] xydm, String[] xmxz, String[] xmmc, User user) {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if("1".equals(pjzq)){
			params.add(xq);
		}
		//params.add(xmlx);
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.rs rs");
		sql.append(" from (select a.xmmc, a.xymc, count(1) rs");
		sql.append(" from (select t1.*,t2.xymc from ");
		sql.append(" (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh))");
		sql.append(" bjdm from xg_pjpy_new_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_pjpy_new_xmlx b");
		sql.append(" on a.lxdm = b.xmlxdm");
		sql.append(" inner join xg_pjpy_new_xmxz c");
		sql.append(" on a.xzdm = xmxzdm");
		
		sql.append(" where a.xn = ? ");
		if("1".equals(pjzq)){
			sql.append(" and a.xq = ? ");
		}
			//append(" and b.xmlxdm = ?")
		
		sql.append("and  b.xmlxdm in( ");
		
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlx[i]);
		}
		sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and c.xmxzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" group by a.xymc, a.xmmc) b").append(" where rs is not null and rs<>0 ");
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "xymc" , "xmmc", "rs"});
	}
	/**
	 * �༶����������������óְҵ����ѧԺ��
	 */
	private StringBuilder getBjListSql_12072(String xn, String xq ,String[] xmlx, String[] xydm, String[] xmxz, String[] xmmc, List<HashMap<String,String>> hjxmList, User user, ArrayList<String> params) {
		StringBuilder hjxmSql = new StringBuilder();
		for (int i = 0; i < hjxmList.size(); i++) {
			HashMap<String, String> hjxmMap = hjxmList.get(i);
			// ���
			hjxmSql.append(" cast(sum(case when a.xmmc='"+hjxmMap.get("xmmc")+"' then a.bjxmje else 0 end) as decimal(10,2)) bjxmje"+i+", ");
			// ����
			hjxmSql.append(" sum(case when a.xmmc='"+hjxmMap.get("xmmc")+"' then a.bjxmrs else 0 end) bjxmrs"+i+", ");
		}
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();

		params.add(xn);
		if("1".equals(pjzq)){
			params.add(xq);
		}
		//params.add(xmlx);
		
		sql.append(" select ");
		sql.append(hjxmSql);
		sql.append(" a.bjmc from ( ");
		
		sql.append("select b.xmmc, b.bjmc, b.bjxmrs, b.bjxmje");
		sql.append(" from (select a.xmmc, a.bjmc, count(1) bjxmrs, sum(a.xmje) bjxmje");
		sql.append(" from (select t1.*,t2.xymc,t2.bjmc from ");
		sql.append(" (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh))");
		sql.append(" bjdm from xg_pjpy_new_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_pjpy_new_xmlx b");
		sql.append(" on a.lxdm = b.xmlxdm");
		sql.append(" inner join xg_pjpy_new_xmxz c");
		sql.append(" on a.xzdm = xmxzdm");
		
		sql.append(" where a.xn = ? ");
		if("1".equals(pjzq)){
			sql.append(" and a.xq = ? ");
		}
			//append(" and b.xmlxdm = ?")
		
		sql.append("and  b.xmlxdm in( ");
		
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlx[i]);
		}
		sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and c.xmxzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" group by a.bjmc, a.xmmc) b").append(" where b.bjxmrs is not null and b.bjxmrs<>0 ");
		sql.append(" order by b.bjmc ");
		sql.append(" ) a group by a.bjmc ");
		return sql;
	}
	/**
	 * �༶�����������ϼƣ�������óְҵ����ѧԺ��
	 */
	public HashMap<String,String> getBjSum_12072(String xn, String xq ,String[] xmlx, String[] xydm, String[] xmxz, String[] xmmc, List<HashMap<String,String>> hjxmList, User user) {
		DAO dao = DAO.getInstance();
		ArrayList<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		StringBuilder hjxmSql = new StringBuilder();
		for (int i = 0; i < hjxmList.size(); i++) {
			// ���
			hjxmSql.append(" cast(sum(bjxmje"+i+") as decimal(10,2)) bjxmjesum"+i+", ");
			// ����
			hjxmSql.append(" sum(bjxmrs"+i+") bjxmrssum"+i);
			if(i < hjxmList.size() - 1){
				hjxmSql.append(", ");
			}
		}
		sql.append(hjxmSql);
		sql.append(" from ( ");
		sql.append(getBjListSql_12072(xn, xq, xmlx, xydm, xmxz, xmmc, hjxmList, user, params));
		sql.append(" ) a ");
		return dao.getMapNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * �༶����������������óְҵ����ѧԺ��
	 */
	public List<HashMap<String,String>> getBjList_12072(String xn, String xq ,String[] xmlx, String[] xydm, String[] xmxz, String[] xmmc, List<HashMap<String,String>> hjxmList, User user) {
		DAO dao = DAO.getInstance();
		ArrayList<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(getBjListSql_12072(xn, xq, xmlx, xydm, xmxz, xmmc, hjxmList, user, params));
		return dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	/**
	 * ����Ŀ������˳�����У�������óְҵ����ѧԺ��
	 */
	public List<HashMap<String,String>> getHjxmList_12072(String xn, String xq ,String[] xmlx, String[] xydm, String[] xmxz, String[] xmmc, User user) {
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if("1".equals(pjzq)){
			params.add(xq);
		}
		//params.add(xmlx);
		
		sql.append("select a.xmmc,b.xsxh from ( ");
		sql.append("select distinct b.xmmc ");
		sql.append(" from (select a.xmmc, a.bjmc, count(1) bjxmrs, sum(a.xmje) bjxmje");
		sql.append(" from (select t1.*,t2.xymc,t2.bjmc from ");
		sql.append(" (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh))");
		sql.append(" bjdm from xg_pjpy_new_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_pjpy_new_xmlx b");
		sql.append(" on a.lxdm = b.xmlxdm");
		sql.append(" inner join xg_pjpy_new_xmxz c");
		sql.append(" on a.xzdm = xmxzdm");
		
		sql.append(" where a.xn = ? ");
		if("1".equals(pjzq)){
			sql.append(" and a.xq = ? ");
		}
			//append(" and b.xmlxdm = ?")
		
		sql.append("and  b.xmlxdm in( ");
		
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlx[i]);
		}
		sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and c.xmxzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" group by a.bjmc, a.xmmc) b").append(" where b.bjxmrs is not null and b.bjxmrs<>0 ");
		sql.append(" ) a left join xg_pjpy_new_pjxmb b on a.xmmc=b.XMMC ");
		sql.append(" order by b.xsxh ");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	
	/**
	 * @����:��Ŀ���ʻ��������Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-7 ����08:58:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmxz
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getXmleList_11458(String xn , String[] xmxz ,  String[] xydm){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		sql.append("select b.xmmc xmmc , b.rs rs from  (");
		sql.append("select a.xmmc , count(1) rs from XG_PJPY_NEW_PJJGB a inner join  xg_pjpy_new_xmxz b on a.xzdm = b.xmxzdm ");
		sql.append("where a.xn = ? ");
		sql.append("and  b.xmxzdm in( ");
		for (int i = 0; i < xmxz.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmxz[i]);
		}
		sql.append(")");
		sql.append("group by a.xmmc) b");

		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "xmmc", "rs"});
	}
	
	/**
	 * �㽭��ѧ����֤���ӡ����ҳ��
	 */
	public List<HashMap<String, String>> viewZsdy(TjcxModel t, User user) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();

		sql.append("select t1.*,t1.xn||' '||t1.xqmc pjzq from (select * from view_new_pjpy_zjdx_zsdy order by XSXH asc,xm asc) t1  where 1=1 ");
		sql.append(" and t1.xn = '"+CsszService.getPjzq().get("xn")+"' ");
		if(!StringUtils.isNull(CsszService.getPjzq().get("cxxq"))){
			sql.append(" and t1.xq = '"+CsszService.getPjzq().get("cxxq")+"' ");
		}
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:����Ա�������Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-7 ����08:58:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmxz
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getHjmdList_11458(String xn , String[] xmxz , String[] xydm ){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.xm xm, b.bjmc");
		sql.append(" from (select a.xmmc, a.cpxymc xymc, c.xm, c.bjmc");
		sql.append(" from XG_PJPY_NEW_PJJGB a");
		sql.append(" inner join xg_pjpy_new_xmxz b");
		sql.append(" on a.xzdm = b.xmxzdm");
		sql.append("  inner join view_xsxxb c on a.xh=c.xh ");
		sql.append(" where").
			append(" a.xn = ?");
		
		sql.append("and  b.xmxzdm in( ");
			for (int i = 0; i < xmxz.length; i++) {
				if(i != 0)
					sql.append(",");
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		sql.append(" ) b ");
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] {"xymc" , "xmmc", "xm", "bjmc"});
	}
	
	/**
	 * 
	 * @����:���л�ѧԺ���Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-7 ����08:58:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmxz
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllXyList_11458(String xn , String[] xmxz ){
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		
		sql.append("select distinct xydm,xymc");
		sql.append(" from (select a.cpxymc xymc, a.cpxydm xydm");
		sql.append(" from XG_PJPY_NEW_PJJGB a");
		sql.append(" inner join xg_pjpy_new_xmxz b");
		sql.append(" on a.xzdm = b.xmxzdm");
		sql.append("  inner join view_xsxxb c on a.xh=c.xh ");
		sql.append(" where").
		append(" a.xn = ?");
		
		sql.append("and  b.xmxzdm in( ");
		for (int i = 0; i < xmxz.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmxz[i]);
		}
		sql.append(")");
		
		sql.append(" ) b ");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[] {}));
	}
	
	/**
	 * 
	 * @����:ѧԺ���������Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-7 ����08:58:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmxz
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getXyList_11458(String xn, String[] xmxz, String[] xydm) {
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		sql.append("select b.xmmc xmmc, b.rs rs");
		sql.append(" from (select a.xmmc, count(1) rs");
		sql.append(" from XG_PJPY_NEW_PJJGB a");
		sql.append(" inner join xg_pjpy_new_xmxz b");
		sql.append(" on a.xzdm = b.xmxzdm");
		
		sql.append(" where").
			append(" a.xn = ?");
		
		sql.append("and  b.xmxzdm in( ");
		
		for (int i = 0; i < xmxz.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmxz[i]);
		}
		sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
//		sql.append(" group by a.cpxymc, a.xmmc) b").append(" where rs is not null and rs<>0 ");
		sql.append(" group by a.xmmc) b");
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "xmmc", "rs"});
	}
	
	/**
	 * 
	 * @����:��������ѧԺ�������Ϻ����ѧԺ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-7 ����08:58:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jtpjTj
	 * @param xn
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getJtpjList_11458(String jtpjTj, String xn, String[] xydm){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		ArrayList<String> params = new ArrayList<String>();
		sql.append(" select a.pjjtmc from xg_pjpy_jtpj_jtpjjgb a ");
		sql.append(" left join XG_PJPY_JTPJ_JTJXSZ b on (a.jxid=b.jxid) ");
		sql.append(" where b.jxmc=? and a.pdxn=? and exists ( ");
		params.add(jtpjTj);
		params.add(xn);
		sql.append(" select 1 from (select distinct a.bjdm,a.bjmc,a.xydm,a.xymc from view_xsxxb a  ");
		if (xydm != null && xydm.length > 0) {
			sql.append(" where a.xydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		sql.append(" ) c where c.bjmc=a.pjjtmc or c.xymc=a.pjjtmc ) ");
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "pjjtmc"});
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}

	/** 
	 * @����:�㽭��ѧ��ã���ͳ��������Ŀ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-3-10 ����04:13:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPjxmList(TjcxModel model) {
		
		String sql = "select * from xg_pjpy_new_pjxmb where xmdm in (select xmdm from xg_pjpy_new_rsszb where fpbl is not null) and xn = ? order by to_number(xsxh)";
		
		return dao.getListNotOut(sql, new String[]{model.getXn()});
	}

	/**
	 * @throws Exception  
	 * @����:�㽭��ѧ��ѧ���������һ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-3-10 ����04:20:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param pjxmList
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJxjmefpList(TjcxModel model,
			User user, List<HashMap<String, String>> pjxmList,String rsfpfs) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "bmdm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r, t1.* from ( ");
		sql.append("select bmdm ,xn,(select bmmc from zxbz_xxbmdm b where a.bmdm=b.bmdm) bmmc,");
		for (int i = 0; i < pjxmList.size(); i++) {
			sql.append("nvl(case when substr(max(jx"+i+"),0,1)='.'or ");
			sql.append("substr(max(jx"+i+"),0,1)='/' then '0'||max(jx"+i+") else max(jx"+i+") end,'0/0') jx"+i+",");
		}
		sql.append("nvl(sum(jje),0) jje,nvl(sum(bmtzje),0) bmtzje,zrs,ytjrs,sx from(select t1.bmdm,t4.xn,");
		for (int i = 0; i < pjxmList.size(); i++) {
			sql.append("case when t2.xmdm='"+pjxmList.get(i).get("xmdm")+"' then nvl(t1.zd3,0)||'/'||case when t1.zd1 is null then '0' else t1.zzme end else '' end jx"+i+",");
		}
		sql.append("t1.fpbl,nvl(t1.zd3 * xmje, 0) jje,case when t1.zd1 is null then '0' else t1.zzme end *xmje bmtzje,");
		sql.append("t2.xmje,nvl(t4.zrs,0) zrs,nvl(t4.ytjrs,0) ytjrs,t3.sx  from xg_pjpy_new_rsszb t1 left join xg_pjpy_new_pjxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" left join xg_xtwh_bmsxb t3 on t1.bmdm=t3.bmdm");
		sql.append(" left join (select t2.xymc,t1.xn, t2.xydm bmdm,nvl(count(1),0) zrs,nvl(count(case when tjzt=1 then'1' else'' end),0) ytjrs");
		sql.append(" from (select a.xn,a.xq,a.xh,a.bjdm,b.tjzt from xg_pjpy_new_cpmdb a left join xg_zhcp_fstjjlb b");
		sql.append(" on a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm where a.bjdm is not null) t1");
		sql.append(" left join VIEW_NJXYZYBJ_ALL t2 on t1.bjdm = t2.bjdm where t1.xn ='"+model.getXn()+"'");
		sql.append(" and t2.xydm is not null");
		sql.append(" and t2.nj is not null");
		sql.append(" and t2.zydm is not null");
		sql.append(" group by t2.xymc, t2.xydm,t1.xn) t4 on t1.bmdm=t4.bmdm");
		sql.append(" where t1.fpbl is not null");
		sql.append(" and t2.xn = '"+model.getXn()+"') a  where bmdm in (select bmdm from zxbz_xxbmdm where bmlb ='5') group by bmdm,zrs,ytjrs,sx,xn order by to_number(sx)");
		sql.append(")t1 where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @����:ѧԺ������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-2 ����04:16:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xmlx
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getXyList_10335(String xn, String xq ,String[] xmlx, String[] xydm,String[] xmxz, String[] xmmc ,User user) {
		DAO dao = DAO.getInstance();
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if("1".equals(pjzq)){
			params.add(xq);
		}
		//params.add(xmlx);
		sql.append("select b.xymc xymc, b.xmmc xmmc, b.rs rs");
		sql.append(" from (select a.xmmc, nvl(a.cpxymc,a.xymc)xymc, a.cpxydm, count(1) rs, d.xsxh ");
		sql.append(" from (select * from (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh))");
		sql.append(" cpbj from xg_pjpy_new_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.cpbj =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" )a");
		sql.append(" inner join xg_pjpy_new_xmlx b");
		sql.append(" on a.lxdm = b.xmlxdm");
		sql.append(" inner join xg_pjpy_new_xmxz c");
		sql.append(" on a.xzdm = c.xmxzdm");
		sql.append(" left join (select * from xg_pjpy_new_pjxmb where xn || xq = (select xn || xq from xg_pjpy_new_csszb)) d ");
		sql.append(" on a.xmmc = d.xmmc ");
		
		sql.append(" where a.xn = ? ");
		if("1".equals(pjzq)){
			sql.append(" and a.xq = ? ");
		}
			//append(" and b.xmlxmc = ?")
		
		sql.append("and  b.xmlxdm in( ");
		
		for (int i = 0; i < xmlx.length; i++) {
			if(i != 0)
				sql.append(",");
			sql.append("?");
			params.add(xmlx[i]);
		}
		sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and c.xmxzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" group by nvl(a.cpxymc,a.xymc), a.xmmc,a.cpxydm,d.xsxh) b left join xg_xtwh_bmsxb c on b.cpxydm=c.bmdm ");
		sql.append(" where rs is not null and rs<>0 order by to_number(c.sx) asc,to_number(b.xsxh) asc");
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] { "xymc" , "xmmc", "rs"});
	}
	
	/**
	 * 
	 * @����:����Ա����_�㽭��ѧ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-3-17 ����10:26:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param xmlx
	 * @param xydm
	 * @return
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getHjmdList_10335(String xn , String xq , String[] xmlx , String[] xydm, String[] xmxz, String[] xmmc){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> params = new ArrayList<String>();
		params.add(xn);
		if("1".equals(pjzq)){
			params.add(xq);
		}
		//params.add(xmlx);
		
		sql.append("select b.xymc xymc, b.xmmc xmmc,b.xm,");
		sql.append("case when length(b.xm)=2 then substr(b.xm,1,1)||'  '|| substr(b.xm,2) else b.xm end ||'('||b.xh||')'xmxh, ");
		sql.append("case when length(b.xm)=2 then substr(b.xm,1,1)||'  '|| substr(b.xm,2) else b.xm end xmxm");
		sql.append(" from (select a.xmmc, a.cpxymc xymc, c.xm,c.xh");
		sql.append(" from XG_PJPY_NEW_PJJGB a");
		sql.append(" inner join xg_pjpy_new_xmlx b");
		sql.append(" on a.lxdm = b.xmlxdm");
		sql.append(" inner join xsxxb c on a.xh=c.xh ");
		sql.append(" inner join xg_pjpy_new_xmxz d");
		sql.append(" on a.xzdm = d.xmxzdm");
		sql.append(" where a.xn = ? ");
		if("1".equals(pjzq)){
			sql.append(" and a.xq = ? ");
		}
			//append(" and b.xmlxmc = ?");
		
		sql.append("and  b.xmlxdm in( ");
			for (int i = 0; i < xmlx.length; i++) {
				if(i != 0)
					sql.append(",");
				sql.append("?");
				params.add(xmlx[i]);
			}
			sql.append(")");
		
		
		if (xydm != null && xydm.length > 0) {
			sql.append(" and a.cpxydm in (");
			for (int i = 0; i < xydm.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xydm[i]);
			}
			sql.append(")");
		}
		
		if (xmxz != null && xmxz.length > 0) {
			sql.append(" and d.xmxzdm in (");
			for (int i = 0; i < xmxz.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmxz[i]);
			}
			sql.append(")");
		}
		
		if (xmmc != null && xmmc.length > 0) {
			sql.append(" and a.xmmc in (");
			for (int i = 0; i < xmmc.length; i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("?");
				params.add(xmmc[i]);
			}
			sql.append(")");
		}
		
		sql.append(" order by length(c.xm),xm ) b  ");
		
		return dao.rsToVator(sql.toString(), params.toArray(new String[] {}),
				new String[] {"xymc" , "xmmc", "xm","xmxh","xmxm"});
	}
	
	/**
	 * ���Ż��ܵ���
	 */
	public List<HashMap<String, String>> getFfhzList(TjcxModel t, User user)
		throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xymc,xydm,xh,xm,bcffje,jjze,bz from ( ");
		sql.append(" select c.xymc,c.xydm,a.xh,b.xm,a.xn,d.sx, ");
		sql.append(" (case when gjjxjje > 0 and GJJXJJE > ZKZJXJJE and GJJXJJE > WSJXJZE then (jjze - (select xmje from xg_pjpy_new_pjxmb where xmmc = '���ҽ�ѧ��' and rownum=1)) / 2 ");
		sql.append(" when gjjxjje > 0 and ZKZJXJJE > GJJXJJE and ZKZJXJJE > wsjxjze then (jjze - (select xmje from xg_pjpy_new_pjxmb where xmmc = '���ҽ�ѧ��'and rownum=1)) / 2 ");
		sql.append(" when gjjxjje > 0 and wsjxjze >= GJJXJJE and wsjxjze > ZKZJXJJE then (jjze - (select xmje from xg_pjpy_new_pjxmb where xmmc = '���ҽ�ѧ��'and rownum=1)) / 2 ");
		sql.append(" else jjze / 2 end) bcffje, a.jjze, (case ");
		sql.append(" when GJJXJJE > 0 and GJJXJJE > ZKZJXJJE and GJJXJJE > WSJXJZE then '���ҽ�ѧ���ѷ���' ");
		sql.append(" when ZKZJXJJE > 0 and ZKZJXJJE > WSJXJZE and gjjxjje > 0 then '�ÿ��塢���ҽ�ѧ��(���ҽ�ѧ���ѷ���)' ");
		sql.append(" when ZKZJXJJE > 0 and ZKZJXJJE > WSJXJZE then '�ÿ��影ѧ��' ");
		sql.append(" when WSJXJZE > 0 and WSJXJZE >= GJJXJJE and gjjxjje > 0 then '���ҽ�ѧ���ѷ���' else '��' end) BZ ");
		sql.append(" from xg_pjpy_new_jxjhzb a left join xg_pjpy_new_cpmdb b on a.xh = b.xh and a.xn = b.xn ");
		sql.append(" left join view_njxyzybj_all c on b.bjdm = c.bjdm left join xg_xtwh_bmsxb d on c.xydm=d.bmdm) a where 1=1  ");
		sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb b where a.xh=b.xh and a.xn=b.xn) ");
//		sql.append(" and a.xh||a.xn in(select xh||xn from xg_pjpy_new_cpmdb)");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by to_number(a.sx) asc,xm");
	
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getGjjxjList(TjcxModel t)
	throws Exception {
	
	StringBuilder sql = new StringBuilder();
	sql.append(" select a.*,b.xm from xg_pjpy_new_pjjgb a left join xg_pjpy_new_cpmdb b on a.xh = b.xh and a.xn = b.xn ");
	sql.append(" left join view_njxyzybj_all c on b.bjdm = c.bjdm left join xg_xtwh_bmsxb d on c.xydm=d.bmdm where 1=1 and a.xmmc='���ҽ�ѧ��'  and a.xn=?");
	sql.append(" and exists (select 1 from xg_pjpy_new_cpmdb b where a.xh=b.xh and a.xn=b.xn) ");
	sql.append(" order by a.xh desc");

	return dao.getListNotOut(sql.toString(), new String[]{t.getXn()});
}
	
	
	/**
	 * 
	 * @����:��󷢷Ż��ܵ����洢����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-29 ����04:21:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean computeFfhz(String xn ,String dqxn ) throws Exception{
		return dao.runProcuder("{call xg_pj_proc_jxjhz_new(?,?)}", 
				new String[]{xn,dqxn});
	}

	/** 
	 * @����:������ݵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-6 ����05:50:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param lxmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getExport(TjcxModel model, User user,
			String lxmc) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "cpxydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		ArrayList<String> params = new ArrayList<String>();
		String[] lxmcs = lxmc.split(",");
		
		sql.append(" select rownum rn,a.cpxymc,b.xm,a.xh,a.xmmc,b.xmpy,(select c.xmywmc ");
		sql.append(" from xg_pjpy_new_pjxmb c where a.xmmc = c.xmmc and rownum = 1) xmywmc ");
		sql.append("  from (select t1.*,t2.xymc from (select a.*,nvl(a.cpbjdm,(select bjdm from xsxxb t2 where a.xh=t2.xh)) bjdm ");
		sql.append(" from xg_pjpy_new_pjjgb a) t1 left join view_njxyzybj_all t2 on t1.bjdm =t2.bjdm where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(") a left join xsxxb b on a.xh = b.xh");
		sql.append(" where a.xn || nvl(a.xq, 'on') in (select xn || xq from xg_pjpy_new_csszb) and a.lxdm in(");
		for (int i = 0; i < lxmcs.length; i++) {
			if(i==0){
				sql.append("?");
			}else{
				sql.append(",?");
			}
			params.add(lxmcs[i]);
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	
	}
	
	/**
	 * �Ϻ����ѧԺ���Ի���ѯ
	 */
	public List<HashMap<String, String>> getPageListShTyxy(TjcxModel t, User user)
	throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select rownum r,");
		sql.append(" t1.*,");
		sql.append(" case");
		sql.append(" when xmje = mo and rn = '1' then");
		sql.append(" to_char(mo)");
		sql.append(" when xmje is null then");
		sql.append(" ''");
		sql.append(" else");
		sql.append(" '�͸�'");
		sql.append(" end jgyz");
		sql.append(" from (select a.id,");
		sql.append(" a.xn,");
		sql.append(" c.xsxh,");
		sql.append(" d.sx,");
		sql.append(" (select xqmc from xqdzb c where a.xq = c.xqdm) xqmc,");
		sql.append(" a.xq,");
		sql.append(" a.xn || (select xqmc from xqdzb c where a.xq = c.xqdm) pjzq,");
		sql.append(" a.xh,");
		sql.append(" b.xm,");
		sql.append(" b.xb,");
		sql.append(" b.nj,");
		sql.append(" b.zydm,");
		sql.append(" b.xz,");
		sql.append(" b.mz,");
		sql.append(" a.cpnj,");
		sql.append(" a.cpxydm xydm,");
		sql.append(" a.cpxydm,");
		sql.append(" a.cpzydm,");
		sql.append(" a.cpbjdm bjdm,");
		sql.append(" a.cpbjdm,");
		sql.append(" a.cpxymc,");
		sql.append(" a.xmmc,");
		sql.append(" a.sqsj hdsj,");
		sql.append(" a.xmje,");
		sql.append(" a.lxdm xxmlx,");
		sql.append(" a.xzdm xxmxz,");
		sql.append(" (select xmxzmc from xg_pjpy_new_xmxz c where a.xzdm = c.xmxzdm) xmxzmc,");
		sql.append(" (select xmlxmc from xg_pjpy_new_xmlx c where a.lxdm = c.xmlxdm) xmlxmc,");
		sql.append(" max(to_number(a.xmje)) over(partition by a.xh,a.xn,a.xq order by a.xh,a.xn,a.xq) mo,");
		sql.append("  row_number() over(partition by a.xh, a.xmje order by a.sqsj desc) rn");
		sql.append(" from xg_pjpy_new_pjjgb a");
		sql.append(" left join view_xsxxb b");
		sql.append(" on a.xh = b.xh");
		sql.append(" left join xg_pjpy_new_pjxmb c");
		sql.append(" on a.xmdm = c.xmdm");
		sql.append(" left join xg_xtwh_bmsxb d");
		sql.append(" on a.cpxydm = d.bmdm");
		sql.append(" order by a.xh asc, to_number(d.sx) asc,");
		sql.append(" to_number(c.xsxh) asc,");
		sql.append(" a.xn,");
		sql.append(" a.xq desc) t1");
		sql.append(" where 1=1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 *  ����ѧ�꣬ѧԺ�����ȡ�༶����������¼.
	 *  <p>��̶��ѧ</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-19 20:14
	 * @param xn
	 * @param xydm
	 * @param xmmc
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getGrpjList(String xn, String xydm,String xmmc) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t1.xh,t2.xm,t1.xn,t1.xmdm,t1.xmmc FROM XG_PJPY_NEW_PJJGB t1 ");
		sql.append("LEFT JOIN XSXXB t2 ON t1.xh = t2.xh ");
		sql.append("WHERE t1.XN = ? AND t1.CPXYDM = ? ");
		sql.append("AND t1.XMMC = ? ");

		return dao.getListNotOut(sql.toString(),new String[] {xn,xydm,xmmc});
	}

	/**
	 *  ����ѧ�꣬ѧԺ�����ȡ����������¼.
	 *  <p>��̶��ѧ</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-19 20:14
	 * @param xn
	 * @param xydm
	 * @param jxmc
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getBjpjList(String xn, String xydm,String jxmc) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.PJJTID,a.PJJTMC,a.JXID,c.JXMC,b.nj,b.zymc FROM XG_PJPY_JTPJ_JTPJJGB a ");
		sql.append("LEFT JOIN VIEW_NJXYZYBJ b ON a.PJJTID = b.BJDM ");
		sql.append("LEFT JOIN XG_PJPY_JTPJ_JTJXSZ c ON a.JXID = c.JXID ");
		sql.append("WHERE a.JTPJDW = 'bj'AND a.PDXN = ? AND b.XYDM = ? ");
		sql.append("AND c.JXMC = ? ");

		return dao.getListNotOut(sql.toString(),new String[] {xn,xydm,jxmc});
	}

	/**
	 *  ����ѧԺ�����ȡѧԺ����.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-20 10:41
	 * @param xydm
	 * @return java.lang.String
	 * @throw
	 */
	public String getXymcByXydm(String xydm) {

		String sql = "SELECT bmmc FROM ZXBZ_XXBMDM WHERE BMLB = '5' AND BMDM = ?";
		return dao.getOneRs(sql,new String[]{xydm},"bmmc");
	}

	public String[] getXymcByDms(String[] xydms) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select bmmc from ZXBZ_XXBMDM where bmdm in (");
		int length = xydms.length;
		for(int i=0;i< length-1;i++){
			sql.append("'"+xydms[i]+"',");
		}
		sql.append("'"+xydms[length-1]+"')");
		return dao.getArray(sql.toString(),new String[]{},"bmmc");
	}
}
