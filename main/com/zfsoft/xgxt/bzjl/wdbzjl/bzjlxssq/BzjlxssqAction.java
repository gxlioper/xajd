/**
 * @部门:学工产品事业部
 * @日期：2013-12-9 上午11:22:37 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-9 上午11:22:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BzjlxssqAction extends SuperAction {
	// 是否班级评议.1：是，0：否
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));
	private static final String template_dir = "classpath://templates//pjpy";//模版路径
	private static final String default_template = "default.xml";//默认模版
	
	private static final String url = "bzjl_xssq.do";
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-9 上午11:52:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
			String msg = "该模块仅允许学生用户访问，请确认！";
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
		
		
		//查看是否在参评组内
		
		boolean isExistCpz = service.isExistCpz(csszModel,user.getUserName());
		
		if(!isExistCpz){
			
			String msg = "您不在本次参评组内，请确认！";
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午02:57:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
			//学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		this.saveToken(request);
		return mapping.findForward("xmsqView");
		
	}
	
	/**
	 * 
	 * @描述:奖项申请保存
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午05:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请（学生）-保存申请-XMDMS:{xmdms}")
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午06:23:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
			
			//学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//学生参评班级
			ZcfsService zcfsService = new ZcfsService();
			HashMap<String, String> cpbjxx = zcfsService.getCpbjListByXh(model.getXh(), model.getXn(), model.getXq());
			request.setAttribute("cpbjxx", cpbjxx);
			
			//评奖项目信息
			XmwhService xmwhService = new XmwhService();
			XmwhModel xmwhModel = xmwhService.getModel(model.getXmdm());
			request.setAttribute("xmwhModel", xmwhModel);
			
			request.setAttribute("pjzqmc", csszService.getPjzq().get("pjzqmc"));
			//中国美术学院
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午06:17:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请（学生）-保存修改-ID:{id}")
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
	 * @描述:撤销申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-14 下午04:46:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-我的评奖-奖项申请（学生）-撤销-VALUES:{values}")
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午07:09:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewLcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		BzjlxssqModel modelForm = (BzjlxssqModel) form;
		BzjlxssqService service = new BzjlxssqService();
		
		//单个查询
		BzjlxssqModel model = service.getModel(modelForm);
		
		if (model != null){
			BeanUtils.copyProperties(modelForm, model);
		}
		
		//审批记录
		List<HashMap<String,String>> spjlList = service.getSpjlList(modelForm.getSqid());
		request.setAttribute("spjlList", spjlList);
		request.setAttribute("spjlsize", spjlList.size());
		
		//审批岗位
		List<HashMap<String,String>> spgwList = ShlcUtil.getSpbzBySplc(model.getSplc());
		request.setAttribute("spgwList", spgwList);
		
		//待审核岗位
		request.setAttribute("dshGwid", service.getDsgw(modelForm.getSqid()));
		return mapping.findForward("viewLcgz");
	}
	

	/**
	 * 
	 * @描述:登记表批量打印
	 * @作者：陶钢军[工号：1075]
	 * @日期：2016-4-21 下午03:51:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
	 * @描述:登记表下载
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-11 上午10:33:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
	
	
	
	//填充模版数据生成word文件
	private File getWord(BzjlxssqModel pjxmsqModel)
			throws Exception {
		BzjljgService servicePjPy = new BzjljgService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		BzjlxssqModel model = pjxmsqModel;
		HashMap<String, String> bbMap = null;// 报表

		if (model != null){
			
			String dqxmdm = model.getDqxmdm();//调整后项目代码
			
			if(StringUtils.isNull(dqxmdm)){
				throw new SystemException("当前项目代码为空，不允许下载登记表。");
			}
			
			if(!StringUtil.isNull(model.getXmmc())){
				XmwhService xmwhService = new XmwhService();
				HashMap<String, String> xmMap = xmwhService.getDataByName(model.getXmmc(), model.getXn(), model.getXq());//查询项目记录
				if(xmMap != null){
					model.setXmdm(xmMap.get("xmdm"));//项目代码
					BbwhService bbwhService = new BbwhService();
					bbMap = bbwhService.getDataById(xmMap.get("dybb"));//查询对应表格 
				}
			}
			if(bbMap == null || bbMap.size() == 0){
				//查询不到相关联报表信息
				throw new SystemException(MessageKey.PJPY_BBDY_FAIL);
			}
			
			String xh = model.getXh();
			
			//基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			BzjljgService service = new BzjljgService();
			if("10355".equals(Base.xxdm)){
				//由于word里有学院字样，去掉xymc里最后2个字
				String xymc = xsjbxx.get("xymc").substring(0,xsjbxx.get("xymc").length() -2);
				data.put("xymc", xymc);
				
				xsjbxx.put("csny",xsjbxx.get("csrq"));
				// 分解身份证号
				String sfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = sfzh.length(); i < j; i++) {
					data.put("sfzh" + i, String.valueOf(sfzh.charAt(i)));
				}
				data.put("xsjbxx", xsjbxx);
			}
			ZcfsService zcfService = new ZcfsService();
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));
			data.put("currY", DateUtils.getYear());//当前年
			data.put("currM", DateUtils.getMonth());//当前月
			data.put("currD",DateUtils.getDayOfMonth());//当前日
			WjcfCfsbService wjcfcfsbService =  new WjcfCfsbService();
			List<HashMap<String , String>> yscfqk = wjcfcfsbService.getYscfqk(xh);
			BzjlxssqService pjxmsqService = new BzjlxssqService();
			//审核意见
			List<HashMap<String, String>> shyjList = pjxmsqService.getAllShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
			//黑龙江农垦职业学院个性化
			if("12727".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				// ========== 本学年成绩相关 begin ============
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "德育");
				String xndypjcj = bxncjMap.get("xndypjcj");
				data.put("xndypjcj", xndypjcj);
				data.put("sxqdycj", bxncjMap.get("sxqdycj"));
				data.put("xxqdycj", bxncjMap.get("xxqdycj"));
				
				CpmdService cpmdService = new CpmdService();
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // 本学年班级人数
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // 本学年成绩班级排名
				
				List<HashMap<String,String>> xnjxj_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "必修");
				servicePjPy.addBlankList(xnjxj_cjSxqBxList, 16 - xnjxj_cjSxqBxList.size());
				data.put("xnjxj_cjSxqBxList", xnjxj_cjSxqBxList); // 本学年上学期必修课成绩
				List<HashMap<String,String>> xnjxj_cjSxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "选修");
				servicePjPy.addBlankList(xnjxj_cjSxqXxList, 6 - xnjxj_cjSxqXxList.size());
				data.put("xnjxj_cjSxqXxList", xnjxj_cjSxqXxList); // 本学年上学期选修课成绩
				List<HashMap<String,String>> xnjxj_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "必修");
				servicePjPy.addBlankList(xnjxj_cjXxqBxList, 16 - xnjxj_cjXxqBxList.size());
				data.put("xnjxj_cjXxqBxList", xnjxj_cjXxqBxList); // 本学年下学期必修课成绩
				List<HashMap<String,String>> xnjxj_cjXxqXxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "选修");
				servicePjPy.addBlankList(xnjxj_cjXxqXxList, 6 - xnjxj_cjXxqXxList.size());
				data.put("xnjxj_cjXxqXxList", xnjxj_cjXxqXxList); // 本学年下学期选修课成绩
				
				List<HashMap<String,String>> xnjxj_cjSxqSum = xnjxj_cjSxqBxList;
				xnjxj_cjSxqSum.addAll(xnjxj_cjSxqXxList);
				String xnjxj_cjSxqPjf = service.getPjf(xnjxj_cjSxqSum, 2); // 本学年上学期平均成绩
				data.put("xnjxj_cjSxqPjf", xnjxj_cjSxqPjf);
				List<HashMap<String,String>> xnjxj_cjXxqSum = xnjxj_cjXxqBxList;
				xnjxj_cjXxqSum.addAll(xnjxj_cjXxqXxList);
				String xnjxj_cjXxqPjf = service.getPjf(xnjxj_cjXxqSum, 2); // 本学年下学期平均成绩
				data.put("xnjxj_cjXxqPjf", xnjxj_cjXxqPjf);
				String xnjxj_cjXnPjf = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_cjXnPjf = service.getPjf(new String[]{ xnjxj_cjSxqPjf, xnjxj_cjXxqPjf }, 2); // 本学年平均成绩
				}
				data.put("xnjxj_cjXnPjf", xnjxj_cjXnPjf);
				// 学生奖学金评定综合成绩 = 学年学习平均成绩（70％）＋学年德育考核平均成绩（30％）
				BigDecimal xnjxj_cjXnPjf_BD = StringUtils.isNotNull(xnjxj_cjXnPjf) ? new BigDecimal(xnjxj_cjXnPjf) : new BigDecimal("0"); 
				BigDecimal xnjxj_dypjcj_BD = StringUtils.isNotNull(xndypjcj) ? new BigDecimal(xndypjcj) : new BigDecimal("0"); 
				String xnjxj_xsjxjpdzhcj = "";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_xsjxjpdzhcj = xnjxj_cjXnPjf_BD.multiply(new BigDecimal("0.7")).add(xnjxj_dypjcj_BD.multiply(new BigDecimal("0.3"))).divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP).toString();
				}
				data.put("xnjxj_xsjxjpdzhcj", xnjxj_xsjxjpdzhcj);
				// 本学年有无不及格课程
				String xnjxj_bjgXn = "blank";
				if(StringUtils.isNotNull(xnjxj_cjSxqPjf)){
					xnjxj_bjgXn = service.getBjgcjNum(xh, model.getXn(), "");
				}
				data.put("xnjxj_bjgXn",xnjxj_bjgXn );
				// ========== 本学年成绩相关 end ============
				// ========== 历年成绩相关 begin ============
				String xnTemp = model.getXn().substring(0,4);
				String diXn = String.valueOf(Integer.parseInt(xnTemp)-2) + "-" + String.valueOf(Integer.parseInt(xnTemp)-1); // 第一学年
				String deXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + xnTemp; // 第二学年
				HashMap<String,String> bxncjMap_diXn = servicePjPy.getDjbZcfListByXhXn(xh, diXn, "德育");
				// 第一学年德育考核平均成绩
				String xndypjcj_diXn = "";
				if(StringUtils.isNotNull(bxncjMap_diXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_diXn.get("xxqdycj"))){
					xndypjcj_diXn = service.getPjf(new String[]{ bxncjMap_diXn.get("sxqdycj"), bxncjMap_diXn.get("xxqdycj") }, 2);
				}
				HashMap<String,String> bxncjMap_deXn = servicePjPy.getDjbZcfListByXhXn(xh, deXn, "德育");
				// 第二学年德育考核平均成绩
				String xndypjcj_deXn = "";
				if(StringUtils.isNotNull(bxncjMap_deXn.get("sxqdycj")) || 
						StringUtils.isNotNull(bxncjMap_deXn.get("xxqdycj"))){
					xndypjcj_deXn = service.getPjf(new String[]{ bxncjMap_deXn.get("sxqdycj"), bxncjMap_deXn.get("xxqdycj") }, 2);
				}
				// 前两学年德育考核总平均成绩
				String xndypjcj_qlXn = "";
				if(StringUtils.isNotNull(xndypjcj_diXn) || 
						StringUtils.isNotNull(xndypjcj_deXn)){
					xndypjcj_qlXn = service.getPjf(new String[]{ xndypjcj_diXn, xndypjcj_deXn }, 2);
				}
				data.put("xndypjcj_diXn", xndypjcj_diXn);
				data.put("xndypjcj_deXn", xndypjcj_deXn);
				data.put("xndypjcj_qlXn", xndypjcj_qlXn);
				// ========== 历年成绩相关 end ============
				// ========== 获奖相关 begin ============
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				String bxnxnjxj = ""; // 本学年校内奖学金
				String bxngjjxj = ""; // 本学年国家奖学金
				String bxngjlzjxj = ""; // 本学年国家励志奖学金
				String dixnxnjxj = ""; // 第一学年校内奖学金
				String dixngjjxj = ""; // 第一学年国家奖学金
				String dexnxnjxj = ""; // 第二学年校内奖学金
				String dexngjjxj = ""; // 第二学年国家奖学金
				String dixnxn1 = ""; // 第一学年校内XXX
				String dixnxn2 = ""; // 第一学年校内XXX
				String dixnsj = ""; // 第一学年省级XXX
				String dexnxn1 = ""; // 第二学年校内XXX
				String dexnxn2 = ""; // 第二学年校内XXX
				String dexnsj = ""; // 第二学年省级XXX
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					String xn = pj.get("xn");
					if(xmmc.contains("等校内奖学金")){
						if(model.getXn().equals(xn)){
							bxnxnjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixnxnjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexnxnjxj = xmmc;
						}
					}else if(xmmc.contains("国家奖学金")){
						if(model.getXn().equals(xn)){
							bxngjjxj = xmmc;
						}else if(diXn.equals(xn)){
							dixngjjxj = xmmc;
						}else if(deXn.equals(xn)){
							dexngjjxj = xmmc;
						}
					}else if(xmmc.contains("国家励志奖学金") && model.getXn().equals(xn)){
						bxngjlzjxj = xmmc;
					}else if(xmmc.startsWith("校内")){
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
					}else if(xmmc.startsWith("省级")){
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
				
				String xnjxj_hjxjdj = model.getXmmc().contains("等奖学金") ? model.getXmmc() : ""; // （校内奖学金评审表）获奖学金等级
				data.put("xnjxj_hjxjdj", xnjxj_hjxjdj);
				// ========== 获奖相关 end ============
				String yxtgpsbXmmc = "";
				if("yxtgpsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "优秀团干";
				}else if("yxtypsb_12727".equals(bbMap.get("bbdm"))){
					yxtgpsbXmmc = "优秀团员";
				}
				data.put("bbMapBbdm", bbMap.get("bbdm"));
				data.put("yxtgpsbXmmc", yxtgpsbXmmc);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
				//获取不及格成绩数量
				String bjgcjs=service.getBjgcjNum(xh, model.getXn(), "");
				// 有无违纪处分
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ // 处分挂科
					xsjbxx.put("ywcfgk", "无");
				}else{
					xsjbxx.put("ywcfgk", "有");
				}
				if(StringUtils.isNull(bjgcjs)){ // 本学年有无不及格课程
					xsjbxx.put("bxnywbjgkc", "无");
				}else{
					xsjbxx.put("bxnywbjgkc", "有");
				}
				DwwhService dwwhService = new DwwhService();
				List<HashMap<String , String>> bxnxsgbzwList = dwwhService.getZwForXhXn(xh, model.getXn()); // 学生干部职务任期时间
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
				
				// 家庭情况
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
				if(jthk != null && jthk.contains("城镇")){
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// 家庭成员
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
			}
			//浙江警官职业学院
			if("12869".equals(Base.xxdm)) {
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				/*打印日期*/
				data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("中国共产党党员")) {
					data.put("zzmmmc", "中共党员");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
				List<HashMap<String, String>> pjjg =  service.getPjpyInfoMapForDjb(xh);
				data.put("pjjg", pjjg);
			}
			//中央民族大学
			if("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				// 寝室
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
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// 出生日期
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
				// 家庭成员
				JtqkdcService jtqkService = new JtqkdcService();
				List<HashMap<String, String>> jtcyxxList_zymzdx = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_zymzdx, 5 - jtcyxxList_zymzdx.size());
				data.put("jtcyxxList_zymzdx", jtcyxxList_zymzdx);
				// 项目学年学期必修课成绩
				List<HashMap<String,String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "必修");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList); 
				String xmXnxqBxCjPjf = service.getPjf(xmXnxqBxCjList, 2); // 平均成绩
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				//========== 获取上一学年成绩排名、总人数、必修课及及格数 begin ============
				String xnTemp = Base.currXn.substring(0,4);
				String beforXn = String.valueOf(Integer.parseInt(xnTemp)-1) + "-" + String.valueOf(Integer.parseInt(xnTemp)); // 上学年
					//必修课及排名
				CpmdService cpmdService = new CpmdService();
				String bjrsForZymzdx = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(beforXn, xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(beforXn, xh);
				String bxkjgs = zcfService.getBxkjgs(beforXn, xh);
				data.put("bjrsForZymzdx", bjrsForZymzdx);// 班级人数
				data.put("cjpmForZymzdx", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// 参评人数
				data.put("cpzrsForZymzdx", cpzrs);
				//========== 获取上一学年成绩排名、总人数、必修课及及格数 end ============
				
				// 党团建设
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if(StringUtils.isNotNull(zzmmmc)){
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if((zzmmmc.contains("团员") && "02".equals(jddm))
							|| (zzmmmc.contains("预备党员") && "09".equals(jddm))
							|| (zzmmmc.contains("党员") && !zzmmmc.contains("预备党员") && "11".equals(jddm))){
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj,FomartDateType.day));
				// 已获奖项
				StringBuffer yhjxBuffer = new StringBuffer();
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("、");
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
				data.put("rddcmc", knsjg.get("dcmc"));//认定档次
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("品行")){
						data.put("pdfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("学业")){
						data.put("xyfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("文体")){
						data.put("wtfs", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("综测")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
					if(xmmc.contains("平均成绩")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
					}
				}
				/*获取第一级审核意见*/
				data.put("shyj1", new CommShlcImpl().getShyjListByYwid(model.getSqid()).get(0).get("shyj"));
				data.put("shyj2", new CommShlcImpl().getShyjListByYwid(model.getSqid()).get(1).get("shyj"));
				data.put("shyj3", new CommShlcImpl().getShyjListByYwid(model.getSqid()).get(2).get("shyj"));
			}
			
			//涪陵师范学院个性化
			if("10647".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // 评奖年度
				data.put("njzwmc", xsjbxx.get("nj").substring(2, 4) + "级"); // 评奖年度
				// ============ 计算年龄 begin==========
				String nl = "";
				//1994-12-24 19920217 两格式
				String csrq = xsjbxx.get("csrq");//获取出生日期
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				// ============ 计算年龄 end==========
				// ========== 获得校级优秀毕业生荣誉称号的时间 begin ============
				StringBuffer xjyxbysHjsjBuffer = new StringBuffer();
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if(xmmc.contains("校级优秀毕业生")){
						xjyxbysHjsjBuffer.append(pj.get("sqsjs")).append("，");
					}
				}
				String xjyxbysHjsj = xjyxbysHjsjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("xjyxbysHjsj", xjyxbysHjsj);
				// ========== 获得校级优秀毕业生荣誉称号的时间 end ============
				// ========== 获奖情况个性化 begin ============
				List<HashMap<String, String>> pjjgList =  pjjgService.getPjjgGroupByXn(xh);
				if(pjjgList.size() == 0){
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				// ========== 获奖情况个性化 end ============
			}
			//辽宁美术职业学院
			if("13957".equals(Base.xxdm)) {
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
			}
			//潍坊学院个性化
			if("11067".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxny = xsjbxx.get("rxny") == null ? "" : xsjbxx.get("rxny");
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				xsjbxx.put("rxny_num", rxny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				// 分解身份证号begin
				String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
//				// ===============定制申请理由 begin===============
//				// ===========山东省非师范类优秀毕业生评审表、山东省师范类优秀毕业生评审表 begin==========
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
//				// ===========山东省非师范类优秀毕业生评审表、山东省师范类优秀毕业生评审表 end==========
//				// ===========山东省高等学校优秀学生登记表、山东省高等学校优秀学生干部登记表 begin==========
//				HashMap<String, String> sqly_11067_map = new HashMap<String, String>();
//				if("sdsgdxxyxxsgb_11067".equals(bbdm) || "sdsgdxxyxxs_11067".equals(bbdm)){
//					int rows = 12; // 行数
//					int words = 35; // 每行最大字数
//					sqly_11067_max = 420;
//					String sqly_11067_d2y = ""; // 第二页
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
//				// ===========山东省高等学校优秀学生登记表、山东省高等学校优秀学生干部登记表 end==========
//				// ===============定制申请理由 end===============
				// 通过学号查询综测分数
				List<HashMap<String,String>> zcfNList = zcfService.getZcfsNList(xh);
				data.put("zcfNList_11067", zcfNList);
			}
			
			// 担任职务
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			
			
			//学生照片
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);

			
			//陕西师范大学
			if("10718".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期

				// 分解身份证号begin
				String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
			}

			
			//成都体育学院个性化
			if("10653".equals(Base.xxdm)){
				String nl = "";
				//计算年龄
				//1994-12-24 19920217 两格式
				String csrq = xsjbxx.get("csrq");//获取出生日期
				
				if(StringUtils.isNotNull(csrq)){
					String[] yearAndMonth = DateUtils.calYears(csrq);
					if(yearAndMonth[0] != null)
						nl = yearAndMonth[0];
				}
				data.put("nl", nl);
				String[] code = new String[]{"01","02","03"}; //党团员代码 中国共产党01 预备02 团员03
				//判断是否是党团员
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&(ArrayUtil.contains(code, xsjbxx.get("zzmm")))){
					data.put("sfsdty", "是");
				}else{
					data.put("sfsdty", "否");
				}
				
				//获取同年级同专业人数
				String zyrs = servicePjPy.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生年月
				
				String[] shyj = new String[]{"","","",""};
				for (int i = 0; i < shyjList.size(); i++) {
					shyj[i] = shyjList.get(i).get("shyj");
				}
				data.put("shyj1", shyj[0]);
				data.put("shyj2", shyj[1]);
				data.put("shyj3", shyj[2]);
				data.put("shyj4", shyj[3]);
				
				//家庭成员信息
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList_10653 = xsxxglService.getJtcyxxXsList(xh);
				
				data.put("jtcyxxList_10653", jtcyxxList_10653);
				int size=(3 - jtcyxxList_10653.size())<0?0:(3 - jtcyxxList_10653.size());
				data.put("blankList_10653", getBlankList(size));
				
				//困难生认定档次名称
				KnsjgService knsjgService = new KnsjgService();
				HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
				data.put("dcmc", knsjg.get("dcmc")==null?"":knsjg.get("dcmc"));
				
			}
			//广西医科大学个性化多张报表
			if("10598".equals(Base.xxdm)){
				if("‘优利特’优秀学生干部奖学金".equals(model.getXmmc()) 
						|| "邓洁彬奖学金".equals(model.getXmmc())
						|| "荣和教育奖学金".equals(model.getXmmc())
						|| "三好学生".equals(model.getXmmc()) 
						|| "优秀学生干部".equals(model.getXmmc())
				){
					//项目类型名称
					PjdmModel pjdmModel = new PjdmModel();
					pjdmModel.setXmlxdm(model.getLxdm());
					PjdmService pjdmService = new PjdmService();
					PjdmModel xmlxModel = pjdmService.getModel(pjdmModel);
					model.setLxdmmc(xmlxModel.getXmlxmc());
				}
			}
			//测试使用
			//xh="3060601025";
			HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
			
			//按照学号加载学生学年成绩
			List<HashMap<String,String>> cjList = xsxxService.getCjList(xh,model.getXn(),model.getXq());
			//获取平均分数
			String pjfs=servicePjPy.getAverage(cjList);
			xsjbxx.put("pjfs",pjfs );
			
			//获取不及格成绩数量
			String bjgcjs=servicePjPy.getBjgcjNum(xh, model.getXn(), model.getXq());
			xsjbxx.put("bjgcjs",bjgcjs );
			
			//浙江大学宁波理工学院个性化
			if("13022".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				// 有无不及格课程
				if(StringUtils.isNull(bjgcjs)){
					xsjbxx.put("ywbjgmc", "无");
				}else{
					xsjbxx.put("ywbjgmc", "有");
				}
				// 有无违纪处分
				
				if(yscfqk==null || yscfqk.size() == 0){
					xsjbxx.put("ywwjcfmc", "无");
				}else{
					xsjbxx.put("ywwjcfmc", "有");
				}
				// 已申请项目
				StringBuffer ysqxmBuffer = new StringBuffer();
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					// 拼接已申请项目
					if(StringUtils.isNotNull(xmmc)){
						ysqxmBuffer.append(xmmc).append(" ");
					}
				}
				data.put("ysqxmmc", ysqxmBuffer.toString());
				
				// ============ 根据申请的项目名称判断是否选中 begin ============
				String sqxmmc = model.getXmmc();
				// 奖学金 模板是否选中判断
				if(sqxmmc.equals("一等奖学金")){
					data.put("ydjxjflag", "true");
				}else if(sqxmmc.equals("二等奖学金")){
					data.put("edjxjflag", "true");
				}else if(sqxmmc.equals("三等奖学金")){
					data.put("sdjxjflag", "true");
				}else if(sqxmmc.equals("学习优秀奖学金")){
					data.put("xxyxjxjflag", "true");
				}else if(sqxmmc.equals("社会工作优秀奖学金")){
					data.put("shgzyxjxjflag", "true");
				}else if(sqxmmc.equals("文体活动奖学金")){
					data.put("xthdjxjflag", "true");
				}
				// 荣誉称号 模板是否选中判断
				if(sqxmmc.equals("三好学生")){
					data.put("shxsrychflag", "true");
				}else if(sqxmmc.equals("优秀学生干部")){
					data.put("yxxsgbrychflag", "true");
				}
				// ============ 根据申请的项目名称判断是否选中 end ============
				
				// 成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.equals("学业水平")){
						xsjbxx.put("xyspnjzypmdy", zcfMap.get("njzypm"));
					}else if(xmmc.equals("思想品德")){
						xsjbxx.put("sxpdbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("综合能力")){
						xsjbxx.put("zhnlbjpmdy", zcfMap.get("bjpm"));
					}else if(xmmc.equals("身体素质")){
						xsjbxx.put("stszfsdy", zcfMap.get("fs"));
					}
				}
				String zyrs = servicePjPy.getTjzyrs(model.getXh(), model.getXn(), model.getXq());
				data.put("tjzyrs", zyrs);
			}
			
			//北京林业大学个性化
			if("10022".equals(Base.xxdm)){
				//获取打印日期
				XsxxglService xsxxglService = new XsxxglService();
				String dyrq = xsxxglService.getDqrq(xh);
				data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				
				// 已申请项目
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				// 当前项目的学年学期
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
					// 当前项目的学年学期下，学生所获奖项
					if(xnXqmcDqxm.equals(xnTemp + xqmcTemp)){
						pjzqPjjgList.add(pj);
					}
				}
				// 当前项目的学年学期下，学生所获奖项
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
			//广州铁路职业技术学院个性化
			if("13943".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				servicePjPy.addBlankList(pjjg, 1 - pjjg.size());
				data.put("pjjg", pjjg);
			}
			
			//中国美术学院个性化
			if("10355".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
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
				//获取学生学习情况
				CpmdService cpmdService = new CpmdService();
				HashMap<String,String> bxncjMap = servicePjPy.getDjbZcfListByXhXn(xh, model.getXn(), "德育");
				String shxs_xn_bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), CsszService.XQKG);
				data.put("bxncjbjpm", bxncjMap.get("bxncjbjpm")); // 本学年成绩班级排名
				data.put("shxs_xn_bjrs", shxs_xn_bjrs); // 本学年班级人数
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// 参评人数
				data.put("cpzrsForZymy", cpzrs);
				//必修课及排名
				String bjrsForZymy = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bxksForZymy", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgsForZymy", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
				data.put("cjpmForZymy", cjpm);// 总成绩排名
				data.put("bjrsForZymy", "".equals(cjpm) ? "" : bjrsForZymy);// 班级人数
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String zCxmmc = zcfMap.get("xmmc").trim();
					if(zCxmmc.contains("综测")){
						data.put("zcpm", zcfMap.get("bjpm"));
						continue;
					}
				}
				String cprs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());// 参评人数
				data.put("cpzrsForZymy", cprs);
				
				
				//获取奖项名称
				String jxmc = pjxmsqModel.getXmmc();
				data.put("jxmc",jxmc);
				//判断奖项名称是否包含三好学生字段
				if(jxmc.indexOf("三好学生")!= -1){
					data.put("isShxs","1");
				} else {
					data.put("isShxs","0");
				}
				// 有无违纪处分
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "否");
				}else{
					data.put("ywcfmc", "是");
				}
				//补考课数
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				String bkks = xxcjMap.get("bkcjnum");
				//补考结果体能
				String bkjg = xxcjMap.get("xscjstr");
				data.put("bkjg", bkjg);
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "无");
				}else {
					data.put("sfbk", "有");
				}
				
				//中国美院 学生旷课补考违纪信息
				HashMap<String,String> kkbkxxMap = servicePjPy.getKkbkxx(xh);
				data.put("kkbkxx", kkbkxxMap);
				
				//体能测试成绩
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("体能测试",model.getXn(),model.getXq(),xh).get("fs"));
				
				//时间变量
				HashMap<String,String> sqsj = new HashMap<String, String>();
				HashMap<String,String> shsjfdy = new HashMap<String, String>();//辅导员
				HashMap<String,String> shsjyx = new HashMap<String, String>();//院系
				HashMap<String,String> shsjxx = new HashMap<String, String>();//学校
				shsjxx = pjjgService.getHjshsjList(xmdm, "0");
				if(xmmc.indexOf("国家奖学金") != -1 || xmmc.indexOf("省政府") != -1 ){
					sqsj = pjjgService.getHjshsjList(xmdm, "13");
					shsjfdy = pjjgService.getHjshsjList(xmdm,"11");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
					
				}else if(xmmc.indexOf("国家励志奖学金") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "9");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else if(xmmc.indexOf("国家助学金") != -1){
					sqsj = pjjgService.getHjshsjList(xmdm, "12");
					shsjyx = pjjgService.getHjshsjList(xmdm, "7");
				}else {
					
				}
				data.put("sqsjs", sqsj);
				data.put("shsjfdy", shsjfdy);
				data.put("shsjyx", shsjyx);
				data.put("shsjxx", shsjxx);
			}

			//湘潭大学
			if("10530".equals(Base.xxdm)){
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> pjjgL4 =  xsxxglService.getXsxxHjqkNewList(xh,4);
				data.put("pjjgL4", pjjgL4);

				String zzmmmc = xsjbxx.get("zzmmmc");

				if(zzmmmc!=null){
					if (zzmmmc.equals("中国共产党党员")) {
						xsjbxx.put("zzmmmc", "中共党员");
					}else if(zzmmmc.equals("中国共产主义青年团团员")){
						xsjbxx.put("zzmmmc", "共青团员");
					}
				}
			}
			
			//广西师范大学个性化
			if("10602".equals(Base.xxdm)){
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xh);
				data.put("pjjg", pjjg);
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
			}
			
			//浙江传媒
			if("11647".equals(Base.xxdm)) {
				HashMap<String, String> bxk = service.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> pm = service.getPm(model.getXh(),model.getXn());
				
				data.put("bxk", bxk);
				data.put("pm", pm);
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				data.put("beforXn", model.getXn());
				xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
			}
			//青岛滨海学院
			if("10868".equals(Base.xxdm)){
				/*取【学年综合成绩】*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*取学年综合成绩名次*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				/*根据学号取当前评奖周期的参评班级人数*/
				String xsszcpbjRs = service.getXsszcpbjRsForxh(model.getXh(),model.getXn());
				data.put("xsszcpbjRs", xsszcpbjRs);
			}
			//北京联合大学个性化
			if("11417".equals(Base.xxdm)){
				// 设置北京联合大学标题
				data.put("bjlhdxbt", model.getXmmc());
				
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				data.put("pjnd", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))); // 评奖年度
				// 已申请项目
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
				// 获取各种成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("学业")){
						xsjbxx.put("xycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("文体")){
						xsjbxx.put("wtcjfs", zcfMap.get("fs"));
					}
				}
			}
			//永康市职业技术学校
			if("90211".equals(Base.xxdm)) {
				
				data.put("xmmc", model.getXmmc());// 项目名称
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				//判断是否是党团员
				if((!StringUtil.isNull(xsjbxx.get("zzmm")))&&( "03".equals(xsjbxx.get("zzmm")))){
					data.put("sfty", "是");
				}else{
					data.put("sfty", "否");
				}
				// 获取各种成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						xsjbxx.put("dycjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("教学")){
						xsjbxx.put("jxcjfs", zcfMap.get("fs"));
					}else if(xmmc.contains("体育")){
						xsjbxx.put("tycjfs", zcfMap.get("fs"));
					}
				}		
				
			}
			
			//浙江旅游职业学院个性化
			if("12867".equals(Base.xxdm)){
				// 获取各种成绩
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("智育")){
						xsjbxx.put("zyfbjpm", zcfMap.get("bjpm"));
					}
				}
				// 最高分、最低分、平均分、补考课数、学习成绩
				HashMap<String,String> xxcjMap = xsxxService.getXxcj(xh,model.getXn(),model.getXq());
				if(xxcjMap == null){
					xxcjMap = new HashMap<String,String>();
				}
				data.put("xxcjMap", xxcjMap);
				//浙江旅游国家奖学金
				//评奖取值
				BzjljgService pjjgService = new BzjljgService();
				//国家奖学金 取出基本数据
				HashMap<String, String> pjjg12867 =  pjjgService.getZjlyByXhMap(xh,model.getXn());
				data.put("pjjg12867", pjjg12867);
				//综合素质排名
				HashMap<String, String> rspm = pjjgService.getZjlyByPm(xh,model.getXn());
				data.put("rspm", rspm);	
				//浙江旅游励志奖学金中获取字段（家庭情况）
				HashMap<String,String> lzjxj = pjjgService.getZjlylzByXhMap(xh, model.getXn());
				data.put("lzjxj",lzjxj);	
				//浙江旅游学习成绩和排名（这里的成绩可以用在校奖学金）
				HashMap<String,String> zjlyxxcj = pjjgService.getZjlyXxqkCj(xh, model.getXn());
				data.put("zjlyxxcj",zjlyxxcj);
				//浙江省政府奖学金浙江旅游
				HashMap<String, String> zjszf12867 =  pjjgService.getZjszfByXhMap(xh,model.getXn());
				data.put("zjszf12867", zjszf12867);
				//浙江旅游职业学院奖学金
				HashMap<String, String> zjlyzyxy12867 =  pjjgService.getZjlyzyxyfByXhMap(xh,model.getXn());
				data.put("zjlyzyxy12867", zjlyzyxy12867);
				//浙江旅游省级优秀毕业生
				HashMap<String, String> zjlysjyxbys12867 =  pjjgService.getZjlySjyxbys(xh,model.getXn());
				data.put("zjlysjyxbys12867", zjlysjyxbys12867);	
				//浙江省旅游学院优秀毕业生
				HashMap<String, String> zjlyxyyxbys12867 =  pjjgService.getZjlyxyyxbys(xh,model.getXn());
				data.put("zjlyxyyxbys12867", zjlyxyyxbys12867);
			}
			
			//上海电机学院个性化
			if("11458".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				// 当前项目的学年
				data.put("xn_11458", model.getXn().replace("-", "/"));
			}
			//重庆三峡医药
			if("14008".equals(Base.xxdm)){
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (zzmmmc!=null && zzmmmc.equals("中国共产党党员")) {
					data.put("zzmmmc", "中共党员");
				}else {
					data.put("zzmmmc", zzmmmc);
				}
			}
			
			//华中农业大学
			if("10504".equals(Base.xxdm)){
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				//评奖取值
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
					if(xmmc.contains("素拓测评分")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("综测总分")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			//浙江中医药大学
			if("10344".equals(Base.xxdm)){
				data.put("xsjbxx", xsjbxx);
				//申请项目同学年获得奖学金名称（等级）
				BzjljgService pjjgService = new BzjljgService();
				String jxjdj = pjjgService.getJxjmcByXhXn(xh,model.getXn());
				data.put("jxjdj",jxjdj);
				// 参评组人数
				CpmdService cpmdService = new CpmdService();
				String cpzrs = cpmdService.getCpzrs(xh, model.getXn(), model.getXq());
				data.put("cpzrs", cpzrs);
				//获取评奖结果
				List<HashMap<String, String>> pjjgL4 =  pjjgService.getHjxxList(xh,model.getXn(),model.getXq(),"4");
				data.put("pjjgLine1", pjjgL4.get(0));
				data.put("pjjgLine2", pjjgL4.get(1));
				data.put("pjjgLine3", pjjgL4.get(2));
				data.put("pjjgLine4", pjjgL4.get(3));
				data.put("pjjgL4", pjjgL4);
				//获取成绩排名等
				HashMap<String, String> pm = service.getCjPm(model.getXh(),model.getXn());
				data.put("pm", pm);
				String bjrsForZjzyydx = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrsForZjzyydx", "".equals(cjpm) ? "" : bjrsForZjzyydx);// 班级人数
				data.put("cjpm", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
				//时间变量
				HashMap<String,String> sqsj = new HashMap<String, String>();
				HashMap<String,String> shsjfdy = new HashMap<String, String>();//辅导员
				HashMap<String,String> shsjyx = new HashMap<String, String>();//院系
				HashMap<String,String> shsjxx = new HashMap<String, String>();//学校
				shsjxx = pjjgService.getHjshsjList(model.getXmdm(), "0");
				if(model.getXmdm().indexOf("国家奖学金") != -1 || model.getXmdm().indexOf("省政府") != -1 ){
					sqsj = pjjgService.getHjshsjList(model.getXmdm(), "13");
					shsjfdy = pjjgService.getHjshsjList(model.getXmdm(),"11");
					shsjyx = pjjgService.getHjshsjList(model.getXmdm(), "7");
					
				}else if(model.getXmdm().indexOf("国家励志奖学金") != -1){
					sqsj = pjjgService.getHjshsjList(model.getXmdm(), "9");
					shsjyx = pjjgService.getHjshsjList(model.getXmdm(), "7");
				}else if(model.getXmdm().indexOf("国家助学金") != -1){
					sqsj = pjjgService.getHjshsjList(model.getXmdm(), "12");
					shsjyx = pjjgService.getHjshsjList(model.getXmdm(), "7");
				}else {
					
				}
				data.put("sqsjs", sqsj);
				data.put("shsjfdy", shsjfdy);
				data.put("shsjyx", shsjyx);
				data.put("shsjxx", shsjxx);
			}
			//西安科技大学
			if("10704".equals(Base.xxdm)){
				//data.put("jd",new PjxmsqService().getJd_10277(xh,model.getXn()));
				//这里主要是国奖奖学金，获取大学期间获奖情况，装入list。
				BzjljgService pjjgService = new BzjljgService();
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 4 - pjjgList.size());
				data.put("pjjgList", pjjgList);
				int size1=(4 - pjjgList.size())<0?0:(4 - pjjgList.size());
				data.put("blankList1", getBlankList(size1));
				//必修课~这路的专业成绩和专业人数
				String crtxq = StringUtils.isNull(model.getXq())?"on":model.getXq();
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), crtxq);
				//本学年获奖情况
				List<HashMap<String, String>> hjqkList = pjjgService.getHjqk(model.getXh(),model.getXn());
				data.put("hjqkList",hjqkList);
			    //另一种方法的必修课和成绩排名
			    HashMap<String, String> bxk = pjjgService.getBxk(model.getXh(),model.getXn());
				HashMap<String, String> cjpm = pjjgService.getCjPm(model.getXh(),model.getXn());
				//HashMap<String, String> pm = service.getPm(model.getXh(),model.getXn());
				//data.put("pm", pm);
				data.put("bxk", bxk);
				data.put("cjpm", cjpm);
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("素拓测评分")){
						data.put("stzcf", zcfMap.get("fs"));
						continue;
					}
					if(xmmc.contains("综测总分")){
						data.put("zczf", zcfMap.get("fs"));
						continue;
					}					
				}
			}
			
			//浙江万里学院
			if("10876".equals(Base.xxdm)){
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("sfwj", "无");
				}else{
					data.put("sfwj", "有");
				}
				//体育成绩
				data.put("tycj",zcfService.getZcfsByXmXnXqXh("体育分",model.getXn(),model.getXq(),xh).get("fs"));
				//志愿者服务小时数
				data.put("zyfwxs",zcfService.getZcfsByXmXnXqXh("志愿服务小时",model.getXn(),model.getXq(),xh).get("fs"));
				//行为规范成绩（德育分）
				data.put("dyf",zcfService.getZcfsByXmXnXqXh("德育分",model.getXn(),model.getXq(),xh).get("fs"));
				//审核意见
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			if("11799".equals(Base.xxdm)){
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("智育")){
						data.put("zyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("德育")){
						data.put("dycjfs", zcfMap.get("fs"));
						data.put("dyfbjpm", zcfMap.get("bjpm"));
					}
					if(xmmc.contains("平均成绩")){
						data.put("pjcjjdfs", zcfMap.get("fs"));
						data.put("pjcjjdpm", zcfMap.get("bjpm"));
					}
					
				}
				if(StringUtils.isNull(bjgcjs) && (yscfqk==null || yscfqk.size() == 0)){ 
					data.put("ywcfgk", "无");
				}else{
					data.put("ywcfgk", "有");
				}
				if(StringUtils.isNull(bjgcjs)){ // 本学年有无不及格课程
					data.put("bxnywbjgkc", "无");
				}else{
					data.put("bxnywbjgkc", "有");
				}
				
				
				List<HashMap<String, String>> cjList1 = service.getCjsxqList(model.getXn(),model.getXh());
				/**
				 * 成绩处理，写死八项课程八项成绩 第一学期
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
				  成绩处理，写死八项课程八项成绩 第二学期
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
			//徐州医药高等
			if("70002".equals(Base.xxdm)){
				List<HashMap<String,String>> zcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> zcfMap : zcfList) {
					String xmmc = zcfMap.get("xmmc").trim();
					if(xmmc.contains("德育")){
						data.put("dyxf", zcfMap.get("fs"));
					}
					if(xmmc.contains("综测总分")){
						data.put("zhpm", zcfMap.get("bjpm"));
					}
				}
				data.put("djjl", HtmlUtil.xmlZy(model.getDjjl()));
				data.putAll(pjxmsqService.getMaxOrMinWfkCj(xh, model.getXn(),model.getXq()));
				//审核意见
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}
			}
			//评奖日期，奖项名称，颁奖单位用的太多，拉出来通用
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

			//学年拼接，拆分
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
			//学期写死取02 ，01 
			String sxnxq = "02";
			String zxnxq = "01";
			int pjpm =0;
			List<HashMap<String,String>> zcfList2 = zcfService.getZcfListByXh(xh, upsxn1, sxnxq);
			for (HashMap<String, String> zcfMap : zcfList2) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("德育")){
					data.put("dyfs02_70002", zcfMap.get("fs"));
				}
				if(xmmc.contains("综测总分")){
					data.put("zhpm_70002", zcfMap.get("bjpm"));
					if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
						pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
					}
				}
			}
			List<HashMap<String,String>> zcfList3 = zcfService.getZcfListByXh(xh, model.getXn(), zxnxq);
			for (HashMap<String, String> zcfMap : zcfList3) {
				String xmmc = zcfMap.get("xmmc").trim();
				if(xmmc.contains("德育")){
					data.put("dyfs01_70002", zcfMap.get("fs"));
				}
				if(xmmc.contains("综测总分")){
					data.put("pjcjjdpm", zcfMap.get("bjpm"));
					if(StringUtils.isNotNull(zcfMap.get("bjpm"))){
						pjpm=pjpm+Integer.parseInt(zcfMap.get("bjpm"));
					}
				}
			}
			data.put("pjpm_70002", pjpm/2);
			
			// 加载学生家庭基本信息
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			// 班级人数
			CpmdService cpmdService = new CpmdService();
			String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), model.getXq());
			xsjbxx.put("bjrs", bjrs);		

			//加载家庭成员信息
		
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
			
			data.put("jtcyxxList", jtcyxxList);
			int size=(5 - jtcyxxList.size())<0?0:(5 - jtcyxxList.size());
			data.put("blankList", getBlankList(size));
			
			data.put("jtqk", jtqkmodel);//家庭情况
			
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
			String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
			xsjbxx.put("csny_num", csny.replaceAll("年", ".").replaceAll("月", "")); // 2016.12
			xsjbxx.put("rxny", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"),FomartDateType.month));// 入学日期
			
			// 分解身份证号begin
			String xssfzh =  xsjbxx.get("sfzh");
			if(!StringUtil.isNull(xssfzh)){
				for (int i = 0,j = xssfzh.length(); i < j; i++) {
					xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
				}
			}else {
				xsjbxx.put("sfzh7","");
			}

			
			//困难生认定档次
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(xh, model.getXn(), model.getXq());
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//认定档次
			
			// 困难生档次list
			KnsdcService knsdcService = new KnsdcService();
			data.put("knsdcList", knsdcService.getKnsdcList());
			
			HashMap<String, String> spxxInfo =service.getSpxxInfo(model.getSqid());
			data.put("yjshyj", spxxInfo.get("yjshyj"));
			data.put("ejshyj", spxxInfo.get("ejshyj"));
			data.put("sjshyj", spxxInfo.get("sjshyj"));
			
			//格式化数据
			cjList=servicePjPy.formatForDjb(cjList);
			List<HashMap<String, String>> pjjg =  service.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgAll =  service.getPjpyInfoList(xh);
			data.put("pjjgAll",pjjgAll);
			data.put("pjjg", pjjg);
			data.put("xmmc", model.getXmmc());// 项目名称
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
				if(xmmc.contains("综测总分")){
					data.put("zczf", zcfMap.get("fs"));
					data.put("bjpm", zcfMap.get("bjpm"));
					data.put("njzypm", zcfMap.get("njzypm"));
					data.put("cpzpm", zcfMap.get("cpzpm"));
					break;
				}					
			}
			// ================= 项目性质 begin ==================
			data.put("xmxzmc", "");
			XmwhService xmwhService = new XmwhService();
			List<HashMap<String, String>> xmxzList = xmwhService.getXmxz();
			for (HashMap<String, String> xmxzMap : xmxzList) {
				if (xmxzMap.get("dm").equals(model.getXzdm())){
					data.put("xmxzmc", xmxzMap.get("mc"));
					break;
				}
			}

			//广西医科大学个性化多张报表
			/*if("10598".equals(Base.xxdm)){
				if("‘优利特’优秀学生干部奖学金".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_yltyxxsgbjxj.xml";
				}else if("邓洁彬奖学金".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_djbjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_djbjxj.xml";
				}else if("荣和教育奖学金".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_rhjyjxj.xml";
				}else if("三好学生".equals(model.getXmmc()) || "优秀学生干部".equals(model.getXmmc())){
					templatePath = template_dir+"//pjdjb_"+Base.xxdm+"_shxs.xml";
					templateXmlPath="pjdjb_"+Base.xxdm+"_shxs.xml";
				}
			}*/
			//河北工业大学
			if("10080".equals(Base.xxdm)){
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				List<HashMap<String,String>> xmXnBxCjList = zcfService.getCjListByXhXn(xh, model.getXn(), "必修");
				data.put("xnpjf", service.getPjf(xmXnBxCjList, 2));// 学年平均分
				String bjzrs = zcfService.getBjrs(xh);
				data.put("bjzrs",bjzrs);// 班级人数
				data.put("cjpm", cjpm);// 成绩排名
				/*取【学年综合成绩】*/
				HashMap<String, String> xnzhcj = service.getXnzhcj(model.getXh(),model.getXn());
				data.put("xnzhcj", xnzhcj.get("fs"));
				/*取学年综合成绩名次*/
				data.put("xnzhcjmc", xnzhcj.get("bjpm"));
				// 已获奖项
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjgAllList =  service.getPjpyInfoList(model.getXh(),model.getXn());
				for (int i = 0; i < pjjgAllList.size(); i++) {
					HashMap<String, String> pj = pjjgAllList.get(i);
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						yhjxBuffer.append(xmmc).append("、");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if(yhjxStr.length() > 1){
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
				data.put("xmmc", model.getXmmc());// 项目名称
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+(i+1), shyjList.get(i).get("shyj")) ;
				}

				// 获取各种成绩
				List<HashMap<String,String>> hbzcfList = zcfService.getZcfListByXh(xh, model.getXn(), model.getXq());
				for (HashMap<String, String> hbzcfMap : hbzcfList) {
					String xmmc = hbzcfMap.get("xmmc").trim();
					if(xmmc.equals("学年学分绩点")){
						data.put("xnxfjd", hbzcfMap.get("fs"));
					}else if(xmmc.equals("学年体育测试成绩")){
						data.put("xntycscj",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("第一课堂学年学分绩点排序")){
						data.put("dyktxnxfpx",  hbzcfMap.get("fs"));
					}else if(xmmc.equals("第二课堂学年学分排序")){
						data.put("dektxnxfpx", hbzcfMap.get("fs"));
					}else if(xmmc.equals("学年排序加权综合评价")){
						data.put("xnpxjqzhpj",  hbzcfMap.get("fs"));
					}
				}
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				//转换为中文日期格式
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
				data.put("xmje",model.getXmje());//项目金额
			}
			//徐州工程
			if("11998".equals(Base.xxdm)){
				// 有无违纪处分
				if(yscfqk==null || yscfqk.size() == 0){
					data.put("ywcfmc", "否");
				}else{
					data.put("ywcfmc", "是");
				}
				//补考课数
				String bkks = xxcjMap.get("bkcjnum");
				if(bkks == null || "0".equals(bkks)){
					data.put("sfbk", "无");
				}else {
					data.put("sfbk", "有");
				}
				StringBuffer jxj = new StringBuffer();//奖学金
				StringBuffer qtjx = new StringBuffer();//其他奖项
				for (HashMap<String, String> pj : pjjgAll) {
					String xmmc = pj.get("xmmc");
					if(StringUtils.isNotNull(xmmc)){
						if(xmmc.contains("奖学金")){
							jxj.append(xmmc);
							jxj.append("、");
						}else{
							qtjx.append(xmmc);
							qtjx.append("、");
						}
					}
				}
				if(jxj.length()>0)
				jxj.deleteCharAt(jxj.length() - 1);//删除最后一个、
				if(qtjx.length()>0)
				qtjx.deleteCharAt(qtjx.length() - 1);
				String tbrq = model.getSqsj();// 申请时间
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				data.put("jxj", jxj.toString());
				data.put("qtjx", qtjx.toString());
				List<HashMap<String,String>> zwInfo = dwwhService.getZwxx(xh);//所有职务
				StringBuffer xrzw = new StringBuffer();
				StringBuffer crzw = new StringBuffer();
				for (HashMap<String, String> map : zwInfo) {
					String lzsj = map.get("lzsj");
					if("1".equals(map.get("zzzt")) && map.get("rzsj").length() >= 10){
						xrzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-至今"+"、");
					}else{
						if((lzsj != null || !"".equals(lzsj)) && map.get("rzsj").length() >= 10 && map.get("lzsj").length() >= 10){
							crzw.append(map.get("zwmc")+" "+map.get("rzsj").substring(0, 10)+"-"+map.get("lzsj").substring(0,10)+"、");
						}
					}
				}
				if(xrzw.length() > 0) xrzw.deleteCharAt(xrzw.length() - 1);
				if(crzw.length() > 0) crzw.deleteCharAt(crzw.length() - 1);
				data.put("xrzw", xrzw.toString());//现任职务
				data.put("crzw", crzw.toString());//曾任职务
				
				List<HashMap<String,String>> cjlist = zcfService.getCjListByXh(xh);
				data.put("pjcj", service.getPjf(cjlist, 2));//平均成绩
				String cjpm = zcfService.getCjpm("", xh, xsjbxx.get("bjmc"));
				data.put("cjpm", cjpm);
			}
			//浙江交通职业技术学院
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getSqid());
				data.put("bjyj", shyjjtzyList.get(0).get("shyj"));
				data.put("xscyj", shyjjtzyList.get(2).get("shyj"));
				//取综合测评分数
				List<HashMap<String,String>> pmfsList = new ZhcpDjService().getZcfsForDjb(model.getXh(), model.getXn(), model.getXq());
				for (int i = 0; i < pmfsList.size(); i++) {
					HashMap<String, String> temp = pmfsList.get(i);
					String xmmc = temp.get("xmmc");
					if("课程学习记实测评分".equals(xmmc)){
						data.put("kcf", temp.get("fs"));
					}else if("思想品德行为表现记实测评分".equals(xmmc)){
						data.put("pdf", temp.get("fs"));
					}else if("体育表现记实测评分".equals(xmmc)){
						data.put("tyf", temp.get("fs"));
					}else if("综合测评分".equals(xmmc)){
						data.put("zf", temp.get("fs"));
					}
				}
				HashMap<String, String> zjszf12867 =  pjjgService.getZjszfByXhMap(xh,model.getXn());
				data.put("zjszf12867", zjszf12867);
				data.put("nd",new BzjlxssqService().getHsbyjs(xh));
				
			}
			
			//通用审核意见1-7级
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(model.getSqid());
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			// ================= 项目性质 end ==================
			//评奖登记表，判断是否有个性化表单，否则默认。
			String templatePath = template_dir+"//pjdjb_"+Base.xxdm+".xml";
			String templateXmlPath = "pjdjb_"+Base.xxdm+".xml";
			File wordFile = null;
			
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), xsjbxx.get("bjmc")+""+xsjbxx.get("xh")+"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), model.getXh() +"["+xsjbxx.get("xm")+"]" + "-" + model.getXmmc());
			}
			
			//判断文件是否存在
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
	 * 空的list
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
