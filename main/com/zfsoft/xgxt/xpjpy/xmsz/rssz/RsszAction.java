/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:07:42
 */
package com.zfsoft.xgxt.xpjpy.xmsz.rssz;

import java.text.DateFormat;
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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshModel;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��-��������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:07:42
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class RsszAction extends SuperAction {

	private static final String urlJxj = "xpj_xmwh.do?method=xmwhCx&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_xmwh.do?method=xmwhCx&xmxz=2&sindex=1";
	
	/**
	 * 
	 * @����:������ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmwhRsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		request.setAttribute("rsfpnj", rsfpnj);//���������꼶
		List<String>  njList = service.getNj();//�õ����а���ѧ�����꼶
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
//		List<HashMap<String, String>> xyList = Base.getXyList();
//		request.setAttribute("xyList", xyList);
		// �꼶ѧԺרҵ�༶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		if (QUERY.equals(myForm.getType())) {
			if(Base.xxdm.equals("10704")){//�����Ƽ���ѧ�����Ƿ�����ѧ���ɲ�
				String sfyxgb = xmwhService.getSfyxgb(myForm.getXmdm());
				myForm.setSfyxgb(sfyxgb);
			}
			List<HashMap<String, String>> resultList = service
					.getRsszList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�Ƿ���ѧ��������Ŀ
		SqshService sqshService = new SqshService();
		boolean flag = sqshService.isExistsFlowData(xmdm);
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
		
		CsszService csszService = new CsszService();
		request.setAttribute("rsjsfs", csszService.getCsz("rsjsfs"));
		request.setAttribute("xmje", request.getParameter("xmje"));
		//������ʽ
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("czfs", request.getParameter("czfs"));
		
		//���-ѧԺ�������ý���������ѣ����Ի���
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszCx");
	}

	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-��������-����XMDM��{xmdm}")
	public ActionForward xmwhRsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		RsszService service = new RsszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String messageKey = null;
			User user = getUser(request);
			if(myForm.getRsfpfs() != null && myForm.getRsfpfs().equals(Constants.RSKZFW_XX)){//ѧУ
				messageKey = service.setZzrsForXx(myForm,user);
			}else{
				messageKey = service.setZzrs(myForm,user);
			}
//			messageKey = "����ɹ���";
			response.getWriter().print(getJsonMessage(messageKey));
			
			return null;
		}
		
		if("xyrssz".equals(myForm.getCzfs()) && Base.xxdm.equals(Globals.XXDM_ZJDX)){  //�㽭��ѧ���Ի� ��������˺�ʱ��
			User user = getUser(request);
			myForm.setZd1(user.getUserName());
			DateFormat date = DateFormat.getDateTimeInstance();//��ȷ��ʱ����
			myForm.setZd2(date+"");
		}

		RsszModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @����:�������÷���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-��������-��������-����XMDM��{xmdm}")
	public ActionForward xmwhRsszBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<RsszModel> list = JsonUtil.jsonToList(json,
					RsszModel.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//�����ʽ
				zme = request.getParameter("zme");
			}
			String rsfpnj = request.getParameter("rsfpnj");//���������꼶
			if(Base.xxdm.equals("10704")){//�����Ƽ���ѧ�����Ƿ�����ѧ���ɲ�
				String sfyxgb = new XmwhService().getSfyxgb(myForm.getXmdm());
				myForm.setSfyxgb(sfyxgb);
			}
			messageKey = service.fpsz(myForm, list, zme,rsfpnj,user);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}

		RsszModel model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = {urlJxj,urlBz},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-��������-ɾ��XMDM��{xmdm}")
	public ActionForward xmwhRsszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		RsszService service = new RsszService();

		if (!StringUtil.isNull(xmdm)) {
			service.runDeleteByXmdm(xmdm);
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @����:��ѯ��Ŀ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
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
	public ActionForward xmwhRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		RsszService service = new RsszService();
		int yszrs = service.getYszrs(myForm);
		XmwhService xmwhService = new XmwhService();
		String zme = "";
		if (myForm.getXmdm() != null) {
			HashMap<String, String> hashMap = xmwhService.getDataById(myForm
					.getXmdm());
			if (hashMap != null) {
				zme = hashMap.get("rsfpz");
			}
		}

		response.setContentType("text/html;charset=gbk");
		Map<String, String> map = new HashMap<String, String>();
		map.put("yszrs", yszrs + "");
		map.put("zme", zme);
		response.getWriter().print(JSONObject.fromMap(map));
		return null;
	}

	/**
	 * 
	 * @����:��������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
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
	public ActionForward xmwhRsszLnme(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		RsszService service = new RsszService();
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> xyList = service.getSyxy();// ����ѧԺ��Ϣ
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> tjrsList = pjjgService.tjrs(xmmc);
			HashMap<String, List<HashMap<String, String>>> map = new HashMap<String, List<HashMap<String, String>>>();
			map.put("xyList", xyList);
			map.put("tjrsList", tjrsList);
			map.put("xqList", Base.getXqList());
			response.getWriter().print(JSONObject.fromMap(map));
			return null;
		}

		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszLnme");
	}
	
	
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��������
	 * @���ߣ�cq[���ţ�785]
	 * @���ڣ�2014-1-17 ����02:52:15
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
	public ActionForward xmwhRsszCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		String rsfpnj = xmwhService.getRsfpnj(xmdm);
		request.setAttribute("rsfpnj", rsfpnj);//���������꼶
		List<String>  njList = service.getNj();//�õ����а���ѧ�����꼶
		request.setAttribute("njList", njList);
		request.setAttribute("njArrList", njList);
		// �꼶ѧԺרҵ�༶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getRsszList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�Ƿ���ѧ��������Ŀ
		SqshService sqshService = new SqshService();
		boolean flag = sqshService.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszCk");
	}
    /**
     * 
     * @����:ajax��֤�������޺ͽ������
     * @���ߣ�xiaxia[���ţ�1104]
     * @���ڣ�2015-3-19 ����10:52:04
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
	public ActionForward rsszCheckAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsszModel myForm = (RsszModel) form;
		User user = getUser(request);
		RsszService service = new RsszService();
			List<HashMap<String, String>> resultList = service
					.getJxjze(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		
	}
}
