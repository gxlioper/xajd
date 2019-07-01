package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyStuModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目审核-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyXmshAction extends BasicAction {

	/**
	 * 评奖评优_评奖流程_项目审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmshForm myForm = (PjpyXmshForm) form;
		PjpyXmshService service = new PjpyXmshService();
		PjpyXmshInit init = new PjpyXmshInit();
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		String xmshdm=request.getParameter("xmshdm");
		// 结果集显示字段
		String editPageSize = request.getParameter("editPageSize");
		if (!"yes".equalsIgnoreCase(editPageSize)) {
			myForm.getPages().setPageSize(16);
			request.setAttribute("autoPageSize", "16");
		}

		//访问方式
		String fwfs=request.getParameter("fwfs");
		String gwxx=request.getParameter("gwxx");
		String jbxx=request.getParameter("jbxx");
		if(!Base.isNull(jbxx)){
			myForm.setShjb(jbxx);
			myForm.setSpgw(gwxx);
		}
		// 审核项目
		String shxm =!Base.isNull(xmshdm)? xmshdm :myForm.getShxm();
		myForm.setShxm(shxm);
		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		// 项目设置model初始化
		String pkValue = pjxn + pjxq + pjnd + shxm;
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pkValue);
		
		myForm.setJbszModel(jbszModel);
		myForm.setXmszModel(xmszModel);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmshRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;

		// ================= end =====================

		// =============== 获得项目列表 ===============
		String xmdm = request.getParameter("xmdm");// 项目代码
		String xmmc = request.getParameter("xmmc");// 项目名称
		String ywmc = request.getParameter("ywmc");// 英文名称
		String xmxz = request.getParameter("xmxz");// 项目性质
		String xmfw = request.getParameter("xmfw");// 项目范围
		String xmlx = request.getParameter("xmlx");// 项目类型
		
		myForm.setXmdm(xmdm);
		myForm.setXmmc(xmmc);
		myForm.setYwmc(ywmc);
		myForm.setXmxz(xmxz);
		myForm.setXmfw(xmfw);
		myForm.setXmlx(xmlx);
		List<HashMap<String, String>> xhxmList = service.getXhxmList(myForm,
				user);
		request.setAttribute("xhxmList", xhxmList);
		request.setAttribute("hid_xmdm", xmdm);
		request.setAttribute("hid_xmmc", xmmc);
		request.setAttribute("hid_ywmc", ywmc);
		request.setAttribute("hid_xmxz", xmxz);
		request.setAttribute("hid_xmfw", xmfw);
		request.setAttribute("hid_xmlx", xmlx);
		// ================= end =====================
		  
		// =============== 执行审核操作 ===========
		if ("sh".equalsIgnoreCase(doType)) {

			// 审核状态
			String shzt = myForm.getShzt();
			// 审核级别
			String shjb = myForm.getShjb();
			// 最终审核级别
			String zzShjb = service.getXmzzshjb(myForm);
			// 提示信息
			String message = "";
			
			// 审核为最终级
			if (shjb.equalsIgnoreCase(zzShjb) && "通过".equalsIgnoreCase(shzt)) {
				message = service.getXmxzInfo(myForm);
			}
			
			// 人数未超过上限
			if (Base.isNull(message)) {
				boolean flag = service.saveXmshzt(myForm, user);

				if (flag && "退回".equalsIgnoreCase(shzt)) {
					// 退回操作需要操作上级审核状态
					flag = service.saveXmcszt(myForm, user);
				} else if (flag && "通过".equalsIgnoreCase(shzt)
						&& !shjb.equalsIgnoreCase(zzShjb)) {
					// 重审核操作需要操作下级审核状态
					flag = service.saveXmycszt(myForm, user);
				}
				message = flag ? MessageInfo.MESSAGE_SH_SUCCESS
						: MessageInfo.MESSAGE_SH_FALSE;
			}
			
			rForm.setMessage(message);

			// 审核完毕执行查询
			search = true;
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========
		if (search) {
			// 结果集
			rsArrList = service.getXmshXsList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		request.setAttribute("fwfs", fwfs);
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xmshManage");
	}

	/**
	 * 评奖评优_评奖流程_项目审核(详细)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmshForm myForm = (PjpyXmshForm) form;
		PjpyXmshService service = new PjpyXmshService();
		PjpyXmshInit init = new PjpyXmshInit();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============

		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 主键
		String pkValue = request.getParameter("pk");
		myForm.setPkValue(pkValue);
		// 审核级别
		String shjb = request.getParameter("shjb");
		myForm.setShjb(shjb);
		// 审核项目
		String shxm = request.getParameter("shxm");
		myForm.setShxm(shxm);
		myForm.setXmdm(shxm);
		
		// 项目设置model初始化
		String pk = pjxn + pjxq + pjnd + shxm;
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pk);
		myForm.setXmszModel(xmszModel);
		myForm.setJbszModel(PjxtszModel.pjxtszModel);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmshRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 项目名称
		String xmmc = xmszModel.getXmmc();
		myForm.setXmmc(xmmc);
		// ================= end =====================

		// =============== 执行审核操作 ===========
		if ("sh".equalsIgnoreCase(doType)) {

			String[] PrimaryKey = new String[] { pkValue };
			myForm.setPrimarykey_checkVal(PrimaryKey);
			// 审核状态
			String shzt = myForm.getShzt();
			// 最终审核级别
			String zzShjb = service.getXmzzshjb(myForm);
			// 提示信息
			String message = "";

			// 审核为最终级
			if (shjb.equalsIgnoreCase(zzShjb) && "通过".equalsIgnoreCase(shzt)) {
				message = service.getXmxzInfo(myForm);
			}

			// 人数未超过上限
			if (Base.isNull(message)) {
				boolean flag = service.saveXmshzt(myForm, user);

				if (flag && "退回".equalsIgnoreCase(shzt)) {
					// 退回操作需要操作上级审核状态
					flag = service.saveXmcszt(myForm, user);
				} else if (flag && "通过".equalsIgnoreCase(shzt)
						&& !shjb.equalsIgnoreCase(zzShjb)) {
					// 重审核操作需要操作下级审核状态
					flag = service.saveXmycszt(myForm, user);
				}
				message = flag ? MessageInfo.MESSAGE_SH_SUCCESS
						: MessageInfo.MESSAGE_SH_FALSE;

			} else {
				//顺延项目
				List<HashMap<String, String>> syxmList = service
						.getSyxmList(myForm);
				
				if (syxmList != null && syxmList.size() > 0) {
					request.setAttribute("xmsy", "yes");
					request.setAttribute("syxmList", syxmList);
				}
			}
			
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== 执行审核操作 ===========
		if ("xmsy".equalsIgnoreCase(doType)) {
			// 可否顺延
			boolean kfsy = service.getKfsy(myForm);
			String message = "";

			if (kfsy) {
				boolean flag = service.saveXmsy(myForm, user);
				message = flag ? MessageInfo.MESSAGE_SH_SUCCESS
						: MessageInfo.MESSAGE_SH_FALSE;
			} else {
				message = "该项目已经申请，请重新选择顺延项目!";
			}
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =================== 获得审核详细情况 ===========
		
		List<HashMap<String, String>> rsList = service.getXmshInfoList(myForm);
		request.setAttribute("rsList", rsList);
		System.out.println("项目审核相关信息记录数:"+rsList.size());       
		
		String xh = "";
		if (rsList != null && rsList.size() > 0) {
			xh = rsList.get(0).get("xh");
			request.setAttribute("rs", rsList.get(0));
			System.out.println("Action操作的学生.....："+xh);
		}
		
		PjpyXmsqService xmsqService = new PjpyXmsqService();
		PjpyStuModel stuModel = xmsqService.getStuForPj(xh, xmszModel);
		
		request.setAttribute("xmxx", xmszModel);
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);		 // 评奖项目信息
		request.setAttribute("stuJbxx", stuModel.getJbxx());		 // 学生基本信息
		request.setAttribute("stuCjxx", stuModel.getCjxx());		 // 学生成绩信息
		request.setAttribute("stuDjks", stuModel.getDjks());		 // 等级考试
		request.setAttribute("stuZcxx", stuModel.getZcxx());         // 综测成绩信息
		request.setAttribute("shlc", xmsqService.formatShlc(xmszModel.getShlc()));
		request.setAttribute("sqxmInfo", xmsqService.getSqxmInfo(xh, xmszModel));
		// ================= end =====================
		
		// =================== 初始化列表值 ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xmshDetail");
	}
}
