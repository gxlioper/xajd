package xsgzgl.pjpy.general.tjcx.knstjmdhz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.PjpyTjcxDAO;
import xsgzgl.pjpy.general.tjcx.tjmdhz.TjcxTjmdhzModel;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖金额汇总_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class TjcxTjmdhzDAO extends PjpyTjcxDAO {

	/**
	 * 获取我的评奖本次评奖信息
	 * 
	 * @author qlj
	 */
	public ArrayList<String[]> getTjmdhzList(PjpyGeneralForm myForm,
			TjcxTjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = searchModel.getSearch_tj_xn();
//		if (xn == null || xn.length == 0) {
//			xn = new String[] { jbszForm.getPjxn() };
//		}
//		searchModel.setSearch_tj_xn(xn);
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum r,a.* from(select substr(a.xmmc,11,3)pjdj,a.xn,b.xm,b.xh, ");
		sql.append(" b.nj,b.xydm,b.zydm,b.bjdm,b.bjmc||'('||c.bjrs||'人)' bjrs,nvl(zd2,0) dycj, nvl(zd1,0) zcf,zcfbjpm ,zdcj ");
		sql.append(" from (select a.xn,a.xh,a.xmmc from  xg_pjpy_pjlsxxb a  ");
		sql.append(" union (select a.pjxn,a.xh,b.xmmc from xg_pjpy_pjxmsqb a, ");
		sql.append(" xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd and ( a.sqjg='tg' or a.sqjg='wxsh')))a ");
		sql.append(" left join xg_view_pjpy_pjryk b on a.xh = b.xh ");
		sql.append(" left join (select count(1)bjrs,bjdm from xg_view_pjpy_pjryk group by bjdm)c on b.bjdm=c.bjdm ");
		sql.append(" left join xg_pjpy_zhcpb d on a.xh=d.xh and a.xn=d.xn  ");
		sql.append(" left join (select xh,xn,min(cj)zdcj from view_zhhcjb where (kcxz like '%必修课%' or kcxz like '%选修课%') ");
		sql.append(" and regexp_like(cj,'^\\d{1,10}\\.*\\d{0,10}$') ");
		sql.append("  group by xh,xn) e on a.xh=e.xh and a.xn=e.xn  ");
		
		sql.append(" where xmmc like '%学院家庭经济困难学生一等奖%' ");
		sql.append(" or xmmc like '%学院家庭经济困难学生二等奖%' ");
		sql.append(" or xmmc like '%学院家庭经济困难学生三等奖%'  ");
		sql.append(" )a ");

		sql.append(query);

		String[] colList = { "r","pjdj", "xm", "xh",  "bjrs", "dycj", "zcf",
				"zcfbjpm",  "zdcj" };
		// ====================SQL拼装 end================================
		ArrayList<String[]> list =new ArrayList<String[]>();
		
		if("exp".equalsIgnoreCase(model.getType())){
			list=(ArrayList<String[]>) CommonQueryDAO
			.commonQueryNotFy(sql.toString(), "", inputV, colList, myForm);
		}else{
			list=(ArrayList<String[]>) CommonQueryDAO
					.commonQuery(sql.toString(), "", inputV, colList, myForm);
		}

		return list;
	}

}
