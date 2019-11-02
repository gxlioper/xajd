/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdxq;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypxJg.PxJgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;

/**
 * @className	�� HdxqAction
 * @description	�� �����action(��������������)
 * @author 		��������1282��
 * @date		�� 2018-2-1 ����04:14:15
 * @version 	V1.0 
 */

public class HdxqAction extends SuperAction<HdxxForm, HdxxService> {
	  private HdxxService service = new HdxxService();
	  private static final String url = "hdgl_hdgl_hdxq.do";
	//ѧ��������Ϣ����
	private static List<HashMap<String, String>> jbxxList = null;

	public static String HDBL = "hdbl";

	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	/**
	 * @description	�� �����list
	 * @author 		�� ������1282��
	 * @date 		��2018-2-1 ����04:20:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)  
	public ActionForward hdxqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getHdxqList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "hdgl_hdgl_hdxq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("hdlxList",service.getHdlx());
		return mapping.findForward("hdxqList");
	}
	
	/**
	 * @description	�� �����ҳ��
	 * @author 		�� ������1282��
	 * @date 		��2018-2-2 ����08:56:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		model = service.getModel(model);
		request.setAttribute("model",model);
		return mapping.findForward("hdxqgl");
	}

	/**
	 * @description	�� �����
	 * @author 		�� ������1282��
	 * @date 		��2018-2-2 ����02:01:19
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdxq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		HashMap<String,String> data = service.getHdxx(model);
		//�׶���Ϣ
		List<HashMap<String,String>> jdxx = service.getJdxx(model.getHdid());
		request.setAttribute("jdxxList", jdxx);
		request.setAttribute("data", data);
		return mapping.findForward("hdxx");
	} 
	
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date 		��2018-2-2 ����09:57:04
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdcyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getCyList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}else{
			HashMap<String,String> data = service.getHdxx(model);
			request.setAttribute("data", data);
		}		
		String path = "hdgl_hdgl_hdxq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdcyList");
	}
	public ActionForward sh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		HashMap<String,String> data = service.getBmSqxx(model.getIds()[0],model.getBmlx());

		// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(data.get("xh"));
		String cz = request.getParameter("cz");//��˲���||�鿴����
        HdxxForm hdxxForm = model = service.getModel(model);
        String bmjbxxxs = hdxxForm.getBmjbxxxs();
        String[] bmjbxx = null;
        String[] kzbmjbxx = {"xb","mzmc","dzyx","zzmmmc","sfzh","fydmc"};
		List<HashMap<String,String>> jbxxListxs = new ArrayList<>();
		jbxxListxs.addAll(jbxxList);
        List<HashMap<String,String>> lslist = new ArrayList<>(); //��Ҫ��jbxxListxs���Ƴ���Ԫ��(������ʾ���ֶ�)
        List<String> jbxszdlist = new ArrayList<>();
        if(!StringUtil.isNull(bmjbxxxs)){
			bmjbxx = bmjbxxxs.split(",");
			for(int i=0;i<kzbmjbxx.length;i++){
			    boolean flag = true;
			    for(int j=0;j<bmjbxx.length;j++){
			        if(kzbmjbxx[i].equals(bmjbxx[j])){
			            flag = false;
                    }
                }
                if(flag){
			        jbxszdlist.add(kzbmjbxx[i]);//����ʾ�ֶ�
                }
            }
        }else {
            jbxszdlist = Arrays.asList(kzbmjbxx);//����ʾ�ֶ�
        }
        for(String jbxszd:jbxszdlist){
            for(HashMap<String,String> jbxx:jbxxList){
                String zddm = jbxx.get("zddm");
                if (zddm.equals(jbxszd)){
                    lslist.add(jbxx);
                    continue;
                }
            }
        }
        for(HashMap<String,String> lsxx:lslist){
            int index = jbxxListxs.indexOf(lsxx);
            jbxxListxs.remove(index);//�Ƴ�Ԫ��
        }
		request.setAttribute("cz",cz);
		request.setAttribute("jbxx", xsjbxx);
		request.setAttribute("xh", data.get("xh"));
        request.setAttribute("jbxxList", jbxxListxs);
		request.setAttribute("data",data);
		request.setAttribute("bmlx",model.getBmlx());
		return mapping.findForward("sh");
	}

	public ActionForward plsh(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		if(SAVE.equals(model.getType())){
			model.setIds(model.getId().split(","));
			for(int i=0;i<model.getIds().length;i++){
				boolean re = service.checkIsSh(model.getIds()[i]);
				if(re){
					response.getWriter().print(getJsonMessage("������������ݣ������ظ����"));
					return null;
				}

			}
			boolean result = service.updateShzt(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("ids",model.getId());
		request.setAttribute("bmlx",model.getBmlx());
		return mapping.findForward("plsh");
	}
	/**
	 * @description	�� �������״̬
	 * @author 		�� ������1282��
	 * @date 		��2018-2-5 ����11:41:46
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward saveShzt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		
		//������״̬Ϊͨ���Ļ�����֤ͨ������
//		if(model.getShzt().equals("1") && model.getBmlx().equals("1") && service.beyondMaximum(model)){
//			String key = MessageKey.XG_HDGL_PEOPLE_BEYOND;
//			response.getWriter().print(getJsonMessageByKey(key));
//		}else{
		boolean re = service.checkIsSh(model.getSqid());
		if(re){
			response.getWriter().print(getJsonMessage("����ˣ������ظ����"));
			return null;
		}
			boolean result = service.updateShzt(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
//		}
		return null;
	}
	
	/**
	 * @description	�� ������б�
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-8 ����10:38:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdplList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			//User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> hdplList = service.getHdplList(model);
			JSONArray dataList = JSONArray.fromObject(hdplList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "hdgl_hdgl_hdxq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdplList");
	}
	
	/**
	 * @description	�� ���ۻظ�
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-8 ����05:14:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward plReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		HashMap<String,String> plxx = service.getHdPlInfo(model.getPlid());
		plxx = StringUtils.formatMap(plxx);
		request.setAttribute("plxx", plxx);
		return mapping.findForward("plReply");
	}
	
	/**
	 * @description	�� ����ظ�����
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-9 ����01:48:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward savePlReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		boolean result = service.replyPl(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;		
	}
	
	/**
	 * @description	�� ɾ������
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-9 ����03:45:03
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward delPl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		boolean result = service.delPl(model.getPlid());
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� �鿴����
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-13 ����03:12:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ckPl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		HashMap<String,String> plxx = service.getHdPlInfo(model.getPlid());
		plxx = StringUtils.formatMap(plxx);
		request.setAttribute("plxx", plxx);
		return mapping.findForward("ckPl");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-13 ����04:38:48
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		boolean result = service.dz(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * ��Ʊ����
	 */
    @SystemAuth(url = url)
	public ActionForward getPpView(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response) throws Exception{

        HdxxForm model = (HdxxForm) form;
        //��û�������ͱ�������
        HashMap<String,String> data = service.getHdxx(model);
        request.setAttribute("data",data);

		return mapping.findForward("hdxqpp");
	}

	public ActionForward checkPpRs(ActionMapping mapping, ActionForm form,
										 HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
    	String msg = service.checkPpRs(model);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}
    /**
     * ��ȡ��Ʊ��Ա����
     */
	public ActionForward getPpPersonList(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{

        HdxxForm model = (HdxxForm) form;
        List<HashMap<String,String>> list = service.getPpPersonList(model);
        JSONArray result = JSONArray.fromObject(list);
        response.getWriter().print(result);

        return null;
    }

    /**
     * ���û�ȡ�ĵ�Ʊ��Ա״̬
     */
    public ActionForward setPpPersonList(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception{

        HdxxForm model = (HdxxForm) form;
        boolean flag = service.update(Arrays.asList(model.getParam()),model.getBmlx());
        String messageKey = flag ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

	/**
	 * ����ʱ�������
	 */
	@SystemAuth(url = url)
	public ActionForward bmsjglView(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws Exception{

		HdxxForm model = (HdxxForm) form;
		HashMap<String,String> data = service.getHdxx(model);
		BeanUtils.copyProperties(model,data);
		request.setAttribute("data",data);
		request.setAttribute("jldxList",service.getBtZdList(model.getHdid()));//���������ֶ���Ϣ
		return mapping.findForward("bmsjgl");
	}

	//���汨������
	public ActionForward saveBmsj(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response) throws Exception{

		HdxxForm model = (HdxxForm) form;
		String messageKey;
		if(service.update(model)){
			messageKey = MessageKey.SYS_SAVE_SUCCESS;
		} else {
			messageKey = MessageKey.SYS_SAVE_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HdxxForm model = (HdxxForm) form;
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		Pages pages = new Pages();
		pages.setPageSize(Integer.MAX_VALUE);
		model.setPages(pages);
		List<HashMap<String,String>> resultList = getService().gethasPCyList(model,user);

		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

	public ActionForward getEwm(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		HdxxForm model = (HdxxForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			JSONObject jobj = new JSONObject();
			HdEwm hdEwm = new HdEwm();
			String code = hdEwm.getEwm(model.getHdid());
			code = "data:image/png;base64,"+code;
			jobj.put("base64_png",code);
			response.getWriter().print(jobj);
			return null;
		}
		request.setAttribute("hdid",model.getHdid());
		return mapping.findForward("getEwm");
	}
}
