package xgxt.xtwh.xtwhCriterion.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.xtwh.xtwhCriterion.CriterionService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 过滤器service</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LUNING</p>
 * <p>Version: 1.0</p>
 * <p>Time:2011-4-28</p>
 */

public class FilterService extends CriterionService{
	
	/**
	 * 返回需要作判断用户权限需要的参数
	 * @param request
	 * @return
	 */
	public FilterModel setFilterAttribute(HttpServletRequest request) {
		FilterModel filterModel = new FilterModel();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		String userType = (String)session.getAttribute("userType");
		String userRoles = (String)session.getAttribute("userRoles");
		String fwlj = request.getRequestURI();
		if(request.getQueryString()!=null){
			fwlj += "?"+request.getQueryString();
		}
		String purview = request.getParameter("purview");
		fwlj  =  fwlj.replace("/xgxt/", "");
		filterModel.setFwlj(fwlj);
		filterModel.setUserType(userType);
		filterModel.setPurview(purview);
		filterModel.setUserName(userName);
		filterModel.setUserRoles(userRoles);
		return filterModel;
	}
	
	/**
	 * 获取用户权限
	 */
	public FilterModel getUserPurview(FilterModel filterModel) {
		//获取用户类型,用户名，路径
		String userType = filterModel.getUserType();
		filterModel = getUserGnmkdmAndTitle(filterModel);
		//判断
		if(userType.equalsIgnoreCase("stu")){
			filterModel = getStuPurview(filterModel);
		}else{
			filterModel = getCommUserPurview(filterModel);
			
		}
		
		return null;
	}

	/**
	 * 获取学生权限
	 * @param filterModel
	 * @return
	 * @throws Exception 
	 */
	private FilterModel getStuPurview(FilterModel filterModel){
		// TODO 自动生成方法存根
		String userName = filterModel.getUserName();
		String yhjs = filterModel.getUserRoles();
		String[] yhjsz = yhjs.split("!!");
		//根据用户名和用户所拥有的角色取出用户的功能模块
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder("select distinct cdgndm from xg_xtwh_jscdqxb b where gnmkdm ");
		sql.append(" = ? and jsdm in (");
		//数组异常判断并拼接
		if(yhjsz!=null&&yhjsz.length>0){
			for(int i = 0;i<yhjsz.length-1;i++){
				sql.append(yhjsz[i]);
				sql.append(",");
			}
			sql.append(yhjsz[yhjsz.length-1]);
		}
		sql.append(") union select cdgndm from xg_xtwh_xscdqxb where xh =? and gnmkdm = ? and ");
		sql.append("((to_char(sysdate,'yyyymmdd')>=kssj and to_char(sysdate,'yyyymmdd')<=jssj) or jssj is null)");
		try {
			String [] purviewRs = dao.getRs(sql.toString(), new String[]{filterModel.getGnmkdm(),userName,userName,filterModel.getGnmkdm()}, "cdgndm");
			String purview = getPurviewString(purviewRs);
			filterModel.setPurview(purview);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return filterModel;
	}

	/**
	 * 获取一般用户权限（非学生）
	 * @param filterModel
	 */
	private FilterModel getCommUserPurview(FilterModel filterModel) {
		StringBuilder sql = new StringBuilder("select distinct cdgndm from xg_xtwh_jscdqxb b where gnmkdm");
		sql.append(" = ? and jsdm in (");
		String yhjs = filterModel.getUserRoles();
		String[] yhjsz = yhjs.split("!!");
		//数组异常判断并拼接
		if(yhjsz!=null&&yhjsz.length>0){
			for(int i = 0;i<yhjsz.length-1;i++){
				sql.append(yhjsz[i]);
				sql.append(",");
			}
			sql.append(yhjsz[yhjsz.length-1]);
		}
		sql.append(") union select cdgndm from xg_xtwh_yhcdqxb where yhm =? and gnmkdm = ? and");
		sql.append(" ((to_char(sysdate,'yyyymmdd')>=kssj and to_char(sysdate,'yyyymmdd')<=jssj) or jssj is null)");
		
		DAO dao = DAO.getInstance();
		
		try {
			String [] purviewRs = dao.getRs(sql.toString(), new String[]{filterModel.getGnmkdm(),filterModel.getUserName(),filterModel.getUserName(),filterModel.getGnmkdm()}, "cdgndm");
			String purview = getPurviewString(purviewRs);
			filterModel.setPurview(purview);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return filterModel;
	}
	
	/**
	 * 获取用户功能模块代码和标题
	 * @param FilterModel
	 * @return
	 */
	public FilterModel getUserGnmkdmAndTitle(FilterModel filterModel) {
		DAO daoBase = DAO.getInstance();
		String dyym = filterModel.getFwlj();
		StringBuilder sql = new StringBuilder("select gnmkdm,(select gnmkmc from gnmkdmb where gnmkdm ");
		sql.append("= substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,");
		sql.append("0,5))||'-'||gnmkmc title from gnmkdmb a where dyym=?");
		String[] ress = daoBase.getOneRs(sql.toString(), new String[]{dyym}, new String[]{"gnmkdm","title"});
		filterModel.setGnmkdm(ress[0]);
		filterModel.setTitle(ress[0]);
		return filterModel;
	}
	
	


}
