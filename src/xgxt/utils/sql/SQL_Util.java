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
	 * <font color='red'> ֻ�Ƿ���һ��û���κν����sql��䣬��Ҫ���ڱ�ȡ�ر���ֶε�ע����Ϣ</font> <br>
	 * For Example: <code><font color='blue'>"select * from TABLENAME(����) where
	 * 1=2"</font></code>
	 * 
	 * @param tableName
	 *            ����
	 * @return
	 */
	public static String getNoResultSql(String tableName) {
		return new StringBuffer("select * from ").append(tableName).append(
				" where 1=2 ").toString();
	}

	/**
	 * <font color='red'> ֻ�Ƿ���һ��û���κν����sql��䣬��Ҫ���ڱ�ȡ�ر���ֶε�ע����Ϣ</font> <br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 * "select columns[0],columns[1]... from TABLENAME(����) where 1=2"
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
	 * ����ɾ������ֵ��Ӧ��sql��� ���ء���������sql��<br>
	 * For Example:<br>
	 * <code>
	 * <font color='blue'> "delete from TABLENAME(����) where pkColumn = ?"
	 * </font></code>
	 * 
	 * @param tableName
	 *            ����
	 * @param pk
	 *            ����
	 * @return
	 */
	public static String getDelSqlByPKParam(String tableName, String pkColumn) {
		return new StringBuffer("delete from ").append(tableName).append(
				" where ").append(pkColumn).append("=?").toString();
	}

	/**
	 * ����ɾ������ֵ��Ӧ��sql��� ���ء���ֵ��sql��<br>
	 * For Example:<br>
	 * <code>
	 * <font color='blue'> "delete from TABLENAME(����) where pkColumn = pkValue"
	 * </font></code>
	 * 
	 * @param tableName
	 *            ����
	 * @param pk
	 *            ����
	 * @return
	 */
	public static String getDelSqlByPKValue(String tableName, String pkColumn,
			String pkValue) {
		return new StringBuffer("delete from ").append(tableName).append(
				" where ").append(pkColumn).append("='").append(pkValue)
				.append("'").toString();
	}

	/**
	 * ����������ֵ����select��sql��䡾��������sql�� <font color='red'><br>
	 * �����Ὣ���һ����¼��ȫ���ֶβ��</font> <br>
	 * For Example:
	 * <code><font color='blue'> "select * from TABLENAME(����) where PK = ?"
	 * </font></code>
	 * 
	 * @param tableName
	 *            ��������ͼ��
	 * @param pk
	 *            ������
	 * @return
	 */
	public static String getQuerySqlByPKParam(String tableName, String pkColumn) {
		return new StringBuffer("select * from ").append(tableName).append(
				" where ").append(pkColumn).append("=?").toString();
	}

	/**
	 * ����������ֵ����select��sql��䡾��ֵ��sql�� <font color='red'><br>
	 * �����Ὣ���һ����¼��ȫ���ֶβ��</font> <br>
	 * For Example:
	 * <code><font color='blue'> "select * from TABLENAME(����) where PK = pkValue"
	 * </font></code>
	 * 
	 * @param tableName
	 *            ��������ͼ��
	 * @param pk
	 *            ������
	 * @return
	 */
	public static String getQuerySqlByPKValue(String tableName,
			String pkColumn, String pkValue) {
		return new StringBuffer("select * from ").append(tableName).append(
				" where ").append(pkColumn).append("='").append(pkValue)
				.append("'").toString();
	}

	/**
	 * ����������ֵ����update��sql��䡾pkValueΪֵ��columns��Ӧ��ֵΪ������ʽ��<br>
	 * <font color='red'>�����Ὣ���һ����¼����ѡ�ֶθ���</font> <br>
	 * For Example: <br>
	 * <code><font color='blue'> "update realTable(����) set
	 * columns[0]=?,columns[1]=? ... where pk=pkValue"</font></code>
	 * 
	 * @param columns
	 *            �ֶ��б�
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
	 * ����������ֵ����update��sql��䡾pkValue��columns��Ӧ��ֵΪ������ʽ��<br>
	 * <font color='red'>�����Ὣ���һ����¼����ѡ�ֶθ���</font> <br>
	 * For Example: <br>
	 * <code><font color='blue'> "update realTable(����) set
	 * columns[0]=?,columns[1]=? ... where pk=?"</font></code>
	 * 
	 * @param columns
	 *            �ֶ��б�
	 * @return
	 */
	public static String getUpdateSqlPKParam(String realTable, String pk,
			String[] columns) {
		StringBuffer sb = new StringBuffer().append(getUpdatePrefixSql(
				realTable, columns));
		return sb.append(" where ").append(pk).append("=?").toString();
	}

	/**
	 * ����insert�� sql��� ���ò�����ʽ�� <code>For Example:<br><font color='blue'>
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
	 * ����insert�� sql��� ����ֵ��ʽ�� <code>For Example:<br><font color='blue'>
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
	 * ����һ��insert��sql��䣬������а���һ��sequence<br>
	 * <code><font color='blue'>
	 * insert into tableName(otherColumns[0],otherColumns[1]...,seqColumn)<br>
	 * values(?,?,?,seqName.nextval)
	 * </font></code>
	 * 
	 * @param tableName
	 *            ����
	 * @param otherColumns
	 *            ���ε��ֶ�����
	 * @param seqName
	 *            sequence������
	 * @param seqColumn
	 *            sequence��Ӧ�ĵض�����
	 * @return
	 */
	public static String getInseSeqSqlUseParam(String tableName,
			String[] otherColumns, String seqName, String seqColumn) {
		return SQL_Util.getInsertSqlUseParam(tableName,
				arrayCopy(otherColumns, new String[] { seqColumn })).replace(
				"?)", seqName + ".nextval)");
	}

	/**
	 * ����һ��insert��sql��䣬������а���һ��sequence<br>
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
	 * ����һ��insert��sql��䣬������а���һ��sequence<br>
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
	 * ����������ֵ����delete��sql��� ����ֵ��ʽ��<br>
	 * <font color='red'>�����Ὣ��������������ɾ��</font> <br>
	 * For Example:<br>
	 * <code><font color='blue'> "delete from realTable(����) where pk
	 * in(inValues[0],.....)</font></code>
	 * 
	 * @param realTable
	 *            ����
	 * @param pk
	 *            ������
	 * @param inValues
	 *            ����ֵ����
	 * @return
	 */
	public static String getDelSqlWith_InValue(String realTable, String pk,
			String[] inValues) {
		return new StringBuffer("delete from ").append(realTable).append(
				" where ").append(pk).append(" in(").append(
				getArrayValueTogether(inValues)).append(")").toString();
	}

	/**
	 * ����������ֵ����delete��sql��� ���ò�����ʽ�� <font color='red'>�����Ὣ��������������ɾ��</font>
	 * <br>
	 * For Example��<br>
	 * <code><font color='blue'> "delete from realTable(����) where pkColumn
	 * in(?,?,.....)</font></code>
	 * 
	 * @param inValues
	 *            ����ֵ����
	 * @return
	 */
	public static String getDelSqlWith_InParam(String realTable,
			String pkColumn, int length) {
		return new StringBuffer().append("delete from ").append(realTable)
				.append(" where ").append(pkColumn).append(" in ").append(
						getInseSqlParams(length)).toString();
	}

	/**
	 * ����������Ϣ ����ֵ��ʽ�� For Example:<br>
	 * <code><font color='blue'>select outputValues[0] ... from tableName<br>
	 * where columns[0]=values[0]....  </font></code>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            ������ֶ����� <font color='blue'>Ϊ"*"ʱΪȫ���ֶ�</font>
	 * @return
	 */
	public static String getQuerySqlUseValue(String tableName,
			String[] columns, String[] values, String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereSqlUseValue(columns, values)).toString();
	}

	/**
	 * ����������Ϣ ���ò�����ʽ�� For Example:<br>
	 * <code><font color='blue'>select outputValues[0] ... from tableName<br>
	 * where columns[0]=? ....  </font></code>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            <font color='blue'>Ϊ"*"ʱΪȫ���ֶ�</font>
	 * @return
	 */
	public static String getQuerySqlUseParam(String tableName,
			String[] columns, String[] outputValues) {
		return new StringBuffer().append(
				getQueryPrefixSql(tableName, outputValues)).append(
				getWhereSqlUseParam(columns)).toString();
	}

	/**
	 * ����������Ϣ����Щ��ϢΪ��ͬ�ģ� ����ֵ��ʽ�� <code>DISTINCT</code><br>
	 * For Example:<br>
	 * <code><font color='blue'>select distinct outputValues[0] ... <br>
	 * from tableName where columns[0]=values[0]....  </font></code>
	 * 
	 * @param tableName
	 *            �����ͼ��
	 * @param columns
	 *            �ֶ�����
	 * @param values
	 *            ֵ����
	 * @param outputValues
	 *            ������ֶ����� <font color='blue'>��һ��Ԫ��ֵΪ"*"ʱΪȫ���ֶ�</font>
	 * @return
	 */
	public static String getDistinctQuerySql(String tableName,
			String[] columns, String[] values, String[] outputValues) {
		return new StringBuffer(getQuerySqlUseValue(tableName, columns, values,
				outputValues)).toString().replace("select", "select distinct");
	}

	/**
	 * ����������Ϣ ����ֵ��ʽ�� <br>
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
	 * ����������Ϣ <br>
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
	 * ����������Ϣ ����ȵ����ݣ�Ҳ����������ݽ���ƥ���ѯ ����ֵ��ʽ�� <br>
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
	 *            <font color='blue'>Ϊ"*"ʱΪȫ���ֶ�</font>
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
	 * ����������Ϣ ����ȵ����ݣ�Ҳ����������ݽ���ƥ���ѯ���ò�����ʽ�� <br>
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
	 * <font color='red'>������� <code>
	 * <b>xxsh  /  xysh   / fdysh <font color='blue'>ѧУ���//ѧԺ���//����Ա���</font></b></code><br>
	 * ע���ڷ��ص��ֶ��� outputValues Ҫ���������л����������У�������λ��������������jspҳ����ȡ����ֵ<br>
	 * ���ڻ���ݲ�ͬ�����״̬���ز�ͬ����ɫ�������յķ��ص���������ɫ����ڵ�һλ���������ڵڶ�λ ����shlb��sqsj������ʾ<br>
	 * <font color='blue'>For Example: ������Ϊ
	 * bgcolor��outputValues[0],outputValues[1]....<font></font><br>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            Ϊ*��ʱ����ȫ���ֶ�
	 * @param shlb
	 * @return
	 */
	public static String get_SH3_CondiSql(String tableName, String[] columns,
			String[] values, String[] outputValues, String shlb) {
		StringBuffer sb = new StringBuffer();
		sb.append(get_SH_CondiSql(tableName, columns, values, outputValues,
				shlb));
		// ���˼�¼
		if (shlb.equals("xxsh")) {
			sb.append(" and xysh= 'ͨ��' and fdysh='ͨ��'"); // xy fdy pass
		} else if (shlb.equals("xysh")) {
			sb.append(" and fdysh='ͨ��'"); // fdy pass
		}
		sb.append(" order by").append(shlb).append(",sqsj");
		return sb.toString();
	}

	/**
	 * <font color='red'>�������
	 * <code><b>xxsh/xysh <font color='blue'>ѧУ���//ѧԺ���</font></b></code><br>
	 * ע���ڷ��ص��ֶ��� outputValues Ҫ���������л����������У�������λ��������������jspҳ����ȡ����ֵ<br>
	 * ���ڻ���ݲ�ͬ�����״̬���ز�ͬ����ɫ�������յķ��ص���������ɫ����ڵ�һλ���������ڵڶ�λ ����shlb��sqsj������ʾ<br>
	 * <font color='blue'>For Example: ������Ϊ
	 * bgcolor��outputValues[0],outputValues[1]....<font></font><br>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 *            Ϊ*��ʱ����ȫ���ֶ�
	 * @param shlb
	 * @return
	 */
	public static String get_SH2_CondiSql(String tableName, String[] columns,
			String[] values, String[] outputValues, String shlb) {
		StringBuffer sb = new StringBuffer();
		sb.append(get_SH_CondiSql(tableName, columns, values, outputValues,
				shlb));
		if (shlb.equals("xxsh")) {
			sb.append(" and xysh= 'ͨ��' "); // xy fdy pass
		}
		sb.append(" order by ").append(shlb).append(",sqsj");
		return sb.toString();
	}

	/**
	 * �������ű�ĵ�ֵ��ѯ��sql���<br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 * select a.xm,a.ksh,a.nj,b.xh from liang a,kang b where 1=1 <br>
	 * and a.xm=b.xm and a.ksh=b.xh</font></code>
	 * 
	 * @param tables
	 *            ������� Array
	 * @param compareColumns
	 *            Ҫ�Ƚϵ��ֶ����� Array
	 * @param outputValues
	 *            Ҫ��ѯ���ֶ����� Array
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
	 * �����һϵ�е�delete�����<br>
	 * For Example:<br>
	 * <code><font color='blue'>
	 * delete from realTable(����) where <br>
	 * column = splitStr.split([splitType/'-'])[0];
	 * </font></code>
	 * 
	 * @param realTable
	 *            ����
	 * @param pkColumn
	 *            ɾ�����ݵ��ֶ�
	 * @param splitStr
	 *            Ҫ�ָ���ַ���
	 * @param splitType
	 *            �ָʽ default '-'
	 * @return
	 */
	public static String[] getBatchDelSqlUseValue(String realTable,
			String pkColumn, String splitStr, String splitType) {
		StringBuffer sbPfefix = new StringBuffer("delete from ").append(
				realTable).append(" where ").append(pkColumn).append("='");
		return getCUADEndWith(sbPfefix, splitStr, splitType);
	}

	/**
	 * �����һϵ�е�update�����<br>
	 * For Example:����һ����sql���<br>
	 * <code><font color='blue'>
	 * update realTable(����) set upCols[0] = upVals[0],...<br> 
	 * where pkColumn = splitStr.split([splitType/'-'])[0]
	 * </font></code>
	 * 
	 * @param realTable
	 *            ����
	 * @param pkColumn
	 *            ���¸��ݵ��ֶ�
	 * @param splitStr
	 *            Ҫ�ָ���ַ���[�����pk�ֶε�ֵ]
	 * @param splitType
	 *            �ָʽ default '-'
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
	 * ����update��sql��ǰ׺<br>
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
	 * ����update��sql��ǰ׺<br>
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
	 * ���ز�ѯ��sql���<br>
	 * For Example��<br>
	 * <code><font color='blue'>
	 * proxy.queryColumns[0],proxy.queryColumns[1]....</font></code>
	 * 
	 * @param proxy
	 *            ��Ĵ�������
	 * @param queryColumns
	 *            Ҫ��ѯ���ֶ���
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
	 * ����where�Ӿ� �ò�������ֵ<br>
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
	 * ����where�Ӿ�<br>
	 * For Example : <font color='red'>and columns[0]=values[0] and
	 * columns[1]=values[1]... <br>
	 * ��������values[i]��ֵΪ�գ���ôֱ�����������Դ˾����sql����ƴ�� </font>
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
	 * ����where�Ӿ�<br>
	 * For Example : <font color='red'>and eqCols[0]=eqVals[0] and
	 * eqCols[1]=eqVals[1]... and lkCols[0] like '%lkVals[0]%' and lkCols[1]
	 * like '%lkVals[1]%'... <br>
	 * ��������eqVals(lkVals)[i]��ֵΪ�գ���ôֱ�����������Դ˾����sql����ƴ�� </font>
	 * 
	 * @param eqCols
	 *            �ж���ȵ��ֶ�
	 * @param eqVals
	 *            �ж���ȵ�ֵ
	 * @param lkCols
	 *            �ж�������ֶ�
	 * @param lkVals
	 *            �ж������ֵ
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
	 * ����where�Ӿ�<br>
	 * For Example : <font color='red'> and lkCols[0] like '%lkVals[0]%' and
	 * lkCols[1] like '%lkVals[1]%'... <br>
	 * ��������eqVals(lkVals)[i]��ֵΪ�գ���ôֱ�����������Դ˾����sql����ƴ��
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
	 * ����where�Ӿ�<br>
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
	 * ����where�Ӿ�<br>
	 * For Example : <br>
	 * <font color='red'>and eqCols[0]=? and eqCols[1]=?... and lkCols[0] like
	 * '%?%' and lkCols[1] like '%?%'... <br>
	 * <font color='blue'>ע��:����������ʹ�ø÷����ķ��ص�sql���ʱ��<br>
	 * ����ڴ�������ֵ��ʱ��, ������ȵ�ֵһ������Ϊ�գ����򷵻صĽ���ǿյ�</font> </font>
	 * 
	 * @param eqCols
	 *            �ж���ȵ��ֶ�
	 * @param lkCols
	 *            �ж�������ֶ�
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
	 * ������˵�����sql �������û��ļ��𷵻رȸ��û�����˶�Ϊ ͨ�� ��sql����
	 * 
	 * @param shColumns
	 *            �ܵ��������б�
	 * @param shlb
	 *            Ҫ��ѯ����˵��б�
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
			sb.append(" and ").append(shColumns[j]).append("='ͨ��'");
		}
		return sb.toString();
	}

	/** ********************************************************************************** */

	/**
	 * <font color='red'>�÷����Ǵ��εķ�ʽ��Ҫ��֤������Value�Ƕ�Ҫ�еģ���Ȼ�ò�����ȷ������</font> <br>
	 * For Example: <br>
	 * <font color='blue'> select count(1) count from TABLENAME(��)where 1=1 and
	 * columns[0]=? and columns[1]=?...</font>
	 * 
	 * @param tableName
	 * @param columns
	 *            �б�
	 * @param mode
	 *            ģʽ <��ʱû����>
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
	 * ����shlb����ֶε�ֵѡȡ��ͬ��ֵ
	 * 
	 * @param shlb
	 *            �Ը��ֶ�Ϊ��׼//������ͨ����δ��ˣ���ͨ����
	 * @return
	 */
	private static String getColourQuerySql(String shlb) {
		return new StringBuffer().append("(case nvl(").append(shlb).append(
				",'δ���')").append(" when 'ͨ��' then '#99CCFF' when 'δ���' then ")
				.append("'#FFFFFF' else '#FF9999' end) bgcolor,").toString();
	}

	/**
	 * ����sql����ǰ׺ For Example�� select
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
	 * ������ת��ΪString ȥ��"(" �� ")" For Example��<font color='red'><br>
	 * ��������columnsΪ[1,2,3] split��ֵΪ","����ô���ص������� <code>"1,2,3"���ַ�����ʽ</code>,��������</font>
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
	 * ������ת����ΪString,���ڲ������� For Example��<font color='red'><br>
	 * ��������columnsΪ[1,2,3] split��ֵΪ","����ô���ص������� <code>"(1,2,3)"���ַ�����ʽ</code>,��������</font>
	 * 
	 * @param columns
	 *            ������ֶ���
	 * @param split
	 *            �ָʽ
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
	 * For Example��<font color='red'><br>
	 * ��������inValuesΪ[1,2,3]��<br>
	 * ��ô���ص������� <code>"'1','2','3'"���ַ�����ʽ</code>,�����ֵΪ�գ�����ֵ���ϡ���������</font>
	 * 
	 * @param inValues
	 *            ֵ�б�
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
	 * ���ز������ݵģ��Ÿ��� ������ʽ For Example��<font color='red'>��������lengthΪ3��<br>
	 * ��ô���ص������� <code>(?,?,?)</code>,��������</font>
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
	 *            ��ֵ
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
	 *            ���εĳ���
	 * @param valColumns
	 *            ��ֵ���ֶ�����
	 * @param valValues
	 *            ��ֵ��ֵ����
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
	 * ��Ҫ���ӵ����� addColumns �ӵ�ԭ������ columns ����ǰ��
	 * 
	 * @param columns
	 *            �ܵ�����
	 * @param removeColumns
	 *            Ҫ���ӵ�Ԫ����
	 * @return
	 * @throws Exception
	 */
	private static String[] arrayCopy(String[] columns, String[] addColumns) {
		if (addColumns != null) {
			if (checkArrayHaveNullElem(addColumns)) {
				try {
					throw new Exception("Ҫ�ӵ�����addColumns���ڿյ��ַ���");
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

	/** �����������Ƿ��пյ��ַ�������StringUtils.isNull(s)����true */
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
		 * outputValues)); } catch (Exception e) { // TODO �Զ����� catch ��
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
