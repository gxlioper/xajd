package com.zfsoft.xgxt.pjpy.hjsq.jg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class HjjgDao extends SuperDAOImpl<HjjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(HjjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(HjjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select t.*,");
		sql.append(" t1.XM,t1.BJDM,t1.BJMC,t1.XYDM,t1.XYMC,t1.NJ,t1.ZYDM,t1.ZYMC,t1.XB,t2.xqmc");
		sql.append(" from xg_xsxx_new_hjqk_jgb t left join view_xsbfxx t1 on t.xh = t1.XH");
		sql.append(" left join xqdzb t2 on t.xq = t2.xqdm ");
		sql.append(" )t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(HjjgForm.class);
		this.setTableName("xg_xsxx_new_hjqk_jgb");
		this.setKey("id");
	}
	
	/**
	 * 
	 * @描述: 验证是否可以保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-12 上午09:57:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExistForSave(HjjgForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		paraList.add(model.getXh());
		paraList.add(model.getXn());
		paraList.add(model.getXq());
		paraList.add(model.getHjmc());
		sql.append("select id from xg_xsxx_new_hjqk_jgb t where t.xh = ? and t.xn = ? and t.xq = ? and t.hjmc = ? ");
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
	
	/**
	 * 
	 * @描述: 获取获奖信息List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-22 下午02:12:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getHjxxPageList(HjjgForm t, User user)
	throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select * from (select t.*,");
		sql.append(" t1.XM,t1.BJDM,t1.BJMC,t1.XYDM,t1.XYMC,t1.NJ,t1.ZYDM,t1.ZYMC,t1.XB,t2.xqmc,case when t3.id is not null then 'yes' else 'no' end done");
		sql.append(" from xg_xsxx_new_hjqk_jgb t left join xg_pjpy_hjxxjl t3  on t3.id = t.id left join view_xsbfxx t1 on t.xh = t1.XH");
		sql.append(" left join xqdzb t2 on t.xq = t2.xqdm ");
		sql.append(" )t where 1=1");
		if(StringUtils.isNotNull(t.getSfysq())){
			sql.append(" and done = ?");
			paraList.add(t.getSfysq());
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		paraList.addAll(Arrays.asList(inputV));
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除获奖信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-22 下午05:16:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public boolean delHjxx(List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_pjpy_hjxxjl where id = ?");
		return dao.runBatchBoolean(sql.toString(), params);
	}
}
