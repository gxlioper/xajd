/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-5-11 下午04:06:07</p>
 */
package xgxt.sztz.bjlhdx;


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
import xgxt.base.DealString;
import xgxt.sztz.dao.SztzDao;
import xgxt.utils.RowidToPk;

public class SztzBjlhdxAction extends DispatchAction {
	public ActionForward  creditPointInit(ActionMapping mapping ,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SztzBjlhdxActionForm myForm = (SztzBjlhdxActionForm)form;
		String nj = myForm.getNj();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
	    String doType = request.getParameter("doType");
	    QuerryModel  model = new QuerryModel();
	    SztzBjlhdxService service = new SztzBjlhdxService();
	    BeanUtils.copyProperties(model, myForm); 
	    if("save".equalsIgnoreCase(doType)){	    	
	    	boolean done=false;	    	
	    	done = service.serv_csfInit(model);
	    	request.setAttribute("done", done);	    	
	    }
	    if(nj!=null&&xydm!=null){//点击链接访问此功能时，不做查询
	    ArrayList<String[]> rs    = service.serv_csfInitQuerr(model);
    	request.setAttribute("rs",rs);
	    }
		SztzDao myDao = new SztzDao();		
		myDao.getListxx(request, xydm, zydm, nj);
		return mapping.findForward("creditPInit");
	}
	public ActionForward creditPointStat(ActionMapping mapping ,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SztzBjlhdxActionForm myForm = (SztzBjlhdxActionForm)form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();

		String nj = myForm.getNj();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String xm = DealString.toGBK(myForm.getXm());
		myForm.setXm(xm);
		String xh = DealString.toGBK(myForm.getXh());
		myForm.setXh(xh);
		SztzDao myDao = new SztzDao();
		myDao.getListxx(request, xydm, zydm, nj);
		SztzBjlhdxService service = new SztzBjlhdxService();
		QuerryModel  model = new QuerryModel();
		
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, myForm); 
			ArrayList<HashMap<String, String>> topTr = service.getcsfStatTitle();
			ArrayList<String[]> rs    = service.serv_csfStatResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}
		request.setAttribute("writeAble", (Base.chkUPower(userName,"creditPointStat.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("creditPStat");
	}
	public ActionForward kfPlDelete(ActionMapping mapping ,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	  	String pkValue = RowidToPk.rowidToPK(request.getParameter("delPk"));    	
	  	SztzBjlhdxService service = new SztzBjlhdxService();
    	service.serv_kxfDel( pkValue);   	    	    	
    	return new ActionForward("/bjlhdx_sztz.do?method=creditPointStat&go=go",false);
	}
	public ActionForward kfModi(ActionMapping mapping ,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue")); 
		String doType  = request.getParameter("doType");
		HashMap<String,String> map = new HashMap<String,String>();
		SztzBjlhdxService service = new SztzBjlhdxService();
		String kfs = request.getParameter("kfs");
		if("save".equalsIgnoreCase(doType)){
			boolean done =false;
			done = service.serv_kfModiSave(kfs,pkValue);
			request.setAttribute("done",done);
		}
		map = service.srev_kxfXx(pkValue);
		request.setAttribute("csf",service.serv_getXyCsf(pkValue));		
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		return mapping.findForward("creditPModi");
	}
}
