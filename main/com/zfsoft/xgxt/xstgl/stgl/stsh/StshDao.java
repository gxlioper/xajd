/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:35 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-4  下午02:38:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StshDao extends SuperDAOImpl<StshForm>{

	
	
	@Override
	public List<HashMap<String, String>> getPageList(StshForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	
	@Override
	public List<HashMap<String, String>> getPageList(StshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.STLBMC,t3.xmlbmc, t4.shzt shztx,");
		sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append("decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn,t7.xm stfzrxm,x.zdlsxms zdlsxm ");
		sql.append(" from xg_stgl_jtsq t1 left join xg_stgl_stlb t2 on t1.stlbdm=t2.stlbdm");
		sql.append("  left join (select a.sqid,WM_CONCAT(c.xm) as zdlsxms from xg_stgl_jtsq a left join xg_stgl_zdlscy b on a.stid = b.stid left join fdyxxb c on b.zgh = c.zgh  group by a.sqid) x on t1.sqid = x.sqid  ");
		sql.append(" left join xg_stgl_xmlb t3 on t1.xmlbdm=t3.xmlbdm ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id " +
				"  left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t7 on t1.stfzr =  t7.yhm" +
				"  left join yhb t8 on  t1.zdls = t8.yhm" +
				"  where t5.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		//sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取社团审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4  上午10:36:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmshInfo(String stid) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,case when t1.sfsljx='1' then '是' else '否' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc ");
		sql.append("from xg_stgl_jtsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm");
		sql.append(" where t1.stid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { stid });
	}

	
	
	@Override
	protected void setTableInfo() {
		super.setClass(StshForm.class);
		super.setKey("stid");
		super.setTableName("xg_stgl_jtsq");
	}

}
