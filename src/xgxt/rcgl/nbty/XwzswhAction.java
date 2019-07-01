package xgxt.rcgl.nbty;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;
import xgxt.action.Base;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 *	处理日常事务维护Action
 *	author:sjf
 *	date:2010-06-30
 */
public class XwzswhAction extends BasicAction{
	/**
	 * 负责校外住宿记录的查找，删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xwzswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XwzswhForm xwzswhForm = (XwzswhForm)form;
		String go = request.getParameter("go");
		String type = request.getParameter("type") == null ? "" : request.getParameter("type").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			xwzswhForm.setQueryequals_xydm(userDep);
		}
		
		if("stu".equalsIgnoreCase(userType)){
			String xh = session.getAttribute("userName").toString();
			xwzswhForm.setQuerylike_xh(xh);
			request.setAttribute("xh", xh);
		}
		
		if("del".equalsIgnoreCase(type)){
			deleteOperation(request, "xwzsb");
			go = "go";
		}
		
		//根据页面中的条件获取数据
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = {"pkValue","xh","xm","nj","xymc","zymc","bjmc"
					,"sxyy","sxyydh","xzzxxdz"};
			selectPageDataByPagination(request, xwzswhForm, "xwzsb", "view_xwzswh", outputColumn);
		}
		
		
		appendProperties(request);
		
		if("del".equalsIgnoreCase(type)){
			request.setAttribute("writeAble", "yes");
		}
		
		request.setAttribute("tableName", "view_xwzswh");
		request.setAttribute("realTable", "xwzsb");
		request.setAttribute("userType", userType);
		request.setAttribute("userDep", userDep);
		return mapping.findForward("xwzswh");
	}

	/**
	 * 根据pkValue来修改校外住宿信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xwzsModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String type = request.getParameter("type");
		String pkValue = request.getParameter("pkValue");
		String readOnly = "false";
		
		if("save".equalsIgnoreCase(type)){
			this.updateOperation(request, "xwzsb");
		}
		
		if(StringUtils.isNotNull(type)){
			this.selectPageDataByOne(request, "xwzsb", "view_xwzswh", pkValue);
		}
		
		if("query".equalsIgnoreCase(request.getParameter("act"))){	
			readOnly = "true";
		}
		
		appendProperties(request);//加载下拉列表数据
		
		request.setAttribute("type", type);
		request.setAttribute("readOnly", readOnly);
		return mapping.findForward("xwzsModi");
	}

	/**
	 * 增加校外住宿信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xwzsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService xsxxService = new XsxxglService();
		String xh = request.getParameter("xh");
		String type = request.getParameter("type");
		
		
		if("add".equalsIgnoreCase(type)){
			this.insertOperation(request, "xwzsb");
		}
		
		HashMap<String, String> stu =  xsxxService.selectStuinfo(xh);
		stu.put("save_xh", stu.get("xh"));
		if(!"add".equalsIgnoreCase(type)){
			stu.put("save_nd", Base.currNd);
		}
		request.setAttribute("rs",stu);
		appendProperties(request);
		return mapping.findForward("xwzsAdd");
	}
	
	/**
	 * 在request里面添加页面中的对象
	 * @param request
	 */
	public void appendProperties(HttpServletRequest request){
		request.setAttribute("path", "rcsw_xwzswh.do?method=xwzswh");
		String[] writeAbleAndTilte = FormModleCommon.getWriteAbleAndTitle(request);     // 获得可读性和标题的数组
		String userType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", writeAbleAndTilte[0]);
		request.setAttribute("title", writeAbleAndTilte[1]);	
		request.setAttribute("tableName", "view_xsxxb");
		request.setAttribute("realTable", "xszsb");
		request.setAttribute("userType", userType);				//用户类型
		FormModleCommon.setNdXnXqList(request); 				//年度学年年级
		FormModleCommon.setNjXyZyBjList(request);				//年级学院专业
		
	}
	
	/**
	 * 根据：宁波天一职业技术学院实习生校外住宿情况登记表.xls报表导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward xwzsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//需要导出的字段
		String[] output = {"xm","nj","zymc","bjmc","sjhm","jtdh"
				,"sxyy","sxyydh","xzzxxdz","zzsqlxdh","bz"};
		expPageData(request, response, "xwzsb", "view_xwzswh", output);
		return mapping.findForward("");
	}
	
}
