
package xgxt.wjcf.gdby;

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

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㶫����ѧԺΥ�ʹ���Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-15</p>
 */
public class WjcfGdbyAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	CommonAction commonAction = new CommonAction();//���û�ȡ���ص�LIST����
	
	/**
	 * Υ�ʹ������Ĭ��ҳ��
	 * wjcfshdefault ---- Υ�ʹ������Ĭ��ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//�û�����
		String bmdm     = session.getAttribute("userDep").toString();//�û����ڲ���
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			gdbyForm.setXydm(xydm);
		}
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ��LIST
		commonAction.appendCflbCfyy(request);//���ش�����𣬴���ԭ���б�
		return mapping.findForward("wjcfshdefault");
	}
	
	/**
	 * Υ�ʹ�����˲�ѯ
	 * wjcfshqry ---- Υ�ʹ�����˲�ѯ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		WjcfGdbyService service = new WjcfGdbyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//�û�����
		String bmdm     = session.getAttribute("userDep").toString();//�û����ڲ���
		String userName = session.getAttribute("userName").toString();
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			gdbyForm.setXydm(xydm);
		}
		WjcfShModel wjcfModel = new WjcfShModel();//Υ�ʹ������MODEL
		BeanUtils.copyProperties(wjcfModel, gdbyForm);
		List<HashMap<String, String>> topList = service.getWjcfshTitle(userType, userName);//��ѯ��ͷ
		List<String[]> resList = service.getWjcfshResult(userType, userName, wjcfModel);//��ѯ���
		commonAction.appendResult(request, topList, resList);//���ز�ѯ��ͷ�����
		commonAction.appendCflbCfyy(request);//���ش�����𣬴���ԭ���б�
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ��LIST
		return mapping.findForward("wjcfshdefault");
	}
	
	/**
	 * Υ�ʹ��������ʾ��ϸ��Ϣ
	 * wjcfshview ---- Υ�ʹ��������ʾ��ϸ��Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		WjcfGdbyService service = new WjcfGdbyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//�û�����
		String userName = session.getAttribute("userName").toString();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> resMap = service.getWjcfShView(userType, userName, pkValue);
		if (resMap != null) {
			gdbyForm.setCfsj(resMap.get("cfsj"));
			gdbyForm.setCfwh(resMap.get("cfwh"));
			gdbyForm.setCflb(resMap.get("cflb"));
			gdbyForm.setCfyy(resMap.get("cfyy"));
			gdbyForm.setSh(resMap.get("sh"));
			gdbyForm.setClyj(resMap.get("clyj"));
		}
		if (!StringUtils.isNull(userType) && StringUtils.isEqual(userType, "admin")) {
			request.setAttribute("updated", "no");//����Ա���߱����Ȩ��
		} else {
			request.setAttribute("updated", "yes");
		}
		commonAction.appendCflbCfyy(request);//���ش�����𣬴���ԭ���б�
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ��LIST
		commonAction.appendChkList(request);//��ȡ����б�
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("wjcfshview");
	}
	
	/**
	 * Υ�ʹ��ֵ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjcfSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGdbyActionForm gdbyForm = (WjcfGdbyActionForm) form;
		WjcfGdbyService service = new WjcfGdbyService();
		HttpSession session = request.getSession();
		ShResultModel shModel = new ShResultModel();//�������MODEL
		BeanUtils.copyProperties(shModel, gdbyForm);
		shModel.setXh(request.getParameter("xh"));
		shModel.setPkValue(request.getParameter("pkValue"));
		String userType = session.getAttribute("userType").toString();//�û�����
		String userName = session.getAttribute("userName").toString();
		boolean bChk = service.chkDataExists(shModel, request.getParameter("pkValue"));//����ǰ�ȼ�������Ƿ��ظ�
		if (bChk) {
			request.setAttribute("inserted", "exists");
		} else {
			boolean bFlag = service.shResult(userType, userName, shModel, request);//�������
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		gdbyForm.setClyj(DealString.toGBK(gdbyForm.getClyj()));
		gdbyForm.setCfwh(DealString.toGBK(gdbyForm.getCfwh()));
		commonAction.appendCflbCfyy(request);//���ش�����𣬴���ԭ���б�
		commonAction.appendChkList(request);//��ȡ����б�
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("wjcfshview");
	}
}
