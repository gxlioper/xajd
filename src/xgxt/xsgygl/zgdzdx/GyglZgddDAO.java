package xgxt.xsgygl.zgdzdx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.rcsw.RcswDAO;
import xgxt.utils.MakeQuery;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyForm;

public class GyglZgddDAO extends GyglTyDAO {
	
	/**
	 * 获得校区楼栋信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXqldInfoList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] {"xqdm", "lddm", "cs",
				"qsh" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String xydm = model.getXydm();
		String nj = model.getNj();
		
		StringBuffer sb = new StringBuffer();
		sb.append(Base.isNull(xydm) ? "" : " and xydm like '%" + xydm + "%' ");
		sb.append(Base.isNull(nj) ? "" : " and nj like '%" + nj + "%' ");
		
		query+=sb.toString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct xqdm,xqmc,lddm,ldmc from view_zgdd_gygl_tjjbxx ");
		sql.append(query);
		sql.append("order by xqdm,lddm ");

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "xqdm", "xqmc", "lddm", "ldmc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 获得楼栋层数信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getLdcsInfoList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] {"xqdm", "lddm", "cs",
				"qsh" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String xydm = model.getXydm();
		String nj = model.getNj();
		
		StringBuffer sb = new StringBuffer();
		sb.append(Base.isNull(xydm) ? "" : " and xydm like '%" + xydm + "%' ");
		sb.append(Base.isNull(nj) ? "" : " and nj like '%" + nj + "%' ");
		
		query+=sb.toString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xqdm, a.lddm, a.cs, count(a.qsh) fjs, sum(cws) zcw, sum(kcws) kcw ");
		sql.append("from view_zgdd_gygl_tjjbxx a ");
		sql.append(query);
		sql.append("group by a.xqdm, a.lddm, a.cs ");
		sql.append("order by a.xqdm, a.lddm, a.cs ");

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] {"xqdm","lddm","cs","fjs","zcw","kcw"};

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 获得寝室信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getQsInfoList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] { "xqdm", "lddm", "cs",
				"qsh","xymc" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String xydm = model.getXydm();
		String nj = model.getNj();
		
		StringBuffer sb = new StringBuffer();
		sb.append(Base.isNull(xydm) ? "" : " and xydm like '%" + xydm + "%' ");
		sb.append(Base.isNull(nj) ? "" : " and nj like '%" + nj + "%' ");
		
		query+=sb.toString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.xqdm,a.lddm,a.cs,a.qsh,a.color,a.ssbh, ");
		sql.append("a.xymc,a.qtxy ");
		//--------------拼接混住寝室学院名称----------
//		sql.append("case when a.xydm is not null and a.qtxy is not null then ");
//		sql.append("(select b.xysx from zgdd_xysxb b where a.xydm = b.xydm)||'/'||a.qtxy  ");
//		sql.append("when a.xydm is not null then ");
//		sql.append("(select b.xysx from zgdd_xysxb b where a.xydm = b.xydm) ");
//		sql.append("when a.qtxy is not null then a.qtxy end xymc,");
		//------------------end------------------------
		//sql.append("(select b.njsx from zgdd_njsxb b where a.nj = b.nj) sxnj ");
		sql.append("from view_zgdd_gygl_tjjbxx a ");
		sql.append(query);

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] {"xqdm","ssbh","lddm","cs","qsh","xymc","qtxy","color"};

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}

	/**
	 * 获得统计基本信息
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getTjjbxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] {"xqdm", "lddm", "cs",
				"qsh" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String xydm = model.getXydm();
		String nj = model.getNj();
		
		StringBuffer sb = new StringBuffer();
		sb.append(Base.isNull(xydm) ? "" : " and xydm like '%" + xydm + "%' ");
		sb.append(Base.isNull(nj) ? "" : " and nj like '%" + nj + "%' ");
		sb.append(" and xqdm is not null and lddm is not null ");
		sb.append(" and cs is not null and qsh is not null ");
		
		query+=sb.toString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.bxynjrs,t.cs,t.cws,t.fpbj,t.lddm,t.ldmc,t.nj,t.qsh, ");
		sql.append("t.ssbh,t.wxsrs,t.xqdm,t.xqmc,t.xydm,t.xymc from view_zgdd_gygl_tjjbxx t ");
		sql.append(query);
		sql.append("order by t.xqdm,t.lddm,t.cs,t.qsh ");

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "bxynjrs", "cs", "cws", "fpbj",
				"lddm", "ldmc", "nj", "qsh", "ssbh", "wxsrs", "xqdm", "xqmc",
				"xydm","xymc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 获得其他信息
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getQtxyxxList(GyglTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();

		String[] queryList = new String[] {"xqdm", "lddm", "cs",
				"qsh" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String xydm = model.getXydm();
		String nj = model.getNj();
		
		StringBuffer sb = new StringBuffer();
		sb.append(Base.isNull(xydm) ? "" : " and xydm like '%" + xydm + "%' ");
		sb.append(Base.isNull(nj) ? "" : " and nj like '%" + nj + "%' ");
		
		query+=sb.toString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.cs,t.lddm,t.nj,t.qsh,t.ssbh,t.xydm,t.xymc,t.yzrs, ");
		sql.append("t.xqdm from view_zgdd_gygl_qtxyxx t ");
		sql.append(query);
		sql.append("order by t.xqdm,t.lddm,t.cs,t.qsh,t.xydm,t.nj ");

		String[] inputValue = myQuery.getInputList();
		String[] outputValue = new String[] { "cs", "lddm", "nj", "qsh",
				"ssbh", "xydm", "xymc", "yzrs", "xqdm" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
}
