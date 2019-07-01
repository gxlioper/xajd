package xgxt.bdsz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 自定义表单DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 屈朋辉
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2010-05-31
 * </p>
 */
public class BdszDAO extends DAO {

	/**
	 * 删除自定义字段
	 * 
	 * @param pk
	 * @param zdyTable
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delBdsz(String pk, String zdyTable,
			HttpServletRequest request) throws Exception {
		String tableName = "ty_bdsz";
		String primaryKey = "zdid||'-'||tabname";
		String tabName = "";

		String[] temp = pk.split("-");

		if (null != temp && pk.length() >= 1) {
			tabName = temp[1];
		}

		boolean flg = StandardOperation.delete(tableName, primaryKey, pk,
				request);

		String[] tmp = pk.split(tabName);
		String zdid = tmp[0];
		if (flg) {
			String[] pkCol = CommonQueryDAO.GetPkCol(tabName);
			String pkC = "";
			for (int i = 0; i < pkCol.length - 1; i++) {
				pkC += pkCol[i];
				pkC += "||";
			}
			pkC += pkCol[pkCol.length - 1];
			String sql = "delete from " + zdyTable
					+ " a where exists (select 1 from " + tabName + " where "
					+ pkC + "=a.zbid ) and zdid = ? and tabname = ? ";
			flg = runUpdate(sql, new String[] { zdid, tabName });
		}
		return flg;
	}

	/**
	 * 获取模块列表
	 * @deprecated 数据存在数据库里了
	 * @param zdyTable
	 * @return
	 */
	public List<HashMap<String, String>> getMkList(String mkmc) {
		String[] dm = null;
		String[] mc = null;

		if ("rcsw".equals(mkmc)) {
			dm = new String[] { "rcsw_zxzmb", "rcsw_bxwh", "rcsw_lpwh" };
			mc = new String[] { "在校证明申请", "保险维护", "理赔维护" };
		} else if ("jygl".equals(mkmc)) {
			dm = new String[] { "jygl_jygp", "jygl_ecfp" };
			mc = new String[] { "改派", "二次分配" };
		} else if ("xsh".equals(mkmc)) {
			dm = new String[] { "xsh_hdxx", "xsh_xcxx", "xsh_stglb",
					"xsh_sthdglb" };
			mc = new String[] { "志愿者服务", "宣传信息", "社团信息", "社团活动" };
		} else {
			dm = new String[] { "" };
			mc = new String[] { "" };
		}
		return arrayToList(dm, mc);
	}

	/**
	 * 获取自定义字段列表
	 * 
	 * @param tableName
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getBdszList(String tableName, BdszModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "mkdm", "tabname" };
		String[] queryLikeList = new String[] { "zdid" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(), myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 返回单个自定义字段
	 * 
	 * @param tableName
	 * @param colList
	 * @param pkCol
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> getBdsz(String tableName, String[] colList,
			String pkCol, String pk) {
		// TODO 自动生成方法存根
		return CommonQueryDAO.commonQueryOne(tableName, colList, pkCol, pk);
	}

	/**
	 * 对设置自定义字段表的下拉框选项进行先删后增操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updataOps(BdszModel model, String pkValue) throws Exception {
		String[] opid = model.getOpid();
		String[] opmc = model.getOpmc();
		String[] insertSqls = new String[opmc.length];
		boolean result = runUpdate(
				"delete from ty_bdszxxb where szbzj = ?",
				new String[] { pkValue });

		if (null != opmc && opmc.length > 0) {
			for (int i = 0; i < opmc.length; i++) {

				StringBuilder sql = new StringBuilder();

				sql.append("insert into ty_bdszxxb values ('");
				sql.append(pkValue);
				sql.append("','");
				sql.append(opid[i]);
				sql.append("','");
				sql.append(opmc[i]);
				sql.append("')");

				insertSqls[i] = sql.toString();

			}

			if (result) {
				int[] tempArr = runBatch(insertSqls);
				result = checkBatch(tempArr);
			}
		}

		return result;
	}

	/**
	 * 获取选项列列表
	 * 
	 * @param pk
	 * @return
	 */
	public List<HashMap<String, String>> getOpList(String pk) {
		String sql = "select rownum opxh,opid,opmc from ty_bdszxxb where szbzj = ?";
		return getList(sql, new String[] { pk }, new String[] { "opxh",
				"opid", "opmc" });
	}

	/**
	 * 获取自定义表单字段
	 * 
	 * @param realTable
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getBdZd(String realTable, String mkmc) {
		String bdszTable = "ty_bdsz";// 自定义字段信息表

		String sql = "select zdid,zdmc,zdlx,zdcd,'' sfnum,'' sfnull,'' xzf from "
				+ bdszTable + " where tabname = ?  order by zdlx,zdpx";

		if ("pjpy".equalsIgnoreCase(mkmc)) {// 评奖评优模块
			bdszTable = "py_bdszb";
			sql = "select zdid,zdmc,zdlx,zdcd,sfnum,sfnull,xzf from "
					+ bdszTable + " where tabname = ?  order by zdlx,zdpx";
		}

		return getList(sql, new String[] { realTable }, new String[] {
				"zdid", "zdmc", "zdlx", "zdcd", "sfnum", "sfnull", "xzf" });
	}

	/**
	 * 获得自定义字段选项列
	 * 
	 * @param tableName
	 * @return HashMap<String, ArrayList<HashMap<String, String>>>
	 */
	public HashMap<String, ArrayList<HashMap<String, String>>> getOps(
			String mkmc, String realTable) {

		String zdxxTable = "ty_bdsz";
		if ("pjpy".equalsIgnoreCase(mkmc)) {// 评奖评优
			zdxxTable = "py_bdszb";
		}
		String sql = "select b.zdid,a.opid,a.opmc from ty_bdszxxb a "
				+ "left join " + zdxxTable
				+ " b on a.szbzj= b.zdid||b.tabname "
				+ "where b.tabname = ?  order by b.zdpx";
		return getOptionList(sql, new String[] { realTable }, new String[] {
				"zdid", "opid", "opmc" });
	}

	/**
	 * 获取表单字段
	 * 
	 * @param tableName
	 * @param xn
	 */
	public List<HashMap<String, String>> getBdZd(String tableName) {
		String sql = "select zdid,zdmc,zdlx,zdcd from ty_bdsz where tabname = ?  order by zdlx,zdpx";
		return getList(sql, new String[] { tableName }, new String[] {
				"zdid", "zdmc", "zdlx", "zdcd" });
	}

	/**
	 * 获取导出sql
	 * 
	 * @param tableName
	 * @param myModel
	 * @param colList
	 * @param zdyCol
	 * @param realTable
	 * @param queryList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String getDcSql(String tableName, String zdyTable, String[] zdyCol,
			String realTable, String query) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		StringBuffer sqlBuffer = new StringBuffer("select a.*");
		for (int i = 0; i < zdyCol.length; i++) {
			sqlBuffer.append(",(select bcnr from ");
			sqlBuffer.append(zdyTable);
			sqlBuffer.append(" where tabname = '");
			sqlBuffer.append(realTable);
			sqlBuffer.append("' and zdid = '");
			sqlBuffer.append(zdyCol[i]);
			sqlBuffer.append("' and zbid = a.pk ) ");
			sqlBuffer.append(zdyCol[i]);
		}
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" a ");
		sqlBuffer.append(query);
		return sqlBuffer.toString();
	}

	

	

	/**
	 * 删除自定义字段数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean delZdyData(String tableName, String pkValue)
			throws Exception {
		String sql = "delete from ty_bdsz_bcnr where tabname = ? and zbid = ?";

		return runUpdate(sql, new String[] { tableName, pkValue });
	}

	/**
	 * 获取需要做自定义字段维护的功能模块
	 * 
	 * @author qph
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList() {

		StringBuilder sql = new StringBuilder();

		sql.append("select distinct mkdm,(select b.gnmkmc from gnmkdmb b where ");
		sql.append("b.gnmkdm like 'N__' and b.gnmkdm=a.mkdm and exists ");
		sql.append("(select 1 from yhqxb where gnmkdm = b.gnmkdm)) mkmc from ");
		sql.append(" xg_bdsz_gnmkb a");

		return getList(sql.toString(), new String[] {}, new String[] {
				"mkdm", "mkmc" });
	}

	/**
	 * 获取需要做自定义字段维护的功能点
	 * 
	 * @author qph
	 * @param mkdm
	 * @return
	 */
	public List<HashMap<String, String>> getGnmcListByMkdm(String mkdm) {

		String sql = "select distinct gnb,gnmc from xg_bdsz_gnmkb where mkdm=?";

		return getList(sql, new String[] { mkdm }, new String[] { "gnb",
				"gnmc" });
	}

	/**
	 * 获取需要做自定义字段维护的功能点
	 * 
	 * @author qph
	 * @return
	 */
	public List<HashMap<String, String>> getGnmcList() {

		String sql = "select distinct gnb,gnmc from xg_bdsz_gnmkb";

		return getList(sql, new String[] {}, new String[] { "gnb", "gnmc" });
	}

	/**
	 * 根据键值返回自定义字段业务数据保存表
	 * 
	 * @param pkValues
	 * @return
	 */
	private List<HashMap<String, String>> getZdbcszList(String[] pkValues) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select zdid||tabname pkValue,mkdm,");
		sql
				.append("nvl((select bcnrb from xg_bdsz_bcnrszb where gnmkdm=a.mkdm),");
		sql.append("'ty_bdsz_bcnr') bcnrb from ty_bdsz a");
		sql.append(" where zdid||tabname in (");

		for (int i = 0; i < pkValues.length; i++) {

			sql.append("'");
			sql.append(pkValues[i]);
			sql.append("'");

			if (i == pkValues.length - 1) {
				sql.append(")");
			} else {
				sql.append(",");
			}
		}

		return getList(sql.toString(), new String[] {}, new String[] {
				"pkValue", "bcnrb" });
	}

	/**
	 * 根据自定义字段、主表名获取相应内容
	 * 
	 * @param pkValues
	 * @return
	 */
	public List<HashMap<String, String>> getZdnrList(String[] pkValues) {

		List<HashMap<String, String>> zdnrbcList = getZdbcszList(pkValues);
		StringBuilder sql = new StringBuilder();

		for (int i = 0; i < zdnrbcList.size(); i++) {
			sql.append("select distinct zdid,tabname,");
			sql
					.append("(select gnmc from xg_bdsz_gnmkb where gnb=a.tabname and rownum=1) gnmc ");
			sql.append("from ");
			sql.append(zdnrbcList.get(i).get("bcnrb"));
			sql.append(" a where zdid||tabname ='");
			sql.append(zdnrbcList.get(i).get("pkValue"));
			sql.append("'");

			if (i != zdnrbcList.size() - 1) {
				sql.append(" union all ");
			}
		}

		return getList(sql.toString(), new String[] {}, new String[] {
				"zdid", "tabname", "gnmc" });
	}

	/**
	 * 删除自定义字段
	 * 如果字段类型为下拉列表的要删除下拉列表内容
	 * @param pkValues
	 * @return
	 * @throws Exception
	 */
	public boolean delZdyZd(String[] pkValues) throws Exception {

		StringBuilder sql = new StringBuilder();
		StringBuilder delOpt = new StringBuilder();

		sql.append("delete from ty_bdsz where zdid||tabname in (");
		delOpt.append("delete from ty_bdszxxb where szbzj in (");

		for (int i = 0; i < pkValues.length; i++) {
			sql.append("'");
			sql.append(pkValues[i]);
			sql.append("'");

			delOpt.append("'");
			delOpt.append(pkValues[i]);
			delOpt.append("'");

			if (i == pkValues.length - 1) {
				sql.append(")");
				delOpt.append(")");
			} else {
				sql.append(",");
				delOpt.append(",");
			}
		}

		return runUpdate(sql.toString(), new String[] {})
				&& runUpdate(delOpt.toString(), new String[] {});
	}

	/**
	 * 根据主表名获取自定义字段内容的保存表
	 * 
	 * @param realTable
	 * @return
	 */
	public String getZdyBcnrb(String realTable) {

		String sql = "select gnmkdm,bcnrb from xg_bdsz_bcnrszb where gnmkdm=(select mkdm from xg_bdsz_gnmkb where gnb=?)";

		return getOneRs(sql, new String[] { realTable }, "bcnrb");
	}

	/**
	 * 返回表中的全部列
	 * 
	 * @param tableName
	 * @return
	 */
	protected String[] getColumn(String tableName) {

		String sql = "select * from " + tableName;
		String[] colList = getColumnName(sql);
		
		if (null != colList && colList.length > 0) {
			
			String[] columns = new String[colList.length] ;
			
			for (int i = 0 ; i < colList.length ;i++) {
				columns[i] = colList[i].toLowerCase();
			}
			return columns;
		} else {
			return new String[] {};
		}
		
		
	}

	/**
	 * 返回指定表中指定列的中文名
	 * 
	 * @param colList
	 * @param tableName
	 * @return
	 */
	protected String[] getColumnCn(String[] colList, String tableName) {

		return getColumnNameCN(colList, tableName);
	}
	

	/**
	 * 返回导出sql
	 * 
	 * @param realTable
	 *            主表名
	 * @param pkKey
	 *            主表主键
	 * @param tableName
	 *            视图名
	 * @param zdyTable
	 *            自定义字段业务数据保存表
	 * @param zdyCol
	 *            自定义字段
	 * @param zdyZdLx
	 *            自定义字段类型
	 * @param query
	 *            过滤条件
	 * @return
	 */
	public String getQueryDataSql(String realTable, String pkKey,
			String tableName, String zdyTable, String[] zdyCol,
			String[] zdyZdLx, String query) {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r,")
		   .append(pkKey)
		   .append(" pkValue");

		for (int i = 0; i < zdyCol.length; i++) {
			// 如果自定义字段类型为下拉列表的去查询下对应值
			String value=zdyCol[i].replace("zdid_", "");
			if ("003".equals(zdyZdLx[i])) {
				sql.append(",(select opmc from ty_bdszxxb where opid=(select bcnr from ");
				sql.append(zdyTable);
				sql.append(" where tabname = '");
				sql.append(realTable);
				sql.append("' and zdid = '");
				sql.append(value);
				sql.append("' and zbid = ");
				sql.append(pkKey);
				sql.append(") and szbzj='");
				sql.append(value);
				sql.append(realTable);
				sql.append("')");
				sql.append(zdyCol[i]);
			} else {
				sql.append(",(select bcnr from ");
				sql.append(zdyTable);
				sql.append(" where tabname = '");
				sql.append(realTable);
				sql.append("' and zdid = '");
				sql.append(value);
				sql.append("' and zbid = ");
				sql.append(pkKey);
				sql.append(")");
				sql.append(zdyCol[i]);
			}
		}
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" a ");
		sql.append(query);
		return sql.toString();
	}

	
	/**
	 * 查询要显示的自定义字段
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> zdyColList(String realTable)
			throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select zdid,zdmc,zdlx from ty_bdsz where ");
		sql.append("tabname = ? and cxxs = '显示' order by cxxspx");
		
		return getList(sql.toString(), new String[] { realTable }, 
							new String[] {"zdid", "zdmc" ,"zdlx"});
	}
	
	
	/**
	 * 保存自定义字段数据
	 * @param realTable 主表
	 * @param zdyTable 自定义字段数据保存表
	 * @param pkValue 主表数据主键值，联合主键 “||”拼接
	 * @param zdyZd  HashMap<String,String> 键zdid-自定义字段 值zdValue-自定义字段值 
	 * @return
	 * @throws Exception
	 */
	public boolean saveZdyData(String realTable, String zdyTable,
			String pkValue, List<HashMap<String, String>> zdyZd)
			throws Exception {

		String[] sqlArr = new String[zdyZd.size()];

		for (int i = 0; i < zdyZd.size(); i++) {
			StringBuilder sql = new StringBuilder();

			sql.append("insert into ");
			sql.append(zdyTable);
			sql.append(" values ('");
			sql.append(zdyZd.get(i).get("zdid"));
			sql.append("','");
			sql.append(realTable);
			sql.append("','");
			sql.append(pkValue);
			sql.append("','");
			sql.append(zdyZd.get(i).get("zdValue"));
			sql.append("')");

			sqlArr[i] = sql.toString();
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	/**
	 * 单条自定义字段数据
	 * @param realTable
	 * @param zdyTable
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getZdyDataByOne(String realTable,String zdyTable,String pkValue){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select zdid,bcnr from ");
		sql.append(zdyTable);
		sql.append(" where tabname=? and zbid=?");
		
		return getList(sql.toString(), new String[] {realTable,pkValue}, new String[] {"zdid","bcnr"});
	}
	
	
	/**
	 * 修改自定义字段数据
	 * @param realTable 主表
	 * @param zdyTable 自定义字段数据保存表
	 * @param newPkValue 主表的新主键
	 * @param oldPkValue 主表的旧主键
	 * @param zdyZd HashMap<String,String> 键zdid-自定义字段 值zdValue-自定义字段值 
	 * @return
	 * @throws Exception
	 */
	public boolean updataZdyData(String realTable, String zdyTable,
			String newPkValue,String oldPkValue, List<HashMap<String, String>> zdyZd) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ").append(zdyTable).append(" where tabname=? and zbid=?");
		
		boolean result = runUpdate(sql.toString(), new String[] {realTable,oldPkValue});
		
		if (result) {
			result = saveZdyData(realTable, zdyTable, newPkValue, zdyZd);
		}
		
		return result;
	}

	
	/**
	 * 删除使用自定义字段业务模块的数据
	 * @param realTable
	 * @param zdyTable
	 * @param pkKey
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delData(String realTable, String zdyTable, String pkKey,
			String[] pkValue) throws Exception {

		StringBuilder delZbSql = new StringBuilder();
		StringBuilder delZdySql = new StringBuilder();

		delZbSql.append("delete from ").append(realTable).append(" where ")
				.append(pkKey).append(" in (");

		delZdySql.append("delete from ").append(zdyTable).append(
				" where tabname='").append(realTable).append("' and zbid in (");

		for (int i = 0; i < pkValue.length; i++) {
			delZbSql.append("'").append(pkValue[i]).append("'");

			delZdySql.append("'").append(pkValue[i]).append("'");

			if (i != pkValue.length - 1) {
				delZbSql.append(",");
				delZdySql.append(",");
			} else {
				delZbSql.append(")");
				delZdySql.append(")");
			}
		}

		return runUpdate(delZbSql.toString(), new String[] {}) && runUpdate(delZdySql.toString(), new String[] {});
	}



	/**
	 * 返回主表相关的全部自定义字段
	 * @param realTable
	 * @return
	 */
	public List<HashMap<String, String>> getZdyZd(String realTable) {

		String sql = "select zdid,zdmc,zdlx,zdcd,sfbt from ty_bdsz where tabname = ?  order by zdlx,zdpx";


		return getList(sql, new String[] { realTable }, new String[] {
				"zdid", "zdmc", "zdlx", "zdcd", "sfbt" });
	}
	
	
	
	/**
	 * 返回主表自定义字段的下拉列表选项
	 * @param realTable
	 * @return
	 */
	public HashMap<String, ArrayList<HashMap<String, String>>> getZdyOptions(String realTable) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("select b.zdid,a.opid,a.opmc from ty_bdszxxb a ");
		sql.append("left join ty_bdsz b ");
		sql.append("on a.szbzj= b.zdid||b.tabname ");
		sql.append("where b.tabname = ?  order by b.zdpx");
		
		
		return getOptionList(sql.toString(), new String[] {realTable }, new String[] {"zdid", "opid", "opmc" });
	}

}
