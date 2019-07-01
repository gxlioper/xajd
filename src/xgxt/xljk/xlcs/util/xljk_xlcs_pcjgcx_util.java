package xgxt.xljk.xlcs.util;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.xlcs.form.xilk_zxpc_form;

public class xljk_xlcs_pcjgcx_util {

	DAO mydao = DAO.getInstance();
	StandardOperation myop = new StandardOperation();
	New_Random_ID newId = new New_Random_ID();
	lrh_commen_util commen_util = new lrh_commen_util();
	HttpServletRequest request;
	
	public void xljk_xlcs_pcjgcx_util1(HttpServletRequest request) {
		this.request = request;
	}

	// 根据表中其他字段值查询结果，传入的参数是其他字段的字段名，字段值，表名和主键，返回的是集合
	public List<HashMap<String, String>> Find_By_OtherKey(String tableName,
			String usersql, String[] otherKeys, String[] otherKeyValues) {
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}
		List<HashMap<String, String>> lii = mydao.getList(strbuf.toString(),
				otherKeyValues, tabTitle);
		return lii;
	}

	public List<HashMap<String, String>> Find_By_OtherKey3(String tableName,
			String usersql, String[] otherKeys, String[] otherKeyValues,
			String[] like, String[] likeVal, String[] order) {

		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		String s1 = "!!SplitSignOne!!";
		StringBuffer val = new StringBuffer();
		for (int i = 0; i < otherKeyValues.length; i++) {
			val.append(otherKeyValues[i] + s1);
		}
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());

		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}

		for (int i = 0; i < like.length; i++) {
			int a = likeVal[i].indexOf("%");
			if (a == -1) {
				str = " and " + like[i] + " like ?";
				strbuf.append(str);
				val.append(likeVal[i] + s1);
			} else {
				str = " and " + like[i] + " like '" + likeVal[i] + "'";
				strbuf.append(str);
			}
		}
		String[] values = val.toString().split(s1);
		List<HashMap<String, String>> lii = mydao.getList(strbuf.toString(),
				values, tabTitle);
		return lii;
	}

	public List Find_By_OtherKey5(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues, String[] like,
			String[] likeVal, String[] order) {

		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		String s1 = "!!SplitSignOne!!";
		StringBuffer val = new StringBuffer();
		for (int i = 0; i < otherKeyValues.length; i++) {
			val.append(otherKeyValues[i] + s1);
		}
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());

		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}

		for (int i = 0; i < like.length; i++) {
			int a = likeVal[i].indexOf("%");
			if (a == -1) {
				str = " and " + like[i] + " like ?";
				strbuf.append(str);
				val.append(likeVal[i] + s1);
			} else {
				str = " and " + like[i] + " like '" + likeVal[i] + "'";
				strbuf.append(str);
			}
		}
		List lii = mydao.getList(strbuf.toString(), otherKeyValues, tabTitle);
		return lii;
	}

	public List Find_By_OtherKey2(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues) {
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (usersql != null && !usersql.equalsIgnoreCase("")) {
			String[] temp = usersql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		for (int i = 0; i < otherKeys.length; i++) {
			str = " and " + otherKeys[i] + " =?";
			strbuf.append(str);
		}
		List<Vector<String>> lii = mydao.getVectorList(strbuf.toString(),
				otherKeyValues, tabTitle);
		return lii;
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

	/**
	 * @category 无条件查询,查询结果通过userSql定义,并且需要输入表名<font color=red>
	 *           userSql一定要按规范写,并且需要有关键字from </font>
	 * @return
	 */
	public List<HashMap<String, String>> Find_All(String userSql,
			String tableName) {
		List<HashMap<String, String>> li = null;
		StringBuffer strbuf = new StringBuffer();
		String sql = "";
		if (userSql != null && !userSql.equalsIgnoreCase("")) {
			String[] temp = userSql.split("from");
			sql = temp[0];
		} else {
			sql = "select * ";
		}
		strbuf.append(sql);
		String str = "";
		str = " from " + tableName + " a  where 1 = 1 ";
		strbuf.append(str);
		String[] tabTitle = mydao.getColumnName(strbuf.toString());
		li = mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
		return li;
	}

	public List pcjgcx_getXztjList() {
		List li = null;
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from";
		String[] otherKeys = { "ss" };
		String[] otherKeyValues = { "xztj" };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		return li;
	}

	public List pcjgcx_getSjList() {
		List li = null;
		String tableName = "sjb";
		String userSql = "select a.sjlsh,a.sjm from";
		li = this.Find_All(userSql, tableName);
		return li;
	}

	public List pcjgcx_cjjlb_search(xilk_zxpc_form myform) {
		List<HashMap<String,String>> li = null;
		String sjlsh = myform.getSjlsh();
		String dtrq = myform.getDtrq();
		String xztj = myform.getXztj();
		String fz = myform.getFz();
		String xh = myform.getXh();
		StringBuffer sql = new StringBuffer();
		String s1 = "!!SplitSignOne!!";
		StringBuffer val = new StringBuffer();
		sql
				.append("select a.XN_ID,a.XH,a.DTSJ,a.DTYS,a.SJLSH,a.CJ,a.BZ from cjjlb a where 1=1 ");
		if (sjlsh != null && !sjlsh.equalsIgnoreCase("")) {
			sql.append("and sjlsh = ? ");
			val.append(sjlsh + s1);
		}
		if (dtrq != null && !dtrq.equalsIgnoreCase("")) {
			String[] rq = dtrq.split("-");
			dtrq = rq[0] + rq[1] + rq[2];
			sql.append(" and dtsj like '" + dtrq + "%' ");
		}
		if (xztj != null && !xztj.equalsIgnoreCase("")) {
			if (xztj.equalsIgnoreCase("001")) {
				sql.append(" and cj = ? ");
			} else if (xztj.equalsIgnoreCase("002")) {
				sql.append(" and cj > ? ");
			} else if (xztj.equalsIgnoreCase("003")) {
				sql.append(" and cj < ? ");
			} else if (xztj.equalsIgnoreCase("004")) {
				sql.append(" and cj >= ? ");
			} else if (xztj.equalsIgnoreCase("005")) {
				sql.append(" and cj <= ? ");
			}
			val.append(fz + s1);
		}
		if (xh != null && !xh.equalsIgnoreCase("")) {
			sql.append(" and xh = ? ");
			val.append(xh + s1);
		}

		String[] outputValue = { "XN_ID", "XH", "DTSJ", "SJLSH", "CJ", "BZ",
				"DTYS" };
		if (val.toString().equals("")) {
			String[] in = {};
			li = mydao.getList(sql.toString(), in, outputValue);
		} else {
			String[] inputValue = val.toString().split(s1);
			li = mydao.getList(sql.toString(), inputValue, outputValue);
		}
		for(HashMap<String,String> map : li){
			if(StringUtils.isNotNull(map.get("DTSJ"))){
				map.put("DTSJ", mydao.doForTime(map.get("DTSJ")));
			}
		}
		return li;
	}

	public xilk_zxpc_form pcjgcx_cjjlb_view(xilk_zxpc_form myform) {
		String cjjlb_xnid = myform.getCjjlb_xnid();
		String tableName = "cjjlb";
		String usersql = "select a.XN_ID,a.XH,a.DTSJ,a.DTYS,a.SJLSH,a.CJ,a.BZ from";
		String[] otherKeys = { "XN_ID" };
		String[] otherKeyValues = { cjjlb_xnid };
		List<HashMap<String, String>> li = this.Find_By_OtherKey(tableName,
				usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			myform.setCjjlb_xnid(cjjlb_xnid);
			myform.setXh(map.get("XH"));
			myform.setDtsj(mydao.doForTime(map.get("DTSJ")));
			myform.setDtys(map.get("DTYS"));
			myform.setSjlsh(map.get("SJLSH"));
			myform.setCj(map.get("CJ"));
			myform.setBz(map.get("BZ"));
		}
		return myform;
	}

	public List pcjgcx_dtjlb_search(xilk_zxpc_form myform) {
		List li = null;
		xilk_zxpc_form cjjlform = new xilk_zxpc_form();
		cjjlform = this.pcjgcx_cjjlb_view(myform);
		String sjlsh = cjjlform.getSjlsh();
		String xh = cjjlform.getXh();
		String dtsj = cjjlform.getDtsj();
		String tableName = "view_xljk_xlcs_dtljb";
		String usersql = "select a.XH,a.DTSJ,a.SJLSH,a.STLSH,a.DTDA,a.DTFZ,a.XN_ID,a.STXH,a.SSLXMC from ";
		String[] otherKeys = { "XH", "SJLSH" };
		String[] otherKeyValues = { xh, sjlsh };
		String[] likev = { "DTSJ" };
		String[] likeVal = { dtsj + "%" };
		String[] order = {};
		li = this.Find_By_OtherKey3(tableName, usersql, otherKeys,
				otherKeyValues, likev, likeVal, order);
		return li;
	}

	public List pcjgcx_dtjl_tjsearch(xilk_zxpc_form myform) {
		List li = null;
		String sjlsh = myform.getSjlsh();
		String xh = myform.getXh();
		String dtsj = myform.getDtsj();
		String stlsh = myform.getStlsh();
		String stxh = myform.getStxh();
		String sslxdm = myform.getSslxdm();
		dtsj = dtsj.substring(0, 8);
		String tableName = "view_xljk_xlcs_dtljb";
		String usersql = "select a.XH,a.DTSJ,a.SJLSH,a.STLSH,a.DTDA,a.DTFZ,a.XN_ID,a.STXH,a.SSLXMC from ";
		StringBuffer othK = new StringBuffer();
		StringBuffer othKV = new StringBuffer();
		String s1 = "!!SplitSignOne!!";
		othK.append("XH" + s1);
		othKV.append(xh + s1);
		othK.append("SJLSH" + s1);
		othKV.append(sjlsh + s1);

		if (stlsh != null && !stlsh.equalsIgnoreCase("")) {
			othK.append("STLSH" + s1);
			othKV.append(stlsh + s1);
		}
		if (stxh != null && !stxh.equalsIgnoreCase("")) {
			othK.append("STXH" + s1);
			othKV.append(stxh + s1);
		}
		if (sslxdm != null && !sslxdm.equalsIgnoreCase("")) {
			othK.append("SSLXDM" + s1);
			othKV.append(sslxdm + s1);
		}
		String[] otherKeys = othK.toString().split(s1);
		String[] otherKeyValues = othKV.toString().split(s1);
		String[] likev = { "DTSJ" };
		String[] likeVal = { dtsj + "%" };
		String[] order = {};
		li = this.Find_By_OtherKey3(tableName, usersql, otherKeys,
				otherKeyValues, likev, likeVal, order);
		return li;
	}

	public List pcjgcx_stb_search(xilk_zxpc_form myform) {
		List li = null;
		String sjlsh = myform.getSjlsh();
		String tableName = "view_xljk_xlcs_sjstb";
		String usersql = "select a.stlsh from";
		String[] otherKey = { "sjlsh" };
		String[] otherKeyV = { sjlsh };
		li = this.Find_By_OtherKey(tableName, usersql, otherKey, otherKeyV);
		return li;
	}

	public List pcjgcx_stxh_search(xilk_zxpc_form myform) {
		List li = null;
		String sjlsh = myform.getSjlsh();
		String tableName = "view_xljk_xlcs_sjstb";
		String usersql = "select a.stxh from";
		String[] otherKey = { "sjlsh" };
		String[] otherKeyV = { sjlsh };
		li = this.Find_By_OtherKey(tableName, usersql, otherKey, otherKeyV);
		return li;
	}

	public List<HashMap<String, String>> pcjgcx_get_sslbList() {
		List<HashMap<String, String>> li = null;
		String userSql = "select a.SSLXDM,a.SSLXMC from";
		String tableName = "xljk_stsslbdmb";
		li = this.Find_All(userSql, tableName);
		return li;
	}

	public String xljk_dmwhb_dmzh(String dmh) {
		List<HashMap<String, String>> li = null;
		String dmmc = "";
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from";
		String[] otherKeys = { "DMH" };
		String[] otherKeyValues = { dmh };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			dmmc = map.get("DMMC");
		}
		return dmmc;
	}

	public String xljk_dmwhb_dmzh2(String dmmc, String ss) {
		List<HashMap<String, String>> li = null;
		String dmh = "";
		String tableName = "xljk_dmwhb";
		String usersql = "select a.DMH,a.DMMC from";
		String[] otherKeys = { "DMMC", "SS" };
		String[] otherKeyValues = { dmmc, ss };
		li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			dmh = map.get("DMH");
		}
		return dmh;
	}

	public List pcjgcx_csfx(xilk_zxpc_form myform) {
		// a.XH,a.DTSJ,a.SJLSH,a.STLSH,a.DTDA,a.DTFZ,a.XN_ID,a.STXH,a.SSLXMC
		List<HashMap<String, String>> li = null;
		xilk_zxpc_form cjjlform = new xilk_zxpc_form();
		cjjlform = this.pcjgcx_cjjlb_view(myform);
		String sjlsh = cjjlform.getSjlsh();
		String xh = cjjlform.getXh();
		String dtsj = cjjlform.getDtsj();
		List<HashMap<String, String>> stssList = this.pcjgcx_get_sslbList();
		String tableName = "view_xljk_xlcs_dtljb";
		String usersql = "select a.XH,a.DTSJ,a.SJLSH,a.STLSH,a.DTDA,a.DTFZ,a.XN_ID,a.STXH,a.SSLXMC,a.SSLXDM from ";
		String stsslxdm = "";
		String xn_id = "";
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = stssList.size();
		for (int i = 0; i < tempLen; i++) {
			map = stssList.get(i);
			stsslxdm = map.get("SSLXDM");
			String[] otherKeys = { "XH", "SJLSH", "SSLXDM" };
			String[] otherKeyValues = { xh, sjlsh, stsslxdm };
			String[] likev = { "DTSJ" };
			String[] likeVal = { dtsj + "%" };
			String[] order = {};
			li = this.Find_By_OtherKey3(tableName, usersql, otherKeys,
					otherKeyValues, likev, likeVal, order);

			int fs = 0;
			HashMap<String, String> map2 = new HashMap<String, String>();
			int tempLen2 = li.size();
			if (0 != tempLen2) {
				for (int j = 0; j < tempLen2; j++) {
					map2 = li.get(j);
					fs = fs + Integer.parseInt(map2.get("DTFZ"));
				}
				xn_id = newId.new_xnid("xljk_csfx");
				String[] columns = { "XN_ID", "XH", "SJLSH", "SSLXDM", "DF",
						"RQ" };
				String[] values = { xn_id, xh, sjlsh, stsslxdm,
						String.valueOf(fs), dtsj };
				StandardOperation.insert("XLJK_ZXPC_CSFX", columns, values,
						this.request);
			}
		}
		int fs = 0;
		String sql = "select a.XH,a.DTSJ,a.SJLSH,a.STLSH,a.DTDA,a.DTFZ,a.XN_ID,a.STXH,a.SSLXMC,a.SSLXDM from view_xljk_xlcs_dtljb a where 1=1 and XH=? and SJLSH=? and DTSJ like '%"
				+ dtsj + "%'and SSLXDM is null ";
		String[] in = { xh, sjlsh };
		String[] out = { "DTFZ" };
		li = mydao.getList(sql, in, out);
		HashMap<String, String> map2 = new HashMap<String, String>();
		int tempLen2 = li.size();
		if (0 != tempLen2) {
			for (int j = 0; j < tempLen2; j++) {
				map2 = li.get(j);
				fs = fs + Integer.parseInt(map2.get("DTFZ"));
			}
			xn_id = newId.new_xnid("xljk_csfx");
			String[] columns = { "XN_ID", "XH", "SJLSH", "SSLXDM", "DF", "RQ" };
			String[] values = { xn_id, xh, sjlsh, "", String.valueOf(fs), dtsj };
			StandardOperation.insert("XLJK_ZXPC_CSFX", columns, values,
					this.request);
		}

		tableName = "VIEW_XLJK_CSFX";
		usersql = "select a.XN_ID,a.XH,a.SJLSH,a.SSLXDM,a.DF,a.SSLXMC from";
		String[] otherKeys = { "XH", "SJLSH" };
		String[] otherKeyValues = { xh, sjlsh };
		String[] likev = { "RQ" };
		String[] likeVal = { dtsj + "%" };
		String[] order = {};
		li = this.Find_By_OtherKey3(tableName, usersql, otherKeys,
				otherKeyValues, likev, likeVal, order);
		return li;
	}

	public List pcjgcx_csfx2(xilk_zxpc_form myform) {
		List li = null;
		xilk_zxpc_form cjjlform = new xilk_zxpc_form();
		cjjlform = this.pcjgcx_cjjlb_view(myform);
		String sjlsh = cjjlform.getSjlsh();
		String xh = cjjlform.getXh();
		String dtsj = cjjlform.getDtsj();
		String tableName = "VIEW_XLJK_CSFX";
		String usersql = "select a.XN_ID,a.XH,a.SJLSH,a.SSLXDM,a.DF,a.SSLXMC from";
		String[] otherKeys = { "XH", "SJLSH" };
		String[] otherKeyValues = { xh, sjlsh };
		String[] likev = { "RQ" };
		String[] likeVal = { dtsj + "%" };
		String[] order = {};
		li = this.Find_By_OtherKey3(tableName, usersql, otherKeys,
				otherKeyValues, likev, likeVal, order);
		if (0 == li.size()) {
			li = this.pcjgcx_csfx(myform);
		}
		return li;
	}

	public List xlkhxsxx_pcjgcx(xilk_zxpc_form myform) {
		List li = null;
		String xh = myform.getXh();
		String tableName = "cjjlb";
		String usersql = "select a.XN_ID,a.XH,a.DTSJ,a.DTYS,a.SJLSH,a.CJ,a.BZ from";
		String[] otherKeys = { "XH" };
		String[] otherKeyValues = { xh };
		li = commen_util.Find_By_OtherKey(tableName, usersql, otherKeys,
				otherKeyValues);
		return li;
	}

	public String judgeTj(String tj) {
		if (tj.equals("1")) {
			return "=";
		} else if (tj.equals("2")) {
			return ">";
		} else if (tj.equals("3")) {
			return "<";
		} else if (tj.equals("4")) {
			return ">=";
		} else if (tj.equals("5")) {
			return "<=";
		} else {
			return "<>";
		}
	}

	// 心理健康试卷
	public List<HashMap<String, String>> getOkStuList(String yz, String tj,
			String df) {
		String sql = null;
		String[] outputValue = new String[] { "xh", "xb", "nl", "zf", "zjf",
				"yxzztksp" };
		if (yz == null || yz.equalsIgnoreCase("")) {
			sql = "select * from view_xljkzptj";
		} else {
			sql = "select * from view_xljkzptj where to_number(" + yz + ")"
					+ judgeTj(tj) + "to_number('" + df + "')";
		}
		// 得到满足的学生名单
		return mydao.getList(sql, new String[] {}, outputValue);
	}

	public List<HashMap<String, String>> getConGtOneStuList(String sql) {
		String[] outputValue = new String[] { "xh", "xb", "nl", "zf", "zjf",
				"yxzztksp" };
		sql = "select * from view_xljkzptj" + sql;
		return mydao.getList(sql, new String[] {}, outputValue);
	}

	// 根据学号得到学生的详细信息
	public String[] getMoreStuInfo(String xh, String[] output) {
		String sql = "select distinct * from view_xljkzptj where xh=?";
		return mydao.getOneRs(sql, new String[] { xh }, output);
	}

	// 根据条件返回sql语句
	public String getSqlByCondi(String yz, String tj, String df) {
		String sql = null;
		if (yz == null || yz.equalsIgnoreCase("")) {
			sql = " where 1=1 ";
		} else {
			sql = " where to_number(" + yz + ")" + judgeTj(tj) + "to_number('"
					+ df + "')";
		}
		return sql;
	}

	// 根据以后的条件返回sql的有关语句
	public String getOtherSql(String yz, String tj, String df) {
		String sql = null;
		if (yz == null || yz.equalsIgnoreCase("")) {
			sql = " and 1=1 ";
		} else {
			sql = " and to_number(" + yz + ")" + judgeTj(tj) + "to_number('"
					+ df + "')";
		}
		return sql;
	}

	// 根据条件--因子
	public String getAllSqlByYz(String yz, String tj, String df) {
		if (yz == null || yz.equalsIgnoreCase("")) {
			return " and 1=1 ";
		} else {
			return " and to_number(" + yz + ")" + judgeTj(tj) + "to_number('"
					+ df + "')";
		}
	}

	// 根据条件--题号
	public String getAllSqlByExam(String th, String tj, String thdf) {
		if (th == null || th.equalsIgnoreCase("")) {
			return " and 1=1 ";
		} else {
			return " and (th = " + th + " and df " + judgeTj(tj) + thdf + ")";
		}
	}

	//get ok students by yz
	public List<HashMap<String, String>> getOkStuByYz(String tempSql) {
		String sql = "select rownum r ,a.* from zcyscjzbfb a " + tempSql;
		String[] outputValue = new String[] { "r", "xh", "a", "b", "c", "e",
				"f", "g", "h", "i", "l", "m", "n", "o", "q1", "q2", "q3", "q4" };
		return mydao.getList(sql, new String[] {}, outputValue);
	}

	//get Ok students by exam
	public List<HashMap<String, String>> getOkStuByExam(String tempSql) {
		String sql = "select rownum r,a.* from zcystmdfb a " + tempSql;
		String[] outputValue = new String[] { "r", "xh", "th", "yx", "df" };
		return mydao.getList(sql, new String[] {}, outputValue);
	}
}
