/**
 * @部门:学工产品事业部
 * @日期：2013-6-8 下午02:16:06 
 */  
package com.zfsoft.xgxt.jygl.zfss;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 社区管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： huj
 * @时间： 2013-6-8 下午02:16:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JzygDao extends SuperDAOImpl<JzygForm> {

	public List<HashMap<String, String>> getPageList(JzygForm t)
			throws Exception {
		return null;
	}

	public List<HashMap<String, String>> getPageList(JzygForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select a.zgh,a.xm,a.bmdm,decode(a.xb, '1', '男', '2', '女', '男','男','女','女') as xb," +
				"  b.bmmc from fdyxxb a left join zxbz_xxbmdm b on a.bmdm=b.bmdm where 1=1 ) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	protected void setTableInfo() {

	}

	public HashMap<String,String> getJzygxx(String zgh){
		String sql = "select * from fdyxxb where zgh = ?";
		return dao.getMapNotOut(sql, new String[]{zgh});
	}

}
