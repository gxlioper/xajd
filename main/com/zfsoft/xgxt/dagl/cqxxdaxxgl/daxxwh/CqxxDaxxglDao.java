/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-17 ����02:06:17 
 */  
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ͽ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-8-25 ����06:49:51 
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CqxxDaxxglDao extends SuperDAOImpl<CqxxDaxxglForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CqxxDaxxglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(CqxxDaxxglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select* from(select t.*," +
				"case when f.count != '0' then f.count else 0 end ywhrs, " +
				"case when  f.count != '0' then cast(t.bjrs as int)-cast(f.count as int ) else t.bjrs end wwhrs " +
				" from (");
		sql.append(" select to_char(t1.nj) nj,t1.xymc," +
				"t1.xydm,t1.zymc,t1.zydm,t1.bjmc,t1.bjdm,t2.bjrs bjrs");
		sql.append(" from view_njxyzybj t1");
		sql.append(" left join (select count(1) bjrs,bjdm  from view_xsjbxx a    group by a.bjdm) t2");
		sql.append(" on t1.bjdm = t2.bjdm)t");
		sql.append(" left join (select a.bjdm, count(1) count from (" +
				" select t2.bjdm   from xg_xsxx_cqxxdaxxb t1 " +
				" left join view_xsjbxx t2 on t1.xh = t2.xh    where t1.kddh is not null) a " +
				"  group by a.bjdm) f " +
				" on t.bjdm = f.bjdm ) t ");
		sql.append(" where 1=1");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(),inputV);
	}

	
	@Override
	protected void setTableInfo() {
		super.setKey("daxxid");
		super.setTableName("XG_XSXX_CQXXDAXXB");
		super.setClass(CqxxDaxxglForm.class);
	}
	public CqxxDaxxglForm getModel() throws Exception{
		String sql = "select * from XG_XSXX_CQXXDAXXB ";
		return super.getModel(sql, new String[]{});
	}

	
	public List<HashMap<String, String>> getXsPageList(CqxxDaxxglForm t, User user) 
	throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] bjdm =t.getBjdms().split(",");
		sql.append(" select * from (select t.*,q.kddh,q.yjdz,q.yjr,q.dahh,q.xjzt," +
				"case when q.kddh is not null then '0' else '1' end ywh," +
				"q.sj,q.bz from(");
		sql.append(" select t2.bjdm,");
		sql.append(" t2.xh,");
		sql.append(" t2.xm,");
		sql.append(" t2.sydqmc syd,");
		sql.append(" t2.bjmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.sfzx ");
		sql.append(" from view_xsxxb t2");
		sql.append(" left join view_njxyzybj b ");
		sql.append(" on b.bjdm = t2.bjdm ");
		if(t.getFlag().equals("gx")){
			sql.append(" where t2.bjdm in(");
			for(int i=0;i<bjdm.length;i++){
				sql.append("'"+bjdm[i]+"'");
				if(i != bjdm.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
		}
		sql.append(") t left join  XG_XSXX_CQXXDAXXB q  on q.xh = t.xh " +
				" where  t.sfzx = '��У' or t.sfzx is null) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
	    if(StringUtils.isNotNull(t.getXh())){
	    	sql.append(" and xh like '%"+t.getXh()+"%'");
	    	sql.append(" or ");
	    	sql.append(" xm like '%"+t.getXh()+"%'");
		}
	    if(StringUtils.isNotNull(t.getYwh())){
	    	sql.append(" and ywh = '"+t.getYwh()+"' ");
		}
		return getPageList(t, sql.toString(),inputV);
	}
	
	
	//��ӻ���µ�����Ϣ
	public boolean saveDataXs(CqxxDaxxglForm t, String type)throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
		if(type.equalsIgnoreCase("insert")){
			sql.append(" insert into xg_xsxx_cqxxdaxxb(xh,kddh,yjdz,yjr,sj,bz,xjzt,dahh)");
			sql.append(" values(?,?,?,?,?,?,?,?)");
			parameter.add(t.getXh());
			parameter.add(t.getKddh());
			parameter.add(t.getYjdz());
			parameter.add(t.getYjr());
			parameter.add(t.getSj());
			parameter.add(t.getBz());
			parameter.add(t.getXjzt());
			parameter.add(t.getDahh());
		}else{
			sql.append(" update xg_xsxx_cqxxdaxxb set kddh=?,yjdz=?,yjr=?,sj=?,bz=?,xjzt=?,dahh=?  where xh =? ");
			parameter.add(t.getKddh());
			parameter.add(t.getYjdz());
			parameter.add(t.getYjr());
			parameter.add(t.getSj());
			parameter.add(t.getBz());
			parameter.add(t.getXjzt());
			parameter.add(t.getDahh());
			parameter.add(t.getXh());
			
		}
		return dao.runUpdate(sql.toString(),parameter.toArray(new String[]{}));
	}


	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-8-25 ����03:52:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(CqxxDaxxglForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_xsxx_cqxxdaxxb ");
		sql.append(" where xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{myForm.getXh()}, "num");
		return num;
	}
	public HashMap<String, String> getBjxx(String bjdm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select xymc,zymc,bjmc,nj from view_njxyzybj where bjdm=?");
		return dao.getMap(sql.toString(), new String[]{ bjdm }, new String[]{"xymc","zymc","bjmc","nj"});
	}
//	public String getxymc(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select xymc from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "xymc");
//	}
//	public String getzymc(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select zymc from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "zymc");
//	}
//	public String getbjmc(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select bjmc from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "bjmc");
//	}
//	
//	public String getnj(String bjdm) {
//		StringBuffer sb = new StringBuffer();
//		sb.append(" select nj from view_njxyzybj where bjdm=?");
//		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "nj");
//	}

	public String getCount(String bjdm) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) count from view_xsjbxx t1 left join view_njxyzybj t2 on t1.bjdm=t2.bjdm where t1.bjdm=?");
		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "count");
	}
	
	public String getywhCount(String bjdm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(ywh) ywhcount from " +
				"(select a.daxxid," +
				"case when a.kddh is not null then '0' else '1' end  ywh " +
				"from ( select * from ( select * from view_njxyzybj t1 " +
				"left join view_xsjbxx t2  on t1.bjdm = t2.bjdm " +
				"where t1.bjdm = ?) t3 " +
				"left join XG_XSXX_CQXXDAXXB t4 on t3.xh = t4.xh) a )where ywh = '0' ");
		return dao.getOneRs(sb.toString(), new String[] { bjdm }, "ywhcount");
	}
	
	
	
	
	
}
