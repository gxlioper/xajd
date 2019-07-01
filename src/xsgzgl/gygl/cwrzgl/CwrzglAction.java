package xsgzgl.gygl.cwrzgl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.comm.GyglNewInit;

public class CwrzglAction extends BasicAction {
	/**
	 * ��λ��ס����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cwrzglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwrzgl_cwrz.do");
		HttpSession session=request.getSession();
		boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("fdyQx")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
		}
		if(session.getAttribute("bzrQx")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		}
		if(("bjdm".equals(GyglNewInit.CWFPDX)||"zydm".equals(GyglNewInit.CWFPDX))&&(isFdy||isBzr)){//�������䣬����Ա�������
			return cwrzglManage_xy(mapping, form, request, response);
		}else if("xydm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr)){//�������䣬�Ǹ���Ա�������
			 String msg = "��û�б�ģ��Ĳ���Ȩ�ޣ���ȷ�ϣ�";
			   request.setAttribute("yhInfo", msg);
			   return new ActionForward("/yhInfo.do", false);
		}
		CwrzglForm myForm = (CwrzglForm) form;
		CwrzglService service = new CwrzglService();
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// �������ʾ�ֶ�
		String[] colList = new String[]{"�꼶",Base.YXPZXY_KEY,"�Ա�","������","����ס����","δ��ס����","�ܴ�λ��","����ס��λ��","δ��ס��λ��"};

		// =============== ִ�в�ѯ���� ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});
		
		rsArrList = service.getCwrzglInfoList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	/**
	 * ��λ��ס�ֶ�����	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-��λ��ס����-{doType}ά��CWH:{checkbox_cwh}")
	public ActionForward cwrzglSdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		HttpSession session=request.getSession();
		boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("fdyQx")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
		}
		if(session.getAttribute("bzrQx")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		}
		if("bjdm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr)){//����Ա�������
			return cwrzglSdfp_xy(mapping, form, request, response);
		}else if(("xydm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr))){//�������䣬�Ǹ���Ա�������
			 String msg = "��û�б�ģ��Ĳ���Ȩ�ޣ���ȷ�ϣ�";
			   request.setAttribute("yhInfo", msg);
			   return new ActionForward("/yhInfo.do", false);
		}else if("zydm".equals(GyglNewInit.CWFPDX)&&(isFdy||isBzr)){//����Ա�������
			return cwrzglSdfp_zy(mapping, form, request, response);
		}
		
//		HttpSession session = request.getSession();
		String rzsj=(String)session.getAttribute("temp_gygl_cwrz_rzsj");//��סʱ��
		CwrzglForm myForm = (CwrzglForm) form;
		if(Base.isNull(myForm.getRzsj())){
			myForm.setRzsj(rzsj);
			myForm.setTsyy((String)session.getAttribute("temp_gygl_cwrz_tsyy"));
			myForm.setBz((String)session.getAttribute("temp_gygl_cwrz_bz"));
		}else{
			session.setAttribute("temp_gygl_cwrz_rzsj", myForm.getRzsj());
			session.setAttribute("temp_gygl_cwrz_tsyy", myForm.getTsyy());
			session.setAttribute("temp_gygl_cwrz_bz", myForm.getBz());
		}
		
		CwrzglService service = new CwrzglService();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		
		//�����Ļ�ȡ������
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//����"ѧԺ_�꼶_�Ա�"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setXb(temp[2]);
			myForm.setRzqk("δ��ס");
			myForm.setCwzt("δ��ס");
		}
		
		//¥���б�
		List<HashMap<String,String>> ldlist=service.getLdList(myForm);
		if(ldlist!=null&&ldlist.size()==1){//��ֻ��һ�������¥������ֱ��ѡ�в�ѯ
			myForm.setLddm(ldlist.get(0).get("lddm"));
		}
		
		
		//���Ҵ�λ����ı�����ȡ��
		boolean isKqxrz=service.isKqxrz(myForm);//�Ƿ��ȡ����ס
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//����ѧ����ס��Ϣ
			boolean b=service.saveXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)&&isKqxrz){//ȡ�����Ҵ�λ����
			boolean b=service.qxXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== ִ�в�ѯ���� ===========
		if(!Base.isNull(myForm.getLddm())){//¥����Ϊ��ʱ���в�ѯ
			service.tjldFpxx(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
			request.setAttribute("xsxxlist", new ArrayList<String[]>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", ldlist);//¥���б�
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		request.setAttribute("tsyylist", service.getTsyyList());
		request.setAttribute("rzyylist", service.getRzyyList());
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo(myForm));//ѧԺ�꼶������������ס����
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx(myForm));//ѧԺ�꼶�Ա�¥��ͳ�����
		
		FormModleCommon.setNjXyZyBjList(request);
//		request.setAttribute("njList", Base.getNjList()) ;//�꼶
//		request.setAttribute("xyList", Base.getXyList()) ;//ѧԺ
//		request.setAttribute("zyList", Base.getZyMap().get(myForm.getXydm()));
//		request.setAttribute("bjList", Base.getBjMap().get(myForm.getXydm()+"!!"+myForm.getZydm()+"!!"+myForm.getNj()));
		request.setAttribute("isKqxrz", isKqxrz);
		request.setAttribute("act", request.getParameter("act"));

		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		request.setAttribute("xxdm", Base.xxdm);
		
		//�½�ҽ�ƴ�ѧ��ѧԺ���Ի�
		if("13560".equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("yzlbList", service.getYzlbList());
		}
		
		return mapping.findForward("sdfp");
	}
	
	/** 
	 * @����:(��λ���䵽רҵʱ������Ա�û�����ѧ����ס����)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-9-5 ����03:20:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-��λ��ס����-{doType}ά��CWH:{checkbox_cwh}")
	private ActionForward cwrzglSdfp_zy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		HttpSession session = request.getSession();
		String rzsj=(String)session.getAttribute("temp_gygl_cwrz_rzsj");//��סʱ��
		CwrzglForm myForm = (CwrzglForm) form;
		if(Base.isNull(myForm.getRzsj())){
			myForm.setRzsj(rzsj);
			myForm.setTsyy((String)session.getAttribute("temp_gygl_cwrz_tsyy"));
			myForm.setBz((String)session.getAttribute("temp_gygl_cwrz_bz"));
		}else{
			session.setAttribute("temp_gygl_cwrz_rzsj", myForm.getRzsj());
			session.setAttribute("temp_gygl_cwrz_tsyy", myForm.getTsyy());
			session.setAttribute("temp_gygl_cwrz_bz", myForm.getBz());
		}
		
		CwrzglService service = new CwrzglService();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		
		//�����Ļ�ȡ������
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//����"ѧԺ_�꼶_�Ա�"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setBjdm(temp[3]);
			myForm.setXb(temp[4]);
			myForm.setRzqk("δ��ס");
			myForm.setCwzt("δ��ס");
		}else{
			myForm.setNj(request.getParameter("hidden_nj"));
			myForm.setXydm(request.getParameter("hidden_xydm"));
		}
		
		//¥���б�
		List<HashMap<String,String>> ldlist=service.getLdList_zy(myForm);
		if(ldlist!=null&&ldlist.size()==1){//��ֻ��һ�������¥������ֱ��ѡ�в�ѯ
			myForm.setLddm(ldlist.get(0).get("lddm"));
		}
		
		
		//���Ҵ�λ����ı�����ȡ��
		boolean isKqxrz=service.isKqxrz(myForm);//�Ƿ��ȡ����ס
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//����ѧ����ס��Ϣ
			boolean b=service.saveXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)&&isKqxrz){//ȡ�����Ҵ�λ����
			boolean b=service.qxXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== ִ�в�ѯ���� ===========
		if(!Base.isNull(myForm.getLddm())){//¥����Ϊ��ʱ���в�ѯ
			service.tjldFpxx_zy(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
			request.setAttribute("xsxxlist", new ArrayList<String[]>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", ldlist);//¥���б�
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		request.setAttribute("tsyylist", service.getTsyyList());
		request.setAttribute("rzyylist", service.getRzyyList());
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo_xy(myForm));//ѧԺ�꼶������������ס����
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx_zy(myForm));//ѧԺ�꼶�Ա�¥��ͳ�����
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
//		request.setAttribute("njList", Base.getNjList()) ;//�꼶
//		request.setAttribute("xyList", Base.getXyList()) ;//ѧԺ
//		request.setAttribute("zyList", Base.getZyMap().get(myForm.getXydm()));
//		request.setAttribute("bjList", Base.getBjMap().get(myForm.getXydm()+"!!"+myForm.getZydm()+"!!"+myForm.getNj()));
		request.setAttribute("isKqxrz", isKqxrz);
		request.setAttribute("user", getUser(request));
		request.setAttribute("act", request.getParameter("act"));
		boolean isFdy=(Boolean)session.getAttribute("isFdy");
		boolean isBzr=(Boolean)session.getAttribute("isBzr");
		request.setAttribute("isFdy", isFdy);
		request.setAttribute("isBzr", isBzr);
		
		return mapping.findForward("sdfp_zy");
	}
	/** 
	 * ת�����ݼ��͵���ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-��λ��ס����-����TABLENAME:{tableName}")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		String tableName = request.getParameter("tableName");//��ͼ��
		String realTable = request.getParameter("realTable");//����
		
		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		
		String act=request.getParameter("act");
		//��������
		if("import".equals(act)){
			uploadFile(mapping, form, request, response);
			
			CwrzglService service = new CwrzglService();
			String back= service.importData(request,response);//��������
			String sfdcExcel=(String)request.getAttribute("sfdcExcel");
			if("yes".equals(sfdcExcel)){
				return mapping.findForward("");
			}
			request.setAttribute("back", back);
			
		}
		return mapping.findForward("importData");
	}
	
	/**
	 * �ļ��ϴ� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-��λ��ס����-�ϴ�FNAME:{userName}")
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//�ô���Ҫ��֤��������ԱȨ��
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		CwrzglForm hff = (CwrzglForm) form;
		FormFile file = hff.getImpFilePath();	
//		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
//			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
//		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
	
	//#############################################����ΪѧԺ����(��δ�޸�)
	/**
	 * ��λ��ס����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cwrzglManage_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwrzgl_cwrz.do");
		CwrzglForm myForm = (CwrzglForm) form;
		CwrzglService service = new CwrzglService();
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// �������ʾ�ֶ�
		String[] colList = new String[]{"�꼶","�༶","�Ա�","������","����ס����","δ��ס����","�ܴ�λ��","����ס��λ��","δ��ס��λ��"};

		// =============== ִ�в�ѯ���� ===========
				
		rsArrList = service.getCwfpglInfoList_xy(request,myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "gyglnew_cwrzgl_cwrz_xy.do");
		// ================= end =====================

		return mapping.findForward("success_xy");
	}
	/**
	 * ��λ��ס�ֶ�����	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-��λ��ס����ѧԺ��-{doType}ά��CWH:{checkbox_cwh}")
	public ActionForward cwrzglSdfp_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		HttpSession session = request.getSession();
		String rzsj=(String)session.getAttribute("temp_gygl_cwrz_rzsj");//��סʱ��
		CwrzglForm myForm = (CwrzglForm) form;
		if(Base.isNull(myForm.getRzsj())){
			myForm.setRzsj(rzsj);
			myForm.setTsyy((String)session.getAttribute("temp_gygl_cwrz_tsyy"));
			myForm.setBz((String)session.getAttribute("temp_gygl_cwrz_bz"));
		}else{
			session.setAttribute("temp_gygl_cwrz_rzsj", myForm.getRzsj());
			session.setAttribute("temp_gygl_cwrz_tsyy", myForm.getTsyy());
			session.setAttribute("temp_gygl_cwrz_bz", myForm.getBz());
		}
		
		CwrzglService service = new CwrzglService();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		
		//�����Ļ�ȡ������
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//����"ѧԺ_�꼶_�Ա�"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setBjdm(temp[3]);
			myForm.setXb(temp[4]);
			myForm.setRzqk("δ��ס");
			myForm.setCwzt("δ��ס");
		}else{
			myForm.setNj(request.getParameter("hidden_nj"));
			myForm.setXydm(request.getParameter("hidden_xydm"));
		}
		
		//¥���б�
		List<HashMap<String,String>> ldlist=service.getLdList_xy(myForm);
		if(ldlist!=null&&ldlist.size()==1){//��ֻ��һ�������¥������ֱ��ѡ�в�ѯ
			myForm.setLddm(ldlist.get(0).get("lddm"));
		}
		
		
		//���Ҵ�λ����ı�����ȡ��
		boolean isKqxrz=service.isKqxrz(myForm);//�Ƿ��ȡ����ס
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//����ѧ����ס��Ϣ
			boolean b=service.saveXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)&&isKqxrz){//ȡ�����Ҵ�λ����
			boolean b=service.qxXsrzfpxx(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== ִ�в�ѯ���� ===========
		if(!Base.isNull(myForm.getLddm())){//¥����Ϊ��ʱ���в�ѯ
			service.tjldFpxx_xy(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
			request.setAttribute("xsxxlist", new ArrayList<String[]>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", ldlist);//¥���б�
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		request.setAttribute("tsyylist", service.getTsyyList());
		request.setAttribute("rzyylist", service.getRzyyList());
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo_xy(myForm));//ѧԺ�꼶������������ס����
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx_xy(myForm));//ѧԺ�꼶�Ա�¥��ͳ�����
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
//		request.setAttribute("njList", Base.getNjList()) ;//�꼶
//		request.setAttribute("xyList", Base.getXyList()) ;//ѧԺ
//		request.setAttribute("zyList", Base.getZyMap().get(myForm.getXydm()));
//		request.setAttribute("bjList", Base.getBjMap().get(myForm.getXydm()+"!!"+myForm.getZydm()+"!!"+myForm.getNj()));
		request.setAttribute("isKqxrz", isKqxrz);
		request.setAttribute("user", getUser(request));
		request.setAttribute("act", request.getParameter("act"));
		boolean isFdy=(Boolean)session.getAttribute("isFdy");
		boolean isBzr=(Boolean)session.getAttribute("isBzr");
		request.setAttribute("isFdy", isFdy);
		request.setAttribute("isBzr", isBzr);
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		return mapping.findForward("sdfp_xy");
	}
}
