/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 上午10:05:18 
 */  
package com.zfsoft.xgxt.zjly.ylbx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-19 上午10:05:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlbxDao extends SuperDAOImpl<YlbxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YlbxForm t, User user)
			throws Exception {
		SearchModel searchmodel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from(");
		sql.append(" select t.id,");
		sql.append(" case when xn is null then (select dqxn from xtszb ) else xn end xn ,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.zydm,");
		sql.append(" t1.nj,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.xh,");
		sql.append(" t1.sfzh,");
		sql.append(" substr(t1.sfzh,7,8)  csrq,");
//		sql.append(" substr(t1.rxrq,0,6) rxsj,");
		sql.append(" t1.sjhm lxdh,");
		sql.append(" case when t.zlbh is null then '无' else t.zlbh end zlbh,");
		sql.append(" t.czxx,");
		sql.append(" t.czzjbh,");
		sql.append(" t.czkssj,");
		sql.append(" t.czzzsj,");
		sql.append(" t.knbs,");
		sql.append(" t.ganbs,");
		sql.append(" t.ycyy,");
		sql.append(" t.yyyybs,");
		sql.append(" case when t.cxblb is null then '未参保'else t.cxblb end cxblb ,");
		sql.append(" t.shbz,");
		sql.append(" t.shyj");
		sql.append(" from view_ylbx_xsxx t1 ");
		sql.append(" left join ");
		if(searchTj.indexOf("xn")!=-1){
			 String[] str = searchmodel.getSearch_tj_xn();
			 StringBuffer sb = new StringBuffer();
			 for(int i = 0; i < str.length; i++){
			  sb. append("\'"+str[i]+"\'"+",");
			 }
			 String s = sb.toString().substring(0,sb.length() - 1);
			 sql.append("(select * from XG_ZJLY_YLBX  where xn in ("+s+")) t");
		}else{
			 sql.append(" XG_ZJLY_YLBX t");
		}
		sql.append(" on t.xh = t1.xh");
		sql.append(" ) t");
		sql.append(" where 1= 1  ");
		if(StringUtils.isNotNull(t.getCxblb())){
			sql.append(" and t.cxblb = '"+t.getCxblb()+"' ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
	
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZJLY_YLBX");
		super.setKey("id");
		super.setClass(YlbxForm.class);
	}
	
	/**
	 * @描述：唯一性校验
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年9月12日 下午5:43:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型
	 */
	public String checkExist(YlbxForm form){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String id =  form.getId();
		String xh = form.getXh();
		sql.append(" select count(1) num from XG_ZJLY_YLBX ");
		sql.append(" where  xn = ? ");
		params.add(form.getXn());
		if(!StringUtils.isNull(xh)){
			sql.append(" and xh= ? ");
			params.add(xh);
		}else{
			sql.append(" and xh= (select xh from XG_ZJLY_YLBX where id= ?)");
			params.add(id);
		}
		if(!StringUtils.isNull(id)){
			sql.append(" and id <> ? ");
			params.add(id);
		}
		
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}

	/** 
	 * @描述：取该学生续保信息列表(学年倒序)
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月17日 下午3:11:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getXbxxList(String xh) {
		String sql="select * from XG_ZJLY_YLBX where xh= ? order by xn desc";
		return dao.getListNotOut(sql,new String[]{xh});
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-5-3 上午09:19:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public Object getFdyxx(String zgh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select lxdh,xm,zgh,bmmc");
		sql.append("  from view_fdyxx");
		sql.append(" where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午10:31:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkJsDrSjfw(String xh, YlbxForm t) {
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(t.getUser(), "t", "xydm", "bjdm");
		sql.append(" select count(1) num from view_xsxxb t where xh = ?  ");
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), new String[]{xh}, "num").equals("1") ? true : false;
	}

	/**
	 * @throws SQLException  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 下午01:56:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_ZJLY_YLBX( ");
		sql.append(" xn,");
		sql.append(" xh,");
		sql.append(" sfzh,");
		sql.append(" xm,");
		sql.append(" xb,");
		sql.append(" csrq,");
		sql.append(" rxsj,");
		sql.append(" zlbh,");
		sql.append(" cxblb");
		sql.append(" )values(?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 下午02:47:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkXhQjsjIsExist(String xn, String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_ZJLY_YLBX where xn = ? and xh = ? ");
		return Integer.valueOf(dao.getOneRs(sql.toString(), new String[]{xn,xh}, "num"))>=1 ? true : false;
	}
	
}
