package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwykh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;

/** 
 * 日常行为月考核 
 */
public class RcxwykhDao extends SuperDAOImpl<RcxwykhForm> {
	private static final String[] YFDM_ARR = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private static final String[] YFMC_ARR = new String[] { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" };

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_rcxwjg");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwykhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwykhForm t, User user)
			throws Exception {
		// =============== 年度 < ===================
		List<HashMap<String, String>> xnndList = Base.getXnndList2();
		StringBuilder xnndSql = new StringBuilder();
		for (int i = 0; i < xnndList.size(); i++) {
			HashMap<String, String> xnndMap = xnndList.get(i);
			xnndSql.append(" select '"+xnndMap.get("nd")+"' nd from dual ");
			if(i < xnndList.size() - 1){
				xnndSql.append(" union all ");
			}
		}
		// =============== 年度 > ===================
		// =============== 月份 < ===================
		StringBuilder yfSql = new StringBuilder();
		for (int i = 0; i < YFDM_ARR.length; i++) {
			yfSql.append(" select '"+YFDM_ARR[i]+"' yf,'"+YFMC_ARR[i]+"' yfmc from dual ");
			if(i < YFDM_ARR.length - 1){
				yfSql.append(" union all ");
			}
		}
		// =============== 月份 > ===================
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (  ");
		sql.append("   select  ");
		sql.append("     a.*,a.nd||a.yf||a.xh pk,b.ycfmc,  ");
		sql.append("     (case when b.fzsum is null then 0 else b.fzsum end) fzsum,  ");
		sql.append("     (case when b.ydjmc is null then (select mc from xg_rcsw_rcxwypdj where dm='1') else b.ydjmc end) ydjmc  ");
		sql.append("   from (  ");
		sql.append("     select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,  ");
		sql.append("            b.nd,c.yf,c.yfmc  ");
		sql.append("     from view_xsjbxx a,  ");
		sql.append("     ("+xnndSql.toString()+") b,  ");
		sql.append("     ("+yfSql.toString()+") c  ");
		sql.append("   ) a left join (  ");
		sql.append("       select a.*,b.mc ydjmc,c.mc ycfmc from (  ");
		sql.append("         select  ");
		sql.append("              a.*,case when a.fzsum>0 then 0 else (a.fzsum*(-1)) end fzsumtemp  ");
		sql.append("         from (  ");
		sql.append("           select  ");
		sql.append("             a.nd,a.yf,a.xh,  ");
		sql.append("             sum(a.fz) over (partition by a.nd,a.yf,a.xh order by a.nd,a.yf,a.xh) fzsum,  ");
		sql.append("             row_number() over (partition by a.nd,a.yf,a.xh order by a.nd,a.yf,a.xh) rn  ");
		sql.append("           from (  ");
		sql.append("             select  ");
		sql.append("               a.xh,  ");
		sql.append("               (case  ");
		sql.append("               when b.rcxwfzlx = '01' then  ");
		sql.append("               '+' || a.fz  ");
		sql.append("               when b.rcxwfzlx = '02' then  ");
		sql.append("               '-' || a.fz  ");
		sql.append("               else  ");
		sql.append("               '0'  ");
		sql.append("               end) fz,  ");
		sql.append("               substr(a.fssj,1,4) nd,  ");
		sql.append("               substr(a.fssj,6,2) yf  ");
		sql.append("             from xg_rcsw_rcxwjg a  ");
		sql.append("             left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm = b.rcxwlbdm  ");
		sql.append("           ) a  ");
		sql.append("         ) a where a.rn='1'  ");
		sql.append("       ) a left join xg_rcsw_rcxwypdj b on (a.fzsumtemp >= b.ksfs and a.fzsumtemp <= b.jsfs)  ");
		sql.append("           left join xg_rcsw_rcxwycfdj c on (a.fzsumtemp >= c.ksfs and a.fzsumtemp <= c.jsfs)  ");
		sql.append("   ) b on (a.xh=b.xh and a.nd=b.nd and a.yf=b.yf)  ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 太原旅游职业学院导出
	 */
	public List<HashMap<String, String>> rcxwykhDc_13696(String nd, String yf, String bjdm)
		throws Exception {
		// =============== 年度 < ===================
		StringBuilder xnndSql = new StringBuilder();
		xnndSql.append(" select '"+nd+"' nd from dual ");
		// =============== 年度 > ===================
		// =============== 月份 < ===================
		int yfIndex = Arrays.binarySearch(YFDM_ARR, yf);
		StringBuilder yfSql = new StringBuilder();
		yfSql.append(" select '"+yf+"' yf,'"+YFMC_ARR[yfIndex]+"' yfmc from dual ");
		// =============== 月份 > ===================
		
		// =============== 月份 < ===================
		StringBuilder caseFzsumSql = new StringBuilder();
		StringBuilder sumLbdmFzsumSql = new StringBuilder();
		StringBuilder sumFzsumSql = new StringBuilder();
		StringBuilder sumZdSql = new StringBuilder();
		sumLbdmFzsumSql.append(" sum(a.fz) over (partition by a.nd,a.yf,a.xh,a.rcxwlbdm order by a.nd,a.yf,a.xh,a.rcxwlbdm) fzsum, ");
		sumFzsumSql.append(" sum(a.fzsum) over (partition by a.nd,a.yf,a.xh order by a.nd,a.yf,a.xh) fzsum, ");
		sumZdSql.append(" (case when b.fzsum is null then 0 else b.fzsum end) fzsum, ");
		//行为大类
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwdlList = service.getXwdlList(null);
		for (int i = 0; i < xwdlList.size(); i++) {
			HashMap<String,String> xwdlMap = xwdlList.get(i);
			String rcxwlbdldm = xwdlMap.get("rcxwlbdldm");
			// 行为类别
			List<HashMap<String,String>> xwlbList = service.getXwlbList(rcxwlbdldm, null);
			for (int j = 0; j < xwlbList.size(); j++) {
				HashMap<String,String> xwlbMap = xwlbList.get(j);
				String rcxwlbdm = xwlbMap.get("rcxwlbdm");
				caseFzsumSql.append(" case when a.rcxwlbdm='"+rcxwlbdm+"' then a.fz else '0' end fzsum"+i+"_"+j+", ");
				sumLbdmFzsumSql.append(" sum(a.fzsum"+i+"_"+j+") over (partition by a.nd,a.yf,a.xh,a.rcxwlbdm order by a.nd,a.yf,a.xh,a.rcxwlbdm) fzsum"+i+"_"+j+", ");
				sumFzsumSql.append(" sum(a.fzsum"+i+"_"+j+") over (partition by a.nd,a.yf,a.xh order by a.nd,a.yf,a.xh) fzsum"+i+"_"+j+", ");
				sumZdSql.append(" (case when b.fzsum"+i+"_"+j+" is null then 0 else b.fzsum"+i+"_"+j+" end) fzsum"+i+"_"+j+", ");
			}
		}
		// =============== 月份 > ===================
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select  ");
		sql.append("   a.*,a.nd||a.yf||a.xh pk,b.ycfmc,  ");
		sql.append(sumZdSql.toString());
		sql.append("  (case when b.ydjmc is null then (select mc from xg_rcsw_rcxwypdj where dm='1') else b.ydjmc end) ydjmc   ");
		sql.append(" from (   ");
		sql.append("  select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,   ");
		sql.append("         b.nd,c.yf,c.yfmc   ");
		sql.append("  from view_xsjbxx a,   ");
		sql.append("     ("+xnndSql.toString()+") b,  ");
		sql.append("     ("+yfSql.toString()+") c  ");
		sql.append(" ) a left join (   ");
		sql.append("    select a.*,b.mc ydjmc,c.mc ycfmc from (   ");
		sql.append("      select   ");
		sql.append("           a.*,case when a.fzsum>0 then 0 else (a.fzsum*(-1)) end fzsumtemp   ");
		sql.append("      from (   ");
		sql.append("        select   ");
		sql.append("          a.nd,a.yf,a.xh,a.rcxwlbdm,   ");
		sql.append(sumFzsumSql.toString());
		sql.append("          row_number() over (partition by a.nd,a.yf,a.xh order by a.nd,a.yf,a.xh) rn2   ");
		sql.append("        from (   ");
		sql.append("          select a.* from (   ");
		sql.append("            select   ");
		sql.append("              a.nd,a.yf,a.xh,a.rcxwlbdm,   ");
		sql.append(sumLbdmFzsumSql.toString());
		sql.append("              row_number() over (partition by a.nd,a.yf,a.xh,a.rcxwlbdm order by a.nd,a.yf,a.xh,a.rcxwlbdm) rn   ");
		sql.append("            from (  ");
		sql.append("              select   ");
		sql.append(caseFzsumSql.toString());
		sql.append("                a.*   ");
		sql.append("              from (   ");
		sql.append("                select   ");
		sql.append("                  a.xh,   ");
		sql.append("                  (case   ");
		sql.append("                  when b.rcxwfzlx = '01' then   ");
		sql.append("                  '+' || a.fz   ");
		sql.append("                  when b.rcxwfzlx = '02' then   ");
		sql.append("                  '-' || a.fz   ");
		sql.append("                  else   ");
		sql.append("                  '0'   ");
		sql.append("                  end) fz,   ");
		sql.append("                  substr(a.fssj,1,4) nd,   ");
		sql.append("                  substr(a.fssj,6,2) yf,   ");
		sql.append("                  b.rcxwlbdm   ");
		sql.append("                from xg_rcsw_rcxwjg a   ");
		sql.append("                left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm = b.rcxwlbdm   ");
		sql.append("              ) a  ");
		sql.append("            ) a   ");
		sql.append("          ) a where a.rn='1'   ");
		sql.append("        ) a   ");
		sql.append("      ) a where a.rn2='1'   ");
		sql.append("    ) a left join xg_rcsw_rcxwypdj b on (a.fzsumtemp >= b.ksfs and a.fzsumtemp <= b.jsfs)   ");
		sql.append("        left join xg_rcsw_rcxwycfdj c on (a.fzsumtemp >= c.ksfs and a.fzsumtemp <= c.jsfs)   ");
		sql.append(" ) b on (a.xh=b.xh and a.nd=b.nd and a.yf=b.yf)   ");
		sql.append(" where a.nd=? and a.yf=? and a.bjdm=? ");
		return dao.getListNotOut(sql.toString(), new String[]{nd, yf, bjdm});
	}
	/**
	 * 获取班级信息
	 */
	public HashMap<String,String> querBjxx(String bjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_njxyzybj_all where bjdm=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{ bjdm });
	}
	/**
	 * 查看日常行为月考核
	 */
	public List<HashMap<String, String>> getPageListView(RcxwykhForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select a.*,substr(a.fssj,1,4) nd,substr(a.fssj,6,2) yf ");
		sql.append(" from VIEW_NEW_DC_RCSW_RCXWJG a ");
		sql.append(" ) a where a.nd||a.yf||a.xh=? ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getId()}, inputV));
	}
	
}
