package com.zfsoft.xgxt.qgzx.jfcjgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cjgl.QgzxCjglService;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.jfgl.QgzxJfglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ѳ�����
 * @�๦������: ����ά��action
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-04-15 ����03:33:37
 * @�汾�� V5.7.15
 * @�޸ļ�¼:
 */
public class CjffSjwhAction extends SuperAction {
	private static final String CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "qgzx_jfcjgl_cjff.do?method=gjcxCjff";

	/**
	 * @����: ��𷢷Ÿ߼�ģʽ��ѯ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-15 ����03:34:24
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gjcxCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CjffwhForm model = (CjffwhForm) form;
		CjffSjwhService service = new CjffSjwhService();
		// ��ȡ��¼�û�
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "qgzx_jfcjgl_cjff.do?method=gjcxCjff";
		request.setAttribute("path", path);
		request.setAttribute("tableName", "xg_qgzx_cjff");
		request.setAttribute("realTable", "xg_qgzx_cjff");
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		QgCommUtilf.setCzpath(request, searchModel);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("searchTj", searchModel);
		QgCommUtilf.setCssz(request);
		return mapping.findForward("gjcxCjff");
	}

	/**
	 * 
	 * @����: ��λ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����11:18:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward getCjsxForGw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn = request.getParameter("xn");
		String bmdm = request.getParameter("bmdm");
		String gwdm = request.getParameter("gwdm");
		CjffSjwhService service = new CjffSjwhService();
		String cjsx = service.getCjsxForGw(xn, bmdm, gwdm);
		String sx = service.getSxForGw(xn,bmdm,gwdm);
		request.setAttribute("sx",sx);
		response.getWriter().print(cjsx);
		return null;
	}

	/**
	 * @��������:
	 * @auther: ������[1692]
	 */
	public ActionForward getSxForGw(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn = request.getParameter("xn");
		String bmdm = request.getParameter("bmdm");
		String gwdm = request.getParameter("gwdm");
		CjffSjwhService service = new CjffSjwhService();
		String sx = service.getSxForGw(xn,bmdm,gwdm);
		response.getWriter().print(sx);
		return null;
	}

	/**
	 * @����:���ӳ�𷢷�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-15 ����03:35:01
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-���ѳ�����-����ά��-����XH:{xh},XN:{xn},GWDM:{gwdm},FFSJ:{ffsj},GS:{gs},JE:{je}")
	public ActionForward zjCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjffwhForm myForm = (CjffwhForm) form;
		CjffSjwhService service = new CjffSjwhService();
		User user = getUser(request);
		String disQg = request.getParameter("disQg");
		if (!StringUtils.isNull(disQg) || StringUtils.isNull(myForm.getYrbm())) {// ���Ϊ�������ڹ�����Ա
			myForm.setYrbm(user.getUserDep());
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// ����ѧ��������Ϣ
		boolean isPks = service.isPks(myForm.getXh()); //�ж��Ƿ���ƶ������������ж����𷢷Ŵ�һ��
		szXsxx(request, myForm.getXh(),isPks);
		String path = "qgzx_jfcjgl_cjff.do?method=zjCjff";
		request.setAttribute("path", path);
		
		// ѧ��������Ϣ��ʾ����
		HashMap<String,String> ffmrCs = service.getFfmrCs(request, myForm);
		request.setAttribute("rs",ffmrCs );
		
		request.setAttribute("model", myForm);
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		if("xq".equals(QgCommUtilf.getQgzq())){
			myForm.setXq(null);
		}
		

		// �ڹ�ע���������
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String, String> qgzxCsszRs = qgzxCsszService.getCssz();
		
		//���ݴ�ѧƶ���������Ի�
		if(Globals.XXDM_WZDX.equals(Base.xxdm)){
			if(isPks) qgzxCsszRs.put("cjbz", qgzxCsszRs.get("pkscjbz"));
		}
		
		request.setAttribute("qgzxCsszRs", qgzxCsszRs);
		this.saveToken(request);
		QgCommUtilf.setCssz(request);
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("zjCjff");
	}
	/**
	 * 
	 * @����: ����Ƿ񷢷Ź�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����02:42:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward checkFfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjffSjwhService service = new CjffSjwhService();
		String wbh=request.getParameter("wbh");
		String xh=request.getParameter("xh");
		String xn=request.getParameter("xn");
		String yrdwdm=request.getParameter("yrdwdm");
		String gwdm=request.getParameter("gwdm");
		String ffsj=request.getParameter("ffsj");
		Boolean ish=service.isHaveFfxx(wbh, xh, xn, yrdwdm, gwdm,ffsj);
		response.getWriter().print(ish);
		return null;
	}

	/**
	 * @����:�޸ĳ�𷢷�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-15 ����03:35:25
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-���ѳ�����-����ά��-�޸�WBH:{wbh},XH:{xh},XN:{xn},GWDM:{gwdm},FFSJ:{ffsj},GS:{gs},JE:{je}")
	public ActionForward xgCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjffwhForm myForm = (CjffwhForm) form;
		CjffSjwhService service = new CjffSjwhService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			if(result){
				result=service.updataFfxx(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		CjffwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		// ����ѧ��������Ϣ
		boolean isPks = service.isPks(myForm.getXh());	//�ж��Ƿ���ƶ������������ж����𷢷Ŵ�һ��
		szXsxx(request, model.getXh(),isPks);
		String path = "qgzx_jfcjgl_cjff.do?method=xgCjff";
		request.setAttribute("path", path);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request, myForm)));
		String flag = request.getParameter("flag");
		request.setAttribute("type", "update");
		QgCommUtilf.setCssz(request);
		if (flag != null) {
			request.setAttribute("xqmc", Base.getXqmcForXqdm(model.getXq()));
			return mapping.findForward("sjwhCk");
		}

		// �ڹ�ע���������
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String, String> qgzxCsszRs = qgzxCsszService.getCssz();
		
		//���ݴ�ѧƶ���������Ի�
		if(Globals.XXDM_WZDX.equals(Base.xxdm)){
			if(isPks) qgzxCsszRs.put("cjbz", qgzxCsszRs.get("pkscjbz"));
		}
		
		request.setAttribute("qgzxCsszRs", StringUtils.formatData(qgzxCsszRs));
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("xgCjff");
	}

	/**
	 * @����:ɾ����𷢷���Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-15 ����03:35:57
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward ��������
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-���ѳ�����-����ά��-ɾ��VALUES:{values}")
	public ActionForward scCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CjffSjwhService service = new CjffSjwhService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;

	}

	/**
	 * @����:����ѧ��������Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-3 ����10:59:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param xh
	 *            ѧ�� void ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private void szXsxx(HttpServletRequest request, String xh,boolean isPks) {
		// ��ѯѧ����Ϣ
		if (xh != null && !"".equals(xh)) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			
			xsjbxx.put("sfkns",isPks?"��":"��");
			
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}

	/**
	 * 
	 * @����:�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-24 ����11:51:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	public ActionForward checkJe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ffje = request.getParameter("ffje");
		String yrdwdm = request.getParameter("yrdwdm");
		String oldje = request.getParameter("oldje");
		String oldyrdm = request.getParameter("oldyrbm");
		String nd = request.getParameter("nd");
		
		//���ѻ�����ʽ:0,���껮��
		if("0".equals(new QgzxCsszService().getJfhbfs())){
			nd = StringUtils.isNull(nd) ? "-1" : nd.substring(0, 4);
		}
		
		QgzxJfglService qjs = new QgzxJfglService();
		Map<String, String> map = qjs.getJfxx(yrdwdm, nd);
		boolean isKzhbje=qjs.isKzHbjs();
		if(isKzhbje&&(null==map||map.size()<=0)){
			String message=null;
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				message = MessageUtil.getText(MessageKey.QGZX_CHECK_WFFJF_YF,
					new Object[] { nd});
			}else{
				message = MessageUtil.getText(MessageKey.QGZX_CHECK_WFFJC,
						new Object[] { nd});
			}
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		// String hbzje=map.get("hbzje");
		String syje = map.get("syje");
		String yrdmmc = map.get("yrdwmc");
		CjffSjwhService service = new CjffSjwhService();
		// ͬһ�����޸Ľ���ǲ�����ԭ�����
		if (!StringUtil.isNull(oldje) && yrdwdm.equals(oldyrdm)) {
			Float syjeI = Float.parseFloat("0");
			if(syje != null && !syje.equals("")){
				 syjeI = Float.parseFloat(syje);
			}else{
				 syjeI = Float.parseFloat("0");
			}
			
			syjeI = syjeI + Float.parseFloat(oldje);
			syje = syjeI.toString();
		}
		if (isKzhbje&&service.checkJfyg(ffje, syje)) {
			String message=null;
			if("1".equals(new QgzxCsszService().getJfhbfs())){
				message = MessageUtil.getText(MessageKey.QGZX_CHECK_FFJE_YF,
					new Object[] { ffje, yrdmmc, nd, syje });
			}else{
				message = MessageUtil.getText(MessageKey.QGZX_CHECK_FFJE,
						new Object[] { ffje, yrdmmc, nd, syje });
			}
			response.getWriter().print(getJsonMessage(message));
		} else {
			response.getWriter().print(getJsonMessage("true"));
		}
		return null;
	}

	/**
	 * @����:
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-3 ����03:02:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward getGwmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = null;
		QgzxCjglForm model = new QgzxCjglForm();
		model.setXn(request.getParameter("xn"));
		model.setYrbm(request.getParameter("yrbm"));
		model.setXq(request.getParameter("xq"));
		List<HashMap<String, String>> gwList = new QgzxCjglService()
				.getGwdm(model);
		JSONArray dataList = JSONArray.fromObject(gwList);

		try {
			out = response.getWriter();
			out.print(dataList);
			out.flush();
			out.close();
		} catch (Exception e) {

		}
		return null;

	}

	/**
	 * �ڹ����ά���Զ��嵼��
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qgjgwhExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CjffwhForm model = (CjffwhForm) form;
		CjffSjwhService service = new CjffSjwhService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);
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
	
	//������ҵ��ѧ�ڹ���ѧ��𷢷��걨����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getCjffSbbDclist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjffwhForm model = (CjffwhForm) form;
		CjffSjwhDAO dao = new CjffSjwhDAO();
		Map<String,Object> data = new HashMap<String,Object>();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*��ʽ�����š���ȡ��͡��·ݡ���Ϊ�ù��ڼ� start*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");
		String[] nd = model.getSearchModel().getSearch_tj_nd();
		String[] yf = model.getSearchModel().getSearch_tj_yf();
		Arrays.sort(yf);
		int ndInt = Integer.parseInt(nd[0]);
		int yfBeginInt = Integer.parseInt(yf[0]) - 1;
		int yfEndInt = Integer.parseInt(yf[yf.length - 1]) - 1;
		Calendar c = Calendar.getInstance();
		c.set(ndInt, yfBeginInt, 1);
		String yfBeginStr = format.format(c.getTime());
		c.set(Calendar.MONTH, yfEndInt);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		String yfEndStr = format.format(c.getTime());
		/*��ʽ�����š���ȡ��͡��·ݡ���Ϊ�ù��ڼ� end*/
		User user = getUser(request);
		//��ѯҳ��߼���ѯ����ѡ���Ľ����
		List<HashMap<String,String>> resultList = dao.getCjffSbbDclist(model, user);
		//��ѯ���˵�λ����list,�����ж��Ƿ���ڶ����λ�ĳ�𷢷���Ϣ�����У��������ݣ����zip������û���������word�ļ�
		List<HashMap<String,String>>  fzlist = dao.getFzlist(model, user);
		//�ж��Ƿ���ڶ�����˵�λ
        if(fzlist.size() < 2){
        	data.put("cjfflist", resultList);
    		data.put("yfBeginStr", yfBeginStr);
    		data.put("yfEndStr", yfEndStr);
    		if(fzlist != null){
        		data.put("yrdwmc",fzlist.get(0).get("mc"));
    		}
    		File WordFile = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx", "bjlydx_qgzx_word_sqb.xml", "�걨��");
    		FileUtil.outputWord(response, WordFile);
    		return null;
        }
        List<File> files = new ArrayList<File>();
        
        //�������ݷ��飬�����zip�����
        for(int i=1;i<=fzlist.size();i++){
        	File file = null;
        	String yrdwmc = null;
        	List<HashMap<String, String>> filelist = new ArrayList<HashMap<String,String>>();
        	Map<String,Object> datatemp = new HashMap<String,Object>();
        	for (HashMap<String, String> hashMap : resultList) {
        		if(fzlist.get(i-1).get("dm").equals(hashMap.get("yrdwdm"))){
        			filelist.add(hashMap);
        			yrdwmc = fzlist.get(i-1).get("mc");
        		}
			}
        	data.put("cjfflist", filelist);
        	data.put("yfBeginStr", yfBeginStr);
        	data.put("yfEndStr", yfEndStr);
        	data.put("yrdwmc", yrdwmc);
        	file = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx","bjlydx_qgzx_word_sqb.xml",yrdwmc+"�걨��");
        	files.add(file);
        }
        File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
        FileUtil.outputZip(response, zipFile);
		return null;
	}
}
