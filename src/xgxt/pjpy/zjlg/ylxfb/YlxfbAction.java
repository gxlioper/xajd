/*
 * @Title: ѧ������������Ϣϵͳ
 * 
 * @ClassName: YlxfbAction.java
 * 
 * @time: 2010-06-04 
 * 
 * @copyright: hz-zfsoft 
 */
package xgxt.pjpy.zjlg.ylxfb;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.pjpy.zjlg.ZjlgPjpyService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * @desc �㽭�� - �������� - ����ѧ��༶����ά��ACTION��
 *                          ��������ɾ���ģ��飬��˵Ȳ���
 * @parentClass CommonAction.java
 * @author lt
 * @version 1.0 2010-06-04
 */
public class YlxfbAction extends CommonAction {

	YlxfbService service = new YlxfbService();
	
	/** 
	 * ����ѧ��༶����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ylxfbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YlxfbActionForm myForm = (YlxfbActionForm) form;
		String userType = getSessionAttributeValue(request, "userType");
		String fdyFlag = getSessionAttributeValue(request, "fdyQx");
		String act = myForm.getAct();

		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(act)) {
			this.insertOperation(request, "zjlg_ylxfb");
		}
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(fdyFlag)) {
			myForm.setXydm(getSessionAttributeValue(request, "userDep"));
		}

		HashMap<String, String> rs = new HashMap<String, String>();
		rs = CommonQueryDAO.commonQueryOne("view_njxyzybj", new String[] {
				"nj", "xymc", "bjmc" }, "bjdm", myForm.getSave_bjdm());

		if (StringUtils.isNotNull(myForm.getSave_bjdm())) {
			YlxfbModel model = new YlxfbModel();
			model.setBjdm(myForm.getSave_bjdm());
			model.setXn(Base.getJxjsqxn());
			model.setNj(rs.get("nj"));
			List<HashMap<String, String>> kcfsszMap = service.queryBjkskckxx(model);
			if (!kcfsszMap.isEmpty()) {
				for (HashMap<String, String> map : kcfsszMap) {
					if (!map.isEmpty() && "kckyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
						model.setKckcj(map.get("tjz"));
					} else if (!map.isEmpty() && "kskyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
						model.setKskcj(map.get("tjz"));
					}
				}
			}
			HashMap<String, String> map = service.queryPmxx(model);
			request.setAttribute("map", map);
			
			String fdyxm = service.getFdyxm(myForm.getSave_bjdm());
			if (rs != null && !rs.isEmpty()) {
				rs.put("fdyxm", fdyxm);
			}
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("xn", Base.getJxjsqxn());
		/* ����ҳ���б���дȨ */
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		// FormModleCommon.setNdXnXqList(request);
		appendOperQx(request, "pjpy_zjlg_ylxfbsq.do");
		return mapping.findForward("ylxfbsq");
	}
	
	/**
	 * ����ѧ��༶��������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ylxfbwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlxfbActionForm myForm = (YlxfbActionForm) form;
		String act = myForm.getAct();
		String userType = getSessionAttributeValue(request, "userType");
		String fdyFlag = getSessionAttributeValue(request, "fdyQx");

		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(fdyFlag)) {
			myForm.setQueryequals_xydm(getSessionAttributeValue(request, "userDep"));
			myForm.setXydm(getSessionAttributeValue(request, "userDep"));
		}
		
		if (QUERY.equalsIgnoreCase(act)) {// ��ѯ���ݲ���
			request.setAttribute("clientColumns", service.getClientColumn(
					userType, fdyFlag));
			this.selectPageDataByPagination(request, myForm, "zjlg_ylxfb",
					"view_zjlg_ylxfb", new String[] { "pkValue", "dis", "�к�",
							"xn", "xymc", "bjmc", "pjpm", "xysh", "xxsh" });
		} else if (DELETE.equalsIgnoreCase(act)) {
			this.deleteOperation(request, "zjlg_ylxfb");
		}

		/* ����ҳ���б���дȨ */
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_ylxfb", "zjlg_ylxfb");
		appendOperQx(request, "pjpy_zjlg_ylxfbwh.do");
		return mapping.findForward("ylxfbwh");
	}
	
	/**
	 * ����ѧ����޸Ĳ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ylxfbModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YlxfbActionForm myForm = (YlxfbActionForm) form;
		String act = myForm.getAct();
		String userType = getSessionAttributeValue(request, "userType");
		String fdyFlag = getSessionAttributeValue(request, "fdyQx");
		String pkValue = request.getParameter("pkValue");
		
		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(act)) {
			this.updateOperation(request, "zjlg_ylxfb");
		} else if (VIEW.equalsIgnoreCase(act)) {
			request.setAttribute("writable", "no");
		}
		
		//��ʾ��ѯ��������
		selectPageDataByOne(request, "zjlg_ylxfb", "view_zjlg_ylxfb", pkValue);
		
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		if (!rs.isEmpty()) {
			if (StringUtils.isNotNull(rs.get("bjdm"))) {
				YlxfbModel model = new YlxfbModel();
				model.setBjdm(rs.get("bjdm"));
				model.setXn(rs.get("xn"));
				model.setNj(rs.get("nj"));
				List<HashMap<String, String>> kcfsszMap = service.queryBjkskckxx(model);
				if (!kcfsszMap.isEmpty()) {
					for (HashMap<String, String> map : kcfsszMap) {
						if (!map.isEmpty() && "kckyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
							model.setKckcj(map.get("tjz"));
						} else if (!map.isEmpty() && "kskyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
							model.setKskcj(map.get("tjz"));
						}
					}
				}
				HashMap<String, String> map = service.queryPmxx(model);
				request.setAttribute("map", map);
				
				String fdyxm = service.getFdyxm(rs.get("bjdm"));
				if (rs != null && !rs.isEmpty()) {
					rs.put("fdyxm", fdyxm);
				}
				
			}
			if (UserTypePd.isXy(userType) && "ͨ��".equalsIgnoreCase(rs.get("xxsh"))) {
				request.setAttribute("writable", "no");
			}
		}
		
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("ylxfbmodi");
	}
	
	/**
	 * ����ѧ��༶���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ylxfbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YlxfbActionForm myForm = (YlxfbActionForm) form;
		String act = myForm.getAct();
		myForm.setQueryequals_xn(Base.getJxjsqxn());
		String userType = getSessionAttributeValue(request, "userType");
		String fdyFlag = getSessionAttributeValue(request, "fdyQx");
		
		
		/* ����ԱȨ������ */
		if (UserTypePd.isFdy(fdyFlag)) {
			request.setAttribute("errMsg", "����Ȩ���ʵ�ǰҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(fdyFlag)) {
			myForm.setQueryequals_xydm(getSessionAttributeValue(request, "userDep"));
			myForm.setXydm(getSessionAttributeValue(request, "userDep"));
		}
		
		if (QUERY.equalsIgnoreCase(act)) {//��ѯ���ݲ���
			request.setAttribute("clientColumns", service.getClientColumn(
					userType, fdyFlag));
			selectPageDataByPagination(request, myForm, "zjlg_ylxfb",
					"view_zjlg_ylxfb", new String[] { "pkValue", "dis", "�к�",
							"xn", "xymc", "bjmc", "pjpm", "xysh", "xxsh" });
		} else if (SH.equalsIgnoreCase(act)) {//��˲���
			request.setAttribute("shzd",
					UserTypePd.isXx(userType) ? "xxsh" : "xysh");
			request.setAttribute("shjg", getShjgString(request));
			auditingBatchOperation(request, "zjlg_ylxfb");
		}
		ZjlgPjpyService service = new ZjlgPjpyService();
		String userName = request.getSession().getAttribute("userName").toString();
		//boolean bools = service.serv_audit(pkVStr, userType, check, "", "�����ƺ�����", userName,"zjlg_xsrychb");
		/* ����ҳ���б���дȨ */
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		appendTableXx(request, "view_zjlg_ylxfb", "zjlg_ylxfb");
		appendOperQx(request, "pjpy_zjlg_ylxfbsh.do");
		return mapping.findForward("ylxfbsh");
	}
	
	/**
	 * ����ѧ��൥�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ylxfbdgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YlxfbActionForm myForm = (YlxfbActionForm) form;
		String act = myForm.getAct();
		String pkValue = request.getParameter("pkValue");
		String userType = getSessionAttributeValue(request, "userType");
		
		if (SAVE.equalsIgnoreCase(act)) {//�������ݲ���
			updateOperation(request, "zjlg_ylxfb");
		} else if (VIEW.equalsIgnoreCase(act)) {//��ʾ����
			request.setAttribute("writable", "no");
		}
		
		selectPageDataByOne(request, "zjlg_ylxfb", "view_zjlg_ylxfb", pkValue);
		
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		if (!rs.isEmpty()) {
			if (StringUtils.isNotNull(rs.get("bjdm"))) {
				YlxfbModel model = new YlxfbModel();
				model.setBjdm(rs.get("bjdm"));
				model.setXn(rs.get("xn"));
				model.setNj(rs.get("nj"));
				List<HashMap<String, String>> kcfsszMap = service.queryBjkskckxx(model);
				if (!kcfsszMap.isEmpty()) {
					for (HashMap<String, String> map : kcfsszMap) {
						if (!map.isEmpty() && "kckyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
							model.setKckcj(map.get("tjz"));
						} else if (!map.isEmpty() && "kskyxlfs".equalsIgnoreCase(map.get("tjzd"))) {
							model.setKskcj(map.get("tjz"));
						}
					}
				}
				HashMap<String, String> map = service.queryPmxx(model);
				request.setAttribute("map", map);
				
				String fdyxm = service.getFdyxm(rs.get("bjdm"));
				if (rs != null && !rs.isEmpty()) {
					rs.put("fdyxm", fdyxm);
				}
			}
			
			if (UserTypePd.isXy(userType) && "ͨ��".equalsIgnoreCase(rs.get("xxsh"))) {
				request.setAttribute("writable", "no");
			}
		}
		
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("ylxfbdgsh");
	}
	
	/**
	 * ����ѧ��༶���Ȼ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ylxfbhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YlxfbActionForm myForm = (YlxfbActionForm) form;
		YlxfbModel model = new YlxfbModel();
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/zjlg_ylxfbhzb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		model.setXymc(request.getParameter("xymc"));
		model.setXydm(myForm.getQueryequals_xydm());
		model.setXn(Base.getJxjsqxn());
		
		service.exportYlxfbhzData(wwb, model);
		return mapping.findForward("");
	}
}
