package xgxt.pjpy.comm.pjpy.rssz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_人数设置_DAO类
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

public class PjpyRsszDAO {

	/**
	 * 获得人员确定4张表的数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRyqdList() {

		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		// 评奖学生表
		sql.append(" select count(1) num from xg_pjpy_xsb a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.pjxn = ? ");
		sql.append(" and a.pjxq = ? ");
		sql.append(" and a.pjnd = ? ");
		sql.append(" union all ");
		// 评奖学院表
		sql.append(" select count(1) num from xg_pjpy_xyb b ");
		sql.append(" where 1 = 1 ");
		sql.append(" and b.pjxn = ? ");
		sql.append(" and b.pjxq = ? ");
		sql.append(" and b.pjnd = ? ");
		sql.append(" union all ");
		// 评奖专业表
		sql.append(" select count(1) num from xg_pjpy_zyb c ");
		sql.append(" where 1 = 1 ");
		sql.append(" and c.pjxn = ? ");
		sql.append(" and c.pjxq = ? ");
		sql.append(" and c.pjnd = ? ");
		sql.append(" union all ");
		// 评奖班级表
		sql.append(" select count(1) num from xg_pjpy_bjb d ");
		sql.append(" where 1 = 1 ");
		sql.append(" and d.pjxn = ? ");
		sql.append(" and d.pjxq = ? ");
		sql.append(" and d.pjnd = ? ");

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd, pjxn, pjxq, pjnd, pjxn, pjxq,
						pjnd, pjxn, pjxq, pjnd, }, new String[] { "num" });
		
		return list;
	}
}
