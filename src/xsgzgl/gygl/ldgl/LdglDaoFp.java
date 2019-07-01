/**
 * @部门:学工产品事业部
 * @日期：2017-1-23 下午04:10:32 
 */  
package xsgzgl.gygl.ldgl;


import java.util.ArrayList;
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
 * @时间： 2017-1-23 下午04:10:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LdglDaoFp extends SuperDAOImpl<LdglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LdglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(LdglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		
	
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(LdglForm.class);
		this.setKey("bmdm");
		this.setTableName("ZXBZ_XXBMDM");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-23 下午03:31:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param ldglform
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFpryList(User user,LdglForm ldglform) throws  Exception{
		String searchTj = SearchService.getSearchTj(ldglform.getSearchModel());
		String[] inputV = SearchService.getTjInput(ldglform.getSearchModel());
		StringBuffer sql = new StringBuffer();
		String[] lddmArray = ldglform.getCheckVal();
		List<String> paraArray = new ArrayList<String>();
		sql.append(" select  distinct a.* ");
		sql.append(" from (select a.yhm,");
		sql.append(" a.xm,");
		sql.append(" b.bmmc,");
		sql.append(" a.szbm bmdm,");
		sql.append(" decode(c.xb, '1', '男', '2', '女', c.xb) xb");
		sql.append(" from yhb a");
		sql.append(" left join zxbz_xxbmdm b");
		sql.append(" on a.szbm = b.bmdm");
		sql.append(" left join fdyxxb c");
		sql.append(" on a.yhm = c.zgh) a");
		sql.append("  where 1 = 1");
		
		//1：已分配，0：未分配 //多选没有已分配页签
		if("1".equals(ldglform.getSffp())){
			sql.append(" and ");
			sql.append("  a.yhm in(");
			sql.append(" select yhm from xg_gygl_new_gyfdyb");
			if(lddmArray != null && lddmArray.length > 0){
				if(lddmArray != null && lddmArray.length > 0){
					sql.append(" where lddm = ?");
					paraArray.add(lddmArray[0]);
				}
			}
			sql.append(" )");
		}else{
			sql.append(" and ");
			sql.append("  a.yhm not in(");
			sql.append(" select yhm from xg_gygl_new_gyfdyb");
			sql.append(" where lddm in(");
			for (int i = 0; i < lddmArray.length; i++) {
				sql.append("?");
				paraArray.add(lddmArray[i]);
				if(i != lddmArray.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
			sql.append(" )");
		}
		for (int i = 0; i < inputV.length; i++) {
			paraArray.add(inputV[i]);
		}
		inputV = paraArray.toArray(new String[]{});
		sql.append(searchTj);
		return getPageList(ldglform, sql.toString(), inputV);
	}

}
