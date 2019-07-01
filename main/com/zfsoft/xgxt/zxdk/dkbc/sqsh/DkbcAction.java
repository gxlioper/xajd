/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:36:20 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.sqsh;

import java.io.File;
import java.util.ArrayList;
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
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.zxdk.dkbc.cssz.DkbcCsszService;
import com.zfsoft.xgxt.zxdk.dkbc.tjsz.TjszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-12 ����09:36:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DkbcAction extends SuperAuditAction<DkbcModel, DkbcService> {

	protected DkbcAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public DkbcAction(){
		this("zxdk_dkbc", "zxdk_jcjy_bcsq.do", "zxdk_jcjy_bcsh.do");
	}
	
	
	/**�����ҳ��**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do")
	public ActionForward dkbcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcCsszService csszService = new DkbcCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "zxdk_jcjy_bcsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dkbcList");
	}
	
	
	/**�������ѯ**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do")
	public ActionForward getDkbcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcModel model = (DkbcModel) form;
		DkbcService service = new DkbcService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**���������**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcModel model = (DkbcModel) form;
		User user = getUser(request);
		DkbcService service = new DkbcService();
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
		
		if (!StringUtil.isNull(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			model.setXh(xh);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		
		DkbcCsszService csszService = new DkbcCsszService();
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("yhlist",service.getYhList());
		request.setAttribute("yjxflist", service.getXfList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "dkbc_sqsh.do?method=bcsq");
		request.setAttribute("dclblist", service.getDclbList());
		this.saveToken(request);
		return mapping.findForward("bcsq");
	}
	
	/**
	 * 
	 * @����: �������������ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-8 ����04:17:56
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
	public ActionForward getXmsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmdm = "JCJYDKBC";
		DkbcModel myForm = (DkbcModel) form;
		String xh = myForm.getXh();
		if(!StringUtil.isNull(xh)) {
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(xmdm,xh);	
			//У������������У����
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(xh, conditions);
			JSONArray dataList = JSONArray.fromObject(results);
			response.getWriter().print(dataList);
		}
		
		return null;
	}
	
	/**������޸�**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		DkbcModel model = service.getModel(djjdForm);
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("yhlist",service.getYhList());
		request.setAttribute("yjxflist", service.getXfList());
		request.setAttribute("dclblist", service.getDclbList());
		this.saveToken(request);
		return mapping.findForward("xgsq");
	}
	
	
	/**ȡ������**/
	@SystemAuth(url = "zxdk_jcjy_bcsq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ѧ����-�����ҵ-��������-ɾ��values:{id}")
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		
		boolean result = service.runDelete(new String[]{djjdForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ��������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:37:30
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
	@SystemAuth(url = "zxdk_jcjy_bcsh.do")
	public ActionForward bcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_jcjy_bcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bcshList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ش�������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:39:06
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
	@SystemAuth(url = "zxdk_jcjy_bcsh.do")
	public ActionForward getBcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel model = (DkbcModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**��������**/
	@SystemAuth(url = "zxdk_jcjy_bcsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		DkbcModel model = service.getModel(djjdForm.getId());
		String shid = request.getParameter("shid");
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dclblist", service.getDclbList());
		request.setAttribute("shid", shid);
		return mapping.findForward("bcsh");
	}
	
	
	/**�鿴����*/
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		
		DkbcModel model = service.getModel(djjdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("jcjybcdc");
		request.setAttribute("dclblist", service.getDclbList());
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("cksq");
	}
	
	/**��ӡ�����**/
	public ActionForward printSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			String[] ids = request.getParameter("ids").split(",");
			
			if(null!=ids && ids.length == 1){//һ������
				File file=print(ids[0],request);
				FileUtil.outputWord(response, file);
			}else{//��������
				List<File> files = new ArrayList<File>();
				for(String id : ids){
					File file=print(id,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			}
			return null;
	}
	
	private synchronized File print(String id,HttpServletRequest request) throws Exception{
		String mbmc = "zxdk_dcsqb.xml";
		Map<String, Object> data = new HashMap<String, Object>();
		
		DkbcService service = new DkbcService();
		DkbcModel model = service.getModel(id);
		
		data.put("m_byny", DateTranCnDate.fomartDateToCn(model.getBysj(),FomartDateType.month));// ��ҵ����
		data.put("m", model);
		data.put("date", DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));// ��ҵ����
		
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		data.put("j", xsjbxx);
		String xh = xsjbxx.get("xh");
		
		if("10264".equals(Base.xxdm)){ //�Ϻ�ˮ����ѧ
			mbmc = "zxdk_dcsqb_10264.xml";
			// ��ͥ���
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm); 
			if (jtqkmodel == null) {
				jtqkmodel = new JtqkdcForm();
			}
			data.put("jtqkmodel", jtqkmodel);
//			// �����
//			PjjgService pjjgService = new PjjgService();
//			List<HashMap<String, String>> pjjgList =  pjjgService.getPjpyInfoMap(xh);
//			pjjgService.addBlankList(pjjgList, 2 - pjjgList.size());
//			data.put("pjjgList", pjjgList);
			// ����ѧ�������϶����
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, "", "");
			String rddcmc = knsjg.get("dcmc")==null?"":knsjg.get("dcmc");
			data.put("rddcmcFlag", String.valueOf(rddcmc.matches(".*��.*��.*")));// �Ƿ�����
		}
		
		return FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", mbmc, FreeMarkerUtil.getFileName(xsjbxx.get("xh") +"["+xsjbxx.get("xm")+"]"));
	}
	
	
	/**��ѧ��ѧ���ѯ�Ƿ�������**/
	public ActionForward getCountByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DkbcModel djjdForm = (DkbcModel) form;
		DkbcService service = new DkbcService();
		
		String count = service.getCountByXhAndXn(djjdForm);
		response.getWriter().print(count);
		return null;
	}
	
	//�������͸ı�
	public ActionForward dklxChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		String dklx = request.getParameter("dklx");
		String xn = request.getParameter("xn");
		DkbcService service = new DkbcService();
		HashMap<String, String> datamap = service.produceDataMap(xh, dklx, xn);
		
		JSONObject json = JSONObject.fromObject(datamap);
		response.getWriter().print(json);
		return null;
		
	}
	
	/**
	 * 
	 * @����: ���������������д
	 * @���ߣ� yxy[����:1206]
	 * @���ڣ�2014��6��9�� ����9:51:00
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
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		DkbcModel model = (DkbcModel) form;
		DkbcService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����: ���ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-3 ����02:26:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	@Override
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		DkbcModel model = (DkbcModel) form;
		DkbcService service = getService();
		boolean isSuccess = false;
		//���������¼��Ӧ�����״̬������С�
		model.setShzt(Constants.YW_SHZ);
		
		//��֤����ID�����ID��һ���ԣ���ϵͳ����ΨһID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//���������¼
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//�ύ���뵽�������
			message = submit("zxdk_dkbc" , model.getId(), "zxdk_jcjy_bcsq.do", "zxdk_jcjy_bcsh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����: ���ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-3 ����02:26:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
	throws Exception {

		ShlcInterface shlc = new CommShlcImpl();
		
		DkbcService service = getService();
		//��ѯ�����¼
		DkbcModel model = service.getModel(id);
		String splcid = model.getSplcid();
		//�ύ��������
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//���������¼״̬
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		
		return getJsonMessage(message);
	}
}
