
package xgxt.rcgl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.rcgl.rcgl_OperationAction;

public class rcgl_action extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		rcgl_form rcglform = (rcgl_form)form;
		HttpSession session = request.getSession();
		String writeAble = "";
		String userName = "";
		String userType = "";
		
		try {			
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				rcglform.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}						
			if (session == null) {
				request.setAttribute("errMsg", "��������");
				return mapping.findForward("false");
			} 
			userName = session.getAttribute("userName").toString();
			userType = session.getAttribute("userOnLine").toString();
	        String act = request.getParameter("act");
			boolean isStu = userType.equalsIgnoreCase("student");
			int p = 0;
			ActionForward myActFwd = null;
			String mypam = mapping.getParameter();
			if(mypam.equalsIgnoreCase("bbsh_Search")){
				if(act.equalsIgnoreCase("xsz")){
					p = Base.chkUPower(userName, "bbsh_Search.do?act=xsz", isStu);
					//ѧ��֤������˲�ѯ
				}else if(act.equalsIgnoreCase("hck")){
					p = Base.chkUPower(userName, "bbsh_Search.do?act=hck", isStu);
					//���Żݿ�������˲�ѯ
				}else if(act.equalsIgnoreCase("ykt")){
					p = Base.chkUPower(userName, "bbsh_Search.do?act=ykt", isStu);
					//У԰һ��ͨ������˲�ѯ
				}else if(act.equalsIgnoreCase("xh")){
					p = Base.chkUPower(userName, "bbsh_Search.do?act=xh", isStu);
					////ѧУУ�ղ�����˲�ѯ
				}else if(act.equalsIgnoreCase("xh")){
					p = Base.chkUPower(userName, "bbsh_Search.do?act=xfhj", isStu);
					////ѧ�ѻ�����˲�ѯ
				}	
				myActFwd = rcgl_OperationAction.rcgl_sdataSearch(mapping,form,request,response);
			}else if(mypam.equalsIgnoreCase("bb_shOne")){//�������
				myActFwd = rcgl_OperationAction.xszCheckOne(mapping, form, request, response);//֤�������
			}else if(mypam.equalsIgnoreCase("gzcjXfcj")){//���ݳǽ�ѧ�Ѵ߽�
				myActFwd = rcgl_OperationAction.xfcjSave(mapping, form, request, response);
			}else if(mypam.equalsIgnoreCase("viewXfcj")){//���ݳǽ�ѧ�Ѵ߽�show page
				myActFwd = rcgl_OperationAction.selectInfo(mapping, form, request, response);
			}else if(mypam.equalsIgnoreCase("gzcjXfhj")){
				myActFwd = rcgl_OperationAction.xfhjSave(mapping, form, request, response);
			}else if(mypam.equalsIgnoreCase("viewXfhj")){
				myActFwd = rcgl_OperationAction.selectXfhjInfo(mapping, form, request, response);
			}
			
            writeAble = (p==1)? "yes" : "no" ;
            request.setAttribute("writeAble",writeAble);
			return myActFwd;
		} catch (Exception e){
			e.printStackTrace();
			rcglform.setErrMsg("���������жϣ������µ�½��");
	    	return new ActionForward("/login.jsp", false);
		}
	}
}
