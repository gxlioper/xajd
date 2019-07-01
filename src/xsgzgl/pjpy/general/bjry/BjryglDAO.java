package xsgzgl.pjpy.general.bjry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class BjryglDAO extends CommDAO {

	/**
	 * 获取班级荣誉管理信息
	 * @param myForm
	 * @return
	 */
	public ArrayList<String[]> getBjryglCx(BjryglForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] { "guid", "pjzq", "nj", "xymc", "zymc", "bjmc", "mc", "hdsj"};
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String qxSql = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append("select xn||'!@'||xq||'!@'||a.bjdm guid,case when xqmc is not null then xn||' 学年 '||xqmc||' 学期 ' else xn||' 学年 ' end pjzq,nj,xymc,zymc,bjmc,mc,hdsj,rownum r from xg_pjpy_bjryb a left join xqdzb b on a.xq=b.xqdm left join view_njxyzybj_all c on a.bjdm=c.bjdm left join xg_pjpy_bjrydmb d on a.rydm=d.dm ");
		sql.append(query + qxSql);
		sql.append(" order by xn,xq,hdsj ");
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 获得荣誉下拉框
	 * @param request
	 * @return
	 */
	public Object getHdryList(HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		String sql = "select dm,mc from xg_pjpy_bjrydmb";
		return dao.getList(sql, new String[] {}, new String[] { "dm","mc" });
	}

	/**
	 * 查看班级荣誉信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getBjryglMap(BjryglForm myForm) {
		DAO dao = DAO.getInstance();
		String guid = myForm.getGuid();
		String xn = guid.split("!@")[0];
		String xq = guid.split("!@")[1];
		String bjdm = guid.split("!@")[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" select xn,xq,xqmc,nj,xymc,zymc,a.bjdm,bjmc,rydm,mc,hdsj,bz from xg_pjpy_bjryb a left join xqdzb b on a.xq=b.xqdm left join view_njxyzybj_all c on a.bjdm=c.bjdm left join ");
		sql.append(" xg_pjpy_bjrydmb d on a.rydm=d.dm where xn=? and xq=? and a.bjdm=? ");
		return dao.getMapNotOut(sql.toString(),new String[] { xn, xq, bjdm });
	}

	/**
	 * 班级荣誉修改保存
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean bjryglXgBc(BjryglForm myForm) throws Exception{
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String bjdm = myForm.getBjdm();
		String hdsj = myForm.getHdsj();
		String bz = myForm.getBz();
		String sql = "update xg_pjpy_bjryb set hdsj=?,bz=? where xn=? and xq=? and bjdm=?";
		flag = dao.runUpdate(sql.toString(), new String[] { hdsj, bz, xn, xq, bjdm });
		return flag;
	}

	/**
	 * 班级荣誉删除
	 * @param myForm
	 * @param valArr
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean bjryglSc(BjryglForm myForm, String[] valArr, String username) throws Exception{
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "delete from xg_pjpy_bjryb where xn||xq||bjdm=?";
		String[] val = null;
		for (String threeVal : valArr) {
			val = new String[1];
			val[0] = threeVal.replaceAll("!@", "");
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}

	/**
	 * 获取班级名称信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjmc(BjryglForm myForm) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] { "guid", "nj", "xymc", "zymc", "bjmc" };
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjdm guid,nj,xymc,zymc,bjmc,rownum r from view_njxyzybj_all a ");
		sql.append(query + qxSql);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 获取班级信息
	 * @param myForm
	 * @param bjdm
	 * @return
	 */
	public List<HashMap<String, String>> getBjxx(BjryglForm myForm, String bjdm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select nj,xymc,zymc,bjmc,bjdm from view_njxyzybj_all where bjdm=?");
		return dao.getList(sql.toString(), new String[]{bjdm}, new String[]{"nj","xymc","zymc","bjmc","bjdm"});
	}

	/**
	 * 班级荣誉增加保存
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean bjryglZjBc(BjryglForm myForm) throws Exception{
		boolean flag = false;
		DAO dao = DAO.getInstance();

		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String bjdm = myForm.getBjdm();
		String rydm = myForm.getRydm();
		String hdsj = myForm.getHdsj();
		String bz = myForm.getBz();
		if(!xq.equals("")){
			String sql = "insert into xg_pjpy_bjryb (xn,xq,bjdm,rydm,hdsj,bz) values (?,?,?,?,?,?)";
			flag = dao.runUpdate(sql.toString(), new String[] { xn, xq, bjdm, rydm, hdsj, bz});
		}else{
			String sql = "insert into xg_pjpy_bjryb (xn,bjdm,rydm,hdsj,bz) values (?,?,?,?,?)";
			flag = dao.runUpdate(sql.toString(), new String[] { xn, bjdm, rydm, hdsj, bz});
		}
		return flag;
	}
}