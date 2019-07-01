/**
 * @部门:学工产品事业部
 * @日期：2013-5-13 上午09:04:42 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 人武管理模块
 * @类功能描述: TODO入伍学费补偿管理
 * @作者：HongLin 
 * @时间： 2013-5-13 上午08:55:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwxfbcglDao extends SuperDAOImpl<RwxfbcglForm>{

	@Override
	public List<HashMap<String, String>> getPageList(RwxfbcglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 使用高级查询
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(RwxfbcglForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select *");
		sql.append(" from view_xg_rwgl_rwxfbcxxb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rwgl_rwxfbcxxb");
		super.setKey("guid");
		super.setClass(RwxfbcglForm.class);
		
	}
	
	/**
	 * 
	 * @描述:增加操作唯一性判断（学号，学年）
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:52:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkExistForSave(RwxfbcglForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rwgl_rwxfbcxxb where xh=? and xn=?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @描述:修改操作唯一性判断（学号，学年）
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:52:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(RwxfbcglForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rwgl_rwxfbcxxb where xh=? and xn=? and guid<>?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getGuid()}, "num");
		return num;
		
	}

	/** 
	 * @描述:TODO 获得单个学生入伍学费补偿信息
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:09:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getOneRwxfbcList(String  xh) {
		StringBuilder sql = new StringBuilder("select xn,xfbcsj,xfbcje,bz,rwxn,rwsj,rwd,rwdwdmc,rwdwd,sg,tz,zysl,yysl from view_xg_rwgl_rwxfbcxxb where xh=? ");
				
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
	
	/** 
	 * @描述: 批量更新
	 * @作者：HongLin
	 * @日期：2013-5-16 下午05:02:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updatePlbc(RwxfbcglForm model) throws Exception {
		String[] ids = model.getGuid().split(",");
		String[] xhs = model.getXh().split(",");
		StringBuilder guid = new StringBuilder();//用于存储值不为空的guid
		StringBuilder guidsql = new StringBuilder();
		StringBuilder xhsql = new StringBuilder();
		
		if(null!=xhs && xhs.length>0){//过滤掉guid为空的，和拼接学号
			xhsql.append(" (");
			for (int i = 0 , n = xhs.length ; i < n ; i++){
				if(ids[i]!=null && !"null".equalsIgnoreCase(ids[i]) && !"".equalsIgnoreCase(ids[i])){
					guid.append(ids[i] + ",");
				}
				if(i==(xhs.length-1)){
					xhsql.append(" xh='"+xhs[i]+"' ");
				}else{
					xhsql.append(" xh='"+xhs[i]+"' or ");
				}
			}
			xhsql.append(" ) ");
		}
		String[] guids=guid.toString()!=""?guid.toString().split(","):new String[]{};
		if(null!=guids && guids.length>0){
			guidsql.append(" and (");
			for (int i = 0 , n = guids.length ; i < n ; i++){//拼接guid
				if(i==(guids.length-1)){
					guidsql.append(" guid='"+guids[i]+"' ");
				}else{
					guidsql.append(" guid='"+guids[i]+"' or ");
				}
			}
			guidsql.append(" )");
		}
		
		String sql = "update xg_rwgl_rwxfbcxxb set xn='"+model.getXn()+"',xfbcsj='"+model.getXfbcsj()+"',xfbcje='"+model.getXfbcje()+"',bz='"+model.getBz()+"' where 1=1 "+guidsql.toString();
		return dao.runUpdate(sql, new String []{});
	}
	
	/** 
	 * @描述: 批量新增
	 * @作者：HongLin
	 * @日期：2013-5-16 下午05:02:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertPlbc(RwxfbcglForm model) throws Exception {
		String[] ids = model.getGuid().split(",");
		String[] xhs = model.getXh().split(",");
		StringBuilder guid = new StringBuilder();//用于存储值不为空的guid
		StringBuilder guidsql = new StringBuilder();
		StringBuilder xhsql = new StringBuilder();
		
		if(null!=xhs && xhs.length>0){//过滤掉guid为空的，和拼接学号
			xhsql.append(" (");
			for (int i = 0 , n = xhs.length ; i < n ; i++){
				if(ids[i]!=null && !"null".equalsIgnoreCase(ids[i]) && !"".equalsIgnoreCase(ids[i])){
					guid .append(ids[i] + ",");
				}
				if(i==(xhs.length-1)){
					xhsql.append(" xh='"+xhs[i]+"' ");
				}else{
					xhsql.append(" xh='"+xhs[i]+"' or ");
				}
			}
			xhsql.append(" ) ");
		}
		String[] guids=guid.toString()!=""?guid.toString().split(","):new String[]{};
		if(null!=guids && guids.length>0){
			guidsql.append(" (");
			for (int i = 0 , n = guids.length ; i < n ; i++){//拼接guid
				if(i==(guids.length-1)){
					guidsql.append(" guid='"+guids[i]+"' ");
				}else{
					guidsql.append(" guid='"+guids[i]+"' or ");
				}
			}
			guidsql.append(" )");
		}
		
		xhsql = new StringBuilder();
		xhsql.append(xhsql.toString()!=null && !"".equals(xhsql.toString())?xhsql.toString()+" and ":"");
		String sql = "insert into xg_rwgl_rwxfbcxxb (xh,xn,xfbcsj,xfbcje,bz) select xh,'"+model.getXn()+"','"+model.getXfbcsj()+"','"+model.getXfbcje()+"','"+model.getBz()+"' from xsxxb a where "+xhsql.toString()+" not exists (select 1 from xg_rwgl_rwxfbcxxb b where a.xh=b.xh)";
		return dao.runUpdate(sql, new String []{});
	}
}
