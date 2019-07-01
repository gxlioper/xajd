
package xgxt.pjpy.xbemy;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.action.base.BaseServicesUtil;

public class PjpyXbemyAction extends DispatchAction {
	private String jsName = "zf01"; //����Ա
	/**
	 * ѧԺ�ϱ���ѧ�����˵�Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xysbjxjDefault(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		PjpyXbemyActionForm myForm  = (PjpyXbemyActionForm) form;
		String xydm 		   = "";
		String zydm 		   = "";
		String nj   		   = "";
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
		String userName		   = session.getAttribute("userName").toString();//�û���
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		getTitles(request, userName, userType);//����ҳ��title
		request.setAttribute("userType", session.getAttribute("userType"));
		return mapping.findForward("xysbjxj");
	}
	
	/**
	 * ѧԺ�ϱ���ѧ���ѯ
	 * @return
	 */
	public ActionForward xysbjxjSearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		PjpyXbemyActionForm myForm  = (PjpyXbemyActionForm) form;
		String xydm 		   		= "";
		String zydm 		   		= "";
		String nj   		   		= "";
		HttpSession session    		= request.getSession();
		String bmdm            		= session.getAttribute("userDep").toString();//�û����ڲ���
		String userName             = session.getAttribute("userName").toString();
		String userType        		= session.getAttribute("userType").toString();//�û�����
		PjpyXbemyServices service   = new PjpyXbemyServices();
//		�û���ѧԺ�����Ա��û����˵�λ�ķ���
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		
		PjpyXbemyXysbjxjModel pjpyModel = new PjpyXbemyXysbjxjModel();//���ڴ洢ѧԺ�ϱ���ѧ���model
		BeanUtils.copyProperties(pjpyModel,myForm);//��form�е�pjpyModel�ֶ�ֵ���Ƶ�pjpyModel��
		ArrayList<HashMap<String, String>> topTr = service.getSbSearchTitle(userType, userName);//�����û���������ȡ��ͬ��TIP
		ArrayList<String[]> rs = service.getSbSearchResult(userName, userType, pjpyModel);//�����û���������ȡ��ͬ��SEA
		
		getTitles(request, userName, userType);//����ҳ��title
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		return mapping.findForward("xysbjxj");
	}
	
	/**
	 * ���������������ѧ����
	 * ���Ҫ��ѧԺ��ѧ���������񴦣����񴦲���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xyjxjSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
								throws Exception {
		HttpSession session                         = request.getSession();
		String shRes                                = request.getParameter("param1");//�����˽��
		PjpyXbemyActionForm myForm                  = (PjpyXbemyActionForm) form;
		PjpyXbemyShModel shModel                    = new PjpyXbemyShModel();
		String userName                             = session.getAttribute("userName").toString();
		String userType                             = session.getAttribute("userType").toString();
		PjpyXbemyServices service                   = new PjpyXbemyServices();
		BeanUtils.copyProperties(shModel, myForm);
		service.submitShResult  (shRes, userName, userType, shModel);//�û����
		return mapping.findForward("xysbjxjsh");
	}
	
	/**
	 * ѧԺ��ѧ������ѯ��Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyjxjshjgDefault(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	throws Exception {
		PjpyXbemyActionForm myForm                  = (PjpyXbemyActionForm) form;
		String xydm 		   = "";
		String zydm 		   = "";
		String nj   		   = "";
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		return mapping.findForward("xyjxjjgqry");
	}
	
	/**
	 * ѧԺ��ѧ����˽����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyshjxjjgQry(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	throws Exception {
		PjpyXbemyActionForm myForm  = (PjpyXbemyActionForm) form;
		String shjg 				= request.getParameter("jg")!=null?request.getParameter("jg"):"";
		String xydm 		   		= "";
		String zydm 		   		= "";
		String nj   		   		= "";
		HttpSession session    		= request.getSession();
		String bmdm            		= session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        		= session.getAttribute("userType").toString();//�û�����
		PjpyXbemyServices service   = new PjpyXbemyServices();
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		PjpyXbemyXysbjxjModel pjpyModel = new PjpyXbemyXysbjxjModel();//���ڴ洢ѧԺ�ϱ���ѧ���model
		BeanUtils.copyProperties(pjpyModel,myForm);//��form�е�pjpyModel�ֶ�ֵ���Ƶ�pjpyModel��
		ArrayList<HashMap<String, String>> topTr = service.getJxjShJgSearchTitle();//��ѯ��ͷ
		ArrayList<String[]> rs = service.getJxjShJgSearchResult(pjpyModel, shjg);//��ѯ���
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		return mapping.findForward("xyjxjjgqry");
	}
	
	/**
	 * ѧԺ��ѧ����˽������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dataExport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	throws Exception {
		PjpyXbemyActionForm myForm  = (PjpyXbemyActionForm) form;
		PjpyXbemyServices service   = new PjpyXbemyServices();
		String shjg 				= request.getParameter("jg")!=null?request.getParameter("jg"):"";
		PjpyXbemyXysbjxjModel pjpyModel = new PjpyXbemyXysbjxjModel();//���ڴ洢ѧԺ�ϱ���ѧ���model
		BeanUtils.copyProperties(pjpyModel,myForm);//��form�е�pjpyModel�ֶ�ֵ���Ƶ�pjpyModel��
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		ArrayList<String[]> rs = service.getJxjShJgExp(pjpyModel, shjg);
		exportDataForPjpy(rs, response.getOutputStream());
		return mapping.findForward("exp");
	}
	
	/**
	 * ��request������������Ӧ����Ŀ:xnList,njList,xyList,zyList,bjList
	 * @param request
	 */
	private void appendProperities(HttpServletRequest request,String xydm,String zydm,String nj){
		PjpyXbemyDAO dao = new PjpyXbemyDAO();
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": njLocal;
		
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("jxjList", dao.getJxjList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}
	
	/**
	 * ��request�����ò�ͬ��ҳ��title
	 * @param request
	 * @param userName
	 * @param userType
	 */
	private void getTitles(HttpServletRequest request, String userName, String userType){
		String tips="�������� - ��� - ";
		char js = BaseServicesUtil.checkUserToGroup(userName, "����")? 'a'               //��ɫ�ǽ���
				:(BaseServicesUtil.checkUserToGroup(userName, "����")? 'b'           //��ɫ�ǲ���
						: (BaseServicesUtil.checkUserToGroup(userName, "ѧ����") && !userName.equalsIgnoreCase(jsName)? 'c'  //��ɫ��ѧ����
								: ("admin".equalsIgnoreCase(userType) && BaseServicesUtil.checkUserToGroup(userName, "ѧ����") && userName.equalsIgnoreCase(jsName)? 'd'             //��ɫ�ǹ���Ա
										:'e')));									  //��ɫ��ѧԺ
		switch(js){
		case 'a':{//��ɫ�ǽ���
			tips += "�������";
			break;
		}				      
		case 'b':{//��ɫ�ǲ���
			tips += "�������";
			break;
		}
		case 'c':{//��ɫ��ѧ����
			tips += "ѧ�������";
			break;
		}
		case 'd':{//��ɫ�ǹ���Ա
			tips += "����Ա���";
			break;
		}
		case 'e':{//��ɫ��ѧԺ
			tips += "ѧԺ�ϱ�";
			break;
		}
		}
		request.setAttribute("tips", tips);
	}
	
	public void exportDataForPjpy(ArrayList<String[]> vec, OutputStream os)
	throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		try {
			String ColumnName[] = { "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
					"xscsh", "zzjg" };
			String ColumnNameCN[] = { "ѧ��", "����", "רҵ����", "�༶����", "ѧԺ���", "�������", "�������",
					"ѧ�������" ,"���ս��"};
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
}
