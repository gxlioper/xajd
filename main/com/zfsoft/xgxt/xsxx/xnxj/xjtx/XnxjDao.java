/**
 * @部门:学工产品事业部
 * @日期：2013-12-19 下午01:49:12 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjtx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-19 下午01:49:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnxjDao extends SuperDAOImpl<XnxjForm> {
	
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	
	public HashMap<String , String> getXnxjInfo(String xh , String xn){
		String sql = "select * from xg_xsxx_xnxjb where xh = ? and xn = ?";
		return dao.getMapNotOut(sql, new String[]{xh , xn});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*, t2.spl shlcidnew from (select a.*,")
		.append(" b.id,")
		.append("nvl2(b.xn, b.xn, '"+t.getSearchXn()+"') xn,")
		.append("b.xjnr, ")
		.append(" to_char(to_date(b.txsj , 'yyyy-MM-dd,hh24:mi:ss'),'yyyy-MM-dd') txsj, ")
		.append(" nvl2(b.shjg , b.shjg , '-1') shjg, ")
		.append("decode(b.shjg ,'0' ,'未提交' , '1' , '通过' , '2' , '不通过' , '3' , '退回' , '4' , '需审核' , '5' , '审核中') shztmc,")
		.append("nvl2(b.id , '已填' , '未填') txztmc,")
		.append(" nvl2(b.id , '1' , '0') txzt,")
		.append(" b.splid")
		.append(" from view_xsjbxx a")
		.append(" left join xg_xsxx_xnxjb b")
		.append(" on a.xh = b.xh) t1, xg_xsxx_xnxjkgb t2")
		.append(" where 1 = 1")
		.append(" ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:学年小结审核列表查询
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-21 下午02:22:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXnxjShPageList(XnxjForm t, User user)
		throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");	
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t2.* from (select t1.* , row_number() over(partition by id order by shsj desc) rn from (select b.*, ")
		.append("'[' || c.mc || ':' || decode(b.shzt, '0','待审核','1', '通过', '2','不通过','3', '退回', '4', '需重审', '5', '审核中',b.shzt) || ']' shztmc,c.gwz ")
		.append("from (select a.xh,a.xn,   to_char(to_date(a.txsj , 'yyyy-MM-dd,hh24:mi:ss'),'yyyy-MM-dd') txsj,a.id,a.xjnr,a.shjg, a.splid, m.nj,m.xb, m.xm,m.xydm, m.zydm, m.bjdm,b.zd3 as dcmc, m.xymc,m.zymc,m.bjmc,b.shzt, ")
		.append("b.shsj, b.gwid as xtgwid, b.zd2 as rddc,b.guid as shid ")
		.append("from xg_xsxx_xnxjb a left join view_xsjbxx m on a.xh = m.xh ")
		.append("left join xg_xtwh_shztb b on a.id = b.ywid  where 1 = 1) b ")
		.append("left join xg_xtwh_spgw c on b.xtgwid = c.id where ")
		.append(" b.xtgwid in (select spgw from xg_xtwh_spgwyh where spyh = '").append(user.getUserName()).append("' ) ");
		
		if(DSH.equals(t.getShQryType()))
			sql.append("and b.shzt in ('0', '4')) t1 ) t2 where rn = 1 ");
		else if(YSH.equals(t.getShQryType()))
			sql.append("and b.shzt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);
		
		return getPageList(t, sql.toString(), inputV);
		
	}
	
	/**
	 * 
	 * @描述:根据学号和学年查询学年小结
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-19 下午04:42:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param params
	 * @return
	 * @throws Exception
	 * KnsrdForm 返回类型 
	 * @throws
	 */
	public XnxjForm getModel(XnxjForm t ,String[] params) throws Exception {
		
		String sql = "select * from xg_xsxx_xnxjb where xh=? and xn=?";
		
		return super.getModel(t, sql, params);
	}

	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(XnxjForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_xnxjb");
	}
	
	/**
	 * 查询审核意见，根据审核时间升序
	 */
	public List<HashMap<String , String>> getXnxjShyjList(String id){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.xm from xg_xtwh_shztb a ");
		sql.append(" left join fdyxxb b on a.shr=b.zgh ");
		sql.append(" where a.ywid = ? order by a.shsj asc ");
		return dao.getListNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @描述:查询业务ID是否有过退回记录
	 * @作者：cq [工号：785]
	 * @日期：2014-5-6 下午01:43:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShzt(String ywid){
		
		StringBuffer sql = new StringBuffer(" select count(1) num from xg_xtwh_shztb where shzt = '3' and  ywid = ? ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{ywid}, "num");
		
		return num;
	}

}
