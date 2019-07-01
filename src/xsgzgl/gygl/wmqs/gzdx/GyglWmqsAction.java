package xsgzgl.gygl.wmqs.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * ��Ԣ����-������������             ���ݴ�ѧ���Թ���
 * 2012-03-21
 */
public class GyglWmqsAction extends BasicExtendAction{
	
	/**
	 * �������Ҹ���ά��
	 * @return
	 */
	@SystemLog(description="���ʹ�Ԣ����-��������-�������Ҹ���ά��-{doType}ά��XYDMS:{xydms}")
	public ActionForward qsgswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);// �û�����
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		GyglWmqsService service = new GyglWmqsService();
		RequestForm rForm = new RequestForm();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//�������õ��������Ҹ���
			boolean b=service.saveWmqsgs(myForm);
			if(b){
				request.setAttribute("back", "�����ɹ���");
			}else{
				request.setAttribute("back", "����ʧ�ܣ�");
			}
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] colList = new String[]{"ѧԺ","������","�����ٷֱȸ���","����������"};

		// =============== ִ�в�ѯ���� ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});
		
		rsArrList = service.getWmqsgswhInfoList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgswh.do");
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================
		
		//�жϱ�����ѧ������ý���
		if(rsArrList.size() == 0){
			request.setAttribute("yhInfo", "Ŀǰ��Ԣ���ҵ����ҳ�δά�����޷�ͨ�����ҳ�����ѧԺ�����������Ҹ�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		return mapping.findForward("qsgswhManage");
	}
	/**
	 * ������������
	 * @return
	 */
	@SystemLog(description="���ʹ�Ԣ����-��������-������������-����LDDM:{lddm}")
	public ActionForward qssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//�жϱ�����ѧ������ý���
		if(!"stu".equals((String)request.getSession().getAttribute("userType"))){
			request.setAttribute("yhInfo", "������ֻ�����ҳ����������");
			return new ActionForward("/yhInfo.do", false);
		}
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		GyglWmqsService service = new GyglWmqsService();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//����������Ϣ
			myForm.setSqr(getUser(request).getRealName());
			boolean b=service.saveWmqssqxx(myForm);
			if(b){
				request.setAttribute("back", "�����ɹ���");
			}else{
				request.setAttribute("back", "����ʧ�ܣ�");
			}
		}
		myForm.setSqr(getUser(request).getUserName());
//		myForm.setSqr("3063029018");
		HashMap<String,String> rs=service.getWmqssqxx(myForm,request);//��ȡ��������������Ϣ
		request.setAttribute("rs", rs);
		if(rs==null||rs.isEmpty()){
			request.setAttribute("yhInfo", "������ֻ�����ҳ����������");
			return new ActionForward("/yhInfo.do", false);
		}
		return mapping.findForward("qssqManage");
	}
	/**
	 * �����������
	 * @return
	 */
	@SystemLog(description="���ʹ�Ԣ����-��������-�����������-����PK:{primarykey_checkVal}")
	public ActionForward qsshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("path", "gzdx_gygl_wmqs_qssh.do");
		
		//�жϱ�����ѧ������ý���
		if("stu".equals((String)request.getSession().getAttribute("userType"))){
			request.setAttribute("yhInfo", "ѧ����Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		//ȷ���û�����
		User user = getUser(request);
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		/*boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("isFdy")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("isFdy").toString());
		}
		if(session.getAttribute("isBzr")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("isBzr").toString());
		}
		if(isFdy||isBzr){
			myForm.setUserType("fdy");
			myForm.setSqr(getUser(request).getUserName());//��ʱ����û���������Ȩ�޿���
			request.setAttribute("shtype", "fdy");
		}else{
			myForm.setUserType("xx");
			request.setAttribute("shtype", "xx");
		}
		*/
		GyglWmqsService service = new GyglWmqsService();
		
		String doType = request.getParameter("doType");
		
		//��˲���
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveWmqsshxx(myForm,request,user) ? "�����ɹ���" : "����ʧ�ܣ�" ;
			if(request.getAttribute("message")==null){
				request.setAttribute("message", message);
			}
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] cn = new String[]{"¥��","����","��������","����ƽ����","������","����Ա���״̬","ѧУ���״̬"};
//		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxnr","wxsj","jjcd","clzt","mycd"};

		// =============== ִ�в�ѯ���� ===========
		
		rsArrList = service.getWmqsshList(myForm,request,user);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//��ʾtitle
		return mapping.findForward("qsshManage");
	}
	/**
	 * �������ҹ���
	 * @return
	 */
	@SystemLog(description="���ʹ�Ԣ����-��������-�������ҹ���-ɾ��PK:{primarykey_checkVal}")
	public ActionForward qsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgl.do");
		//�жϱ�����ѧ������ý���
		if("stu".equals((String)request.getSession().getAttribute("userType"))){
			request.setAttribute("yhInfo", "ѧ����Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		GyglWmqsService service = new GyglWmqsService();
		
		String doType = request.getParameter("doType");
		
		// ɾ������
		if("save".equalsIgnoreCase(doType)){
			myForm.setUserType("xx");
			String message = service.saveWmqssqxx(myForm) ? "�����ɹ���" : "����ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}else if("del".equals(doType)){
			String message = service.delWmqsshxx(myForm) ? "�����ɹ���" : "����ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] cn = new String[]{"¥��","����","��������","����ƽ����","������","����Ա���״̬","ѧУ���״̬"};
//		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxnr","wxsj","jjcd","clzt","mycd"};

		// =============== ִ�в�ѯ���� ===========
		request.setAttribute("path", "gzdx_gygl_wmqs_qsgl.do");
		rsArrList = service.getWmqsglList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//��ʾtitle
		request.setAttribute("tableName", "view_xg_gygl_new_gzdx_wmqssq");
		return mapping.findForward("qsglManage");
	}
	
	/**
	 * ���������޸�
	 * @return
	 */
	@SystemLog(description="���ʹ�Ԣ����-��������-�������ҹ���-�޸�PK:{lddm}")
	public ActionForward wmqsglModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		myForm.setUserType("teacher");
		GyglWmqsService service = new GyglWmqsService();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){
			myForm.setSqr(getUser(request).getRealName());
			String message = service.saveWmqssqxx(myForm) ? "�����ɹ���" : "����ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}
		
		Map<String, String> rs = service.getWmqssqxx(myForm,request);
		request.setAttribute("rs", rs);
		request.setAttribute("ldList", service.getLdxxList(request));
		request.setAttribute("doType", doType);
		return mapping.findForward("wmqsglModi");
	}
	
	/**
	 * ��ȡ����������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWmqsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglWmqsForm myForm = (GyglWmqsForm) form;
		myForm.setUserType("teacher");
		GyglWmqsService service = new GyglWmqsService();

		Map<String, String> map = service.getWmqssqxx(myForm,request);
		List<HashMap<String,String>> xsList=(List<HashMap<String,String>>)request.getAttribute("xsList");
		if(xsList!=null&&xsList.size()>0){
			StringBuffer xsHtml=new StringBuffer();
			xsHtml.append("<table width='100%'>");
			xsHtml.append("<tr>");
			xsHtml.append("<th>ѧ��</th>");
			xsHtml.append("<th>����</th>");
			xsHtml.append("<th>ѧԺ</th>");
			xsHtml.append("<th>רҵ</th>");
			xsHtml.append("<th>�༶</th>");
			xsHtml.append("</tr>");
			HashMap<String,String> r;
			for(int i=0;i<xsList.size();i++){
				r=xsList.get(i);
				xsHtml.append("<tr><td>");
				xsHtml.append(r.get("xh"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("xm"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("xymc"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("zymc"));
				xsHtml.append("</td><td>");
				xsHtml.append(r.get("bjmc"));
				xsHtml.append("</td></tr>");
			}
			xsHtml.append("</table>");
			map.put("xsHtml", xsHtml.toString());
		}
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * ��ȡ�������Ұٷֱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWmqsbfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm=Base.chgNull(request.getParameter("csdm"), "", 0);
		GyglWmqsService service = new GyglWmqsService();

		List<HashMap<String,String>> list=service.getWmqsbfb(new String[]{csdm});
		if(list!=null&&list.size()>0){
			String json = JSONObject.fromObject(list.get(0)).toString();	
			
			response.setCharacterEncoding("gbk");
			response.getWriter().write(json);
		}
		return null;
	}
	
	/**
	 * �����������Ұٷֱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��������-�������Ҹ���ά��-�����������Ұٷֱ�")
	public ActionForward saveWmqsbfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csz=request.getParameter("csz");
		GyglWmqsService service = new GyglWmqsService();
		boolean b = service.saveWmqsbfb(csz);
//		String json = JSONObject.fromObject(map).toString();	
		String msg;
		if(b){
			msg="�޸ĳɹ���";
		}else{
			msg="�޸�ʧ�ܣ�";
		}
		response.setCharacterEncoding("gbk");
		response.getWriter().write(msg);
		return null;
	}
	
}
