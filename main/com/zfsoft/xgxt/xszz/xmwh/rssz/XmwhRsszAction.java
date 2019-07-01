/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.rssz;

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
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��-��������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhRsszAction extends SuperAction {
	private XmwhRsszService service = new XmwhRsszService();
	
	private static final String url = "xszz_xmwh.do?method=xmwhCx";

	/**
	 * 
	 * @����:������ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmwhRsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		String xmdm = request.getParameter("xmdm");
		String xslb = request.getParameter("xslb");
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> zzxmMap = xmwhService.getDataById(xmdm);
		request.setAttribute("xmmc", zzxmMap.get("xmmc"));
		String rskznj = xmwhService.getRskznj(xmdm);
		request.setAttribute("rskznj", rskznj);//���������꼶
		List<String>  njList = service.getNj();//�õ����а���ѧ�����꼶
		request.setAttribute("njArrList", njList);
		request.setAttribute("xslb", xslb);

		// �꼶ѧԺרҵ�༶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		myForm.setXn(Base.currXn);// ��ǰѧ��
		myForm.setXq(Base.currXq);// ����ѧ��
		myForm.setPdxn(zzxmMap.get("pdxn"));
		myForm.setPdxq(zzxmMap.get("pdxq"));
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�Ƿ���ѧ��������Ŀ
		XszzSqshService xszzSqshService = new XszzSqshService();
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		request.setAttribute("xslb", xslb);
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);
		List<HashMap<String, String>> syList = service.getSyList();//��Ժlist
		request.setAttribute("syList",syList);
		return mapping.findForward("xmwhRsszCx");
	}

	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-��������-�޸�GUIDS:{guids}")
	public ActionForward xmwhRsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String messageKey;
			messageKey = service.setZzrs(myForm);
//			messageKey = "����ɹ���";
			response.getWriter().print(getJsonMessage(messageKey));
			
			return null;
		}

		XmwhRsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
	}

	/**
	 * 
	 * @����:�������÷���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-��������-���ñ���JSON:{json}")
	public ActionForward xmwhRsszBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<XmwhRsszForm> list = JsonUtil.jsonToList(json,
					XmwhRsszForm.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//�����ʽ
				zme = request.getParameter("zme");
			}
			String rskznj = request.getParameter("rskznj");//���������꼶
			messageKey = service.fpsz(myForm, list, zme,rskznj);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}

		XmwhRsszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-��������-ɾ��XMDM:{xmdm}")
	public ActionForward xmwhRsszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");

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
	 * @���ڣ�2013-5-28 ����11:38:30
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
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		int yszrs = service.getYszrs(myForm);
		XmwhService xmwhService = new XmwhService();
		String zme = "";
		if (myForm.getXmdm() != null) {
			HashMap<String, String> hashMap = xmwhService.getDataById(myForm
					.getXmdm());
			if (hashMap != null) {
				zme = hashMap.get("zme");
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
	 * @���ڣ�2013-5-28 ����03:11:46
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
		XmwhRsszForm myForm = (XmwhRsszForm) form;
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> xyList = service.getSyxy();// ����ѧԺ��Ϣ
			ZzxmjgService zzxmjgService = new ZzxmjgService();
			List<HashMap<String, String>> tjrsList = zzxmjgService.tjrs(xmmc);
			HashMap<String, List<HashMap<String, String>>> map = new HashMap<String, List<HashMap<String, String>>>();
			map.put("xyList", xyList);
			map.put("tjrsList", tjrsList);
			map.put("xqList", Base.getXqList());
			response.getWriter().print(JSONObject.fromMap(map));
			return null;
		}

		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhRsszLnme");
	}
	
	
	/**
	 * 
	 * @����:������Ŀ�����ѯ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-22 ����01:54:24
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
		
		XmwhRsszForm myForm = (XmwhRsszForm) form; 
		XmwhService xmwhService = new XmwhService();
		
		String xmdm = request.getParameter("xmdm");
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		
		
		// �꼶ѧԺרҵ�༶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		myForm.setXn(Base.currXn);// ��ǰѧ��
		myForm.setXq(Base.currXq);// ����ѧ��
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�Ƿ���ѧ��������Ŀ
		XszzSqshService xszzSqshService = new XszzSqshService();
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		
		FormModleCommon.commonRequestSet(request);

		List<HashMap<String, String>> syList = service.getSyList();//��Ժlist
		request.setAttribute("syList",syList);
		return mapping.findForward("xmwhRsszCk");
	}

}
