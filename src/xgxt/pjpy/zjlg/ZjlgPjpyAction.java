package xgxt.pjpy.zjlg;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.zjlg.dycj.DycjModel;
import xgxt.pjpy.zjlg.dycj.DycjService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import common.Globals;

public class ZjlgPjpyAction extends BasicAction {
	
	private static final String QUERY = "qry"; 

	/**
	 * @describe ��������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();

		String tableName = "";
		String realTable = "zjlg_pjpy_tjsz";
		String title = "";
		String userType = session.getAttribute("userType").toString();
		String szlx = request.getParameter("szlx");
		szlx = Base.isNull(szlx) ? "rych" : szlx;
		boolean result = false;

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();

		if ((request.getParameter("type") != null)
				&& request.getParameter("type").equalsIgnoreCase("save")) {
			// ѧ��
			String xn = myForm.getXn();
			// ��ѧ�����
			String jxjdm = "";
			if (!"xjbj".equalsIgnoreCase(szlx) && !"ylxfb".equalsIgnoreCase(szlx)) {
				jxjdm = myForm.getJxjdm();
			} else {
				jxjdm = "-";
			}
			// �ֶ���
			String zdm = myForm.getZdm();
			String tjz = myForm.getVal();
			if(zdm.equals("xstzjkbz")||zdm.equals("bjgl")){
			   tjz = myForm.getVal()+"%";
			}
			// ��������
			String tjlx = myForm.getTj();
			// ����ֵ
			
			// �Ƿ�����༶
			String sfyxbj = DealString.toGBK(myForm.getSfyxbj());
			tjz="".equalsIgnoreCase(sfyxbj)?tjz:sfyxbj;
			tjlx="".equalsIgnoreCase(sfyxbj)?tjlx:"=";
			// ����
			String tj = zdm + " " + tjlx + " '" + tjz+"'";
			result = service.saveTjsz(xn, szlx, jxjdm, zdm, tj, tjlx, tjz,
					request);
			myForm.setSfyxbj(sfyxbj);					
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}

		}
		if ((request.getParameter("type") != null)
				&& request.getParameter("type").equalsIgnoreCase("del")) {
			String pk = request.getParameter("pk");
			result = service.delTj(pk, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}

		}

		if ("rych".equalsIgnoreCase(szlx)) {
			title = "�������� - �������� - �����ƺ�";
		} else if ("xjbj".equalsIgnoreCase(szlx)) {
			title = "�������� - �������� - �Ƚ��༶";
		} else if ("jxj".equalsIgnoreCase(szlx)) {
			title = "�������� - �������� - ��ѧ��";
		} else if ("ylxfb".equalsIgnoreCase(szlx)) {
			title = "�������� - �������� - ����ѧ��༶";
		}
		BeanUtils.copyProperties(myModel, myForm);
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "szlx", "xn", "jxjdm",
					"tjzd", "szmc", "jxjmc", "tjmc", "tjlx", "tjz" };

			rs = service.getTjList(myModel, szlx, colList);

		}

		request.setAttribute("path", "prise_condition.do");
		unit.setNjXyZyBjTempValue(request, myForm);
		unit.setNjXyZyBjList(request, myForm);
		unit.setTj(request, myForm);
		unit.commonRequestSet(request, tableName, realTable,
				new ArrayList<String[]>(), new ArrayList<String[]>());

		request.setAttribute("title", title);
		request.setAttribute("szlx", szlx);
		request.setAttribute("rs", rs);
		request.setAttribute("zdList", service.getZdList(szlx));
		request.setAttribute("jxjlbList", service.getJxjlbList());
		if ("rych".equalsIgnoreCase(szlx)) {
			request.setAttribute("jxjList",service .serv_getRychList());
		}else{
			request.setAttribute("jxjList", service.getJxjList(""));
		}
		request.setAttribute("userType", userType);

		return mapping.findForward("pjpytjsz");
	}

	/**
	 * @describe ���������֡���ƽʱ��ά��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dycpfPsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String)session.getAttribute("userDep");

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();

//		String xxdm = StandardOperation.getXxdm();
//		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
//			return new ActionForward("/jhzy_pjpy_dyf.do", false);
//		}
		if (service.isBzrBj(userName)) {
			if (service.isDySz(userName, Base.currXn)) {
				return new ActionForward("/zjlgPjpy.do?method=dycpfPsfSz",
						false);
			}
		}

		String tableName = "view_zjlg_dypsf";
		String realTable = "zjlg_dypsf";
		String title = "�������� - �������� - ƽʱ��ά��";

		// ���������ڰ༶
		if (service.isBzrBj(userName)) {
			userType = "teacher";
		}

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(myModel, myForm);

		boolean result = false;

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

			if ("teacher".equalsIgnoreCase(userType)) {
				result = service.savePsf(myModel,userType);
			} else if ("xy".equalsIgnoreCase(userType)) {
				result = service.savePsfXyfjf(myModel);
			} else if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
				result = service.savePsf(myModel,userType);
			}
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"zwpyf", "bjpyf", "xyfjf", "xysh" };

			if (userType.equalsIgnoreCase("teacher")) {
				colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
						"zwpyf", "bjpyf", "xyfjf" };
			}
			if ("xy".equalsIgnoreCase(userType)) {
				myModel.setXydm(userDep);
			}
			topTr = service.getTopTr(tableName, colList);
			rs = service.getPsfList(myModel, myForm, userName, colList,
					userType);

		}

		request.setAttribute("path", "zjlgPjpy.do?method=dycpfPsf");
		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		unit.setNjXyZyBjTempValue(request, myForm);
		unit.setNjXyZyBjList(request, myForm);
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);

		return mapping.findForward("dycpfPsf");
	}

	/**
	 * @describe ���������֡���ƽʱ��ά������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dycpfPsfSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		String tableName = "";
		String realTable = "zjlg_dypsf_sz";
		String title = "�������� - �������� - ƽʱ�ֱ�������";
		String type = request.getParameter("type");

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(myModel, myForm);

		if (type != null && ("edit").equalsIgnoreCase(type)) {

			rs = service.getBlSz(Base.currXn, userName);
		}

		if (type != null && ("save").equalsIgnoreCase(type)) {
			boolean result = service.saveBlSz(Base.currXn, userName, myModel,
					request);
			if (result) {
				request.setAttribute("result", "yes");
				if (!"edit".equalsIgnoreCase(request.getParameter("doType"))) {
					return new ActionForward("/zjlgPjpy.do?method=dycpfPsf",
							false);
				}
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("title", title);
		request.setAttribute("type", type != null ? type : "");
		request.setAttribute("rs", rs);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("dycpfPsfSz");
	}

	/**
	 * 
	 * @describe ���������֡������ڷ�ά��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dycpfKqf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String)session.getAttribute("userDep");

		String tableName = "view_zjlg_dykqf";
		String realTable = "zjlg_dykqf";
		String title = "�������� - �������� - ���ڷ�ά��";

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();

		// ���������ڰ༶
		if (service.isBzrBj(userName)) {
			userType = "teacher";
		}

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(myModel, myForm);

		boolean result = false;

		if ((request.getParameter("type") != null)
				&& request.getParameter("type").equalsIgnoreCase("save")) {
			result = service.saveKqf(myModel);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go") || result) {
			String[] colList = new String[] { "xh", "xm", "xb", "zymc", "bjmc",
					"xn", "kqf" };

			if ("xy".equalsIgnoreCase(userType)) {
				myModel.setXydm(userDep);
			}
			topTr = service.getTopTr(tableName, colList);

			rs = service.getKqfList(myModel,myForm, userName, colList,userType);

			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		HashMap<String, String> map= service.getDyZgf(Base.currXn);
		request.setAttribute("path", "zjlgPjpy.do?method=dycpfPsf");
		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		unit.setNjXyZyBjTempValue(request, myForm);
		unit.setNjXyZyBjList(request, myForm);
		unit.setZdz(request, map);

		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);

		return mapping.findForward("dycpfKqf");

	}

	/**
	 * @describe ���������֡���������ά��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dycpfWsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String)session.getAttribute("userDep");

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();

		String tableName = "view_zjlg_dywsf";
		String realTable = "zjlg_dywsf";
		String title = "�������� - �������� - ������ά��";

		// ���������ڰ༶
		if (service.isBzrBj(userName)) {
			userType = "teacher";
		}

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(myModel, myForm);

		boolean result = false;

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {

			if ("teacher".equalsIgnoreCase(userType)) {
				result = service.saveWsf(myModel,userType);
			} else if ("xy".equalsIgnoreCase(userType)) {
				result = service.saveWsfXyfjf(myModel);
			} else if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
				result = service.saveWsf(myModel,userType);
			}
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"qsf", "xyfjf","isZds","xysh" };

			if (userType.equalsIgnoreCase("teacher")) {
				colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
						"qsf", "xyfjf","isZds" };
				myModel.setXydm(userDep);
			}

			topTr = service.getTopTr(tableName, colList);

			rs = service.getWsfList(myModel, myForm, userName, colList,userType);

		}

		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}
		HashMap<String, String> map= service.getDyZgf(Base.currXn);
		request.setAttribute("path", "zjlgPjpy.do?method=dycpfPsf");
		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		unit.setNjXyZyBjTempValue(request, myForm);
		unit.setNjXyZyBjList(request, myForm);
		unit.setZdz(request, map);
		if (rs != null && !"".equals(rs)) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);

		return mapping.findForward("dycpfWsf");
	}

	/**
	 * @describe ���������֡����ֲܷ鿴
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dycpfZf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String)session.getAttribute("userDep");

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();

		String tableName = "view_zjlg_dywsf";
		String realTable = "";
		String title = "�������� - �������� - �ֲܷ鿴";

		String xn = myForm.getXn();
		xn = (Base.isNull(xn)) ? Base.currXn : xn;

		List<HashMap<String, String>> rs = null;

		// ���������ڰ༶
		if (service.isBzrBj(userName)) {
			userType = "teacher";
		}

		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(myModel, myForm);

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go")) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"psf", "wsf", "kqf", "kf", "dycpf","psfcl","wsfcl","kqfcl" };

			if (service.isDySz(xn)) {
				topTr = service.getTopTr(tableName, colList);

				rs = service.getZfList(myModel, myForm, userName, colList,
						userType, userDep);
			} else {
				String msg = xn + "ѧ�껹δ�Ե����ֵĸ�����߷ֽ������ã��޷������ܷ�";
				request.setAttribute("msg", msg);
			}
		}

		HashMap<String, String> map= service.getDyZgf(xn);
		request.setAttribute("path", "zjlgPjpy.do?method=dycpfPsf");
		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		unit.setNjXyZyBjTempValue(request, myForm);
		unit.setNjXyZyBjList(request, myForm);
		unit.setZdz(request, map);
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("xnts", xn);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);

		return mapping.findForward("dycpfZf");
	}

	/**
	 * @describe ���������ֱ�������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dycpfDycpfSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "";
		String realTable = "zjlg_dycpf_sz";
		String title = "�������� - �������� - ��������������";
		String type = request.getParameter("type");

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(myModel, myForm);

		rs = service.getDyBlSz(Base.getJxjsqxn());

		if (type != null && ("save").equalsIgnoreCase(type)) {
			boolean result = service.saveDyBlSz(Base.getJxjsqxn(), myModel, request);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("title", title);
		request.setAttribute("type", type != null ? type : "");
		request.setAttribute("rs", rs);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("dycpfDycpfSz");
	}

	/**
	 * 
	 * @describe �ۺ����ʲ���
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zhszcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String isBzr = session.getAttribute("bzrQx").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_zjlg_zhszcpf";
		String realTable = "zjlg_zhszcp";
		String title = "�������� - �ۺ����ʲ��� - �鿴";

		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
			return new ActionForward("/jhzy_pjpy_zhf.do", false);
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {
			return new ActionForward("/zjcm_pjpy_zhf.do", false);
		}
		
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();
		String xn = myForm.getXn();
		xn = Base.isNull(xn) ? Base.currXn : xn;
		myForm.setXn(xn);
		// ���������ڰ༶
		if (service.isBzrBj(userName)) {
			userType = "teacher";
		}

		List<HashMap<String, String>>  rs = null;
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(myModel, myForm);

		boolean result = false;

		if ((request.getParameter("type") != null)
				&& request.getParameter("type").equalsIgnoreCase("save")) {
			if (service.isMaxDy(myModel.getXn())) {
				result = service.saveZhszcpf(myModel);
				if (result) {
					request.setAttribute("result", "yes");
				} else {
					request.setAttribute("result", "no");
				}
			} else {
				String msg = xn + "ѧ��ĵ��������ָ�����߷ֻ�δ���ã��޷����м��㣬��ȷ��!!";
				request.setAttribute("msg", msg);
			}
		}

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go") || result) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj","zymc", "bjmc",
					"dycpf", "zycpf","tycj","zhcpf","pm" };

			topTr = service.getTopTr(tableName, colList);

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("pm", "�۲�����");
			topTr.add(map);
			rs = service.getZhszcpfList(myModel,myForm, userName, colList,userType, isBzr);

			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		unit.commonRequestSet(request, tableName, realTable,  new ArrayList<String[]>(), topTr);
		unit.setNjXyZyBjTempValue(request, myForm);
		unit.setNjXyZyBjList(request, myForm);
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		//	��øò�����
		if("xy".equalsIgnoreCase(userType)){
			myModel.setXydm(userDep);
		}
		request.setAttribute("cpzList",service.getCpzList(myModel));

		return mapping.findForward("zhszcp");

	}

	/**
	 * @describe �ۺ����ʲ�����������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zhszcpSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "";
		String realTable = "zjlg_zhszcp_sz";
		String title = "�������� - �ۺ����ʲ��� - ��������";
		String type = request.getParameter("type");

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(myModel, myForm);

		if (type != null && ("edit").equalsIgnoreCase(type)) {

			rs = service.getZhBlSz(Base.getJxjsqxn());
		}

		if (type != null && ("save").equalsIgnoreCase(type)) {
			boolean result = service.saveZhBlSz(Base.getJxjsqxn(), myModel, request);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("title", title);
		request.setAttribute("type", type != null ? type : "");
		request.setAttribute("rs", rs);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("zhszcpSz");
	}

	/**
	 * @describe �ۺ����ʲ������������ӡ
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zhszcpPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

//		String xn = request.getParameter("xn");
//		String zydm = request.getParameter("zydm");
//		String bjdm = request.getParameter("bjdm");
//		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		DycjModel model = new DycjModel();
		model.setXn(myForm.getQueryequals_xn());
		model.setNj(myForm.getQueryequals_nj());
		model.setXydm(myForm.getQueryequals_xydm());
		model.setZydm(myForm.getQueryequals_zydm());
		model.setBjdm(myForm.getQueryequals_bjdm());
		model.setXh(myForm.getQuerylike_xh());
		model.setXm(myForm.getQuerylike_xm());
		model.setCpzdm(myForm.getQueryequals_cpzdm());
		String modelPath = servlet.getServletContext().getRealPath("")
		+ "/print/zjlg_zhszcp.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		//service.printZhszcp(wwb, xn, zydm, bjdm);

		DycjService service = new DycjService();
		service.exprotZhszcpinfo(wwb, model, request.getSession().getAttribute(
				"bzrQx").toString(), request.getSession().getAttribute(
				"userName").toString());
		return mapping.findForward("");
	}

	/**
	 * @describe �������á����������ã�������λͨ���������ã�
	 * @author luning
	 * @throws Exception
	 * @bz ��ȡѡ�����3����ģ�飨�������ţ������ƺţ��Ƚ��༶��	
	 */

	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String title = request.getParameter("jmsxPurview");
		String realTable = "";
		String xxdm = request.getSession().getAttribute("xxdm").toString();
		ArrayList<String[]> rs =new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String path = "prise_conf_rs.do";
		request.setAttribute("path", path);

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		ZjlgPjpyUnit myUnit  =  new ZjlgPjpyUnit();
		myUnit.setFormForXnNdXqNj(myForm);
		myUnit.setNjXyZyBjList(request,myForm);

		BeanUtils.copyProperties(myModel, myForm);
		//ѡ��ж�
		String xxk       = request.getParameter("xxk");
		if(xxk==null){
			
			if (xxdm.equals(Globals.XXDM_GUIZHDX)) {
				xxk = "xjbj";
			} else {
				xxk = "jxj";
			}
			
		}
		
		if (xxdm.equals(Globals.XXDM_GUIZHDX)) {
			request.setAttribute("path", "pjpy_rssz.do");
		}
		
		if (request.getParameter("go") != null&&!request.getParameter("go").equalsIgnoreCase("")){
			if(xxk.equalsIgnoreCase("jxj")){
				//��ѧ������
				realTable = "zjlg_xyjxjrs";
				String bmlb = DealString.toGBK(myModel.getBmlb());
				rs = service.getJxjRsList(myModel,myForm);
				topTr = service.getJxjRsTopTr(bmlb);
			}else if(xxk.equalsIgnoreCase("rych")){
				//�����ƺ�����
				String bmlb = DealString.toGBK(myModel.getBmlb());
				realTable = "zjlg_xyrychrs";
				rs = service.getRychRsList(myModel,myForm);
				topTr = service.getRychRsTopTr(bmlb);
			}else if(xxk.equalsIgnoreCase("xjbj")){
				//�Ƚ��༶
				realTable = "zjlg_xyxjbjmeb";
				rs = service.getXjbjRsList(myModel,myForm);
				topTr = service.getXjbjRsTopTr();
			}
		}

		//��øò�����
		request.setAttribute("cpzList",service.getCpzList(myModel));

		myUnit.commonRequestSet(request, "", realTable, rs, topTr);

		if(xxk.equalsIgnoreCase("jxj")){
			request.setAttribute("jxjList", service.getJxjList(""));
			return mapping.findForward("jxjCssz");
		}else if(xxk.equalsIgnoreCase("rych")){
			request.setAttribute("rychList", service.serv_getRychList());
			return mapping.findForward("rychCssz");
		}else{
			return mapping.findForward("xjbjCssz");
		} 
	}

	public ActionForward cpzhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session =request.getSession();

		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyUnit myUnit  =  new ZjlgPjpyUnit();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		myUnit.setFormForXnNdXqNj(myForm);
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();


		//��øò���������Ӧ�༶
		request.setAttribute("cpzBjList",service.getCpzBjList(myModel));
		request.setAttribute("xn",myForm.getXn());

		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		BeanUtils.copyProperties(myModel, myForm);

		//��øò�����
		request.setAttribute("cpzList",service.getCpzList(myModel));
		//���δ����༶�б�
		request.setAttribute("wfpBjList",service.getWfpBjList(myModel));	
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));

		return mapping.findForward("cpzhf");
	}

	/**
	 * @describe ����������
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward addCpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		boolean inserted = service.addCpz(request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return cpzhf(mapping,form,request,response);
	}

	/**
	 * @describe ����������
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward delCpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		boolean inserted = service.delCpz(request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return cpzhf(mapping,form,request,response);
	}

	/**
	 * @describe �����黮�ֱ���
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward saveCpzFp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();

		boolean inserted = service.saveCpzFp(request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return cpzhf(mapping,form,request,response);
	}

	/**
	 * @describe ��ѧ���ʼ��
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward jxjcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		boolean update = service.jxjcsh();
		if (update) {
			request.setAttribute("initOK", "ok");
		} else {
			request.setAttribute("initOK", "no");
		}
		return cssz(mapping,form,request,response);
	}
	/**
	 * @describe �������á����������ñ���
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward plszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		if(userType.equalsIgnoreCase("xy")){
			myForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(myModel, myForm);
		boolean updated = service.plszSave(myModel,userType);
		if (updated) {
			request.setAttribute("updateOK", "ok");
		} else {
			request.setAttribute("updateOK", "no");
		}
		String message = service.getJxjRstxPlsz(myModel);
		if(message!=null){
			request.setAttribute("message", message);
		}
		return cssz(mapping,form,request,response);
	}
	/**
	 * @describe �������á���������������
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward saveJxjRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		String pk = request.getParameter("pk");
		BeanUtils.copyProperties(myModel, myForm);
		boolean updated = service.saveJxjRs(pk,myModel,request);
		if (updated) {
			request.setAttribute("updateOK", "ok");
		} else {
			request.setAttribute("updateOK", "no");
		}
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep  = (String)session.getAttribute("userDep");
		if(userType.equalsIgnoreCase("xy")){
			myModel.setXydm(userDep);
		}
		String message = service.getJxjRstxOne(myModel);
		if(message!=null){
			request.setAttribute("message", message);
		}
		return cssz(mapping,form,request,response);
	}

	/**
	 * @describe �������á���������ѧ��ѧ��
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward tzjxjxn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		String jxjxn = request.getParameter("jxjxn");
		boolean updated = service.saveJxjXn(jxjxn,request);
		if (updated) {
			myForm.setXn(jxjxn);
			request.setAttribute("updateOK", "ok");
		} else {
			request.setAttribute("updateOK", "no");
		}
		
		// ================2010/7/6 edit by luojw=========================
		String mklx = request.getParameter("mklx");
		String url = "/guizdxPjpy.do?method=rsszXxManage&mklx=";
		if ("jxj".equalsIgnoreCase(mklx)) {// ��ѧ��
			url += mklx;
		} else if ("rych".equalsIgnoreCase(mklx)) {// �����ƺ�
			url += mklx;
		} else {

		}
		if (!Base.isNull(mklx)) {
			return new ActionForward(url, false);
		}
		// ================end=========================
		return cssz(mapping,form,request,response);
	}

	/**
	 * @describe �������á����Ƚ��༶��������
	 * @author luning
	 * @throws Exception
	 */

	public ActionForward xjbjPlszSave (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		BeanUtils.copyProperties(myModel, myForm);
		boolean updated = service.xjbjPlszSave(myModel);
		if (updated) {
			request.setAttribute("updateOK", "ok");
		} else {
			request.setAttribute("updateOK", "no");
		}
		return cssz(mapping,form,request,response);
	}


	/**
	 * @describe �������á����Ƚ��༶������������
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward saveXjbjMe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		String pk = request.getParameter("pk");
		BeanUtils.copyProperties(myModel, myForm);
		boolean updated = service.saveXjbjMe(pk,myModel,request);
		if (updated) {
			request.setAttribute("updateOK", "ok");
		} else {
			request.setAttribute("updateOK", "no");
		}
		return cssz(mapping,form,request,response);
	}

	/**
	 * @describe �����ƺų�ʼ��
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward rychcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		boolean update = service.rychcsh();
		if (update) {
			request.setAttribute("initOK", "ok");
		} else {
			request.setAttribute("initOK", "no");
		}
		return cssz(mapping,form,request,response);
	}
	/**
	 * @describe �������á����������ñ���
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward rychPlszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		BeanUtils.copyProperties(myModel, myForm);
		boolean updated = service.rychPlszSave(myModel);
		if (updated) {
			request.setAttribute("updateOK", "ok");
		} else {
			request.setAttribute("updateOK", "no");
		}
		return cssz(mapping,form,request,response);
	}
	/**
	 * �����ƺ�����(ѧ��)
	 * @throws Exception 
	 */
	public ActionForward rychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String xh  = request.getParameter("xh");
		String doType = request.getParameter("doType");	
		HashMap<String,String> map = new HashMap<String, String>();
		HashMap<String,String> stuMap =  new HashMap<String, String>();
		HashMap<String,String> stuCjMap = new HashMap<String, String>();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		RychModel   model   = new RychModel();
		ZjlgPjpyModel pjpyModel = new ZjlgPjpyModel();
		if("student".equalsIgnoreCase(userOnline)){
			xh = userName;
		}
		if (StringUtils.isNotNull(xh)) {			
			stuMap = service.serv_getXsInfo(xh);//��ȡѧ����ػ�����Ϣ
		}
		myForm.setBz(DealString.toGBK(myForm.getBz()));//


		map.put("xn",Base.getJxjsqxn());//��ѧ������ѧ��
		map.put("xq",Base.getJxjsqxq());//��ѧ������ѧ��

		//���ݸ���ѧ����ͳ�Ƹð༶����˵��רҵ�������ɼ�
//		String zydm = stuMap.get("zydm");
		String xn   = Base.getJxjsqxn();	
//		String bjdm = stuMap.get("bjdm");
		String zpzdm = service.getCpzdmForXh(xh,xn);//���������
		pjpyModel.setCpzdm(zpzdm);
		if (StringUtils.isNotNull(xh)) {			
			stuCjMap = service.getStuCjForXh(pjpyModel,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
		}
		map.put("zcbjpm",stuCjMap.get("pm"));//�ۺϲ����༶����
		map.put("dycj",stuCjMap.get("dycpf"));//�����ɼ�
		map.put("zycj",stuCjMap.get("zycpf"));//�����ɼ�
		map.put("zhcj",stuCjMap.get("zhcpf"));//�ۺϲ����ɼ�
		map.put("dypm",stuCjMap.get("dypm"));//��������
		map.put("zypm",stuCjMap.get("zypm"));//��������
		map.put("tycj",service.serv_getTyCj(xh, xn));//�����ɼ�
		if("save".equalsIgnoreCase(doType)){//���ݱ���
			BeanUtils.copyProperties(model, myForm);
			HashMap<String,String> isExist =service.serv_rychSqPd(model); 
			if(isExist.size()==0||isExist==null){//�жϸü�¼�Ƿ���������ͨ��
				//�жϸ����Ƿ�������������
				if(service.rychSqTjPd(model.getRychdm(), xh, xn, zpzdm, Base.isNull(stuCjMap.get("dycpf"))?"0":stuCjMap.get("dycpf"),
						Base.isNull(stuCjMap.get("zycpf"))?"0":stuCjMap.get("zycpf"), Base.isNull(stuCjMap.get("zhcpf"))?"0":stuCjMap.get("zhcpf"),
								Base.isNull(map.get("tycj"))?"0":map.get("tycj"))){
					boolean done = false;
					done = service.serv_rychSave(model);
					request.setAttribute("done",done);
				}else{
					request.setAttribute("pass","no");
				}								
			}else{
				request.setAttribute("isExist","no");
			}
		}
		request.setAttribute("rychList",service.serv_getRychList());//�����ƺ��б�
		request.setAttribute("rs",stuMap);
		request.setAttribute("rsOther",map);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("rychSq");
	}
	/**
	 * �����ƺ������ѯ��ҳ
	 * @throws InvocationTargetException 
	 * @throws Exception 
	 */
	public ActionForward rychDefault (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		RychModel   model       = new RychModel();
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit();  
		String userName = request.getSession().getAttribute("userName").toString();		
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userType  = request.getSession().getAttribute("userType").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		if(myForm.getXn()==null){
			myForm.setXn(Base.getJxjsqxn());//��ѧ��ѧ��
		}
		if("admin".equalsIgnoreCase(userType)||"xx".equalsIgnoreCase(userType)){
			userType = "xx";
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		ArrayList<HashMap<String, String>> topTr = null;
		ArrayList<String[]>  rs = null;
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, myForm);
			topTr = service.serv_getRychQerrTitle();
			rs    = service.serv_rychDefault(model);
		}		
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);

		//initList(request);//��ѯ����
		pjpyUnit.setNjXyZyBjList(request, myForm);

		//request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("rychList",service.serv_getRychList());//�����ƺ��б�
		request.setAttribute("userType",userType);
		request.setAttribute("tableName","view_zjlg_xsrychxx");
		request.setAttribute("realTable","zjlg_xsrychb");
		//��дȨ�ж�		 			
		request.setAttribute("writeAble",(Base.chkUPower(userName,"credit_result.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("rychDefault");
	}
	/**
	 * �����ƺ�����
	 * @throws Exception 
	 */
	public ActionForward rychAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		String xh  = request.getParameter("xh");
		HashMap<String,String> map = new HashMap<String, String>();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		RychModel   model   = new RychModel();
		ZjlgPjpyModel pjpyModel = new ZjlgPjpyModel();
		myForm.setBz(DealString.toGBK(myForm.getBz()));//
//		HashMap<String,String> stuMap = service.serv_getXsInfo(xh);//��ȡѧ����ػ�����Ϣ
		HashMap<String,String> stuCjMap = new HashMap<String, String>();

		String doType = request.getParameter("doType");

		map.put("xn",Base.getJxjsqxn());//��ѧ������ѧ��
		map.put("xq",Base.getJxjsqxq());//��ѧ������ѧ��
		//���ݸ���ѧ����ͳ�Ƹð༶�����������ɼ�
//		String bjdm = stuMap.get("bjdm");
		String xn   = Base.getJxjsqxn();	

		stuCjMap = service.getStuCjForXh(pjpyModel,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
		map.put("zcbjpm",stuCjMap.get("pm"));//�ۺϲ����༶����
		map.put("dycj",stuCjMap.get("dycpf"));//�����ɼ�
		map.put("zycj",stuCjMap.get("zycpf"));//�����ɼ�
		map.put("zhcj",stuCjMap.get("zhcpf"));//�ۺϲ����ɼ�
		map.put("dypm",stuCjMap.get("dypm"));//��������
		map.put("zypm",stuCjMap.get("zypm"));//��������
		map.put("tycj",service.serv_getTyCj(xh, xn));//�����ɼ�
		String zpzdm = service.getCpzdmForXh(xh,xn);//���������
		if("save".equalsIgnoreCase(doType)){//���ݱ���
			BeanUtils.copyProperties(model, myForm);
			//HashMap<String,String> isExist =service.serv_rychSqPd(model);     		
			//�жϸ����Ƿ�������������
			if(service.rychSqTjPd(model.getRychdm(), xh, xn, zpzdm, Base.isNull(stuCjMap.get("dycpf"))?"0":stuCjMap.get("dycpf"),
					Base.isNull(stuCjMap.get("zycpf"))?"0":stuCjMap.get("zycpf"), Base.isNull(stuCjMap.get("zhcpf"))?"0":stuCjMap.get("zhcpf"),
							Base.isNull(map.get("tycj"))?"0":map.get("tycj"))){
				boolean done = false;
				done = service.serv_rychAddSave(model);
				request.setAttribute("done",done); 
			}else{
				request.setAttribute("pass","no");
			}
		}
		request.setAttribute("rychList",service.serv_getRychList());//�����ƺ��б�
		request.setAttribute("rs",service.serv_getXsInfo(xh));//��ȡѧ����ػ�����Ϣ
		request.setAttribute("rsOther",map);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());    	
		return mapping.findForward("rychAdd");
	}
	/**
	 * ѧ�������ƺ���Ϣ�޸�
	 * @throws Exception 
	 */
	public ActionForward rychModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		String xh  = "";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String act    = request.getParameter("act");
		HashMap<String,String> rychXsXx = service.serv_getRychxxForXh(pkValue);//ͨ��pkValue��ø��������ƺ���Ϣ
		xh = rychXsXx.get("xh");		
		HashMap<String,String> map = new HashMap<String, String>();
		HashMap<String,String> stuCjMap = new HashMap<String, String>();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		RychModel   model   = new RychModel();
		ZjlgPjpyModel pjpyModel = new ZjlgPjpyModel();

		myForm.setBz(DealString.toGBK(myForm.getBz()));//		

//		String bjdm = rychXsXx.get("bjdm");
		String xn   = rychXsXx.get("xn");			
		stuCjMap = service.getStuCjForXh(pjpyModel,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ
		String zpzdm = service.getCpzdmForXh(xh,xn);
		if("save".equalsIgnoreCase(doType)){//���ݱ���
			BeanUtils.copyProperties(model, myForm);
			//�жϸ����Ƿ�������������
			if(service.rychSqTjPd(model.getRychdm(), xh, xn, zpzdm, Base.isNull(stuCjMap.get("dycpf"))?"0":stuCjMap.get("dycpf"),
					Base.isNull(stuCjMap.get("zycpf"))?"0":stuCjMap.get("zycpf"), Base.isNull(stuCjMap.get("zhcpf"))?"0":stuCjMap.get("zhcpf"),
							Base.isNull(map.get("tycj"))?"0":map.get("tycj"))){
				boolean done = false;
				done = service.serv_rychModi(model,pkValue);     	
				request.setAttribute("done",done);  
			}else{
				request.setAttribute("pass","no");
			}
		}
		map.put("xn",xn);//��ѧ������ѧ��
		map.put("xq",rychXsXx.get("xq"));//��ѧ������ѧ��
		map.put("rychdm", rychXsXx.get("rychdm"));//�����ƺŴ���
		map.put("bz",rychXsXx.get("bz"));//�����ƺŴ���
//		���ݸ���ѧ����ͳ�Ƹð༶�����������ɼ�
		HashMap<String,String> mapCj =service.getStuCjForXh(pjpyModel,rychXsXx.get("xn"),xh);		
		map.put("zcbjpm",mapCj.get("pm"));//�ۺϲ����༶����
		map.put("dycj",mapCj.get("dycpf"));//�����ɼ�
		map.put("zycj",mapCj.get("zycpf"));//�����ɼ�
		map.put("zhcj",mapCj.get("zhcpf"));//�ۺϲ����ɼ�
		map.put("dypm",mapCj.get("dypm"));//��������
		map.put("zypm",mapCj.get("zypm"));//��������
		map.put("tycj",service.serv_getTyCj(xh, xn));//�����ɼ�

		request.setAttribute("rychList",service.serv_getRychList());//�����ƺ��б�
		request.setAttribute("rs",service.serv_getXsInfo(xh));//��ȡѧ����ػ�����Ϣ
		request.setAttribute("rsOther",map);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList()); 
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("act",act);
		return mapping.findForward("rychModi");
	}
	/**
	 * ѧ�������ƺ���Ϣɾ��
	 * @throws Exception 
	 */
	public ActionForward rychDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String delPk = request.getParameter("delPk");
		ZjlgPjpyService service = new ZjlgPjpyService();
		service.serv_rychDelete(delPk);
		return rychDefault(mapping,form,request,response);
	}
	/**
	 * �����ƺ���˲�ѯ��ҳ
	 */
	public ActionForward rychCkDefault (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Ժϵ��� �����������ƣ�ѧУ��˲����ơ����༶��ѧ���۲�ɼ��ߵ�������
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		RychModel model = new RychModel();
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit(); 
		String userName = request.getSession().getAttribute("userName").toString();		
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userType  = request.getSession().getAttribute("userType").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		myForm.setXn(Base.getJxjsqxn());//��ѧ��ѧ��
		myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));//���״̬����ת��
		myForm.setXh(DealString.toGBK(myForm.getXh()));//ѧ������ת��
		myForm.setXm(DealString.toGBK(myForm.getXm()));//��������ת��
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}		
		if("admin".equalsIgnoreCase(userType)||"xx".equalsIgnoreCase(userType)){
			userType = "xx";
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		ArrayList<HashMap<String, String>> topTr = null;
		ArrayList<String[]>  rs = null;
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, myForm);
			topTr = service.serv_getRychCkQerrTitle();
			rs    = service.serv_rychCkDefault(model,userType);
		}		
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);

//		initList(request);//��ѯ����
//		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		pjpyUnit.setNjXyZyBjList(request, myForm);
		request.setAttribute("rychList",service.serv_getRychList());//�����ƺ��б�
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("userType",userType);
//		��дȨ�ж�		 			
		request.setAttribute("writeAble",(Base.chkUPower(userName,"credit_check.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("rychCkDefault");
	}
	/**
	 * �����ƺ���˲鿴
	 * @throws Exception 
	 */
	public ActionForward rychCkView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		String xh  = "";
		String pkValue = request.getParameter("pkValue");
		String view = request.getParameter("view");
		String select = request.getParameter("select");
		HashMap<String,String> rychXsXx = service.serv_getRychxxForXh(pkValue);//ͨ��pkValue��ø��������ƺ���Ϣ
		xh = rychXsXx.get("xh");
		ZjlgPjpyModel pjpyModel = new ZjlgPjpyModel();
		HashMap<String,String> map = new HashMap<String, String>();		
		map.put("xn",rychXsXx.get("xn"));//��ѧ������ѧ��
		map.put("xq",rychXsXx.get("xq"));//��ѧ������ѧ��
		map.put("rychmc",rychXsXx.get("rychmc"));//�����ƺ�����
		map.put("bz",rychXsXx.get("bz"));//�����ƺŴ���
//		���ݸ���ѧ����ͳ�Ƹð༶�����������ɼ�	
		HashMap<String,String> mapCj = service.getStuCjForXh(pjpyModel,rychXsXx.get("xn"),xh);
		map.put("zcbjpm",mapCj.get("pm"));//�ۺϲ����༶����
		map.put("dycj",mapCj.get("dycpf"));//�����ɼ�
		map.put("zycj",mapCj.get("zycpf"));//�����ɼ�
		map.put("zhcj",mapCj.get("zhcpf"));//�ۺϲ����ɼ�
		map.put("dypm",mapCj.get("dypm"));//��������
		map.put("zypm",mapCj.get("zypm"));//��������
		map.put("bjg",mapCj.get("bjg"));//������γ���
		map.put("tycj",service.serv_getTyCj(xh, rychXsXx.get("xn")));//�����ɼ�
		HashMap<String,String> stuDjMap = new HashMap<String, String>();
		stuDjMap = service.getStuDjForXh(rychXsXx.get("xn"),xh);//��ȡѧ����ػ����ɼ���Ϣ
		map.put("yydjcj", stuDjMap.get("yydjcj"));
		map.put("jsjdjcj", stuDjMap.get("jsjdjcj"));
		//HashMap<String,String> mapDj = service.getStuDjForXh(pjpyModel,xh);
		
		
		String xycprs= DealString.toString(service.getRychMe("xydm",rychXsXx.get("xydm"),rychXsXx.get("rychdm"),rychXsXx.get("xn")));
		String zycprs= DealString.toString(service.getRychMe("zydm",rychXsXx.get("zydm"),rychXsXx.get("rychdm"),rychXsXx.get("xn")));
		String bjcprs= DealString.toString(service.getRychMe("bjdm",rychXsXx.get("bjdm"),rychXsXx.get("rychdm"),rychXsXx.get("xn")));
		String allPassRs=DealString.toString(service.serv_getRychPssRs(rychXsXx.get("rychdm"),rychXsXx.get("xn"),"","",""));
		String xyPassRs= DealString.toString(service.serv_getRychPssRs(rychXsXx.get("rychdm"),rychXsXx.get("xn"),rychXsXx.get("xydm"),"",""));
		String zyPassRs= DealString.toString(service.serv_getRychPssRs(rychXsXx.get("rychdm"),rychXsXx.get("xn"),"",rychXsXx.get("zydm"),""));
		String bjPassRs= DealString.toString(service.serv_getRychPssRs(rychXsXx.get("rychdm"),rychXsXx.get("xn"),"","",rychXsXx.get("bjdm")));
		request.setAttribute("xycprs",Base.isNull(xycprs)?"0":xycprs);//ѧԺ��������
		request.setAttribute("zycprs",Base.isNull(zycprs)?"0":zycprs);//רҵ��������
		request.setAttribute("bjcprs",Base.isNull(bjcprs)?"0":bjcprs);//�༶��������		
		request.setAttribute("allPassRs",Base.isNull(allPassRs)?"0":allPassRs);//ȫУͨ������
		request.setAttribute("xyPassRs",Base.isNull(xyPassRs)?"0":xyPassRs);//ѧԺͨ������
		request.setAttribute("zyPassRs",Base.isNull(zyPassRs)?"0":xyPassRs);//רҵͨ������
		request.setAttribute("bjPassRs",Base.isNull(bjPassRs)?"0":xyPassRs);//�༶ͨ������			
		request.setAttribute("rs",service.serv_getXsInfo(xh));//��ȡѧ����ػ�����Ϣ
		request.setAttribute("rhJxjList",service.serv_getRhJxjList(xh));//�ٻ�ѧ��
		request.setAttribute("rsOther",map);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList()); 
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("view",view);
		request.setAttribute("select",select);
		request.setAttribute("sel",request.getParameter("sel"));
		return mapping.findForward("rychCkView");
	}
	/**
	 * �����ƺ����
	 */
	public ActionForward rychCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkVStr = request.getParameter("pkVStr");
		ZjlgPjpyService service = new ZjlgPjpyService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String xydm = "";
		String rychdm = request.getParameter("rychdm");
		String xn     = Base.getJxjsqxn();
		String check = request.getParameter("check");
		String shfs  = request.getParameter("shfs");
		String zydm  = request.getParameter("zydm");
		String bjdm  = request.getParameter("bjdm");
		if("xy".equalsIgnoreCase(userType)){//Ժϵ�û����ʱ��������������ƺ������ƿ���
			String str = "";
			String bmlb ="";
			String bmdm = "";
			xydm = request.getSession().getAttribute("userDep").toString();	
			if("zyfs".equalsIgnoreCase(shfs)){
				str = "רҵ";
				bmlb = "zydm";
				bmdm = zydm;
			}else if("bjfs".equalsIgnoreCase(shfs)){
				str = "�༶";
				bmlb = "bjdm";
				bmdm = bjdm;
			}else{
				str = "ѧԺ";
				bmlb = "xydm";
				bmdm = xydm;
			}					
			if("yes".equalsIgnoreCase(check)){//���ͨ��ʱ�����ƿ���
				if(service.serv_rychCkRsXd(rychdm, xn, xydm, shfs, zydm, bjdm, pkVStr)){				
					check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
					service.serv_rychCk(pkVStr, userType, check);
				}else{
					request.setAttribute("pass","no");
				}
			}else{
				check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
				service.serv_rychCk(pkVStr, userType, check);
			}
			request.setAttribute("clin","�������ƺ����ͨ�����������ѳ�����ѧ�ꡢ��"+str+"���ޣ�"+service.getRychMe(bmlb, bmdm, rychdm, xn)+"��");			
		}else{	
			check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
			service.serv_rychCk(pkVStr, userType, check);
		}
		//boolean bools = service.serv_JxjSqCk(buff.toString(), userType, check,shyj,"��ѧ��",userName);
		boolean bools = service.serv_audit(pkVStr, userType, check, "", "�����ƺ�����", userName,"zjlg_xsrychb");
		return rychCkDefault(mapping,form,request,response);
	}
	/**
	 * �Ƚ��༶����
	 */
	public ActionForward xjbjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userType = request.getSession().getAttribute("userType").toString(); 
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit(); 
		String xn = Base.getJxjsqxn();
		String doType  = request.getParameter("doType");
		String bjdm    = myForm.getBjdm();//�༶����	
		myForm.setXn(xn);
		myForm.setBjqk(DealString.toGBK(myForm.getBjqk()));	
		myForm.setSfyxbj(DealString.toGBK(myForm.getSfyxbj()));
		if("save".equalsIgnoreCase(doType)){
			XjbjModel model = new XjbjModel();
			BeanUtils.copyProperties(model,myForm); 
			if(service.xjbjSqTjPd(bjdm, model.getXn(),Base.isNull(model.getWmqsgs())?"0":model.getWmqsgs(),
					Base.isNull(model.getAjqsgs())?"0":model.getAjqsgs(),model.getSfyxbj())){
				boolean done = false;
				done = service.serv_xjbjsqSave(model);
				request.setAttribute("done",done);
			}else{
				request.setAttribute("pass","no");
			}
		}
		//ҳ����������		
		pjpyUnit.setNjXyZyBjList(request, myForm);
		request.setAttribute("bjpjf", service.serv_getbjpjf(xn, bjdm));
		request.setAttribute("bjbjdl",service.serv_getbjbjdl(xn, bjdm));
		request.setAttribute("fdy", service.serv_getFdyxxForBj(bjdm));//�༶����Ա
		request.setAttribute("rs",service.serv_getBjxx(bjdm));//�༶������Ϣ
		request.setAttribute("xhnum", service.serv_getXhNum(bjdm));//�༶����
		return mapping.findForward("xjbjSq");
	}
	/**
	 * �Ƚ��༶���ѯ��ҳ 
	 */
	public ActionForward xjbjDefault (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{

		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();		
		String userDep  = request.getSession().getAttribute("userDep").toString();	
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit(); 		
		ArrayList<HashMap<String, String>> topTr = null;
		ArrayList<String[]>  rs = null;
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		if(myForm.getXn()==null){
			myForm.setXn(Base.getJxjsqxn());//��ѧ��ѧ��
		}
		if("admin".equalsIgnoreCase(userType)||"xx".equalsIgnoreCase(userType)){
			userType = "xx";
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			XjbjModel model = new XjbjModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.serv_getXjbjQerrTitle();
			rs    = service.serv_xjbjDefault(model);
		}
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);
//		ҳ����������		
		pjpyUnit.setNjXyZyBjList(request, myForm);
//		��дȨ�ж�	
		request.setAttribute("userType",userType);
		request.setAttribute("writeAble",(Base.chkUPower(userName,"ynys_xjbjsqqry.do", userType.equalsIgnoreCase("stu"))==1)?"yes":"no");		
		request.setAttribute("tableName","view_zjlg_xjbjxx");
		request.setAttribute("realTable","zjlg_xjbjb");
		return mapping.findForward("xjbjDefault");
	}
	/**
	 * @describe �������š�������
	 * @author hhh
	 * @throws Exception
	 */
	public ActionForward yxxsjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		ZjlgPjpyService service = new ZjlgPjpyService();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String xh  = request.getParameter("xh");
//		HashMap<String,String> map = new HashMap<String, String>();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
//		String jxjmc = request.getParameter("jxjmc");
		HashMap<String,String> stuCjMap = new HashMap<String, String>();
		String xn = Base.getJxjsqxn();
//		HttpSession session = request.getSession();
		if("student".equalsIgnoreCase(userOnline)){
			xh = userName;
		}
		ActionForward myActFwd = null;
		HashMap<String, String> rs1 = service.serv_getXsInfo(xh);
		String zydm = rs1.get("zydm");
		String xq = Base.getJxjsqxq();
		if("01".equals(xq)){
			rs1.put("xq", "��");
		}else if("02".equals(xq)){
			rs1.put("xq", "��");
		}
		String zzmm = rs1.get("zzmm");
		DAO dao = DAO.getInstance();
		HashMap<String, String> zzmmdm = dao.getMap("select * from zzmmdmb where zzmmdm=?", new String[]{zzmm}, new String[]{"zzmmmc"});
		rs1.put("zzmm", zzmmdm.get("zzmmmc"));

		String mz = rs1.get("mz");
		HashMap<String, String> mmdmv = dao.getMap("select * from mzdmb where mzdm=?", new String[]{mz}, new String[]{"mzmc"});
		rs1.put("mz", mmdmv.get("mzmc"));

		if(StringUtils.isNotNull(xh)){
			rs1.put("xn",Base.getJxjsqxn());//��ѧ������ѧ��
			//rs1.put("xq",Base.getJxjsqxq());//��ѧ������ѧ��
//			String bjdm = rs1.get("bjdm");
			stuCjMap = service.getStuCjForXh(model,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
			rs1.put("zhszcpcjpm",stuCjMap.get("pm"));//�ۺϲ����༶����
			rs1.put("dycj",("00".equals(stuCjMap.get("dycpf")))?"0":(stuCjMap.get("dycpf")));//�����ɼ�
			rs1.put("zycj",("00".equals(stuCjMap.get("zycpf")))?"0":(stuCjMap.get("zycpf")));//�����ɼ�
			rs1.put("zhcj",stuCjMap.get("zhcpf"));//�ۺϲ����ɼ�
			rs1.put("dypm",stuCjMap.get("dypm"));//��������
			rs1.put("zypm",stuCjMap.get("zypm"));//��������
//			String tycj = "";
//			HashMap<String, String> tycjmap = service.gettycj(xh, xn);
//			if(tycjmap!=null){
//			tycj = tycjmap.get("tycj");
//			}
			rs1.put("tycj",service.serv_getTyCj(xh, xn));//�����ɼ�
			//rs1.put("dycj",service.serv_getZjlgDyCj(xh, xn));//�����ɼ� zjlg_zpf
			
			String sql = "select distinct (select zhpm from (select zhpm,xh,xn,zydm from (select distinct nvl((dense_rank() " +
			"over(partition by xn,zydm order by zhcpf desc nulls last)),0) " +
			"zhpm, xh, xn, zydm from view_zjlg_zhszcpf where 1 = 1))where 1=1 and xh='"+xh+"' and xn ='"+xn+"' and " +
			"zydm='"+zydm+"')||'/'||(select count(xh) cpznum from view_zjlg_zhszcpf where xn='"+xn+"' " +
			"and zydm='"+zydm+"') zhuanypm from view_zjlg_zhszcpf ";
			System.out.println(sql);
			HashMap<String, String> zhuanypm1 = dao.getMap(sql, new String[]{}, new String[]{"zhuanypm"});
			if(zhuanypm1 != null){
				rs1.put("zhuanypm", zhuanypm1.get("zhuanypm"));
			}

		}

		rs1.put("jxjdm",myForm.getJxjdm());
		ZjlgPjpyUnit.formToGBK(myForm);
		request.setAttribute("rs",rs1);//��ȡѧ����ػ�����Ϣ
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("jzxjxmList", service.getJxjList(""));
		StuInfoDAO siDAO = new StuInfoDAO();
		request.setAttribute("yhklxList", siDAO.getYhklxList());

		String jxjdm = myForm.getJxjdm();
		HashMap<String, String> map1 = service.getJxjlj(jxjdm);
		String jxjlj = null;
		if(map1!=null){
			jxjlj = map1.get("sqbdlj");
		}
		if(StringUtils.isNull(jxjlj)){
//			request.setAttribute("errMsg", "�뵽����ά����ȥƥ����������ͣ�");
//			return new ActionForward("/errMsg.do", false);
			jxjlj = "/pjpy/zjlg/jxjsq/yxxsjxj/add.jsp";
		}
		myActFwd = new ActionForward(jxjlj);

//		if("shgzjxj".equals(jxjmc) || "yxxsjxj".equals(jxjmc) 
//		||"kycxjxj".equals(jxjmc) || "dxjxj".equals(jxjmc)){
//		return mapping.findForward("yxxsjxjsq");
//		}else if("tcgxjxj".equals(jxjmc)){
//		return mapping.findForward("tcgxjxj");
//		}else if("yxbysjxj".equals(jxjmc)){
//		return mapping.findForward("yxbysjxj");
//		}else if("fzzgjxj".equals(jxjmc)){
//		return mapping.findForward("fzzgjxj");
//		}else if("gjjxj".equals(jxjmc)){
//		return mapping.findForward("gjjxj");
//		}else if("hdjxj".equals(jxjmc)){
//		return mapping.findForward("hdjxj");
//		}else if("smjxj".equals(jxjmc)){
//		return mapping.findForward("smjxj");
//		}{
//		return mapping.findForward("yxxsjxjsq");
//		}
		return myActFwd;
	}
	/**
	 * ��ѧ������
	 * @throws Exception 
	 */
	public ActionForward jxjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		String xh  = request.getParameter("xh");
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel   model   = new ZjlgPjpyModel();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String userName   = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
//		String jxjtype = request.getParameter("jxjtype");
//		myForm.setSbdj(myForm.getSbdj());
//		myForm.setDjchdjxj(myForm.getDjchdjxj());
		boolean booltj = true;
		ActionForward myActFwd = null;
//		String xn     = Base.getJxjsqxn();
		String xn = request.getParameter("xn");
//		String xq     = Base.getJxjsqxq();
		String jxjdm  = myForm.getJxjdm();
		String update = request.getParameter("update");

//		String zhszcpcjpm = myForm.getZhszcpcjpm();
		String dycj = myForm.getDycj();
		String zycj = myForm.getZycj();
//		String tycj = myForm.getTycj();
		HashMap<String, String> rs2 = service.serv_getXsInfo(xh);
		String zydm = rs2.get("zydm");
//		String bjdm = rs2.get("bjdm");

		String prexn = "";
		if("student".equalsIgnoreCase(userOnline)){
			xh = userName;
		}
		if(StringUtils.isNotNull(xn)){
			String[] xn1 = xn.split("-");
			xn1[0] = String.valueOf((Integer.parseInt(xn1[0])-1));
			xn1[1] = String.valueOf((Integer.parseInt(xn1[1])-1));
			StringBuffer buff = new StringBuffer();
			prexn = buff.append(xn1[0]).append("-").append(xn1[1]).toString();

		}

		//����ѧ��ȡ�ò��������
		String cpzdm = service.getCpzdmForXh(xh,xn);
		String cpzdmpre = service.getCpzdmForXh(xh,prexn);


		HashMap<String,String> stuCjMap = new HashMap<String, String>();
		if(StringUtils.isNotNull(xh)){
		stuCjMap = service.getStuCjForXh(model,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
		}
		String xsbjpm = stuCjMap.get("pm");//�ۺϲ����༶����
		dycj = stuCjMap.get("dycpf");//�����ɼ�
		zycj = stuCjMap.get("zycpf");//�����ɼ�
		String zhszf = stuCjMap.get("zhcpf");//�ۺϲ����ɼ�
//		String dypm = stuCjMap.get("dypm");//��������
//		String zypm = stuCjMap.get("zypm");//��������
		
		if(!"update".equals(update)){
			if("save".equalsIgnoreCase(doType)){//���ݱ���
//				booltj = service.dao_jxjiscftj(xh);
				if (booltj) {
					//��������-ͻ�����׽�ѧ��
					if (booltj) {
						//�����ɼ���������
						boolean bool = service.iszydztj(xn, jxjdm);
						if(bool){
							if(StringUtils.isNotNull(cpzdm)){
								if(Float.parseFloat(service.getZybfb(prexn, xh, cpzdmpre))
										-Float.parseFloat((service.getZybfb(xn, xh, cpzdm)))>0){
									booltj = service.zypmdzTj(xn, "jxj", jxjdm, 
											service.getZybfb(xn, xh, cpzdm), 
											service.getZybfb(prexn, xh, cpzdmpre));
									if (!booltj) {
										request.setAttribute("zycjbf", "zycjbf1");
									}
								}else{
									booltj = false;
									request.setAttribute("zycjbf", "zycjbf1");
								}
							}else{
								booltj = false;
								request.setAttribute("cpzisnull", "cpzisnull");
							}
						}
					}
					if (booltj) {
						//������������1
						booltj = service.zyfTj(xn, "jxj", jxjdm, zycj);
						if (!booltj) {
							request.setAttribute("zycjbf", "zycjbf2");
						} else {
						}
					}
					if (booltj) {
						//�����ַ�����1
						booltj = service.dyfTj(xn, "jxj", jxjdm, dycj);
						if (!booltj) {
							request.setAttribute("zycjbf", "zycjbf3");
						} else {
						}
					}
					if (booltj) {
						//��������������1
						booltj = service.zyfpmTj(xh, xn, "jxj", jxjdm, cpzdm);
						if (!booltj) {
							request.setAttribute("zycjbf", "zycjbf4");
						}
					}
					if (booltj) {
						@SuppressWarnings("unused")
						String wycj = service.getYycj(xh, xn);
						//�����ɼ���������1
						booltj = service.dyfpmTj(xh, xn, "jxj", jxjdm, cpzdm);
						if (!booltj) {
							request.setAttribute("zycjbf", "zycjbf5");
						}
					}
					if (booltj) {
						String wycj = service.getYycj(xh, xn);
						//����ɼ�����
						booltj = service.wycjTj(xn, "jxj", jxjdm, wycj);
						if (!booltj) {
							request.setAttribute("zycjbf", "zycjbf6");
						}
					}
					if (booltj) {
						//�ۺ����ʷ�����
						booltj = service.zhpmTj(xh, xn, "jxj", jxjdm, cpzdm);
						if (!booltj) {
							request.setAttribute("zycjbf", "zycjbf7");
						}
					}
					if (booltj) {
						//�ۺ����ʷ�����1
						booltj = service.zhszfTj(xn, "jxj", jxjdm, zhszf);
						if (!booltj) {
							request.setAttribute("zycjbf", "zycjbf8");
						}
					}
					if (booltj) {
						if ("00061".equals(jxjdm) || "00062".equals(jxjdm)) {
							if (!booltj) {
								request.setAttribute("zycjbf", "zycjbf5");
							} else {
								String xsxm = service.getIsbys(xh);
								if (StringUtils.isNull(xsxm)) {
									request.setAttribute("zycjbf", "zycjbf9");
									booltj = false;
								}
							}
						}
					}
//					if (booltj) {
//					if ("00054".equals(jxjdm)) {
//					booltj = service.getIsjxjjd(xh, xn, "00054");
//					if (!booltj) {
//					request.setAttribute("zycjbf", "jxjjd1");
//					}
//					}
//					}
//					if (booltj) {
//					if ("00011".equals(jxjdm) || "00012".equals(jxjdm) || "00013".equals(jxjdm)) {
//					booltj = service.getIsjxjjd(xh, xn, "��־����");
//					if (!booltj) {
//					request.setAttribute("zycjbf", "jxjjd1");
//					}
//					if(booltj){
//					booltj = service.getIsjxjjd(xh, xn, "����ѧ��");
//					if (!booltj) {
//					request.setAttribute("zycjbf", "jxjjd2");
//					}
//					}
//					}
//					}
//					}else{
//					booltj = false;
//					request.setAttribute("isczcpf", "isczcpf");
				}

				BeanUtils.copyProperties(model, myForm);
				ZjlgPjpyUnit.formToGBK(model);
				if (booltj) {
					BeanUtils.copyProperties(model, myForm);
					boolean bool = false;
					if("update".equals(update)){
						ZjlgPjpyUnit.formToGBK(model);
						bool = true;
					}else{
						bool = service.dao_jxjiscftj(model);
					}
					if (!bool) {
						request.setAttribute("jxjcftj", "yes");
					} else {
						bool = service.dao_jxjAddSave(model);
						if (bool) {
							request.setAttribute("done", bool);
						} else {
							request.setAttribute("done", bool);
						}
					}
				}
			}
		}else{
			BeanUtils.copyProperties(model, myForm);
			ZjlgPjpyUnit.formToGBK(model);

			BeanUtils.copyProperties(model, myForm);
			boolean bool = false;
			if("update".equals(update)){
				ZjlgPjpyUnit.formToGBK(model);
				bool = true;
			}else{
				bool = service.dao_jxjiscftj(model);
			}
			if (!bool) {
				request.setAttribute("jxjcftj", "yes");
			} else {
				bool = service.dao_jxjAddSave(model);
				if (bool) {
					request.setAttribute("done", bool);
				} else {
					request.setAttribute("done", bool);
				}
			}

		}
		ZjlgPjpyUnit.formToGBK(myForm);

		HashMap<String, String> rs1 = service.serv_getXsInfo(xh);
		HashMap<String, String> map1 = service.getJxjlj(jxjdm);
		String jxjlj = null;
		String xq1 = Base.getJxjsqxq();
		if("01".equals(xq1)){
			rs1.put("xq", "��");
		}else if("02".equals(xq1)){
			rs1.put("xq", "��");
		}
		String zzmm = rs1.get("zzmm");
		DAO dao = DAO.getInstance();
		HashMap<String, String> zzmmdm = dao.getMap("select * from zzmmdmb where zzmmdm=?", new String[]{zzmm}, new String[]{"zzmmmc"});
		rs1.put("zzmm", zzmmdm.get("zzmmmc"));

		String mz = rs1.get("mz");
		HashMap<String, String> mmdmv = dao.getMap("select * from mzdmb where mzdm=?", new String[]{mz}, new String[]{"mzmc"});
		rs1.put("mz", mmdmv.get("mzmc"));
		rs1.put("", xsbjpm);
		if("update".equals(update)){

			if (booltj) {
				request.setAttribute("done", booltj);
			} else {
				request.setAttribute("done", booltj);
			}


			if(map1!=null){
				jxjlj = map1.get("sqjgbdlj");

				StringBuffer pkss = new StringBuffer();
				pkss.append(xh).append(xn).append(jxjdm);
				String pks = pkss.toString();
				rs1 =  service.jxj_view(pks,"");

				request.setAttribute("forwardtype", "update");
				request.setAttribute("rs1", rs1);
			}
		}else{
			if(map1!=null){
				jxjlj = map1.get("sqbdlj");
			}
		}

		rs1.put("xn",Base.getJxjsqxn());//��ѧ������ѧ��
		//rs1.put("xq",Base.getJxjsqxq());//��ѧ������ѧ��
//		rs1.put("zhszcpcjpm","zhszcpcjpm");//�ۺϲ����༶����
//		rs1.put("dycj","dycj");//�����ɼ�
//		rs1.put("zycj","zycj");//�����ɼ�
//		rs1.put("tycj","tycj");//�����ɼ�
//		rs1.put("zcbjpm",service.getStuCjForXh(model,rs1.get("bjdm"),xn,xh).get("pm"));//�ۺϲ����༶����		
//		rs1.put("dycj",service.getStuCjForXh(model,rs1.get("bjdm"),xn,xh).get("dycpf"));//�����ɼ�
//		rs1.put("zycj",service.getStuCjForXh(model,rs1.get("bjdm"),xn,xh).get("zycpf"));//�����ɼ�
//		rs1.put("tycj","0");//�����ɼ�
		if(StringUtils.isNotNull(xh)){
			stuCjMap = service.getStuCjForXh(model,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
			rs1.put("zhszcpcjpm",stuCjMap.get("pm"));//�ۺϲ����༶����
//			rs1.put("dycj",stuCjMap.get("dycpf"));//�����ɼ�
//			rs1.put("zycj",stuCjMap.get("zycpf"));//�����ɼ�
			rs1.put("dycj",("00".equals(stuCjMap.get("dycpf")))?"0":(stuCjMap.get("dycpf")));//�����ɼ�
			rs1.put("zycj",("00".equals(stuCjMap.get("zycpf")))?"0":(stuCjMap.get("zycpf")));//�����ɼ�
			rs1.put("zhcj",stuCjMap.get("zhcpf"));//�ۺϲ����ɼ�
			rs1.put("dypm",stuCjMap.get("dypm"));//��������
			rs1.put("zypm",stuCjMap.get("zypm"));//��������
//			String tycj1 = "";
//			HashMap<String, String> tycjmap = service.gettycj(xh, xn);

			String sql = "select distinct (select zhpm from (select zhpm,xh,xn,zydm from (select distinct nvl((dense_rank() " +
			"over(partition by xn,zydm order by zhcpf desc nulls last)),0) " +
			"zhpm, xh, xn, zydm from view_zjlg_zhszcpf where 1 = 1))where 1=1 and xh='"+xh+"' and xn ='"+xn+"' and " +
			"zydm='"+zydm+"')||'/'||(select count(xh) cpznum from view_zjlg_zhszcpf where xn='"+xn+"' " +
			"and zydm='"+zydm+"') zhuanypm from view_zjlg_zhszcpf ";
			System.out.println(sql);
			HashMap<String, String> zhuanypm1 = dao.getMap(sql, new String[]{}, new String[]{"zhuanypm"});
			if(zhuanypm1 != null){
				rs1.put("zhuanypm", zhuanypm1.get("zhuanypm"));
			}

//			if(tycjmap!=null){
//			tycj1 = tycjmap.get("tycj");
//			}
			rs1.put("tycj",service.serv_getTyCj(xh, xn));//�����ɼ�
		}

		request.setAttribute("jzxjxmList",service.getJxjList(""));//��ѧ������б�
		request.setAttribute("rs",rs1);//��ȡѧ����ػ�����Ϣ
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());   
		StuInfoDAO siDAO = new StuInfoDAO();
		request.setAttribute("yhklxList", siDAO.getYhklxList());
		if(StringUtils.isNull(jxjlj)){
//			request.setAttribute("errMsg", "�뵽����ά����ȥƥ����������ͣ�");
//			return new ActionForward("/errMsg.do", false);
			String msg = "�뵽����ά����ȥƥ����������ͣ���";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		myActFwd = new ActionForward(jxjlj);
		return myActFwd;
//		if("tcgxjxj".equals(jxjtype)){
//		return mapping.findForward("tcgxjxj");
//		}else if("shgzjxj".equals(jxjtype) || "yxxsjxj".equals(jxjtype)
//		||"kycxjxj".equals(jxjtype) || "dxjxj".equals(jxjtype)){
//		return mapping.findForward("yxxsjxjsq");
//		}else if("yxbysjxj".equals(jxjtype)){
//		return mapping.findForward("yxbysjxj");
//		}else if("fzzgjxj".equals(jxjtype)){
//		return mapping.findForward("fzzgjxj");
//		}else if("gjjxj".equals(jxjtype)){
//		return mapping.findForward("gjjxj");
//		}else if("hdjxj".equals(jxjtype)){
//		return mapping.findForward("hdjxj");
//		}else if("smjxj".equals(jxjtype)){
//		return mapping.findForward("smjxj");
//		}else{
//		return mapping.findForward("yxxsjxjsq");
//		}
	}
	/**
	 * ��ѧ���ѯ
	 */
	public ActionForward jxjQuery (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Ժϵ��� �����������ƣ�ѧУ��˲����ơ����༶��ѧ���۲�ɼ��ߵ�������
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		String go = request.getParameter("go");
		ArrayList<String[]> rs = new ArrayList<String[]> ();
		ArrayList<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		HashMap<String, String> xnmap = new HashMap<String, String>();
		String jxjdel = request.getParameter("jxjdel");
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();
		HttpSession td  = request.getSession();
		String usertype = (String) request.getSession().getAttribute("userType") ;
		String userDep = (String) td.getAttribute("userDep");
		String bmmc = (String)request.getSession().getAttribute("bmmc");
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		String whotype = "";
		if("stu".equals(usertype)){
			whotype = "stu";

		}else if("xy".equals(usertype)){
			whotype = "xy";
			request.setAttribute("xybmmc", bmmc);
			//model.setXn(Base.getJxjsqxn());
			model.setXydm(userDep);
		}else{
			whotype = "xx";
		}
		request.setAttribute("whotype", whotype);
		String count = null ;
		if("go".equals(go)){
			BeanUtils.copyProperties(model, myForm);
			rs = service.jxj_query(myForm,model,usertype);
			topTr = service.getjxjTitle();
			count = service.jxj_queryrsunm(myForm,model,usertype);
		}
		if("jxjdel".equals(jxjdel)){
			String pks = request.getParameter("pkstring");
			String whichpk = service.getAllDelList(pks);
			if (StringUtils.isNull(whichpk)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("whichpk", whichpk);
			}
		}
		xnmap.put("xn",Base.getJxjsqxn());//��ѧ������ѧ��
		xnmap.put("xq",Base.getJxjsqxq());//��ѧ������ѧ��

		request.setAttribute("cpzList",service.getCpzList(model));
		unit.setNjXyZyBjList(request, myForm);
		ZjlgPjpyDAO pjpydao = new ZjlgPjpyDAO();
		String txcpzdm = myForm.getCpzdm();
		if(StringUtils.isNotNull(txcpzdm)){
			request.setAttribute("bjList", pjpydao.getCpzBjList(myForm.getXn(), myForm.getXydm(), myForm.getCpzdm()));
		}
		request.setAttribute("xnmap", xnmap);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", count);
		request.setAttribute("jzxjxmList",service.getJxjList(""));//��ѧ������б�
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());   
		return mapping.findForward("jxjquery");
	}
	/**
	 * ��ѧ���޸�
	 */
	public ActionForward jxjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		HashMap<String,String> rs1 = new HashMap<String,String>();
//		String go = request.getParameter("go");
		HashMap<String, String> rs = new HashMap<String, String> ();
//		ArrayList<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();
		String pk = request.getParameter("pkValue");
		String jxjcxzj = request.getParameter("jxjcxzj");
//		String jxjtype = null ; 
//		if("00021".equals(jxjcxzj) || "00022".equals(jxjcxzj) || "00023".equals(jxjcxzj)){
//		jxjtype = "tcgxjxj";
//		}else if("00011".equals(jxjcxzj) || "00012".equals(jxjcxzj) || "00013".equals(jxjcxzj)){
//		jxjtype = "yxxsjxj";
//		}else if("00061".equals(jxjcxzj) || "00062".equals(jxjcxzj)){
//		jxjtype = "yxbysjxj";
//		}else if("00071".equals(jxjcxzj)){
//		jxjtype = "fzzgjxj";
//		}else if("00072".equals(jxjcxzj)){
//		jxjtype = "gjjxj";
//		}else if("00073".equals(jxjcxzj)){
//		jxjtype = "hdjxj";
//		}else if("00074".equals(jxjcxzj)){
//		jxjtype = "smjxj";
//		}else{
//		jxjtype = "yxxsjxj";
//		}
		String act = request.getParameter("act");
		if("view".equals(act)){
			BeanUtils.copyProperties(model, myForm);
			rs =  service.jxj_view(pk,jxjcxzj);
			request.setAttribute("forwardtype", "view");
		}
		if("update".equals(act)){
			@SuppressWarnings("unused")
			String pks = request.getParameter("pkstring");
			rs =  service.jxj_view(pk,jxjcxzj);
			request.setAttribute("forwardtype", "update");
		}
		String xq = Base.getJxjsqxq();
		if("01".equals(xq)){
			rs.put("xq", "��");
		}else if("02".equals(xq)){
			rs.put("xq", "��");
		}
		String zzmm = rs.get("zzmm");
		DAO dao = DAO.getInstance();
		HashMap<String, String> zzmmdm = dao.getMap("select * from zzmmdmb where zzmmdm=?", new String[]{zzmm}, new String[]{"zzmmmc"});
		rs.put("zzmm", zzmmdm.get("zzmmmc"));

		String mz = rs.get("mz");
		HashMap<String, String> mmdmv = dao.getMap("select * from mzdmb where mzdm=?", new String[]{mz}, new String[]{"mzmc"});
		rs.put("mz", mmdmv.get("mzmc"));

		HashMap<String,String> stuCjMap = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs2 = service.serv_getXsInfo(xh);
		String xn = request.getParameter("xn");
		String zydm = rs2.get("zydm");
//		String bjdm = rs2.get("bjdm");
		stuCjMap = service.getStuCjForXh(model,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
		rs.put("zhszcpcjpm",stuCjMap.get("pm"));//�ۺϲ����༶����
		rs.put("dycj",stuCjMap.get("dycpf"));//�����ɼ�
		rs.put("zycj",stuCjMap.get("zycpf"));//�����ɼ�
		rs.put("zhcj",stuCjMap.get("zhcpf"));//�ۺϲ����ɼ�
		rs.put("dypm",stuCjMap.get("dypm"));//��������
		rs.put("zypm",stuCjMap.get("zypm"));//��������
//		String tycj1 = "";
//		HashMap<String, String> tycjmap = service.gettycj(xh, xn);
//		if(tycjmap!=null){
//		tycj1 = tycjmap.get("tycj");
//		}
		rs.put("tycj",service.serv_getTyCj(xh, xn));//�����ɼ�
		stuCjMap = service.getStuCjForXh(model,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
		rs.put("zhszcpcjpm",stuCjMap.get("pm"));//�ۺϲ����༶����



		String sql = "select (select zhpm from (select xh,xn,zydm,nvl((dense_rank() " +
		"over(partition by xn,zydm order by zhcpf desc nulls last)),0) " +
		"zhpm from view_zjlg_zhszcpf) where 1 = 1 and xh='"+xh+"' and xn ='"+xn+"' and " +
		"zydm='"+zydm+"')||'/'||(select count(xh) cpznum from view_zjlg_zhszcpf where xn='"+xn+"' " +
		"and zydm='"+zydm+"') zhuanypm from view_zjlg_zhszcpf ";
//		System.out.println(sql);
		HashMap<String, String> zhuanypm1 = dao.getMap(sql, new String[]{}, new String[]{"zhuanypm"});
		if(zhuanypm1 != null){
			rs.put("zhuanypm", zhuanypm1.get("zhuanypm"));
		}




		request.setAttribute("rs1", rs1);
		unit.setNjXyZyBjList(request, myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("jzxjxmList",service.getJxjList(""));//��ѧ������б�
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());  
		ActionForward myActFwd = null;
		HashMap<String, String> map1 = service.getJxjlj(jxjcxzj);
		String jxjlj = null;
		if(map1!=null){
			jxjlj = map1.get("sqjgbdlj");
		}
		if(StringUtils.isNull(jxjlj)){
			request.setAttribute("errMsg", "�뵽����ά����ȥƥ����������ͣ�");
			return new ActionForward("/errMsg.do", false);
		}
		myActFwd = new ActionForward(jxjlj);
		return myActFwd;
//		if("tcgxjxj".equals(jxjtype)){
//		return mapping.findForward("tcgxjxjview");
//		}else if("shgzjxj".equals(jxjtype) || "yxxsjxj".equals(jxjtype)
//		||"kycxjxj".equals(jxjtype) || "dxjxj".equals(jxjtype)){
//		return mapping.findForward("yxxsjxjsqview");
//		}else if("yxbysjxj".equals(jxjtype)){
//		return mapping.findForward("yxbysjxjview");
//		}else if("fzzgjxj".equals(jxjtype)){
//		return mapping.findForward("fzzgjxjview");
//		}else if("gjjxj".equals(jxjtype)){
//		return mapping.findForward("gjjxjview");
//		}else if("hdjxj".equals(jxjtype)){
//		return mapping.findForward("hdjxjview");
//		}else if("smjxj".equals(jxjtype)){
//		return mapping.findForward("smjxjview");
//		}else{
//		return mapping.findForward("yxxsjxjsqview");
//		}
	}
	/**
	 * ��ѧ�𵼳�
	 */
	public ActionForward jxjDataExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel model = new ZjlgPjpyModel();
//		ArrayList<String[]> rs = new ArrayList<String[]>();
		String jxjdm = request.getParameter("jxjdm");

		String act = request.getParameter("act");
		if("expData".equals(act)){
			BeanUtils.copyProperties(model, myForm);
			service.jxj_DataExport(myForm,model,jxjdm,response);
			request.setAttribute("forwardtype", "view");
		}
		return mapping.findForward("");
	}
	/**
	 * ��ѧ�𱨱�
	 */ 
	public ActionForward jxjReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel model = new ZjlgPjpyModel();
//		String go = request.getParameter("go");
		HashMap<String, String> rs = new HashMap<String, String> ();
//		ArrayList<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();
		String pk = request.getParameter("pkValue");
		String jxjcxzj = request.getParameter("jxjcxzj");

//		String jxjtype = null ; 
		ActionForward myActFwd = null;
//		if("00021".equals(jxjcxzj) || "00022".equals(jxjcxzj) || "00023".equals(jxjcxzj)){
//		jxjtype = "tcgxjxj";
//		}else if("00011".equals(jxjcxzj) || "00012".equals(jxjcxzj) || "00013".equals(jxjcxzj)){
//		jxjtype = "yxxsjxj";
//		}else if("00061".equals(jxjcxzj) || "00062".equals(jxjcxzj)){
//		jxjtype = "yxbysjxj";
//		}else if("00071".equals(jxjcxzj)){
//		jxjtype = "fzzgjxj";
//		}else if("00072".equals(jxjcxzj)){
//		jxjtype = "gjjxj";
//		}else if("00073".equals(jxjcxzj)){
//		jxjtype = "hdjxj";
//		}else if("00074".equals(jxjcxzj)){
//		jxjtype = "smjxj";
//		}else{
//		jxjtype = "yxxsjxj";
//		}
//		String pks = request.getParameter("pkstring");
		rs =  service.jxj_view(pk,jxjcxzj);
		request.setAttribute("forwardtype", "update");
		unit.setNjXyZyBjList(request, myForm);
		String xh = rs.get("xh");
		xh = StringUtils.isNull(xh) ? request.getParameter("pkValue") : xh;
		String xn = rs.get("xn");
		String zydm = rs.get("zydm");


		String zzmm = rs.get("zzmm");
		DAO dao = DAO.getInstance();
		HashMap<String, String> zzmmdm = dao.getMap("select * from zzmmdmb where zzmmdm=?", new String[]{zzmm}, new String[]{"zzmmmc"});
		rs.put("zzmm", zzmmdm.get("zzmmmc"));

		String sql = "select (select zhpm from (select xh,xn,zydm,nvl((dense_rank() " +
		"over(partition by xn,zydm order by zhcpf desc nulls last)),0) " +
		"zhpm from view_zjlg_zhszcpf) where 1 = 1 and xh='"+xh+"' and xn ='"+xn+"' and " +
		"zydm='"+zydm+"')||'/'||(select count(xh) cpznum from view_zjlg_zhszcpf where xn='"+xn+"' " +
		"and zydm='"+zydm+"') zhuanypm from view_zjlg_zhszcpf ";
		//System.out.println(sql);
		HashMap<String, String> zhuanypm1 = dao.getMap(sql, new String[]{}, new String[]{"zhuanypm"});
		if(zhuanypm1 != null){
			rs.put("zhuanypm", zhuanypm1.get("zhuanypm"));
		}
		String xq = Base.getJxjsqxq();
		if("01".equals(xq)){
			rs.put("xq", "��");
		}else if("02".equals(xq)){
			rs.put("xq", "��");
		}
		String mz = rs.get("mz");
		HashMap<String, String> mmdmv = dao.getMap("select * from mzdmb where mzdm=?", new String[]{mz}, new String[]{"mzmc"});
		rs.put("mz", mmdmv.get("mzmc"));


		HashMap<String,String> rs1 = new HashMap<String,String>();
		HashMap<String,String> stuCjMap = new HashMap<String, String>();
	//	xh = request.getParameter("xh");
		HashMap<String, String> rs2 = service.serv_getXsInfo(xh);
		xn = request.getParameter("xn");
		zydm = rs2.get("zydm");
//		String bjdm = rs2.get("bjdm");
		if (StringUtils.isNotNull(xh)) {
			stuCjMap = service.getStuCjForXh(model,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
			rs.put("zhszcpcjpm", stuCjMap.get("pm"));//�ۺϲ����༶����
			rs.put("dycj", stuCjMap.get("dycpf"));//�����ɼ�
			rs.put("zycj", stuCjMap.get("zycpf"));//�����ɼ�
			rs.put("zhcj", stuCjMap.get("zhcpf"));//�ۺϲ����ɼ�
			rs.put("dypm", stuCjMap.get("dypm"));//��������
			rs.put("zypm", stuCjMap.get("zypm"));//��������
//			String tycj1 = "";
//			HashMap<String, String> tycjmap = service.gettycj(xh, xn);
//			if (tycjmap != null) {
//			tycj1 = tycjmap.get("tycj");
//			}
			rs.put("tycj", service.serv_getTyCj(xh, xn));//�����ɼ�
		}
		String dqxn = (String) request.getSession().getAttribute("LoginXn");
		if(StringUtils.isNotNull(dqxn)){
			String[] sqxn = dqxn.split("-");
			dqxn=sqxn[1];
		}
		request.setAttribute("dqxn", dqxn);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("jzxjxmList",service.getJxjList(""));//��ѧ������б�
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());  

//		ActionForward myActFwd = null;
		HashMap<String, String> map1 = service.getJxjlj(jxjcxzj);
		String jxjlj = null;
		if(map1!=null){
			jxjlj = map1.get("bblj");
		}
		if(StringUtils.isNull(jxjlj)){
			String msg = "�ý�ѧ�����ޱ���";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		myActFwd = new ActionForward(jxjlj);
		return myActFwd;
//		if("tcgxjxj".equals(jxjtype)){
//		return mapping.findForward("tcgxjxjprint");
//		}else if("shgzjxj".equals(jxjtype) || "yxxsjxj".equals(jxjtype)
//		||"kycxjxj".equals(jxjtype) || "dxjxj".equals(jxjtype)){
//		return mapping.findForward("yxxsjxjsqprint");
//		}else if("yxbysjxj".equals(jxjtype)){
//		return mapping.findForward("yxbysjxjprint");
//		}else if("fzzgjxj".equals(jxjtype)){
//		return mapping.findForward("fzzgjxjprint");
//		}else if("gjjxj".equals(jxjtype)){
//		return mapping.findForward("gjjxjprint");
//		}else if("hdjxj".equals(jxjtype)){
//		return mapping.findForward("hdjxjprint");
//		}else if("smjxj".equals(jxjtype)){
//		return mapping.findForward("smjxjprint");
//		}else{
//		return mapping.findForward("yxxsjxjsqprint");
//		}
	}
	/**
	 * ��ѧ�����
	 */
	public ActionForward jxjCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		HashMap<String, String> rs = new HashMap<String, String> ();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();
		String pk = request.getParameter("pkValue");
		String jxjcxzj = request.getParameter("jxjcxzj");
//		String jxjtype = null ;
//		String act = request.getParameter("act");
		ActionForward myActFwd = null;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userType").toString();
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		String userDep = session.getAttribute("userDep").toString();
//		String userName = session.getAttribute("userName").toString();
		userType = dao.getUserType(userDep);

		request.setAttribute("userType", userType);
//		if("00021".equals(jxjcxzj) || "00022".equals(jxjcxzj) || "00023".equals(jxjcxzj)){
//		jxjtype = "tcgxjxj";
//		}else if("00011".equals(jxjcxzj) || "00012".equals(jxjcxzj) || "00013".equals(jxjcxzj)){
//		jxjtype = "yxxsjxj";
//		}else if("00061".equals(jxjcxzj) || "00062".equals(jxjcxzj)){
//		jxjtype = "yxbysjxj";
//		}else if("00071".equals(jxjcxzj)){
//		jxjtype = "fzzgjxj";
//		}else if("00072".equals(jxjcxzj)){
//		jxjtype = "gjjxj";
//		}else if("00073".equals(jxjcxzj)){
//		jxjtype = "hdjxj";
//		}else if("00074".equals(jxjcxzj)){
//		jxjtype = "smjxj";
//		}else{
//		jxjtype = "yxxsjxj";
//		}
//		String pks = request.getParameter("pkstring");
		rs =  service.jxj_view(pk,jxjcxzj);
		HashMap<String, String> rs1 = new HashMap<String, String>();
		HashMap<String,String> stuCjMap = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs2 = service.serv_getXsInfo(xh);
		String xn = Base.currXn;
		String zydm = rs2.get("zydm");
//		String bjdm = rs2.get("bjdm");
		stuCjMap = service.getStuCjForXh(model,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ		
		rs.put("zhszcpcjpm",stuCjMap.get("pm"));//�ۺϲ����༶����
		rs.put("dycj",stuCjMap.get("dycpf"));//�����ɼ�
		rs.put("zycj",stuCjMap.get("zycpf"));//�����ɼ�
		rs.put("zhcj",stuCjMap.get("zhcpf"));//�ۺϲ����ɼ�
		rs.put("dypm",stuCjMap.get("dypm"));//��������
		rs.put("zypm",stuCjMap.get("zypm"));//��������
		rs.put("bjg",stuCjMap.get("bjg"));//��������
		if(!StringUtils.isNull(stuCjMap.get("pm"))){
			rs.put("zcbjpm",stuCjMap.get("pm").split("/")[0]);
		}
		HashMap<String,String> stuDjMap = new HashMap<String, String>();
		stuDjMap = service.getStuDjForXh(xn,xh);//��ȡѧ����ػ����ɼ���Ϣ
		rs.put("yydjcj", stuDjMap.get("yydjcj"));
		rs.put("jsjdjcj", stuDjMap.get("jsjdjcj"));
//		String tycj1 = "";

		String zzmm = rs.get("zzmm");
		HashMap<String, String> zzmmdm = dao.getMap("select * from zzmmdmb where zzmmdm=?", new String[]{zzmm}, new String[]{"zzmmmc"});
		rs.put("zzmm", zzmmdm.get("zzmmmc"));

		String sql = "select (select zhpm from (select xh,xn,zydm,nvl((dense_rank() " +
		"over(partition by xn,zydm order by zhcpf desc nulls last)),0) " +
		"zhpm from view_zjlg_zhszcpf) where 1 = 1 and xh='"+xh+"' and xn ='"+xn+"' and " +
		"zydm='"+zydm+"')||'/'||(select count(xh) cpznum from view_zjlg_zhszcpf where xn='"+xn+"' " +
		"and zydm='"+zydm+"') zhuanypm from view_zjlg_zhszcpf ";
		//System.out.println(sql);
		HashMap<String, String> zhuanypm1 = dao.getMap(sql, new String[]{}, new String[]{"zhuanypm"});
		if(zhuanypm1 != null){
			rs.put("zhuanypm", zhuanypm1.get("zhuanypm"));
		}
		String xq = Base.getJxjsqxq();
		if("01".equals(xq)){
			rs.put("xq", "��");
		}else if("02".equals(xq)){
			rs.put("xq", "��");
		}
		String mz = rs.get("mz");
		HashMap<String, String> mmdmv = dao.getMap("select * from mzdmb where mzdm=?", new String[]{mz}, new String[]{"mzmc"});
		rs.put("mz", mmdmv.get("mzmc"));

//		HashMap<String, String> tycjmap = service.gettycj(xh, xn);
//		if(tycjmap!=null){
//		tycj1 = tycjmap.get("tycj");
//		}
		rs.put("tycj",service.serv_getTyCj(xh, xn));//�����ɼ�
		request.setAttribute("rs1", rs1);

		request.setAttribute("forwardtype", "update");
		unit.setNjXyZyBjList(request, myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("jzxjxmList",service.getJxjList(""));//��ѧ������б�
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());   
		request.setAttribute("jxjcxzj",jxjcxzj);
		StuInfoDAO siDAO = new StuInfoDAO();
		request.setAttribute("yhklxList", siDAO.getYhklxList());
		/*List<HashMap<String, String>>mapYhk=siDAO.getYhklxList();
		for (HashMap<String, String> hashMap : mapYhk) {
			 if(hashMap.get("yhklxdm").equals(rs.get("yhlx"))){
				 rs.put("yhlx", hashMap.get("yjklxmc"));
			 }
			 
		}  */

//		ActionForward myActFwd = null;
		HashMap<String, String> map1 = service.getJxjlj(jxjcxzj);
		String jxjlj = null;
		if(map1!=null){
			jxjlj = map1.get("shbdlj");
		}
		if(StringUtils.isNull(jxjlj)){
			request.setAttribute("errMsg", "�뵽����ά����ȥƥ����������ͣ�");
			return new ActionForward("/errMsg.do", false);
		}
		myActFwd = new ActionForward(jxjlj);
		return myActFwd;
//		if("tcgxjxj".equals(jxjtype)){
//		return mapping.findForward("tcgxjxjsh");
//		}else if("shgzjxj".equals(jxjtype) || "yxxsjxj".equals(jxjtype)
//		||"kycxjxj".equals(jxjtype) || "dxjxj".equals(jxjtype)){
//		return mapping.findForward("yxxsjxjsqsh");
//		}else if("yxbysjxj".equals(jxjtype)){
//		return mapping.findForward("yxbysjxjsh");
//		}else if("fzzgjxj".equals(jxjtype)){
//		return mapping.findForward("fzzgjxjsh");
//		}else if("gjjxj".equals(jxjtype)){
//		return mapping.findForward("gjjxjsh");
//		}else if("hdjxj".equals(jxjtype)){
//		return mapping.findForward("hdjxjsh");
//		}else if("smjxj".equals(jxjtype)){
//		return mapping.findForward("smjxjsh");
//		}else{
//		return mapping.findForward("yxxsjxjsqsh");
//		}
	}
	/**
	 * ��ѧ����˲�ѯ
	 */
	public ActionForward jxjshQuery (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Ժϵ��� �����������ƣ�ѧУ��˲����ơ����༶��ѧ���۲�ɼ��ߵ�������
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		String go = request.getParameter("go");
		ArrayList<String[]> rs = new ArrayList<String[]> ();
		ArrayList<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();
		String userType = (String) request.getSession().getAttribute("userType");
		ZjlgPjpyDAO pjpydao = new ZjlgPjpyDAO();
		String isZds = request.getParameter("isZds");
		String bmlb = "";
		String bmdm = "";
		String xydm = request.getParameter("xydm");
		String cpzdm = request.getParameter("cpzdm");
		String bjdm = request.getParameter("bjdm");
		String xn = request.getParameter("xn");
		String jxjdm = request.getParameter("jxjdm");
		//myForm.setJxjdm(jxjdm);
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		int count = 0;
		String userDep  = request.getSession().getAttribute("userDep").toString();	
		String bmmc = (String) request.getSession().getAttribute("bmmc");
		userType = (userType.equalsIgnoreCase("xy"))?"xy":"xx";
		String userName = request.getSession().getAttribute("userName").toString();		
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
			model.setXydm(userDep);
			request.setAttribute("shbmmc", bmmc);
		}
		if("go".equals(go)){
			ZjlgPjpyUnit.formToGBK(myForm);
			BeanUtils.copyProperties(model, myForm);
			rs = service.jxj_shquery(myForm,model,userType);
			topTr = service.getjxjshTitle(userType);
			count = rs != null ? rs.size():0;
		}				

		if("xysh".equals(isZds)){
			bmlb = "xydm";
			bmdm = xydm;
		}else if("cpzsh".equals(isZds)){
			bmlb = "cpzdm";
			bmdm = cpzdm;
		}else if("bjsh".equals(isZds)){
			bmlb = "bjdm";
			bmdm = bjdm;
		}
		
//		 int jxjrs = pjpydao.getJxjrsxz(bmlb, bmdm, jxjdm, xn,userType);
//		 request.setAttribute("jxjszrs", jxjrs);
//		 int jxjydrs = pjpydao.getJxjrsxz1(bmlb, bmdm, jxjdm, xn,userType);
//		 request.setAttribute("jxjysqrs", jxjydrs);
//		 int jxjhxsyrs = ((jxjrs-jxjydrs)<0)?0:(jxjrs-jxjydrs);
//		 request.setAttribute("jxjhxsyrs", jxjhxsyrs);

		if( !Base.isNull(bmlb)){
			request.setAttribute("jxjSqrs", service.getJxjSqQk(bmlb, xydm, bmdm, jxjdm, xn));
		}
		request.setAttribute("cpzList",service.getCpzList(model));
		unit.setNjXyZyBjList(request, myForm);
		myForm.setXn(Base.getJxjsqxn());
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", count);
		request.setAttribute("jzxjxmList",service.getJxjList(""));//��ѧ������б�
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xnval", Base.getJxjsqxn());
		request.setAttribute("xqList",Base.getXqList());  
		request.setAttribute("userType",userType);  
		request.setAttribute("writeAble",(Base.chkUPower(userName,"credit_check.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("jxjshquery");
	}
	/**
	 * �Ƚ��༶����
	 */
	public ActionForward xjbjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userType = request.getSession().getAttribute("userType").toString(); 
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit(); 

		String doType  = request.getParameter("doType");
		String bjdm    = myForm.getBjdm();//�༶����	
		myForm.setXn(Base.getJxjsqxn());
		myForm.setBjqk(DealString.toGBK(myForm.getBjqk()));	
		myForm.setSfyxbj(DealString.toGBK(myForm.getSfyxbj()));
		if("save".equalsIgnoreCase(doType)){
			XjbjModel model = new XjbjModel();
			BeanUtils.copyProperties(model,myForm);
			if(service.xjbjSqTjPd(bjdm, model.getXn(),Base.isNull(model.getWmqsgs())?"0":model.getWmqsgs(),
					Base.isNull(model.getAjqsgs())?"0":model.getAjqsgs(),model.getSfyxbj())){				
				boolean done = false;
				done = service.serv_xjbjAddSave(model);
				request.setAttribute("done",done); 
			}else{
				request.setAttribute("pass","no");
			}

		}
		//ҳ����������		
		pjpyUnit.setNjXyZyBjList(request, myForm);		
		request.setAttribute("fdy", service.serv_getFdyxxForBj(bjdm));//�༶����Ա
		request.setAttribute("rs",service.serv_getBjxx(bjdm));//�༶������Ϣ
		request.setAttribute("xhnum", service.serv_getXhNum(bjdm));//�༶����
		return mapping.findForward("xjbjAdd");
	}
	/**
	 * �Ƚ��༶��Ϣ�޸� 
	 */
	public ActionForward xjbjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		String  bjdm   = request.getParameter("bjdm");
		String pkValue = request.getParameter("pkValue");
		String doType  = request.getParameter("doType");
		String act     = request.getParameter("act");

		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit(); 		
		myForm.setBz(DealString.toGBK(myForm.getBz()));//
//		ҳ����������		
		HashMap<String,String> xjbjXx = service.serv_getXjbjxxForBj(pkValue);//ͨ��pkValue����Ƚ��༶�����Ϣ
		String xn = xjbjXx.get("xn");
		myForm.setSfyxbj(DealString.toGBK(myForm.getSfyxbj()));
		myForm.setBjqk(DealString.toGBK(myForm.getBjqk()));
		if("save".equalsIgnoreCase(doType)){//���ݱ���
			XjbjModel model = new XjbjModel();
			BeanUtils.copyProperties(model, myForm); 
			if(service.xjbjSqTjPd(bjdm,xn,Base.isNull(model.getWmqsgs())?"0":model.getWmqsgs(),
					Base.isNull(model.getAjqsgs())?"0":model.getAjqsgs(),model.getSfyxbj())){				
				boolean done = false;	
				done  = service.serv_xjbjModiSave(model, pkValue);
				request.setAttribute("done",done);  
			}else{
				request.setAttribute("pass","no");
			}  		
		}				

		pjpyUnit.setNjXyZyBjList(request, myForm);			
		request.setAttribute("rs",xjbjXx);
		request.setAttribute("fdy", service.serv_getFdyxxForBj(bjdm));//�༶����Ա
		request.setAttribute("xhnum", service.serv_getXhNum(bjdm));//�༶����
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("act",act);
		return mapping.findForward("xjbjModi");
	}
	/**
	 * �Ƚ��༶��Ϣɾ��
	 */
	public ActionForward xjbjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String delPk = request.getParameter("delPk");
		ZjlgPjpyService service = new ZjlgPjpyService();
		service.serv_xjbjDelete(delPk);
		return xjbjDefault(mapping,form,request,response);
	}
	/**
	 * �Ƚ��༶�����ҳ
	 */
	public ActionForward xjbjChkDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userType = request.getSession().getAttribute("userType").toString();
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		String xxdm = StandardOperation.getXxdm();
		if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			return new ActionForward("/nbtyWmbj.do?method=nbtyShWmbj",false);
		}
		
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit(); 
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyService service = new ZjlgPjpyService();
		String userDep  = request.getSession().getAttribute("userDep").toString();	
		myForm.setXn(Base.getJxjsqxn());
		myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
		ArrayList<HashMap<String, String>> topTr = null;
		ArrayList<String[]>  rs = null;	
		userType = (userType.equalsIgnoreCase("xy"))?"xy":"xx";
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			XjbjModel model = new XjbjModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.serv_getXjbjQerrTitle();
			rs    = service.serv_xjbjCkDefault(model, userType);
		}
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		request.setAttribute("rs",rs);
		request.setAttribute("topTr",topTr);
//		ҳ����������		
		pjpyUnit.setNjXyZyBjList(request, myForm);
		request.setAttribute("userType",userType);
		request.setAttribute("chkList",service.serv_getChkList());
		return mapping.findForward("xjbjChkDefault");
	}
	/**
	 *�Ƚ��༶��˲鿴 
	 */
	public ActionForward xjbjCkView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyService service = new ZjlgPjpyService();
		String pkValue = request.getParameter("pkValue");
		String view = request.getParameter("view");
		String select = request.getParameter("select");
		String  bjdm   = "";

		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyUnit pjpyUnit   = new ZjlgPjpyUnit(); 
		myForm.setBz(DealString.toGBK(myForm.getBz()));

//		ҳ����������		
		HashMap<String,String> xjbjXx = service.serv_getXjbjxxForBj(pkValue);//ͨ��pkValue����Ƚ��༶�����Ϣ
		bjdm = xjbjXx.get("bjdm");
		pjpyUnit.setNjXyZyBjList(request, myForm);			
		request.setAttribute("rs",xjbjXx);
		request.setAttribute("fdy", service.serv_getFdyxxForBj(bjdm));//�༶����Ա
		request.setAttribute("xhnum", service.serv_getXhNum(bjdm));//�༶����
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("view",view);
		request.setAttribute("select",select);
		request.setAttribute("sel",request.getParameter("sel"));
		return mapping.findForward("xjbjCkView");
	}	
	/**
	 * �Ƚ��༶���
	 */
	public ActionForward xjbjCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkVStr = request.getParameter("pkVStr");
		ZjlgPjpyService service = new ZjlgPjpyService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String check = request.getParameter("check");
		String xydm  = request.getParameter("xydm");		
		if("xy".equalsIgnoreCase(userType)){//Ժϵ�û����ʱ��������˰༶�����ƿ���
			xydm = request.getSession().getAttribute("userDep").toString();			
			if("yes".equalsIgnoreCase(check)){//���ͨ��ʱ�����ƿ���
				if(service.xjbjChBjsPd(Base.getJxjsqxn(),xydm, pkVStr)){				
					check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
					service.serv_xjbjCk(pkVStr, userType, check);
				}else{
					request.setAttribute("pass","no");
				}
			}else{
				check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
				service.serv_xjbjCk(pkVStr, userType, check);
			}
		}else{	
			check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
			service.serv_xjbjCk(pkVStr, userType, check);
		}
		request.setAttribute("clin","���ͨ���İ༶�ѳ�����ѧ�ꡢ��ѧԺ�Ƚ��༶�����ޣ�"+service.getXjbjMe(Base.getJxjsqxn(), xydm)+"��");
		boolean bools = service.serv_audit(pkVStr, userType, check, "", "�Ƚ��༶����", userName,"zjlg_xjbjb");
		return xjbjChkDefault(mapping,form,request,response);
	}
	/**
	 * ��ѧ�����
	 */
	public ActionForward jxjSqSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		String pkVStra = request.getParameter("pkVStr");
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		String userType = request.getSession().getAttribute("userType").toString();
		String shyj     = DealString.toGBK(request.getParameter("shyj"));
		HashMap<String, String> map = new HashMap<String, String>();
		myForm.setShyj(shyj);
		String check    = request.getParameter("check");
		check = "yes".equalsIgnoreCase(check)?"ͨ��":"��ͨ��";
		String shlx = request.getParameter("isZds");
		StringBuffer buff = new StringBuffer();
		//�����ж�

		ZjlgPjpyUnit.formToGBK(myForm);
		myForm.setShyj("");
		BeanUtils.copyProperties(model, myForm);
		String xh = "";
		String xn = "";
		String jxjdm = "";
		@SuppressWarnings("unused")
		String zhcpf = "";
		String rowStr = "!!";
		String errorStr = "";
		@SuppressWarnings("unused")
		String bmlb = "";


		String[] pkValueA = pkVStra.split("!!");
		if("xysh".equals(shlx)){
			bmlb = "xydm";
		}else if("cpzsh".equals(shlx)){
			bmlb = "cpzdm";
		}else if("bjsh".equals(shlx)){
			bmlb = "bjdm";
		}
//		String bmdm = "";
		if("ͨ��".equals(check)){
			for(String pkval : pkValueA){
				if(StringUtils.isNotNull(pkval)){
//					int i =0;
					map = service.jxj_getxhxnjxjdm(pkval);
					if(map != null){
						xh = map.get("xh");
						xn = map.get("xn");
						jxjdm = map.get("jxjdm");
						zhcpf = map.get("zhcpf");
					}
					//bool = service.zhszfTj(xn, "jxj", jxjdm, zhcpf);
					//bool = service.getJxjMe(bmlb, bmdm, jxjdm, xn);

					boolean booltj = true;
					boolean bool = true;
					if ("00054".equals(jxjdm)) {
						booltj = service.getIsjxjjd(xh, xn, "00054",userType);
						if (!booltj) {
							request.setAttribute("zycjbf", "jxjjd1");
							bool = false;
						}
					}

					if ("00011".equals(jxjdm) || "00012".equals(jxjdm) || "00013".equals(jxjdm)) {
						booltj = service.getIsjxjjd(xh, xn, "��־����",userType);
						if (!booltj) {
							request.setAttribute("zycjbf", "jxjjd1");
							bool = false;
						}
						if(booltj){
							booltj = service.getIsjxjjd(xh, xn, "����ѧ��",userType);
							if (!booltj) {
								request.setAttribute("zycjbf", "jxjjd2");
								bool = false;
							}
						}
					}

					if(booltj){
						if ("00054".equals(jxjdm) || "00011".equals(jxjdm) || "00012".equals(jxjdm) || "00013".equals(jxjdm)) {
							if(!"00054".equals(jxjdm)){
								String ckdm = xh+xn+"00054"; 
								for(int j = 0;j<pkValueA.length;j++){
									if(ckdm.equals(pkValueA[j])){
										bool = false;
									}
								}
							} 
							if(bool && (!"00011".equals(jxjdm))){
								String ckdm = xh+xn+"00011"; 
								for(int j = 0;j<pkValueA.length;j++){
									if(ckdm.equals(pkValueA[j])){
										bool = false;
									}
								}
							}
							if(bool && (!"00012".equals(jxjdm))){
								String ckdm = xh+xn+"00012"; 
								for(int j = 0;j<pkValueA.length;j++){
									if(ckdm.equals(pkValueA[j])){
										bool = false;
									}
								}
							}
							if(bool && (!"00013".equals(jxjdm))){
								String ckdm = xh+xn+"00013"; 
								for(int j = 0;j<pkValueA.length;j++){
									if(ckdm.equals(pkValueA[j])){
										bool = false;
									}
								}
							}
						}
					}



					if(bool){
						buff.append(pkval).append(rowStr);
					}else{
						errorStr = errorStr + "ѧ��Ϊ[" + xh + "]��ѧ�����ʧ�� ,����ѧ����ѧ�����־���ѧ���ܼ��;";
					}
//					i++;
				}
			}
		}else{
			for(String pkval : pkValueA){
				buff.append(pkval).append(rowStr);
			}
		}

		@SuppressWarnings("unused")
		String userName = request.getSession().getAttribute("userName").toString();
		boolean bools = service.serv_JxjSqCk(buff.toString(), userType, check,shyj,"��ѧ������",userName);

		request.setAttribute("errorStr", errorStr);

		if("xy".equals(userType)){
			userType = "xy";
		}else{
			userType = "xx";
		}
		request.setAttribute("userType", userType);

		return jxjshQuery(mapping,form,request,response);
	}
	/**
	 * ���
	 * author lyl
	 */
	public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		String pkVStra = request.getParameter("pkVStr");
		String workFlowName = request.getParameter("workFlowName");
		String tableName = request.getParameter("tableName");
		String status = request.getParameter("status");
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String thly     = DealString.toGBK(request.getParameter("thly"));
		HashMap<String, String> map = new HashMap<String, String>();
		myForm.setThly(thly);
		StringBuffer buff = new StringBuffer();
		//�����ж�
		ZjlgPjpyUnit.formToGBK(myForm);
		myForm.setShyj("");
		BeanUtils.copyProperties(model, myForm);
		String errorStr = "";
		String rowStr = "!!";
		String[] pkValueA = pkVStra.split("!!");
		for(String pkval : pkValueA){
			buff.append(pkval).append(rowStr);
		}
		
		// buff workflowId ����ID  userType �û�����  ����״̬  thly  �˻�����    workFlowName �������� userName �û���
		if(workFlowName.equals("jxjlc")){
			workFlowName="��ѧ������";
		}
		else if(workFlowName.equals("rychlc")){
			workFlowName="�����ƺ�����";
		}
		if(status.equals("th")){
			status="�˻�";
		}
		@SuppressWarnings("unused")
		boolean bools = service.serv_JxjBack(buff.toString(), userType,status,thly,DealString.toGBK(workFlowName),userName,tableName);

		request.setAttribute("errorStr", errorStr);

		if("xy".equals(userType)){
			userType = "xy";
		}else{
			userType = "xx";
		}
		request.setAttribute("userType", userType);
		request.setAttribute("deleted", "yes");
		if(tableName.equals("xsjxjb")){
		    return jxjshQuery(mapping,form,request,response);
		}
		else if(tableName.equals("zjlg_xsrychb")){
			return rychCkDefault(mapping,form,request,response);
		}
		else {
			return jxjshQuery(mapping,form,request,response);
		}
	}
	/**
	 * ����׷��
	 * author lyl
	 */
	public ActionForward recordView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ArrayList<String[]> rs1 = new ArrayList<String[]> ();
		ArrayList<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		String pkR = request.getParameter("pkR");
		String tableName = request.getParameter("tableName");
		ZjlgPjpyService service = new ZjlgPjpyService();
		rs1=service.workFlowQuery(pkR,tableName);
		topTr = service.getWorkFlowTitle();
		request.setAttribute("rs1", rs1);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("workflow");
	}

	/**
	 * @describe �������á��������ƺŵ�����������
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward saveRychRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		String pk = request.getParameter("pk");
		BeanUtils.copyProperties(myModel, myForm);
		boolean updated = service.saveRychRs(pk,myModel,request);
		if (updated) {
			request.setAttribute("updateOK", "ok");
		} else {
			request.setAttribute("updateOK", "no");
		}
		return cssz(mapping,form,request,response);
	}
	/**
	 * ��ѧ�𷢷�
	 */
	public ActionForward jxjffhz (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Ժϵ��� �����������ƣ�ѧУ��˲����ơ����༶��ѧ���۲�ɼ��ߵ�������
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel model = new ZjlgPjpyModel();
		String go = request.getParameter("go");
		ArrayList<String[]> rs = new ArrayList<String[]> ();
		ArrayList<HashMap<String, String>>  topTr = new ArrayList<HashMap<String, String>> ();
		ZjlgPjpyUnit unit= new ZjlgPjpyUnit();
		String usertype = (String) request.getSession().getAttribute("userType") ;
		int count = 0;
		if("go".equals(go)){
			ZjlgPjpyUnit.formToGBK(myForm);
			BeanUtils.copyProperties(model, myForm);
			rs = service.jxj_ffquery(myForm,model,usertype);
			topTr = service.getjxjffTitle(usertype);
			count = rs != null ? rs.size():0;
		}


		String xydm 		   = "";
//		String zydm 		   = "";
//		String nj   		   = "";
		HttpSession session    = request.getSession();
		request.setAttribute("cpzList",service.getCpzList(model));
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 		
		request.setAttribute("userType", session.getAttribute("userType"));				
		unit.setNjXyZyBjList(request, myForm);
		//myForm.setXn(Base.getJxjsqxn());
		ZjlgPjpyDAO pjpydao = new ZjlgPjpyDAO();
		String txcpzdm = myForm.getCpzdm();
		if(StringUtils.isNotNull(txcpzdm)){
			request.setAttribute("bjList", pjpydao.getCpzBjList(myForm.getXn(), myForm.getXydm(), myForm.getCpzdm()));
		}
		HashMap<String, String> xnmap = new HashMap<String, String>();
		xnmap.put("xn",Base.getJxjsqxn());//��ѧ������ѧ��
		xnmap.put("xq",Base.getJxjsqxq());//��ѧ������ѧ��
		request.setAttribute("xnmap", xnmap);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", count);
		request.setAttribute("jxjList",service.getJxjList(""));//��ѧ������б�
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xnval", Base.getJxjsqxn());
		request.setAttribute("xqList",Base.getXqList());   
		
		request.setAttribute("path", "zjlgPjpy.do?method=jxjffhz");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		
		return mapping.findForward("jxjff");
	}	
	/**
	 * �鿴�������
	 */
	public ActionForward jxjcfqk (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//Ժϵ��� �����������ƣ�ѧУ��˲����ơ����༶��ѧ���۲�ɼ��ߵ�������
		ZjlgPjpyService service = new ZjlgPjpyService();
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		ArrayList<HashMap<String, String>> cfqk = service.getCfqk(xh,xn,xq);
		request.setAttribute("cfqk", cfqk);
		return mapping.findForward("cfqk");
	}
	/**
	 * �����ƺŵǼǱ��ӡ
	 * @throws SQLException 
	 */
	public ActionForward rychDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
//		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyModel pjpyModel = new ZjlgPjpyModel();
		String rychdm = request.getParameter("rychdm");
		String xh = request.getParameter("xh");
		String xn = DealString.toString(request.getParameter("xn"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		ZjlgPjpyService service = new ZjlgPjpyService();			


		HashMap<String, String> map = service.getRychBb(rychdm);	
		String bblj = "";
		if(map!=null){
			bblj = map.get("bblj");
		}
		if(Base.isNull(bblj)){
			request.setAttribute("message", "�������ƺ�������Ӧ����");
			return new ActionForward("/prompt.do", false);
		}
		HashMap<String, String> stuMap = service.serv_getXsInfo(xh);//��ȡѧ����ػ�����Ϣ
		HashMap<String, String>  stuCjMap = service.getStuCjForXh(pjpyModel,xn,xh);//��ȡѧ����ػ����ɼ���Ϣ	

		request.setAttribute("rsCj",stuCjMap);
		request.setAttribute("rs",stuMap);
		request.setAttribute("bz",bz);
		request.setAttribute("xn",xn);
		return new ActionForward(bblj,false);
	}	
	/**
	 * @describe ��ѧ����ܱ���
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jxjhzPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyForm myform = (ZjlgPjpyForm) form;
		String xn = request.getParameter("xn");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String xnIn = myform.getXn();
		String xyIn = myform.getXydm();
		String zyIn = myform.getZydm();
		String bjIn = myform.getBjdm();
		String cpzIn = myform.getCpzdm();
		String xh = myform.getXh();
		String xm = myform.getXm();
		String jxjdm = myform.getJxjdm();
		ZjlgPjpyService service = new ZjlgPjpyService();
		String modelPath = servlet.getServletContext().getRealPath("")
		+ "/print/zjlg_pjpyzhhz.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.jxjhzPrint(wwb, xn, zydm, bjdm,xnIn,xyIn,zyIn,bjIn,cpzIn,xh,xm,jxjdm);

		return mapping.findForward("");
	}

	public ActionForward pjfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyService service = new ZjlgPjpyService();
		List<HashMap<String, String>> rs = service.getPjfsList();
		request.setAttribute("rs", rs);
		return mapping.findForward("pjfs");
	}

	public ActionForward savePjfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyService service = new ZjlgPjpyService();
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		BeanUtils.copyProperties(myModel, myForm);

		boolean updated = service.updatePjfs(myModel);
		if(updated){
			request.setAttribute("updated", "yes");
		}else{
			request.setAttribute("updated", "no");
		}
		return pjfs(mapping,form,request,response);
	}
	
	/**
	 * �����ɼ�ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zycpfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZjlgPjpyForm myForm = (ZjlgPjpyForm) form;
		ZjlgPjpyService service = new ZjlgPjpyService();
		
		String userType = request.getSession().getAttribute("userType").toString();
		String fdyFlag = request.getSession().getAttribute("fdyQx").toString();
		
		if (UserTypePd.isXy(userType) && !UserTypePd.isFdy(fdyFlag)) {
			myForm.setQueryequals_xydm(request.getSession().getAttribute("userDep").toString());
			myForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		
		/* ��ѯ���ݲ��� */
		if (QUERY.equalsIgnoreCase(myForm.getAct())) {
			selectPageDataByPagination(request, myForm, "", "view_zjlg_xxzlf",
					new String[] { "�к�", "xh", "xm", "xb", "bjmc", "xn",
							"jqpjf", "xxzlf", "pjfsmc", "cj", "bz" });
		} else if ("export".equalsIgnoreCase(myForm.getAct())) {
			expPageData(request, response, "", "view_zjlg_xxzlf", new String[] {
					"xh", "xm", "xb", "xymc", "zymc", "bjmc", "nj", "xn",
					"jqpjf", "xxzlf", "pjfsmc", "cj", "bz" });
			return mapping.findForward("");
		}
		
		appendOperQx(request, "zjlgPjpy.do?method=zycpfwh");
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("tableName", "view_zjlg_xxzlf");
		return mapping.findForward("zycpfwh");
	}
	
	
	
	public void appendOperQx(HttpServletRequest request, String path) {
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
	}
	
}
