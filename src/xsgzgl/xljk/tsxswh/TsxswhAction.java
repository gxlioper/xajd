package xsgzgl.xljk.tsxswh;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.szdw.bjlh.fdyzp.BjlhFdyzpForm;
import xgxt.szdw.bjlh.fdyzp.BjlhFdyzpService;
import xgxt.utils.FormModleCommon;
public class TsxswhAction extends BasicExtendAction{
	/**
	 * ����ѧ��ά����ѯҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "xljk_zjzy_tsxswh.do");
		TsxswhService service = new TsxswhService();
		TsxswhForm myForm = (TsxswhForm) form;
		String doType = request.getParameter("doType");
		//ɾ��
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.deleteTsxsXX(myForm.getCheckVal());
			//boolean flag = service.deleteTsxsXX(request.getParameter("pkValue").split("!!!SplitOne!!!"));
			if (flag) {
				request.setAttribute("message", "ɾ���ɹ���");
			} else {
				request.setAttribute("message", "ɾ��ʧ�ܣ�");
			}
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("tsxswh"));
		request.setAttribute("rs", service.getTableList(myForm, request));
		// write��titile
		setWriteAbleAndTitle(request, "xljk_zjzy_tsxswh.do");

		
		request.setAttribute("realTable", "XG_XLJK_ZJZYY_TSXSXXB"); // �����
		request.setAttribute("tableName", "view_xljk_zjzyy_tsxsxxb"); // ������ͼ

		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tsxswhManage");
	}
	/**
	 * ����ѧ��ά���༭ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxswhEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxswhService service = new TsxswhService();
		TsxswhForm myForm = (TsxswhForm) form;
		String doType = request.getParameter("doType");
		HashMap<String,String> rs = new HashMap<String, String>();
		rs.put("xn", Base.currXn);
		//����
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveTsxsXX(myForm,request);
			if (flag) {
				request.setAttribute("message", "����ɹ���");
			} else {
				request.setAttribute("message", "����ʧ�ܣ�");
			}
		} 
		//�޸Ļ��߲鿴���ݳ�ʼ��
		else if ("modi".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("pkValue");
			rs = service.getOneTsxsXX(pkValue.split("!!!SplitOne!!!"));
		}else if("add".equalsIgnoreCase(doType)){
			String act = request.getParameter("act");
			if(act!=null&&"hx".equalsIgnoreCase(act)){
				rs.put("xn", myForm.getXn());
				rs.put("xq", myForm.getXq());
				rs.put("xh", myForm.getXh());
				rs.putAll(service.getXsxx(myForm.getXh()));
				rs.put("sbsj", myForm.getSbsj());
				rs.put("zywt", myForm.getZywt());
				rs.put("brth", myForm.getBrth());
				rs.put("lxjs", myForm.getLxjs());
				rs.put("bz", myForm.getBz());
				rs.putAll(service.getOneTsxsXX(new String[]{myForm.getXn(),myForm.getXq(),myForm.getXh()}));
			}
			
		}
		request.setAttribute("rs", rs);
		// write��titile
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		setWriteAbleAndTitle(request, "xljk_zjzy_tsxswh.do");
		request.setAttribute("doType", doType);
		return mapping.findForward("tsxswhEdit");
	}
}
