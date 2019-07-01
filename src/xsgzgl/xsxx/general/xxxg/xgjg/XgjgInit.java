package xsgzgl.xsxx.general.xxxg.xgjg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgjgInterface;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改結果_Init类
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

public class XgjgInit extends BasicInit {

	/**
	 * 信息修改_修改結果【查询】
	 * 
	 * @date 2013-01-28
	 * @author 伟大的骆
	 */
	public void initXgjgSearch(RequestForm rForm, XsxxGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_general_xxxg_xgjg.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 信息修改_修改審核【詳細】
	 * 
	 * @date 2013-01-28
	 * @author 伟大的骆
	 */
	public void initXgjgDetail(RequestForm rForm, XsxxGeneralForm myForm,
			XgjgModel model, User user, HttpServletRequest request)
			throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgshInterface xgshService = myService.getXxxgXgshService(myForm);
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// 申請ID
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_general_xxxg_xgsh.do";
		// 其他字段
		String[] qtzd = new String[] { "sqid" };
		// 其他字段值
		String[] qtzdz = new String[] { sqid };

		// -----------------獲得學生信息 begin-------------------
		XgshModel xgshModel = new XgshModel();
		xgshModel.setSqid(sqid);

		HashMap<String, String> rs = xgshService.getXgshInfo(xgshModel, user);
		
		//zd4被个性化用于 银行名称（2）
		if(Base.xxdm.equals("12036")&&null!=rs.get("zd4")){
			//更改显示的银行代码为银行对应名称
			XsxxtyglService xsxxService = new XsxxtyglService();
			rs.put("zd4", xsxxService.getYhmc(rs.get("zd4")));
		}
		
		List<HashMap<String, String>> rsList = xgshService.getXgshList(
				xgshModel, user);
		// -----------------獲得學生信息 end-------------------

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
	 * @date 2013-01-28
	 * @author 伟大的骆
	 */
	public void initParameter(XsxxGeneralForm myForm) throws Exception {

		XsxxGeneralService myService = new XsxxGeneralService();
		XxxgXgjgInterface service = myService.getXxxgXgjgService(myForm);

		service.initParameter();
	}
}
