/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:21:39 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;
import com.zfsoft.xgxt.xljkwzdx.common.ZtToCnDesc;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl.XlzxclForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl.XlzxclService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqForm;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx.XsyysqService;
import com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl.ZxsxxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -预约咨询反馈
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-29 下午03:21:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YyzxfkAction extends SuperAction{
	
	/**
	 * 在读证明-学生信息表单显示数据库配置代码
	 */
	public static final String XLZXCL_BDID = "xljkwzdxxlzxcl";
	/**
	 * 学生显示信息表单服务
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	private XlzxclForm xsyysqForm;
	
	/**
	 * @描述 ：无参构造器 
	 * @描述 ：初始化表单参数
	 */
	public YyzxfkAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(XLZXCL_BDID);
	}
	
	private static final String url = "xljk_xljkzx_yyzxfk.do";

	/**
	 * 
	 * @描述:查询预约咨询反馈（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-30 上午11:14:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryYyzxfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取操作权限
		request.setAttribute("path", "xljk_xljkzx_yyzxfk.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryYyzxfk");
	}
	
	/**
	 * 
	 * @描述:查询预约咨询反馈
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-30 上午11:39:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryYyzxfkAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyzxfkForm model = (YyzxfkForm) form;
		YyzxfkService service = new YyzxfkService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:查出所有咨询师  (根据预约时间) 
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-29 下午19:49:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getZxsxxAllByYysjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String yysj = request.getParameter("yysj");
		ZxsxxService zxsxxService = new ZxsxxService();
		//查询
		List<HashMap<String,String>> resultList = zxsxxService.getZxsxxAllByYysjList(yysj);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:预约反馈(跳转)
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-30 下午01:39:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xlzxYyfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		YyzxfkForm model = (YyzxfkForm) form;
		
		XsyysqService service = new XsyysqService();
		
		XsyysqForm xsyysq = service.getModel(model.getSqid());
		xsyysq.setYyzxzt(StringTools.strOutNull(xsyysq.getYyzxzt()).replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp"));
		xsyysq.setYyzxxq(StringTools.strOutNull(xsyysq.getYyzxxq()).replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp"));
		String yyzt = StringTools.strOutNull(xsyysq.getYyzt());
		
		//设置咨询反馈默认值为2（预约成功）
		if(!yyzt.equals("2")&&!yyzt.equals("5")){
			xsyysq.setYyzt("2");
		}
		request.setAttribute("xsyysq", xsyysq);
		
		//设置学生信息显示字段
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(xsyysq.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(xsyysq.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
			
		}
		
		String wtlxarr = xsyysq.getWtlx()!=null?xsyysq.getWtlx():"";
		wtlxarr = wtlxarr.replaceAll(",", "','");
		wtlxarr = "'"+wtlxarr+"'";
		
		//设置心理问题类型名称String  供前台显示
		List<HashMap<String, String>> xlwtList = service.getXlwtAllListByLxdm(wtlxarr);
		
		StringBuffer wtlxMcStr = new StringBuffer();
		for(int i=0;i<xlwtList.size();i++){
			wtlxMcStr.append(xlwtList.get(i).get("lxmc")).append("；");
		}
		//删除最后一个","
		if(wtlxMcStr.lastIndexOf("；")!= -1 && wtlxMcStr.lastIndexOf("；") == wtlxMcStr.length()-1){
			wtlxMcStr = wtlxMcStr.deleteCharAt(wtlxMcStr.length()-1);
		}
		request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
		
		XlzxclService xlzxclService = new XlzxclService();
		
		HashMap<String, String> yyzxsMap = xlzxclService.getZxsxx(xsyysq.getZxs());
		
		request.setAttribute("yyzxsxm", yyzxsMap.get("xm"));
		
		//设置咨询师列表
		ZxsxxService zxsxxService = new ZxsxxService();
		List<HashMap<String, String>>  zxslist = zxsxxService.getZxsxxAllList();
		request.setAttribute("zxslist", zxslist);
		
		
		
		if(model.getYyzt().equals("2")){   //预约成功状态下 查出咨询处理记录
			
			HashMap<String , String> xlzxcl = xlzxclService.getXlzxclInfo(model.getSqid());
			model.setZxid(xlzxcl.get("zxid"));
			model.setZzaprq(xlzxcl.get("zzaprq"));
			model.setZxsdkssj(xlzxcl.get("zxsdkssj"));
			model.setZxsdjssj(xlzxcl.get("zxsdjssj"));
			model.setZxs(xlzxcl.get("zxs"));
			
			model.setYyzxfs(xlzxcl.get("yyzxfs"));
			
			model.setZxslxdh(xlzxcl.get("zxslxdh"));
			model.setZxdz(xlzxcl.get("zxdz"));
			model.setBz(xlzxcl.get("bz"));
			request.setAttribute("zxs", model.getZxs());
			
			List<HashMap<String, String>>  zxslistNew = zxsxxService.getZxsxxAllByYysjList(xlzxcl.get("zzaprq"));
			
			request.setAttribute("zxslist", zxslistNew);			
		}else if(model.getYyzt().equals("5")){
			model.setYysbyy(xsyysq.getYysbyy());
		}
		
		
		
		return mapping.findForward("xlzxYyfk");
	}
	
	/**
	 * 
	 * @描述:查看学生预约咨询申请及安排咨询信息
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-9上午08:50:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	@SystemAuth(url = url)
	public ActionForward viewXlzxYyfk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyzxfkForm model = (YyzxfkForm) form;
		XsyysqService xsyysqservice = new XsyysqService();
		
		XsyysqForm xsyysqForm = xsyysqservice.getModel(model.getSqid());
		xsyysqForm.setYyzxzt(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxzt())));
		xsyysqForm.setYyzxxq(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYyzxxq())));
		xsyysqForm.setYysbyy(StringTools.StringToText(StringTools.strOutNull(xsyysqForm.getYysbyy())));
		request.setAttribute("yyztmc", ZtToCnDesc.yyztChange(xsyysqForm.getYyzt()));
		request.setAttribute("xsyysqForm",xsyysqForm);
		
		//设置学生信息显示字段
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", "update");
		
		if(!StringUtil.isNull(xsyysqForm.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(xsyysqForm.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
			
		}
		
		String wtlxarr = xsyysqForm.getWtlx()!=null?xsyysqForm.getWtlx():"";
		wtlxarr = wtlxarr.replaceAll(",", "','");
		wtlxarr = "'"+wtlxarr+"'";
		
		//设置心理问题类型名称String  供前台显示
		List<HashMap<String, String>> xlwtList = xsyysqservice.getXlwtAllListByLxdm(wtlxarr);
		
		StringBuffer wtlxMcStr = new StringBuffer();
		for(int i=0;i<xlwtList.size();i++){
			wtlxMcStr.append(xlwtList.get(i).get("lxmc")).append("；");
		}
		request.setAttribute("wtlxMcStr", wtlxMcStr.toString());
		
		//设置咨询预约说明
		String zxyysm = xsyysqservice.getZxyysm();
		request.setAttribute("zxyysm", StringTools.StringToText(StringTools.strOutNull(zxyysm)));
		
		XlzxclService xlzxclService = new XlzxclService();
		
		HashMap<String, String> xlzxclMap = xlzxclService.getXlzxclInfo(model.getSqid());
		
		HashMap<String, String> zxsMap = xlzxclService.getZxsxx(xlzxclMap.get("zxs"));
		
		request.setAttribute("zxsxm", zxsMap.get("xm"));	
		
		HashMap<String, String> yyzxsMap = xlzxclService.getZxsxx(xsyysqForm.getZxs());
		
		request.setAttribute("yyzxsxm", yyzxsMap.get("xm"));		
		
		xlzxclMap.put("bz", StringTools.StringToText(StringTools.strOutNull(xlzxclMap.get("bz"))));
		xlzxclMap.put("xszxpj", StringTools.StringToText(StringTools.strOutNull(xlzxclMap.get("xszxpj"))));
		xlzxclMap.put("zxzt", ZtToCnDesc.zxztChange(xlzxclMap.get("zxzt")));
		request.setAttribute("xlzxclMap", StringUtils.formatData(xlzxclMap));
		
		//设置咨询师列表
		ZxsxxService zxsxxService = new ZxsxxService();
		List<HashMap<String, String>>  zxslist = zxsxxService.getZxsxxAllList();
		request.setAttribute("zxslist", zxslist);
		
		return mapping.findForward("viewXlzxYyfk");
	}
	
	
	/**
	 * 
	 * @描述:预约反馈
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-5-4上午09:10:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xlzxYyfkAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyzxfkForm model  = (YyzxfkForm) form;
		
		boolean isSuccess = true;
		
		XsyysqService xsyysqService = new XsyysqService();
		
		XlzxclService xlzxclService = new XlzxclService();
		
		XsyysqForm xsyysqForm = new XsyysqForm();
		
		XlzxclForm xlzxclForm = new XlzxclForm();
		
		xsyysqForm.setSqid(model.getSqid());
		xsyysqForm.setYyzt(model.getYyzt());
		if(model.getYyzt().equals("2")){    //预约成功
			
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			xlzxclForm.setZxid(uuid);
			xlzxclForm.setSqid(model.getSqid());
			xlzxclForm.setZxzt("0"); //待咨询
			xlzxclForm.setXh(model.getXh());
			xlzxclForm.setZzaprq(model.getZzaprq());
			xlzxclForm.setZxsdkssj(model.getZxsdkssj());
			xlzxclForm.setZxsdjssj(model.getZxsdjssj());
			xlzxclForm.setZxs(model.getZxs());
			
			xlzxclForm.setYyzxfs(model.getYyzxfs());
			
			xlzxclForm.setZxslxdh(model.getZxslxdh());
			xlzxclForm.setZxdz(model.getZxdz());
			xlzxclForm.setBz(model.getBz());
			
			
			
			isSuccess = isSuccess && xlzxclService.deleteXlzxclBySqid(model.getSqid());//先删除原先心理咨询处理记录  再插入一条新的
			
			isSuccess = isSuccess && xlzxclService.runInsert(xlzxclForm);   //为该条心理咨询预约添加一条心理咨询
			
			xsyysqForm.setYysbyy("");  //清空心理咨询预约的“预约失败原因”
		}else{   //预约失败
			xsyysqForm.setYysbyy(model.getYysbyy());  //设置心理预约的预约失败原因
			isSuccess = isSuccess && xlzxclService.deleteXlzxclBySqid(model.getSqid());//删除该条心理咨询预约的心理咨询处理记录
		}
		isSuccess = isSuccess && xsyysqService.runUpdate(xsyysqForm);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
