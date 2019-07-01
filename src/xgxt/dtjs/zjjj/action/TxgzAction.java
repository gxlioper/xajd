 package xgxt.dtjs.zjjj.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xgxt.dtjs.utils.GetWriteAbleAndTitle;
import xgxt.dtjs.zjjj.form.TxgzForm;
import xgxt.dtjs.zjjj.model.TxgzModel;
import xgxt.dtjs.zjjj.server.TxgzService;
import xgxt.pjpy.xbemy.PjpyXbemyDAO;

public class TxgzAction extends DispatchAction{
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理团学-action类</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	
	public ActionForward tzzgb(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	    团组织干部
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_tzzgb";
		String realTable     = "tzzgbb";
		
		ArrayList<String[]> rs = null;
		TxgzForm myForm              =    (TxgzForm)  form;
		TxgzService service          =    new TxgzService();
		TxgzModel myModel            =    new TxgzModel();
		
		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		nj = (nj==null) ? "" : nj;
		
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getTzzgbList(myModel);
		}
		
		List topTr = service.getTzzgbTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("zwList", service.getZwList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("tzzgb");		
	}
	
	public ActionForward tzzgbOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // 入团积极份子单个查看
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			String xh                      =    DealString.toGBK(request.getParameter("xh"));
			String writeAble               =    DealString.toGBK(request.getParameter("writeAble"));
			TxgzService service          =    new TxgzService();
			HashMap<String, String> rs     =    service.getTzzgbOne(pk,xh);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("rs", rs);
			request.setAttribute("zwList", service.getZwList());
			return mapping.findForward("tzzgbOne");	
	}
	
	public ActionForward saveTzzgbOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // 军训新生体检维护单个查看保存
		TxgzForm myForm              =    (TxgzForm)  form;
		TxgzService service          =    new TxgzService();
		TxgzModel myModel            =    new TxgzModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataTzzgb(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("tzzgbOne");	
	}
	
	public ActionForward delTzzgbOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 入团积极分子单个删除
		TxgzService service          =    new TxgzService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteTzzgbOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return tzzgb(mapping, form,request, response);		
	}

	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable, ArrayList<String[]> rs, List topTr) {
		// Request存值的通用方法2 区别是title从数据库里取
		String writeAble  = request.getParameter("writeAble");
		String title      = DealString.toGBK(request.getParameter("title"));
		if(Base.isNull(writeAble)) {
			String [] message = GetWriteAbleAndTitle.getWriteAbleAndTitle(request);
			writeAble = message[0];
			title     = message[1];
		}
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public ActionForward tytysbsh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		TxgzForm myForm  = (TxgzForm) form;
		String xydm 		   		= "";
		String zydm 		   		= "";
		String nj   		   		= "";
		HttpSession session    		= request.getSession();
		String bmdm            		= session.getAttribute("userDep").toString();//用户所在部门
//		String userName             = session.getAttribute("userName").toString();
		String userType        		= session.getAttribute("userType").toString();//用户类型
		TxgzService service   = new TxgzService();
//		用户是学院或管理员，没有审核单位的分配
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		
		TxgzModel myModel = new TxgzModel();
		BeanUtils.copyProperties(myModel,myForm);//把form中的pjpyModel字段值复制到pjpyModel中
		List topTr = service.getSbSearchTitle();//增加用户类型来获取不同的TIP
		ArrayList<String[]> rs = null;
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			if (userType.equalsIgnoreCase("xy")){//学院用户查询结果
				rs = service.getXySbSearchResult(myModel);
			}else{//学生处，财务处，教务处和管理员查询结果
				rs = service.getXxShSearchResult(myModel);
			}
		}
		commonRequestSet(request,"", "", rs,topTr);//设置页面title
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		return mapping.findForward("tytysbsh");
	}

	
	@SuppressWarnings("unused")
	private void setFormForXnNdXqNj(TxgzForm myForm) {
		//    判断学年，年度，学期,年级后，往form里注入的方法
			String xn           = Base.currXn;
			String xq           = Base.currXq;
			String nd           = Base.currNd;
			
			if((DealString.toGBK(myForm.getXn())).equalsIgnoreCase("")){
				myForm.setXn(xn);
			}
			
			if((DealString.toGBK(myForm.getXq())).equalsIgnoreCase("")){
				myForm.setXq(xq);
			}
			
			if((DealString.toGBK(myForm.getNd())).equalsIgnoreCase("")){
				myForm.setNd(nd);
				myForm.setNj(nd);
			}
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
	
	
}
