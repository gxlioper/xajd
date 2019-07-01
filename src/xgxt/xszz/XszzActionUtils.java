
package xgxt.xszz;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生资助通用Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class XszzActionUtils extends DispatchAction {

	/**
	 * 学生资助通用方法：加载页面所需的列表
	 * @param request
	 * @param commenBean
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request,
			XszzCommenBean commenBean, boolean sfFDY) throws Exception {
		DAO dao = new DAO();
		String xy = "";
		String zy = "";
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();

		String njLocal = commenBean.getNj();
		xy = xy == null ? "" : commenBean.getXydm();
		zy = zy == null ? "" : commenBean.getZydm();
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;
			commenBean.setXydm(xy);
		}
		njLocal = commenBean.getNj() == null ? "" : commenBean.getNj();
		String zyKey = xy == null ? "" : xy;
		String bjKey = (xy == null ? "" : xy) + "!!" + (zy == null ? "" : zy)
				+ "!!" + (njLocal == null ? "" : njLocal);

		request.setAttribute("writeAble", "yes");// 判断用户读写权
		request.setAttribute("xqList", Base.getXqList());// 学期列表
		request.setAttribute("xnList", Base.getXnndList());// 学年列表
		request.setAttribute("ndList", Base.getXnndList());// 学年列表
		request.setAttribute("njList", Base.getNjList());// 年级列表
		request.setAttribute("xyList", Base.getXyList());// 学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));// 专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 班级列表
		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			request.setAttribute("userType", "xx");// 用户类型
		} else if (StringUtils.isEqual(userType, "stu")
				|| StringUtils.isEqual(userType, "student")) {
			request.setAttribute("userType", "stu");// 用户类型
		} else {
			request.setAttribute("userType", "xy");// 用户类型
		}
		if (sfFDY) {
			ArrayList<String> userBj = new ArrayList<String>();
			userBj = dao.getUserBj(userName);

			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				request.setAttribute("userType", "xx");// 用户类型
			} else if (StringUtils.isEqual(userType, "stu")
					|| StringUtils.isEqual(userType, "student")) {
				request.setAttribute("userType", "stu");// 用户类型
			} else {
				if (userBj.size() == 0) {
					request.setAttribute("userType", "xy");// 用户类型
				} else {
					request.setAttribute("userType", "fdy");// 用户类型
					request.setAttribute("bjList", dao.getBjList(xy, zy, null,
							userBj));
				}
			}
		}
		request.setAttribute("userName", userName);//用户名
	}
}
