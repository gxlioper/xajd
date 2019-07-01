/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-24 ����05:23:30 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.jg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.yxybgl.sq.YxybsqDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-24 ����05:23:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YxybjgDao extends SuperDAOImpl<YxybjgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YxybjgForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String bmdm = user.getUserDep();
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.xqmc,substr(t1.yf, 0, 4) nian,substr(t1.yf, 6, 2) yue,t3.xm mz,t4.bmmc xymc ");
		sql.append(" from xg_bjzyy_xqyb_yxyb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join zxbz_xxbmdm t4 on t1.xydm = t4.bmdm ");
		if(new YxybsqDao().isYxUser(user)){
			sql.append(" where t1.xydm = '" + bmdm +"'");
		}
		sql.append(" ) a where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(YxybjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_bjzyy_xqyb_yxyb_jg");		
	}
	
	public boolean isHaveRecordForjg(YxybjgForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_bjzyy_xqyb_yxyb_jg where xq = ? and xn = ? and yf = ? and xydm = ? ");
		if("update".equalsIgnoreCase(form.getType())){
			sql.append(" and jgid <> '" + form.getJgid() + "'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXq(),form.getXn(),form.getYf(),form.getXydm()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public boolean deleteForSq(YxybjgForm form) throws Exception{
		String sql = "delete from xg_bjzyy_xqyb_yxyb_jg where xq = ? and xn = ? and yf = ? and xydm = ? ";
		return dao.runUpdate(sql, new String[]{form.getXq(),form.getXn(),form.getYf(),form.getXydm()});	
	}
	
	public boolean delByLclyywid(String lclyywid) throws Exception{
		String sql = "delete from xg_bjzyy_xqyb_yxyb_jg where lylcywid = ?";		
		return dao.runUpdate(sql, new String[]{lclyywid});		
	}
	
	public Map<String,String> getJgxx(YxybjgForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xqmc,t3.bmmc xymc,t4.xm mz from xg_bjzyy_xqyb_yxyb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join zxbz_xxbmdm t3 on t1.xydm = t3.bmdm ");
		sql.append(" left join fdyxxb t4 on t1.txr = t4.zgh ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{form.getJgid()});
	}
	
	/** 
	 * @����:��ȡ�ϲ�����list(������ҽҩ���Ի�)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-30 ����04:40:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHbdcList(YxybjgForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String bmdm = user.getUserDep();
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select * from ( ");
		sql.append(" select t1.*,t2.xqmc,substr(t1.yf, 0, 4) nian,substr(t1.yf, 6, 2) yue,t3.xm mz,t4.bmmc xymc ");
		sql.append(" from xg_bjzyy_xqyb_yxyb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join zxbz_xxbmdm t4 on t1.xydm = t4.bmdm ");
		if(new YxybsqDao().isYxUser(user)){
			sql.append(" where t1.xydm = '" + bmdm +"'");
		}
		sql.append(" ) group by nian,yue,xymc,xn,xq,yf,jgid,xqmc,mz,bygzkzqk,xsgzrd,xssxdt,xstsjgzjy,txsj,txr,sjly,lylcywid,xydm order by nian,yue,xymc) a where 1=1 ");
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
}
