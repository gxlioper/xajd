/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */
package com.zfsoft.xgxt.gygl.ssyd.ydsq;

import java.util.ArrayList;
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
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YdsqDao extends SuperDAOImpl<YdsqForm> {

	/*
	 * 描述: 宿舍异动申请列表
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YdsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		/*sql.append(" select * from (");
		sql.append("select a.*,");
		sql.append(" (select tsyymc from XG_GYGL_NEW_TSYYDMB where tsyydm = a.tstzyy) tsyymc,");
		sql.append("(select xqmc from xqdzb where xqdm = a.xq) xqmc,xsxx.xm,xsxx.xb,xsxx.xymc, xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,");
		sql.append(" xsxx.nj, ");
		sql.append(" a.tzqldmc || '_'||a.tzqqsh || '_'||a.tzqcwh qsmc, ");
		sql.append("decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',a.shzt) shztmc,");
		sql.append("decode(a.ssydlx,'00','退宿','01','宿舍调整',a.ssydlx) ydlxmc");
		sql.append(" from XG_GYGL_NEW_SSYD_SSYDSQ a");
		//学生信息
		sql.append(" left join view_xsxxb xsxx on a.xh = xsxx.xh");
		sql.append(") a where 1=1 ");*/
		
		sql.append("select * from (select a.*   ");
		//重庆邮电大学
		if("13627".equals(Base.xxdm)){
			sql.append(",fdy.xm fdyxm ");
		}
		sql.append(" from VIEW_NEW_DC_GYGL_QSYDSQ a ");
		//重庆邮电大学
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy on a.bjdm = fdy.bjdm ");
		}
		sql.append(" ) a where 1=1 ");
		if("12865".equals(Base.xxdm)&&!"admin".equals(user.getUserType())){
			sql.append("and sqr='"+user.getUserName()+"'");
		}
		//高级查询相关
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YdsqForm t)
			throws Exception {
		return null;
	}
	@Override
	protected void setTableInfo() {
		this.setKey("ssydsqid");
		this.setTableName("xg_gygl_new_ssyd_ssydsq");
		this.setClass(YdsqForm.class);
	}
	/**
	 * 
	 * @描述:(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-25 上午11:47:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String,String>> getTstzyy(){
		StringBuffer sb=new StringBuffer();
		sb.append("select tsyydm,tsyymc from XG_GYGL_NEW_TSYYDMB");
		return dao.getListNotOut(sb.toString(), new String[]{});
	}
	/**
	 * 宿舍調整原因
	 */
	public List<HashMap<String,String>> getTzyy(){
		StringBuffer sb=new StringBuffer();
		sb.append("select tzyydm,tzyymc from XG_GYGL_NEW_TZYYDMB");
		return dao.getListNotOut(sb.toString(), new String[]{});
	}
	/**
	 * 
	 * @描述:获取异动申请信息(所有关联信息)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-25 下午03:26:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYdsq(String id){
		StringBuffer sql=new StringBuffer();
		sql.append("select * from(");
		sql.append("select a.*,");
		sql.append("(select xqmc from xqdzb where xqdm = a.xq) xqmc,xsxx.xm,xsxx.xb,xsxx.xymc, xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,");
		sql.append("decode(a.shzt,'0','未审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中','','无需审核',a.shzt) shztmc,");
		sql.append("decode(a.ssydlx,'00','退宿','01','宿舍调整',a.ssydlx) ydlxmc");
		sql.append(" from XG_GYGL_NEW_SSYD_SSYDSQ a ");
		sql.append(" left join view_xsxxb xsxx on a.xh = xsxx.xh)a");
		sql.append(" where 1=1 and a.ssydsqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	public HashMap<String,String> getCwxxForXh(String xh){
		StringBuffer sql=new StringBuffer();
		sql.append("select ldmc,lddm,qsh,cwh,qsdh,sfbz,nj,xydm,xymc,bjdm,bjmc,rzsj from VIEW_XG_GYGL_NEW_CWXX where xh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	/** 
	 * @描述: 查询寝室信息
	 * @作者：qilm
	 * @日期：2013-10-8 下午02:07:12
	 * @param lddm
	 * @param qsh
	 * @param cwh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getCwxx(String lddm, String qsh, String cwh) {
		

		StringBuffer sql=new StringBuffer();
		sql.append("select ldmc,lddm,qsh,cwh,qsdh,sfbz,nj,xydm,xymc,bjdm,bjmc,rzsj,xh,sfrz from VIEW_XG_GYGL_NEW_CWXX where lddm=? and qsh=? and cwh=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{lddm, qsh , cwh});
	}
	
	/** 
	 * 查询申请结果
	 */
	public HashMap<String, String> getCwxxYdjg(String xh, String ssydsqid) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select ");
		sql.append(" ydqlddm, "); 
		sql.append(" ydqldmc, ");
		sql.append(" ydqqsh, ");
		sql.append(" ydqcwh, ");
		sql.append(" ydhlddm, ");
		sql.append(" ydhldmc, ");
		sql.append(" ydhqsh, ");
		sql.append(" ydhcwh ");
		sql.append(" from XG_GYGL_NEW_SSYD_SSYDJG where xh=? and ssydsqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh , ssydsqid});
	}

	/**
	 * @描述:床位列表（调整）
	 * @作者：qilm
	 * @日期：2013-10-9 下午02:14:55
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws  Exception 
	 */
	public List<HashMap<String, String>> getCwxxList(YdsqForm t, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select t1.lddm || '_' || t1.qsh || '_' || t1.cwh cwxx, t2.xm, t1.* ");
		sql.append(" ,t2.xb ");
		sql.append("  from VIEW_XG_GYGL_NEW_CWXX t1 ");
		sql.append("  left join view_xsbfxx t2 ");
		sql.append("  on t1.xh = t2.xh ");
		sql.append("  where 1=1 ");
		if(t.getCwxx()!=null && !"".equals(t.getCwxx())){
			String[] cwxx = t.getCwxx().split("_");
			sql.append("  and t1.lddm =  '" + cwxx[0] + "' ");
			sql.append("  and t1.qsh =  '" + cwxx[1] + "' ");
			sql.append("  and t1.cwh =  '" + cwxx[2] + "' ");
		}
		if(t.getXh() !=null && !"".equals(t.getXh())){
			sql.append(" and qsxb = (select xb from view_xsbfxx where xh='" + t.getXh()+ "' ) ");
			sql.append(" and nvl(t1.xh,0) <> '" + t.getXh() + "' ");
		}
		sql.append(" ) a ");
		sql.append("  where 1=1 ");
		
		//高级查询相关
		String searchTjQx="";
		String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";	
		/* 当用户为公寓管理员时，xg_gygl_new_gyfdyb 中 存在该用户数据
		 * 不考虑其作为学院或者辅导员的权限控制，只控制楼栋数据范围
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
		sql.append(searchTjQx);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述:床位列表（入住）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-14 上午10:49:23
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws  Exception 
	 */
	public List<HashMap<String, String>> getRzcwxxList(YdsqForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select t1.lddm || '_' || t1.qsh || '_' || t1.cwh rzcwxx, t2.xm, t1.* ");
		sql.append(" ,t2.xb ");
		sql.append("  from VIEW_XG_GYGL_NEW_CWXX t1 ");
		sql.append("  left join view_xsxxb t2 ");
		sql.append("  on t1.xh = t2.xh ");
		sql.append("  where t1.sfbl='否' and t1.sfrz='否' ");
		if(t.getRzcwxx()!=null && !"".equals(t.getRzcwxx())){
			String[] rzcwxx = t.getRzcwxx().split("_");
			sql.append("  and t1.lddm =  '" + rzcwxx[0] + "' ");
			sql.append("  and t1.qsh =  '" + rzcwxx[1] + "' ");
			sql.append("  and t1.cwh =  '" + rzcwxx[2] + "' ");
		}
		if(t.getXh() !=null && !"".equals(t.getXh())){
			sql.append(" and qsxb = (select xb from view_xsbfxx where xh='" + t.getXh()+ "' ) ");
			sql.append(" and nvl(t1.xh,0) <> '" + t.getXh() + "' ");
		}
		sql.append(" ) a ");
		sql.append("  where 1=1 ");
		
		//高级查询相关
		String searchTjQx="";
		String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";	
		/* 当用户为公寓管理员时，xg_gygl_new_gyfdyb 中 存在该用户数据
		 * 不考虑其作为学院或者辅导员的权限控制，只控制楼栋数据范围
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
		//河南农业大学如果为用户为学生身份，选择异动类型为入住，无判断为学校管理员和学院管理员权限
		if(Base.xxdm.equals("10466") && user.getUserType().equalsIgnoreCase("stu")){
			searchTjQx = " and not exists (select 1 from XG_GYGL_NEW_SSYD_SSYDSQ  where ssydlx = '03' and tzqlddm = a.lddm and tzqqsh = a.qsh  and tzqcwh = a.cwh and xh !='" + t.getXh()+ "') ";
		}
		sql.append(searchTjQx);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:是否已申请
	 * @作者：qilm
	 * @日期：2013-10-22 下午02:08:47
	 * @param xh
	 * @return
	 * boolean 返回true:有已申请未审核完了的 false:没有
	 * @throws 
	 */
	public boolean getSfysq(String xh) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) counts ");
		sql.append(" from XG_GYGL_NEW_SSYD_SSYDSQ ");
		sql.append(" where ");
		sql.append(" shzt not in ('1','2') ");
		if(xh !=null && !"".equals(xh)){
			sql.append(" and xh= '" + xh + "' " );
		}
		HashMap<String, String> map = dao.getMapNotOut(sql.toString(), new String[]{});
		
		return Integer.parseInt(map.get("counts")) > 0 ? true:false;
	}
	
	
	public boolean getInShz(String qsmc) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) n ");
		sql.append(" from view_new_dc_gygl_qsydsq t1 ");
		sql.append(" where ");
		sql.append(" shzt = '5' ");
		if(qsmc !=null && !"".equals(qsmc)){
			sql.append(" and t1.tzqlddm || '_' || t1.tzqqsh || '_' || t1.tzqcwh = '" + qsmc + "' " );
		}
		HashMap<String, String> map = dao.getMapNotOut(sql.toString(), new String[]{});
		
		return Integer.parseInt(map.get("n")) > 0 ? true:false;
		
	}
	
	public boolean getSfjl(String qsmc) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) n ");
		sql.append(" from view_new_dc_gygl_qsydsq ");
		sql.append(" where ");
		sql.append(" shzt not in ('1','2') ");
		if(qsmc !=null && !"".equals(qsmc)){
			sql.append(" and qsmc= '" + qsmc + "' " );
		}
		HashMap<String, String> map = dao.getMapNotOut(sql.toString(), new String[]{});
		
		return Integer.parseInt(map.get("n")) > 0 ? true:false;
		
	}
	
	public boolean checkExistForRzcwxx(String lddm, String qsh, String cwh, String xh) {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append(" select count(1) n ");
		sql.append(" from XG_GYGL_NEW_SSYD_SSYDSQ ");
		sql.append(" where ");
		sql.append(" ssydlx = '03' and tzqlddm = ? and tzqqsh = ?  and tzqcwh = ? ");
		params.add(lddm);
		params.add(qsh);
		params.add(cwh);
		if(xh !=null && !"".equals(xh)){
			sql.append(" and xh != ? " );
			params.add(xh);
		}
		
		HashMap<String, String> map = dao.getMapNotOut(sql.toString(), params.toArray(new String[params.size()]));
		
		return Integer.parseInt(map.get("n")) > 0 ? true:false;
	}
	
	public HashMap<String,String> getYdsqxx(String ssydsqid){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xh,a.tstzsj,a.tzqldmc ydqldmc,a.tzqqsh ydqqsh,a.tzqcwh ydqcwh,");
		sql.append("a.tzhldmc ydhldmc,a.tzhqsh ydhqsh,a.tzhcwh ydhcwh,a.bz,b.xm,b.xymc,b.bjmc ");
		sql.append("from xg_gygl_new_ssyd_ssydsq a left join view_xsbfxx b on a.xh=b.xh where a.ssydsqid = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{ssydsqid});
	}
	
	/**
	 * 入住异动类型寝室信息List
	 * @param t
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getRzYdLxQsxxList(YdsqForm t, User user){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String searchTjQx="";
		String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";	
		/* 当用户为公寓管理员时，xg_gygl_new_gyfdyb 中 存在该用户数据
		 * 不考虑其作为学院或者辅导员的权限控制，只控制楼栋数据范围
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
		sql.append(" select *");
		sql.append(" from (select *");
		sql.append(" from (select t1.lddm || '_' || t1.qsh || '_' ||");
		sql.append(" t1.cwh rzcwxx,");
		sql.append(" t2.xm,");
		sql.append(" t1.*,");
		sql.append(" t2.xb");
		sql.append(" from VIEW_XG_GYGL_NEW_CWXX t1");
		sql.append(" left join view_xsxxb t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" where t1.sfbl = '否'");
		sql.append(" and t1.sfrz = '否'");
		sql.append(" and qsxb = (select xb");
		sql.append(" from view_xsbfxx");
		sql.append(" where xh = ?)");
		paraList.add(t.getXh());
		sql.append(" and nvl(t1.xh, 0) <> ?) a");
		paraList.add(t.getXh());
		sql.append(" where 1 = 1");
		sql.append(" and (1 = 1)");
		sql.append(searchTjQx);
		sql.append(" )order by lddm,to_number(qsh),cwh asc");
		sql.append(" ");
		
		
		return dao.getListNotOut(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * @描述: 宿舍床位调整
	 * @作者：qilm
	 * @日期：2013-10-9 下午02:14:55
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws  Exception 
	 */
	public List<HashMap<String, String>> getTCwxxList(YdsqForm t, User user) throws Exception {

		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		List<String> paraList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select t1.lddm || '_' || t1.qsh || '_' || t1.cwh cwxx, t2.xm, t1.* ");
		sql.append(" ,t2.xb ");
		sql.append("  from VIEW_XG_GYGL_NEW_CWXX t1 ");
		sql.append("  left join view_xsbfxx t2 ");
		sql.append("  on t1.xh = t2.xh ");
		sql.append("  where 1=1 ");
		if(t.getXh() !=null && !"".equals(t.getXh())){
			sql.append(" and qsxb = (select xb from view_xsbfxx where xh= ? ) ");
			sql.append(" and nvl(t1.xh,0) <> ? ");
			paraList.add(t.getXh());
			paraList.add(t.getXh());
		}
		sql.append(" ) a ");
		sql.append("  where 1=1 ");
		
		//高级查询相关
		String searchTjQx="";
		String searchTjByGyfdy = " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";	
		/* 当用户为公寓管理员时，xg_gygl_new_gyfdyb 中 存在该用户数据
		 * 不考虑其作为学院或者辅导员的权限控制，只控制楼栋数据范围
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//用户为公寓辅导员
			searchTjQx = searchTjByGyfdy;
		}else{//用户非公寓辅导员，可能是学校管理员，可能是学院管理员
			searchTjQx = searchTjByUser;
		}
		sql.append(searchTjQx);
		sql.append(" order by lddm,qsh,cwh asc");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
}
