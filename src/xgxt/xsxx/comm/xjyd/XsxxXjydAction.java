package xgxt.xsxx.comm.xjyd;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.audit.spgc.AuditingInterface;
import xgxt.audit.spgc.AuditingModel;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * 学生信息-学籍异动
 * @author Penghui.Qu
 */
public class XsxxXjydAction extends BasicAction {

	
	/**
	 * 学籍异动类别管理 
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydlbgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxXjydForm model = (XsxxXjydForm) form;
		String[] topTr = new String[] { "异动类别代码", "异动类别名称", "学籍状态",
						"是否在校","审核流程", "申请人范围" };

		//查询
		//if (QUERY.equalsIgnoreCase(model.getDoType())){
			XsxxXjydService service = new XsxxXjydService();
			request.setAttribute("rs", service.getYdlbList(model));
		//}
		
		setListData(request,"ydlbgl");
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "xsxx_xjydlbgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydlbgl");
	}
	
	
	/**
	 * 异动类别删除
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		XsxxXjydForm model = (XsxxXjydForm) form;
		//System.out.println("进来了");
		deleteOperation(request, tableName);
		
		model.setDoType(QUERY);
		return xjydlbgl(mapping,form,request,response);
	}
	
	
	
	/**
	 * 异动类别增加
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		//增加
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			insertOperation(request, tableName);
		}
		
		setListData(request,"ydlbgl");
		return mapping.findForward("ydlbAdd");
	}
	
	
	/**
	 * 异动类别修改
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		//修改
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			updateOperation(request, tableName);
		}
		
		setListData(request,"ydlbgl");
		return mapping.findForward("ydlbUpdate");
	}
	
	
	
	/**
	 * 异动类别加载单条记录
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		String pkValue = request.getParameter("pkValue");
		
		//单个查询
		if (StringUtils.isNotNull(pkValue)){
			selectPageDataByOne(request, tableName, tableName, pkValue);
		}
		
		setListData(request,"ydlbgl");
		return mapping.findForward("ydlbUpdate");
	}
	
	
	/**
	 * 批量设置申请人范围
	 * @return
	 * @throws Exception
	 */
	public ActionForward plszSqrfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		
		boolean result = service.plszSqrfw(model);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		model.setDoType(QUERY);
		return xjydlbgl(mapping,form,request,response);
	}
	
	
	
	/**
	 * 学籍异动申请
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "bks_xjydxx";
		String shztb = "xg_xsxx_xjydshztb";
		String xh = request.getParameter("xh");
		XsxxXjydForm model = (XsxxXjydForm) form;
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)){
			xh = userName;
		}
		
		//加载学生基本信息
		if (StringUtils.isNotNull(xh)){
			XsxxglService xsxxglService = new XsxxglService();
			request.setAttribute("rs", xsxxglService.selectStuinfo(xh));
			
			XsxxXjydService service = new XsxxXjydService();
			//同一学年同一天不能重复申请
			if(Globals.XXDM_HBJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
				request.setAttribute("sfcf", true);
			}else{
				request.setAttribute("sfcf", service.sfcf(xh));
			}
		}
		
		//申请保存
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			//---------- 2012-10-22 honglin 湖北交通职业技术学院 begin----------//
			if(Globals.XXDM_HBJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
				XsxxXjydService service = new XsxxXjydService();
				String xsxh= request.getParameter("save_xh");
				String xn = model.getXn();
				String ydlbm =request.getParameter("save_ydlbm");
				if(StringUtils.isNotNull(xsxh) && StringUtils.isNotNull(xn) && StringUtils.isNotNull(ydlbm)){
					//同一学年同一学号不能重复申请
					boolean cf = service.sfcfByXn(xsxh,xn,ydlbm);
					if(cf){}else{
						request.setAttribute("message", "该学生在"+xn+"学年已经申请过此类别的学籍异动，不能再次进行申请!");
						model.setSave_id(GetSysData.getGuid());
						model.setSave_sqsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
						setListData(request,"xjydsq");
						request.setAttribute("xn", model.getXn());
						request.setAttribute("path", "xsxx_xjydsq.do");
						FormModleCommon.commonRequestSet(request);
						return mapping.findForward("xjydsq");
					}
				}
			}
			//---------- 2012-10-22 honglin 湖北交通职业技术学院 end----------//
			//保存申请记录
			insertOperation(request, tableName);
			boolean result = (Boolean)request.getAttribute("result");
			
			//提交到审核流程 && "是".equalsIgnoreCase(model.getSftj())
			if (result){
				AuditingInterface manage = new XsxxXjydService();
				AuditingModel auditModel = new AuditingModel();
				
				auditModel.setXtgwid("Applicant");
				auditModel.setId(model.getSave_id());
				auditModel.setShlcid(model.getSave_shlcid());
				auditModel.setShr(model.getSave_sqr());
				auditModel.setSftj(model.getSftj());
				manage.saveAuditing(auditModel, shztb,null,auditModel);
			}
		} else {
			model.setSave_xn(Base.currXn);
			model.setSave_xq(Base.currXq);
		}
		
		model.setSave_id(GetSysData.getGuid());
		model.setSave_sqsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		setListData(request,"xjydsq");
		request.setAttribute("xn", model.getXn());
		request.setAttribute("path", "xsxx_xjydsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydsq");
	}
	
	
	
	/**
	 * 学籍异动添加(异动信息直接提交正式库)
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "bks_xjydxx";
		String xh = request.getParameter("xh");
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		
		if (StringUtils.isNull(request.getParameter("save_xn"))) {
			model.setXn(Base.currXn);
		}else{
			model.setXn(request.getParameter("save_xn"));
		}
		//加载学生基本信息
		if (StringUtils.isNotNull(xh)){
			XsxxglService xsxxglService = new XsxxglService();
			request.setAttribute("rs", xsxxglService.selectStuinfo(xh));
		}
		
		//申请保存
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			//保存申请记录
			insertOperation(request, tableName);
			boolean result = (Boolean)request.getAttribute("result");
			
//			if (result){//提交异动信息到正式库
//				service.submitStuInfo(new String[]{model.getSave_id()});
//			}
		}
		
		model.setSave_id(GetSysData.getGuid());
		model.setSave_sqsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		setListData(request,"xjydsq");
		request.setAttribute("xn",model.getXn());
		return mapping.findForward("xjydAdd");
	}
	
	
	
	
	/**
	 * 学籍异动审核查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		String[] topTr = new String[]{"学号","姓名","学年","学期","异动类别",
				"转出"+Base.YXPZXY_KEY,"转出班级","转入"+Base.YXPZXY_KEY,"转入班级","申请时间"};
		
		//查询
//		if (QUERY.equalsIgnoreCase(model.getDoType())){
			List<String[]> rs = service.queryXjydsh(model, getUser(request));
			request.setAttribute("rs", rs);
//		}
		
		setListData(request,"xjydsh");
		setNjXyZyBj(request,model);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		
		request.setAttribute("xn", model.getXn());
		request.setAttribute("path", "xsxx_xjydsh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xjydsh");
	}
	/**
	 * 学籍异动审核查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydshquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return xjydsh(mapping, form, request, response);
	}
	
	
	
	/**
	 * 批量审核学籍异动
	 * @return
	 * @throws Exception
	 */
	public ActionForward plshXjyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxXjydForm model = (XsxxXjydForm) form;
		String[] pkValues = model.getPrimarykey_cbv(); 
		User user = getUser(request);
		
		model.setShr(user.getUserName());
		model.setShsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		
		//boolean result = service.plshXjyd(model, pkValues);
		String shztb = "xg_xsxx_xjydshztb";
		AuditingInterface manage = new XsxxXjydService();
		AuditingModel auditingModel = new AuditingModel();
		auditingModel.setSftj(model.getSftj());
		auditingModel.setShr(model.getShr());
		auditingModel.setShyj(model.getShyj());
		auditingModel.setShzt(model.getShjg());
		
		boolean result = manage.saveBatchAuditingAndDoSomething(auditingModel, pkValues, shztb,null,auditingModel);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		model.setDoType(QUERY);
		return xjydsh(mapping, form, request, response);
	}
	
	
	
	
	/**
	 * 学籍异动结果查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		XsxxXjydService service = new XsxxXjydService();
		String[] topTr = new String[]{"学号","姓名","学年","学期","异动类别","转入班级","转出班级","申请时间","审核结果"};
		
//		if (QUERY.equalsIgnoreCase(model.getDoType())){
			List<String[]> rs = service.queryXjyd(model,getUser(request));
			request.setAttribute("rs", rs);
//		}
		
		setListData(request,"xjydcx");
		setNjXyZyBj(request,model);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		request.setAttribute("xn", model.getXn());
		request.setAttribute("path", "xsxx_xjydcx.do");
		FormModleCommon.commonRequestSet(request);
		//学校个性化
		if(Globals.XXDM_GLLGDX.equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsxx/comm/xjyd/xxgxh/xjydcx_"+Base.xxdm+".jsp",false);
		}
		return mapping.findForward("xjydcx");
	}
	/**
	 * 学籍异动结果查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydcxquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return xjydcx(mapping, form, request, response);
	}
	
	/**
	 * 学籍异动删除
	 * @return
	 * @throws Exception
	 */
	public ActionForward delXjyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();

		boolean result = service.delXjyd(model);
		request.setAttribute("message", result ? DEL_SUCCESS : DEL_FAIL);

		model.setDoType(QUERY);
		return xjydcx(mapping, form, request, response);
	}
	
	
	
	/**
	 * 学籍异动单条审核查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydDgshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxXjydService service = new XsxxXjydService();
		String pkValue = request.getParameter("pkValue");
		String userName = (String) request.getSession().getAttribute("userName");
		
		//单个查询
		if (StringUtils.isNotNull(pkValue)){
			HashMap<String,String> rs = service.getXjydById(pkValue);//申请信息
			//rs.putAll(service.getShxxById(pkValue, userName));//审核信息
			
			request.setAttribute("rs", rs);
			request.setAttribute("cj", service.getXscjByXh(rs.get("xh")));
			//退回时需要选择退回到哪个流程
			//XsxxXjydService manage = new XsxxXjydService();
			AuditingModel auditingModel = new AuditingModel();
			auditingModel.setId(rs.get("id"));
			auditingModel.setShr(userName);
			//审批岗位
			String spgwid = service.getSpgw(auditingModel, "xg_xsxx_xjydshztb");
			auditingModel.setXtgwid(spgwid);
			auditingModel.setShlcid(rs.get("shlcid"));
			
			//是否最后一级审核
			request.setAttribute("isLastSpgw", service.isLastAudit(auditingModel));
			
			request.setAttribute("xtgwList", service.getKthXtgw(rs.get("shlcid"), spgwid));
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(rs.get("ydhxydm")));
			request.setAttribute("xnList", Base.getXnndList()); // 发送学年列表
			String bjKey = rs.get("ydhxydm") + "!!" + rs.get("ydhzydm") + "!!" + rs.get("ydhnj");
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		}
		
		request.setAttribute("now", GetTime.getTimeByFormat("yyyy-mm-dd"));
		
		setListData(request,"dgsh");
		return mapping.findForward("dgshXjyd");
	}
	
	
	
	
	/**
	 * 学籍异动单个审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "bks_xjydxx";
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			//修改申请记录，保证审核人可以分班
			updateOperation(request, tableName);
			boolean result = (Boolean) request.getAttribute("result");
			
			if (result){
				AuditingInterface manage = new XsxxXjydService();
				AuditingModel auditingModel = new AuditingModel();
				auditingModel.setId(request.getParameter("pkValue"));
				auditingModel.setSftj(model.getSftj());
				auditingModel.setShr(model.getShr());
				auditingModel.setShyj(model.getShyj());
				auditingModel.setShzt(model.getShjg());
				auditingModel.setShlcid(model.getSave_shlcid());
				auditingModel.setThgw(model.getThjsr());
				manage.saveAuditing(auditingModel, "xg_xsxx_xjydshztb",null,auditingModel);
			}
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		setListData(request,"dgsh");
		return mapping.findForward("dgshXjyd");
	}
	
	
	/**
	 * 学籍异动-流程跟踪
	 * @return
	 * @throws Exception
	 */
	public ActionForward lcgzXjyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("pkValue");
		XsxxXjydService service = new XsxxXjydService();
		
		if (StringUtils.isNotNull(id)){
			HashMap<String,String> sqxx = service.getXjydById(id);//申请信息
			List<HashMap<String,String>> shxx = service.getShxxList(id);//审核信息

			request.setAttribute("sqxx", sqxx);
			request.setAttribute("rs", shxx);
		}
		
		return mapping.findForward("lcgzXjyd");
	}
	
	
	
	/**
	 * 学籍异动修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		String pkValue = request.getParameter("pkValue");
		
		//单个查询
		if (StringUtils.isNotNull(pkValue)){
			HashMap<String,String> rs = service.getXjydById(pkValue);//申请信息
			request.setAttribute("rs", rs);
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("xnList", Base.getXnndList()); // 发送学年列表
			request.setAttribute("xqList", Base.getXqList()); // 发送学期列表
			request.setAttribute("zyList", (Base.getZyMap()).get(rs.get("ydhxydm")));
			String bjKey = rs.get("ydhxydm") + "!!" + rs.get("ydhzydm") + "!!" + rs.get("ydhnj");
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		}
		
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			
			String tableName = "bks_xjydxx";
			String shztb = "xg_xsxx_xjydshztb";
			
			updateOperation(request, tableName);
			boolean result = (Boolean)request.getAttribute("result");
			
			//提交到审核流程
			if (result && "是".equalsIgnoreCase(model.getSftj())){
				AuditingInterface manage = new XsxxXjydService();
				AuditingModel auditModel = new AuditingModel();
				
				auditModel.setXtgwid("Applicant");
				auditModel.setId(model.getSave_id());
				auditModel.setShlcid(model.getSave_shlcid());
				auditModel.setShr(model.getSave_sqr());
				auditModel.setSftj(model.getSftj());
				result = manage.saveAuditing(auditModel, shztb,null,auditModel);
			}
			
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		setListData(request,"dgsh");
		return mapping.findForward("xjydUpdate");
	}
	
	
	
	/**
	 * 加载下拉列表的数据
	 * @param reqeust
	 * */
	public void setListData(HttpServletRequest request,String flg){
		XsxxXjydService service = new XsxxXjydService();
		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xq",Base.currXq);
		//学籍状态
		request.setAttribute("xjztList", xsxxglService.getList("xjztList"));
		//是否在校
		request.setAttribute("sfzxList", xsxxglService.getList("sfzxList"));
		//异动类型
		request.setAttribute("ydlbList", service.getYdlbByUser(getUser(request)));
		request.setAttribute("ydlbAllList", service.getYdlbAll(getUser(request)));
		if ("ydlbgl".equalsIgnoreCase(flg)){
			//审核流程
			request.setAttribute("shlcList", xsxxglService.getList("shlcList"));
			request.setAttribute("xnList", Base.getXnndList()); // 发送学年列表
		} else if ("xjydsq".equalsIgnoreCase(flg)){
			//异动类别
			request.setAttribute("xnList", Base.getXnndList()); // 发送学年列表
			request.setAttribute("xqList", Base.getXqList());//学期列表
			//年级、学院、专业、班级
			FormModleCommon.setNjXyZyBjList(request);
		} else if ("xjydcx".equalsIgnoreCase(flg)) {
			//审核状态
			request.setAttribute("shjgList", service.getList("shjg"));
			request.setAttribute("xnList", Base.getXnndList()); // 发送学年列表
			request.setAttribute("xqList", Base.getXqList());//学期列表
		} else if ("xjydsh".equalsIgnoreCase(flg)) {
			//审核状态
			request.setAttribute("shztList",service.getList("shzt"));
			request.setAttribute("xnList", Base.getXnndList()); // 发送学年列表
			request.setAttribute("xqList", Base.getXqList());//学期列表
		} else if ("dgsh".equalsIgnoreCase(flg)){
			//审核状态
			request.setAttribute("shztList",service.getList("shzt"));
			//异动类别
			request.setAttribute("xnList", Base.getXnndList()); // 发送学年列表
		}
	}
	
	
	
	/**
	 * 年级、学院、专业、班级
	 * @param request
	 * @param model
	 */
	private void setNjXyZyBj(HttpServletRequest request,XsxxXjydForm model){
		
		String ydqnj = model.getYdqnj();
		String ydqxydm = model.getYdqxydm();
		String ydqzydm = model.getYdqzydm();
		String ydhqbdm = model.getYdqbdm();
		
		ydqnj = (ydqnj == null) ? "" : ydqnj;
		ydqxydm = (ydqxydm == null) ? "" : ydqxydm;
		ydqzydm = (ydqzydm == null) ? "" : ydqzydm;
		ydhqbdm = (ydhqbdm == null) ? "" : ydhqbdm;
		
		//异动前学院、专业、班级列表
		String ydqBjKey = ydqxydm + "!!" + ydqzydm + "!!" + ydqnj;
		request.setAttribute("ydqXyList", Base.getXyList());
		request.setAttribute("ydqZyList", (Base.getZyMap()).get(ydqxydm));
		request.setAttribute("ydqBjList", (Base.getBjMap()).get(ydqBjKey));
		
		
		String ydhnj = model.getYdhnj();
		String ydhxydm = model.getYdhxydm();
		String ydhzydm = model.getYdhzydm();
		String ydhbdm = model.getYdhbdm();
		
		ydhnj = (ydhnj == null) ? "" : ydhnj;
		ydhxydm = (ydhxydm == null) ? "" : ydhxydm;
		ydhzydm = (ydhzydm == null) ? "" : ydhzydm;
		ydhbdm = (ydhbdm == null) ? "" : ydhbdm;
		
		//异动后学院、专业、班级列表
		String ydhBjKey = ydhxydm + "!!" + ydhzydm + "!!" + ydhnj;
		request.setAttribute("ydhXyList", Base.getXyList());
		request.setAttribute("ydhZyList", (Base.getZyMap()).get(ydhxydm));
		request.setAttribute("ydhBjList", (Base.getBjMap()).get(ydhBjKey));
		
		request.setAttribute("njList", Base.getNjList());
	}


	
	/**
	 * 异动文号处理
	 */
	public ActionForward ydwhcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		String[] topTr = new String[]{"处理文号","学年","学号","姓名","异动类别","异动前班级名称","异动后班级名称","申请时间"};
		
//		if (QUERY.equalsIgnoreCase(model.getDoType())){
			model.setShzt("通过");
			List<String[]> rs = service.queryWhcl(model,getUser(request));
			request.setAttribute("rs", rs);
//		}
		
		setListData(request,"xjydcx");
		setNjXyZyBj(request,model);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		request.setAttribute("path", "xsxx_ydwhcl.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		
		return mapping.findForward("ydwhcl");
	}


	/**
	 * 保存处理文号
	 */
	public ActionForward saveClwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		
		boolean result = service.saveClwh(model);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return ydwhcl(mapping, form, request, response);
	}
	/**
	 * 删除类别使用检测
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DelDetectModel model = new DelDetectModel();
		XsxxXjydService service = new XsxxXjydService();

		// 导出形式
		String[] pkValue = request.getParameter("pk").split("!!@@!!");
		model.setPkValue(pkValue);

		// 提示信息
		String message = service.checkDel(model);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * 修改类别使用检测
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DelDetectModel model = new DelDetectModel();
		XsxxXjydService service = new XsxxXjydService();

		// 导出形式
		String[] pkValue = request.getParameter("pk").split("!!@@!!");
		model.setPkValue(pkValue);

		// 提示信息
		String message = service.checkUpdate(model);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * 检测异动类别设置人范围是否可以使用
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSqrfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DelDetectModel model = new DelDetectModel();
		XsxxXjydService service = new XsxxXjydService();

		// 导出形式
		String[] pkValue = request.getParameter("pk").split("!!@@!!");
		model.setPkValue(pkValue);

		// 提示信息
		String message = service.checkSqrfw(model);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 学籍异动统计
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		List<HashMap<String, String>> topTr =service.getXjydTop(model);
		//查询
		if (QUERY.equalsIgnoreCase(model.getDoType())){
			request.setAttribute("rs", service.xjydtj(model));
		}
		
		setListData(request,"ydlbgl");
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "xsxx_xjydtj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydtj");
	}
	
	/**
	 * 结果导出
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydService service = new XsxxXjydService();
		XsxxXjydForm myForm = (XsxxXjydForm) form;
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = service.getXjydDcTop(myForm);
		List<String[]> rs = service.xjydtj(myForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 学籍异动学生
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxXjydxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		String fwcs =  request.getParameter("fwcs");
		XsxxXjydService service = new XsxxXjydService();
		request.setAttribute("rs", service.cxXjydxs(model,fwcs));
		String[] topTr = new String[]{"学号","姓名","学院","专业","班级","异动时间"};
		setListData(request,"ydlbgl");
		request.setAttribute("topTr", topTr);
		request.setAttribute("sqkssj", model.getSqkssj());
		request.setAttribute("sqjssj", model.getSqjssj());
		String tjfwmc="";
		String fwcsmc="";
		if("ydqxydm".equals(model.getTjfw())){
			tjfwmc="学院";
			List<HashMap<String,String>> list= service.getNjxyzybjmc("ydqxydm",fwcs);
			if(null!=list&&list.size()>0){
				fwcsmc = list.get(0).get("xymc");
			}else{
				fwcsmc="合计";
			}
		}
		if("ydqzydm".equals(model.getTjfw())){
			tjfwmc="专业";
			List<HashMap<String,String>> list= service.getNjxyzybjmc("ydqzydm",fwcs);
			if(null!=list&&list.size()>0){
				fwcsmc = list.get(0).get("zymc");
			}else{
				fwcsmc="合计";
			}
		}
		if("ydqbdm".equals(model.getTjfw())){
			tjfwmc="班级";
			List<HashMap<String,String>> list= service.getNjxyzybjmc("ydqbdm",fwcs);
			if(null!=list&&list.size()>0){
				fwcsmc = list.get(0).get("bjmc");
			}else{
				fwcsmc="合计";
			}
		}
		if("xn".equals(model.getTjfw())){
			tjfwmc="学年";
			fwcsmc=fwcs;
		}
		if("ydqnj".equals(model.getTjfw())){
			tjfwmc="年级";
			fwcsmc=fwcs;
		}
		request.setAttribute("tjfw",tjfwmc);
		request.setAttribute("fwcs",fwcs);
		request.setAttribute("fwcsmc",fwcsmc);
		request.setAttribute("tjfwdm",model.getTjfw());
		request.setAttribute("ydlbm",model.getYdlbm());
		request.setAttribute("ydlbmc",service.getXjydlbmc(model.getYdlbm()).get(0).get("ydlbmc"));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxXjydxs");
	}
}
