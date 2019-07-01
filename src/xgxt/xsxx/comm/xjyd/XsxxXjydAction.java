package xgxt.xsxx.comm.xjyd;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.audit.spgc.AuditingInterface;
import xgxt.audit.spgc.AuditingModel;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * ѧ����Ϣ-ѧ���춯
 * @author Penghui.Qu
 */
public class XsxxXjydAction extends BasicAction {

	
	/**
	 * ѧ���춯������ 
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydlbgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxXjydForm model = (XsxxXjydForm) form;
		String[] topTr = new String[] { "�춯������", "�춯�������", "ѧ��״̬",
						"�Ƿ���У","�������", "�����˷�Χ" };

		//��ѯ
		//if (QUERY.equalsIgnoreCase(model.getDoType())){
			XsxxXjydService service = new XsxxXjydService();
			request.setAttribute("rs", service.getYdlbList(model));
		//}
		
		setListData(request,"ydlbgl");
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "xsxx_xjydlbgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydlbgl");
	}
	
	
	/**
	 * �춯���ɾ��
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		XsxxXjydForm model = (XsxxXjydForm) form;
		//System.out.println("������");
		deleteOperation(request, tableName);
		
		model.setDoType(QUERY);
		return xjydlbgl(mapping,form,request,response);
	}
	
	
	
	/**
	 * �춯�������
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		//����
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			insertOperation(request, tableName);
		}
		
		setListData(request,"ydlbgl");
		return mapping.findForward("ydlbAdd");
	}
	
	
	/**
	 * �춯����޸�
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		//�޸�
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			updateOperation(request, tableName);
		}
		
		setListData(request,"ydlbgl");
		return mapping.findForward("ydlbUpdate");
	}
	
	
	
	/**
	 * �춯�����ص�����¼
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydlbView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "dm_ydlb";
		String pkValue = request.getParameter("pkValue");
		
		//������ѯ
		if (StringUtils.isNotNull(pkValue)){
			selectPageDataByOne(request, tableName, tableName, pkValue);
		}
		
		setListData(request,"ydlbgl");
		return mapping.findForward("ydlbUpdate");
	}
	
	
	/**
	 * �������������˷�Χ
	 * @return
	 * @throws Exception
	 */
	public ActionForward plszSqrfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		
		boolean result = service.plszSqrfw(model);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		model.setDoType(QUERY);
		return xjydlbgl(mapping,form,request,response);
	}
	
	
	
	/**
	 * ѧ���춯����
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "bks_xjydxx";
		String shztb = "xg_xsxx_xjydshztb";
		String xh = request.getParameter("xh");
		XsxxXjydForm model = (XsxxXjydForm) form;
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)){
			xh = userName;
		}
		
		//����ѧ��������Ϣ
		if (StringUtils.isNotNull(xh)){
			XsxxglService xsxxglService = new XsxxglService();
			request.setAttribute("rs", xsxxglService.selectStuinfo(xh));
			
			XsxxXjydService service = new XsxxXjydService();
			//ͬһѧ��ͬһ�첻���ظ�����
			if(Globals.XXDM_HBJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
				request.setAttribute("sfcf", true);
			}else{
				request.setAttribute("sfcf", service.sfcf(xh));
			}
		}
		
		//���뱣��
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			//---------- 2012-10-22 honglin ������ְͨҵ����ѧԺ begin----------//
			if(Globals.XXDM_HBJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
				XsxxXjydService service = new XsxxXjydService();
				String xsxh= request.getParameter("save_xh");
				String xn = model.getXn();
				String ydlbm =request.getParameter("save_ydlbm");
				if(StringUtils.isNotNull(xsxh) && StringUtils.isNotNull(xn) && StringUtils.isNotNull(ydlbm)){
					//ͬһѧ��ͬһѧ�Ų����ظ�����
					boolean cf = service.sfcfByXn(xsxh,xn,ydlbm);
					if(cf){}else{
						request.setAttribute("message", "��ѧ����"+xn+"ѧ���Ѿ������������ѧ���춯�������ٴν�������!");
						model.setSave_id(GetSysData.getGuid());
						model.setSave_sqsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
						setListData(request,"xjydsq");
						request.setAttribute("xn", model.getXn());
						request.setAttribute("path", "xsxx_xjydsq.do");
						FormModleCommon.commonRequestSet(request);
						return mapping.findForward("xjydsq");
					}
				}
			}
			//---------- 2012-10-22 honglin ������ְͨҵ����ѧԺ end----------//
			//���������¼
			insertOperation(request, tableName);
			boolean result = (Boolean)request.getAttribute("result");
			
			//�ύ��������� && "��".equalsIgnoreCase(model.getSftj())
			if (result){
				AuditingInterface manage = new XsxxXjydService();
				AuditingModel auditModel = new AuditingModel();
				
				auditModel.setXtgwid("Applicant");
				auditModel.setId(model.getSave_id());
				auditModel.setShlcid(model.getSave_shlcid());
				auditModel.setShr(model.getSave_sqr());
				auditModel.setSftj(model.getSftj());
				manage.saveAuditing(auditModel, shztb,null,auditModel);
			}
		} else {
			model.setSave_xn(Base.currXn);
			model.setSave_xq(Base.currXq);
		}
		
		model.setSave_id(GetSysData.getGuid());
		model.setSave_sqsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		setListData(request,"xjydsq");
		request.setAttribute("xn", model.getXn());
		request.setAttribute("path", "xsxx_xjydsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydsq");
	}
	
	
	
	/**
	 * ѧ���춯���(�춯��Ϣֱ���ύ��ʽ��)
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "bks_xjydxx";
		String xh = request.getParameter("xh");
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		
		if (StringUtils.isNull(request.getParameter("save_xn"))) {
			model.setXn(Base.currXn);
		}else{
			model.setXn(request.getParameter("save_xn"));
		}
		//����ѧ��������Ϣ
		if (StringUtils.isNotNull(xh)){
			XsxxglService xsxxglService = new XsxxglService();
			request.setAttribute("rs", xsxxglService.selectStuinfo(xh));
		}
		
		//���뱣��
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			//���������¼
			insertOperation(request, tableName);
			boolean result = (Boolean)request.getAttribute("result");
			
//			if (result){//�ύ�춯��Ϣ����ʽ��
//				service.submitStuInfo(new String[]{model.getSave_id()});
//			}
		}
		
		model.setSave_id(GetSysData.getGuid());
		model.setSave_sqsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		setListData(request,"xjydsq");
		request.setAttribute("xn",model.getXn());
		return mapping.findForward("xjydAdd");
	}
	
	
	
	
	/**
	 * ѧ���춯��˲�ѯ
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		String[] topTr = new String[]{"ѧ��","����","ѧ��","ѧ��","�춯���",
				"ת��"+Base.YXPZXY_KEY,"ת���༶","ת��"+Base.YXPZXY_KEY,"ת��༶","����ʱ��"};
		
		//��ѯ
//		if (QUERY.equalsIgnoreCase(model.getDoType())){
			List<String[]> rs = service.queryXjydsh(model, getUser(request));
			request.setAttribute("rs", rs);
//		}
		
		setListData(request,"xjydsh");
		setNjXyZyBj(request,model);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		
		request.setAttribute("xn", model.getXn());
		request.setAttribute("path", "xsxx_xjydsh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xjydsh");
	}
	/**
	 * ѧ���춯��˲�ѯ
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydshquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return xjydsh(mapping, form, request, response);
	}
	
	
	
	/**
	 * �������ѧ���춯
	 * @return
	 * @throws Exception
	 */
	public ActionForward plshXjyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxXjydForm model = (XsxxXjydForm) form;
		String[] pkValues = model.getPrimarykey_cbv(); 
		User user = getUser(request);
		
		model.setShr(user.getUserName());
		model.setShsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		
		//boolean result = service.plshXjyd(model, pkValues);
		String shztb = "xg_xsxx_xjydshztb";
		AuditingInterface manage = new XsxxXjydService();
		AuditingModel auditingModel = new AuditingModel();
		auditingModel.setSftj(model.getSftj());
		auditingModel.setShr(model.getShr());
		auditingModel.setShyj(model.getShyj());
		auditingModel.setShzt(model.getShjg());
		
		boolean result = manage.saveBatchAuditingAndDoSomething(auditingModel, pkValues, shztb,null,auditingModel);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		model.setDoType(QUERY);
		return xjydsh(mapping, form, request, response);
	}
	
	
	
	
	/**
	 * ѧ���춯�����ѯ
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		XsxxXjydService service = new XsxxXjydService();
		String[] topTr = new String[]{"ѧ��","����","ѧ��","ѧ��","�춯���","ת��༶","ת���༶","����ʱ��","��˽��"};
		
//		if (QUERY.equalsIgnoreCase(model.getDoType())){
			List<String[]> rs = service.queryXjyd(model,getUser(request));
			request.setAttribute("rs", rs);
//		}
		
		setListData(request,"xjydcx");
		setNjXyZyBj(request,model);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		request.setAttribute("xn", model.getXn());
		request.setAttribute("path", "xsxx_xjydcx.do");
		FormModleCommon.commonRequestSet(request);
		//ѧУ���Ի�
		if(Globals.XXDM_GLLGDX.equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsxx/comm/xjyd/xxgxh/xjydcx_"+Base.xxdm+".jsp",false);
		}
		return mapping.findForward("xjydcx");
	}
	/**
	 * ѧ���춯�����ѯ
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydcxquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return xjydcx(mapping, form, request, response);
	}
	
	/**
	 * ѧ���춯ɾ��
	 * @return
	 * @throws Exception
	 */
	public ActionForward delXjyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();

		boolean result = service.delXjyd(model);
		request.setAttribute("message", result ? DEL_SUCCESS : DEL_FAIL);

		model.setDoType(QUERY);
		return xjydcx(mapping, form, request, response);
	}
	
	
	
	/**
	 * ѧ���춯������˲�ѯ
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydDgshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxXjydService service = new XsxxXjydService();
		String pkValue = request.getParameter("pkValue");
		String userName = (String) request.getSession().getAttribute("userName");
		
		//������ѯ
		if (StringUtils.isNotNull(pkValue)){
			HashMap<String,String> rs = service.getXjydById(pkValue);//������Ϣ
			//rs.putAll(service.getShxxById(pkValue, userName));//�����Ϣ
			
			request.setAttribute("rs", rs);
			request.setAttribute("cj", service.getXscjByXh(rs.get("xh")));
			//�˻�ʱ��Ҫѡ���˻ص��ĸ�����
			//XsxxXjydService manage = new XsxxXjydService();
			AuditingModel auditingModel = new AuditingModel();
			auditingModel.setId(rs.get("id"));
			auditingModel.setShr(userName);
			//������λ
			String spgwid = service.getSpgw(auditingModel, "xg_xsxx_xjydshztb");
			auditingModel.setXtgwid(spgwid);
			auditingModel.setShlcid(rs.get("shlcid"));
			
			//�Ƿ����һ�����
			request.setAttribute("isLastSpgw", service.isLastAudit(auditingModel));
			
			request.setAttribute("xtgwList", service.getKthXtgw(rs.get("shlcid"), spgwid));
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(rs.get("ydhxydm")));
			request.setAttribute("xnList", Base.getXnndList()); // ����ѧ���б�
			String bjKey = rs.get("ydhxydm") + "!!" + rs.get("ydhzydm") + "!!" + rs.get("ydhnj");
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		}
		
		request.setAttribute("now", GetTime.getTimeByFormat("yyyy-mm-dd"));
		
		setListData(request,"dgsh");
		return mapping.findForward("dgshXjyd");
	}
	
	
	
	
	/**
	 * ѧ���춯�������
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "bks_xjydxx";
		XsxxXjydForm model = (XsxxXjydForm) form;
		
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			//�޸������¼����֤����˿��Էְ�
			updateOperation(request, tableName);
			boolean result = (Boolean) request.getAttribute("result");
			
			if (result){
				AuditingInterface manage = new XsxxXjydService();
				AuditingModel auditingModel = new AuditingModel();
				auditingModel.setId(request.getParameter("pkValue"));
				auditingModel.setSftj(model.getSftj());
				auditingModel.setShr(model.getShr());
				auditingModel.setShyj(model.getShyj());
				auditingModel.setShzt(model.getShjg());
				auditingModel.setShlcid(model.getSave_shlcid());
				auditingModel.setThgw(model.getThjsr());
				manage.saveAuditing(auditingModel, "xg_xsxx_xjydshztb",null,auditingModel);
			}
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		setListData(request,"dgsh");
		return mapping.findForward("dgshXjyd");
	}
	
	
	/**
	 * ѧ���춯-���̸���
	 * @return
	 * @throws Exception
	 */
	public ActionForward lcgzXjyd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("pkValue");
		XsxxXjydService service = new XsxxXjydService();
		
		if (StringUtils.isNotNull(id)){
			HashMap<String,String> sqxx = service.getXjydById(id);//������Ϣ
			List<HashMap<String,String>> shxx = service.getShxxList(id);//�����Ϣ

			request.setAttribute("sqxx", sqxx);
			request.setAttribute("rs", shxx);
		}
		
		return mapping.findForward("lcgzXjyd");
	}
	
	
	
	/**
	 * ѧ���춯�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		String pkValue = request.getParameter("pkValue");
		
		//������ѯ
		if (StringUtils.isNotNull(pkValue)){
			HashMap<String,String> rs = service.getXjydById(pkValue);//������Ϣ
			request.setAttribute("rs", rs);
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("xnList", Base.getXnndList()); // ����ѧ���б�
			request.setAttribute("xqList", Base.getXqList()); // ����ѧ���б�
			request.setAttribute("zyList", (Base.getZyMap()).get(rs.get("ydhxydm")));
			String bjKey = rs.get("ydhxydm") + "!!" + rs.get("ydhzydm") + "!!" + rs.get("ydhnj");
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		}
		
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			
			String tableName = "bks_xjydxx";
			String shztb = "xg_xsxx_xjydshztb";
			
			updateOperation(request, tableName);
			boolean result = (Boolean)request.getAttribute("result");
			
			//�ύ���������
			if (result && "��".equalsIgnoreCase(model.getSftj())){
				AuditingInterface manage = new XsxxXjydService();
				AuditingModel auditModel = new AuditingModel();
				
				auditModel.setXtgwid("Applicant");
				auditModel.setId(model.getSave_id());
				auditModel.setShlcid(model.getSave_shlcid());
				auditModel.setShr(model.getSave_sqr());
				auditModel.setSftj(model.getSftj());
				result = manage.saveAuditing(auditModel, shztb,null,auditModel);
			}
			
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		setListData(request,"dgsh");
		return mapping.findForward("xjydUpdate");
	}
	
	
	
	/**
	 * ���������б������
	 * @param reqeust
	 * */
	public void setListData(HttpServletRequest request,String flg){
		XsxxXjydService service = new XsxxXjydService();
		XsxxglService xsxxglService = new XsxxglService();
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xq",Base.currXq);
		//ѧ��״̬
		request.setAttribute("xjztList", xsxxglService.getList("xjztList"));
		//�Ƿ���У
		request.setAttribute("sfzxList", xsxxglService.getList("sfzxList"));
		//�춯����
		request.setAttribute("ydlbList", service.getYdlbByUser(getUser(request)));
		request.setAttribute("ydlbAllList", service.getYdlbAll(getUser(request)));
		if ("ydlbgl".equalsIgnoreCase(flg)){
			//�������
			request.setAttribute("shlcList", xsxxglService.getList("shlcList"));
			request.setAttribute("xnList", Base.getXnndList()); // ����ѧ���б�
		} else if ("xjydsq".equalsIgnoreCase(flg)){
			//�춯���
			request.setAttribute("xnList", Base.getXnndList()); // ����ѧ���б�
			request.setAttribute("xqList", Base.getXqList());//ѧ���б�
			//�꼶��ѧԺ��רҵ���༶
			FormModleCommon.setNjXyZyBjList(request);
		} else if ("xjydcx".equalsIgnoreCase(flg)) {
			//���״̬
			request.setAttribute("shjgList", service.getList("shjg"));
			request.setAttribute("xnList", Base.getXnndList()); // ����ѧ���б�
			request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		} else if ("xjydsh".equalsIgnoreCase(flg)) {
			//���״̬
			request.setAttribute("shztList",service.getList("shzt"));
			request.setAttribute("xnList", Base.getXnndList()); // ����ѧ���б�
			request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		} else if ("dgsh".equalsIgnoreCase(flg)){
			//���״̬
			request.setAttribute("shztList",service.getList("shzt"));
			//�춯���
			request.setAttribute("xnList", Base.getXnndList()); // ����ѧ���б�
		}
	}
	
	
	
	/**
	 * �꼶��ѧԺ��רҵ���༶
	 * @param request
	 * @param model
	 */
	private void setNjXyZyBj(HttpServletRequest request,XsxxXjydForm model){
		
		String ydqnj = model.getYdqnj();
		String ydqxydm = model.getYdqxydm();
		String ydqzydm = model.getYdqzydm();
		String ydhqbdm = model.getYdqbdm();
		
		ydqnj = (ydqnj == null) ? "" : ydqnj;
		ydqxydm = (ydqxydm == null) ? "" : ydqxydm;
		ydqzydm = (ydqzydm == null) ? "" : ydqzydm;
		ydhqbdm = (ydhqbdm == null) ? "" : ydhqbdm;
		
		//�춯ǰѧԺ��רҵ���༶�б�
		String ydqBjKey = ydqxydm + "!!" + ydqzydm + "!!" + ydqnj;
		request.setAttribute("ydqXyList", Base.getXyList());
		request.setAttribute("ydqZyList", (Base.getZyMap()).get(ydqxydm));
		request.setAttribute("ydqBjList", (Base.getBjMap()).get(ydqBjKey));
		
		
		String ydhnj = model.getYdhnj();
		String ydhxydm = model.getYdhxydm();
		String ydhzydm = model.getYdhzydm();
		String ydhbdm = model.getYdhbdm();
		
		ydhnj = (ydhnj == null) ? "" : ydhnj;
		ydhxydm = (ydhxydm == null) ? "" : ydhxydm;
		ydhzydm = (ydhzydm == null) ? "" : ydhzydm;
		ydhbdm = (ydhbdm == null) ? "" : ydhbdm;
		
		//�춯��ѧԺ��רҵ���༶�б�
		String ydhBjKey = ydhxydm + "!!" + ydhzydm + "!!" + ydhnj;
		request.setAttribute("ydhXyList", Base.getXyList());
		request.setAttribute("ydhZyList", (Base.getZyMap()).get(ydhxydm));
		request.setAttribute("ydhBjList", (Base.getBjMap()).get(ydhBjKey));
		
		request.setAttribute("njList", Base.getNjList());
	}


	
	/**
	 * �춯�ĺŴ���
	 */
	public ActionForward ydwhcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		String[] topTr = new String[]{"�����ĺ�","ѧ��","ѧ��","����","�춯���","�춯ǰ�༶����","�춯��༶����","����ʱ��"};
		
//		if (QUERY.equalsIgnoreCase(model.getDoType())){
			model.setShzt("ͨ��");
			List<String[]> rs = service.queryWhcl(model,getUser(request));
			request.setAttribute("rs", rs);
//		}
		
		setListData(request,"xjydcx");
		setNjXyZyBj(request,model);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		request.setAttribute("path", "xsxx_ydwhcl.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		
		return mapping.findForward("ydwhcl");
	}


	/**
	 * ���洦���ĺ�
	 */
	public ActionForward saveClwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		
		boolean result = service.saveClwh(model);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return ydwhcl(mapping, form, request, response);
	}
	/**
	 * ɾ�����ʹ�ü��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DelDetectModel model = new DelDetectModel();
		XsxxXjydService service = new XsxxXjydService();

		// ������ʽ
		String[] pkValue = request.getParameter("pk").split("!!@@!!");
		model.setPkValue(pkValue);

		// ��ʾ��Ϣ
		String message = service.checkDel(model);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * �޸����ʹ�ü��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DelDetectModel model = new DelDetectModel();
		XsxxXjydService service = new XsxxXjydService();

		// ������ʽ
		String[] pkValue = request.getParameter("pk").split("!!@@!!");
		model.setPkValue(pkValue);

		// ��ʾ��Ϣ
		String message = service.checkUpdate(model);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * ����춯��������˷�Χ�Ƿ����ʹ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSqrfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DelDetectModel model = new DelDetectModel();
		XsxxXjydService service = new XsxxXjydService();

		// ������ʽ
		String[] pkValue = request.getParameter("pk").split("!!@@!!");
		model.setPkValue(pkValue);

		// ��ʾ��Ϣ
		String message = service.checkSqrfw(model);

		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * ѧ���춯ͳ��
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjydtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		XsxxXjydService service = new XsxxXjydService();
		List<HashMap<String, String>> topTr =service.getXjydTop(model);
		//��ѯ
		if (QUERY.equalsIgnoreCase(model.getDoType())){
			request.setAttribute("rs", service.xjydtj(model));
		}
		
		setListData(request,"ydlbgl");
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "xsxx_xjydtj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydtj");
	}
	
	/**
	 * �������
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydService service = new XsxxXjydService();
		XsxxXjydForm myForm = (XsxxXjydForm) form;
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = service.getXjydDcTop(myForm);
		List<String[]> rs = service.xjydtj(myForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ѧ���춯ѧ��
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxXjydxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxXjydForm model = (XsxxXjydForm) form;
		String fwcs =  request.getParameter("fwcs");
		XsxxXjydService service = new XsxxXjydService();
		request.setAttribute("rs", service.cxXjydxs(model,fwcs));
		String[] topTr = new String[]{"ѧ��","����","ѧԺ","רҵ","�༶","�춯ʱ��"};
		setListData(request,"ydlbgl");
		request.setAttribute("topTr", topTr);
		request.setAttribute("sqkssj", model.getSqkssj());
		request.setAttribute("sqjssj", model.getSqjssj());
		String tjfwmc="";
		String fwcsmc="";
		if("ydqxydm".equals(model.getTjfw())){
			tjfwmc="ѧԺ";
			List<HashMap<String,String>> list= service.getNjxyzybjmc("ydqxydm",fwcs);
			if(null!=list&&list.size()>0){
				fwcsmc = list.get(0).get("xymc");
			}else{
				fwcsmc="�ϼ�";
			}
		}
		if("ydqzydm".equals(model.getTjfw())){
			tjfwmc="רҵ";
			List<HashMap<String,String>> list= service.getNjxyzybjmc("ydqzydm",fwcs);
			if(null!=list&&list.size()>0){
				fwcsmc = list.get(0).get("zymc");
			}else{
				fwcsmc="�ϼ�";
			}
		}
		if("ydqbdm".equals(model.getTjfw())){
			tjfwmc="�༶";
			List<HashMap<String,String>> list= service.getNjxyzybjmc("ydqbdm",fwcs);
			if(null!=list&&list.size()>0){
				fwcsmc = list.get(0).get("bjmc");
			}else{
				fwcsmc="�ϼ�";
			}
		}
		if("xn".equals(model.getTjfw())){
			tjfwmc="ѧ��";
			fwcsmc=fwcs;
		}
		if("ydqnj".equals(model.getTjfw())){
			tjfwmc="�꼶";
			fwcsmc=fwcs;
		}
		request.setAttribute("tjfw",tjfwmc);
		request.setAttribute("fwcs",fwcs);
		request.setAttribute("fwcsmc",fwcsmc);
		request.setAttribute("tjfwdm",model.getTjfw());
		request.setAttribute("ydlbm",model.getYdlbm());
		request.setAttribute("ydlbmc",service.getXjydlbmc(model.getYdlbm()).get(0).get("ydlbmc"));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxXjydxs");
	}
}
