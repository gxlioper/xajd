/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DBEncrypt;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxxg.zjgydxjk.ZjgyDxjkService;
import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ����Ϣ�޸�
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XxxgAction extends SuperAction {
	private String messageKey;
	
	private static final String url = "xsxx_xsxxxg_xgsh.do";
	private static final String jgurl = "xsxx_xsxxxg_xgjg.do";

	/**
	 * 
	 * @����:ѧ����Ϣ�޸�����
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
	@SystemAuth(url = "xsxx_xsxxxg_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��Ϣ�޸����-����XGZDJSON:{xgzdJson}")
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService service = new XsxxglService();
		XxxgService xxxgService = new XxxgService();
		XsxxglModel model = (XsxxglModel) form;
		User user = getUser(request);
		String shjg = request.getParameter("shjg");
		String sqid = request.getParameter("dshSqid");
		if ("stu".equals(user.getUserType())){
			
			// ͬһ�������ͬ�û�ͬʱ��¼�ж�
			if(StringUtils.isNotNull(model.getXh()) 
					&& !model.getXh().equals(user.getUserName())){

				response.getWriter().print(getJsonMessage("��ǰѧ�����¼ѧ�Ų�һ�£���ˢ��ҳ������²�����"));
				return null;
			}
			
			model.setXh(user.getUserName());
		}
		String xh = model.getXh();
		HashMap xsxxMap = service.getXsxxByXhForUpdate(xh);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			
			String gndm = "xsxx_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			
			if (srcList.contains("jtcyxxList")) {
				// ��ͥ��Ա��Ϣ
				List<HashMap<String, String>> jtcyxxList = service
						.getJtcyxxList(model.getXh());
				xsxxMap.put("jtcyxxList", jtcyxxList);
			}
			// ѧ����ᾭ����Ϣ
			// 1036 �޸� 2014-01-23
			if (srcList.contains("xlshjlxxList")) {
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
			
			// У��������������·ְҵ����ѧԺ��
			if (srcList.contains("hjqkxxNewList")) {
				List<HashMap<String, String>> xsxwhjqkList = service.getXsxwhjqkList(model.getXh());
				xsxxMap.put("xsxwhjqkList", xsxwhjqkList);
			}
			
			//����ũҵ��ѧ
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
				//��ѧ�о�
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
				// ���������Ϣ
				if(srcList.contains("cyktxxList_10026")) {
					List<HashMap<String, String>> cyktxxList = service
					.getCyktxxList(model.getXh());
					xsxxMap.put("cyktxxList_10026", cyktxxList);
				}		
			}

			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		} else if (SAVE.equals(model.getType())) {
			model.setUser(user);
			String xgzdJson = request.getParameter("xgzdJson");
			XgsqModel xgsqModel = new XgsqModel();
			xgsqModel.setXh(model.getXh());
			xgsqModel.setXgzd(xgzdJson);
			xgsqModel.setShjg(shjg);
			xgsqModel.setSqid(sqid);
			boolean result = xxxgService.saveXgsqDemo(xgsqModel);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if (SUBMIT.equals(model.getType())) {
			model.setUser(user);
			String xgzdJson = request.getParameter("xgzdJson");
			XgsqModel xgsqModel = new XgsqModel();
			xgsqModel.setXh(model.getXh());
			xgsqModel.setXgzd(xgzdJson);
			xgsqModel.setShjg(shjg);
			xgsqModel.setSqid(sqid);
			boolean result = xxxgService.saveXgsq(xgsqModel);
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		String zpsfcz = xsxxtyglService.checkxszpSfcz(xh);
		request.setAttribute("zpsfcz", zpsfcz);
		HashMap<String, String> csszMap = xsxxtyglService.getCsszjg();
		String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";//�޸�״̬����
		if("y".equals(kfxg)){
			//�жϵ�ǰʱ���Ƿ��ڿ�ʼʱ�������ʱ��֮��
			if(!DateUtils.betweenTime(csszMap.get("sqkssj"), csszMap.get("sqjssj"))){
				kfxg="n";
			}
		}
		request.setAttribute("kfxg", kfxg);
		request.setAttribute("sqcs", csszMap.get("sqcs"));
		request.setAttribute("xsxgsplc", csszMap.get("shlid"));
		String dshSqid = xxxgService.getDshDataByXh(xh);
		String shzSqid = xxxgService.getShzDataByXh(xh);
		HashMap<String,String> shxxRs = xxxgService.getShxxByXh(xh);
		HashMap<String,String> shztRs= xxxgService.getShztByXh(xh);
		request.setAttribute("dshSqid", dshSqid);
		request.setAttribute("shzSqid", shzSqid);
		request.setAttribute("shxxRs", shxxRs);
		request.setAttribute("shztRs", shztRs);
		request.setAttribute("xxdm", Base.xxdm);
		
		String path = "xsxx_xsxxxg_xgsq.do";
		request.setAttribute("path", path);
		
		request.setAttribute("rs", xsxxMap);
//		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
//		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", xh);
		request.setAttribute("yyfj", (String) xsxxMap.get("zd6"));
		return mapping.findForward("xgsq");
	}

	/**
	 * 
	 * @����:ѧ����Ϣ�޸�����б�
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
	@SystemAuth(url = url)
	public ActionForward xgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getWclPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		XsxxtyglService xsxxtyglService = new XsxxtyglService();
		HashMap<String, String> csszMap = xsxxtyglService.getCsszjg();
		String shkg = "n".equalsIgnoreCase(csszMap.get("shkg")) ? "n" : "y";//�޸�״̬����
		if("y".equals(shkg)){
			//�жϵ�ǰʱ���Ƿ��ڿ�ʼʱ�������ʱ��֮��
			if(!DateUtils.betweenTime(csszMap.get("shkssj"), csszMap.get("shjssj"))){
				shkg="n";
			}
		}
		request.setAttribute("shkg", shkg);
		String path = "xsxx_xsxxxg_xgsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgsh");
	}

	/**
	 * 
	 * @����:�޸���˲���
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
	public ActionForward xgshZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXhForUpdate(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);

		request.setAttribute("rs", xsxxMap);
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("gwid", request.getParameter("gwid"));
		request.setAttribute("ywid", request.getParameter("ywid"));
		request.setAttribute("lcid", request.getParameter("lcid"));
		request.setAttribute("shid", request.getParameter("shid"));
		this.saveToken(request);
		return mapping.findForward("xgshZj");
	}

	/**
	 * 
	 * @����:�������
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-17 ����09:26:57
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgshPlzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dataJson = request.getParameter("params");
    	DBEncrypt p = new DBEncrypt();
    	String afterE = p.eCode(dataJson);
		request.setAttribute("dataJson",afterE );
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("xgshPlzj");
	}
	
	/**
	 * 
	 * @����:�޸Ľ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-11 ����01:33:11
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
	@SystemAuth(url = jgurl)
	public ActionForward xgjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		XsxxglModel model = (XsxxglModel) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXgjgPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xsxxxg_xgjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgjg");

	}

	/**
	 * 
	 * @����:�õ��޸��ֶμ�ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����02:06:13
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
	public ActionForward getXgzdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		String sqid = request.getParameter("sqid");
		List<HashMap<String, String>> xgzdList = service.getXgzdList(sqid);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(xgzdList));
		return null;
	}

	/**
	 * 
	 * @����:�޸Ľ���鿴
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-11 ����01:33:53
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
	public ActionForward xgjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XsxxglService service = new XsxxglService();
		HashMap xsxxMap = service.getXsxxByXhForUpdate(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("rs", xsxxMap);
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("sqid", request.getParameter("sqid"));
		return mapping.findForward("xgjgCk");
	}

	/**
	 * 
	 * @����:ѧ����Ϣ�޸ģ����ͨ���˻�
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-11 ����03:32:57
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
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��Ϣ�޸����-����SQID:{sqid}")
	public ActionForward thRecordForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		String sqid = request.getParameter("sqid");
		boolean result = service.thXgsqZt(sqid);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ����Ϣ-��Ϣ�޸����-����SQID:{sqid}")
	public ActionForward updateSqzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxxgService service = new XxxgService();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		String sqid = request.getParameter("sqid");
		User user = getUser(request);
		
		String cancelFlg = service.updateSqzt(sqid,shid,splc,user);
		// ��˳����ɹ�
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * 
	 * @����:�����ֻ���֤��
	 * @���ߣ�ChenQ-856
	 * @���ڣ�2016-1-20 ����02:20:53
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
	@SystemAuth(url = "xsxx_xsxxxg_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward sendCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		User user = getUser(request);
		String xh = user.getUserName();
		boolean result = ZjgyDxjkService.sendCord(request, model.getSjhm(), xh);
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 
	 * @����:��֤�ֻ���֤��
	 * @���ߣ�ChenQ-856
	 * @���ڣ�2016-1-20 ����02:22:15
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
	@SystemAuth(url = "xsxx_xsxxxg_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward checkCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String xh = user.getUserName();
		String code = request.getParameter("code");
		boolean result = ZjgyDxjkService.checkCord(request, code, xh);
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 
	 * @����: ѧ����Ϣ�޸���˵���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-6 ����05:15:51
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
	public ActionForward exportDataSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		model.setShzt(shlx);
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListSh(model,user);//��ѯ�����м�¼������ҳ
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
	
	/**
	 * 
	 * @����:���˻񽱸����ϴ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-26 ����02:52:09
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
	public ActionForward grfjsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getGrfjscCx(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xsxx_xsxxgl_grfjsc.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("grhjfjsc");
	}
	
	public ActionForward uploadfjsc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
		String hjid = request.getParameter("hjid");
		String type = model.getType();
		if("update".equals(type)){
			String gid = request.getParameter("gid");
			boolean flag = service.updateGrhjxxGid(hjid, gid);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("hjfjsc");
		request.setAttribute("jbxxList", jbxxList);
		String path = "xsxx_xsxxxg.do?method=grhjfjsc";
		request.setAttribute("path", path);
		HashMap<String, String> hjxx = service.getXshjXx(hjid);
		request.setAttribute("hjxx", hjxx);
		request.setAttribute("gid", hjxx.get("gid"));
		//������Ϣ����
		if("view".equals(type)){
			return mapping.findForward("view");
		}else{
			return mapping.findForward("upload");
		}
	}
	
	/**
	 * @��������Ϣ�޸Ĳ�ѯ ����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��19�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = jgurl)
	public ActionForward exportDataJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglModel model = (XsxxglModel) form;
		XxxgService service = new XxxgService();
	
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListJg(model, user);//��ѯ�����м�¼������ҳ
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
