package xgxt.action.zgkd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.sql.CLOB;

import xgxt.DAO.DAO;
import xgxt.DAO.DBPool;
import xgxt.utils.CustomException;
import xgxt.utils.String.StringUtils;

public class SyltDao {
	private DAO dao = DAO.getInstance();

	private static SyltDao syltDao;

	// �õ�Dao��ʵ������
	public static SyltDao getSyltDao() {
		if (syltDao == null) {
			syltDao = new SyltDao();
		}
		return syltDao;
	}

	/**
	 * �õ�������������Ϣ
	 */
	public List<HashMap<String, String>> getTalksInfo() {
		// String sql = "select glym,nc,tzsl,bk,bkms from view_talks"; //1
		String sql = "select tzsl,bkmc,bkms,bkmccount from view_talks order by to_number(xssx)";
		// return dao.getList(sql, new String[]{},new
		// String[]{"glym","nc","tzsl","bk","bkms"}); //1
		List<HashMap<String, String>> result = dao.getList(sql,
				new String[] {}, new String[] { "tzsl", "bkmc", "bkms",
						"bkmccount" });
		sql = "select nc,bkmc from view_sylt_bkglypp order by bkmc";
		List<HashMap<String, String>> ncList = dao.getList(sql,
				new String[] {}, new String[] { "nc", "bkmc" });
		String subStr = "";
		for (int i = 0; i < result.size(); i++) {
			HashMap<String, String> map = result.get(i);
			int bkmcCount = Integer.parseInt(map.get("bkmccount")); // get
			// bkmc's
			// glrs
			if (bkmcCount > 0) {
				scan: {
					for (int j = 0; j < ncList.size(); j++) {
						if (map.get("bkmc").equalsIgnoreCase(
								ncList.get(j).get("bkmc"))) {
							List<HashMap<String, String>> subNcList = ncList
									.subList(j, j + bkmcCount);
							for (int k = 0; k < subNcList.size(); k++) {
								subStr += (StringUtils.isNull(subNcList.get(k)
										.get("nc")) ? "" : ((k == subNcList
										.size() - 1) ? subNcList.get(k).get(
										"nc") : subNcList.get(k).get("nc")
										+ "/"));
							}
							map.put("glry", subStr);
							subStr = "";
							break scan;
						}
					}
				}
			} else {
				map.put("glry", "���޹�����Ա");
			}
		}
		return result;
	}

	/**
	 * ������̳������ע��
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getTalksCN() {
		String[] inputValue = { "glym", "nc", "tzsl", "bkmc" }; // û�н�bkmsȡ��
		String[] colListCN = dao.getColumnNameCN(inputValue, "view_talks");
		return dao.arrayToList(inputValue, colListCN);
	}

	/**
	 * �����û���Ȩ��
	 * 
	 * @param userName
	 * @return String
	 */
	public String checkUserPower(String userName) {
		String sql = "select yhjb from view_sylt_bkglypp where glym=?";
		String[] powerS = dao.getOneRs(sql, new String[] { userName },
				new String[] { "yhjb" });
		if (powerS == null) {
			return "";
		} else {
			return (powerS[0] == null ? "" : powerS[0]);
		}

	}

	/**
	 * �����û��Ĳ���Ȩ�� true ��ʾ���� false ������
	 * 
	 * @param userName
	 * @param bkmc
	 * @return boolean
	 */
	public boolean checkUserOperatePower(String userName, String bkmc) {
		String sql = "select glym from view_sylt_bkglypp where GLYM = ? and BKMC=?";
		String[] result = dao.getOneRs(sql, new String[] { userName, bkmc },
				new String[] { "glym" });
		if (result == null) {
			return false;
		} else {
			return (StringUtils.isNull(result[0]) ? false : true);
		}
	}

	/**
	 * ����where��������������������������
	 * 
	 * @param tableName
	 * @param columns
	 * @param values
	 * @return String
	 */
	public String getTotalRsNumByEqual(String tableName, String[] columns,
			String[] values) {
		String[] rs = null;
		StringBuffer sqlBf = new StringBuffer();
		sqlBf.append("select count(*) num from ");
		sqlBf.append(tableName);
		sqlBf.append(" where 1=1 ");
		for (int i = 0; i < columns.length; i++) {
			if (!StringUtils.isNull(values[i])) {
				sqlBf.append(" and ");
				sqlBf.append(columns[i]);
				sqlBf.append("='");
				sqlBf.append(values[i] + "'");
			}
		}
		rs = dao.getOneRs(sqlBf.toString(), new String[] {},
				new String[] { "num" });
		return (rs == null) ? "0" : rs[0];
	}

	/**
	 * �����ӵ��������������������1
	 * 
	 * @param tableName
	 * @param updateCol
	 * @param updateVal
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addLllOrPll(String tableName, String updateCol, String pk,
			String pkValue) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");
		sb.append(updateCol);
		sb.append("=");
		sb.append("to_number(" + updateCol + ") + 1");
		sb.append(" where ");
		sb.append(pk);
		sb.append("='");
		sb.append(pkValue + "'");
		return dao.runUpdate(sb.toString(), new String[] {});
	}

	/**
	 * �����ۼ�1
	 * 
	 * @param tableName
	 * @param updateCol
	 * @param pk
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean minusLll(String tableName, String updateCol, String pk,
			String pkValue) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(tableName);
		sb.append(" set ");
		sb.append(updateCol);
		sb.append("=");
		sb.append("to_number(" + updateCol + ") - 1");
		sb.append(" where ");
		sb.append(pk);
		sb.append("='");
		sb.append(pkValue + "'");
		return dao.runUpdate(sb.toString(), new String[] {});
	}

	/**
	 * ����û����˺�û��ͨ���������б�
	 * 
	 * @param bkmc
	 * @return
	 */
	public List<HashMap<String, String>> getNoPassNoteList(String bkmc) {
		String sql = "select rowid rid, a.*,(case a.pass when '0' then 'δ���' when '2' then '��ͨ��' end) status,"
				+ " (case a.pass when '0' then '#99CCFF' when '2' then '#FF9999' end) color"
				+ " from syltb a where bk=?"
				+ " and (pass='0' or pass='2') and type='����'  order by pass,fbsj desc";
		String[] output = new String[] { "rid", "bt", "fbrnc", "fbsj",
				"status", "color" };
		return dao.getArrayList2(sql, new String[] { bkmc }, output);
		// return dao.getList(sql, new String[]{bkmc},output);
	}

	/**
	 * ������ͨ��
	 * 
	 * @param bt
	 * @return
	 */
	public boolean setNotePass(String rid) {
		try {
			String sql = "update syltb set pass='1' where rowid=?";
			return dao.runUpdate(sql, new String[] { rid });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * �����Ӳ�ͨ��
	 * 
	 * @param bt
	 * @return
	 */
	public boolean setNoteNoPass(String rid) {
		try {
			String sql = "update syltb set pass='2' where rowid=?";
			return dao.runUpdate(sql, new String[] { rid });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ɾ������
	 * 
	 * @param rid
	 * @return
	 */
	public boolean delNoPassNote(String rid) {
		try {
			String sql = "delete from  syltb where rowid=? and type='����'";
			return dao.runUpdate(sql, new String[] { rid });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<HashMap<String, String>> rsToVator(String sql,
			String[] inputValue, String[] outputValue) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// ArrayList<String[]> vector = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> vector = new ArrayList<HashMap<String, String>>();
		try {
			conn = DBPool.getPool().getConnection();
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < inputValue.length; i++) {
				stmt.setString(i + 1, inputValue[i]);
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				// String[] tmp = new String[outputValue.length];
				HashMap<String, String> tmp = new HashMap<String, String>();
				for (int i = 0; i < outputValue.length; i++) {
					String tempstr = rs.getString(outputValue[i]);
					tmp.put(outputValue[i], (((tempstr != null)
							&& (outputValue[i].contains("fbsj")) && (tempstr
							.length() == 14)) ? tempstr.substring(0, 4) + "��"
							+ tempstr.substring(4, 6) + "��"
							+ tempstr.substring(6, 8) + "�� "
							+ tempstr.substring(8, 10) + ":"
							+ tempstr.substring(10, 12) + ":"
							+ tempstr.substring(12, 14) : tempstr));
					// �޸Ŀ�ʼ
					if ((outputValue[0].contains("nr")) && (tempstr != null)) {
						DAO dao = DAO.getInstance();
						String rowid = rs.getString(0);
						CLOB clob = dao.getOneClob(sql, new String[] { rowid },
								"nr");
						String nr = clob.getSubString(1L, (int) clob.length());
						tmp.put("nr", nr);
					}
					// �޸Ľ���
				}
				vector.add(tmp);
				tmp = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CustomException.pringCustomExcInfo(sql);
			vector = null;
		} finally {
			dao.closeAllStmtAndRs(rs, stmt, null, null);
			dao.closeConnection(conn);
		}
		return vector;
	}

	/**
	 * ���ݰ�����Ĵ�������˳����ʾ�����ҳ���ϵ�˳��
	 * 
	 * @param splitStr
	 * @return
	 */
	public boolean bkSortSave(String splitStr) {
		if (StringUtils.isNull(splitStr)) {
			return true;
		}
		String[] tempStrArray = splitStr.split("-");
		String sql = "";
		String[] sqlArray = new String[tempStrArray.length];
		for (int i = 0; i < tempStrArray.length; i++) {
			sql = "update sylt_bkb set xssx = '" + i + "' where bkdm = '"
					+ tempStrArray[i] + "'";
			sqlArray[i] = sql;
		}
		try {
			int[] result = dao.runBatch(sqlArray);
			return dao.checkBatch(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �������ӵĵȼ�
	 * @param rowid
	 * @return
	 */
	public String getNoteDj(String rowid) {
		if(StringUtils.isNull(rowid)){
			return "0";
		}
		String sql = "select dj from syltb where rowid = '" + rowid + "'";
		String[] djArray = dao.getOneRs(sql, new String[]{}, new String[]{"dj"});
		if(djArray == null){
			return "0";
		}else{
			return djArray[0];
		}
	}

}
