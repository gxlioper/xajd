/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsq;

import java.io.File;
import java.net.URLDecoder;
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
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.rcsw.qjgl.qjxysz.QjXySzService;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:03:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjsqAction extends SuperAction {
	//�����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWRCXW = "rcswqjgl_qjgl";//�� rcswqjgl ��Ϊ rcswqjgl_qjgl
	private BdpzService bdpzService = new BdpzService();
	private QjjgService qjjgService = new QjjgService();
	private List<HashMap<String,String>> jbxxList = null;
	private static int maxSize=5*1024*1024;
	private static String _TJ="tj";//��ʾ�ύ����
	
	private static final String url = "qjsqbase.do";
	/**
	 * 
	 * @����:��ٹ����б��ѯ��ʾ
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
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("qjsqbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "qjsqbase.do";
		request.setAttribute("path", path);
		request.setAttribute("swtjs", service.getWtjs(myForm,user));
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("usertype", user.getUserType());
		QjXySzService qjxysz = new QjXySzService();
		request.setAttribute("qjxy", qjxysz.getQjXySzDada().get("id"));
		return mapping.findForward("qjsqlb");
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
	@SystemLog(description="�����ճ�����-��ٹ���-�������-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
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
	 * @����:�����ύ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-23 ����10:57:09
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
	@SystemLog(description="�����ճ�����-��ٹ���-�������-�ύVALUES:{values}")
	public ActionForward pltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		String values = request.getParameter("values");
		QjsqForm qjsq = (QjsqForm)form ;
		if (!StringUtil.isNull(values)) {
			qjsq.setQjsqid(values);			
			
			// �˻��ύ��ȡ���µ��������ID
			QjsqForm model = service.getModel(values);	
			model.setSsxydm(qjsq.getSsxydm());
			// �����˻صļ�¼������ȡ�������ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				HashMap<String, String> hm = service.getSplc(model);
				if (null==hm) {
					model.setSplcid("�������");
				}else{
					model.setSplcid(hm.get("splcid"));
				}
				qjsq.setSplcid(model.getSplcid());
				//���˻صļ�¼ȡ�ϵ��������ID
			}else{
				qjsq.setSplcid(model.getSplcid());
			}
			
			boolean result = service.tj(qjsq);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;

			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����: ��ٹ���ģ��
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
	@SystemLog(description="�����ճ�����-��ٹ���-�������-�޸�QJSQID:{qjsqid},XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy},QJJS:{qjjs}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		if (!StringUtil.isNull(myForm.getMdd())) {
			myForm.setMdd(URLDecoder.decode(myForm.getMdd(),"UTF-8"));
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//���ó���ʱ�θ��Ի��ж�
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			// �˻��ύ��ȡ���µ��������ID
			QjsqForm model = service.getModel(myForm.getQjsqid());	
			
			// �����˻صļ�¼������ȡ�������ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				HashMap<String, String> hm = service.getSplc(model);
				if (null==hm) {
					model.setSplcid("�������");
				}else{
					model.setSplcid(hm.get("splcid"));
				}
				myForm.setSplcid(model.getSplcid());
				model.setShzt(Constants.YW_WTJ);
				//���˻صļ�¼ȡ�ϵ��������ID
			}else{
				myForm.setSplcid(model.getSplcid());
				myForm.setShzt(Constants.YW_YTH);
			}
			
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			
			/**
			 * ͨ�ù��ܱ�����ύʱ���ڼ��պ���ĩ���͵�ʱ��ѡ����ȷ����֤
			 */
			HashMap<String, String> dataMap = service.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			boolean result = service.update(myForm);
			service.saveQjmx(myForm, mxrq, mxxmList);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("qjzt",myForm.getQjzt());
		request.setAttribute("qjts",myForm.getQjts());
		request.setAttribute("qjlxList",service.getQjlx());
		request.setAttribute("filepath", model.getFilepath());
		request.setAttribute("shzt", model.getShzt());
		
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", service.getQjmxList(myForm.getQjsqid()));
		
		//��ٿγ���Ϣ
		List<HashMap<String,String>> qjkcList = service.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		//ѧ��������Ϣ
		String path = "qjsq.do?method=update";
		request.setAttribute("path", path);

		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("qjsjxsgz", qjjgService.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("isnotList", isnotList);
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//���������ѧ���Ի�
			request.setAttribute("dmList", djService.getDmList());
		}
		return mapping.findForward("qjsqxg");
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
 * @return
 * @throws Exception
 * ActionForward �������� 
 * @throws
 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ٹ���-�������-����XH:{xh},QJTS:{qjts},QJLXID:{qjlxid},KSSJ:{kssj},JSSJ:{jssj},QJSY:{qjsy}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		if (!StringUtil.isNull(myForm.getMdd())) {
			myForm.setMdd(URLDecoder.decode(myForm.getMdd(),"UTF-8"));
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//��ȡ��������ʱ������ѧԺ
			myForm.setSsxydm(xsjbxx.get("xydm"));
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//���ó���ʱ�θ��Ի��ж�
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			if(service.getDupStatusSerivce(myForm)){
				String messageKey =  MessageKey.RCSW_QJGL_CFSQ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			//myForm.setQjsqid(UniqID.getInstance().getUniqIDHash());
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			
			/**
			 * ͨ�ù��ܱ�����ύʱ���ڼ��պ���ĩ���͵�ʱ��ѡ����ȷ����֤
			 */
			HashMap<String, String> dataMap = service.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
			if("false".equals(dataMap.get("rs"))){
				response.getWriter().print(getJsonMessage(dataMap.get("message")));
				return null;
			}
			super.resetToken(request);

			boolean result = service.save(myForm);
			service.saveQjmx(myForm, mxrq, mxxmList);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjlxList",service.getQjlx());
		//get student detail
		//ѧ��������Ϣ
		String path = "qjsq.do?method=add";
		request.setAttribute("path", path);

		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("qjsjxsgz", qjjgService.getCssz().get("qjsjxsgz"));
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("isnotList", isnotList);
		request.setAttribute("usertype", user.getUserType());
		if(Base.xxdm.equals("10695")){
			LxdjService djService = new LxdjService();
			//���������ѧ���Ի�
			request.setAttribute("dmList", djService.getDmList());
		}
		this.saveToken(request);
		return mapping.findForward("qjsqzj");
	}
	/**
	 * 
	 * @����:�ύ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:44:21
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
	@SystemLog(description="�����ճ�����-��ٹ���-�������-�ύ")
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		boolean result=false;
		//myForm.setQjzt(QjsqService._SQZT_TJZT);
		Map<String, String> map = new HashMap<String, String>();
		HashMap<String, String> dataMap = service.checkJjrOrWeekend(myForm.getQjlxid(), myForm.getKssj(), myForm.getJssj());
		if("false".equals(dataMap.get("rs"))){
			response.getWriter().print(getJsonMessage(dataMap.get("message")));
			return null;
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			//���ó���ʱ�θ��Ի��ж�
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			if(service.getDupStatusSerivce(myForm)){
				String messageKey =  MessageKey.RCSW_QJGL_CFSQ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			//myForm.setQjsqid(UniqID.getInstance().getUniqIDHash());
			
			result = service.save(myForm);
			result=service.tj(myForm);
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			service.saveQjmx(myForm, mxrq, mxxmList);
		}else if(_TJ.equals(myForm.getType())){
			//���ó���ʱ�θ��Ի��ж�
			if("1103202".equals(Base.xxdm)){
				String messageFlag = service.checkIfOverTime(myForm.getKssj(), myForm.getJssj());
				if(StringUtils.isNotNull(messageFlag)){
					response.getWriter().print(getJsonMessage(messageFlag)); 
					return null;
				}
				
			}
			// �˻��ύ��ȡ���µ��������ID
			QjsqForm model = service.getModel(myForm.getQjsqid());	

			// �����˻صļ�¼������ȡ�������ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				model.setSsxydm(myForm.getSsxydm());
				HashMap<String, String> hm = service.getSplc(model);
				if (null==hm) {
					model.setSplcid("�������");
				}else{
					model.setSplcid(hm.get("splcid"));
				}
				myForm.setSplcid(model.getSplcid());
				//���˻صļ�¼ȡ�ϵ��������ID
			}else{
				myForm.setSplcid(model.getSplcid());
			}			
			
			result = service.update(myForm);
			result=service.tj(myForm);
			
			String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			service.saveQjmx(myForm, mxrq, mxxmList);
		}else{

			HashMap<String, String> hm = service.getSplc(myForm);
			if (null==hm) {
				myForm.setSplcid("�������");
			}else{
				myForm.setSplcid(hm.get("splcid"));
			}
			result = service.update(myForm);
		}
		if(result){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
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
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		QjsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HashMap<String, String>  hm=service.getSplc(model,true);
		if(StringUtils.isNotNull(myForm.getShzt())&&!myForm.getShzt().equals("")){
			request.setAttribute("shztmc", hm.get("splcid"));
		}else{
			request.setAttribute("shztmc", "�������");
		}
		//�������
		QjlxService qjlx=new QjlxService();
		request.setAttribute("qjlxmc",qjlx.getModel(model.getQjlxid()).getQjlxmc());
		//��ٿγ���Ϣ
		List<HashMap<String,String>> qjkcList = service.getKcList(model.getKcbhs());
		request.setAttribute("qjkcList", qjkcList);
		
		request.setAttribute("qjmxEnable", MessageUtil.getText("rcsw.qjgl.qjmx.enable"));
		request.setAttribute("qjxmList", new QjjgService().getQjxmList());
		request.setAttribute("qjmxList", service.getQjmxList(myForm.getQjsqid()));
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", model);
		//���������Ϣ
		QjjgService qjjg=new QjjgService();
		request.setAttribute("xjxx", qjjg.getXjxx(model.getQjsqid()));
		return mapping.findForward("qjsqck");
	}
	/**
	 * 
	 * @����:�Ƿ��������
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:24:41
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
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		// �Ƿ��������
		boolean result = service.isCanAdd(myForm);
		if (!result) {// ������
			response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ����֮ǰ����֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-1 ����11:52:27
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
	public ActionForward checkTs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		//��ȡ�������
		Map<String, String> map = new HashMap<String, String>();
		boolean result = service.isHaveSplcForTs(myForm);
		if (!result) {
			map.put("success", "false");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	/**
	 * 
	 * @����:��ȡδ�ύ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-18 ����05:55:50
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
	public ActionForward getSwtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		User user = getUser(request);
		Map<String, String> map = new HashMap<String, String>();
		CommService cs = new CommService();
		SearchModel searchModel = cs.getSearchModel(request);
		searchModel.setPath("qjsqbase.do");
		myForm.setSearchModel(searchModel); 
		map.put("wtjs", service.getWtjs(myForm,user));
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	
	
	/**
	 * 
	 * @����:�������������Ϣ
	 * @���ߣ������[���ţ�1004]
	 * @���ڣ�2013-12-16 ����04:41:17
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
	@SystemAuth(url = url)
	public ActionForward printQjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		CommShlcImpl commService = new CommShlcImpl();
		XsxxService xsxxService = new XsxxService();
		QjsqForm myForm = (QjsqForm) form;
		if(StringUtils.isNotNull(myForm.getQjsqid())){
			List<File> files = new ArrayList<File>();
			String[] qjsqids = myForm.getQjsqid().split(",");
			for (String id : qjsqids) {
				myForm.setQjsqid(id);
				QjsqForm model = service.getModel(myForm);
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				
				String qjsj="��  "+DateTranCnDate.fomartDateToCn(model.getKssj(),FomartDateType.day);
				qjsj+="  ��  "+DateTranCnDate.fomartDateToCn(model.getJssj(),FomartDateType.day);
				qjsj+=" ��  "+model.getQjts()+" ��";
				
				if("10351".equals(Base.xxdm)){
					qjsj+=" ��  "+model.getQjjs()+" ��";
				}
				//������������ְҵ����ѧԺ,����������Ի�begin
				if("12379".equals(Base.xxdm) || "14008".equals(Base.xxdm)){
					List<HashMap<String,String>> shxx = commService.getShyjListByYwid(id);
					String sqsj = model.getSqsj();
					xsjbxx.put("sqsj", sqsj);
					
					//�����Ϣ
					for (int i = 0; i < shxx.size(); i++){
						//���������
						xsjbxx.put("shrxm"+String.valueOf(i+1), shxx.get(i).get("shrmc"));

						//���ʱ��
						xsjbxx.put("shsj"+String.valueOf(i+1), shxx.get(i).get("shsj"));

						//������
						xsjbxx.put("shyj"+String.valueOf(i+1), shxx.get(i).get("shyj"));
					}
					
				}//������������ְҵ����ѧԺ,����������Ի�end
				xsjbxx.put("qjsj", qjsj);
				
				if("10695".equals(Base.xxdm)){//���������ѧ���Ի�
					List<HashMap<String, String>> shxx = commService.getShyjListByYwid(id);
					for (int i = 0; i < shxx.size(); i++){
						xsjbxx.put("shyj"+String.valueOf(i+1), shxx.get(i).get("shyj"));
					}
				}
				
				String qjkssj = DateTranCnDate.fomartDateToCn(model.getKssj(),FomartDateType.day);
				String qjjssj = DateTranCnDate.fomartDateToCn(model.getJssj(),FomartDateType.day);
				xsjbxx.put("qjkssj", qjkssj.substring(qjkssj.indexOf("��")+1));
				xsjbxx.put("qjjssj", qjjssj.substring(qjjssj.indexOf("��")+1));
				xsjbxx.put("sqsj", model.getSqsj().substring(0,10));
				xsjbxx.put("qjsy", HtmlUtil.xmlZy(model.getQjsy()));
				xsjbxx.put("bz", HtmlUtil.xmlZy(model.getBz()));
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}
	/**
	 * 
	 * @����:��ʦ����Ի�����������ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-11-19 ����02:54:58
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
	@SystemAuth(url = url)
	public ActionForward printQjsqbOfHs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> fdyData = new HashMap<String, Object>();
		QjsqService service = new QjsqService();
		XsxxService xsxxService = new XsxxService();
		QjsqForm myForm = (QjsqForm) form;
		if(StringUtils.isNotNull(myForm.getQjsqid())){
			List<File> files = new ArrayList<File>();
			String[] qjsqids = myForm.getQjsqid().split(",");
			for (String id : qjsqids) {
				// ==========��ѯ�����Ϣ���������ʱ�併�� begin=======
				Map<String, String> shMap0 = new HashMap<String, String>();
				ShlcDao splcdao = new ShlcDao();
				List<HashMap<String , String>> shyjList = splcdao.getShyjList(id, "desc");
				if(shyjList.size() >= 1){
					shMap0 = shyjList.get(0);
				}
				// ==========��ѯ�����Ϣ���������ʱ�併�� end=======
				myForm.setQjsqid(id);
				QjsqForm model = service.getModel(myForm);
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				String qjkssj = DateTranCnDate.fomartDateToCn(model.getKssj(),FomartDateType.day);
				String qjjssj = DateTranCnDate.fomartDateToCn(model.getJssj(),FomartDateType.day);
				String sqsj = DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day);
				String qjbh = model.getSqsj().substring(0,10).replace("-", "")+model.getQjbh();
				xsjbxx.put("qjkssj", qjkssj.substring(qjkssj.indexOf("��")+1));
				xsjbxx.put("qjjssj", qjjssj.substring(qjjssj.indexOf("��")+1));
				xsjbxx.put("qjts", model.getQjts());
				xsjbxx.put("qjsy", HtmlUtil.xmlZy(model.getQjsy()));
				xsjbxx.put("sqsj", sqsj);
				xsjbxx.put("qjbh", qjbh);
				//��ٿγ���Ϣ
				xsjbxx.put("rkjs","�ον�ʦ");
				List<HashMap<String,String>> qjkcList = service.getKcList(model.getKcbhs());
				//�ο���ʦ������ٵ�
				File qjkcFile =null;
				for (HashMap<String, String> qjkc : qjkcList) {
					xsjbxx.put("kcmc", qjkc.get("kcmc"));
					xsjbxx.put("rkjs", "�ον�ʦ");
					xsjbxx.put("rklsxm", qjkc.get("rklsxm"));
					fdyData.putAll(xsjbxx);
					fdyData.put("shMap0", shMap0);
					qjkcFile =service.getWordOfFdy(fdyData);
					files.add(qjkcFile);
				}
				//�������⣬��һ�����ݵ�������
				if(qjkcList.size()>0){
					xsjbxx.put("kcmc1", qjkcList.get(0).get("kcmc"));
					xsjbxx.put("rkjs1", "�ον�ʦ");
					xsjbxx.put("rklsxm1", qjkcList.get(0).get("rklsxm"));
					qjkcList.remove(0);
					
				}
				data.putAll(xsjbxx);
				
				data.put("qjkcList", qjkcList);
				data.put("shMap0", shMap0);
				File file = service.getWord(data);
				
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ٹ���-�������-����VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			QjsqForm model = service.getModel(values);
			ShlcDao splcdao = new ShlcDao();
			if(Integer.valueOf(splcdao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			model.setQjzt(QjsqService._SQZT_CGZT);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����: ��֤�Ƿ���ύ
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
	public ActionForward checkSfktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QjsqForm model = (QjsqForm) form;
		QjsqService service = new QjsqService();
		String qjts = request.getParameter("qjts");
		model.setQjts(qjts);
		
		// ȡ���Ƿ������֤(�����춯���) true:���ύ/false�������ύ
		boolean isSfktj = service.isHaveSplcForTs(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	/**
	 * 
	 * ѡ����ٿγ�
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-18 ����02:38:24
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
	@SystemAuth(url = url)
	public ActionForward selectQjkc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsqService service = new QjsqService();
		QjsqForm myForm = (QjsqForm) form;
		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("qjsq.do?method=selectQjkc");
			myForm.setSearchModel(searchModel);
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getQjkcList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qjsq.do?method=selectQjkc";
		request.setAttribute("path", path);
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("selectqjkc");
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����05:55:41
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
	@SystemAuth(url = url)
	public ActionForward getXsqjspbOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		QjsqForm model = (QjsqForm)form;
		/*��ȡurl���������������id*/
		String qjsqid = model.getQjsqid();
		/*��ȡ�ļ���Ϣ*/
		File wordFile = getWordForQjspb(qjsqid);
		/*����ļ�*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����: �㽭����ְҵѧԺ���Ի�����������ӡ���������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����06:54:55
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
	@SystemAuth(url = url)
	public ActionForward getXsqjspbZip (ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		/*��ȡurl��������Value*/
		String value = request.getParameter("value");
		/*�ж�value�Ƿ�Ϊ��*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForQjspb(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @����: ����������������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����06:44:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForQjspb (String qjsqid) throws Exception{
		
		QjsqService service = new QjsqService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*����һ����file*/
		File file = null;
		/*������ѡqjsqid��ȡ�����Ϣ*/
		HashMap<String,String> qjxxInfo = service.getQjsqxxForQjsqid(qjsqid);
		String xh = qjxxInfo.get("xh");
		/*�������Ϣ*/
		data.put("qjxxInfo", qjxxInfo);
		
		XsxxService xsxxService = new XsxxService();
		/*����ѧ��������Ϣ*/
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		
		/*�ָ���ٿ�ʼ����ʱ�������ʱ*/
		String kssjMonth = qjxxInfo.get("kssj").substring(5, 7);
		String kssjDay = qjxxInfo.get("kssj").substring(8, 10);
		String kssjHour = qjxxInfo.get("kssj").substring(11, 13);
		data.put("kssjMonth", kssjMonth);
		data.put("kssjDay", kssjDay);
		data.put("kssjHour", kssjHour);
		String jssjMonth = qjxxInfo.get("jssj").substring(5, 7);
		String jssjDay = qjxxInfo.get("jssj").substring(8, 10);
		String jssjHour = qjxxInfo.get("jssj").substring(11, 13);
		data.put("jssjMonth", jssjMonth);
		data.put("jssjDay", jssjDay);
		data.put("jssjHour", jssjHour);
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//rcsw","qjspb_12869.xml", FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		return file;
	}
}
