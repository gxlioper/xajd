/**
 * @部门:学工产品事业部
 * @日期：2015-9-7 下午05:06:58 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.sqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  ChenQ[工号:856]
 * @时间： 2015-9-7 下午05:06:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwfbysqshDao extends SuperDAOImpl<RwfbysqshModel> {
	private static final String YSH = "ysh";
	
	@Override
	public List<HashMap<String, String>> getPageList(RwfbysqshModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(RwfbysqshModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc ");
		sql.append("from XG_ZXDK_RWFBYDCSQB t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	public List<HashMap<String, String>> getAudingList(RwfbysqshModel t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2","xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm","bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from XG_ZXDK_RWFBYDCSQB t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql.append(") b left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
//		if("xx".equals(user.getUserStatus())){
			sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '")
					.append(user.getUserName()).append("')  ");
//		} else {
//			sql.append("(select spgw from xg_xtwh_spgwyh where yhjs = '").append(user.getUserStatus())
//					.append("' and spyh = '")
//					.append(user.getUserName()).append("') ");
//		}

		if(!"xx".equals(user.getUserStatus())){
			sql.append(" and c.yhjs='"+user.getUserStatus()+"'");
		}
		if (YSH.equals(t.getShzt())) {
			sql.append("and b.lczt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		} else {
			sql.append(" and b.lczt in ('0', '4')) t1) t2 where rn = 1 ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);

		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	@Override
	public RwfbysqshModel getModel(String id) throws Exception {
        StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t3.mc yjxfmc,t10.yhmc ");
		sql.append("from XG_ZXDK_RWFBYDCSQB t1 ");
		sql.append(" left join xg_zxdk_xfdmb t3 on t1.yjxf=t3.dm ");
		sql.append(" left join dmk_yh t10 on t1.yhdm=t10.yhdm ) t where id=?");
		return super.getModel(sql.toString(),new String[]{id});
	}


	/**
	 * 
	 * @描述:验证是否已申请
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-7 下午06:16:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExists(RwfbysqshModel model) {
		String sql = "select count(1) count from XG_ZXDK_RWFBYDCSQB where xh = ?";
		String num = dao.getOneRs(sql, new String[]{model.getXh()}, "count");
		return Integer.parseInt(num) > 0?true:false;
	}
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZXDK_RWFBYDCSQB");
		super.setKey("id");
		super.setClass(RwfbysqshModel.class);
	}


	/** 
	 * @描述:根据申请学号学年获取生源地贷款信息(苏州卫生)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-16 下午02:47:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getSyddkxx(String id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t2.*,t3.yhmc ");
		sql.append(" from XG_ZXDK_RWFBYDCSQB t1");
		sql.append(" left join  xg_zxdk_syddk t2  on t1.xh=t2.xh and t1.xn=t2.xn ");
		sql.append(" left join dmk_yh t3 on t2.dkyh = t3.yhdm ");
		sql.append("where t1.id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}


	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-5-16 下午02:54:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXyddkxx(String id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (select t2.*,t3.yhmc ");
		sql.append(" from XG_ZXDK_RWFBYDCSQB t1 ");
		sql.append(" left join  xg_zdgxh_wxjk_jgb t2  on t1.xh=t2.xh and t1.xn=t2.xn ");
		sql.append(" left join dmk_yh t3 on t1.yhdm = t3.yhdm ");
		sql.append(" where t1.id = ? order by t2.xq) where rownum=1");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}

}
