package xgxt.szdw.bjlh.fdycpwj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhForm;
import xgxt.szdw.bjlh.fdykh.BjlhFdykhService;
import xgxt.utils.FormModleCommon;

public class BjlhFdycpwjAction extends BasicExtendAction{

	/**
	 * �����ʾ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglManage(ActionMapping mapping, ActionForm form,
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
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		myForm.setFbr(getUser(request).getUserName());
		
		if(!Base.isNull(doType)){
			String message="��������";
			if("add".equals(doType)){//����
				message=service.saveCpwjInfo(myForm,"add")?"�����ɹ���":"����ʧ�ܣ�";
			}else if("update".equals(doType)){//�޸�
				message=service.saveCpwjInfo(myForm,"update")?"�����ɹ���":"����ʧ�ܣ�";
			}else if("del".equalsIgnoreCase(doType)){//ɾ��
				message=service.checkCpwjQx(myForm);
				if("".equals(message)){
					message = service.deleteCpwjInfo(myForm); 
				}else{
					message+="����ɾ����";
				}
			}else if("copy".equals(doType)){//����
				message=service.copyCpwjInfo(myForm)?"���Ƴɹ���":"����ʧ�ܣ�";
			}else if("sfqy".equals(doType)){//�Ƿ�����
				message=service.sfqyCpwj(myForm);
			}
			request.setAttribute("message", message);
		}
		
		List<String[]> rs = service.getCpwjList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjgl.do");
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("cpwjgl"));
		request.setAttribute("addxnList", service.getAddXnList());
		
		return mapping.findForward("manage");
	}
	
	/**
	 * �����ʾ�������༭
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String doType=request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setFbr(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String,String> rs=new HashMap<String,String>();;
		if("add".equals(doType)){
			rs.put("sfqy", "��");
		}else if("add_save".equals(doType)){
			String msg=service.saveCpwjInfo(myForm,"add")?"�����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
		}else if("update".equals(doType)){
			rs=service.getCpwjOne(myForm);
		}else if("update_save".equals(doType)){
			String msg=service.saveCpwjInfo(myForm,"update")?"�����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		request.setAttribute("addxnList", service.getAddXnList());
		return mapping.findForward("edit");
	}
	
	/**
	 * �����ʾ����������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglStwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String doType=request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setFbr(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();

		String yhInfo=service.checkCpwjQx(myForm);
		if(!"".equals(yhInfo)){
			request.setAttribute("yhInfo", yhInfo+"���ⲻ����ά����");
		    return new ActionForward("/yhInfo.do", false);
		}

		HashMap<String,String> rs=new HashMap<String,String>();;
		if("del".equals(doType)){//ɾ��
			String msg=service.saveCpwjStxx(myForm,"delete")?"�����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
			rs.put("wjid", myForm.getWjid());
			rs.put("sfqy", "��");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("stxxList", service.getCpwjStList(myForm));
//		request.setAttribute("xxList", service.getXxlist(myForm, "st"));
		request.setAttribute("doType", doType);
		request.setAttribute("topTr", service.getTopTr("cpwjst"));
		return mapping.findForward("stwh");
	}
	
	/**
	 * �����ʾ����������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglStwhOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String doType=request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setFbr(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String,String> rs=new HashMap<String,String>();;
		if("add".equals(doType)){
			rs.put("wjid", myForm.getWjid());
			rs.put("sfqy", "��");
		}else if("add_save".equals(doType)){
			String msg=service.saveCpwjStxx(myForm,"add")?"�����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
			//��ӳɹ���Ȼ������ӣ��������
			myForm.setStid("");
			doType="add";
//			rs=service.getCpwjStxxOne(myForm);
		}else if("update".equals(doType)){
			rs=service.getCpwjStxxOne(myForm);
		}else if("update_save".equals(doType)){
			String msg=service.saveCpwjStxx(myForm,"update")?"�����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
			rs=service.getCpwjStxxOne(myForm);
		}else if("del".equals(doType)){//ɾ��
			String msg=service.saveCpwjStxx(myForm,"delete")?"�����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
			rs.put("wjid", myForm.getWjid());
			rs.put("sfqy", "��");
		}
		request.setAttribute("rs", rs);
//		request.setAttribute("stxxList", service.getCpwjStList(myForm));
		request.setAttribute("xxList", service.getXxlist(myForm, "st"));
		request.setAttribute("doType", doType);
		return mapping.findForward("stwhOne");
	}
	
	/**
	 * �����ʾ����-�鿴����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){
			String msg=service.saveCpwjDa(request, myForm)?"����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
		}

		request.setAttribute("rs", service.getCpwjOne(myForm));//�����ʾ���Ϣ
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//ת������ѡ��Ϊ�ַ���
		
		return mapping.findForward("view");
	}
	
	/**
	 * �����ʾ�������ʾ�¼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglWjlr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		User user = getUser(request);
		HashMap<String, String> fdyInfo = service.getFdyInfo(myForm);
		
		
		BjlhFdykhForm myFormKhb = new BjlhFdykhForm();
		BjlhFdykhService serviceKhb = new BjlhFdykhService();
		String yhm = user.getUserName();
		
		String lx="";
		if("fdy".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��󸨵�Ա
			lx="����Ա";
		} else if("bzr".equalsIgnoreCase(SzkhCssz.KHDX)){//���ֶ��������
			lx="������";
		}
		
		List<HashMap<String,String>> fdylist = serviceKhb.getYhmxx(yhm,lx);
		String dqsjKhb = serviceKhb.getDate();
		
		//�жϱ����ɸ���Ա����ý���
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "��ѧ���û��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(fdylist.size()== 0 ){
				request.setAttribute("yhInfo", "��û�ж�Ӧ�ĸ���Ա���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);				
			} else if(fdylist.size()== 1){
				return new ActionForward("/bjlh_fdycpwj.do?method=cpwjglWjlrSingle&fdyid="+fdylist.get(0).get("zgh"), false);
			} else{
				if (QUERY.equals(myForm.getType())){
					myForm.setWjid(mrsz.get("wjid"));
					myForm.setXh(yhm);
					if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
						myForm.setKhbid(mrsz.get("khbid"));
					}
					List<HashMap<String,String>> cpFdyList=service.getCpFdyList(myForm);
					JSONArray dataList = JSONArray.fromObject(cpFdyList);
					response.getWriter().print(dataList);
					return null;
				}
				String path = "bjlh_fdykh_cpwjlr.do";
				request.setAttribute("path", path);
				FormModleCommon.commonRequestSet(request);
				
				return mapping.findForward("cpFdyList");
			}
		}
		
//		if(mrsz == null ||mrsz.isEmpty()){
//			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
//			return new ActionForward("/yhInfo.do", false);
//		}else{
//			
//			if(mrsz.get("wjid") == null || "".equalsIgnoreCase(mrsz.get("wjid"))){
//				request.setAttribute("yhInfo", "�����ʾ�����д���޷����иò�����");
//				return new ActionForward("/yhInfo.do", false);
//			}
//			
//			//�ж� ��ǰѧ���Ƿ����� ��������
//			if("0".equalsIgnoreCase(mrsz.get("khsfqd"))
//					|| Integer.valueOf(dqsj) < Integer.valueOf(mrsz.get("khkssj")) 
//					|| Integer.valueOf(dqsj) > Integer.valueOf(mrsz.get("khjssj"))){
//				request.setAttribute("yhInfo", "�Ǹ���Ա����ʱ��Σ��޷����иò�����");
//				return new ActionForward("/yhInfo.do", false);
//			}
//		}
//		
//		myForm.setXn(mrsz.get("xn"));		
//		myForm.setWjid(mrsz.get("wjid"));
//		String doType=request.getParameter("doType");
//		if("save".equals(doType)){
//			String msg=service.saveCpwjDa(request, myForm)?"����ɹ���":"����ʧ�ܣ�";
//			request.setAttribute("back", msg);
//		}
//		
//		request.setAttribute("rs", mrsz);//�����ʾ���Ϣ
//		request.setAttribute("fdyInfo", fdyInfo);//����Ա��Ϣ
//		request.setAttribute("xn", Base.currXn);
//		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
//		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//ת������ѡ��Ϊ�ַ���
//		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjlr.do");
//		request.setAttribute("xswjstsfzd", service.getXsWjstSfzd(myForm));
//		
//		//////////////////////////////////#################################################################
//		
//		
//		//��ͨ�ߵ�ʦ��ѧУ����Ҫ��ʾ����Ա������
//		if(!Globals.XXDM_NTGDSF.equalsIgnoreCase(Base.xxdm)){
//		//�жϱ����ɸ���Ա����ý���
//		
//		if (mrszKhb == null || mrszKhb.isEmpty()) {
//			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
//			return new ActionForward("/yhInfo.do", false);
//		} else {
//			if(mrszKhb.get("khbid") == null || "".equalsIgnoreCase(mrszKhb.get("khbid"))){
//				request.setAttribute("yhInfo", "���޲���������д���޷����иò�����");
//				return new ActionForward("/yhInfo.do", false);
//			}
//			
//			// �ж� ��ǰѧ���Ƿ����� ��������
//			if ("0".equalsIgnoreCase(mrszKhb.get("khsfqd"))
//					|| Integer.valueOf(dqsjKhb) < Integer.valueOf(mrszKhb
//							.get("khkssj"))
//					|| Integer.valueOf(dqsjKhb) > Integer.valueOf(mrszKhb
//							.get("khjssj"))) {
//				request.setAttribute("yhInfo", "�Ǹ���Ա����ʱ��Σ��޷����иò�����");
//				return new ActionForward("/yhInfo.do", false);
//			}
//		}
//
////		String doType = request.getParameter("doType");
//		myFormKhb.setKhbid(mrszKhb.get("khbid"));
//		myFormKhb.setYhm(yhm);
//		myFormKhb.setFdyid(fdylist.get(0).get("zgh"));
//		HashMap<String, String> rs = serviceKhb.getKhbxx(myFormKhb);
//		
//		if (doType != null && "save".equalsIgnoreCase(doType)) {
//			String[] xmid = request.getParameterValues("xmid");
//			String[] df = request.getParameterValues("df");
//			if (serviceKhb.saveFdykhpfb(xmid, df, myFormKhb)) {
//				request.setAttribute("message", "����ɹ�");
//			} else {
//				request.setAttribute("message", "����ʧ��");
//			}
//		}
//		// write��title
////		setWriteAbleAndTitle(request, "bjlh_fdykh_xskhcp.do");
//		request.setAttribute("rsKhb", rs);// ���˱���Ϣ
//		}
//		request.setAttribute("rs1", fdylist.get(0));// ���˱���Ϣ
//		request.setAttribute("xmList", serviceKhb.getKhbXmYjzbColNum(myFormKhb));// ��ȡ��������һ��ָ��Ŀ��˱���Ŀ�б�
//		//����ְҵ����ѧԺ���Ի�
//		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
//			request.setAttribute("khbsfzd", serviceKhb.getSfcgkhsj());//����ѧԺ-�ж��Ƿ񳬹�����ʱ��
//		}else{
//			request.setAttribute("khbsfzd", serviceKhb.getKhbSfzd(myFormKhb));
//		}
		//return mapping.findForward("wjlr");
	}
	
	/**
	 * @����:�����ʾ�������ʾ�¼��(��������Աʱ)
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-13 ����03:24:26
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
	public ActionForward cpwjglWjlrSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		BjlhFdykhService serviceKhb = new BjlhFdykhService();
		User user = getUser(request);
		String yhm = user.getUserName();
		String zgh = (String) request.getParameter("fdyid");
		request.setAttribute("fdyid", zgh);
		String fh = (String) request.getParameter("flag");//�ж��Ƿ��з��ذ�ť
		request.setAttribute("fh", fh);
		HashMap<String,String> fdyxx = service.getRsInfo("view_fdyxx", "zgh", zgh, new String[]{"xm","bmmc"});
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		if(mrsz == null ||mrsz.isEmpty()){
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		}else{
			
			if(mrsz.get("wjid") == null || "".equalsIgnoreCase(mrsz.get("wjid"))){
				request.setAttribute("yhInfo", "�����ʾ�����д���޷����иò�����");
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
		
		myForm.setXn(mrsz.get("xn"));		
		myForm.setWjid(mrsz.get("wjid"));
		String doType=request.getParameter("doType");
		myForm.setXh(yhm);
		
		//�������ϴ�ѧ���Ի�
		BjlhFdykhForm myFormKhb = new BjlhFdykhForm();
		if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			HashMap<String, String> mrszKhb = serviceKhb.getMrsz("xs");
			myFormKhb.setKhbid(mrszKhb.get("khbid"));
			myFormKhb.setYhm(yhm);
			myFormKhb.setFdyid(zgh);
			HashMap<String, String> rs = serviceKhb.getKhbxx(myFormKhb);
			request.setAttribute("rsKhb", rs);// ���˱���Ϣ
			
		}
		if("save".equals(doType)){
			
			//�������ϴ�ѧ���Ի�
			if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
				if (doType != null && "save".equalsIgnoreCase(doType) ) {
					String[] xmid = request.getParameterValues("xmid");
					String[] df = request.getParameterValues("df");
					boolean flag= true;
					
					if(!serviceKhb.checkFdykhpf(xmid, df)){
						flag= false;
						request.setAttribute("back", "�����ֵ������Ҫ��");
					}
					
					if(flag){
						String msg=service.saveCpwjDa(request, myForm)?"����ɹ���":"����ʧ�ܣ�";
						request.setAttribute("back", msg);
						if(msg.equalsIgnoreCase("����ɹ���")){
							flag=true;
						}
						
						if(flag){
							if (serviceKhb.saveFdykhpfb(xmid, df, myFormKhb)) {
								request.setAttribute("back", "����ɹ�");
							} else {
								request.setAttribute("back", "����ʧ��");
							}
						}
					}
				}
			}else{
				//ͨ��
				String msg=service.saveCpwjDa(request, myForm)?"����ɹ���":"����ʧ�ܣ�";
				request.setAttribute("back", msg);
			}
		}
		
		request.setAttribute("rs", mrsz);//�����ʾ���Ϣ
		request.setAttribute("fdyInfo", fdyxx);//����Ա��Ϣ
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//ת������ѡ��Ϊ�ַ���
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjlr.do");
		request.setAttribute("xswjstsfzd", service.getXsWjstSfzd(myForm));
		
		request.setAttribute("rs1", fdyxx);// ���˱���Ϣ
		request.setAttribute("xmList", serviceKhb.getKhbXmYjzbColNum(myFormKhb));// ��ȡ��������һ��ָ��Ŀ��˱���Ŀ�б�
		//����ְҵ����ѧԺ���Ի�
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZZYJSXY)){
			request.setAttribute("khbsfzd", serviceKhb.getSfcgkhsj());//����ѧԺ-�ж��Ƿ񳬹�����ʱ��
		}else if(Globals.XXDM_BJLHDX .equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("khbsfzd", service.getCpwjSfzd(myForm)&&serviceKhb.getKhbSfzd(myFormKhb));
		}
		else{
			request.setAttribute("khbsfzd", service.getCpwjSfzd(myForm));
		}
		return mapping.findForward("cpwjglWjlrSingle");
	}
	
	
	/**
	 * �����ʾ�������ʾ�¼��_�޸�ǰ�� ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglWjlr_back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		HashMap<String, String> mrsz = service.getMrsz();
		String dqsj = service.getDate();
		
		User user = getUser(request);
		HashMap<String, String> fdyInfo = service.getFdyInfo(myForm);
		//�жϱ����ɸ���Ա����ý���
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("yhInfo", "��ѧ���û��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		}else{
			if(fdyInfo.get("zgh")== null ){
				request.setAttribute("yhInfo", "��û�ж�Ӧ�ĸ���Ա���޷����иò�����");
				return new ActionForward("/yhInfo.do", false);				
			}
		}
		
		if(mrsz == null ||mrsz.isEmpty()){
			request.setAttribute("yhInfo", "��ǰѧ�꿼�˲���δ���ã��޷����иò�����");
			return new ActionForward("/yhInfo.do", false);
		}else{

			if(mrsz.get("wjid") == null || "".equalsIgnoreCase(mrsz.get("wjid"))){
				request.setAttribute("yhInfo", "�����ʾ�����д���޷����иò�����");
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
		
		String doType=request.getParameter("doType");
		if("save".equals(doType)){
			String msg=service.saveCpwjDa(request, myForm)?"����ɹ���":"����ʧ�ܣ�";
			request.setAttribute("back", msg);
		}

		myForm.setXn(mrsz.get("xn"));		
		myForm.setWjid(mrsz.get("wjid"));
		request.setAttribute("rs", mrsz);//�����ʾ���Ϣ
		request.setAttribute("fdyInfo", fdyInfo);//����Ա��Ϣ
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxXxHtml(myForm,stxxList));//ת������ѡ��Ϊ�ַ���
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjlr.do");
		request.setAttribute("xswjstsfzd", service.getXsWjstSfzd(myForm));
		return mapping.findForward("wjlr");
	}
	
	/**
	 * �����ʾ����--��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		//�жϱ�����ѧԺ��ѧУ�򸨵�Ա�û�����ý���
		if("stu".equals(user.getUserType()) || "true".equalsIgnoreCase(user.getBzrQx())){
			request.setAttribute("yhInfo", "�����μ�ѧ���û���Ȩ���иò�����");
			return new ActionForward("/yhInfo.do", false);
		}
		
		// ����
		String doType = request.getParameter("doType");
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		
		HashMap<String, String> mrsz = service.getMrsz();
		
		String[] xn = myForm.getSearchModel().getSearch_tj_xn();
		if(xn==null){
			xn = new String[]{mrsz.get("xn")};
			myForm.getSearchModel().setPath("bjlh_fdykh_fdyzpcx.do");
			myForm.getSearchModel().setSearch_tj_xn(xn);
		}
		if("del".equalsIgnoreCase(doType)){
			String message = service.deleteCpwjInfo(myForm); 
			request.setAttribute("message", message);
		}
		
		List<String[]> rs = service.getCpwjTjQueryList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		setWriteAbleAndTitle(request, "bjlh_fdykh_cpwjcx.do");
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("cpwjtj"));
		
		return mapping.findForward("query");
	}
	
	/**
	 * �����ʾ������ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cpwjglTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjlhFdycpwjForm myForm = (BjlhFdycpwjForm)form;
//		myForm.setFdyid("null");
		myForm.setXh(getUser(request).getUserName());
		BjlhFdycpwjService service=new BjlhFdycpwjService();
		
		
		request.setAttribute("rs", service.getCpwjOne(myForm));//�����ʾ���Ϣ
		List<HashMap<String,String>> stxxList=service.getCpwjStList(myForm);
		request.setAttribute("stList",service.getStxxTjxxHtml(myForm,stxxList));//ת������ѡ��Ϊ�ַ���
		
		
		return mapping.findForward("tj");
	}
	
	
}
