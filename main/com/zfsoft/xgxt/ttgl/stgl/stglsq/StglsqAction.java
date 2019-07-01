/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stglsq;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.ttgl.stgl.jcsz.JcszForm;
import com.zfsoft.xgxt.ttgl.stgl.jcsz.JcszService;
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class StglsqAction extends SuperAction{

	private static final String url = "xg_ttgl_stglsq.do";
	 StglsqService service = new StglsqService();
	
	
	/**
	 * @description	�� ��ѯ
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-1 ����10:20:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stglsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglsqForm model = (StglsqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//����������Ϣ
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("stglsqList");
		
	}

	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-1 ����10:45:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			StglsqForm model = (StglsqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = service.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		if (SAVE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			model.setSqsj(df.format(new Date()));
			String[] xhArray = request.getParameterValues("xh");
			model.setXhArray(xhArray);
            String[] jflyArray = model.getJflyArray();
            StringBuilder s = new StringBuilder();
            for(int i=0;i<jflyArray.length;i++){
                s.append(jflyArray[i]).append(",");
            }
            if (s.length() > 0) {
                model.setJfly(s.substring(0, s.length()-1));//������Դ��ѡ���ö��Ÿ��������浽���ݿ�
            }
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
        		boolean result = service.saveStsq(model);
        		String messageKey = "";
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		StglService stglService = new StglService();
        //ָ����λ
		List<HashMap<String, String>> bmList = stglService.getbmList();
		//ѧ����֯������Դ
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        //��ȡѧ����֯������Դ����
        List<HashMap<String, String>> ndzztList = stglService.getNdzzztList();
        //��ȡ��֯��𼯺�
        List<HashMap<String, String>> zzlbList = stglService.getZzlbList();
        request.setAttribute("bmList", bmList);
        request.setAttribute("xszzjflyList",xszzjflyList);
        request.setAttribute("ndzztList",ndzztList);
        request.setAttribute("zzlbList",zzlbList);
        //ѧ��list
        request.setAttribute("currXn", Base.currXn);

		String path = "ttgl_stglsq.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("stsqzj");
	}
/**
 * @description	�� ѡ��ѧ��
 * @author 		�� CP��1352��
 * @date 		��2018-2-1 ����03:20:11
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward getxsInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhs");
		String[] xhs = null;
		if(!"".equals(xhstr)){
			xhs = xhstr.split(",");
		}
		//��ȡ�ı�����ѧ�ŵ�ѧ����Ϣ
		HashMap<String, String> xsmap = service.getXsxx(xh,xhs);
		JSONObject jsonObj = JSONObject.fromObject(xsmap);
		response.getWriter().print(jsonObj);
		return null;
	}
	/**
	 * @description	�� �޸�
	 * @author 		��CP��1352��
	 * @date 		��2018-1-17 ����04:14:47
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			StglsqForm model = (StglsqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		if (UPDATE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			model.setSqsj(df.format(new Date()));
			String[] xhArray = request.getParameterValues("xh");
			model.setXhArray(xhArray);
			String[] jflyArray = model.getJflyArray();
			StringBuilder s = new StringBuilder();
			for(int i=0;i<jflyArray.length;i++){
			    s.append(jflyArray[i]).append(",");
            }
            if (s.length() > 0) {
                model.setJfly(s.substring(0, s.length()-1));//������Դ��ѡ���ö��Ÿ��������浽���ݿ�
            }
            boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.updateStsq(model);
        		String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		StglsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		StglService stglService = new StglService();
		List<HashMap<String, String>> bmList = stglService.getbmList();
		//ѧ����֯������Դ
		List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
		//��ȡѧ����֯������Դ����
		List<HashMap<String, String>> ndzztList = stglService.getNdzzztList();
		//��ȡ��֯��𼯺�
		List<HashMap<String, String>> zzlbList = stglService.getZzlbList();
		request.setAttribute("bmList", bmList);
		request.setAttribute("xszzjflyList",xszzjflyList);
		request.setAttribute("ndzztList",ndzztList);
		request.setAttribute("zzlbList",zzlbList);
		request.setAttribute("stlx", updateForm.getStlx());
		request.setAttribute("zdls", stglService.getfdyxm(updateForm.getStzdls()));
		request.setAttribute("zgh", updateForm.getStzdls());
		request.setAttribute("fzrxxInfo", service.getFzrxx(model.getSqid()));
		request.setAttribute("tzsxxInfo",service.getTzsxx(model.getSqid()));
        //ѧ��list
        request.setAttribute("currXn", Base.currXn);
		String path = "ttgl_stglsq.do?method=update";
		request.setAttribute("path", path);
		return mapping.findForward("stsqxg");
	}
	
	
	/**
	 * @description	�� ɾ��
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-17 ����04:14:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteStsq(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
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
	 * @description	�� �ύ
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-1 ����11:29:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglsqForm model = (StglsqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setSqid(sqid);
		model.setXh(xh);
		//�ж��ύʱ����Ƿ񿪷�
		boolean result = service.submitStsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-1 ����11:38:02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelStsq(sqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			StglsqForm model = new StglsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateCxStsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * @description	�� �鿴
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-1 ����11:41:43
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglsqForm model = (StglsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		HashMap<String,String> stsqMap = service.getStsqInfo(model);
        //ѧ����֯������Դ
        StglService stglService = new StglService();
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        request.setAttribute("xszzjflyList",xszzjflyList);
		request.setAttribute("rs", StringUtils.formatData(stsqMap));
		request.setAttribute("filepath", stsqMap.get("filepath"));
		request.setAttribute("fzrxxInfo", service.getFzrxx(model.getSqid()));
        request.setAttribute("tzsxxInfo",service.getTzsxx(model.getSqid()));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("stsqck");
	}
	
	/**
	 * 
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-17 ����05:40:48
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglsqForm model = (StglsqForm) form;
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
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

	
	
	
	
}
