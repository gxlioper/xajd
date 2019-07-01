 
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import common.Globals;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/** 
 * 日常行为结果 
 */

public class RcxwjgDao extends SuperDAOImpl<RcxwjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("XG_RCSW_NEW_RCXWJG");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*,t1.rcxwlbdlmc || '(' || ");
		sql.append(" CASE WHEN a.ssxydm = 'qx' THEN '全校' ELSE b.bmmc END");
		sql.append(" || ')' dlxx from VIEW_NEW_DC_RCSW_RCXWJGnew t1" );
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB a on a.RCXWLBDLDM=t1.RCXWLBDLDM" );
		sql.append(" left join ZXBZ_XXBMDM b on b.bmdm=a.ssxydm" );
		sql.append(" where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	public List<HashMap<String, String>> getXwdlfList(RcxwjgForm t, User user,List<HashMap<String,String>> xmdlList)
	throws Exception {
		// TODO 自动生成方法存根
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		//沧州医高专  按学年学期统计
		if(Globals.XXDM_CZYXGDZKXX.equals(Base.xxdm) || "0".equals(getCspz().get("zq"))){
			
			List<String> params = new ArrayList<String>();
			String[] xh = t.getSearchModel().getSearch_tj_xh();
			String[] xn = t.getSearchModel().getSearch_tj_xn();
			String[] xq = t.getSearchModel().getSearch_tj_xq();
			
			StringBuilder xhSql = new StringBuilder();
			StringBuilder xnSql = new StringBuilder();
			StringBuilder xqSql = new StringBuilder();
			
			if(StringUtils.isArrayNotNull(xh)){
				xhSql.append("and xh in(");
				for (int i = 0; i < xh.length; i++) {
					if(i!=0){
						xhSql.append(",");
					}
					xhSql.append(" ? ");
					params.add(xh[i]);
				}
				xhSql.append(")");
			}
			if(StringUtils.isArrayNotNull(xn)){
				xnSql.append(" and dqndq||'-'||dqndh in(");
				for (int i = 0; i < xn.length; i++) {
					if(i!=0){
						xnSql.append(",");
					}
					xnSql.append(" ? ");
					params.add(xn[i]);
				}
				xnSql.append(")");
			}
			if(StringUtils.isArrayNotNull(xq)){
				xqSql.append("and c.xqdm in(");
				for (int i = 0; i < xq.length; i++) {
					if(i!=0){
						xqSql.append(",");
					}
					xqSql.append(" ? ");
					params.add(xq[i]);
				}
				xqSql.append(")");
			}
			
			inputV=(String[]) ArrayUtils.addAll(params.toArray(new String[params.size()]),inputV);
			sql.append(" select * from (select ");
			for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
				   sql.append("sum(t1.fz").append(i).append(")fz").append(i).append(",");
			}
			sql.append("t1.xh,t1.nj,t1.xn,t1.xqdm,t1.xqmc,t1.xm, t1.xb, t1.bjmc, t1.zydm,t1.zymc,t1.bjdm, t1.xydm, t1.xymc ");
			sql.append(" from(select ");
			for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
				sql.append(" case when t.rcxwlbmc='").append(xmdlList.get(i).get("rcxwlbmc"));
				sql.append("' then");
				if("德".equals(xmdlList.get(i).get("rcxwlbmc")) && Globals.XXDM_CZYXGDZKXX.equals(Base.xxdm)){
					sql.append("  nvl(300 + to_number(nvl(t.fz,0)), 300)");
				}
				else{
					sql.append(" to_number(nvl(fz,0))");
				}
				sql.append(" else 0 end fz").append(i).append(",");
			}
			sql.append(" t.xh,t.nj,t.rdnd xn,t.xqdm,t.xqmc,t.rcxwlbdm,t.rcxwlbmc,t.xm, t.xb, t.bjmc, t.zydm,t.zymc,t.bjdm, t.xydm, t.xymc from ( ");
			sql.append(" select t1.xm, t1.xb, t1.bjmc, t1.zydm,t1.zymc,t1.bjdm, t1.xydm, t1.xymc,t1.xh,t1.nj,t1.rdnd,t1.xqdm,t1.xqmc,t1.rcxwlbdm,t1.rcxwlbmc,sum(t2.fz)fz from (");
			sql.append(" select xh,nj,dqndq||'-'||dqndh rdnd,c.xqdm,c.xqmc,xm, xb, bjmc, zydm,zymc,bjdm, xydm,xymc,d.* from (");
			sql.append(" select a.xh,xm, xb, bjmc, zydm,zymc,bjdm, xydm,a.xymc,a.nj,a.nj+b.lv-1 dqndq,a.nj+b.lv dqndh  from view_xsbfxx a,(select LEVEL lv from dual CONNECT BY LEVEL <= 4) b),xqdzb c,XG_RCSW_NEW_RCXWLBDMB d");
			sql.append("  where nj is not null");
			sql.append(xnSql);
			sql.append(xqSql);
			sql.append(xhSql);
			sql.append(" and dqndq||'-'||dqndh||c.xqdm <= (select dqxn||dqxq from xtszb) ");
			sql.append("  ) t1 left join view_new_dc_rcsw_rcxwjgnew t2 on t1.rcxwlbdm = t2.rcxwlbdm and t1.xh=t2.xh and t1.rdnd = t2.xn and t1.xqdm = t2.xqdm group by t1.xh,t1.nj,t1.rdnd,t1.xqdm,t1.xqmc,t1.rcxwlbdm,t1.rcxwlbmc");
			sql.append(",t1.xm, t1.xb, t1.bjmc, t1.zydm,t1.zymc,t1.bjdm, t1.xydm, t1.xymc order by t1.rdnd,t1.xqdm,t1.rcxwlbdm )t)t1 group by t1.xh,t1.nj,t1.xn,t1.xqdm,t1.xqmc,t1.xm, t1.xb, t1.bjmc, t1.zydm,t1.zymc,t1.bjdm, t1.xydm, t1.xymc)t1 ");
			sql.append("  where 1=1 ");
		} else {
			sql.append(" select * from(select t.*, i.yqdm, c.xm, c.xb, c.bjmc, c.nj,c.zydm,c.zymc,c.bjdm, c.xydm, c.xymc from(select ");
			for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
				sql.append("nvl(sum(fz").append(i).append("),0) fz").append(i).append(",");
				
			}
			sql.append(" xh,xn from ( select");
			for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
				sql.append(" case when t.rcxwlbmc='").append(xmdlList.get(i).get("rcxwlbmc"))
				   .append("' then t.fz else '' end fz")
				   .append(i).append(",");
			}
			sql.append(" xh,xn from ( ");
			
			sql.append("select xn,xh, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz||'' fz,jlrxm,xssx from (");
			sql.append(" select xn,xh, rcxwlbmc,'' rcxwlbdlmc, '' rcxwlbxlmc, '9999999999' fssj, sum(fz) fz,'' jlrxm,'0' xssx from ( ");
			sql.append("select xn,xh, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz,jlrxm,xssx from (");
			sql.append("select xn,xh,rcxwlbdlmc,rcxwlbdm,rcxwlbxlmc,fssj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,jlrxm,xssx from(");
			sql.append(" select xn,xh,rcxwlbdlmc, rcxwlbdm,rcxwdlfssx,rcxwdljcf,'' rcxwlbxlmc,'9999999999' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '0' xssx from ( ");
			sql.append(" select a.xh,a.xn, b.rcxwlbdm,b.rcxwlbdlmc,d.rcxwdljcf,d.rcxwdlfssx, c.rcxwlbxlmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append(" from XG_RCSW_NEW_RCXWJG a ");
			sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWLBDMB d on b.rcxwlbdm = d.rcxwlbdm ");
			sql.append(" order by xn desc) group by xn,xh,rcxwlbdlmc,rcxwdlfssx, rcxwlbdm,rcxwdljcf,rcxwlbdm)  order by xn desc,xssx desc,rcxwlbdlmc,fssj desc ");
			sql.append(" ) b left join XG_RCSW_NEW_RCXWLBDMB b2 on b2.rcxwlbdm = b.rcxwlbdm ");
			sql.append(" ) a group by xn,xh,rcxwlbmc ");
			sql.append(" ) a order by xn desc,xssx desc,rcxwlbmc,fssj desc,rcxwlbdlmc ");
			
			sql.append(" )t ) group by xh,xn)t left join view_xsbfxx c on t.xh = c.xh left join xg_gygl_new_cwxxb g on t.xh=g.xh");
	        sql.append("   left join xg_gygl_new_ldxxb h on h.lddm=g.lddm left join zxbz_ssyqdm i on i.yqdm=h.yqdm)t1 where 1=1 ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

}
	
	public List<HashMap<String, String>> getAllList(RcxwjgForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( select a.*,b.fz zfz from VIEW_NEW_DC_RCSW_RCXWJGnew a left join");
		sql.append("(select xn,xh,xq,case when to_char(sum(fz)) like '.%'then '0' ");
		sql.append("|| to_char(sum(fz)) else to_char(sum(fz)) end fz from (select *from(select ");
		sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz,xh,xq,xn from ");
		sql.append("XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ))group by xn, xq,xh)b on");
		sql.append(" a.xh=b.xh and a.xn=b.xn and a.xq=b.xq ) t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return dao.getListNotOut(sql.toString(), inputV);

	}
	
	/**
	 * 获取行为大类集合
	 */
	public List<HashMap<String,String>> getXwdlList(HttpServletRequest request){	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.rcxwlbdldm,b.rcxwlbdlmc, ");
		sql.append(" case when b.sqkg=1 and sysdate between to_date(nvl(b.sqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(b.sqjssj,'2200-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
		sql.append(" from XG_RCSW_NEW_RCXWDLDMB b  ");
		sql.append(" ) where isopen='true' order by rcxwlbdldm ");
		
		return dao.getList(sql.toString(), new String[]{},new String[]{"rcxwlbdldm","rcxwlbdlmc"});
	}
	
	/**
	 * 获取行为类别集合
	 */
	public List<HashMap<String,String>> getXwlbList(String xwdldm, HttpServletRequest request){
		String sql = "select rcxwlbdm,rcxwlbmc from XG_RCSW_NEW_RCXWXLDMB where rcxwlbdldm=? and sfqy='1' order by rcxwlbdm ";		
		return dao.getList(sql, new String[]{xwdldm},new String[]{"rcxwlbdm","rcxwlbmc"});
	}
	
	/**
	 * 查看行为结果
	 */
	public Map<String, String> getOneXwjgList(String  xwjgId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id,a.xn, g.xqmc,b2.rcxwlbmc,b.rcxwlbdlmc,c.rcxwlbxlmc,a.fjlj,a.fjmc, ");
		sql.append(" CASE WHEN b.ssxydm = 'qx' THEN '全校' ELSE b3.bmmc END ssxymc,");
		sql.append(" a.rcxwjlsj,a.gfly,a.bz,decode(f.shzt,'0','未审核','1','通过','2','不通过','','无需审核',f.shzt) shztmc,d.shyj,(case when c.rcxwfzlx='01' then '+'||a.fz when c.rcxwfzlx='02' then '-'||a.fz else '未知类型' end) fz,c.rcxwlbbz,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm ");
		sql.append(" from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join XG_RCSW_NEW_RCXWLBDMB b2 on b.rcxwlbdm = b2.rcxwlbdm ");
		sql.append(" left join ZXBZ_XXBMDM b3 on b3.bmdm = b.ssxydm ");
		sql.append(" left join  xg_xtwh_shztb d on a.id = d.ywid ");
		sql.append(" left join xg_xtwh_spgw  e on d.gwid = e.id  left join XG_RCSW_NEW_RCXWXXWH f  on a.id = f.id ");
		sql.append(" left join  xqdzb g on a.xq = g.xqdm ");
		sql.append(" where a.id = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{xwjgId});
	}
	/**
	 * 修改行为维护信息时判断修改的行为维护是不是已经存在行为结果中
	 */
	public boolean checkXwwhForxwjg(String  xwjgId) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_RCSW_NEW_RCXWJG a ");
		sql.append(" where a.id = ? ");
		String num = dao.getOneRs(sql.toString(),new String[]{xwjgId},"num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return  flag;
	}
	/**
	 * 查看历史行为记录信息
	 */
	public ArrayList<String[]>  getMoreXwjgList(RcxwjgForm model,HashMap<String,String> cspzMap) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] outputValue = null;
		String[] inputValue = new String[]{model.getXh(),model.getXh(),model.getXh()};
		if("1".equals(cspzMap.get("zq"))){
			outputValue = new String[] { "xn","rcxwlbmc","rcxwlbdlmc","rcxwlbxlmc","fssj","fz","jlrxm" };
			sql.append(" select xn, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz||'' fz,jlrxm,xssx from ( ");
			sql.append("select xn, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz,jlrxm,xssx from (");
			sql.append("select xn, rcxwlbdm,rcxwlbdlmc, rcxwlbxlmc, fssj, fz,jlrxm,xssx from (");
			sql.append(" select a.xn,b.rcxwlbdm,b.rcxwlbdlmc, c.rcxwlbxlmc,a.rcxwjlsj, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm, '0' xssx ");
			sql.append(" from XG_RCSW_NEW_RCXWJG a ");
			sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ?");
			sql.append(" order by xn desc) ");
			sql.append(" ) b left join XG_RCSW_NEW_RCXWLBDMB b2 on b2.rcxwlbdm = b.rcxwlbdm ");
			sql.append(" order by xn desc) ");
			sql.append(" union all ");
			sql.append(" select xn, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz||'' fz,jlrxm,xssx from ( ");
			sql.append(" select xn,rcxwlbmc,rcxwlbdlmc,rcxwlbxlmc,fssj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,jlrxm,xssx from(");
			sql.append(" select  xn,'总分:' rcxwlbmc,'' rcxwlbdlmc,'' rcxwlbxlmc,'0' fssj,");
			sql.append(" case when to_char(sum(fz)+"+cspzMap.get("rcxwjcf")+") like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '1' xssx from ( ");
			sql.append("select a.xn,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,rcxwlbdldm,rcxwlbdm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xh,a.xn,d.rcxwdljcf,d.rcxwdlfssx, d.rcxwlbdm,b.rcxwlbdldm,b.rcxwlbdlmc, c.rcxwlbxlmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append(" from XG_RCSW_NEW_RCXWJG a ");
			sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWLBDMB d on b.rcxwlbdm = d.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn, rcxwlbdm,rcxwlbdldm, rcxwlbdlmc, rcxwdljcf)a left join XG_RCSW_NEW_RCXWLBDMB b on a.rcxwlbdm=b.rcxwlbdm)group by xn)) ");
			sql.append(" union all ");
			sql.append("select xn, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz||'' fz,jlrxm,xssx from (");
			sql.append(" select xn, rcxwlbmc,'' rcxwlbdlmc, '' rcxwlbxlmc, '9999999999' fssj, sum(fz) fz,'' jlrxm,'0' xssx from ( ");
			sql.append("select xn, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz,jlrxm,xssx from (");
			sql.append("select xn,rcxwlbdlmc,rcxwlbdm,rcxwlbxlmc,fssj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,jlrxm,xssx from(");
			sql.append(" select xn,rcxwlbdlmc, rcxwlbdm,rcxwdlfssx,rcxwdljcf,'' rcxwlbxlmc,'9999999999' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '0' xssx from ( ");
			sql.append(" select a.xh,a.xn, b.rcxwlbdm,b.rcxwlbdlmc,d.rcxwdljcf,d.rcxwdlfssx, c.rcxwlbxlmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append(" from XG_RCSW_NEW_RCXWJG a ");
			sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWLBDMB d on b.rcxwlbdm = d.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf,rcxwlbdm)  order by xn desc,xssx desc,rcxwlbdlmc,fssj desc ");
			sql.append(" ) b left join XG_RCSW_NEW_RCXWLBDMB b2 on b2.rcxwlbdm = b.rcxwlbdm ");
			sql.append(" ) a group by xn,rcxwlbmc ");
			sql.append(" ) a order by xn desc,xssx desc,rcxwlbmc,fssj desc,rcxwlbdlmc ");
		}else{
			outputValue = new String[] { "xn","xq","rcxwlbmc","rcxwlbdlmc","rcxwlbxlmc","fssj","fz","jlrxm" };
			sql.append(" select xn,xq, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz||'' fz,jlrxm,xssx from ( ");
			sql.append("select xn,xq, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz,jlrxm,xssx from (");
			sql.append("select xn,xq, rcxwlbdm,rcxwlbdlmc, rcxwlbxlmc, fssj, fz,jlrxm,xssx from (");
			sql.append(" select a.xn,e.xqmc xq,b.rcxwlbdm,b.rcxwlbdlmc, c.rcxwlbxlmc,a.rcxwjlsj, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm, '0' xssx ");
			sql.append(" from XG_RCSW_NEW_RCXWJG a ");
			sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ?");
			sql.append(" order by xn desc) ");
			sql.append(" ) b left join XG_RCSW_NEW_RCXWLBDMB b2 on b2.rcxwlbdm = b.rcxwlbdm ");
			sql.append(" order by xn desc) ");
			sql.append(" union all ");
			sql.append(" select xn,xq, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz||'' fz,jlrxm,xssx from ( ");
			sql.append(" select xn,xq,rcxwlbmc,rcxwlbdlmc,rcxwlbxlmc,fssj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,jlrxm,xssx from(");
			sql.append(" select  xn,xqmc xq,'总分:' rcxwlbmc,'' rcxwlbdlmc,'' rcxwlbxlmc,'0' fssj,");
			sql.append(" case when to_char(sum(fz)+"+cspzMap.get("rcxwjcf")+") like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '1' xssx from ( ");
			sql.append("select a.xn,a.xq,a.xqmc,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+a.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+a.rcxwdljcf) end fz from (");
			sql.append(" select xn,xq,xqmc,rcxwdljcf,rcxwlbdm,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xh,a.xn,xq,d.rcxwdlfssx,d.rcxwlbdm, b.rcxwlbdldm,b.rcxwlbdlmc, c.rcxwlbxlmc, ");
			if("10355".equals(Base.xxdm)){
			  sql.append("'0' rcxwdljcf");	
			}
			else{
			  sql.append("d.rcxwdljcf");
			}
			sql.append(",e.xqmc,(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append(" from XG_RCSW_NEW_RCXWJG a ");
			sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWLBDMB d on b.rcxwlbdm = d.rcxwlbdm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn,xq,xqmc, rcxwlbdm,rcxwlbdldm, rcxwlbdlmc, rcxwdljcf)a left join XG_RCSW_NEW_RCXWLBDMB b on a.rcxwlbdm=b.rcxwlbdm)group by xn,xqmc)) ");
			sql.append(" union all ");
			sql.append("select xn,xq, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz||'' fz,jlrxm,xssx from (");
			sql.append(" select xn,xq, rcxwlbmc,'' rcxwlbdlmc, '' rcxwlbxlmc, '9999999999' fssj, sum(fz) fz,'' jlrxm,'0' xssx from ( ");
			sql.append("select xn,xq, rcxwlbmc,rcxwlbdlmc, rcxwlbxlmc, fssj, fz,jlrxm,xssx from (");
			sql.append("select xn,xq,rcxwlbdlmc,rcxwlbdm,rcxwlbxlmc,fssj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,jlrxm,xssx from(");
			sql.append(" select xn,xqmc xq,rcxwlbdlmc, rcxwlbdm,rcxwdlfssx,'' rcxwlbxlmc,'9999999999' fssj,");
			if("10355".equals(Base.xxdm)){
			  sql.append("'0' rcxwdljcf");	
			}
			else{
			  sql.append("rcxwdljcf");
			}
			sql.append(" ,case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '0' xssx from ( ");
			sql.append(" select a.xh,a.xn,a.xq,e.xqmc, b.rcxwlbdm,b.rcxwlbdlmc,d.rcxwdljcf,d.rcxwdlfssx, c.rcxwlbxlmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append(" from XG_RCSW_NEW_RCXWJG a ");
			sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join XG_RCSW_NEW_RCXWLBDMB d on b.rcxwlbdm = d.rcxwlbdm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn,xqmc,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf,rcxwlbdm)  order by xn desc,xssx desc,rcxwlbdlmc,fssj desc ");
			sql.append(" ) b left join XG_RCSW_NEW_RCXWLBDMB b2 on b2.rcxwlbdm = b.rcxwlbdm ");
			sql.append(" ) a group by xn,xq,rcxwlbmc ");
			sql.append(" ) a order by xn,xq desc,xssx desc,rcxwlbmc,fssj desc,rcxwlbdlmc ");
		}
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputValue, outputValue, model);
	}
	/**
	 * 获取日行为结果参数配置
	 */
	public HashMap<String,String> getCspz(){ 
		String cszSql = "select * from xg_rcsw_cspzb where rownum=1";
		return dao.getMapNotOut(cszSql, new String[]{});
	}
	
	/**
	 * 学生能够查看到自己的操行分分数，及相关记录
	 */
	public List<String[]> getStuRcswList(String xh) {
		//DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xn,e.xqmc xq ,b.rcxwlbdlmc, c.rcxwlbmc,a.rcxwjlsj,a.fz rcxwlbfz ");
		sql.append(" from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by xn ");
		
		
		return dao.rsToVator(sql.toString(),new String[]{xh}, new String[] { "xn","xq","rcxwlbdlmc","rcxwlbmc","rcxwjlsj","rcxwlbfz" });
	}
	/**
	 * 根据学号查询日常事务
	 */
	public List<HashMap<String,String>> getRcswList(String xh,HashMap<String,String> cspzMap) {
		StringBuilder sql = new StringBuilder();
		if("1".equals(cspzMap.get("zq"))){
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,fz,xssx from ( ");
			sql.append("select a.xn,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '0' xssx");
			sql.append(" from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join XG_RCSW_NEW_RCXWXLDMB c ");
			sql.append("     on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,xssx from(");
			sql.append(" select  xn,'总分:' rcxwlbdlmc,'' rcxwlbmc,'0' rcxwjlsj, ");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'1' xssx from ( ");
			sql.append("select a.xn,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xn, b.rcxwlbdlmc, c.rcxwlbmc,b.rcxwdljcf,  b.rcxwdlfssx, b.rcxwlbdldm,");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '1' xssx");
			sql.append("  from XG_RCSW_NEW_RCXWJG a ");
			sql.append("  left join XG_RCSW_NEW_RCXWDLDMB b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join XG_RCSW_NEW_RCXWXLDMB c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn)  group by xn, rcxwlbdldm, rcxwlbdlmc) a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm) group by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,xssx from(");
			sql.append(" select  xn,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf,'' rcxwlbmc,'9999999999' rcxwjlsj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) " );
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'0' xssx ");
			sql.append(" from (select a.xh,a.xn,b.rcxwlbdlmc,b.rcxwdljcf, b.rcxwdlfssx, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz, '0' xssx");
			sql.append("  from XG_RCSW_NEW_RCXWJG a ");
			sql.append("  left join XG_RCSW_NEW_RCXWDLDMB b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join XG_RCSW_NEW_RCXWXLDMB c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn)  group by xn, rcxwlbdlmc, rcxwdlfssx, rcxwdljcf)  order by xn,xssx desc,rcxwlbdlmc,rcxwjlsj desc ");
			
		}else{
			sql.append("select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,fz,xssx from ( ");
			sql.append("select a.xn, e.xqmc, b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '0' xssx");
			sql.append(" from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join XG_RCSW_NEW_RCXWXLDMB c ");
			sql.append("     on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append("  left join xqdzb e ");
			sql.append("    on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) ");
			sql.append(" union all ");
			sql.append(" select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,xssx from(");
			sql.append(" select  xn, xqmc,'总分:' rcxwlbdlmc,'' rcxwlbmc,'0' rcxwjlsj, ");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'1' xssx from ( ");
			sql.append("select a.xn,a.xqmc,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,xqmc,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xn, e.xqmc,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("b.rcxwdljcf");
				}
            sql.append(",b.rcxwdlfssx,b.rcxwlbdldm, b.rcxwlbdlmc, c.rcxwlbmc,");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '1' xssx");
			sql.append("  from XG_RCSW_NEW_RCXWJG a ");
			sql.append("  left join XG_RCSW_NEW_RCXWDLDMB b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join XG_RCSW_NEW_RCXWXLDMB c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) group by xn , xqmc, rcxwlbdldm, rcxwlbdlmc) a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm) group by xn,xqmc)  ");
			sql.append(" union all ");
			sql.append("select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,xssx from(");
			sql.append(" select  xn,xqmc,rcxwlbdlmc,rcxwdlfssx,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("rcxwdljcf");
				}
            sql.append(",'' rcxwlbmc,'9999999999' rcxwjlsj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) " );
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'0' xssx ");
			sql.append(" from (select a.xh,a.xq,a.xn, e.xqmc, b.rcxwdlfssx, b.rcxwdljcf,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz, '0' xssx");
			sql.append("  from XG_RCSW_NEW_RCXWJG a ");
			sql.append("  left join XG_RCSW_NEW_RCXWDLDMB b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join XG_RCSW_NEW_RCXWXLDMB c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) group by xn, xqmc, rcxwlbdlmc, rcxwdlfssx, rcxwdljcf)   order by xn, xqmc, xssx desc, rcxwlbdlmc, rcxwjlsj desc ");
		}
		return dao.getListNotOut(sql.toString(), new String[] { xh,xh,xh });
	}
	@Override
	public RcxwjgForm getModel(RcxwjgForm t) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select a.*,b.rcxwlbdldm,d.rcxwlbdm,b.rcxwlbzgfz,b.rcxwlbzdfz ");
		sql.append(" from XG_RCSW_NEW_RCXWJG a ");
		sql.append(" left join XG_RCSW_NEW_RCXWXLDMB b   ");
		sql.append(" on a.rcxwlbxldm = b.rcxwlbxldm ");
		sql.append(" left join XG_RCSW_NEW_RCXWDLDMB c   ");
		sql.append(" on b.rcxwlbdldm = c.rcxwlbdldm ");		
		sql.append(" left join XG_RCSW_NEW_RCXWLBDMB d   ");
		sql.append(" on d.rcxwlbdm = c.rcxwlbdm ");		
		sql.append(" where a.id = ? ");
		RcxwjgForm model=new RcxwjgForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{t.getId()});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/** 
	 * 判断信息是否重复(学号、学期、学年、行为列表、发生时间)
	 */
	public List<HashMap<String,String>> getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String[] xwxlStr,String[] fssjStr){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.rcxwlbxlmc from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWXLDMB b on a.rcxwlbxldm=b.rcxwlbxldm where 1=1 ");
		if(xwxlStr!=null && xwxlStr.length>0){
			sql.append(" and ");
			for (int i=0;i<xwxlStr.length;i++){
				if(i==(xwxlStr.length-1)){
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbxldm='"+xwxlStr[i]+"' and a.fssj='"+fssjStr[i]+"')");
				}else{
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbxldm='"+xwxlStr[i]+"' and a.fssj='"+fssjStr[i]+"') or ");
				}
			}
		}
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xh","xn","xq","rcxwlbxlmc","fssj"});
	}
	/**
	 * 获取楼栋
	 */
	public List<HashMap<String,String>> getLdxx(String yqdm){
		String sql = "select lddm,ldmc from xg_gygl_new_ldxxb where yqdm = ? ";
		return DAO.getInstance().getList(sql, new String[]{yqdm}, new String[]{"lddm","ldmc"});
	}
	/**
	 * 获取寝室list
	 * @param lddm
	 * @return
	 */
//	public List<HashMap<String,String>> getQsList(String yqdm){
//		String sql= "select ldmc,qsh,qsxb from view_xg_gygl_new_qsxx where lddm = ? order by ch,qsh";
//		return DAO.getInstance().getList(sql, new String[]{lddm}, new String[]{"ldmc","qsh","qsxb"});
//	}
	/**
	 * 获取楼栋寝室数量
	 */
	public String getQsCount(String lddm){
		String sql= "select count(*) qss from view_xg_gygl_new_qsxx where lddm=?";
		return DAO.getInstance().getOneRs(sql, new String[]{lddm}, "qss");
	}
	/**
	 * 获取楼栋寝室住宿信息list
	 */
	public List<HashMap<String,String>> getZsxxList(String yqdm){
		String sql= "select a.lddm,a.qsh,a.xh,b.xm,b.bjmc from (select ldmc,qsh,cwh,xh from view_xg_gygl_new_cwxx " +
				"where xh is not null and yqdm = ? order by qsh,cwh ) a left join view_xsjbxx b on a.xh = b.xh";
		return DAO.getInstance().getList(sql, new String[]{yqdm}, new String[]{"qsh","xh","xm","bjmc"});
	}
	/**
	 * 获取园区楼栋所住学生学年学期文明品行分
	 */
	public List<HashMap<String,String>> getpxfList(String yqdm,String xn,String xq){
		StringBuffer sql  = new StringBuffer();
		sql.append("select a.*,b.plusxm,b.cutxm,(zfs+to_number(nvl(b.fz,0))) sjfz from(select * from (select a.*,80+to_number(nvl(b.zfs,0)) zfs from (select a.*,b.xm,b.zzmmmc from (select* from view_xg_gygl_new_cwxx where yqdm=?) a left join view_xsbfxx b on a.xh=b.xh) a  left join");
		sql.append(" (  select sum(fs) zfs,xh from XG_GYGL_NEW_WSJC_XSFSB where xn=? and xq=? group by xh ");
		sql.append(" ) b on a.xh=b.xh )where xh is not null)a left join (select xh,xn,xq,sum(decode(rcxwfzlx, '01', fz)) plusxm,");
		sql.append(" sum(decode(rcxwfzlx, '02', fz)) cutxm, case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz))");
		sql.append(" else to_char(sum(fz)) end fz from (select a.xh,a.xn,a.xq,c.rcxwfzlx,b.rcxwlbdlmc,c.rcxwlbxlmc,(case when c.rcxwfzlx = '02' then  '-' || a.fz");
		sql.append(" when c.rcxwfzlx = '01' then a.fz else  '未知类型' end) fz  from XG_RCSW_NEW_RCXWJG a ");
		sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbxldm = c.rcxwlbxldm left join XG_RCSW_NEW_RCXWDLDMB b on c.rcxwlbdldm = b.rcxwlbdldm where xn=? and xq=? order by xn)group by xh,xn,xq)b on a.xh=b.xh order by lddm,qsh,cwh");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{yqdm,xn,xq,xn,xq});
	}
	
	public String getXqmc(String xqdm){
		String sql ="select xqmc from xqdzb where xqdm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{xqdm}, "xqmc");
		
	}
	public String getYqmc(String yqdm){
		String sql ="select yqmc from zxbz_ssyqdm where yqdm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{yqdm}, "yqmc");
		
	}
	/**
	 * 日常行为数据导出（2大类能力素质测评与思想道德素质测评-浙江医学个性化）
	 */
	public List<HashMap<String,String>> getrcxwfList(String xn){
		StringBuffer sql  = new StringBuffer();
		sql.append(" select xh, xm, xymc, zymc, bjmc, nlf, sxddf, (nlf + sxddf) zf, (case when nlf >= 70 and sxddf >= 70 then '2' when nlf < 70 and sxddf < 70 then '0' else '1' end) xf ");
		sql.append(" from (select a.xh, c.xm, c.xymc, c.zymc, c.bjmc, (60 + to_number(nvl(b.fz, 0))) nlf, z.sxddf ");
		sql.append(" from (select xh from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
        sql.append(" where rcxwlbdlmc like '%能力素质测评%' or rcxwlbdlmc like '%思想道德素质测评%' group by xh) a ");       
        sql.append(" left join view_xsbfxx c on a.xh = c.XH left join (select xh, xn, sum(decode(rcxwfzlx, '01', fz)) plusxm, sum(decode(rcxwfzlx, '02', fz)) cutxm, ");
        sql.append(" case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz)) else to_char(sum(fz)) end fz ");                   
        sql.append(" from (select a.xh, a.xn, a.xq, c.rcxwfzlx, c.rcxwlbmc, (case when c.rcxwfzlx = '02' then '-' || a.fz when c.rcxwfzlx = '01' then a.fz else '未知类型' end) fz ");                     
        sql.append(" from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm left join XG_RCSW_NEW_RCXWXLDMB c ");
        sql.append(" on a.rcxwlbdm = c.rcxwlbdm left join view_xsbfxx d on a.xh = d.xh where xn = ? and rcxwlbdlmc like '%能力素质测评%' ");
        sql.append(" order by xn) group by xh, xn) b on a.XH = b.xh ");
        sql.append(" left join (select (60 + to_number(nvl(b.fz, 0))) sxddf, xh ");
        sql.append(" from (select xh, xn, rcxwlbdlmc, sum(decode(rcxwfzlx, '01', fz)) plusxm, sum(decode(rcxwfzlx, '02', fz)) cutxm, ");
        sql.append(" case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz)) else to_char(sum(fz)) end fz ");
        sql.append(" from (select a.xh, a.xn, a.xq, c.rcxwfzlx, b.rcxwlbdlmc, c.rcxwlbmc, (case when c.rcxwfzlx = '02' then '-' || a.fz when c.rcxwfzlx = '01' then a.fz else '未知类型' end) fz ");
        sql.append(" from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWDLDMB b on a.rcxwlbdldm = b.rcxwlbdldm ");
        sql.append(" left join XG_RCSW_NEW_RCXWXLDMB c on a.rcxwlbdm = c.rcxwlbdm left join view_xsbfxx d ");
        sql.append(" on a.xh = d.xh where xn = ? and rcxwlbdlmc like '%思想道德素质测评%' order by xn) ");
        sql.append(" group by xh, xn, rcxwlbdlmc) b) z on z.xh = a.xh) ");
        
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xn,xn});
	}
	/**
	 * 日常行为总分（天津体育）
	 */
	public List<HashMap<String,String>> getrcxwzfList(HashMap<String,String> cspzMap,String xn){
		StringBuffer sql  = new StringBuffer();
		sql.append("select b.xm, b.xymc, b.zymc, b.bjmc,b.nj,a.xn,a.xh,to_char((a.fz+"+cspzMap.get("rcxwjcf")+"))fz from(");
		sql.append(" select  xh,xn,");
		sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
		sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
		sql.append("else to_char(sum(fz)) end fz from ( ");
		sql.append(" select a.xn,a.xh, b.rcxwlbdlmc, c.rcxwlbmc,");
		sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz");
		sql.append("  from XG_RCSW_NEW_RCXWJG a ");
		sql.append("  left join XG_RCSW_NEW_RCXWDLDMB b ");
		sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append("  left join XG_RCSW_NEW_RCXWXLDMB c ");
		sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
	
		sql.append(" order by xh,xn) group by xh,xn) a  ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh  ");
		sql.append(" where a.xn=?");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xn});
	}
	
	/**
	 * 日常行为总分
	 */
	public List<HashMap<String,String>> getrcxwFzxxList(String xh) {
		
		StringBuffer sql  = new StringBuffer();
		//沧州医高专
	if(Base.xxdm.equals(Globals.XXDM_CZYXGDZKXX)){
		sql.append(" select t1.xh, ");
		sql.append(" t1.nj, ");
		sql.append(" t1.rdnd, ");
		sql.append(" t1.xqdm, ");
		sql.append(" t1.xqmc, ");
		sql.append(" t1.rcxwlbdm, ");
		sql.append(" t1.rcxwlbmc, ");
		sql.append(" (case when t1.rcxwlbmc = '德' then nvl(300 + sum(t2.fz), 300) else nvl(sum(t2.fz), 0) end) fz, ");
		sql.append(" (case when t1.rcxwlbmc = '德' and nvl(300 + sum(t2.fz), 300) > 240 then '3' else '' end) xf ");
		sql.append(" from (select xh, nj, dqndq || '-' || dqndh rdnd, c.xqmc,c.xqdm, d.* ");
		sql.append(" from (select a.xh, a.nj, a.nj + b.lv - 1 dqndq, a.nj + b.lv dqndh ");
		sql.append(" from view_xsbfxx a, ");
		sql.append(" (select LEVEL lv from dual CONNECT BY LEVEL <= 4) b ");
		sql.append(" where xh = ? ), ");
		sql.append(" xqdzb c, ");
		sql.append(" XG_RCSW_NEW_RCXWLBDMB d ");
		sql.append(" where nj is not null ");
		sql.append(" and dqndq || '-' || dqndh || c.xqdm <= ");
		sql.append(" (select dqxn || dqxq from xtszb)) t1 ");
		sql.append(" left join VIEW_NEW_DC_RCSW_RCXWJGnew t2 ");
		sql.append(" on t1.rcxwlbdm = t2.RCXWLBDM ");
		sql.append(" and t1.xh = t2.xh ");
		sql.append(" and t1.rdnd = t2.xn ");
		sql.append(" and t1.xqmc = t2.xqmc ");
		sql.append(" group by t1.xh, t1.nj, t1.rdnd, t1.xqmc, t1.xqdm, t1.rcxwlbdm, t1.rcxwlbmc ");
		sql.append(" order by t1.rdnd, t1.xqmc,t1.xqdm,t1.rcxwlbdm ");
		}else{
			sql.append("  select t1.xh,t1.nj,t1.xn rdnd,t1.xqdm,t1.xqmc,t1.rcxwlbdm,t1.rcxwlbmc,nvl(sum(t1.fz), 0) fz from  VIEW_NEW_DC_RCSW_RCXWJGnew t1");
			sql.append(" where xh=? group by t1.xh,t1.nj, t1.xn, t1.xqmc, t1.xqdm, t1.rcxwlbdm, t1.rcxwlbmc");
			sql.append(" order by t1.xn, t1.xqmc, t1.xqdm, t1.rcxwlbdm");
		}
		
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xh});
		
	}
	
	public List<HashMap<String,String>> getrcxwFzxxListForPrint(String xh,String rcxwlbdm) {
		
		StringBuffer sql  = new StringBuffer();
		//沧州医高专
		sql.append(" select a.*,nvl(b.pluszf,0)pluszf,nvl(b.cutzf,0)cutzf from (");
		sql.append(" select t1.xh, ");
		sql.append(" t1.nj, ");
		sql.append(" t1.rdnd, ");
		sql.append(" t1.xqdm, ");
		sql.append(" t1.xqmc, ");
		sql.append(" t1.rcxwlbdm, ");
		sql.append(" t1.rcxwlbmc, ");
		sql.append(" (case when t1.rcxwlbmc = '德' then nvl(300 + sum(t2.fz), 300) else nvl(sum(t2.fz), 0) end) fz, ");
		sql.append(" (case when t1.rcxwlbmc = '德' and nvl(300 + sum(t2.fz), 300) > 240 then '3' else '' end) xf ");
		sql.append(" from (select xh, nj, dqndq || '-' || dqndh rdnd, c.xqmc,c.xqdm, d.* ");
		sql.append(" from (select a.xh, a.nj, a.nj + b.lv - 1 dqndq, a.nj + b.lv dqndh ");
		sql.append(" from view_xsbfxx a, ");
		sql.append(" (select LEVEL lv from dual CONNECT BY LEVEL <= 4) b ");
		sql.append(" where xh = ? ), ");
		sql.append(" xqdzb c, ");
		sql.append(" XG_RCSW_NEW_RCXWLBDMB d ");
		sql.append(" where nj is not null ");
		sql.append(" and dqndq || '-' || dqndh || c.xqdm <= ");
		sql.append(" (select dqxn || dqxq from xtszb)) t1 ");
		sql.append(" left join VIEW_NEW_DC_RCSW_RCXWJGnew t2 ");
		sql.append(" on t1.rcxwlbdm = t2.RCXWLBDM ");
		sql.append(" and t1.xh = t2.xh ");
		sql.append(" and t1.rdnd = t2.xn ");
		sql.append(" and t1.xqmc = t2.xqmc ");
		sql.append(" group by t1.xh, t1.nj, t1.rdnd, t1.xqmc, t1.xqdm, t1.rcxwlbdm, t1.rcxwlbmc ");
		sql.append(" order by t1.rdnd, t1.xqmc,t1.xqdm,t1.rcxwlbdm)a ");
		sql.append(" left join (select sum(case when rcxwfzlx = '01' then fz else '0'end) pluszf,");
        sql.append(" sum(case when rcxwfzlx = '02' then fz else '0'end) cutzf,");
        sql.append(" xh,xn,xq,rcxwfzlx,rcxwlbdm from (select a.*, b.rcxwfzlx,b.rcxwlbdm from XG_RCSW_NEW_RCXWJG a left join XG_RCSW_NEW_RCXWXLDMB b");
        sql.append(" on a.rcxwlbxldm = b.rcxwlbxldm)  group by xh, xn, xq, rcxwfzlx,rcxwlbdm )b on a.xh = b.xh and a.rdnd=b.xn and a.xqdm=b.xq and  a.rcxwlbdm=b.rcxwlbdm where a.rcxwlbdm=? order by rdnd, xqdm");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xh,rcxwlbdm});
		
	}
	
	public String chkxwdlExists(String xh, String xn, String xq, String rcxwlbmc) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select count(1) cnt from VIEW_NEW_DC_RCSW_RCXWJGnew where xh=? and xn = ? and xq = ? and rcxwlbmc = ?", new String[]{xh,xn,xq,rcxwlbmc}, "cnt");
	}
	
public List<HashMap<String,String>> getRcxwMx(RcxwjgForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from VIEW_NEW_DC_RCSW_RCXWJGnew ");
		sql.append(" where xh = ? and rcxwlbdm =? and  xn=? and xq=? ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXh(),model.getRcxwlbdm(),model.getXn(),model.getXq()});
	}
	
	
}
