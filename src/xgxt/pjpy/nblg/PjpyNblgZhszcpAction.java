package xgxt.pjpy.nblg;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyNblgZhszcpAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	
	private PjpyNblgZhszcpService service = PjpyNblgZhszcpService.getInstance();

	/**
	 * 道德评议成绩维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ddpycjWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyNblgZhszcpActionForm nblgForm = (PjpyNblgZhszcpActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();//用户类型
		String userDep = session.getAttribute("userDep") == null ? ""
				: session.getAttribute("userDep").toString();//用户部门
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nblgForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		zydm = nblgForm.getZydm();
		nj = nblgForm.getNj();
		String act = request.getParameter("act");
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		String cjlx = nblgForm.getCjlx();
		String tableName = "";//视图名
		String realTable = "pjpy_ddpycjb";//表名
		if (StringUtils.isNull(nblgForm.getCjlx())) {
			nblgForm.setCjlx("0");
		}
		if (!StringUtils.isNull(cjlx)) {
			if ("0".equalsIgnoreCase(cjlx)) {//德育评议
				realTable = "pjpy_ddpycjb";
			} else if ("1".equalsIgnoreCase(cjlx)) {//行为记实
				realTable = "pjpy_xwjscjb";
			} else if ("2".equalsIgnoreCase(cjlx)) {//生活园区记实
				realTable = "pjpy_shyqjsb";
			} 
		}
		tableName = "view_" + realTable;
		if (!StringUtils.isNull(act) && "query".equalsIgnoreCase(act)) {
			PjpyNblgModel model = new PjpyNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			titList = service.title(cjlx);
			rsList = service.result(cjlx, model);
		} 
		if (!StringUtils.isNull(act) && "delete".equalsIgnoreCase(act)) {
			String res = service.delete(nblgForm.getCbv(), realTable, request);
			if (StringUtils.isNull(res)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "操作完成,其中第" + res + "条数据操作失败!");
			}
		}
		nblgForm.setXm(DealString.toGBK(nblgForm.getXm()));
		appendProperties(request, xydm, zydm, nj);
		appendQryResult(request, titList, rsList);
		appendTableXx(request, tableName, realTable);//页面增加表名,视图名
		return mapping.findForward("ddpycjpage");
	}
	
	/**
	 * 德育成绩增加页面(包含:德育评议成绩,行为记实成绩,生活园区成绩)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dycjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyNblgZhszcpActionForm nblgForm = (PjpyNblgZhszcpActionForm) form;
		String params = request.getParameter("params");
		String act = request.getParameter("act");
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			if (rs==null) {
				rs.put("stuExists", "no");
			}
		}
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			PjpyNblgModel model = new PjpyNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			boolean bFlag = service.save(model, params, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			nblgForm.setBz(DealString.toGBK(nblgForm.getBz()));
		}
		request.setAttribute("dycjName", service.getDycjName(params));
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		request.setAttribute("params", params);
		return mapping.findForward("dycjaddpage");
	}
	
	/**
	 * 德育成绩修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dycjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNblgZhszcpActionForm nblgForm = (PjpyNblgZhszcpActionForm) form;
		
		String params = request.getParameter("params");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("view".equalsIgnoreCase(act)) {
			request.setAttribute("write", "no");
		} else if ("save".equalsIgnoreCase(act)) {
			PjpyNblgModel model = new PjpyNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			boolean bFlag = service.update(pkValue, model, request, params);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			nblgForm.setBz(DealString.toGBK(nblgForm.getBz()));
		}
		rs = service.view(pkValue, params);
		nblgForm.setXn(rs.get("xn"));
		nblgForm.setBz(rs.get("bz"));
		nblgForm.setDycj(rs.get("dycj"));
		request.setAttribute("dycjName", service.getDycjName(params));
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		request.setAttribute("params", params);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("dycjmodipage");
	}
	
	/**
	 * 综合测评页面
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
		PjpyNblgZhszcpActionForm nblgForm = (PjpyNblgZhszcpActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();//用户类型
		String userDep = session.getAttribute("userDep") == null ? ""
				: session.getAttribute("userDep").toString();//用户部门
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nblgForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		zydm = nblgForm.getZydm();
		nj = nblgForm.getNj();
		String act = request.getParameter("act");
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		if (!StringUtils.isNull(act) && "query".equalsIgnoreCase(act)) {
			PjpyNblgModel model = new PjpyNblgModel();
			BeanUtils.copyProperties(model, nblgForm);
			titList = service.zhcpTitle();
			rsList = service.zhcpResult(model);
		}
		if (!StringUtils.isNull(act) && "autocount".equalsIgnoreCase(act)) {
			boolean bFlag = service.zhszcpAutoCount(nblgForm.getXn(), xydm);
			if (bFlag) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "操作失败!");
			}
		}
		appendQryResult(request, titList, rsList);
		appendProperties(request, xydm, zydm, nj);
		nblgForm.setXm(DealString.toGBK(nblgForm.getXm()));
		return mapping.findForward("zhszcppage");
	}
	
	/**
	 * 综合测评数据导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNblgModel model = new PjpyNblgModel();
		model.setXn(request.getParameter("xn"));
		model.setBjdm(request.getParameter("bjdm"));
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/nblg_zhszcpb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.zhszcpPrint(wwb, model);
		return mapping.findForward("");
	}
}
