package xgxt.studentInfo.ynys;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Fdypd;

public class XsxxYnysAction extends DispatchAction{
	/***
	 * ����ҳ�����
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, XsxxYnysForm model) throws Exception {
		XsxxYnysService service = new XsxxYnysService();
		String xy = "";
		String zy = "";
		String njLocal = model.getNj();
		xy = xy==null ? "": (model.getXydm()==null ? "" : model.getXydm()); 
		zy = zy==null ? "": (model.getZydm()==null ? "" : model.getZydm()); 
		njLocal = model.getNj()==null ? "": model.getNj();
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		
		request.setAttribute("mzList", service.getMzList());
		request.setAttribute("zzmmList", service.getZzmmList());
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
//		request.setAttribute("nfbyList", service.getChkList(2));
//		request.setAttribute("sfzcList", service.getChkList(2));
		request.setAttribute("userType", userType);//�û�����
		//�����������ᰴʡ�����������б�ѡ��		
		request.setAttribute("ssList", service.getSsList());
		request.setAttribute("shiList", service.getShiList(model.getJgshen()).get("shiList"));
		request.setAttribute("xianList",  service.getShiList(model.getJgshen()).get("xianList"));
		
	}
	
	/**
	 * ѧ����Ϣ��ѯ
	 * @throws Exception 
	 * */
	public ActionForward xsxxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxYnysForm model = (XsxxYnysForm)form;
		HttpSession session = request.getSession();
		XsxxYnysService service = new XsxxYnysService();
		XsxxglService xsxxglService = new XsxxglService();
		String userType = session.getAttribute("userType").toString();
		String userSpeType = service.getSpeType(userType);
		String sfyby = request.getParameter("sfyby");
		String title = "ѧ����Ϣ - ������Ϣ - ��Ϣ��ѯ ";
		String writeAble = "yes";
		String tableName = "view_xsxxb";
		String realTable = "xsxxb";
		boolean isFdy = session.getAttribute("fdyQx").toString().equalsIgnoreCase("true") ? true : false;
		String userName = session.getAttribute("userName").toString();
		
		//ѧ������
		if (userType.equalsIgnoreCase("student")) {			
			String xh = session.getAttribute("userName").toString();
			return new ActionForward("/stu_info_details.do?xh=" + xh, false);
		}
		//ѧԺ�û�
		if("xy".equalsIgnoreCase(userSpeType)){
			request.setAttribute("isXy", "true");
			model.setXydm(session.getAttribute("userDep").toString());
		}
		
		model.setTableName(tableName);
		model.setFdy(isFdy);
		model.setUserName(userName);
		model.setJg(request.getParameter("jgz"));//���� ʡ+��+��
		if ((request.getParameter("go") != null) && request.getParameter("go").equalsIgnoreCase("go")) {
			Vector<Object> rs = new Vector<Object>();
			rs.addAll(service.selectXsxx(model));
			
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("topTr", service.getXsxxSearchTopTr());
		}
		
		
		//����Ƿ�����޸�ѧ����Ϣ
		if(service.checkXsxxModify()){
			request.setAttribute("userOper", "yes");
		}
		
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("sfyby", sfyby);		
		request.setAttribute("title", title);
		appendProperties(request, model);
		if(isFdy){//����Ա��¼	
			request.setAttribute("bjList", Fdypd.getFdybjList(session.getAttribute("userName").toString()));
		}
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userSpeType", userSpeType);
		request.setAttribute("xjztList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_XJZTLIST));
		return mapping.findForward("success");
	}
}
