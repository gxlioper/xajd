package xgxt.xtwh;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_DAO类
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

public class XtwhDAO extends CommDAO {

	/**
	 * 保存快捷方式
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveKjfsOne(String yhm, String picpath, String lx)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "select max(xssx) + 1 xssx from xtwh_kjfsszb where yhm = ? ";

		// 显示顺序
		String xssx = dao.getOneRs(sql, new String[] { yhm }, "xssx");
		xssx = Base.isNull(xssx) ? "0" : xssx;

		String[] arrSql = null;

		if ("yy".equalsIgnoreCase(lx)) {// 应用

			arrSql = new String[2];

			arrSql[0] = "delete from xtwh_kjfsszb where yhm = '" + yhm
					+ "' and pic = '" + picpath + "'";

			arrSql[1] = "insert into xtwh_kjfsszb (yhm,pic,xssx) values('"
					+ yhm + "','" + picpath + "','" + xssx + "')";
		} else {// 取消

			arrSql = new String[1];

			arrSql[0] = "delete from xtwh_kjfsszb where yhm = '" + yhm
					+ "' and pic = '" + picpath + "'";
		}

		User user = new User();
		user.setUserName(yhm);

		boolean flag = dao.saveArrDate(arrSql, user);

		return flag;
	}

	/**
	 * 删除快捷方式 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delKjfsOne(String yhm, String picpath)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xtwh_kjfsszb where yhm = ? and pic = ?";

		boolean flag = dao.runUpdate(sql, new String[] { yhm, picpath });

		return flag;
	}
	
	/**
	 * 保存首页调查
	 * 
	 * @param yhm(用户名)
	 * @param dcid(调查ID)
	 * @param xxid(选项ID)
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveSydc(String yhm, String dcid, String xxid)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "insert into xg_xtwh_sydcjgb(dcid,xxid,bdcr) values(?,?,?)";

		boolean flag = dao.runUpdate(sql, new String[] { dcid, xxid, yhm });

		return flag;
	}
	/** 
	 * @描述:查询预警状态（杭州师范大学）
	 * @作者：lgx[工号：1553]
	 * @日期：2018-3-21 下午04:55:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getYjzt() {
		DAO dao = DAO.getInstance();
		String  sql = "select yjzt from xtszb where rownum=1";
		return dao.getOneRs(sql, new String[]{}, "yjzt");
	}
}
