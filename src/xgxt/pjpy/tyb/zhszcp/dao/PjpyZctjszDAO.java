package xgxt.pjpy.tyb.zhszcp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.utils.String.StringUtils;

public class PjpyZctjszDAO {

	DAO dao = DAO.getInstance();

	/**
	 * ��ѯ�۲���������ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryEjTitle() {
		return dao.getList("select dm en,mc cn from zhszcpdmb order by dm",
				new String[] {}, new String[] { "en", "cn" });
	}

	/**
	 * ͨ���۲���������ѯ�۲���Ϣ
	 * 
	 * @param dm
	 * @return
	 */
	public HashMap<String, String> queryZcejdmxx(String dm) {
		return dao
				.getMapNotOut(
						"select a.mc,a.dm,b.bl,b.lb,b.mrf,b.xzf,b.bm,b.zd,b.sfplzj,b.shjb,b.fdm,b.tj from zhszcpdmb a left "
								+ "join pjpy_zctjszb b on a.dm=b.dm where a.dm=?",
						new String[] { dm });
	}

	/**
	 * ����2���۲��������������Ϣ ��ɾ��������
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean save2jZhszcpdmxx(PjpyZctjszModel model) throws Exception {
		boolean result = dao.runUpdate("delete from pjpy_zctjszb where dm=?",
				new String[] { model.getDm() });
		return result ? dao
				.runUpdate(
						"insert into pjpy_zctjszb(dm,mc,bl,xzf,lb,bm,zd,fdm,tj,sfplzj,shjb,dmjb) values (?,?,?,?,?,?,?,?,?,?,?,?)",
						new String[] { model.getDm(), model.getMc(),
								model.getBl(), model.getXzf(), model.getLb(),
								model.getBm(), model.getZd(), model.getFdm(),
								model.getTj(), model.getSfplzj(),
								model.getShjb(), model.getJb() })
				: false;
	}

	/**
	 * ͨ�����������ѯ�����Ӵ�����Ϣ
	 * 
	 * @param dm
	 * @return
	 */
	public List<HashMap<String, String>> query3jZcdm(String dm) {
		return dao.getList("select dm,mc from pjpy_zctjszb where fdm=?",
				new String[] { dm }, new String[] { "dm", "mc" });
	}

	/**
	 * ������ݿ�����Ƿ���� ���ڷ���TRUE����֮FALSE
	 * 
	 * @param dm
	 * @return
	 */
	public boolean checkDmDataUnion(String dm) {
		if (StringUtils.isNull(dm)) {
			return false;
		} else {
			String count = dao.getOneRs(
					"select count(dm) cnt from pjpy_zctjszb where dm=?",
					new String[] { dm }, "cnt");
			return "0".equalsIgnoreCase(count) || StringUtils.isNull(count) ? false
					: true;
		}
	}

	/**
	 * ͨ�����뼶���ѯ����������Ϣ
	 * 
	 * @param dmjb
	 *            ��Ϊ1��2��3��4��
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryZcsjdmxx(String dmjb, boolean isSfwh)
			throws Exception {
		String whereSql = isSfwh ? " and sfwh='1'" : "";
		return dao.getList("select bm dm,mc from pjpy_zctjszb where dmjb = ?" + whereSql,
				new String[] { dmjb }, new String[] { "dm", "mc" });
	}
	
	

	/**
	 * ͨ���������ѯ�Ӵ�����Ϣ
	 * 
	 * @param dmlb
	 * @param dm
	 * @return
	 */
	public List<HashMap<String, String>> queryZcdmxxList(String dm) {
		//���ڲ���Ҫά��������ֻ�ܽ��в�ѯ
		String[] dmArray = StringUtils.isNotNull(dm) ? dm.split("!@") : new String[]{};
		StringBuilder sql = new StringBuilder("select dm,mc,bm,sfwh,shjb from pjpy_zctjszb where 1=1 and sfwh='1'");
		if (dmArray != null && dmArray.length > 0) {
			sql.append(" and fdm in (");
			for (int i=0;i<dmArray.length;i++) {
				sql.append("'");
				sql.append(dmArray[i]);
				sql.append("',");
			}
			sql.delete(sql.length() - 1, sql.length());
			sql.append(") order by dm asc");
		}
		return dao
				.getList(
						//"select dm,mc,bm from pjpy_zctjszb where fdm=? and sfwh='1' order by dm asc",
						sql.toString(),
						new String[] {}, new String[] {
								"dm", "mc", "bm", "sfwh", "shjb" });
	}
	
	/**
	 * ͨ��������ͱ�����ѯ��˼���
	 * 
	 * @param dm
	 * @param tableName
	 * @returnString
	 */
	public String queryZhszcpxmShjb(String dm,String tableName) {
		return dao
				.getOneRs(
						"select shjb from pjpy_zctjszb where fdm=? and bm=?  order by dm asc",
						new String[] {dm,tableName },"shjb");
	}
	
	/**
	 * ͨ��������ѯ������ϸ��Ϣ
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpxm(String tableName) {
		return dao
				.getList(
						"select zdid,zdmc,tabname,zdlx,zdcd,cxxs,cxxspx,zdpx,nvl(sfnum,'') sfnum,xzf,"
								+ "nvl(sfnull,'') sfnull,(case when sfnull='0' then zdmc||'" +
										"<font color=red>*</font>' else zdmc end ) sfnullmc from py_bdszb a where tabname=? order by zdpx",
						new String[] { tableName },
						new String[] { "zdid", "zdmc", "tabname", "zdlx",
								"zdcd", "cxxs", "cxxspx", "zdpx", "sfnum","xzf", 
								"sfnull", "sfnullmc" });
	}
	
	/**
	 * ��ѯ�۲������Ϣ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpDmList() {
		return dao.getList(
				"select dm,mc,dmjb from pjpy_zctjszb order by dmjb,dm asc",
				new String[] {}, new String[] { "dm", "mc", "dmjb" });
	}
	
	/**
	 * ��ѯ�ۺϲ�����Ŀ��Ϣ
	 * @param jb
	 * @param dm
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryZhcpXmxx(String jb, String[] dm){
		String[] input = new String[]{jb};
		String sql = "select dm,mc,dmjb,fdm from pjpy_zctjszb where dmjb=?";
		
		if(dm != null && dm.length > 0){
			sql = "select dm,mc,dmjb,fdm from pjpy_zctjszb where dmjb=? and dm in ('";
			for (int i=0;i<dm.length;i++) {
				sql += dm[i];
				sql += "','";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += ")";
			input = new String[]{jb};
		}
		return dao.getList(sql, 
						  input, 
				          new String[]{"dm", "mc", "dmjb", "fdm"});
	}
	
	/**
	 * �޸ĸ������������˼�����Ϣ
	 * @param dmArray
	 * @param bmArray
	 * @param fdmArray
	 * @return
	 */
	public boolean updateFdmbmAndShjb(PjpyZhszcpModel model) {
		String[] sqlArr = new String[model.getFdm().length];
		for (int i = 0; i < model.getFdm().length; i++) {
			StringBuilder sql = new StringBuilder(
					"update pjpy_zctjszb set (bm,shjb,sfwh)=(select bm,shjb,sfwh from pjpy_zctjszb where dm='");
			sql.append(model.getDm()[i]);
			sql.append("' and bm='");
			sql.append(model.getBm()[i]);
			sql.append("' and rownum='1') where dm='");
			sql.append(model.getFdm()[i]);
			sql.append("'");

			sqlArr[i] = sql.toString();
		}
		try {
			return dao.checkBatch(dao.runBatch(sqlArr));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * �۲��������ü���ѯ���
	 * @return
	 */
	public List<String[]> queryZctjjcResult() {
		return dao
				.rsToVator(
						"select msg,dm,mc,bm,zd,fmc,sfwh,shjb,tj from pjpy_zctjjcb order by msg,dm",
						new String[] {}, new String[] { "msg", "dm", "mc",
								"bm", "zd", "fmc", "sfwh", "shjb", "tj" });
	}
	
	/**
	 * ִ���۲������������
	 * @return
	 * @throws Exception
	 */
	public boolean zctjSjCheckResult() throws Exception{
		return dao.runProcuder("{call PROC_PJPY_ZCTJPZJC()}", new String[]{});
	}
	
	/**
	 * ����һ����������Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean insertSjjcErrorMsg(Map<String, String> map) throws Exception{
		return dao.insert("insert into pjpy_zctjjcb (msg) values (?)", new String[]{map.get("msg")});
	}
	
	/**
	 * ɾ���۲����ü����Ϣ���е���������
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAllZcsjjcResult() throws Exception{
		return dao.runUpdate("delete from pjpy_zctjjcb", new String[]{});
	}
	
	/**
	 * ��ѯ����Ҫ��������ά���Ĵ�����Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> query3jwhdmxx() {
		return dao.getList(
						"select dm,bm,shjb,sfwh,fdm from pjpy_zctjszb where dmjb='3' and sfwh='1' order by dm",
						new String[] {}, new String[] { "dm", "bm", "shjb",
								"sfwh", "fdm" });
	}
	
	/**
	 * �޸Ķ����۲������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update2jZcdmxx(PjpyZctjszModel model) throws Exception{
		return dao.runUpdate(
				"update pjpy_zctjszb set bl=?,xzf=?,mrf=?,lb=? where dm=?",
				new String[] { model.getBl(), model.getXzf(), model.getMrf(),model.getLb(),
						model.getDm() });
	}
	
	/**
	 * �޸������۲������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update3jZcdmxx(PjpyZctjszModel model) throws Exception{
		String[] sqlArr = new String[model.getEjdm().length];
		StringBuilder sql = new StringBuilder("");
		for (int i=0;i<sqlArr.length;i++) {
			sql = new StringBuilder("");
			sql.append("update pjpy_zctjszb set bl='");
			sql.append(model.getEjbl()[i]);
			sql.append("',xzf='");
			sql.append(model.getEjxzf()[i]);
			sql.append("',mrf='");
			sql.append(model.getEjmrf()[i]);
			sql.append("',lb='");
			sql.append(model.getEjlb()[i]);
			sql.append("' where dm='");
			sql.append(model.getEjdm()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		return dao.checkBatch(dao.runBatch(sqlArr));
	}

	/**
	 * �޸��ļ��۲������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean update4jZcdmxx(PjpyZctjszModel model) throws Exception{
		String[] sqlArr = new String[model.getSjdm().length];
		StringBuilder sql = new StringBuilder("");
		for (int i=0;i<sqlArr.length;i++) {
			sql = new StringBuilder("");
			sql.append("update pjpy_zctjszb set bl='");
			sql.append(model.getSjbl()[i]);
			sql.append("',xzf='");
			sql.append(model.getSjxzf()[i]);
			sql.append("',mrf='");
			sql.append(model.getSjmrf()[i]);
			sql.append("',lb='");
			sql.append(model.getSjlb()[i]);
			sql.append("' where dm='");
			sql.append(model.getSjdm()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		return dao.checkBatch(dao.runBatch(sqlArr));
	}
}
