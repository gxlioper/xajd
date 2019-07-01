/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-31 ����05:01:23 
 */  
package com.zfsoft.xgxt.xpjpy.xmsz.tjsz;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �������� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-31 ����05:01:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszAction extends SuperAction {
	private TjszService service = new TjszService();
	private String messageKey;

	private static final String urlJxj = "xpj_pjdm.do?method=xmlxList&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_pjdm.do?method=xmlxList&xmxz=2&sindex=1";
	
	/**
	 * 
	 * @����:������ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhTjszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		
		
		//�Ƿ���ѧ��������Ŀ
		SqshService sqshService = new SqshService();
		boolean flag = sqshService.isExistsFlowData(xmdm);
		
		String flagpath = request.getParameter("flagpath");
		//�Ƿ��������ж�
		if(StringUtils.isNotNull(flagpath) && "jtpj".equalsIgnoreCase(flagpath)){
			request.setAttribute("flagpath", request.getParameter("flagpath"));
			xmmc = xmwhService.getNameByIdJtpj(xmdm);
			flag = sqshService.isExistsFlowDatajtpj(xmdm);
		}else{
			request.setAttribute("flagpath","");
		}
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("spzt", flag);
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		String path= null;
		if("2".equals(xmxz))
		{
			path= urlBz;
		}else{
			path= urlJxj;
		}

		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// �꼶ѧԺרҵ�༶
		return mapping.findForward("xmwhTjszCx");
	}

	/**
	 * 
	 * @����:��ѯ���м�¼������json��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward xmwhTjszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String flagpath = request.getParameter("flagpath");
		TjszViewForm viewForm = new  TjszViewForm();
		if(StringUtils.isNull(flagpath)){
			 viewForm = service.getAll(xmdm);
		}else{
			 viewForm = service.getJtpj(xmdm, "002");
		}
		
		JSONObject json = JSONObject.fromBean(viewForm);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����:��ѯѧ��ѧ�ڼ�¼������json��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward xmwhTjszXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszModel myForm = (TjszModel) form;
		if (QUERY.equals(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			TjszViewForm viewForm = service.getXn(xmdm);
			JSONObject json = JSONObject.fromBean(viewForm);
			response.getWriter().print(json);
			return null;
		}
		String xnVal = request.getParameter("xnVal");
		request.setAttribute("xnVal", xnVal);
		String zqlx = request.getParameter("zqlx");
		request.setAttribute("zqlx", zqlx);
		CsszDao csszDao = new CsszDao();
		request.setAttribute("pjzq", csszDao.getCsz("pjzq"));
		request.setAttribute("knszq", MessageUtil.getText("xszz.knsrd.sqzq"));
		String tjdm = request.getParameter("tjdm");
		request.setAttribute("tjdm", tjdm);
		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhTjszXn");
	}
	
	/**
	 * 
	 * @����:����ֵ����ѡ��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-31 ����08:54:54
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xmwhTjszTjzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("xmwhTjszTjzDiv");
	}

	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-��������-����XMDM��{xmdm}")
	public ActionForward xmwhTjszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszModel myForm = (TjszModel) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			String json = request.getParameter("json");
			boolean result = false;
			try {
				List<TjszModel> list = JsonUtil.jsonToList(json,
						TjszModel.class);
				if (list != null && list.size() > 0) {
					result = service.saveOrUpdate(xmdm, list);
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = MessageKey.SYS_SAVE_SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();// //�쳣��ӡ����������////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			
			String message =  MessageUtil.getText(messageKey);
			Map<String,String> map = new HashMap<String,String>();
			map.put("message", message);
			map.put("success", String.valueOf(result));
			//����ɹ�ʱ�Ż���checkbfh
			if(result&&!checkbfh(xmdm)){
				map.put("bfhlist", "y");
			}
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}

		TjszModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-��������-ɾ��XMDM��{xmdm}")
	public ActionForward xmwhTjszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String tjdm = request.getParameter("tjdm");

		if (!StringUtil.isNull(xmdm) && !StringUtil.isNull(tjdm)) {
			service.delDeal(xmdm,tjdm);//ɾ�����еĹ�����
		} 
		return null;

	}
	
	/**
	 * @��������������޸ĺ��Ƿ���ѧ��������������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��17�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	private boolean checkbfh(String xmdm) throws Exception{
		TjszService service = new TjszService();
		List<String> list=service.getbfhxhList(xmdm);
		return list.isEmpty();
	}
	
	/**
	 * @�������޸���Ŀ����������еĲ�������������ѧ���б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��16�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward getbfhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjszModel model = (TjszModel) form;
		String xmdm=request.getParameter("xmdm");
		if("query".equals(request.getParameter("type"))){
			
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			TjszService service = new TjszService();
			List<String> xhList=service.getbfhxhList(xmdm);
			List<HashMap<String,String>> resultList=service.getbfhList(model, xmdm, xhList);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String xmmc=request.getParameter("xmmc");
		request.setAttribute("path", "xg_xlzx_ybmxslist.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		return mapping.findForward("xmwhbfhList");
	}

}
