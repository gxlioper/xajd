/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:32:05 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.comm.task.TaskHandler;
import com.zfsoft.xgxt.pjpy.hjsq.jg.HjjgForm;
import com.zfsoft.xgxt.pjpy.hjsq.jg.HjjgService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jglr.JglrService;
import com.zfsoft.xgxt.xpjpy.wdpj.CheckConditionsTask;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgModel;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszService;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.tzsz.TzszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-������� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:32:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SqshAction extends SuperAction {
	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));
	private static final String JXSQ = "jxsq";
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String PJPY_KEY = "PJPY";
	private static final String Temp = "java.io.tmpdir";
	private static final String KFHJQKXZ = "1";
	private static  String xzdm = "1";
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(JXSQ);
	}
	
	private static final String url = "pj_jxsb.do";

	/**
	 * 
	 * @����: �����ϱ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����10:55:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward viewJxsbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		if (QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel); 
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getJxsbList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�۲���Ŀ�б�
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getCurrentZcxmList();
//		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm();
//		List<HashMap<String,String>> zcxmList = zcxmService.getCurrentZfxm();
		
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("path", "pj_jxsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJxsbList");
	}

	
	/**
	 * 
	 * @����: �����ϱ�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����11:04:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward toJxsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		//������Ŀ��Ϣ
		XmwhService xmwhService = new XmwhService();
		XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
		request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		
		//ѧ��������Ϣ
//		XsxxService xsxxService = new XsxxService();
//		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
		request.setAttribute("jbxx", xsjbxx);
		
		//������������Ŀ
		List<HashMap<String,String>> ysqPjxmList = service.getYsqPjxmList(model.getXh(),model.getXmdm());
		request.setAttribute("ysqPjxmList", ysqPjxmList);
		
		//����
		TjszService tjszService = new TjszService();
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
		
		//У������������У����
		CheckCondition check = new CheckStudentCondition();
		List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
		request.setAttribute("checkResult", results);
		return mapping.findForward("toJxsb");
	}
	
	
	/**
	 * 
	 * @����: �����ϱ������棩
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����11:44:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ҵ�����-�����ϱ�-�ϱ�����-XMDM:{xmdm}")
	public ActionForward saveJxsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		//�������
		boolean result = service.saveJxsb(model, user.getUserName());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����10:56:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"})
	public ActionForward viewJxsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		xzdm=model.getXzdm();
		SqshService service = new SqshService();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("xzdm", model.getXzdm());
		if("2".equals(xzdm))
		{
			request.setAttribute("path","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1");
		}else{
			request.setAttribute("path","pj_jxsq.do");
		}
		request.setAttribute("czpath", "pj_jxsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("viewJxsqList");
	}
	
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����10:57:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	
	public ActionForward viewJxshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		xzdm=model.getXzdm();
		SqshService service = new SqshService();
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getAudingListSingle(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//�۲���Ŀ�б�
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getCurrentZcxmList2();
		request.setAttribute("zcxmList", zcxmList);
		if("2".equals(xzdm))
		{
			request.setAttribute("path","xpj_sqsh.do?method=viewJxshList&xzdm=2&sindex=1");
		}else{
			request.setAttribute("path","pj_jxsh.do");
		}
		request.setAttribute("czpath", "pj_jxsh.do");
		FormModleCommon.commonRequestSet(request);
		//��������
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		//Ĭ�ϲ�ѯ���� 
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xzdm", model.getXzdm());
		
		return mapping.findForward("viewJxshList");
	}
	
	@SystemAuth(url = "pj_jxplsh.do")
	public ActionForward viewJxplshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getAudingList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "pj_jxplsh.do");
		FormModleCommon.commonRequestSet(request);
		//��������
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		//Ĭ�ϲ�ѯ���� 
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
		}
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("viewJxshList_1202");
	}
	
	/**
	 * 
	 * @����:��ת���������ҳ
	 * @���ߣ�obq[445]
	 * @���ڣ�2013-12-5 ����09:06:37
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
	public ActionForward toCheckPjpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String xn = request.getParameter("xn");
//		String xq = request.getParameter("xq");
//		String bjdms = request.getParameter("bjdms");
		SqshModel model = (SqshModel) form;
		User user = getUser(request);
		//�õ��۲���Ŀ
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(model.getXn(),model.getXq());
		request.setAttribute("zcxmList", zcxmList);
		//�õ�������Ŀ
		XmwhService xmwhService = new XmwhService();
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm(model.getXn()+model.getXq(),model.getBjdms());
		request.setAttribute("pjxmList", pjxmList);
		//�õ���ѡ�༶ѧ���۲��
		ZcfsService service = new ZcfsService();
		List<HashMap<String,String>> resultList = service.getZcfjgList(model.getXn(),model.getXq(),model.getBjdms());
		//�õ����걨��Ŀ��������״̬����λ
		SqshService sqService = new SqshService();
		request.setAttribute("resultList", resultList);
		List<HashMap<String,String>> shLists = sqService.getLastCheckStatus(user,model.getBjdms(), model.getXn(),model.getXq());
		request.setAttribute("shLists", shLists);
		//request.setAttribute("bjdms", bjdms);
		request.setAttribute("model", model);
		//request.setAttribute("path", "pj_jxsh.do");
		return mapping.findForward("toCheckPjpy");
	}
	
	/**
	 * 
	 * @����:��ת����������ҳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-12 ����09:20:09
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
	public ActionForward toCheckPjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		SqshService sqshService = new SqshService();
		SqshService sqService = new SqshService();
		
		String xn=CsszService.getPjzq().get("xn");
		String xq=CsszService.getPjzq().get("xq");
		
		//�õ��۲���Ŀ
		ZcxmService zcxmService = new ZcxmService();
		List<HashMap<String,String>> zcxmList = zcxmService.getFirstAndSecondZcxm(csszModel.getXn(),csszModel.getXq());
		request.setAttribute("zcxmList", zcxmList);
		//�õ�������Ŀ
		XmwhService xmwhService = new XmwhService();
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm(xn+xq,model.getBjdms());
		
		if("10279".equals(Base.xxdm)){
			List<HashMap<String,String>> newpjxmList=new ArrayList<HashMap<String,String>>();
			for(HashMap<String,String>map:pjxmList){
				if("bj".equals(map.get("rsfpfs"))){
					map.put("xmmc", map.get("xmmc")+"("+map.get("zzme")+")");
				}
				newpjxmList.add(map);
			}
			pjxmList=newpjxmList;
			request.setAttribute("engScoreList",sqService.getEngScore_10279(xn,xq,model.getBjdms()));
		}
		request.setAttribute("pjxmList", pjxmList);
		
		
		//�õ���ѡ�༶ѧ���۲��
		ZcfsService service = new ZcfsService();
		List<HashMap<String,String>> resultList = service.getZcfjgList(csszModel.getXn(),csszModel.getXq(),model.getBjdms());
		//�õ����걨��Ŀ��������״̬����λ
		
		request.setAttribute("resultList", resultList);
		List<HashMap<String,String>> sqLists = sqService.getLastCheckStatus(model.getBjdms(), xn,xq);
		request.setAttribute("sqLists", sqLists);
		request.setAttribute("model", StringUtils.formatData(model));
		//�㽭���˸��Ի�����
		if("11842".equals(Base.xxdm)){
			request.setAttribute("dgbkjd","true");
			request.setAttribute("bxkcjList", sqshService.getXsdmBxkMinCj(csszModel.getXn(),csszModel.getXq(), model.getBjdms()));
////			//������֤list
//			ArrayList<Map<String, Object>> checklist = new ArrayList<Map<String,Object>>();
//			//����resultlist,����ÿ��ѧ������Ŀ�Ƿ�����������֤
//			for (HashMap<String, String> hashMap : resultList) {
//				String xh = hashMap.get("xh");
//				Map<String,Object> resultMap = new SqshService().getXmsqInfoList(xh);
//				resultMap.put("xh", xh);
//				checklist.add(resultMap);
//				
//			}
//			request.setAttribute("checklist", checklist);
		}else{
			request.setAttribute("dgbkjd","false");
		}
		
		List<HashMap<String,String>> checkResultList = sqshService.getCheckResultList(csszModel.getXn(), csszModel.getXq(), model.getBjdms().split(","));
		request.setAttribute("checkResultList",checkResultList);
		return mapping.findForward("toCheckPjsq");
	}
	
	
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����7:30:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward checkConditions(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SqshModel model = (SqshModel) form;
		CpmdService service = new CpmdService();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		String[] bjdmArr = model.getBjdms().split(",");
		List<HashMap<String,String>> cpmdList = service.getCpmdList(bjdmArr, csszModel.getXn(), csszModel.getXq());
		if (cpmdList != null && !cpmdList.isEmpty()){
			CheckConditionsTask task = new CheckConditionsTask(cpmdList);
			
			TaskHandler handler = TaskHandler.getInstance();
			handler.startup(PJPY_KEY);
			handler.addTask(PJPY_KEY, model.getBjdms(), task);
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��ȡ����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����7:35:02
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
	public ActionForward getCheckProgress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SqshModel model = (SqshModel) form;
		TaskHandler handler = TaskHandler.getInstance();
		String progress = handler.getTaskProgress(PJPY_KEY, model.getBjdms());
		boolean isComplate = handler.isComplate(PJPY_KEY, model.getBjdms());
		response.getWriter().print(getJsonMessageResult(progress,isComplate));
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @����:��󼶵ĳ���
	 * @���ߣ�945
	 * @���ڣ�2013-12-5 ����09:24:58
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splcid = request.getParameter("splcid");
		String shid = request.getParameter("shid");
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		// ҵ��ع�
		SqshService sqService = new SqshService();
		boolean result = sqService.cancel(splcid, shxx.get("ywid"),user);
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * 
	 * @����: ѡ���ϱ�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����05:19:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward selectSbjx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhService xmwhService = new XmwhService();
		//������Ŀ�б�
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm();
		
		request.setAttribute("pjxmList", pjxmList);
		
		return mapping.findForward("selectSbjx");
	}


	/**
	 * 
	 * @����: �޸������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����02:59:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"},rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		SqshModel model = service.getModel(sqshForm);
		
			if (model != null){
			BeanUtils.copyProperties(sqshForm, StringUtils.formatData(model));
			
			//ѧ��������Ϣ
//			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
			User user = getUser(model.getSqr());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
			
			//ѧ�������༶
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String, String> cpbjxx = zcfsService.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			
			//������Ŀ��Ϣ
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
		}
		//�й�����ѧԺ���Ի�
		
		if("10355".equals(Base.xxdm)  ){
			XmwhModel xmForm = new XmwhService().getModel(sqshForm.getXmdm());
			request.setAttribute("hjjgxskg", xmForm.getSfkfhjqkxz());
			if(KFHJQKXZ.equals(xmForm.getSfkfhjqkxz())){
				request.setAttribute("hjjgList",new SqshService().getHjxxList(model.getXh(), model.getXn(),model.getXq()));
			}
			
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		return mapping.findForward("updateSqb");
	}


	/**
	 * 
	 * @����: �����޸�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����03:40:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="������������-�ҵ�����-��������-�����޸�-XMDM:{xmdm},SQLY:{sqly}")
	public ActionForward saveUpdateSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		TjszService tjszServcie = new TjszService();
		if(SFBJPY_Y){
			// ����С����Ա�������룡
			JglrService jglrService = new JglrService();
			HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(sqshForm.getXh());
			if(bjpyxzcyMap.get("xh") != null){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		boolean result= false;
		
		//����ύ���������״̬
		if("submit".equalsIgnoreCase(sqshForm.getType())||"tj".equalsIgnoreCase(sqshForm.getType())){
			
			//�����Ի���֤����
			if(Globals.XXDM_ZJDX.equals(Base.xxdm)){
				HashMap<String,String> checkMap = checkRs(sqshForm);
				if("true".equals(checkMap.get("isXmrsOut"))){
					response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
					return null;
				}
				
			}
			// ѧ�������ύ
			if("tj".equalsIgnoreCase(sqshForm.getType())){
				String values = request.getParameter("values");
				sqshForm.setSqid(values);
			}
			
			if(SFBJPY_Y){
				sqshForm.setShzt(Constants.YW_BJPYZ);
			}else{
				sqshForm.setShzt(Constants.YW_SHZ);
			}
			
			// ========== ��֤�������� begin ============
			List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(sqshForm.getXmdm(), sqshForm.getXh());
			// У���������������ز�������������Ŀ���ơ�
			CheckCondition checkCondition = new CheckStudentCondition();
			// �Ƿ�����ȫ����������
			boolean conditionsAllOk = checkCondition.checkConditionBoolean(sqshForm.getXh(), conditions);
			
			if(!conditionsAllOk){
				response.getWriter().print(getJsonMessage("��������������ȷ�ϣ�"));
				return null;
			}
			// ========== ��֤�������� end ============
			
			result = service.runUpdate(sqshForm);
			
			XmwhDao xmwhDao = new XmwhDao();
			XmwhModel xmwhModel = xmwhDao.getModel(sqshForm.getXmdm());
			String splc = xmwhModel.getShlc();
			ShlcInterface shlc = new CommShlcImpl();
			if (!SFBJPY_Y && result) {
				result = shlc.runSubmit(sqshForm.getSqid(), splc,sqshForm.getXh(),"pj_jxsh.do","pj_pjxmsq.do");
			}
		}else{
			 result = service.runUpdate(sqshForm);
		}
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(sqshForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * @����: �����ύ����
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"},rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ҵ�����-��������-�����ύ����-VALUES:{values}")
	public ActionForward saveUpdateSqbPl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel sqshForm = (SqshModel) form;
		SqshService sqshService = new SqshService();
		TjszService tjszServcie = new TjszService();
		ShlcInterface shlcInterface = new CommShlcImpl();
		boolean result = true;
		boolean isXmrsOut=false;//������������
		String rsOutMsg = "";
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int okNum = 0;
			StringBuilder notOkStu = new StringBuilder(); // ������������ѧ��
			HashMap<String, List<HashMap<String,String>>> notOkStuMap = new HashMap<String, List<HashMap<String,String>>>();
			List<HashMap<String,String>> dataList = sqshService.getPjxmXsxxList(values.split(","));
			//�ύ�ĸ�ѧԺ����Ŀ����
			List<HashMap<String,String>> xmrsList = sqshService.getPjxmRsxx(values.split(","));
			List<String> xzjxList = sqshService.getXzjx();
			String[] xmArr = new String[xmrsList.size()];
			for (int i = 0; i < xmrsList.size(); i++) {
				xmArr[i]=xmrsList.get(i).get("xmdm")+xmrsList.get(i).get("xydm");
			}
			//�����Ի��ύ��������������֤
			if(Globals.XXDM_ZJDX.equals(Base.xxdm)){
				for (int i = 0; i < xmrsList.size(); i++) {
					//�ύ����
					if(xzjxList.contains(xmrsList.get(i).get("xmmc"))){
						String xmtjrs = xmrsList.get(i).get("xmtjrs");
						//��Ŀ����������
						String ysqrs = sqshService.getYsqXs(xmArr[i]);
						//��Ŀ��������
						String rssx = sqshService.getPjxmRsxxsx(xmArr[i]);
						if(Integer.parseInt(ysqrs)+Integer.parseInt(xmtjrs)>Integer.parseInt(rssx)){
							isXmrsOut=true;
							rsOutMsg+=xmrsList.get(i).get("xmmc")+" ������������"+rssx+"��<br/>";
						}
					}
				}
				
				if(isXmrsOut){
					response.getWriter().print(getJsonMessage(rsOutMsg));
					return null;
				}
			}
			//��ѯ�ύ��Ŀ����
			for (int i = 0; i < dataList.size(); i++) {
				HashMap<String, String> dataMap = dataList.get(i);
				String sqid = dataMap.get("sqid");
				String xmdm = dataMap.get("xmdm");
				String xmmc = dataMap.get("xmmc");
				String xh = dataMap.get("xh");
				String xm = dataMap.get("xm");
				String splc = dataMap.get("shlc");
				
				if(SFBJPY_Y){
					// ����С����Ա�������룡
					JglrService jglrService = new JglrService();
					HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(xh);
					if(bjpyxzcyMap.get("xh") != null){
						response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
						return null;
					}
				}
				
				// ========== ��֤�������� begin ============
				List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(xmdm, xh);
				// У���������������ز�������������Ŀ���ơ�
				CheckCondition checkCondition = new CheckStudentCondition();
				// �Ƿ�����ȫ����������
				boolean conditionsAllOk = checkCondition.checkConditionBoolean(xh, conditions);
				// ========== ��֤�������� end ============
				// ========== ���ɼ�ý��� begin ============
				JdszService jdszService = new JdszService();
				List<HashMap<String, String>> bjdxmList = jdszService.getBjdxm(xmdm);
				if(conditionsAllOk && bjdxmList.size() > 0){
					// =========== ������XX������������ý��� begin ==========
					Map<String,Object> xmsqInfoMap = sqshService.getXmsqInfoList(xh,xzdm);
					List<HashMap<String, String>> ysqList = (List<HashMap<String, String>>) xmsqInfoMap.get("ysqList");
					if(ysqList != null && ysqList.size() > 0){
						for (HashMap<String, String> bjdxmMap : bjdxmList) {
							for (HashMap<String, String> ysqMap : ysqList) {
								if(bjdxmMap.get("xmdm").equals(ysqMap.get("xmdm"))){
									conditionsAllOk = false;
									break;
								}
							}
							if(!conditionsAllOk){
								break;
							}
						}
					}
					// =========== ������XX������������ý��� end ==========
					// =========== �ý�����XX����ͬʱ���� begin ==========
					if(conditionsAllOk){
						for (int j = i + 1; j < dataList.size(); j++) {
							HashMap<String, String> dataJ = dataList.get(j);
							String xhJ = dataJ.get("xh");
							String xmdmJ = dataJ.get("xmdm");
							if(xh.equals(xhJ)){
								for (HashMap<String, String> bjdxmMap : bjdxmList) {
									if(bjdxmMap.get("xmdm").equals(xmdmJ)){
										conditionsAllOk = false;
										break;
									}
								}
								if(!conditionsAllOk){
									break;
								}
							}
						}
					}
					// =========== �ý�����XX����ͬʱ���� end ==========
				}
				// ========== ���ɼ�ý��� end ============
				
				
				if(conditionsAllOk){
					sqshForm.setSqid(sqid);
					if(SFBJPY_Y){
						sqshForm.setShzt(Constants.YW_BJPYZ);
					}else{
						sqshForm.setShzt(Constants.YW_SHZ);
					}
					result = sqshService.runUpdate(sqshForm);
					if (!SFBJPY_Y && result) {
						result = shlcInterface.runSubmit(sqid,splc,xh,"pj_jxsh.do","pj_pjxmsq.do");
					}
					if (result) {
						okNum++;
					}
				}else{
					HashMap<String,String> mapTemp = new HashMap<String,String>();
					mapTemp.put("xh", xh);
					mapTemp.put("xm", xm);
					mapTemp.put("xmmc", xmmc);
					List<HashMap<String,String>> listTemp = notOkStuMap.get(xmdm);
					if(listTemp == null){
						listTemp = new ArrayList<HashMap<String,String>>();
						notOkStuMap.put(xmdm, listTemp);
					}
					listTemp.add(mapTemp);
				}
			}
			Set<String> keys = notOkStuMap.keySet();
			for(String key : keys){
				List<HashMap<String,String>> temp = notOkStuMap.get(key);
				for (int i = 0; i < temp.size(); i++) {
					if(i == 0){
						notOkStu.append(temp.get(0).get("xmmc") + "��<br/>");
					}
					notOkStu.append(temp.get(i).get("xh") + " " + temp.get(i).get("xm") + "<br/>");
				}
			}
			String resultMsg = "�ύ�ɹ�"+okNum+"����";
			if(dataList.size() - okNum > 0){
				resultMsg += "�����������ļ�¼��<br/>" + notOkStu.toString().substring(0, notOkStu.toString().length() - 1);
			}
			String message = result ? resultMsg : MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	/**
	 * 
	 * @����:��������ĳ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-16 ����10:29:01
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
	@SystemLog(description="������������-�ҵ�����-��������-����VALUES��{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshService service = new SqshService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		if(!SFBJPY_Y){
			result = service.cancleRecord(values,lcid);
		}
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			SqshModel model = new SqshModel();
			model.setSqid(values);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
			
			if(SFBJPY_Y){
				SqshModel modelNew = service.getModel(model);
				JgcxDao jgcxDao = new JgcxDao();
				String sqid = modelNew.getSqid();
				String xn = modelNew.getXn();
				String xq = modelNew.getXq();
				String xmdm = modelNew.getXmdm();
				String sqr = modelNew.getXh();
				// ���°༶�����
				boolean rs = jgcxDao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				if(rs){
					// ���½����ѯ��
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(sqid);
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
//	//�������ύ
//	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		SqshModel sqshForm = (SqshModel) form;
//		SqshService service = new SqshService();
//		
//		sqshForm.setShzt(Constants.YW_SHZ);//�����
//		
//		
//		YdsqService service = new YdsqService();
//		String values = request.getParameter("values");
//		String lcid = request.getParameter("lcid");
//		String ydlx = request.getParameter("ydlx");
//		String xh = request.getParameter("xh");
//		boolean result = service.submitRecord(ydlx,values,lcid,xh);
//		if(result){
//			//����ҵ��״̬Ϊ'�����'
//			YdsqForm model = new YdsqForm();
//			model.setSsydsqid(values);
//			model.setShzt(Constants.YW_SHZ);
//			service.updateModel(model);
//		}
//		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
//				: MessageKey.SYS_SUBMIT_FAIL;
//		response.getWriter().print(getJsonMessageByKey(messageKey));
//		return null;
//	}
	
	
	/**
	 * 
	 * @����: �鿴�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����02:59:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"})
	public ActionForward viewSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		SqshModel model = service.getModel(sqshForm);
		
		if (model != null){
			BeanUtils.copyProperties(sqshForm, StringUtils.formatBean(model));
			
			//ѧ��������Ϣ
//			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
//				xsxxService.getXsjbxxMore(model.getXh());
			User user = getUser(model.getSqr());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
			
			//ѧ�������༶
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String, String> cpbjxx = zcfsService.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			
			//������Ŀ��Ϣ
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			//��˼�¼
			List<HashMap<String,String>> spjlList = service.getSpjlList(model.getSqid());
			request.setAttribute("spjlList", spjlList);
			
			//����
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
			
			//У������������У����
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
			request.setAttribute("checkResult", results);
			
			request.setAttribute("mkxxForm", StringUtils.formatData(model));
			//�й�����ѧԺ
			if("10355".equals(Base.xxdm)){
				XmwhModel xmwhForm = new XmwhService().getModel(model.getXmdm());
				request.setAttribute("hjjgxskg", xmwhForm.getSfkfhjqkxz());
				if("1".equals(xmwhForm.getSfkfhjqkxz())){
					request.setAttribute("hjjgList", new SqshService().getHjxxList(model.getXh(),model.getXn(), model.getXq()));
				}
			}
		}
		
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		
		return mapping.findForward("viewSqb");
	}

	
	/**
	 * 
	 * @����: ��д�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����04:02:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = {"pj_jxsq.do","xpj_sqsh.do?method=viewJxsqList&xzdm=2&sindex=1"},rewritable=ReadWrite.WRITEABLE)
	public ActionForward editSqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		User user = getUser(request);
		
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : sqshForm.getXh();
		
		if (!StringUtil.isNull(xh)){
			//ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());

			request.setAttribute("jbxx", xsjbxx);
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		request.setAttribute("pjzq", CsszService.getPjzq().get("pjzqmc"));
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("path", "xpj_sqsh.do?method=editSqb&xzdm="+sqshForm.getXzdm());
		request.setAttribute("xh", xh);
		if (!StringUtil.isNull(xh)){
			//ѧ�������༶��Ϣ
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String,String> cpbjxx = zcfsService.getCpbjListByXh(xh, csszModel.getXn(), csszModel.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
		}
		this.saveToken(request);
		request.setAttribute("xzdm",sqshForm.getXzdm());
		return mapping.findForward("editSqb");
	}
	
	
	/**
	 * 
	 * @����: ѡ��������Ŀ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����04:36:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	
	public ActionForward selectPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		xzdm = sqshForm.getXzdm();
		SqshService service = new SqshService();
		
		Map<String,Object> resultMap = service.getXmsqInfoList(sqshForm.getXh(),xzdm);
		
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("selectPjxm");
	}
	
	
	/**
	 * 
	 * @����: �������뱣��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����09:59:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="������������-�ҵ�����-��������-����ҳ��-����XMDMS:{xmdms}XH:{xh},SQLY:{sqly}")
	public ActionForward saveJxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		if(SFBJPY_Y){
			// ����С����Ա�������룡
			JglrService jglrService = new JglrService();
			HashMap<String,String> bjpyxzcyMap = jglrService.queryBjpyxzcy(sqshForm.getXh());
			if(bjpyxzcyMap.get("xh") != null){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		
		String[] xmdm = request.getParameterValues("xmdms");
		//�����Ի���֤����
		if(Globals.XXDM_ZJDX.equals(Base.xxdm)&&"submit".equals(sqshForm.getType())){
			HashMap<String,String> checkMap = new HashMap<String,String>();
			for (int i = 0; i < xmdm.length; i++) {
				sqshForm.setXmdm(xmdm[i]);
				checkMap = checkRs(sqshForm);
			}
			
			if("true".equals(checkMap.get("isXmrsOut"))){
				response.getWriter().print(getJsonMessage(checkMap.get("rsOutMsg")));
				return null;
			}
			
		}
		if("dgtj".equals(request.getParameter("flag"))){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			boolean result = service.checkIsNotbkjd(xmdm[0], csszModel.getXn(), csszModel.getXq(), sqshForm.getXh());
			if(!result){
				String bkjdmc = service.getbkjdMc(xmdm[0]);
				String message = "����Ŀ��["+bkjdmc+"]���ɼ�ã�";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		User user = getUser(request);
		sqshForm.setSqr(user.getUserName());
		boolean result = service.saveJxsq(xmdm, sqshForm);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(sqshForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: ȡ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����11:34:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="������������-�ҵ�����-��������-ɾ��VALUES��{values}")
	public ActionForward cancelXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshService service = new SqshService();
		
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// ɾ��
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	
	}
	
	
	/**
	 * 
	 * @����: ������ͳ�� 
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����08:46:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 * @deprecated
	 */
	public ActionForward viewShqkList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		if (QUERY.equals(sqshForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			sqshForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getShqkList(sqshForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//������λ�б�
		List<HashMap<String,String>> spgwList = service.getSpgwList(user);
		
		if (spgwList == null || spgwList.isEmpty()){
			throw new SystemException(MessageKey.PJPY_AUDING_QXBZ);
		}
		
		request.setAttribute("spgwList", spgwList);
		request.setAttribute("path", "pj_jxsh.do");
		FormModleCommon.commonRequestSet(request);
		
		//����������Ϣ
		CsszModel csszModel = new CsszService().getModel();
		request.setAttribute("csszModel", csszModel);
		//�߼���ѯ���ñ�ʶ
		request.setAttribute("path", service.getShqkSearchPath(sqshForm));
		return mapping.findForward("viewShqkList");
	}
	

	/**
	 * 
	 * @����: ѡ��ͳ�Ƶ�λ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����11:42:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward selectTjdw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		OptionUtil optionUtil = new OptionUtil();
		List<HashMap<String,String>> tjdwList = optionUtil.getOptions(OptionUtil.PJPY_TJDW);
		
		request.setAttribute("tjdwList", tjdwList);
		return mapping.findForward("selectTjdw");
	}


	/**
	 * 
	 * @����: �����ϸҳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:33:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = {"pj_jxsh.do","xpj_sqsh.do?method=viewJxshList&xzdm=2&sindex=1"})
	public ActionForward viewJxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		String shid = sqshForm.getShid(); 
		
		SqshModel model = service.getModel(sqshForm);
		model.setGwid(sqshForm.getGwid());
		if (null != model && !StringUtil.isNull(model.getXh())){
			//ѧ��������Ϣ
//			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
			User user = getUser(model.getSqr());
			xsjbxx.put("sqr",user.getRealName());
			xsjbxx.put("sqrbm",user.getUserDepName());
			request.setAttribute("jbxx", xsjbxx);
			
			//���ز����༶��Ϣ
			
			
			//�����۲�ɼ�
			ZcfsService zcfsServcie = new ZcfsService();
			List<HashMap<String,String>> zcfsList = zcfsServcie.getZcfListByXh(model.getXh(), model.getXn(), model.getXq());
			HashMap<String, String> cpbjxx = zcfsServcie.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			request.setAttribute("zcfsList", zcfsList);
	        if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)){
	        	List<HashMap<String,String>> zcpmAll = zcfsServcie.getAllZcpmWithXh(model.getXh());
	        	request.setAttribute("zcpmAll", zcpmAll);
			}

			// ����ѧ�Ų�ѯѧ���춯�б�
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(model.getXh());
			request.setAttribute("xjydList", xjydList);

			//����������Ŀ��Ϣ
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", StringUtils.formatData(xmwhModel));
			
			//�����������ͨ����Ŀ����
			XmwhModel dqxmwhModel = xmwhService.getModel(model.getDqxmdm());
			request.setAttribute("dqxmwhModel", dqxmwhModel);
			
			//����������¼
			List<HashMap<String,String>> spjlList = service.getSpjlList(model.getSqid());
			request.setAttribute("spjlList", spjlList);
			
			//���˻ظ�λ
			ShlcInterface shlcService = new CommShlcImpl();
			List<HashMap<String,String>> kthGwList = shlcService.getKthSpgw(model.getSplc(), sqshForm.getShid());
			request.setAttribute("kthGwList", kthGwList);
			//�ɵ�������
			TzszService tzszService = new TzszService();
			List<HashMap<String,String>> ktzPjxmList = tzszService.getKtzPjxmList(model.getDqxmdm());
			request.setAttribute("ktzPjxmList", ktzPjxmList);
			
			//����
			TjszService tjszService = new TjszService();
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(model.getXmdm(),model.getXh());
			
			//У������������У����
			CheckCondition check = new CheckStudentCondition();
			List<HashMap<String, String>> results = check.checkCondition(model.getXh(), conditions);
			request.setAttribute("checkResult", results);
			
			
			BeanUtils.copyProperties(sqshForm, StringUtils.formatBean(model));
			sqshForm.setShid(shid);
			request.setAttribute("mkxxForm", model);
			//�й�����ѧԺ���Ի�
			
			if("10355".equals(Base.xxdm)  ){
				XmwhModel xmForm = new XmwhService().getModel(sqshForm.getXmdm());
				request.setAttribute("hjjgxskg", xmForm.getSfkfhjqkxz());
				if(KFHJQKXZ.equals(xmForm.getSfkfhjqkxz())){
					request.setAttribute("hjjgList",new SqshService().getHjxxList(model.getXh(), model.getXn(),model.getXq()));
				}
				
			}
		}
		BdpzService bdpzService = new BdpzService();
		request.setAttribute("jbxxList",bdpzService.getJbxxpz(JXSQ));
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		this.saveToken(request);
		return mapping.findForward("viewJxsh");
	}

	
	
	/**
	 * 
	 * @����: Ajax��֤��Ŀ����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-13 ����10:53:33
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
	public ActionForward checkCondition(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		//����
		TjszService tjszService = new TjszService();
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(sqshForm.getXmdm(),sqshForm.getXh());
		
		//У������������У����
		CheckCondition check = new CheckStudentCondition();
		List<HashMap<String, String>> results = check.checkCondition(sqshForm.getXh(), conditions);
		
		JSONArray json = JSONArray.fromCollection(results);
		response.getWriter().print(json);
		
		return null;
	}
	
	/** 
	 * @����:��ȡ��Ŀ���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-25 ����09:07:40
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
	public ActionForward getXmje(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		SqshModel sqshForm = (SqshModel) form;
		String xmje = new SqshService().getXmje(sqshForm.getXmdm());
		JSONObject json = JSONObject.fromString("{xmje:"+xmje+"}");
		response.getWriter().print(json);		
		return null;
	}
	

	/**
	 * 
	 * @����: ������˲���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:50:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="������������-�ҵ�����-�������-�������-SQID��{sqid}")
	public ActionForward runAuding(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		String isSuccess = service.runAuding(sqshForm,user);
		String messageKey = "";
		if(isSuccess.equals("true")){
			messageKey = MessageKey.SYS_AUD_SUCCESS;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else if(isSuccess.equals("false")){
			messageKey = MessageKey.SYS_AUD_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			messageKey = isSuccess;
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", messageKey);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
		}
		
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: ������˲���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����11:12:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description="������������-�ҵ�����-�������-���泷��-SQID��{sqid}")
	public ActionForward runCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		boolean isSuccess = service.runCancel(sqshForm, user);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * 
	 * @����: ���̸���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-8 ����10:13:17
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
	public ActionForward viewLcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		//������ѯ
		SqshModel model = service.getModel(sqshForm);
		
		if (model != null){
			BeanUtils.copyProperties(sqshForm, model);
		}
		
		//������¼
		List<HashMap<String,String>> spjlList = service.getSpjlList(sqshForm.getSqid());
		request.setAttribute("spjlList", spjlList);
		request.setAttribute("spjlsize", spjlList.size());
		
		//������λ
		List<HashMap<String,String>> spgwList = ShlcUtil.getSpbzBySplc(model.getSplc());
		request.setAttribute("spgwList", spgwList);
		
		//����˸�λ
		request.setAttribute("dshGwid", service.getDsgw(sqshForm.getSqid()));
		return mapping.findForward("viewLcgz");
	}


	/**
	 * 
	 * @����: չʾ������ͳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-12 ����11:38:22
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
	public ActionForward viewShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		RsszService rsszService = new RsszService();
		request.setAttribute("rsfpfs", rsszService.getRsfpfs(sqshForm.getXmdm()));

		if (StringUtils.isNotNull(sqshForm.getXmdm())) {
			Map<String,Object> shqkInfo = service.getShqkInfo(user,sqshForm.getXmdm());

			request.setAttribute("shqkInfo", shqkInfo);
		}
		return mapping.findForward("viewShqk");
	}
	
	
	/**
	 * 
	 * @����: ������Ŀ���ͳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-12 ����11:41:49
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
	public ActionForward pjxmShtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmwhService xmwhService = new XmwhService();
		
		List<HashMap<String,String>> pjxmList = xmwhService.getOthers(" ",xzdm);

		request.setAttribute("pjxmList", pjxmList);
		return mapping.findForward("pjxmShtj");
	}




	/**
	 * 
	 * @����: ���ͳ��ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-12 ����02:28:43
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
	public ActionForward getStudentsByShqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		if (QUERY.equalsIgnoreCase(sqshForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			sqshForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getStudentsFromShtj(sqshForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "pj_shtj_students.do");
		return mapping.findForward("studentsList");
	}
	
	
	/**
	 * 
	 * @����:������˵���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-10-26 ����11:14:48
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAudingExportList(model,
				user);// ��ѯ�����м�¼������ҳ

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
	 * @����:������Ŀ���뵼��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-10-26 ����12:29:43
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
	public ActionForward sqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getSqExportList(model,
				user);// ��ѯ�����м�¼������ҳ

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
	 * @����:�������ҳ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-9 ����10:57:33
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
	public ActionForward toPlshy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		//�õ�������Ŀ
		XmwhService xmwhService = new XmwhService();
		List<HashMap<String,String>> pjxmList = xmwhService.getKfsqPjxm(model.getXn()+model.getXq());
		request.setAttribute("pjxmList", pjxmList);
		request.setAttribute("model", model);
		return mapping.findForward("pjshview");
	}
	
	public ActionForward toPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		SqshModel model = (SqshModel) form;
//		request.setAttribute("model", model);
		request.setAttribute("xxdm",Base.xxdm);
		return mapping.findForward("pjshview_new");
	}
	
	
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel myForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		String message = service.batchSave(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-9 ����10:57:58
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
	@SystemLog(description="������������-�ҵ�����-�����������-����-XMDMS:{xmdms}")
	public ActionForward doPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel myForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�cq[���ţ�785]
	 * @���ڣ�2013-12-10 ����01:47:31
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
	public ActionForward viewJxsqList_new(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getXssqList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "pj_jxsq_new.do");
		FormModleCommon.commonRequestSet(request);
		//��������
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("cssz", csszModel);
		
		return mapping.findForward("viewJxsqList_new");
	}
	
	/**
	 * 
	 * @����:�����ϱ�����ѡ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-10 ����07:24:39
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
	public ActionForward plSelectPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel sqshForm = (SqshModel) form;
		SqshService service = new SqshService();
		
		Map<String,Object> resultMap = service.getXmsqInfoList(sqshForm.getXh(),xzdm);
		
		request.setAttribute("xh", sqshForm.getXh());
		request.setAttribute("resultMap", resultMap);
		return mapping.findForward("plSelectPjxm");
	}
	
	
	/**
	 * 
	 * @����:������˳���
	 * @���ߣ�cq ���ţ�785
	 * @���ڣ�2013-12-16 ����02:49:46
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
	@SystemLog(description="������������-�ҵ�����-�������-��������-SHID:{shid}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshService sqService = new SqshService();
		//SqshModel model = (SqshModel) form;
		SqshModel model = new SqshModel();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("splc");
		String xmdm = request.getParameter("xmdm");
		String xh = request.getParameter("xh");
		model.setSplc(splc);
		model.setShid(shid);
		model.setXmdm(xmdm);
		model.setXh(xh);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = sqService.cxshnew(shxx.get("ywid"),model,user);
		

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
	 * @����:�㽭��ѧ���Ի��޸�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-25 ����06:47:36
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
	public ActionForward zjdxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("jsonStr", request.getParameter("jsonStr"));
		return mapping.findForward("zjdxPlsh");
	}
	
	/**
	 * 
	 * @����:�㽭�����Ի����������ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-26 ����03:56:21
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
	public ActionForward viweShmx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> viweShmx = service.getViweShmx(model,user);
		JSONArray dataList = JSONArray.fromObject(viweShmx);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	public ActionForward saveZdPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel myForm = (SqshModel) form;
		SqshService service = new SqshService();
		User user = getUser(request);
		
		System.out.println("����ʱ��=================="+System.currentTimeMillis());
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		String message = service.saveZdPlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		System.out.println("����ʱ��=================="+System.currentTimeMillis());
		return null;
	}
	

	public ActionForward viewAllZcpm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		//ѧ��������Ϣ
//		XsxxService xsxxService = new XsxxService();
//		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		HashMap<String,String> xsjbxx = service.getPjInfo(model.getXh(), model.getXn(), model.getXq());
		request.setAttribute("jbxx", xsjbxx);
		
		ZcfsService zcfsServcie = new ZcfsService();
		List<HashMap<String,String>> zcpmAll = zcfsServcie.getAllZcpmWithXh(model.getXh());
		
    	request.setAttribute("zcpmAll", zcpmAll);
    	request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewAllzcpm");
	}
	
	private HashMap<String, String> checkRs(SqshModel sqshForm) throws Exception {
		// ����������Ϣ
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		sqshForm.setXn(csszModel.getXn());
		sqshForm.setXq(CsszService.getPjzq().get("xq"));
		String isXmrsOut = "false";
		String rsOutMsg = "";
		HashMap<String, String> checkMap = new HashMap<String, String>();
		SqshService sqshService = new SqshService();
		XmwhService xmwhService = new XmwhService();
		HashMap<String, String> xmMap = xmwhService.getDataById(sqshForm.getXmdm());
		HashMap<String, String> xsxxMap = sqshService.getPjxmXsxxMap(sqshForm);
		List<String> xzjxList = sqshService.getXzjx();
		
			if (xzjxList.contains(xmMap.get("xmmc"))) {
				// ��Ŀ����������
				String xmxx=xmMap.get("xmdm")+xsxxMap.get("xydm");
				String ysqrs = sqshService.getYsqXs(xmxx);
				// ��Ŀ��������
				String rssx = sqshService.getPjxmRsxxsx(xmxx);
				if (Integer.parseInt(ysqrs) + 1 > Integer.parseInt(rssx)) {
					isXmrsOut = "true";
					rsOutMsg += xmMap.get("xmmc") + " ������������" + rssx + "��<br/>";
				}
			}
		
		checkMap.put("isXmrsOut", isXmrsOut);
		checkMap.put("rsOutMsg", rsOutMsg);
		return checkMap;
	}
	
	//����������Ŀѡ����֤
	public ActionForward selectJtpjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		String bjdm = request.getParameter("bjdm");
		User user = getUser(request);
		TjszService tjszServcie = new TjszService();
		List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(xmdm, bjdm);
		// У�����������ز�������������Ŀ���ơ�
		CheckCondition check = new CheckStudentCondition();
		// У����
		List<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		if ("10466".equals(Base.xxdm)) {
			results = check.checkCondition(bjdm+"&&"+user.getUserName(),
					conditions);
		}else {
			results = check.checkCondition(bjdm,conditions);
		}
	   JSONArray json = JSONArray.fromObject(results);
	   response.getWriter().print(json);
	   return null;
	}
	
	/**
	 * 
	 * @����:�����ϱ�����ѡ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-10 ����07:24:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public void checkIsExists(HttpServletRequest request)
			throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		String xh = (String)request.getAttribute("xh");
		String xmdm = (String)request.getAttribute("xmdm");
		Map<String,Object> resultMap = new SqshService().getXmsqInfoList(xh,xzdm);
		List<HashMap<String, String>> wsqlist = (List<HashMap<String, String>>) resultMap.get("wsqList");
		for (HashMap<String, String> hashMap : wsqlist) {
			if(xmdm.equals(hashMap.get("xmdm"))){
				result = hashMap;
			}
		}
		request.setAttribute("result", result);
	}
	
	/**
	 * @�������������ܱ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��23�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward getHzbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		File excelFile = service.getHzbPrint(searchModel);
		FileUtil.outputWord(response, excelFile);
		return null;
	}
	
	
	/**
	 * @����:�㽭ͬ�ÿƼ�ְҵѧԺ��ѧ���ɼ����ܵ���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��11�� ����3:40:39
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
	public ActionForward xscjhzdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXscjhzList(model, user);//��ѯ�����м�¼������ҳ
		
		File file = service.getXscjhzFile(resultList);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����:�㽭ͬ�ÿƼ�ְҵѧԺ��ѧ���ɼ����ܲ鿴
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��11�� ����3:40:39
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
	public ActionForward xscjhzck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXscjhzList(model, user);//��ѯ�����м�¼������ҳ
		
		Map<String,ClassSummary> classMap = new ResultSummary().setResultMap("bjdm", resultList).getResultMap();
		request.setAttribute("classMap", classMap);
		return mapping.findForward("xscjhzShow");
	}
	
	/** 
	 * @����:�����������(�Ϻ�Ϸ����Ի�)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-19 ����05:25:23
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
	public ActionForward fjdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		List<File> files = service.getFjList(model, user);
		if(files.size()>0){
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			FileUtil.delFolder(System.getProperty(Temp) + "\\" + PJPY_KEY);
		}
		return null;
	}
	
	/** 
	 * @����:��֤�Ƿ��е����ĸ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-22 ����01:52:57
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
	public ActionForward checkFjExist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		SqshService service = new SqshService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<File> files = service.getFjList(model, user);
		if(files.size()<1){
			response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_SHXJ_NOATTACHMENT)); 
		}else{
			response.getWriter().print(true);
		}
		return null;
	}

	/**
	 *  �������Ž��������ύ�����쳣����.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 17:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw
	 */
	public ActionForward exceptionDataResolve(ActionMapping mapping, ActionForm form,
											  HttpServletRequest request, HttpServletResponse response) throws Exception {

		SqshService service = new SqshService();
		Map<String, String> map = service.exceptionDataResolve();

		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @description	�� ѧ������������������Ͼ��ߵ�ְҵ����ѧУ��
	 * @author 		�� ������1282��
	 * @date 		��2018-1-5 ����04:05:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsglmddc(ActionMapping mapping, ActionForm form,
			  HttpServletRequest request, HttpServletResponse response) throws Exception {
		SqshModel model = (SqshModel) form;
		SearchModel searchModel = model.getSearchModel();
		SearchModel sm = new SearchModel();
		
		sm.setSearch_tj_xn(searchModel.getSearch_tj_xn());//ѧ���ѯ����
		sm.setSearch_tj_xq(searchModel.getSearch_tj_xq());//ѧ�ڲ�ѯ����
		sm.setSearch_tj_xy(searchModel.getSearch_tj_xy());//ѧԺ��ѯ����
		sm.setPath(searchModel.getPath());
		model.setSearchModel(sm);
		
		SqshService service = new SqshService();
		List<File> files = service.getXsglmc(model);//��ȡѧ����������exl�ļ�
		if(files.size()>1){
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);					
		}else if(files.size()==1){
			FileUtil.outputExcel(response, files.get(0));
		} 
		return null;
	}
	
	/**
	 * @description	�� ��֤�Ƿ������ݿ��Ե���(�Ͼ��ߵ�ְҵ����ѧԺ)
	 * @author 		�� ������1282��
	 * @date 		��2018-1-9 ����02:27:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzxsglmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SqshModel model = (SqshModel) form;
		SearchModel searchModel = model.getSearchModel();
		SearchModel sm = new SearchModel();
		
		sm.setSearch_tj_xn(searchModel.getSearch_tj_xn());//ѧ���ѯ����
		sm.setSearch_tj_xq(searchModel.getSearch_tj_xq());//ѧ�ڲ�ѯ����
		sm.setSearch_tj_xy(searchModel.getSearch_tj_xy());//ѧԺ��ѯ����
		sm.setPath(searchModel.getPath());
		model.setSearchModel(sm);
		
		SqshService service = new SqshService();
		boolean result = service.yzxsglmddc(model);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		JSONObject fromBean = JSONObject.fromBean(map);
		response.getWriter().print(fromBean);
		return null;
	}
	public ActionForward sqlyPdf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("sqlyPdf");
	}
	
	/**
	 *
	 * @����: �Ƿ�����������(�й�����ѧԺ���Ի�)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-16 ����11:10:55
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
	public ActionForward checkHjsqCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		XmwhModel model = new XmwhService().getModel(xmdm);
		String checkResult = "false";
		if(model != null && StringUtils.isNotNull(model.getSfkfhjqkxz()) && KFHJQKXZ.equals(model.getSfkfhjqkxz())){
			checkResult = "true";
		}
		response.getWriter().print(getJsonMessage(checkResult));
		return null;
	}
	
	/**
	 * 
	 * @����: �񽱽��add
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-16 ����01:48:22
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
	public ActionForward getHjjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xpj_sqsh.do?method=getHjjgAdd");
		request.setAttribute("xh",request.getParameter("xh"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hjjgadd");
	}
	
	/**
	 * 
	 * @����: ��ѯ�񽱽��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-16 ����02:13:52
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
	public ActionForward searchHjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		HjjgForm hjForm = new HjjgForm();
		BeanUtils.copyProperties(hjForm, model);
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setInput_xh(model.getXh());
		hjForm.setSearchModel(searchModel);  
		User user = getUser(request);
		HjjgService service = new HjjgService();
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getHjxxPageList(hjForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: �������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-19 ����11:30:41
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
	public ActionForward saveHjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SqshModel model = (SqshModel) form;
		boolean rs = new SqshService().saveHjxx(model);
		JSONObject jsonRs = getJsonMessage(rs+"");
		if(rs){
			CsszModel cssz = new CsszService().getModel();
			List<HashMap<String,String>> rsList = new SqshService().getHjxxList(model.getXh(), cssz.getXn(),cssz.getXq());
			jsonRs.put("rsList", JSONArray.fromObject(rsList));
		}
		response.getWriter().print(jsonRs);
		return null;
		
	}
	public ActionForward delHjxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���id
		String values = request.getParameter("values");
		HjjgService service = new HjjgService();
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = service.delHjxx(ids);
			SqshModel model = (SqshModel) form;
			JSONObject jsonRs = getJsonMessage(result+"");
			if(result){
				CsszModel cssz = new CsszService().getModel();
				List<HashMap<String,String>> rsList = new SqshService().getHjxxList(model.getXh(), cssz.getXn(),cssz.getXq());
				jsonRs.put("rsList", JSONArray.fromObject(rsList));
			}
			response.getWriter().print(jsonRs);
			/*String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));*/
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����: ���ػ���Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-20 ����10:13:17
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
	public ActionForward loadHjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		request.setAttribute("hjjgList",new SqshService().getHjxxList(request.getParameter("xh"), csszModel.getXn(),csszModel.getXq()));
		return mapping.findForward("hjxx");
	}
	
	/**
	 * 
	 * @����: ��ȡ��Ŀ�Ƿ�������Ϣ�鿴����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-21 ����09:27:39
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
	public ActionForward getSfkqHjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		XmwhModel model = new XmwhService().getModel(xmdm);
		boolean rs = (model != null && StringUtils.isNotNull(model.getSfkfhjqkxz()) && KFHJQKXZ.equals(model.getSfkfhjqkxz())) ? true : false;
		response.getWriter().print(getJsonMessage(rs+""));
		return null;
	}
}
