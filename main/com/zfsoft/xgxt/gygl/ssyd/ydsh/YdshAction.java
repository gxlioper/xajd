package com.zfsoft.xgxt.gygl.ssyd.ydsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqForm;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-�����춯
 * @�๦������: �����춯���
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-27
 * @�汾�� V1.0
 */
public class YdshAction extends SuperAction {
	
	private static final String url = "ydshbase.do";
	
	private YdshService service = TransactionControlProxy.newProxy(new YdshService());
	
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @����:�����춯����б�
	 * @���ߣ� qilm
	 * @ʱ�䣺 2013-9-27
	 * @�汾�� V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YdshForm myForm = (YdshForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("ydshbase.do");
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			// ========== ��λ���� begin ============
			for (HashMap<String, String> resultMap : resultList) {
				String ssydlx = resultMap.get("ssydlx");
				String sqshHideCwxx = "";
				// �������
				if (YdshService._YDLX_SSTZ.equals(ssydlx)) {
					// ���õ�����λ��Ϣ
					sqshHideCwxx = resultMap.get("tzhlddm") + "_"
							+ resultMap.get("tzhqsh") + "_" + resultMap.get("tzhcwh");
				}else if (YdshService._YDLX_SSRZ.equals(ssydlx)) { // ��ס
					sqshHideCwxx = resultMap.get("tzqlddm") + "_"
							+ resultMap.get("tzqqsh") + "_" + resultMap.get("tzqcwh");
				}
				// ========== ��˴�λ���� begin ============
				ShlcDao shlcDao = new ShlcDao();
				List<HashMap<String, String>> shyjList = shlcDao.getShyjList(resultMap.get("ssydsqid"), "desc");
				if(shyjList.size() > 0){
					HashMap<String, String> shyj = shyjList.get(0);
					sqshHideCwxx = shyj.get("zd7") + "_" + shyj.get("zd9") + "_" + shyj.get("zd10");
				}
				// ========== ��˴�λ���� end ============
				resultMap.put("sqshHideCwxx", sqshHideCwxx);
			}
			// ========== ��λ���� end ============
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//���һ��������˺���õ�·��
		request.setAttribute("cancelPath", "ydsh.do?method=cancel");
		request.setAttribute("path", "ydshbase.do");
		FormModleCommon.commonRequestSet(request);

		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("list");
	}
	
	
	/**
	 * @����:ְ�����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-9 ����5:02:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯���-���PK:{ssydsqid}")
	public ActionForward ydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdshForm myForm = (YdshForm) form;
		YdsqService sqservice = new YdsqService();
		//��ȡ������Ϣ
		YdsqForm model = sqservice.getModel(myForm);
		
		User user = getUser(request);
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(model.getSsydlx());
		request.setAttribute("zsfxs", shlcXx.getZsfxs());	
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			
			//������˽��
			myForm.setSplcid(model.getSplcid());
			myForm.setXh(model.getXh());
			myForm.setSsydsqid(model.getSsydsqid());
			myForm.setSsydlx(model.getSsydlx());
			
			boolean result = service.ydsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѧ����Ϣ��ʾ�������ã�
		szXsxx(request,myForm.getXh());
		request.setAttribute("myForm", myForm);
		//��λ��Ϣ��ʾ
		if(!"12303".equals(Base.xxdm)){
		request.setAttribute("cwxxData",StringUtils.formatData(sqservice.getCwxx(model.getTzqlddm(),
				model.getTzqqsh(), model.getTzqcwh())));
		}
		// �������
		if (YdshService._YDLX_SSTZ.equals(model.getSsydlx())) {
			// ���õ�����λ��Ϣ
			model.setCwxx(model.getTzhlddm() + "_"
					+ model.getTzhqsh() + "_" + model.getTzhcwh());
		}else if (YdshService._YDLX_SSRZ.equals(model.getSsydlx())) { // ��ס
			model.setCwxx(model.getTzqlddm() + "_"
					+ model.getTzqqsh() + "_" + model.getTzqcwh());
		}
		
		//�춯������Ϣ
		request.setAttribute("data", StringUtils.formatData(model));
		//���ᮐ�����
		request.setAttribute("ssydlx", model.getSsydlx());
		request.setAttribute("xxdm", Base.xxdm);
		// ========== ��˴�λ���� begin ============
		ShlcDao shlcDao = new ShlcDao();
		List<HashMap<String, String>> shyjList = shlcDao.getShyjList(myForm.getSsydsqid(), "desc");
		String shCwxx = model.getCwxx();
		if(shyjList.size() > 0){
			HashMap<String, String> shyj = shyjList.get(0);
			shCwxx = shyj.get("zd7") + "_" + shyj.get("zd9") + "_" + shyj.get("zd10");
		}
		if (YdshService._YDLX_SSTZ.equals(model.getSsydlx())) { // ���õ�����λ��Ϣ
			myForm.setCwxx(shCwxx);
		}else if (YdshService._YDLX_SSRZ.equals(model.getSsydlx())) { // ��ס
			myForm.setRzcwxx(shCwxx);
		}
		// ========== ��˴�λ���� end ============
		if("ck".equalsIgnoreCase(myForm.getType())){
			return mapping.findForward("ydck");
		}
		
		return mapping.findForward("ydsh");
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}

		//ѧ��������Ϣ��ʾ����
		jbxxList = new BdpzService().getJbxxpz("gyglssyd");
		request.setAttribute("jbxxList", jbxxList);
	}
	

	/**
	 * @����:���һ���������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdshForm model = (YdshForm) form;
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());	
		// ҵ��ع�
		boolean result = service.cancel(model.getSplcid(), shxx.get("ywid"));
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @����:������˲�������Ҫ��������ҵ��
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯���-����PK:{shid}")
	public ActionForward cxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdshForm model = (YdshForm) form;
		ShlcInterface service = new CommShlcImpl();
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());
		boolean result = service.runCancel(user.getUserName(), shxx.get("ywid"), model.getSplcid(), shxx.get("gwid"));
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �����춯��˵���
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdshForm model = (YdshForm) form;
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		YdshService service = new YdshService();
		model.setShzt(shlx);
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getPageList(model,user);//��ѯ�����м�¼������ҳ
		
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
	 * 
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-28 ����05:06:17
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
	@SystemLog(description="���ʹ�Ԣ����-�����춯-�����춯���-�������PK:{id}")
	public ActionForward ydPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YdshForm model = (YdshForm) form;
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		ShlcService shlcService = new ShlcService();
		ShlcForm shlcXx = shlcService.getNowShlc(model.getSsydlx());
		request.setAttribute("zsfxs", shlcXx.getZsfxs());
		
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("ydPlsh");
	}
	
}
