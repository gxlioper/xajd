/*
 * @Title: ѧ������������Ϣϵͳ
 * 
 * @ClassName: PjpyCzxxJxjAction.java
 * 
 * @time: 2010-5-14 
 * 
 * @copyright: hz-zfsoft 
 */
package xgxt.pjpy.czxx.jxj;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.pjpy.czxx.PjpyCzxxActionForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;


/**
 * ������Ϣ - �������� - ��ѧ����Ϣά��ACTION����������ɾ���ģ��飬��˵Ȳ���
 * 
 * @author lt
 * @version 1.0 2010-5-14
 */
public class PjpyCzxxJxjAction extends CommonAction {
	
	private static final String DATA_TJ = "tj"; //�����ύ 
	private static final String DATA_FH = "fh";

	/**
	 * ��ѧ����������ѯ��Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
//		List<String[]> rs = new ArrayList<String[]>();
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
//		JxjService service = new JxjService();
//		
		/* ѧԺ�ҷǸ���Ա����ѧԺ���� */
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString())
				&& !"true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		/* ��ѯ��� */
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			JxjModel model = new JxjModel();
			BeanUtils.copyProperties(model, czxxForm);
//			topTr = service.queryJxjTitle();
//			rs = service.queryJxjReuslt(request.getSession().getAttribute(
//					"userType").toString(), request.getSession().getAttribute(
//					"isFdy").toString(), request.getSession().getAttribute(
//					"userName").toString(), model);

			String[] outputColumn = { "pkValue", "dis", "�к�", "xn", "xqmc",
					"xh", "xm", "bjmc", "jxjmc", "jlje", "zfpm", "fdysh",
					"xysh", "xxsh" };
			request.setAttribute("clientColumns", getDisSql(request, "qry"));
			this.selectPageDataByPagination(request, 
											czxxForm, 
					                        "xsjxjb", 
					                        "view_czxx_xsjxjb", outputColumn);
		} else if (DELETE.equalsIgnoreCase(czxxForm.getAct())) {//ɾ�����ݲ���
			this.deleteOperation(request, "xsjxjb");
		}
		
		appendOperQx(request, "prise_result.do");
		appendJxjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
//		appendQryResult(request, topTr, rs);
		appendTableXx(request, "view_czxx_xsjxjb", "xsjxjb");
		
		return mapping.findForward("query");
	}
	
	/**
	 * ��ѧ��������Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		JxjService service = new JxjService();
		String xh = request.getParameter("save_xh");
		xh = UserTypePd.isStu(request.getSession().getAttribute("userType")
				.toString()) ? request.getSession().getAttribute("userName")
				.toString() : xh;
		
		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {			
			this.insertOperation(request, "xsjxjb");	
			
		} 
		
		if ("add".equalsIgnoreCase(request.getParameter("typ"))) {
			request.setAttribute("typ", "add");
		}
		
		if (StringUtils.isNotNull(xh)) {
			rs = CommonQueryDAO.getStuInfo(xh);
			rs.put("save_xh", rs.get("xh"));
		}
		
		/* ����ѧ�����֣��ɼ���Ϣ */
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("xh", xh);
		paramMap.put("xn", Base.getJxjsqxn());
		paramMap.put("xq", Base.getJxjsqxq());
		setStuKccjxx(request, paramMap);
		setStuWjcfxx(request, paramMap);
		
		if (!paramMap.isEmpty() && !rs.isEmpty()) {
			//��ѯѧ���۲�ɼ�
			rs.putAll(service.queryStuZhcjMap(paramMap));
		}
		
		appendJxjList(request);
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("xqmc", Base.getJxjsqxqmc());
		request.setAttribute("xq", Base.getJxjsqxq());
		request.setAttribute("rs", rs);
		return mapping.findForward("add");
	}
	
	/**
	 * ��ѧ���޸Ĳ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		JxjService service = new JxjService();
		
		/* �޸����ݲ��� */
		if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {
			this.updateOperation(request, "xsjxjb");
		}else if (VIEW.equalsIgnoreCase(czxxForm.getAct())) {
			request.setAttribute("writable", "view");
		}
		
		//��ʾ���ݲ���
		this.selectPageDataByOne(request, "xsjxjb", "view_xsjxjb", request.getParameter("pkValue"));
		
		HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("rs");
		
		/* ����ѧ�����֣��ɼ���Ϣ */
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("xh", map.get("xh"));
		paramMap.put("xn", Base.getJxjsqxn());
		paramMap.put("xq", Base.getJxjsqxq());
		setStuKccjxx(request, paramMap);
		setStuWjcfxx(request, paramMap);
		
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
//		��ѯѧ���۲�ɼ�
		if (!rs.isEmpty() && StringUtils.isNotNull(rs.get("xh"))) {
			rs.putAll(service.queryStuZhcjMap(paramMap));
			
		}
		
		appendJxjList(request);
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("xqmc", Base.getJxjsqxqmc());
		request.setAttribute("xq", Base.getJxjsqxq());
		request.setAttribute("rs", rs);
		return mapping.findForward("update");
	}
	
	/**
	 * ��ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		ActionForward af = null;
		
		/* ѧԺ�ҷǸ���Ա����ѧԺ���� */
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString())
				&& !"true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		if (UserTypePd.isFdy(request.getSession().getAttribute("fdyQx")
				.toString())) {
			af = fdySh(mapping, form, request, response);
		} else {
			af = jxjBmsh(mapping, form, request, response);
		}
		appendOperQx(request, "prise_check.do");
		appendJxjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		return af;
	}
	
	/**
	 * ����Ա���ѧ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdySh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		JxjService service = new JxjService();
		boolean result = false;
		JxjModel model = new JxjModel();
		
		czxxForm.setQueryequals_xn(Base.getJxjsqxn());
		czxxForm.setQueryequals_xq(Base.getJxjsqxq());
		czxxForm.setXn(czxxForm.getQueryequals_xn());
		czxxForm.setXq(czxxForm.getQueryequals_xq());
		
		/* ѧԺ�ҷǸ���Ա����ѧԺ���� */
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString())
				&& !"true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		BeanUtils.copyProperties(model, czxxForm);
		/* ��ѯ��� */
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			String[] outputColumn = { "pkValue", "dis", "�к�", "xn", "xqmc",
					"xh", "xm", "bjmc", "jxjmc", "jlje", "zfpm", "fdysh","�ύ��", "�ύʱ��",
					"xysh", "xxsh" };
			request.setAttribute("clientColumns", getDisSql(request, "sh"));
			this.selectPageDataByPagination(request, czxxForm, "xsjxjb",
					"view_czxx_xsjxjb", outputColumn);
			
			/* �༶�����ύ״̬ */
			String tjzt = service.queryTjzt(czxxForm.getQueryequals_bjdm(), model, "bj",
					"jxj");
			request.setAttribute("tjzt", tjzt);
		} else if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {//��˲���
			result = service.updateJxjplshjg(request
					.getParameterValues("primarykey_cbv"), request
					.getParameter("jg"), request.getSession().getAttribute(
					"userType").toString(), request.getSession().getAttribute(
					"fdyQx").toString());			
			appendOperResult(request, result);
		} else if (DATA_TJ.equalsIgnoreCase(czxxForm.getAct())) {//�ύ�༶�����
			String userName = request.getSession().getAttribute("userNameReal").toString();
			String bjdm = request.getParameter("tjbjdm");
			model.setBjdm(bjdm);
			result = service.fdyTjshjg(bjdm, model, userName, "jxj", "1", "bj");
			appendOperResult(request, result);
		}
		
		appendOperQx(request, "prise_check.do");
		appendJxjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("xq", Base.getJxjsqxq());
		return mapping.findForward("grsh");
	}
	
	
	
	/**
	 * ����Ա�û���ѧ�𵥸����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDggrsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		JxjService service = new JxjService();
		String pkValue =request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		
		rs = CommonQueryDAO.commonQueryOne("view_czxx_xsjxjb", new String[] {
				"xh", "xn", "xq", "xqmc", "jxjmc", "jlje", "xm", "xb", "nj",
				"xymc", "zymc", "bjmc", "zfpm", "dcj", "zcj", "tcj", "zxf",
				"dpm", "tpm", "zpm", "fdysh", "fdyyj", "xysh", "xyshyj",
				"xxsh", "xxshyj", "bjdm", "xydm", "zydm" },
				"xh||jxjdm||xn||xq", pkValue);
		
		if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {
			boolean result = service.updateJxjdgshjg(pkValue, czxxForm.getSh(),
					czxxForm.getYj(), request.getSession().getAttribute(
							"userType").toString(), request.getSession()
							.getAttribute("fdyQx").toString());
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(czxxForm.getAct())) {//���ö�дȨΪֻ��
			request.setAttribute("writable", "view");
		}
		
		if (!rs.isEmpty()) {
			makeFormValue(rs, request, czxxForm);
			setStuKccjxx(request, rs);
			setStuWjcfxx(request, rs);
			JxjModel model = new JxjModel();
			model.setQueryequals_xn(rs.get("xn"));
			model.setQueryequals_xq(rs.get("xq"));
			String tjzt = service.queryTjzt(rs.get("bjdm"), model, "bj", "jxj");
			request.setAttribute("tjzt", tjzt);
		}
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("jxjDggrsh");
	}
	
	/**
	 * ��ѧ����������ѯ����ҳ��ѡ���SQL
	 * @param request
	 * @return
	 */
	public String getDisSql(HttpServletRequest request, String type) {
		String userType = request.getSession().getAttribute("userType").toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		JxjService serivce = new JxjService();
		
		return serivce.getDisSqlByQuery(userType, isFdy, type);
	}
	
	/**
	 * ѧԺ��ѧУ��ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjBmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		JxjService service = new JxjService();
		boolean result = false;
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		JxjModel model = new JxjModel();
		String userType = request.getSession()
		.getAttribute("userType").toString();
		String userName = request.getSession()
		.getAttribute("userNameReal").toString();
		
		czxxForm.setXn(Base.getJxjsqxn());
		czxxForm.setXq(Base.getJxjsqxq());
		
		/* ѧԺ�ҷǸ���Ա����ѧԺ���� */
		if ("xy".equalsIgnoreCase(request.getSession().getAttribute("userType")
				.toString())
				&& !"true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())) {
			czxxForm.setXydm(request.getSession().getAttribute("userDep")
					.toString());
		}
		
		BeanUtils.copyProperties(model, czxxForm);
		/* ��ѯ��� */
		if (QUERY.equalsIgnoreCase(czxxForm.getAct())) {
			topTr = service.queryXyJxjshTitle(userType);
			rs = service.queryXyjxjshjg(model, userType);
			model.setQueryequals_xn(czxxForm.getXn());
			model.setQueryequals_xq(czxxForm.getXq());
			
			/* �༶�����ύ״̬ */
			String tjzt = service.queryTjzt(czxxForm.getXydm(), model, "xy",
					"jxj");
			request.setAttribute("tjzt", tjzt);
		} else if (SAVE.equalsIgnoreCase(czxxForm.getAct())) {//��˲���
			result = service.updateXyjxjplshjg(request.getParameter("jg"),
					request.getParameter("yj"), request
							.getParameterValues("cbv"), userType);
			appendOperResult(request, result);
			
		} else if (DATA_TJ.equalsIgnoreCase(czxxForm.getAct())) {//�ύ�༶�����
			String xydm = request.getParameter("tjxydm");
			model.setXydm(xydm);
			model.setQueryequals_xn(czxxForm.getXn());
			model.setQueryequals_xq(czxxForm.getXq());
			result = service.fdyTjshjg(xydm, model, userName, "jxj", "1", "xy");
			appendOperResult(request, result);
		} else if (DATA_FH.equalsIgnoreCase(czxxForm.getAct())) {//ѧԺ��������
			String[] pkValue = request.getParameterValues("cbv");
			result = service.xyjxjFhcs("δ���", "", pkValue, userType, userName);
			appendOperResult(request, result);
		}
		
		appendQryResult(request, topTr, rs);
		appendOperQx(request, "prise_check.do");
		appendJxjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		
		return mapping.findForward("jxjBmsh");
	}
	
	/**
	 * ѧԺ��ѧ�������ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyjxjshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyCzxxActionForm czxxForm = (PjpyCzxxActionForm) form;
		String userType = request.getSession()
		.getAttribute("userType").toString();
		List<String[]> rs = new ArrayList<String[]>();
		
		String pkValue = request.getParameter("pkValue");
		if (UserTypePd.isXy(userType)) {
			rs = CommonQueryDAO
			.commonQueryNotFy("", "", new String[] { pkValue },
					new String[] { "�к�", "xn", "xqmc", "bjmc", "xh", "xm",
							"jxjmc", "jlje", "zfpm", "fdysh", "xysh",
							"xxsh" },
					"select a.*,rownum �к� from view_czxx_xsjxjb a where fdysh='ͨ��' and xn||xq||bjdm||jxjdm=? " +
					"and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='jxj' and tjzt='1')");
		} else {
			rs = CommonQueryDAO
			.commonQueryNotFy("", "", new String[] { pkValue },
					new String[] { "�к�",  "xn", "xqmc", "xymc", "bjmc", "xh", "xm",
							"jxjmc", "jlje", "zfpm",
							"xxsh" },
					"select a.*,rownum �к� from view_czxx_xsjxjb a where fdysh='ͨ��' and xysh='ͨ��' and xn||xq||xydm=? " +
					"and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.bjdm=b.dm and b.bm='bj' and b.tjxm='jxj' and tjzt='1') " +
					"and exists (select 1 from pjpy_bmshtjb b where a.xn||a.xq=b.zjz and a.xydm=b.dm and b.bm='xy' and b.tjxm='jxj' and tjzt='1')");
		}
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("xyjxjshView");
	}
	
	public void makeFormValue(HashMap<String, String> rs,
			HttpServletRequest request, PjpyCzxxActionForm form) {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		if (!rs.isEmpty()) {
			if (UserTypePd.isFdy(isFdy)) {
				form.setSh(rs.get("fdysh"));
				form.setYj(rs.get("fdyyj"));
			} else if (UserTypePd.isXy(userType)) {
				form.setSh(rs.get("xysh"));
				form.setYj(rs.get("xyshyj"));
			} else {
				form.setSh(rs.get("xxsh"));
				form.setYj(rs.get("xxshyj"));
			}
		}
	}
	
	//��ӡ����
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String table = request.getParameter("table");
		
		JxjService service = new JxjService();
		
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = service.getPrintMap(pkValue, table);
		request.setAttribute("mc", "xsjxjb".equalsIgnoreCase(table) ? "��ѧ��" : "�����ƺ�");
		request.setAttribute("rs", rs);
		return mapping.findForward("print");
	}

}

