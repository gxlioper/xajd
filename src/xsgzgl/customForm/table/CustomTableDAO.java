package xsgzgl.customForm.table;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xsgzgl.customForm.model.TableModel;
import xsgzgl.customForm.model.TablePkContentModel;
import xsgzgl.customForm.model.TableSearchContentModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class CustomTableDAO extends CommDAO {

	/**
	 * ��ù���ģ��list
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getGnmkList() {
		
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select gnmkdm,gnmkmc from gnmkdmb ");
		sql.append("where length(gnmkdm) = '3' ");
		sql.append("order by to_number(xssx) ");
		  
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "gnmkdm", "gnmkmc" });

		return list;
	}
	
	/**
	 * ��ý����(����ģ��)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getCustomGnmkList(CustomTableForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		MakeQuery myQuery = new MakeQuery();
	
		String[] queryList = new String[] { "shzt" };
		String[] queryLikeList = new String[] { "gnmkdm", "gnmkmc" };
		
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String query = myQuery.getQueryString();
		String[] inputV = myQuery.getInputList();
		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from (");
		tableSql.append("select a.id,a.gnmkdm,a.gnmkmc,a.dyym");
		tableSql.append(",a.shzt,a.create_time from xg_custom_gnmkb a ");
		tableSql.append(query);
		tableSql.append(" order by a.create_time ) a where 1=1 ");
		
		// ====================SQLƴװ end================================

		String[] colList = new String[] { "id", "gnmkdm", "gnmkmc", "shzt",
				"create_time" };
		
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * ������ֶ�
	 * 
	 * @author ΰ�����
	 */
	public Boolean checkTableName(String tablename, String dm, String mc,
			CustomTableForm model) {

		StringBuilder sql = new StringBuilder();
		sql.append("(");
		sql.append("select ");
		sql.append(dm + " dm, ");
		sql.append(mc + " mc ");
		sql.append(" from ");
		sql.append(tablename);
		sql.append(")");

		boolean flag = true;

		try {
			ArrayList<String[]> list = CommonQueryDAO.commonQuery(sql
					.toString(), "", new String[] {},
					new String[] { "dm", "mc" }, "", model);
		} catch (IllegalArgumentException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		} catch (SecurityException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		} catch (IllegalAccessException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		} catch (InvocationTargetException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		} catch (NoSuchMethodException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	/**
	 * �������Դlist
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getListBySource(String tablename,
			String dm, String mc, CustomTableForm model) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append(dm + " dm, ");
		sql.append(mc + " mc ");
		sql.append(" from ");
		sql.append(tablename);

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	/**
	 * ��ñ�Id list
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getTableIdList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select id, title,row_all from xg_custom_table ");
		sql.append("where create_time = ");
		sql.append("(select max(create_time) from xg_custom_table) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "id", "title","row_all" });

		return list;
	}
	
	/**
	 * ��ñ�Id list
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getTableList(String gnmk_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.id table_id,a.gnmk_id,a.title, ");
		sql.append("a.row_all ");
		sql.append("from xg_custom_table a ");
		sql.append("where a.gnmk_id = ? ");
		sql.append("order by a.id ");
	   
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gnmk_id }, new String[] { "table_id", "gnmk_id",
						"title", "row_all" });

		return list;
	}
	
	/**
	 * ��ñ�Id list
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getTableContentList(String gnmk_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.colspan from ( ");
		sql.append("select b.id,b.table_id,b.row_num,b.column_num, ");
		sql.append("b.content,b.width,b.textarea_rows,b.postfix, ");
		sql.append("b.source_table,b.select_dm,b.select_mc,b.checked ");
		sql.append("from xg_custom_table a, xg_custom_table_content b ");
		sql.append("where a.id = b.table_id ");
		sql.append("and a.gnmk_id = ? ");
		sql.append("order by b.table_id,to_number(b.row_num),to_number(b.column_num))a ");
		
		sql.append("left join (select '3' colspan,table_id, row_num,'2' column_num ");
		sql.append("from xg_custom_table_content ");
		sql.append("where column_num = '3' ");
		sql.append("and content = 'colspan') b on a.table_id = b.table_id ");
		sql.append("and a.row_num = b.row_num ");
		sql.append("and a.column_num = b.column_num ");
	   
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gnmk_id }, new String[] { "id", "table_id",
						"row_num", "column_num", "content", "width",
						"textarea_rows", "postfix", "source_table",
						"select_dm", "select_mc", "checked", "colspan" });

		return list;
	}

	/**
	 * ɾ���ϱ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteOldTable(CustomTableForm model) throws Exception {

		TableModel tableModel = model.getTableModel();

		// ����ģ��ID
		String gnmk_id = tableModel.getGnmk_id();

		String sql = "delete from xg_custom_table where gnmk_id = ? ";

		DAO dao = DAO.getInstance();
		
		boolean flag = dao.runUpdate(sql, new String[]{gnmk_id});
			
		return flag;
	}

	/**
	 * ɾ���ϱ���������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteOldTableContent(CustomTableForm model) throws Exception {

		String sql = "delete from xg_custom_table_content a "
				+ "where not exists (select 1 from xg_custom_table b "
				+ "where b.id = a.table_id)";

		DAO dao = DAO.getInstance();
		
		boolean flag = dao.runUpdate(sql, new String[]{});
			
		return flag;
	}

	// =============��ѯ��ʾ������=====================
	/**
	 * ��ù���ģ������ list
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getGnmkContentList(String gnmk_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xssx from (");
		sql.append("select c.table_id, c.id zd,c.content zdm from ");
		sql.append("xg_custom_gnmkb a ");
		sql.append(",xg_custom_table b ");
		sql.append(",xg_custom_table_content c ");
		sql.append("where a.id = b.gnmk_id ");
		sql.append("and b.id = c.table_id ");
		sql.append("and a.id = ? ");
		sql.append("and (c.column_num = '1' or c.column_num = '3') ");
		sql.append("and c.content <> 'colspan' ");
		sql.append("and trim(c.content) is not null) a ");
		sql.append("left join ");
		
		sql.append("(select c.id content_id, e.xssx ");
		sql.append("from (select c.*, ");
		sql.append("(select d.id ");
		sql.append("from xg_custom_table_content d ");
		sql.append("where c.table_id = d.table_id ");
		sql.append("and c.row_num = d.row_num ");
		sql.append("and d.column_num = (c.column_num + 1)) next_id ");
		sql.append("from xg_custom_table_content c) c ");
		sql.append("left join xg_custom_search_content e on c.next_id = e.content_id) b ");
				  
		sql.append("on a.zd = b.content_id ");
		sql.append("order by a.zd ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gnmk_id }, new String[] { "table_id", "zd",
						"zdm", "xssx" });

		return list;
	}
	
	/**
	 * ɾ����ѯ��ʾ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteTableSearchContent(CustomTableForm model, User user)
			throws Exception {

		TableSearchContentModel tableSearchContentModel = model
				.getTableSearchContentModel();

		// ����ģ��ID
		String gnmk_id = tableSearchContentModel.getGnmk_id();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_custom_search_content ");
		sql.append("where gnmk_id = ? ");

		DAO dao = DAO.getInstance();

		boolean flag = dao.runUpdate(sql.toString(), new String[] { gnmk_id });

		return flag;
	}
	
	/**
	 * �����ѯ��ʾ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveTableSearchContent(CustomTableForm model, User user)
			throws Exception {

		TableSearchContentModel tableSearchContentModel = model
				.getTableSearchContentModel();

		// ����ģ��ID
		String gnmk_id = tableSearchContentModel.getGnmk_id();
		// ����ID
		String[] content_id = tableSearchContentModel.getContent_id();
		// ��ʾ˳��
		String[] xssx = tableSearchContentModel.getXssx();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_search_content");
		sql.append("(gnmk_id,content_id,xssx)");
		sql.append("select '" + gnmk_id + "' gnmk_id,a.id content,rownum xssx ");
		sql.append("from xg_custom_table_content a ");
		sql.append("where exists (select 1 ");
		sql.append("from xg_custom_table_content b ");
		sql.append("where 1=1 ");
		
		if (content_id != null && content_id.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < content_id.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.id=? ");
			}
			sql.append(")");
		}
		
		sql.append("and a.table_id = b.table_id ");
		sql.append("and a.row_num = b.row_num ");
		sql.append("and a.column_num = (b.column_num + 1)) ");
		
		DAO dao = DAO.getInstance();
		
		boolean flag = dao.runUpdate(sql.toString(), content_id);

		return flag;
	}
	// =============��ѯ��ʾ������ end=====================
	
	// =============��������=====================
	/**
	 * ����������� list
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getPkContentList(String gnmk_id) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xssx from (");
		sql.append("select c.table_id, c.id zd,c.content zdm from ");
		sql.append("xg_custom_gnmkb a ");
		sql.append(",xg_custom_table b ");
		sql.append(",xg_custom_table_content c ");
		sql.append("where a.id = b.gnmk_id ");
		sql.append("and b.id = c.table_id ");
		sql.append("and a.id = ? ");
		sql.append("and (c.column_num = '1' or c.column_num = '3') ");
		sql.append("and c.content <> 'colspan') a ");
		sql.append("left join ");
		
		sql.append("(select c.id pk_id, e.xssx ");
		sql.append("from (select c.*, ");
		sql.append("(select d.id ");
		sql.append("from xg_custom_table_content d ");
		sql.append("where c.table_id = d.table_id ");
		sql.append("and c.row_num = d.row_num ");
		sql.append("and d.column_num = (c.column_num + 1)) next_id ");
		sql.append("from xg_custom_table_content c) c ");
		sql.append("left join xg_custom_pk_content e on c.next_id = e.pk_id) b ");
		
		sql.append("on a.zd = b.pk_id ");
		sql.append("order by a.zd ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { gnmk_id }, new String[] { "table_id", "zd",
						"zdm", "xssx" });

		return list;
	}
	
	/**
	 * ɾ����������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deletePkSearchContent(CustomTableForm model, User user)
			throws Exception {

		TablePkContentModel tablePkContentModel = model
				.getTablePkContentModel();

		// ����ģ��ID
		String gnmk_id = tablePkContentModel.getGnmk_id();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_custom_pk_content ");
		sql.append("where gnmk_id = ? ");

		DAO dao = DAO.getInstance();

		boolean flag = dao.runUpdate(sql.toString(), new String[] { gnmk_id });

		return flag;
	}

	/**
	 * ������������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveTablePkContent(CustomTableForm model, User user)
			throws Exception {

		TablePkContentModel tablePkContentModel = model
				.getTablePkContentModel();

		// ����ģ��ID
		String gnmk_id = tablePkContentModel.getGnmk_id();
		// ����ID
		String[] pk_id = tablePkContentModel.getPk_id();
		// ��ʾ˳��
		String[] xssx = tablePkContentModel.getXssx();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_custom_pk_content");
		sql.append("(gnmk_id,pk_id,xssx)");
		sql.append("select '" + gnmk_id + "' gnmk_id,a.id pk_id,rownum xssx ");
		sql.append("from xg_custom_table_content a ");
		sql.append("where exists (select 1 ");
		sql.append("from xg_custom_table_content b ");
		sql.append("where 1=1 ");

		if (pk_id != null && pk_id.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < pk_id.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.id=? ");
			}
			sql.append(")");
		}

		sql.append("and a.table_id = b.table_id ");
		sql.append("and a.row_num = b.row_num ");
		sql.append("and a.column_num = (b.column_num + 1)) ");

		DAO dao = DAO.getInstance();

		boolean flag = dao.runUpdate(sql.toString(), pk_id);

		return flag;
	}
	// =============�������� end=====================
}
