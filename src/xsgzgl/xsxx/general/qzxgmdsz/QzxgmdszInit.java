package xsgzgl.xsxx.general.qzxgmdsz;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

public class QzxgmdszInit {

	/**
	 * 初始化数据【强制修改名单设置】
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initQzxgmd(RequestForm rForm, QzxgmdszForm myForm, User user,
			HttpServletRequest request) throws Exception {

		// 访问路径
		String path = "general_xsxx_qzxgmdsz.do";
	
		// 学校代码
		String tableName = "";
		String realTable = "";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		
		//====================初始化页面相关数据=====================
		// 高级查询
		SearchModel searchModel = new SearchModel();
		// 学校,管理员按钮操作权限控制
		request.setAttribute("czqx", "admin".equalsIgnoreCase(user.getUserType())
				|| "xx".equalsIgnoreCase(user.getUserType()) ? "yes" : "no");
		request.setAttribute("searchTj", searchModel);
	}
	
}
