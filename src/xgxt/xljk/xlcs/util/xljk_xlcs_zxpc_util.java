package xgxt.xljk.xlcs.util;

import xgxt.DAO.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.base.Encrypt;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.xlcs.form.*;

public class xljk_xlcs_zxpc_util {

	DAO mydao = DAO.getInstance();

	StandardOperation myop = new StandardOperation();

	New_Random_ID newId = new New_Random_ID();

	HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public xljk_xlcs_zxpc_util() {
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
		// int a=0;
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
	public List Find_All(String userSql, String tableName) {
		List li = null;
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

	public List zxpc_sj_search(xilk_zxpc_form myform) {
		// List li=null;
		String sjlsh = myform.getSjlsh();
		String s1 = "!!SplitSignOne!!";
		String otherK = "SJXSBJ" + s1;
		String otherKV = "是" + s1;
		String usersql = "select a.SJLSH,a.SJM,a.SJSM,a.SJXSBJ,a.SJXD,a.JRSJ from";
		if (sjlsh != null && !sjlsh.equalsIgnoreCase("")) {
			otherK = otherK + "SJLSH!!SplitSignOne!!";
			otherKV = otherKV + sjlsh + "!!SplitSignOne!!";
		}
		String[] othk = otherK.split("!!SplitSignOne!!");
		String[] othkV = otherKV.split("!!SplitSignOne!!");
		return this.Find_By_OtherKey("SJB", usersql, othk, othkV);
	}

	public List zxpc_sjst_getsjList() {
		List li = null;
		String userSql = "select SJLSH,SJM from ";
		String tableName = "sjb";
		String[] otherKeys = { "SJXSBJ" };
		String[] otherKeyValues = { "是" };
		li = this.Find_By_OtherKey(tableName, userSql, otherKeys,
				otherKeyValues);
		return li;
	}

	public xilk_zxpc_form tkwh_findBySjlsh(xilk_zxpc_form myform) {
		xilk_zxpc_form newform = new xilk_zxpc_form();
		String sjlsh = myform.getSjlsh();
		String tableName = "sjb";
		String usersql = "select a.SJLSH,a.SJM,a.SJSM,a.SJXSBJ,a.SJXD,a.JRSJ,a.XLCSXMDM from";
		String[] otherKey = { "sjlsh" };
		String[] otherKeyValues = { sjlsh };
		List<HashMap<String, String>> li = this.Find_By_OtherKey(tableName,
				usersql, otherKey, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
//			String ff = map.get("XLCSXMDM");
			newform.setSjm(map.get("SJM"));
			newform.setSjsm(map.get("SJSM"));
			newform.setSjxsbj(map.get("SJXSBJ"));
			newform.setSjxd(map.get("SJXD"));
			newform.setJrsj(map.get("JRSJ"));
			newform.setXlcsxmdm(map.get("XLCSXMDM"));
		}
//		String ii = newform.getXlcsxmdm();
		if(null != map.get("XLCSXMDM")){
			String sql = "SELECT xlcsxmmc FROM xlcsxmdmb where xlcsxmdm = ?";
			DAO dao = DAO.getInstance();
			String[] rs1 = dao.getOneRs(sql, new String[] { map.get("XLCSXMDM") },
					new String[] { "XLCSXMMC" });
			newform.setXlcsxmmc(rs1[0]);
		}
		newform.setSjlsh(myform.getSjlsh());
		return newform;
	}

	public List zxpc_getsjinfoList(xilk_zxpc_form myform) {
		String sjlsh = myform.getSjlsh();
		String sql = "select count(*) flTotal,stlxmc from view_xljk_xlcs_sjstb where sjlsh=? group by stlxdm,stlxmc order by stlxdm";
		List sjinfoList = mydao.getList(sql, new String[] { sjlsh },
				new String[] { "flTotal", "stlxmc" });
		return sjinfoList;
	}

	public String zxpc_getTotalStNum(xilk_zxpc_form myform) {
		String sjlsh = myform.getSjlsh();
		String sql = "select count(*) total from view_xljk_xlcs_sjstb where sjlsh=?";
		String[] inputValue = { sjlsh };
		String[] outputValue = { "total" };
		List<HashMap<String, String>> li = mydao.getList(sql, inputValue,
				outputValue);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		String total = "";
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			total = map.get("total");
		}
		return total;
	}

	public List zxpc_getStList(xilk_zxpc_form myform) {
		String sjlsh = myform.getSjlsh();
		String sql = "select stlsh,stxh,stnr,stlxmc,stndjbmc,stfz from view_xljk_xlcs_sjstb where sjlsh=? order by to_number(stxh)";
		List stList = mydao.getList(sql, new String[] { sjlsh }, new String[] {
				"stlsh", "stxh", "stnr", "stlxmc", "stndjbmc", "stfz" });
		return stList;
	}

	public List zxpc_getXxList(xilk_zxpc_form myform) {
		String sjlsh = myform.getSjlsh();
		String sql = "select stlsh,stlxdm,xxxh,xxnr,xxfz from view_xljk_xlcs_sjstxxxx where sjlsh=? order by stlsh,xxxh";
		List xxList = mydao.getList(sql, new String[] { sjlsh }, new String[] {
				"stlsh", "stlxdm", "xxxh", "xxnr", "xxfz" });
		return xxList;
	}

	public String List_to_String(List li, String[] values) {
		String rsString = "";
		rsString = mydao.listToString(li, values);
		return rsString;
	}

	public String zxpc_getXxstr(xilk_zxpc_form myform) {
		List li = this.zxpc_getXxList(myform);
		String[] values = { "stlsh", "stlxdm", "xxxh", "xxnr", "xxfz" };
		String xxstr = this.List_to_String(li, values);
		return xxstr;
	}

	public xilk_zxpc_form zxpc_view_st(xilk_zxpc_form myform) {
		String stlsh = myform.getStlsh();
		xilk_zxpc_form resForm = new xilk_zxpc_form();
		String tableName = "VIEW_XLJK_XLCS_ST";
		String usersql = "select a.STLSH ,a.STNR,a.STLXDM,a.STNDJBDM,decode(a.stjffs,'0','按选项','1','按题','') stjffs,a.STFZ, a.STDA, a.STDAJS, a.STXSBJ,a.STNDJBMC,a.STLXMC,a.SSLXDM,a.SSLXMC from";
		String[] otherKeys = { "STLSH" };
		String[] otherKeyValues = { stlsh };
		List<HashMap<String, String>> li = this.Find_By_OtherKey(tableName,
				usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			resForm.setStlsh(stlsh);
			resForm.setStnr(map.get("STNR"));
			resForm.setStlxdm(map.get("STLXDM"));
			resForm.setStndjbdm(map.get("STNDJBDM"));
			resForm.setStjffs(map.get("STJFFS"));
			resForm.setStfz(map.get("STFZ"));
			resForm.setStda(map.get("STDA"));
			resForm.setStdajs(map.get("STDAJS"));
			resForm.setStxsbj(map.get("STXSBJ"));
			resForm.setStndjbmc(map.get("STNDJBMC"));
			resForm.setStlxmc(map.get("STLXMC"));
			resForm.setSslxdm(map.get("SSLXDM"));
			resForm.setSslxmc(map.get("SSLXMC"));
		}
		return resForm;
	}

	public xilk_zxpc_form zxpc_view_st2(xilk_zxpc_form myform, String[] colums,
			String[] values) {
		String stlsh = myform.getStlsh();
		xilk_zxpc_form resForm = new xilk_zxpc_form();
		// String stda=myform.getStda();
		String tableName = "VIEW_XLJK_XLCS_ST";
		String usersql = "select a.STLSH ,a.STNR,a.STLXDM,a.STNDJBDM,decode(a.stjffs,'0','按选项','1','按题','') stjffs,a.STFZ, a.STDA, a.STDAJS, a.STXSBJ,a.STNDJBMC,a.STLXMC,a.SSLXDM,a.SSLXMC from";
		List<HashMap<String, String>> li = this.Find_By_OtherKey(tableName,
				usersql, colums, values);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			resForm.setStlsh(stlsh);
			resForm.setStnr(map.get("STNR"));
			resForm.setStlxdm(map.get("STLXDM"));
			resForm.setStndjbdm(map.get("STNDJBDM"));
			resForm.setStjffs(map.get("STJFFS"));
			resForm.setStfz(map.get("STFZ"));
			resForm.setStda(map.get("STDA"));
			resForm.setStdajs(map.get("STDAJS"));
			resForm.setStxsbj(map.get("STXSBJ"));
			resForm.setStndjbmc(map.get("STNDJBMC"));
			resForm.setStlxmc(map.get("STLXMC"));
			resForm.setSslxdm(map.get("SSLXDM"));
			resForm.setSslxmc(map.get("SSLXMC"));
		}
		return resForm;
	}

	public xilk_zxpc_form zxpc_stb_xxxxxx(xilk_zxpc_form myform) {
		xilk_zxpc_form rsform = new xilk_zxpc_form();
		String xxlsh = myform.getXxlsh();
		String tableName = "xxb";
		String usersql = "select a.XXLSH ,a.STLSH,a.XXXH,a.XXNR,a.XXFZ,a.XXXSBJ from";
		String[] otherKeys = { "XXLSH" };
		String[] otherKeyValues = { xxlsh };
		List<HashMap<String, String>> li = this.Find_By_OtherKey(tableName,
				usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			rsform.setXxlsh(xxlsh);
			rsform.setXxfz(map.get("XXFZ"));
			rsform.setXxnr(map.get("XXNR"));
			rsform.setXxxh(map.get("XXXH"));
			rsform.setXxxsbj(map.get("XXXSBJ"));
			rsform.setStlsh(map.get("STLSH"));
		}
		return rsform;
	}

	public xilk_zxpc_form zxpc_stb_xxxxxx2(xilk_zxpc_form myform,
			String[] colums, String[] values) {
		xilk_zxpc_form rsform = new xilk_zxpc_form();
		String xxlsh = myform.getXxlsh();
		String tableName = "xxb";
		String usersql = "select a.XXLSH ,a.STLSH,a.XXXH,a.XXNR,a.XXFZ,a.XXXSBJ from";
		List<HashMap<String, String>> li = this.Find_By_OtherKey(tableName,
				usersql, colums, values);
		HashMap<String, String> map = new HashMap<String, String>();
		int tempLen = li.size();
		for (int i = 0; i < tempLen; i++) {
			map = li.get(i);
			rsform.setXxlsh(xxlsh);
			rsform.setXxfz(map.get("XXFZ"));
			rsform.setXxnr(map.get("XXNR"));
			rsform.setXxxh(map.get("XXXH"));
			rsform.setXxxsbj(map.get("XXXSBJ"));
			rsform.setStlsh(map.get("STLSH"));
		}
		return rsform;
	}

	// 验证学生是否合法，放回的是string
	// 如果是合法用户，返回的是true,如果是非法用户，返回的是no this xh，如果是密码错误，那么返回的是wrong mm
	public String xssqyy_check_student(xilk_zxpc_form myform) {
		String message = "true";
		String xh = myform.getXh();
		String xssrmm = myform.getXssrmm();
		String xsmm = "";
		String sql = "select * from view_xsjbxx where xh=? ";
		String[] input = { xh };
		String[] tabTitle = mydao.getColumnName("select * from VIEW_XSJBXX ");
		List<HashMap<String, String>> li = mydao.getList(sql, input, tabTitle);
		HashMap<String, String> map = new HashMap<String, String>();
		if (li.isEmpty()) {
			message = "no this xh";
			return message;
		} else {
			for (int i = 0; i < li.size(); i++) {
				Encrypt ept = new Encrypt();
				map = li.get(i);
				xsmm = map.get("MM");
				xsmm = ept.decrypt(xsmm);
			}
			if (xsmm.equals(xssrmm)) {
				return message;
			} else {
				message = "wrong mm";
				return message;
			}
		}
	}

	public String zxpc_test_record(xilk_zxpc_form myform) {
		boolean flag = false;
		String message = "";
		String sjlsh = myform.getSjlsh();
		String xh = myform.getXh();
		String dtkssj = myform.getDtkssj();
		String xxStr = myform.getXxStr();
		String tt[] = dtkssj.split("#");
		int year = Integer.parseInt(tt[0]);
		int month = Integer.parseInt(tt[1]) - 1;
		int day = Integer.parseInt(tt[2]);
		int hour = Integer.parseInt(tt[3]);
		int minute = Integer.parseInt(tt[4]);
		int second = Integer.parseInt(tt[5]);
		Calendar kssj = Calendar.getInstance();
		kssj.set(year, month, day, hour, minute, second);
		Calendar now = Calendar.getInstance();
		long t1 = kssj.getTimeInMillis();
		long t2 = now.getTimeInMillis();
		long time = t2 - t1;
		long tmilli = time % 1000;
		long tsecond = ((time - tmilli) / 1000) % 60;
		long tminute = (time - tmilli - tsecond * 1000) / 1000 / 60 % 60;
		long thour = (time - tmilli - tsecond * 1000 - tminute * 1000 * 60)
				/ 1000 / 60 / 60 % 24;
		long tday = (time - tmilli - tsecond * 1000 - tminute * 1000 * 60 - thour * 1000 * 60 * 60)
				/ 1000 / 60 / 60 / 24;
		String dtys = tday + "天" + thour + "小时" + tminute + "分" + tsecond + "秒";

		String selectedStr[] = xxStr.split("!!SplitSignOne!!");
		int len = selectedStr.length;
		String selectedStr1[][] = new String[len][2];

		String stfz = "";
		String stjffs = "";
		int cj = 0;
		int dtfz = 0;

		// Calendar test1 = Calendar.getInstance();

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dtsj = sf.format(new Date());

		xilk_zxpc_form stform = new xilk_zxpc_form();
		for (int i = 0; i < len; i++) {
			// int a=0;
			selectedStr1[i] = selectedStr[i].split("!!SplitSignTwo!!");

			int str1len = selectedStr1[i].length;
			if (1 == str1len) {
				dtfz = 0;
				cj += dtfz;
				@SuppressWarnings("unused")
				String tableName = "dtjlb";
				String xn_id = newId.new_xnid("dtjlb");
				@SuppressWarnings("unused")
				String[] columns = { "XN_ID", "xh", "sjlsh", "stlsh", "dtda",
						"dtfz", "dtsj" };
				@SuppressWarnings("unused")
				String[] values = { xn_id, xh, sjlsh, selectedStr1[i][0], "",
						String.valueOf(dtfz), dtsj };
				// flag=StandardOperation.insert(tableName, columns, values,
				// this.request);
			} else {
				stform.setStlsh(selectedStr1[i][0]);
				stform = this.zxpc_view_st(stform);
				stjffs = stform.getStjffs();

				if (stjffs.equalsIgnoreCase("按题")) {
					String[] colums = { "STLSH", "STDA" };
					String[] values = { selectedStr1[i][0], selectedStr1[i][1] };
					stform = this.zxpc_view_st2(myform, colums, values);
					stfz = stform.getStfz();
					if (stfz != null && !stfz.equalsIgnoreCase(""))
						dtfz = Integer.parseInt(stfz);
					else
						dtfz = 0;
				}

				else if (stjffs.equalsIgnoreCase("按选项")) {
					dtfz = 0;
					int leng = selectedStr1[i][1].length();
					String xxxh = "";
					String xxfz = "";
					int xxdtfz = 0;
					for (int j = 0; j < leng; j++) {
						xxxh = String.valueOf(selectedStr1[i][1].charAt(j));
						String[] colums = { "XXXH", "STLSH" };
						String[] values = { xxxh, selectedStr1[i][0] };
						stform = this.zxpc_stb_xxxxxx2(stform, colums, values);
						xxfz = stform.getXxfz();
						if (xxfz != null && !xxfz.equalsIgnoreCase(""))
							xxdtfz = Integer.parseInt(xxfz);
						else
							xxdtfz = 0;
						dtfz += xxdtfz;
					}
				}
				cj += dtfz;
				@SuppressWarnings("unused")
				String tableName = "dtjlb";
				String xn_id = newId.new_xnid("dtjlb");
				@SuppressWarnings("unused")
				String[] columns = { "XN_ID", "xh", "sjlsh", "stlsh", "dtda",
						"dtfz", "dtsj" };
				@SuppressWarnings("unused")
				String[] values = { xn_id, xh, sjlsh, selectedStr1[i][0],
						selectedStr1[i][1], String.valueOf(dtfz), dtsj };
				// flag=StandardOperation.insert(tableName, columns, values,
				// this.request);
			}
		}

		@SuppressWarnings("unused")
		String tableName = "cjjlb";
		String xn_id = newId.new_xnid("dtjlb");
		@SuppressWarnings("unused")
		String[] columns = { "XN_ID", "xh", "dtys", "sjlsh", "cj", "dtsj" };
		@SuppressWarnings("unused")
		String[] values = { xn_id, xh, dtys, sjlsh, String.valueOf(cj), dtsj };
		// flag=StandardOperation.insert(tableName, columns, values,
		// this.request);
		// Calendar test2 = Calendar.getInstance();
		if (flag == false) {
			message = "record_fail";
		} else if (flag == true) {
			message = "record_success";
		}
		return message;
	}

	// 根据试卷的流水号码返回试卷的名字
	public String get_sjmcBy_sjlsh(String sjlsh) {
		String sql = "select * from sjb where sjlsh=?";
		String sjm = mydao.getOneRs(sql, new String[] { sjlsh },
				new String[] { "sjm" })[0];
		return sjm;
	}

	// 根据试卷的流水号码返回试卷的说明
	public String get_sjsmBy_sjlsh(String sjlsh) {
		String sql = "select * from sjb where sjlsh=?";
		String[] array = mydao.getOneRs(sql, new String[] { sjlsh },
				new String[] { "sjsm" });
		String sjm = array == null?"":array[0];
		return sjm;
	}

	// 根据试卷的流水号和试题的显示顺序返回试题的详细信息
	public List<HashMap<String, String>> getStInfo(String sjlsh, String stxh) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "select * from view_xljk_xlcs_sjstb where sjlsh=? and stxh=?";
		String[] outputValue = new String[] { "stlsh", "stnr", };
		String[] stinfo = mydao.getOneRs(sql, new String[] { sjlsh, stxh },
				outputValue);
		if(null != stinfo){
			// 加入链表
			for (int i = 0; i < outputValue.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(outputValue[i], stinfo[i]);
				rs.add(map);
			}
		}
		return rs;
	}

	// 根据试题的流水号码取得其选项的内容
	public List getstMoreInfo(String stlsh) {
		String sql = "select * from xxb where stlsh=? order by xxxh";
		String[] outputValue = new String[] { "xxxh", "xxnr" };
		return mydao.getArrayList(sql, new String[] { stlsh }, outputValue);
	}

	// 判断试题是什么因素的
	public String judgeYx(String stxh) {
		// A．乐群性：3，26，27，51，52，76，101，126，151，176
		if (stxh.equals("3") || stxh.equals("26") || stxh.equals("27")
				|| stxh.equals("51") || stxh.equals("52") || stxh.equals("76")
				|| stxh.equals("101") || stxh.equals("126")
				|| stxh.equals("151") || stxh.equals("176")) {
			return "A";
		} else if (stxh.equals("28") || stxh.equals("53") || stxh.equals("54")
				|| stxh.equals("77") || stxh.equals("78") || stxh.equals("102")
				|| stxh.equals("103") || stxh.equals("127")
				|| stxh.equals("128") || stxh.equals("152")
				|| stxh.equals("153") || stxh.equals("177")
				|| stxh.equals("178")) {
			// B．聪慧性：28，53，54，77，78，102，103，127，128，152，153，177，178。
			return "B";
		} else if (stxh.equals("4") || stxh.equals("5") || stxh.equals("29")
				|| stxh.equals("30") || stxh.equals("55") || stxh.equals("79")
				|| stxh.equals("80") || stxh.equals("104")
				|| stxh.equals("105") || stxh.equals("129")
				|| stxh.equals("130") || stxh.equals("154")
				|| stxh.equals("179")) {
			// C．稳定性（情绪）：4，5，29，30，55，79，80，104，105，129，130，154,179.
			return "C";
		} else if (stxh.equals("6") || stxh.equals("7") || stxh.equals("31")
				|| stxh.equals("32") || stxh.equals("56") || stxh.equals("57")
				|| stxh.equals("81") || stxh.equals("106")
				|| stxh.equals("131") || stxh.equals("155")
				|| stxh.equals("156") || stxh.equals("180")
				|| stxh.equals("181")) {
			// E.持强性：顺从←→独立固执: 6，7，31，32，56，57，81，106，131，155，156，180，181。
			return "E";
		} else if (stxh.equals("8") || stxh.equals("33") || stxh.equals("58")
				|| stxh.equals("82") || stxh.equals("83") || stxh.equals("107")
				|| stxh.equals("108") || stxh.equals("132")
				|| stxh.equals("133") || stxh.equals("157")
				|| stxh.equals("158") || stxh.equals("182")
				|| stxh.equals("183")) {
			// F．兴奋性：8，33，58，82，83，107，108，132，133，157，158，182，183。
			return "F";
		} else if (stxh.equals("9") || stxh.equals("34") || stxh.equals("59")
				|| stxh.equals("84") || stxh.equals("109")
				|| stxh.equals("134") || stxh.equals("159")
				|| stxh.equals("160") || stxh.equals("184")
				|| stxh.equals("185")) {
			// G．有恒性：9，34，59，84，109，134，159，160，184，185。
			return "G";
		} else if (stxh.equals("10") || stxh.equals("35") || stxh.equals("36")
				|| stxh.equals("60") || stxh.equals("110")
				|| stxh.equals("111") || stxh.equals("86")
				|| stxh.equals("132") || stxh.equals("133")
				|| stxh.equals("135") || stxh.equals("136")
				|| stxh.equals("161") || stxh.equals("186")) {
			// H．敢为性：10，35，36，60，61，85，86，110，111，135，136，161，186。
			return "H";
		} else if (stxh.equals("11") || stxh.equals("12") || stxh.equals("37")
				|| stxh.equals("62") || stxh.equals("87") || stxh.equals("112")
				|| stxh.equals("137") || stxh.equals("138")
				|| stxh.equals("162") || stxh.equals("163")) {
			// I．敏感性：理智←→感性: 11，12，37，62，87，112，137，138，162，163。
			return "I";
		} else if (stxh.equals("13") || stxh.equals("38") || stxh.equals("63")
				|| stxh.equals("64") || stxh.equals("88") || stxh.equals("89")
				|| stxh.equals("113") || stxh.equals("114")
				|| stxh.equals("139") || stxh.equals("164")) {
			// L．怀疑：13，38，63，64，88，89，113，114，139，164。
			return "L";
		} else if (stxh.equals("14") || stxh.equals("15") || stxh.equals("39")
				|| stxh.equals("40") || stxh.equals("65") || stxh.equals("90")
				|| stxh.equals("91") || stxh.equals("115")
				|| stxh.equals("116") || stxh.equals("140")
				|| stxh.equals("141") || stxh.equals("165")
				|| stxh.equals("166")) {
			// M．幻想性：现实←→幻想: 14，15，39，40，65，90，91，115，116，140，141，165，166。
			return "M";
		} else if (stxh.equals("16") || stxh.equals("17") || stxh.equals("41")
				|| stxh.equals("42") || stxh.equals("66") || stxh.equals("67")
				|| stxh.equals("92") || stxh.equals("117")
				|| stxh.equals("142") || stxh.equals("167")) {
			// N．世故性：16，17，41，42，66，67，92，117，142，167。
			return "N";
		} else if (stxh.equals("18") || stxh.equals("19") || stxh.equals("43")
				|| stxh.equals("44") || stxh.equals("68") || stxh.equals("69")
				|| stxh.equals("93") || stxh.equals("94") || stxh.equals("118")
				|| stxh.equals("119") || stxh.equals("143")
				|| stxh.equals("144") || stxh.equals("168")) {
			// O．忧虑性：沉着，自信←→忧虑抑郁: 18，19，43，44，68，69，93，94，118，119，143，144，168。
			return "O";
		} else if (stxh.equals("20") || stxh.equals("21") || stxh.equals("45")
				|| stxh.equals("46") || stxh.equals("70") || stxh.equals("95")
				|| stxh.equals("120") || stxh.equals("145")
				|| stxh.equals("169") || stxh.equals("170")) {
			// Q1．实验性：传统保守←→自由激进: 20，21，45，46，70，95，120，145，169，170。
			return "Q1";
		} else if (stxh.equals("22") || stxh.equals("47") || stxh.equals("71")
				|| stxh.equals("72") || stxh.equals("96") || stxh.equals("97")
				|| stxh.equals("121") || stxh.equals("122")
				|| stxh.equals("146") || stxh.equals("171")) {
			// Q2．独立性：依赖←→独立: 22，47，71，72，96，97，121，122，146，171。
			return "Q2";
		} else if (stxh.equals("23") || stxh.equals("24") || stxh.equals("48")
				|| stxh.equals("73") || stxh.equals("98") || stxh.equals("123")
				|| stxh.equals("147") || stxh.equals("148")
				|| stxh.equals("172") || stxh.equals("173")) {
			// Q3．自律性：23，24，48，73，98，123，147，148，172，173。
			return "Q3";
		} else if (stxh.equals("25") || stxh.equals("49") || stxh.equals("50")
				|| stxh.equals("74") || stxh.equals("75") || stxh.equals("99")
				|| stxh.equals("100") || stxh.equals("124")
				|| stxh.equals("125") || stxh.equals("149")
				|| stxh.equals("150") || stxh.equals("174")
				|| stxh.equals("175")) {
			// Q4．紧张性：心平气和←→紧张激动: 25，49，50，74，75，99，100，124，125，149，150，174，175。
			return "Q4";
		} else {
			return null;
		}
	}
	//通过试题流水号得到因子
	public String getYz(String stlsh){
		String sql = "select yzdm from yzstb where stlsh=?";
		String yzdm = mydao.getOneRs(sql, new String[]{stlsh}, "yzdm");
		return yzdm;
	}

	// 根据因素，题号和选项得到得分
	public String getDf(String yx, String stxh, String xx) {
		if (yx == null) {
			return "0";
		} else if (yx.equalsIgnoreCase("A")) {
			if (stxh.equals("3") || stxh.equals("52") || stxh.equals("101")
					|| stxh.equals("126") || stxh.equals("176")) {
				// 3，52，101，126，176→选a得2分。选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 6，27，51，76，151→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("B")) {
			if (stxh.equals("28") || stxh.equals("53") || stxh.equals("54")
					|| stxh.equals("78") || stxh.equals("103")
					|| stxh.equals("128") || stxh.equals("152")
					|| stxh.equals("177")) {
				// 28，53，54，78，103，128，152，177→选b得1分，选a，c为0
				if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else if (stxh.equals("77") || stxh.equals("102")
					|| stxh.equals("127") || stxh.equals("153")) {
				// 77，102，127，153→选c得1分，选a，b为0分
				if (xx.equalsIgnoreCase("C")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 178，→选a得1分，选b，c为0分
				if (xx.equalsIgnoreCase("A")) {
					return "1";
				} else {
					return "0";
				}
			}
		} else if (yx.equalsIgnoreCase("C")) {
			if (stxh.equals("4") || stxh.equals("30") || stxh.equals("55")
					|| stxh.equals("105") || stxh.equals("129")
					|| stxh.equals("130") || stxh.equals("179")) {
				// 4，30，55，105，129，130，179→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 5，29，79，80，104，154→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("E")) {
			if (stxh.equals("7") || stxh.equals("56") || stxh.equals("131")
					|| stxh.equals("155") || stxh.equals("156")
					|| stxh.equals("180") || stxh.equals("181")) {
				// 7，56，131，155，156，180，181→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 6，31，32，57，81，106→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("F")) {
			if (stxh.equals("33") || stxh.equals("58") || stxh.equals("132")
					|| stxh.equals("133") || stxh.equals("182")
					|| stxh.equals("183")) {
				// 33，58，132，133，182，183→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 8，82，83，107，108，157，158→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("G")) {
			if (stxh.equals("59") || stxh.equals("109") || stxh.equals("134")
					|| stxh.equals("160") || stxh.equals("184")
					|| stxh.equals("185")) {
				// 59，109，134，160，184，185→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 9，34，84，159→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("H")) {
			if (stxh.equals("10") || stxh.equals("36") || stxh.equals("110")
					|| stxh.equals("111") || stxh.equals("136")
					|| stxh.equals("186")) {
				// 10，36，110，111，136，186→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 35，60，61，85，85，135，161→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("I")) {
			if (stxh.equals("11") || stxh.equals("12") || stxh.equals("37")
					|| stxh.equals("112") || stxh.equals("138")
					|| stxh.equals("163")) {
				// 11，12，37，112，138，163→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 62，87，137，162→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("L")) {
			if (stxh.equals("13") || stxh.equals("38") || stxh.equals("88")
					|| stxh.equals("113") || stxh.equals("114")
					|| stxh.equals("164")) {
				// 13，38，88，113，114，164→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 63，64，89，139 →选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("M")) {
			if (stxh.equals("39") || stxh.equals("40") || stxh.equals("65")
					|| stxh.equals("91") || stxh.equals("115")
					|| stxh.equals("140")) {
				// 39，40，65，91，115，140→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 14，15，90，116，141，165，166→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("N")) {
			if (stxh.equals("17") || stxh.equals("42") || stxh.equals("117")
					|| stxh.equals("142") || stxh.equals("167")) {
				// 17，42，117，142，167→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 16，41，66，67，92→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("O")) {
			if (stxh.equals("18") || stxh.equals("43") || stxh.equals("69")
					|| stxh.equals("118") || stxh.equals("119")
					|| stxh.equals("143") || stxh.equals("168")) {
				// 18，43，69，118，119，143，168→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 19，44，68，93，94，144→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("Q1")) {
			if (stxh.equals("20") || stxh.equals("21") || stxh.equals("46")
					|| stxh.equals("70") || stxh.equals("145")
					|| stxh.equals("169")) {
				// 20，21，46，70，145，169→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 45，95，120，170→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("Q2")) {
			if (stxh.equals("47") || stxh.equals("71") || stxh.equals("72")
					|| stxh.equals("146") || stxh.equals("171")) {
				// 47，71，72，146，171→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 22，96，97，121，122→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("Q3")) {
			if (stxh.equals("48") || stxh.equals("73") || stxh.equals("98")
					|| stxh.equals("147") || stxh.equals("148")
					|| stxh.equals("173")) {
				// 48，73，98，147，148，173→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 23，24，123，172→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else if (yx.equalsIgnoreCase("Q4")) {
			if (stxh.equals("25") || stxh.equals("49") || stxh.equals("50")
					|| stxh.equals("74") || stxh.equals("99")
					|| stxh.equals("124") || stxh.equals("149")
					|| stxh.equals("150") || stxh.equals("174")) {
				// 25，49，50，74，99，124，149，150，174→选a得2分，选c得0分
				if (xx.equalsIgnoreCase("A")) {
					return "2";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "0";
				}
			} else {
				// 75，100，125，175→选c得2分，选a得0分
				if (xx.equalsIgnoreCase("A")) {
					return "0";
				} else if (xx.equalsIgnoreCase("B")) {
					return "1";
				} else {
					return "2";
				}
			}
		} else {
			return "0";
		}
	}
	
	//通过试题流水号和选项号获得试题得分
	public String getStdf(String stlsh,String xx){
		String sql = "select xxfz from xxb where stlsh=? and xxxh=?";
		String xxfz = mydao.getOneRs(sql, new String[]{stlsh,xx}, "xxfz");
		return xxfz;
	}

	// 保存分数
	public boolean saveDf(String[] input) throws Exception {
		String sql = "insert into zcystmdfb values(?,?,?,?)";
		boolean isOk = mydao.insert(sql, input);
		return isOk;
	}
	//获得试题流水号
	public String getStlsh(String sjlsh,String stxh) throws Exception {
		String sql = "select stlsh from sjstb where sjlsh=? and stxh=?";
		String stlsh = mydao.getOneRs(sql, new String[]{sjlsh,stxh}, "stlsh");
		return stlsh;
	}
	//处理字符连接
	public StringBuffer dealStrBuff(StringBuffer sb,String[] values){
		sb = (sb.toString().length()>0)?sb.append("!!SplitSign!!"):sb;
		sb.append("insert into zcystmdfb(xh,sjlsh,yx,th,df) values (");
		for(int i=0;i<values.length;i++){
			sb.append("'");
			sb.append(values[i]);
			if(i==values.length-1){	
				sb.append("')");
			}else{
				sb.append("',");
			}			
		}
		return sb;
	}

	// 给学生总结评语
	public List<HashMap<String, String>> getZongJie(String xh, String xb)
			throws Exception {
		// 对每个因素进行分析
		String[] yxz = new String[] { "A", "B", "C", "E", "F", "G", "H", "I",
				"L", "M", "N", "O", "Q1", "Q2", "Q3", "Q4" };// 十六大因素
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String, String>>(
				yxz.length);
		List<HashMap<String, String>> fsList = new ArrayList<HashMap<String, String>>(
				yxz.length);// 保存每个因素的分数
		String sql = "select sum(to_number(df)) yxdf from zcystmdfb where xh=? and yx=?";
		for (int i = 0; i < yxz.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			String mxfs = mydao.getOneRs(sql, new String[] { xh, yxz[i] },
					new String[] { "yxdf" })[0];
			map.put(yxz[i], (mxfs == null) ? "0" : mxfs);
			fsList.add(map);
		}
		// 将记录保存到数据库中
		String[] fsArray = new String[yxz.length + 1];
		fsArray[0] = xh;
		for (int i = 0; i < yxz.length; i++) {
			fsArray[i + 1] = fsList.get(i).get(yxz[i]);
		}
		sql = "insert into zcyscjb values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		mydao.insert(sql, fsArray);
		// 与标准分进行相比,得到标准分数，拿到评语
		sql = "select * from zcbzdyb where yx=? and xb=?";
		String[] bzfArrayRes = new String[yxz.length + 1];
		bzfArrayRes[0] = xh;
		for (int i = 0; i < yxz.length; i++) {
			List<HashMap<String, String>> bzfList = mydao.getList(sql,
					new String[] { yxz[i], xb }, new String[] { "dyysf"});
			for (int j = 0; j < bzfList.size(); j++) {
				String[] bzfArray = bzfList.get(j).get("dyysf").split("-");
				// 有分数间隔
				if ((bzfArray.length > 1
						&& Integer.parseInt(fsList.get(i).get(yxz[i])) >= Integer
								.parseInt(bzfArray[0]) && Integer
						.parseInt(fsList.get(i).get(yxz[i])) <= Integer
						.parseInt(bzfArray[1]))
						|| (bzfArray.length == 1 && bzfArray[0]
								.equalsIgnoreCase(fsList.get(i).get(yxz[i])))) {
					String tempSql = "select * from zcbzdyb where dyysf=?";
					String[] bzfsArray = mydao.getOneRs(tempSql,
							new String[] { bzfList.get(j).get("dyysf") },
							new String[] { "bzf", "dj" });
					tempSql = "select * from zcbzpyb where dj=? and yx=?";
					String[] pyArray = mydao.getOneRs(tempSql, new String[] {
							bzfsArray[1], yxz[i] }, new String[] { "bzpy",
							"yxmc" });
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("yxz", yxz[i]);
					map.put("pyArray", pyArray[1]);
					map.put("bzfsArray", bzfsArray[0]);
					map.put("sjdf", fsList.get(i).get(yxz[i]));
					map.put("py", pyArray[0]);
					resList.add(map);
					bzfArrayRes[i + 1] = bzfsArray[0];
					// bf.append(yxz[i] + ": " + pyArray[1] + ", "
					// + "标准分得分为："+bzfsArray[0]
					// + " 你的实际得分为：" + fsList.get(i).get(yxz[i])
					// + "\n 评语:" + pyArray[0] +";\n");
					break;
				}
			}
		}
		// 将标准分插入到表中，便于以后查询
		sql = "insert into zcyscjzbfb values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		mydao.insert(sql, bzfArrayRes);
		return resList;
	}
	
//	 给学生总结评语
	public List<HashMap<String, String>> getResult(String xh){
		return null;
	}
	
	// 根据学号得到性别
	public String getXb(String xh) {
		String sql = "select * from view_xsjbxx where xh=?";
		String[] xbs = mydao.getOneRs(sql, new String[] { xh },
				new String[] { "xb" });
		String xb = xbs == null?"":xbs[0];
		if (xb.equalsIgnoreCase("男") || xb.equalsIgnoreCase("01")
				|| xb.equalsIgnoreCase("1")) {
			// 男子表示为１，女子表示为０
			return "1";
		} else {
			return "0";
		}
	}

	// 根据试卷的名字返回试卷的题目数目
	public String getExamTotalNum(String sjlsh) {
		String sql = "select count(*) num from sjstb where sjlsh=?";
		String[] examNum = mydao.getOneRs(sql, new String[] { sjlsh },
				new String[] { "num" });
		return examNum[0];
	}

	// 心理健康自评试卷[保存一条数据]
	public boolean save_XljkExam(String[] input) throws Exception {
		String sql = "insert into xljk_sjxsjlb values(?,?,?,?)";
		return mydao.insert(sql, input);
	}

	// 根据学生的学号得到心理健康试卷所有因子的分数,保存到数据库中
	public boolean analyse_xjlkByStuId(String xh) throws Exception {
		String[] record = new String[14];
		// 得到总分
		String sql = "select sum(df) zf from xljk_sjxsjlb where xh=?";
		String zf = mydao.getOneRs(sql, new String[] { xh },
				new String[] { "zf" })[0];
		Float zjfF = Float.parseFloat(zf) / 90;
		// 得到总均分
		String zjf = (String.valueOf(zjfF).length() > 3) ? String.valueOf(zjfF)
				.substring(0, 4) : String.valueOf(zjfF);
		// 得到阳性项目数
		sql = "select count(*) yxxms from xljk_sjxsjlb where xh=? and df > '0'";
		String yxxms = mydao.getOneRs(sql, new String[] { xh },
				new String[] { "yxxms" })[0];
		// 得到阳性症状痛苦水平
		String yxzztksp = (String.valueOf(
				Float.parseFloat(zf) / Float.parseFloat(yxxms)).length() > 3) ? String
				.valueOf(Float.parseFloat(zf) / Float.parseFloat(yxxms))
				.substring(0, 4)
				: String
						.valueOf(Float.parseFloat(zf) / Float.parseFloat(yxxms));
		sql = "select sum(df) peryzdf,count(yz) num,yz from xljk_sjxsjlb where xh=? group by yz order by yz";
		// 将得到的有关数据放入到数组record中
		record[0] = xh;
		record[1] = zf;
		record[2] = zjf;
		record[3] = yxzztksp;
		List<HashMap<String, String>> yzPerScore = mydao.getList(sql,
				new String[] { xh }, new String[] { "peryzdf", "num", "yz" });
		for (int i = 0; i < yzPerScore.size(); i++) {
			Float temp_peryzdf = Float.parseFloat(yzPerScore.get(i).get(
					"peryzdf"));
			Float temp_num = Float.parseFloat(yzPerScore.get(i).get("num"));
			record[i + 4] = (String.valueOf(temp_peryzdf / temp_num).length() > 3) ? String
					.valueOf(temp_peryzdf / temp_num).substring(0, 4)
					: String.valueOf(temp_peryzdf / temp_num);
		}
		// 保存记录
		sql = "insert into xljkzptjb values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean isSaveOk = mydao.insert(sql, record);
		return isSaveOk;
	}

	public boolean isStuAlreadyDid(String xh) {
		String sql = "select xh from zcyscjb where xh=?";
		String[] stuList = mydao.getOneRs(sql, new String[] { xh },
				new String[] { "xh" });
		if (stuList == null) {
			return false;
		}
		return true;
	}
	public boolean isFinishTest(String xh) {
		String sql = "select xh from yzfsb where xh=?";
		String[] stuList = mydao.getOneRs(sql, new String[] { xh },
				new String[] { "xh" });
		if (stuList == null) {
			return false;
		}
		return true;
	}
	public boolean isStuAlready(String xh) {
		String sql = "select xh from zzzplb where xh=?";
		String[] stuList = mydao.getOneRs(sql, new String[] { xh },
				new String[] { "xh" });
		if (stuList == null) {
			return false;
		}
		return true;
	}

	// 根据试题判断因子类型
	public String getYzById(String stxh) {
		if (stxh.equals("1") || stxh.equals("4") || stxh.equals("12")
				|| stxh.equals("27") || stxh.equals("40") || stxh.equals("42")
				|| stxh.equals("48") || stxh.equals("49") || stxh.equals("52")
				|| stxh.equals("53") || stxh.equals("56") || stxh.equals("58")) {
			// 躯体化因子：包括第1，4，12，27，40，42，48，49，52，53，56，58题共12项
			return "0";
		} else if (stxh.equals("3") || stxh.equals("9") || stxh.equals("10")
				|| stxh.equals("28") || stxh.equals("38") || stxh.equals("45")
				|| stxh.equals("46") || stxh.equals("51") || stxh.equals("55")
				|| stxh.equals("65")) {
			// 强迫症状因子：包括第3，9，10，28，38，45，46，51，55，65题共10项。
			return "1";
		} else if (stxh.equals("6") || stxh.equals("21") || stxh.equals("34")
				|| stxh.equals("36") || stxh.equals("37") || stxh.equals("41")
				|| stxh.equals("61") || stxh.equals("69") || stxh.equals("73")) {
			// 人际关系敏感因子：包括第6，21，34，36，37，41，61，69，73题共9项。
			return "2";
		} else if (stxh.equals("5") || stxh.equals("14") || stxh.equals("15")
				|| stxh.equals("20") || stxh.equals("22") || stxh.equals("26")
				|| stxh.equals("29") || stxh.equals("30") || stxh.equals("31")
				|| stxh.equals("32") || stxh.equals("54") || stxh.equals("71")
				|| stxh.equals("79")) {
			// 抑郁因子：包括第5，14，15，20，22，26，29，30，31，32，54，71，79题共13项
			return "3";
		} else if (stxh.equals("2") || stxh.equals("17") || stxh.equals("23")
				|| stxh.equals("33") || stxh.equals("39") || stxh.equals("57")
				|| stxh.equals("72") || stxh.equals("78") || stxh.equals("80")
				|| stxh.equals("86")) {
			// 焦虑因子：包括第2，17，23，33，39，57，72，78，80，86题共10项。
			return "4";
		} else if (stxh.equals("11") || stxh.equals("24") || stxh.equals("63")
				|| stxh.equals("67") || stxh.equals("74") || stxh.equals("81")) {
			// 敌对因子：包括第11，24，63，67，74，81题共6项。
			return "5";
		} else if (stxh.equals("13") || stxh.equals("25") || stxh.equals("47")
				|| stxh.equals("50") || stxh.equals("70") || stxh.equals("75")
				|| stxh.equals("82")) {
			//恐怖因子：包括第13，25，47，50，70，75，82题共7项。
			return "6";
		} else if (stxh.equals("8") || stxh.equals("18") || stxh.equals("43")
				|| stxh.equals("68") || stxh.equals("76") || stxh.equals("83")) {
			//偏执因子：包括第8，18，43，68，76，83题共6项。
			return "7";
		} else if (stxh.equals("7") || stxh.equals("16") || stxh.equals("35")
				|| stxh.equals("62") || stxh.equals("77") || stxh.equals("84")
				|| stxh.equals("85") || stxh.equals("87") || stxh.equals("88")
				|| stxh.equals("90")) {
			//精神病性因子：包括第7，16，35，62，77，84，85，87，88，90题共10项。
			return "8";
		} else if (stxh.equals("19") || stxh.equals("44") || stxh.equals("59")
				|| stxh.equals("60") || stxh.equals("64") || stxh.equals("66")
				|| stxh.equals("89")) {
			//附加因子：包括第19，44，59，60，64，66，89题共7项。
			return "9";
		}
		return "0";
	}
	
	/**判断学生是否存在*/
	public boolean stuExist(String xh){
		String sql ="select xh from view_xsjbxx where xh=?";
		return mydao.getOneRs(sql, new String[]{xh}, new String[]{"xh"}) == null ? false : true; 
	}
	
	/*************************************************************************************************/
	/**
	 * 保存学生的信息 <font color='blue'>用于武汉理工大学</font>
	 * */	
	public boolean saveStuDWR(String[] params) throws Exception{
		New_Random_ID newid = new New_Random_ID();
		String xn_id = newid.new_xnid("xljk_khxx");//create xn_id primary
		String tableName = "XJLK_WHLGDX_KHXX";
		String sql = "insert into " + tableName + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String[] values = new String[params.length + 1];
		values[0] = xn_id;
		System.arraycopy(params, 0, values, 1, params.length);
		return mydao.runUpdate(sql, values);
	}	
	
	/**
	 *判断学生是否已经在困惑对象维护的信息表中 
	 **/
	public boolean isStuInKHXX(String xh){
		String sql = "select * from XJLK_WHLGDX_KHXX where xh=?";
		return mydao.getOneRs(sql, new String[]{xh}, new String[]{"xh"}) == null ? false : true;
	}
	
	/**
	 * 	根据学号返回学生的基本信息
	 * */
	public String[] getStuInfoByXh(String xh){
		String[] CHOICE_CONDIARRAYCOl =
			{"nj","xymc","zymc","bjmc","xh","xm","xb","ssbh","sjhm","lxdh","qsdh","xz","sfzh","jg"};
		String sql = "select * from view_xsxxb where xh=?";
		return mydao.getOneRs(sql, new String[]{xh.trim()}, CHOICE_CONDIARRAYCOl);
	}
	
	/**
	 * 修改学生的信息
	 * */
	public boolean modiStuInfo(String xh,String[] columns,String[] values) throws Exception{
		StringBuffer sql = new StringBuffer("update XJLK_WHLGDX_KHXX set ");
		for(int i=0;i<columns.length;i++){
			sql.append(columns[i] + "=?" + (i == columns.length - 1 ? "" : ","));
		}
		sql.append(" where xh = '");
		sql.append(xh);
		sql.append("'");
		return mydao.runUpdate(sql.toString(), values);
	}
	
	/***
	 * 根据虚拟主键删除学生的信息
	 */
	public boolean deleteStuInfoByXn_id(String xn_id) {
		try{
		String sql = "delete from XJLK_WHLGDX_KHXX where xn_id =?";
		return mydao.runUpdate(sql, new String[]{xn_id.trim()});
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 批量删除学生的信息
	 */
	public boolean deleteStuInfoByCondi(String columns,String[] params){
		String [] column = columns.split("-");
		StringBuffer sql = new StringBuffer("select xn_id from VIEW_XLJK_WHLGDX_KHXX where 1=1 ");
		for(int i=0;i<column.length;i++){
			if(!StringUtils.isNull(params[i])){
				sql.append(" and ");
				sql.append(column[i]);
				sql.append("='");
				sql.append(params[i]);
				sql.append("'");
			}	
		}
		List<HashMap<String,String>> rs = mydao.getList(sql.toString(),new String[]{}, new String[]{"xn_id"});
		if(rs.size() == 0){
			return true;
		}else{
			StringBuffer sqlDelSb = new StringBuffer("delete from XJLK_WHLGDX_KHXX where xn_id in(");
			sqlDelSb.append(sql.toString() + ")");
			try{
				return mydao.runUpdate(sqlDelSb.toString(),new String[]{}); //no Exception then true
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
		}
	}
	//批量处理sql
	public boolean  dealBatch(String[] sqls) throws SQLException{
		int[] num = mydao.runBatch(sqls);	
		return mydao.checkBatch(num);
		
	}
	
	//增加因子分数和总分数
	public boolean  addYzAndZfs(String sjlsh,String xh) throws Exception{
		String sql = "insert into yzfsb (xh,sjlsh,yzdm,df) select xh,sjlsh,'-1',avg(df) df from zcystmdfb where sjlsh=? and xh=? group by xh,sjlsh ";
		boolean flag = mydao.runUpdate(sql, new String[]{sjlsh,xh});
		sql = "insert into yzfsb (xh,sjlsh,yzdm,df) select xh,sjlsh,yx,avg(df) df from zcystmdfb where sjlsh=? and xh=? and yx is not null group by xh,sjlsh,yx";
		flag = mydao.runUpdate(sql, new String[]{sjlsh,xh});
		return flag;
	}
	//获得因子的标准评语
	public List<HashMap<String,String>>  getYzpy(String sjlsh,String xh) throws Exception{
		String sql = "select b.yxmc,a.df,bzpy from (select yzdm,df from yzfsb where sjlsh=? and xh=? and yzdm <>'-1')a,zcbzpyb " +
				"b where a.yzdm=b.yx and to_number(df) between to_number(substr(b.bzf,0,instr(b.bzf,'-')-1)) and " +
				"to_number(substr(b.bzf,instr(b.bzf,'-')+1))";
		List<HashMap<String,String>> list = mydao.getList(sql, new String[]{sjlsh,xh}, new String[]{"yxmc","df","bzpy"});
		return list == null ?new ArrayList<HashMap<String,String>>() :list;
	}
	//获得总结评语
	public HashMap<String,String>  getZjpy(String sjlsh,String xh) throws Exception{
		String sql = "select '综合评语' yxmc,a.df,sjzfpy bzpy from (select sjlsh,df from yzfsb where sjlsh=? and xh=? and yzdm ='-1') a,sjzfpyb " +
				"b where a.sjlsh=b.sjlsh and to_number(df) between to_number(substr(b.fzfw,0,instr(b.fzfw,'-')-1)) and " +
				"to_number(substr(b.fzfw,instr(b.fzfw,'-')+1))";
		List<HashMap<String,String>> list = mydao.getList(sql, new String[]{sjlsh,xh}, new String[]{"yxmc","df","bzpy"});
		HashMap<String,String> map = new HashMap<String,String>();
		if(list != null && list.size()>0){
			map = list.get(0);
		}else{
			map = null;
		}
		return map;
	}
	//获得总分，因子平均分，阳性项目数评语
	public HashMap<String,String>  getZf_Pjf_Yxxms(String sjlsh,String xh) throws Exception{
		HashMap<String,String> map = new HashMap<String,String>();
		boolean flag = false;
		String sql = "select sum(df) df from zcystmdfb where sjlsh=? and xh=? group by sjlsh,xh";
		String result = mydao.getOneRs(sql, new String[]{sjlsh,xh},"df");
		if(!Base.isNull(result)){
			flag = Integer.parseInt(result)>160? true:false;
		}
		if(flag){
			map.put("yxmc", "总得分");
			map.put("df", Integer.parseInt(result)+"");
			map.put("bzpy", "需进一步测查");
			return map;
		}
		sql = "select xh from yzfsb where sjlsh=? and xh=? and to_number(df)>2";
		result = mydao.getOneRs(sql, new String[]{sjlsh,xh},"xh");
		if(!Base.isNull(result)){
			map.put("yxmc", "各项因子平均分");
			map.put("df", "存在大于２分");
			map.put("bzpy", "需进一步测查");
			return map;
		}
		sql = "select count(*) count from zcystmdfb where sjlsh=? and xh=? and to_number(df) between 1 and 4 group by sjlsh,xh";
		result = mydao.getOneRs(sql, new String[]{sjlsh,xh},"count");
		if(!Base.isNull(result)){
		flag = Integer.parseInt(result)>43? true:false;
		}
		if(flag){
			map.put("yxmc", "阳性项目数");
			map.put("df", Integer.parseInt(result)+"");
			map.put("bzpy", "需进一步测查");
			return map;
		}
		return null;
	}
	/*************************************************************************************************/	
}
