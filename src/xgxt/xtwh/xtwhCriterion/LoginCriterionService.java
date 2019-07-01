package xgxt.xtwh.xtwhCriterion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 处理用户登录时的权限关系</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LUNING</p>
 * <p>Version: 1.0</p>
 * <p>Time:2011-6-2</p>
 */
public class LoginCriterionService  extends CriterionService{
	
	/**
	 * 往session里存放用户角色,避免过多的数据库交互
	 * @param request
	 */
	public void setUserRole(HttpServletRequest request){
		//session中获取得到用户角色的相关参数
		HttpSession session = request.getSession();
		String userName =(String)session.getAttribute("userName");
		String userType =(String)session.getAttribute("userType");
		String UserRoles="";
		if(userType.equalsIgnoreCase("stu")){
			UserRoles = getStuRoles(userName);
		}else{
			UserRoles = getCommUserRoles(userName);
		}
		session.setAttribute("userRoles", UserRoles);
	
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	private String getCommUserRoles(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select jsdm from xg_xtwh_yhjsb where yhm=?";
		String[] jsArray = null;
		String yhjs="";
		try {
			jsArray = dao.getRs(sql, new String[]{userName}, "jsdm");
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		yhjs = getPurviewString(jsArray);
		return yhjs;
	}

	/**
	 * 获取学生角色
	 * @param userName
	 * @return
	 */
	private String getStuRoles(String userName){
		
		//判断是否社团干部，如果是，则已社团代码+职务代码做为主键，用以关联学生角色表
		DAO dao = DAO.getInstance();
		String sql ="select stv||zwdm xslxdm from xsh_cyglb a where xh = ?";
		String[] stgbdmz;
		String yhjs="";
		try {
			stgbdmz = dao.getRs(sql, new String[]{userName}, "xslxdm");
			
			//判断是否班级干部，如果是，取出学生担任的班级干部类型
			sql = "select distinct bjgbdm from sxjy_bjgbxxb where xh = ?";
			String[] bjgbdmz;
			bjgbdmz = dao.getRs(sql, new String[]{userName}, "bjgbdm");
			
			//得到该生拥有的角色及功能模块
			StringBuilder jsQuerySql = new StringBuilder("select jsdm from yhlxjsb where yhlxdm in ");
			jsQuerySql.append("(? ");
			for(int i = 0;i<(bjgbdmz.length+stgbdmz.length);i++){
				jsQuerySql.append(",?");
			}
			jsQuerySql.append(")");
			
			String[] jsArray = dao.getRs(sql, new String[]{}, "jsdm");
			yhjs = getPurviewString(jsArray);
			} catch (Exception e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
		}
		return yhjs;
	}

}
