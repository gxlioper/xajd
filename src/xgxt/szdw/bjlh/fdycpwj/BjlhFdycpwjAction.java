package xgxt.szdw.bjlh.fdycpwj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhForm;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhService;
import xgxt.utils.FormModleCommon;

public class BjlhFdycpwjAction extends BasicExtendAction{

	/**
	 * 测评问卷管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglManage(ActionMapping mapping, ActionForm form,
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
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		myForm.setFbr(getUser(request).getUserName());
		
		if(!Base.isNull(doType)){
			String message="参数错误！";
			if("add".equals(doType)){//增加
				message=service.saveCpwjInfo(myForm,"add")?"操作成功！":"操作失败！";
			}else if("update".equals(doType)){//修改
				message=service.saveCpwjInfo(myForm,"update")?"操作成功！":"操作失败！";
			}else if("del".equalsIgnoreCase(doType)){//删除
				message=service.checkCpwjQx(myForm);
				if("".equals(message)){
					message = service.deleteCpwjInfo(myForm); 
				}else{
					message+="不可删除！";
				}
			}else if("copy".equals(doType)){//复制
				message=service.copyCpwjInfo(myForm)?"复制成功！":"复制失败！";
			}else if("sfqy".equals(doType)){//是否启用
				message=service.sfqyCpwj(myForm);
			}
			request.setAttribute("message", message);
		}
		
		List<String[]> rs = service.getCpwjList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjgl.do");
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("cpwjgl"));
		request.setAttribute("addxnList", service.getAddXnList());
		
		return mapping.findForward("manage");
	}
	
	/**
	 * 测评问卷管理——编辑
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String doType=request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setFbr(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String,String> rs=new HashMap<String,String>();;
		if("add".equals(doType)){
			rs.put("sfqy", "是");
		}else if("add_save".equals(doType)){
			String msg=service.saveCpwjInfo(myForm,"add")?"操作成功！":"操作失败！";
			request.setAttribute("back", msg);
		}else if("update".equals(doType)){
			rs=service.getCpwjOne(myForm);
		}else if("update_save".equals(doType)){
			String msg=service.saveCpwjInfo(myForm,"update")?"操作成功！":"操作失败！";
			request.setAttribute("back", msg);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		request.setAttribute("addxnList", service.getAddXnList());
		return mapping.findForward("edit");
	}
	
	/**
	 * 测评问卷管理——试题维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglStwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String doType=request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setFbr(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();

		String yhInfo=service.checkCpwjQx(myForm);
		if(!"".equals(yhInfo)){
			request.setAttribute("yhInfo", yhInfo+"试题不允许维护！");
		    return new ActionForward("/yhInfo.do", false);
		}

		HashMap<String,String> rs=new HashMap<String,String>();;
		if("del".equals(doType)){//删除
			String msg=service.saveCpwjStxx(myForm,"delete")?"操作成功！":"操作失败！";
			request.setAttribute("back", msg);
			rs.put("wjid", myForm.getWjid());
			rs.put("sfqy", "是");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("stxxList", service.getCpwjStList(myForm));
//		request.setAttribute("xxList", service.getXxlist(myForm, "st"));
		request.setAttribute("doType", doType);
		request.setAttribute("topTr", service.getTopTr("cpwjst"));
		return mapping.findForward("stwh");
	}
	
	/**
	 * 测评问卷管理——试题维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglStwhOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String doType=request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setFbr(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String,String> rs=new HashMap<String,String>();;
		if("add".equals(doType)){
			rs.put("wjid", myForm.getWjid());
			rs.put("sfqy", "是");
		}else if("add_save".equals(doType)){
			String msg=service.saveCpwjStxx(myForm,"add")?"操作成功！":"操作失败！";
			request.setAttribute("back", msg);
			//添加成功后，然后在添加，方便操作
			myForm.setStid("");
			doType="add";
//			rs=service.getCpwjStxxOne(myForm);
		}else if("update".equals(doType)){
			rs=service.getCpwjStxxOne(myForm);
		}else if("update_save".equals(doType)){
			String msg=service.saveCpwjStxx(myForm,"update")?"操作成功！":"操作失败！";
			request.setAttribute("back", msg);
			rs=service.getCpwjStxxOne(myForm);
		}else if("del".equals(doType)){//删除
			String msg=service.saveCpwjStxx(myForm,"delete")?"操作成功！":"操作失败！";
			request.setAttribute("back", msg);
			rs.put("wjid", myForm.getWjid());
			rs.put("sfqy", "是");
		}
		request.setAttribute("rs", rs);
//		request.setAttribute("stxxList", service.getCpwjStList(myForm));
		request.setAttribute("xxList", service.getXxlist(myForm, "st"));
		request.setAttribute("doType", doType);
		return mapping.findForward("stwhOne");
	}
	
	/**
	 * 测评问卷管理-查看详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){
			String msg=service.saveCpwjDa(request, myForm)?"保存成功！":"保存失败！";
			request.setAttribute("back", msg);
		}

		request.setAttribute("rs", service.getCpwjOne(myForm));//测评问卷信息
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//转换试题选项为字符串
		
		return mapping.findForward("view");
	}
	
	/**
	 * 测评问卷管理——问卷录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglWjlr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		User user = getUser(request);
		HashMap<String, String> fdyInfo = service.getFdyInfo(myForm);
		
		
		BjlhFdykhForm myFormKhb = new BjlhFdykhForm();
		BjlhFdykhService serviceKhb = new BjlhFdykhService();
		String yhm = user.getUserName();
		
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象辅导员
			lx="辅导员";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//评分对象班主任
			lx="班主任";
		}
		
		List<HashMap<String,String>> fdylist = serviceKhb.getYhmxx(yhm,lx);
		String dqsjKhb = serviceKhb.getDate();
		
		//判断必须由辅导员进入该界面
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "非学生用户无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(fdylist.size()== 0 ){
				request.setAttribute("yhInfo", "您没有对应的辅导员，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);				
			} else if(fdylist.size()== 1){
				return new ActionForward("/bjlh_fdycpwj.do?method=cpwjglWjlrSingle&fdyid="+fdylist.get(0).get("zgh"), false);
			} else{
				if (QUERY.equals(myForm.getType())){
					myForm.setWjid(mrsz.get("wjid"));
					myForm.setXh(yhm);
					if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
						myForm.setKhbid(mrsz.get("khbid"));
					}
					List<HashMap<String,String>> cpFdyList=service.getCpFdyList(myForm);
					JSONArray dataList = JSONArray.fromObject(cpFdyList);
					response.getWriter().print(dataList);
					return null;
				}
				String path = "bjlh_fdykh_cpwjlr.do";
				request.setAttribute("path", path);
				FormModleCommon.commonRequestSet(request);
				
				return mapping.findForward("cpFdyList");
			}
		}
		
//		if(mrsz == null ||mrsz.isEmpty()){
//			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
//			return new ActionForward("/yhInfo.do", false);
//		}else{
//			
//			if(mrsz.get("wjid") == null || "".equalsIgnoreCase(mrsz.get("wjid"))){
//				request.setAttribute("yhInfo", "暂无问卷需填写，无法进行该操作！");
//				return new ActionForward("/yhInfo.do", false);
//			}
//			
//			//判断 当前学年是否启用 的自评表
//			if("0".equalsIgnoreCase(mrsz.get("khsfqd"))
//					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj")) 
//					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))){
//				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
//				return new ActionForward("/yhInfo.do", false);
//			}
//		}
//		
//		myForm.setXn(mrsz.get("xn"));		
//		myForm.setWjid(mrsz.get("wjid"));
//		String doType=request.getParameter("doType");
//		if("save".equals(doType)){
//			String msg=service.saveCpwjDa(request, myForm)?"保存成功！":"保存失败！";
//			request.setAttribute("back", msg);
//		}
//		
//		request.setAttribute("rs", mrsz);//测评问卷信息
//		request.setAttribute("fdyInfo", fdyInfo);//辅导员信息
//		request.setAttribute("xn", Base.currXn);
//		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
//		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//转换试题选项为字符串
//		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjlr.do");
//		request.setAttribute("xswjstsfzd", service.getXsWjstSfzd(myForm));
//		
//		//////////////////////////////////#################################################################
//		
//		
//		//南通高等师范学校不需要显示辅导员测评表
//		if(!Globals.XXDM_NTGDSF.equalsIgnoreCase(Base.xxdm)){
//		//判断必须由辅导员进入该界面
//		
//		if (mrszKhb == null || mrszKhb.isEmpty()) {
//			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
//			return new ActionForward("/yhInfo.do", false);
//		} else {
//			if(mrszKhb.get("khbid") == null || "".equalsIgnoreCase(mrszKhb.get("khbid"))){
//				request.setAttribute("yhInfo", "暂无测评表需填写，无法进行该操作！");
//				return new ActionForward("/yhInfo.do", false);
//			}
//			
//			// 判断 当前学年是否启用 的自评表
//			if ("0".equalsIgnoreCase(mrszKhb.get("khsfqd"))
//					|| Integer.valueOf(dqsjKhb) < Integer.valueOf(mrszKhb
//							.get("khkssj"))
//					|| Integer.valueOf(dqsjKhb) > Integer.valueOf(mrszKhb
//							.get("khjssj"))) {
//				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
//				return new ActionForward("/yhInfo.do", false);
//			}
//		}
//
////		String doType = request.getParameter("doType");
//		myFormKhb.setKhbid(mrszKhb.get("khbid"));
//		myFormKhb.setYhm(yhm);
//		myFormKhb.setFdyid(fdylist.get(0).get("zgh"));
//		HashMap<String, String> rs = serviceKhb.getKhbxx(myFormKhb);
//		
//		if (doType != null && "save".equalsIgnoreCase(doType)) {
//			String[] xmid = request.getParameterValues("xmid");
//			String[] df = request.getParameterValues("df");
//			if (serviceKhb.saveFdykhpfb(xmid, df, myFormKhb)) {
//				request.setAttribute("message", "保存成功");
//			} else {
//				request.setAttribute("message", "保存失败");
//			}
//		}
//		// write和title
////		setWriteAbleAndTitle(request, "bjlh_fdykh_xskhcp.do");
//		request.setAttribute("rsKhb", rs);// 考核表信息
//		}
//		request.setAttribute("rs1", fdylist.get(0));// 考核表信息
//		request.setAttribute("xmList", serviceKhb.getKhbXmYjzbColNum(myFormKhb));// 获取经过处理一级指标的考核表项目列表
//		//池州职业技术学院个性化
//		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
//			request.setAttribute("khbsfzd", serviceKhb.getSfcgkhsj());//池州学院-判断是否超过考核时间
//		}else{
//			request.setAttribute("khbsfzd", serviceKhb.getKhbSfzd(myFormKhb));
//		}
		//return mapping.findForward("wjlr");
	}
	
	/**
	 * @描述:测评问卷管理——问卷录入(单个辅导员时)
	 * @作者：cmj
	 * @日期：2013-12-13 下午03:24:26
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
	public ActionForward cpwjglWjlrSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		BjlhFdykhService serviceKhb = new BjlhFdykhService();
		User user = getUser(request);
		String yhm = user.getUserName();
		String zgh = (String) request.getParameter("fdyid");
		request.setAttribute("fdyid", zgh);
		String fh = (String) request.getParameter("flag");//判断是否有返回按钮
		request.setAttribute("fh", fh);
		HashMap<String,String> fdyxx = service.getRsInfo("view_fdyxx", "zgh", zgh, new String[]{"xm","bmmc"});
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		if(mrsz == null ||mrsz.isEmpty()){
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}else{
			
			if(mrsz.get("wjid") == null || "".equalsIgnoreCase(mrsz.get("wjid"))){
				request.setAttribute("yhInfo", "暂无问卷需填写，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			
			//判断 当前学年是否启用 的自评表
			if("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj")) 
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))){
				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
		}
		
		myForm.setXn(mrsz.get("xn"));		
		myForm.setWjid(mrsz.get("wjid"));
		String doType=request.getParameter("doType");
		myForm.setXh(yhm);
		
		//北京联合大学个性化
		BjlhFdykhForm myFormKhb = new BjlhFdykhForm();
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			HashMap<String, String> mrszKhb = serviceKhb.getMrsz("xs");
			myFormKhb.setKhbid(mrszKhb.get("khbid"));
			myFormKhb.setYhm(yhm);
			myFormKhb.setFdyid(zgh);
			HashMap<String, String> rs = serviceKhb.getKhbxx(myFormKhb);
			request.setAttribute("rsKhb", rs);// 考核表信息
			
		}
		if("save".equals(doType)){
			
			//北京联合大学个性化
			if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
				if (doType != null && "save".equalsIgnoreCase(doType) ) {
					String[] xmid = request.getParameterValues("xmid");
					String[] df = request.getParameterValues("df");
					boolean flag= true;
					
					if(!serviceKhb.checkFdykhpf(xmid, df)){
						flag= false;
						request.setAttribute("back", "输入分值不符合要求");
					}
					
					if(flag){
						String msg=service.saveCpwjDa(request, myForm)?"保存成功！":"保存失败！";
						request.setAttribute("back", msg);
						if(msg.equalsIgnoreCase("保存成功！")){
							flag=true;
						}
						
						if(flag){
							if (serviceKhb.saveFdykhpfb(xmid, df, myFormKhb)) {
								request.setAttribute("back", "保存成功");
							} else {
								request.setAttribute("back", "保存失败");
							}
						}
					}
				}
			}else{
				//通用
				String msg=service.saveCpwjDa(request, myForm)?"保存成功！":"保存失败！";
				request.setAttribute("back", msg);
			}
		}
		
		request.setAttribute("rs", mrsz);//测评问卷信息
		request.setAttribute("fdyInfo", fdyxx);//辅导员信息
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//转换试题选项为字符串
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjlr.do");
		request.setAttribute("xswjstsfzd", service.getXsWjstSfzd(myForm));
		
		request.setAttribute("rs1", fdyxx);// 考核表信息
		request.setAttribute("xmList", serviceKhb.getKhbXmYjzbColNum(myFormKhb));// 获取经过处理一级指标的考核表项目列表
		//池州职业技术学院个性化
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
			request.setAttribute("khbsfzd", serviceKhb.getSfcgkhsj());//池州学院-判断是否超过考核时间
		}else if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("khbsfzd", service.getCpwjSfzd(myForm)&&serviceKhb.getKhbSfzd(myFormKhb));
		}
		else{
			request.setAttribute("khbsfzd", service.getCpwjSfzd(myForm));
		}
		return mapping.findForward("cpwjglWjlrSingle");
	}
	
	
	/**
	 * 测评问卷管理——问卷录入_修改前的 备份
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglWjlr_back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		User user = getUser(request);
		HashMap<String, String> fdyInfo = service.getFdyInfo(myForm);
		//判断必须由辅导员进入该界面
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "非学生用户无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(fdyInfo.get("zgh")== null ){
				request.setAttribute("yhInfo", "您没有对应的辅导员，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);				
			}
		}
		
		if(mrsz == null ||mrsz.isEmpty()){
			request.setAttribute("yhInfo", "当前学年考核参数未设置，无法进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}else{

			if(mrsz.get("wjid") == null || "".equalsIgnoreCase(mrsz.get("wjid"))){
				request.setAttribute("yhInfo", "暂无问卷需填写，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
			
			//判断 当前学年是否启用 的自评表
			if("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj")) 
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))){
				request.setAttribute("yhInfo", "非辅导员考核时间段，无法进行该操作！");
				return new ActionForward("/yhInfo.do", false);
			}
		}
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){
			String msg=service.saveCpwjDa(request, myForm)?"保存成功！":"保存失败！";
			request.setAttribute("back", msg);
		}

		myForm.setXn(mrsz.get("xn"));		
		myForm.setWjid(mrsz.get("wjid"));
		request.setAttribute("rs", mrsz);//测评问卷信息
		request.setAttribute("fdyInfo", fdyInfo);//辅导员信息
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//转换试题选项为字符串
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjlr.do");
		request.setAttribute("xswjstsfzd", service.getXsWjstSfzd(myForm));
		return mapping.findForward("wjlr");
	}
	
	/**
	 * 测评问卷管理--查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		//判断必须由学院或学校或辅导员用户进入该界面
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "班主任及学生用户无权进行该操作！");
			return new ActionForward("/yhInfo.do", false);
		}
		
		// 操作
		String doType = request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		
		HashMap<String, String> mrsz = service.getMrsz();
		
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_fdyzpcx.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
		}
		if("del".equalsIgnoreCase(doType)){
			String message = service.deleteCpwjInfo(myForm); 
			request.setAttribute("message", message);
		}
		
		List<String[]> rs = service.getCpwjTjQueryList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjcx.do");
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("cpwjtj"));
		
		return mapping.findForward("query");
	}
	
	/**
	 * 测评问卷管理——统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
//		myForm.setFdyid("null");
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		
		
		request.setAttribute("rs", service.getCpwjOne(myForm));//测评问卷信息
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxTjxxHtml(myForm,stxxList));//转换试题选项为字符串
		
		
		return mapping.findForward("tj");
	}
	
	
}
