

package xgxt.pjpy.zjjd;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.ApplyAction;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭������������Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class PjpyZjjdAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	CommonAction commonAction = new CommonAction();
	/**
	 * У԰���ֵַ�Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		} else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//���ѧ�ڷǿ�,���ȡ���Ӧ���·�
			yfList = service.getYfList(zjjdForm.getXq());
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xybxfcjb");//
		request.setAttribute("tableName", "view_xybxfcj");
		
		return mapping.findForward("xybxfadd");
	}
	
	/**
	 * У԰���ֲַ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		String xm = DealString.toGBK(zjjdForm.getXm());
		String xh = DealString.toGBK(zjjdForm.getXh());
		PjpyZjjdService service = new PjpyZjjdService();
		XybxfModel xybxfModel = new XybxfModel();
		
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//���ѧ�ڷǿ�,���ȡ���Ӧ���·�
			yfList = service.getYfList(zjjdForm.getXq());
		}
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getXybxfTitle();//��ѯ��ͷ
		List<String[]> resList = service.getXybxfResult(xybxfModel);//��ѯ���
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(xm);
		zjjdForm.setXh(xh);
		request.setAttribute("realTable", "xybxfcjb");
		request.setAttribute("tableName", "view_xybxfcj");
		
		return mapping.findForward("xybxfadd");
	}
	
	/**
	 * ѧ��У԰���ֵַ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfnewAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String xh = request.getParameter("xh");
		HashMap<String, String> stuMap = new HashMap<String, String>();
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(xh)) {//ѧ�ŷǿ�ʱ��ȡ���������Ϣ
			stuMap = service.getStuInfo(xh);
			if (stuMap != null) {
				stuMap.put("stuExists", "yes");//���ڸ���
			} else {
				stuMap.put("stuExists", "no");//�����ڸ���
			}
		}
		if (!StringUtils.isNull(zjjdForm.getXq())) {//���ѧ�ڷǿ�,���ȡ���Ӧ���·�
			yfList = service.getYfList(zjjdForm.getXq());
		}
		request.setAttribute("yfList", yfList);
		request.setAttribute("rs", stuMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("add");
	}
	
	/**
	 * У԰���ֵַ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		zjjdForm.setXh(request.getParameter("xh"));
		XybxfModel xybxfModel = new XybxfModel();//У԰���ֱַ���MODEL
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		boolean bFlag = service.xybxfSave(xybxfModel, request);//���ݱ���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap.put("stuExists", "yes");
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("yfList", new ArrayList<HashMap<String, String>>());
		zjjdForm.setSx(DealString.toGBK(zjjdForm.getSx()));
		return mapping.findForward("add");
	}
	
	/**
	 * ѧ��У԰���ַ��޸���ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		String xq = request.getParameter("xq");
		HashMap<String, String> resMap = service.getXybxfInfo(pkValue);//��ȡУ԰���ַ���Ϣ
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(resMap.get("xq"))) {
			yfList = service.getYfList(resMap.get("xq"));
		}
		if (!StringUtils.isNull(xq)) {
			yfList = service.getYfList(xq);
			resMap = service.getXybxfInfo(pkValue);//��ȡУ԰���ַ���Ϣ
			resMap.put("xq", xq);
		}
		zjjdForm.setXn(resMap.get("xn"));
		zjjdForm.setXq(resMap.get("xq"));
		zjjdForm.setYf(resMap.get("yf"));
		zjjdForm.setJf(resMap.get("jf"));
		zjjdForm.setKf(resMap.get("kf"));
		zjjdForm.setSx(resMap.get("sx"));
		request.setAttribute("yfList", yfList);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xybxfview");
	}
	
	/**
	 *  ѧ��У԰���ַ��޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		zjjdForm.setXh(request.getParameter("xh"));
		XybxfModel xybxfModel = new XybxfModel();
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.xybxfModi(xybxfModel, pkValue, request);//�����޸�
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap.put("stuExists", "yes");
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("yfList", new ArrayList<HashMap<String, String>>());
		zjjdForm.setSx(DealString.toGBK(zjjdForm.getSx()));
		return mapping.findForward("xybxfview");
	}
	
	/**
	 * ѧ��У԰���ַ�����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//���ѧ�ڷǿ�,���ȡ���Ӧ���·�
			yfList = service.getYfList(zjjdForm.getXq());
		}
		String sDel = service.xybxfDel(zjjdForm.getCbv(), request);//����ɾ��
		if (!StringUtils.isNull(sDel)) {//ɾ��ʧ��
			request.setAttribute("failinfo", String.format("�� %1$s ������ɾ��ʧ�ܣ�", sDel));
			request.setAttribute("deleted", "no");
		} else {//ɾ���ɹ�
			request.setAttribute("deleted", "yes");
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xybxfcjb");//
		request.setAttribute("tableName", "view_xybxfcj");
		
		return mapping.findForward("xybxfadd");
	}
	
	/**
	 * У԰���ַ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//���ѧ�ڷǿ�,���ȡ���Ӧ���·�
			yfList = service.getYfList(zjjdForm.getXq());
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		
		return mapping.findForward("xybxfshdefault");
	}
	
	/**
	 * У԰���ַ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String[] keys = zjjdForm.getCbv();
		String sJg = request.getParameter("param1");
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//���ѧ�ڷǿ�,���ȡ���Ӧ���·�
			yfList = service.getYfList(zjjdForm.getXq());
		}
		String shRes = service.xybxfSh(keys, sJg, userType, request);//�������
		if (!StringUtils.isNull(shRes)) {
			request.setAttribute("result", "no");
			request.setAttribute("failinfo", shRes);
		} else {
			request.setAttribute("result", "yes");
		}
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		
		return mapping.findForward("xybxfshdefault");
	}
	
	/**
	 * У԰���ַ���˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		XybxfModel xybxfModel = new XybxfModel();
		
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (!StringUtils.isNull(zjjdForm.getXq())) {//���ѧ�ڷǿ�,���ȡ���Ӧ���·�
			yfList = service.getYfList(zjjdForm.getXq());
		}
		BeanUtils.copyProperties(xybxfModel, zjjdForm);
		List<HashMap<String, String>> topList = service.xybxfshQryTitle(userType);//��ѯ��ͷ
		List<String[]> resList = service.xybxfshQryResult(xybxfModel, userType);;//��ѯ���
		request.setAttribute("yfList", yfList);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		zjjdForm.setXh(DealString.toGBK(zjjdForm.getXh()));
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		
		return mapping.findForward("xybxfshdefault");
	}
	
	/**
	 * У԰���ֵַ��������ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//�û�����
		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();//�·��б�
		
		List<HashMap<String, String>> chkList = service.getChkList(3);//����б�
		HashMap<String, String> resMap = service.xybxfshView(userType, pkValue);//�����ʾ��ϸ��Ϣ
		if (!StringUtils.isNull(resMap.get("xq"))) {
			yfList = service.getYfList(resMap.get("xq"));
		}
		request.setAttribute("chkList", chkList);
		request.setAttribute("rs", resMap);
		if (resMap != null) {
			request.setAttribute("oldsh", resMap.get("sh"));
			request.setAttribute("oldyj", resMap.get("yj"));
		}
		request.setAttribute("yfList", yfList);
		request.setAttribute("userType", userType);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xybxfshview");
	}
	
	/**
	 * У԰���ֵַ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xybxfshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		String sh = DealString.toGBK(request.getParameter("sh"));
		String yj = DealString.toGBK(request.getParameter("yj"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//�û�����
		boolean bFlag = service.xybxfShOne(pkValue, userType, sh, yj, request);//������˽��
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> chkList = service.getChkList(3);//����б�
		request.setAttribute("chkList", chkList);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("yfList", new ArrayList<HashMap<String, String>>());//�·��б�
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("xybxfshview");
	}
	
	/**
	 * ѧ���ɲ��������ӷ�ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxgbdyfjfWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xsgbdyfjfb");
		request.setAttribute("tableName", "view_xsgbdyfjf");
		return mapping.findForward("xxgbdyfjfwh");
	}
	
	/**
	 * ѧ���ɲ��������ӷֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxgbdyfjfQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		XsgbdyfjfModel xsgbdyfjfModel = new XsgbdyfjfModel();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		BeanUtils.copyProperties(xsgbdyfjfModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getXsgbdyQryTitle();//��ѯ��ͷ
		List<String[]> resList = service.getXsgbdyQryResult(xsgbdyfjfModel);//��ѯ���

		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("realTable", "xsgbdyfjfb");
		request.setAttribute("tableName", "view_xsgbdyfjf");
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		return mapping.findForward("xxgbdyfjfwh");
	}
	
	/**
	 * ѧ���ɲ��������ӷֵ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyfjfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String xh = request.getParameter("xh");
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			resMap = service.getStuInfo(xh);//��ȡѧ����Ϣ
			if (resMap != null) {
				resMap.put("stuExists", "yes");
			} else {
				resMap.put("stuExists", "no");
			}
		}
		List<HashMap<String, String>> djList = service.getDjList(1);//��ȡ�ȼ��б�
		request.setAttribute("djList", djList);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xsgbdyfjfadd");
	}
	
	/**
	 * ѧ���ɲ��������ӷֵ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		XsgbdyfjfModel xsgbdyfjfModel = new XsgbdyfjfModel();
		BeanUtils.copyProperties(xsgbdyfjfModel, zjjdForm);
		boolean bFlag = service.saveXsgbdyfjf(xsgbdyfjfModel, request);//���ݱ���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> djList = service.getDjList(1);//��ȡ�ȼ��б�
		request.setAttribute("djList", djList);
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		zjjdForm.setDrzw(DealString.toGBK(zjjdForm.getDrzw()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("xsgbdyfjfadd"); 
	}
	
	/**
	 * ѧ���ɲ��������ӷ���ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> resMap = service.getXsgbdyfxx(pkValue);//��ȡѧ����Ϣ
		List<HashMap<String, String>> djList = service.getDjList(1);//��ȡ�ȼ��б�
		request.setAttribute("djList", djList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", resMap);
		if (resMap != null) {
			zjjdForm.setXn(resMap.get("xn"));
			zjjdForm.setXq(resMap.get("xq"));
			zjjdForm.setDrzw(resMap.get("drzw"));
			zjjdForm.setRzsj(resMap.get("rzsj"));
			zjjdForm.setJf(resMap.get("jf"));
			zjjdForm.setBz(resMap.get("bz"));
			zjjdForm.setKhdj(resMap.get("khdj"));
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xsgbdyfmodi");
	}
	
	/**
	 * ѧ���ɲ��������ӷ��޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		zjjdForm.setXh(request.getParameter("xh"));
		XsgbdyfjfModel xsgbModel = new XsgbdyfjfModel();
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		BeanUtils.copyProperties(xsgbModel, zjjdForm);
		boolean bFlag = service.xsgbdyfModi(pkValue, xsgbModel, request);//�޸ı���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> djList = service.getDjList(1);//��ȡ�ȼ��б�
		request.setAttribute("djList", djList);
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		zjjdForm.setDrzw(DealString.toGBK(zjjdForm.getDrzw()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("xsgbdyfmodi");
	}
	
	/**
	 * ѧ���ɲ�����������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgbdyfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		String sDel = service.xsgbdyDel(zjjdForm.getCbv(), request);//����ɾ��
		if (!StringUtils.isNull(sDel)) {//ɾ��δ�ɹ�
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("�� %1$s ������ɾ��ʧ�ܣ�", sDel));
		} else {//ɾ���ɹ�
			request.setAttribute("deleted", "yes");
		}
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		zjjdForm.setXm(DealString.toGBK(zjjdForm.getXm()));
		request.setAttribute("realTable", "xsgbdyfjfb");
		request.setAttribute("tableName", "view_xsgbdyfjf");
		return mapping.findForward("xxgbdyfjfwh");
	}
	
	/**
	 * �ۺ����ʲ�����ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		request.setAttribute("realTable", "zjjd_zhszcp");
		request.setAttribute("tableName", "view_zjjd_zhszcp");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * �ۺ����ʲ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		ZhszcpModel zhszcpModel = new ZhszcpModel();//�ۺ����ʲ�ѯMODEL
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getQryTitle("zhszcp");//��ѯ��ͷ
		List<String[]> resList = service.getZhszcpQryResult(zhszcpModel);//��ѯ���
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("realTable", "zjjd_zhszcp");
		request.setAttribute("tableName", "view_zjjd_zhszcp");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * �ۺ����ʲ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			resMap = service.getZhszcpf(xh, xn, xq);//��ȡѧ����ر��ַ�
			if (resMap != null) {
				resMap.put("stuExists", "yes");
			} else {
				resMap.put("stuExists", "no");
			}
		}
		List<String[]> cjList = service.getcjList(xh, xn, xq);
		request.setAttribute("cjList", cjList);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺ����ʲ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		String xh = request.getParameter("xh");
		zjjdForm.setXh(xh);
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		boolean bFlag = service.zhszcpSave(zhszcpModel, request);//���ݱ���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺ����ʲ�������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//���Ŵ���
		String userType = session.getAttribute("userType").toString();//�û�����
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�ֱ�ӻ�ȡ���Ŵ���
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String sDel = service.zhszcpDel(zjjdForm.getCbv(), request);//����ɾ��
		if (!StringUtils.isNull(sDel)) {//ɾ��ʧ��
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("�� %1$s ������ɾ��ʧ�ܣ�", sDel));
		} else {//ɾ���ɹ�
			request.setAttribute("deleted", "yes");
		}
		request.setAttribute("realTable", "zjjd_zhszcp");
		request.setAttribute("tableName", "view_zjjd_zhszcp");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * �ۺ����ʲ����޸���ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = service.getZhszcpInfo(pkValue);//��ʾ��ϸ��Ϣ
 		request.setAttribute("pkValue", pkValue);
 		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		if (resMap != null) {
			zjjdForm.setXn(resMap.get("xn"));
			zjjdForm.setXq(resMap.get("xq"));
			zjjdForm.setNd(resMap.get("nd"));
			zjjdForm.setZyfjf(resMap.get("zyfjf"));
			zjjdForm.setTycj(resMap.get("tycj"));
			zjjdForm.setTyfjf(resMap.get("tyfjf"));
			zjjdForm.setBz(resMap.get("bz"));
			zjjdForm.setTyzf(resMap.get("tyzf"));
			zjjdForm.setTyxj(resMap.get("tyxj"));
			zjjdForm.setZyzf(resMap.get("zyzf"));
			zjjdForm.setZyxj(resMap.get("zyxj"));
		}
		List<String[]> cjList = service.getcjList(resMap.get("xh"), resMap.get("xn"), resMap.get("xq"));
		request.setAttribute("cjList", cjList);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * �ۺ����ʲ����޸ĵ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		zjjdForm.setXh(request.getParameter("xh"));
		String pkValue = request.getParameter("pkValue");
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		boolean bFlag = service.zhszcpModi(pkValue, zhszcpModel, request);//�����޸ı���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap<String, String>());
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * �ۺ����ʲ������ݵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDataExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, zjjdForm);
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")+"/print/zjjdzhszcpb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.zhszcpPrint(wwb, zhszcpModel);
		return mapping.findForward("");
	}
	
	/**
	 * ��ѧ���ϱ�������Ա��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//ѧԺ�û�
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			jxjpdModel.setXn(jxjsqxnxqnd[0]);
			jxjpdModel.setXq(jxjsqxnxqnd[1]);
			jxjpdModel.setNd(jxjsqxnxqnd[2]);
		}
		String xh = "";
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
			jxjpdModel.setXq(jxjpdModel.getXq());
		} else {
			jxjpdModel.setXq(jxjpdModel.getXq());
			xh = request.getParameter("xh");
		}
		jxjpdModel.setXh(xh);
		if (!StringUtils.isNull(xh)) {
			resMap = service.getJxjpdxx(jxjpdModel);//��ȡѧ��������Ϣ
			if (resMap != null) {//������Ϣ����
				resMap.put("stuExists", "yes");
			} else {//������Ϣ������
				resMap.put("stuExists", "no");
			}
		}
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendJxjList(request);//��ѧ���б�
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		request.setAttribute("userType", userType);
		return mapping.findForward("jxjsqdefault");
	}
	
	
	/**
	 * ��ѧ�����뱣��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//ѧԺ�û�
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		String xh = "";
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		jxjpdModel.setXn(request.getParameter("xn"));
		jxjpdModel.setNd(request.getParameter("nd"));
		String xqdm=request.getParameter("xq");
		jxjpdModel.setXq(xqdm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getJxjpdxx(jxjpdModel);//��ȡѧ��������Ϣ
		resMap.put("stuExists", "yes");
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		
		boolean bCj  = service.stuCjFlag(xh, jxjpdModel.getXn(), jxjpdModel.getXq());//ѧ���Ƿ��гɼ�
		if (!bCj) {
			request.setAttribute("cjFlag", "no");
		} else {
			boolean tjFlag = ApplyAction.pdStuTjFlag(zjjdForm.getXh(), zjjdForm.getJxjdm(), "jxj");
			if (!tjFlag) {
				request.setAttribute("failinfo", "true");
			} else {
				boolean bFlag = service.jxjsqSave(jxjpdModel, request);//���뱣��
				if (bFlag) {//�ɹ�
					request.setAttribute("inserted", "yes");
				} else {//ʧ��
					request.setAttribute("inserted", "no");
				}
			}
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendJxjList(request);//��ѧ���б�
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setTzjkbzdj(DealString.toGBK(zjjdForm.getTzjkbzdj()));
		zjjdForm.setSzxyj(DealString.toGBK(zjjdForm.getSzxyj()));
		zjjdForm.setFdyyj(DealString.toGBK(zjjdForm.getFdyyj()));
		return mapping.findForward("jxjsqdefault");
	}
	
	/**
	 * ��ѧ����������ѯ��ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqqryDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			String xh = session.getAttribute("userName").toString();
			List<HashMap<String, String>> topList = service.getQryTitle("stusqxx");//��ѯ��ͷ
			List<String[]>  resList = service.stuJxjSqxx(xh);//ѧ����ѧ��������Ϣ
			request.setAttribute("rs", resList);
			request.setAttribute("num", resList != null ? resList.size() : 0);//�������
			request.setAttribute("topTr", topList);
			return mapping.findForward("stujxjqry");
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "view_xsjxjb");
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * ��ѧ����������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getQryTitle("xsjxjb");//��ѯ��ͷ
		List<String[]> resList = service.getJxjsqQryResult(jxjpdModel);//��ѯ���
		zjjdForm.setXh(DealString.toGBK(zjjdForm.getXh()));
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "view_xsjxjb");
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * ��ѧ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//ѧԺ�û�
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			jxjpdModel.setXn(jxjsqxnxqnd[0]);
			jxjpdModel.setXq(jxjsqxnxqnd[1]);
			jxjpdModel.setNd(jxjsqxnxqnd[2]);
		}
		String xh = request.getParameter("xh");
		if (!StringUtils.isNull(xh)) {
			resMap = service.getJxjpdxx(jxjpdModel);//��ȡѧ��������Ϣ
			if (resMap != null) {//������Ϣ����
				resMap.put("stuExists", "yes");
			} else {//������Ϣ������
				resMap.put("stuExists", "no");
			}
		}
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendJxjList(request);//��ѧ���б�
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("jxjadd");
	}
	
	/**
	 * ��ѧ������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String sDel = service.jxjDel(zjjdForm.getCbv(), request);//����ɾ��
		if (StringUtils.isNull(sDel)) {//ɾ���ɹ�
			request.setAttribute("deleted", "yes");
		} else {//ɾ��ʧ��
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("�� %1$s ������ɾ��ʧ�ܣ�", sDel));
		}
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "view_xsjxjb");
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * ��ѧ���޸���ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjmodiView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getJxjModixx(pkValue);//��ȡ������Ϣ
		String[] jxjshList = service.jxjshResult(pkValue);
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		request.setAttribute("rs", resMap);
		if (jxjshList != null && jxjshList.length == 2) {
			request.setAttribute("xysh", jxjshList[0]);
			request.setAttribute("xxsh", jxjshList[1]);
		}
		zjjdForm.setTzjkbzdj(resMap.get("tzjkbzdj"));
		zjjdForm.setBjpddj(resMap.get("bjpddj"));
		zjjdForm.setSzxyj(resMap.get("szxyj"));
		zjjdForm.setFdyyj(resMap.get("fdyyj"));
		zjjdForm.setJxjdm(resMap.get("jxjdm"));
		commonAction.appendProperties(request, xydm, zydm, nj);
		commonAction.appendJxjList(request);//��ѧ���б�
		request.setAttribute("pdList", pdList);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjmodiview");
	}
	
	/**
	 * ��ѧ���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = new HashMap<String, String>();
		zjjdForm.setXn(request.getParameter("xn"));
		zjjdForm.setNd(request.getParameter("nd"));
		zjjdForm.setXq(request.getParameter("xq"));
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		boolean bFlag = service.jxjModi(pkValue, jxjpdModel, request);//�޸ı���
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		} else {//ʧ��
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		commonAction.appendJxjList(request);//��ѧ���б�
		request.setAttribute("pdList", pdList);
		zjjdForm.setTzjkbzdj(DealString.toGBK(zjjdForm.getTzjkbzdj()));
		zjjdForm.setSzxyj(DealString.toGBK(zjjdForm.getSzxyj()));
		zjjdForm.setFdyyj(DealString.toGBK(zjjdForm.getFdyyj()));
		return mapping.findForward("jxjmodiview");
	}
	
	/**
	 *  ��ѧ�����ݵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjdataExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		String modelPath = "";//���ݱ��ģ��
		modelPath = servlet.getServletContext().getRealPath("")+"/print/zjjdjxjpdb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.jxjPrint(wwb, jxjpdModel);//��ѧ�����ݵ���
		return mapping.findForward("");
	}
	
	/**
	 * ��ѧ�𱣴�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//ѧԺ�û�
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		BeanUtils.copyProperties(jxjpdModel, zjjdForm);
		jxjpdModel.setXn(request.getParameter("xn"));
		jxjpdModel.setNd(request.getParameter("nd"));
		jxjpdModel.setXq(request.getParameter("xq"));
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		boolean bFlag = service.jxjsqSave(jxjpdModel, request);//���뱣��
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		} else {//ʧ��
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendJxjList(request);//��ѧ���б�
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setTzjkbzdj(DealString.toGBK(zjjdForm.getTzjkbzdj()));
		zjjdForm.setSzxyj(DealString.toGBK(zjjdForm.getSzxyj()));
		zjjdForm.setFdyyj(DealString.toGBK(zjjdForm.getFdyyj()));
		return mapping.findForward("jxjadd");
	}
	
	/**
	 * �����ƺ�������ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		
		//ѧ���û���������
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		if (!StringUtils.isNull(xh)) {
			resMap = service.getXxtj(xh);//�����ƺ���ѡ����
			
		} 
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendRychList(request);//���������ƺ��б�
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("rychsq");
	}
	
	/**
	 * �����ƺű���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		RychModel rychModel = new RychModel();
		//ѧ���û���������
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getXxtj(xh);//�����ƺ���ѡ����
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendRychList(request);//���������ƺ��б�
		BeanUtils.copyProperties(rychModel, zjjdForm);
		rychModel.setXh(xh);
		boolean bCj  = service.stuCjFlag(xh, jxjsqxnxqnd[0], jxjsqxnxqnd[1]);//ѧ���Ƿ��гɼ�
		if (!bCj) {
			request.setAttribute("cjFlag", "no");
		} else {
			boolean tjFlag = ApplyAction.pdStuTjFlag(zjjdForm.getXh(), zjjdForm.getRychdm(), "rych");
			if (!tjFlag) {
				request.setAttribute("failinfo", "true");
			} else {
				boolean bFlag = service.rychSave(rychModel, request);//������Ϣ
				if (bFlag) {
					request.setAttribute("inserted", "yes");
				} else {
					request.setAttribute("inserted", "no");
				}
			}
		}
		
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("rychsq");
	}
	
	/**
	 * �����ƺ������ѯ��ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();	
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		String xn = zjjdForm.getXn();
		String xq = zjjdForm.getXq();
		if(xn==null){
			if(jxjsqxnxqnd!=null){
				zjjdForm.setXn(jxjsqxnxqnd[0]);
			}
		}
		if(xq==null){
			if(jxjsqxnxqnd!=null){
				zjjdForm.setXq(jxjsqxnxqnd[1]);
			}
		}
		//ѧԺ�û�ֱ�ӻ�ȡ�䲿�Ŵ���
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychsqqry");
	}
	
	/**
	 * �����ƺŲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		//ѧԺ�û�ֱ�ӻ�ȡ�䲿�Ŵ���
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		RychModel rychModel = new RychModel();//�����ƺŲ�ѯMODEL
		BeanUtils.copyProperties(rychModel, zjjdForm);
		List<HashMap<String, String>> topList = service.getQryTitle("xsrychb");//��ѯ��ͷ
		List<String[]> resList = service.rychQryResult(rychModel);//��ѯ���
		zjjdForm.setXh(DealString.toGBK(zjjdForm.getXh()));
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychsqqry");
	}
	
	/**
	 * �����ƺŵ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		//ѧ���û���������
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getXxtj(xh);//�����ƺ���ѡ����
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendRychList(request);//���������ƺ��б�
		
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("rychadd");
	}
	
	/**
	 * �����ƺű���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsaveRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		String xh = "";
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}
		RychModel rychModel = new RychModel();
		//ѧ���û���������
		if (StringUtils.isEqual(userType, "student")
				|| StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getXxtj(xh);//�����ƺ���ѡ����
		String[] jxjsqxnxqnd = service.getJxjsqxnxqnd();//��ѧ������ѧ�꣬ѧ�ڣ����
		if (jxjsqxnxqnd != null && jxjsqxnxqnd.length == 3) {
			resMap.put("xn", jxjsqxnxqnd[0]);
			resMap.put("xq", jxjsqxnxqnd[1]);
			resMap.put("nd", jxjsqxnxqnd[2]);
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		commonAction.appendRychList(request);//���������ƺ��б�
		BeanUtils.copyProperties(rychModel, zjjdForm);
		rychModel.setXh(xh);
		boolean bFlag = service.rychSave(rychModel, request);//������Ϣ
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("rychadd");
	}
	
	/**
	 * �����ƺ��޸���ʾ��ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychmodiView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = service.getRychXx(pkValue);//��ʾ��ϸ��Ϣ
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		request.setAttribute("pdList", pdList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		if (resMap != null) {
			zjjdForm.setWydj(resMap.get("wydj"));
			zjjdForm.setJsjdj(resMap.get("jsjdj"));
			zjjdForm.setBjpddj(resMap.get("bjpddj"));
			zjjdForm.setRychdm(resMap.get("rychdm"));
			zjjdForm.setBz(resMap.get("bz"));
		}
		commonAction.appendRychList(request);//���������ƺ��б�
		return mapping.findForward("rychview");
	}
	
	/**
	 * �����ƺ��޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		String pkValue = request.getParameter("pkValue");
		zjjdForm.setXh(request.getParameter("xh"));
		RychModel rychModel = new RychModel();
		BeanUtils.copyProperties(rychModel, zjjdForm);
		boolean bFlag = service.rychModi(rychModel, pkValue, request);//������Ϣ
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> pdList = service.getDjList(1);//�༶�����б�
		request.setAttribute("pdList", pdList);
		commonAction.appendRychList(request);//���������ƺ��б�
		request.setAttribute("rs", new HashMap<String, String>());
		zjjdForm.setWydj(DealString.toGBK(zjjdForm.getWydj()));
		zjjdForm.setJsjdj(DealString.toGBK(zjjdForm.getJsjdj()));
		zjjdForm.setBz(DealString.toGBK(zjjdForm.getBz()));
		return mapping.findForward("rychview");
	}
	
	/**
	 * �����ƺ�����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdActionForm zjjdForm = (PjpyZjjdActionForm) form;
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		//ѧԺ�û�ֱ�ӻ�ȡ�䲿�Ŵ���
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			zjjdForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String sDel = service.rychDel(zjjdForm.getCbv(), request);
		if (StringUtils.isNull(sDel)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", sDel);
		}
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychsqqry");
	}
	
	/**
	 * ��ѧ������ѧ����ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stujxjQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjjdService service = new PjpyZjjdService();
		HttpSession session = request.getSession();
		String xh = session.getAttribute("userName").toString();
		List<HashMap<String, String>> topList = service.getQryTitle("stusqxx");//��ѯ��ͷ
		List<String[]>  resList = service.stuJxjSqxx(xh);//ѧ����ѧ��������Ϣ
		request.setAttribute("rs", resList);
		request.setAttribute("num", resList != null ? resList.size() : 0);//�������
		request.setAttribute("topTr", topList);
		return mapping.findForward("stujxjqrys");
	}
	/**
	 * ��Ԣ���ַ�ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gybxfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		
		PjpyZjjdService service = new PjpyZjjdService();
		PjpyZjjdActionForm myForm = (PjpyZjjdActionForm) form;
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
//		String xm = DealString.toGBK(myForm.getXm());
		String tableName = "view_pjpy_gybxf";
		String realTable = "gybxfcjb";

		// ȡ�ò�ѯ����
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;
		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// ȡ�ñ�ͷ
			String[] colList = new String[] { "pk","xn","xqmc","xh","xm", "xymc", "zymc",
					"bjmc", "rq" };
			topTr = service.getTopTr(tableName, colList);
			// ȡ�ò�ѯ���
			vector = service.getGybxfList(myForm);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("gybxfManage");
	}
	
	/**
	 * ��Ԣ���ַ�ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gybxfOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");

		PjpyZjjdService service = new PjpyZjjdService();
		PjpyZjjdActionForm myForm = (PjpyZjjdActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			rs.put("jxnd", "1");
		}
		if ("save".equalsIgnoreCase(doType)) {
			
			String xn=request.getParameter("xnV");
			String xq=request.getParameter("xqV");
			
			if(!Base.isNull(xn)){
				myForm.setXn(xn);
			}
			if(!Base.isNull(xq)){
				myForm.setXq(xq);
			}
			
			boolean result = service.saveGybxf(myForm, request);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			
			// ѧ��
			String xh = myForm.getXh();
			// ѧ��
			xn = myForm.getXn();
			// ѧ��
			xq = myForm.getXq();
			// ����
			String rq = myForm.getRq();
			
			rs = service.getGybxfOne(xh + xn + xq + rq);
		}
		if ("edit".equalsIgnoreCase(doType)|| "view".equalsIgnoreCase(doType)) {
			rs = service.getGybxfOne(pkValue);
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean result = service.delGybxf(pkValue,request);
			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/pjpyzjjdwh.do?method=gybxfManage", false);
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		return mapping.findForward("gybxfOne");
	}
		
}
