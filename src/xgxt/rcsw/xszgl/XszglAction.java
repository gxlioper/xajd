package xgxt.rcsw.xszgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.audit.AuditGnmc;
import xgxt.audit.AuditUtil;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.szyqxy.zhszcp.PjpySzyqxyZhszcpDAO;
import xgxt.rcsw.RcswForm;
import xgxt.rcsw.RcswService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;
import xgxt.xszz.zgmsxy.XszzZgmsxyService;

import com.zfsoft.basic.BasicAction;
import common.exception.SystemException;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ճ�����ѧ��֤����-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lr
 * @version 1.0
 * 
 * 
 * 2011-06-15 ѧ��֤�������������� @author qph
 * 
 * 
 */

public class XszglAction extends BasicAction {

	/**
	 * ѧ��֤�������� 
	 * @return ActionForward
	 */
	public ActionForward xszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		RcswForm model = (RcswForm)form;
		RcswService rcswService = new RcswService(); 
		XszglService service = new XszglService();
		String[] topTr = new String[]{"pkValue","ѧ��","����","�꼶",Base.YXPZXY_KEY+"����",
									  "רҵ����","�༶����","����ʱ��","��������"};
		String doType = request.getParameter("doType");
		String[] gwmc = AuditUtil.getGwmcByGnmc(AuditGnmc.XSZBB);
		String tableName = "xszgl_xszbbsqb";
		
		//ɾ��
		if(DEL.equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.delXszbb(pkValues) ? DEL_SUCCESS :DEL_FAIL);
			
			doType = QUERY;
		}
		
		//��ѯ
		if(QUERY.equals(doType)){
			String[] colList = new String[]{"disabled","pkValue","xh",
						"xm","nj","xymc","zymc","bjmc","sqsj","bblxmc"};
			try{
				List<String[]> result = service.queryXszbb(model, colList,gwmc,request);
				request.setAttribute("rs", result);
			} catch(SystemException e){
				catchSystemException(request,e);
			}
		}
		
		//����
		if (EXP.equals(doType)){
			String[] colList = new String[]{"xh","xm","nj","xymc","zymc","bjmc","sqsj","bblxmc"};
			List<String[]> rs = service.queryXszbb(model, colList,gwmc,request);
			topTr = new String[]{"ѧ��","����","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����","����ʱ��"};
			topTr = StringUtils.joinStrArr(topTr,gwmc);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(rs,topTr,topTr, response.getOutputStream());
		}
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		request.setAttribute("gwmc", gwmc);
		request.setAttribute("shjgList", rcswService.getSelectList("shjg"));
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "rcsw_xszgl.do?method=xszbbsq");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("topTr", StringUtils.joinStrArr(topTr,gwmc));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		//��������
		request.setAttribute("bblxList", rcswService.getRcglList("xszbblxdmb", "bblxdm", "bblxmc", null, null, null));
		return mapping.findForward("xszbbsq");
	}
	
	/**
	 * ѧ��֤������������
	 * @return ActionForward
	 */
	public ActionForward xszbbsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszglService service = new XszglService();
		RcswService rcswService = new RcswService();
		XsxxglService xsxxglService = new XsxxglService();
		
		String type = request.getParameter("type");
		String xh = request.getParameter("xh");
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		
		//ѧ����½
		if("stu".equalsIgnoreCase(userType)){
			xh = userName;
		}
		
		if("save".equalsIgnoreCase(type)){
			insertOperation(request, "xszgl_xszbbsqb");
			
			boolean result = (Boolean)request.getAttribute("result");
			String sqsj = request.getParameter("save_sqsj");
			xh = request.getParameter("save_xh");
			
			if (result){
				result = service.saveXszbbsh(xh, sqsj, AuditGnmc.XSZBB);
			}
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		HashMap<String, String> rs = xsxxglService.selectStuinfo(xh);
		rs.put("save_xh", rs.get("xh"));
		rs.put("save_sqsj", GetTime.getNowTime2());
		
		request.setAttribute("rs", rs);
		FormModleCommon.commonRequestSet(request);
		//��������
		request.setAttribute("bblxList", rcswService.getRcglList("xszbblxdmb", "bblxdm", "bblxmc", null, null, null));
		return mapping.findForward("xszbbsqAdd");
	}	
	
	/**
	 * ѧ��֤����������Ϣ�޸�
	 * @return ActionForward
	 */
	public ActionForward xszbbsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswService service = new RcswService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String type = request.getParameter("type");
		
		if(SAVE.equalsIgnoreCase(type)){
			updateOperation(request, "xszgl_xszbbsqb");
		}
		
		String pkValue = request.getParameter("pkValue");			
		selectPageDataByOne(request, "xszgl_xszbbsqb", "xszgl_xszbbsqb", pkValue);
		rs = (HashMap<String, String>) request.getAttribute("rs");
		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("rs", rs);
		FormModleCommon.commonRequestSet(request);
		//��������
		request.setAttribute("bblxList", service.getRcglList("xszbblxdmb", "bblxdm", "bblxmc", null, null, null));
		request.setAttribute("type", type);
		return mapping.findForward("xszbbsqModi");
	}
	
	/**
	 * ѧ��֤�������뵼��
	 * @return ActionForward
	 */
	public ActionForward xszbbsqExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] outputColumn = {"xh", "xm", "xb", "nj", "xydm", "xymc", "zydm",
                                 "zymc", "bjdm", "bjmc", "bblxdm", "bblxmc", "sqly",
                                 "sqsj", "xxsh", "xxshyj"};
		this.expPageData(request, response, "xszgl_xszbbsqb", "view_guizhdx_xszbbsqb", outputColumn);
		return mapping.findForward("");
	}
	
	/**
	 * ѧ��֤�������
	 * @return ActionForward
	 */
	public ActionForward xszbbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		RcswForm model = (RcswForm)form;	
		XszglService service = new XszglService();
		RcswService rcswService = new RcswService();
		
		String gnmc = AuditGnmc.XSZBB;
		String doType = request.getParameter("doType");
		String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
		String[] yygw = AuditUtil.getGwmcByGnmcAndUser(gnmc,userName);
		String[] topTr = new String[]{"pkValue","ѧ��","����","�꼶"
				,Base.YXPZXY_KEY+"����","רҵ����","�༶����","����ʱ��","��������"};
		
		//�������
		if(PLSH.equals(doType)){
			model.setShr(userName);
			model.setShsj(GetTime.getNowTime2());
			
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.plshXszbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
			
			doType= QUERY;
		}
		
		//ȡ�����
		if(QXSH.equals(doType)){
			model.setShjg("δ���");
			model.setShsj("");
			model.setShr("");
			model.setShyj("");
			
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.plshXszbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
			
			doType= QUERY;
		}
		
		
		//��ѯ
		if(QUERY.equals(doType)){
			if (StringUtils.isNull(model.getShgw())){
				try{
					model.setShgw(yygw[0]);
				}catch(RuntimeException re){
					catchSystemException(request,new SystemException("Error-1023"));
				}
			}
			
			User user = service.getUser(request);
			String[] colList = new String[]{"disabled","isdis","pkValue","xh",
								"xm","nj","xymc","zymc","bjmc","sqsj","bblxmc"};
			HashMap<String,Object> map = service.queryXszbbsh(user,model, colList,topTr,gwmc);
			request.setAttribute("rs", map.get("result"));
			topTr = (String[]) map.get("topTr");
		}
		
		if ("xy".equals(userType)){
			model.setXydm(userDep);
		} else if("stu".equals(userType)){
			request.setAttribute("message", "�Բ�������Ȩ���ʴ�ҳ");
			return new ActionForward("/prompt.do",false);
		}
		
		request.setAttribute("shjgList", rcswService.getSelectList("shjg"));
		request.setAttribute("shztList", rcswService.getSelectList("shzt"));
		request.setAttribute("yygw", yygw);//ӵ�и�λ
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "rcsw_xszgl.do?method=xszbbsh");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		//��������
		request.setAttribute("bblxList", rcswService.getRcglList("xszbblxdmb", "bblxdm", "bblxmc", null, null, null));
		return mapping.findForward("xszbbsh");
	}
	
	/**
	 * ѧ��֤���쵥�����
	 * @return ActionForward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward xszbbshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswForm model = (RcswForm) form;
		XszglService service = new XszglService();
		RcswService rcswService = new RcswService();
		XsxxglService xsxxglService = new XsxxglService();
		String gnmc =  AuditGnmc.XSZBB;
		String tableName = "xszgl_xszbbsqb";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		if (SAVE.equals(doType)){//������˱���
			request.setAttribute("message", service.plshXszbb(model, new String[]{pkValue}, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		//���ص�����¼��Ϣ
		selectPageDataByOne(request, tableName, tableName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		//����ѧ����Ϣ
		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
		request.setAttribute("rs", rs);
		//�����Ϣ
		List<HashMap<String,String>> shxx = service.getXszbbShxx(pkValue, gnmc);
		
		request.setAttribute("shxx", shxx);
		request.setAttribute("shztList", rcswService.getSelectList("shzt"));
		FormModleCommon.commonRequestSet(request);
		//��������
		request.setAttribute("bblxList", rcswService.getRcglList("xszbblxdmb", "bblxdm", "bblxmc", null, null, null));
		request.setAttribute("shsj", GetTime.getNowTime2());//���ʱ��
		request.setAttribute("doType", doType);
		return mapping.findForward("xszbbshOne");
	}
	
	
	/**
	 * ѧ��֤�Ǽ� 
	 * @return ActionForward
	 */
	public ActionForward xszdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		RcswForm model = (RcswForm)form;
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String go = request.getParameter("go");
		String type = request.getParameter("type");
		String tableName = "rcsw_xszdj";
		String viewName = "view_rcsw_xszdj";
		
		if("student".equalsIgnoreCase(userOnLine)){
			model.setQuerylike_xh(userName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = {"pkValue", "xh", "xm", "xb", "nj", "xymc", 
					                 "zymc", "bjmc", "ccqj", "djsj", "xxsh"};
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		if("del".equalsIgnoreCase(type)){
			this.deleteOperation(request, tableName);
		}
		
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request, 
				                                 model.getQueryequals_xydm(),
				                                 model.getQueryequals_zydm(), 
				                                 model.getQueryequals_bjdm(), 
				                                 model.getQueryequals_nj());
		request.setAttribute("realTable", tableName);	
		
		request.setAttribute("path", "rcsw_xszgl.do?method=xszdj");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszdj");
	}
	
	/**
	 * ѧ��֤�Ǽ�����
	 * @return ActionForward
	 */
	public ActionForward xszdjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XsxxglService xsxxglService = new XsxxglService();
		String type = request.getParameter("type");
		String xh = request.getParameter("xh");
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
			xh = userName;
		}
		
		if("save".equalsIgnoreCase(type)){
			this.insertOperation(request, "rcsw_xszdj");
			String pkValue = xh+GetTime.getNowTime2();
			
			selectPageDataByOne(request, "rcsw_xszdj", "view_rcsw_xszdj", pkValue);
			rs = (HashMap<String, String>) request.getAttribute("rs");
		}
		rs.putAll(xsxxglService.selectStuinfo(xh));
		rs.put("save_xh", rs.get("xh"));
		rs.put("save_djsj", GetTime.getNowTime2());
		
		request.setAttribute("rs", rs);
		FormModleCommon.commonRequestSet(request);		
		return mapping.findForward("xszdjAdd");
	}	
	
	/**
	 * ѧ��֤�Ǽ���Ϣ�޸�
	 * @return ActionForward
	 */
	public ActionForward xszdjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String type = request.getParameter("type");
		
		if("save".equalsIgnoreCase(type)){
			this.updateOperation(request, "rcsw_xszdj");
		}
		
		String pkValue = request.getParameter("pkValue");			
		selectPageDataByOne(request, "rcsw_xszdj", "view_rcsw_xszdj", pkValue);
		rs = (HashMap<String, String>) request.getAttribute("rs");
		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("rs", rs);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("type", type);
		return mapping.findForward("xszdjModi");
	}
	
	/**
	 * ѧ��֤�Ǽǵ���
	 * @return ActionForward
	 */
	public ActionForward xszdjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] outputColumn = {"xh", "xm", "xb", "nj", "xydm", "xymc", "zydm",
                                 "zymc", "bjdm", "bjmc", "ccqj", "djsj", "xxsh", 
                                 "xxshyj"};
		this.expPageData(request, response, "rcsw_xszdj", "view_rcsw_xszdj", outputColumn);
		return mapping.findForward("");
	}
	
	/**
	 * ѧ��֤�Ǽ����
	 * @return ActionForward
	 */
	public ActionForward xszdjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswForm model = (RcswForm)form;	
		String go = request.getParameter("go");
		String type = request.getParameter("type");
		String tableName = "rcsw_xszdj";
		String viewName = "view_rcsw_xszdj";
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = {"pkValue", "xh", "xm", "xb", "nj", "xymc", 
					                 "zymc", "bjmc", "ccqj", "djsj", "xxsh"};
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		if("save".equalsIgnoreCase(type)){
			this.auditingBatchOperation(request, tableName);
		}
				
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request, 
				                                 model.getQueryequals_xydm(),
				                                 model.getQueryequals_zydm(), 
				                                 model.getQueryequals_bjdm(), 
				                                 model.getQueryequals_nj());
		request.setAttribute("realTable", tableName);
		request.setAttribute("shsj", GetTime.getNowTime2());	
		request.setAttribute("path", "rcsw_xszgl.do?method=xszdjsh");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszdjsh");
	}
	
	/**
	 * ѧ��֤�Ǽǵ������
	 * @return ActionForward
	 */
	public ActionForward xszdjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String type = request.getParameter("type");
		String tableName = "rcsw_xszdj";
		
		if("save".equalsIgnoreCase(type)){//���
			this.updateOperation(request, tableName);
		}
		String pkValue = request.getParameter("pkValue");			
		selectPageDataByOne(request, "rcsw_xszdj", "view_rcsw_xszdj", pkValue);
		rs = (HashMap<String, String>) request.getAttribute("rs");
		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("rs", rs);
		FormModleCommon.commonRequestSet(request);		
		request.setAttribute("shsj", GetTime.getNowTime2());
		return mapping.findForward("xszdjshOne");
	}
	
	/**
	 * ѧ��֤����������ӡ
	 * @return ActionForward
	 */
	public ActionForward xszbbPrintOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService xsxxglService = new XsxxglService();
		String xh = request.getParameter("xh");
		
		HashMap<String, String> xmMap = new HashMap<String, String>();
		HashMap<String, String> timeMap = new HashMap<String, String>();
		String xm = xsxxglService.getStuName(xh);
		xmMap.put("xm", xm);
		String nowTime = xsxxglService.getNowTime();
		timeMap.put("nowYear", nowTime.substring(0, 4));
		timeMap.put("nowMonth", nowTime.substring(5, nowTime.lastIndexOf("-")));
		timeMap.put("nowDay", nowTime.substring(nowTime.lastIndexOf("-") + 1, nowTime.length()));	
		
		request.setAttribute("xmMap", xmMap);
		request.setAttribute("timeMap", timeMap);
		return mapping.findForward("xszbb_cn_one");
	}
	
	/**
	 * ѧ��֤����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszblManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		RcswForm myForm = (RcswForm) form;
		XszglService service = new XszglService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		String ffsj=request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		myForm.setUser(user);
		String message="";
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delXszbl(myForm);
			message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			request.setAttribute("message", message);
		}
		
		if("plff".equalsIgnoreCase(doType)){
			boolean flag=service.addXszbl(myForm);
			message = flag ? MessageInfo.MESSAGE_EXTEND_SUCCESS
					: MessageInfo.MESSAGE_EXTEND_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		String showNum = String.valueOf(service.getTopTr("xszffb",rForm).size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		myForm.setUser(user);
		rForm.setRsArrList((ArrayList<String[]>)service.queryXszbl(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		request.setAttribute("jbrList", service.getJbrList());
		request.setAttribute("path", "rcsw_xszgl.do?method=xszblManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		//��ͷ
		request.setAttribute("topTr", service.getTopTr("xszffb",rForm));
		return mapping.findForward("xszblManage");
	}
	
	/**
	 * ѧ��֤ע��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszzcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		RcswForm myForm = (RcswForm) form;
		XszglService service = new XszglService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		String ffsj=request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		myForm.setUser(user);
		String message="";
		
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delXszzc(myForm);
			message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			request.setAttribute("message", message);
		}
		
		if("plzc".equalsIgnoreCase(doType)){
			boolean flag=service.plXszzc(myForm);
			message = flag ? MessageInfo.MESSAGE_ENROL_SUCCESS
					: MessageInfo.MESSAGE_ENROL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		String showNum = String.valueOf(service.getTopTr("xszzc",rForm).size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		myForm.setUser(user);
		
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		
		rForm.setRsArrList((ArrayList<String[]>)service.queryXszc(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "rcsw_xszgl.do?method=xszzcManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		
		
		//��ͷ
		request.setAttribute("jbrList", service.getJbrList());
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("topTr", service.getTopTr("xszzc",rForm));
		return mapping.findForward("xszzcManage");
	}
	

	/**
	 * ѧ��֤ע��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszzxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		RcswForm myForm = (RcswForm) form;
		XszglService service = new XszglService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		String ffsj=request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		myForm.setUser(user);
		String message="";
		
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delXszzx(myForm);
			message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			request.setAttribute("message", message);
		}
		
		if("plzx".equalsIgnoreCase(doType)){
			boolean flag=service.plXszzx(myForm);
			message = flag ? MessageInfo.MESSAGE_LOGOUT_SUCCESS
					: MessageInfo.MESSAGE_LOGOUTL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		String showNum = String.valueOf(service.getTopTr("xszzx",rForm).size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		myForm.setUser(user);
		rForm.setRsArrList((ArrayList<String[]>)service.queryXszzx(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "rcsw_xszgl.do?method=xszzxManage");
		
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		
		
		//��ͷ
		request.setAttribute("ydlbList", service.getYdlbList());
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("topTr", service.getTopTr("xszzx",rForm));
		return mapping.findForward("xszzxManage");
	}
	
	
	
	/**
	 * ѧ��֤ע��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszzcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		User user=getUser(request);
		RcswForm myForm = (RcswForm) form;
		XszglService service = new XszglService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);
		
		// =================ѧ��֤ע�� begin==================
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addXszzc(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================ѧ��֤ע�� end==================
		
		request.setAttribute("path", "rcsw_xszgl.do?method=xszzcManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("jbrList", service.getJbrList());
		request.setAttribute("rs", stuInfo);
		request.setAttribute("doType", "add");
		return mapping.findForward("xszzcUpdate");
	}
	
	/**
	 * ѧ��֤ע����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszzcOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
	
		RcswForm myForm = (RcswForm) form;
		XszglService service = new XszglService();

		String pkValue = request.getParameter("pkValue");
		XsxxglService stuService = new XsxxglService();
		
		HashMap<String,String>rs=new HashMap<String,String>();
		// ѧ��֤ע����Ϣ
		myForm.setPkValue(pkValue);
		rs=service.getXszzcMap(myForm);
		
		// ѧ����Ϣ
		rs.putAll(stuService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("path", "rcsw_xszgl.do?method=xszzcManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType", "view");
		return mapping.findForward("xszzcUpdate");
	}
	
	/**
	 * ѧ��֤ע��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward xszzxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		User user=getUser(request);
		RcswForm myForm = (RcswForm) form;
		XszglService service = new XszglService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);
		
		// =================ѧ��֤ע�� begin==================
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addXszzx(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================ѧ��֤ע�� end==================
		
		request.setAttribute("path", "rcsw_xszgl.do?method=xszzxManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("ydlbList", service.getYdlbList());
		request.setAttribute("rs", stuInfo);
		request.setAttribute("doType", "add");
		return mapping.findForward("xszzxUpdate");
	}
	
	/**
	 * ѧ��֤ע����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszzxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
	
		RcswForm myForm = (RcswForm) form;
		XszglService service = new XszglService();

		String pkValue = request.getParameter("pkValue");
		XsxxglService stuService = new XsxxglService();
		
		HashMap<String,String>rs=new HashMap<String,String>();
		// ѧ��֤ע����Ϣ
		myForm.setPkValue(pkValue);
		rs=service.getXszzxMap(myForm);
		
		// ѧ����Ϣ
		rs.putAll(stuService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("path", "rcsw_xszgl.do?method=xszzxManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType", "view");
		return mapping.findForward("xszzxUpdate");
	}
	
	
}
