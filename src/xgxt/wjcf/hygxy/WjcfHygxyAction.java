
package xgxt.wjcf.hygxy;

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
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.WjcfOperactionAction;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺΥ�ʹ���Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-23</p>
 */
public class WjcfHygxyAction extends CommonAction {

	String xydm = "";//ѧԺ����
	String zydm = "";//רҵ����
	String nj = "";//�꼶
	String xq = "";//ѧ��
	
	CommonAction commonAction = null;//�����б���ACTION

	/**
	 * ���ٽ�����¼Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyjlDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();//�û�����
		String sUserDep = session.getAttribute("userDep").toString();//�û�����
		if (StringUtils.isEqual(sUserType, "xy")) {
			xydm = sUserDep;
		}
		commonAction = new CommonAction();
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		request.setAttribute("tableName", "view_wjcf_gzjy");//��ͼ��
		request.setAttribute("realTable", "wjcf_gzjyb");//����
		return mapping.findForward("gzjyjldefault");
	}
	
	/**
	 * ���ٽ�����ѯ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String sUserType = session.getAttribute("userType").toString();//�û�����
		String sUserDep = session.getAttribute("userDep").toString();//�û�����
		if (StringUtils.isEqual(sUserType, "xy")) {
			xydm = sUserDep;
			hygxyForm.setXydm(xydm);
		}
		hygxyForm.setXm(DealString.toGBK(hygxyForm.getXm()));
		WjcfGzjyModel gzjyModel = new WjcfGzjyModel();//���ٽ���MODEL
		BeanUtils.copyProperties(gzjyModel, hygxyForm);
		List<HashMap<String, String>> titleList = service.getQueryTitle("wjcf_gzjyb");//��ѯ��ͷ
		List<String[]> resList = service.getGzjyQueryResult(gzjyModel);//��ѯ���
		commonAction = new CommonAction();
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		request.setAttribute("topTr", titleList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��ѯ��¼��
		request.setAttribute("tableName", "view_wjcf_gzjy");//��ͼ��
		request.setAttribute("realTable", "wjcf_gzjyb");//����
		return mapping.findForward("gzjyjldefault");
	}
	
	/**
	 * ���ٽ�����¼��������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		commonAction = new CommonAction();
		WjcfHygxyService service = new WjcfHygxyService();
		HashMap<String, String> resMap = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String tmpPk = request.getParameter("pkval");
		if (!StringUtils.isNull(tmpPk)) {
			resMap = service.getStuWjxx(xh, tmpPk);
		}
		request.setAttribute("xh", xh);
		request.setAttribute("pkval", tmpPk);
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		return mapping.findForward("gzjyadd");
	}
	
	/**
	 * ���ٽ�����¼�������ӱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		commonAction = new CommonAction();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		WjcfGzjyModel model = new WjcfGzjyModel();//���ٽ���MODEL
		hygxyForm.setXh(request.getParameter("xh"));
		hygxyForm.setCfxn(request.getParameter("cfxn"));
		hygxyForm.setCfxq(request.getParameter("cfxq"));
		hygxyForm.setCfsbsj(request.getParameter("cfsbsj"));
		BeanUtils.copyProperties(model, hygxyForm);
		model.setCfpk(request.getParameter("pkValue"));
		boolean bFlag = service.saveGzjy(model, request);//������ټ�¼
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		hygxyForm.setJyr("");
		hygxyForm.setJyrbx("");
		hygxyForm.setJysj("");
		hygxyForm.setJyzt("");
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("gzjyadd");
	}
	
	/**
	 * ���ٽ�����¼�����޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		commonAction = new CommonAction();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		HashMap<String, String> resMap = service.viewGzjy(pkValue);//��ʾ�޸���Ϣ
		if (resMap != null) {
			hygxyForm.setXn(resMap.get("xn"));
			hygxyForm.setNd(resMap.get("nd"));
			hygxyForm.setXq("0" + resMap.get("xq"));
			hygxyForm.setJyr(resMap.get("jyr"));
			hygxyForm.setJyrbx(resMap.get("jyrbx"));
			hygxyForm.setJysj(resMap.get("jysj"));
			hygxyForm.setJyzt(resMap.get("jyzt"));
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cfpk", resMap.get("cfpk"));
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", resMap);
		request.setAttribute("act", act);
		return mapping.findForward("gzjyview");
	}
	
	/**
	 * ���ٽ�����¼�����޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfHygxyService service = new WjcfHygxyService();
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		hygxyForm.setXh(request.getParameter("xh"));
		hygxyForm.setCfpk(request.getParameter("cfpk"));
		WjcfGzjyModel model = new WjcfGzjyModel();
		BeanUtils.copyProperties(model, hygxyForm);
		boolean bFlag = service.updateGzjy(model, pkValue, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		hygxyForm.setJyr("");
		hygxyForm.setJyrbx("");
		hygxyForm.setJysj("");
		hygxyForm.setJyzt("");
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap());
		return mapping.findForward("gzjyview");
	}
	
	/**
	 * ���ٽ�����¼����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();//�û�����
		String sUserDep = session.getAttribute("userDep").toString();//�û�����
		if (StringUtils.isEqual(sUserType, "xy")) {
			xydm = sUserDep;
		}
		commonAction = new CommonAction();
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ�������б�
		request.setAttribute("tableName", "view_wjcf_gzjy");//��ͼ��
		request.setAttribute("realTable", "wjcf_gzjyb");//����
		WjcfHygxyActionForm hygxyForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String[] keys = hygxyForm.getCbv();
		String sFlag = service.deleteGzjy(keys, request);//����ɾ��
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", String.format("��ʾ: ��%1$s������ɾ��ʧ��!", sFlag));
		}
		return mapping.findForward("gzjyjldefault");
	}
	
	/**
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plfw(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfHygxyActionForm hForm = (WjcfHygxyActionForm) form;
		String pkVal = request.getParameter("str");
		pkVal = StringUtils.isNull(pkVal) ? "" : pkVal.substring(0, pkVal.length()-1);
		WjcfHygxyService service = new WjcfHygxyService();
		//String pkVal ="" ;//= service.listToString(list);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			pkVal = request.getParameter("pkVal");
			String cfsj = request.getParameter("cfsj");
			String cfwh = DealString.toGBK(request.getParameter("cfwh"));
			boolean result = service.plfw(pkVal, cfwh, cfsj);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		hForm.setCfwh(DealString.toGBK(request.getParameter("cfwh")));
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("tips", "��ǰ����λ�ã�Υ�ʹ��� - ��� - ѧУ�������");
		return mapping.findForward("plfwpage");
	}
	
	/**
	 * ίԱ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wyhsl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfHygxyActionForm hForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String act = request.getParameter("act");
		hForm.setXm(DealString.toGBK(hForm.getXm()));
		List<HashMap<String, String>> title = new ArrayList<HashMap<String,String>>();
		List<String[]> list = new ArrayList<String[]>();
		if ("query".equalsIgnoreCase(act)) {
			WjcfGzjyModel model = new WjcfGzjyModel();
			BeanUtils.copyProperties(model, hForm);
			title = service.wyhslTitle();
			list = service.wyhslQuery(model);
		}
		request.setAttribute("rs", list);
		request.setAttribute("topTr", title);
		request.setAttribute("rsNum", list != null ? list.size() : 0);
		request.setAttribute("tips", "��ǰ����λ�ã� Υ�ʹ��� - ���ߴ��� - ίԱ������");
		appendProperties(request, xydm, zydm, nj);
		appendCflbCfyy(request);
		return mapping.findForward("wyhslpage");
	}
	
	/**
	 * ίԱ�ᵥ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wyhslone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		WjcfHygxyService service = new WjcfHygxyService();
		HashMap<String, String> rs = service.wyhslone(pkValue);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			WjcfGzjyModel model = new WjcfGzjyModel();
			model.setWyhsl(request.getParameter("wyhsl"));
			model.setWyhsllr(request.getParameter("wyhsllr"));
			boolean flag = service.updateWhysl(model, pkValue, request);
			if (flag) {
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}
		request.setAttribute("rs", rs);
		request.setAttribute("tips", "��ǰ����λ�ã� Υ�ʹ��� - ���ߴ��� - ίԱ������");
		request.setAttribute("rswj", WjcfOperactionAction.GetFileList1(pkValue));
		request.setAttribute("pkValue", pkValue);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("wyhslonepage");
	}
	
	/**
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plsl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfHygxyActionForm hForm = (WjcfHygxyActionForm) form;
		WjcfHygxyService service = new WjcfHygxyService();
		String pkValue = request.getParameter("str");
		pkValue = StringUtils.isNull(pkValue) ? "" : pkValue.substring(0, pkValue.length()-1);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			pkValue = request.getParameter("pkValue");
			boolean result = service.plsl(pkValue, DealString.toGBK(request.getParameter("wyhsl")), DealString.toGBK(request.getParameter("wyhsllr")));
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("pkValue", pkValue);
		hForm.setWyhsl(DealString.toGBK(request.getParameter("wyhsl")));
		hForm.setWyhsllr(DealString.toGBK(request.getParameter("wyhsllr")));
		request.setAttribute("tips", "��ǰ����λ�ã� Υ�ʹ��� - ���ߴ��� - ίԱ������");
		return mapping.findForward("plslpage");
	}
}
