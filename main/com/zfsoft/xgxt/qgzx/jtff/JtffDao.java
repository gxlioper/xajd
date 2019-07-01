package com.zfsoft.xgxt.qgzx.jtff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class JtffDao extends SuperDAOImpl<JtffForm> {
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_QGZX_NEW_JTFFB");
		super.setKey("id");
		super.setClass(JtffForm.class);
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(JtffForm model) throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t1.*,t2.XM,t2.BJDM,t2.BJMC,t2.ZYDM,t2.ZYMC,t2.XYDM,t2.XYMC,t2.NJ ");
		sql.append("from XG_QGZX_NEW_JTFFB t1 left join view_xsbfxx t2 on t1.xh=t2.xh) where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JtffForm model, User user) throws Exception {
		return null;
	}

	public String checkExist(JtffForm form){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_QGZX_NEW_JTFFB where xh = ? and sj = ? ");
		if(StringUtils.isNotNull(form.getId())){
			sql.append("and id<> '");
			sql.append(form.getId());
			sql.append("'");
		}
		return dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getSj()}, "num");
	}


	/** 
	 * @描述：
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月27日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getBmList() {
		String sql = " select distinct bmmc from (select * from  ZXBZ_XXBMDM order by bmdm) ";
		return dao.getListNotOut(sql, new String[]{});
	}
	

	
}
