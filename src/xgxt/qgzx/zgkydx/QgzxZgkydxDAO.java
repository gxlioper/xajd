package xgxt.qgzx.zgkydx;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й���ҵ��ѧ�ڹ���ѧ����DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-01-04</p>
 */
public class QgzxZgkydxDAO extends DAO {
	
	/**
	 * ���ѧ���Ƿ�����Ѿ�ͨ���ĸ�λ
	 * @param xh
	 * @param shdx
	 * @return boolean
	 * */
	public boolean checkPostPass(String xh, String shdx){
		boolean flag = false;
		String sql = "select count(*) num from view_xsgwxx where " + shdx + " ='ͨ��' and xh=?";
		String count = getOneRs(sql, new String[]{xh}, "num");
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		flag = Integer.parseInt(count)>0 ? true : false;
		return flag;
	}

}
