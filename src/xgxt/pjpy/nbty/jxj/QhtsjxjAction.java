package xgxt.pjpy.nbty.jxj;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;
import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统
 * Description:宁波天一清寒天使奖学金Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-7-14
 */
public class QhtsjxjAction extends BasicAction{
	/**
	 * 国家助学贷款申请，用于宁波天一职业技术学院
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward qhtsjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		String tableName = "nbty_qhtssqb";
		
		// 是否可申请判断依据，如果不能被申请，隐藏掉页面中的申请按钮
		boolean isApply = true;
		
		// 如果用户类型是学生则xh即userName,不是学生xh为从页面中提交上来的学号；
		String xh = StringUtils.isEqual(userType, "stu") ? userName : request.getParameter("save_xh");
		String doType = request.getParameter("doType");
		
		// 当前学年
		String xn = Base.currXn;
		
		Map<String, String> stuInf = new HashMap<String, String>();		
		QhtsNbtyService service = new QhtsNbtyService();
		Map<String, String> jxjxx = service.getJxjxx("清寒天使奖学金");
		
		if(!StringUtils.isNull(xh)){
			stuInf = service.getStuInfo(xh);
		}
		
		//判断该学生是否可以申请
		if(service.isAuditing(xh, xn)){
			isApply = false;
			request.setAttribute("message", "正在被审核，不能申请！");
		}
		
		if("add".equalsIgnoreCase(doType) && isApply){
			// 判断要增加的数据是否存在
			BaseDAO baseDao = new BaseDAO();
			String pkVal = xh+xn;
			boolean isExists = baseDao.checkExists(tableName, "xh||xn", pkVal);
			
			// 如果存在执行update操作，不存在执行插入操作
			if(isExists){
				updateOperation(request, tableName);
			}else{
				insertOperation(request, tableName);
			}
		}
		// 申请时间
		request.setAttribute("sqsj", GetTime.getNowTime());
		// 判断标志，是否可以申请
		request.setAttribute("isApply", isApply);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("jxjxx", jxjxx);
		request.setAttribute("rs", stuInf);
		request.setAttribute("userType", userType);
		return mapping.findForward("qhtsjxjsq");
	}
	
	/**
	 * 清寒天使申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward qhtsjxjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String go = request.getParameter("go");
		QhtsjxjForm qForm = (QhtsjxjForm)form;
		
		// 区分辅导员和学院用户
		String user = getUserType(session);
		String tableName = "nbty_qhtssqb";
		String viewName = "view_nbty_qhtsjxj";
		QhtsNbtyService service = new QhtsNbtyService();
		
		
		if("xy".equalsIgnoreCase(user)){
			qForm.setQueryequals_xydm(userDep);
		}
		
		if("stu".equalsIgnoreCase(user)){
			String xh = session.getAttribute("userName").toString();
			Map<String,String> stuInfo = service.getStuInfo(xh);
			String equals_xh = stuInfo.get("xh");
			String like_xm = stuInfo.get("xm");
			String equals_nj = stuInfo.get("nj");

			qForm.setQuerylike_xh(equals_xh);
			qForm.setQuerylike_xm(like_xm);
			qForm.setQueryequals_nj(equals_nj);
			
			request.setAttribute("queryInfo", stuInfo);
		}

		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = getOutputList(request, user);		
			selectPageDataByPagination(request, qForm, tableName, 
					viewName, outputColumn);
		}
		
		// 加载信息
		FormModleCommon.setNdXnXqList(request);
		loadInfo(request, "nbty_qhtsjxj.do?method=qhtsjxjcx");
		
		// 放入导入导出的表和视图
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("userType", user);
		request.setAttribute("userDep", userDep);
		return mapping.findForward("qhtsjxjcx");
	}
	
	/**
	 * 单条信息的查看，审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键为学号，学年
		String pkValue = request.getParameter("pkValue");
		String user = getUserType(request.getSession());
		String doType = request.getParameter("doType");
		
		QhtsNbtyService service = new QhtsNbtyService();

		Map<String, String> map = service.getQhtsxx(pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", user);
		
		if("view".equalsIgnoreCase(doType)){
			// 返回修改的页面
			request.setAttribute("view","true");
		}
		
		// 是否具有修改或单个审核的权限
		boolean isModi = isModi(request, user);
		// 修改
		if("modi".equalsIgnoreCase(doType) && !isModi){
			request.setAttribute("view", "true");
		}
		
		if("shone".equalsIgnoreCase(doType)){
			if(!isModi){
				request.setAttribute("view", "true");
			}else{
				request.setAttribute("nowtime", GetTime.getNowTime());
				return mapping.findForward("qhtsjxjshone");
			}
		}
		
		return mapping.findForward("qhtsjxjmodi");
	}
	
	/**
	 * 更新清寒天使奖学金
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 返回页面
		String destination = request.getParameter("destination");
		this.updateOperation(request, "nbty_qhtssqb");
		return mapping.findForward(destination);
	}
	
	/**
	 * 清寒天使打印方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		
		QhtsNbtyService service = new QhtsNbtyService();
		Map<String, String> map = service.getQhtsxx(pkValue);
		
		request.setAttribute("rs", map);
		return mapping.findForward("qhtsjxjprint");
	}
	
	/**
	 * 国家助学贷款审核，用于宁波天一职业技术学院
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String tableName = "nbty_qhtssqb";
		String viewName = "view_nbty_qhtsjxj";
		// 需要进行的操作
		String doType = request.getParameter("doType");
		// 审核结果
		String shjg = request.getParameter("shjg");
		// 审核字段
		String shzd = request.getParameter("shzd");
		// 用户部门
		String userDep = session.getAttribute("userDep").toString();
		// 审核时间
		String shsj = request.getParameter("shsj");
		
		String go = request.getParameter("go");
		
		// 获取userType
		String user = getUserType(session);

		QhtsjxjForm gForm = (QhtsjxjForm)form; 
		
		// 如果是学院，学院部门就确定
		if("xy".equalsIgnoreCase(user)){
			gForm.setQueryequals_xydm(userDep);
		}
		
		if(!StringUtils.isNull(shjg)){
			shjg = "tg".equalsIgnoreCase(shjg) ? "通过" : "不通过";
		}
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			deleteOperation(request, tableName);
		}
		
		// 审核操作
		if("sh".equalsIgnoreCase(doType)){
			
			// 获取页面中以primarykey_为开始的数据
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
			valueMap.put(shsj, GetTime.getNowTime());
			
			// 通用审核方法
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		//		根据页面中的条件获取数据
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = getOutputList(request, user);
			selectPageDataByPagination(request, gForm, tableName,
					viewName, outputColumn);
		}
		
		loadInfo(request, "nbty_qhtsjxj.do?method=qhtsjxjsh");
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("userType", user);
		return mapping.findForward("qhtsjxjsh");
	}
	
	
	/**
	 * 加载页面中的查询信息
	 * @param request
	 * @param userType
	 */
	private void loadInfo(HttpServletRequest request, String path){
		// 根据类型加载年级，学院，专业，班级信息
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", path);
		request.setAttribute("writeAble", FormModleCommon.getWriteAbleAndTitle(request)[0]);
	}
	
	/**
	 * 把userType 分成四类，其他，辅导员，学院，学校
	 * @param session
	 * @return
	 */
	private String getUserType(HttpSession session){
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		String userType = (String)session.getAttribute("userType");
		
		if(isFdy){
			userType = "fdy";
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/**
	 * 显示需要获取数据库的字段包括特殊字段
	 * @param request
	 * @param user
	 * @return
	 */
	private String[] getOutputList(HttpServletRequest request, String user){
		String[] outputColumn = {"pkValue","disabled","xh","nj","xn","xm","bjmc","bjsh","xysh","xxsh"};
		// 获取上级是否审核通过信息，返回个页面
		if("fdy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
		}else if("xy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
		}else{
			request.setAttribute("clientColumns", "'' disabled,");
		}
		
		return outputColumn;
	}
	
	
	private boolean isModi(HttpServletRequest request, String user){
		String xysh = request.getParameter("xysh");
		String xxsh = request.getParameter("xxsh");
		
		boolean flag = true;
		
		if("fdy".equalsIgnoreCase(user) && "通过".equalsIgnoreCase(xysh) || 
				"xy".equalsIgnoreCase(user) && "通过".equalsIgnoreCase(xxsh)){
			flag = false;
		}
		
		return flag;
	}
	/**
	 * 导出数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableName = "nbty_qhtssqb";
		String viewName = "view_nbty_qhtsjxj";
		// 需要导出的字段
			String[] output = { "xh", "xn", "nj", "xm", "xb", "csrq", "mzmc","sfzh","rxrq","xymc",
				"zymc", "bjmc", "pddd", "sfcjqgzx", "sfsqzxdk","jxjmc","jlje","bjsh", "xysh",
				"xxsh" };
		
		expPageData(request, response, tableName,viewName, output);
		return mapping.findForward("");
	}
	
	
}

