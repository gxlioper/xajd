package xsgzgl.gygl.ldgl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class LdglAction extends BasicAction{
	
	private LdglServices service=new LdglServices();
	
	/**
	 * ¥������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward ldglManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		throws Exception{
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		request.setAttribute("path", "gyglnew_ldgl_ldgl.do");
		
		String doType=Base.chgNull(request.getParameter("doType"), "",0);
		//ɾ��¥����Ϣ
		if("delete".equals(doType)){
			boolean b=service.deleteLdxx(request, model);
			request.setAttribute("result", b);
		}
		
		List<String[]> rsList=service.getLdglInfoList(model);
		
		request.setAttribute("rsList", rsList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("topTr", service.getTopTr(model));
		request.setAttribute("colnum", service.getTopTr(model).length);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tableName", "xg_gygl_new_ldxxb");
		request.setAttribute("realTable", "xg_gygl_new_ldxxb");
		request.setAttribute("xxdm", Base.xxdm);
				
		return mapping.findForward("success");
	}
	
	/**
	 * ¥����Ϣ�����Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ldxxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getLdglInfoExportList(model);
		
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
	
	/**
	 * ¥������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-¥����Ϣ����-�޸�LDDM:{lddm},LDMC:{ldmc}")
	public ActionForward ldxxwh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String doType=Base.chgNull(request.getParameter("doType"), "",0);
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		request.setAttribute("xqbj", GyglNewInit.XQBJ);//У�����
		request.setAttribute("yqbj", GyglNewInit.YQBJ);//԰�����
		request.setAttribute("xqlist", service.getXqList());//У���б�
		request.setAttribute("yqlist", service.getYqList(""));//԰���б�
		
		
		if("add".equals(doType)){//����
			HashMap<String, String> rs=new HashMap<String, String>();
			rs.put("ldxb", "��");	//Ĭ��¥���Ա�Ϊ��
			rs.put("qsch", "1");	//Ĭ����ʼ���Ϊ1
			request.setAttribute("rs", rs);
		}else if("save".equals(doType)){//���ӱ���
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean b=service.saveLdxx(request, model);
			request.setAttribute("result", b);
		}else if("update".equals(doType)){//�޸�
			HashMap<String,String> rs=service.getLdxxOne(model);//¥���Ļ�����Ϣ
			List<HashMap<String,String>> rstj=service.getLdtjxx(model);//¥�������ͳ����Ϣ
			request.setAttribute("rs", rs);
			request.setAttribute("rstj", rstj);
			request.setAttribute("ldcsList",service.getLdcsList());
			return mapping.findForward("update");
		}else if("modi".equals(doType)){//�޸ı���
			boolean b=service.updateLdxx(request, model);
			request.setAttribute("result", b);
		}else{//�鿴������Ϣ
			HashMap<String,String> rs=service.getLdxxOne(model);
			request.setAttribute("rs", rs);
		}
		request.setAttribute("ldcsList",service.getLdcsList());//¥�������б�
		request.setAttribute("doType", doType);
		this.saveToken(request);
		return mapping.findForward("add");
	}
	
	/**
	 * ¥���������Ԣ����Ա
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-¥����Ϣ����-���乫Ԣ����ԱLDDM:{lddm}")
	public ActionForward ldfpUpdate(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String doType = request.getParameter("doType");
		
		LdglServices service = new LdglServices();
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		if("fp".equalsIgnoreCase(doType)){
			request.getSession().setAttribute("fpld", myForm.getCheckVal());
		}else if("save".equalsIgnoreCase(doType)){
			String[] yhs = request.getParameterValues("primarykey_cbv");
			String[] lds = (String[])request.getSession().getAttribute("fpld");
			
			// ���渨��Ա����
			String message = service.saveFdyFp(yhs, lds) ? "����ɹ���" : "����ʧ�ܣ�";
			
			// ��session��ȥ�������¥��
			request.getSession().removeAttribute("fpld");
			request.setAttribute("message", message);
		}
		
		try {
			request.setAttribute("topTr", service.getTopTr("yh"));
			request.setAttribute("rs", service.queryYh(myForm));
			
			FormModleCommon.requestSetList(new String[]{"bm"}, request);
			request.setAttribute("pageSize", myForm.getPages().getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ldfpUpdate");
	}
	
	/**
	 * ¥���������Ԣ����Ա���㽭����ְҵ����ѧԺ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ldfpUpdate_12861(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String doType = request.getParameter("doType");
		
		LdglServices service = new LdglServices();
		
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		if("fp".equalsIgnoreCase(doType)){
			request.getSession().setAttribute("fpld_12861", myForm.getCheckVal());
		}else if("save".equalsIgnoreCase(doType)){
			String[] yhs = request.getParameterValues("primarykey_cbv");
			String[] lds = (String[])request.getSession().getAttribute("fpld_12861");
			
			// ���渨��Ա����
			String message = service.saveFdyFp_12861(yhs, lds) ? "����ɹ���" : "����ʧ�ܣ�";
			
			// ��session��ȥ�������¥��
			request.getSession().removeAttribute("fpld_12861");
			request.setAttribute("message", message);
		}
		
		try {
			request.setAttribute("topTr", service.getTopTr("yh"));
			request.setAttribute("rs", service.queryYh(myForm));
			
			FormModleCommon.requestSetList(new String[]{"bm"}, request);
			request.setAttribute("pageSize", myForm.getPages().getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("ldfpUpdate_12861");
	}
	
	/**
	 * ����԰����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadYqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xqdm = request.getParameter("xqdm");
		
//		LdglServices service = new LdglServices();
//		LdglModel model = new LdglModel();
//		model.setLddm(lddm);
		
		List<HashMap<String, String>> list = service.getYqList(xqdm);
		if(list==null||list.size()==0){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size();i++){
			sb.append("<option value='"+list.get(i).get("yqdm")+"'>");
			sb.append(list.get(i).get("yqmc"));
			sb.append("</option>");
		}
//		String json = JSONObject.fromObject(list).toString();
		
//		response.setCharacterEncoding("gbk");
		response.getWriter().write(sb.toString());

		return null;
	}
	
	/**
	 * ¥������������Ϣ����ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Դ����-¥����Ϣ����-����ά��LDDM:{lddm}")
	public ActionForward ldglQsxxplwh(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		LdglForm myForm=(LdglForm)form;
		LdglModel model=new LdglModel();
		BeanUtils.copyProperties(myForm,model);
		
		boolean sfkxg=service.checkLdQsxxSfkxg(myForm);
		if(!sfkxg){
			String msg = "��¥�������һ�λ�ѷ��������ס�����ɽ����޸ģ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		String doType = request.getParameter("doType");
		if("save".equals(doType)){//�����޸ĵ���Ϣ
			service.saveLdQsxx(request, model);
		}
		
		request.setAttribute("ldchqs_list", service.getLdqsxxList(myForm));
		request.setAttribute("max_qss", service.getChMaxQss(myForm));
		request.setAttribute("ldxx", service.getLdxxOne(model));
		return mapping.findForward("ldglQsxxplwh");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ¥�����Ա�����ѯ��д��ͨ�á�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-24 ����10:31:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ldfpUpdateNew(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LdglForm myForm=(LdglForm)form;
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			LdglServices service = new LdglServices();
			List<HashMap<String, String>> resultList = service.getFpryList(user,  myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String[] ldArry = myForm.getCheckVal();
		request.setAttribute("ldArry",ldArry);
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("searchTj", searchModel);
		String path = "gyglnew_ldgl.do?method=ldfpUpdateNew";
		request.setAttribute("path", path);
		boolean isShow = ldArry.length > 1 ? false :true;
		request.setAttribute("isShow", isShow+"");
		return mapping.findForward("ldfpUpdateNew");
	}
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-25 ����11:55:38
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
	public ActionForward saveFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LdglForm myForm=(LdglForm)form;
		LdglServices service = new LdglServices();
		boolean rs = service.saveLdfp(myForm);
		String message = rs ? "����ɹ���" :"����ʧ�ܣ�";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����: ȡ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-25 ����04:54:46
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
	public ActionForward cancelFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		LdglForm myForm=(LdglForm)form;
		LdglServices service = new LdglServices();
		boolean rs = service.cancelLdfp(myForm);
		String message = rs ? "ȡ���ɹ���" :"ȡ��ʧ�ܣ�";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * ��message��װΪjson����
	 * @param message
	 * @return
	 */
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	

}
