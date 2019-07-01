/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-7-13 上午11:02:20</p>
 */
package xgxt.sztz.nbzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.sztz.dao.SztzDao;
import xgxt.utils.RowidToPk;


public class SztzNbzyAction extends DispatchAction {
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// TODO 自动生成方法存根
		try {
			HttpSession session = request.getSession();
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "系统出现错误，请重新登录！" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
		return super.execute(mapping,form,request, response);
	}
	/**拓展信息维护查询
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	public ActionForward tzInfoManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		SztzNbzyForm sztzForm = (SztzNbzyForm)form;
		SztzNbzyService service = new SztzNbzyService();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String xn = sztzForm.getXn();
		String xq = sztzForm.getXq();		
		sztzForm.setXh(DealString.toGBK(sztzForm.getXh()));
		sztzForm.setXm(DealString.toGBK(sztzForm.getXm()));
		TzInfoQuerryModel model = new TzInfoQuerryModel();
		if(xn==null||xq==null){
			sztzForm.setXn(Base.currXn);
			sztzForm.setXq(Base.currXq);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, sztzForm);
			ArrayList<HashMap<String, String>> topTr = service.serv_getTzInfoTit();
			ArrayList<String[]> rs    = service.serv_tzInfoQuerry(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		SztzDao.getXyZyBjxx(request);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("writeAble", (Base.chkUPower(userName,"tzInfoManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("tzInfoManage");
	}
	/**拓展信息添加*/
	public ActionForward tzInfoAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SztzNbzyForm sztzForm = (SztzNbzyForm)form;
		SztzNbzyService service = new SztzNbzyService();
		TzInfoAddModel model = new TzInfoAddModel();
		String doType    = request.getParameter("doType");		
		String xh = request.getParameter("xh");
		String xn = sztzForm.getXn();
		String xq = sztzForm.getXq();
		if(Base.isNull(xn)||Base.isNull(xq)){
			sztzForm.setXn(Base.currXn);
			sztzForm.setXq(Base.currXq);
		}
		boolean done = false;
		sztzForm.setXh(DealString.toGBK(sztzForm.getXh()));
		sztzForm.setXmmc(DealString.toGBK(sztzForm.getXmmc()));
		sztzForm.setCj(DealString.toGBK(sztzForm.getCj()));
		sztzForm.setBz(DealString.toGBK(sztzForm.getBz()));
		sztzForm.setSfjlxm(DealString.toGBK(sztzForm.getSfjlxm()));		
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, sztzForm);
			done = service.serv_tzInfoAddSave(model);
			request.setAttribute("done",done);
		}
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("kmdmList", SztzDao.getKmList());
		request.setAttribute("rs",service.serv_getStuInfo(xh));		
		return mapping.findForward("tzInfoAdd");
	}
	/**拓展信息修改*/
	public ActionForward tzInfoModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{		
		SztzNbzyService service = new SztzNbzyService();
		SztzNbzyForm sztzForm = (SztzNbzyForm)form;
		String pkValue =  RowidToPk.rowidToPK(request.getParameter("pkValue"));
		String doType  = request.getParameter("doType");
		String act     = DealString.toGBK(request.getParameter("act"));
		TzInfoAddModel model = new TzInfoAddModel();
		boolean done = false;
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, sztzForm);
			done = service.serv_tzInfoModiSave(model, pkValue);
			request.setAttribute("done",done);
		}
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("act",act);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("kmdmList", SztzDao.getKmList());
		request.setAttribute("rs",service.serv_getTzxxInfo(pkValue));	
		return mapping.findForward("tzInfoModi");
	}
	/**拓展信息删除
	 * @throws SQLException */
	public ActionForward tzInfoDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{
		SztzNbzyService service = new SztzNbzyService();
		String delPk = request.getParameter("delPk");
		service.serv_tzInfoDel(delPk);
		return new ActionForward("/nbzy_sztz.do?method=tzInfoManage&go=go", false);
	}
	/**拓展信息打印查询*/
	public ActionForward tzPrintManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		SztzNbzyForm sztzForm = (SztzNbzyForm)form;
		SztzNbzyService service = new SztzNbzyService();	
		String xn = sztzForm.getXn();
		String xq = sztzForm.getXq();		
		sztzForm.setXh(DealString.toGBK(sztzForm.getXh()));
		sztzForm.setXm(DealString.toGBK(sztzForm.getXm()));
		TzInfoQuerryModel model = new TzInfoQuerryModel();
		if(Base.isNull(xn)||Base.isNull(xq)){
			sztzForm.setXn(Base.currXn);
			sztzForm.setXq(Base.currXq);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, sztzForm);
			ArrayList<HashMap<String, String>> topTr = service.serv_getTzPrintTit();
			ArrayList<String[]> rs    = service.serv_tzPrintQuerry(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		SztzDao.getXyZyBjxx(request);
		return mapping.findForward("tzPrintManage");
	}
	/**拓展信息打印
	 * @throws Exception */
	public ActionForward tzInfoPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String rq = request.getParameter("rq");
		SztzNbzyService service = new SztzNbzyService();
		String kmdm[]  = service.serv_getKmdm();
		Vector<Object> rsTzXm = new Vector<Object>();
		String fzrq = "";
		if(kmdm==null){
			request.setAttribute("errMsg", "先在路径 \"系统维护 - 代码维护 - 素质拓展\"下，维护科目设立！");
			return new ActionForward("/errMsg.do", false);			
		}
		if(rq.length()==8){
			fzrq = rq.substring(0,4)+"年"+rq.substring(4, 6)+"月"+rq.substring(6,rq.length())+"日";
		}
		rsTzXm = service.serv_getTzInfoListPrint(xh);
		request.setAttribute("rsTzXm",rsTzXm);
		request.setAttribute("xxmc",Base.xxmc);
		request.setAttribute("rsJlxm",service.serv_tzJlxm(xh));
		request.setAttribute("rsStu",service.serv_getStuInfo(xh));
		request.setAttribute("fzrq",fzrq);
		return mapping.findForward("tzInfoPrint");
	}
}
