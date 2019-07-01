package xgxt.rcsw.hcyhk;

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
import xgxt.form.User;
import xgxt.rcsw.RcswService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import common.exception.SystemException;

/**
 * ���Żݿ�����
 * ��ע��������������
 * ���ڣ�2011-06-17
 * @author qph
 * @version 1.0
 */
public class HcyhkAction extends BasicAction {

	
	/**
	 * ���Żݿ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		HcyhkForm model = (HcyhkForm) form;
		HcyhkService service = new HcyhkService();
		RcswService rcswService = new RcswService();
		
		String doType = request.getParameter("doType");
		String[] gwmc = AuditUtil.getGwmcByGnmc(AuditGnmc.HCYHKBB);
		String[] topTr = new String[]{"pkValue","ѧ��","����","�꼶",Base.YXPZXY_KEY+"����",
									"רҵ����","�༶����","����ʱ��"};
		
		//ɾ��
		if(DEL.equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.delHcyhkbb(pkValues) ? DEL_SUCCESS :DEL_FAIL);
			
			doType = QUERY;
		}
		
		
		//��ѯ
		if (QUERY.equals(doType)){
			String[] colList = new String[]{"disabled","pkValue","xh",
					"xm","nj","xymc","zymc","bjmc","sqsj"};
			try{
				List<String[]> result = service.queryHcyhkbb(model, colList,gwmc,request);
				request.setAttribute("rs", result);
			} catch(SystemException e){
				catchSystemException(request,e);
			}
		}
		
		//����
		if (EXP.equals(doType)){
			String[] colList = new String[]{"xh","xm","nj","xymc","zymc","bjmc","sqsj"};
			List<String[]> rs = service.queryHcyhkbb(model, colList,gwmc,request);
			topTr = new String[]{"ѧ��","����","�꼶",Base.YXPZXY_KEY+"����","רҵ����","�༶����","����ʱ��"};
			topTr = StringUtils.joinStrArr(topTr,gwmc);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(rs,topTr,topTr, response.getOutputStream());
			return mapping.findForward("");
		}
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		request.setAttribute("gwmc", gwmc);
		request.setAttribute("shjgList", rcswService.getSelectList("shjg"));
		request.setAttribute("topTr", StringUtils.joinStrArr(topTr,gwmc));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "hcyhk.do?method=yhkManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("yhkManage");
	}
	
	
	/**
	 * ���Żݿ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hcyhkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcyhkForm model = (HcyhkForm) form;
		HcyhkService service = new HcyhkService();
		XsxxglService xsxxglService = new XsxxglService();
		
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		
		if("stu".equalsIgnoreCase(userType)){
			xh = userName;
		}
		
		//����
		if (SAVE.equals(doType)){
			insertOperation(request, "xg_rcsw_hcyhkbb");
			boolean result = (Boolean) request.getAttribute("result");
			
			if (result){
				result = service.saveHcyhkbbsh(model, AuditGnmc.HCYHKBB);
			}
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		//����ѧ��������Ϣ
		HashMap<String, String> rs = xsxxglService.selectStuinfo(xh);
		rs.put("sqsj", GetTime.getNowTime2());//����ʱ��
		request.setAttribute("rs", rs);
		return mapping.findForward("hcyhkAdd");
	}
	
	
	
	/**
	 * ���Żݿ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhkAuditing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		HcyhkForm model = (HcyhkForm) form;
		HcyhkService service = new HcyhkService();
		RcswService rcswService = new RcswService();
		String doType = request.getParameter("doType");
		
		String gnmc = AuditGnmc.HCYHKBB;
		String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
		String[] yygw = AuditUtil.getGwmcByGnmcAndUser(gnmc,userName);
		String[] topTr = new String[]{"pkValue","ѧ��","����","�꼶",
				Base.YXPZXY_KEY+"����","רҵ����","�༶����","����ʱ��"};
		
		//�������
		if(PLSH.equals(doType)){
			model.setShr(userName);
			model.setShsj(GetTime.getNowTime2());
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.plshHcyhkbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
			
			doType= QUERY;
		}
		
		//ȡ�����
		if(QXSH.equals(doType)){
			model.setShjg("δ���");
			model.setShsj("");
			model.setShr("");
			model.setShyj("");
			
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.plshHcyhkbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
			
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
								"xm","nj","xymc","zymc","bjmc","sqsj"};
			HashMap<String,Object> map = service.queryHcyhkbbsh(user,model, colList,topTr,gwmc);
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
		request.setAttribute("path", "hcyhk.do?method=yhkAuditing");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("yhkAuditing");
	}
	
	
	
	/**
	 * ���Żݿ����쵥�����
	 * @return ActionForward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hcyhkDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcyhkForm model = (HcyhkForm) form;
		HcyhkService service = new HcyhkService();
		RcswService rcswService = new RcswService();
		XsxxglService xsxxglService = new XsxxglService();
		String gnmc =  AuditGnmc.HCYHKBB;
		String tableName = "xg_rcsw_hcyhkbb";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		if (SAVE.equals(doType)){//������˱���
			request.setAttribute("message", service.plshHcyhkbb(model, new String[]{pkValue}, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		//���ص�����¼��Ϣ
		selectPageDataByOne(request, tableName, tableName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		//����ѧ����Ϣ
		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
		request.setAttribute("rs", rs);
		//�����Ϣ
		List<HashMap<String,String>> shxx = service.getHcyhkbbShxx(pkValue, gnmc);
		
		request.setAttribute("shxx", shxx);
		request.setAttribute("shztList", rcswService.getSelectList("shzt"));
		FormModleCommon.commonRequestSet(request);
		//��������
		request.setAttribute("shsj", GetTime.getNowTime2());//���ʱ��
		request.setAttribute("doType", doType);
		return mapping.findForward("hcyhkDgsh");
	}
	
	
	
	/**
	 * ���Żݿ������޸�
	 * @return ActionForward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward hcyhkbbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService xsxxglService = new XsxxglService();
		
		String tableName = "xg_rcsw_hcyhkbb";
		String doType = request.getParameter("doType");
		
		if(SAVE.equalsIgnoreCase(doType)){
			updateOperation(request, tableName);
		}
		
		String pkValue = request.getParameter("pkValue");			
		selectPageDataByOne(request, tableName, tableName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
		
		request.setAttribute("rs", rs);
		return mapping.findForward("hcyhkbbUpdate");
	}
}
