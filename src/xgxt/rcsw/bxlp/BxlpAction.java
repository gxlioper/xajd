package xgxt.rcsw.bxlp;

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
import xgxt.bdsz.BdszService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ա�����Action</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: �����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-03</p>
 */
public class BxlpAction extends BasicAction {
	
	/**
	 * ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BdszService bdszService = new BdszService();
		
		String realTable = "rcsw_bxwh";
		String tableName = "view_rcsw_bxwh";
		String[] colList = new String[] { "pkValue","disabled","xh", "xm", "xb", "sfzh", "xn",
				"nd",  "nj", "xymc", "zymc", "bjmc", "rq","sfby", "je","nx","fdysh","xysh","xxsh" };
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		
		String doType = request.getParameter("doType");
		boolean result = false;
		
		
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		//����ɾ����ѡ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = bdszService.delData(realTable, checkVal);

				request.setAttribute("result", result);
			}
		}
		
		//��ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))) {
			
			//��ѯ���� 
			String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm",
					  "xb", "cxlx", "xn", "nd", "xq", "sfby" }; 
			String[] queryLikeList = new String[] { "xh", "xm", "sfzh" };
			
			//������������ѯ�Զ����ֶε����������������ֶ�����
			HashMap<String,String[]> zdyCol = bdszService.getZdyCol(realTable);
			
			String[] zdyZdEn = zdyCol.get("zdyZdEn");
			String[] zdyZdCn = zdyCol.get("zdyZdCn");
			String[] zdyZdLx = zdyCol.get("zdyZdLx");
			
			//���Զ����ֶεı�ͷ�������
			topTr = bdszService.getZdyTopTr(tableName, colList, zdyZdEn, zdyZdCn);
			rs = bdszService.getZdyData(realTable, tableName, queryList, queryLikeList, model, colList, zdyZdEn, zdyZdLx);
			
		}
		
		setList("bxwh", request);
		request.setAttribute("path", "rcsw_bxwh.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("bxwhManage");
	}
	
	
	
	/**
	 * ������Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxwhSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BxlpService service = new BxlpService();
		BdszService bdszService = new BdszService();//������service
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		String realTable = "rcsw_bxwh";
		String tableName = "view_rcsw_bxwh";
		
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String now = service.getNow();
		
		boolean result = false;
		
		if ("stu".equals(userType)) {
			myForm.setXh(userName);
		}
		
		HashMap<String ,String> rs = service.getStu(myForm.getXh());
		
		BeanUtils.copyProperties(model, myForm);
		
		model.setNd(Base.currNd);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setRq(now);
		
		//����
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			
			String pkValue = model.getXh()+now;
			model.setXysh("δ���");
			model.setFdysh("δ���");
			model.setXxsh("δ���");
			result = bdszService.saveData(realTable,pkValue, model, request);
			
			request.setAttribute("result", result);
		}
		
		
		//�޸�
		if (!Base.isNull(doType) && "modi".equalsIgnoreCase(doType)) {
			String newPkValue = model.getXh()+now;
			
			result = bdszService.updateData(realTable,newPkValue, pkV, model, request);
			request.setAttribute("result", result);
		}
		
		//��������
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			
			rs = bdszService.getOneData(tableName, realTable, pkV);
		}
		
//		service.getBdZd(realTable, myForm);
		//���Զ����ֶ���Ϣ�󶨵�form��
		bdszService.setBdZd(realTable,myForm);
		
		setList("bxwh", request);
		request.setAttribute("pk", pkV);
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		
		return mapping.findForward("bxwhSave");
	}
	
	
	
	/**
	 * ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lpwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BxlpService service = new BxlpService();
		
		String realTable = "rcsw_lpwh";
		String tableName = "view_rcsw_lpwh";
		String pk = "xh||'!!@!!'||slrq||'!!@!!'||cxlx";
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "sfzh", 
				"xymc", "bjmc", "slrq", "zfrq", "spje", "lpje",
				"cxlxmc", "lxdh" };
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		
		String doType = request.getParameter("doType");
		boolean result = false;
		
		
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		//����ɾ����ѡ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = service.delData(realTable, checkVal, pk);

				request.setAttribute("result", result);
			}
		}
		
		//��ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))) {
			
			List<HashMap<String, String>> zdyColList = service.zdyColList(realTable);
			String[] zdyCol = new String[zdyColList.size()];
			String[] zdyColCN = new String[zdyColList.size()];
			
			for (int i = 0; i < zdyColList.size(); i++) {
				zdyCol[i] = zdyColList.get(i).get("zdid");
				zdyColCN[i] = zdyColList.get(i).get("zdmc");
			}
			
			topTr = service.getZdyTopTr(tableName, colList, zdyCol, zdyColCN);
			rs = service.getDataList(tableName, model, colList, zdyCol,
					realTable, new String[] { pk});
			
		}
		
		request.setAttribute("path", "rcsw_lpwh.do");
		
		service.setList(request, "cxlx");
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("lpwhManage");
	}
	

	
	/**
	 * ������Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lpwhSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BxlpService service = new BxlpService();
		BdszService bdszService = new BdszService();//������service
		
		String realTable = "rcsw_lpwh";
		String tableName = "view_rcsw_lpwh";
		String pk = "xh||'!!@!!'||slrq||'!!@!!'||cxlx";
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String[] colList = new String[]{"xh","spje","lpje","slrq","zfrq","cxlx","lxdh"};
		
		boolean result = false;
		
		HashMap<String ,String> rs = service.getStu(myForm.getXh());
		
		BeanUtils.copyProperties(model, myForm);
		
		//����
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String pkValue = model.getXh()+"!!@!!"+model.getSlrq()+"!!@!!"+model.getCxlx();
			
			result = service.saveData(realTable, colList, pkValue, model, request);

			request.setAttribute("result", result);
		}
		
		
		//�޸�
		if (!Base.isNull(doType) && "modi".equalsIgnoreCase(doType)) {
			
			result = service.updateData(realTable,pk,model, pkV,colList, request);

			request.setAttribute("result", result);
		}
		
		//��������
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			
			List<HashMap<String, String>> zdyColList = service.zdyColList(realTable);
			String[] queryColList = new String[] { "xh", "xm", "xb", "sfzh",
					"nj", "xydm", "zydm", "bjdm", "slrq", "zfrq", "spje",
					"lpje", "cxlx", "lxdh" };
			String[] zdyCol = new String[zdyColList.size()];
			
			for (int i = 0; i < zdyColList.size(); i++) {
				zdyCol[i] = zdyColList.get(i).get("zdid");
			}
			rs = service.getOneData(tableName, realTable, queryColList, zdyCol, pk,pkV);
		}
		
//		service.getBdZd(realTable, myForm);
		
		//���Զ����ֶ���Ϣ�󶨵�form��
		bdszService.getBdZd(realTable, "bxlp", myForm);
		
		service.setList(request, "cxlx");
		
		request.setAttribute("pk", pkV);
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		
		return mapping.findForward("lpwhSave");
	}
	
	
	
	/**
	 * ���ջ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxhzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BxlpService service = new BxlpService();
		
		String realTable = "rcsw_bxwh";
		String tableName = "view_rcsw_bxwz";
		String pk = "xh||'!!@!!'||nd";
		String[] colList = new String[] { pk, "xh", "xm", "xb", "sfzh", "nj",
				"xymc", "zymc", "bjmc", "nd", "ljje" };
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))) {
			
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getBxlpList(tableName, model, colList);
		}
		
		request.setAttribute("path", "rcsw_bxhz.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		
		return mapping.findForward("bxhzManage");
	}
	
	
	
	/**
	 * ���ջ�����ϸ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxhzShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BxlpService service = new BxlpService();

		String realTable = "rcsw_bxwh";
		String tableName = "view_rcsw_bxwh";
		String pk = "xh||'!!@!!'||nd";
		String pkV = request.getParameter("pk");

		String[] colList = new String[] { "xh", "xm", "xb", "sfzh", "nj",
				"xydm", "zydm", "bjdm", "nd", "ljje" };

		HashMap<String, String> hzxx = service.getInfo("view_rcsw_bxwz", pk,
				pkV, colList);

		BeanUtils.copyProperties(model, myForm);

		String[] temp = pkV.split("!!@!!");

		if (null != temp && temp.length > 0) {

			model.setXh(temp[0]);
			model.setNd(temp[1]);
		}

		List  topTr = SearchUtils.getTopTr(tableName, new String[] { "xh","nd","rq", "je" }, null);
		ArrayList<String[]>  rs = service.getBxlpList(tableName, model, new String[] { "xh","nd","rq", "je" });

		request.setAttribute("hzxx", hzxx);
		request.setAttribute("path", "rcsw_bxhz.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,topTr);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("bxhzShow");
	}
	
	public void setList(String flg,HttpServletRequest request) {
		
		BxlpService service = new BxlpService();
		
		if ("bxwh".equals(flg)) {
			request.setAttribute("isNot", service.getList("isNot"));
			request.setAttribute("bxgsList", service.getList("bxgs"));
			request.setAttribute("bxxzList", service.getList("bxxz"));
		}
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
	}
	
	
	/**
	 * ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BxlpService service = new BxlpService();
		
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		String doType = request.getParameter("doType");
		boolean result = false;
		
		
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		//����ɾ����ѡ����
		String go=request.getParameter("go");
		if (!Base.isNull(doType) && "plsh".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = service.savePlsh( myForm, user);

				request.setAttribute("result", result);
			}
			go="go";
		}
		
		//��ѯ
		if (((go != null) && "go"
				.equalsIgnoreCase(go))) {
			
			
			rs = service.getBxshList(myForm, user);
			request.setAttribute("rs", rs);
		}
		
		setList("bxwh", request);
		request.setAttribute("topTr", service.getTopTr(user));
		request.setAttribute("path", "rcsw_bxsh.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("bxshManage");
	}
	
	/**
	 * ������Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=getUser(request);
		BxlpForm myForm = (BxlpForm) form;
		BxlpModel model = new BxlpModel();
		BxlpService service = new BxlpService();
		BdszService bdszService = new BdszService();//������service
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		String realTable = "rcsw_bxwh";
		String tableName = "view_rcsw_bxwh";
		
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String now = service.getNow();
		
		boolean result = false;
		
		if ("stu".equals(userType)) {
			myForm.setXh(userName);
		}
		
		HashMap<String ,String> rs = service.getStu(myForm.getXh());
		
		BeanUtils.copyProperties(model, myForm);
		
		model.setNd(Base.currNd);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setRq(now);
		
		//����
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			myForm.setCheckVal(new String[]{pkV});
			result = service.savePlsh(myForm, user);
			request.setAttribute("result", result);
		}
		
		rs = bdszService.getOneData(tableName, realTable, pkV);
		
		//���Զ����ֶ���Ϣ�󶨵�form��
		bdszService.setBdZd(realTable,myForm);
		
		setList("bxwh", request);
		request.setAttribute("pk", pkV);
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "rcsw_bxsh.do");
		return mapping.findForward("bxshSave");
	}
	
}
