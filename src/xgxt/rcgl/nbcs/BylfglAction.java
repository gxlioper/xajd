package xgxt.rcgl.nbcs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class BylfglAction  extends DispatchAction {
	
	/***
	 * 加载学籍信息列表
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");//判断用户读写权		
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
	}
	
	/***
	 * 加载时间信息列表
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendTimeList(HttpServletRequest request){
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		request.setAttribute("yfList", Base.getYfList());//月份列表
	}
	
	/**
	 * 显示学院毕业礼服管理信息页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglForm model = (BylfglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		request.setAttribute("realTable", "bylfglb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("bylfgl");
	}
	
	/**
	 * 查询学院毕业礼服信息
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		List<String[]> rs = service.queryBylfglb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getBylfsqTopTr());		
		request.setAttribute("realTable", "bylfglb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("bylfgl");
	}
	
	/**
	 * 显示学院毕业礼服申请页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglForm model = (BylfglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		model.setNd(Base.currNd);
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("bylfsqAdd");
	}
	
	/**
	 * 显示学院毕业礼服申请修改页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryBylfglbOne(model));//按主键查询信息
		return mapping.findForward("bylfsqModi");
	}
	
	/**
	 * 毕业礼服申请信息保存
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		String page = "bylfsqModi";
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		model.setPkValue(model.getNd()+model.getXydm());//设置主键值		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "bylfsqAdd" : page;
		
		request.setAttribute("result", service.saveBylfglbSq(model,request));//信息保存
		request.setAttribute("rs", service.queryBylfglbOne(model));
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward(page);
	}
	
	/**
	 * 毕业礼服信息删除
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delBylfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		request.setAttribute("result", service.delBylfglb(model));//删除信息
		request.setAttribute("xyList", Base.getXyList());//学院列表
		return bylfcx(mapping, form, request, response);
	}
	
	/**
	 * 毕业礼服信息查询导出
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expBylfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xydm"));
		
		String[] colList = {"nd","xydm","xymc","sqsj","lqr","xxsh","shryhm","shrxm","shsj","bkfxl","bkfl","bkfm","bkfs","zkfxl","zkfl","zkfm","zkfs","dsfl","dsfm","dsfs","dsfxl","ghbkfl","xzfxl","xzfl","xzfm","xzfs","ghbkfxl","ghbkfl","ghbkfm","ghbkfs","ghzkfxl","ghzkfl","ghzkfm","ghzkfs","ghdsfxl","ghdsfl","ghdsfm","ghdsfs","ghxzfxl","ghxzfl","ghxzfm","ghxzfs","ghmaozi","ghpijian","ghlingdai","ghlingjie","lingdai","lingjie","maozi","pcje","pijian","shfz","shlingdai","shlingjie","shmaozi"};
		String[] colListCN = service.getColCN("view_bylfglb", colList);
		List<String[]> rs = service.queryBylfglbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 显示学院毕业礼服管理信息审核页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfshgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("bylfshgl");
	}
	
	/**
	 * 查询学院毕业礼服信息审核
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		List<String[]> rs = service.queryBylfglb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getBylfsqTopTr());		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("bylfshgl");
	}
	
	/**
	 * 显示学院毕业礼服申请单个审核页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfsqAudi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryBylfglbOne(model));//按主键查询信息
		return mapping.findForward("bylfsqAudi");
	}
	
	/**
	 * 毕业礼服申请审核信息保存
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		model.setPkValue(model.getNd()+model.getXydm());//设置主键值		
		model.setShryhm(request.getSession().getAttribute("userName").toString());
		model.setShsj(GetTime.getSystemTime());
		
		request.setAttribute("result", service.saveBylfglbSh(model,request));//信息保存
		request.setAttribute("rs", service.queryBylfglbOne(model));
		return mapping.findForward("bylfsqAudi");
	}
	
	/**
	 * 毕业礼服申请审核信息批量保存
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfsqshBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setXxsh(request.getParameter("xxsh"));
		model.setShryhm(request.getSession().getAttribute("userName").toString());
		model.setShsj(GetTime.getSystemTime());
		
		request.setAttribute("result", service.saveBylfglbShBatch(model));//信息保存
		return bylfshcx(mapping, form, request, response);
	}
	
	/**
	 * 显示学院毕业礼服管理归还登记页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfghdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("bylfghdj");
	}
	
	/**
	 * 查询学院毕业礼服归还登记信息
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfghdjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setXxsh("通过");
		
		List<String[]> rs = service.queryBylfglbGhxx(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getBylfghdjTopTr());		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("bylfghdj");
	}
	
	
	/**
	 * 显示学院毕业礼服归还登记页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfgh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryBylfglbOne(model));//按主键查询信息
		return mapping.findForward("bylfgh");
	}
	
	/**
	 * 毕业礼服归还登记信息保存
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfghdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		model.setPkValue(model.getNd()+model.getXydm());//设置主键值
		
		request.setAttribute("result", service.saveBylfghdj(model,request));//信息保存
		request.setAttribute("rs", service.queryBylfglbOne(model));
		return mapping.findForward("bylfgh");
	}
	
	/**
	 * 打印毕业礼服归还一览表
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward printBylfghllb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xydm"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printBylfghllb(response.getOutputStream(),model);
		return mapping.findForward("");
	}
	
}
