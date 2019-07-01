package xgxt.pjpy.ycsfxy;

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
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * deprecated �γ�ʦ������ACTION
 * author litao
 * time 2009.9.24
 */
public class PjpyYcsfxyAction extends CommonAction {

	private PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
	
	private String act;//��������
	
//	private String xydm;
	/**
	 * �ۺ�����-ƽʱ���׶ο��˳ɼ�ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjdkhcjWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString()
				: "";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		if ("xy".equalsIgnoreCase(userType)) {
			ycsfForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
			userType = "fdy";
		}
		
		//Ĭ��ѡ��Ϊ����ѧ�꣬ѧ��
		if (StringUtils.isNull(ycsfForm.getXn())) {
			ycsfForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(ycsfForm.getXq())) {
			ycsfForm.setXq(Base.getJxjsqxq());
		}
		
		act = request.getParameter("go");
		//���ݲ�ѯ
		if ("go".equalsIgnoreCase(act)) {
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			topTr = service.getPsjdkhQueryTitle();
			int count = service.getPsjdkhQueryResultCount(model, userType, userName);
			rs = service.getPsjdkhQueryResult(model, userType, userName, ycsfForm);//����ҳ��ѯ
			ycsfForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//���û���дȨ�����ж�
		request.setAttribute("path", "pjpy_ycsf_pjjdkhcjwh.do");
		unit.commonRequestSet(request, "pjpy_ycsf_zhszcpb", "pjpy_ycsf_zhszcpb",  rs, topTr);
		//����ѧԺ��רҵ���༶�����б�
		setNjXyZyBjList(request, ycsfForm);
		
		request.setAttribute("userType", userType);
		ycsfForm.setXm(DealString.toGBK(ycsfForm.getXm()));
		ycsfForm.setXh(DealString.toGBK(ycsfForm.getXh()));
		return mapping.findForward("pjjdkhcjWh");
	}
	
	/**
	 * ����ƽʱ���׶ο��˳ɼ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjdkhcjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] pkList = request.getParameterValues("pkvalue");//����ֵ
		String[] pjkhcj = request.getParameterValues("pjkhcj");//ƽʱ���˳ɼ�ֵ
		String[] jdkhcj = request.getParameterValues("jdkhcj");//�׶ο��˳ɼ�ֵ
		String[] dis = request.getParameterValues("dis");//ѧУ��˱�־,�����������ѧУ��������ܽ��в���
		//ִ�����ݱ������
		boolean result = service.savePjjdkhcjxx(pkList, pjkhcj, jdkhcj, dis);
		request.setAttribute("result", result);
		
		//��������б���Ϣ
		pjjdkhcjWh(mapping, form, request, response);
		return mapping.findForward("pjjdkhcjWh");
	}
	
	/**
	 * ɾ��ƽʱ���׶ο��˳ɼ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjdkhcjDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] pkList = request.getParameterValues("cbv");
		String failinfo = "";
		boolean result = true;
		if (pkList != null && pkList.length > 0) {
			failinfo = service.deletePjjdkhcjxx(pkList);
		}
		if (!StringUtils.isNull(failinfo)) {
			result = false;
			request.setAttribute("failinfo", "�������,���е�" + failinfo + "�����ݲ���ʧ��!");
		}
		
		//��������б���Ϣ
		pjjdkhcjWh(mapping, form, request, response);
		request.setAttribute("result", result);
		return mapping.findForward("pjjdkhcjWh");
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
			PjpyYcsfxyActionForm myForm) throws Exception,
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
	
	/**
	 * �ۺ����ʲ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setZhszcpBl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		boolean flag = false;
		String xxdm = Base.xxdm;
		if(Globals.XXDM_XMLGXY.equalsIgnoreCase(Base.xxdm)||
				Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(Base.xxdm)){
			map.put("pskhcjbl", "�������ֱַ���");
			map.put("xykhcjbl", "�������ֱַ���");
			map.put("jdkhcjbl", "������ֱַ���");
			request.setAttribute("showXmlgxy","");
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			map.put("pskhcjbl", "ѧҵ������ռ����");
			map.put("jdkhcjbl", "��Ϊ���ַ���ռ����");
			map.put("zhbxfbl", "ͻ�����ַ���ռ����");
			map.put("xykhcjbl", "�ۺϱ��ַ���ռ����");
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			map.put("pskhcjbl", "�����ɼ�");
			map.put("xykhcjbl", "�����ɼ�");
			map.put("jdkhcjbl", "�����ɼ�");
		} else{
			map.put("pskhcjbl", "ƽʱ���˳ɼ�");
			map.put("xykhcjbl", "ѧҵ���˳ɼ�");
			map.put("jdkhcjbl", "�׶ο��˳ɼ�");
		}
		try{
			if("save".equals(myForm.getAct())){
				BeanUtils.copyProperties(model,myForm);
				flag = service.setZhszcpBl_ser(model);
				if(flag){
					request.setAttribute("result", "yes");
				}else{
					request.setAttribute("result", "no");
				}
			}else{
				model = service.getZhszcpBl_ser();
				BeanUtils.copyProperties(myForm,model);
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", " no");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("setzhszcpbl");
	}
	
	/**
	 * �ۺ����ʲ���ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhszcpwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String)session.getAttribute("userName");
		String[] chkValue = request.getParameterValues("cbv");
		String xydm = "";
		if("xy".equals(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}else if("stu".equals(userType)){
			return new ActionForward("/pjpy_ycsf_stuZhszcpQuery.do");
		}
		if(session.getAttribute("fdyQx") != null
				&& "true".equals((String)session.getAttribute("fdyQx")
						.toString())){
			userType = "fdy";
			request.setAttribute("userType", "fdy");
		}
		xydm = myForm.getXydm() == null ? "":myForm.getXydm();
		String zydm = myForm.getZydm() == null ? "":myForm.getZydm();
		String nj = myForm.getNj() == null ? "":myForm.getNj();
		String bjKey = xydm + "!!" + zydm + "!!" + nj;	
		
		try{
			BeanUtils.copyProperties(model, myForm);
			if ("query".equals(myForm.getAct())) {
				String[] colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc", "pjkhcj", "xykhcj", "jdkhcj", "zhszcpzf",
						"pm","xxsh" };
				String[] colCn = new String[] { "pk", "ѧ��", "����", "ѧ��", "ѧ��",
						"�꼶", "�༶����", "ƽʱ���˳ɼ�", "�׶ο��˳ɼ�", "ѧҵ���˳ɼ�","ѧҵ�༶����", "�۲�ɼ�",
						"�۲�༶����", "ѧУ���" };
				List topTr = service.getTabName_ser(colEn, colCn);
				String isFdy = session.getAttribute("fdyQx") == null ? ""
						: session.getAttribute("fdyQx").toString();
				int count = service.zhszcpQueryCount_ser(model, userName, isFdy);
				myForm.getPages().setMaxRecord(count);// �������ļ�¼��
				List<String[]> rs = service.zhszcpQuery_ser(myForm, model,
						userName, isFdy);
				request.setAttribute("rs", rs);
				request.setAttribute("topTr", topTr);
			} else if ("compute".equals(myForm.getAct())) {
				request.setAttribute("result", service.computeZhszcp_ser(userType,xydm,userName));
			} else if("sh_all".equals(myForm.getAct())){
				request.setAttribute("result", service.zhszcpShAll_ser());
			} else if("sh_part".equals(myForm.getAct()) || "sh_btg".equals(myForm.getAct())
					||  "sh_cx".equals(myForm.getAct())){
				request.setAttribute("result", service.zhszcpShPart_ser(chkValue,myForm.getAct()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(myForm.getXn() == null){
			myForm.setXn(Base.getJxjsqxn());
		}
		if(myForm.getXq() == null){
			myForm.setXq(Base.getJxjsqxq());
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		List<HashMap<String,String>> bjList = (Base.getBjMap()).get(bjKey) ==null ? 
				new ArrayList<HashMap<String,String>>() :(Base.getBjMap()).get(bjKey);
		request.setAttribute("tableName", "view_ycsf_zhszcp");
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", bjList);
		if (session.getAttribute("fdyQx") != null
				&& "true".equals((String)session.getAttribute("fdyQx")
						.toString())) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ���Ͱ༶�б�
		}
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * ��ʦ�鿴ѧ���ɼ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward viewxskccj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String pk = request.getParameter("pk");
		String view = request.getParameter("view");
		String psjd = request.getParameter("psjd");
		String zhszcp = request.getParameter("zhszcp");
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		CommonAction comm = new CommonAction();
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		HashMap<String, String> shMap = null;
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
		}
		String pkVal = "";
		try {
			BeanUtils.copyProperties(model, myForm);
			if ("save".equals(myForm.getAct())){
				request.setAttribute("result", service.jxjOrRychDgsh_ser(pk,
						model,userType));
			}
			if (Base.isNull(myForm.getDm())) {
				pkVal = pk;

			} else {
				pkVal = pk.substring(0, pk.length() - myForm.getDm().length());
				shMap = service.getJxjOrRychShxx_ser(myForm.getLb(), pk);

				comm.appendJxjList(request);
				comm.appendRychList(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("yes".equals(psjd)){
			request.setAttribute("xsxxmap", service.getXsxxAndPsjdcj_ser(pkVal));
			request.setAttribute("psjd", psjd);
		}else{
			request.setAttribute("xsxxmap", service.getXsxxAndCjhz_ser(pkVal));
			if("yes".equals(zhszcp)){
				request.setAttribute("psjd", zhszcp);
			}
		}
		request.setAttribute("view", view);
		request.setAttribute("lb", myForm.getLb());
		request.setAttribute("pk", pk);
		request.setAttribute("rs", service.getXscj_ser(pkVal));
		request.setAttribute("shMap", shMap);
		return mapping.findForward("viewxskccj");
	}
	
	/**
	 * ѧ���ۺ����ʲ����ɼ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward stuZhszcpQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String xh = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString()
				: ""; 
		List<String[]> rs = service.stuQueryZhszcpxx(xh);
		request.setAttribute("rs", rs);
		return mapping.findForward("stuZhszcpQuery");
	}
	
	/**
	 * ѧ���ۺ����ʲ�����ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward stuZhszcpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pk = request.getParameter("pk");
		HashMap<String, String> rs = service.stuZhszcpView(pk);
		List<String[]> cjList = service.stuCjInfo(pk);
		request.setAttribute("rs", rs);
		request.setAttribute("cjList", cjList);
	
		return mapping.findForward("stuZhszcpView");
	}
	
	/**
	 * ��ѧ�������ƺ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jxjRychSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		CommonAction comm = new CommonAction();
		String[] chkValue = request.getParameterValues("cbv");
		String xydm = myForm.getXydm() == null ? "":myForm.getXydm();
		String zydm = myForm.getZydm() == null ? "":myForm.getZydm();
		String nj = myForm.getNj() == null ? "":myForm.getNj();;
		String bjKey = xydm + "!!" + zydm + "!!" + nj;	
		HttpSession session = request.getSession();
		List<String[]> rs = null;
		String userType = session.getAttribute("userType") != null ? session.getAttribute("userType").toString():"";
		String userDep = session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString():"";
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
		}
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
			request.setAttribute("xydm", userDep);
		}
		try{
			BeanUtils.copyProperties(model, myForm);
			if ("query".equals(myForm.getAct())) {
				
				List topTr = service.getJxjOrRychTabName(model.getLb());
				int count = service.getJxjOrRychCount_db(model);
				myForm.getPages().setMaxRecord(count);// �������ļ�¼��
				if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
					rs = service.getJxjOrRychList_ser(myForm, model,userType);
				}else{
					rs = service.getJxjOrRychQueryList_ser(myForm, model);
				}
				request.setAttribute("rs", rs);
				request.setAttribute("topTr", topTr);
			} else if("sh_all".equals(myForm.getAct())){
				request.setAttribute("result", service.jxjOrRychShAll_ser(userType,userDep));
			} else if("sh_part".equals(myForm.getAct()) || "sh_btg".equals(myForm.getAct()) 
					|| "sh_cx".equals(myForm.getAct())){
				request.setAttribute("result", service.jxjOrRychShPart_ser(chkValue,myForm.getAct(),model.getLb(),userType,userDep));
			}
			comm.appendJxjList(request);
			comm.appendRychList(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());
		if(myForm.getLb() == null){
			myForm.setLb("jxj");
		}
		request.setAttribute("form", myForm);
		request.setAttribute("lb", myForm.getLb());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		return mapping.findForward("jxjrychsh");
	}
	
	/**
	 * ѧԺ�������ϱ�Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xyhjmdSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String tableName = "";
		String realTable = "";
		if (StringUtils.isNull(ycsfForm.getXn())) {
			ycsfForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(ycsfForm.getXq())) {
			ycsfForm.setXq(Base.getJxjsqxq());
		}
		
		String lb = ycsfForm.getLb();
		if (StringUtils.isNull(lb)) {
			lb = "jxj";
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			realTable = "view_ahzyjs_xsjxjb";
			if ("rych".equalsIgnoreCase(lb)) {
				realTable = "view_ahzyjs_xsrychb";
			}
		}else{
			realTable = "view_ycsf_xsjxjb";
			if ("rych".equalsIgnoreCase(lb)) {
				realTable = "view_ycsf_xsrychb";
			}
		}
		tableName = "xsjxjb";
		if ("rych".equalsIgnoreCase(lb)) {
			tableName = "xsrychb";
		}
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString()
				: "";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		if ("xy".equalsIgnoreCase(userType)) {
			ycsfForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
			userType = "fdy";
		}
		
		act = request.getParameter("act");
		//���ݲ�ѯ
		if ("query".equalsIgnoreCase(act)) {
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			topTr = service.queryHjmdxxTitle();
			rs = service.queryHjmdxxResult(model, userType, userName, ycsfForm);
			int count = service.queryHjmdxxResultCount(model, userType, userName, ycsfForm);
			ycsfForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//���û���дȨ�����ж�
		request.setAttribute("path", "pjpy_ycsf_xyhjmdsb.do");
		unit.commonRequestSet(request, realTable, tableName,  rs, topTr);
		//����ѧԺ��רҵ���༶�����б�
		setNjXyZyBjList(request, ycsfForm);
		request.setAttribute("userType", userType);
		ycsfForm.setXm(DealString.toGBK(ycsfForm.getXm()));
		ycsfForm.setXh(DealString.toGBK(ycsfForm.getXh()));
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("lb", lb);
		return mapping.findForward("xyhjmdSb");
	}
	
	/**
	 * ���ӻ�������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addHjmdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String lb = request.getParameter("lb");//�������Ŀ���
		String xh = request.getParameter("xh");
		String act = request.getParameter("act");//�������ݱ�־
		if (StringUtils.isNull(lb)) {
			lb = "jxj";
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		List<String[]> cjList = new ArrayList<String[]>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh,Base.xxdm);//��ѯѧ����Ϣ���۲���Ϣ
			cjList = service.stuCjInfo(xh, Base.getJxjsqxn(), Base.getJxjsqxq());//��ѯѧ���ɼ���Ϣ
			
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
				userType = "fdy";
			}
			request.setAttribute("userType", userType);
		}
		if ("save".equalsIgnoreCase(act)) {//��������,
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			model.setXn(Base.getJxjsqxn());
			model.setXq(Base.getJxjsqxq());
			boolean result = service.saveHjmdxx(model, userType, lb);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			
		}
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxqmc());
		request.setAttribute("rs", rs);
		request.setAttribute("cjList", cjList);
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("lb", lb);
		return mapping.findForward("addHjmdxx");
	}
	
	/**
	 * ɾ����������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delHjmdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String type = request.getParameter("type");
		String[] pkList = request.getParameterValues("cbv");
		boolean result = service.deleteHjmdxx(pkList, type);//ɾ�����ݲ���
		request.setAttribute("result", result);
		xyhjmdSb(mapping, form, request, response);//�����б���Ϣ
		return mapping.findForward("xyhjmdSb");
	}
	
	/**
	 * �޸Ļ�������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hjmdxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String lb = request.getParameter("lb");//�������
		lb = StringUtils.isNull(lb) ? "jxj" : lb;
		String pkValue = request.getParameter("pk");//����
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		if (Globals.XXDM_AHZYJSXY.equals(Base.xxdm)) {
			request.setAttribute("ahzyjsxy", "yes");
			if (Fdypd.isFdy(request.getSession().getAttribute("userName")
					.toString())) {
				userType = "fdy";
				request.setAttribute("userType", userType);
			}
		} 
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {//�����޸Ĳ���
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			boolean result = service.modiHjmdxx(userType, lb, pkValue, model);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		HashMap<String, String> rs = service.viewHjmdxx(lb, userType, pkValue);//��ʾ�޸���Ϣ
		if ("jxj".equalsIgnoreCase(lb)) {
			rs.put("lb", "��ѧ��");
		} else {
			rs.put("lb", "�����ƺ�");
		}
		if("fdy".equals(userType)){
			if ("δ���".equalsIgnoreCase(rs.get("xysh"))) {
				request.setAttribute("modi", "true");
			}
		}else if("xy".equals(userType)){
			if ("δ���".equalsIgnoreCase(rs.get("xxsh"))) {
				request.setAttribute("modi", "true");
			}
		}else{
			request.setAttribute("modi", "true");
		}
		
		//ycsfForm.setDm(rs.get("dm"));
		//ycsfForm.setXyshyj(rs.get("xyshyj"));
		List<String[]> cjList = new ArrayList<String[]>();//ѧ���ɼ��б�
		cjList = service.stuCjInfo(rs.get("xh"), rs.get("xn"), rs.get("xq"));
		request.setAttribute("cjList", cjList);
		
		request.setAttribute("lb", lb);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		appendJxjList(request);
		appendRychList(request);
		return mapping.findForward("hjmdxxModi");
	}
	
	/**
	 * ѧԺ���������ϱ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hjmdSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String xhList = request.getParameter("xhList");
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				
		String lb = request.getParameter("lb");//�������
		lb = StringUtils.isNull(lb) ? "jxj" : lb;
		if ("jxj".equalsIgnoreCase(lb)) {
			request.setAttribute("lbmc", "��ѧ��");
		} else {
			request.setAttribute("lbmc", "�����ƺ�");
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
				userType = "fdy";
				request.setAttribute("userType", userType);
			}
		}
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			model.setXn(Base.getJxjsqxn());
			model.setXq(Base.getJxjsqxq());
			String[] xhArray = !StringUtils.isNull(xhList) ? xhList.split("!@") : new String[]{};
			boolean result = service.hjmdPlsb(userType, model, xhArray);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		request.setAttribute("lb", lb);
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("xq", Base.getJxjsqxqmc());
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("xhList", xhList);
		return mapping.findForward("hjmdSb");
	}
	
	/**
	 * ��������ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hjmdxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
//		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String pkValue = request.getParameter("pk");
		String lb = request.getParameter("lb");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			if (Fdypd.isFdy(request.getSession().getAttribute("userName")
					.toString())) {
				userType = "fdy";
				request.setAttribute("userType", userType);
			}
		}
		model.setXn(xn);
		model.setXq(xq);
		model.setXh(xh);
		HashMap<String, String> rs = service.viewHjmdxx(model, pkValue, userType, lb);
		if ("jxj".equalsIgnoreCase(lb)) {
			rs.put("lb", "��ѧ��");
		} else {
			rs.put("lb", "�����ƺ�");
		}
		//ycsfForm.setDm(rs.get("dm"));
		//ycsfForm.setXyshyj(rs.get("xyshyj"));
		List<String[]> cjList = service.stuCjInfo(xh, xn, xq);
		request.setAttribute("rs", rs);
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("lb", lb);
		request.setAttribute("cjList", cjList);
		return mapping.findForward("hjmdxxView");
	}
	
	/**
	 * ��ѧ�������ƺ���˽����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryShResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String)session.getAttribute("userName");
		CommonAction comm = new CommonAction();
		String xydm = "";
		String tableName = "";
		String realTable = "";
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			tableName = "view_ahzyjs_xsjxjb";
			if ("rych".equalsIgnoreCase(myForm.getLb())) {
				tableName = "view_ahzyjs_xsrychb";
			}
		}else{
			tableName = "view_ycsf_xsjxjb";
			if ("rych".equalsIgnoreCase(myForm.getLb())) {
				tableName = "view_ycsf_xsrychb";
			}
		}
		realTable = "xsjxjb";
		if ("rych".equalsIgnoreCase(myForm.getLb())) {
			realTable = "xsrychb";
		}
		if("xy".equals(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}else if("stu".equals(userType)){
			request.setAttribute("jxjrs", service.queryXsJxj_ser(userName));
			request.setAttribute("rychrs", service.queryXsRych_ser(userName));
			return mapping.findForward("studentQueryResult");
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
		}
		xydm = myForm.getXydm() == null ? "":myForm.getXydm();
		String zydm = myForm.getZydm() == null ? "":myForm.getZydm();
		String nj = myForm.getNj() == null ? "":myForm.getNj();
		String bjKey = xydm + "!!" + zydm + "!!" + nj;	
		try{
			BeanUtils.copyProperties(model, myForm);
			if ("query".equals(myForm.getAct())) {
				List topTr = service.getJxjOrRychTabName(model.getLb());
				String isFdy = session.getAttribute("fdyQx") == null ? ""
						: session.getAttribute("fdyQx").toString();
				int count = service.queryShResultCount_ser(model, userName, isFdy);
				myForm.getPages().setMaxRecord(count);// �������ļ�¼��
				List<String[]> rs = service.queryShResult_ser(myForm, model,
						userName, isFdy);
				request.setAttribute("rs", rs);
				request.setAttribute("topTr", topTr);
			} 
			comm.appendJxjList(request);
			comm.appendRychList(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(myForm.getXn() == null){
			myForm.setXn(Base.getJxjsqxn());
		}
		if(myForm.getXq() == null){
			myForm.setXq(Base.getJxjsqxq());
		}
		if(myForm.getLb() == null){
			myForm.setLb("jxj");
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		request.setAttribute("form", myForm);
		request.setAttribute("lb", myForm.getLb());
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx") != null
				&& "true".equals((String)session.getAttribute("fdyQx")
						.toString())) {
			// ����Ա��¼
			request.setAttribute("userType", "fdy");
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ���Ͱ༶�б�
		}
		return mapping.findForward("teacherQueryResult");
	}
	
	/**
	 * ��ѧ�������ƺ���˽����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expBjcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			BeanUtils.copyProperties(model, myForm);
			service.expBjcj_ser(model, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
