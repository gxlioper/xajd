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
 * 心理咨询管理
 */

public class YysqAction extends SuperAction{

	private static YysqService service = new YysqService();
	/**
	 * 定义心理咨询预约申请可以从基本信息表中获取学生信息
	 */
	private static final String XLJKYYSQ = "xljkyysq";
	private List<HashMap<String,String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();
	
	private static final String url = "xlzx_yysqnew.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/** 
	 * 预约咨询反馈查询页面
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyfkManage")
	public ActionForward yyfkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
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
	 * 预约咨询反馈
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
		// 有学号信息
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			ZxzxjlService zxzxjlService = new ZxzxjlService();
			ZxzxjlModel zxzxjlModel = new ZxzxjlModel();
			zxzxjlModel.setXh(yyzxInfo.get("xh"));
			zxzxjlModel = zxzxjlService.getModel(zxzxjlModel);
			request.setAttribute("zxzxjlModel", zxzxjlModel);

			//学生填写信息
			XstxxxForm xstxxxForm = XstxxxAction.getModel(yyzxInfo.get("xh"));
			request.setAttribute("xstxxx", xstxxxForm);
		}

		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("yyfkDetail");
	}
	/** 
	 * 咨询反馈
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
		// 有学号信息
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		//咨询历史信息
		List<HashMap<String, String>> zxlsxxList = service.getZxlsxxList(yyzxInfo.get("xh"), id);
		request.setAttribute("zxlsxxList", zxlsxxList);
		List<HashMap<String,String>> haveList = new OptionUtil().getOptions(OptionUtil.HAVENOT);
		request.setAttribute("haveList", haveList);
		List<HashMap<String,String>> isNotList = new OptionUtil().getOptions(OptionUtil.ISNOT);
		request.setAttribute("isNotList", isNotList);

		//咨询问题类型列表
		if("10704".equals(Base.xxdm)){
			List<HashMap<String, String>> zxwtlxList = service.getZxwtlxList();
			request.setAttribute("zxwtlxList", zxwtlxList);
		}

		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs) && "10026".equals(Base.xxdm)){
			request.setAttribute("xqmc", new ZxspbService().getXqmc(yyzxInfo.get("zxrq"), yyzxInfo.get("apzxs")));
		}
		request.setAttribute("pbfs",pbfs);
		request.setAttribute("xxdm",Base.xxdm);

		//家长信息
		HashMap<String, String> jzxx = service.getJzxx(id);
		request.setAttribute("jzxx", StringUtils.formatData(jzxx));
		//学生填写信息
		XstxxxForm xstxxxForm = XstxxxAction.getModel(yyzxInfo.get("xh"));
		request.setAttribute("xstxxx", xstxxxForm);

		return mapping.findForward("zxfkDetail");
	}
	/** 
	 * 咨询反馈
	 */
	
	public ActionForward zxfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		HashMap<String, String> yyzxInfo = service.getYyzxDetail(id);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		String pbfs = new ZxspbService().getPbfs();
		User user = getUser(request);
		// 有学号信息
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		//咨询历史信息
		List<HashMap<String, String>> zxlsxxList = service.getZxlsxxList(yyzxInfo.get("xh"), id);
		request.setAttribute("zxlsxxList", zxlsxxList);
		request.setAttribute("pbfs", pbfs);
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs) && "10026".equals(Base.xxdm)){
			request.setAttribute("xqmc", new ZxspbService().getXqmc(yyzxInfo.get("zxrq"), yyzxInfo.get("apzxs")));
		}
		//家长信息
		HashMap<String, String> jzxx = service.getJzxx(id);
		request.setAttribute("jzxx", StringUtils.formatData(jzxx));
		//学生填写信息
		XstxxxForm xstxxxForm = XstxxxAction.getModel(yyzxInfo.get("xh"));
		request.setAttribute("xstxxx", xstxxxForm);
		return mapping.findForward("zxfkView");
	}
	/** 
	 * 咨询反馈
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyjgManage")
	public ActionForward yyjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = request.getParameter("id");
		HashMap<String, String> yyzxInfo = service.getYyzxDetail(id);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		User user = getUser(request);
		// 有学号信息
		if(yyzxInfo!=null && yyzxInfo.size()>0 && StringUtils.isNotNull(yyzxInfo.get("xh"))){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(yyzxInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("jbxxList", jbxxList);
		//咨询历史信息
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
	 * 学生预约申请查询页面、预约咨询处理查询页面
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
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
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
	 * 预约结果
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
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoListJg(myForm, user);
			
			JSONArray dataList = JSONArray.fromObject(YysqInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "xlzx_yysqnew.do?method=yyjgManage");
		FormModleCommon.commonRequestSet(request);
		//北京中医药走另外一套高级查询，多一个校区查询条件
		if("10026".equals(Base.xxdm)){
			request.setAttribute("path", "xlzx_yysqnew_zyy.do?method=yyjgManage");
		}
		String pbfs = new ZxspbService().getPbfs();
		request.setAttribute("pbfs",pbfs);
		return mapping.findForward("yyjgManage");
	}
	
	
	/** 
	 * 学生评价信息
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
	 * 预约日期是否约满
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
		
		//判断当前日期所有已安排咨询师的预约人数是否已满
		int count=0;//当日已预约总人数
		int kjdrs =0;//当日可接待总人数
		for(int i=0;i<pbzgh.length;i++){
			List yysqInfo = service.getYysqByZghAnddDate(date,pbzgh[i]);
			if(!yysqInfo.isEmpty()){
				count +=yysqInfo.size();
			}
		}
		if(count!=0){
			//获取当日可预约人数
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
	 * 预约日期对应的咨询师排班信息
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
		if(yysqInfo!=null && yysqInfo.size()>0){//查看预约信息
			zxsInfoList.add(zxsglSv.getZxsInfoByZgh(yysqInfo.get("zgh")));
			request.setAttribute("yysqInfo", StringUtils.formatData(yysqInfo));
		}else{//新增预约信息
			if(zxspbInfo!=null && zxspbInfo.size()>0){
				String pbfs = zxspbSv.getPbfs();
				if(StringUtils.isNotNull(pbfs) || "1".equals(pbfs)){
					String _pbzgh = zxspbInfo.get("zgh");
					String[] pbzgh = _pbzgh.split(",");
					zxsInfoList = zxsglSv.getZxsInfoByZgh(pbzgh);
				}
				
			}
		}
		//设置咨询师当前日期的已接待人数
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
	 * 新增预约申请信息
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
			//获取学生信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			yysqInfo.put("xstell",  xsjbxx.get("sjhm"));
			
			request.setAttribute("yysqInfo", yysqInfo);
			

			return mapping.findForward("addYysqInfo");
	}
	
	/** 
	 * 选择预约咨询（咨询师、管理员操作）
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward addYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm model = (YysqForm) form;
		HashMap<String, String> zxsInfo = new HashMap<String, String>();
		ZxsglService zxsSv = new ZxsglService();
		String pbfs = new ZxspbService().getPbfs();
		// 选择学生
		String xh = StringUtil.isNull(request.getParameter("xh"))? model.getXh() : request.getParameter("xh");
		if(StringUtils.isNotNull(xh) ){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		User user = getUser(request);
		String zgh = user.getUserName();
		
		//根据职工号判断是否是咨询师管理员
		boolean isZxsGly = new ZxsglyService().isZxsGly(zgh);
		
		// 咨询师相关信息
		zxsInfo = zxsSv.getZxsInfoByZgh(zgh);
		
		// 分配到学院
		if(zxsInfo == null || zxsInfo.size()==0){
			 String msg = "非咨询师没有该操作权限的，请确认！";
			 request.setAttribute("yhInfo", msg);
			 return new ActionForward("/yhInfo.do", false);
		}
		
		request.setAttribute("zxsInfo", zxsInfo);
		
		//学生基本信息
		String path = "xlzx_yysqnew.do?method=addYyzxInfo";
		if(StringUtils.isNotNull(zxsInfo.get("zgh"))){
			path += "&zgh=" + zxsInfo.get("zgh");
		}
		//学生基本信息显示配置
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
	 * 选择预约咨询（咨询师、管理员操作）
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
		// 选择学生
		String xh = StringUtil.isNull(request.getParameter("xh"))? model.getXh() : request.getParameter("xh");
		if(StringUtils.isNotNull(xh) ){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		
//		User user = getUser(request);
//		String zgh = user.getUserName();
		
		String zgh = yyzxInfo.get("zgh");
		
		// 咨询师相关信息
		zxsInfo = zxsSv.getZxsInfoByZgh(zgh);
		
		// 分配到学院
		if(zxsInfo == null || zxsInfo.size()==0){
			String msg = "非咨询师没有该操作权限，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("zxsInfo", StringUtils.formatData(zxsInfo));
		
		//学生基本信息
		String path = "xlzx_yysqnew.do?method=updateYyzxInfo";
		if(StringUtils.isNotNull(zxsInfo.get("zgh"))){
			path += "&zgh=" + zxsInfo.get("zgh");
		}
		//学生基本信息显示配置
		//TODO maker 将学校代码判断去掉就无错	&& "10026".equals(Base.xxdm)
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

		//家长填写信息
		if("jz".equals(yyzxInfo.get("yyfs"))){
			HashMap<String,String> jzxx = service.getJzxx(yyzxInfo.get("id"));
			request.setAttribute("jzxx", StringUtils.formatData(jzxx));
		}

		return mapping.findForward("updateYyzxInfo");
	}
	/** 
	 * 中心反馈意见
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
	 * 新增预约申请信息
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
	 * 删除预约咨询信息(老师追加的咨询且未咨询的)
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward delYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 刪除申請
			String[] ids = values.split(",");
			int count = service.delYyzxInfo(ids);
			String message = "";
			if(count > 0){
				message = "成功删除了<font color='green'>&nbsp;"+count+"&nbsp;</font>条数据";

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
	 * 修改预约申请信息
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyfkManage",rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			try {
				boolean flag = service.updateYysqInfo(model);
				
				// 更新成功后 4预约成功(学生取消)则删除咨询
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
	 * 导出
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yysqManage")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YysqForm model = (YysqForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoListAll(model, user);

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(YysqInfoList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	/**
	 * 导出
	 */
	@SystemAuth(url = "xlzx_yysqnew.do?method=yyjgManage")
	public ActionForward exportDataJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		YysqForm model = (YysqForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoListAllJg(model, user);
		
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(YysqInfoList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述:签到状态
	 * @作者：lgx [工号：1553]
	 * @日期：2018/12/28 16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
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
	 * @描述:签到状态保存
	 * @作者：lgx [工号：1553]
	 * @日期：2018/12/28 16:42
	 * @修改记录: 修改者名字-修改日期-修改内容
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
