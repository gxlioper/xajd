/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����02:48:29 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.beans.BeanUtils;

import xgxt.DAO.PicDAO;
import xgxt.action.Base;
import xgxt.base.DBEncrypt;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.mchange.v2.beans.BeansUtils;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import com.zfsoft.xgxt.szdw.xgsz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgAction;
import common.Globals;

/** 
 * @�๦������: ����Ա��Ϣ
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-7-9 ����02:48:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyxxAction extends SuperAuditAction<FdyxxModel, FdyxxService> {

	protected FdyxxAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}
	
	public FdyxxAction (){
		this("szdw_fdyxx", "szdw_fdyxx_xgsq.do", "szdw_fdyxx_xgsh.do");
	}

	
	/***����Ա��Ϣ�޸�***/
	public ActionForward fdyxxEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyxxModel model = (FdyxxModel)form;
		FdyxxService service = new FdyxxService();
		FdyxxModel fdyxxModel = service.getModel(model);
		if("10026".equals(Base.xxdm)){
			//������ҽҩ��ѧ  ��ʦ���
			request.setAttribute("jssf",fdyxxModel.getJssf());
		}
		BeanUtils.copyProperties(fdyxxModel, model);
		return mapping.findForward("fdyxxEdit");
	}
	
	
	/***����Ա��Ϣ�޸�***/
	public ActionForward fdyxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("fdyxxView");
	}
	
	/**��ѯ����Ա��Ϣ***/
	public ActionForward getFdyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyxxModel fdyxxForm = (FdyxxModel) form;
		FdyxxService service = getService();
		String zgh = fdyxxForm.getZgh();
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.putAll(service.getFdyInfo(zgh));

		map.put("jtcyList",service.getJtcyxx(zgh));
		map.put("gwydList",service.getGwydxx(zgh));
		map.put("jtjlList",service.getJtjlxx(zgh));
		map.put("zyjszwList",service.getZyjsgwxx(zgh));
		map.put("xxjlList",service.getXxjlxx(zgh));
		map.put("gzjlList",service.getGzjlxx(zgh));

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(map));
		return null;
	}

	public ActionForward getGwxx(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gwdjdm = request.getParameter("gwdjdm");
		FdyxxService service = getService();
		List<HashMap<String,String>> gwList = service.getGwxx(gwdjdm);
		response.getWriter().print(JSONArray.fromObject(gwList));
		return null;
	}

	/***�ֶ�����***/
	public ActionForward zdpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "szdw_fdyxx_zdpz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zdpz");
	}
	
	
	/***�޸�����***/
	@SystemAuth(url = "szdw_fdyxx_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		FdyxxService service = getService();
		User user = getUser(request);
		
		String dshSqid = service.getDshDataByZgh(user.getUserName());
		String shzSqid = service.getShzDataByZgh(user.getUserName());
		HashMap<String,String> shxxRs = service.getShxxByZgh(user.getUserName());
		request.setAttribute("dshSqid", dshSqid);
		request.setAttribute("shzSqid", shzSqid);
		request.setAttribute("shxxRs", shxxRs);
		if(Base.xxdm.equals("13431")){
			String zpsfcz = service.checkxszpSfcz(user.getUserName());
			request.setAttribute("zpsfcz", zpsfcz);
		}
		
		CsszService csszService = new CsszService();
		CsszModel cssz = csszService.getModel();
		
		request.setAttribute("cssz", cssz);
		request.setAttribute("path", "szdw_fdyxx_xgsq.do");
		FormModleCommon.commonRequestSet(request);
		if(Base.xxdm.equals("10704")){//�����Ƽ���ѧ���Ի�
			FdyxxService fdyService = new FdyxxService();
			FdyxxModel fdyxxModel = fdyService.getModel(user.getUserName());
			FdyxxModel fdyxxForm = (FdyxxModel) form;
			BeanUtils.copyProperties(fdyxxModel, fdyxxForm);			
		}
		return mapping.findForward("xgsq");
	}
	
	
	/***��������****/
	@SystemAuth(url = "szdw_fdyxx_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyxxModel fdyxxForm = (FdyxxModel) form;
		FdyxxService service = getService();
		String sqid = request.getParameter("dshSqid");
		fdyxxForm.setSqid(sqid);
		
		boolean result = service.saveXgsq(fdyxxForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/***�ύ����***/
	@SystemAuth(url = "szdw_fdyxx_xgsq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward tjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		FdyxxModel fdyxxForm = (FdyxxModel) form;
		FdyxxService service = getService();
		String sqid = request.getParameter("dshSqid");
		fdyxxForm.setSqid(sqid);
		
		boolean result = service.saveTjsq(fdyxxForm);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**��ѯ�޸��ֶ���Ϣ***/
	public ActionForward getXgzdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyxxService service = getService();
		String sqid = request.getParameter("sqid");
		List<HashMap<String, String>> xgzdList = service.getXgzdList(sqid);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(xgzdList));
		return null;
	}

	public ActionForward getBmmc(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String bmdm = request.getParameter("bmdm");
		FdyxxService service = getService();
		HashMap<String,String> data = service.getBmdm(bmdm);
		response.getWriter().print(JSONObject.fromObject(data));
		return null;
	}
	/***�޸����***/
	@SystemAuth(url = "szdw_fdyxx_xgsh.do")
	public ActionForward xgshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "szdw_fdyxx_xgsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgshList");
	}
	
	
	/***�޸���˲�ѯ�б�**/
	@SystemAuth(url = "szdw_fdyxx_xgsh.do")
	public ActionForward getXgshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		FdyxxModel model = (FdyxxModel) form;
		FdyxxService service = getService();
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getWclPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/***�޸����***/
	@SystemAuth(url = "szdw_fdyxx_xgsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyxxModel model = (FdyxxModel) form;
		FdyxxService service = getService();
		
		String dshSqid = service.getDshDataByZgh(model.getZgh());
		String shzSqid = service.getShzDataByZgh(model.getZgh());
		HashMap<String,String> shxxRs = service.getShxxByZgh(model.getZgh());
		request.setAttribute("dshSqid", dshSqid);
		request.setAttribute("shzSqid", shzSqid);
		request.setAttribute("shxxRs", shxxRs);
		request.setAttribute("gwid", request.getParameter("gwid"));
		request.setAttribute("ywid", request.getParameter("ywid"));
		request.setAttribute("lcid", request.getParameter("lcid"));
		request.setAttribute("shid", request.getParameter("shid"));
		return mapping.findForward("xgsh");
	}
	
	
	/****�������****/
	@SystemAuth(url = "szdw_fdyxx_xgsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String dataJson = request.getParameter("params");
    	DBEncrypt p = new DBEncrypt();
    	String afterE = p.eCode(dataJson);
		request.setAttribute("dataJson",afterE );
		return mapping.findForward("plsh");
	}
	
	/***�����������**/
	@SystemAuth(url = "szdw_fdyxx_xgsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyxxService service = TransactionControlProxy.newProxy(new FdyxxService());
		String params = request.getParameter("dataJson");
		DBEncrypt dbEncrypt = new DBEncrypt();
		String dataJson = dbEncrypt.dCode(params.getBytes());
		User user = getUser(request);
		ShxxModel model = new ShxxModel();
		model.setShr(user.getUserName());
		model.setShzt(request.getParameter("shjg"));
		model.setShyj(request.getParameter("shyj"));
		
		boolean result = service.savePlsh(model,dataJson);
		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS
				: MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/*****�޸Ĳ鿴******/
	@SystemAuth(url = "szdw_fdyxx_xgsh.do")
	public ActionForward xgjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CsszService csszService = new CsszService();
		CsszModel cssz = csszService.getModel();
		
		request.setAttribute("cssz", cssz);
		return mapping.findForward("xgjgCk");
	}
	
	
	/**�������***/
	@SystemAuth(url = "szdw_fdyxx_xgsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyxxService service = TransactionControlProxy.newProxy(new FdyxxService());
		User user = getUser(request);
		ShxxModel model = new ShxxModel();
		model.setShr(user.getUserName());
		model.setGwid(request.getParameter("gwid"));
		model.setYwid(request.getParameter("ywid"));
		model.setSqrid(request.getParameter("zgh"));
		model.setShzt(request.getParameter("shjg"));
		model.setShyj(request.getParameter("shyj"));
		model.setThgw(request.getParameter("thgw"));
		boolean result = service.saveXgsh(model);

		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS
				: MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����Ա��Ϣά���޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2015-12-16 ����10:15:46
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
	public ActionForward fdyxxXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyxxModel model = (FdyxxModel)form;

		FdyxxService service = new FdyxxService();
		
		String gndm = "szdw_fdyxx_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);
		
		boolean isSuccess = service.runUpdate(model);
		//��ͥ��Ա
		if(isSuccess){
			String jtcyJson = request.getParameter("jtcyList");
			List<JtcyModel> jtcyList = JsonUtil.jsonToList(jtcyJson,JtcyModel.class);
			isSuccess = service.updateJtcyxx(model.getZgh(),jtcyList);
		}
		//��λ�춯
		if(isSuccess){
			String gwydJson = request.getParameter("gwydList");
			List<GwydModel> gwydList = JsonUtil.jsonToList(gwydJson,GwydModel.class);
			isSuccess = service.updateGwydxx(model.getZgh(),gwydList);
		}
		//�������
		if(isSuccess){
			String jtjlJson = request.getParameter("jtjlList");
			List<JtjlModel> jtjlList = JsonUtil.jsonToList(jtjlJson,JtjlModel.class);
			isSuccess = service.updateJtjlxx(model.getZgh(),jtjlList);
		}
		//רҵ����ְ����Ϣ
		if(isSuccess){
			String zyjszwJson = request.getParameter("zyjszwList");
			List<ZyjsgwModel> zyjszwList = JsonUtil.jsonToList(zyjszwJson,ZyjsgwModel.class);
			isSuccess = service.updateZyjsgwxx(model.getZgh(),zyjszwList);
		}
		//ѧϰ������Ϣ
		if(isSuccess){
			String xxjlJson = request.getParameter("xxjlList");
			List<XxjlModel> xxjlList = JsonUtil.jsonToList(xxjlJson,XxjlModel.class);
			isSuccess = service.updateXxjlxx(model.getZgh(),xxjlList);
		}
		//����������Ϣ
		if(isSuccess){
			String gzjlJson = request.getParameter("gzjlList");
			List<GzjlModel> gzjlList = JsonUtil.jsonToList(gzjlJson,GzjlModel.class);
			isSuccess = service.updateGzjlxx(model.getZgh(),gzjlList);
		}
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward getJsdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyxxModel myForm = (FdyxxModel) form;
		File wordFile = getJsdjWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getJsdjWord(FdyxxModel myForm,HttpServletRequest request) throws Exception{
		FdyxxService service = new FdyxxService();
		String zgh = myForm.getZgh();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> FdyxxMap = service.getFdyxxMap(zgh);
		HashMap<String, String> Fdycjgzsj= service.getFdyInfo(zgh);
		data.put("cjgzrq", Fdycjgzsj.get("cjgzrq"));
		FdyxxMap.put("bz",HtmlUtil.xmlZy(FdyxxMap.get("bz")));
	    String photo = service.getPhoto(zgh);
		data.putAll(FdyxxMap);
		List<HashMap<String, String>> hjxxList =service.getHjxxList(zgh);
		List<HashMap<String, String>> fblwList =service.getFblwList(zgh);
		List<HashMap<String, String>> kyxmList =service.getKyxmList(zgh);
		data.put("hjxxList", hjxxList);
		data.put("fblwList", fblwList);
		data.put("kyxmList", kyxmList);
		PjjgAction pjjg = new PjjgAction();
		int size=(4 - fblwList.size())<0?0:(4 - fblwList.size());
		data.put("blankfblw", pjjg.getBlankList(size));
		size =(4 - hjxxList.size())<0?0:(4 - hjxxList.size());
		data.put("blankhjxx", pjjg.getBlankList(size));
		size =(4 - kyxmList.size())<0?0:(4 - kyxmList.size());
		data.put("blankkyxm", pjjg.getBlankList(size));
		data.put("photo", photo);
		if("10125".equals(Base.xxdm)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw","jsdjb_10125.xml",myForm.getZgh()+FdyxxMap.get("xm"));
		}
//		if("14073".equals(Base.xxdm)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw","jsdjb_14073.xml",myForm.getZgh()+FdyxxMap.get("xm"));
//		}
		if("13431".equals(Base.xxdm)){
			List<HashMap<String, String>> xsryList =service.getXsryList(zgh);//�����ڼ�ѧ������
			StringBuffer xsryBuff = new StringBuffer();
			for (HashMap<String, String> map : xsryList) {
				if(StringUtils.isNotNull(map.get("xsxm"))){
					xsryBuff.append(map.get("xsxm")+" ��� ");
					xsryBuff.append(map.get("bsmc")+" "+map.get("hjdj"));
					xsryBuff.append(" ��");
				}
			}
			String xsry = xsryBuff.toString();
			data.put("xsry",xsry.length()>1 ?  xsry.substring(0, xsry.length() - 1) : "");
			List<HashMap<String, String>> grryList =service.getGrryList(zgh);//����Ա�ڼ��������
			StringBuffer grryBuff = new StringBuffer();
			for (HashMap<String, String> map : grryList) {
				if(StringUtils.isNotNull(map.get("bsmc"))){
					grryBuff.append(map.get("bsmc")+" ");
					grryBuff.append(map.get("hjdj"));
					grryBuff.append(" ��");
				}
			}
			String grry=grryBuff.toString();
			data.put("grry",grry.length()>1 ?  grry.substring(0, grry.length() - 1) : "");
			List<HashMap<String, String>> zgzsList =service.getZgzsList(zgh);//�ʸ�֤��
			StringBuffer zgzsBuff = new StringBuffer();
			for (HashMap<String, String> map : zgzsList) {
				if(StringUtils.isNotNull(map.get("zsmc"))){
					zgzsBuff.append("��"+map.get("zsmc")+"��").append(" ��");
				}
			}
			String zgzs = zgzsBuff.toString();
			data.put("zgzs",zgzs.length()>1 ?  zgzs.substring(0, zgzs.length() - 1) : "");
			
			List<HashMap<String, String>> kylwList =service.getKylwList(zgh);//������� ����
			List<HashMap<String, String>> kyktList =service.getKyktList(zgh);//������� ����
			StringBuffer kyqkBuff = new StringBuffer();
			for (HashMap<String, String> map : kylwList) {
				if(StringUtils.isNotNull(map.get("lwmc"))){
					kyqkBuff.append("��"+map.get("lwmc")+"��");
					kyqkBuff.append(" ��");
				}
			}
			for (HashMap<String, String> map : kyktList) {
				if(StringUtils.isNotNull(map.get("ktmc"))){
					kyqkBuff.append("��"+map.get("ktmc")+"��").append(" ��");
				}
			}
			String kyqk=kyqkBuff.toString();
			data.put("kyqk",kyqk.length()>1 ?  kyqk.substring(0, kyqk.length() - 1) : "");
			List<HashMap<String, String>> jxqkList =service.getJxqkList(zgh);//��ѧ�������
			StringBuffer jxqkBuff = new StringBuffer();
			for (HashMap<String, String> map : jxqkList) {
				if(StringUtils.isNotNull(map.get("xsskc"))){
					jxqkBuff.append(map.get("xsskc"));
					jxqkBuff.append(" ��");
				}
			}
			String jxqk=jxqkBuff.toString();
			data.put("jxqk",jxqk.length()>1 ?  jxqk.substring(0, jxqk.length() - 1) : "");
			List<HashMap<String, String>> gzjlList =service.getGzjlList(zgh,"4");//��������
			service.addKList(gzjlList, 4);
			data.put("gzjlList", gzjlList);
			String dbzrs = service.getDbzrs(zgh);//����������
			data.put("dbzrs", dbzrs);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//szdw","jsdjb_13431.xml",myForm.getZgh()+FdyxxMap.get("xm"));
		}
		return file;
	}
	
	public ActionForward getJsdjTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyxxModel myForm = (FdyxxModel) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setZgh(values[i]);
				File file = getJsdjWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:��ʦ�������ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-19 ����02:11:19
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
	public ActionForward jssfPlwh (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String zghs = request.getParameter("zghs");
		String[] zgharr = zghs.split(",");
		if(zgharr.length == 1){
			FdyxxService service = new FdyxxService();
			String jssfstr = service.getFdyInfo(zgharr[0]).get("jssf");
			request.setAttribute("jssfs", jssfstr);
		}
		request.setAttribute("zghs", zghs);
		return mapping.findForward("jssfwh");
	}
	
	/**
	 * 
	 * @����: �����ʦ�������ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-19 ����02:11:48
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
	public ActionForward savejssfPlwh (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] zghs = request.getParameter("zghs").split(",");
		String jssf = StringUtils.joinArr(request.getParameterValues("jssf"));
		boolean result = new FdyxxService().savejssfPlwh(zghs, jssf);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
}
