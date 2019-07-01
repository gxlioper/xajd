package xgxt.xtwh.sysz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xszz.comm.XszzCommService;
import xgxt.xtwh.XtwhInit;
import xsgzgl.xtwh.general.news.NewQxfwForm;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.SearchUtil;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ҳ����_action��
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

public class SyszAction extends BasicAction {
	// =========== ��ʼ�� begin ============
	private List<HashMap<String, String>> radioList = new ArrayList<HashMap<String, String>>();
	{
		addRadioList("ȫУ", "ʦ��", "teastu", radioList);
		addRadioList("��ʦ", "��ʦ", "tea", radioList);
		addRadioList("ѧ��", "ѧ��", "stu", radioList);
	}

	private void addRadioList(String pName, String cName, String toWho, List<HashMap<String, String>> radioList){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pName", pName);
		map.put("cName", cName);
		map.put("toWho", toWho);
		radioList.add(map);
	}
	// =========== ��ʼ�� end ============
	/**
	 * ��ҳ- Ajax��������ר���ļ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadFilesInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getFilesInfoList(myForm,user);
		
		JSONArray filesInfoList = JSONArray.fromObject(resultList);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(filesInfoList);
		
		return null;
	}
	
	

	/**
	 * ϵͳά��_��ҳ����_����ר������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ϵͳά��-��ҳ����-����ר��ά��:ɾ��values:{primarykey_checkVal}")
	public ActionForward xzzqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXtwhXzzqInit(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();

		// �Ƿ��ѯ���������������жϸ��ֶΣ�����˵�Ĭ��ִ�в�ѯ����--20121225--zhanghui
//		boolean search = Boolean.parseBoolean(rForm.getSearch());

		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();

		// ����б�
		ArrayList<String[]> rsArrList = null;
		// =================== end ===================

		// ============= ִ��ɾ������ ============
		if ("del".equalsIgnoreCase(doType)) {

			String[] checkVal = myForm.getPrimarykey_checkVal();

			if (checkVal != null && checkVal.length > 0) {

				boolean flag = service.delXzzq(myForm, user, request);

				String message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
						: MessageInfo.MESSAGE_DEL_FALSE;

				rForm.setMessage(message);
			}

		}
		// =================== end ==============

		// =============== ִ�в�ѯ���� ===========
//		if (search) {�����������жϸ��ֶΣ�����˵�Ĭ��ִ�в�ѯ����--20121225--zhanghui
			// �����
			rsArrList = service.getXzzqRsList(myForm, user, colList);
//		} else {
//			myForm.getPages().setMaxPage(1);
//		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xzzqManage");
	}

	/**
	 * ϵͳά��_��ҳ����_��ʾ��Ŀά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ϵͳά��-��ҳ����-����ר��ά��:���ӻ򱣴�NEWSID:{newsId},FILEMC:{filemc},FILELX:{filelx},FILESS:{filess},UPDATEFILE:{updateFile}")
	public ActionForward xzzqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXtwhXzzqInit(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		// =================== end ===================
		myForm.setFilemc(StringEscapeUtils.escapeHtml4(myForm.getFilemc()));
		myForm.setFilesm(StringEscapeUtils.escapeHtml4(myForm.getFilesm()));
		myForm.setBz(StringEscapeUtils.escapeHtml4(myForm.getBz()));
		request.setAttribute("filelx",myForm.getFilelx());
		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {

			String filepath = myForm.getFilepath();

			if (Base.isNull(filepath)) {
				FormFile file = myForm.getUploadFile();

				// �ϴ�·��
				filepath = service.upLoadFile(request, file, "xzzq");

			}

			if (!Base.isNull(filepath)) {
				myForm.setFilepath(filepath);
				boolean flag = service.saveXzzq(myForm, user, request);
				String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
						: MessageInfo.MESSAGE_SAVE_FALSE;
				rForm.setMessage(message);
				rForm.setPk(myForm.getNewsid());
			}
		}else if("modi".equalsIgnoreCase(doType)){

			FormFile file = myForm.getUpdateFile();
			String path="";
			if (!"".equalsIgnoreCase(file.getFileName())) {
				path = service.upLoadFile(request, file, "xzzq");
				if(!StringUtils.isEmpty(path)){
					//ɾ���ļ�
					service.delUpLoadFile(new String[]{myForm.getFilepath()});
					//ɾ����¼
					service.deleteFile(myForm.getFilepath());
					myForm.setFilepath(path);
				}
			}
			//if (!Base.isNull(path)) {
			boolean flag = service.saveXzzq(myForm, user, request);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
			rForm.setPk(myForm.getNewsid());
			//}
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("add".equalsIgnoreCase(doType)) {// ����
//			rs.put("xzdx", "ȫ��");
			rs.put("newsid", "");
		} else {
			myForm.setNewsid(rForm.getPk());
			//rForm.setPk(myForm.getFilepath());
			rs = service.getXzzqInfo(myForm);
			if (!"view".equalsIgnoreCase(doType)) {
				String bz = rs.get("bz")!=null?rs.get("bz"):"";
				String filesm = rs.get("filesm")!=null?rs.get("filesm"):"";
				rs.put("bz",bz.replaceAll("<br/>", "\\\n"));
				rs.put("filesm",filesm.replaceAll("<br/>", "\\\n"));	
			}
		}
		rForm.setRs(rs);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		request.setAttribute("doType", doType);
		service.setRequestValue(rForm, user, request);
		request.setAttribute("radioList", radioList);
		if ("add".equalsIgnoreCase(doType)) {// ����
			
		} else {
			NewQxfwForm nqf = SearchUtil.getInstance().getNewQxfwForm(rForm.getPk());
			request.setAttribute("search", nqf);
			request.setAttribute("selectTj", SearchUtil.getInstance().getSelectTj(nqf)); 
		}
		// =================== end ===================
		if ("view".equalsIgnoreCase(doType)) {// ����
			return mapping.findForward("viewXzzq");
		} else{
			return mapping.findForward("xzzqUpdate");
		}
	}

	/**
	 * ϵͳά��_��ҳ_����ר��(more)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xzzqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		request.setAttribute("writeAble", "no");//���� ������ť
		RequestForm rForm = new RequestForm();
		init.getXtwhXzzqInit(rForm, myForm, request);
		String[] colList = rForm.getColList();
		ArrayList<String[]> rsArrList = service.getXzzqMoreList(myForm, user, colList);
		rForm.setRsArrList(rsArrList);
		service.setXtwhOptionList(myForm, rForm, request);
		service.setRequestValue(rForm, user, request);
		
		return mapping.findForward("xzzqMore");
	}

	/**
	 * ϵͳά��_��ҳ����_��ҳ���飨����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sydcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXtwhSydcInit(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();

		// �Ƿ��ѯ���������������жϸ��ֶΣ�����˵�Ĭ��ִ�в�ѯ����--20121225--zhanghui
//		boolean search = Boolean.parseBoolean(rForm.getSearch());

		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();

		// ����б�
		ArrayList<String[]> rsArrList = null;

		// =================== end ===================

		// ============= ִ��ɾ������ ============
		if ("del".equalsIgnoreCase(doType)) {

			String[] checkVal = myForm.getPrimarykey_checkVal();

			if (checkVal != null && checkVal.length > 0) {

				boolean flag = service.delSydc(myForm, user, request);

				String message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
						: MessageInfo.MESSAGE_DEL_FALSE;

				rForm.setMessage(message);
			}

		}
		// =================== end ==============

		// ============= ִ�б����Ƿ����ò��� ============
		if ("save".equalsIgnoreCase(doType)) {

			// �����Ƿ�����
			String dcqy = myForm.getDcqy();
			myForm.setDcid(dcqy);

			boolean flag = service.saveSydcSfqy(myForm, user);

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;

			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== ִ�в�ѯ���� ===========
//		if (search) {�����������жϸ��ֶΣ�����˵�Ĭ��ִ�в�ѯ����--20121225--zhanghui
			// �����
			rsArrList = service.getSydcRsList(myForm, user, colList);
//		}
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("sydcManage");
	}

	/**
	 * ϵͳά��_��ҳ����_��ҳ���飨ά����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sydcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXtwhSydcInit(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		// =================== end ===================

		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.saveSydc(myForm, user, request);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);

		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		HashMap<String, String> rs = new HashMap<String, String>();
		if (Base.isNull(doType)) {// ����
			rs.put("sfqy", "��");
		} else {
			myForm.setDcid(rForm.getPk());
			rs = service.getSzdcInfo(myForm);
		}
		rForm.setRs(rs);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("sydcUpdate");
	}

	/**
	 * ϵͳά��_��ҳ����_��ҳ���飨�鿴ͳ�ƣ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sydcView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXtwhSydcInit(rForm, myForm, request);
		// =================== end ===================

		// =============== ��ʼ��ҳ����ʾֵ ===========
		// ����ID
		String dcid = request.getParameter("dcid");

		// ��������
		myForm.setDcid(dcid);
//		String dcnr = service
//				.getOneValue("xg_xtwh_sydcb", "dcnr", "dcid", dcid);
		myForm.setDcid(dcid);
		request.setAttribute("dcMap", service.getSydcInfo(myForm));
		
		// ��������
		List<HashMap<String, String>> rsList = service.getSydcTjList(myForm,
				user);
		rForm.setRsList(rsList);
	
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXtwhOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		//request.setAttribute("dcnr", dcnr);
		// =================== end ===================

		return mapping.findForward("sydcView");
	}

	// =======================����made by ΰ�����=====================

	/**
	 * ѧ��������Ϣ author ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward xssqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		SyszForm myForm = (SyszForm) form;
		BasicService basicService = new BasicService();
		SyszService service = new SyszService();

		String userName = session.getAttribute("userName").toString();
		myForm.setUserName(userName);

		// ��ѯ
		request.setAttribute("rs", service.getXssqInfo(myForm));

		// ��ʼ�������б�
		service.getXssqInit(request,myForm);
		request.setAttribute("path", "newsInfo.do");
		
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("mklx", myForm.getMklx());
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", basicService.getTopTr("xg_sysz_tpszb",
				new String[] { "��Ŀ����", "��������", "������" }));
		
		return mapping.findForward("xssqInfo");
	}

	/**
	 * ����������Ϣ author ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward dbsxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();

		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		boolean fdyQx=false;
		boolean bzrQx=false;
		try{
			bzrQx=(Boolean)session.getAttribute("bzrQx");
			fdyQx=(Boolean)session.getAttribute("fdyQx");
		}catch (Exception e) {
			// TODO: handle exception
		}
		myForm.setUserDep(userDep);
		myForm.setUserName(userName);
		myForm.setUserType(userType);
		myForm.setFdyQx(fdyQx);
		myForm.setBzrQx(bzrQx);

		// ��ѯ���
		request.setAttribute("rs", service.getDbsxInfo(myForm));

		// ��ʼ�������б�
		service.getInitList(request,myForm,"dbsx");
		// ��ͷ
		request.setAttribute("path", "newsInfo.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		//��ȡtopTr(��ͷ)
		service.getTop(request, myForm);
		request.setAttribute("mklx", myForm.getMklx());
		request.setAttribute("xmList", service.getDbsxXmList(myForm));
		return mapping.findForward("dbsxInfo");
	}

	/**
	 * ��ʾ��Ŀ��Ϣ�޸�(��ҳ �������Ӻ���ϵ��ʽά��) author ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ϵͳά��-ϵͳ����-��ϵ��ʽ-�޸�XMDM:{xmdm},XMMC:{xmmc},XMNR:{xmnr},SFXS:{sfxs}")
	public ActionForward xsxmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyszService service = new SyszService();
		XszzCommService xszzComm = new XszzCommService();
		SyszForm myForm = (SyszForm) form;

		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tabName = "xg_sysz_xsxmb";
		// �����Ӳ��� ��ѯ��¼
		if (!"add".equalsIgnoreCase(doType)) {
			this.selectPageDataByOne(request, tabName, tabName, pkValue);
		} else {
			request.setAttribute("xmdm", xszzComm.getXmbh(tabName, "xmdm"));
		}

		if ("save".equalsIgnoreCase(doType)) {
			this.insertOperation(request, tabName);
		}

		if ("modi".equalsIgnoreCase(doType)) {
			this.updateOperation(request, tabName);
		}

		// ������ʽ
		request.setAttribute("doType", doType);
		// ��ʼ�������б�
		service.getInitList(request,myForm,"xsxm");
		// ·��
		request.setAttribute("path", "xtwh_xsxmwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxmUpdate");
	}

	/**
	 * ��ʾ��Ŀ��Ϣ(��ҳ �������Ӻ���ϵ��ʽ) author ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SystemLog(description="����ϵͳά��-ϵͳ����-��ϵ��ʽ-����XMDM:{xmdm},XMMC:{xmmc},XMNR:{xmnr},SFXS:{sfxs},��ɾ��VALUES:{primarykey_}")
	public ActionForward xsxmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BasicService basicService = new BasicService();
		SyszForm myForm = (SyszForm) form;
		SyszService service = new SyszService();
		String doType = request.getParameter("doType");
		String tabName = "xg_sysz_xsxmb";

		// �������棨�Ƿ���ʾ��
		if ("save".equalsIgnoreCase(doType)) {
			// ���ز������
			request.setAttribute("result", service.updateSfxs(myForm));
		}

		// ɾ������
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, tabName);
		}

		// ��ѯ
//		if ("query".equalsIgnoreCase(doType)) {�����������жϸ��ֶΣ�����˵�Ĭ��ִ�в�ѯ����--20121225--zhanghui
		
		//2013-1-10 ȥ����������  qph
			String[] outputColumn = { "pkValue", "xmmc", "xmnr", "xsfs",
					"sfxs", "xssx" };
			this.selectPageDataByPagination(request, form, tabName, tabName,
					outputColumn);
//		}
		
		
		request.setAttribute("topTr",basicService.getTopTr(tabName,new String[]{"���","��Ŀ����","��Ŀ����","�Ƿ���ʾ","��ʾ˳��"}));
		// ��ʼ���б�
		service.getInitList(request,myForm,"xsxm");
		request.setAttribute("path", "xtwh_xsxmwh.do");
		//
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxmManage");
	}

	// =======================����made by ��������=====================
}