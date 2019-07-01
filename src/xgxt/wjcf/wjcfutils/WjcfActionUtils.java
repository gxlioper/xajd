
package xgxt.wjcf.wjcfutils;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 违纪处分通用Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class WjcfActionUtils extends DispatchAction {

	/**
	 * 违纪处分通用方法：加载页面所需的列表
	 * @param request
	 * @param commenBean
	 * @throws Exception
	 */
	public  void appendProperties(HttpServletRequest request, WjcfCommenBean commenBean) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = commenBean.getNj();
		xy = xy==null ? "": commenBean.getXydm(); 
		zy = zy==null ? "": commenBean.getZydm(); 
		njLocal = commenBean.getNj() == null ? "": commenBean.getNj();
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		/*String realTable = "cjb";
		String tableName = "view_cjb";*/
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		request.setAttribute("writeAble", "yes");//判断用户读写权
		/*request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名
*/		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
		request.setAttribute("userName", userName);//用户名
	}
}
