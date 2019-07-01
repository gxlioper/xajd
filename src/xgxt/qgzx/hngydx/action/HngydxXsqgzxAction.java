/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.hngydx.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.hngydx.dao.HngydxXsqgzxDAO;
import xgxt.qgzx.hngydx.service.HngydxXsqgzxService;
import xgxt.qgzx.service.QgzxService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.ExcelMethods;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块湖南工业学生勤工助学管理Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-11</p>
 */
public class HngydxXsqgzxAction extends DispatchAction {
	
	/** 
	 * Method stationApp 显示申请岗位页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward stationApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		QgzxService qgzxService = new QgzxService();
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String xh = request.getParameter("xh");
		if(userOnLine != null && "student".equalsIgnoreCase(userOnLine)){
			xh = session.getAttribute("userName").toString();
		}
		String getStuInfo = request.getParameter("getStuInfo");
		if(getStuInfo == null || "".equalsIgnoreCase(getStuInfo)){
			getStuInfo = "xh-xm-xb-nj-xymc-zymc-bjmc-ssbh-jg-zzmmmc";
		}
		rs = service.getRangeInfo();
		if(getStuInfo!=null && !"".equalsIgnoreCase(getStuInfo)){
			rs = service.setStuInfo(xh,getStuInfo,rs);
		}
		
		request.setAttribute("sqsjFlag", qgzxService.getSqsjFlag());
		request.setAttribute("whkxList", dao.getWhkxList());
		request.setAttribute("kxList", null);
		request.setAttribute("gwList", dao.getGwList());
		request.setAttribute("kxbz", "no");
		request.setAttribute("rs", rs);
		request.setAttribute("doType", "add");
		request.setAttribute("do", "no");
		request.setAttribute("printView", "printView");//打印申请报表
		return mapping.findForward("success");
	}
	
	/** 
	 * Method addXsgwInfo 增加学勤工助学信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward addXsgwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommanForm xsgw = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("stuExists", "yes");
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		boolean result = true;
		
		result = service.addXsgwInfo(xsgw,request);
		
		request.setAttribute("result",result);
		return this.stationApp(mapping, form, request, response);
	}
	
	
	/** 
	 * Method addXsgwInfo 查询学生勤工助学信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getXsgwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xh = "";
		
		map = service.getStustationInfo(pkValue);
		xh = map.get("xh");
		
		request.setAttribute("kxList", dao.getKxSj(xh));
		request.setAttribute("whkxList", dao.getWhkxList());
		request.setAttribute("gwList", dao.getGwList());
		request.setAttribute("rs", map);
		request.setAttribute("doType", "modi");
		request.setAttribute("do", "yes");		
		return mapping.findForward("success");
	}
	
	/** 
	 * Method modXsgwInfo 修改学生勤工助学信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward modXsgwInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean result = true;
		CommanForm xsgw = (CommanForm) form;
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		
		String gwdm = request.getParameter("gwdm");
		xsgw.setGwdm(gwdm);
		//修改学生岗位申请信息
		result = service.modXsgwInfo(xsgw,request);
		
		request.setAttribute("result", result);				
		return this.stationApp(mapping, form, request, response);
	}
	
	/** 
	 * Method 审核信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getAuditingInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();
		HashMap<String, String> rs = new HashMap<String, String>();
		StuInfoDAO stu = new StuInfoDAO();
		String pkValue = DealString.toGBK(request.getParameter("pkVal"));
		String xxdm = StandardOperation.getXxdm();
		
		//获取基本信息
		rs = service.getStuAppInfo(pkValue);
		
		//学生资助项目		
		List zzrs = stu.getTabCNofXszz(xxdm);
		request.setAttribute("zzrs", zzrs);
		request.setAttribute("zzrssize", zzrs.size());
		
		//获取空闲时间
		request.setAttribute("kxList", dao.getKxSj(rs.get("xh")));		
		request.setAttribute("map", rs);
		return new ActionForward("/qgzx/hngydx/post_stu_check_one.jsp",false);
	}
	
	/** 
	 * Method 导出学生勤工助学花名册
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward expRoster(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/qgzx_hngydx_sqqgzxb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb;
		try {
			wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printRoster(wwb);
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return mapping.findForward("");
	}
	
	
	/** 
	 * Method 导出岗位分布情况表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward expStation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String sql = "select xh,xm,xb,xymc,bjmc,nj,lxdh,(select kh from view_xsxxb b where a.xh=b.xh) kh,(select yrdwmc from yrdwdmb b where b.yrdwdm=(select distinct sqdw from gwxxb c where c.sqdw=b.yrdwdm and c.gwdm=a.gwdm)) yrdwdm,gwdm from view_xsgwxx a where a.xxyj='通过' order by xn,nd,xq,yrdwdm";
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		try {
			Excel2Oracle.exportData(sql, "view_xsgwxx", response .getOutputStream());
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return mapping.findForward("");
	}
	
	
	/** 
	 * Method 修改学生岗位信息备注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward modiXsgwxxRemark(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.getStustationInfo(pkValue));
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modiRemark");
	}	
	
	/** 
	 * 保存学生岗位信息备注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveXsgwxxRemark(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		String pkValue = request.getParameter("pkValue");
		String bz = request.getParameter("bz");
		
		request.setAttribute("result", service.saveXsgwxxRemark(pkValue,bz,request));
		return modiXsgwxxRemark(mapping, form, request, response);
	}
	
	/** 
	 * 打印学生勤工助学申请表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward printXsgwxxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HngydxXsqgzxService service = new HngydxXsqgzxService();
		CommanForm model = (CommanForm) form;
		String xh = request.getParameter("xh");
		String lxdh = request.getParameter("lxdh");
		String yhtc = request.getParameter("yhtc");
		String gzjl = request.getParameter("gzjl");
		
		model.setXh(xh);
		model.setLxdh(lxdh);
		model.setYhtc(yhtc);
		model.setGzjl(gzjl);
		
		request.setAttribute("rs", service.getPintInfo(model));
		return mapping.findForward("printXsgwxxb");
	}
}