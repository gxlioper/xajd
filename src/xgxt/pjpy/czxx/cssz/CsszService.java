package xgxt.pjpy.czxx.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.pjpy.PjpyFinalUtils;
import xgxt.pjpy.czxx.PjpyDAO;

public class CsszService {

	CsszDAO dao = new CsszDAO();
	
	public List<HashMap<String, String>> queryTitle() {
		String[] en = {"pk", "lbmc", "mc", "bfb"};
		String[] cn = {"pk", "���", "��������", "�۲������༶ǰ�ٷֱ�(%)"};
		return dao.queryTitle(en, cn);
	}
	
	/**
	 * ��ѯ�ۺ����ʲ�������������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryResult(CsszModel model) throws Exception {
		return dao.queryResult(model);
	}
	
	/**
	 * ��ѯ��ѧ�������ƺŴ���
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> queryDmList(String lx) {
		PjpyDAO dao = new PjpyDAO();
		if (PjpyFinalUtils.LX_JXJ.equalsIgnoreCase(lx)) {
			return dao.queryJxjdmList();
		} else if (PjpyFinalUtils.LX_RYCH.equalsIgnoreCase(lx)) {
			return dao.queryRychdmList();
		}
		return new ArrayList<HashMap<String, String>>();
	}
	
	/**
	 * �����ۺϲ�����������������Ϣ
	 * @param model
	 * @return
	 */
	public boolean saveZhszcpblxx(CsszModel model) {
		return dao.deleteZhszcppmBlf(model) ? dao.saveZhszcppmBlf(model) : false;  
	}
	
	/**
	 * ɾ���۲���������������Ϣ
	 * @param cbv
	 * @return
	 * @throws Exception
	 */
	public boolean deleteZhszcppmblf(String[] cbv) throws Exception {
		return cbv == null ? false : dao.deleteZhszcppmBlf(appendBatchSql(cbv,
				"zhszpmbfbb", "lb||dm"));
	}

	/**
	 * ƴ��������SQL���ִ��
	 * @param cbv  ��������ֵ
	 * @param tableName  ����
	 * @param pkzd       �����ֶ� ��ʽ:a||b
	 * @return
	 */
	private String[] appendBatchSql(String[] cbv, String tableName, 
									String pkzd) {
		String[] sqlArr = new String[cbv.length];
		for (int i = 0; i < sqlArr.length; i++) {
			StringBuffer sql = new StringBuffer("delete from ");
			sql.append(tableName);
			sql.append(" where ");
			sql.append(pkzd);
			sql.append("='");
			sql.append(cbv[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		return sqlArr;
	}
	
	/**
	 * ���濪��ά����Ϣ
	 * @param model
	 * @return
	 */
	public boolean saveKgwhxx(CsszModel model) {
		return dao.deleteKgwhxx() ? dao.saveKgwhxx(model) : false;
	}
	
	/**
	 * ��ѯ����������Ϣ
	 * @return
	 */
	public HashMap<String, String> queryKgwhxx() {
		return dao.queryKgwhxx();
	}
	
	/**
	 * ��ѯ�����ƺ������������Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> queryRychsqtjxx() {
		return dao.queryRychsqtjxx();
	}
	
	/**
	 * ��ѯ�����ƺ�����������еĵ����ɼ���Ϣ
	 * @return
	 */
	public String queryRychsqdycjtj() {
		return dao.queryRychsqdycjtj();
	}
	
	/**
	 * ���������ƺ���������������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveRychsqtjxx(CsszModel model) throws Exception{
		if (dao.deleteRychsqtjxx()) {
			if (model.getChkonexy() == null || model.getChkonexy().length <= 0) {
				return dao.saveRychsqtjDycjxx(model);
			} else {
				String[] sqlArr = new String[model.getChkonexy().length];
				for (int i=0;i<sqlArr.length;i++) {
					StringBuffer sql = new StringBuffer("insert into czxx_rychtjszb");
					sql.append("(dm,cj) values ('");
					sql.append(model.getChkonexy()[i]);
					sql.append("','");
					sql.append(model.getCj());
					sql.append("')");
					sqlArr[i] = sql.toString();
				}
				PjpyDAO dao = new PjpyDAO();
				return dao.excuteSqlResult(sqlArr);
			}
		} else {
			return false;
		}
	}
}
