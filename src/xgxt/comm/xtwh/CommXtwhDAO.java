package xgxt.comm.xtwh;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;

public class CommXtwhDAO extends CommDAO {

	/**
	 * ������ҳ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveSysz(CommXtwhForm model, HttpServletRequest request)
			throws Exception {

		// ��ҳ���ñ�
		String tableName = "xtwh_syszb";
		// ��ʾ���
		String tsyj = model.getTsyj();

		// ��ձ�����
		boolean flag = StandardOperation.delete(tableName, "1", "1", request);

		if (flag) {

			String[] columns = new String[] { "tsyj" };
			String[] values = new String[] { tsyj };

			flag = StandardOperation
					.insert(tableName, columns, values, request);
		}
		return flag;
	}
	
	/**
	 * �Ƿ�ӵ��Ȩ��
	 * 
	 * @author luojw
	 */
	public Boolean hadQx(User user, String path) {

		DAO dao = DAO.getInstance();

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		
		String sql = "";

		// ��ѧ���û�
		if (!"stu".equalsIgnoreCase(userType)) {

			sql = "select count(1) num from (select nvl(dxq,'0') dxq,(select gnmkmc "
					+ "from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||"
					+ "(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from"
					+ " view_yhqx a where yhm=? and dyym=?)";
		} else {

			sql = "select count(1) num from (select nvl(dxq,'0') dxq,(select "
					+ "gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,3))||'-'||"
					+ "(select gnmkmc from gnmkdmb where gnmkdm = substr(a.gnmkdm,0,5))||'-'||gnmkmc title from"
					+ " view_yhzqx a where zdm=? and dyym=?)";
			userName = "6727";
		}
		
		String num = dao.getOneRs(sql, new String[] { userName, path }, "num");
		
		boolean flag = false;

		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
}
