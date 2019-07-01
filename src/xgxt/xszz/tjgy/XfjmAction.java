package xgxt.xszz.tjgy;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgAction;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgModel;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bbwh.BbwhService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcDao;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhForm;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

public class XfjmAction extends BasicAction {

	
	/**
	 * ��Ŀ������Ϣҳ��
	 */
	public ActionForward xmsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmid = request.getParameter("pkValue");
		XfjmService service = new XfjmService();
		
		if (StringUtils.isNotNull(xmid)){
			//��Ŀ������Ϣ
			request.setAttribute("rs", service.getXmszInfo(xmid));
			//������Ŀ��������
			request.setAttribute("tjList", service.getXmtjList(xmid));
		}
		
		return mapping.findForward("xmsz");
	}
	
	
	
	/**
	 * ������Ŀ����
	 */
	public ActionForward saveXmsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		
		boolean result = service.saveXmsz(model);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return xmsz(mapping,form,request,response);
	}
	
	
	
	/**
	 * ɾ����Ŀ���������Ϣ
	 */
	public ActionForward delXmsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String[] xmid = request.getParameterValues("primarykey_cbv");
		XfjmService service = new XfjmService();
		
		boolean result = service.delXmsz(xmid);
		
		request.setAttribute("message", result ? DEL_SUCCESS : DEL_FAIL);
		return xmszManage(mapping, form, request, response);
	}

	
	
	/**
	 * ��Ŀ���ù��� 
	 */
	public ActionForward xmszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		
		//ͳ�Ʋ�ѯ
		List<HashMap<String,String>> xmszList = service.getXmszList(model);
		
		request.setAttribute("rs", xmszList);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "tjgy_jmxmsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmszManage");
	}
	
	
	
	/**
	 * ��Ŀ������� 
	 */
	public ActionForward xmsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "�Բ��𣬱��˵�����ѧ���û����ʣ�");
			return new ActionForward("/prompt.do",false);
		}
		String xh = user.getUserName();
		XfjmService service = new XfjmService();
		
		List<HashMap<String,String>> xmsqList = service.getXmsqInfoByXh(xh);
		
		request.setAttribute("xmsqList", xmsqList);
		request.setAttribute("path", "tjgy_jmxmsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsqManage");
	}

	
	
	/**
	 * ��Ŀ����չʾ
	 */
	public ActionForward xmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		XsxxglService xsxxglService = new XsxxglService();
		
		model.setXn(Base.currXn);
		//����ѧ����Ϣ
		HashMap<String,String> stuInfo = xsxxglService.selectStuinfo(model.getXh());
		//������Ŀ����
		List<HashMap<String,String>> xmtjList = service.getXmtjList(model.getXmid());
		//����ѧ��������Ϣ
		HashMap<String,String> xmsqInfo = service.getXmsqInfo(model);

		request.setAttribute("xmsqInfo", xmsqInfo);
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xmtjList", xmtjList);
		return mapping.findForward("xmsq");
	}
	
	
	
	/**
	 * ������Ŀ������Ϣ
	 */
	public ActionForward saveXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		//��Ŀ���뱣��
		boolean result = service.saveXmsqInfo(model);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return xmsq(mapping,form,request,response);
	}
	
	
	
	
	/**
	 * ��Ŀ��˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if (!"xy".equals(user.getUserType()) && !"xx".equals(user.getUserType()) && !"admin".equals(user.getUserType())){
			request.setAttribute("message", "�Բ�������Ȩ���ʴ˲˵���");
			return new ActionForward("/prompt.do",false);
		}
		
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		String path = "tjgy_jmxmsh.do";
		SearchModel searchModel = model.getSearchModel();
		
		searchModel.setPath(path);
		//�û�Ȩ�޿���
		if ("xy".equals(user.getUserType())){
			searchModel.setSearch_tj_xy(new String[]{user.getUserDep()});
		} else {
			searchModel.setSearch_tj_shzt1(new String[]{"ͨ��"});
		}
		List<HashMap<String,String>> xmsqList = service.getXmsqList(model,user);
		
		request.setAttribute("rs", xmsqList);
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("searchTj", model.getSearchModel());
		return mapping.findForward("xmshManage");
	}
	
	
	/**
	 * ���������ʾҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmdgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		XsxxglService xsxxglService = new XsxxglService();
		
		HashMap<String,String> xmsqInfo = service.getXmsqInfo(model);
		HashMap<String,String> stuInfo = xsxxglService.selectStuinfo(xmsqInfo.get("xh"));
		List<HashMap<String,String>> xmtjList = service.getXmtjList(xmsqInfo.get("xmid"));
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xmsqInfo", xmsqInfo);
		request.setAttribute("xmtjList", xmtjList);
		return mapping.findForward("xmdgsh");
	}
	
	
	/**
	 * ������˱���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmdgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		
		boolean result = service.saveDgsh(model, getUser(request));
		
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return xmdgsh(mapping,form,request,response);
	}
	
	
	/**
	 * ����Ƿ�Υ����ã�AJAX���� ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSfktg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		
		boolean sfksh = service.checkSfktg(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(sfksh);
		return null;
	}
	
	
	
	/**
	 * �������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] pkValue = request.getParameterValues("primarykey_cbv");
		
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
				
		boolean result = service.plxmsh(model, pkValue, getUser(request));
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return xmshManage(mapping, form, request, response);
	}
	
	
	
	/**
	 * �����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		User user = getUser(request);
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		String path = "tjgy_jmxmjg.do";
		SearchModel searchModel = model.getSearchModel();
		searchModel.setPath(path);
		
		List<HashMap<String,String>> xmsqList = service.getXmsqList(model,user);
		
		request.setAttribute("rs", xmsqList);
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("searchTj", model.getSearchModel());
		
		return mapping.findForward("jgcxManage");
	}
	
	
	
	/**
	 * ɾ����Ŀ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] pkValue = request.getParameterValues("primarykey_cbv");
		XfjmService service = new XfjmService();

		boolean result = service.delXmsq(pkValue);
		request.setAttribute("message", result ? DEL_SUCCESS : DEL_FAIL);
		return jgcxManage(mapping, form, request, response);
	}
	
	
	
	
	/**
	 * ����ͳ�Ƶ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expJgcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		XfjmForm model = (XfjmForm) form;
		XfjmService service = new XfjmService();
		
		String[] topList = new String[]{"���","ѧԺ","ѧ��ѧ��","����","�Ա�",
						"רҵ�༶","Ӧ��ѧ��","����ѧ��","����ԭ��","ѧ���ֻ�"};
		
		List<String[]> dataList = service.getExpXmsqList(model, user);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(dataList, topList, topList, response.getOutputStream());
		return null;
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:����ZIP
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-31 ����11:32:26
	 * @�޸ļ�¼:
	 */

	public ActionForward downloadZip(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String sqjb =  request.getParameter("sqjb");
		
		String mbmc = "";
		if("yj".equals(sqjb)){
			mbmc = "xfjmbyj_hzsf";
		}
		if("xj".equals(sqjb)){
			mbmc = "xfjmbxj_hzsf";
		}
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				String[] temp = values[i].split(";");
				String pkValue = temp[0];
				String xh = temp[1];
				File file = getWord(pkValue,xh,mbmc);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 * @����:��ӡword
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-31 ����11:32:52
	 * @�޸ļ�¼:
	 */

	public ActionForward downloadWord(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfjmForm myForm = (XfjmForm) form;
		String pkValue = myForm.getPkValue();
		String xh = myForm.getXh();
		String sqjb = myForm.getBz();
		String mbmc = "";
		if("yj".equals(sqjb)){
			mbmc = "xfjmbyj_hzsf";
		}
		if("xj".equals(sqjb)){
			mbmc = "xfjmbxj_hzsf";
		}
		File wordFile = getWord(pkValue,xh,mbmc);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	// ���ģ����������word�ļ�
	private File getWord(String pkValue,String xh,String mbmc) throws Exception {
		XfjmService service = new XfjmService();
		ZzxmjgService zzxmjgService = new ZzxmjgService();
		XfjmForm myForm = new XfjmForm();
		myForm.setPkValue(pkValue);
		HashMap<String,String> xmsqxx = service.getXmsqInfo(myForm);
		HashMap<String, String> bbMap = null;// ����

		// ������Ŀ��ȡ������Ϣ
		BbwhService bbwhService = new BbwhService();
		bbMap = bbwhService.getDataById(mbmc);
		// ��ѯ���������������Ϣ
		if (bbMap == null || bbMap.isEmpty()) {
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}
		if (!StringUtil.isNull(xh)) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//ת��Ϊ�������ڸ�ʽ
			xsjbxx.put("rxrq", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"), FomartDateType.month));
			// ����ѧ����ͥ������Ϣ
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkmodel = jtqkService.getModel(xh);

			// ����ѧ�������϶����   ѧ��
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xmsqxx
					.get("xh"), xmsqxx.get("xn"), "02");
			if(knsjg == null){
				knsjg = knsjgService.getXsknsjg(xmsqxx
						.get("xh"), xmsqxx.get("xn"), "01");
			}
			
			
			KnsdcService knsdcService = new KnsdcService();
			Map<String, Object> data = new HashMap<String, Object>();
			data.putAll(xsjbxx);// ѧ��������Ϣ
			data.put("jtqk", jtqkmodel == null ? new JtqkdcForm() : jtqkmodel);// ��ͥ���
			if (jtqkmodel != null && jtqkmodel.getJtnzsr() != null) {
				data.put("jtyzsr", (Double.parseDouble(jtqkmodel.getJtnzsr()) / 12));//��ͥ��������
				data.put("jtrjysr", jtqkmodel.getJtrs() == null ? "" : (Double.parseDouble(jtqkmodel.getJtnzsr()) / 12 / Integer.parseInt(jtqkmodel.getJtrs())));//��ͥ�˾�������
			} else {
				data.put("jtyzsr", "");
				data.put("jtrjysr", "");
			}
			//����ʦ�����Ի�
			if("10346".equals(Base.xxdm)){
				//�������������Ϣ
				PjjgService pjjgservice = new PjjgService();
				//����������Ϣ��3����
				List<HashMap<String, String>> zzdwlist = zzxmjgService.getZzxmjgInfoList(xh);
				int m=3-zzdwlist.size();
				for (int i = 0; i <m; i++) {
					zzdwlist.add(new HashMap<String, String>());
				}
				List<HashMap<String, String>> pjjg3line = pjjgservice.getHjqkByXhMap(xh, 3);  //��ȡ����3���������
				//��������Ϣ������������У�����xml�ļ���ȡ
				for (int i = 0; i < pjjg3line.size(); i++) {
					pjjg3line.get(i).put("zzxmmc", zzdwlist.get(i).get("xmmc"));
					pjjg3line.get(i).put("zzje", zzdwlist.get(i).get("je"));
				}
				data.put("pjjg3_hzsf", pjjg3line);
				//��ѧ������Ϣ
				XszzSqshService xszzSqshService = new XszzSqshService();
				String dkxx = xszzSqshService.getXsDkxx(xmsqxx.get("xn"), xh);
				data.put("zxdkxx", dkxx);
				
			}
			
			data.put("knsdcList", knsdcService.getKnsdcList());// ����������list
			data.put("rddc", knsjg.get("rddc"));// �϶�����
			data.put("rddcmc", knsjg.get("dcmc") == null ? "" : knsjg.get("dcmc"));//�϶���������
			data.put("xxmc", Base.xxmc);// ѧУ����
			data.put("sqly", "");// ��������
			data.put("currXn", xmsqxx.get("xn"));// ѧ��
			//data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
			//ת��Ϊ�������ڸ�ʽ
			data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month));// ��������
			//ѧ������
			String xsnl = " ";
			try {
				String csn = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.year).replaceAll("��", "");
				Calendar cal = Calendar.getInstance();
				int currn = cal.get(Calendar.YEAR); //��ǰ��
				xsnl = String.valueOf(currn - Integer.valueOf(csn));
			} catch (Exception e) {
				//e.printStackTrace();
			}
			data.put("xsnl", xsnl);// ѧ������


			File wordFile = null;
			if ("12867".equals(Base.xxdm)) {
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(xsjbxx.get("bjmc") + "" + xsjbxx.get("xh") + "[" + xsjbxx.get("xm") + "]"));
			} else {
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(xh + "[" + xsjbxx.get("xm") + "]"));// "classpath://templates//" +  "xszz"// +"gjjxjb.xml"
			}
			return wordFile;
		}
		
		return null;
	}
}
