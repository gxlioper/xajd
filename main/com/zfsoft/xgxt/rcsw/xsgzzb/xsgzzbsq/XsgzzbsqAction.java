package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz.CsszService;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz.XsgzzbJcszForm;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz.XsgzzbJcszService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.*;

public class XsgzzbsqAction extends SuperAction {
	private List<HashMap<String, String>> xyList;
	private MessageResources messageResources = MessageResources.getMessageResources("config.ApplicationResources");
	
	{
		XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
		xyList = xsgzzbsqService.getXyList();
	}
	
	private static final String url = "rcsw_xsgzzb_xsgzzbsq.do";

	/**
	 * @����:��ѯ�ܱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgzzbsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "��ģ�鲻����ѧ�����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList;
			if("bj".equals(gzzblx)){
				resultList = service.getPageListBj(model, user);
			}else{
				resultList = service.getPageList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		XsgzzbJcszService xsgzzbJcszService = new XsgzzbJcszService();
		XsgzzbJcszForm jcszModel = xsgzzbJcszService.getModel(gzzblx);
		request.setAttribute("jcszModel", jcszModel);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_xsgzzb_xsgzzbsq.do";
		if("bj".equals(gzzblx)){
			path = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
		}
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		List<HashMap<String, String>> zcList = service.getZcList();
		request.setAttribute("zcListSize", zcList.size());
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbsqManage");
		}
		return mapping.findForward("xsgzzbsqManage");
	}
	
	/**
	 * @����:��ѯ�༶
	 */
	public ActionForward bjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("flag", StringUtils.isNotNull(request.getParameter("flag"))? request.getParameter("flag"):"");
		String path = "rcsw_xsgzzb_xsgzzbsqgl.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}

	/**
	 * @����:�����ܱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ճ�����-ѧ�������ܱ���-�ܱ���д-����BMDM:{bmdm},ZC:{zc},YZZYGZ:{yzzygz},XZZYGZ:{xzzygz}")
	public ActionForward addXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�꣬ѧ�ڣ�ѧԺ���ܴΣ�¼���ˣ�
			boolean isExist = false;
			model.setLrsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setLrr(user.getUserName());
			isExist = service.isExistByXszbbsq(model, user);
			if (!isExist) {
				// ���
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
				if(Base.xxdm.equalsIgnoreCase("13815")){
					model.setWjlxdms(request.getParameterValues("wjlxdm"));
				}
				boolean result = service.saveXsgzzbsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String mc = messageResources.getMessage("lable.xy");
				if("bj".equals(gzzblx)){
					mc = "�༶";
				}
				String message = MessageUtil.getText(MessageKey.RCSW_XSGZZB_XSGZZBSQCZ, mc) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}

		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setXqmc(service.getCurrentXqmc(model));
		List<HashMap<String, String>> zcList = service.getZcList();
		// ========= ��ʼ������������ begin =========
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		DateFormat d = DateFormat.getDateInstance();
		for (HashMap<String, String> zcMap : zcList) {
			Date ksrq = d.parse(zcMap.get("ksrq"));
			Date jsrq = d.parse(zcMap.get("jsrq"));
			boolean flag = now.after(ksrq) && now.before(jsrq);
			if(flag){
				model.setZc(zcMap.get("dm"));
			}
		}
		// ========= ��ʼ������������ end =========
		if(!"bj".equals(gzzblx)){
			// ========= ��ȡѧԺ���� begin =========
			model.setBmdm(user.getUserDep());
			for (HashMap<String, String> xyMap : xyList) {
				if(xyMap.get("xydm").equals(user.getUserDep())){
					model.setBmdmmc(xyMap.get("xymc"));
				}
			}
			// ========= ��ȡѧԺ���� end =========
			request.setAttribute("xyList", xyList);
		}
		request.setAttribute("zcList", zcList);
		request.setAttribute("xsgzzbsqMap", model);
		request.setAttribute("userStatus", user.getUserStatus());
		if("bj".equals(gzzblx)){
			return mapping.findForward("addBjgzzbsq");
		}
		//�Ĵ���Ϣְҵ����ѧԺ�ļ�list,����������
		if(Base.xxdm.equals("13815")){
			CsszService csszservice =  new CsszService();
			request.setAttribute("wjlxlist", csszservice.getWjlxList());
		}
		return mapping.findForward("addXsgzzbsq");
	}

	/**
	 * @����:�޸��ܱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ճ�����-ѧ�������ܱ���-�ܱ���д-�޸�SQID:{sqid},BMDM:{bmdm},ZC:{zc},YZZYGZ:{yzzygz},XZZYGZ:{xzzygz}")
	public ActionForward updateXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�꣬ѧ�ڣ�ѧԺ���ܴΣ�¼���ˣ�
			boolean isExist = false;
			model.setLrsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setLrr(user.getUserName());
			isExist = service.isExistByXszbbsq(model, user);
			if (!isExist) {
				if(Base.xxdm.equalsIgnoreCase("13815")){
					model.setWjlxdms(request.getParameterValues("wjlxdm"));
				}
				boolean result = service.updateXsgzzbsq(model);
				String messageKey = "";
				if (UPDATE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String mc = messageResources.getMessage("lable.xy");
				if("bj".equals(gzzblx)){
					mc = "�༶";
				}
				String message = MessageUtil.getText(MessageKey.RCSW_XSGZZB_XSGZZBSQCZ, mc) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		XsgzzbsqForm updateForm = null;
		if("bj".equals(gzzblx)){
			updateForm = service.getModelBj(model);
		}else{
			updateForm = service.getModel(model);
		}
		XsgzzbJcszService xsgzzbJcszService = new XsgzzbJcszService();
		XsgzzbJcszForm jcszModel = xsgzzbJcszService.getModel(gzzblx);
		request.setAttribute("jcszModel", StringUtils.formatData(jcszModel));
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xsgzzbsqMap", StringUtils.formatData(model));
		if("bj".equals(gzzblx)){
			return mapping.findForward("updateBjgzzbsq");
		}
		//�Ĵ���Ϣְҵ����ѧԺ�ļ�list,����������
		if(Base.xxdm.equals("13815")){
			CsszService csszservice =  new CsszService();
			List<HashMap<String, String>>  yscfjlist = csszservice.getYscfjList(model.getSqid());
			request.setAttribute("wjlxlist", csszservice.getWjlxList());
			request.setAttribute("yscfjlist", yscfjlist);
			if(yscfjlist == null || yscfjlist.size() == 0){
				request.setAttribute("scbz", "0");
			}else{
				request.setAttribute("scbz", "1");
			}
			
		}
		return mapping.findForward("updateXsgzzbsq");
	}

	/**
	 * @����:ɾ���ܱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ճ�����-ѧ�������ܱ���-�ܱ���д-ɾ��VALUES:{values}")
	public ActionForward delXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// ɾ��
			service.setTableInfo(model);
			List<HashMap<String, String>> fjlj = null;
			ArrayList<String> delFileArrId = new ArrayList<String>();
			//�Ĵ���Ϣְҵ����ѧԺ���Ի�����ɾ��ʱ�ж�ɾ������
			if(Base.xxdm.equalsIgnoreCase("13815")){
				String[]ids = values.split(",");
				String filegid = null;
				int idslen = ids.length;
				for(int i = 0;i <= idslen-1;i++){
					filegid = service.getFileGID(ids[i]);
					if(StringUtils.isNotNull(filegid)){
						delFileArrId.add(filegid);
					}
				}
				if(delFileArrId != null && delFileArrId.size() != 0){
					 fjlj = service.getfjlj(delFileArrId.toArray(new String[]{}));
				}
			}
			int num = service.runDelete(values.split(","));
			//�Ĵ���Ϣְҵ����ѧԺ���Ի�����ɾ��ʱ�ж�ɾ������
			if(Base.xxdm.equalsIgnoreCase("13815")){
				boolean fjselresult = service.Delfile_13815(delFileArrId.toArray(new String[]{}));
				if(num > 0 && fjlj != null){
					if(fjselresult){
						fjselresult = service.Delfile_13815_realfile(fjlj);
					}
					if(!fjselresult){
						throw new SystemException(MessageKey.SYS_DEL_FAIL);
					}
				}
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * @����:�ύ�ܱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String sqid = request.getParameter("values");
		model.setSqid(sqid);
		XsgzzbsqService service = new XsgzzbsqService();
		boolean result = service.submitXsgzzbsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @����:����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm myForm = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			XsgzzbsqForm model = new XsgzzbsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			model.setGzzblx(myForm.getGzzblx());
			service.cancelXsgzzbsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @����:�鿴�ܱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		if("bj".equals(gzzblx)){
			model = service.getModelBj(model);
		}else{
			model = service.getModel(model);
		}
		request.setAttribute("model", StringUtils.formatData(model));
		if("bj".equals(gzzblx)){
			return mapping.findForward("viewBjgzzbsq");
		}
		//�Ĵ���Ϣְҵ����ѧԺ�ļ�list,����������
		if(Base.xxdm.equals("13815")){
			CsszService csszservice =  new CsszService();
			request.setAttribute("yscfjlist", csszservice.getYscfjList(model.getSqid()));
			
		}
		return mapping.findForward("viewXsgzzbsq");
	}

	/**
	 * @����:����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
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
	
	//ɾ�������ļ�
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileid = request.getParameter("id");
		XsgzzbsqService service = new XsgzzbsqService();
		HashMap<String, String> fjlj = service.onefjlj(fileid);
	    boolean result = service.delonefjlj(fileid);;
		if (StringUtils.isNotNull(fjlj.get("fjlj")) && result == true){
			File file = new File(fjlj.get("fjlj"));
			if (file.exists()){
				result = file.delete();
			}
			if(result){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		}
		return null;
	}
	
	//���ص����ļ�
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileid = request.getParameter("id");
		XsgzzbsqService service = new XsgzzbsqService();
		HashMap<String, String> fjlj = service.onefjlj(fileid);
		if (StringUtils.isNotNull(fjlj.get("fjlj"))){
			File file = new File(fjlj.get("fjlj"));
			if (file.exists()){
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fjlj.get("fjmc"),"utf-8")); 
					FileUtil.outputFile(response, file);
				}
			}
		}
		return null;
	}
}
