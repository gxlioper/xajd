package xgxt.jxgl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class JxglTyDAO extends CommonQueryDAO {

	/**
	 * 获得军训管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getJxglList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "xb" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		other_query = Base.isNull(other_query) ? "" : other_query;

		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得军训管理相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getJxglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}

	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("yesorno".equalsIgnoreCase(lx)) {
			dm = new String[] { "yes", "no" };
			mc = new String[] { "有", "无" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "有", "无" };
			mc = new String[] { "有", "无" };
		} else if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "男", "女" };
			mc = new String[] { "男", "女" };
		} else if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "未审核", "通过", "不通过" };
			mc = new String[] { "未审核", "通过", "不通过" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 删除所上传文件
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = new DAO();

		boolean flag = true;
		String sql = "select " + dzzd + " scdz from " + tableName + " where "
				+ pk + " = ?";
		String wjlj = dao.getOneRs(sql, new String[] { pkValue }, "scdz");
		if (wjlj != null) {
			File file = new File(wjlj);
			if (file.exists()) {
				file.delete();
				sql = "update " + tableName + " set " + dzzd + "='' where "
						+ pk + " = ?";
				flag = dao.runUpdate(sql, new String[] { pkValue });
			}
		}
		return flag;
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		DAO dao = DAO.getInstance();
		String sql = "";
		if ("1".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') || '年' || to_char(sysdate, 'MM') "
					+ "|| '月' || to_char(sysdate, 'DD') || '日 ' now from dual";
		} else if ("2".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') ||  to_char(sysdate, 'MM') "
					+ "||  to_char(sysdate, 'DD') now from dual";
		}
		String now = dao.getOneRs(sql, new String[] {}, "now");
		return now;
	}

	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(dm);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk + "='" + pkValue + "'");
		sql.append(" and rownum = 1 ");

		String value = dao.getOneRs(sql.toString(), new String[] {}, dm);
		return value;
	}
	
	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 根据军训编制级别连动
	 * @param pk
	 * @param pkValue
	 * @param objId
	 * @param jb
	 * @return
	 */
	public List<HashMap<String,String>>getJxjzList(String pk,String pkValue,String jb){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select ''"+jb+"dm ,''"+jb+"mc  from dual  union ");
		sql.append(" select distinct("+jb+"dm)"+jb+"dm,"+jb+"mc from xg_view_jxgl_jxbz where "+pk+"='"+pkValue+"' ");
		sql.append(" and xn=(select dqxn from xtszb where rownum=1) order by "+jb+"mc nulls first  ");
		System.out.println(sql);
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{jb+"dm",jb+"mc"});
	}
	/**
	 * 湖州师范（生成名单）
	 * @return
	 * @throws Exception 
	 */
	public boolean scmd() throws Exception{
		boolean flag = false;
		DAO dao=DAO.getInstance();
		
		String sql = "delete from jxgl_jxmdb where xn = ? and nd= ?";
		String[] input = new String[]{Base.currXn,Base.currNd};
		flag = dao.runUpdate(sql, input);
		if(flag){
			sql = "insert into jxgl_jxmdb(xh,nd,ldbh,isxs,xn)select b.xh,?,a.sjdm,'0',? from (select a.bzdm,a.sjdm from jxbzdmb a where xn = ? and bzdj = '4') a,view_xsjbxx b where a.bzdm=b.bjdm and not exists(select 1 from jxgl_jxmdb where xh = b.xh)";
			input = new String[]{Base.currNd,Base.currXn,Base.currXn};
			flag = dao.runUpdate(sql, input);
		}
		return flag;
	}
}
