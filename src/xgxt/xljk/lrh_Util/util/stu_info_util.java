package xgxt.xljk.lrh_Util.util;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;
import xgxt.xljk.lrh_Util.model.stu_info_model;
import xgxt.xljk.xlxh.Form.xljk_xlxhhy_from;

public class stu_info_util {

	public DAO mydao = DAO.getInstance();

	private static final String[] nullArray = {};

	public stu_info_model stu_find_byxh(String xh) throws Exception {
		stu_info_model stu_info = new stu_info_model();
		String tableName = "VIEW_XSJBXX";
		String[] outputValues = new String[] { "xh", "xm", "xb", "nj",
				"xydm", "xymc", "xz", "zydm", "zymc", "bjdm", "bjmc",
				"dqszj", "xy", "bmdm", "xzb", "ssbh", "qsdh", "lxdzxx",
				"lxdh", "sjhm", "mm", "bz" };
		outputValues = FormUtils.chgArrayElem2UpperCase(outputValues);
		String[] lkCols = { "XH" };
		String[] lkVals = { xh };
		String sql = SQL_Util.getQueryLkSqlUseValue(tableName, lkCols, lkVals,
				outputValues);
		String[] values = mydao.getOneRs(sql, nullArray, outputValues);
		FormUtils.setProperties(outputValues, values, stu_info);
		return stu_info;
	}

	public List stus_find_by(xljk_xlxhhy_from myform) throws Exception {
		StringBuilder sb=new StringBuilder();
		String tableName = "view_xsjbxx";		
		String[] outputValues = new String[] { "xh", "xm", "xymc", "zymc",
				"bjmc" };
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, myform);
		//辅导员用户
		if("true".equalsIgnoreCase(myform.getIsFdy())){
			sb.append("  and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+myform.getUserName()+"' )  ");
		}
		
		return CommonQueryDAO.commonQuery(tableName,myQuery.getQueryString()+sb,
				myQuery.getInputList(), outputValues, "", myform);
	}

	// 根据表中其他字段值查询结果，传入的参数是其他字段的字段名，字段值，表名和主键，返回的是集合
	// usersql用法,写你想要查询的结果字段,一定要写到from,后面可以不写,如果为空,那么就是select *
	// 例如 String uesesql="select a.xh,a.xm,a.xb,a.zy from ";
	public List Find_By_OtherKey(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues) {
		StringBuffer sb = new StringBuffer();
		String prefixSql = this.getQueryPrefix(tableName, usersql);
		String[] tabTitle = mydao
				.getColumnName(prefixSql.replace("1=1", "1=2"));
		sb.append(prefixSql).append(
				SQL_Util.getWhereSqlUseValue(otherKeys, otherKeyValues));
		return mydao.getList(sb.toString(), nullArray, tabTitle);
	}

	public List Find_By_OtherKey2(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues) {
		StringBuffer sb = new StringBuffer();
		String prefixSql = this.getQueryPrefix(tableName, usersql);
		String[] tabTitle = mydao
				.getColumnName(prefixSql.replace("1=1", "1=2"));
		sb.append(prefixSql).append(
				SQL_Util.getWhereSqlUseValue(otherKeys, otherKeyValues));
		return mydao.getVectorList(sb.toString(), otherKeyValues, tabTitle);
	}

	private String getQueryPrefix(String tableName, String usersql) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotNull(usersql)) {
			sb.append(usersql.split("from")[0]);
		} else {
			sb.append("select * ");
		}
		sb.append(" from " + tableName + " a  where 1=1 ");
		return sb.toString();

	}

	// 取表头，根据传入一个表名，和表中字段数组，取出表头
	public List Get_Table_Title(String tableName, String[] zdm) {
		String sql = "select rownum 行号, a.";
		String str = "";
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(sql);
		for (int i = 0; i < zdm.length; i++) {
			strbuf.append(zdm[i]);
			strbuf.append((i == zdm.length - 1 ? " " : ",a."));
		}
		str = " from " + tableName + " a ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		String[] tabTitleCN = mydao.getColumnNameCN(tabTitle, tableName);
		List titles = mydao.arrayToList(tabTitle, tabTitleCN);
		return titles;
	}

	// mzdmb
	public void mz_to_mzdm() throws Exception {
		String sql = "select *  from mzdmb where mzdm between '01' and '56' ";
		List<HashMap<String, String>> li = mydao.getListNotOut(sql, nullArray);
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < li.size(); i++) {
			map = li.get(i);
			String mzdm = map.get("mzdm");
			String mzmc = map.get("mzmc");
			sql = "update xsxxb set mz=? where mz=?";
			mydao.runUpdate(sql, new String[] { mzdm, mzmc });
		}
	}

	public void update_tycjb() throws Exception {
		String sql = "select xh from cjb where kcsbm like '0110000%' and xq='2'";
		List<HashMap<String, String>> li = mydao.getListNotOut(sql, nullArray);
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		int flagc = 0;
		int false_count = 0;
		boolean flag = false;
		for (int i = 0; i < li.size(); i++) {
			map = li.get(i);
			String xh = map.get("xh");
			sql = "select xh,cj from cjb where xh='" + xh
					+ "' and kcsbm ='dxstzjkbz01'";
			map2 = mydao.getMapNotOut(sql, nullArray);
			if (0 != map2.size()) {
				flagc = flagc + 1;
				String cj = map2.get("cj");
				sql = "update tycjb set stszcpcj='" + cj + "' where xh='" + xh
						+ "' and xq='2'";
				flag = mydao.runUpdate(sql, nullArray);
				if (false == flag) {
					false_count = false_count + 1;
				}
			}
		}
	}
}
