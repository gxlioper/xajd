/**
 * @部门:学工产品事业部
 * @日期：2016-3-24 上午08:43:41 
 */
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药_学情月报管理模块
 * @类功能描述: TODO(北京中医药_学情月报_班级月报申请)
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-24 上午08:43:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxqybsqDao extends SuperDAOImpl<BjxqybsqForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BjxqybsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(BjxqybsqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();

		sql.append(" select a.*,b.splc splcnew from ( select t1.sqid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd, ");
		sql.append(" substr(t1.yf,6,2)yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj, ");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '','无需审核', t1.shzt) shztmc, ");
		sql.append(" t4.xqmc,t1.txr,t1.shzt, ");
		sql.append(" t1.splc,t1.bjdm,t2.nj,t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_sq t1  ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm ) a,xg_bjzyy_xqyb_bjyb_cssz b where 1 = 1  ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {

		// TODO 自动生成方法存根
		super.setKey("sqid");
		super.setTableName("xg_bjzyy_xqyb_bjyb_sq");
		super.setClass(BjxqybsqForm.class);

	}

	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-更新班级月报申请)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:29:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateBjxqybsq(BjxqybsqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE xg_bjzyy_xqyb_bjyb_sq ");
		sql.append(" set ");
		sql.append(" shzt = ? ,splc = ? ");
		sql.append(" where sqid = ? ");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();

		return dao.update(sql.toString(), inputV) > 0 ? true : false;

	}

	/**
	 * 
	 * @描述:TODO(班级学情月报管理-班级月报申请-按申请ID获取申请详细信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:43:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBjxqybsqInfo(BjxqybsqForm model) {

		StringBuffer sql = new StringBuffer();

		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj, ");
		sql.append("  decode(t1.shzt, '0','未提交', '1', '通过', '2', '不通过', '3','退回', '4', '需重审', '5', '审核中', '','无需审核', t1.shzt) shztmc , ");
		sql.append(" t4.xqmc ,");
		sql.append(" t1.txr,t1.shzt,t1.splc,t1.bjdm,t2.xymc,t2.bjmc,t3.xm lrrxm from xg_bjzyy_xqyb_bjyb_sq t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm left join fdyxxb t3 on t1.txr = t3.zgh  ");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm where  ");
		sql.append("  t1.sqid = ? ");

		return dao.getMapNotOut(sql.toString(),new String[] { model.getSqid() });
	}

	/**
	 * 
	 * @描述:TODO(按照班级代码,学年,月份判断班级学情月报申请表中是否已经存在该班)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-7 下午04:34:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return String 返回类型
	 * @throws
	 */
	public String checkExistForBjxqybsqSave(BjxqybsqForm model) {
		StringBuffer sql = new StringBuffer(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_sq where  xn = ?  and xq = ? and yf = ? and bjdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXn(),
				model.getXq(), model.getYf(), model.getBjdm() }, "num");
		return num;
	}

	/**
	 * 
	 * @描述:TODO(按照班级代码, 学年,月份,申请ID判断班级学情月报申请表中是否已经存在该班)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-7 下午04:50:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return String 返回类型
	 * @throws
	 */
	public String checkExistForBjxqybsqUpdate(BjxqybsqForm model) {
		StringBuffer sql = new StringBuffer(
				" select count(1) num from xg_bjzyy_xqyb_bjyb_sq where bjdm = ? and xn = ?  and xq = ? and yf = ? and sqid <> ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {
				model.getBjdm(), model.getXn(), model.getXq(), model.getYf(),
				model.getSqid() }, "num");
		return num;
	}

	/**
	 * 
	 * @描述:TODO(学情月报_班级月报申请-从班级月报_参数设置中获取审批流程)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:45:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 * @throws
	 */
	public String getShlcID() {

		String sql = " select splc from xg_bjzyy_xqyb_bjyb_cssz ";
		return dao.getOneRs(sql, new String[] {}, "splc");

	}

	/**
	 * 
	 * @描述:TODO(学情月报_班级月报申请-更改学情月报申请审核状态)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-8 上午09:51:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancelBjxqybsq(BjxqybsqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE xg_bjzyy_xqyb_bjyb_sq ");
		sql.append(" set ");
		sql.append(" shzt = ? ,");
		sql.append(" splc = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();

		return dao.update(sql.toString(), inputV) > 0 ? true : false;
	}

	/**
	 * 
	 * @描述:TODO(学情月报_班级月报申请-只有未提交才可以撤销)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:49:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean isCanDel(String sqid) {

		StringBuffer sb = new StringBuffer();

		sb.append("select * from xg_bjzyy_xqyb_bjyb_sq where sqid=? ");
		Map<String, String> map = dao.getMapNotOut(sb.toString(),
				new String[] { sqid });
		String bbsqzt = map.get("shzt");
		// 如果未提交才可以提交
		return null == bbsqzt || bbsqzt.equals("0") ? true : false;

	}

	/**
	 * 
	 * @描述:TODO(学情月报_班级月报申请-按申请ID获取班级信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 上午11:50:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBjxqybsq(String sqid) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.bjdm bjdm,b.bjmc bjmc from xg_bjzyy_xqyb_bjyb_sq a,view_njxyzybj_all b  ");
		sb.append(" where a.bjdm = b.bjdm and a.sqid=? ");
		return dao.getMapNotOut(sb.toString(), new String[] { sqid });
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理-查询班级)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-13 下午03:15:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjList(BjxqybsqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1",
				"xydm", "bjdm");

		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.bjrs,b.* from ( ");
		sql.append(" select bjdm,count(1) bjrs  from view_xsjbxx group by bjdm ");
		sql.append(" ) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);

		return getPageList(t, sql.toString(), inputValue);
	}

}
