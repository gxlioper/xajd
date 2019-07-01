package com.zfsoft.xgxt.rcsw.zxzx.xszxzx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XszxzxDao extends SuperDAOImpl<XszxzxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_zxzx_zxzx");
		super.setKey("zxid");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszxzxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszxzxForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
//		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
//		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,decode(t1.sfcjwt,'0',t1.zxsj,'') zxsjtemp,decode(t1.sfcjwt,'0',t1.zxr,'') zxrtemp,decode(t1.sfzd,'1','是','否') sfzdmc, ");
		sql.append(" t2.bkmc,case when t3.hfid is null then '未回复' else '已回复' end sfhfmc,case when t3.hfid is null then '0' else '1' end sfhf,t3.hfnr, ");
		sql.append(" t4.xm zxrxm,t4.xymc,t4.bjmc ");
		sql.append(" from xg_rcsw_zxzx_zxzx t1 ");
		sql.append(" left join xg_rcsw_zxzx_zxbk t2 on t1.bkid=t2.bkid ");
		sql.append(" left join xg_rcsw_zxzx_zxhf t3 on t3.zxid=t1.zxid ");
		sql.append(" left join view_xsbfxx t4 on t4.xh=t1.zxr ");
		sql.append(" where t2.sfqy='1' ");
		if("cjwt".equals(t.getTab())){
			sql.append(" and t1.sfcjwt='1' ");
		}else if("wdzx".equals(t.getTab())){
			sql.append(" and t1.zxr='" + user.getUserName() + "' ");
		}else if("all".equals(t.getTab())){
			sql.append(" and t1.sfcjwt='0' ");
		}
		if(!StringUtil.isNull(t.getBkid())){
			sql.append(" and t1.bkid='" + t.getBkid() + "' ");
		}
		if(!StringUtil.isNull(t.getZxzt())){
			sql.append(" and t1.zxzt like '%" + t.getZxzt() + "%' ");
		}
//		sql.append(" ) a where 1 = 1 ");
//		sql.append(searchTj);
		return getPageList(t, sql.toString(), new String[]{  });
	}
	@Override
	public XszxzxForm getModel(XszxzxForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.sfcjwt,'0',t1.zxsj,'') zxsjtemp,decode(t1.sfcjwt,'0',t1.zxr,'') zxrtemp,decode(t1.sfzd,'1','是','否') sfzdmc, ");
		sql.append(" t2.bkmc,case when t3.hfid is null then '未回复' else '已回复' end sfhfmc,t3.hfnr, ");
		sql.append(" t4.xm zxrxm,t4.xymc,t4.bjmc ");
		sql.append(" from xg_rcsw_zxzx_zxzx t1 ");
		sql.append(" left join xg_rcsw_zxzx_zxbk t2 on t1.bkid=t2.bkid ");
		sql.append(" left join xg_rcsw_zxzx_zxhf t3 on t3.zxid=t1.zxid ");
		sql.append(" left join view_xsbfxx t4 on t4.xh=t1.zxr ");
		sql.append(" where t1.zxid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getZxid() });
		XszxzxForm model=new XszxzxForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * 删除在线咨询
	 */
	public boolean deleteXszxzx(String values) throws Exception {
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
