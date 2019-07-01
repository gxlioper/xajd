package xgxt.utils.form;

/**
 * @author LiangChao
 * @date 2008-09-25
 */
import java.lang.reflect.InvocationTargetException;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;

import org.apache.struts.action.ActionForm;

import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
// import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;

public class FormUtils {

	private static final DAO dao = DAO.getInstance();

	public static final int EQUAL_MODE = 0;

	public static final int LIKE_MODE = 1;

	public static final int NOT_LIKE_MODE = 2;

	public static final int LEFT_LIKE_MODE = 3;

	public static final int RIGHT_LIKE_MODE = 4;

	public static final int NOT_EQUAL = 5;

	private static final String[] nullArray = {};
	
	private static final String str = new String();
	
	private static final New_Random_ID id = new New_Random_ID();

	/** for set properties */
	public static void setProperties(String[] columns, String[] values,
			Object myForm) {
		if (values != null) {
			Class myClass = myForm.getClass();
			for (int i = 0; i < columns.length; i++) {
				String methodName = "set"
						+ columns[i].substring(0, 1).toUpperCase()
						+ columns[i].substring(1);
				try {
					myClass.getMethod(methodName, (str).getClass())
							.invoke(myForm, values[i]);
				} catch (IllegalArgumentException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				} // �����޶�ΪString����
			}
		}
	}

	/** for set one property */
	public static void setOneProperty(String column, String value, Object myForm) {
		if (value != null) {
			Class myClass = myForm.getClass();
			String methodName = "set" + column.substring(0, 1).toUpperCase()
					+ column.substring(1);
			try {
				myClass.getMethod(methodName, (str).getClass())
						.invoke(myForm, value);
			} catch (IllegalArgumentException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} // �����޶�ΪString����
		}
	}

	/** for get properties */
	public static String[] getProperties(String[] columns, Object myForm) {
		String[] resultPro = new String[columns.length];
		Class myClass = myForm.getClass();
		for (int i = 0; i < columns.length; i++) {
			String methodName = "get"
					+ columns[i].substring(0, 1).toUpperCase()
					+ columns[i].substring(1);
			try {
				resultPro[i] = (String) myClass.getMethod(methodName,(Class[]) null)
						.invoke(myForm,(Object[]) null);
			} catch (IllegalArgumentException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} // ����Ϊ��
		}
		return resultPro;
	}

	/** for get one property */
	public static String getOneProperty(String column, Object myForm) {
		Class myClass = myForm.getClass();
		String methodName = "get" + column.substring(0, 1).toUpperCase()
				+ column.substring(1);
		try {
			return (String) myClass.getMethod(methodName,(Class[]) null).invoke(myForm,
					(Object[]) null);
		} catch (IllegalArgumentException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} // ����Ϊ��
		return "";
	}

	/** ������ת��ΪString ȥ��"(" �� ")" */
	public static String array2String_WithNoScope(String[] columns, String split) {
		return array2StringForInseSql(columns, split).replace("(", "").replace(
				")", "").trim();
	}

	/** ������ת����ΪString */
	public static String array2StringForInseSql(String[] columns, String split) {
		StringBuffer result = new StringBuffer("(");
		for (int i = 0; i < columns.length; i++) {
			result.append((i == columns.length - 1) ? columns[i] : columns[i]
					.toString()
					+ split);
		}
		return result.append(")").toString();
	}

	/** ���ز������ݵģ��Ÿ��� ������ʽ */
	public static String getInseSqlParams(int length) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < length; i++) {
			sb.append((i < length - 1) ? "?," : "?");
		}
		return sb.append(")").toString();
	}

	/** ����insert�� sql��� */
	public static String getInsertSql(String tableName, String[] columns) {
		return new StringBuffer("insert into ").append(tableName).append(
				array2StringForInseSql(columns, ",")).append("values").append(
				getInseSqlParams(columns.length)).toString();
	}

	/**
	 * 
	 * @param tableName
	 * @param columns
	 *            ֻ����ֶε�����Ϊvarchar2��Ч�����������͸��£�����������
	 * @param condiColumns
	 *            �����ֶ�
	 * @param condiValues
	 *            �����ֶε�ֵ
	 * @param mode
	 *            ƥ��ģʽ<���/�����/like/left like/right like/>
	 * @return
	 */
	public static String getUpdateSql(String tableName, String[] columns,
			String[] condiColumns, String[] condiValues, int mode) {
		StringBuffer sb = new StringBuffer("update ").append(tableName).append(
				" set ");
		for (int i = 0; i < columns.length; i++) {
			sb.append(columns[i]).append("=?").append(
					(i == columns.length - 1) ? "" : ",");
		}
		sb.append(" where 1=1 ");
		// condition
		if (mode == FormUtils.EQUAL_MODE) {
			for (int i = 0; i < columns.length; i++) {
				sb.append(" and ").append(columns[i]).append("=?");
			}
		} else if (mode == FormUtils.LIKE_MODE) {
			for (int i = 0; i < columns.length; i++) {
				sb.append(" and ").append(columns[i]).append(" like '%?%'");
			}
		} else if (mode == FormUtils.LEFT_LIKE_MODE) {
			for (int i = 0; i < columns.length; i++) {
				sb.append(" and ").append(columns[i]).append(" like '?%'");
			}
		} else if (mode == FormUtils.RIGHT_LIKE_MODE) {
			for (int i = 0; i < columns.length; i++) {
				sb.append(" and ").append(columns[i]).append(" like '%?'");
			}
		} else if (mode == FormUtils.NOT_LIKE_MODE) {
			for (int i = 0; i < columns.length; i++) {
				sb.append(" and ").append(columns[i]).append(" not like '%?%'");
			}
		} else if (mode == FormUtils.NOT_EQUAL) {
			for (int i = 0; i < columns.length; i++) {
				sb.append(" and ").append(columns[i]).append(" <> ?");
			}
		}
		// System.out.println(sb.toString());
		return sb.toString();

	}

	public static final int DISTINCT = 0;

	/**
	 * ����������Ϣ
	 * 
	 * @param tableName
	 *            �����ͼ��
	 * @param columns
	 *            �ֶ�����
	 * @param values
	 *            ֵ����
	 * @param outputValues
	 *            ������ֶ����� <font color='blue'>Ϊ*ʱΪȫ���ֶ�</font>
	 * @return
	 */
	public static String getNormalCondiSql(String tableName, String[] columns,
			String[] values, String[] outputValues) {
		StringBuffer sb = new StringBuffer("select ").append(
				(outputValues == null) ? "*" : array2String_WithNoScope(
						outputValues, ",")).append(" from ").append(tableName)
				.append(" where 1=1 ");
		for (int i = 0; i < columns.length; i++) {
			if (StringUtils.isNotNull(values[i])) {
				sb.append(" and ").append(columns[i]).append("='").append(
						values[i]).append("'");
			}
		}
		return sb.toString();
	}

	/**
	 * �õ���˵����,����ߵ��û�ֻ�ܲ�ѯ��������һ���û��Ѿ����ͨ����ѧ����Ϣ <font color='red'>ֻ��Ϊ�������[xy xx]..
	 * �Ƽ�ʹ�� <code>SQL_Util</code>�ķ���</font><br>
	 * FOR EXAMPLE : <font color='red'>select ... from where .... and
	 * xxsh(xysh,fdysh) = ''</font>
	 * <p>
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 * @param shlb
	 * @return
	 */
	public static String get_SH_NormalCondiSql(String tableName,
			String[] columns, String[] values, String[] outputValues,
			String shlb) {
		StringBuffer sb = new StringBuffer("select ").append(
				(outputValues == null) ? "*" : array2String_WithNoScope(
						outputValues, ",")).append(" from ").append(tableName)
				.append(" where 1=1 ");
		for (int i = 0; i < columns.length; i++) {
			if (StringUtils.isNotNull(values[i])) {
				sb.append(" and ").append(columns[i]).append("='").append(
						values[i]).append("'");
			}
		}
		if (shlb.equals("xxsh")) {
			sb.append(" and xysh= 'ͨ��'");
		}
		return sb.toString();
	}

	/**
	 * ���������� ���������ͨ����Sql��䲹��
	 * 
	 * @param shlb
	 *            ������
	 * @return
	 */
	public static String appenShPass(String shlb) {
		return new StringBuffer(" and ").append(shlb).append("= 'ͨ��' ")
				.toString();
	}

	/**
	 * ����������Ϣ����Щ��ϢΪ��ͬ�ģ� DISTINCT
	 * 
	 * @param tableName
	 *            �����ͼ��
	 * @param columns
	 *            �ֶ�����
	 * @param values
	 *            ֵ����
	 * @param outputValues
	 *            ������ֶ����� <font color='blue'>ΪnullʱΪȫ���ֶ�</font>
	 * @return
	 */
	public static String getNormalCondiSql_Distin(String tableName,
			String[] columns, String[] values, String[] outputValues) {
		StringBuffer sb = new StringBuffer("select distinct ").append(
				(outputValues == null) ? "*" : array2String_WithNoScope(
						outputValues, ",")).append(" from ").append(tableName)
				.append(" where 1=1 ");
		// System.out.println(sb.toString());
		for (int i = 0; i < columns.length; i++) {
			if (StringUtils.isNotNull(values[i])) {
				sb.append(" and ").append(columns[i]).append("='").append(
						values[i]).append("'");
			}
		}
		return sb.toString();
	}

	/**
	 * ��nullֵתΪ���ַ��� �����Ϊ�գ���ô��ԭֵ����
	 * 
	 * @param param
	 * @return
	 */
	public static String dealNull2Space(String param) {
		return param == null ? "" : param;
	}

	/**
	 * �������ȫ��Ԫ�ر�ΪСд
	 * 
	 * @param columnNames
	 * @return
	 */
	public static String[] chgArrayElem2LowerCase(String[] columnNames) {
		String[] lowCaseArray = new String[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			lowCaseArray[i] = columnNames[i].toLowerCase();
		}
		return lowCaseArray;
	}
	
	/**
	 * 
	 * @param columnNames
	 * @return
	 */
	public static String[] chgArrayElem2UpperCase(String[] columnNames) {
		String[] lowCaseArray = new String[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			lowCaseArray[i] = columnNames[i].toUpperCase();
		}
		return lowCaseArray;
	}

	/**
	 * �Ƿ���ڼ�¼
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return
	 */
	public static boolean haveRecord(String tableName, String[] columns,
			String[] values) {
		StringBuffer sb = new StringBuffer("select 'have' a from ").append(
				tableName).append(" where 1=1");
		for (int i = 0; i < columns.length; i++) {
			if (StringUtils.isNotNull(values[i])) {
				sb.append(" and ").append(columns[i]).append("=?");
			}
		}
		return dao.getOneRs(sb.toString(), values, new String[] { "a" }) != null ? true
				: false;
	}

	/**
	 * �Ƿ���ڼ�¼
	 * 
	 * @param tableName
	 * @param column
	 *            ������
	 * @param value
	 *            ����ֵ
	 * @return
	 */
	public static boolean haveOneRecord(String tableName, String pk,
			String value) {
		StringBuffer sb = new StringBuffer("select 'have' a from ").append(
				tableName).append(" where 1=1");
		if (StringUtils.isNotNull(value)) {
			sb.append(" and ").append(pk).append("=?");
			return null != dao.getOneRs(sb.toString(), new String[] { value },
					new String[] { "a" })  ? true : false;
		} else {
			return null != dao.getOneRs(sb.toString(), new String[] {},
					new String[] { "a" })  ? true : false;
		}
		
	}

	/**
	 * ������ɾ��һ����Ԫ�غ󷵻�
	 * 
	 * @param columns
	 *            �ܵ�����
	 * @param removeColumns
	 *            Ҫɾ����Ԫ����
	 * @return
	 */
	public static String[] removeArrayElem(String[] columns,
			String[] removeColumns) {
		if (removeColumns != null) {
			List<String> list = chgArray2List(columns);
			for (int k = 0; k < removeColumns.length; k++) {
				if (list.contains(removeColumns[k])) {
					list.remove(removeColumns[k]);
				}
			}
			return chgList2Array(list);
		} else { // if removeColumns is null ,then return columns self
			return columns;
		}
	}

	/**
	 * ��Ҫ���ӵ����� addColumns �ӵ�ԭ������ columns ����ǰ��
	 * 
	 * @param columns
	 *            �ܵ�����
	 * @param removeColumns
	 *            Ҫ���ӵ�Ԫ����
	 * @return
	 */
	public static String[] arrayCopy(String[] columns, String[] addColumns) {
		if (addColumns != null) {
			List<String> list = chgArray2List(columns);
			for (int i = 0; i < addColumns.length; i++) {
				list.add(0, addColumns[i]);
			}
			return chgList2Array(list);
			// System.arraycopy(addColumns, 0, columns, columns.length,
			// addColumns.length);
		} else {
			return columns;
		}
	}

	/**
	 * ������ת��Ϊ����
	 * 
	 * @param columns
	 *            ����
	 * @return
	 */
	public static List<String> chgArray2List(String[] columns) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < columns.length; i++) {
			list.add(columns[i]);
		}
		return list;
	}

	/**
	 * ������ת��Ϊ����
	 * 
	 * @param list
	 * @return
	 */
	public static String[] chgList2Array(List<String> list) {
		String[] array = new String[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	public static final String YEAR = "yyyy";

	public static final String MONTH = "MM";

	public static final String DAY = "dd";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	public static final String CHN_Mode = "yyyy��MM��dd�� HH:mm:ss EEE";

	public static final String PM_Mode = "hh:mm a";

	/** get now time ���ݴ���Ĳ�����ʽ������ʱ�� */
	public static String getCurrentTime(String pattern) {
		SimpleDateFormat simple = new SimpleDateFormat(pattern,
				new DateFormatSymbols(Locale.CHINESE));
		return simple.format(Calendar.getInstance().getTime());
	}

	/** Test method */
	public static void main(String[] args) {
		// System.out.println(getCurrentTime(FormUtils.PM_Mode));
		String s = "a.XXLSH ,a.STLSH,a.XXXH,a.XXNR,a.XXFZ,a.XXXSBJ";

		String[] sArray = s.replaceAll("a.", "").split(",");
		for (int i = 0; i < sArray.length; i++) {
			System.out.print("\"" + sArray[i].toLowerCase().trim() + "\",");
		}
		System.out.print("}");
	}

	/**
	 * ��֤�û��Ƿ��в�����ҳ���Ȩ��
	 * 
	 * @param userName
	 *            �û���
	 * @param userType
	 *            �û�����
	 * @param ActionPath
	 *            Action����������·��
	 */
	public static ActionForward CheckUserAccessPower(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		int p = Base.chkUPower(userName, getActionPath(request), userType
				.equalsIgnoreCase("stu"));
		if (p == -1) {
			request.setAttribute("errMsg", "��Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		return null;
	}

	/** ����Action��Path ���Path�� gnmkdmb�е�dyym��Ӧ */
	public static String getActionPath(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return new StringBuffer(uri.substring(uri.lastIndexOf("/") + 1))
				.append("?").append(request.getQueryString()).toString();
	}

	/**
	 * set ѧԺ�û� û�� ���� ��ɾ�Ĳ� �͵��뵼�� ��Ȩ�� ֻ�Ǽ򵥵�����writeAble��ֵ�� <br>
	 * ����request�е�userType���и�ֵ�����Բ���FormUtil��setXyUserWritAbleDisabled����
	 * ��request�е�writeAble���и�ֵ// For Example��<br>
	 * <code><font color='blue'>request.setAttribute("writeAble",writeAble);<br>
	 * </font></code>
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 */
	public static String setWritAble(
			HttpServletRequest request) {
		// String userDep = request.getSession().getAttribute("userDep")
		// .toString();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String writeAble = "yes";// дȨ�� �ٶ�ѧԺû�в���Ȩ��
		if (userType.equalsIgnoreCase("xx") || userType.equals("admin")) {
			writeAble = "yes";
			// myForm.setXydm(userDep);
		} else {
			writeAble = "no";
		}
		//request.setAttribute("userType", tempUserType);
		request.setAttribute("writeAble", writeAble);
		return writeAble;
	}

	/*public static setWritableYes(HttpServletRequest request){
		
	}*/
	
	 /**
	 * <font color='red'>Ȩ�޿��� ��request������userType �ֱ�Ϊ xx / xy / fdy / stu<br>
	 * ����ѧԺ�������û��� writeAble = no ֻ��ѧУ�û���Ȩ�� writeAble = yes �����浽request�� For
	 * Example:<br>
	 * <code><font color='blue'>request.setAttribute("userType", tempUserType);<br>
	 request.setAttribute("writeAble", writeAble);
	 </font></code> </font>
	 * 
	 * @param myForm
	 *            ActionForm
	 * @param request
	 *            request
	 */
	public static String setXyUserWritAbleDisabled(ActionForm myForm,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String writeAble = "";// дȨ�� �ٶ�ѧԺû�в���Ȩ��
		String tempUserType = "";
		if (userType.equalsIgnoreCase("xx") || userType.equals("admin")) { // ѧУ�û������Ա
			writeAble = "yes";
			tempUserType = "xx";
		} else {
			if (userType.equalsIgnoreCase("xy")) { // ѧԺ�û�
				writeAble = "no";
				try {
					FormUtils.setOneProperty("xydm", userDep, myForm); // ����ѧԺ�����ֵ
				} catch (Exception e) {
					e.printStackTrace();
				}
				tempUserType = request.getSession().getAttribute("isFdy")
						.toString().equals("true") ? "fdy" : "xy";// ����Ա
			} else {
				tempUserType = "stu"; // ѧ��
			}
		}
		request.setAttribute("userType", tempUserType); // ���û������ͱ��浽request��
		request.setAttribute("writeAble", writeAble);
		return writeAble;
	}

	/**
	 * request setAttributes <code><font color='blue'>
	 * 	/realTable/tableName/rs/rsNum/form
	 * </font></code>
	 * 
	 * @param request
	 * @param writeAble
	 * @param realTable
	 * @param tableName
	 * @param rs
	 * @param myForm
	 */
	public static void setReqAttr(HttpServletRequest request, String realTable,
			String tableName, List<String[]> rs, ActionForm myForm) {
		// HttpSession session = request.getSession();
		// String userType = session.getAttribute("userType").toString();
		// request.setAttribute("writeAble", writeAble);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		// request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", (rs == null) == true ? "0" : rs.size());
		request.setAttribute("form", myForm);
	}

	/**
	 * ���������id����
	 * @param realTable
	 * @return
	 */
	public static String getXnId(String realTable){
		return id.new_xnid(realTable);
	}
	
	/**
	 * �õ�request�е�ֵ ����where ���� sql��� <br>
	 * For Example:<font color='red'> where params[i] =
	 * request.getParameter(params[i]) and...</font>
	 * 
	 * @param params
	 *            Ҫ��requestȡ��Attribute��ʾ����
	 * @param request
	 * @return
	 */
	public static String getWhereStrByReq(String[] params,
			HttpServletRequest request) {
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		for (int i = 0; i < params.length; i++) {
			String tempValue = request.getParameter(params[i]);
			if (StringUtils.isNotNull(tempValue)) {
				sb.append(" and ").append(params[i]).append("='").append(
						tempValue).append("'");
			}
		}
		return sb.toString();
	}

	/**
	 * ���ر��е�������Ĭ��ֵ���ֶ����� �����û��Ĭ�ϵ�ֵ��ô����null �����쳣����null
	 * 
	 * @param realTable
	 *            �������
	 * @return
	 */
	public static String[] getDefaultColumns(String realTable) {
		StringBuffer sb = new StringBuffer(
				"select column_name from user_tab_columns where table_name='");
		sb.append(realTable.toUpperCase()).append(
				"' and data_default is not null");
		String[] defaultCols = null;
		try {
			defaultCols = dao.getArray(sb.toString(), nullArray, "column_name");
			if (defaultCols != null) {
				return chgArrayElem2LowerCase(defaultCols); // ���ֶε����ָ�ΪСд
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
