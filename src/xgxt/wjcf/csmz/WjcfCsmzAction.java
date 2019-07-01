
package xgxt.wjcf.csmz;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院违纪处分Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class WjcfCsmzAction extends BasicAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	String xq = "";
	
	/**
	 * 撤消处分审核默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfshDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		WjcfCsmzService service = new WjcfCsmzService();
		HttpSession session    = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		用户是学院时
		
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		if (StringUtils.isNull(csmzForm.getXn())) {
			csmzForm.setXn(Base.currXn);
		}
		
		wjcfcsmzcxcfQry(mapping, form, request, response);
		
//		appendProperities(request, xydm, zydm, nj, xq);
		request.setAttribute("userType", session.getAttribute("userType"));
		List<HashMap<String, String>> slList = service.getList();//获取审核列表
		request.setAttribute("shList", slList);
		return mapping.findForward("wjcfcxmzcxcfshdefault");
	}
	
	/**
	 * 撤消处分查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user =getUser(request);
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String xh = DealString.toGBK(csmzForm.getXh());
		String xm = DealString.toGBK(csmzForm.getXm());

		HttpSession session   = request.getSession();

		String userName= session.getAttribute("userName").toString();
		String userType= session.getAttribute("userType").toString();//用户类型
		String isFdy= session.getAttribute("fdyQx").toString();//辅导员权限
		
		//用户是学院或管理员，没有审核单位的分配
		
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		if (StringUtils.isNull(csmzForm.getXn())) {
			csmzForm.setXn(Base.currXn);
		}
		WjcfCsmzCxcfModel wjcfcsmzcxcfModel = new WjcfCsmzCxcfModel();//查询MODEL
		BeanUtils.copyProperties(wjcfcsmzcxcfModel, csmzForm);
		WjcfCsmzService service = new WjcfCsmzService();
		//根据用户类型不同来决定输出表头
		List<HashMap<String, String>> topList = service.getSearchTitle(userType, isFdy);
		List<String[]> resList = new ArrayList<String[]>();
		
		//重庆工程职业（辅导员、班主任，学院，学校三级审核）
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			resList=service.getSearchResult(user, wjcfcsmzcxcfModel);
		}else{
			resList=service.getSearchResult(userName, userType, isFdy, wjcfcsmzcxcfModel);
		}
		csmzForm.setXh(xh);
		csmzForm.setXm(xm);
		//appendProperities(request, xydm, zydm, nj, xq);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("userType", userType);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		List<HashMap<String, String>> slList = service.getList();//获取审核列表
		request.setAttribute("shList", slList);
		return mapping.findForward("wjcfcxmzcxcfshdefault");
	}
	
	/**
	 * 撤消处分信息显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfCsmzService service = new WjcfCsmzService();
		String userType = request.getSession().getAttribute("userType").toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		HashMap<String, String> resMap =new HashMap<String,String>();
		
		
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			resMap= service.getCxcfQryRestlt(user, pkVal);
		}else{
			resMap= service.getCxcfQryRestlt(userType, isFdy, pkVal);
		}
		
		List<HashMap<String, String>> slList = service.getList();//获取审核列表
		
		csmzForm.setSh(resMap.get("sh"));
		csmzForm.setShyj(resMap.get("shyj"));
		csmzForm.setJcsj(resMap.get("jcsj"));
		csmzForm.setJcwh(resMap.get("jcwh"));
		csmzForm.setFwbm(resMap.get("fwbm"));
		csmzForm.setCxjg(resMap.get("cxjg"));
		csmzForm.setBz(resMap.get("bz"));
		request.setAttribute("pkValue", pkVal);
		request.setAttribute("shList", slList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("wjcfcsmzcxcfview");
	}
	
	/**
	 * 撤消处分审核信息保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String jdwh = DealString.toGBK(csmzForm.getJdwh());
		
		WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel = new WjcfCsmzCxcfSaveModel();
		BeanUtils.copyProperties(wjcfsmzcxcfsaveModel, csmzForm);
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfCsmzService service = new WjcfCsmzService();
		
		String userType = request.getSession().getAttribute("userType").toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		HashMap<String, String> resMap = service.getCxcfQryRestlt(userType, isFdy, pkVal);
		List<HashMap<String, String>> slList = service.getList();//获取审核列表
		csmzForm.setSh(resMap.get("sh"));
		csmzForm.setShyj(resMap.get("shyj"));
		csmzForm.setJdwh(jdwh);
		csmzForm.setJcsj(resMap.get("jcsj"));
		csmzForm.setCxjg(resMap.get("cxjg"));
		request.setAttribute("pkValue", pkVal);
		request.setAttribute("shList", slList);
		request.setAttribute("rs", resMap);
		boolean bFlag =false;
		bFlag = service.saveCxcfInfo(userType, isFdy, pkVal, wjcfsmzcxcfsaveModel, request);
		//重庆电子工程职业学院
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			//在WJCFB中加入是否撤销和撤销日期
			bFlag = service.updateCxcfInfo(wjcfsmzcxcfsaveModel);
		}
		
		if (bFlag) {
			request.setAttribute("done", "yes");
		} else {
			request.setAttribute("done", "no");
		}//end if
		csmzForm.setJcwh("");
		csmzForm.setFwbm("");
		csmzForm.setBz("");
		csmzForm.setCxjg("");
		return mapping.findForward("wjcfcsmzcxcfview");
	}
	
	/**
	 * 撤销处分批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cxcfplsh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		WjcfCsmzService service = new WjcfCsmzService();
		String userType = request.getSession().getAttribute("userType").toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String pkString = DealString.toGBK(request.getParameter("pkString"));
		
		if("save".equals(request.getParameter("operType"))){
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel = new WjcfCsmzCxcfSaveModel();
			
			BeanUtils.copyProperties(wjcfsmzcxcfsaveModel, csmzForm);
			boolean result = false;
			if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
				
				result = service.saveCxcfInfoByCqgc(userType, isFdy,isBzr, wjcfsmzcxcfsaveModel);
				
			}else{
				result = service.saveCxcfInfoPl(userType, isFdy, wjcfsmzcxcfsaveModel);
				
			}
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		List<HashMap<String, String>> slList = service.getList();//获取审核列表
		request.setAttribute("pkString", pkString);
		request.setAttribute("shList", slList);
		return mapping.findForward("cxcfplsh");
	}
	
	/**
	 * 撤消处分申请默认页面
	 * cxcfsq---撤消处分申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = new HashMap<String, String>();
		WjcfCsmzService service = new WjcfCsmzService();
		String sUserType = request.getSession().getAttribute("userOnLine")
							.toString();//用户类型
		String xh = request.getParameter("xh");
		if (StringUtils.isEqual(sUserType, "student")) {
			xh = request.getSession().getAttribute("userName").toString();
			resMap = service.getStuInfo(xh, "");//如果是学生则直接查询学生基本信息
		} else {
			resMap = service.getStuInfo(xh, pkValue);//如果是学生则直接查询学生基本信息
		}
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//获取系统当前时间
		//currDate += dd.getTime();
		if (!StringUtils.isNull(pkValue)) {
			resMap = service.getcfInfo(pkValue);
		}
		resMap.put("cxsj", currDate);
		request.setAttribute("rs", resMap);
		if(Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("print", "true");
		}
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * 撤消处分详细信息显示
	 * cxcfsq---撤消处分申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		
		String pkVal = DealString.toGBK(request.getParameter("cfid"));//主键xh||cfwh||cfsj
		WjcfCsmzService service = new WjcfCsmzService();
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getStuInfo(xh, pkVal);//获取学生基本信息及违纪信息
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//获取系统当前时间
		resMap.put("cxsj", currDate);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("rs", resMap);
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * 保存撤消处分申请信息
	 * cxcfSq----撤消处分申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savecxcfSqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		CxcfSqSaveModel cxcfSaveModel = new CxcfSqSaveModel();//撤消处分MODEL
		BeanUtils.copyProperties(cxcfSaveModel, csmzForm);
		WjcfCsmzService service = new WjcfCsmzService();
		String xh = csmzForm.getXh();
		String pkVal = DealString.toGBK(request.getParameter("pkValue"));
		String cfsj = csmzForm.getCfsj();
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {//长沙民政学院
			boolean bHg = service.chkStuTj(cfsj);//检查学生申请条件是否合格,满一年才能申请
			if (bHg) {
				boolean bFlag = service.saveCxcfSqlInfo(cxcfSaveModel, request);
				if (bFlag) {
					request.setAttribute("inserted", "ok");
				}else {
					request.setAttribute("inserted", "no");
				}
			}else {
				request.setAttribute("isHG", "no");
			}
		} else {//其它学校不需要进行条件检查(浙江传媒不需要检查)
				boolean bFlag = service.saveCxcfSqlInfo(cxcfSaveModel, request);
				if (bFlag) {
					request.setAttribute("inserted", "ok");
				}else {
					request.setAttribute("inserted", "no");
				}
		}
		
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getStuInfo(xh, pkVal);//获取学生基本信息及违纪信息
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//获取系统当前时间
		resMap.put("cxsj", currDate);
		request.setAttribute("pk", cxcfSaveModel.getXh()+DealString.toGBK(cxcfSaveModel.getCfwh())+cxcfSaveModel.getCfsj()+currDate);
		resMap.put("cfwh", DealString.toGBK(request.getParameter("cfwh")));
		resMap.put("xn", request.getParameter("xn"));
		resMap.put("xq", request.getParameter("xq"));
		resMap.put("cfsj", request.getParameter("cfsj"));
		resMap.put("cflbmc", DealString.toGBK(request.getParameter("cflbmc")));
		resMap.put("cfyymc", DealString.toGBK(request.getParameter("cfyymc")));
		request.setAttribute("rs", resMap);
		csmzForm.setBz(DealString.toGBK(csmzForm.getBz()));
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * 撤消处分查询默认页面
	 * cxcfcx----撤消处分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfCxDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		WjcfCsmzService service = new WjcfCsmzService();
		String xxdm = StandardOperation.getXxdm();
		String sUserType = request.getSession().getAttribute("userType").toString();//用户类型
		//学生撤消申请查询页面
		if (StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")) {
			String xh = request.getSession().getAttribute("userName").toString();//学号
			List<HashMap<String, String>> topList = service.stuCxcfTitle(xxdm);
			List<String[]> resList = service.stuCxcfResult(xh, xxdm);
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("num", resList != null ? resList.size() : 0);
			return mapping.findForward("stucxcfview");
		}
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		
		csmzCxcfCx(mapping, form, request, response);
		
		//appendProperities(request, xydm, zydm, nj, xq);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("path", "csmz_cxcfcxdefault.do");
		FormModleCommon.commonRequestSet(request);
		List<HashMap<String, String>> slList = service.getList();//获取审核列表
		request.setAttribute("shList", slList);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * 撤消处分查询
	 * cxcfcx----撤消处分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward csmzCxcfCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		User user=getUser(request);
		
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		String xxdm = StandardOperation.getXxdm();
		String xh = DealString.toGBK(csmzForm.getXh());
		String xm = DealString.toGBK(csmzForm.getXm());
		WjcfCsmzCxcfModel cxcfModel = new WjcfCsmzCxcfModel();//页面查询MODEL
		
		cxcfModel.setUser(user);
		BeanUtils.copyProperties(cxcfModel, csmzForm);
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> resList = new ArrayList<String[]>();
		WjcfCsmzService service = new WjcfCsmzService();
		cxcfModel.setUserType(userType);
		topList = service.getCxcfQryTit(cxcfModel, xxdm);
		resList = service.getCxcfQryRes(cxcfModel, xxdm);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", "csmz_cxcfcxdefault.do");
		FormModleCommon.commonRequestSet(request);
		csmzForm.setXh(xh);
		csmzForm.setXm(xm);
		request.setAttribute("topTr", topList);//表头列表
		request.setAttribute("rs", resList);//结果集列表
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		List<HashMap<String, String>> slList = service.getList();//获取审核列表
		request.setAttribute("shList", slList);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * 批量删除撤消处分信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delCxcfInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String[] cbv = csmzForm.getCbv();
		WjcfCsmzService service = new WjcfCsmzService();
		String sFlag = service.delCxcfInfo(cbv);
		if (!StringUtils.isNull(sFlag)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sFlag);
		}//end if
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * 撤消处分详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stucxcfViews(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		
		WjcfCsmzService service = new WjcfCsmzService();
		HashMap<String, String> resMap = service.stuInfoByPk(pkValue);
		
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
	
		return mapping.findForward("scxcfview");
	}
	
	/**
	 * 撤消处分查询单个信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String act = request.getParameter("act");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		WjcfCsmzService service = new WjcfCsmzService();
		HashMap<String, String> resMap = service.stuInfoByPk(pkValue);
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			boolean bFlag = service.update(pkValue, DealString.toGBK(request.getParameter("bz")), request);
			csmzForm.setBz("");
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("act", act);
		request.setAttribute("rs", resMap);
		return mapping.findForward("scxcfview");
	}
	
	/**
	 * 将所有集合存放在request中
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 */
	private void appendProperities(HttpServletRequest request,String xydm, String zydm, String nj, String xq){
		String xy = "";
		String zy = "";
//		String xqLocal = xq;
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
		xy = StringUtils.isNull(xy) ? "" : xy;
		zy = StringUtils.isNull(zy) ? "" : zy;
		nj = StringUtils.isNull(nj) ? "" : nj;
//		xqLocal = xq==null ? "": xq;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String realTable = "wjcf_xskqccb";
		String tableName = "view_xskqxx";
		String sUserType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("userType", sUserType);//用户类型
		request.setAttribute("writeAble", "yes");//读写权
		request.setAttribute("tableName", tableName);//视图名
		request.setAttribute("realTable", realTable);//表名
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
	}
}
