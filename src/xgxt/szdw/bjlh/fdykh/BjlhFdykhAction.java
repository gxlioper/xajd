package xgxt.szdw.bjlh.fdykh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.utils.FormModleCommon;

import common.Globals;

public class BjlhFdykhAction extends BasicExtendAction {
	
	/**
	 *=============ͳ��������=============================
	 */
	public static final String B_BJ = "BJ"; //���༶
	public static final String B_RS = "RS"; //������
	public static final String B_ZZ = "ZZ"; //��ְ��
	/**
	 *=============ͳ��������=============================
	 */
	
	/**
	 * ����Ա����-���˶�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhKhdxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		//�жϱ����ɸ���Ա����ý���
		if("stu".equals(user.getUserType())|| "true".equalsIgnoreCase(user.getFdyQx()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "����Ա���������û���Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;

		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("khdxxz"));
		request.setAttribute("rs", service.getTableList(myForm, request));

		// write��title
		setWriteAbleAndTitle(request, "bjlh_fdykh_khdxsz.do");

		request.setAttribute("realTable", "xg_gygl_new_cwxxb"); // �����
		request.setAttribute("tableName", "xg_gygl_new_cwxxb"); // ������ͼ

		request.setAttribute("path", "bjlh_fdykh_khdxsz.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("khdxsz");
	}

	/**
	 * ����Ա����-���˶������÷����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhKhdxszEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		//�����ѧԺ�û������ò�ѯ����
		User user = getUser(request);
		if("xy".equals(user.getUserType())){
			myForm.setBmdm(user.getUserDep());
			request.setAttribute("disabled", "true");
		}
		String doType = request.getParameter("doType");
		// ���ó�ʼ��
		if ("sz".equalsIgnoreCase(doType)) {
			
			request.getSession().setAttribute("khzgh", myForm.getCheckVal());
			// ��������
		} else if ("save".equalsIgnoreCase(doType)) {
			String[] yhs = request.getParameterValues("primarykey_cbv");
			String[] khzgh = (String[]) request.getSession().getAttribute(
					"khzgh");

			// ���渨��Ա��������
			String message = service.saveFdyKh(yhs, khzgh) ? "���óɹ���" : "����ʧ�ܣ�";

			// ��session��ȥ�������õĿ���ְ����
			request.getSession().removeAttribute("khzgh");
			request.setAttribute("message", message);

		}

		try {
			request.setAttribute("topTr", service.getTopTr("khdxxzyh"));
			request.setAttribute("rs", service.queryYh(myForm));

			FormModleCommon.requestSetList(new String[] { "yjbm" }, request);
			request.setAttribute("pageSize", myForm.getPages().getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("szedit");
	}

	/**
	 * ����Ա���˲��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "����˼������-����Ա����-����Ա���˱����-����XN:{xn},KHBMC:{khbmc},PFDX:{pfdx};�޸�KHBID:{khbid},KHBMC:{khbmc}; ɾ��KHBID:{khbid}")
	public ActionForward fdykhKhcpbgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception { 
		User user = getUser(request);
		//�жϱ����ɸ���Ա����ý���
		if(!"xx".equals(user.getUserType()) && !"admin".equals(user.getUserType())){
			request.setAttribute("yhInfo", "��У���û���Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		// ����
		String doType = request.getParameter("doType");
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();

		if (!Base.isNull(doType)) {
			String message = "��������";
			if ("add".equals(doType)) {// ����
				message = service.saveKhbInfo(myForm, "add");
			} else if ("update".equals(doType)) {// �޸�
				message = service.saveKhbInfo(myForm, "update");
			} else if ("del".equalsIgnoreCase(doType)) {// ɾ��
				message = service.deleteKhbInfo(myForm);
			} else if ("copy".equals(doType)) {// ����
				message = service.copyKhbInfo(myForm) ? "���Ƴɹ���" : myForm.getXn() + "����ж�Ӧ�����ֶ��󿼺˱��Ѵ��ڣ�";
			} else if ("sfqy".equals(doType)) {// �Ƿ�����
				message = service.sfqyKhb(myForm);
			}
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getFdykhList(myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());

		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("fdykh"));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("pfdxList", service.getPfdxList());

		return mapping.findForward("fdykhKhcpbgl");
	}

	/**
	 * ����Ա����-ѧ�����˲���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXskhcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��󸨵�Ա
			lx="����Ա";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��������
			lx="������";
		}
		
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("xs");

		String yhm = user.getUserName();
		List<HashMap<String,String>> fdylist = service.getYhmxx(yhm,lx);
		String dqsj = service.getDate();
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "���޲���������д���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
		}
		//�жϱ����ɸ���Ա����ý���
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "��ѧ���û��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(fdylist.size()== 0){
				request.setAttribute("yhInfo", "��û�ж�Ӧ�ĸ���Ա���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);				
			}else if(fdylist.size()== 1){
				return new ActionForward("/bjlh_fdykh.do?method=fdykhXskhpf&khzgh="+fdylist.get(0).get("zgh"), false);		
			}else{
				if (QUERY.equals(myForm.getType())){
					myForm.setKhbid(mrsz.get("khbid"));
					myForm.setYhm(yhm);
					List<HashMap<String,String>> khFdyList=service.getKhFdyList(myForm);
					JSONArray dataList = JSONArray.fromObject(khFdyList);
					response.getWriter().print(dataList);
					return null;
				}
				String path = "bjlh_fdykh_xskhcp.do";
				request.setAttribute("path", path);
				FormModleCommon.commonRequestSet(request);
				
				return mapping.findForward("khFdyList");
			}
		}
		
/*		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "���޲���������д���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
			
			// �ж� ��ǰѧ���Ƿ����� ��������
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz
							.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz
							.get("khjssj"))) {
				request.setAttribute("yhInfo", "�Ǹ���Ա����ʱ��Σ��޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
		}

		String doType = request.getParameter("doType");
		myForm.setKhbid(mrsz.get("khbid"));
		myForm.setYhm(yhm);
		myForm.setFdyid(rs1.get("zgh"));
		HashMap<String, String> rs = service.getKhbxx(myForm);
		
		if (doType != null && "save".equalsIgnoreCase(doType)) {
			String[] xmid = request.getParameterValues("xmid");
			String[] df = request.getParameterValues("df");
			if (service.saveFdykhpfb(xmid, df, myForm)) {
				request.setAttribute("message", "����ɹ�");
			} else {
				request.setAttribute("message", "����ʧ��");
			}
		}
		// write��title
		setWriteAbleAndTitle(request, "bjlh_fdykh_xskhcp.do");
		request.setAttribute("rs", rs);// ���˱���Ϣ
		request.setAttribute("rs1", rs1);// ���˱���Ϣ
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// ��ȡ��������һ��ָ��Ŀ��˱���Ŀ�б�
		//����ְҵ����ѧԺ���Ի�
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
			request.setAttribute("khbsfzd", service.getSfcgkhsj());//����ѧԺ-�ж��Ƿ񳬹�����ʱ��
		}else{
			request.setAttribute("khbsfzd", service.getKhbSfzd(myForm));
		}*/
		
	}
	
	/**
	 * ����Ա����-ѧ�����˲���-��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXskhpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("xs");

		String yhm = user.getUserName();
//		List<HashMap<String,String>> fdylist = service.getYhmxx(yhm);
		String zgh = (String) request.getParameter("khzgh");
		String fh = (String) request.getParameter("flag");//�ж��Ƿ��з��ذ�ť
		request.setAttribute("fh", fh);
		request.setAttribute("khzgh", zgh);
		HashMap<String,String> fdyxx = service.getRsInfo("view_fdyxx", "zgh", zgh, new String[]{"xm","bmmc"});
		
		String dqsj = service.getDate();

		//�жϱ����ɸ���Ա����ý���
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "��ѧ���û��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(zgh== null){
				request.setAttribute("yhInfo", "��û�ж�Ӧ�ĸ���Ա���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);				
			}
		}
		
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "���޲���������д���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
			
			// �ж� ��ǰѧ���Ƿ����� ��������
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))
					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz
							.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz
							.get("khjssj"))) {
				request.setAttribute("yhInfo", "�Ǹ���Ա����ʱ��Σ��޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
		}

		String doType = request.getParameter("doType");
		myForm.setKhbid(mrsz.get("khbid"));
		myForm.setYhm(yhm);
		myForm.setFdyid(zgh);
		HashMap<String, String> rs = service.getKhbxx(myForm);
		
		if (doType != null && "save".equalsIgnoreCase(doType)) {
			String[] xmid = request.getParameterValues("xmid");
			String[] df = request.getParameterValues("df");
			boolean flag= true;
			if(!service.checkFdykhpf(xmid, df)){
				flag= false;
				request.setAttribute("message", "�����ֵ������Ҫ��");
			}
			if(flag){
				if ( service.saveFdykhpfb(xmid, df, myForm)) {
					request.setAttribute("message", "����ɹ�");
				} else {
					request.setAttribute("message", "����ʧ��");
				}
			}
		}
		// write��title
		setWriteAbleAndTitle(request, "bjlh_fdykh_xskhcp.do");
		request.setAttribute("rs", rs);// ���˱���Ϣ
		request.setAttribute("rs1", fdyxx);// ���˱���Ϣ
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// ��ȡ��������һ��ָ��Ŀ��˱���Ŀ�б�
		//����ְҵ����ѧԺ���Ի�
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
			request.setAttribute("khbsfzd", service.getSfcgkhsj());//����ѧԺ-�ж��Ƿ񳬹�����ʱ��
		}else{
			request.setAttribute("khbsfzd", service.getKhbSfzd(myForm));
		}
		
		return mapping.findForward("fdykhXskhpf");
	}

	/**
	 * ����Ա����-��ʦ���˲���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhJskhcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_jskhcp.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
		}
		String dqsj = service.getDate();
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			request.setAttribute("sfxspfan", false);//�Ƿ���ʾ���ְ�ť
		} else {
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))) {
				request.setAttribute("yhInfo", "����Ա����δ���������������ٽ��иò�����");
				request.setAttribute("sfxspfan", false);//�Ƿ���ʾ���ְ�ť
			}
			if (Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))) {
				request.setAttribute("yhInfo", "�Ǹ���Ա����ʱ��Σ��޷����иò�����");
				request.setAttribute("sfxspfan", false);//�Ƿ���ʾ���ְ�ť
			}
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
				if (Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khlrjzsj"))) {
					request.setAttribute("yhInfo", "�ѳ�������Ա����¼���ֹʱ�䣬�޷����иò�����");
					request.setAttribute("sfxspfan", false);//�Ƿ���ʾ���ְ�ť
				}
			}
		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr("jskhcp"));
		request.setAttribute("rs", service.getFdykhcpList(user.getUserName(),myForm, request));
		System.out.println(service.getFdykhcpList(user.getUserName(),myForm, request).size());
		request.setAttribute("path", "bjlh_fdykh_jskhcp.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		setWriteAbleAndTitle(request, "bjlh_fdykh_jskhcp.do");

		return mapping.findForward("fdykhJskhcp");
	}
	/**
	 * ����Ա����-��ʦ���˲���-��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhJskhpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		String khbid = request.getParameter("khbid");
		String dqsj = service.getDate();
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		} else {
			if(mrsz.get("khbid") == null || "".equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "���޲���������д���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}else if(!khbid.equalsIgnoreCase(mrsz.get("khbid"))){
				request.setAttribute("yhInfo", "�ǵ�ǰѧ��������޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
			
			// �ж� ��ǰѧ���Ƿ����� ��������
			if ("0".equalsIgnoreCase(mrsz.get("khsfqd"))) {
				request.setAttribute("yhInfo", "����Ա����δ���������������ٽ��иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
			
			if (Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj"))
					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))) {
				request.setAttribute("yhInfo", "�Ǹ���Ա����ʱ��Σ��޷����иò�����");
				return new ActionForward("/yhInfo.do", false);
			}
			//--����ѧԺ���Ի�����--���ֿɷ��޸��Կ���¼���ֹʱ��Ϊ׼
			if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
				if (Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khlrjzsj"))) {
					request.setAttribute("yhInfo", "�ѳ�������Ա����¼���ֹʱ�䣬�޷����иò�����");
					return new ActionForward("/yhInfo.do", false);
				}
			}
		}
		
		String fdyid = request.getParameter("fdyid");
		String xm = request.getParameter("xm");
		String bmmc = request.getParameter("bmmc");
		String doType= request.getParameter("doType");
		String yhm = user.getUserName();
		
		myForm.setYhm(yhm);
		myForm.setKhbid(khbid);
		myForm.setFdyid(fdyid);
		if(doType!=null && "save".equalsIgnoreCase(doType)){
			String[] xmid = request.getParameterValues("xmid");
			String[] df = request.getParameterValues("df");
			if (service.saveFdykhpfb(xmid, df, myForm)) {
				request.setAttribute("message", "����ɹ�");
			} else {
				request.setAttribute("message", "����ʧ��");
			}
		}
		HashMap<String, String> rs = service.getKhbxx(myForm);
		rs.put("bmmc", bmmc);
		rs.put("xm", xm);
		rs.put("fdyid", fdyid);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// ��ȡ��������һ��ָ��Ŀ��˱���Ŀ�б�
		return mapping.findForward("fdykhJskhpf");
	}

	/**
	 * ����Ա����-���˲���ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhKhcptj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		//�жϱ�����ѧԺ��ѧУ�򸨵�Ա�û�����ý���
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "�����μ�ѧ���û���Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		} 
		
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_khcptj.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
		}
		// ѧУ����
		String xxdm = Base.xxdm;
		List<String[]> rs = null;
		if(xxdm.equals(Globals.XXDM_CZZYJSXY)){
			rs = service.getFdyKhTjInfoforCzxy(myForm,request);
			request.setAttribute("czxygxh", "yes");
		}else{
			rs = service.getFdyKhTjInfo(myForm,request);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("khbtj"));

		request.setAttribute("searchTj", myForm.getSearchModel());
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_szdw_fdykhbzb");
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj.do");
		return mapping.findForward("fdykhKhcptj");
	}

	/**
	 * 
	 * @����:����Ա����<b>�°�</b>
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-27 ����10:02:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward fdykhKhcptjNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		//�жϱ�����ѧԺ��ѧУ�򸨵�Ա�û�����ý���
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "�����μ�ѧ���û���Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		BjlhFdykhNewService newService = new BjlhFdykhNewService();
		HashMap<String, String> mrsz = service.getMrsz("pfxz");
		if (mrsz == null || mrsz.isEmpty()) {
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		} 

		if (QUERY.equalsIgnoreCase(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			
			List<HashMap<String,String>> resultList = null;
			String tylx = myForm.getTjlx();
			if(StringUtils.isNotBlank(tylx) && StringUtils.equals(B_BJ, tylx)){
				resultList = newService.getFdyKhxxByBJ(myForm, user);
			}else if(StringUtils.isNotBlank(tylx) && StringUtils.equals(B_RS, tylx)){
				resultList = newService.getFdyKhxxByRS(myForm, user);
			}else if(StringUtils.isNotBlank(tylx) && StringUtils.equals(B_ZZ, tylx)){
				resultList = newService.getFdyKhxxByZZ(myForm, user);
			}
		
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_khcptj_new.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
			request.setAttribute("searchTj", myForm.getSearchModel());
		}
		
		//List<String[]> rs = null;
		//rs = service.getFdyKhTjInfo(myForm,request);
		//request.setAttribute("rs", rs);
		//request.setAttribute("topTr", service.getTopTr("khbtj"));
		//request.setAttribute("searchTj", myForm.getSearchModel());
		// ----------------- ��������� ------------------------
		//request.setAttribute("realTable", "xg_szdw_fdykhbzb");
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj_new.do"); //�������� �ɶ���д
		return mapping.findForward("fdykhKhcptjnew");
	}
	
	/**
	 * 
	 * @����:������ϸ��ѯ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-18 ����04:53:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward fdykhKhMxNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		BjlhFdykhNewService newService = new BjlhFdykhNewService();
		Object[] mxsj = null; //��ϸ����
		String pk = request.getParameter("pk");
		String tjlx = null; //��ѯ�������� RS BJ ZZ
		if(!StringUtil.isNull(pk)){
			String[] splitVals = xgxt.utils.String.StringUtils.splitStr(pk, "!!one!!");
			tjlx = splitVals[0];
			
			if(xgxt.utils.String.StringUtils.isEqual(tjlx, B_RS)){ //�������� Example: RS!!one!!2013-2014!!one!!100100
				String xn = splitVals[1]; //ѧ��
				String zgh = splitVals[2]; //ְ����
				myForm.setTjlx(B_RS);
				myForm.setXn(xn);
				myForm.setFdyid(zgh);
			}else if(xgxt.utils.String.StringUtils.isEqual(tjlx, B_BJ)){
				String xn = splitVals[1]; //ѧ��
				String zgh = splitVals[3]; //ְ����
				String bjdm = splitVals[2]; //�༶����
				myForm.setTjlx(B_BJ);
				myForm.setXn(xn);
				myForm.setFdyid(zgh);
				myForm.setBjdm(bjdm);
			}else if(xgxt.utils.String.StringUtils.isEqual(tjlx, B_ZZ)){
				String xn = splitVals[1]; //ѧ��
				String zgh = splitVals[3]; //ְ����
				String zzlbmc = splitVals[2]; //�༶����
				myForm.setTjlx(B_ZZ);
				myForm.setXn(xn);
				myForm.setFdyid(zgh);
				myForm.setZzlbmc(zzlbmc);
			}
			
			mxsj = newService.getFdyKhMx(myForm, user);
			
			request.setAttribute("xsmxPjf", mxsj[0]);
			request.setAttribute("tmmxZf", mxsj[1]);
			request.setAttribute("xsmxList", mxsj[2]);
			request.setAttribute("tmmxList", mxsj[3]);
			
		}
		request.setAttribute("tjlx", tjlx);
		return mapping.findForward("fdykhKhMxNew");
	}
	
	/**
	 * 
	 * @����:������ϸ�б��ѯ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-20 ����09:24:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward fdykhKhMxDetailNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		User user = getUser(request);
		BjlhFdykhNewService newService = new BjlhFdykhNewService();

		Object[] data = newService.getFdyKhxxMxDetail(myForm, user);
		
		//��ѯ
		List<HashMap<String,String>> resultList = (List<HashMap<String, String>>) data[0];
		JSONArray dataList = JSONArray.fromObject(resultList);
		dataList.put(data[1]);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-2-21 ����10:42:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward exportDataNew (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm model = (BjlhFdykhForm) form;
		BjlhFdykhNewService service = new BjlhFdykhNewService();
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList =  null;
		String dcclbh = request.getParameter("dcclbh");
		if(xgxt.utils.String.StringUtils.isEqual("bjlh_fdykh_new_RS.do", dcclbh)){
			resultList = service.getFdyKhxxByRSAll(model, user);//��ѯ�����м�¼������ҳ
		}else if(xgxt.utils.String.StringUtils.isEqual("bjlh_fdykh_new_BJ.do", dcclbh)){
			resultList = service.getFdyKhxxByBJAll(model, user);//��ѯ�����м�¼������ҳ
		}else if(xgxt.utils.String.StringUtils.isEqual("bjlh_fdykh_new_ZZ.do", dcclbh)){
			resultList = service.getFdyKhxxByZZAll(model, user);//��ѯ�����м�¼������ҳ
		}
		
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(dcclbh);//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * ����Ա������Ŀά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();

		String yhInfo = service.checkKhbQx(myForm);
		if (!"".equals(yhInfo)) {
			request.setAttribute("message", yhInfo+"������Ŀ������ά����");
		    return new ActionForward("/bjlh_fdykh_khcpbgl.do", false);
		}

		if ("del".equals(doType)) {// ɾ��
			String msg = service.deleteKhbXmxx(myForm) ? "�����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", msg);
		}
		request.setAttribute("xmxxList", service.getFdykhXmList(myForm));
		// request.setAttribute("xxList", service.getXxlist(myForm, "st"));
		request.setAttribute("doType", doType);
		request.setAttribute("topTr", service.getTopTr("khbxm"));
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcpbgl.do");
		return mapping.findForward("fdykhXmwh");
	}

	/**
	 * �����ʾ����������ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhXmwhOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("add".equals(doType)) {
			rs.put("khbid", myForm.getKhbid());
		} else if ("add_save".equals(doType)) {
			String msg = service.saveKhbXmxx(myForm, "add");
			request.setAttribute("back", msg);
			// ��ӳɹ���Ȼ������ӣ��������
			myForm.setXmid("");
			doType = "add";
			// rs=service.getCpwjStxxOne(myForm);
		} else if ("update".equals(doType)) {
			rs = service.getKhbXmxxOne(myForm);
		} else if ("update_save".equals(doType)) {
			String msg = service.saveKhbXmxx(myForm, "update");
			request.setAttribute("back", msg);
			rs = service.getKhbXmxxOne(myForm);
		}
		// else if("del".equals(doType)){//ɾ��
		// String msg=service.deleteKhbXmxx(myForm)?"�����ɹ���":"����ʧ�ܣ�";
		// request.setAttribute("back", msg);
		// }
		String fzqj = rs.get("fzqj");
		if(!StringUtils.isEmpty(fzqj)){
			
			if(fzqj.split("-").length == 1){
				rs.put("fzDown", fzqj.split("-")[0]);
			}else if(fzqj.split("-").length == 2){
				if(StringUtils.isBlank(fzqj.split("-")[0])){
					rs.put("fzDown", fzqj.split("-")[1]);
				}else{
					rs.put("fzDown", fzqj.split("-")[0]);
					rs.put("fzUp", fzqj.split("-")[1]);
				}
			}
		}
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		return mapping.findForward("fdykhXmwhOne");
	}

	/**
	 * ���˱�-����鿴
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdykhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();

		String doType = request.getParameter("doType");
		if ("save".equals(doType)) {
			// String msg=service.saveCpwjDa(request, myForm)?"����ɹ���":"����ʧ�ܣ�";
			// request.setAttribute("back", msg);
		}

		request.setAttribute("rs", service.getKhbxx(myForm));// ���˱���Ϣ
		// List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("xmList", service.getKhbXmYjzbColNum(myForm));// ��ȡ��������һ��ָ��Ŀ��˱���Ŀ�б�

		return mapping.findForward("fdykhXmView");
	}
	
	
	/** 
	 * @����:(��ȡ����Ա���˱����Ŀ��ƽ�����б�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-9 ����04:58:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public ActionForward pfmxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		request.setAttribute("xmList", service.getFdykhmx(myForm));// ��ȡ��������һ��ָ��ĸ���Ա������ϸ
		return mapping.findForward("pfmxCk");
		
	}

	/**
	 * ����Ա�ɼ�ͳ��ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String xn = request.getParameter("xn");
		String zgh = request.getParameter("fdyid");
		myForm.setXn(xn);
		myForm.setZgh(zgh);
		HashMap<String, String> fdycjTjxx = service.fdycjTjWh(myForm);
		request.setAttribute("rs", fdycjTjxx);
		// ��ȡ�ȼ������б��
		request.setAttribute("khdjlist", service.getKhDjList(request));
		request.setAttribute("jtdjlist", service.getJtDjList(request));
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj.do");
		return mapping.findForward("fdycjTjWh");
	}
	
	/**
	 * ����Ա�ɼ�ͳ�Ʋ鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String xn = request.getParameter("xn");
		String zgh = request.getParameter("fdyid");
		myForm.setXn(xn);
		myForm.setZgh(zgh);
		HashMap<String, String> fdycjTjxx = service.fdycjTjWh(myForm);
		request.setAttribute("rs", fdycjTjxx);
		// ��ȡ�ȼ������б��
		request.setAttribute("khdjlist", service.getKhDjList(request));
		request.setAttribute("jtdjlist", service.getJtDjList(request));
		setWriteAbleAndTitle(request, "bjlh_fdykh_khcptj.do");
		return mapping.findForward("fdycjTjCk");
	}
	
	/**
	 * ����Ա�ɼ�ͳ��ά������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjWhBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		String message = "";
		boolean flag = false;

		String xn = request.getParameter("xn");
		String zgh = request.getParameter("zgh");
		String khdj = service.unicode2Gbk(request.getParameter("khdj"));
		String jtdj = service.unicode2Gbk(request.getParameter("jtdj"));
		String bz = service.unicode2Gbk(request.getParameter("bz"));

		myForm.setXn(xn);
		myForm.setZgh(zgh);
		myForm.setKhdj(khdj);
		myForm.setJtdj(jtdj);
		myForm.setBz(bz);

		flag = service.fdycjTjWhBc(myForm);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ����Ա�ɼ�ͳ�Ƶ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdycjTjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BjlhFdykhForm myForm = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expFdycjTj(myForm, response.getOutputStream(),request);
		return null;
	}
	
	public ActionForward exportData (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdykhForm model = (BjlhFdykhForm) form;
		BjlhFdykhService service = new BjlhFdykhService();
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,request);//��ѯ�����м�¼������ҳ
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
