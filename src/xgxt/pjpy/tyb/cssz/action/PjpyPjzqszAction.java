/**
 * @ClassName: PjpyPjzqszAction.java
 *  
 * Date: 
 * 
 */
package xgxt.pjpy.tyb.cssz.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.pjpy.tyb.cssz.model.PjpyPjzqszModel;
import xgxt.pjpy.tyb.cssz.service.PjpyPjzqszService;
import xgxt.utils.FormModleCommon;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * �������� - �������� - ��������ʱ�䣬��������
 * 
 * @author lt
 * @version 1.0 2010-6-23
 */
public class PjpyPjzqszAction extends CommonAction {

	
	/**
	 * ����ʱ��,��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjsjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PjpyPjzqszActionForm zqForm = (PjpyPjzqszActionForm) form;
		PjpyPjzqszService service = new PjpyPjzqszService();
		
		String act = request.getParameter("act");
		
		/* ��������ʱ��,�������� */
		if (SAVE.equalsIgnoreCase(act)) {
			PjpyPjzqszModel model = new PjpyPjzqszModel();
			BeanUtils.copyProperties(model, zqForm);
			//�޸���������ʱ��,
			boolean result = service.updatePjsj(model);
			appendOperResult(request, result);
			
			//����ɹ���ˢ��BASE����Ľ�ѧ������ѧ��,ѧ��,���
			if (result) {
				HashMap<String, String> map = service.queryXtszbxx();
				Base.setJxjsqxn(map.get("jxjsqxn"));
				Base.setJxjsqxq(map.get("jxjsqxq"));
				Base.setJxjsqnd(map.get("jxjsqnd"));
				Base.setJxjsqxqmc(map.get("jxjxqmc"));
				if (service.hasZczq()) {
					String zczq = "";
					if ("checked".equalsIgnoreCase(model.getZcnd())) {
						zczq = "nd";
					} else if ("checked".equalsIgnoreCase(model.getZcxq())) {
						zczq = "xq";
					} else if ("checked".equalsIgnoreCase(model.getZcxn())) {
						zczq = "xn";
					}
					Base.setZczq(zczq);
				}
			}
		}
		
		/* ��ѯ����ʱ��,������Ϣ */
		HashMap<String, String> rs = service.queryPjpysj();
		request.setAttribute("rs", rs);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("hasZczq", service.hasZczq());//����Ƿ������ۺϲ�������
		return mapping.findForward("pjsjsz");
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		return mapping.findForward("cssz");
	}
}
