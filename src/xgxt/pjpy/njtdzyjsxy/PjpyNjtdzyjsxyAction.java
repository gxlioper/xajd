
package xgxt.pjpy.njtdzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;

/**
 * Title:�Ͼ�����ְҵ����ѧԺ��������ҳ����ת������
 * Copyright:Copright(c)2008
 * Company:�����������ӹ������޹�˾
 * @Author:Lp
 * @version 1.0
 * �������ڣ�2008-05-26
 */
public class PjpyNjtdzyjsxyAction extends DispatchAction {
	String writeAble = "";
	String userType = "";
	boolean isStu = false;
	String sUName = "";
	String userDep = "";
	String tableName = "";
	String realTable = "";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������
		HttpSession session = request.getSession();
		/** ���߼�� */
		int i = Base.chkTimeOut(session);
		if (i <= 2) {
			request.setAttribute("errMsg", "��½��ʱ�������µ�½��");
			return new ActionForward("/login.jsp", false);
		}
		userType = session.getAttribute("userType").toString();   //�õ��û�����
		userDep = session.getAttribute("userDep").toString();//�û����ڲ���
		isStu = (userType.equalsIgnoreCase("stu"));				 //�ж��Ƿ���ѧ���û���¼
		sUName = session.getAttribute("userName").toString();	//�õ���¼�û���
		request.setAttribute("userType",userType);
		return super.execute(mapping,form,request,response);
	}
	/**
	 * �ۺ����ʳɼ�����Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ActionForward zhszcjDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyNjtdzyjsxyActionForm myForm = (PjpyNjtdzyjsxyActionForm)form;
		PjpyNjtdzyjsxyZhszcjModel zhszcjModel = new PjpyNjtdzyjsxyZhszcjModel();
		String xydm = "";
		String zydm = "";
		String nj = "";
		tableName = "view_njtdzhszcj";
		realTable = "njtd_zhszcjb";
		//Ժϵ�û��������Ʋ�����Ժϵ��ѧ���������
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		BeanUtils.copyProperties(zhszcjModel,myForm);//��form�е�pjpyModel�ֶ�ֵ���Ƶ�zhszcjModel��
		appendProperities(request,xydm,zydm,nj);//��ȡ��������б�
		
		/**�ж��û���дȨ*/
		writeAble = (Base.chkUPower(sUName, "zhszcjImportDefaul.do", isStu) == 1) ? "yes" : "no";
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
		return mapping.findForward("zhszcj_Dr_Default");
	}
	/**
	 * �ۺ����ʳɼ������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcjSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyNjtdzyjsxyActionForm myForm = (PjpyNjtdzyjsxyActionForm)form;
		PjpyNjtdzyjsxyZhszcjModel zhszcjModel = new PjpyNjtdzyjsxyZhszcjModel();
		PjpyNjtdzyjsxyServices service = new PjpyNjtdzyjsxyServices();
		String xydm = "";
		String zydm = "";
		String nj = "";
//		Ժϵ�û��������Ʋ�����Ժϵ��ѧ���������
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
		}
		BeanUtils.copyProperties(zhszcjModel,myForm);//��form�е�pjpyModel�ֶ�ֵ���Ƶ�zhszcjModel��
		ArrayList<HashMap<String, String>> topTr = service.getzhszcjSearchTitle();//�����ر�ͷ��Ϣ
		ArrayList<String[]> rs = service.getzhszcjResult(zhszcjModel);//�����ѯ
		request.setAttribute("topTr",topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		appendProperities(request,xydm,zydm,nj);//��ȡ��������б�
		request.setAttribute("writeAble",writeAble);//��дȨ
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
		return mapping.findForward("zhszcj_Dr_Default");
	}
	/**
	 * ��ø�����ѧ��ѧ���ۺ����ʳɼ���ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcjDetails(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		PjpyNjtdzyjsxyServices service = new PjpyNjtdzyjsxyServices();
		ArrayList<HashMap<String, String>> cjList = service.getZhszcjJsJg(xh, xn, xq);//��ø�����ѧ��ѧ���ۺ����ʳɼ���ϸ��Ϣ
		request.setAttribute("rs",cjList);
		return mapping.findForward("zhszcjDetails");
	}
	/**
	 * ��request������������Ӧ����Ŀ:xnList,njList,xyList,zyList,bjList
	 * @param request
	 */
	private void appendProperities(HttpServletRequest request,String xydm,String zydm,String nj){		
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": njLocal;
		
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
				
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}
}
