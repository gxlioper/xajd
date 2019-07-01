
package xgxt.pjpy.ynys.zhszcp;

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

import xgxt.base.DealString;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �����������������ۺ����ʲ���Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-31</p>
 */
public class PjpyYnysZhszcpAction extends CommonAction {

	private String xydm = "";//ѧԺ����
	private String zydm = "";//רҵ����
	private String nj = "";//�꼶
	
	/**
	 * ���ʲ���ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//�û�����
		String userDep = getUserDep(request);//�û�����
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx("0");//Ĭ��Ϊ˼�����ʷ�
		String excelB = "ynys_sxzzddszb.xls";//Ĭ��Ϊ˼�����ʷֱ�
		String act = request.getParameter("act");
		realTable = "sxzzddszb";
		tableName = "view_sxzzddszb";
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(act) && StringUtils.isEqual("szlx", act)) {//ҳ���ȡ
			ynysForm.setSzlx(request.getParameter("szlx"));
			realTable = service.getRealTable(request.getParameter("szlx"));//����
			tableName = "view_" + realTable;//��ͼ��
			excelB = "ynys_" + realTable + ".xls";
			
			if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
				request.setAttribute("ty", "1");
			}
		} 
		request.setAttribute("tmp", ynysForm.getSzlx());
		request.setAttribute("excelb", excelB);
		appendProperties(request, xydm, zydm, nj);//�����б�
		appendTableXx(request, tableName, realTable);//���ر���,��ͼ��
//		String xm = DealString.toGBK(ynysForm.getXm());
		ModelToStrings.formToGBK(ynysForm);
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * ���ʲ�����ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szcpQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);// �û�����
		String userDep = getUserDep(request);// �û�����
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		//ynysForm.setSzlx("0");// Ĭ��Ϊ˼�����ʷ�
		
		ynysForm.setSzlx(request.getParameter("szlx"));
		
		realTable = service.getRealTable(request.getParameter("szlx"));// ����
		tableName = "view_" + realTable;// ��ͼ��
		String excelb = "ynys_" + realTable + ".xls";//ģ������
		request.setAttribute("excelb", excelb);
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		String szlx = request.getParameter("szlx");
		model.setCxfs(request.getParameter("cxfs"));
		List<HashMap<String, String>> topList = service.szcpQryTitle(szlx, model);//��ѯ��ͷ
		List<String[]> resList = service.szfQryRes(model, szlx);//��ѯ���
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		appendResult(request, topList, resList);//���ؽ����
		appendProperties(request, xydm, zydm, nj);// �����б�
		appendTableXx(request, tableName, realTable);// ���ر���,��ͼ��
		request.setAttribute("tmp", request.getParameter("szlx"));
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		ynysForm.setShzt(DealString.toGBK(ynysForm.getShzt()));
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * ˼�����ε������ʷ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//�����б�
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// �û�����
		String userName = (String)session.getAttribute("userName");// �û�����
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sxzzddszf");
	}
	
	/**
	 * ˼�����ε������ʷ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//�����б�
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// �û�����
		String userName = (String)session.getAttribute("userName");// �û�����
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sxzzddszf");
	}
	
	/**
	 * ����˼�����ε������ʷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveSxzzf(model);//�����¼
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��,ԭ������Ϊ��" + sFlag
					+ "����¼�д��ڴ�������!");
		}
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		appendProperties(request, xydm, zydm, nj);//�����б�
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("sxzzddszf");
	}
	
	/**
	 * ˼�����ʷֵ�����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszbDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//�û�����
		String userDep = getUserDep(request);//�û�����
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//����
		tableName = "view_" + realTable;//��ͼ��
		String sFlag = service.sxzzddszfDel(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "���е�" + sFlag + "����ɾ��ʧ��!");
		}
		String excelb = "ynys_" + realTable + ".xls";//ģ������
		request.setAttribute("excelb", excelb);
		appendProperties(request, xydm, zydm, nj);//�����б�
		appendTableXx(request, tableName, realTable);//���ر���,��ͼ��
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * ˼�����ʷ��޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getSqxsxx(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getSqxsxxs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("sxzzddfview");
	}
	
	/**
	 * ˼�����ʷ��޸ı�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszbModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataSxzzf(model);//�����¼
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��");
		}
		return mapping.findForward("sxzzddfview");
	}
	
	/**
	 * ʵ���ֵ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//�����б�
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// �û�����
		String userName = (String)session.getAttribute("userName");// �û�����
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sjfadd");
	}
	
	/**
	 * ʵ���ֵ����ӱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxszbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveSjlxf(model);//�����¼
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��,ԭ������Ϊ��" + sFlag
					+ "����¼�д��ڴ�������!");
		}
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		appendProperties(request, xydm, zydm, nj);//�����б�
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("sjfadd");
	}
	
	/**
	 * ʵ�����޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getSjlxcxf(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getSjlxcxfs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("sjlxview");
	}
	
	/**
	 * ʵ�����޸ı�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxszbModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataSjlx(model);//�����¼
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��");
		}
		return mapping.findForward("sjlxview");
	}
	
	/**
	 * ʵ��������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//�û�����
		String userDep = getUserDep(request);//�û�����
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//����
		tableName = "view_" + realTable;//��ͼ��
		String sFlag = service.sjlxcxszbDel(ynysForm.getCbv(), request);//����ɾ��
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "���е�" + sFlag + "����ɾ��ʧ��!");
		}
		String excelb = "ynys_" + realTable + ".xls";//ģ������
		request.setAttribute("excelb", excelb);
		appendProperties(request, xydm, zydm, nj);//�����б�
		appendTableXx(request, tableName, realTable);//���ر���,��ͼ��
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * ��ѧ�Ļ����ʷ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// �û�����
		String userName = (String)session.getAttribute("userName");// �û�����
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);//ѧ����Ϣ
			if (StringUtils.isNull(rs.get("xh"))) {
				rs.put("stuExists", "no");//ѧ�Ų�����
			}
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		ynysForm.setKcmc(null);
		ynysForm.setCj(null);
		ynysForm.setDf(null);
		ynysForm.setSfbxk(null);
		return mapping.findForward("kxwhszfadd");
	}
	
	/**
	 * ��ѧ�Ļ����ʷֱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveKxwhf(model);//�����¼
		if (StringUtils.isNull(sFlag)) {//�ɹ�
			request.setAttribute("inserted", "yes");
		} else {//ʧ��
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��,ԭ������Ϊ��" + sFlag
					+ "����¼�д��ڴ�������!");
		}
		ynysForm.setCj(null);
		ynysForm.setKcmc(null);
		ynysForm.setDf(null);
		ynysForm.setSfbxk(null);
		request.setAttribute("rs", new HashMap<String, String>());
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("kxwhszfadd");
	}
	
	/**
	 * ��ѧ�Ļ����ʷ��޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getKxwhszf(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getKxwhszfs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("kxwhszfview");
	}
	
	/**
	 * ��ѧ�Ļ����ʷ��޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfbmodisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataKxwhszf(model);//�����¼
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��");
		}
		return mapping.findForward("sxzzddfview");
	}
	
	/**
	 * ��ѧ�Ļ�������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxszfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//�û�����
		String userDep = getUserDep(request);//�û�����
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//����
		tableName = "view_" + realTable;//��ͼ��
		String sFlag = service.kxwhfDel(ynysForm.cbv, request);//����ɾ��
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "���е�" + sFlag + "����ɾ��ʧ��!");
		}
		String excelb = "ynys_" + realTable + ".xls";//ģ������
		request.setAttribute("excelb", excelb);
		appendProperties(request, xydm, zydm, nj);//�����б�
		appendTableXx(request, tableName, realTable);//���ر���,��ͼ��
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * ���ķ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxlxszfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//�����б�
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// �û�����
		String userName = (String)session.getAttribute("userName");// �û�����
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sxlxfadd");
	}
	
	/**
	 * ���������ֱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllszbsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveSxllf(model);//�����¼
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��,ԭ������Ϊ��" + sFlag
					+ "����¼�д��ڴ�������!");
		}
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		appendProperties(request, xydm, zydm, nj);//�����б�
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("sxlxfadd");
	}
	
	/** ʵ�����޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getSxllf(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getSxllfs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("sxllview");
	}
	
	/** ʵ�����޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllfszbmodi(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataSxllfsz(model);//�����¼
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "����ʧ��");
		}
		return mapping.findForward("sxllview");
		}
	
	/** ʵ����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
	String userType = getUserType(request);//�û�����
	String userDep = getUserDep(request);//�û�����
	SzcpService service = new SzcpService();
	String tableName = "";
	String realTable = "";
	if (StringUtils.isEqual("xy", userType)) {
		xydm = userDep;
		ynysForm.setXydm(xydm);
	}
	ynysForm.setSzlx(request.getParameter("szlx"));
	realTable = service.getRealTable(request.getParameter("szlx"));//����
	tableName = "view_" + realTable;//��ͼ��
	String sFlag = service.sxllfDel(ynysForm.getCbv(), request);
	if (StringUtils.isNull(sFlag)) {
		request.setAttribute("deleted", "yes");
	} else {
		request.setAttribute("deleted", "no");
		request.setAttribute("failinfo", "���е�" + sFlag + "����ɾ��ʧ��!");
	}
	String excelb = "ynys_" + realTable + ".xls";//ģ������
	request.setAttribute("excelb", excelb);
	appendProperties(request, xydm, zydm, nj);//�����б�
	appendTableXx(request, tableName, realTable);//���ر���,��ͼ��
	String cxfs = request.getParameter("szlx");
	if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
		request.setAttribute("ty", "1");
	}
	return mapping.findForward("szcpwh");
	}
}
