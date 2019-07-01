
package xgxt.wjcf.jgsdx;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.FormModleCommon;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.csmz.WjcfCsmzService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧΥ�ʹ���Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class WjcfJgsdxAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	String xq  = "";
	
	/**
	 * ������������Ĭ��ҳ��
	 * cxcfSqDefault ---- ������������Ĭ��ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		//appendProperities(request, xydm, zydm, nj, xq);
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();//�û�����
		String sUserName = "";//�û���
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		//ѧ���û�
		if (StringUtils.isEqual(sUserType, "student") || StringUtils.isEqual(sUserType, "stu")) {
			sUserName = session.getAttribute("userName").toString();
			stuMap = service.getStuInfo(sUserName);//��ȡѧ����Ϣ
		}else {//�����û���ҳ���ϻ�ȡѧ��
			String xh = request.getParameter("xh");
			stuMap = service.getStuInfo(xh);
		}
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//��ȡϵͳ��ǰʱ��
		stuMap.put("cxsj", currDate);
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		request.setAttribute("rs", stuMap);
		return mapping.findForward("cxcfsqdefault");
	}
	
	public ActionForward cxcfInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
//		WjcfJgsdxService services = new WjcfJgsdxService();
		WjcfCsmzService service = new WjcfCsmzService();
		String xh = request.getParameter("xh");
		String pkValue = DealString.toGBK(request.getParameter("cfid"));
		HashMap<String, String> resMap = service.getStuInfo(xh, pkValue);
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//��ȡϵͳ��ǰʱ��
		resMap.put("cxsj", currDate);
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		request.setAttribute("rs", resMap);
		String xxdm = StandardOperation.getXxdm();
		if (!Globals.XXDM_JGSDX.equalsIgnoreCase(xxdm)) {
			return mapping.findForward("csmzcxcf");
		}
		return mapping.findForward("cxcfsqdefault");
	}
	/**
	 * �����������뱣��
	 * cxcfSqSave ---- �����������뱣��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		WjcfJgsdxService service = new WjcfJgsdxService();
		CxcfSqSaveModel cxcfSaveModel = new CxcfSqSaveModel();//������������
		BeanUtils.copyProperties(cxcfSaveModel, jgsdxForm);
		String xh = jgsdxForm.getXh();
		String cfsj = jgsdxForm.getCfsj();
		boolean bTj = service.chkStuTj(cfsj);
		if (bTj) {//���ѧ�����������Ƿ�ϸ�,��һ���������
			boolean bFlag = service.saveCxcfSqlInfo(cxcfSaveModel, request);//������������
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			}else {
				request.setAttribute("inserted", "no");
			}
		}else {
			request.setAttribute("isHG", "no");
		}
		HashMap<String, String> stuMap = service.getStuInfo(xh);//��ȡѧ����Ϣ
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//��ȡϵͳ��ǰʱ��
		stuMap.put("cxsj", currDate);
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		request.setAttribute("rs", stuMap);
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * �������ֲ�ѯĬ��ҳ��
	 * cxcfCxdefault ---- �������ֲ�ѯĬ��ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfCxdefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sUserType = request.getSession().getAttribute("userType").toString();//�û�����
		//ѧ�����������ѯҳ��
		if (StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")) {
			String xh = request.getSession().getAttribute("userName").toString();//ѧ��
			WjcfJgsdxService service = new WjcfJgsdxService();
			List<String[]> stuList = service.getStuWjcfinfo(xh);//ѧ��Υ����Ϣ���
			List<HashMap<String, String>> topList = service.getStuWjcfTit(xh);//��ͷ
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", stuList);
			request.setAttribute("num", stuList != null ? stuList.size() : 0);
			return mapping.findForward("stucxcfcx");
		}
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * �������ֲ�ѯ���
	 * cxcfCx ---- �������ֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		CxcfQryModel cxcfModel = new CxcfQryModel();//�������ֲ�ѯMODEL
		BeanUtils.copyProperties(cxcfModel, jgsdxForm);
		WjcfJgsdxService service = new WjcfJgsdxService();
		List<HashMap<String, String>> topList = service.getCxcfSearchTitle();//��ѯ��ͷ
		List<String[]> resList = service.getCxcfSearchResult(cxcfModel);//��ѯ���
		jgsdxForm.setXm(DealString.toGBK(jgsdxForm.getXm()));
		
//		appendProperities(request, xydm, zydm, nj, xq);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * �������ֵ�����ӡ
	 * cxcfPrint ---- �������ִ�ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
//		String xh = request.getParameter("xh");
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>(); 
	    stuMap = service.getStuInfo1(pkValue);//ͨ��������ȡѧ�������Ϣ
		request.setAttribute("rs", stuMap);
		return mapping.findForward("cxcfprint");
	}
	
	/**
	 * ����������Ϣ����ɾ��
	 * cxcfdel ---- ��������ɾ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		String[]keys = jgsdxForm.getCbv();//�����б�
		WjcfJgsdxService service = new WjcfJgsdxService();
		String sJg = service.wjcfCxcfPlDel(keys, request);//�����������ݵ�����ɾ��
		if (!StringUtils.isNull(sJg)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", "noview");
		}
		
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * ������������Ĭ��ҳ��
	 * cxcfXxspDefault ---- ������������Ĭ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfXxspDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfxxspdefault");
	}
	
	/**
	 * ��������������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfspQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		WjcfJgsdxService service = new WjcfJgsdxService();
		CxcfQryModel cxcfModel = new CxcfQryModel();
		BeanUtils.copyProperties(cxcfModel, jgsdxForm);
		List<HashMap<String, String>> topList = service.getCxcfSpTitle();//��ѯ��ͷ
		List<String[]> resList = service.getCxcfSpResult(cxcfModel);//��ѯ���
		jgsdxForm.setXm(DealString.toGBK(jgsdxForm.getXm()));
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfxxspdefault");
	}
	
	/**
	 * ������������
	 * cxcfSp ---- ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));//����
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> resMap = service.getCxcfInfoByPk(pkValue);//ͨ��������ȡ����������ϸ��Ϣ
		List<HashMap<String, String>> spztList = service.getChList(3);//����״̬
		jgsdxForm.setSpzt(resMap.get("spzt"));
		jgsdxForm.setCxsj(resMap.get("cxsj"));
		jgsdxForm.setCxwh(resMap.get("cxwh"));
		request.setAttribute("spztList", spztList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("writeAble", "yes");//��дȨ
 		return mapping.findForward("cxcfsp");
	}
	
	/**
	 * ����������ϸ��Ϣ��ʾ
	 * cxcfView ---- ����������ϸ��Ϣ��ʾ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> resMap = service.getCxcfInfoByPk(pkValue);//����������ʾ��Ϣ
		request.setAttribute("rs", resMap);
		request.setAttribute("writeAble", "yes");//��дȨ
		return mapping.findForward("cxcfview");
	}
	
	/**
	 * ����������������
	 * cxcfSpSave ---- ���������������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSpSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfJgsdxActionForm jgsdxForm = (WjcfJgsdxActionForm) form;
		CxcfSpSaveModel cxcfsqModel = new CxcfSpSaveModel();//������������MODEL
		BeanUtils.copyProperties(cxcfsqModel, jgsdxForm);
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		cxcfsqModel.setPkValue(pkValue);
		WjcfJgsdxService service = new WjcfJgsdxService();
		boolean bFlag = service.cxcfSp(cxcfsqModel, request);
		if (bFlag) {
			request.setAttribute("updated", "yes");
		}else {
			request.setAttribute("updated", "no");
		}
		HashMap resMap = service.getCxcfInfoByPk(pkValue);//����������ʾ��Ϣ
		List<HashMap<String, String>> spztList = service.getChList(3);//����״̬
		jgsdxForm.setBz(DealString.toGBK(jgsdxForm.getBz()));
		jgsdxForm.setCxwh(DealString.toGBK(jgsdxForm.getCxwh()));
		request.setAttribute("spztList", spztList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", resMap);
		request.setAttribute("writeAble", "yes");//��дȨ
		return mapping.findForward("cxcfsp");
	}
	
	/**
	 * ѧ������������ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuCxcfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		WjcfJgsdxService service = new WjcfJgsdxService();
		HashMap<String, String> stuMap = service.getCxcfInfoByPk(pkValue);
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("stucxcfview");
	}
	
	/**
	 * �����м��ϴ����request��
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 */
	private void appendProperities(HttpServletRequest request,String xydm, String zydm, String nj, String xq){
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
		String sUserType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("userType", sUserType);//�û�����
		request.setAttribute("writeAble", "yes");//��дȨ
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
	}
}
