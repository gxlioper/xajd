package xgxt.pjpy.njjs;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.audit.AuditGnmc;
import xgxt.audit.AuditUtil;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import common.exception.SystemException;

/**
 * ��ʦּܲ���Ͼ���ʦ���Ի�����
 * �Ƚ��༶
 * @author ³�� 2011.6.27
 */
public class XjbjAction extends BasicAction{

	
	/**
	 * ����༯������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward xjbjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		
		XjbjForm model = (XjbjForm) form;
		XjbjService service = new XjbjService();
		XsxxglService xsxxglService = new XsxxglService();
		
		if ("stu".equals(userType)){
			
			HashMap<String, String> stuInfo = xsxxglService.selectStuinfo(userName);
			request.setAttribute("stuInfo", stuInfo);
		} else if ("xy".equals(userType)){
			model.setXydm(userDep);
		}
		
		
		request.setAttribute("doType", request.getParameter("doType"));
		request.setAttribute("sqsj", GetTime.getTimeByFormat("yyyy-mm"));
		request.setAttribute("xjlxList", service.getWhList("xg_pjpy_yxbjlxb", "dm", "mc", null, null, null));
		request.setAttribute("path", "njjs_yxbjt.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xjbjsq");
	}
	
	
	/**
	 * �Ƚ��༶�屣��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_pjpy_njjs_yxbjt";
		XjbjForm model = (XjbjForm) form;
		XjbjService service = new XjbjService();
		
		insertOperation(request, tableName);
		boolean result = (Boolean) request.getAttribute("result");
		
		if (result){
			result = service.saveXjbjbbsh(model, AuditGnmc.YXBJT);
		}
		
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return xjbjsq(mapping, form, request, response);
	}
	
	
	/**
	 * �Ƚ��༶����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		
		XjbjForm model = (XjbjForm) form;
		XjbjService service = new XjbjService();
		XsxxglService xsxxglService = new XsxxglService();
		
		String doType = request.getParameter("doType");
		String[] gwmc = AuditUtil.getGwmcByGnmc(AuditGnmc.YXBJT);
		String[] topTr = new String[]{"pkValue","�༶����","��������","�༶����","�೤ѧ��",
				"������","����ʱ��"};
		
		if ("stu".equals(userType)){
			model.setBjdm(xsxxglService.selectStuinfo(userName).get("bjdm"));
		}
		
		//ɾ��
		if(DEL.equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.delXjbj(pkValues) ? DEL_SUCCESS :DEL_FAIL);
			
			doType = QUERY;
		}
		
		
		//��ѯ
		if (QUERY.equals(doType)){
			
			String[] colList = new String[]{"disabled","pkValue","bjmc",
							"yxlxmc","bjrs","bzxh","bzrzgh","sqsj"};
			try{
				List<String[]> result = service.queryXjbj(model, colList,gwmc,request);
				request.setAttribute("rs", result);
			} catch(SystemException e){
				catchSystemException(request,e);
			}
		}
		
		//����
		if (EXP.equals(doType)){
			String[] colList = new String[]{"bjmc","yxlxmc","bjrs","bzxh","bzrzgh","sqsj"};
			List<String[]> rs = service.queryXjbj(model, colList,gwmc,request);
			topTr = new String[]{"�༶����","��������","�༶����","�೤ѧ��","������","����ʱ��"};
			topTr = StringUtils.joinStrArr(topTr,gwmc);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(rs,topTr,topTr, response.getOutputStream());
			return mapping.findForward("");
		}
		
		
		if ("xy".equals(userType)){
			model.setXydm(userDep);
		}
		
		request.setAttribute("shjgList", service.getSelectList("shjg"));
		request.setAttribute("gwmc", gwmc);
		request.setAttribute("topTr", StringUtils.joinStrArr(topTr,gwmc));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "njjs_yxbjtgl.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xjbjManage");
	}
	
	
	
	/**
	 * �Ƚ��༶���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjAudi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userNameReal = (String) session.getAttribute("userNameReal");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("stu".equals(userType)){
			request.setAttribute("message", "�Բ�������Ȩ���ʴ�ҳ!");
			return new ActionForward("/prompt.do",false);
		}
		
		
		XjbjForm model = (XjbjForm) form;
		XjbjService service = new XjbjService();
		String gnmc = AuditGnmc.YXBJT;
		String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
		String[] yygw = AuditUtil.getGwmcByGnmcAndUser(gnmc, userName);
		String[] topTr = new String[] { "pkValue", "�༶����", "��������", 
							"�༶����","�೤ѧ��", "������", "����ʱ��" };
		String doType = request.getParameter("doType");
		
		//�������
		if(PLSH.equals(doType)){
			model.setShr(userNameReal);
			model.setShsj(GetTime.getNowTime2());
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.plshXjbjt(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
			
			doType= QUERY;
		}
		
		//ȡ�����
		if(QXSH.equals(doType)){
			model.setShjg("δ���");
			model.setShsj("");
			model.setShr("");
			model.setShyj("");
			
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.plshXjbjt(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
			
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
			String[] colList = new String[]{"disabled","isdis","pkValue","bjmc",
									"yxlxmc","bjrs","bzxh","bzrzgh","sqsj"};
			HashMap<String,Object> map = service.queryXjbjsh(user,model, colList,topTr,gwmc);
			request.setAttribute("rs", map.get("result"));
			topTr = (String[]) map.get("topTr");
		}
		
		if ("xy".equals(userType)){
			model.setXydm(userDep);
		}
		
		request.setAttribute("yygw", yygw);//ӵ�и�λ
		request.setAttribute("topTr", topTr);
		request.setAttribute("shjgList", service.getSelectList("shjg"));
		request.setAttribute("shztList", service.getSelectList("shzt"));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "njjs_yxbjtsh.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xjbjAudi");
	}
	
	
	
	/**
	 * �Ƚ��༶�������
	 * @return ActionForward
	 */
	public ActionForward xjbjDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XjbjForm model = (XjbjForm) form;
		XjbjService service = new XjbjService();
		String gnmc =  AuditGnmc.YXBJT;
		String tableName = "xg_pjpy_njjs_yxbjt";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		if (SAVE.equals(doType)){//������˱���
			request.setAttribute("message", service.plshXjbjt(model, new String[]{pkValue}, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		//���ص�����¼��Ϣ
		selectPageDataByOne(request, tableName, tableName, pkValue);
		//�����Ϣ
		List<HashMap<String,String>> shxx = service.getXjbjShxx(pkValue, gnmc);
		
		request.setAttribute("shxx", shxx);
		request.setAttribute("shztList", service.getSelectList("shzt"));
		request.setAttribute("xjlxList", service.getWhList("xg_pjpy_yxbjlxb", "dm", "mc", null, null, null));
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("shsj", GetTime.getNowTime2());//���ʱ��
		request.setAttribute("doType", doType);
		return mapping.findForward("xjbjDgsh");
	}
	
	
	
	/**
	 * �Ƚ��༶�޸�
	 * @return ActionForward
	 */
	public ActionForward xjbjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjbjService service = new XjbjService();
		String tableName = "xg_pjpy_njjs_yxbjt";
		String doType = request.getParameter("doType");
		
		if(SAVE.equalsIgnoreCase(doType)){
			updateOperation(request, tableName);
		}
		
		String pkValue = request.getParameter("pkValue");			
		selectPageDataByOne(request, tableName, tableName, pkValue);
		
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("xjlxList", service.getWhList("xg_pjpy_yxbjlxb", "dm", "mc", null, null, null));
		return mapping.findForward("xjbjUpdate");
	}
	
	
	
	/**
	 * �Ƚ��༶��ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjbjForm model = (XjbjForm) form;
		String gnmc =  AuditGnmc.YXBJT;
		XjbjService service = new XjbjService();
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printXjbj(model, response.getOutputStream(), gnmc);
		
		return null;
	}
}
