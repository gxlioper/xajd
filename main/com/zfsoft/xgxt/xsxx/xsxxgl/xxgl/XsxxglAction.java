/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import java.io.File;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.qgzx.kycxgl.kyxmgl.KyxmglService;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.QueryDataService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhService;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;
import com.zfsoft.xgxt.rcsw.cwsjcx.CwsjService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgService;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgForm;
import com.zfsoft.xgxt.xsxx.kzxxgl.jg.XsxxKzxxglJgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg.ZyfwJgService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgService;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgService;
import com.zfsoft.xgxt.zxdk.syddk.SyddkService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��-�������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XsxxglAction extends SuperAction {
	private String messageKey;

	private static final String url = "xsxx_xsxxgl_cxzxs.do";
	// ѧ������ҵ֤��ӡѧУ
	private static final String[] XSZ_XXDMS = { "10220" };
	
	/**
	 * 
	 * @����:��У����Ϣ��ѯ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xsxxglCxZxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "xsxx_xsxxgl_cxzxs.do";
		request.setAttribute("path", path);
		request.setAttribute("xszFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // ѧ��֤
		request.setAttribute("dyxwzsFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // ��һѧλ֤��
		request.setAttribute("dexwzsFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // �ڶ�ѧλ֤��
		request.setAttribute("zsbzsFlag", Arrays.asList(XSZ_XXDMS).contains(Base.xxdm)); // ר����֤��
		request.setAttribute("method", request.getParameter("method"));
		FormModleCommon.commonRequestSet(request);
		if("13871".equals(Base.xxdm)){
			HttpSession session = request.getSession();
			String isFdy = String.valueOf(session.getAttribute("isFdy"));
			String isBzr = String.valueOf(session.getAttribute("isBzr"));
			if("true".equals(isFdy) || "true".equals(isBzr)){
				request.setAttribute("bzrfdy", "true");
			}else{
				request.setAttribute("bzrfdy", "false");
			}
		}
		return mapping.findForward("xsxxglCxZxs");
	}

	/**
	 * 
	 * @����:����У����Ϣ��ѯ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xsxxglCxFzxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		model.setSfzx("0");// ����У
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xsxxgl_cxfzxs.do";
		request.setAttribute("path", path);
		request.setAttribute("method", request.getParameter("method"));
		FormModleCommon.commonRequestSet(request);
		if("10143".equals(Base.xxdm)){
			//Ĭ�ϸ߼���ѯ����
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_xj(new String[]{ "��ѧ��" });
			request.setAttribute("searchTj", searchModel);
		}
		return mapping.findForward("xsxxglCxFzxs");
	}

	/**
	 * 
	 * @����:��ѯ����ֶ������б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-25 ����09:29:29
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
	public ActionForward getCxjgzdpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		List<HashMap<String, String>> cxjgzdpzList = service.getCxjgzdpzList();
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(cxjgzdpzList));
		return null;
	}

	/**
	 * ѧ�������ʼ��
	 */
	@SystemAuth(url = url)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ-ѧ�������ʼ��VALUES:{values}")
	public ActionForward mmcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		String values = request.getParameter("values");
		String mm1 = request.getParameter("mm1");
		String bz = request.getParameter("bz");
		if (UPDATE.equals(myForm.getType())) {
			boolean flag = false;
			flag = service.cshYhmm(mm1, values,bz);
			String ip = request.getRemoteAddr();
			String czr = getUser(request).getUserName();
			String xgmmxh = values;
			if(flag){
				service.runInsertMmXgLog(ip, czr, xgmmxh);
			}
			messageKey = flag ? MessageInfo.MESSAGE_INIT_SUCCESS
					: MessageInfo.MESSAGE_INIT_FALSE;
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("mmcsh");
	}
	/**
	 * ѧ�������ʼ��-���ݲ�ѯ�������
	 */
	@SystemAuth(url = url)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ-ѧ�������ʼ��-���ݲ�ѯ�������")
	public ActionForward mmcshPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		String mm1 = request.getParameter("mm1");
		String bz = request.getParameter("bz");
		if (UPDATE.equals(myForm.getType())) {
			//���ɸ߼���ѯ����		
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xsxx_xsxxgl_cxzxs.do");
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getXsxxAllList(myForm, user);//��ѯ�����м�¼������ҳ
			StringBuilder values = new StringBuilder();
			for (int i = 0; i < resultList.size(); i++) {
				values.append(resultList.get(i).get("xh"));
				if(i < resultList.size() - 1){
					values.append(",");
				}
			}
			boolean flag = false;
			flag = service.cshYhmm(mm1, values.toString(),bz);
			if(flag){
				String ip = request.getRemoteAddr();
				String czr = getUser(request).getUserName();
				String xgmmxh = values.toString();
				service.runInsertMmXgLog(ip, czr, xgmmxh);
			}
			messageKey = flag ? MessageInfo.MESSAGE_INIT_SUCCESS : MessageInfo.MESSAGE_INIT_FALSE;
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		request.setAttribute("len", request.getParameter("len"));
		request.setAttribute("path", "xsxx_xsxxgl_cxzxs.do");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("mmcshPl");
	}

	/**
	 * 
	 * @����:���ӷ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����10:22:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ-����XH:{xh},BJDM:{bjdm},XM:{xm}")
	public ActionForward xsxxglZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			User user = getUser(request);
			model.setUser(user);
			CommService commService = new CommService();
			HashMap<String, String> valueMap = commService.getValueMap(request,
					service.getColumnByTable("xsxxb"));
			boolean result = service.saveRecord(model, valueMap);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		this.saveToken(request);
		return mapping.findForward("xsxxglZj");
	}
/**
 * @description	�� ͬ�����ͨ����ʧ������
 * @author 		�� CP��1352��
 * @date 		��2017-11-30 ����08:34:59
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward ycsjTs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		List<HashMap<String, String>> sqidlist = service.getycsjList();
		boolean result = service.ycsjTs(sqidlist);
		String messageKey = result ? MessageKey.SYS_TB_SUCCESS
				: MessageKey.SYS_TB_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����10:22:26
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("unchecked")
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ-�޸�XH:{xh},BJDM:{bjdm},XM:{xm}")
	public ActionForward xsxxglXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		HashMap xsxxMap = service.getXsxxByXhForUpdate(model.getXh());

		String gndm = "xsxx_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);
		if (QUERY.equalsIgnoreCase(model.getType())) {

			//ͬ��ͬѧ
			if (srcList.contains("tbtxList")) {
				List<HashMap<String, String>> tbtxList = service
						.getTbtxList(model.getXh(),(String)xsxxMap.get("bjdm"));
				xsxxMap.put("tbtxList", tbtxList);
			}


			if (srcList.contains("jtcyxxList")) {
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}

			if (srcList.contains("xlshjlxxList")) {
				// ѧ����ᾭ����Ϣ
				List<HashMap<String, String>> xlshjlxxList = service
						.getXlshjlList(model.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);

			}

			// ��ѵ��Ϣ
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service
						.getPxxxList(model.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}

			// �����
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service
						.getHjqkList(model.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// ���£������
			if (srcList.contains("hjqkxxNewList")) {
				List<HashMap<String, String>> hjqkxxNewList = service
				.getHjqkNewList(model.getXh());
				xsxxMap.put("hjqkxxNewList", hjqkxxNewList);
			}
			
			// ѧ��У��������������·ְҵ����ѧԺ��
			if (srcList.contains("xsxwhjqkList")) {
				List<HashMap<String, String>> xsxwhjqkList = service.getXsxwhjqkList(model.getXh());
				xsxxMap.put("xsxwhjqkList", xsxwhjqkList);
			}
			if("10698".equalsIgnoreCase(Base.xxdm)){
				//���⾭��
				if (srcList.contains("hwjlList_10698")) {
					List<HashMap<String, String>> hwjlList = service.getHwjlList(model.getXh());
					xsxxMap.put("hwjlList_10698", hwjlList);
				}
			}
			if("10466".equalsIgnoreCase(Base.xxdm)){
				//���ʵ�����
				if (srcList.contains("shsjqkList_10466")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(model.getXh());
					xsxxMap.put("shsjqkList_10466", shsjqkList);
				}
			}
			//�����Ƽ���ѧ
			if("10704".equalsIgnoreCase(Base.xxdm)){
				// �ȼ����Գɼ�
				if (srcList.contains("djkscjList_10704")) {
					List<HashMap<String, String>> djkscjList = service.getDjkscjList(model.getXh());
					xsxxMap.put("djkscjList_10704", djkscjList);
				}
				//ѧ���ɲ�����
				if (srcList.contains("xsgbjlList_10704")) {
					List<HashMap<String, String>> xsgbjlList = service.getXsgbjlList(model.getXh());
					xsxxMap.put("xsgbjlList_10704", xsgbjlList);
				}
				//���ʵ�����
				if (srcList.contains("shsjqkList_10704")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(model.getXh());
					xsxxMap.put("shsjqkList_10704", shsjqkList);
				}
				//��������������Ϣ
				if (srcList.contains("cgcjjlList_10704")) {
					List<HashMap<String, String>> cgcjjlList = service.getCgcjjlList(model.getXh());
					xsxxMap.put("cgcjjlList_10704", cgcjjlList);
				}

				//ѧ��������Ϣ���������� ������Ŀ �����ɹ�
				//��������
				if (srcList.contains("fblwList_10704")) {
					List<HashMap<String, String>> fblwList = service.getFblwList(model.getXh());
					xsxxMap.put("fblwList_10704", fblwList);
				}
				//������Ŀ
				if (srcList.contains("kyxmList_10704")) {
					List<HashMap<String, String>> kyxmList = service.getKyxmList(model.getXh());
					xsxxMap.put("kyxmList_10704", kyxmList);
				}
				//�����ɹ�
				if (srcList.contains("qtcgList_10704")) {
					List<HashMap<String, String>> qtcgList = service.getQtcgList(model.getXh());
					xsxxMap.put("qtcgList_10704", qtcgList);
				}
				//�����
				if (srcList.contains("hjqkList_10704")) {
					List<HashMap<String, String>> hjqkList = service.getHjqkListForXakj(model.getXh());
					xsxxMap.put("hjqkList_10704", hjqkList);
				}
			}

			// ���ֹ�ҵְҵ����ѧԺ
			if(Base.xxdm.equalsIgnoreCase("12903")) {
				// ����ʵϰ��Ϣ
				if (srcList.contains("ggsxjlList")) {
					List<HashMap<String, String>> ggsxjlList = service
					.getGgsxjlList(model.getXh());
					xsxxMap.put("ggsxjlList", ggsxjlList);
				}
				// ����ʵϰ��Ϣ
				if (srcList.contains("dgsxjlList")) {
					List<HashMap<String, String>> dgsxjlList = service
					.getDgsxjlList(model.getXh());
					xsxxMap.put("dgsxjlList", dgsxjlList);
				}
			}
			//ɽ���ƾ�
			if("10125".equalsIgnoreCase(Base.xxdm)) {
				//��ѧ�о����
				if(srcList.contains("kxyjList")) {
					xsxxMap.put("kxyjList", service.getKxyjList(model.getXh()));
				}
				//�����о�
				if(srcList.contains("ktyjList")) {
					xsxxMap.put("ktyjList", service.getKtyjList(model.getXh()));
				}
				//���´�ҵ��Ŀ
				if(srcList.contains("cxcyxmList")) {
					xsxxMap.put("cxcyxmList", service.getCxcyxmList(model.getXh()));
				}
				//ѧ�ƾ���
				if(srcList.contains("xkjsList")) {
					xsxxMap.put("xkjsList", service.getXkjsList(model.getXh()));
				}
				//����֤��
				if(srcList.contains("jnzsList")) {
					xsxxMap.put("jnzsList", service.getJnzsList(model.getXh()));
				}
			}

			//����ְҵ
			if("13265".equalsIgnoreCase(Base.xxdm)) {
				if(srcList.contains("xsjfList")) {
					xsxxMap.put("xsjfList", service.getXsjfList(model.getXh()));
				}
			}

			// ����ʦ����ѧ
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// ���˼���
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(model.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// ��������
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(model.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}

			}
			/*//�½���ҵְҵ����ѧԺ
			if("5046".equalsIgnoreCase(Base.xxdm)){
				//���ʵ�����
				if (srcList.contains("ztxxList_5046")) {
					List<HashMap<String, String>> ztxxList_5046 = service.getShsjqkList(model.getXh());
					xsxxMap.put("ztxxList_5046", ztxxList_5046);
				}
			}*/
			// ����ѧԺ
			if("11354".equalsIgnoreCase(Base.xxdm)) {
				// ѧ��������Ϣ
				if(srcList.contains("xsyhxxList")) {
					List<HashMap<String, String>> xsyhxxList = service
					.getXsyhxxList(model.getXh());
					xsxxMap.put("xsyhxxList", xsyhxxList);
				}
			}

			// ������ҽҩ��ѧ
			if("10026".equalsIgnoreCase(Base.xxdm)) {
				// ���˽�����Ϣ
				if(srcList.contains("grhjxxList")) {
					List<HashMap<String, String>> grhjxxList = service
					.getGrhjxxList(model.getXh());
					xsxxMap.put("grhjxxList", grhjxxList);
				}
				//���������Ϣ
				if(srcList.contains("cyktxxList_10026")) {
					List<HashMap<String, String>> cyktxxList_10026 = service
					.getCyktxxList(model.getXh());
					xsxxMap.put("cyktxxList_10026", cyktxxList_10026);
				}
			}

			//�Ͼ��ߵ�ְҵ����ѧУ
			//��ȡʵϰ��Ϣ
			if("10874".equalsIgnoreCase(Base.xxdm)){
				if(srcList.contains("sxxxList")) {
					List<HashMap<String, String>> sxxxList = service
					.getSxxxList(model.getXh());
					xsxxMap.put("sxxxList", sxxxList);
				}
				//��ȡ������Ϣ
				if(srcList.contains("fwxxList")) {
					List<HashMap<String, String>> fwxxList = service
					.getFwxxList(model.getXh());
					xsxxMap.put("fwxxList", fwxxList);
				}
			}

			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		} else if (UPDATE.equals(model.getType())) {
			model.setUser(user);
			CommService commService = new CommService();
			HashMap<String, String> valueMap = commService.getValueMap(request,
					service.getColumnByTable("xsxxb"));
			HashMap<String, String> xsfzxxValueMap = commService.getValueMap(
					request, service.getColumnByTable("xsfzxxb"));
			// ��ͥ��ַ:ȡxsxxb��jtdz���ɰ棺ȡxsfzxxb��jtszd��
//			valueMap.put("jtdz", xsfzxxValueMap.get("jtszd"));
			xsfzxxValueMap.put("jtszd", valueMap.get("jtdz"));

			List<HashMap<String,String>> insLsjlList = new ArrayList<HashMap<String, String>>();
			//�������ڵء��־�ס�� �޸Ĳ�����ʷ��¼��
			if(!StringUtils.equals((String) xsxxMap.get("hkszd"),valueMap.get("hkszd"))){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("xh",model.getXh());
				map.put("zd","hkszd");
				map.put("zdmc","�������ڵ�");
				map.put("zdz",service.getSzdmc(valueMap.get("hkszd")));
				map.put("xgqz",service.getSzdmc((String)xsxxMap.get("hkszd")));
				map.put("czr",user.getUserName());
				map.put("czrxm",user.getRealName());
				map.put("czsj", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
				insLsjlList.add(map);
			}
			if(!StringUtils.equals((String) xsxxMap.get("xwzsxxdz"),valueMap.get("xwzsxxdz"))){
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("xh",model.getXh());
				map.put("zd","xwzsxxdz");
				map.put("zdmc","�־�ס��");
				map.put("zdz",valueMap.get("xwzsxxdz"));
				map.put("xgqz",(String)xsxxMap.get("xwzsxxdz"));
				map.put("czr",user.getUserName());
				map.put("czrxm",user.getRealName());
				map.put("czsj", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
				insLsjlList.add(map);
			}
			if(insLsjlList != null){
				boolean rs = service.insLsjl(insLsjlList);
			}
			boolean result = service.updateRecord(model, valueMap,
					xsfzxxValueMap);
			if("13943".equals(Base.xxdm)){
				XsxxtyglService xsxxtyglservice = new XsxxtyglService();
				boolean resultly = xsxxtyglservice.setYxyj(model);
			}
			if (srcList.contains(Constants.ZDYBD_JTCYXX)) {
				String jtcyxxJson = request
						.getParameter(Constants.ZDYBD_JTCYXX);
				List jtcyxxList = JsonUtil.jsonToList(jtcyxxJson,
						JtcyxxModel.class);
				result = service.updateJtcyxx(model.getXh(), jtcyxxList,user);// ��ͥ��Ա��Ϣ����

				String jtcyDelJson = request.getParameter("jtcyDelList");
				if(StringUtils.isNotNull(jtcyDelJson)){
					List jtcyDelList = JsonUtil.jsonToList(jtcyDelJson,JtcyxxModel.class);
					boolean flag = service.jtcyDelLsjl(model.getXh(), jtcyDelList,user);// ɾ���ļ�ͥ��Ա��Ϣ��������ʷ��¼��
				}
			}

			/**
			 * ����ѧ����ᾭ����Ϣ����
			 */
			if (srcList.contains(Constants.ZDYBD_XLSHJLXX)) {

				String xlshjlxxJson = request
						.getParameter(Constants.ZDYBD_XLSHJLXX);
				List xlshjlxxList = JsonUtil.jsonToList(xlshjlxxJson,
						XlshjlModel.class);
				result = service.updateXlshjlxx(model.getXh(), xlshjlxxList); // ѧ��ѧ����ᾭ����Ϣ����

			}
			//������ͨ��ѧ
			if("10698".equalsIgnoreCase(Base.xxdm)){
				//���⾭��
				if (srcList.contains("hwjlList_10698")) {
					String hwjlJson = request.getParameter("hwjlList_10698");
					List<HwjlModel> hwjlList = JsonUtil.jsonToList(hwjlJson, HwjlModel.class);
					result = service.updateHwjl(model.getXh(), hwjlList);
				}
			}
			// ��ѵ��Ϣ����
			if (srcList.contains("pxxxList")) {
				String pxxxJson = request.getParameter("pxxxList");
				List pxxxList = JsonUtil.jsonToList(pxxxJson, PxxxModel.class);
				result = service.updatePxxx(model.getXh(), pxxxList);
			}

			// ���������
			if (srcList.contains("rxqhjqkList")) {
				String hjqkJson = request.getParameter("rxqhjqkList");
				List hjqkList = JsonUtil.jsonToList(hjqkJson, HjqkModel.class);
				result = service.updateHjqk(model.getXh(), hjqkList);
			}
			
			// ���£����������
			if (srcList.contains("hjqkxxNewList")) {
				String hjqkJson = request.getParameter("hjqkxxNewList");
				List hjqkList = JsonUtil.jsonToList(hjqkJson, HjqkNewModel.class);
				result = service.updateHjqkNew(model.getXh(), hjqkList);
			}
			
			// ��У�⣩��������棨������·ְҵ����ѧԺ��
			if (srcList.contains("xsxwhjqkList")) {
				String hjqkJson = request.getParameter("xsxwhjqkList");
				List<XsxwhjqkModel> hjqkList = JsonUtil.jsonToList(hjqkJson, XsxwhjqkModel.class);
				result = service.updateXsxwhjqk(model.getXh(), hjqkList);
			}
		
			if("10466".equalsIgnoreCase(Base.xxdm)){
				//���ʵ�����
				if (srcList.contains("shsjqkList_10466")) {
					String shsjqkJson = request.getParameter("shsjqkList_10466");
					List<ShsjqkModel> shsjqkList = JsonUtil.jsonToList(shsjqkJson, ShsjqkModel.class);
					result = service.updateShsjqk(model.getXh(), shsjqkList);
				}
			}
			//�����Ƽ���ѧ
			if("10704".equalsIgnoreCase(Base.xxdm)){
				// �ȼ����Գɼ�
				if (srcList.contains("djkscjList_10704")) {
					String djkscjJson = request.getParameter("djkscjList_10704");
					List<DjkscjModel> djkscjList = JsonUtil.jsonToList(djkscjJson, DjkscjModel.class);
					result = service.updateDjkscj(model.getXh(), djkscjList);
				}
				//ѧ���ɲ�����
				if (srcList.contains("xsgbjlList_10704")) {
					String xsgbjlJson = request.getParameter("xsgbjlList_10704");
					List<XsgbjlModel> xsgbjlList = JsonUtil.jsonToList(xsgbjlJson, XsgbjlModel.class);
					result = service.updateXsgbjl(model.getXh(), xsgbjlList);
				}
				//���ʵ�����
				if (srcList.contains("shsjqkList_10704")) {
					String shsjqkJson = request.getParameter("shsjqkList_10704");
					List<ShsjqkModel> shsjqkList = JsonUtil.jsonToList(shsjqkJson, ShsjqkModel.class);
					result = service.updateShsjqk(model.getXh(), shsjqkList);
				}
				//��������������Ϣ
				if (srcList.contains("cgcjjlList_10704")) {
					String cgcjjlJson = request.getParameter("cgcjjlList_10704");
					List<CgcjjlModel> cgcjjlList = JsonUtil.jsonToList(cgcjjlJson, CgcjjlModel.class);
					result = service.updateCgcjjl(model.getXh(), cgcjjlList);
				}
				
				//ѧ��������Ϣ���������� ������Ŀ �����ɹ�
				//��������
				if (srcList.contains("fblwList_10704")) {
					String fblwJson = request.getParameter("fblwList_10704");
					List<FblwModel> fblwList = JsonUtil.jsonToList(fblwJson, FblwModel.class);
					result = service.updateFblw(model.getXh(), fblwList);
				}
				//������Ŀ
				if (srcList.contains("kyxmList_10704")) {
					String kyxmJson = request.getParameter("kyxmList_10704");
					List<KyxmModel> kyxmList = JsonUtil.jsonToList(kyxmJson, KyxmModel.class);
					result = service.updateKyxm(model.getXh(), kyxmList);
				}
				//�����ɹ�
				if (srcList.contains("qtcgList_10704")) {
					String qtcgJson = request.getParameter("qtcgList_10704");
					List<QtcgModel> qtcgList = JsonUtil.jsonToList(qtcgJson, QtcgModel.class);
					result = service.updateQtcg(model.getXh(), qtcgList);
				}
				//�����ɹ�
				if (srcList.contains("hjqkList_10704")) {
					String hjqkJson = request.getParameter("hjqkList_10704");
					List<HjqkModel> hjqkList = JsonUtil.jsonToList(hjqkJson, HjqkModel.class);
					result = service.updateHjqkXakj(model.getXh(), hjqkList);
				}
			}
			
			// ���ֹ�ҵְҵ����ѧԺ
			if(Base.xxdm.equalsIgnoreCase("12903")) {
				// ����ʵϰ��Ϣ
				if (srcList.contains("ggsxjlList")) {
					String ggsxjlJson = request.getParameter("ggsxjlList");
					List ggsxjlList = JsonUtil.jsonToList(ggsxjlJson, GgsxjlModel.class);
					result = service.updateGgsxjl(model.getXh(), ggsxjlList);
				}
				// ����ʵϰ��Ϣ
				if (srcList.contains("dgsxjlList")) {
					String dgsxjlJson = request.getParameter("dgsxjlList");
					List dgsxjlList = JsonUtil.jsonToList(dgsxjlJson, DgsxjlModel.class);
					result = service.updateDgsxjl(model.getXh(), dgsxjlList);
				}
			}
			
			//ɽ���ƾ�
			if("10125".equalsIgnoreCase(Base.xxdm)) {
				//��ѧ�о����
				if (srcList.contains("kxyjList")) {
					List kxyjList = JsonUtil.jsonToList(request.getParameter("kxyjList"), KxyjModel.class);
					result = service.updateKxyj(model.getXh(), kxyjList);
				}
				//�����о����
				if (srcList.contains("ktyjList")) {
					List ktyjList = JsonUtil.jsonToList(request.getParameter("ktyjList"), KtyjModel.class);
					result = service.updateKtyj(model.getXh(), ktyjList);
				}
				//���´�ҵ��Ŀ
				if (srcList.contains("cxcyxmList")) {
					List cxcyxmList = JsonUtil.jsonToList(request.getParameter("cxcyxmList"), CxcyxmModel.class);
					result = service.updateCxcyxm(model.getXh(), cxcyxmList);
				}
				//ѧ�ƾ���
				if (srcList.contains("xkjsList")) {
					List xkjsList = JsonUtil.jsonToList(request.getParameter("xkjsList"), XkjsModel.class);
					result = service.updateXkjs(model.getXh(), xkjsList);
				}
				//����֤��
				if (srcList.contains("jnzsList")) {
					List jnzsList = JsonUtil.jsonToList(request.getParameter("jnzsList"), JnzsModel.class);
					result = service.updateJnzs(model.getXh(), jnzsList);
				}
			}
			
			// ����ʦ����ѧ
			if(Base.xxdm.equalsIgnoreCase("10511")) {
				// ���˼���
				if (srcList.contains("grjlList")) {
					String grjlJson = request.getParameter("grjlList");
					List grjlList = JsonUtil.jsonToList(grjlJson, GrjlModel.class);
					result = service.updateXsGrjl(model.getXh(), grjlList);
				}
				// ��������
				if (srcList.contains("gzjlList")) {
					String gzjlJson = request.getParameter("gzjlList");
					List gzjlList = JsonUtil.jsonToList(gzjlJson, GzjlModel.class);
					result = service.updateXsGzjl(model.getXh(), gzjlList);
				}
			}
			
			// ����ѧԺ
			if("11354".equalsIgnoreCase(Base.xxdm)) {
				// ѧ��������Ϣ
				if(srcList.contains("xsyhxxList")) {
					String xsyhxxJson = request.getParameter("xsyhxxList");
					List xsyhxxList = JsonUtil.jsonToList(xsyhxxJson, XsyhxxModel.class);
					result = service.updateXsyhxx(model.getXh(), xsyhxxList);
				}
			}
			
			// ������ҽҩ��ѧ
			if("10026".equalsIgnoreCase(Base.xxdm)) {
				// ���˽�����Ϣ
				if(srcList.contains("grhjxxList")) {
					String grhjxxJson = request.getParameter("grhjxxList");
					String[] hjid = request.getParameterValues("hjid");
					List grhjxxList = JsonUtil.jsonToList(grhjxxJson, GrhjxxModel.class);
					result = service.updateGrhjxxByjg(model.getXh(), grhjxxList,hjid);
				}		
				// ���˽�����Ϣ
				if(srcList.contains("cyktxxList_10026")) {
					String cyktxxJson = request.getParameter("cyktxxList_10026");
					String[] id = request.getParameterValues("id");
					List<CyktxxModel> cyktxxList = JsonUtil.jsonToList(cyktxxJson, CyktxxModel.class);
					result = service.updateCyktxxByjg(model.getXh(), cyktxxList,id);
				}		
			}
			
			//�Ͼ��ߵ�ְҵ����ѧУ
			if("10874".equalsIgnoreCase(Base.xxdm)){
				//ʵϰ��Ϣ
				if (srcList.contains("sxxxList")) {
					String sxxxJson = request.getParameter("sxxxList");
					List<SxxxModel> sxxxList = JsonUtil.jsonToList(sxxxJson, SxxxModel.class);
					result = service.updateSxxx(model.getXh(), sxxxList);
				}
				//������Ϣ
				if (srcList.contains("fwxxList")) {
					String fwxxJson = request.getParameter("fwxxList");
					List<FwxxModel> fwxxList = JsonUtil.jsonToList(fwxxJson, FwxxModel.class);
					result = service.updateFwxx(model.getXh(), fwxxList);
				}
			}

			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		if("12303".equalsIgnoreCase(Base.xxdm)){
			String sfqnzyz = new XsxxglService().getSfqnzyz(model.getXh());
			request.setAttribute("sfqnzyz", sfqnzyz);
		}
		if("13943".equalsIgnoreCase(Base.xxdm)){
			String shgxgzdw1 = new XsxxtyglService().getYxyj(model.getXh());
			request.setAttribute("shgxgzdw1", shgxgzdw1);
		}
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xh", model.getXh());
		request.setAttribute("yyfj", (String) xsxxMap.get("zd6"));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xsxxglXg");
	}

	/**
	 * 
	 * @����:ɾ��ѧ����Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����11:10:41
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ-ɾ��VALUES:{values}")
	public ActionForward xsxxglSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = values.split(",").length;
			boolean result = service.delData(values);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @����:ѧ����ϸ��Ϣ�鿴
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-26 ����04:16:59
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
	
	@SuppressWarnings("unchecked")
	public ActionForward xsxxglCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		HashMap xsxxMap = service.getXsxxByXh(myForm.getXh());
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			//ͬ��ͬѧ
			if (srcList.contains("tbtxList")) {
				List<HashMap<String, String>> tbtxList = service
						.getTbtxList(myForm.getXh(),(String)xsxxMap.get("bjdm"));
				xsxxMap.put("tbtxList", tbtxList);
			}
			//ͬ����ͬѧ
			if (srcList.contains("tsstxList")) {
				List<HashMap<String, String>> tsstxList = service
						.getTsstxList(myForm.getXh());
				xsxxMap.put("tsstxList", tsstxList);
			}
			if (srcList.contains("bjgkmList")) {
				List<HashMap<String, String>> bjgkmList = service
						.getBjgkmList(myForm.getXh());
				xsxxMap.put("bjgkmList", bjgkmList);
			}
			//������Ŀ�ɼ�
			if (srcList.contains("qtkmList")) {
				List<HashMap<String, String>> qtkmList = service
						.getQtkmList(myForm.getXh());
				xsxxMap.put("qtkmList", qtkmList);
			}
			//֪��̸����Ϣ
			if (srcList.contains("zxthxxList")) {
				List<HashMap<String, String>> zxthxxList = service
						.getZxthxxList(myForm.getXh());
				xsxxMap.put("zxthxxList", zxthxxList);
			}
			//�ҷ���Ϣ
			if (srcList.contains("jfxxList")) {
				List<HashMap<String, String>> jfxxList = service
						.getJfxxList(myForm.getXh());
				xsxxMap.put("jfxxList", jfxxList);
			}
			//�ڶ�����-���Ϣ
			if (srcList.contains("hdxxList")) {
				List<HashMap<String, String>> hdxxList = service
						.getHdxxList(myForm.getXh());
				xsxxMap.put("hdxxList", hdxxList);
			}
			//�ĸ�һ����Ϣ
			if (srcList.contains("sgybxxList")) {
				List<HashMap<String, String>> sgybxxList = service
						.getSgybxxList(myForm.getXh());
				xsxxMap.put("sgybxxList", sgybxxList);
			}
			//�μ�������Ϣ
			if (srcList.contains("cjstxxList")) {
				List<HashMap<String, String>> cjstxxList = service
						.getCjstxxList(myForm.getXh());
				xsxxMap.put("cjstxxList", cjstxxList);
			}

			//��ҵ��Ϣ
			if (srcList.contains("byxxList")) {
				List<HashMap<String, String>> byxxList = service
						.getByxxList(myForm.getXh());
				xsxxMap.put("byxxList", byxxList);
			}
			//���ý���
			if (srcList.contains("bzjlList")) {
				List<HashMap<String, String>> bzjlList = service
						.getBzjlList(myForm.getXh());
				xsxxMap.put("bzjlList", bzjlList);
			}



			if (srcList.contains("stuCjList")) {
				List<HashMap<String, String>> stuCjList = service
						.getStuCjList(myForm.getXh());// �γ̿��Գɼ�
				xsxxMap.put("stuCjList", stuCjList);
			}
			if (srcList.contains("stuDjcjList")) {
				List<HashMap<String, String>> stuDjcjList = service
						.getStuDjcjList(myForm.getXh());// �ȼ����Գɼ�
				xsxxMap.put("stuDjcjList", stuDjcjList);
			}
			if (srcList.contains("zyfwJgList")) {
				List<HashMap<String, String>> zyfwJgList = new ZyfwJgService()
						.getZyfwJgListByXh(myForm.getXh());// ־Ը�����¼
				xsxxMap.put("zyfwJgList", zyfwJgList);
			}
			if (srcList.contains("stuQgzxXsgwxxList")) {
				List<HashMap<String, String>> stuQgzxXsgwxxList = service
						.getStuQgzxXsgwxxList(myForm.getXh());// ��λ¼�����
				xsxxMap.put("stuQgzxXsgwxxList", stuQgzxXsgwxxList);
			}
			if (srcList.contains("stuJxkhqkList")) {
				List<HashMap<String, String>> stuJxkhqkList = service
						.getStuJxkhqk(myForm.getXh());// ��ѵ�������
				xsxxMap.put("stuJxkhqkList", stuJxkhqkList);
			}
			if (srcList.contains("stuQgzxCjffList")) {
				List<HashMap<String, String>> stuQgzxCjffList = service
						.getStuQgzxCjffList(myForm.getXh());// ��𷢷����
				xsxxMap.put("stuQgzxCjffList", stuQgzxCjffList);
			}
			if (srcList.contains("stuGyxxList")) {
				List<HashMap<String, String>> stuGyxxList = service
						.getStuGyxxList(myForm.getXh());// ��Ԣ��Ϣ
				xsxxMap.put("stuGyxxList", stuGyxxList);
			}
			if (srcList.contains("qswpList")) {
				List<HashMap<String, String>> qswpList = service
						.getQswpList(myForm.getXh());// ��ȡ������Ʒ�б�
				xsxxMap.put("qswpList", qswpList);
			}

			// ���ҵ�ʦ��Ϣ�б�1036
			if (srcList.contains("qsdsList")) {
				QsdswhService qsdsService = new QsdswhService();
				xsxxMap.put("qsdsList", qsdsService.getQsdsxxListByXh(myForm
						.getXh()));
			}
			// ��Ԣ����Ա��Ϣ���㽭����ְҵ����ѧԺ��
			if (srcList.contains("stuGyglyxxList")) {
				xsxxMap.put("stuGyglyxxList", service.getGyglyxx(myForm.getXh()));
			}
			// ��Ԣ����Ա��Ϣ���㽭����ְҵ����ѧԺ��
			if (srcList.contains("stuGyfdyxxList")) {
				xsxxMap.put("stuGyfdyxxList", service.getGyfdyxx(myForm.getXh()));
			}

			if (srcList.contains("knsInfoList")) {
				List<HashMap<String, String>> knsInfoList = new KnsjgService()
						.getKnsInfoList(myForm.getXh());// ��ѧ�Ų�ѯȫ����������Ϣ
				xsxxMap.put("knsInfoList", knsInfoList);
			}
			if (srcList.contains("zzxmjgInfoList")) {
				List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService()
						.getZzxmjgInfoList(myForm.getXh());// ͨ��ѧ�Ų�ѯ������Ŀ���
				xsxxMap.put("zzxmjgInfoList", zzxmjgInfoList);
			}
			if (srcList.contains("hjqkList")) {
				List<HashMap<String, String>> hjqkList = new PjjgService()
						.getHjqkList(myForm.getXh());// ����ѧ�Ų�ѯ�����
				xsxxMap.put("hjqkList", hjqkList);
			}
			if (srcList.contains("zcfsList")) {
				List<HashMap<String, String>> zcfsList = new ZcfsService()
						.getZcfsList(myForm.getXh());// ͨ��ѧ�Ų�ѯ�۲����
				xsxxMap.put("zcfsList", zcfsList);
			}
			if("10698".equalsIgnoreCase(Base.xxdm)){
				//���⾭��
				if (srcList.contains("hwjlList_10698")) {
					List<HashMap<String, String>> hwjlList = service.getHwjlList(myForm.getXh());
					xsxxMap.put("hwjlList_10698", hwjlList);
				}
			}
			//�㽭�����۲����
			if("12867".equalsIgnoreCase(Base.xxdm)){
				// �ȼ����Գɼ�
				if (srcList.contains("zjlyzcfsList")) {
					List<HashMap<String, String>> zjlyzcfsList = service.getZcfsList(myForm.getXh());
					xsxxMap.put("zjlyzcfsList", zjlyzcfsList);
				}
			}
			//�½�ҽ�ƴ�ѧ��ѧԺ
			if("13560".equalsIgnoreCase(Base.xxdm)){
				// �ȼ����Գɼ�
				if (srcList.contains("xljkList")) {
					List<HashMap<String, String>> xljkList = service.getxljkList(myForm.getXh());
					xsxxMap.put("xljkList", xljkList);
				}
			}
			if (srcList.contains("zcfsListold")) {
				List<HashMap<String, String>> zcfsListold = new ZcfsService()
						.getZcfsListOld(myForm.getXh());// ͨ��ѧ�Ų�ѯ���ϰ汾�Ĳ����
				xsxxMap.put("zcfsListold", zcfsListold);
			}
			if (srcList.contains("cwsjList")) {
				List<HashMap<String, String>> cwsjList = new CwsjService()
						.getCwsjList(myForm.getXh());// ����ѧ�Ų�ѯѧ����������
				xsxxMap.put("cwsjList", cwsjList);
			}
			if (srcList.contains("wjcfList")) {
				List<HashMap<String, String>> wjcfList = service
						.getWjcfList(myForm.getXh());// ����ѧ�Ų�ѯΥ�ʹ����б�
				xsxxMap.put("wjcfList", wjcfList);
			}
			if (srcList.contains("xjydList")) {
				// List<HashMap<String, String>> xjydList = service
				// .getXjydList(myForm.getXh());// ����ѧ�Ų�ѯѧ���춯�б�
				List<HashMap<String, String>> xjydList = new XjydjgService()
						.getXsydList(myForm.getXh());// ����ѧ�Ų�ѯѧ���춯�б�
				xsxxMap.put("xjydList", xjydList);
			}
			if (srcList.contains("jtcyxxList")) {
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxXsList(myForm.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			// ѧ����ᾭ����Ϣ
			// 1036 �޸� 2014-01-23
			if (srcList.contains("xlshjlxxList")) {
				List<HashMap<String, String>> xlshjlxxList = service
						.getXlshjlList(myForm.getXh());
				xsxxMap.put("xlshjlxxList", xlshjlxxList);
			}

			// ��ѵ��Ϣ
			if (srcList.contains("pxxxList")) {
				List<HashMap<String, String>> pxxxList = service
						.getPxxxList(myForm.getXh());
				xsxxMap.put("pxxxList", pxxxList);
			}

			// �����
			if (srcList.contains("rxqhjqkList")) {
				List<HashMap<String, String>> rxqhjqkList = service
						.getHjqkList(myForm.getXh());
				xsxxMap.put("rxqhjqkList", rxqhjqkList);
			}
			
			// ���£������
			if (srcList.contains("hjqkxxNewList")) {
				List<HashMap<String, String>> hjqkxxNewList = service
				.getHjqkNewList(myForm.getXh());
				xsxxMap.put("hjqkxxNewList", hjqkxxNewList);
			}
			// У��������������·ְҵ����ѧԺ��
			if (srcList.contains("xsxwhjqkList")) {
				List<HashMap<String, String>> xsxwhjqkList = service.getXsxwhjqkList(myForm.getXh());
				xsxxMap.put("xsxwhjqkList", xsxwhjqkList);
			}

			// ˼����ʦ�б�
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService
					.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));

			// ����Ա��ʦ��Ϣ�б�
			if (srcList.contains("fdyList")) {
				xsxxMap.put("fdyList", xsxxtyglService.getSzxxList("fdy",
						szxxList));
			}
			// ��������ʦ��Ϣ�б�
			if (srcList.contains("bzrList")) {
				xsxxMap.put("bzrList", xsxxtyglService.getSzxxList("bzr",
						szxxList));
			}

			// ��Ԣ���ɴ�����Ϣ�б�
			if (srcList.contains("gyjlclxxList")) {
				xsxxMap.put("gyjlclxxList", service.getGyjlclxxAllList(myForm
						.getXh()));
			}
			// ��Ԣ�춯��Ϣ�б�
			if (srcList.contains("gyygxxList")) {
				xsxxMap
						.put("gyygxxList", service
								.getGyydxxList(myForm.getXh()));
			}

			// ��Ԣ������Ϣ�б�
			if (srcList.contains("gypyxxList")) {
				xsxxMap
						.put("gypyxxList", service
								.getGypyxxList(myForm.getXh()));
			}

			// ѧ���ɲ���Ϣ

			if (srcList.contains("xsgbxxList")) {
				xsxxMap
						.put("xsgbxxList", service
								.getXsgbxxList(myForm.getXh()));
			}

			// �����Ϣ

			if (srcList.contains("qjxxList")) {
				xsxxMap.put("qjxxList", service.getQjjgxxList(myForm.getXh()));
			}

			// ������У��Ϣ

			if (srcList.contains("jqlxxxList")) {
				xsxxMap
						.put("jqlxxxList", service
								.getJqlxxxList(myForm.getXh()));
			}

			// ֤��������Ϣ

			if (srcList.contains("zjbbxxList")) {
				xsxxMap
						.put("zjbbxxList", service
								.getZjbbxxList(myForm.getXh()));
			}
			

			// ��Ԣ������

			if (srcList.contains("gywsflist")) {
				xsxxMap.put("gywsflist", service.getGywsfList(myForm.getXh()));
			}
			
			// ��Ԣ�����֣����м�¼��
			if (srcList.contains("gywsfAllList")) {
				xsxxMap.put("gywsfAllList", service.getGywsfAllList(myForm.getXh()));
			}

			// ���Żݿ�

			if (srcList.contains("hcyhkxxList")) {
				xsxxMap.put("hcyhkxxList", service.getHcyhkxxList(myForm
						.getXh()));
			}

			// ����ע��

			if (srcList.contains("xqzcbdxxList")) {
				xsxxMap.put("xqzcbdxxList", service.getXqbdzcxxList(myForm
						.getXh()));
			}

			// ��ԢΥ��

			/*if (srcList.contains("gywjxxList")) {
				xsxxMap
						.put("gywjxxList", service
								.getGywjxxList(myForm.getXh()));
			}
*/
			// ���÷���

			if (srcList.contains("fyffxxList")) {
				xsxxMap
						.put("fyffxxList", service
								.getFyffxxList(myForm.getXh()));
			}
			
			//��Ԣ������ 
			
			if(srcList.contains("gywsflist")){
				xsxxMap.put("gywsflist", service.getGywsfList(myForm.getXh()));
			}
			

			// ��ȡ���һ�ܹ�Ԣ���Ų������ύ����ճ̵����һ�ܣ��㽭�����Ի���

			if (srcList.contains("lgGypyxxList")) {
				xsxxMap.put("lgGypyxxList", service.getLgGypyxxList(myForm.getXh()));
			}
			
			//������Ϣ
			if(srcList.contains("kqxxList")){
				xsxxMap.put("kqxxList", service.getKqxxList(myForm.getXh()));
			}
			
			//������ϰ������Ϣ
			if(srcList.contains("zwzxkqxxList")){
				xsxxMap.put("zwzxkqxxList", service.getZwzxKqxxList(myForm.getXh()));
			}
			
			//���ĳ�����Ʒ������Ϣ
			if(srcList.contains("wpsqjgList")){
				xsxxMap.put("wpsqjgList", service.getWpsqjgList(myForm.getXh()));
			}
			//��ɫͨ��������Ϣ
			if(srcList.contains("lstdList")){
				xsxxMap.put("lstdList", service.getLstdList(myForm.getXh()));
			}
			
			//������Ϣ
			DtxxjgService dtxxjgService = new DtxxjgService();
			if(srcList.contains("grjdxxList")){
				xsxxMap.put("grjdxxList", dtxxjgService.getGrJdxx(myForm.getXh()));
			}
			
			//��ѧ����(��Դ�ش���)
			if (srcList.contains("syddkList")){
				SyddkService sydService = new SyddkService();
				xsxxMap.put("syddkList", sydService.getSydkList(myForm.getXh()));
			}
			
			//��ѧ����(������ѧ����)
			if (srcList.contains("gjdkList")){
				DkjgService dkjgService = new DkjgService();
				xsxxMap.put("gjdkList", dkjgService.getGjdkList(myForm.getXh()));
			}
			
			//����ʦ�������ҵ�������
			if (srcList.contains("JcjyBcjglist")){
				BcjgService bcjg = new BcjgService();
				xsxxMap.put("JcjyBcjglist", bcjg.getJcjyBcjglist(myForm.getXh()));
			}
			
			//����ʦ��������۴������
			if (srcList.contains("Rwdcjglist")){
				RwfbydcjgService rwdc = new RwfbydcjgService();
				xsxxMap.put("Rwdcjglist", rwdc.getRwdcjglist(myForm.getXh()));
			}
			
			//����ʦ��־Ը�߽��
			if (srcList.contains("Sthdlist")){
				SthdjgService stjg = new SthdjgService();
				xsxxMap.put("Sthdlist", stjg.getSthdlist(myForm.getXh()));
			}
			//���д���
			if (srcList.contains("Kycxlist")){
				KyxmglService kycx = new KyxmglService();
				xsxxMap.put("Kycxlist", kycx.getKycxList(myForm.getXh()));
			}
			//�����OLD
			if (srcList.contains("hjqkListOld")) {
				List<HashMap<String, String>> hjqkListOld = new PjjgService()
						.getHjqkListOld(myForm.getXh());// ����ѧ�Ų�ѯ�����
				xsxxMap.put("hjqkListOld", hjqkListOld);
			}
			//�ۺϲ��������ϳ��У�
			if(srcList.contains("khfsList")){
				List<HashMap<String,String>> khfsList = new ArrayList<HashMap<String,String>>();
				HashMap<String,String> khfsMap = new KhpfService().getZpxxList(myForm.getXh());
				khfsList.add(khfsMap);
				xsxxMap.put("khfsList", khfsList);
				
			}
			
			//��ѧ��ƽ�����㣨���
			if("10335".equals(Base.xxdm)) {
				if(srcList.contains("xfjdList")) {		
					xsxxMap.put("xfjdList", service.getXfjdList(myForm.getXh(),service.getXsnj(myForm.getXh())));
				}
				
			}
			
			if(Base.xxdm.equalsIgnoreCase("12871")) {
				//�����ȵ�
				if(srcList.contains("dyddList")) {
					List<HashMap<String, String>> dyddList = new PjjgService()
					.getDyddList(myForm.getXh());// ����ѧ�Ų�ѯ�����
					xsxxMap.put("dyddList", dyddList);		
				}
			}
			//����ũҵ��ѧ
			if("10466".equalsIgnoreCase(Base.xxdm)){
				//���ʵ�����
				if (srcList.contains("shsjqkList_10466")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(myForm.getXh());
					xsxxMap.put("shsjqkList_10466", shsjqkList);
				}
			}
			//�����Ƽ���ѧ
			if("10704".equalsIgnoreCase(Base.xxdm)){
				// �ȼ����Գɼ�
				if (srcList.contains("djkscjList_10704")) {
					List<HashMap<String, String>> djkscjList = service.getDjkscjList(myForm.getXh());
					xsxxMap.put("djkscjList_10704", djkscjList);
				}
				//ѧ���ɲ�����
				if (srcList.contains("xsgbjlList_10704")) {
					List<HashMap<String, String>> xsgbjlList = service.getXsgbjlList(myForm.getXh());
					xsxxMap.put("xsgbjlList_10704", xsgbjlList);
				}
				//���ʵ�����
				if (srcList.contains("shsjqkList_10704")) {
					List<HashMap<String, String>> shsjqkList = service.getShsjqkList(myForm.getXh());
					xsxxMap.put("shsjqkList_10704", shsjqkList);
				}
				//��������������Ϣ
				if (srcList.contains("cgcjjlList_10704")) {
					List<HashMap<String, String>> cgcjjlList = service.getCgcjjlList(myForm.getXh());
					xsxxMap.put("cgcjjlList_10704", cgcjjlList);
				}
				
				//ѧ��������Ϣ���������� ������Ŀ �����ɹ�
				//��������
				if (srcList.contains("fblwList_10704")) {
					List<HashMap<String, String>> fblwList = service.getFblwList(myForm.getXh());
					xsxxMap.put("fblwList_10704", fblwList);
				}
				//������Ŀ
				if (srcList.contains("kyxmList_10704")) {
					List<HashMap<String, String>> kyxmList = service.getKyxmList(myForm.getXh());
					xsxxMap.put("kyxmList_10704", kyxmList);
				}
				//�����ɹ�
				if (srcList.contains("qtcgList_10704")) {
					List<HashMap<String, String>> qtcgList = service.getQtcgList(myForm.getXh());
					xsxxMap.put("qtcgList_10704", qtcgList);
				}
				//�����
				if (srcList.contains("hjqkList_10704")) {
					List<HashMap<String, String>> hjqkList = service.getHjqkListForXakj(myForm.getXh());
					xsxxMap.put("hjqkList_10704", hjqkList);
				}
			}
			
			// ���ֹ�ҵְҵ����ѧԺ
			if(Base.xxdm.equalsIgnoreCase("12903")) {
				// ����ʵϰ��Ϣ
				if (srcList.contains("ggsxjlList")) {
					List<HashMap<String, String>> ggsxjlList = service
					.getGgsxjlList(myForm.getXh());
					xsxxMap.put("ggsxjlList", ggsxjlList);
				}
				// ����ʵϰ��Ϣ
				if (srcList.contains("dgsxjlList")) {
					List<HashMap<String, String>> dgsxjlList = service
					.getDgsxjlList(myForm.getXh());
					xsxxMap.put("dgsxjlList", dgsxjlList);
				}
			}
			
			//ɽ���ƾ�
			if("10125".equalsIgnoreCase(Base.xxdm)) {
				//��ѧ�о����
				if(srcList.contains("kxyjList")) {
					xsxxMap.put("kxyjList", service.getKxyjList(myForm.getXh()));
				}
				//�����о�
				if(srcList.contains("ktyjList")) {
					xsxxMap.put("ktyjList", service.getKtyjList(myForm.getXh()));
				}
				//���´�ҵ��Ŀ
				if(srcList.contains("cxcyxmList")) {
					xsxxMap.put("cxcyxmList", service.getCxcyxmList(myForm.getXh()));
				}
				//ѧ�ƾ���
				if(srcList.contains("xkjsList")) {
					xsxxMap.put("xkjsList", service.getXkjsList(myForm.getXh()));
				}
				//����֤��
				if(srcList.contains("jnzsList")) {
					xsxxMap.put("jnzsList", service.getJnzsList(myForm.getXh()));
				}
			}
			
			if("13871".equalsIgnoreCase(Base.xxdm)) {
				//Ƿ�����
				if(srcList.contains("qfqkList")) {
					xsxxMap.put("qfqkList", service.getQfqk(myForm.getXh()));
				}
			}
			
			if("11998".equalsIgnoreCase(Base.xxdm) || "10346".equalsIgnoreCase(Base.xxdm)) {
				//һ��ͨ�������
				if(srcList.contains("yktxfqkList")) {
					xsxxMap.put("yktxfqkList", service.getYktxfls(myForm.getXh()));
				}
			}
			
			if("10346".equalsIgnoreCase(Base.xxdm)) {
				//ͼ��������
				if(srcList.contains("tsjyqkList")) {
					xsxxMap.put("tsjyqkList", service.getTsjyList(myForm.getXh()));
				} 
			}
			//����ҽҩ
			if("70002".equalsIgnoreCase(Base.xxdm)) {
				//һ��ͨ����У
				if(srcList.contains("yktcrjlList")) {
					xsxxMap.put("yktcrjlList", service.getXsyktcuList(myForm.getXh()));
				}
			}
			//����ְҵ
			if("13265".equalsIgnoreCase(Base.xxdm)) {
				if(srcList.contains("xsjfList")) {
					xsxxMap.put("xsjfList", service.getXsjfList(myForm.getXh()));
				}
			}
			
			// ����ʦ����ѧ
			if("10511".equalsIgnoreCase(Base.xxdm)) {
				// ���˼���
				if (srcList.contains("grjlList")) {
					List<HashMap<String, String>> grjlList = service
					.getXsGrjlList(myForm.getXh());
					xsxxMap.put("grjlList", grjlList);
				}
				// ��������
				if (srcList.contains("gzjlList")) {
					List<HashMap<String, String>> gzjlList = service
					.getXsGzjlList(myForm.getXh());
					xsxxMap.put("gzjlList", gzjlList);
				}
				
			}
			
			// ����ѧԺ
			if("11354".equalsIgnoreCase(Base.xxdm)) {
				// ѧ��������Ϣ
				if(srcList.contains("xsyhxxList")) {
					List<HashMap<String, String>> xsyhxxList = service
					.getXsyhxxList(myForm.getXh());
					xsxxMap.put("xsyhxxList", xsyhxxList);
				}
			}
			
			// ������ҽҩ��ѧ
			if("10026".equalsIgnoreCase(Base.xxdm)) {
				// ���˽�����Ϣ
				if(srcList.contains("grhjxxList")) {
					List<HashMap<String, String>> grhjxxList = service
					.getGrhjxxList(myForm.getXh());
					xsxxMap.put("grhjxxList", grhjxxList);
				}	
				// ���������Ϣ
				if(srcList.contains("cyktxxList_10026")) {
					List<HashMap<String, String>> cyktxxList = service
					.getCyktxxList(myForm.getXh());
					xsxxMap.put("cyktxxList_10026", cyktxxList);
				}				
			}
			
			if("10335".equalsIgnoreCase(Base.xxdm)) {
				// ���ѧ���춯
				if(srcList.contains("zdxjydList")) {
					List<HashMap<String, String>> zdxjydList = service
					.getZdxjydList(myForm.getXh());
					xsxxMap.put("zdxjydList", zdxjydList);
				}		
			}
			
			//�Ͼ��ߵ�ְҵ����ѧУ
			if("10874".equalsIgnoreCase(Base.xxdm)){
				if(srcList.contains("sxxxList")) {
					List<HashMap<String, String>> sxxxList = service
					.getSxxxList(myForm.getXh());
					xsxxMap.put("sxxxList", sxxxList);
				}
				
				if(srcList.contains("fwxxList")) {
					List<HashMap<String, String>> fwxxList = service
					.getFwxxList(myForm.getXh());
					xsxxMap.put("fwxxList", fwxxList);
				}
			}
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		/*����ʦ����ѧ||�㽭��ѧ  ��Ҫ����������Ŀ������*/
		if(Base.xxdm.equalsIgnoreCase("10718")||Base.xxdm.equalsIgnoreCase("10335")){
			HashMap<String, String> zzxmxx = new ZzxmjgDao().getZzxmNumTotalMoney(myForm.getXh());
			request.setAttribute("zzxmxx", zzxmxx);
		}
		
		//�����Ƽ���ѧ���Ի�����ƶ����Ϣ
		if("10704".equalsIgnoreCase(Base.xxdm)){
			HashMap<String, String> fpbxx = service.getFpbxx(myForm.getXh());
			xsxxMap.putAll(fpbxx);
		}
		
		//�ճ���Ϊ��������
		HashMap<String,String> cspzMap = new RcxwjgService().getCspz();
		request.setAttribute("zq",cspzMap.get("zq"));
		//1���ճ���ΪNEW 2���ճ���Ϊ
		request.setAttribute("mklb",cspzMap.get("mklb"));
		List<HashMap<String, String>> rcswList = new ArrayList<HashMap<String, String>>();
		if("1".equals(cspzMap.get("mklb"))){
			rcswList=new com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgService().getrcxwFzxxList(myForm.getXh());//����ѧ�Ų�ѯ�ճ�����
			
		}
		else{
			rcswList = new RcxwjgService().getRcswList(myForm.getXh());// ����ѧ�Ų�ѯ�ճ�����
		}
		request.setAttribute("rcswList",rcswList);
		request.setAttribute("rs", xsxxMap);

		// ˼����ʦ�б�
		/*
		 * XsxxtyglService xsxxtyglService = new XsxxtyglService();
		 * List<HashMap<String, String>> szxxList = xsxxtyglService
		 * .getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
		 * request.setAttribute("fdyList", xsxxtyglService.getSzxxList("fdy",
		 * szxxList));// ����Ա��ʦ��Ϣ�б� request.setAttribute("bzrList",
		 * xsxxtyglService.getSzxxList("bzr", szxxList));// ��������ʦ��Ϣ�б�
		 */
		XsxxKzxxglJgService kzxxgljgService = new XsxxKzxxglJgService();
		XsxxKzxxglJgForm kzxxModel = kzxxgljgService.getModelByXh(myForm.getXh());
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("kzxxModel", kzxxModel);
		HashMap<String, String> jxSum = new PjjgService().getJxSum(myForm.getXh());
		request.setAttribute("jxhj", jxSum);
		//�㽭��ѧ������ܽ����ʾ�����Ի���
		if("10335".equalsIgnoreCase(Base.xxdm)){
		HashMap<String, String> pjzje = new PjjgService().getPjzje(myForm.getXh());
		request.setAttribute("pjzje", pjzje);
		}
		return mapping.findForward("xsxxglCk");
	}

	@SystemAuth(url = "xsxx_xsxxgl_xxck.do")
	@SuppressWarnings("unchecked")
	public ActionForward xxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;

		User user = getUser(request);
		myForm.setXh(user.getUserName());

		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXh(myForm.getXh());

		request.setAttribute("rs", xsxxMap);

		// ˼����ʦ�б�
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		List<HashMap<String, String>> szxxList = xsxxtyglService
				.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
		request.setAttribute("fdyList", xsxxtyglService.getSzxxList("fdy",
				szxxList));// ����Ա��ʦ��Ϣ�б�
		request.setAttribute("bzrList", xsxxtyglService.getSzxxList("bzr",
				szxxList));// ��������ʦ��Ϣ�б�

		request.setAttribute("xh", myForm.getXh());
		
		List<HashMap<String, String>> rcswList = new RcxwjgService()
				.getRcswList(myForm.getXh());// ����ѧ�Ų�ѯ�ճ�����
		HashMap<String,String> cspzMap = new RcxwjgService().getCspz();
		request.setAttribute("zq",cspzMap.get("zq"));
		request.setAttribute("rcswList",rcswList);

		String path = "xsxx_xsxxgl_cxfzxs.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		/*����ʦ����Ҫ����������Ŀ������*/
		if(Base.xxdm.equalsIgnoreCase("10718")){
			HashMap<String, String> zzxmxx = new ZzxmjgDao().getZzxmNumTotalMoney(myForm.getXh());
			request.setAttribute("zzxmxx", zzxmxx);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxck");

	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ-�ϴ���ƬXH:{xh}")
	public ActionForward uploadStuPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxZxxsService service = new XsxxZxxsService();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		// ============= ִ�б������ ============
		String flag = service.saveStuPic(myForm, user);
		// request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(getJsonMessage(flag));

		return null;
	}

	/**
	 * ��У����Ϣ�Զ��嵼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward zxsxxExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		exportData(model, request, response);
		insertLog(mapping, form, request, response);//���뵼��������־
		return null;
	}
	
	

	/**
	 * ����У����Ϣ�Զ��嵼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward fzxsxxExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		model.setSfzx("0");// ����У
		exportData(model, request, response);
		insertLog(mapping, form, request, response);//���뵼��������־
		return null;
	}
	/*
	 * �Զ��嵼������ʵ��
	 */
	private void exportData(XsxxglModel model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		exportModel.setRowConut(model.getRowConut());
		model.getPages().setPageSize(RAM_MAX_SIZE);
		File file = exportService.getExportExcelFile(exportModel,new QueryDataService(model,user){
			@Override
			public List queryData(Object model, User user) throws Exception {
				XsxxglModel fmtModel = (XsxxglModel)model;
				fmtModel.getPages().setCurrentPage(OptionUtil.page);
				XsxxglService service = new XsxxglService();
				return service.getAllList(fmtModel, user);	
			}});
		FileUtil.outputExcel(response, file);
	}

	/**
	 * 
	 * @����:��У����Ϣ��ѯByֻ������ѯ�鿴���ܣ����ѧԺ���Ի���
	 * @���ߣ�HongLin
	 * @���ڣ�2014-01-02
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xsxxglCxZxsRead(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "xsxx_xsxxgl_cxzxs_read.do";
		request.setAttribute("path", path);
		request.setAttribute("method", request.getParameter("method"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxxglCxZxsRead");
	}

	/**
	 * 
	 * @����:ѧ����ϸ��Ϣ�鿴,���ѧԺ���Ի���ȥ�����֤��ʾ
	 * @���ߣ�HongLin
	 * @���ڣ�2014-01-02
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
	@SystemAuth(url = url)
	@SuppressWarnings("unchecked")
	public ActionForward xsxxglCkByCfxy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel myForm = (XsxxglModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXh(myForm.getXh());
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			if (srcList.contains("stuCjList")) {
				List<HashMap<String, String>> stuCjList = service
						.getStuCjList(myForm.getXh());// �γ̿��Գɼ�
				xsxxMap.put("stuCjList", stuCjList);
			}
			if (srcList.contains("stuDjcjList")) {
				List<HashMap<String, String>> stuDjcjList = service
						.getStuDjcjList(myForm.getXh());// �ȼ����Գɼ�
				xsxxMap.put("stuDjcjList", stuDjcjList);
			}
			if (srcList.contains("stuQgzxXsgwxxList")) {
				List<HashMap<String, String>> stuQgzxXsgwxxList = service
						.getStuQgzxXsgwxxList(myForm.getXh());// ��λ¼�����
				xsxxMap.put("stuQgzxXsgwxxList", stuQgzxXsgwxxList);
			}
			if (srcList.contains("stuQgzxCjffList")) {
				List<HashMap<String, String>> stuQgzxCjffList = service
						.getStuQgzxCjffList(myForm.getXh());// ��𷢷����
				xsxxMap.put("stuQgzxCjffList", stuQgzxCjffList);
			}
			if (srcList.contains("stuGyxxList")) {
				List<HashMap<String, String>> stuGyxxList = service
						.getStuGyxxList(myForm.getXh());// ��Ԣ��Ϣ
				xsxxMap.put("stuGyxxList", stuGyxxList);
			}
			if (srcList.contains("qswpList")) {
				List<HashMap<String, String>> qswpList = service
						.getQswpList(myForm.getXh());// ��ȡ������Ʒ�б�
				xsxxMap.put("qswpList", qswpList);
			}
			if (srcList.contains("knsInfoList")) {
				List<HashMap<String, String>> knsInfoList = new KnsjgService()
						.getKnsInfoList(myForm.getXh());// ��ѧ�Ų�ѯȫ����������Ϣ
				xsxxMap.put("knsInfoList", knsInfoList);
			}
			if (srcList.contains("zzxmjgInfoList")) {
				List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService()
						.getZzxmjgInfoList(myForm.getXh());// ͨ��ѧ�Ų�ѯ������Ŀ���
				xsxxMap.put("zzxmjgInfoList", zzxmjgInfoList);
			}
			if (srcList.contains("hjqkList")) {
				List<HashMap<String, String>> hjqkList = new PjjgService()
						.getHjqkList(myForm.getXh());// ����ѧ�Ų�ѯ�����
				xsxxMap.put("hjqkList", hjqkList);
			}
			if (srcList.contains("zcfsList")) {
				List<HashMap<String, String>> zcfsList = new ZcfsService()
						.getZcfsList(myForm.getXh());// ͨ��ѧ�Ų�ѯ�ղ����
				xsxxMap.put("zcfsList", zcfsList);
			}
			if (srcList.contains("rcswList")) {
				List<HashMap<String, String>> rcswList = new RcxwjgService()
						.getRcswList(myForm.getXh());// ����ѧ�Ų�ѯ�ճ�����
				xsxxMap.put("rcswList", rcswList);
				request.setAttribute("rcswList", rcswList);
			}
			if (srcList.contains("cwsjList")) {
				List<HashMap<String, String>> cwsjList = new CwsjService()
						.getCwsjList(myForm.getXh());// ����ѧ�Ų�ѯѧ����������
				xsxxMap.put("cwsjList", cwsjList);
			}
			if (srcList.contains("wjcfList")) {
				List<HashMap<String, String>> wjcfList = service
						.getWjcfList(myForm.getXh());// ����ѧ�Ų�ѯΥ�ʹ����б�
				xsxxMap.put("wjcfList", wjcfList);
			}
			if (srcList.contains("xjydList")) {
				// List<HashMap<String, String>> xjydList = service
				// .getXjydList(myForm.getXh());// ����ѧ�Ų�ѯѧ���춯�б�
				List<HashMap<String, String>> xjydList = new XjydjgService()
						.getXsydList(myForm.getXh());// ����ѧ�Ų�ѯѧ���춯�б�
				xsxxMap.put("xjydList", xjydList);
			}
			if (srcList.contains("jtcyxxList")) {
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxXsList(myForm.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}

		request.setAttribute("rs", xsxxMap);

		// ˼����ʦ�б�
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		List<HashMap<String, String>> szxxList = xsxxtyglService
				.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
		request.setAttribute("fdyList", xsxxtyglService.getSzxxList("fdy",
				szxxList));// ����Ա��ʦ��Ϣ�б�
		request.setAttribute("bzrList", xsxxtyglService.getSzxxList("bzr",
				szxxList));// ��������ʦ��Ϣ�б�

		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("xsxxglCkByCfxy");
	}
	
	/**
	 * 
	 * @����:��Ա������Ϣͳ�Ʊ�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-8 ����09:10:50
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
	public ActionForward tyqntjExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxglModel exporModel = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getTyqntjList(exporModel,user);//��ѯ�����м�¼������ҳ
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.tyqntjExport(resultList, response.getOutputStream(), user);
		return null;
	}
	
	
	/**
	 * �㽭��ѧѧ԰��ѯ�����Ը�
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SystemAuth(url = url)
	public ActionForward xycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getXycx(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xsxx_xycx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xycx");
	}
	
	/**
	 * 
	 * @����:�㽭��ѧ���Ի�����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-11 ����04:08:06
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// ��ѯ
		List<HashMap<String,String>> resultList = service.getXycx(model,user);
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
	 * 
	 * @����:�㽭����ѧԺ���Ի�����
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-08 ����04:08:06
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
	public ActionForward exportDataZjjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getZjjcXsbxExcel(model, user);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		file.delete();
		return null;
	}
	
	/**
	 * 
	 * @����:�㽭����ѧԺ���Ի�����
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-08 ����04:08:06
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
	public ActionForward exportZcfDataZjjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		String xhs = request.getParameter("xhs");
		if(StringUtil.isNull(xhs)){
			return mapping.findForward("error");
		}
		String[] xhAll = xhs.split(","); 
		if(xhAll.length==1){
			File file = service.getZjjcZcfExcel(xhAll[0]);// ���ɵ����ļ�
			FileUtil.outputExcel(response, file);
			file.delete();
		}else if(xhAll.length>1){
			List<File> files = new ArrayList<File>();
			for(int i=0;i<xhAll.length;i++){
				File file = service.getZjjcZcfExcel(xhAll[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			for(int i=0;i<files.size();i++){
				files.get(i).delete();
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:�㽭����ѧԺ���Ի�����
	 * @���ߣ�ChenQ [���ţ�856]
	 * @���ڣ�2015-6-10 ����04:08:06
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
	public ActionForward exportZhqkDataZjjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getZjjcZhqkExcel(model, user);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		file.delete();
		return null;
	}
	
	/**
	 * ��ѯѧ��֤��Ϣ
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cxXsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		User user = getUser(request);// �û�����
		XsxxglService service = new XsxxglService();
		Map<String,String> map = service.cxXsz(csdm, user);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * ����ѧ��֤��Ϣ
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="ѧ����Ϣ-ѧ����Ϣ-��У����Ϣ-����ѧ��֤ģ��-CSDM:{csdm}")
	public ActionForward bcXsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String csz = request.getParameter("csz");
		// ����ģ��
		csz = csz.replaceAll("\\#\\{", "\\\\#\\{");
		User user = getUser(request);// �û�����
		XsxxglService service = new XsxxglService();
		boolean result = service.bcXsz(csdm, csz, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��ӡѧ��֤��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-10-9 ����10:22:47
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
	public ActionForward dyXsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		XsxxglService service = new XsxxglService();
		XsxxService xsxxService = new XsxxService();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		// ��ȡԭʼģ��
		User user = getUser(request);// �û�����
		Map<String,String> map = service.cxXsz(csdm, user);
		String csz = map.get("csz");
		// ѭ�����ɴ�ӡ����
		StringBuilder cszBuilder = new StringBuilder();
		// ����������
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				XsxxglModel model = service.getModel(values[i]);
				
				// ѧ����Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				HashMap<String,String> xsssx = xsxxService.getXsjtSsx(model.getXh());
				HashMap<String,String> xshcxx = xsxxService.getXsHcxx(model.getXh());
				String xm = xsjbxx.get("xm");
				String xb = xsjbxx.get("xb");
				String mzmc = xsjbxx.get("mzmc");
				String pyccmc = xsjbxx.get("pyccmc");
				String xymc = xsjbxx.get("xymc");
				String zymc = xsjbxx.get("zymc");
				String bjmc = xsjbxx.get("bjmc");
				String sfzh = xsjbxx.get("sfzh");
				String jtdz = xsssx.get("hkszd");
				String cczdz = xshcxx.get("cczdz");		

				cszTemp = csz.replaceAll("\\\\#\\{xm\\}", xm)
					         .replaceAll("\\\\#\\{xh\\}", xsjbxx.get("xh"))
					         .replaceAll("\\\\#\\{xb\\}", xb)
					         .replaceAll("\\\\#\\{mzmc\\}", mzmc)
					         .replaceAll("\\\\#\\{pyccmc\\}", pyccmc)
					         .replaceAll("\\\\#\\{xymc\\}", xymc)
					         .replaceAll("\\\\#\\{zymc\\}", zymc)
					         .replaceAll("\\\\#\\{bjmc\\}", bjmc)
					         .replaceAll("\\\\#\\{sfzh\\}", sfzh)
							 .replaceAll("\\\\#\\{ry\\}", xsjbxx.get("ry"))
							 .replaceAll("\\\\#\\{rm\\}", xsjbxx.get("rm"))
							 .replaceAll("\\\\#\\{rd\\}", xsjbxx.get("rd"))
							 .replaceAll("\\\\#\\{yxnf\\}", xsjbxx.get("yxnf"))
							 .replaceAll("\\\\#\\{jtdz\\}", jtdz)
							 .replaceAll("\\\\#\\{cczdz\\}", cczdz);
							 		            	
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // ��һҳ
				}
			}
		}
		// �����ӡ����
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dyByzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		XsxxglService service = new XsxxglService();
		XsxxService xsxxService = new XsxxService();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		// ��ȡԭʼģ��
		User user = getUser(request);// �û�����
		Map<String,String> map = service.cxXsz(csdm, user);
		String csz = map.get("csz");
		// ѭ�����ɴ�ӡ����
		StringBuilder cszBuilder = new StringBuilder();
		// ����������
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				XsxxglModel model = service.getModel(values[i]);
				
				// ѧ����Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				HashMap<String,String> xsssx = xsxxService.getXsjtSsx(model.getXh());
				HashMap<String,String> xshcxx = xsxxService.getXsHcxx(model.getXh());
				String xm = xsjbxx.get("xm");
				String xb = xsjbxx.get("xb");
				String mzmc = xsjbxx.get("mzmc");
				String pyccmc = xsjbxx.get("pyccmc");
				String xymc = xsjbxx.get("xymc");
				String zymc = xsjbxx.get("zymc");
				String bjmc = xsjbxx.get("bjmc");
				String sfzh = xsjbxx.get("sfzh");
				String pyccmc1 = pyccmc.substring(0,1);
				String time=format.format(date);
				String csy = DateTranCnDate.dateToCnDateSubYear(xsjbxx.get("csrq")); // ������ݴ�д
				String csm = DateTranCnDate.dateToCnDateSubOnlyMonth(xsjbxx.get("csrq")); // �����·ݴ�д
				String csd = DateTranCnDate.dateToCnDateSubOnlyDay(xsjbxx.get("csrq")); // �������ڴ�д
				String rxy = DateTranCnDate.dateToCnDateSubYear(xsjbxx.get("rxrq")); // ��ѧ��ݴ�д
				String rxm = DateTranCnDate.dateToCnDateSubOnlyMonth(xsjbxx.get("rxrq")); // ��ѧ�·ݴ�д
				String byy = DateTranCnDate.dateToCnDateSubYear(xsjbxx.get("byny")); // ��ҵ��ݴ�д
				String bym = DateTranCnDate.dateToCnDateSubOnlyMonth(xsjbxx.get("byny")); // ��ҵ�·ݴ�д
				String dqy = DateTranCnDate.dateToCnDateSubYear(time); // ��ǰ��ݴ�д
				String dqm = DateTranCnDate.dateToCnDateSubOnlyMonth(time); // ��ǰ�·ݴ�д
				String dqd = DateTranCnDate.dateToCnDateSubOnlyDay(time); // ��ǰ���ڴ�д
				String xz = DateUtils.numToZh(xsjbxx.get("xz")); // ѧ�ƴ�д

				cszTemp = csz.replaceAll("\\\\#\\{xm\\}", xm)
					         .replaceAll("\\\\#\\{xh\\}", xsjbxx.get("xh"))
					         .replaceAll("\\\\#\\{xb\\}", xb)
					         .replaceAll("\\\\#\\{mzmc\\}", mzmc)
					         .replaceAll("\\\\#\\{pyccmc\\}", pyccmc)
					         .replaceAll("\\\\#\\{xymc\\}", xymc)
					         .replaceAll("\\\\#\\{zymc\\}", zymc)
					         .replaceAll("\\\\#\\{bjmc\\}", bjmc)
					         .replaceAll("\\\\#\\{sfzh\\}", sfzh)
							 .replaceAll("\\\\#\\{ry\\}", xsjbxx.get("ry"))
							 .replaceAll("\\\\#\\{rm\\}", xsjbxx.get("rm"))
							 .replaceAll("\\\\#\\{rd\\}", xsjbxx.get("rd"))
							 .replaceAll("\\\\#\\{yxnf\\}", xsjbxx.get("yxnf"))		 
							 .replaceAll("\\\\#\\{csy\\}", csy)
							 .replaceAll("\\\\#\\{csm\\}", csm)
							 .replaceAll("\\\\#\\{csd\\}", csd)
							 .replaceAll("\\\\#\\{rxy\\}", rxy)
							 .replaceAll("\\\\#\\{rxm\\}", rxm)
							 .replaceAll("\\\\#\\{byy\\}", byy)
							 .replaceAll("\\\\#\\{bym\\}", bym)
							 .replaceAll("\\\\#\\{dqy\\}", dqy)
							 .replaceAll("\\\\#\\{dqm\\}", dqm)
							 .replaceAll("\\\\#\\{dqd\\}", dqd)
							 .replaceAll("\\\\#\\{xz\\}", xz)
							 .replaceAll("\\\\#\\{pyccmc1\\}", pyccmc1);
							 		            	
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // ��һҳ
				}
			}
		}
		// �����ӡ����
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����: �����໪ѧԺ��ӡѧ��֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-24 ����05:34:23
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
	public ActionForward dyXszXaph(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String csdm = request.getParameter("csdm");
		String value = request.getParameter("value");
		XsxxglService service = new XsxxglService();
//		XsxxService xsxxService = new XsxxService();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String fzrq = format.format(new Date());
		// ��ȡԭʼģ��
		User user = getUser(request);// �û�����
		Map<String,String> map = service.cxXsz(csdm, user);
		String csz = map.get("csz");
		// ѭ�����ɴ�ӡ����
		StringBuilder cszBuilder = new StringBuilder();
		// ����������
		String cszTemp = "";
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				// ѧ����Ϣ
				HashMap<String,String> xsjbxx = service.getXszdyxxXaph(values[i]);
				String xymc = xsjbxx.get("xymc");
				String zymc = xsjbxx.get("zymc");
				String bjmc = xsjbxx.get("bjmc");
				String rxny = xsjbxx.get("rxny");
				String xm = xsjbxx.get("xm");
				String xb = xsjbxx.get("xb");
				String mzmc = xsjbxx.get("mzmc");
				String csrq = xsjbxx.get("csrq");
				String jgmc = xsjbxx.get("jgmc");
				String xh = xsjbxx.get("xh");
				cszTemp = csz.replaceAll("#xymc", xymc)
					         .replaceAll("#zymc", zymc)
					         .replaceAll("#bjmc", bjmc)
					         .replaceAll("#rxny", rxny)
					         .replaceAll("#xm", xm)
					         .replaceAll("#xb", xb)
					         .replaceAll("#mzmc", mzmc)
					         .replaceAll("#csrq", csrq)
					         .replaceAll("#jgmc", jgmc)
							 .replaceAll("#xh", xh).replace("#{xh}", xh)
				             .replaceAll("#fzrq", fzrq);		            	
				cszBuilder.append(cszTemp);
				if(i < (n - 1)){
					cszBuilder.append(" LODOP.NewPage(); "); // ��һҳ
				}
			}
		}
		// �����ӡ����
		map.put("csz", cszBuilder.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ������ҽҩ���Ի������鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-27 ����03:08:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getFileData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		XsxxglService service = new XsxxglService();
		List<HashMap<String, String>> filedataList = service.getFileData(xh);
		String size = "0";
		if(filedataList != null && filedataList.size()>0){
			size = filedataList.size()+"";
		}
		HashMap<String, Object> datamap = new HashMap<String, Object>();
		datamap.put("size", size);
		datamap.put("filedata", JSONArray.fromObject(filedataList));
		JSONObject json = JSONObject.fromObject(datamap);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����: ����ʡ�����е�ר��ѧУ����ʵϰ״̬(zd1)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-5 ����04:49:11
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
	public ActionForward updateSxzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxglModel model = (XsxxglModel) form;
		String[] xhs = model.getXhs();
		String zt = URLDecoder.decode(URLDecoder.decode(request.getParameter("zd1"),"UTF-8"),"UTF-8");
		boolean rs = new XsxxglService().updateSxzt(xhs, zt);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ʵϰ״̬�޸���תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-5 ����05:07:50
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
	public ActionForward sxztXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		XsxxglModel myForm = (XsxxglModel) form;
		if(cnt == 1){
			XsxxglModel model = new XsxxglService().getModel(myForm.getXh());
			myForm.setZd1(model.getZd1());
		}
		return mapping.findForward("sxzt");
	}

	/**
	 * @����:�ֶ���ʷ��¼��ʾ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 16:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward viewZdLsjl(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception{

		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getZdLsjList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewZdLsjl");
	}

	/**
	 * @����:��ͥ��Ա��ʷ��¼��ʾ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/2/25 16:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward viewJtcyLsjl(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws Exception{

		XsxxglService service = new XsxxglService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getJtcyLsjList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJtcyLsjl");
	}

}
