package xgxt.xszz.nbty;

import java.util.HashMap;
import java.util.List;
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
 * Description:宁波天一资助Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-6-25
 */
public class XszzAction extends BasicAction{
	/**
	 * 国家助学贷款申请，用于宁波天一职业技术学院
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// 表名
		String tableName = "nbty_gjzxdksqb";
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		// 是否可申请判断依据，如果不能被申请，隐藏掉页面中的申请按钮
		boolean isApply = true;
		
		// 获得当前学年
		String xn = Base.currXn;
		
		// 如果用户类型是学生则xh即userName,不是学生xh为从页面中提交上来的学号；
		String xh = StringUtils.isEqual(userType, "stu") ? userName : request.getParameter("save_xh");
		String type = request.getParameter("type");

		
		Map<String, String> stuInf = new HashMap<String, String>();
		XszzNbtyService service = new XszzNbtyService();
		
		if(!StringUtils.isNull(xh)){
			stuInf = service.getStuInfo(xh);
			
			// 放入学年信息
			stuInf.put("xn", xn);
			service.changeRelationToParents(stuInf);
		}
		
		//判断该学生是否可以申请
		if(service.isAuditing(xh, xn)){
			isApply = false;
			request.setAttribute("message", "正在被审核，不能申请！");
		}
		
		if("add".equalsIgnoreCase(type) && isApply){
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
		request.setAttribute("rs", stuInf);
		request.setAttribute("userType", userType);
		return mapping.findForward("gjzxdksq");
	}
	
	/**
	 * 国家助学贷款申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward gjzxdksqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		// 表名
		String tableName = "nbty_gjzxdksqb";
		// 视图名
		String viewName = "view_nbty_gjzxdksq";
		String go = request.getParameter("go");
		String xh = session.getAttribute("userName").toString();
		GjzxdkForm gForm = (GjzxdkForm)form;
		XszzNbtyService service = new XszzNbtyService();
		String userType = service.getUserType(session);
		
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		
		if("stu".equalsIgnoreCase(userType)){
			List<String[]> list = service.getXsgjzxdkxx(xh);
			request.setAttribute("rs", list);
			return mapping.findForward("gjzxdkforstu");
		}
		// 加载页面属性
		if("fdy".equalsIgnoreCase(userType)){
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		}else{
			FormModleCommon.setNjXyZyBjList(request);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			gForm.setQueryequals_xydm(userDep);
		}

		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdksqjg");
		request.setAttribute("writeAble", FormModleCommon.getWriteAbleAndTitle(request)[0]);

		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = {"pkValue","disabled","xh","nj","xn","xm","bjmc","fdysh","xysh","xxsh"};
			// 获取上级是否审核通过信息，返回个页面
			if("fdy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
			}else if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
			}else{
				request.setAttribute("clientColumns", "'' disabled,");
			}
			selectPageDataByPagination(request, gForm, tableName, viewName, outputColumn);
		}
		

		request.setAttribute("userType", userType);
		request.setAttribute("userDep", userDep);
		return mapping.findForward("gjzxdksqjg");
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
	public ActionForward gjzxdkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键为学号，学年
		String pkValue = request.getParameter("pkValue");
		String tableName = "nbty_gjzxdksqb";
		XszzNbtyService service = new XszzNbtyService();
		String userType = service.getUserType(request.getSession()); 
		String doType = request.getParameter("doType");
		
		// 单个审核
		if("save".equalsIgnoreCase(doType)){
			updateOperation(request, tableName);
		}
		
		// 根据主键获取国家助学贷款表里面的信息
		Map<String, String> map = service.getGjzxdkxx(pkValue);
		service.changeRelationToParents(map);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userType);
		
		if("view".equalsIgnoreCase(doType)){
			// 返回修改的页面
			return mapping.findForward("gjzxdkshview");
		}

		// 打印信息
		if("print".equalsIgnoreCase(doType)){
			// 获得每学年的信息
			Map<String,Map<String,String>> mxndkxx = service.getMxndkxx(map.get("xh"));
			request.setAttribute("mxndkxx", mxndkxx);
			return mapping.findForward("gjzxdkprint");
		}
		
		// 修改
		if("modi".equalsIgnoreCase(doType) || "shone".equalsIgnoreCase(doType)){
			String xysh = request.getParameter("xysh");
			String xxsh = request.getParameter("xxsh");
		
			if("fdy".equalsIgnoreCase(userType) && "通过".equalsIgnoreCase(xysh) || 
				"xy".equalsIgnoreCase(userType) && "通过".equalsIgnoreCase(xxsh)){
				return mapping.findForward("gjzxdkshview");
			}
			
			if("modi".equalsIgnoreCase(doType)){
				return mapping.findForward("gjzxdkmodi");
			}
			
			request.setAttribute("nowtime", GetTime.getNowTime());
		}
		
		return mapping.findForward("gjzxdkshone");
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
	public ActionForward gjzxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// 表名
		String tableName = "nbty_gjzxdksqb";
		// 视图名
		String viewName = "view_nbty_gjzxdksq";
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
		
		XszzNbtyService service = new XszzNbtyService();
		// 获取userType
		String userType = service.getUserType(session);

		GjzxdkForm gForm = (GjzxdkForm)form; 
		
		// 如果是学院，学院部门就确定
		if("xy".equalsIgnoreCase(userType)){
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
			String[] outputColumn = {"pkValue","disabled","xh","nj","xn","xm","bjmc","fdysh","xysh","xxsh"};
			// 获取上级是否审核通过信息，返回个页面
			if("fdy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
			}else if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
			}else{
				request.setAttribute("clientColumns", "'' disabled,");
			}
			selectPageDataByPagination(request, gForm, tableName, viewName, outputColumn);
		}
		
		loadInfo(request, userType);
		request.setAttribute("userType", userType);
		return mapping.findForward("gjzxdksh");
	}
	
	
	/**
	 * 加载页面中的查询信息
	 * @param request
	 * @param userType
	 */
	private void loadInfo(HttpServletRequest request, String userType){
		// 根据类型加载年级，学院，专业，班级信息
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		// 加载年度，学年，学期信息
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdksh");
		request.setAttribute("writeAble", FormModleCommon.getWriteAbleAndTitle(request)[0]);
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
	public ActionForward gjzxdkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableName = "nbty_gjzxdksqb";
		String viewName = "view_nbty_gjzxdksq";
		
		// 需要导出的字段
		String[] output = {"xh","xm", "xb", "xn", "bjmc", "xxqkztpj",
				"bjgbxkms", "sxnpddd", "ywblxyjl", "dkje","fdysh", "xysh", "xxsh", "hkfs"};
		expPageData(request, response, tableName, viewName, output);
		return mapping.findForward("");
	}
}
