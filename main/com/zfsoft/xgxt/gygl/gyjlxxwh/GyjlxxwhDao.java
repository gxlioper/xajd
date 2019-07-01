package com.zfsoft.xgxt.gygl.gyjlxxwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GyjlxxwhDao extends SuperDAOImpl<GyjlxxwhForm>{
		/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjlxxwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(GyjlxxwhForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select  a.wjid,b.lddm,b.ldmc,b.qsh,b.cwh,b.nj,b.xymc,b.zymc,a.czr,a.czsj,b.xydm,b.zydm,b.bjdm,b.bjmc,b.xb,b.xm,a.xh,a.wjxn,a.wjxq,a.gyjllbdldm,a.gyjllbdm,a.wjsj,a.bz,c.xqmc,d.gyjllbdlmc,e.gyjllbmc,substr(wjsj,1,10) wjrq,d.gyjllbdlmc||'('||e.gyjllbmc||')' wjlb," );
		sql.append("b.ldmc||','||b.qsh||','||b.cwh||'号床' zsqs");
		sql.append(" from xg_gygl_new_gyjlb a left join xg_view_gygl_new_xszsgl b on a.xh=b.xh left join xqdzb c on c.xqdm=a.wjxq ");
		sql.append(" left join xg_gygl_new_gyjllbdlb d on a.gyjllbdldm=d.gyjllbdldm left join xg_gygl_new_gyjllbdmb e on  a.gyjllbdm=e.gyjllbdm ");
		sql.append(") t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	
	@Override
	protected void setTableInfo() {
		super.setClass(GyjlxxwhForm.class);
		super.setKey("wjid");
		super.setTableName("xg_gygl_new_gyjlb");
	}
	public HashMap<String,String> getOneRsWjxx(GyjlxxwhForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select a.wjid,a.xh,a.wjxn,b.qsdh,a.wjxq,a.gyjllbdldm,a.gyjllbdm,a.wjsj,a.bz,d.gyjllbdlmc,e.gyjllbmc,");
		sql.append(" b.lddm,b.ldmc,b.qsh,b.cwh,b.nj,b.bjmc,b.zymc,b.xymc,b.xydm,b.zydm,b.bjdm,b.xb,b.xm,substr(wjsj,1,10) wjrq,d.gyjllbdlmc||'('||e.gyjllbmc||')' wjlb," );
		sql.append("c.xqmc,a.gyjllbdldm jldldm, a.gyjllbdm jllbdm ,b.ldmc||','||b.qsh||','||b.cwh||'号床' zsqs");
		sql.append(" from xg_gygl_new_gyjlb a left join xg_view_gygl_new_xszsgl b on a.xh=b.xh left join xqdzb c on c.xqdm=a.wjxq ");
		sql.append(" left join xg_gygl_new_gyjllbdlb d on a.gyjllbdldm=d.gyjllbdldm left join xg_gygl_new_gyjllbdmb e on  a.gyjllbdm=e.gyjllbdm ");
		sql.append(") t where t.wjid=?");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getWjid()});
		
	}
	public String isExists(GyjlxxwhForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_GYGL_NEW_GYJLB where xh=? and wjsj =? and gyjllbdm =?");
		return dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getWjsj(),model.getJllbdm()}, "num");
		
	}

	/**
	 * 获得纪律大类
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> getJldlList(HttpServletRequest request) {
		String sql = "select gyjllbdldm jldldm,gyjllbdlmc jldlmc from xg_gygl_new_gyjllbdlb order by gyjllbdldm";
		return dao.getList(sql, new String[]{},new String[]{"jldldm","jldlmc"});
	}
	


	
	/**
	 * 获得历史违纪信息
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getWjxxList(GyjlxxwhForm myForm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select  a.wjid,b.lddm,b.ldmc,b.qsh,b.cwh,b.nj,b.xymc,b.zymc,a.czr,a.czsj,b.xydm,b.zydm,b.bjdm,b.bjmc,b.xb,b.xm,a.xh,a.wjxn,a.wjxq,a.gyjllbdldm,a.gyjllbdm,a.wjsj,a.bz,c.xqmc,d.gyjllbdlmc,e.gyjllbmc,substr(wjsj,1,10) wjrq,d.gyjllbdlmc||'('||e.gyjllbmc||')' wjlb," );
		sql.append("b.ldmc||','||b.qsh||','||b.cwh||'号床' zsqs");
		sql.append(" from xg_gygl_new_gyjlb a left join xg_view_gygl_new_xszsgl b on a.xh=b.xh left join xqdzb c on c.xqdm=a.wjxq ");
		sql.append(" left join xg_gygl_new_gyjllbdlb d on a.gyjllbdldm=d.gyjllbdldm left join xg_gygl_new_gyjllbdmb e on  a.gyjllbdm=e.gyjllbdm ");
		sql.append(") t where xh=?");
		return dao.getListNotOut(sql.toString(), new String[]{myForm.getXh()});
	}
	public List<HashMap<String, String>> getWjxsList(GyjlxxwhForm model,String cxzd,String qsxx,String sftq,String[] xhs){
		//String[] params = new String[]{};
		List<String> params = new ArrayList<String>();
		
		String[] newParams = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.ldmc||','||a.qsh||','||a.cwh zsqs,a.lddm,a.xb, a.xh, a.xm, a.nj, a.xymc, a.zymc, a.bjmc from  xg_view_gygl_new_xszsgl a ");
		sql.append(" where 1=1  ");
		if(StringUtils.isNotEmpty(cxzd)){
		sql.append(" and (a.xh=? or a.xm=? or a.xjh=?)");
		params.add(cxzd);
		params.add(cxzd);
		params.add(cxzd);
		}
		if(StringUtils.isNotEmpty(qsxx)){
			sql.append(" and (a.ldmc||'-'||a.qsh||'-'||a.cwh)=?");
			params.add(qsxx);
			}
		sql.append(" and cwh is not null");
		if("Y".equals(sftq)){
			sql.append(" union select a.ldmc||','||a.qsh||','||a.cwh zsqs,a.lddm,a.xb, a.xh, a.xm, a.nj, a.xymc, a.zymc, a.bjmc from xg_view_gygl_new_xszsgl a");
			sql.append(" where a.lddm||a.qsh in (select a.lddm||a.qsh from xg_view_gygl_new_xszsgl a where 1=1 ");
			if(StringUtils.isNotEmpty(cxzd)){
				sql.append(" and (a.xh=? or a.xm=? or a.xjh=?)");
				params.add(cxzd);
				params.add(cxzd);
				params.add(cxzd);
				}
			if(StringUtils.isNotEmpty(qsxx)){
				sql.append(" and (a.ldmc||'-'||a.qsh||'-'||a.cwh)=?");
				params.add(qsxx);
				}
			sql.append("  and a.cwh is not null )");
			
		}
		sql.append(")where 1=1");
		if(null!=xhs&&xhs.length>0){
			params.add(model.getWjsj());
			params.add(model.getJldldm());
			params.add(model.getJllbdm());
			sql.append(" and xh||?||?||? not in(");
			for (int i = 0; i < xhs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhs[i]+"' ");
			}
			sql.append(")");
		}
		params.add(model.getWjsj());
		params.add(model.getJldldm());
		params.add(model.getJllbdm());
		sql.append("and xh||?||?||? not in(select xh||wjsj||gyjllbdldm||gyjllbdm from xg_gygl_new_gyjlb)");
		return dao.getListNotOut(sql.toString(), (String[])params.toArray(new String[params.size()]));
	}
	
	/**
	 * 保存公寓纪律信息
	 * @param xh
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public boolean saveGyjlxx(List<String[]> param) throws Exception  {
		String sql = "insert into xg_gygl_new_gyjlb(xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,gyjllbdldm,gyjllbdm,wjsj,bz,czr,wjxn,wjxq) " +
		"select xh,lddm,ldmc,qsh,cwh,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,?,?,?,?,?,?,? from xg_view_gygl_new_xszsgl where xh = ? ";
			int[] res= dao.runBatch(sql, param);
			return dao.checkBatchResult(res);
	}
}
