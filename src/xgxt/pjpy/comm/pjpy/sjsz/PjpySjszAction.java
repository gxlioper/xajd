package xgxt.pjpy.comm.pjpy.sjsz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.utils.FormModleCommon;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_ʱ������_action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @version 1.0
 */
public class PjpySjszAction extends BasicAction{
	
	/**
	 * ʱ������
	 */
	public ActionForward sjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySjszForm model = (PjpySjszForm) form;
		PjpySjszService service = new PjpySjszService();
		
		model.setPjxn(PjxtszModel.pjxtszModel.getPjxn());
		model.setPjxq(PjxtszModel.pjxtszModel.getPjxq());
		model.setPjnd(PjxtszModel.pjxtszModel.getPjnd());
		
		List<String[]> rs = service.querySjsz(model);
		request.setAttribute("rs", rs);
		
		request.setAttribute("path", "pjpy_sjsz.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		return mapping.findForward("sjszManage");
	}
	
	
	
	/**
	 * ʱ��������������
	 */
	public ActionForward sjszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySjszForm model = (PjpySjszForm) form;
		PjpySjszService service = new PjpySjszService();
		
		String[] pkValues = request.getParameterValues("pkValue");
		
		if (null != pkValues && pkValues.length > 0){
			String[] sqkzkg = new String[pkValues.length];
			String[] shkzkg = new String[pkValues.length];
			
			for (int i = 0 ; i < pkValues.length ; i++){
				sqkzkg[i] = request.getParameter("sqkzkg"+i);
				shkzkg[i] = request.getParameter("shkzkg"+i);
			}
			model.setSqkzkg(sqkzkg);
			model.setShkzkg(shkzkg);
		}
		
		boolean result = service.sjszSave(pkValues, model);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return sjszManage(mapping, form, request, response);
	}
	
	
	
	/**
	 * ʱ�����õ������ã��ṩ����Ŀά�����ݲ�����
	 */
	public ActionForward sjszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "xg_pjpy_sjszb";
		PjpySjszForm model = (PjpySjszForm) form;
		PjpySjszService service = new PjpySjszService();
		
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			insertOperation(request, tableName);
		}
		
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pjxn+pjxq+pjnd+model.getXmdm());
		
		request.setAttribute("xmmc", xmszModel.getXmmc());
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		return mapping.findForward("sjszUpdate");
	}
	
	/**
	 * ʱ�����õ������ã��ṩ����Ŀά�����ݲ�����
	 */
	public ActionForward sjszFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "xg_pjpy_sjszb";
		PjpySjszForm model = (PjpySjszForm) form;
		PjpySjszService service = new PjpySjszService();
		
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			insertOperation(request, tableName);
			return new ActionForward("/pjpy_comm_rssz.do?method=rsszManageFlow&doType=''",false);
		}
		
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pjxn+pjxq+pjnd+model.getXmdm());
		
		request.setAttribute("xmmc", xmszModel.getXmmc());
		request.setAttribute("xmszModel", xmszModel);
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		return mapping.findForward("sjszUpdate");
	}
}
