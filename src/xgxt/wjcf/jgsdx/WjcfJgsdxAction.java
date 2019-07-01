
package xgxt.wjcf.jgsdx;

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

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.FormModleCommon;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.csmz.WjcfCsmzService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学违纪处分Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class WjcfJgsdxAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	String xq  = "";
	
	/**
	 * 撤消处分申请默认页面
	 * cxcfSqDefault ---- 撤消处分申请默认页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		//appendProperities(request, xydm, zydm, nj, xq);
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();//用户类型
		String sUserName = "";//用户名
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		//学生用户
		if (StringUtils.isEqual(sUserType, "student") || StringUtils.isEqual(sUserType, "stu")) {
			sUserName = session.getAttribute("userName").toString();
			stuMap = service.getStuInfo(sUserName);//获取学生信息
		}else {//其它用户从页面上获取学号
			String xh = request.getParameter("xh");
			stuMap = service.getStuInfo(xh);
		}
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//获取系统当前时间
		stuMap.put("cxsj", currDate);
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		request.setAttribute("rs", stuMap);
		return mapping.findForward("cxcfsqdefault");
	}
	
	public ActionForward cxcfInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
//		WjcfJgsdxService services = new WjcfJgsdxService();
		WjcfCsmzService service = new WjcfCsmzService();
		String xh = request.getParameter("xh");
		String pkValue = DealString.toGBK(request.getParameter("cfid"));
		HashMap<String, String> resMap = service.getStuInfo(xh, pkValue);
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//获取系统当前时间
		resMap.put("cxsj", currDate);
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		request.setAttribute("rs", resMap);
		String xxdm = StandardOperation.getXxdm();
		if (!Globals.XXDM_JGSDX.equalsIgnoreCase(xxdm)) {
			return mapping.findForward("csmzcxcf");
		}
		return mapping.findForward("cxcfsqdefault");
	}
	/**
	 * 撤消处分申请保存
	 * cxcfSqSave ---- 撤消处分申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		WjcfJgsdxService service = new WjcfJgsdxService();
		CxcfSqSaveModel cxcfSaveModel = new CxcfSqSaveModel();//撤消处分申请
		BeanUtils.copyProperties(cxcfSaveModel, jgsdxForm);
		String xh = jgsdxForm.getXh();
		String cfsj = jgsdxForm.getCfsj();
		boolean bTj = service.chkStuTj(cfsj);
		if (bTj) {//检查学生申请条件是否合格,满一年才能申请
			boolean bFlag = service.saveCxcfSqlInfo(cxcfSaveModel, request);//保存申请数据
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			}else {
				request.setAttribute("inserted", "no");
			}
		}else {
			request.setAttribute("isHG", "no");
		}
		HashMap<String, String> stuMap = service.getStuInfo(xh);//获取学生信息
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//获取系统当前时间
		stuMap.put("cxsj", currDate);
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		request.setAttribute("rs", stuMap);
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * 撤消处分查询默认页面
	 * cxcfCxdefault ---- 撤消处分查询默认页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfCxdefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sUserType = request.getSession().getAttribute("userType").toString();//用户类型
		//学生撤消申请查询页面
		if (StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")) {
			String xh = request.getSession().getAttribute("userName").toString();//学号
			WjcfJgsdxService service = new WjcfJgsdxService();
			List<String[]> stuList = service.getStuWjcfinfo(xh);//学生违纪信息结果
			List<HashMap<String, String>> topList = service.getStuWjcfTit(xh);//表头
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", stuList);
			request.setAttribute("num", stuList != null ? stuList.size() : 0);
			return mapping.findForward("stucxcfcx");
		}
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * 撤消处分查询结果
	 * cxcfCx ---- 撤消处分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		CxcfQryModel cxcfModel = new CxcfQryModel();//撤消处分查询MODEL
		BeanUtils.copyProperties(cxcfModel, jgsdxForm);
		WjcfJgsdxService service = new WjcfJgsdxService();
		List<HashMap<String, String>> topList = service.getCxcfSearchTitle();//查询表头
		List<String[]> resList = service.getCxcfSearchResult(cxcfModel);//查询结果
		jgsdxForm.setXm(DealString.toGBK(jgsdxForm.getXm()));
		
//		appendProperities(request, xydm, zydm, nj, xq);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * 撤消处分单个打印
	 * cxcfPrint ---- 撤消处分打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
//		String xh = request.getParameter("xh");
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>(); 
	    stuMap = service.getStuInfo1(pkValue);//通过主键获取学生相关信息
		request.setAttribute("rs", stuMap);
		return mapping.findForward("cxcfprint");
	}
	
	/**
	 * 撤消处分信息批量删除
	 * cxcfdel ---- 撤消处分删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		String[]keys = jgsdxForm.getCbv();//主键列表
		WjcfJgsdxService service = new WjcfJgsdxService();
		String sJg = service.wjcfCxcfPlDel(keys, request);//撤消处分数据的批量删除
		if (!StringUtils.isNull(sJg)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", "noview");
		}
		
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * 撤消处分审批默认页面
	 * cxcfXxspDefault ---- 撤消处分审批默认
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfXxspDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfxxspdefault");
	}
	
	/**
	 * 撤消处分审批查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfspQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		WjcfJgsdxService service = new WjcfJgsdxService();
		CxcfQryModel cxcfModel = new CxcfQryModel();
		BeanUtils.copyProperties(cxcfModel, jgsdxForm);
		List<HashMap<String, String>> topList = service.getCxcfSpTitle();//查询表头
		List<String[]> resList = service.getCxcfSpResult(cxcfModel);//查询结果
		jgsdxForm.setXm(DealString.toGBK(jgsdxForm.getXm()));
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfxxspdefault");
	}
	
	/**
	 * 撤消处分审批
	 * cxcfSp ---- 撤消处分审批
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));//主键
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> resMap = service.getCxcfInfoByPk(pkValue);//通过主键获取撤消处分详细信息
		List<HashMap<String, String>> spztList = service.getChList(3);//审批状态
		jgsdxForm.setSpzt(resMap.get("spzt"));
		jgsdxForm.setCxsj(resMap.get("cxsj"));
		jgsdxForm.setCxwh(resMap.get("cxwh"));
		request.setAttribute("spztList", spztList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("writeAble", "yes");//读写权
 		return mapping.findForward("cxcfsp");
	}
	
	/**
	 * 撤消处分详细信息显示
	 * cxcfView ---- 撤消处分详细信息显示 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> resMap = service.getCxcfInfoByPk(pkValue);//撤消处分显示信息
		request.setAttribute("rs", resMap);
		request.setAttribute("writeAble", "yes");//读写权
		return mapping.findForward("cxcfview");
	}
	
	/**
	 * 撤消处分审批保存
	 * cxcfSpSave ---- 撤消处分审批保存 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSpSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		CxcfSpSaveModel cxcfsqModel = new CxcfSpSaveModel();//撤消处分审批MODEL
		BeanUtils.copyProperties(cxcfsqModel, jgsdxForm);
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		cxcfsqModel.setPkValue(pkValue);
		WjcfJgsdxService service = new WjcfJgsdxService();
		boolean bFlag = service.cxcfSp(cxcfsqModel, request);
		if (bFlag) {
			request.setAttribute("updated", "yes");
		}else {
			request.setAttribute("updated", "no");
		}
		HashMap resMap = service.getCxcfInfoByPk(pkValue);//撤消处分显示信息
		List<HashMap<String, String>> spztList = service.getChList(3);//审批状态
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		jgsdxForm.setCxwh(DealString.toGBK(jgsdxForm.getCxwh()));
		request.setAttribute("spztList", spztList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", resMap);
		request.setAttribute("writeAble", "yes");//读写权
		return mapping.findForward("cxcfsp");
	}
	
	/**
	 * 学生撤消处分详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuCxcfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> stuMap = service.getCxcfInfoByPk(pkValue);
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("stucxcfview");
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
//		xqLocal = xq==null ? "": xq;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String sUserType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("userType", sUserType);//用户类型
		request.setAttribute("writeAble", "yes");//读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
	}
}
