package xsgzgl.wjcf.xscfcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.base.DealString;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;
import xsgzgl.rcsw.zjbb.RcswZjbbDAO;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhActionForm;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhService;
import xsgzgl.wjcf.cfssgl.WjcfCfssglServices;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.cfjcgl.WjcfCfjcshService;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;
/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 ����12:36:30
 * </p>
 */
public class WjcfXscfAction extends BasicAction {

	/**
	 * ѧ�����ֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfService service = new WjcfXscfService();
		User user = getUser(request);
		
		if ("stu".equalsIgnoreCase(user.getUserType())
				|| "student".equalsIgnoreCase(user.getUserType())) {
			RequestForm rForm = new RequestForm();
			// ----------------����PATH begin-----------------------
			// ----------------��ʾtitle���ж϶�дȨ----------------
			rForm.setPath("xscfCx.do");
			// ----------------����PATH end-----------------------
			service.setRequestValue(rForm, user, request);
			
		} else {
			String msg = "��ģ��ֻ����<font color='blue'>ѧ���û�</font>���в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * �������ݲ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfCxAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfXscfService service = new WjcfXscfService();
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		myForm.setXh(user.getUserName());
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		service.initCfcxManage(rForm, request);
		myForm.getSearchModel().setPath("xscfCx.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.xscfCx(myForm);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ѧ�����ֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		
		//��ѯ�������
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());
		String pkValue = myForm.getCfid();
		String sswh = rs.get("sswh");
		String jcwh = rs.get("jcwh");
		
		if (StringUtils.isNotNull(sswh)) {
			WjcfCfssglServices ssservice=new WjcfCfssglServices();
			request.setAttribute("rs", ssservice.cfssglCk(pkValue));
			request.setAttribute("pkValue", pkValue);
			request.setAttribute("cfshxxList", ssservice.ssshxxCk(pkValue));
			return mapping.findForward("cfsswhCk");
		} else if (StringUtils.isNotNull(jcwh)) {
			XsxxglService stuService = new XsxxglService();
			String cfId = pkValue;
			WjcfCfjcshService wjcfcfjcshService = new WjcfCfjcshService();

			List<HashMap<String, String>> cfsh = wjcfcfjcshService.getCfjcshxxList(cfId);
			request.setAttribute("cfshList", cfsh);

			HashMap<String, String> cfsbMap = wjcfcfjcshService.getCfxx(cfId);// ��������

			HashMap<String, String> cfjcMap = wjcfcfjcshService.getOnesCfjc(cfId);// ���ֽ������
			
			HashMap<String, String> cfssMap = wjcfcfjcshService.getOnesCfss(cfId);// ������������
			
			if (null != cfsbMap) {
				request.setAttribute("rs", stuService.selectStuinfo(cfsbMap
						.get("xh")));
			}
			request.setAttribute("wjsb", cfsbMap);
			request.setAttribute("cfjc", cfjcMap);
			request.setAttribute("cfss", cfssMap);
			return mapping.findForward("ckCfjc");
		} else {
			request.setAttribute("rs", rs);
		}
		return mapping.findForward("xscfCk");
	}
	

	/**
	 * ѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsssSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		WjcfCfssglServices ssglService = new WjcfCfssglServices();
		myForm.setSqsj(DealString.getDateTime());
		boolean flag = service.xsssSave(myForm);
		
		if (flag){
			//======���칤������=2013-1-15==qph==begin=========
			String xh = myForm.getUserName();
			String[] spgw = ssglService.getSsshSpgw();
			String id = myForm.getCfid();
			
			if (null != spgw && spgw.length > 0){
				Job job = Job.getJobInstance(id, xh, spgw[0], 
						"cfssshCx.do?xtgwid="+spgw[0], 
						"cfsswhCx.do","��������", "��������");
				MyJobsManager manager = new MyJobsImpl();
				manager.pushJob(job);
				
			}
			//======���칤������=2013-1-15==qph==end===========
		}
		
		request.setAttribute("isSuccess", flag==true?"true":"false");
		request.setAttribute("message",flag?"�����ɹ���":"����ʧ�ܣ�");
		
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * ѧ�����ֽ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		myForm.setSqsj(DealString.getDateTime());
		boolean flag = service.cfjcSave(myForm);
		request.setAttribute("isSuccess", flag==true?"true":"false");
		
		request.setAttribute("message",flag?"�����ɹ���":"����ʧ�ܣ�");
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * ��������ȡ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsssQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		boolean flag = service.xsssCx(myForm);
		request.setAttribute("isSuccess", flag==true?"true":"false");
		
		request.setAttribute("message",flag?"�����ɹ���":"����ʧ�ܣ�");
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * ���ֽ��ȡ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfjcQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		boolean flag = service.jcsqCx(myForm);
		request.setAttribute("isSuccess", flag==true?"true":"false");
		
		request.setAttribute("message",flag?"�����ɹ���":"����ʧ�ܣ�");
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
}
