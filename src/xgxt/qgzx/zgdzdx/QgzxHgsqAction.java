package xgxt.qgzx.zgdzdx;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForward;

import com.zfsoft.basic.BasicAction;

import common.Globals;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.service.QgzxService;
import xgxt.qgzx.service.XsgwglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.ToolClass;
import xgxt.utils.String.StringUtils;

public class QgzxHgsqAction extends BasicAction {

	String writeAble = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String myAction = mapping.getParameter();
		ActionForward myActFwd = null;
		try {
			// �ж��û���дȨ
			writeAble = Base.getWriteAble(request);
			String dxq = request.getParameter("writeAble");
			if (!"".equalsIgnoreCase(dxq) && dxq != null) {
				writeAble = dxq;
			}

			if ("qgzxHgsq".equalsIgnoreCase(myAction)) {//��������
				myActFwd = qgzxHgsq(mapping, form, request, response);
			}
			if("zgdzdx_hg_Save".equalsIgnoreCase(myAction)){//�������뱣��
				myActFwd = zgdzdx_hg_Save(mapping, form, request, response);
			}
			if("qgzxHgsqsh".equalsIgnoreCase(myAction)){//����������˲�ѯ
				myActFwd = qgzxHgsqsh(mapping, form, request, response);
			}
			if("qgzxHgsqModi".equalsIgnoreCase(myAction)){//���������޸���ת
				myActFwd = qgzxhgsqModi(mapping, form, request, response);
			}
			if("zgdzdx_hg_SaveOne".equalsIgnoreCase(myAction)){//���������޸ı���
				myActFwd = zgdzdx_hg_SaveOne(mapping, form, request, response);
			}
			if("hgsqsh".equalsIgnoreCase(myAction)){//����������˲���
				myActFwd = hgsqsh(mapping, form, request, response);
			}
			if("qgzxCgsq".equalsIgnoreCase(myAction)){//�Ǹ�����
				myActFwd = qgzxCgsq(mapping, form, request, response);
			}
			if("zgdzdx_cg_Save".equalsIgnoreCase(myAction)){//�Ǹ����뱣��
				myActFwd = zgdzdx_cg_Save(mapping, form, request, response);
			}
			if("qgzxCgsqsh".equalsIgnoreCase(myAction)){//�Ǹ�������˲�ѯ
				myActFwd = qgzxCgsqsh(mapping, form, request, response);
			}
			if("cgsqsh".equalsIgnoreCase(myAction)){//�Ǹ�������˲���
				myActFwd = cgsqsh(mapping, form, request, response);
			}
			if("qgzxcgsqModi".equalsIgnoreCase(myAction)){//�Ǹ������޸�
				myActFwd = qgzxcgsqModi(mapping, form, request, response);
			}
			if("zgdzdx_cg_SaveOne".equalsIgnoreCase(myAction)){//�Ǹ������޸ı���
				myActFwd = zgdzdx_cg_SaveOne(mapping, form, request, response);
			}
			if("qgzxYrdwGhXsGwsq".equalsIgnoreCase(myAction)){//���˵�λ����ѧ����λ����
				myActFwd =  qgzxYrdwGhXsGwsq(mapping, form, request, response);
			}
			if("zgdzdx_yrdwghxssq_Save".equalsIgnoreCase(myAction)){//���˵�λ����ѧ����λ���뱣��
				myActFwd =  zgdzdx_yrdwghxssq_Save(mapping, form, request, response);
			}
			if("qgzxYrdwGhXsGwsh".equalsIgnoreCase(myAction)){//���˵�λ����ѧ����λ���
				myActFwd =  qgzxYrdwGhXsGwsh(mapping, form, request, response);
			}
			if("qgzxYrdwGhxsModi".equalsIgnoreCase(myAction)){//���˵�λ����ѧ����λ����ѧ����ϸ��Ϣ
				myActFwd =  qgzxYrdwGhxsModi(mapping, form, request, response);
			}
			if("qgzxYrdwGhxssh".equalsIgnoreCase(myAction)){//���˵�λ����ѧ����λ��˲���
				myActFwd =  qgzxYrdwGhxssh(mapping, form, request, response);
			}
			
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionForward("/login.jsp", false);
		}
	}

	public ActionForward qgzxHgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		XsgwglService xsgwglService = new XsgwglService();

		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		
		String userType = session.getAttribute("userOnLine").toString();

		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xxmc", xxmc);

		String xh = DealString.toGBK(request.getParameter("xh"));

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}

		// �ж��Ƿ���ƶ����
		if (!("".equalsIgnoreCase(xh) || xh == null)) {
			request.setAttribute("IsKns", dao.isKns(xh) == true ? "yes" : "no");
		}
		//��ȡ�й����ʴ�ѧѧ������ʱ���
		xsgwglService.freeTimeTableZgdzdx(xh, request);

		// ��ѯѧ��������Ϣ
		map = xsgwglService.getStuInfo(xh);

		// �ж��Ƿ������õ���������ʱ�䷶Χ�� tag Ϊempty��������ʱ����
		String tag = xsgwglService.checkTime();
		if ("empty".equalsIgnoreCase(tag)) {
			request.setAttribute("sqsjFlag", "1");
			map.put("xn", "");
			map.put("nd", "");
			map.put("xq", "");
			map.put("xqmc", "");
		} else {
			String[] tmp = Hgsqservice.getStuTimeService();
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("xq", tmp[2]);
			map.put("xqmc", tmp[3]);
		}

		// ��ȡ��λ�����б�
		List<HashMap<String, String>> gwList = Hgsqservice.getGwNameListService("no");
		request.setAttribute("gwList", gwList);

		if (xh != null && !"".equalsIgnoreCase(xh)) {//�ж��Ƿ�ƶ����
			map.put("sfpks", (dao.isKns(xh) == true) ? "��" : "��");
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);

		if (xh != null && !"".equalsIgnoreCase(xh)) {//�ж�ѧ���Ƿ�ͨ��ѧԺ�Ƽ�
			HashMap<String, String> timeMap = service.getSqsjInfo();
			String xn = timeMap.get("xn");
			String nd = timeMap.get("nd");
			String xq = timeMap.get("xq");
			
			String result = Hgsqservice.getXytjcountService(xh, xn, nd, xq);
			if (Integer.parseInt(result) < 1) {
				request.setAttribute("noCommend", "yes");
			}
			boolean flag = Hgsqservice.getGwshcountService(xh, xn, nd, xq);
			if (flag) {
				request.setAttribute("gwExists", "yes");
			}
			//��ȡѧ���ڸڸ�λ
			result = Hgsqservice.getGwdmService(xh, xn, nd, xq);
			map.put("gwdmgwsbsj", result);
			
		}

		request.setAttribute("do", "no");// �����ж��ǲ��ǽ����޸Ĳ�����no��ʾ�����޸Ĳ���
		request.setAttribute("rs", map);
		request.setAttribute("chkList", dao.getChkList(2));

		request.setAttribute("sqdwList", service.getYrdwList());
		return mapping.findForward("success");

	}
	
	private ActionForward qgzxhgsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		QgzxService service = new QgzxService();
		XsgwglService gwglService = new XsgwglService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xh = "";
		//��ȡ���õĲ���
		HashMap<String, String> timeMap = service.getSqsjInfo();
		
		//��ȡ��λ�����б�
		List gwList = Hgsqservice.getGwNameListService("no");
		request.setAttribute("gwList", gwList);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map = Hgsqservice.getQgzxOneService(pkValue);//������ϸ��Ϣ��ѯ
		XsgwglService xsgwglService = new XsgwglService();
		xsgwglService.freeTimeTableZgdzdx(pkValue.split("-")[0], request);
		
		//��ȡѧ���ڸڸ�λ
		String result = Hgsqservice.getGwdmService(pkValue.split("-")[0],timeMap.get("xn"), timeMap.get("nd"), timeMap.get("xq"));
		//�ڸڸ�λ
		map.put("gwdmgwsbsj", result);
		//�⻻�ڸ�λ
		String hgdmhgsj = map.get("hgdm")+"-"+map.get("hgsj");
		map.put("hgdmhgsj", hgdmhgsj);		
		//ѧ���Ƿ���������
		xh = map.get("xh");
		map.put("sfpks", service.isKns(xh) ? "��" : "��");
		
		gwglService.freeTimeTableGzdx(xh, request);//'����ʱ���'����
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}
	
	/**�й����ʴ�ѧ�������뱣��*/
	private ActionForward zgdzdx_hg_Save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		XsgwglService xsgwglService = new XsgwglService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();

		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tab = request.getParameter("tab");
		tab = StringUtils.isNull(tab) ? "" :tab;
		String tab1 = request.getParameter("tab1");
		tab1 = StringUtils.isNull(tab1) ? "" : tab1;
		
//		 ��������
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		
//		 ��λ����ʱ��
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		//����ʱ�䱣��
		Hgsqservice.updateQgzxTime(xh, request);
		//����������Ϣ����
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String hgdmhgsj1 = DealString.toGBK(request.getParameter("hgdmhgsj1"));
		String hgdmhgsj2 = DealString.toGBK(request.getParameter("hgdmhgsj2"));
		String hgdmhgsj3 = DealString.toGBK(request.getParameter("hgdmhgsj3"));
		String[] strGw = {hgdmhgsj1,hgdmhgsj2,hgdmhgsj3};
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("xssq"));
		String yhtc = DealString.toGBK(qgzxhgsqForm.getYhtc());
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		for(int i=0;i<strGw.length;i++){
			if(!"".equalsIgnoreCase(strGw[i]) && strGw[i] != null){
				String gw=strGw[i].split("-")[0];
				String sj=strGw[i].split("-")[1];
				String []tmp1  = {xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,yhtc,gw,sj,bz};
				boolean res = Hgsqservice.hgxx_saveService(tmp1);
				pkValue = xh+gwdm+gwsbsj+ gw+sj;
				if(res){
					request.setAttribute("inserted", "ok");
				}else{
					i++;
					request.setAttribute("inserted", "no");
					request.setAttribute("reason", "���ڼ�¼�Ѿ����ڣ�");
					break;
				}
			}
		}
		//��Ϣ��ѯ
		this.selectPageDataByOne(request, "zgdzdx_xshgxxb", "view_zgdzdx_xshgxxb", pkValue);		
		HashMap<String, String> rs = (HashMap<String, String>)request.getAttribute("rs");
		rs.put("hgdmhgsj1", rs.get("hgdm")+"-"+rs.get("hgsj"));
		rs.put("gwdmgwsbsj", rs.get("gwdm")+"-"+rs.get("gwsbsj"));
		rs.put("isKns", service.isKns(xh) ? "��" : "��");
		request.setAttribute("rs", rs);
		
		//��ȡ�й����ʴ�ѧѧ������ʱ���
		xsgwglService.freeTimeTableZgdzdx(xh, request);		
		request.setAttribute("gwExists", "yes");
		request.setAttribute("gwList", Hgsqservice.getGwNameListService("no"));
		request.setAttribute("chkList", dao.getChkList(2));
		request.setAttribute("sqdwList", service.getYrdwList());
		return mapping.findForward("success");
	}
	
	/**�й����ʴ�ѧ������Ϣ�޸ĵ�����¼����*/
	private ActionForward zgdzdx_hg_SaveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 1);
		
//		 ��������
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = dataSearchForm.getXh();
		
//		 ��λ����ʱ��
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		//����ʱ�䱣��
		Hgsqservice.updateQgzxTime(xh, request);
		//����������Ϣ����
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String hgdmhgsj1 = DealString.toGBK(request.getParameter("hgdmhgsj"));
		String[] strGw = {hgdmhgsj1};
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("xssq"));
		String yhtc = DealString.toGBK(qgzxhgsqForm.getYhtc());
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		for(int i=0;i<strGw.length;i++){
			if(!"".equalsIgnoreCase(strGw[i]) && strGw[i] != null){
				String gw=strGw[i].split("-")[0];
				String sj=strGw[i].split("-")[1];
				String[] tmp1 = {lxdh,xn,nd,xq,sqly,yhtc,gw,sj,bz};
				boolean res = Hgsqservice.hgxx_updateService(tmp1,pkValue);
				if(res){
					request.setAttribute("inserted", "ok");
				}else{
					i++;
					request.setAttribute("inserted", "no");
					request.setAttribute("reason", "�������λ�ظ�����Ч��");
					break;
				}
			}
		}
		
		return mapping.findForward("success");
	}
	
	/**�й����ʴ�ѧ�������*/
	private ActionForward qgzxHgsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		QgzxHgsqService service = new QgzxHgsqService();
		QgzxService qgzxService = new QgzxService();
		CommanForm model = (CommanForm) form;
		String tableName = "view_zgdzdx_xshgxxb";
		String realTable = "zgdzdx_xshgxxb";
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		boolean isYrdw = service.checkIsYrdwUser(userName);//�ж��Ƿ������˵�λ�û�
		
		StringBuffer querry = new StringBuffer();
		
		String go = request.getParameter("go");
		String tips = "�ڹ���ѧ - ��� - �����������";
		List<HashMap<String, String>> topTr = null;
		List<HashMap<String, String>> gwList = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		
		String[] colList = { "����", "�к�", "xh","xm", "gwdm","hgdm","sqsj",
				"xyyj", "xxyj", "lxdh", "xn", "nd", "xqmc"};
		String[] CNcolList = { "����", "�к�", "ѧ��","����", "�ڸڸ�λ","�����λ","����ʱ��",
				Base.YXPZXY_KEY+"���", "ѧУ���", "��ϵ�绰", "ѧ��", "���", "ѧ��"};
		topTr = dao.arrayToList(colList, CNcolList);
		
		if ("xy".equalsIgnoreCase(userType)) {
			model.setXydm(userDep);
		} 
		
		gwList = service.getGwListService(userName);
		List<HashMap<String, String>> gwxzList = service.getGwXzListService();
		querry = ToolClass.getHgxxQuery(model,isYrdw);
		if ("go".equalsIgnoreCase(go)) {//��ѯ
			vector.addAll(service.getHgXsService(querry, colList, userType));
			rsNum = String.valueOf(vector != null ? vector.size() : "");
		}
		
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", model.getXn());
		paramter.put("nd", model.getNd());
		paramter.put("xq", model.getXq());
		paramter.put("yrdwdm", model.getYrdwdm());
		paramter.put("gwxzdm", model.getGwxz());
		
		
		request.setAttribute("gwList",qgzxService.getGwmcxxList(paramter,"no"));//�������ͨ����λ�б�
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
//		request.setAttribute("gwList", gwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}
	
	/**�й����ʴ�ѧ������˲���*/
	private ActionForward hgsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		String pkValue = DealString.toGBK(request.getParameter("pkString"));
		String[] pkTmp = pkValue.split("!!SplitOneSplit!!");
		String type = DealString.toGBK(request.getParameter("type"));
		
		boolean res = Hgsqservice.hgsqshService(pkTmp,userType,type);
		
		request.setAttribute("result", res);
		return new ActionForward("/qgzxHgsqsh.do", false);
	}
	
	/**�й����ʴ�ѧ�Ǹ�����*/
	private ActionForward qgzxCgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		XsgwglService xsgwglService = new XsgwglService();
		XsxxglService xsxxglService = new XsxxglService();

		HttpSession session = request.getSession();
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> timeMap = service.getSqsjInfo();
		
		String userType = session.getAttribute("userOnLine").toString();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xxdm = StandardOperation.getXxdm();

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		
		String xn = timeMap.get("xn");
		String nd = timeMap.get("nd");
		String xq = timeMap.get("xq");
		
		//��ѯѧ��������Ϣ
		map = xsgwglService.getStuInfo(xh);
		if (xh != null && !"".equalsIgnoreCase(xh)) {
			boolean result = Hgsqservice.getGwshcountService(xh, xn, nd, xq);
			if (result) {
				request.setAttribute("gwExists", "yes");
			}
			map.put("xn", xn);
			map.put("nd", nd);
			map.put("xq", xq);
			map.put("xqmc", xsxxglService.getXqmc(xq));
		
			//��ȡѧ���ڸڸ�λ
			String xmdm = Hgsqservice.getGwdmService(xh, xn, nd, xq);
			map.put("gwdmgwsbsj", xmdm);
			map.put("gwdm", xmdm.split("-")[0]);
			
		}
		
		if("teacher".equalsIgnoreCase(userType)
				&& (xh==null || xh.equalsIgnoreCase(""))){
			request.setAttribute("gwExists", "yes");
		}
		request.setAttribute("rs", map);		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			// ���ݴ�ѧ
			request.setAttribute("gwList", Hgsqservice.getXsgwList(xh));
			return mapping.findForward("gwdx_xstgsq");
		}
		return mapping.findForward("success");
	}
	
	/**ѧ���Ǹ���Ϣ����
	 * @throws Exception */
	private ActionForward zgdzdx_cg_Save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String xxdm = StandardOperation.getXxdm();

		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String tab = request.getParameter("tab");
		tab = StringUtils.isNull(tab) ? "" :tab;
		String tab1 = request.getParameter("tab1");
		tab1 = StringUtils.isNull(tab1) ? "" : tab1;
		String modi = request.getParameter("modi");
		
//		 ��������
		pkValue = (pkValue == null) ? "" : pkValue;
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		
//		 ��λ����ʱ��
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			gwdmgwsbsj = dataSearchForm.getGwdm();
		}
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		
		boolean res=false;
		if("yes".equalsIgnoreCase(modi)){
			String[] tmp1  = {lxdh,sqly,bz,xh,gwdm,gwsbsj};
			 res = Hgsqservice.cgxx_saveService(tmp1,modi);
		}else{
			String[] tmp1  = {xh,gwdm,gwsbsj,lxdh,xn,nd,xq,sqly,bz};
			 res = Hgsqservice.cgxx_saveService(tmp1,modi);
		}
		if(res){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
			if("yes".equalsIgnoreCase(modi)){
				request.setAttribute("reason", "�����ԣ�");
			}
			request.setAttribute("reason", "�Ǹ��������ύ�������ظ����룡");
		}
		return new ActionForward("/qgzxCgsq.do", false);
	}
	
	/**�Ǹ�������˲�ѯ*/
	private ActionForward qgzxCgsqsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		QgzxHgsqService service = new QgzxHgsqService();
		QgzxService qgzxService = new QgzxService();
		CommanForm model = (CommanForm) form;
		String xxdm = StandardOperation.getXxdm();
		String tableName = "view_zgdzdx_xscgxxb";
		String realTable = "zgdzdx_xscgxxb";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		boolean isYrdw = service.checkIsYrdwUser(userName);//�ж��Ƿ������˵�λ�û�
		
		StringBuffer querry = new StringBuffer();
		
		String go = request.getParameter("go");
		String tips = "�ڹ���ѧ - ��� - �Ǹ��������";
		List<HashMap<String, String>> topTr = null;
		List<HashMap<String, String>> gwList = null;
		ArrayList<String[]> vector = new ArrayList<String[]>();
		String rsNum = "";
		
		String[] colList = { "����", "�к�", "xh","xm", "gwdm","sqsj",
				"xyyj", "xxyj", "lxdh", "xn", "nd", "xqmc"};
		String[] CNcolList = { "����", "�к�", "ѧ��","����", "�Ǹڸ�λ","����ʱ��",
				Base.YXPZXY_KEY+"���", "ѧУ���", "��ϵ�绰", "ѧ��", "���", "ѧ��"};
		topTr = dao.arrayToList(colList, CNcolList);
		
		if("xy".equalsIgnoreCase(userType)){
			//ѧԺ�û�
			model.setXydm(userDep);
		}
		if (isYrdw) {
			gwList = service.getGwListForXydmService(userName);
		} else {
			gwList = service.getGwListService(userName);
		}
		List<HashMap<String, String>> gwxzList = service.getGwXzListService();
		model.setUserName(userName);
		querry = ToolClass.getCgxxQuery(model,isYrdw);
		if ("go".equalsIgnoreCase(go)) {//��ѯ
			vector.addAll(service.getCgXsService(querry, colList, userType));
			rsNum = String.valueOf(vector != null ? vector.size() : "");
		}
		
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", model.getXn());
		paramter.put("nd", model.getNd());
		paramter.put("xq", model.getXq());
		paramter.put("yrdwdm", model.getYrdwdm());
		paramter.put("gwxzdm", model.getGwxz());
		
		
		request.setAttribute("gwList",qgzxService.getGwmcxxList(paramter,"no"));//�������ͨ����λ�б�
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
//		request.setAttribute("gwList", gwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		FormModleCommon.commonRequestSet(request, tableName, realTable, vector, topTr);
		return mapping.findForward("success");
	}
	
	/**�Ǹ�������˲���*/
	private ActionForward cgsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		String pkValue = DealString.toGBK(request.getParameter("pkString"));
		String[] pkTmp = pkValue.split("!!SplitOneSplit!!");
		String type = DealString.toGBK(request.getParameter("type"));
		
		@SuppressWarnings("unused")
		boolean res = Hgsqservice.cgsqshService(pkTmp,userType,type);
		return new ActionForward("/qgzxCgsqsh.do", false);
	}
	
	private ActionForward qgzxcgsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		QgzxService service = new QgzxService();
		
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> timeMap = service.getSqsjInfo();
		
		map = Hgsqservice.getQgzxcgOneService(pkValue);
		if(map!=null){
			request.setAttribute("gwExists", "yes");
		}
		//��ȡѧ���ڸڸ�λ
		String result = Hgsqservice.getGwdmService(pkValue.split("-")[0],timeMap.get("xn"), timeMap.get("nd"), timeMap.get("xq"));
		map.put("gwdm", result.split("-")[0]);
		map.put("gwdmgwsbsj", result);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		
		return mapping.findForward("success");
	}
	
	private ActionForward zgdzdx_cg_SaveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		CommanForm dataSearchForm = (CommanForm) form;
		CommanForm qgzxhgsqForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();

		String modi = request.getParameter("modi");
		
		//��������
		String xh = "";
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = dataSearchForm.getXh();
		}
		
		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String gwdm = "";
		String gwsbsj = "";
		if("".equalsIgnoreCase(gwdmgwsbsj) || gwdmgwsbsj == null){
			gwdmgwsbsj = DealString.toGBK(request.getParameter("xmdmmodi"));
		}
		if(!"".equalsIgnoreCase(gwdmgwsbsj) && gwdmgwsbsj != null){
			gwdm = gwdmgwsbsj.split("-")[0];
			gwsbsj = gwdmgwsbsj.split("-")[1];
		}else{
			gwdm = DealString.toGBK(request.getParameter("gwdm"));
			gwsbsj = request.getParameter("gwsbsj");
		}
		
		String lxdh = qgzxhgsqForm.getLxdh();
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String bz = DealString.toGBK(qgzxhgsqForm.getBz());
		
		boolean res=false;
		String[] tmp1  = {lxdh,sqly,bz,xh,gwdm,gwsbsj};
		res = Hgsqservice.cgxx_saveService(tmp1,modi);
		
		if(res){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
			request.setAttribute("reason", "�����ԣ�");
		}
		String pkValue=xh+"-"+gwdm+"-"+gwsbsj;
		HashMap<String, String> map = new HashMap<String, String>();
		map = Hgsqservice.getQgzxcgOneService(pkValue);
		if(map!=null){
			request.setAttribute("gwExists", "yes");
		}
		map.put("gwdm", gwdm);
		map.put("gwdmgwsbsj", gwdm+"-"+gwsbsj);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	
	/**���˵�λ����ѧ����λ����
	 * @throws Exception */
	private ActionForward qgzxYrdwGhXsGwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String xxdm = StandardOperation.getXxdm();
		String page = "success";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = request.getSession().getAttribute("userName").toString();
		
		List<HashMap<String, String>> gwList = new ArrayList<HashMap<String,String>>();
		if ("xy".equalsIgnoreCase(userType)) {
			String xydm = userDep;
			gwList = Hgsqservice.getGwListForXydmService(xydm);
		} 
//		if(service.isYrdwUser(userName)){
			gwList = Hgsqservice.getGwListService(userName);
//		}
		//������ʱ���е�ѧ����¼
		Hgsqservice.deleteYrdwghxslsbSerivce(userName);
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			page = "gzdx_xyghgwsq";
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map = Hgsqservice.getYrdwxxService(userName);
		map.put("userName", userName);
		
		request.setAttribute("path", "qgzxYrdwGhXsGwsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gwList", gwList);
		request.setAttribute("rs", map);
		return mapping.findForward(page);
	}
	
	/**�й����ʴ�ѧ���˵�λ����ѧ�����뱣��
	 * @throws Exception */
	private ActionForward zgdzdx_yrdwghxssq_Save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userName = request.getSession().getAttribute("userName").toString();
		
//		String gwdmgwsbsj = DealString.toGBK(request.getParameter("gwdmgwsbsj"));
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		
//		 ��λ����ʱ��
		String[] tmp = Hgsqservice.getStuTimeService();
		String xn = tmp[0];
		String nd = tmp[1];
		String xq = tmp[2];
		
		boolean res = false;
		/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
		ArrayList<String[]> ctList = Hgsqservice.getYrdwctxslsxxserivce(userName);
		if(ctList!=null){
			for(int i=0;i<ctList.size();i++){
				res = Hgsqservice.insertYrdwghXsService(ctList.get(i),xn,nd,xq,sqly,bz);//��ʱ������Ϣ������ʽ��
				if(!res){
					request.setAttribute("insert", "no");					
					return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
				}
			}
		}
		
		/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
		ArrayList<String[]> ghList = Hgsqservice.getYrdwghxslsxxserivce(userName);
		if(ghList!=null){
			for(int i=0;i<ghList.size();i++){
				res = Hgsqservice.insertYrdwghXsService(ghList.get(i),xn,nd,xq,sqly,bz);//��ʱ������Ϣ������ʽ��
				if(!res){
					request.setAttribute("insert", "no");
					return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
				}
			}
		}
		
		/**�й����ʴ�ѧ��ȡ���˵�λ����ѧ����ʱ��Ϣ*/
		ArrayList<String[]> sqList = Hgsqservice.getYrdwsqxslsxxserivce(userName);
		if(sqList!=null){
			for(int i=0;i<sqList.size();i++){
				res = Hgsqservice.insertYrdwghXsService(sqList.get(i),xn,nd,xq,sqly,bz);//��ʱ������Ϣ������ʽ��
				if(!res){
					request.setAttribute("insert", "no");
					return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
				}
			}
		}
		return new ActionForward("/qgzxYrdwGhXsGwsq.do", false);
	}
	
	private ActionForward qgzxYrdwGhXsGwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CommanForm model = (CommanForm)form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		QgzxService service = new QgzxService();
		String tableName = "view_zgdzdx_yrdwghxsxxb";
		String realTable = "zgdzdx_yrdwghxsb";
//		String pk = "xh||gwdm||gwsbsj";
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = session.getAttribute("userName").toString();
		String xydm = request.getParameter("xydm");
		String gwdm = DealString.toGBK(request.getParameter("gwdm"));
//		String hgdm = DealString.toGBK(request.getParameter("hgdm"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
//		String nj = request.getParameter("nj");
		String gwxz = request.getParameter("gwxz");
		
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		
		String go = request.getParameter("go");
		
		List topTr = null;
		List gwList = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		
		String[] colList = { "����", "�к�", "xh","xm", "gwdm","sqsj",
				"xxyj", "lxdh", "xn", "nd", "xqmc","bj"};
		String[] CNcolList = { "����", "�к�", "ѧ��","����", "��λ","����ʱ��",
				"ѧУ���", "��ϵ�绰", "ѧ��", "���", "ѧ��","��������"};
		topTr = dao.arrayToList(colList, CNcolList);
		
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			model.setXydm(xydm);
		}
		
		gwList = Hgsqservice.getGwListService(userName);
		
		List gwxzList = Hgsqservice.getGwXzListService();
		querry = ToolClass.getYrdwGhXsxxQuery(xh, gwdm, "", "", "", "", xn, "", xq, "", "",gwxz,xydm,bj, querry);
		if ("go".equalsIgnoreCase(go)) {//��ѯ
			vector.addAll(Hgsqservice.getYrdwGhXsService(querry, colList, userType));
			rsNum = String.valueOf(vector != null ? vector.size() : "");
		}
		
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", model.getXn());
		paramter.put("nd", model.getNd());
		paramter.put("xq", model.getXq());
		paramter.put("yrdwdm", model.getYrdwdm());
		paramter.put("gwxzdm", model.getGwxz());
		
		
		request.setAttribute("gwList",service.getGwmcxxList(paramter,"no"));//�������ͨ����λ�б�
		request.setAttribute("path", "qgzxYrdwGhXsGwsh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("topTr", topTr);
//		request.setAttribute("gwList", gwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("bj", bj);
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}
	
	/**�й����ʴ�ѧ���˵�λ����ѧ����ϸ��Ϣ*/
	private ActionForward qgzxYrdwGhxsModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
//		HttpSession session = request.getSession();
//		String userType = session.getAttribute("userOnLine").toString();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			
			HashMap<String, String> map = new HashMap<String, String>();
			map = Hgsqservice.getYrdwGhXsOneService(pkValue);
			if(map!=null){
				request.setAttribute("gwExists", "yes");
			}
//			��ȡѧ���ڸڸ�λ
//			String result = Hgsqservice.getGwdmService(pkValue.split("-")[0], Base.currXn, Base.currNd, Base.currXq);
//			map.put("gwdm", result.split("-")[0]);
//			map.put("gwdmgwsbsj", result);
			request.setAttribute("rs", map);
			request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}
	
	/**���˵�λ����ѧ����λ������˲���*/
	private ActionForward qgzxYrdwGhxssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		QgzxHgsqService Hgsqservice = new QgzxHgsqService();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		String pkValue = DealString.toGBK(request.getParameter("pkString"));
		String[] pkTmp = pkValue.split("!!SplitOneSplit!!");
		String type = DealString.toGBK(request.getParameter("type"));
		
		@SuppressWarnings("unused")
		boolean res = Hgsqservice.yrdwGhxsshService(pkTmp,userType,type);
		
		return new ActionForward("/qgzxYrdwGhXsGwsh.do", false);
	}
}
