package xsgzgl.wjcf.general.cfjcgl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.wjcf.general.GlobalsValue;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;

public class WjcfCfjcInit {

	/**
	 * 初始化数据【违纪解除】
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfjc(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		// 访问路径
		String path = "wjcf_general_cfjc.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校代码
		String tableName = "view_wjcf_cfjcsq";
		String realTable = "xg_wjcf_wjcfsbb";
		// 学校拼音名称
		String xxpymc = (Base.isNull(myForm.getXxpymc()))?GlobalsValue.getXxpymc(xxdm):myForm.getXxpymc();
		myForm.setXxpymc(xxpymc);
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
		searchModel.setSearch_tj_xn(new String[] { });

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * 初始化数据【违纪解除审核】
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfjcsh(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfjcshInterface service = myService.getWjcfCfjcshService(myForm);
		// 访问路径
		String path = "wjcf_general_cfjcsh.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校代码
		String tableName = "xg_wjcf_wjcfsbb";
		String realTable = "xg_wjcf_wjcfsbb";
		// 学校拼音名称
		String xxpymc = (Base.isNull(myForm.getXxpymc()))?GlobalsValue.getXxpymc(xxdm):myForm.getXxpymc();
		myForm.setXxpymc(xxpymc);
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
//		List<HashMap<String,String>> spgwList = service.getSpgwList(user);
//		
//		request.setAttribute("spgwList", spgwList);
		
		request.setAttribute("isZgjyh", service.isZgjyh(user));
		
		//====================初始化页面相关数据=====================
		// 高级查询
		SearchModel searchModel = new SearchModel();
		searchModel.setPath(path);
		searchModel.setSearch_tj_shzt(new String[] {"未审核","需重审","不通过"});
		// 学校,管理员按钮操作权限控制
		request.setAttribute("czqx", "admin".equalsIgnoreCase(user.getUserType())
				|| "xx".equalsIgnoreCase(user.getUserType()) ? "yes" : "no");
		request.setAttribute("searchTj", searchModel);
	}
}
