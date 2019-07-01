package xgxt.utils.sql;

/**
 * @author LiangChao
 * @date 2008-09-25
 */
import java.util.ArrayList;
import java.util.List;

import xgxt.utils.String.StringUtils;

public class SQL_Util {
	/**
	 * <font color='red'> 只是返回一个没有任何结果的sql语句，主要用于被取回表的字段的注释信息</font> <br>
	 * For Example: <code><font color='blue'>"select * from TABLENAME(表名) where
	 * 1=2"</font></code>
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public static String getNoResultSql(String tableName) {
		return new StringBuffer("select * from ").append(tableName).append(
				" where 1=2 ").toString();
	}

	/**
	 * <font color='red'> 只是返回一个没有任何结果的sql语句，主要用于被取回表的字段的注释信息</font> <br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 * "select columns[0],columns[1]... from TABLENAME(表名) where 1=2"
	 * </font></code>
	 * 
	 * @param tableName
	 * @param columns
	 * @return
	 */
	public static String getNoResultSql(String tableName, String[] columns) {
		return new StringBuffer(SQL_Util.getQueryPrefixSql(tableName, columns))
				.append(" where 1=2 ").toString();
	}

	/**
	 * 返回删除主键值对应的sql语句 返回【带参数的sql】<br>
	 * For Example:<br>
	 * <code>
	 * <font color='blue'> "delete from TABLENAME(表名) where pkColumn = ?"
	 * </font></code>
	 * 
	 * @param tableName
	 *            表名
	 * @param pk
	 *            主键
	 * @return
	 */
	public static String getDelSqlByPKParam(String tableName, String pkColumn) {
		return new StringBuffer("delete from ").append(tableName).append(
				" where ").append(pkColumn).append("=?").toString();
	}

	/**
	 * 返回删除主键值对应的sql语句 返回【带值的sql】<br>
	 * For Example:<br>
	 * <code>
	 * <font color='blue'> "delete from TABLENAME(表名) where pkColumn = pkValue"
	 * </font></code>
	 * 
	 * @param tableName
	 *            表名
	 * @param pk
	 *            主键
	 * @return
	 */
	public static String getDelSqlByPKValue(String tableName, String pkColumn,
			String pkValue) {
		return new StringBuffer("delete from ").append(tableName).append(
				" where ").append(pkColumn).append("='").append(pkValue)
				.append("'").toString();
	}

	/**
	 * 根据主键的值返回select的sql语句【带参数的sql】 <font color='red'><br>
	 * 该语句会将表的一条记录的全部字段查出</font> <br>
	 * For Example:
	 * <code><font color='blue'> "select * from TABLENAME(表名) where PK = ?"
	 * </font></code>
	 * 
	 * @param tableName
	 *            表名或视图名
	 * @param pk
	 *            主键列
	 * @return
	 */
	public static String getQuerySqlByPKParam(String tableName, String pkColumn) {
		return new StringBuffer("select * from ").append(tableName).append(
				" where ").append(pkColumn).append("=?").toString();
	}

	/**
	 * 根据主键的值返回select的sql语句【传值的sql】 <font color='red'><br>
	 * 该语句会将表的一条记录的全部字段查出</font> <br>
	 * For Example:
	 * <code><font color='blue'> "select * from TABLENAME(表名) where PK = pkValue"
	 * </font></code>
	 * 
	 * @param tableName
	 *            表名或视图名
	 * @param pk
	 *            主键列
	 * @return
	 */
	public static String getQuerySqlByPKValue(String tableName,
			String pkColumn, String pkValue) {
		return new StringBuffer("select * from ").append(tableName).append(
				" where ").append(pkColumn).append("='").append(pkValue)
				.append("'").toString();
	}

	/**
	 * 根据主键的值返回update的sql语句【pkValue为值，columns对应的值为参数形式】<br>
	 * <font color='red'>该语句会将表的一条记录的所选字段更新</font> <br>
	 * For Example: <br>
	 * <code><font color='blue'> "update realTable(表名) set
	 * columns[0]=?,columns[1]=? ... where pk=pkValue"</font></code>
	 * 
	 * @param columns
	 *            字段列表
	 * @return
	 */
	public static String getUpdateSqlByPKValue(String realTable, String pk,
			String pkValue, String[] columns) {
		StringBuffer sb = new StringBuffer().append(getUpdatePrefixSql(
				realTable, columns));
		return sb.append(" where ").append(pk).append(" ='").append(pkValue)
				.append("'").toString();
	}

	/**
	 * 根据主键的值返回update的sql语句【pkValue和columns对应的值为参数形式】<br>
	 * <font color='red'>该语句会将表的一条记录的所选字段更新</font> <br>
	 * For Example: <br>
	 * <code><font color='blue'> "update realTable(表名) set
	 * columns[0]=?,columns[1]=? ... where pk=?"</font></code>
	 * 
	 * @param columns
	 *            字段列表
	 * @return
	 */
	public static String getUpdateSqlPKParam(String realTable, String pk,
			String[] columns) {
		StringBuffer sb = new StringBuffer().append(getUpdatePrefixSql(
				realTable, columns));
		return sb.append(" where ").append(pk).append("=?").toString();
	}

	/**
	 * 返回insert的 sql语句 【用参数形式】 <code>For Example:<br><font color='blue'>
	 * insert into tableName (columns[0],...) values(?,....)
	 * </font></code>
	 * 
	 * @param tableName
	 * @param columns
	 * @return
	 */
	public static String getInsertSqlUseParam(String tableName, String[] columns) {
		return new StringBuffer("insert into ").append(tableName).append(
				array2StrForInseSql(columns, ",")).append("values").append(
				getInseSqlParams(columns.length)).toString();
	}

	/**
	 * 返回insert的 sql语句 【用值形式】 <code>For Example:<br><font color='blue'>
	 * insert into tableName (columns[0],...) values(values[0],....)
	 * </font></code>
	 * 
	 * @param tableName
	 * @param columns
	 * @return
	 */
	public static String getInsertSqlUseValue(String tableName,
			String[] columns, String[] values) {
		return new StringBuffer("insert into ").append(tableName).append(
				array2StrForInseSql(columns, ",")).append("values").append(
				getArrayValueTogetherAll(values)).toString();
	}

	/**
	 * 返回一条insert的sql语句，该语句中包含一个sequence<br>
	 * <code><font color='blue'>
	 * insert into tableName(otherColumns[0],otherColumns[1]...,seqColumn)<br>
	 * values(?,?,?,seqName.nextval)
	 * </font></code>
	 * 
	 * @param tableName
	 *            表名
	 * @param otherColumns
	 *            传参的字段数组
	 * @param seqName
	 *            sequence的名字
	 * @param seqColumn
	 *            sequence对应的地段名字
	 * @return
	 */
	public static String getInseSeqSqlUseParam(String tableName,
			String[] otherColumns, String seqName, String seqColumn) {
		return SQL_Util.getInsertSqlUseParam(tableName,
				arrayCopy(otherColumns, new String[] { seqColumn })).replace(
				"?)", seqName + ".nextval)");
	}

	/**
	 * 返回一条insert的sql语句，该语句中包含一个sequence<br>
	 * <code><font color='blue'>
	 * insert into tableName(otherColumns[0],otherColumns[1]...,seqColumn)<br>
	 * values(otherValues[0],otherValues[1]...,seqName.nextval)
	 * </font></code>
	 * 
	 * @param tableName
	 * @param otherColumns
	 * @param otherValues
	 * @param seqName
	 * @param seqColumn
	 * @return
	 */
	public static String getInseSeqSqlUseValue(String tableName,
			String[] otherColumns, String[] otherValues, String seqName,
			String seqColumn) {
		return getInsertSqlUseValue(tableName,
				arrayCopy(otherColumns, new String[] { seqColumn }),
				arrayCopy(otherValues, new String[] { seqName })).replace(
				"'" + seqName + "'", seqName + ".nextval");
	}

	/**
	 * 返回一条insert的sql语句，该语句中包含一个sequence<br>
	 * <code><font color='blue'>
	 * insert into tableName(otherColumns[0],otherColumns[1]...,<br>
	 * vCols[0],vCols[1]...)values(?,?,...,vVals[0],vVals[1]...)
	 * </font></code>
	 * @param tableName
	 * @param pCols
	 * @param vCols
	 * @param vVals
	 * @return
	 */
	public static String getInseSqlUsePaVa(String tableName, String[] pCols,
			String[] vCols, String[] vVals) {
		return new StringBuffer("insert into ").append(tableName).append(
				array2StrForInseSql(arrayCopy(pCols, vCols), ",")).append(
				"values").append(
				getInseSql_PV2(pCols.length, vCols.length, vVals)).toString();
	}

	/**
	 * 根据主键的值返回delete的sql语句 【用值形式】<br>
	 * <font color='red'>该语句会将满足条件的数据删除</font> <br>
	 * For Example:<br>
	 * <code><font color='blue'> "delete from realTable(表名) where pk
	 * in(inValues[0],.....)</font></code>
	 * 
	 * @param realTable
	 *            表名
	 * @param pk
	 *            主键列
	 * @param inValues
	 *            主键值数组
	 * @return
	 */
	public static String getDelSqlWith_InValue(String realTable, String pk,
			String[] inValues) {
		return new StringBuffer("delete from ").append(realTable).append(
				" where ").append(pk).append(" in(").append(
				getArrayValueTogether(inValues)).append(")").toString();
	}

	/**
	 * 根据主键的值返回delete的sql语句 【用参数形式】 <font color='red'>该语句会将满足条件的数据删除</font>
	 * <br>
	 * For Example：<br>
	 * <code><font color='blue'> "delete from realTable(表名) where pkColumn
	 * in(?,?,.....)</font></code>
	 * 
	 * @param inValues
	 *            主键值数组
	 * @return
	 */
	public static String getDelSqlWith_InParam(String realTable,
			String pkColumn, int length) {
		return new StringBuffer().append("delete from ").append(realTable)
				.append(" where ").append(pkColumn).append(" in ").append(
						getInseSqlParams(length)).toString();
	}

	/**
	 * 返回条件信息 【用值形式】 For Example:<br>
	 * <code><font color='blue'>select outputValues[0] ... from tableName<br>
	 * where columns[0]=values[0]....  </font></code>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            输出的字段数组 <font color='blue'>为"*"时为全部字段</font>
	 * @return
	 */
	public static String getQuerySqlUseValue(String tableName,
			String[] columns, String[] values, String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereSqlUseValue(columns, values)).toString();
	}

	/**
	 * 返回条件信息 【用参数形式】 For Example:<br>
	 * <code><font color='blue'>select outputValues[0] ... from tableName<br>
	 * where columns[0]=? ....  </font></code>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            <font color='blue'>为"*"时为全部字段</font>
	 * @return
	 */
	public static String getQuerySqlUseParam(String tableName,
			String[] columns, String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereSqlUseParam(columns)).toString();
	}

	/**
	 * 返回条件信息（这些信息为不同的） 【用值形式】 <code>DISTINCT</code><br>
	 * For Example:<br>
	 * <code><font color='blue'>select distinct outputValues[0] ... <br>
	 * from tableName where columns[0]=values[0]....  </font></code>
	 * 
	 * @param tableName
	 *            表或视图名
	 * @param columns
	 *            字段数组
	 * @param values
	 *            值数组
	 * @param outputValues
	 *            输出的字段数组 <font color='blue'>第一个元素值为"*"时为全部字段</font>
	 * @return
	 */
	public static String getDistinctQuerySql(String tableName,
			String[] columns, String[] values, String[] outputValues) {
		return new StringBuffer(getQuerySqlUseValue(tableName, columns, values,
				outputValues)).toString().replace("select", "select distinct");
	}

	/**
	 * 返回条件信息 【用值形式】 <br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 *select ... from tableName where lkCols[0] like '%lkVals[0]%' and ... 
	 *<code></font>
	 * @param tableName
	 * @param lkCols
	 * @param lkVals
	 * @param outputValues
	 * @return
	 */
	public static String getQueryLkSqlUseValue(String tableName,
			String[] lkCols, String[] lkVals, String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereLkSqlUseValue(lkCols, lkVals)).toString();
	}

	/**
	 * 返回条件信息 <br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 *select ..... from tableName where lkCols[0] like '%?%' and ... </font></code>
	 * 
	 * @param tableName
	 * @param lkCols
	 * @param outputValues
	 * @return
	 */
	public static String getQueryLkSqlUseParam(String tableName,
			String[] lkCols, String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereLkSqlUseParam(lkCols)).toString();
	}

	/**
	 * 返回条件信息 有相等的数据，也有相像的数据进行匹配查询 【用值形式】 <br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 *select ..... from tableName where eqCols[0]=eqVals[0]... <br>
	 *and lkCols[0] like '%lkVals[0]%' and ... </font></code>
	 * 
	 * @param tableName
	 * @param eqCols
	 * @param eqVals
	 * @param lkCols
	 * @param lkVals
	 * @param outputValues
	 *            <font color='blue'>为"*"时为全部字段</font>
	 * @return
	 */
	public static String getQueryEqLkSqlUseValue(String tableName,
			String[] eqCols, String[] eqVals, String[] lkCols, String[] lkVals,
			String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereSqlEqLkUseValue(eqCols, eqVals, lkCols, lkVals))
				.toString();
	}

	/**
	 * 返回条件信息 有相等的数据，也有相像的数据进行匹配查询【用参数形式】 <br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 * select ..... from tableName where eqCols[0]=?... <br>
	 * and lkCols[0] like '%?%' and ... </font></code>
	 * 
	 * @param tableName
	 * @param eqCols
	 * @param lkCols
	 * @param outputValues
	 * @return
	 */
	public static String getQueryEqLkSqlUseParam(String tableName,
			String[] eqCols, String[] lkCols, String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereSqlEqLkUseParam(eqCols, lkCols)).toString();
	}

	/**
	 * <font color='red'>三级审核 <code>
	 * <b>xxsh  /  xysh   / fdysh <font color='blue'>学校审核//学院审核//辅导员审核</font></b></code><br>
	 * 注意在返回的字段中 outputValues 要把主键的列或（联合主键列）放在首位，这样有利用在jsp页面提取主键值<br>
	 * 由于会根据不同的审核状态返回不同的颜色，在最终的返回的数据中颜色会放在第一位，主键放在第二位 根据shlb和sqsj排序显示<br>
	 * <font color='blue'>For Example: 输出结果为
	 * bgcolor，outputValues[0],outputValues[1]....<font></font><br>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            为*号时返回全部字段
	 * @param shlb
	 * @return
	 */
	public static String get_SH3_CondiSql(String tableName, String[] columns,
			String[] values, String[] outputValues, String shlb) {
		StringBuffer sb = new StringBuffer();
		sb.append(get_SH_CondiSql(tableName, columns, values, outputValues,
				shlb));
		// 过滤记录
		if (shlb.equals("xxsh")) {
			sb.append(" and xysh= '通过' and fdysh='通过'"); // xy fdy pass
		} else if (shlb.equals("xysh")) {
			sb.append(" and fdysh='通过'"); // fdy pass
		}
		sb.append(" order by").append(shlb).append(",sqsj");
		return sb.toString();
	}

	/**
	 * <font color='red'>二级审核
	 * <code><b>xxsh/xysh <font color='blue'>学校审核//学院审核</font></b></code><br>
	 * 注意在返回的字段中 outputValues 要把主键的列或（联合主键列）放在首位，这样有利用在jsp页面提取主键值<br>
	 * 由于会根据不同的审核状态返回不同的颜色，在最终的返回的数据中颜色会放在第一位，主键放在第二位 根据shlb和sqsj排序显示<br>
	 * <font color='blue'>For Example: 输出结果为
	 * bgcolor，outputValues[0],outputValues[1]....<font></font><br>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            为*号时返回全部字段
	 * @param shlb
	 * @return
	 */
	public static String get_SH2_CondiSql(String tableName, String[] columns,
			String[] values, String[] outputValues, String shlb) {
		StringBuffer sb = new StringBuffer();
		sb.append(get_SH_CondiSql(tableName, columns, values, outputValues,
				shlb));
		if (shlb.equals("xxsh")) {
			sb.append(" and xysh= '通过' "); // xy fdy pass
		}
		sb.append(" order by ").append(shlb).append(",sqsj");
		return sb.toString();
	}

	/**
	 * 返回两张表的等值查询的sql语句<br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 * select a.xm,a.ksh,a.nj,b.xh from liang a,kang b where 1=1 <br>
	 * and a.xm=b.xm and a.ksh=b.xh</font></code>
	 * 
	 * @param tables
	 *            表的名字 Array
	 * @param compareColumns
	 *            要比较的字段名字 Array
	 * @param outputValues
	 *            要查询的字段名字 Array
	 * @return
	 * @throws Exception
	 */
	public static String getJoinTwoTabSql(String[] tables,
			String[][] compareColumns, String[][] outputValues)
			throws Exception {
		String[] proxy = { "a", "b" };
		StringBuffer sb = new StringBuffer("select ");
		for (int i = 0; i < 2; i++) {
			sb.append(getQueryUseTableProxy(proxy[i], outputValues[i]));
			sb.append(i == 0 ? "," : "");
		}
		sb.append(" from ");
		for (int k = 0; k < 2; k++) {
			sb.append(tables[k]).append(" ").append(proxy[k]);
			sb.append(k == 0 ? "," : "");
		}
		sb.append(" where 1=1 ");
		if (compareColumns[0].length != compareColumns[1].length) {
			throw new Exception(
					"the compareColumns[0].length is not the same as compareColumns[1].length");
		}
		for (int j = 0; j < compareColumns[0].length; j++) {
			sb.append(" and ").append(proxy[0]).append(".").append(
					compareColumns[0][j]).append("=").append(proxy[1]).append(
					".").append(compareColumns[1][j]);
		}
		return sb.toString();
	}

	/**
	 * 将输出一系列的delete的语句<br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 * delete from realTable(表名) where <br>
	 * column = splitStr.split([splitType/'-'])[0];
	 * </font></code>
	 * 
	 * @param realTable
	 *            表名
	 * @param pkColumn
	 *            删除根据的字段
	 * @param splitStr
	 *            要分割的字符串
	 * @param splitType
	 *            分割方式 default '-'
	 * @return
	 */
	public static String[] getBatchDelSqlUseValue(String realTable,
			String pkColumn, String splitStr, String splitType) {
		StringBuffer sbPfefix = new StringBuffer("delete from ").append(
				realTable).append(" where ").append(pkColumn).append("='");
		return getCUADEndWith(sbPfefix, splitStr, splitType);
	}

	/**
	 * 将输出一系列的update的语句<br>
	 * For Example:更新一条的sql语句<br>
	 * <code><font color='blue'>
	 * update realTable(表名) set upCols[0] = upVals[0],...<br> 
	 * where pkColumn = splitStr.split([splitType/'-'])[0]
	 * </font></code>
	 * 
	 * @param realTable
	 *            表名
	 * @param pkColumn
	 *            更新根据的字段
	 * @param splitStr
	 *            要分割的字符串[保存的pk字段的值]
	 * @param splitType
	 *            分割方式 default '-'
	 * @return
	 */
	public static String[] getBatchUpdateSqlUseValue(String realTable,
			String pkColumn, String splitStr, String splitType,
			String[] upCols, String[] upVals) {
		String tempType = splitType;
		if (StringUtils.isNull(tempType)) { // default type
			tempType = "-";
		}
		String[] pkValues = splitStr.split(tempType);
		String[] sqlArray = new String[pkValues.length];
		for (int i = 0; i < pkValues.length; i++) {
			sqlArray[i] = getUpdateSqlByPKValue(realTable, pkColumn,
					pkValues[i], upCols, upVals);
		}
		return sqlArray;
	}

	/**
	 * 返回update的sql的前缀<br>
	 * <code><font color='blue'>update realTable set columns[0] = values[0],...
	 * <br> where pkColumn = pkValue
	 * </font></code>
	 * 
	 * @param realTable
	 * @param pkColumn
	 * @param pkValue
	 * @param upCols
	 * @param upVals
	 * @return
	 */
	private static String getUpdateSqlByPKValue(String realTable,
			String pkColumn, String pkValue, String[] upCols, String[] upVals) {
		StringBuffer sb = new StringBuffer().append(getUpdatePrefixSqlVal(
				realTable, upCols, upVals));
		return sb.append(" where ").append(pkColumn).append(" ='").append(
				pkValue).append("'").toString();
	}

	/**
	 * 返回update的sql的前缀<br>
	 * <code><font color='blue'>update realTable set columns[0] = values[0],...
	 * </font></code>
	 * 
	 * @param realTable
	 * @param columns
	 * @param values
	 * @return
	 */
	private static String getUpdatePrefixSqlVal(String realTable,
			String[] columns, String[] values) {
		StringBuffer sb = new StringBuffer("update ").append(realTable).append(
				" set ");
		for (int i = 0; i < columns.length; i++) {
			sb.append(columns[i]).append("='").append(values[i]).append("'")
					.append(i == columns.length - 1 ? "" : ",");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param sbPfefix
	 * @param splitStr
	 * @param splitType
	 * @return
	 */
	private static String[] getCUADEndWith(StringBuffer sbPfefix,
			String splitStr, String splitType) {
		StringBuffer sbEndWith = new StringBuffer();
		List<String> resArraySql = new ArrayList<String>();
		String tempType = splitType;
		if (StringUtils.isNull(tempType)) { // default type
			tempType = "-";
		}
		String[] tempStr = splitStr.split(tempType);
		for (int i = 0; i < tempStr.length; i++) {
			sbEndWith.append(sbPfefix).append(tempStr[i]).append("'");
			resArraySql.add(sbEndWith.toString());
			sbEndWith.delete(0, sbEndWith.length()); // clear sb
		}
		return chgList2Array(resArraySql);
	}

	/**
	 * 返回查询的sql语句<br>
	 * For Example：<br>
	 * <code><font color='blue'>
	 * proxy.queryColumns[0],proxy.queryColumns[1]....</font></code>
	 * 
	 * @param proxy
	 *            表的代理名字
	 * @param queryColumns
	 *            要查询的字段名
	 * @return
	 */
	private static String getQueryUseTableProxy(String proxy,
			String[] queryColumns) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < queryColumns.length; i++) {
			sb.append(proxy).append(".").append(queryColumns[i]);
			sb.append(i == queryColumns.length - 1 ? "" : ",");
		}
		return sb.toString();
	}

	/**
	 * 返回where子句 用参数代替值<br>
	 * For Example : <font color='red'>and columns[0]=? and columns[1]=?...</font>
	 * 
	 * @param columns
	 * @return
	 */
	public static String getWhereSqlUseParam(String[] columns) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columns.length; i++) {
			sb.append(" and ").append(columns[i]).append("=?");
		}
		return sb.toString();
	}

	/**
	 * 返回where子句<br>
	 * For Example : <font color='red'>and columns[0]=values[0] and
	 * columns[1]=values[1]... <br>
	 * 如果传入的values[i]的值为空，那么直接跳过，不对此句进行sql语句的拼接 </font>
	 * 
	 * @param columns
	 * @param values
	 * @return
	 */
	public static String getWhereSqlUseValue(String[] columns, String[] values) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columns.length; i++) {
			if (StringUtils.isNotNull(values[i])) {
				sb.append(" and ").append(columns[i]).append("='").append(
						values[i]).append("'");
			}
		}
		return sb.toString();
	}

	/**
	 * 返回where子句<br>
	 * For Example : <font color='red'>and eqCols[0]=eqVals[0] and
	 * eqCols[1]=eqVals[1]... and lkCols[0] like '%lkVals[0]%' and lkCols[1]
	 * like '%lkVals[1]%'... <br>
	 * 如果传入的eqVals(lkVals)[i]的值为空，那么直接跳过，不对此句进行sql语句的拼接 </font>
	 * 
	 * @param eqCols
	 *            判断相等的字段
	 * @param eqVals
	 *            判断相等的值
	 * @param lkCols
	 *            判断相像的字段
	 * @param lkVals
	 *            判断相像的值
	 * @return
	 */
	private static String getWhereSqlEqLkUseValue(String[] eqCols,
			String[] eqVals, String[] lkCols, String[] lkVals) {
		StringBuffer sb = new StringBuffer();
		sb.append(getWhereSqlUseValue(eqCols, eqVals)).append(
				getWhereLkSqlUseValue(lkCols, lkVals));
		return sb.toString();
	}

	/**
	 * 返回where子句<br>
	 * For Example : <font color='red'> and lkCols[0] like '%lkVals[0]%' and
	 * lkCols[1] like '%lkVals[1]%'... <br>
	 * 如果传入的eqVals(lkVals)[i]的值为空，那么直接跳过，不对此句进行sql语句的拼接
	 * 
	 * @param lkCols
	 * @param lkVals
	 * @return
	 */
	private static String getWhereLkSqlUseValue(String[] lkCols, String[] lkVals) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lkCols.length; i++) {
			if (StringUtils.isNotNull(lkVals[i])) {
				sb.append(" and ").append(lkCols[i]).append(" like '%").append(
						lkVals[i]).append("%'");
			}
		}
		return sb.toString();
	}

	/**
	 * 返回where子句<br>
	 * For Example : <font color='red'> and lkCols[0] like '%?%' and lkCols[1]
	 * like '%?%'...
	 * 
	 * @param lkCols
	 * @param lkVals
	 * @return
	 */

	private static String getWhereLkSqlUseParam(String[] lkCols) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lkCols.length; i++) {
			sb.append(" and ").append(lkCols[i]).append(" like '%?%'");
		}
		return sb.toString();
	}

	/**
	 * 返回where子句<br>
	 * For Example : <br>
	 * <font color='red'>and eqCols[0]=? and eqCols[1]=?... and lkCols[0] like
	 * '%?%' and lkCols[1] like '%?%'... <br>
	 * <font color='blue'>注意:其他方法在使用该方法的返回的sql语句时，<br>
	 * 如果在传真正的值的时候, 对于相等的值一定不能为空，否则返回的结果是空的</font> </font>
	 * 
	 * @param eqCols
	 *            判断相等的字段
	 * @param lkCols
	 *            判断相像的字段
	 * @return
	 */
	private static String getWhereSqlEqLkUseParam(String[] eqCols,
			String[] lkCols) {
		StringBuffer sb = new StringBuffer();
		sb.append(getWhereSqlUseParam(eqCols)).append(
				getWhereLkSqlUseParam(lkCols));
		return sb.toString();
	}

	private static String getUpdatePrefixSql(String realTable, String[] columns) {
		StringBuffer sb = new StringBuffer("update ").append(realTable).append(
				" set ");
		for (int i = 0; i < columns.length; i++) {
			sb.append(columns[i])
					.append(i == columns.length - 1 ? "=?" : "=?,");
		}
		return sb.toString();
	}

	/** ############## PRIVATE METHOD ###########################s*/
	/**
	 * 返回审核的条件sql 即根据用户的级别返回比该用户的审核都为 通过 的sql条件
	 * 
	 * @param shColumns
	 *            总的审核身份列表
	 * @param shlb
	 *            要查询的审核的列表
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getShConstraint(String[] shColumns, String shlb) {
		StringBuffer sb = new StringBuffer("");
		int index = 0;
		for (int i = 0; i < shColumns.length; i++) {
			if (shColumns[i].equals(shlb)) {
				index = i;
			}
		}
		for (int j = 0; j < index; j++) {
			sb.append(" and ").append(shColumns[j]).append("='通过'");
		}
		return sb.toString();
	}

	/** ********************************************************************************** */

	/**
	 * <font color='red'>该方法是传参的方式，要保证参数的Value是都要有的，不然得不到正确的数据</font> <br>
	 * For Example: <br>
	 * <font color='blue'> select count(1) count from TABLENAME(表)where 1=1 and
	 * columns[0]=? and columns[1]=?...</font>
	 * 
	 * @param tableName
	 * @param columns
	 *            列表
	 * @param mode
	 *            模式 <暂时没有用>
	 * @return
	 */
	public static String getCountSqlUseParam(String tableName, String[] columns,
			int mode) {
		StringBuffer sb = new StringBuffer("select count(1) count from ")
				.append(tableName).append(" where 1=1 ");
		for (int i = 0; i < columns.length; i++) {
			sb.append(" and ").append(columns[i]).append("=?");
		}
		return sb.toString();
	}

	private static String get_SH_CondiSql(String tableName, String[] columns,
			String[] values, String[] outputValues, String shlb) {
		StringBuffer sb = new StringBuffer("select ").append(
				getColourQuerySql(shlb)).append(
				(outputValues[0].equals("*")) ? "a.*" : array2StrNoScope(
						outputValues, ",")).append(" from ").append(tableName)
				.append(" a where 1=1 ");
		sb.append(getWhereSqlUseValue(columns, values));
		return sb.toString();
	}

	/**
	 * 根据shlb这个字段的值选取不同的值
	 * 
	 * @param shlb
	 *            以该字段为标准//审核类别【通过，未审核，不通过】
	 * @return
	 */
	private static String getColourQuerySql(String shlb) {
		return new StringBuffer().append("(case nvl(").append(shlb).append(
				",'未审核')").append(" when '通过' then '#99CCFF' when '未审核' then ")
				.append("'#FFFFFF' else '#FF9999' end) bgcolor,").toString();
	}

	/**
	 * 返回sql语句的前缀 For Example： select
	 * 
	 * @param tableName
	 * @param outputValues
	 * @return
	 */
	private static String getQueryPrefixSql(String tableName,
			String[] outputValues) {
		return new StringBuffer("select ").append(
				(outputValues[0].equals("*")) ? "*" : array2StrNoScope(
						outputValues, ",")).append(" from ").append(tableName)
				.append(" where 1=1 ").toString();
	}

	/**
	 * 将数组转换为String 去掉"(" 和 ")" For Example：<font color='red'><br>
	 * 如果参入的columns为[1,2,3] split的值为","，那么返回的数据是 <code>"1,2,3"的字符串形式</code>,依次类推</font>
	 * 
	 * @param columns
	 * @param split
	 * @return
	 */
	private static String array2StrNoScope(String[] columns, String split) {
		return array2StrForInseSql(columns, split).replace("(", "").replace(
				")", "").trim();
	}

	/**
	 * 把数组转换成为String,用于插入数据 For Example：<font color='red'><br>
	 * 如果参入的columns为[1,2,3] split的值为","，那么返回的数据是 <code>"(1,2,3)"的字符串形式</code>,依次类推</font>
	 * 
	 * @param columns
	 *            传入的字段名
	 * @param split
	 *            分割方式
	 * @return
	 */
	private static String array2StrForInseSql(String[] columns, String split) {
		StringBuffer result = new StringBuffer("(");
		for (int i = 0; i < columns.length; i++) {
			result.append((i == columns.length - 1) ? columns[i] : columns[i]
					.toString()
					+ split);
		}
		return result.append(")").toString();
	}

	/**
	 * For Example：<font color='red'><br>
	 * 如果参入的inValues为[1,2,3]，<br>
	 * 那么返回的数据是 <code>"'1','2','3'"的字符串形式</code>,如果有值为空，不将值加上。依次类推</font>
	 * 
	 * @param inValues
	 *            值列表
	 * @return
	 */
	private static String getArrayValueTogether(String[] inValues) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inValues.length; i++) {
			if (StringUtils.isNotNull(inValues[i])) {
				sb.append("'").append(inValues[i]).append("'").append(
						i == inValues.length - 1 ? "" : ",");
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param inValues
	 * @return
	 */
	private static String getArrayValueTogetherAll(String[] inValues) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < inValues.length; i++) {
			sb.append("'").append(inValues[i]).append("'").append(
					i == inValues.length - 1 ? "" : ",");
		}
		return sb.append(")").toString();
	}

	/**
	 * 返回插入数据的？号个数 参数形式 For Example：<font color='red'>如果参入的length为3，<br>
	 * 那么返回的数据是 <code>(?,?,?)</code>,依次类推</font>
	 * 
	 * @param length
	 * @return
	 */
	private static String getInseSqlParams(int length) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < length; i++) {
			sb.append((i < length - 1) ? "?," : "?");
		}
		return sb.append(")").toString();
	}

	/**
	 * For Example:<br>
	 * <code><font color='blue'>
	 * (?,?,?,value)
	 * </font></code>
	 * 
	 * @param length
	 * @param value
	 *            传值
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getInseSql_PV(int length, String value) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < length; i++) {
			sb.append("?,");
		}
		sb.append("'").append(value).append("'");
		return sb.append(")").toString();
	}

	/**
	 * For Example:<br>
	 * <code><font color='blue'>
	 * (?,?,?,valValues[0],valValues[1]...)
	 * </font></code>
	 * 
	 * @param length
	 *            传参的长度
	 * @param valColumns
	 *            传值的字段数组
	 * @param valValues
	 *            传值的值数组
	 * @return
	 */
	private static String getInseSql_PV2(int length, int valColLen,
			String[] valValues) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < length; i++) {
			sb.append("?,");
		}
		for (int j = 0; j < valColLen; j++) {
			sb.append("'").append(valValues[j] + "'");
			sb.append(j < valColLen - 1 ? "," : "");
		}
		return sb.append(")").toString();
	}

	/**
	 * 将要增加的数组 addColumns 加到原来数组 columns 的最前端
	 * 
	 * @param columns
	 *            总的数组
	 * @param removeColumns
	 *            要增加的元素组
	 * @return
	 * @throws Exception
	 */
	private static String[] arrayCopy(String[] columns, String[] addColumns) {
		if (addColumns != null) {
			if (checkArrayHaveNullElem(addColumns)) {
				try {
					throw new Exception("要加的数组addColumns存在空的字符串");
				} catch (Exception e) {
				}
			}
			List<String> list = chgArray2List(columns);
			int size = list.size();
			for (int i = 0; i < addColumns.length; i++) {
				list.add(size + i, addColumns[i]);
			}
			return chgList2Array(list);
		} else {
			return columns;
		}
	}

	/** 检验数组中是否有空的字符串，即StringUtils.isNull(s)返回true */
	private static boolean checkArrayHaveNullElem(String[] array) {
		for (String s : array) {
			if (StringUtils.isNull(s)) {
				return true;
			}
		}
		return false;
	}

	private static List<String> chgArray2List(String[] columns) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < columns.length; i++) {
			list.add(columns[i]);
		}
		return list;
	}

	private static String[] chgList2Array(List<String> list) {
		String[] array = new String[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/** Main Method */
	public static void main(String[] args) {
		/*
		 * System.out
		 * .println(SQL_Util.get_SH3_CondiSql("view_xszz_ynys_gjlzjxj", new
		 * String[] {}, new String[] {}, new String[] { "*" }, "xxsh"));
		 */

		/*
		 * String[] tables = new String[] { "liang", "kang" }; String[][]
		 * compareColumns = new String[][] { new String[] { "xm", "ksh" }, new
		 * String[] { "xm", "xh" } }; String[][] outputValues = new String[][] {
		 * new String[] { "xm", "ksh", "nj" }, new String[] { "xh" } }; try {
		 * System.out.println(getJoinTwoTabSql(tables, compareColumns,
		 * outputValues)); } catch (Exception e) { // TODO 自动生成 catch 块
		 * e.printStackTrace(); }
		 */

		String[] otherColumns = new String[] { "liang", "kang", "TRT", "fgss" };
		String[] vCols = new String[] { "twet", "twet", "te"};
		String[] otherValues = new String[] { "liangVal", "kangVal", "TRTVal",
				"fgssVal" };
		String tableName = "LC";
		System.out.println(SQL_Util.getInseSqlUsePaVa(tableName, otherColumns, vCols, otherValues));

		// String splitStr =
		// "gertert-tret-retert-ret-63-rtyer-4564-rtewrt-454-ert";
		// String[] upCols = new String[] { "gg", "ff" };
		// String[] upVals = new String[] { "111", "222" };
		// String pkColumn = "kang";
		// String realTable = "Lc";
		// String[] rs = SQL_Util.getBatchUpdateSqlUseValue(realTable, pkColumn,
		// splitStr, null, upCols, upVals);
		// for (String s : rs) {
		// System.out.println(s);
		// }
	}

}
