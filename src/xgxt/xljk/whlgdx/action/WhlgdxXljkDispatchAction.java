package xgxt.xljk.whlgdx.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import xgxt.xljk.whlgdx.form.Xljk_XskhdxwhForm;
import xgxt.xljk.whlgdx.service.WhlgdxXljkDispatchService;

public class WhlgdxXljkDispatchAction extends DispatchAction {
	// service object singleton
	private WhlgdxXljkDispatchService service = WhlgdxXljkDispatchService
			.getInstance();

	/** 心理咨询中心教育活动单个处理 */
	public ActionForward xlzxjyhdper(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		String tableName = "view_xljk_whgldx_xlzxzxjyhd";
		String realTable = "xljk_whgldx_xlzxzxjyhdb";
		String pk = "xn_id";
		this.doTypeRequest(myForm, request, service, realTable, tableName, pk);
		request.setAttribute("hdxsList", new lrh_commen_util()
				.get_dmwhb_dmList("xlxhhd_hdxs")); // 取得心理健康的教育形式
		return mapping.findForward("xlzxjyhd_queryOne");
	}

	/** 心理咨询中心教育活动维护 */
	public ActionForward xlzxjyhdwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		String tableName = "view_xljk_whgldx_xlzxzxjyhd";
		String realTable = "xljk_whgldx_xlzxzxjyhdb";
		String pkColumn = "xn_id"; // 主键
		myForm.do_Xlzxzxjyhd_GBK(); // Do_GBK
		String[] condiColumns = new String[] { "xn", "hdrq", "zcr" };// 查询条件
		String[] outputValues = new String[] { "xn_id", "zcr", "zt", "dd",
				"jyxsmc", "kssj", "jssj", "hdrq" }; // 主键放在第一位
		List<String[]> rs = this.doTypeRequest_WH(myForm, pkColumn, realTable,
				tableName, request, service, condiColumns, outputValues);
		FormUtils.setWritAble(request);
		FormUtils.setReqAttr(request, realTable, tableName, rs, myForm);
		request.setAttribute("hdrqList", service.getSpecialDisList(tableName,
				new String[] { "hdrq" }));
		request.setAttribute("zcrList", service.getSpecialDisList(tableName,
				new String[] { "zcr" }));
		return mapping.findForward("data_xlzxjyhd");
	}

	/** 辅导员工作记录单个处理 只针对辅导员开放 */
	public ActionForward fdygzjlpre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		String tableName = "view_xljk_fdygzjl";
		String realTable = "xljk_fdygzjlb";
		String pk = "zgh||tjsj";
		String doType = myForm.getDoType();
		myForm.setZgh(request.getSession().getAttribute("userName").toString());
		FormUtils.setXyUserWritAbleDisabled(myForm, request);
		if ("fdy".equals(request.getAttribute("userType").toString())
				|| "yes".equals(myForm.getCheckfdy())) {
			tableName = realTable; // 不用视图，直接用表
		}
		try {
			if ("add".equals(doType)) {
				request.setAttribute("ok", myForm.setResult(service
						.saveFdyInfo(myForm, realTable, pk)));
			} else if ("view".equals(doType) || "modi".equals(doType)) {
				service.getCommonAllInfo(myForm, tableName, pk);
				// 非辅导员查看辅导员信息是加一段附加信息
				if (!"fdy".equals(request.getAttribute("userType").toString())) {
					this.dealFdyGzjlInfo(myForm);
				}
			}
			request.setAttribute("rs", myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("fdygzjl_queryOne");
	}

	/** 辅导员工作记录维护 */
	public ActionForward fdygzjlwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		String tableName = "view_xljk_fdygzjl";
		String realTable = "xljk_fdygzjlb";
		String pkColumn = "zgh";
		myForm.do_fdygzjl_GBK();
		String[] condiColumns = new String[] { "xn", "xydm", "zgh" };// 查询条件
		String[] outputValues = new String[] { "zgh", "xm", "xb", "xymc",
				"jlzs" };
		FormUtils.setXyUserWritAbleDisabled(myForm, request);
		if ("fdy".equals(request.getAttribute("userType").toString())
				// 当为yes时，表示是学院或学校用户查看辅导员的工作记录信息
				|| "yes".equals(myForm.getCheckfdy())) {
			pkColumn = "zgh||tjsj";
			condiColumns = new String[] { "xn", "zgh" };// 查询条件
			outputValues = new String[] { pkColumn, "xn", "zt", "tjsj" };
			tableName = realTable; // 不用视图，直接用表
			myForm.setZgh(request.getSession().getAttribute("userName")
					.toString()); // 取得职工号，即用户名
			if ("yes".equals(myForm.getCheckfdy())) {
				myForm.setZgh(myForm.getPkVal());
				request.setAttribute("seeFdyInfo", "true");
			}
		}
		List<String[]> rs = new ArrayList<String[]>();
		String doType = myForm.getDoType();
		try {
			if (StringUtils.isNotNull(doType)) {
				if ("del".equals(doType)) {
					if ("true".equals(request.getParameter("seeFdyInfo"))) {
						pkColumn = "zgh||tjsj";
					}
					String pk = request.getParameter("cbVal");
					request.setAttribute("ok", myForm.setResult(service
							.batchDelRecord(pk, realTable, pkColumn)));// 提示信息
				}
				rs = service.getCommonVector(myForm, tableName, condiColumns,
						outputValues);
				request.setAttribute("topTr", myForm.getColumnCN()); // 返回表头信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FormUtils.setReqAttr(request, realTable, tableName, rs, myForm);
		if ("xy".equals(request.getAttribute("userType"))
				|| StringUtils.isNotNull(myForm.getXydm())) {
			String xydm = StringUtils.isNotNull(myForm.getXydm()) ? myForm
					.getXydm() : request.getSession().getAttribute("userDep")
					.toString();
			request.setAttribute("fdyList", service.getSpecialDisList("fdyxxb",
					new String[] { "bmdm" }, new String[] { xydm },
					new String[] { "zgh", "xm" }));
		} else {
			request.setAttribute("fdyList", service.getSpecialDisList("fdyxxb",
					new String[] { "zgh", "xm" }));
		}
		return mapping.findForward("data_fdygzjl");
	}

	/**
	 * *******************************************private
	 * method***********************************
	 */

	/** 数据导出 */
	public ActionForward common_exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		service.getCommonExp(myForm, response, request, myForm.getTableName(),
				userType);
		return mapping.findForward("common_exp");
	}

	/** 维护数据 */
	private List<String[]> doTypeRequest_WH(Xljk_XskhdxwhForm myForm,
			String pkColumn, String realTable, String tableName,
			HttpServletRequest request, WhlgdxXljkDispatchService service,
			String[] condiColumns, String[] outputValues) {
		List<String[]> rs = new ArrayList<String[]>();
		String doType = myForm.getDoType();
		try {
			if (StringUtils.isNotNull(doType)) {
				if ("del".equals(doType)) {
					String pk = request.getParameter("cbVal");
					request.setAttribute("ok", myForm.setResult(service
							.batchDelRecord(pk, realTable, pkColumn)));// 提示信息
				}
				rs = service.getCommonVector(myForm, tableName, condiColumns,
						outputValues);
				request.setAttribute("topTr", myForm.getColumnCN()); // 返回表头信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/** ###################################################################################################### */

	/** 单个数据 */
	private void doTypeRequest(Xljk_XskhdxwhForm myForm,
			HttpServletRequest request, WhlgdxXljkDispatchService service,
			String realTable, String tableName, String pk) {
		// HttpSession session = request.getSession();
		// String userOnLine = session.getAttribute("userOnLine").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = myForm.getDoType();
		
		try {
			request.setCharacterEncoding("gbk");
			if ("add".equals(doType)) {
				request.setAttribute("ok", myForm.setResult(service
						.saveCommonInfo(myForm, realTable, pk)));
			} else if ("view".equals(doType) || "modi".equals(doType)) {
				service.getCommonAllInfo(myForm, tableName, pk);
			}
			request.setAttribute("rs", myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 学院或学校用户查看辅导员信息的时候加上一段信息
	 */
	private void dealFdyGzjlInfo(Xljk_XskhdxwhForm myForm) {
		StringBuffer sb1 = new StringBuffer(myForm.getNr()).append(
				"<br><br>--------------------------------------------<br>")
				.append("<code><font color='blue'>最后提交时间为  <b>").append(
						myForm.getTjsj()).append("<b>  </font></code>");
		myForm.setNr(sb1.toString());

	}
	
}
