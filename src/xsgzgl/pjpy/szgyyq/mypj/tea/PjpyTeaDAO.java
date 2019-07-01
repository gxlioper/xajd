package xsgzgl.pjpy.szgyyq.mypj.tea;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.model.FivesModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjDAO;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(老师)_DAO类
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

public class PjpyTeaDAO extends PjpyMypjDAO {

	/**
	 * 获得班级学生列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getBjxsList(PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		SearchModel searchModel = model.getSearchModel();
		String [] xn=searchModel.getSearch_tj_xn();
		String [] xq=searchModel.getSearch_tj_xq();
		searchModel.setSearch_tj_xn(null);
		searchModel.setSearch_tj_xq(null);
		
		user.setUserStatus(model.getYhlx());
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[]  inputV = SearchService.getTjInput(searchModel);
		searchModel.setSearch_tj_xn(xn);
		searchModel.setSearch_tj_xq(xq);
		
		// 学号
		String xh = model.getXh();
		// 班级
		String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);

		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(select a.xh, a.xm ");
		tableSql.append("from view_xsjbxx a ");
		tableSql.append("where 1=1 ");
		if ("bz".equalsIgnoreCase(model.getYhlx())) {
			tableSql.append("and bjdm = '"+bjdm+"' ");
			tableSql.append("and xh <> '"+xh+"' ");
		}else{
			tableSql.append(searchTjByUser);
		}
		tableSql.append(searchTj);
		tableSql.append(" order by xh ");
		tableSql.append(") ");
		
		
		return getRsArrList(tableSql.toString(), "", inputV,
				new String[] { "xh", "xm" }, "", model);
	}

	/**
	 * 获得班级学生列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getFivesList(PjpyTeaForm model,
			ArrayList<String[]> xsList, User user) {

		SearchModel searchModel = model.getSearchModel();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];
		// 用户类型
		String yhlx = model.getYhlx();

		StringBuilder sql = new StringBuilder();

		sql.append(" select xh,jjf,nvl(sqf,0) sqf,nvl(xyshf,0) xyshf, ");
		sql.append(" xysh,nvl(xxshf,0) xxshf,xxsh ");
		sql.append(" from szyc_5sb where 1=1 ");
		sql.append(" and xn = ? ");
		sql.append(" and xq = ? ");

		if (xsList != null && xsList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < xsList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xh = '" + xsList.get(i)[0] + "' ");
			}
			sql.append(" ) ");
		}
		if ("bz".equalsIgnoreCase(yhlx)) {
			sql.append(" and xh <> '" + user.getUserName() + "' ");
		}

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq }, new String[] { "xh", "jjf", "sqf",
						"xyshf", "xysh", "xxshf", "xxsh" });

		return list;
	}

	/**
	 * 获得加减分原因列表
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getYyList() {
		DAO dao = DAO.getInstance();
		String sql = "select dm,mc from szyc_jjfyyb order by dm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

	/**
	 * 获得已申请的5S分列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFivesList(PjpyTeaForm model) {

		// 学年
		String xn = Base.currXn;
		// 学期
		String xq = Base.currXq;
		// 学号
		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select a.id,a.jfrq,a.jfyy, case when a.fzxm='grszf' then '个人素质分' ");
		sql
				.append(" when a.fzxm='jsssf' then '教室与宿舍5S分' when a.fzxm='ktf' then '课堂5S分' ");
		sql
				.append(" when a.fzxm='cxf' then '诚信分' when a.fzxm='qtf' then '其他分' end fzxm, ");
		sql
				.append(" a.sqf,nvl(a.xyshf,0) xyshf,nvl(a.xxshf,0) xxshf,a.xysh,a.xxsh,a.jjf, ");
		sql
				.append(" case when xysh = '未审核' and xxsh = '未审核' then 'yes' else 'no' end shzt, ");
		sql.append(" (select b.mc from szyc_jjfyyb b where a.yy=b.dm) yy ");
		sql.append(" from szyc_5sb a ");
		sql.append(" where xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and xh = ? ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "id", "fzxm",
						"jfrq", "yy", "jfyy", "jjf", "sqf", "xyshf", "xysh",
						"xxshf", "xxsh", "shzt" });

		return list;
	}

	/**
	 * 修改5S分
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean editFives(FivesModel fivesModel, User user) {

		// ID
		String[] id = fivesModel.getId();
		// 申请分
		String[] sqf = fivesModel.getSqf();

		StringBuilder sql = new StringBuilder();
		sql.append(" update szyc_5sb set sqf = ? ");
		sql.append(" where id = ? ");

		boolean flag = false;

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < id.length; i++) {
			String[] value = new String[] { sqf[i], id[i] };
			params.add(value);
		}

		try {
			flag = saveArrDate(sql.toString(), params, "szyc_5sb", user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存审核分
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveShf(PjpyTeaForm model, User user) {

		// 用户类型
		String yhlx = model.getYhlx();
		// 项目代码
		String xmdm = model.getXmdm();
		// ID
		String[] id = model.getId();
		// 审核分
		String[] shf = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(xmdm);

		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班长或班主任
			sql.append(" set bzrshf = ? ");
			// 班主任审核分
			shf = model.getBzrshf();
		} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
			sql.append(" set xyshf = ? ");
			// 学院审核分
			shf = model.getXyshf();
		} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
			sql.append(" set xxshf = ? ");
			// 学校审核分
			shf = model.getXxshf();
		}
		sql.append(" where id = ? ");

		boolean flag = false;

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < id.length; i++) {
			String[] value = new String[] { shf[i], id[i] };
			params.add(value);
		}

		try {
			flag = saveArrDate(sql.toString(), params, xmdm, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存审核状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveShzt(PjpyTeaForm model, User user) {

		DAO dao = DAO.getInstance();
		// 用户类型
		String yhlx = model.getYhlx();
		// 项目代码
		String xmdm = model.getXmdm();
		// ID
		String[] id = model.getId();
		// 班主任审核分
		String[] bzrshf = model.getBzrshf();
		// 学院审核分
		String[] xyshf = model.getXyshf();
		// 学校审核分
		String[] xxshf = model.getXxshf();
		// 审核人
		String shr = user.getUserName();
		// 审核人
		String shsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		// 审核状态
		String shzt = model.getShzt();
		// 评分
		String pf = "";
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)
				|| "szyq_yybdjzb".equalsIgnoreCase(xmdm)
				|| "szyq_ivtltb".equalsIgnoreCase(xmdm)
				|| "szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 读书活动 语言表达 ivt论坛
			// 文体活动
			pf = "pf";
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)
				|| "szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 组织能力 社会实践
			pf = "shfz";
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5S
			pf = "fz";
		}

		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(xmdm);

		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班长或班主任
			sql.append(" set bzrshf = ? ");
			sql.append(" ,bzrshsj = ? ");
			sql.append(" ,bzrshr = ? ");
			sql.append(" ,bzrsh = ? ");
			sql.append(" ,xysh = '未审核' ");
		} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
			sql.append(" set xyshf = ? ");
			sql.append(" ,xyshsj = ? ");
			sql.append(" ,xyshr = ? ");
			sql.append(" ,xysh = ? ");
			sql.append(" ,xxsh = '未审核' ");
			if ("tg".equalsIgnoreCase(shzt)) {
				sql.append(" ," + pf + " = ? ");
			} else if ("th".equalsIgnoreCase(shzt)) {
				sql.append(" ," + pf + " = '0' ");
				sql.append(" ,bzrsh = ? ");
			} else {
				sql.append(" ," + pf + " = '0' ");
			}
		} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
			sql.append(" set xxshf = ? ");
			sql.append(" ,xxshsj = ? ");
			sql.append(" ,xxshr = ? ");
			sql.append(" ,xxsh = ? ");

			if ("tg".equalsIgnoreCase(shzt)) {
				sql.append(" ," + pf + " = ? ");
			} else if ("th".equalsIgnoreCase(shzt)) {
				sql.append(" ," + pf + " = '0' ");
				sql.append(" ,xysh = ? ");
			} else {
				sql.append(" ," + pf + " = '0' ");
			}
		}

		sql.append(" where id = ? ");

		boolean flag = false;

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < id.length; i++) {

			String[] value = null;

			if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班长或班主任

				if ("tg".equalsIgnoreCase(shzt)) {
					value = new String[] { bzrshf[i], shsj, shr, "通过", id[i] };
				} else if ("btg".equalsIgnoreCase(shzt)) {
					value = new String[] { bzrshf[i], shsj, shr, "不通过", id[i] };
				}

			} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
				if ("tg".equalsIgnoreCase(shzt)) {
					value = new String[] { xyshf[i], shsj, shr, "通过", xyshf[i],
							id[i] };
				} else if ("btg".equalsIgnoreCase(shzt)) {
					value = new String[] { xyshf[i], shsj, shr, "不通过", id[i] };
				} else if ("th".equalsIgnoreCase(shzt)) {
					value = new String[] { xyshf[i], shsj, shr, "退回", "需重审",
							id[i] };
				}
			} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
				if ("tg".equalsIgnoreCase(shzt)) {
					value = new String[] { xxshf[i], shsj, shr, "通过", xxshf[i],
							id[i] };
				} else if ("btg".equalsIgnoreCase(shzt)) {
					value = new String[] { xxshf[i], shsj, shr, "不通过", id[i] };
				} else if ("th".equalsIgnoreCase(shzt)) {
					value = new String[] { xxshf[i], shsj, shr, "退回", "需重审",
							id[i] };
				}
			}
			params.add(value);
		}

		try {
			flag = saveArrDate(sql.toString(), params, xmdm, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存批量审核状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean savePlShzt(PjpyTeaForm model, User user) {

		DAO dao = DAO.getInstance();
		// 用户类型
		String yhlx = model.getYhlx();
		// 项目代码
		String xmdm = model.getXmdm();
		// 学号
		String[] xh = model.getShxh();
		// 审核人
		String shr = user.getUserName();
		// 审核人
		String shsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		// 审核状态
		String shzt = model.getShzt();
		// 评分
		String pf = "";
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)
				|| "szyq_yybdjzb".equalsIgnoreCase(xmdm)
				|| "szyq_ivtltb".equalsIgnoreCase(xmdm)
				|| "szyq_xthddjb".equalsIgnoreCase(xmdm)) {// 读书活动 语言表达 ivt论坛
			// 文体活动
			pf = "pf";
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)
				|| "szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// 组织能力 社会实践
			pf = "shfz";
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5S
			pf = "fz";
		}

		StringBuilder sql = new StringBuilder();
		sql.append(" update ");
		sql.append(xmdm);

		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班长或班主任
			sql.append(" set bzrshf = sqf ");
			sql.append(" ,bzrshsj = ? ");
			sql.append(" ,bzrshr = ? ");
			sql.append(" ,bzrsh = ? ");
			sql.append(" ,xysh = '未审核' ");
		} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
			if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
				sql.append(" set xyshf = bzrshf ");
			}else{
				sql.append(" set xyshf = sqf ");
			}
			sql.append(" ,xyshsj = ? ");
			sql.append(" ,xyshr = ? ");
			sql.append(" ,xysh = ? ");
			sql.append(" ,xxsh = '未审核' ");
			
			if ("tg".equalsIgnoreCase(shzt)) {
				if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
					sql.append(" ," + pf + " = bzrshf ");
				}
			} else if ("th".equalsIgnoreCase(shzt)) {
				sql.append(" ," + pf + " = '0' ");
				if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
					sql.append(" ,bzrsh = '需重审' ");
				}
			} else {
				sql.append(" ," + pf + " = '0' ");

			}
		} else if ("xx".equalsIgnoreCase(yhlx)) {// 学院
			sql.append(" set xxshf = xyshf ");
			sql.append(" ,xxshsj = ? ");
			sql.append(" ,xxshr = ? ");
			sql.append(" ,xxsh = ? ");
			if ("tg".equalsIgnoreCase(shzt)) {
				sql.append(" ," + pf + " = xyshf ");
			} else if ("th".equalsIgnoreCase(shzt)) {
				sql.append(" ," + pf + " = '0' ");
				sql.append(" ,xysh = '需重审' ");
			} else {
				sql.append(" ," + pf + " = '0' ");
			}
		}

		sql.append(" where xh = ? ");

		if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班长或班主任
			sql.append(" and  (xysh = '未审核' or xysh = '退回') ");
		} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
			if (!"szyc_5sb".equalsIgnoreCase(xmdm)) {
				sql.append(" and  bzrsh = '通过' ");
			}
			sql.append(" and  (xxsh = '未审核' or xxsh = '退回') ");
		} else if ("xx".equalsIgnoreCase(yhlx)) {// 学院
			sql.append(" and  xysh = '通过' ");
		}

		boolean flag = false;

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < xh.length; i++) {

			String[] value = null;

			if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {// 班长或班主任
				if ("tg".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "通过", xh[i] };
				} else if ("btg".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "不通过", xh[i] };
				}
			} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
				if ("tg".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "通过", xh[i] };
				} else if ("btg".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "不通过", xh[i] };
				} else if ("th".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "退回", xh[i] };
				}
			} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
				if ("tg".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "通过", xh[i] };
				} else if ("btg".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "不通过", xh[i] };
				} else if ("th".equalsIgnoreCase(shzt)) {
					value = new String[] { shsj, shr, "退回", xh[i] };
				}
			}
			params.add(value);
		}

		try {
			flag = saveArrDate(sql.toString(), params, xmdm, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 获得学生投诉信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXstsInfo(
			ArrayList<String[]> xhList, PjpyTeaForm model, User user) {

		SearchModel searchModel = model.getSearchModel();

		// 用户类型
		String yhlx = model.getYhlx();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];
		// 项目代码
		String xmdm = model.getXmdm();

		StringBuilder sql = new StringBuilder();

		sql.append("select tsnr, tssj, xh tsr, btsr ");
		sql.append(" from xg_pjpy_szgyyq_xstsb ");
		sql.append("where xn = '" + xn + "' ");
		sql.append("and xq = '" + xq + "' ");
		sql.append("and xmlx = '" + xmdm + "' ");

		if (xhList != null && xhList.size() > 0) {
			sql.append("and ( ");
			for (int i = 0; i < xhList.size(); i++) {
				String xh = xhList.get(i)[0];
				if (i != 0) {
					sql.append(" or");
				}
				sql.append(" btsr = '" + xh + "' ");
			}
			sql.append(") ");
		}
		sql.append("and clr is null ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao
				.getList(sql.toString(), new String[] {}, new String[] {
						"btsr", "tsnr", "tssj", "tsr" });

		return list;

	}

	/**
	 * 获得学生申诉列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsssList(PjpyTeaForm model,
			User user) {

		// 用户类型
		String yhlx = model.getYhlx();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 学号
		String xh = model.getXh();
		// 项目代码
		String xmlx = model.getXmdm();

		StringBuilder sql = new StringBuilder();

		sql.append("select xmid,ssnr,sssj,clyj, ");
		sql.append("(select b.xm from yhb b where a.clr = b.yhm) clr ");
		sql.append("from xg_pjpy_szgyyq_xsssb a ");
		sql.append("where xn = '" + xn + "' ");
		sql.append("and xq = '" + xq + "' ");
		sql.append("and xh = '" + xh + "' ");
		sql.append("and xmlx = '" + xmlx + "' ");
		// sql.append("and clr is null ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xmid", "ssnr", "sssj", "clr",
						"clyj" });

		return list;

	}

	// ==================以上Made By 伟大的骆======================

	/**
	 * 获取中和测评列表
	 * 
	 * @author gaobb
	 */
	public List<String[]> getZhcpList(PjpyTeaForm model,
			User user) throws Exception {
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		user.setUserStatus(model.getYhlx());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		searchTj += searchTjByUser;

		StringBuilder sql = new StringBuilder();
		List<String[]> list = new ArrayList<String[]>();

		sql.append("select rownum r,a.* from xg_view_szgy_zhszcphzlsb a where 1=1");

		String[] output = new String[] { "xh", "xm", "bjmc", "wsmkf", "yybdf",
				"dshdf", "ivtlt", "wthd", "zznl", "shsj", "zhszf", "zhszfpm" };
		
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV,
					output, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 计算分数排名
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author gaobb
	 */
	public synchronized boolean jsfspm(PjpyTeaForm model,
			HttpServletRequest request) throws Exception {

		DAO dao = DAO.getInstance();
		String nowTime = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		System.out.println("自动计算时间:" + nowTime);
		
		boolean b = false;
		String tj = getJsfspmTj(model);// 计算分数排名范围
		b = jsfs(tj,model);// 计算分数
		b = jszfpm(tj);// 计算排名
		return b;
	}

	/**
	 * 获取苏州工业综合素质测评分数设定表相关信息
	 * 
	 * @return
	 */
	public HashMap<String, HashMap<String, String>> getSzgy_zhszycfsszb() {
		HashMap<String, HashMap<String, String>> rs = new HashMap<String, HashMap<String, String>>();
		DAO dao = DAO.getInstance();
		String sql = "select * from szgy_zhszycfsszb";
		List<HashMap<String, String>> list = dao.getListNotOut(sql,
				new String[] {});
		for (int i = 0; i < list.size(); i++) {
			rs.put(list.get(i).get("zxmdm"), list.get(i));
		}
		return rs;
	}

	/**
	 * 获取计算分数排名条件
	 * 
	 * @param model
	 * @return
	 */
	public String getJsfspmTj(PjpyTeaForm model) {
		StringBuffer tj = new StringBuffer(" where a.xn='" + Base.currXn
				+ "' and a.xq='" + Base.currXq + "' ");
		tj.append(" and exists (select 1 from " + Base.xsxxb
				+ " b where a.xh=b.xh ");
		String nj = model.getNj();
		if (!Base.isNull(nj)) {
			tj.append(" and nj='" + nj + "'");
		}
		String xydm = model.getXydm();
		if (!Base.isNull(xydm)) {
			tj.append(" and xydm='" + xydm + "'");
		}
		String zydm = model.getZydm();
		if (!Base.isNull(zydm)) {
			tj.append(" and zydm='" + zydm + "'");
		}
		String bjdm = model.getBjdm();
		if (!Base.isNull(bjdm)) {
			tj.append(" and bjdm='" + bjdm + "'");
		}
		tj.append(")");
		return tj.toString();
	}
	
	/**
	 * 获取计算分数排名条件_基础分
	 * 
	 * @param model
	 * @return
	 */
	public String getJsfspmTj_jcf(PjpyTeaForm model) {
		StringBuffer tj = new StringBuffer();
		String nj = model.getNj();
		if (!Base.isNull(nj)) {
			tj.append(" and nj='" + nj + "'");
		}
		String xydm = model.getXydm();
		if (!Base.isNull(xydm)) {
			tj.append(" and xydm='" + xydm + "'");
		}
		String zydm = model.getZydm();
		if (!Base.isNull(zydm)) {
			tj.append(" and zydm='" + zydm + "'");
		}
		String bjdm = model.getBjdm();
		if (!Base.isNull(bjdm)) {
			tj.append(" and bjdm='" + bjdm + "'");
		}
		return tj.toString();
	}

	/**
	 * 计算各项目分数
	 * 
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public boolean jsfs(String tj,PjpyTeaForm model) throws Exception {
		boolean b = false;
		HashMap<String, HashMap<String, String>> xmpz = getSzgy_zhszycfsszb();
		// 1.5S
		HashMap<String, String> map = xmpz.get("wsmk");
		b = jsfsOne("szyc_5sb", "fz", map.get("zxmjcf"), map.get("zxmzgf"), tj,
				"wsmkf",model);
		// 2.语言表达
		map = xmpz.get("yybd");
		b = jsfsOne("szyq_yybdjzb", "pf", map.get("zxmjcf"), map.get("zxmzgf"),
				tj, "yybdf",model);
		// 3.读书活动
		map = xmpz.get("dshd");
		b = jsfsOne("szyq_dshdjzb", "pf", map.get("zxmjcf"), map.get("zxmzgf"),
				tj, "dshdf",model);
		// 4.ivt论坛
		map = xmpz.get("ivtlt");
		b = jsfsOne("szyq_ivtltb", "pf", map.get("zxmjcf"), map.get("zxmzgf"),
				tj, "ivtlt",model);
		// 5.文体活动
		map = xmpz.get("wthd");
		b = jsfsOne("szyq_xthddjb", "pf", map.get("zxmjcf"), map.get("zxmzgf"),
				tj, "wthd",model);
		// 6.组织能力
		map = xmpz.get("zznl");
		b = jsfsOne("szyc_zznlfzb", "shfz", map.get("zxmjcf"), map
				.get("zxmzgf"), tj, "zznl",model);
		// 7.社会实践
		map = xmpz.get("shsj");
		b = jsfsOne("szyc_shsjfzb", "shfz", map.get("zxmjcf"), map
				.get("zxmzgf"), tj, "shsj",model);

		return b;
	}

	/**
	 * 计算单个项目分数
	 * 
	 * @param table
	 *            项目表
	 * @param fzField
	 *            项目表对应的分值字段
	 * @param jcf
	 *            基础分
	 * @param zgf
	 *            最高分
	 * @param tj
	 *            sql条件
	 * @param gxField
	 *            更新szgy_zhszcphzlsb对应分值字段
	 * @return
	 * @throws Exception
	 */
	public boolean jsfsOne(String table, String fzField, String jcf,
			String zgf, String tj, String gxField,PjpyTeaForm model) throws Exception {
		boolean b = false;
		DAO dao = new DAO();
		// 首先清除临时表中的数据
		String sql = "delete from szgy_zhszcphzlsb_zhszfpm_temp";
		b = dao.runUpdate(sql, new String[] {});
		if (!b) {
			return b;
		}
		// 将计算的单个项目的成绩放在临时表中
		sql = "insert into szgy_zhszcphzlsb_zhszfpm_temp(xh,fz)"
				+ "select xh,(case when fz>" + zgf + " then " + zgf
				+ " else fz end) fz from( " + "select xh,fz+" + jcf
				+ " fz from ( " + "select xn,xq,xh,sum(fz) fz from ( "
				+ "select xn,xq,xh,(case when jjf='减分' then '-'||nvl("
				+ fzField + ",'0') else nvl(" + fzField + ",'0') end) fz from "
				+ table + " a " + tj + ") group by xn,xq,xh))";
		b = dao.runUpdate(sql, new String[] {});
		if (!b) {
			return b;
		}
		//将没有记录的学生的分数默认为基础分插入
		String tj_jcf=getJsfspmTj_jcf(model);
		sql = "insert into szgy_zhszcphzlsb_zhszfpm_temp(xh,fz) "+
              "select a.xh,'"+jcf+"' fz from "+Base.xsxxb+" a where not " +
              "exists (select 1 from szgy_zhszcphzlsb_zhszfpm_temp b where a.xh=b.xh) "+tj_jcf;
		b = dao.runUpdate(sql, new String[] {});
		if (!b) {
			return b;
		}
		// 将计算所得项目的成绩更新到szgy_zhszcphzlsb表中
		sql = "update szgy_zhszcphzlsb a set " + gxField
				+ "=(select fz from szgy_zhszcphzlsb_zhszfpm_temp b "
				+ "where a.xh=b.xh) " + tj;
		b = dao.runUpdate(sql, new String[] {});
		if (!b) {
			return b;
		}
		//将计算所得项目的成绩更新到szgy_zhszcphzlsb表中
		sql="insert into szgy_zhszcphzlsb(xn,xq,xh,"+gxField+") " +
			"select '"+Base.currXn+"' xn,'"+Base.currXq+"' xq,xh,fz from szgy_zhszcphzlsb_zhszfpm_temp a "+ 
			"where not exists(select 1 from szgy_zhszcphzlsb x where x.xn='"+Base.currXn+"' and x.xq='"+Base.currXq+"' and x.xh=a.xh)";
		b=dao.runUpdate(sql, new String[]{});
		if(!b){
			return b;
		}
		return b;
	}

	/**
	 * 计算总分和排名
	 * 
	 * @param tj
	 * @return
	 * @throws Exception
	 */
	public boolean jszfpm(String tj) throws Exception {
		boolean b = false;
		DAO dao = new DAO();
		// 首先清除临时表中的数据
		String sql = "delete from szgy_zhszcphzlsb_zhszfpm_temp";
		b = dao.runUpdate(sql, new String[] {});
		if (!b) {
			return b;
		}
		// 计算综合测评分
		sql = "update szgy_zhszcphzlsb a set "
				+ "zhszf=nvl(wsmkf,0)+nvl(yybdf,0)+nvl(dshdf,0)+nvl(ivtlt,0)+nvl(wthd,0)+nvl(zznl,0)+nvl(shsj,0)"
				+ tj;
		b = dao.runUpdate(sql, new String[] {});
		if (!b) {
			return b;
		}
		// 计算排名，将排名放入临时表中,排名放在分值中
		sql = "insert into szgy_zhszcphzlsb_zhszfpm_temp(xn,xq,xh,fz) "
				+ "select a.xn,a.xq,a.xh,(rank() over(partition by a.xn,a.xq,c.bjdm order by to_number(a.zhszf) desc nulls last)) zhszfpm from "
				+ "szgy_zhszcphzlsb a,"+Base.xsxxb+" c " + tj + " and a.xh=c.xh ";
		b = dao.runUpdate(sql, new String[] {});
		
		if (!b) {
			return b;
		}
		// 将临时表中计算所得的排名，更新到正式表中
		sql = "update szgy_zhszcphzlsb a set zhszfpm=(select fz from szgy_zhszcphzlsb_zhszfpm_temp b "
				+ "where a.xn=b.xn and a.xq=b.xq and a.xh=b.xh)" + tj;
		b = dao.runUpdate(sql, new String[] {});
		if (!b) {
			return b;
		}
		return b;
	}

	/**
	 * 获取班级名称
	 * 
	 * @param bjdm
	 * @return
	 */
	public String getBjmc(String bjdm) {
		DAO dao = new DAO();
		String sql = "select distinct bjmc from view_njxyzybj where bjdm=?";
		return dao.getOneRs(sql, new String[] { bjdm }, "bjmc");
	}

	/**
	 * 获得通用审核信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getCommShList(PjpyTeaForm model, User user,
			String tableName) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// 用户类型
		String yhlx = model.getYhlx();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder tableSql = new StringBuilder();

		// 权限控制
		user.setUserStatus(yhlx);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询条件
		String[] shztlx = searchModel.getSearch_tj_shztlx();
		searchModel.setSearch_tj_shztlx(null);
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);
		searchModel.setSearch_tj_shztlx(shztlx);

		StringBuilder shztTj = new StringBuilder();
		String userName=user.getUserName();
		if (shztlx != null && shztlx.length > 0) {

			for (int i = 0; i < shztlx.length; i++) {
				if (i == 0) {
					shztTj.append("and( ");
				} else {
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
		tableSql.append("select a.xh,a.xn,a.xq,a.xm,a.bjmc,sum(a.sqf) sqf, ");
		tableSql.append("a.bjdm,a.xydm,a.zydm,a.nj, ");
		tableSql.append("sum(a.bzrshf) bzrshf,sum(a.xyshf) xyshf, ");
		tableSql.append("sum(xxshf) xxshf,ss,ts from (select a.xh,a.xn,a.xq, ");
		tableSql.append("b.xm,b.bjdm,b.bjmc,a.sqf,b.xydm,b.zydm,b.nj, ");
		tableSql
				.append("case when a.bzrsh = '通过' then nvl(a.bzrshf, '0') else '0' end bzrshf, ");
		tableSql
				.append("case when a.xysh = '通过' then nvl(a.xyshf, '0') else '0' end xyshf, ");
		tableSql
				.append("case when a.xxsh = '通过' then nvl(a.xxshf, '0') else '0' end xxshf, ");
		
		tableSql.append("case when (c.ssrs = '0' or c.ssrs is null) then '否' else '是' end ss, ");
		tableSql.append("case when (d.tsrs = '0' or d.tsrs is null) then '否' else '是' end ts ");
		
		tableSql.append("from (select * from " + tableName + " where 1=1 "
				+ shztTj + " ) a ");
		tableSql.append("left join view_xsjbxx b on a.xh = b.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) ssrs ");
		tableSql.append("from (select distinct xn, xq, xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xsssb a ");
		tableSql.append("where xn = '" + xn + "' ");
		//tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) c on c.xmlx = '"+tableName+"' ");
		tableSql.append("and a.xh = c.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) tsrs ");
		tableSql.append("from (select distinct xn, xq, btsr xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb a ");
		tableSql.append("where xn = '" + xn + "' ");
		tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) d on d.xmlx = '"+tableName+"' ");
		tableSql.append("and a.xh = d.xh ");
		
		tableSql.append("where a.xn = '" + xn + "' ");
		tableSql.append("and a.xq = '" + xq + "') a ");
		tableSql
				.append("group by a.xn,a.xq,a.xh,a.xm,a.bjdm,a.bjmc,a.xydm,a.zydm,a.nj,a.ss,a.ts ");
		tableSql.append(") a ");
		tableSql.append(" where 1=1 ");
		if ("bz".equalsIgnoreCase(yhlx)) {// 班长

			// 学号
			String xh = user.getUserName();
			// 班级
			String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);

			tableSql.append(" and bjdm = '" + bjdm + "' and xh<>'"+userName+"' ");

		} else {// 非班长用户

			tableSql.append(searchTjByUser);
		}
		// 高级查询输入值

		tableSql.append(searchTj);
		tableSql.append(")");
		
		return getRsArrList(tableSql.toString(), "", inputV, new String[] {
				"xh", "xm", "bjmc", "sqf", "bzrshf", "xyshf", "xxshf" }, "",
				model);

	}

	/**
	 * 获得公共审核信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getCommShList(
			ArrayList<String[]> dshdList, PjpyTeaForm model, User user,
			String tableName) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		SearchModel searchModel = model.getSearchModel();

		// 用户类型
		String yhlx = model.getYhlx();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder sql = new StringBuilder();

		sql.append("select xh, bzrsh, xysh, xxsh from " + tableName);
		sql.append(" where xn = '" + xn + "' ");
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
						shztTj.append(" 1=1 ");
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
						shztTj.append(" 1=1 ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" (xysh='未审核' or xysh='需重审') ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" ((xysh='通过' and xxsh='未审核') or (xysh='通过' and xxsh='需重审'))  ");
					}

				} else if ("存在需重审".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// 班长
						// shztTj.append(" bzrsh='需重审' ");
						shztTj.append(" 1=1 ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh='需重审' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xxsh='需重审'  ");
					}
				} else if ("无需审核".equalsIgnoreCase(shztlx[i])) {
					if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" bzrsh<>'通过'");
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
	 * 获得5S分审核信息列表
	 * 
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getFiveSList(PjpyTeaForm model, User user,
			String tableName) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// 用户类型
		String yhlx = model.getYhlx();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder tableSql = new StringBuilder();

		// 权限控制
		user.setUserStatus(yhlx);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询条件
		String[] shztlx = searchModel.getSearch_tj_shztlx();
		searchModel.setSearch_tj_shztlx(null);
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);
		searchModel.setSearch_tj_shztlx(shztlx);

		StringBuilder shztTj = new StringBuilder();
		String userName=user.getUserName();
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
						//shztTj.append(" bzrsh='通过' ");
						shztTj.append(" 1=1 ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh='通过' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xxsh='通过' ");
					}

				} else if ("存在未审核".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// 班长
						//shztTj.append(" bzrsh='未审核' or bzrsh='需重审' ");
						shztTj.append(" 1=1 ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" (xysh='未审核' or xysh='需重审') ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" ((xysh='通过' and xxsh='未审核') or (xysh='通过' and xxsh='需重审'))  ");
					}

				} else if ("存在需重审".equalsIgnoreCase(shztlx[i])) {
					if ("bz".equalsIgnoreCase(yhlx)
							|| "bzr".equalsIgnoreCase(yhlx)) {// 班长
						//shztTj.append(" bzrsh='需重审' ");
						shztTj.append(" 1=1 ");
					} else if ("xy".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xysh='需重审' ");
					} else if ("xx".equalsIgnoreCase(yhlx)
							|| "admin".equalsIgnoreCase(yhlx)) {
						shztTj.append(" xxsh='需重审'  ");
					}
				}else if("无需审核".equalsIgnoreCase(shztlx[i])){
					if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" (xysh<>'未审核' and xysh<>'需重审' and xysh<>'通过') ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh<>'通过' ");
					}	
				}
			}
			shztTj.append(" ) ");
		}

		tableSql.append("(");
		tableSql.append("select * from (");
		tableSql.append("select a.xh,a.xn,a.xq,a.xm,a.bjmc,sum(a.sqf) sqf, ");
		tableSql.append("a.bjdm,a.xydm,a.zydm,a.nj, ");
		tableSql.append("sum(a.xyshf) xyshf, ");
		tableSql.append("sum(xxshf) xxshf,a.ss,a.ts from (  ");

		tableSql
				.append(" select a.xh,a.xn,a.xq,a.xm,a.bjdm,a.bjmc,a.xydm,a.zydm,a.nj, ");
		tableSql
				.append(" case when a.jjf='减分' and a.jjf is not null then '-'||a.sqf else a.sqf end sqf, ");
		tableSql
				.append(" case when a.jjf='减分' and a.jjf is not null then '-'||a.xyshf else a.xyshf end xyshf, ");
		tableSql
				.append(" case when a.jjf='减分' and a.jjf is not null then '-'||a.xxshf else a.xxshf end xxshf, ");
		tableSql.append(" a.ss,a.ts from(  ");

		tableSql
				.append("select a.xh,a.xn,a.xq,a.jjf, b.xm,b.bjdm,b.bjmc,a.sqf,b.xydm,b.zydm,b.nj, ");
		tableSql
				.append("case when a.xysh = '通过' then nvl(a.xyshf, '0') else '0' end xyshf, ");
		tableSql
				.append("case when a.xxsh = '通过' then nvl(a.xxshf, '0') else '0' end xxshf, ");
		
		tableSql.append("case when (c.ssrs = '0' or c.ssrs is null) then '否' else '是' end ss, ");
		tableSql.append("case when (d.tsrs = '0' or d.tsrs is null) then '否' else '是' end ts ");
		
		tableSql.append("from (select * from " + tableName + " where 1=1 "
				+ shztTj + " ) a ");
		tableSql.append("left join view_xsjbxx b on a.xh = b.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) ssrs ");
		tableSql.append("from (select distinct xn, xq, xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xsssb a ");
		tableSql.append("where xn = '" + xn + "' ");
		//tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) c on c.xmlx = 'szyc_5sb' ");
		tableSql.append("and a.xh = c.xh ");
		
		tableSql.append("left join (select xmlx, xh, count(1) tsrs ");
		tableSql.append("from (select distinct xn, xq, btsr xh, xmlx ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb a ");
		tableSql.append("where xn = '" + xn + "' ");
		tableSql.append("and clr is null ");
		tableSql.append("and xq = '" + xq + "') ");
		tableSql.append("group by xmlx, xh) d on d.xmlx = 'szyc_5sb' ");
		tableSql.append("and a.xh = d.xh ");
		
		tableSql.append("where xn = '" + xn + "' ");
		tableSql.append("and xq = '" + xq + "') a )a ");
		tableSql
				.append("group by a.xh,a.xn,a.xq,a.xm,a.bjdm,a.bjmc,a.xydm,a.zydm,a.nj,a.ss,a.ts ");
		tableSql.append(") a ");
		tableSql.append(" where 1=1 ");

		if ("xy".equalsIgnoreCase(yhlx)) {

			// 所在部门
			String userDep = user.getUserDep();

			tableSql.append(" and xydm = '" + userDep + "' and xh<>'"+userName+"' ");
		} else {// 非班长用户

			tableSql.append(searchTjByUser);
		}

		tableSql.append(searchTj);
		tableSql.append(")");

		return getRsArrList(tableSql.toString(), "", inputV, new String[] {
				"xh", "xm", "bjmc", "sqf", "xyshf", "xxshf" }, "", model);

	}

	/**
	 * 获得5S审核信息列表
	 * 
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getFiveSList(
			ArrayList<String[]> dshdList, PjpyTeaForm model, User user,
			String tableName) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		SearchModel searchModel = model.getSearchModel();

		// 用户类型
		String yhlx = model.getYhlx();
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];

		StringBuilder sql = new StringBuilder();

		sql.append("select xh,  xysh, xxsh from " + tableName);
		sql.append(" where xn = '" + xn + "' ");
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
						shztTj.append(" 1=1 ");
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
						shztTj.append(" 1=1 ");
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
					if("xy".equalsIgnoreCase(yhlx) ){
						shztTj.append(" (xysh<>'未审核' and xysh<>'需重审' and xysh<> '通过') ");
					}else if("xx".equalsIgnoreCase(yhlx) || "admin".equalsIgnoreCase(yhlx) ){
						shztTj.append(" xysh<>'通过' ");
					}	
				}
			}
			
			shztTj.append(" ) ");
		}
		sql.append(shztTj);
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "xh", "xysh", "xxsh" });

		return list;

	}

	/**
	 * 获取综合测评设定
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getZhcpsdInfo(PjpyTeaForm model) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xmdm = model.getXmdm();
		if ("szyc_5sb".equalsIgnoreCase(xmdm)) {
			xmdm = "wsmk";
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {
			xmdm = "yybd";
		} else if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {
			xmdm = "dshd";
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {
			xmdm = "ivtlt";
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {
			xmdm = "wthd";
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {
			xmdm = "zznl";
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {
			xmdm = "shsj";
		}

		sql
				.append(" select zxmjcf,zxmzgf from szgy_zhszycfsszb where zxmdm=? ");
		return dao.getMap(sql.toString(), new String[] { xmdm }, new String[] {
				"zxmjcf", "zxmzgf" });

	}

}
