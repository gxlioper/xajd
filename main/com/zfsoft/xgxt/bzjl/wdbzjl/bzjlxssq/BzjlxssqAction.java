/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-9 ����11:22:37 
 */  
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjlxssq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg.BzjljgService;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjlsqsh.BzjlsqshService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.pjpy.xzhcp.sq.ZhcpDjService;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.bbwh.BbwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmModel;
import com.zfsoft.xgxt.xpjpy.pjdm.PjdmService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-9 ����11:22:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BzjlxssqAction extends SuperAction {
	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));
	private static final String template_dir = "classpath://templates//pjpy";//ģ��·��
	private static final String default_template = "default.xml";//Ĭ��ģ��
	
	private static final String url = "bzjl_xssq.do";
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-9 ����11:52:31
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
	@SuppressWarnings("static-access")
	public ActionForward viewPjxmsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		BzjlxssqModel model = (BzjlxssqModel) form;
		BzjlxssqService service = new BzjlxssqService();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		
		User user = getUser(request);
		String userType = user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		
		if(QUERY.equals(model.getType())){
			String xh = user.getUserName();
			model.setXh(xh);
			
			List<HashMap<String , Object>> data = service.getSqXmList(model);
			
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
			
		}
		
		
		//�鿴�Ƿ��ڲ�������
		
		boolean isExistCpz = service.isExistCpz(csszModel,user.getUserName());
		
		if(!isExistCpz){
			
			String msg = "�����ڱ��β������ڣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		String path = "pj_pjxmsq.do";
		request.setAttribute("path", path);
		request.setAttribute("xzdm",model.getXzdm());
		request.setAttribute("cssz", csszModel);
		request.setAttribute("pjzqmc", csszService.getPjzq().get("pjzqmc"));
		String dateFormat = "yyyy-MM-dd HH:mm";
		request.setAttribute("currDate", service.getCurrTime(dateFormat));
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("viewPjxmsqList");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����02:57:29
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
	public ActionForward viewXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		User user = getUser(request);
		
		String xh = user.getUserName();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		request.setAttribute("pjzqmc", csszService.getPjzq().get("pjzqmc"));
		
		
		if (!StringUtil.isNull(xh)){
			//ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		this.saveToken(request);
		return mapping.findForward("xmsqView");
		
	}
	
	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����05:00:18
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
	@SystemLog(description="������������-�ҵ�����-�������루ѧ����-��������-XMDMS:{xmdms}")
	public ActionForward saveJxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BzjlxssqModel model = (BzjlxssqModel) form;
		BzjlxssqService service = new BzjlxssqService();
		
		String[] xmdm = request.getParameterValues("xmdms");
		
		boolean result = service.saveJxsq(xmdm, model);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����06:23:30
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
	public ActionForward updateSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BzjlxssqModel formModel = (BzjlxssqModel) form;
		BzjlxssqService service = new BzjlxssqService();
		User user = getUser(request);
		CsszService csszService = new CsszService();
		
		BzjlxssqModel model = service.getModel(formModel);
		
		if (model != null){
			BeanUtils.copyProperties(formModel, model);
			
			//ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//ѧ�������༶
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String, String> cpbjxx = zcfsService.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			
			//������Ŀ��Ϣ
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", xmwhModel);
			
			request.setAttribute("pjzqmc", csszService.getPjzq().get("pjzqmc"));
			//�й�����ѧԺ
			if("10355".equals(Base.xxdm)){
				XmwhModel xmwhForm = new XmwhService().getModel(model.getXmdm());
				request.setAttribute("hjjgxskg", xmwhForm.getSfkfhjqkxz());
				if("1".equals(xmwhForm.getSfkfhjqkxz())){
					request.setAttribute("hjjgList", new BzjlsqshService().getHjxxList(model.getXh(),model.getXn(), model.getXq()));
				}
			}
		}
		
		request.setAttribute("UserType", user.getUserType());
		
		return mapping.findForward("updateSqb");
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����06:17:51
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
	@SystemLog(description="������������-�ҵ�����-�������루ѧ����-�����޸�-ID:{id}")
	public ActionForward saveUpdateSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		BzjlxssqModel model = (BzjlxssqModel) form;
		BzjlxssqService service = new BzjlxssqService();
		
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-1-14 ����04:46:29
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
	@SystemLog(description="������������-�ҵ�����-�������루ѧ����-����-VALUES:{values}")
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{

		BzjlxssqService service = new BzjlxssqService();
		ShlcInterface shlcService = new CommShlcImpl();
		
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		
		boolean result = shlcService.firstStepCancle(values, lcid);
		
		if(result){
			BzjlxssqModel model = new BzjlxssqModel();
			model.setSqid(values);
			model.setShzt(Constants.YW_WTJ);
			result = service.runUpdate(model);
		}
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����07:09:59
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
	public ActionForward viewLcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		BzjlxssqModel modelForm = (BzjlxssqModel) form;
		BzjlxssqService service = new BzjlxssqService();
		
		//������ѯ
		BzjlxssqModel model = service.getModel(modelForm);
		
		if (model != null){
			BeanUtils.copyProperties(modelForm, model);
		}
		
		//������¼
		List<HashMap<String,String>> spjlList = service.getSpjlList(modelForm.getSqid());
		request.setAttribute("spjlList", spjlList);
		request.setAttribute("spjlsize", spjlList.size());
		
		//������λ
		List<HashMap<String,String>> spgwList = ShlcUtil.getSpbzBySplc(model.getSplc());
		request.setAttribute("spgwList", spgwList);
		
		//����˸�λ
		request.setAttribute("dshGwid", service.getDsgw(modelForm.getSqid()));
		return mapping.findForward("viewLcgz");
	}
	

	/**
	 * 
	 * @����:�ǼǱ�������ӡ
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2016-4-21 ����03:51:59
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		BzjlxssqService service = new BzjlxssqService();
		BzjlxssqModel model = null;
		BzjlxssqModel modelForm = null;
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				modelForm = new BzjlxssqModel();
				modelForm.setSqid(values[i]);
				model = service.getModel(modelForm);
				File file = getWord(model);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:�ǼǱ�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-11 ����10:33:55
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
	
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BzjlxssqModel modelForm = (BzjlxssqModel) form;
		BzjlxssqService service = new BzjlxssqService();
		BzjlxssqModel model = service.getModel(modelForm);
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		
		return null;
	}
	
	
	
	//���ģ����������word�ļ�
	private File getWord(BzjlxssqModel pjxmsqModel)
			throws Exception {
		BzjljgService servicePjPy = new BzjljgService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		BzjlxssqModel model = pjxmsqModel;
		HashMap<String, String> bbMap = null;// ����

		if (model != null){
			
			String dqxmdm = model.getDqxmdm();//��������Ŀ����
			
			if(StringUtils.isNull(dqxmdm)){
				throw new SystemException("��ǰ��Ŀ����Ϊ�գ����������صǼǱ�");
			}
			
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//��ѯ��Ŀ��¼
				if(xmMap != null){
					model.setXmdm(xmMap.get("xmdm"));//��Ŀ����
					BbwhService bbwhService = new BbwhService();
					bbMap = bbwhService.getDataById(xmMap.get("dybb"));//��ѯ��Ӧ��� 
				}
			}
			if(bbMap == null || bbMap.size() == 0){
				//��ѯ���������������Ϣ
				throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
			}
			
			String xh = model.getXh();
			
			//������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			BzjljgService service = new BzjljgService();
			if("10355".equals(Base.xxdm)){
				//����word����ѧԺ������ȥ��xymc�����2����
				String xymc = xsjbxx.get("xymc").substring(0,xsjbxx.get("xymc").length() -2);
				data.put("xymc", xymc);
				
				xsjbxx.put("csny",xsjbxx.get("csrq"));
				// �ֽ����֤��
				String sfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = sfzh.length(); i < j; i++) {
					data.put("sfzh" + i, String.valueOf(sfzh.charAt(i)));
				}
				data.put("xsjbxx", xsjbxx);
			}
			ZcfsService zcfService = new ZcfsService();
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));
			data.put("currY", DateUtils.getYear());//��ǰ��
			data.put("currM", DateUtils.getMonth());//��ǰ��
			data.put("currD",DateUtils.getDayOfMonth());//��ǰ��
			WjcfCfsbService wjcfcfsbService =  new WjcfCfsbService();
			List<HashMap<String , String>> yscfqk = wjcfcfsbService.getYscfqk(xh);
			BzjlxssqService pjxmsqService = new BzjlxssqService();
			//������
			List<HashMap<String, String>> shyjList = pjxmsqService.getAllShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
			//������ũ��ְҵѧԺ���Ի�
			if("12727".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				// ========== ��ѧ��ɼ���� begin ============
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "����");
				String xndypjcj = bxncjMap.get("xndypjcj");
				data.put("xndypjcj", xndypjcj);
				data.put("sxqdycj", bxncjMap.get("sxqdycj"));
				data.put("xxqdycj", bxncjMap.get("xxqdycj"));
				
				CpmdService cpmdService = new CpmdService();
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // ��ѧ��༶����
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // ��ѧ��ɼ��༶����
				
				List<HashMap<String,String>> xnjxj_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "����");
				servicePjPy.addBlankList(xnjxj_cjSxqBxList, 16 - xnjxj_cjSxqBxList.size());
				data.put("xnjxj_cjSxqBxList", xnjxj_cjSxqBxList); // ��ѧ����ѧ�ڱ��޿γɼ�
				List<HashMap<String,String>> xnjxj_cjSxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "ѡ��");
				servicePjPy.addBlankList(xnjxj_cjSxqXxList, 6 - xnjxj_cjSxqXxList.size());
				data.put("xnjxj_cjSxqXxList", xnjxj_cjSxqXxList); // ��ѧ����ѧ��ѡ�޿γɼ�
				List<HashMap<String,String>> xnjxj_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "����");
				servicePjPy.addBlankList(xnjxj_cjXxqBxList, 16 - xnjxj_cjXxqBxList.size());
				data.put("xnjxj_cjXxqBxList", xnjxj_cjXxqBxList); // ��ѧ����ѧ�ڱ��޿γɼ�
				List<HashMap<String,String>> xnjxj_cjXxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "ѡ��");
				servicePjPy.addBlankList(xnjxj_cjXxqXxList, 6 - xnjxj_cjXxqXxList.size());
				data.put("xnjxj_cjXxqXxList", xnjxj_cjXxqXxList); // ��ѧ����ѧ��ѡ�޿γɼ�
				
				List<HashMap<String,String>> xnjxj_cjSxqSum = xnjxj_cjSxqBxList;
				xnjxj_cjSxqSum.addAll(xnjxj_cjSxqXxList);
				String xnjxj_cjSxqPjf = service.getPjf(xnjxj_cjSxqSum, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("xnjxj_cjSxqPjf", xnjxj_cjSxqPjf);
				List<HashMap<String,String>> xnjxj_cjXxqSum = xnjxj_cjXxqBxList;
				xnjxj_cjXxqSum.addAll(xnjxj_cjXxqXxList);
				String xnjxj_cjXxqPjf = service.getPjf(xnjxj_cjXxqSum, 2); // ��ѧ����ѧ��ƽ���ɼ�
				data.put("xnjxj_cjXxqPjf", xnjxj_cjXxqPjf);
				String xnjxj_cjXnPjf = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_cjXnPjf = service.getPjf(new String[]{ xnjxj_cjSxqPjf, xnjxj_cjXxqPjf }, 2); // ��ѧ��ƽ���ɼ�
				}
				data.put("xnjxj_cjXnPjf", xnjxj_cjXnPjf);
				// ѧ����ѧ�������ۺϳɼ� = ѧ��ѧϰƽ���ɼ���70������ѧ���������ƽ���ɼ���30����
				BigDecimal xnjxj_cjXnPjf_BD = StringUtils.isNotNull(xnjxj_cjXnPjf) ? new BigDecimal(xnjxj_cjXnPjf) : new BigDecimal("0"); 
				BigDecimal xnjxj_dypjcj_BD = StringUtils.isNotNull(xndypjcj) ? new BigDecimal(xndypjcj) : new BigDecimal("0"); 
				String xnjxj_xsjxjpdzhcj = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_xsjxjpdzhcj = xnjxj_cjXnPjf_BD.multiply(new BigDecimal("0.7")).add(xnjxj_dypjcj_BD.multiply(new BigDecimal("0.3"))).divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP).toString();
				}
				data.put("xnjxj_xsjxjpdzhcj", xnjxj_xsjxjpdzhcj);
				// ��ѧ�����޲�����γ�
				String xnjxj_bjgXn = "blank";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_bjgXn = service.getBjgcjNum(xh, model.getXn(), "");
				}
				data.put("xnjxj_bjgXn",xnjxj_bjgXn );
				// ========== ��ѧ��ɼ���� end ============
				// ========== ����ɼ���� begin ============
				String xnTemp = model.getXn().substring(0,4);
				String diXn = String.valueOf(Integer.parseInt(xnTemp)-2) + "-" + String.valueOf(Integer.parseInt(xnTemp)-1); // ��һѧ��
				String deXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + xnTemp; // �ڶ�ѧ��
				HashMap<String,String> bxncjMap_diXn = servicePjPy.getDjbZcfListByXhXn(xh, diXn, "����");
				// ��һѧ���������ƽ���ɼ�
				String xndypjcj_diXn = "";
				if(StringUtils.isNotNull(bxncjMap_diXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_diXn.get("xxqdycj"))){
					xndypjcj_diXn = service.getPjf(new String[]{ bxncjMap_diXn.get("sxqdycj"), bxncjMap_diXn.get("xxqdycj") }, 2);
				}
				HashMap<String,String> bxncjMap_deXn = servicePjPy.getDjbZcfListByXhXn(xh, deXn, "����");
				// �ڶ�ѧ���������ƽ���ɼ�
				String xndypjcj_deXn = "";
				if(StringUtils.isNotNull(bxncjMap_deXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_deXn.get("xxqdycj"))){
					xndypjcj_deXn = service.getPjf(new String[]{ bxncjMap_deXn.get("sxqdycj"), bxncjMap_deXn.get("xxqdycj") }, 2);
				}
				// ǰ��ѧ�����������ƽ���ɼ�
				String xndypjcj_qlXn = "";
				if(StringUtils.isNotNull(xndypjcj_diXn) || 
						StringUtils.isNotNull(xndypjcj_deXn)){
					xndypjcj_qlXn = service.getPjf(new String[]{ xndypjcj_diXn, xndypjcj_deXn }, 2);
				}
				data.put("xndypjcj_diXn", xndypjcj_diXn);
				data.put("xndypjcj_deXn", xndypjcj_deXn);
				data.put("xndypjcj_qlXn", xndypjcj_qlXn);
				// ========== ����ɼ���� end ============
				// ========== ����� begin ============
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				String bxnxnjxj = ""; // ��ѧ��У�ڽ�ѧ��
				String bxngjjxj = ""; // ��ѧ����ҽ�ѧ��
				String bxngjlzjxj = ""; // ��ѧ�������־��ѧ��
				String dixnxnjxj = ""; // ��һѧ��У�ڽ�ѧ��
				String dixngjjxj = ""; // ��һѧ����ҽ�ѧ��
				String dexnxnjxj = ""; // �ڶ�ѧ��У�ڽ�ѧ��
				String dexngjjxj = ""; // �ڶ�ѧ����ҽ�ѧ��
				String dixnxn1 = ""; // ��һѧ��У��XXX
				String dixnxn2 = ""; // ��һѧ��У��XXX
				String dixnsj = ""; // ��һѧ��ʡ��XXX
				String dexnxn1 = ""; // �ڶ�ѧ��У��XXX
				String dexnxn2 = ""; // �ڶ�ѧ��У��XXX
				String dexnsj = ""; // �ڶ�ѧ��ʡ��XXX
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					String xn = pj.get("xn");
					if(xmmc.contains("��У�ڽ�ѧ��")){
						if(model.getXn().equals(xn)){
							bxnxnjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixnxnjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexnxnjxj = xmmc;
						}
					}else if(xmmc.contains("���ҽ�ѧ��")){
						if(model.getXn().equals(xn)){
							bxngjjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixngjjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexngjjxj = xmmc;
						}
					}else if(xmmc.contains("������־��ѧ��") && model.getXn().equals(xn)){
						bxngjlzjxj = xmmc;
					}else if(xmmc.startsWith("У��")){
						if(diXn.equals(xn)){
							if("".equals(dixnxn1)){
								dixnxn1 = xmmc;
							}else if(!"".equals(dixnxn1) && "".equals(dixnxn2)){
								dixnxn2 = xmmc;
							}
						}else if(deXn.equals(xn)){
							if("".equals(dexnxn1)){
								dexnxn1 = xmmc;
							}else if(!"".equals(dexnxn1) && "".equals(dexnxn2)){
								dexnxn2 = xmmc;
							}
						}
					}else if(xmmc.startsWith("ʡ��")){
						if(diXn.equals(xn)){
							dixnsj = xmmc;
						}else if(deXn.equals(xn)){
							dexnsj = xmmc;
						}
					}
				}
				data.put("bxnxnjxj", bxnxnjxj);
				data.put("bxngjjxj", bxngjjxj);
				data.put("bxngjlzjxj", bxngjlzjxj);
				data.put("dixnxnjxj", dixnxnjxj);
				data.put("dexnxnjxj", dexnxnjxj);
				data.put("dixngjjxj", dixngjjxj);
				data.put("dexngjjxj", dexngjjxj);
				data.put("dixnxn1", dixnxn1);
				data.put("dixnxn2", dixnxn2);
				data.put("dixnsj", dixnsj);
				data.put("dexnxn1", dexnxn1);
				data.put("dexnxn2", dexnxn2);
				data.put("dexnsj", dexnsj);
				
				String xnjxj_hjxjdj = model.getXmmc().contains("�Ƚ�ѧ��") ? model.getXmmc() : ""; // ��У�ڽ�ѧ���������ѧ��ȼ�
				data.put("xnjxj_hjxjdj", xnjxj_hjxjdj);
				// ========== ����� end ============
				String yxtgpsbXmmc = "";
				if("yxtgpsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "�����Ÿ�";
				}else if("yxtypsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "������Ա";
				}
				data.put("bbMapBbdm", bbMap.get("bbdm"));
				data.put("yxtgpsbXmmc", yxtgpsbXmmc);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
				//��ȡ������ɼ�����
				String bjgcjs=service.getBjgcjNum(xh, model.getXn(), "");
				// ����Υ�ʹ���
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ // ���ֹҿ�
					xsjbxx.put("ywcfgk", "��");
				}else{
					xsjbxx.put("ywcfgk", "��");
				}
				if(StringUtils.isNull(bjgcjs)){ // ��ѧ�����޲�����γ�
					xsjbxx.put("bxnywbjgkc", "��");
				}else{
					xsjbxx.put("bxnywbjgkc", "��");
				}
				DwwhService dwwhService = new DwwhService();
				List<HashMap<String , String>> bxnxsgbzwList = dwwhService.getZwForXhXn(xh, model.getXn()); // ѧ���ɲ�ְ������ʱ��
				HashMap<String , String> bxnxsgbzw1 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 0){
					bxnxsgbzw1 = bxnxsgbzwList.get(0);
				}
				HashMap<String , String> bxnxsgbzw2 = new HashMap<String , String>();
				if(bxnxsgbzwList.size() > 1){
					bxnxsgbzw2 = bxnxsgbzwList.get(1);
				}
				data.put("bxnxsgbzw1", bxnxsgbzw1);
				data.put("bxnxsgbzw2", bxnxsgbzw2);
				
				// ��ͥ���
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkdcForm = new JtqkdcForm();
				jtqkdcForm.setXh(xh);
				JtqkdcForm jtqkmodel_12727 = jtqkService.getModel(jtqkdcForm); 
				if (jtqkmodel_12727 == null) {
					jtqkmodel_12727 = new JtqkdcForm();
				}
				data.put("jtqkmodel_12727", jtqkmodel_12727);
				String jthk = jtqkmodel_12727.getJthk();
				boolean jthkCzFlag = false;
				if(jthk != null && jthk.contains("����")){
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// ��ͥ��Ա
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
			}
			//�㽭����ְҵѧԺ
			if("12869".equals(Base.xxdm)) {
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				/*��ӡ����*/
				data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("�й���������Ա")) {
					data.put("zzmmmc", "�й���Ա");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
				List<HashMap<String, String>> pjjg =  service.getPjpyInfoMapForDjb(xh);
				data.put("pjjg", pjjg);
			}
			//���������ѧ
			if("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// ��������
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
				// ��ͥ��Ա
				JtqkdcService jtqkService = new JtqkdcService();
				List<HashMap<String, String>> jtcyxxList_zymzdx = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_zymzdx, 5 - jtcyxxList_zymzdx.size());
				data.put("jtcyxxList_zymzdx", jtcyxxList_zymzdx);
				// ��Ŀѧ��ѧ�ڱ��޿γɼ�
				List<HashMap<String,String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "����");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList); 
				String xmXnxqBxCjPjf = service.getPjf(xmXnxqBxCjList, 2); // ƽ���ɼ�
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				//========== ��ȡ��һѧ��ɼ������������������޿μ������� begin ============
				String xnTemp = Base.currXn.substring(0,4);
				String beforXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + String.valueOf(Integer.parseInt(xnTemp)); // ��ѧ��
					//���޿μ�����
				CpmdService cpmdService = new CpmdService();
				String bjrsForZymzdx = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(beforXn, xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(beforXn, xh);
				String bxkjgs = zcfService.getBxkjgs(beforXn, xh);
				data.put("bjrsForZymzdx", bjrsForZymzdx);// �༶����
				data.put("cjpmForZymzdx", cjpm);// �ܳɼ�����
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// ��������
				data.put("cpzrsForZymzdx", cpzrs);
				//========== ��ȡ��һѧ��ɼ������������������޿μ������� end ============
				
				// ���Ž���
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if(StringUtils.isNotNull(zzmmmc)){
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if((zzmmmc.contains("��Ա") && "02".equals(jddm))
							|| (zzmmmc.contains("Ԥ����Ա") && "09".equals(jddm))
							|| (zzmmmc.contains("��Ա") && !zzmmmc.contains("Ԥ����Ա") && "11".equals(jddm))){
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj,FomartDateType.day));
				// �ѻ���
				StringBuffer yhjxBuffer = new StringBuffer();
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("��");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 5 - pjjgList.size());
				data.put("pjjg_10052", pjjgList);
				int size1=(5 - pjjgList.size())<0?0:(5 - pjjgList.size());
				data.put("blank_10052", getBlankList(size1));
				HashMap<String, String> dkfs = pjjgService.getCjfsList(model.getXh(),model.getXn());
				data.put("dkzgf", dkfs.get("max"));
				data.put("dkzdf", dkfs.get("min"));
				data.put("pjcjjdfs", dkfs.get("pjcjjdfs"));
				List<HashMap<String, String>> hjqkList = service.getHjqk(model.getXh(),model.getXn());
				data.put("hjqkList",hjqkList);
				KnsjgService knsjgService = new KnsjgService();
				HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
				data.put("rddcmc", knsjg.get("dcmc"));//�϶�����
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("Ʒ��")){
						data.put("pdfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("ѧҵ")){
						data.put("xyfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("����")){
						data.put("wtfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("�۲�")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
					if(xmmc.contains("ƽ���ɼ�")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
					}
				}
				/*��ȡ��һ��������*/
				data.put("shyj1", new CommShlcImpl().getShyjListByYwid(model.getSqid()).get(0).get("shyj"));
				data.put("shyj2", new CommShlcImpl().getShyjListByYwid(model.getSqid()).get(1).get("shyj"));
				data.put("shyj3", new CommShlcImpl().getShyjListByYwid(model.getSqid()).get(2).get("shyj"));
			}
			
			//����ʦ��ѧԺ���Ի�
			if("10647".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // �������
				data.put("njzwmc", xsjbxx.get("nj").substring(2, 4) + "��"); // �������
				// ============ �������� begin==========
				String nl = "";
				//1994-12-24 19920217 ����ʽ
				String csrq = xsjbxx.get("csrq");//��ȡ��������
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				// ============ �������� end==========
				// ========== ���У�������ҵ�������ƺŵ�ʱ�� begin ============
				StringBuffer xjyxbysHjsjBuffer = new StringBuffer();
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(xmmc.contains("У�������ҵ��")){
						xjyxbysHjsjBuffer.append(pj.get("sqsjs")).append("��");
					}
				}
				String xjyxbysHjsj = xjyxbysHjsjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("xjyxbysHjsj", xjyxbysHjsj);
				// ========== ���У�������ҵ�������ƺŵ�ʱ�� end ============
				// ========== ��������Ի� begin ============
				List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				// ========== ��������Ի� end ============
			}
			//��������ְҵѧԺ
			if("13957".equals(Base.xxdm)) {
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
			}
			//Ϋ��ѧԺ���Ի�
			if("11067".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxny = xsjbxx.get("rxny") == null ? "" : xsjbxx.get("rxny");
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				xsjbxx.put("rxny_num", rxny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				// �ֽ����֤��begin
				String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
//				// ===============������������ begin===============
//				// ===========ɽ��ʡ��ʦ���������ҵ�������ɽ��ʡʦ���������ҵ������� begin==========
//				String sqly_11067_1 = sqly;
//				String sqly_11067_2 = "";
//				String bbdm = bbMap.get("bbdm");
//				int sqly_11067_max = 0;
//				if("sdsfsflyxbys_11067".equals(bbdm)){
//					sqly_11067_max = 224;
//				}else if("sdssflyxbys_11067".equals(bbdm)){
//					sqly_11067_max = 646;
//				}
//				if(sqly_11067_1.length() > sqly_11067_max){
//					sqly_11067_1 = sqly.substring(0, sqly_11067_max);
//					sqly_11067_2 = sqly.substring(sqly_11067_max);
//				}
//				data.put("sqly_11067_1", sqly_11067_1);
//				data.put("sqly_11067_2", sqly_11067_2);
//				// ===========ɽ��ʡ��ʦ���������ҵ�������ɽ��ʡʦ���������ҵ������� end==========
//				// ===========ɽ��ʡ�ߵ�ѧУ����ѧ���ǼǱ�ɽ��ʡ�ߵ�ѧУ����ѧ���ɲ��ǼǱ� begin==========
//				HashMap<String, String> sqly_11067_map = new HashMap<String, String>();
//				if("sdsgdxxyxxsgb_11067".equals(bbdm) || "sdsgdxxyxxs_11067".equals(bbdm)){
//					int rows = 12; // ����
//					int words = 35; // ÿ���������
//					sqly_11067_max = 420;
//					String sqly_11067_d2y = ""; // �ڶ�ҳ
//					if(sqly.length() > sqly_11067_max){
//						sqly_11067_d2y = sqly.substring(sqly_11067_max); 
//					}
//					sqly_11067_map.put("sqly_11067_d2y", sqly_11067_d2y);
//					String temp = sqly;
//					for (int i = 1; i <= rows; i++) {
//						if(temp.length() > words){
//							sqly_11067_map.put("sqly_11067_" + i, temp.substring(0, words));
//							temp = temp.substring(words);
//						}else{
//							sqly_11067_map.put("sqly_11067_" + i, temp);
//							temp = "";
//						}
//					}
//				}
//				data.put("sqly_11067_map", sqly_11067_map);
//				// ===========ɽ��ʡ�ߵ�ѧУ����ѧ���ǼǱ�ɽ��ʡ�ߵ�ѧУ����ѧ���ɲ��ǼǱ� end==========
//				// ===============������������ end===============
				// ͨ��ѧ�Ų�ѯ�۲����
				List<HashMap<String,String>> zcfNList = zcfService.getZcfsNList(xh);
				data.put("zcfNList_11067", zcfNList);
			}
			
			// ����ְ��
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			
			
			//ѧ����Ƭ
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);

			
			//����ʦ����ѧ
			if("10718".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����

				// �ֽ����֤��begin
				String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
			}

			
			//�ɶ�����ѧԺ���Ի�
			if("10653".equals(Base.xxdm)){
				String nl = "";
				//��������
				//1994-12-24 19920217 ����ʽ
				String csrq = xsjbxx.get("csrq");//��ȡ��������
				
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				String[] code = new String[]{"01","02","03"}; //����Ա���� �й�������01 Ԥ��02 ��Ա03
				//�ж��Ƿ��ǵ���Ա
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&(ArrayUtil.contains(code, xsjbxx.get("zzmm")))){
					data.put("sfsdty", "��");
				}else{
					data.put("sfsdty", "��");
				}
				
				//��ȡͬ�꼶ͬרҵ����
				String zyrs = servicePjPy.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				
				String[] shyj = new String[]{"","","",""};
				for (int i = 0; i < shyjList.size(); i++) {
					shyj[i] = shyjList.get(i).get("shyj");
				}
				data.put("shyj1", shyj[0]);
				data.put("shyj2", shyj[1]);
				data.put("shyj3", shyj[2]);
				data.put("shyj4", shyj[3]);
				
				//��ͥ��Ա��Ϣ
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList_10653 = xsxxglService.getJtcyxxXsList(xh);
				
				data.put("jtcyxxList_10653", jtcyxxList_10653);
				int size=(3 - jtcyxxList_10653.size())<0?0:(3 - jtcyxxList_10653.size());
				data.put("blankList_10653", getBlankList(size));
				
				//�������϶���������
				KnsjgService knsjgService = new KnsjgService();
				HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
				data.put("dcmc", knsjg.get("dcmc")==null?"":knsjg.get("dcmc"));
				
			}
			//����ҽ�ƴ�ѧ���Ի����ű���
			if("10598".equals(Base.xxdm)){
				if("�������ء�����ѧ���ɲ���ѧ��".equals(model.getXmmc()) 
						|| "�˽��ѧ��".equals(model.getXmmc())
						|| "�ٺͽ�����ѧ��".equals(model.getXmmc())
						|| "����ѧ��".equals(model.getXmmc()) 
						|| "����ѧ���ɲ�".equals(model.getXmmc())
				){
					//��Ŀ��������
					PjdmModel pjdmModel = new PjdmModel();
					pjdmModel.setXmlxdm(model.getLxdm());
					PjdmService pjdmService = new PjdmService();
					PjdmModel xmlxModel = pjdmService.getModel(pjdmModel);
					model.setLxdmmc(xmlxModel.getXmlxmc());
				}
			}
			//����ʹ��
			//xh="3060601025";
			HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
			
			//����ѧ�ż���ѧ��ѧ��ɼ�
			List<HashMap<String,String>> cjList = xsxxService.getCjList(xh,model.getXn(),model.getXq());
			//��ȡƽ������
			String pjfs=servicePjPy.getAverage(cjList);
			xsjbxx.put("pjfs",pjfs );
			
			//��ȡ������ɼ�����
			String bjgcjs=servicePjPy.getBjgcjNum(xh, model.getXn(), model.getXq());
			xsjbxx.put("bjgcjs",bjgcjs );
			
			//�㽭��ѧ������ѧԺ���Ի�
			if("13022".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				// ���޲�����γ�
				if(StringUtils.isNull(bjgcjs)){
					xsjbxx.put("ywbjgmc", "��");
				}else{
					xsjbxx.put("ywbjgmc", "��");
				}
				// ����Υ�ʹ���
				
				if(yscfqk==null || yscfqk.size() == 0){
					xsjbxx.put("ywwjcfmc", "��");
				}else{
					xsjbxx.put("ywwjcfmc", "��");
				}
				// ��������Ŀ
				StringBuffer ysqxmBuffer = new StringBuffer();
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					// ƴ����������Ŀ
					if(StringUtils.isNotNull(xmmc)){
						ysqxmBuffer.append(xmmc).append(" ");
					}
				}
				data.put("ysqxmmc", ysqxmBuffer.toString());
				
				// ============ �����������Ŀ�����ж��Ƿ�ѡ�� begin ============
				String sqxmmc = model.getXmmc();
				// ��ѧ�� ģ���Ƿ�ѡ���ж�
				if(sqxmmc.equals("һ�Ƚ�ѧ��")){
					data.put("ydjxjflag", "true");
				}else if(sqxmmc.equals("���Ƚ�ѧ��")){
					data.put("edjxjflag", "true");
				}else if(sqxmmc.equals("���Ƚ�ѧ��")){
					data.put("sdjxjflag", "true");
				}else if(sqxmmc.equals("ѧϰ���㽱ѧ��")){
					data.put("xxyxjxjflag", "true");
				}else if(sqxmmc.equals("��Ṥ�����㽱ѧ��")){
					data.put("shgzyxjxjflag", "true");
				}else if(sqxmmc.equals("������ѧ��")){
					data.put("xthdjxjflag", "true");
				}
				// �����ƺ� ģ���Ƿ�ѡ���ж�
				if(sqxmmc.equals("����ѧ��")){
					data.put("shxsrychflag", "true");
				}else if(sqxmmc.equals("����ѧ���ɲ�")){
					data.put("yxxsgbrychflag", "true");
				}
				// ============ �����������Ŀ�����ж��Ƿ�ѡ�� end ============
				
				// �ɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.equals("ѧҵˮƽ")){
						xsjbxx.put("xyspnjzypmdy", zcfMap.get("njzypm"));
					}else if(xmmc.equals("˼��Ʒ��")){
						xsjbxx.put("sxpdbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("�ۺ�����")){
						xsjbxx.put("zhnlbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("��������")){
						xsjbxx.put("stszfsdy", zcfMap.get("fs"));
					}
				}
				String zyrs = servicePjPy.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
			}
			
			//������ҵ��ѧ���Ի�
			if("10022".equals(Base.xxdm)){
				//��ȡ��ӡ����
				XsxxglService xsxxglService = new XsxxglService();
				String dyrq = xsxxglService.getDqrq(xh);
				data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				
				// ��������Ŀ
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				// ��ǰ��Ŀ��ѧ��ѧ��
				String xnDqxm = model.getXn();
				if(xnDqxm != null){
					xnDqxm = xnDqxm.trim();
				}
				String xqmcDqxm = (model.getXqmc() == null) ? "" : model.getXqmc();
				if(xqmcDqxm != null){
					xqmcDqxm = xqmcDqxm.trim();
				}
				String xnXqmcDqxm = xnDqxm + xqmcDqxm;
				List<HashMap<String, String>> pjzqPjjgList = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xnTemp = pj.get("xn");
					String xqmcTemp = pj.get("xqmc");
					if(xnTemp != null){
						xnTemp = xnTemp.trim();
					}
					if(xqmcTemp != null){
						xqmcTemp = xqmcTemp.trim();
					}
					// ��ǰ��Ŀ��ѧ��ѧ���£�ѧ��������
					if(xnXqmcDqxm.equals(xnTemp + xqmcTemp)){
						pjzqPjjgList.add(pj);
					}
				}
				// ��ǰ��Ŀ��ѧ��ѧ���£�ѧ��������
				String xnXqmcDqxn = xnDqxm;
				List<HashMap<String, String>> XnPjjgList = new ArrayList<HashMap<String, String>>();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xnTemp = pj.get("xn");
					if(xnTemp != null){
						xnTemp = xnTemp.trim();
					}
					if(xnXqmcDqxn.equals(xnTemp)){
						XnPjjgList.add(pj);
					}
				}
				data.put("XnPjjgList", XnPjjgList);
				data.put("XnPjjgListSize", XnPjjgList.size());
				data.put("pjzqPjjgList", pjzqPjjgList);
				data.put("pjzqPjjgListSize", pjzqPjjgList.size());
			}
			//������·ְҵ����ѧԺ���Ի�
			if("13943".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				servicePjPy.addBlankList(pjjg, 1 - pjjg.size());
				data.put("pjjg", pjjg);
			}
			
			//�й�����ѧԺ���Ի�
			if("10355".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjgL4 =  pjjgService.getHjxxList(xh,model.getXn(),model.getXq(),"4");
				data.put("pjjgLine1", pjjgL4.get(0));
				data.put("pjjgLine2", pjjgL4.get(1));
				data.put("pjjgLine3", pjjgL4.get(2));
				data.put("pjjgLine4", pjjgL4.get(3));
				data.put("pjjgL4", pjjgL4);
				data.put("xxdm", Base.xxdm);
				data.put("ywwj", yscfqk.size()>0);
				String xmmc = model.getXmmc();
				String xmdm = model.getXmdm();
				//��ȡѧ��ѧϰ���
				CpmdService cpmdService = new CpmdService();
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "����");
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // ��ѧ��ɼ��༶����
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // ��ѧ��༶����
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// ��������
				data.put("cpzrsForZymy", cpzrs);
				//���޿μ�����
				String bjrsForZymy = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bxksForZymy", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgsForZymy", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����
				data.put("cjpmForZymy", cjpm);// �ܳɼ�����
				data.put("bjrsForZymy", "".equals(cjpm) ? "" : bjrsForZymy);// �༶����
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String zCxmmc = zcfMap.get("xmmc").trim();
					if(zCxmmc.contains("�۲�")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
				}
				String cprs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// ��������
				data.put("cpzrsForZymy", cprs);
				
				
				//��ȡ��������
				String jxmc = pjxmsqModel.getXmmc();
				data.put("jxmc",jxmc);
				//�жϽ��������Ƿ��������ѧ���ֶ�
				if(jxmc.indexOf("����ѧ��")!= -1){
					data.put("isShxs","1");
				} else {
					data.put("isShxs","0");
				}
				// ����Υ�ʹ���
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "��");
				}else{
					data.put("ywcfmc", "��");
				}
				//��������
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				String bkks = xxcjMap.get("bkcjnum");
				//�����������
				String bkjg = xxcjMap.get("xscjstr");
				data.put("bkjg", bkjg);
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "��");
				}else {
					data.put("sfbk", "��");
				}
				
				//�й���Ժ ѧ�����β���Υ����Ϣ
				HashMap<String,String> kkbkxxMap = servicePjPy.getKkbkxx(xh);
				data.put("kkbkxx", kkbkxxMap);
				
				//���ܲ��Գɼ�
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("���ܲ���",model.getXn(),model.getXq(),xh).get("fs"));
				
				//ʱ�����
				HashMap<String,String> sqsj = new HashMap<String, String>();
				HashMap<String,String> shsjfdy = new HashMap<String, String>();//����Ա
				HashMap<String,String> shsjyx = new HashMap<String, String>();//Ժϵ
				HashMap<String,String> shsjxx = new HashMap<String, String>();//ѧУ
				shsjxx = pjjgService.getHjshsjList(xmdm, "0");
				if(xmmc.indexOf("���ҽ�ѧ��") != -1 || xmmc.indexOf("ʡ����") != -1 ){
					sqsj = pjjgService.getHjshsjList(xmdm, "13");
					shsjfdy = pjjgService.getHjshsjList(xmdm,"11");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
					
				}else if(xmmc.indexOf("������־��ѧ��") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "9");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else if(xmmc.indexOf("������ѧ��") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "12");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else {
					
				}
				data.put("sqsjs", sqsj);
				data.put("shsjfdy", shsjfdy);
				data.put("shsjyx", shsjyx);
				data.put("shsjxx", shsjxx);
			}

			//��̶��ѧ
			if("10530".equals(Base.xxdm)){
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> pjjgL4 =  xsxxglService.getXsxxHjqkNewList(xh,4);
				data.put("pjjgL4", pjjgL4);

				String zzmmmc = xsjbxx.get("zzmmmc");

				if(zzmmmc!=null){
					if (zzmmmc.equals("�й���������Ա")) {
						xsjbxx.put("zzmmmc", "�й���Ա");
					}else if(zzmmmc.equals("�й�����������������Ա")){
						xsjbxx.put("zzmmmc", "������Ա");
					}
				}
			}
			
			//����ʦ����ѧ���Ի�
			if("10602".equals(Base.xxdm)){
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				data.put("pjjg", pjjg);
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
			}
			
			//�㽭��ý
			if("11647".equals(Base.xxdm)) {
				HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> pm = service.getPm(model.getXh(),model.getXn());
				
				data.put("bxk", bxk);
				data.put("pm", pm);
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				data.put("beforXn", model.getXn());
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
			}
			//�ൺ����ѧԺ
			if("10868".equals(Base.xxdm)){
				/*ȡ��ѧ���ۺϳɼ���*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*ȡѧ���ۺϳɼ�����*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				/*����ѧ��ȡ��ǰ�������ڵĲ����༶����*/
				String xsszcpbjRs = service.getXsszcpbjRsForxh(model.getXh(),model.getXn());
				data.put("xsszcpbjRs", xsszcpbjRs);
			}
			//�������ϴ�ѧ���Ի�
			if("11417".equals(Base.xxdm)){
				// ���ñ������ϴ�ѧ����
				data.put("bjlhdxbt", model.getXmmc());
				
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // �������
				// ��������Ŀ
				StringBuffer ysqxmBuffer = new StringBuffer();
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					if(StringUtils.isNotNull(pj.get("xmmc"))){
						ysqxmBuffer.append(pj.get("xmmc")).append(" ");
					}
				}
				xsjbxx.put("ysqxmmc", ysqxmBuffer.toString());
				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("ѧҵ")){
						xsjbxx.put("xycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("����")){
						xsjbxx.put("wtcjfs", zcfMap.get("fs"));
					}
				}
			}
			//������ְҵ����ѧУ
			if("90211".equals(Base.xxdm)) {
				
				data.put("xmmc", model.getXmmc());// ��Ŀ����
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ""));// 1988.9
				//�ж��Ƿ��ǵ���Ա
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&( "03".equals(xsjbxx.get("zzmm")))){
					data.put("sfty", "��");
				}else{
					data.put("sfty", "��");
				}
				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("��ѧ")){
						xsjbxx.put("jxcjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("����")){
						xsjbxx.put("tycjfs", zcfMap.get("fs"));
					}
				}		
				
			}
			
			//�㽭����ְҵѧԺ���Ի�
			if("12867".equals(Base.xxdm)){
				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						xsjbxx.put("zyfbjpm", zcfMap.get("bjpm"));
					}
				}
				// ��߷֡���ͷ֡�ƽ���֡�����������ѧϰ�ɼ�
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				if(xxcjMap == null){
					xxcjMap = new HashMap<String,String>();
				}
				data.put("xxcjMap", xxcjMap);
				//�㽭���ι��ҽ�ѧ��
				//����ȡֵ
				BzjljgService pjjgService = new BzjljgService();
				//���ҽ�ѧ�� ȡ����������
				HashMap<String, String> pjjg12867 =  pjjgService.getZjlyByXhMap(xh,model.getXn());
				data.put("pjjg12867", pjjg12867);
				//�ۺ���������
				HashMap<String, String> rspm = pjjgService.getZjlyByPm(xh,model.getXn());
				data.put("rspm", rspm);	
				//�㽭������־��ѧ���л�ȡ�ֶΣ���ͥ�����
				HashMap<String,String> lzjxj = pjjgService.getZjlylzByXhMap(xh, model.getXn());
				data.put("lzjxj",lzjxj);	
				//�㽭����ѧϰ�ɼ�������������ĳɼ���������У��ѧ��
				HashMap<String,String> zjlyxxcj = pjjgService.getZjlyXxqkCj(xh, model.getXn());
				data.put("zjlyxxcj",zjlyxxcj);
				//�㽭ʡ������ѧ���㽭����
				HashMap<String, String> zjszf12867 =  pjjgService.getZjszfByXhMap(xh,model.getXn());
				data.put("zjszf12867", zjszf12867);
				//�㽭����ְҵѧԺ��ѧ��
				HashMap<String, String> zjlyzyxy12867 =  pjjgService.getZjlyzyxyfByXhMap(xh,model.getXn());
				data.put("zjlyzyxy12867", zjlyzyxy12867);
				//�㽭����ʡ�������ҵ��
				HashMap<String, String> zjlysjyxbys12867 =  pjjgService.getZjlySjyxbys(xh,model.getXn());
				data.put("zjlysjyxbys12867", zjlysjyxbys12867);	
				//�㽭ʡ����ѧԺ�����ҵ��
				HashMap<String, String> zjlyxyyxbys12867 =  pjjgService.getZjlyxyyxbys(xh,model.getXn());
				data.put("zjlyxyyxbys12867", zjlyxyyxbys12867);
			}
			
			//�Ϻ����ѧԺ���Ի�
			if("11458".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				// ��ǰ��Ŀ��ѧ��
				data.put("xn_11458", model.getXn().replace("-", "/"));
			}
			//������Ͽҽҩ
			if("14008".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// ��������
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("�й���������Ա")) {
					data.put("zzmmmc", "�й���Ա");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
			}
			
			//����ũҵ��ѧ
			if("10504".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				//����ȡֵ
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg10504 =  pjjgService.getHznydxPjpyMap(xh);
				data.put("pjjg10504", pjjg10504);
				int size1=(4 - pjjg10504.size())<0?0:(4 - pjjg10504.size());
				data.put("blankList1", getBlankList(size1));
			}
			
			if("10277".equals(Base.xxdm)){
				data.put("jd",new BzjlxssqService().getJd_10277(xh,model.getXn()));
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 4 - pjjgList.size());
				data.put("pjjgList", pjjgList);
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("���ز�����")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			//�㽭��ҽҩ��ѧ
			if("10344".equals(Base.xxdm)){
				data.put("xsjbxx", xsjbxx);
				//������Ŀͬѧ���ý�ѧ�����ƣ��ȼ���
				BzjljgService pjjgService = new BzjljgService();
				String jxjdj = pjjgService.getJxjmcByXhXn(xh,model.getXn());
				data.put("jxjdj",jxjdj);
				// ����������
				CpmdService cpmdService = new CpmdService();
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());
				data.put("cpzrs", cpzrs);
				//��ȡ�������
				List<HashMap<String, String>> pjjgL4 =  pjjgService.getHjxxList(xh,model.getXn(),model.getXq(),"4");
				data.put("pjjgLine1", pjjgL4.get(0));
				data.put("pjjgLine2", pjjgL4.get(1));
				data.put("pjjgLine3", pjjgL4.get(2));
				data.put("pjjgLine4", pjjgL4.get(3));
				data.put("pjjgL4", pjjgL4);
				//��ȡ�ɼ�������
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				String bjrsForZjzyydx = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrsForZjzyydx", "".equals(cjpm) ? "" : bjrsForZjzyydx);// �༶����
				data.put("cjpm", cjpm);// �ܳɼ�����
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// ���޿���
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// ���޿μ�����
				//ʱ�����
				HashMap<String,String> sqsj = new HashMap<String, String>();
				HashMap<String,String> shsjfdy = new HashMap<String, String>();//����Ա
				HashMap<String,String> shsjyx = new HashMap<String, String>();//Ժϵ
				HashMap<String,String> shsjxx = new HashMap<String, String>();//ѧУ
				shsjxx = pjjgService.getHjshsjList(model.getXmdm(), "0");
				if(model.getXmdm().indexOf("���ҽ�ѧ��") != -1 || model.getXmdm().indexOf("ʡ����") != -1 ){
					sqsj = pjjgService.getHjshsjList(model.getXmdm(), "13");
					shsjfdy = pjjgService.getHjshsjList(model.getXmdm(),"11");
					shsjyx = pjjgService.getHjshsjList(model.getXmdm(), "7");
					
				}else if(model.getXmdm().indexOf("������־��ѧ��") != -1){
					sqsj = pjjgService.getHjshsjList(model.getXmdm(), "9");
					shsjyx = pjjgService.getHjshsjList(model.getXmdm(), "7");
				}else if(model.getXmdm().indexOf("������ѧ��") != -1){
					sqsj = pjjgService.getHjshsjList(model.getXmdm(), "12");
					shsjyx = pjjgService.getHjshsjList(model.getXmdm(), "7");
				}else {
					
				}
				data.put("sqsjs", sqsj);
				data.put("shsjfdy", shsjfdy);
				data.put("shsjyx", shsjyx);
				data.put("shsjxx", shsjxx);
			}
			//�����Ƽ���ѧ
			if("10704".equals(Base.xxdm)){
				//data.put("jd",new PjxmsqService().getJd_10277(xh,model.getXn()));
				//������Ҫ�ǹ�����ѧ�𣬻�ȡ��ѧ�ڼ�������װ��list��
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 4 - pjjgList.size());
				data.put("pjjgList", pjjgList);
				int size1=(4 - pjjgList.size())<0?0:(4 - pjjgList.size());
				data.put("blankList1", getBlankList(size1));
				//���޿�~��·��רҵ�ɼ���רҵ����
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				//��ѧ������
				List<HashMap<String, String>> hjqkList = pjjgService.getHjqk(model.getXh(),model.getXn());
				data.put("hjqkList",hjqkList);
			    //��һ�ַ����ı��޿κͳɼ�����
			    HashMap<String, String> bxk = pjjgService.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> cjpm = pjjgService.getCjPm(model.getXh(),model.getXn());
				//HashMap<String, String> pm = service.getPm(model.getXh(),model.getXn());
				//data.put("pm", pm);
				data.put("bxk", bxk);
				data.put("cjpm", cjpm);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("���ز�����")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			
			//�㽭����ѧԺ
			if("10876".equals(Base.xxdm)){
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("sfwj", "��");
				}else{
					data.put("sfwj", "��");
				}
				//�����ɼ�
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("������",model.getXn(),model.getXq(),xh).get("fs"));
				//־Ը�߷���Сʱ��
				data.put("zyfwxs",zcfService.getZcfsByXmXnXqXh("־Ը����Сʱ",model.getXn(),model.getXq(),xh).get("fs"));
				//��Ϊ�淶�ɼ��������֣�
				data.put("dyf",zcfService.getZcfsByXmXnXqXh("������",model.getXn(),model.getXq(),xh).get("fs"));
				//������
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			if("11799".equals(Base.xxdm)){
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("zyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("����")){
						data.put("dycjfs", zcfMap.get("fs"));
						data.put("dyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("ƽ���ɼ�")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
					}
					
				}
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ 
					data.put("ywcfgk", "��");
				}else{
					data.put("ywcfgk", "��");
				}
				if(StringUtils.isNull(bjgcjs)){ // ��ѧ�����޲�����γ�
					data.put("bxnywbjgkc", "��");
				}else{
					data.put("bxnywbjgkc", "��");
				}
				
				
				List<HashMap<String, String>> cjList1 = service.getCjsxqList(model.getXn(),model.getXh());
				/**
				 * �ɼ�����д������γ̰���ɼ� ��һѧ��
				 */
				HashMap<String,String> cjMap1 = new HashMap<String, String>();
				for (int i = 0; i < 10; i++) {
					cjMap1.put("kcmc"+i,"");
					cjMap1.put("cj"+i,"");
				}
				for (int i = 0;  i< cjList1.size(); i++) {
					data.put("kcmc"+i,cjList1.get(i).get("kcmc"));
					data.put("cj"+i,cjList1.get(i).get("cj"));
				}
				List<HashMap<String, String>> cjList2 = service.getCjxsqList(model.getXn(),model.getXh());
				/**
				  �ɼ�����д������γ̰���ɼ� �ڶ�ѧ��
				 */
				HashMap<String,String> cjMap2 = new HashMap<String, String>();
				for (int i = 0; i < 10; i++) {
					cjMap2.put("xsqkcmc"+i,"");
					cjMap2.put("xsqcj"+i,"");
				}
				for (int i = 0;  i< cjList2.size(); i++) {
					data.put("xsqkcmc"+i,cjList2.get(i).get("kcmc"));
					data.put("xsqcj"+i,cjList2.get(i).get("cj"));
				}
			}
			//����ҽҩ�ߵ�
			if("70002".equals(Base.xxdm)){
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("����")){
						data.put("dyxf", zcfMap.get("fs"));
					}
					if(xmmc.contains("�۲��ܷ�")){
						data.put("zhpm", zcfMap.get("bjpm"));
					}
				}
				data.put("djjl", HtmlUtil.xmlZy(model.getDjjl()));
				data.putAll(pjxmsqService.getMaxOrMinWfkCj(xh, model.getXn(),model.getXq()));
				//������
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			//�������ڣ��������ƣ��佱��λ�õ�̫�࣬������ͨ��
			BzjljgService pjjgService = new BzjljgService();
			List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
			servicePjPy.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
			data.put("pjjgListhjqk", pjjgListhjqk);
			int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
			data.put("blankListhjqk", getBlankList(size1));
			String[] xnArr = model.getXn().split("-");
			if(xsjbxx.get("csrq")!=null){
				String[] csArr = xsjbxx.get("csrq").split("\\D");
				if(csArr != null&&csArr.length == 3){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
					data.put("csr1", csArr[2]);
				}else if (csArr != null&&csArr.length ==2){
					data.put("csn1", csArr[0]);
					data.put("csy1", csArr[1]);
				}else if (csArr != null&&csArr.length ==1){
					data.put("csn1", csArr[0]);
				}
				if(xnArr.length == 2){
					data.put("qsxn", xnArr[0]);
					data.put("zhxn", xnArr[1]);
				}else if (xnArr.length == 1){
					data.put("qsxn", xnArr[0]);
				}
				if(csArr != null&&csArr.length == 2){
					data.put("csn", csArr[0]);
					data.put("csy", csArr[1]);
				}else if (csArr != null&&csArr.length == 1){
					data.put("csn", csArr[0]);
				}
			}

			//ѧ��ƴ�ӣ����
			int uqsnx = Integer.parseInt(xnArr[0])-1;//such as:2016->2015
			int uzhxn = Integer.parseInt(xnArr[1])-1;//such as:2017->2016
			StringBuilder usxn = new StringBuilder();
			String upsxn1 = usxn.append(uqsnx+"-"+uzhxn).toString();//such as:2016-2017 ->2015-2016
			String sxnhlw = xnArr[0].substring(xnArr[0].lastIndexOf("/")+3, xnArr[0].lastIndexOf("/")+5);//such as:2016->16
			String zxnhlw = xnArr[1].substring(xnArr[1].lastIndexOf("/")+3, xnArr[1].lastIndexOf("/")+5);//such as:2017->17
			data.put("sxnhlw", sxnhlw);
			data.put("zxnhlw", zxnhlw);
			int ssxnhlw = Integer.parseInt(sxnhlw)-1;//such as:16->15
			int zxxnhlw = Integer.parseInt(zxnhlw)-1;//such as:17->16
			data.put("upqsxn", ssxnhlw);
			data.put("upzhxn", zxxnhlw);
			//ѧ��д��ȡ02 ��01 
			String sxnxq = "02";
			String zxnxq = "01";
			int pjpm =0;
			List<HashMap<String,String>> zcfList2 = zcfService.getZcfListByXh(xh, upsxn1, sxnxq);
			for (HashMap<String, String> zcfMap : zcfList2) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("����")){
					data.put("dyfs02_70002", zcfMap.get("fs"));
				}
				if(xmmc.contains("�۲��ܷ�")){
					data.put("zhpm_70002", zcfMap.get("bjpm"));
					if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
						pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
					}
				}
			}
			List<HashMap<String,String>> zcfList3 = zcfService.getZcfListByXh(xh, model.getXn(), zxnxq);
			for (HashMap<String, String> zcfMap : zcfList3) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("����")){
					data.put("dyfs01_70002", zcfMap.get("fs"));
				}
				if(xmmc.contains("�۲��ܷ�")){
					data.put("pjcjjdpm", zcfMap.get("bjpm"));
					if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
						pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
					}
				}
			}
			data.put("pjpm_70002", pjpm/2);
			
			// ����ѧ����ͥ������Ϣ
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			// �༶����
			CpmdService cpmdService = new CpmdService();
			String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
			xsjbxx.put("bjrs", bjrs);		

			//���ؼ�ͥ��Ա��Ϣ
		
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
			
			data.put("jtcyxxList", jtcyxxList);
			int size=(5 - jtcyxxList.size())<0?0:(5 - jtcyxxList.size());
			data.put("blankList", getBlankList(size));
			
			data.put("jtqk", jtqkmodel);//��ͥ���
			
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
			String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
			xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", "")); // 2016.12
			xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// ��ѧ����
			
			// �ֽ����֤��begin
			String xssfzh =  xsjbxx.get("sfzh");
			if(!StringUtil.isNull(xssfzh)){
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
			}else {
				xsjbxx.put("sfzh7","");
			}

			
			//�������϶�����
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//�϶�����
			
			// ����������list
			KnsdcService knsdcService = new KnsdcService();
			data.put("knsdcList", knsdcService.getKnsdcList());
			
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getSqid());
			data.put("yjshyj", spxxInfo.get("yjshyj"));
			data.put("ejshyj", spxxInfo.get("ejshyj"));
			data.put("sjshyj", spxxInfo.get("sjshyj"));
			
			//��ʽ������
			cjList=servicePjPy.formatForDjb(cjList);
			List<HashMap<String, String>> pjjg =  service.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgAll =  service.getPjpyInfoList(xh);
			data.put("pjjgAll",pjjgAll);
			data.put("pjjg", pjjg);
			data.put("xmmc", model.getXmmc());// ��Ŀ����
			data.put("currXn", model.getXn());
			data.put("cjList", cjList);
			model.setSqly(HtmlUtil.xmlZy(model.getSqly()));
			data.put("xmxx", model);
			data.put("rs", xsjbxx);
			data.put("photo", photo);
			data.put("zcf", zcf);
			data.put("xxmc", Base.xxmc);
			HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
			if(xxcjMap == null){
				xxcjMap = new HashMap<String,String>();
			}
			data.put("xxcjMap", xxcjMap);
			String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
			List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
			for (HashMap<String, String> zcfMap : zcfList) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("�۲��ܷ�")){
					data.put("zczf", zcfMap.get("fs"));
					data.put("bjpm", zcfMap.get("bjpm"));
					data.put("njzypm", zcfMap.get("njzypm"));
					data.put("cpzpm", zcfMap.get("cpzpm"));
					break;
				}					
			}
			// ================= ��Ŀ���� begin ==================
			data.put("xmxzmc", "");
			XmwhService xmwhService = new XmwhService();
			List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
			for (HashMap<String, String> xmxzMap : xmxzList) {
				if (xmxzMap.get("dm").equals(model.getXzdm())){
					data.put("xmxzmc", xmxzMap.get("mc"));
					break;
				}
			}

			//����ҽ�ƴ�ѧ���Ի����ű���
			/*if("10598".equals(Base.xxdm)){
				if("�������ء�����ѧ���ɲ���ѧ��".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
				}else if("�˽��ѧ��".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_djbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_djbjxj.xml";
				}else if("�ٺͽ�����ѧ��".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
				}else if("����ѧ��".equals(model.getXmmc()) || "����ѧ���ɲ�".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_shxs.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_shxs.xml";
				}
			}*/
			//�ӱ���ҵ��ѧ
			if("10080".equals(Base.xxdm)){
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				List<HashMap<String,String>> xmXnBxCjList = zcfService.getCjListByXhXn(xh, model.getXn(), "����");
				data.put("xnpjf", service.getPjf(xmXnBxCjList, 2));// ѧ��ƽ����
				String bjzrs = zcfService.getBjrs(xh);
				data.put("bjzrs",bjzrs);// �༶����
				data.put("cjpm", cjpm);// �ɼ�����
				/*ȡ��ѧ���ۺϳɼ���*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*ȡѧ���ۺϳɼ�����*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				// �ѻ���
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjgAllList =  service.getPjpyInfoList(model.getXh(),model.getXn());
				for (int i = 0; i < pjjgAllList.size(); i++) {
					HashMap<String, String> pj = pjjgAllList.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("��");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				data.put("xmmc", model.getXmmc());// ��Ŀ����
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}

				// ��ȡ���ֳɼ�
				List<HashMap<String,String>> hbzcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> hbzcfMap : hbzcfList) {
					String xmmc = hbzcfMap.get("xmmc").trim();
					if(xmmc.equals("ѧ��ѧ�ּ���")){
						data.put("xnxfjd", hbzcfMap.get("fs"));
					}else if(xmmc.equals("ѧ���������Գɼ�")){
						data.put("xntycscj",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("��һ����ѧ��ѧ�ּ�������")){
						data.put("dyktxnxfpx",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("�ڶ�����ѧ��ѧ������")){
						data.put("dektxnxfpx", hbzcfMap.get("fs"));
					}else if(xmmc.equals("ѧ�������Ȩ�ۺ�����")){
						data.put("xnpxjqzhpj",  hbzcfMap.get("fs"));
					}
				}
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// ��������
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
				data.put("xmje",model.getXmje());//��Ŀ���
			}
			//���ݹ���
			if("11998".equals(Base.xxdm)){
				// ����Υ�ʹ���
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "��");
				}else{
					data.put("ywcfmc", "��");
				}
				//��������
				String bkks = xxcjMap.get("bkcjnum");
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "��");
				}else {
					data.put("sfbk", "��");
				}
				StringBuffer jxj = new StringBuffer();//��ѧ��
				StringBuffer qtjx = new StringBuffer();//��������
				for (HashMap<String, String> pj : pjjgAll) {
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						if(xmmc.contains("��ѧ��")){
							jxj.append(xmmc);
							jxj.append("��");
						}else{
							qtjx.append(xmmc);
							qtjx.append("��");
						}
					}
				}
				if(jxj.length()>0)
				jxj.deleteCharAt(jxj.length() - 1);//ɾ�����һ����
				if(qtjx.length()>0)
				qtjx.deleteCharAt(qtjx.length() - 1);
				String tbrq = model.getSqsj();// ����ʱ��
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				data.put("jxj", jxj.toString());
				data.put("qtjx", qtjx.toString());
				List<HashMap<String,String>> zwInfo = dwwhService.getZwxx(xh);//����ְ��
				StringBuffer xrzw = new StringBuffer();
				StringBuffer crzw = new StringBuffer();
				for (HashMap<String, String> map : zwInfo) {
					String lzsj = map.get("lzsj");
					if("1".equals(map.get("zzzt")) && map.get("rzsj").length() >= 10){
						xrzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-����"+"��");
					}else{
						if((lzsj != null || !"".equals(lzsj)) && map.get("rzsj").length() >= 10 && map.get("lzsj").length() >= 10){
							crzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-"+map.get("lzsj").substring(0,10)+"��");
						}
					}
				}
				if(xrzw.length() > 0) xrzw.deleteCharAt(xrzw.length() - 1);
				if(crzw.length() > 0) crzw.deleteCharAt(crzw.length() - 1);
				data.put("xrzw", xrzw.toString());//����ְ��
				data.put("crzw", crzw.toString());//����ְ��
				
				List<HashMap<String,String>> cjlist = zcfService.getCjListByXh(xh);
				data.put("pjcj", service.getPjf(cjlist, 2));//ƽ���ɼ�
				String cjpm = zcfService.getCjpm("", xh, xsjbxx.get("bjmc"));
				data.put("cjpm", cjpm);
			}
			//�㽭��ְͨҵ����ѧԺ
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getSqid());
				data.put("bjyj", shyjjtzyList.get(0).get("shyj"));
				data.put("xscyj", shyjjtzyList.get(2).get("shyj"));
				//ȡ�ۺϲ�������
				List<HashMap<String,String>> pmfsList = new ZhcpDjService().getZcfsForDjb(model.getXh(), model.getXn(), model.getXq());
				for (int i = 0; i < pmfsList.size(); i++) {
					HashMap<String, String> temp = pmfsList.get(i);
					String xmmc = temp.get("xmmc");
					if("�γ�ѧϰ��ʵ������".equals(xmmc)){
						data.put("kcf", temp.get("fs"));
					}else if("˼��Ʒ����Ϊ���ּ�ʵ������".equals(xmmc)){
						data.put("pdf", temp.get("fs"));
					}else if("�������ּ�ʵ������".equals(xmmc)){
						data.put("tyf", temp.get("fs"));
					}else if("�ۺϲ�����".equals(xmmc)){
						data.put("zf", temp.get("fs"));
					}
				}
				HashMap<String, String> zjszf12867 =  pjjgService.getZjszfByXhMap(xh,model.getXn());
				data.put("zjszf12867", zjszf12867);
				data.put("nd",new BzjlxssqService().getHsbyjs(xh));
				
			}
			
			//ͨ��������1-7��
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(model.getSqid());
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			// ================= ��Ŀ���� end ==================
			//�����ǼǱ��ж��Ƿ��и��Ի���������Ĭ�ϡ�
			String templatePath = template_dir+"//pjdjb_"+Base.xxdm+".xml";
			String templateXmlPath = "pjdjb_"+Base.xxdm+".xml";
			File wordFile = null;
			
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), xsjbxx.get("bjmc")+""+xsjbxx.get("xh")+"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}
			
			//�ж��ļ��Ƿ����
			/*try{
				ResourceUtils.getFile(templatePath);
				wordFile = FreeMarkerUtil.getWordFile(data,template_dir,templateXmlPath, FreeMarkerUtil.getFileName(xh+"["+xsjbxx.get("xm")+"]"));
			}catch (Exception e) {
				wordFile = FreeMarkerUtil.getWordFile(data,template_dir,default_template,FreeMarkerUtil.getFileName(xh+"["+xsjbxx.get("xm")+"]"));
			}*/
			
					
			return wordFile;
		}
		
		return null;
	}
	
	/**
	 * �յ�list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}
	
}
