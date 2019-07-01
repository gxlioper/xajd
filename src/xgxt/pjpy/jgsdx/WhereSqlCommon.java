
package xgxt.pjpy.jgsdx;

import java.util.ArrayList;

import xgxt.pjpy.hbsf.ZhszcpQryModel;
import xgxt.utils.String.StringUtils;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学评奖评优WHERE条件查询MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-07</p>
 */
public class WhereSqlCommon {

	public ArrayList<String> values = new ArrayList<String>();//条件值
	
	/**
	 * 荣誉称号查询条件及值
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSqlByRych(RychQryModel rychModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xydm = rychModel.getXydm();
		String xn = rychModel.getXn();
		String nd = rychModel.getNd();
		String nj = rychModel.getNj();
		String xmdm = rychModel.getXmdm();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xmdm)) {
			whereSql.append(" and rychdm = ?");
			values.add(xmdm);
		}
		return whereSql;
	}
	
	/**
	 * 查询条件及值
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(RychSjQryModel rychModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String nj = rychModel.getNj();
		String xn = rychModel.getXn();
		String xq = rychModel.getXq();
		String xh = rychModel.getXh();
		String xydm = rychModel.getXydm();
		String zydm = rychModel.getZydm();
		String bjdm = rychModel.getBjdm();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}
		return whereSql;
	}
	
	/**
	 * 查询条件及值
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(ZhszcpQryModel zhszcpModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String nj = zhszcpModel.getNj();
		String xn = zhszcpModel.getXn();
		String xq = zhszcpModel.getXq();
		String xh = zhszcpModel.getXh();
		String xydm = zhszcpModel.getXydm();
		String zydm = zhszcpModel.getZydm();
		String bjdm = zhszcpModel.getBjdm();
		String nd = zhszcpModel.getNd();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		return whereSql;
	}
}
