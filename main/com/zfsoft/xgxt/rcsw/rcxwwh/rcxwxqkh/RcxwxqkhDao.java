package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxqkh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;
import common.Globals;

/** 
 * 日常行为学期考核 
 */
public class RcxwxqkhDao extends SuperDAOImpl<RcxwxqkhForm> {
	private static final String XQ_1 = "01"; // 上学期
	private static final String XQ_2 = "02"; // 下学期
	private static final String[] YFDM_SXQ_ARR = new String[] { "09", "10", "11", "12", "01" }; // 上学期
	private static final String[] YFMC_SXQ_ARR = new String[] { "9月", "10月", "11月", "12月", "1月" }; // 上学期
	private static final String[] YFDM_XXQ_ARR = new String[] { "02", "03", "04", "05", "06" }; // 下学期
	private static final String[] YFMC_XXQ_ARR = new String[] { "2月", "3月", "4月", "5月", "6月" }; // 下学期

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
	public List<HashMap<String, String>> getPageList(RcxwxqkhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwxqkhForm t, User user)
			throws Exception {
		// =============== 年度 < ===================
		List<HashMap<String, String>> xnndListTemp = Base.getXnndList2();
		// 第一个年度前的年度
		HashMap<String, String> nd1 = new HashMap<String, String>();
		nd1.put("nd", String.valueOf(Integer.parseInt(xnndListTemp.get(0).get("nd")) - 1));
		// 最后一个年度后的年度
		HashMap<String, String> nd2 = new HashMap<String, String>();
		nd2.put("nd", String.valueOf(Integer.parseInt(xnndListTemp.get(xnndListTemp.size() - 1).get("nd")) + 1));
		List<HashMap<String, String>> xnndList = new ArrayList<HashMap<String, String>>();
		xnndList.addAll(xnndListTemp);
		xnndList.add(nd1);
		xnndList.add(nd2);
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
		for (int i = 0; i < YFDM_SXQ_ARR.length; i++) {
			yfSql.append(" select '"+YFDM_SXQ_ARR[i]+"' yf,'"+YFMC_SXQ_ARR[i]+"' yfmc, '"+XQ_1+"' yfxq from dual ");
			if(i < YFDM_SXQ_ARR.length - 1){
				yfSql.append(" union all ");
			}
		}
		yfSql.append(" union all ");
		for (int i = 0; i < YFDM_XXQ_ARR.length; i++) {
			yfSql.append(" select '"+YFDM_XXQ_ARR[i]+"' yf,'"+YFMC_XXQ_ARR[i]+"' yfmc, '"+XQ_2+"' yfxq from dual ");
			if(i < YFDM_XXQ_ARR.length - 1){
				yfSql.append(" union all ");
			}
		}
		// =============== 月份 > ===================
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append("  a.* ");
		sql.append(" from ( ");
		sql.append("  select  ");
		sql.append("    case when a.bjgnum=0 then ( ");
		sql.append("      case when (a.yxnum=2 or a.yxnum=3) then '良好' ");
		sql.append("           when a.yxnum>=4 then '优秀' else '及格' end) ");
		sql.append("    when (a.bjgnum>=2 or a.fzsummin<=-45) then '不及格' ");
		sql.append("    else '及格' ");
		sql.append("    end xqdjmc, ");
		sql.append("    (select b.xqmc from xqdzb b where a.yfxq=b.xqdm) yfxqmc, ");
		sql.append("    a.*  ");
		sql.append("  from (    ");
		sql.append("    select  ");
		sql.append("      sum(a.yxnumtemp) over (partition by a.yfxn,a.yfxq,a.xh order by a.yfxn,a.yfxq,a.xh) yxnum, ");
		sql.append("      sum(a.bjgnumtemp) over (partition by a.yfxn,a.yfxq,a.xh order by a.yfxn,a.yfxq,a.xh) bjgnum, ");
		sql.append("      min(a.fzsum) over (partition by a.yfxn,a.yfxq,a.xh order by a.yfxn,a.yfxq,a.xh) fzsummin, ");
		sql.append("      row_number() over (partition by a.yfxn,a.yfxq,a.xh order by a.yfxn,a.yfxq,a.xh) rn, ");
		sql.append("      a.* ");
		sql.append("    from (  ");
		sql.append("      select ");
		sql.append("        case when a.ydjmc='优秀' then 1 else 0 end yxnumtemp, ");
		sql.append("        case when a.ydjmc='不及格' then 1 else 0 end bjgnumtemp,  ");
		sql.append("        a.*  ");
		sql.append("      from (   ");
		sql.append("        select   ");
		sql.append("          a.*,a.nd||a.yf||a.xh pk,b.ycfmc,   ");
		sql.append("          (case when b.fzsum is null then 0 else b.fzsum end) fzsum,   ");
		sql.append("          (case when b.ydjmc is null then (select mc from xg_rcsw_rcxwypdj where dm='1') else b.ydjmc end) ydjmc   ");
		sql.append("        from (   ");
		sql.append("          select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xydm,a.zydm,a.bjdm,   ");
		sql.append("                 b.nd,b.yf,b.yfmc,b.yfxn,b.yfxq   ");
		sql.append("          from view_xsjbxx a,   ");
		sql.append("          ( ");
		sql.append("           select  ");
		sql.append("             decode(a.yfxq,'"+XQ_2+"',(a.nd-1)||'-'||a.nd,'"+XQ_1+"',decode(a.yf,'01',(a.nd-1)||'-'||a.nd,a.nd||'-'||(a.nd+1)),'') yfxn, ");
		sql.append("             a.* ");
		sql.append("           from ( ");
		sql.append("             select ");
		sql.append("               *  ");
		sql.append("             from  ");
		sql.append("     ("+xnndSql.toString()+") b,  ");
		sql.append("     ("+yfSql.toString()+") c  ");
		sql.append("           ) a ");
		sql.append("          ) b ");
		sql.append("        ) a left join (   ");
		sql.append("            select a.*,b.mc ydjmc,c.mc ycfmc from (   ");
		sql.append("              select   ");
		sql.append("                   a.*,case when a.fzsum>0 then 0 else (a.fzsum*(-1)) end fzsumtemp   ");
		sql.append("              from (   ");
		sql.append("                select   ");
		sql.append("                  a.nd,a.yf,a.xh,   ");
		sql.append("                  sum(a.fz) over (partition by a.nd,a.yf,a.xh order by a.nd,a.yf,a.xh) fzsum,   ");
		sql.append("                  row_number() over (partition by a.nd,a.yf,a.xh order by a.nd,a.yf,a.xh) rn   ");
		sql.append("                from (   ");
		sql.append("                  select   ");
		sql.append("                    a.xh,   ");
		sql.append("                    (case   ");
		sql.append("                    when b.rcxwfzlx = '01' then   ");
		sql.append("                    '+' || a.fz   ");
		sql.append("                    when b.rcxwfzlx = '02' then   ");
		sql.append("                    '-' || a.fz   ");
		sql.append("                    else   ");
		sql.append("                    '0'   ");
		sql.append("                    end) fz,   ");
		sql.append("                    substr(a.fssj,1,4) nd,   ");
		sql.append("                    substr(a.fssj,6,2) yf   ");
		sql.append("                  from xg_rcsw_rcxwjg a   ");
		sql.append("                  left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm = b.rcxwlbdm   ");
		sql.append("                ) a   ");
		sql.append("              ) a where a.rn='1'   ");
		sql.append("            ) a left join xg_rcsw_rcxwypdj b on (a.fzsumtemp >= b.ksfs and a.fzsumtemp <= b.jsfs)   ");
		sql.append("                left join xg_rcsw_rcxwycfdj c on (a.fzsumtemp >= c.ksfs and a.fzsumtemp <= c.jsfs)   ");
		sql.append("        ) b on (a.xh=b.xh and a.nd=b.nd and a.yf=b.yf)   ");
		sql.append("      ) a where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append("    ) a  ");
		sql.append("  ) a where a.rn='1' ");
		sql.append(" ) a ");

		return getPageList(t, sql.toString(), inputV);
	}
	
}
