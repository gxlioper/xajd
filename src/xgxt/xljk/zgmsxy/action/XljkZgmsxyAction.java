package xgxt.xljk.zgmsxy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.xljk.common.CommonForm;
import xgxt.xljk.common.XljkUtil;
import xgxt.xljk.zgmsxy.form.XljkZgmsxyForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.MD5Clipher;

public class XljkZgmsxyAction extends SuperAction {
//	private static final XljkZgmsxySerivce service = XljkZgmsxySerivce
//			.getInstance();

	private static final XljkUtil util = XljkUtil.getInstance();

	/** 心理健康教育中心 */
	public ActionForward xljkjyzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("xljkjyzx");
	}


	/** 微信审核跳转 */
	public ActionForward wxshtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		User user = getUser(request);
		String userName = user.getUserName();
		String timestamp = String.valueOf(System.currentTimeMillis());
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("gate", request.getParameter("gate"));
		request.setAttribute("cipher", MD5Clipher.encrypt(userName+timestamp+"ZFKEY"));
		return mapping.findForward("wxshtz");
	}
	
	/** 咨询师信息单个处理 */
	public ActionForward zxspre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FormUtils.CheckUserAccessPower(request); // Check
		XljkZgmsxyForm myForm = (XljkZgmsxyForm) form;
		String tableName = "xljk_zxsxxb2"; // 心理健康 咨询师信息表
		String realTable = "xljk_zxsxxb2";
		myForm.do_zxsxx_GBK();
		String pk = "bh"; // 咨询师编号
		String pkValue = myForm.getBh();
		doPre(myForm, request, realTable, tableName, pk, pkValue);
		FormUtils.setWritAble(request);
		return mapping.findForward("zxs_queryOne");
	}

	/** 咨询师信息维护 */
	public ActionForward zxswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FormUtils.CheckUserAccessPower(request);
		XljkZgmsxyForm myForm = (XljkZgmsxyForm) form;
		String tableName = "xljk_zxsxxb2";
		String realTable = "xljk_zxsxxb2";
		String pkColumn = "bh";
		myForm.do_zxsxx_GBK();
		List<String[]> rs = new ArrayList<String[]>();
		String[] columns = new String[] { "bh", "xm", "xb" };
		String[] outputValues = new String[] { "bh", "xm", "xb", "zg", "lxdh" };
		rs = doWh(myForm, rs, pkColumn, realTable, tableName, request, columns,
				outputValues);
		String writeAble = FormUtils.setWritAble(request);
		setAttr(request, writeAble, realTable, tableName, rs, myForm);
		return mapping.findForward("data_zxs");
	}

	/** 咨询师资源单个处理 */
	public ActionForward zxszypre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FormUtils.CheckUserAccessPower(request); // Check
		XljkZgmsxyForm myForm = (XljkZgmsxyForm) form;
		String realTable = "xljk_zxszyb"; // 心理健康 咨询师资源表
		String tableName = "view_zxszy";
		myForm.do_zxszy_GBK();
		String pk = "zxxbh||xq||sjd"; // 姓名、星期，时间段
		myForm.setXn_id(FormUtils.getXnId(realTable));
		myForm.setZxxbh(myForm.getBh()); // zxxbh//bh the same
		String pkValue = myForm.getBh() + myForm.getXq() + myForm.getSjd();
		String doType = myForm.getDoType();
		try {
			if ("add".equals(doType)) {
				request.setAttribute("ok", util.setResult(util.saveCommonInfo(
						myForm, realTable, pk, pkValue)));
			} else if (util.viewOrModi(doType)) {
				pk = "bh||xq||sjd";
				util.getCommonAllInfo(myForm.getPkVal(), tableName, pk, myForm);
			}
			request.setAttribute("rs", myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FormUtils.setWritAble(request);
		return mapping.findForward("zxszy_queryOne");
	}

	/** 咨询师资源维护 */
	public ActionForward zxszywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// FormUtils.CheckUserAccessPower(request);
		XljkZgmsxyForm myForm = (XljkZgmsxyForm) form;
		String realTable = "xljk_zxszyb"; // 心理健康 咨询师资源表
		String tableName = "view_zxszy";
		myForm.do_zxszy_GBK();
		String pkColumn = "zxxbh||xq||sjd";
		List<String[]> rs = new ArrayList<String[]>();
		String[] columns = new String[] { "bh" };
		String[] outputValues = new String[] { "bh||xq||sjd", "xm", "xq",
				"sjd", "is_yy", "dd" };
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String doType = myForm.getDoType();
		try {
			if ("del".equals(doType)) {
				String pk = request.getParameter("cbVal");
				request.setAttribute("ok", util.setResult(util.batchDelRecord(
						pk, realTable, pkColumn)));
			}
			rs = util.getCommonVector(myForm, tableName, userType, columns,
					outputValues);
			request.setAttribute("topTr", myForm.getColumnCN());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String writeAble = FormUtils.setWritAble(request);
		setAttr(request, writeAble, realTable, tableName, rs, myForm);
		return mapping.findForward("data_zxszy");
	}

	/** 单个数据 */
	private void doPre(XljkZgmsxyForm myForm, HttpServletRequest request,
			String realTable, String tableName, String pk, String pkValue) {
		String doType = myForm.getDoType();
		try {
			if ("add".equals(doType)) {
				request.setAttribute("ok", util.setResult(util.saveCommonInfo(
						myForm, realTable, pk, pkValue)));
			} else if (util.viewOrModi(doType)) {
				util.getCommonAllInfo(myForm.getPkVal(), tableName, pk, myForm);
			}
			request.setAttribute("rs", myForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setAttr(HttpServletRequest request, String writeAble,
			String realTable, String tableName, List<String[]> rs,
			CommonForm myForm) {
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", (rs == null) == true ? "0" : rs.size());
		request.setAttribute("form", myForm);
	}

	/** 维护数据 */
	private List<String[]> doWh(CommonForm myForm, List<String[]> rs,
			String pkColumn, String realTable, String tableName,
			HttpServletRequest request, String[] columns, String[] outputValues) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String doType = myForm.getDoType();
		try {
			if (StringUtils.isNotNull(doType)) {
				if ("del".equals(doType)) {
					String pk = request.getParameter("cbVal");
					request.setAttribute("ok", util.setResult(util
							.batchDelRecord(pk, realTable, pkColumn)));
				}
				rs = util.getCommonVector(myForm, tableName, userType, columns,
						outputValues);
				request.setAttribute("topTr", myForm.getColumnCN());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
