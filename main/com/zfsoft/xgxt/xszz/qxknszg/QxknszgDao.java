/**
 * @部门:学工产品事业部
 * @日期：2016-4-20 下午06:25:44 
 */  
package com.zfsoft.xgxt.xszz.qxknszg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.base.message.MessageUtil;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-4-21 上午08:35:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QxknszgDao extends SuperDAOImplExtend<QxknszgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxknszgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxknszgForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();	
		String view = "(select * from (select a.*,row_number()over(partition by xh order by xn desc,xq desc) rn from VIEW_NEW_DC_XSZZ_KNSRDCX a) where rn = 1 and nvl(sfqxrd,'0')<>'1')";
		sql.append("select a.*, rownum xl from "+view+" a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 上午09:05:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForKnsqxjlSave(QxknszgForm model) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_xszz_new_knsqxjl where jgGuid=? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getGuid()}, "num");
		return num;
		
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 上午09:05:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForQxknszgUpdate(QxknszgForm model) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_xszz_new_knsjgb where guid=? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getGuid()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @描述:TODO(获取需要取消的困难生资格人数)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午09:04:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCountNum(QxknszgForm t,User user)throws Exception {
	
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());	
		StringBuffer sql = new StringBuffer();	
				
		sql.append( " select count(*) countNum from ( select * " );
		sql.append( " from (select * from (select a.*,row_number()over(partition by xh order by xn desc,xq desc) rn from VIEW_NEW_DC_XSZZ_KNSRDCX a) ");
		sql.append( "  where rn = 1 and nvl(sfqxrd,'0')<>'1' ) ) a ");
		sql.append( " where 1=1 " );
			
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return dao.getOneRs(sql.toString(), inputV, "countNum");
		
	}
	
	/**
	 * 
	 * @描述:TODO(先取消掉困难生认定资格再往取消困难生记录表中插入该条数据)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 下午01:55:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelOneKnsrdzg(QxknszgForm model) throws Exception{ 
		//先取消掉困难生认定资格再往取消困难生记录表中插入该条数据
		updateOneKnsrdzg(model);						
		deleteKnsqxjl(model.getGuid());			
		StringBuffer insertKnsqxjl = new StringBuffer();		
		insertKnsqxjl.append(" insert into xg_xszz_new_knsqxjl ");
		insertKnsqxjl.append(" (jgguid,czr,czsj,qxyy) ");
		insertKnsqxjl.append(" values(?,?,?,?) ");
		
		try {
			dao.runUpdate(insertKnsqxjl.toString(), new String[]{model.getGuid(),model.getCzr(),model.getCzsj(),model.getQxyy()});
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
	}
	
	/**
	 * 
	 * @描述:TODO(是否取消困难生认定困难生认定时该字段为0或者其它字段，取消的时候该字段为1)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 下午01:51:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateOneKnsrdzg(QxknszgForm model) throws Exception{ 	
		
		//设置困难生认定0或者其它为默认进来就是该字段的值1为取消困难生认定
		String[] inputValue = new String[]{"1",model.getGuid()}; 
		String sql = " update xg_xszz_new_knsjgb set sfqxrd=? where guid = ? ";			
		try {
			dao.runUpdate(sql,inputValue);
			return true;		
		} catch (SQLException e){
			e.printStackTrace();
			return false;		
		}
		
	}
	
	/**
	 * 
	 * @描述:TODO(取消还未取消的全部的困难生认定资格)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午10:23:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelAllKnsrdzg(QxknszgForm t,User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		
		StringBuffer sql = new StringBuffer();				
		String view = "(select * from (select a.*,row_number()over(partition by xh order by xn desc,xq desc) rn from VIEW_NEW_DC_XSZZ_KNSRDCX a) where rn = 1 and nvl(sfqxrd,'0')<>'1')";							
		sql.append("select a.*, rownum xl from "+view+" a where 1=1 ");						
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		List<HashMap<String, String>> knsrdzgmap =  dao.getListNotOut(sql.toString(),inputV);										
		StringBuffer insertKnsqxjl = new StringBuffer();		
		insertKnsqxjl.append(" insert into xg_xszz_new_knsqxjl ");
		insertKnsqxjl.append(" (jgguid,czr,czsj,qxyy) ");
		insertKnsqxjl.append(" values(?,?,?,?) ");
		
		List<String[]> params = new ArrayList<String[]>();		
		String[] param = null;
		
		for (Map<String, String> map : knsrdzgmap){
			
			String guid = map.get("guid");
			deleteKnsqxjl(guid);						
			t.setGuid(guid);
			updateOneKnsrdzg(t);
			
			param = new String[4];			
			param[0] = map.get("guid");			
			param[1] = t.getCzr();
			param[2] = t.getCzsj();
			param[3] = t.getQxyy();
			
			params.add(param);
			
		}

		try {			
			dao.runBatch(insertKnsqxjl.toString(), params);
			return true;			
		} catch (SQLException e){			
			e.printStackTrace();
			return false;			
		}
	}
	
	/**
	 * 
	 * @描述:TODO(取消多个困难生认定资格)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午10:25:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelMoreKnsrdzg(QxknszgForm model) throws Exception{ 
		
		String[] jgguids = model.getGuids();		
		for (int i = 0, n = jgguids.length; i < n; i++) {				
			
			String guid = jgguids[i];
			model.setGuid(guid);
			deleteKnsqxjl(guid);
			updateOneKnsrdzg(model);	
		}		
		StringBuffer insertKnsqxjl = new StringBuffer();
		
		insertKnsqxjl.append(" insert into xg_xszz_new_knsqxjl ");
		insertKnsqxjl.append(" (jgguid,czr,czsj,qxyy) ");
		insertKnsqxjl.append(" values(?,?,?,?) ");
		List<String[]> params = new ArrayList<String[]>();
		
		String[] param = null;
		for (int i = 0, n = jgguids.length; i < n; i++) {
			
			param = new String[4];
			param[0] = jgguids[i];
			param[1] = model.getCzr();
			param[2] = model.getCzsj();
			param[3] = model.getQxyy();				
			params.add(param);
			
		}
		
		try {			
			dao.runBatch(insertKnsqxjl.toString(), params);
			return true;			
		} catch (SQLException e){			
			e.printStackTrace();
			return false;				
		}
		
	}
	
	public int deleteKnsqxjl(String jgGuid) throws Exception {
		String sql = " delete from xg_xszz_new_knsqxjl where jgguid = ? ";
		return dao.runDelete(sql, new String[] {jgGuid});

	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("XG_XSZZ_NEW_KNSJGB");
		super.setKey("guid");
	}

}
