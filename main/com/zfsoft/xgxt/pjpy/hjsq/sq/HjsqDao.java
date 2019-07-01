package com.zfsoft.xgxt.pjpy.hjsq.sq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class HjsqDao extends SuperDAOImpl<HjsqForm> {

	@Override
	public List<HashMap<String, String>> getPageList(HjsqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t.*,decode(t.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t.shzt) shztmc,");
		sql.append(" t1.XM,t1.BJDM,t1.BJMC,t1.XYDM,t1.XYMC,t1.NJ,t1.ZYDM,t1.ZYMC,t1.XB,t2.xqmc");
		sql.append(" from xg_xsxx_new_hjqk_sqb t left join view_xsbfxx t1 on t.xh = t1.XH");
		sql.append(" left join xqdzb t2 on t.xq = t2.xqdm ");
		sql.append(" )t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(HjsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	public boolean checkExistForSave(HjsqForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		paraList.add(model.getXh());
		paraList.add(model.getXn());
		paraList.add(model.getXq());
		paraList.add(model.getHjmc());
		sql.append("select id from xg_xsxx_new_hjqk_sqb t where t.xh = ? and t.xn = ? and t.xq = ? and t.hjmc = ? ");
		if(StringUtils.isNotNull(model.getId())){
			sql.append(" and id != ? ");
			paraList.add(model.getId());
		}
		String num = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "id");
		if (num != null && ! num.equals("") ){
			flag = true;
		}
		return flag;
	}


	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(HjsqForm.class);
		this.setTableName("xg_xsxx_new_hjqk_sqb");
		this.setKey("id");
	}

}
