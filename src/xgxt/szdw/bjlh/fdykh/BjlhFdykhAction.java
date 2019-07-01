package xgxt.szdw.bjlh.fdykh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.utils.FormModleCommon;

import common.Globals;

public class BjlhFdykhAction extends BasicExtendAction {
	
	/**
	 *=============统计类别参数=============================
	 */
	public static final String B_BJ = "BJ"; //按班级
	public static final String B_RS = "RS"; //按人数
	public static final String B_ZZ = "ZZ"; //按职责
	/**
	 *=============统计类别参数=============================
	 */
	
	/**
	 * 辅导员考核-考核对象设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhKhdxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		//判断必须由辅导员进入该界面
		if("stu".equals(user.getUserType())|| "true".equalsIgnoreCase(user.getFdyQx()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "辅导员及班主任用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;

		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("khdxxz"));
		request.setAttribute("rs", service.getTableList(myForm, request));

		// write和title
		setWriteAbleAndTitle(request, "bjlh_fdykh_khdxsz.do");

		request.setAttribute("realTable", "xg_gygl_new_cwxxb"); // 导入表
		request.setAttribute("tableName", "xg_gygl_new_cwxxb"); // 导出视图

		request.setAttribute("path", "bjlh_fdykh_khdxsz.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("khdxsz");
	}

	/**
	 * 辅导员考核-考核对象设置分配表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhKhdxszEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		//如果是学院用户，设置查询条件
		User user = getUser(request);
		if("xy".equals(user.getUserType())){
			myForm.setBmdm(user.getUserDep());
			request.setAttribute("disabled", "true");
		}
		String doType = request.getParameter("doType");
		// 设置初始化
		if ("sz".equalsIgnoreCase(doType)) {
			
			request.getSession().setAttribute("khzgh", myForm.getCheckVal());
			// 保存设置
		} else if ("save".equalsIgnoreCase(doType)) {
			String[] yhs = request.getParameterValues("primarykey_cbv");
			String[] khzgh = (String[]) request.getSession().getAttribute(
					"khzgh");

			// 保存辅导员考核设置
			String message = service.saveFdyKh(yhs, khzgh) ? "设置成功！" : "设置失败！";

			// 从session中去除已设置的考核职工号
			request.getSession().removeAttribute("khzgh");
			request.setAttribute("message", message);

		}

		try {
			request.setAttribute("topTr", service.getTopTr("khdxxzyh"));
			request.setAttribute("rs", service.queryYh(myForm));

			FormModleCommon.requestSetList(new String[] { "yjbm" }, request);
			request.setAttribute("pageSize", myForm.getPages().getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("szedit");
	}

	/**
	 * 辅导员考核测评表管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问思政队伍-辅导员考评-辅导员考核表管理-增加XN:{xn},KHBMC:{khbmc},PFDX:{pfdx};修改KHBID:{khbid},KHBMC:{khbmc}; 删除KHBID:{khbid}")
	public ActionForward fdykhKhcpbgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		User user = getUser(request);
		//判断必须由辅导员进入该界面
		if(!"xx".equals(user.getUserType()) && !"admin".equals(user.getUserType())){
			request.setAttribute("yhInfo", "非校级用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		// 操作
		String doType = request.getParameter("doType");
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();

		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveKhbInfo(myForm, "add");
			} else if ("update".equals(doType)) {// 修改
				message = service.saveKhbInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.deleteKhbInfo(myForm);
			} else if ("copy".equals(doType)) {// 复制
				message = service.copyKhbInfo(myForm) ? "复制成功！" : myForm.getXn() + "年度中对应的评分对象考核表已存在！";
			} else if ("sfqy".equals(doType)) {// 是否启用
				message = service.sfqyKhb(myForm);
			}
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getFdykhList(myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());

		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("fdykh"));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("pfdxList", service.getPfdxList());

		return mapping.findForward("fdykhKhcpbgl");
	}

	/**
	 * 辅导员考核-学生考核测评
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXskhcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象辅导员
			lx="辅导员";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象班主任
			lx="班主任";
		}
		
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("xs");

		String yhm = user.getUserName();
		List<HashMap<String,String>> fdylist = service.getYhmxx(yhm,lx);
		String dqsj = service.getDate();
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "暂无测评表需填写，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
		}
		//判断必须由辅导员进入该界面
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "非学生用户无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(fdylist.size()== 0){
				request.setAttribute("yhInfo", "您没有对应的辅导员，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);				
			}else if(fdylist.size()== 1){
				return new ActionForward("/bjlh_fdykh.do?method=fdykhXskhpf&khzgh="+fdylist.get(0).get("zgh"), false);		
			}else{
				if (QUERY.equals(myForm.getType())){
					myForm.setKhbid(mrsz.get("khbid"));
					myForm.setYhm(yhm);
					List<HashMap<String,String>> khFdyList=service.getKhFdyList(myForm);
					JSONArray dataList = JSONArray.fromObject(khFdyList);
					response.getWriter().print(dataList);
					return null;
				}
				String path = "bjlh_fdykh_xskhcp.do";
				request.setAttribute("path", path);
				FormModleCommon.commonRequestSet(request);
				
				return mapping.findForward("khFdyList");
			}
		}
		
/*		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "暂无测评表需填写，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			
			// 判断 当前学年是否启用 的自评表
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz
							.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz
							.get("khjssj"))) {
				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
		}

		String doType = request.getParameter("doType");
		myForm.setKhbid(mrsz.get("khbid"));
		myForm.setYhm(yhm);
		myForm.setFdyid(rs1.get("zgh"));
		HashMap<String, String> rs = service.getKhbxx(myForm);
		
		if (doType != null && "save".equalsIgnoreCase(doType)) {
			String[] xmid = request.getParameterValues("xmid");
			String[] df = request.getParameterValues("df");
			if (service.saveFdykhpfb(xmid, df, myForm)) {
				request.setAttribute("message", "保存成功");
			} else {
				request.setAttribute("message", "保存失败");
			}
		}
		// write和title
		setWriteAbleAndTitle(request, "bjlh_fdykh_xskhcp.do");
		request.setAttribute("rs", rs);// 考核表信息
		request.setAttribute("rs1", rs1);// 考核表信息
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// 获取经过处理一级指标的考核表项目列表
		//池州职业技术学院个性化
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
			request.setAttribute("khbsfzd", service.getSfcgkhsj());//池州学院-判断是否超过考核时间
		}else{
			request.setAttribute("khbsfzd", service.getKhbSfzd(myForm));
		}*/
		
	}
	
	/**
	 * 辅导员考核-学生考核测评-个人评分
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXskhpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("xs");

		String yhm = user.getUserName();
//		List<HashMap<String,String>> fdylist = service.getYhmxx(yhm);
		String zgh = (String) request.getParameter("khzgh");
		String fh = (String) request.getParameter("flag");//判断是否有返回按钮
		request.setAttribute("fh", fh);
		request.setAttribute("khzgh", zgh);
		HashMap<String,String> fdyxx = service.getRsInfo("view_fdyxx", "zgh", zgh, new String[]{"xm","bmmc"});
		
		String dqsj = service.getDate();

		//判断必须由辅导员进入该界面
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "非学生用户无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(zgh== null){
				request.setAttribute("yhInfo", "您没有对应的辅导员，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);				
			}
		}
		
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "暂无测评表需填写，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			
			// 判断 当前学年是否启用 的自评表
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz
							.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz
							.get("khjssj"))) {
				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
		}

		String doType = request.getParameter("doType");
		myForm.setKhbid(mrsz.get("khbid"));
		myForm.setYhm(yhm);
		myForm.setFdyid(zgh);
		HashMap<String, String> rs = service.getKhbxx(myForm);
		
		if (doType != null && "save".equalsIgnoreCase(doType)) {
			String[] xmid = request.getParameterValues("xmid");
			String[] df = request.getParameterValues("df");
			boolean flag= true;
			if(!service.checkFdykhpf(xmid, df)){
				flag= false;
				request.setAttribute("message", "输入分值不符合要求");
			}
			if(flag){
				if ( service.saveFdykhpfb(xmid, df, myForm)) {
					request.setAttribute("message", "保存成功");
				} else {
					request.setAttribute("message", "保存失败");
				}
			}
		}
		// write和title
		setWriteAbleAndTitle(request, "bjlh_fdykh_xskhcp.do");
		request.setAttribute("rs", rs);// 考核表信息
		request.setAttribute("rs1", fdyxx);// 考核表信息
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// 获取经过处理一级指标的考核表项目列表
		//池州职业技术学院个性化
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
			request.setAttribute("khbsfzd", service.getSfcgkhsj());//池州学院-判断是否超过考核时间
		}else{
			request.setAttribute("khbsfzd", service.getKhbSfzd(myForm));
		}
		
		return mapping.findForward("fdykhXskhpf");
	}

	/**
	 * 辅导员考核-教师考核测评
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhJskhcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_jskhcp.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
		}
		String dqsj = service.getDate();
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			request.setAttribute("sfxspfan", false);//是否显示评分按钮
		} else {
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))) {
				request.setAttribute("yhInfo", "辅导员考核未启动，请先启动再进行该操作！");
				request.setAttribute("sfxspfan", false);//是否显示评分按钮
			}
			if (Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))) {
				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
				request.setAttribute("sfxspfan", false);//是否显示评分按钮
			}
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
				if (Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khlrjzsj"))) {
					request.setAttribute("yhInfo", "已超过辅导员考核录入截止时间，无法进行该操作！");
					request.setAttribute("sfxspfan", false);//是否显示评分按钮
				}
			}
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("jskhcp"));
		request.setAttribute("rs", service.getFdykhcpList(user.getUserName(),myForm, request));
		System.out.println(service.getFdykhcpList(user.getUserName(),myForm, request).size());
		request.setAttribute("path", "bjlh_fdykh_jskhcp.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		setWriteAbleAndTitle(request, "bjlh_fdykh_jskhcp.do");

		return mapping.findForward("fdykhJskhcp");
	}
	/**
	 * 辅导员考核-教师考核测评-个人评分
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhJskhpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		String khbid = request.getParameter("khbid");
		String dqsj = service.getDate();
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "暂无测评表需填写，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}else if(!khbid.equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "非当前学年测评表，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			
			// 判断 当前学年是否启用 的自评表
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))) {
				request.setAttribute("yhInfo", "辅导员考核未启动，请先启动再进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			
			if (Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))) {
				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			//--池州学院个性化功能--评分可否修改以考核录入截止时间为准
			if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
				if (Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khlrjzsj"))) {
					request.setAttribute("yhInfo", "已超过辅导员考核录入截止时间，无法进行该操作！");
					return new ActionForward("/yhInfo.do", false);
				}
			}
		}
		
		String fdyid = request.getParameter("fdyid");
		String xm = request.getParameter("xm");
		String bmmc = request.getParameter("bmmc");
		String doType= request.getParameter("doType");
		String yhm = user.getUserName();
		
		myForm.setYhm(yhm);
		myForm.setKhbid(khbid);
		myForm.setFdyid(fdyid);
		if(doType!=null && "save".equalsIgnoreCase(doType)){
			String[] xmid = request.getParameterValues("xmid");
			String[] df = request.getParameterValues("df");
			if (service.saveFdykhpfb(xmid, df, myForm)) {
				request.setAttribute("message", "保存成功");
			} else {
				request.setAttribute("message", "保存失败");
			}
		}
		HashMap<String, String> rs = service.getKhbxx(myForm);
		rs.put("bmmc", bmmc);
		rs.put("xm", xm);
		rs.put("fdyid", fdyid);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// 获取经过处理一级指标的考核表项目列表
		return mapping.findForward("fdykhJskhpf");
	}

	/**
	 * 辅导员考核-考核测评统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhKhcptj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		//判断必须由学院或学校或辅导员用户进入该界面
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "班主任及学生用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		} 
		
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_khcptj.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
		}
		// 学校代码
		String xxdm = Base.xxdm;
		List<String[]> rs = null;
		if(xxdm.equals(Globals.XXDM_CZZYJSXY)){
			rs = service.getFdyKhTjInfoforCzxy(myForm,request);
			request.setAttribute("czxygxh", "yes");
		}else{
			rs = service.getFdyKhTjInfo(myForm,request);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("khbtj"));

		request.setAttribute("searchTj", myForm.getSearchModel());
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_szdw_fdykhbzb");
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj.do");
		return mapping.findForward("fdykhKhcptj");
	}

	/**
	 * 
	 * @描述:辅导员考核<b>新版</b>
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-27 上午10:02:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward fdykhKhcptjNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		//判断必须由学院或学校或辅导员用户进入该界面
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "班主任及学生用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhNewService newService = new BjlhFdykhNewService();
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		} 

		if (QUERY.equalsIgnoreCase(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			
			List<HashMap<String,String>> resultList = null;
			String tylx = myForm.getTjlx();
			if(StringUtils.isNotBlank(tylx) && StringUtils.equals(B_BJ, tylx)){
				resultList = newService.getFdyKhxxByBJ(myForm, user);
			}else if(StringUtils.isNotBlank(tylx) && StringUtils.equals(B_RS, tylx)){
				resultList = newService.getFdyKhxxByRS(myForm, user);
			}else if(StringUtils.isNotBlank(tylx) && StringUtils.equals(B_ZZ, tylx)){
				resultList = newService.getFdyKhxxByZZ(myForm, user);
			}
		
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_khcptj_new.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
			request.setAttribute("searchTj", myForm.getSearchModel());
		}
		
		//List<String[]> rs = null;
		//rs = service.getFdyKhTjInfo(myForm,request);
		//request.setAttribute("rs", rs);
		//request.setAttribute("topTr", service.getTopTr("khbtj"));
		//request.setAttribute("searchTj", myForm.getSearchModel());
		// ----------------- 导入表设置 ------------------------
		//request.setAttribute("realTable", "xg_szdw_fdykhbzb");
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj_new.do"); //标题设置 可读可写
		return mapping.findForward("fdykhKhcptjnew");
	}
	
	/**
	 * 
	 * @描述:考核明细查询
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-18 下午04:53:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward fdykhKhMxNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		BjlhFdykhNewService newService = new BjlhFdykhNewService();
		Object[] mxsj = null; //明细数据
		String pk = request.getParameter("pk");
		String tjlx = null; //查询条件类型 RS BJ ZZ
		if(!StringUtil.isNull(pk)){
			String[] splitVals = xgxt.utils.String.StringUtils.splitStr(pk, "!!one!!");
			tjlx = splitVals[0];
			
			if(xgxt.utils.String.StringUtils.isEqual(tjlx, B_RS)){ //人数条件 Example: RS!!one!!2013-2014!!one!!100100
				String xn = splitVals[1]; //学年
				String zgh = splitVals[2]; //职工号
				myForm.setTjlx(B_RS);
				myForm.setXn(xn);
				myForm.setFdyid(zgh);
			}else if(xgxt.utils.String.StringUtils.isEqual(tjlx, B_BJ)){
				String xn = splitVals[1]; //学年
				String zgh = splitVals[3]; //职工号
				String bjdm = splitVals[2]; //班级代码
				myForm.setTjlx(B_BJ);
				myForm.setXn(xn);
				myForm.setFdyid(zgh);
				myForm.setBjdm(bjdm);
			}else if(xgxt.utils.String.StringUtils.isEqual(tjlx, B_ZZ)){
				String xn = splitVals[1]; //学年
				String zgh = splitVals[3]; //职工号
				String zzlbmc = splitVals[2]; //班级代码
				myForm.setTjlx(B_ZZ);
				myForm.setXn(xn);
				myForm.setFdyid(zgh);
				myForm.setZzlbmc(zzlbmc);
			}
			
			mxsj = newService.getFdyKhMx(myForm, user);
			
			request.setAttribute("xsmxPjf", mxsj[0]);
			request.setAttribute("tmmxZf", mxsj[1]);
			request.setAttribute("xsmxList", mxsj[2]);
			request.setAttribute("tmmxList", mxsj[3]);
			
		}
		request.setAttribute("tjlx", tjlx);
		return mapping.findForward("fdykhKhMxNew");
	}
	
	/**
	 * 
	 * @描述:考核明细列表查询
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-20 上午09:24:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward fdykhKhMxDetailNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		BjlhFdykhNewService newService = new BjlhFdykhNewService();

		Object[] data = newService.getFdyKhxxMxDetail(myForm, user);
		
		//查询
		List<HashMap<String,String>> resultList = (List<HashMap<String, String>>) data[0];
		JSONArray dataList = JSONArray.fromObject(resultList);
		dataList.put(data[1]);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-21 上午10:42:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward exportDataNew (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm model = (BjlhFdykhForm) form;
		BjlhFdykhNewService service = new BjlhFdykhNewService();
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList =  null;
		String dcclbh = request.getParameter("dcclbh");
		if(xgxt.utils.String.StringUtils.isEqual("bjlh_fdykh_new_RS.do", dcclbh)){
			resultList = service.getFdyKhxxByRSAll(model, user);//查询出所有记录，不分页
		}else if(xgxt.utils.String.StringUtils.isEqual("bjlh_fdykh_new_BJ.do", dcclbh)){
			resultList = service.getFdyKhxxByBJAll(model, user);//查询出所有记录，不分页
		}else if(xgxt.utils.String.StringUtils.isEqual("bjlh_fdykh_new_ZZ.do", dcclbh)){
			resultList = service.getFdyKhxxByZZAll(model, user);//查询出所有记录，不分页
		}
		
		
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(dcclbh);//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 辅导员考核项目维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();

		String yhInfo = service.checkKhbQx(myForm);
		if (!"".equals(yhInfo)) {
			request.setAttribute("message", yhInfo+"考核项目不允许维护！");
		    return new ActionForward("/bjlh_fdykh_khcpbgl.do", false);
		}

		if ("del".equals(doType)) {// 删除
			String msg = service.deleteKhbXmxx(myForm) ? "操作成功！" : "操作失败！";
			request.setAttribute("message", msg);
		}
		request.setAttribute("xmxxList", service.getFdykhXmList(myForm));
		// request.setAttribute("xxList", service.getXxlist(myForm, "st"));
		request.setAttribute("doType", doType);
		request.setAttribute("topTr", service.getTopTr("khbxm"));
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");
		return mapping.findForward("fdykhXmwh");
	}

	/**
	 * 测评问卷管理——试题维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXmwhOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("add".equals(doType)) {
			rs.put("khbid", myForm.getKhbid());
		} else if ("add_save".equals(doType)) {
			String msg = service.saveKhbXmxx(myForm, "add");
			request.setAttribute("back", msg);
			// 添加成功后，然后在添加，方便操作
			myForm.setXmid("");
			doType = "add";
			// rs=service.getCpwjStxxOne(myForm);
		} else if ("update".equals(doType)) {
			rs = service.getKhbXmxxOne(myForm);
		} else if ("update_save".equals(doType)) {
			String msg = service.saveKhbXmxx(myForm, "update");
			request.setAttribute("back", msg);
			rs = service.getKhbXmxxOne(myForm);
		}
		// else if("del".equals(doType)){//删除
		// String msg=service.deleteKhbXmxx(myForm)?"操作成功！":"操作失败！";
		// request.setAttribute("back", msg);
		// }
		String fzqj = rs.get("fzqj");
		if(!StringUtils.isEmpty(fzqj)){
			
			if(fzqj.split("-").length == 1){
				rs.put("fzDown", fzqj.split("-")[0]);
			}else if(fzqj.split("-").length == 2){
				if(StringUtils.isBlank(fzqj.split("-")[0])){
					rs.put("fzDown", fzqj.split("-")[1]);
				}else{
					rs.put("fzDown", fzqj.split("-")[0]);
					rs.put("fzUp", fzqj.split("-")[1]);
				}
			}
		}
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		return mapping.findForward("fdykhXmwhOne");
	}

	/**
	 * 考核表-详情查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();

		String doType = request.getParameter("doType");
		if ("save".equals(doType)) {
			// String msg=service.saveCpwjDa(request, myForm)?"保存成功！":"保存失败！";
			// request.setAttribute("back", msg);
		}

		request.setAttribute("rs", service.getKhbxx(myForm));// 考核表信息
		// List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// 获取经过处理一级指标的考核表项目列表

		return mapping.findForward("fdykhXmView");
	}
	
	
	/** 
	 * @描述:(获取辅导员考核表个项目的平均分列表)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-9 下午04:58:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public ActionForward pfmxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		request.setAttribute("xmList", service.getFdykhmx(myForm));// 获取经过处理一级指标的辅导员考核明细
		return mapping.findForward("pfmxCk");
		
	}

	/**
	 * 辅导员成绩统计维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String xn = request.getParameter("xn");
		String zgh = request.getParameter("fdyid");
		myForm.setXn(xn);
		myForm.setZgh(zgh);
		HashMap<String, String> fdycjTjxx = service.fdycjTjWh(myForm);
		request.setAttribute("rs", fdycjTjxx);
		// 获取等级下拉列表框
		request.setAttribute("khdjlist", service.getKhDjList(request));
		request.setAttribute("jtdjlist", service.getJtDjList(request));
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj.do");
		return mapping.findForward("fdycjTjWh");
	}
	
	/**
	 * 辅导员成绩统计查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String xn = request.getParameter("xn");
		String zgh = request.getParameter("fdyid");
		myForm.setXn(xn);
		myForm.setZgh(zgh);
		HashMap<String, String> fdycjTjxx = service.fdycjTjWh(myForm);
		request.setAttribute("rs", fdycjTjxx);
		// 获取等级下拉列表框
		request.setAttribute("khdjlist", service.getKhDjList(request));
		request.setAttribute("jtdjlist", service.getJtDjList(request));
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj.do");
		return mapping.findForward("fdycjTjCk");
	}
	
	/**
	 * 辅导员成绩统计维护保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjWhBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String message = "";
		boolean flag = false;

		String xn = request.getParameter("xn");
		String zgh = request.getParameter("zgh");
		String khdj = service.unicode2Gbk(request.getParameter("khdj"));
		String jtdj = service.unicode2Gbk(request.getParameter("jtdj"));
		String bz = service.unicode2Gbk(request.getParameter("bz"));

		myForm.setXn(xn);
		myForm.setZgh(zgh);
		myForm.setKhdj(khdj);
		myForm.setJtdj(jtdj);
		myForm.setBz(bz);

		flag = service.fdycjTjWhBc(myForm);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 辅导员成绩统计导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expFdycjTj(myForm, response.getOutputStream(),request);
		return null;
	}
	
	public ActionForward exportData (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm model = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,request);//查询出所有记录，不分页
		
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
