/**
 * @部门:学工产品事业部
 * @日期：2013-9-13 上午08:54:28 
 */  
package com.zfsoft.xgxt.xlzx.yysq;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxAction;
import com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxForm;
import com.zfsoft.xgxt.xlzx.zxxzwh.ZxxzwhForm;
import com.zfsoft.xgxt.xlzx.zxxzwh.ZxxzwhService;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xlzx.yysq.zwpg.ZwpgService;
import com.zfsoft.xgxt.xlzx.zxswh.ZxsglService;
import com.zfsoft.xgxt.xlzx.zxswh.ZxspbService;
import com.zfsoft.xgxt.xlzx.zxyycl.ZxyyclService;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlAction;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlModel;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 预约申请
 * @作者： wanghj [工号：1004]
 * @时间： 2013-9-13 上午08:54:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YysqAction extends SuperAction{

	private static YysqService service = new YysqService();

	/**
	 * 定义心理咨询预约申请可以从基本信息表中获取学生信息
	 */
	private static final String XLJKYYSQ = "xljkyysq";
	private List<HashMap<String,String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();
	/** 
	 * @描述:学生预约申请查询页面、预约咨询处理查询页面
	 * @作者：whj [工号：1004]
	 * @日期：2013-9-13 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
	public ActionForward yysqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		if(user.getUserStatus().equals("stu")){
			if(service.isExists(user.getUserName())){
				request.setAttribute("ydj", "1");
			}else{
				request.setAttribute("ydj", "0");
			}
		}

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
			request.setAttribute("path", "xlzx_yysq_yysq.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("yysqManage");
		}else{
			request.setAttribute("path", "xlzx_zxyycl_zxyycl.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("zxyyclManage");
		}
	}
	
	
	/** 
	 * @描述:学生评价信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
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
	 * @描述:根据主键查询预约咨询详情
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
	public ActionForward yyzxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm myForm = (YysqForm) form;
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
			List zxsInfo = service.getYysqByZghAnddDate(date,yyzxInfo.get("zgh"));
			if(zxsInfo!=null && zxsInfo.size()>0){
				yjdrs = zxsInfo.size();
			}
		}
		request.setAttribute("yjdrs", yjdrs);
		request.setAttribute("yyzxInfo", StringUtils.formatData(yyzxInfo));
		request.setAttribute("doType", doType);
		request.setAttribute("pbfs", pbfs);
		String currxn=Base.currXn;
		String currxq=Base.getDqxqmc();
		request.setAttribute("currxn", currxn);
		request.setAttribute("currxq", currxq);
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if(!"view".equals(doType) && (StringUtils.isNotNull(pbfs) && "2".equals(pbfs))){
			request.setAttribute("sjddmList", service.getXsYySjd(yyzxInfo.get("yyzxrq"), yyzxInfo.get("zgh"), yyzxInfo.get("xh")));
		}
		if(userStatus.equals("stu")){
			XstxxxForm xstxxxForm = XstxxxAction.getModel(user.getUserName());
			request.setAttribute("xstxxx", xstxxxForm);
			BeanUtils.copyProperties(myForm,service.getModel(id));

			//学生是否首次申请
			String count = service.getCountJg(user.getUserName());
			request.setAttribute("sfscsq", "0".equals(count)?"0":"1");

			return mapping.findForward("yyzxDetail");
		}else{
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
			
			return mapping.findForward("zxyyclDetail");
		}
	}
	
	/** 
	 * @描述:预约日期是否约满
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
	public ActionForward getSfymFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String date = request.getParameter("date");
		String sfym="";

		ZxspbService zxspbSv = new ZxspbService();
		String pbfs = zxspbSv.getPbfs();
		String[] pbzgh = null;
		if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
			HashMap<String, String> zxspbInfo = zxspbSv.getPbInfoByDate(date);
			if(zxspbInfo==null || zxspbInfo.size()<=0){
				return null;
			}
			String _pbzgh = zxspbInfo.get("zgh");
			pbzgh = _pbzgh.split(",");
		}else{
			List<HashMap<String,String>> zxsJbxxList = zxspbSv.getZxsjbxxForSjdPb(date);
			List<String> zghList = new ArrayList<String>();
			for (HashMap<String, String> map : zxsJbxxList) {
				zghList.add(map.get("zgh"));
			}
			pbzgh = zghList.toArray(new String[]{});
		}
		
		
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
			if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
				ZxsglService zxsglSv = new ZxsglService();
				List<HashMap<String,String>> zxsInfoList = zxsglSv.getZxsInfoByZgh(pbzgh);
				if(!zxsInfoList.isEmpty()){
					for(int j=0;j<zxsInfoList.size();j++){
						if(zxsInfoList.get(j).get("kjdrs") != null){
							kjdrs += Integer.parseInt(zxsInfoList.get(j).get("kjdrs"));
						}
					}
				}
			}else{
				kjdrs = Integer.parseInt(service.getSjdYySx(date));
			}
			
			if(count>=kjdrs){
				sfym="Y";
			}
		}
		
		response.getWriter().print(sfym);
		return null;
	}
	
	
	/** 
	 * @描述:预约日期对应的咨询师排班信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
	public ActionForward yysqDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglService zxsglSv = new ZxsglService();
		String date = request.getParameter("date");
		String doType = request.getParameter("doType");
		ZxspbService zxspbSv = new ZxspbService();
		String pbfs = new ZxspbService().getPbfs();
		HashMap<String, String> zxspbInfo = null;
		if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
			zxspbInfo = zxspbSv.getPbInfoByDate(date);
		}else{
			zxspbInfo = zxspbSv.getPbInfoByDateForsjd(date);
		}
	    
	
		List<HashMap<String,String>> zxsInfoList  = new ArrayList<HashMap<String,String>>();
		String xq = request.getParameter("xxxq");
		User user = getUser(request);
		String xh = user.getUserName();
		HashMap<String,String> yysqInfo = service.getYysqByXhAnddDate(date,xh);
		if(yysqInfo!=null && yysqInfo.size()>0){//查看预约信息
			if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
				zxsInfoList.add(zxsglSv.getZxsInfoByZgh(yysqInfo.get("zgh")));
			}else{
				zxsInfoList = zxsglSv.getZxsInfoByZghForsjdYy(new String[]{yysqInfo.get("zgh")}, null, date);
			}
			
			request.setAttribute("yysqInfo", StringUtils.formatData(yysqInfo));
		}else{//新增预约信息
			if(zxspbInfo!=null && zxspbInfo.size()>0){
				String _pbzgh = zxspbInfo.get("zgh");
				String[] pbzgh = _pbzgh.split(",");
				
				if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
					zxsInfoList = zxsglSv.getZxsInfoByZgh(pbzgh,xq);
				}else{
					zxsInfoList = zxsglSv.getZxsInfoByZghForsjdYy(pbzgh, xq, date);	
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
		ZxsglService zxsservice = new ZxsglService();
		request.setAttribute("xqList",zxsservice.getXq());
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xlzx_yysq_yysq.do");
		request.setAttribute("pbfs", pbfs);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yysqDetail");
	}
	
	/** 
	 * @描述:新增预约申请信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
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
			String pbfs = new ZxspbService().getPbfs();
			request.setAttribute("pbfs", pbfs);
			if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
				List<HashMap<String, String>> sjddmList = service.getXsYySjd(model.getYyzxrq(), model.getZgh(),null);
				request.setAttribute("sjddmList",sjddmList );
				String flag = "0";
				if(sjddmList.size() > 0){
					flag="1";
				}
				request.setAttribute("flag", flag);
			}
			//获取学生信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
			jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
			request.setAttribute("jbxxList", jbxxList);
			yysqInfo.put("xstell",  xsjbxx.get("sjhm"));

			//学生填写信息
			XstxxxForm xstxxxForm = XstxxxAction.getModel(user.getUserName());
			request.setAttribute("xstxxx", xstxxxForm);


			request.setAttribute("yysqInfo", StringUtils.formatData(yysqInfo));
			//学生是否首次申请
			String count = service.getCountJg(user.getUserName());
			request.setAttribute("sfscsq", "0".equals(count)?"0":"1");
			return mapping.findForward("addYysqInfo");
	}
	
	/** 
	 * @描述:选择预约咨询（咨询师、管理员操作）
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
	public ActionForward addYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YysqForm model = (YysqForm) form;
		HashMap<String, String> zxsInfo = new HashMap<String, String>();
		ZxsglService zxsSv = new ZxsglService();
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
		String path = "xlzx_yysq.do?method=addYyzxInfo";
		if(StringUtils.isNotNull(zxsInfo.get("zgh"))){
			path += "&zgh=" + zxsInfo.get("zgh");
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XLJKYYSQ);
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("addYyzxInfo");
	}
	/** 
	 * @描述:新增预约申请信息
	 * @作者：whj [工号：1004]
	 * @日期：2013-8-14 下午03:02:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
	public ActionForward saveYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			if(StringUtils.isNotNull(model.getYyzxzt())){
				model.setYyzxzt(URLDecoder.decode((URLDecoder.decode(model.getYyzxzt(),"UTF-8")),"UTF-8"));
			}
			if(StringUtils.isNotNull(model.getYyzxxq())){
				model.setYyzxxq(URLDecoder.decode((URLDecoder.decode(model.getYyzxxq(),"UTF-8")),"UTF-8"));
			}
			if(StringUtils.isNotNull(model.getBczxwt())){
				model.setYyzxzt(URLDecoder.decode((URLDecoder.decode(model.getBczxwt(),"UTF-8")),"UTF-8"));
			}
			if(StringUtils.isNotNull(model.getZxhzt())){
				model.setYyzxxq(URLDecoder.decode((URLDecoder.decode(model.getZxhzt(),"UTF-8")),"UTF-8"));
			}
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
	 * 
	 * @描述:删除预约咨询信息(老师追加的咨询且未咨询的)
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-28 下午02:23:40
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward delYyzxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 刪除申請
			int count = service.delYyzxInfo(values.split(","));
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
	 * @描述:修改、取消公用方法
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-9-1 上午11:35:40
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
	public ActionForward updateYysqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			YysqForm model = (YysqForm) form;
			String doType = request.getParameter("doType");
			//学生修改预约申请信息时，走if。如果是取消则跳过。
			if("update".equalsIgnoreCase(doType)){
				//此处用于处理修改数据存入数据库乱码（字段为：咨询主题、咨询概要）
				if(StringUtils.isNotNull(model.getYyzxzt())){
					model.setYyzxzt(URLDecoder.decode((URLDecoder.decode(model.getYyzxzt(),"UTF-8")),"UTF-8"));
				}
				if(StringUtils.isNotNull(model.getYyzxxq())){
					model.setYyzxxq(URLDecoder.decode((URLDecoder.decode(model.getYyzxxq(),"UTF-8")),"UTF-8"));
				}
				if(StringUtils.isNotNull(model.getBczxwt())){
					model.setBczxwt(URLDecoder.decode((URLDecoder.decode(model.getBczxwt(),"UTF-8")),"UTF-8"));
				}
				if(StringUtils.isNotNull(model.getZxhzt())){
					model.setZxhzt(URLDecoder.decode((URLDecoder.decode(model.getZxhzt(),"UTF-8")),"UTF-8"));
				}
			}
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
	 * 
	 * @描述:导出
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-28 下午05:49:59
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
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
		List<HashMap<String,String>> YysqInfoList = service.queryYyzxInfoList(model, user);

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
	 * @描述:咨询须知显示
	 * @作者：lgx [工号：1553]
	 * @日期：2018/12/27 9:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward zxxz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YysqForm model = (YysqForm) form;
		ZxxzwhForm zxxzwhForm = new ZxxzwhService().getModel();
		request.setAttribute("zxxzwhForm",StringUtils.formatData(zxxzwhForm));
		return mapping.findForward("zxxz");
	}

}
