/**
 * @部门:学工产品事业部
 * @日期：2013-8-19 下午04:35:22 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xlzx.yysq.zwpg.ZwpgService;
import com.zfsoft.xgxt.xlzx.yysqnew.YysqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理咨询-咨询师排班管理(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-8-19 下午04:35:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxspbAction extends SuperAction{
	// 咨询师排班-过期数据是否可修改：1：是，0：否
	private static final boolean XLZX_ZXSPB_N = "0".equals(MessageUtil.getText("xlzx.zxspb"));
	private static ZxspbService service = new ZxspbService();

	private static final String url = "xlzx_zxspb_zxspb.do";
	private static final String MESSAGE = "请先维护时间段代码表的数据！";
	/** 
	 * @描述:咨询师排班信息日历展示
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-24 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward zxsPbDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String queryDate = request.getParameter("queryDate");
		if (QUERY.equals(doType)){	//查询
			List<HashMap<String, String>>  zxspbInfoList = service.getZxspbAsRl(queryDate);
			JSONArray data = JSONArray.fromObject(zxspbInfoList);
			response.getWriter().print(data);
			return null;
		}
		request.setAttribute("XLZX_ZXSPB_N", XLZX_ZXSPB_N);
		return mapping.findForward("zxsPbDeal");
	}
	
	/** 
	 * @描述:咨询师排班信息日历展示 for 学生
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-24 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward zxsPbbForXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String queryDate = request.getParameter("queryDate");

		if (QUERY.equals(doType)){	//查询
			String xh = getUser(request).getUserName();
			if(!StringUtil.isNull(xh)){
				//根据xg_xlzx_csszb表中[pbfs]字段决定走哪个查询，排班方式 1：按天  2：按时间段
				String pbfs = service.getPbfs();
				List<HashMap<String, String>>  zxspbInfoList = null;
				if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
					zxspbInfoList = service.getZxspbAsRlForXs(xh,queryDate);
				}else{
					zxspbInfoList = service.getZxspbAsRlForXsSjd(xh,queryDate);
				}
				
				JSONArray data = JSONArray.fromObject(zxspbInfoList);
				response.getWriter().print(data);
				return null;
			}
		}
		return mapping.findForward("zxsPbbForXs");
	}
	/** 
	 * @描述:咨询师排班详情
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward zxspbDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		ZxsglService zxsglSv = new ZxsglService();
	 	String date = request.getParameter("date");
	 	String doType = request.getParameter("doType");
	 	HashMap<String, String> zxspbList=new HashMap<String,String>();
	 	if(!StringUtil.isNull(model.getId())){
	 		zxspbList = service.getPbInfoById(model.getId());
	 		date = zxspbList.get("pbdate");
	 	}
	 	//获取所有在岗可预约的咨询师列表
	 	String status= "1";
		List<HashMap<String,String>> zxsList = zxsglSv.getZxsListXssq(status, date);
	 	request.setAttribute("zxsList", zxsList);
		request.setAttribute("zxspbList", StringUtils.formatData(zxspbList));
		request.setAttribute("date", date);
		if(StringUtils.isNotNull(date)){
			String weekday = service.getWeekdayByDate(date).get("weekday");
			request.setAttribute("weekday", weekday);
			request.setAttribute("week", weekday.substring(2));
		}
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xlzx_zxspb_zxspb.do");
		FormModleCommon.commonRequestSet(request);
		//根据xg_xlzx_csszb表中[pbfs]字段决定跳转页面，排班方式 1：按天  2：按时间段
		//按天或者为空跳转原页面
		String pbfs = service.getPbfs();
		request.setAttribute("xxdm", Base.xxdm);
		if( StringUtils.isNull(pbfs) || "1".equals(pbfs)){
			return mapping.findForward("zxspbDetail");
		}else{
			//按时间段，通用页面，请勿随便改动
			List<HashMap<String, String>> sjdList = service.getSjdList();
			//如果没有维护时间段表，转到提示页面
			if(sjdList == null || sjdList.size() == 0){
				request.setAttribute("message", MESSAGE);
				return mapping.findForward("error");
			}
			return mapping.findForward("zxspbDetailSJD");
		}
		
	}
	
	
	/** 
	 * @描述:咨询师排班详情
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	/*public ActionForward zxspbInfoById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
	 	HashMap<String, String> zxspbList=new HashMap<String,String>();
	 	if(!StringUtil.isNull(model.getId())){
	 		zxspbList = service.getPbInfoById(model.getId());
	 	}
		response.getWriter().print(zxspbList);
		return null;
	}*/
	
	
	/** 
	 * @描述:根据日期查询当天是否已排班
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward getPbInfoByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		String flag = "false";
		if(!StringUtil.isNull(model.getPbdate())){
			HashMap<String, String> pbInfo = service.getPbInfoByDate(model.getPbdate());
			if(pbInfo!=null && pbInfo.size()>0){
				flag = "true";
			}
		}
		response.getWriter().print(flag);
		return null;
	}
	
	
	/** 
	 * @描述:当前日期该咨询师是否可预约
	 * @作者：whj [工号：1004]
	 * @日期：2013-10-11 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward getSfkyFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String message ="";
		YysqService yysqSv = new YysqService();
		ZxspbForm model = (ZxspbForm) form;
		String date = model.getPbdate();
		String zgh = model.getZgh();
		String pbfs = service.getPbfs();
		String xh = request.getParameter("xh");
		String id = request.getParameter("id");
		HashMap<String, String> zxspbInfo = null;
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			zxspbInfo = service.getPbInfoByDateForsjd(date);
		}else{
			zxspbInfo = service.getPbInfoByDate(date);
		}
		//判断当前日期咨询师是否有进行排班
		if(zxspbInfo!=null && zxspbInfo.size()>0){
			String zghs = zxspbInfo.get("zgh");
			if( StringUtils.isNull(zghs) || zghs.indexOf(zgh)==-1){
				message = "所选日期该咨询师不在排班中！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}else{//所选咨询日期未进行排班
			message = "所选日期没有进行排班！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//判断当前日期所选学生是否已经预约 过
		HashMap<String,String> yysqInfoByXh = null;//yysqSv.getYysqByXhAnddDate(date,xh);
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			yysqInfoByXh = yysqSv.getYysqByXhAnddDateForSjd(date,xh,id);
		}else{
			yysqInfoByXh = yysqSv.getYysqByXhAnddDate(date,xh);
		}
		if(yysqInfoByXh!=null && yysqInfoByXh.size()>0){
			message = "所选学生已预约当前所选咨询日期！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//判断当前日期咨询师的预约人数是否已满
		int count=0;//当日已预约总人数
		int kjdrs =0;//当日可接待总人数
		List yysqInfo = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getYysqByZghAnddDate(date,zgh);
		if(yysqInfo!=null && yysqInfo.size()>0){
			count +=yysqInfo.size();
		}
		if(count!=0){
			//获取当日可预约人数
			ZxsglService zxsglSv = new ZxsglService();
			HashMap<String, String> zxsInfo = null;//zxsglSv.getZxsInfoByZgh(zgh);
			if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
				List<HashMap<String, String>> tempList = zxsglSv.getZxsInfoByZghForsjdYy(new String[]{zgh}, null, date);
				if(tempList != null && tempList.size() > 0){
					zxsInfo = tempList.get(0);
				}
			}else{
				zxsInfo = zxsglSv.getZxsInfoByZgh(zgh);
			}
			if(zxsInfo!=null && zxsInfo.size()>0){
				kjdrs += Integer.parseInt(zxsInfo.get("kjdrs"));
			}
			if(count>=kjdrs){
				message="所选日期该咨询师已约满！";
			}
		}
		JSONObject json = getJsonMessage(message);
		List<HashMap<String, String>> sjddmList = new ArrayList<HashMap<String,String>>();
		if(StringUtils.isNull(message) && (StringUtils.isNotNull(pbfs) && "2".equals(pbfs))){
			 sjddmList = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getXsYySjd(date, zgh, xh);
			
		}
		json.put("sjddmList", JSONArray.fromObject(sjddmList));
		json.put("xqmc",service.getXqmc(date, zgh));
		response.getWriter().print(json);
		return null;
	}
	/** 
	 * 当前日期可预约咨询师
	 */
	public ActionForward getOkZxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		YysqService yysqSv = new YysqService();
		ZxspbForm model = (ZxspbForm) form;
		String date = model.getPbdate();
		String id = model.getId();
		String xh = request.getParameter("xh");
		Map<String,String> rs = new HashMap<String, String>();
		String pbfs = service.getPbfs();
		HashMap<String, String> yyzxInfo =  new YysqService().getYyzxDetail(id);
		//判断当前日期所选学生是否已经预约 过
		HashMap<String,String> yysqInfoByXh = yysqSv.getYysqByXhAnddDateId(date,xh,id);
		if(yysqInfoByXh!=null && yysqInfoByXh.size()>0){
			rs.put("message", "所选学生已预约当前所选咨询日期！");
			JSONObject json = JSONObject.fromObject(rs); 
			response.getWriter().print(json);
			return null;
		}
		HashMap<String, String> zxspbInfo =  (StringUtils.isNotNull(pbfs) && "2".equals(pbfs)) ? service.getPbInfoByDateForsjd(date) : service.getPbInfoByDate(date);
		//判断当前日期咨询师是否有进行排班
		if(zxspbInfo!=null && zxspbInfo.size()>0){
			String[] zghs = zxspbInfo.get("zgh").split(",");
			List<String> zghsList = new ArrayList<String>();
			for (int i = 0; i < zghs.length; i++) {
				String zgh = zghs[i];
				//判断当前日期咨询师的预约人数是否已满
				int count=0;//当日已预约总人数
				int kjdrs =0;//当日可接待总人数
				List yysqInfo = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getYysqByZghAnddDate(date,zgh);
				if(yysqInfo!=null && yysqInfo.size()>0){
					count +=yysqInfo.size();
				}
				if(count!=0){
					//获取当日可预约人数
					ZxsglService zxsglSv = new ZxsglService();
					HashMap<String, String> zxsInfo = null;
					if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
						List<HashMap<String, String>> tempList = zxsglSv.getZxsInfoByZghForsjdYy(new String[]{zgh}, null, date);
						if(tempList != null && tempList.size() > 0){
							zxsInfo = tempList.get(0);
						}
					}else{
						zxsInfo = zxsglSv.getZxsInfoByZgh(zgh);
					}
					//zxsglSv.getZxsInfoByZgh(zgh)
					if(zxsInfo!=null && zxsInfo.size()>0){
						String kjdrsTemp = zxsInfo.get("kjdrs");
						kjdrs += Integer.parseInt(kjdrsTemp == null ? "999999999" : kjdrsTemp);
					}
					if(count>=kjdrs && !yyzxInfo.get("zgh").equals(zgh)){//count>=kjdrs,巨大的bug
						// 所选日期该咨询师已约满！
						continue;
					}
				}
				zghsList.add(zgh);
			}
			ZxsglService zxsglService = new ZxsglService();
			if(zghsList.size() > 0){
//				YysqService yysqService = new YysqService();
//				boolean isZxs = yysqService.isZxs(user.getUserName()); // 当前用户是咨询师
				String[] zghsNew = zghsList.toArray(new String[]{});
				// 只显示当前用户
//				if(isZxs){
//					boolean pb_Y = zghsList.contains(user.getUserName()); // 当前用户有排班
//					if(pb_Y){
//						zghsNew = new String[]{ user.getUserName() };
//					}else{
//						rs.put("message", "所选日期没有进行排班！");
//						JSONObject json = JSONObject.fromObject(rs); 
//						response.getWriter().print(json);
//						return null;
//					}
//				}
				List<HashMap<String,String>> zxslist = null;//
				if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
					zxslist = zxsglService.getZxsListYyfkForSjd(zghsNew, date);
				}else{
					zxslist = zxsglService.getZxsListYyfk(zghsNew, date);
				}
				JSONArray dataList = JSONArray.fromObject(zxslist);
				response.getWriter().print(dataList);
				return null;
			}else{
				rs.put("message", "所选日期没有进行排班！");
				JSONObject json = JSONObject.fromObject(rs); 
				response.getWriter().print(json);
				return null;
			}
		}else{//所选咨询日期未进行排班
			rs.put("message", "所选日期没有进行排班！");
			JSONObject json = JSONObject.fromObject(rs); 
			response.getWriter().print(json);
			return null;
		}
	}
	

	/** 
	 * @描述:根据日期查询当天已排班的咨询师信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-24 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	public ActionForward getPbZxsListByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglService zxsglSv = new ZxsglService();
		ZxspbForm model = (ZxspbForm) form;
		List<HashMap<String,String>> zghInfoList= new ArrayList<HashMap<String,String>>();
		String pbfs = service.getPbfs();
		if(StringUtils.isNull(pbfs)|| "1".equals(pbfs)){
			if(!StringUtil.isNull(model.getPbdate())){
				HashMap<String, String> zxspbInfo = service.getPbInfoByDate(model.getPbdate());
				if(zxspbInfo!=null && zxspbInfo.size()>0){
					String _zgh = zxspbInfo.get("zgh");
					if(!StringUtil.isNull(_zgh)){
						String[] zgh = _zgh.split(",");
						zghInfoList = zxsglSv.getZxsInfoByZgh(zgh);
					}
				}
			}
		}else{
			if(StringUtils.isNotNull(model.getPbdate())){
				zghInfoList = service.getZxsjbxxForSjdPb(model.getPbdate());
			}
		}
	
		response.getWriter().print(JSONArray.fromObject(zghInfoList));
		return null;
	}
	/** 
	 * @描述:根据日期查询当天預約說明
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-24 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getYysmByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		if(!StringUtil.isNull(model.getPbdate())){
			HashMap<String, String> zxspbInfo = service.getPbInfoByDate(model.getPbdate());
			response.getWriter().print(JSONArray.fromObject(zxspbInfo));
		}
		return null;
	}
	
	/** 
	 * @描述:保存排班信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveZxspbInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			ZxspbForm model = (ZxspbForm) form;
			try {
				boolean flag = service.saveZxspbInfo(model);
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("zxspbDetail");
	}
	
	/** 
	 * @描述:批量保存排班信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveBatchZxspbInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		
		String doType = request.getParameter("doType");
		String zgh = model.getZgh();
		String bz = model.getBz();
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		List<HashMap<String, String>> pbdateInfoList = service.getDateByWeekBetweenDate(startDate, endDate);
		if(pbdateInfoList!=null && pbdateInfoList.size()>0){
			String[] pbdate = new String[pbdateInfoList.size()];
			for(int i=0;i<pbdateInfoList.size();i++){
				pbdate[i] = pbdateInfoList.get(i).get("tdate");
			}
			if(StringUtils.isArrayNotNull(pbdate) && !StringUtil.isNull(zgh)){
				try {
					boolean flag = service.saveBatchZxspbInfo(pbdate, zgh, bz);
					response.getWriter().print(flag);
					return null;
				} catch (Exception e) {
					e.printStackTrace();
					throw new SystemException(MessageKey.SYS_SAVE_FAIL);
				}
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("zxspbDetail");
	}
	
	/** 
	 * @描述:修改排班信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZxspbInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;

		try {
			boolean flag = service.updateZxspbInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/** 
	 * @描述:修改排班信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delZxspbById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;

		try {
			Boolean flag= false;
			int count = service.delZxspbById(model.getId());
			if(count>0){
				flag = true;
			}
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/** 
	 * @描述:根据咨询师编号查询咨询师的排班情况
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getZxspbInfoByZgh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		List<HashMap<String,String>> zxsPbInfoList = new ArrayList<HashMap<String,String>>();
		try {
			if(!StringUtil.isNull(model.getZgh())){
				zxsPbInfoList = service.getZxspbInfoByZgh(model.getZgh());
				JSONArray dataList = JSONArray.fromObject(zxsPbInfoList);
				response.getWriter().print(dataList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @描述:根据日期获取星期几
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param args
	 * void 返回类型 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getWeekdayByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String date = request.getParameter("date");
		try {
			HashMap<String, String> m = service.getWeekdayByDate(date);
			String week = (String)m.get("weekday");
			response.getWriter().print(week);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @描述: 湖南城市学院个性化导出方法
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-6-29 下午01:49:23
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
	public ActionForward exportConfig (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZxspbService service = new ZxspbService();
		CommService comService = new CommService();
		ZxspbForm exporModel = new ZxspbForm();
		comService.getModelValue(exporModel, request);
		response.reset();//清除首部的空白行
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("月咨询师排班信息".getBytes("gb2312"),"iso-8859-1")+".xls");
		service.exportConfig_11527(exporModel,response.getOutputStream());
		return null;
	}
	
	/**
	 * 
	 * @描述: 初始化按时间段排班
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午03:43:44
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
	public ActionForward initSjdPb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pbdate = request.getParameter("pbdate");
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		JSONArray jsonList = service.createSjdPbInitList(pbdate);
		List<HashMap<String, String>>  xqList = new ZxsglService().getXq();
		JSONObject jsonMap = JSONObject.fromObject(dataMap);
		jsonMap.put("xqList", JSONArray.fromObject(xqList));
		jsonMap.put("dataList", jsonList);
		jsonMap.put("sjdList", JSONArray.fromObject(service.getSjdList()));
		response.getWriter().print(jsonMap);
		return null;
	}
	
	/**
	 * 
	 * @描述：保存排班信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-23 上午10:59:57
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
	public ActionForward savePbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxspbForm model = (ZxspbForm) form;
		model.setId(request.getParameter("pbid"));
		ZxspbService transService = TransactionControlProxy.newProxy(new ZxspbService());
		boolean rs = transService.savePbxxb(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 咨询时间段下拉框重新赋值
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-30 下午02:53:07
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
	public ActionForward changeSjdSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String date = request.getParameter("date");
		String zgh = request.getParameter("zgh");
		String xh = request.getParameter("xh");
		List<HashMap<String, String>> sjddmList = new com.zfsoft.xgxt.xlzx.yysq.YysqService().getXsYySjd(date, zgh, xh);
		JSONArray json = JsonUtil.ListToJSON(sjddmList);
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward xlzxcns(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xh = getUser(request).getUserName();
		ZwpgService zwpgService = new ZwpgService();
		HashMap<String, String> map =  zwpgService.getInfoByXh(xh);
		request.setAttribute("map", map);
		return mapping.findForward("xlzxcns");
	}
}
