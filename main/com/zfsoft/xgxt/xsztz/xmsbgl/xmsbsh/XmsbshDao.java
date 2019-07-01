/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:35 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbsh;
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
 * @时间： 2015-7-13 下午02:38:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmsbshDao extends SuperDAOImpl<XmsbshForm>{

	
	
	@Override
	public List<HashMap<String, String>> getPageList(XmsbshForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	
	@Override
	public List<HashMap<String, String>> getPageList(XmsbshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchSztzShTjByUser(user, "t", "sbbmdm", "sbr");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc,t5.xqmc,nvl(t8.xm,t10.xm) sbrxm,decode(t1.csms,'1','个人','2','团体') csmsmc, ");
		sql.append("case when t1.xmdm in( select xmdm from xg_sztz_xsxmsq");
		sql.append(" union all select xmdm from xg_sztz_xs_sqjg) then 'no' else 'yes' end sfkcx,");
		sql.append("t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append("decode(t6.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t9.gwz, ");
		sql.append(" row_number() over(partition by t1.xmdm order by t6.shsj desc) rn ");
		sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm left join xqdzb t5 on t1.xq=t5.xqdm");
		sql.append(" left join xg_xtwh_shztb t6 on t1.xmdm = t6.ywid");
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw left join yhb t8 on t1.sbr=t8.yhm left join xsxxb t10 on t1.sbr=t10.xh left join xg_xtwh_spgw t9 on t6.gwid = t9.id where t7.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4)");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取项目申报审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 上午10:36:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmshInfo(String xmdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,case when t1.sfsljx='1' then '是' else '否' end sfsljxmc,t2.xmjbmc,t3.sskmmc,t4.bmmc sbbmmc ");
		sql.append("from xg_sztz_xmsq t1 left join xg_sztz_xmjb t2 on t1.xmjbdm = t2.xmjbdm left join xg_sztz_sskm t3 on t1.sskmdm=t3.sskmdm");
		sql.append(" left join zxbz_xxbmdm t4 on t1.sbbmdm=t4.bmdm");
		sql.append(" where t1.xmdm=?");
		return dao.getMapNotOut(sql.toString(), new String[] { xmdm });
	}

	
	
	@Override
	protected void setTableInfo() {
		super.setClass(XmsbshForm.class);
		super.setKey("xmdm");
		super.setTableName("xg_sztz_xmsq");
	}

}
