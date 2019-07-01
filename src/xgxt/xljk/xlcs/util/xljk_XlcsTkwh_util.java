package xgxt.xljk.xlcs.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.xlcs.form.xilk_xlcsTkwh_form;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

 

public class xljk_XlcsTkwh_util {
	public DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	HttpServletRequest request;

	private static final String[] nullArray = {};

	public void xljk_XlcsTkwh_util1(final HttpServletRequest request) {
		this.request = request;
	}

	// ���ݱ��������ֶ�ֵ��ѯ���������Ĳ����������ֶε��ֶ������ֶ�ֵ�����������������ص��Ǽ���
	public List<HashMap<String, String>> Find_By_OtherKey(String tableName,
			String usersql, String[] otherKeys, String[] otherKeyValues) {
		StringBuffer strbuf = new StringBuffer();
		String sql = StringUtils.isNull(usersql) ? usersql.split("from")[0]
				: "select * ";
		strbuf.append(sql);
		strbuf.append(" from " + tableName + " a  where 1=1 ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString().replace(
				"1=1", "1=2"));
		strbuf.append(SQL_Util.getWhereSqlUseParam(otherKeys));
		List<HashMap<String, String>> lii = mydao.getList(strbuf.toString(),
				otherKeyValues, tabTitle);
		// ��ʱ�����ת��
		for (HashMap<String, String> map : lii) {
			if (StringUtils.isNotNull(map.get("JRSJ"))) {
				map.put("JRSJ", mydao.doForTime2(map.get("JRSJ")));
			}
		}
		return lii;
	}

	public List Find_By_OtherKey2(String tableName, String usersql,
			String[] otherKeys, String[] otherKeyValues) {
		StringBuffer strbuf = new StringBuffer();
		String sql = StringUtils.isNull(usersql) ? usersql.split("from")[0]
				: "select * ";
		strbuf.append(sql);
		strbuf.append(" from " + tableName + " a  where 1=1 ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString().replace(
				"1=1", "1=2"));
		strbuf.append(SQL_Util.getWhereSqlUseParam(otherKeys));
		return mydao.getVectorList(strbuf.toString(), otherKeyValues, tabTitle);
	}

	// ȡ��ͷ�����ݴ���һ���������ͱ����ֶ����飬ȡ����ͷ
	public List Get_Table_Title(String tableName, String[] zdm) {
		String sql = "select rownum �к�, a.";
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
		return mydao.arrayToList(tabTitle, tabTitleCN);
	}

	/**
	 * @category ��������ѯ,��ѯ���ͨ��userSql����,������Ҫ�������<font color=red>
	 *           userSqlһ��Ҫ���淶д,������Ҫ�йؼ���from </font>
	 * @return
	 */
	public List<HashMap<String, String>> Find_All(String userSql,
			String tableName) {
//		List<HashMap<String, String>> li = new ArrayList<HashMap<String, String>>();
		StringBuffer strbuf = new StringBuffer();
		String sql = StringUtils.isNull(userSql) ? userSql.split("from")[0]
				: "select * ";
		strbuf.append(sql);
		strbuf.append(" from " + tableName + " a ");
		String[] tabTitle = mydao.getColumnName(strbuf.toString().replace(
				"1=1", "1=2"));
		return mydao.getList(strbuf.toString(), new String[] {}, tabTitle);
	}

	/**
	 * @category �õ��Ծ���ˮ���б� <font color=red> </font>
	 * @return
	 */
	public List tkwh_getSjlshList() {
		String userSql = "select SJLSH,SJM from ";
		String tableName = "sjb";
		return this.Find_All(userSql, tableName);
	}

	/**
	 * @category �õ�������ˮ���б� <font color=red> </font>
	 * @return
	 */
	public List tkwh_getStlshList() {
		String userSql = "select stlsh from ";
		String tableName = "stb";
		return this.Find_All(userSql, tableName);
	}

	/**
	 * @category �õ������Ѷȼ����б� <font color=red> </font>
	 * @return
	 */
	public List tkwh_getStndjbbList() {
		String userSql = "select stndjbdm,stndjbmc from";
		String tableName = "stndjbdmb";
		return this.Find_All(userSql, tableName);
	}

	/**
	 * @category �õ����������б� <font color=red> </font>
	 * @return
	 */
	public List tkwh_getStlxList() {
		String userSql = "select STLXDM,STLXMC from";
		String tableName = "stlxdmb";
		return this.Find_All(userSql, tableName);
	}

	/**
	 * @category �õ�������������б� <font color=red> </font>
	 * @return
	 */
	public List tkwh_getStsslbList() {
		String userSql = "select SSLXDM,SSLXMC from";
		String tableName = "XLJK_STSSLBDMB";
		return this.Find_All(userSql, tableName);
	}

	/**
	 * @category �õ�ĳ���Ծ�������б�Ҫ�������Ծ���ˮ�� <font color=red> </font>
	 * @return
	 */
	public List tkwh_getstList(String sjlsh) {
		String sql = "select a.stlsh,rownum||'----'||(case when length(a.stnr)>12 then substr(a.stnr,0,12)||'...' else a.stnr end) stlshstxh "
				+ "from (select a.*,b.stnr from sjstb a,stb b where a.stlsh=b.stlsh and a.sjlsh=? order by to_number(a.stxh) asc) a";
		List stList = mydao.getList(sql, new String[] { sjlsh }, new String[] {
				"stlsh", "stlshstxh" });
		return stList;
	}

	/**
	 * �����Ծ���ˮ�ŷ���������ܺ���
	 */
	public String getstTotalNumBySjlsh(String sjlsh) {
		String sql = "select count(*) count from sjstb where sjlsh=?";
		String[] countArray = mydao.getOneRs(sql, new String[] { sjlsh },
				new String[] { "count" });
		return countArray == null ? "0" : countArray[0];
	}

	/**
	 * @category ��ѯ�Ծ�,���ݿ��ж�Ӧ��sjb <font color=red> </font>
	 * @return
	 */
	public List<HashMap<String, String>> tkwh_findBy_sql(
			xilk_xlcsTkwh_form myform) {
		String[] sjlsh = new String[] { myform.getSjlsh() };
		String tableName = "sjb";
		String[] columns = new String[] { "sjlsh" };
		String[] outputValues = new String[] { "sjlsh", "sjm", "sjsm",
				"sjxsbj", "sjxd", "jrsj" };
		List<HashMap<String, String>> rs = mydao.getList(SQL_Util
				.getQuerySqlUseValue(tableName, columns, sjlsh, outputValues),
				nullArray, outputValues);
		for (HashMap<String, String> map : rs) {
			map.put("jrsj", mydao.doForTime2(map.get("jrsj")));
		}
		return rs;
	}

	/**
	 * @category ��ѯ�Ծ�,���ݿ��ж�Ӧ��sjb,�����Ծ���ˮ�Ų�ѯ�Ծ�,�����������Ϣ����<font color=red>
	 *           ����������form </font>
	 * @return
	 */
	public xilk_xlcsTkwh_form tkwh_findBySjlsh(xilk_xlcsTkwh_form myform) {
		xilk_xlcsTkwh_form newform = new xilk_xlcsTkwh_form();
		String[] columns = new String[] { "sjlsh" };
		String[] values = new String[] { myform.getSjlsh() };
		String[] outputValues = new String[] { "sjm", "sjsm", "sjxsbj", "sjxd",
				"jrsj", "sjlsh","xlcsxmdm" };
		String tableName = "sjb";
		String[] rs = mydao.getOneRs(SQL_Util.getQuerySqlUseValue(tableName,
				columns, values, outputValues), nullArray, outputValues);
		FormUtils.setProperties(outputValues, rs, newform);
		return newform;
	}
	
	/**
	 * @category ���ݴ���Ĳ����õ���Ӧ�������б�ֵ<font color=red> ����������list </font>
	 * @return
	 */
	public List getCheckList(int type) {
		return mydao.getChkList(type);
	}
	/**
	 * @category ���ݴ���Ĳ����õ���Ӧ�������б�ֵ<font color=red> ����������list </font>
	 * @return
	 */
	public List getCsxmList() {
		String sql = "select xlcsxmdm dm,xlcsxmmc mc from xlcsxmdmb ";	
		return mydao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}

	/**
	 * @category �����Ծ�<font color=red> ����������String </font>
	 * @return
	 */
	public String tkwh_add_sj(xilk_xlcsTkwh_form myform) {
		String tableName = "sjb";
		String[] columns = new String[] { "sjm", "sjxd", "sjxsbj", "sjsm","xlcsxmdm" };
		boolean flag = StandardOperation.insert2(tableName, columns, FormUtils
				.getProperties(columns, myform), "SJLSH",
				"sequence_sjlsh.nextval", request);
		return flag == true ? "insert success" : "insert fail";
	}

	/**
	 * @category �޸��Ծ�<font color=red> ����������String </font>
	 * @return
	 */
	public String tkwh_modi_sj(xilk_xlcsTkwh_form myform) throws Exception {
		String tableName = "sjb";
		String sjlsh = myform.getSjlsh();
		String sjm = myform.getSjm();
		String sjxd = myform.getSjxd();
		String sjxsbj = myform.getSjxsbj();
		String sjsm = myform.getSjsm();
		String xlcsxmdm = myform.getXlcsxmdm();
		String[] columns = { "SJM", "SJSM", "SJXSBJ", "SJXD","xlcsxmdm" };
		String[] values = { sjm, sjsm, sjxsbj, sjxd ,xlcsxmdm };
		boolean flag = StandardOperation.update(tableName, columns, values,
				"SJLSH", sjlsh, this.request);
		return flag == true ? "update success" : "update fail";
	}

	/**
	 * @category ɾ���Ծ�<font color=red> ����������String </font>
	 * @return
	 */
	public String tkwh_del_sj(xilk_xlcsTkwh_form myform) {
		String sjlsh = myform.getSjlsh();
		boolean flag = false;
		try {
			String[] otherKey = { "SJLSH" };
			String[] otherKeyV = { sjlsh };
			flag = StandardOperation.delete2("sjstb", "XN_ID", otherKey,
					otherKeyV, this.request);
			flag = flag
					| StandardOperation.delete("sjb", "SJLSH", sjlsh,
							this.request);
			return flag == true ? "del success" : "del fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "del fail";
		}
	}

	/**
	 * @category ��������<font color=red> ����������String </font>
	 * @return
	 */
	public String tkwh_add_st(xilk_xlcsTkwh_form myform) throws Exception {
		String[] columns = new String[] { "stlxdm", "stda", "stfz", "stjffs",
				"stnr", "stndjbdm", "stxsbj", "stdajs" };
		String tableName = "stb";
		boolean flag = StandardOperation.insert2(tableName, columns, FormUtils
				.getProperties(columns, myform), "STLSH",
				"sequence_stlsh.nextval", request);
		return flag == true ? "insert success" : "insert fail";
	}

	/**
	 * @category �����ѯ,�����û�����Ĳ�ѯ�������в�ѯ,��صı���view_xljk_xlcs_st<font color=red>
	 *           ����������List </font>
	 * @return
	 */
	public List tkwh_search_st(xilk_xlcsTkwh_form myform) {
		String[] columns = new String[] { "stlxdm", "stndjbdm", "sslxdm" };
		String tableName = "VIEW_XLJK_XLCS_ST";
		StringBuffer sb = new StringBuffer();
		sb.append("select stlsh,(case when length(stnr)>5 then substr(stnr,1,5)||'...' else stnr end) stnr,stlxdm,");
		sb.append("stndjbdm,decode(stjffs,'0','��ѡ��','1','����','') stjffs,");
		sb.append("stfz,stda,stdajs,stxsbj,stndjbmc,stlxmc,");
		sb.append("sslxdm,sslxmc from ").append(tableName)
				.append(" where 1=1 ");
		String[] outputValues = new String[] { "stlsh", "stnr", "stlxdm",
				"stndjbdm", "stjffs", "stfz", "stda", "stdajs", "stxsbj",
				"stndjbmc", "stlxmc", "sslxdm", "sslxmc" };
		sb.append(SQL_Util.getWhereSqlUseValue(columns, FormUtils.getProperties(
						columns, myform)));
		if(!Base.isNull(myform.getStnr())){
			sb.append(" and stnr like ? ");
		}
		return mydao.getList(sb.toString(), Base.isNull(myform.getStnr()) ? nullArray : new String[]{"%"+myform.getStnr()+"%"}, outputValues);
	}

	public List<HashMap<String, String>> tkwh_search_st2(
			xilk_xlcsTkwh_form myform) {
		String[] columns = new String[] { "stlxdm", "stndjbdm", "sslxdm" };
		String[] outputValues = new String[] { "stlsh", "stnr", "stlxdm",
				"stndjbdm", "stjffs", "stfz", "stda", "stdajs", "stxsbj",
				"stndjbmc", "stlxmc", "sslxdm", "sslxmc" };
		String tableName = "VIEW_XLJK_XLCS_ST";
		myform.getPages().setPageSize(20);
		StringBuilder sb = new StringBuilder();
		StringBuilder whereSb = new StringBuilder(" from ").append(tableName)
				.append(" where 1=1 ").append(
						SQL_Util.getWhereSqlUseValue(columns, FormUtils
								.getProperties(columns, myform))).append(
						" and stxsbj = '��'");
		if(!Base.isNull(myform.getStnr())){
			whereSb.append(" and stnr like ? ");
		}
		sb.append("select r,a.* from(");
		sb.append("select stlsh ,(case when length(stnr)>10 then substr(stnr,0,10)||'...' else stnr end) stnr,stlxdm,").append(
				"stndjbdm,decode(stjffs,'0','��ѡ��','1','����','') stjffs,")
				.append("stfz, stda, stdajs, stxsbj,stndjbmc,stlxmc,sslxdm,")
				.append("sslxmc,rownum r ").append(whereSb).append(
						")a ");
		List<HashMap<String, String>> list = mydao.getList(sb.toString(),
				(Base.isNull(myform.getStnr()) ?nullArray:new String[]{"%"+myform.getStnr()+"%"}), outputValues);
				sb.append("where r<=");
						sb.append(
						myform.getPages().getStart()
								+ myform.getPages().getPageSize()).append(
						" and r>").append(myform.getPages().getStart());
		
		List<HashMap<String, String>> rs = mydao.getList(sb.toString(),
				(Base.isNull(myform.getStnr()) ?nullArray:new String[]{"%"+myform.getStnr()+"%"}), outputValues);
		//String countSql = "select count()"
		myform.getPages().setMaxRecord(
				Integer.parseInt((list == null) ? "0" : String
								.valueOf(list.size())));
		return rs;
	}

	/**
	 * �����ѯ,����������ˮ�Ų�ѯ������������������Ϣ,��صı���view_xljk_xlcs_st <font color=red>
	 * ����������xilk_xlcsTkwh_form </font>
	 * 
	 * @return
	 */
	public xilk_xlcsTkwh_form tkwh_view_st(xilk_xlcsTkwh_form myform) {
		String stlsh = myform.getStlsh();
		xilk_xlcsTkwh_form resForm = new xilk_xlcsTkwh_form();
		String tableName = "VIEW_XLJK_XLCS_ST";
		String usersql = "select a.STLSH ,a.STNR,a.STLXDM,a.STNDJBDM,"
				+ "decode(a.stjffs,'0','��ѡ��','1','����','') stjffs,a.STFZ, a.STDA,"
				+ " a.STDAJS, a.STXSBJ,a.STNDJBMC,a.STLXMC,a.SSLXDM,a.SSLXMC from "
				+ tableName + " a where 1=1 ";
		String[] columns = { "stlsh" };
		String[] values = { stlsh };
		String[] outputValues = new String[] { "stnr", "stlxdm", "stndjbdm",
				"stjffs", "stfz", "stda", "stdajs", "stxsbj", "stndjbmc",
				"stlxmc", "sslxdm", "sslxmc" };
		String[] rs = mydao.getOneRs(usersql
				+ SQL_Util.getWhereSqlUseValue(columns, values), nullArray,
				outputValues);
		FormUtils.setProperties(outputValues, rs, resForm);
		resForm.setStlsh(stlsh); // set primary key
		return resForm;
	}

	public xilk_xlcsTkwh_form tkwh_view_st2(xilk_xlcsTkwh_form myform) {
		xilk_xlcsTkwh_form resForm = new xilk_xlcsTkwh_form();
		String tableName = "VIEW_XLJK_XLCS_ST";
		String[] outputValues = new String[] { "stlsh", "stnr", "stlxdm",
				"stndjbdm", "stjffs", "stfz", "stda", "stdajs", "stxsbj",
				"stndjbmc", "stlxmc", "sslxdm", "sslxmc" };
		String[] otherKeys = { "stlsh" };
		String[] otherKeyValues = { myform.getStlsh() };
		String[] values = mydao.getOneRs(SQL_Util.getQuerySqlUseValue(
				tableName, otherKeys, otherKeyValues, outputValues), nullArray,
				outputValues);
		FormUtils.setProperties(outputValues, values, resForm);
		return resForm;
	}

	/**
	 * �޸�����<font color=red> ����������String </font>
	 * 
	 * @return
	 */
	public String tkwh_modi_st(xilk_xlcsTkwh_form myform) throws Exception {
		String[] columns = new String[] { "stlsh", "stlxdm", "stda", "stfz",
				"stjffs", "stnr", "stndjbdm", "stxsbj", "stdajs" };
		String tableName = "stb";
		String pk = "stlsh";
		boolean flag = mydao.runUpdate(SQL_Util.getUpdateSqlByPKValue(
				tableName, pk, myform.getStlsh(), columns), FormUtils
				.getProperties(columns, myform));
		return flag == true ? "update success" : "update fail";
	}

	/**
	 * ɾ������<font color=red> ����������String </font>
	 * 
	 * @return
	 */
	public String tkwh_del_st(xilk_xlcsTkwh_form myform) throws Exception {
		String stlsh = myform.getStlsh();
		String tableName = "";
		boolean flag = false;
		flag = StandardOperation.delete("xljk_stssb", "stlsh", stlsh,
				this.request);
		String[] otherKey1 = { "STLSH" };
		String[] otherKeyV1 = { stlsh };
		flag = StandardOperation.delete2("xxb", "xxlsh", otherKey1, otherKeyV1,
				this.request);
		tableName = "sjstb";
		String[] otherKey2 = { "STLSH" };
		String[] otherKeyV2 = { stlsh };
		flag = StandardOperation.delete2(tableName, "XN_ID", otherKey2,
				otherKeyV2, this.request);
		flag = StandardOperation.delete("stb", "stlsh", stlsh, this.request);
		return flag == true ? "del success" : "del fail";
	}

	/**
	 * ƥ�������������<font color=red> ����������String </font>
	 * 
	 * @return
	 */
	public String tkwh_stsslb_pipei(xilk_xlcsTkwh_form myform) throws Exception {
		boolean flag = false;
		String[] columns = new String[] { "stlsh", "sslxdm" };
		String tableName = "xljk_stssb";
		String pkCol = "stlsh";
		if (FormUtils.haveOneRecord(tableName, pkCol, myform.getStlsh())) {
			mydao.runUpdate(SQL_Util.getUpdateSqlByPKValue(tableName, pkCol,
					myform.getStlsh(), columns), FormUtils.getProperties(
					columns, myform));
		} else {
			String xn_id = newId.new_xnid(tableName);
			myform.setXn_id(xn_id);
			columns = new String[] { "xn_id", "stlsh", "sslxdm" };
			flag = mydao.insert(SQL_Util.getInsertSqlUseValue(tableName,
					columns, FormUtils.getProperties(columns, myform)),
					nullArray);
		}
		return flag == true ? "pipei success" : "pipei fail";
	}

	/**
	 * @category ��ѯ��������Ӧ��ѡ����Ϣ,�������������ˮ�ţ����������Ӧ������ˮ�ŵ�����ѡ����Ϣ<font color=red>
	 *           ����������List </font>
	 * @return
	 */
	public List tkwh_stb_xxxx(xilk_xlcsTkwh_form myform) {
		List li = null;
		String stlsh = myform.getStlsh();
		String tableName = "xxb";
		String usersql = "select a.XXLSH ,a.STLSH,a.XXXH,a.XXNR,a.XXFZ,a.XXXSBJ from";
		if (!StringUtils.isNull(stlsh)) {
			String[] otherKeys = { "STLSH" };
			String[] otherKeyValues = { stlsh };
			li = this.Find_By_OtherKey(tableName, usersql, otherKeys,
					otherKeyValues);
		} else {
			li = this.Find_All(usersql, tableName);
		}
		return li;
	}

	/**
	 * @category ��ѯ�Զ�Ӧѡ����ˮ�ŵ���ϸѡ����Ϣ���������ѡ����ˮ�ţ����������Ӧѡ����ˮ�ŵ���ϸ��Ϣ<font color=red>
	 *           ����������xilk_xlcsTkwh_form </font>
	 * @return
	 */
	public xilk_xlcsTkwh_form tkwh_stb_xxxxxx(xilk_xlcsTkwh_form myform) {
		xilk_xlcsTkwh_form rsform = new xilk_xlcsTkwh_form();
		String[] outputValues = new String[] { "xxlsh", "stlsh", "xxxh",
				"xxnr", "xxfz", "xxxsbj" };
		String tableName = "xxb";
		String[] columns = { "xxlsh" };
		String[] values = { myform.getXxlsh() };
		String[] rs = mydao.getOneRs(SQL_Util.getQuerySqlUseValue(tableName,
				columns, values, outputValues), nullArray, outputValues);
		FormUtils.setProperties(outputValues, rs, rsform);
		return rsform;
	}

	/**
	 * @category ����ĳ���ѡ����Ϣ������������ˮ�ţ������û������ѡ����Ϣ�������¼��xxb<font color=red>
	 *           ����������String </font>
	 * @return
	 */
	public String tkwh_stb_xx_add(xilk_xlcsTkwh_form myform) {
		boolean flag = false;
		String[] columns = new String[] { "stlsh", "xxxh", "xxnr", "xxfz",
				"xxxsbj" };
		String tableName = "xxb";
		String zdhs_zd = "XXLSH";
		String zdhz_value = "sequence_xxlsh.nextval";
		flag = StandardOperation.insert2(tableName, columns, FormUtils
				.getProperties(columns, myform), zdhs_zd, zdhz_value, request);
		return flag == true ? "insert success" : "insert fail";
	}

	/**
	 * @category �޸�ĳ���ѡ����Ϣ������ѡ����ˮ�ţ������û������ѡ����Ϣ���޸ļ�¼<font color=red> ����������String
	 *           </font>
	 * @return
	 */
	public String tkwh_stb_xx_modi(xilk_xlcsTkwh_form myform) throws Exception {
		String[] columns = new String[] { "stlsh", "xxxh", "xxnr", "xxfz",
				"xxxsbj" };
		String tableName = "xxb";
		boolean flag = StandardOperation.update(tableName, columns, FormUtils
				.getProperties(columns, myform), "XXLSH", myform.getXxlsh(),
				this.request);
		return flag == true ? "update success" : "update fail";
	}

	public String tkwh_st_xx_del(xilk_xlcsTkwh_form myform) throws Exception {
		boolean flag = false;
		String tableName = "xxb";
		String primaryKey = "XXLSH";
		String value = myform.getXxlsh();
		flag = StandardOperation.delete(tableName, primaryKey, value,
				this.request);
		return flag == true ? "del success" : "del fail";
	}

	public List<HashMap<String, String>> tkwh_sjst_search(
			xilk_xlcsTkwh_form myform) {
		String[] columns = new String[] { "sjlsh", "stlxdm", "stndjbdm",
				"sslxdm" };
		String tableName = "VIEW_XLJK_XLCS_SJSTB";
		String[] outputValues = new String[] { "xn_id", "sjlsh", "stlsh",
				"stxh", "stnr", "stlxdm", "stndjbdm", "stjffs", "stfz", "stda",
				"stdajs", "stxsbj", "stndjbmc", "stlxmc", "sslxdm", "sslxmc",
				"sjm", "sjsm", "sjxsbj", "sjxd", "jrsj" };
		String userSql = "select a.XN_ID,a.SJLSH,a.STLSH,a.STXH,"
				+ "substr(a.STNR,1,5)||'...' STNR,a.STLXDM,a.STNDJBDM,a.STJFFS,"
				+ "a.STFZ,a.STDA,a.STDAJS,a.STXSBJ,a.STNDJBMC,a.STLXMC,a.SSLXDM,"
				+ "a.SSLXMC,a.SJM,a.SJSM,a.SJXSBJ,a.SJXD,a.JRSJ from "
				+ tableName + " a where 1=1 ";
		userSql += SQL_Util.getWhereSqlUseValue(columns, FormUtils
				.getProperties(columns, myform));
		if (StringUtils.isNotNull(myform.getSjlsh())) {
			userSql += " order by to_number(stxh) asc";
		}
		return mydao.getList(userSql, nullArray, outputValues);
	}

	public xilk_xlcsTkwh_form tkwh_sjst_view(xilk_xlcsTkwh_form myform) {
		xilk_xlcsTkwh_form rsform = new xilk_xlcsTkwh_form();
		String tableName = "VIEW_XLJK_XLCS_SJSTB";
		String[] outputValues = new String[] { "xn_id", "sjlsh", "stlsh",
				"stxh", "stnr", "stlxdm", "stndjbdm", "stjffs", "stfz", "stda",
				"stdajs", "stxsbj", "stndjbmc", "stlxmc", "sslxdm", "sslxmc",
				"sjm", "sjsm", "sjxsbj", "sjxd", "jrsj" };
		String[] otherKey = { "xn_id" };
		String[] otherKeyValue = { myform.getSjst_xnid() };
		String[] values = mydao.getOneRs(SQL_Util.getQuerySqlUseValue(
				tableName, otherKey, otherKeyValue, outputValues), nullArray,
				outputValues);
		FormUtils.setProperties(outputValues, values, rsform);
		return rsform;
	}

	public String tkwh_sjst_st_add(xilk_xlcsTkwh_form myform) throws Exception {
		boolean flag = false;
		String yxstlbStr = myform.getYxstStr();
		String[] stlbList = yxstlbStr.split("-");
		String sjlsh = myform.getSjlsh();
		String tableName = "sjstb";
		String[] otherK = { "SJLSH" };
		String[] otherKV = { sjlsh };
//		HttpServletRequest request = this.request;
		flag = StandardOperation.delete2(tableName, "XN_ID", otherK, otherKV,
				this.request);
		String[] columns = { "XN_ID", "SJLSH", "STLSH", "STXH" };
		for (int i = 0; i < stlbList.length; i++) {
			String[] values = { newId.new_xnid(tableName), sjlsh, stlbList[i],
					Integer.toString(i + 1) };
			flag = StandardOperation.insert(tableName, columns, values,
					this.request);
		}
		return flag == true ? "del success" : "del fail";
	}

	/** <font color='red'>�����������ݵ��������ݣ������ֶ�Ϊȫ���ֶ� </font> */
	public List<String[]> getCommonVector_Exp(xilk_xlcsTkwh_form myForm,
			String tableName, String[] columns) throws Exception {
		DAO mydao = DAO.getInstance();
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(mydao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		return mydao.rsToVator3(FormUtils.getNormalCondiSql(tableName, columns,
				FormUtils.getProperties(columns, myForm), null), nullArray,
				columnNames);
	}

	/** �������� */
	public void getCommonExp(xilk_xlcsTkwh_form myForm,
			HttpServletResponse response, HttpServletRequest request,
			String tableName, String[] columns) throws Exception {
		DAO mydao = DAO.getInstance();
		ArrayList<Object> rs = new ArrayList<Object>();
		rs.addAll(this.getCommonVector_Exp(myForm, tableName, columns));
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = mydao.getColumnNameCN(mydao.getColumnName(SQL_Util
				.getNoResultSql(tableName)), tableName);
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	/** ��ò�������list */
	public List<HashMap<String,String>> getYzList() {
		DAO mydao = DAO.getInstance();
		String sql = "select yzdm dm,yzmc mc from csxmyzb";
		List<HashMap<String,String>> list = mydao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		return list;
	}
	/** �������δ������list */
	public List<HashMap<String,String>> getStList(xilk_xlcsTkwh_form form) {
		DAO mydao = DAO.getInstance();
		String sql = "select stlsh || '/' || stlxdm || '/' || stxsbj dm,(case when length(stnr) > 12 then "+
         " substr(stnr, 0, 12)||'...' else stnr end) || '/' || stlxmc || '/' || stxsbj mc from view_st a "+
         "where not exists (select stlsh from yzstb where stlsh = a.stlsh) ";
		if(!Base.isNull(form.getStlxdm())){
			sql += " and a.stlxdm='"+form.getStlxdm()+"' ";
		}
		if(!Base.isNull(form.getStxsbj())){
			sql += " and a.stxsbj='"+form.getStxsbj()+"'";
		}
		sql += "order by a.stlxdm";
		List<HashMap<String,String>> list = mydao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		return list;
	}
	/** �����������list */
	public List<HashMap<String,String>> getYzstList(xilk_xlcsTkwh_form form) {
		DAO mydao = DAO.getInstance();
		String sql = "select a.stlsh || '/' || a.stlxdm || '/' || a.stxsbj dm,(case when length(a.stnr) > 12 then "+
         " substr(a.stnr, 0, 12)||'...' else a.stnr end) || '/' || a.stlxmc || '/' || a.stxsbj mc from view_st a,yzstb b "+
         "where a.stlsh=b.stlsh and  exists (select stlsh from yzstb where stlsh = a.stlsh) and yzdm='"+form.getYzdm()+"' ";
		if(!Base.isNull(form.getStlxdm())){
			sql += " and a.stlxdm='"+form.getStlxdm()+"' ";
		}
		if(!Base.isNull(form.getStxsbj())){
			sql += " and a.stxsbj='"+form.getStxsbj()+"'";
		}
		List<HashMap<String,String>> list = mydao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		return list;
	}
	/** �������
	 * @throws SQLException */
	public boolean saveYzst(xilk_xlcsTkwh_form form,String[] array) throws SQLException {
		DAO mydao = DAO.getInstance();
		String[] sqls = new String[array ==null? 1 : array.length+1];
		String sql = "delete from yzstb a where yzdm='"+form.getYzdm()+"' and  exists (select stlsh from view_st where a.stlsh=stlsh ";
		if(!Base.isNull(form.getStlxdm())){
			sql += " and stlxdm='"+form.getStlxdm()+"' ";
		}
		if(!Base.isNull(form.getStxsbj())){
			sql += " and stxsbj='"+form.getStxsbj()+"'";
		}
		sql += ")";
		sqls[0] = sql;
		if(array != null && array.length>0){
			for(int i=0;i<array.length;i++){
				sqls[i+1] = "insert into yzstb (yzdm,stlsh) values ('"+form.getYzdm()+"','"+array[i].split("/")[0]+"')";
			}		
		}
		int[] num = mydao.runBatch(sqls);	
		return mydao.checkBatch(num);
	}
	//��ò�����Ŀ��
	public String getXlcsxmmc(String xlcsxmdm){
		String sql = "select xlcsxmmc from xlcsxmdmb where xlcsxmdm=?";	
		return mydao.getOneRs(sql, new String[]{xlcsxmdm}, "xlcsxmmc");
	}
}
