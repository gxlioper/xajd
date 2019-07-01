/**
 * @部门:学工产品事业部
 * @日期：2016-6-8 下午02:38:50 
 */  
package com.zfsoft.xgxt.rcsw.xshdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-6-8 下午02:38:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XshdglDao extends SuperDAOImpl<XshdglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XshdglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XshdglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,decode(t.hdlx,0,'校内',1,'校外') hdlxmc from xg_rcsw_xshdgl t");
		sql.append(" where 1= 1  ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XshdglForm.class);
		super.setKey("sqid");
		super.setTableName("xg_rcsw_xshdgl");
	}
	
	/**
	 * 
	 * @描述:获取部门代码list
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-12 上午10:08:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBmdmList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmdm dm,bmmc mc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:获取学生活动记录map,用于查看功能
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-12 上午10:09:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXshdMap(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,decode(t.hdlx,0,'校内',1,'校外') hdlxmc from xg_rcsw_xshdgl t");
        sql.append(" where t.sqid = ?");
        return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @描述:是否存在同一主办单位同一时间申请两个或两个以上的活动名称，如果是，返回false,如果否，返回true
	 * @作者：张昌路[工号：982]
	 * @日期：2016-6-12 上午10:15:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsExistNotSame(XshdglForm t){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select count(1) num");
		sql.append(" from xg_rcsw_xshdgl");
		sql.append(" where zbdwdm = ?");
		sql.append(" and hdsj = ?");
		sql.append(" and hdmc = ?");
		paralist.add(t.getZbdwdm());
		paralist.add(t.getHdsj());
		paralist.add(t.getHdmc().trim());
		if("edit".equals(t.getType())){
			sql.append(" and sqid != ?");
			paralist.add(t.getSqid());
		}
		String numStr = dao.getOneRs(sql.toString(),paralist.toArray(new String[]{}) , "num");
		if(StringUtils.isNotNull(numStr)){
			if("0".equals(numStr.trim())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
