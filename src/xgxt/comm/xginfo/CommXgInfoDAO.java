package xgxt.comm.xginfo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CommXgInfoDAO extends CommDAO {

	/**
	 * 获得学生信息
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXsxxList(CommForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 类型
		String lx = model.getUserType();
		// 类型
		String zgh = model.getZgh();
		// 表名
		String tableName = model.getTableName();
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		StringBuffer queryStr = getXsxxCondition(model);

		// =======================用户身份相关条件=============================
		if ("fdy".equalsIgnoreCase(lx)) {
			queryStr.append(" and exists(select 1 from fdybjb b ");
			queryStr.append(" where a.bjdm = b.bjdm ");
			queryStr.append(" and b.zgh = '" + zgh + "')");
		} else if ("bzr".equalsIgnoreCase(lx)) {
			queryStr.append(" and exists(select 1 from bzrbbb b ");
			queryStr.append(" where a.bjdm = b.bjdm");
			queryStr.append(" and b.zgh = '" + zgh + "')");
		} else if ("jd".equalsIgnoreCase(lx)) {
			queryStr.append(" and (");
			queryStr.append(" exists(select 1 from fdybjb b ");
			queryStr.append(" where a.bjdm = b.bjdm");
			queryStr.append(" and b.zgh = '" + zgh + "')");

			queryStr.append(" or exists(select 1 from bzrbbb b ");
			queryStr.append(" where a.bjdm = b.bjdm");
			queryStr.append(" and b.zgh = '" + zgh + "')");

			queryStr.append(" )");
		}
		// =======================用户身份相关条件 end=============================
		
		//=======================公寓相关查询条件=============================
		// 校区代码
		String xqdm = model.getXqdm();
		// 楼栋代码
		String lddm = model.getLddm();
		// 层数
		String cs = model.getCs();
		// 寝室号
		String qsh = model.getQsh();
		
		if (!Base.isNull(xqdm) ||!Base.isNull(lddm) || !Base.isNull(cs) || !Base.isNull(qsh)) {
			queryStr.append(" and exists(select 1 from view_xszsxx b ");
			queryStr.append(" where a.xh = b.xh ");
			queryStr.append(!Base.isNull(xqdm)?" and b.xqdm = '" + xqdm + "'" : "");
			queryStr.append(!Base.isNull(lddm)?" and b.lddm = '" + lddm + "'" : "");
			queryStr.append(!Base.isNull(cs)?" and b.cs = '" + cs + "'" : "");
			queryStr.append(!Base.isNull(qsh)?" and b.qsh = '" + qsh + "'" : "");
			queryStr.append(" )");
		}
		// =======================公寓相关查询条件 end======================
		
		String query = myQuery.getQueryString() + queryStr;

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得学生信息相关条件
	 * 
	 * @author luojw
	 */
	private StringBuffer getXsxxCondition(CommForm model) {

		StringBuffer sb = new StringBuffer();
		StringBuffer tmp = new StringBuffer();

		if (!Base.isNull(model.getZd())) {
			String[] zdArr = model.getZd().split("-");

			String[] zdVArr = Base.isNull(model.getZdValue()) ? new String[] {}
					: model.getZdValue().split("!!@@!!");

			for (int i = 0; i < zdArr.length; i++) {
				if (!Base.isNull(zdArr[i])) {
					tmp = new StringBuffer();
					tmp.append(" and ");
					tmp.append(zdArr[i]);
					tmp.append(" = '");
					tmp
							.append(zdVArr.length >= i
									&& !Base.isNull(zdVArr[i]) ? zdVArr[i] : "");
					tmp.append("'");
					sb.append(tmp);
				}
			}
		}
		return sb;
	}
}
