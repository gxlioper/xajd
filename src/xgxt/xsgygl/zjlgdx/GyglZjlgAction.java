package xgxt.xsgygl.zjlgdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江理工大学公寓管理Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: mht</p>
 * <p>Version: 1.0</p>
 */
public class GyglZjlgAction extends  DispatchAction {
	
	/**A级寝室申报默认页面
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	public ActionForward ajqssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response)
			throws IllegalAccessException, InvocationTargetException {
			gyglDao dao = new gyglDao();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		String doType = request.getParameter("doType");
		String commit = "";

		if ("save".equalsIgnoreCase(doType)) {
			GyglZjlgModel model = new GyglZjlgModel();
			BeanUtils.copyProperties(model, myForm);
			if(service.isAjqs_ser(userName)){
				request.setAttribute("isajqs", "yes");
			}else{
				boolean done = service.saveAjqs_ser(model);
				request.setAttribute("done", done);
			}	
		}

		if ("stu".equals(userType)) {
			map = service.getAjqssqqk_ser(userName);
			if (map == null) {
				request.setAttribute("rzxx", "no");
				request.setAttribute("message", "您还没有入住，不能申请A级寝室!");
				return new ActionForward("/prompt.do",false);
			} else {
				if (map.get("sqsj") == null || !"未审核".equals(map.get("xxsh"))) {
					map.put("xn", Base.currXn);
					map.put("xq", Base.getDqxqmc());
					map.put("scsqsj", map.get("sqsj"));
					map.put("sqsj", map.get("xtsj"));
					map.put("sqly", "");
					commit = "no";
				}		
				request.setAttribute("commit", commit);
			}
		} else {
			request.setAttribute("message", "该页面只允许学生访问!");
			return new ActionForward("/prompt.do",false);
		}
		request.setAttribute("rs", map != null ? map
				: new HashMap<String, String>());
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(map==null?"":map.get("lddm")));
		request.setAttribute("qshList", dao.getQshList());
		return mapping.findForward("ajqssq");
	}
	
	/**A级寝室审核默认页面
	 * @throws IllegalAccessException */
	public ActionForward ajqssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response)
			throws IllegalAccessException, InvocationTargetException {
		gyglDao dao = new gyglDao();
		DAO  dbdao = DAO.getInstance();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgModel model = new GyglZjlgModel();
		GyglZjlgService service = new GyglZjlgService();
		String doType = request.getParameter("doType");
		String pks = DealString.toGBK(request.getParameter("pkVStr"));	
		String lddm = myForm.getLddm();
		String lc = myForm.getLc();
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		BeanUtils.copyProperties(model, myForm);
		if(!Base.isNull(doType) && !"query".equals(doType)){
			request.setAttribute("result", service.operation_ser(model, doType, pks));
		}
		if("query".equals(doType)){
			request.setAttribute("rs",service.queryAjqsSh_ser(myForm, model));
			List topTr = service.getTopName("ajqssh");
			request.setAttribute("topTr", topTr);
		}
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		request.setAttribute("myform", myForm);
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm==null?"":lddm));
		request.setAttribute("qshList", GyglShareDAO.GetLcQshList(lddm, lc));
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("chkList",dbdao.getChkList(3));
		return mapping.findForward("ajqsShManage");
	}
	
	/**查看A级寝室详细信息页面
	 * @throws IllegalAccessException */
	public ActionForward viewSqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response){
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
		if("print".equals(doType)){
			pkValue = myForm.getSsbh()+myForm.getSqsj();
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
		}
		HashMap<String,Object> map = service.viewSqxx_ser(pkValue,myForm.getXn() ,myForm.getXq());
		if("print".equals(doType)){
			map.putAll(CommonQueryDAO.getGySsInfo(myForm.getSsbh()));
			map.put("xymc", service.getQsxymc_ser(myForm.getSsbh()));
			map.put("lxdh", DealString.toGBK(myForm.getLxdh()));
			map.put("sqly", DealString.toGBK(myForm.getSqly()));
			map.put("qsz", service.getQszBySsbh(myForm.getSsbh()));
			if(map.get("sqsj")==null){
				map.put("sqsj", myForm.getSqsj());
			}
			request.setAttribute("rs",map);
			return mapping.findForward("ajqssqb");
		}
		request.setAttribute("rs",map);
		return mapping.findForward("viewAjqsxx");
	}
	
	/**A级寝室审核结果查询默认页面
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalAccessException */
	public ActionForward ajqsshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response) 
			throws IllegalAccessException, InvocationTargetException{
		gyglDao dao = new gyglDao();
		DAO  dbdao = DAO.getInstance();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgModel model = new GyglZjlgModel();
		GyglZjlgService service = new GyglZjlgService();
		HttpSession session = request.getSession();
		List topTr = new ArrayList<HashMap<String,String>>();
		String userType = (String)session.getAttribute("userType");
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			topTr = service.getTopName("ajqssh");
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs",service.xsQueryAjqsShcx_ser(userName));
			return mapping.findForward("xsajqsShcx");
		}
		String lddm = myForm.getLddm();
		String lc = myForm.getLc();
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		myForm.setSfcx(DealString.toGBK(myForm.getSfcx()));
		BeanUtils.copyProperties(model, myForm);
		if("query".equals(doType)){
			request.setAttribute("rs",service.queryAjqsShcx_ser(myForm, model));
			topTr = service.getTopName("ajqssh");
			request.setAttribute("topTr", topTr);
		}
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		if("admin".equals(userType) || "xx".equals(userType)){
			request.setAttribute("writeAble", "yes");
		}
		request.setAttribute("myform", myForm);
		request.setAttribute("tableName", "view_ajqsb");
		request.setAttribute("realTable", "ajqsb");
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm==null?"":lddm));
		request.setAttribute("qshList", GyglShareDAO.GetLcQshList(lddm, lc));
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("chkList",dbdao.getChkList(3));
		return mapping.findForward("ajqsManage");
	}
    
	/**文明寝室申报默认页面
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	public ActionForward wmqssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response)
			throws IllegalAccessException, InvocationTargetException {
		gyglDao dao = new gyglDao();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		String doType = request.getParameter("doType");
		String commit = "";

		if ("save".equalsIgnoreCase(doType)) {
			GyglZjlgModel model = new GyglZjlgModel();
			BeanUtils.copyProperties(model, myForm);
			if(service.isAjqsCondition_ser(userName, "wmqs")){
				boolean done = service.saveWmqs_ser(model);
				request.setAttribute("done", done);
			}else{
				request.setAttribute("isajqs", "no");
			}		
		}

		if ("stu".equals(userType)) {
			map = service.getWmqssqqk_ser(userName);
			if (map == null) {
				request.setAttribute("message", "您还没有入住，不能申请文明寝室!");
				return new ActionForward("/prompt.do",false);
			} else {
				if (map.get("sqsj") == null || !"未审核".equals(map.get("xysh"))) {
					map.put("xn", Base.currXn);
					map.put("xq", Base.getDqxqmc());
					map.put("scsqsj", map.get("sqsj"));
					map.put("sqsj", map.get("xtsj"));
					map.put("sqly", "");
					commit = "no";
				}		
				request.setAttribute("commit", commit);
			}
		} else {
			request.setAttribute("message", "该页面只允许学生访问!");
			return new ActionForward("/prompt.do",false);
		}
		request.setAttribute("rs", map != null ? map
				: new HashMap<String, String>());
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(map==null?"":map.get("lddm")));
		request.setAttribute("qshList", dao.getQshList());
		return mapping.findForward("wmqssq");
	}
	
	/**文明寝室审核默认页面
	 * @throws IllegalAccessException */
	public ActionForward wmqssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response)
			throws IllegalAccessException, InvocationTargetException {
		gyglDao dao = new gyglDao();
		DAO  dbdao = DAO.getInstance();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgModel model = new GyglZjlgModel();
		GyglZjlgService service = new GyglZjlgService();
		String doType = request.getParameter("doType");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String pks = DealString.toGBK(request.getParameter("pkVStr"));	
		String lddm = myForm.getLddm();
		String lc = myForm.getLc();
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		myForm.setXysh(DealString.toGBK(myForm.getXysh()));
		BeanUtils.copyProperties(model, myForm);
		if(!Base.isNull(doType) && !"query".equals(doType)){
			request.setAttribute("result", service.WmAndTsOperation_ser("wmqsb", doType, pks,userType));
		}
		if("query".equals(doType)){
			request.setAttribute("rs",service.queryWmqsSh_ser(myForm, model,userType,userDep));
			List topTr = service.getTopName("wmqssh");
			request.setAttribute("topTr", topTr);
		}
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		request.setAttribute("myform", myForm);
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm==null?"":lddm));
		request.setAttribute("qshList", GyglShareDAO.GetLcQshList(lddm, lc));
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("chkList",dbdao.getChkList(3));
		return mapping.findForward("wmqsShManage");
	}
	
	/**查看文明寝室寝室详细信息页面
	 * @throws Exception 
	 * @throws IllegalAccessException */
	public ActionForward viewWmqsSqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
		if("print".equals(doType)){
			pkValue = myForm.getSsbh()+myForm.getSqsj();
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
		}
		HashMap<String,Object> map = service.viewWmqsSqxx_ser(pkValue,myForm.getXn() ,myForm.getXq());
		if("print".equals(doType)){
			map.putAll(CommonQueryDAO.getGySsInfo(myForm.getSsbh()));
			map.put("xymc", service.getQsxymc_ser(myForm.getSsbh()));
			map.put("lxdh", DealString.toGBK(myForm.getLxdh()));
			map.put("sqly", DealString.toGBK(myForm.getSqly()));
			map.put("qsz", service.getQszBySsbh(myForm.getSsbh()));
			if(map.get("sqsj")==null){
				map.put("sqsj", myForm.getSqsj());
			}	
			request.setAttribute("rs",map);
			return mapping.findForward("wmqssqb");
		}
		request.setAttribute("rs",map);
		return mapping.findForward("viewWmqsxx");
	}
	
	/**文明寝室审核结果查询默认页面
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalAccessException */
	public ActionForward wmqsshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response) 
			throws IllegalAccessException, InvocationTargetException{
		gyglDao dao = new gyglDao();
		DAO  dbdao = DAO.getInstance();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgModel model = new GyglZjlgModel();
		GyglZjlgService service = new GyglZjlgService();
		HttpSession session = request.getSession();
		List topTr = new ArrayList<HashMap<String,String>>();
		String userType = (String)session.getAttribute("userType");
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			topTr = service.getTopName("wmqssh");
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs",service.xsQueryWmqsShcx_ser(userName));
			return mapping.findForward("xswmqsShcx");
		}
		String lddm = myForm.getLddm();
		String lc = myForm.getLc();
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		BeanUtils.copyProperties(model, myForm);
		if("query".equals(doType)){
			request.setAttribute("rs",service.queryWmqsShcx_ser(myForm, model,userType,userDep));
			topTr = service.getTopName("wmqssh");
			request.setAttribute("topTr", topTr);
		}
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		if("admin".equals(userType) || "xx".equals(userType)){
			request.setAttribute("writeAble", "yes");
		}
		request.setAttribute("myform", myForm);
		request.setAttribute("tableName", "view_wmqsb");
		request.setAttribute("realTable", "wmqsb");
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm==null?"":lddm));
		request.setAttribute("qshList", GyglShareDAO.GetLcQshList(lddm, lc));
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("chkList",dbdao.getChkList(3));
		return mapping.findForward("wmqsManage");
	}
	
	/**特色寝室申报默认页面
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	public ActionForward tsqssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response)
			throws IllegalAccessException, InvocationTargetException {
		gyglDao dao = new gyglDao();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		String doType = request.getParameter("doType");
		String commit = "";

		if ("save".equalsIgnoreCase(doType)) {
			GyglZjlgModel model = new GyglZjlgModel();
			BeanUtils.copyProperties(model, myForm);		
			if(service.isAjqsCondition_ser(userName, "tsqs")){
				boolean done = service.saveTsqs_ser(model);
				request.setAttribute("done", done);
			}else{
				request.setAttribute("isajqs", "no");
			}
		}

		if ("stu".equals(userType)) {
			map = service.getTsqssqqk_ser(userName);
			if (map == null) {
				request.setAttribute("rzxx", "no");
				request.setAttribute("errMsg", "您还没有入住，不能申请特色寝室!");
				return new ActionForward("/errMsg.do",false);
			} else {
				if (map.get("sqsj") == null || (!"未审核".equals(map.get("xysh"))
						&&!"未审核".equals(map.get("xxsh")))) {
					map.put("xn", Base.currXn);
					map.put("scsqsj", map.get("sqsj"));
					map.put("sqsj", map.get("xtsj"));
					map.put("jtgznr", "");
					map.put("ykzgzqk", "");
					commit = "no";
				}		
				request.setAttribute("commit", commit);
			}
		} else {
			request.setAttribute("info", "yes");
			request.setAttribute("errMsg", "该页面只允许学生访问!");
			return new ActionForward("/errMsg.do",false);
		}
		request.setAttribute("rs", map != null ? map
				: new HashMap<String, String>());
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(map==null?"":map.get("lddm")));
		request.setAttribute("qshList", dao.getQshList());
		return mapping.findForward("tsqssq");
	}
	
	/**特色寝室审核默认页面
	 * @throws IllegalAccessException */
	public ActionForward tsqssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response)
			throws IllegalAccessException, InvocationTargetException {
		gyglDao dao = new gyglDao();
		DAO  dbdao = DAO.getInstance();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgModel model = new GyglZjlgModel();
		GyglZjlgService service = new GyglZjlgService();
		String doType = request.getParameter("doType");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String pks = DealString.toGBK(request.getParameter("pkVStr"));	
		String lddm = myForm.getLddm();
		String lc = myForm.getLc();
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		myForm.setXysh(DealString.toGBK(myForm.getXysh()));
		BeanUtils.copyProperties(model, myForm);
		if(!Base.isNull(doType) && !"query".equals(doType)){
			request.setAttribute("result", service.WmAndTsOperation_ser("tsqsb", doType, pks,userType));
		}
		if("query".equals(doType)){
			request.setAttribute("rs",service.queryTsqsSh_ser(myForm, model,userType,userDep));
			List topTr = service.getTopName("tsqssh");
			request.setAttribute("topTr", topTr);
		}
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		request.setAttribute("myform", myForm);
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm==null?"":lddm));
		request.setAttribute("qshList", GyglShareDAO.GetLcQshList(lddm, lc));
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("chkList",dbdao.getChkList(3));
		return mapping.findForward("tsqsShManage");
	}
	
	/**查看特色寝室寝室详细信息页面
	 * @throws Exception 
	 * @throws IllegalAccessException */
	public ActionForward viewTsqsSqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
		if("print".equals(doType)){
			pkValue = myForm.getSsbh()+myForm.getSqsj();
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
		}
		HashMap<String,Object> map = service.viewTsqsSqxx_ser(pkValue,myForm.getXn());
		if("print".equals(doType)){
			map.putAll(CommonQueryDAO.getGySsInfo(myForm.getSsbh()));
			map.put("xymc", service.getQsxymc_ser(myForm.getSsbh()));
			map.put("lxdh", DealString.toGBK(myForm.getLxdh()));
			map.put("jtgznr", DealString.toGBK(myForm.getJtgznr()));
			map.put("ykzgzqk", DealString.toGBK(myForm.getYkzgzqk()));
			map.put("qsz", service.getQszBySsbh(myForm.getSsbh()));
			if(map.get("sqsj")==null){
				map.put("sqsj", myForm.getSqsj());
			}
			request.setAttribute("rs",map);
			return mapping.findForward("tsqssqb");
		}
		request.setAttribute("rs",map);
		return mapping.findForward("viewTsqsxx");
	}
	
	/**特色寝室审核结果查询默认页面
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalAccessException */
	public ActionForward tsqsshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response) 
			throws IllegalAccessException, InvocationTargetException{
		gyglDao dao = new gyglDao();
		DAO  dbdao = DAO.getInstance();
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgModel model = new GyglZjlgModel();
		GyglZjlgService service = new GyglZjlgService();
		HttpSession session = request.getSession();
		List topTr = new ArrayList<HashMap<String,String>>();
		String userType = (String)session.getAttribute("userType");
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			topTr = service.getTopName("tsqssh");
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs",service.xsQueryTsqsShcx_ser(userName));
			return mapping.findForward("xstsqsShcx");
		}
		String lddm = myForm.getLddm();
		String lc = myForm.getLc();
		myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		BeanUtils.copyProperties(model, myForm);
		if("query".equals(doType)){
			request.setAttribute("rs",service.queryTsqsShcx_ser(myForm, model,userType,userDep));
			topTr = service.getTopName("tsqssh");
			request.setAttribute("topTr", topTr);
		}
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		if("admin".equals(userType) || "xx".equals(userType)){
			request.setAttribute("writeAble", "yes");
		}
		request.setAttribute("myform", myForm);
		request.setAttribute("tableName", "view_tsqsb");
		request.setAttribute("realTable", "tsqsb");
		request.setAttribute("ldList", dao.getGyLdList());
		request.setAttribute("lcList", GyglShareDAO.getLcList2(lddm==null?"":lddm));
		request.setAttribute("qshList", GyglShareDAO.GetLcQshList(lddm, lc));
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("chkList",dbdao.getChkList(3));
		return mapping.findForward("tsqsManage");
	}
	
	/**
	 * 楼层长队伍查询
	 */
	public ActionForward dormCadreStat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response) throws Exception{
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
//		String lddm = myForm.getLddm();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglZjlgModel model = new GyglZjlgModel();
			BeanUtils.copyProperties(model,myForm); 
			List topTr = service.getDormCadreStat();
			rs = service.ser_dormCadreStat(model);
			request.setAttribute("topTr", topTr);			
			request.setAttribute("rsNum",rs.size());
		}
		request.setAttribute("rs",rs);
		gyglDao.getLdLcQshList(request);
		return mapping.findForward("dormCadreStat");
	}
	/**
	 * 导出数据
	 * @throws Exception 
	 */
	public ActionForward expCadreStatData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglZjlgForm myForm = (GyglZjlgForm) form;
		GyglZjlgService service = new GyglZjlgService();
		GyglZjlgModel model = new GyglZjlgModel();
		BeanUtils.copyProperties(model,myForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		try {
			service.ser_expDormCadreStat(wwb, model);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return mapping.findForward("");
	}
}
