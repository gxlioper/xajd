package xgxt.rcsw.nthy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
/**
 * 学费欠费管理
 */
public class XfqfglAction extends BasicAction {
	
	/**
	 * 学费欠费查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfqfcx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();//判断是否是辅导员
		
		XfqfglActionForm myForm = (XfqfglActionForm) form;
		XfqfglService service = new XfqfglService();
		
		String tableName = "xg_rcsw_nthy_xsqfxxb";
		String viewName = "xg_view_rcsw_nthy_xsqfxxb";
		
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] {"pkValue","xh", "xn","xm","nj","xymc", "zymc","bjmc", "jfzt"};
		
		/*权限控制*/
		if(StringUtils.isEqual(isFdy, "true")){
			request.setAttribute("annexTerm", " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		} else if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"' ");
		} else if("stu".equals(userType)){
			myForm.setQuerylike_xh(userName);
			request.setAttribute("annexTerm", " and xh= '"+userName+"' ");
		}
		
		service.setList(request, "sfqf");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "rcsw_nthy_xfqfcx.do");
		FormModleCommon.commonRequestSet(request);
		
		//查询
		this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		
		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, new String[] {"xh", "xn","sfqf","jlsj","xm", "xb","xydm", "xymc",
					"zydm","zymc","bjdm","bjmc","nj","jfzt"});
			return mapping.findForward("");
		}
		return mapping.findForward("xfqfcx");
	}
	
	/**
	 * 学费欠费查询交费信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 */
	public ActionForward xfqfcxDetial(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws NullStringException{
		
		XfqfglService service = new XfqfglService();
		XfqfglActionForm myForm = (XfqfglActionForm)form;
		String tableName = "xg_rcsw_nthy_xsqfxxb";
		String viewName = "xg_view_rcsw_nthy_xsqfxxb";
		
		this.selectPageDataByOne(request, tableName, viewName, myForm.getPk());
		service.setList(request, "sfqf");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		return mapping.findForward("xfqfcxDetail");
	}
	
}

