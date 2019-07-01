package xgxt.xszz.guizdx;

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

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 贵州大学学生资助管理军训管理-action类
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

public class XszzGuizdxAction extends BasicAction {
	
	/**
	 * 学生资助_副食补助_分配专业
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbzfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGuizdxService service = new XszzGuizdxService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		//	================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_njxyzybj";
		// 表名
		String realTable = "xszz_fsbzzyb";
		// 访问路径
		String path = "xszz_fsbz_fp.do";
		// 当前年度
		String xn = Base.currNd;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================判断模块,权限======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(xn);
		}
		
		// =================end ===================
		
		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveFsbzZyxm(myForm, realTable);
			String message = result ? "操作成功" : "操作失败";
			request.setAttribute("result", result);
			request.setAttribute("message", message);
		}
		// =================end ===================
		
		//==================执行查询操作 ==================
		if (search) {
			String[] colList = new String[] { "nd", "xymc", "zymc","zydm","bzlx" };
			
			ArrayList<String[]> rs = service.getZyList(myForm, colList);
			List<HashMap<String, String>> topTr = service.getTopTr("fsbz_fp");
			
			//副食补助项目列表
			List<HashMap<String, String>> xmList = service.getFsbzXmList(myForm);
			if(xmList !=null && xmList.size()>0){
				request.setAttribute("xmNum", xmList.size());
			}
			request.setAttribute("xmList", xmList);
			
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);	
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "fsbz_fp");
		// =================end ===================

		return mapping.findForward("fsbzfpManage");
	}
	
	/**
	 * 学生资助_副食补助_发放
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbzffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGuizdxService service = new XszzGuizdxService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		//	================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_xsjbxx";
		// 表名
		String realTable = "xszz_fsbz_xsffb";
		// 访问路径
		String path = "xszz_fsbz_ff.do";
		// 当前年度
		String xn = Base.currNd;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================判断模块,权限======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		
		//设置当前学年
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(xn);
		}
		
		// 设置当前月
		if (Base.isNull(myForm.getYf())) {
			myForm.setYf(service.getDqyf());
		}
		
		// =================end ===================
		
		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			
			boolean flag = service.delFsbzFf(myForm, realTable);
			
			if(flag){
				HashMap<String, String[]> primaryArrayMap = this.getValueArrayMap(request, "primarykey_");
				HashMap<String, String> primaryMap = new HashMap<String, String>();
				//批量保存字段
				primaryMap.put("nd", myForm.getNd());
				primaryMap.put("yf", myForm.getYf());
				primaryMap.put("bzlx", myForm.getBzlx());
				
				HashMap<String, String> tableMap = new HashMap<String, String>();
				tableMap.put("tableName", realTable);
				//tableMap.put("viewName", tableName);
				
				this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
			}
		}
		// =================end ===================
		
		//==================执行查询操作 ==================
		if (search) {
			String[] colList = new String[] { "sfdj", "xh", "xm", "nj",
					"xymc", "zymc", "bjmc", "nd", "yf", "bzmc" };
			
			ArrayList<String[]> rs = service.getFsbzXsffList(myForm, colList);
			List<HashMap<String, String>> topTr = service.getTopTr("fsbz_ff");
			
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);	
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "fsbz_ff");
		// =================end ===================

		return mapping.findForward("fsbzffManage");
	}
	
	/**
	 * 学生资助_副食补助_结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbzjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGuizdxService service = new XszzGuizdxService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		//	================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 表名
		String realTable = "";
		// 访问路径
		String path = "xszz_fsbz_jg.do";
		// 模块类型
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "jg" : mklx;
		// 视图名
		String tableName = "";
		tableName = ("jg".equalsIgnoreCase(mklx)) ? "view_xszz_fsbzff" : "view_xszz_fsbztj";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================判断模块,权限======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_xydm(userDep);
		}
		
		// =================end ===================
		
		//==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = null;

			// 结果
			if ("jg".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "nd", "yfmc", "bzmc", "kh", "ffje" };
				this.selectPageDataByPagination(request, myForm, "", tableName,
						outputColumn);
			}
			// 未发放
			else if ("wff".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "nd", "yf", "bzmc" };
				ArrayList<String[]> rs = service.getFsbzWffList(myForm,
						outputColumn);
				List<HashMap<String, String>> topTr = service
						.getTopTr("fsbz_wff");
				FormModleCommon.commonRequestSet(request, tableName, realTable,
						rs, topTr);
			}
			// 统计
			else if ("tj".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "bzmc", "nd", "kh", "zje" };
				this.selectPageDataByPagination(request, myForm, "", tableName,
						outputColumn);
			}
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			// 结果
			if ("jg".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xydm",
						"xymc", "zydm", "zymc", "bjdm", "bjmc", "nd", "yf",
						"yfmc", "bzlx", "bzmc", "kh", "ffje" };
			}// 统计
			else if ("tj".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xydm",
						"xymc", "zydm", "zymc", "bjdm", "bjmc", "nd", "bzlx",
						"bzmc", "kh", "zje" };
			}
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "fsbz_ff");
		// =================end ===================

		return mapping.findForward("fsbzjgManage");
	}
}
