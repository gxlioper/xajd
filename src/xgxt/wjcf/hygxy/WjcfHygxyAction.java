
package xgxt.wjcf.hygxy;

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

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.WjcfOperactionAction;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院违纪处分Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-23</p>
 */
public class WjcfHygxyAction extends CommonAction {

	String xydm = "";//学院代码
	String zydm = "";//专业代码
	String nj = "";//年级
	String xq = "";//学期
	
	CommonAction commonAction = null;//数据列表公用ACTION

	/**
	 * 跟踪教育记录默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyjlDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();//用户类型
		String sUserDep = session.getAttribute("userDep").toString();//用户部门
		if (StringUtils.isEqual(sUserType, "xy")) {
			xydm = sUserDep;
		}
		commonAction = new CommonAction();
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面下拉列表
		request.setAttribute("tableName", "view_wjcf_gzjy");//视图名
		request.setAttribute("realTable", "wjcf_gzjyb");//表名
		return mapping.findForward("gzjyjldefault");
	}
	
	/**
	 * 跟踪教育查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String sUserType = session.getAttribute("userType").toString();//用户类型
		String sUserDep = session.getAttribute("userDep").toString();//用户部门
		if (StringUtils.isEqual(sUserType, "xy")) {
			xydm = sUserDep;
			hygxyForm.setXydm(xydm);
		}
		hygxyForm.setXm(DealString.toGBK(hygxyForm.getXm()));
		WjcfGzjyModel gzjyModel = new WjcfGzjyModel();//跟踪教育MODEL
		BeanUtils.copyProperties(gzjyModel, hygxyForm);
		List<HashMap<String, String>> titleList = service.getQueryTitle("wjcf_gzjyb");//查询表头
		List<String[]> resList = service.getGzjyQueryResult(gzjyModel);//查询结果
		commonAction = new CommonAction();
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面下拉列表
		request.setAttribute("topTr", titleList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//查询记录数
		request.setAttribute("tableName", "view_wjcf_gzjy");//视图名
		request.setAttribute("realTable", "wjcf_gzjyb");//表名
		return mapping.findForward("gzjyjldefault");
	}
	
	/**
	 * 跟踪教育记录单个增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		commonAction = new CommonAction();
		WjcfHygxyService service = new WjcfHygxyService();
		HashMap<String, String> resMap = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String tmpPk = request.getParameter("pkval");
		if (!StringUtils.isNull(tmpPk)) {
			resMap = service.getStuWjxx(xh, tmpPk);
		}
		request.setAttribute("xh", xh);
		request.setAttribute("pkval", tmpPk);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面下拉列表
		return mapping.findForward("gzjyadd");
	}
	
	/**
	 * 跟踪教育记录单个增加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		commonAction = new CommonAction();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		WjcfGzjyModel model = new WjcfGzjyModel();//跟踪教育MODEL
		hygxyForm.setXh(request.getParameter("xh"));
		hygxyForm.setCfxn(request.getParameter("cfxn"));
		hygxyForm.setCfxq(request.getParameter("cfxq"));
		hygxyForm.setCfsbsj(request.getParameter("cfsbsj"));
		BeanUtils.copyProperties(model, hygxyForm);
		model.setCfpk(request.getParameter("pkValue"));
		boolean bFlag = service.saveGzjy(model, request);//保存跟踪记录
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		hygxyForm.setJyr("");
		hygxyForm.setJyrbx("");
		hygxyForm.setJysj("");
		hygxyForm.setJyzt("");
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("gzjyadd");
	}
	
	/**
	 * 跟踪教育记录单个修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		commonAction = new CommonAction();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		HashMap<String, String> resMap = service.viewGzjy(pkValue);//显示修改信息
		if (resMap != null) {
			hygxyForm.setXn(resMap.get("xn"));
			hygxyForm.setNd(resMap.get("nd"));
			hygxyForm.setXq("0" + resMap.get("xq"));
			hygxyForm.setJyr(resMap.get("jyr"));
			hygxyForm.setJyrbx(resMap.get("jyrbx"));
			hygxyForm.setJysj(resMap.get("jysj"));
			hygxyForm.setJyzt(resMap.get("jyzt"));
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cfpk", resMap.get("cfpk"));
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", resMap);
		request.setAttribute("act", act);
		return mapping.findForward("gzjyview");
	}
	
	/**
	 * 跟踪教育记录单个修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfHygxyService service = new WjcfHygxyService();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		hygxyForm.setXh(request.getParameter("xh"));
		hygxyForm.setCfpk(request.getParameter("cfpk"));
		WjcfGzjyModel model = new WjcfGzjyModel();
		BeanUtils.copyProperties(model, hygxyForm);
		boolean bFlag = service.updateGzjy(model, pkValue, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		hygxyForm.setJyr("");
		hygxyForm.setJyrbx("");
		hygxyForm.setJysj("");
		hygxyForm.setJyzt("");
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap());
		return mapping.findForward("gzjyview");
	}
	
	/**
	 * 跟踪教育记录批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();//用户类型
		String sUserDep = session.getAttribute("userDep").toString();//用户部门
		if (StringUtils.isEqual(sUserType, "xy")) {
			xydm = sUserDep;
		}
		commonAction = new CommonAction();
		commonAction.appendProperties(request, xydm, zydm, nj);//加载页面下拉列表
		request.setAttribute("tableName", "view_wjcf_gzjy");//视图名
		request.setAttribute("realTable", "wjcf_gzjyb");//表名
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String[] keys = hygxyForm.getCbv();
		String sFlag = service.deleteGzjy(keys, request);//批量删除
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("提示: 第%1$s条数据删除失败!", sFlag));
		}
		return mapping.findForward("gzjyjldefault");
	}
	
	/**
	 * 批量发文
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plfw(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfHygxyActionForm hForm = (WjcfHygxyActionForm) form;
		String pkVal = request.getParameter("str");
		pkVal = StringUtils.isNull(pkVal) ? "" : pkVal.substring(0, pkVal.length()-1);
		WjcfHygxyService service = new WjcfHygxyService();
		//String pkVal ="" ;//= service.listToString(list);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			pkVal = request.getParameter("pkVal");
			String cfsj = request.getParameter("cfsj");
			String cfwh = DealString.toGBK(request.getParameter("cfwh"));
			boolean result = service.plfw(pkVal, cfwh, cfsj);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		hForm.setCfwh(DealString.toGBK(request.getParameter("cfwh")));
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("tips", "当前所在位置：违纪处理 - 审核 - 学校处理决定");
		return mapping.findForward("plfwpage");
	}
	
	/**
	 * 委员会受理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wyhsl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfHygxyActionForm hForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String act = request.getParameter("act");
		hForm.setXm(DealString.toGBK(hForm.getXm()));
		List<HashMap<String, String>> title = new ArrayList<HashMap<String,String>>();
		List<String[]> list = new ArrayList<String[]>();
		if ("query".equalsIgnoreCase(act)) {
			WjcfGzjyModel model = new WjcfGzjyModel();
			BeanUtils.copyProperties(model, hForm);
			title = service.wyhslTitle();
			list = service.wyhslQuery(model);
		}
		request.setAttribute("rs", list);
		request.setAttribute("topTr", title);
		request.setAttribute("rsNum", list != null ? list.size() : 0);
		request.setAttribute("tips", "当前所在位置： 违纪处理 - 申诉处理 - 委员会受理");
		appendProperties(request, xydm, zydm, nj);
		appendCflbCfyy(request);
		return mapping.findForward("wyhslpage");
	}
	
	/**
	 * 委员会单个受理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wyhslone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		WjcfHygxyService service = new WjcfHygxyService();
		HashMap<String, String> rs = service.wyhslone(pkValue);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			WjcfGzjyModel model = new WjcfGzjyModel();
			model.setWyhsl(request.getParameter("wyhsl"));
			model.setWyhsllr(request.getParameter("wyhsllr"));
			boolean flag = service.updateWhysl(model, pkValue, request);
			if (flag) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		request.setAttribute("rs", rs);
		request.setAttribute("tips", "当前所在位置： 违纪处理 - 申诉处理 - 委员会受理");
		request.setAttribute("rswj", WjcfOperactionAction.GetFileList1(pkValue));
		request.setAttribute("pkValue", pkValue);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("wyhslonepage");
	}
	
	/**
	 * 批量受理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plsl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfHygxyActionForm hForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String pkValue = request.getParameter("str");
		pkValue = StringUtils.isNull(pkValue) ? "" : pkValue.substring(0, pkValue.length()-1);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			pkValue = request.getParameter("pkValue");
			boolean result = service.plsl(pkValue, DealString.toGBK(request.getParameter("wyhsl")), DealString.toGBK(request.getParameter("wyhsllr")));
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("pkValue", pkValue);
		hForm.setWyhsl(DealString.toGBK(request.getParameter("wyhsl")));
		hForm.setWyhsllr(DealString.toGBK(request.getParameter("wyhsllr")));
		request.setAttribute("tips", "当前所在位置： 违纪处理 - 申诉处理 - 委员会受理");
		return mapping.findForward("plslpage");
	}
}
