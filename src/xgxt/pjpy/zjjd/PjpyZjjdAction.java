

package xgxt.pjpy.zjjd;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.ApplyAction;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电评奖评优Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class PjpyZjjdAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	CommonAction commonAction = new CommonAction();
	/**
	 * 校园表现分的默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//如果学期非空,则获取相对应的月份
			yfList = service.getYfList(zjjdForm.getXq());
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xybxfcjb");//
		request.setAttribute("tableName", "view_xybxfcj");
		
		return mapping.findForward("xybxfadd");
	}
	
	/**
	 * 校园表现分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		String xm = DealString.toGBK(zjjdForm.getXm());
		String xh = DealString.toGBK(zjjdForm.getXh());
		PjpyZjjdService service = new PjpyZjjdService();
		XybxfModel xybxfModel = new XybxfModel();
		
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//如果学期非空,则获取相对应的月份
			yfList = service.getYfList(zjjdForm.getXq());
		}
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getXybxfTitle();//查询表头
		List<String[]> resList = service.getXybxfResult(xybxfModel);//查询结果
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(xm);
		zjjdForm.setXh(xh);
		request.setAttribute("realTable", "xybxfcjb");
		request.setAttribute("tableName", "view_xybxfcj");
		
		return mapping.findForward("xybxfadd");
	}
	
	/**
	 * 学生校园表现分单个增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfnewAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String xh = request.getParameter("xh");
		HashMap<String, String> stuMap = new HashMap<String, String>();
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(xh)) {//学号非空时获取该生相关信息
			stuMap = service.getStuInfo(xh);
			if (stuMap != null) {
				stuMap.put("stuExists", "yes");//存在该生
			} else {
				stuMap.put("stuExists", "no");//不存在该生
			}
		}
		if (!StringUtils.isNull(zjjdForm.getXq())) {//如果学期非空,则获取相对应的月份
			yfList = service.getYfList(zjjdForm.getXq());
		}
		request.setAttribute("yfList", yfList);
		request.setAttribute("rs", stuMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("add");
	}
	
	/**
	 * 校园表现分单个保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		zjjdForm.setXh(request.getParameter("xh"));
		XybxfModel xybxfModel = new XybxfModel();//校园表现分保存MODEL
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		boolean bFlag = service.xybxfSave(xybxfModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap.put("stuExists", "yes");
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("yfList", new ArrayList<HashMap<String, String>>());
		zjjdForm.setSx(DealString.toGBK(zjjdForm.getSx()));
		return mapping.findForward("add");
	}
	
	/**
	 * 学生校园表现分修改显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		String xq = request.getParameter("xq");
		HashMap<String, String> resMap = service.getXybxfInfo(pkValue);//获取校园表现分信息
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(resMap.get("xq"))) {
			yfList = service.getYfList(resMap.get("xq"));
		}
		if (!StringUtils.isNull(xq)) {
			yfList = service.getYfList(xq);
			resMap = service.getXybxfInfo(pkValue);//获取校园表现分信息
			resMap.put("xq", xq);
		}
		zjjdForm.setXn(resMap.get("xn"));
		zjjdForm.setXq(resMap.get("xq"));
		zjjdForm.setYf(resMap.get("yf"));
		zjjdForm.setJf(resMap.get("jf"));
		zjjdForm.setKf(resMap.get("kf"));
		zjjdForm.setSx(resMap.get("sx"));
		request.setAttribute("yfList", yfList);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xybxfview");
	}
	
	/**
	 *  学生校园表现分修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		zjjdForm.setXh(request.getParameter("xh"));
		XybxfModel xybxfModel = new XybxfModel();
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.xybxfModi(xybxfModel, pkValue, request);//单个修改
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap.put("stuExists", "yes");
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("yfList", new ArrayList<HashMap<String, String>>());
		zjjdForm.setSx(DealString.toGBK(zjjdForm.getSx()));
		return mapping.findForward("xybxfview");
	}
	
	/**
	 * 学生校园表现分批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//如果学期非空,则获取相对应的月份
			yfList = service.getYfList(zjjdForm.getXq());
		}
		String sDel = service.xybxfDel(zjjdForm.getCbv(), request);//数据删除
		if (!StringUtils.isNull(sDel)) {//删除失败
			request.setAttribute("failinfo", String.format("第 %1$s 条数据删除失败！", sDel));
			request.setAttribute("deleted", "no");
		} else {//删除成功
			request.setAttribute("deleted", "yes");
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xybxfcjb");//
		request.setAttribute("tableName", "view_xybxfcj");
		
		return mapping.findForward("xybxfadd");
	}
	
	/**
	 * 校园表现分审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//如果学期非空,则获取相对应的月份
			yfList = service.getYfList(zjjdForm.getXq());
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		
		return mapping.findForward("xybxfshdefault");
	}
	
	/**
	 * 校园表现分审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String[] keys = zjjdForm.getCbv();
		String sJg = request.getParameter("param1");
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//如果学期非空,则获取相对应的月份
			yfList = service.getYfList(zjjdForm.getXq());
		}
		String shRes = service.xybxfSh(keys, sJg, userType, request);//批量审核
		if (!StringUtils.isNull(shRes)) {
			request.setAttribute("result", "no");
			request.setAttribute("failinfo", shRes);
		} else {
			request.setAttribute("result", "yes");
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		
		return mapping.findForward("xybxfshdefault");
	}
	
	/**
	 * 校园表现分审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		XybxfModel xybxfModel = new XybxfModel();
		
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//如果学期非空,则获取相对应的月份
			yfList = service.getYfList(zjjdForm.getXq());
		}
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		List<HashMap<String, String>> topList = service.xybxfshQryTitle(userType);//查询表头
		List<String[]> resList = service.xybxfshQryResult(xybxfModel, userType);;//查询结果
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		zjjdForm.setXh(DealString.toGBK(zjjdForm.getXh()));
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		
		return mapping.findForward("xybxfshdefault");
	}
	
	/**
	 * 校园表现分单个审核详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();//月份列表
		
		List<HashMap<String, String>> chkList = service.getChkList(3);//审核列表
		HashMap<String, String> resMap = service.xybxfshView(userType, pkValue);//审核显示详细信息
		if (!StringUtils.isNull(resMap.get("xq"))) {
			yfList = service.getYfList(resMap.get("xq"));
		}
		request.setAttribute("chkList", chkList);
		request.setAttribute("rs", resMap);
		if (resMap != null) {
			request.setAttribute("oldsh", resMap.get("sh"));
			request.setAttribute("oldyj", resMap.get("yj"));
		}
		request.setAttribute("yfList", yfList);
		request.setAttribute("userType", userType);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xybxfshview");
	}
	
	/**
	 * 校园表现分单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		String sh = DealString.toGBK(request.getParameter("sh"));
		String yj = DealString.toGBK(request.getParameter("yj"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//用户类型
		boolean bFlag = service.xybxfShOne(pkValue, userType, sh, yj, request);//单个审核结果
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> chkList = service.getChkList(3);//审核列表
		request.setAttribute("chkList", chkList);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("yfList", new ArrayList<HashMap<String, String>>());//月份列表
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("xybxfshview");
	}
	
	/**
	 * 学生干部德育附加分维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxgbdyfjfWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xsgbdyfjfb");
		request.setAttribute("tableName", "view_xsgbdyfjf");
		return mapping.findForward("xxgbdyfjfwh");
	}
	
	/**
	 * 学生干部德育附加分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxgbdyfjfQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		XsgbdyfjfModel xsgbdyfjfModel = new XsgbdyfjfModel();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		BeanUtils.copyProperties(xsgbdyfjfModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getXsgbdyQryTitle();//查询表头
		List<String[]> resList = service.getXsgbdyQryResult(xsgbdyfjfModel);//查询结果

		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("realTable", "xsgbdyfjfb");
		request.setAttribute("tableName", "view_xsgbdyfjf");
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		return mapping.findForward("xxgbdyfjfwh");
	}
	
	/**
	 * 学生干部德育附加分单个增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyfjfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String xh = request.getParameter("xh");
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			resMap = service.getStuInfo(xh);//获取学生信息
			if (resMap != null) {
				resMap.put("stuExists", "yes");
			} else {
				resMap.put("stuExists", "no");
			}
		}
		List<HashMap<String, String>> djList = service.getDjList(1);//获取等级列表
		request.setAttribute("djList", djList);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xsgbdyfjfadd");
	}
	
	/**
	 * 学生干部德育附加分单个保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		XsgbdyfjfModel xsgbdyfjfModel = new XsgbdyfjfModel();
		BeanUtils.copyProperties(xsgbdyfjfModel, zjjdForm);
		boolean bFlag = service.saveXsgbdyfjf(xsgbdyfjfModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> djList = service.getDjList(1);//获取等级列表
		request.setAttribute("djList", djList);
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		zjjdForm.setDrzw(DealString.toGBK(zjjdForm.getDrzw()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("xsgbdyfjfadd"); 
	}
	
	/**
	 * 学生干部德育附加分显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> resMap = service.getXsgbdyfxx(pkValue);//获取学生信息
		List<HashMap<String, String>> djList = service.getDjList(1);//获取等级列表
		request.setAttribute("djList", djList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", resMap);
		if (resMap != null) {
			zjjdForm.setXn(resMap.get("xn"));
			zjjdForm.setXq(resMap.get("xq"));
			zjjdForm.setDrzw(resMap.get("drzw"));
			zjjdForm.setRzsj(resMap.get("rzsj"));
			zjjdForm.setJf(resMap.get("jf"));
			zjjdForm.setBz(resMap.get("bz"));
			zjjdForm.setKhdj(resMap.get("khdj"));
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xsgbdyfmodi");
	}
	
	/**
	 * 学生干部德育附加分修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		zjjdForm.setXh(request.getParameter("xh"));
		XsgbdyfjfModel xsgbModel = new XsgbdyfjfModel();
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		BeanUtils.copyProperties(xsgbModel, zjjdForm);
		boolean bFlag = service.xsgbdyfModi(pkValue, xsgbModel, request);//修改保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> djList = service.getDjList(1);//获取等级列表
		request.setAttribute("djList", djList);
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		zjjdForm.setDrzw(DealString.toGBK(zjjdForm.getDrzw()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("xsgbdyfmodi");
	}
	
	/**
	 * 学生干部德育分批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		String sDel = service.xsgbdyDel(zjjdForm.getCbv(), request);//批量删除
		if (!StringUtils.isNull(sDel)) {//删除未成功
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("第 %1$s 条数据删除失败！", sDel));
		} else {//删除成功
			request.setAttribute("deleted", "yes");
		}
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面所有列表
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xsgbdyfjfb");
		request.setAttribute("tableName", "view_xsgbdyfjf");
		return mapping.findForward("xxgbdyfjfwh");
	}
	
	/**
	 * 综合素质测评首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		request.setAttribute("realTable", "zjjd_zhszcp");
		request.setAttribute("tableName", "view_zjjd_zhszcp");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 综合素质测评查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		ZhszcpModel zhszcpModel = new ZhszcpModel();//综合素质查询MODEL
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getQryTitle("zhszcp");//查询表头
		List<String[]> resList = service.getZhszcpQryResult(zhszcpModel);//查询结果
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("realTable", "zjjd_zhszcp");
		request.setAttribute("tableName", "view_zjjd_zhszcp");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 综合素质测评单个增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			resMap = service.getZhszcpf(xh, xn, xq);//获取学生相关表现分
			if (resMap != null) {
				resMap.put("stuExists", "yes");
			} else {
				resMap.put("stuExists", "no");
			}
		}
		List<String[]> cjList = service.getcjList(xh, xn, xq);
		request.setAttribute("cjList", cjList);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * 综合素质测评单个保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		String xh = request.getParameter("xh");
		zjjdForm.setXh(xh);
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		boolean bFlag = service.zhszcpSave(zhszcpModel, request);//数据保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * 综合素质测评批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String sDel = service.zhszcpDel(zjjdForm.getCbv(), request);//批量删除
		if (!StringUtils.isNull(sDel)) {//删除失败
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("第 %1$s 条数据删除失败！", sDel));
		} else {//删除成功
			request.setAttribute("deleted", "yes");
		}
		request.setAttribute("realTable", "zjjd_zhszcp");
		request.setAttribute("tableName", "view_zjjd_zhszcp");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 综合素质测评修改显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = service.getZhszcpInfo(pkValue);//显示详细信息
 		request.setAttribute("pkValue", pkValue);
 		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		if (resMap != null) {
			zjjdForm.setXn(resMap.get("xn"));
			zjjdForm.setXq(resMap.get("xq"));
			zjjdForm.setNd(resMap.get("nd"));
			zjjdForm.setZyfjf(resMap.get("zyfjf"));
			zjjdForm.setTycj(resMap.get("tycj"));
			zjjdForm.setTyfjf(resMap.get("tyfjf"));
			zjjdForm.setBz(resMap.get("bz"));
			zjjdForm.setTyzf(resMap.get("tyzf"));
			zjjdForm.setTyxj(resMap.get("tyxj"));
			zjjdForm.setZyzf(resMap.get("zyzf"));
			zjjdForm.setZyxj(resMap.get("zyxj"));
		}
		List<String[]> cjList = service.getcjList(resMap.get("xh"), resMap.get("xn"), resMap.get("xq"));
		request.setAttribute("cjList", cjList);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * 综合素质测评修改单个保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		zjjdForm.setXh(request.getParameter("xh"));
		String pkValue = request.getParameter("pkValue");
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		boolean bFlag = service.zhszcpModi(pkValue, zhszcpModel, request);//单个修改保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap<String, String>());
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * 综合素质测评数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDataExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")+"/print/zjjdzhszcpb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.zhszcpPrint(wwb, zhszcpModel);
		return mapping.findForward("");
	}
	
	/**
	 * 奖学金上报（辅导员）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//学院用户
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			jxjpdModel.setXn(jxjsqxnxqnd[0]);
			jxjpdModel.setXq(jxjsqxnxqnd[1]);
			jxjpdModel.setNd(jxjsqxnxqnd[2]);
		}
		String xh = "";
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
			jxjpdModel.setXq(jxjpdModel.getXq());
		} else {
			jxjpdModel.setXq(jxjpdModel.getXq());
			xh = request.getParameter("xh");
		}
		jxjpdModel.setXh(xh);
		if (!StringUtils.isNull(xh)) {
			resMap = service.getJxjpdxx(jxjpdModel);//获取学生评定信息
			if (resMap != null) {//该生信息存在
				resMap.put("stuExists", "yes");
			} else {//该生信息不存在
				resMap.put("stuExists", "no");
			}
		}
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendJxjList(request);//奖学金列表
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		request.setAttribute("userType", userType);
		return mapping.findForward("jxjsqdefault");
	}
	
	
	/**
	 * 奖学金申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//学院用户
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		String xh = "";
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		jxjpdModel.setXn(request.getParameter("xn"));
		jxjpdModel.setNd(request.getParameter("nd"));
		String xqdm=request.getParameter("xq");
		jxjpdModel.setXq(xqdm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getJxjpdxx(jxjpdModel);//获取学生评定信息
		resMap.put("stuExists", "yes");
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		
		boolean bCj  = service.stuCjFlag(xh, jxjpdModel.getXn(), jxjpdModel.getXq());//学生是否有成绩
		if (!bCj) {
			request.setAttribute("cjFlag", "no");
		} else {
			boolean tjFlag = ApplyAction.pdStuTjFlag(zjjdForm.getXh(), zjjdForm.getJxjdm(), "jxj");
			if (!tjFlag) {
				request.setAttribute("failinfo", "true");
			} else {
				boolean bFlag = service.jxjsqSave(jxjpdModel, request);//申请保存
				if (bFlag) {//成功
					request.setAttribute("inserted", "yes");
				} else {//失败
					request.setAttribute("inserted", "no");
				}
			}
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendJxjList(request);//奖学金列表
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setTzjkbzdj(DealString.toGBK(zjjdForm.getTzjkbzdj()));
		zjjdForm.setSzxyj(DealString.toGBK(zjjdForm.getSzxyj()));
		zjjdForm.setFdyyj(DealString.toGBK(zjjdForm.getFdyyj()));
		return mapping.findForward("jxjsqdefault");
	}
	
	/**
	 * 奖学金申请结果查询首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqqryDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			String xh = session.getAttribute("userName").toString();
			List<HashMap<String, String>> topList = service.getQryTitle("stusqxx");//查询表头
			List<String[]>  resList = service.stuJxjSqxx(xh);//学生奖学金申请信息
			request.setAttribute("rs", resList);
			request.setAttribute("num", resList != null ? resList.size() : 0);//申请次数
			request.setAttribute("topTr", topList);
			return mapping.findForward("stujxjqry");
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "view_xsjxjb");
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * 奖学金申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getQryTitle("xsjxjb");//查询表头
		List<String[]> resList = service.getJxjsqQryResult(jxjpdModel);//查询结果
		zjjdForm.setXh(DealString.toGBK(zjjdForm.getXh()));
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "view_xsjxjb");
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * 奖学金增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//学院用户
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			jxjpdModel.setXn(jxjsqxnxqnd[0]);
			jxjpdModel.setXq(jxjsqxnxqnd[1]);
			jxjpdModel.setNd(jxjsqxnxqnd[2]);
		}
		String xh = request.getParameter("xh");
		if (!StringUtils.isNull(xh)) {
			resMap = service.getJxjpdxx(jxjpdModel);//获取学生评定信息
			if (resMap != null) {//该生信息存在
				resMap.put("stuExists", "yes");
			} else {//该生信息不存在
				resMap.put("stuExists", "no");
			}
		}
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendJxjList(request);//奖学金列表
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("jxjadd");
	}
	
	/**
	 * 奖学金批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String sDel = service.jxjDel(zjjdForm.getCbv(), request);//批量删除
		if (StringUtils.isNull(sDel)) {//删除成功
			request.setAttribute("deleted", "yes");
		} else {//删除失败
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("第 %1$s 条数据删除失败！", sDel));
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "view_xsjxjb");
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * 奖学金修改显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjmodiView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getJxjModixx(pkValue);//获取该生信息
		String[] jxjshList = service.jxjshResult(pkValue);
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		request.setAttribute("rs", resMap);
		if (jxjshList != null && jxjshList.length == 2) {
			request.setAttribute("xysh", jxjshList[0]);
			request.setAttribute("xxsh", jxjshList[1]);
		}
		zjjdForm.setTzjkbzdj(resMap.get("tzjkbzdj"));
		zjjdForm.setBjpddj(resMap.get("bjpddj"));
		zjjdForm.setSzxyj(resMap.get("szxyj"));
		zjjdForm.setFdyyj(resMap.get("fdyyj"));
		zjjdForm.setJxjdm(resMap.get("jxjdm"));
		commonAction.appendProperties(request, xydm, zydm, nj);
		commonAction.appendJxjList(request);//奖学金列表
		request.setAttribute("pdList", pdList);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjmodiview");
	}
	
	/**
	 * 奖学金修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = new HashMap<String, String>();
		zjjdForm.setXn(request.getParameter("xn"));
		zjjdForm.setNd(request.getParameter("nd"));
		zjjdForm.setXq(request.getParameter("xq"));
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		boolean bFlag = service.jxjModi(pkValue, jxjpdModel, request);//修改保存
		if (bFlag) {//成功
			request.setAttribute("inserted", "yes");
		} else {//失败
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		commonAction.appendJxjList(request);//奖学金列表
		request.setAttribute("pdList", pdList);
		zjjdForm.setTzjkbzdj(DealString.toGBK(zjjdForm.getTzjkbzdj()));
		zjjdForm.setSzxyj(DealString.toGBK(zjjdForm.getSzxyj()));
		zjjdForm.setFdyyj(DealString.toGBK(zjjdForm.getFdyyj()));
		return mapping.findForward("jxjmodiview");
	}
	
	/**
	 *  奖学金数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjdataExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		String modelPath = "";//数据表格模板
		modelPath = servlet.getServletContext().getRealPath("")+"/print/zjjdjxjpdb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.jxjPrint(wwb, jxjpdModel);//奖学金数据导出
		return mapping.findForward("");
	}
	
	/**
	 * 奖学金保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//学院用户
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		jxjpdModel.setXn(request.getParameter("xn"));
		jxjpdModel.setNd(request.getParameter("nd"));
		jxjpdModel.setXq(request.getParameter("xq"));
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		boolean bFlag = service.jxjsqSave(jxjpdModel, request);//申请保存
		if (bFlag) {//成功
			request.setAttribute("inserted", "yes");
		} else {//失败
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendJxjList(request);//奖学金列表
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setTzjkbzdj(DealString.toGBK(zjjdForm.getTzjkbzdj()));
		zjjdForm.setSzxyj(DealString.toGBK(zjjdForm.getSzxyj()));
		zjjdForm.setFdyyj(DealString.toGBK(zjjdForm.getFdyyj()));
		return mapping.findForward("jxjadd");
	}
	
	/**
	 * 荣誉称号申请首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		
		//学生用户进行申请
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		if (!StringUtils.isNull(xh)) {
			resMap = service.getXxtj(xh);//荣誉称号评选条件
			
		} 
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendRychList(request);//加载荣誉称号列表
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("rychsq");
	}
	
	/**
	 * 荣誉称号保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		RychModel rychModel = new RychModel();
		//学生用户进行申请
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getXxtj(xh);//荣誉称号评选条件
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendRychList(request);//加载荣誉称号列表
		BeanUtils.copyProperties(rychModel, zjjdForm);
		rychModel.setXh(xh);
		boolean bCj  = service.stuCjFlag(xh, jxjsqxnxqnd[0], jxjsqxnxqnd[1]);//学生是否有成绩
		if (!bCj) {
			request.setAttribute("cjFlag", "no");
		} else {
			boolean tjFlag = ApplyAction.pdStuTjFlag(zjjdForm.getXh(), zjjdForm.getRychdm(), "rych");
			if (!tjFlag) {
				request.setAttribute("failinfo", "true");
			} else {
				boolean bFlag = service.rychSave(rychModel, request);//保存信息
				if (bFlag) {
					request.setAttribute("inserted", "yes");
				} else {
					request.setAttribute("inserted", "no");
				}
			}
		}
		
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("rychsq");
	}
	
	/**
	 * 荣誉称号申请查询首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();	
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		String xn = zjjdForm.getXn();
		String xq = zjjdForm.getXq();
		if(xn==null){
			if(jxjsqxnxqnd!=null){
				zjjdForm.setXn(jxjsqxnxqnd[0]);
			}
		}
		if(xq==null){
			if(jxjsqxnxqnd!=null){
				zjjdForm.setXq(jxjsqxnxqnd[1]);
			}
		}
		//学院用户直接获取其部门代码
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychsqqry");
	}
	
	/**
	 * 荣誉称号查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		//学院用户直接获取其部门代码
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		RychModel rychModel = new RychModel();//荣誉称号查询MODEL
		BeanUtils.copyProperties(rychModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getQryTitle("xsrychb");//查询表头
		List<String[]> resList = service.rychQryResult(rychModel);//查询结果
		zjjdForm.setXh(DealString.toGBK(zjjdForm.getXh()));
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychsqqry");
	}
	
	/**
	 * 荣誉称号单个增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		//学生用户进行申请
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getXxtj(xh);//荣誉称号评选条件
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendRychList(request);//加载荣誉称号列表
		
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("rychadd");
	}
	
	/**
	 * 荣誉称号保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsaveRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		RychModel rychModel = new RychModel();
		//学生用户进行申请
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getXxtj(xh);//荣誉称号评选条件
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//奖学金申请学年，学期，年度
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		commonAction.appendRychList(request);//加载荣誉称号列表
		BeanUtils.copyProperties(rychModel, zjjdForm);
		rychModel.setXh(xh);
		boolean bFlag = service.rychSave(rychModel, request);//保存信息
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("rychadd");
	}
	
	/**
	 * 荣誉称号修改显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychmodiView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = service.getRychXx(pkValue);//显示详细信息
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		if (resMap != null) {
			zjjdForm.setWydj(resMap.get("wydj"));
			zjjdForm.setJsjdj(resMap.get("jsjdj"));
			zjjdForm.setBjpddj(resMap.get("bjpddj"));
			zjjdForm.setRychdm(resMap.get("rychdm"));
			zjjdForm.setBz(resMap.get("bz"));
		}
		commonAction.appendRychList(request);//加载荣誉称号列表
		return mapping.findForward("rychview");
	}
	
	/**
	 * 荣誉称号修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		zjjdForm.setXh(request.getParameter("xh"));
		RychModel rychModel = new RychModel();
		BeanUtils.copyProperties(rychModel, zjjdForm);
		boolean bFlag = service.rychModi(rychModel, pkValue, request);//保存信息
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//班级评定列表
		request.setAttribute("pdList", pdList);
		commonAction.appendRychList(request);//加载荣誉称号列表
		request.setAttribute("rs", new HashMap<String, String>());
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("rychview");
	}
	
	/**
	 * 荣誉称号批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		//学院用户直接获取其部门代码
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String sDel = service.rychDel(zjjdForm.getCbv(), request);
		if (StringUtils.isNull(sDel)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", sDel);
		}
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychsqqry");
	}
	
	/**
	 * 奖学金申请学生查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stujxjQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String xh = session.getAttribute("userName").toString();
		List<HashMap<String, String>> topList = service.getQryTitle("stusqxx");//查询表头
		List<String[]>  resList = service.stuJxjSqxx(xh);//学生奖学金申请信息
		request.setAttribute("rs", resList);
		request.setAttribute("num", resList != null ? resList.size() : 0);//申请次数
		request.setAttribute("topTr", topList);
		return mapping.findForward("stujxjqrys");
	}
	/**
	 * 公寓表现分维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gybxfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		
		PjpyZjjdService service = new PjpyZjjdService();
		PjpyZjjdActionForm myForm = (PjpyZjjdActionForm) form;
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
//		String xm = DealString.toGBK(myForm.getXm());
		String tableName = "view_pjpy_gybxf";
		String realTable = "gybxfcjb";

		// 取得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] { "pk","xn","xqmc","xh","xm", "xymc", "zymc",
					"bjmc", "rq" };
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			vector = service.getGybxfList(myForm);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("gybxfManage");
	}
	
	/**
	 * 公寓表现分维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gybxfOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");

		PjpyZjjdService service = new PjpyZjjdService();
		PjpyZjjdActionForm myForm = (PjpyZjjdActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			rs.put("jxnd", "1");
		}
		if ("save".equalsIgnoreCase(doType)) {
			
			String xn=request.getParameter("xnV");
			String xq=request.getParameter("xqV");
			
			if(!Base.isNull(xn)){
				myForm.setXn(xn);
			}
			if(!Base.isNull(xq)){
				myForm.setXq(xq);
			}
			
			boolean result = service.saveGybxf(myForm, request);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			
			// 学号
			String xh = myForm.getXh();
			// 学年
			xn = myForm.getXn();
			// 学期
			xq = myForm.getXq();
			// 日期
			String rq = myForm.getRq();
			
			rs = service.getGybxfOne(xh + xn + xq + rq);
		}
		if ("edit".equalsIgnoreCase(doType)|| "view".equalsIgnoreCase(doType)) {
			rs = service.getGybxfOne(pkValue);
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean result = service.delGybxf(pkValue,request);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/pjpyzjjdwh.do?method=gybxfManage", false);
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		return mapping.findForward("gybxfOne");
	}
		
}
