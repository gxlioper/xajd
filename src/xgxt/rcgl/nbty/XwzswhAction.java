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
 *	�����ճ�����ά��Action
 *	author:sjf
 *	date:2010-06-30
 */
public class XwzswhAction extends BasicAction{
	/**
	 * ����У��ס�޼�¼�Ĳ��ң�ɾ�� 
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
		
		//����ҳ���е�������ȡ����
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
	 * ����pkValue���޸�У��ס����Ϣ
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
		
		appendProperties(request);//���������б�����
		
		request.setAttribute("type", type);
		request.setAttribute("readOnly", readOnly);
		return mapping.findForward("xwzsModi");
	}

	/**
	 * ����У��ס����Ϣ
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
	 * ��request�������ҳ���еĶ���
	 * @param request
	 */
	public void appendProperties(HttpServletRequest request){
		request.setAttribute("path", "rcsw_xwzswh.do?method=xwzswh");
		String[] writeAbleAndTilte = FormModleCommon.getWriteAbleAndTitle(request);     // ��ÿɶ��Ժͱ��������
		String userType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", writeAbleAndTilte[0]);
		request.setAttribute("title", writeAbleAndTilte[1]);	
		request.setAttribute("tableName", "view_xsxxb");
		request.setAttribute("realTable", "xszsb");
		request.setAttribute("userType", userType);				//�û�����
		FormModleCommon.setNdXnXqList(request); 				//���ѧ���꼶
		FormModleCommon.setNjXyZyBjList(request);				//�꼶ѧԺרҵ
		
	}
	
	/**
	 * ���ݣ�������һְҵ����ѧԺʵϰ��У��ס������ǼǱ�.xls������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward xwzsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//��Ҫ�������ֶ�
		String[] output = {"xm","nj","zymc","bjmc","sjhm","jtdh"
				,"sxyy","sxyydh","xzzxxdz","zzsqlxdh","bz"};
		expPageData(request, response, "xwzsb", "view_xwzswh", output);
		return mapping.findForward("");
	}
	
}
