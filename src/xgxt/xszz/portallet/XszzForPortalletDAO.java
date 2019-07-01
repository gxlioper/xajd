package xgxt.xszz.portallet;


import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.shgz.model.common.CommonModel;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生资助提供Portal查询DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-08</p>
 */
public class XszzForPortalletDAO extends DAO {
	/**
	 * 获取学生资助情况信息
	 * @param model
	 * @return List
	 * */
	public List getXszz(String xh){	
		String[] inputValue = {xh};
		String[] outputValue = {"nd", "xmmc", "zzje"};
		String sql = "select a.* from (select nd,xmmc,xxpzje zzje from xszz_common_new_zzbbxssqb where xh=? and xxsh='通过' order by nd desc)a where rownum<6";
		
		return  rsToVator(sql, inputValue, outputValue);
	}
	
	/**
	 * 获取国家助学贷款信息
	 * @param model
	 * @return List
	 * */
	public List getGjzxdk(String xh){	
		String[] inputValue = {xh};
		String[] outputValue = {"nd", "hth", "dkje"};
		String sql = "select a.* from (select nd,hth,hj dkje from view_xszz_common_gjzxdk where xh=? and xxsh='通过' order by nd desc)a where rownum<6";
		
		return  rsToVator(sql, inputValue, outputValue);
	}
	
	
	public List<String[]> getSzzqk(CommonModel model) {
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1 ");
		
		if (!Base.isNull(model.getXh())) {
			sb.append(" and a.xh like '%");
			sb.append(model.getXh());
			sb.append("%'");
		}
		
		if (!Base.isNull(model.getXm())) {
			sb.append(" and a.xm like '%");
			sb.append(model.getXm());
			sb.append("%'");
		}
		
		if (!Base.isNull(model.getXydm())) {
			sb.append(" and a.xydm ='");
			sb.append(model.getXydm());
			sb.append("'");
		}
		
		if (!Base.isNull(model.getZydm())) {
			sb.append(" and a.zydm ='");
			sb.append(model.getZydm());
			sb.append("'");
		}
		
		if (!Base.isNull(model.getNj())) {
			sb.append(" and a.nj ='");
			sb.append(model.getNj());
			sb.append("'");
		}
		
		String sql = "select a.*,(to_number(a.cjje)+to_number(a.jlje)+to_number(a.sqje)+to_number(a.zxjje)+to_number(a.jxje)+to_number(a.wszxj)+to_number(a.knsbz)) zje from ( " +
				"select rownum r,a.*, nvl(b.knsbz,0) knsbz from("+
				"select a.*,nvl(b.wszxj,0) wszxj from ("+
				"select a.*,nvl(b.jxje,0) jxje from " +
				"( select a.*,nvl(b.zxjje,0) zxjje from ( select a.*, nvl(b.sqje, 0) sqje " +
				"from (select a.*, nvl(b.jlje, 0) jlje from (select a.xh, a.xm, a.nj, a.xydm," +
				" a.zydm, a.bjdm, a.xymc, a.zymc, a.bjmc, nvl(b.cjje, 0) cjje from view_xsjbxx a" +
				"  left join (select xh, sum(cjje) cjje from view_xscjff group by xh) b on a.xh = b.xh "+sb.toString()+") a" +
				"  left join (select xh, sum(jlje) jlje from view_xsjxjb group by xh) b on a.xh = b.xh) a" +
				"  left join (select xh, sum(dkje) sqje from VIEW_XSZZ_ZZLG_ZXDK group by xh) b on a.xh = b.xh ) a" +
				"  left join (select xh, sum(zxjje) zxjje from view_xszz_com_gjzxj3 group by xh) b on a.xh = b.xh ) a" +
				"  left join (select xh, sum(yjjl+xjjl+sjjl) jxje from view_xszz_com_gjjxj1 group by xh) b on a.xh=b.xh  )  a" +
				" left join (select xh,sum(sqje) wszxj from xszz_com_wszxj1 group by xh) b on a.xh=b.xh) a"+
				" left join (select xh, sum(sqje) knsbz from view_xszz_com_knslsbz1 where xxsh='通过' group by xh) b on a.xh=b.xh) a"+
				" where r>"+ model.getPages().getStart()+" and r<"+(model.getPages().getPageSize()+model.getPages().getStart());
		
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*) count from view_xsjbxx a " , new String[] {}, "count")));
		
		return rsToVator(sql, new String[] {}, new String[] {"xh","xm","xymc","bjmc","cjje","jlje","sqje","zxjje","jxje","wszxj","knsbz","zje"});
	}
}
