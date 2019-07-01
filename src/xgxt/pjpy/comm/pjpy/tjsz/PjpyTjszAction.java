package xgxt.pjpy.comm.pjpy.tjsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * ��������-��������-��������
 * </p>
 * <p>
 * ���ø�����ѧ�������ƺţ����������������ڹ��˽�ѧ����������ϱ�������<br>
 * ����Ŀ�Ƿ����á����Ϊ�����Ŀ���ڱ�ģ�鲻�ɱ�������
 * </p>
 * 
 * @author Penghui.Qu
 */
public class PjpyTjszAction extends BasicAction {

	private static final String SUCCESS = "����ɹ�!";

	private static final String FAIL = "����ʧ��!";

	/**
	 * ����������ҳ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward tjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "xg_pjpy_tjszb";
		String viewName = "xg_view_pjpy_tjms";
		String[] colList = new String[] { "pkValue", "xmmc", "tjms" };
		String[] topTr = new String[] { "pkValue", "��Ŀ����", "��������" };
		String doType = request.getParameter("doType");

		PjpyTjszForm myForm = (PjpyTjszForm) form;
		PjpyXmszService xmszService = new PjpyXmszService();

		if (DEL.equals(doType)) {
			deleteOperation(request, tableName);
			//doType = QUERY;
		}

		//if (QUERY.equals(doType)) {
			selectPageDataByPagination(request, myForm, tableName, viewName,
					colList);
		//}

		xmszService.initList(request);
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("path", "pjpy_xmsz_tjsz.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjszManage");
	}

	/**
	 * ����ѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		PjpyTjszService service = new PjpyTjszService();

		request.setAttribute("tjkList", service.getTjk(xmdm));// �������б�
		request.setAttribute("xmtjList", service.getXmtj(xmdm));// ��Ŀ����
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
		request.setAttribute("xmList", new PjpyCommService()
				.getPjxmList(PjxtszModel.pjxtszModel));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjxz");
	}

	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmtj = request.getParameter("xmtj");
		String[] tjdm = null;
		PjpyTjszForm tjszForm = (PjpyTjszForm) form;
		PjpyTjszService service = new PjpyTjszService();
		String doType = request.getParameter("doType");

		if (StringUtils.isNotNull(xmtj)) {
			tjdm = xmtj.split(",");
		}

		if (SAVE.equals(doType)) {
			request.setAttribute("message",
					service.saveTjsz(tjszForm) ? SUCCESS : FAIL);
			tjdm = tjszForm.getTjdm();
		}

		setList("tjsz", request);
		
		//=============2011.07.21 edit by ΰ�����===========================
		//��Ŀ�����б�
		List<HashMap<String, Object>> xmtjList = service.getXmtjList(tjszForm
				.getXmdm(), tjdm);
		if (xmtjList != null && xmtjList.size() > 0) {
			request.setAttribute("tjNum", xmtjList.size());
		} else {
			request.setAttribute("tjNum", 0);
		}
		request.setAttribute("xmtjList", xmtjList);
		
		
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
//		request.setAttribute("xmtjInfo", service.getXmtjInfo(
//				tjszForm.getXmdm(), tjdm));
		request.setAttribute("xmList", new PjpyCommService()
				.getPjxmList(PjxtszModel.pjxtszModel));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjsz");
	}
	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmtj = request.getParameter("xmtj");
		String[] tjdm = null;
		PjpyTjszForm tjszForm = (PjpyTjszForm) form;
		PjpyTjszService service = new PjpyTjszService();
		String doType = request.getParameter("doType");

		if (StringUtils.isNotNull(xmtj)) {
			tjdm = xmtj.split(",");
		}

		if (SAVE.equals(doType)) {
			request.setAttribute("message",
					service.saveTjsz(tjszForm) ? SUCCESS : FAIL);
			tjdm = tjszForm.getTjdm();
		}

		setList("tjsz", request);
		
		//=============2011.07.21 edit by ΰ�����===========================
		//��Ŀ�����б�
		List<HashMap<String, Object>> xmtjList = service.getXmtjList(tjszForm
				.getXmdm(), tjdm);
		if (xmtjList != null && xmtjList.size() > 0) {
			request.setAttribute("tjNum", xmtjList.size());
		} else {
			request.setAttribute("tjNum", 0);
		}
		request.setAttribute("xmtjList", xmtjList);
		
		
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
//		request.setAttribute("xmtjInfo", service.getXmtjInfo(
//				tjszForm.getXmdm(), tjdm));
		request.setAttribute("xmList", new PjpyCommService()
				.getPjxmList(PjxtszModel.pjxtszModel));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjszFlow");
	}
	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmtj = request.getParameter("xmtj");
		String[] tjdm = null;
		PjpyTjszForm tjszForm = (PjpyTjszForm) form;
		PjpyTjszService service = new PjpyTjszService();
		String doType = request.getParameter("doType");

		if (StringUtils.isNotNull(xmtj)) {
			tjdm = xmtj.split(",");
		}

		if (SAVE.equals(doType)) {
			request.setAttribute("message",
					service.saveTjsz(tjszForm) ? SUCCESS : FAIL);
			tjdm = tjszForm.getTjdm();
			return new ActionForward("/pjpy_ty_sjsz.do?method=sjszFlow&doType=''",false);
		}

		setList("tjsz", request);
		
		//=============2011.07.21 edit by ΰ�����===========================
		//��Ŀ�����б�
		List<HashMap<String, Object>> xmtjList = service.getXmtjList(tjszForm
				.getXmdm(), tjdm);
		if (xmtjList != null && xmtjList.size() > 0) {
			request.setAttribute("tjNum", xmtjList.size());
		} else {
			request.setAttribute("tjNum", 0);
		}
		request.setAttribute("xmtjList", xmtjList);
		
		
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
//		request.setAttribute("xmtjInfo", service.getXmtjInfo(
//				tjszForm.getXmdm(), tjdm));
		request.setAttribute("xmList", new PjpyCommService()
				.getPjxmList(PjxtszModel.pjxtszModel));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjszFlow");
	}
	/**
	 * ���������޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward tjszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "xg_pjpy_tjszb";
		String viewName = "xg_view_pjpy_tjms";
		String doType = request.getParameter("doType");

		PjpyTjszForm myForm = (PjpyTjszForm) form;
		PjpyTjszService service = new PjpyTjszService();

		if (SAVE.equals(doType)) {
			request.setAttribute("message",
					service.updateTjsz(myForm) ? SUCCESS : FAIL);
		}

		if (StringUtils.isNotNull(myForm.getPkValue())) {
			selectPageDataByOne(request, tableName, viewName, myForm
					.getPkValue());
		}

		HashMap<String, String> rs = (HashMap<String, String>) request
				.getAttribute("rs");

		setList("tjsz", request);
		
		// =============2011.07.21 edit by ΰ�����===========================
		// ��Ŀ�����б�
		List<HashMap<String, Object>> xmtjList = service.getXmtjList(myForm
				.getXmdm(), new String[] { rs.get("tjdm") });
		if (xmtjList != null && xmtjList.size() > 0) {
			request.setAttribute("tjNum", xmtjList.size());
		} else {
			request.setAttribute("tjNum", 0);
		}
		request.setAttribute("xmtjList", xmtjList);
		
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
		request.setAttribute("xmtjInfo", service.getXmtjInfo(rs.get("xmdm"),
				new String[] { rs.get("tjdm") }));
		request.setAttribute("xmList", new PjpyCommService()
				.getPjxmList(PjxtszModel.pjxtszModel));
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("doType", "update");
		return mapping.findForward("tjsz");
	}
	
	
	/**
	 * ���������޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward tjszCopyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmdm = request.getParameter("xmdm");
		PjpyTjszService service = new PjpyTjszService();
		PjpyTjszForm myForm = (PjpyTjszForm) form;
		// ��Ŀ�����б�
		HashMap<String, String> rs = (HashMap<String, String>) request
				.getAttribute("rs");
		List<HashMap<String, Object>> xmtjList = null;
		if(rs!=null){
		 xmtjList = service.getXmtjList(myForm
				.getXmdm(), new String[] { rs.get("tjdm") });
		}
		request.setAttribute("xmtjList", xmtjList);
		
		setList("tjsz", request);
		request.setAttribute("tjkList", service.getTjk(xmdm));// �������б�
		request.setAttribute("xmtjList", service.getXmtj(xmdm));// ��Ŀ����
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
		request.setAttribute("xmList", new PjpyCommService()
				.getPjxmList(PjxtszModel.pjxtszModel));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjszCopy");
	}
	
	/**
	 * ���������޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward tjszFlowAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmdm = request.getParameter("xmdm");
		PjpyTjszService service = new PjpyTjszService();
		PjpyTjszForm myForm = (PjpyTjszForm) form;
		// ��Ŀ�����б�
		HashMap<String, String> rs = (HashMap<String, String>) request
				.getAttribute("rs");
		List<HashMap<String, Object>> xmtjList = null;
		if(rs!=null){
		 xmtjList = service.getXmtjList(myForm
				.getXmdm(), new String[] { rs.get("tjdm") });
		}
		request.setAttribute("xmtjList", xmtjList);
		
		setList("tjsz", request);
		request.setAttribute("tjkList", service.getTjk(xmdm));// �������б�
		request.setAttribute("xmtjList", service.getXmtj(xmdm));// ��Ŀ����
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
		request.setAttribute("xmList", new PjpyCommService()
				.getPjxmList(PjxtszModel.pjxtszModel));
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("tjszFlow");
	}

	/**
	 * ����ѡ��
	 * 
	 * @param flg
	 * @param request
	 */
	private void setList(String flg, HttpServletRequest request) {

		PjpyTjszService service = new PjpyTjszService();
		PjpyCommService commService = new PjpyCommService();

		if ("tjsz".equals(flg)) {
			// ������ϵ
			List<HashMap<String, String>> gxList = service.getList("tjgx");
			request.setAttribute("gxList", gxList);

			// �ǡ���
			List<HashMap<String, String>> isnotList = service.getList("isnot");
			request.setAttribute("isnotList", isnotList);

			// ����������
			List<HashMap<String, String>> knsjb = service.getList("knsjb");
			request.setAttribute("knsjb", knsjb);

			// ǰ������
			List<HashMap<String, String>> qzxmList = commService
					.getQztjXmList(PjxtszModel.pjxtszModel);
			request.setAttribute("qzxmList", qzxmList);

			// ������Χ
			List<HashMap<String, String>> tjfwList = commService
					.getTjfw(PjxtszModel.pjxtszModel);
			request.setAttribute("tjfwList", tjfwList);

			// �γ�����
			List<HashMap<String, String>> kcxzList = service.getList("kcxz");
			request.setAttribute("kcxzList", kcxzList);

			// ���˷�ʽ
			List<HashMap<String, String>> khfsList = service.getList("khfs");
			request.setAttribute("khfsList", khfsList);
		}

	}
	
	public List<HashMap<String, String>> getTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmdm = request.getParameter("xmdm");
		PjpyTjszService service = new PjpyTjszService();
		PjpyTjszForm myForm = (PjpyTjszForm) form;
		// ==============��ȡ������������===========
		
		// ��ʾ��Ϣ
		boolean message = false;
		List<HashMap<String, String>> sEs = null;
        try {
        	sEs = service.getTjk(xmdm);// �������б�
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return sEs;
	}
	
	
	
	
	/**
	 * Ajax����
	 * ��ȡ��������
	 */
	public ActionForward getTjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTjszForm model = (PjpyTjszForm) form;
		PjpyTjszService service = new PjpyTjszService();	
		JSONArray tjList = JSONArray.fromObject(service.getXmtj(model.getXmdm()));
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(tjList);
		return null;
	}

}
