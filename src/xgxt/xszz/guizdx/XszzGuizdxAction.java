package xgxt.xszz.guizdx;

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

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���ݴ�ѧѧ�����������ѵ����-action��
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

public class XszzGuizdxAction extends BasicAction {
	
	/**
	 * ѧ������_��ʳ����_����רҵ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbzfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGuizdxService service = new XszzGuizdxService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		//	================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½�û�����
		String userDep= (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_njxyzybj";
		// ����
		String realTable = "xszz_fsbzzyb";
		// ����·��
		String path = "xszz_fsbz_fp.do";
		// ��ǰ���
		String xn = Base.currNd;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================�ж�ģ��,Ȩ��======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(xn);
		}
		
		// =================end ===================
		
		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveFsbzZyxm(myForm, realTable);
			String message = result ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("result", result);
			request.setAttribute("message", message);
		}
		// =================end ===================
		
		//==================ִ�в�ѯ���� ==================
		if (search) {
			String[] colList = new String[] { "nd", "xymc", "zymc","zydm","bzlx" };
			
			ArrayList<String[]> rs = service.getZyList(myForm, colList);
			List<HashMap<String, String>> topTr = service.getTopTr("fsbz_fp");
			
			//��ʳ������Ŀ�б�
			List<HashMap<String, String>> xmList = service.getFsbzXmList(myForm);
			if(xmList !=null && xmList.size()>0){
				request.setAttribute("xmNum", xmList.size());
			}
			request.setAttribute("xmList", xmList);
			
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);	
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "fsbz_fp");
		// =================end ===================

		return mapping.findForward("fsbzfpManage");
	}
	
	/**
	 * ѧ������_��ʳ����_����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbzffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGuizdxService service = new XszzGuizdxService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		//	================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½�û�����
		String userDep= (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_xsjbxx";
		// ����
		String realTable = "xszz_fsbz_xsffb";
		// ����·��
		String path = "xszz_fsbz_ff.do";
		// ��ǰ���
		String xn = Base.currNd;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================�ж�ģ��,Ȩ��======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		
		//���õ�ǰѧ��
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(xn);
		}
		
		// ���õ�ǰ��
		if (Base.isNull(myForm.getYf())) {
			myForm.setYf(service.getDqyf());
		}
		
		// =================end ===================
		
		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			
			boolean flag = service.delFsbzFf(myForm, realTable);
			
			if(flag){
				HashMap<String, String[]> primaryArrayMap = this.getValueArrayMap(request, "primarykey_");
				HashMap<String, String> primaryMap = new HashMap<String, String>();
				//���������ֶ�
				primaryMap.put("nd", myForm.getNd());
				primaryMap.put("yf", myForm.getYf());
				primaryMap.put("bzlx", myForm.getBzlx());
				
				HashMap<String, String> tableMap = new HashMap<String, String>();
				tableMap.put("tableName", realTable);
				//tableMap.put("viewName", tableName);
				
				this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
			}
		}
		// =================end ===================
		
		//==================ִ�в�ѯ���� ==================
		if (search) {
			String[] colList = new String[] { "sfdj", "xh", "xm", "nj",
					"xymc", "zymc", "bjmc", "nd", "yf", "bzmc" };
			
			ArrayList<String[]> rs = service.getFsbzXsffList(myForm, colList);
			List<HashMap<String, String>> topTr = service.getTopTr("fsbz_ff");
			
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);	
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "fsbz_ff");
		// =================end ===================

		return mapping.findForward("fsbzffManage");
	}
	
	/**
	 * ѧ������_��ʳ����_���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbzjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzGuizdxService service = new XszzGuizdxService();
		XszzTyForm myForm = (XszzTyForm) form;
		
		//	================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½�û�����
		String userDep= (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String realTable = "";
		// ����·��
		String path = "xszz_fsbz_jg.do";
		// ģ������
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "jg" : mklx;
		// ��ͼ��
		String tableName = "";
		tableName = ("jg".equalsIgnoreCase(mklx)) ? "view_xszz_fsbzff" : "view_xszz_fsbztj";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================�ж�ģ��,Ȩ��======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_xydm(userDep);
		}
		
		// =================end ===================
		
		//==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = null;

			// ���
			if ("jg".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "nd", "yfmc", "bzmc", "kh", "ffje" };
				this.selectPageDataByPagination(request, myForm, "", tableName,
						outputColumn);
			}
			// δ����
			else if ("wff".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "nd", "yf", "bzmc" };
				ArrayList<String[]> rs = service.getFsbzWffList(myForm,
						outputColumn);
				List<HashMap<String, String>> topTr = service
						.getTopTr("fsbz_wff");
				FormModleCommon.commonRequestSet(request, tableName, realTable,
						rs, topTr);
			}
			// ͳ��
			else if ("tj".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "bzmc", "nd", "kh", "zje" };
				this.selectPageDataByPagination(request, myForm, "", tableName,
						outputColumn);
			}
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			// ���
			if ("jg".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xydm",
						"xymc", "zydm", "zymc", "bjdm", "bjmc", "nd", "yf",
						"yfmc", "bzlx", "bzmc", "kh", "ffje" };
			}// ͳ��
			else if ("tj".equalsIgnoreCase(mklx)) {
				outputColumn = new String[] { "xh", "xm", "xb", "nj", "xydm",
						"xymc", "zydm", "zymc", "bjdm", "bjmc", "nd", "bzlx",
						"bzmc", "kh", "zje" };
			}
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "fsbz_ff");
		// =================end ===================

		return mapping.findForward("fsbzjgManage");
	}
}
