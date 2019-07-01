package xgxt.pjpy.jhzy.pjsz;

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
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.pjpy.jhzy.JhzyPjpyForm;
import xgxt.pjpy.jhzy.JhzyPjpyUnit;
import xgxt.utils.String.StringUtils;

public class PjszAction extends DispatchAction {
	public ActionForward dzzsjhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
		String userType;

		String userDep;

		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		userDep = session.getAttribute("userDep").toString();
		
		PjszService myService = new PjszService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String bmdm = myForm.getXydm();
		String zgh = myForm.getZgh();
		String nj = myForm.getNj();
		String jsbmdm = myForm.getBmdm();
		String fdyxm = myForm.getFdyxm();
		bmdm = Base.chgNull(bmdm, "%", 0);
		nj = Base.chgNull(nj, "%", 0);
		zgh = Base.chgNull(zgh, "", 0);
		fdyxm = Base.chgNull(fdyxm, "", 0);

		userDep = request.getSession().getAttribute("userDep")
		.toString();
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			bmdm = userDep;
		}
		
		List fdyList = myService.getFdyList(jsbmdm);
		List zyList = myService.getZyList(nj,bmdm);
		List fdyZyList = myService.fdyZyList(zgh);
		HashMap<String, String> fdyInfo = myService.getFdyInfo(zgh, jsbmdm);
		
		if (fdyInfo == null) {
			fdyInfo = new HashMap<String, String>();
		}

		myForm.setFdyxm(fdyxm);
		
		request.setAttribute("bmList", myService.getBmList());
		request.setAttribute("fdyList", fdyList);
		request.setAttribute("zyList", zyList);
		request.setAttribute("fdyZyList", fdyZyList);
		request.setAttribute("fdyInfo", fdyInfo);
		request.setAttribute("njList",  Base.getNjList());// 发送年级列表
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		if(bmdm.equalsIgnoreCase("%")){
			bmdm = "";
		}
		return mapping.findForward("dzzsjhf");
	}
	
	public ActionForward dzzsjhfSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjszService myService = new PjszService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String zgh = myForm.getZgh();
		String[] zydm = myForm.getZydms();
		myService.saveDzzbsjhf(zgh,zydm,request);
		return dzzsjhf(mapping,form,request,response);
	}
	/**
	 * @describe 条件设置
	 * @author 
	 * @throws Exception
	 */
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String tableName = "";
		String realTable = "";
		String title = "";
		String userType = session.getAttribute("userType").toString();
		String szlx = request.getParameter("szlx");
		szlx = Base.isNull(szlx) ? "rych" : szlx;
		boolean result = false;
		PjszService service = new PjszService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		PjszModel myModel = new PjszModel();
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if(StringUtils.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		
//		boolean bool = service.isTjsz("05251020225", "2008-2009", "", "rych", "001");
		
		if ((request.getParameter("type") != null)
				&& request.getParameter("type").equalsIgnoreCase("save")) {
			JhzyPjpyUnit.formToGBK(myForm);
			BeanUtils.copyProperties(myModel, myForm);
			result = service.saveTjsz(myModel,request);					
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		if ((request.getParameter("type") != null)
				&& request.getParameter("type").equalsIgnoreCase("del")) {
			String pk = request.getParameter("pk");
			result = service.delTj(pk, request);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		title = "评奖评优 - 条件设置";
		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] {"pk","xn","xq","szlx","jxjdm","tjzd","tjmc","tjlx","tjz"};
			if("rych".equalsIgnoreCase(szlx)){
				colList = new String[] {"pk","xn","szlx","jxjdm","tjzd","tjmc","tjlx","tjz"};
			}
			rs = service.getTjList(myModel, szlx, colList);
		}
		request.setAttribute("path", "prise_condition.do");
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable,
				new ArrayList<String[]>(), new ArrayList<String[]>());
		request.setAttribute("title", title);
		request.setAttribute("szlx", szlx);
		request.setAttribute("rs", rs);
		request.setAttribute("zdList", service.getZdList());
		request.setAttribute("rychList",service .serv_getRychList());
		request.setAttribute("jxjList", service.getJxjList(""));
		request.setAttribute("userType", userType);
		
		return mapping.findForward("pjpytjsz");
	}
}
