package com.zfsoft.xgxt.xsxx.jcsjwh;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������:��������ά�����꼶ѧԺרҵ�༶��
 * @���ߣ� qilm
 * @ʱ�䣺 2013-12-5
 * @�汾�� V1.0
 */
public class JcsjAction extends SuperAction {
	
	private static final String url = "jcsjcl.do";
	
	private JcsjService service = new JcsjService();

	/**
	 * @����:���������б�
	 * @���ߣ� qilm
	 * @ʱ�䣺 2013-9-27
	 * @�汾�� V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward jcsjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JcsjForm myForm = (JcsjForm) form;
		User user = getUser(request);
		String xzFlg = myForm.getXzflg();
		String path = "";

		if(xzFlg == null || "".equals(xzFlg) || "0".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=0";
		}else if("1".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=1";
		}else if("2".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=2";
		}
		if (QUERY.equals(myForm.getType())) {
			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath(path);
			myForm.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		// ============= ��ʼ����������ֵ ============
		//XsxxtyglInit init = new XsxxtyglInit();
		RequestForm rForm = new RequestForm();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		//String path = "xsxx_tygl_cxfzxs.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		//init.initFzxsSearch(rForm, user, request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		request.setAttribute("path", "jcsjcl.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", path);
		return mapping.findForward("jcsjList");
	}

	/**
	 * ���ӻ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧԺרҵ�༶ά��-����XZFLG:{xzflg},XYDM:{xydm},XYMC:{xymc},BMLB:{bmlb},XYDMBJ:{xydmbj},ZYDM:{zydm},XYDMZY:{xydmzy},ZYMC:{zymc},BJDM:{bjdm},NJ:{nj},BJMC:{bjmc},ZYDMBJ:{zydmbj},XYDMBJ:{xydmbj}")
	public ActionForward jcsjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcsjForm myForm = (JcsjForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			String messageKey = service.saveJcsj(myForm);			
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		request.setAttribute("bmlbList", service.getBmlbList());
		request.setAttribute("xyList", service.getXyList());
		request.setAttribute("zyList", service.getZyList(""));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jcsjAdd");
	}

	/**
	 * �޸Ļ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧԺרҵ�༶ά��-�޸�XYDMOLD:{xydmold},XYDM:{xydm},XYMC:{xymc},BMLB:{bmlb},XYDMBJ:{xydmbj},ZYDM:{zydm},XYDMZY:{xydmzy},ZYMC:{zymc},BJDM:{bjdm},NJ:{nj},BJMC:{bjmc},ZYDMBJ:{zydmbj},XYDMBJ:{xydmbj}")
	public ActionForward jcsjUpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcsjForm myForm = (JcsjForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			String messageKey = service.updJcsj(myForm);
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		JcsjForm model = service.getModel(myForm);
		model.setXzflg(myForm.getXzflg());
		request.setAttribute("bmlbList", service.getBmlbList());
		request.setAttribute("xyList", service.getXyList());
		request.setAttribute("zyList", service.getZyList(""));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jcsjUpd");
	}
	
	/**
	 * ɾ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-ѧԺרҵ�༶ά��-ɾ��VALUES:{values}")
	public ActionForward jcsjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xzflg = request.getParameter("xzflg");
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num =  service.runDelete(xzflg, values.split(","));
			String message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @����: �������ݵ���
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcsjForm model = (JcsjForm) form;
		JcsjService service = new JcsjService();

		String xzFlg = model.getXzflg();
		String path = "";

		if(xzFlg == null || "".equals(xzFlg) || "0".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=0";
		}else if("1".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=1";
		}else if("2".equals(xzFlg)){
			path = "jcsjcl.do?xzflg=2";
		}
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath(path);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllListGet(model, user);// ��ѯ�����м�¼������ҳ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @����: ѧԺרҵ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����11:40:21
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward onChangXyZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String xydm = request.getParameter("xydm");
		List<HashMap<String, String>> zyList = service.getZyList(xydm);
		JSONArray dataList = JSONArray.fromObject(zyList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @����: �꼶����[ѧԺ/רҵ/�༶]
	 *        ���꼶Ϊ�գ���ѧԺ/רҵ/�༶ȫ�������б��༶��ա�
	 *        ���꼶��Ϊ�գ���ѧԺ/רҵ/�༶ȫ�������б��༶��ա������ú�רҵ�������б�������ա�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����11:40:21
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward onChangJcsjNj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		// �꼶
		String nj = request.getParameter("nj");
		// �Ƿ�������ݷ�Χ sfkzSjfw:�Ƿ�������ݷ�Χ[0:����;1:������]	
		String sfkzSjfw = request.getParameter("sfkzSjfw");
		// �Ƿ���У�������ݼ�sfzx:�Ƿ���У��Χ[0:��Уview_njxyzybj;1:����Уview_njxyzybj_all]
		String sfzx = request.getParameter("sfzx");		
		User user = getUser(request);

		JcsjForm model = new JcsjForm();
		model.setNj(nj);
		model.setXydm("");
		model.setZydm("");
		model.setBjdm("");
		model.setSfkzSjfw(sfkzSjfw);
		model.setSfzx(sfzx);
		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("0");
		// ѧԺ�б�
		List<HashMap<String, String>> xyList = service.getAllList(model, user);

		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("1");
		// רҵ�б�
		List<HashMap<String, String>> zyList = service.getAllList(model, user);
		
		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("2");
		// �༶�б�
		List<HashMap<String, String>> bjList = service.getAllList(model, user);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("xyList", xyList);
		map.put("zyList", zyList);
		map.put("bjList", bjList);				
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @����: ѧԺ����[�꼶/רҵ/�༶]
	 *        ���꼶Ϊ�գ���ѧԺ/רҵ/�༶ȫ�������б��༶��ա�
	 *        ���꼶��Ϊ�գ���ѧԺ/רҵ/�༶ȫ�������б��༶��ա������ú�רҵ�������б�������ա�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����11:40:21
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward onChangJcsjXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		// �꼶
		String nj = request.getParameter("nj");
		// �Ƿ�������ݷ�Χ sfkzSjfw:�Ƿ�������ݷ�Χ[0:����;1:������]	
		String sfkzSjfw = request.getParameter("sfkzSjfw");
		// �Ƿ���У�������ݼ�sfzx:�Ƿ���У��Χ[0:��Уview_njxyzybj;1:����Уview_njxyzybj_all]
		String sfzx = request.getParameter("sfzx");		
		User user = getUser(request);

		JcsjForm model = new JcsjForm();
		model.setNj(nj);
		model.setXydm(request.getParameter("xydm"));
		model.setZydm("");
		model.setBjdm("");
		model.setSfkzSjfw(sfkzSjfw);
		model.setSfzx(sfzx);
		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("3");
		// �꼶�б�
		List<HashMap<String, String>> njList = service.getAllList(model, user);

		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("1");
		// רҵ�б�
		List<HashMap<String, String>> zyList = service.getAllList(model, user);
		
		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("2");
		// �༶�б�
		List<HashMap<String, String>> bjList = service.getAllList(model, user);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("njList", njList);
		map.put("zyList", zyList);
		map.put("bjList", bjList);				
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	

	/**
	 * @throws IOException 
	 * 
	 * @����: רҵ����[�꼶/ѧԺ/�༶]
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����11:40:21
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward onChangJcsjZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		// �꼶
		String nj = request.getParameter("nj");
		// �Ƿ�������ݷ�Χ sfkzSjfw:�Ƿ�������ݷ�Χ[0:����;1:������]	
		String sfkzSjfw = request.getParameter("sfkzSjfw");
		// �Ƿ���У�������ݼ�sfzx:�Ƿ���У��Χ[0:��Уview_njxyzybj;1:����Уview_njxyzybj_all]
		String sfzx = request.getParameter("sfzx");		
		User user = getUser(request);

		JcsjForm model = new JcsjForm();
		model.setNj(nj);
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm("");
		model.setSfkzSjfw(sfkzSjfw);
		model.setSfzx(sfzx);
		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("3");
		// �꼶�б�
		List<HashMap<String, String>> njList = service.getAllList(model, user);

		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("0");
		// רҵ�б�
		List<HashMap<String, String>> xyList = service.getAllList(model, user);
		
		//ѡ��FLG(0��ѧԺ�б�1��רҵ�б�2���༶�б�  3���꼶�б�) 
		model.setXzflg("2");
		// �༶�б�
		List<HashMap<String, String>> bjList = service.getAllList(model, user);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("njList", njList);
		map.put("xyList", xyList);
		map.put("bjList", bjList);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
}
