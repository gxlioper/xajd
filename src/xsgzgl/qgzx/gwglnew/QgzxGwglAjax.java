package xsgzgl.qgzx.gwglnew;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * �ڹ���ѧ-�ڹ���λ����-��λ��Ϣ����
 */
public class QgzxGwglAjax extends SuperAction {

	
	/**
	 * ��λ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwsq.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwsq");
		// �����
		ArrayList<String[]> rsArrList = service.getGwsqList(myForm);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���˵�λ��λ���� �Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward gwsqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getGwsqAllList(model,user);
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
	 * ��λ��˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwsq.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwsh");
		// �����
		ArrayList<String[]> rsArrList = service.getGwshList(myForm);
		// ���������
		String spHtml = service.createSearchHTMLByGwsh(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ��λ��Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_gwgl_gwxxgl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwxx");
		// �����
		ArrayList<String[]> rsArrList = service.getGwxxList(myForm);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ���ѧ���б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_gwgl_getStu.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		String pkValue= request.getParameter("pkValue");
		model.setPkValue(pkValue);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("stu");
		// �����
		ArrayList<String[]> rsArrList = service.getStuList(model,request);
		// ���������
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ��λ��Ϣ�������ӱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-��λ��Աά��-����XN:{xn},YRBM:{yrbm},GWMC:{gwmc},GWXZDM:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String[] sqxy = request.getParameterValues("sqxy");
		myForm.setSqxy(sqxy);
		myForm.setYrdwdm(myForm.getYrbm());
		myForm.setSqr(getUser(request).getUserName());
		myForm.setSjly("0");
		//TODO �㽭����ְҵѧԺ���Ի�
		if("12867".equals(Base.xxdm)){
			//��ȡ����ʱ�䣬�޸ĳ�ǰ̨��ʾ��ͬ��ʽ��ʱ���ʽ
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			myForm.setGwkssj(df.format(df1.parse(myForm.getSqsj())));
			if(myForm.getGwlx().equals("�̶���")){
				myForm.setGwxzdm("001");
			} else if(myForm.getGwlx().equals("ʵϰ��")){
				myForm.setGwxzdm("002");
			}
		}
		//������������
		//myForm =(QgzxGwglForm) StringUtils.formatGbkToUtf(myForm);
		boolean rs = service.gwxxBc(myForm);
	//	response.setContentType("text/html;charset=gbk");
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
		return null;
	}
	
	
	/**
	 * ��λ��Ϣ�����޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-��λ��Աά��-�޸�XN:{xn},GWMC:{gwmc},gwxzdm:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwdm(myForm.getPkValue());
		String[] sqxy = request.getParameterValues("sqxy");
		myForm.setSqxy(sqxy);
		//������������
	//	myForm =(QgzxGwglForm) StringUtils.formatGbkToUtf(myForm);
		boolean rs = service.update(myForm);
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
	//	response.setContentType("text/html;charset=gbk");
		return null;
	}
	
	
	/**
	 * ��λ��Ϣ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxFz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String type = request.getParameter("doType");
		  if ("fz".equalsIgnoreCase(type)){
			  	String pkValue = request.getParameter("pkValue");
				String xn = request.getParameter("xn");
				String xq = request.getParameter("xq");
				myForm.setPkValue(pkValue);
				myForm.setXq(xq);
				String message = service.gwxxFz(xn,myForm);
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print(message);
				return null;
			  
		  }else{
			  String num = request.getParameter("num");
			  String len = request.getParameter("len");
			  String str = request.getParameter("str");
			  String idList = request.getParameter("idList");
			  request.setAttribute("num", num);
			  request.setAttribute("len", len);
			  request.setAttribute("str", str);
			  request.setAttribute("idList", idList);
			  List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//��Чʱ����list
			  request.setAttribute("yxsszList", yxsszList);
		  }
		  this.saveToken(request);
		  QgCommUtilf.setCssz(request);
		  request.setAttribute("xqList", Base.getXqList());
		  request.setAttribute("xnList", Base.getXnndList());
		  return mapping.findForward("gyxxFzgw");
		
	}
	
	
	/**
	 * ��λ��Ϣ����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-��λ��Աά��-ɾ��VALUES:{pkValue}")
	public ActionForward gwxxSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.gwxxSc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * ��֤��λ��Ϣ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwmc(service.unicode2Gbk(myForm.getGwmc()));
		String message = service.checkBcInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * ��֤��λ��Ϣ����ɾ����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.checkScInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * ��֤��λ��Ϣ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkFzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setPkValue(pkValue);
		String message = "";
		if(StringUtils.isNotNull(myForm.getType()) && "sq".equals(myForm.getType())){
			message = service.checkFzInfoSq(myForm);
		}else{
			message = service.checkFzInfo(myForm);
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * ���ѧ�꣨���ƣ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> list = Base.getXnndList();
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	
	/**
	 * ���ѧ����Ϣ�б�����ѧ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		//�����������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		List<HashMap<String, String>> list = service.getXsxxList(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	
	/**
	 * ����������Ա��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcZjryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//������������
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setXn(service.unicode2Gbk(model.getXn()));
		String message = service.bcZjryxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	public ActionForward saveRyzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		String xsGwxxStr = request.getParameter("xsGwxxStr");
		List<QgzxGwglForm> gwryList=JsonUtil.jsonArrToList(xsGwxxStr,QgzxGwglForm.class);
		boolean result = service.saveRyzj(model,gwryList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * ɾ����Ա��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcScryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//������������
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		String message = service.scRyxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * �����˸���Ա��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcTgryxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//������������
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setSgsj(service.unicode2Gbk(model.getSgsj()));
		model.setTgyy(service.unicode2Gbk(model.getTgyy()));
		model.setXh(service.unicode2Gbk(model.getXh()));
		model.setSqbhs(service.unicode2Gbk(model.getSqbhs()));
		String message = service.bcTgryxx(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * �����λ�����˸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcTggwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		//������������
		model.setGwdm(service.unicode2Gbk(model.getGwdm()));
		model.setTgyy(service.unicode2Gbk(model.getTgyy()));
		User user = getUser(request);
		String[] gwdms = null;
		if(StringUtil.isNull(model.getGwdm())){
			// ���ݲ�ѯ������������˸�
			//���ɸ߼���ѯ����		
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("qgzx_gwglnew_gwxxgl.do");
			model.setSearchModel(searchModel);
			List<HashMap<String,String>> resultList = service.getGwxxAllList(model, user);//��ѯ�����м�¼������ҳ
			gwdms = new String[resultList.size()];
			for (int i = 0; i < resultList.size(); i++) {
				gwdms[i] = resultList.get(i).get("gwdm");
			}
		}else{
			// ���ݹ�ѡ��¼���������˸�
			gwdms = model.getGwdm().split(",");
		}
		String message = service.bcTggwxx(gwdms, model.getTgyy());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * ��Ա��Ϣ�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryxxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		HashMap<String,String> rs = service.ryxxCk(model,request);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * ��֤��λ��Ϣ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScRyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String message = service.checkScRyxx(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * ��֤���Ӹ�λ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkZjGwsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwmc(service.unicode2Gbk(myForm.getGwmc()));
		String message = service.checkZjGwsqInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * �������Ӹ�λ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-��λ��Աά��(���˵�λ��λ����new)-����XN:{xn},GWMC:{gwmc},gwxzdm:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward bcZjGwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm) form;
		model.setYrdwdm(model.getYrbm());
		String[] sqxy = request.getParameterValues("sqxy");
		model.setSqxy(sqxy);
		model.setSqr(getUser(request).getUserName());
		//������������
		//model =(QgzxGwglForm) StringUtils.formatGbkToUtf(model);
		boolean rs = service.bcZjGwsq(model);
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
		return null;
	}
	/**
	 * 
	 * @����:TODO  �������Ӹ�λ����,�㽭����ѧԺ���Ի�
	 * @���ߣ��´���[���ţ�1620]
	 * @���ڣ�2018-7-20 ����03:26:30
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
	public ActionForward bcZjGwsqForZjlyzy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglForm model = (QgzxGwglForm) form;
		QgzxGwglService service = new QgzxGwglService();
		String[] zjs = model.getZjs();//��ȡ��������
		User user = getUser(request);
		boolean result = service.insertGwxxForZjlyzy(zjs,model.getType(),user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * ��λ�������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-��λ��Աά��(���˵�λ��λ����new)-ɾ��VALUES:{pkValue}")
	public ActionForward gwsqSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setPkValue(pkValue);
		String message = service.gwsqSc(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * �������Ӹ�λ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-��λ��Աά��(���˵�λ��λ����new)-���ӻ��޸�XN:{xn},GWMC:{gwmc},gwxzdm:{gwxzdm},XQRS:{xqrs},KNSRS:{knsrs},YXSSZ:{yxssz},SFSGWSQSXZ:{sfsgwsqsxz},GWKSSJ:{gwkssj},GWCJSX:{gwcjsx},GWMS:{gwms},GWRYYQ:{gwryyq}")
	public ActionForward bcXgGwsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		myForm.setGwdm(myForm.getPkValue());
		String[] sqxy = request.getParameterValues("sqxy");
		myForm.setSqxy(sqxy);
		myForm.setSqr(getUser(request).getUserName());
		//TODO �㽭����ְҵѧԺ���Ի�
		if("12867".equals(Base.xxdm)){
			if(myForm.getGwlx().equals("�̶���")) {
				myForm.setGwxzdm("001");
			} else {
				myForm.setGwxzdm("002");
			}
		}
		//������������
		//myForm =(QgzxGwglForm) StringUtils.formatGbkToUtf(myForm);
		boolean rs = service.bcXgGwsq(myForm);
		
		//response.setContentType("text/html;charset=gbk");
		String messagKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messagKey));
		return null;
	}
	
	/**
	 * @���������ͨ��ʱ��λ�޸� ���ݴ�ѧ���Ի�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��12�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description = "�����ڹ���ѧ-��λ��Ա����-���˸�λ���(���˵�λ��λ����new) GWDM:{pkValue}")
	public ActionForward bcXgGwshInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		User user = getUser(request);
		myForm.setUser(user);
		String message = service.bcXgGwsh(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ��λ��Ϣ��˱���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxshBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglForm model = (QgzxGwglForm) form;
		if(model.getSqsj() != null){
			model.setSqsj(model.getSqsj().replaceAll("&nbsp;", " "));
		}
		User user = getUser(request);
		// ���浥�����
		QgzxGwglService Transervice = TransactionControlProxy.newProxy(new QgzxGwglService());
		//TODO �㽭����ѧԺ���Ի�
		if(model.getGwlx() != null && model.getGwlx().equals("�̶���")){
			model.setGwxzdm("001");
		} else if(model.getGwlx() != null && model.getGwlx().equals("ʵϰ��")){
			model.setGwxzdm("002");
		}
		if(model.getNd() != null){
			model.setXn(model.getNd());
			//��ȡ����ʱ�䣬�޸ĳ�ǰ̨��ʾ��ͬ��ʽ��ʱ���ʽ
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			model.setGwkssj(df.format(df1.parse(model.getSqsj())));
		}
		boolean result = Transervice.gwxxshBcNew(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * ��λ��Ϣ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqFz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		User user = getUser(request);
		String type = request.getParameter("doType");
		  if ("fz".equalsIgnoreCase(type)){
			  	String pkValue = request.getParameter("pkValue");
				String xn = request.getParameter("xn");
				String xq = request.getParameter("xq");
				myForm.setPkValue(pkValue);
				String message = service.gwsqFz(xn,myForm,user.getUserName(),xq);
				response.setContentType("text/html;charset=gbk");
				response.getWriter().print(message);
				return null;
			  
		  }else{
			  String num = request.getParameter("num");
			  String len = request.getParameter("len");
			  String str = request.getParameter("str");
			  String idList = request.getParameter("idList");
			  request.setAttribute("num", num);
			  request.setAttribute("len", len);
			  request.setAttribute("str", str);
			  request.setAttribute("idList", idList);
			  List<HashMap<String,String>> yxsszList = new OptionUtil().getOptions(OptionUtil.YXSSZ);//��Чʱ����list
			  request.setAttribute("yxsszList", yxsszList);
			  request.setAttribute("xnList", Base.getXnndList());
		  }
		  this.saveToken(request);
		  QgCommUtilf.setCssz(request);
		  request.setAttribute("xqList", Base.getXqList());
		  return mapping.findForward("gysqFzgw");
		
	}
	
	/**
	 * 
	 * @����: ������˱���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-8 ����05:37:59
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QgzxGwglForm model = (QgzxGwglForm)form;
		User user = getUser(request);
		QgzxGwglService tranService = TransactionControlProxy.newProxy(new QgzxGwglService());
		String message = tranService.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
}
