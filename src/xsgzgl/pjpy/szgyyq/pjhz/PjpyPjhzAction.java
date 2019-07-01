package xsgzgl.pjpy.szgyyq.pjhz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_��������_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyPjhzAction extends BasicAction {

	/**
	 * ��������_��������_�ɼ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjhzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyPjhzService service=new PjpyPjhzService();
		PjpyPjhzForm myForm=(PjpyPjhzForm)form;
		String go=request.getParameter("go");
		if("go".equals(go)){//�ɼ���ѯ
			service.getPjhzList(request, myForm);
		}else if("export".equals(go)){//�ɼ�����
			String back=service.exportPjhzList(response, myForm);
			if(back==null){
				return null;
			}else{
				request.setAttribute("yhInfo", back);
				return new ActionForward("/yhInfo.do");
			}
		}
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		
		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		
		return mapping.findForward("cjhzManage");
	}
	
}
