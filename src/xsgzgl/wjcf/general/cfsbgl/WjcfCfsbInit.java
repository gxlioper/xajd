package xsgzgl.wjcf.general.cfsbgl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.wjcf.general.GlobalsValue;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfshInterface;

public class WjcfCfsbInit {

	/**
	 * 初始化数据【违纪上报】
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfsb(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		// 访问路径
		String path = "wjcf_general_cfsb.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校代码
		String tableName = "view_wjcf_cfsb";
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
		// 学校,管理员按钮操作权限控制
		request.setAttribute("czqx", "admin".equalsIgnoreCase(user.getUserType())
				|| "xx".equalsIgnoreCase(user.getUserType()) ? "yes" : "no");
		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * 初始化数据【违纪审核】
	 * 
	 * @param request
	 * @author xucy
	 * @throws Exception
	 * 
	 */
	public void initCfsh(RequestForm rForm, WjcfGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfshInterface service = myService.getWjcfCfshService(myForm);
		
		SearchModel searchModel=new SearchModel();
		// 访问路径
		String path = "wjcf_general_cfsh.do";
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
		
		//====================初始化页面相关数据=====================
		// 高级查询
		searchModel.setPath(path);
		searchModel.setSearch_tj_shzt(new String[] {"未审核","需重审","不通过"});
		
		request.setAttribute("isZgjyh", service.isZgjyh(user));//判断是否最高级用户控制提交按钮
		request.setAttribute("searchTj", searchModel);
	}
	
}
