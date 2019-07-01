/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshForm;

/**
 * @className	： HdblshDao
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-18 下午04:24:55
 * @version 	V1.0 
 */

public class HdblshDao extends SuperDAOImpl<HdblsqshForm>{

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-18 下午04:25:36
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdblsqshForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	： 审核列表
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-18 下午04:25:36
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdblsqshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String searchShgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*");
		sql.append(" ,t2.xm,t2.xb,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,");
		sql.append(" t6.guid shid,t6.shzt shztx,t6.gwid,t6.shr,t6.shyj,t9.mc || '[' ||");
		sql.append(" decode(t6.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t9.gwz,");
		sql.append(" row_number() over(partition by t1.sqid order by t6.shsj desc) rn,");
		sql.append(" t8.xqmc,");
		sql.append(" t9.hdlxmc");
		sql.append(" from xg_hdgl_hdblsqb t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xg_xtwh_shztb t6 on t1.sqid = t6.ywid");
		sql.append(" left join xg_xtwh_spgwyh t7 on  t6.gwid = t7.spgw");
		sql.append(" left join xg_xtwh_spgw t9 on t6.gwid = t9.id");
		sql.append(" left join xqdzb t8");
		sql.append(" on t1.xq = t8.xqdm");
		sql.append(" left join xg_hdgl_hdlxdmb t9");
		sql.append(" on t1.hdlx = t9.hdlxdm");
		sql.append(" where t7.spyh = ?");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t6.shzt<>0 and  t6.shzt<>4)");
		} else {
			sql.append(" and (t6.shzt=0  or t6.shzt = 4 )");
		}
		sql.append(" ) t where 1 = 1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		sql.append(searchShgwzByUser);
		sql.append(" order by sqsj desc");
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{user.getUserName()},inputV));
	}

	/**
	 * @description	： TODO
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-18 下午04:25:36
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(HdblsqshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_hdgl_hdblsqb");	
		
	}
	
	/**
	 * @description	： 获取最后一次审核的审核信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 上午11:47:58
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getShxxLatest(HdblsqshForm model){
		String sql = "select * from (select zd2,zd6,zd9,rownum rn from (select zd2,zd6,zd9 from xg_xtwh_shztb where ywid = ? and shsj is not null order by shsj desc)) where rn = 1";
		return dao.getMapNotOut(sql, new String[]{model.getSqid()});
	}

}
