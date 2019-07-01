package xgxt.pjpy.comm.pjpy.pjlc.jgcx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszService;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrInit;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

public class PjpyJgcxAction extends BasicAction {

	/**
	 * 
	 * 评奖评优-结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward pjpyQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		PjpyJgcxForm model = (PjpyJgcxForm) form;
		PjpyJgcxService service = new PjpyJgcxService();
		PjpyXmszService xmszService = new PjpyXmszService();
		PjpyCommService commService = new PjpyCommService();
		
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "pkValue", "xh", "xm", "bjmc",
				"xmmc", "sqsj" };
		String[] topTr = new String[] { "pkValue", "学号", "姓名", "班级名称", "项目名称",
				"申请时间" };
		List<String[]> rs = new ArrayList<String[]>();
		
		if (DEL.equals(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.delPjjl(pkValues) ? DEL_SUCCESS : DEL_FAIL);
			
			doType = QUERY;
		}
		
		if (EXP.equals(doType)) {

			//int pageSize = model.getPages().getPageSize();
			model.getPages().setPageSize(Integer.MAX_VALUE);
			
			colList = new String[] { "xh", "xm", "nj", "xymc", "zymc", "bjmc",
					"xmmc", "xmje", "sqsj","yhmc","yhkh" };
			topTr = new String[] { "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称",
					"项目名称", "项目金额", "申请时间","银行名称","银行卡号" };
			
			Map<String, Object> result = service.queryPjjg(model, colList,
					topTr, request);
			rs = (List<String[]>) result.get("rs");
			topTr = (String[]) result.get("topTr");

			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(rs, topTr, topTr, response
					.getOutputStream());
			return mapping.findForward("");
		}

		doType = QUERY;
		
		if (QUERY.equals(doType)){
			
			User user = getUser(request);
			
			if ("stu".equalsIgnoreCase(user.getUserType())){
				model.setXh(user.getUserName());
			}
			
			Map<String,Object> result = service.queryPjjg(model, colList, topTr,request);
			rs = (List<String[]>) result.get("rs");
			topTr = (String[]) result.get("topTr");
		}
		
		if ("xy".equals(userType)){
			model.setXydm(userDep);
		}

		xmszService.initList(request);
		request.setAttribute("shztList", commService.getSelectList("shzt"));
		request.setAttribute("xmList", commService.getPjxmList(PjxtszModel.pjxtszModel));
		request.setAttribute("path", "pjpy_pjlc_jgcx.do");
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("searchTj", model.getSearchModel());
		request.setAttribute("lcmcValue", null != model.getLcmcValue() ? Arrays.toString(model.getLcmcValue()) : "[]");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("pjpyQuery");
	}
	
	/**
	 * 删除检测
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DelDetectModel model = new DelDetectModel();
		PjpyJgcxService service = new PjpyJgcxService();

		// 导出形式
		String[] pkValue = request.getParameter("pk").split("!!@@!!");
		model.setPkValue(pkValue);

		// 提示信息
		String message = service.checkDel(model);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
}
