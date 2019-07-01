package xsgzgl.gygl.cwfpgl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

public class CwfpglAction extends BasicAction {
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward cwfpglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		User user = getUser(request);// �û�����
		HttpSession session=request.getSession();
		boolean isFdy=false;
		boolean isBzr=false;
		if(session.getAttribute("fdyQx")!=null){
			isFdy=Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
		}
		if(session.getAttribute("bzrQx")!=null){
			isBzr=Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		}
	
		// ���䵽ѧԺ
		if(isFdy||isBzr|| ("xydm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType()))){//����ѧԺ�˺���û��Ȩ��
			 String msg = null;
			 if("xydm".equals(GyglNewInit.CWFPDX)){//ֻ���䵽ѧԺ
				 msg=Base.YXPZXY_KEY+"�������κ͸���Ա��û�иò���Ȩ�޵ģ���ȷ�ϣ�";
			 }else{//���䵽�༶
				 msg="�����κ͸���Ա��û�иò���Ȩ�޵ģ���ȷ�ϣ�";
			 }
			 request.setAttribute("yhInfo", msg);
			 return new ActionForward("/yhInfo.do", false);
		}
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		RequestForm rForm = new RequestForm();
		// ��ȡ��ʾ�����(ѧԺ:xyli,רҵ��zyli,�༶��bjli)
		String resultSet = request.getParameter("resultSet")== null? "":request.getParameter("resultSet").toString();
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ(��λ�������)
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		//Ĭ����ʾ
		String[] colList = null;
		
		//ѧУ�û� ���� ��λ�������Ϊ��ѧԺ��Ĭ����ʾѧԺ�б�
		// ѧԺ�û��л�ѧԺ�����ʱ��ʾ
		if("xydm".equals(cwfpdx) || ("".equalsIgnoreCase(resultSet) && ("xx".equals(user.getUserType())
				||"admin".equals(user.getUserType())))
				|| "xyli".equalsIgnoreCase(resultSet)){

			colList = new String[]{"�꼶",Base.YXPZXY_KEY,"�Ա�","������","�ѷ��䴲λ��","�ѷ���������","�ѷ���¥����"};
			
			// ȡ���꼶ѧԺͳ��
			rsArrList = service.getCwfpglInfoList(myForm,request);
			// �����л��������ʼֵ
			request.setAttribute("resultSet", "xyli");
			// ���ô�λ�������
			request.setAttribute("cwfpdx", cwfpdx);
			
			//��λ�������Ϊ��רҵ ��ΪѧԺ�û�
			//�л������Ϊרҵ
		}else if(("zydm".equals(cwfpdx) && "".equalsIgnoreCase(resultSet) && "xy".equalsIgnoreCase(user.getUserType()))
				|| "zyli".equalsIgnoreCase(resultSet)){

			// ѧԺ�û�����ѧԺ����
			if("xy".equalsIgnoreCase(user.getUserType())){
				String xydm=user.getUserDep();//xydm
				myForm.setXydm(xydm);
			}
			
			colList = new String[]{"�꼶",Base.YXPZXY_KEY,"רҵ","�Ա�","������","�ѷ��䴲λ��","�ѷ���������","�ѷ���¥����"};

			// ȡ���꼶ѧԺרҵͳ��
			rsArrList = service.getCwfpglInfoList_zy(myForm);
			// �����л��������ʼֵ
			request.setAttribute("resultSet", "zyli");
			// ���ô�λ�������
			request.setAttribute("cwfpdx", cwfpdx);
			
			//��λ�������Ϊ���༶ ��ΪѧԺ�û�
			//�л������Ϊ�༶
		}else if(("bjdm".equals(cwfpdx) && "".equalsIgnoreCase(resultSet) && "xy".equalsIgnoreCase(user.getUserType()))
				|| "bjli".equalsIgnoreCase(resultSet)){
			
			// ѧԺ�û�����ѧԺ����
			if("xy".equalsIgnoreCase(user.getUserType())){
				String xydm=user.getUserDep();//xydm
				myForm.setXydm(xydm);
			}
			
			colList = new String[]{"�꼶",Base.YXPZXY_KEY,"רҵ","�༶","�Ա�","������","�ѷ��䴲λ��","�ѷ���������","�ѷ���¥����"};
			
			// ȡ���꼶ѧԺרҵ�༶ͳ��
			rsArrList = service.getCwfpglInfoList_xy(myForm);
			// �����л��������ʼֵ
			request.setAttribute("resultSet", "bjli");
			// ���ô�λ�������
			request.setAttribute("cwfpdx", cwfpdx);
		}
		// =============== ִ�в�ѯ���� ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});

		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		request.setAttribute("userType", user.getUserType());
		service.setRequestValue(rForm, user,request);
		
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	
	/**
	 * ��λ�������--�ֶ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-��λ�������-{resultSet}[{doType}]ά��QSH:{checkbox_qsh}")
	public ActionForward cwfpglSdfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		User user = getUser(request);// �û�����
		
//		if("bjdm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType())){//ѧԺ�û�
//			return cwfpglSdfp_xy(mapping, form, request, response);
//		}else if("xydm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType())){//����ѧԺ�˺���û��Ȩ��
//			 String msg = "��ģ��ֻ����ѧУ�û����в�������ȷ�ϣ�";
//			 request.setAttribute("yhInfo", msg);
//			 return new ActionForward("/yhInfo.do", false);
//		}
		
		if("xydm".equals(GyglNewInit.CWFPDX)&&"xy".equals(user.getUserType())){//����ѧԺ�˺���û��Ȩ��
			 String msg = "��ģ��ֻ����ѧУ�û����в�������ȷ�ϣ�";
			 request.setAttribute("yhInfo", msg);
			 return new ActionForward("/yhInfo.do", false);
		}

		CwfpglForm myForm = (CwfpglForm) form;
		//¥��
		myForm.setLddm(request.getParameter("lddm"));
		//ȡ���Ԅe
		String xb = request.getParameter("xb");
		if(StringUtils.isNotNull(xb)){
			if("1".equalsIgnoreCase(xb)){
				myForm.setXb("��");
			}else if ("2".equalsIgnoreCase(xb)){
				myForm.setXb("Ů");
			}
		}
		//ȡ���꼶
		String nj = request.getParameter("nj");
		myForm.setNj(nj);
		//ȡ��ѧԺ
		String xydm = request.getParameter("xydm");
		myForm.setXydm(xydm);
		
		//ȡ���Ԅe
		String xbdm = request.getParameter("xbdm");
		if (xbdm != null && !"".equalsIgnoreCase(xbdm)) {
			if("1".equalsIgnoreCase(xbdm)){
				myForm.setXb("��");
				myForm.setLddm(null);
			}else if ("2".equalsIgnoreCase(xbdm)){
				myForm.setXb("Ů");
				myForm.setLddm(null);
			}
		}
		
		CwfpglService service = new CwfpglService();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// ��ȡ��ʾ�����(ѧԺ:xyli,רҵ��zyli,�༶��bjli)
		String resultSet = request.getParameter("resultSet")== null? "xyli":request.getParameter("resultSet").toString();
		
		// �����Ļ�ȡ������
		String act = request.getParameter("act");
		
		// ��ȡ����
		// ѧԺ�����������"ѧԺ_�꼶_�Ա�"��
		// רҵ�����������"ѧԺ_�꼶_רҵ_�Ա�"��
		// �༶�����������"ѧԺ_�꼶_רҵ_�༶_�Ա�"
		String primarykey_checkVal = request
				.getParameter("primarykey_checkVal");
		
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp = primarykey_checkVal.split("_");
			
			// ѧԺ�����
			if("xyli".equalsIgnoreCase(resultSet)){
				myForm.setXydm(temp[0]);
				myForm.setNj(temp[1]);
				myForm.setXb(temp[2]);
				
				// רҵ�����
			}else if("zyli".equalsIgnoreCase(resultSet)){				
				myForm.setXydm(temp[0]);
				myForm.setNj(temp[1]);
				myForm.setZydm(temp[2]);
				myForm.setXb(temp[3]);
				
				// �༶�����
			}else if("bjli".equalsIgnoreCase(resultSet)){				
				myForm.setXydm(temp[0]);
				myForm.setNj(temp[1]);
				myForm.setZydm(temp[2]);
				myForm.setBjdm(temp[3]);
				myForm.setXb(temp[4]);
			}
			
			if("to_fp".equals(act)){
				myForm.setCwzt("δ����");
			}else{
				myForm.setCwzt("�ѷ���");	
			}
		}
		String pageqh=request.getParameter("pageqh");
		if("pageqh".equals(pageqh)){
			if("to_fp".equals(act)){
				myForm.setCwzt("δ����");
			}else{
				myForm.setCwzt("�ѷ���");	
			}
		}
		
		//���Ҵ�λ����ı�����ȡ��
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//�������Ҵ�λ����
			boolean b = false;
			
			// ѧԺ�����
			if ("xyli".equalsIgnoreCase(resultSet)) {

				// ��ѧԺ����
				b = service.saveQscwfpxx(request, myForm);
				// רҵ�����
			} else if ("zyli".equalsIgnoreCase(resultSet)) {

				// �����I����
				b = service.saveQscwfpxx_zy(request, myForm);

				// �༶�����
			} else if ("bjli".equalsIgnoreCase(resultSet)) {

				// ���༶����
				b = service.saveQscwfpxx_xy(request, myForm);
			}
			
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)){//ȡ�����Ҵ�λ����
			boolean b = false;
			// ѧԺ�����
			if ("xyli".equalsIgnoreCase(resultSet)) {

				// ��ѧԺ����
				b = service.qxQscwfpxx(request, myForm);
				// רҵ�����
			} else if ("zyli".equalsIgnoreCase(resultSet)) {

				// �����I����
				b = service.qxQscwfpxx_zy(request, myForm);

				// �༶�����
			} else if ("bjli".equalsIgnoreCase(resultSet)) {

				// ���༶����
				b = service.qxQscwfpxx_xy(request, myForm);
			}
			
			request.setAttribute("back", b);
		}
		
		// =============== ִ�в�ѯ���� ===========
		if(!Base.isNull(myForm.getLddm())){//¥����Ϊ��ʱ���в�ѯ
			// ѧԺ�����
			if("xyli".equalsIgnoreCase(resultSet)){

				service.tjldFpxx(request, myForm);
				
				// רҵ�����
			}else if("zyli".equalsIgnoreCase(resultSet)){
				
				service.tjldFpxx_zy(request, myForm);
	
				// �༶�����
			}else if("bjli".equalsIgnoreCase(resultSet)){
				
				service.tjldFpxx_xy(request, myForm);
			}
		}else{
			//¥��������Ϣ��¥�����롢¥�����ƣ�
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			//¥��¥����Ϣ��¥��ͳ����Ϣ��������/��λ���� δ���䴲λ���� ���䵱ǰ��λ����
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			//¥��¥�����Ҵ�λ��Ϣ����
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
		}
		
		// ================= end =====================
		//ѧԺ�����������¥���б�
		if("xyli".equalsIgnoreCase(resultSet)){

			request.setAttribute("ldlist", service.getLdListByGyfdy(request,myForm.getXb()));//¥���б�
			
			// רҵ�������༶�����������¥���б�
		}else if("zyli".equalsIgnoreCase(resultSet)||"bjli".equalsIgnoreCase(resultSet)){
			
			request.setAttribute("ldlist", service.getCwfpLdListByGyfdy(request,myForm.getXb()));//¥���б�
		}
		
		
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("zydm", myForm.getZydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		
		// ͳ����Ϣ
		HashMap<String,String> xynj_tjxx = new HashMap<String,String>();
		
		// ¥��ͳ����Ϣ
		List<HashMap<String,String>> xynjxbld_tjxx = new ArrayList<HashMap<String,String>>();
		
		// �����л��������ʼֵ
		request.setAttribute("resultSet", resultSet);

		// ���ô�λ�������
		request.setAttribute("cwfpdx", cwfpdx);
		
		// ��λ�������ΪѧԺ  ���� �����ΪѧԺ��ʱ��
		if ("xydm".equalsIgnoreCase(cwfpdx) || 
				("xyli".equalsIgnoreCase(resultSet)&& ("xx".equals(user.getUserType()) || "admin".equals(user.getUserType())))) {
			xynj_tjxx = service.getCwfpglInfo(myForm);
			xynjxbld_tjxx = service.getXynjxbLdfpxx(myForm);
		}else 
		
		// ��λ�������Ϊרҵ
		if ("zydm".equalsIgnoreCase(cwfpdx) || "zyli".equalsIgnoreCase(resultSet)) {
			xynj_tjxx = service.getCwfpglInfo_zy(myForm);
			xynjxbld_tjxx = service.getXynjxbLdfpxx_zy(myForm);
		} else
		
		// ��λ�������Ϊ�༶
		if ("bjdm".equalsIgnoreCase(cwfpdx) || "bjli".equalsIgnoreCase(resultSet)) {
			xynj_tjxx = service.getCwfpglInfo_xy(myForm);
			xynjxbld_tjxx = service.getXynjxbLdfpxx_xy(myForm);
		}
		request.setAttribute("xynj_tjxx", xynj_tjxx);			//������������ס����
		request.setAttribute("xynjxbld_tjxx", xynjxbld_tjxx);	//�Ա�¥��ͳ�����
		FormModleCommon.setCwfpNjXyZyBjList(request,resultSet);				
		request.setAttribute("userType", user.getUserType());			
		request.setAttribute("act", act);
		
		return mapping.findForward("sdfp");
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xiaxia
	 * @���ڣ�2014-9-12 ����03:21:41
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
	public ActionForward cwfpglInitLdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwfpglService service = new CwfpglService();
		List<HashMap<String,String>> ldList = service.cwfpglInitLdList(request);
		try {
			response.setContentType("text/html;charset=gbk");
			JSONArray dataList = JSONArray.fromObject(ldList);
			response.getWriter().print(dataList);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward cwfpglCwfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("cwfp"); 
	}
	
	/**
	 * jquery����¥���б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadLdlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		
		StringBuffer sb=new StringBuffer();
		List<HashMap<String,String>> ldlist=service.loadLdlist(myForm);
		HashMap<String,String> m=null;
		for(int i=0;i<ldlist.size();i++){
			m=ldlist.get(i);
			sb.append(m.get("lddm")+",");
			sb.append(m.get("ldmc")+"");
			if(i<ldlist.size()-1){
				sb.append(";");
			}
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(sb.toString());
		pw.close();
		return null;
	}
	
	/**
	 * ��λ�������ѧԺ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwfpglManage_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		String xydm=user.getUserDep();//xydm
		myForm.setXydm(xydm);
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);

		// �������ʾ�ֶ�
		String[] colList = new String[]{"רҵ","�༶","�Ա�","������","����ס����","δ��ס����","�ܴ�λ��","����ס��λ��","δ��ס��λ��"};

		// =============== ִ�в�ѯ���� ===========
		
//		myForm.getSearchModel().setSearch_tj_nj(new String[]{Base.currNd});
		
		rsArrList = service.getCwfpglInfoList_xy(myForm);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp_xy.do");
		// ================= end =====================

		return mapping.findForward("success_xy");
	}
	
	/**
	 * ��λ�������--�ֶ�����(ѧԺ)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-ס�޹���-��λ�������(ѧԺ)-{doType}ά��CWH:{checkbox_cwh}")
	public ActionForward cwfpglSdfp_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwfpgl_cwfp.do");
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		// ============= ��ʼ����������ֵ ============
		// ��ù�Ԣ���������Ϣ
		String cwfpdx = GyglNewInit.CWFPDX;
		myForm.setCwfpdx(cwfpdx);
		
		//�����Ļ�ȡ������
		String act=request.getParameter("act");
		String primarykey_checkVal=request.getParameter("primarykey_checkVal");//����"ѧԺ_�꼶_רҵ_�༶_�Ա�"
		if(!Base.isNull(primarykey_checkVal)){
			String[] temp=primarykey_checkVal.split("_");
			myForm.setXydm(temp[0]);
			myForm.setNj(temp[1]);
			myForm.setZydm(temp[2]);
			myForm.setBjdm(temp[3]);
			myForm.setXb(temp[4]);
			if("to_fp".equals(act)){
				myForm.setCwzt("δ����");
			}else{
				myForm.setCwzt("�ѷ���");	
			}
		}
		myForm.setXydm(getUser(request).getUserDep());
		String pageqh=request.getParameter("pageqh");
		if("pageqh".equals(pageqh)){
			if("to_fp".equals(act)){
				myForm.setCwzt("δ����");
			}else{
				myForm.setCwzt("�ѷ���");
			}
		}
		
		//���Ҵ�λ����ı�����ȡ��
		String doType=request.getParameter("doType");
		if("save".equals(doType)){//�������Ҵ�λ����
			boolean b=service.saveQscwfpxx_xy(request, myForm);
			request.setAttribute("back", b);
			
		}else if("qxfp".equals(doType)){//ȡ�����Ҵ�λ����
			boolean b=service.qxQscwfpxx_xy(request, myForm);
			request.setAttribute("back", b);
		}
		
		// =============== ִ�в�ѯ���� ===========
		if(!Base.isNull(myForm.getLddm())){//¥����Ϊ��ʱ���в�ѯ
			service.tjldFpxx_xy(request, myForm);
		}else{
			request.setAttribute("ldjbxx", new HashMap<String,String>());
			request.setAttribute("ldlcxx", new ArrayList<HashMap<String,String>>());
			request.setAttribute("ldlcqscwxxb", new ArrayList<HashMap<String,String>>());
		}
		
		// ================= end =====================
		request.setAttribute("ldlist", service.getLdList_xy(myForm));//¥���б�
		HashMap<String,String> rs=new HashMap<String, String>();
		rs.put("lddm", myForm.getLddm());
		rs.put("nj", myForm.getNj());
		rs.put("xydm", myForm.getXydm());
		rs.put("zydm", myForm.getZydm());
		rs.put("bjdm", myForm.getBjdm());
		rs.put("xb", myForm.getXb());
		request.setAttribute("rs", rs);
		
		request.setAttribute("xynj_tjxx", service.getCwfpglInfo_xy(myForm));//ѧԺ�꼶������������ס����
		request.setAttribute("xynjxbld_tjxx", service.getXynjxbLdfpxx_xy(myForm));//ѧԺ�꼶�Ա�¥��ͳ�����
		FormModleCommon.setAllNjXyZyBjList(request);
//		request.setAttribute("njlist", Base.getNjList()) ;//�꼶
//		request.setAttribute("xylist", Base.getXyList()) ;//ѧԺ
		request.setAttribute("act", act);
		
		return mapping.findForward("sdfp_xy");
	}
	
	/**
	 * jquery����¥���б�ѧԺ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadLdlist_xy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwfpglForm myForm = (CwfpglForm) form;
		CwfpglService service = new CwfpglService();
		
		StringBuffer sb=new StringBuffer();
		List<HashMap<String,String>> ldlist=service.loadLdlist_xy(myForm);
		HashMap<String,String> m=null;
		for(int i=0;i<ldlist.size();i++){
			m=ldlist.get(i);
			sb.append(m.get("lddm")+",");
			sb.append(m.get("ldmc")+"");
			if(i<ldlist.size()-1){
				sb.append(";");
			}
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=response.getWriter();
		pw.write(sb.toString());
		pw.close();
		return null;
	}
}
