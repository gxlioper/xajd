/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.gygl.ssyd.ydsq;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����춯����ģ��
 * @�๦������: �����춯����
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-25 ����10:27:32 
 * @�汾�� V1.0
 */
public class YdsqAction extends SuperAction {
	
	private static final String url = "ydsqbase.do";
	
	/**
	 * �Ƿ�����Ո��0[����Ո�е�δ������˵�����]
	 */
	private static final String SFYSQ_YES = "0";
	/**
	 * �Ƿ�����Ո��1[û����Ո�е�δ������˵�����]
	 */
	private static final String SFYSQ_NO = "1";
	
	/**
	 *  ����
	 */
	public static String _YDLX_TS = "00";
	/**
	 *  �������
	 */
	public static String _YDLX_SSTZ = "01";
	/**
	 *  ��ס
	 */
	public static String _YDLX_SSRZ = "03";
	
	/**
	 * ���幫Ԣ���������춯���Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String GYGLSSYD = "gyglssyd";
	private List<HashMap<String,String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();

	/**
	 * 
	 * @����:�����춯�б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("ydsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "ydsqbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("list");
	}


	/**
	 * 
	 * @����:��λ����ѡ�񣨵�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url)
	public ActionForward selectCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdsqForm myForm = (YdsqForm) form;
		YdsqService service = new YdsqService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydsq.do?method=selectCwxx");
			myForm.setSearchModel(searchModel);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getCwxxList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		myForm.setXh(xh);
		String path = "ydsq.do?method=selectCwxx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xh", xh);
		
		return mapping.findForward("selectCwxx");
	}
	
	/**
	 * 
	 * @����:��λ����ѡ����ס��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-14 ����10:49:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 * 
	 */
	@SystemAuth(url = url)
	public ActionForward selectRzcwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		YdsqForm myForm = (YdsqForm) form;
		YdsqService service = new YdsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (QUERY.equals(myForm.getType())) {
			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydsq.do?method=selectRzcwxx");
			myForm.setSearchModel(searchModel);
			
			if (!StringUtil.isNull(myForm.getRzcwxx())){
				request.setAttribute("qsmcCk", service.getInShz(myForm.getRzcwxx())? "0":"1");
//				request.setAttribute("qsmcCk1", service.getSfjl(myForm.getRzcwxx())? "0":"1");
			}
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getRzcwxxList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		myForm.setXh(xh);
		String path = "ydsq.do?method=selectRzcwxx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xh", xh);
		
		return mapping.findForward("selectRzcwxx");
	}
	
	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯����-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����: �����춯ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯����-�޸�PK:{ssydsqid},XH:{xh},SSYDLX:{ssydlx},XN:{xn},XQ:{xq},TSTZYY:{tstzyy},TSTZSJ:{tstzsj}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		
		User user = getUser(request);

		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String result = service.update(myForm,"save");
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("�ô�λ�ѱ�ʹ�ã�"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}else if ("submit".equalsIgnoreCase(myForm.getType())) {
			if (!"stu".equals(user.getUserType())){
				myForm.setTjsqrxm(user.getUserName());
			}		
			String result = service.update(myForm,"submit");
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("�ô�λ�ѱ�ʹ�ã�"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));	
			}
			return null;
		}
		YdsqForm model = service.getModel(myForm);
		//��λ��Ϣ�O��
		if (!StringUtil.isNull(model.getTzhlddm())
				&& !StringUtil.isNull(model.getTzhqsh())
				&& !StringUtil.isNull(model.getTzhcwh())) {
			model.setCwxx(model.getTzhlddm() + "_" + model.getTzhqsh() + "_"
					+ model.getTzhcwh());
		}
		String ssydlx = model.getSsydlx();
		if(ssydlx.equals(YdsqService._YDLX_RZ)){
			//��λ��Ϣ�O��
			if (!StringUtil.isNull(model.getTzqlddm())
					&& !StringUtil.isNull(model.getTzqqsh())
					&& !StringUtil.isNull(model.getTzqcwh())) {
				model.setRzcwxx(model.getTzqlddm() + "_" + model.getTzqqsh() + "_"
						+ model.getTzqcwh());
			}
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		//ѧ��������Ϣ
		String path = "ydsq.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		//����ԭ��
		request.setAttribute("tstzyyList", service.getTstzyy());
		//����ԭ��
		request.setAttribute("tzyyList", service.getTzyy());
		//��סԭ��
		CwglService cwglService = new CwglService();
		request.setAttribute("rzyyList", cwglService.getRzyyList());
		//��λ��Ϣ
		request.setAttribute("cwxxData", StringUtils.formatData(service.getCwxx(model.getTzqlddm(),
				model.getTzqqsh(), model.getTzqcwh())));
		//����ְҵ����ѧԺ���Ի� ��ʾ�����ѧ��ѧ��
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("ydsqxg");
	}
	
	
	/**
	 * 
	 * @����:���������ύ
	 * @���ߣ�945
	 * @���ڣ�2013-11-25 ����04:09:04
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯����-�ύPK:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		User user = getUser(request);
		String values = request.getParameter("values");
		String ydlx = request.getParameter("ydlx");
		String xh = request.getParameter("xh");
		String ssydlx = request.getParameter("ssydlx");
		String lcid = "";
		
		YdsqForm myModel = service.getModel(values);
		
		// ���˻ص�״̬,ȡ�ϵ������
		if(Constants.YW_YTH.equals(myModel.getShzt())){
			lcid = myModel.getSplcid();	
			
		}else{
			//ȡ���µ��������
			ShlcForm sf = service.getShlcForm(ydlx);
			//ShlcForm sf = service.getShlcForm();
			if (null == sf) {
				lcid = "";
			}else{
				lcid = sf.getSplcid();
			}
		}
		// δ�趨���������ʾ
		if(StringUtils.isNull(lcid)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SELECT_SHLC_FAIL));
			return null;
		}
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		boolean result = service.submitRecord(ydlx,values,lcid,xh);
		if(result){
			//����ҵ��״̬Ϊ'�����'
			YdsqForm model = new YdsqForm();
			if (!"stu".equals(user.getUserType())){
				model.setTjsqrxm(user.getUserName());
			}	
			model.setSsydsqid(values);
			model.setShzt(Constants.YW_SHZ);
			model.setSplcid(lcid);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯����-����PK:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			YdsqForm model = new YdsqForm();
			model.setSsydsqid(values);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
/**
 * 
 * @����:����
 * @���ߣ��Ų�·[���ţ�982]
 * @���ڣ�2013-9-17 ����10:44:10
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return  ActionForward �������� 
 * @throws Exception
 *
 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯����-����XH:{xh},SSYDLX:{ssydlx},XN:{xn},XQ:{xq},TSTZYY:{tstzyy},TSTZSJ:{tstzsj}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			// �趨�Ƿ�������
			request.setAttribute("sfysq", service.getSfysq(myForm.getXh())?SFYSQ_YES : SFYSQ_NO);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			String result = service.save(myForm,"save");
			
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("�ô�λ�ѱ�ʹ�ã�"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
			
		}else if ("submit".equalsIgnoreCase(myForm.getType())) {
			if (!"stu".equals(user.getUserType())){
				myForm.setTjsqrxm(user.getUserName());
			}
			String result = service.save(myForm,"submit");
			if("exist_fail".equals(result)){
				response.getWriter().print(getJsonMessage("�ô�λ�ѱ�ʹ�ã�"));
			} else {
				String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
						: "true".equals(result)?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		//get student detail
		//ѧ��������Ϣ
		String path = "ydsq.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		//����ԭ��
		request.setAttribute("tstzyyList", service.getTstzyy());
		//����ԭ��
		request.setAttribute("tzyyList", service.getTzyy());
		//��סԭ��
		CwglService cwglService = new CwglService();
		request.setAttribute("rzyyList", cwglService.getRzyyList());
		request.setAttribute("dqxq",Base.getDqxqmc());
		request.setAttribute("dqxn",Base.currXn);
		//��λ��Ϣ
		request.setAttribute("cwxxData",StringUtils.formatData(service.getCwxxForXh(myForm.getXh())));
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyy-MM-dd"));
		return mapping.findForward("ydsqzj");
	}
	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-17 ����05:23:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		YdsqForm myForm = (YdsqForm) form;
		YdsqForm model = service.getModel(myForm);
		
		// ѧ����Ϣ
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(GYGLSSYD);
		
		
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		request.setAttribute("ssydlx", model.getSsydlx());
		
		// ԭ��λ��Ϣ
		request.setAttribute("cwxxData", service.getCwxx(model.getTzqlddm(),
				model.getTzqqsh(), model.getTzqcwh()));
		// ======== ��ѯ������ begin ========
		HashMap<String, String> cwxxYdjg = service.getCwxxYdjg(model.getXh(), model.getSsydsqid());
		//���������������õ�������
		if(model.getSsydlx().equals(_YDLX_SSTZ)){
			if(!StringUtil.isNull(cwxxYdjg.get("ydqlddm"))){
				model.setTzqlddm(cwxxYdjg.get("ydqlddm")); 
				model.setTzqldmc(cwxxYdjg.get("ydqldmc")); 
				model.setTzqqsh(cwxxYdjg.get("ydqqsh")); 
				model.setTzqcwh(cwxxYdjg.get("ydqcwh")); 
			}
			if(!StringUtil.isNull(cwxxYdjg.get("ydhlddm"))){
				model.setTzhlddm(cwxxYdjg.get("ydhlddm")); 
				model.setTzhldmc(cwxxYdjg.get("ydhldmc")); 
				model.setTzhqsh(cwxxYdjg.get("ydhqsh")); 
				model.setTzhcwh(cwxxYdjg.get("ydhcwh")); 
			}
		}else if(model.getSsydlx().equals(_YDLX_SSRZ)){//��ס����
			if(!StringUtil.isNull(cwxxYdjg.get("ydhlddm"))){
				model.setTzqlddm(cwxxYdjg.get("ydhlddm")); 
				model.setTzqldmc(cwxxYdjg.get("ydhldmc")); 
				model.setTzqqsh(cwxxYdjg.get("ydhqsh")); 
				model.setTzqcwh(cwxxYdjg.get("ydhcwh")); 
			}
		}
		// ======== ��ѯ������ end ========
		return mapping.findForward("ydsqck");
	}
	

	/**
	 * 
	 * @����: �����춯���뵼��
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-12
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
		YdsqForm model = (YdsqForm) form;
		YdsqService service = new YdsqService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// ��ѯ�����м�¼������ҳ

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
	 * 
	 * @���� ����֪ͨ����ӡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-5 ����01:33:31
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printTstzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdsqService service = new YdsqService();
		String ssydsqid = request.getParameter("ssydsqid");
		if(ssydsqid != null && ssydsqid.split(",").length == 1){	/*-->���ص������*/
			HashMap<String , String> data = service.getYdsqxx(ssydsqid);
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
			objectData.putAll(data);
			File file = null;
			String xxdm = Base.xxdm;
			String guid = "gygl_tstzd_"+xxdm;
			file = BbdmUtils.getSigleFile(guid, objectData);
			if(null==file){
				//ͨ�õǼǱ�
				file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
			}
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String ssydsqids:ssydsqid.split(",")){
				HashMap<String , String> data = service.getYdsqxx(ssydsqids);
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				data.put("tstzsj", DateTranCnDate.fomartDateToCn(data.get("tstzsj")));
				objectData.putAll(data);
				File file = null;
				String xxdm = Base.xxdm;
				String guid = "gygl_tstzd_"+xxdm;
				file = BbdmUtils.getSigleFile(guid, objectData);
				if(null==file){
					//ͨ�õǼǱ�
					file = BbdmUtils.getSigleFile("gygl_tstzd", objectData);
				}
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
}
