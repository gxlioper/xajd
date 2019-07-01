package xgxt.xszz.nbzyjsxy;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description:����ְҵ����ѧԺѧ������Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-13</p>
 */
public class XszzAction extends BaseAction {
	/**
	 * �������Ϣҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_jkrxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delJkrxx(actionForm.getPkT(), request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getJkrTit();
		List<String[]> resList = service.getJkrRes(queryModel,request,actionForm);
		
		actionForm.setXm(Base.chgNull(queryModel.getXm(), "", 1));
		actionForm.setBm(Base.chgNull(queryModel.getBm(), "", 1));
		actionForm.setDw(Base.chgNull(queryModel.getDw(), "", 1));
		actionForm.setSfzh(Base.chgNull(queryModel.getSfzh(), "", 1));
		actionForm.setSj(Base.chgNull(queryModel.getSj(), "", 1));
		actionForm.setXhyhm(Base.chgNull(queryModel.getXhyhm(), "", 1));
		actionForm.setDwlxdh(Base.chgNull(queryModel.getDwlxdh(), "", 1));
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		actionForm.getPages().setMaxRecord(Integer.parseInt(service.getJkrxxResNum(queryModel,request)));
		request.setAttribute("pk", "guid");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nbzy_syjj_jkrxxb");
		request.setAttribute("tableName", "view_nbzy_syjj_jkrxxb");
		request.setAttribute("jsList", dao.getArrayList("SELECT jsdm,jsmc FROM nbzy_syjj_jkrjsdmb ORDER BY jsdm", new String[]{}, new String[]{"jsdm", "jsmc"}));
		return mapping.findForward("data_jkrxx");
	}
	
	/**
	 * �������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jkrxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getJkrxxExp(queryModel, response,request);
		return mapping.findForward("jkrxxExp");
	}
	
	/**
	 * �������Ϣ�༭ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jkrxxEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String xhyhm = Base.chgNull(request.getParameter("xhyhm"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		List<HashMap<String, String>> jkjlList = null;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xhyhm) || !"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getJkrxxByGuid(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getJkrxxByXhyhm(xhyhm);
			}
		}
		
		if (!"add".equalsIgnoreCase(act)) {
			jkjlList = service.getJkjlList(pkVal);
		}
		
		request.setAttribute("jkjlList", jkjlList);
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("jsList", dao.getArrayList("SELECT jsdm,jsmc FROM nbzy_syjj_jkrjsdmb ORDER BY jsdm", new String[]{}, new String[]{"jsdm", "jsmc"}));
		return mapping.findForward("jkrxxEdit");
	}
	
	/**
	 * �������Ϣ�༭����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jkrxxEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		
		JkrxxModel model = new JkrxxModel();
		BeanUtils.copyProperties(model, actionForm);
		
		model.setGuid(!"add".equalsIgnoreCase(act)?pkVal:model.getGuid());
		boolean bJg = service.saveJkrxx(model, act, request);// ������Ϣ
		
		List<HashMap<String, String>> jkjlList = null;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getJkrxxByModel(model);// �õ���ϸ��Ϣ
		if (bJg) {
			List<HashMap<String,String>> valueList = null;
			List<HashMap<String,String>> param = getParams(request);
			valueList = param;
			
			bJg = service.saveJklu(valueList, stuMap.get("guid"));
			
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		jkjlList = service.getJkjlList(pkVal);
		
		request.setAttribute("jkjlList", jkjlList);
		request.setAttribute("act", "mod");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("jsList", dao.getArrayList("SELECT jsdm,jsmc FROM nbzy_syjj_jkrjsdmb ORDER BY jsdm", new String[]{}, new String[]{"jsdm", "jsmc"}));
		return mapping.findForward("jkrxxEditSave");
	}
	
	/**
	 * �������Ϣͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jkrxxTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		BeanUtils.copyProperties(queryModel, actionForm);
		
		List<HashMap<String, String>> topList = null;
		List<String[]> resList = null;
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			topList = service.getJkrTjTit(queryModel.getTjlx());
			resList = service.getJkrTjRes(queryModel);
		}
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		request.setAttribute("hForm", actionForm);
		return mapping.findForward("jkrxxTj");
	}
	
	/**
	 * ˼Դ��������ҳ��
	 * syjjsq ----- ˼Դ��������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getSyjjxxByGuid(pkVal);
		}
		request.setAttribute("isKns", "yes");
		if (!"".equalsIgnoreCase(xh) && stuMap.size() == 0) {
			stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			stuMap.put("nd", Base.currNd);//��ǰ���
			boolean b = dao.isKns(xh);
			if (!b) {
				request.setAttribute("isKns", "no");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("syjjsq");
	}
	
	/**
	 * ����˼Դ����������Ϣ
	 * syjjsqSave ---- ����˼Դ����������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		SyjjModel model = new SyjjModel();
		BeanUtils.copyProperties(model, actionForm);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		model.setGuid(pkVal);
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		boolean bJg = service.saveSyjjxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getSyjjxxByModel(model);
		
		request.setAttribute("isKns", "yes");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", stuMap.get("guid"));
		return mapping.findForward("syjjsqSave");
	}
	
	/**
	 * ˼Դ���������ҳ��
	 * syjjsqb ----- ˼Դ���������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getSyjjxxByGuid(pkVal);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("syjjsqb");
	}
	
	/**
	 * ˼Դ�������ҳ��
	 * syjjsh ----- ˼Դ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXyjj(actionForm.getPkT(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXyjj(actionForm.getPkT(), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXyjj(actionForm.getPkT(), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXyjjTit();
		List<String[]> resList = service.getXyjjRes(queryModel,request,actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		String xm = DealString.toGBK(actionForm.getXm());
		actionForm.setXh(xh);
		actionForm.setXm(xm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);//��REQUEST�д��ҳ����ص��б�
		
		if("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			request.setAttribute("userType", "xx");
			request.setAttribute("isFdy", "false");
		} else {
			if (userBj.size()==0){
				request.setAttribute("userType", "xy");
				request.setAttribute("isFdy", "false");
			} else {
				request.setAttribute("userType", "bjr");
				request.setAttribute("isFdy", "true");
			}
		}
		request.setAttribute("userName", userName);
		actionForm.getPages().setMaxRecord(Integer.parseInt(service.getXyjjResNum(queryModel,request)));
		actionForm.setBjrsh(DealString.toGBK(actionForm.getBjrsh()));
		actionForm.setXysh(DealString.toGBK(actionForm.getXysh()));
		actionForm.setXxsh(DealString.toGBK(actionForm.getXxsh()));
		request.setAttribute("pk", "guid");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nbzyjsxy_syjj");
		request.setAttribute("tableName", "view_nbzyjsxy_syjj");
		return mapping.findForward("syjjsh");
	}
	
	/**
	 * ˼Դ����ͳ��ҳ��
	 * syjjTj ----- ˼Դ����ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		if("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
			userType = "xx";
		} else {
			if (userBj.size()==0){
				userType = "xy";
			} else {
				userType = "bjr";
			}
		}
		List<HashMap<String, String>> topList = service.getXyjjTjTit(userType);
		List<String[]> resList = service.getXyjjTjRes(userType,userDep,userBj);
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		
		return mapping.findForward("syjjTj");
	}
	
	/**
	 * ˼Դ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getSyjjExp(queryModel, response,request);
		return mapping.findForward("syjjExp");
	}
	
	/**
	 * ˼Դ���������ϸҳ��
	 * syjjshOne ----- ˼Դ���������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getSyjjxxByGuid(pkVal);
		
		actionForm.setBjrsh(stuMap.get("bjrsh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
			request.setAttribute("isFdy", "false");
		} else {
			if (userBj.size()==0){
				request.setAttribute("userType", "xy");
				request.setAttribute("isFdy", "false");
			} else {
				request.setAttribute("userType", "bjr");
				request.setAttribute("isFdy", "true");
				stuMap.put("bjrxm", userName);
			}
		}
		List<HashMap<String, String>> syjjList = null;
		syjjList = service.getSyjjList(stuMap.get("xh"));
		request.setAttribute("syjjList", syjjList);
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("syjjshOne");
	}
	
	/**
	 * ����˼Դ���������Ϣ
	 * syjjshSave ---- ����˼Դ���������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syjjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		XszzNbzyjsxyActionForm actionForm = (XszzNbzyjsxyActionForm)form;
		SyjjModel model = new SyjjModel();
		BeanUtils.copyProperties(model, actionForm);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		model.setGuid(pkVal);
		XszzNbzyjsxyService service = new XszzNbzyjsxyService();
		boolean bJg = service.saveSyjjShxx(model, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getSyjjxxByGuid(pkVal);
		actionForm.setBjrsh(stuMap.get("bjrsh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));
		
		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "bjr");
			}
		}
		List<HashMap<String, String>> syjjList = null;
		syjjList = service.getSyjjList(stuMap.get("xh"));
		request.setAttribute("syjjList", syjjList);
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("syjjshSave");
	}
	
//	ҳ�����ֵ��ȡ
	public List<HashMap<String, String>> getParams(HttpServletRequest request) {
		Enumeration paramsEnum = request.getParameterNames();
		List<HashMap<String, String>> valueList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String[]> map_values = new HashMap<String, String[]>();
		int valueListSize = -1;
		while (paramsEnum.hasMoreElements()) {
			String param = (String) paramsEnum.nextElement();
			if (param.startsWith("_")) {
				String[] paramValue = request.getParameterValues(param);
				map_values.put(param.replace("_", ""), paramValue);
				valueListSize = paramValue.length;
			}
		}
		if (valueListSize > -1) {
			for (int i = 0; i < valueListSize; i++) {
				valueList.add(new HashMap<String, String>());
			}
			Set<String> keySet = map_values.keySet();
			for (String key : keySet) {
				String[] values = map_values.get(key);
				for (int i = 0; i < valueList.size(); i++) {
					HashMap<String, String> m = valueList.get(i);
					m.put(key, DealString.toGBK(values[i]));
				}
			}
		}
		return valueList;
	}
}
