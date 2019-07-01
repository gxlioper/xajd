
package xgxt.pjpy.cqkjxy;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 上海工程技术大学违纪处分DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-19</p>
 */
public class PjpyCqkjxyDAO  {
	DAO dao = DAO.getInstance();
	/**
	 * 保存辅导员审核信息
	 * @param cqkjxyfdyshModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyShXs(PjpyCqkjxyFdyshModel cqkjxyfdyshModel, String pk, String pkValue, HttpServletRequest request) throws Exception {
		boolean flag = false;
		String fdysh = DealString.toGBK(cqkjxyfdyshModel.getYesNo());
		flag = StandardOperation.update("xsjxjb", new String[]{"fdysh"}, new String[]{fdysh}, pk, pkValue, request);
		return flag;
	}
	
	/**
	 * 根据主键获取查询出来的信息
	 * @return
	 * @throws Exception
	 */
	public String[] getFdyShQry(String pk, String pkVal, String[] colList) throws Exception {
		String [] rs = null;
		String sql = "select "
			+ pk
			+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq from view_xsjxjb where "
			+ pk + "='" + pkVal + "'";
		rs = dao.getOneRs(sql, new String[] {}, colList);
		return rs;
	}
}
