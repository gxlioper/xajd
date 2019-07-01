
package xgxt.wjcf.szzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 深圳职业信息技术学院学生考勤信息Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-12</p>
 */
public class WjcfSzzyAction extends DispatchAction {
	
	/**
	 * 违纪处分学生考勤信息维护默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szzykqxxwhDefault(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String xydm = "";
		String zydm = "";
		String nj = "";
		String xq = "";
		
		appendProperities(request, xydm, zydm, nj, xq);
		request.setAttribute("userType", request.getSession().getAttribute("userType"));
		return mapping.findForward("wjcfkqxxwh");
	}
	
	/**
	 * 查询出来的数据信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szzykqxxQuery(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		WjcfSzzyActionForm wjcfSzzyForm = (WjcfSzzyActionForm) form;
		String xqj = DealString.toGBK(request.getParameter("xqj"));
		wjcfSzzyForm.setXqj(xqj);
		WjcfSzzyQryModel szzyQryModel = new WjcfSzzyQryModel();
		BeanUtils.copyProperties(szzyQryModel, wjcfSzzyForm);//将FORM中的属性COPY到MODEL
		String xydm = "";
		String zydm = "";
		String nj = "";
		String xq = "";
		appendProperities(request, xydm, zydm, nj, xq);
		WjcfSzzyService services = new WjcfSzzyService();
		ArrayList<HashMap<String, String>> topTr = services.getSearchTitle();//获取表头
		ArrayList<String[]> queryResult = services.getSearchResult(szzyQryModel, "view_xskqxx");//获取查询结果
		wjcfSzzyForm.setXqj(xqj);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", queryResult);
		request.setAttribute("rsNum", queryResult !=null ? queryResult.size() : 0);
		request.setAttribute("userType", request.getSession().getAttribute("userType"));
		return mapping.findForward("wjcfkqxxwh");
	}
	
	/**
	 * 增加考勤信息页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szzyAddkqxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pk = "xn||xq||bjdm||kcdm";
		String pkValue = request.getParameter("pkValue");//教务表中的主键值
		String pkValue2 = request.getParameter("pk");//WJCF—XSKQCCB中的主键值
		String tableName = "view_xskqxx";
		WjcfSzzyService services = new WjcfSzzyService();
		Map<String, String> rs = services.getAddResult(tableName, pk, pkValue, pkValue2);
		if (!StringUtils.isNull(pkValue2) && pkValue2.length() > 0) {
			request.setAttribute("ispk", "yes");
		}
 		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("pkValue2", pkValue2);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("szzy_addkqxx");
	}
	
	/**
	 * 保存考勤信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szzySavekqxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");//教务表中的主键值
		String pkValue2 = request.getParameter("pkValue2");//WJCF—XSKQCCB中的主键值
		String pk = "xn||xq||bjdm||kcdm";
		String tableName = "wjcf_xskqccb";
		WjcfSzzyActionForm wjcfSzzyForm = (WjcfSzzyActionForm) form;
		WjcfSzzyKqxxModel szzyKqxxModel = new WjcfSzzyKqxxModel();
		BeanUtils.copyProperties(szzyKqxxModel, wjcfSzzyForm);//将FORM中的属性COPY到MODEL
		WjcfSzzyService services = new WjcfSzzyService();
		boolean flag = services.saveKqxx(tableName, pkValue, szzyKqxxModel, pkValue2);
		Map<String, String> rs = services.getAddResult("view_xskqxx", pk, pkValue, pkValue2);
		if (flag) {
			request.setAttribute("isszzy", "yes");
		}else{
			request.setAttribute("isszzy", "no");
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("pkValue2", pkValue2);
		request.setAttribute("rs", rs);
		return mapping.findForward("szzy_savekqxx");
	}
	
	/**
	 * 学工考勤信息抽查默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szzyKqccDefault(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String xydm = "";
		String zydm = "";
		String nj = "";
		String xq = "";
		WjcfSzzyService services = new WjcfSzzyService();
		List<HashMap<String, String>> nfList= services.getChkList1(1);//年份列表
		List<HashMap<String, String>> yfList = services.getChkList1(2);//月份列表
		request.setAttribute("nfList", nfList);
		request.setAttribute("yfList", yfList);
		appendProperities(request, xydm, zydm, nj, xq);
		request.setAttribute("userType", request.getSession().getAttribute("userType"));
		return mapping.findForward("szzy_kqxxcc");
	}
	
	/**
	 * 学工考勤信息查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szzyKqxxTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		WjcfSzzyActionForm wjcfSzzyForm = (WjcfSzzyActionForm) form;
		WjcfSzzyQryModel szzyQryModel = new WjcfSzzyQryModel();
		BeanUtils.copyProperties(szzyQryModel, wjcfSzzyForm);//将FORM中的属性COPY到MODEL
		String xydm = "";
		String zydm = "";
		String nj = "";
		String xq = "";
		appendProperities(request, xydm, zydm, nj, xq);
		WjcfSzzyService services = new WjcfSzzyService();
		ArrayList<HashMap<String, String>> topTr = services.getSearchTitle();//获取表头
		ArrayList<String[]> queryResult = services.getKqxxTjJg(szzyQryModel, "wjcf_xskqccb");//获取查询结果
		List<HashMap<String, String>> nfList= services.getChkList1(1);//年份列表
		List<HashMap<String, String>> yfList = services.getChkList1(2);//月份列表
		request.setAttribute("nfList", nfList);
		request.setAttribute("yfList", yfList);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", queryResult);
		request.setAttribute("rsNum", queryResult !=null ? queryResult.size() : 0);
		request.setAttribute("userType", request.getSession().getAttribute("userType"));
		return mapping.findForward("szzy_kqxxcc");
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
		String realTable = "wjcf_xskqccb";
		String tableName = "view_xskqxx";
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}
	
	/**
	 * 学工考勤信息显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szzyKqxxView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pk = "xn||xq||bjdm||kcdm";
		String pkValue = request.getParameter("pkValue");//教务表中的主键值
		String pkValue2 = request.getParameter("pk");//WJCF—XSKQCCB中的主键值
		String tableName = "view_xskqxx";
		WjcfSzzyService services = new WjcfSzzyService();
		Map<String, String> rs = services.getAddResult(tableName, pk, pkValue, pkValue2);
		if (!StringUtils.isNull(pkValue2) && pkValue2.length() > 0) {
			request.setAttribute("ispk", "yes");
		}
		List<HashMap<String, String>> nfList= services.getChkList1(1);//年份列表
		List<HashMap<String, String>> yfList = services.getChkList1(2);//月份列表
		request.setAttribute("nfList", nfList);
		request.setAttribute("yfList", yfList);
 		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("pkValue2", pkValue2);
		return mapping.findForward("szzy_kqxxview");
	}
}
