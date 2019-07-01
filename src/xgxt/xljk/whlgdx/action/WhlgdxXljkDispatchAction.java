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

	/** ������ѯ���Ľ������������ */
	public ActionForward xlzxjyhdper(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		String tableName = "view_xljk_whgldx_xlzxzxjyhd";
		String realTable = "xljk_whgldx_xlzxzxjyhdb";
		String pk = "xn_id";
		this.doTypeRequest(myForm, request, service, realTable, tableName, pk);
		request.setAttribute("hdxsList", new lrh_commen_util()
				.get_dmwhb_dmList("xlxhhd_hdxs")); // ȡ���������Ľ�����ʽ
		return mapping.findForward("xlzxjyhd_queryOne");
	}

	/** ������ѯ���Ľ����ά�� */
	public ActionForward xlzxjyhdwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		String tableName = "view_xljk_whgldx_xlzxzxjyhd";
		String realTable = "xljk_whgldx_xlzxzxjyhdb";
		String pkColumn = "xn_id"; // ����
		myForm.do_Xlzxzxjyhd_GBK(); // Do_GBK
		String[] condiColumns = new String[] { "xn", "hdrq", "zcr" };// ��ѯ����
		String[] outputValues = new String[] { "xn_id", "zcr", "zt", "dd",
				"jyxsmc", "kssj", "jssj", "hdrq" }; // �������ڵ�һλ
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

	/** ����Ա������¼�������� ֻ��Ը���Ա���� */
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
			tableName = realTable; // ������ͼ��ֱ���ñ�
		}
		try {
			if ("add".equals(doType)) {
				request.setAttribute("ok", myForm.setResult(service
						.saveFdyInfo(myForm, realTable, pk)));
			} else if ("view".equals(doType) || "modi".equals(doType)) {
				service.getCommonAllInfo(myForm, tableName, pk);
				// �Ǹ���Ա�鿴����Ա��Ϣ�Ǽ�һ�θ�����Ϣ
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

	/** ����Ա������¼ά�� */
	public ActionForward fdygzjlwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		String tableName = "view_xljk_fdygzjl";
		String realTable = "xljk_fdygzjlb";
		String pkColumn = "zgh";
		myForm.do_fdygzjl_GBK();
		String[] condiColumns = new String[] { "xn", "xydm", "zgh" };// ��ѯ����
		String[] outputValues = new String[] { "zgh", "xm", "xb", "xymc",
				"jlzs" };
		FormUtils.setXyUserWritAbleDisabled(myForm, request);
		if ("fdy".equals(request.getAttribute("userType").toString())
				// ��Ϊyesʱ����ʾ��ѧԺ��ѧУ�û��鿴����Ա�Ĺ�����¼��Ϣ
				|| "yes".equals(myForm.getCheckfdy())) {
			pkColumn = "zgh||tjsj";
			condiColumns = new String[] { "xn", "zgh" };// ��ѯ����
			outputValues = new String[] { pkColumn, "xn", "zt", "tjsj" };
			tableName = realTable; // ������ͼ��ֱ���ñ�
			myForm.setZgh(request.getSession().getAttribute("userName")
					.toString()); // ȡ��ְ���ţ����û���
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
							.batchDelRecord(pk, realTable, pkColumn)));// ��ʾ��Ϣ
				}
				rs = service.getCommonVector(myForm, tableName, condiColumns,
						outputValues);
				request.setAttribute("topTr", myForm.getColumnCN()); // ���ر�ͷ��Ϣ
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

	/** ���ݵ��� */
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

	/** ά������ */
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
							.batchDelRecord(pk, realTable, pkColumn)));// ��ʾ��Ϣ
				}
				rs = service.getCommonVector(myForm, tableName, condiColumns,
						outputValues);
				request.setAttribute("topTr", myForm.getColumnCN()); // ���ر�ͷ��Ϣ
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/** ###################################################################################################### */

	/** �������� */
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
	 * ѧԺ��ѧУ�û��鿴����Ա��Ϣ��ʱ�����һ����Ϣ
	 */
	private void dealFdyGzjlInfo(Xljk_XskhdxwhForm myForm) {
		StringBuffer sb1 = new StringBuffer(myForm.getNr()).append(
				"<br><br>--------------------------------------------<br>")
				.append("<code><font color='blue'>����ύʱ��Ϊ  <b>").append(
						myForm.getTjsj()).append("<b>  </font></code>");
		myForm.setNr(sb1.toString());

	}
	
}
