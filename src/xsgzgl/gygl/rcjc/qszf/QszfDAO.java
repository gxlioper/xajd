package xsgzgl.gygl.rcjc.qszf;

import java.sql.SQLException;
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
 * Time:2012-7-9 下午14:19:22
 * </p>
 */

public class QszfDAO extends CommDAO {
	/**
	 * 寝室走访首页面查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getQszfCx(QszfForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] { "guid", "xymc", "xm", "bjmc", "ldmc", "qsh", "xqsj" };
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append("select guid,xymc,xm,bjmc,ldmc,qsh,xqsj,lddm,zgh,xydm,nj,bjdm,zydm,rownum r from (select d.guid,c.xymc,d.xm, ");
		sql.append(" (select bjmc from view_njxyzybj_all where bjdm = d.bjdm) bjmc, ");
		sql.append(" c.ldmc,d.qsh,d.xqsj,d.lddm lddm,d.zgh,c.xydm xydm,c.nj,d.bjdm bjdm, ");
		sql.append(" (select zydm from view_njxyzybj_all where bjdm = d.bjdm) zydm,rownum r ");
		sql.append(" from VIEW_XG_GYGL_NEW_QSXX c ");
		sql.append(" join (select b.zgh, b.xm, a.bjdm, a.lddm, a.qsh, a.xqsj, a.guid from fdyxxb b ");
		sql.append(" join (select bjdm,zgh,lddm,qsh,xqsj,bjdm || '!@' || zgh || '!@' || lddm || '!@' || qsh || '!@' || xqsj guid ");
		sql.append(" from XG_GYGL_JHZY_BZRXQB) a on a.zgh = b.zgh) d on c.LDDM =d.lddm and c.qsh =d.qsh) ");
		sql.append(query);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 获取所有走访老师
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getZfls(QszfForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		String[] colList = new String[] { "zgh", "xm" };
		// 用户属性
		String userType = user.getUserType();
		// ====================过滤条件===================================
		user.setUserStatus(userType);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		sql.append("select zgh,xm,rownum r from (select a.zgh,b.xm from bzrbbb a left join fdyxxb b on a.zgh=b.zgh group by a.zgh,b.xm)");
		sql.append(query);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, myForm);
		return list;
	}

	/**
	 * 级联查询-由走访老师工号获得负责班级
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFzbjForZflsgh(String[] inputValue,String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select zgh,bjdm,bjmc from (select zgh,bjdm,(select bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm) bjmc from bzrbbb a where zgh=?) where bjmc is not null");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 级联查询-由走访老师工号获得走访老师姓名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZflsxmForZflsgh(QszfForm QszfForm,String zflsgh) {
		DAO dao = DAO.getInstance();
		String sql = "select xm from fdyxxb where zgh=?";
		return dao.getMapNotOut(sql.toString(), new String[] { zflsgh });
	}

	/**
	 * 级联查询-由负责班级获得楼栋
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLdForFzbj(String[] inputValue,String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct LDDM,LDMC from view_xg_gygl_new_cwxx where bjdm=?");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 级联查询-由楼栋号获得寝室号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQshForLd(String[] inputValue,String[] outputValue) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qsh xsszqsh from view_xg_gygl_new_cwxx where bjdm=? and lddm=?");
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 寝室走访信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfBc(QszfForm myForm, String username) throws Exception {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String zflsgh = myForm.getZflsgh();
		String fzbj = myForm.getFzbj();
		String xsszld = myForm.getXsszld();
		String xsszqsh = myForm.getXsszqsh();
		String xqsj = myForm.getXqsj();
		String bz = myForm.getBz();
		String sql = "insert into XG_GYGL_JHZY_BZRXQB (BJDM,ZGH,LDDM,QSH,XQSJ,BZ) values (?,?,?,?,?,?)";
		flag = dao.runUpdate(sql.toString(), new String[] { fzbj, zflsgh, xsszld, xsszqsh, xqsj, bz });
		return flag;
	}

	/**
	 * 寝室走访信息删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfSc(QszfForm myForm, String[] valArr, String username)throws SQLException {
		List<String[]> sqlList = new ArrayList<String[]>();
		String sql = "delete from XG_GYGL_JHZY_BZRXQB where bjdm||zgh||lddm||qsh||xqsj=?";
		String[] val = null;
		for (String fiveVal : valArr) {
			val = new String[1];
			val[0] = fiveVal.replaceAll("!@", "");
			sqlList.add(val);
		}
		DAO dao = DAO.getInstance();
		boolean flag = dao.checkBatchResult(dao.runBatch(sql, sqlList));
		return flag;
	}

	/**
	 * 获取学院下拉框集合
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object getXyList(HttpServletRequest request) {
		String sql = "select distinct xymc from VIEW_XG_GYGL_JHZY_BZRXQB";
		DAO dao = DAO.getInstance();
		return dao.getList(sql, new String[] {}, new String[] { "xymc","xymc" });
	}

	/**
	 * 获取寝室走访信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getQszfMap(QszfForm QszfForm) {
		DAO dao = DAO.getInstance();
		String guid = QszfForm.getGuid();
		String[] valArr = guid.split("!@");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_GYGL_JHZY_BZRXQB where bjdm=? and zgh=? and lddm=? and qsh=? and xqsj=?");
		return dao.getMapNotOut(sql.toString(), new String[] { valArr[0], valArr[1], valArr[2], valArr[3], valArr[4] });
	}

	/**
	 * 寝室走访信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean qszfXg(QszfForm myForm, String username) throws Exception {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String zflsgh = myForm.getZflsgh();
		String fzbj = myForm.getFzbj();
		String xsszld = myForm.getXsszld();
		String xsszqsh = myForm.getXsszqsh();
		String fzbjBefore = myForm.getBjBefore();
		String xsszldBefore = myForm.getXsszldBefore();
		String xsszqshBefore = myForm.getXsszqsBefore();
		String xqsj = myForm.getXqsj();
		String bz = myForm.getBz();
		String sql = "delete from XG_GYGL_JHZY_BZRXQB where bjdm=? and zgh=? and lddm=? and qsh=? and xqsj=?";
		flag = dao.runUpdate(sql.toString(), new String[] { fzbjBefore, zflsgh, xsszldBefore, xsszqshBefore, xqsj });
		if (flag == true) {
			sql = "insert into XG_GYGL_JHZY_BZRXQB (BJDM,ZGH,LDDM,QSH,XQSJ,BZ) values (?,?,?,?,?,?)";
			flag = dao.runUpdate(sql.toString(), new String[] { fzbj, zflsgh, xsszld, xsszqsh, xqsj, bz });
		}
		return flag;
	}

	/**
	 * 获得走访次数统计表信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> expXqtjbList(QszfForm model, String xn, String yf, String xymc) throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String[]> list = null;
		sql.append(" select xy,bzr,bj,xsszqs,xqsj,count(*) xqsdcs from (");
		sql.append("select xymc xy,xm bzr,bjmc bj,ldmc||''||qsh xsszqs,substr(xqsj,0,6) xqsj from VIEW_XG_GYGL_JHZY_BZRXQB) ");
		if(xn !="" || yf !="" || xymc !=""){
			sql.append(" where ");
			if(xn !=""){
				sql.append(" substr(xqsj,0,4)='"+xn+"' ");
			}
			if(yf !=""){
				if(xn !=""){
					sql.append(" and ");
				}
				sql.append(" substr(xqsj,5,5)='"+yf+"' ");
			}
			if(xymc !=""){
				if(xn !="" ||  yf !=""){
					sql.append(" and ");
				}
				sql.append(" xy='"+xymc+"' ");
			}
		}
		sql.append("group by xy,bzr,bj,xsszqs,xqsj order by xy,bj,xsszqs");
		String[] colList = null;
		colList = new String[] { "xy", "bzr", "bj", "xsszqs", "xqsdcs" };
		list = commonQueryNotFy(sql.toString(), "", new String[] {}, colList, model);
		return list;
	}
}