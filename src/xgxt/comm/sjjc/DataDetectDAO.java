package xgxt.comm.sjjc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;

public class DataDetectDAO extends CommDAO {

	/**
	 * 根据学校代码、模块类型 获取异常数据检测基本信息
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getSjjcByXxMk(DataDetectForm myForm) {

		DAO dao = DAO.getInstance();

		String mklx = myForm.getMklx();
		String xxdm = myForm.getXxdm();

		StringBuilder sql = new StringBuilder();
		sql
				.append(" select xxdm, gnmklx,jcmkdm,jcmkmc, yclxdm, yclxmc,method,tablename,zd, ");
		sql.append(" searchtj,pdlxone,pdlxtwo,pdzone,pdztwo,xszd,pk ");
		sql.append(" from xg_comm_sjjcb ");
		// 根据学校代码获取相应学校的检测数据
		sql.append(" where (xxdm = 'all' ");
		sql.append(" or xxdm = ? ) ");
		// 根据模块获取该模块的检测数据
		sql.append(" and gnmklx = ? ");
		sql.append(" order by yclxdm ");
		return dao
				.getList(sql.toString(), new String[] { xxdm, mklx },
						new String[] { "xxdm", "gnmklx", "jcmkdm", "jcmkmc",
								"yclxdm", "yclxmc", "method", "tablename",
								"zd", "searchtj", "pdlxone", "pdlxtwo",
								"pdzone", "pdztwo", "xszd", "pk" });

	}

	/**
	 * 根据学校代码、模块类型 获取需检测模块信息
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getJcmkList(DataDetectForm myForm) {

		DAO dao = DAO.getInstance();

		String mklx = myForm.getMklx();
		String xxdm = myForm.getXxdm();

		StringBuilder sql = new StringBuilder();
		sql.append(" select jcmkdm,jcmkmc ");
		sql.append(" from xg_comm_sjjcb ");
		// 根据学校代码获取相应学校的检测数据
		sql.append(" where (xxdm = 'all' ");
		sql.append(" or xxdm = ? ) ");
		// 根据模块获取该模块的检测数据
		sql.append(" and gnmklx = ? ");
		sql.append(" group by jcmkdm,jcmkmc ");

		return dao.getList(sql.toString(), new String[] { xxdm, mklx },
				new String[] { "jcmkdm", "jcmkmc" });

	}

	/**
	 * 根据学校代码、模块类型 获取需检测模块信息
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getJcxx(DataDetectForm myForm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql
				.append(" select xxdm, gnmklx,jcmkdm,jcmkmc, yclxdm, yclxmc,method,tablename,zd, ");
		sql.append(" searchtj,pdlxone,pdlxtwo,pdzone,pdztwo,xszd,pk ");
		sql.append(" from xg_comm_sjjcb where 1=1 ");

		// 根据异常类型代码获取
		if (!Base.isNull(myForm.getYclxdm())) {
			sql.append(" and yclxdm=? ");
		}

		String[] colList = { "xxdm", "gnmklx", "jcmkdm", "jcmkmc", "yclxdm",
				"yclxmc", "method", "tablename", "zd", "searchtj", "pdlxone",
				"pdlxtwo", "pdzone", "pdztwo", "xszd", "pk" };

		return dao.getMap(sql.toString(), new String[] { myForm.getYclxdm() },
				colList);

	}

	/**
	 * 获取检测表详细信息(主键和检测字段)
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, String>> getTableZd(DataDetectForm myForm) {
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append(" select ");

		ArrayList<String> input = new ArrayList<String>();

		if (!Base.isNull(myForm.getPk())) {
			sql.append(myForm.getPk() + " pkValue,");
			input.add("pkValue");
		}

		sql.append(myForm.getZd() + " from " + myForm.getTableName() + " ");
		input.add(myForm.getZd());
		return dao.getList(sql.toString(), new String[] {}, input
				.toArray(new String[] {}));

	}

	/**
	 * 获取异常数据数(统计通过XML配置SQL类型检测的异常数据数)
	 * 
	 * @param myForm
	 * @return
	 * @throws SQLException
	 */
	public int getCounts(DataDetectForm myForm) throws SQLException {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append(" select count(1)num from( ");
		sql.append(myForm.getSql());
		sql.append(")");

		return dao.getOneRsint(sql.toString());
	}

	/**
	 * 获取异常数据数
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public void getYcsjList(DataDetectForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();
		String pk = myForm.getPk();

		sql.append(" select rownum r,");
		sql.append(pk);
		sql.append(" pkValue,");
		sql.append(" a.* from( ");
		sql.append(myForm.getSql());
		sql.append(" )a ");

		myForm.setXszd("pkValue," + myForm.getXszd());
		String[] colList = myForm.getXszd().split(",");
		myForm.setColList(colList);
		myForm.setRs(CommonQueryDAO.commonQuery(sql.toString(), "",
				new String[] {}, colList, myForm));
	}

	/**
	 * 获取异常数据数
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public void getYcsjxxList(DataDetectForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();
		String pk = myForm.getPk();

		sql.append(" select rownum r, ");
		sql.append(pk);
		sql.append(" pkValue,");
		sql.append(" a.* from  ");
		sql.append(myForm.getTableName());
		sql.append(" a where 1=1 ");
		for (int i = 0; i < myForm.getPkValue().size(); i++) {
			HashMap<String, String> pkMap = myForm.getPkValue().get(i);
			if (i == 0) {

				sql.append(" and ( ");

			} else {

				sql.append(" or ");
			}
			sql.append(pk);
			sql.append(" = ");
			sql.append("'" + pkMap.get("pkValue") + "'");
		}

		if (myForm.getPkValue().size() > 0) {
			sql.append(" )");
		}

		myForm.setXszd("pkValue," + myForm.getXszd());
		String[] colList = myForm.getXszd().split(",");
		myForm.setColList(colList);
		myForm.setRs(CommonQueryDAO.commonQuery(sql.toString(), "",
				new String[] {}, colList, myForm));
	}

	/**
	 * 删除异常数据
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delData(DataDetectForm myForm) throws Exception {
		String[] pkValue = myForm.getPrimarykey_pkValue();
		String[] sql = new String[pkValue.length];
		
		for (int i = 0; i < sql.length; i++) {
			
			sql[i] = "delete from " + myForm.getTableName() + " where "
					+ myForm.getPk() + " = '" + pkValue[i] + "'";
		}

		boolean blog = saveArrDate(sql);
		return blog;
	}
}
