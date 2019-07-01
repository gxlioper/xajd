/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����09:19:36 
 */  
package xsgzgl.gygl.gybxgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-7-13 ����09:19:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GybxRyglAction extends SuperAction<GybxRyglForm, GybxRyglService> {
  
	private static final String url = "gyglnew_bxrywh.do";
	
	@SystemAuth(url = url)
	   public ActionForward bxlbList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		   GybxRyglForm myForm = (GybxRyglForm) form;
		   String path = "gyglnew_bxrywh.do";
		   request.setAttribute("path", path);
		   if(QUERY.equalsIgnoreCase(myForm.getType())){
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath(path);
				myForm.setSearchModel(searchModel);
				GybxRyglService service = new GybxRyglService();
				List<HashMap<String,String>> result = service.getPageList(myForm);
				JSONArray dataList = JSONArray.fromObject(result);
				response.getWriter().print(dataList);
				return null;
		   }
		   FormModleCommon.commonRequestSet(request);
		   return mapping.findForward("gyglnew_bxlbrywh");
	   }
	
	@SystemAuth(url = url)
	   public ActionForward getBxlbYhList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		   GybxRyglForm myForm = (GybxRyglForm) form;
		   String path = "gyglnew_bxlbrywh.do?method=getBxlbYhList";
		   request.setAttribute("path", path);
		   if(QUERY.equalsIgnoreCase(myForm.getType())){
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath(path);
				myForm.setSearchModel(searchModel);
			   if(StringUtil.isNull(myForm.getFlag())){
					myForm.setFlag("0");
				}
			    GybxRyglService service = new GybxRyglService();
				List<HashMap<String,String>> result = service.getBxlbYhList(myForm);
				JSONArray dataList = JSONArray.fromObject(result);
				response.getWriter().print(dataList);
				return null;
		   }
		   return mapping.findForward("gybxRywhList");
	   }
	   
	@SystemAuth(url = url)
	   public ActionForward ViewBxlbYhList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		   GybxRyglForm myForm = (GybxRyglForm) form;
		   String path = "gyglnew_bxlbrywh.do?method=getBxlbYhList";
		   request.setAttribute("path", path);
		   if(QUERY.equalsIgnoreCase(myForm.getType())){
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath(path);
				myForm.setSearchModel(searchModel);
			   if(StringUtil.isNull(myForm.getFlag())){
					myForm.setFlag("1");
				}
			    GybxRyglService service = new GybxRyglService();
				List<HashMap<String,String>> result = service.getBxlbYhList(myForm);
				JSONArray dataList = JSONArray.fromObject(result);
				response.getWriter().print(dataList);
				return null;
		   }
		   return mapping.findForward("viewBxlbYhList");
	   }
	   
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
		public ActionForward yhAdd(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			 GybxRyglForm myForm = (GybxRyglForm) form;
			String messageKey  = MessageKey.SYS_SAVE_FAIL;
			if(StringUtils.isNotNull(myForm.getBxlb())){
				GybxRyglService service = new GybxRyglService();
				messageKey  = service.saveBxry(myForm);
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
		public ActionForward yhDel(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			 GybxRyglForm myForm = (GybxRyglForm) form;
			String messageKey  = MessageKey.SYS_SAVE_FAIL;
			if(StringUtils.isNotNull(myForm.getBxlb())){
				GybxRyglService service = new GybxRyglService();
				messageKey  = service.delBxry(myForm);
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
}
