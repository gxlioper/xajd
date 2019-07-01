package xsgzgl.customForm.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_DEMO_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */
public class DemoFormDAO extends CommDAO {

	// ==================ִ�в�ѯ���� begin==============================
	/**
	 * �������ٱ��ԃ���ֶ�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getTableZdList(String ssb) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.zd,a.zdm ");
		sql.append("from xg_custom_zdb a");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zd", "zdm" });

		return list;
	}

	/**
	 * ����Form_id��ԃTable
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getTableList(String form_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.table_id,a.title,a.row_num ");
		sql.append("from xg_custom_table a ");
		sql.append("where form_id=? ");
		sql.append("order by to_number(a.xssx) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { form_id }, new String[] { "table_id", "title",
						"row_num" });

		return list;
	}
	
	/**
	 * ����Table_id��ԃTable
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getContentList(String table_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.row_num,a.column_num ");
		sql.append("from xg_custom_content a ");
		sql.append("where table_id=? ");
		sql.append("order by to_number(a.row_num),to_number(a.column_num) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { table_id }, new String[] { "row_num",
						"column_num" });

		return list;
	}
	// ==================ִ�в�ѯ���� end==============================

	// ==================ִ�����Ӳ��� begin==============================
	/**
	 * �������ݣ�xg_custom_table:�Զ��x���TABLE��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertCustomTable(String table_id, String form_id,
			String title, String row_num, String xssx, User user)
			throws Exception {

		// ����
		String tableName = "xg_custom_table";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_table ");
		sql.append("(");
		sql.append("table_id,form_id,title,");
		sql.append("row_num,xssx");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { table_id, form_id, title, row_num, xssx };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * �������ݣ�xg_custom_content:�Զ��x��΃��ݱ�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean insertCustomContent(String table_id, String[] row,
			String[] column, User user) throws Exception {

		// ����
		String tableName = "xg_custom_content";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_content ");
		sql.append("(");
		sql.append("table_id,row_num,column_num");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < column.length; i++) {
			String[] value = new String[] { table_id, row[i], column[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================ִ�����Ӳ��� end==============================
	
	// ==================ִ���޸Ĳ��� begin==============================
	/**
	 * �������ݣ�xg_custom_content:�Զ��x��΃��ݱ�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean updateCustomContent(String[] table_id, String[] row,
			String[] column, String[] rowspan, String[] colspan, User user)
			throws Exception {

		// ����
		String tableName = "xg_custom_content";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_content ");
		sql.append("(");
		sql.append("table_id,row_num,column_num");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < column.length; i++) {
			String[] value = new String[] { table_id[i], row[i], column[i] };
			params.add(value);
		}

		boolean flag = true;// saveArrDate(sql.toString(), params, tableName,
							// user);

		return flag;
	}
	// ==================ִ���޸Ĳ��� begin==============================
}
