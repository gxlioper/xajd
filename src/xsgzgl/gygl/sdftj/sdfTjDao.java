/**
 * @部门:学工产品事业部
 * @日期：2016-12-9 下午02:26:39 
 */  
package xsgzgl.gygl.sdftj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-9 下午02:26:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class sdfTjDao extends SuperDAOImpl<sdfTjForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(sdfTjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(sdfTjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.*, t1.ldmc");
		sql.append(" from xg_gygl_new_sdftjb t");
		sql.append(" left join (select distinct lddm, ldmc from XG_GYGL_NEW_LDXXB) t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(sdfTjForm.class);
		this.setKey("id");
		this.setTableName("xg_gygl_new_sdftjb");
	}
	
	/**
	 * 
	 * @描述: 获取楼栋代码List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:02:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm, ldmc from XG_GYGL_NEW_LDXXB ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 获取寝室List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:04:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(String lddm,String ch){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm,qsh from XG_GYGL_NEW_QSXXB where lddm = ? and ch = ?");
		return dao.getListNotOut(sql.toString(), new String[]{lddm,ch});
	}
	
	/**
	 * 
	 * @描述: 获取层号List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(String lddm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct lddm,ch from XG_GYGL_NEW_QSXXB where lddm = ?");
		return dao.getListNotOut(sql.toString(),new String[]{lddm});
	}
	
	
	/**
	 * 
	 * @描述: 获取层号
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public String getCh(String qsh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ch from XG_GYGL_NEW_QSXXB where qsh = ?");
		return dao.getOneRs(sql.toString(),new String[]{qsh},"ch");
	}
	
	/**
	 * 
	 * @描述: 获取楼栋
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public String getLdmc(String lddm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ldmc from XG_GYGL_NEW_LDXXB where lddm = ?");
		return dao.getOneRs(sql.toString(),new String[]{lddm},"ldmc");
	}
	
	/**
	 * 
	 * @描述:检查是否已存在数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午05:41:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nd
	 * @param jd
	 * @param lddm
	 * @param qsh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String nd,String jd,String lddm,String qsh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num from xg_gygl_new_sdftjb where nd = ?");
		sql.append(" and jd = ? ");
		sql.append(" and lddm = ? ");
		sql.append(" and qsh = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{nd,jd,lddm,qsh},"num" );
		return ("0").equals(num) ? true : false;
	}

}
