package xgxt.xsgygl.bjlh.cwfp;

import xgxt.DAO.DAO;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;

public class CwfpDAO extends BjlhGyglDAO{
	DAO dao = DAO.getInstance();
	
	/**
	 * 保存床位分配信息
	 * @param oldMappingItems  要删除的已划分宿舍列表字符串
	 * @param newMappingItems  已划分宿舍列表字符串
	 * @return
	 * @throws Exception
	 */
	public boolean saveCwfpxx(String oldMappingItems, String newMappingItems, String fplx)
			throws Exception {
		String[] delSql = null;
		String[] inserSql = null;
		if (((oldMappingItems != null) && !oldMappingItems.equals(""))) {
			//删除语句拼接
			String delItems1[] = oldMappingItems.split(",");
			int delNum = delItems1 != null ? delItems1.length : 0;
			String delItems2[][] = new String[delNum][];
			delSql = new String[delNum];
			for (int i = 0; i < delNum; i++) {
				delItems2[i] = delItems1[i].split("/");
				delSql[i] = "delete from bjlh_xszsxxb where xh='"
						+ delItems2[i][0] + "' and lx='" + fplx
						+ "' and lddm= '" + delItems2[i][1] + "' and cs= '"
						+ delItems2[i][2] + "' and qsh= '" + delItems2[i][3]
						+ "' and cwh= '" + delItems2[i][4] + "' and zzbj='yes'";
			}
		} else {
			delSql = new String[1];
			delSql[0] = "delete from bjlh_xszsxxb where 1=2";
		}
		if (((newMappingItems != null) && !newMappingItems.equals(""))) {
			//插入语句拼接
			String inserItems1[] = newMappingItems.split(",");
			int inNum = inserItems1 != null ? inserItems1.length : 0;
			String inserItems2[][] = new String[inNum][];
			inserSql = new String[inNum];
			for (int i = 0; i < inNum; i++) {
				inserItems2[i] = inserItems1[i].split("/");
				inserSql[i] = "insert into bjlh_xszsxxb(xh,lx,lddm,cs,qsh,cwh,rzrq) values('"
						+ inserItems2[i][0]
						+ "','"
						+ fplx
						+ "','"
						+ inserItems2[i][1]
						+ "','"
						+ inserItems2[i][2]
						+ "','"
						+ inserItems2[i][3]
						+ "','"
						+ inserItems2[i][4]
						+ "','"
						+ inserItems2[i][5] + "') ";
			}
		} else {
			inserSql = new String[1];
			inserSql[0] = "delete from bjlh_xszsxxb where 1=2";
		}
		String[] exesql = dao.unionArray(delSql, inserSql);
		int[] array = null;
		array = dao.runBatch(exesql);
		return dao.checkBatch(array);
	}
	
	/**
	 * 保存行李床位分配信息
	 * @param cwList
	 * @return
	 * @throws Exception
	 */
	public boolean saveXlCwfpxx(String cwList)
			throws Exception {
		String[] delSql = null;
		String[] inserSql = null;
		if (((cwList != null) && !cwList.equals(""))) {
			//删除语句拼接
			String delItems1[] = cwList.split(",");
			int num = delItems1 != null ? delItems1.length : 0;
			String delItems2[][] = new String[num][];
			delSql = new String[num];
			inserSql = new String[num];
			for (int i = 0; i < num; i++) {
				delItems2[i] = delItems1[i].split("/");
				delSql[i] = "delete from bjlh_xlcwb where lddm='"
						+ delItems2[i][0] + "' and cs='" + delItems2[i][1]
						+ "' and qsh= '" + delItems2[i][2] + "' and cwh= '"
						+ delItems2[i][3] + "'";
				inserSql[i] = "insert into bjlh_xlcwb(lddm,cs,qsh,cwh) values('"
					+ delItems2[i][0]
					+ "','"
					+ delItems2[i][1]
					+ "','"
					+ delItems2[i][2]
					+ "','"
					+ delItems2[i][3]
					+ "') ";
			}
		} else {
			delSql = new String[1];
			delSql[0] = "delete from bjlh_xlcwb where 1=2";
		}
		String[] exesql = dao.unionArray(delSql, inserSql);
		int[] array = null;
		array = dao.runBatch(exesql);
		return dao.checkBatch(array);
	}
}
