package xsgzgl.qgzx.xsgw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglDao;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-23 下午14:19:22
 * </p>
 */

public class QgzxXsgwcxDAO extends CommDAO{

	/**
	 * 查询我的勤工岗位首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWdqggwCx(QgzxXsgwcxForm myForm,String username) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] { "guid", "r", "xn", "gwmc", "gwxz", "yrbm","zje"};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r, xn,gwmc,gwxz,yrbm,tgsj,zje,guid from (select a.xh,b.xn,b.gwmc,b.gwxzdm,d.gwxzmc gwxz,e.bmmc yrbm,nvl(sum(c.je),0)zje,");
		sql.append("decode(c.zgzt, 'zg', '在岗', '退岗') zgzt,a.gwdm guid,a.tgsj from xg_qgzx_xsgwxxb a ");
		sql.append(" left join xg_qgzx_gwxxb b on a.gwdm=b.gwdm left join VIEW_NEW_DC_QGZX_CIFFSJWH c on b.gwdm=c.gwdm and a.xh=c.xh ");
		sql.append(" left join xg_qgzx_gwxzdmb d on b.gwxzdm=d.gwxzdm left join zxbz_xxbmdm e on b.yrdwdm =e.bmdm");
		sql.append(" where a.xh = '"+username+"' group by a.xh,b.xn,b.gwmc,b.gwxzdm,d.gwxzmc,e.bmmc,c.zgzt,a.gwdm,a.tgsj )");
		
		sql.append(query );
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 查看我的勤工岗位首页面一条数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsgwCkmxMap(QgzxXsgwcxForm myForm,String userName) {
		DAO dao = DAO.getInstance();
		String guid = myForm.getGuid();
		StringBuilder sql = new StringBuilder();
		sql.append("select b.xh,b.xm,b.nj,b.xymc xy,b.zymc zy,b.bjmc bj,d.xn,d.gwmc,d.yrdwdm yrbm,(select bmmc from zxbz_xxbmdm e where d.yrdwdm=e.bmdm) yrdw, ");
		sql.append("(select gwxzmc from xg_qgzx_gwxzdmb f where f.gwxzdm=d.gwxzdm) gwxz,a.GWDM from xg_qgzx_xsgwxxb a ");
		sql.append("left join view_xsbfxx b on a.xh=b.xh  ");
		//sql.append("left join VIEW_NEW_DC_QGZX_CIFFSJWH c on a.xh=c.xh ");
		sql.append("left join xg_qgzx_gwxxb d on a.gwdm=d.gwdm where a.xh=? and a.gwdm=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { userName,guid });
	}

	/**
	 * 查看我的勤工岗位首页面一条数据（酬金发放明细部分）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getXsgwCkcjmxMap(QgzxXsgwcxForm myForm,String userName) throws Exception{
		String guid = myForm.getGuid();
		StringBuilder sql = new StringBuilder();
		sql.append(" select ffsj,yf,je,gs from VIEW_NEW_DC_QGZX_CIFFSJWH where xh=? and gwdm=? ");
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy(sql.toString(), "", new String[]{userName,guid}, new String[]{"ffsj","yf","je","gs"}, myForm);
		return list;
	}

	/**
	 * 学生岗位查询
	 * @param myForm
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> gwxxCx(QgzxXsgwcxForm myForm) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		/*QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}*/
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "gwdm", "r", "xn", "yrdwmc", "gwmc", "xqrs", "knsrs", "zgrs" };
		sql.append(" select a.*,rownum r from view_xg_qgzx_gwxxb a where xn = '"+Base.currXn+"' ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}

	/**
	 * 查看岗位信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> gwxxCk(QgzxXsgwcxForm model) {
		DAO dao = DAO.getInstance();
		String sql = "select * from view_xg_qgzx_gwxxb where gwdm = ? ";
		return dao.getMapNotOut(sql, new String[]{model.getGuid()});
	}
	
	/**
	 * 获取学生岗位信息列表
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuQgzxXsgwxxList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select (select bmmc from zxbz_xxbmdm c where c.bmdm=b.yrdwdm) yrdwmc,b.gwmc,a.gwdm,(select gwxzmc from xg_qgzx_gwxzdmb c where" +
				" b.gwxzdm=c.gwxzdm) gwxzmc,a.xh,a.sgsj,(case when a.zgzt='zg' then '在岗' else '退岗'" +
				" end) zgzt,a.tgsj,a.tgyy from xg_qgzx_xsgwxxb a," +
				"xg_qgzx_gwxxb b where a.gwdm=b.gwdm and xh=? order by a.sgsj desc,b.yrdwdm,a.gwdm", 
				new String[]{xh}, new String[]{"yrdwmc", "gwmc", "gwxzmc", "sgsj", "zgzt", "tgsj", "tgyy"});
	}
	
	/**
	 * 获取学生酬金发放列表
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuQgzxCjffList(String xh) {
		DAO dao = DAO.getInstance();
		String sql="select yrdw as yrdwmc,xh,gwdm,nd||'年'||to_number(yf)||'月'  ffny,gs,je,gwmc from view_new_dc_qgzx_ciffsjwh where xh=?";
		//"select (select bmmc from zxbz_xxbmdm c where c.bmdm=b.yrdwdm) yrdwmc,a.xh,a.gwdm,substr(yf,0,4)||'年'||substr(yf,6,2)||'月' yf,a.gs,a.je,b.gwmc from xg_qgzx_jcffb a," +
		//"xg_qgzx_gwxxb b where a.gwdm =b.gwdm and a.xh=? order by yf desc,gwmc "
		return dao.rsToVator(sql, 
				new String[]{xh}, new String[]{"yrdwmc","gwmc","ffny",  "je", "gs"});
	}
}