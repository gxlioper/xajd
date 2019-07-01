package xgxt.pjpy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import common.Globals;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优页面跳转-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class PjpyTyAction extends DispatchAction {

	/**
	 * 测评小组维护跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward cpxzForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Cpxz(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_cpxz.do";
		} else {
			forward = "/zjcm_pjpy_cpxzz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 条件设置跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward tjszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Tjsz(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_tjsz.do";
		} else {
			forward = "/zjcm_pjpy_tjsz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 奖学金兼得设置跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jdszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jdsz(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_jdsz.do";
		} else {
			forward = "/zjcm_pjpy_jdsz.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 智育分维护跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward zyfForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Zyf(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_zyf.do";
		}  else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){
			forward = "/pjpyMjxy.do?method=zyfManage";
		}	else {
			forward = "/zjcm_pjpy_zyf.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 综合素质分维护跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward zhfForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Zhf(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_zhf.do";
		} else {
			forward = "/zjcm_pjpy_zhf.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 奖学金申请(校内)跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjsqXnForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jxjsq(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_jxjsqxn.do";
		} else {
			forward = "/zjcm_pjpy_jxjsqxn.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 奖学金申请(校外)跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjsqXwForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jxjsq_xw(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_jxjsqxw.do";
		} else {
			forward = "/zjcm_pjpy_jxjsqxw.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 荣誉称号申请跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward rychsqForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Rychsq(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_rychsq.do";
		} else {
			forward = "/zjcm_pjpy_rychsq.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 奖学金申报结果跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward jxjjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Jxjjg(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_jxjjg.do";
		} else {
			forward = "/zjcm_pjpy_jxjjg.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 荣誉称号申报结果跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward rychjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Rychjg(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_rychjg.do";
		} else {
			forward = "/zjcm_pjpy_rychjg.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 报表统计跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward bbtjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (PjpyYw.IsZjcm_Bbtj(xxdm)) {// 浙江传媒
			forward = "/zjcm_pjpy_bbtj.do";
		} else {
			forward = "/zjcm_pjpy_tjbb.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 操行分维护跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// 浙江交通职业技术学院
			forward = "/zjjt_pjpy_cxfwh.do";
		} else {
			forward = "/zjjt_pjpy_cxfwh.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 操行分审核跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfshForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// 浙江交通职业技术学院
			forward = "/zjjt_pjpy_cxfsh.do";
		} else {
			forward = "/zjjt_pjpy_cxfsh.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 操行分结果跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// 浙江交通职业技术学院
			forward = "/zjjt_pjpy_cxfjg.do";
		} else {
			forward = "/zjjt_pjpy_cxfjg.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 操行分录入跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxflrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// 浙江交通职业技术学院
			forward = "/zjjt_pjpy_cxflr.do";
		} else {
			forward = "/zjjt_pjpy_cxflr.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 操行分查询跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxfcxForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// 浙江交通职业技术学院
			forward = "/zjjt_pjpy_cxfcx.do";
		} else {
			forward = "/zjjt_pjpy_cxfcx.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 操行分统计跳转
	 * 
	 * @return ActionForward
	 */
	public ActionForward cxftjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();
		String forward = "";

		if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// 浙江交通职业技术学院
			forward = "/zjjt_pjpy_cxftj.do";
		} else {
			forward = "/zjjt_pjpy_cxftj.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 参数设置 - 奖学金(荣誉称号) - 人数设置
	 * 
	 * @return ActionForward
	 */
	public ActionForward rsszForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {// 贵州大学
			// 学校（管理员）
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				forward = "/guizdx_pjpy_rssz_xx.do";
			}
			// 学院
			else if ("xy".equalsIgnoreCase(userType)) {
				forward = "/guizdx_pjpy_rssz_xy.do";
			}
			// 其他
			else {
				request.setAttribute("errMsg", "你没有访问权限，请确认！");
				return new ActionForward("/errMsg.do", false);
			}
		} else {
			forward = "/prise_conf_rs.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 问卷调查 - 问卷维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward wjwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// 判断是否问卷调查版本1
			forward = "/nbcs_pjpy_wjManage.do";
		} else {
			forward = "/nbcs_pjpy_wjManage.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 问卷调查 - 试题维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward stwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// 判断是否问卷调查版本1
			forward = "/nbcs_pjpy_stManage.do";
		} else {
			forward = "/nbcs_pjpy_stManage.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 问卷调查 - 组卷维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward zjwhForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// 判断是否问卷调查版本1
			forward = "/nbcs_pjpy_zjManage.do";
		} else {
			forward = "/nbcs_pjpy_zjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 问卷调查 - 回答问卷
	 * 
	 * @return ActionForward
	 */
	public ActionForward hdwjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// 判断是否问卷调查版本1
			forward = "/nbcs_pjpy_hdwjManage.do";
		} else {
			forward = "/nbcs_pjpy_hdwjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 问卷调查 - 回答统计
	 * 
	 * @return ActionForward
	 */
	public ActionForward hdtjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// 判断是否问卷调查版本1
			forward = "/nbcs_pjpy_hdtjManage.do";
		} else {
			forward = "/nbcs_pjpy_hdtjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 问卷调查 - 回答批卷
	 * 
	 * @return ActionForward
	 */
	public ActionForward hdpjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// 判断是否问卷调查版本1
			forward = "/nbcs_pjpy_hdpjManage.do";
		} else {
			forward = "/nbcs_pjpy_hdpjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 问卷调查 - 问卷分配
	 * 
	 * @return ActionForward
	 */
	public ActionForward wjfpForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (PjpyYw.isWjdcFirst(xxdm)) {// 判断是否问卷调查版本1
			forward = "/nbcs_pjpy_wjfpManage.do";
		} else {
			forward = "/nbcs_pjpy_wjfpManage.do";
		}
		return new ActionForward(forward, false);
	}

	/**
	 * 学生品行评价 - 答卷人管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward djrForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// 宁波城市职业技术学院
			forward = "/nbcs_pjpy_pxpj_djrManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_djrManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生品行评价 - 问卷分配
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxwjfpForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// 宁波城市职业技术学院
			forward = "/nbcs_pjpy_pxpj_wjfpManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_wjfpManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生品行评价 - 品行评价
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxpjForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// 宁波城市职业技术学院
			forward = "/nbcs_pjpy_pxpj_pxpjManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_pxpjManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 学生品行评价 - 评价结果
	 * 
	 * @return ActionForward
	 */
	public ActionForward pjjgForward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String forward = "";

		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {// 宁波城市职业技术学院
			forward = "/nbcs_pjpy_pxpj_pjjgManage.do";
		} else {
			forward = "/nbcs_pjpy_pxpj_pjjgManage.do";
		}
		return new ActionForward(forward, false);
	}
	
	/**
	 * 发展性素质 - 发展性素质分录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		
		String xxdm = StandardOperation.getXxdm();
		
		if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){
			forward = "/hndx_fzxsz.do?method=fzxszflr";
		}else{
			forward = "/hndx_fzxsz.do?method=fzxszflr";
		}
		
		return new ActionForward(forward);
	}
	
	/**
	 * 发展性素质 - 发展性素质分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fzxszfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		
		String xxdm = StandardOperation.getXxdm();
		
		if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){
			forward = "/hndx_fzxsz.do?method=fzxszfcx";
		}else{
			forward = "/hndx_fzxsz.do?method=fzxszfcx";
		}
		
		return new ActionForward(forward);
	}
	
	public ActionForward zhszfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		
		String xxdm = StandardOperation.getXxdm();
		
		if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){
			forward = "/hndx_fzxsz.do?method=zhszfcx";
		}else{
			forward = "/hndx_fzxsz.do?method=zhszfcx";
		}
		
		return new ActionForward(forward);
	}
}
