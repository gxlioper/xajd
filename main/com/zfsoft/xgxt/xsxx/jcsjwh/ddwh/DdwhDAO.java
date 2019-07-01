/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午09:08:51 
 */  
package com.zfsoft.xgxt.xsxx.jcsjwh.ddwh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息-大队维护
 * @类功能描述: 浙江警察学院个性化大队维护功能 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-12 上午08:59:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DdwhDAO extends SuperDAOImpl<DdwhForm> {

	
	@Override
	public List<HashMap<String, String>> getPageList(DdwhForm model)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*, nvl(b.zsnum,0) qds  from xg_xsxx_zjjcxy_dddmb a ");
		sql.append("left join (select dddm, count(1) zsnum from xg_xsxx_zjjcxy_ddwhb t1 where ");
		sql.append("exists ( select 1 from view_njxyzybj b where t1.qddm=b.bjdm )group by dddm) b ");
		sql.append("on a.dddm = b.dddm ) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(DdwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	protected void setTableInfo() {
		super.setKey("dddm");
		super.setTableName("xg_xsxx_zjjcxy_dddmb");
		super.setClass(DdwhForm.class);
	}
	
	/**
	 * 
	 * @描述:验证大队代码是否存在
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-12 上午11:28:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHasExists(DdwhForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) zs from xg_xsxx_zjjcxy_dddmb ") ;
		sql.append(" where dddm= ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getDddm()},"zs");
		return Integer.parseInt(num)>0 ? true:false;
	}
    
	public boolean runDelete(DdwhForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xsxx_zjjcxy_dddmb where dddm=?");
		int num = 0;
		boolean flag = false;
		try {
			num = dao.runDelete(sql.toString(),new String[]{model.getDddm()});
		} catch (Exception e) {
			logger.error(e);
		}
		
		if(num > 0){
			sql.delete(0, sql.length());
			sql.append("delete from xg_xsxx_zjjcxy_ddwhb where dddm =? ");
			try {
				dao.runDelete(sql.toString(),new String[]{model.getDddm()});
				flag = true ;
			} catch (Exception e) {
				logger.error(e);
			}
		    
		}
		return flag;
	}
	
	public List<HashMap<String,String>> getQdList(DdwhForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		if("0".equals(model.getFlag())){
			sql.append("select * from (select * from view_njxyzybj a where not exists ");
			sql.append(" (select 1 from xg_xsxx_zjjcxy_ddwhb b where ");
			sql.append(" a.bjdm=b.qddm) ) where 1=1 ");
		}else{
			sql.append(" select * from (select b.* from xg_xsxx_zjjcxy_ddwhb a  inner join  ");
			sql.append(" view_njxyzybj_all b on a.qddm=b.bjdm where dddm=?) where 1=1 ");
			params.add(model.getDddm());
		}
		sql.append(searchTj);
		params.addAll(Arrays.asList(inputV));
		return getPageList(model,sql.toString(),params.toArray(new String[params.size()]));
	}
	
	
	public boolean saveQd(String dddm,String qddm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_zjjcxy_ddwhb values (");
		sql.append("?,? )");
		return dao.runUpdate(sql.toString(), new String[]{dddm,qddm});
	}
	
	public boolean delQd(String dddm,String qddm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  xg_xsxx_zjjcxy_ddwhb where ");
		sql.append("dddm=? and qddm=? ");
		return dao.runUpdate(sql.toString(), new String[]{dddm,qddm});
	}
}
