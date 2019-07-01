/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 下午02:15:20 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zpjg;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.wiscom.is.idstar.Date;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszModel;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjModel;
import com.zfsoft.xgxt.xsxx.dyxj.dmwh.ZpdjService;
import com.zfsoft.xgxt.xsxx.dyxj.dyzp.DyzpModel;
import com.zfsoft.xgxt.xsxx.dyxj.dyzp.DyzpService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @类功能描述: 自评结果
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 下午02:15:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZpjgAction extends SuperAction<ZpjgModel, ZpjgService> {
	
	private static final String url = "xsxx_dyxj_pyjg.do";
	
	/**德育自评列表*/
	@SystemAuth(url = url)
	public ActionForward zpjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SearchModel searchModel=new SearchModel();
		CsszService csszService = new CsszService();
		CsszModel csszModle =  csszService.getModel();
		searchModel.setSearch_tj_xn(new String[]{csszModle.getSqxn()});
		searchModel.setSearch_tj_xq(new String[]{csszModle.getSqxq()});
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("path", "xsxx_dyxj_pyjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zpjgList");
	}
	
	/**德育自评列表*/
	@SystemAuth(url = url)
	public ActionForward getZpjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpjgService service = getService();
		ZpjgModel model = (ZpjgModel) form;
		
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**德育自评增加*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zpjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpjgModel model = (ZpjgModel) form;
		User user = getUser(request);
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		model.setXn(csszModel.getSqxn());
		model.setXq(csszModel.getSqxq());
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		ZpdjService zpdjService = new ZpdjService();
		List<HashMap<String,String>> zpdjList = zpdjService.getAllList(new ZpdjModel());
		
		request.setAttribute("zpdjList", zpdjList);
		request.setAttribute("path", "xsxxDyxjZpjg.do?method=zpjgAdd");
		this.saveToken(request);
		return mapping.findForward("zpjgAdd");
	}
	
	/**德育自评修改*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zpjgEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpjgService service = getService();
		ZpjgModel myForm = (ZpjgModel) form;
		
		ZpjgModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		ZpdjService zpdjService = new ZpdjService();
		List<HashMap<String,String>> zpdjList = zpdjService.getAllList(new ZpdjModel());
		
		request.setAttribute("zpdjList", zpdjList);
		return mapping.findForward("zpjgEdit");
	}
	
	
	
	/**查看 ***/
	@SystemAuth(url = url)
	public ActionForward zpjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpjgService service = getService();
		ZpjgModel myForm = (ZpjgModel) form;
		
		ZpjgModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("zpjgView");
	}
	
	
	/**德育自评删除*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-德育自评-删除VALUES:{ids}")
	public ActionForward zpjgDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		ZpjgService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	
	
	/**导出***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgService service = getService();
		ZpjgModel model = (ZpjgModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**查询是否已申请**/
	public ActionForward getCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpjgService service = getService();
		ZpjgModel model = (ZpjgModel) form;
		
		String count = service.getCount(model);
		response.getWriter().print(count);
		return null;
	}
	
	/**
	 * 
	 *德育自评结果详细表导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZpjgxjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		File wordFile = getZpjgWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getZpjgWord(ZpjgModel myForm,HttpServletRequest request) throws Exception{
		ZpjgService service = new ZpjgService();
		DyzpService dyzpservice = new DyzpService();
		String flag = request.getParameter("flag");
		String id = "";
		if(flag.equalsIgnoreCase("jg")){
			ZpjgModel model = service.getModel(myForm.getId());
			id = model.getId();
		}else{
			DyzpModel model = dyzpservice.getModel(myForm.getId());
			id = model.getId();
		}
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> zpjgmap = null;
		
		zpjgmap = service.getDyzpbbxx(id,flag);
		ShlcUtil shlcutil = new ShlcUtil();
		List<HashMap<String, String>> shlclist = shlcutil.getShlcInfo(myForm.getId());
		//德育自评查询
		HashMap<String, String> shlcMap = new HashMap<String, String>();
		if(shlclist != null && shlclist.size() != 0){
			shlcMap = shlclist.get(shlclist.size()-1);
		}
		String djdm = "";
		if(flag.equalsIgnoreCase("jg")){
			ZpjgModel dyzp = service.getModel(myForm.getId());
			djdm = dyzp.getDjdm();
			if(dyzp.getSjly() != null && dyzp.getSjly().equalsIgnoreCase("1")){
				data.put("bjpd", service.getXsZpdj(shlcMap.get("zd2")));
				data.put("shr", shlcMap.get("shr"));
			}
		}else{
			DyzpModel dyzp = dyzpservice.getModel(myForm.getId());
			djdm = dyzp.getDjdm();
			data.put("bjpd", service.getXsZpdj(shlcMap.get("zd2")));
			data.put("shr", shlcMap.get("shr"));
		}
		String xszpdj = service.getXsZpdj(djdm);
		data.putAll(zpjgmap);
		data.put("xb",zpjgmap.get("xb").equals("1")?"男":"女");
		data.put("zpnr",HtmlUtil.xmlZy(zpjgmap.get("zpnr")));
		data.putAll(shlcMap);
		data.put("xszpdj", xszpdj);
		data.put("sysdate", GetTime.getTimeByFormat("yyyymmdd"));
		data.put("xns", zpjgmap.get("xn"));
//		data.put("xqs", zpjgmap.get("xq").replace("0", ""));
		if(zpjgmap.get("xq").equals("01")){
			data.put("xqs", "第一学期");
		}else{
			data.put("xqs", "第二学期");
		}
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx//dyzp","dyxj_zp.xml",zpjgmap.get("xh")+zpjgmap.get("xm"));		
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZpjgTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setId(values[i]);
				File file = getZpjgWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 *德育自评班级汇总结果表导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getbjzpjghzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		File wordFile = getZpjghzWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getZpjghzWord(ZpjgModel myForm,HttpServletRequest request) throws Exception{
		ZpjgService service = new ZpjgService();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> zpjglist = null;
		List<HashMap<String, String>> zphzjglist = null;
		myForm.setBjdm(URLDecoder.decode(myForm.getBjdm(),"UTF-8"));
		zpjglist = service.getDyzpHzlist(myForm.getBjdm(),myForm.getXn(),myForm.getXq());
		zphzjglist = service.getDyzpHztjlist(myForm.getBjdm(),myForm.getXn(),myForm.getXq());
		//德育自评查询
		DyzpService dyzpservice = new DyzpService();
		data.put("zpjglist",zpjglist);
		String yxrs = "";
		String yxbfb = "";
		String lhrs = "";
		String lhbfb = "";
		String zdrs = "";
	    String zdbfb = "";
	    String jbhgrs = "";
	    String jbhgbfb = "";
	    if(zphzjglist != null && zphzjglist.size()>0){
	    	for (HashMap<String, String> hashMap : zphzjglist) {
				if(hashMap.get("djmc").equalsIgnoreCase("优")){
					yxrs = hashMap.get("rs");
					yxbfb = hashMap.get("bfb");
				}else if(hashMap.get("djmc").equalsIgnoreCase("良")){
					lhrs = hashMap.get("rs");
					lhbfb = hashMap.get("bfb");
				}else if(hashMap.get("djmc").equalsIgnoreCase("中")){
					zdrs = hashMap.get("rs");
					zdbfb = hashMap.get("bfb");
				}else if(hashMap.get("djmc").equalsIgnoreCase("基本合格")){
					jbhgrs = hashMap.get("rs");
					jbhgbfb = hashMap.get("bfb");
				}else{
					System.out.print("导出数据有误-"+hashMap.get("djmc"));
				}
			}
	    }
		data.put("yxrs", yxrs);
		data.put("yxbfb", yxbfb);
		data.put("lhrs", lhrs);
		data.put("lhbfb", lhbfb);
		data.put("zdrs", zdrs);
		data.put("zdbfb",zdbfb);
		data.put("jbhgrs",jbhgrs);
		data.put("jbhgbfb",jbhgbfb);
		data.put("y",   GetTime.getTimeByFormat("yyyy"));
		data.put("m",   GetTime.getTimeByFormat("mm"));
		data.put("d",   GetTime.getTimeByFormat("dd"));
		if(zpjglist != null && zpjglist.size() != 0){
			data.put("xymc", zpjglist.get(0).get("xymc"));
			data.put("bjmc", zpjglist.get(0).get("bjmc"));
			data.put("xn", zpjglist.get(0).get("xn"));
			data.put("xq", zpjglist.get(0).get("xq"));
		}else{
			data.put("xymc", "");
			data.put("bjmc", "");
			data.put("xn", "");
			data.put("xq", "");
		}
		
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx//dyzp","dyzpbjhz.xml",myForm.getBjdm()+"-"+myForm.getXn());		
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZpjghzTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			value = URLDecoder.decode(value,"UTF-8");
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setBjdm(values[i]);
				File file = getZpjghzWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 *德育自评学院汇总结果表导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getxyzpjghzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		File wordFile = getZpjgxyhzWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 *德育自评学院汇总结果表导出
	 */
	private File getZpjgxyhzWord(ZpjgModel myForm,HttpServletRequest request) throws Exception{
		ZpjgService service = new ZpjgService();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> zpjgxylist = null;
		HashMap<String, String> zphzxymap = null;
		zpjgxylist = service.getDyzpXyHzlist(myForm.getXydm(),myForm.getXn(),myForm.getXq());
		zphzxymap = service.getDyzpXyHzSumlist(myForm.getXydm(), myForm.getXn(), myForm.getXq());
		//德育自评查询
		DyzpService dyzpservice = new DyzpService();
		data.put("zpjglist",zpjgxylist);
		data.putAll(zphzxymap);
		
		if(myForm.getXq().equals("01")){
			data.put("xq", "一");
		}else{
			data.put("xq", "二");
		}
		data.put("xn", myForm.getXn());
		data.put("y",   GetTime.getTimeByFormat("yyyy"));
		data.put("m",   GetTime.getTimeByFormat("mm"));
		data.put("d",   GetTime.getTimeByFormat("dd"));
		data.put("xymc", URLDecoder.decode(URLDecoder.decode(myForm.getXymc(),"UTF-8"),"UTF-8").replace("学院", ""));
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx//dyzp","dyzpxyhz.xml",myForm.getXydm()+"-"+myForm.getXn());		
		return file;
	}
	

	/**
	 * 
	 *德育自评学院汇总结果表导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZpjgxyhzTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		String value = request.getParameter("value");
		String value1 = request.getParameter("value1");
		if (StringUtils.isNotNull(value) && StringUtils.isNotNull(value1)){
			String[] values = value.split(",");
			String[] value1s = value1.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setXydm(values[i]);
				myForm.setXymc(value1s[i]);
				File file = getZpjgxyhzWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 *德育自评学院汇总结果表导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getxxzpjghzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		File wordFile = getZpjgxxhzWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 *德育自评学院汇总结果表导出
	 */
	private File getZpjgxxhzWord(ZpjgModel myForm,HttpServletRequest request) throws Exception{
		ZpjgService service = new ZpjgService();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> zpjgxylist = null;
		HashMap<String, String> zphzxymap = null;
		zpjgxylist = service.getDyzpXxHzlist(myForm.getXn(),myForm.getXq());
		zphzxymap = service.getDyzpXxHzSumlist( myForm.getXn(), myForm.getXq());
		//德育自评查询
		DyzpService dyzpservice = new DyzpService();
		data.put("zpjglist",zpjgxylist);
		data.putAll(zphzxymap);
		data.put("xq", myForm.getXq().replace("0", ""));
		data.put("xn", myForm.getXn());
		data.put("y",   GetTime.getTimeByFormat("yyyy"));
		data.put("m",   GetTime.getTimeByFormat("mm"));
		data.put("d",   GetTime.getTimeByFormat("dd"));
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx//dyzp","dyzpxxhzb.xml",myForm.getXn());		
		return file;
	}
	
	/**
	 * 
	 * @描述: 德育小结个人汇总明细表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-20 下午02:40:25
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
	public ActionForward getZpjgMxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		ZpjgService service = new ZpjgService();
		String value = request.getParameter("value");
		String[] ids = value.split(",");
		List<HashMap<String, String>> xhList =service.getDistinctXh(ids); 
		if(xhList.size() == 1){
			myForm.setXh(xhList.get(0).get("xh"));
			File wordFile = getZpjgMxWord(myForm,request);
			FileUtil.outputWord(response, wordFile);
		}else if(xhList.size() > 1){
			request.setAttribute("xhList", xhList);
			this.getZpjgMxTy(mapping, myForm, request, response);
		}else{
			return null;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-20 下午07:12:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getZpjgMxWord(ZpjgModel myForm,HttpServletRequest request) throws Exception{
		ZpjgService service = new ZpjgService();
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> zpjgmap = null;
		
		zpjgmap = service.getDyzpMxhzXsxx(myForm.getXh());
		data.putAll(zpjgmap);
		data.put("xb",zpjgmap.get("xb").equals("1")?"男":"女");
		data.put("zpnr",HtmlUtil.xmlZy(zpjgmap.get("zpnr")));
//		data.put("xszpdj", xszpdj);
		List<HashMap<String, String>> dyzpList = service.getEveryXsDyMx(myForm.getXh());
		data.put("sysdate", GetTime.getTimeByFormat("yyyymmdd"));
		//取出学制
		String xz = zpjgmap.get("xz");
		//取出年级
		String nj = zpjgmap.get("nj");
	    //取出学生每年德育小结的数据
		List<HashMap<String, String>> xnxqMxList = new ArrayList<HashMap<String,String>>();
		int num = xnxqMxList.size();
		for (int i = 0; i < 8; i=i+2) {
			String xnxq1 = "";
			String djmc1 = "";
			if(i<dyzpList.size()){
				 xnxq1 = dyzpList.get(i).get("xnxq");
				 djmc1 = dyzpList.get(i).get("djmc");
			}
			
			String xnxq2 = "";
			String djmc2 = "";
			if(i+1 < dyzpList.size()){
				xnxq2 = dyzpList.get(i+1).get("xnxq");
				djmc2 = dyzpList.get(i+1).get("djmc");
			}
			HashMap<String, String> lsMap = new HashMap<String, String>();
			lsMap.put("xnxq1", xnxq1);
			lsMap.put("djmc1", djmc1);
			lsMap.put("xnxq2", xnxq2);
			lsMap.put("djmc2", djmc2);
			xnxqMxList.add(lsMap);
		}
//		if(num%2 !=0){
//			num = num+1;
//		}
//		if(num < 8){
//			for (int i = num+1; i <=8; i=i+2) {
//				String xnxq1 = "";
//				String djmc1 = "";
//				String xnxq2 = "";
//				String djmc2 = "";
//				HashMap<String, String> lsMap = new HashMap<String, String>();
//				lsMap.put("xnxq1", xnxq1);
//				lsMap.put("djmc1", djmc1);
//				lsMap.put("xnxq2", xnxq2);
//				lsMap.put("djmc2", djmc2);
//				xnxqMxList.add(lsMap);
//			}
//		}
		data.put("xnxqMxList", xnxqMxList);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx//dyzp","dyxj_hzmx.xml",zpjgmap.get("xh")+zpjgmap.get("xm"));		
		return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZpjgMxTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZpjgModel myForm = (ZpjgModel) form;
		List<HashMap<String, String>> xhList = (List<HashMap<String, String>>)request.getAttribute("xhList");
		
		if (null != xhList && xhList.size() > 0){
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = xhList.size() ; i < n ; i=i+1){
				myForm.setXh(xhList.get(i).get("xh"));
				File file = getZpjgMxWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	@Override
	/**
	 * 增加保存
	 * 重写该方法，原来直接调用的父类方法接收值的方式使用泛型方式，有可能取不到属性值
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		ZpjgModel model = (ZpjgModel) form;
	    ZpjgService service = new ZpjgService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	@Override
	/**
	 * 修改保存
	 * 重写该方法，原来直接调用的父类方法接收值的方式使用泛型方式，有可能取不到属性值
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZpjgModel model = (ZpjgModel) form;
		ZpjgService service = getService();
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
}


