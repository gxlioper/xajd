/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-21 ����11:48:38 
 */  
package com.zfsoft.xgxt.xsztz.xwtzxmjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-21 ����11:48:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XwTzXmJgAction extends SuperAction<XwTzXmJgForm, XwTzXmJgService> {
	 XwTzXmJgService service = new XwTzXmJgService();
	 private DAO Dao = DAO.getInstance();
	 private final String XYTZXMJG ="tzxmsq";
	 private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	 
	 private static final String url = "sztz_xwtzgl_xmjg.do";
	 
	 @SystemAuth(url = url)
	 public ActionForward  getXwJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		 XwTzXmJgForm model = (XwTzXmJgForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				// ��ѯ
				List<HashMap<String, String>> resultList = service.getPageList(model, user);
				
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			// Ĭ�ϸ߼���ѯ����
			SearchModel searchModel = new SearchModel();
			searchModel.setSearch_tj_xn(new String[] { Base.currXn });
			searchModel.setSearch_tj_xq(new String[] { Base.currXq });
			request.setAttribute("searchTj", searchModel);
			String path = "sztz_xwtzgl_xmjg.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
		    return mapping.findForward("xwjglist");
	 }
	 
	 @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	 @SystemLog(description = "����������չ-У����չ��Ŀ-У����չ���-ɾ��VALUES:{values}")
	 public ActionForward delSqjl(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			//���id
			String values = request.getParameter("values");
			if (!StringUtil.isNull(values)) {
				String[] ids = values.split(",");
				int num = service.runDelete(ids);
				boolean result = num > 0;
				String message = result ? MessageUtil.getText(
						MessageKey.SYS_DEL_NUM, num) : MessageUtil
						.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
			} else {
				throw new SystemException(MessageKey.SYS_DEL_NULL);
			}
			return null;
	}
	 
	 @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	 public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		    XwTzXmJgForm model = (XwTzXmJgForm) form;
		    User user = getUser(request);
			if ("stu".equals(user.getUserType())) {
				model.setXh(user.getUserName());
			}
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			// ѧ��������Ϣ��ʾ����
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYTZXMJG);
			request.setAttribute("jbxxList", jbxxList);
			request.setAttribute("xnList", Dao.getXnndList());
			request.setAttribute("xqList", Dao.getXqList());
			request.setAttribute("sskm", service.getSsKmList());
			request.setAttribute("xmjb", service.getXmJbList());
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.currXq);
			String path = "xwtzgl_xmjg.do?method=add";
			request.setAttribute("path", path);
			//������Ϣ����
			return mapping.findForward("add");
		}
	 
	 /**
		 * ������չ����������
		 */
	 @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
		public ActionForward exportData(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			XwTzXmJgForm  model = (XwTzXmJgForm) form;

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getAllList(model,
					user);// ��ѯ�����м�¼������ҳ
			
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
		 * У��������չ��Ŀ�����޸�
		 */
	 @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
		public ActionForward editSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			XwTzXmJgForm myForm = (XwTzXmJgForm) form;
			XwTzXmJgForm model = service.getModel(myForm);
			if(null!=model){
				BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			// ѧ��������Ϣ��ʾ����
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYTZXMJG);
			request.setAttribute("jbxxList", jbxxList);
			HashMap<String, String> hdmap = service.getHdMap(model);//false
			hdmap.put("sgid", model.getSqid());
			request.setAttribute("xmjbxx", model);
			request.setAttribute("hdmap", StringUtils.formatData(hdmap));
			request.setAttribute("xnList", Dao.getXnndList());
			request.setAttribute("xqList", Dao.getXqList());
			request.setAttribute("sskm", service.getSsKmList());
			request.setAttribute("xmjb", service.getXmJbList());
			String path = "xwtzgl_xmjg.do?method=editSqjg";
			request.setAttribute("path", path);
			return mapping.findForward("edit");
		}
		
		/**
		 * ����鿴
		 */
	 @SystemAuth(url = url)
		public ActionForward XmjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			XwTzXmJgForm myForm = (XwTzXmJgForm) form;
			XwTzXmJgForm model = service.getModel(myForm);
			if(null!=model){
				BeanUtils.copyProperties(myForm, model);
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			// ѧ��������Ϣ��ʾ����gzkhKhjgXx
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYTZXMJG);
			request.setAttribute("jbxxList", jbxxList);
			HashMap<String, String> hdmap = service.getHdMap(model);
			request.setAttribute("hdmap", hdmap);
			request.setAttribute("form",model);
			return mapping.findForward("view");
		}
		
	 @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	 @SystemLog(description = "����������չ-У����չ��Ŀ-У����չ���-DOTYPE��{type},XH:{xh},XMMC:{xmmc},XMJBDM:{xmjbdm},XN:{xn},XQ:{xq},SSKMDM:{sskmdm},XMKSSJ:{xmkssj},CJDD:{cjdd},LXFS:{lxfs},ZXF:{zxf},HDJX:{hdjx},SQID:{sqid}")
		public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			XwTzXmJgForm model = (XwTzXmJgForm) form;
			boolean result = false;
			String message=null;
		    User user = getUser(request);
	 		if(model.getType().equals("save")){
	 			if(service.checkExistForSave(model) == true){
	 				response.getWriter().print(getJsonMessageByKey(MessageKey.CFTX));
					return null;
	 			}
	 			model.setYlzd1(user.getRealName());
	 			model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
				result = service.runInsert(model);
			}else if(model.getType().equals("update")){
				model.setYlzd1(user.getRealName());
				result = service.runUpdate(model);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	  
}
