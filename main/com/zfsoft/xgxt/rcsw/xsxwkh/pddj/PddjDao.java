package com.zfsoft.xgxt.rcsw.xsxwkh.pddj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class PddjDao extends SuperDAOImpl<PddjForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PddjForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(PddjForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from(select t.*,case when jsf<75 and to_number(jsf)+to_number(nvl(fjf,0))>75 then 75 else to_number(jsf)+to_number(nvl(fjf,0)) end zf,case when jsf<75 then '不合格' else  '合格' end jsdj from (");
		sql.append("select t1.*,t2.xm,(to_number(t1.bzrcpfz)+to_number(t1.xscpfz)+to_number(nvl(t3.jlf,0))-to_number(nvl(t3.cff,0))) jsf,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc");
		sql.append(" ,t3.jlf,t3.cff,t4.fz fjf,t5.pddj from xg_xsxwkh_jbfb  t1 left join view_xsxxb t2 on t1.xh=t2.xh left join ");
		sql.append("(select xh,xn,sum(decode(rcxwfzlx, '01' ,zf, null)) as jlf,sum(decode(rcxwfzlx, '02' ,zf, null)) as cff");
		sql.append(" from (select sum(fz) zf, xh, xn, rcxwfzlx from (select a.*, b.rcxwfzlx from xg_rcsw_rcxwjg a");
	    sql.append(" left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm = b.rcxwlbdm) group by xh, xn, rcxwfzlx)group by xh,xn)t3");
		sql.append(" on t1.xh=t3.xh and t1.xn=t3.xn left join (select xh,xn,sum(fz)fz from xg_xsxwkh_fjfb group by xh,xn) t4 on t1.xh=t4.xh and t1.xn=t4.xn");
		sql.append(" left join xg_xsxwkh_khdjb t5 on t1.xh=t5.xh and t1.xn=t5.xn  ) t )t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public HashMap<String,String> getXsxwKhxx(String jbfid,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,case when jsf<75 and to_number(jsf)+to_number(nvl(fjf,0))>75 then 75 else to_number(jsf)+to_number(nvl(fjf,0)) end zf,case when jsf<75 then '不合格' else  '合格' end jsdj from (");
		sql.append("select t1.*,t2.xm,(to_number(t1.bzrcpfz)+to_number(t1.xscpfz)+to_number(nvl(t3.jlf,0))-to_number(nvl(t3.cff,0))) jsf,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc");
		sql.append(" ,t3.jlf,t3.cff,t4.fz fjf,t5.id,t5.pddj from xg_xsxwkh_jbfb  t1 left join view_xsxxb t2 on t1.xh=t2.xh left join ");
		sql.append("(select xh,xn,sum(decode(rcxwfzlx, '01' ,zf, null)) as jlf,sum(decode(rcxwfzlx, '02' ,zf, null)) as cff");
		sql.append(" from (select sum(fz) zf, xh, xn, rcxwfzlx from (select a.*, b.rcxwfzlx from xg_rcsw_rcxwjg a");
	    sql.append(" left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm = b.rcxwlbdm) group by xh, xn, rcxwfzlx)group by xh,xn)t3");
		sql.append(" on t1.xh=t3.xh and t1.xn=t3.xn left join (select xh,xn,sum(fz)fz from xg_xsxwkh_fjfb group by xh,xn) t4 on t1.xh=t4.xh and t1.xn=t4.xn");
		sql.append(" left join xg_xsxwkh_khdjb t5 on t1.xh=t5.xh and t1.xn=t5.xn  ) t where 1=1 ");
		sql.append(" and jbfid=? and xn=?");
		return dao.getMapNotOut(sql.toString(), new String[]{jbfid,xn});
	}
	/**
	 * 
	 * @描述:奖励分信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-8 下午03:46:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJlfList(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.rcxwlbdlmc lxmc, c.rcxwlbmc xmmc from xg_rcsw_rcxwjg a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" where c.rcxwfzlx='01' and a.xh=? and a.xn=?");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	/**
	 * 
	 * @描述:处罚分信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-8 下午03:46:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCffList(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.rcxwlbdlmc lxmc, c.rcxwlbmc xmmc from xg_rcsw_rcxwjg a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" where c.rcxwfzlx='02' and a.xh=? and a.xn=?");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn});
	}
	/**
	 * 
	 * @描述:附加分列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-8 下午04:28:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getFjfList(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxwkh_fjfb where xh=? and xn=?");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	public boolean savePddjPl(PddjForm model) throws Exception { 
		StringBuffer sql = new StringBuffer();
		sql.append("merge into XG_XSXWKH_KHDJB a ");
		sql.append("using (select ? xh,? xn,? pddj,? xf from dual) b on ");
		sql.append("(a.xh=b.xh and a.xn=b.xn) ");
		sql.append("when matched then ");
		sql.append(" update set pddj=?");
		sql.append("when not matched then ");
		sql.append("insert(xh,xn,pddj,xf) values(b.xh, b.xn, b.pddj, b.xf)");
		return dao.runUpdate(sql.toString(), new String[]{
			model.getXh(),model.getXn(),model.getPddj(),model.getXf(),model.getPddj()
		});
	}
	
	

	
	@Override
	public PddjForm getModel(PddjForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_XSXWKH_KHDJB t1");
		sql.append(" where t1.id=?");
		return super.getModel(sql.toString(),new String[]{model.getId()});
	}

	@Override
	protected void setTableInfo() {
		super.setClass(PddjForm.class);
		super.setKey("id");
		super.setTableName("XG_XSXWKH_KHDJB");

	}
	
}
