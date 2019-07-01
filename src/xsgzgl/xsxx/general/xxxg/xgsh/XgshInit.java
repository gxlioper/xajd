package xsgzgl.xsxx.general.xxxg.xgsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改審核_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgshInit extends BasicInit {

	/**
	 * 信息修改_修改審核【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initXgshSearch(RequestForm rForm, XsxxGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_general_xxxg_xgsh.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// 高级查询
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_shztTwo(new String[] { "未审核", "需重审" });
		request.setAttribute("searchTj", searchModel);
	}

	/**
	 * 信息修改_修改審核【詳細】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initXgshDetail(RequestForm rForm, XsxxGeneralForm myForm,
			XgshModel model, User user, HttpServletRequest request)
			throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// 申請ID
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		// 崗位ID
		String gwid = request.getParameter("gwid");
		model.setGwid(gwid);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_general_xxxg_xgsh.do";
		// 其他字段
		String[] qtzd = new String[] { "sqid", "gwid" };
		// 其他字段值
		String[] qtzdz = new String[] { sqid, gwid };

		HashMap<String, String> rs = service.getXgshInfo(model, user);
		//zd4被个性化用于 银行名称（2）
		if(Base.xxdm.equals("12036")&&null!=rs.get("zd4")){
			//更改显示的银行代码为银行对应名称
			XsxxtyglService xsxxService = new XsxxtyglService();
			rs.put("zd4", xsxxService.getYhmc(rs.get("zd4")));
		}	
		List<HashMap<String, String>> rsList = service.getXgshList(model, user);
		rForm.setRsList(rsList);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	/**
	 * 初始化参数
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initParameter(XsxxGeneralForm myForm) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		service.initParameter();
	}
}
