
package xgxt.jxgl;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生资助通用Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class NblgJxglActionUtils extends DispatchAction {

	/**
	 * 学生资助通用方法：加载页面所需的列表
	 * @param request
	 * @param commenBean
	 * @throws Exception
	 */
	public  void appendProperties(HttpServletRequest request, NblgJxglCommenBean commenBean) throws Exception {
		DAO dao = DAO.getInstance();
		String xy = "";
		String zy = "";
		String yjdm = "%";
		String ljdm = "%";
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		
//		ArrayList<String> userBj = new ArrayList<String>();
//		userBj = dao.getUserBj(userName);
		
		String njLocal = commenBean.getNj();
		xy = xy==null ? "": commenBean.getXydm(); 
		zy = zy==null ? "": commenBean.getZydm();
		yjdm = "".equalsIgnoreCase(commenBean.getYjdm()) ? "%": commenBean.getYjdm();
		ljdm = "".equalsIgnoreCase(commenBean.getLjdm()) ? "%": commenBean.getLjdm();
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;
		}
		njLocal = commenBean.getNj() == null ? "": commenBean.getNj();
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("yjList", dao.getList("select bzdm yjdm,bzmc yjmc from nblg_jxbzdmb where bzdj='1' order by nj,bzdm", new String[]{}, new String[]{"yjdm","yjmc"}));
		request.setAttribute("ljList", dao.getList("select bzdm ljdm,bzmc ljmc from nblg_jxbzdmb where bzdj='2' and sjdm like ? order by nj,bzdm", new String[]{yjdm}, new String[]{"ljdm","ljmc"}));
		request.setAttribute("pjList", dao.getList("select bzdm pjdm,bzmc pjmc from nblg_jxbzdmb where bzdj='3' and sjdm like ? order by nj,bzdm", new String[]{ljdm}, new String[]{"pjdm","pjmc"}));
		request.setAttribute("userType", userType);//用户类型
		request.setAttribute("userName", userName);//用户名
	}
}
