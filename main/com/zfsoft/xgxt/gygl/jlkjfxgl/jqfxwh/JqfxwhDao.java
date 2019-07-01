package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxwh;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

public class JqfxwhDao extends  SuperDAOImpl<JqfxwhForm> {
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */	
	@Override
	public List<HashMap<String, String>> getPageList(JqfxwhForm t, User user)
			throws Exception {

		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuffer sql = new StringBuffer();	
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = " to_number(a.ch),a.qsh,a.cwh ";
		}else{
			sb = " to_number(a.ch),a.qsh,to_number(a.cwh) ";
		}
		sql.append(" select rownum r,a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from "
				+ " bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
				+ " b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm," 
				+ " c.id,c.xn,c.xq," + "  decode(c.fxzt,NULL , '-1' , '0' , '0' , '1' , '1')fxzt, "
				+ " decode(c.fxzt,'','未处理','0','未返校','1','已返校',c.fxzt)fxztmc,c.fxsj,c.wfxyy,d.fxmc,");
		if ("13011".equals(Base.xxdm)){
			sql.append("c.tbsj,");
		}
		sql.append(" (case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis," 
				+ " (case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
				+ " from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh left join xg_gygl_new_fxgl_jgb c " 
				+ " on a.xh = c.xh left join xg_gygl_new_fxgl_dmwhb d on c.fxdm = d.fxdm order by "  
				+ " a.lddm,"+sb+") a where  a.sfrz='是' and 1=1 ");
		
		String searchTjQx = "";
		//判断公寓管理员权限
		if( "yes".equalsIgnoreCase(user.getGyglyQx())){
			 searchTjQx =" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";
		}	
		sql.append(searchTjByUser);
		sql.append(searchTj+searchTjQx);
		return getPageList(t, sql.toString(), inputV); 
		
		
	}
	
	
	/**
	 * 
	 * @描述:TODO(获取返校名称)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-1 上午09:40:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getFxmc()throws Exception {
		String sql = " select fxmc "
			+"from xg_gygl_new_fxgl_csszb a left join  xg_gygl_new_fxgl_dmwhb b on a.fxdm = b.fxdm ";
		return dao.getOneRs(sql, new String[]{}, "fxmc");
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-2 上午11:01:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneFxgljgList(String  xh) {		
			StringBuffer sql = new StringBuffer();
			//辽宁机电职业技术学院 床位号存在中文，个性化修改
			String sb = "";
			if("12898".equals(Base.xxdm)){
				sb = "a.cwh";
			}else{
				sb = "to_number(a.cwh)";
			}
			sql.append("select a.*,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from bks_bjdm where bjdm=a.bjdm) cwbjmc from" 
					+ " (select lddm||qsh||cwh pkValue,a.*,b.xm,b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,"
					+ "b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm,b.sfzh, b.lxdh, c.id, c.xn,c.xq,c.fxdm,"
					+ "(case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis," 
					+ "(case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
					+ "from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh left join "
					+ "xg_gygl_new_fxgl_jgb c on a.xh = c.xh order by a.lddm,to_number(a.ch)," 
					+ "a.qsh,"+sb+") a where 1=1 and  a.xh = ? ");	
			return dao.getMapNotOut(sql.toString(),new String[]{xh});		
	}
	
	/**
	 * 
	 * @描述:TODO(获取未返校信息记录)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-2 下午02:58:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneWfxxwhjgList(String  xh) {
		StringBuffer sql = new StringBuffer();	
	    sql.append("select xh,xn,xq,decode(fxzt,'','未处理','0','未返校','1','已返校',fxzt) fxztmc," 
	    		  +"fxsj,fxdm,wfxyy from xg_gygl_new_fxgl_jgb  where xh = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
			
	/**
	 * 
	 * @描述:TODO(批量获取数据库里面的未返校记录)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-7 上午10:26:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCountNum(JqfxwhForm t,User user)throws Exception {
		//wewew
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());	
		StringBuffer sql = new StringBuffer();			
		//辽宁机电职业技术学院 床位号存在中文，个性化修改
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "a.cwh";
		}else{
			sb = "to_number(a.cwh)";
		}
		sql.append( " select count(*) countNum from (select a.xh,a.sfrz,a.lddm," 
		+ " a.bjdm,a.nj,a.qsh,a.xydm,a.zydm,a.cwh,c.fxsj,b.xm,c.fxzt "         
		+  " from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh = b.xh "
		+  " left join xg_gygl_new_fxgl_jgb c on a.xh = c.xh "
		+  " order by a.lddm, to_number(a.ch), a.qsh, "+sb+") a "
		+  " where a.sfrz = '是'  and a.xh not in (select xh  from xg_gygl_new_fxgl_jgb where fxzt = '1') " );
		
		String searchTjQx = "";
		//判断公寓管理员权限
		if( "yes".equalsIgnoreCase(user.getGyglyQx())){
			 searchTjQx =" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";
		}	
		sql.append(searchTjByUser);
		sql.append(searchTj+searchTjQx);
		return dao.getOneRs(sql.toString(), inputV, "countNum");		
	}
	

 /**
 * @throws NoSuchMethodException 
 * @throws InvocationTargetException 
 * @throws IllegalAccessException 
 * @throws SecurityException 
 * @throws IllegalArgumentException 
  * 
  * @描述:TODO(批量保存假期返校)
  * @作者：杜利骑[工号：995]
  * @日期：2016-3-14 下午12:26:58
  * @修改记录: 修改者名字-修改日期-修改内容
  * @param model
  * @return
  * boolean 返回类型 
  * @throws
  */
  public boolean plSaveJqwh(JqfxwhForm t,User user) throws Exception {
	  					
			String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
			String[] inputV = SearchService.getTjInput(t.getSearchModel());		
			StringBuffer sql = new StringBuffer();	
			//辽宁机电职业技术学院 床位号存在中文，个性化修改
			String sb = "";
			if("12898".equals(Base.xxdm)){
				sb = "a.cwh";
			}else{
				sb = "to_number(a.cwh)";
			}
			sql.append(" select xh,(select xymc from zxbz_xxbmdm where bmdm=a.xydm) cwxymc,(select bjmc from "
					+ " bks_bjdm where bjdm=a.bjdm) cwbjmc from (select lddm||qsh||cwh pkValue,a.*,b.xm,"
					+ " b.zymc xszymc,b.zydm xszydm, b.bjmc xsbjmc,b.bjdm xsbjdm,b.xz,b.pycc,b.sjhm," 
					+ " c.id,c.xn,c.xq," + "  decode(c.fxzt,NULL , '-1' , '0' , '0' , '1' , '1')fxzt, " +
					" decode(c.fxzt,'','未处理','0','未返校','1','已返校',c.fxzt)fxztmc,c.fxsj,c.wfxyy,d.fxmc,"
					+ " (case when a.xydm is not null or a.nj is not null or a.xh is not null then 'disabled' else '' end) dis," 
					+ " (case when length(a.bz)>5 then substr(a.bz,1,5)||'...' else a.bz end)subbz "
					+ " from view_xg_gygl_new_cwxx a left join view_xsbfxx b on a.xh=b.xh left join xg_gygl_new_fxgl_jgb c " 
					+ " on a.xh = c.xh left join xg_gygl_new_fxgl_dmwhb d on c.fxdm = d.fxdm order by "  
					+ " a.lddm,to_number(a.ch),a.qsh,"+sb+") a where  a.sfrz='是' and 1=1 ");
			
			String searchTjQx = "";
			//判断公寓管理员权限
			if( "yes".equalsIgnoreCase(user.getGyglyQx())){
				 searchTjQx =" and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')";
			}	
			sql.append(searchTjByUser);
			sql.append(searchTj+searchTjQx);
						
			//////////////////////////////////////////////////////////////////////////////////////////////
			
			List<HashMap<String, String>> xsjqfxxhmap =  dao.getListNotOut(sql.toString(),inputV);				
						
			StringBuffer insertJgbSql = new StringBuffer();
			insertJgbSql.append("insert into xg_gygl_new_fxgl_jgb ");
			if("13011".equals(Base.xxdm)){
				insertJgbSql.append("(xh,xn,xq,fxzt,fxsj,fxdm,wfxyy,tbsj)");
				insertJgbSql.append(" values(?,?,?,?,?,?,?,?)");
			}else{
				insertJgbSql.append("(xh,xn,xq,fxzt,fxsj,fxdm,wfxyy)");
				insertJgbSql.append(" values(?,?,?,?,?,?,?)");
			}
			
			
			List<String[]> params = new ArrayList<String[]>();
			String[] param = null;
			if("13011".equals(Base.xxdm)){
				for (Map<String, String> map : xsjqfxxhmap) {
					deleteFxgljgb(map.get("xh"));
					param = new String[8];
					param[0] = map.get("xh");
					param[1] = t.getXn();
					param[2] = t.getXq();
					param[3] = t.getFxzt();
					param[4] = t.getFxsj();
					param[5] = t.getFxdm();	
					param[6] = "";//未返校原因先置空等业务再确认	
					param[7] = t.getTbsj();
					params.add(param);
					
				}
			}else{
				for (Map<String, String> map : xsjqfxxhmap) {
					deleteFxgljgb(map.get("xh"));
					param = new String[7];
					param[0] = map.get("xh");
					param[1] = t.getXn();
					param[2] = t.getXq();
					param[3] = t.getFxzt();
					param[4] = t.getFxsj();
					param[5] = t.getFxdm();	
					param[6] = "";//未返校原因先置空等业务再确认	
					params.add(param);
					
				}
			}
			try {
				dao.runBatch(insertJgbSql.toString(), params);
				return true;		
			} catch (SQLException e){
				e.printStackTrace();
				return false;		
			}
	}
  
  /**
   * 
   * @描述:TODO(批量保存假期返校)
   * @作者：杜利骑[工号：995]
   * @日期：2016-4-19 上午11:21:47
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param model
   * @param user
   * @return
   * @throws Exception
   * boolean 返回类型 
   * @throws
   */
  public boolean pldgSaveJqwh(JqfxwhForm model,User user) throws Exception{
	  
	  	String[] xhs = model.getXhs();
		for (int i = 0, n = xhs.length; i < n; i++) {				
			deleteFxgljgb(xhs[i]);
		}
	  
		StringBuffer insertJgbSql = new StringBuffer();
		insertJgbSql.append(" insert into xg_gygl_new_fxgl_jgb ");
		insertJgbSql.append(" (xh,xn,xq,fxzt,fxsj,fxdm,wfxyy) ");
		insertJgbSql.append(" values(?,?,?,?,?,?,?) ");
		List<String[]> params = new ArrayList<String[]>();
		//String[] xhs = model.getXhs();
		String[] param = null;
		for (int i = 0, n = xhs.length; i < n; i++) {
			
			param = new String[7];
			param[0] = xhs[i];
			param[1] = model.getXn();
			param[2] = model.getXq();
			param[3] = model.getFxzt();
			param[4] = model.getFxsj();
			param[5] = model.getFxdm();	
			param[6] = "";//未返校原因先置空等业务再确认	
			params.add(param);
			
		}
		try {
			dao.runBatch(insertJgbSql.toString(), params);
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
}

  /**
   * 
   * @描述:TODO(获取常用意见)
   * @作者：杜利骑[工号：995]
   * @日期：2016-3-14 下午06:30:24
   * @修改记录: 修改者名字-修改日期-修改内容
   * @param user
   * @return
   * List<HashMap<String,String>> 返回类型 
   * @throws
   */
   public List<HashMap<String, String>> getCyyyList(User user) {		
		String sql = " select * from xg_gygl_new_fxgl_wfxcyyyb where zgh=? ";		
		return dao.getListNotOut(sql, new String[]{user.getUserName()});
	}
   	
	
	/**
	 * 
	 * @描述:TODO(根据用户删除未返校常用原因)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-15 上午11:29:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delCyyy(User user) throws Exception{
		
		String sql = " delete from xg_gygl_new_fxgl_wfxcyyyb where zgh=? ";
		return dao.runUpdate(sql, new String[]{user.getUserName()});
	}
	
	
	/**
	 * 
	 * @描述:TODO(根据用户保存未返校常用原因)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-15 上午11:30:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param cyyy
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveCyyy(User user,String[]cyyy) throws SQLException{		
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;		
		for (String str : cyyy){			
			if (StringUtil.isNull(str))
				continue;			
			param = new String[]{user.getUserName(),str};
			params.add(param);
		}		
		String sql = "insert into xg_gygl_new_fxgl_wfxcyyyb(id,zgh,cyyy) values (sys_guid(),?,?)";		
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @描述:TODO(删除假期返校结果表中的数据)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-18 下午06:17:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xhs
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteFxgljgb(String xhs) throws Exception {
		String sql = " delete from xg_gygl_new_fxgl_jgb where xh = ? ";
		return dao.runDelete(sql, new String[] {xhs});

	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(批量保存多个学生假期未返校维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-18 下午06:18:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean pldgSaveJqwfx(JqfxwhForm model) throws Exception{ 			
			String[] xhs = model.getXhs();
			for (int i = 0, n = xhs.length; i < n; i++) {				
				deleteFxgljgb(xhs[i]);
			}		
			StringBuffer insertJgbSql = new StringBuffer();
			insertJgbSql.append(" insert into xg_gygl_new_fxgl_jgb ");
			insertJgbSql.append(" (xh,xn,xq,fxzt,fxsj,fxdm,wfxyy) ");
			insertJgbSql.append(" values(?,?,?,?,?,?,?) ");
			List<String[]> params = new ArrayList<String[]>();
			//String[] xhs = model.getXhs();
			String[] param = null;
			for (int i = 0, n = xhs.length; i < n; i++) {					
				param = new String[7];
				param[0] = xhs[i];
				param[1] = model.getXn();
				param[2] = model.getXq();
				param[3] = model.getFxzt();
				param[4] = model.getFxsj();
				param[5] = model.getFxdm();	
				param[6] = model.getWfxyy();	
				params.add(param);				
			}
			try {
				dao.runBatch(insertJgbSql.toString(), params);
				return true;		
			} catch (SQLException e){
				e.printStackTrace();
				return false;		
			}
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {		
		// TODO 自动生成方法存根
		super.setClass(JqfxwhForm.class);
		super.setTableName("xg_gygl_new_fxgl_jgb");
		super.setKey("id");
		
		
	}

}
