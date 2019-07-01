package xgxt.qtsj.gdby;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;

import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xsh.XshService;

import com.zfsoft.basic.BasicAction;

/**
 * Title: ѧ����������ϵͳ Description:У԰�Ļ�����Action Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-7-14
 */
public class XywhglAction extends BasicAction {

	/**
	 * У԰�Ļ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String tablename = "gdby_xywhglb";
		XywhglForm myForm = (XywhglForm) form;
		XywhglModel model = new XywhglModel();
		XywhglService service = new XywhglService();

		BeanUtils.copyProperties(model, myForm);
		
		// ��������еı���ʱ
		if ("save".equalsIgnoreCase(doType)) {
			// �����ļ��ϴ�
			FormFile file = myForm.getUploadFile();
			
			// �ϴ��ļ�
			String filePath = uploadFile(request, file);
			
			model.setFj(filePath);
			
			BaseDAO dao = new BaseDAO();

			if (dao.checkExists(tablename, "hddm", model.getHddm())) {
				service.delFile(model.getHddm());
				request.setAttribute("msg", "У԰������ظ������޸ĺ��ύ");
			} else if (service.insertRocord(model)) {
				request.setAttribute("msg", "�����ɹ�");
			} else {
				service.delFile(model.getHddm());
				request.setAttribute("msg", "����ʧ��");
			}
			
		}

		request.setAttribute("fbxx", service.getXywhxxOne(model.getHddm()));
		loadInfo(request, "gdby_xywhgl.do?method=xywhfb");
		return mapping.findForward("xywhfb");
	}

	/**
	 * У԰�Ļ�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhsqforuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XywhglService xywhglService = new XywhglService();

		XywhglForm xForm = (XywhglForm) form;
		XywhglModel model = new XywhglModel();
		BeanUtils.copyProperties(model, xForm);

		List<HashMap<String, String>> xywhList = xywhglService.getXywhList();
		request.setAttribute("rs", xywhList);
		loadInfo(request, "gdby_xywhgl.do?method=xywhsqforuser");
		return mapping.findForward("xywhsqforuser");
	}

	/**
	 * ���嵥��ĳ����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String hddm = request.getParameter("hddm");
		String doType = request.getParameter("doType");

		String tablename = "gdby_xywhsqb";
		XywhglForm xForm = (XywhglForm) form;
		XywhglModel model = new XywhglModel();
		BeanUtils.copyProperties(model, xForm);

		XywhglService xywhglService = new XywhglService();
		XshService xshService = new XshService();

		setCdAndWz(request, hddm);
		// �Ƿ����ų�Ա
		boolean sfstcy = false;
		// �Ƿ������
		boolean isApply = true;
		List<HashMap<String, String>> list = xshService.getStInfo(userName);
		
		// ֻ��ѧԺ�û����������û��������ʸ�
		if ("xy".equalsIgnoreCase(userType) || (list !=null && list.size()>0)) {
			// ���ų�Ա���룬��ش���	
			if (list != null && list.size() > 0) {
				List<String> stList = new ArrayList<String>();
				for (HashMap<String, String> map : list) {
					stList.add(map.get("stmc"));
				}
				sfstcy = true;
				request.setAttribute("stList", stList);
			}
		}else {
			isApply = false;
			// �����ʸ��ʶ
			request.setAttribute("msg", "��û������Ȩ��");
		}
		
		String pkValue = userNameReal + hddm;
		
		if(xywhglService.isAuditing(pkValue)){
			isApply = false;
			request.setAttribute("msg", "�ѱ����ͨ������������");
		}
		
		// �ھ�������ҳ����,������水ť
		if ("add".equalsIgnoreCase(doType) && isApply) {

			BaseDAO dao = new BaseDAO();
			String pkv = model.getUsername() + model.getHddm();

			boolean isSuccess = false;
			if (dao.checkExists(tablename, "username||hddm", pkv)) {
				isSuccess = xywhglService.updateSqb(model);
			} else {
				isSuccess = xywhglService.insertSqb(model);
			}

			if (isSuccess) {
				request.setAttribute("msg", "�����ɹ�");
			} else {
				request.setAttribute("msg", "����ʧ��");
			}
		}
		
		// ��ȡ���Ϣ
		Map<String, String> hdxx = xywhglService.getXywhxxOne(hddm);
		request.setAttribute("hdxx", hdxx);
		// ��ȡ������Ϣ
		Map<String, String> sqxx = xywhglService.getSqxxOne(pkValue);
		request.setAttribute("sqxx", sqxx);

		loadInfo(request, "gdby_xywhgl.do?method=xywhsqforuser");
		request.setAttribute("sfstcy", sfstcy);
		request.setAttribute("isApply", isApply);
		return mapping.findForward("xywhsq");
	}

	/**
	 * У԰�Ļ���鿴�޸�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhViewAndModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String doType = request.getParameter("doType");
		String hddm = request.getParameter("hddm");
		
		XywhglForm xForm = (XywhglForm)form;
		XywhglModel model = new XywhglModel();
		BeanUtils.copyProperties(model, xForm);
		
		XywhglService service = new XywhglService();

		

		if("modi".equalsIgnoreCase(doType)){
			request.setAttribute("option", "modi");
		}
		
		if ("view".equalsIgnoreCase(doType)) {
			request.setAttribute("option", "view");
		}
		
		if("update".equalsIgnoreCase(doType)){
			request.setAttribute("option","modi");
			
			String filePath = uploadFile(request, model.getUploadFile());
			model.setFj(filePath);
			if(service.updateRecord(model)){
				request.setAttribute("msg", "�����ɹ�");
			}else{
				service.delFile(hddm);
				request.setAttribute("msg", "����ʧ��");
			}
		}

		Map<String, String> map = service.getXywhxxOne(hddm);
		request.setAttribute("rs", map);
		return mapping.findForward("xywhview");

	}
	
	/**
	 * У԰�Ļ������鿴���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhsqViewAndModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		if("view".equalsIgnoreCase(doType)){
			request.setAttribute("option", "view");
		}
		
		if("modi".equalsIgnoreCase(doType)){
			String hddm = request.getParameter("hddm");
			setCdAndWz(request, hddm);
			request.setAttribute("option", "modi");
		}
		
		XywhglService service = new XywhglService();
		Map<String, String> map = service.getSqxxOne(pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xywhshone");
	}
		
	/**
	 * У԰�Ļ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String go = request.getParameter("go");
		String tableName = "gdby_xywhsqb";
		String doType = request.getParameter("doType");
		XywhglService service = new XywhglService();
	
		XywhglForm xForm = (XywhglForm)form;
		
		
		if("sh".equalsIgnoreCase(doType)){
			String shjg = request.getParameter("shjg");
			String shzd = "xxsh";
			String shsj = "xxshsj";
			
			if(!StringUtils.isNull(shjg)){
				shjg = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : "��ͨ��";
			}
			
			// ��ȡҳ������primarykey_Ϊ��ʼ������
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
			valueMap.put(shsj, GetTime.getNowTime());
			
			// ͨ����˷���
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","hddm","hdmc","username","lxdh","xxsh"};
			this.selectPageDataByPagination(request, xForm, tableName, tableName, outputColumn);
		}

		
		// ���ҳ���еĻ����
		List<HashMap<String, String>> whxxList = service.getXywhList();
		
		loadInfo(request, "gdby_xywhgl.do?method=xywhsh");
		
		request.setAttribute("whxxList", whxxList);
		return mapping.findForward("xywhsh");
	}

	/**
	 * У԰�Ļ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhshone(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		XywhglService service = new XywhglService();
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			XywhglForm xForm = (XywhglForm)form;
			XywhglModel model = new XywhglModel();
			BeanUtils.copyProperties(model, xForm);
			
			if(service.updateSqb(model)){
				request.setAttribute("msg", "�����ɹ�");
			}else{
				request.setAttribute("msg", "����ʧ��");
			}
		}
		
		Map<String, String> map = service.getSqxxOne(pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("option", "shone");
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("nowtime", GetTime.getNowTime());
		return mapping.findForward("xywhshone");
	}
	
	/**
	 * У԰�Ļ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhcx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
		String userName = session.getAttribute("userName").toString();
		String go = request.getParameter("go");
		String tableName = "gdby_xywhsqb";
		String doType = request.getParameter("doType");
		XywhglService service = new XywhglService();
		String dest = "xywhcx";
		
		XywhglForm xForm = (XywhglForm)form;
		
		XshService xshService = new XshService();
		
		List<HashMap<String, String>> sts = xshService.getStInfo(userName);
		
		if("xy".equalsIgnoreCase(userType) || (sts != null && sts.size()>0)){
			dest = "xywhcxforuser";
			request.setAttribute("rs", service.getSqxxByUser(userNameReal));
		}
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue","hddm","hdmc","username","lxdh","xxsh"};
			this.selectPageDataByPagination(request, xForm, tableName, tableName, outputColumn);
		}
		
		// ���ҳ���еĻ����
		List<HashMap<String, String>> hdxxList = service.getXywhList();
		
		loadInfo(request, "gdby_xywhgl.do?method=xywhcx");
		
		request.setAttribute("hdxxList", hdxxList);
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", tableName);
		return mapping.findForward(dest);
	}
	
	/**
	 * У԰�Ļ�ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = "gdby_xywhglb";
		String doType = request.getParameter("doType");
		XywhglForm xForm = (XywhglForm)form;
		XywhglService service = new XywhglService();
		
		// ����ɾ����ͬʱɾ���йظ���
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = xForm.getPrimarykey_cbv();
			if(service.delRecord(pkValues)){
				request.setAttribute("msg", "�����ɹ�");
			}else{
				request.setAttribute("msg", "����ʧ��");
			}
			
		}
		
		String[] outputColumn = new String[]{"hddm","hdmc","fj"};
		this.selectPageDataByPagination(request, form, tableName, tableName, outputColumn);
		
		loadInfo(request, "gdby_xywhgl.do?method=xywhwh");
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTableName", tableName);
		return mapping.findForward("xywhwh");
	}
	
	/**
	 * ���Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhExp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = "gdby_xywhsqb;";
		String viewName = "gdby_xywhsqb";
		// ��Ҫ�������ֶ�
			String[] output = {"hddm","hdmc","username","lxdh","cdmc","wzmc","xxsh","xxshyj","xxshsj"};
		
		expPageData(request, response, tableName,viewName, output);
		return mapping.findForward("");
	}
	
	/**
	 * ���ҳ������Ҫ�ĵ�һЩ��Ϣ
	 * 
	 * @param request
	 */
	private void loadInfo(HttpServletRequest request, String path) {
		request.setAttribute("path", path);

		String[] writeAndTitle = FormModleCommon.getWriteAbleAndTitle(request);

		request.setAttribute("writeAble", writeAndTitle[0]);
		request.setAttribute("title", writeAndTitle[1]);
		FormModleCommon.setNjXyZyBjList(request);
	}
		
	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward fileDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XywhglForm myForm = (XywhglForm) form;
		XywhglService service = new XywhglService();
		
		String hddm = myForm.getHddm();
		hddm = Base.isNull(hddm) ? request.getParameter("hddm") : hddm;
		
		if(!Base.isNull(hddm)){
			if(service.delFile(hddm)){
				request.setAttribute("msg", "�����ɹ�");
			}else{
				request.setAttribute("msg", "����ʧ��");
			}
		}
		
		String forward = request.getParameter("forward");
		return new ActionForward(forward);
	}
	
	/**
	 * �ϴ�����
	 * @param request
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String uploadFile(HttpServletRequest request, FormFile file) throws FileNotFoundException, IOException{
		String filePath = "";
		String fName = "";
		if (file != null) {
			String dir = "/upload/qtsj";
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			int size = file.getFileSize();
			if (size < 10485760 && size != 0) {
				fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			} else if(size >= 10485760){
				request.setAttribute("filebig", "(�ϴ��ļ�������С��10M)");
			}
		}
		return filePath;
	}
	
	/**
	 * У԰�Ļ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xywhglExp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = "gdby_xywhglb;";
		String viewName = "gdby_xywhglb";
		// ��Ҫ�������ֶ�
			String[] output = {"hddm","hdmc","hdms","fj"};
		
		expPageData(request, response, tableName,viewName, output);
		return mapping.findForward("");
	}

	/**
	 * ���غ������б�
	 * 
	 * @param request
	 * @param hddm
	 */
	private void setCdAndWz(HttpServletRequest request, String hddm) {
		XywhglService service = new XywhglService();
		request.setAttribute("cdList", service.getCdList(hddm));
		request.setAttribute("wzList", service.getWzList(hddm));
	}
}
