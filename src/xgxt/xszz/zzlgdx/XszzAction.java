package xgxt.xszz.zzlgdx;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭����ѧAction</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-12-17</p>
 */
public class XszzAction extends BaseAction {
//	ҳ�����ֵ��ȡ
	public List<HashMap<String, String>> getParams(HttpServletRequest request) {
		Enumeration paramsEnum = request.getParameterNames();
		List<HashMap<String, String>> valueList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String[]> map_values = new HashMap<String, String[]>();
		int valueListSize = -1;
		while (paramsEnum.hasMoreElements()) {
			String param = (String) paramsEnum.nextElement();
			if (param.startsWith("_")) {
				String[] paramValue = request.getParameterValues(param);
				map_values.put(param.replace("_", ""), paramValue);
				valueListSize = paramValue.length;
			}
		}
		if (valueListSize > -1) {
			for (int i = 0; i < valueListSize; i++) {
				valueList.add(new HashMap<String, String>());
			}
			Set<String> keySet = map_values.keySet();
			for (String key : keySet) {
				String[] values = map_values.get(key);
				for (int i = 0; i < valueList.size(); i++) {
					HashMap<String, String> m = valueList.get(i);
					m.put(key, DealString.toGBK(values[i]));
				}
			}
		}
		return valueList;
	}
	
	/**
	 * ��ѧ������Ŀά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZzlgdxService service = new XszzZzlgdxService();
		XszzZzlgdxActionForm actionForm = (XszzZzlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delZxdk(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getZxdkTit();
		List<String[]> resList = service.getZxdkRes(queryModel,request,actionForm);
		
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setDkhth(DealString.toGBK(actionForm.getDkhth()));
		
		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getZxdkResNum(queryModel, request)));
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�
		
		request.setAttribute("pk", "guid");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_zzlg_zxdk");
		request.setAttribute("tableName", "view_xszz_zzlg_zxdk");
		return mapping.findForward("zxdk");
	}
	
	/**
	 * ��ѧ������Ŀ��Ϣ�༭ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZzlgdxService service = new XszzZzlgdxService();
		String guid = Base.chgNull(request.getParameter("guid"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(guid) || !"".equalsIgnoreCase(xh)) {
			stuMap = service.getZxdkXx(guid);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zxdkEdit");
	}
	
	/**
	 * ��ѧ������Ŀ��Ϣ�༭����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZzlgdxService service = new XszzZzlgdxService();
		XszzZzlgdxActionForm actionForm = (XszzZzlgdxActionForm) form;
		ZxdkModel model = new ZxdkModel();
		BeanUtils.copyProperties(model, actionForm);
		
		String[] bJg = service.saveZxdkxx(model, request);// ������Ϣ
		if ("1".equalsIgnoreCase(bJg[0])) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZxdkXx(bJg[1]);// �õ���ϸ��Ϣ
		
		request.setAttribute("act", "modi");
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zxdkEditSave");
	}
	
	/**
	 * ��ѧ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZzlgdxService service = new XszzZzlgdxService();
		XszzZzlgdxActionForm actionForm = (XszzZzlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getZxdkExp(queryModel, response, request);
		return mapping.findForward("zxdkExp");
	}
	
}
