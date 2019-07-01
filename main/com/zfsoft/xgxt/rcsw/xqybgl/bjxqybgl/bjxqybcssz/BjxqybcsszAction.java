/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-23 ����12:02:20 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybcssz;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_�༶�±�����ģ��
 * @�๦������: TODO(������ҽҩ_�༶�±�_��������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-23 ����12:02:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybcsszAction extends
		SuperAction<BjxqybcsszForm, BjxqybcsszService> {
	private static final String url = "rcsw_xqybgl_bjxqybgl_bjxqybcssz.do";
	
	/**
	 * 
	 * @����:TODO(ѧ���±�����-�༶�±�����-��������-�б�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����12:08:18
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
	public ActionForward bjxqybcssz(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		BjxqybcsszForm myForm = (BjxqybcsszForm) form;
		BjxqybcsszService service = new BjxqybcsszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybcssz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		BjxqybcsszForm model = service.getModel();
		
		if(model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		return mapping.findForward("bjxqybcssz");		
	}
	
	/**
	 * 
	 * @����:TODO(ѧ���±�����-�༶�±�����-��������-����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-23 ����05:19:13
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
	@SystemLog(description="�����ճ�����-ѧ���±�����-�༶�±�����-��������-����")
	public ActionForward saveBjxqybcssz(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		BjxqybcsszForm myForm = (BjxqybcsszForm)form;
		BjxqybcsszService service = new BjxqybcsszService();		
		boolean result = service.saveBjxqybcssz(myForm);		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
}
