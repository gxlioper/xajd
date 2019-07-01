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
		String[] cn = {"pk", "类别", "奖项名称", "综测排名班级前百分比(%)"};
		return dao.queryTitle(en, cn);
	}
	
	/**
	 * 查询综合素质测评排名比例信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryResult(CsszModel model) throws Exception {
		return dao.queryResult(model);
	}
	
	/**
	 * 查询奖学金，荣誉称号代码
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
	 * 保存综合测评排名比例设置信息
	 * @param model
	 * @return
	 */
	public boolean saveZhszcpblxx(CsszModel model) {
		return dao.deleteZhszcppmBlf(model) ? dao.saveZhszcppmBlf(model) : false;  
	}
	
	/**
	 * 删除综测排名比例设置信息
	 * @param cbv
	 * @return
	 * @throws Exception
	 */
	public boolean deleteZhszcppmblf(String[] cbv) throws Exception {
		return cbv == null ? false : dao.deleteZhszcppmBlf(appendBatchSql(cbv,
				"zhszpmbfbb", "lb||dm"));
	}

	/**
	 * 拼接批处理SQL语句执行
	 * @param cbv  主键数组值
	 * @param tableName  表名
	 * @param pkzd       主键字段 格式:a||b
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
	 * 保存开关维护信息
	 * @param model
	 * @return
	 */
	public boolean saveKgwhxx(CsszModel model) {
		return dao.deleteKgwhxx() ? dao.saveKgwhxx(model) : false;
	}
	
	/**
	 * 查询开关设置信息
	 * @return
	 */
	public HashMap<String, String> queryKgwhxx() {
		return dao.queryKgwhxx();
	}
	
	/**
	 * 查询荣誉称号申请获奖条件信息
	 * @return
	 */
	public List<HashMap<String, String>> queryRychsqtjxx() {
		return dao.queryRychsqtjxx();
	}
	
	/**
	 * 查询荣誉称号申请获奖条件中的德育成绩信息
	 * @return
	 */
	public String queryRychsqdycjtj() {
		return dao.queryRychsqdycjtj();
	}
	
	/**
	 * 保存荣誉称号申请条件设置信息
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
