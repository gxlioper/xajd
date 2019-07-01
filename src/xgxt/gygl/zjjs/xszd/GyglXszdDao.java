package xgxt.gygl.zjjs.xszd;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForward;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.MakeQuery;

/**
 * 深圳职业新评奖评优奖学金维护DAO
 */
public class GyglXszdDao extends CommDAO {

	DAO dao = DAO.getInstance();

	/**
	 * 保存学生走读申请
	 * 
	 * @author luojw
	 */
	public boolean saveXszdSq(GyglXszdForm model) {

		// 学号
		String xh = model.getXh();
		// ID
		String id = model.getId();
		// 住宿地点
		String zsdd = model.getZsdd();
		// 联系电话
		String lxdh = model.getLxdh();
		// 家庭地址
		String jtdz = model.getJtdz();
		// 家庭电话
		String jtdh = model.getJtdh();
		// 申请时间
		String sqsj = model.getSqsj();
		// 走读开始时间
		String zdkssj = model.getZdkssj();
		// 走读结束时间
		String zdjssj = model.getZdjssj();
		// 申请理由
		String sqly = model.getSqly();
		// 备注
		String bz = model.getBz();
		
		DAO dao = DAO.getInstance();
		
		boolean flag = true;
		
		StringBuilder deleteSql = new StringBuilder();
		deleteSql.append("delete from xg_gygl_zjjs_zdssqb ");
		deleteSql.append("where 1=1 ");
		deleteSql.append("and xh||sqsj = ? ");
		
		try {
			flag = dao.runProcuder(deleteSql.toString(), new String[] { xh
					+ sqsj });
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuilder insertSql = new StringBuilder();
		insertSql.append("insert into xg_gygl_zjjs_zdssqb");
		insertSql.append("(xh,lxdh,zsdd,jtdz,jtdh,zdkssj,zdjssj,sqly,bz)");
		insertSql.append("values(?,?,?,?,?,?,?,?,?)");

		try {
			flag = dao.runProcuder(insertSql.toString(), new String[] { xh,
					lxdh, zsdd, jtdz, jtdh, zdkssj, zdjssj, sqly, bz });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 获得学生走读申请信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXszdSqInfo(String pk) {

		String[] colList = new String[] { "pk","id", "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "lxdh", "zsdd", "jtdz",
				"sqsj", "jtdh", "zdkssj", "zdjssj", "sqly", "bz", "bjsh",
				"bjshr", "bjshyj", "bjshsj", "xysh", "xyshr", "xyshyj",
				"xyshsj" };

		return CommonQueryDAO.commonQueryOne("xg_view_gygl_zjjs_zdssqb",
				colList, "pk", pk);
	}

	/**
	 * 获得奖学金审核列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdshList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String userType = user.getUserType();
		String userStatus = user.getUserStatus();
		
		//	====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================过滤条件 end================================
		
		StringBuilder tableName = new StringBuilder();
		tableName.append("select rownum r,a.* from (");
		tableName.append("select a.pk,a.xh,a.xm,a.bjmc,a.lxdh, ");
		tableName.append("a.nj,a.xydm,a.zydm,a.bjdm,a.zsdd, ");
		tableName.append("a.jtdz,a.jtdh,a.zdkssj,a.zdjssj, ");
		tableName.append("a.bjsh,a.xysh,a.sqsj ");
		if ("fdy".equalsIgnoreCase(userStatus)||"jd".equalsIgnoreCase(userStatus)) {
			tableName.append(",bjsh shzt ");
		}else if("xy".equalsIgnoreCase(userType)){
			tableName.append(",xysh shzt ");
		}
		tableName.append("from xg_view_gygl_zjjs_zdssqb a ");
		tableName.append("where 1=1 ");
		
		if ("fdy".equalsIgnoreCase(userStatus)||"jd".equalsIgnoreCase(userStatus)) {
			tableName.append(" and xysh = '未审核' ");
		}else if("xy".equalsIgnoreCase(userType)){
			tableName.append(" and bjsh = '通过' ");
		}
		tableName.append(searchTjByUser);
		tableName.append(") a");
		tableName.append(query);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
		.commonPageQuery(model.getPages(), tableName.toString(), inputV,
				colList);
		
		return list;
	}
	
	/**
	 * 保存奖学金审核状态
	 * 
	 * @author luojw
	 */
	public boolean saveXszdShzt(GyglXszdForm model, User user) {

		DAO dao = DAO.getInstance();

		boolean flag = true;

		String pk = model.getPk();
		String shr = user.getUserName();
		String shsj = getNowTime("YYYYMMDD");
		String shzt = model.getShzt();

		String[] inputV = null;

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_gygl_zjjs_zdssqb ");
		sql.append("set");
		
		if ("fdy".equalsIgnoreCase(user.getUserStatus())||"jd".equalsIgnoreCase(user.getUserStatus())) {
			sql.append(" bjsh=?");
			sql.append(",bjshsj=?");
			sql.append(",bjshr=?");
			sql.append(",bjshyj=?");

			inputV = new String[] { shzt, shsj, shr, model.getBjshyj(), pk };
		}else if ("xy".equalsIgnoreCase(user.getUserType())) {
			sql.append(" xysh=?");
			sql.append(",xyshsj=?");
			sql.append(",xyshr=?");
			sql.append(",xyshyj=?");

			inputV = new String[] { shzt, shsj, shr, model.getXyshyj(), pk };
		}

		sql.append(" where xh||sqsj=?");

		try {
			flag = dao.runProcuder(sql.toString(), inputV);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 批量保存学生走读审核状态
	 * 
	 * @author luojw
	 */
	public boolean savePlXszdShzt(GyglXszdForm model, User user) {

		DAO dao = DAO.getInstance();

		boolean flag = true;

		String[] pk = model.getPrimarykey_checkVal();
		String shr = user.getUserName();
		String shsj = getNowTime("YYYYMMDD");
		String shzt = model.getShzt();

		String[] inputV = null;

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_gygl_zjjs_zdssqb ");
		sql.append("set");
		if ("fdy".equalsIgnoreCase(user.getUserStatus())||"jd".equalsIgnoreCase(user.getUserStatus())) {
			sql.append(" bjsh=?");
			sql.append(",bjshsj=?");
			sql.append(",bjshr=?");
		}else if ("xy".equalsIgnoreCase(user.getUserType())) {
			sql.append(" xysh=?");
			sql.append(",xyshsj=?");
			sql.append(",xyshr=?");
		}

		sql.append(" where 1=1 ");
		
		sql.append(" and (");
		for (int i = 0; i < pk.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xh||sqsj='" + pk[i] + "' ");
		}
		sql.append(" )");

		inputV = new String[] { shzt, shsj, shr };
		
		try {
			flag = dao.runProcuder(sql.toString(), inputV);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 获得奖学金结果列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdjgList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String userType = user.getUserType();
		String userStatus = user.getUserStatus();
		
		//	====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================过滤条件 end================================
		
		StringBuilder tableName = new StringBuilder();
		tableName.append("select rownum r,a.* from (");
		tableName.append("select a.pk,a.xh,a.xm,a.bjmc,a.lxdh, ");
		tableName.append("a.nj,a.xydm,a.zydm,a.bjdm,a.zsdd, ");
		tableName.append("a.jtdz,a.jtdh,a.zdkssj,a.zdjssj, ");
		tableName.append("a.xymc,a.zymc,a.xb, ");
		tableName.append("a.bjsh,a.xysh,a.sqsj,a.sqly ");
		tableName.append("from xg_view_gygl_zjjs_zdssqb a ");
		tableName.append("where 1=1 ");
		
		tableName.append(searchTjByUser);
		tableName.append(") a");
		tableName.append(query);
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
		.commonPageQuery(model.getPages(), tableName.toString(), inputV,
				colList);
		
		return list;
	}
	
	/**
	 * 获得奖学金结果列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXszdTjList(GyglXszdForm model,
			String[] colList, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String userType = user.getUserType();
		String userStatus = user.getUserStatus();
		
		//	====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================过滤条件 end================================
		
		StringBuilder tableName = new StringBuilder();
		tableName.append("select rownum r,a.* from (");
		tableName.append("select a.pk,a.xh,a.xm,a.bjmc,a.lxdh, ");
		tableName.append("a.nj,a.xydm,a.zydm,a.bjdm,a.zsdd, ");
		tableName.append("a.jtdz,a.jtdh,a.zdkssj,a.zdjssj, ");
		tableName.append("a.xymc,a.zymc,a.xb, ");
		tableName.append("a.bjsh,a.xysh,a.sqsj,a.sqly ");
		tableName.append("from xg_view_gygl_zjjs_zdssqb a ");
		tableName.append("where 1=1 ");
		
		tableName.append(searchTjByUser);
		tableName.append(") a");
		tableName.append(query);
		
		DAO dao = DAO.getInstance();
		ArrayList<String[]> list = dao.rsToVator(tableName.toString(), inputV,
				colList);
		
		return list;
	}
}
	