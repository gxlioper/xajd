package xgxt.xszz.bjlhdx;

import java.util.HashMap;
import java.util.List;

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
 * <p>Description: �������ϴ�ѧѧ������Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-23</p>
 */
public class XszzAction extends BaseAction {

	/**
	 * ������ѧ������ҳ��
	 * gjzxjsq ----- ������ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzBjlhService service = new XszzBjlhService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("xn", Base.currXn);//��ǰѧ��
		request.setAttribute("sfksq", service.getGjzxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsq");
	}
	
	/**
	 * ���������ѧ��������Ϣ
	 * gjzxjsqSave ---- ���������ѧ��������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzBjlhActionForm);
		XszzBjlhService service = new XszzBjlhService();
		boolean bJg = service.saveGjzxjSqxx(gjzxjModel, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String xn = gjzxjModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// �õ���������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);//��ǰ���
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsqSave");
	}
	
	/**
	 * ������ѧ�������ҳ��
	 * gjzxjsqb ----- ������ѧ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		XszzBjlhService service = new XszzBjlhService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzBjlhActionForm);
		stuMap = service.getGjzxjSqb(gjzxjModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxjsqb");
	}
	
	/**
	 * ������ѧ�����ҳ��
	 * gjzxjsh ----- ������ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzBjlhService service = new XszzBjlhService();
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzBjlhActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzBjlhActionForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzBjlhActionForm.getXh());
		xszzBjlhActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzBjlhActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzBjlhActionForm.setXn(Base.currXn);
		}
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzBjlhActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_bjlhdx_gjzxj");
		request.setAttribute("tableName", "view_xszz_bjlhdx_gjzxj");
		return mapping.findForward("gjzxjsh");
	}
	
	/**
	 * ������ѧ����Ϣ����
	 * gjzxjExp ----- ������ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzBjlhService service = new XszzBjlhService();
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzBjlhActionForm);
		service.getGjzxjExp(queryModel, response,request);
		return mapping.findForward("gjzxjExp");
	}
	
	/**
	 * ������ѧ�������ϸҳ��
	 * gjzxjshOne ----- ������ѧ�������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzBjlhService service = new XszzBjlhService();
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		xszzBjlhActionForm.setXysh(stuMap.get("xysh"));
		xszzBjlhActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshOne");
	}
	
	/**
	 * ���������ѧ�������Ϣ
	 * gjzxjshSave ---- ���������ѧ�������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzBjlhActionForm);
		XszzBjlhService service = new XszzBjlhService();
		boolean bJg = service.saveGjzxjShxx(gjzxjModel, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String xn = gjzxjModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		xszzBjlhActionForm.setXysh(stuMap.get("xysh"));
		xszzBjlhActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}
}
