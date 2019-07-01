/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xgxt.gygl.sspy.pysq;

import java.io.File;
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
import xgxt.gygl.sspy.cssz.SspycsszForm;
import xgxt.gygl.sspy.cssz.SspycsszService;
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
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;

/**
 * @className	�� SspysqAction
 * @description	�� ������������Action��
 * @author 		��CP��1352��
 * @date		�� 2018-4-27 ����03:11:51
 * @version 	V1.0
 */
public class SspysqAction extends SuperAction{
	private final static String url = "sspy_sq.do"; 
	SspysqService service = new SspysqService();
	
	
	/**
	 * @description	�� ��ѯ�б�
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-27 ����04:17:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward pySqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SspysqForm model = (SspysqForm) form;
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
		SspycsszService csszService = new SspycsszService();
		SspycsszForm jcszModel = csszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
	
		return mapping.findForward("pySqList");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-27 ����05:51:16
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
			SspysqForm model = (SspysqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			//��λ��Ϣ--ȡ�������춯ģ��
			YdsqService ydsqService = new YdsqService();
			HashMap<String,String> cwxxMap = ydsqService.getCwxxForXh(model.getXh());
			request.setAttribute("cwxxData",cwxxMap);
			
			List<HashMap<String,String>> pyxmList = service.getPyxmList();
			request.setAttribute("pyxmList", pyxmList);
			request.setAttribute("lddm",cwxxMap.get("lddm"));
			request.setAttribute("qsh",cwxxMap.get("qsh"));
		if (SAVE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			if (StringUtils.isNull(cwxxMap.get("qsh"))) {
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_GYGL_WRZ));
			}else {
				model.setXn(Base.currXn);
				model.setXq(Base.currXq);
				model.setLddm(cwxxMap.get("lddm"));
				model.setQsh(cwxxMap.get("qsh"));
				boolean isExist = service.isExist(model);
				if(!isExist){
					super.resetToken(request);
					String sqid = UniqID.getInstance().getUniqIDHash();
					model.setSqid(sqid);
					boolean result = service.saveSspy(model);
					String messageKey = "";
					if(SAVE.equalsIgnoreCase(model.getType())){
						messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					}else{
						messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
					}
					response.getWriter().print(getJsonMessageByKey(messageKey));
				}else{
					response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_GYGL_SSPYCF));
				}
			}
			
			return null;
		}
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		String path = "sspysq.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("zjpysq");
	}

	
	
	/**
	 * @description	�� ���������Ա��λ��Ϣ
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-28 ����09:59:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ldqsxx = request.getParameter("ldqsxx");//lddm+qsh
		Map<String, String> rs = service.getQsForPk(ldqsxx);// ������ϸ��Ϣ
		List<HashMap<String, String>> list = service.getCwForQs(ldqsxx);//��ȡ����ѧ����Ϣ
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @description	�� �޸�
	 * @author 		��CP��1352��
	 * @date 		��2018-4-28 ����10:43:09
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
			SspysqForm model = (SspysqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			//��λ��Ϣ--ȡ�������춯ģ��
			YdsqService ydsqService = new YdsqService();
			HashMap<String,String> cwxxMap = ydsqService.getCwxxForXh(model.getXh());
			request.setAttribute("cwxxData",cwxxMap);
			
			List<HashMap<String,String>> pyxmList = service.getPyxmList();
			request.setAttribute("pyxmList", pyxmList);
			request.setAttribute("lddm",cwxxMap.get("lddm"));
			request.setAttribute("qsh",cwxxMap.get("qsh"));
		if (UPDATE.equalsIgnoreCase(model.getType())||SUBMIT.equalsIgnoreCase(model.getType())) {
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setLddm(cwxxMap.get("lddm"));
			model.setQsh(cwxxMap.get("qsh"));
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.updatSspy(model);
        		String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_GYGL_SSPYCF));
        	}
			return null;
		}
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		SspysqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("sqid", updateForm.getSqid());
		String path = "sspysq.do?method=update";
		request.setAttribute("path", path);
		return mapping.findForward("xgpysq");
	}
	/**
	 * @description	�� �鿴
	 * @author 		�� CP��1352��
	 * @date 		��2018-5-3 ����04:52:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		SspysqForm model = (SspysqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//��λ��Ϣ--ȡ�������춯ģ��
		YdsqService ydsqService = new YdsqService();
		HashMap<String,String> cwxxMap = ydsqService.getCwxxForXh(model.getXh());
		request.setAttribute("cwxxData",cwxxMap);
		
		List<HashMap<String,String>> pyxmList = service.getPyxmList();
		request.setAttribute("pyxmList", pyxmList);
		request.setAttribute("lddm",cwxxMap.get("lddm"));
		request.setAttribute("qsh",cwxxMap.get("qsh"));
		//ѧ�� ѧ��
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		HashMap<String,String> rs = service.getInfoBySqid(model.getSqid());
		request.setAttribute("rs", rs);
		request.setAttribute("filepath", rs.get("filepath"));
		return mapping.findForward("ckpysq");
	}
	/**
	 * @description	�� ɾ��
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-28 ����10:42:58
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
			String[] mess = service.deleteSspy(values.split(","));
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
	 * @date 		��2018-4-28 ����11:37:59
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
		SspysqForm model = (SspysqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setSqid(sqid);
		model.setXh(xh);
		//�ж��ύʱ����Ƿ񿪷�
		boolean result = service.submitSspy(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-28 ����11:37:45
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
		boolean result = service.cancelSspy(sqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			SspysqForm model = new SspysqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateSspysq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-5-3 ����05:19:16
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
		SspysqForm model = (SspysqForm) form;
		
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
