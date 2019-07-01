/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-7 ����05:17:25 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.sqsh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.zxdk.dkbc.sqsh.DkbcService;
import com.zfsoft.xgxt.zxdk.rwfbybc.cssz.RwfbyCsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-9-7 ����05:17:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwfbysqshAction extends SuperAuditAction<RwfbysqshModel, RwfbysqshService> {
    
	 private RwfbysqshService service = new RwfbysqshService();
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param gnid
	 * @param squrl
	 * @param shurl
	 */
	protected RwfbysqshAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}
	
	public RwfbysqshAction(){
		this("rwfby_sqsh","zxdk_rwfby_dcsq.do","zxdk_rwfby_dcsh.do");
	}
	/**
	 * 
	 * @����:�������������
	 * @���ߣ� ChenQ[����:856]
	 * @���ڣ�2015-9-7 ����05:25:08
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
	@SystemAuth(url = "zxdk_rwfby_dcsq.do")
	public ActionForward dcsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    RwfbyCsszService csszService = new RwfbyCsszService();
		    request.setAttribute("cssz", csszService.getModel());
		    request.setAttribute("path", "zxdk_rwfby_dcsq.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("dcsqList");
	}
	
	/**
	 * 
	 * @����:�������������
	 * @���ߣ� ChenQ[����:856]
	 * @���ڣ�2015-9-7 ����05:25:08
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
	@SystemAuth(url = "zxdk_rwfby_dcsq.do")
	public ActionForward getDcsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel model = (RwfbysqshModel) form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	@SystemAuth(url = "zxdk_rwfby_dcsq.do")
	public ActionForward dcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel model = (RwfbysqshModel) form;
		User user = getUser(request);
		String xh = "stu".equals(user.getUserType()) ? user.getUserName(): model.getXh();
		if (!StringUtil.isNull(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("rxrq", xsjbxx.get("rxrq"));
			model.setXh(xh);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		RwfbyCsszService cssz = new RwfbyCsszService();
		request.setAttribute("cssz", cssz.getModel());
		DkbcService dkdcservice = new DkbcService();
		request.setAttribute("yhlist",dkdcservice.getYhList());
		request.setAttribute("yjxflist", dkdcservice.getXfList());
		request.setAttribute("dclblist", new DkbcService().getDclbList());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("path", "rwfby_sqsh.do?method=dcsq");
		this.saveToken(request);
		return mapping.findForward("dcsq");
	}
	
	@SystemAuth(url = "zxdk_rwfby_dcsq.do")
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel myForm = (RwfbysqshModel) form;
		RwfbysqshModel model = service.getModel(myForm.getId());
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkdcservice = new DkbcService();
		request.setAttribute("yhlist",dkdcservice.getYhList());
		request.setAttribute("yjxflist", dkdcservice.getXfList());
		request.setAttribute("dclblist", new DkbcService().getDclbList());
		return mapping.findForward("xgsq");
	}
	
	@SystemAuth(url = "zxdk_rwfby_dcsq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ѧ����-��������۲�������-��������-ɾ��values:{id}")
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel myForm = (RwfbysqshModel) form;
		boolean result = service.runDelete(new String[]{myForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = "zxdk_rwfby_dcsh.do")
	public ActionForward dcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "zxdk_rwfby_dcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dcshList");
	}
	
	@SystemAuth(url = "zxdk_rwfby_dcsh.do")
	public ActionForward getDcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel model = (RwfbysqshModel) form;
	    User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = service.getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
		
	}
	
	/**��������**/
	@SystemAuth(url = "zxdk_rwfby_dcsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel myForm = (RwfbysqshModel) form;
		RwfbysqshModel model = service.getModel(myForm.getId());
		String shid = request.getParameter("shid");
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkdcservice = new DkbcService();
		request.setAttribute("dclblist", dkdcservice.getDclbList());
		request.setAttribute("shid", shid);
		return mapping.findForward("dcsh");
	}
	
	/**�鿴����*/
	@SystemAuth(url = "zxdk_rwfby_dcsh.do")
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel myForm = (RwfbysqshModel) form;
		RwfbysqshModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("rwfbydc");
		request.setAttribute("jbxxList", jbxxList);
		DkbcService dkdcservice = new DkbcService();
		request.setAttribute("dclblist", dkdcservice.getDclbList());
		return mapping.findForward("cksq");
	}
	
	/**
	 * 
	 * @����:��֤�Ƿ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-7 ����06:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public ActionForward isExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel myForm = (RwfbysqshModel) form;
		boolean flag = service.isExists(myForm);
		response.getWriter().print(flag);
		return null;
	}
	
	/**
	 * 
	 * @����: ����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-9-7 ����06:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
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
		RwfbysqshModel model = (RwfbysqshModel) form;
		RwfbysqshService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}

	
	/**
	 * 
	 * @����:�ǼǱ�����
	 * @���ߣ�lgx[����:1553]
	 * @���ڣ�2018-5-15 ����17:33:55
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
	
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbysqshModel modelForm = (RwfbysqshModel) form;
		RwfbysqshModel model = service.getModel(modelForm.getId());
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		RwfbysqshService service = new RwfbysqshService();
		RwfbysqshModel model = null;
		RwfbysqshModel modelForm = null;
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				modelForm = new RwfbysqshModel();
				modelForm.setId(values[i]);
				model = service.getModel(modelForm);
				File file = getWord(model);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
	//���ģ����������word�ļ�
	private File getWord(RwfbysqshModel rwfbysqshModel)
			throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		RwfbysqshService service = new RwfbysqshService();
		RwfbysqshModel model = rwfbysqshModel;
		String xh = model.getXh();
		//������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
		JtqkdcService jtqkService = new JtqkdcService();
		List<HashMap<String, String>> jtcyxxList= jtqkService.getJtcyList(xh);
		String fq = "";
		String mq = "";
		String qtcy = "";
		for (HashMap<String, String> map : jtcyxxList) {
			if("����".equals(map.get("cygxmc"))){
				fq = map.get("cyxm")+" " ;
				fq += (map.get("cylxdh") == null ? "" : map.get("cylxdh"));
			}else if("ĸ��".equals(map.get("cygxmc"))){
				mq = map.get("cyxm") + " ";
				mq += (map.get("cylxdh") == null ? "": map.get("cylxdh"));
			}else{
				qtcy +=  map.get("cyxm") + " " ;
				qtcy += map.get("cylxdh") == null ? "" : map.get("cylxdh");
				qtcy += "   ";
			}
		}
		HashMap< String, String> syddk = service.getSyddkxx(model.getId());
		data.put("syddk", syddk);
		HashMap< String, String> xyddk = service.getXyddkxx(model.getId());
		data.put("xyddk", xyddk);
		data.put("dkbj", model.getDkbj());
		data.put("fq", fq);
		data.put("mq", mq);
		data.put("qtcy", qtcy);
		data.put("yx",Base.xxmc);
		data.put("xsjbxx", xsjbxx);
		data.put("model", model);
		File wordFile = null;
		wordFile = FreeMarkerUtil.getWordFile(data,  Constants.TEP_DIR + "yzrw","yzrwxfbcsqb_12688.xml", model.getXh() +"["+xsjbxx.get("xm")+"]" + "-");
		return wordFile;
		
	}
}
