package com.zfsoft.xgxt.rcsw.zxzx.zxhf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZxhfDao extends SuperDAOImpl<ZxhfForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_zxzx_zxhf");
		super.setKey("hfid");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZxhfForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZxhfForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,decode(t1.sfzd,'1','是','否') sfzdmc, ");
		sql.append(" t2.bkmc,t3.hfid,case when t3.hfid is null then '未回复' else '已回复' end sfhfmc,case when t3.hfid is null then '0' else '1' end sfhf,t3.hfnr,t3.hfsj, ");
		sql.append(" t4.xm zxrxm,t4.xymc,t4.bjmc,t4.zymc,t4.xb,t4.nj,t5.xm hfrxm ");
		sql.append(" from xg_rcsw_zxzx_zxzx t1 ");
		sql.append(" left join xg_rcsw_zxzx_zxbk t2 on t1.bkid=t2.bkid ");
		sql.append(" left join xg_rcsw_zxzx_zxhf t3 on t3.zxid=t1.zxid ");
		sql.append(" left join view_xsbfxx t4 on t4.xh=t1.zxr ");
		sql.append(" left join fdyxxb t5 on t5.zgh=t3.hfr ");
		sql.append(" where t2.sfqy='1' and t1.sfcjwt='0' ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	public ZxhfForm getModel(ZxhfForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.sfzd,'1','是','否') sfzdmc, ");
		sql.append(" t2.bkmc,t3.hfid,case when t3.hfid is null then '未回复' else '已回复' end sfhfmc,case when t3.hfid is null then '0' else '1' end sfhf,t3.hfnr, ");
		sql.append(" t4.xm zxrxm,t4.xymc,t4.bjmc ");
		sql.append(" from xg_rcsw_zxzx_zxzx t1 ");
		sql.append(" left join xg_rcsw_zxzx_zxbk t2 on t1.bkid=t2.bkid ");
		sql.append(" left join xg_rcsw_zxzx_zxhf t3 on t3.zxid=t1.zxid ");
		sql.append(" left join view_xsbfxx t4 on t4.xh=t1.zxr ");
		sql.append(" where t1.zxid=? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getZxid() });
		ZxhfForm model=new ZxhfForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * 删除
	 */
	public boolean deleteZxhf(String values) throws Exception {
		String[] arr = values.split(",");
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < arr.length; i++) {
			params.add(new String[]{ arr[i] });
		}
		String sql = "delete from xg_rcsw_zxzx_zxzx where zxid=?";
		int[] num = dao.runBatch(sql, params);
		boolean rs = dao.checkBatchResult(num);
		if(rs){
			sql = "delete from xg_rcsw_zxzx_zxhf where zxid=?";
			num = dao.runBatch(sql, params);
			rs = dao.checkBatchResult(num);
		}
		return rs;
	}
	
}
