
package xgxt.wjcf.csmz;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺΥ�ʹ���Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class WjcfCsmzAction extends BasicAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	String xq = "";
	
	/**
	 * �����������Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfshDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		WjcfCsmzService service = new WjcfCsmzService();
		HttpSession session    = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String userType        = session.getAttribute("userType").toString();//�û�����
//		�û���ѧԺʱ
		
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		if (StringUtils.isNull(csmzForm.getXn())) {
			csmzForm.setXn(Base.currXn);
		}
		
		wjcfcsmzcxcfQry(mapping, form, request, response);
		
//		appendProperities(request, xydm, zydm, nj, xq);
		request.setAttribute("userType", session.getAttribute("userType"));
		List<HashMap<String, String>> slList = service.getList();//��ȡ����б�
		request.setAttribute("shList", slList);
		return mapping.findForward("wjcfcxmzcxcfshdefault");
	}
	
	/**
	 * �������ֲ�ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user =getUser(request);
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String xh = DealString.toGBK(csmzForm.getXh());
		String xm = DealString.toGBK(csmzForm.getXm());

		HttpSession session   = request.getSession();

		String userName= session.getAttribute("userName").toString();
		String userType= session.getAttribute("userType").toString();//�û�����
		String isFdy= session.getAttribute("fdyQx").toString();//����ԱȨ��
		
		//�û���ѧԺ�����Ա��û����˵�λ�ķ���
		
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		if (StringUtils.isNull(csmzForm.getXn())) {
			csmzForm.setXn(Base.currXn);
		}
		WjcfCsmzCxcfModel wjcfcsmzcxcfModel = new WjcfCsmzCxcfModel();//��ѯMODEL
		BeanUtils.copyProperties(wjcfcsmzcxcfModel, csmzForm);
		WjcfCsmzService service = new WjcfCsmzService();
		//�����û����Ͳ�ͬ�����������ͷ
		List<HashMap<String, String>> topList = service.getSearchTitle(userType, isFdy);
		List<String[]> resList = new ArrayList<String[]>();
		
		//���칤��ְҵ������Ա�������Σ�ѧԺ��ѧУ������ˣ�
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			resList=service.getSearchResult(user, wjcfcsmzcxcfModel);
		}else{
			resList=service.getSearchResult(userName, userType, isFdy, wjcfcsmzcxcfModel);
		}
		csmzForm.setXh(xh);
		csmzForm.setXm(xm);
		//appendProperities(request, xydm, zydm, nj, xq);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("userType", userType);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		List<HashMap<String, String>> slList = service.getList();//��ȡ����б�
		request.setAttribute("shList", slList);
		return mapping.findForward("wjcfcxmzcxcfshdefault");
	}
	
	/**
	 * ����������Ϣ��ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfCsmzService service = new WjcfCsmzService();
		String userType = request.getSession().getAttribute("userType").toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		HashMap<String, String> resMap =new HashMap<String,String>();
		
		
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			resMap= service.getCxcfQryRestlt(user, pkVal);
		}else{
			resMap= service.getCxcfQryRestlt(userType, isFdy, pkVal);
		}
		
		List<HashMap<String, String>> slList = service.getList();//��ȡ����б�
		
		csmzForm.setSh(resMap.get("sh"));
		csmzForm.setShyj(resMap.get("shyj"));
		csmzForm.setJcsj(resMap.get("jcsj"));
		csmzForm.setJcwh(resMap.get("jcwh"));
		csmzForm.setFwbm(resMap.get("fwbm"));
		csmzForm.setCxjg(resMap.get("cxjg"));
		csmzForm.setBz(resMap.get("bz"));
		request.setAttribute("pkValue", pkVal);
		request.setAttribute("shList", slList);
		request.setAttribute("rs", resMap);
		return mapping.findForward("wjcfcsmzcxcfview");
	}
	
	/**
	 * �������������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfcsmzcxcfSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String jdwh = DealString.toGBK(csmzForm.getJdwh());
		
		WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel = new WjcfCsmzCxcfSaveModel();
		BeanUtils.copyProperties(wjcfsmzcxcfsaveModel, csmzForm);
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		WjcfCsmzService service = new WjcfCsmzService();
		
		String userType = request.getSession().getAttribute("userType").toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		HashMap<String, String> resMap = service.getCxcfQryRestlt(userType, isFdy, pkVal);
		List<HashMap<String, String>> slList = service.getList();//��ȡ����б�
		csmzForm.setSh(resMap.get("sh"));
		csmzForm.setShyj(resMap.get("shyj"));
		csmzForm.setJdwh(jdwh);
		csmzForm.setJcsj(resMap.get("jcsj"));
		csmzForm.setCxjg(resMap.get("cxjg"));
		request.setAttribute("pkValue", pkVal);
		request.setAttribute("shList", slList);
		request.setAttribute("rs", resMap);
		boolean bFlag =false;
		bFlag = service.saveCxcfInfo(userType, isFdy, pkVal, wjcfsmzcxcfsaveModel, request);
		//������ӹ���ְҵѧԺ
		if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
			//��WJCFB�м����Ƿ����ͳ�������
			bFlag = service.updateCxcfInfo(wjcfsmzcxcfsaveModel);
		}
		
		if (bFlag) {
			request.setAttribute("done", "yes");
		} else {
			request.setAttribute("done", "no");
		}//end if
		csmzForm.setJcwh("");
		csmzForm.setFwbm("");
		csmzForm.setBz("");
		csmzForm.setCxjg("");
		return mapping.findForward("wjcfcsmzcxcfview");
	}
	
	/**
	 * ���������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cxcfplsh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		WjcfCsmzService service = new WjcfCsmzService();
		String userType = request.getSession().getAttribute("userType").toString();
		String isFdy = request.getSession().getAttribute("fdyQx").toString();
		String isBzr = request.getSession().getAttribute("bzrQx").toString();
		String pkString = DealString.toGBK(request.getParameter("pkString"));
		
		if("save".equals(request.getParameter("operType"))){
			WjcfCsmzCxcfSaveModel wjcfsmzcxcfsaveModel = new WjcfCsmzCxcfSaveModel();
			
			BeanUtils.copyProperties(wjcfsmzcxcfsaveModel, csmzForm);
			boolean result = false;
			if(Globals.XXDM_CQGCZY.equalsIgnoreCase(Base.xxdm)){
				
				result = service.saveCxcfInfoByCqgc(userType, isFdy,isBzr, wjcfsmzcxcfsaveModel);
				
			}else{
				result = service.saveCxcfInfoPl(userType, isFdy, wjcfsmzcxcfsaveModel);
				
			}
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		List<HashMap<String, String>> slList = service.getList();//��ȡ����б�
		request.setAttribute("pkString", pkString);
		request.setAttribute("shList", slList);
		return mapping.findForward("cxcfplsh");
	}
	
	/**
	 * ������������Ĭ��ҳ��
	 * cxcfsq---������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = new HashMap<String, String>();
		WjcfCsmzService service = new WjcfCsmzService();
		String sUserType = request.getSession().getAttribute("userOnLine")
							.toString();//�û�����
		String xh = request.getParameter("xh");
		if (StringUtils.isEqual(sUserType, "student")) {
			xh = request.getSession().getAttribute("userName").toString();
			resMap = service.getStuInfo(xh, "");//�����ѧ����ֱ�Ӳ�ѯѧ��������Ϣ
		} else {
			resMap = service.getStuInfo(xh, pkValue);//�����ѧ����ֱ�Ӳ�ѯѧ��������Ϣ
		}
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//��ȡϵͳ��ǰʱ��
		//currDate += dd.getTime();
		if (!StringUtils.isNull(pkValue)) {
			resMap = service.getcfInfo(pkValue);
		}
		resMap.put("cxsj", currDate);
		request.setAttribute("rs", resMap);
		if(Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("print", "true");
		}
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * ����������ϸ��Ϣ��ʾ
	 * cxcfsq---������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfSqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		
		String pkVal = DealString.toGBK(request.getParameter("cfid"));//����xh||cfwh||cfsj
		WjcfCsmzService service = new WjcfCsmzService();
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getStuInfo(xh, pkVal);//��ȡѧ��������Ϣ��Υ����Ϣ
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//��ȡϵͳ��ǰʱ��
		resMap.put("cxsj", currDate);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("rs", resMap);
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * ���泷������������Ϣ
	 * cxcfSq----������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savecxcfSqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		CxcfSqSaveModel cxcfSaveModel = new CxcfSqSaveModel();//��������MODEL
		BeanUtils.copyProperties(cxcfSaveModel, csmzForm);
		WjcfCsmzService service = new WjcfCsmzService();
		String xh = csmzForm.getXh();
		String pkVal = DealString.toGBK(request.getParameter("pkValue"));
		String cfsj = csmzForm.getCfsj();
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {//��ɳ����ѧԺ
			boolean bHg = service.chkStuTj(cfsj);//���ѧ�����������Ƿ�ϸ�,��һ���������
			if (bHg) {
				boolean bFlag = service.saveCxcfSqlInfo(cxcfSaveModel, request);
				if (bFlag) {
					request.setAttribute("inserted", "ok");
				}else {
					request.setAttribute("inserted", "no");
				}
			}else {
				request.setAttribute("isHG", "no");
			}
		} else {//����ѧУ����Ҫ�����������(�㽭��ý����Ҫ���)
				boolean bFlag = service.saveCxcfSqlInfo(cxcfSaveModel, request);
				if (bFlag) {
					request.setAttribute("inserted", "ok");
				}else {
					request.setAttribute("inserted", "no");
				}
		}
		
		HashMap<String, String> resMap = new HashMap<String, String>();
		resMap = service.getStuInfo(xh, pkVal);//��ȡѧ��������Ϣ��Υ����Ϣ
		dealDate dd = new dealDate();
		String currDate = dd.getToday().replace("-", "");//��ȡϵͳ��ǰʱ��
		resMap.put("cxsj", currDate);
		request.setAttribute("pk", cxcfSaveModel.getXh()+DealString.toGBK(cxcfSaveModel.getCfwh())+cxcfSaveModel.getCfsj()+currDate);
		resMap.put("cfwh", DealString.toGBK(request.getParameter("cfwh")));
		resMap.put("xn", request.getParameter("xn"));
		resMap.put("xq", request.getParameter("xq"));
		resMap.put("cfsj", request.getParameter("cfsj"));
		resMap.put("cflbmc", DealString.toGBK(request.getParameter("cflbmc")));
		resMap.put("cfyymc", DealString.toGBK(request.getParameter("cfyymc")));
		request.setAttribute("rs", resMap);
		csmzForm.setBz(DealString.toGBK(csmzForm.getBz()));
		return mapping.findForward("cxcfsqdefault");
	}
	
	/**
	 * �������ֲ�ѯĬ��ҳ��
	 * cxcfcx----�������ֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfCxDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		WjcfCsmzService service = new WjcfCsmzService();
		String xxdm = StandardOperation.getXxdm();
		String sUserType = request.getSession().getAttribute("userType").toString();//�û�����
		//ѧ�����������ѯҳ��
		if (StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")) {
			String xh = request.getSession().getAttribute("userName").toString();//ѧ��
			List<HashMap<String, String>> topList = service.stuCxcfTitle(xxdm);
			List<String[]> resList = service.stuCxcfResult(xh, xxdm);
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("num", resList != null ? resList.size() : 0);
			return mapping.findForward("stucxcfview");
		}
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		
		csmzCxcfCx(mapping, form, request, response);
		
		//appendProperities(request, xydm, zydm, nj, xq);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("path", "csmz_cxcfcxdefault.do");
		FormModleCommon.commonRequestSet(request);
		List<HashMap<String, String>> slList = service.getList();//��ȡ����б�
		request.setAttribute("shList", slList);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * �������ֲ�ѯ
	 * cxcfcx----�������ֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward csmzCxcfCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		User user=getUser(request);
		
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		nj = request.getParameter("nj");
		zydm = request.getParameter("zydm");
		csmzForm.setXydm(xydm);
		String xxdm = StandardOperation.getXxdm();
		String xh = DealString.toGBK(csmzForm.getXh());
		String xm = DealString.toGBK(csmzForm.getXm());
		WjcfCsmzCxcfModel cxcfModel = new WjcfCsmzCxcfModel();//ҳ���ѯMODEL
		
		cxcfModel.setUser(user);
		BeanUtils.copyProperties(cxcfModel, csmzForm);
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> resList = new ArrayList<String[]>();
		WjcfCsmzService service = new WjcfCsmzService();
		cxcfModel.setUserType(userType);
		topList = service.getCxcfQryTit(cxcfModel, xxdm);
		resList = service.getCxcfQryRes(cxcfModel, xxdm);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", "csmz_cxcfcxdefault.do");
		FormModleCommon.commonRequestSet(request);
		csmzForm.setXh(xh);
		csmzForm.setXm(xm);
		request.setAttribute("topTr", topList);//��ͷ�б�
		request.setAttribute("rs", resList);//������б�
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		List<HashMap<String, String>> slList = service.getList();//��ȡ����б�
		request.setAttribute("shList", slList);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * ����ɾ������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delCxcfInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String[] cbv = csmzForm.getCbv();
		WjcfCsmzService service = new WjcfCsmzService();
		String sFlag = service.delCxcfInfo(cbv);
		if (!StringUtils.isNull(sFlag)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sFlag);
		}//end if
		appendProperities(request, xydm, zydm, nj, xq);
		return mapping.findForward("cxcfcxdefault");
	}
	
	/**
	 * ����������ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stucxcfViews(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		
		WjcfCsmzService service = new WjcfCsmzService();
		HashMap<String, String> resMap = service.stuInfoByPk(pkValue);
		
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
	
		return mapping.findForward("scxcfview");
	}
	
	/**
	 * �������ֲ�ѯ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxcfDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCsmzActionForm csmzForm = (WjcfCsmzActionForm) form;
		String act = request.getParameter("act");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		WjcfCsmzService service = new WjcfCsmzService();
		HashMap<String, String> resMap = service.stuInfoByPk(pkValue);
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			boolean bFlag = service.update(pkValue, DealString.toGBK(request.getParameter("bz")), request);
			csmzForm.setBz("");
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("act", act);
		request.setAttribute("rs", resMap);
		return mapping.findForward("scxcfview");
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
		xy = StringUtils.isNull(xy) ? "" : xy;
		zy = StringUtils.isNull(zy) ? "" : zy;
		nj = StringUtils.isNull(nj) ? "" : nj;
//		xqLocal = xq==null ? "": xq;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String realTable = "wjcf_xskqccb";
		String tableName = "view_xskqxx";
		String sUserType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("userType", sUserType);//�û�����
		request.setAttribute("writeAble", "yes");//��дȨ
		request.setAttribute("tableName", tableName);//��ͼ��
		request.setAttribute("realTable", realTable);//����
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
	}
}
