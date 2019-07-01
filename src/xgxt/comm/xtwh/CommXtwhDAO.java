package xgxt.comm.xtwh;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;

public class CommXtwhDAO extends CommDAO {

	/**
	 * 保存首页设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveSysz(CommXtwhForm model, HttpServletRequest request)
			throws Exception {

		// 首页设置表
		String tableName = "xtwh_syszb";
		// 提示语句
		String tsyj = model.getTsyj();

		// 清空表数据
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
	 * 是否拥有权限
	 * 
	 * @author luojw
	 */
	public Boolean hadQx(User user, String path) {

		DAO dao = DAO.getInstance();

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		
		String sql = "";

		// 非学生用户
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
