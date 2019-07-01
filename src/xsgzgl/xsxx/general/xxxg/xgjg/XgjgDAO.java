package xsgzgl.xsxx.general.xxxg.xgjg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.szdw.general.SzdwGeneralDAO;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改結果_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgjgDAO extends SzdwGeneralDAO {

	// ==================执行查询操作 begin =============================

	/**
	 * 获得修改結果列表
	 * 
	 * @date 2013-01-28
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXgjgList(XsxxGeneralForm myForm,
			XgjgModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r, a.* from ( ");
		sql.append("select a.sqid,a.xh,f.xm,f.nj,f.xydm,f.xymc, ");
		sql.append("f.zydm,f.xb,f.zymc,f.bjdm,f.bjmc, ");
		sql.append("decode(a.shjg,'tg','通过','wsh','未审核','th','退回','审核中') shzt, ");
		sql.append("a.xgsj from xg_xsxx_xxxgsqb a, ");
		sql.append("(select g.xh,g.xm,g.xb,h.nj,h.xydm, ");
		sql.append("h.xymc,h.zydm,h.zymc,h.bjdm,h.bjmc ");
		sql.append("from (select a.xh,a.xm, ");
		sql.append("decode(a.xb,'1','男','2','女',a.xb) xb, ");
		sql.append("a.bjdm from xsxxb a  ");
		sql.append("where a.sfzx = '在校' or a.sfzx is null) g ");
		sql.append("left join view_njxyzybj_all h ");
		sql.append("on g.bjdm = h.bjdm) f ");
		sql.append("where f.xh = a.xh order by xgsj desc) a ");
		sql.append("where 1=1 ");
		
		// 过滤条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");

		sql.append(searchTj);
		sql.append(searchTjByUser);

		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), sql.toString(), inputV,
						new String[] { "sqid", "xh", "xm", "xb", "nj", "bjmc",
								"xgsj", "shzt" });

		return list;
	}

	// ==================执行查询操作 end =============================
}
