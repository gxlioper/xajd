package xgxt.comm.xginfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �����Ϣͨ��-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class CommXgInfoAction extends BasicAction {
	
	/**
	 * ѧ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXgInfoService service = new CommXgInfoService();
		CommForm myForm = (CommForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// �û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		// ��ͼ
		String tableName = request.getParameter("tableName");
		// ��Ŀ����
		String lx = request.getParameter("lx");
		// ģ������
		String mklx = request.getParameter("mklx");
		// ��ʾ��Ϣ
		String message = "";
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// =================end==================

		// ==================��½�û���� ==================

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// ѧԺ�û�
			myForm.setXydm(userDep);
			isxy = true;
		}

		// ��½�û�����
		String userLx = "";

		if (bzrQx && fdyQx) {// �����μ渨��Ա
			userLx = "jd";
		} else if (fdyQx) {// ����Ա
			userLx = "fdy";
		} else if (bzrQx) {// ������
			userLx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			userLx = "xy";
			myForm.setXydm(userDep);
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			userLx = "xx";
		}

		myForm.setUserType(userLx);

		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {

			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc" };
			
			myForm.setTableName(tableName);
			
			// ��ͷ
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			// ���
			rsArrList = service.getXsxxList(myForm, colList);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy","lx" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy),lx };

		rForm.setTableName(tableName);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setMklx(mklx);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		rForm.setGnmk("gygl");
		service.setList(myForm, rForm, request);
		// =================end ===================

		return mapping.findForward("commImp");
	}
	
	/**
	 * ѡ��ѧԺ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		
		List<String[]> rs = service.getXyList(model);

		// ��ѯ��������������ѧԺ
		request.setAttribute("rs", rs);
		
		// ��������ҳ���ҳ,��Ҫ���ܹ���Ŀ��
		commonForm.setPages(model.getPages());
		
		return mapping.findForward("choiceXy");
	}
	
	/**
	 * ѡ��רҵ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		List<String[]>  rs = service.getZyList(model);
		
		request.setAttribute("xymc", commonForm.getXymc());
		request.setAttribute("xydm", commonForm.getXydm());
		request.setAttribute("rs", rs);
		return mapping.findForward("choiceZy");
	}
	
	/**
	 * ѡ��༶
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		model.setUser(getUser(request));
		List<String[]>  rs = service.getBjList(model);
		
		request.setAttribute("xymc", request.getParameter("xymc"));
		request.setAttribute("zymc", request.getParameter("zymc"));
		request.setAttribute("zydm", request.getParameter("zydm"));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("rs", rs);
		return mapping.findForward("choiceBj");
	}
	
	/**
	 * ѡ��༶�������꼶��ѧԺ��רҵ��ѯ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	public ActionForward xzBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		User user = getUser(request);// �û�����
		CommXgInfoModel model = new CommXgInfoModel();
		if ("xy".equalsIgnoreCase(user.getUserType())) {
			commonForm.setXydm(user.getUserDep());
		}
		model.setUser(user);
		RequestForm rForm = new RequestForm();
		BeanUtils.copyProperties(model, commonForm);
		CommXgInfoService service = new CommXgInfoService();
		String zdpzstr = request.getParameter("zdpzstr");
		service.setRequestValue(rForm, user, request);
		List<String[]>  rs = service.getBjList(model);
		request.setAttribute("rs", rs);
		request.setAttribute("zdpzstr", zdpzstr);
		String isZybjXz= request.getParameter("isZybjXz");
		request.setAttribute("isZybjXz", isZybjXz);
		service.setAllList(commonForm, rForm, request);
		return mapping.findForward("xzBj");
	}
	
	/**
	 * ѡ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceLc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		//ģ����Ϣ
		String mkid = request.getParameter("mkid");
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		List<String[]>  rs = service.getLcList(model,mkid);
		
		request.setAttribute("rs", rs);
		request.setAttribute("cyfsList", service.getCyfsList());
		return mapping.findForward("choiceLc");
	}
	
}