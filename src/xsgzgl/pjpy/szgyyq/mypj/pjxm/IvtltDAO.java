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
import xsgzgl.pjpy.szgyyq.model.IvtltModel;
import xsgzgl.pjpy.szgyyq.model.YybdModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_评奖项目_Ivt论坛_DAO类
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

public class IvtltDAO extends CommDAO {

	/**
	 * 删除Ivt论坛申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean delIvtltSqf(PjpyStuForm model, String[] pkValue, User user) {

		boolean flag = true;

		// 主键
		String pk = "xn||xq||xh||jztm||xthdrq";
		// 操作表
		String tableName = "szyq_ivtltb";

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
	 * 保存Ivt论坛申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveIvtltSqf(PjpyStuForm model, List<String[]> params,
			User user) {

		boolean flag = false;

		// 读书活动记载表
		String tableName = "szyq_ivtltb";
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(tableName);
		sql.append("(xn,xq,xh,jztm,xthdrq,jcdj,ccdj,sqf)");
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
	 * 获取语言表达列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getIvtltList(PjpyStuForm model) {

//		 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 学号
		String xh = model.getXh();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id,case when length(a.jztm) > 6 then ");
		sql.append(" substr(a.jztm, 0, 6) || '...'  ");
		sql.append("  else a.jztm end jztm, a.jztm alljztm, a.xthdrq, ");
		
		sql.append(" a.jcdj,a.ccdj,a.sqf, case when bzrsh = '通过' then bzrshf ");
		sql.append(" else '0' end bzrshf, case when xysh = '通过' then ");
		sql.append(" xyshf else '0' end xyshf, case when xxsh = '通过' then ");
		sql.append(" xxshf else '0' end xxshf, case ");
		sql.append(" when clr is not null then '已处理' ");
		sql.append(" when b.xh is not null then '已申诉' ");
		sql.append(" else '申诉' end cz,b.ssnr,b.clyj,b.clr, ");
		sql.append(" (select xm from yhb c where c.yhm = b.clr) clrxm, ");
		sql.append(" bzrsh,xysh,xxsh,bzrshf bzrlrf,xyshf xylrf,xxshf xxlrf ");
		sql.append(" from (select id,xn,xq,xh,jztm,xthdrq,sqf,jcdj,ccdj, ");
		sql.append(" nvl(bzrshf, 0) bzrshf,bzrsh,nvl(xyshf, 0) xyshf, ");
		sql.append(" xysh,nvl(xxshf, 0) xxshf,xxsh from szyq_ivtltb a ");
		sql.append(" where xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and xh = ?) a ");
		sql.append(" left join xg_pjpy_szgyyq_xsssb b on ");
		sql.append(" a.xh = b.xh and a.id = b.xmid ");
		sql.append(" order by xthdrq ");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xh }, new String[] { "id", "jztm",
						"alljztm", "xthdrq",  "sqf","jcdj","ccdj",
						"bzrshf", "xyshf", "xxshf", "cz", "ssnr", "clyj",
						"clrxm", "bzrsh", "xysh", "xxsh","bzrlrf","xylrf","xxlrf" });

		return list;
	}
	
	/**
	 * 获得IVTLT论坛信息列表
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getIvtltList(PjpyStuForm model, User user)
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
		tableSql.append("from szyq_ivtltb b ");
		tableSql.append("where b.xn = '"+xn+"' ");
		tableSql.append("and b.xq = '"+xq+"' ");
		tableSql.append("group by b.xh) b on a.xh = b.xh ");
		tableSql.append("left join (select c.xh, c.btsr, c.tsnr, c.tssj, c.clr, c.clyj, c.clsj ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb c ");
		tableSql.append("where c.xmlx = 'szyq_ivtltb' ");
		
		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {
			tableSql.append("and c.xh = '" + xh + "' ");
		} else {
			tableSql.append("and exists (select 1 ");
			tableSql.append("from (select btsr, max(tssj) tssj ");
			tableSql.append("from xg_pjpy_szgyyq_xstsb ");
			tableSql.append("where xmlx = 'szyq_ivtltb' ");
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
		
		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {//班长
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
		}else{
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
	 * 修改Ivt论坛信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean editIvtltInfo(PjpyStuForm model, User user) throws Exception {

		IvtltModel ivtltModel = model.getIvtltModel();
		// 申请ID
		String id = ivtltModel.getId();
		// 讲座题目
		String jztm = ivtltModel.getJztm()[0];
		// 日期
		String xthdrq = ivtltModel.getXthdrq()[0];
		// 进场登记
		String jcdj = (ivtltModel.getJcdj() != null) ? ivtltModel.getJcdj()[0] : "";
		// 出场登记
		String ccdj = (ivtltModel.getCcdj() != null) ? ivtltModel.getCcdj()[0] : "";
		// 申请分
		String sqf = ivtltModel.getSqf()[0];

		StringBuilder sql = new StringBuilder();
		sql.append("update szyq_ivtltb set ");
		sql.append("jztm = ? ");
		sql.append(",xthdrq = ? ");
		sql.append(",jcdj = ? ");
		sql.append(",ccdj = ? ");
		sql.append(",sqf = ? ");
		sql.append("where id = ? ");

		DAO dao = DAO.getInstance();

		boolean flag = dao.runUpdate(sql.toString(), new String[] { jztm,
				xthdrq, jcdj, ccdj, sqf, id });

		return flag;
	}
}
