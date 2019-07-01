package xgxt.rcsw.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.rcsw.RcswForm;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xljk.zxzx.util.Xljk_DAO;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 广州大学日常事务-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class RcswGzdxAction extends DispatchAction {

	/**
	 * 学生留言
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xslyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//赋初值
		String userType = (String) request.getSession().getAttribute("userType");
		String userName = (String) request.getSession().getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_xsly";
		String realTable = "rcsw_xslyb";
		String path = "rcsw_lyb_xsly.do";
		String mklx = "ly";
		
		RcswForm myForm = (RcswForm) form;
		
		RequestForm rForm = new RequestForm();		
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		
		return lyfbManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 发布通知
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fbtzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 赋初值
		String userType = (String) request.getSession().getAttribute("userType");
		String userName = (String) request.getSession().getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_xsly";
		String realTable = "rcsw_xslyb";
		String path = "rcsw_lyb_fbtz.do";
		String mklx = "tz";
		
		RcswForm myForm = (RcswForm) form;
		
		RequestForm rForm = new RequestForm();
		
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		
		return lyfbManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 留言发布管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward lyfbManage(ActionMapping mapping, RcswForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String userName = rForm.getUserName();
		String userType = rForm.getUserType();
		String tableName = rForm.getTableName();
		String realTable = rForm.getRealTable();
		String doType = rForm.getDoType();
		String xxdm = Base.xxdm;
		RcswGzdxService service = new RcswGzdxService();
		RcswGzdxModel model = new RcswGzdxModel();

		boolean result = false;
		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		BeanUtils.copyProperties(model, myForm);

		// 保存留言
		if ("save".equalsIgnoreCase(doType)) {

			// 保存字段
			String[] onezd = new String[] { "lymc", "lylx", "lynr", "lyr" };
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
				onezd = new String[] { "lymc", "lylx", "lynr", "lyr","lydx" };
			}
			// 主键
			String pk = "lyr||lymc||lylx||lysj";
			// 主键值
			String pkValue = myForm.getLyr() + myForm.getLymc()
					+ myForm.getLylx() + myForm.getLysj();
			// 留言人
			String lyr = userName;
			model.setLyr(lyr);

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			result = service.saveRcsw(saveForm, model, request);
			request.setAttribute("result", result);

		}

		// 查看留言	
		model.setLyr(userName);

		String[] colList = new String[] { "pk", "lxmc", "lymc", "xm", "lysj",
				"hfqk" };
//		String[] orderBy = new String[] { "lysj" };
		topTr = SearchUtils.getTopTr(tableName, colList, null);
		rs = service.getXslyInfoList(model, userType);
		if (!"stu".equalsIgnoreCase(userType)) {
			myForm.setLylx("");
		}
		myForm.setLymc("");
		myForm.setLynr("");
		
		// 初始化列表值
		service.setList(myForm, request, "lyfb");
		
		// 设置Request的值
		service.setRequestValue(rForm, request);

		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		request.setAttribute("mklx", rForm.getMklx());
		//南宁职业 部门列表
		request.setAttribute("bmdmList",DAO.getInstance().getBmListAll());//部门List
		//南宁职业 心理咨询师列表
		request.setAttribute("zxsList", service.getXljkZxs());
		return mapping.findForward("lyfbManage");
	}
	
	/**
	 * 回复留言管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward hflyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		RcswForm myForm = (RcswForm) form;
		RcswGzdxService service = new RcswGzdxService();
		RcswGzdxModel model = new RcswGzdxModel();
		
		String xxdm = Base.xxdm;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_xsly";
		String realTable = "rcsw_xslyb";
		String path = "rcsw_lyb_hfly.do";
		
		//管理员权限
		boolean isAdmin = "xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType) ? true : false;
		// 教师权限
		boolean isTeacher = false;
		Xljk_DAO xljkDao=new Xljk_DAO();
		// 判断是否学院用户
		if (!(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY) && xljkDao.checkXlzxs(userName)) && "xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			myForm.setBmdm(userDep);
			// 判断是否是辅导员或者班主任
			if (Fdypd.isFdy(userName) || Fdypd.isBzr(userName, "bzrbbb")) {
				isTeacher = true;
			}
		}
		
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		//留言置顶操作
		if(!Base.isNull(doType) && "zd".equalsIgnoreCase(doType)){
			
			String pkValue = myForm.getPkValue();
			boolean flag = Boolean.parseBoolean(request.getParameter("flag"));// true :置顶操作 false：取消操作
			
			result = service.saveZdInfo(pkValue,flag);
			
			request.setAttribute("result", result);
		}
		
		//删除留言操作
		if(!Base.isNull(doType) && "del".equalsIgnoreCase(doType)){
			
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				
				result = service.delXslyInfo(checkVal);
				request.setAttribute("result", result);
			}
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			String bmdm = model.getBmdm();//部门代码
			if(!Base.isNull(bmdm)){
				model.setXydm(bmdm);
			}
			String[] colList = new String[] { "pk", "lxmc", "lymc", "lx", "xhzgh","xm",
					"lysj", "hfqk", "czsj" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			//置顶信息默认显示
			String other_query = " or exists (select 1 from view_rcsw_xsly b where a.pk = b.pk and b.czsj <> 0)  ";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
				 //南宁职业增加心理健康老师选择
				 other_query = " and exists (select 1 from view_rcsw_xsly b where a.pk = b.pk and lydx is null) or exists(select 1 from  rcsw_lylxb b where a.lylx=b.dm and b.xzzxs='yes' and (  lydx='"+userName+"'  ) ) ";
			}
			rs = service.getRcswList(tableName, model, colList, other_query, null);
		}

		// 初始化列表
		service.setList(myForm, request, "lyfb");

		// 初始化request的值
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		
		service.setRequestValue(rForm, request);
		
		//管理员
		request.setAttribute("isAdmin", isAdmin);
		//老师
		request.setAttribute("isTeacher", isTeacher);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		//学校个性化
		if("10596".equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/rcsw/gzdx/hflyManage_"+Base.xxdm+".jsp",false);
			//return new ActionForward("/rcsw/gzdx/xxgxh/hflyManage_"+Base.xxdm+".jsp",false);
		}
		return mapping.findForward("hflyManage");
	}
	
	/**
	 * 学生留言回复
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xslyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswGzdxService service = new RcswGzdxService();
		RcswGzdxModel model = new RcswGzdxModel();

		String doType = request.getParameter("doType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String title = "日常事务 - 留言板 - 留言回复";
		String tableName = "view_rcsw_xsly";
		String realTable = "rcsw_lyhfb";
		String pk = "";
		String pkValue = request.getParameter("pk");

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs = new HashMap<String, String>();

		// 回复留言
		if ("save".equalsIgnoreCase(doType)) {

			// 保存字段
			String[] onezd = new String[] { "lymc", "lylx", "lysj", "lyr",
					"hfr", "hfnr", "hfls" };
			// 主键
			pk = "lyr||lymc||lylx||lysj||hfsj";
			// 主键值
			pkValue = myForm.getLyr() + myForm.getLymc() + myForm.getLylx()
					+ myForm.getLysj() + myForm.getHfsj();
			// 回复人
			String hfr = userName;
			model.setHfr(hfr);
			// 回复楼数
			int ls = service.getHfls(model);
			model.setHfls(String.valueOf(ls + 1));
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			result = service.saveRcsw(saveForm, model, request);

			if (result) {
				pkValue = myForm.getLyr() + myForm.getLymc() + myForm.getLylx()
						+ myForm.getLysj();
			}
			request.setAttribute("result", result);
		}

		// 编辑留言
		if ("edit".equalsIgnoreCase(doType)) {
			result = service.editHfnr(model, request);
			request.setAttribute("result", result);
		}
		
		// 获得留言信息
		if ("view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType) || result) {

			pk = "pk";

			String[] colList = new String[] { "xm", "xymc", "zymc", "bjmc",
					"lymc", "lylx", "lxmc", "lysj", "lyr", "lynr" };

			rs = service.getRcswInfo(tableName, pk, pkValue, colList);
			rs.put("sj", service.changDateLx(rs.get("lysj"), "YYYYMMDDHH24MISS"));

			model.setLylx(rs.get("lylx"));
			model.setLymc(rs.get("lymc"));
			model.setLysj(rs.get("lysj"));
			model.setLyr(rs.get("lyr"));
			
			//获得回复信息
			rsList = service.getLyhfList(model);
		}

		// 初始化列表值
		service.setList(myForm, request, "lyfb");

		// 设置Request的值
		RequestForm rForm = new RequestForm();
		rForm.setTitle(title);
		rForm.setRs(rs);
		rForm.setRsList(rsList);
		rForm.setPk(pkValue);
		rForm.setDoType(doType);
		service.setRequestValue(rForm, request);

		return mapping.findForward("xslyUpdate");
	}

	/**
	 * 回复统计管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward hftjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		RcswForm myForm = (RcswForm) form;
		RcswGzdxService service = new RcswGzdxService();
		RcswGzdxModel model = new RcswGzdxModel();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_czxx_rdsq";
		String realTable = "czxx_rdsqb";
		String path = "rcsw_lyb_hftj.do";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 学院用户访问
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setBmdm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.printTjbb(model, response.getOutputStream());

			return null;
		}
		
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			topTr = service.getTopTr("hftj");
			rs = service.getHflyInfoList(model);
		}

		// 初始化列表值
		service.setList(myForm, request, "lyfb");

		// 设置Request的值
		RequestForm rForm = new RequestForm();
		rForm.setDoType(doType);
		rForm.setPath(path);
		service.setRequestValue(rForm, request);

		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("hftjManage");
	}

}
