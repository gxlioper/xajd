package xgxt.wjcf.zjlg;

import java.lang.reflect.InvocationTargetException;
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

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.zjlg.ZjlgPjpyUnit;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.wjcf.zjcm.WjcfZjcmService;

public class WjcfZjlgAction extends CommonAction {
	
	private static final String SAVE = "save";

	/**
	 * Υ�ʹ������Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfshWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		zjlgForm.setXn(Base.currXn);
		zjlgForm.setNd(Base.currNd);
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if ("xy".equalsIgnoreCase(userType)) {
			zjlgForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		String operType = request.getParameter("operType");
		if ("query".equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			WjcfZjlgService service = new WjcfZjlgService();
			topTr = service.queryWjcfsbxxTitle();
			rs = service.queryWjcfsbxx(model, zjlgForm, userType);
			int count = service.queryWjcfsbxxNum(model, userType);
			zjlgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//���û���дȨ�����ж�
		request.setAttribute("path", "stuPunishAudit.do");
		unit.commonRequestSet(request, "", "",  rs, topTr);
		//����ѧԺ��רҵ���༶�����б�
		setNjXyZyBjList(request, zjlgForm);
		request.setAttribute("userType", userType);
		zjlgForm.setXm(DealString.toGBK(zjlgForm.getXm()));
		zjlgForm.setXh(DealString.toGBK(zjlgForm.getXh()));
		appendCflbCfyy(request);
		return mapping.findForward("cfshWh");
	}
	
	/**
	 * ������Ϣ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfxxDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String pkValue = request.getParameter("pkValue");
		WjcfZjlgService service = new WjcfZjlgService();
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String operType = request.getParameter("operType");
		if ("save".equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			boolean result = service.saveCfwhxx(pkValue, model, userType);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			
		}
		HashMap<String, String> rs = service.queryWjcfsbxxOne(pkValue);
		//����ͨ������������,���ؾ������ִ���ѧԺ�������
		if ("xy".equalsIgnoreCase(userType) && !checkCflbxx(rs.get("cflbmc"))) {
			request.setAttribute("writable", "no");
		}
		if ("��У�쿴".equalsIgnoreCase(rs.get("cflbmc"))) {
			request.setAttribute("lxck", "yes");
		}
		
		zjlgForm.setCfwh(rs.get("cfwh"));
		zjlgForm.setCfsj(rs.get("cfsj"));
		zjlgForm.setXxclyj(rs.get("xxclyj"));
		zjlgForm.setXyyj(rs.get("xyyj"));
		zjlgForm.setCflb(rs.get("cflb"));
		zjlgForm.setCfyy(rs.get("cfyy"));
		zjlgForm.setLxcksj(rs.get("lxcksj"));
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendCflbCfyy(request);
		return mapping.findForward("cfxxDgsh");
	}
	
	/**
	 * ѧԺֻ�����ͨ������������,���ؾ���
	 * @param cflbmc
	 * @return
	 */
	public boolean checkCflbxx(String cflbmc) {
		String[] cflbList = {"ͨ������", "����", "���ؾ���"};
		if (!StringUtils.isNull(cflbmc)) {
			for (String s : cflbList) {
				if (s.equalsIgnoreCase(cflbmc.trim())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * �����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfxxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String pkStr = request.getParameter("pkString");
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		WjcfZjlgService service = new WjcfZjlgService();
		String operType = request.getParameter("operType");
		if ("save".equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			boolean result = service.savePlCfwhxx(
					!StringUtils.isNull(pkStr) ? pkStr.split("!@")
							: new String[] {}, model, userType);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		zjlgForm.setCfsj(DealString.toGBK(zjlgForm.getCfsj()));
		zjlgForm.setCfwh(DealString.toGBK(zjlgForm.getCfwh()));
		zjlgForm.setXyyj(DealString.toGBK(zjlgForm.getXyyj()));
		zjlgForm.setXxclyj(DealString.toGBK(zjlgForm.getXxclyj()));
		request.setAttribute("pkString", pkStr);
		return mapping.findForward("cfxxPlsh");
	}
	
	/**
	 * ��У�쿴���ѹ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
			userType = "fdy";
		}
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String lxcksj = zjlgForm.getLxcksj();
		if (StringUtils.isNull(lxcksj)) {
			//lxcksj = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();
		}
		zjlgForm.setLxcksj(lxcksj);
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			zjlgForm.setXydm(userDep);
		}
		WjcfZjlgService service = new WjcfZjlgService();
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if (QUERY.equalsIgnoreCase(request.getParameter("act"))) {
			topTr = service.queryLxckcfxxTitle();
			rs = service.queryLxckxxResult(zjlgForm, userType, userName);
		}
		
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
			if (StringUtils.isNull(zjlgForm.getLxcksj())) {
				WjcfZjcmService zjcmService = new WjcfZjcmService();
				zjlgForm.setLxcksj(zjcmService.getSj());
			}
			if (("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) && !QUERY.equalsIgnoreCase(request.getParameter("act"))) {
				topTr = service.queryLxckcfxxTitle();
				rs = service.queryLxckxxResult(zjlgForm, userType, userName);
			}
		}
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		zjlgForm.setLxcksj(DealString.toGBK(zjlgForm.getLxcksj()));
		request.setAttribute("sj", StringUtils.isNotNull(zjlgForm.getLxcksj()) ? "" : zjlgForm.getLxcksj());
		return mapping.findForward("lxckCx");
	}
	
	/******************************************************
	 * ��У�쿴����ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 ******************************************************/
	public ActionForward lxckSjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		String xxdm = Base.xxdm;
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_xmlg_lxckDefault.do", false);
		}
		
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
					userType = "fdy";
				}
				String userName = request.getSession().getAttribute("userName").toString();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if ("xy".equalsIgnoreCase(userType)) {
			zjlgForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		String operType = request.getParameter("operType");
		if ("query".equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			WjcfZjlgService service = new WjcfZjlgService();
			topTr = service.queryLxckxxTitle();
			rs = service.queryLxckxxResult(zjlgForm, model, userType, userName);
			int count = service.queryLxckxxResultNum(userType, model, userName);
			zjlgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//���û���дȨ�����ж�
		request.setAttribute("path", "wjcf_zjlg_lxcksjwh.do");
		unit.commonRequestSet(request, "view_wjcf_zjlg_lxck", "wjcf_zjlg_lxckb",  rs, topTr);
		//����ѧԺ��רҵ���༶�����б�
		setNjXyZyBjList(request, zjlgForm);
		request.setAttribute("userType", userType);
		zjlgForm.setXm(DealString.toGBK(zjlgForm.getXm()));
		zjlgForm.setXh(DealString.toGBK(zjlgForm.getXh()));
		zjlgForm.setLxcksj(DealString.toGBK(zjlgForm.getLxcksj()));
		return mapping.findForward("lxckSjwh");
	}
	
	/**
	 * ������У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String pkValue = request.getParameter("pkValue");
		WjcfZjlgService service = new WjcfZjlgService();
		HashMap<String, String> rs = service.queryWjcfsbxxOne(pkValue);
		String operType = request.getParameter("operType");
		if ("save".equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			boolean result = service.saveLxcksqxx(model);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		String message = "��У�쿴<br/>�ڼ����";
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
			message = "��У�쿴�ڼ�<br/>ѧ������ǩ��";
		}
		
		request.setAttribute("rs", rs);
		//		����ѧԺ��רҵ���༶�����б�
		setNjXyZyBjList(request, zjlgForm);
		zjlgForm.setXsbx(DealString.toGBK(zjlgForm.getXsbx()));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("message", message);
		return mapping.findForward("addLxckxx");
	}
	public ActionForward addLxckxx2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String pkValue = request.getParameter("pkValue");
		WjcfZjlgService service = new WjcfZjlgService();
		HashMap<String, String> rs = service.queryWjcfsbxxOne(pkValue);
		String operType = request.getParameter("operType");
		if ("save".equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			boolean result = service.saveLxcksqxx(model);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		String message = "��У�쿴<br/>�ڼ����";
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
			message = "��У�쿴�ڼ�<br/>ѧ������ǩ��";
		}
		
		request.setAttribute("rs", rs);
		//		����ѧԺ��רҵ���༶�����б�
		setNjXyZyBjList(request, zjlgForm);
		zjlgForm.setXsbx(DealString.toGBK(zjlgForm.getXsbx()));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("message", message);
		return mapping.findForward("addLxckxx2");
	}
	
	/**
	 * ��ѯ��У�쿴����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckxxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		FormModleCommon.formToGBK(zjlgForm);
		String userType = request.getSession().getAttribute("userType").toString();
		if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
			userType = "fdy";
		}
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String lxcksj = zjlgForm.getLxcksj();
		if (StringUtils.isNull(lxcksj)) {
			//lxcksj = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();
		}
		zjlgForm.setLxcksj(lxcksj);
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			zjlgForm.setXydm(userDep);
		}
		WjcfZjlgService service = new WjcfZjlgService();
		List<HashMap<String, String>> topTr = service.queryLxckcfxxTitle();
		ArrayList<String[]> rs = service.queryLxckxxResultnotExis(zjlgForm,userType, userName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		zjlgForm.setLxcksj(DealString.toGBK(zjlgForm.getLxcksj()));
		return mapping.findForward("lxckxxQuery");
	}
	
	/********************************************
	 * �޸���У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 ********************************************/
	public ActionForward modiLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String operType = request.getParameter("operType");
		WjcfZjlgService service = new WjcfZjlgService();
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			boolean result = service.modiLxcksqxx(pkValue, model);
			appendOperResult(request, result);
		}
		String message = "��У�쿴<br/>�ڼ����";
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
			message = "��У�쿴�ڼ�<br/>ѧ������ǩ��";
		}
		request.setAttribute("message", message);
		HashMap<String, String> rs = service.viewLxcksqxx(pkValue);
		zjlgForm.setXn(rs.get("xn"));
		zjlgForm.setNd(rs.get("nd"));
		zjlgForm.setXsbx(rs.get("xsbx"));
		zjlgForm.setTqjcly(rs.get("tqjcly"));
		request.setAttribute("rs", rs);
		setNjXyZyBjList(request, zjlgForm);
		request.setAttribute("writable", operType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modiLxckxx");
	}
	
	/**
	 * ɾ����У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] pkValue = request.getParameterValues("cbv");
		WjcfZjlgService service = new WjcfZjlgService();
		boolean result = false;
		result = service.delLxcksqxx(pkValue);
		if (result) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
		lxckSjwh(mapping, form, request, response);
		return mapping.findForward("lxckSjwh");
	}
	
	/**
	 * ��У�쿴�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckSjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
					userType = "fdy";
				}
				String userName = request.getSession().getAttribute("userName").toString();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if ("xy".equalsIgnoreCase(userType)) {
			zjlgForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		
		String operType = request.getParameter("operType");
		if ("query".equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			WjcfZjlgService service = new WjcfZjlgService();
			topTr = service.queryLxcksqshxxTitle();
			rs = service.queryLxckshxxResult(userType, userName, zjlgForm, model);
			int count = service.queryLxckshxxResultNum(model, userType, userName);
			zjlgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//���û���дȨ�����ж�
		request.setAttribute("path", "wjcf_zjlg_lxcksjsh.do");
		unit.commonRequestSet(request, "view_wjcf_zjlg_lxck", "wjcf_zjlg_lxckb",  rs, topTr);
		//����ѧԺ��רҵ���༶�����б�
		setNjXyZyBjList(request, zjlgForm);
		request.setAttribute("userType", userType);
		zjlgForm.setXm(DealString.toGBK(zjlgForm.getXm()));
		zjlgForm.setXh(DealString.toGBK(zjlgForm.getXh()));
		zjlgForm.setLxcksj(DealString.toGBK(zjlgForm.getLxcksj()));
		return mapping.findForward("lxckSjsh");
	}
	
	/**
	 * ��У�쿴�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckxxDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		FormModleCommon.formToGBK(zjlgForm);
		String pkValue = request.getParameter("pkValue");

		String xxdm = Base.xxdm;
		//�������������
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_xmlg_lxckDgsh.do?pkValue=" + pkValue, false);
		}
		
		String userType = request.getSession().getAttribute("userType").toString();
		
		String operType = request.getParameter("operType");
		WjcfZjlgService service = new WjcfZjlgService();
		if (SAVE.equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			boolean result = service.saveLxckDgshxx(pkValue, userType, model);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		HashMap<String, String> rs = service.viewLxcksh(pkValue, userType);
		if ("xy".equalsIgnoreCase(userType)) {
			//ѧԺ�����ʱ����ѧУ�����ͨ���ľͲ����ٲ���
			if ("ͨ��".equalsIgnoreCase(rs.get("xxsh"))) {
				request.setAttribute("writable", "no");
			}
		}
		zjlgForm.setSh(rs.get("sh"));
		zjlgForm.setYj(rs.get("yj"));
		zjlgForm.setJcjg(rs.get("jcjg"));
		zjlgForm.setJcsj(rs.get("jcsj"));
		zjlgForm.setJcwh(rs.get("jcwh"));
		zjlgForm.setFdyjdyj(rs.get("fdyjdyj"));
		request.setAttribute("shList", service.loadShlist());
		request.setAttribute("jcList", service.loadJcList());
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userType);
		return mapping.findForward("lxckxxDgsh");
	}
	
	/**
	 * ���������У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plshLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjlgActionForm zjlgForm = (WjcfZjlgActionForm) form;
		FormModleCommon.formToGBK(zjlgForm);
		String pkValue = request.getParameter("pkString");
		String userType = request.getSession().getAttribute("userType").toString();
		String operType = request.getParameter("operType");
		WjcfZjlgService service = new WjcfZjlgService();
		
		String xxdm = Base.xxdm;
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_xmlg_plshLxckxx.do?pkString=" + pkValue, false);
		}
		
		if(SAVE.equalsIgnoreCase(operType)) {
			WjcfZjlgModel model = new WjcfZjlgModel();
			BeanUtils.copyProperties(model, zjlgForm);
			boolean result = service.savePlshLxckxx(userType, pkValue != null ? pkValue.split("!@") : null, model);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("userType", userType);
		request.setAttribute("pkString", pkValue);
		request.setAttribute("shList", service.loadShlist());
		request.setAttribute("jcList", service.loadJcList());
		return mapping.findForward("plshLxckxx");
	}
	
	/**
	 * ���ֱ��ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfbprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = request.getParameter("xh");
		String pkValue = request.getParameter("pkValue");
		//String cflb = request.getParameter("cflb");
		//String cfyy = request.getParameter("cfyy");
		WjcfZjlgService service = new WjcfZjlgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = CommonQueryDAO.getStuInfo(xh);
		}
		if (!StringUtils.isNull(pkValue)) {
			rs = service.getCfxx(pkValue);
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("cfbprint");
	}
	
	/**
	 * ��REQUEST�д�����ݲ�����Ľ��
	 * @param request
	 * @param result
	 */
	public void appendOperResult(HttpServletRequest request, boolean result) {
		if (result) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
	} 
	
	/**
	 * ����ѧԺ���꼶��רҵ���༶�����б�ֵ
	 * @param request
	 * @param myForm
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setNjXyZyBjList(HttpServletRequest request,
			WjcfZjlgActionForm myForm) throws Exception,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// ��request�����꼶ѧԺרҵ�༶List�ķ���
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(session.getAttribute("fdyQx")
						.toString())) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ���Ͱ༶�б�
		}

	}
}
