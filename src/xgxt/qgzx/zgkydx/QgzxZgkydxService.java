package xgxt.qgzx.zgkydx;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й���ҵ��ѧ�ڹ���ѧ����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-01-04</p>
 */
public class QgzxZgkydxService {
	QgzxZgkydxDAO dao = new QgzxZgkydxDAO();

	/**
	 * ���ѧ���Ƿ�����Ѿ�ͨ���ĸ�λ 
	 * @param xh
	 * @param shdx
	 * @return boolean
	 */
	public boolean checkPostPass(String xh, String shdx) {
		boolean flag = false;
		flag = dao.checkPostPass(xh, shdx);
		return flag;
	}
}
