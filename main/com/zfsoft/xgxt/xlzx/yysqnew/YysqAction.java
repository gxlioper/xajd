package com.zfsoft.xgxt.xlzx.yysqnew;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxAction;
import com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxForm;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xlzx.yysq.zwpg.ZwpgService;
import com.zfsoft.xgxt.xlzx.zxsgly.ZxsglyService;
import com.zfsoft.xgxt.xlzx.zxswh.ZxsglService;
import com.zfsoft.xgxt.xlzx.zxswh.ZxspbService;
import com.zfsoft.xgxt.xlzx.zxyyclnew.ZxyyclService;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlModel;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
/** 
 * ������ѯ����
 */

public class YysqAction extends SuperAction{

	private static YysqService service = new YysqService();
	/**
	 * ����������ѯԤԼ������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String XLJKYYSQ = "xljkyysq";
	private List<HashMap<String,String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();
	
	private static final String url = "xlzx_yysqnew.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/** 
	 * ԤԼ��ѯ������ѯҳ��
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyfkManage")
	public ActionForward yyfkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> list = service.queryYyfkList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xlzx_yysqnew.do?method=yyfkManage");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yyfkManage");
	}
	/** 
	 * ԤԼ��ѯ����
	 */
	@SuppressWarnings("unchecked")
	public ActionForward yyfkDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String zxrq = request.getParameter("zxrq");
		String doType = request.getParameter("doType");
		String pbfs = new ZxspbService().getPbfs();
		int yjdrs = 0;
		String date = "";
		HashMap<String, String> yyzxInfo = new HashMap<String,String>();
		if(!StringUtil.isNull(id)){
			yyzxInfo = service.getYyzxDetail(id);
			if(StringUtils.isNull(zxrq)){
				if(!StringUtils.isNull(yyzxInfo.get("zxrq"))){
					date = yyzxInfo.get("zxrq");
				}else{
					date = yyzxInfo.get("yyzxrq");
				}
			}else {
				date = zxrq;
				yyzxInfo.put("zxrq", zxrq);
			}
			List<HashMap<String, String>>  zxsInfo = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getYysqByZghAnddDate(date,yyzxInfo.get("zgh"));
			if(zxsInfo!=null && zxsInfo.size()>0){
				yjdrs = zxsInfo.size();
			}
		}
		if(!"view".equals(doType) && (StringUtils.isNotNull(pbfs) && "2".equals(pbfs))){
			request.setAttribute("sjddmList",  new com.zfsoft.xgxt.xlzx.yysq.YysqService().getXsYySjd(date, yyzxInfo.get("zgh"), yyzxInfo.get("xh")));
		}
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs) && "10026".equals(Base.xxdm)){
			request.setAttribute("xqmc", new ZxspbService().getXqmc(yyzxInfo.get("zxrq"), yyzxInfo.get("apzxs")));
			request.setAttribute("yyxqmc", new ZxspbService().getXqmc(yyzxInfo.get("yyzxrq"), yyzxInfo.get("zgh")));
		}
		request.setAttribute("yjdrs", yjdrs);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		request.setAttribute("doType", doType);
		request.setAttribute("pbfs",pbfs);
		request.setAttribute("xxdm", Base.xxdm);
		String currxn=Base.currXn;
		String currxq=Base.getDqxqmc();
		request.setAttribute("currxn", currxn);
		request.setAttribute("currxq", currxq);
		request.setAttribute("today",GetTime.getTimeByFormat(DATE_FORMAT));
//		User user = getUser(request);
		// ��ѧ����Ϣ
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			ZxzxjlService zxzxjlService = new ZxzxjlService();
			ZxzxjlModel zxzxjlModel = new ZxzxjlModel();
			zxzxjlModel.setXh(yyzxInfo.get("xh"));
			zxzxjlModel = zxzxjlService.getModel(zxzxjlModel);
			request.setAttribute("zxzxjlModel", zxzxjlModel);

			//ѧ����д��Ϣ
			XstxxxForm xstxxxForm = XstxxxAction.getModel(yyzxInfo.get("xh"));
			request.setAttribute("xstxxx", xstxxxForm);
		}

		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("yyfkDetail");
	}
	/** 
	 * ��ѯ����
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward zxfkDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		HashMap<String, String> yyzxInfo = service.getYyzxDetail(id);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		User user = getUser(request);
		String pbfs = new ZxspbService().getPbfs();
		// ��ѧ����Ϣ
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		//��ѯ��ʷ��Ϣ
		List<HashMap<String, String>> zxlsxxList = service.getZxlsxxList(yyzxInfo.get("xh"), id);
		request.setAttribute("zxlsxxList", zxlsxxList);
		List<HashMap<String,String>> haveList = new OptionUtil().getOptions(OptionUtil.HAVENOT);
		request.setAttribute("haveList", haveList);
		List<HashMap<String,String>> isNotList = new OptionUtil().getOptions(OptionUtil.ISNOT);
		request.setAttribute("isNotList", isNotList);

		//��ѯ���������б�
		if("10704".equals(Base.xxdm)){
			List<HashMap<String, String>> zxwtlxList = service.getZxwtlxList();
			request.setAttribute("zxwtlxList", zxwtlxList);
		}

		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs) && "10026".equals(Base.xxdm)){
			request.setAttribute("xqmc", new ZxspbService().getXqmc(yyzxInfo.get("zxrq"), yyzxInfo.get("apzxs")));
		}
		request.setAttribute("pbfs",pbfs);
		request.setAttribute("xxdm",Base.xxdm);

		//�ҳ���Ϣ
		HashMap<String, String> jzxx = service.getJzxx(id);
		request.setAttribute("jzxx", StringUtils.formatData(jzxx));
		//ѧ����д��Ϣ
		XstxxxForm xstxxxForm = XstxxxAction.getModel(yyzxInfo.get("xh"));
		request.setAttribute("xstxxx", xstxxxForm);

		return mapping.findForward("zxfkDetail");
	}
	/** 
	 * ��ѯ����
	 */
	
	public ActionForward zxfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		HashMap<String, String> yyzxInfo = service.getYyzxDetail(id);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		String pbfs = new ZxspbService().getPbfs();
		User user = getUser(request);
		// ��ѧ����Ϣ
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		//��ѯ��ʷ��Ϣ
		List<HashMap<String, String>> zxlsxxList = service.getZxlsxxList(yyzxInfo.get("xh"), id);
		request.setAttribute("zxlsxxList", zxlsxxList);
		request.setAttribute("pbfs", pbfs);
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs) && "10026".equals(Base.xxdm)){
			request.setAttribute("xqmc", new ZxspbService().getXqmc(yyzxInfo.get("zxrq"), yyzxInfo.get("apzxs")));
		}
		//�ҳ���Ϣ
		HashMap<String, String> jzxx = service.getJzxx(id);
		request.setAttribute("jzxx", StringUtils.formatData(jzxx));
		//ѧ����д��Ϣ
		XstxxxForm xstxxxForm = XstxxxAction.getModel(yyzxInfo.get("xh"));
		request.setAttribute("xstxxx", xstxxxForm);
		return mapping.findForward("zxfkView");
	}
	/** 
	 * ��ѯ����
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyjgManage")
	public ActionForward yyjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		HashMap<String, String> yyzxInfo = service.getYyzxDetail(id);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		User user = getUser(request);
		// ��ѧ����Ϣ
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		//��ѯ��ʷ��Ϣ
		List<HashMap<String, String>> zxlsxxList = service.getZxlsxxList(yyzxInfo.get("xh"), id);
		request.setAttribute("zxlsxxList", zxlsxxList);
		String pbfs = new ZxspbService().getPbfs();
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs) && "10026".equals(Base.xxdm)){
			request.setAttribute("xqmc", new ZxspbService().getXqmc(yyzxInfo.get("zxrq"), yyzxInfo.get("apzxs")));
		}
		request.setAttribute("pbfs", pbfs);
		return mapping.findForward("yyjgView");
	}
	
	/** 
	 * ѧ��ԤԼ�����ѯҳ�桢ԤԼ��ѯ�����ѯҳ��
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage")
	public ActionForward yysqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoList(myForm, user);
			
			JSONArray dataList = JSONArray.fromObject(YysqInfoList);
			response.getWriter().print(dataList);
			return null;
		}
	
		if(userStatus.equals("stu")){
			request.setAttribute("path", "xlzx_yysqnew_yysqnew.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("yysqManage");
		}else{
			request.setAttribute("path", "xlzx_yysqnew.do?method=yysqManage");
			FormModleCommon.commonRequestSet(request);
			String pbfs = new ZxspbService().getPbfs();
			request.setAttribute("pbfs",pbfs);
			return mapping.findForward("zxyyclManage");
		}
	}
	/** 
	 * ԤԼ���
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyjgManage")
	public ActionForward yyjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YysqForm myForm = (YysqForm) form;
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoListJg(myForm, user);
			
			JSONArray dataList = JSONArray.fromObject(YysqInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "xlzx_yysqnew.do?method=yyjgManage");
		FormModleCommon.commonRequestSet(request);
		//������ҽҩ������һ�׸߼���ѯ����һ��У����ѯ����
		if("10026".equals(Base.xxdm)){
			request.setAttribute("path", "xlzx_yysqnew_zyy.do?method=yyjgManage");
		}
		String pbfs = new ZxspbService().getPbfs();
		request.setAttribute("pbfs",pbfs);
		return mapping.findForward("yyjgManage");
	}
	
	
	/** 
	 * ѧ��������Ϣ
	 */
	public ActionForward xspjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		ZxyyclService zxyyclSv = new ZxyyclService();
		HashMap<String, String> zxInfo  = zxyyclSv.getXlzxInfoByYyId(myForm.getId());
		request.setAttribute("zxInfo", StringUtils.formatData(zxInfo));
		return mapping.findForward("xspjInfo");
	}
	
	/** 
	 * ԤԼ�����Ƿ�Լ��
	 */
	public ActionForward getSfymFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String date = request.getParameter("date");
		String sfym="";

		ZxspbService zxspbSv = new ZxspbService();
		HashMap<String, String> zxspbInfo = zxspbSv.getPbInfoByDate(date);
		if(zxspbInfo==null || zxspbInfo.size()<=0){
			return null;
		}
		String _pbzgh = zxspbInfo.get("zgh");
		String[] pbzgh = _pbzgh.split(",");
		
		//�жϵ�ǰ���������Ѱ�����ѯʦ��ԤԼ�����Ƿ�����
		int count=0;//������ԤԼ������
		int kjdrs =0;//���տɽӴ�������
		for(int i=0;i<pbzgh.length;i++){
			List yysqInfo = service.getYysqByZghAnddDate(date,pbzgh[i]);
			if(!yysqInfo.isEmpty()){
				count +=yysqInfo.size();
			}
		}
		if(count!=0){
			//��ȡ���տ�ԤԼ����
			ZxsglService zxsglSv = new ZxsglService();
			List<HashMap<String,String>> zxsInfoList = zxsglSv.getZxsInfoByZgh(pbzgh);
			if(!zxsInfoList.isEmpty()){
				for(int j=0;j<zxsInfoList.size();j++){
					kjdrs += Integer.parseInt(zxsInfoList.get(j).get("kjdrs"));
				}
			}
			if(count>=kjdrs){
				sfym="Y";
			}
		}
		
		response.getWriter().print(sfym);
		return null;
	}
	
	
	/** 
	 * ԤԼ���ڶ�Ӧ����ѯʦ�Ű���Ϣ
	 */
	@SystemAuth(url = url)
	public ActionForward yysqDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglService zxsglSv = new ZxsglService();
		String date = request.getParameter("date");
		String doType = request.getParameter("doType");
		ZxspbService zxspbSv = new ZxspbService();
		HashMap<String, String> zxspbInfo = zxspbSv.getPbInfoByDate(date);
		List<HashMap<String,String>> zxsInfoList  = new ArrayList<HashMap<String,String>>();
		
		User user = getUser(request);
		String xh = user.getUserName();
		HashMap<String,String> yysqInfo = service.getYysqByXhAnddDate(date,xh);
		if(yysqInfo!=null && yysqInfo.size()>0){//�鿴ԤԼ��Ϣ
			zxsInfoList.add(zxsglSv.getZxsInfoByZgh(yysqInfo.get("zgh")));
			request.setAttribute("yysqInfo", StringUtils.formatData(yysqInfo));
		}else{//����ԤԼ��Ϣ
			if(zxspbInfo!=null && zxspbInfo.size()>0){
				String pbfs = zxspbSv.getPbfs();
				if(StringUtils.isNotNull(pbfs) || "1".equals(pbfs)){
					String _pbzgh = zxspbInfo.get("zgh");
					String[] pbzgh = _pbzgh.split(",");
					zxsInfoList = zxsglSv.getZxsInfoByZgh(pbzgh);
				}
				
			}
		}
		//������ѯʦ��ǰ���ڵ��ѽӴ�����
		if(zxsInfoList!=null && zxsInfoList.size()>0){
			for(int i=0;i<zxsInfoList.size();i++){
				String zgh = zxsInfoList.get(i).get("zgh");
				List zxsInfo = service.getYysqByZghAnddDate(date,zgh);
				int yjdrs=0;
				if(zxsInfo!=null && zxsInfo.size()>0){
					yjdrs = zxsInfo.size();
				}
				zxsInfoList.get(i).put("yjdrs", String.valueOf(yjdrs));
			}
		}
		String currxn=Base.currXn;
		String currxq=Base.getDqxqmc();
		request.setAttribute("currxn", currxn);
		request.setAttribute("currxq", currxq);
		request.setAttribute("zxspbInfo", StringUtils.formatData(zxspbInfo));
		request.setAttribute("zxsInfoList", zxsInfoList);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xlzx_yysqnew_yysqnew.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yysqDetail");
	}
	
	/** 
	 * ����ԤԼ������Ϣ
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			User user = getUser(request);
			HashMap<String,String> yysqInfo = service.getYysqByCn(user.getUserName(),model.getZgh(),model.getYyzxrq());
			String currxn=Base.currXn;
			String currxq=Base.getDqxqmc();
			request.setAttribute("currxn", currxn);
			request.setAttribute("currxq", currxq);
			yysqInfo.put("xh",  user.getUserName());
			yysqInfo.put("zgh",  model.getZgh());
			yysqInfo.put("yyzxrq",  model.getYyzxrq());
			//��ȡѧ����Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			yysqInfo.put("xstell",  xsjbxx.get("sjhm"));
			
			request.setAttribute("yysqInfo", yysqInfo);
			

			return mapping.findForward("addYysqInfo");
	}
	
	/** 
	 * ѡ��ԤԼ��ѯ����ѯʦ������Ա������
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward addYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm model = (YysqForm) form;
		HashMap<String, String> zxsInfo = new HashMap<String, String>();
		ZxsglService zxsSv = new ZxsglService();
		String pbfs = new ZxspbService().getPbfs();
		// ѡ��ѧ��
		String xh = StringUtil.isNull(request.getParameter("xh"))? model.getXh() : request.getParameter("xh");
		if(StringUtils.isNotNull(xh) ){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		User user = getUser(request);
		String zgh = user.getUserName();
		
		//����ְ�����ж��Ƿ�����ѯʦ����Ա
		boolean isZxsGly = new ZxsglyService().isZxsGly(zgh);
		
		// ��ѯʦ�����Ϣ
		zxsInfo = zxsSv.getZxsInfoByZgh(zgh);
		
		// ���䵽ѧԺ
		if(zxsInfo == null || zxsInfo.size()==0){
			 String msg = "����ѯʦû�иò���Ȩ�޵ģ���ȷ�ϣ�";
			 request.setAttribute("yhInfo", msg);
			 return new ActionForward("/yhInfo.do", false);
		}
		
		request.setAttribute("zxsInfo", zxsInfo);
		
		//ѧ��������Ϣ
		String path = "xlzx_yysqnew.do?method=addYyzxInfo";
		if(StringUtils.isNotNull(zxsInfo.get("zgh"))){
			path += "&zgh=" + zxsInfo.get("zgh");
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("pbfs",pbfs);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("today", GetTime.getTimeByFormat(DATE_FORMAT));
		request.setAttribute("isZxsGly", isZxsGly);
		return mapping.findForward("addYyzxInfo");
	}
	/** 
	 * ѡ��ԤԼ��ѯ����ѯʦ������Ա������
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YysqForm model = (YysqForm) form;
		HashMap<String, String> zxsInfo = new HashMap<String, String>();
		ZxsglService zxsSv = new ZxsglService();
		HashMap<String,String> yyzxInfo = service.getYyzxDetail(model.getId());
		String pbfs = new ZxspbService().getPbfs();
		// ѡ��ѧ��
		String xh = StringUtil.isNull(request.getParameter("xh"))? model.getXh() : request.getParameter("xh");
		if(StringUtils.isNotNull(xh) ){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		
//		User user = getUser(request);
//		String zgh = user.getUserName();
		
		String zgh = yyzxInfo.get("zgh");
		
		// ��ѯʦ�����Ϣ
		zxsInfo = zxsSv.getZxsInfoByZgh(zgh);
		
		// ���䵽ѧԺ
		if(zxsInfo == null || zxsInfo.size()==0){
			String msg = "����ѯʦû�иò���Ȩ�ޣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("zxsInfo", StringUtils.formatData(zxsInfo));
		
		//ѧ��������Ϣ
		String path = "xlzx_yysqnew.do?method=updateYyzxInfo";
		if(StringUtils.isNotNull(zxsInfo.get("zgh"))){
			path += "&zgh=" + zxsInfo.get("zgh");
		}
		//ѧ��������Ϣ��ʾ����
		//TODO maker ��ѧУ�����ж�ȥ�����޴�	&& "10026".equals(Base.xxdm)
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs) ){
			request.setAttribute("sjddmList", new com.zfsoft.xgxt.xlzx.yysq.YysqService().getXsYySjd(yyzxInfo.get("zxrq"), zgh, xh));
			request.setAttribute("xqmc",new ZxspbService().getXqmc(yyzxInfo.get("zxrq"), zgh));
		}
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("pbfs", pbfs);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		request.setAttribute("today", GetTime.getTimeByFormat(DATE_FORMAT));

		//�ҳ���д��Ϣ
		if("jz".equals(yyzxInfo.get("yyfs"))){
			HashMap<String,String> jzxx = service.getJzxx(yyzxInfo.get("id"));
			request.setAttribute("jzxx", StringUtils.formatData(jzxx));
		}

		return mapping.findForward("updateYyzxInfo");
	}
	/** 
	 * ���ķ������
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyjgManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateYyjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YysqForm model = (YysqForm) form;
		HashMap<String,String> yyzxInfo = service.getYyzxDetail(model.getId());
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		return mapping.findForward("updateYyjg");
	}
	/** 
	 * ����ԤԼ������Ϣ
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			try {
				boolean flag = service.saveYysqInfo(model);
				response.getWriter().print(flag);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return null;
	}
	
	/**
	 * ɾ��ԤԼ��ѯ��Ϣ(��ʦ׷�ӵ���ѯ��δ��ѯ��)
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward delYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// �h����Ո
			String[] ids = values.split(",");
			int count = service.delYyzxInfo(ids);
			String message = "";
			if(count > 0){
				message = "�ɹ�ɾ����<font color='green'>&nbsp;"+count+"&nbsp;</font>������";

			}else{
				message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			}
			response.getWriter().print(getJsonMessage(message));
			
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/** 
	 * �޸�ԤԼ������Ϣ
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyfkManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			try {
				boolean flag = service.updateYysqInfo(model);
				
				// ���³ɹ��� 4ԤԼ�ɹ�(ѧ��ȡ��)��ɾ����ѯ
				if(flag && "4".equals(model.getStatus())){
					ZxyyclService zxyyclService = new ZxyyclService();
					String[] yyids = new String[]{model.getId()};
					int count = zxyyclService.delZxInfoByYyid(yyids);
					if(count>0){
						flag = true;
					}else{
						flag = false;
					}
				}

				response.getWriter().print(flag);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			return null;
	}

	/**
	 * ����
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YysqForm model = (YysqForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoListAll(model, user);

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(YysqInfoList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	/**
	 * ����
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyjgManage")
	public ActionForward exportDataJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		YysqForm model = (YysqForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoListAllJg(model, user);
		
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(YysqInfoList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @����:ǩ��״̬
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/12/28 16:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward qdztwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		YysqForm model = (YysqForm) form;
		YysqForm t = service.getModel(model.getId());
		if(t != null){
			BeanUtils.copyProperties(model,t);
		}
		request.setAttribute("qdztbz",model.getQdztbz());
		request.setAttribute("nowTime",GetTime.getTimeByFormat("yyyy-MM-dd HH:mm:ss"));
		return mapping.findForward("qdztwh");
	}

	/**
	 * @����:ǩ��״̬����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/12/28 16:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward saveQdzt(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)throws Exception {
		YysqForm model = (YysqForm) form;
		boolean result = service.updateQdzt(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
