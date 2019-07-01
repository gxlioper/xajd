package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_评奖项目_读书活动_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DshdDAO extends CommDAO {

	/**
	 * 删除读书活动申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean delDshdSqf(PjpyStuForm model, String[] pkValue, User user) {

		boolean flag = true;

		// 主键
		String pk = "xn||xq||xh||dsmc||dsrq";
		// 操作表
		String tableName = "szyq_dshdjzb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(tableName);
		sql.append(" where bzrsh = '未审核' ");

		if (pkValue != null && pkValue.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < pkValue.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(pk + "=? ");
			}
			sql.append(")");
		}

		try {

			DAO dao = DAO.getInstance();
			if (pkValue != null && pkValue.length > 0) {
				flag = dao.runUpdate(sql.toString(), pkValue);
			}

		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	/**
	 * 保存读书活动申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveDshdSqf(PjpyStuForm model, List<String[]> params,
			User user) {

		boolean flag = false;

		// 读书活动记载表
		String tableName = "szyq_dshdjzb";
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(tableName);
		sql.append("(xn,xq,xh,dsmc,dsrq,dsxd,sfhj,sqf)");
		sql.append("values(?,?,?,?,?,?,?,?)");

		try {
			flag = saveArrDate(sql.toString(), params, tableName, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getDshdList(PjpyStuForm model) {

		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 学号
		String xh = model.getXh();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id,case when length(a.dsmc) > 6 then ");
		sql.append(" substr(a.dsmc, 0, 6) || '...' ");
		sql.append(" else a.dsmc end dsmc, a.dsmc alldsmc, a.dsrq, case ");
		sql.append(" when length(a.dsxd) > 6 then substr(a.dsxd, 0, 6) || '...' ");
		sql.append(" else a.dsxd end dsxd, a.dsxd alldsxd, a.sfhj, ");
		sql.append(" a.sqf, case when bzrsh = '通过' then bzrshf ");
		sql.append(" else '0' end bzrshf, case when xysh = '通过' then ");
		sql.append(" xyshf else '0' end xyshf, case when xxsh = '通过' then ");
		sql.append(" xxshf else '0' end xxshf, case ");
		sql.append(" when clr is not null then '已处理' ");
		sql.append(" when b.xh is not null then '已申诉' ");
		sql.append(" else '申诉' end cz,b.ssnr,b.clyj,b.clr, ");
		sql.append(" (select xm from yhb c where c.yhm = b.clr) clrxm, ");
		sql.append(" bzrsh,xysh,xxsh,bzrshf bzrlrf,xyshf xylrf,xxshf xxlrf ");
		sql.append(" from (select id,xn,xq,xh,dsmc,dsrq,dsxd,sfhj,sqf, ");
		sql.append(" nvl(bzrshf, 0) bzrshf,bzrsh,nvl(xyshf, 0) xyshf, ");
		sql.append(" xysh,nvl(xxshf, 0) xxshf,xxsh from szyq_dshdjzb a ");
		sql.append(" where xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and xh = ?) a ");
		sql.append(" left join xg_pjpy_szgyyq_xsssb b on ");
		sql.append(" a.xh = b.xh and a.id = b.xmid ");
		sql.append(" order by dsrq ");
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "id", "dsmc",
						"alldsmc", "dsrq", "dsxd", "alldsxd", "sfhj", "sqf",
						"bzrshf", "xyshf", "xxshf", "cz", "ssnr", "clyj",
						"clrxm", "bzrsh", "xysh", "xxsh","bzrlrf","xylrf","xxlrf" });

		return list;
	}
	
	/**
	 * 获得读书活动信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// 学号
		String xh = model.getXh();
		// 班级
		String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];
		// 操作
		String[] cz = searchModel.getSearch_tj_cz();
		// 模糊查询
		String input_mhcx = searchModel.getInput_mhcx();
		// 查询类型
		String mhcx_lx = searchModel.getMhcx_lx();
		// 用户类型
		String yhlx = model.getYhlx();
		
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("( select a.xh,a.xm,a.sqf,a.zzf,a.cz,a.clr,");
		tableSql.append("a.clyj,a.clsj,a.tsnr,a.clrxm,a.nj,a.xydm,a.zydm,a.bjdm ");	
		tableSql.append("from ( ");
		tableSql.append("select a.xh, a.xm, nvl(b.sqf, 0) sqf, nvl(b.zzf, 0) zzf, ");
		tableSql.append("'"+xn+"' xn,'"+xq+"' xq, ");
		tableSql.append("case when a.xh = '" + xh + "' then '查看' ");
		tableSql.append("when c.clr is not null then '已处理' ");
		tableSql.append("when c.xh is not null then '已投诉' else '可投诉' end cz, ");
		tableSql.append("c.clr,c.clyj,c.clsj,c.tsnr,a.nj,a.xydm,a.zydm,a.bjdm,");
		tableSql.append("(select xm from yhb d where d.yhm = c.clr) clrxm ");
		tableSql.append("from (select a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where 1=1 ");
		tableSql.append("bz".equalsIgnoreCase(yhlx)||"stu".equalsIgnoreCase(yhlx)  ? " and a.bjdm = '" + bjdm + "'" : "");
		tableSql.append(") a ");
		tableSql.append("left join (select b.xh, sum(b.sqf) sqf, sum(b.pf) zzf ");
		tableSql.append("from szyq_dshdjzb b ");
		tableSql.append("where b.xn = '"+xn+"' ");
		tableSql.append("and b.xq = '"+xq+"' ");
		tableSql.append("group by b.xh) b on a.xh = b.xh ");
		tableSql.append("left join (select c.xh, c.btsr, c.tsnr, c.tssj, c.clr, c.clyj, c.clsj ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb c ");
		tableSql.append("where c.xmlx = 'szyq_dshdjzb' ");
		
		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
			tableSql.append("and c.xh = '" + xh + "' ");
		} else {
			tableSql.append("and exists (select 1 ");
			tableSql.append("from (select btsr, max(tssj) tssj ");
			tableSql.append("from xg_pjpy_szgyyq_xstsb ");
			tableSql.append("where xmlx = 'szyq_dshdjzb' ");
			tableSql.append("and xn = '" + xn + "' ");
			tableSql.append("and xq = '" + xq + "' ");
			tableSql.append("group by btsr) d ");
			tableSql.append("where c.btsr = d.btsr ");
			tableSql.append("and c.tssj = d.tssj) ");
		}
		
		tableSql.append("and c.xn = '"+xn+"' ");
		tableSql.append("and c.xn = '"+xn+"' ");
		tableSql.append("and c.xq = '"+xq+"') c on a.xh = c.btsr ");
		tableSql.append("order by zzf,xh ");
		tableSql.append(") a ");
		tableSql.append("where 1 = 1 ");

		String[] inputV = new String[]{};
		
		if ("stu".equalsIgnoreCase(yhlx) ||"bz".equalsIgnoreCase(yhlx)) {//班长
			// 操作
			if (cz != null && cz.length > 0) {
				tableSql.append("and ( ");
				for (int i = 0; i < cz.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("a.cz = '" + commService.unicode2Gbk(cz[i])
							+ "'");
				}
				tableSql.append(") ");
			}

			// 模糊查询
			if (!Base.isNull(input_mhcx.trim())) {

				String[] mhcx = input_mhcx.split(" ");

				if (!"all".equalsIgnoreCase(mhcx_lx)) {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("a." + mhcx_lx + " like '%" + mhcx[i]
								+ "%'");
					}
					tableSql.append(") ");

				} else {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("(a.xh like '%" + mhcx[i] + "%' ");
						tableSql.append("or ");
						tableSql.append("a.xm like '%" + mhcx[i] + "%') ");
					}
					tableSql.append(") ");
				}
			}
		} else {

			user.setUserStatus(yhlx);
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");

			tableSql.append(searchTj);
			tableSql.append(searchTjByUser);

			inputV = SearchService.getTjInput(model.getSearchModel());
		}

		tableSql.append(")");

		String query = " order by xh ";
		
		return getRsArrList(tableSql.toString(), query, inputV, new String[] {
				"xh", "xm", "sqf", "zzf", "clr", "clsj", "clrxm", "clyj",
				"tsnr", "cz" }, "", model);

	}

	/**
	 * 获得读书活动审核信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getDshdShList(
			ArrayList<String[]> dshdList, PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchModel searchModel = model.getSearchModel();

		// 用户类型
		String yhlx = model.getYhlx();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder sql = new StringBuilder();

		sql.append("select xh, bzrsh, xysh, xxsh from szyq_dshdjzb ");
		sql.append("where xn = '" + xn + "' ");
		sql.append("and xq = '" + xq + "' ");
		if (dshdList != null && dshdList.size() > 0) {
			sql.append("and ( ");
			for (int i = 0; i < dshdList.size(); i++) {
				String xh = dshdList.get(i)[0];
				if (i != 0) {
					sql.append(" or");
				}
				sql.append(" xh = '" + xh + "' ");
			}
			sql.append(") ");
		}

		StringBuilder shztTj = new StringBuilder();

		// 高级查询条件
		String[] shztlx = searchModel.getSearch_tj_shztlx();
		
		if (shztlx != null && shztlx.length > 0) {

			for (int i = 0; i < shztlx.length; i++) {
				if (i == 0) {
					shztTj.append("and( ");
				} else {
					shztTj.append(" or ");
				}
				if ("全部审核通过".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// 班长
						// shztTj.append(" bzrsh='通过' ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh='通过' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xxsh='通过' ");
					}

				} else if ("存在未审核".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// 班长
						// shztTj.append(" bzrsh='未审核' or bzrsh='需重审' ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" (xysh='未审核' or xysh='需重审') ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj
								.append(" ((xysh='通过' and xxsh='未审核') or (xysh='通过' and xxsh='需重审'))  ");
					}

				} else if ("存在需重审".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// 班长
						// shztTj.append(" bzrsh='需重审' ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh='需重审' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xxsh='需重审'  ");
					}
				} else if ("无需审核".equalsIgnoreCase(shztlx[i])) {
					if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" bzrsh<>'通过' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh<>'通过' ");
					}
				}
			}
			
			shztTj.append(" ) ");
		}
		sql.append(shztTj);
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao
				.getList(sql.toString(), new String[] {}, new String[] { "xh",
						"bzrsh", "xysh", "xxsh" });

		return list;

	}
	
	/**
	 * 获得读书活动审核信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdShList(PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchModel searchModel = model.getSearchModel();

		// 权限控制
		
		
		// 用户类型
		String yhlx = model.getYhlx();
		user.setUserStatus(yhlx);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder tableSql = new StringBuilder();

//		高级查询条件
		String[] shztlx=searchModel.getSearch_tj_shztlx();
		searchModel.setSearch_tj_shztlx(null);
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
//		高级查询输入值
		String[]  inputV = SearchService.getTjInput(searchModel);
		searchModel.setSearch_tj_shztlx(shztlx);
		
		StringBuilder shztTj=new StringBuilder();
		String userName=user.getUserName();
		if(shztlx!=null && shztlx.length>0){
			
			for(int i=0;i<shztlx.length;i++){
				if(i==0){
					shztTj.append("and( ");
				}else{
					shztTj.append(" or ");
				}
		
				if("全部审核通过".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//班长
						shztTj.append(" bzrsh='通过' ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh='通过' ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xxsh='通过' ");
					}	
				}else if("存在未审核".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//班长
						shztTj.append(" bzrsh='未审核' or bzrsh='需重审' ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" (bzrsh='通过' and xysh='未审核') or (bzrsh='通过' and xysh='需重审') ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" (xysh='通过' and xxsh='未审核') or (xysh='通过' and xxsh='需重审')  ");
					}
				}else if("存在需重审".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//班长
						shztTj.append(" bzrsh='需重审' ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh='需重审' ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xxsh='需重审'  ");
					}
				}else if("无需审核".equalsIgnoreCase(shztlx[i])){
					if("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)){//班长
						shztTj.append(" (xysh<>'未审核' and xysh<>'退回') ");
					}else if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" bzrsh<>'通过' ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh<>'通过' ");
					}	
				}
			}
			shztTj.append(" ) ");
		}
		
		tableSql.append("(");
		tableSql.append("select * from (");
		tableSql.append("select a.xn,a.xq,a.xh,a.xm,a.bjmc,sum(a.sqf) sqf, ");
		tableSql.append("a.bjdm,a.xydm,a.zydm,a.nj, ");
		tableSql.append("sum(a.bzrshf) bzrshf,sum(a.xyshf) xyshf, ");
		tableSql.append("sum(a.xxshf) xxshf,a.ss,a.ts from (select a.xh,a.xn,a.xq, ");
		tableSql.append("b.xm,b.bjdm,b.bjmc,a.sqf,b.xydm,b.zydm,b.nj, ");
		tableSql.append("case when a.bzrsh = '通过' then nvl(a.bzrshf, '0') else '0' end bzrshf, ");
		tableSql.append("case when a.xysh = '通过' then nvl(a.xyshf, '0') else '0' end xyshf, ");
		tableSql.append("case when a.xxsh = '通过' then nvl(a.xxshf, '0') else '0' end xxshf, ");
		
		tableSql.append("case when (c.ssrs = '0' or c.ssrs is null) then '否' else '是' end ss, ");
		tableSql.append("case when (d.tsrs = '0' or d.tsrs is null) then '否' else '是' end ts ");
		
		tableSql.append("from (select * from szyq_dshdjzb where 1=1 "+shztTj+" ) a ");
		tableSql.append("left join view_xsjbxx b on a.xh = b.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) ssrs ");
		tableSql.append("from (select distinct xn, xq, xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xsssb a ");
		tableSql.append("where xn = '" + xn + "' ");
		tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) c on c.xmlx = 'szyq_dshdjzb' ");
		tableSql.append("and a.xh = c.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) tsrs ");
		tableSql.append("from (select distinct xn, xq,btsr xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb a ");
		tableSql.append("where xn = '" + xn + "' ");
		tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) d on d.xmlx = 'szyq_dshdjzb' ");
		tableSql.append("and a.xh = d.xh ");
	                                  
		tableSql.append("where a.xn = '" + xn + "' ");
		tableSql.append("and a.xq = '" + xq + "') a ");
		tableSql.append("group by a.xn,a.xq,a.xh,a.xm,a.bjdm,a.bjmc,a.xydm,a.zydm,a.nj,a.ss,a.ts ");
		tableSql.append(") a ");
		
		tableSql.append(" where 1=1 ");
		
		if("bz".equalsIgnoreCase(yhlx)){//班长
			
			// 学号
			String xh = user.getUserName();
			// 班级
			String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);
				
			tableSql.append(" and bjdm = '"+bjdm+"' and xh<>'"+userName+"' ");
			
		} else {//非班长用户
			
			
			tableSql.append(searchTjByUser);
		}
		
		tableSql.append(searchTj);
		tableSql.append(")");
		
		return getRsArrList(tableSql.toString(), "", inputV, new String[] {
				"xh", "xm", "bjmc", "sqf", "bzrshf", "xyshf", "xxshf", "ss",
				"ts" }, "", model);

	}

	/**
	 * 修改读书活动信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean editDshdInfo(PjpyStuForm model, User user) throws Exception {

		DshdModel dshdModel = model.getDshdModel();
		// 申请ID
		String id = dshdModel.getId();
		// 读书名称
		String dsmc = dshdModel.getDsmc()[0];
		// 读书日期
		String dsrq = dshdModel.getDsrq()[0];
		// 读书心得
		String dsxd = dshdModel.getDsxd()[0];
		// 是否获奖
		String sfhj = dshdModel.getSfhj()[0];
		// 申请分
		String sqf = dshdModel.getSqf()[0];

		StringBuilder sql = new StringBuilder();
		sql.append("update szyq_dshdjzb set ");
		sql.append("dsmc = ? ");
		sql.append(",dsrq = ? ");
		sql.append(",dsxd = ? ");
		sql.append(",sfhj = ? ");
		sql.append(",sqf = ? ");
		sql.append("where id = ? ");

		DAO dao = DAO.getInstance();

		boolean flag = dao.runUpdate(sql.toString(), new String[] { dsmc, dsrq,
				dsxd, sfhj, sqf, id });

		return flag;
	}
}
