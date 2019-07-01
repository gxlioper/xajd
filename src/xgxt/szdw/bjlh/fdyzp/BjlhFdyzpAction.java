package xgxt.szdw.bjlh.fdyzp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

public class BjlhFdyzpAction extends BasicExtendAction {

	public ActionForward fdyzpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		//�жϱ�����У���û�����ý���
		if(!"xx".equals(user.getUserType()) && !"admin".equals(user.getUserType())){
			request.setAttribute("yhInfo", "��У���û���Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		request.setAttribute("path", "bjlh_fdykh_fdyzp.do");
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		String doType = request.getParameter("doType");
		String[] checkVal = request.getParameterValues("checkVal");
		String pkValue = request.getParameter("pkValue");
		Map<String, String> rs1 = new HashMap<String, String>();
		//ɾ��
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.deleteAllById(pkValue);
			if (flag) {
				request.setAttribute("message", "ɾ���ɹ���");
			} else {
				request.setAttribute("message", "ɾ��ʧ�ܣ�");
			}
		}
		//���� 
		else if ("qy".equalsIgnoreCase(doType)) {
			boolean flag = service.changeSfqy(pkValue,"��");
			if (flag) {
				request.setAttribute("message", "���óɹ���");
			} else {
				request.setAttribute("message", "����ʧ�ܣ�");
			}
		} 
		//ͣ��
		else if ("ty".equalsIgnoreCase(doType)) {
			boolean flag = service.changeSfqy(pkValue,"��");
			if (flag) {
				request.setAttribute("message", "ͣ�óɹ���");
			} else {
				request.setAttribute("message", "ͣ��ʧ�ܣ�");
			}
		} 
		//����
		else if ("copy".equalsIgnoreCase(doType)){
			boolean flag = service.copyFdyzpById(myForm,pkValue);
			if (flag) {
				request.setAttribute("message", "���Ƴɹ���");
			} else {
				request.setAttribute("message", "����ʧ�ܣ�");
			}
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("fdyzp"));
		request.setAttribute("rs", service.getTableList(myForm, request));
		request.setAttribute("rs1", rs1);
		// write��titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzp.do");

		
		request.setAttribute("realTable", "xg_gygl_new_cwxxb"); // �����
		request.setAttribute("tableName", "xg_gygl_new_cwxxb"); // ������ͼ

		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("addxnList", service.getAddXnList());
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("manage");
	}

	public ActionForward fdyzpEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		String doType = request.getParameter("doType");
		HashMap<String,String> rs = new HashMap<String, String>();
		//����
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveFdyzpbAndFdyzpxmb(myForm);
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
			rs = service.getFdyzpbById(pkValue);	
			request.setAttribute("xmList", service.getXmListByZpbId(pkValue));
			myForm.setZpbid(pkValue);
		}
		
		request.setAttribute("addxnList", service.getAddXnList());
		request.setAttribute("rs", rs);
		// write��titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzp.do");
		request.setAttribute("doType", doType);
		return mapping.findForward("edit");
	}

	public ActionForward fdyzpFdylr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		String doType = request.getParameter("doType");
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		User user = getUser(request);
		//�жϱ����ɸ���Ա����ý���
		if("false".equals(user.getFdyQx())){
			request.setAttribute("yhInfo", "�Ǹ���Ա��Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(mrsz == null ||mrsz.isEmpty()){
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(mrsz.get("zpbid") == null || "".equalsIgnoreCase(mrsz.get("zpbid"))){
				request.setAttribute("yhInfo", "��������������д���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
			
			//�ж� ��ǰѧ���Ƿ����� ��������
			if("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj")) 
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))){
				request.setAttribute("yhInfo", "�Ǹ���Ա����ʱ��Σ��޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}			
		}		
		
		
		//����
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveFdyzpxxb(myForm,"��");
			if(flag){
				request.setAttribute("message", "����ɹ���");
			}else{
				request.setAttribute("message", "����ʧ�ܣ�");
			}
		//�ύ
		}else if("tj".equalsIgnoreCase(doType)){
			boolean flag = service.saveFdyzpxxb(myForm,"��");
			if(flag){
				request.setAttribute("message", "�ύ�ɹ���");
			}else{
				request.setAttribute("message", "�ύʧ�ܣ�");
			}
		}
		
		HashMap<String,String> rs = service.getFdyxx(user.getUserName(),mrsz.get("zpbid"));
		List<HashMap<String,String>> xmList = service.getFdyzpxxb(mrsz.get("zpbid"),user.getUserName());
				
		rs.put("sftj", xmList.get(0).get("sftj"));
		request.setAttribute("rs", rs);
		
		request.setAttribute("xmList", xmList);
		// write��titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzplr.do");
		
		request.setAttribute("path", "bjlh_fdykh_fdyzplr.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		return mapping.findForward("fdylr");
	}
	
	//����Ա������ѯ
	public ActionForward fdyzpQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		//�жϱ�����ѧԺ��ѧУ�򸨵�Ա�û�����ý���
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "�����μ�ѧ���û���Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdyzpService service = new BjlhFdyzpService();
		BjlhFdyzpForm myForm = (BjlhFdyzpForm) form;
		HashMap<String, String> mrsz = service.getMrsz();
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_fdyzpcx.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
			myForm.getSearchModel().setSearch_tj_sf(new String[]{"��"});
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("fdyzpcx"));
		request.setAttribute("rs", service.getQueryTableList(myForm, request));
		// write��titile
		setWriteAbleAndTitle(request, "bjlh_fdykh_fdyzpcx.do");
		
		return mapping.findForward("query");
	}
	
	public ActionForward fdyzpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BjlhFdyzpService service = new BjlhFdyzpService();
		
		String zpbid=request.getParameter("zpbid");
		String yhm= request.getParameter("yhm");
		
		request.setAttribute("rs", service.getFdyxx(yhm,zpbid));
		request.setAttribute("xmList", service.getFdyzpxxb(zpbid,yhm));
		
		return mapping.findForward("view");
	}
	
	//����Ա������ѯ
	public ActionForward fdyzpPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdyzpService service = new BjlhFdyzpService();
		String zpbid=request.getParameter("zpbid");
		String yhm= request.getParameter("yhm");
		
		request.setAttribute("rs", service.getFdyxx(yhm,zpbid));
		request.setAttribute("xmList", service.getFdyzpxxb(zpbid,yhm));
		
		return mapping.findForward("print");
	}
	
}
