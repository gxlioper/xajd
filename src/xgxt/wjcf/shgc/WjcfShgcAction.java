package xgxt.wjcf.shgc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Ϻ����̼�����ѧΥ�ʹ���Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-19</p>
 */
public class WjcfShgcAction extends DispatchAction {
	
	String xydm = "";
	String zydm = "";
	String nj = "";
	String xq = "";
	
	/**
	 * Υ�ʹ����걨Ĭ��ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward shgcXysbDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
//		String xh = "";
		DAO dao = DAO.getInstance();
		String xxdm=dao.getXxdm();
		String userType = request.getSession().getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("student")) {
//			xh = request.getSession().getAttribute("userName").toString();
		}//end if
		HashMap<String, String> map = new HashMap<String, String>();
		String sbsj = DealString.getDateTime();
		map.put("stuExists", "yes");
		map.put("userType", userType);
		map.put("xn", Base.currXn);
		map.put("nd", Base.currNd);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("rs", map);
		request.setAttribute("sbsj", sbsj);
		return mapping.findForward("shgcwjcfxysb");
	}

	/**
	 * ��ȡ��ѯ������ѧ����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgcXysbQryStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		
		String xh = shgcForm.getXh();
		String userType = request.getSession().getAttribute("userType").toString();
		WjcfShgcService service = new WjcfShgcService();
		HashMap<String, String> map = service.getQryStuInfo(xh, userType);
		String sbsj = request.getParameter("sbsj");
		if (StringUtils.isNull(sbsj)) {
			sbsj = DealString.getDateTime();
		}
		request.setAttribute("sbsj", sbsj);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("rs", map);
		return mapping.findForward("shgcwjcfxysb");
	}
	
	/**
	 * ����ѧԺ�걨��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgcXysbDataSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		shgcForm.setSbsj(request.getParameter("sbsj"));
		WjcfShgcXysbModel shgcxysbModel = new WjcfShgcXysbModel();
		XsxxglService xsxxglService=new XsxxglService();
		String xh=request.getParameter("xh");
		BeanUtils.copyProperties(shgcxysbModel, shgcForm);//��FORM�е�����COPY��MODEL��
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		WjcfShgcService service = new WjcfShgcService();
		shgcxysbModel.setXq(request.getParameter("xq"));
		
		boolean flag = service.saveXysbXx(shgcxysbModel, xn, nd);//������Ϣ
		if (flag) {
			request.setAttribute("inserted", "ok");
		}else {
			request.setAttribute("inserted", "no");
		}//end if
		HashMap<String, String> map = new HashMap<String, String>();
		if(!Base.isNull(xh)){
			map=xsxxglService.selectStuinfo(xh);
		}
		map.put("stuExists", "yes");
		map.put("xn", xn);
		map.put("nd", nd);
		request.setAttribute("sbsj", shgcForm.getSbsj());
		map.put("userType", request.getSession().getAttribute("userType").toString());
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("rs", map);
		return mapping.findForward("shgcwjcfxysb");
	}
	
	/**
	 * ѧУ���Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgcxxshDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		appendProperties(request, xydm, zydm, nj, xq);
		String pk = "xh||xn||nd||sbsj";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcService service = new WjcfShgcService();
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		shgcForm.setXn(currxnnd[0]);
		shgcForm.setNd(currxnnd[1]);
		request.setAttribute("pk", pk);//���������ֶ�
		return mapping.findForward("shgcxxsh");
	}
	
	/**
	 * ��ȡ��˲�ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgcXxshQry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcXxshQryModel shgcxxshqryModel = new WjcfShgcXxshQryModel();
		String pk = "xh||xn||nd||sbsj";
		WjcfShgcService service = new WjcfShgcService();
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		String xn = currxnnd[0];
		String nd = currxnnd[1];
		BeanUtils.copyProperties(shgcxxshqryModel, shgcForm);//��FORM�е�ֵCOPY��MODEL
		shgcxxshqryModel.setXn(xn);
		shgcxxshqryModel.setNd(nd);
		ArrayList<HashMap<String, String>> topTr = service.getSearchTitle();//��ͷ
		ArrayList<String[]> rs = service.getSearchResult(shgcxxshqryModel);//��ѯ���
		shgcForm.setXn(xn);
		shgcForm.setNd(nd);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("topTr", topTr);
		request.setAttribute("pk", pk);//���������ֶ�
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		return mapping.findForward("shgcxxsh");
	}
	
	public ActionForward wjshres(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		appendProperties(request, xydm, zydm, nj, xq);
		String pk = "xh||xn||nd||sbsj";
		String res = request.getParameter("res");
		WjcfShgcService service = new WjcfShgcService();
		service.wjshres(shgcForm.getCbv(), request, res);
		request.setAttribute("result", "view");
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		shgcForm.setXn(currxnnd[0]);
		shgcForm.setNd(currxnnd[1]);
		request.setAttribute("pk", pk);//���������ֶ�
		return mapping.findForward("shgcxxsh");
	}
	
	/**
	 * Υ�ʹ���ѧУ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgcXxSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		WjcfShgcXxshModel shgcxxshModel = new WjcfShgcXxshModel();
		BeanUtils.copyProperties(shgcxxshModel, shgcForm);
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service = new WjcfShgcService();
		List<HashMap<String, String>> chkList = service.getChkList(3);
		HashMap<String, String> rs = service.getXxShInfo(pkVal);
		shgcForm.setBz(rs.get("bz"));
		shgcForm.setXxclyj(rs.get("xxclyj"));
		shgcForm.setYesNo(rs.get("xxsh"));
		shgcForm.setJtwjsy(rs.get("jtwjsy"));
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		shgcForm.setXn(currxnnd[0]);
		shgcForm.setNd(currxnnd[1]);
		request.setAttribute("pk", "xh||xn||nd||sbsj");//���������ֶ�
		request.setAttribute("rs", rs);
		request.setAttribute("chkList", chkList);
		request.setAttribute("pkVal", pkVal);
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		return mapping.findForward("shgcxxshone");
	}
	
	/**
	 * Υ�ʹ���ѧУ�����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgcxxshSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		WjcfShgcXxshModel shgcxxshModel = new WjcfShgcXxshModel();
		BeanUtils.copyProperties(shgcxxshModel, shgcForm);
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service = new WjcfShgcService();
		boolean flag = service.saveXxshInfo(shgcxxshModel, pkVal);
		
		List<HashMap<String, String>> chkList = service.getChkList(3);
		HashMap<String, String> rs = service.getXxShInfo(pkVal);
		shgcForm.setXxclyj(rs.get("xxclyj"));
		shgcForm.setYesNo(rs.get("xxsh"));
		shgcForm.setJtwjsy(rs.get("jtwjsy"));
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		shgcForm.setXn(currxnnd[0]);
		shgcForm.setNd(currxnnd[1]);
		request.setAttribute("pk", "xh||xn||nd||sbsj");//���������ֶ�
		if (flag) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", "noview");
		}//end if
		request.setAttribute("rs", rs);
		request.setAttribute("chkList", chkList);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("shgcxxshone");
	}
	
	/**
	 * ����ɾ�������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delshxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String[] keys = shgcForm.getCbv();
		WjcfShgcService service = new WjcfShgcService();
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		String xn = currxnnd[0];
		String nd = currxnnd[1];
		shgcForm.setXn(xn);
		shgcForm.setNd(nd);
		String pks = service.delxxshInfo(keys);
		appendProperties(request, xydm, zydm, nj, xq);
		if (!StringUtils.isNull(pks)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", pks);
		}//end if
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		request.setAttribute("pk", "xh||xn||nd||sbsj");
		return mapping.findForward("shgcxxsh");
	}
	
	/**
	 * �����걨�����ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfysbPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service  = new WjcfShgcService();
		String xh = request.getParameter("xh");
		String cfyy = request.getParameter("cflb");
		String cflb = request.getParameter("cfyy");
		HashMap<String, String> rs = service.getwjcfysbInfo(pkVal, xh, cflb, cfyy);
		String rxrq = rs.get("rxrq");
		String sfzh = rs.get("sfzh");
		rxrq = !StringUtils.isNull(rxrq) ? rxrq.replaceAll("-", "") : "";
		sfzh = !StringUtils.isNull(sfzh) ? sfzh : "";
		String sJxYear = "";
		String sJxMon = "";
		String sCsYear = "";
		String sCsMon = "";
		String sCsDate = "";
		if (!StringUtils.isNull(rxrq)) {
			sJxYear = rxrq.substring(0, 4);
			sJxMon = rxrq.substring(4, 6);
		}
		if (!StringUtils.isNull(sfzh)) {
			sCsYear = sfzh.substring(6, 10);
			sCsMon = sfzh.substring(10, 12);
			sCsDate = sfzh.substring(12, 14);
		}
		rs.put("sjxy", sJxYear);
		rs.put("sjxmon", sJxMon);
		rs.put("scsy", sCsYear);
		rs.put("scsm", sCsMon);
		rs.put("scsd", sCsDate);
		request.setAttribute("rs", rs);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("wjcfysbprint");
	}
	
	
	/**
	 * �������ں��ĺ�ά��Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfrqwhwhDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String xxdm = Base.xxdm;
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			request.setAttribute("tips", "��ǰλ�ã�Υ�ʹ��� - ����ά�� - ѧУ�������");
		} else {
			request.setAttribute("tips", "��ǰλ�ã�Υ�ʹ��� - ����ά�� - �������ں��ĺ�ά��");
		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj, xq);
//		WjcfShgcService service = new WjcfShgcService();
//		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		//shgcForm.setXn(currxnnd[0]);
		//shgcForm.setNd(currxnnd[1]);
		return mapping.findForward("wjcfrqwhwh");
	}
	
	/**
	 * �������ں��ĺ�ά����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfrqwhQry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form ;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		String xxdm = Base.xxdm;
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			request.setAttribute("tips", "��ǰλ�ã�Υ�ʹ��� - ����ά�� - ѧУ�������");
		} else {
			request.setAttribute("tips", "��ǰλ�ã�Υ�ʹ��� - ����ά�� - �������ں��ĺ�ά��");
		}
		WjcfShgcXxshQryModel shgcxxshqryModel = new WjcfShgcXxshQryModel();
		BeanUtils.copyProperties(shgcxxshqryModel, shgcForm);
		WjcfShgcService service = new WjcfShgcService();
//		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		//shgcForm.setXn(currxnnd[0]);
		//shgcForm.setNd(currxnnd[1]);
		List<HashMap<String, String>> topTr = service.getSearchTitle1();
		List<String[]> rs = service.getSearchResult1(shgcxxshqryModel);
		String xm = DealString.toGBK(shgcForm.getXm());
		shgcForm.setXm(xm);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		return mapping.findForward("wjcfrqwhwh");
	}
	
	/**
	 * �������ں��ĺ�ά����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgcwjrswhWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form ;
		WjcfShgcXxshModel shgcxxshModel = new WjcfShgcXxshModel();
		BeanUtils.copyProperties(shgcxxshModel, shgcForm);
		WjcfShgcService service = new WjcfShgcService();
		HashMap<String, String> rs = service.getXxShInfo(pkVal);
		String xxdm = Base.xxdm;
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			request.setAttribute("tips", "��ǰ����λ�ã�Υ�ʹ��� - ����ά�� - ѧУ�������");
			request.setAttribute("tit", "ѧУ�������");
		} else {
			request.setAttribute("tit", "�������ں��ĺ�ά��");
		}
		shgcForm.setCfsj(rs.get("cfsj"));
		shgcForm.setCfwh(rs.get("cfwh"));
		shgcForm.setCflb(rs.get("cflb"));
		shgcForm.setCfyy(rs.get("cfyy"));
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("clwh", "������ѧ[][]��");
		return mapping.findForward("wjcfrqwhsh");
	}
	
	/**
	 * �������ں��ĺ�ά����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savecfrqwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String cfsj = DealString.toGBK(shgcForm.getCfsj());
		String cfwh = DealString.toGBK(shgcForm.getCfwh());
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service = new WjcfShgcService();
		String cflb = request.getParameter("cflb");
		String cfyy = request.getParameter("cfyy");
		boolean flag = service.wjcfrqwhsh(pkVal, cfsj, cfwh,cflb,cfyy);
		String xxdm = Base.xxdm;
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			request.setAttribute("tips", "��ǰ����λ�ã�Υ�ʹ��� - ����ά�� - ѧУ�������");
		} else {
			request.setAttribute("tips", "��ǰ����λ�ã�Υ�ʹ��� - ����ά�� - �������ں��ĺ�ά��");
		}
		HashMap<String, String> rs = service.getXxShInfo(pkVal);
		if (flag) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", "noview");
		}//end if
		shgcForm.setCfwh(cfwh);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("rs", rs);
		request.setAttribute("clwh", "������ѧ[][]��");
		appendProperties(request, xydm, zydm, nj, xq);
		return mapping.findForward("wjcfrqwhsh");
	}
	
	/**
	 * ������������ά��Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfkszbDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		appendProperties(request, xydm, zydm, nj, xq);
		WjcfShgcService service = new WjcfShgcService();
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		shgcForm.setXn(currxnnd[0]);
		shgcForm.setNd(currxnnd[1]);
		String cfyydm = service.getCfyy("��������");
		shgcForm.setCfyy(cfyydm);
		return mapping.findForward("wjcfkszb");
	}
	
	/**
	 * �����������ݲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfkszbQry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String xh = DealString.toGBK(shgcForm.getXh());
		String xm = DealString.toGBK(shgcForm.getXm());
		WjcfShgcService service = new WjcfShgcService();
		WjcfShgcXxshQryModel shgcxxshqryModel = new WjcfShgcXxshQryModel();
		BeanUtils.copyProperties(shgcxxshqryModel, shgcForm);
		String cfyydm = service.getCfyy("��������");
		List<HashMap<String, String>> topTr = service.getSearchTitle2();
		List<String[]> rs = service.getSearchResult2(shgcxxshqryModel, cfyydm);
		appendProperties(request, xydm, zydm, nj, xq);
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		shgcForm.setXn(currxnnd[0]);
		shgcForm.setNd(currxnnd[1]);
		shgcForm.setCfyy(cfyydm);
		shgcForm.setXm(xm);
		shgcForm.setXh(xh);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		return mapping.findForward("wjcfkszb");
	}
	
	/**
	 * ��������������Ϣɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteWjcfKszb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String[] keys = shgcForm.getCbv();
		WjcfShgcService service = new WjcfShgcService();
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		String xn = currxnnd[0];
		String nd = currxnnd[1];
		shgcForm.setXn(xn);
		shgcForm.setNd(nd);
		String cfyydm = service.getCfyy("��������");
		shgcForm.setCfyy(cfyydm);
		String pks = service.delxxshInfo(keys);
		appendProperties(request, xydm, zydm, nj, xq);
		if (!StringUtils.isNull(pks)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", pks);
		}//end if
		return mapping.findForward("wjcfkszb");
	}
	
	/**
	 * �������ٱ����ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygzbPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		//pkVal = pkVal.replace("-", "");
		WjcfShgcService service = new WjcfShgcService();
		HashMap<String, String> map = service.getxxInfo("a.xh||a.xn||a.sbsj", pkVal, "view_wjcf");
		String nn = service.getxxNn(map);
		map.put("nn", nn);
		request.setAttribute("rs", map);
		return mapping.findForward("jygzprint");
	}
	
	/**
	 * δͨ�����Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wtgwjcfDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		WjcfShgcService service = new WjcfShgcService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		String xn = currxnnd[0];
		String nd = currxnnd[1];
		shgcForm.setXn(xn);
		shgcForm.setNd(nd);
		appendProperties(request, xydm, zydm, nj, xq);
		return mapping.findForward("wtgwjcf");
	}
	
	/**
	 * δͨ����˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wtgwjcfxxQry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcXxshQryModel shgcxxshqryModel = new WjcfShgcXxshQryModel();
		
		BeanUtils.copyProperties(shgcxxshqryModel, shgcForm);
		WjcfShgcService service = new WjcfShgcService();
		List<HashMap<String, String>> topTr = service.getSearchTitle3();
		List<String[]> rs = service.getSearchResult3(shgcxxshqryModel);
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		String xn = currxnnd[0];
		String nd = currxnnd[1];
		shgcForm.setXn(xn);
		shgcForm.setNd(nd);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		appendProperties(request, xydm, zydm, nj, xq);
		return mapping.findForward("wtgwjcf");
	}
	
	/**
	 * δͨ�������Ϣ����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteWtgWjxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		String[] cbv = shgcForm.getCbv();
		WjcfShgcService service = new WjcfShgcService();
		String res = service.deleteWtgWjxx(cbv);
		if (!StringUtils.isNull(res)){
			request.setAttribute("result", "view");
		}else{
			request.setAttribute("result", res);
		}//end if
		String[] currxnnd = service.getXnNdList();//��ȡ��ǰѧ�����
		String xn = currxnnd[0];
		String nd = currxnnd[1];
		shgcForm.setXn(xn);
		shgcForm.setNd(nd);
		appendProperties(request, xydm, zydm, nj, xq);
		return mapping.findForward("wtgwjcf");
	}
	
	public ActionForward wtgview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfShgcService service = new WjcfShgcService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.wtgview(pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("wtgview");
	}
	
	/**
	 * ����������ϸ��Ϣҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shslDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service = new WjcfShgcService();
		HashMap<String, String> rs = service.getFindResult("xh||cfwh||cfsj", pkVal, "view_wjcf_xsssxx", 1);
		List<HashMap<String, String>> slList = service.getChkList1(1);//��ȡ�����б�
		List<HashMap<String, String>> rswj = service.GetFileList(pkVal);//��ȡ�����ļ��ϴ�·��
		shgcForm.setSlqk(rs.get("slqk"));
		shgcForm.setSlrq(rs.get("slrq"));
		shgcForm.setSltzs(rs.get("sltzs"));
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("rs", rs);
		request.setAttribute("rswj", rswj);
		request.setAttribute("slqkList", slList);
		return mapping.findForward("shsldefault");
	}
	
	/**
	 * ��������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSsSlxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		WjcfShgcSsSlModel shgcssslModel = new WjcfShgcSsSlModel();
		BeanUtils.copyProperties(shgcssslModel, shgcForm);
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service = new WjcfShgcService();
		boolean flag = service.saveSsSlXx(shgcssslModel, pkVal, request);
		if (flag) {
			request.setAttribute("done", "yes");
		}else {
			request.setAttribute("done", "no");
		}//end if
		List<HashMap<String, String>> slList = service.getChkList1(1);//��ȡ�����б�
		HashMap<String, String> rs = service.getFindResult("xh||cfwh||cfsj", pkVal, "view_wjcf_xsssxx", 1);
		List<HashMap<String, String>> rswj = service.GetFileList(pkVal);//��ȡ�����ļ��ϴ�·��
		shgcForm.setSlqk(rs.get("slqk"));
		shgcForm.setSltzs(rs.get("sltzs"));
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("slqkList", slList);
		request.setAttribute("rs", rs);
		request.setAttribute("rswj", rswj);
		return mapping.findForward("shsldefault");
	}
	
	/**
	 * ���߾�����ϸ��Ϣҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjdDefault(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service = new WjcfShgcService();
		HashMap<String, String> rs = service.getFindResult("xh||cfwh||cfsj", pkVal, "view_wjcf_xsssxx", 2);
		List<HashMap<String, String>> fcqkList = service.getChkList1(2);//��ȡ�����б�
		List<HashMap<String, String>> rswj = service.GetFileList(pkVal);//��ȡ�����ļ��ϴ�·��
		shgcForm.setFcrq(rs.get("fcrq"));
		shgcForm.setFcqk(rs.get("fcqk"));
		shgcForm.setCsqk(rs.get("csqk"));
		shgcForm.setMqzt(rs.get("mqzt"));
		shgcForm.setFctzs(rs.get("fctzs"));
		shgcForm.setJdsj(rs.get("jdsj"));
		shgcForm.setJdwh(rs.get("jdwh"));
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("rs", rs);
		request.setAttribute("rswj", rswj);
		request.setAttribute("mqztList", fcqkList);
		return mapping.findForward("shjddefault");
	}
	
	/**
	 * �������߾�����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savessJdXx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		WjcfShgcSsJdModel shgcssjdModel = new WjcfShgcSsJdModel();
		BeanUtils.copyProperties(shgcssjdModel, shgcForm);
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfShgcService service = new WjcfShgcService();
		boolean flag = service.saveSsJdXx(shgcssjdModel, pkVal, request);
		if (flag) {
			request.setAttribute("done", "yes");
		}else {
			request.setAttribute("done", "no");
		}//end if
		List<HashMap<String, String>> mqztList = service.getChkList1(2);
		HashMap<String, String> rs = service.getFindResult("xh||cfwh||cfsj", pkVal, "view_wjcf_xsssxx", 2);
		List<HashMap<String, String>> rswj = service.GetFileList(pkVal);//��ȡ�����ļ��ϴ�·��
		shgcForm.setFcrq(rs.get("fcrq"));
		shgcForm.setMqzt(rs.get("mqzt"));
		shgcForm.setCsqk(rs.get("csqk"));
		shgcForm.setFcqk(rs.get("fcqk"));
		shgcForm.setFctzs(rs.get("fctzs"));
		shgcForm.setJdsj(rs.get("jdsj"));
		shgcForm.setJdwh(rs.get("jdwh"));
		request.setAttribute("rs", rs);
		request.setAttribute("mqztList", mqztList);
		request.setAttribute("rswj", rswj);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("shjddefault");
	}
	
	/**
	 * ����Υ������ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjsjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcService service = new WjcfShgcService();
		request.setAttribute("tableName", "view_kswjcf");
		request.setAttribute("realTable", "kswjcfb");
		request.setAttribute("kscfyyList", service.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		return mapping.findForward("kswjpage");
	}
	
	/**
	 * ����Υ�Ͳ�ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcService service = new WjcfShgcService();
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		List<HashMap<String, String>> titList = service.kswjTitle();//��ѯ��ͷ
		List<String[]> resList = service.kswjQryRes(model);//��ѯ���
		request.setAttribute("topTr", titList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//���ؽ��
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		request.setAttribute("tableName", "view_kswjcf");
		request.setAttribute("realTable", "kswjcfb");
		request.setAttribute("kscfyyList", service.getKscfyyList());
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		return mapping.findForward("kswjpage");
	}
	
	/**
	 * ����Υ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		WjcfShgcService service  = new WjcfShgcService();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuDetails(xh);
		}
		request.setAttribute("rs", rs);
		shgcForm.setCfwh(DealString.toGBK(shgcForm.getCfwh()));
		shgcForm.setJtwjsy(DealString.toGBK(shgcForm.getJtwjsy()));
		shgcForm.setZacfqk(DealString.toGBK(shgcForm.getZacfqk()));
		shgcForm.setQtcfqk(DealString.toGBK(shgcForm.getQtcfqk()));
		shgcForm.setXyclyj(DealString.toGBK(shgcForm.getXyclyj()));
		shgcForm.setXxclyj(DealString.toGBK(shgcForm.getXxclyj()));
		request.setAttribute("kscflbList", service.getKscflbList());
		request.setAttribute("kscfyyList", service.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);
		return mapping.findForward("kswjadd");
	}
	
	/**
	 * ����Υ�ͱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		WjcfShgcService service  = new WjcfShgcService();
		request.setAttribute("kscflbList", service.getKscflbList());
		request.setAttribute("kscfyyList", service.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);
		shgcForm.setXh(request.getParameter("xh"));
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		boolean bFlag = service.savekswjInfo(model, request);
		HashMap<String, String> rs = new HashMap<String, String>();
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			rs = service.getStuDetails(shgcForm.getXh());
			request.setAttribute("inserted", "no");
		}
		shgcForm.setCfwh(DealString.toGBK(shgcForm.getCfwh()));
		shgcForm.setJtwjsy(DealString.toGBK(shgcForm.getJtwjsy()));
		shgcForm.setZacfqk(DealString.toGBK(shgcForm.getZacfqk()));
		shgcForm.setQtcfqk(DealString.toGBK(shgcForm.getQtcfqk()));
		shgcForm.setXyclyj(DealString.toGBK(shgcForm.getXyclyj()));
		shgcForm.setXxclyj(DealString.toGBK(shgcForm.getXxclyj()));
		request.setAttribute("rs", rs);
		request.setAttribute("kscflbList", service.getKscflbList());
		request.setAttribute("kscfyyList", service.getKscfyyList());
		request.setAttribute("tableName", "view_kswjcf");
		request.setAttribute("realTable", "kswjcfb");
		return mapping.findForward("kswjadd");
	}
	
	/**
	 * ����Υ������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcService service = new WjcfShgcService();
		String jg = service.kswjDel(shgcForm.getCbv(), request);
		if (StringUtils.isNull(jg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "�������,���е�" + jg + "������ɾ��ʧ��!");
		}
		request.setAttribute("tableName", "view_kswjcf");
		request.setAttribute("realTable", "kswjcfb");
		request.setAttribute("kscflbList", service.getKscflbList());
		request.setAttribute("kscfyyList", service.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		return mapping.findForward("kswjpage");
	}
	
	/**
	 * ����Υ���޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		WjcfShgcService service = new WjcfShgcService();
		String act = request.getParameter("act");
		rs = service.kswjModi(pkValue);
		if (rs != null) {
			shgcForm.setXn(rs.get("xn"));
			shgcForm.setNd(rs.get("nd"));
			shgcForm.setXq(rs.get("xq"));
			shgcForm.setCflb(rs.get("cflb"));
			shgcForm.setCfyy(rs.get("cfyy"));
			shgcForm.setCfsj(rs.get("cfsj"));
			shgcForm.setCfwh(rs.get("cfwh"));
			shgcForm.setQtcfqk(rs.get("qtcfqk"));
			shgcForm.setZacfqk(rs.get("zacfqk"));
			shgcForm.setJtwjsy(rs.get("jtwjsy"));
			shgcForm.setXyclyj(rs.get("xyclyj"));
			shgcForm.setXxclyj(rs.get("xxclyj"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual(act, "view")) {
			request.setAttribute("act", "view");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("kscflbList", service.getKscflbList());
		request.setAttribute("kscfyyList", service.getKscfyyList());
		return mapping.findForward("kswjmodipage");
	}
	
	public ActionForward kswjmodiSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		WjcfShgcService service = new WjcfShgcService();
		shgcForm.setXh(request.getParameter("xh"));
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		boolean bFlag = service.kswjModisave(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			rs = service.kswjModi(pkValue);
		}
		shgcForm.setCfwh(DealString.toGBK(shgcForm.getCfwh()));
		shgcForm.setQtcfqk(DealString.toGBK(shgcForm.getQtcfqk()));
		shgcForm.setZacfqk(DealString.toGBK(shgcForm.getZacfqk()));
		shgcForm.setJtwjsy(DealString.toGBK(shgcForm.getJtwjsy()));
		shgcForm.setXyclyj(DealString.toGBK(shgcForm.getXyclyj()));
		shgcForm.setXxclyj(DealString.toGBK(shgcForm.getXxclyj()));
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("kscflbList", service.getKscflbList());
		request.setAttribute("kscfyyList", service.getKscfyyList());
		return mapping.findForward("kswjmodipage");
	}
	
	/**
	 * ����Υ�͸���ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjGzjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcService service = new WjcfShgcService();
		request.setAttribute("tableName", "view_kswjcf");
		request.setAttribute("realTable", "kswjcfb");
		request.setAttribute("kscfyyList", service.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		return mapping.findForward("kswjgzjypage");
	}
	
	/**
	 * ����Υ�͸��ٽ�����ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjGzjyqry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcService service = new WjcfShgcService();
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		List<HashMap<String, String>> topList = service.kswjgzjyTitle();
		List<String[]> resList = service.kswjgzjyResult(model);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		request.setAttribute("tableName", "view_kswjcf");
		request.setAttribute("realTable", "kswjcfb");
		request.setAttribute("kscfyyList", service.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		return mapping.findForward("kswjgzjypage");
	}
	
	/**
	 * ����Υ�͸��ٽ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjjybxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		WjcfShgcService service = new WjcfShgcService();
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = service.viewKswjgzjyxx(pkValue);
		String cfsj = rs.get("cfsj");
		String year=cfsj.substring(0, 4);
		String month=cfsj.substring(4,6);
		String dt=cfsj.substring(6,8);
		int monthtmp=Integer.parseInt(month);
		int yeartmp=Integer.parseInt(year);
		String[] dttemp=new String[4];
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		int temp=0;
		if (sdf.parse(sdf.format(new Date())).after((sdf.parse(yeartmp+1+""+month+dt)))){
			temp=4;
		}else{
			for (int i=0;i<4;i++){
				monthtmp+=3;
				if (monthtmp>12){
					yeartmp++;
					monthtmp-=12;
				}
				if (monthtmp<10){
					dttemp[i]=yeartmp+"0"+monthtmp+""+dt;
				}else{
					dttemp[i]=yeartmp+""+monthtmp+""+dt;
				}
			}
			for (int i=1;i<=dttemp.length;i++){
				if (sdf.parse(sdf.format(new Date())).after(sdf.parse(dttemp[i-1]))){
					temp=i;
					if (temp==3)
						break;
				}
			}
		}
		request.setAttribute("isSHGC", temp);
		if (rs != null) {
			shgcForm.setXsbx1(rs.get("xsbx1"));
			shgcForm.setXsbx2(rs.get("xsbx2"));
			shgcForm.setXsbx3(rs.get("xsbx3"));
			shgcForm.setXsbx4(rs.get("xsbx4"));
			shgcForm.setXyclyj(rs.get("xyclyj"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("kswjgzjyadd");
	}
	
	/**
	 * ���Ը��ٽ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjgzjySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String pkValue = request.getParameter("pkValue");
		WjcfShgcService service = new WjcfShgcService();
		HashMap<String, String> rs = new HashMap<String, String>();
		shgcForm.setXsbx1(request.getParameter("xsbx1"));
		shgcForm.setXsbx2(request.getParameter("xsbx2"));
		shgcForm.setXsbx3(request.getParameter("xsbx3"));
		shgcForm.setXsbx4(request.getParameter("xsbx4"));
		shgcForm.setXyclyj(request.getParameter("xyclyj"));
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		boolean bFlag = service.kswjGzjysaveres(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			rs = service.viewKswjgzjyxx(pkValue);
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", rs);
		shgcForm.setXsbx1(DealString.toGBK(shgcForm.getXsbx1()));
		shgcForm.setXsbx2(DealString.toGBK(shgcForm.getXsbx2()));
		shgcForm.setXsbx3(DealString.toGBK(shgcForm.getXsbx3()));
		shgcForm.setXsbx4(DealString.toGBK(shgcForm.getXsbx4()));
		shgcForm.setXyclyj(DealString.toGBK(shgcForm.getXyclyj()));
		return mapping.findForward("kswjgzjyadd");
	}
	
	/**
	 * ����Υ�ʹ��ֺ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjcfhbx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		WjcfShgcService service = new WjcfShgcService();
		request.setAttribute("kscfyyList", service.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		request.setAttribute("tableName", "view_kswjcfhbxb");
		request.setAttribute("realTable", "kswjcfhbxb");
		return mapping.findForward("cfhbxpage");
	}
	
	/**
	 * ����Υ�ʹ��ֺ����ҳ���ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kswjcfbxQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		KswjCfbxService service = new KswjCfbxService();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		WjcfShgcService services = new WjcfShgcService();
		List<HashMap<String, String>> topTr = service.cfbxTitle();
		List<String[]> res = service.cfbxResult(model);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", res);
		request.setAttribute("rsNum", res != null ? res.size() : 0);
		
		request.setAttribute("kscfyyList", services.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		request.setAttribute("tableName", "view_kswjcfhbxb");
		request.setAttribute("realTable", "kswjcfhbxb");
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		return mapping.findForward("cfhbxpage");
	}
	
	/**
	 * ����Υ�ʹ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfbxadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String xh = shgcForm.getXh();
		HashMap<String, String> rs = new HashMap<String, String>();
		KswjCfbxService service = new KswjCfbxService();
		if (!StringUtils.isNull(xh)) {
			rs = service.stuDetails(xh);
		}
		request.setAttribute("rs", rs);
		shgcForm.setJlr(DealString.toGBK(shgcForm.getJlr()));
		shgcForm.setZc(DealString.toGBK(shgcForm.getZc()));
		shgcForm.setCxryqd(DealString.toGBK(shgcForm.getCxryqd()));
		shgcForm.setHyjl(DealString.toGBK(shgcForm.getHyjl()));
		return mapping.findForward("cfbxaddpage");
	}
	
	public ActionForward cfbxaddsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		shgcForm.setXh(request.getParameter("xh"));
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		HashMap<String, String> rs = new HashMap<String, String>();
		KswjCfbxService service = new KswjCfbxService();
		boolean bFlag = service.kswjcfbxSave(model, request);
		if (bFlag) {
			request.setAttribute("done", "ok");
		} else {
			request.setAttribute("done", "no");
			rs = service.stuDetails(model.getXh());
		}
		request.setAttribute("rs", rs);
		shgcForm.setJlr(DealString.toGBK(shgcForm.getJlr()));
		shgcForm.setZc(DealString.toGBK(shgcForm.getZc()));
		shgcForm.setCxryqd(DealString.toGBK(shgcForm.getCxryqd()));
		shgcForm.setHyjl(DealString.toGBK(shgcForm.getHyjl()));
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		request.setAttribute("tableName", "view_kswjcfhbxb");
		request.setAttribute("realTable", "kswjcfhbxb");
		return mapping.findForward("cfbxaddpage");
	}
	
	public ActionForward cfbxmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		KswjCfbxService service = new KswjCfbxService();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		rs = service.cfbxInfo(pkValue);
		if (rs != null) {
			shgcForm.setZc(rs.get("zc"));
			shgcForm.setHyjl(rs.get("hyjl"));
			shgcForm.setJlr(rs.get("jlr"));
			shgcForm.setCxrs(rs.get("cxrs"));
			shgcForm.setCxryqd(rs.get("cxryqd"));
			shgcForm.setRq(rs.get("rq"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("cfbxmodipage");
	}
	
	public ActionForward cfbxmodiSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		KswjCfbxService service = new KswjCfbxService();
		String pkValue = request.getParameter("pkValue");
		shgcForm.setXh(request.getParameter("xh"));
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		boolean bFlag = service.cfbxModiSave(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("done", "ok");
		} else {
			request.setAttribute("done", "no");
			rs = service.cfbxInfo(model.getXh() + model.getRq());
		}
		shgcForm.setJlr(DealString.toGBK(shgcForm.getJlr()));
		shgcForm.setZc(DealString.toGBK(shgcForm.getZc()));
		shgcForm.setCxryqd(DealString.toGBK(shgcForm.getCxryqd()));
		shgcForm.setHyjl(DealString.toGBK(shgcForm.getHyjl()));
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		request.setAttribute("tableName", "view_kswjcfhbxb");
		request.setAttribute("realTable", "kswjcfhbxb");
		return mapping.findForward("cfbxmodipage");
	}
	
	public ActionForward cfbxdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		KswjCfbxService service = new KswjCfbxService();
		String jg = service.cfbcDel(shgcForm.getCbv(), request);
		if (StringUtils.isNull(jg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "�������,���е�" + jg + "�����ݲ���ʧ��!");
		}
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		appendProperties(request, xydm, zydm, nj, xq);//�����б�
		request.setAttribute("tableName", "view_kswjcfhbxb");
		request.setAttribute("realTable", "kswjcfhbxb");

		return mapping.findForward("cfhbxpage");
	}
	
	public ActionForward kswjcfbgd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("tableName", "view_kswjbgdb");
		request.setAttribute("realTable", "kswjbgdb");
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		return mapping.findForward("cfbgdpage");
	}
	
	public ActionForward kswjbgdqry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		KswjCfbxService service = new KswjCfbxService();
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		List<HashMap<String, String>> topTr = service.bgdTitle();
		List<String[]> resList = service.bgdResult(model);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("tableName", "view_kswjbgdb");
		request.setAttribute("realTable", "kswjbgdb");
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		return mapping.findForward("cfbgdpage");
	}
	
	public ActionForward bgdadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		String tz = request.getParameter("tz");
		KswjCfbxService service = new KswjCfbxService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.stuCfxx(xh);
		}
		if (!StringUtils.isNull(pk)) {
			rs = service.stuKswjxx(pk);
			request.setAttribute("tz", tz);
		}
		request.setAttribute("rs", rs);
		shgcForm.setBz(DealString.toGBK(shgcForm.getBz()));
		shgcForm.setCfhbx(DealString.toGBK(shgcForm.getCfhbx()));
		shgcForm.setJcjl(DealString.toGBK(shgcForm.getJcjl()));
		shgcForm.setBzryj(DealString.toGBK(shgcForm.getBzryj()));
		shgcForm.setXyyj(DealString.toGBK(shgcForm.getXyyj()));
		shgcForm.setKbbmyj(DealString.toGBK(shgcForm.getKbbmyj()));
		shgcForm.setXscyj(DealString.toGBK(shgcForm.getXscyj()));
		shgcForm.setXxyj(DealString.toGBK(shgcForm.getXxyj()));
		return mapping.findForward("cfbgdaddpage");
	}
	
	public ActionForward bgdsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		shgcForm.setXh(request.getParameter("xh"));
		shgcForm.setCfjb(request.getParameter("cfjb"));
		shgcForm.setCfyy(request.getParameter("cfyy"));
		shgcForm.setCflb(request.getParameter("cflb"));
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		String tz = request.getParameter("tz");
		KswjCfbxService service = new KswjCfbxService();
		boolean isBys = StandardOperation.isBys(shgcForm.getXh());
		HashMap<String, String> rs = new HashMap<String, String>();
		isBys = true;
		if (isBys) {
			boolean bFlag = service.bgdSave(model, request);
			if (bFlag) {
				request.setAttribute("done", "ok");
			} else {
				rs = service.stuCfxx(shgcForm.getXh());
				request.setAttribute("done", "no");
			}
		} else {
			request.setAttribute("isBgd", "no");
			rs = service.stuCfxx(shgcForm.getXh());
		}
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		request.setAttribute("rs", rs);
		shgcForm.setBz(DealString.toGBK(shgcForm.getBz()));
		shgcForm.setCfhbx(DealString.toGBK(shgcForm.getCfhbx()));
		shgcForm.setJcjl(DealString.toGBK(shgcForm.getJcjl()));
		shgcForm.setBzryj(DealString.toGBK(shgcForm.getBzryj()));
		shgcForm.setXyyj(DealString.toGBK(shgcForm.getXyyj()));
		shgcForm.setKbbmyj(DealString.toGBK(shgcForm.getKbbmyj()));
		shgcForm.setXscyj(DealString.toGBK(shgcForm.getXscyj()));
		shgcForm.setXxyj(DealString.toGBK(shgcForm.getXxyj()));
		request.setAttribute("tz", tz);
		return mapping.findForward("cfbgdaddpage");
	}
	
	public ActionForward bgddel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		shgcForm.setXm(DealString.toGBK(shgcForm.getXm()));
		KswjCfbxService service = new KswjCfbxService();
		String jg = service.bgdDel(shgcForm.getCbv(), request);
		if (StringUtils.isNull(jg)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "������ɣ����е�" + jg + "������ɾ��ʧ�ܣ�");
		}
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("tableName", "view_kswjbgdb");
		request.setAttribute("realTable", "kswjbgdb");
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		return mapping.findForward("cfbgdpage");
	}
	
	public ActionForward bgdmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		KswjCfbxService service = new KswjCfbxService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.bgdinfo(pkValue);
		String act = request.getParameter("act");
		if (rs != null) {
			shgcForm.setBz(rs.get("bz"));
			shgcForm.setCfhbx(rs.get("cfhbx"));
			shgcForm.setJcjl(rs.get("jcjl"));
			shgcForm.setBzryj(rs.get("bzryj"));
			shgcForm.setXyyj(rs.get("xyyj"));
			shgcForm.setKbbmyj(rs.get("kbbmyj"));
			shgcForm.setXscyj(rs.get("xscyj"));
			shgcForm.setXxyj(rs.get("xxyj"));
		}
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("bgdmodipage");
	}
	
	public ActionForward bgdmodisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfShgcActionForm shgcForm = (WjcfShgcActionForm) form;
		KswjCfbxService service = new KswjCfbxService();
		shgcForm.setSbsj(request.getParameter("sbsj"));
		String pkValue = request.getParameter("pkValue");
		shgcForm.setXh(request.getParameter("xh"));
		KswjModel model = new KswjModel();
		BeanUtils.copyProperties(model, shgcForm);
		HashMap<String, String> rs  = new HashMap<String, String>();
		boolean bFlag = service.bgdmodisave(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("done", "ok");
		} else {
			request.setAttribute("done", "no");
			rs = service.bgdinfo(pkValue);
		}
		shgcForm.setBz(DealString.toGBK(shgcForm.getBz()));
		shgcForm.setCfhbx(DealString.toGBK(shgcForm.getCfhbx()));
		shgcForm.setJcjl(DealString.toGBK(shgcForm.getJcjl()));
		shgcForm.setBzryj(DealString.toGBK(shgcForm.getBzryj()));
		shgcForm.setXyyj(DealString.toGBK(shgcForm.getXyyj()));
		shgcForm.setKbbmyj(DealString.toGBK(shgcForm.getKbbmyj()));
		shgcForm.setXscyj(DealString.toGBK(shgcForm.getXscyj()));
		shgcForm.setXxyj(DealString.toGBK(shgcForm.getXxyj()));
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		WjcfShgcService services = new WjcfShgcService();
		request.setAttribute("kscfyyList", services.getKscfyyList());
		return mapping.findForward("bgdmodipage");
	}
	
	public ActionForward kswjprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkVal = request.getParameter("pkVal");
		KswjCfbxService service = new KswjCfbxService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkVal)) {
			rs = service.kswjprint(pkVal);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("kswjprint");
	}
	
	public ActionForward kswjcfbxprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		KswjCfbxService service = new KswjCfbxService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.kswjcfbxPrint(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("kswjcfbxprint");
	}
	
	public ActionForward kswjbgdprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		KswjCfbxService service = new KswjCfbxService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.kswjbgdprint(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("kswjbgdprint");
	}
	
	public ActionForward kswjjygzbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		KswjCfbxService service = new KswjCfbxService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.kswjjybzprint(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("kswjjygzbprint");
	}
	
	public ActionForward lssjzy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String tips = "";
		String pkValue = request.getParameter("pkValue");
		String[] keys = pkValue.split("!!SplitOneSplit!!");
		WjcfShgcService service = new WjcfShgcService();
		service.sjzy(keys);
		request.setAttribute("tips", tips);
		appendProperties(request, xydm, zydm, nj, xq);
		request.setAttribute("realTable", "wjcfb");
		request.setAttribute("tableName", "view_wjcfb");
		request.setAttribute("act", "");
		request.setAttribute("pk", "");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("stab", "");
		request.setAttribute("isBzr", "");
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("wjcfflag", "true");//Υ�ʹ���ר�ò�ѯ��־
		return mapping.findForward("datasearch");
	}
	
	/**
	 * ��REQUEST �м������е�LIST����
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm, String zydm, String nj, String xq) throws Exception {
		String xy = "";
		String zy = "";
//		String xqLocal = xq;
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
//		xqLocal = xq==null ? "": xq;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		WjcfShgcService service = new WjcfShgcService();
		List<HashMap<String, String>> cflbList = service.getCflbList();//�������
		List<HashMap<String, String>> cfyyList = service.getCfyyList();//����ԭ��
		request.setAttribute("userType", userType);
		request.setAttribute("writeAble", "yes");//�û���дȨ
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
		request.setAttribute("cflbList", cflbList);//��������б�
		request.setAttribute("cfyyList", cfyyList);//����ԭ���б�
	}
}
