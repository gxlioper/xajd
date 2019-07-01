
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
	private String jsName = "zf01"; //管理员
	/**
	 * 学院上报奖学金和审核的默认页面
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
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		String userName		   = session.getAttribute("userName").toString();//用户名
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		getTitles(request, userName, userType);//设置页面title
		request.setAttribute("userType", session.getAttribute("userType"));
		return mapping.findForward("xysbjxj");
	}
	
	/**
	 * 学院上报奖学金查询
	 * @return
	 */
	public ActionForward xysbjxjSearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		PjpyXbemyActionForm myForm  = (PjpyXbemyActionForm) form;
		String xydm 		   		= "";
		String zydm 		   		= "";
		String nj   		   		= "";
		HttpSession session    		= request.getSession();
		String bmdm            		= session.getAttribute("userDep").toString();//用户所在部门
		String userName             = session.getAttribute("userName").toString();
		String userType        		= session.getAttribute("userType").toString();//用户类型
		PjpyXbemyServices service   = new PjpyXbemyServices();
//		用户是学院或管理员，没有审核单位的分配
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		
		PjpyXbemyXysbjxjModel pjpyModel = new PjpyXbemyXysbjxjModel();//用于存储学院上报奖学金的model
		BeanUtils.copyProperties(pjpyModel,myForm);//把form中的pjpyModel字段值复制到pjpyModel中
		ArrayList<HashMap<String, String>> topTr = service.getSbSearchTitle(userType, userName);//增加用户类型来获取不同的TIP
		ArrayList<String[]> rs = service.getSbSearchResult(userName, userType, pjpyModel);//增加用户类型来获取不同的SEA
		
		getTitles(request, userName, userType);//设置页面title
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		return mapping.findForward("xysbjxj");
	}
	
	/**
	 * 各级，各部门审核学生获奖
	 * 审核要分学院，学生处，教务处，财务处部门
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xyjxjSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
								throws Exception {
		HttpSession session                         = request.getSession();
		String shRes                                = request.getParameter("param1");//获得审核结果
		PjpyXbemyActionForm myForm                  = (PjpyXbemyActionForm) form;
		PjpyXbemyShModel shModel                    = new PjpyXbemyShModel();
		String userName                             = session.getAttribute("userName").toString();
		String userType                             = session.getAttribute("userType").toString();
		PjpyXbemyServices service                   = new PjpyXbemyServices();
		BeanUtils.copyProperties(shModel, myForm);
		service.submitShResult  (shRes, userName, userType, shModel);//用户审核
		return mapping.findForward("xysbjxjsh");
	}
	
	/**
	 * 学院奖学金结果查询的默认页面
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
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		return mapping.findForward("xyjxjjgqry");
	}
	
	/**
	 * 学院奖学金审核结果查询
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
		String bmdm            		= session.getAttribute("userDep").toString();//用户所在部门
		String userType        		= session.getAttribute("userType").toString();//用户类型
		PjpyXbemyServices service   = new PjpyXbemyServices();
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		PjpyXbemyXysbjxjModel pjpyModel = new PjpyXbemyXysbjxjModel();//用于存储学院上报奖学金的model
		BeanUtils.copyProperties(pjpyModel,myForm);//把form中的pjpyModel字段值复制到pjpyModel中
		ArrayList<HashMap<String, String>> topTr = service.getJxjShJgSearchTitle();//查询表头
		ArrayList<String[]> rs = service.getJxjShJgSearchResult(pjpyModel, shjg);//查询结果
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		return mapping.findForward("xyjxjjgqry");
	}
	
	/**
	 * 学院奖学金审核结果导出
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
		PjpyXbemyXysbjxjModel pjpyModel = new PjpyXbemyXysbjxjModel();//用于存储学院上报奖学金的model
		BeanUtils.copyProperties(pjpyModel,myForm);//把form中的pjpyModel字段值复制到pjpyModel中
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		ArrayList<String[]> rs = service.getJxjShJgExp(pjpyModel, shjg);
		exportDataForPjpy(rs, response.getOutputStream());
		return mapping.findForward("exp");
	}
	
	/**
	 * 在request对象中增加相应的项目:xnList,njList,xyList,zyList,bjList
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
	 * 在request中设置不同的页面title
	 * @param request
	 * @param userName
	 * @param userType
	 */
	private void getTitles(HttpServletRequest request, String userName, String userType){
		String tips="评奖评优 - 审核 - ";
		char js = BaseServicesUtil.checkUserToGroup(userName, "教务处")? 'a'               //角色是教务处
				:(BaseServicesUtil.checkUserToGroup(userName, "财务处")? 'b'           //角色是财务处
						: (BaseServicesUtil.checkUserToGroup(userName, "学生处") && !userName.equalsIgnoreCase(jsName)? 'c'  //角色是学生处
								: ("admin".equalsIgnoreCase(userType) && BaseServicesUtil.checkUserToGroup(userName, "学生处") && userName.equalsIgnoreCase(jsName)? 'd'             //角色是管理员
										:'e')));									  //角色是学院
		switch(js){
		case 'a':{//角色是教务处
			tips += "教务处审核";
			break;
		}				      
		case 'b':{//角色是财务处
			tips += "财务处审核";
			break;
		}
		case 'c':{//角色是学生处
			tips += "学生处审核";
			break;
		}
		case 'd':{//角色是管理员
			tips += "管理员审核";
			break;
		}
		case 'e':{//角色是学院
			tips += "学院上报";
			break;
		}
		}
		request.setAttribute("tips", tips);
	}
	
	public void exportDataForPjpy(ArrayList<String[]> vec, OutputStream os)
	throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
			String ColumnName[] = { "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
					"xscsh", "zzjg" };
			String ColumnNameCN[] = { "学号", "姓名", "专业名称", "班级名称", "学院审核", "教务处审核", "财务处审核",
					"学生处审核" ,"最终结果"};
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
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
}
