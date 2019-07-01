package xgxt.pjpy.zjcm.xnjxj;

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
import org.apache.struts.actions.DispatchAction;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.pjpy.zjcm.PjpyZjcmUnit;
import xgxt.pjpy.zjcm.rych.RychService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.zjcm.WjcfZjcmService;

public class XnjxjAction extends DispatchAction {

	/**
	 * У�ڽ�ѧ���걨����
	 * 
	 * @throws Exception
	 */
	public ActionForward sbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		XnjxjService service = new XnjxjService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		XnjxjModel model = new XnjxjModel();

		String tableName = "view_zjcm_zhf";
		String realTable = "zjcm_jxjsq";
		String title = "�������� - ��ѧ�� - У�ڽ�ѧ���걨";
		boolean canWrite = false;

		if ("stu".equalsIgnoreCase(userType)) {
			if (!service.isCpzCy(userName, userDep)) {
				String msg = "�㲻�ǲ���С���Ա���޷�����";
				request.setAttribute("msg", msg);
			} else {
				if (!service.inTime(userDep)) {
					String msg = "���ڲ��������õ��걨ʱ�䷶Χ�ڣ��޷�����";
					request.setAttribute("msg", msg);
				}
			}
			request.setAttribute("xydm", userDep);
			myForm.setXydm(userDep);
			myForm.setZydm(service.getStuInfo(userName).get("zydm"));
			myForm.setBjdm(service.getStuInfo(userName).get("bjdm"));
			myForm.setXn(Base.getJxjsqxn());
			myForm.setXq(Base.getJxjsqxq());
			request.setAttribute("canWrite", canWrite);
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			myForm.setXn(Base.getJxjsqxn());
			myForm.setXq(Base.getJxjsqxq());
			request.setAttribute("canWrite", canWrite);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"xq", "zyf", "zhf", "zhpm","jxjmc" };

			topTr = service.getTopTr(tableName, colList);
			topTr.remove(topTr.size()-1);
			rs = service.getZhfList(tableName, model, colList);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "zjcm_pjpy_xnjxjsb.do");
		request.setAttribute("title", title);
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);

		return mapping.findForward("sbManage");
	}

	/**
	 * У�ڽ�ѧ���ϱ�
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward xnJxjSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XnjxjService service = new XnjxjService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		XnjxjModel model = new XnjxjModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "�������� - ��ѧ�� - У�ڽ�ѧ���걨";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh").trim();
		String xn = request.getParameter("xn").trim();
		String xq = DealString.toGBK(request.getParameter("xq")).trim();
		String pm = request.getParameter("pm").trim();
		List<HashMap<String, String>> wjcfList = service.getWjcfList(xh);

		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());
		BeanUtils.copyProperties(model, myForm);

		if ("save".equals(doType)) {
			String msg = service.Jxjtj(model, pm);
			if (Base.isNull(msg)) {
				boolean result = service.saveJxjsq(model, request);
				request.setAttribute("result", result);
			} else {
				request.setAttribute("msg", msg);
			}
			myForm.setHgjjxjqk(DealString.toGBK(myForm.getHgjjxjqk()));
		}

		map = service.getSbzXx(xh, xn, xq);
		map.put("pm", pm);
		map.put("kkqk", Base.isNull(service.getKkcs(xh, xn, xq))?"��":service.getKkcs(xh, xn, xq)+"��");

		unit.setNjXyZyBjList(request, myForm);
		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("yyList", service.getYyList());
		request.setAttribute("jsjList", service.getJsjList());
		request.setAttribute("jxjList", service.getJxjList("У"));
		request.setAttribute("wjcfList", wjcfList);
		if (wjcfList != null && wjcfList.size() > 0) {
			request.setAttribute("cfqk", wjcfList.size());
		}
		request.setAttribute("title", title);
		request.setAttribute("xh", xh);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("pm", pm);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		return mapping.findForward("xnJxjSb");
	}

	/**
	 * У�ڽ�ѧ����˹���
	 * 
	 * @throws Exception
	 */
	public ActionForward shManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String isFdy = session.getAttribute("isFdy").toString();

		XnjxjService service = new XnjxjService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit util = new PjpyZjcmUnit();
		XnjxjModel model = new XnjxjModel();

		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String title = "�������� - ��ѧ�� - У�ڽ�ѧ�����";
		String doType = request.getParameter("doType");
		String shzt = request.getParameter("shzt");

		String[] checkVal = myForm.getCheckVal();

		boolean canWrite = false;

//		if (Fdypd.isFdy(userName)) {
//			userType = "fdy";
//			//myForm.setXydm(userDep);
//		}
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
		}

		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		model.setKey(GlobalsVariable.PJPY_JXJ);

//		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
//			if (checkVal != null && checkVal.length > 0) {
//				String shjxj=request.getParameter("jxjdm");
//				String msg= service.saveJxjsh(checkVal, shjxj, shzt, userType);
//				result = Base.isNull(msg) ? true : false;
//				request.setAttribute("result", result);
//				request.setAttribute("msg", msg);
//			}
//		}
		
//		if (!Base.isNull(doType) && "cjsave".equals(doType)) {
//			if (checkVal != null && checkVal.length > 0) {
//				String shjxj = request.getParameter("jxjdm");
//				String msg = service.getCjJxjRs(shjxj, myForm.getBjdm(), String.valueOf(checkVal.length));
//				if (Base.isNull(msg)) {
//					service.saveCjJxjsh(checkVal, shjxj, shzt);
//				}
//				result = Base.isNull(msg) ? true : false;
//				request.setAttribute("result", result);
//				request.setAttribute("msg", msg);
//			}
//		}
		
		// �����ѯ��ť���в�ѯ
		String go = request.getParameter("go");
		if ("go".equalsIgnoreCase(go)) {
			
			String shjxj = request.getParameter("jxjdm");
//			String msg = service.getCjJxjRs(shjxj, myForm.getBjdm(), "1");
//			if (!Base.isNull(msg)) {
//				request.setAttribute("cjrs", "yes");
//			}
			
			topTr = service.queryJxjshTitle(model, userType, isFdy);
			rs = (ArrayList<String[]>) service.queryJxhshResult(model,
					userType, isFdy, userName);
			
			//String jxjrs = service.getJxjRs(myForm.getJxjdm(),myForm.getBjdm());
			//request.setAttribute("jxjrs", Base.isNull(jxjrs) ? "0" : jxjrs);
			//request.setAttribute("hdrs", service.getHdJxjRs(myForm.getJxjdm(),myForm.getBjdm()));
			if ("xy".equalsIgnoreCase(userType)
					&& StringUtils.isNotNull(model.getBjdm())
					&& StringUtils.isNotNull(model.getJxjdm())) {
				model.setNd(Base.getJxjsqnd());
				//��ѧ�������
				request.setAttribute("jxjrs", service.queryBjhjme(model));
				//ͨ������
				request.setAttribute("hdrs", service.queryBjhjrs(model, isFdy));
				//�Ƿ���ҳ����ʾ��ʾ��Ϣ
				request.setAttribute("view", "yes");
			}
			
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		} else if ("save".equalsIgnoreCase(go)) {
			boolean flag = service.updateJxjshResult(checkVal, request
					.getParameter("lb"), userType, isFdy);
			request.setAttribute("result", flag);
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "zjcm_pjpy_xnjxjsh.do");
		request.setAttribute("title", title);
		request.setAttribute("jxjList", service.getXnjxjList("У��%"));
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		if (UserTypePd.isFdy(isFdy)) {
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		}

		return mapping.findForward("shManage");
	}

	/**
	 * У�ڽ�ѧ�����
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward xnJxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		XnjxjService service = new XnjxjService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit unit = new PjpyZjcmUnit();
		XnjxjModel model = new XnjxjModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "�������� - ��ѧ�� - У�ڽ�ѧ�����";
		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk").trim();
		String jg = request.getParameter("jg");
		String cjsh = request.getParameter("cjsh");
		String cjrs = request.getParameter("cjrs");
		String dxj = request.getParameter("dxj");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());
		
		BeanUtils.copyProperties(model, myForm);

		if (Fdypd.isFdy(userName)) {
			userType = "fdy";
		}
		
		if ("save".equals(doType)) {
			String shzt = request.getParameter("shzt");
			boolean result = service.saveJxjsh(model, pk, shzt, userType,
					request);
			request.setAttribute("result", result);
		}else if ("cjsave".equals(doType)) {
			String shzt = request.getParameter("shzt");
			boolean	result = service.saveCjJxjsh(model, pk, shzt, userType);
			request.setAttribute("result", result);
		}else{
			if(service.hadJxj(pk)){
				String msg = "�Ѿ����" + Base.getJxjsqxn() + "ѧ�� "
						+ Base.getJxjsqxqmc() + "ѧ�� У�ڽ�ѧ��\n���ɼ�ã���ȷ�ϣ���";
				request.setAttribute("msg", msg);
			}
		}

		map = service.getSbJxjXx(pk);
		if(!Base.isNull(cjsh)&&"yes".equalsIgnoreCase(cjsh)){
			if ("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)) {
				request.setAttribute("cjsh", "yes");
			}
		}else if(!Base.isNull(dxj)&&"yes".equalsIgnoreCase(dxj)){
			if ("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)) {
				request.setAttribute("dxj", "yes");
			}
		}
		
		List<HashMap<String, String>> wjcfList = service.getWjcfList(map
				.get("xh"));
		unit.setNjXyZyBjList(request, myForm);

		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("yyList", service.getYyList());
		request.setAttribute("jsjList", service.getJsjList());
		request.setAttribute("jxjList", service.getJxjList("У"));
		request.setAttribute("wjcfList", wjcfList);
		if (wjcfList != null && wjcfList.size() > 0) {
			request.setAttribute("cfqk", wjcfList.size());
		}
		request.setAttribute("title", title);
		request.setAttribute("pk", pk);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", map);
		if (!Base.isNull(cjrs) && "yes".equalsIgnoreCase(cjrs)) {
			String msg = "������ѧ���Լ��μ���������������ȷ�ϣ���";
			request.setAttribute("msg", msg);
			request.setAttribute("cjrs", cjrs);
		}
		if (!Base.isNull(jg) && "yes".equalsIgnoreCase(jg)) {
			title = "�������� - ��ѧ�� - ��ѧ���������鿴";
			userType = "admin";
			String jxjlb = service.getJxjlb(map.get("jxjdm"));
			if("��".equalsIgnoreCase(jxjlb)){
				request.setAttribute("xw", jxjlb);
			}
			request.setAttribute("jg", jg);
			request.setAttribute("title", title);
			return mapping.findForward("jxjJg");
		}
		return mapping.findForward("xnJxjSh");
	}

	/**
	 * У�ڽ�ѧ���걨�������
	 * 
	 * @throws Exception
	 */
	public ActionForward jgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		XnjxjService service = new XnjxjService();
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		PjpyZjcmUnit util = new PjpyZjcmUnit();
		XnjxjModel model = new XnjxjModel();

		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String title = "�������� - ��ѧ�� - ��ѧ���걨���";
		String doType = request.getParameter("doType");
		String jxjlb = DealString.toGBK(myForm.getJxjlb());
		String xwlb = "";
		String[] checkVal = myForm.getCheckVal();

		boolean canWrite = false;

		if (Fdypd.isFdy(userName)) {
			userType = "fdy";
			myForm.setXydm(userDep);
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
		}
		
		if ("stu".equalsIgnoreCase(userType)) {
			if (!service.isCpzCy(userName, userDep)) {
				myForm.setXh(userName);
			} else {
				request.setAttribute("canWrite", canWrite);
				userType = "cpz";
				request.setAttribute("xydm", userDep);
				myForm.setXydm(userDep);
				myForm.setZydm(service.getStuInfo(userName).get("zydm"));
				myForm.setBjdm(service.getStuInfo(userName).get("bjdm"));
			}
		}
		
		if ("xy".equalsIgnoreCase(userType)) {
			canWrite = true;
			myForm.setXydm(userDep);
		}

		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			canWrite = true;
			request.setAttribute("canWrite", canWrite);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				String jxjdm = request.getParameter("jxjdm");
				xwlb = service.getJxjlb(jxjdm);
				myForm.setJxjlb(xwlb);
				result = service.saveXwjxjSb(checkVal, jxjdm);
				request.setAttribute("result", result);
			}
		}
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delJxjSq(checkVal,userType);
				request.setAttribute("result", result);
			}
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "jxjlb", "jxjmc", "fdysh", "xysh", "xxsh" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getJxjSqList(tableName, model, colList, userType, "jg");
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
			myForm.setJxjlb(DealString.toGBK(myForm.getJxjlb()));
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "prise_result.do");
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userType", "cpz".equalsIgnoreCase(userType)?"stu":userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xwJxjList", service.getJxjList("��"));
		request.setAttribute("xnJxjList", service.getJxjList(jxjlb));
		request.setAttribute("jxjLbList", service.getJxjLbList());
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", service.getXqmc(Base.getJxjsqxq()));
		util.setNjXyZyBjList(request, myForm);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		request.setAttribute("title", title);
		if ("fdy".equalsIgnoreCase(userType)) {
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		}

		return mapping.findForward("jgManage");
	}
	
	/**
	 * ��ѧ��ǼǱ�
	 * 
	 * @throws Exception
	 */
	public ActionForward jxjDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		
		String xh = request.getParameter("xh");
		String jxjmc = DealString.toGBK(request.getParameter("jxjmc"));
		
		String xyyj = DealString.toGBK(myForm.getXyyj());
		String xxyj = DealString.toGBK(myForm.getXxyj());
		
		RychService service = new RychService();
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("xh", xh);
		rs.put("xmmc", jxjmc);
		rs.put("xyshyj", xyyj);
		rs.put("xxshyj", xxyj);
		rs = service.getPrintXx(rs);
		request.setAttribute("rs", rs);
		return mapping.findForward("jxjDj");
	}
	
	/**
	 * ��ѧ�𵥸������ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjshDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm myForm = (PjpyZjcmActionForm) form;
		String pkValue = request.getParameter("pkValue");
		XnjxjService service = new XnjxjService();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		//�������ݲ���
		if ("save".equalsIgnoreCase(request.getParameter("go"))) {
			XnjxjModel model = new XnjxjModel();
			BeanUtils.copyProperties(model, myForm);
			request.setAttribute("result", service.updateXnjxjshResult(
					userType, isFdy, model, pkValue));
		} else if ("view".equalsIgnoreCase(request.getParameter("go"))) {
			request.setAttribute("writa", "view");
		}
		//��ѯ��ʾ������ϸ��Ϣ
		if (StringUtils.isNotNull(pkValue)) {
			rs = service.queryXnjxjDetails(pkValue, userType, isFdy);
		}
		if (!rs.isEmpty()) {
			myForm.setSh(rs.get("sh"));
			myForm.setYj(rs.get("yj"));
			WjcfZjcmService myService = new WjcfZjcmService();
			request.setAttribute("cfList", myService.queryStuCfxx(rs.get("xh")));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.getShList());
		request.setAttribute("pkValue", pkValue);
		setWritable(rs, isFdy, userType, request);
		return mapping.findForward("jxjshDetails");
	}
	
	public void setWritable(HashMap<String, String> rs, String isFdy,
			String userType, HttpServletRequest request) {
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				if ("ͨ��".equalsIgnoreCase(rs.get("xysh"))
						|| "ͨ��".equalsIgnoreCase(rs.get("xxsh"))) {
					request.setAttribute("dis", "disabled='disabled'");
				}
			} else {
				if ("ͨ��".equalsIgnoreCase(rs.get("xxsh"))) {
					request.setAttribute("dis", "disabled='disabled'");
				}
			}
		}
	}
}
